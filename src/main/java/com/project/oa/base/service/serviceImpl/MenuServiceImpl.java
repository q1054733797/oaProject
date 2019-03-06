package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Menu;
import com.project.oa.base.mapper.MenuMapper;
import com.project.oa.base.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: MenuServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/5 15:46
 * @Version: 1.0
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class MenuServiceImpl implements IMenuService{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteMenu(int id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public Menu getMenuById(int id) {
        return menuMapper.getMenuById(id);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int addMenu(Menu menu) {
        int i = menuMapper.addMenu(menu);
        return i;
    }

    @Override
    public List getMenu() {
        List<Menu> menu = menuMapper.getMenu();
        return menu;
    }

    @Override
    public List<Menu> getChildrenMenu(int id) {
        List<Menu> menu = menuMapper.getChildrenMenu(id);
        return menu;
    }
}
