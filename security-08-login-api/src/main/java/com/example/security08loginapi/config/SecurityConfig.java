package com.example.security08loginapi.config;

import com.example.security08loginapi.filter.CaptchaFilter;
import com.example.security08loginapi.handler.MyAuthenticationFailureHandler;
import com.example.security08loginapi.handler.MyAuthenticationSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @description 
 * @date 2025-12-02 01:06
 * @author Joshua.H.Brooks 
 */
@EnableMethodSecurity //开启方法上的注解权限检查
@Configuration //配置spring的容器，类似spring.xml文件一样
public class SecurityConfig {
    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CorsConfigurationSource configurationSource) throws Exception {
        return httpSecurity

                //配置我们自己的登录页
                .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(FormLoginConfigurer<HttpSecurity> formLogin) {
                        // 框架默认接收登录提交请求的地址是 /login，但是我们把它给弄丢了，需要捡回来
                        formLogin.loginProcessingUrl("/user/login") //登录的账号密码往哪个地址提交，也就是页面里登陆表单的提交地址
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(myAuthenticationFailureHandler);
                        // 前后端分离时， 登陆时直接请求在访问http://localhost:8080/user/login， /user/Loqin 地址之前，对于后端应用来说，没有上一个地址（原地），那默认是跳转到项目的根路径斜杆 /
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
                .csrf((csrf)->{
                    //禁用csrf跨站请求伪造，禁用之后，肯定就不安全了，有csrf网络攻击的风险，后续加入jwt是可以防御的
                    csrf.disable();
                })
                .cors((cors)->{
                    cors.configurationSource(configurationSource);
                })
                .build();
    }

    @Bean //配置跨域
    public CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        //跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*")); //允许任何来源，http://localhost:10492/
        corsConfiguration.setAllowedMethods(Arrays.asList("*")); //允许任何请求方法，post、get、put、delete
        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); //允许任何的请求头 (jwt)

        //注册跨域配置
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration); //  /api/user,  /api/user/12082
        return urlBasedCorsConfigurationSource;
    }

}
