package com.sgay.giligili.controller;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Controller
public class MainPageController extends BaseController{

	private final static String JSP_INDEX = "index";

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap){
		List<Movie> recommendMovies = mMovieService.queryRecommendMovies();
		modelMap.addAttribute("recommendMovies",recommendMovies);
		SeoOptimization(modelMap, Constants.HOME_PAGE_DESCRIPTION, Constants.HOME_PAGE_KEYWORDS, Constants.HOME_PAGE_TITLE);
		return JSP_INDEX;
	}
}
