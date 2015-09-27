package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.MafadeGharardad;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.viewModel.GharardadSearch;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GharardadDAO extends BaseDAO {

    public List<Gharardad> search(String shomare, String aztarikh, String tatarikh, String nameNamayande, String kodeNamayande, String nameSherkat, String shomareSabt, City ostan, City shahr, User userCreator) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Gharardad.class, "g");
        if (shomare != null && !shomare.isEmpty()) {
            crit.add(Restrictions.ilike("g.shomare", "%" + shomare + "%"));
        }
        if (aztarikh != null && !aztarikh.isEmpty()) {
            crit.add(Restrictions.ge("g.createdDate", aztarikh));
        }
        if (tatarikh != null && !tatarikh.isEmpty()) {
            crit.add(Restrictions.lt("g.createdDate", tatarikh));
        }
        if ((nameNamayande != null && !nameNamayande.isEmpty()) || (kodeNamayande != null && !kodeNamayande.isEmpty())) {
            crit.createCriteria("g.pishnehadList", "p");
            if ((nameNamayande != null && !nameNamayande.isEmpty()) || (kodeNamayande != null && !kodeNamayande.isEmpty())) {
                crit.createCriteria("p.namayande", "n");
                if (nameNamayande != null && !nameNamayande.isEmpty())
                    crit.add(Restrictions.ilike("n.name", "%" + nameNamayande + "%"));
                if (kodeNamayande != null && !kodeNamayande.isEmpty())
                    crit.add(Restrictions.ilike("n.kodeNamayandeKargozar", "%" + kodeNamayande + "%"));
            }


        }
        if (nameSherkat != null && !nameSherkat.isEmpty())
            crit.add(Restrictions.ilike("g.nameSherkat", "%" + nameSherkat + "%"));
        if (shomareSabt != null && !shomareSabt.isEmpty())
            crit.add(Restrictions.ilike("g.shomareSabt", "%" + shomareSabt + "%"));
        if ((ostan != null && ostan.getCityId() != null)) {
            crit.createCriteria("g.ostan", "m");
            crit.add(Restrictions.eq("m.cityId", ostan.getCityId()));
        }
        if ((shahr != null && shahr.getCityId() != null)) {
            crit.createCriteria("g.shahr", "m2");
            crit.add(Restrictions.eq("m2.cityId", shahr.getCityId()));
        }
        if (userCreator != null && userCreator.getId() != null) {
            crit.createCriteria("g.userCreator", "u").add(Restrictions.eq("u.id", userCreator.getId()));
        }
        crit.addOrder(Order.desc("g.createdTime"));
        return crit.list();
    }

    public int countGharardad() {
        final Session session = getSession();
        List<Gharardad> list = session.createCriteria(Gharardad.class).list();
        return list.size();
    }

    public List<Gharardad> findAllByNamayande(Integer namayandeId) {
        return getSession().createCriteria(Gharardad.class).createCriteria("namayande").add(Restrictions.eq("id", namayandeId)).list();
    }

    public PaginatedListImpl<Gharardad> findAllowedGharardads(User user, PaginatedListImpl<Gharardad> paginatedList) {
        int pageNum = paginatedList.getPageNumber();
        final int objectsPerPage = paginatedList.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;

        DetachedCriteria gharardads = DetachedCriteria.forClass(Gharardad.class);
        if (userHasRole(user, "ROLE_BAZARYAB")) {
            return paginatedList;
        } else if (userHasRole(user, "ROLE_NAMAYANDE")) {
            gharardads.createAlias("namayande", "n").add(Restrictions.eq("n.id", user.getNamayandegi().getId()));
        }

        gharardads.setProjection(Projections.id());

        Criteria gharardadsNonDetachedCount = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Gharardad.class);
        gharardadsNonDetachedCount.add(Subqueries.propertyIn("id", gharardads));
        int count = Integer.parseInt(gharardadsNonDetachedCount.setProjection(Projections.rowCount()).list().get(0).toString());

        Criteria gharardadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Gharardad.class);
        gharardadsNonDetached.add(Subqueries.propertyIn("id", gharardads)).addOrder(Order.desc("createdDate"));
