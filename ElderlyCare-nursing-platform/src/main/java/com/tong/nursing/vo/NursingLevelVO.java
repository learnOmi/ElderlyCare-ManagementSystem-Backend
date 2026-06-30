package com.tong.nursing.vo;

import com.tong.nursing.domain.NursingLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 护理等级VO（含护理计划名称和项目列表）
 *
 * @author Tong
 * @date 2026-06-30
 */
@Data
@ApiModel(value = "NursingLevelVO", description = "护理等级视图对象（含护理计划名称和项目列表）")
public class NursingLevelVO extends NursingLevel
{
    private static final long serialVersionUID = 1L;

    /** 护理计划名称 */
    @ApiModelProperty("护理计划名称")
    private String planName;

    /** 项目列表（含执行时间、周期、频次） */
    @ApiModelProperty("项目列表")
    private List<ProjectVO> projectList;

    /**
     * 项目VO
     */
    @Data
    @ApiModel(value = "NursingLevelVO$ProjectVO", description = "护理等级关联项目视图")
    public static class ProjectVO
    {
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
