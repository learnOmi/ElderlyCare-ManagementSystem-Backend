package com.tong.nursing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.alibaba.fastjson2.JSON;
import com.tong.common.exception.ServiceException;
import com.tong.common.utils.DateUtils;
import com.tong.nursing.constant.NursingConstants;
import com.tong.nursing.dto.CheckInApplyDto;
import com.tong.nursing.domain.*;
import com.tong.nursing.mapper.NursingElderFamilyMapper;
import com.tong.nursing.mapper.NursingElderMapper;
import com.tong.nursing.mapper.NursingBedMapper;
import com.tong.nursing.mapper.NursingContractMapper;
import com.tong.nursing.mapper.NursingFamilyMapper;
import com.tong.nursing.service.INursingCheckInConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingCheckInMapper;
import com.tong.nursing.service.INursingCheckInService;
import com.tong.nursing.vo.NursingCheckInDetailVO;
import com.tong.nursing.vo.NursingCheckInListVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入住申请Service业务层处理
 *
 * @author Tong
 * @date 2026-06-28
 */
@Service
public class NursingCheckInServiceImpl implements INursingCheckInService
{
    @Autowired
    private NursingCheckInMapper nursingCheckInMapper;

    @Autowired
    private NursingElderMapper nursingElderMapper;

    @Autowired
    private NursingBedMapper nursingBedMapper;

    @Autowired
    private NursingContractMapper nursingContractMapper;

    @Autowired
    private NursingElderFamilyMapper nursingElderFamilyMapper;

    @Autowired
    private NursingFamilyMapper nursingFamilyMapper;

    @Autowired
    private INursingCheckInConfigService nursingCheckInConfigService;

    /**
     * 查询入住申请
     *
     * @param id 入住申请主键
     * @return 入住申请
     */
    @Override
    public NursingCheckIn selectNursingCheckInById(Long id)
    {
        return nursingCheckInMapper.selectNursingCheckInById(id);
    }

    /**
     * 查询入住申请详情（包含老人、家属、床位、房间、楼层、护理等级等完整信息）
     *
     * @param id 入住申请主键
     * @return 入住申请详情
     */
    @Override
    public NursingCheckInDetailVO selectCheckInDetailById(Long id)
    {
        return nursingCheckInMapper.selectCheckInDetailById(id);
    }

    /**
     * 查询入住申请列表（包含老人详细信息和护理等级信息，用于列表接口）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请列表
     */
    @Override
    public List<NursingCheckInListVO> selectCheckInList(NursingCheckIn nursingCheckIn)
    {
        return nursingCheckInMapper.selectCheckInList(nursingCheckIn);
    }

    /**
     * 查询入住申请列表（仅基础字段，用于导出等功能）
     *
     * @param nursingCheckIn 入住申请
     * @return 入住申请
     */
    @Override
    public List<NursingCheckIn> selectNursingCheckInList(NursingCheckIn nursingCheckIn)
    {
        return nursingCheckInMapper.selectNursingCheckInList(nursingCheckIn);
    }

    /**
     * 新增入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    @Override
    public int insertNursingCheckIn(NursingCheckIn nursingCheckIn)
    {
        nursingCheckIn.setCreateTime(DateUtils.getNowDate());
        return nursingCheckInMapper.insertNursingCheckIn(nursingCheckIn);
    }

    /**
     * 修改入住申请
     *
     * @param nursingCheckIn 入住申请
     * @return 结果
     */
    @Override
    public int updateNursingCheckIn(NursingCheckIn nursingCheckIn)
    {
        nursingCheckIn.setUpdateTime(DateUtils.getNowDate());
        return nursingCheckInMapper.updateNursingCheckIn(nursingCheckIn);
    }

    /**
     * 批量删除入住申请
     *
     * @param ids 需要删除的入住申请主键
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInByIds(Long[] ids)
    {
        return nursingCheckInMapper.deleteNursingCheckInByIds(ids);
    }

    /**
     * 删除入住申请
     *
     * @param id 入住申请主键
     * @return 结果
     */
    @Override
    public int deleteNursingCheckInById(Long id)
    {
        return nursingCheckInMapper.deleteNursingCheckInById(id);
    }

