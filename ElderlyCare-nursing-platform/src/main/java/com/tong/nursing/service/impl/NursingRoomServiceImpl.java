package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingRoomMapper;
import com.tong.nursing.domain.NursingRoom;
import com.tong.nursing.service.INursingRoomService;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.domain.NursingBed;

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

    @Autowired
    private NursingBedMapper nursingBedMapper;

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
        // 校验每个房间下是否有关联床位
        for (Long id : ids)
        {
            NursingBed bed = new NursingBed();
            bed.setRoomId(id);
            List<NursingBed> bedList = nursingBedMapper.selectNursingBedList(bed);
            if (bedList != null && !bedList.isEmpty())
            {
                NursingRoom room = nursingRoomMapper.selectNursingRoomById(id);
                String roomNo = room != null ? room.getRoomNo() : "ID:" + id;
                throw new ServiceException("房间【" + roomNo + "】下存在" + bedList.size() + "个床位，不允许删除");
            }
        }
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
        // 校验房间下是否有关联床位
        NursingBed bed = new NursingBed();
        bed.setRoomId(id);
        List<NursingBed> bedList = nursingBedMapper.selectNursingBedList(bed);
        if (bedList != null && !bedList.isEmpty())
        {
            NursingRoom room = nursingRoomMapper.selectNursingRoomById(id);
            String roomNo = room != null ? room.getRoomNo() : "ID:" + id;
            throw new ServiceException("房间【" + roomNo + "】下存在" + bedList.size() + "个床位，不允许删除");
        }
        return nursingRoomMapper.deleteNursingRoomById(id);
    }
}
