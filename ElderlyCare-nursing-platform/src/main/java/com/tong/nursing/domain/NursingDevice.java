package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备对象 nursing_device
 *
 * @author Tong
 * @date 2026-06-27
 */
@Data
@ApiModel(value = "NursingDevice", description = "设备实体")
public class NursingDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    @ApiModelProperty("设备ID")
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @ApiModelProperty("设备编号")
    private String deviceNo;

    /** 设备名称 */
    @Excel(name = "设备名称")
    @ApiModelProperty("设备名称")
    private String deviceName;

    /** 设备类型(0:智能床, 1:呼叫器, 2:监测设备, 3:其他) */
    @Excel(name = "设备类型(0:智能床, 1:呼叫器, 2:监测设备, 3:其他)")
    @ApiModelProperty("设备类型(0:智能床, 1:呼叫器, 2:监测设备, 3:其他)")
    private Integer deviceType;

    /** 绑定床位ID */
    @Excel(name = "绑定床位ID")
    @ApiModelProperty("绑定床位ID")
    private Long bedId;

    /** 状态(0:离线, 1:在线, 2:故障) */
    @Excel(name = "状态(0:离线, 1:在线, 2:故障)")
    @ApiModelProperty("状态(0:离线, 1:在线, 2:故障)")
    private Integer status;

    /** 床位编号（查询用） */
    @ApiModelProperty("床位编号")
    private String bedNo;

    /** 房间ID（查询用） */
    @ApiModelProperty("房间ID")
    private Long roomId;

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
