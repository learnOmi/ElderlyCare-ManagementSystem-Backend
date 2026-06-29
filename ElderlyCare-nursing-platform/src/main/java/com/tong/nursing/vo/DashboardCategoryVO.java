package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 仪表盘分类统计视图对象
 * 用于饼图、柱状图的数据展示
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "DashboardCategoryVO", description = "仪表盘分类统计数据")
public class DashboardCategoryVO
{
    /** 类别名称 */
    @ApiModelProperty("类别名称")
    private String name;

    /** 数值 */
    @ApiModelProperty("数值")
    private Integer value;
}
