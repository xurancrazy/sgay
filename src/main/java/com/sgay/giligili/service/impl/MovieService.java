package com.sgay.giligili.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.IMovieService;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MovieService extends BaseService implements IMovieService {

    private SimpleDateFormat mSimpleDateFormat = Utils.createSimpleDateFormat(Constants.SF_PATTERN);

    @Cacheable(value = "MovieService", key = "'recommendMovies'")
    @Override
    @Transactional
    public List<Movie> queryRecommendMovies() {
        int recommendMovieNum = 12;
        int defaultDayInterval = 0;
        List<Movie> res = new LinkedList<>();
        int totalNum = 0;
        int round = 0;
        while(totalNum < recommendMovieNum){
            List<Movie> movies = mMovieMapper.selectRecommendMovies(defaultDayInterval + round);
            if (CollectionUtils.isEmpty(movies)){
                round += 1;
                continue;
            }
            int num = movies.size();
            if (num + totalNum >= recommendMovieNum){
                List<Movie> subMovies = movies.subList(0, recommendMovieNum - totalNum);
                for (Movie movie : subMovies){
                    res.add(movie);
                }
                break;
            }
            res.addAll(movies);
            totalNum += num;
            round += 1;
        }
        return res;
    }

    @Cacheable(value = "MovieService", key = "'randomMovies'", condition = "#isUseCache == true")
    @Override
    @Transactional
    public List<Movie> queryRandomMovies(int sum, int numOfOneQuery, boolean isUseCache) {
        return queryRandomMoviesImpl(sum, numOfOneQuery);
    }

    @Cacheable(value = "MovieService", key = "'editorRecommendMovies'")
    @Override
    @Transactional
    public List<Movie> queryEditorRecommendMovies(int sum, int numOfOneQuery) {
        return queryRandomMoviesImpl(sum, numOfOneQuery);
    }

    @Cacheable(value = "MovieService", key = "'imageAndTextRecommendMovies'")
    @Override
    @Transactional
    public List<Movie> queryImageAndTextRecommendMovies(int sum, int numOfOneQuery) {
        return queryRandomMoviesImpl(sum, numOfOneQuery);
    }


    private List<Movie> queryRandomMoviesImpl(int sum, int numOfOneQuery){
        int times = sum / numOfOneQuery;
        List<Movie> res = new LinkedList<>();
        for (int i = 0; i < times; i++){
            res.addAll(mMovieMapper.selectRandomMovies(numOfOneQuery));
        }
        return res;
    }

    @Cacheable(value = "MovieService", key = "'teacherMovies:'+#teacherName")
    @Override
    public List<Movie> queryMoviesByTeacherName(String teacherName) {
        if (Strings.isNullOrEmpty(teacherName)){
            throw new PageNotFoundException("Exception: queryMoviesByTeacherName --> " + "teacherName = " + teacherName);
        }
        List<Movie> movies = mMovieMapper.selectMoviesByTeacherName(teacherName);
        if (CollectionUtils.isEmpty(movies)){
            throw new PageNotFoundException("Exception: queryMoviesByTeacherName --> " + "teacherName = " + teacherName);
        }
        return movies;
    }

    @Cacheable(value = "MovieService", key = "'movieDetail:'+#movieName")
    @Override
    public Movie queryMovieByFanhao(String movieName) {
        if (Strings.isNullOrEmpty(movieName)){
            throw new PageNotFoundException("Exception: queryMovieByFanhao --> " + "movieName = " + movieName);
        }
        Movie movie = mMovieMapper.selectMovieByFanhao(movieName);
        if (movie == null){
            throw new PageNotFoundException("Exception: queryMovieByFanhao --> " + "movieName = " + movieName);
        }
        return movie;
    }


    @Override
    public List<Movie> queryMovieByMovieNames(List<String> movieNames) {
        List<Movie> movies = new LinkedList<>();
        if (!CollectionUtils.isEmpty(movieNames)){
            for (String movieName : movieNames){
                Movie movie = queryMovieByFanhao(movieName);
                movies.add(movie);
            }
        }
        return movies;
    }

    @Override
    public List<Movie> queryTodayPopularMovies() throws IOException {
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + mSimpleDateFormat.format(new Date()));
    }

    @Override
    public List<Movie> queryLastWeekPopularMovies() throws IOException{
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + Constants.LAST_WEEK);
    }

    @Override
    public List<Movie> queryLastMonthPopularMovies() throws IOException{
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + Constants.LAST_MONTH);
    }

    public List<Movie> queryPopularMoviesImpl(String key) throws IOException{
        Set<Object> set = mRedisTemplate.opsForZSet().reverseRange(key, 0, 9);
        List<Movie> res = new LinkedList<>();
        if (!CollectionUtils.isEmpty(set)){
            for (Object fanhao : set){
                Movie movie = (Movie) mRedisTemplate.opsForValue().get("movieDetail:" + fanhao);
                res.add(movie);
            }
        }
        return res;
    }

    @CacheEvict(value = "MovieService", key = "'movieDetail:'+#movie.fanhao")
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void updateMovieViewsNum(Movie movie) {
        mMovieMapper.updateByPrimaryKeySelective(movie);
    }

    @Cacheable(value = "MovieService", key = "'searchPrefix:'+#locate")
    @Override
    public List<Movie> queryMoviesByLocateFunction(String locate) {
        return mMovieMapper.selectMovieByLocateFunction(locate);
    }

    @Override
    @Transactional
    public void testTransactional(){
        Movie movie = mMovieMapper.selectByPrimaryKey((long)3);
        movie.setViewsnum((long)100);
        mMovieMapper.updateByPrimaryKey(movie);
        int a = 3/0;
    }
}
