package com.bitarts.parsian.service.ScheduleService;


import com.bitarts.common.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/8/14
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class NightlyCleanupJobs  extends QuartzJobBean
{
    private NightlyCleanupTasks nightlyCleanupTasks;

    public void setNightlyCleanupTasks(NightlyCleanupTasks nightlyCleanupTasks)
    {
        this.nightlyCleanupTasks = nightlyCleanupTasks;
    }

    public NightlyCleanupJobs()
    {
        this.nightlyCleanupTasks = new NightlyCleanupTasks();
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        synchronized (NightlyCleanupJobs.class)
        {

            Properties prop = new Properties();
            try
            {
                prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String property = prop.getProperty("Scheduler.StartNightlyCleanup.Enable");
            if (property.trim().equals("true"))
            {
                final org.slf4j.Logger logger = LoggerFactory.getLogger(NightlyCleanupJobs.class);
                String header = String.format("NightlyCleanupJobs started at : " + DateUtil.getCurrentTime());
                logger.info(String.format(header));
                try
                {
                    nightlyCleanupTasks.hazfeTaghsiteVamInvalid();
                    nightlyCleanupTasks.hazfeKhesaratBeduneState();
                    nightlyCleanupTasks.cleanChangeWithSerachField();
                }
                catch (Exception e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                final org.slf4j.Logger logger2 = LoggerFactory.getLogger(NightlyCleanupJobs.class);
                String header2 = String.format("NightlyCleanupJobs ended at : " + DateUtil.getCurrentTime());
                logger2.info(String.format(header2));
            }
        }
    }
}
