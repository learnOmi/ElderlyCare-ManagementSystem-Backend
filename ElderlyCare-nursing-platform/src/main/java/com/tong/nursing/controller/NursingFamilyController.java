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
import com.tong.nursing.domain.NursingFamily;
import com.tong.nursing.service.INursingFamilyService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 家属信息Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "家属信息管理")
@RestController
@RequestMapping("/nursing/family")
public class NursingFamilyController extends BaseController
{
    @Autowired
    private INursingFamilyService nursingFamilyService;

    /**
     * 查询家属信息列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:list')")
    @ApiOperation(value = "查询家属信息列表", notes = "分页返回家属信息列表，支持按姓名、手机号筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingFamily> list(NursingFamily nursingFamily)
    {
        startPage();
        List<NursingFamily> list = nursingFamilyService.selectNursingFamilyList(nursingFamily);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingFamily> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出家属信息列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:export')")
    @Log(title = "家属信息", businessType = BusinessType.EXPORT)
    @ApiOperation("导出家属信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingFamily nursingFamily)
    {
        List<NursingFamily> list = nursingFamilyService.selectNursingFamilyList(nursingFamily);
        ExcelUtil<NursingFamily> util = new ExcelUtil<NursingFamily>(NursingFamily.class);
        util.exportExcel(response, list, "家属信息数据");
    }

    /**
     * 获取家属信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:query')")
    @ApiOperation(value = "获取家属信息详细信息", notes = "返回单个家属信息对象详情")
    @ApiImplicitParam(name = "id", value = "家属ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingFamily> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingFamilyService.selectNursingFamilyById(id));
    }

    /**
     * 新增家属信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:add')")
    @Log(title = "家属信息", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增家属信息", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingFamily nursingFamily)
    {
        int rows = nursingFamilyService.insertNursingFamily(nursingFamily);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改家属信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:edit')")
    @Log(title = "家属信息", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改家属信息", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingFamily nursingFamily)
    {
        int rows = nursingFamilyService.updateNursingFamily(nursingFamily);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除家属信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:family:remove')")
    @Log(title = "家属信息", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除家属信息", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "家属ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingFamilyService.deleteNursingFamilyByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
