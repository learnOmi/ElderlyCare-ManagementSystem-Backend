package com.tong.nursing.dto;

import com.tong.nursing.domain.NursingPlan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 护理计划DTO（含项目关联）
 *
 * @author Tong
 * @date 2026-06-30
 */
@Data
@ApiModel(value = "NursingPlanDTO", description = "护理计划数据传输对象（含项目关联）")
public class NursingPlanDTO
{
    /** 护理计划基本信息 */
    private NursingPlan plan;

    /** 项目计划关联列表 */
    @ApiModelProperty("项目计划关联列表")
    private List<ProjectPlanItem> projectPlans;

    /**
     * 项目计划项
     */
    @Data
    @ApiModel(value = "ProjectPlanItem", description = "项目计划项")
    public static class ProjectPlanItem
    {
        /** 项目ID */
        @ApiModelProperty("项目ID")
        private Integer projectId;

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
