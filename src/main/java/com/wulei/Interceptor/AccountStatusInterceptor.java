package com.wulei.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wulei on 2017/10/21
 *
 */
public class AccountStatusInterceptor implements HandlerInterceptor{

    /**
     * 为每一个请求（除/account/login）拦截
     * 判断当前是否有用户登录
     * 如果没有登录，则跳转到登录页面
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object sessionAttribute = httpServletRequest.getSession().getAttribute("user");
        if(sessionAttribute != null)
            return true;

        httpServletResponse.sendRedirect("account/login");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
