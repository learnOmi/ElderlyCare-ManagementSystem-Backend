package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingProjectPlanMapper;
import com.tong.nursing.domain.NursingProjectPlan;
import com.tong.nursing.service.INursingProjectPlanService;

/**
 * 护理项目计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-06-25
 */
@Service
public class NursingProjectPlanServiceImpl implements INursingProjectPlanService 
{
    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    /**
     * 查询护理项目计划
     * 
     * @param id 护理项目计划主键
     * @return 护理项目计划
     */
    @Override
    public NursingProjectPlan selectNursingProjectPlanById(Integer id)
    {
        return nursingProjectPlanMapper.selectNursingProjectPlanById(id);
    }

    /**
     * 查询护理项目计划列表
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 护理项目计划
     */
    @Override
    public List<NursingProjectPlan> selectNursingProjectPlanList(NursingProjectPlan nursingProjectPlan)
    {
        return nursingProjectPlanMapper.selectNursingProjectPlanList(nursingProjectPlan);
    }

    /**
     * 新增护理项目计划
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 结果
     */
    @Override
    public int insertNursingProjectPlan(NursingProjectPlan nursingProjectPlan)
    {
        nursingProjectPlan.setCreateTime(DateUtils.getNowDate());
        return nursingProjectPlanMapper.insertNursingProjectPlan(nursingProjectPlan);
    }

    /**
     * 修改护理项目计划
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 结果
     */
    @Override
    public int updateNursingProjectPlan(NursingProjectPlan nursingProjectPlan)
    {
        nursingProjectPlan.setUpdateTime(DateUtils.getNowDate());
        return nursingProjectPlanMapper.updateNursingProjectPlan(nursingProjectPlan);
    }

    /**
     * 批量删除护理项目计划
     * 
     * @param ids 需要删除的护理项目计划主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectPlanByIds(Integer[] ids)
    {
        return nursingProjectPlanMapper.deleteNursingProjectPlanByIds(ids);
    }

    /**
     * 删除护理项目计划信息
     * 
     * @param id 护理项目计划主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectPlanById(Integer id)
    {
        return nursingProjectPlanMapper.deleteNursingProjectPlanById(id);
    }
}
