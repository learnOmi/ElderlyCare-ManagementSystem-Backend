package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingProjectPlan;

/**
 * 护理项目计划Service接口
 * 
 * @author ruoyi
 * @date 2026-06-25
 */
public interface INursingProjectPlanService 
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
     * 批量删除护理项目计划
     * 
     * @param ids 需要删除的护理项目计划主键集合
     * @return 结果
     */
    public int deleteNursingProjectPlanByIds(Integer[] ids);

    /**
     * 删除护理项目计划信息
     * 
     * @param id 护理项目计划主键
     * @return 结果
     */
    public int deleteNursingProjectPlanById(Integer id);
}
