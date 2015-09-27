package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.dao.BimenameDAO;
import com.bitarts.parsian.dao.ElhaghieDao;
import com.bitarts.parsian.dao.StateDAO;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.service.IElhaghieService;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElhaghieService implements IElhaghieService {
    private ElhaghieDao elhaghieDAO;
    private BimenameDAO bimenameDAO;
    private StateDAO stateDAO;

    public Elhaghiye findById(Integer id) {
        return (Elhaghiye) elhaghieDAO.getHibernateTemplate().load(Elhaghiye.class, id);
    }

    @Transactional
    public void sabteElhaghie(Elhaghiye elhaghie) {
        elhaghie.getBimename().setState(stateDAO.findById(Constant.BIMENAME_INITIAL_STATE));
        elhaghie.setState(stateDAO.findById(3001));
        if (elhaghie.getDarkhastBazkharid() != null) {
            elhaghie.getDarkhastBazkharid().setState(stateDAO.findById(1500));
            elhaghie.getDarkhastBazkharid().setFinished(true);
        }
        bimenameDAO.updateDarkhstBazkharid(elhaghie.getDarkhastBazkharid());
        bimenameDAO.update(elhaghie.getDarkhastBazkharid().getBimename());
        elhaghieDAO.saveOrUpdate(elhaghie);
    }

    public List<Elhaghiye> findByBimename(Integer bimename, Elhaghiye.ElhaghiyeType type) {
        return elhaghieDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Elhaghiye.class).add(Restrictions.eq("bimename.id", bimename)).add(Restrictions.eq("elhaghiyeType", type)).addOrder(Order.desc("createdDate")).list();
    }

    public ElhaghieDao getElhaghieDAO() {
        return elhaghieDAO;
    }

    public void setElhaghieDAO(ElhaghieDao elhaghieDAO) {
        this.elhaghieDAO = elhaghieDAO;
    }

    public BimenameDAO getBimenameDAO() {
        return bimenameDAO;
    }

    public void setBimenameDAO(BimenameDAO bimenameDAO) {
        this.bimenameDAO = bimenameDAO;
    }

    public StateDAO getStateDAO() {
        return stateDAO;
    }

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }
}
