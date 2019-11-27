package com.project.oa.base.mapper;

import com.project.oa.base.bean.Menu;
import com.project.oa.base.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: MenuMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/5 15:29
 * @Version: 1.0
 */
public interface MenuMapper {
    @Select("select * from t_menu where parentId = #{menuId} and id in " +
            "(select distinct(menu_id) from t_menu_role a " +
            "inner join t_user_role b on a.role_id = b.role_id " +
            "inner join t_user c on b.user_id = c.id and c.id = #{userId})")
    List<Menu> getUserMenu(HashMap map);

    @Delete("delete from t_menu where id = #{id}")
    int deleteMenu(int id);

    @Select("select * from t_menu where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "code",property = "code"),
            @Result(column = "text",property = "text"),
            @Result(column = "sort",property = "sort"),
            @Result(column = "leaf",property = "leaf"),
            @Result(column = "url",property = "url"),
            @Result(column = "iconCls",property = "iconCls"),
            @Result(column = "id",property = "children",many = @Many(
                    select = "getChildrenMenu"
            ))
    })
    Menu getMenuById(int id);

    @Update("update t_menu set " +
            "name = #{name}," +
            "code = #{code}," +
            "text = #{text}," +
            "sort = #{sort}," +
            "leaf = #{leaf}," +
            "parentId = #{parentId}," +
            "url = #{url}," +
            "iconCls = #{iconCls}," +
            "level = #{level} " +
            "where id = #{id}")
    int updateMenu(Menu menu);

    @Insert("insert into t_menu(name,code,text,sort,leaf,parentId,url,iconCls,level) " +
            "values(#{name},#{code},#{text},#{sort},#{leaf},#{parentId},#{url},#{iconCls},#{level})")
    int addMenu(Menu menu);

    @SelectProvider(type = MenuProvider.class,method = "getMenu")
    Menu getRootMenu(Menu menu);

    @SelectProvider(type = MenuProvider.class,method = "getChildMenu")
    List<Menu> getChildrenMenu(Menu menu);

    class MenuProvider{
        public String getMenu(Menu menu){
            SQL sql = new SQL();
            sql.FROM("t_menu a");
            sql.SELECT("a.*");
            sql.WHERE("parentId is null");
            if(menu.getRoleId() != null && menu.getRoleId() != ""){
                sql.LEFT_OUTER_JOIN("t_menu_role b on a.id = b.menu_id and b.role_id = #{roleId}");
                sql.SELECT("b.role_id roleId,b.checked");
            }
            return sql.toString();
        }

        public String getChildMenu(Menu menu){
            SQL sql = new SQL();
            sql.FROM("t_menu a");
            sql.SELECT("a.*");
            sql.WHERE("parentId = #{id}");
            if(menu.getRoleId() != null && menu.getRoleId() != ""){
                sql.LEFT_OUTER_JOIN("t_menu_role b on a.id = b.menu_id and b.role_id = #{roleId}");
                sql.SELECT("b.role_id roleId,b.checked");
            }
            return sql.toString();
        }
    }
}
