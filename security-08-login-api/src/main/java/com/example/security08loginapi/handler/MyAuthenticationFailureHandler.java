package com.example.security08loginapi.handler;

import cn.hutool.json.JSONUtil;
import com.example.security08loginapi.result.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-25 07:41
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        //采用构建器模式，链式编程创建一个R对象
        R result = R.builder().code(500).msg("登录失败：" + exception.getMessage()).build();

        //hutool工具包，把R对象转成json字符串
        String json = JSONUtil.toJsonStr(result);

        response.getWriter().write(json);
    }
}
