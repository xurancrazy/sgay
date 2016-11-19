package com.sgay.giligili.quartz;

import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by xurancrazy on 2016/11/2.
 */
@Component
public class GeneratePopularMoviesQuartz extends BaseQuartz {

    @Scheduled(cron = "0 2 0 ? * 1 ")
    public void generateLastWeekPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastWeekPopularMovies" + new Date());
        mRedisTemplate.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK);
        generateKeyCollections(yesterday,7);
    }

    @Scheduled(cron = "0 2 0 1 * ? ")
    public void generateLastMonthPopularMovies() {
        Date yesterday = Utils.getYesterday();
        logger.info("GenerateLastWeekPopularMovies --> time:" + new Date());
        mRedisTemplate.delete(Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH);
        generateKeyCollections(yesterday,30);
    }

    private void generateKeyCollections(Date base, int offset) {
        Set<ZSetOperations.TypedTuple<Object>> set = mRedisTemplate.opsForZSet().rangeWithScores(Constants.MOVIE_PREFIX + ":" + Utils.getDateFormatString(base),0 ,-1);
        Map<String, Integer> map = new HashMap<>();
        if (set != null){
            for (ZSetOperations.TypedTuple<Object> item : set) {
                map.put((String) item.getValue(), item.getScore().intValue());
            }
        }
        for (int i = 1; i < offset; i++) {
            Date date = Utils.getDayOffsetFromBasicDay(base, i);
            String dateFormat = Utils.getDateFormatString(date);
            Set<ZSetOperations.TypedTuple<Object>> tmp = mRedisTemplate.opsForZSet().rangeWithScores(Constants.MOVIE_PREFIX + ":" + dateFormat, 0, -1);
            if (tmp != null){
                for (ZSetOperations.TypedTuple<Object> item : tmp) {
                    String value = (String) item.getValue();
                    int score = item.getScore().intValue();
                    map.put(value,map.getOrDefault(value,0)+score);
                }
            }
        }
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            mRedisTemplate.opsForZSet().add(offset <= 7 ? Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK
                    : Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH, item.getKey(), item.getValue());
        }
    }
}
