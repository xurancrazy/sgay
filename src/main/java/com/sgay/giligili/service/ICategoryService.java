package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movie;

import java.util.List;

/**
 * Created by xurancrazy on 2016/12/3.
 */
public interface ICategoryService extends IBaseService{

    List<String> queryMovieNameByCategory(String category);

    List<String> queryCategoryByMovieName(String movieName);

    List<String> queryCategorys();
}
