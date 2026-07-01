package com.tong.nursing.task;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tong.nursing.mapper.NursingReservationMapper;
import com.tong.quartz.domain.SysJob;
import com.tong.quartz.util.AbstractQuartzJob;

/**
 * 预约过期自动取消定时任务
 * 每天凌晨1点执行，将过期的预约状态更新为已取消
 *
 * @author Tong
 * @date 2026-07-01
 */
@Component("reservationExpireJob")
public class ReservationExpireJob extends AbstractQuartzJob
{
    private static final Logger log = LoggerFactory.getLogger(ReservationExpireJob.class);

    @Autowired
    private NursingReservationMapper nursingReservationMapper;

    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        log.info("开始执行预约过期自动取消任务...");
        Date currentDate = new Date();
        
        int count = nursingReservationMapper.updateExpiredReservationStatus(currentDate);
        
        log.info("预约过期自动取消任务执行完成，共取消 {} 条预约", count);
    }
}