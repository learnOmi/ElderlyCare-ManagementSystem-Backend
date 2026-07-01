package com.tong.nursing.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.tong.nursing.domain.NursingFamily;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 入住申请DTO
 * 包含老人信息、入住配置、合同信息、家属列表
 */
@Data
@ApiModel(value = "CheckInApplyDto", description = "入住申请DTO")
public class CheckInApplyDto {

    /** 老人信息 */
    @ApiModelProperty("老人信息")
    private ElderDto elder;

    /** 入住配置信息 */
    @ApiModelProperty("入住配置信息")
    private CheckInConfigDto checkInConfig;

    /** 合同信息 */
    @ApiModelProperty("合同信息")
    private CheckInContractDto contract;

    /** 家属列表 */
    @ApiModelProperty("家属列表")
    private List<NursingFamily> familyList;

    /**
     * 老人信息DTO
     */
    @Data
    @ApiModel(value = "ElderDto", description = "老人信息DTO")
    public static class ElderDto {
        @ApiModelProperty("老人ID")
        private Long id;
        @ApiModelProperty("姓名")
        private String name;
        @ApiModelProperty("性别(0:男, 1:女)")
        private Integer gender;
        @ApiModelProperty("出生日期")
        private Date birthday;
        @ApiModelProperty("身份证号")
        private String idCard;
        @ApiModelProperty("联系电话")
        private String phone;
        @ApiModelProperty("家庭住址")
        private String address;
        @ApiModelProperty("护理等级ID")
        private Long levelId;
        @ApiModelProperty("健康状态(0:自理, 1:半自理, 2:不能自理)")
        private Integer healthStatus;
    }

    /**
     * 入住配置DTO
     */
    @Data
    @ApiModel(value = "CheckInConfigDto", description = "入住配置DTO")
    public static class CheckInConfigDto {
        @ApiModelProperty("床位ID")
        private Long bedId;
        @ApiModelProperty("护理等级ID")
        private Long nursingLevelId;
        @ApiModelProperty("护理等级名称")
        private String nursingLevelName;
        @ApiModelProperty("费用开始日期")
        private Date feeStartDate;
        @ApiModelProperty("费用结束日期")
        private Date feeEndDate;
        @ApiModelProperty("押金")
        private BigDecimal deposit;
        @ApiModelProperty("护理费用")
        private BigDecimal nursingFee;
        @ApiModelProperty("床位费用")
        private BigDecimal bedFee;
        @ApiModelProperty("医保支付")
        private BigDecimal insurancePayment;
        @ApiModelProperty("政府补贴")
        private BigDecimal governmentSubsidy;
        @ApiModelProperty("其他费用")
        private BigDecimal otherFees;
        @ApiModelProperty("入住日期")
        private Date checkInDate;
        @ApiModelProperty("预计退住日期")
        private Date expectedCheckOutDate;
    }

    /**
     * 合同信息DTO
     */
    @Data
    @ApiModel(value = "CheckInContractDto", description = "合同信息DTO")
    public static class CheckInContractDto {
        @ApiModelProperty("合同类型(0:入住合同, 1:服务合同, 2:其他)")
        private Integer contractType;
        @ApiModelProperty("签订日期")
        private Date signDate;
        @ApiModelProperty("开始日期")
        private Date startDate;
        @ApiModelProperty("结束日期")
        private Date endDate;
        @ApiModelProperty("合同总金额")
        private BigDecimal totalAmount;
    }
}
