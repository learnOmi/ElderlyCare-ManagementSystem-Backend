package com.tong.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tong.common.annotation.Log;
import com.tong.common.core.controller.BaseController;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import com.tong.common.enums.BusinessType;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.nursing.domain.NursingCheckInConfig;
import com.tong.nursing.service.INursingCheckInConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 入住配置Controller
 *
 * @author Tong
 * @date 2026-06-30
 */
@Api(tags = "入住配置管理")
@RestController
@RequestMapping("/nursing/checkInConfig")
public class NursingCheckInConfigController extends BaseController
{
    @Autowired
    private INursingCheckInConfigService nursingCheckInConfigService;

    /**
     * 查询入住配置列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:list')")
    @ApiOperation(value = "查询入住配置列表", notes = "分页返回入住配置列表")
    @GetMapping("/list")
    public TableDataInfo<NursingCheckInConfig> list(NursingCheckInConfig nursingCheckInConfig)
    {
        startPage();
        List<NursingCheckInConfig> list = nursingCheckInConfigService.selectNursingCheckInConfigList(nursingCheckInConfig);
        TableDataInfo<NursingCheckInConfig> rspData = new TableDataInfo<>();
        rspData.setCode(200);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new com.github.pagehelper.PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出入住配置列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:export')")
    @ApiOperation(value = "导出入住配置列表", notes = "导出入住配置数据")
    @Log(title = "入住配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingCheckInConfig nursingCheckInConfig)
    {
        List<NursingCheckInConfig> list = nursingCheckInConfigService.selectNursingCheckInConfigList(nursingCheckInConfig);
        ExcelUtil<NursingCheckInConfig> util = new ExcelUtil<NursingCheckInConfig>(NursingCheckInConfig.class);
        util.exportExcel(response, list, "入住配置数据");
    }

    /**
     * 获取入住配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:query')")
    @ApiOperation(value = "获取入住配置详细信息", notes = "根据ID获取入住配置详情")
    @GetMapping(value = "/{id}")
    public R<NursingCheckInConfig> getInfo(@ApiParam(name = "id", value = "配置ID", required = true) @PathVariable("id") Long id)
    {
        return R.ok(nursingCheckInConfigService.selectNursingCheckInConfigById(id));
    }

    /**
     * 新增入住配置
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:add')")
    @ApiOperation(value = "新增入住配置", notes = "新增入住配置信息")
    @Log(title = "入住配置", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Integer> add(@RequestBody NursingCheckInConfig nursingCheckInConfig)
    {
        return R.ok(nursingCheckInConfigService.insertNursingCheckInConfig(nursingCheckInConfig));
    }

    /**
     * 修改入住配置
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:edit')")
    @ApiOperation(value = "修改入住配置", notes = "修改入住配置信息")
    @Log(title = "入住配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Integer> edit(@RequestBody NursingCheckInConfig nursingCheckInConfig)
    {
        return R.ok(nursingCheckInConfigService.updateNursingCheckInConfig(nursingCheckInConfig));
    }

    /**
     * 删除入住配置
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkInConfig:remove')")
    @ApiOperation(value = "删除入住配置", notes = "批量删除入住配置")
    @Log(title = "入住配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Integer> remove(@ApiParam(name = "ids", value = "配置ID列表", required = true) @PathVariable Long[] ids)
    {
        return R.ok(nursingCheckInConfigService.deleteNursingCheckInConfigByIds(ids));
    }
}
