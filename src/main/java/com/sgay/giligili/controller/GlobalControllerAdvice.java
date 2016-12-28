package com.sgay.giligili.controller;

import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xurancrazy on 2016/10/23.
 */
@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object> handlerNotFound(WebRequest request, NoHandlerFoundException exception){
//        logger.error("NoHandlerFoundException");
//        return this.handleException(exception,request);
//    }

//    @Override
//    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        logger.error("NoHandlerFoundException");
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String pageNotFound(PageNotFoundException exception){
        logger.error(exception.getCustomizeMessage());
        return Constants.DEFAULT_404_NOTFOUND_VIEW;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public String IOExceptionHandler(HttpServletResponse httpServletResponse, IOException exception) throws IOException {
        logger.error(exception.getMessage());
        return Constants.DEFAULT_INTERNAL_ERROR_VIEW;
    }
}
