package com.sgay.giligili.service.impl;

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

    @Cacheable(value = "queryRecommendMovies", key = "'recommendMovies'")
    @Override
    public List<Movie> queryRecommendMovies() {
        return mMovieMapper.selectRecommendMovies();
    }

    @Cacheable(value = "queryRandomMovies", key = "'randomMovies'", condition = "#isUseCache == true")
    @Override
    @Transactional
    public List<Movie> queryRandomMovies(int sum, int numOfOneQuery, boolean isUseCache) {
        return queryRandomMoviesImpl(sum, numOfOneQuery);
    }

    @Cacheable(value = "queryEditorRecommendMovies", key = "'editorRecommendMovies'")
    @Override
    @Transactional
    public List<Movie> queryEditorRecommendMovies(int sum, int numOfOneQuery) {
        return queryRandomMoviesImpl(sum, numOfOneQuery);
    }

    @Cacheable(value = "queryImageAndTextRecommendMovies", key = "'imageAndTextRecommendMovies'")
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
//        res.addAll(mMovieMapper.selectRandomMovies(1));
//        res.addAll(mMovieMapper.selectRandomMovies(2));
//        res.addAll(mMovieMapper.selectRandomMovies(3));
        return res;
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
    @Transactional(propagation = Propagation.NESTED)
    public void updateMovieViewsNum(Movie movie) {
        mMovieMapper.updateByPrimaryKeySelective(movie);
        int a =3/0;
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
