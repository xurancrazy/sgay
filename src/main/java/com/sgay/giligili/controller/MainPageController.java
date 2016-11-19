package com.sgay.giligili.controller;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Controller
public class MainPageController extends BaseController{

	@GetMapping(value = "/")
	public String homePage(ModelMap modelMap) throws IOException {
		List<Movie> recommendMovies = mMovieService.queryRecommendMovies();
		modelMap.addAttribute("recommendMovies",recommendMovies);
		return "index";
	}

	@GetMapping(value = "/teachers")
	public String teachersPage(ModelMap modelMap) throws PinyinException {
		List<Teacher> teachers = mTeacherService.queryTeachers();
		modelMap.addAttribute("teachers",teachers);
		buildShortPinyin(teachers,modelMap);
		return "teachers";
	}

	private void buildShortPinyin(List<Teacher> teachers, ModelMap modelMap) throws PinyinException {
		Multimap<Character,Teacher> firstPinyinToTeachers = LinkedListMultimap.create();
		for (Teacher teacher : teachers){
			Character firstPinyin = Character.toUpperCase(PinyinHelper.getShortPinyin(teacher.getName()).charAt(0));
			firstPinyinToTeachers.put(firstPinyin,teacher);
		}
		Set<Character> firstPinyinSets = firstPinyinToTeachers.keySet();
		List<Character> pinyinTable = new LinkedList<>(firstPinyinSets);
		pinyinTable.sort((final Character o1, Character o2) -> o1 - o2);
		for (Character c : pinyinTable){
			modelMap.addAttribute("list" + c.toString(),(List<Teacher>)firstPinyinToTeachers.get(c));
		}
		modelMap.addAttribute("pinyinTable",pinyinTable);
	}
}
