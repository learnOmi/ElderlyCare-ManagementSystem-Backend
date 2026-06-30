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
import com.tong.nursing.domain.NursingProject;
import com.tong.nursing.service.INursingProjectService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
    @ApiOperation(value = "查询护理项目列表", notes = "分页返回护理项目列表")
    @GetMapping("/list")
    public TableDataInfo<NursingProject> list(NursingProject nursingProject)
    {
        startPage();
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingProject> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 查询全部护理项目（下拉框用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:list')")
    @ApiOperation(value = "查询全部护理项目", notes = "下拉框用，返回全部启用的护理项目列表，不分页")
    @GetMapping("/listAll")
    public R<List<NursingProject>> listAll()
    {
        return R.ok(nursingProjectService.selectNursingProjectAll());
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
    @ApiOperation(value = "获取护理项目详细信息", notes = "返回单个护理项目对象详情")
    @ApiImplicitParam(name = "id", value = "护理项目ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingProject> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingProjectService.selectNursingProjectById(id));
    }

    /**
     * 新增护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:add')")
    @Log(title = "护理项目", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理项目", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingProject nursingProject)
    {
        int rows = nursingProjectService.insertNursingProject(nursingProject);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:edit')")
    @Log(title = "护理项目", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理项目", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingProject nursingProject)
    {
        int rows = nursingProjectService.updateNursingProject(nursingProject);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除护理项目
     */
    @PreAuthorize("@ss.hasPermi('nursing:project:remove')")
    @Log(title = "护理项目", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理项目", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理项目ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingProjectService.deleteNursingProjectByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}