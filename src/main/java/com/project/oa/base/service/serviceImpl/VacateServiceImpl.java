package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Vacate;
import com.project.oa.base.mapper.VacateMapper;
import com.project.oa.base.service.IVacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: VacateServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:50
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VacateServiceImpl implements IVacateService {
    @Autowired
    private VacateMapper vacateMapper;

    @Override
    public int addVacate(Vacate vacate) {
        return vacateMapper.addVacate(vacate);
    }

    @Override
    public List getMyVacateList(int applyUserId) {
        return vacateMapper.getMyVacateList(applyUserId);
    }
}
