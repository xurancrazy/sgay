package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movies;

import java.io.IOException;
import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
public interface IMoviesService {
    List<Movies> queryRecommendMovies();

    List<Movies> queryMoviesByTeacherName(String teacherName);

    Movies queryMovieByFanhao(String movieName);

    List<Movies> queryTodayPopularMovies() throws IOException;

    List<Movies> queryLastWeekPopularMovies() throws IOException;

    List<Movies> queryLastMonthPopularMovies() throws IOException;

    void updateMovieViewsNum(Movies movie);
}
