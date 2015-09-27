package com.bitarts.parsian.service.ScheduleService;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.service.IDarkhastService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 6/14/14
 * Time: 9:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TasviePishAzMoedVamTask
{
    public void changeTransitionTasvieAzAndukhte() throws Exception
    {
        final org.slf4j.Logger logger = LoggerFactory.getLogger(TasviePishAzMoedVamTask.class);
        String header = String.format("changeTransitionTasvieAzAndukhte started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
        IDarkhastService darkhastService = (IDarkhastService) InsuranceServiceFactory.getService(IDarkhastService.SERVICE_NAME, sessionFactory);

        List<DarkhastBazkharid> tasviePishAzMoedList = new ArrayList<DarkhastBazkharid>();
        tasviePishAzMoedList = darkhastService.findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day();

        for (DarkhastBazkharid db : tasviePishAzMoedList)
        {
            try
            {
                darkhastService.transitDarkhast(null, db.getId(), 12022, "-");
                header = String.format("Auto change transition " + db.getId() + " ok !");
                logger.info(String.format(header));
            }
            catch (Exception ex)
            {
                header = String.format("Exception auto change transition " + db.getId());
                logger.info(String.format(header));
                logger.error(ex.toString());
            }

        }
        header = String.format("changeTransitionTasvieAzAndukhte ended at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }

    public void changeTransitionTasvieNahaei() throws Exception
    {
        final org.slf4j.Logger logger = LoggerFactory.getLogger(TasviePishAzMoedVamTask.class);
        String header = String.format("changeTransitionTasvieNahaei started at : " + DateUtil.getCurrentTime());
        SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
        IDarkhastService darkhastService = (IDarkhastService) InsuranceServiceFactory.getService(IDarkhastService.SERVICE_NAME, sessionFactory);

        List<DarkhastBazkharid> tasviePishAzMoedList = new ArrayList<DarkhastBazkharid>();
        tasviePishAzMoedList = darkhastService.findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day();

        for (DarkhastBazkharid db : tasviePishAzMoedList)
        {
            try
            {
                darkhastService.transitDarkhast(null, db.getId(), 12022, "-");
                header = String.format("Auto change transition " + db.getId() + " ok !");
                logger.info(String.format(header));
            }
            catch (Exception ex)
            {
                header = String.format("Exception auto change transition " + db.getId());
                logger.info(String.format(header));
                logger.error(ex.toString());
            }

        }
    }
}
