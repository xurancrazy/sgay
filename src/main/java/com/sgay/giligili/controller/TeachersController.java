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

    @GetMapping(value = "/{teacher}")
    public String teacherMovies(@PathVariable String teacher, ModelMap modelMap){
        List<Movies> movies = mMoviesService.queryMoviesByTeacherName(teacher);
        modelMap.addAttribute("movies",movies);
        return "teacherDetail";
    }
}
