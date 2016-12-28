package com.sgay.giligili.dao;

import com.sgay.giligili.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectRecommendMovies(Integer interval);

    List<Movie> selectMoviesByTeacherName(String teacherName);

    Movie selectMovieByFanhao(String movieName);

    List<Movie> selectRandomMovies(Integer numOfOneQuery);

    List<Movie> selectMovieByLocateFunction(String locate);
}