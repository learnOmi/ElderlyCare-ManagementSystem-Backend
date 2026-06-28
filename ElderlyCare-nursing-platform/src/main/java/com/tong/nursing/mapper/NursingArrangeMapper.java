package com.tong.nursing.mapper;

import java.util.List;
import com.tong.nursing.domain.NursingArrange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 护理排班Mapper接口
 *
 * @author Tong
 * @date 2026-06-28
 */
@Mapper
public interface NursingArrangeMapper
{
    /**
     * 查询护理排班
     *
     * @param id 护理排班主键
     * @return 护理排班
     */
    public NursingArrange selectNursingArrangeById(Long id);

    /**
     * 查询护理排班列表
     *
     * @param nursingArrange 护理排班
     * @return 护理排班集合
     */
    public List<NursingArrange> selectNursingArrangeList(NursingArrange nursingArrange);

    /**
     * 新增护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    public int insertNursingArrange(NursingArrange nursingArrange);

    /**
     * 修改护理排班
     *
     * @param nursingArrange 护理排班
     * @return 结果
     */
    public int updateNursingArrange(NursingArrange nursingArrange);

    /**
     * 删除护理排班
     *
     * @param id 护理排班主键
     * @return 结果
     */
    public int deleteNursingArrangeById(Long id);

    /**
     * 批量删除护理排班
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingArrangeByIds(Long[] ids);
}
