package com.sgay.giligili.service.impl;

import com.google.common.base.Strings;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.ITeacherService;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
@Service
public class TeacherService extends BaseService implements ITeacherService {

    @Cacheable(value = "TeacherService",key = "'teachers'")
    @Override
    public List<Teacher> queryTeachers() {
        return mTeacherMapper.selectTeachers();
    }

    @Cacheable(value = "TeacherService",key = "'teacherDetail:'+#teacherName")
    @Override
    public Teacher queryTeacherByName(String teacherName) {
        if (Strings.isNullOrEmpty(teacherName)){
            throw new PageNotFoundException("Exception: queryTeacherByName--> " + "teacherName = " + teacherName);
        }
        Teacher teacher = mTeacherMapper.selectTeacherByName(teacherName);
        if (teacher == null){
            throw new PageNotFoundException("Exception: queryTeacherByName--> " + "teacherName = " + teacherName);
        }
        return teacher;
    }

    @CachePut(value = "TeacherService",key = "'teacherDetail:'+#result.name")
    @Override
    public Teacher queryTeacherById(long id) {
        Teacher teacher = mTeacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    @CacheEvict(value = "TeacherService",key = "'teacherDetail:'+#teacher.name")
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void updateTeacherViewsNum(Teacher teacher) {
        mTeacherMapper.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public void updateTeacherLikesNumInCache(String teacherName) {
        Date today = Utils.getToday();
        logger.info("updateTeacherLikesNumInCache --> today = " + today + ",teacherName=" + teacherName);
        String key = Constants.TEACHER_PREFIX + ":" + Constants.TEACHER_VOTE + ":" + Utils.getDateFormatString(today);
        mRedisTemplate.opsForZSet().incrementScore(key,teacherName,1);
    }

}
