package com.itechart.contactsList.service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzListener implements ServletContextListener {
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        System.out.println("Context Initialized");
        try {
            JobDetail job = newJob(EverydayJob.class).withIdentity("CronQuartzJob", "Group").build();
            Trigger trigger = newTrigger().withIdentity("TriggerName", "Group")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 ? * *")).build();
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        System.out.println("Context Destroyed");
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}