package com.project.oa.base.service;

import com.project.oa.base.bean.DictItem;

import java.util.List;

/**
 * @ClassName: IDictItemService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 11:41
 * @Version: 1.0
 */
public interface IDictItemService {
    List<DictItem> getDictItem(DictItem dictItem);
    int addDictItem(DictItem dictItem);
    int updateDictItem(DictItem dictItem);
    int deleteDictItem(DictItem dictItem);
    List<DictItem> getDictItemByDictCode(String dict_code);
    DictItem getDictItemByDictCodeAndDictItemCode(String dictCode,String dictItemCode);
}
