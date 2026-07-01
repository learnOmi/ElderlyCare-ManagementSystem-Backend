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
import com.tong.nursing.domain.NursingCheckIn;
import com.tong.nursing.dto.CheckInApplyDto;
import com.tong.nursing.service.INursingCheckInService;
import com.tong.nursing.vo.NursingCheckInDetailVO;
import com.tong.nursing.vo.NursingCheckInListVO;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 入住申请Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "入住申请管理")
@RestController
@RequestMapping("/nursing/checkIn")
public class NursingCheckInController extends BaseController
{
    @Autowired
    private INursingCheckInService nursingCheckInService;

    /**
     * 查询入住申请列表（包含老人详细信息和护理等级）
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:list')")
    @ApiOperation(value = "查询入住申请列表", notes = "分页返回入住申请列表，支持按老人ID、床位ID、状态、老人姓名筛选，返回老人详细信息和护理等级信息")
    @GetMapping("/list")
    public TableDataInfo<NursingCheckInListVO> list(NursingCheckIn nursingCheckIn)
    {
        startPage();
        List<NursingCheckInListVO> list = nursingCheckInService.selectCheckInList(nursingCheckIn);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingCheckInListVO> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出入住申请列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:export')")
    @Log(title = "入住申请", businessType = BusinessType.EXPORT)
    @ApiOperation("导出入住申请列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingCheckIn nursingCheckIn)
    {
        List<NursingCheckIn> list = nursingCheckInService.selectNursingCheckInList(nursingCheckIn);
        ExcelUtil<NursingCheckIn> util = new ExcelUtil<NursingCheckIn>(NursingCheckIn.class);
        util.exportExcel(response, list, "入住申请数据");
    }

    /**
     * 获取入住申请详细信息（含老人、家属、床位、房间、楼层、护理等级等完整关联信息）
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:query')")
    @ApiOperation(value = "获取入住申请详情", notes = "返回入住申请完整详情，包含老人信息、家属列表、床位、房间、楼层、房型、护理等级、护理计划等关联信息")
    @ApiImplicitParam(name = "id", value = "入住ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/detail/{id}")
    public R<NursingCheckInDetailVO> getDetail(@PathVariable("id") Long id)
    {
        return R.ok(nursingCheckInService.selectCheckInDetailById(id));
    }

    /**
     * 获取入住申请基本信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:query')")
    @ApiOperation(value = "获取入住申请基本信息", notes = "返回单个入住申请对象详情（基本信息）")
    @ApiImplicitParam(name = "id", value = "入住ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingCheckIn> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingCheckInService.selectNursingCheckInById(id));
    }

    /**
     * 申请入住（完整业务流程）
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:add')")
    @Log(title = "入住申请", businessType = BusinessType.INSERT)
    @ApiOperation(value = "申请入住", notes = "执行完整入住申请流程：校验老人入住状态→校验床位状态→更新床位状态→新增/更新老人信息→新增合同→新增入住记录→新增入住配置→保存家属列表")
    @PostMapping("/apply")
    public R<Long> apply(@RequestBody CheckInApplyDto dto)
    {
        Long checkInId = nursingCheckInService.apply(dto);
        return R.ok(checkInId);
    }

    /**
     * 新增入住申请
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:add')")
    @Log(title = "入住申请", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增入住申请", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingCheckIn nursingCheckIn)
    {
        int rows = nursingCheckInService.insertNursingCheckIn(nursingCheckIn);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改入住申请
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:edit')")
    @Log(title = "入住申请", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改入住申请", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingCheckIn nursingCheckIn)
    {
        int rows = nursingCheckInService.updateNursingCheckIn(nursingCheckIn);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除入住申请
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:remove')")
    @Log(title = "入住申请", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除入住申请", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "入住ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingCheckInService.deleteNursingCheckInByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
