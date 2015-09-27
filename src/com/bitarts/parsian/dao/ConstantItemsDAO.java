package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm;
import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;
import com.bitarts.parsian.model.management.Bank;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/24/11
 * Time: 3:54 PM
 */
public class ConstantItemsDAO extends BaseDAO {
    public static final String ID = "id";
    public static final String CONSTANT_ITEM_KEY = "constantItemKey";
    public static final String CONSTANT_ITEM_VALUE = "constantItemValue";
    public static final String CITY_NAME = "cityName";
    public static final String CITY_TYPE = "cityType";
    public static final String CITY_PID = "cityPid";
    public static final String CITY_IDFORPID = "cityIdForPid";

    public ConstantForPishnehadForm findConstantForPishnehadFormById(Integer id) {
        return (ConstantForPishnehadForm) super.findById(ConstantForPishnehadForm.class, id);
    }

    public List findConstantForPishnehadFormByKeys(Object[] objects) {
        return super.findByPropertiesWithOrderBy(ConstantForPishnehadForm.class, CONSTANT_ITEM_KEY, objects, ID);
    }

    public List findAllConstantForPishnehadForm() {
        return super.findAllWithOrderByAsc(ConstantForPishnehadForm.class, ID);
    }

    public List findConstantSoalateVaziateSalamati() {
        return super.findAllWithOrderByAsc(ConstantSoalateVaziateSalamati.class, ID);
    }

    public List finaAllCities() {
//        return super.findByPropertyWithOrderBy(City.class, CITY_TYPE, new Integer(3), CITY_NAME);
        Criteria cities = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class);
        //.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //cities.add(Restrictions.or(Restrictions.or(Restrictions.eq(CITY_TYPE, new Integer(3)),Restrictions.eq(CITY_TYPE, new Integer(4))),Restrictions.eq(CITY_TYPE, new Integer(5))))
        cities.add(Restrictions.eq(CITY_TYPE, new Integer(3)))
                .addOrder(Order.asc(CITY_NAME));
        return cities.list();
    }

    public List findAllOstans() {
        Criteria cities = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //cities.add(Restrictions.or(Restrictions.eq(CITY_TYPE, new Integer(2)),Restrictions.eq(CITY_TYPE, new Integer(1))))
        cities.add(Restrictions.eq(CITY_TYPE, new Integer(2))).add(Restrictions.eq(CITY_PID, new Long(98)))
                .addOrder(Order.asc(CITY_NAME));
        return cities.list();
    }

    public City findCityById(long cityId) {
        return (City) super.findById(City.class, cityId);
    }

    public List<City> findCityByName(String cityName) {
        Criteria cities = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        cities.add(Restrictions.like(CITY_NAME, "%" + cityName + "%"))
                .addOrder(Order.asc(CITY_NAME))
                .setCacheable(true);
        return cities.list();
    }

    public City findCityByIdForPid(long idForPid) {
        Criteria cities = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        cities.add(Restrictions.eq(CITY_IDFORPID, idForPid));
        cities.setMaxResults(1);
        ArrayList<City> result = (ArrayList<City>) cities.list();
        if (result.size() < 1)
            return null;
        else
            return result.get(0);
    }

    public ConstantSoalateVaziateSalamati findSoalVaziateSalamatiById(Integer id) {
        return (ConstantSoalateVaziateSalamati) super.findById(ConstantSoalateVaziateSalamati.class, id);
    }

    public ConstantSoalateVaziateSalamati findSoalVaziateSalamatiBySoal(String matneSoal) {
        List<ConstantSoalateVaziateSalamati> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ConstantSoalateVaziateSalamati.class).add(Restrictions.eq("matneSoal", matneSoal)).list();
        if (list.size() > 0) return list.get(0);
        else return null;
    }

    public void saveSoal(ConstantSoalateVaziateSalamati soalItem) {
        getSession().save(soalItem);
    }

    public List<State> findStatesByStateMachine(String pishnahad_system) {
        final Session session = getSession();
        List<State> list = session.createCriteria(State.class).add(Restrictions.eq("stateMachineName", pishnahad_system)).addOrder(Order.asc("stateName")).list();
        return list;
    }

    public void removeCityById(Long cityId){
        super.deleteById(City.class, cityId);
    }

    public PaginatedListImpl<City> finaAllCities(PaginatedListImpl<City> paginatedList, String sname, Long scode, String stabe, Long scodetabe) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class, "c").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(City.class, "c").setCacheable(true);
        if (sname != null && !sname.isEmpty()) {
            criteria.add(Restrictions.ilike("c.cityName", "%" + sname + "%"));
            criteria2.add(Restrictions.ilike("c.cityName", "%" + sname + "%"));
        }
        if (scode != null) {
            criteria.add(Restrictions.eq("c.cityIdForPid", scode));
            criteria2.add(Restrictions.eq("c.cityIdForPid", scode));
        }

        if (stabe != null && !stabe.isEmpty()) {
            DetachedCriteria criteria3 = DetachedCriteria.forClass(City.class, "c2");
            criteria3.add(Restrictions.ilike("c2.cityName", "%" + stabe+ "%"));
            criteria3.setProjection(Property.forName("c2.cityIdForPid"));
            criteria.add(Property.forName("c.cityPid").in(criteria3));
            criteria2.add(Property.forName("c.cityPid").in(criteria3));
        }
        if (scodetabe != null) {
            DetachedCriteria criteria3 = DetachedCriteria.forClass(City.class, "c2");
            criteria3.add(Restrictions.eq("c2.cityIdForPid", scodetabe));
            criteria3.setProjection(Property.forName("c2.cityIdForPid"));
            criteria.add(Property.forName("c.cityPid").in(criteria3));
            criteria2.add(Property.forName("c.cityPid").in(criteria3));
        }

        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize((Integer) criteria2.list().get(0));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<City> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }

    public PaginatedListImpl<Bank> finaAllBanks(PaginatedListImpl<Bank> paginatedList) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bank.class, "b").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bank.class, "b").setCacheable(true);


        criteria2.setProjection(Projections.rowCount());

       paginatedList.setFullListSize((((Long)criteria2.list().get(0)).intValue()) );
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<Bank> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }
}
