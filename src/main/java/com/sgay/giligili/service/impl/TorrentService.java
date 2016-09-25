package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.TorrentDetailMapper;
import com.sgay.giligili.dao.TorrentMapMapper;
import com.sgay.giligili.entity.TorrentDetail;
import com.sgay.giligili.entity.TorrentMap;
import com.sgay.giligili.service.ITorrentService;
import common.constants.Torrent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xurancrazy on 2016/5/6.
 */

@Service
public class TorrentService implements ITorrentService {
    @Autowired
    private TorrentDetailMapper torrentDetailMapper;
    @Autowired
    private TorrentMapMapper torrentMapMapper;

    @Override
    public TorrentDetail createTorrentDetail(String torrentname, String torrentsize,
                                       String torrentuploadtime, String torrentmagnet, String keyword) {
        TorrentDetail torrentDetail=new TorrentDetail();
        torrentDetail.setKeyword(keyword);
        torrentDetail.setTorrentmagnet(torrentmagnet);
        torrentDetail.setTorrentname(torrentname);
        torrentDetail.setTorrentsize(torrentsize);
        torrentDetail.setTorrentuploadtime(torrentuploadtime);
        torrentDetailMapper.insertSelective(torrentDetail);
        return torrentDetail;
    }

    @Override
    public TorrentMap createTorrentMap(String keyword, Long queryCount, Date lastestQuery) {
        TorrentMap torrentMap=new TorrentMap();
        torrentMap.setKeyword(keyword);
        torrentMap.setLastestQuery(new Timestamp(System.currentTimeMillis()));
        torrentMap.setQuerycount(queryCount);
        torrentMapMapper.insertSelective(torrentMap);
        return torrentMap;
    }

    @Override
    public TorrentMap isTorrentMapExist(String keyword) {
        TorrentMap torrentMap=torrentMapMapper.getTorrentMap(keyword);
        return torrentMap;
    }

    @Override
    public List<TorrentDetail> getTorrentDetailList(String fanhao) {
        List<TorrentDetail> res=torrentDetailMapper.getTorrentDetailList(fanhao);
        return res;
    }


    @Override
    public void saveTorrentMap(TorrentMap torrentMap) {
        torrentMapMapper.updateByPrimaryKey(torrentMap);
    }

    @Override
    @Transactional
    public List<TorrentDetail> createTorrent(String fanhao, List<String> fanhaoItem) {
        List<TorrentDetail> cars=new ArrayList<>();

        //在torrent_map表中插入数据
        createTorrentMap(fanhao,(long)1,new Date(System.currentTimeMillis()));

        for (String item : fanhaoItem) {
            //根据每个番号项匹配出其中的片名，磁力链接，种子大小，种子时间
            String regexName = "(?<=<(td)\\s\\bclass\\b=\"name\">).*?(?=</\\1>)";
            String regexMagnet = "<a\\s\\bhref\\b=\"magnet.*(?=\"\\stitle)";
            String regexTorrentSize = "(?<=<(td)\\s\\bclass\\b=\"size\">).*?(?=</\\1>)";
            String regexUploadTime = "(?<=<(td)\\s\\bclass\\b=\"date\">).*?(?=</\\1>)";

            String name = buildTemplate(item, regexName, Torrent.NAME);
            String magnet = buildTemplate(item, regexMagnet, Torrent.MAGNET).substring(9);
            String uploadTime = buildTemplate(item, regexUploadTime, Torrent.UPLOADTIME);
            String torrentSize = buildTemplate(item, regexTorrentSize, Torrent.TORRENTSIZE);
            String keyword = fanhao;

            //在torrent_detail表中插入数据
            TorrentDetail torrentDetail = createTorrentDetail(name, torrentSize, uploadTime, magnet, keyword);

            cars.add(torrentDetail);
        }
        return cars;
    }
    private  String buildTemplate(String item,String regex,Torrent type)
    {
        String result="";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(item);
        if(matcher.find())
        {
            String nameForAccess=matcher.group();
            if(type==Torrent.NAME&&nameForAccess.startsWith("<a class"))
            {
                regex="(?<=</script>).*";
                pattern=Pattern.compile(regex);
                matcher=pattern.matcher(nameForAccess);
                if(matcher.find())
                {
                    nameForAccess=matcher.group();
                }
            }
            result=nameForAccess;
        }
        return result;
    }
}
