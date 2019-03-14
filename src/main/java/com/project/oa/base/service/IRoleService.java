package com.project.oa.base.service;

import com.project.oa.base.bean.Role;
import com.project.oa.base.bean.User;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: IRoleService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:18
 * @Version: 1.0
 */
public interface IRoleService {
    int deleteRole(Role role);
    int updateRole(Role role);
    int addRole(Role role);
    List<Role> getRole(Role role);
    List<Role> getUserOwnRole(User user);
    List<Role> getUserNoRole(User user);
    int grantRole(int roleId,int userId);
    int cancelRole(int roleId,int userId);
    int cancelRoleByUserId(int userId);
    int cancelRoleByRoleId(int roleId);
    boolean userOwnRole(int userId,int roleId);
    int addMenuRole(int menuId,int roleId,String checked);
    int deleteMenuRoleByRoleId(int roleId);
}
