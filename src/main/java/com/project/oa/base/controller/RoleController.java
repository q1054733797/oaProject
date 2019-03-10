package com.project.oa.base.controller;

import com.project.oa.base.bean.Role;
import com.project.oa.base.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: RoleController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:20
 * @Version: 1.0
 */
@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("deleteRole")
    @ResponseBody
    public String deleteRole(@RequestBody List<Role> roles){
        String result = "ok";
        try {
            for (Role role : roles) {
                roleService.deleteRole(role);
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateRole")
    @ResponseBody
    public String updateRole(Role role){
        String result = "ok";
        try {
            roleService.updateRole(role);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addRole")
    @ResponseBody
    public String addRole(Role role){
        String result = "ok";
        try {
            roleService.addRole(role);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getRole")
    @ResponseBody
    public List getRole(Role role){
        return roleService.getRole(role);
    }
}
