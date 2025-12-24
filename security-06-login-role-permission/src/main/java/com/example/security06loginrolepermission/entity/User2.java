package com.example.security06loginrolepermission.entity;

import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description 用来演示controller 层方法被 @ResponseBody 注解修饰时, Jackson框架（默认框架）会将所有字段的getters值组装并转化为JSON对象
 * @date 2025-12-04 23:20
 */
public class User2 {
    public int getId() {
        return 10283;
    }

    public String getName() {
        return "张三丰";
    }

    public Date getBirthDay() {
        return new Date();
    }
}