//        pishnehadsNonDetached.addOrder(Order.desc("createdDate")).addOrder(Order.desc("radif"));
        gharardadsNonDetached.setFirstResult(firstResult);
        gharardadsNonDetached.setMaxResults(objectsPerPage);
        List<Gharardad> res = gharardadsNonDetached.list();

        paginatedList.setFullListSize(count);
        paginatedList.setList(res);
        paginatedList.setPageNumber(pageNum + 1);
        paginatedList.setObjectsPerPage(objectsPerPage);
        return paginatedList;
    }

    private boolean userHasRole(User user, String roleName) {
        Set<Role> roles = user.getRoles();
        boolean result = false;
        for (Role role : roles) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                result = true;
            }
        }
        return result;
    }

    public PaginatedListImpl<Gharardad> search(GharardadSearch gharardadSearch, User user, PaginatedListImpl<Gharardad> paginatedList) {
        int pageNum = paginatedList.getPageNumber();
        final int objectsPerPage = paginatedList.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;
        DetachedCriteria gharardads = DetachedCriteria.forClass(Gharardad.class, "g");

        if (gharardadSearch.getShomare() != null && !gharardadSearch.getShomare().isEmpty()) {
            gharardads.add(Restrictions.ilike("g.shomare", "%" + gharardadSearch.getShomare() + "%"));
        }
        if (gharardadSearch.getAztarikh() != null && !gharardadSearch.getAztarikh().isEmpty()) {
            gharardads.add(Restrictions.ge("g.createdDate", gharardadSearch.getAztarikh()));
        }
        if (gharardadSearch.getTatarikh() != null && !gharardadSearch.getTatarikh().isEmpty()) {
            gharardads.add(Restrictions.lt("g.createdDate", gharardadSearch.getTatarikh()));
        }
        if ((gharardadSearch.getNameNamayande() != null && !gharardadSearch.getNameNamayande().isEmpty()) || (gharardadSearch.getKodeNamayande() != null && !gharardadSearch.getKodeNamayande().isEmpty())) {
                gharardads.createCriteria("p.namayande", "n");
                if (gharardadSearch.getNameNamayande() != null && !gharardadSearch.getNameNamayande().isEmpty())
                    gharardads.add(Restrictions.ilike("n.name", "%" + gharardadSearch.getNameNamayande() + "%"));
                if (gharardadSearch.getKodeNamayande() != null && !gharardadSearch.getKodeNamayande().isEmpty())
                    gharardads.add(Restrictions.ilike("n.kodeNamayandeKargozar", "%" + gharardadSearch.getKodeNamayande() + "%"));
        }
        if (gharardadSearch.getNameSherkat() != null && !gharardadSearch.getNameSherkat().isEmpty())
            gharardads.add(Restrictions.ilike("g.nameSherkat", "%" + gharardadSearch.getNameSherkat() + "%"));
        if (gharardadSearch.getShomareSabt() != null && !gharardadSearch.getShomareSabt().isEmpty())
            gharardads.add(Restrictions.ilike("g.shomareSabt", "%" + gharardadSearch.getShomareSabt() + "%"));
        if ((gharardadSearch.getOstan() != null && !gharardadSearch.getOstan().isEmpty())) {
            gharardads.createCriteria("g.ostan", "m");
            gharardads.add(Restrictions.eq("m.cityName", gharardadSearch.getOstan()));
        }
        if ((gharardadSearch.getShahr() != null && !gharardadSearch.getShahr().isEmpty())) {
            gharardads.createCriteria("g.shahr", "m2");
            gharardads.add(Restrictions.eq("m2.cityName", gharardadSearch.getShahr()));
        }
        if (gharardadSearch.getUserCreator() != null && !gharardadSearch.getUserCreator().isEmpty()) {
            gharardads.createCriteria("g.userCreator", "u").add(Restrictions.eq("u.id", gharardadSearch.getUserCreator()));
        }

        if (userHasRole(user, "ROLE_BAZARYAB")) {
            return paginatedList;
        }
        if (userHasRole(user, Constant.ROLE_NAMAYANDE)) {
            gharardads.add(Restrictions.eq("namayande.id", user.getNamayandegi().getId()));
        }
//        if (userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
//            pishnehads.add(Restrictions.isNotNull("karshenas"))
//                    .add(Restrictions.eq("karshenas.id", user.getId()));
//        }
//    private String numberOfPishnehads;
//    private String numberOfBimename;

        Criteria gharardadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Gharardad.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        gharardads.setProjection(Projections.id());
        gharardadsNonDetached.add(Subqueries.propertyIn("id", gharardads));
        int count = Integer.parseInt(gharardadsNonDetached.setProjection(Projections.rowCount()).list().get(0).toString());
        gharardadsNonDetached.setFirstResult(firstResult);
        gharardadsNonDetached.setMaxResults(objectsPerPage);
        List<Gharardad> res = gharardadsNonDetached.setProjection(null).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        paginatedList.setFullListSize(count);
        paginatedList.setList(res);
        paginatedList.setPageNumber(pageNum + 1);
        paginatedList.setObjectsPerPage(objectsPerPage);
        return paginatedList;
    }

    public void saveMafadeGharardad(MafadeGharardad mafad) {
        super.save(mafad);
    }

    public List findAllGharardad() {
        List<Gharardad> retList = new ArrayList<Gharardad>();
        Gharardad nullOne = new Gharardad();
        nullOne.setId(null);
        nullOne.setNameSherkat("");
        retList.add(nullOne);
        retList.addAll(getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Gharardad.class).list());
        return retList;
    }
}
