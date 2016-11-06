package com.sgay.giligili.quartz;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Date;
import java.util.Set;

/**
 * Created by xurancrazy on 2016/11/2.
 */
public class RedisCachePersistenceQuartz extends BaseQuartz{

    public void updateTeacherViewNum(){
        Date today = Utils.getToday();
        Date yesterday = Utils.getYesterday();
        logger.info("updateTeacherViewNum --> today = " + today + ",yesterday = " + yesterday);
        String key = Constants.TEACHER_PREFIX + ":" + Utils.getDateFormatString(yesterday);
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
        for (ZSetOperations.TypedTuple<Object> teacherAndNums : set){
            String teacherName = (String)teacherAndNums.getValue();
            int nums = (int)teacherAndNums.getScore().doubleValue();
            Teacher teacher =mTeacherService.queryTeacherByName(teacherName);
            teacher.setViewsnum(teacher.getViewsnum()+nums);
            mTeacherService.updateTeacherViewsNum(teacher);
        }
    }

    public void updateMovieViewNum(){
        Date today = Utils.getToday();
        Date yesterday = Utils.getYesterday();
        logger.info("updateTeacherViewNum --> today = " + today + ",yesterday = " + yesterday);
        String key = Constants.MOVIE_PREFIX + ":" + yesterday;
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
        for (ZSetOperations.TypedTuple<Object> fanhaoAndNums : set){
            String fanhao = (String)fanhaoAndNums.getValue();
            int nums = (int)fanhaoAndNums.getScore().doubleValue();
            Movie movie = mMovieService.queryMovieByFanhao(fanhao);
            movie.setViewsnum(movie.getViewsnum()+nums);
            mMovieService.updateMovieViewsNum(movie);
        }
    }
}
