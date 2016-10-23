package com.sgay.giligili.controller;

import com.sgay.giligili.exception.PageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xurancrazy on 2016/10/23.
 */
@ControllerAdvice
public class GlobalControllerAdvice extends BaseController{

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PageNotFoundException.class)
    public String pageNotFoundHandler(HttpServletResponse httpServletResponse, PageNotFoundException exception) throws IOException {
        logger.error(exception.getCustomizeMessage());
        return DEFAULT_404_NOTFOUND_VIEW;
    }
}
