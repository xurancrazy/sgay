package com.sgay.giligili.controller;

import com.google.common.base.Strings;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.exception.PageNotFoundException;
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
        modelMap.addAttribute("years",years);
        modelMap.addAttribute("movies",movies);
        modelMap.addAttribute("teacher",teacher);
        return "teacherDetail";
    }

    private void groupMoviesByYear(Multimap<String,Movie> res, List<Movie> movies) {
        for (Movie movie : movies){
            String year = movie.getPublishtime().toString().split("-")[0];
            res.put(year,movie);
        }
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
        modelMap.addAttribute("movie",movie);
        modelMap.addAttribute("teacherName",teacherName);
        return "movieDetail";
    }

}
