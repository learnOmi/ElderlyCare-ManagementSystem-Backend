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
import com.tong.nursing.domain.NursingRoomType;
import com.tong.nursing.service.INursingRoomTypeService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 房型Controller
 *
 * @author Tong
 * @date 2026-06-27
 */
@Api(tags = "房型管理")
@RestController
@RequestMapping("/nursing/roomType")
public class NursingRoomTypeController extends BaseController
{
    @Autowired
    private INursingRoomTypeService nursingRoomTypeService;

    /**
     * 查询房型列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:list')")
    @ApiOperation(value = "查询房型列表", notes = "分页返回房型列表")
    @GetMapping("/list")
    public TableDataInfo<NursingRoomType> list(NursingRoomType nursingRoomType)
    {
        startPage();
        List<NursingRoomType> list = nursingRoomTypeService.selectNursingRoomTypeList(nursingRoomType);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingRoomType> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出房型列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:export')")
    @Log(title = "房型", businessType = BusinessType.EXPORT)
    @ApiOperation("导出房型列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingRoomType nursingRoomType)
    {
        List<NursingRoomType> list = nursingRoomTypeService.selectNursingRoomTypeList(nursingRoomType);
        ExcelUtil<NursingRoomType> util = new ExcelUtil<NursingRoomType>(NursingRoomType.class);
        util.exportExcel(response, list, "房型数据");
    }

    /**
     * 获取房型详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:query')")
    @ApiOperation(value = "获取房型详细信息", notes = "返回单个房型对象详情")
    @ApiImplicitParam(name = "id", value = "房型ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingRoomType> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingRoomTypeService.selectNursingRoomTypeById(id));
    }

    /**
     * 新增房型
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:add')")
    @Log(title = "房型", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增房型", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingRoomType nursingRoomType)
    {
        int rows = nursingRoomTypeService.insertNursingRoomType(nursingRoomType);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改房型
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:edit')")
    @Log(title = "房型", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改房型", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingRoomType nursingRoomType)
    {
        int rows = nursingRoomTypeService.updateNursingRoomType(nursingRoomType);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除房型
     */
    @PreAuthorize("@ss.hasPermi('nursing:roomType:remove')")
    @Log(title = "房型", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除房型", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "房型ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingRoomTypeService.deleteNursingRoomTypeByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}