package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingFamily;

/**
 * 家属信息Mapper接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface NursingFamilyMapper
{
    /**
     * 查询家属信息
     *
     * @param id 家属信息主键
     * @return 家属信息
     */
    public NursingFamily selectNursingFamilyById(Long id);

    /**
     * 查询家属信息列表
     *
     * @param nursingFamily 家属信息
     * @return 家属信息集合
     */
    public List<NursingFamily> selectNursingFamilyList(NursingFamily nursingFamily);

    /**
     * 新增家属信息
     *
     * @param nursingFamily 家属信息
     * @return 结果
     */
    public int insertNursingFamily(NursingFamily nursingFamily);

    /**
     * 修改家属信息
     *
     * @param nursingFamily 家属信息
     * @return 结果
     */
    public int updateNursingFamily(NursingFamily nursingFamily);

    /**
     * 删除家属信息
     *
     * @param id 家属信息主键
     * @return 结果
     */
    public int deleteNursingFamilyById(Long id);

    /**
     * 批量删除家属信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingFamilyByIds(Long[] ids);
}
