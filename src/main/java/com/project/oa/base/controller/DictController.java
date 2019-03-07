package com.project.oa.base.controller;

import com.project.oa.base.bean.Dict;
import com.project.oa.base.bean.DictItem;
import com.project.oa.base.service.IDictItemService;
import com.project.oa.base.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: DictController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 10:57
 * @Version: 1.0
 */
@Controller
public class DictController {
    @Autowired
    private IDictService dictService;
    @Autowired
    private IDictItemService dictItemService;

    @RequestMapping("deleteDict")
    @ResponseBody
    public String deleteDict(@RequestBody List<Dict> dicts){
        String result = "ok";
        try {
            for (Dict dict : dicts) {
                dictService.deleteDict(dict);
                List<DictItem> dictItemByDictCode = dictItemService.getDictItemByDictCode(dict.getCode());
                for (DictItem dictItem : dictItemByDictCode) {
                    dictItemService.deleteDictItem(dictItem);
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateDict")
    @ResponseBody
    public String updateDict(Dict dict){
        String result = "ok";
        try {
            dictService.updateDict(dict);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getDict")
    @ResponseBody
    public List getDict(Dict dict){
        return dictService.getDict(dict);
    }

    @RequestMapping("addDict")
    @ResponseBody
    public String addDict(Dict dict){
        String result = "ok";
        try {
            dictService.addDict(dict);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }
}
