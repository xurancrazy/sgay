package com.sgay.giligili.service.impl;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.service.ICategoryService;
import com.sgay.giligili.service.IMovieService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<String> queryMovieNameByCategory(String category) {
        List<String> res = new LinkedList<>();
        try {
            String key = "category:" + category;
            BoundSetOperations<String, Object> queryMovies = mRedisTemplate.boundSetOps(key);
            Set<Object> movieNamesInCategory = queryMovies.members();
            if (movieNamesInCategory.size() == 0) {
                throw new PageNotFoundException("category:" + category + " not exist");
            }
            for (Object movieName : movieNamesInCategory) {
                res.add((String) movieName);
            }
        } catch (PageNotFoundException e) {
            throw e;
        } catch (Exception e){
            return res;
        }
        return res;
    }

    @Override
    public List<String> queryCategoryByMovieName(String movieName) {
        String key = "movieCategory:" + movieName;
        BoundSetOperations<String, Object> queryCategorys = mRedisTemplate.boundSetOps(key);
        Set<Object> categorysInMovie = queryCategorys.members();
        List<String> res = new LinkedList<>();
        for (Object category : categorysInMovie) {
            res.add((String) category);
        }
        return res;
    }

    @Override
    public List<String> queryCategorys() {
        String key = "category";
        BoundSetOperations<String, Object> queryCategorys = mRedisTemplate.boundSetOps(key);
        Set<Object> categorys = queryCategorys.members();
        List<String> res = new LinkedList<>();
        for (Object category : categorys) {
            res.add((String) category);
        }
        return res;
    }
}
