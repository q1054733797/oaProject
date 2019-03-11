package com.project.oa.base.service;

import com.project.oa.base.bean.User;

import java.util.List;

/**
 * @ClassName: IUserService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/11 17:08
 * @Version: 1.0
 */
public interface IUserService {
    List<User> getUserByOrgId(String orgId);
    int addUser(User user);
}
