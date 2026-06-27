package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingElderMapper;
import com.tong.nursing.domain.NursingElder;
import com.tong.nursing.service.INursingElderService;

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
        return nursingElderMapper.deleteNursingElderByIds(ids);
    }

    /**
     * 删除老人信息
     *
     * @param id 老人信息主键
     * @return 结果
     */
    @Override
    public int deleteNursingElderById(Long id)
    {
        return nursingElderMapper.deleteNursingElderById(id);
    }
}
