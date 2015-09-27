package com.bitarts.parsian.service.ScheduleService;

/**
 * User: a.sabzechian
 * Date: 12/16/13
 * Time: 1:30 PM
 */
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.Properties;

public class SMSServiceJob extends QuartzJobBean {
    private SMSServiceTask smsServiceTask;

    public void setSmsServiceTask(SMSServiceTask smsServiceTask) {
        this.smsServiceTask = smsServiceTask;
    }

    public SMSServiceJob() {
        this.smsServiceTask = new SMSServiceTask();
    }

    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = prop.getProperty("Scheduler.SendAlertSMSForGhest.Enable");
        if (property.trim().equals("true")){
            smsServiceTask.SendSmsForGhest();
            smsServiceTask.findInformationGhest20DayAfter();
        }

    }
}
