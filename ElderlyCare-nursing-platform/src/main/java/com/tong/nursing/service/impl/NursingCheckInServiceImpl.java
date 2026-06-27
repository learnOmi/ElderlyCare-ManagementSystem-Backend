package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingCheckInMapper;
import com.tong.nursing.domain.NursingCheckIn;
import com.tong.nursing.vo.NursingCheckInDetailVO;
import com.tong.nursing.vo.NursingCheckInListVO;
import com.tong.nursing.service.INursingCheckInService;

/**
 * 入住申请Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingCheckInServiceImpl implements INursingCheckInService
{
    @Autowired
    private NursingCheckInMapper nursingCheckInMapper;

    /**
     * 查询入住申请
     *
     * @param id 入住申请主键
     * @return 入住申请
     */
    @Override
    public NursingCheckIn selectNursingCheckInById(Long id)
    {
        return nursingCheckInMapper.selectNursingCheckInById(id);
    }

    /**
     * 查询入住申请详情（包含老人、家属、床位、房间、楼层、护理等级等完整信息）
     *
     * @param id 入住申请主键
     * @return 入住申请详情
     */
    @Override
    public NursingCheckInDetailVO selectCheckInDetailById(Long id)
    {
        return nursingCheckInMapper.selectCheckInDetailById(id);
    }

    /**
     * 查询入住申请列表（包含老人详细信息和护理等级信息，用于列表接口）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请列表
     */
    @Override
    public List<NursingCheckInListVO> selectCheckInList(NursingCheckIn nursingCheckIn)
    {
        return nursingCheckInMapper.selectCheckInList(nursingCheckIn);
    }

    /**
     * 查询入住申请列表（仅基础字段，用于导出等功能）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请
     */
    @Override
    public List<NursingCheckIn> selectNursingCheckInList(NursingCheckIn nursingCheckIn)
    {
        return nursingCheckInMapper.selectNursingCheckInList(nursingCheckIn);
    }

    /**
     * 新增入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    @Override
    public int insertNursingCheckIn(NursingCheckIn nursingCheckIn)
    {
        nursingCheckIn.setCreateTime(DateUtils.getNowDate());
        return nursingCheckInMapper.insertNursingCheckIn(nursingCheckIn);
    }

    /**
     * 修改入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    @Override
    public int updateNursingCheckIn(NursingCheckIn nursingCheckIn)
    {
        nursingCheckIn.setUpdateTime(DateUtils.getNowDate());
        return nursingCheckInMapper.updateNursingCheckIn(nursingCheckIn);
    }

    /**
     * 批量删除入住申请
     *
     * @param ids 需要删除的入住申请主键
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInByIds(Long[] ids)
    {
        return nursingCheckInMapper.deleteNursingCheckInByIds(ids);
    }

    /**
     * 删除入住申请
     *
     * @param id 入住申请主键
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInById(Long id)
    {
        return nursingCheckInMapper.deleteNursingCheckInById(id);
    }
}
