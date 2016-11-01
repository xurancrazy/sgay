package com.sgay.giligili.controller;

import com.sgay.giligili.entity.Movies;
import com.sgay.giligili.entity.Teachers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class MainPageController extends BaseController{

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap) throws IOException {
		List<Movies> recommendMovies = mMoviesService.queryRecommendMovies();
		List<Movies> todayPopularMovies = mMoviesService.queryTodayPopularMovies();
		List<Movies> lastWeekPopularMovies = mMoviesService.queryLastWeekPopularMovies();
		List<Movies> lastMonthPopularMovies = mMoviesService.queryLastMonthPopularMovies();
		modelMap.addAttribute("todayPopularMovies",todayPopularMovies);
		modelMap.addAttribute("lastWeekPopularMovies",lastWeekPopularMovies);
		modelMap.addAttribute("lastMonthPopularMovies",lastMonthPopularMovies);
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
