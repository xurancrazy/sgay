package com.sgay.giligili.quartz;

import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by xurancrazy on 2016/11/2.
 */
@Component
public class GeneratePopularMoviesQuartz extends BaseQuartz {


    private class RedisSessionCallBack implements SessionCallback{
        private Date yesterday = Utils.getYesterday();
        private int days;
        private String key;

        public RedisSessionCallBack(int days){
            this.days = days;
            this.key = days <= 7 ? Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK
                    : Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH;
        }

        @Override
        public Object execute(RedisOperations redisOperations) throws DataAccessException {
            Map<String, Integer> keysMap = generateKeyCollections(redisOperations, yesterday, days);
            List<Map.Entry<String, Integer>> allMovies = new ArrayList<>(keysMap.entrySet());
            allMovies.sort((final Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue() - o1.getValue());
            int size = allMovies.size();
            List<Map.Entry<String, Integer>> topMovies = allMovies.subList(0, size < 10 ? size :10);
            for (Map.Entry<String, Integer> item : topMovies) {
                mMovieService.queryMovieByFanhao(item.getKey());
            }
            redisOperations.delete(key);
            redisOperations.multi();
            for (Map.Entry<String, Integer> item : topMovies) {
                redisOperations.opsForZSet().add(key, item.getKey(), item.getValue());
            }
            return redisOperations.exec();
        }
    }

    @Scheduled(cron = "0 9 0 * * ? ")
    public void generateLastWeekPopularMovies() {
        int days = 7;
        logger.info("GenerateLastWeekPopularMovies  --> time:" + new Date());
        RedisSessionCallBack redisSessionCallBack = new RedisSessionCallBack(days);
        mRedisTemplate.execute(redisSessionCallBack);

    }

    @Scheduled(cron = "0 10 0 * * ? ")
    public void generateLastMonthPopularMovies() {
        int days = 30;
        logger.info("GenerateLastMonthPopularMovies --> time:" + new Date());
        RedisSessionCallBack redisSessionCallBack = new RedisSessionCallBack(days);
        mRedisTemplate.execute(redisSessionCallBack);
    }

    private Map<String, Integer> generateKeyCollections(RedisOperations redisOperations, Date base, int offset) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < offset; i++) {
            Date date = Utils.getDayOffsetFromBasicDay(base, i);
            String dateFormat = Utils.getDateFormatString(date);
            Set<ZSetOperations.TypedTuple<Object>> tmp = redisOperations.opsForZSet().rangeWithScores(Constants.MOVIE_PREFIX + ":" + dateFormat, 0, -1);
            if (tmp != null){
                for (ZSetOperations.TypedTuple<Object> item : tmp) {
                    String value = (String) item.getValue();
                    int score = item.getScore().intValue();
                    map.put(value,map.getOrDefault(value,0) + score);
                }
            }
        }
        return map;
    }
}
