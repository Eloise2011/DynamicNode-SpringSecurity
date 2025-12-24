package com.example.security07loginresourcepermission.controller;

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
    /**
     * 张琪这个用户拥有如下权限资源：
     *
     * 线索管理-列表 clue： list
     * 线索管理-灵入 clue： add
     * 线索管理-编辑 clue： edit
     * 线索管理-查看 clue： view
     * 线索管理-导入 clue： import
     * @return
     */

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

    @PreAuthorize(value = "hasAuthority('clue:download')")
    @RequestMapping(value = "/api/clue/download")
    public String clueDownload(){
        return "Only those who has 'clue:download' access can manipulate : 线索管理-导出";
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:list','clue:add','clue:edit','clue:view','clue:import')")
    @RequestMapping(value = "/api/clue/any")
    public String clueAny(){
        return "has any authority amongst of \r\n 'clue:list','clue:add','clue:edit','clue:view','clue:import' can access : 线索管理-any";
    }
}
