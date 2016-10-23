package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MoviesMapper;
import com.sgay.giligili.dao.TeachersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * Created by xurancrazy on 2016/10/16.
 */
public class BaseService {
    @Autowired
    protected MoviesMapper mMoviesMapper;

    @Autowired
    protected TeachersMapper mTeachersMapper;

    // inject the actual template
    @Autowired
    protected RedisTemplate<String, String> template;

}
