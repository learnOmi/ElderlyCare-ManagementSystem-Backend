package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingRoomTypeMapper;
import com.tong.nursing.domain.NursingRoomType;
import com.tong.nursing.service.INursingRoomTypeService;

/**
 * 房型Service业务层处理
 *
 * @author Tong
 * @date 2026-06-27
 */
@Service
public class NursingRoomTypeServiceImpl implements INursingRoomTypeService
{
    @Autowired
    private NursingRoomTypeMapper nursingRoomTypeMapper;

    /**
     * 查询房型
     *
     * @param id 房型主键
     * @return 房型
     */
    @Override
    public NursingRoomType selectNursingRoomTypeById(Long id)
    {
        return nursingRoomTypeMapper.selectNursingRoomTypeById(id);
    }

    /**
     * 查询房型列表
     *
     * @param nursingRoomType 房型
     * @return 房型
     */
    @Override
    public List<NursingRoomType> selectNursingRoomTypeList(NursingRoomType nursingRoomType)
    {
        return nursingRoomTypeMapper.selectNursingRoomTypeList(nursingRoomType);
    }

    /**
     * 新增房型
     *
     * @param nursingRoomType 房型
     * @return 结果
     */
    @Override
    public int insertNursingRoomType(NursingRoomType nursingRoomType)
    {
        nursingRoomType.setCreateTime(DateUtils.getNowDate());
        return nursingRoomTypeMapper.insertNursingRoomType(nursingRoomType);
    }

    /**
     * 修改房型
     *
     * @param nursingRoomType 房型
     * @return 结果
     */
    @Override
    public int updateNursingRoomType(NursingRoomType nursingRoomType)
    {
        nursingRoomType.setUpdateTime(DateUtils.getNowDate());
        return nursingRoomTypeMapper.updateNursingRoomType(nursingRoomType);
    }

    /**
     * 批量删除房型
     *
     * @param ids 需要删除的房型主键
     * @return 结果
     */
    @Override
    public int deleteNursingRoomTypeByIds(Long[] ids)
    {
        return nursingRoomTypeMapper.deleteNursingRoomTypeByIds(ids);
    }

    /**
     * 删除房型信息
     *
     * @param id 房型主键
     * @return 结果
     */
    @Override
    public int deleteNursingRoomTypeById(Long id)
    {
        return nursingRoomTypeMapper.deleteNursingRoomTypeById(id);
    }
}
