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
import com.tong.nursing.domain.NursingProjectPlan;
import com.tong.nursing.service.INursingProjectPlanService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 护理项目计划Controller
 *
 * @author Tong
 * @date 2026-06-25
 */
@Api(tags = "护理项目计划管理")
@RestController
@RequestMapping("/nursing/projectPlan")
public class NursingProjectPlanController extends BaseController
{
    @Autowired
    private INursingProjectPlanService nursingProjectPlanService;

    /**
     * 查询护理项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:list')")
    @ApiOperation(value = "查询护理项目计划列表", notes = "分页返回护理项目计划列表")
    @GetMapping("/list")
    public TableDataInfo<NursingProjectPlan> list(NursingProjectPlan nursingProjectPlan)
    {
        startPage();
        List<NursingProjectPlan> list = nursingProjectPlanService.selectNursingProjectPlanList(nursingProjectPlan);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingProjectPlan> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出护理项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:export')")
    @Log(title = "护理项目计划", businessType = BusinessType.EXPORT)
    @ApiOperation("导出护理项目计划列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingProjectPlan nursingProjectPlan)
    {
        List<NursingProjectPlan> list = nursingProjectPlanService.selectNursingProjectPlanList(nursingProjectPlan);
        ExcelUtil<NursingProjectPlan> util = new ExcelUtil<NursingProjectPlan>(NursingProjectPlan.class);
        util.exportExcel(response, list, "护理项目计划数据");
    }

    /**
     * 获取护理项目计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:query')")
    @ApiOperation(value = "获取护理项目计划详细信息", notes = "返回单个护理项目计划对象详情")
    @ApiImplicitParam(name = "id", value = "护理项目计划ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingProjectPlan> getInfo(@PathVariable("id") Integer id)
    {
        return R.ok(nursingProjectPlanService.selectNursingProjectPlanById(id));
    }

    /**
     * 新增护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:add')")
    @Log(title = "护理项目计划", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理项目计划", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingProjectPlan nursingProjectPlan)
    {
        int rows = nursingProjectPlanService.insertNursingProjectPlan(nursingProjectPlan);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:edit')")
    @Log(title = "护理项目计划", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理项目计划", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingProjectPlan nursingProjectPlan)
    {
        int rows = nursingProjectPlanService.updateNursingProjectPlan(nursingProjectPlan);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:projectPlan:remove')")
    @Log(title = "护理项目计划", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理项目计划", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理项目计划ID数组", required = true, dataType = "Integer[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Integer[] ids)
    {
        int rows = nursingProjectPlanService.deleteNursingProjectPlanByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}