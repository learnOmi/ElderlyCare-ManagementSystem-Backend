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
import com.tong.nursing.domain.NursingContract;
import com.tong.nursing.service.INursingContractService;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合同Controller
 *
 * @author Tong
 * @date 2026-06-29
 */
@Api(tags = "合同管理")
@RestController
@RequestMapping("/nursing/contract")
public class NursingContractController extends BaseController
{
    @Autowired
    private INursingContractService nursingContractService;

    /**
     * 查询合同列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:list')")
    @ApiOperation(value = "查询合同列表", notes = "分页返回合同列表")
    @GetMapping("/list")
    public TableDataInfo<NursingContract> list(NursingContract nursingContract)
    {
        startPage();
        List<NursingContract> list = nursingContractService.selectNursingContractList(nursingContract);
        TableDataInfo<NursingContract> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出合同列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @ApiOperation("导出合同列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingContract nursingContract)
    {
        List<NursingContract> list = nursingContractService.selectNursingContractList(nursingContract);
        ExcelUtil<NursingContract> util = new ExcelUtil<NursingContract>(NursingContract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:query')")
    @ApiOperation(value = "获取合同详细信息", notes = "返回单个合同对象详情")
    @ApiImplicitParam(name = "id", value = "合同ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingContract> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingContractService.selectNursingContractById(id));
    }

    /**
     * 新增合同
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增合同", notes = "返回操作结果状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingContract nursingContract)
    {
        int rows = nursingContractService.insertNursingContract(nursingContract);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改合同
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改合同", notes = "返回操作结果状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingContract nursingContract)
    {
        int rows = nursingContractService.updateNursingContract(nursingContract);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除合同
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除合同", notes = "返回操作结果状态")
    @ApiImplicitParam(name = "ids", value = "合同ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingContractService.deleteNursingContractByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 合同续签
     */
    @PreAuthorize("@ss.hasPermi('nursing:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "合同续签", notes = "根据原合同ID创建新合同，更新开始日期和结束日期，原合同状态改为已到期")
    @ApiImplicitParam(name = "id", value = "原合同ID", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/renew/{id}")
    public R<Long> renew(@PathVariable("id") Long id, @RequestBody NursingContract contract)
    {
        Long newId = nursingContractService.renewContract(id, contract);
        return R.ok(newId);
    }
}
