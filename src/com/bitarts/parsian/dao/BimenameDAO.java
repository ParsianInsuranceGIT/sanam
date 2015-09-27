package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.viewModel.*;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@SuppressWarnings({"ALL"})
public class BimenameDAO extends BaseDAO {
    public static String ID = "id";
    public static String shomare = "shomare";
    public static String tarikhSodour = "tarikhSodour";

    public void save(Bimename bimename) {
        bimename.setReadyToShow("no");
        super.save(bimename);
    }

    public void update(Bimename bimename) {
        super.update(bimename);
    }

    public Long minOrMaxSerialByIdDaste(String minOrmax,Integer idDaste)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Serial.class,"s")
                                                                                        .createCriteria("dasteSerial","ds")
                                                                                        .add(Restrictions.eq("ds.id",idDaste));
        if(minOrmax=="MAX") criteria.setProjection(Projections.max("s.shomareSerial"));

        else criteria.setProjection(Projections.min("s.shomareSerial"));

        return (Long)criteria.list().get(0);
    }

    public List<User> findAllKarshenas() {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("faal", true));
        list.createCriteria("u.roles", "r").createCriteria("u.mojtamaSodoor", "m").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.or(
                        Restrictions.eq("r.roleName", "ROLE_PAS_KARSHENAS"),
                        Restrictions.and(
                                Restrictions.eq("r.roleName", "ROLE_KARSHENAS_SODUR"),
                                Restrictions.eq("m.namayandeType", Namayande.NayamandeType.MOJTAMA)
                        )
                ));
        list.addOrder(Order.asc("firstName")).setCacheable(true);
        List<User> returnList = list.list();
        for(User k: returnList)
        {
//            Long assignCount = (Long) getSession().createQuery("select count(*) from Pishnehad p where p.karshenas=:ka and p.tarikhAssignBeKarshenas=:em").setParameter("ka",k).setParameter("em",DateUtil.getCurrentDate()).uniqueResult();
//            k.setAssignCount(assignCount.intValue());
            k.setAssignCount(0);
        }
        return returnList;
    }

    public boolean isAvailableSerial(Long shomareSerial)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Serial.class).add(Restrictions.eq("shomareSerial",shomareSerial));
        return criteria.list().size()>0? true:false;

    }

    public List<Serial> findSerialsEstefadeNashode(Long serialFirst,Long serialLast)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Serial.class);
        List<Serial> notUsedSerials=new ArrayList<Serial>();
        if(serialFirst!=null && serialFirst>0)
        {
            criteria.add(Restrictions.ge("shomareSerial",serialFirst));
        }
        if(serialLast!=null && serialLast>0)
        {
            criteria.add(Restrictions.le("shomareSerial",serialLast));
        }
        criteria.addOrder(Order.asc("shomareSerial"));
        for(Serial serial:(List<Serial>)criteria.list())
        {

            if(serial.getBimename()==null)
            {
                notUsedSerials.add(serial);
            }
        }

        return notUsedSerials;
    }

    public DasteSerial findDasteSerialById(Integer id)
    {
        return (DasteSerial)super.findById(DasteSerial.class,id);
    }

    public void saveOrUpdateDasteSerials(DasteSerial dasteSerial)
    {
        super.saveOrUpdate(dasteSerial);
    }

    public void saveOrUpdateSerials(List<Serial> serials)
    {
        super.saveOrUpdateAll(serials);
    }

    public DasteSerial findLastDasteSerial()
    {
        DetachedCriteria lastKodeDaste=DetachedCriteria.forClass(DasteSerial.class)
                .setProjection(Projections.max("kodeDaste"));
        List<DasteSerial> list=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DasteSerial.class)
                .add(Property.forName("kodeDaste").eq(lastKodeDaste)).list();
        return list.get(0);
    }

    public List<Serial> findSerialByShomareSerial(Long[] shomareSerials)
    {
        return (List<Serial>) super.findByProperties(Serial.class,"shomareSerial",shomareSerials);
    }



    public PaginatedListImpl<DasteSerial> findAllDasteSerials(PaginatedListImpl paginatedList,String vaziateDaste,Long serialFirst,Long serialLast,Long namayandegi,String bimenameType, User user)
    {
////        Criteria criteriaSerials= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Serial.class,"s");
//
//        SerialsVM svmOb= new SerialsVM();
////        svmOb.setVaziateDaste(DasteSerial.VaziateDaste.valueOf(vaziateDaste));
//        svmOb.setFirstShomareSerial(serialFirst);
//        svmOb.setLastShomareSerial(serialLast);
//        svmOb.setNamayandeId(namayandegi);
//        svmOb.setBimenameType(bimenameType);
//        findAllDasteSerials(paginatedList, svmOb);

        Criteria criteria= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DasteSerial.class,"d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Criteria paginatCriteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DasteSerial.class, "d");
        if(vaziateDaste != null && !vaziateDaste.isEmpty())
        {
            criteria.add(Restrictions.eq("d.vaziateDaste",DasteSerial.VaziateDaste.valueOf(vaziateDaste)));
            paginatCriteria.add(Restrictions.eq("d.vaziateDaste",DasteSerial.VaziateDaste.valueOf(vaziateDaste)));
        }

        criteria.add(Restrictions.eq("d.vahedeSodur",user.getMojtamaSodoor()));
        paginatCriteria.add(Restrictions.eq("d.vahedeSodur",user.getMojtamaSodoor()));

        if(bimenameType!=null && !bimenameType.isEmpty())
        {
            criteria.add(Restrictions.eq("d.bimenameType",bimenameType));
            paginatCriteria.add(Restrictions.eq("d.bimenameType",bimenameType));
        }

        if(namayandegi!=null && !namayandegi.equals(0))
        {
            criteria.createCriteria("d.namayandegi","n").add(Restrictions.eq("n.id",namayandegi));
            paginatCriteria.createCriteria("d.namayandegi","n").add(Restrictions.eq("n.id",namayandegi));
        }

        boolean isSerial=false;
        if(serialFirst!=null && !serialFirst.equals(0))
        {
//              criteriaSerials.add(Restrictions.ge("shomareSerial",serialFirst));
            criteria.createCriteria("d.serials","s").add(Restrictions.ge("s.shomareSerial", serialFirst));isSerial=true;
        }

        if(serialLast!=null && !serialLast.equals(0))
        {
//            criteriaSerials.add(Restrictions.le("shomareSerial",serialLast));
            if(!isSerial)
                criteria.createCriteria("d.serials", "s");
            criteria.add(Restrictions.le("s.shomareSerial", serialLast));
        }

//        criteriaSerials.setProjection(Projections.distinct(Property.forName("dasteSerial")));
//        List<DasteSerial> dsList=criteriaSerials.list();
//        Integer[] idArr=new Integer[dsList.size()];
//        int index=0;
//        for(DasteSerial ds:dsList)
//        {
//            idArr[index]=ds.getId();
//            index++;
//        }
//        if(idArr.length>0)
//        {
//            if(idArr.length>1000 && idArr.length<=3000)
//            {
//                int i=0;
//                Integer[] idArr1=new Integer[1000];
//                Integer[] idArr2=new Integer[1000];
//                Integer[] idArr3=new Integer[idArr.length-2000];
//
//                for(int j=0;j<idArr.length;j++)
//                {
//                    if(j<1000)
//                    {idArr1[j]=idArr[j];}
//                    else if(j<2000)
//                    {idArr2[j-1000] = idArr[j];}
//                    else
//                    {idArr3[j-2000] = idArr[j];}
//                }
//                criteria.add(Restrictions.in("id", idArr1));
//                paginatCriteria.add(Restrictions.in("id", idArr1));
////                criteria.add(Restrictions.and(Restrictions.in("id", idArr1), Restrictions.and(Restrictions.in("id", idArr2), Restrictions.in("id", idArr3))));
////                paginatCriteria.add(Restrictions.and(Restrictions.in("id", idArr1), Restrictions.and(Restrictions.in("id", idArr2), Restrictions.in("id", idArr3))));
//            }
//            else
//            {
//                criteria.add(Restrictions.in("id", idArr));
//                paginatCriteria.add(Restrictions.in("id", idArr));
//            }
//        }
        criteria.addOrder(Order.asc("d.kodeDaste"));
        paginatCriteria.setProjection(Projections.rowCount());
        paginatedList.setFullListSize(Integer.parseInt(paginatCriteria.list().get(0).toString()));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        return PagingUtil.getPaginatedList(criteria);
    }

//    public PaginatedListImpl<DasteSerial> findAllDasteSerials(PaginatedListImpl paginatedList,String vaziateDaste,Long serialFirst,Long serialLast,Long namayandegi,String bimenameType)
//    {
////        Criteria criteriaSerials= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Serial.class,"s");
//        Criteria criteria= getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DasteSerial.class,"d");
//        Criteria paginatCriteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DasteSerial.class, "d");
//        if(vaziateDaste != null && !vaziateDaste.isEmpty())
//        {
//            criteria.add(Restrictions.eq("d.vaziateDaste",DasteSerial.VaziateDaste.valueOf(vaziateDaste)));
//            paginatCriteria.add(Restrictions.eq("d.vaziateDaste",DasteSerial.VaziateDaste.valueOf(vaziateDaste)));
//        }
//
//        if(bimenameType!=null && !bimenameType.isEmpty())
//        {
//            criteria.add(Restrictions.eq("d.bimenameType",bimenameType));
//            paginatCriteria.add(Restrictions.eq("d.bimenameType",bimenameType));
//        }
//
//        if(namayandegi!=null && !namayandegi.equals(0))
//        {
//            criteria.createCriteria("d.namayandegi","n").add(Restrictions.eq("n.id",namayandegi));
//            paginatCriteria.createCriteria("d.namayandegi","n").add(Restrictions.eq("n.id",namayandegi));
//        }
//
//        if(serialFirst!=null && !serialFirst.equals(0))
//        {
//              criteriaSerials.add(Restrictions.ge("shomareSerial",serialFirst));
//        }
//
//        if(serialLast!=null && !serialLast.equals(0))
//        {
//            criteriaSerials.add(Restrictions.le("shomareSerial",serialLast));
//        }
//
//        criteriaSerials.setProjection(Projections.distinct(Property.forName("dasteSerial")));
//        List<DasteSerial> dsList=criteriaSerials.list();
//        Integer[] idArr=new Integer[dsList.size()];
//        int index=0;
//        for(DasteSerial ds:dsList)
//        {
//            idArr[index]=ds.getId();
//            index++;
//        }
//        if(idArr.length>0)
//        {
//            if(idArr.length>1000 && idArr.length<=3000)
//            {
//                int i=0;
//                Integer[] idArr1=new Integer[1000];
//                Integer[] idArr2=new Integer[1000];
//                Integer[] idArr3=new Integer[idArr.length-2000];
//
//                for(int j=0;j<idArr.length;j++)
//                {
//                    if(j<1000)
//                    {idArr1[j]=idArr[j];}
//                    else if(j<2000)
//                    {idArr2[j-1000] = idArr[j];}
//                    else
//                    {idArr3[j-2000] = idArr[j];}
//                }
//                criteria.add(Restrictions.in("id", idArr1));
//                paginatCriteria.add(Restrictions.in("id", idArr1));
////                criteria.add(Restrictions.and(Restrictions.in("id", idArr1), Restrictions.and(Restrictions.in("id", idArr2), Restrictions.in("id", idArr3))));
////                paginatCriteria.add(Restrictions.and(Restrictions.in("id", idArr1), Restrictions.and(Restrictions.in("id", idArr2), Restrictions.in("id", idArr3))));
//            }
//            else
//            {
//                criteria.add(Restrictions.in("id", idArr));
//                paginatCriteria.add(Restrictions.in("id", idArr));
//            }
//        }
//        criteria.addOrder(Order.asc("kodeDaste"));
//        paginatCriteria.setProjection(Projections.rowCount());
//        paginatedList.setFullListSize((Integer) paginatCriteria.list().get(0));
//        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
//        criteria.setFirstResult(maxResults);
//        criteria.setMaxResults(paginatedList.getObjectsPerPage());
//        return PagingUtil.getPaginatedList(criteria);
//    }
    public PaginatedListImpl<SerialsVM> findAllDasteSerials(PaginatedListImpl pgList,SerialsVM svmOb)
    {
        String whereClause="";
        String havingClause="";


        if(svmOb.getVaziateDaste() != null)
        {
            whereClause +="d.vaziateDaste='"+svmOb.getVaziateDaste()+"' and ";

        }

        if(svmOb.getBimenameType()!=null && !svmOb.getBimenameType().isEmpty())
        {
            whereClause +="d.bimenameType='" + svmOb.getBimenameType() + "' and ";
        }

        if(svmOb.getNamayandeId()!=null && !svmOb.getNamayandeId().equals(0))
        {
            whereClause +="n.id="+ svmOb.getNamayandeId() + " and ";
        }

        if(svmOb.getVahedeSodurId()!=null && !svmOb.getVahedeSodurId().equals(0))
        {
            whereClause +="vs.id="+ svmOb.getVahedeSodurId() + " and ";
        }

        if(svmOb.getFirstShomareSerial()!=null && !svmOb.getFirstShomareSerial().equals(0))
        {
            havingClause += " having min(s.shomareSerial)>='" + svmOb.getFirstShomareSerial() + "' ";
        }
//
        if(svmOb.getLastShomareSerial()!=null && !svmOb.getLastShomareSerial().equals(0))
        {
            havingClause += " and max(s.shomareSerial)<='" + svmOb.getLastShomareSerial() + "' ";
        }

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count= getSession().createQuery
        (
            "select count(distinct d.id)from DasteSerial d join d.namayandegi n join d.vahedeSodur vs join d.serials s where 1=1 " + whereClause +
            havingClause
        );

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.SerialsVM( min(s.shomareSerial), max(s.shomareSerial), d.tarikheSabt, d.bimenameType, vs.id, vs.kodeNamayandeKargozar, vs.name," +
                                " n.id, n.kodeNamayandeKargozar, n.name,n.name, d.vaziateDaste, count(s.id), " +
                                "(select count(s1.id) from Serial s1 join s1.dasteSerial d1 where d1.id=d.id and s1.vaziatSerial=:sttEbtl)," +
                                " d.mizaneJabejayi, d.id, d.kodeDaste, (select count(s2.id) from Serial s2 join s2.dasteSerial d2 where d2.id=d.id and s2.bimename is null)) " +
                                "from DasteSerial d join d.namayandegi n join d.vahedeSodur vs join d.serials s " +
                                "where 1=1 " + whereClause +
                                "group by d.tarikheSabt, d.bimenameType, vs.id, vs.kodeNamayandeKargozar, vs.name,n.id, n.kodeNamayandeKargozar, n.name, d.vaziateDaste, d.mizaneJabejayi, d.id, d.kodeDaste " +
                                havingClause + " order by d.kodeDaste "
                ).setParameter("sttEbtl", Serial.VaziatSerial.EBTAL_SHODE);
//        .setParameter("st", DasteSerial.VaziateDaste.BASTE_SHODE);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        return pgList;
    }

    public Darkhast findDarkhastByKhesaratId(Integer khesaratId)
    {
        Query query = getSession().createQuery
                (
                        "SELECT d FROM Darkhast d where d.khesarat.id = " + khesaratId
                );

        List<Darkhast> list = (List<Darkhast>) query.list();
        if(list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    public PaginatedListImpl findAll(String tarikhShoro, String tarikhPayan, PaginatedListImpl pgList)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Bimename.class);
        criteria.createCriteria("b.pishnehadList", "p");
        criteria.add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.eq("b.readyToShow", "yes"));
//        criteria.add(Restrictions.gt("tarikhSodour", tarikhShoro)).add(Restrictions.le("tarikhSodour", tarikhPayan));
        criteria.add(Restrictions.le("tarikhSodour", tarikhPayan));
        criteria.addOrder(Order.desc("b.shomare"));
        criteria.setProjection(Projections.id());

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class, "b");
        count.add(Subqueries.propertyIn("b.id",criteria));
        count.addOrder(Order.desc("b.shomare"));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        pgList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class, "b");
        nonDetachedCriteria.add(Subqueries.propertyIn("b.id",criteria));
        nonDetachedCriteria.addOrder(Order.desc("b.shomare"));
        nonDetachedCriteria.setFirstResult(pgList.getPageNumber() * pgList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(nonDetachedCriteria.list());
        pgList.setPageNumber(pgList.getPageNumber() - 1);
        return pgList;
    }

    public List<Bimename> findAllForMosharekat(String tarikhShoro, String tarikhPayan) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class, "b");
        criteria.createCriteria("b.pishnehadList", "p");
        criteria.add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.eq("b.readyToShow", "yes"));
        criteria.add(Restrictions.le("tarikhSodour", tarikhPayan));
        criteria.add(Restrictions.ge("tarikhSodour", tarikhShoro));
        criteria.addOrder(Order.desc("b.shomare"));
        return criteria.list();
    }

