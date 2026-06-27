package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 家属信息对象 nursing_family
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingFamily", description = "家属信息实体")
public class NursingFamily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 家属ID */
    @ApiModelProperty("家属ID")
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    private String name;

    /** 性别(0:男, 1:女) */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    @ApiModelProperty("性别(0:男, 1:女)")
    private Integer gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String phone;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    private String idCard;

    /** 住址 */
    @Excel(name = "住址")
    @ApiModelProperty("住址")
    private String address;

    /** 与老人关系 */
    @Excel(name = "与老人关系")
    @ApiModelProperty("与老人关系")
    private String relation;

    /** 状态(0:禁用, 1:启用) */
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;
}
