package com.sgay.giligili.service;

import com.sgay.giligili.BaseTest;
import com.sgay.giligili.entity.Movie;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xurancrazy on 2016/12/4.
 */
public class TransactionServiceTest extends BaseTest {

    @Test
    public void queryMovieByMovieNamesWithCategory(){
        List<Movie> movies = mTransactionService.queryMovieByMovieNamesWithCategory(Arrays.asList("KPD-001","JUFD-463"),"中出");
        System.out.println(movies.size());
    }
}
