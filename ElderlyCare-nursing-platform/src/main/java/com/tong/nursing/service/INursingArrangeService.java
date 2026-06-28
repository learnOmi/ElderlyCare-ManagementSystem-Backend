package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingArrange;

/**
 * 护理排班Service接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface INursingArrangeService
{
    /**
     * 查询护理排班
     *
     * @param id 护理排班主键
     * @return 护理排班
     */
    public NursingArrange selectNursingArrangeById(Long id);

    /**
     * 查询护理排班列表
     *
     * @param nursingArrange 护理排班
     * @return 护理排班集合
     */
    public List<NursingArrange> selectNursingArrangeList(NursingArrange nursingArrange);

    /**
     * 新增护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    public int insertNursingArrange(NursingArrange nursingArrange);

    /**
     * 修改护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    public int updateNursingArrange(NursingArrange nursingArrange);

    /**
     * 批量删除护理排班
     *
     * @param ids 需要删除的护理排班主键集合
     * @return 结果
     */
    public int deleteNursingArrangeByIds(Long[] ids);

    /**
     * 删除护理排班信息
     *
     * @param id 护理排班主键
     * @return 结果
     */
    public int deleteNursingArrangeById(Long id);

    /**
     * 执行护理排班
     *
     * @param id 排班主键
     * @return 结果
     */
    public int executeArrange(Long id);

    /**
     * 取消护理排班
     *
     * @param id 排班主键
     * @param cancelReason 取消原因
     * @return 结果
     */
    public int cancelArrange(Long id, String cancelReason);
}
