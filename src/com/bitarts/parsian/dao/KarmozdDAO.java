package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.karmozd.*;
import com.bitarts.parsian.viewModel.KarmozdNamayandeVM;
import com.bitarts.parsian.viewModel.KarmozdTashvighiVosuliVM;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.*;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:21 PM
 */
public class KarmozdDAO extends BaseDAO {

    public void saveOrUpdateKarmozdNamayande(KarmozdNamayande karmozdNamayande)
    {
        saveOrUpdate(karmozdNamayande);
    }

    public void saveKarmozdNamayandeList(List<KarmozdNamayande> knList)
    {
        super.saveOrUpdateAll(knList);
    }

    public PaginatedListImpl<BlackList> loadAllBlackListPaging(PaginatedListImpl<BlackList> pgList)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BlackList.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        return pgList;
    }

    public List<BlackList> loadAllBlackList(BlackList.Type type)
    {
        return getSession().createQuery("select b from BlackList b where b.type=:tp").setParameter("tp", type).list();
    }

    public BlackList findBlackListByBimenameId(Integer id , BlackList.Type type)
    {
        return (BlackList) getSession().createQuery("select bl from BlackList bl join bl.bimename b where b.id=:myId and bl.type=:tp").setInteger("myId",id).setParameter("tp",type).uniqueResult();
    }

    public void saveOrUpdateBlackList(Bimename bimename, BlackList.Type type)
    {
        BlackList blackList=new BlackList();
        blackList.setBimename(bimename);
        blackList.setType(type);
        super.saveOrUpdate(blackList);
    }

    public List findDistinctNamayandeOfTadilatByKarmozdId(Long karmozdId)
    {
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdTadilat.class,"kt").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.createCriteria("kt.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
//        criteria.setProjection(Projections.distinct(Projections.property("kt.xlCodeNamayande")));
//        criteria.add(Restrictions.eq("kt.karmozdNamayande",null));
//        return criteria.list();
        Query query = getSession().createQuery(
                                                "select distinct(kt.xlCodeNamayande) from KarmozdTadilat kt join kt.karmozd k join kt.karmozdNamayande kn " +
                                                "where k.id=:karmozdId and not exists(select 1 from KarmozdNamayande kn1 where kn1.id=kn.id ) "
                                              ).setLong("karmozdId",karmozdId);
        return query.list();
    }

    public KarmozdNamayande findKarmozdNamayandeForNamayande(Long karmozdId ,Long namayandeId)
    {
        Criteria criteria= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdNamayande.class,"kn").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("kn.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        criteria.createCriteria("kn.namayande","n").add(Restrictions.eq("n.id",namayandeId));
        return (KarmozdNamayande) criteria.uniqueResult();
    }

    public List findDistinctNamayandeOfGhestByKarmozdId(Long karmozdId)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg");
        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        criteria.createCriteria("kg.ghest", "g");
        criteria.createCriteria("g.ghestBandi","gb");
        criteria.createCriteria("gb.bimename","b");
        criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
        criteria.createCriteria("p.namayande","n");
        criteria.setProjection(Projections.distinct(Projections.property("n.id")));
        return criteria.list();
    }

    public List<KhateSanad> findGhestsByTarikhVosoli(String azTarikhVosoli, String taTarikhVosoli)
    {
        Query query = getSession().createQuery
        (
                "select kh from KhateSanad kh join kh.bedehiCredebit c  join kh.etebarCredebit et join c.ghest gh join gh.ghestBandi gb join gb.bimename b  " +
                "join b.pishnehadList p join p.namayande n join p.estelam e join kh.sanad s left join kh.karmozdGhestList kg " +
                "where kh.converted is null and c.credebitType=:GHEST and (et.credebitType!=:ELHAGHIE_BARGASHTI_ETEBAR and et.credebitType!=:ETEBAR_EBTAL) " +
                "and gb.saleBimei<=4 and b.converted is null and b.readyToShow='yes' and ( b.karmozdBimename is null or b.karmozdBimename>=0l ) and " +
                "p.valid=true and e.nahve_pardakht_hagh_bime!='yekja' and " +
                "( (not exists (select 1 from KarmozdGhest subKg where subKg.khateSanad=kh) and (s.createdDate<=:taTarikh and s.createdDate>=:azTarikh)) or (kg.wasPaid=false and kg.type=:DAR_ENTEZAR_SARRESID) )"
        ).setParameter("GHEST", Credebit.CredebitType.GHEST)
         .setParameter("ELHAGHIE_BARGASHTI_ETEBAR", Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)
         .setParameter("ETEBAR_EBTAL", Credebit.CredebitType.ETEBAR_EBTAL)
         .setParameter("DAR_ENTEZAR_SARRESID", KarmozdGhest.Type.DAR_ENTEZAR_SARRESID)
         .setString("taTarikh", taTarikhVosoli)
         .setString("azTarikh", azTarikhVosoli);
        return query.list();

//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "kh").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.add(Restrictions.isNull("kh.converted"));
//        criteria.createCriteria("kh.bedehiCredebit", "c").add(Restrictions.eq("c.credebitType", Credebit.CredebitType.GHEST));
//        criteria.createCriteria("kh.etebarCredebit","et");
//        criteria.add(Restrictions.ne("et.credebitType", Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR));
//        criteria.add(Restrictions.ne("et.credebitType", Credebit.CredebitType.ETEBAR_EBTAL));
//        criteria.createCriteria("c.ghest", "gh");
//        criteria.createCriteria("gh.ghestBandi","gb").add(Restrictions.le("gb.saleBimei", "4"));
//        criteria.createCriteria("gb.bimename", "b");
//        criteria.add(Restrictions.isNull("b.converted")).add(Restrictions.eq("b.readyToShow", "yes"));
//        criteria.add(Restrictions.or(Restrictions.isNull("b.karmozdBimename"),Restrictions.ge("b.karmozdBimename",0L)));
//        criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
//        criteria.createCriteria("p.namayande", "n");
//        criteria.createCriteria("p.estelam","e").add(Restrictions.ne("e.nahve_pardakht_hagh_bime", "yekja"));
//        criteria.createCriteria("kh.sanad", "s");
//        criteria.createCriteria("kh.karmozdGhestList", "kg", CriteriaSpecification.LEFT_JOIN);
//        criteria.add
//        (
//            Restrictions.or
//            (
//                Restrictions.and
//                (
//                    Restrictions.isEmpty("kh.karmozdGhestList"),
//                    Restrictions.and
//                    (
//                        Restrictions.le("s.createdDate", taTarikhVosoli),
//                        Restrictions.ge("s.createdDate", "1393/10/28")
//                    )
//                ),
//                Restrictions.and
//                (
//                    Restrictions.eq("kg.wasPaid", false),
//                    Restrictions.eq("kg.type", KarmozdGhest.Type.DAR_ENTEZAR_SARRESID)
//                )
//            )
//        );
    }


    public List<KarmozdGhest> findKarmozdGhestDoubleTaghirat(String azTarikh, String taTarikh)
    {
        Query query = getSession().createQuery
                (
                    "select kg1 from KarmozdGhest kg1 where exists (" +
                    "select kg.id from KarmozdGhest kg join kg.ghest gh join gh.ghestBandi gb join gb.bimename b join b.state s join b.elhaghiyeha el join el.state els " +
                    "join kg.karmozdTaghirat kgTgr join kgTgr.karmozd krmzdTgr " +
                    "where s.id=:bimenameState and els.id=:elhaghieState and el.elhaghiyeType=:elType and el.tarikhAsar=b.tarikhShorou and el.createdDate<=:ta " +
                    "and kg.karmozdNamayande is not null and el.createdDate > krmzdTgr.taTarikhVosoli and (kg.type=:kgTypeTlghi or kg.type=:kgTypeCdMvgt or kg.type=:kgTypeAdi) " +
                    "and kg1.id=kg.id )"
                ).setInteger("bimenameState", Constant.BIMENAME_INITIAL_STATE)
                .setInteger("elhaghieState", Constant.ELHAGHIYE_FINAL_STATE)
                .setString("ta", taTarikh)
                .setParameter("elType", Elhaghiye.ElhaghiyeType.TAGHYIRAT)
                .setParameter("kgTypeTlghi", KarmozdGhest.Type.TALIGHI)
                .setParameter("kgTypeCdMvgt", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)
                .setParameter("kgTypeAdi", KarmozdGhest.Type.ADI);
        return (List<KarmozdGhest>)query.list();
    }

    public List<KarmozdGhest> findKarmozdGhestTaghirat(String azTarikh, String taTarikh)
    {
        Query query = getSession().createQuery
                      (
                          "select kg1 from KarmozdGhest kg1 where exists ("+
                          "select kg.id from KarmozdGhest kg join kg.ghest gh join gh.ghestBandi gb join gb.bimename b join b.state s join b.elhaghiyeha el join el.state els " +
                          "where s.id=:bimenameState and els.id=:elhaghieState and el.elhaghiyeType=:elType and el.tarikhAsar=b.tarikhShorou and el.createdDate<=:ta " +// and el.createdDate >=:az " +
                          "and kg.karmozdNamayande is not null and kg.karmozdTaghirat is null and (kg.type=:kgTypeTlghi or kg.type=:kgTypeCdMvgt or kg.type=:kgTypeAdi) "+ //" or kg.type=:tadil) "
                          "and kg1.id=kg.id group by kg.id )"
                      ).setInteger("bimenameState", Constant.BIMENAME_INITIAL_STATE)
                       .setInteger("elhaghieState", Constant.ELHAGHIYE_FINAL_STATE)
//                       .setString("az",azTarikh)
                       .setString("ta",taTarikh)
                       .setParameter("elType",Elhaghiye.ElhaghiyeType.TAGHYIRAT)
                       .setParameter("kgTypeTlghi", KarmozdGhest.Type.TALIGHI)
                       .setParameter("kgTypeCdMvgt", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)
                       .setParameter("kgTypeAdi", KarmozdGhest.Type.ADI);
        return query.list();
    }

    public List findKarmozdGhestsMovaghat(String azTarikh, String taTarikh)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class, "kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("kg.type", KarmozdGhest.Type.CODE_MOVAGHAT));
        criteria.createCriteria("kg.ghest","gh");
        criteria.createCriteria("gh.ghestBandi", "gb");
        criteria.createCriteria("gb.bimename","b");
        criteria.createCriteria("b.pishnehadList","p");
        criteria.createCriteria("p.namayandePoshtiban","np");
        criteria.add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.isNotNull("np.id"));

        criteria.add(Restrictions.eq("b.state.id", Constant.BIMENAME_INITIAL_STATE));
        criteria.createCriteria("b.elhaghiyeha","el").add(Restrictions.eq("el.elhaghiyeType",Elhaghiye.ElhaghiyeType.TAGHIRKOD));
        criteria.add(Restrictions.le("el.createdDate", taTarikh));
