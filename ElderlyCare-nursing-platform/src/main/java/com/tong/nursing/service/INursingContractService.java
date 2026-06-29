package com.tong.nursing.service;

import java.util.List;
import com.tong.nursing.domain.NursingContract;

/**
 * 合同Service接口
 *
 * @author Tong
 * @date 2026-06-29
 */
public interface INursingContractService
{
    /**
     * 查询合同
     *
     * @param id 合同主键
     * @return 合同
     */
    public NursingContract selectNursingContractById(Long id);

    /**
     * 查询合同列表
     *
     * @param nursingContract 合同
     * @return 合同集合
     */
    public List<NursingContract> selectNursingContractList(NursingContract nursingContract);

    /**
     * 新增合同
     *
     * @param nursingContract 合同
     * @return 结果
     */
    public int insertNursingContract(NursingContract nursingContract);

    /**
     * 修改合同
     *
     * @param nursingContract 合同
     * @return 结果
     */
    public int updateNursingContract(NursingContract nursingContract);

    /**
     * 批量删除合同
     *
     * @param ids 需要删除的合同主键集合
     * @return 结果
     */
    public int deleteNursingContractByIds(Long[] ids);

    /**
     * 删除合同信息
     *
     * @param id 合同主键
     * @return 结果
     */
    public int deleteNursingContractById(Long id);
}
