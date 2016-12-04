package com.sgay.giligili.service;

import com.sgay.giligili.entity.VoteTeacher;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xurancrazy on 2016/11/19.
 */
public interface IVoteService extends IBaseService{
    VoteTeacher queryVoteTeacherByCookie(int id, HttpServletResponse response, String teachersVoted);
}
