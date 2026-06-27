package com.tong.nursing.constant;

/**
 * 护理业务模块常量类
 *
 * @author Tong
 * @date 2026-06-27
 */
public class NursingConstants
{
    // ==================== 通用状态 ====================

    /** 通用状态-禁用 */
    public static final Integer STATUS_DISABLE = 0;

    /** 通用状态-启用 */
    public static final Integer STATUS_ENABLE = 1;

    // ==================== 床位状态 ====================

    /** 床位状态-空闲 */
    public static final Integer BED_STATUS_FREE = 0;

    /** 床位状态-已入住 */
    public static final Integer BED_STATUS_OCCUPIED = 1;

    /** 床位状态-维修中 */
    public static final Integer BED_STATUS_MAINTENANCE = 2;

    /** 床位状态-预订 */
    public static final Integer BED_STATUS_RESERVED = 3;

    // ==================== 床位设备绑定状态 ====================

    /** 设备绑定-未绑定 */
    public static final Integer BED_DEVICE_UNBIND = 0;

    /** 设备绑定-已绑定 */
    public static final Integer BED_DEVICE_BOUND = 1;

    // ==================== 设备类型 ====================

    /** 设备类型-智能床 */
    public static final Integer DEVICE_TYPE_SMART_BED = 0;

    /** 设备类型-呼叫器 */
    public static final Integer DEVICE_TYPE_CALLER = 1;

    /** 设备类型-监测设备 */
    public static final Integer DEVICE_TYPE_MONITOR = 2;

    /** 设备类型-其他 */
    public static final Integer DEVICE_TYPE_OTHER = 3;

    // ==================== 设备状态 ====================

    /** 设备状态-离线 */
    public static final Integer DEVICE_STATUS_OFFLINE = 0;

    /** 设备状态-在线 */
    public static final Integer DEVICE_STATUS_ONLINE = 1;

    /** 设备状态-故障 */
    public static final Integer DEVICE_STATUS_FAULT = 2;

    // ==================== 入住状态 ====================

    /** 入住状态-未入住 */
    public static final Integer CHECK_IN_STATUS_NONE = 0;

    /** 入住状态-已入住 */
    public static final Integer CHECK_IN_STATUS_IN = 1;

    /** 入住状态-已退住 */
    public static final Integer CHECK_IN_STATUS_OUT = 2;

    // ==================== 健康状态 ====================

    /** 健康状态-自理 */
    public static final Integer HEALTH_STATUS_SELF_CARE = 0;

    /** 健康状态-半自理 */
    public static final Integer HEALTH_STATUS_SEMI_CARE = 1;

    /** 健康状态-不能自理 */
    public static final Integer HEALTH_STATUS_NO_CARE = 2;

    // ==================== 告警级别 ====================

    /** 告警级别-一般 */
    public static final Integer ALERT_LEVEL_NORMAL = 0;

    /** 告警级别-重要 */
    public static final Integer ALERT_LEVEL_IMPORTANT = 1;

    /** 告警级别-紧急 */
    public static final Integer ALERT_LEVEL_URGENT = 2;

    // ==================== 告警处理状态 ====================

    /** 告警状态-未处理 */
    public static final Integer ALERT_STATUS_PENDING = 0;

    /** 告警状态-处理中 */
    public static final Integer ALERT_STATUS_PROCESSING = 1;

    /** 告警状态-已处理 */
    public static final Integer ALERT_STATUS_HANDLED = 2;

    /** 告警状态-已忽略 */
    public static final Integer ALERT_STATUS_IGNORED = 3;

    // ==================== 排班执行状态 ====================

    /** 排班状态-待执行 */
    public static final Integer ARRANGE_STATUS_PENDING = 0;

    /** 排班状态-已执行 */
    public static final Integer ARRANGE_STATUS_EXECUTED = 1;

    /** 排班状态-已取消 */
    public static final Integer ARRANGE_STATUS_CANCELED = 2;

    /** 排班状态-执行中 */
    public static final Integer ARRANGE_STATUS_EXECUTING = 3;

    // ==================== 执行周期 ====================

    /** 执行周期-天 */
    public static final Integer EXECUTE_CYCLE_DAY = 0;

    /** 执行周期-周 */
    public static final Integer EXECUTE_CYCLE_WEEK = 1;

    /** 执行周期-月 */
    public static final Integer EXECUTE_CYCLE_MONTH = 2;

    // ==================== 合同状态 ====================

    /** 合同状态-草稿 */
    public static final Integer CONTRACT_STATUS_DRAFT = 0;

    /** 合同状态-已签订 */
    public static final Integer CONTRACT_STATUS_SIGNED = 1;

    /** 合同状态-已生效 */
    public static final Integer CONTRACT_STATUS_EFFECTIVE = 2;

    /** 合同状态-已到期 */
    public static final Integer CONTRACT_STATUS_EXPIRED = 3;

    /** 合同状态-已终止 */
    public static final Integer CONTRACT_STATUS_TERMINATED = 4;

    // ==================== 预约状态 ====================

    /** 预约状态-待确认 */
    public static final Integer RESERVATION_STATUS_PENDING = 0;

    /** 预约状态-已确认 */
    public static final Integer RESERVATION_STATUS_CONFIRMED = 1;

    /** 预约状态-已完成 */
    public static final Integer RESERVATION_STATUS_COMPLETED = 2;

    /** 预约状态-已取消 */
    public static final Integer RESERVATION_STATUS_CANCELED = 3;

    // ==================== 预约类型 ====================

    /** 预约类型-来访预约 */
    public static final Integer RESERVATION_TYPE_VISIT = 0;

    /** 预约类型-入住预约 */
    public static final Integer RESERVATION_TYPE_CHECK_IN = 1;

    /** 预约类型-参观预约 */
    public static final Integer RESERVATION_TYPE_TOUR = 2;
}
