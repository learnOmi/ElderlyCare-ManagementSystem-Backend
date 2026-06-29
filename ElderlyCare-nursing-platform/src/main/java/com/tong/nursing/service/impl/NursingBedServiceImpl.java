package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.domain.NursingBed;
import com.tong.nursing.service.INursingBedService;
import com.tong.nursing.constant.NursingConstants;
import com.tong.nursing.mapper.NursingElderMapper;
import com.tong.nursing.domain.NursingElder;
import com.tong.nursing.mapper.NursingDeviceMapper;
import com.tong.nursing.domain.NursingDevice;

/**
 * 床位Service业务层处理
 *
 * @author Tong
 * @date 2026-06-27
 */
@Service
public class NursingBedServiceImpl implements INursingBedService
{
    @Autowired
    private NursingBedMapper nursingBedMapper;

    @Autowired
    private NursingElderMapper nursingElderMapper;

    @Autowired
    private NursingDeviceMapper nursingDeviceMapper;

    /**
     * 查询床位
     *
     * @param id 床位主键
     * @return 床位
     */
    @Override
    public NursingBed selectNursingBedById(Long id)
    {
        return nursingBedMapper.selectNursingBedById(id);
    }

    /**
     * 查询床位列表
     *
     * @param nursingBed 床位
     * @return 床位
     */
    @Override
    public List<NursingBed> selectNursingBedList(NursingBed nursingBed)
    {
        return nursingBedMapper.selectNursingBedList(nursingBed);
    }

    /**
     * 新增床位
     *
     * @param nursingBed 床位
     * @return 结果
     */
    @Override
    public int insertNursingBed(NursingBed nursingBed)
    {
        if (nursingBed.getBedStatus() == null)
        {
            nursingBed.setBedStatus(NursingConstants.BED_STATUS_FREE);
        }
        if (nursingBed.getDeviceBind() == null)
        {
            nursingBed.setDeviceBind(NursingConstants.BED_DEVICE_UNBIND);
        }
        nursingBed.setCreateTime(DateUtils.getNowDate());
        return nursingBedMapper.insertNursingBed(nursingBed);
    }

    /**
     * 修改床位
     *
     * @param nursingBed 床位
     * @return 结果
     */
    @Override
    public int updateNursingBed(NursingBed nursingBed)
    {
        nursingBed.setUpdateTime(DateUtils.getNowDate());
        return nursingBedMapper.updateNursingBed(nursingBed);
    }

    /**
     * 批量删除床位
     *
     * @param ids 需要删除的床位主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingBedByIds(Long[] ids)
    {
        // 校验每个床位是否有老人绑定
        for (Long id : ids)
        {
            NursingElder elderQuery = new NursingElder();
            elderQuery.setBedId(id);
            List<NursingElder> elderList = nursingElderMapper.selectNursingElderList(elderQuery);
            if (elderList != null && !elderList.isEmpty())
            {
                NursingBed bed = nursingBedMapper.selectNursingBedById(id);
                String bedNo = bed != null ? bed.getBedNo() : "ID:" + id;
                throw new ServiceException("床位【" + bedNo + "】已绑定" + elderList.size() + "位老人，不允许删除");
            }
        }
        // 自动解绑所有关联设备
        for (Long id : ids)
        {
            NursingDevice deviceQuery = new NursingDevice();
            deviceQuery.setBedId(id);
            List<NursingDevice> deviceList = nursingDeviceMapper.selectNursingDeviceList(deviceQuery);
            if (deviceList != null && !deviceList.isEmpty())
            {
                for (NursingDevice device : deviceList)
                {
                    device.setBedId(null);
                    nursingDeviceMapper.updateNursingDevice(device);
                }
            }
        }
        return nursingBedMapper.deleteNursingBedByIds(ids);
    }

    /**
     * 删除床位信息
     *
     * @param id 床位主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingBedById(Long id)
    {
        // 校验床位是否有老人绑定
        NursingElder elderQuery = new NursingElder();
        elderQuery.setBedId(id);
        List<NursingElder> elderList = nursingElderMapper.selectNursingElderList(elderQuery);
        if (elderList != null && !elderList.isEmpty())
        {
            NursingBed bed = nursingBedMapper.selectNursingBedById(id);
            String bedNo = bed != null ? bed.getBedNo() : "ID:" + id;
            throw new ServiceException("床位【" + bedNo + "】已绑定" + elderList.size() + "位老人，不允许删除");
        }
        // 自动解绑所有关联设备
        NursingDevice deviceQuery = new NursingDevice();
        deviceQuery.setBedId(id);
        List<NursingDevice> deviceList = nursingDeviceMapper.selectNursingDeviceList(deviceQuery);
        if (deviceList != null && !deviceList.isEmpty())
        {
            for (NursingDevice device : deviceList)
            {
                device.setBedId(null);
                nursingDeviceMapper.updateNursingDevice(device);
            }
        }
        return nursingBedMapper.deleteNursingBedById(id);
    }
}
