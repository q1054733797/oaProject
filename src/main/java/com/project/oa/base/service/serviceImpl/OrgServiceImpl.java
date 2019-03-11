package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Org;
import com.project.oa.base.bean.OrgAndUserTree;
import com.project.oa.base.mapper.OrgMapper;
import com.project.oa.base.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: OrgServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:29
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class OrgServiceImpl implements IOrgService {
    @Autowired
    private OrgMapper orgMapper;

    @Override
    public Org getOrgById(String id) {
        return orgMapper.getOrgById(id);
    }

    @Override
    public int updateOrg(Org org) {
        return orgMapper.updateOrg(org);
    }

    @Override
    public int deleteOrg(Org org) {
        return orgMapper.deleteOrg(org);
    }

    @Override
    public int addOrg(Org org) {
        return orgMapper.addOrg(org);
    }

    @Override
    public List<Org> getChildOrg(int id) {
        return orgMapper.getChildOrg(id);
    }

    @Override
    public List<OrgAndUserTree> getOrgAndUserTree() {
        return orgMapper.getOrgAndUserTree();
    }
}
