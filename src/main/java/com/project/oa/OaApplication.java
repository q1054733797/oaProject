package com.project.oa;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = {"com.project.oa.base.mapper"})
@Controller
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }

    @RequestMapping("/")
    public String index(Model model, HttpServletResponse response){
        return "base/login.html";
    }
}
