package com.bitarts.parsian.service.factory;

import com.bitarts.common.util.ApplicationContextUtil;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.IBaseService;
import com.bitarts.parsian.service.IKhesaratService;
import com.bitarts.parsian.service.INamayandeService;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class InsuranceServiceFactory {
    public static IBaseService getService(String serviceName, SessionFactory sessionFactory){
        return (IBaseService) ApplicationContextUtil.getBaseFactory().getBean(serviceName);
    }

    public static IAsnadeSodorService getAsnadeSodorService(SessionFactory sessionFactory){
        return (IAsnadeSodorService) ApplicationContextUtil.getBaseFactory().getBean(IAsnadeSodorService.SERVICE_NAME);
    }

    public static IKhesaratService getWithoutSessionKhesaratService(){
        return (IKhesaratService) ApplicationContextUtil.getBaseFactory().getBean(IKhesaratService.SERVICE_NAME);
    }

    public static IKhesaratService getKhesaratService(SessionFactory sessionFactory){
        return (IKhesaratService) ApplicationContextUtil.getBaseFactory().getBean(IKhesaratService.SERVICE_NAME);
    }

    public static INamayandeService getNamayandeService(SessionFactory sessionFactory){
        return (INamayandeService) ApplicationContextUtil.getBaseFactory().getBean(INamayandeService.SERVICE_NAME);
    }

    public static IAsnadeSodorService getWithoutSessionAsnadeSodorService(){
        return (IAsnadeSodorService) ApplicationContextUtil.getBaseFactory().getBean(IAsnadeSodorService.SERVICE_NAME);
    }

    public static INamayandeService getWithoutSessionNamayandeService(){
        return (INamayandeService) ApplicationContextUtil.getBaseFactory().getBean(INamayandeService.SERVICE_NAME);
    }

    public static SessionFactory setUpSessionFactory() throws Exception
    {
        SessionFactory sessionFactory = (SessionFactory) ApplicationContextUtil.getBaseFactory().getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.NEVER);
        try{
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
        }catch (Exception e){

        }
        return sessionFactory;
    }

    public static void tearDownSessionFactory(SessionFactory sessionFactory) throws Exception
    {
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
        Session session = sessionHolder.getSession();
        SessionFactoryUtils.closeSession(session);

    }
}
