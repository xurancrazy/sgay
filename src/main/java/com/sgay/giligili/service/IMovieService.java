package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */
public interface IMovieService {
    List<Movie> queryRecommendMovies();

    List<Movie> queryRandomMovies(int sum, int numOfOneQuery, boolean isUseCache);

    List<Movie> queryEditorRecommendMovies(int sum, int numOfOneQuery);

    List<Movie> queryImageAndTextRecommendMovies(int sum, int numOfOneQuery);

    List<Movie> queryMoviesByTeacherName(String teacherName);

    Movie queryMovieByFanhao(String movieName);

    List<Movie> queryTodayPopularMovies() throws IOException;

    List<Movie> queryLastWeekPopularMovies() throws IOException;

    List<Movie> queryLastMonthPopularMovies() throws IOException;

    void updateMovieViewsNum(Movie movie);

}
