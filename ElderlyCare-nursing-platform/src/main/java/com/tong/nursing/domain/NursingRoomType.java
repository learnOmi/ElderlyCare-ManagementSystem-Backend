package com.tong.nursing.domain;

import java.math.BigDecimal;
import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房型对象 nursing_room_type
 *
 * @author Tong
 * @date 2026-06-27
 */
@Data
@ApiModel(value = "NursingRoomType", description = "房型实体")
public class NursingRoomType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房型ID */
    @ApiModelProperty("房型ID")
    private Long id;

    /** 房型名称 */
    @Excel(name = "房型名称")
    @ApiModelProperty("房型名称")
    private String name;

    /** 床位数量 */
    @Excel(name = "床位数量")
    @ApiModelProperty("床位数量")
    private Integer bedCount;

    /** 房型价格 */
    @Excel(name = "房型价格")
    @ApiModelProperty("房型价格")
    private BigDecimal price;

    /** 状态(0:禁用, 1:启用) */
    @Excel(name = "状态(0:禁用, 1:启用)")
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 房型描述 */
    @Excel(name = "房型描述")
    @ApiModelProperty("房型描述")
    private String description;
}
