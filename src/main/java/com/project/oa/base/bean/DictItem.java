package com.project.oa.base.bean;

/**
 * @ClassName: DictItem
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 10:35
 * @Version: 1.0
 */
public class DictItem {
    private String id;
    private String name;
    private String code;
    private String dict_code;
    private String sort;

    public DictItem(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDict_code() {
        return dict_code;
    }

    public void setDict_code(String dict_code) {
        this.dict_code = dict_code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
