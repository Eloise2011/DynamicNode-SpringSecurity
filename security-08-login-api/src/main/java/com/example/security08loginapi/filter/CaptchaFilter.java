package com.example.security08loginapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-03 20:34
 */
//@Component
public class CaptchaFilter extends OncePerRequestFilter { //在spring框架中，实现Filter，直接继承OncePerRequestFilter更方便

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String code = request.getParameter("captcha");
        String sessionCode = (String) request.getSession().getAttribute("captcha");
        String requestUri = request.getRequestURI(); //   /user/login
        if (requestUri.equals("/user/login")) { //如果是登录请求，我们就验证验证码，否则不需要验证验证码
            if (!StringUtils.hasText(code)) { //前面加了一个“！” 表示非，取反，那就是如果code是空的
                //验证没通过
                System.out.printf("当前请求中 code: %s; sessionCode: %s; uri: %s",code,sessionCode,requestUri);
                System.out.println(System.lineSeparator());
                response.sendRedirect("/");
            } else if (!code.equalsIgnoreCase(sessionCode)) { //如果前端传过来的验证码和后端session中存放的验证码不相等
                //验证没通过
                System.out.printf("当前请求中 code: %s; sessionCode: %s; uri: %s",code,sessionCode,requestUri);
                System.out.println(System.lineSeparator());
                response.sendRedirect("/");
            } else {
                //验证码相等，可以放行，继续执行下一个filter
                System.out.printf("当前请求中 code: %s; sessionCode: %s; uri: %s",code,sessionCode,requestUri);
                System.out.println(System.lineSeparator());
                filterChain.doFilter(request, response);
            }
        } else {
            System.out.printf("当前请求中 code: %s; sessionCode: %s; uri: %s",code,sessionCode,requestUri);
            System.out.println(System.lineSeparator());
            //不是登录请求，不需要验证验证码，直接放行
            filterChain.doFilter(request, response);
        }
    }
}
