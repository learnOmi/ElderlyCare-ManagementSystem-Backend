package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingRoomMapper;
import com.tong.nursing.domain.NursingRoom;
import com.tong.nursing.service.INursingRoomService;

/**
 * 房间Service业务层处理
 *
 * @author Tong
 * @date 2026-06-27
 */
@Service
public class NursingRoomServiceImpl implements INursingRoomService
{
    @Autowired
    private NursingRoomMapper nursingRoomMapper;

    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public NursingRoom selectNursingRoomById(Long id)
    {
        return nursingRoomMapper.selectNursingRoomById(id);
    }

    /**
     * 查询房间列表
     *
     * @param nursingRoom 房间
     * @return 房间
     */
    @Override
    public List<NursingRoom> selectNursingRoomList(NursingRoom nursingRoom)
    {
        return nursingRoomMapper.selectNursingRoomList(nursingRoom);
    }

    /**
     * 新增房间
     *
     * @param nursingRoom 房间
     * @return 结果
     */
    @Override
    public int insertNursingRoom(NursingRoom nursingRoom)
    {
        nursingRoom.setCreateTime(DateUtils.getNowDate());
        return nursingRoomMapper.insertNursingRoom(nursingRoom);
    }

    /**
     * 修改房间
     *
     * @param nursingRoom 房间
     * @return 结果
     */
    @Override
    public int updateNursingRoom(NursingRoom nursingRoom)
    {
        nursingRoom.setUpdateTime(DateUtils.getNowDate());
        return nursingRoomMapper.updateNursingRoom(nursingRoom);
    }

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteNursingRoomByIds(Long[] ids)
    {
        return nursingRoomMapper.deleteNursingRoomByIds(ids);
    }

    /**
     * 删除房间信息
     *
     * @param id 房间主键
     * @return 结果
     */
    @Override
    public int deleteNursingRoomById(Long id)
    {
        return nursingRoomMapper.deleteNursingRoomById(id);
    }
}
