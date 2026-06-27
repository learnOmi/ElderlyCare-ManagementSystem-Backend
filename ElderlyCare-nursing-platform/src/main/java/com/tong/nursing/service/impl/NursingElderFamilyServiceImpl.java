package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingElderFamilyMapper;
import com.tong.nursing.domain.NursingElderFamily;
import com.tong.nursing.service.INursingElderFamilyService;

/**
 * 老人家属关联Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingElderFamilyServiceImpl implements INursingElderFamilyService
{
    @Autowired
    private NursingElderFamilyMapper nursingElderFamilyMapper;

    /**
     * 查询老人家属关联
     *
     * @param id 老人家属关联主键
     * @return 老人家属关联
     */
    @Override
    public NursingElderFamily selectNursingElderFamilyById(Long id)
    {
        return nursingElderFamilyMapper.selectNursingElderFamilyById(id);
    }

    /**
     * 查询老人家属关联列表
     *
     * @param nursingElderFamily 老人家属关联
     * @return 老人家属关联
     */
    @Override
    public List<NursingElderFamily> selectNursingElderFamilyList(NursingElderFamily nursingElderFamily)
    {
        return nursingElderFamilyMapper.selectNursingElderFamilyList(nursingElderFamily);
    }

    /**
     * 新增老人家属关联
     *
     * @param nursingElderFamily 老人家属关联
     * @return 结果
     */
    @Override
    public int insertNursingElderFamily(NursingElderFamily nursingElderFamily)
    {
        nursingElderFamily.setCreateTime(DateUtils.getNowDate());
        return nursingElderFamilyMapper.insertNursingElderFamily(nursingElderFamily);
    }

    /**
     * 修改老人家属关联
     *
     * @param nursingElderFamily 老人家属关联
     * @return 结果
     */
    @Override
    public int updateNursingElderFamily(NursingElderFamily nursingElderFamily)
    {
        nursingElderFamily.setUpdateTime(DateUtils.getNowDate());
        return nursingElderFamilyMapper.updateNursingElderFamily(nursingElderFamily);
    }

    /**
     * 批量删除老人家属关联
     *
     * @param ids 需要删除的老人家属关联主键
     * @return 结果
     */
    @Override
    public int deleteNursingElderFamilyByIds(Long[] ids)
    {
        return nursingElderFamilyMapper.deleteNursingElderFamilyByIds(ids);
    }

    /**
     * 删除老人家属关联
     *
     * @param id 老人家属关联主键
     * @return 结果
     */
    @Override
    public int deleteNursingElderFamilyById(Long id)
    {
        return nursingElderFamilyMapper.deleteNursingElderFamilyById(id);
    }
}
