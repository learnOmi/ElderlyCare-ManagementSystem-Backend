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
import com.tong.nursing.service.INursingDataScreenService;
import com.tong.nursing.vo.DashboardCategoryVO;

/**
 * 数据大屏Service业务层处理
 *
 * @author Tong
 * @date 2026-07-01
 */
@Service
public class NursingDataScreenServiceImpl implements INursingDataScreenService {

    @Autowired
    private NursingDashboardMapper nursingDashboardMapper;

    /**
     * 获取大屏核心统计数据
     */
    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> data = new HashMap<>();
        data.put("elderCount", nursingDashboardMapper.countOnlineElders());
        data.put("bedTotalCount", nursingDashboardMapper.countTotalBeds());
        data.put("bedFreeCount", nursingDashboardMapper.countFreeBeds());
        data.put("alertPendingCount", nursingDashboardMapper.countPendingAlerts());
        data.put("serviceTodayCount", nursingDashboardMapper.countTodayService());
        return data;
    }

    /**
     * 获取月度入退住趋势
     */
    @Override
    public Map<String, Object> getEnterTrend(Integer months) {
        if (months == null || months <= 0) {
            months = 6;
        }

        Map<String, Object> data = new HashMap<>();
        String[] dates = new String[months];
        Integer[] checkInData = new Integer[months];
        Integer[] checkOutData = new Integer[months];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = months - 1; i >= 0; i--) {
            Date d = new Date(System.currentTimeMillis() - (long) i * 30 * 24 * 60 * 60 * 1000);
            dates[months - 1 - i] = sdf.format(d);
            checkInData[months - 1 - i] = 0;
            checkOutData[months - 1 - i] = 0;
        }

        List<Map<String, Object>> checkInList = nursingDashboardMapper.selectRecentMonthCheckInCount(months);
        for (Map<String, Object> row : checkInList) {
            String dateStr = row.get("stat_month").toString();
            int cnt = ((Number) row.get("cnt")).intValue();
            for (int i = 0; i < months; i++) {
                if (dates[i].equals(dateStr)) {
                    checkInData[i] = cnt;
                    break;
                }
            }
        }

        data.put("dates", dates);
        data.put("checkInData", checkInData);
        data.put("checkOutData", checkOutData);
        return data;
    }

    /**
     * 获取护理等级分布
     */
    @Override
    public List<Map<String, Object>> getLevelDistribution() {
        List<DashboardCategoryVO> levelData = nursingDashboardMapper.selectLevelElderCount();
        List<Map<String, Object>> result = new ArrayList<>();
        for (DashboardCategoryVO item : levelData) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.getName());
            map.put("value", item.getValue());
            result.add(map);
        }
        return result;
    }

    /**
     * 获取楼层入住率
     */
    @Override
    public List<Map<String, Object>> getFloorOccupancy() {
        List<DashboardCategoryVO> floorData = nursingDashboardMapper.selectFloorOccupancyCount();
        List<Map<String, Object>> result = new ArrayList<>();
        for (DashboardCategoryVO item : floorData) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.getName());
            map.put("value", item.getValue());
            result.add(map);
        }
        return result;
    }

    /**
     * 获取告警趋势
     */
    @Override
    public Map<String, Object> getAlertTrend(Integer days) {
        if (days == null || days <= 0) {
            days = 7;
        }

        Map<String, Object> data = new HashMap<>();
        String[] dates = new String[days];
        Integer[] alertData = new Integer[days];

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for (int i = days - 1; i >= 0; i--) {
            Date d = new Date(System.currentTimeMillis() - (long) i * 24 * 60 * 60 * 1000);
            dates[days - 1 - i] = sdf.format(d);
            alertData[days - 1 - i] = 0;
        }

        List<Map<String, Object>> alertList = nursingDashboardMapper.selectRecentDayAlertCount(days);
        for (Map<String, Object> row : alertList) {
            String dateStr = row.get("stat_date").toString();
            int cnt = ((Number) row.get("cnt")).intValue();
            for (int i = 0; i < days; i++) {
                if (dateStr.endsWith(dates[i])) {
                    alertData[i] = cnt;
                    break;
                }
            }
        }

        data.put("dates", dates);
        data.put("alertData", alertData);
        return data;
    }

    /**
     * 获取服务类型数据
     */
    @Override
    public List<Map<String, Object>> getServiceTypeData() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "日常护理");
        item1.put("value", 45);
        item1.put("color", "#5470c6");
        result.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "康复训练");
        item2.put("value", 25);
        item2.put("color", "#91cc75");
        result.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "健康评估");
        item3.put("value", 15);
        item3.put("color", "#fac858");
        result.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "心理关怀");
        item4.put("value", 10);
        item4.put("color", "#ee6666");
        result.add(item4);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("name", "其他服务");
        item5.put("value", 5);
        item5.put("color", "#73c0de");
        result.add(item5);

        return result;
    }

    /**
     * 获取年龄分布
     */
    @Override
    public List<Map<String, Object>> getAgeDistribution() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "60-69岁");
        item1.put("value", 15);
        result.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "70-79岁");
        item2.put("value", 25);
        result.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "80-89岁");
        item3.put("value", 35);
        result.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "90岁以上");
        item4.put("value", 25);
        result.add(item4);

        return result;
    }

    /**
     * 获取实时动态事件
     */
    @Override
    public List<Map<String, Object>> getRealtimeEvents() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        Map<String, Object> event1 = new HashMap<>();
        event1.put("id", 1);
        event1.put("type", "alert");
        event1.put("title", "老人离床告警");
        event1.put("content", "101房间李老人于10:30离开床位");
        event1.put("time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
        event1.put("level", "warning");
        result.add(event1);

        Map<String, Object> event2 = new HashMap<>();
        event2.put("id", 2);
        event2.put("type", "service");
        event2.put("title", "护理服务完成");
        event2.put("content", "王护士完成对张老人的健康评估");
        event2.put("time", new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis() - 120000)));
        event2.put("level", "success");
        result.add(event2);

        Map<String, Object> event3 = new HashMap<>();
        event3.put("id", 3);
        event3.put("type", "checkin");
        event3.put("title", "老人入住");
        event3.put("content", "刘老人已入住203房间");
        event3.put("time", new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis() - 300000)));
        event3.put("level", "info");
        result.add(event3);

        return result;
    }
}