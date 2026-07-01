package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingAlertData;
import com.tong.nursing.vo.NursingAlertDataVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警数据Mapper接口
 *
 * @author Tong
 * @date 2026-06-29
 */
@Mapper
public interface NursingAlertDataMapper
{
    /**
     * 查询告警数据
     *
     * @param id 告警数据主键
     * @return 告警数据
     */
    public NursingAlertData selectNursingAlertDataById(Long id);

    /**
     * 查询告警数据列表
     *
     * @param nursingAlertData 告警数据
     * @return 告警数据集合
     */
    public List<NursingAlertData> selectNursingAlertDataList(NursingAlertData nursingAlertData);

    /**
     * 查询告警数据视图列表（含老人姓名、床位名称、设备名称、规则名称）
     *
     * @param nursingAlertData 告警数据
     * @return 告警数据视图集合
     */
    public List<NursingAlertDataVO> selectAlertDataVOList(NursingAlertData nursingAlertData);

    /**
     * 查询告警数据视图详情
     *
     * @param id 告警数据主键
     * @return 告警数据视图
     */
    public NursingAlertDataVO selectAlertDataVOById(Long id);

    /**
     * 新增告警数据
     *
     * @param nursingAlertData 告警数据
     * @return 结果
     */
    public int insertNursingAlertData(NursingAlertData nursingAlertData);

    /**
     * 修改告警数据
     *
     * @param nursingAlertData 告警数据
     * @return 结果
     */
    public int updateNursingAlertData(NursingAlertData nursingAlertData);

    /**
     * 删除告警数据
     *
     * @param id 告警数据主键
     * @return 结果
     */
    public int deleteNursingAlertDataById(Long id);

    /**
     * 批量删除告警数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingAlertDataByIds(Long[] ids);

    /**
     * 查询未处理告警数量
     *
     * @return 未处理告警数量
     */
    public int selectUnhandleCount();
}
