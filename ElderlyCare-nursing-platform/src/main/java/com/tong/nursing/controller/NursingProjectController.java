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
import com.tong.nursing.domain.NursingProject;
import com.tong.nursing.service.INursingProjectService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 护理项目Controller
 *
 * @author Tong
 * @date 2026-06-23
 */
@Api(tags = "护理项目管理")
@RestController
@RequestMapping("/nursing/project")
public class NursingProjectController extends BaseController
{
    @Autowired
    private INursingProjectService nursingProjectService;

    /**
     * 查询护理项目列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:list')")
    @ApiOperation(value = "查询护理项目列表", notes = "分页返回NursingProject对象列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "成功", response = NursingProject.class)
    })
    @GetMapping("/list")
    public TableDataInfo list(NursingProject nursingProject)
    {
        startPage();
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        return getDataTable(list);
    }

    /**
     * 导出护理项目列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:export')")
    @Log(title = "护理项目", businessType = BusinessType.EXPORT)
    @ApiOperation("导出护理项目列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingProject nursingProject)
    {
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        ExcelUtil<NursingProject> util = new ExcelUtil<NursingProject>(NursingProject.class);
        util.exportExcel(response, list, "护理项目数据");
    }

    /**
     * 获取护理项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:query')")
    @ApiOperation(value = "获取护理项目详细信息", notes = "返回单个NursingProject对象详情")
    @ApiResponses({
        @ApiResponse(code = 200, message = "成功", response = NursingProject.class)
    })
    @ApiImplicitParam(name = "id", value = "护理项目ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nursingProjectService.selectNursingProjectById(id));
    }

    /**
     * 新增护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:add')")
    @Log(title = "护理项目", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理项目", notes = "返回操作结果状态")
    @PostMapping
    public AjaxResult add(@RequestBody NursingProject nursingProject)
    {
        return toAjax(nursingProjectService.insertNursingProject(nursingProject));
    }

    /**
     * 修改护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:edit')")
    @Log(title = "护理项目", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理项目", notes = "返回操作结果状态")
    @PutMapping
    public AjaxResult edit(@RequestBody NursingProject nursingProject)
    {
        return toAjax(nursingProjectService.updateNursingProject(nursingProject));
    }

    /**
     * 删除护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:remove')")
    @Log(title = "护理项目", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理项目", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理项目ID数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursingProjectService.deleteNursingProjectByIds(ids));
    }
}
