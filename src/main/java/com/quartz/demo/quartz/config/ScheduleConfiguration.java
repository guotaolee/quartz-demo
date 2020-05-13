package com.quartz.demo.quartz.config;

import com.quartz.demo.quartz.job.DemoJob01;
import com.quartz.demo.quartz.job.DemoJob02;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-13
 * Time: 21:48
 * Description: No Description
 */
@Configuration
public class ScheduleConfiguration {

    public static class DemoJob01Configuration{
        @Bean
        public JobDetail demoJob01() {
            return JobBuilder.newJob(DemoJob01.class)
                    .withIdentity("demoJob01") //name 为demoJob01
                    .storeDurably() //设置没有 Trigger 关联的时候，任务是否保留为true。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger domoJob01Trigger() {
            //构建一个简单的任务调度，使用SimpleScheduleBuilder
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5) //每5秒执行一次
                    .repeatForever();

            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(demoJob01()) //job 为demoJob01
                    .withIdentity("domoJob01Trigger") //name 为domoJob01Trigger
                    .withSchedule(scheduleBuilder) //schedule 为scheduleBuilder
                    .build();
        }
    }

    public static class DemoJob02Configuration{
        @Bean
        public JobDetail demoJob02() {
            return JobBuilder.newJob(DemoJob02.class)
                    .withIdentity("demoJob02") //name 为demoJob01
                    .storeDurably() //设置没有 Trigger 关联的时候，任务是否保留为true。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger domoJob02Trigger() {
            //构建一个基于 Quartz Cron 表达式的任务调度，使用CronScheduleBuilder
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *"); //每十秒执行一次

            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(demoJob02()) //job 为demoJob02
                    .withIdentity("domoJob02Trigger") //name 为domoJob02Trigger
                    .withSchedule(scheduleBuilder) //schedule 为scheduleBuilder
                    .build();
        }
    }
}
