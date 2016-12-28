package com.sgay.giligili.service.impl;

import com.google.common.base.Strings;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.ICategoryService;
import com.sgay.giligili.service.IMovieService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xurancrazy on 2016/12/3.
 */
@Service
public class CategoryService extends BaseService implements ICategoryService {

    @Override
    public List<String> queryNameOfMoviesByCategory(String category) {
        if (Strings.isNullOrEmpty(category)){
            throw new PageNotFoundException("Exception: queryNameOfMoviesByCategory -- > category:" + category + " not exist");
        }
        List<String> res = new LinkedList<>();
        String key = "category:" + category;
        BoundSetOperations<String, Object> queryNameOfMovies = mRedisTemplate.boundSetOps(key);
        Set<Object> nameOfMoviesWithCategory = queryNameOfMovies.members();
        if (!CollectionUtils.isEmpty(nameOfMoviesWithCategory)) {
            for (Object movieName : nameOfMoviesWithCategory) {
                res.add((String) movieName);
            }
        }
        return res;
    }

    @Override
    public List<String> queryCategoriesByMovieName(String movieName) {
        String key = "movieCategory:" + movieName;
        BoundSetOperations<String, Object> queryCategories = mRedisTemplate.boundSetOps(key);
        Set<Object> categories = queryCategories.members();
        List<String> res = new LinkedList<>();
        if (!CollectionUtils.isEmpty(categories)) {
            for (Object category : categories) {
                res.add((String) category);
            }
        }
        return res;
    }

    @Override
    public List<String> queryCategorys() {
        String key = "category";
        BoundSetOperations<String, Object> queryCategorys = mRedisTemplate.boundSetOps(key);
        Set<Object> categorys = queryCategorys.members();
        List<String> res = new LinkedList<>();
        if (!CollectionUtils.isEmpty(categorys)){
            for (Object category : categorys) {
                res.add((String) category);
            }
        }
        return res;
    }
}
