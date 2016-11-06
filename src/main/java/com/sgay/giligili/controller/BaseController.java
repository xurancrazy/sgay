package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.service.IMovieService;
import com.sgay.giligili.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;

/**
 * Created by xurancrazy on 2016/10/16.
 */

public class BaseController{
    @Autowired
    protected IMovieService mMovieService;
    @Autowired
    protected ITeacherService mTeacherService;
    @Autowired
    protected RedisTemplate<String, String> mRedisTemplate;

    protected  Logger logger= LoggerFactory.getLogger(this.getClass());

    protected static final String DEFAULT_404_NOTFOUND_VIEW = "common/404";

    protected static final String DEFAULT_INTERNAL_ERROR_VIEW = "common/500";

    @ModelAttribute
    public void queryPopularMovies(ModelMap modelMap) throws IOException {
        List<Movie> todayPopularMovies = mMovieService.queryTodayPopularMovies();
        List<Movie> lastWeekPopularMovies = mMovieService.queryLastWeekPopularMovies();
        List<Movie> lastMonthPopularMovies = mMovieService.queryLastMonthPopularMovies();
        modelMap.addAttribute("todayPopularMovies",todayPopularMovies);
        modelMap.addAttribute("lastWeekPopularMovies",lastWeekPopularMovies);
        modelMap.addAttribute("lastMonthPopularMovies",lastMonthPopularMovies);
    }

}
