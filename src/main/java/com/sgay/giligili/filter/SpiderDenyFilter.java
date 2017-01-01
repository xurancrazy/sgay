package com.sgay.giligili.filter;

import com.sgay.giligili.exception.ForbiddenException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xurancrazy on 2017/1/1.
 */
public class SpiderDenyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String ip = httpServletRequest.getRemoteAddr();
        String ips = null;
        if (httpServletRequest.getHeader("x-forwarded-for") != null){
            ips = httpServletRequest.getHeader("x-forwarded-for");
            logger.info("doFilterInternal --> x-forwarded-for:" + ips);
        }

        logger.info("doFilterInternal -- > ip:" + ip);
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
            throw new ForbiddenException("access denied");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