//        criteria.add(Restrictions.ge("el.createdDate", azTarikh));
        criteria.createCriteria("kg.karmozdCodeMovaghat", "kgMvght",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.isNull("kgMvght.id"));
//        criteria.add(Restrictions.isNull("kg.karmozdCodeMovaghat"));
        return criteria.list();
    }

    public List<KarmozdGhest> findKarmozdGhestsChangeNamayande(String azTarikh, String taTarikh)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class, "kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.CODE_MOVAGHAT));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.SENIOR));
        //todo:tashvighi hanuz taklifesh malum nist. . .
        criteria.createCriteria("kg.ghest","gh");
        criteria.createCriteria("gh.ghestBandi", "gb");
        criteria.createCriteria("gb.bimename","b");
        criteria.createCriteria("b.pishnehadList","p");
        criteria.createCriteria("p.previousNamayande","pn");
        criteria.add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.isNotNull("pn.id"));

        criteria.add(Restrictions.eq("b.state.id", Constant.BIMENAME_INITIAL_STATE));
        criteria.createCriteria("b.elhaghiyeha","el").add(Restrictions.eq("el.elhaghiyeType",Elhaghiye.ElhaghiyeType.TAGHIRKOD));
        criteria.add(Restrictions.le("el.createdDate", taTarikh));
