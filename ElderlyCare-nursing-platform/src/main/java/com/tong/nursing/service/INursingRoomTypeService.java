package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingRoomType;

/**
 * 房型Service接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface INursingRoomTypeService
{
    /**
     * 查询房型
     *
     * @param id 房型主键
     * @return 房型
     */
    public NursingRoomType selectNursingRoomTypeById(Long id);

    /**
     * 查询房型列表
     *
     * @param nursingRoomType 房型
     * @return 房型集合
     */
    public List<NursingRoomType> selectNursingRoomTypeList(NursingRoomType nursingRoomType);

    /**
     * 新增房型
     *
     * @param nursingRoomType 房型
     * @return 结果
     */
    public int insertNursingRoomType(NursingRoomType nursingRoomType);

    /**
     * 修改房型
     *
     * @param nursingRoomType 房型
     * @return 结果
     */
    public int updateNursingRoomType(NursingRoomType nursingRoomType);

    /**
     * 批量删除房型
     *
     * @param ids 需要删除的房型主键集合
     * @return 结果
     */
    public int deleteNursingRoomTypeByIds(Long[] ids);

    /**
     * 删除房型信息
     *
     * @param id 房型主键
     * @return 结果
     */
    public int deleteNursingRoomTypeById(Long id);
}
