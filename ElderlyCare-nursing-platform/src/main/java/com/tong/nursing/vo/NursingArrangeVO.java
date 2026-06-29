package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 护理排班视图对象
 * 包含老人姓名、护理项目名称、护理员姓名等展示字段
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "NursingArrangeVO", description = "护理排班视图对象")
public class NursingArrangeVO
{
    /** 排班ID */
    @ApiModelProperty("排班ID")
    private Long id;

    /** 排班编号 */
    @ApiModelProperty("排班编号")
    private String arrangeNo;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 护理项目ID */
    @ApiModelProperty("护理项目ID")
    private Long projectId;

    /** 护理项目名称 */
    @ApiModelProperty("护理项目名称")
    private String projectName;

    /** 护理员ID */
    @ApiModelProperty("护理员ID")
    private Long nurseId;

    /** 护理员姓名 */
    @ApiModelProperty("护理员姓名")
    private String nurseName;

    /** 计划日期 */
    @ApiModelProperty("计划日期")
    private Date planDate;

    /** 计划时间 */
    @ApiModelProperty("计划时间")
    private String planTime;

    /** 实际执行时间 */
    @ApiModelProperty("实际执行时间")
    private Date executeTime;

    /** 执行人 */
    @ApiModelProperty("执行人")
    private String executeBy;

    /** 状态(0:待执行, 1:已执行, 2:已取消, 3:执行中) */
    @ApiModelProperty("状态(0:待执行, 1:已执行, 2:已取消, 3:执行中)")
    private Integer status;

    /** 取消原因 */
    @ApiModelProperty("取消原因")
    private String cancelReason;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;
}
