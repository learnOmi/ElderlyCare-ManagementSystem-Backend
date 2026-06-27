package com.tong.nursing.domain;

import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 老人家属关联对象 nursing_elder_family
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingElderFamily", description = "老人家属关联实体")
public class NursingElderFamily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 家属ID */
    @ApiModelProperty("家属ID")
    private Long familyId;

    /** 亲属关系 */
    @Excel(name = "亲属关系")
    @ApiModelProperty("亲属关系")
    private String relation;

    /** 是否紧急联系人(0:否, 1:是) */
    @Excel(name = "是否紧急联系人", readConverterExp = "0=否,1=是")
    @ApiModelProperty("是否紧急联系人(0:否, 1:是)")
    private Integer isEmergency;

    /** 老人姓名（非数据库字段） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 家属姓名（非数据库字段） */
    @ApiModelProperty("家属姓名")
    private String familyName;
}
