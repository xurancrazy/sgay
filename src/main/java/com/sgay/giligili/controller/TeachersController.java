package com.sgay.giligili.controller;

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

import java.util.List;

/**
 * Created by xurancrazy on 2016/10/10.
 */

@Controller
@RequestMapping(value = "/teachers")
public class TeachersController {

    @Autowired
    private ITeacherService mTeacherService;
    @Autowired
    private IMoviesService mMoviesService;

    @GetMapping(value = "/{teacherName}")
    public String teacherMovies(@PathVariable String teacherName, ModelMap modelMap){
        List<Movies> movies = mMoviesService.queryMoviesByTeacherName(teacherName);
        Teachers teacher = mTeacherService.queryTeacherByName(teacherName);
        modelMap.addAttribute("movies",movies);
        modelMap.addAttribute("teacher",teacher);
        return "teacherDetail";
    }

    @GetMapping(value = "/{teacherName}/{movieName}")
    public String movieDetail(@PathVariable String teacherName, @PathVariable String movieName,ModelMap modelMap){
        Movies movie = mMoviesService.queryMovieByFanhao(movieName);
        modelMap.addAttribute("movie",movie);
        modelMap.addAttribute("teacherName",teacherName);
        return "movieDetail";
    }

}
