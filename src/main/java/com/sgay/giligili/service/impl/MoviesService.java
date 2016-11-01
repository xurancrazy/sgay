package com.sgay.giligili.service.impl;

import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.service.IMoviesService;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MoviesService extends BaseService implements IMoviesService {

    private SimpleDateFormat mSimpleDateFormat = Utils.createSimpleDateFormat(Constants.sfPattern);

    private static final String mLastWeek = "lastWeek";

    private static final String mLastMonth = "lastMonth";

    @Cacheable(value = "queryRecommendMovies", key = "'recommendMovies'")
    @Override
    public List<Movies> queryRecommendMovies() {
        return mMoviesMapper.selectRecommendMovies();
    }

    @Cacheable(value = "queryMoviesByTeacherName", key = "'teacherName:'+#teacherName")
    @Override
    public List<Movies> queryMoviesByTeacherName(String teacherName) {
        return mMoviesMapper.selectMoviesByTeacherName(teacherName);
    }

    @Cacheable(value = "queryMovieByFanhao", key = "'movieName:'+#movieName")
    @Override
    public Movies queryMovieByFanhao(String movieName) {
        return mMoviesMapper.selectMovieByFanhao(movieName);
    }

    @Override
    public List<Movies> queryTodayPopularMovies() throws IOException {
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + mSimpleDateFormat.format(new Date()));
    }

    @Override
    public List<Movies> queryLastWeekPopularMovies() throws IOException{
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + mLastWeek);
    }

    @Override
    public List<Movies> queryLastMonthPopularMovies() throws IOException{
        return queryPopularMoviesImpl(Constants.MOVIE_PREFIX + ":" + mLastMonth);
    }

    public List<Movies> queryPopularMoviesImpl(String key) throws IOException{
        Set<Object> set = mRedisTemplate.opsForZSet().reverseRangeByScore(key, 0, 9);
        List<Movies> res = new LinkedList<>();
        for (Object fanhao : set){
            Movies movie = (Movies) mRedisTemplate.opsForValue().get("movieName:" + fanhao);
            res.add(movie);
        }
        return res;
    }

    @CacheEvict(value = "queryqueryMovieByFanhao", key = "'movieName:'+#movie.fanhao")
    @Override
    public void updateMovieViewsNum(Movies movie) {
        mMoviesMapper.updateByPrimaryKeySelective(movie);
    }
}
