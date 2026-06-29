package com.tong.nursing.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingDashboardMapper;
import com.tong.nursing.service.INursingDashboardService;
import com.tong.nursing.vo.DashboardStatVO;
import com.tong.nursing.vo.DashboardTrendVO;
import com.tong.nursing.vo.DashboardCategoryVO;
import com.tong.nursing.vo.DashboardRadarVO;

/**
 * 仪表盘Service业务层处理
 *
 * @author Tong
 * @date 2026-06-29
 */
@Service
public class NursingDashboardServiceImpl implements INursingDashboardService
{
    @Autowired
    private NursingDashboardMapper nursingDashboardMapper;

    /**
     * 获取统计卡片数据
     */
    @Override
    public DashboardStatVO getStatCards()
    {
        DashboardStatVO vo = new DashboardStatVO();
        vo.setElderCount(nursingDashboardMapper.countOnlineElders());
        vo.setBedFreeCount(nursingDashboardMapper.countFreeBeds());
        vo.setReservationPendingCount(nursingDashboardMapper.countPendingReservations());
        vo.setAlertPendingCount(nursingDashboardMapper.countPendingAlerts());
        return vo;
    }

    /**
     * 获取趋势图数据（最近7天）
     */
    @Override
    public DashboardTrendVO getTrendData()
    {
        DashboardTrendVO vo = new DashboardTrendVO();

        // 生成最近7天的日期数组
        String[] dates = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for (int i = 6; i >= 0; i--)
        {
            Date d = new Date(System.currentTimeMillis() - (long)i * 24 * 60 * 60 * 1000);
            dates[6 - i] = sdf.format(d);
        }
        vo.setDates(dates);

        // 预约数量
        Integer[] expectedData = new Integer[7];
        List<Map<String, Object>> reservationList = nursingDashboardMapper.selectRecent7DayReservationCount();
        for (int i = 0; i < 7; i++)
        {
            expectedData[i] = 0;
        }
        for (Map<String, Object> row : reservationList)
        {
            String dateStr = row.get("stat_date").toString();
            int cnt = ((Number) row.get("cnt")).intValue();
            for (int i = 0; i < 7; i++)
            {
                String day = dates[i];
                if (dateStr.endsWith(day))
                {
                    expectedData[i] = cnt;
                    break;
                }
            }
        }
        vo.setExpectedData(expectedData);

        // 入住数量
        Integer[] actualData = new Integer[7];
        List<Map<String, Object>> checkInList = nursingDashboardMapper.selectRecent7DayCheckInCount();
        for (int i = 0; i < 7; i++)
        {
            actualData[i] = 0;
        }
        for (Map<String, Object> row : checkInList)
        {
            String dateStr = row.get("stat_date").toString();
            int cnt = ((Number) row.get("cnt")).intValue();
            for (int i = 0; i < 7; i++)
            {
                String day = dates[i];
                if (dateStr.endsWith(day))
                {
                    actualData[i] = cnt;
                    break;
                }
            }
        }
        vo.setActualData(actualData);

        return vo;
    }

    /**
     * 获取饼图数据（床位状态分布）
     */
    @Override
    public List<DashboardCategoryVO> getPieData()
    {
        return nursingDashboardMapper.selectBedStatusDistribution();
    }

    /**
     * 获取柱状图数据（各楼层入住人数）
     */
    @Override
    public List<DashboardCategoryVO> getBarData()
    {
        List<DashboardCategoryVO> floorData = nursingDashboardMapper.selectFloorOccupancyCount();

        // 补齐7天数据，用于前端柱状图的多系列展示
        List<DashboardCategoryVO> result = new ArrayList<>();
        for (DashboardCategoryVO item : floorData)
        {
            result.add(item);
        }
        // 如果没有数据，返回默认占位
        if (result.isEmpty())
        {
            DashboardCategoryVO placeholder = new DashboardCategoryVO();
            placeholder.setName("暂无数据");
            placeholder.setValue(0);
            result.add(placeholder);
        }
        return result;
    }

    /**
     * 获取雷达图数据（各护理等级老人分布）
     */
    @Override
    public DashboardRadarVO getRadarData()
    {
        DashboardRadarVO vo = new DashboardRadarVO();

        // 雷达图指标：按护理等级维度
        List<DashboardRadarVO.RadarIndicator> indicators = new ArrayList<>();
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("自理"); setMax(50); }});
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("半自理"); setMax(50); }});
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("不能自理"); setMax(50); }});
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("特级护理"); setMax(50); }});
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("康复训练"); setMax(50); }});
        indicators.add(new DashboardRadarVO.RadarIndicator() {{ setName("心理关怀"); setMax(50); }});
        vo.setIndicators(indicators);

        // 系列数据：各护理等级老人数量
        List<DashboardCategoryVO> levelData = nursingDashboardMapper.selectLevelElderCount();
        List<DashboardRadarVO.RadarSeries> series = new ArrayList<>();

        DashboardRadarVO.RadarSeries currentSeries = new DashboardRadarVO.RadarSeries();
        currentSeries.setName("在住老人分布");

        List<Integer> values = new ArrayList<>();
        // 按指标顺序填充数值
        Map<String, Integer> levelMap = new HashMap<>();
        for (DashboardCategoryVO item : levelData)
        {
            levelMap.put(item.getName(), item.getValue());
        }
        for (DashboardRadarVO.RadarIndicator indicator : indicators)
        {
            Integer val = levelMap.get(indicator.getName());
            values.add(val == null ? 0 : val);
        }
        currentSeries.setValue(values);
        series.add(currentSeries);

        vo.setSeries(series);
        return vo;
    }
}
