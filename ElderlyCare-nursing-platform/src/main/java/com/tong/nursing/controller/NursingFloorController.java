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
import com.tong.nursing.domain.NursingFloor;
import com.tong.nursing.service.INursingFloorService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import com.tong.nursing.vo.FloorTreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 楼层Controller
 *
 * @author Tong
 * @date 2026-06-27
 */
@Api(tags = "楼层管理")
@RestController
@RequestMapping("/nursing/floor")
public class NursingFloorController extends BaseController
{
    @Autowired
    private INursingFloorService nursingFloorService;

    /**
     * 查询楼层列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:list')")
    @ApiOperation(value = "查询楼层列表", notes = "分页返回楼层列表")
    @GetMapping("/list")
    public TableDataInfo<NursingFloor> list(NursingFloor nursingFloor)
    {
        startPage();
        List<NursingFloor> list = nursingFloorService.selectNursingFloorList(nursingFloor);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingFloor> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 查询全部楼层（下拉框用）
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:list')")
    @ApiOperation(value = "查询全部楼层", notes = "下拉框用，返回全部启用的楼层列表，不分页")
    @GetMapping("/listAll")
    public R<List<NursingFloor>> listAll()
    {
        return R.ok(nursingFloorService.selectNursingFloorAll());
    }

    /**
     * 查询楼层树形结构（含房间和床位）
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:query')")
    @ApiOperation(value = "查询楼层树形结构", notes = "返回指定楼层→房间→床位三级嵌套结构")
    @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}/tree")
    public R<FloorTreeVO> tree(@PathVariable("id") Long id)
    {
        return R.ok(nursingFloorService.selectFloorTreeById(id));
    }

    /**
     * 导出楼层列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:export')")
    @Log(title = "楼层", businessType = BusinessType.EXPORT)
    @ApiOperation("导出楼层列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingFloor nursingFloor)
    {
        List<NursingFloor> list = nursingFloorService.selectNursingFloorList(nursingFloor);
        ExcelUtil<NursingFloor> util = new ExcelUtil<NursingFloor>(NursingFloor.class);
        util.exportExcel(response, list, "楼层数据");
    }

    /**
     * 获取楼层详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:query')")
    @ApiOperation(value = "获取楼层详细信息", notes = "返回单个楼层对象详情")
    @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingFloor> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingFloorService.selectNursingFloorById(id));
    }

    /**
     * 新增楼层
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:add')")
    @Log(title = "楼层", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增楼层", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingFloor nursingFloor)
    {
        int rows = nursingFloorService.insertNursingFloor(nursingFloor);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改楼层
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:edit')")
    @Log(title = "楼层", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改楼层", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingFloor nursingFloor)
    {
        int rows = nursingFloorService.updateNursingFloor(nursingFloor);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除楼层
     */
    @PreAuthorize("@ss.hasPermi('nursing:floor:remove')")
    @Log(title = "楼层", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除楼层", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "楼层ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingFloorService.deleteNursingFloorByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
