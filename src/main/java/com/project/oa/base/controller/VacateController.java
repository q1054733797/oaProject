package com.project.oa.base.controller;

import com.project.oa.base.bean.User;
import com.project.oa.base.bean.Vacate;
import com.project.oa.base.service.IVacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: VacateController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:42
 * @Version: 1.0
 */
@Controller
public class VacateController {
    @Autowired
    private IVacateService vacateService;

    @RequestMapping("addVacate")
    @ResponseBody
    public String addVacate(HttpServletRequest request, Vacate vacate){
        String result = "ok";
        User user = (User)request.getSession().getAttribute("user");
        try {
            vacate.setApplyUserId(user.getId());
            vacateService.addVacate(vacate);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getMyVacateList")
    @ResponseBody
    public List getMyVacateList(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List myVacateList = vacateService.getMyVacateList(Integer.parseInt(user.getId()));
        return myVacateList;
    }
}
