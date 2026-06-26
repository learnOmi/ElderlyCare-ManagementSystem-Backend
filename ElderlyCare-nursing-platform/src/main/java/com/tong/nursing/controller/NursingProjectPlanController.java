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
import com.tong.nursing.domain.NursingProjectPlan;
import com.tong.nursing.service.INursingProjectPlanService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.page.TableDataInfo;

/**
 * 护理项目计划Controller
 * 
 * @author ruoyi
 * @date 2026-06-25
 */
@RestController
@RequestMapping("/nursing/nursingprojectplan")
public class NursingProjectPlanController extends BaseController
{
    @Autowired
    private INursingProjectPlanService nursingProjectPlanService;

    /**
     * 查询护理项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingProjectPlan nursingProjectPlan)
    {
        startPage();
        List<NursingProjectPlan> list = nursingProjectPlanService.selectNursingProjectPlanList(nursingProjectPlan);
        return getDataTable(list);
    }

    /**
     * 导出护理项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:export')")
    @Log(title = "护理项目计划", businessType = BusinessType.EXPORT)
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
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(nursingProjectPlanService.selectNursingProjectPlanById(id));
    }

    /**
     * 新增护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:add')")
    @Log(title = "护理项目计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NursingProjectPlan nursingProjectPlan)
    {
        return toAjax(nursingProjectPlanService.insertNursingProjectPlan(nursingProjectPlan));
    }

    /**
     * 修改护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:edit')")
    @Log(title = "护理项目计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NursingProjectPlan nursingProjectPlan)
    {
        return toAjax(nursingProjectPlanService.updateNursingProjectPlan(nursingProjectPlan));
    }

    /**
     * 删除护理项目计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursingprojectplan:remove')")
    @Log(title = "护理项目计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(nursingProjectPlanService.deleteNursingProjectPlanByIds(ids));
    }
}
