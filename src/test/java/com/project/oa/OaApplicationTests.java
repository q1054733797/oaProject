package com.project.oa;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    IdentityService identityService;
    @Autowired
    FormService formService;

    @Test
    public void deploymentProcessDefinition() {
        Deployment deploy = repositoryService.createDeployment()
                .name("我的请假流程")
                .addClasspathResource("processes/myProcess.xml")
                .addClasspathResource("processes/myProcess.png")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
        System.out.println(deploy.getKey());
    }

    @Test
    public void startProcessInst(){
        //identityService.setAuthenticatedUserId("7");
        Map map = new HashMap();
        map.put("applyPage", "申请页面");
        map.put("auditPage", "审批页面");
        map.put("applyUserId", "7");
        map.put("audit_result", "true");
        ProcessInstance myProcess = runtimeService
                .startProcessInstanceByKey("test", map);
    }

    @Test
    public void queryMyTask(){
        Map<String, Object> variables = runtimeService.getVariables("80001");
        variables.put("infoPage", "/base/vacateManage/infoVacate.html");
        runtimeService.setVariables("80001", variables);
    }

    @Test
    public void completeTask(){
//        Map map = new HashMap();
//        map.put("audit_result", "false");
//        taskService.complete("27502",map);
        taskService.complete("17507" );
    }

    @Test
    public void getProcessImg() throws IOException {
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionId("myProcess:1:22504").singleResult();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).orderByHistoricActivityInstanceId().desc().list();
        List<String> activitiIdList = new ArrayList();
        for (HistoricActivityInstance activityInstance : list) {
            activitiIdList.add(activityInstance.getActivityId());
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> executedFlowIdList = new ArrayList<>();
        for(int i=0;i<list.size()-1;i++) {
            HistoricActivityInstance hai = list.get(i);
            FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(hai.getActivityId());
            List<SequenceFlow> sequenceFlows = flowNode.getOutgoingFlows();
            if(sequenceFlows.size()>1) {
                HistoricActivityInstance nextHai = list.get(i+1);
                sequenceFlows.forEach(sequenceFlow->{
                    if(sequenceFlow.getTargetFlowElement().getId().equals(nextHai.getActivityId())) {
                        executedFlowIdList.add(sequenceFlow.getId());
                    }
                });
            }else {
                executedFlowIdList.add(sequenceFlows.get(0).getId());
            }
        }
        InputStream inputStream = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
                .generateDiagram(bpmnModel, "jpg", activitiIdList, executedFlowIdList, "宋体", "宋体", "宋体", null, 2.0);
        FileUtils.copyInputStreamToFile(inputStream, new File("F:/a.jpg"));

    }

    @Test
    public void deploymentProcessDefinitionByZip() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("F:/MyProcess.zip"));
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deploy = repositoryService.createDeployment()
                .name("zip部署")
                .addZipInputStream(zipInputStream).deploy();
        System.out.println(deploy.getKey());
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void getProcessPng() throws IOException {
        InputStream inputStream = repositoryService.getResourceAsStream("17501", "MyProcess.png");
        FileUtils.copyInputStreamToFile(inputStream, new File("F:/a.png"));
    }

    @Test
    public void queryProcessInst(){
        Task task = taskService.createTaskQuery().processInstanceId("42501").singleResult();
        String processInstanceId = task.getProcessInstanceId();
        System.out.println(processInstanceId);
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).list();
        for (ProcessInstance processInstance : list) {
            System.out.println(processInstance.isSuspended());
        }
    }

    @Test
    public void deleteProcessDefinition(){
        repositoryService.deleteDeployment("27501", true);
    }

    @Test
    public void deleteProcessInst(){
        runtimeService.deleteProcessInstance("32505", "noWhay");
    }

    @Test
    public void queryProcessDefinitionList(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
            System.out.println(processDefinition.getKey());
            System.out.println(processDefinition.getResourceName());
        }
    }

    @Test
    public void setStartFormData(){
        StartFormData startFormData = formService.getStartFormData("test:1:65004");

    }
}
