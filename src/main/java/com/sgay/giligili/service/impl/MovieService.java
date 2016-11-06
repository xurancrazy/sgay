package com.sgay.giligili.service.impl;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.IMovieService;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MovieService extends BaseService implements IMovieService {

    private SimpleDateFormat mSimpleDateFormat = Utils.createSimpleDateFormat(Constants.SF_PATTERN);

    @Cacheable(value = "queryRecommendMovies", key = "'recommendMovies'")
    @Override
    public List<Movie> queryRecommendMovies() {
        return mMovieMapper.selectRecommendMovies();
    }

    @Cacheable(value = "queryMoviesByTeacherName", key = "'teacherName:'+#teacherName")
    @Override
    public List<Movie> queryMoviesByTeacherName(String teacherName) {
        return mMovieMapper.selectMoviesByTeacherName(teacherName);
    }

    @Cacheable(value = "queryMovieByFanhao", key = "'movieName:'+#movieName")
    @Override
    public Movie queryMovieByFanhao(String movieName) {
        Movie movie = mMovieMapper.selectMovieByFanhao(movieName);
        if (movie == null){
            throw new PageNotFoundException("queryMovieByFanhao-->"+"movieName="+movieName);
        }
        return movie;
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
        for (Object fanhao : set){
            Movie movie = (Movie) mRedisTemplate.opsForValue().get("movieName:" + fanhao);
            res.add(movie);
        }
        return res;
    }

    @CacheEvict(value = "queryqueryMovieByFanhao", key = "'movieName:'+#movie.fanhao")
    @Override
    public void updateMovieViewsNum(Movie movie) {
        mMovieMapper.updateByPrimaryKeySelective(movie);
    }
}
