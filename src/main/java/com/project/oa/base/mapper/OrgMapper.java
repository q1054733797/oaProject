package com.project.oa.base.mapper;

import com.project.oa.base.bean.Org;
import com.project.oa.base.bean.OrgAndUserTree;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: OrgMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:17
 * @Version: 1.0
 */
public interface OrgMapper {
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

    @Select("select * from t_org where parentId = #{id}")
    List<Org> getChildOrg(int id);

    @Select("select id orgId,name,null userId,parentId,iconCls from t_org " +
            "union " +
            "select null orgId,a.name,a.id userId,a.orgId parentId,a.iconCls from t_user a join t_org b on a.orgId = b.id")
    List<OrgAndUserTree> getOrgAndUserTree();
}
