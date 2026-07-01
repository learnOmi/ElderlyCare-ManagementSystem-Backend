package com.tong.nursing.service.impl;

import java.util.List;
import com.tong.common.exception.ServiceException;
import com.tong.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 合同续签
     * 创建新合同，复制原合同信息，更新开始日期、结束日期、状态为已签订
     *
     * @param id 原合同ID
     * @param contract 续签信息
     * @return 新合同ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long renewContract(Long id, NursingContract contract)
    {
        NursingContract original = nursingContractMapper.selectNursingContractById(id);
        if (original == null)
        {
            throw new ServiceException("原合同不存在");
        }

        NursingContract newContract = new NursingContract();
        BeanUtils.copyProperties(original, newContract);
        newContract.setId(null);
        newContract.setContractNo(null);
        newContract.setSignDate(DateUtils.getNowDate());
        newContract.setStartDate(contract.getStartDate());
        newContract.setEndDate(contract.getEndDate());
        newContract.setStatus(1);
        newContract.setCreateTime(DateUtils.getNowDate());

        int rows = nursingContractMapper.insertNursingContract(newContract);
        if (rows > 0)
        {
            original.setStatus(3);
            nursingContractMapper.updateNursingContract(original);
            return newContract.getId();
        }
        throw new ServiceException("合同续签失败");
    }
}
