package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingAlertRuleMapper;
import com.tong.nursing.domain.NursingAlertRule;
import com.tong.nursing.service.INursingAlertRuleService;

/**
 * 告警规则Service业务层处理
 *
 * @author Tong
 * @date 2026-06-29
 */
@Service
public class NursingAlertRuleServiceImpl implements INursingAlertRuleService
{
    @Autowired
    private NursingAlertRuleMapper nursingAlertRuleMapper;

    /**
     * 查询告警规则
     *
     * @param id 告警规则主键
     * @return 告警规则
     */
    @Override
    public NursingAlertRule selectNursingAlertRuleById(Long id)
    {
        return nursingAlertRuleMapper.selectNursingAlertRuleById(id);
    }

    /**
     * 查询告警规则列表
     *
     * @param nursingAlertRule 告警规则
     * @return 告警规则
     */
    @Override
    public List<NursingAlertRule> selectNursingAlertRuleList(NursingAlertRule nursingAlertRule)
    {
        return nursingAlertRuleMapper.selectNursingAlertRuleList(nursingAlertRule);
    }

    /**
     * 新增告警规则
     *
     * @param nursingAlertRule 告警规则
     * @return 结果
     */
    @Override
    public int insertNursingAlertRule(NursingAlertRule nursingAlertRule)
    {
        nursingAlertRule.setCreateTime(DateUtils.getNowDate());
        return nursingAlertRuleMapper.insertNursingAlertRule(nursingAlertRule);
    }

    /**
     * 修改告警规则
     *
     * @param nursingAlertRule 告警规则
     * @return 结果
     */
    @Override
    public int updateNursingAlertRule(NursingAlertRule nursingAlertRule)
    {
        nursingAlertRule.setUpdateTime(DateUtils.getNowDate());
        return nursingAlertRuleMapper.updateNursingAlertRule(nursingAlertRule);
    }

    /**
     * 批量删除告警规则
     *
     * @param ids 需要删除的告警规则主键
     * @return 结果
     */
    @Override
    public int deleteNursingAlertRuleByIds(Long[] ids)
    {
        return nursingAlertRuleMapper.deleteNursingAlertRuleByIds(ids);
    }

    /**
     * 删除告警规则信息
     *
     * @param id 告警规则主键
     * @return 结果
     */
    @Override
    public int deleteNursingAlertRuleById(Long id)
    {
        return nursingAlertRuleMapper.deleteNursingAlertRuleById(id);
    }
}
