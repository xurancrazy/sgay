package com.sgay.giligili.controller;

import com.sgay.giligili.service.ITorrentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class MainPageController {

	@Autowired
	private ITorrentService iTorrentService;


	private static Logger logger= LoggerFactory.getLogger(MainPageController.class);

	private static final String defaultURL = "";

	@GetMapping(value = "/")
	public String index(){
		return "index";
	}


	@GetMapping(value ="/actors")
	public String getActorsList(ModelMap modelMap)
	{
		return "index";
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