//        criteria.add(Restrictions.ge("el.createdDate", azTarikh));
        criteria.createCriteria("kg.karmozdTaghirCodeDaem", "kgDaem",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.isNull("kgDaem.id"));
//        criteria.add(Restrictions.isNull("kg.karmozdCodeMovaghat"));
        return criteria.list();
    }

    public List findKarmozdGhestsBazkharidOrEbtal(String azTarikh, String taTarikh)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class, "kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("kg.ghest","gh");
        criteria.createCriteria("gh.ghestBandi","gb").add(Restrictions.between("gb.saleBimei", "0", "4"));
        criteria.createCriteria("gb.bimename","b").add
        (
            Restrictions.or
                    (
                            Restrictions.eq("b.state.id", 540),
                            Restrictions.eq("b.state.id", 550)
                    )
        );
        criteria.createCriteria("b.elhaghiyeha", "el");
        criteria.add
                (
                        Restrictions.or
                                (
                                        Restrictions.eq("el.elhaghiyeType", Elhaghiye.ElhaghiyeType.BAZKHARID),
                                        Restrictions.eq("el.elhaghiyeType", Elhaghiye.ElhaghiyeType.EBTAL)
                                )
                );
        criteria.add(Restrictions.le("el.createdDate",taTarikh));
//        criteria.add(Restrictions.ge("el.createdDate",azTarikh));

        criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.KARMOZD_BARGASHTI));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.DAR_ENTEZAR_SARRESID));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.TALIGHI));
        criteria.add
        (
            Restrictions.and
            (
                Restrictions.and
                (
                    Restrictions.isNull("p.options"),
                    Restrictions.isNull("p.namayandePoshtiban")
                ),
                Restrictions.or
                (
                    Restrictions.or(Restrictions.eq("kg.type", KarmozdGhest.Type.ADI), Restrictions.eq("kg.type", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)),
                    Restrictions.eq("kg.type", KarmozdGhest.Type.TADILI)
                )
            )
        );
        criteria.add(Restrictions.ne("kg.wasPaid", false));
        criteria.add(Restrictions.isNull("kg.karmozdBargashti"));
        return criteria.list();
    }

    public List<Ghest> findGhestsYekjaForVosuliOrPoshesh(String azTarikhVosoli,String taTarikhVosoli,Karmozd.Type type)
    {
        String whereClause="";
        if (type.equals(Karmozd.Type.Karmozd_Vosuli))
        {
            whereClause += " and g.karmozdVosuli>0l and not exists (select k1.id from KarmozdGhest k1 where k1.ghest.id=g.id and k1.type='" + KarmozdGhest.Type.VOSULI +"')";
        }

        if (type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
        {
            whereClause += " and g.karmozdPoosheshEzafi>0l and not exists (select k1.id from KarmozdGhest k1 where k1.ghest.id=g.id and k1.type='" + KarmozdGhest.Type.POOSHESH_EZAFI + "')";
        }


        Query query = getSession().createQuery
        (
            "select distinct g from Ghest g join g.ghestBandi gb join gb.bimename b join b.pishnehadList p " +
            "join p.estelam e join g.credebitList c join c.khateSanadsBedehi khb join g.karmozdSet k " +
            "where p.valid = true and b.readyToShow='yes' and b.converted is null "+ //and b.shomare='2210/250010/92/002412' " +
            "and e.nahve_pardakht_hagh_bime='yekja' and khb.converted is null and b.tarikhSodour>=:fromDate  and b.tarikhSodour<=:toDate " + whereClause
        ).setString("fromDate",azTarikhVosoli).setString("toDate",taTarikhVosoli);

//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class,"g").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.createCriteria("g.ghestBandi","gb");
//        criteria.createCriteria("gb.bimename","b");criteria.add(Restrictions.isNull("b.converted")).add(Restrictions.eq("b.readyToShow", "yes"));
//        criteria.add(Restrictions.ge("b.tarikhSodour", azTarikhVosoli));
//        criteria.add(Restrictions.le("b.tarikhSodour", taTarikhVosoli));
//        criteria.add(Restrictions.eq("b.shomare", "2210/250010/92/002412"));
//        criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
//        criteria.createCriteria("p.estelam","e").add(Restrictions.eq("e.nahve_pardakht_hagh_bime","yekja"));
//        criteria.createCriteria("g.credebitList","c")
//                .createCriteria("c.khateSanadsBedehi","khb").add(Restrictions.isNull("khb.converted"));
//
//        criteria.createCriteria("g.karmozdSet","k",CriteriaSpecification.LEFT_JOIN);
//        if(type.equals(Karmozd.Type.Karmozd_Vosuli))
//        {
//            criteria.add(Restrictions.ge("g.karmozdVosuli",0l));
////            criteria.add(Restrictions.ne("k.type", KarmozdGhest.Type.VOSULI));
//            criteria.add(Restrictions.not(Restrictions.eq("k.type",KarmozdGhest.Type.VOSULI)));
//            criteria.add(Restrictions.not(Restrictions.("k.type", KarmozdGhest.Type.VOSULI)));
//
//        }
////            criteria.add(Restrictions.or(Restrictions.isNull("k.type"),Restrictions.ne("k.type",KarmozdGhest.Type.VOSULI)));
//        if(type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
//        {
//            criteria.add(Restrictions.ge("g.karmozdPoosheshEzafi", 0l));
//            criteria.add(Restrictions.ne("k.type", KarmozdGhest.Type.POOSHESH_EZAFI));
//        }
//            criteria.add(Restrictions.or(Restrictions.isNull("k.type"),Restrictions.ne("k.type",KarmozdGhest.Type.POOSHESH_EZAFI)));
//        return criteria.list();
        return query.list();
    }


    public List<KhateSanad> findKhateSanadGYekjaForVosuliOrPoshesh(String azTarikhVosoli, String taTarikhVosoli, Karmozd.Type type)
    {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "kh").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.isNull("kh.converted"));
        criteria.createCriteria("kh.sanad", "s").add(Restrictions.le("s.createdDate", taTarikhVosoli)).add(Restrictions.ge("s.createdDate", azTarikhVosoli));
        criteria.createCriteria("kh.bedehiCredebit","cb").add(Restrictions.eq("cb.credebitType",Credebit.CredebitType.GHEST));
        criteria.createCriteria("cb.bimename","b").add(Restrictions.isNull("b.converted")).add(Restrictions.eq("b.readyToShow", "yes")).add(Restrictions.gt("b.karmozdBimename",0l));
        criteria.createCriteria("kh.karmozdGhestList", "k", CriteriaSpecification.LEFT_JOIN);
        if (type.equals(Karmozd.Type.Karmozd_Vosuli))
            criteria.add(Restrictions.ne("k.type", KarmozdGhest.Type.VOSULI));
