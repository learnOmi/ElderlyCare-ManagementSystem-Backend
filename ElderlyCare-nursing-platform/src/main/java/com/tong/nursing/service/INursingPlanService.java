package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingPlan;
import com.tong.nursing.dto.NursingPlanDTO;
import com.tong.nursing.vo.NursingPlanVO;

/**
 * 护理计划Service接口
 *
 * @author Tong
 * @date 2026-06-25
 */
public interface INursingPlanService
{
    /**
     * 查询护理计划
     *
     * @param id 护理计划主键
     * @return 护理计划
     */
    public NursingPlan selectNursingPlanById(Long id);

    /**
     * 查询护理计划详情（含项目列表）
     *
     * @param id 护理计划主键
     * @return 护理计划详情（含项目列表）
     */
    public NursingPlanVO selectNursingPlanVOById(Long id);

    /**
     * 查询护理计划列表
     *
     * @param nursingPlan 护理计划
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan);

    /**
     * 查询全部护理计划（下拉框/全量列表用）
     *
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanAll();

    /**
     * 新增护理计划（含项目关联）
     *
     * @param dto 护理计划DTO
     * @return 结果
     */
    public int insertNursingPlan(NursingPlanDTO dto);

    /**
     * 修改护理计划（含项目关联）
     *
     * @param dto 护理计划DTO
     * @return 结果
     */
    public int updateNursingPlan(NursingPlanDTO dto);

    /**
     * 批量删除护理计划
     *
     * @param ids 需要删除的护理计划主键集合
     * @return 结果
     */
    public int deleteNursingPlanByIds(Long[] ids);

    /**
     * 删除护理计划信息
     *
     * @param id 护理计划主键
     * @return 结果
     */
    public int deleteNursingPlanById(Long id);
}
