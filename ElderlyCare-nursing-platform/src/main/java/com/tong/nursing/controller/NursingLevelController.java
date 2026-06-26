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
import com.tong.nursing.domain.NursingLevel;
import com.tong.nursing.service.INursingLevelService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.page.TableDataInfo;

/**
 * 护理等级Controller
 * 
 * @author Tong
 * @date 2026-06-25
 */
@RestController
@RequestMapping("/nursing/nursinglevel")
public class NursingLevelController extends BaseController
{
    @Autowired
    private INursingLevelService nursingLevelService;

    /**
     * 查询护理等级列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingLevel nursingLevel)
    {
        startPage();
        List<NursingLevel> list = nursingLevelService.selectNursingLevelList(nursingLevel);
        return getDataTable(list);
    }

    /**
     * 导出护理等级列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:export')")
    @Log(title = "护理等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingLevel nursingLevel)
    {
        List<NursingLevel> list = nursingLevelService.selectNursingLevelList(nursingLevel);
        ExcelUtil<NursingLevel> util = new ExcelUtil<NursingLevel>(NursingLevel.class);
        util.exportExcel(response, list, "护理等级数据");
    }

    /**
     * 获取护理等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(nursingLevelService.selectNursingLevelById(id));
    }

    /**
     * 新增护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:add')")
    @Log(title = "护理等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NursingLevel nursingLevel)
    {
        return toAjax(nursingLevelService.insertNursingLevel(nursingLevel));
    }

    /**
     * 修改护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:edit')")
    @Log(title = "护理等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NursingLevel nursingLevel)
    {
        return toAjax(nursingLevelService.updateNursingLevel(nursingLevel));
    }

    /**
     * 删除护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:nursinglevel:remove')")
    @Log(title = "护理等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(nursingLevelService.deleteNursingLevelByIds(ids));
    }
}
