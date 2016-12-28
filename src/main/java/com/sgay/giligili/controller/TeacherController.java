package com.sgay.giligili.controller;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Strings;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.exception.PageNotFoundException;
import com.sgay.giligili.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
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
public class TeacherController extends BaseController{

    private static final String JSP_TEACHERS = "teachers";

    private static final String JSP_TEACHER_DETAIL= "teacherDetail";
    @GetMapping
    public String teachersPage(ModelMap modelMap) throws PinyinException {
        List<Teacher> teachers = mTeacherService.queryTeachers();
        List<Character> pinyinTable = buildShortPinyin(teachers, modelMap);
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("pinyinTable", pinyinTable);
        SeoOptimization(modelMap, Constants.TEACHERS_PAGE_DESCRIPTION, Constants.TEACHERS_PAGE_KEYWORDS, Constants.TEACHERS_PAGE_TITLE);
        return JSP_TEACHERS;
    }

    private List<Character> buildShortPinyin(List<Teacher> teachers, ModelMap modelMap) throws PinyinException {
        Multimap<Character,Teacher> firstPinyinMapToTeachers = LinkedListMultimap.create();
        if (!CollectionUtils.isEmpty(teachers)){
            for (Teacher teacher : teachers){
                Character firstPinyin = Character.toUpperCase(PinyinHelper.getShortPinyin(teacher.getName()).charAt(0));
                firstPinyinMapToTeachers.put(firstPinyin,teacher);
            }
        }
        Set<Character> firstPinyinSets = firstPinyinMapToTeachers.keySet();
        List<Character> pinyinTable = new LinkedList<>(firstPinyinSets);
        pinyinTable.sort((final Character o1, Character o2) -> o1 - o2);
        for (Character c : pinyinTable){
            modelMap.addAttribute("list" + c.toString(),firstPinyinMapToTeachers.get(c));
        }
        return pinyinTable;
    }

    @GetMapping(value = "/{teacherName}")
    public String teacherDetail(@PathVariable String teacherName, @RequestParam(value = "year", required = false) String year, ModelMap modelMap){
        List<Movie> movies = mMovieService.queryMoviesByTeacherName(teacherName);
        Teacher teacher = mTeacherService.queryTeacherByName(teacherName);
        Multimap<String,Movie> yearMapToMovies = groupMoviesByYear(movies);
        if(!Strings.isNullOrEmpty(year)){
            movies = (List<Movie>)yearMapToMovies.get(year);
        }
        List<String> years = new LinkedList<>(yearMapToMovies.keySet());
        years.sort((final String o1, String o2) -> Integer.valueOf(o2) - Integer.valueOf(o1));
        years.add(0,"all");
        String teacherDescription = createTeacherDescription(teacherName, year, movies);
        modelMap.addAttribute("teacherDescription",teacherDescription);
        modelMap.addAttribute("years",years);
        modelMap.addAttribute("movies",movies);
        modelMap.addAttribute("teacher",teacher);
        String title = teacherName+"【作品　番号　种子　图片】 - 番号站";
        SeoOptimization(modelMap, teacherDescription, teacherName, title);
        return JSP_TEACHER_DETAIL;
    }

    private Multimap<String,Movie> groupMoviesByYear(List<Movie> movies) {
        Multimap<String,Movie> yearMapToMovies = LinkedListMultimap.create();
        if (!CollectionUtils.isEmpty(movies)){
            for (Movie movie : movies){
                String year = movie.getPublishtime().toString().split("-")[0];
                yearMapToMovies.put(year,movie);
            }
        }
        return yearMapToMovies;
    }

    private String createTeacherDescription(String teacherName, String year, List<Movie> movies){
        StringBuilder sb = new StringBuilder();
        sb.append(teacherName);
        if (!Strings.isNullOrEmpty(year)){
            sb.append(year);
        }
        sb.append("作品番号，最新作品包含：");
        int num = 0;
        if (!CollectionUtils.isEmpty(movies)){
            int length = movies.size();
            for(Movie movie : movies){
                sb.append(movie.getFanhao());
                num++;
                if (num > 8 || num == length){
                    break;
                }
                sb.append("、");
            }
        }
        return sb.toString();
    }
}
