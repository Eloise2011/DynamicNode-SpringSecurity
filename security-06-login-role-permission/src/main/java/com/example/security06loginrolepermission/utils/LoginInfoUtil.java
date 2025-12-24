package com.example.security06loginrolepermission.utils;

import com.example.security06loginrolepermission.entity.TUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-04 23:32
 */
public class LoginInfoUtil {

    /**
     * 获取当前登录人的信息
     *
     * @return
     */
    public static TUser getCurrentLoginUser() {
        return (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