//    public PaginatedListImpl<Bimename> findAllPaginated(User user, PaginatedListImpl<Bimename> paginatedList) {
//
//        int pageNum = paginatedList.getPageNumber();
//        final int objectsPerPage = paginatedList.getObjectsPerPage();
//        final int firstResult = objectsPerPage * pageNum;
//
//        DetachedCriteria criteria = DetachedCriteria.forClass(Bimename.class, "b")
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
////                .createCriteria("p.bimeShode", "bs")
////                .createCriteria("bs.shakhs","bss").createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "bgs")
//                .add(Restrictions.eq("b.readyToShow", "yes"));
//
//        if(userHasRole(user, "ROLE_NAMAYANDE"))
//        {
//            criteria.createCriteria("b.pishnehadList", "p").createCriteria("p.namayande", "n").add(Restrictions.eq("n.id", user.getNamayandegi().getId()));
//            // this is temp
//            criteria.createCriteria("p.karshenas","k").createCriteria("k.mojtamaSodoor","m").add(Restrictions.ne("m.kodeNamayandeKargozar","111115"));
//        }
//        else if (userHasRole(user,"ROLE_KARSHENAS_SODUR"))
//        {
//            criteria.createCriteria("b.pishnehadList", "p").createCriteria("p.karshenas","k")
//                    .createCriteria("k.mojtamaSodoor","m")
//                    .add(Restrictions.eq("m.id",user.getMojtamaSodoor().getId()));
//        }
////        criteria.addOrder(Order.desc("b.shomare"));
//        criteria.setProjection(Projections.id());
//
//        Criteria bimenameCountCriteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class);
//        bimenameCountCriteria.add(Subqueries.propertyIn("id",criteria));
//        int count= (Integer)bimenameCountCriteria.setProjection(Projections.rowCount()).list().get(0);
//
//        Criteria bimenameCriteria =getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class,"b");
//        bimenameCriteria.add(Subqueries.propertyIn("b.id",criteria));
//        bimenameCriteria.addOrder(Order.desc("b.shomare"));
//        bimenameCriteria.setFirstResult(firstResult);
//        bimenameCriteria.setMaxResults(objectsPerPage);
//        List<Bimename> list=bimenameCriteria.list();
//
//        paginatedList.setFullListSize(count);
//        paginatedList.setList(list);
//        paginatedList.setPageNumber(pageNum+1);
//        paginatedList.setObjectsPerPage(objectsPerPage);
//        return paginatedList;
////        Criteria intactCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class);
////        return PagingUtil.getPaginatedList(criteria, intactCriteria, "id");
////        return PagingUtil.getPaginatedList(criteria);
////        return PagingUtil.getPaginatedList_oldVersion(criteria);
//    }

    public PaginatedListImpl findAllPaginated(User user, PaginatedListImpl pgList)
    {
        String whereClause="";

        if (user != null) //karbare jari
        {
            if(userHasRole(user, "ROLE_NAMAYANDE")|| userHasRole(user, "ROLE_KARMOZD_NAMAYANDE")|| userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
            {
                whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
                // this is temp
                //whereClause += "k.mojtamaSodoor.kodeNamayandeKargozar != '111115' and ";
            }
            else if (userHasRole(user,"ROLE_KARSHENAS_SODUR") && user.getMojtamaSodoor().getKodeNamayandeKargozar() != "111116") //111116 = shobeye sodour va khesarate omre markaze 2
            {
    //            whereClause += "(k.mojtamaSodoor.id = " + user.getMojtamaSodoor().getId() + " or p.namayande.sarparast.id = " + user.getMojtamaSodoor().getId() + ") and ";
            }
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }
        Query count = null;
             count = getSession().createQuery
                    (
                            "select count(b.id) " +
                                    "from Pishnehad p left join p.karshenas k join p.bimename b " +
                                    "where b.readyToShow = 'yes' and p.valid = true " + whereClause +
                                    " order by b.shomare desc"
                    );
        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                      (
                          "select new com.bitarts.parsian.viewModel.BimenameVM(p.id, b.shomare, b.tarikhSodour, b.tarikhEngheza, b.state.stateName, " +
                          "p.bimeGozar.shakhs.name, p.bimeGozar.shakhs.nameKhanevadegi, p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, " +
                          "p.namayande.name, p.namayande.kodeNamayandeKargozar, p.namayande.vahedSodur.name, k.firstName, k.lastName, k.personalCode, b.state.id, b.id, b.hasElhaghie, b.darkhastDarJaryanType) " +
                          "from Pishnehad p left join p.karshenas k join p.bimename b " +
                          "where b.readyToShow = 'yes' and p.valid = true " +  whereClause +
                          " order by b.shomare desc"
                      );

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);

        return pgList; //liste bimenameha
    }

    public List<Bimename> findAllBimenamePaginatedForBimeGozar(User user) {
        String  shomaresabt ="";
        String tarikhsabt = "";
        if (user.getShomare_sabt() != null &&  user.getTarikhe_sabt() != null) //hoghooghi
        {
            shomaresabt = user.getUsername().substring(0, user.getUsername().length()-8);
            tarikhsabt = user.getUsername().substring(user.getUsername().length()-8,user.getUsername().length()-4)+"/"+
                    user.getUsername().substring(user.getUsername().length()-4,user.getUsername().length()-2)+ "/"+
                    user.getUsername().substring(user.getUsername().length()-2,user.getUsername().length());
        }
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class, "b");
        criteria.createCriteria("b.pishnehadList", "p").createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "bgs");
        criteria.add(Restrictions.eq("p.valid", true));
        criteria.add(Restrictions.eq("b.readyToShow", "yes"));
        criteria.add(Restrictions.or
                (Restrictions.and(Restrictions.eq("bgs.kodeMelliShenasayi", user.getUsername()),Restrictions.eq("bgs.type" , Shakhs.BimeGozarType.HAGHIGHI)),
                 Restrictions.and(Restrictions.eq("bgs.shomareSabt", shomaresabt),Restrictions.eq("bgs.tarikheSabt" , tarikhsabt))
                ));
        criteria.addOrder(Order.desc("b.shomare"));
        return criteria.list();
    }

    public PaginatedListImpl searchBimenamePaginated(PaginatedListImpl pgList, String bimeGozarName,String bimeGozarFamily,String bimeGozarKodeMelli, String bimeShodeName,String bimeShodeFamily,
                                                                   String bimeShodeKodeMelli, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid,
                                                                   State state, Integer subsetState, Integer barresiPezeshki, String nahvePardakhtS, User user, String sarmayeFowt,String codeRahgiri,
                                                                   Long namayandegiId,Long vahedSodurId,String noe_tarh,String noeGharardad,String karbareSabtKonande,Long groupId, Boolean isCodeMovaghat)
    {
        String whereClause = "";
        List<Integer> pishIds=new ArrayList<Integer>();

        if (user != null)
        {
            if(userHasRole(user, "ROLE_NAMAYANDE") || userHasRole(user, "ROLE_KARMOZD_NAMAYANDE")|| userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
            {
                whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
                // this is temp
                //whereClause += "p.karshenas.mojtamaSodoor.kodeNamayandeKargozar != '111115' and ";
            }
            else if (userHasRole(user,"ROLE_KARSHENAS_SODUR"))
            {
//                whereClause += "(k.mojtamaSodoor.id = " + user.getMojtamaSodoor().getId() + " or p.namayande.sarparast.id = " + user.getMojtamaSodoor().getId() + ") and ";
            }
        }

        if (user != null){
            if(!userHasRole(user, "ROLE_NAMAYANDE"))
            {
                if((namayandegiId!=null && namayandegiId>0) || (vahedSodurId!=null && vahedSodurId>0))
                {
                    if(namayandegiId!=null && namayandegiId>0)
                        whereClause += "p.namayande.id = " + namayandegiId + " and ";
                    if(vahedSodurId!=null && vahedSodurId>0)
                        whereClause += "p.namayande.vahedSodur.id = " + vahedSodurId + " and ";//todo : vahedSodur is false. must be change
                }
            }
        }

        if(groupId!=null && groupId>0)
        {
            whereClause += "p.gharardad.id = " + groupId + " and ";
        }

        if(karbareSabtKonande!=null && !karbareSabtKonande.isEmpty())
        {
            whereClause += "k.firstName like '%" + karbareSabtKonande + "%' or k.lasttName like '%" + karbareSabtKonande + "%' and ";
        }
        if(codeRahgiri!=null && !codeRahgiri.isEmpty())
            whereClause += "p.radif = '" + codeRahgiri + "' and ";
        if (noeGharardad!=null && !noeGharardad.isEmpty())
            whereClause += "p.noeGharardad = '" + noeGharardad + "' and ";
        if (state != null && state.getId() != null)
        {
//            crit.createCriteria("b.state", "st").add(Restrictions.eq("st.id", state.getId()));
            whereClause += "b.state.id = " + state.getId() + " and ";
            if(state.getId()==500)
            {
                if(subsetState != null && subsetState==1)
                {
//                    crit.createCriteria("b.elhaghiyeha","be",CriteriaSpecification.LEFT_JOIN);
                    whereClause += "b.elhaghiyeha is empty and ";
                }
                if(subsetState != null && subsetState==2)
                {
//                    crit.createCriteria("b.elhaghiyeha","be",CriteriaSpecification.INNER_JOIN);
                    whereClause += "b.elhaghiyeha is not empty and ";
                }
            }
            if(state.getId()==510)
            {
                if(subsetState != null && subsetState==1)
                {
//                    crit.createCriteria("b.elhaghiyeha","be");
                    Query q= getSession().createQuery("select dt.oldPishnehad.id from DarkhastTaghirat dt");
                    pishIds= q.list();
                    whereClause +="p.id in (:pishnehads) and ";
                }
                if(subsetState != null && subsetState==2)
                {
//                    crit.createCriteria("b.allDarkhasts","ba");
                    whereClause += "b.allDarkhasts is not empty and ";
                }
                if(subsetState != null && subsetState==3)
                {
//                    crit.createCriteria("b.khesarats","bk");
                    whereClause += "b.khesarats is not empty and ";
                }
            }
        }
        if (bimeGozarName != null && !bimeGozarName.isEmpty() || bimeGozarFamily != null && !bimeGozarFamily.isEmpty() || bimeGozarKodeMelli != null && !bimeGozarKodeMelli.isEmpty())
        {
            if (bimeGozarName != null && !bimeGozarName.isEmpty())
                whereClause += "p.bimeGozar.shakhs.name like '%" + bimeGozarName + "%' and ";
            if (bimeGozarFamily != null && !bimeGozarFamily.isEmpty())
                whereClause += "p.bimeGozar.shakhs.nameKhanevadegi like '%" + bimeGozarFamily + "%' and ";
            if(bimeGozarKodeMelli != null && !bimeGozarKodeMelli.isEmpty())
                whereClause += "p.bimeGozar.shakhs.kodeMelliShenasayi like '%" + bimeGozarKodeMelli + "%' and ";
        }
        if (bimeShodeName != null && !bimeShodeName.isEmpty() || bimeShodeFamily != null && !bimeShodeFamily.isEmpty() || bimeShodeKodeMelli != null && !bimeShodeKodeMelli.isEmpty())
        {
            if (bimeShodeName != null && !bimeShodeName.isEmpty())
                whereClause += "p.bimeShode.shakhs.name like '%" + bimeShodeName + "%' and ";
            if (bimeShodeFamily != null && !bimeShodeFamily.isEmpty())
                whereClause += "p.bimeShode.shakhs.nameKhanevadegi like '%" + bimeShodeFamily + "%' and ";
            if(bimeShodeKodeMelli != null && !bimeShodeKodeMelli.isEmpty())
                whereClause += "p.bimeShode.shakhs.kodeMelliShenasayi like '%" + bimeShodeKodeMelli + "%' and ";
        }
        if(isCodeMovaghat)
        {
            whereClause += "p.options = 'CODE_MOVAGHAT' and ";
        }
        if (azTarikhSodur != null && !azTarikhSodur.isEmpty())
            whereClause += "b.tarikhSodour >= '" + azTarikhSodur + "' and ";
        if (taTarikhSodur != null && !taTarikhSodur.isEmpty())
            whereClause += "b.tarikhSodour <= '" + taTarikhSodur + "' and ";
        if (shomare != null && !shomare.isEmpty())
            whereClause += "b.shomare like '%" + shomare + "%' and ";
        if((nahvePardakhtS != null && !nahvePardakhtS.isEmpty())||(sarmayeFowt != null && !sarmayeFowt.isEmpty())||(noe_tarh != null && !noe_tarh.isEmpty()))
        {
            if (nahvePardakhtS != null && !nahvePardakhtS.isEmpty())
                whereClause += "p.estelam.nahve_pardakht_hagh_bime = '" + nahvePardakhtS + "' and ";
            if (sarmayeFowt != null && !sarmayeFowt.isEmpty())
                whereClause += "p.estelam.sarmaye_paye_fot_long = " + Long.parseLong(sarmayeFowt.replaceAll(",","").trim()) + " and ";
            if(noe_tarh!=null && !noe_tarh.isEmpty())
                whereClause += "p.estelam.noe_tarh like '%"+ noe_tarh + "%' and " ;
        }
        if (azSarresid != null || taSarresid != null)
        {
//            crit.createCriteria("b.ghestBandiList", "gb").createCriteria("gb.ghestList", "gh").createCriteria("gh.credebit", "c");
            String bimeIds=" (select g.ghestBandi.bimename.id from Ghest g where ";
            if (azSarresid != null)
            {
                 bimeIds += "g.sarresidDate >= '" + azSarresid +"' ";
//                crit.add(Restrictions.ge("gh.sarresidDate", azSarresid));
            }
            if (taSarresid != null)
            {
                if(azSarresid != null)
                    bimeIds += "and ";
                bimeIds += "g.sarresidDate < '" + taSarresid +"' ";
//                crit.add(Restrictions.lt("gh.sarresidDate", taSarresid));
            }
            bimeIds += ")";
            whereClause += "b.id in (" + bimeIds + ") and ";
        }
        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count = getSession().createQuery
                      (
                          "select count(b.id) " +
                          "from Pishnehad p left join p.karshenas k join p.bimename b " +
                          "where b.readyToShow = 'yes' and p.valid = true " + whereClause +
                          " order by b.shomare desc"
                      );
        if(pishIds.size()>0)
            count.setParameterList("pishnehads",pishIds);

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                      (
                          "select new com.bitarts.parsian.viewModel.BimenameVM(p.id, b.shomare, b.tarikhSodour, b.tarikhEngheza, b.state.stateName, " +
                          "p.bimeGozar.shakhs.name, p.bimeGozar.shakhs.nameKhanevadegi, p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, " +
                          "p.namayande.name, p.namayande.kodeNamayandeKargozar, p.namayande.vahedSodur.name, k.firstName, k.lastName, k.personalCode, b.state.id, b.id, b.hasElhaghie, b.darkhastDarJaryanType) " +
                          "from Pishnehad p left join p.karshenas k join p.bimename b " +
                          "where b.readyToShow = 'yes' and p.valid = true " +  whereClause +
                          " order by b.shomare desc"
                      );
        if(pishIds.size()>0)
            query.setParameterList("pishnehads",pishIds);


        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);

        return pgList;
    }

    public PaginatedListImpl<Bimename> findAllPaginatedForMosharekat(User user, PaginatedListImpl<Bimename> paginatedList, String tarikhShoro, String tarikhPayan) {

        int pageNum = paginatedList.getPageNumber();
        final int objectsPerPage = paginatedList.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;

        DetachedCriteria criteria = DetachedCriteria.forClass(Bimename.class, "b")
                .add(Restrictions.eq("b.readyToShow", "yes"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.createCriteria("pishnehadList", "p").add(Restrictions.eq("p.valid", true));
//                .add(Restrictions.in("p.id", validIds));
        criteria.add(Restrictions.le("b.tarikhSodour", tarikhPayan));
//        criteria.add(Restrictions.eq("b.tarikhSodour", "1392/05/28"));
        criteria.setProjection(Projections.id());

        Criteria bimenameCountCriteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class);
        bimenameCountCriteria.add(Subqueries.propertyIn("id",criteria));
        int count = Integer.parseInt(bimenameCountCriteria.setProjection(Projections.rowCount()).list().get(0).toString());

        Criteria bimenameCriteria =getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class,"b");
        bimenameCriteria.add(Subqueries.propertyIn("b.id",criteria));
        bimenameCriteria.addOrder(Order.desc("b.shomare"));
        bimenameCriteria.setFirstResult(firstResult);
        bimenameCriteria.setMaxResults(objectsPerPage);
        List<Bimename> list=bimenameCriteria.list();

        paginatedList.setFullListSize(count);
        paginatedList.setList(list);
        paginatedList.setPageNumber(pageNum+1);
        paginatedList.setObjectsPerPage(objectsPerPage);
        return paginatedList;
    }

    private boolean userHasRole(User user, String roleName) {
        boolean result = false;
        if (user != null){
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getRoleName().equalsIgnoreCase(roleName)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public void saveDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        super.save(darkhastBazkharid);
    }

    public void updateDarkhstBazkharid(DarkhastBazkharid darkhastBazkharid) {
        super.update(darkhastBazkharid);
    }

    public List<DarkhastTaghirat> searchDarkhastTaghiratsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DarkhastTaghirat.class,"d");
        if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
            criteria.add(Restrictions.ge("tarikhDarkhast",azTarikheDarkhast));
        if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
            criteria.add(Restrictions.le("tarikhDarkhast",taTarikheDarkhast));
        if((namayandegiId!=null && namayandegiId>0) ||(nameBimeGozar!=null && !nameBimeGozar.isEmpty())||(familyBimeGozar!=null && !familyBimeGozar.isEmpty()) || (kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty())
               || (shomareBime!=null && !shomareBime.isEmpty())||(nameBimeShode!=null && !nameBimeShode.isEmpty())||(familyBimeShode!=null && !familyBimeShode.isEmpty()) || (kodeMelliBimeShode!=null && !kodeMelliBimeShode.isEmpty()))
        {

            criteria.createCriteria("d.newPishnehad","dp");
            if(namayandegiId!=null && namayandegiId>0)
                criteria.createCriteria("dp.namayande","dpn").add(Restrictions.eq("dpn.id",namayandegiId));
            if(shomareBime!=null && !shomareBime.isEmpty())
                criteria.createCriteria("dp.bimename","dpb").add(Restrictions.like("shomare",shomareBime,MatchMode.ANYWHERE));
            if((nameBimeGozar!=null && !nameBimeGozar.isEmpty())||(familyBimeGozar!=null && !familyBimeGozar.isEmpty()) || (kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty()))
            {
                criteria.createCriteria("dp.bimeGozar","dpbg").createCriteria("dpbg.shakhs","dpbgs");
                if(nameBimeGozar!=null && !nameBimeGozar.isEmpty())
                    criteria.add(Restrictions.like("dpbgs.name",familyBimeGozar,MatchMode.ANYWHERE));
                if(familyBimeGozar!=null && !familyBimeGozar.isEmpty())
                    criteria.add(Restrictions.like("dpbgs.nameKhanevadegi",familyBimeGozar,MatchMode.ANYWHERE));
                if(kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty())
                    criteria.add(Restrictions.like("dpbgs.kodeMelliShenasayi",kodeMelliBimeGozar,MatchMode.ANYWHERE));
            }
            if((nameBimeShode!=null && !nameBimeShode.isEmpty())||(familyBimeShode!=null && !familyBimeShode.isEmpty()) || (kodeMelliBimeShode!=null && !kodeMelliBimeShode.isEmpty()))
            {
                criteria.createCriteria("dp.bimeShode","dpbs").createCriteria("dpbs.shakhs","dpbss");
                if(nameBimeShode!=null && !nameBimeShode.isEmpty())
                    criteria.add(Restrictions.like("dpbss.name",familyBimeShode,MatchMode.ANYWHERE));
                if(familyBimeShode!=null && !familyBimeShode.isEmpty())
                    criteria.add(Restrictions.like("dpbss.nameKhanevadegi",familyBimeShode,MatchMode.ANYWHERE));
                if(kodeMelliBimeShode!=null && !kodeMelliBimeShode.isEmpty())
                    criteria.add(Restrictions.like("dpbss.kodeMelliShenasayi",kodeMelliBimeShode,MatchMode.ANYWHERE));
            }
        }
        if(karshenas!=null && !karshenas.isEmpty())
            criteria.createCriteria("d.karshenas","dk").add(Restrictions.or(Restrictions.like("dk.firstName",karshenas,MatchMode.ANYWHERE),
                                                                            Restrictions.like("dk.lastName",karshenas,MatchMode.ANYWHERE)));
        return criteria.list();
    }

    public List<DarkhastBazkharid> searchDarkhastBazkharidsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(DarkhastBazkharid.class,"d");

        if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
            criteria.add(Restrictions.ge("tarikhDarkhast",azTarikheDarkhast));
        if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
            criteria.add(Restrictions.le("tarikhDarkhast",taTarikheDarkhast));
        if(nameBimeShode!=null && !nameBimeShode.isEmpty())
            criteria.add(Restrictions.like("nameBimeShode",nameBimeShode,MatchMode.ANYWHERE));
        if(familyBimeShode!=null && !familyBimeShode.isEmpty())
            criteria.add(Restrictions.like("familyBimeShode",familyBimeShode,MatchMode.ANYWHERE));
        if(kodeMelliBimeShode!=null && !kodeMelliBimeShode.isEmpty())
            criteria.add(Restrictions.like("kodeMeliBimeShode",kodeMelliBimeShode,MatchMode.ANYWHERE));

        if(shomareBime!=null && !shomareBime.isEmpty()|| nameBimeGozar!=null && !nameBimeGozar.isEmpty() || familyBimeGozar!=null && !familyBimeGozar.isEmpty() || kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty())
        {
            criteria.createCriteria("d.bimename","b");
            if(shomareBime!=null && !shomareBime.isEmpty())
                criteria.add(Restrictions.like("b.shomare",shomareBime,MatchMode.ANYWHERE));
            if((nameBimeGozar!=null && !nameBimeGozar.isEmpty()) || (familyBimeGozar!=null && !familyBimeGozar.isEmpty()) || (kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty()) || (namayandegiId!=null && namayandegiId>0))
            {
                criteria.createCriteria("b.pishnehad","p").add(Restrictions.eq("p.valid", true));
                if((nameBimeGozar!=null && !nameBimeGozar.isEmpty()) || (familyBimeGozar!=null && !familyBimeGozar.isEmpty()) || (kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty()))
                {
                    criteria.createCriteria("p.bimeGozar","pb").createCriteria("pb.shakhs","pbs");
                    if(nameBimeGozar!=null && !nameBimeGozar.isEmpty())
                        criteria.add(Restrictions.like("pbs.name",nameBimeGozar,MatchMode.ANYWHERE));
                    if(familyBimeGozar!=null && !familyBimeGozar.isEmpty())
                        criteria.add(Restrictions.like("pbs.nameKhanevadegi",familyBimeGozar,MatchMode.ANYWHERE));
                    if(kodeMelliBimeGozar!=null && !kodeMelliBimeGozar.isEmpty())
                        criteria.add(Restrictions.like("pbs.kodeMelliShenasayi",kodeMelliBimeGozar,MatchMode.ANYWHERE));
                }
                if(namayandegiId!=null && namayandegiId>0)
                    criteria.createCriteria("p.namayande","pn").add(Restrictions.eq("pn.id", namayandegiId));
            }
        }
        if(karshenas!=null && !karshenas.isEmpty())
            criteria.createCriteria("d.karshenas","dk").add(Restrictions.or(Restrictions.ilike("dk.firstName", "%" + karshenas + "%"),
                                                            Restrictions.ilike("dk.lastName", "%" + karshenas + "%")));
        return criteria.list();
    }

    public PaginatedListImpl<Darkhast> searchDarkhastsTaghirat(User user, String darkhastType ,String shomareBimename, Long namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createCriteria("d.darkhastTaghirat","dt")
            .createCriteria("dt.state","dts")
            .createCriteria("dts.transitionBegin","dtst")
            .createCriteria("dtst.roles","dtstr")
            .createCriteria("dtstr.users","dtstrUser");

            criteria.add(Restrictions.eq("dtstrUser.id", user.getId()));
            Set<Role> theRoles = user.getRoles();
            for (Role theRole : theRoles)
            {
                if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
                {
                    criteria.createCriteria("dt.namayande", "dtn");
                    criteria.add(Restrictions.eq("dtn.id", user.getNamayandegi().getId()));
                }
    //            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODUR))
    //            {
    //                criteria.createCriteria("db.karshenas", "dbk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dv.karshenas", "dvk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dk.karshenasKhesarat", "dkk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dt.karshenas", "dtk", CriteriaSpecification.LEFT_JOIN);
    //
    //                criteria.add
    //                        (
    //                            Restrictions.or
    //                            (
    //                                Restrictions.or
    //                                (
    //                                    Restrictions.eq("dbk.id", user.getId()),
    //                                    Restrictions.eq("dvk.id", user.getId())
    //                                ),
    //
    //                                Restrictions.or
    //                                (
    //                                    Restrictions.eq("dkk.id", user.getId()),
    //                                    Restrictions.eq("dtk.id", user.getId())
    //                                )
    //                            )
    //                        );
    //            }
            }

            if((shomareBimename!=null && !shomareBimename.isEmpty()) || (bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) ||
               (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) || (bimeShodeName!=null && !bimeShodeName.isEmpty()) || (bimeShodeFamily!=null && bimeGozarFamily.isEmpty()) ||
               (bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty()))
            {
                criteria.createCriteria("dt.oldPishnehad","dtPishnehad").createCriteria("dtPishnehad.bimename","dteBimename");

                if(shomareBimename!=null && !shomareBimename.isEmpty())
                    criteria.add(Restrictions.like("dteBimename.shomare", shomareBimename, MatchMode.ANYWHERE));

                if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) ||
                   (bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                {

                    if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dtPishnehad.bimeGozar","dtebGozar").createCriteria("dtebGozar.shakhs","dtebsh");

                        if(bimeGozarName!=null && !bimeGozarName.isEmpty())
                            criteria.add(Restrictions.like("dtebsh.name",bimeGozarName,MatchMode.ANYWHERE));

                        if(bimeGozarFamily!=null && !bimeGozarFamily.isEmpty())
                            criteria.add(Restrictions.like("dtebsh.nameKhanevadegi",bimeGozarFamily,MatchMode.ANYWHERE));

                        if(bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dtebsh.kodeMelliShenasayi",bimeGozarKodeMelli,MatchMode.ANYWHERE));
                    }
                    if((bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dtPishnehad.bimeShode","dtebShode").createCriteria("dtebShode.shakhs","dtebssh");

                        if(bimeShodeName!=null && !bimeShodeName.isEmpty())
                            criteria.add(Restrictions.like("dtebssh.name",bimeShodeName,MatchMode.ANYWHERE));

                        if(bimeShodeFamily!=null && !bimeShodeFamily.isEmpty())
                            criteria.add(Restrictions.like("dtebssh.nameKhanevadegi",bimeShodeFamily,MatchMode.ANYWHERE));

                        if(bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dtebssh.kodeMelliShenasayi",bimeShodeKodeMelli,MatchMode.ANYWHERE));
                    }
                }
            }

            if(namayandeId!=null && namayandeId>0)
                criteria.createCriteria("dt.namayande","dtNamayande").add(Restrictions.eq("dtNamayande.id",namayandeId));

            if(karshenas!=null && !karshenas.isEmpty())
            {
                criteria.createCriteria("dt.karshenas","dtKarshenas")
                        .add(Restrictions.or(
                                             Restrictions.like("dtKarshenas.firstName",karshenas,MatchMode.ANYWHERE),
                                             Restrictions.like("dtKarshenas.lastName",karshenas,MatchMode.ANYWHERE)));
            }

            if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.ge("dt.tarikhDarkhast",azTarikheDarkhast));

            if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.le("dt.tarikhDarkhast",taTarikheDarkhast));

            return PagingUtil.getPaginatedList(criteria);
    }

    public PaginatedListImpl<Darkhast> searchDarkhastsBazkharid(String darkhastType ,String shomareBimename, Integer namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d")
                          .createCriteria("d.darkhastBazkharid", "db");


            if((shomareBimename!=null && !shomareBimename.isEmpty()) || (bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) ||
               (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) || (bimeShodeName!=null && !bimeShodeName.isEmpty()) || (bimeShodeFamily!=null && bimeGozarFamily.isEmpty()) ||
               (bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty()))
            {
                criteria.createCriteria("db.bimename", "dbBimename");

                if(shomareBimename!=null && !shomareBimename.isEmpty())
                    criteria.add(Restrictions.like("dbBimename.shomare", shomareBimename, MatchMode.ANYWHERE));

                if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) ||
                   (bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                {
                    criteria.createCriteria("dbBimename.pishnehadSet","dbp").add(Restrictions.eq("dbp.valid",true));

                    if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dbp.bimeGozar","dtebGozar").createCriteria("dbbGozar.shakhs","dbbsh");

                        if(bimeGozarName!=null && !bimeGozarName.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.name",bimeGozarName,MatchMode.ANYWHERE));

                        if(bimeGozarFamily!=null && !bimeGozarFamily.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.nameKhanevadegi",bimeGozarFamily,MatchMode.ANYWHERE));

                        if(bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.kodeMelliShenasayi",bimeGozarKodeMelli,MatchMode.ANYWHERE));
                    }
                    if((bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dbp.bimeShode","dbbShode").createCriteria("dbbShode.shakhs","dbssh");

                        if(bimeShodeName!=null && !bimeShodeName.isEmpty())
                            criteria.add(Restrictions.like("dbssh.name",bimeShodeName,MatchMode.ANYWHERE));

                        if(bimeShodeFamily!=null && !bimeShodeFamily.isEmpty())
                            criteria.add(Restrictions.like("dbssh.nameKhanevadegi",bimeShodeFamily,MatchMode.ANYWHERE));

                        if(bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dbssh.kodeMelliShenasayi",bimeShodeKodeMelli,MatchMode.ANYWHERE));
                    }
                }
            }

            if(namayandeId!=null && namayandeId>0)
                criteria.createCriteria("db.namayande","dbNamayande").add(Restrictions.eq("dbNamayande.id",namayandeId));

            if(karshenas!=null && !karshenas.isEmpty())
            {
                criteria.createCriteria("db.karshenas","dbKarshenas")
                        .add(Restrictions.or(
                                             Restrictions.like("dbKarshenas.firstName",karshenas,MatchMode.ANYWHERE),
                                             Restrictions.like("dbKarshenas.lastName",karshenas,MatchMode.ANYWHERE)));
            }

            if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.ge("db.tarikhDarkhast",azTarikheDarkhast));

            if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.le("db.tarikhDarkhast",taTarikheDarkhast));

            return PagingUtil.getPaginatedList(criteria);
    }

    public PaginatedListImpl<Darkhast> searchDarkhastsKhesarat(User user, String darkhastType ,String shomareBimename, Long namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d");
            criteria.createCriteria("d.khesarat","db",CriteriaSpecification.LEFT_JOIN)
            .createCriteria("db.state","dbs",CriteriaSpecification.LEFT_JOIN)
            .createCriteria("dbs.transitionBegin","dbst",CriteriaSpecification.LEFT_JOIN)
            .createCriteria("dbst.roles","dbstr",CriteriaSpecification.LEFT_JOIN)
            .createCriteria("dbstr.users","dbstrUser",CriteriaSpecification.LEFT_JOIN);

            criteria.add(Restrictions.eq("dbstrUser.id", user.getId()));
            Set<Role> theRoles = user.getRoles();
            for (Role theRole : theRoles)
            {
                if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
                {
                    criteria.createCriteria("db.namayande", "dbn", CriteriaSpecification.LEFT_JOIN);
                    criteria.add(Restrictions.eq("dbn.id", user.getNamayandegi().getId()));
                }
    //            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODUR))
    //            {
    //                criteria.createCriteria("db.karshenas", "dbk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dv.karshenas", "dvk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dk.karshenasKhesarat", "dkk", CriteriaSpecification.LEFT_JOIN);
    //                criteria.createCriteria("dt.karshenas", "dtk", CriteriaSpecification.LEFT_JOIN);
    //
    //                criteria.add
    //                        (
    //                            Restrictions.or
    //                            (
    //                                Restrictions.or
    //                                (
    //                                    Restrictions.eq("dbk.id", user.getId()),
    //                                    Restrictions.eq("dvk.id", user.getId())
    //                                ),
    //
    //                                Restrictions.or
    //                                (
    //                                    Restrictions.eq("dkk.id", user.getId()),
    //                                    Restrictions.eq("dtk.id", user.getId())
    //                                )
    //                            )
    //                        );
    //            }
            }

            if((shomareBimename!=null && !shomareBimename.isEmpty()) || (bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) ||
               (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) || (bimeShodeName!=null && !bimeShodeName.isEmpty()) || (bimeShodeFamily!=null && bimeGozarFamily.isEmpty()) ||
               (bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty()))
            {
                criteria.createCriteria("db.bimename","dbBimename");

                if(shomareBimename!=null && !shomareBimename.isEmpty())
                    criteria.add(Restrictions.like("dbBimename.shomare", shomareBimename, MatchMode.ANYWHERE));

                if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) ||
                   (bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                {
                    criteria.createCriteria("dbBimename.pishnehadSet","dbp").add(Restrictions.eq("dbp.valid",true));

                    if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dbp.bimeGozar","dtebGozar").createCriteria("dbbGozar.shakhs","dbbsh");

                        if(bimeGozarName!=null && !bimeGozarName.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.name",bimeGozarName,MatchMode.ANYWHERE));

                        if(bimeGozarFamily!=null && !bimeGozarFamily.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.nameKhanevadegi",bimeGozarFamily,MatchMode.ANYWHERE));

                        if(bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dbbsh.kodeMelliShenasayi",bimeGozarKodeMelli,MatchMode.ANYWHERE));
                    }
                    if((bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                    {
                        criteria.createCriteria("dbp.bimeShode","dbbShode").createCriteria("dbbShode.shakhs","dbssh");

                        if(bimeShodeName!=null && !bimeShodeName.isEmpty())
                            criteria.add(Restrictions.like("dbssh.name",bimeShodeName,MatchMode.ANYWHERE));

                        if(bimeShodeFamily!=null && !bimeShodeFamily.isEmpty())
                            criteria.add(Restrictions.like("dbssh.nameKhanevadegi",bimeShodeFamily,MatchMode.ANYWHERE));

                        if(bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty())
                            criteria.add(Restrictions.like("dbssh.kodeMelliShenasayi",bimeShodeKodeMelli,MatchMode.ANYWHERE));
                    }
                }
            }

            if(namayandeId!=null && namayandeId>0)
                criteria.createCriteria("db.namayande","dbNamayande").add(Restrictions.eq("dbNamayande.id",namayandeId));

            if(karshenas!=null && !karshenas.isEmpty())
            {
                criteria.createCriteria("db.karshenasKhesarat","dbKarshenas")
                        .add(Restrictions.or(
                                             Restrictions.like("dbKarshenas.firstName",karshenas,MatchMode.ANYWHERE),
                                             Restrictions.like("dbKarshenas.lastName",karshenas,MatchMode.ANYWHERE)));
            }

            if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.ge("db.createdDate",azTarikheDarkhast));

            if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
                criteria.add(Restrictions.le("db.createdDate",taTarikheDarkhast));

            return PagingUtil.getPaginatedList(criteria);
    }

    public List<DarkhastBazkharid> findDarkhastKhesaratBeduneState()
    {
        return getSession().createQuery("select db from DarkhastBazkharid db where db.darkhastType=:khsrt and db.state.id is null").setParameter("khsrt",DarkhastBazkharid.DarkhastType.KHESARAT).list();
    }

    public void cleanChangeWithSerachField()
    {
        getSession().createQuery
        (
            "update Shakhs sh set sh.changeWithSerach = null " +
            "where sh.changeWithSerach is not null and  " +
            "      not exists (select s.id from Shakhs s join bimeShode bs join bs.pishnehadSet p join p.bimename b join b.state bst where bst.id=510 and s.id=sh.id ) and " +
            "      not exists (select s.id from Shakhs s join bimeGozar bg join bg.pishnehadSet p join p.bimename b join b.state bst where bst.id=510 and s.id=sh.id ) "
        );
    }


    public List<DarkhastBazkharid> findAllDarkhastBazkharid() {
    // todo this is for multiple duplicated fetch
            List<DarkhastBazkharid> darkhastBazkharidList = (List<DarkhastBazkharid>) super.findAll(DarkhastBazkharid.class);
//            Set<DarkhastBazkharid> darkhastBazkharidSet = new HashSet<DarkhastBazkharid>();
//            for (DarkhastBazkharid darkhastBazkharid : darkhastBazkharidList) {
//                darkhastBazkharidSet.add(darkhastBazkharid);
//            }
//            darkhastBazkharidList = new ArrayList<DarkhastBazkharid>();
//            for (DarkhastBazkharid darkhastBazkharid : darkhastBazkharidSet) {
//                darkhastBazkharidList.add(darkhastBazkharid);
//            }
            return darkhastBazkharidList;
    }

    public DarkhastBazkharid findDarkhastBazkharidById(Integer darkhastBazkharidId) {
        return (DarkhastBazkharid) super.findById(DarkhastBazkharid.class, darkhastBazkharidId);
    }

    public void updateZamaem(ZamaemDarkhast theZamime) {
        super.update(theZamime);
    }

    public void saveZamaemDarkhast(ZamaemDarkhast zamaemDarkhast) {
        super.save(zamaemDarkhast);
    }

    public ZamaemDarkhast findZamaemDarkhastById(int zamaemId) {
        return (ZamaemDarkhast) super.findById(ZamaemDarkhast.class, zamaemId);
    }

    public void saveElhaghiye(Elhaghiye elhaghiye) {
        super.saveOrUpdate(elhaghiye);
    }

    public void updateElhaghiye(Elhaghiye elhaghiye) {
        super.update(elhaghiye);
    }

    public PaginatedListImpl<ElhaghieVM> elhaghieReport(PaginatedListImpl<ElhaghieVM> pgList,ElhaghieVM svm)
    {
        String whereClause="";
        if(svm!=null)
        {
            if(svm.getBimeGozarFirstName()!=null && !svm.getBimeGozarFirstName().isEmpty())
            {
                whereClause += "bgs.name like '%"+svm.getBimeGozarFirstName()+"%' and ";
            }

            if(svm.getBimeGozarLastName()!=null && !svm.getBimeGozarLastName().isEmpty())
            {
                whereClause += "bgs.nameKhanevadegi like '%"+svm.getBimeGozarLastName()+"%' and ";
            }

            if (svm.getBimeGozarCodeMeliOrShenasaei() != null && !svm.getBimeGozarCodeMeliOrShenasaei().isEmpty())
            {
                whereClause += "bgs.kodeMelliShenasayi like '%" + svm.getBimeGozarCodeMeliOrShenasaei() + "%' and ";
            }

            if(svm.getBimeShodeFirstName()!=null && !svm.getBimeShodeFirstName().isEmpty())
            {
                whereClause += "bss.name like '%"+svm.getBimeShodeFirstName()+"%' and ";
            }

            if(svm.getBimeShodeLastName()!=null && !svm.getBimeShodeLastName().isEmpty())
            {
                whereClause += "bss.nameKhanevadegi like '%"+svm.getBimeShodeLastName()+"%' and ";
            }

            if(svm.getBimeShodeCodeMeliOrShenasaei()!=null && !svm.getBimeShodeCodeMeliOrShenasaei().isEmpty())
            {
                whereClause += "bss.kodeMelliShenasayi like '%"+svm.getBimeShodeCodeMeliOrShenasaei()+"%' and ";
            }


            if(svm.getShomareBimename()!=null && !svm.getShomareBimename().isEmpty())
            {
                whereClause += "b.shomare like '%"+svm.getShomareBimename()+"%' and ";
            }

            if(svm.getShomareElhaghie()!=null && !svm.getShomareElhaghie().isEmpty())
            {
                whereClause += "el.shomareElhaghiye like '%"+svm.getShomareElhaghie()+"%' and ";
            }

            if(svm.getFromTarikhSodurElhaghie()!=null && !svm.getFromTarikhSodurElhaghie().isEmpty())
            {
                whereClause += "el.createdDate>='"+svm.getFromTarikhSodurElhaghie()+"' and ";
            }

            if(svm.getToTarikhSodurElhaghie()!=null && !svm.getToTarikhSodurElhaghie().isEmpty())
            {
                whereClause += "el.createdDate<='"+svm.getToTarikhSodurElhaghie()+"' and ";
            }

            if(svm.getNamayandeId()!=null)
            {
                whereClause +="(dtn.id="+svm.getNamayandeId()+" or dbn.id=" + svm.getNamayandeId() + ") and ";
            }

            if(svm.getVahedSodurId()!=null)
            {
                whereClause +="(elUserCrtM.id="+svm.getVahedSodurId()+" or elUserCrtN.id=" + svm.getVahedSodurId() + ") and ";
            }

             if(svm.getElhaghiyeTypeStr()!=null && !svm.getElhaghiyeTypeStr().isEmpty())
            {
                whereClause += "el.elhaghiyeType='"+Elhaghiye.ElhaghiyeType.valueOf(svm.getElhaghiyeTypeStr())+"' and ";
            }

            if(svm.getNoeGharardad()!=null && !svm.getNoeGharardad().isEmpty())
            {
                whereClause+= "p.noeGharardad='"+svm.getNoeGharardad()+"' and ";
            }

            if(svm.getBimenameGroupId()!=null)
            {
                whereClause += "gh.id="+svm.getBimenameGroupId()+" and ";
            }

            if(svm.getTarhId()!=null && svm.getTarhId()>0)
            {
                whereClause += "t.id="+svm.getTarhId()+" and ";
            }

            if(svm.getUserSaderKonandeId()!=null)
            {
                whereClause +="elUserCrt.id="+svm.getUserSaderKonandeId()+" and ";
            }

        }

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }



        Query count = getSession().createQuery
        (
            "select count(el.id) " +
            "from Elhaghiye el join el.darkhast d left join d.darkhastBazkharid db left join d.darkhastTaghirat dt left join dt.namayande dtn left join db.namayande dbn " +
            "left join dbn.vahedSodur dbv join el.bimename b join b.state bstt join b.pishnehadList p join p.bimeShode bShode join p.bimeGozar bGozar join p.namayande pn " +
            "join bShode.shakhs bss join bGozar.shakhs bgs join p.estelam e left join p.tarh t left join p.gharardad gh left join db.karshenas dbk left join dt.karshenas dtk " +
            "left join el.vahedSodor elVahedSdr left join el.userSaderKonande elUserCrt left join elUserCrt.namayandegi elUserCrtN left join el.khesarat elkh " +
            "where p.valid=true and el.elhaghiyeType in (:elBazkharid,:elTaghirCode,:elTozih,:elTaghir,:elEbtal,:elKhesarat) and el.state.id=3001 and bstt.id!=:state_bme " + whereClause
        ).setParameter("elBazkharid", Elhaghiye.ElhaghiyeType.BAZKHARID)
         .setParameter("elTaghirCode", Elhaghiye.ElhaghiyeType.TAGHIRKOD)
         .setParameter("elTozih", Elhaghiye.ElhaghiyeType.TOZIH)
         .setParameter("elTaghir", Elhaghiye.ElhaghiyeType.TAGHYIRAT)
         .setParameter("elEbtal", Elhaghiye.ElhaghiyeType.EBTAL)
         .setParameter("elKhesarat", Elhaghiye.ElhaghiyeType.KHESARAT)
         .setParameter("state_bme", Constant.BIMENAME_LOCK_STATE)
                ;

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query = getSession().createQuery
        (
            "select new com.bitarts.parsian.viewModel.ElhaghieVM(elVahedSdr.name,elUserCrtN.name,elVahedSdr.kodeNamayandeKargozar,elUserCrtN.kodeNamayandeKargozar, elVahedSdr.id,elUserCrtN.id, pn.name,pn.name,pn.kodeNamayandeKargozar," +
            "pn.kodeNamayandeKargozar, pn.id,pn.id, el.shomareElhaghiye, el.radifElhaghiye, el.elhaghiyeType, b.shomare, gh.nameSherkat, bgs.name ||' '|| bgs.nameKhanevadegi , bss.name || ' ' || bss.nameKhanevadegi, " +
            "e.modat_bimename, e.sarmaye_paye_fot_long, e.sarmaye_paye_fot_dar_asar_hadese, e.sarmaye_pooshesh_amraz_khas, e.sarmaye_pooshesh_naghs_ozv, e.pooshesh_moafiat, e.nahve_pardakht_hagh_bime , " +
            "b.tarikhShorou, b.tarikhEngheza, b.tarikhSodour, bss.kodeMelliShenasayi, e.sen_bime_shode, el.mozoo, el.mablagh,el.createdDate,e.nerkh_afzayesh_salaneh_hagh_bime,e.nerkh_afzayesh_salaneh_sarmaye_fot,el.tarikhAsar," +
            "elUserCrt.firstName||' '||elUserCrt.lastName,elUserCrt.personalCode, p.options,elkh.khesaratType) " +
            "from Elhaghiye el join el.darkhast d left join d.darkhastBazkharid db left join d.darkhastTaghirat dt left join dt.namayande dtn left join db.namayande dbn " +
            "left join dbn.vahedSodur dbv join el.bimename b join b.state bstt join b.pishnehadList p join p.bimeShode bShode join p.bimeGozar bGozar join p.namayande pn " +
            "join bShode.shakhs bss join bGozar.shakhs bgs join p.estelam e left join p.tarh t left join p.gharardad gh left join db.karshenas dbk left join dt.karshenas dtk " +
            "left join el.vahedSodor elVahedSdr left join el.userSaderKonande elUserCrt left join elUserCrt.namayandegi elUserCrtN left join el.khesarat elkh " +
            "where p.valid=true and el.elhaghiyeType in (:elBazkharid,:elTaghirCode,:elTozih,:elTaghir,:elEbtal,:elKhesarat) and el.state.id=3001 and bstt.id!=:state_bme " + whereClause
             + " order by el.createdDate desc "
        ).setParameter("elBazkharid",Elhaghiye.ElhaghiyeType.BAZKHARID)
         .setParameter("elTaghirCode",Elhaghiye.ElhaghiyeType.TAGHIRKOD)
         .setParameter("elTozih",Elhaghiye.ElhaghiyeType.TOZIH)
         .setParameter("elTaghir",Elhaghiye.ElhaghiyeType.TAGHYIRAT)
         .setParameter("elEbtal",Elhaghiye.ElhaghiyeType.EBTAL)
        .setParameter("elKhesarat", Elhaghiye.ElhaghiyeType.KHESARAT)
        .setParameter("state_bme", Constant.BIMENAME_LOCK_STATE);

        query.setFirstResult(pgList.getObjectsPerPage()*pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
    }

    public PaginatedListImpl<Elhaghiye> findAllElhaghiyes(PaginatedListImpl<Elhaghiye> pgList,Long userId)
    {
        int pageNum=pgList.getPageNumber();
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Elhaghiye.class,"e");
        criteria.createCriteria("e.darkhast","d");
        criteria.createCriteria("d.darkhastTaghirat","dt",CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("d.darkhastBazkharid","db",CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("dt.creator","dtc",CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("db.creator","dbc",CriteriaSpecification.LEFT_JOIN);
        criteria.add
        (
            Restrictions.or
            (
                Restrictions.eq("dtc.id",userId),
                Restrictions.eq("dbc.id",userId)
            )
        );

        criteria.addOrder(Order.desc("shomareElhaghiye"));
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        pgList.setPageNumber(pageNum+1);
        return pgList;
//        return (List<Elhaghiye>) super.findAllWithOrderByDesc(Elhaghiye.class, "shomareElhaghiye");
    }

    public PaginatedListImpl<Elhaghiye> searchForElhaghiye(ElhaghiyeSearch elhaghiyeSearch, PaginatedListImpl<Elhaghiye> pgList,Long userId)
    {
        int pageNum=pgList.getPageNumber();
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Elhaghiye.class,"e");
        criteria.createCriteria("e.darkhast","d").createCriteria("d.darkhastTaghirat","dt").createCriteria("dt.creator","dtc");
        criteria.add(Restrictions.eq("dtc.id",userId));
        criteria.addOrder(Order.desc("shomareElhaghiye"));
        if(elhaghiyeSearch.getShomareElhaghiye()!=null && !elhaghiyeSearch.getShomareElhaghiye().isEmpty())
            criteria.add(Restrictions.like("e.shomareElhaghiye",elhaghiyeSearch.getShomareElhaghiye(),MatchMode.ANYWHERE));
//        if(elhaghiyeSearch.getAzCreatedDate()!=null && !elhaghiyeSearch.getAzCreatedDate().isEmpty())
//            criteria.add(Restrictions.ge("e.createdDate", elhaghiyeSearch.getAzCreatedDate()));
//        if(elhaghiyeSearch.getTaCreatedDate()!=null && !elhaghiyeSearch.getTaCreatedDate().isEmpty())
//            criteria.add(Restrictions.le("e.createdDate",elhaghiyeSearch.getTaCreatedDate()));
        if((elhaghiyeSearch.getAzTarikhDarkhast()!=null && !elhaghiyeSearch.getAzTarikhDarkhast().isEmpty()) || (elhaghiyeSearch.getTaTarikhDarkhast()!=null && !elhaghiyeSearch.getTaTarikhDarkhast().isEmpty()))
        {
            criteria.createCriteria("e.darkhast","d");
            if(elhaghiyeSearch.getAzTarikhDarkhast()!=null && !elhaghiyeSearch.getAzTarikhDarkhast().isEmpty())
                criteria.add(Restrictions.ge("d.darkhastDate",elhaghiyeSearch.getAzTarikhDarkhast()));
            if(elhaghiyeSearch.getTaTarikhDarkhast()!=null && !elhaghiyeSearch.getTaTarikhDarkhast().isEmpty())
                criteria.add(Restrictions.le("d.darkhastDate",elhaghiyeSearch.getTaTarikhDarkhast()));
        }
        if(elhaghiyeSearch.getAzTarikhAsar()!= null && !elhaghiyeSearch.getAzTarikhAsar().isEmpty())
            criteria.add(Restrictions.ge("e.tarikhAsar",elhaghiyeSearch.getAzTarikhAsar()));
        if(elhaghiyeSearch.getTaTarikhAsar()!= null && !elhaghiyeSearch.getTaTarikhAsar().isEmpty())
            criteria.add(Restrictions.le("e.tarikhAsar",elhaghiyeSearch.getTaTarikhAsar()));
        if(elhaghiyeSearch.getShomareBimename()!=null && !elhaghiyeSearch.getShomareBimename().isEmpty())
            criteria.createCriteria("e.bimename","b").add(Restrictions.like("b.shomare",elhaghiyeSearch.getShomareBimename(),MatchMode.ANYWHERE));
        criteria.addOrder(Order.desc("e.shomareElhaghiye"));
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
//        return criteria.list();
    }

    public Darkhast findDarkhastById(Integer id) {
        return (Darkhast) super.findById(Darkhast.class, id);
    }

    public void saveDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        super.save(darkhastTaghirat);
    }

    public void saveDarkhast(Darkhast darkhast) {
        super.saveOrUpdate(darkhast);
    }

    public List<DarkhastTaghirat> findAllDarkhastTaghir() {
        return (List<DarkhastTaghirat>) super.findByPropertyWithOrderByDesc(DarkhastTaghirat.class, "isArchive", "no", "tarikhDarkhast");
    }

    public DarkhastTaghirat findDarkhastTaghiratById(Integer id) {
        return (DarkhastTaghirat) super.findById(DarkhastTaghirat.class, id);
    }

    public void updateDarkhstTaghirat(DarkhastTaghirat theDarkhast) {
        super.update(theDarkhast);
    }

    public List findAllBimenameByFilterForZakhireTafkikGhest(String tarikhMabna, String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId) {
//        getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("ALTER SESSION SET optimizer_mode = First_Rows").executeUpdate();
//        getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("ALTER SESSION SET optimizer_mode = All_Rows").executeUpdate();
        String whereClause = "";
        if(!shomareBimename.isEmpty())
            whereClause = "and b.shomare like '%" + shomareBimename + "%' ";
        String whereClause2 = " and ((get_statechangedate(b.id) >= '" + tarikhMabna + "') or (b.state.id != 520 and b.state.id != 540 and b.state.id != 550 and get_statechangedate(b.id) < '" + tarikhMabna + "')) ";
        String selectHeader = "select new com.bitarts.parsian.viewModel.ZakhireRiaziVM" +
                "           ( " +
                "               t.name,p.noeGharardad,pg.nameSherkat,b.shomare,b.stateChangeDate,b.state.id,b.tarikhSodour,b.tarikhShorou,b.tarikhEngheza," +
                "               e.nerkh_afzayesh_salaneh_sarmaye_fot,e.nerkh_afzayesh_salaneh_hagh_bime,'1',c.id," +
                "               p.bimeGozar.shakhs.name||' '||p.bimeGozar.shakhs.nameKhanevadegi,p.bimeShode.shakhs.name||' '||p.bimeShode.shakhs.nameKhanevadegi," +
                "               p.bimeGozar.shakhs.kodeMelliShenasayi,p.bimeShode.shakhs.kodeMelliShenasayi,e.sen_bime_shode,e.modat_bimename," +
                "               e.nahve_pardakht_hagh_bime,e.sarmaye_paye_fot_long,e.sarmaye_paye_fot_dar_asar_hadese," +
                "               g.haghBimeFot_long," +
                "               g.haghBimePoosheshhayeEzafiLong," +
                "               g.hazineTaghsit,b.autoAndukhtGhati2,b.autoAndukhtGhati," +
                "               g.maliatLong,'0',b.autoAndukhtGhati,b.autoArzeshGhati,b.autoAndukhtAlalHesab,'0','0','" + tarikhMabna + "'," +
                "               g.hazineKarmonz," +
                "               g.hazineBimegari, " +
                "               g.hazineEdari, " +
                "               g.hazineVosoul,b ,ec.id ,ec" +
                "            ) ";
        if(onlyId) {
            selectHeader = "select new com.bitarts.parsian.viewModel.ZakhireRiaziVM" +
                    "           ( " +
                    "               b.id" +
                    "           ) ";
        }
        Query query = getSession().createQuery
                (
                        selectHeader +
                                " from Pishnehad p join p.bimename b join b.credebitList c join c.khateSanadsBedehi kb join kb.sanad kbs join c.ghest g left join p.bazarYab bry left join p.tarh t left join p.estelam e left join p.gharardad pg " +
                                " join kb.etebarCredebit ec " +
                                " where c.credebitType = 'GHEST' and (c.dateFish < '1393/01/01' or kbs.createdDate like '1392%') and c.amount_long != c.remainingAmount_long and p.valid = true and b.readyToShow = 'yes' and b.tarikhSodour >= :azTS and b.tarikhSodour <= :taTS " + whereClause + whereClause2
                )
                .setString("azTS", azTarikheSodoreBimename)
                .setString("taTS", taTarikheSodoreBimename);
        if (!isExport) {
            query.setFirstResult(0);
            query.setMaxResults(30);
        }
        return query.list();
    }

    public List findAllBimenameByFilterForZakhire(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId) {
        String whereClause = "";
        if(!shomareBimename.isEmpty())
            whereClause = "and b.shomare like '%" + shomareBimename + "%' ";
        String selectHeader = "select new com.bitarts.parsian.viewModel.ZakhireRiaziVM" +
                "           ( " +
                "               nOstan.cityName,nShahr.cityName,nVahedSodor.name,nVahedSodor.kodeNamayandeKargozar," +
                "               n.name,n.kodeNamayandeKargozar,bry.firstName||' '||bry.lastName,p.noeGharardad,t.name,b.shomare," +
                "               bgs.name||' '||bgs.nameKhanevadegi,bss.name||' '||bss.nameKhanevadegi," +
                "               bss.tarikheTavallod,b.tarikhSodour,b.tarikhShorou,b.tarikhEngheza,e.modat_bimename," +
                "               e.sarmaye_paye_fot_long,e.nahve_pardakht_hagh_bime,t.hijdahDarsad,b.autoAndukhtGhati,b.autoArzeshGhati," +
                "               bstt.id,b.stateChangeDate,b.id,1" +
                "           ) ";
        if(onlyId) {
            selectHeader = "select new com.bitarts.parsian.viewModel.ZakhireRiaziVM" +
                    "           ( " +
                    "               b.id" +
                    "           ) ";
        }
        Query query = getSession().createQuery
                (
                         selectHeader +
                                "from Pishnehad p left join p.bazarYab bry left join p.tarh t join p.namayande n left join n.ostan nOstan left join n.shahr nShahr " +
                                "left join n.vahedSodur nVahedSodor join p.bimename b join p.bimeGozar bg join bg.shakhs bgs join p.bimeShode bs join bs.shakhs bss " +
                                "join b.state bstt join p.estelam e " +
                                "where p.valid = true and b.readyToShow = 'yes' and b.tarikhSodour >= :azTS and b.tarikhSodour <= :taTS " + whereClause
                )
                .setString("azTS", azTarikheSodoreBimename)
                .setString("taTS", taTarikheSodoreBimename);
        if (!isExport) {
            query.setFirstResult(0);
            query.setMaxResults(30);
        }
        return query.list();
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
//                        .createCriteria(Bimename.class, "b").createCriteria("b.pishnehadList", "p").createCriteria("p.tarh", "t", CriteriaSpecification.LEFT_JOIN)
//                        .createCriteria("p.namayande", "n", CriteriaSpecification.LEFT_JOIN).createCriteria("p.namayande.vahedSodur", "nvs", CriteriaSpecification.LEFT_JOIN)
//                        .createCriteria("n.shahr", "ns", CriteriaSpecification.LEFT_JOIN).createCriteria("n.ostan", "no", CriteriaSpecification.LEFT_JOIN)
//                        .createCriteria("p.estelam", "es", CriteriaSpecification.LEFT_JOIN).createCriteria("p.bazarYab", "by", CriteriaSpecification.LEFT_JOIN)
//                        .createCriteria("p.bimeShode", "bs", CriteriaSpecification.LEFT_JOIN).createCriteria("bs.shakhs", "bss", CriteriaSpecification.LEFT_JOIN)
//                        .createCriteria("p.bimeGozar", "bg", CriteriaSpecification.LEFT_JOIN).createCriteria("bg.shakhs", "bgs", CriteriaSpecification.LEFT_JOIN)
//                        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                        .add(
//                                Restrictions.and(
//                                        Restrictions.le("b.tarikhSodour", taTarikheSodoreBimename),
//                                        Restrictions.ge("b.tarikhSodour", azTarikheSodoreBimename)
//                                )
//                        ).add(Restrictions.eq("b.readyToShow", "yes"));
//        if(onlyId) {
//            criteria.setProjection(Projections.property("b.id"));
//        }
//        if(hijdahDarsad) {
//            criteria.add(Restrictions.eq("t.hijdahDarsad", true));
//        } else {
//            criteria.add(Restrictions.or(Restrictions.eq("t.hijdahDarsad", false), Restrictions.isNull("t.id")));
//        }
//        if(!isExport) {
//            criteria.setFirstResult(0);
//            criteria.setMaxResults(30);
//        }
//        if(!shomareBimename.isEmpty())
//            criteria.add(Restrictions.ilike("b.shomare", shomareBimename, MatchMode.ANYWHERE));
//        return criteria.list();
    }

    public List<Bimename> findAllBimenameByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Bimename.class, "b").createCriteria("b.ghestBandiList", "gb").createCriteria("gb.ghestList", "g").createCriteria("g.credebitList", "c")
                .createCriteria("c.khateSanadsBedehi", "khb").createCriteria("khb.sanad", "s").createCriteria("khb.etebarCredebit", "ce")
                .createCriteria("b.pishnehadList", "p").createCriteria("p.tarh", "t", CriteriaSpecification.LEFT_JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(
                        Restrictions.and(
                                Restrictions.le("b.tarikhSodour", taTarikheSodoreBimename),
                                Restrictions.ge("b.tarikhSodour", azTarikheSodoreBimename)
                        )
                ).add(Restrictions.eq("b.readyToShow", "yes")).add(Restrictions.eq("p.valid", true));
        if (!shomareBimename.isEmpty())
            criteria.add(Restrictions.ilike("b.shomare", shomareBimename, MatchMode.ANYWHERE));
        return criteria.list();
    }



    public PaginatedListImpl makeGozareshZakhireRiazi(PaginatedListImpl pgList,ZakhireRiazi zr)
    {
        String whereClause="";

        //todo insert clause in the whereClause variable here. . .

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Long count= (Long)
                getSession().createQuery
                (
                    "select count(p.id) " +
                    "from Pishnehad p left join p.bazarYab bry left join p.tarh t " +
                    "where p.valid = true and p.bimename.readyToShow = 'yes' and p.bimename.tarikhSodour >= :azTS and p.bimename.tarikhSodour < :taTS " + whereClause
                ).setString("azTS",zr.getAzTarikh()).setString("taTS",zr.getTaTarikh()).uniqueResult();

        Query query=getSession().createQuery
                (
                    "select new com.bitarts.parsian.viewModel.ZakhireRiaziVM" +
                    "           ( " +
                    "               p.namayande.ostan.cityName,p.namayande.shahr.cityName,p.namayande.vahedSodur.name,p.namayande.vahedSodur.kodeNamayandeKargozar," +
                    "               p.namayande.name,p.namayande.kodeNamayandeKargozar,bry.firstName||' '||bry.lastName,p.noeGharardad,t.name,p.bimename.shomare," +
                    "               p.bimeGozar.shakhs.name||' '||p.bimeGozar.shakhs.nameKhanevadegi,p.bimeShode.shakhs.name||' '||p.bimeShode.shakhs.nameKhanevadegi," +
                    "               p.bimeShode.shakhs.tarikheTavallod,p.bimename.tarikhSodour,p.bimename.tarikhShorou,p.bimename.tarikhEngheza,p.estelam.modat_bimename," +
                    "               p.estelam.sarmaye_paye_fot_long,p.estelam.nahve_pardakht_hagh_bime" +
                    "           ) " +
                    "from Pishnehad p left join p.bazarYab bry left join p.tarh t " +
                    "where p.valid = true and p.bimename.readyToShow = 'yes' and p.bimename.tarikhSodour >= :azTS and p.bimename.tarikhSodour < :taTS " + whereClause
                )
                .setString("azTS",zr.getAzTarikh())
                 .setString("taTS",zr.getTaTarikh())
                 .setFirstResult(pgList.getPageNumber()-1)
                 .setMaxResults(pgList.getObjectsPerPage());
        pgList.setFullListSize(count.intValue());
        pgList.setList(query.list());
        return pgList;
    }

    public List<Ghest> findAllBimenameGhestByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Ghest.class, "g")
                .createCriteria("g.ghestBandi", "gb")
                .createCriteria("gb.bimename", "b")
                .createCriteria("g.credebitList", "cr")
                .createCriteria("cr.khateSanadsBedehi", "khB")
                .add(
                        Restrictions.and(
                                Restrictions.lt("b.tarikhSodour", taTarikheSodoreBimename),
                                Restrictions.ge("b.tarikhSodour", azTarikheSodoreBimename)
                        )
                )
                .add(Restrictions.eq("b.readyToShow", "yes"))
                .addOrder(Order.asc("b.shomare")).addOrder(Order.asc("g.sarresidDate"))
                .list();
    }

    public List<Credebit> findForMabaleghePardakhti(Bimename bimename, String azTarikh, String taTarikh) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final Criteria criteria = session.createCriteria(Credebit.class, "c").createCriteria("pishnehad", "p");
        return criteria.createCriteria("c.khateSanadsBedehi", "k").createCriteria("k.sanad", "s").add(
                Restrictions.and(
                        Restrictions.lt("s.createdDate", taTarikh),
                        Restrictions.ge("s.createdDate", azTarikh)
                )).createCriteria("p.bimename", "b").add(Restrictions.eq("b.id", bimename.getId())).list();
    }

    public List<BimenameForGozaresh> findByBimenameGozareshFilter(BimenameForGozaresh bimenamehSearch) {

        String whereClause = "";
        final Session session = getSession();
        if( bimenamehSearch.getSalBimeei()!=null && !bimenamehSearch.getSalBimeei().isEmpty())
        {
            whereClause += "gstb.saleBimei = " + bimenamehSearch.getSalBimeei() + " and ";
        }

        if (!bimenamehSearch.getOstan().isEmpty()) {
            if(!bimenamehSearch.getShahr().isEmpty() && !bimenamehSearch.getShahr().isEmpty())
            {
                whereClause += "no.cityId = " + bimenamehSearch.getOstanId() + " and ";
                whereClause += "ns.cityId = " + bimenamehSearch.getShahrId() + " and ";
            }
            else
            {
                whereClause += "no.cityId = " + bimenamehSearch.getOstanId() + " and ";
            }
        }
        if (!bimenamehSearch.getVahedSodur().isEmpty()) {
            whereClause += "nv.id = " + bimenamehSearch.getVahedSodurId() + " and ";
        }
        if (!bimenamehSearch.getNamayandegi().isEmpty()) {
            whereClause += "n.id = " + bimenamehSearch.getNamayandegiId() + " and ";
        }
        if (!bimenamehSearch.getNoeGharardad().isEmpty()) {
            whereClause += "p.noeGharardad = '" + bimenamehSearch.getNoeGharardad() + "' and ";
        }
        if (bimenamehSearch.getTarh().getId() != null) {
            whereClause += "t.id = " + bimenamehSearch.getTarh().getId() + " and ";
        }
        if(bimenamehSearch.getRaveshPardakhtBimename()!=null && !bimenamehSearch.getRaveshPardakhtBimename().isEmpty())
        {
            whereClause += "es.nahve_pardakht_hagh_bime = '" + bimenamehSearch.getRaveshPardakhtBimename() + "' and ";
        }
        if(bimenamehSearch.getAzTarikhSodor()!=null && !bimenamehSearch.getAzTarikhSodor().isEmpty() )
        {
            whereClause += "gstbb.tarikhSodour >= '" + bimenamehSearch.getAzTarikhSodor() + "' and ";
        }
        if(bimenamehSearch.getTaTarikhSodor()!=null && !bimenamehSearch.getTaTarikhSodor().isEmpty() )
        {
            whereClause += "gstbb.tarikhSodour <= '" + bimenamehSearch.getTaTarikhSodor() + "' and ";
        }

        if(bimenamehSearch.getGroupId()!=null)
        {
            whereClause += "pgh.id = " + bimenamehSearch.getGroupId() + " and ";
        }

        if(bimenamehSearch.getAzTarikhSodor()!=null && !bimenamehSearch.getAzTarikhSodor().isEmpty())
        {
            whereClause += "gstbb.tarikhSodour >='" + bimenamehSearch.getAzTarikhSodor() + "' and ";
        }

        if(bimenamehSearch.getTaTarikhSodor()!=null && !bimenamehSearch.getTaTarikhSodor().isEmpty())
        {
            whereClause += "gstbb.tarikhSodour <='" + bimenamehSearch.getTaTarikhSodor() + "' and ";
        }

        if(bimenamehSearch.getAzTarikhSanad()!=null && !bimenamehSearch.getAzTarikhSanad().isEmpty())
        {
            whereClause += "s.createdDate >='" + bimenamehSearch.getAzTarikhSanad() + "' and ";
        }

        if(bimenamehSearch.getTaTarikhSanad()!=null && !bimenamehSearch.getTaTarikhSanad().isEmpty())
        {
            whereClause += "s.createdDate <='" + bimenamehSearch.getTaTarikhSanad() + "' and ";
        }

        if(bimenamehSearch.getTaTarikhPardakht()!=null && !bimenamehSearch.getTaTarikhPardakht().isEmpty())
        {
            whereClause += "c.dateFish <='"+ bimenamehSearch.getTaTarikhPardakht() +"' and ";
        }

        if(bimenamehSearch.getAzTarikhPardakht()!=null && !bimenamehSearch.getAzTarikhPardakht().isEmpty())
        {
            whereClause += "c.dateFish >='"+ bimenamehSearch.getAzTarikhPardakht() +"' and ";
        }

        if(whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }


        List<BimenameForGozaresh> gozaresh = session.createQuery("select new com.bitarts.parsian.viewModel.BimenameForGozaresh(no.cityName,ns.cityName,nv.name,nv.kodeNamayandeKargozar," +
                "n.name,n.kodeNamayandeKargozar,gstbb.shomare,gstbb.tarikhSodour,gstb.saleBimei,bgs.name||' '||bgs.nameKhanevadegi,bss.name||' '||bss.nameKhanevadegi,gstbb.tarikhShorou,gstbb.tarikhEngheza," +
                "es.modat_bimename,khg.maliatLong,es.sarmaye_paye_fot_long,es.nahve_pardakht_hagh_bime,p.noeGharardad,t.name,kheb.amount_long,khg.sarresidDate," +
                "khe.amount,s.createdDate,c.dateFish,s.shomare,gstbb.id,khg.haghBimePoosheshhayeEzafiLong,khg.haghBimeFot_long,pgh.nameSherkat,np.name, np.kodeNamayandeKargozar, p.options " +
                ",primitiveEstelam.hagh_bime_pardakhti,primitiveEstelam.sarmaye_paye_fot_long,primitiveEstelam.nahve_pardakht_hagh_bime,primitiveEstelam.mablagh_seporde_ebtedaye_sal,khg.number, bry.firstName||' '||bry.lastName||' - '||bry.personalCode) " +
                "from KhateSanad khe join khe.sanad s join khe.etebarCredebit c " +
                "join khe.bedehiCredebit kheb join  kheb.ghest khg join khg.ghestBandi gstb join gstb.bimename gstbb join gstbb.primitivePishnehad primitivePishnehad " +
                "join primitivePishnehad.estelam primitiveEstelam join gstbb.ghestBandiList gb1 join gstbb.pishnehadList p " +
                "join p.namayande n join p.bimeGozar bg join p.bimeShode bs join bg.shakhs bgs join bs.shakhs bss join n.shahr ns join n.ostan no " +
                "left join p.karshenas kar left join p.bazarYab bry left join kar.mojtamaSodoor nv join p.estelam es left join p.tarh t left join p.gharardad pgh left join p.namayandePoshtiban np " +
                "where p.valid = true and gstbb.readyToShow='yes' and gstb.type=:gbType and gb1.type=:gbType and gb1.saleBimei=:zero and c.credebitType!=:elhaghie " + whereClause +
                " order by gstbb.shomare desc").setParameter("gbType", GhestBandi.Type.G_BIMENAME).setString("zero", "0").setParameter("elhaghie",Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)
                .list();

        return gozaresh;
    }

    public List<Bimename> findByBimenameBazkharidShode(BimenameForGozaresh bimenamehSearch) {
        String hql = "";
        String whereClause = "";
        final Session session = getSession();
        if(bimenamehSearch.getShomareBimename()!=null && !bimenamehSearch.getShomareBimename().isEmpty())
        {
            whereClause +=" b.shomare like '%"+bimenamehSearch.getShomareBimename()+ "%' and ";
        }

        if(bimenamehSearch.getNameBimeShode()!=null && !bimenamehSearch.getNameBimeShode().isEmpty())
        {
            hql = "select b from Pishnehad p join p.bimename b join p.bimeShode bs join bs.shakhs sbs ";
            whereClause +=" concat(sbs.name,sbs.nameKhanevadegi) like '%"+bimenamehSearch.getNameBimeShode()+ "%' and ";
        }

        if(bimenamehSearch.getNameBimeGozar()!=null && !bimenamehSearch.getNameBimeGozar().isEmpty())
        {
            hql = "select b from Pishnehad p join p.bimename b join p.bimeGozar bg join bg.shakhs sbg join b.elhaghiyeha bel ";
            whereClause +=" concat(sbg.name,sbg.nameKhanevadegi) like '%"+bimenamehSearch.getNameBimeGozar()+ "%' and ";
        }

        if(bimenamehSearch.getCodeMeliBimegozar()!=null && !bimenamehSearch.getCodeMeliBimegozar().isEmpty())
        {
            hql = "select b from Pishnehad p join p.bimename b join p.bimeGozar bg join bg.shakhs sbg join b.elhaghiyeha bel  ";
            whereClause +=" sbg.kodeMelliShenasayi like '%"+bimenamehSearch.getCodeMeliBimegozar()+ "%' and ";
        }

        if (bimenamehSearch.getNameBimeShode()!=null && !bimenamehSearch.getNameBimeShode().isEmpty() && bimenamehSearch.getNameBimeGozar()!=null && !bimenamehSearch.getNameBimeGozar().isEmpty()){
            hql = "select b from Pishnehad p join p.bimename b join p.bimeShode bs join p.bimeGozar bg join bs.shakhs sbs  join bg.shakhs sbg join b.elhaghiyeha bel ";
        }

        if(bimenamehSearch.getAzTarikhSodor()!=null && !bimenamehSearch.getAzTarikhSodor().isEmpty())
        {
            whereClause += " b.tarikhSodour >='" + bimenamehSearch.getAzTarikhSodor() + "' and ";
        }

        if(bimenamehSearch.getTaTarikhSodor()!=null && !bimenamehSearch.getTaTarikhSodor().isEmpty())
        {
            whereClause += " b.tarikhSodour <='" + bimenamehSearch.getTaTarikhSodor() + "' and ";
        }

        if(bimenamehSearch.getAzTarikhSodorElhaghiye()!=null && !bimenamehSearch.getAzTarikhSodorElhaghiye().isEmpty())
        {
            whereClause += " bel.createdDate >='" + bimenamehSearch.getAzTarikhSodorElhaghiye() + "' and ";
        }

        if(bimenamehSearch.getTaTarikhSodorElhaghiye()!=null && !bimenamehSearch.getTaTarikhSodorElhaghiye().isEmpty())
        {
            whereClause += " bel.createdDate <='" + bimenamehSearch.getTaTarikhSodorElhaghiye() + "' and ";
        }

        if(bimenamehSearch.getNamayandegiId()!=null && bimenamehSearch.getNamayandegiId()>0)
        {
            whereClause += " p.namayande.id ='" + bimenamehSearch.getNamayandegiId() + "' and ";
        }

        if(whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        if (hql.equals("")){
            hql = " select b from Pishnehad p join p.bimename b join b.elhaghiyeha bel ";
        }
        List<Bimename> gozaresh = session.createQuery(hql +
                " where bel.darkhastBazkharid is not null and p.valid = true and b.readyToShow='yes' and bel.elhaghiyeType = '" + Elhaghiye.ElhaghiyeType.BAZKHARID +"' and b.state.id = " + Constant.BIMENAME_BAZKHARID + " " + whereClause )
                .list();
        return gozaresh;
    }

    public List<Bimename> findByBimenameSarresidShode(BimenameForGozaresh bimenamehSearch) {
        String hql = "";
        String whereClause = "";
        final Session session = getSession();

        if(bimenamehSearch.getVahedSodurId()!=null && bimenamehSearch.getVahedSodurId()>0)
            whereClause +=" b.vahedSodor.id = '"+bimenamehSearch.getVahedSodurId()+ "' and ";

        if(bimenamehSearch.getNamayandegiId()!=null && bimenamehSearch.getNamayandegiId()>0)
            whereClause += " p.namayande.id ='" + bimenamehSearch.getNamayandegiId() + "' and ";

        if (bimenamehSearch.getNoeGharardad()!=null && !bimenamehSearch.getNoeGharardad().isEmpty())
            whereClause += " p.noeGharardad = '" + bimenamehSearch.getNoeGharardad() + "' and ";

        if(bimenamehSearch.getAzTarikhSodor()!=null && !bimenamehSearch.getAzTarikhSodor().isEmpty())
            whereClause += " b.tarikhSodour >='" + bimenamehSearch.getAzTarikhSodor() + "' and ";

        if(bimenamehSearch.getTaTarikhSodor()!=null && !bimenamehSearch.getTaTarikhSodor().isEmpty())
            whereClause += " b.tarikhSodour <='" + bimenamehSearch.getTaTarikhSodor() + "' and ";

        if(bimenamehSearch.getTarh().getId()!=null && bimenamehSearch.getTarh().getId()>0)
            whereClause += " p.tarh.id = "+ bimenamehSearch.getTarh().getId() + " and " ;

        if(bimenamehSearch.getGroupId()!=null && bimenamehSearch.getGroupId()>0)
            whereClause += " p.gharardad is not null and p.gharardad.id = "+ bimenamehSearch.getGroupId() + " and " ;

        if(bimenamehSearch.getAzTarikhEngheza()!=null && !bimenamehSearch.getAzTarikhEngheza().isEmpty())
            whereClause += " b.tarikhEngheza >='" + bimenamehSearch.getAzTarikhEngheza() + "' and ";

        if(bimenamehSearch.getTaTarikhEngheza()!=null && !bimenamehSearch.getTaTarikhEngheza().isEmpty())
            whereClause += " b.tarikhEngheza <='" + bimenamehSearch.getTaTarikhEngheza() + "' and ";

        if(whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        if (hql.equals("")){
            hql = " select b from Pishnehad p join p.bimename b ";
        }
        List<Bimename> gozaresh = session.createQuery(hql +
                " where p.valid = true and b.readyToShow='yes' " + whereClause )
                .list();
        return gozaresh;
    }

    public List<DarkhastTaghirat> findAllUnfinishedDarkhastTaghir() {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final Criteria c = session.createCriteria(DarkhastTaghirat.class);
        c.add(Restrictions.or(
                Property.forName("finished").eq(false), Property.forName("finished").isNull()));
        List<DarkhastTaghirat> darkhastList = (List<DarkhastTaghirat>) c.list();
        Set<DarkhastTaghirat> darkhastSet = new HashSet<DarkhastTaghirat>();
        for (DarkhastTaghirat darkhastTaghirat : darkhastList) {
            darkhastSet.add(darkhastTaghirat);
        }
        darkhastList = new ArrayList<DarkhastTaghirat>();
        for (DarkhastTaghirat darkhastTaghirat : darkhastSet) {
            darkhastList.add(darkhastTaghirat);
        }
        return darkhastList;
    }

    public PaginatedListImpl<DarkhastsVM> myDarkhasts(PaginatedListImpl<DarkhastsVM> pgList,DarkhastsVM vms)
    {

        String bzkrdOrTgr = "";
        String fromBodyTgr = "";
        String fromBodyBzk = "";
        String fromBody = "";

        String whereClauseTgr = "";
        String whereClauseBzk = "";
        String whereClause = "";
        String selectBody = "";

        final String LEFT_JOIN = " left join ";
        final String JOIN = " join ";

        String joinType = LEFT_JOIN;
        if (!vms.getDarkhastTypeStr().equals("ALL"))
            joinType = JOIN;





        fromBodyTgr +=  joinType + " d.darkhastTaghirat dt " +
                        joinType + " dt.state dts " +
//                        joinType + " dts.transitionBegin dtst " +
//                        joinType + " dtst.roles dtstr " +
//                        joinType + " dtstr.users dtstrUser " +
                        LEFT_JOIN+ " dts.peygirKonande dtsp "+
                        joinType + " dt.oldPishnehad dtPishnehad " +
                        joinType + " dtPishnehad.bimename dtBimename " +
                        joinType + " dt.creator dtc " +
                        LEFT_JOIN+ " dt.karshenas dtk " +
                        joinType + " dtPishnehad.bimeGozar dtBimeGzr " +
                        joinType + " dtBimeGzr.shakhs dtbgShakhs " +
                        joinType + " dt.namayande dtn "+
                        joinType + " dtPishnehad.bimeShode dtBimeShde " +
                        joinType + " dtBimeShde.shakhs dtbsShakhs "
        ;

        fromBodyBzk +=  joinType + " d.darkhastBazkharid db " +
                        joinType + " db.state dbs " +
//                        joinType + " dbs.transitionBegin dbst " +
//                        joinType + " dbst.roles dbstr " +
//                        joinType + " dbstr.users dbstrUser " +
                        LEFT_JOIN+ " dbs.peygirKonande dbsp " +
                        joinType + " db.bimename dbBimename " +
                        joinType + " dbBimename.pishnehadSet dbPishnehad " +
                        joinType + " db.creator dbc " +
                        LEFT_JOIN+ " db.karshenas dbk " +
                        LEFT_JOIN+ " db.karshenasKhesarat dbkkh " +
                        joinType + " dbPishnehad.bimeGozar dbBimeGzr " +
                        joinType + " dbBimeGzr.shakhs dbbgShakhs " +
                        joinType + " db.namayande dbn "+
                        joinType + " dbPishnehad.bimeShode dbBimeShde " +
                        joinType + " dbBimeShde.shakhs dbbsShakhs "
        ;

        whereClauseTgr += " dts.id is not null and " +
                          " dts.id in (select t1.stateBegin.id from Transition t1 join t1.roles r1 where r1.id in (:rolesParam)) and ";
//                          " dtstrUser.id="+vms.getUser().getId()+" and " ;
        whereClauseBzk += " dbs.id is not null and " +
                          " dbs.id in (select t2.stateBegin.id from Transition t2 join t2.roles r2 where  r2.id in (:rolesParam)) and  " +
//                          " dbstrUser.id="+vms.getUser().getId()+" and " +
                          " dbPishnehad.valid=true and " ;
        Set<Role> theRoles = vms.getUser().getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClauseTgr +=" dtn.id="+vms.getUser().getNamayandegi().getId()+" and ";
                whereClauseBzk +=" dbn.id="+vms.getUser().getNamayandegi().getId()+" and ";
            }

            //khesarat
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_KHESARAT"))
            {
                whereClauseBzk += " (dbkkh.id=" + vms.getUser().getId() +" or dbc.id=" +vms.getUser().getId()+") and ";
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_KHESARAT"))
            {}
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL_KHESARAT"))
            {}

            //Sodor
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR"))
            {
                whereClauseTgr += " (dtk.id=" + vms.getUser().getId() + " or dtc.id=" + vms.getUser().getId() + " ) and ";
                whereClauseBzk += " (dbk.id=" + vms.getUser().getId() + " or dbc.id=" + vms.getUser().getId() + " ) and ";
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR"))
            {
                whereClauseTgr += " (dtk.id=" + vms.getUser().getId() + " or dtc.id=" + vms.getUser().getId() + " ) and ";
                whereClauseBzk += " (dbk.id=" + vms.getUser().getId() + " or dbc.id=" + vms.getUser().getId() + " ) and ";
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL"))
            {
                whereClauseTgr += " (dtk.id=" + vms.getUser().getId() + " or dtc.id=" + vms.getUser().getId() + " ) and ";
                whereClauseBzk += " (dbk.id=" + vms.getUser().getId() + " or dbc.id=" + vms.getUser().getId() + " ) and ";
            }

            //Pas az sodor
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS"))
            {
                whereClauseTgr += " (dtk.id=" + vms.getUser().getId() + " or dtc.id=" + vms.getUser().getId() + " ) and ";
                whereClauseBzk += " (dbk.id=" + vms.getUser().getId() + " or dbc.id=" + vms.getUser().getId() + " ) and ";
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL"))
            {
                whereClauseBzk +=   " ( " +
                                    "    (db.darkhastType!='" + DarkhastBazkharid.DarkhastType.KHESARAT + "') or " +
                                    "    ((dbs.id=652 or dbs.id=657 or dbs.id=654 or dbs.id=658) and db.darkhastType='" + DarkhastBazkharid.DarkhastType.KHESARAT + "') " +
                                    " ) and " ;
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                whereClauseBzk +=   " ( " +
                                    "    (db.darkhastType!='" + DarkhastBazkharid.DarkhastType.KHESARAT + "') or " +
                                    "    ((dbs.id=652 or dbs.id=657 or dbs.id=654 or dbs.id=658) and db.darkhastType='" + DarkhastBazkharid.DarkhastType.KHESARAT + "') " +
                                    " ) and ";

            }
        }

        if(vms.getShomareBimename() != null && !vms.getShomareBimename().isEmpty())
        {
            whereClauseTgr += " dtBimename.shomare like '%" +vms.getShomareBimename()+ "%' and ";
            whereClauseBzk += " dbBimename.shomare like '%" + vms.getShomareBimename() + "%' and ";
        }

        if (vms.getBimeGozarCodeMelli() != null && !vms.getBimeGozarCodeMelli().isEmpty())
        {
            whereClauseTgr += " dtbgShakhs.kodeMelliShenasayi like '%" +vms.getBimeGozarCodeMelli()+ "%' and ";
            whereClauseBzk += " dbbgShakhs.kodeMelliShenasayi like '%" +vms.getBimeGozarCodeMelli()+ "%' and ";
        }

        if (vms.getBimeGozrLastName() != null && !vms.getBimeGozrLastName().isEmpty())
        {
            whereClauseTgr += " dtbgShakhs.nameKhanevadegi like '%" +vms.getBimeGozrLastName()+ "%' and ";
            whereClauseBzk += " dbbgShakhs.nameKhanevadegi like '%" +vms.getBimeGozrLastName()+ "%' and ";
        }

        if (vms.getBimeGozarFirstName() != null && !vms.getBimeGozarFirstName().isEmpty())
        {
            whereClauseTgr += " dtbgShakhs.name like '%" +vms.getBimeGozarFirstName()+ "%' and ";
            whereClauseBzk += " dbbgShakhs.name like '%" +vms.getBimeGozarFirstName()+ "%' and ";
        }

        if (vms.getBimeShodeCodeMelli() != null && !vms.getBimeShodeCodeMelli().isEmpty())
        {
            whereClauseTgr += " dtbsShakhs.kodeMelliShenasayi like '%" +vms.getBimeShodeCodeMelli()+ "%' and ";
            whereClauseBzk += " dbbsShakhs.kodeMelliShenasayi like '%" +vms.getBimeShodeCodeMelli()+ "%' and ";
        }

        if (vms.getBimeShodeLastName() != null && !vms.getBimeShodeLastName().isEmpty())
        {
            whereClauseTgr += " dtbsShakhs.nameKhanevadegi like '%" +vms.getBimeShodeLastName()+ "%' and ";
            whereClauseBzk += " dbbsShakhs.nameKhanevadegi like '%" +vms.getBimeShodeLastName()+ "%' and ";
        }

        if (vms.getBimeShodeFirstName() != null && !vms.getBimeShodeFirstName().isEmpty())
        {
            whereClauseTgr += " dtbsShakhs.name like '%" +vms.getBimeShodeFirstName()+ "%' and ";
            whereClauseBzk += " dbbsShakhs.name like '%" +vms.getBimeShodeFirstName()+ "%' and ";
        }


        if(vms.getNamayandeId()!=null && vms.getNamayandeId()>0)
        {
            whereClauseTgr += " dtn.id=" + vms.getNamayandeId() + " and ";
            whereClauseBzk += " dbn.id=" + vms.getNamayandeId() + " and ";
        }

        if(vms.getKarshenasFirstName()!=null && !vms.getKarshenasFirstName().isEmpty())
        {
            whereClauseTgr += " dtk.firstName like '%" + vms.getKarshenasFirstName() + "%' and ";
            whereClauseBzk += " (dbkkh.firstName like '%" + vms.getKarshenasFirstName() + "%' or dbkkh.firstName like '%" + vms.getKarshenasFirstName() + "%') and ";
        }

        if (vms.getKarshenasLastName() != null && !vms.getKarshenasLastName().isEmpty())
        {
            whereClauseTgr += " dtk.lastName like '%" + vms.getKarshenasFirstName() + "%' and ";
            whereClauseBzk += " (dbkkh.lastName like '%" + vms.getKarshenasFirstName() + "%' or dbkkh.lastName like '%" + vms.getKarshenasFirstName() + "%') and ";
        }

        if (vms.getAzTarikheDarkhast() != null && !vms.getAzTarikheDarkhast().isEmpty())
        {
            whereClauseTgr +=" dt.tarikhDarkhast>='"+vms.getAzTarikheDarkhast()+"' and ";
            whereClauseBzk +=" db.tarikhDarkhast>='"+vms.getAzTarikheDarkhast()+"' and ";
        }

        if (vms.getTaTarikheDarkhast() != null && !vms.getTaTarikheDarkhast().isEmpty())
        {
            whereClauseTgr +=" dt.tarikhDarkhast<='"+vms.getTaTarikheDarkhast()+"' and ";
            whereClauseBzk +=" db.tarikhDarkhast<='"+vms.getTaTarikheDarkhast()+"' and ";
        }


        if ((vms.getDarkhastStates() != null && vms.getDarkhastStates().size() != 0) && !vms.getDarkhastStates().get(0).equals("-"))
        {
            whereClauseTgr += " dts.id=" + Integer.parseInt(vms.getDarkhastStates().get(0)) + " and ";
            int i=0;
            for(String state : vms.getDarkhastStates())
            {
                if(i==0)
                    whereClauseBzk += " (dbs.id=" + Integer.parseInt(state) + " ";
                else
                    whereClauseBzk += " or dbs.id=" + Integer.parseInt(state) + " ";
                i++;
            }
            whereClauseBzk += " ) and " ;
        }

        if (!vms.getDarkhastTypeStr().equals("TAGHYIRAT") && !vms.getDarkhastTypeStr().equals("ALL"))
        {
            bzkrdOrTgr = "db";
            whereClauseBzk += " d.darkhastType='" + Darkhast.DarkhastType.valueOf(vms.getDarkhastTypeStr()) + "' ";
            whereClause += whereClauseBzk;
            fromBody+=fromBodyBzk;
            selectBody +=   "'', dbsp.roleColor, '', db.tarikhDarkhast, " +
                            "'',dbBimename.shomare, '', dbn.name, '',dbn.kodeNamayandeKargozar, " +
                            "d.darkhastType,'', dbbgShakhs.name," +
                            "'',dbbgShakhs.nameKhanevadegi," +
                            "'',dbbsShakhs.name," +
                            "'',dbbsShakhs.nameKhanevadegi," +
                            "'', dbk.firstName, '', dbk.lastName, '', dbc.firstName, '', dbc.lastName, " +
                            "'' ," +
                            "(select max(t2.date) as bazkharidTransitionLogDate from TransitionLog t2 join t2.darkhastBazkharid db1 where db1.id = db.id) , '', dbs.stateName, 0, db.id, dbkkh.firstName, dbkkh.lastName"
            ;
        }

        if (vms.getDarkhastTypeStr().equals("TAGHYIRAT"))
        {
            bzkrdOrTgr = "dt";
            whereClauseTgr += " d.darkhastType='" + Darkhast.DarkhastType.valueOf(vms.getDarkhastTypeStr()) + "' ";
            whereClause+=whereClauseTgr;
            fromBody += fromBodyTgr;
            selectBody +=   "dtsp.roleColor, ''," +
                            " dt.tarikhDarkhast, '', " +
                            "dtBimename.shomare,'', " +
                            "dtn.name, '', " +
                            "dtn.kodeNamayandeKargozar,'', " +
                            "d.darkhastType, " +
                            "dtbgShakhs.name, '', " +
                            "dtbgShakhs.nameKhanevadegi,'', " +
                            "dtbsShakhs.name,'', " +
                            "dtbsShakhs.nameKhanevadegi,'', " +
                            "dtk.firstName, '', " +
                            "dtk.lastName, '', " +
                            "dtc.firstName, '', " +
                            "dtc.lastName, '', " +
                            "(select max(t1.date) as taghirTransitionLogDate from TransitionLog t1 join t1.darkhastTaghirat dt1  where dt1.id = dt.id) ,'', " +
                            "dts.stateName, '', " +
                            "dt.id, 0, " +
                            "'', ''"
            ;
        }

        if (vms.getDarkhastTypeStr().equals("ALL"))
        {
            bzkrdOrTgr = "all";
            whereClauseBzk=whereClauseBzk.substring(0, whereClauseBzk.length() - 5);
            whereClauseTgr=whereClauseTgr.substring(0, whereClauseTgr.length() - 5);
            whereClause+=" ( " + whereClauseTgr + " ) or ( " + whereClauseBzk + " ) ";
            fromBody += fromBodyBzk + fromBodyTgr;

            selectBody+=    "dtsp.roleColor, dbsp.roleColor, dt.tarikhDarkhast, db.tarikhDarkhast, " +
                            "dtBimename.shomare,dbBimename.shomare, dtn.name, dbn.name, dtn.kodeNamayandeKargozar,dbn.kodeNamayandeKargozar, " +
                            "d.darkhastType,dtbgShakhs.name, dbbgShakhs.name," +
                            "dtbgShakhs.nameKhanevadegi,dbbgShakhs.nameKhanevadegi," +
                            "dtbsShakhs.name,dbbsShakhs.name," +
                            "dtbsShakhs.nameKhanevadegi,dbbsShakhs.nameKhanevadegi," +
                            "dtk.firstName, dbk.firstName, dtk.lastName, dbk.lastName, dtc.firstName, dbc.firstName, dtc.lastName, dbc.lastName, " +
                            "(select max(t1.date) as taghirTransitionLogDate from TransitionLog t1 join t1.darkhastTaghirat dt1  where dt1.id = dt.id) ," +
                            "(select max(t2.date) as bazkharidTransitionLogDate from TransitionLog t2 join t2.darkhastBazkharid db1 where db1.id = db.id) , dts.stateName, dbs.stateName, dt.id, db.id, dbkkh.firstName, dbkkh.lastName"
            ;
        }

        Query count = getSession().createQuery("select count(d.id) from Darkhast d " + fromBody + " where " + whereClause).setParameterList("rolesParam", vms.getUser().getRolesId());

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery("select  new com.bitarts.parsian.viewModel.DarkhastsVM( d.id," + selectBody + ") from Darkhast d  " + fromBody + " where " + whereClause).setParameterList("rolesParam",vms.getUser().getRolesId());

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);

        return pgList;
    }
    public PaginatedListImpl<Darkhast> searchDarkhasts(PaginatedListImpl pgList,User user,String darkhastType ,String shomareBimename, Long namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli,List<String> darkhastState)
    {
        DetachedCriteria criteria=DetachedCriteria.forClass(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        int specification = CriteriaSpecification.LEFT_JOIN;
        String  bzkrdOrTgr = "";
        if (!darkhastType.equals("ALL"))
            specification = CriteriaSpecification.INNER_JOIN;

        if (!darkhastType.equals("TAGHIRAT") && !darkhastType.equals("ALL"))
            bzkrdOrTgr = "db";

        if (darkhastType.equals("TAGHIRAT"))
            bzkrdOrTgr = "dt";


        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("d.darkhastTaghirat","dt",specification).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("d.darkhastBazkharid","db",specification).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("dt.state","dts",specification);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("db.state","dbs",specification);

        if(darkhastType.equals("ALL"))
            criteria.add(Restrictions.or(Restrictions.isNotNull("dbs.id"), Restrictions.isNotNull("dts.id")));
        if(darkhastType.equals("TAGHIRAT"))
            criteria.add(Restrictions.isNotNull("dts.id"));
        if(!darkhastType.equals("TAGHIRAT")&& !darkhastType.equals("ALL"))
            criteria.add(Restrictions.isNotNull("dbs.id"));

        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("dts.transitionBegin","dtst",specification);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("dbs.transitionBegin","dbst",specification);

        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("dtst.roles","dtstr",specification);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("dbst.roles","dbstr",specification);

        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("dtstr.users","dtstrUser",specification);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("dbstr.users","dbstrUser",specification);

        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            criteria.createCriteria("dt.creator","dtccc", CriteriaSpecification.LEFT_JOIN);
        if (!darkhastType.equals("TAGHIRAT"))
            criteria.createCriteria("db.creator","dbccc", CriteriaSpecification.LEFT_JOIN);

        if (darkhastType.equals("ALL"))
            criteria.add
            (
                Restrictions.or
                (
                    Restrictions.eq("dtstrUser.id", user.getId()),
                    Restrictions.eq("dbstrUser.id", user.getId())
                )
            );
        if (!darkhastType.equals("ALL"))
            criteria.add(Restrictions.eq(bzkrdOrTgr+"strUser.id", user.getId()));


//            criteria.createCriteria("dt.namayande", "dtn");

//            criteria.createCriteria("dt.karshenas", "dtk");


        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                if (darkhastType.equals("ALL"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.eq("db.namayande.id", user.getNamayandegi().getId()),
                            Restrictions.eq("dt.namayande.id", user.getNamayandegi().getId())
                        )
                    );
                else
                    criteria.add(Restrictions.eq(bzkrdOrTgr+".namayande.id",user.getNamayandegi().getId()));
            }
//            if (theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_KHESARAT") ||
//                    theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL_KHESARAT") ||
//                    theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_KHESARAT")
//                    )
//            {
//                if (darkhastType.equals("ALL"))
//                {
//                    criteria.add
//                    (
//                        Restrictions.or
//                        (
//                            Restrictions.eq("db.karshenasKhesarat.id", user.getId()),
//                            Restrictions.eq("dbccc.id", user.getId())
//                        )
//
//                    );
//                }
//                if (darkhastType.equals("TAGHIRAT"))
//                {
//                    criteria.add(Restrictions.eq("dtccc.id", user.getId()));
//                }
//                if (!darkhastType.equals("TAGHIRAT") && !darkhastType.equals("ALL"))
//                {
//                    criteria.add
//                            (
//                                    Restrictions.or
//                                            (
//                                                    Restrictions.eq("db.karshenasKhesarat.id", user.getId()),
//                                                    Restrictions.eq("dbccc.id", user.getId())
//                                            )
//                            );
//                }
//            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS")||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL"))
            {
                if (darkhastType.equals("ALL"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("dt.karshenas.id", user.getId()),
                                Restrictions.eq("dtccc.id", user.getId())
                            ),
                            Restrictions.or
                            (
                                Restrictions.eq("db.karshenas.id", user.getId()),
                                Restrictions.eq("dbccc.id", user.getId())
                            )
                        )
                    );
                else
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.eq(bzkrdOrTgr + ".karshenas.id", user.getId()),
                            Restrictions.eq(bzkrdOrTgr + "ccc.id", user.getId())
                        )
                    );
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                if (darkhastType.equals("ALL"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.gt("dts.id", 9010),
                                Restrictions.eq("dtccc.id", user.getId())
                            ),
                            Restrictions.or
                            (
                                Restrictions.and
                                        (
                                                Restrictions.and
                                                        (
                                                                Restrictions.and
                                                                        (
                                                                                Restrictions.ne("dbs.id", 1000),
                                                                                Restrictions.ne("dbs.id", 10000)
                                                                        ),
                                                                Restrictions.and
                                                                        (
                                                                                Restrictions.ne("dbs.id", 11000),
                                                                                Restrictions.ne("dbs.id", 12010)
                                                                        )
                                                        ),
                                                Restrictions.and
                                                        (
                                                                Restrictions.and
                                                                        (
                                                                                Restrictions.and
                                                                                        (
                                                                                                Restrictions.ne("dbs.id", 9130),
                                                                                                Restrictions.ne("dbs.id", 10040)
                                                                                        ),
                                                                                Restrictions.and
                                                                                        (
                                                                                                Restrictions.and
                                                                                                        (
                                                                                                                Restrictions.ne("dbs.id", 1300),
                                                                                                                Restrictions.ne("dbs.id", 11040)
                                                                                                        ),
                                                                                                Restrictions.ne("dbs.id", 12050)
                                                                                        )
                                                                        ),
                                                                Restrictions.ne("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT)
                                                        )
                                        ),
                                Restrictions.eq("dbccc.id", user.getId())
                            )
                        )
                    );

                else if (darkhastType.equals("TAGHIRAT"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.gt("dts.id", 9010),
                            Restrictions.eq("dtccc.id", user.getId())
                        )
                    );

                else //if (!darkhastType.equals("TAGHIRAT"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.and
                            (
                                Restrictions.and
                                (
                                    Restrictions.and
                                    (
                                        Restrictions.ne("dbs.id", 1000),
                                        Restrictions.ne("dbs.id", 10000)
                                    ),
                                    Restrictions.and
                                    (
                                        Restrictions.ne("dbs.id", 11000),
                                        Restrictions.ne("dbs.id", 12010)
                                    )
                                ),
                                Restrictions.and
                                (
                                    Restrictions.and
                                    (
                                        Restrictions.and
                                        (
                                            Restrictions.ne("dbs.id", 9130),
                                            Restrictions.ne("dbs.id", 10040)
                                        ),
                                        Restrictions.ne("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT)
                                    ),
                                    Restrictions.and
                                    (
                                        Restrictions.and
                                        (
                                            Restrictions.ne("dbs.id", 1300),
                                            Restrictions.ne("dbs.id", 11040)
                                        ),
                                        Restrictions.ne("dbs.id", 12050)
                                    )
                                )
                            ),
                            Restrictions.eq("dbccc.id", user.getId())
                        )
                    );
            }
        }

        if((shomareBimename!=null && !shomareBimename.isEmpty()) || (bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) ||
           (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) || (bimeShodeName!=null && !bimeShodeName.isEmpty()) || (bimeShodeFamily!=null && bimeGozarFamily.isEmpty()) ||
           (bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty()))
        {
            if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            {
                criteria.createCriteria("dt.oldPishnehad","dtPishnehad",specification);
                criteria.createCriteria("dtPishnehad.bimename","dteBimename",specification);
            }
            if (!darkhastType.equals("TAGHIRAT"))
            {
                criteria.createCriteria("db.bimename","dbeBimename",specification);
                criteria.createCriteria("dbeBimename.pishnehadSet","dbPishnehad",specification);
            }

            if(shomareBimename!=null && !shomareBimename.isEmpty())
            {
                if (darkhastType.equals("ALL"))
                    criteria.add
                    (
                        Restrictions.or
                        (
                            Restrictions.like("dteBimename.shomare", shomareBimename, MatchMode.ANYWHERE),
                            Restrictions.like("dbeBimename.shomare", shomareBimename, MatchMode.ANYWHERE)
                        )
                    );
                else
                    criteria.add(Restrictions.like(bzkrdOrTgr + "eBimename.shomare", shomareBimename, MatchMode.ANYWHERE));
            }

            if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()) ||
               (bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
            {
                if ((darkhastType.equals("TAGHIRAT") || (darkhastType.equals("ALL"))))
                    criteria.add
                    (
                        Restrictions.or
                        (
//                            Restrictions.eq("dtPishnehad.valid",true),
                            Restrictions.isNull("dbPishnehad.id"),
                            Restrictions.eq("dbPishnehad.valid",true)
                        )
                    );
                else
                    criteria.add(Restrictions.eq(bzkrdOrTgr+"pishnehad.valid",true));

                if((bimeGozarName!=null && !bimeGozarName.isEmpty()) || (bimeGozarFamily!=null && !bimeGozarFamily.isEmpty()) || (bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty()))
                {
                    if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                        criteria.createCriteria("dtPishnehad.bimeGozar","dtebGozar",specification);
                    if (!darkhastType.equals("TAGHIRAT"))
                        criteria.createCriteria("dbPishnehad.bimeGozar","dbebGozar",specification);

                    if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                        criteria.createCriteria("dtebGozar.shakhs","dtebsh",specification);
                    if (!darkhastType.equals("TAGHIRAT"))
                        criteria.createCriteria("dbebGozar.shakhs","dbebsh",specification);

                    if(bimeGozarName!=null && !bimeGozarName.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebsh.name",bimeGozarName,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebsh.name",bimeGozarName,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr+"ebsh.name",bimeGozarName,MatchMode.ANYWHERE));
                    }

                    if(bimeGozarFamily!=null && !bimeGozarFamily.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebsh.nameKhanevadegi",bimeGozarFamily,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebsh.nameKhanevadegi",bimeGozarFamily,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr+"ebsh.nameKhanevadegi", bimeGozarFamily, MatchMode.ANYWHERE));
                    }

                    if(bimeGozarKodeMelli!=null && !bimeGozarKodeMelli.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebsh.kodeMelliShenasayi",bimeGozarKodeMelli,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebsh.kodeMelliShenasayi",bimeGozarKodeMelli,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr + "ebsh.kodeMelliShenasayi", bimeGozarKodeMelli, MatchMode.ANYWHERE));

                    }
                }
                if((bimeShodeName!="" && !bimeShodeName.isEmpty()) || (bimeShodeFamily!="" && !bimeGozarFamily.isEmpty()) || (bimeShodeKodeMelli!="" && !bimeShodeKodeMelli.isEmpty()))
                {
                    if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                        criteria.createCriteria("dtPishnehad.bimeShode","dtebShode",specification);
                    if (!darkhastType.equals("TAGHIRAT"))
                        criteria.createCriteria("dbPishnehad.bimeShode","dbebShode",specification);

                    if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                        criteria.createCriteria("dtebShode.shakhs","dtebssh",specification);
                    if (!darkhastType.equals("TAGHIRAT"))
                        criteria.createCriteria("dbebShode.shakhs","dbebssh",specification);

                    if(bimeShodeName!=null && !bimeShodeName.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebssh.name",bimeShodeName,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebssh.name",bimeShodeName,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr+"ebssh.name", bimeShodeName, MatchMode.ANYWHERE));
                    }

                    if(bimeShodeFamily!=null && !bimeShodeFamily.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebssh.nameKhanevadegi",bimeShodeFamily,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebssh.nameKhanevadegi",bimeShodeFamily,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr+"ebssh.nameKhanevadegi", bimeShodeFamily, MatchMode.ANYWHERE));
                    }

                    if(bimeShodeKodeMelli!=null && !bimeShodeKodeMelli.isEmpty())
                    {
                        if (darkhastType.equals("ALL"))
                            criteria.add
                            (
                                Restrictions.or
                                (
                                    Restrictions.like("dtebssh.kodeMelliShenasayi",bimeShodeKodeMelli,MatchMode.ANYWHERE),
                                    Restrictions.like("dbebssh.kodeMelliShenasayi",bimeShodeKodeMelli,MatchMode.ANYWHERE)
                                )
                            );
                        else
                            criteria.add(Restrictions.like(bzkrdOrTgr+"ebssh.kodeMelliShenasayi", bimeShodeKodeMelli, MatchMode.ANYWHERE));
                    }

                }
            }
        }

        if(namayandeId!=null && namayandeId>0)
        {
            if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                criteria.createCriteria("dt.namayande","dtNamayande",specification);
            if (!darkhastType.equals("TAGHIRAT"))
                criteria.createCriteria("db.namayande","dbNamayande",specification);

            if (darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.eq("dtNamayande.id",namayandeId),
                        Restrictions.eq("dbNamayande.id",namayandeId)
                    )
                );
            if (!darkhastType.equals("ALL"))
                criteria.add(Restrictions.eq(bzkrdOrTgr+"Namayande.id",namayandeId));
        }

        if(karshenas!=null && !karshenas.isEmpty())
        {
            if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
                criteria.createCriteria("dt.karshenas","dtKarshenas",specification);
            if (!darkhastType.equals("TAGHIRAT"))
                criteria.createCriteria("db.karshenas","dbKarshenas",specification);

            if (darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.or
                        (
                            Restrictions.like("dtKarshenas.firstName",karshenas,MatchMode.ANYWHERE),
                            Restrictions.like("dtKarshenas.lastName",karshenas,MatchMode.ANYWHERE)
                        ),
                        Restrictions.or
                        (
                            Restrictions.like("dbKarshenas.firstName",karshenas,MatchMode.ANYWHERE),
                            Restrictions.like("dbKarshenas.lastName",karshenas,MatchMode.ANYWHERE)
                        )
                    )
                );
            if (!darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.like(bzkrdOrTgr+"Karshenas.firstName", karshenas, MatchMode.ANYWHERE),
                        Restrictions.like(bzkrdOrTgr+"Karshenas.lastName", karshenas, MatchMode.ANYWHERE)
                    )
                );
        }

        if(azTarikheDarkhast!=null && !azTarikheDarkhast.isEmpty())
        {
            if (darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.ge("dt.tarikhDarkhast",azTarikheDarkhast),
                        Restrictions.ge("db.tarikhDarkhast",azTarikheDarkhast)
                    )
                );
            else
                criteria.add(Restrictions.ge(bzkrdOrTgr+".tarikhDarkhast", azTarikheDarkhast));
        }

        if(taTarikheDarkhast!=null && !taTarikheDarkhast.isEmpty())
        {
            if (darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.le("dt.tarikhDarkhast",taTarikheDarkhast),
                        Restrictions.le("db.tarikhDarkhast",taTarikheDarkhast)
                    )
                );
            else
                criteria.add(Restrictions.le(bzkrdOrTgr+".tarikhDarkhast", taTarikheDarkhast));
        }

        if((darkhastState!=null || darkhastState.size()!=0) && !darkhastState.get(0).equals("-"))
        {
            if (darkhastType.equals("ALL"))
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.eq("dts.id",Integer.parseInt(darkhastState.get(0))),
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("dbs.id",Integer.parseInt(darkhastState.get(0))),
                                Restrictions.eq("dbs.id",Integer.parseInt(darkhastState.get(1)))
                            ),Restrictions.or
                            (
                                Restrictions.eq("dbs.id", Integer.parseInt(darkhastState.get(2))),
                                Restrictions.eq("dbs.id",Integer.parseInt(darkhastState.get(3)))
                            )

                        )
                    )
                );
            else
                criteria.add(Restrictions.eq(bzkrdOrTgr+"s.id",Integer.parseInt(darkhastState.get(0))));
        }

        if(darkhastType.equals("EBTAL"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.EBTAL));
        if(darkhastType.equals("BAZKHARID"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.BAZKHARID));
        if(darkhastType.equals("VAM"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.VAM));
        if(darkhastType.equals("TAGHIR_CODE"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.TAGHIRKOD));
        if(darkhastType.equals("TOZIH"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.TOZIH));
        if(darkhastType.equals("TAGHIRAT"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.TAGHYIRAT));
        if(darkhastType.equals("BARDASHT"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE));
        if(darkhastType.equals("KHESARAT"))
            criteria.add(Restrictions.eq("d.darkhastType",Darkhast.DarkhastType.KHESARAT));

        criteria.setProjection(Projections.id());

        Criteria nonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class,"d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        nonDetached.add(Subqueries.propertyIn("d.id", criteria));
        int count = Integer.parseInt(nonDetached.setProjection(Projections.rowCount()).list().get(0).toString());

        Criteria c=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            c.createCriteria("d.darkhastTaghirat","dt",specification);
        if (!darkhastType.equals("TAGHIRAT"))
            c.createCriteria("d.darkhastBazkharid","db",specification);

        c.add(Subqueries.propertyIn("d.id", criteria));
        if (darkhastType.equals("TAGHIRAT") || darkhastType.equals("ALL"))
            c.addOrder(Order.desc("dt.tarikhDarkhast"));
        if (!darkhastType.equals("TAGHIRAT"))
            c.addOrder(Order.desc("db.tarikhDarkhast"));
            pgList.setFullListSize(count);
            c.setFirstResult(pgList.getPageNumber()* pgList.getObjectsPerPage());
            c.setMaxResults(pgList.getObjectsPerPage());
            pgList.setList(c.list());
            pgList.setPageNumber(pgList.getPageNumber()+1);
            return pgList ;
