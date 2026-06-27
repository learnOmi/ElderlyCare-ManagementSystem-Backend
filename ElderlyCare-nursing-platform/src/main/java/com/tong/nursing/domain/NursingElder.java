package com.tong.nursing.domain;

import java.util.Date;
import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 老人信息对象 nursing_elder
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingElder", description = "老人信息实体")
public class NursingElder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    private String name;

    /** 性别(0:男, 1:女) */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    @ApiModelProperty("性别(0:男, 1:女)")
    private Integer gender;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("出生日期")
    private Date birthday;

    /** 年龄 */
    @Excel(name = "年龄")
    @ApiModelProperty("年龄")
    private Integer age;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    private String idCard;

    /** 联系电话 */
    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String phone;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    @ApiModelProperty("家庭住址")
    private String address;

    /** 头像 */
    @ApiModelProperty("头像")
    private String avatar;

    /** 护理等级ID */
    @ApiModelProperty("护理等级ID")
    private Long levelId;

    /** 入住床位ID */
    @ApiModelProperty("入住床位ID")
    private Long bedId;

    /** 入住状态(0:未入住, 1:已入住, 2:已退住) */
    @Excel(name = "入住状态", readConverterExp = "0=未入住,1=已入住,2=已退住")
    @ApiModelProperty("入住状态(0:未入住, 1:已入住, 2:已退住)")
    private Integer checkInStatus;

    /** 健康状态(0:自理, 1:半自理, 2:不能自理) */
    @Excel(name = "健康状态", readConverterExp = "0=自理,1=半自理,2=不能自理")
    @ApiModelProperty("健康状态(0:自理, 1:半自理, 2:不能自理)")
    private Integer healthStatus;

    /** 状态(0:禁用, 1:启用) */
    @ApiModelProperty("状态(0:禁用, 1:启用)")
    private Integer status;

    /** 护理等级名称（非数据库字段） */
    @ApiModelProperty("护理等级名称")
    private String levelName;

    /** 床位名称（非数据库字段） */
    @ApiModelProperty("床位名称")
    private String bedName;
}
