package com.sgay.giligili.service.impl;

import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.ITeacherService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
@Service
public class TeacherService extends BaseService implements ITeacherService {

    @Cacheable(value = "queryTeachers",key = "'teachers'")
    @Override
    public List<Teacher> queryTeachers() {
        return mTeacherMapper.selectTeachers();
    }

    @Cacheable(value = "queryTeacherByName",key = "'teacherDetail:'+#teacherName")
    @Override
    public Teacher queryTeacherByName(String teacherName) {
        Teacher teacher = mTeacherMapper.selectTeacherByName(teacherName);
        if (teacher == null){
            throw new PageNotFoundException("queryTeacherByName-->"+"teacherName="+teacherName);
        }
        return teacher;
    }

    @CacheEvict(value = "queryTeacherByName",key = "'teacherDetail:'+#teacher.name")
    @Override
    public void updateTeacherViewsNum(Teacher teacher) {
        mTeacherMapper.updateByPrimaryKeySelective(teacher);
    }
}
