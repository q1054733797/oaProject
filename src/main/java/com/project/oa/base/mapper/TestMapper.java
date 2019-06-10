package com.project.oa.base.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.HashMap;

/**
 * @ClassName: TestMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/6/10 14:20
 * @Version: 1.0
 */
public interface TestMapper {
    @Insert("insert into t_test(name,username,password,birthday) " +
            "values(#{name},#{username},#{password},#{birthday})")
    int addTest(HashMap<String,Object> map);
}
