package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 仪表盘雷达图视图对象
 * 展示各护理维度的老人分布情况
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "DashboardRadarVO", description = "仪表盘雷达图数据")
public class DashboardRadarVO
{
    /** 雷达图指标列表 */
    @ApiModelProperty("雷达图指标列表")
    private List<RadarIndicator> indicators;

    /** 雷达图系列数据 */
    @ApiModelProperty("雷达图系列数据")
    private List<RadarSeries> series;

    /**
     * 雷达图指标
     */
    @Data
    @ApiModel(value = "RadarIndicator", description = "雷达图指标")
    public static class RadarIndicator
    {
        /** 指标名称 */
        @ApiModelProperty("指标名称")
        private String name;

        /** 最大值 */
        @ApiModelProperty("最大值")
        private Integer max;
    }

    /**
     * 雷达图系列数据
     */
    @Data
    @ApiModel(value = "RadarSeries", description = "雷达图系列数据")
    public static class RadarSeries
    {
        /** 系列名称 */
        @ApiModelProperty("系列名称")
        private String name;

        /** 各指标数值 */
        @ApiModelProperty("各指标数值")
        private List<Integer> value;
    }
}
