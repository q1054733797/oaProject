package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Role;
import com.project.oa.base.bean.User;
import com.project.oa.base.mapper.RoleMapper;
import com.project.oa.base.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:19
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean userOwnRole(int userId, int roleId) {
        List<HashMap> maps = roleMapper.userOwnRole(userId, roleId);
        if(maps.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int cancelRoleByRoleId(int roleId) {
        return roleMapper.cancelRoleByRoleId(roleId);
    }

    @Override
    public int cancelRoleByUserId(int userId) {
        return roleMapper.cancelRoleByUserId(userId);
    }

    @Override
    public int grantRole(int roleId, int userId) {
        return roleMapper.grantRole(roleId, userId);
    }

    @Override
    public int cancelRole(int roleId, int userId) {
        return roleMapper.cancelRole(roleId, userId);
    }

    @Override
    public List<Role> getUserOwnRole(User user) {
        return roleMapper.getUserOwnRole(user);
    }

    @Override
    public List<Role> getUserNoRole(User user) {
        return roleMapper.getUserNoRole(user);
    }

    @Override
    public int deleteRole(Role role) {
        return roleMapper.deleteRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public List<Role> getRole(Role role) {
        return roleMapper.getRole(role);
    }
}