//            criteria.add(Restrictions.or(Restrictions.isNull("k.type"),Restrictions.ne("k.type",KarmozdGhest.Type.VOSULI)));
        if (type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
            criteria.add(Restrictions.ne("k.type", KarmozdGhest.Type.POOSHESH_EZAFI));
//            criteria.add(Restrictions.or(Restrictions.isNull("k.type"),Restrictions.ne("k.type",KarmozdGhest.Type.POOSHESH_EZAFI)));
        return criteria.list();
    }

    public List findGhestsYekja(String azTarikhVosoli,String taTarikhVosoli)
    {
        Criteria criteria =getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class,"gh").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("gh.ghestBandi","gb");
        criteria.add(Restrictions.eq("gb.saleBimei","0"));
        criteria.createCriteria("gb.bimename","b").add(Restrictions.isNull("b.converted")).add(Restrictions.eq("b.readyToShow","yes"));
        criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
        criteria.createCriteria("p.estelam","e").add(Restrictions.eq("e.nahve_pardakht_hagh_bime","yekja"));
//        criteria.createCriteria("gb.ghest","g");
//        criteria.createCriteria("g.credebit","c");
        return criteria.list();
    }

    public void saveKarmozdGhest(KarmozdGhest kg)
    {
        saveOrUpdate(kg);
    }

    public void saveKarmozdGhestList(List<KarmozdGhest> kgList)
    {
//        super.saveOrUpdateAll(kgList);

        if (kgList == null || kgList.isEmpty()) return;

        for(KarmozdGhest kg : kgList)
            getHibernateTemplate().getSessionFactory().getCurrentSession().saveOrUpdate(kg);

    }


    public List findKarmozdGhestByKarmozdNamayandeId(Long karmozdNamayandeId)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg");
        criteria.createCriteria("kg.karmozdNamayande","kn").add(Restrictions.eq("kn.id",karmozdNamayandeId));
        criteria.createCriteria("kg.ghest","g")
                .createCriteria("g.ghestBandi","gb")
                .createCriteria("gb.bimename","b")
                .createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.DAR_ENTEZAR_SARRESID));
        criteria.add(Restrictions.ne("kg.type", KarmozdGhest.Type.KARMOZD_BARGASHTI));
        criteria.add(Restrictions.isNull("p.options"));

        return criteria.list();
    }

    public List findKarmozdGhestByKarmozdIdForNamayande(Long karmozdId,Integer namayandeId)
    {
//        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
//        criteria.createCriteria("kg.ghest","g")
//                .createCriteria("g.ghestBandi","gb")
//                .createCriteria("gb.bimename","b")
//                .createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true))
//                .createCriteria("p.namayande","n").add(Restrictions.eq("n.id",namayandeId));
//        return criteria.list();

        return (List<KarmozdGhest>) getSession().createQuery("select kg from KarmozdGhest kg join kg.karmozd k join kg.ghest g join g.ghestBandi gb join gb.bimename b join b.pishnehadList p join p.namayande n " +
                                 "where k.id=:kId and p.valid=true and n.id=:nId").setLong("kId",karmozdId).setInteger("nId",namayandeId).list();
    }

    public List findKarmozdGhestByKarmozdId(Long karmozdId)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        return criteria.list();
    }

    public PaginatedListImpl<KarmozdGhest> searchKarmozdGhest(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId, String shomareMoshtari, String shenaseEtebar,String kodeNamayande,String shomareBimename)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg");
        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        criteria.createCriteria("kg.khateSanad","ks");
        criteria.createCriteria("kg.ghest","g")
                .createCriteria("g.ghestBandi","gb")
                .createCriteria("gb.bimename","b");

        if(shenaseEtebar!=null && !shenaseEtebar.isEmpty())
        {
            criteria.createCriteria("ks.etebarCredebit","ec").add(Restrictions.like("ec.shenaseCredebit",shenaseEtebar,MatchMode.ANYWHERE));
        }

        if(shomareMoshtari!=null && !shomareMoshtari.isEmpty())
        {
            criteria.createCriteria("ks.bedehiCredebit","bc").add(Restrictions.like("bc.shomareMoshtari",shomareMoshtari,MatchMode.ANYWHERE));
        }

        if(shomareBimename!=null && !shomareBimename.isEmpty())
        {
            criteria.add(Restrictions.like("b.shomare",shomareBimename,MatchMode.ANYWHERE));
        }

        if(kodeNamayande!=null && !kodeNamayande.isEmpty())
        {
            criteria.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true))
                    .createCriteria("p.namayande","n").add(Restrictions.like("n.kodeNamayandeKargozar",kodeNamayande,MatchMode.ANYWHERE));
        }

        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
