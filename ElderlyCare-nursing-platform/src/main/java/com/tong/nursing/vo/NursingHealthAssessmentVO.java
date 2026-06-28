package com.tong.nursing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 健康评估视图对象
 * 包含老人姓名、护理等级名称等展示字段
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingHealthAssessmentVO", description = "健康评估视图对象")
public class NursingHealthAssessmentVO
{
    /** 评估ID */
    @ApiModelProperty("评估ID")
    private Long id;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 评估日期 */
    @ApiModelProperty("评估日期")
    private Date assessmentDate;

    /** 评估人 */
    @ApiModelProperty("评估人")
    private String assessor;

    /** 身体状况评分 */
    @ApiModelProperty("身体状况评分")
    private Integer physicalScore;

    /** 精神状况评分 */
    @ApiModelProperty("精神状况评分")
    private Integer mentalScore;

    /** 日常生活能力评分 */
    @ApiModelProperty("日常生活能力评分")
    private Integer dailyScore;

    /** 社会适应能力评分 */
    @ApiModelProperty("社会适应能力评分")
    private Integer socialScore;

    /** 综合评分 */
    @ApiModelProperty("综合评分")
    private Integer totalScore;

    /** 建议护理等级ID */
    @ApiModelProperty("建议护理等级ID")
    private Long suggestedLevel;

    /** 护理等级名称 */
    @ApiModelProperty("护理等级名称")
    private String levelName;

    /** 评估结果描述 */
    @ApiModelProperty("评估结果描述")
    private String assessmentResult;

    /** 状态(0:禁用, 1:启用) */
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;
}
