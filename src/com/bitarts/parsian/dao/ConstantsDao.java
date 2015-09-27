package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantsDao extends BaseDAO {
    public Constants findById(Integer id) {
        return (Constants) super.findById(Constants.class, id);
    }

    public List<Constants> findByName(Constants.Keys name, Constants.KeysParam name2) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Constants.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("name2", name2)).addOrder(Order.desc("createdDate")).list();
    }

    public List<Constants> findByNameConsiderEffect(Constants.Keys name, Constants.KeysParam name2, String date, Tarh tarh) {
        List<Constants> list1 = null;
        if (tarh != null)
            list1 = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Constants.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("name2", name2)).add(Restrictions.le("dateEffect", date)).addOrder(Order.desc("dateEffect")).add(Restrictions.eq("tarh.id", tarh.getId())).setCacheable(true).list();
        if (list1 == null || list1.size() == 0)
            return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Constants.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("name2", name2)).add(Restrictions.le("dateEffect", date)).addOrder(Order.desc("dateEffect")).add(Restrictions.isNull("tarh")).setCacheable(true).list();
        else return list1;
    }

    public List<Constants> findByNameConsiderEffectByApplyStyle(Constants.Keys name, Constants.KeysParam name2, String date, Constants.ConstantsApplyStyle applyStyle) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Constants.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("name2", name2)).add(Restrictions.eq("applyStyle", applyStyle)).add(Restrictions.le("dateEffect", date)).setCacheable(true).addOrder(Order.desc("dateEffect")).list();
    }

    public void saveOrUpdate(Constants c) {
        if (c.getCreatedDate() == null) c.setCreatedDate(DateUtil.getCurrentDate());
        if (c.getDateEffect() == null) c.setDateEffect(DateUtil.getCurrentDate());
        super.saveOrUpdate(c);

    }


    public List<Constants> findAll() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Constants.class).addOrder(Order.desc("createdDate")).addOrder(Order.desc("id")).list();
    }

    public List<Tarh> listAllTarhs() {
        List<Tarh> retList = new ArrayList<Tarh>();
        Tarh nullTarh = new Tarh();
        nullTarh.setId(null);
        nullTarh.setName("");
        retList.add(nullTarh);
        retList.addAll(getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Tarh.class).addOrder(Order.desc("name")).list());

        return retList;
    }

    public void saveOrUpdateTarh(Tarh tarh) {
        super.saveOrUpdate(tarh);
    }

    public Tarh findTarhById(Long id) {
        return (Tarh) super.findById(Tarh.class, id);
    }
}

