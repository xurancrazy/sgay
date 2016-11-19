package com.sgay.giligili.quartz;

import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xurancrazy on 2016/11/12.
 */

@Component
public class GenerateRandomMoviesQuartz extends BaseQuartz {

    @Scheduled(cron = "0 0/15 * * * ? ")
    public void removeRandomMoviesCache() {
        logger.info("GenerateRandomMovies" + System.currentTimeMillis());
        mRedisTemplate.delete(RANDOM_MOVIES_KEY);
    }

    @Scheduled(cron = "0 0/15 * * * ? ")
    public void removeEditorRecommendMoviesCache() {
        logger.info("GenerateRandomMovies" + System.currentTimeMillis());
        mRedisTemplate.delete(EDITOR_RECOMMEND_MOVIES_KEY);
    }

    @Scheduled(cron = "0 0/15 * * * ? ")
    public void removeImageAndTextRecommendMoviesCache() {
        logger.info("GenerateRandomMovies" + System.currentTimeMillis());
        mRedisTemplate.delete(IMAGE_AND_TEXT_MOVIES_KEY);
    }
}
