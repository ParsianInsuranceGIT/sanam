package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.KhesaratHavale;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.viewModel.KhesaratVM;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class KhesaratDao extends BaseDAO {
    public long count() {
        return (Long) getSession().createQuery("select count(*) from Khesarat").uniqueResult();
    }

    public List<Khesarat> findByVaziatParvande(Integer stateId) {
        return getSession().createCriteria(Khesarat.class).add(Restrictions.eq("state.id", stateId)).list();
    }

    public Khesarat findById(Long id) {
        return (Khesarat) findById(Khesarat.class, id);
    }

    public KhesaratHavale findHavaleById(Long id) {
        return (KhesaratHavale) findById(KhesaratHavale.class, id);
    }

    public void deleteHavaleById(Long id) {
        deleteById(KhesaratHavale.class, id);
    }

    public long countHavale(Khesarat khesarat) {
        return khesarat.getHavaleList().size();
    }

    public List<Khesarat> findAll() {
        return findAllWithOrderByDesc(Khesarat.class, "createdDate");
    }

    public PaginatedListImpl<KhesaratVM> loadKhesarats(PaginatedListImpl<KhesaratVM> pgList, KhesaratVM srchModel)
    {
        String whereClause="";
        Set<Role> theRoles = srchModel.getUserCreator().getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClause += " (usrCrtr.id=" + srchModel.getUserCreator().getId()+" or db.namayande.id="+srchModel.getUserCreator().getNamayandegi().getId() +") and ";
            }
        }
        if(srchModel!=null)
        {
            if(srchModel.getShomareParvande()!=null && !srchModel.getShomareParvande().isEmpty())
            {
                whereClause += " db.shomareParvande like '%"+srchModel.getShomareParvande()+"%' and ";
            }

            if(srchModel.getShomareBimename()!=null && !srchModel.getShomareBimename().isEmpty())
            {
                whereClause += " b.shomare like '%"+srchModel.getShomareBimename()+"%' and ";
            }

            if(srchModel.getCreatedDate()!=null && !srchModel.getCreatedDate().isEmpty())
            {
                whereClause += " db.tarikhDarkhast='"+srchModel.getCreatedDate()+"' and ";
            }

            if(srchModel.getStateId()!=null && !srchModel.getStateId().isEmpty())
            {
                whereClause += " s.id='"+srchModel.getStateId()+"' and ";
            }

            if(srchModel.getBimeGozarFirstName()!=null && !srchModel.getBimeGozarFirstName().isEmpty())
            {
                whereClause += " bgs.name like '%"+srchModel.getBimeGozarFirstName()+"%' and ";
            }


            if(srchModel.getBimeGozarLastName()!=null && !srchModel.getBimeGozarLastName().isEmpty())
            {
                whereClause += " bgs.nameKhanevadegi like '%"+srchModel.getBimeGozarLastName()+"%' and ";
            }
            if(srchModel.getBimeShodeCodeMeli()!=null && !srchModel.getBimeShodeCodeMeli().isEmpty())
            {
                whereClause += " bgs.kodeMelliShenasayi like '%"+srchModel.getBimeShodeCodeMeli()+"%' and ";
            }

            if (whereClause.length() > 0)
            {
                whereClause = "and " + whereClause;
                whereClause = whereClause.substring(0, whereClause.length() - 5);
            }
        }

        Query count = getSession().createQuery
        (
            "select count(db.id) " +
            "from DarkhastBazkharid db join db.khesaratI khI join db.state s " +
            "join db.creator usrCrtr join db.bimename b join b.pishnehadList p " +
            "join p.bimeShode bs join p.bimeGozar bg join bg.shakhs bgs join bs.shakhs bss " +
            "where p.valid=1 " + whereClause
        );

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
        (
            "select new com.bitarts.parsian.viewModel.KhesaratVM(db.id,db.shomareParvande, db.tarikhDarkhast, s.stateName, usrCrtr.firstName, usrCrtr.lastName, bss.name, bss.nameKhanevadegi, " +
            " bgs.name, bgs.nameKhanevadegi, b.shomare, khI.id,bss.kodeMelliShenasayi,bgs.kodeMelliShenasayi)" +
            "from DarkhastBazkharid db join db.khesaratI khI join db.state s " +
            "join db.creator usrCrtr join db.bimename b join b.pishnehadList p " +
            "join p.bimeShode bs join p.bimeGozar bg join bg.shakhs bgs join bs.shakhs bss " +
            "where p.valid=1 " + whereClause +
            " order by b.shomare desc"
        );

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);

        return pgList;
    }

    public List<Khesarat> searchKhesarat(City ostan, City shahr, Namayande namayande, Namayande vahedSodur, Tarh tarh, String noeGharardad, String nameBimeGozar, Integer noeKhesarat, String ellat, String azTarikh, String taTarikh) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Khesarat.class, "kh").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        boolean join = false;
        if (ostan != null) {
            crit.add(Restrictions.eq("kh.ostanMahalleHadese.cityId", ostan.getCityId()));
        }
        if (shahr != null) {
            crit.add(Restrictions.eq("kh.shahrMahalleHadese.cityId", shahr.getCityId()));
        }
        if (namayande != null && namayande.getId() != null) {
            crit.createCriteria("kh.bimename", "b").createCriteria("b.pishnehadList", "p").add(Restrictions.eq("valid",true)).createCriteria("p.namayande", "n").add(Restrictions.eq("n.id", namayande.getId()));
            join = true;
        }
        if (vahedSodur != null && vahedSodur.getId() != null) {
            if (!join) {
                crit.createCriteria("kh.bimename", "b").createCriteria("b.pishnehad", "p").createCriteria("p.namayande", "n");
                join = true;
            }
            crit.createCriteria("n.vahedSodur", "v");
            crit.add(Restrictions.eq("v.id", vahedSodur.getId()));
        }
        if ((tarh != null) && (tarh.getId()!=null && tarh.getId()>0)) {
            if (!join) {
                crit.createCriteria("kh.bimename", "b").createCriteria("b.pishnehadList", "p").add(Restrictions.eq("valid",true));
                join = true;
            }
            crit.add(Restrictions.eq("p.tarh", tarh));
        }
        if (noeGharardad != null && !noeGharardad.isEmpty()) {
            if (!join) {
                crit.createCriteria("kh.bimename", "b").createCriteria("b.pishnehadList", "p").add(Restrictions.eq("valid",true));
                join = true;
            }
            crit.add(Restrictions.eq("p.noeGharardad", noeGharardad));
        }
        if (nameBimeGozar != null && !nameBimeGozar.isEmpty()) {
            if (!join) {
                crit.createCriteria("kh.bimename", "b").createCriteria("b.pishnehadList", "p").add(Restrictions.eq("valid",true));
                join = true;
            }
            crit.createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "sh");
            crit.add(Restrictions.or(Restrictions.ilike("sh.name", nameBimeGozar), Restrictions.ilike("sh.nameKhanevadegi", nameBimeGozar)));
        }
        if (ellat != null && !ellat.isEmpty()) {
            crit.add(Restrictions.ilike("kh.ellat", ellat));
        }
        join = false;
        if (azTarikh != null && !azTarikh.isEmpty()) {
            crit.createCriteria("kh.havaleList", "h");
            join = true;
            crit.add(Restrictions.ge("h.credebit.createdDate", azTarikh));
        }
        if (taTarikh != null && !taTarikh.isEmpty()) {
            if (!join) {
                crit.createCriteria("kh.havaleList", "h");
                join = true;
            }
            crit.add(Restrictions.lt("h.credebit.createdDate", taTarikh));
        }
        List<Khesarat> list = crit.list();
        if (noeKhesarat != -1) {
            final Iterator<Khesarat> it = list.iterator();
            while (it.hasNext()) {
                final Khesarat k = it.next();
                if (noeKhesarat == 0 && !k.isOmr())
                    it.remove();
                if (noeKhesarat == 1 && !k.isHadese())
                    it.remove();
                if (noeKhesarat == 2 && !k.isAmraz())
                    it.remove();
                if (noeKhesarat == 3 && !k.isMoafiat())
                    it.remove();
            }
        }
        return list;
    }
}
