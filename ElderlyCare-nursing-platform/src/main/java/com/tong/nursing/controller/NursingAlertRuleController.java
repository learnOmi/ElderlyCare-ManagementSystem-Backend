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
import com.tong.nursing.domain.NursingAlertRule;
import com.tong.nursing.service.INursingAlertRuleService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 告警规则Controller
 *
 * @author Tong
 * @date 2026-06-29
 */
@Api(tags = "告警规则管理")
@RestController
@RequestMapping("/nursing/alertRule")
public class NursingAlertRuleController extends BaseController
{
    @Autowired
    private INursingAlertRuleService nursingAlertRuleService;

    /**
     * 查询告警规则列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:list')")
    @ApiOperation(value = "查询告警规则列表", notes = "分页返回告警规则列表")
    @GetMapping("/list")
    public TableDataInfo<NursingAlertRule> list(NursingAlertRule nursingAlertRule)
    {
        startPage();
        List<NursingAlertRule> list = nursingAlertRuleService.selectNursingAlertRuleList(nursingAlertRule);
        TableDataInfo<NursingAlertRule> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出告警规则列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:export')")
    @Log(title = "告警规则", businessType = BusinessType.EXPORT)
    @ApiOperation("导出告警规则列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingAlertRule nursingAlertRule)
    {
        List<NursingAlertRule> list = nursingAlertRuleService.selectNursingAlertRuleList(nursingAlertRule);
        ExcelUtil<NursingAlertRule> util = new ExcelUtil<NursingAlertRule>(NursingAlertRule.class);
        util.exportExcel(response, list, "告警规则数据");
    }

    /**
     * 获取告警规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:query')")
    @ApiOperation(value = "获取告警规则详细信息", notes = "返回单个告警规则对象详情")
    @ApiImplicitParam(name = "id", value = "告警规则ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingAlertRule> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingAlertRuleService.selectNursingAlertRuleById(id));
    }

    /**
     * 新增告警规则
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:add')")
    @Log(title = "告警规则", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增告警规则", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingAlertRule nursingAlertRule)
    {
        int rows = nursingAlertRuleService.insertNursingAlertRule(nursingAlertRule);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改告警规则
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:edit')")
    @Log(title = "告警规则", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改告警规则", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingAlertRule nursingAlertRule)
    {
        int rows = nursingAlertRuleService.updateNursingAlertRule(nursingAlertRule);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除告警规则
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertRule:remove')")
    @Log(title = "告警规则", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除告警规则", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "告警规则ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingAlertRuleService.deleteNursingAlertRuleByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
