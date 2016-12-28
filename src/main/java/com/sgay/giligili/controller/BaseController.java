package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    protected IVoteService mVoteService;

    @Autowired
    protected ICategoryService mCategoryService;

    @Autowired
    protected RedisTemplate<String, String> mRedisTemplate;

    protected  Logger logger= LoggerFactory.getLogger(this.getClass());



    protected void SeoOptimization(ModelMap modelMap, String description, String keywords, String title){
        modelMap.addAttribute("title",title);
        modelMap.addAttribute("description",description);
        modelMap.addAttribute("keywords",keywords);
    }

    @ModelAttribute
    public void queryPopularMovies(ModelMap modelMap) throws IOException {
        List<Movie> todayPopularMovies = mMovieService.queryTodayPopularMovies();
        List<Movie> lastWeekPopularMovies = mMovieService.queryLastWeekPopularMovies();
        List<Movie> lastMonthPopularMovies = mMovieService.queryLastMonthPopularMovies();
        modelMap.addAttribute("todayPopularMovies",todayPopularMovies);
        modelMap.addAttribute("lastWeekPopularMovies",lastWeekPopularMovies);
        modelMap.addAttribute("lastMonthPopularMovies",lastMonthPopularMovies);
    }

    @ModelAttribute
    public void queryRandomMovies(ModelMap modelMap){
        boolean isUseCache = true;
        List<Movie> randomMovies = mMovieService.queryRandomMovies(10,2,isUseCache);
        modelMap.addAttribute("randomMovies",randomMovies);
    }

    @ModelAttribute
    public void queryEditorRecommendMovies(ModelMap modelMap){
        List<Movie> editorRecommendMovies = mMovieService.queryEditorRecommendMovies(5,1);
        modelMap.addAttribute("editorRecommendMovies",editorRecommendMovies);
    }

    @ModelAttribute
    public void queryImageAndTextRecommendMovies(ModelMap modelMap){
        List<Movie> imageAndTextRecommendMovies = mMovieService.queryImageAndTextRecommendMovies(8,2);
        modelMap.addAttribute("imageAndTextRecommendMovies",imageAndTextRecommendMovies);
    }

}
