package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingElder;

/**
 * 老人信息Service接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface INursingElderService
{
    /**
     * 查询老人信息
     *
     * @param id 老人信息主键
     * @return 老人信息
     */
    public NursingElder selectNursingElderById(Long id);

    /**
     * 查询老人信息列表
     *
     * @param nursingElder 老人信息
     * @return 老人信息集合
     */
    public List<NursingElder> selectNursingElderList(NursingElder nursingElder);

    /**
     * 新增老人信息
     *
     * @param nursingElder 老人信息
     * @return 结果
     */
    public int insertNursingElder(NursingElder nursingElder);

    /**
     * 修改老人信息
     *
     * @param nursingElder 老人信息
     * @return 结果
     */
    public int updateNursingElder(NursingElder nursingElder);

    /**
     * 批量删除老人信息
     *
     * @param ids 需要删除的老人信息主键集合
     * @return 结果
     */
    public int deleteNursingElderByIds(Long[] ids);

    /**
     * 删除老人信息
     *
     * @param id 老人信息主键
     * @return 结果
     */
    public int deleteNursingElderById(Long id);
}
