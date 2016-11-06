package com.sgay.giligili.service;

import com.sgay.giligili.entity.Teacher;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
public interface ITeacherService {
    List<Teacher> queryTeachers();

    Teacher queryTeacherByName(String teacherName);

    void updateTeacherViewsNum(Teacher teacher);
}
