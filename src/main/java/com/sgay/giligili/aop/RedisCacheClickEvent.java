package com.sgay.giligili.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xurancrazy on 2016/10/23.
 */
@Aspect
@Component
public class RedisCacheClickEvent {

    @Autowired
    private RedisTemplate<String,String> template;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Pointcut("execution(* com.sgay.giligili.controller.TeachersController.teacherMovies(String))"+
    "&&args(teacherName,..)")
    public void viewPages(String teacherName){}

    @AfterReturning("viewPages(teacherName)")
    public void addTeacherOneView(String teacherName){
        String key = simpleDateFormat.format(new Date());
        System.out.println(key);
        template.opsForZSet().incrementScore(key,"testaopafter",1);
    }

    @Before("viewPages(teacherName)")
    public void test(String teacherName){
        String key = simpleDateFormat.format(new Date());
        System.out.println(key);
        template.opsForZSet().incrementScore(key,"testaopbefore",1);
    }
}
