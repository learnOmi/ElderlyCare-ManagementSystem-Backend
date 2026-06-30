package com.tong.nursing.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tong.nursing.mapper.NursingReservationMapper;
import com.tong.nursing.domain.NursingReservation;
import com.tong.nursing.service.INursingReservationService;
import com.tong.nursing.constant.NursingConstants;

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
    @Transactional
    public int insertNursingReservation(NursingReservation nursingReservation)
    {
        nursingReservation.setCreateTime(DateUtils.getNowDate());
        // 状态默认为待确认
        if (nursingReservation.getStatus() == null)
        {
            nursingReservation.setStatus(NursingConstants.RESERVATION_STATUS_PENDING);
        }
        // 自动生成预约编号
        if (nursingReservation.getReservationNo() == null || nursingReservation.getReservationNo().isEmpty())
        {
            nursingReservation.setReservationNo(generateReservationNo());
        }
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
     * 确认预约
     *
     * @param id 预约主键
     * @return 结果
     */
    @Override
    @Transactional
    public int confirmReservation(Long id)
    {
        NursingReservation reservation = nursingReservationMapper.selectNursingReservationById(id);
        if (reservation == null)
        {
            throw new ServiceException("预约记录不存在");
        }
        // 只有待确认状态可以确认
        if (!NursingConstants.RESERVATION_STATUS_PENDING.equals(reservation.getStatus()))
        {
            throw new ServiceException("只有待确认状态的预约可以确认，当前状态：" + getStatusText(reservation.getStatus()));
        }
        reservation.setStatus(NursingConstants.RESERVATION_STATUS_CONFIRMED);
        reservation.setUpdateTime(DateUtils.getNowDate());
        return nursingReservationMapper.updateNursingReservation(reservation);
    }

    /**
     * 取消预约
     *
     * @param id 预约主键
     * @param cancelReason 取消原因
     * @return 结果
     */
    @Override
    @Transactional
    public int cancelReservation(Long id, String cancelReason)
    {
        NursingReservation reservation = nursingReservationMapper.selectNursingReservationById(id);
        if (reservation == null)
        {
            throw new ServiceException("预约记录不存在");
        }
        // 待确认和已确认状态可以取消
        if (NursingConstants.RESERVATION_STATUS_PENDING.equals(reservation.getStatus())
            || NursingConstants.RESERVATION_STATUS_CONFIRMED.equals(reservation.getStatus()))
        {
            reservation.setStatus(NursingConstants.RESERVATION_STATUS_CANCELED);
            reservation.setCancelReason(cancelReason);
            reservation.setUpdateTime(DateUtils.getNowDate());
            return nursingReservationMapper.updateNursingReservation(reservation);
        }
        else
        {
            throw new ServiceException("当前状态不允许取消：" + getStatusText(reservation.getStatus()));
        }
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

    /**
     * 生成预约编号
     * 格式: RES-yyyyMMdd-001
     */
    private String generateReservationNo()
    {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 查询当天最大的编号
        String prefix = "RES-" + dateStr + "-";
        List<NursingReservation> list = nursingReservationMapper.selectNursingReservationList(new NursingReservation());
        int maxSeq = 0;
        if (list != null)
        {
            for (NursingReservation r : list)
            {
                if (r.getReservationNo() != null && r.getReservationNo().startsWith(prefix))
                {
                    String seqStr = r.getReservationNo().substring(prefix.length());
                    try
                    {
                        int seq = Integer.parseInt(seqStr);
                        if (seq > maxSeq)
                        {
                            maxSeq = seq;
                        }
                    }
                    catch (NumberFormatException ignored)
                    {
                    }
                }
            }
        }
        return prefix + String.format("%03d", maxSeq + 1);
    }

    /**
     * 获取状态文本
     */
    private String getStatusText(Integer status)
    {
        if (status == null)
        {
            return "未知";
        }
        switch (status)
        {
            case 0: return "待确认";
            case 1: return "已确认";
            case 2: return "已完成";
            case 3: return "已取消";
            default: return "未知";
        }
    }
}
