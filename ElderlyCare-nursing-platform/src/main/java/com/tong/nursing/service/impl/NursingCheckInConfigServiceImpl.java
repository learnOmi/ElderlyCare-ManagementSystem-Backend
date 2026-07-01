package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingCheckInConfigMapper;
import com.tong.nursing.domain.NursingCheckInConfig;
import com.tong.nursing.service.INursingCheckInConfigService;

/**
 * 入住配置Service业务层处理
 *
 * @author Tong
 * @date 2026-06-30
 */
@Service
public class NursingCheckInConfigServiceImpl implements INursingCheckInConfigService
{
    @Autowired
    private NursingCheckInConfigMapper nursingCheckInConfigMapper;

    /**
     * 查询入住配置
     *
     * @param id 入住配置主键
     * @return 入住配置
     */
    @Override
    public NursingCheckInConfig selectNursingCheckInConfigById(Long id)
    {
        return nursingCheckInConfigMapper.selectNursingCheckInConfigById(id);
    }

    /**
     * 查询入住配置列表
     *
     * @param nursingCheckInConfig 入住配置
     * @return 入住配置
     */
    @Override
    public List<NursingCheckInConfig> selectNursingCheckInConfigList(NursingCheckInConfig nursingCheckInConfig)
    {
        return nursingCheckInConfigMapper.selectNursingCheckInConfigList(nursingCheckInConfig);
    }

    /**
     * 新增入住配置
     *
     * @param nursingCheckInConfig 入住配置
     * @return 结果
     */
    @Override
    public int insertNursingCheckInConfig(NursingCheckInConfig nursingCheckInConfig)
    {
        nursingCheckInConfig.setCreateTime(DateUtils.getNowDate());
        return nursingCheckInConfigMapper.insertNursingCheckInConfig(nursingCheckInConfig);
    }

    /**
     * 修改入住配置
     *
     * @param nursingCheckInConfig 入住配置
     * @return 结果
     */
    @Override
    public int updateNursingCheckInConfig(NursingCheckInConfig nursingCheckInConfig)
    {
        nursingCheckInConfig.setUpdateTime(DateUtils.getNowDate());
        return nursingCheckInConfigMapper.updateNursingCheckInConfig(nursingCheckInConfig);
    }

    /**
     * 删除入住配置
     *
     * @param id 入住配置主键
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInConfigById(Long id)
    {
        return nursingCheckInConfigMapper.deleteNursingCheckInConfigById(id);
    }

    /**
     * 批量删除入住配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInConfigByIds(Long[] ids)
    {
        return nursingCheckInConfigMapper.deleteNursingCheckInConfigByIds(ids);
    }

    /**
     * 按入住ID查询入住配置
     *
     * @param checkInId 入住ID
     * @return 入住配置
     */
    @Override
    public NursingCheckInConfig selectNursingCheckInConfigByCheckInId(Long checkInId)
    {
        return nursingCheckInConfigMapper.selectNursingCheckInConfigByCheckInId(checkInId);
    }

    /**
     * 删除指定入住ID的配置
     *
     * @param checkInId 入住ID
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInConfigByCheckInId(Long checkInId)
    {
        return nursingCheckInConfigMapper.deleteNursingCheckInConfigByCheckInId(checkInId);
    }
}
