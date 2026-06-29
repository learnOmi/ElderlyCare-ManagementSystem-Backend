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
import com.tong.nursing.domain.NursingAlertData;
import com.tong.nursing.service.INursingAlertDataService;
import com.tong.nursing.vo.NursingAlertDataVO;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 告警数据Controller
 *
 * @author Tong
 * @date 2026-06-29
 */
@Api(tags = "告警数据管理")
@RestController
@RequestMapping("/nursing/alertData")
public class NursingAlertDataController extends BaseController
{
    @Autowired
    private INursingAlertDataService nursingAlertDataService;

    /**
     * 查询告警数据列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:list')")
    @ApiOperation(value = "查询告警数据列表", notes = "分页返回告警数据列表（含老人姓名、床位名称、设备名称、规则名称）")
    @GetMapping("/list")
    public TableDataInfo<NursingAlertDataVO> list(NursingAlertData nursingAlertData)
    {
        startPage();
        List<NursingAlertDataVO> list = nursingAlertDataService.selectAlertDataVOList(nursingAlertData);
        TableDataInfo<NursingAlertDataVO> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出告警数据列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:export')")
    @Log(title = "告警数据", businessType = BusinessType.EXPORT)
    @ApiOperation("导出告警数据列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingAlertData nursingAlertData)
    {
        List<NursingAlertData> list = nursingAlertDataService.selectNursingAlertDataList(nursingAlertData);
        ExcelUtil<NursingAlertData> util = new ExcelUtil<NursingAlertData>(NursingAlertData.class);
        util.exportExcel(response, list, "告警数据");
    }

    /**
     * 获取告警数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:query')")
    @ApiOperation(value = "获取告警数据详细信息", notes = "返回单个告警数据详情（含老人姓名、床位名称、设备名称、规则名称）")
    @ApiImplicitParam(name = "id", value = "告警数据ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingAlertDataVO> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingAlertDataService.selectAlertDataVOById(id));
    }

    /**
     * 新增告警数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:add')")
    @Log(title = "告警数据", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增告警数据", notes = "返回操作结果状态，告警编号自动生成")
    @PostMapping
    public R<Void> add(@RequestBody NursingAlertData nursingAlertData)
    {
        int rows = nursingAlertDataService.insertNursingAlertData(nursingAlertData);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改告警数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:edit')")
    @Log(title = "告警数据", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改告警数据", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingAlertData nursingAlertData)
    {
        int rows = nursingAlertDataService.updateNursingAlertData(nursingAlertData);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 处理告警
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:edit')")
    @Log(title = "告警数据", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "处理告警", notes = "将告警状态改为已处理")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "告警ID", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "handleResult", value = "处理结果", required = true, dataType = "String", paramType = "query")
    })
    @PutMapping("/handle/{id}")
    public R<Void> handle(@PathVariable("id") Long id, @RequestParam String handleResult)
    {
        int rows = nursingAlertDataService.handleAlert(id, handleResult);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 忽略告警
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:edit')")
    @Log(title = "告警数据", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "忽略告警", notes = "将告警状态改为已忽略")
    @ApiImplicitParam(name = "id", value = "告警ID", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/ignore/{id}")
    public R<Void> ignore(@PathVariable("id") Long id)
    {
        int rows = nursingAlertDataService.ignoreAlert(id);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除告警数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:alertData:remove')")
    @Log(title = "告警数据", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除告警数据", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "告警数据ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingAlertDataService.deleteNursingAlertDataByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }
}
