package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingHealthAssessment;

/**
 * 健康评估Service接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface INursingHealthAssessmentService
{
    /**
     * 查询健康评估
     *
     * @param id 健康评估主键
     * @return 健康评估
     */
    public NursingHealthAssessment selectNursingHealthAssessmentById(Long id);

    /**
     * 查询健康评估列表
     *
     * @param nursingHealthAssessment 健康评估
     * @return 健康评估集合
     */
    public List<NursingHealthAssessment> selectNursingHealthAssessmentList(NursingHealthAssessment nursingHealthAssessment);

    /**
     * 新增健康评估
     *
     * @param nursingHealthAssessment 健康评估
     * @return 结果
     */
    public int insertNursingHealthAssessment(NursingHealthAssessment nursingHealthAssessment);

    /**
     * 修改健康评估
     *
     * @param nursingHealthAssessment 健康评估
     * @return 结果
     */
    public int updateNursingHealthAssessment(NursingHealthAssessment nursingHealthAssessment);

    /**
     * 批量删除健康评估
     *
     * @param ids 需要删除的健康评估主键集合
     * @return 结果
     */
    public int deleteNursingHealthAssessmentByIds(Long[] ids);

    /**
     * 删除健康评估信息
     *
     * @param id 健康评估主键
     * @return 结果
     */
    public int deleteNursingHealthAssessmentById(Long id);
}
