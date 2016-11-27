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

    public void testTransaction(){
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastWeekPopularMovies --> time:" + new Date());
        mRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.multi();
                Set<RedisZSetCommands.Tuple> set = redisConnection.zRangeWithScores("testset".getBytes(),0,-1);
                if (set!=null){
                    System.out.println(set.size());
                }
                return redisConnection.exec();
            }
        });
//        mRedisTemplate.opsForValue().set("test4","test4");
//        mRedisTemplate.opsForValue().set("test2","test2");
//        String test1 = (String) mRedisTemplate.opsForValue().get("test");
//        System.out.println(test1);
//        mRedisTemplate.opsForList().leftPush("test3","test3");
//        Set<ZSetOperations.TypedTuple<Object>> a = mRedisTemplate.opsForZSet().rangeWithScores("testset",0,2);
//        if (a!=null){
//
//            System.out.println(a.size());
//        }
    }

    @Scheduled(cron = "0 2 0 ? * 1 ")
    public void generateLastWeekPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastWeekPopularMovies" + new Date());
        RedisSessionCallBack sessionCallback = new RedisSessionCallBack() {
            @Override
            protected void customizeBehavior(RedisOperations redisOperations) {
                redisOperations.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK);
                generateKeyCollections(redisOperations, yesterday, 7);
            }
        };
        mRedisTemplate.execute(sessionCallback);
        System.out.println("begin");
    }

    @Scheduled(cron = "0 2 0 1 * ? ")
    public void generateLastMonthPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastMonthPopularMovies --> time:" + new Date());
//        mRedisTemplate.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH);
//        generateKeyCollections(yesterday,30);
        String abc = "aaa";
        RedisSessionCallBack sessionCallback = new RedisSessionCallBack() {
            @Override
            protected void customizeBehavior(RedisOperations redisOperations) {
//                redisOperations.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH);
//                generateKeyCollections(redisOperations, yesterday, 30);
                Set<ZSetOperations.TypedTuple<Object>> tmp = redisOperations.opsForZSet().rangeWithScores("testset",0,-1);
                if (tmp!=null){
                    System.out.println(tmp.size());}
                redisOperations.multi();
                test(redisOperations,abc);
                redisOperations.exec();
                redisOperations.multi();
                test(redisOperations,abc);
                System.out.println("end");
            }
            @Override
            protected void customizeWatch(RedisOperations redisOperations){
                redisOperations.watch("testset");
            }
        };
        System.out.println("begin");
        mRedisTemplate.execute(sessionCallback);

    }

    private void test(RedisOperations redisOperations,String abc){
        redisOperations.opsForZSet().incrementScore("testset",abc,5);
        redisOperations.opsForValue().set("test","multi");
        redisOperations.opsForList().leftPush("test1","abc11");
        redisOperations.opsForList().leftPush("test1",abc);
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
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            redisOperations.opsForZSet().add(offset <= 7 ? Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK
                    : Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH, item.getKey(), item.getValue());
        }
    }
}
