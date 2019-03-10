package com.project.oa.base.service.serviceImpl;

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
    public List<OrgAndUserTree> getOrgAndUserTree() {
        return orgMapper.getOrgAndUserTree();
    }
}
