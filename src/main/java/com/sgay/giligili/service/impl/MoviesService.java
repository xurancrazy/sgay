package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MoviesMapper;
import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MoviesService extends BaseService implements IMoviesService {


    @Cacheable(value = "queryRecommendMovies",key = "'recommendMovies'")
    @Override
    public List<Movies> queryRecommendMovies() {
        return mMoviesMapper.selectRecommendMovies();
    }

    @Cacheable(value = "queryMoviesByTeacherName",key = "'teacherName:'+#teacherName")
    @Override
    public List<Movies> queryMoviesByTeacherName(String teacherName) {
        return mMoviesMapper.selectMoviesByTeacherName(teacherName);
    }

    @Cacheable(value = "queryMovieByFanhao",key = "'movieName:'+#movieName")
    @Override
    public Movies queryMovieByFanhao(String movieName) {
        return mMoviesMapper.selectMovieByFanhao(movieName);
    }

    @CacheEvict(value = "queryqueryMovieByFanhao", key = "'movieName:'+#movie.fanhao")
    @Override
    public void updateMovieViewsNum(Movies movie) {
        mMoviesMapper.updateByPrimaryKeySelective(movie);
    }
}
