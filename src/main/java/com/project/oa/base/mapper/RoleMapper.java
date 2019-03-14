package com.project.oa.base.mapper;

import com.project.oa.base.bean.Role;
import com.project.oa.base.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: RoleMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 9:13
 * @Version: 1.0
 */
public interface RoleMapper {
    @Insert("insert into t_menu_role(menu_id,role_id,checked) values(#{menuId},#{roleId},#{checked})")
    int addMenuRole(@Param("menuId") int menuId,@Param("roleId") int roleId,@Param("checked") String checked);

    @Delete("delete from t_menu_role where role_id = #{roleId}")
    int deleteMenuRoleByRoleId(int roleId);

    @Select("select * from t_user_role where user_id = #{userId} and role_id = #{roleId}")
    List<HashMap> userOwnRole(@Param("userId") int userId,@Param("roleId")int roleId);

    @Delete("delete from t_user_role where role_id = #{roleId}")
    int cancelRoleByRoleId(int roleId);

    @Delete("delete from t_user_role where user_id = #{userId}")
    int cancelRoleByUserId(int userId);

    @Delete("delete from t_user_role where role_id = #{roleId} and user_id = #{userId}")
    int cancelRole(@Param("roleId") int roleId,@Param("userId") int userId);

    @Insert("insert into t_user_role(user_id,role_id) values(#{userId},#{roleId})")
    int grantRole(@Param("roleId") int roleId,@Param("userId") int userId);

    @Select("select a.* from t_role a " +
            "inner join t_user_role b on a.id = b.role_id and b.user_id = #{id}")
    List<Role> getUserOwnRole(User user);

    @Select("select a.* from t_role a where a.id not in (select role_id from t_user_role where user_id = #{id})")
    List<Role> getUserNoRole(User user);

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
