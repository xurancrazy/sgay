package com.sgay.giligili.dao;

import com.sgay.giligili.entity.TorrentMap;
import org.springframework.stereotype.Component;

@Component
public interface TorrentMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TorrentMap record);

    int insertSelective(TorrentMap record);

    TorrentMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TorrentMap record);

    int updateByPrimaryKey(TorrentMap record);

    TorrentMap getTorrentMap(String keyword);
}