//        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
    }

    public List<KarmozdGhest> findKarmozdGhestByBimenameId(Integer id, KarmozdNamayande.State state, Karmozd.Type type)
    {
        Criteria criteria= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (type != null)
        {
            criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.type", type));
        }
        criteria.createCriteria("kg.karmozdNamayande","kn").add(Restrictions.eq("kn.state",state));
        criteria.createCriteria("kg.ghest","g")
                .createCriteria("g.ghestBandi","gb")
                .createCriteria("gb.bimename","b").add(Restrictions.eq("b.id",id));
        return criteria.list();
    }

    public void save(Karmozd k) {
        if (k.getCreatedDate() == null || k.getCreatedDate().isEmpty())
            k.setCreatedDate(DateUtil.getCurrentDate());
        saveOrUpdate(k);
    }

    public boolean isThereNotFinal()
    {
//        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Karmozd.class);
//        criteria.add(Restrictions.isNull("status"));
        Query query = getSession().createQuery("select k from Karmozd k where k.status is null");

        if(query.list().size()>0)
            return true;
        return false;
    }

    public PaginatedListImpl<Ghest> findGhestsByKarmozdId(PaginatedListImpl<Ghest> pgList,Long karmozdId)
    {
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(KarmozdGhest.class,"kg")
                .createCriteria("kg.karmozd","k")
                .add(Restrictions.eq("k.id",karmozdId))
                .setProjection(Projections.distinct(Projections.property("kg.ghest")));

        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "gh");
                 criteria.add(Property.forName("gh.id").in(detachedCriteria));
                pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        return pgList;
    }

    public KarmozdNamayande findKarmozdNamayandeById(Long id)
    {
        return (KarmozdNamayande) super.findById(KarmozdNamayande.class,id);
    }

    public String getSerialKarmozd(String str)
    {
        return (String) getSession().createQuery("select max(k.serial) from Karmozd k where k.serial like '" + str +"%'").uniqueResult();
    }

    public PaginatedListImpl<KarmozdNamayandeVM> findKarmozdNamayandeVMByKarmozdId(PaginatedListImpl<KarmozdNamayandeVM> pgList,KarmozdNamayandeVM knSVM)
    {
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();

        String whereClause="";

        if(knSVM.getKarmozdId()==null) return null;

        if (knSVM.getState() != null && !knSVM.getState().toString().isEmpty())
        {
            whereClause += " and kn.state ='" + knSVM.getState() + "' ";
        }

        if(knSVM.getNamayandeId()!=null)
        {
            whereClause += " and kn.namayande.id=" + knSVM.getNamayandeId();
        }

        Query count = getSession().createQuery
                (
                        "select count(kn.id) " +
                                "from KarmozdNamayande kn join kn.karmozd k " +
                                "where k.id=:krmzdId " + whereClause
                ).setLong("krmzdId", knSVM.getKarmozdId());

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
        (
            "select new com.bitarts.parsian.viewModel.KarmozdNamayandeVM(kn.id,k.id,kn.state,kn.status,kn.denominator,kn.numerator,kn.namayande.kodeNamayandeKargozar,kn.namayande.name,kn.tempNumerator,kn.tempDenominator,kn.description) " +
            "from KarmozdNamayande kn join kn.karmozd k " +
            "where k.id=:krmzdId "+ whereClause
        ).setLong("krmzdId",knSVM.getKarmozdId());
        query.setFirstResult(maxResults);
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        pgList.setList(query.list());
        return pgList;
    }

    public PaginatedListImpl<KarmozdNamayande> findKarmozdNamayandeByKarmozdId(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId, String state)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdNamayande.class, "kn");
        if(state!=null && !state.isEmpty())
        {
            criteria.add(Restrictions.eq("kn.state", KarmozdNamayande.State.valueOf(state)));
        }
        criteria.createCriteria("kn.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        pgList.setFullListSize(criteria.list().size());
        int maxResults=pgList.getObjectsPerPage()*pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        return pgList;
    }

    public PaginatedListImpl<KarmozdNamayande> searchKarmozdNamayanade(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId, String state,String namayandeName,Long namayandeId)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdNamayande.class,"kn");
        if(state!=null && !state.isEmpty())
        {
            criteria.add(Restrictions.eq("kn.state", KarmozdNamayande.State.valueOf(state)));
        }

        if(karmozdId!=null)
        {
            criteria.createCriteria("kn.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        }

        if(namayandeName!=null && !namayandeName.isEmpty())
        {
            criteria.createCriteria("kn.namayande","n").add(Restrictions.eq("n.id",namayandeId));
        }


        pgList.setFullListSize(criteria.list().size());
        int maxResults=pgList.getObjectsPerPage()*pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        return pgList;
    }



    public PaginatedListImpl<Karmozd> findAllKarmozds(PaginatedListImpl<Karmozd> pgList,User user)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Karmozd.class);
        Role karmozdNamayandeRole = new Role();
        karmozdNamayandeRole.setRoleName(Constant.ROLE_KARMOZD_NAMAYANDE);
        if (user.getRoles().contains(karmozdNamayandeRole))
        {
            criteria.add(Restrictions.eq("viewForNamayande","yes"));
        }
        criteria.addOrder(Order.desc("serial"));
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        return pgList;
    }

    public Karmozd findById(Long id) {
        return (Karmozd) findById(Karmozd.class, id);
    }

    public List<Karmozd> findByNamayande(Integer namayandeId) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Karmozd.class).add(Restrictions.eq("namayande.id", namayandeId)).list();
    }


    public KarmozdYekja findByIdYekja(Long id) {
        return (KarmozdYekja) findById(KarmozdYekja.class, id);
    }

    public List<KarmozdYekja> findByNamayandeYekja(Integer namayandeId) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdYekja.class).add(Restrictions.eq("namayande.id", namayandeId)).list();
    }

    public void saveYekja(KarmozdYekja k) {
        if (k.getCreatedDate() == null || k.getCreatedDate().isEmpty())
            k.setCreatedDate(DateUtil.getCurrentDate());
        saveOrUpdate(k);
    }

    public KarmozdYekja findByBimenameYekja(Integer bimenameId) {
        return (KarmozdYekja) getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdYekja.class).add(Restrictions.eq("bimename.id", bimenameId)).uniqueResult();
    }

    public List<KarmozdTadilat> findTadilatWithDate(Karmozd.Type type,String fromDate,String toDate)
    {
        List<KarmozdGhest.Type> typeList=new ArrayList<KarmozdGhest.Type>();
        if(type.equals(Karmozd.Type.Karmozd_Pardakhti))
        {
            typeList.add(KarmozdGhest.Type.ADI);
            typeList.add(KarmozdGhest.Type.TALIGHI);
            typeList.add(KarmozdGhest.Type.TADILI);
            typeList.add(KarmozdGhest.Type.TAGHIRAT);
            typeList.add(KarmozdGhest.Type.KARMOZD_BARGASHTI);
            typeList.add(KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI);
        }
        else if (type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
        {
            typeList.add(KarmozdGhest.Type.POOSHESH_EZAFI);
        }
        else if (type.equals(Karmozd.Type.Karmozd_Vosuli))
        {
            typeList.add(KarmozdGhest.Type.VOSULI);
        }
        else if (type.equals(Karmozd.Type.Karmozd_Tashvighi_Vosuli))
        {
            typeList.add(KarmozdGhest.Type.KARMOZD_TASHVIGHI_VOSULI);
        }
        else if (type.equals(Karmozd.Type.Karmozd_Seniors))
        {
             typeList.add(KarmozdGhest.Type.SENIOR);
        }

        Query query = getSession().createQuery
        (
            "select t from KarmozdTadilat t left join t.karmozd k join t.karmozdGhest kg where kg.type in (:typeList) and t.createdDate<=:toDate and k.id is null"
        ) .setParameterList("typeList",typeList).setParameter("toDate",toDate);
        return query.list();
    }

    public PaginatedListImpl<KarmozdTadilat> findKarmozedTadilat(PaginatedListImpl<KarmozdTadilat> pgList,Long karmozdId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdTadilat.class)
                .createCriteria("karmozd")
                .add(Restrictions.eq("id", karmozdId)).setCacheable(true);
        pgList.setFullListSize(criteria.list().size());
        int maxResults=pgList.getObjectsPerPage()*pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
    }

    public PaginatedListImpl<KarmozdGhest> findKarmozdGhestByKarmozdId(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId, Namayande n)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdGhest.class,"kg");
        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
        if(n!=null)
            criteria.createCriteria("kg.karmozdNamayande","kn").add(Restrictions.eq("kn.namayande.id",n.getId()));
        criteria.createCriteria("kg.ghest","g").createCriteria("g.ghestBandi","gb").createCriteria("gb.bimename","b").add(Restrictions.eq("b.readyToShow","yes"));
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
    }

