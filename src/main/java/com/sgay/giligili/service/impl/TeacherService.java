package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.TeachersMapper;
import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;
import com.sgay.giligili.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Teachers> queryTeachers() {
        return mTeachersMapper.selectTeachers();
    }

    @Cacheable(value = "queryTeacherByName",key = "'teacherDetail:'+#teacherName")
    @Override
    public Teachers queryTeacherByName(String teacherName) {
        return mTeachersMapper.selectTeacherByName(teacherName);
    }

    @CacheEvict(value = "queryTeacherByName",key = "'teacherDetail:'+#teacher.name")
    @Override
    public void updateTeacherViewsNum(Teachers teacher) {
        mTeachersMapper.updateByPrimaryKeySelective(teacher);
    }
}
