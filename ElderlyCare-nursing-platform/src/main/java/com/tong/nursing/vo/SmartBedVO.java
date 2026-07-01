package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 智能床位监控视图对象
 * 整合床位、设备、老人信息及实时监测数据
 *
 * @author Tong
 * @date 2026-07-02
 */
@Data
@ApiModel(value = "SmartBedVO", description = "智能床位监控视图对象")
public class SmartBedVO {

    /** 床位ID */
    @ApiModelProperty("床位ID")
    private Long id;

    /** 床位号 */
    @ApiModelProperty("床位号")
    private String bedNumber;

    /** 楼层ID */
    @ApiModelProperty("楼层ID")
    private Long floorId;

    /** 楼层名称 */
    @ApiModelProperty("楼层名称")
    private String floorName;

    /** 房间ID */
    @ApiModelProperty("房间ID")
    private Long roomId;

    /** 房间名称 */
    @ApiModelProperty("房间名称")
    private String roomName;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 设备ID */
    @ApiModelProperty("设备ID")
    private Long deviceId;

    /** 设备编号 */
    @ApiModelProperty("设备编号")
    private String deviceNo;

    /** 设备是否在线(0:离线, 1:在线) */
    @ApiModelProperty("设备是否在线")
    private Boolean deviceOnline;

    /** 床位监测状态(0:在床, 1:离床, 2:异常, 3:空闲) */
    @ApiModelProperty("床位监测状态(0:在床, 1:离床, 2:异常, 3:空闲)")
    private String bedStatus;

    /** 心率（实时） */
    @ApiModelProperty("心率")
    private Integer heartRate;

    /** 呼吸频率（实时） */
    @ApiModelProperty("呼吸频率")
    private Integer breathRate;

    /** 体温（实时） */
    @ApiModelProperty("体温")
    private Double bodyTemp;

    /** 睡眠状态 */
    @ApiModelProperty("睡眠状态")
    private String sleepStatus;

    /** 最后更新时间 */
    @ApiModelProperty("最后更新时间")
    private Date updateTime;
}