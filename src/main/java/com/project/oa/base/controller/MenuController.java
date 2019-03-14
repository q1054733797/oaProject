package com.project.oa.base.controller;

import com.project.oa.base.bean.Menu;
import com.project.oa.base.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: MenuController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/5 15:22
 * @Version: 1.0
 */
@Controller
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("getUserMenu")
    @ResponseBody
    public List getUserMenu(){
        HashMap map = new HashMap();
        map.put("userId", "1");
        return menuService.getUserMenu(map);
    }

    @RequestMapping("deleteMenu")
    @ResponseBody
    public String deleteMenu(@RequestBody List<Menu> menus){
        String result = "ok";
        try {
            deleteMenus(menus);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    private void deleteMenus(List<Menu> menus){
        for (Menu menu : menus) {
            Menu menuById = menuService.getMenuById(Integer.parseInt(menu.getId()));
            menuService.deleteMenu(Integer.parseInt(menu.getId()));
            if (menuById.getChildren() != null && menuById.getChildren().size() >0){
                deleteMenus(menuById.getChildren());
            }
        }
    }

    @RequestMapping("updateMenu")
    @ResponseBody
    public String updateMenu(Menu menu){
        String result = "ok";
        try {
            menuService.updateMenu(menu);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addMenu")
    @ResponseBody
    public String addMenu(Menu menu){
        String result = "ok";
        try {
            menuService.addMenu(menu);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getChildrenMenu")
    @ResponseBody
    public List getChildrenMenu(Menu menu){
        List menus = menuService.getChildrenMenu(menu);
        processMenu(menus);
        return menus;
    }

    @RequestMapping("getMenu")
    @ResponseBody
    public List<Menu> getMenu(Menu menu){
        List menus = menuService.getMenu(menu);
        processMenu(menus);
        return menus;
    }

    private void processMenu(List<Menu> list){
        for (Menu menu : list) {
            List<Menu> children = menu.getChildren();
            if(children == null || children.size() == 0){
                menu.setChildren(null);
            }else{
                processMenu(children);
            }
        }
    }
}
