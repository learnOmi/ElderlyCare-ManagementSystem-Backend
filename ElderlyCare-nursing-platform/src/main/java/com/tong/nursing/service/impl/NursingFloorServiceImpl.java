package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingFloorMapper;
import com.tong.nursing.domain.NursingFloor;
import com.tong.nursing.service.INursingFloorService;

/**
 * 楼层Service业务层处理
 *
 * @author Tong
 * @date 2026-06-27
 */
@Service
public class NursingFloorServiceImpl implements INursingFloorService
{
    @Autowired
    private NursingFloorMapper nursingFloorMapper;

    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    @Override
    public NursingFloor selectNursingFloorById(Long id)
    {
        return nursingFloorMapper.selectNursingFloorById(id);
    }

    /**
     * 查询楼层列表
     *
     * @param nursingFloor 楼层
     * @return 楼层
     */
    @Override
    public List<NursingFloor> selectNursingFloorList(NursingFloor nursingFloor)
    {
        return nursingFloorMapper.selectNursingFloorList(nursingFloor);
    }

    /**
     * 新增楼层
     *
     * @param nursingFloor 楼层
     * @return 结果
     */
    @Override
    public int insertNursingFloor(NursingFloor nursingFloor)
    {
        nursingFloor.setCreateTime(DateUtils.getNowDate());
        return nursingFloorMapper.insertNursingFloor(nursingFloor);
    }

    /**
     * 修改楼层
     *
     * @param nursingFloor 楼层
     * @return 结果
     */
    @Override
    public int updateNursingFloor(NursingFloor nursingFloor)
    {
        nursingFloor.setUpdateTime(DateUtils.getNowDate());
        return nursingFloorMapper.updateNursingFloor(nursingFloor);
    }

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键
     * @return 结果
     */
    @Override
    public int deleteNursingFloorByIds(Long[] ids)
    {
        return nursingFloorMapper.deleteNursingFloorByIds(ids);
    }

    /**
     * 删除楼层信息
     *
     * @param id 楼层主键
     * @return 结果
     */
    @Override
    public int deleteNursingFloorById(Long id)
    {
        return nursingFloorMapper.deleteNursingFloorById(id);
    }
}
