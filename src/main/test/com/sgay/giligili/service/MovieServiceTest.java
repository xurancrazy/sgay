package com.sgay.giligili.service;

import com.sgay.giligili.BaseTest;
import com.sgay.giligili.entity.Movie;
import org.junit.Test;

import java.util.List;

/**
 * Created by xurancrazy on 2016/11/27.
 */

public class MovieServiceTest extends BaseTest {

    @Test
    public void queryRecommendMovies(){
        List<Movie> movies = mMovieService.queryRecommendMovies();
        System.out.println(movies.size());
    }

    @Test
    public void queryRandomMovies(){
        List<Movie> movies = mMovieService.queryRandomMovies(5,1,false);
        for (Movie movie:movies){
            System.out.println(movie.getFanhao());
        }
    }

    @Test
    public void testTransactional(){
        mMovieService.testTransactional();
    }
}
