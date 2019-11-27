package com.project.oa.base.controller;

import com.project.oa.base.bean.User;
import com.project.oa.base.service.IUserService;
import com.project.oa.base.util.ActivitiUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
import java.util.Map;
import java.util.stream.Collectors;
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
    @Autowired
    private IUserService userService;

    @RequestMapping("getHistoryImg")
    public ResponseEntity getHistoryImg(String processInstId) throws IOException {
        System.out.println(processInstId);
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        HistoricActivityInstanceQuery historyInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstId);
        List<HistoricActivityInstance> historicActivityInstanceList = historyInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        List<String> executedActivityIdList = historicActivityInstanceList.stream().map(item -> item.getActivityId()).collect(Collectors.toList());
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<String> flowIds = ActivitiUtils.getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);
        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = diagramGenerator.generateDiagram(bpmnModel, executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", true, "png");
        String fileName = new String("流程图片".getBytes("utf-8"),"iso-8859-1" );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(IOUtils.toByteArray(inputStream),httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping("getMyBacklog")
    @ResponseBody
    public List<HashMap<String, Object>> getMyBacklog(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(user.getId())
                .list();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup("boss")
                .list();
        list.addAll(tasks);
        List<HashMap<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = null;
        for (Task task : list) {
            map = new HashMap<>();
            map.put("processInstId",task.getProcessInstanceId());
            map.put("name", task.getName());
            map.put("createTime", task.getCreateTime());
            HistoricVariableInstance auditPageInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .variableName("auditPage")
                    .singleResult();
            map.put(auditPageInstance.getVariableName(), auditPageInstance.getValue());
            HistoricVariableInstance modelNameInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .variableName("modelName")
                    .singleResult();
            map.put(modelNameInstance.getVariableName(), modelNameInstance.getValue());
            HistoricVariableInstance applyUserIdInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .variableName("applyUserId")
                    .singleResult();
            User applyUser = userService.getUserById(Integer.parseInt(applyUserIdInstance.getValue().toString()));
            map.put("person", applyUser.getName());
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
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list();
        List<HashMap<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = null;
        for (HistoricTaskInstance historicTaskInstance : list) {
            map = new HashMap<>();
            map.put("processInstId",historicTaskInstance.getProcessInstanceId());
            map.put("name", historicTaskInstance.getName());
            map.put("endTime", historicTaskInstance.getEndTime());
            HistoricVariableInstance infoPageInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId())
                    .variableName("infoPage")
                    .singleResult();
            map.put(infoPageInstance.getVariableName(), infoPageInstance.getValue());
            HistoricVariableInstance modelNameInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId())
                    .variableName("modelName")
                    .singleResult();
            map.put(modelNameInstance.getVariableName(), modelNameInstance.getValue());
            HistoricVariableInstance applyUserIdInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId())
                    .variableName("applyUserId")
                    .singleResult();
            User applyUser = userService.getUserById(Integer.parseInt(applyUserIdInstance.getValue().toString()));
            map.put("person", applyUser.getName());
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
