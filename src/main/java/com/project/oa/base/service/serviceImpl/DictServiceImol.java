package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Dict;
import com.project.oa.base.bean.DictItem;
import com.project.oa.base.mapper.DictMapper;
import com.project.oa.base.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: DictServiceImol
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 10:55
 * @Version: 1.0
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class DictServiceImol implements IDictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public int addDict(Dict dict) {
        return dictMapper.addDict(dict);
    }

    @Override
    public List<Dict> getDict(Dict dict) {
        return dictMapper.getDict(dict);
    }

    @Override
    public int updateDict(Dict dict) {
        return dictMapper.updateDict(dict);
    }

    @Override
    public int deleteDict(Dict dict) {
        return dictMapper.deleteDict(dict);
    }
}
