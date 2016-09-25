package com.sgay.giligili.dao;

import com.sgay.giligili.entity.TorrentDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TorrentDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TorrentDetail record);

    int insertSelective(TorrentDetail record);

    TorrentDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TorrentDetail record);

    int updateByPrimaryKey(TorrentDetail record);

    List<TorrentDetail> getTorrentDetailList(String keyword);

}