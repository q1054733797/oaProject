package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Menu;
import com.project.oa.base.mapper.MenuMapper;
import com.project.oa.base.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Menu> getUserMenu(HashMap map) {
        Menu rootMenu = menuMapper.getRootMenu(new Menu());
        map.put("menuId", rootMenu.getId());
        getUserMenus(rootMenu,map);
        List<Menu> menus = new ArrayList<>();
        menus.add(rootMenu);
        return menus;
    }

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
    public List<Menu> getMenu(Menu menu) {
        Menu rootMenu = menuMapper.getRootMenu(menu);
        getMenus(rootMenu,menu.getRoleId());
        List<Menu> menus = new ArrayList<>();
        menus.add(rootMenu);
        return menus;
    }

    @Override
    public List<Menu> getChildrenMenu(Menu menu) {
        List<Menu> menus = menuMapper.getChildrenMenu(menu);
        return menus;
    }

    private void getMenus(Menu menu,String roleId){
        menu.setRoleId(roleId);
        List<Menu> childrenMenu = menuMapper.getChildrenMenu(menu);
        if (childrenMenu.size() > 0){
            menu.setChildren(childrenMenu);
            for (Menu childrenMenu1 : childrenMenu) {
                getMenus(childrenMenu1,roleId);
            }
        }
    }

    private void getUserMenus(Menu menu,HashMap map){
        List<Menu> childrenMenu = menuMapper.getUserMenu(map);
        if (childrenMenu.size() > 0){
            menu.setChildren(childrenMenu);
            for (Menu childrenMenu1 : childrenMenu) {
                map.put("menuId", childrenMenu1.getId());
                getUserMenus(childrenMenu1,map);
            }
        }
    }
}
