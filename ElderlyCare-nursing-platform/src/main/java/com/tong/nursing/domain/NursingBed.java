package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 床位对象 nursing_bed
 *
 * @author Tong
 * @date 2026-06-27
 */
@Data
@ApiModel(value = "NursingBed", description = "床位实体")
public class NursingBed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 床位ID */
    @ApiModelProperty("床位ID")
    private Long id;

    /** 床位编号 */
    @Excel(name = "床位编号")
    @ApiModelProperty("床位编号")
    private String bedNo;

    /** 所属房间ID */
    @Excel(name = "所属房间ID")
    @ApiModelProperty("所属房间ID")
    private Long roomId;

    /** 床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订) */
    @Excel(name = "床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订)")
    @ApiModelProperty("床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订)")
    private Integer bedStatus;

    /** 设备绑定(0:未绑定, 1:已绑定) */
    @Excel(name = "设备绑定(0:未绑定, 1:已绑定)")
    @ApiModelProperty("设备绑定(0:未绑定, 1:已绑定)")
    private Integer deviceBind;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @ApiModelProperty("设备编号")
    private String deviceNo;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态(0:禁用, 1:启用)")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 房间编号（查询用） */
    @ApiModelProperty("房间编号")
    private String roomNo;

    /** 楼层ID（查询用） */
    @ApiModelProperty("楼层ID")
    private Long floorId;

    /** 楼层名称（查询用） */
    @ApiModelProperty("楼层名称")
    private String floorName;
}
