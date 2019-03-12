package com.project.oa.base.controller;

import com.project.oa.base.bean.Org;
import com.project.oa.base.service.IOrgService;
import com.project.oa.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: OrgController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:30
 * @Version: 1.0
 */
@Controller
public class OrgController {
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IUserService userService;

    @RequestMapping("getOrg")
    @ResponseBody
    public List<Org> getOrg(Org org){
        return orgService.getOrg(org);
    }

    @RequestMapping("getOrgById")
    @ResponseBody
    public Org getOrgById(Integer id){
        Org org = null;
        if(id != null){
            org = orgService.getOrgById(String.valueOf(id));
        }
        return org;
    }

    @RequestMapping("deleteOrg")
    @ResponseBody
    public String deleteOrg(@RequestBody List<Org> orgs){
        String result = "ok";
        try {
            deleteOrgs(orgs);
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateOrg")
    @ResponseBody
    public String updateOrg(Org org){
        String result = "fail";
        try {
            if(orgService.updateOrg(org) > 0){
                result = "ok";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addOrg")
    @ResponseBody
    public String addOrg(Org org){
        String result = "ok";
        try {
            orgService.addOrg(org);
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getChildOrg")
    @ResponseBody
    public List getChildren(Org org){
        return orgService.getChildOrg(org);
    }

    @RequestMapping("getOrgAndUserTree")
    @ResponseBody
    public List getOrgAndUserTree(){
        return orgService.getOrgAndUserTree();
    }

    private void deleteOrgs(List<Org> orgs){
        for (Org org : orgs) {
            orgService.deleteOrg(org);
            userService.deleteUserByOrgId(Integer.parseInt(org.getId()));
            List<Org> childOrg = orgService.getChildOrg(org);
            deleteOrg(childOrg);
        }
    }
}
