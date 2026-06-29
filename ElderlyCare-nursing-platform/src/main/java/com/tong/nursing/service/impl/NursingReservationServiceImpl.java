package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingReservationMapper;
import com.tong.nursing.domain.NursingReservation;
import com.tong.nursing.service.INursingReservationService;

/**
 * 预约Service业务层处理
 *
 * @author Tong
 * @date 2026-06-29
 */
@Service
public class NursingReservationServiceImpl implements INursingReservationService
{
    @Autowired
    private NursingReservationMapper nursingReservationMapper;

    /**
     * 查询预约
     *
     * @param id 预约主键
     * @return 预约
     */
    @Override
    public NursingReservation selectNursingReservationById(Long id)
    {
        return nursingReservationMapper.selectNursingReservationById(id);
    }

    /**
     * 查询预约列表
     *
     * @param nursingReservation 预约
     * @return 预约
     */
    @Override
    public List<NursingReservation> selectNursingReservationList(NursingReservation nursingReservation)
    {
        return nursingReservationMapper.selectNursingReservationList(nursingReservation);
    }

    /**
     * 新增预约
     *
     * @param nursingReservation 预约
     * @return 结果
     */
    @Override
    public int insertNursingReservation(NursingReservation nursingReservation)
    {
        nursingReservation.setCreateTime(DateUtils.getNowDate());
        return nursingReservationMapper.insertNursingReservation(nursingReservation);
    }

    /**
     * 修改预约
     *
     * @param nursingReservation 预约
     * @return 结果
     */
    @Override
    public int updateNursingReservation(NursingReservation nursingReservation)
    {
        nursingReservation.setUpdateTime(DateUtils.getNowDate());
        return nursingReservationMapper.updateNursingReservation(nursingReservation);
    }

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的预约主键
     * @return 结果
     */
    @Override
    public int deleteNursingReservationByIds(Long[] ids)
    {
        return nursingReservationMapper.deleteNursingReservationByIds(ids);
    }

    /**
     * 删除预约信息
     *
     * @param id 预约主键
     * @return 结果
     */
    @Override
    public int deleteNursingReservationById(Long id)
    {
        return nursingReservationMapper.deleteNursingReservationById(id);
    }
}
