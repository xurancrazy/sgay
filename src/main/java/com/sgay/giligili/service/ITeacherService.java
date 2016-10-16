package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
public interface ITeacherService {
    List<Teachers> queryTeachers();

    Teachers queryTeacherByName(String teacherName);

    void updateTeacherViewsNum(Teachers teacher);
}
