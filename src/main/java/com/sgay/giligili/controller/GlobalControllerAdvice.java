package com.sgay.giligili.controller;

import com.sgay.giligili.exception.ForbiddenAccessException;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xurancrazy on 2016/10/23.
 */
@ControllerAdvice
public class GlobalControllerAdvice{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(PageNotFoundException.class)
    public String pageNotFound(HttpServletResponse httpServletResponse, PageNotFoundException exception){
        logger.error(exception.getCustomizeMessage());
        return Constants.DEFAULT_404_NOTFOUND_VIEW;
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public String forbidden(HttpServletResponse httpServletResponse, ForbiddenAccessException exception){
        logger.error(exception.getCustomizeMessage());
        return Constants.DEFAULT_403_FORBIDDEN_VIEW;
    }
}
