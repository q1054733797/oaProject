package com.project.oa.base.service;

import com.project.oa.base.bean.Vacate;

import java.util.List;

/**
 * @ClassName: IVacateService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:49
 * @Version: 1.0
 */
public interface IVacateService {
    List getMyVacateList(int applyUserId);
    int addVacate(Vacate vacate);
    int updateVacate(Vacate vacate);
    int removeVacate(Vacate vacate);
    Vacate getVacateByProcessInstId(String processInstId);
}
