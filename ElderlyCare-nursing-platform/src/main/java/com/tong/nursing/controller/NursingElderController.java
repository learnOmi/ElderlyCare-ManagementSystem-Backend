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
import com.tong.nursing.domain.NursingElder;
import com.tong.nursing.service.INursingElderService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 老人信息Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "老人信息管理")
@RestController
@RequestMapping("/nursing/elder")
public class NursingElderController extends BaseController
{
    @Autowired
    private INursingElderService nursingElderService;

    /**
     * 查询老人信息列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:list')")
    @ApiOperation(value = "查询老人信息列表", notes = "分页返回老人信息列表，支持按姓名、身份证、手机号筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingElder> list(NursingElder nursingElder)
    {
        startPage();
        List<NursingElder> list = nursingElderService.selectNursingElderList(nursingElder);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingElder> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出老人信息列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:export')")
    @Log(title = "老人信息", businessType = BusinessType.EXPORT)
    @ApiOperation("导出老人信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingElder nursingElder)
    {
        List<NursingElder> list = nursingElderService.selectNursingElderList(nursingElder);
        ExcelUtil<NursingElder> util = new ExcelUtil<NursingElder>(NursingElder.class);
        util.exportExcel(response, list, "老人信息数据");
    }

    /**
     * 获取老人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:query')")
    @ApiOperation(value = "获取老人信息详细信息", notes = "返回单个老人信息对象详情")
    @ApiImplicitParam(name = "id", value = "老人ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingElder> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingElderService.selectNursingElderById(id));
    }

    /**
     * 新增老人信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:add')")
    @Log(title = "老人信息", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增老人信息", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingElder nursingElder)
    {
        int rows = nursingElderService.insertNursingElder(nursingElder);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改老人信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:edit')")
    @Log(title = "老人信息", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改老人信息", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingElder nursingElder)
    {
        int rows = nursingElderService.updateNursingElder(nursingElder);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除老人信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:elder:remove')")
    @Log(title = "老人信息", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除老人信息", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "老人ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingElderService.deleteNursingElderByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
