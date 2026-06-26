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
import com.tong.common.annotation.Log;
import com.tong.common.core.controller.BaseController;
import com.tong.common.core.domain.AjaxResult;
import com.tong.common.enums.BusinessType;
import com.tong.nursing.domain.NursingPlan;
import com.tong.nursing.service.INursingPlanService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.page.TableDataInfo;

/**
 * 护理计划Controller
 * 
 * @author Tong
 * @date 2026-06-25
 */
@RestController
@RequestMapping("/nursing/nursingplan")
public class NursingPlanController extends BaseController
{
    @Autowired
    private INursingPlanService nursingPlanService;

    /**
     * 查询护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingPlan nursingPlan)
    {
        startPage();
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        return getDataTable(list);
    }

    /**
     * 导出护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:export')")
    @Log(title = "护理计划", businessType = BusinessType.EXPORT)
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
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nursingPlanService.selectNursingPlanById(id));
    }

    /**
     * 新增护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:add')")
    @Log(title = "护理计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NursingPlan nursingPlan)
    {
        return toAjax(nursingPlanService.insertNursingPlan(nursingPlan));
    }

    /**
     * 修改护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:edit')")
    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NursingPlan nursingPlan)
    {
        return toAjax(nursingPlanService.updateNursingPlan(nursingPlan));
    }

    /**
     * 删除护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingplan:remove')")
    @Log(title = "护理计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursingPlanService.deleteNursingPlanByIds(ids));
    }
}
