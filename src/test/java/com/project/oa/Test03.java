package com.project.oa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : zhanghongkai
 * @version : 1.0
 * @ClassName : Test03
 * @date : Create in 2019/9/5 19:45
 */
public class Test03 {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
