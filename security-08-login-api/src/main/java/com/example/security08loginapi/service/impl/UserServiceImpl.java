package com.example.security08loginapi.service.impl;

import com.example.security08loginapi.entity.TPermission;
import com.example.security08loginapi.entity.TRole;
import com.example.security08loginapi.entity.TUser;
import com.example.security08loginapi.mapper.TPermissionMapper;
import com.example.security08loginapi.mapper.TRoleMapper;
import com.example.security08loginapi.mapper.TUserMapper;
import com.example.security08loginapi.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-02 00:18
 */
@Service
public class UserServiceImpl implements UserService {

    //逆向工程、反向工程（根据数据库表，生成mapper接口、mapper.xml、实体类）

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private TRoleMapper tRoleMapper;
    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库，查询页面上传过来的这个用户名是否在数据库中存在，也就是根据该username查询用户对象
        TUser tUser = tUserMapper.selectByLoginAct(username); //cat
        if (tUser == null) {
            throw new UsernameNotFoundException("登录账号不存在");
        }
        return tUser;
//        return User.builder().username(tUser.getLoginAct()).password(tUser.getLoginPwd()).accountExpired(true)
//                .authorities(AuthorityUtils.NO_AUTHORITIES).accountExpired(false).build();

    }
}
