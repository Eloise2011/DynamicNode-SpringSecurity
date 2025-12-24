package com.example.security06loginrolepermission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua.H.Brooks
 * @description 在这个控制器里定义3个方法，具有不同权限的角色用户可以访问对应不同的资源
 *
 *
 * @date 2025-12-05 22:51
 */
@RestController
public class ClueController {

    @PreAuthorize(value = "hasRole('saler')")
    @RequestMapping(value = "/api/clue/list")
    public String clueList(){
        return "has role of saler can access : 线索管理-列表";
    }

    @RequestMapping(value = "/api/clue/check")
    public String clueCheck(){
        return "No access control on : 线索管理-查看";
    }

    @PreAuthorize(value = "hasAnyRole('manager','admin')")
    @RequestMapping(value = "/api/clue/edit")
    public String clueEdit(){
        return "has any role amongst of 'admin', 'manager' can access : 线索管理-列表";
    }

}
