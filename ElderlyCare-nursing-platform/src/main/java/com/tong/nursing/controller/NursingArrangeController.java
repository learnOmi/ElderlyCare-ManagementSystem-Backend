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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.tong.common.annotation.Log;
import com.tong.common.constant.HttpStatus;
import com.tong.common.core.controller.BaseController;
import com.tong.common.enums.BusinessType;
import com.tong.nursing.domain.NursingArrange;
import com.tong.nursing.service.INursingArrangeService;
import com.tong.nursing.vo.NursingArrangeVO;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 护理排班Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "护理排班管理")
@RestController
@RequestMapping("/nursing/arrange")
public class NursingArrangeController extends BaseController
{
    @Autowired
    private INursingArrangeService nursingArrangeService;

    /**
     * 查询护理排班列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:list')")
    @ApiOperation(value = "查询护理排班列表", notes = "分页返回护理排班列表（含老人姓名、项目名称、护理员姓名）")
    @GetMapping("/list")
    public TableDataInfo<NursingArrangeVO> list(NursingArrange nursingArrange)
    {
        startPage();
        List<NursingArrangeVO> list = nursingArrangeService.selectArrangeVOList(nursingArrange);
        TableDataInfo<NursingArrangeVO> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出护理排班列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:export')")
    @Log(title = "护理排班", businessType = BusinessType.EXPORT)
    @ApiOperation("导出护理排班列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingArrange nursingArrange)
    {
        List<NursingArrange> list = nursingArrangeService.selectNursingArrangeList(nursingArrange);
        ExcelUtil<NursingArrange> util = new ExcelUtil<NursingArrange>(NursingArrange.class);
        util.exportExcel(response, list, "护理排班数据");
    }

    /**
     * 获取护理排班详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:query')")
    @ApiOperation(value = "获取护理排班详细信息", notes = "返回单个护理排班详情（含老人姓名、项目名称、护理员姓名）")
    @ApiImplicitParam(name = "id", value = "护理排班ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingArrangeVO> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingArrangeService.selectArrangeVOById(id));
    }

    /**
     * 新增护理排班
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:add')")
    @Log(title = "护理排班", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增护理排班", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingArrange nursingArrange)
    {
        int rows = nursingArrangeService.insertNursingArrange(nursingArrange);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改护理排班
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:edit')")
    @Log(title = "护理排班", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改护理排班", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingArrange nursingArrange)
    {
        int rows = nursingArrangeService.updateNursingArrange(nursingArrange);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除护理排班
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:remove')")
    @Log(title = "护理排班", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除护理排班", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "护理排班ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingArrangeService.deleteNursingArrangeByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 执行护理排班
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:edit')")
    @Log(title = "护理排班", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "执行护理排班", notes = "将排班状态从待执行改为已执行")
    @ApiImplicitParam(name = "id", value = "排班ID", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/execute/{id}")
    public R<Void> execute(@PathVariable("id") Long id)
    {
        int rows = nursingArrangeService.executeArrange(id);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 取消护理排班
     */
    @PreAuthorize("@ss.hasPermi('nursing:arrange:edit')")
    @Log(title = "护理排班", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "取消护理排班", notes = "将排班状态改为已取消")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "排班ID", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "cancelReason", value = "取消原因", required = false, dataType = "String", paramType = "query")
    })
    @PutMapping("/cancel/{id}")
    public R<Void> cancel(@PathVariable("id") Long id, @RequestParam(required = false) String cancelReason)
    {
        int rows = nursingArrangeService.cancelArrange(id, cancelReason);
        return rows > 0 ? R.ok() : R.fail();
    }
}
