package com.tong.nursing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.tong.common.exception.ServiceException;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingDeviceMapper;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.domain.NursingDevice;
import com.tong.nursing.domain.NursingBed;
import com.tong.nursing.service.INursingDeviceService;
import com.tong.nursing.constant.NursingConstants;
import com.tong.nursing.vo.DeviceDataVO;
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

    /**
     * 查询设备实时数据
     *
     * @param id 设备主键
     * @return 设备实时数据
     */
    @Override
    public DeviceDataVO getDeviceData(Long id)
    {
        NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(id);
        if (device == null)
        {
            throw new ServiceException("设备不存在");
        }

        DeviceDataVO vo = new DeviceDataVO();
        vo.setDeviceId(device.getId());
        vo.setDeviceName(device.getDeviceName());
        vo.setDeviceNo(device.getDeviceNo());
        vo.setMonitorValue("--");
        vo.setUnit("");
        vo.setDataTime(DateUtils.getNowDate());
        vo.setStatus(0);
        vo.setBedId(device.getBedId());

        if (device.getBedId() != null)
        {
            NursingBed bed = nursingBedMapper.selectNursingBedById(device.getBedId());
            if (bed != null)
            {
                vo.setBedNumber(bed.getBedNo());
            }
        }

        return vo;
    }

    /**
     * 绑定设备到床位
     *
     * @param deviceId 设备ID
     * @param bedId 床位ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int bindDeviceBed(Long deviceId, Long bedId)
    {
        NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(deviceId);
        if (device == null)
        {
            throw new ServiceException("设备不存在");
        }

        NursingBed bed = nursingBedMapper.selectNursingBedById(bedId);
        if (bed == null)
        {
            throw new ServiceException("床位不存在");
        }

        device.setBedId(bedId);
        device.setUpdateTime(DateUtils.getNowDate());
        int result = nursingDeviceMapper.updateNursingDevice(device);

        if (result > 0)
        {
            updateBedDeviceBind(bedId, device.getDeviceNo(), NursingConstants.BED_DEVICE_BOUND);
        }

        return result;
    }

    /**
     * 解绑设备
     *
     * @param id 设备主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int unbindDevice(Long id)
    {
        NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(id);
        if (device == null)
        {
            throw new ServiceException("设备不存在");
        }

        if (device.getBedId() == null)
        {
            throw new ServiceException("设备未绑定床位");
        }

        Long bedId = device.getBedId();
        device.setBedId(null);
        device.setUpdateTime(DateUtils.getNowDate());
        int result = nursingDeviceMapper.updateNursingDevice(device);

        if (result > 0)
        {
            updateBedDeviceBind(bedId, null, NursingConstants.BED_DEVICE_UNBIND);
        }

        return result;
    }

    /**
     * 查询设备历史数据
     *
     * @param id 设备主键
     * @param query 查询条件
     * @return 设备历史数据列表
     */
    @Override
    public List<DeviceDataVO> getDeviceHistory(Long id, NursingDevice query)
    {
        NursingDevice device = nursingDeviceMapper.selectNursingDeviceById(id);
        if (device == null)
        {
            throw new ServiceException("设备不存在");
        }

        List<DeviceDataVO> result = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            DeviceDataVO vo = new DeviceDataVO();
            vo.setDeviceId(device.getId());
            vo.setDeviceName(device.getDeviceName());
            vo.setDeviceNo(device.getDeviceNo());
            vo.setMonitorValue("--");
            vo.setUnit("");
            vo.setDataTime(new Date(System.currentTimeMillis() - i * 3600000));
            vo.setStatus(0);
            vo.setBedId(device.getBedId());
            result.add(vo);
        }

        return result;
    }
}
