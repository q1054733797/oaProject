package com.project.oa.base.controller;

import com.project.oa.base.bean.User;
import com.project.oa.base.bean.Vacate;
import com.project.oa.base.service.IVacateService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("cancelVacates")
    @ResponseBody
    public String cancelVacates(@RequestBody List<Vacate> vacates){
        String result = "ok";
        try {
            for (Vacate vacate : vacates) {
                if(vacate.getProcessStatus() != null && vacate.getProcessStatus().equals("请假单已提交")){
                    runtimeService.deleteProcessInstance(vacate.getProcessInstId(),"撤销");
                    vacate.setProcessStatus("申请已撤销");
                    vacateService.updateVacate(vacate);
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("auditVacate")
    @ResponseBody
    public String auditVacate(String taskId,String audit_result,String processInstId){
        String result = "ok";
        try {
            HashMap map = new HashMap();
            map.put("audit_result", audit_result);
            taskService.complete(taskId, map);
            Vacate vacate = vacateService.getVacateByProcessInstId(processInstId);
            if(audit_result.equals("true")){
                vacate.setProcessStatus("审批通过，放假啦");
            }else{
                vacate.setProcessStatus("审批未通过");
            }
            vacateService.updateVacate(vacate);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getVacateByProcessInstId")
    @ResponseBody
    public Vacate getVacateByProcessInstId(String processInstId){
        Vacate vacate = vacateService.getVacateByProcessInstId(processInstId);
        return vacate;
    }

    @RequestMapping("applyVacates")
    @ResponseBody
    public String applyVacates(@RequestBody List<Vacate> vacates,HttpServletRequest request){
        String result = "ok";
        User user = (User)request.getSession().getAttribute("user");
        try {
            String processStatus = null;
            HashMap map = null;
            for (Vacate vacate : vacates) {
                processStatus = vacate.getProcessStatus();
                if(processStatus == null || processStatus.equals("")){
                    vacate.setProcessStatus("请假单已提交");
                    vacate.setApplyTime(new Date());
                    map = new HashMap();
                    map.put("applyUserId", user.getId());
                    map.put("auditRoles", "boss");
                    map.put("applyPage", "/base/vacateManage/applyVacate.html");
                    map.put("auditPage", "/base/vacateManage/auditVacate.html");
                    ProcessInstance processInstance = runtimeService
                            .startProcessInstanceByKey("vacate",map);
                    vacate.setProcessInstId(processInstance.getId());
                    Task task = taskService.createTaskQuery()
                            .processInstanceId(processInstance.getId())
                            .taskAssignee(user.getId())
                            .singleResult();
                    taskService.complete(task.getId());
                    vacateService.updateVacate(vacate);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = "fail";
        }
        return result;
    }

    @RequestMapping("removeVacates")
    @ResponseBody
    public String removeVacates(@RequestBody List<Vacate> vacates){
        String result = "ok";
        try {
            for (Vacate vacate : vacates) {
                vacateService.removeVacate(vacate);
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateVacate")
    @ResponseBody
    public String updateVacate(Vacate vacate){
        String result = "ok";
        try {
            vacateService.updateVacate(vacate);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

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
