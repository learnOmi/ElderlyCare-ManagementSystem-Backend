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
import com.tong.nursing.domain.NursingHealthAssessment;
import com.tong.nursing.service.INursingHealthAssessmentService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 健康评估Controller
 *
 * @author Tong
 * @date 2026-06-28
 */
@Api(tags = "健康评估管理")
@RestController
@RequestMapping("/nursing/healthAssessment")
public class NursingHealthAssessmentController extends BaseController
{
    @Autowired
    private INursingHealthAssessmentService nursingHealthAssessmentService;

    /**
     * 查询健康评估列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:list')")
    @ApiOperation(value = "查询健康评估列表", notes = "分页返回健康评估列表")
    @GetMapping("/list")
    public TableDataInfo<NursingHealthAssessment> list(NursingHealthAssessment nursingHealthAssessment)
    {
        startPage();
        List<NursingHealthAssessment> list = nursingHealthAssessmentService.selectNursingHealthAssessmentList(nursingHealthAssessment);
        TableDataInfo<NursingHealthAssessment> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出健康评估列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:export')")
    @Log(title = "健康评估", businessType = BusinessType.EXPORT)
    @ApiOperation("导出健康评估列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingHealthAssessment nursingHealthAssessment)
    {
        List<NursingHealthAssessment> list = nursingHealthAssessmentService.selectNursingHealthAssessmentList(nursingHealthAssessment);
        ExcelUtil<NursingHealthAssessment> util = new ExcelUtil<NursingHealthAssessment>(NursingHealthAssessment.class);
        util.exportExcel(response, list, "健康评估数据");
    }

    /**
     * 获取健康评估详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:query')")
    @ApiOperation(value = "获取健康评估详细信息", notes = "返回单个健康评估对象详情")
    @ApiImplicitParam(name = "id", value = "健康评估ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingHealthAssessment> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingHealthAssessmentService.selectNursingHealthAssessmentById(id));
    }

    /**
     * 新增健康评估
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:add')")
    @Log(title = "健康评估", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增健康评估", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingHealthAssessment nursingHealthAssessment)
    {
        int rows = nursingHealthAssessmentService.insertNursingHealthAssessment(nursingHealthAssessment);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改健康评估
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:edit')")
    @Log(title = "健康评估", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改健康评估", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingHealthAssessment nursingHealthAssessment)
    {
        int rows = nursingHealthAssessmentService.updateNursingHealthAssessment(nursingHealthAssessment);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除健康评估
     */
    @PreAuthorize("@ss.hasPermi('nursing:healthAssessment:remove')")
    @Log(title = "健康评估", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除健康评估", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "健康评估ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingHealthAssessmentService.deleteNursingHealthAssessmentByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
