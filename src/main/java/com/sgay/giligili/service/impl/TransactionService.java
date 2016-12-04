package com.sgay.giligili.service.impl;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.service.IMovieService;
import com.sgay.giligili.service.ITransactionService;
import com.sun.deploy.appcontext.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xurancrazy on 2016/12/4.
 */

@Service
public class TransactionService extends BaseService implements ITransactionService {

    @Autowired
    private IMovieService mMovieService;

    @Override
    public List<Movie> queryMovieByMovieNamesWithCategory(List<String> movieNames, String category) {
        List<Movie> movies = new LinkedList<>();
        if (movieNames != null){
            for (String movieName : movieNames){
                Movie movie = mMovieService.queryMovieByFanhao(movieName);
                if (movie != null){
                    movies.add(movie);
                }
            }
        }
        return movies;
    }
}
