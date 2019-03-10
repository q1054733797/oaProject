package com.project.oa.base.bean;

/**
 * @ClassName: OrgAndUserTree
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:12
 * @Version: 1.0
 */
public class OrgAndUserTree {
    private String orgId;
    private String userId;
    private String name;
    private String parentId;
    private String iconCls;

    public OrgAndUserTree(){

    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
}
