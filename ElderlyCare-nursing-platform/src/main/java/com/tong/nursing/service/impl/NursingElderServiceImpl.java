package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingElderMapper;
import com.tong.nursing.domain.NursingElder;
import com.tong.nursing.service.INursingElderService;
import com.tong.nursing.mapper.NursingCheckInMapper;
import com.tong.nursing.domain.NursingCheckIn;
import com.tong.nursing.mapper.NursingContractMapper;
import com.tong.nursing.domain.NursingContract;

/**
 * 老人信息Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingElderServiceImpl implements INursingElderService
{
    @Autowired
    private NursingElderMapper nursingElderMapper;

    @Autowired
    private NursingCheckInMapper nursingCheckInMapper;

    @Autowired
    private NursingContractMapper nursingContractMapper;

    /**
     * 查询老人信息
     *
     * @param id 老人信息主键
     * @return 老人信息
     */
    @Override
    public NursingElder selectNursingElderById(Long id)
    {
        return nursingElderMapper.selectNursingElderById(id);
    }

    /**
     * 查询老人信息列表
     *
     * @param nursingElder 老人信息
     * @return 老人信息
     */
    @Override
    public List<NursingElder> selectNursingElderList(NursingElder nursingElder)
    {
        return nursingElderMapper.selectNursingElderList(nursingElder);
    }

    /**
     * 新增老人信息
     *
     * @param nursingElder 老人信息
     * @return 结果
     */
    @Override
    public int insertNursingElder(NursingElder nursingElder)
    {
        nursingElder.setCreateTime(DateUtils.getNowDate());
        return nursingElderMapper.insertNursingElder(nursingElder);
    }

    /**
     * 修改老人信息
     *
     * @param nursingElder 老人信息
     * @return 结果
     */
    @Override
    public int updateNursingElder(NursingElder nursingElder)
    {
        nursingElder.setUpdateTime(DateUtils.getNowDate());
        return nursingElderMapper.updateNursingElder(nursingElder);
    }

    /**
     * 批量删除老人信息
     *
     * @param ids 需要删除的老人信息主键
     * @return 结果
     */
    @Override
    public int deleteNursingElderByIds(Long[] ids)
    {
        // 校验每位老人是否有关联入住记录
        for (Long id : ids)
        {
            NursingCheckIn checkInQuery = new NursingCheckIn();
            checkInQuery.setElderId(id);
            List<NursingCheckIn> checkInList = nursingCheckInMapper.selectNursingCheckInList(checkInQuery);
            if (checkInList != null && !checkInList.isEmpty())
            {
                NursingElder elder = nursingElderMapper.selectNursingElderById(id);
                String elderName = elder != null ? elder.getName() : "ID:" + id;
                throw new ServiceException("老人【" + elderName + "】存在" + checkInList.size() + "条入住记录，不允许删除");
            }
        }
        // 校验每位老人是否有关联合同
        for (Long id : ids)
        {
            NursingContract contractQuery = new NursingContract();
            contractQuery.setElderId(id);
            List<NursingContract> contractList = nursingContractMapper.selectNursingContractList(contractQuery);
            if (contractList != null && !contractList.isEmpty())
            {
                NursingElder elder = nursingElderMapper.selectNursingElderById(id);
                String elderName = elder != null ? elder.getName() : "ID:" + id;
                throw new ServiceException("老人【" + elderName + "】存在" + contractList.size() + "条合同记录，不允许删除");
            }
        }
        return nursingElderMapper.deleteNursingElderByIds(ids);
    }

    /**
     * 删除老人信息信息
     *
     * @param id 老人信息主键
     * @return 结果
     */
    @Override
    public int deleteNursingElderById(Long id)
    {
        // 校验老人是否有关联入住记录
        NursingCheckIn checkInQuery = new NursingCheckIn();
        checkInQuery.setElderId(id);
        List<NursingCheckIn> checkInList = nursingCheckInMapper.selectNursingCheckInList(checkInQuery);
        if (checkInList != null && !checkInList.isEmpty())
        {
            NursingElder elder = nursingElderMapper.selectNursingElderById(id);
            String elderName = elder != null ? elder.getName() : "ID:" + id;
            throw new ServiceException("老人【" + elderName + "】存在" + checkInList.size() + "条入住记录，不允许删除");
        }
        // 校验老人是否有关联合同
        NursingContract contractQuery = new NursingContract();
        contractQuery.setElderId(id);
        List<NursingContract> contractList = nursingContractMapper.selectNursingContractList(contractQuery);
        if (contractList != null && !contractList.isEmpty())
        {
            NursingElder elder = nursingElderMapper.selectNursingElderById(id);
            String elderName = elder != null ? elder.getName() : "ID:" + id;
            throw new ServiceException("老人【" + elderName + "】存在" + contractList.size() + "条合同记录，不允许删除");
        }
        return nursingElderMapper.deleteNursingElderById(id);
    }
}
