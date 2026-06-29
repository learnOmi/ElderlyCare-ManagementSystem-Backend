package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 告警规则对象 nursing_alert_rule
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "NursingAlertRule", description = "告警规则实体")
public class NursingAlertRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则ID */
    @ApiModelProperty("规则ID")
    private Long id;

    /** 规则名称 */
    @Excel(name = "规则名称")
    @ApiModelProperty("规则名称")
    private String ruleName;

    /** 告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他) */
    @Excel(name = "告警类型", readConverterExp = "0=离床,1=坠床,2=心率异常,3=呼吸异常,4=其他")
    @ApiModelProperty("告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他)")
    private Integer alertType;

    /** 告警阈值 */
    @Excel(name = "告警阈值")
    @ApiModelProperty("告警阈值")
    private String threshold;

    /** 告警级别(0:一般, 1:重要, 2:紧急) */
    @Excel(name = "告警级别", readConverterExp = "0=一般,1=重要,2=紧急")
    @ApiModelProperty("告警级别(0:一般, 1:重要, 2:紧急)")
    private Integer level;

    /** 通知方式(0:系统通知, 1:短信, 2:电话) */
    @Excel(name = "通知方式", readConverterExp = "0=系统通知,1=短信,2=电话")
    @ApiModelProperty("通知方式(0:系统通知, 1:短信, 2:电话)")
    private Integer notifyType;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态", readConverterExp = "0=禁用,1=启用")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;
}
