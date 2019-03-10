package com.project.oa.base.service;

import com.project.oa.base.bean.Role;

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
}
