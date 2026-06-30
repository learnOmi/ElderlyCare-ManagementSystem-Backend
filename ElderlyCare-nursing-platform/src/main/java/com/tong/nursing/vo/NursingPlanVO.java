package com.tong.nursing.vo;

import com.tong.nursing.domain.NursingPlan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 护理计划VO（含项目列表）
 *
 * @author Tong
 * @date 2026-06-30
 */
@Data
@ApiModel(value = "NursingPlanVO", description = "护理计划视图对象（含项目列表）")
public class NursingPlanVO extends NursingPlan
{
    private static final long serialVersionUID = 1L;

    /** 项目计划关联列表（含项目名称） */
    @ApiModelProperty("项目计划关联列表")
    private List<ProjectPlanVO> projectPlanList;

    /**
     * 项目计划项VO
     */
    @Data
    @ApiModel(value = "NursingPlanVO$ProjectPlanVO", description = "项目计划项VO")
    public static class ProjectPlanVO
    {
        /** 关联记录ID */
        @ApiModelProperty("关联记录ID")
        private Integer id;

        /** 项目ID */
        @ApiModelProperty("项目ID")
        private Integer projectId;

        /** 项目名称 */
        @ApiModelProperty("项目名称")
        private String projectName;

        /** 执行时间 */
        @ApiModelProperty("执行时间")
        private String executeTime;

        /** 执行周期(0: 天, 1: 周, 2: 月) */
        @ApiModelProperty("执行周期(0: 天, 1: 周, 2: 月)")
        private Integer executeCycle;

        /** 执行频次 */
        @ApiModelProperty("执行频次")
        private Integer executeFrequency;
    }
}
