package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Dict;
import com.project.oa.base.bean.DictItem;
import com.project.oa.base.mapper.DictItemMapper;
import com.project.oa.base.service.IDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: DictItemServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 11:41
 * @Version: 1.0
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class DictItemServiceImpl implements IDictItemService {
    @Autowired
    private DictItemMapper dictItemMapper;

    @Override
    public DictItem getDictItemByDictCodeAndDictItemCode(String dictCode, String dictItemCode) {
        return dictItemMapper.getDictItemByDictCodeAndDictItemCode(dictCode,dictItemCode );
    }

    @Override
    public List<DictItem> getDictItemByDictCode(String dict_code) {
        return dictItemMapper.getDictItemByDictCode(dict_code);
    }

    @Override
    public int updateDictItem(DictItem dictItem) {
        return dictItemMapper.updateDictItem(dictItem);
    }

    @Override
    public int deleteDictItem(DictItem dictItem) {
        return dictItemMapper.deleteDictItem(dictItem);
    }

    @Override
    public int addDictItem(DictItem dictItem) {
        return dictItemMapper.addDictItem(dictItem);
    }

    @Override
    public List<DictItem> getDictItem(DictItem dictItem) {
        return dictItemMapper.getDictItem(dictItem);
    }
}
