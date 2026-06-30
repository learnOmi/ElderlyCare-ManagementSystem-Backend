package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingFloor;
import com.tong.nursing.vo.FloorTreeVO;

/**
 * 楼层Service接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface INursingFloorService
{
    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    public NursingFloor selectNursingFloorById(Long id);

    /**
     * 查询楼层列表
     *
     * @param nursingFloor 楼层
     * @return 楼层集合
     */
    public List<NursingFloor> selectNursingFloorList(NursingFloor nursingFloor);

    /**
     * 查询全部楼层（下拉框/全量列表用）
     *
     * @return 楼层集合
     */
    public List<NursingFloor> selectNursingFloorAll();

    /**
     * 查询楼层树形结构（含房间和床位）
     *
     * @param id 楼层主键
     * @return 楼层树形结构
     */
    public FloorTreeVO selectFloorTreeById(Long id);

    /**
     * 新增楼层
     *
     * @param nursingFloor 楼层
     * @return 结果
     */
    public int insertNursingFloor(NursingFloor nursingFloor);

    /**
     * 修改楼层
     *
     * @param nursingFloor 楼层
     * @return 结果
     */
    public int updateNursingFloor(NursingFloor nursingFloor);

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键集合
     * @return 结果
     */
    public int deleteNursingFloorByIds(Long[] ids);

    /**
     * 删除楼层信息
     *
     * @param id 楼层主键
     * @return 结果
     */
    public int deleteNursingFloorById(Long id);
}
