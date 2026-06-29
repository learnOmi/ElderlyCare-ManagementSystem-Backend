package com.tong.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.tong.common.annotation.Log;
import com.tong.common.constant.HttpStatus;
import com.tong.common.core.controller.BaseController;
import com.tong.common.enums.BusinessType;
import com.tong.nursing.domain.NursingReservation;
import com.tong.nursing.service.INursingReservationService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 预约Controller
 *
 * @author Tong
 * @date 2026-06-29
 */
@Api(tags = "预约管理")
@RestController
@RequestMapping("/nursing/reservation")
public class NursingReservationController extends BaseController
{
    @Autowired
    private INursingReservationService nursingReservationService;

    /**
     * 查询预约列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:list')")
    @ApiOperation(value = "查询预约列表", notes = "分页返回预约列表")
    @GetMapping("/list")
    public TableDataInfo<NursingReservation> list(NursingReservation nursingReservation)
    {
        startPage();
        List<NursingReservation> list = nursingReservationService.selectNursingReservationList(nursingReservation);
        TableDataInfo<NursingReservation> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出预约列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:export')")
    @Log(title = "预约", businessType = BusinessType.EXPORT)
    @ApiOperation("导出预约列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingReservation nursingReservation)
    {
        List<NursingReservation> list = nursingReservationService.selectNursingReservationList(nursingReservation);
        ExcelUtil<NursingReservation> util = new ExcelUtil<NursingReservation>(NursingReservation.class);
        util.exportExcel(response, list, "预约数据");
    }

    /**
     * 获取预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:query')")
    @ApiOperation(value = "获取预约详细信息", notes = "返回单个预约对象详情")
    @ApiImplicitParam(name = "id", value = "预约ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingReservation> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingReservationService.selectNursingReservationById(id));
    }

    /**
     * 新增预约
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:add')")
    @Log(title = "预约", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增预约", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingReservation nursingReservation)
    {
        int rows = nursingReservationService.insertNursingReservation(nursingReservation);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改预约
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:edit')")
    @Log(title = "预约", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改预约", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingReservation nursingReservation)
    {
        int rows = nursingReservationService.updateNursingReservation(nursingReservation);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除预约
     */
    @PreAuthorize("@ss.hasPermi('nursing:reservation:remove')")
    @Log(title = "预约", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除预约", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "预约ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingReservationService.deleteNursingReservationByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
