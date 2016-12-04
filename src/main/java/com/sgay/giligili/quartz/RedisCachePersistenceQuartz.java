package com.sgay.giligili.quartz;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.redis.RedisSessionCallBack;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.Set;

/**
 * Created by xurancrazy on 2016/11/2.
 */
public class RedisCachePersistenceQuartz extends BaseQuartz {

    public void updateTeacherViewNum() {
        Date today = Utils.getToday();
        Date yesterday = Utils.getYesterday();
        logger.info("updateTeacherViewNum --> today = " + today + ",yesterday = " + yesterday);
        String key = Constants.TEACHER_PREFIX + ":" + Utils.getDateFormatString(yesterday);
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
        if (set != null){
            for (ZSetOperations.TypedTuple<Object> teacherAndNums : set) {
                String teacherName = (String) teacherAndNums.getValue();
                int nums = (int) teacherAndNums.getScore().doubleValue();
                try {
                    Teacher teacher = mTeacherService.queryTeacherByName(teacherName);
                    teacher.setViewsnum(teacher.getViewsnum() + nums);
                    mTeacherService.updateTeacherViewsNum(teacher);
                }catch (Exception e){
                    //rollback the nested Transaction
                    //continue the loop
                }
            }
        }
    }

    public void updateTeacherLikeNum() {
        Date today = Utils.getToday();
        Date yesterday = Utils.getYesterday();
        logger.info("updateTeacherLikesNum --> today = " + today + ",yesterday = " + yesterday);
        String key = Constants.TEACHER_PREFIX + ":" + Constants.TEACHER_VOTE + ":"+ Utils.getDateFormatString(yesterday);
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
        if (set != null){
            for (ZSetOperations.TypedTuple<Object> teacherAndNums : set) {
                String teacherName = (String) teacherAndNums.getValue();
                int nums = (int) teacherAndNums.getScore().doubleValue();
                try {
                    Teacher teacher = mTeacherService.queryTeacherByName(teacherName);
                    teacher.setLikesnum(teacher.getLikesnum() + nums);
                    mTeacherService.updateTeacherViewsNum(teacher);
                }catch (Exception e){
                    //rollback the nested Transaction
                    //continue the loop
                }
            }
        }
    }


    public void updateMovieViewNum() {
        Date today = Utils.getToday();
        Date yesterday = Utils.getYesterday();
        logger.info("updateTeacherViewNum --> today = " + today + ",yesterday = " + yesterday);
        String key = Constants.MOVIE_PREFIX + ":" + Utils.getDateFormatString(yesterday);
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
        if (set != null){
            for (ZSetOperations.TypedTuple<Object> fanhaoAndNums : set) {
                String fanhao = (String) fanhaoAndNums.getValue();
                int nums = (int) fanhaoAndNums.getScore().doubleValue();
                try {
                    Movie movie = mMovieService.queryMovieByFanhao(fanhao);
                    movie.setViewsnum(movie.getViewsnum() + nums);
                    mMovieService.updateMovieViewsNum(movie);
                }catch (Exception e){
                    //rollback the nested Transaction
                    //continue the loop
                }
            }
        }
    }

    public void updateTeachers() {
        logger.info("updateTeachers" + System.currentTimeMillis());
        mRedisTemplate.delete(TEACHERS_KEY);
    }

}
