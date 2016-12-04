package com.sgay.giligili.controller;

import com.google.common.collect.Lists;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xurancrazy on 2016/12/3.
 */
@Controller
@RequestMapping(value = "/movies")
public class MovieController extends BaseController{

    @GetMapping(value = "/{movieName}")
    public String movieDetail(@PathVariable String movieName, ModelMap modelMap){
        Movie movie = mMovieService.queryMovieByFanhao(movieName);
        String teacherName = movie.getTeacher();
        List<Movie> sameTeacherMovies = createRandomMoviesByTeacherName(teacherName, movieName);
        List<String> movieCategorys = mCategoryService.queryCategoryByMovieName(movieName);
        List<Movie> sameTypeMovies;
        if (movieCategorys == null || movieCategorys.isEmpty()){
            sameTypeMovies = mMovieService.queryRandomMovies(4,4,false);
        }
        sameTypeMovies = createSameTypeMoviesByCategory(movieCategorys, movieName);
        modelMap.addAttribute("sameTypeMovies",sameTypeMovies);
        modelMap.addAttribute("sameTeacherMovies",sameTeacherMovies);
        modelMap.addAttribute("movie",movie);
        modelMap.addAttribute("teacherName",teacherName);
        modelMap.addAttribute("movieCategorys",movieCategorys);

        StringBuilder keywords = new StringBuilder();
        keywords.append(teacherName).append(",").append(movieName);
        StringBuilder title = new StringBuilder();
        title.append("番号-").append(movieName).append("【").append(teacherName).append("】").append("作品封面及种子").append(" - 番号站");
        StringBuilder description = new StringBuilder();
        description.append("作品:").append(movieName).append(" ").append(movie.getTitle()).append(" 发行时间:").append(movie.getPublishtime());
        SeoOptimization(modelMap, description.toString(), keywords.toString(), title.toString());
        return "movieDetail";
    }

    private List<Movie> createSameTypeMoviesByCategory(List<String> movieCategorys, String movieName) {
        List<Movie> res = new LinkedList<>();
        int num = 0;
        int total = 4;
        if (!CollectionUtils.isEmpty(movieCategorys)){
            for (String category : movieCategorys){
                List<String> tmp = mCategoryService.queryMovieNameByCategory(category);
                List<String> movieNames = null;
                if (!CollectionUtils.isEmpty(tmp)){
                    movieNames = tmp.stream().filter(name -> !name.equals(movieName)).collect(Collectors.toList());
                }
                int size = 0;
                if (!CollectionUtils.isEmpty(movieNames)){
                    size = movieNames.size();
                }else {
                    continue;
                }
                if (num + size < total){
                    num += size;
                    res.addAll(mTransactionService.queryMovieByMovieNamesWithCategory(movieNames,category));
                }else {
                    res.addAll(mTransactionService.queryMovieByMovieNamesWithCategory(movieNames.subList(0, total - num),category));
                    break;
                }
            }
        }
        return res.subList(0, total);
    }

    private List<Movie> createRandomMoviesByTeacherName(String teacherName, String movieName){
        List<Movie> movies = mMovieService.queryMoviesByTeacherName(teacherName);
        List<Movie> res = new ArrayList<>();
        int len = movies.size();
        int numOfMovies = len < 4 ? len : 4;
        int curNum = 0;
        int cursor = 0;
        while(cursor<len && curNum<numOfMovies){
            Movie tmp = movies.get(cursor);
            if (!movieName.equals(tmp.getFanhao())){
                res.add(tmp);
                curNum++;
            }
            cursor++;
        }
        return res;
    }
}
