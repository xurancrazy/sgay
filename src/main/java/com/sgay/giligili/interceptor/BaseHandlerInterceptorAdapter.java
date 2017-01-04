package com.sgay.giligili.interceptor;

import com.google.common.base.Strings;
import com.sgay.giligili.exception.ForbiddenAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xurancrazy on 2017/1/2.
 */
public class BaseHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    @Autowired
    protected RedisTemplate<String, String> mRedisTemplate;

    protected Logger logger= LoggerFactory.getLogger(this.getClass());

    protected String getUserIP(HttpServletRequest request){
        String IP = request.getRemoteAddr();
        String IPs = null;
        if (request.getHeader("x-forwarded-for") != null){
            IPs = request.getHeader("x-forwarded-for");
            logger.info("getUserIP --> x-forwarded-for:" + IPs);
        }
        if (!Strings.isNullOrEmpty(IPs)){
            String[] proxyIPs = IPs.split(",");
            int length = proxyIPs.length;
            IP = proxyIPs[length - 1].trim();
        }
        return IP;
    }
}
