package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 护理计划对象 nursing_plan
 *
 * @author Tong
 * @date 2026-06-25
 */
@Data
@ApiModel(value = "NursingPlan", description = "护理计划实体")
public class NursingPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 排序号 */
    @Excel(name = "排序号")
    @ApiModelProperty("排序号")
    private Long sortNo;

    /** 计划名称 */
    @Excel(name = "计划名称")
    @ApiModelProperty("计划名称")
    private String planName;

    /** 状态(0: 禁用, 1: 启用) */
    @Excel(name = "状态(0: 禁用, 1: 启用)")
    @ApiModelProperty("状态(0: 禁用, 1: 启用)")
    private Long status;
}
