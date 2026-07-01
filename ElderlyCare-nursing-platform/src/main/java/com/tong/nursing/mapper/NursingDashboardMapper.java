package com.tong.nursing.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.tong.nursing.vo.DashboardCategoryVO;

/**
 * 仪表盘Mapper接口
 *
 * @author Tong
 * @date 2026-06-29
 */
@Mapper
public interface NursingDashboardMapper
{
    /**
     * 统计在住老人数
     *
     * @return 在住老人数
     */
    public Integer countOnlineElders();

    /**
     * 统计空闲床位数
     *
     * @return 空闲床位数
     */
    public Integer countFreeBeds();

    /**
     * 统计待确认预约数
     *
     * @return 待确认预约数
     */
    public Integer countPendingReservations();

    /**
     * 统计待处理告警数
     *
     * @return 待处理告警数
     */
    public Integer countPendingAlerts();

    /**
     * 查询最近7天每日预约数量
     *
     * @return 日期-数量映射列表
     */
    public List<Map<String, Object>> selectRecent7DayReservationCount();

    /**
     * 查询最近7天每日入住数量
     *
     * @return 日期-数量映射列表
     */
    public List<Map<String, Object>> selectRecent7DayCheckInCount();

    /**
     * 查询床位状态分布统计
     *
     * @return 状态-数量映射列表
     */
    public List<DashboardCategoryVO> selectBedStatusDistribution();

    /**
     * 查询各楼层入住人数统计
     *
     * @return 楼层-数量映射列表
     */
    public List<DashboardCategoryVO> selectFloorOccupancyCount();

    /**
     * 查询各护理等级老人数量
     *
     * @return 等级-数量映射列表
     */
    public List<DashboardCategoryVO> selectLevelElderCount();

    /**
     * 统计待处理入住申请数
     *
     * @return 待处理入住申请数
     */
    public Integer countPendingCheckIns();

    /**
     * 统计待续签合同数
     *
     * @return 待续签合同数
     */
    public Integer countContractRenew();

    /**
     * 统计床位总数
     *
     * @return 床位总数
     */
    public Integer countTotalBeds();

    /**
     * 统计今日服务数量
     *
     * @return 今日服务数量
     */
    public Integer countTodayService();

    /**
     * 查询最近N个月每日入住数量
     *
     * @param months 月份数
     * @return 月份-数量映射列表
     */
    public List<Map<String, Object>> selectRecentMonthCheckInCount(Integer months);

    /**
     * 查询最近N天每日告警数量
     *
     * @param days 天数
     * @return 日期-数量映射列表
     */
    public List<Map<String, Object>> selectRecentDayAlertCount(Integer days);
}
