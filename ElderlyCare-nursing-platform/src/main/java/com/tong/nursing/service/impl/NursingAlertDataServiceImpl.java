package com.tong.nursing.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tong.common.exception.base.BaseException;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingAlertDataMapper;
import com.tong.nursing.domain.NursingAlertData;
import com.tong.nursing.service.INursingAlertDataService;
import com.tong.nursing.constant.NursingConstants;
import com.tong.nursing.vo.NursingAlertDataVO;

/**
 * 告警数据Service业务层处理
 *
 * @author Tong
 * @date 2026-06-29
 */
@Service
public class NursingAlertDataServiceImpl implements INursingAlertDataService
{
    @Autowired
    private NursingAlertDataMapper nursingAlertDataMapper;

    /**
     * 查询告警数据
     *
     * @param id 告警数据主键
     * @return 告警数据
     */
    @Override
    public NursingAlertData selectNursingAlertDataById(Long id)
    {
        return nursingAlertDataMapper.selectNursingAlertDataById(id);
    }

    /**
     * 查询告警数据列表
     *
     * @param nursingAlertData 告警数据
     * @return 告警数据
     */
    @Override
    public List<NursingAlertData> selectNursingAlertDataList(NursingAlertData nursingAlertData)
    {
        return nursingAlertDataMapper.selectNursingAlertDataList(nursingAlertData);
    }

    /**
     * 查询告警数据视图列表（含老人姓名、床位名称、设备名称、规则名称）
     *
     * @param nursingAlertData 告警数据
     * @return 告警数据视图集合
     */
    @Override
    public List<NursingAlertDataVO> selectAlertDataVOList(NursingAlertData nursingAlertData)
    {
        return nursingAlertDataMapper.selectAlertDataVOList(nursingAlertData);
    }

    /**
     * 查询告警数据视图详情
     *
     * @param id 告警数据主键
     * @return 告警数据视图
     */
    @Override
    public NursingAlertDataVO selectAlertDataVOById(Long id)
    {
        return nursingAlertDataMapper.selectAlertDataVOById(id);
    }

    /**
     * 新增告警数据
     *
     * @param nursingAlertData 告警数据
     * @return 结果
     */
    @Override
    public int insertNursingAlertData(NursingAlertData nursingAlertData)
    {
        if (nursingAlertData.getAlertNo() == null || nursingAlertData.getAlertNo().isEmpty())
        {
            nursingAlertData.setAlertNo(generateAlertNo());
        }
        if (nursingAlertData.getStatus() == null)
        {
            nursingAlertData.setStatus(NursingConstants.ALERT_STATUS_PENDING);
        }
        if (nursingAlertData.getAlertTime() == null)
        {
            nursingAlertData.setAlertTime(DateUtils.getNowDate());
        }
        nursingAlertData.setCreateTime(DateUtils.getNowDate());
        return nursingAlertDataMapper.insertNursingAlertData(nursingAlertData);
    }

    /**
     * 修改告警数据
     *
     * @param nursingAlertData 告警数据
     * @return 结果
     */
    @Override
    public int updateNursingAlertData(NursingAlertData nursingAlertData)
    {
        nursingAlertData.setUpdateTime(DateUtils.getNowDate());
        return nursingAlertDataMapper.updateNursingAlertData(nursingAlertData);
    }

    /**
     * 处理告警
     *
     * @param id 告警数据主键
     * @param handleResult 处理结果
     * @return 结果
     */
    @Override
    public int handleAlert(Long id, String handleResult)
    {
        NursingAlertData alertData = nursingAlertDataMapper.selectNursingAlertDataById(id);
        if (alertData == null)
        {
            throw new BaseException("告警记录不存在");
        }
        Integer status = alertData.getStatus();
        if (status != null && status == NursingConstants.ALERT_STATUS_HANDLED)
        {
            throw new BaseException("该告警已处理");
        }
        if (status != null && status == NursingConstants.ALERT_STATUS_IGNORED)
        {
            throw new BaseException("该告警已被忽略");
        }
        alertData.setStatus(NursingConstants.ALERT_STATUS_HANDLED);
        alertData.setHandleTime(DateUtils.getNowDate());
        alertData.setHandleBy("admin");
        alertData.setHandleResult(handleResult);
        alertData.setUpdateTime(DateUtils.getNowDate());
        return nursingAlertDataMapper.updateNursingAlertData(alertData);
    }

    /**
     * 忽略告警
     *
     * @param id 告警数据主键
     * @return 结果
     */
    @Override
    public int ignoreAlert(Long id)
    {
        NursingAlertData alertData = nursingAlertDataMapper.selectNursingAlertDataById(id);
        if (alertData == null)
        {
            throw new BaseException("告警记录不存在");
        }
        Integer status = alertData.getStatus();
        if (status != null && status == NursingConstants.ALERT_STATUS_HANDLED)
        {
            throw new BaseException("该告警已处理");
        }
        if (status != null && status == NursingConstants.ALERT_STATUS_IGNORED)
        {
            throw new BaseException("该告警已被忽略");
        }
        alertData.setStatus(NursingConstants.ALERT_STATUS_IGNORED);
        alertData.setHandleTime(DateUtils.getNowDate());
        alertData.setHandleBy("admin");
        alertData.setUpdateTime(DateUtils.getNowDate());
        return nursingAlertDataMapper.updateNursingAlertData(alertData);
    }

    /**
     * 批量删除告警数据
     *
     * @param ids 需要删除的告警数据主键
     * @return 结果
     */
    @Override
    public int deleteNursingAlertDataByIds(Long[] ids)
    {
        return nursingAlertDataMapper.deleteNursingAlertDataByIds(ids);
    }

    /**
     * 删除告警数据信息
     *
     * @param id 告警数据主键
     * @return 结果
     */
    @Override
    public int deleteNursingAlertDataById(Long id)
    {
        return nursingAlertDataMapper.deleteNursingAlertDataById(id);
    }

    /**
     * 生成告警编号
     * 格式：ALT + yyyyMMdd + 3位序号
     */
    private String generateAlertNo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        return "ALT-" + dateStr + "-" + String.format("%03d", 1);
    }

    /**
     * 批量处理告警
     *
     * @param ids 告警ID数组
     * @param handleResult 处理结果
     * @return 结果
     */
    @Override
    public int batchHandleAlert(Long[] ids, String handleResult)
    {
        int count = 0;
        for (Long id : ids)
        {
            try
            {
                count += handleAlert(id, handleResult);
            }
            catch (BaseException e)
            {
                continue;
            }
        }
        return count;
    }

    /**
     * 查询未处理告警数量
     *
     * @return 未处理告警数量
     */
    @Override
    public int getUnhandleCount()
    {
        return nursingAlertDataMapper.selectUnhandleCount();
    }
}
