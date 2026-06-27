package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingRoom;

/**
 * 房间Service接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface INursingRoomService
{
    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    public NursingRoom selectNursingRoomById(Long id);

    /**
     * 查询房间列表
     *
     * @param nursingRoom 房间
     * @return 房间集合
     */
    public List<NursingRoom> selectNursingRoomList(NursingRoom nursingRoom);

    /**
     * 新增房间
     *
     * @param nursingRoom 房间
     * @return 结果
     */
    public int insertNursingRoom(NursingRoom nursingRoom);

    /**
     * 修改房间
     *
     * @param nursingRoom 房间
     * @return 结果
     */
    public int updateNursingRoom(NursingRoom nursingRoom);

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键集合
     * @return 结果
     */
    public int deleteNursingRoomByIds(Long[] ids);

    /**
     * 删除房间信息
     *
     * @param id 房间主键
     * @return 结果
     */
    public int deleteNursingRoomById(Long id);
}
