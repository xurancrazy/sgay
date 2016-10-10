package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MoviesMapper;
import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Service
public class MoviesService implements IMoviesService {
    @Autowired
    private MoviesMapper mMoviesMapper;

    @Override
    public List<Movies> queryRecommendMovies() {
        return mMoviesMapper.selectRecommendMovies();
    }

    @Override
    public List<Movies> queryTeacherMovies() {
        return null;
    }
}
