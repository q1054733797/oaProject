package com.project.oa.base.service;

import com.project.oa.base.bean.Dict;

import java.util.List;

/**
 * @ClassName: IDictService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 10:55
 * @Version: 1.0
 */
public interface IDictService {
    int addDict(Dict dict);
    List<Dict> getDict(Dict dict);
    int updateDict(Dict dict);
    int deleteDict(Dict dict);
}
