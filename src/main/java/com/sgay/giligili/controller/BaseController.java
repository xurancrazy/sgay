package com.sgay.giligili.controller;

import com.sgay.giligili.service.IMoviesService;
import com.sgay.giligili.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xurancrazy on 2016/10/16.
 */

@Controller
public class BaseController {
    @Autowired
    protected IMoviesService mMoviesService;
    @Autowired
    protected ITeacherService mTeacherService;

    protected  Logger logger= LoggerFactory.getLogger(BaseController.class);

}
