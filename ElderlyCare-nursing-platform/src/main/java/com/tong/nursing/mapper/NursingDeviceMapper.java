package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingDevice;

/**
 * 设备Mapper接口
 *
 * @author Tong
 * @date 2026-06-27
 */
public interface NursingDeviceMapper
{
    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    public NursingDevice selectNursingDeviceById(Long id);

    /**
     * 查询设备列表
     *
     * @param nursingDevice 设备
     * @return 设备集合
     */
    public List<NursingDevice> selectNursingDeviceList(NursingDevice nursingDevice);

    /**
     * 新增设备
     *
     * @param nursingDevice 设备
     * @return 结果
     */
    public int insertNursingDevice(NursingDevice nursingDevice);

    /**
     * 修改设备
     *
     * @param nursingDevice 设备
     * @return 结果
     */
    public int updateNursingDevice(NursingDevice nursingDevice);

    /**
     * 删除设备
     *
     * @param id 设备主键
     * @return 结果
     */
    public int deleteNursingDeviceById(Long id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingDeviceByIds(Long[] ids);
}
