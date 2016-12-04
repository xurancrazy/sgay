package com.sgay.giligili.quartz;

import com.sgay.giligili.redis.RedisSessionCallBack;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

import java.util.*;

/**
 * Created by xurancrazy on 2016/11/2.
 */
@Component
public class GeneratePopularMoviesQuartz extends BaseQuartz {

    @Scheduled(cron = "0 10 0 * * ? ")
    public void generateLastWeekPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastWeekPopularMovies  --> time:" + new Date());
        RedisSessionCallBack sessionCallback = new RedisSessionCallBack() {
            @Override
            protected void customizeBehavior(RedisOperations redisOperations) {
                redisOperations.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK);
                generateKeyCollections(redisOperations, yesterday, 7);
            }
        };
        mRedisTemplate.execute(sessionCallback);
    }

    @Scheduled(cron = "0 9 0 * * ? ")
    public void generateLastMonthPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastMonthPopularMovies --> time:" + new Date());
        RedisSessionCallBack sessionCallback = new RedisSessionCallBack() {
            @Override
            protected void customizeBehavior(RedisOperations redisOperations) {
                redisOperations.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH);
                generateKeyCollections(redisOperations, yesterday, 30);
            }
        };
        mRedisTemplate.execute(sessionCallback);
    }

    private void generateKeyCollections(RedisOperations redisOperations, Date base, int offset) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < offset; i++) {
            Date date = Utils.getDayOffsetFromBasicDay(base, i);
            String dateFormat = Utils.getDateFormatString(date);
            Set<ZSetOperations.TypedTuple<Object>> tmp = redisOperations.opsForZSet().rangeWithScores(Constants.MOVIE_PREFIX + ":" + dateFormat, 0, -1);
            if (tmp != null){
                for (ZSetOperations.TypedTuple<Object> item : tmp) {
                    String value = (String) item.getValue();
                    int score = item.getScore().intValue();
                    map.put(value,map.getOrDefault(value,0)+score);
                }
            }
        }
        redisOperations.multi();
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            redisOperations.opsForZSet().add(offset <= 7 ? Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK
                    : Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH, item.getKey(), item.getValue());
        }
    }
}
