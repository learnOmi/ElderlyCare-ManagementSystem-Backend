package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingDeviceMapper;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.domain.NursingDevice;
import com.tong.nursing.domain.NursingBed;
import com.tong.nursing.service.INursingDeviceService;
import com.tong.nursing.constant.NursingConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备Service业务层处理
 *
 * @author Tong
 * @date 2026-06-27
 */
@Service
public class NursingDeviceServiceImpl implements INursingDeviceService
{
    @Autowired
    private NursingDeviceMapper nursingDeviceMapper;

    @Autowired
    private NursingBedMapper nursingBedMapper;

    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public NursingDevice selectNursingDeviceById(Long id)
    {
        return nursingDeviceMapper.selectNursingDeviceById(id);
    }

    /**
     * 查询设备列表
     *
     * @param nursingDevice 设备
     * @return 设备
     */
    @Override
    public List<NursingDevice> selectNursingDeviceList(NursingDevice nursingDevice)
    {
        return nursingDeviceMapper.selectNursingDeviceList(nursingDevice);
    }

    /**
     * 新增设备
     *
     * @param nursingDevice 设备
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNursingDevice(NursingDevice nursingDevice)
    {
        nursingDevice.setCreateTime(DateUtils.getNowDate());
        int result = nursingDeviceMapper.insertNursingDevice(nursingDevice);
        if (result > 0 && nursingDevice.getBedId() != null)
        {
            updateBedDeviceBind(nursingDevice.getBedId(), nursingDevice.getDeviceNo(), NursingConstants.BED_DEVICE_BOUND);
        }
        return result;
    }

    /**
     * 修改设备
     *
     * @param nursingDevice 设备
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNursingDevice(NursingDevice nursingDevice)
    {
        NursingDevice oldDevice = nursingDeviceMapper.selectNursingDeviceById(nursingDevice.getId());
        Long oldBedId = oldDevice.getBedId();
        String oldDeviceNo = oldDevice.getDeviceNo();

        nursingDevice.setUpdateTime(DateUtils.getNowDate());
        int result = nursingDeviceMapper.updateNursingDevice(nursingDevice);

        if (result > 0)
        {
            if (oldBedId != null && (nursingDevice.getBedId() == null || !oldBedId.equals(nursingDevice.getBedId())))
            {
                updateBedDeviceBind(oldBedId, null, NursingConstants.BED_DEVICE_UNBIND);
            }
            if (nursingDevice.getBedId() != null && (oldBedId == null || !oldBedId.equals(nursingDevice.getBedId())))
            {
                updateBedDeviceBind(nursingDevice.getBedId(), nursingDevice.getDeviceNo(), NursingConstants.BED_DEVICE_BOUND);
            }
            else if (nursingDevice.getBedId() != null && oldBedId != null && oldBedId.equals(nursingDevice.getBedId()))
            {
                if (oldDeviceNo == null || !oldDeviceNo.equals(nursingDevice.getDeviceNo()))
                {
                    updateBedDeviceBind(nursingDevice.getBedId(), nursingDevice.getDeviceNo(), NursingConstants.BED_DEVICE_BOUND);
                }
            }
        }
        return result;
    }

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingDeviceByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(id);
            if (device != null && device.getBedId() != null)
            {
                updateBedDeviceBind(device.getBedId(), null, NursingConstants.BED_DEVICE_UNBIND);
            }
        }
        return nursingDeviceMapper.deleteNursingDeviceByIds(ids);
    }

    /**
     * 删除设备信息
     *
     * @param id 设备主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingDeviceById(Long id)
    {
        NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(id);
        if (device != null && device.getBedId() != null)
        {
            updateBedDeviceBind(device.getBedId(), null, NursingConstants.BED_DEVICE_UNBIND);
        }
        return nursingDeviceMapper.deleteNursingDeviceById(id);
    }

    /**
     * 更新床位设备绑定状态
     *
     * @param bedId 床位ID
     * @param deviceNo 设备编号
     * @param bindStatus 绑定状态
     */
    private void updateBedDeviceBind(Long bedId, String deviceNo, Integer bindStatus)
    {
        NursingBed bed = new NursingBed();
        bed.setId(bedId);
        bed.setDeviceBind(bindStatus);
        bed.setDeviceNo(deviceNo);
        nursingBedMapper.updateNursingBed(bed);
    }
}
