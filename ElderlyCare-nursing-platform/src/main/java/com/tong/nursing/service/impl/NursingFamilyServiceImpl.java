package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingFamilyMapper;
import com.tong.nursing.domain.NursingFamily;
import com.tong.nursing.service.INursingFamilyService;

/**
 * 家属信息Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingFamilyServiceImpl implements INursingFamilyService
{
    @Autowired
    private NursingFamilyMapper nursingFamilyMapper;

    /**
     * 查询家属信息
     *
     * @param id 家属信息主键
     * @return 家属信息
     */
    @Override
    public NursingFamily selectNursingFamilyById(Long id)
    {
        return nursingFamilyMapper.selectNursingFamilyById(id);
    }

    /**
     * 查询家属信息列表
     *
     * @param nursingFamily 家属信息
     * @return 家属信息
     */
    @Override
    public List<NursingFamily> selectNursingFamilyList(NursingFamily nursingFamily)
    {
        return nursingFamilyMapper.selectNursingFamilyList(nursingFamily);
    }

    /**
     * 新增家属信息
     *
     * @param nursingFamily 家属信息
     * @return 结果
     */
    @Override
    public int insertNursingFamily(NursingFamily nursingFamily)
    {
        nursingFamily.setCreateTime(DateUtils.getNowDate());
        return nursingFamilyMapper.insertNursingFamily(nursingFamily);
    }

    /**
     * 修改家属信息
     *
     * @param nursingFamily 家属信息
     * @return 结果
     */
    @Override
    public int updateNursingFamily(NursingFamily nursingFamily)
    {
        nursingFamily.setUpdateTime(DateUtils.getNowDate());
        return nursingFamilyMapper.updateNursingFamily(nursingFamily);
    }

    /**
     * 批量删除家属信息
     *
     * @param ids 需要删除的家属信息主键
     * @return 结果
     */
    @Override
    public int deleteNursingFamilyByIds(Long[] ids)
    {
        return nursingFamilyMapper.deleteNursingFamilyByIds(ids);
    }

    /**
     * 删除家属信息
     *
     * @param id 家属信息主键
     * @return 结果
     */
    @Override
    public int deleteNursingFamilyById(Long id)
    {
        return nursingFamilyMapper.deleteNursingFamilyById(id);
    }
}
