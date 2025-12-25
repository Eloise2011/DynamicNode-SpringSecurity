package com.example.security08loginapi.handler;

import cn.hutool.json.JSONUtil;
import com.example.security08loginapi.result.R;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-24 21:10
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //采用构建器模式，链式编程创建一个R对象
        R result = R.builder().code(200).msg("登录成功").info(authentication).build();
        //hutool工具包，把R对象转成json字符串
        String json = JSONUtil.toJsonStr(result);

        //把json写出去，写出到浏览器客户端
        response.getWriter().write(json);
    }
}