//        }
    }

    public PaginatedListImpl findAllDarkhastsUnFinished(User user, PaginatedListImpl pgList, List<Integer> statesId)
    {
        String whereClause = "";
        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClause += "( (db.namayande is not null and db.namayande.id = " + user.getNamayandegi().getId() + ") or (dt.namayande is not null and dt.namayande.id = " + user.getNamayandegi().getId() + ") ) and ";
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS"))
            {
                whereClause += "(( dtk.id = " + user.getId() + " or dtc.id = " + user.getId() + ") or ( (dbk.id = " + user.getId() + " and dbs.id != 9130 and dbs.id != 10040 and dbs.id != 1300 and dbs.id != 11040 and dbs.id != 12050) or dbc.id = " + user.getId() + ")) and ";
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL")|| theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR"))
            {
                whereClause += "((dtk.id = " + user.getId() + " or dtc.id = " + user.getId() + ") or (dtk.id = " + user.getId() + " or dbc.id = " + user.getId() + ")) and ";
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
//                whereClause += "((dts.id > 9010 or dtc.id = " + user.getId() + " ) or ((dbs.id > 0 and dbs.id != 9130 and dbs.id != 10040 and dbs.id != 1300 and dbs.id != 11040 and dbs.id != 12050 and dbs.id != 11000 and dbs.id != 11010) or dbc.id = " + user.getId() + ")) and ";
            }
        }

        whereClause +=" (dts.id is not null or dbs.id is not null) and "  ;

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count = getSession().createQuery
                (
                        "select count(d.id) " +
                                "from Darkhast d left join d.darkhastTaghirat dt left join d.darkhastBazkharid db left join dt.state dts left join  db.state dbs left join db.karshenasKhesarat dbkk left join db.karshenas dbk left join dt.karshenas dtk " +
                                "left join dt.creator dtc left join db.creator dbc " +
                                "where (dbs.id in (:statesParam) or dts.id in (:statesParam)) " + whereClause
                ).setParameterList("statesParam", statesId);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                (
                        "select new  com.bitarts.parsian.viewModel.DarkhastsVM(dtsp.roleColor,dbsp.roleColor, dt.tarikhDarkhast,db.tarikhDarkhast," +
                                "dtb.shomare,dbb.shomare, dtn.name, dbn.name, dtn.kodeNamayandeKargozar,dbn.kodeNamayandeKargozar, " +
                                "d.darkhastType,dtBiGozarSh.name, (select p1.bimeGozar.shakhs.name from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= dbb.id)," +
                                "dtBiGozarSh.nameKhanevadegi,(select p2.bimeGozar.shakhs.nameKhanevadegi from Pishnehad p2 join p2.bimename b2 where b2.readyToShow = 'yes' and p2.valid = true and b2.id= dbb.id)," +
                                "dtBiShodeSh.name,(select p3.bimeShode.shakhs.name from Pishnehad p3 join p3.bimename b3 where b3.readyToShow = 'yes' and p3.valid = true and b3.id= dbb.id)," +
                                "dtBiShodeSh.nameKhanevadegi,(select p4.bimeShode.shakhs.nameKhanevadegi from Pishnehad p4 join p4.bimename b4 where b4.readyToShow = 'yes' and p4.valid = true and b4.id= dbb.id)," +
                                "dtk.firstName, dbk.firstName, dtk.lastName, dbk.lastName, dtc.firstName, dbc.firstName, dtc.lastName, dbc.lastName, " +
                                "(select max(t1.date) as taghirTransitionLogDate from TransitionLog t1 join t1.darkhastTaghirat dt1  where dt1.id = dt.id) ," +
                                "(select max(t2.date) as bazkharidTransitionLogDate from TransitionLog t2 join t2.darkhastBazkharid db1 where db1.id = db.id) , dts.stateName, dbs.stateName, dt.id, db.id, dbkk.firstName, dbkk.lastName ) " +
                                "from Darkhast d left join d.darkhastTaghirat dt left join dt.karshenas dtk left join dt.creator dtc left join dt.state dts " +
                                "left join d.darkhastBazkharid db left join db.bimename dbb left join db.namayande dbn left join db.karshenas dbk left join db.karshenasKhesarat dbkk left join db.creator dbc left join db.state dbs " +
                                "left join dt.oldPishnehad oldPishnehad left join oldPishnehad.bimename dtb left join oldPishnehad.bimeGozar dtBimegozar left join dtBimegozar.shakhs dtBiGozarSh " +
                                "left join oldPishnehad.bimeShode dtBimeshode left join dtBimeshode.shakhs dtBiShodeSh left join dt.namayande dtn " +
                                "left join dts.peygirKonande dtsp left join dbs.peygirKonande dbsp " +
                                "where (dbs.id in (:statesParam) or dts.id in (:statesParam)) " + whereClause //+
//                          "order by taghirTransitionLogDate , bazkharidTransitionLogDate desc "
//                              "select new  com.bitarts.parsian.viewModel.DarkhastsVM(dts.peygirKonande.roleColor,dbs.peygirKonande.roleColor, dt.tarikhDarkhast,db.tarikhDarkhast," +
//                              "dtob.shomare,dbb.shomare, dtn.name, dbn.name, dtn.kodeNamayandeKargozar,dbn.kodeNamayandeKargozar," +
//                              "'0',dto.bimeGozar.shakhs.name, (select p1.bimeGozar.shakhs.name from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= db.bimename.id)," +
//                              "dto.bimeGozar.shakhs.nameKhanevadegi,(select p1.bimeGozar.shakhs.nameKhanevadegi from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= db.bimename.id)," +
//                              "dto.bimeShode.shakhs.name,(select p1.bimeShode.shakhs.name from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= db.bimename.id)," +
//                              "dto.bimeShode.shakhs.nameKhanevadegi,(select p1.bimeShode.shakhs.nameKhanevadegi from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= db.bimename.id)," +
//                              "dtk.firstName, dbk.firstName, dtk.lastName, dbk.lastName, dtc.firstName, dbc.firstName, dtc.lastName, dbc.lastName, " +
//                              "(select max(t1.date) from TransitionLog t1 where t1.darkhastTaghirat.id = dt.id) as taghirTransitionLogDate," +
//                              "(select max(t.date) from TransitionLog t where t.darkhastBazkharid.id = db.id) as bazkharidTransitionLogDate, dts.stateName, dbs.stateName, dt.id, db.id ) " +
//                              "from Darkhast d left join d.darkhastTaghirat dt left join d.darkhastBazkharid db left join dt.state dts left join db.state dbs left join db.karshenas dbk left join dt.karshenas dtk " +
//                              "left join dt.creator dtc left join db.creator dbc left join dt.oldPishnehad dto left join dto.bimename dtob left join dt.namayande dtn left join db.namayande dbn left join db.bimename dbb " +
//                              "where (dbs.id in (:statesParam) or dts.id in (:statesParam)) " +  whereClause +
//                              "order by taghirTransitionLogDate , bazkharidTransitionLogDate desc "
                ).setParameterList("statesParam", statesId);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);

        return pgList;
    }

    public PaginatedListImpl<Darkhast> findAllDarkhastsUnFinished(User user,int pageNumber)
    {
        PaginatedListImpl<Darkhast> list = new PaginatedListImpl<Darkhast>();
        list.setObjectsPerPage(30);
        int pageNum = pageNumber-1;
        final int objectsPerPage = list.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;

        DetachedCriteria dc = DetachedCriteria.forClass(Darkhast.class, "d");
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        dc.createCriteria("d.darkhastTaghirat","dt",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dt.creator","dtccc",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dt.state", "dts",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dts.transitionBegin", "dtst",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dtst.roles", "dtstr",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dtstr.users", "dtstrUser",CriteriaSpecification.LEFT_JOIN);

        dc.createCriteria("d.darkhastBazkharid","db",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("db.creator","dbccc",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("db.state", "dbs",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dbs.transitionBegin", "dbst",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dbst.roles", "dbstr",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dbstr.users", "dbstrUser",CriteriaSpecification.LEFT_JOIN);

        dc.add(Restrictions.or(Restrictions.isNotNull("dbs.id"), Restrictions.isNotNull("dts.id")));



//        select this_.id as id22_6_, this_.darkhast_bazkharid as darkhast4_22_6_, this_.darkhast_taghirat as darkhast5_22_6_, this_.darkhast_type as darkhast2_22_6_, this_.darkhast_vam as darkhast6_22_6_, this_.darkhast_finished as darkhast3_22_6_, darkhastva2_.darbazkharid_id as darbazkh1_23_0_, darkhastva2_.darbaz_arzesh_bazkharid as darbaz4_23_0_, darkhastva2_.darbaz_bankName as darbaz5_23_0_, darkhastva2_.darbaz_familyBimeshode as darbaz10_23_0_, darkhastva2_.karshenas_id as karshenas61_23_0_, darkhastva2_.darbaz_kodeMeliBimeShode as darbaz19_23_0_, darkhastva2_.darbaz_kodeNamayandegi as darbaz20_23_0_, darkhastva2_.darbaz_kodeShobe as darbaz21_23_0_, darkhastva2_.namayande_id as namayande65_23_0_, darkhastva2_.darbaz_nameBimeshode as darbaz29_23_0_, darkhastva2_.darbaz_namePedarBimeShode as darbaz30_23_0_, darkhastva2_.darbaz_noeNamayandegi as darbaz33_23_0_, darkhastva2_.darbaz_sahebHesab as darbaz35_23_0_, darkhastva2_.darbaz_shobeName as darbaz36_23_0_, darkhastva2_.darbaz_shomareDarkhast as darbaz38_23_0_, darkhastva2_.darbaz_shomareHesab as darbaz39_23_0_, darkhastva2_.darbaz_shomareShaba as darbaz41_23_0_, darkhastva2_.darbaz_shomareShenasname as darbaz42_23_0_, darkhastva2_.state_id as state69_23_0_, darkhastva2_.darbaz_tarikhijad as darbaz46_23_0_, darkhastva2_.darbaz_tarikhTavalBimShod as darbaz48_23_0_, darkhastva2_.darbaz_hamrahBimeShode as darbaz51_23_0_, darkhastva2_.darbaz_telephonBimeShode as darbaz52_23_0_, darkhastva2_.darbaz_telephoneNamay as darbaz53_23_0_, darkhastva2_.zamaem_darkhast_id as zamaem70_23_0_, namayande3_.id as id58_1_, namayande3_.address as address58_1_, namayande3_.is_arshad as is3_58_1_, namayande3_.code_canceled as code4_58_1_, namayande3_.etebarMojazVosolNashodeAmount as etebarMo5_58_1_, namayande3_.expDate as expDate58_1_, namayande3_.issuance_code as issuance7_58_1_, namayande3_.kodeNamayandeKargozar as kodeNama8_58_1_, namayande3_.mohlate_sanadzadan as mohlate9_58_1_, namayande3_.type_enum as type10_58_1_, namayande3_.name as name58_1_, namayande3_.ostan_id as ostan14_58_1_, namayande3_.parent_id as parent15_58_1_, namayande3_.pishnehad_create_access as pishnehad12_58_1_, namayande3_.sarparast_id as sarparast16_58_1_, namayande3_.senior_id as senior17_58_1_, namayande3_.shahr_id as shahr18_58_1_, namayande3_.namayande_telephone as namayande13_58_1_, namayande3_.vahedSodur_id as vahedSodur19_58_1_, namayande4_.id as id58_2_, namayande4_.address as address58_2_, namayande4_.is_arshad as is3_58_2_, namayande4_.code_canceled as code4_58_2_, namayande4_.etebarMojazVosolNashodeAmount as etebarMo5_58_2_, namayande4_.expDate as expDate58_2_, namayande4_.issuance_code as issuance7_58_2_, namayande4_.kodeNamayandeKargozar as kodeNama8_58_2_, namayande4_.mohlate_sanadzadan as mohlate9_58_2_, namayande4_.type_enum as type10_58_2_, namayande4_.name as name58_2_, namayande4_.ostan_id as ostan14_58_2_, namayande4_.parent_id as parent15_58_2_, namayande4_.pishnehad_create_access as pishnehad12_58_2_, namayande4_.sarparast_id as sarparast16_58_2_, namayande4_.senior_id as senior17_58_2_, namayande4_.shahr_id as shahr18_58_2_, namayande4_.namayande_telephone as namayande13_58_2_, namayande4_.vahedSodur_id as vahedSodur19_58_2_, namayande5_.id as id58_3_, namayande5_.address as address58_3_, namayande5_.is_arshad as is3_58_3_, namayande5_.code_canceled as code4_58_3_, namayande5_.etebarMojazVosolNashodeAmount as etebarMo5_58_3_, namayande5_.expDate as expDate58_3_, namayande5_.issuance_code as issuance7_58_3_, namayande5_.kodeNamayandeKargozar as kodeNama8_58_3_, namayande5_.mohlate_sanadzadan as mohlate9_58_3_, namayande5_.type_enum as type10_58_3_, namayande5_.name as name58_3_, namayande5_.ostan_id as ostan14_58_3_, namayande5_.parent_id as parent15_58_3_, namayande5_.pishnehad_create_access as pishnehad12_58_3_, namayande5_.sarparast_id as sarparast16_58_3_, namayande5_.senior_id as senior17_58_3_, namayande5_.shahr_id as shahr18_58_3_, namayande5_.namayande_telephone as namayande13_58_3_, namayande5_.vahedSodur_id as vahedSodur19_58_3_, namayande6_.id as id58_4_, namayande6_.address as address58_4_, namayande6_.is_arshad as is3_58_4_, namayande6_.code_canceled as code4_58_4_, namayande6_.etebarMojazVosolNashodeAmount as etebarMo5_58_4_, namayande6_.expDate as expDate58_4_, namayande6_.issuance_code as issuance7_58_4_, namayande6_.kodeNamayandeKargozar as kodeNama8_58_4_, namayande6_.mohlate_sanadzadan as mohlate9_58_4_, namayande6_.type_enum as type10_58_4_, namayande6_.name as name58_4_, namayande6_.ostan_id as ostan14_58_4_, namayande6_.parent_id as parent15_58_4_, namayande6_.pishnehad_create_access as pishnehad12_58_4_, namayande6_.sarparast_id as sarparast16_58_4_, namayande6_.senior_id as senior17_58_4_, namayande6_.shahr_id as shahr18_58_4_, namayande6_.namayande_telephone as namayande13_58_4_, namayande6_.vahedSodur_id as vahedSodur19_58_4_, namayande7_.id as id58_5_, namayande7_.address as address58_5_, namayande7_.is_arshad as is3_58_5_, namayande7_.code_canceled as code4_58_5_, namayande7_.etebarMojazVosolNashodeAmount as etebarMo5_58_5_, namayande7_.expDate as expDate58_5_, namayande7_.issuance_code as issuance7_58_5_, namayande7_.kodeNamayandeKargozar as kodeNama8_58_5_, namayande7_.mohlate_sanadzadan as mohlate9_58_5_, namayande7_.type_enum as type10_58_5_, namayande7_.name as name58_5_, namayande7_.ostan_id as ostan14_58_5_, namayande7_.parent_id as parent15_58_5_, namayande7_.pishnehad_create_access as pishnehad12_58_5_, namayande7_.sarparast_id as sarparast16_58_5_, namayande7_.senior_id as senior17_58_5_, namayande7_.shahr_id as shahr18_58_5_, namayande7_.namayande_telephone as namayande13_58_5_, namayande7_.vahedSodur_id as vahedSodur19_58_5_ from tbl_darkhast this_ left outer join tbl_darkhast_bazkharid darkhastva2_ on this_.darkhast_vam=darkhastva2_.darbazkharid_id left outer join tbl_namayande namayande3_ on darkhastva2_.namayande_id=namayande3_.id left outer join tbl_namayande namayande4_ on namayande3_.parent_id=namayande4_.id left outer join tbl_namayande namayande5_ on namayande4_.sarparast_id=namayande5_.id left outer join tbl_namayande namayande6_ on namayande5_.senior_id=namayande6_.id left outer join tbl_namayande namayande7_ on namayande6_.vahedSodur_id=namayande7_.id where this_.id in (select d_.id as y0_ from tbl_darkhast d_ inner join tbl_darkhast_bazkharid db1_ on d_.darkhast_bazkharid=db1_.darbazkharid_id inner join tbl_bimename dbebimenam7_ on db1_.bime_dar=dbebimenam7_.bimename_id inner join tbl_pishnehad dbpishneha8_ on dbebimenam7_.bimename_id=dbpishneha8_.bimename_bimename_id inner join tbl_users dbccc6_ on db1_.user_creator_id=dbccc6_.user_id inner join tbl_state dbs2_ on db1_.state_id=dbs2_.state_id inner join tbl_transition dbst3_ on dbs2_.state_id=dbst3_.state_id1 inner join rel_role_transition roles16_ on dbst3_.transition_id=roles16_.transition_id inner join tbl_role dbstr4_ on roles16_.role_id=dbstr4_.role_id inner join rel_user_role users18_ on dbstr4_.role_id=users18_.role_id inner join tbl_users dbstruser5_ on users18_.user_id=dbstruser5_.user_id where dbstruser5_.user_id=? and db1_.namayande_id=? and d_.darkhast_type=?).createCriteria("d.darkhastBazkharid","db",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("db.state","dbs",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dbs.transitionBegin","dbst",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dbst.roles","dbstr",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dbstr.users","dbstrUser",CriteriaSpecification.LEFT_JOIN);
//
//        criteria.createCriteria("d.darkhastVam","dv",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dv.state","dvs",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dvs.transitionBegin","dvst",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dvst.roles","dvstr",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dvstr.users","dvstrUser",CriteriaSpecification.LEFT_JOIN);
//
//        criteria.createCriteria("d.khesarat","dk",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dk.state","dks",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dks.transitionBegin","dkst",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dkst.roles","dkstr",CriteriaSpecification.LEFT_JOIN)
//                .createCriteria("dkstr.users","dkstrUser",CriteriaSpecification.LEFT_JOIN);

//        criteria.createCriteria("d.darkhastTaghirat","dt",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dt.state","dts",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dts.transitionBegin","dtst",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dtst.roles","dtstr",CriteriaSpecification.LEFT_JOIN)
//        .createCriteria("dtstr.users","dtstrUser",CriteriaSpecification.LEFT_JOIN);

        dc.add(
                Restrictions.or(
                        Restrictions.eq("dtstrUser.id", user.getId())
                        ,
                        Restrictions.eq("dbstrUser.id", user.getId())));

//        criteria.add
//                (
//                    Restrictions.or
//                    (
//                        Restrictions.or
//                        (
//                            Restrictions.eq("dbstrUser.id", user.getId()),
//                            Restrictions.eq("dvstrUser.id", user.getId())
//                        ),
//                        Restrictions.or
//                        (
//                            Restrictions.eq("dkstrUser.id", user.getId()),
//                            Restrictions.eq("dtstrUser.id", user.getId())
//                        )
//                    )
//                );
        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                  dc.add
                     (Restrictions.or
                                   (
                                        Restrictions.eq("db.namayande.id", user.getNamayandegi().getId()),
                                        Restrictions.eq("dt.namayande.id", user.getNamayandegi().getId())
                                   )
                     );
//                criteria.createCriteria("db.namayande", "dbn", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dv.namayande", "dvn", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dk.namayande", "dkn", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dt.namayande", "dtn", CriteriaSpecification.LEFT_JOIN);
//
//                criteria.add
//                        (
//                            Restrictions.or
//                            (
//                                Restrictions.or
//                                (
//                                    Restrictions.eq("dbn.id", user.getNamayandegi().getId()),
//                                    Restrictions.eq("dvn.id", user.getNamayandegi().getId())
//                                ),
//
//                                Restrictions.or
//                                (
//                                    Restrictions.eq("dkn.id", user.getNamayandegi().getId()),
//                                    Restrictions.eq("dtn.id", user.getNamayandegi().getId())
//                                )
//                            )
//                        );
            }
            if
            (
                theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL")
            )
            {
//                criteria.createCriteria("db.karshenas", "dbk", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dv.karshenas", "dvk", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dk.karshenasKhesarat", "dkk", CriteriaSpecification.LEFT_JOIN);
//                criteria.createCriteria("dt.karshenas", "dtk", CriteriaSpecification.LEFT_JOIN);
                dc.add
                        (Restrictions.or(
                                (Restrictions.or
                                        (
                                                Restrictions.eq("dt.karshenas.id", user.getId()),
                                                Restrictions.eq("dtccc.id",user.getId())
                                        )
                                ),(Restrictions.or
                                        (
                                                Restrictions.eq("db.karshenas.id", user.getId()),
                                                Restrictions.eq("dbccc.id",user.getId())
                                        )
                                )));
//
//                criteria.add
//                        (
//                            Restrictions.or
//                            (
//                                Restrictions.or
//                                (
//                                    Restrictions.eq("dbk.id", user.getId()),
//                                    Restrictions.eq("dvk.id", user.getId())
//                                ),
//
//                                Restrictions.or
//                                (
//                                    Restrictions.eq("dkk.id", user.getId()),
//                                    Restrictions.eq("dtk.id", user.getId())
//                                )
//                            )
//                        );
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                dc.add
                        (Restrictions.or((Restrictions.or
                                (
                                        Restrictions.gt("dts.id",9010),
                                        Restrictions.eq("dtccc.id",user.getId())
                                )
                        ), (Restrictions.or
                                (
                                        Restrictions.gt("dbs.id",0),
                                        Restrictions.eq("dbccc.id",user.getId())
                                )
                        )));
            }
        }

//        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("id"), "id"))).setResultTransformer(Transformers.aliasToBean(Darkhast.class));

        dc.setProjection(Projections.id());

        Criteria nonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class);
        nonDetached.add(Subqueries.propertyIn("id", dc));
        int count = Integer.parseInt(nonDetached.setProjection(Projections.rowCount()).list().get(0).toString());
         Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("d.darkhastTaghirat","dt",CriteriaSpecification.LEFT_JOIN)
                .createCriteria("d.darkhastBazkharid","db",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Subqueries.propertyIn("d.id", dc));
        criteria.addOrder(Order.desc("dt.tarikhDarkhast"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(objectsPerPage);
        List<Darkhast> res = criteria.list();
        list.setFullListSize(count);
        list.setList(res);
        list.setPageNumber(pageNum + 1);
        list.setObjectsPerPage(objectsPerPage);
        return list;
    }

    public PaginatedListImpl<Darkhast> searchHameyeDarkhastha(SearchDarkhasts sd,User user, int pageNumber)
    {
        PaginatedListImpl<Darkhast> list = new PaginatedListImpl<Darkhast>();
        list.setObjectsPerPage(30);
        int pageNum = pageNumber-1;
        final int objectsPerPage = list.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;

        DetachedCriteria dc = DetachedCriteria.forClass(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        int specification = CriteriaSpecification.LEFT_JOIN;
        String bzkrdOrTgr = "";

        if (!sd.getDarkhastType().equals("ALL"))
            specification = CriteriaSpecification.INNER_JOIN;

        if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
            bzkrdOrTgr = "db";

        if (sd.getDarkhastType().equals("TAGHIRAT"))
            bzkrdOrTgr = "dt";


        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
            dc.createCriteria("d.darkhastTaghirat", "dt", specification);

        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            dc.createCriteria("d.darkhastBazkharid","db",specification);

        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
            dc.createCriteria("dt.state", "dts", CriteriaSpecification.LEFT_JOIN);

        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            dc.createCriteria("db.state", "dbs",CriteriaSpecification.LEFT_JOIN);

        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
            dc.createCriteria("dt.creator","dtccc",specification);

        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            dc.createCriteria("db.creator","dbccc",specification);

        if (sd.getDarkhastType().equals("ALL"))
            dc.add(Restrictions.or(Restrictions.isNotNull("dbs.id"), Restrictions.isNotNull("dts.id")));
        if (sd.getDarkhastType().equals("TAGHIRAT"))
            dc.add(Restrictions.isNotNull("dts.id"));
        if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
            dc.add(Restrictions.isNotNull("dbs.id"));

        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                if (sd.getDarkhastType().equals("ALL"))
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.eq("dt.namayande.id", user.getNamayandegi().getId()),
                            Restrictions.eq("db.namayande.id", user.getNamayandegi().getId())
                        )
                    );
                else
                    dc.add(Restrictions.eq(bzkrdOrTgr+".namayande.id", user.getNamayandegi().getId()));
            }

            if (theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_KHESARAT") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL_KHESARAT") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_KHESARAT")
               )
            {
                if(theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_KHESARAT"))
                {
                    if (sd.getDarkhastType().equals("ALL"))
                        dc.add
                        (
                            Restrictions.or
                            (
                                Restrictions.or
                                (
                                    Restrictions.eq("db.karshenasKhesarat.id", user.getId()),
                                    Restrictions.eq("dbccc.id", user.getId())
                                ),
                                Restrictions.eq("dtccc.id", user.getId())
                            )
                        );
                    if (sd.getDarkhastType().equals("TAGHIRAT"))
                        dc.add(Restrictions.eq("dtccc.id", user.getId()));
                    if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
                        dc.add
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("db.karshenasKhesarat.id", user.getId()),
                                Restrictions.eq("dbccc.id", user.getId())
                            )
                        );
                }
                else
                {
                    if (sd.getDarkhastType().equals("ALL"))
                        dc.add
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT),
                                Restrictions.eq("dtccc.id", user.getId())
                            )
                        );
                    if (sd.getDarkhastType().equals("TAGHIRAT"))
                        dc.add(Restrictions.eq("dtccc.id", user.getId()));
                    if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
                        dc.add(Restrictions.eq("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT));
                }
            }

            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL")
               )
            {
                if (sd.getDarkhastType().equals("ALL"))
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("db.karshenas.id", user.getId()),
                                Restrictions.eq("dbccc.id", user.getId())
                            ),
                            Restrictions.or
                            (
                                Restrictions.eq("dt.karshenas.id", user.getId()),
                                Restrictions.eq("dtccc.id", user.getId())
                            )

                        )
                    );
                if (sd.getDarkhastType().equals("TAGHIRAT"))
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.eq("dt.karshenas.id", user.getId()),
                            Restrictions.eq("dtccc.id", user.getId())
                        )
                    );
                if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("db.karshenas.id", user.getId()),
                                Restrictions.eq("dbccc.id", user.getId())
                            ),
                            Restrictions.eq("db.karshenas.id", user.getId())
                        )
                    );
            }
            if(theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
//               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                if (sd.getDarkhastType().equals("ALL"))
                {
                    System.out.println("all");
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.ne("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT),
                                Restrictions.and
                                (
                                    Restrictions.eq("dbccc.id", user.getId()),
                                    Restrictions.eq("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT)
                                )
                            ),
                            Restrictions.or
                            (
                                Restrictions.gt("dts.id", 9010),
                                Restrictions.eq("dtccc.id", user.getId())
                            )
                        )
                    );
                }
                if (sd.getDarkhastType().equals("TAGHIRAT"))
                {
                    System.out.println("tgr");
                    dc.add(Restrictions.or(Restrictions.gt("dts.id", 9010),Restrictions.eq("dtccc.id", user.getId())));
                }

                if (!sd.getDarkhastType().equals("TAGHIRAT") && !sd.getDarkhastType().equals("ALL"))
                {
                    System.out.println("bz");
                    dc.add
                    (
                        Restrictions.or
                        (
                            Restrictions.and
                            (
                                Restrictions.and
                                (
                                    Restrictions.and
                                    (
                                        Restrictions.ne("dbs.id", 1000),
                                        Restrictions.ne("dbs.id", 10000)
                                    ),
                                    Restrictions.and
                                    (
                                        Restrictions.and
                                        (
                                            Restrictions.ne("dbs.id", 11000),
                                            Restrictions.ne("dbs.id", 12010)
                                        ),
                                        Restrictions.and
                                        (
                                            Restrictions.and
                                            (
                                                Restrictions.ne("dbs.id", 642),
                                                Restrictions.ne("dbs.id", 619)
                                            ),
                                            Restrictions.ne("dbs.id", 640)
                                        )
                                    )
                                ),
                                Restrictions.or
                                (
                                    Restrictions.or
                                    (
                                        Restrictions.eq("dbs.id", 614),
                                        Restrictions.eq("dbs.id", 680)
                                    ),
                                    Restrictions.or
                                    (
                                        Restrictions.or
                                        (
                                            Restrictions.or
                                                    (
                                                            Restrictions.eq("dbs.id", 681),
                                                            Restrictions.eq("dbs.id", 636)
                                                    ),
                                            Restrictions.and
                                                    (
                                                            Restrictions.eq("dbs.id", 654),
                                                            Restrictions.isNull("db.karshenas.id")
                                                    )
                                        ),
                                        Restrictions.ne("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT)
                                    )
                                )
                            ),
                            Restrictions.and
                            (
                                    Restrictions.eq("dbccc.id", user.getId()),
                                    Restrictions.eq("db.darkhastType", DarkhastBazkharid.DarkhastType.KHESARAT)
                            )

                        )
                    );
                }
            }
        }

        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
        {
            dc.createCriteria("dt.oldPishnehad","o",specification);
            dc.createCriteria("o.bimename", "dtb", specification);
        }
        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            dc.createCriteria("db.bimename","dbb",specification);

        if(sd.getShomareBimename()!=null && !sd.getShomareBimename().isEmpty())
        {
            if (sd.getDarkhastType().equals("ALL"))
                dc.add
                (
                    Restrictions.or
                    (
                        Restrictions.like("dtb.shomare", sd.getShomareBimename(), MatchMode.ANYWHERE),
                        Restrictions.like("dbb.shomare", sd.getShomareBimename(), MatchMode.ANYWHERE)
                    )
                );
            else
                dc.add(Restrictions.like(bzkrdOrTgr + "b.shomare", sd.getShomareBimename(), MatchMode.ANYWHERE));
        }

        if(sd.getKodeMelliShenasayiBimeGozar()!=null && !sd.getKodeMelliShenasayiBimeGozar().isEmpty())
        {
            if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
                dc.createCriteria("dtb.pishnehadSet","dtp",specification);
            if (!sd.getDarkhastType().equals("TAGHIRAT"))
                dc.createCriteria("dbb.pishnehadSet","dbp",specification);

            if (sd.getDarkhastType().equals("ALL"))
                dc.add
                (
                    Restrictions.or
                    (
                        Restrictions.eq("dtp.valid", true),
                        Restrictions.eq("dbp.valid", true)
                    )
                );

            if (!sd.getDarkhastType().equals("ALL"))
                dc.add(Restrictions.eq(bzkrdOrTgr+"p.valid", true));

            if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
                dc.createCriteria("dtp.bimeGozar", "dtbg", specification);

            if (!sd.getDarkhastType().equals("TAGHIRAT"))
                dc.createCriteria("dbp.bimeGozar","dbbg",specification);

            if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
                dc.createCriteria("dtbg.shakhs","dtbs",specification);

            if (!sd.getDarkhastType().equals("TAGHIRAT"))
                dc.createCriteria("dbbg.shakhs","dbbs",specification);

            if (sd.getDarkhastType().equals("ALL"))
                dc.add
                (
                    Restrictions.or
                    (
                        Restrictions.like("dtbs.kodeMelliShenasayi", sd.getKodeMelliShenasayiBimeGozar(), MatchMode.ANYWHERE),
                        Restrictions.like("dbbs.kodeMelliShenasayi", sd.getKodeMelliShenasayiBimeGozar(), MatchMode.ANYWHERE)
                    )
                );

            if (!sd.getDarkhastType().equals("ALL"))
                dc.add(Restrictions.like(bzkrdOrTgr+"bs.kodeMelliShenasayi", sd.getKodeMelliShenasayiBimeGozar(), MatchMode.ANYWHERE));

        }

        if ((sd.getDarkhastStateList() != null || sd.getDarkhastStateList().size() != 0) && !sd.getDarkhastStateList().get(0).equals("-"))
        {
            if (sd.getDarkhastType().equals("ALL"))
                dc.add
                (
                    Restrictions.or
                    (
                        Restrictions.eq("dts.id", Integer.parseInt(sd.getDarkhastStateList().get(0))),
                        Restrictions.or
                        (
                            Restrictions.or
                            (
                                Restrictions.eq("dbs.id", Integer.parseInt(sd.getDarkhastStateList().get(0))),
                                Restrictions.eq("dbs.id", Integer.parseInt(sd.getDarkhastStateList().get(1)))
                            ),
                            Restrictions.or
                            (
                                Restrictions.eq("dbs.id", Integer.parseInt(sd.getDarkhastStateList().get(2))),
                                Restrictions.eq("dbs.id", Integer.parseInt(sd.getDarkhastStateList().get(3)))
                            )
                        )
                    )
                );
            else
                dc.add(Restrictions.eq(bzkrdOrTgr + "s.id", Integer.parseInt(sd.getDarkhastStateList().get(0))));
        }

        if (sd.getDarkhastType().equals("EBTAL"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.EBTAL));
        if (sd.getDarkhastType().equals("BAZKHARID"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.BAZKHARID));
        if (sd.getDarkhastType().equals("VAM"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.VAM));
        if (sd.getDarkhastType().equals("TAGHIR_CODE"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.TAGHIRKOD));
        if (sd.getDarkhastType().equals("TOZIH"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.TOZIH));
        if (sd.getDarkhastType().equals("TAGHIRAT"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.TAGHYIRAT));
        if (sd.getDarkhastType().equals("BARDASHT"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE));
        if (sd.getDarkhastType().equals("KHESARAT"))
            dc.add(Restrictions.eq("d.darkhastType", Darkhast.DarkhastType.KHESARAT));

        dc.setProjection(Projections.id());

        Criteria nonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class,"d");
        nonDetached.add(Subqueries.propertyIn("d.id", dc));
        int count = Integer.parseInt(nonDetached.setProjection(Projections.rowCount()).list().get(0).toString());

        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
            criteria.createCriteria("d.darkhastTaghirat","dt",specification);

        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            criteria.createCriteria("d.darkhastBazkharid","db",specification);

        criteria.add(Subqueries.propertyIn("d.id", dc));
        if (sd.getDarkhastType().equals("TAGHIRAT") || sd.getDarkhastType().equals("ALL"))
            criteria.addOrder(Order.desc("dt.tarikhDarkhast"));

        if (!sd.getDarkhastType().equals("TAGHIRAT"))
            criteria.addOrder(Order.desc("db.tarikhDarkhast"));

        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(objectsPerPage);
        List<Darkhast> res = criteria.list();
        list.setFullListSize(count);
        list.setList(res);
        list.setPageNumber(pageNum + 1);
        list.setObjectsPerPage(objectsPerPage);
        return list;
    }

    public PaginatedListImpl<Darkhast> loadDarkhasts(User user, int pageNumber)
    {
        PaginatedListImpl<Darkhast> list = new PaginatedListImpl<Darkhast>();
        list.setObjectsPerPage(30);
        int pageNum = pageNumber - 1;
        final int objectsPerPage = list.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;

        DetachedCriteria dc = DetachedCriteria.forClass(Darkhast.class, "d");
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        dc.createCriteria("d.darkhastTaghirat", "dt", CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dt.creator", "dtccc", CriteriaSpecification.LEFT_JOIN)
                .createCriteria("dt.state", "dts", CriteriaSpecification.LEFT_JOIN);
        dc.createCriteria("d.darkhastBazkharid", "db", CriteriaSpecification.LEFT_JOIN)
                .createCriteria("db.creator", "dbccc", CriteriaSpecification.LEFT_JOIN)
                .createCriteria("db.state", "dbs", CriteriaSpecification.LEFT_JOIN);
//          .createCriteria("dts.transitionBegin", "dtst")
//          .createCriteria("dtst.roles", "dtstr")
//          .createCriteria("dtstr.users", "dtstrUser");

//        dc.add(Restrictions.eq("dtstrUser.id", user.getId()));

        dc.add(Restrictions.or(Restrictions.isNotNull("dbs.id"), Restrictions.isNotNull("dts.id")));

        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                dc.add
                        (
                                Restrictions.or
                                        (
                                                Restrictions.eq("dt.namayande.id", user.getNamayandegi().getId()),
                                                Restrictions.eq("db.namayande.id", user.getNamayandegi().getId())
                                        )
                        );
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL")
                    )
            {
                dc.add
                        (
                                Restrictions.or
                                        (
                                                Restrictions.or
                                                        (
                                                                Restrictions.eq("db.karshenas.id", user.getId()),
                                                                Restrictions.eq("dbccc.id", user.getId())
                                                        ),
                                                Restrictions.or
                                                        (
                                                                Restrictions.eq("dt.karshenas.id", user.getId()),
                                                                Restrictions.eq("dtccc.id", user.getId())
                                                        )
                                        )
                        );
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
//               theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                dc.add
                        (
                                Restrictions.or
                                        (
                                                Restrictions.or
                                                        (
                                                                Restrictions.gt("dts.id", 9010),
                                                                Restrictions.eq("dtccc.id", user.getId())
                                                        ),
                                                Restrictions.or
                                                        (
                                                                Restrictions.or
                                                                        (
                                                                                Restrictions.or
                                                                                        (
                                                                                                Restrictions.ne("dbs.id", 1000),
                                                                                                Restrictions.ne("dbs.id", 10000)
                                                                                        ),
                                                                                Restrictions.or
                                                                                        (
                                                                                                Restrictions.ne("dbs.id", 11000),
                                                                                                Restrictions.ne("dbs.id", 12010)
                                                                                        )
                                                                        ),
                                                                Restrictions.eq("dbccc.id", user.getId())
                                                        )
                                        )
                        );
            }
        }

        dc.setProjection(Projections.id());

        Criteria nonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class);
        nonDetached.add(Subqueries.propertyIn("id", dc));
        int count = Integer.parseInt(nonDetached.setProjection(Projections.rowCount()).list().get(0).toString());
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class, "d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("d.darkhastTaghirat", "dt", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("d.darkhastBazkharid", "db", CriteriaSpecification.LEFT_JOIN);

        criteria.add(Subqueries.propertyIn("d.id", dc));
        criteria.addOrder(Order.desc("dt.tarikhDarkhast"));
        criteria.addOrder(Order.desc("db.tarikhDarkhast"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(objectsPerPage);
        List<Darkhast> res = criteria.list();
        list.setFullListSize(count);
        list.setList(res);
        list.setPageNumber(pageNum + 1);
        list.setObjectsPerPage(objectsPerPage);
        return list;
    }

    public PaginatedListImpl<DarkhastsVM>  loadDarkhasts(User user, PaginatedListImpl<DarkhastsVM> pgList)
    {
        String whereClause = "";
        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClause += "( (db.namayande is not null and db.namayande.id = " + user.getNamayandegi().getId() + ") or (dt.namayande is not null and dt.namayande.id = " + user.getNamayandegi().getId() + ") ) and ";
            }
            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL"))
            {
                whereClause += "(( dtk.id = " + user.getId() + " or dtc.id = " + user.getId() + ") or ( dbk.id = " + user.getId() + " or dbc.id = " + user.getId() + ")) and ";
            }

            if (theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_KHESARAT") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL_KHESARAT") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_KHESARAT")
               )
            {
                whereClause +=" (dbkk.id="+user.getId() +" or dbc.id="+user.getId()+") and " ;
            }

            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                whereClause += "((dts.id > 9010 or dtc.id = " + user.getId() + " ) or ((dbs.id != 1000 or dbs.id != 10000 or dbs.id != 11000 or dbs.id != 12010) or dbc.id = " + user.getId() + ")) and ";
            }
        }
        whereClause +="(dts.id is not null or dbs.id is not null) and ";
        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count = getSession().createQuery
                (
                        "select count(d.id) " +
                                "from Darkhast d left join d.darkhastTaghirat dt left join d.darkhastBazkharid db left join dt.state dts left join db.state dbs left join db.karshenas dbk left join dt.karshenas dtk " +
                                "left join db.karshenasKhesarat dbkk left join dt.creator dtc left join db.creator dbc  " +
                                "where 1=1 " + whereClause
                );//.setParameterList("statesParam", statesId);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                (
                    "select new  com.bitarts.parsian.viewModel.DarkhastsVM(d.id,dtsp.roleColor,dbsp.roleColor, dt.tarikhDarkhast,db.tarikhDarkhast," +
                    "dtb.shomare,dbb.shomare, dtn.name, dbn.name, dtn.kodeNamayandeKargozar,dbn.kodeNamayandeKargozar, " +
                    "d.darkhastType,dtBiGozarSh.name, (select p1.bimeGozar.shakhs.name from Pishnehad p1 join p1.bimename b1 where b1.readyToShow = 'yes' and p1.valid = true and b1.id= dbb.id)," +
                    "dtBiGozarSh.nameKhanevadegi,(select p2.bimeGozar.shakhs.nameKhanevadegi from Pishnehad p2 join p2.bimename b2 where b2.readyToShow = 'yes' and p2.valid = true and b2.id= dbb.id)," +
                    "dtBiShodeSh.name,(select p3.bimeShode.shakhs.name from Pishnehad p3 join p3.bimename b3 where b3.readyToShow = 'yes' and p3.valid = true and b3.id= dbb.id)," +
                    "dtBiShodeSh.nameKhanevadegi,(select p4.bimeShode.shakhs.nameKhanevadegi from Pishnehad p4 join p4.bimename b4 where b4.readyToShow = 'yes' and p4.valid = true and b4.id= dbb.id)," +
                    "dtk.firstName, dbk.firstName, dtk.lastName, dbk.lastName, dtc.firstName, dbc.firstName, dtc.lastName, dbc.lastName, " +
                    "(select max(t1.date) as taghirTransitionLogDate from TransitionLog t1 join t1.darkhastTaghirat dt1  where dt1.id = dt.id) ," +
                    "(select max(t2.date) as bazkharidTransitionLogDate from TransitionLog t2 join t2.darkhastBazkharid db1 where db1.id = db.id) , dts.stateName, dbs.stateName, dt.id, db.id, dbkk.firstName, dbkk.lastName ) " +
                    "from Darkhast d left join d.darkhastTaghirat dt left join dt.karshenas dtk left join dt.creator dtc left join dt.state dts " +
                    "left join d.darkhastBazkharid db left join db.bimename dbb left join db.namayande dbn left join db.karshenas dbk left join db.creator dbc left join db.state dbs " +
                    "left join db.karshenasKhesarat dbkk left join dt.oldPishnehad oldPishnehad left join oldPishnehad.bimename dtb left join oldPishnehad.bimeGozar dtBimegozar left join dtBimegozar.shakhs dtBiGozarSh " +
                    "left join oldPishnehad.bimeShode dtBimeshode left join dtBimeshode.shakhs dtBiShodeSh left join dt.namayande dtn " +
                    "left join dts.peygirKonande dtsp left join dbs.peygirKonande dbsp " +
                    "where 1=1 " + whereClause //+" order by dt.tarikhDarkhast,db.tarikhDarkhast desc "
                );//.setParameterList("statesParam", statesId);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);

        return pgList;
    }

    public List<DarkhastBazkharid> findAllUnfinishedDarkhastBazkharid() {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final Criteria c = session.createCriteria(DarkhastBazkharid.class).addOrder(Order.desc("tarikhDarkhast"));
        c.add(Restrictions.or(
                Property.forName("finished").eq(false), Property.forName("finished").isNull()));
        List<DarkhastBazkharid> darkhastBazkharidList = (List<DarkhastBazkharid>) c.list();
        Set<DarkhastBazkharid> darkhastBazkharidSet = new HashSet<DarkhastBazkharid>();
        for (DarkhastBazkharid darkhastBazkharid : darkhastBazkharidList) {
            darkhastBazkharidSet.add(darkhastBazkharid);
        }
        darkhastBazkharidList = new ArrayList<DarkhastBazkharid>();
        for (DarkhastBazkharid darkhastBazkharid : darkhastBazkharidSet) {
            darkhastBazkharidList.add(darkhastBazkharid);
        }
        return darkhastBazkharidList;
    }

    public void updateAll(List<Bimename> bimename) {
        super.saveOrUpdateAll(bimename);
    }

    public List<Bimename> findAllBimenameForNamayande(Long nId, String azDate, String taDate) {
        final Criteria crit = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class).add(Restrictions.eq("readyToShow", "yes"));
        if (azDate != null && !azDate.isEmpty())
            crit.add(Restrictions.ge("tarikhSodour", azDate));
        if (taDate != null && !taDate.isEmpty())
            crit.add(Restrictions.lt("tarikhSodour", taDate));
        crit.createCriteria("pishnehadList", "p").add(Restrictions.eq("p.valid", true)).createCriteria("namayande", "n");
        crit.add(Restrictions.eq("n.id", nId));
        return crit.list();
    }

    public Bimename findById(Integer bimename) {
        return (Bimename) super.findById(Bimename.class, bimename);
    }

    public List<Bimename> findAllBimenameForNamayande(Long nId) {
        final Criteria crit = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class).add(Restrictions.eq("readyToShow", "yes")).addOrder(Order.asc("tarikhSodour"));
        crit.createCriteria("pishnehadList", "p").add(Restrictions.eq("p.valid", true)).createCriteria("namayande", "n");
        crit.add(Restrictions.eq("n.id", nId));
        return crit.list();
    }

    public List<Ghest> searchGhest(String shomare, String saleBimei, String bimeGozar,
                                   String bimeShode, String shomareMoshtari, String azTarikhSodur,
                                   String taTarikhSodur, String azSarresid, String taSarresid,
                                   String azPardakht, String taPardakht, String azMablagh,
                                   String taMablagh, String azSanad, String taSanad, State state, User user) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Ghest.class, "gh").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createCriteria("gh.ghestBandi", "gb").createCriteria("gb.bimename", "b").createCriteria("b.pishnehad", "p");
        if (user != null)
            crit.add(Restrictions.eq("p.userCreator", user));
        if (shomare != null)
            crit.add(Restrictions.eq("b.shomare", shomare));
        if (saleBimei != null) {
            crit.add(Restrictions.eq("gb.saleBimei", saleBimei));
        }

        if (bimeGozar != null)
            crit.createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "sh1").add(Restrictions.eq("sh1.name", bimeGozar));
        if (bimeShode != null)
            crit.createCriteria("p.bimeShode", "bs").createCriteria("bs.shakhs", "sh2").add(Restrictions.eq("sh2.name", bimeShode));

        if (azTarikhSodur != null)
            crit.add(Restrictions.ge("b.tarikhSodour", azTarikhSodur));
        if (taTarikhSodur != null)
            crit.add(Restrictions.lt("b.tarikhSodour", taTarikhSodur));
        if (azSarresid != null || taSarresid != null || azPardakht != null || taPardakht != null || azSanad != null || taSanad != null || azMablagh != null || taMablagh != null || shomareMoshtari != null) {
            crit.createCriteria("gh.credebit", "c");
            if (shomareMoshtari != null)
                crit.add(Restrictions.eq("c.shomareMoshtari", shomareMoshtari));
            if (azSarresid != null)
                crit.add(Restrictions.ge("gh.sarresidDate", azSarresid));
            if (taSarresid != null)
                crit.add(Restrictions.lt("gh.sarresidDate", taSarresid));
            if (azPardakht != null || taPardakht != null || azSanad != null || taSanad != null) {
                crit.createCriteria("c.khateSanadsBedehi", "kh").createCriteria("kh.sanad", "sa");
                if (azPardakht != null)
                    crit.add(Restrictions.ge("c.createdDate", azPardakht)).add(Restrictions.isNotNull("c.khateSanadsBedehi"));
                if (taPardakht != null)
                    crit.add(Restrictions.lt("c.createdDate", taPardakht)).add(Restrictions.isNotNull("c.khateSanadsBedehi"));
                if (azSanad != null)
                    crit.add(Restrictions.ge("sa.createdDate", azSanad));
                if (taSanad != null)
                    crit.add(Restrictions.lt("sa.createdDate", taSanad));
            }
            if (azMablagh != null)
                crit.add(Restrictions.ge("c.amount_long", Long.parseLong(azMablagh.replaceAll(",", ""))));
            if (taMablagh != null)
                crit.add(Restrictions.lt("c.amount_long", Long.parseLong(taMablagh.replaceAll(",", ""))));
        }
        if (state != null)
            crit.createCriteria("b.state", "st").add(Restrictions.eq("st.id", state.getId()));
        return crit.list();
    }

    private Criteria getCrit(String bimeGozar, String bimeShode, String shomare, String azTarikhSodur,
                             String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer barresiPezeshki,
                             String nahvePardakhtS, User user, String sarmayeFowt) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Bimename.class, "b").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createCriteria("b.pishnehadList", "p").add(Restrictions.eq("b.readyToShow", "yes"));
        if (user != null) {
            if(userHasRole(user, "ROLE_NAMAYANDE"))
            {
                crit.createCriteria("p.namayande", "n").add(Restrictions.eq("n.id", user.getNamayandegi().getId()));
                // this is temp
//                crit.createCriteria("p.karshenas","k").createCriteria("k.mojtamaSodoor","m").add(Restrictions.ne("m.kodeNamayandeKargozar","111115"));
            }
            else if (userHasRole(user,"ROLE_KARSHENAS_SODUR"))
            {
                crit.createCriteria("karshenas","k")
                        .createCriteria("k.mojtamaSodoor","m")
                        .add(Restrictions.eq("m.id",user.getMojtamaSodoor().getId()));
            }
        }
        if (state != null && state.getId() != null)
            crit.createCriteria("b.state", "st").add(Restrictions.eq("st.id", state.getId()));
        if (bimeGozar != null && !bimeGozar.isEmpty())
            crit.createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "sh1").add(Restrictions.or(Restrictions.ilike("sh1.name", "%" + bimeGozar + "%"),Restrictions.ilike("sh1.nameKhanevadegi", "%" + bimeGozar + "%")));
        if (bimeShode != null && !bimeShode.isEmpty())
            crit.createCriteria("p.bimeShode", "bs").createCriteria("bs.shakhs", "sh2").add(Restrictions.or(Restrictions.ilike("sh2.name", "%" + bimeShode + "%"),Restrictions.ilike("sh2.nameKhanevadegi", "%" + bimeShode + "%")));
        if (azTarikhSodur != null && !azTarikhSodur.isEmpty())
            crit.add(Restrictions.ge("b.tarikhSodour", azTarikhSodur));
        if (taTarikhSodur != null && !taTarikhSodur.isEmpty())
            crit.add(Restrictions.le("b.tarikhSodour", taTarikhSodur));
        if (shomare != null && !shomare.isEmpty())
            crit.add(Restrictions.ilike("b.shomare", "%" + shomare + "%"));
        if (nahvePardakhtS != null)
            crit.createCriteria("p.estelam", "e").add(Restrictions.eq("e.nahve_pardakht_hagh_bime", nahvePardakhtS));
        if (sarmayeFowt != null && !sarmayeFowt.isEmpty())
            crit.createCriteria("p.estelam", "e").add(Restrictions.eq("e.sarmaye_paye_fot_long", Long.parseLong(sarmayeFowt.replaceAll(",","").trim())));
        if (azSarresid != null || taSarresid != null) {
            crit.createCriteria("b.ghestBandiList", "gb").createCriteria("gb.ghestList", "gh").createCriteria("gh.credebitList", "c");
            if (azSarresid != null)
                crit.add(Restrictions.ge("gh.sarresidDate", azSarresid));
            if (taSarresid != null)
                crit.add(Restrictions.lt("gh.sarresidDate", taSarresid));
        }
        return crit;
    }
    public List<Bimename> searchBimename(String bimeGozar, String bimeShode, String shomare, String azTarikhSodur,
                                         String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer barresiPezeshki,
                                         String nahvePardakhtS, User user) {
        Criteria crit = getCrit(bimeGozar, bimeShode, shomare, azTarikhSodur,
                taTarikhSodur, azSarresid, taSarresid, state, barresiPezeshki,
                nahvePardakhtS, user, null);

        return crit.list();
    }

    public void removeDarkhast(DarkhastBazkharid darkhastBazkharid) {
        getSession().delete(darkhastBazkharid);
    }

    public PaginatedListImpl<BahreMandiVM> findBahreMandi(PaginatedListImpl<BahreMandiVM> pgList, BahreMandiVM searchVM)
    {
        String whereClause="";
//        whereClause. . .

        for (Role theRole : searchVM.getUser().getRoles())
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClause += "n.id =" + searchVM.getUser().getNamayandegi().getId() + " and ";
            }
            if
            (
                theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_RAEIS_SODUR") ||
                theRole.getRoleName().equalsIgnoreCase("ROLE_KARSHENAS_MASOUL")
            )
            {
                whereClause += "(dbk.id = " + searchVM.getUser().getId() + " or dbc.id = " + searchVM.getUser().getId() + ") and ";
            }

            if(theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS"))
            {
                whereClause += "((dbk.id is not null and dbk.mojtamaSodoor.id=" + searchVM.getUser().getMojtamaSodoor().getId() + ") or (dbk.id is null and dbc.mojtamaSodoor.id="+ searchVM.getUser().getMojtamaSodoor().getId()+")) and ";
            }

            if (theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_KARSHENAS_MASOUL") ||
                    theRole.getRoleName().equalsIgnoreCase("ROLE_PAS_RAEIS"))
            {
                whereClause += "(dbstate.id != 10000 or dbc.id = " + searchVM.getUser().getId() + ") and ";
            }
        }

        if(searchVM.getShomareBahreMandi()!=null && !searchVM.getShomareBahreMandi().isEmpty())
        {
//            if(searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE))
//            {
//                whereClause += " db.shomareBardashtAzAndukhte like '%" + searchVM.getShomareBahreMandi() + "%' and ";
//            }
//            else if (searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
//            {
//                whereClause +=" db.shomareVam like '%" +searchVM.getShomareBahreMandi() + "%' and ";
//            }
            whereClause += " (db.shomareBardashtAzAndukhte like '%" + searchVM.getShomareBahreMandi() + "%' or " + " db.shomareVam like '%" + searchVM.getShomareBahreMandi() + "%') and ";
        }

        if(searchVM.getMablagheVamFrom()!=null && !searchVM.getMablagheVamFrom().isEmpty())
        {
            whereClause +=" db.mablaghVamedarkhasti>=" + Long.parseLong(searchVM.getMablagheVamFrom().replaceAll(",","").trim()) + " and ";
        }

        if(searchVM.getMablagheVamTo()!=null && !searchVM.getMablagheVamTo().isEmpty())
        {
            whereClause +=" db.mablaghVamedarkhasti<="+ Long.parseLong(searchVM.getMablagheVamTo().replaceAll(",", "").trim())+ " and ";
        }

        if(searchVM.getShomareBimename()!=null && !searchVM.getShomareBimename().isEmpty())
        {
            whereClause +=" b.shomare like '%"+searchVM.getShomareBimename()+ "%' and ";
        }

        if(searchVM.getStateId()!=null && !searchVM.getStateId().equals(0))
        {
            whereClause +="dbstate.id="+searchVM.getStateId()+" and ";
        }

        if(searchVM.getKarshenasId()!=null && searchVM.getKarshenasId()!=0)
        {
            whereClause +="dbk.id="+searchVM.getKarshenasId()+" and ";
        }

        if(searchVM.getDarkhastType()!=null)
        {
            if(searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE))
            {
                whereClause += "db.darkhastType =:tpBardasht and ";
            }
            else if (searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
            {
                whereClause += "db.darkhastType =:tpVam and ";
            }
        }
        if (searchVM.getDarkhastType() == null)
        {
            whereClause += "(db.darkhastType =:tpVam or db.darkhastType =:tpBardasht) and ";
        }

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count = getSession().createQuery
                (
                        "select count(db.id) " +
                                "from DarkhastBazkharid db join db.bimename b join b.pishnehadList p join p.bimeGozar bg join bg.shakhs bgs left join db.namayande n join db.state dbstate left join db.karshenas dbk join db.creator dbc " +
                                "where b.readyToShow='yes' and p.valid=true " + whereClause
                );
        if(searchVM.getDarkhastType()==null || searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
                count.setParameter("tpVam", DarkhastBazkharid.DarkhastType.VAM);
        if (searchVM.getDarkhastType() == null || searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE))
                count.setParameter("tpBardasht", DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query=getSession().createQuery
        (
            "select new  com.bitarts.parsian.viewModel.BahreMandiVM(db.id, b.shomare, db.shomareVam,db.shomareBardashtAzAndukhte, bgs.name, bgs.nameKhanevadegi," +
            " n.name, n.kodeNamayandeKargozar, db.mablaghVamedarkhasti,db.mablaghDarkhastiBardasht,db.darkhastType) " +
            "from DarkhastBazkharid db join db.bimename b join b.pishnehadList p join p.bimeGozar bg join bg.shakhs bgs left join db.namayande n join db.state dbstate " +
            "left join db.karshenas dbk join db.creator dbc " +
            "where b.readyToShow='yes' and p.valid=true " + whereClause
        ) ;
        if (searchVM.getDarkhastType() == null || searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
            query.setParameter("tpVam", DarkhastBazkharid.DarkhastType.VAM);
        if (searchVM.getDarkhastType() == null || searchVM.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE))
            query.setParameter("tpBardasht", DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);

        return pgList;
    }

    public List<DarkhastBazkharid> findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day()
    {
        Query query = getSession().createQuery
        (
            "select db from DarkhastBazkharid db join db.transitionLogs tl join tl.transition t " +
            "where db.darkhastType=:dbType and db.state.id=12110 and t.id=12020 and TO_char(( TO_DATE(g.sarresid_date, 'yyyy/mm/dd', 'nls_calendar=persian') + 30 ),'yyyy/mm/dd','nls_calendar=persian')=:date "
        ).setString("date", DateUtil.getCurrentDate())
        .setParameter("dbType",DarkhastBazkharid.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM);
        return query.list();
    }

    public String executeMosharekat(String tarikhShoro, String tarikhPayan, double i) {
        try {
            String callFunc = "{? = call MOSHAREKAT_CALC(?, ?, ?, ?)}";
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setString(2, DateUtil.getCurrentDate());
            st.setDouble(3, i);
            st.setString(4, tarikhShoro);
            st.setString(5, tarikhPayan);
            st.execute();
            return st.getString(1);
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            return "FAILED";
        }

    }
}
