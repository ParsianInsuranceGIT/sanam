package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/11/12
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO extends BaseDAO {
    public static String ID = "id";

    public void addNewUser(User user) {
        super.saveOrUpdate(user);
    }

    public void editUser(User user) {
        super.update(user);
    }

    public User getUserById(Long id) {
        return (User) super.findById(User.class, id);
    }

    public List<Integer> getRolesIdOfUser(User user)
    {
        Query query =getSession().createQuery("select r.id from User u join u.roles r where u.id=:userId").setLong("userId",user.getId());
        List<Integer> list=query.list();
        return list;
    }

    public PaginatedListImpl<User> findAllUser(PaginatedListImpl paginatedList) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).setCacheable(true);

        paginatedList.setFullListSize(criteria.list().size());

        //add paging
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());

        List<User> list = criteria.list();

        paginatedList.setList(list);

        return paginatedList;
    }

    public List<User> findAll() {
        return findAll(User.class);
    }

    public void removeUser(Long id) {
        super.deleteById(User.class, id);
    }

    public List<Role> getRoleList() {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Role.class);
        return criteria.list();
    }

    public Role findRoleById(Integer roleId) {
        return (Role) findById(Role.class, roleId);
    }
    public List<User> findAllUserByRoleId(Integer roleId)
    {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u").setCacheable(true);
        if (roleId != null) {
            criteria.createCriteria("u.roles", "r").add(Restrictions.eq("r.id", roleId));
        }
        return criteria.list();
    }

    public PaginatedListImpl<User> findAllUserByFilter(PaginatedListImpl paginatedList, String fname, String lname, String code, Long vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Long namayandeId, String namayandeName) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u").setCacheable(true);
        if (fname != null && !fname.isEmpty()) {
            criteria.add(Restrictions.ilike("u.username", "%" + fname + "%"));
            criteria2.add(Restrictions.ilike("u.username", "%" + fname + "%"));
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
        }else if(vSodurName != null && !vSodurName.isEmpty()){
            criteria.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.name", "%" + vSodurName + "%"));
            criteria2.createCriteria("u.mojtamaSodoor", "m").add(Restrictions.eq("m.name", "%" + vSodurName + "%"));
        }
        if (namayandeId != null) {
            criteria.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.id", namayandeId));
            criteria2.createCriteria("u.namayandegi", "n").add(Restrictions.eq("n.id", namayandeId));
        }else if (namayandeName != null && !namayandeName.isEmpty()) {
            criteria.createCriteria("u.namayandegi", "p").add(Restrictions.ilike("p.name", "%" + namayandeName + "%"));
            criteria2.createCriteria("u.namayandegi", "p").add(Restrictions.ilike("p.name", "%" + namayandeName + "%"));
        }
        if (roleId != null) {
            criteria.createCriteria("u.roles", "r").add(Restrictions.eq("r.id", roleId));
            criteria2.createCriteria("u.roles", "r").add(Restrictions.eq("r.id", roleId));
        }
//
        //add paging
        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(Integer.parseInt(criteria2.list().get(0).toString())) ;

        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());

        List<User> list = criteria.list();

        paginatedList.setList(list);

        return paginatedList;
    }

    public PaginatedListImpl<Role> findAllRole(PaginatedListImpl<Role> paginatedList) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Role.class).setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Role.class).setCacheable(true);
        //add paging
        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize((Integer) criteria2.list().get(0));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<Role> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }

    public List<User> findByRole(List<Role> roles) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u").setCacheable(true).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("u.faal", true));
        ArrayList<Integer> roleIds = new ArrayList<Integer>();
        for(Role r : roles) {
            roleIds.add(r.getId());
        }
        criteria.createCriteria("u.roles", "r").add(Restrictions.in("r.id", roleIds));
        return criteria.list();
    }
}
