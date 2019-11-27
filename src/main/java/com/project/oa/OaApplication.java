package com.project.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@MapperScan(basePackages = {"com.project.oa.base.mapper"})
@Controller
@SpringBootApplication
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
        System.out.println("-------启动成功-------");
    }

    @RequestMapping("/")
    public String index(){
        return "base/login.html";
    }
}
