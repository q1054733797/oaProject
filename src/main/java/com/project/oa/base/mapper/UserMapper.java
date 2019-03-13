package com.project.oa.base.mapper;

import com.project.oa.base.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/11 17:06
 * @Version: 1.0
 */
public interface UserMapper {
    @SelectProvider(type = UserProvider.class,method = "getUser")
    List<User> getUser(User user);

    @Delete("delete from t_user where orgId = #{orgId}")
    int deleteUserByOrgId(int orgId);

    @Select("select * from t_user where id = #{id}")
    User getUserById(int id);

    @Delete("delete from t_user where id = #{id}")
    int deleteUser(User user);

    @Update("update t_user set " +
            "name = #{name}," +
            "username = #{username}," +
            "password = #{password}," +
            "sex = #{sex}," +
            "status = #{status}," +
            "birthday = #{birthday}," +
            "cardType = #{cardType}," +
            "cardCode = #{cardCode}," +
            "inDate = #{inDate}," +
            "outDate = #{outDate}," +
            "orgId = #{orgId}," +
            "phone = #{phone} " +
            "where id = #{id}")
    int updateUser(User user);

    @SelectProvider(type = UserProvider.class,method = "getUserByOrgId")
    List<User> getUserByOrgId(User user);

    @Insert("insert into t_user(name,username,password,sex,status,birthday,cardType,cardCode,inDate,outDate,orgId,phone) " +
            "values(#{name},#{username},#{password},#{sex},#{status},#{birthday},#{cardType},#{cardCode},#{inDate},#{outDate},#{orgId},#{phone})")
    int addUser(User user);

    class UserProvider{
        public String getUser(User user){
            SQL sql = new SQL();
            sql.FROM("t_user a");
            sql.SELECT("a.*");
            if(user.getUsername() != null && user.getUsername() != ""){
                sql.WHERE("username like concat('%',#{username},'%')");
            }
            if(user.getName() != null && user.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            if(user.getOrgId() != null && user.getOrgId() != ""){
                sql.WHERE("orgId = #{orgId}");
            }
            if(user.getRoleId() != null && user.getRoleId() != ""){
                sql.LEFT_OUTER_JOIN("t_user_role b on a.id = b.user_id and b.role_id = #{roleId}");
                sql.SELECT("case when b.role_id is not null then 'y' else 'n' end ownRole");
            }
            return sql.toString();
        }

        public String getUserByOrgId(User user){
            SQL sql = new SQL();
            sql.FROM("t_user");
            sql.SELECT("*");
            sql.WHERE("orgId = #{orgId}");
            if(user.getName() != null && user.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            if(user.getUsername() != null && user.getUsername() != ""){
                sql.WHERE("username like concat('%',#{username},'%')");
            }
            return sql.toString();
        }
    }
}
