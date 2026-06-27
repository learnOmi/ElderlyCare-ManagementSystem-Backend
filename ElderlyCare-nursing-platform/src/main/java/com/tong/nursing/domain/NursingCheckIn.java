package com.tong.nursing.domain;

import java.util.Date;
import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 入住申请对象 nursing_check_in
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingCheckIn", description = "入住申请实体")
public class NursingCheckIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("申请日期")
    private Date applyDate;

    /** 入住日期 */
    @Excel(name = "入住日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("入住日期")
    private Date checkInDate;

    /** 预计退住日期 */
    @Excel(name = "预计退住日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("预计退住日期")
    private Date expectedCheckOutDate;

    /** 实际退住日期 */
    @Excel(name = "实际退住日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("实际退住日期")
    private Date checkOutDate;

    /** 状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝) */
    @Excel(name = "状态", readConverterExp = "0=待审核,1=已审核,2=已入住,3=已退住,4=已拒绝")
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

    /** 老人姓名（非数据库字段） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 床位名称（非数据库字段） */
    @ApiModelProperty("床位名称")
    private String bedName;

    /** 护理等级名称（非数据库字段） */
    @ApiModelProperty("护理等级名称")
    private String levelName;
}
