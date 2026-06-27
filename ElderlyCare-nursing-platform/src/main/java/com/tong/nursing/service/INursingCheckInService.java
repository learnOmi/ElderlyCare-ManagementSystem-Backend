package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingCheckIn;
import com.tong.nursing.vo.NursingCheckInDetailVO;
import com.tong.nursing.vo.NursingCheckInListVO;

/**
 * 入住申请Service接口
 *
 * @author Tong
 * @date 2026-06-28
 */
public interface INursingCheckInService
{
    /**
     * 查询入住申请
     *
     * @param id 入住申请主键
     * @return 入住申请
     */
    public NursingCheckIn selectNursingCheckInById(Long id);

    /**
     * 查询入住申请详情（包含老人、家属、床位、房间、楼层、护理等级等完整信息）
     *
     * @param id 入住申请主键
     * @return 入住申请详情
     */
    public NursingCheckInDetailVO selectCheckInDetailById(Long id);

    /**
     * 查询入住申请列表（包含老人详细信息和护理等级信息，用于列表接口）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请列表
     */
    public List<NursingCheckInListVO> selectCheckInList(NursingCheckIn nursingCheckIn);

    /**
     * 查询入住申请列表（仅基础字段，用于导出等功能）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请集合
     */
    public List<NursingCheckIn> selectNursingCheckInList(NursingCheckIn nursingCheckIn);

    /**
     * 新增入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    public int insertNursingCheckIn(NursingCheckIn nursingCheckIn);

    /**
     * 修改入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    public int updateNursingCheckIn(NursingCheckIn nursingCheckIn);

    /**
     * 批量删除入住申请
     *
     * @param ids 需要删除的入住申请主键集合
     * @return 结果
     */
    public int deleteNursingCheckInByIds(Long[] ids);

    /**
     * 删除入住申请
     *
     * @param id 入住申请主键
     * @return 结果
     */
    public int deleteNursingCheckInById(Long id);
}
