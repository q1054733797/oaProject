package com.project.oa.base.mapper;

import com.project.oa.base.bean.Vacate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: VacateMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:48
 * @Version: 1.0
 */
public interface VacateMapper {
    @Select("select a.*,b.name userName from t_vacate_apply a " +
            "left join t_user b on a.applyUserId = b.id " +
            "where processInstId = #{processInstId}")
    Vacate getVacateByProcessInstId(String processInstId);

    @Delete("delete from t_vacate_apply where id = #{id}")
    int removeVacate(Vacate vacate);

    @Update("update t_vacate_apply set " +
            "startTime = #{startTime}," +
            "dayNum = #{dayNum}," +
            "reason = #{reason}," +
            "applyTime = #{applyTime}," +
            "processInstId = #{processInstId}," +
            "processStatus = #{processStatus} " +
            "where id = #{id}")
    int updateVacate(Vacate vacate);

    @Insert("insert into t_vacate_apply(startTime,dayNum,reason,applyUserId) " +
            "values(#{startTime},#{dayNum},#{reason},#{applyUserId})")
    int addVacate(Vacate vacate);

    @Select("select * from t_vacate_apply where applyUserId = #{applyUserId}")
    List<Vacate> getMyVacateList(int applyUserId);
}
