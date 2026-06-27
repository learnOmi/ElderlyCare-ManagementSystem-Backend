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
import com.tong.nursing.domain.NursingRoom;
import com.tong.nursing.service.INursingRoomService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 房间Controller
 *
 * @author Tong
 * @date 2026-06-27
 */
@Api(tags = "房间管理")
@RestController
@RequestMapping("/nursing/room")
public class NursingRoomController extends BaseController
{
    @Autowired
    private INursingRoomService nursingRoomService;

    /**
     * 查询房间列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:list')")
    @ApiOperation(value = "查询房间列表", notes = "分页返回房间列表，支持按楼层ID筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingRoom> list(NursingRoom nursingRoom)
    {
        startPage();
        List<NursingRoom> list = nursingRoomService.selectNursingRoomList(nursingRoom);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingRoom> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出房间列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:export')")
    @Log(title = "房间", businessType = BusinessType.EXPORT)
    @ApiOperation("导出房间列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingRoom nursingRoom)
    {
        List<NursingRoom> list = nursingRoomService.selectNursingRoomList(nursingRoom);
        ExcelUtil<NursingRoom> util = new ExcelUtil<NursingRoom>(NursingRoom.class);
        util.exportExcel(response, list, "房间数据");
    }

    /**
     * 获取房间详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:query')")
    @ApiOperation(value = "获取房间详细信息", notes = "返回单个房间对象详情")
    @ApiImplicitParam(name = "id", value = "房间ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingRoom> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingRoomService.selectNursingRoomById(id));
    }

    /**
     * 新增房间
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:add')")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增房间", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingRoom nursingRoom)
    {
        int rows = nursingRoomService.insertNursingRoom(nursingRoom);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改房间
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:edit')")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改房间", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingRoom nursingRoom)
    {
        int rows = nursingRoomService.updateNursingRoom(nursingRoom);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除房间
     */
    @PreAuthorize("@ss.hasPermi('nursing:room:remove')")
    @Log(title = "房间", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除房间", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "房间ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingRoomService.deleteNursingRoomByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}