package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MovieMapper;
import com.sgay.giligili.dao.TeacherMapper;
import com.sgay.giligili.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/16.
 */
public class BaseService {
    @Autowired
    protected MovieMapper mMovieMapper;

    @Autowired
    protected TeacherMapper mTeacherMapper;
    // inject the actual template
    @Autowired
    protected RedisTemplate<String, Object> mRedisTemplate;

}
