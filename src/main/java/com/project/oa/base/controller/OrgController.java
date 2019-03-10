package com.project.oa.base.controller;

import com.project.oa.base.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("getOrgAndUserTree")
    @ResponseBody
    public List getOrgAndUserTree(){
        return orgService.getOrgAndUserTree();
    }
}
