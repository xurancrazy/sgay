package com.sgay.giligili.controller;

import com.google.common.base.Strings;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.dao.TeachersMapper;
import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;
import com.sgay.giligili.service.IMoviesService;
import com.sgay.giligili.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Movies> movies = null;
        Teachers teacher = mTeacherService.queryTeacherByName(teacherName);
        Multimap<String,Movies> yearMapToMovies = LinkedListMultimap.create();
        if (teacher != null){
            teacher.setViewsnum(teacher.getViewsnum()+1);
            mTeacherService.updateTeacherViewsNum(teacher);
            movies = mMoviesService.queryMoviesByTeacherName(teacherName);
            groupMoviesByYear(yearMapToMovies, movies);
            if(!Strings.isNullOrEmpty(year) && validateParams_Year(year)){
                movies = (List<Movies>)yearMapToMovies.get(year);
            }
        }else{
            return DEFAULT_404_NOTFOUND_VIEW;
        }
        List<String> years = new LinkedList<>(yearMapToMovies.keySet());
        years.sort((final String o1, String o2) -> Integer.valueOf(o2)-Integer.valueOf(o1));
        years.add(0,"all");
        modelMap.addAttribute("years",years);
        modelMap.addAttribute("movies",movies);
        modelMap.addAttribute("teacher",teacher);
        return "teacherDetail";
    }

    private void groupMoviesByYear(Multimap<String,Movies> res, List<Movies> movies) {
        for (Movies movie : movies){
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
    public String movieDetail(@PathVariable String teacherName, @PathVariable String movieName,ModelMap modelMap){
        Movies movie = mMoviesService.queryMovieByFanhao(movieName);
        if (movie == null || !movie.getTeacher().equals(teacherName)){
            return DEFAULT_404_NOTFOUND_VIEW;
        }
        movie.setViewsnum(movie.getViewsnum()+1);
        mMoviesService.updateMovieViewsNum(movie);
        Teachers teacher = mTeacherService.queryTeacherByName(teacherName);
        teacher.setViewsnum(teacher.getViewsnum()+1);
        mTeacherService.updateTeacherViewsNum(teacher);
        modelMap.addAttribute("movie",movie);
        modelMap.addAttribute("teacherName",teacherName);
        return "movieDetail";
    }

}
