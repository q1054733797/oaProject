package com.project.oa;

import com.project.oa.base.bean.User;
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
    public void test() {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("9")
                .list();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup("boss")
                .list();
        list.addAll(tasks);
        List<HashMap<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = null;
        System.out.println(list.size());
        for (Task task : list) {
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
            maps.add(map);
        }
    }
}
