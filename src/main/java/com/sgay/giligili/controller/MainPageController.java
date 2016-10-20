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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
//
//	@RequestMapping(value = "/error")
//	public ModelAndView errorHandler(HttpServletRequest request){
//		ModelAndView modelAndView = new ModelAndView();
//		if ((int)request.getAttribute("javax.servlet.error.status_code") == 404){
//			modelAndView.setViewName(DEFAULT_404_NOTFOUND_VIEW);
//		}
//		else{
//			modelAndView.setViewName(DEFAULT_INTERNAL_ERROR_VIEW);
//		}
//		return modelAndView;
//	}
}
