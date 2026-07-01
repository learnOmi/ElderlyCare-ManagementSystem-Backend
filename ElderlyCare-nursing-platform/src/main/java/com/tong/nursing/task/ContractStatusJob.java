package com.tong.nursing.task;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tong.nursing.mapper.NursingContractMapper;
import com.tong.quartz.domain.SysJob;
import com.tong.quartz.util.AbstractQuartzJob;

/**
 * 合同状态自动更新定时任务
 * 每天凌晨2点执行，将到期的合同状态更新为已到期
 *
 * @author Tong
 * @date 2026-07-01
 */
@Component("contractStatusJob")
public class ContractStatusJob extends AbstractQuartzJob
{
    private static final Logger log = LoggerFactory.getLogger(ContractStatusJob.class);

    @Autowired
    private NursingContractMapper nursingContractMapper;

    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        log.info("开始执行合同状态自动更新任务...");
        Date currentDate = new Date();
        
        int count = nursingContractMapper.updateExpiredContractStatus(currentDate);
        
        log.info("合同状态自动更新任务执行完成，共更新 {} 条合同", count);
    }
}