package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingRoomTypeMapper;
import com.tong.nursing.domain.NursingRoomType;
import com.tong.nursing.service.INursingRoomTypeService;
import com.tong.nursing.mapper.NursingRoomMapper;
import com.tong.nursing.domain.NursingRoom;

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

    @Autowired
    private NursingRoomMapper nursingRoomMapper;

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
        // 校验每个房型下是否有关联房间
        for (Long id : ids)
        {
            NursingRoom room = new NursingRoom();
            room.setRoomTypeId(id);
            List<NursingRoom> roomList = nursingRoomMapper.selectNursingRoomList(room);
            if (roomList != null && !roomList.isEmpty())
            {
                NursingRoomType roomType = nursingRoomTypeMapper.selectNursingRoomTypeById(id);
                String typeName = roomType != null ? roomType.getName() : "ID:" + id;
                throw new ServiceException("房型【" + typeName + "】下存在" + roomList.size() + "个房间，不允许删除");
            }
        }
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
        // 校验房型下是否有关联房间
        NursingRoom room = new NursingRoom();
        room.setRoomTypeId(id);
        List<NursingRoom> roomList = nursingRoomMapper.selectNursingRoomList(room);
        if (roomList != null && !roomList.isEmpty())
        {
            NursingRoomType roomType = nursingRoomTypeMapper.selectNursingRoomTypeById(id);
            String typeName = roomType != null ? roomType.getName() : "ID:" + id;
            throw new ServiceException("房型【" + typeName + "】下存在" + roomList.size() + "个房间，不允许删除");
        }
        return nursingRoomTypeMapper.deleteNursingRoomTypeById(id);
    }
}
