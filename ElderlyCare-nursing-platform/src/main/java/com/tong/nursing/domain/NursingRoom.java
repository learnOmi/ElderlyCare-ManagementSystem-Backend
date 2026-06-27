package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间对象 nursing_room
 *
 * @author Tong
 * @date 2026-06-27
 */
@Data
@ApiModel(value = "NursingRoom", description = "房间实体")
public class NursingRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间ID */
    @ApiModelProperty("房间ID")
    private Long id;

    /** 房间编号 */
    @Excel(name = "房间编号")
    @ApiModelProperty("房间编号")
    private String roomNo;

    /** 所属楼层ID */
    @Excel(name = "所属楼层ID")
    @ApiModelProperty("所属楼层ID")
    private Long floorId;

    /** 房型ID */
    @Excel(name = "房型ID")
    @ApiModelProperty("房型ID")
    private Long roomTypeId;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态(0:禁用, 1:启用)")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 楼层名称（查询用） */
    @ApiModelProperty("楼层名称")
    private String floorName;

    /** 房型名称（查询用） */
    @ApiModelProperty("房型名称")
    private String roomTypeName;
}
