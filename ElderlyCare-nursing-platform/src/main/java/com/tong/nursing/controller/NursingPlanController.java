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
import com.tong.nursing.domain.NursingPlan;
import com.tong.nursing.dto.NursingPlanDTO;
import com.tong.nursing.vo.NursingPlanVO;
import com.tong.nursing.service.INursingPlanService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 护理计划Controller
 *
 * @author Tong
 * @date 2026-06-25
 */
@Api(tags = "护理计划管理")
@RestController
@RequestMapping("/nursing/plan")
public class NursingPlanController extends BaseController
{
    @Autowired
    private INursingPlanService nursingPlanService;

    /**
     * 查询护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:list')")
    @ApiOperation(value = "查询护理计划列表", notes = "分页返回护理计划列表")
    @GetMapping("/list")
    public TableDataInfo<NursingPlan> list(NursingPlan nursingPlan)
    {
        startPage();
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingPlan> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 查询全部护理计划（下拉框用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:list')")
    @ApiOperation(value = "查询全部护理计划", notes = "下拉框用，返回全部启用的护理计划列表，不分页")
    @GetMapping("/listAll")
    public R<List<NursingPlan>> listAll()
    {
        return R.ok(nursingPlanService.selectNursingPlanAll());
    }

    /**
     * 导出护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:export')")
    @Log(title = "护理计划", businessType = BusinessType.EXPORT)
    @ApiOperation("导出护理计划列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingPlan nursingPlan)
    {
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        ExcelUtil<NursingPlan> util = new ExcelUtil<NursingPlan>(NursingPlan.class);
        util.exportExcel(response, list, "护理计划数据");
    }

    /**
     * 获取护理计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:query')")
    @ApiOperation(value = "获取护理计划详细信息", notes = "返回单个护理计划对象详情")
    @ApiImplicitParam(name = "id", value = "护理计划ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingPlan> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingPlanService.selectNursingPlanById(id));
    }

    /**
     * 获取护理计划详情（含项目列表）
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:query')")
    @ApiOperation(value = "获取护理计划详情", notes = "返回护理计划详情（含关联项目列表）")
    @ApiImplicitParam(name = "id", value = "护理计划ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/detail/{id}")
    public R<NursingPlanVO> getDetail(@PathVariable("id") Long id)
    {
        return R.ok(nursingPlanService.selectNursingPlanVOById(id));
    }

    /**
     * 新增护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:add')")
    @Log(title = "护理计划", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理计划", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingPlanDTO dto)
    {
        int rows = nursingPlanService.insertNursingPlan(dto);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:edit')")
    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理计划", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingPlanDTO dto)
    {
        int rows = nursingPlanService.updateNursingPlan(dto);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:remove')")
    @Log(title = "护理计划", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理计划", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理计划ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingPlanService.deleteNursingPlanByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}