package com.sgay.giligili.aop;

import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

    private SimpleDateFormat simpleDateFormat = Utils.createSimpleDateFormat(Constants.sfPattern);

    @Pointcut("execution(* com.sgay.giligili.controller.TeachersController.teacherMovies(..)) && args(teacherName, ..)")
    public void viewTeacherPage(String teacherName){}

    @Pointcut("execution(* com.sgay.giligili.controller.TeachersController.movieDetail(..)) && args(teacherName, movieName, ..)")
    public void viewMoviePage(String teacherName, String movieName){}


    @AfterReturning("viewTeacherPage(teacherName)")
    public void addTeacherOneClick(String teacherName){
        String key = Constants.TEACHER_PREFIX + ":" + simpleDateFormat.format(new Date());
        System.out.println(key);
        template.opsForZSet().incrementScore(key,teacherName,1);
    }

    @AfterReturning("viewMoviePage(teacherName, movieName)")
    public void addMovieOneClick(String teacherName, String movieName){
        String key = Constants.MOVIE_PREFIX + ":" + simpleDateFormat.format(new Date());
        System.out.println(key);
        template.opsForZSet().incrementScore(key, movieName, 1);
    }
}
