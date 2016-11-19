package com.sgay.giligili.service.impl;

import com.google.common.base.Strings;
import com.sgay.giligili.entity.Teacher;
import com.sgay.giligili.entity.VoteTeacher;
import com.sgay.giligili.service.ITeacherService;
import com.sgay.giligili.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xurancrazy on 2016/11/19.
 */
@Service
public class VoteService extends BaseService implements IVoteService {
    @Autowired
    private ITeacherService mTeacherService;

    @Override
    public VoteTeacher queryVoteTeacherByCookie(int id, HttpServletResponse response, String teachersVoted) {
        VoteTeacher voteTeacher = new VoteTeacher();
        boolean voted = false;
        int tmpId = 0;
        int tmpNum = 0;
        if (!Strings.isNullOrEmpty(teachersVoted)){
            String tmpTeachersVoted = teachersVoted;
            if (teachersVoted.endsWith("-")){
                int size = teachersVoted.length();
                tmpTeachersVoted = teachersVoted.substring(0,size-1);
            }
            String[] votedTeachers = tmpTeachersVoted.split("-");
            for (String votedTeacher : votedTeachers){
                String[] idAndNum = votedTeacher.split("/");
                tmpId = Integer.parseInt(idAndNum[0]);
                tmpNum = Integer.parseInt(idAndNum[1]);
                if (tmpId == id){
                    voted = true;
                    break;
                }
            }
            if (voted){
                voteTeacher.setSuccess(false);
                voteTeacher.setNum(tmpNum);
                voteTeacher.setId(tmpId);
                return voteTeacher;
            }
        }
        Teacher teacher = mTeacherService.queryTeacherById(id);
        tmpId = id;
        tmpNum = teacher.getLikesnum().intValue();
        String teacherName = teacher.getName();
        teachersVoted += tmpId + "/" + tmpNum + "-";
        mTeacherService.updateTeacherLikesNumInCache(teacherName);
        response.addCookie(new Cookie("teachersVoted",teachersVoted));
        voteTeacher.setId(tmpId);
        voteTeacher.setNum(tmpNum);
        voteTeacher.setSuccess(true);
        return voteTeacher;
    }
}
