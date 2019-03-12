package com.project.oa.base.mapper;

import com.project.oa.base.bean.Org;
import com.project.oa.base.bean.OrgAndUserTree;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: OrgMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:17
 * @Version: 1.0
 */
public interface OrgMapper {
    @SelectProvider(type = OrgProvider.class,method = "getOrg")
    List<Org> getOrg(Org org);

    @Select("select * from t_org where id = #{id}")
    Org getOrgById(String id);

    @Delete("delete from t_org where id = #{id}")
    int deleteOrg(Org org);

    @Update("update t_org set " +
            "name = #{name}," +
            "code = #{code}," +
            "orgLevel = #{orgLevel}," +
            "type = #{type}," +
            "address = #{address}," +
            "parentId = #{parentId}," +
            "phone = #{phone}," +
            "postCode = #{postCode}," +
            "email = #{email}," +
            "webUrl = #{webUrl}," +
            "remark = #{remark}," +
            "linkman = #{linkman} " +
            "where id = #{id}")
    int updateOrg(Org org);

    @Insert("insert into t_org(name,code,orgLevel,type,address,parentId,phone,postCode,email,webUrl,remark,linkman) " +
            "values(#{name},#{code},#{orgLevel},#{type},#{address},#{parentId},#{phone},#{postCode},#{email},#{webUrl},#{remark},#{linkman})")
    int addOrg(Org org);

    @SelectProvider(type = OrgProvider.class,method = "getChildOrg")
    List<Org> getChildOrg(Org org);

    @Select("select id orgId,name,null userId,parentId,iconCls from t_org " +
            "union " +
            "select null orgId,a.name,a.id userId,a.orgId parentId,a.iconCls from t_user a join t_org b on a.orgId = b.id")
    List<OrgAndUserTree> getOrgAndUserTree();

    class OrgProvider{
        public String getChildOrg(Org org){
            SQL sql = new SQL();
            sql.FROM("t_org");
            sql.SELECT("*");
            sql.WHERE("parentId = #{id}");
            if(org.getCode() != null && org.getCode() != ""){
                sql.WHERE("code = #{code}");
            }
            if(org.getName() != null && org.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            return sql.toString();
        }

        public String getOrg(Org org){
            SQL sql = new SQL();
            sql.FROM("t_org");
            sql.SELECT("*");
            sql.WHERE("parentId is not null");
            if(org.getCode() != null && org.getCode() != ""){
                sql.WHERE("code = #{code}");
            }
            if(org.getName() != null && org.getName() != ""){
                sql.WHERE("name like concat('%',#{name},'%')");
            }
            if(org.getOrgLevel() != null && org.getOrgLevel() != ""){
                sql.WHERE("orgLevel = #{orgLevel}");
            }
            if(org.getType() != null && org.getType() != ""){
                sql.WHERE("type = #{type}");
            }
            return sql.toString();
        }
    }
}
