package com.project.oa.base.controller;

import com.project.oa.base.bean.User;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @ClassName: ProcessController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 11:38
 * @Version: 1.0
 */
@Controller
public class ProcessController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngine processEngine;

    @RequestMapping("getMyBacklog")
    @ResponseBody
    public List<HashMap<String, Object>> getMyBacklog(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(user.getId())
                .list();
        List<HashMap<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = null;
        for (Task task : list) {
            map = new HashMap<>();
            map.put("id",task.getId());
            map.put("name", task.getName());
            map.put("person", user.getName());
            map.put("createTime", task.getCreateTime());
            map.put("processInstId", task.getProcessInstanceId());
            maps.add(map);
        }
        return maps;
    }

    @RequestMapping("getMyFinished")
    @ResponseBody
    public List<HashMap<String, Object>> getMyFinished(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(user.getId())
                .finished()
                .list();
        List<HashMap<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = null;
        for (HistoricTaskInstance historicTaskInstance : list) {
            map = new HashMap<>();
            map.put("processInstId",historicTaskInstance.getProcessInstanceId());
            map.put("name", historicTaskInstance.getName());
            map.put("person", user.getName());
            map.put("startTime", historicTaskInstance.getStartTime());
            map.put("endTime", historicTaskInstance.getEndTime());
            maps.add(map);
        }
        return maps;
    }

    @RequestMapping("getProcessImg")
    public ResponseEntity<byte[]> getProcessImg(String deploymentId,String id){
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(id)
                    .singleResult();
            String diagramResourceName = processDefinition.getDiagramResourceName();
            InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
            File file = new File("F:/a.png");
            FileUtils.copyInputStreamToFile(inputStream, file);
            String fileName = new String(diagramResourceName.getBytes("utf-8"),"iso-8859-1" );
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDispositionFormData("attachment", fileName);
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("removeProcessDefinition")
    @ResponseBody
    public String removeProcessDefinition(@RequestBody List<HashMap<String,String>> list){
        String result = "ok";
        if(list != null && list.size() > 0){
            for (HashMap<String, String> map : list) {
                repositoryService.deleteDeployment(map.get("deploymentId"), true);
            }
        }
        return result;
    }

    @RequestMapping("getProcessDefinitionList")
    @ResponseBody
    public List getProcessDefinitionList(String name,String key){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if(name != null && !name.equals("")){
            processDefinitionQuery.processDefinitionNameLike("%" + name + "%");
        }
        if(key != null && !key.equals("")){
            processDefinitionQuery.processDefinitionKey(key);
        }
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
        List<HashMap> list = new ArrayList<>();
        HashMap map = null;
        for (ProcessDefinition processDefinition : processDefinitionList) {
            map = new HashMap();
            map.put("deploymentId", processDefinition.getDeploymentId());
            map.put("id", processDefinition.getId());
            map.put("name", processDefinition.getName());
            map.put("description", processDefinition.getDescription());
            map.put("key", processDefinition.getKey());
            list.add(map);
        }
        return list;
    }

    @RequestMapping("deploymentProcessDefinition")
    @ResponseBody
    public String deploymentProcessDefinition(String name, MultipartFile file){
        String isOk = "ok";
        try {
            InputStream inputStream = file.getInputStream();
            repositoryService.createDeployment()
                    .name(name)
                    .addZipInputStream(new ZipInputStream(inputStream))
                    .deploy();
        } catch (Exception e) {
            e.printStackTrace();
            isOk = "false";
        }
        return isOk;
    }
}
