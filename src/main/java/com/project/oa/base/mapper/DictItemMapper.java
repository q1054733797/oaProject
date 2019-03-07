package com.project.oa.base.mapper;

import com.project.oa.base.bean.DictItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: DictItemMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 11:39
 * @Version: 1.0
 */
@Mapper
public interface DictItemMapper {
    @Select("select * from t_dict_item where dict_code = #{dict_code}")
    List<DictItem> getDictItemByDictCode(String dict_code);

    @Delete("delete from t_dict_item where id = #{id}")
    int deleteDictItem(DictItem dictItem);

    @Update("update t_dict_item set " +
            "name = #{name}," +
            "code = #{code}," +
            "dict_code = #{dict_code}," +
            "sort = #{sort} " +
            "where id = #{id}")
    int updateDictItem(DictItem dictItem);

    @Select("select * from t_dict_item where dict_code = #{dict_code}")
    List<DictItem> getDictItem(DictItem dictItem);

    @Insert("insert into t_dict_item(name,code,dict_code,sort) " +
            "values(#{name},#{code},#{dict_code},#{sort})")
    int addDictItem(DictItem dictItem);
}
