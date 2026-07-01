package com.tong.nursing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.SmartBedMapper;
import com.tong.nursing.service.ISmartBedService;
import com.tong.nursing.vo.SmartBedVO;

/**
 * 智能床位监控Service实现
 * 整合查询床位、设备、老人信息，不操作独立表
 *
 * @author Tong
 * @date 2026-07-02
 */
@Service
public class SmartBedServiceImpl implements ISmartBedService {

    @Autowired
    private SmartBedMapper smartBedMapper;

    /**
     * 查询智能床位监控列表
     *
     * @param params 查询参数（floorId, bedStatus）
     * @return 智能床位监控列表
     */
    @Override
    public List<SmartBedVO> selectSmartBedList(Map<String, Object> params)
    {
        return smartBedMapper.selectSmartBedList(params);
    }

    /**
     * 查询单个智能床位详情
     *
     * @param id 床位ID
     * @return 智能床位详情
     */
    @Override
    public SmartBedVO selectSmartBedById(Long id)
    {
        return smartBedMapper.selectSmartBedById(id);
    }

    /**
     * 获取床位实时监测数据
     * 当前阶段设备数据接口尚未接入，返回床位基础信息
     * 后续接入智能设备数据后补充心率、呼吸等实时数据
     *
     * @param id 床位ID
     * @return 实时数据
     */
    @Override
    public Map<String, Object> getRealtimeData(Long id)
    {
        SmartBedVO bed = smartBedMapper.selectSmartBedById(id);
        Map<String, Object> result = new HashMap<>();
        if (bed != null)
        {
            // 当前阶段返回基础信息，实时监测数据待设备接入后补充
            result.put("bedNumber", bed.getBedNumber());
            result.put("elderName", bed.getElderName());
            result.put("deviceOnline", bed.getDeviceOnline());
            result.put("bedStatus", bed.getBedStatus());
            // 心率、呼吸等实时数据待智能设备数据接入后补充
            result.put("heartRate", null);
            result.put("breathRate", null);
            result.put("bodyTemp", null);
            result.put("sleepStatus", null);
        }
        return result;
    }

    /**
     * 获取床位历史监测数据
     * 当前阶段设备历史数据表尚未建立，返回空列表
     * 后续接入设备数据后补充历史查询
     *
     * @param id 床位ID
     * @param days 查询天数
     * @return 历史数据列表
     */
    @Override
    public List<Map<String, Object>> getHistoryData(Long id, int days)
    {
        // 待设备历史数据表建立后实现
        return new ArrayList<>();
    }

    /**
     * 获取床位状态概览统计
     *
     * @return 概览数据（onBed, offBed, empty, online, total）
     */
    @Override
    public Map<String, Object> getStatusOverview()
    {
        return smartBedMapper.selectBedStatusOverview();
    }
}