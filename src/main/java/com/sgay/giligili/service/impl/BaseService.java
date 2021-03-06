package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MovieMapper;
import com.sgay.giligili.dao.TeacherMapper;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/16.
 */
public class BaseService implements IBaseService{
    protected Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MovieMapper mMovieMapper;

    @Autowired
    protected TeacherMapper mTeacherMapper;
    // inject the actual template
    @Autowired
    protected RedisTemplate<String, Object> mRedisTemplate;

    @Override
    public boolean isRedisKeyExist(String key){
        return mRedisTemplate.hasKey(key);
    }
}
