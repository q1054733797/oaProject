package com.project.oa.base.service;

import com.project.oa.base.bean.OrgAndUserTree;

import java.util.List;

/**
 * @ClassName: IOrgService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:29
 * @Version: 1.0
 */
public interface IOrgService {
    List<OrgAndUserTree> getOrgAndUserTree();
}
