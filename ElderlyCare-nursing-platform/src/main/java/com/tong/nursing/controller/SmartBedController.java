package com.tong.nursing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tong.common.core.controller.BaseController;
import com.tong.common.core.domain.AjaxResult;
import com.tong.common.core.page.TableDataInfo;
import com.tong.nursing.service.ISmartBedService;
import com.tong.nursing.vo.SmartBedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 智能床位监控Controller
 * 提供床位+设备+老人的综合监控视图，不操作独立表
 *
 * @author Tong
 * @date 2026-07-02
 */
@Api(tags = "智能床位监控")
@RestController
@RequestMapping("/nursing/smartBed")
public class SmartBedController extends BaseController {

    @Autowired
    private ISmartBedService smartBedService;

    /**
     * 查询智能床位监控列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:smartBed:list')")
    @ApiOperation(value = "查询智能床位监控列表", notes = "关联查询床位、设备、老人信息，支持按楼层和状态筛选")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(required = false) Long floorId,
                              @RequestParam(required = false) String bedStatus)
    {
        startPage();
        Map<String, Object> params = new HashMap<>();
        params.put("floorId", floorId);
        params.put("bedStatus", bedStatus);
        List<SmartBedVO> list = smartBedService.selectSmartBedList(params);
        return getDataTable(list);
    }

    /**
     * 获取智能床位详情
     */
    @PreAuthorize("@ss.hasPermi('nursing:smartBed:query')")
    @ApiOperation(value = "获取智能床位详情", notes = "根据床位ID查询综合信息")
    @ApiImplicitParam(name = "id", value = "床位ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(smartBedService.selectSmartBedById(id));
    }

    /**
     * 获取床位实时监测数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:smartBed:query')")
    @ApiOperation(value = "获取床位实时数据", notes = "返回心率、呼吸、睡眠等实时监测数据")
    @ApiImplicitParam(name = "id", value = "床位ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/realtime/{id}")
    public AjaxResult getRealtimeData(@PathVariable("id") Long id)
    {
        return AjaxResult.success(smartBedService.getRealtimeData(id));
    }

    /**
     * 获取床位历史监测数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:smartBed:query')")
    @ApiOperation(value = "获取床位历史数据", notes = "返回指定天数内的历史监测数据")
    @ApiImplicitParam(name = "id", value = "床位ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/history/{id}")
    public AjaxResult getHistoryData(@PathVariable("id") Long id,
                                     @RequestParam(defaultValue = "7") int days)
    {
        return AjaxResult.success(smartBedService.getHistoryData(id, days));
    }

    /**
     * 获取床位状态概览
     */
    @PreAuthorize("@ss.hasPermi('nursing:smartBed:list')")
    @ApiOperation(value = "获取床位状态概览", notes = "统计在床、离床、空闲、设备在线数量")
    @GetMapping("/overview")
    public AjaxResult getOverview()
    {
        return AjaxResult.success(smartBedService.getStatusOverview());
    }
}