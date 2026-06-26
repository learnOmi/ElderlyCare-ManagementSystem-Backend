package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 护理项目计划对象 nursing_project_plan
 *
 * @author Tong
 * @date 2026-06-25
 */
@Data
@ApiModel(value = "NursingProjectPlan", description = "护理项目计划实体")
public class NursingProjectPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Integer id;

    /** 计划id */
    @Excel(name = "计划id")
    @ApiModelProperty("计划id")
    private Integer planId;

    /** 项目id */
    @Excel(name = "项目id")
    @ApiModelProperty("项目id")
    private Integer projectId;

    /** 执行时间 */
    @Excel(name = "执行时间")
    @ApiModelProperty("执行时间")
    private String executeTime;

    /** 执行周期(0: 天, 1: 周, 2: 月) */
    @Excel(name = "执行周期(0: 天, 1: 周, 2: 月)")
    @ApiModelProperty("执行周期(0: 天, 1: 周, 2: 月)")
    private Integer executeCycle;

    /** 执行频次 */
    @Excel(name = "执行频次")
    @ApiModelProperty("执行频次")
    private Integer executeFrequency;
}
