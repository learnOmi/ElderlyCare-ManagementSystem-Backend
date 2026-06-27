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
import com.tong.nursing.domain.NursingBed;
import com.tong.nursing.service.INursingBedService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 床位Controller
 *
 * @author Tong
 * @date 2026-06-27
 */
@Api(tags = "床位管理")
@RestController
@RequestMapping("/nursing/bed")
public class NursingBedController extends BaseController
{
    @Autowired
    private INursingBedService nursingBedService;

    /**
     * 查询床位列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:list')")
    @ApiOperation(value = "查询床位列表", notes = "分页返回床位列表，支持按房间ID、楼层ID筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingBed> list(NursingBed nursingBed)
    {
        startPage();
        List<NursingBed> list = nursingBedService.selectNursingBedList(nursingBed);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingBed> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出床位列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:export')")
    @Log(title = "床位", businessType = BusinessType.EXPORT)
    @ApiOperation("导出床位列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingBed nursingBed)
    {
        List<NursingBed> list = nursingBedService.selectNursingBedList(nursingBed);
        ExcelUtil<NursingBed> util = new ExcelUtil<NursingBed>(NursingBed.class);
        util.exportExcel(response, list, "床位数据");
    }

    /**
     * 获取床位详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:query')")
    @ApiOperation(value = "获取床位详细信息", notes = "返回单个床位对象详情")
    @ApiImplicitParam(name = "id", value = "床位ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingBed> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingBedService.selectNursingBedById(id));
    }

    /**
     * 新增床位
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:add')")
    @Log(title = "床位", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增床位", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingBed nursingBed)
    {
        int rows = nursingBedService.insertNursingBed(nursingBed);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改床位
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:edit')")
    @Log(title = "床位", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改床位", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingBed nursingBed)
    {
        int rows = nursingBedService.updateNursingBed(nursingBed);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除床位
     */
    @PreAuthorize("@ss.hasPermi('nursing:bed:remove')")
    @Log(title = "床位", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除床位", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "床位ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingBedService.deleteNursingBedByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}