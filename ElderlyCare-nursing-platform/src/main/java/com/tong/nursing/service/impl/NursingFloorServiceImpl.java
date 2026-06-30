package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingFloorMapper;
import com.tong.nursing.domain.NursingFloor;
import com.tong.nursing.service.INursingFloorService;
import com.tong.nursing.vo.FloorTreeVO;
import com.tong.nursing.mapper.NursingRoomMapper;
import com.tong.nursing.domain.NursingRoom;

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

    @Autowired
    private NursingRoomMapper nursingRoomMapper;

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
     * 查询全部楼层（下拉框/全量列表用）
     *
     * @return 楼层集合
     */
    @Override
    public List<NursingFloor> selectNursingFloorAll()
    {
        return nursingFloorMapper.selectNursingFloorAll();
    }

    /**
     * 查询楼层树形结构（含房间和床位）
     *
     * @param id 楼层主键
     * @return 楼层树形结构
     */
    @Override
    public FloorTreeVO selectFloorTreeById(Long id)
    {
        return nursingFloorMapper.selectFloorTreeById(id);
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
        // 校验每个楼层下是否有关联房间
        for (Long id : ids)
        {
            NursingRoom room = new NursingRoom();
            room.setFloorId(id);
            List<NursingRoom> roomList = nursingRoomMapper.selectNursingRoomList(room);
            if (roomList != null && !roomList.isEmpty())
            {
                NursingFloor floor = nursingFloorMapper.selectNursingFloorById(id);
                String floorName = floor != null ? floor.getName() : "ID:" + id;
                throw new ServiceException("楼层【" + floorName + "】下存在" + roomList.size() + "个房间，不允许删除");
            }
        }
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
        // 校验楼层下是否有关联房间
        NursingRoom room = new NursingRoom();
        room.setFloorId(id);
        List<NursingRoom> roomList = nursingRoomMapper.selectNursingRoomList(room);
        if (roomList != null && !roomList.isEmpty())
        {
            NursingFloor floor = nursingFloorMapper.selectNursingFloorById(id);
            String floorName = floor != null ? floor.getName() : "ID:" + id;
            throw new ServiceException("楼层【" + floorName + "】下存在" + roomList.size() + "个房间，不允许删除");
        }
        return nursingFloorMapper.deleteNursingFloorById(id);
    }
}
