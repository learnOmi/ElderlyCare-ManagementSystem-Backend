package com.tong.nursing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tong.common.annotation.Excel;
import com.tong.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 合同对象 nursing_contract
 *
 * @author Tong
 * @date 2026-06-29
 */
@Data
@ApiModel(value = "NursingContract", description = "合同实体")
public class NursingContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    @ApiModelProperty("合同ID")
    private Long id;

    /** 合同编号 */
    @Excel(name = "合同编号")
    @ApiModelProperty("合同编号")
    private String contractNo;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 老人姓名（展示字段，非表字段） */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 入住ID */
    @ApiModelProperty("入住ID")
    private Long checkInId;

    /** 合同类型(0:入住合同, 1:服务合同, 2:其他) */
    @Excel(name = "合同类型", dictType = "nursing_contract_type")
    @ApiModelProperty("合同类型(0:入住合同, 1:服务合同, 2:其他)")
    private Integer contractType;

    /** 签订日期 */
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("签订日期")
    private Date signDate;

    /** 开始日期 */
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("开始日期")
    private Date startDate;

    /** 结束日期 */
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("结束日期")
    private Date endDate;

    /** 合同总金额 */
    @Excel(name = "合同总金额")
    @ApiModelProperty("合同总金额")
    private BigDecimal totalAmount;

    /** 状态(0:草稿, 1:已签订, 2:已生效, 3:已到期, 4:已终止) */
    @Excel(name = "状态", readConverterExp = "0=草稿,1=已签订,2=已生效,3=已到期,4=已终止")
    @ApiModelProperty("状态(0:草稿, 1:已签订, 2:已生效, 3:已到期, 4:已终止)")
    private Integer status;

    /** 终止原因 */
    @Excel(name = "终止原因")
    @ApiModelProperty("终止原因")
    private String terminateReason;

    /** 终止日期 */
    @Excel(name = "终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("终止日期")
    private Date terminateDate;
}
