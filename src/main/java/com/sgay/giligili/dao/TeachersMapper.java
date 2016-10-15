package com.sgay.giligili.dao;

import com.sgay.giligili.entity.Teachers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeachersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKey(Teachers record);

    List<Teachers> selectTeachers();

    Teachers selectTeacherByName(String teacherName);
}