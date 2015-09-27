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
 * Date: 6/7/14
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TasviePishAzMoedVamJob extends QuartzJobBean
{
    private TasviePishAzMoedVamTask tasviePishAzMoedVamTask;

    public void setTasviePishAzMoedVamTask(TasviePishAzMoedVamTask tasviePishAzMoedVamTask)
    {
        this.tasviePishAzMoedVamTask = tasviePishAzMoedVamTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        synchronized (TasviePishAzMoedVamJob.class)
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
            String property = prop.getProperty("Scheduler.StartTasvieVamAzAndukhte.Enable");
            if (property.trim().equals("true"))
            {
                final org.slf4j.Logger logger = LoggerFactory.getLogger(TasviePishAzMoedVamJob.class);
                String header = String.format("TasvieVamAzAndukhte started at : " + DateUtil.getCurrentTime());
                logger.info(String.format(header));
                try
                {
                   tasviePishAzMoedVamTask.changeTransitionTasvieAzAndukhte();
//                   tasviePishAzMoedVamTask.changeTransitionTasvieNahaei();
                }
                catch (Exception e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                final org.slf4j.Logger logger2 = LoggerFactory.getLogger(TasviePishAzMoedVamJob.class);
                String header2 = String.format("TasvieVamAzAndukhte ended at : " + DateUtil.getCurrentTime());
                logger2.info(String.format(header2));
            }
        }
    }
}

