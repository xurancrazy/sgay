package com.sgay.giligili.aop;

import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xurancrazy on 2016/10/23.
 */
@Aspect
@Component
public class RedisCacheClickEventAop extends BaseAop{

    @Pointcut("execution(* com.sgay.giligili.controller.TeacherController.teacherDetail(..)) && args(teacherName, ..)")
    public void viewTeacherPage(String teacherName){}

    @Pointcut("execution(* com.sgay.giligili.controller.MovieController.movieDetail(..)) && args(movieName, ..)")
    public void viewMoviePage(String movieName){}

    @AfterReturning("viewTeacherPage(teacherName)")
    public void addTeacherOneClick(String teacherName){
        Date today = Utils.getToday();
        String key = Constants.TEACHER_PREFIX + ":" + Utils.getDateFormatString(today);
        logger.info("addTeacherOneClick --> key:" + key);
        mRedisTemplate.opsForZSet().incrementScore(key,teacherName,1);
    }

    @AfterReturning("viewMoviePage(movieName)")
    public void addMovieOneClick(String movieName){
        Date today = Utils.getToday();
        String key = Constants.MOVIE_PREFIX + ":" + Utils.getDateFormatString(today);
        logger.info("addMovieOneClick --> key:" + key);
        mRedisTemplate.opsForZSet().incrementScore(key, movieName, 1);
    }
}
