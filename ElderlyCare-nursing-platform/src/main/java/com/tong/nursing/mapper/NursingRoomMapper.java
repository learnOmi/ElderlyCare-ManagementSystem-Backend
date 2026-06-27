package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingRoom;

/**
 * 房间Mapper接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface NursingRoomMapper
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
     * 删除房间
     *
     * @param id 房间主键
     * @return 结果
     */
    public int deleteNursingRoomById(Long id);

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingRoomByIds(Long[] ids);
}
