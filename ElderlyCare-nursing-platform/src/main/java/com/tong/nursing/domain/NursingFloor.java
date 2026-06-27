package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 楼层对象 nursing_floor
 *
 * @author Tong
 * @date 2026-06-27
 */
@Data
@ApiModel(value = "NursingFloor", description = "楼层实体")
public class NursingFloor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 楼层ID */
    @ApiModelProperty("楼层ID")
    private Long id;

    /** 楼层名称 */
    @Excel(name = "楼层名称")
    @ApiModelProperty("楼层名称")
    private String name;

    /** 楼层编号 */
    @Excel(name = "楼层编号")
    @ApiModelProperty("楼层编号")
    private Integer floorNo;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态(0:禁用, 1:启用)")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;
}
