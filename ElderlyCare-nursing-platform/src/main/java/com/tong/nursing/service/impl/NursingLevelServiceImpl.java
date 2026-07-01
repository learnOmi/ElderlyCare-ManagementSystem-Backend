package com.tong.nursing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.tong.common.utils.DateUtils;
import com.tong.nursing.constant.NursingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.tong.nursing.mapper.NursingLevelMapper;
import com.tong.nursing.domain.NursingLevel;
import com.tong.nursing.service.INursingLevelService;
import com.tong.nursing.vo.NursingLevelVO;
import com.tong.nursing.mapper.NursingPlanMapper;
import com.tong.nursing.domain.NursingPlan;
import com.tong.nursing.mapper.NursingProjectPlanMapper;
import com.tong.nursing.domain.NursingProjectPlan;
import com.tong.nursing.mapper.NursingProjectMapper;
import com.tong.nursing.domain.NursingProject;

/**
 * 护理等级Service业务层处理
 *
 * @author Tong
 * @date 2026-06-25
 */
@Service
public class NursingLevelServiceImpl implements INursingLevelService
{
    @Autowired
    private NursingLevelMapper nursingLevelMapper;

    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 查询护理等级
     *
     * @param id 护理等级主键
     * @return 护理等级
     */
    @Override
    public NursingLevel selectNursingLevelById(Integer id)
    {
        return nursingLevelMapper.selectNursingLevelById(id);
    }

    /**
     * 查询护理等级详情（含护理计划名称和项目列表）
     *
     * @param id 护理等级主键
     * @return 护理等级详情（含护理计划名称和项目列表）
     */
    @Override
    public NursingLevelVO selectNursingLevelVOById(Integer id)
    {
        NursingLevel level = nursingLevelMapper.selectNursingLevelById(id);
        if (level == null)
        {
            return null;
        }
        NursingLevelVO vo = new NursingLevelVO();
        // 复制基本字段
        vo.setId(level.getId());
        vo.setName(level.getName());
        vo.setPlanId(level.getPlanId());
        vo.setFee(level.getFee());
        vo.setStatus(level.getStatus());
        vo.setDescription(level.getDescription());
        vo.setCreateTime(level.getCreateTime());
        vo.setUpdateTime(level.getUpdateTime());
        vo.setCreateBy(level.getCreateBy());
        vo.setUpdateBy(level.getUpdateBy());
        vo.setRemark(level.getRemark());

        // 查询护理计划名称
        if (level.getPlanId() != null)
        {
            NursingPlan plan = nursingPlanMapper.selectNursingPlanById(level.getPlanId());
            if (plan != null)
            {
                vo.setPlanName(plan.getPlanName());
            }
        }

        // 查询关联的项目列表
        List<NursingProjectPlan> projectPlans = nursingProjectPlanMapper.selectNursingProjectPlanByPlanId(level.getPlanId() != null ? level.getPlanId().longValue() : null);
        List<NursingLevelVO.ProjectVO> projectVOList = new ArrayList<>();
        if (projectPlans != null)
        {
            for (NursingProjectPlan pp : projectPlans)
            {
                NursingLevelVO.ProjectVO projectVO = new NursingLevelVO.ProjectVO();
                projectVO.setProjectId(pp.getProjectId());
                projectVO.setExecuteTime(pp.getExecuteTime());
                projectVO.setExecuteCycle(pp.getExecuteCycle());
                projectVO.setExecuteFrequency(pp.getExecuteFrequency());
                // 查询项目名称
                if (pp.getProjectId() != null)
                {
                    NursingProject project = nursingProjectMapper.selectNursingProjectById(pp.getProjectId().longValue());
                    if (project != null)
                    {
                        projectVO.setProjectName(project.getName());
                    }
                }
                projectVOList.add(projectVO);
            }
        }
        vo.setProjectList(projectVOList);
        return vo;
    }

    /**
     * 查询护理等级列表
     *
     * @param nursingLevel 护理等级
     * @return 护理等级
     */
    @Override
    public List<NursingLevel> selectNursingLevelList(NursingLevel nursingLevel)
    {
        return nursingLevelMapper.selectNursingLevelList(nursingLevel);
    }

    /**
     * 查询全部护理等级（下拉框/全量列表用），带Redis缓存
     *
     * @return 护理等级集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NursingLevel> selectNursingLevelAll()
    {
        String cacheKey = NursingConstants.REDIS_LEVEL_ALL_KEY;
        
        List<NursingLevel> cachedList = (List<NursingLevel>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedList != null && !cachedList.isEmpty())
        {
            return cachedList;
        }
        
        List<NursingLevel> list = nursingLevelMapper.selectNursingLevelAll();
        
        if (list != null && !list.isEmpty())
        {
            redisTemplate.opsForValue().set(cacheKey, list, NursingConstants.REDIS_CACHE_EXPIRE_MINUTES, TimeUnit.MINUTES);
        }
        
        return list;
    }

    /**
     * 新增护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int insertNursingLevel(NursingLevel nursingLevel)
    {
        nursingLevel.setCreateTime(DateUtils.getNowDate());
        int result = nursingLevelMapper.insertNursingLevel(nursingLevel);
        if (result > 0)
        {
            clearLevelCache();
        }
        return result;
    }

    /**
     * 修改护理等级
     *
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int updateNursingLevel(NursingLevel nursingLevel)
    {
        nursingLevel.setUpdateTime(DateUtils.getNowDate());
        int result = nursingLevelMapper.updateNursingLevel(nursingLevel);
        if (result > 0)
        {
            clearLevelCache();
        }
        return result;
    }

    /**
     * 批量删除护理等级
     *
     * @param ids 需要删除的护理等级主键
     * @return 结果
     */
    @Override
    public int deleteNursingLevelByIds(Integer[] ids)
    {
        int result = nursingLevelMapper.deleteNursingLevelByIds(ids);
        if (result > 0)
        {
            clearLevelCache();
        }
        return result;
    }

    /**
     * 删除护理等级信息
     *
     * @param id 护理等级主键
     * @return 结果
     */
    @Override
    public int deleteNursingLevelById(Integer id)
    {
        int result = nursingLevelMapper.deleteNursingLevelById(id);
        if (result > 0)
        {
            clearLevelCache();
        }
        return result;
    }

    /**
     * 清除护理等级缓存
     */
    private void clearLevelCache()
    {
        redisTemplate.delete(NursingConstants.REDIS_LEVEL_ALL_KEY);
    }
}
