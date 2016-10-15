package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movies;

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
public interface IMoviesService {
    List<Movies> queryRecommendMovies();

    List<Movies> queryMoviesByTeacherName(String teacher);
}