//    public PaginatedListImpl<AllPayment> findKarmozdGhestByKarmozdId(PaginatedListImpl<AllPayment> pgList, Karmozd karmozd, Namayande n)
//    {
//        String totalWageField="karmozdReal";
//        if(karmozd.getType().equals(Karmozd.Type.Karmozd_Vosuli))
//            totalWageField="karmozdVosuli";
//        if(karmozd.getType().equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
//            totalWageField="karmozdPoosheshEzafi";
//
//        String totalPaidWageField="karmozdPaid";//todo: badan majmue karmozdpardakhtie vosuli va poosheshhaye ezafe ham ezafe she
//        Query query = getSession().createQuery
//        (
//                "select sum() from b."
//            "select new com.bitarts.parsian.viewModel.AllPayment(g." + totalWageField + ",g."+ totalPaidWageField +",kg.karmozdAmount,g.credebitList.amountLong," +
//            "g.khateSanad.amount, p.estelam.sarmaye_paye_fot, ) " +
//            "from KarmozdGhest kg join kg.ghest g join g.ghestBandi gb join gb.bimename join b join b.pishnehadList p "
//        )
//        criteria.createCriteria("kg.karmozd","k").add(Restrictions.eq("k.id",karmozdId));
//        if(n!=null)
//            criteria.createCriteria("kg.karmozdNamayande","kn").add(Restrictions.eq("kn.namayande.id",n.getId()));
//        pgList.setFullListSize(criteria.list().size());
//        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
//        criteria.setFirstResult(maxResults);
//        criteria.setMaxResults(pgList.getObjectsPerPage());
//        pgList.setList(criteria.list());
//        pgList.setPageNumber(pgList.getPageNumber()+1);
//        return pgList;
//    }

    public PaginatedListImpl findAllPaymentByKarmozdId(PaginatedListImpl pgList,long karmozdId ,Namayande n,Karmozd.Type type)
    {
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        String karmozdWhereClause="";
        if(n!=null)
        {
            karmozdWhereClause = " and n.id=" + n.getId();
        }


        Query count = getSession().createQuery
        (
            "select count(kg.id) " +
            "from KarmozdGhest kg left join kg.ghest g left join g.credebitList c left join kg.khateSanad khs left join g.ghestBandi gtb left join gtb.bimename b left join b.pishnehadList p left join p.estelam e " +
            "join kg.karmozdNamayande kn left join kn.namayande n left join p.bimeGozar bg left join bg.shakhs sh join kg.karmozd k " +
            "where n.namayandeType is not null and ((p.valid=true and b.readyToShow='yes')or g.id is null) and k.id=:karId and kg.blackList is null and ( (kg.type!=:CODE_MOVAGHAT and (kg.type = :TALIGHI or kg.type = :ADI or kg.type IS NULL ) ) " +
            "or kg.type = :TAGHIR_CODE_DAEM or kg.type = :PARDAKHTE_CODE_MOVAGHATI or kg.type = :KARMOZD_BARGASHTI or kg.type = :VOSULI or kg.type = :POOSHESH_EZAFI or kg.type = :SENIOR or kg.type = :TADILI or kg.type = :TAGHIRAT or kg.type = :KG_TSHVIGHI_VSLI) and kg.karmozdAmount != 0 " + karmozdWhereClause
        ).setLong("karId", karmozdId)
                .setParameter("CODE_MOVAGHAT", KarmozdGhest.Type.CODE_MOVAGHAT)
                .setParameter("TALIGHI", KarmozdGhest.Type.TALIGHI)
                .setParameter("ADI", KarmozdGhest.Type.ADI)
                .setParameter("PARDAKHTE_CODE_MOVAGHATI", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)
                .setParameter("TAGHIR_CODE_DAEM", KarmozdGhest.Type.TAGHIR_CODE_DAEM)
                .setParameter("KARMOZD_BARGASHTI", KarmozdGhest.Type.KARMOZD_BARGASHTI)
                .setParameter("VOSULI", KarmozdGhest.Type.VOSULI)
                .setParameter("POOSHESH_EZAFI", KarmozdGhest.Type.POOSHESH_EZAFI)
                .setParameter("SENIOR", KarmozdGhest.Type.SENIOR)
                .setParameter("TADILI", KarmozdGhest.Type.TADILI)
                .setParameter("TAGHIRAT", KarmozdGhest.Type.TAGHIRAT)
                .setParameter("KG_TSHVIGHI_VSLI", KarmozdGhest.Type.KARMOZD_TASHVIGHI_VOSULI);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        String karmozdReal="g.karmozdReal";
        if(type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
            karmozdReal = "g.karmozdPoosheshEzafi";
        if(type.equals(Karmozd.Type.Karmozd_Vosuli))
            karmozdReal="g.karmozdVosuli";

        Query query= getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.AllPayment(''," + karmozdReal + ", g.karmozdPaid,kg.karmozdAmount, c.amount_long, khs.amount, c.shomareMoshtari, " +
                                "gtb.saleBimei, g.sarresidDate, b.shomare, e.sarmaye_paye_fot_long, e.nahve_pardakht_hagh_bime, n.kodeNamayandeKargozar, n.name, n.namayandeType, kg.type, " +
                                "kg.tarefe,'', kg.description, sh.name,sh.nameKhanevadegi) " +
                                "from KarmozdGhest kg left join kg.ghest g left join g.credebitList c left join kg.khateSanad khs left join g.ghestBandi gtb left join gtb.bimename b left join b.pishnehadList p left join p.estelam e " +
                                "join kg.karmozdNamayande kn join kn.namayande n left join p.bimeGozar bg left join bg.shakhs sh join kg.karmozd k " +
                                "where n.namayandeType is not null and ((p.valid=true and b.readyToShow='yes')or g.id is null) and k.id=:karId and kg.blackList is null and ( (kg.type!=:CODE_MOVAGHAT and (kg.type = :TALIGHI or kg.type = :ADI or kg.type IS NULL ) ) " +
                                "or kg.type = :TAGHIR_CODE_DAEM or kg.type = :PARDAKHTE_CODE_MOVAGHATI or kg.type = :KARMOZD_BARGASHTI or kg.type = :VOSULI or kg.type = :POOSHESH_EZAFI or kg.type = :SENIOR or kg.type = :TADILI or kg.type = :TAGHIRAT or kg.type = :KG_TSHVIGHI_VSLI) and kg.karmozdAmount != 0 " + karmozdWhereClause
                ).setLong("karId", karmozdId)
         .setParameter("CODE_MOVAGHAT", KarmozdGhest.Type.CODE_MOVAGHAT)
         .setParameter("TALIGHI", KarmozdGhest.Type.TALIGHI)
         .setParameter("ADI", KarmozdGhest.Type.ADI)
         .setParameter("PARDAKHTE_CODE_MOVAGHATI", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)
         .setParameter("TAGHIR_CODE_DAEM", KarmozdGhest.Type.TAGHIR_CODE_DAEM)
         .setParameter("KARMOZD_BARGASHTI", KarmozdGhest.Type.KARMOZD_BARGASHTI)
         .setParameter("VOSULI", KarmozdGhest.Type.VOSULI)
         .setParameter("POOSHESH_EZAFI", KarmozdGhest.Type.POOSHESH_EZAFI)
         .setParameter("SENIOR", KarmozdGhest.Type.SENIOR)
         .setParameter("TADILI", KarmozdGhest.Type.TADILI)
         .setParameter("TAGHIRAT", KarmozdGhest.Type.TAGHIRAT)
         .setParameter("KG_TSHVIGHI_VSLI", KarmozdGhest.Type.KARMOZD_TASHVIGHI_VOSULI)
                ;
//        SQLQuery
//                query = (SQLQuery) getSession().createSQLQuery
//                (
////                        "select count(*)" +
//                    "select 'کارمزد' as the_type, g.karmozd_real, g.karmozd_paid,cast(kg.karmozd_amount as varchar2(255)) as Mablagh_pardakhti," +
//                    "        c.amount_long as meghdar_bedehi,nvl(khs.amount,c.amount_long) as mablagh_pardakhti_az_bedehi,c.shomare_moshtari,gtb.SALEBIMEI + 1,g.sarresid_date, " +
//                    "        b.BIMENAME_SHOMARE,e.SARMAYE_PAYE_FOT_LONG,e.NAHVE_PARDAKHT_HAGH_BIME,n.KODENAMAYANDEKARGOZAR,n.name as name_Namayande, n.TYPE_ENUM," +
//                    "        kg.type_karmozd,kg.tarefe, null as onvan,null as description ,sh.name as bimeGozarFirstName,sh.name_Khanevadegi as bimeGozarLastName " +
//                    "from tbl_karmozd_ghest kg inner join tbl_ghest g on kg.ghest_id=g.id " +
//                    "                          inner join tbl_karmozd_namayande kn on kg.karmozd_namayande_id=kn.id " +
//                    "                          inner join tbl_credebit c on c.ghest_id=g.id " +
//                    "                          left join tbl_khate_sanad khs on khs.id=kg.khate_sanad_id " +
//                    "                          inner join tbl_ghestbandi gtb on gtb.id=g.ghestbandi_id " +
//                    "                          inner join tbl_bimename b on b.bimename_id=gtb.bimename_id " +
//                    "                          inner join tbl_pishnehad p on p.bimename_bimename_id=b.bimename_id " +
//                    "                          inner join tbl_estelam e on e.ESTELAM_ID=p.estelam_id " +
//                    "                          inner join tbl_namayande n on kn.NAMAYANDE_ID=n.id " +
//                    "                          inner join tbl_bimeGozar bg on p.bimeGozar_id=bg.id" +
//                    "                          inner join tbl_shakhs sh on bg.shakhs_id=sh.shakhs_id " +
//                    "where p.c_valid=1 and kg.karmozd_id=:karId and kg.BLACK_LIST_ID is null and ( (kg.TYPE_KARMOZD!='CODE_MOVAGHAT' and (kg.TYPE_KARMOZD = 'TALIGHI' or kg.TYPE_KARMOZD = 'ADI' or kg.TYPE_KARMOZD IS NULL ) ) " +
//                    "or kg.TYPE_KARMOZD = 'PARDAKHTE_CODE_MOVAGHATI' or kg.TYPE_KARMOZD = 'KARMOZD_BARGASHTI' or kg.TYPE_KARMOZD = 'VOSULI' or kg.TYPE_KARMOZD = 'POOSHESH_EZAFI' or kg.TYPE_KARMOZD = 'SENIOR') "+ karmozdWhereClause +
//                    " union all " +
//                    "select 'تعدیلات' as the_type, null,null, kt.xl_mablagh as Mablagh_pardakhti,null,null,null,null,null,kt.xl_shomare_bimename, null, " +
//                    "        null,kt.xl_code_namayande, n1.name as name_Namayande,n1.TYPE_ENUM,null,null,kt.xl_onvan as onvan,kt.xl_sharh as description, null as bimeGozarFirstName, null as bimeGozarLastName " +
//                    "from tbl_karmozd_tadilat kt inner join tbl_namayande n1 on n1.kodenamayandekargozar=kt.xl_code_namayande " +
//                    "where kt.karmozd_id=:karId and kt.KARMOZD_NAMAYANDE_ID is not null "+ tadilatWhereClause
//                ).setLong("karId", karmozdId);
        query.setFirstResult(maxResults);
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setPageNumber(pgList.getPageNumber()+1);

//        List<AllPayment> allPaymentList=new ArrayList<AllPayment>();
//        for(Object[] o : (List<Object[]>)query.list())
//        {
//            AllPayment allPayment=new AllPayment((String)o[0],(String)o[1],(String)o[2],(String)o[3],o[4]==null?null:o[4].toString(),(String)o[5],
//                                                 (String)o[6],o[7]==null?null:o[7].toString(),(String)o[8],(String)o[9],o[10]==null?null:o[10].toString(),(String)o[11],
//                                                 (String)o[12],(String)o[13],(String)o[14],(String)o[15],(String)o[16],(String)o[17],(String)o[18],o[19]==null?null:o[19].toString(),o[20]==null?null:o[20].toString());
//            allPaymentList.add(allPayment);
//        }

        pgList.setList(query.list());
        return pgList;
    }

    public List<KarmozdGhest> findKarmozdGhestsForSenior(String azTarikh, String taTarikh)
    {
        Query query= getSession().createQuery
                (
                        "select kg from KarmozdGhest kg join kg.karmozdNamayande kn join kn.namayande n " +
                                "where kg.karmozd.taTarikhVosoli<=:to and n.namayandeType=:typeEnum and kg.ghest.id is not null " +
                                "and (kg.type=:adi or kg.type=:talighi or kg.type=:pardakhtMvgt or kg.type=:tgr or kg.type=:brgshti)  " +
                                "and n.senior is not null and kg.karmozdSenior is null "
                )
        .setParameter("typeEnum",Namayande.NayamandeType.FORUSHANDE)
        .setParameter("to",taTarikh)
        .setParameter("adi",KarmozdGhest.Type.ADI)
        .setParameter("talighi",KarmozdGhest.Type.TALIGHI)
        .setParameter("pardakhtMvgt", KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)
        .setParameter("tgr", KarmozdGhest.Type.TAGHIRAT)
        .setParameter("brgshti", KarmozdGhest.Type.KARMOZD_BARGASHTI);
//        .setParameter("tadili", KarmozdGhest.Type.TADILI);
        return query.list();
    }

    public KarmozdGhest findKarmozdGhestById(Long id)
    {
        return (KarmozdGhest) findObjectByProperty(KarmozdGhest.class,"id",id.intValue());
    }

    public String karmozdTashvighiStepOne(Long karmozdId, String azTarikh, String taTarikh)
    {
        try
        {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call KARMOZD_TASHVIGHI(?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setLong(2, karmozdId);
            st.setString(3, azTarikh);
            st.setString(4, taTarikh);
            st.execute();
            return st.getString(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public String setAmountTashvighiKarmozdNamayande(Long karmozdId)
    {
        try
        {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call KARMOZD_TASHVIGHI_TWO(?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setLong(2, karmozdId);
            st.execute();
            return st.getString(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public List<KarmozdTashvighiVosuliVM> getKhateSanadsOfEligibleNamayande(Karmozd karmozd)
    {
        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.KarmozdTashvighiVosuliVM(kh, g, kn, n.id, e.nahve_pardakht_hagh_bime,t,b.tarikhSodour) " +
                                "from KhateSanad kh join kh.sanad s join kh.bedehiCredebit cr join cr.ghest g join g.ghestBandi gb  join gb.bimename b join b.pishnehadList p join p.namayande n " +
                                "join n.karmozdNamayandeList kn left join p.tarh t join p.estelam e " +
                                "where s.createdDate>=:fromDate and s.createdDate<=:toDate and cr.credebitType=:ghest and b.readyToShow='yes' and p.valid=true and kn.karmozd.id=:karmozdId " +
                                "and kn.status=:eligible and p.options is null and not exists(select bl.id from BlackList bl where bl.bimename.id=b.id and bl.type=:typeBL) " +
                                "and get_constantkey(b.tarikhSodour,:currentDate,'HAZINEVOSUL',case when e.nahve_pardakht_hagh_bime='yekja' then 'MEGHDARHAZINE_YEKJA' else 'MEGHDARHAZINE_GYEKJA' end,p.tarh.id)>=0.02"
                )
                .setString("fromDate", karmozd.getAzTarikhVosoli())
                .setString("toDate", karmozd.getTaTarikhVosoli())
                .setParameter("ghest", Credebit.CredebitType.GHEST)
                .setLong("karmozdId", karmozd.getId())
                .setParameter("eligible", KarmozdNamayande.Status.ELIGIBLE)
                .setParameter("typeBL",BlackList.Type.KARMOZD_VOSULI)
                .setParameter("currentDate",DateUtil.getCurrentDate());
        return query.list();
    }

}
