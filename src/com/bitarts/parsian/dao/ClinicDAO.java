package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Moarefiname;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/7/11
 * Time: 5:36 PM
 */
@SuppressWarnings({"JpaQlInspection"})
public class ClinicDAO extends BaseDAO {
    public static String ID = "id";

    public Clinic getClinicById(Integer id) {
        return (Clinic) this.getSession().createQuery("from Clinic as clinic where clinic.id=" + id).setCacheable(true).uniqueResult();
    }

    public List<Clinic> findAll() {
//        return (List<Clinic>) super.findAllWithOrderByAsc(Clinic.class, "clinicName");
        Criteria crit = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Clinic.class);
        crit.add(Restrictions.gt("tarikhEtmamGharardad", DateUtil.getCurrentDate()));
        crit.add(Restrictions.lt("tarikhShorouGharardad", DateUtil.getCurrentDate()));
        crit.addOrder(Order.asc("clinicName"));
        return crit.list();
    }

    public void saveClinic(Clinic clinic) {
        long result = (Long) this.getSession().createQuery("select count(*) from Clinic as clinic where clinic.clinicName='" + clinic.getClinicName() + "' and clinic.cityName='" + clinic.getCityName() + "' and clinic.address='" + clinic.getAddress() + "' and clinic.tarikhEtmamGharardad='" + clinic.getTarikhEtmamGharardad() + "' and clinic.tarikhShorouGharardad='" + clinic.getTarikhShorouGharardad() + "'").uniqueResult();
        if (result == 0) {
            this.getSession().save(clinic);
        }

    }

    public void updateClinic(Clinic clinic) {
        super.update(clinic);
    }

    public void removeClinic(Integer id) {
        Clinic c = (Clinic) super.findById(Clinic.class, id);
        Iterator i = c.getAzmayeshs().iterator();
        while (i.hasNext()) {
            super.delete(i.next());
        }
        c.setAzmayeshs(null);
        super.delete(c);
    }

    public void saveAzmayesh(Azmayesh azmayesh) {
        super.save(azmayesh);
    }

    public List<BankInfo> loadBankInfosBySerial(long serialId, Integer stateId)
    {
        Criteria criteria= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BankInfo.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("serialId",serialId));
        if(stateId!=null)
            criteria.add(Restrictions.eq("stateId",stateId));
        return criteria.list();
    }

    public void addBankInfo(BankInfo bankInfo, Bargozarandeh bargozarandeh) {
        bankInfo.setBargozarandeh(bargozarandeh);
        super.saveOrUpdate(bankInfo) ;
    }

    public void saveBankInfos(List<BankInfo> list)
    {
        super.saveOrUpdateAll(list);
    }

    public List<BankInfo> findAllBankInfos() {
        List<BankInfo> res = (List<BankInfo>) super.findAll(BankInfo.class);
        Set<BankInfo> resSet = new HashSet<BankInfo>();
        resSet.addAll(res);
        res = new ArrayList<BankInfo>();
        for (BankInfo info : resSet) {
            res.add(info);
        }
        return res;
    }

    public BankInfo findBankInfoById(Integer bankInfoId) {
        return (BankInfo) super.findById(BankInfo.class, bankInfoId);
    }

    public Azmayesh getAzmayeshById(Integer id) {
        return (Azmayesh) super.findById(Azmayesh.class, id);
    }

    public void updateAzmayesh(Azmayesh azmayesh) {
        super.update(azmayesh);
    }

    public void addAzmayeshType(AzmayeshType azmayeshType) {
        long result = (Long) this.getSession().createQuery("select count(*) from AzmayeshType as type where type.type='" + azmayeshType.getType() + "' and type.description='" + azmayeshType.getDescription() + "'").uniqueResult();
        if (result == 0) {
            super.save(azmayeshType);
        }
    }

    public List<AzmayeshType> findAllAzmayeshTypes() {
        List<AzmayeshType> res = (List<AzmayeshType>) super.findAll(AzmayeshType.class);
        Set<AzmayeshType> resSet = new HashSet<AzmayeshType>();
        resSet.addAll(res);
        res = new ArrayList<AzmayeshType>();
        for (AzmayeshType info : resSet) {
            res.add(info);
        }
        return res;
    }

    public AzmayeshType getAzmayeshTypeById(int id) {
        return (AzmayeshType) super.findById(AzmayeshType.class, id);
    }

    public void addAzmayeshName(AzmayeshName azmayeshName) {
        long result = (Long) this.getSession().createQuery("select count(*) from AzmayeshName as name where name.description='" + azmayeshName.getDescription() + "' and name.name='" + azmayeshName.getName() + "'").uniqueResult();
        if (result == 0) {
            super.save(azmayeshName);
        }
    }


    public List<AzmayeshName> findAllAzmayeshNames() {
        List<AzmayeshName> res = super.findAll(AzmayeshName.class);
        Set<AzmayeshName> resSet = new HashSet<AzmayeshName>();
        resSet.addAll(res);
        res = new ArrayList<AzmayeshName>();
        for (AzmayeshName info : resSet) {
            res.add(info);
        }
        return res;
    }

    public AzmayeshName getAzmayeshNameById(Integer id) {
        return (AzmayeshName) super.findById(AzmayeshName.class, id);
    }

    public void removeAzmayesh(Integer id) {
        super.deleteById(Azmayesh.class, id);
    }

    public void removeAzmayeshTypeById(Integer azmayeshTypeId) {
        super.deleteById(AzmayeshType.class, azmayeshTypeId);
    }

    public void removeAzmayeshNameById(Integer id) {
        super.deleteById(AzmayeshName.class, id);
    }

    public void updateAzmayeshType(AzmayeshType theAzmayeshType) {
        super.update(theAzmayeshType);
    }

    public void updateAzmayeshName(AzmayeshName theAzmayeshName) {
        super.update(theAzmayeshName);
    }

    @Transactional
    public Bargozarandeh addBargozar(Bargozarandeh bargozar) {
        super.save(bargozar);
        System.out.println("bargozarande saved!");
        return bargozar;
    }

    public void addBankInfo(BankInfo bankInfo) {
        super.saveOrUpdate(bankInfo);
    }

    @Transactional
    public void updateBargozar(Bargozarandeh bargozarandeh) {
        super.update(bargozarandeh);
    }

    public void addEmzaKonande(EmzaKonande emzaKonande) {
        super.save(emzaKonande);
    }

    public EmzaKonande findEmzaKonandeById(Integer id) {
        return (EmzaKonande) super.findById(EmzaKonande.class, id);
    }

    public void updateEmzaKonande(EmzaKonande theEmzaKonande) {
        super.update(theEmzaKonande);
    }

    public void removeEmzaKonandeById(Integer id) {
        EmzaKonande emzaKonande = (EmzaKonande) super.findById(EmzaKonande.class, id);
        emzaKonande.setUser(null);
        super.delete(emzaKonande);
    }

    public boolean doesBankInfoExist(String shomareFish, String kodeShobe, String tarikhFish) {
        if(tarikhFish.length() == 8)
            tarikhFish = ("13" + tarikhFish);
        Long result = 0l;
        if (shomareFish != null){
            if(kodeShobe!=null){
                //result = (Long) this.getSession().createQuery("select count(*) from Credebit as c where c.daryafteFish.shomareFish='" + shomareFish + "' and c.daryafteFish.tarikh='" + tarikhFish + "' and c.daryafteFish.kodeShobe='" + kodeShobe + "'").uniqueResult();
                Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").createCriteria("c.daryafteFish", "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
                criteria.add(Restrictions.like("d.shomareFish",shomareFish));
                criteria.add(Restrictions.like("d.tarikh", tarikhFish));
                criteria.add(Restrictions.like("d.kodeShobe",kodeShobe));
                List list = criteria.list();
                if (list != null && list.size() > 0)
                    return true;
            }
            else{
                //result = (Long) this.getSession().createQuery("select count(*) from Credebit as c where c.daryafteFish.shomareFish='" + shomareFish + "' and c.daryafteFish.tarikh='" + tarikhFish + "'").uniqueResult();
                Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").createCriteria("c.daryafteFish", "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
                criteria.add(Restrictions.like("d.shomareFish",shomareFish));
                criteria.add(Restrictions.like("d.tarikh", tarikhFish));
                List list = criteria.list();
                if (list != null && list.size() > 0)
                    return true;
            }
        }

        return false;

    }

    public List<Moarefiname> findMoarefiname(String azTarikhSodur, String taTarikhSodur, String kodeRahgiri, String kodeMoarefi, String nameBimeshode, String nameKhanevadegiBimeShode, String kodeMelli, int clinicSearch, int vaziatMoarefi) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria crit = session.createCriteria(Moarefiname.class);
        if (azTarikhSodur == null || azTarikhSodur.isEmpty()) azTarikhSodur = DateUtil.getCurrentDate();
        if (taTarikhSodur == null || taTarikhSodur.isEmpty()) taTarikhSodur = DateUtil.getCurrentDate();
        crit.add(Restrictions.between("tarikhSodur", azTarikhSodur, taTarikhSodur));
        boolean join = false;
        boolean join2 = false;
        if (kodeRahgiri != null && !kodeRahgiri.isEmpty()) {
            crit.createCriteria("pishnehad", "p").add(Restrictions.eq("p.id", Integer.valueOf(kodeRahgiri)));
            join = true;
        }
        if (kodeMoarefi != null && !kodeMoarefi.isEmpty()) {
            crit.add(Restrictions.eq("id", Integer.valueOf(kodeMoarefi)));
        }
        if (nameBimeshode != null && !nameBimeshode.isEmpty()) {
            if (!join) {
                crit.createCriteria("pishnehad", "p");
                join = true;
            }
            crit.createCriteria("p.bimeShode", "b").createCriteria("b.shakhs", "shakhs");
            join2 = true;
            crit.add(Restrictions.eq("shakhs.name", nameBimeshode));
        }
        if (nameKhanevadegiBimeShode != null && !nameKhanevadegiBimeShode.isEmpty()) {
            if (!join) {
                crit.createCriteria("pishnehad", "p");
                join = true;
            }
            if (!join2) {
                crit.createCriteria("p.bimeShode", "b").createCriteria("b.shakhs", "shakhs");
                join2 = true;
            }
            crit.add(Restrictions.eq("shakhs.nameKhanevadegi", nameKhanevadegiBimeShode));
        }
        if (kodeMelli == null || !kodeMelli.isEmpty()) {
            if (!join) {
                crit.createCriteria("pishnehad", "p");
                join = true;
            }
            if (!join2) {
                crit.createCriteria("p.bimeShode", "b").createCriteria("b.shakhs", "shakhs");
                join2 = true;
            }
            crit.add(Restrictions.eq("shakhs.kodeMelliShenasayi", kodeMelli));
        }
        crit.add(Restrictions.eq("clinic.id", clinicSearch));

        if (vaziatMoarefi == 0)
            crit.add(Restrictions.eq("vaziat", Moarefiname.Vaziat.DAR_JARYAN));
        else if (vaziatMoarefi == 1)
            crit.add(Restrictions.eq("vaziat", Moarefiname.Vaziat.BATEL_SHODE));
        else
            crit.add(Restrictions.or(Restrictions.eq("vaziat", Moarefiname.Vaziat.BATEL_SHODE), Restrictions.eq("vaziat", Moarefiname.Vaziat.DAR_JARYAN)));
        return crit.list();
    }

    public Moarefiname findMoarefinameById(Integer id) {
        return (Moarefiname) findById(Moarefiname.class, id);
    }

    public void updateMoarefiname(Moarefiname moarefiname) {
        saveOrUpdate(moarefiname);
    }

    public PaginatedListImpl<Clinic> findAllClinics(PaginatedListImpl<Clinic> paginatedList, String sname, String scityname, String saztarikh, String statarikh) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Clinic.class, "c").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Clinic.class, "c").setCacheable(true);

        if (sname != null && !sname.isEmpty()) {
            criteria.add(Restrictions.ilike("c.clinicName", "%" + sname + "%"));
            criteria2.add(Restrictions.ilike("c.clinicName", "%" + sname + "%"));
        }
        if (saztarikh != null && !saztarikh.isEmpty()) {
            criteria.add(Restrictions.ge("c.tarikhShorouGharardad", saztarikh));
            criteria2.add(Restrictions.ge("c.tarikhShorouGharardad", saztarikh));
        }

        if (saztarikh != null && !statarikh.isEmpty()) {
            criteria.add(Restrictions.lt("c.tarikhEtmamGharardad", statarikh));
            criteria2.add(Restrictions.lt("c.tarikhEtmamGharardad", statarikh));
        }
        if (scityname != null && !scityname.isEmpty()) {
            criteria.add(Restrictions.ilike("c.cityName", "%" + scityname + "%"));
            criteria2.add(Restrictions.ilike("c.cityName", "%" + scityname + "%"));
        }

        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(Integer.parseInt(criteria2.list().get(0).toString()));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<Clinic> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }

    public PaginatedListImpl<AzmayeshName> findAllAzmayeshNames(PaginatedListImpl<AzmayeshName> paginatedList, String sname, String snoe) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(AzmayeshName.class, "a").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(AzmayeshName.class, "a").setCacheable(true);

        if (sname != null && !sname.isEmpty()) {
            criteria.add(Restrictions.ilike("a.name", "%" + sname + "%"));
            criteria2.add(Restrictions.ilike("a.name", "%" + sname + "%"));
        }
        if (snoe != null && !snoe.isEmpty()) {
            criteria.createCriteria("a.azmayeshType", "t").add(Restrictions.ilike("t.type", "%" + snoe + "%"));
            criteria2.createCriteria("a.azmayeshType", "t").add(Restrictions.ilike("t.type", "%" + snoe + "%"));
        }

        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(Integer.parseInt(criteria2.list().get(0).toString()));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<AzmayeshName> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }

    public PaginatedListImpl<AzmayeshType> findAllAzmayeshNames(PaginatedListImpl<AzmayeshType> paginatedList, String snoe) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(AzmayeshType.class, "a").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(AzmayeshType.class, "a").setCacheable(true);

        if (snoe != null && !snoe.isEmpty()) {
            criteria.add(Restrictions.ilike("a.type", "%" + snoe + "%"));
            criteria2.add(Restrictions.ilike("a.type", "%" + snoe + "%"));
        }
        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(Integer.parseInt(criteria2.list().get(0).toString()));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<AzmayeshType> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }
}
