package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 告警数据视图对象
 *
 * @author Tong
 * @date 2026-07-01
 */
@Data
@ApiModel(value = "NursingAlertDataVO", description = "告警数据视图对象")
public class NursingAlertDataVO {

    /** 告警ID */
    @ApiModelProperty("告警ID")
    private Long id;

    /** 告警编号 */
    @ApiModelProperty("告警编号")
    private String alertNo;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 床位ID */
    @ApiModelProperty("床位ID")
    private Long bedId;

    /** 床位编号 */
    @ApiModelProperty("床位编号")
    private String bedName;

    /** 设备ID */
    @ApiModelProperty("设备ID")
    private Long deviceId;

    /** 设备名称 */
    @ApiModelProperty("设备名称")
    private String deviceName;

    /** 告警规则ID */
    @ApiModelProperty("告警规则ID")
    private Long ruleId;

    /** 告警规则名称 */
    @ApiModelProperty("告警规则名称")
    private String ruleName;

    /** 告警类型 */
    @ApiModelProperty("告警类型")
    private Integer alertType;

    /** 告警级别 */
    @ApiModelProperty("告警级别")
    private Integer level;

    /** 告警值 */
    @ApiModelProperty("告警值")
    private String alertValue;

    /** 告警时间 */
    @ApiModelProperty("告警时间")
    private Date alertTime;

    /** 处理时间 */
    @ApiModelProperty("处理时间")
    private Date handleTime;

    /** 处理人 */
    @ApiModelProperty("处理人")
    private String handleBy;

    /** 处理人姓名 */
    @ApiModelProperty("处理人姓名")
    private String handlerName;

    /** 处理结果 */
    @ApiModelProperty("处理结果")
    private String handleResult;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 告警内容 */
    @ApiModelProperty("告警内容")
    private String alertContent;

    /** 监测值 */
    @ApiModelProperty("监测值")
    private String monitorValue;

    /** 单位 */
    @ApiModelProperty("单位")
    private String unit;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;
}