    /**
     * 入住申请（完整流程）
     * 8步流程：
     * 1. 校验老人是否已入住
     * 2. 校验床位是否空闲
     * 3. 更新床位状态为已入住
     * 4. 新增/更新老人信息
     * 5. 新增合同（自动创建）
     * 6. 新增入住记录
     * 7. 新增入住配置
     * 8. 保存家属列表
     *
     * @param dto 入住申请DTO
     * @return 入住申请ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long apply(CheckInApplyDto dto) {
        // 1. 校验老人是否已入住（身份证号查重）
        String idCard = dto.getElder().getIdCard();
        checkElderCheckInStatus(idCard);

        // 2. 校验床位是否空闲
        Long bedId = dto.getCheckInConfig().getBedId();
        NursingBed bed = checkBedStatus(bedId);

        // 3. 更新床位状态为已入住
        updateBedStatus(bedId);

        // 4. 新增/更新老人信息
        NursingElder elder = saveOrUpdateElder(dto, bedId, bed);

        // 5. 新增合同（自动创建）
        Long contractId = addContract(dto, elder);

        // 6. 新增入住记录
        NursingCheckIn checkIn = addCheckIn(dto, elder, bed, contractId);

        // 7. 新增入住配置
        addCheckInConfig(dto, checkIn);

        // 8. 保存家属列表
        saveFamilyList(elder.getId(), dto.getFamilyList());

        return checkIn.getId();
    }

    /**
     * 校验老人是否已入住
     */
    private void checkElderCheckInStatus(String idCard) {
        NursingCheckIn query = new NursingCheckIn();
        // 通过老人表关联查询，校验是否存在已入住状态的记录
        NursingElder elder = nursingElderMapper.selectElderByIdCard(idCard);
        if (elder != null) {
            NursingCheckIn checkIn = new NursingCheckIn();
            checkIn.setElderId(elder.getId());
            checkIn.setStatus(NursingConstants.CheckInStatus.CHECKED_IN);
            List<NursingCheckIn> existingList = nursingCheckInMapper.selectNursingCheckInList(checkIn);
            if (!existingList.isEmpty()) {
                throw new ServiceException("老人已办理入住，请勿重复办理");
            }
        }
    }

    /**
     * 校验床位是否空闲
     */
    private NursingBed checkBedStatus(Long bedId) {
        NursingBed bed = nursingBedMapper.selectNursingBedById(bedId);
        if (bed == null) {
            throw new ServiceException("床位不存在");
        }
        if (!NursingConstants.BedStatus.FREE.equals(bed.getBedStatus())) {
            throw new ServiceException("床位已被占用或不可用");
        }
        return bed;
    }

    /**
     * 更新床位状态为已入住
     */
    private void updateBedStatus(Long bedId) {
        NursingBed bed = new NursingBed();
        bed.setId(bedId);
        bed.setBedStatus(NursingConstants.BedStatus.OCCUPIED);
        int rows = nursingBedMapper.updateNursingBed(bed);
        if (rows == 0) {
            throw new ServiceException("更新床位状态失败");
        }
    }

    /**
     * 新增或更新老人信息
     */
    private NursingElder saveOrUpdateElder(CheckInApplyDto dto, Long bedId, NursingBed bed) {
        CheckInApplyDto.ElderDto elderDto = dto.getElder();
        NursingElder elder = nursingElderMapper.selectElderByIdCard(elderDto.getIdCard());

        if (elder == null) {
            elder = new NursingElder();
        }

        BeanUtils.copyProperties(elderDto, elder);
        elder.setCheckInStatus(NursingConstants.ElderCheckInStatus.CHECKED_IN);
        elder.setBedId(bedId);
        elder.setStatus(1);

        int rows;
        if (elder.getId() != null) {
            elder.setUpdateTime(DateUtils.getNowDate());
            rows = nursingElderMapper.updateNursingElder(elder);
        } else {
            elder.setCreateTime(DateUtils.getNowDate());
            rows = nursingElderMapper.insertNursingElder(elder);
        }

        if (rows == 0) {
            throw new ServiceException("保存老人信息失败");
        }

        return elder;
    }

