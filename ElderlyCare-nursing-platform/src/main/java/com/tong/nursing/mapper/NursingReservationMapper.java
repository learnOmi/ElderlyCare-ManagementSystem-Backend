package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingReservation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约Mapper接口
 *
 * @author Tong
 * @date 2026-06-29
 */
@Mapper
public interface NursingReservationMapper
{
    /**
     * 查询预约
     *
     * @param id 预约主键
     * @return 预约
     */
    public NursingReservation selectNursingReservationById(Long id);

    /**
     * 查询预约列表
     *
     * @param nursingReservation 预约
     * @return 预约集合
     */
    public List<NursingReservation> selectNursingReservationList(NursingReservation nursingReservation);

    /**
     * 新增预约
     *
     * @param nursingReservation 预约
     * @return 结果
     */
    public int insertNursingReservation(NursingReservation nursingReservation);

    /**
     * 修改预约
     *
     * @param nursingReservation 预约
     * @return 结果
     */
    public int updateNursingReservation(NursingReservation nursingReservation);

    /**
     * 删除预约
     *
     * @param id 预约主键
     * @return 结果
     */
    public int deleteNursingReservationById(Long id);

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingReservationByIds(Long[] ids);
}
