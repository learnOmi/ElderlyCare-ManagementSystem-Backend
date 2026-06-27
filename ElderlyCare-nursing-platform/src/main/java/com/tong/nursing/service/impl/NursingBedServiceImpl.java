package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.domain.NursingBed;
import com.tong.nursing.service.INursingBedService;
import com.tong.nursing.constant.NursingConstants;

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
    public int deleteNursingBedByIds(Long[] ids)
    {
        return nursingBedMapper.deleteNursingBedByIds(ids);
    }

    /**
     * 删除床位信息
     *
     * @param id 床位主键
     * @return 结果
     */
    @Override
    public int deleteNursingBedById(Long id)
    {
        return nursingBedMapper.deleteNursingBedById(id);
    }
}
