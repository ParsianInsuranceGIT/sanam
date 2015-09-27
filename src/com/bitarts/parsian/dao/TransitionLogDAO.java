package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.log.TransitionLog;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:19 PM
 */
public class TransitionLogDAO extends BaseDAO {
    public static String ID = "id";
    public static String DATE = "date";
    public static String TIME = "time";
    public static String user = "user";
    public static String PISHNEHAD = "pishnehad";
    public static String DARKHASTBAZKHARID = "darkhastBazkharid";
    public static String transition = "transition";

    public void save(TransitionLog transitionLog){
        super.save(transitionLog);
    }

    public List<TransitionLog> findAllTransitionLogByPishnehadId(Integer pishnehadId) {
        List<TransitionLog> s = (List<TransitionLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(TransitionLog.class,"t")

                .createAlias("t." + PISHNEHAD, "p")
                .add(Restrictions.eq("p." + ID, pishnehadId))
                .addOrder(Order.asc(DATE))
                .addOrder(Order.asc(TIME))
                .createCriteria("t.transition","tt")
                .addOrder(Order.asc("tt.stateBegin.id"))
                .addOrder(Order.asc("tt.stateEnd.id"))
                .list();
        return s;
    }

    public List<TransitionLog> findAllTransitionLogsByDarkhastBazkharidId(Integer darkhastBazkharidId) {
        List<TransitionLog> s = (List<TransitionLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(TransitionLog.class)
                .createAlias(DARKHASTBAZKHARID, "d")
                .add(Restrictions.eq("d." + ID, darkhastBazkharidId))
                .addOrder(Order.asc(DATE))
                .addOrder(Order.asc(TIME))
                .list();
        return s;
    }

    public List<TransitionLog> findAllTransitionLogsByDarkhastTaghirId(Integer darkhastTaghirId) {
        List<TransitionLog> s = (List<TransitionLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(TransitionLog.class)
                .createAlias("darkhastTaghirat", "d")
                .add(Restrictions.eq("d." + ID, darkhastTaghirId))
                .addOrder(Order.asc(DATE))
                .addOrder(Order.asc(TIME))
                .list();
        return s;
    }

}
