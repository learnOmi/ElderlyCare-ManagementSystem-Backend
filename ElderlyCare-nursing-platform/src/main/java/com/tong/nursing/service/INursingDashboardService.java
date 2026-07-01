package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.vo.DashboardStatVO;
import com.tong.nursing.vo.DashboardTrendVO;
import com.tong.nursing.vo.DashboardCategoryVO;
import com.tong.nursing.vo.DashboardRadarVO;

/**
 * 仪表盘Service接口
 *
 * @author Tong
 * @date 2026-06-29
 */
public interface INursingDashboardService
{
    /**
     * 获取统计卡片数据
     *
     * @return 统计卡片数据
     */
    public DashboardStatVO getStatCards();

    /**
     * 获取趋势图数据（最近7天）
     *
     * @return 趋势图数据
     */
    public DashboardTrendVO getTrendData();

    /**
     * 获取饼图数据（床位状态分布）
     *
     * @return 饼图数据列表
     */
    public List<DashboardCategoryVO> getPieData();

    /**
     * 获取柱状图数据（各楼层入住人数）
     *
     * @return 柱状图数据列表
     */
    public List<DashboardCategoryVO> getBarData();

    /**
     * 获取雷达图数据（各护理等级老人分布）
     *
     * @return 雷达图数据
     */
    public DashboardRadarVO getRadarData();

    /**
     * 获取待办事项统计
     *
     * @return 待办事项统计数据
     */
    public DashboardStatVO getBacklog();
}
