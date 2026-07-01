package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 告警数据对象 nursing_alert_data
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "NursingAlertData", description = "告警数据实体")
public class NursingAlertData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 告警ID */
    @ApiModelProperty("告警ID")
    private Long id;

    /** 告警编号 */
    @Excel(name = "告警编号")
    @ApiModelProperty("告警编号")
    private String alertNo;

    /** 老人ID */
    @Excel(name = "老人ID")
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 床位ID */
    @Excel(name = "床位ID")
    @ApiModelProperty("床位ID")
    private Long bedId;

    /** 设备ID */
    @Excel(name = "设备ID")
    @ApiModelProperty("设备ID")
    private Long deviceId;

    /** 告警规则ID */
    @Excel(name = "告警规则ID")
    @ApiModelProperty("告警规则ID")
    private Long ruleId;

    /** 告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他) */
    @Excel(name = "告警类型", readConverterExp = "0=离床,1=坠床,2=心率异常,3=呼吸异常,4=其他")
    @ApiModelProperty("告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他)")
    private Integer alertType;

    /** 告警级别(0:一般, 1:重要, 2:紧急) */
    @Excel(name = "告警级别", readConverterExp = "0=一般,1=重要,2=紧急")
    @ApiModelProperty("告警级别(0:一般, 1:重要, 2:紧急)")
    private Integer level;

    /** 告警值 */
    @Excel(name = "告警值")
    @ApiModelProperty("告警值")
    private String alertValue;

    /** 告警时间 */
    @Excel(name = "告警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("告警时间")
    private Date alertTime;

    /** 处理时间 */
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("处理时间")
    private Date handleTime;

    /** 处理人 */
    @Excel(name = "处理人")
    @ApiModelProperty("处理人")
    private String handleBy;

    /** 处理结果 */
    @Excel(name = "处理结果")
    @ApiModelProperty("处理结果")
    private String handleResult;

    /** 状态(0:未处理, 1:处理中, 2:已处理, 3:已忽略) */
    @Excel(name = "状态", readConverterExp = "0=未处理,1=处理中,2=已处理,3=已忽略")
    @ApiModelProperty("状态(0:未处理, 1:处理中, 2:已处理, 3:已忽略)")
    private Integer status;

    /** 老人姓名（查询用） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 告警类型名称（查询用） */
    @ApiModelProperty("告警类型名称")
    private String alertTypeName;

    /** 告警级别名称（查询用） */
    @ApiModelProperty("告警级别名称")
    private String levelName;

    /** 处理人姓名（查询用） */
    @ApiModelProperty("处理人姓名")
    private String handlerName;

    /** 告警内容 */
    @ApiModelProperty("告警内容")
    private String alertContent;

    /** 监测值 */
    @ApiModelProperty("监测值")
    private String monitorValue;

    /** 单位 */
    @ApiModelProperty("单位")
    private String unit;
}
