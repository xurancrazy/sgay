package com.sgay.giligili.dao;

import com.sgay.giligili.entity.Movies;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MoviesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Movies record);

    int insertSelective(Movies record);

    Movies selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Movies record);

    int updateByPrimaryKey(Movies record);

    List<Movies> selectRecommendMovies();

    List<Movies> selectTeacherMovies(String teacher);
}