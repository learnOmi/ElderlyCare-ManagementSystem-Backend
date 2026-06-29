package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingAlertRule;

/**
 * 告警规则Service接口
 *
 * @author Tong
 * @date 2026-06-29
 */
public interface INursingAlertRuleService
{
    /**
     * 查询告警规则
     *
     * @param id 告警规则主键
     * @return 告警规则
     */
    public NursingAlertRule selectNursingAlertRuleById(Long id);

    /**
     * 查询告警规则列表
     *
     * @param nursingAlertRule 告警规则
     * @return 告警规则集合
     */
    public List<NursingAlertRule> selectNursingAlertRuleList(NursingAlertRule nursingAlertRule);

    /**
     * 新增告警规则
     *
     * @param nursingAlertRule 告警规则
     * @return 结果
     */
    public int insertNursingAlertRule(NursingAlertRule nursingAlertRule);

    /**
     * 修改告警规则
     *
     * @param nursingAlertRule 告警规则
     * @return 结果
     */
    public int updateNursingAlertRule(NursingAlertRule nursingAlertRule);

    /**
     * 批量删除告警规则
     *
     * @param ids 需要删除的告警规则主键集合
     * @return 结果
     */
    public int deleteNursingAlertRuleByIds(Long[] ids);

    /**
     * 删除告警规则信息
     *
     * @param id 告警规则主键
     * @return 结果
     */
    public int deleteNursingAlertRuleById(Long id);
}
