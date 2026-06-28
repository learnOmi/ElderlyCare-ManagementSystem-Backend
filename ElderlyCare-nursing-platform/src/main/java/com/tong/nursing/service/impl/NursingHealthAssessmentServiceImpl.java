package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.exception.base.BaseException;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingHealthAssessmentMapper;
import com.tong.nursing.domain.NursingHealthAssessment;
import com.tong.nursing.service.INursingHealthAssessmentService;
import com.tong.nursing.constant.NursingConstants;

/**
 * 健康评估Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingHealthAssessmentServiceImpl implements INursingHealthAssessmentService
{
    @Autowired
    private NursingHealthAssessmentMapper nursingHealthAssessmentMapper;

    /**
     * 查询健康评估
     *
     * @param id 健康评估主键
     * @return 健康评估
     */
    @Override
    public NursingHealthAssessment selectNursingHealthAssessmentById(Long id)
    {
        return nursingHealthAssessmentMapper.selectNursingHealthAssessmentById(id);
    }

    /**
     * 查询健康评估列表
     *
     * @param nursingHealthAssessment 健康评估
     * @return 健康评估
     */
    @Override
    public List<NursingHealthAssessment> selectNursingHealthAssessmentList(NursingHealthAssessment nursingHealthAssessment)
    {
        return nursingHealthAssessmentMapper.selectNursingHealthAssessmentList(nursingHealthAssessment);
    }

    /**
     * 新增健康评估
     *
     * @param nursingHealthAssessment 健康评估
     * @return 结果
     */
    @Override
    public int insertNursingHealthAssessment(NursingHealthAssessment nursingHealthAssessment)
    {
        // 自动计算综合评分
        calculateTotalScore(nursingHealthAssessment);
        // 校验各维度分数范围
        validateScores(nursingHealthAssessment);
        // 自动生成建议护理等级
        if (nursingHealthAssessment.getSuggestedLevel() == null)
        {
            nursingHealthAssessment.setSuggestedLevel(recommendLevel(nursingHealthAssessment.getTotalScore()));
        }
        nursingHealthAssessment.setCreateTime(DateUtils.getNowDate());
        return nursingHealthAssessmentMapper.insertNursingHealthAssessment(nursingHealthAssessment);
    }

    /**
     * 修改健康评估
     *
     * @param nursingHealthAssessment 健康评估
     * @return 结果
     */
    @Override
    public int updateNursingHealthAssessment(NursingHealthAssessment nursingHealthAssessment)
    {
        // 自动计算综合评分
        calculateTotalScore(nursingHealthAssessment);
        // 校验各维度分数范围
        validateScores(nursingHealthAssessment);
        nursingHealthAssessment.setUpdateTime(DateUtils.getNowDate());
        return nursingHealthAssessmentMapper.updateNursingHealthAssessment(nursingHealthAssessment);
    }

    /**
     * 批量删除健康评估
     *
     * @param ids 需要删除的健康评估主键
     * @return 结果
     */
    @Override
    public int deleteNursingHealthAssessmentByIds(Long[] ids)
    {
        return nursingHealthAssessmentMapper.deleteNursingHealthAssessmentByIds(ids);
    }

    /**
     * 删除健康评估信息
     *
     * @param id 健康评估主键
     * @return 结果
     */
    @Override
    public int deleteNursingHealthAssessmentById(Long id)
    {
        return nursingHealthAssessmentMapper.deleteNursingHealthAssessmentById(id);
    }

    /**
     * 计算综合评分
     */
    private void calculateTotalScore(NursingHealthAssessment assessment)
    {
        int total = 0;
        if (assessment.getPhysicalScore() != null)
            total += assessment.getPhysicalScore();
        if (assessment.getMentalScore() != null)
            total += assessment.getMentalScore();
        if (assessment.getDailyScore() != null)
            total += assessment.getDailyScore();
        if (assessment.getSocialScore() != null)
            total += assessment.getSocialScore();
        assessment.setTotalScore(total);
    }

    /**
     * 校验各维度分数范围（0~100）
     */
    private void validateScores(NursingHealthAssessment assessment)
    {
        Integer[] scores = { assessment.getPhysicalScore(), assessment.getMentalScore(),
                             assessment.getDailyScore(), assessment.getSocialScore() };
        for (Integer score : scores)
        {
            if (score != null && (score < 0 || score > 100))
            {
                throw new BaseException("评分必须在0~100之间");
            }
        }
    }

    /**
     * 根据综合评分推荐护理等级
     * 0~40: 自理, 41~70: 半自理, 71~100: 不能自理
     *
     * @param totalScore 综合评分
     * @return 建议的护理等级ID
     */
    private Long recommendLevel(Integer totalScore)
    {
        if (totalScore == null)
            return null;
        // TODO: 后续可扩展为根据评分区间匹配护理等级的配置化规则
        // 目前返回 null，由前端或管理员手动指定
        return null;
    }
}
