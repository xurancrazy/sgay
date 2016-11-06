package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class MainPageController extends BaseController{

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap) throws IOException {
		List<Movie> recommendMovies = mMovieService.queryRecommendMovies();
		modelMap.addAttribute("recommendMovies",recommendMovies);
		return "index";
	}

	@GetMapping(value = "/teachers")
	public String teachersPage(ModelMap modelMap){
		List<Teacher> teachers = mTeacherService.queryTeachers();
		modelMap.addAttribute("teachers",teachers);
		return "teachers";
	}
}
