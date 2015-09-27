package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.management.EmzaKonande;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.transaction.TransactionManager;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
public class LoginDAO extends BaseDAO {

    TransactionManager transactionManager;

    public User authenticateUser(String username, String password) {
//        init();

        List<User> users2 = super.findAll(User.class);
        List<User> users = super.findByProperties(User.class,
                new String[]{"username", "password"},
                new Object[]{username, password});
        if (users.size() == 0) return null;
        else return users.get(0);
    }

    public User findById(Long userId) {
        return (User) super.findById(User.class, userId);
    }

    public User findByUsername(String username) {
        return (User) getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("faal", true)).setFetchMode("namayandegi", FetchMode.JOIN).setFetchMode("roles", FetchMode.JOIN).uniqueResult();
    }

    public User findOnlyByUsername(String username)
    {    User user = null;
        try
        {
            user = (User)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).setFetchMode("namayandegi", FetchMode.JOIN).setFetchMode("roles", FetchMode.JOIN).uniqueResult();
        }
          catch (Exception ex)
          {
              ex.getStackTrace();
          }
        return user;
    }

    public List<User> findAllUsers() {
        return (List<User>) super.findAllWithOrderByAsc(User.class, "firstName");
    }

    public void updateUser(User user) {
        super.update(user);
    }

    public List<EmzaKonande> findAllEmzaKonandegan() {
        return (List<EmzaKonande>) super.findAll(EmzaKonande.class);
    }

    public List<EmzaKonande> findAllEmzaKonandegan(String name, String persoanlCode, Long maxCapital) {

        Criteria emza = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(EmzaKonande.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        emza.createAlias("user", "u");
        if (name != null && !name.trim().contentEquals("")) {
            emza.add(Restrictions.or(Restrictions.like("u.firstName", name, MatchMode.ANYWHERE), Restrictions.like("u.lastName", name, MatchMode.ANYWHERE)));
        }
        if (persoanlCode != null && !persoanlCode.trim().contentEquals("")) {
            emza.add(Restrictions.eq("u.personalCode", persoanlCode));
        }
        if(maxCapital!=null)
            emza.add(Restrictions.ge("maxCapital", maxCapital));

        return emza.list();
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public EmzaKonande findEmzaKonandeById(Integer emzaKonandeAval) {
        return (EmzaKonande) getSession().get(EmzaKonande.class, emzaKonandeAval);
    }

    public List<User> findAllUsersForKarshenas() {
        return getSession().createCriteria(User.class).createCriteria("roles").add(Restrictions.eq("roleName", "ROLE_KARSHENAS_KHESARAT")).list();
    }

    public List<User> findAllUsersNotEmzakonande() {
        return (List<User>) getSession().createQuery("select distinct u from EmzaKonande e join e.user u where u.id is null").list();
    }

    public List<User> findUsersByPersonalCodeOrName(String personalCode,String fname, String lname)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class,"u");

        if (fname != null && !fname.isEmpty())
        {
            criteria.add(Restrictions.or(Restrictions.ilike("u.lastName", "%" + fname + "%"),Restrictions.ilike("u.firstName", "%" + fname + "%")));
        }
//        if (lname != null && !lname.isEmpty())
//        {
//            criteria.add(Restrictions.ilike("u.lastName", "%" + lname + "%"));
//        }
        if (personalCode != null && !personalCode.isEmpty())
        {
            criteria.add(Restrictions.ilike("u.personalCode", "%" + personalCode + "%"));
        }
        return criteria.list();
    }

    public PaginatedListImpl<EmzaKonande> findAllEmzaKonandegan(PaginatedListImpl<EmzaKonande> paginatedList, String fname, String lname, String code, Integer vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Integer namayandeId, String namayandeName, String azEtebar, String taEtebar) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(EmzaKonande.class).setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(EmzaKonande.class).setCacheable(true);
        if (azEtebar != null && !azEtebar.isEmpty()) {
            criteria.add(Restrictions.ge("maxCapital", azEtebar));
            criteria2.add(Restrictions.ge("maxCapital", azEtebar));
        }
        if (taEtebar != null && !taEtebar.isEmpty()) {
            criteria.add(Restrictions.lt("maxCapital", taEtebar));
            criteria2.add(Restrictions.lt("maxCapital", taEtebar));
        }
        criteria.createCriteria("user", "u");
        criteria2.createCriteria("user", "u");
        if (fname != null && !fname.isEmpty()) {
            criteria.add(Restrictions.ilike("u.firstName", "%" + fname + "%"));
            criteria2.add(Restrictions.ilike("u.firstName", "%" + fname + "%"));
        }
        if (lname != null && !lname.isEmpty()) {
            criteria.add(Restrictions.ilike("u.lastName", "%" + lname + "%"));
            criteria2.add(Restrictions.ilike("u.lastName", "%" + lname + "%"));
        }
        if (code != null && !code.isEmpty()) {
            criteria.add(Restrictions.ilike("u.personalCode", "%" + code + "%"));
            criteria2.add(Restrictions.ilike("u.personalCode", "%" + code + "%"));
        }
        if (uStatus != null) {
            criteria.add(Restrictions.eq("u.faal", uStatus));
            criteria2.add(Restrictions.eq("u.faal", uStatus));
        }
        if (vSodurId != null) {
            criteria.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.id", vSodurId));
            criteria2.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.id", vSodurId));
        }else if (vSodurName != null && !vSodurName.isEmpty()){
            criteria.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.name", "%" + vSodurName + "%"));
            criteria2.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.name", "%" + vSodurName + "%"));
        }
        if (namayandeId != null) {
            criteria.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.id", namayandeId));
            criteria2.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.id", namayandeId));
        }else if (namayandeName != null && !namayandeName.isEmpty()){
            criteria.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.name", "%" + namayandeName + "%"));
            criteria2.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.name", "%" + namayandeName + "%"));
        }
        if (roleId != null) {
            criteria.createCriteria("u.roles", "r").add(Restrictions.eq("r.id", roleId));
            criteria2.createCriteria("u.roles", "r").add(Restrictions.eq("r.id", roleId));
        }

//
        //add paging
        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(((Long)criteria2.list().get(0)).intValue());

        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());

        List<EmzaKonande> list = criteria.list();

        paginatedList.setList(list);

        return paginatedList;
    }
}
