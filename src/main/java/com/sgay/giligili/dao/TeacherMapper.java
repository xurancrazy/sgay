package com.sgay.giligili.dao;

import com.sgay.giligili.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectTeachers();

    Teacher selectTeacherByName(String teacherName);
}