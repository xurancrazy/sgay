package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MoviesMapper;
import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MoviesService extends BaseService implements IMoviesService {

    @Override
    public List<Movies> queryRecommendMovies() {
        return mMoviesMapper.selectRecommendMovies();
    }

    @Override
    public List<Movies> queryMoviesByTeacherName(String teacherName) {
        return mMoviesMapper.selectMoviesByTeacherName(teacherName);
    }

    @Override
    public Movies queryMovieByFanhao(String movieName) {
        return mMoviesMapper.selectMovieByFanhao(movieName);
    }

    @Override
    public List<Movies> queryMoviesByTeacherNameAndYear(String teacherName, String year) {
        String startTime = year + "-01-01";
        String endTime = year + "-12-31";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("teacherName",teacherName);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        return mMoviesMapper.selectMoviesByTeacherNameAndYear(paramMap);
    }

    @Override
    public void updateMovieViewsNum(Movies movie) {
        mMoviesMapper.updateByPrimaryKeySelective(movie);
    }
}
