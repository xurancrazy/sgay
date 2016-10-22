package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class MainPageController extends BaseController{

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap){
		List<Movies> recommendMovies = mMoviesService.queryRecommendMovies();
		modelMap.addAttribute("recommendMovies",recommendMovies);
		return "index";
	}

	@GetMapping(value = "/teachers")
	public String teachersPage(ModelMap modelMap){
		List<Teachers> teachers = mTeacherService.queryTeachers();
		modelMap.addAttribute("teachers",teachers);
		return "teachers";
	}
}
