package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 预约对象 nursing_reservation
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "NursingReservation", description = "预约实体")
public class NursingReservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    @ApiModelProperty("预约ID")
    private Long id;

    /** 预约编号 */
    @Excel(name = "预约编号")
    @ApiModelProperty("预约编号")
    private String reservationNo;

    /** 预约类型(0:来访预约, 1:入住预约, 2:参观预约) */
    @Excel(name = "预约类型", dictType = "nursing_reservation_type")
    @ApiModelProperty("预约类型(0:来访预约, 1:入住预约, 2:参观预约)")
    private Integer reservationType;

    /** 预约人姓名 */
    @Excel(name = "预约人姓名")
    @ApiModelProperty("预约人姓名")
    private String name;

    /** 联系电话 */
    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String phone;

    /** 老人姓名 */
    @Excel(name = "老人姓名")
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 预约日期 */
    @Excel(name = "预约日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("预约日期")
    private java.util.Date reservationDate;

    /** 预约时间 */
    @Excel(name = "预约时间")
    @ApiModelProperty("预约时间")
    private String reservationTime;

    /** 来访人数 */
    @Excel(name = "来访人数")
    @ApiModelProperty("来访人数")
    private Integer visitorCount;

    /** 状态(0:待确认, 1:已确认, 2:已完成, 3:已取消) */
    @Excel(name = "状态", readConverterExp = "0=待确认,1=已确认,2=已完成,3=已取消")
    @ApiModelProperty("状态(0:待确认, 1:已确认, 2:已完成, 3:已取消)")
    private Integer status;

    /** 取消原因 */
    @Excel(name = "取消原因")
    @ApiModelProperty("取消原因")
    private String cancelReason;
}
