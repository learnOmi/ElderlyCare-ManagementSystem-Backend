package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 仪表盘统计卡片视图对象
 * 展示在住老人、空闲床位、待确认预约、待处理告警数量
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "DashboardStatVO", description = "仪表盘统计卡片数据")
public class DashboardStatVO
{
    /** 在住老人数 */
    @ApiModelProperty("在住老人数")
    private Integer elderCount;

    /** 空闲床位数 */
    @ApiModelProperty("空闲床位数")
    private Integer bedFreeCount;

    /** 待确认预约数 */
    @ApiModelProperty("待确认预约数")
    private Integer reservationPendingCount;

    /** 待处理告警数 */
    @ApiModelProperty("待处理告警数")
    private Integer alertPendingCount;
}
