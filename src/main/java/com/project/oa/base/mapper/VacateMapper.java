package com.project.oa.base.mapper;

import com.project.oa.base.bean.Vacate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: VacateMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:48
 * @Version: 1.0
 */
public interface VacateMapper {
    @Insert("insert into t_vacate_apply(startTime,dayNum,reason,applyUserId) " +
            "values(#{startTime},#{dayNum},#{reason},#{applyUserId})")
    int addVacate(Vacate vacate);

    @Select("select * from t_vacate_apply where applyUserId = #{applyUserId}")
    List<Vacate> getMyVacateList(int applyUserId);
}
