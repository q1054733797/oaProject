package com.project.oa.base.service;

import com.project.oa.base.bean.Org;
import com.project.oa.base.bean.User;

import java.util.List;

/**
 * @ClassName: IUserService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/11 17:08
 * @Version: 1.0
 */
public interface IUserService {
    int deleteUserByOrgId(int orgId);
    User getUserById(int id);
    List<User> getUserByOrgId(User user);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(User user);
    List<User> getUser(User user);
    User getUserByUsername(String username);
}
