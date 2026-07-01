package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 健康评估对象 nursing_health_assessment
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingHealthAssessment", description = "健康评估实体")
public class NursingHealthAssessment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评估ID */
    @ApiModelProperty("评估ID")
    private Long id;

    /** 老人ID */
    @Excel(name = "老人ID")
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 评估日期 */
    @Excel(name = "评估日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("评估日期")
    private Date assessmentDate;

    /** 评估人 */
    @Excel(name = "评估人")
    @ApiModelProperty("评估人")
    private String assessor;

    /** 身体状况评分 */
    @Excel(name = "身体状况评分")
    @ApiModelProperty("身体状况评分")
    private Integer physicalScore;

    /** 精神状况评分 */
    @Excel(name = "精神状况评分")
    @ApiModelProperty("精神状况评分")
    private Integer mentalScore;

    /** 日常生活能力评分 */
    @Excel(name = "日常生活能力评分")
    @ApiModelProperty("日常生活能力评分")
    private Integer dailyScore;

    /** 社会适应能力评分 */
    @Excel(name = "社会适应能力评分")
    @ApiModelProperty("社会适应能力评分")
    private Integer socialScore;

    /** 综合评分 */
    @Excel(name = "综合评分")
    @ApiModelProperty("综合评分")
    private Integer totalScore;

    /** 建议护理等级ID */
    @ApiModelProperty("建议护理等级ID")
    private Long suggestedLevel;

    /** 评估结果描述 */
    @ApiModelProperty("评估结果描述")
    private String assessmentResult;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态(0:禁用, 1:启用)")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 老人姓名（查询用） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 护理等级名称（查询用） */
    @ApiModelProperty("护理等级名称")
    private String levelName;

    /** 评估报告PDF路径（OSS URL） */
    @Excel(name = "评估报告PDF路径")
    @ApiModelProperty("评估报告PDF路径（OSS URL）")
    private String reportPath;
}
