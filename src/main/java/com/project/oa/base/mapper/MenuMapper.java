package com.project.oa.base.mapper;

import com.project.oa.base.bean.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: MenuMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/5 15:29
 * @Version: 1.0
 */
@Mapper
public interface MenuMapper {
    @Delete("delete from t_menu where id = #{id}")
    int deleteMenu(int id);

    @Select("select * from t_menu where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "code",property = "code"),
            @Result(column = "text",property = "text"),
            @Result(column = "sort",property = "sort"),
            @Result(column = "isLeaf",property = "isLeaf"),
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
            "isLeaf = #{isLeaf}," +
            "parentId = #{parentId}," +
            "url = #{url}," +
            "iconCls = #{iconCls}," +
            "level = #{level} " +
            "where id = #{id}")
    int updateMenu(Menu menu);

    @Insert("insert into t_menu(name,code,text,sort,isLeaf,parentId,url,iconCls,level) " +
            "values(#{name},#{code},#{text},#{sort},#{isLeaf},#{parentId},#{url},#{iconCls},#{level})")
    int addMenu(Menu menu);

    @Select("select * from t_menu where parentId is null")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "code",property = "code"),
            @Result(column = "text",property = "text"),
            @Result(column = "sort",property = "sort"),
            @Result(column = "isLeaf",property = "isLeaf"),
            @Result(column = "url",property = "url"),
            @Result(column = "iconCls",property = "iconCls"),
            @Result(column = "id",property = "children",many = @Many(
                    select = "getChildrenMenu"
            ))
    })
    List<Menu> getMenu();

    @Select("select * from t_menu where parentId = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "code",property = "code"),
            @Result(column = "text",property = "text"),
            @Result(column = "sort",property = "sort"),
            @Result(column = "isLeaf",property = "isLeaf"),
            @Result(column = "url",property = "url"),
            @Result(column = "iconCls",property = "iconCls"),
            @Result(column = "id",property = "children",many = @Many(
                    select = "getChildrenMenu"
            ))
    })
    List<Menu> getChildrenMenu(int id);
}
