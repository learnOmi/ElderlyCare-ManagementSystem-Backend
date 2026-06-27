package com.tong.nursing.vo;

import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 入住申请详情视图对象
 * 包含老人、家属、床位、房间、楼层、护理等级等完整关联信息
 *
 * @author Tong
 * @date 2026-06-28
 */
@Data
@ApiModel(value = "NursingCheckInDetailVO", description = "入住申请详情视图对象")
public class NursingCheckInDetailVO
{
    // ========== 入住申请基本信息 ==========

    /** 入住ID */
    @ApiModelProperty("入住ID")
    private Long id;

    /** 老人ID */
    @ApiModelProperty("老人ID")
    private Long elderId;

    /** 入住床位ID */
    @ApiModelProperty("入住床位ID")
    private Long bedId;

    /** 护理等级ID */
    @ApiModelProperty("护理等级ID")
    private Long levelId;

    /** 护理计划ID */
    @ApiModelProperty("护理计划ID")
    private Long planId;

    /** 申请日期 */
    @ApiModelProperty("申请日期")
    private Date applyDate;

    /** 入住日期 */
    @ApiModelProperty("入住日期")
    private Date checkInDate;

    /** 预计退住日期 */
    @ApiModelProperty("预计退住日期")
    private Date expectedCheckOutDate;

    /** 实际退住日期 */
    @ApiModelProperty("实际退住日期")
    private Date checkOutDate;

    /** 状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝) */
    @ApiModelProperty("状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝)")
    private Integer status;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private String auditBy;

    /** 审核时间 */
    @ApiModelProperty("审核时间")
    private Date auditTime;

    /** 审核意见 */
    @ApiModelProperty("审核意见")
    private String auditRemark;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    // ========== 老人信息 ==========

    /** 老人姓名 */
    @ApiModelProperty("老人姓名")
    private String elderName;

    /** 老人性别(0:男, 1:女) */
    @ApiModelProperty("老人性别(0:男, 1:女)")
    private Integer elderGender;

    /** 老人出生日期 */
    @ApiModelProperty("老人出生日期")
    private Date elderBirthday;

    /** 老人年龄 */
    @ApiModelProperty("老人年龄")
    private Integer elderAge;

    /** 老人身份证号 */
    @ApiModelProperty("老人身份证号")
    private String elderIdCard;

    /** 老人联系电话 */
    @ApiModelProperty("老人联系电话")
    private String elderPhone;

    /** 老人家庭住址 */
    @ApiModelProperty("老人家庭住址")
    private String elderAddress;

    /** 老人头像 */
    @ApiModelProperty("老人头像")
    private String elderAvatar;

    /** 老人入住状态(0:未入住, 1:已入住, 2:已退住) */
    @ApiModelProperty("老人入住状态(0:未入住, 1:已入住, 2:已退住)")
    private Integer elderCheckInStatus;

    /** 老人健康状态(0:自理, 1:半自理, 2:不能自理) */
    @ApiModelProperty("老人健康状态(0:自理, 1:半自理, 2:不能自理)")
    private Integer elderHealthStatus;

    // ========== 床位信息 ==========

    /** 床位编号 */
    @ApiModelProperty("床位编号")
    private String bedNo;

    /** 床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订) */
    @ApiModelProperty("床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订)")
    private Integer bedStatus;

    /** 设备绑定(0:未绑定, 1:已绑定) */
    @ApiModelProperty("设备绑定(0:未绑定, 1:已绑定)")
    private Integer bedDeviceBind;

    /** 设备编号 */
    @ApiModelProperty("设备编号")
    private String bedDeviceNo;

    // ========== 房间信息 ==========

    /** 房间ID */
    @ApiModelProperty("房间ID")
    private Long roomId;

    /** 房间编号 */
    @ApiModelProperty("房间编号")
    private String roomNo;

    /** 房间状态(0:禁用, 1:启用) */
    @ApiModelProperty("房间状态(0:禁用, 1:启用)")
    private Integer roomStatus;

    // ========== 楼层信息 ==========

    /** 楼层ID */
    @ApiModelProperty("楼层ID")
    private Long floorId;

    /** 楼层名称 */
    @ApiModelProperty("楼层名称")
    private String floorName;

    /** 楼层编号 */
    @ApiModelProperty("楼层编号")
    private Integer floorNo;

    // ========== 房型信息 ==========

    /** 房型ID */
    @ApiModelProperty("房型ID")
    private Long roomTypeId;

    /** 房型名称 */
    @ApiModelProperty("房型名称")
    private String roomTypeName;

    /** 床位数量 */
    @ApiModelProperty("床位数量")
    private Integer roomTypeBedCount;

    /** 房型价格 */
    @ApiModelProperty("房型价格")
    private java.math.BigDecimal roomTypePrice;

    /** 房型描述 */
    @ApiModelProperty("房型描述")
    private String roomTypeDescription;

    // ========== 护理等级信息 ==========

    /** 护理等级名称 */
    @ApiModelProperty("护理等级名称")
    private String levelName;

    /** 护理费用 */
    @ApiModelProperty("护理费用")
    private java.math.BigDecimal levelFee;

    /** 等级描述 */
    @ApiModelProperty("等级描述")
    private String levelDescription;

    // ========== 护理计划信息 ==========

    /** 护理计划名称 */
    @ApiModelProperty("护理计划名称")
    private String planName;

    /** 计划状态(0:禁用, 1:启用) */
    @ApiModelProperty("计划状态(0:禁用, 1:启用)")
    private Integer planStatus;

    /** 计划描述 */
    @ApiModelProperty("计划描述")
    private String planDescription;

    // ========== 家属列表 ==========

    /** 家属列表 */
    @ApiModelProperty("家属列表")
    private List<FamilyInfoVO> familyList;

    /**
     * 家属信息内部类
     */
    @Data
    @ApiModel(value = "FamilyInfoVO", description = "家属信息")
    public static class FamilyInfoVO
    {
        /** 家属ID */
        @ApiModelProperty("家属ID")
        private Long familyId;

        /** 家属姓名 */
        @ApiModelProperty("家属姓名")
        private String familyName;

        /** 家属性别(0:男, 1:女) */
        @ApiModelProperty("家属性别(0:男, 1:女)")
        private Integer familyGender;

        /** 家属联系电话 */
        @ApiModelProperty("家属联系电话")
        private String familyPhone;

        /** 家属身份证号 */
        @ApiModelProperty("家属身份证号")
        private String familyIdCard;

        /** 家属住址 */
        @ApiModelProperty("家属住址")
        private String familyAddress;

        /** 与老人关系 */
        @ApiModelProperty("与老人关系")
        private String relation;

        /** 是否紧急联系人(0:否, 1:是) */
        @ApiModelProperty("是否紧急联系人(0:否, 1:是)")
        private Integer isEmergency;
    }
}
