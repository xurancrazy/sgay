package com.sgay.giligili.controller;

import com.sgay.giligili.service.IMoviesService;
import com.sgay.giligili.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xurancrazy on 2016/10/16.
 */

public class BaseController{
    @Autowired
    protected IMoviesService mMoviesService;
    @Autowired
    protected ITeacherService mTeacherService;
    @Autowired
    protected RedisTemplate<String, String> mRedisTemplate;

    protected  Logger logger= LoggerFactory.getLogger(BaseController.class);

    protected static final String DEFAULT_404_NOTFOUND_VIEW = "common/404";

    protected static final String DEFAULT_INTERNAL_ERROR_VIEW = "common/500";

}
