package com.sgay.giligili.controller;

import com.sgay.giligili.entity.TorrentMap;
import com.sgay.giligili.service.ITorrentService;
import com.sgay.giligili.entity.TorrentDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ITorrentService iTorrentService;

	private static final String defaultURL = "http://www.torrentkitty.me/search/";//爬虫的基础URL

	private static Logger logger=LogManager.getLogger(SearchController.class.getName());
	@RequestMapping(method=RequestMethod.POST)
	public String getResult(@RequestParam("fanhao") String fanhao)
	{
		return "redirect:/search/"+fanhao;
	}

	@ResponseBody
	@RequestMapping(value="/{fanhao}",method=RequestMethod.GET)
	public List<TorrentDetail> getTorrent(@PathVariable String fanhao)
	{

		logger.error("111");
		fanhao=fanhao.trim();//对输入的参数清除两旁的空格
		TorrentMap exist=iTorrentService.isTorrentMapExist(fanhao);
		if (exist!=null)//若该番号已有人查询过
		{
			exist.setLastestQuery(new Date(System.currentTimeMillis()));
			exist.setQuerycount(exist.getQuerycount()+1);
			iTorrentService.saveTorrentMap(exist);
			return iTorrentService.getTorrentDetailList(fanhao);
		}
		else {//若该番号无人查询，则爬取数据
			String html=getHTMLContent(fanhao);//根据番号获取html内容

			List<String> fanhaoItem=parseHTMLToFanhaoItem(html);//根据html内容解析出每个番号项

			List<TorrentDetail> cars =iTorrentService.createTorrent(fanhao,fanhaoItem);	//将数据插入数据库中并返回

			return cars;
		}
	}
	private String getHTMLContent(String fanhao)
	{
		URL url;
		URLConnection con = null;
		try {
			url = new URL(defaultURL + fanhao + "/");
			con = url.openConnection();
			con.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");

		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder html = new StringBuilder();
		try (InputStream in = con.getInputStream()) {
			Reader r = new InputStreamReader(new BufferedInputStream(in), "UTF-8");
			int c;
			while ((c = r.read()) != -1)
				html.append((char) c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html.toString();
	}
	private List<String> parseHTMLToFanhaoItem(String html)
	{
		List<String> result = new ArrayList<>();
		String regex = "(?<=<(tr)>).*(?=</\\1>)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			result.add(matcher.group());
		}
		result.remove(0);//获取每个番号项，将标题栏删去
		return result;
	}

}