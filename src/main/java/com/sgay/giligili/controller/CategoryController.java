package com.sgay.giligili.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgay.giligili.entity.Category;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by xurancrazy on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController {

    private static final String JSP_CATEGORIES = "categories";

    private static final String JSP_CATEGORY_DETAIL = "categoryDetail";

    @GetMapping
    public String categories(ModelMap modelMap) {
        SeoOptimization(modelMap, Constants.CATEGORY_PAGE_DESCRIPTION, Constants.CATEGORY_PAGE_KEYWORDS, Constants.CATEGORY_PAGE_TITLE);
        return JSP_CATEGORIES;
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Map<String, Object> ajaxCategories(@RequestParam int offset, @RequestParam int limit, @RequestParam String order) throws JsonProcessingException {
        List<String> allCategorys = mCategoryService.queryCategorys();
        int total = allCategorys.size();
        List<Category> res = new LinkedList<>();
        List<String> categorys = allCategorys.subList(offset, offset + limit > total ? total : offset + limit);
        int id = offset + 1;
        for (String categoryName : categorys) {
            List<String> movieNames = mCategoryService.queryNameOfMoviesByCategory(categoryName);
            Category category = new Category();
            category.setId(id);
            category.setName(categoryName);
            category.setMovieNum(movieNames.size());
            res.add(category);
            id += 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rows", res);
        map.put("total", total);
        return map;
    }

    @GetMapping(value = "/{categoryName}")
    public String categoryDetail(@PathVariable String categoryName, ModelMap modelMap) {
        String key = "category:" + categoryName;
        if (!mCategoryService.isRedisKeyExist(key)) {
            throw new PageNotFoundException("category:" + categoryName + " not Found!");
        }
        modelMap.addAttribute("category", categoryName);
        SeoOptimization(modelMap, Constants.CATEGORY_PAGE_DESCRIPTION, Constants.CATEGORY_PAGE_KEYWORDS, Constants.CATEGORY_PAGE_TITLE);
        return JSP_CATEGORY_DETAIL;
    }

    @PostMapping(value = "/{categoryName}", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Map<String, Object> ajaxCategoryDetail(@RequestParam String category, @RequestParam int offset,
                                          @RequestParam int limit, @RequestParam String order) throws JsonProcessingException {
        List<String> movieNames = mCategoryService.queryNameOfMoviesByCategory(category);
        int total = movieNames.size();
        List<String> selectedMovieNames = movieNames.subList(offset, offset + limit > total ? total : offset + limit);
        List<Movie> movies = mMovieService.queryMovieByMovieNames(selectedMovieNames);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", movies);
        map.put("total", total);
        return map;
    }
}
