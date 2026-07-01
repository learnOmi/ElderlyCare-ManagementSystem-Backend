package com.tong.nursing.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tong.nursing.service.INursingDataScreenService;
import com.tong.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;

/**
 * 数据大屏Controller
 *
 * @author Tong
 * @date 2026-07-01
 */
@Api(tags = "数据大屏")
@RestController
@RequestMapping("/nursing/dataScreen")
public class NursingDataScreenController {

    @Autowired
    private INursingDataScreenService nursingDataScreenService;

    /**
     * 获取大屏核心统计数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取大屏核心统计数据", notes = "返回在住老人数、床位总数、空闲床位数、待处理告警数、今日服务数")
    @GetMapping("/statistics")
    public R<Map<String, Object>> getStatistics() {
        return R.ok(nursingDataScreenService.getStatistics());
    }

    /**
     * 获取月度入退住趋势
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取月度入退住趋势", notes = "返回最近N个月的入住和退住趋势数据")
    @ApiImplicitParam(name = "months", value = "月份数", required = false, dataType = "Integer", paramType = "query", defaultValue = "6")
    @GetMapping("/enterTrend")
    public R<Map<String, Object>> getEnterTrend(
            @RequestParam(value = "months", defaultValue = "6") Integer months) {
        return R.ok(nursingDataScreenService.getEnterTrend(months));
    }

    /**
     * 获取护理等级分布
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取护理等级分布", notes = "返回各护理等级老人数量分布")
    @GetMapping("/levelDistribution")
    public R<List<Map<String, Object>>> getLevelDistribution() {
        return R.ok(nursingDataScreenService.getLevelDistribution());
    }

    /**
     * 获取楼层入住率
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取楼层入住率", notes = "返回各楼层入住人数")
    @GetMapping("/floorOccupancy")
    public R<List<Map<String, Object>>> getFloorOccupancy() {
        return R.ok(nursingDataScreenService.getFloorOccupancy());
    }

    /**
     * 获取告警趋势
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取告警趋势", notes = "返回最近N天的告警数量趋势")
    @ApiImplicitParam(name = "days", value = "天数", required = false, dataType = "Integer", paramType = "query", defaultValue = "7")
    @GetMapping("/alertTrend")
    public R<Map<String, Object>> getAlertTrend(
            @RequestParam(value = "days", defaultValue = "7") Integer days) {
        return R.ok(nursingDataScreenService.getAlertTrend(days));
    }

    /**
     * 获取服务类型数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取服务类型数据", notes = "返回各服务类型占比")
    @GetMapping("/serviceTypeData")
    public R<List<Map<String, Object>>> getServiceTypeData() {
        return R.ok(nursingDataScreenService.getServiceTypeData());
    }

    /**
     * 获取年龄分布
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取年龄分布", notes = "返回各年龄段老人数量分布")
    @GetMapping("/ageDistribution")
    public R<List<Map<String, Object>>> getAgeDistribution() {
        return R.ok(nursingDataScreenService.getAgeDistribution());
    }

    /**
     * 获取实时动态事件
     */
    @PreAuthorize("@ss.hasPermi('nursing:dataScreen:query')")
    @ApiOperation(value = "获取实时动态事件", notes = "返回最近的告警、服务、入住等动态事件")
    @GetMapping("/realtimeEvents")
    public R<List<Map<String, Object>>> getRealtimeEvents() {
        return R.ok(nursingDataScreenService.getRealtimeEvents());
    }
}