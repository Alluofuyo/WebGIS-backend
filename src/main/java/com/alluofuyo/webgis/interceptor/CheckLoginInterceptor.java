package com.alluofuyo.webgis.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CheckLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((!"GET".equals(request.getMethod()))&&(!"POST".equals(request.getMethod()))){
            return true;
        }
        try {
            StpUtil.checkLogin();
            System.out.println(response);
            return true;
        } catch (NotLoginException e) {
            PrintWriter writer = response.getWriter();
            writer.append("{\"status\":-2,\"msg\":\"未登录\"}");
            writer.flush();
            writer.close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(response.getHeaderNames());
        if (StpUtil.isLogin()) {
            response.addHeader(StpUtil.getTokenName(), StpUtil.getTokenValue());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
