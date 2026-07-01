package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingCheckInConfig;

/**
 * 入住配置Service接口
 *
 * @author Tong
 * @date 2026-06-30
 */
public interface INursingCheckInConfigService
{
    /**
     * 查询入住配置
     *
     * @param id 入住配置主键
     * @return 入住配置
     */
    public NursingCheckInConfig selectNursingCheckInConfigById(Long id);

    /**
     * 查询入住配置列表
     *
     * @param nursingCheckInConfig 入住配置
     * @return 入住配置集合
     */
    public List<NursingCheckInConfig> selectNursingCheckInConfigList(NursingCheckInConfig nursingCheckInConfig);

    /**
     * 新增入住配置
     *
     * @param nursingCheckInConfig 入住配置
     * @return 结果
     */
    public int insertNursingCheckInConfig(NursingCheckInConfig nursingCheckInConfig);

    /**
     * 修改入住配置
     *
     * @param nursingCheckInConfig 入住配置
     * @return 结果
     */
    public int updateNursingCheckInConfig(NursingCheckInConfig nursingCheckInConfig);

    /**
     * 删除入住配置
     *
     * @param id 入住配置主键
     * @return 结果
     */
    public int deleteNursingCheckInConfigById(Long id);

    /**
     * 批量删除入住配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingCheckInConfigByIds(Long[] ids);

    /**
     * 按入住ID查询入住配置
     *
     * @param checkInId 入住ID
     * @return 入住配置
     */
    public NursingCheckInConfig selectNursingCheckInConfigByCheckInId(Long checkInId);

    /**
     * 删除指定入住ID的配置
     *
     * @param checkInId 入住ID
     * @return 结果
     */
    public int deleteNursingCheckInConfigByCheckInId(Long checkInId);
}
