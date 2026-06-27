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
import com.tong.nursing.domain.NursingElderFamily;
import com.tong.nursing.service.INursingElderFamilyService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 老人家属关联Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "老人家属关联管理")
@RestController
@RequestMapping("/nursing/elderFamily")
public class NursingElderFamilyController extends BaseController
{
    @Autowired
    private INursingElderFamilyService nursingElderFamilyService;

    /**
     * 查询老人家属关联列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:list')")
    @ApiOperation(value = "查询老人家属关联列表", notes = "分页返回老人家属关联列表，支持按老人ID、家属ID筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingElderFamily> list(NursingElderFamily nursingElderFamily)
    {
        startPage();
        List<NursingElderFamily> list = nursingElderFamilyService.selectNursingElderFamilyList(nursingElderFamily);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingElderFamily> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出老人家属关联列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:export')")
    @Log(title = "老人家属关联", businessType = BusinessType.EXPORT)
    @ApiOperation("导出老人家属关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingElderFamily nursingElderFamily)
    {
        List<NursingElderFamily> list = nursingElderFamilyService.selectNursingElderFamilyList(nursingElderFamily);
        ExcelUtil<NursingElderFamily> util = new ExcelUtil<NursingElderFamily>(NursingElderFamily.class);
        util.exportExcel(response, list, "老人家属关联数据");
    }

    /**
     * 获取老人家属关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:query')")
    @ApiOperation(value = "获取老人家属关联详细信息", notes = "返回单个老人家属关联对象详情")
    @ApiImplicitParam(name = "id", value = "关联ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingElderFamily> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingElderFamilyService.selectNursingElderFamilyById(id));
    }

    /**
     * 新增老人家属关联
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:add')")
    @Log(title = "老人家属关联", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增老人家属关联", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingElderFamily nursingElderFamily)
    {
        int rows = nursingElderFamilyService.insertNursingElderFamily(nursingElderFamily);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改老人家属关联
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:edit')")
    @Log(title = "老人家属关联", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改老人家属关联", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingElderFamily nursingElderFamily)
    {
        int rows = nursingElderFamilyService.updateNursingElderFamily(nursingElderFamily);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除老人家属关联
     */
    @PreAuthorize("@ss.hasPermi('nursing:elderFamily:remove')")
    @Log(title = "老人家属关联", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除老人家属关联", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "关联ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingElderFamilyService.deleteNursingElderFamilyByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