    /**
     * 新增合同
     */
    private Long addContract(CheckInApplyDto dto, NursingElder elder) {
        CheckInApplyDto.CheckInContractDto contractDto = dto.getContract();
        NursingContract contract = new NursingContract();

        if (contractDto != null) {
            BeanUtils.copyProperties(contractDto, contract);
        }

        contract.setElderId(elder.getId());
        contract.setElderName(elder.getName());
        contract.setContractNo("LRRZ" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        contract.setStatus(NursingConstants.ContractStatus.SIGNED);

        if (contract.getSignDate() == null) {
            contract.setSignDate(new Date());
        }

        int rows = nursingContractMapper.insertNursingContract(contract);
        if (rows == 0) {
            throw new ServiceException("新增合同失败");
        }

        return contract.getId();
    }

    /**
     * 新增入住记录
     */
    private NursingCheckIn addCheckIn(CheckInApplyDto dto, NursingElder elder, NursingBed bed, Long contractId) {
        CheckInApplyDto.CheckInConfigDto configDto = dto.getCheckInConfig();
        NursingCheckIn checkIn = new NursingCheckIn();

        checkIn.setElderId(elder.getId());
        checkIn.setBedId(configDto.getBedId());
        checkIn.setLevelId(configDto.getNursingLevelId());
        checkIn.setCheckInDate(configDto.getCheckInDate() != null ? configDto.getCheckInDate() : new Date());
        checkIn.setExpectedCheckOutDate(configDto.getExpectedCheckOutDate());
        checkIn.setStatus(NursingConstants.CheckInStatus.CHECKED_IN);
        checkIn.setApplyDate(new Date());

        // 将家属列表存入remark字段（JSON格式）
        if (dto.getFamilyList() != null && !dto.getFamilyList().isEmpty()) {
            checkIn.setRemark(JSON.toJSONString(dto.getFamilyList()));
        }

        int rows = nursingCheckInMapper.insertNursingCheckIn(checkIn);
        if (rows == 0) {
            throw new ServiceException("新增入住记录失败");
        }

        // 更新合同的checkInId
        NursingContract contract = new NursingContract();
        contract.setId(contractId);
        contract.setCheckInId(checkIn.getId());
        nursingContractMapper.updateNursingContract(contract);

        return checkIn;
    }

    /**
     * 新增入住配置
     */
    private void addCheckInConfig(CheckInApplyDto dto, NursingCheckIn checkIn) {
        CheckInApplyDto.CheckInConfigDto configDto = dto.getCheckInConfig();
        NursingCheckInConfig config = new NursingCheckInConfig();

        BeanUtils.copyProperties(configDto, config);
        config.setCheckInId(checkIn.getId());
        config.setNursingLevelId(configDto.getNursingLevelId());

        int rows = nursingCheckInConfigService.insertNursingCheckInConfig(config);
        if (rows == 0) {
            throw new ServiceException("新增入住配置失败");
        }
    }

    /**
     * 保存家属列表
     */
    private void saveFamilyList(Long elderId, List<NursingFamily> familyList) {
        if (familyList == null || familyList.isEmpty()) {
            return;
        }

        // 删除旧的关联记录
        nursingElderFamilyMapper.deleteNursingElderFamilyByElderId(elderId);

        // 批量保存新的关联记录
        for (NursingFamily family : familyList) {
            // 先保存家属信息
            if (family.getId() == null) {
                family.setCreateTime(DateUtils.getNowDate());
                nursingFamilyMapper.insertNursingFamily(family);
            }

            // 建立关联
            NursingElderFamily elderFamily = new NursingElderFamily();
            elderFamily.setElderId(elderId);
            elderFamily.setFamilyId(family.getId());
            elderFamily.setRelation(family.getRelation());
            elderFamily.setCreateTime(DateUtils.getNowDate());
            nursingElderFamilyMapper.insertNursingElderFamily(elderFamily);
        }
    }
}
