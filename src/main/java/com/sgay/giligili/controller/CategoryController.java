package com.sgay.giligili.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sgay.giligili.entity.Category;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by xurancrazy on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {

    @GetMapping
    public String category(ModelMap modelMap) {
        SeoOptimization(modelMap, Constants.CATEGORY_PAGE_DESCRIPTION, Constants.CATEGORY_PAGE_KEYWORDS, Constants.CATEGORY_PAGE_TITLE);
        return "category";
    }

    @PostMapping(value = "/pages", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getCategoryPagination(@RequestParam int offset, @RequestParam int limit, @RequestParam String order) throws JsonProcessingException {
        List<String> allCategorys = mCategoryService.queryCategorys();
        int total = allCategorys.size();
        List<Category> res = new LinkedList<>();
        List<String> categorys = allCategorys.subList(offset, offset + limit > total ? total : offset + limit);
        int id = offset + 1;
        for (String categoryName : categorys) {
            List<String> movieNames = mCategoryService.queryMovieNameByCategory(categoryName);
            if (movieNames != null && !movieNames.isEmpty()) {
                Category category = new Category();
                category.setId(id);
                category.setName(categoryName);
                category.setMovieNum(movieNames.size());
                res.add(category);
                id += 1;
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("rows", res);
        map.put("total", total);
        return objectMapper.writeValueAsString(map);
    }

    @GetMapping(value = "/{categoryName}")
    public String categoryDetail(@PathVariable String categoryName, ModelMap modelMap) {
        String key = "category:" + categoryName;
        if (!mCategoryService.isRedisKeyExist(key)){
            throw new PageNotFoundException("category:" + categoryName + " not Found!");
        }
        modelMap.addAttribute("category",categoryName);
        SeoOptimization(modelMap, Constants.CATEGORY_PAGE_DESCRIPTION, Constants.CATEGORY_PAGE_KEYWORDS, Constants.CATEGORY_PAGE_TITLE);
        return "categoryDetail";
    }

    @PostMapping(value = "/detail", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getCategoryDetail(@RequestParam String category, @RequestParam int offset,
                             @RequestParam int limit, @RequestParam String order) throws JsonProcessingException {
        List<String> movieNames = mCategoryService.queryMovieNameByCategory(category);
        int total = movieNames.size();
        List<String> selectedMovieNames = movieNames.subList(offset, offset + limit > total ? total : offset + limit);
        List<Movie> movies = mTransactionService.queryMovieByMovieNamesWithCategory(selectedMovieNames, category);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",movies);
        map.put("total",total);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }
}
