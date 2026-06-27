package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingElderFamily;

/**
 * 老人家属关联Mapper接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface NursingElderFamilyMapper
{
    /**
     * 查询老人家属关联
     *
     * @param id 老人家属关联主键
     * @return 老人家属关联
     */
    public NursingElderFamily selectNursingElderFamilyById(Long id);

    /**
     * 查询老人家属关联列表
     *
     * @param nursingElderFamily 老人家属关联
     * @return 老人家属关联集合
     */
    public List<NursingElderFamily> selectNursingElderFamilyList(NursingElderFamily nursingElderFamily);

    /**
     * 新增老人家属关联
     *
     * @param nursingElderFamily 老人家属关联
     * @return 结果
     */
    public int insertNursingElderFamily(NursingElderFamily nursingElderFamily);

    /**
     * 修改老人家属关联
     *
     * @param nursingElderFamily 老人家属关联
     * @return 结果
     */
    public int updateNursingElderFamily(NursingElderFamily nursingElderFamily);

    /**
     * 删除老人家属关联
     *
     * @param id 老人家属关联主键
     * @return 结果
     */
    public int deleteNursingElderFamilyById(Long id);

    /**
     * 批量删除老人家属关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingElderFamilyByIds(Long[] ids);
}
