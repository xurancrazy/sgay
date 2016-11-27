package com.sgay.giligili.controller;

import com.google.common.base.Strings;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Controller
@RequestMapping(value = "/teachers")
public class TeachersController extends BaseController{

    @GetMapping(value = "/{teacherName}")
    public String teacherMovies(@PathVariable String teacherName, @RequestParam(value = "year", required = false) String year, ModelMap modelMap){
        List<Movie> movies;
        Teacher teacher = mTeacherService.queryTeacherByName(teacherName);
        Multimap<String,Movie> yearMapToMovies = LinkedListMultimap.create();
        movies = mMovieService.queryMoviesByTeacherName(teacherName);
        groupMoviesByYear(yearMapToMovies, movies);
        if(!Strings.isNullOrEmpty(year) && validateParams_Year(year)){
            movies = (List<Movie>)yearMapToMovies.get(year);
        }
        List<String> years = new LinkedList<>(yearMapToMovies.keySet());
        years.sort((final String o1, String o2) -> Integer.valueOf(o2)-Integer.valueOf(o1));
        years.add(0,"all");
        String teacherDescription = createTeacherDescription(teacherName, year, movies);
        modelMap.addAttribute("teacherDescription",teacherDescription);
        modelMap.addAttribute("years",years);
        modelMap.addAttribute("movies",movies);
        modelMap.addAttribute("teacher",teacher);
        String title = teacherName+"【作品　番号　种子　图片】 - 番号站";
        SeoOptimization(modelMap, teacherDescription, teacherName, title);
        return "teacherDetail";
    }

    private void groupMoviesByYear(Multimap<String,Movie> res, List<Movie> movies) {
        for (Movie movie : movies){
            String year = movie.getPublishtime().toString().split("-")[0];
            res.put(year,movie);
        }
    }

    private String createTeacherDescription(String teacherName, String year, List<Movie> movies){
        StringBuilder sb = new StringBuilder();
        sb.append(teacherName);
        if (!Strings.isNullOrEmpty(year)){
            sb.append(year);
        }
        sb.append("作品番号，最新作品包含：");
        int num = 0;
        int length = movies.size();
        for(Movie movie : movies){
            sb.append(movie.getFanhao());
            num++;
            if (num > 8 || num == length){
                break;
            }
            sb.append("、");
        }
        return sb.toString();
    }

    private boolean validateParams_Year(String year) {
        if(Integer.valueOf(year) < 2017 && Integer.valueOf(year) > 2005){
            return true;
        }
        return false;
    }

    @GetMapping(value = "/{teacherName}/{movieName}")
    public String movieDetail(@PathVariable String teacherName, @PathVariable String movieName, ModelMap modelMap){
        Movie movie = mMovieService.queryMovieByFanhao(movieName);
        if (!movie.getTeacher().equals(teacherName)){
            throw new PageNotFoundException("movieDetail-->"+"teacherName="+teacherName+",movieName="+movieName);
        }
        List<Movie> sameTeacherMovies = buildRandomMoviesByTeacherName(teacherName, movieName);
        List<Movie> sameTypeMovies = mMovieService.queryRandomMovies(4,4,false);
        modelMap.addAttribute("sameTypeMovies",sameTypeMovies);
        modelMap.addAttribute("sameTeacherMovies",sameTeacherMovies);
        modelMap.addAttribute("movie",movie);
        modelMap.addAttribute("teacherName",teacherName);
        StringBuilder keywords = new StringBuilder();
        keywords.append(teacherName).append(",").append(movieName);
        StringBuilder title = new StringBuilder();
        title.append("番号-").append(movieName).append("【").append(teacherName).append("】").append("作品封面及种子").append(" - 番号站");
        StringBuilder description = new StringBuilder();
        description.append("作品:").append(movieName).append(" ").append(movie.getTitle()).append(" 发行时间:").append(movie.getPublishtime());
        SeoOptimization(modelMap, description.toString(), keywords.toString(), title.toString());
        return "movieDetail";
    }

    private List<Movie> buildRandomMoviesByTeacherName(String teacherName, String movieName){
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
