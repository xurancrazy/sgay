package com.sgay.giligili.service;

import com.sgay.giligili.entity.TorrentDetail;
import com.sgay.giligili.entity.TorrentMap;

import java.util.Date;
import java.util.List;

/**
 * Created by xurancrazy on 2016/5/6.
 */

public interface ITorrentService {
    //创建一项TorrentDetail
    TorrentDetail createTorrentDetail(String torrentname,String torrentsize,
                       String torrentuploadtime,String torrentmagnet,String keyword);

    //创建一项TorrentMap
    TorrentMap createTorrentMap(String keyword,Long queryCount,Date lastestQuery);

    //查询是否存在某TorrentMap记录并获得实例
    TorrentMap isTorrentMapExist(String keyword);

    //通过番号获得TorrentDetail
    List<TorrentDetail> getTorrentDetailList(String fanhao);

    //存储最新的TorrentMap
    void saveTorrentMap(TorrentMap torrentMap);

    //创建一个番号的Torrent集
    List<TorrentDetail> createTorrent(String fanhao, List<String> result);

}
