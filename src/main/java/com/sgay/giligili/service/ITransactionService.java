package com.sgay.giligili.service;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.service.impl.BaseService;

import java.util.List;

/**
 * Created by xurancrazy on 2016/12/4.
 */
public interface ITransactionService extends IBaseService {

    List<Movie> queryMovieByMovieNamesWithCategory(List<String> movieNames, String category);
}
