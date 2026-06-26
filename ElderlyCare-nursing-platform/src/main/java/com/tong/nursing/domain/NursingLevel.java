package com.tong.nursing.domain;

import java.math.BigDecimal;
import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 护理等级对象 nursing_level
 *
 * @author Tong
 * @date 2026-06-25
 */
@Data
@ApiModel(value = "NursingLevel", description = "护理等级实体")
public class NursingLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Integer id;

    /** 等级名 */
    @Excel(name = "等级名")
    @ApiModelProperty("等级名")
    private String name;

    /** 护理计划ID */
    @Excel(name = "护理计划ID")
    @ApiModelProperty("护理计划ID")
    private Integer lplanId;

    /** 护理费用 */
    @Excel(name = "护理费用")
    @ApiModelProperty("护理费用")
    private BigDecimal fee;

    /** 状态(0: 禁用, 1: 启用) */
    @Excel(name = "状态(0: 禁用, 1: 启用)")
    @ApiModelProperty("状态(0: 禁用, 1: 启用)")
    private Integer status;

    /** 等级描述 */
    @Excel(name = "等级描述")
    @ApiModelProperty("等级描述")
    private String description;
}
