package com.tong.nursing.service;

import java.util.List;
import java.util.Map;
import com.tong.nursing.vo.SmartBedVO;

/**
 * 智能床位监控Service接口
 *
 * @author Tong
 * @date 2026-07-02
 */
public interface ISmartBedService {

    /**
     * 查询智能床位监控列表
     *
     * @param params 查询参数（floorId, bedStatus）
     * @return 智能床位监控列表
     */
    List<SmartBedVO> selectSmartBedList(Map<String, Object> params);

    /**
     * 查询单个智能床位详情
     *
     * @param id 床位ID
     * @return 智能床位详情
     */
    SmartBedVO selectSmartBedById(Long id);

    /**
     * 获取床位实时监测数据
     * 后续接入设备数据后补充实现
     *
     * @param id 床位ID
     * @return 实时数据
     */
    Map<String, Object> getRealtimeData(Long id);

    /**
     * 获取床位历史监测数据
     * 后续接入设备数据后补充实现
     *
     * @param id 床位ID
     * @param days 查询天数
     * @return 历史数据列表
     */
    List<Map<String, Object>> getHistoryData(Long id, int days);

    /**
     * 获取床位状态概览统计
     *
     * @return 概览数据
     */
    Map<String, Object> getStatusOverview();
}