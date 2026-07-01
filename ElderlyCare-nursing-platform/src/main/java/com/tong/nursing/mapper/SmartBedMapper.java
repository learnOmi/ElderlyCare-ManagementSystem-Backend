package com.tong.nursing.mapper;

import java.util.List;
import java.util.Map;
import com.tong.nursing.vo.SmartBedVO;

/**
 * 智能床位监控Mapper接口
 * 关联查询床位、设备、老人信息（无独立表）
 *
 * @author Tong
 * @date 2026-07-02
 */
public interface SmartBedMapper {

    /**
     * 查询智能床位监控列表
     * 关联 nursing_bed、nursing_device、nursing_elder、nursing_room、nursing_floor
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
     * 统计各状态床位数量
     *
     * @return 状态统计列表
     */
    Map<String, Object> selectBedStatusOverview();
}