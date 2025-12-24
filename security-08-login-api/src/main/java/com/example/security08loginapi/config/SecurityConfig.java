package com.example.security08loginapi.config;

import com.example.security08loginapi.filter.CaptchaFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description 
 * @date 2025-12-02 01:06
 * @author Joshua.H.Brooks 
 */
@EnableMethodSecurity //开启方法上的注解权限检查
@Configuration //配置spring的容器，类似spring.xml文件一样
public class SecurityConfig {

//    @Resource
//    private CaptchaFilter captchaFilter;
    /**
     * <bean id="passwordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"> </bean>
     *
     * @return
     */
    @Bean  //配置一个spring的bean， bean的id就是方法名，bean的class就是方法的返回类型
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                //配置我们自己的登录页
                .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(FormLoginConfigurer<HttpSecurity> formLogin) {
                        // 框架默认接收登录提交请求的地址是 /login，但是我们把它给弄丢了，需要捡回来
                        formLogin.loginProcessingUrl("/user/login"); //登录的账号密码往哪个地址提交，也就是页面里登陆表单的提交地址
                    }
                })

                //把所有接口都会进行登录状态检查的默认行为，再加回来
                .authorizeHttpRequests( (authorizeHttpRequests) -> {
                    authorizeHttpRequests
                            .requestMatchers("/toLogin",
                                    "/common/captcha",
                                    "/.well-known/**"
//                                    "/favicon.ico",
//                                    "/error",
//                                    "/actuator/**"
                                            )
                            .permitAll() // 忽略这些路径 特殊情况的设置，permitAll允许不登录就可以访问
                            .anyRequest().authenticated(); //除了上面的特殊情况外，其他任何对后端接口的请求，都需要认证（登录）后才能访问
                })
                //验证码filter加在接收登录账号密码的filter之前
                //.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
