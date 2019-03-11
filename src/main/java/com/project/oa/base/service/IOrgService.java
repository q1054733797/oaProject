package com.project.oa.base.service;

import com.project.oa.base.bean.Org;
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
    List<Org> getChildOrg(int id);
    int addOrg(Org org);
    int updateOrg(Org org);
    int deleteOrg(Org org);
    Org getOrgById(String id);
}
