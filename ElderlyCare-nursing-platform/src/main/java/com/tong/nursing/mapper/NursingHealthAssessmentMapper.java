package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingHealthAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 健康评估Mapper接口
 *
 * @author Tong
 * @date 2026-06-28
 */
@Mapper
public interface NursingHealthAssessmentMapper
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
     * 删除健康评估
     *
     * @param id 健康评估主键
     * @return 结果
     */
    public int deleteNursingHealthAssessmentById(Long id);

    /**
     * 批量删除健康评估
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingHealthAssessmentByIds(Long[] ids);
}
