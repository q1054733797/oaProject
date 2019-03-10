package com.project.oa.base.mapper;

import com.project.oa.base.bean.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: RoleMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:13
 * @Version: 1.0
 */
public interface RoleMapper {
    @Delete("delete from t_role where id = #{id}")
    int deleteRole(Role role);

    @Update("update t_role set " +
            "name = #{name}," +
            "code = #{code}," +
            "description = #{description} " +
            "where id = #{id}")
    int updateRole(Role role);

    @Insert("insert into t_role(name,code,description) " +
            "values(#{name},#{code},#{description})")
    int addRole(Role role);

    @SelectProvider(type = RoleProvider.class,method = "getRole")
    List<Role> getRole(Role role);

    class RoleProvider{
        public String getRole(Role role){
            SQL sql = new SQL();
            sql.FROM("t_role");
            sql.SELECT("*");
            if(role.getCode() != null && role.getCode() != ""){
                sql.WHERE("code = #{code}");
            }
            if(role.getName() != null && role.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            return sql.toString();
        }
    }
}
