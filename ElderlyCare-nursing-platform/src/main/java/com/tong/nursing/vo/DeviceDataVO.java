package com.tong.nursing.vo;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备数据视图对象
 *
 * @author Tong
 * @date 2026-07-01
 */
@Data
@ApiModel(value = "DeviceDataVO", description = "设备数据视图对象")
public class DeviceDataVO {

    @ApiModelProperty("设备ID")
    private Long deviceId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    @ApiModelProperty("监测值")
    private String monitorValue;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("数据时间")
    private Date dataTime;

    @ApiModelProperty("数据状态")
    private Integer status;

    @ApiModelProperty("床位ID")
    private Long bedId;

    @ApiModelProperty("床位编号")
    private String bedNumber;
}