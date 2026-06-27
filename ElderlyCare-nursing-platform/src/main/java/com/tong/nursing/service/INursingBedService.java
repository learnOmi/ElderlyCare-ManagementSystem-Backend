package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingBed;

/**
 * 床位Service接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface INursingBedService
{
    /**
     * 查询床位
     *
     * @param id 床位主键
     * @return 床位
     */
    public NursingBed selectNursingBedById(Long id);

    /**
     * 查询床位列表
     *
     * @param nursingBed 床位
     * @return 床位集合
     */
    public List<NursingBed> selectNursingBedList(NursingBed nursingBed);

    /**
     * 新增床位
     *
     * @param nursingBed 床位
     * @return 结果
     */
    public int insertNursingBed(NursingBed nursingBed);

    /**
     * 修改床位
     *
     * @param nursingBed 床位
     * @return 结果
     */
    public int updateNursingBed(NursingBed nursingBed);

    /**
     * 批量删除床位
     *
     * @param ids 需要删除的床位主键集合
     * @return 结果
     */
    public int deleteNursingBedByIds(Long[] ids);

    /**
     * 删除床位信息
     *
     * @param id 床位主键
     * @return 结果
     */
    public int deleteNursingBedById(Long id);
}
