package com.sgay.giligili.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.sgay.giligili.entity.Movie;
import com.sgay.giligili.exception.PageNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xurancrazy on 2016/12/11.
 */

@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController{

    private static final String JSP_SEARCH_DETAIL = "searchDetail";

    @GetMapping
    public String search(@RequestParam String keyword, ModelMap modelMap){
        modelMap.addAttribute("keyword",keyword);
        return JSP_SEARCH_DETAIL;
    }

    @PostMapping(value = "/detail", produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String,Object> ajaxSearchDetail(@RequestParam String keyword, @RequestParam int offset,
                                       @RequestParam int limit, @RequestParam String order) throws JsonProcessingException {
        String[] keywords = keywordNLP(keyword);
        String fanhaoPrefix = keywords[0];
        String fanhaoDigit = keywords[1];
        List<Movie> movies = mMovieService.queryMoviesByLocateFunction(fanhaoPrefix);
        List<Movie> res = movies.stream().filter(movie -> movie.getFanhao().contains(fanhaoDigit)).collect(Collectors.toList());
        int total = res.size();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",res);
        map.put("total",total);
        return map;
    }

    private String[] keywordNLP(String keyword) {
        String[] res = new String[2];
        if (Strings.isNullOrEmpty(keyword)) {
            return res;
        }
        keyword = keyword.trim();
        String cur = "";
        String prefix = "";
        String digit = "";
        int length = keyword.length();
        int index = 0;
        while(index < length){
            char c = keyword.charAt(index);
            if (Character.isLetter(c)){
                cur += c;
            }else if(!Strings.isNullOrEmpty(cur)){
                prefix = cur;
                break;
            }
            index++;
            if (index == length){
                prefix = cur;
            }

        }
        cur = "";
        while(index < length){
            char c = keyword.charAt(index);
            index++;
            if (Character.isDigit(c)){
                cur += c;
            }else if(!Strings.isNullOrEmpty(cur)){
                digit = cur;
                break;
            }
            if (index == length){
                digit = cur;
            }
        }
        if (Strings.isNullOrEmpty(prefix) || Strings.isNullOrEmpty(digit)){
            throw new PageNotFoundException("Exception: keywordNLP -- > search keyword is not correct");
        }
        res[0] = prefix;
        res[1] = digit;
        return res;
    }

}
