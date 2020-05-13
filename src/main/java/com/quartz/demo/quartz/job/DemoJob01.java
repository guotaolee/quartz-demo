package com.quartz.demo.quartz.job;

import com.quartz.demo.quartz.service.DemoService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-13
 * Time: 16:06
 * Description: No Description
 */
public class DemoJob01 extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger counts = new AtomicInteger();//理解为计数器

    @Autowired
    private DemoService demoService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("[executeInternal][定时第 ({}) 次执行, demoService 为 ({})]", counts.incrementAndGet(),
                demoService);
    }
}
