package com.sgay.giligili.interceptor;

import com.sgay.giligili.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xurancrazy on 2017/1/2.
 */
public class UserAccessInterceptor extends BaseHandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String IP = getUserIP(request);
        request.setAttribute("userAccessIP", IP);
        mRedisTemplate.opsForZSet().incrementScore(Constants.USER_ACCESS_0NE_MINUTE, IP, 1);
        mRedisTemplate.opsForZSet().incrementScore(Constants.USER_ACCESS_0NE_HOUR, IP, 1);
        return true;
    }
}
