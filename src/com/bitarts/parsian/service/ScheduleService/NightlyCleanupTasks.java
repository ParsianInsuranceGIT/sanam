package com.bitarts.parsian.service.ScheduleService;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.IDarkhastService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/8/14
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class NightlyCleanupTasks
{
    public void hazfeTaghsiteVamInvalid() throws Exception
    {
        final Logger logger = LoggerFactory.getLogger(NightlyCleanupTasks.class);
        String header = String.format("hazfeTaghsiteVamInvalid started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
        IAsnadeSodorService asnadeSodorService= (IAsnadeSodorService) InsuranceServiceFactory.getService(IAsnadeSodorService.SERVICE_NAME, sessionFactory);

        List<GhestBandi> ghestBandiList= new ArrayList<GhestBandi>();
        ghestBandiList=asnadeSodorService.listInvalidGhestBandiVams();
        for (GhestBandi gb : ghestBandiList)
        {
            try
            {
                asnadeSodorService.deleteGhestBandiById(gb);
                header = String.format("hazfe ghestBandi " + gb.getId() + " ok !");
                logger.info(String.format(header));
            }
            catch (Exception ex)
            {
                header = String.format("Exception hazfe ghestBandi " + gb.getId());
                logger.info(String.format(header));
                logger.error(ex.toString());
                logger.error(ex.getStackTrace().toString());
            }

        }
        header = String.format("hazfeTaghsiteVamInvalid end at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }

    public void hazfeKhesaratBeduneState() throws Exception
    {
        final Logger logger = LoggerFactory.getLogger(NightlyCleanupTasks.class);
        String header = String.format("hazfeKhesaratBeduneState started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
        IDarkhastService darkhastService= (IDarkhastService) InsuranceServiceFactory.getService(IDarkhastService.SERVICE_NAME, sessionFactory);

        List<DarkhastBazkharid> darkhastBazkharidList= new ArrayList<DarkhastBazkharid>();
        darkhastBazkharidList=darkhastService.findDarkhastKhesaratBeduneState();
        for (DarkhastBazkharid db : darkhastBazkharidList)
        {
            try
            {
                darkhastService.deleteDarkhast(db.getDarkhast());
                darkhastService.deleteDarkhastBazkharid(db);
            }
            catch (Exception ex)
            {
                header = String.format("Exception delete (khesarat) darkhastBazkharid_Id:  " + db.getId());
                logger.info(String.format(header));
                logger.error(ex.toString());
            }

        }
        header = String.format("hazfeKhesaratBeduneState end at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }

    public void cleanChangeWithSerachField() throws Exception
    {
        final Logger logger = LoggerFactory.getLogger(NightlyCleanupTasks.class);
        String header = String.format("cleanChangeWithSerachField started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
        IDarkhastService darkhastService= (IDarkhastService) InsuranceServiceFactory.getService(IDarkhastService.SERVICE_NAME, sessionFactory);


        try
        {
            darkhastService.cleanChangeWithSerachField();
        }
        catch (Exception ex)
        {
            header = String.format("Exception in cleanChangeWithSerachField");
            logger.info(String.format(header));
            logger.error(ex.toString());
        }
        header = String.format("cleanChangeWithSerachField end at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }

}
