package com.sgay.giligili.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by xurancrazy on 2016/11/6.
 */
public class BaseAop {

    @Autowired
    protected RedisTemplate<String,String> mRedisTemplate;

    protected Logger logger= LoggerFactory.getLogger(this.getClass());
}
