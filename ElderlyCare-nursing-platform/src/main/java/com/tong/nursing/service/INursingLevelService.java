package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingLevel;
import com.tong.nursing.vo.NursingLevelVO;

/**
 * 护理等级Service接口
 *
 * @author Tong
 * @date 2026-06-25
 */
public interface INursingLevelService
{
    /**
     * 查询护理等级
     *
     * @param id 护理等级主键
     * @return 护理等级
     */
    public NursingLevel selectNursingLevelById(Integer id);

    /**
     * 查询护理等级详情（含护理计划名称和项目列表）
     *
     * @param id 护理等级主键
     * @return 护理等级详情（含护理计划名称和项目列表）
     */
    public NursingLevelVO selectNursingLevelVOById(Integer id);

    /**
     * 查询护理等级列表
     *
     * @param nursingLevel 护理等级
     * @return 护理等级集合
     */
    public List<NursingLevel> selectNursingLevelList(NursingLevel nursingLevel);

    /**
     * 查询全部护理等级（下拉框/全量列表用）
     *
     * @return 护理等级集合
     */
    public List<NursingLevel> selectNursingLevelAll();

    /**
     * 新增护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    public int insertNursingLevel(NursingLevel nursingLevel);

    /**
     * 修改护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    public int updateNursingLevel(NursingLevel nursingLevel);

    /**
     * 批量删除护理等级
     *
     * @param ids 需要删除的护理等级主键集合
     * @return 结果
     */
    public int deleteNursingLevelByIds(Integer[] ids);

    /**
     * 删除护理等级信息
     *
     * @param id 护理等级主键
     * @return 结果
     */
    public int deleteNursingLevelById(Integer id);
}
