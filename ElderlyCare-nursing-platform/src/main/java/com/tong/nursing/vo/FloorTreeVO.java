package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 楼层树形视图对象
 * 包含楼层、房间（N）、床位（N）的三级嵌套结构
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "FloorTreeVO", description = "楼层树形视图对象")
public class FloorTreeVO
{
    /** 楼层ID */
    @ApiModelProperty("楼层ID")
    private Long id;

    /** 楼层名称 */
    @ApiModelProperty("楼层名称")
    private String name;

    /** 楼层编号 */
    @ApiModelProperty("楼层编号")
    private Integer floorNo;

    /** 状态(0:禁用, 1:启用) */
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 房间列表 */
    @ApiModelProperty("房间列表")
    private List<RoomVO> roomList;

    /**
     * 房间内部类
     */
    @Data
    @ApiModel(value = "RoomVO", description = "房间信息")
    public static class RoomVO
    {
        /** 房间ID */
        @ApiModelProperty("房间ID")
        private Long id;

        /** 房间编号 */
        @ApiModelProperty("房间编号")
        private String roomNo;

        /** 房型ID */
        @ApiModelProperty("房型ID")
        private Long roomTypeId;

        /** 房型名称 */
        @ApiModelProperty("房型名称")
        private String roomTypeName;

        /** 状态(0:禁用, 1:启用) */
        @ApiModelProperty("状态(0:禁用, 1:启用)")
        private Integer status;

        /** 床位列表 */
        @ApiModelProperty("床位列表")
        private List<BedVO> bedList;
    }

    /**
     * 床位内部类
     */
    @Data
    @ApiModel(value = "BedVO", description = "床位信息")
    public static class BedVO
    {
        /** 床位ID */
        @ApiModelProperty("床位ID")
        private Long id;

        /** 床位编号 */
        @ApiModelProperty("床位编号")
        private String bedNo;

        /** 床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订) */
        @ApiModelProperty("床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订)")
        private Integer bedStatus;

        /** 设备绑定(0:未绑定, 1:已绑定) */
        @ApiModelProperty("设备绑定(0:未绑定, 1:已绑定)")
        private Integer deviceBind;

        /** 设备编号 */
        @ApiModelProperty("设备编号")
        private String deviceNo;

        /** 状态(0:禁用, 1:启用) */
        @ApiModelProperty("状态(0:禁用, 1:启用)")
        private Integer status;
    }
}
