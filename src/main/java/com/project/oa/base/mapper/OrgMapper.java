package com.project.oa.base.mapper;

import com.project.oa.base.bean.OrgAndUserTree;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: OrgMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:17
 * @Version: 1.0
 */
public interface OrgMapper {
    @Select("select id orgId,name,null userId,parentId,iconCls from t_org " +
            "union " +
            "select null orgId,a.name,a.id userId,a.orgId parentId,a.iconCls from t_user a join t_org b on a.orgId = b.id")
    List<OrgAndUserTree> getOrgAndUserTree();
}
