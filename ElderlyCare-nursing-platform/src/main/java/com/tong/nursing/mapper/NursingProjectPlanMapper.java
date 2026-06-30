package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingProjectPlan;

/**
 * 护理项目计划Mapper接口
 * 
 * @author ruoyi
 * @date 2026-06-25
 */
public interface NursingProjectPlanMapper 
{
    /**
     * 查询护理项目计划
     * 
     * @param id 护理项目计划主键
     * @return 护理项目计划
     */
    public NursingProjectPlan selectNursingProjectPlanById(Integer id);

    /**
     * 查询护理项目计划列表
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 护理项目计划集合
     */
    public List<NursingProjectPlan> selectNursingProjectPlanList(NursingProjectPlan nursingProjectPlan);

    /**
     * 新增护理项目计划
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 结果
     */
    public int insertNursingProjectPlan(NursingProjectPlan nursingProjectPlan);

    /**
     * 修改护理项目计划
     * 
     * @param nursingProjectPlan 护理项目计划
     * @return 结果
     */
    public int updateNursingProjectPlan(NursingProjectPlan nursingProjectPlan);

    /**
     * 删除护理项目计划
     * 
     * @param id 护理项目计划主键
     * @return 结果
     */
    public int deleteNursingProjectPlanById(Integer id);

    /**
     * 批量删除护理项目计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingProjectPlanByIds(Integer[] ids);

    /**
     * 根据计划ID删除护理项目计划关联
     *
     * @param planId 计划ID
     * @return 结果
     */
    public int deleteNursingProjectPlanByPlanId(Long planId);

    /**
     * 批量根据计划ID删除护理项目计划关联
     *
     * @param planIds 计划ID数组
     * @return 结果
     */
    public int deleteNursingProjectPlanByPlanIds(Long[] planIds);

    /**
     * 根据计划ID查询项目计划关联
     *
     * @param planId 计划ID
     * @return 项目计划关联列表
     */
    public List<NursingProjectPlan> selectNursingProjectPlanByPlanId(Long planId);

    /**
     * 批量插入项目计划关联
     *
     * @param projectPlans 项目计划关联列表
     * @return 结果
     */
    public int batchInsertNursingProjectPlan(List<NursingProjectPlan> projectPlans);
}
