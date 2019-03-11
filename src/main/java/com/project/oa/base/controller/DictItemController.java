package com.project.oa.base.controller;

import com.project.oa.base.bean.DictItem;
import com.project.oa.base.service.IDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: DictItemController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 11:44
 * @Version: 1.0
 */
@Controller
public class DictItemController {
    @Autowired
    private IDictItemService dictItemService;

    @RequestMapping("getDictItemText")
    @ResponseBody
    public String getDictItemText(String dictCode, String dictItemCode){
        String name = null;
        if(dictCode != null && dictItemCode != null){
            DictItem dictItem = dictItemService.getDictItemByDictCodeAndDictItemCode(dictCode, dictItemCode);
            name = dictItem.getName();
        }
        return name;
    }

    @RequestMapping("deleteDictItem")
    @ResponseBody
    public String deleteDictItem(@RequestBody List<DictItem> dictItems){
        String result = "ok";
        try {
            for (DictItem dictItem : dictItems) {
                dictItemService.deleteDictItem(dictItem);
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateDictItem")
    @ResponseBody
    public String updateDictItem(DictItem dictItem){
        String result = "ok";
        try {
            dictItemService.updateDictItem(dictItem);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addDictItem")
    @ResponseBody
    public String addDictItem(DictItem dictItem){
        String result = "ok";
        try {
            dictItemService.addDictItem(dictItem);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getDictItem")
    @ResponseBody
    public List getDictItem(DictItem dictItem){
        return dictItemService.getDictItem(dictItem);
    }
}
