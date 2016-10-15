package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;
import com.sgay.giligili.service.IMoviesService;
import com.sgay.giligili.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainPageController {

	@Autowired
	private IMoviesService mRecommendService;
	@Autowired
	private ITeacherService mTeacherService;

	private static Logger logger= LoggerFactory.getLogger(MainPageController.class);

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap){
		List<Movies> recommendMovies = mRecommendService.queryRecommendMovies();
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
