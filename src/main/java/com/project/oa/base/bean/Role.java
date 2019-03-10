package com.project.oa.base.bean;

/**
 * @ClassName: Role
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:08
 * @Version: 1.0
 */
public class Role {
    private String id;
    private String code;
    private String name;
    private String description;

    public Role(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
