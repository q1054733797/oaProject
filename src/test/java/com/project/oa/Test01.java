package com.project.oa;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * @ClassName: Test01
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/12 16:19
 * @Version: 1.0
 */
public class Test01 {

    @Test
    public void createTable(){
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    }

    @Test
    public void deploymentProcessDefinition(){
        System.out.println(ProcessEngines.getDefaultProcessEngine());
//        Deployment deployment = processEngine.getRepositoryService().createDeployment()
//                .name("我的请假流程")
//                .addClasspathResource("processes/test.bpmn")
//                .addClasspathResource("processes/test.png")
//                .deploy();
//        System.out.println(deployment.getId());
    }
}
