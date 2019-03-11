package com.project.oa.base.mapper;

import com.project.oa.base.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/11 17:06
 * @Version: 1.0
 */
public interface UserMapper {
    @Select("select * from t_user where orgId = #{orgId}")
    List<User> getUserByOrgId(String orgId);

    @Insert("insert into t_user(name,username,password,sex,status,birthday,cardType,cardCode,inDate,outData,orgId,phone) " +
            "values(#{name},#{username},#{password},#{sex},#{status},#{birthday},#{cardType},#{cardCode},#{inDate},#{outDate},#{orgId},#{phone})")
    int addUser(User user);
}
