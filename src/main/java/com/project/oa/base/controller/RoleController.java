package com.project.oa.base.controller;

import com.project.oa.base.bean.Role;
import com.project.oa.base.bean.User;
import com.project.oa.base.service.IRoleService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("updateRoleMenus")
    @ResponseBody
    public String updateRoleMenus(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> menus = (List<Map>) map.get("menus");
        try {
            roleService.deleteMenuRoleByRoleId(Integer.parseInt(map.get("roleId").toString()));
            for (Map menu : menus) {
                System.out.println(Integer.parseInt(menu.get("id").toString()));
                System.out.println(Integer.parseInt(map.get("roleId").toString()));
                System.out.println(menu.get("checked").toString());
                roleService.addMenuRole(Integer.parseInt(menu.get("id").toString()), Integer.parseInt(map.get("roleId").toString()),menu.get("checked").toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateRoleUsers")
    @ResponseBody
    public String updateRoleUsers(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> users = (List<Map>) map.get("users");
        try {
            for (Map user : users) {
                if(user.get("ownRole").toString().equals("y")){
                    if(!roleService.userOwnRole(Integer.parseInt(user.get("id").toString()), Integer.parseInt(map.get("roleId").toString()))){
                        roleService.grantRole(Integer.parseInt(map.get("roleId").toString()),Integer.parseInt(user.get("id").toString()));
                    }
                }else if(user.get("ownRole").toString().equals("n")){
                    if(roleService.userOwnRole(Integer.parseInt(user.get("id").toString()), Integer.parseInt(map.get("roleId").toString()))){
                        roleService.cancelRole(Integer.parseInt(map.get("roleId").toString()),Integer.parseInt(user.get("id").toString()));
                    }
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("deleteRoleUsers")
    @ResponseBody
    public String deleteRoleUsers(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> users = (List<Map>) map.get("users");
        try {
            for (Map user : users) {
                roleService.cancelRole(Integer.parseInt(map.get("roleId").toString()),Integer.parseInt(user.get("id").toString()));
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addRoleUsers")
    @ResponseBody
    public String addRoleUsers(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> users = (List<Map>) map.get("users");
        try {
            for (Map user : users) {
                if(!roleService.userOwnRole(Integer.parseInt(user.get("id").toString()), Integer.parseInt(map.get("roleId").toString()))){
                    roleService.grantRole(Integer.parseInt(map.get("roleId").toString()),Integer.parseInt(user.get("id").toString()));
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("cancelRole")
    @ResponseBody
    public String cancelRole(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> roles = (List<Map>) map.get("roles");
        try {
            for (Map role : roles) {
                roleService.cancelRole(Integer.parseInt(role.get("id").toString()), Integer.parseInt(map.get("id").toString()));
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("grantRole")
    @ResponseBody
    public String grantRole(@RequestBody Map<String,Object> map){
        String result = "ok";
        List<Map> roles = (List<Map>) map.get("roles");
        try {
            for (Map role : roles) {
                roleService.grantRole(Integer.parseInt(role.get("id").toString()), Integer.parseInt(map.get("id").toString()));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getUserNoRole")
    @ResponseBody
    public List<Role> getUserNoRole(User user){
        return roleService.getUserNoRole(user);
    }

    @RequestMapping("getUserOwnRole")
    @ResponseBody
    public List<Role> getUserOwnRole(User user){
        return roleService.getUserOwnRole(user);
    }

    @RequestMapping("deleteRole")
    @ResponseBody
    public String deleteRole(@RequestBody List<Role> roles){
        String result = "ok";
        try {
            for (Role role : roles) {
                roleService.deleteRole(role);
                roleService.cancelRoleByRoleId(Integer.parseInt(role.getId()));
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
