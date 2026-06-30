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
import com.tong.nursing.domain.NursingLevel;
import com.tong.nursing.vo.NursingLevelVO;
import com.tong.nursing.service.INursingLevelService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 护理等级Controller
 *
 * @author Tong
 * @date 2026-06-25
 */
@Api(tags = "护理等级管理")
@RestController
@RequestMapping("/nursing/level")
public class NursingLevelController extends BaseController
{
    @Autowired
    private INursingLevelService nursingLevelService;

    /**
     * 查询护理等级列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:list')")
    @ApiOperation(value = "查询护理等级列表", notes = "分页返回护理等级列表")
    @GetMapping("/list")
    public TableDataInfo<NursingLevel> list(NursingLevel nursingLevel)
    {
        startPage();
        List<NursingLevel> list = nursingLevelService.selectNursingLevelList(nursingLevel);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingLevel> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 查询全部护理等级（下拉框用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:list')")
    @ApiOperation(value = "查询全部护理等级", notes = "下拉框用，返回全部启用的护理等级列表，不分页")
    @GetMapping("/listAll")
    public R<List<NursingLevel>> listAll()
    {
        return R.ok(nursingLevelService.selectNursingLevelAll());
    }

    /**
     * 导出护理等级列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:export')")
    @Log(title = "护理等级", businessType = BusinessType.EXPORT)
    @ApiOperation("导出护理等级列表")
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
    @PreAuthorize("@ss.hasPermi('nursing:level:query')")
    @ApiOperation(value = "获取护理等级详细信息", notes = "返回单个护理等级对象详情")
    @ApiImplicitParam(name = "id", value = "护理等级ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingLevel> getInfo(@PathVariable("id") Integer id)
    {
        return R.ok(nursingLevelService.selectNursingLevelById(id));
    }

    /**
     * 获取护理等级详情（含护理计划名称和项目列表）
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:query')")
    @ApiOperation(value = "获取护理等级详情", notes = "返回护理等级详情（含护理计划名称和项目列表）")
    @ApiImplicitParam(name = "id", value = "护理等级ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/detail/{id}")
    public R<NursingLevelVO> getDetail(@PathVariable("id") Integer id)
    {
        return R.ok(nursingLevelService.selectNursingLevelVOById(id));
    }

    /**
     * 新增护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:add')")
    @Log(title = "护理等级", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理等级", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingLevel nursingLevel)
    {
        int rows = nursingLevelService.insertNursingLevel(nursingLevel);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:edit')")
    @Log(title = "护理等级", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理等级", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingLevel nursingLevel)
    {
        int rows = nursingLevelService.updateNursingLevel(nursingLevel);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除护理等级
     */
    @PreAuthorize("@ss.hasPermi('nursing:level:remove')")
    @Log(title = "护理等级", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理等级", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理等级ID数组", required = true, dataType = "Integer[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Integer[] ids)
    {
        int rows = nursingLevelService.deleteNursingLevelByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}