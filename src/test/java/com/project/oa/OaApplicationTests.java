package com.project.oa;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void test(){
        //repositoryService.deleteDeployment("dca22d75-c7d8-11e9-8736-005056c00008");
        repositoryService.deleteDeployment("58adda20-c878-11e9-ab9d-005056c00008");
    }
}
