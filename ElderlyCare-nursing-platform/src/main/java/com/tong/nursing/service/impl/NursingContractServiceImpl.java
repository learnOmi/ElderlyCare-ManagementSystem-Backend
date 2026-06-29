package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingContractMapper;
import com.tong.nursing.domain.NursingContract;
import com.tong.nursing.service.INursingContractService;

/**
 * 合同Service业务层处理
 *
 * @author Tong
 * @date 2026-06-29
 */
@Service
public class NursingContractServiceImpl implements INursingContractService
{
    @Autowired
    private NursingContractMapper nursingContractMapper;

    /**
     * 查询合同
     *
     * @param id 合同主键
     * @return 合同
     */
    @Override
    public NursingContract selectNursingContractById(Long id)
    {
        return nursingContractMapper.selectNursingContractById(id);
    }

    /**
     * 查询合同列表
     *
     * @param nursingContract 合同
     * @return 合同
     */
    @Override
    public List<NursingContract> selectNursingContractList(NursingContract nursingContract)
    {
        return nursingContractMapper.selectNursingContractList(nursingContract);
    }

    /**
     * 新增合同
     *
     * @param nursingContract 合同
     * @return 结果
     */
    @Override
    public int insertNursingContract(NursingContract nursingContract)
    {
        nursingContract.setCreateTime(DateUtils.getNowDate());
        return nursingContractMapper.insertNursingContract(nursingContract);
    }

    /**
     * 修改合同
     *
     * @param nursingContract 合同
     * @return 结果
     */
    @Override
    public int updateNursingContract(NursingContract nursingContract)
    {
        nursingContract.setUpdateTime(DateUtils.getNowDate());
        return nursingContractMapper.updateNursingContract(nursingContract);
    }

    /**
     * 批量删除合同
     *
     * @param ids 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deleteNursingContractByIds(Long[] ids)
    {
        return nursingContractMapper.deleteNursingContractByIds(ids);
    }

    /**
     * 删除合同信息
     *
     * @param id 合同主键
     * @return 结果
     */
    @Override
    public int deleteNursingContractById(Long id)
    {
        return nursingContractMapper.deleteNursingContractById(id);
    }
}
