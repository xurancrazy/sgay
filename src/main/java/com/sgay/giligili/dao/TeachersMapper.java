package com.sgay.giligili.dao;

import com.sgay.giligili.entity.Teachers;

public interface TeachersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKeyWithBLOBs(Teachers record);

    int updateByPrimaryKey(Teachers record);
}