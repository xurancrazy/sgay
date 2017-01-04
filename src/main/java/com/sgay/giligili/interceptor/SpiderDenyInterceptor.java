package com.sgay.giligili.interceptor;

import com.google.common.base.Strings;
import com.sgay.giligili.exception.ForbiddenAccessException;
import com.sgay.giligili.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by xurancrazy on 2017/1/2.
 */
public class SpiderDenyInterceptor extends BaseHandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String IP = (String) request.getAttribute("userAccessIP");
        logger.info("preHandle --> IP:" + IP);
        return Strings.isNullOrEmpty(IP) || !isSpiderDeny(IP);
    }

    private boolean isSpiderDeny(String IP) {
        boolean isDeny = mRedisTemplate.opsForSet().isMember(Constants.USER_ACCESS_DENY_SET, IP);
        if (isDeny){
            throw new ForbiddenAccessException("access denied");
        }
        int accessOneMinute = mRedisTemplate.opsForZSet().score(Constants.USER_ACCESS_0NE_MINUTE, IP).intValue();
        int accessOneHour = mRedisTemplate.opsForZSet().score(Constants.USER_ACCESS_0NE_HOUR, IP).intValue();
        if (accessOneMinute >= 30 || accessOneHour >= 900){
            mRedisTemplate.opsForSet().add(Constants.USER_ACCESS_DENY_SET, IP);
            throw new ForbiddenAccessException("access denied");
        }
        return false;
    }
}
