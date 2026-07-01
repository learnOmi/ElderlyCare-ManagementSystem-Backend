package com.tong.nursing.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tong.nursing.service.INursingDashboardService;
import com.tong.nursing.vo.DashboardStatVO;
import com.tong.nursing.vo.DashboardTrendVO;
import com.tong.nursing.vo.DashboardCategoryVO;
import com.tong.nursing.vo.DashboardRadarVO;
import com.tong.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 仪表盘Controller
 *
 * @author Tong
 * @date 2026-06-29
 */
@Api(tags = "仪表盘统计")
@RestController
@RequestMapping("/nursing/dashboard")
public class NursingDashboardController
{
    @Autowired
    private INursingDashboardService nursingDashboardService;

    /**
     * 统计卡片数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "统计卡片数据", notes = "返回在住老人、空闲床位、待确认预约、待处理告警数量")
    @GetMapping("/stat")
    public R<DashboardStatVO> stat()
    {
        return R.ok(nursingDashboardService.getStatCards());
    }

    /**
     * 趋势图数据（最近7天）
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "趋势图数据", notes = "返回最近7天的预约/入住趋势折线图数据")
    @GetMapping("/trend")
    public R<DashboardTrendVO> trend()
    {
        return R.ok(nursingDashboardService.getTrendData());
    }

    /**
     * 饼图数据（床位状态分布）
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "饼图数据", notes = "返回床位状态分布数据")
    @GetMapping("/pie")
    public R<List<DashboardCategoryVO>> pie()
    {
        return R.ok(nursingDashboardService.getPieData());
    }

    /**
     * 柱状图数据（各楼层入住人数）
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "柱状图数据", notes = "返回各楼层入住人数数据")
    @GetMapping("/bar")
    public R<List<DashboardCategoryVO>> bar()
    {
        return R.ok(nursingDashboardService.getBarData());
    }

    /**
     * 雷达图数据（各护理等级老人分布）
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "雷达图数据", notes = "返回护理等级维度雷达图数据")
    @GetMapping("/radar")
    public R<DashboardRadarVO> radar()
    {
        return R.ok(nursingDashboardService.getRadarData());
    }

    /**
     * 待办事项统计
     */
    @PreAuthorize("@ss.hasPermi('nursing:dashboard:query')")
    @ApiOperation(value = "待办事项统计", notes = "返回待处理告警、待处理入住申请、待续签合同数量")
    @GetMapping("/backlog")
    public R<DashboardStatVO> backlog()
    {
        return R.ok(nursingDashboardService.getBacklog());
    }
}
