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
import com.tong.nursing.domain.NursingDevice;
import com.tong.nursing.service.INursingDeviceService;
import com.tong.nursing.vo.DeviceDataVO;
import com.tong.common.utils.poi.ExcelUtil;
import com.tong.common.core.domain.R;
import com.tong.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * 设备Controller
 *
 * @author Tong
 * @date 2026-06-27
 */
@Api(tags = "设备管理")
@RestController
@RequestMapping("/nursing/device")
public class NursingDeviceController extends BaseController
{
    @Autowired
    private INursingDeviceService nursingDeviceService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @ApiOperation(value = "查询设备列表", notes = "分页返回设备列表，支持按设备类型、状态、床位ID筛选")
    @GetMapping("/list")
    public TableDataInfo<NursingDevice> list(NursingDevice nursingDevice)
    {
        startPage();
        List<NursingDevice> list = nursingDeviceService.selectNursingDeviceList(nursingDevice);
        // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
        TableDataInfo<NursingDevice> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @ApiOperation("导出设备列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingDevice nursingDevice)
    {
        List<NursingDevice> list = nursingDeviceService.selectNursingDeviceList(nursingDevice);
        ExcelUtil<NursingDevice> util = new ExcelUtil<NursingDevice>(NursingDevice.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:query')")
    @ApiOperation(value = "获取设备详细信息", notes = "返回单个设备对象详情")
    @ApiImplicitParam(name = "id", value = "设备ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public R<NursingDevice> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(nursingDeviceService.selectNursingDeviceById(id));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增设备", notes = "返回操作结果状态，绑定床位时自动更新床位设备绑定状态")
    @PostMapping
    public R<Void> add(@RequestBody NursingDevice nursingDevice)
    {
        int rows = nursingDeviceService.insertNursingDevice(nursingDevice);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改设备", notes = "返回操作结果状态，床位变化时自动更新双方绑定状态")
    @PutMapping
    public R<Void> edit(@RequestBody NursingDevice nursingDevice)
    {
        int rows = nursingDeviceService.updateNursingDevice(nursingDevice);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除设备", notes = "返回操作结果状态，删除时自动解除床位绑定")
    @ApiImplicitParam(name = "ids", value = "设备ID数组", required = true, dataType = "Long[]", paramType = "path")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids)
    {
        int rows = nursingDeviceService.deleteNursingDeviceByIds(ids);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 查询设备实时数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @ApiOperation(value = "查询设备实时数据", notes = "返回设备的实时监测数据")
    @ApiImplicitParam(name = "id", value = "设备ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/data/{id}")
    public R<DeviceDataVO> getData(@PathVariable("id") Long id)
    {
        return R.ok(nursingDeviceService.getDeviceData(id));
    }

    /**
     * 绑定设备到床位
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "绑定设备到床位", notes = "将设备绑定到指定床位")
    @PutMapping("/bindBed")
    public R<Void> bindBed(@RequestBody BindBedDto dto)
    {
        int rows = nursingDeviceService.bindDeviceBed(dto.getDeviceId(), dto.getBedId());
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 解绑设备
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "解绑设备", notes = "解除设备与床位的绑定")
    @ApiImplicitParam(name = "id", value = "设备ID", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/unbind/{id}")
    public R<Void> unbind(@PathVariable("id") Long id)
    {
        int rows = nursingDeviceService.unbindDevice(id);
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 查询设备历史数据
     */
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @ApiOperation(value = "查询设备历史数据", notes = "返回设备的历史监测数据")
    @ApiImplicitParam(name = "id", value = "设备ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/history/{id}")
    public R<List<DeviceDataVO>> getHistory(@PathVariable("id") Long id, NursingDevice query)
    {
        return R.ok(nursingDeviceService.getDeviceHistory(id, query));
    }

    /**
     * 绑定设备到床位DTO
     */
    @ApiModel(value = "BindBedDto", description = "绑定设备到床位DTO")
    public static class BindBedDto {
        @ApiModelProperty("设备ID")
        private Long deviceId;

        @ApiModelProperty("床位ID")
        private Long bedId;

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
        }

        public Long getBedId() {
            return bedId;
        }

        public void setBedId(Long bedId) {
            this.bedId = bedId;
        }
    }
}