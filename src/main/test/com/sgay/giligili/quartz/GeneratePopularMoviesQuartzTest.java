package com.sgay.giligili.quartz;

import com.sgay.giligili.BaseTest;
import org.junit.Test;

/**
 * Created by xurancrazy on 2016/11/27.
 */
public class GeneratePopularMoviesQuartzTest extends BaseTest {
    @Test
    public void generateLastMonthPopularMovies(){
        mGeneratePopularMoviesQuartz.generateLastMonthPopularMovies();
    }

    @Test
    public void generateLastWeekPopularMovies(){
        mGeneratePopularMoviesQuartz.generateLastWeekPopularMovies();
    }
}
