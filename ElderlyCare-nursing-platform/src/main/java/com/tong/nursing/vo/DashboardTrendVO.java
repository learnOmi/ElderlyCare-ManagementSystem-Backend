package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 仪表盘趋势图视图对象
 * 展示最近7天的预约趋势折线图数据
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "DashboardTrendVO", description = "仪表盘趋势图数据")
public class DashboardTrendVO
{
    /** 日期列表（最近7天） */
    @ApiModelProperty("日期列表")
    private String[] dates;

    /** 期望数据（预约趋势） */
    @ApiModelProperty("期望数据")
    private Integer[] expectedData;

    /** 实际数据（入住趋势） */
    @ApiModelProperty("实际数据")
    private Integer[] actualData;
}
