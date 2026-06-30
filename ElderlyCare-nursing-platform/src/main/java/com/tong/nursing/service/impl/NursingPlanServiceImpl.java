package com.tong.nursing.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.tong.common.utils.DateUtils;
import com.tong.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tong.nursing.mapper.NursingPlanMapper;
import com.tong.nursing.domain.NursingPlan;
import com.tong.nursing.service.INursingPlanService;
import com.tong.nursing.mapper.NursingProjectPlanMapper;
import com.tong.nursing.domain.NursingProjectPlan;
import com.tong.nursing.dto.NursingPlanDTO;
import com.tong.nursing.vo.NursingPlanVO;
import com.tong.nursing.mapper.NursingProjectMapper;
import com.tong.nursing.domain.NursingProject;

/**
 * 护理计划Service业务层处理
 *
 * @author Tong
 * @date 2026-06-25
 */
@Service
public class NursingPlanServiceImpl implements INursingPlanService
{
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    /**
     * 查询护理计划
     *
     * @param id 护理计划主键
     * @return 护理计划
     */
    @Override
    public NursingPlan selectNursingPlanById(Long id)
    {
        return nursingPlanMapper.selectNursingPlanById(id);
    }

    /**
     * 查询护理计划详情（含项目列表）
     *
     * @param id 护理计划主键
     * @return 护理计划详情（含项目列表）
     */
    @Override
    public NursingPlanVO selectNursingPlanVOById(Long id)
    {
        NursingPlan plan = nursingPlanMapper.selectNursingPlanById(id);
        if (plan == null)
        {
            return null;
        }
        NursingPlanVO vo = new NursingPlanVO();
        // 复制基本字段
        vo.setId(plan.getId());
        vo.setSortNo(plan.getSortNo());
        vo.setPlanName(plan.getPlanName());
        vo.setStatus(plan.getStatus());
        vo.setCreateTime(plan.getCreateTime());
        vo.setUpdateTime(plan.getUpdateTime());
        vo.setCreateBy(plan.getCreateBy());
        vo.setUpdateBy(plan.getUpdateBy());
        vo.setRemark(plan.getRemark());

        // 查询关联的项目计划
        List<NursingProjectPlan> projectPlans = nursingProjectPlanMapper.selectNursingProjectPlanByPlanId(id);
        List<NursingPlanVO.ProjectPlanVO> projectPlanVOList = new ArrayList<>();
        for (NursingProjectPlan pp : projectPlans)
        {
            NursingPlanVO.ProjectPlanVO ppVO = new NursingPlanVO.ProjectPlanVO();
            ppVO.setId(pp.getId());
            ppVO.setProjectId(pp.getProjectId());
            ppVO.setExecuteTime(pp.getExecuteTime());
            ppVO.setExecuteCycle(pp.getExecuteCycle());
            ppVO.setExecuteFrequency(pp.getExecuteFrequency());
            // 查询项目名称
            if (pp.getProjectId() != null)
            {
                NursingProject project = nursingProjectMapper.selectNursingProjectById(pp.getProjectId().longValue());
                if (project != null)
                {
                    ppVO.setProjectName(project.getName());
                }
            }
            projectPlanVOList.add(ppVO);
        }
        vo.setProjectPlanList(projectPlanVOList);
        return vo;
    }

    /**
     * 查询护理计划列表
     *
     * @param nursingPlan 护理计划
     * @return 护理计划
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan)
    {
        return nursingPlanMapper.selectNursingPlanList(nursingPlan);
    }

    /**
     * 查询全部护理计划（下拉框/全量列表用）
     *
     * @return 护理计划集合
     */
    @Override
    public List<NursingPlan> selectNursingPlanAll()
    {
        return nursingPlanMapper.selectNursingPlanAll();
    }

    /**
     * 新增护理计划（含项目关联）
     *
     * @param dto 护理计划DTO
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNursingPlan(NursingPlanDTO dto)
    {
        NursingPlan plan = dto.getPlan();
        plan.setCreateTime(DateUtils.getNowDate());
        plan.setCreateBy(SecurityUtils.getUsername());
        int rows = nursingPlanMapper.insertNursingPlan(plan);

        // 批量插入项目计划关联
        if (dto.getProjectPlans() != null && !dto.getProjectPlans().isEmpty())
        {
            List<NursingProjectPlan> projectPlans = new ArrayList<>();
            for (NursingPlanDTO.ProjectPlanItem item : dto.getProjectPlans())
            {
                NursingProjectPlan pp = new NursingProjectPlan();
                pp.setPlanId(plan.getId().intValue());
                pp.setProjectId(item.getProjectId());
                pp.setExecuteTime(item.getExecuteTime());
                pp.setExecuteCycle(item.getExecuteCycle());
                pp.setExecuteFrequency(item.getExecuteFrequency());
                pp.setCreateTime(DateUtils.getNowDate());
                pp.setCreateBy(SecurityUtils.getUsername());
                projectPlans.add(pp);
            }
            if (!projectPlans.isEmpty())
            {
                nursingProjectPlanMapper.batchInsertNursingProjectPlan(projectPlans);
            }
        }
        return rows;
    }

    /**
     * 修改护理计划（含项目关联）
     *
     * @param dto 护理计划DTO
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNursingPlan(NursingPlanDTO dto)
    {
        NursingPlan plan = dto.getPlan();
        plan.setUpdateTime(DateUtils.getNowDate());
        plan.setUpdateBy(SecurityUtils.getUsername());
        int rows = nursingPlanMapper.updateNursingPlan(plan);

        // 先删除旧关联
        if (plan.getId() != null)
        {
            nursingProjectPlanMapper.deleteNursingProjectPlanByPlanId(plan.getId().longValue());
        }

        // 再插入新关联
        if (dto.getProjectPlans() != null && !dto.getProjectPlans().isEmpty())
        {
            List<NursingProjectPlan> projectPlans = new ArrayList<>();
            for (NursingPlanDTO.ProjectPlanItem item : dto.getProjectPlans())
            {
                NursingProjectPlan pp = new NursingProjectPlan();
                pp.setPlanId(plan.getId().intValue());
                pp.setProjectId(item.getProjectId());
                pp.setExecuteTime(item.getExecuteTime());
                pp.setExecuteCycle(item.getExecuteCycle());
                pp.setExecuteFrequency(item.getExecuteFrequency());
                pp.setCreateTime(DateUtils.getNowDate());
                pp.setCreateBy(SecurityUtils.getUsername());
                projectPlans.add(pp);
            }
            if (!projectPlans.isEmpty())
            {
                nursingProjectPlanMapper.batchInsertNursingProjectPlan(projectPlans);
            }
        }
        return rows;
    }

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的护理计划主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingPlanByIds(Long[] ids)
    {
        // 级联删除关联的项目计划
        nursingProjectPlanMapper.deleteNursingProjectPlanByPlanIds(ids);
        return nursingPlanMapper.deleteNursingPlanByIds(ids);
    }

    /**
     * 删除护理计划信息
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingPlanById(Long id)
    {
        // 级联删除关联的项目计划
        nursingProjectPlanMapper.deleteNursingProjectPlanByPlanId(id);
        return nursingPlanMapper.deleteNursingPlanById(id);
    }
}
