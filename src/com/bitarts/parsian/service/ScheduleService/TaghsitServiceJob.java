package com.bitarts.parsian.service.ScheduleService;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.IPishnehadService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.bitarts.parsian.util.OmrUtil;
import org.hibernate.SessionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 1/18/14
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaghsitServiceJob extends QuartzJobBean
{
    @Override
    protected  void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        synchronized (TaghsitServiceJob.class) {

            Properties prop = new Properties();
            try {
                prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String property = prop.getProperty("Scheduler.StartTaghsitAutomate.Enable");
            if (property.trim().equals("true")) {
                final org.slf4j.Logger logger = LoggerFactory.getLogger(TaghsitServiceJob.class);
                String header = String.format("Autotaghsit started at : " + DateUtil.getCurrentTime());
                logger.info(String.format(header));
                try {
                    SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
                    IPishnehadService pishnehadService = (IPishnehadService) InsuranceServiceFactory.getService(IPishnehadService.SERVICE_NAME, sessionFactory);
                    IAsnadeSodorService asnadeSodorService = InsuranceServiceFactory.getAsnadeSodorService(sessionFactory);

                    List<Integer> pList = asnadeSodorService.findPishnehadsForAutoBatchTaghsit(false);

                    for (Integer id : pList) {
                        Pishnehad p = pishnehadService.loadPishnehadById(id);
                        try {
                            asnadeSodorService.saveGhestbandi(p, OmrUtil.getToGhestbandiYear(p.getBimename()), null);
                            header = String.format("taghsit bimename " + p.getBimename().getShomare() + " ok !");
                            logger.info(String.format(header));
                        } catch (Exception ex) {
                            header = String.format("Exception in taghsit bimename " + p.getBimename().getShomare());
                            logger.info(String.format(header));
                            logger.error(ex.toString());
                        }
                    }

                    InsuranceServiceFactory.tearDownSessionFactory(sessionFactory);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                final org.slf4j.Logger logger2 = LoggerFactory.getLogger(TaghsitServiceJob.class);
                String header2 = String.format("Autotaghsit ended at : " + DateUtil.getCurrentTime());
                logger2.info(String.format(header2));
            }
        }
    }
}
