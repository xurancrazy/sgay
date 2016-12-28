package com.sgay.giligili.service;

import com.sgay.giligili.BaseTest;
import org.junit.Test;

import java.util.List;

/**
 * Created by xurancrazy on 2016/12/3.
 */
public class CategoryServiceTest extends BaseTest {

    @Test
    public void queryCategoryByMovieName(){
        List<String> categorys = mCategoryService.queryCategoriesByMovieName("GVG-135");
        categorys.stream().forEach(System.out::println);
    }

    @Test
    public void queryMovieNameByCategory(){
        List<String> movies = mCategoryService.queryNameOfMoviesByCategory("乱交");
        movies.stream().forEach(System.out::println);
    }

    @Test
    public void test(){
        mRedisTemplate.opsForSet().members("test1").stream().forEach(System.out::println);
    }
}
