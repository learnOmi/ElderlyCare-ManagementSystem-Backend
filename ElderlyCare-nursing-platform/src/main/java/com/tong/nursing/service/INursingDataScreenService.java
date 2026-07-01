package com.tong.nursing.service;

import java.util.List;
import java.util.Map;

/**
 * 数据大屏Service接口
 *
 * @author Tong
 * @date 2026-07-01
 */
public interface INursingDataScreenService {

    /**
     * 获取大屏核心统计数据
     *
     * @return 统计数据
     */
    public Map<String, Object> getStatistics();

    /**
     * 获取月度入退住趋势
     *
     * @param months 月份数
     * @return 趋势数据
     */
    public Map<String, Object> getEnterTrend(Integer months);

    /**
     * 获取护理等级分布
     *
     * @return 分布数据
     */
    public List<Map<String, Object>> getLevelDistribution();

    /**
     * 获取楼层入住率
     *
     * @return 楼层入住率数据
     */
    public List<Map<String, Object>> getFloorOccupancy();

    /**
     * 获取告警趋势
     *
     * @param days 天数
     * @return 告警趋势数据
     */
    public Map<String, Object> getAlertTrend(Integer days);

    /**
     * 获取服务类型数据
     *
     * @return 服务类型数据
     */
    public List<Map<String, Object>> getServiceTypeData();

    /**
     * 获取年龄分布
     *
     * @return 年龄分布数据
     */
    public List<Map<String, Object>> getAgeDistribution();

    /**
     * 获取实时动态事件
     *
     * @return 动态事件列表
     */
    public List<Map<String, Object>> getRealtimeEvents();
}