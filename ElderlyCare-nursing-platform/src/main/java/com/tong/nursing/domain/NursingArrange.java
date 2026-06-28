package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 护理排班对象 nursing_arrange
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingArrange", description = "护理排班实体")
public class NursingArrange extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排班ID */
    @ApiModelProperty("排班ID")
    private Long id;

    /** 排班编号 */
    @Excel(name = "排班编号")
    @ApiModelProperty("排班编号")
    private String arrangeNo;

    /** 老人ID */
    @Excel(name = "老人ID")
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 护理项目ID */
    @Excel(name = "护理项目ID")
    @ApiModelProperty("护理项目ID")
    private Long projectId;

    /** 护理员ID */
    @Excel(name = "护理员ID")
    @ApiModelProperty("护理员ID")
    private Long nurseId;

    /** 计划日期 */
    @Excel(name = "计划日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("计划日期")
    private Date planDate;

    /** 计划时间 */
    @Excel(name = "计划时间")
    @ApiModelProperty("计划时间")
    private String planTime;

    /** 实际执行时间 */
    @ApiModelProperty("实际执行时间")
    private Date executeTime;

    /** 执行人 */
    @Excel(name = "执行人")
    @ApiModelProperty("执行人")
    private String executeBy;

    /** 状态(0:待执行, 1:已执行, 2:已取消, 3:执行中) */
    @Excel(name = "状态(0:待执行, 1:已执行, 2:已取消, 3:执行中)")
    @ApiModelProperty("状态(0:待执行, 1:已执行, 2:已取消, 3:执行中)")
    private Integer status;

    /** 取消原因 */
    @Excel(name = "取消原因")
    @ApiModelProperty("取消原因")
    private String cancelReason;

    /** 老人姓名（查询用） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 护理项目名称（查询用） */
    @ApiModelProperty("护理项目名称")
    private String projectName;

    /** 护理员姓名（查询用） */
    @ApiModelProperty("护理员姓名")
    private String nurseName;
}
