package com.sgay.giligili.controller;

import com.google.common.base.Strings;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.entity.VoteTeacher;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xurancrazy on 2016/11/19.
 */
@Controller
@RequestMapping(value = "/vote")
public class VoteController extends BaseController {

    @PostMapping(value = "/teachers/{id}",produces = "application/json")
    public @ResponseBody
    VoteTeacher teacherVote(@PathVariable int id, HttpServletResponse response, @CookieValue(value = "teachersVoted", defaultValue = "") String teachersVoted){
        VoteTeacher voteTeacher = mVoteService.queryVoteTeacherByCookie(id, response, teachersVoted);
        return voteTeacher;
    }
}
