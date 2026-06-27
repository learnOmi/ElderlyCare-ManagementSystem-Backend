package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入住申请列表视图对象
 * 用于列表接口，返回入住申请基本信息及老人、护理等级等联动信息
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingCheckInListVO", description = "入住申请列表视图对象")
public class NursingCheckInListVO {

    /** 入住ID */
    @ApiModelProperty("入住ID")
    private Long id;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 入住床位ID */
    @ApiModelProperty("入住床位ID")
    private Long bedId;

    /** 护理等级ID */
    @ApiModelProperty("护理等级ID")
    private Long levelId;

    /** 护理计划ID */
    @ApiModelProperty("护理计划ID")
    private Long planId;

    /** 申请日期 */
    @ApiModelProperty("申请日期")
    private Date applyDate;

    /** 入住日期 */
    @ApiModelProperty("入住日期")
    private Date checkInDate;

    /** 预计退住日期 */
    @ApiModelProperty("预计退住日期")
    private Date expectedCheckOutDate;

    /** 实际退住日期 */
    @ApiModelProperty("实际退住日期")
    private Date checkOutDate;

    /** 状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝) */
    @ApiModelProperty("状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝)")
    private Integer status;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private String auditBy;

    /** 审核时间 */
    @ApiModelProperty("审核时间")
    private Date auditTime;

    /** 审核意见 */
    @ApiModelProperty("审核意见")
    private String auditRemark;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** ========== 老人信息 ========== */
    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 老人性别(0:男, 1:女) */
    @ApiModelProperty("老人性别(0:男, 1:女)")
    private Integer elderGender;

    /** 老人年龄 */
    @ApiModelProperty("老人年龄")
    private Integer elderAge;

    /** 老人电话 */
    @ApiModelProperty("老人电话")
    private String elderPhone;

    /** 老人身份证号 */
    @ApiModelProperty("老人身份证号")
    private String elderIdCard;

    /** 老人入住状态(0:未入住, 1:已入住, 2:已退住) */
    @ApiModelProperty("老人入住状态(0:未入住, 1:已入住, 2:已退住)")
    private Integer elderCheckInStatus;

    /** 老人健康状态(0:自理, 1:半自理, 2:不能自理) */
    @ApiModelProperty("老人健康状态(0:自理, 1:半自理, 2:不能自理)")
    private Integer elderHealthStatus;

    /** ========== 床位信息 ========== */
    /** 床位名称 */
    @ApiModelProperty("床位名称")
    private String bedName;

    /** ========== 护理等级信息 ========== */
    /** 护理等级名称 */
    @ApiModelProperty("护理等级名称")
    private String levelName;

    /** 护理等级费用 */
    @ApiModelProperty("护理等级费用")
    private BigDecimal levelFee;

    /** 护理等级描述 */
    @ApiModelProperty("护理等级描述")
    private String levelDescription;
}
