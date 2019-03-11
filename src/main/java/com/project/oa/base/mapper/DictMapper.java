package com.project.oa.base.mapper;

import com.project.oa.base.bean.Dict;
import com.project.oa.base.bean.DictItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: DictMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/7 10:37
 * @Version: 1.0
 */
@Mapper
public interface DictMapper {

    @Delete("delete from t_dict where id = #{id}")
    int deleteDict(Dict dict);

    @Update("update t_dict set " +
            "name = #{name}," +
            "code = #{code} " +
            "where id = #{id}")
    int updateDict(Dict dict);

    @Insert("insert into t_dict(name,code)" +
            "values(#{name},#{code})")
    int addDict(Dict dict);

    @SelectProvider(type = DictProvider.class,method = "getDict")
    List<Dict> getDict(Dict dict);

    class DictProvider{
        public String getDict(Dict dict){
            SQL sql = new SQL();
            sql.FROM("t_dict");
            sql.SELECT("*");
            if(dict.getCode() != null && dict.getCode() != ""){
                sql.WHERE("code = #{code}");
            }
            if(dict.getName() != null && dict.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            return sql.toString();
        }
    }
}
