package com.project.oa;

import com.project.oa.base.bean.User;

/**
 * @ClassName: Test01
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/12 16:19
 * @Version: 1.0
 */
public class Test01 {
    public static void main(String[] args) {
        User user = new User();
        user.setName(new String("zhang"));
        String a = "123";
        String b = "zhang";
        System.out.println(user.getName() == b);
    }
}
