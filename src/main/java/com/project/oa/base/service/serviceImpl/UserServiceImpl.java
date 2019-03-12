package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.User;
import com.project.oa.base.mapper.UserMapper;
import com.project.oa.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/11 17:08
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public int deleteUserByOrgId(int orgId) {
        return userMapper.deleteUserByOrgId(orgId);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> getUserByOrgId(User user) {
        return userMapper.getUserByOrgId(user);
    }
}
