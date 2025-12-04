package com.example.security04captchalogin;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Security05LoginInfoPrintingApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void test01() {
        String password = "aaa111";
        String encodePassword = passwordEncoder.encode(password); //加密方法
        System.out.println(encodePassword);

        boolean match = passwordEncoder.matches(password, encodePassword); //密码匹配
        System.out.println(match);
    }

    /**
     * 这里演示的是PasswordEncoder的加密模式，即同样明文每次加密得到的是不一样的密文
     * 具体逻辑可以参考文档 Spring_Security.doc 的 1.2.6 & 1.2.7  部分
     */
    @Test
    void test02() {
        String password = "aaa111";
        for (int i = 0; i < 10; i++) {
            String encodePassword = passwordEncoder.encode(password); //加密方法
            System.out.println(encodePassword);

            boolean match = passwordEncoder.matches(password, encodePassword); //密码匹配
            System.out.println(match);
        }
    }
}
