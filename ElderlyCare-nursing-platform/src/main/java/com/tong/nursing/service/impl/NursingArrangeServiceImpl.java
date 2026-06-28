package com.tong.nursing.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tong.common.exception.base.BaseException;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingArrangeMapper;
import com.tong.nursing.domain.NursingArrange;
import com.tong.nursing.service.INursingArrangeService;
import com.tong.nursing.constant.NursingConstants;

/**
 * 护理排班Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingArrangeServiceImpl implements INursingArrangeService
{
    @Autowired
    private NursingArrangeMapper nursingArrangeMapper;

    /**
     * 查询护理排班
     *
     * @param id 护理排班主键
     * @return 护理排班
     */
    @Override
    public NursingArrange selectNursingArrangeById(Long id)
    {
        return nursingArrangeMapper.selectNursingArrangeById(id);
    }

    /**
     * 查询护理排班列表
     *
     * @param nursingArrange 护理排班
     * @return 护理排班
     */
    @Override
    public List<NursingArrange> selectNursingArrangeList(NursingArrange nursingArrange)
    {
        return nursingArrangeMapper.selectNursingArrangeList(nursingArrange);
    }

    /**
     * 新增护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    @Override
    public int insertNursingArrange(NursingArrange nursingArrange)
    {
        // 自动生成排班编号
        if (nursingArrange.getArrangeNo() == null || nursingArrange.getArrangeNo().isEmpty())
        {
            nursingArrange.setArrangeNo(generateArrangeNo());
        }
        if (nursingArrange.getStatus() == null)
        {
            nursingArrange.setStatus(NursingConstants.ARRANGE_STATUS_PENDING);
        }
        nursingArrange.setCreateTime(DateUtils.getNowDate());
        return nursingArrangeMapper.insertNursingArrange(nursingArrange);
    }

    /**
     * 修改护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    @Override
    public int updateNursingArrange(NursingArrange nursingArrange)
    {
        // 已执行的排班不允许修改
        if (nursingArrange.getStatus() != null && nursingArrange.getStatus() == NursingConstants.ARRANGE_STATUS_EXECUTED)
        {
            throw new BaseException("已执行的排班不允许修改");
        }
        nursingArrange.setUpdateTime(DateUtils.getNowDate());
        return nursingArrangeMapper.updateNursingArrange(nursingArrange);
    }

    /**
     * 批量删除护理排班
     *
     * @param ids 需要删除的护理排班主键
     * @return 结果
     */
    @Override
    public int deleteNursingArrangeByIds(Long[] ids)
    {
        return nursingArrangeMapper.deleteNursingArrangeByIds(ids);
    }

    /**
     * 删除护理排班信息
     *
     * @param id 护理排班主键
     * @return 结果
     */
    @Override
    public int deleteNursingArrangeById(Long id)
    {
        return nursingArrangeMapper.deleteNursingArrangeById(id);
    }

    /**
     * 执行护理排班
     *
     * @param id 排班主键
     * @return 结果
     */
    @Override
    public int executeArrange(Long id)
    {
        NursingArrange arrange = nursingArrangeMapper.selectNursingArrangeById(id);
        if (arrange == null)
        {
            throw new BaseException("排班记录不存在");
        }
        // 只有待执行状态的排班才能执行
        if (arrange.getStatus() != null && arrange.getStatus() != NursingConstants.ARRANGE_STATUS_PENDING)
        {
            throw new BaseException("当前排班状态不允许执行");
        }
        // 状态流转：待执行 → 执行中 → 已执行
        arrange.setStatus(NursingConstants.ARRANGE_STATUS_EXECUTING);
        arrange.setExecuteTime(DateUtils.getNowDate());
        arrange.setUpdateTime(DateUtils.getNowDate());
        nursingArrangeMapper.updateNursingArrange(arrange);

        // 标记为已执行
        arrange.setStatus(NursingConstants.ARRANGE_STATUS_EXECUTED);
        return nursingArrangeMapper.updateNursingArrange(arrange);
    }

    /**
     * 取消护理排班
     *
     * @param id 排班主键
     * @param cancelReason 取消原因
     * @return 结果
     */
    @Override
    public int cancelArrange(Long id, String cancelReason)
    {
        NursingArrange arrange = nursingArrangeMapper.selectNursingArrangeById(id);
        if (arrange == null)
        {
            throw new BaseException("排班记录不存在");
        }
        // 只有待执行或执行中状态的排班才能取消
        if (arrange.getStatus() != null
            && arrange.getStatus() != NursingConstants.ARRANGE_STATUS_PENDING
            && arrange.getStatus() != NursingConstants.ARRANGE_STATUS_EXECUTING)
        {
            throw new BaseException("当前排班状态不允许取消");
        }
        arrange.setStatus(NursingConstants.ARRANGE_STATUS_CANCELED);
        arrange.setCancelReason(cancelReason);
        arrange.setUpdateTime(DateUtils.getNowDate());
        return nursingArrangeMapper.updateNursingArrange(arrange);
    }

    /**
     * 生成排班编号
     * 格式：AR + yyyyMMdd + 4位序号
     */
    private String generateArrangeNo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        // 简单序号，生产环境建议使用数据库序列或分布式ID
        return "AR" + dateStr + String.format("%04d", 1);
    }
}
