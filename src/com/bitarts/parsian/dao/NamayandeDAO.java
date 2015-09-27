package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.BazarYabSanam;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.SeniorSubset;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.viewModel.AghsatMoavagh;
import com.bitarts.parsian.viewModel.GStructureNamayande;
import com.bitarts.parsian.viewModel.MaliNamayande;
import com.bitarts.parsian.viewModel.RankReport;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/10/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class NamayandeDAO extends BaseDAO {
    public static String ID = "id";

    public Namayande getNamayandeByIssuanceCode(String code)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class);
        criteria.add(Restrictions.eq("issuanceCode",code));
        return (Namayande) criteria.uniqueResult();
    }

    public void addNewNamayande(Namayande namayande) {
        super.save(namayande);
    }

    public void editNamayande(Namayande namayande) {
        super.update(namayande);
    }

    public PaginatedListImpl<SeniorSubset> findSeniorSubsets(SeniorSubset seniorSubset,PaginatedListImpl<SeniorSubset> pgList)
    {
        String whereClause = "";

        if(seniorSubset!=null)
        {
            if(seniorSubset.getSenior()!=null && seniorSubset.getSenior().getId()!=null)
            {
                whereClause +=" s.senior.id="+seniorSubset.getSenior().getId()+" and ";
            }

            if(seniorSubset.getSubset()!=null && seniorSubset.getSubset().getId()!=null)
            {
                whereClause +=" s.subset.id="+seniorSubset.getSubset().getId()+" and ";
            }
        }

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query query = getSession().createQuery("select s from SeniorSubset s where 1=1 " + whereClause);

        pgList.setFullListSize(query.list().size());

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        return pgList;
    }

    public Namayande getNamayandeByKodeKargozar(String kodeKargozar)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class).add(Restrictions.eq("kodeNamayandeKargozar",kodeKargozar));
        return (Namayande) criteria.uniqueResult();
    }

    public Namayande getNamayandeById(Long id)
    {
        return (Namayande) super.findById(Namayande.class, id);
    }
    public BazarYabSanam getBazaryabSanamById(Long id)
    {
        return (BazarYabSanam) super.findById(BazarYabSanam.class, id);
    }

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl paginatedList) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class);

        paginatedList.setFullListSize(criteria.list().size());

        //add paging
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());

        List<Namayande> list = criteria.list();

        paginatedList.setList(list);

        return paginatedList;
    }

    public List<Namayande> getAllNamayande() {
        return super.findAllWithOrderByAsc(Namayande.class, "kodeNamayandeKargozar");
    }

    public PaginatedListImpl<MaliNamayande> findMaliNamayandeHaList(String sarresidDateFrom,String sarresidDateTo,Long namayandeId) {
        String hql = "";
        String hqlBankInfo = "";
        String hqlNamayande = "";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0){
            hql += " AND c.sarresidDate >= " + sarresidDateFrom;
            hqlBankInfo += " AND b.taarikh >= " + sarresidDateFrom;
        }

        if (sarresidDateTo != null && sarresidDateTo.length() > 0){
            hql += " AND c.sarresidDate <= " + sarresidDateTo;
            hqlBankInfo += " AND b.taarikh <= " + sarresidDateTo;
        }

        if (namayandeId != null && namayandeId > 0){
            hqlNamayande += " WHERE n.id = " + namayandeId;
        }

        Query query = getSession().createQuery("select new com.bitarts.parsian.viewModel.MaliNamayande(" +
                "(select nvl(sum(c.amount_long),0) from Credebit c where c.namayande.id = n.id and (c.credebitType = 'VEHICLE_HAGHBIME' OR c.credebitType = 'VEHICLE_HAGHBIME_ELECTRONICI')" + hql + ")," +
                "(select nvl(sum(c.amount_long),0) from Credebit c where c.namayande.id = n.id and c.credebitType = 'VEHICLE_HAGHBIME_BARGASHTI'" + hql +")," +
                "(select nvl(sum(c.amount_long),0) from Credebit c join c.bankInfoList b where c.namayande.id = n.id " + hqlBankInfo + ")," +
                "n" +
                ")" +
                " FROM Namayande n " + hqlNamayande + " group by n.id");


        PaginatedListImpl<MaliNamayande> paginatedList = new PaginatedListImpl<MaliNamayande>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport=false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if((name.startsWith("d-") && name.endsWith("-e")))
                {
                    isExport=true;
                }
            }
        }
        if(isExport)
        {
            List list = query.list();
            paginatedList.setPageNumber(1);
            ArrayList rtnList=new ArrayList();
            for(int i=0;i< list.size();i++)
            {
                rtnList.add(list.get(i));
            }
            if(list != null)
                paginatedList.setList(rtnList);
        }
        else
        {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
            ArrayList rtnList=new ArrayList();
            query.setMaxResults(firstResult+paginatedList.getObjectsPerPage());
            query.setFirstResult(firstResult);
            List list = query.list();
            for(int i=firstResult;i<firstResult+paginatedList.getObjectsPerPage()&& i<list.size();i++)
            {
                rtnList.add(list.get(i));
            }
            if(list != null)
                paginatedList.setList(rtnList);
            rtnList=new ArrayList();
            for(int i=0;i<Integer.MAX_VALUE && i<list.size();i++)
            {
                rtnList.add(list.get(i));
            }

            Query queryCount = getSession().createQuery("SELECT count(n.id) FROM Namayande n " + hqlNamayande );
            Long count = Long.parseLong(queryCount.list().get(0).toString());
            paginatedList.setFullListSize(count.intValue());
        }

        return paginatedList;
    }

    public void removeNamayande(Long id) {
        super.deleteById(Namayande.class, id);
    }

    public List<Namayande> getAllNamayandeByType(String type) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final List<Namayande> list = session.createCriteria(Namayande.class).add(Restrictions.eq("type", type)).list();
        return list;
    }

    public int findNamayandeOfVahedSodor(Long namayandeId)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class).createCriteria("vahedSodur").add(Restrictions.eq("id",namayandeId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setProjection(Projections.rowCount()).uniqueResult();
        return (Integer) criteria.list().get(0) ;
    }

    public List<Namayande> searchNamayande(RankReport rankReport)
    {
        final Session session = getSession();
        boolean isOstan=false;
        final Criteria crit = session.createCriteria(Namayande.class, "n");
        if (rankReport.getOstan().getCityName() != null && !rankReport.getOstan().getCityName().isEmpty())
        {
            crit.createCriteria("n.ostan", "o").add(Restrictions.eq("o.cityId", rankReport.getOstan().getCityId()));isOstan=true;
        }
        if (rankReport.getShahr().getCityName() != null && !rankReport.getShahr().getCityName().isEmpty())
            crit.createCriteria("n.shahr", "s").add(Restrictions.eq("s.cityId", rankReport.getShahr().getCityId()));
        if (rankReport.getVahedSodur().getName() != null && !rankReport.getVahedSodur().getName().isEmpty())
            crit.add(Restrictions.eq("n.vahedSodur.id", rankReport.getVahedSodur().getId()));
        if (rankReport.getVahedSodurPedar().getName()!= null && !rankReport.getVahedSodurPedar().getName().isEmpty())
            crit.createCriteria("n.vahedSodur", "v").add(Restrictions.eq("v.vahedSodur.id", rankReport.getVahedSodurPedar().getId()));
        if (rankReport.getNamNamayande().getName()!= null && !rankReport.getNamNamayande().getName().isEmpty())
            crit.add(Restrictions.eq("n.id", rankReport.getNamNamayande().getId()));
        if(!isOstan)
            crit.createCriteria("n.ostan", "o");
        crit.addOrder(Order.asc("o.cityName"));
        if (rankReport.getLaghvCheckBox().equals("yes"));
        return crit.list();
    }

//    public List<RankReport> rotbeBandiNamayande()
//    {
//        getSession().createQuery("select")
//    }

    public List<GStructureNamayande> makeGozareshMoghayese(Integer filter, String azTarikh, String taTarikh) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Namayande.class);
        if (azTarikh == null || azTarikh.isEmpty())
            azTarikh = "0000/00/00";
        if (taTarikh == null || taTarikh.isEmpty())
            taTarikh = "9999/99/99";
        List<Namayande> list = getAllNamayande();
        for (Namayande n : list)
            n.setRankValue(Double.parseDouble(Integer.toString(n.calcTedadPishnehadat(30, azTarikh, taTarikh))));
        Collections.sort(list);
        if (filter != null && filter != -1)
            list = list.subList(0, filter);
        /*Query q=session.createQuery("select distinct n.id from Namayande n,Pishnehad p where p.state.id=30 and p.namayande.id=n.id and p.createdDate>=:az and p.createdDate<:ta group by n.id order by count(p) desc").setParameter("az",azTarikh).setParameter("ta",taTarikh);
        if (filter != null && filter != -1)
            q.setMaxResults(filter);
        List<Namayande> list=session.createQuery("select n from Namayande n where n.id in (:s)").setParameterList("s",q.list()).list();*/
        List<GStructureNamayande> gStructureNamayandeList = new ArrayList<GStructureNamayande>(list.size());
        for (Namayande n : list) {
            GStructureNamayande tmp = new GStructureNamayande(n.getName() + "/" + n.getKodeNamayandeKargozar(), n.calcTedadPishnehadat(null, azTarikh, taTarikh), n.calcTedadPishnehadat(30, azTarikh, taTarikh),
                    n.calcTedadPishnehadat(290, azTarikh, taTarikh), n.calcTedadPishnehadat(300, azTarikh, taTarikh), n.calcTedadPishnehadat(310, azTarikh, taTarikh), n.calcTedadPishnehadat(320, azTarikh, taTarikh));
            gStructureNamayandeList.add(tmp);
        }
        Collections.sort(gStructureNamayandeList);
        return gStructureNamayandeList;

    }

    public List<Namayande> doSearch(String name, String code) {
        List<Namayande> namayandes = super.findLikesByProperties(Namayande.class,new String[]{"name", "kodeNamayandeKargozar"}, new Object[]{name, code});
        return namayandes;
    }

    public List<BazarYabSanam> doSearchBazSan(String name, String code)
    {
        List<BazarYabSanam> allBazSan = super.findLikesByProperties(BazarYabSanam.class,new String[]{"name", "code"}, new Object[]{name, code});
        return allBazSan;
    }

    public PaginatedListImpl<Namayande> findAllNamayande(PaginatedListImpl<Namayande> paginatedList, String nname, String ncode, String ntype, Long vSodurId, Long ostanId, Long shahrId) {
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class, "n").setCacheable(true);
        Criteria criteria2 = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class, "n").setCacheable(true);
        if (nname != null && !nname.isEmpty()) {
            criteria.add(Restrictions.ilike("n.name", nname,  MatchMode.ANYWHERE));
            criteria2.add(Restrictions.ilike("n.name", "%" + nname + "%"));
        }
        if (ncode != null && !ncode.isEmpty()) {
            criteria.add(Restrictions.ilike("n.kodeNamayandeKargozar", "%" + ncode + "%"));
            criteria2.add(Restrictions.ilike("n.kodeNamayandeKargozar", "%" + ncode + "%"));
        }
        if (ntype != null && !ntype.isEmpty()) {
            criteria.add(Restrictions.eq("n.type", ntype));
            criteria2.add(Restrictions.eq("n.type", ntype));
        }
        if (vSodurId != null) {
            criteria.createCriteria("n.vahedSodur", "v").add(Restrictions.eq("v.id", vSodurId));
            criteria2.createCriteria("n.vahedSodur", "v").add(Restrictions.eq("v.id", vSodurId));
        }
        if (ostanId != null) {
            criteria.createCriteria("n.ostan", "o").add(Restrictions.eq("o.cityId", ostanId));
            criteria2.createCriteria("n.ostan", "o").add(Restrictions.eq("o.cityId", ostanId));
        }
        if (shahrId != null) {
            criteria.createCriteria("n.shahr", "s").add(Restrictions.eq("s.cityId", shahrId));
            criteria2.createCriteria("n.shahr", "s").add(Restrictions.eq("s.cityId", shahrId));
        }
        criteria2.setProjection(Projections.rowCount());
        paginatedList.setFullListSize((Integer) Integer.parseInt(criteria2.list().get(0).toString()));
        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<Namayande> list = criteria.list();
        paginatedList.setList(list);
        return paginatedList;
    }

    public PaginatedListImpl<Namayande> findAllSenior(PaginatedListImpl<Namayande> paginatedList)
    {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.isNotNull("arshad"))
                .add(Restrictions.not(Restrictions.like("kodeNamayandeKargozar", "3", MatchMode.START)));

        int maxResults = paginatedList.getObjectsPerPage() * paginatedList.getPageNumber();
        paginatedList.setFullListSize(criteria.list().size());
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List<Namayande> list = criteria.list();
        paginatedList.setList(list);
        paginatedList.setPageNumber(paginatedList.getPageNumber()+1);
        return paginatedList;
    }

    public List<Namayande> getChildNamayande(Long namayandeId){
        String sql = "SELECT n.id" +
                //" , CONNECT_BY_ISCYCLE ," +
                //"   LEVEL, SYS_CONNECT_BY_PATH(n.id, '#') " +
                "   FROM tbl_namayande n" +
                "   START WITH n.id = " + namayandeId +
                "   CONNECT BY NOCYCLE PRIOR n.id = n.sarparast_id";
        SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        List namayandelist = sqlQuery.list();
        List<Namayande> namayandeListResult = new ArrayList<Namayande>();
        for (Object namayande : namayandelist){
            Long.parseLong(namayande.toString());
        }
        return namayandeListResult;
    }

    public List<Namayande> getChildNamayande(String name, String code, User user){
        if (user != null && user.getNamayandegi() != null){
            String sql = "select na.* from tbl_namayande na " +
                    " left join (SELECT n.id FROM tbl_namayande n START WITH id = " + user.getNamayandegi().getId() + " CONNECT BY NOCYCLE PRIOR id = sarparast_id)nam on nam.id = na.id " +
                    " where nam.id is not null ";

            if (code != null && code.length() > 0)
                sql += " and na.kodenamayandekargozar like '" + code+"%'";
            if (name != null && name.length() > 0)
                sql += " and na.name like '%" + name + "%'";
            SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
            List<Object[]> namayandelist = sqlQuery.list();
            if (namayandelist.size() > 9)
                namayandelist = namayandelist.subList(0,9);

            List<Namayande> namayandeListResult = new ArrayList<Namayande>();
            for (Object[] namayande : namayandelist){
                Long namId = Long.parseLong(namayande[0].toString());
                if (namId != null){
                    Namayande myNamayande = getNamayandeById(namId);
                    if (myNamayande != null)
                        namayandeListResult.add(myNamayande);
                }
            }
            return namayandeListResult;
        }else{
            return doSearch(name,code);
        }
    }
    public List<BazarYabSanam> getBazaryabSanam(String name, String code, User user)  //search bazaryab
    {
        Criteria cr = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BazarYabSanam.class);
        try
        {
            if (code.equals("")) //agar code bazaryab khali bood
            {
                code = "0";
            }
            Criterion nameBazaryab = Restrictions.ilike("name", name, MatchMode.ANYWHERE);
            Criterion codeBazaryab = Restrictions.eq("code",Long.parseLong(code));

        LogicalExpression orExp = Restrictions.or(nameBazaryab, codeBazaryab);
        cr.add(orExp).add(Restrictions.eq("vahedSodor.id", user.getMojtamaSodoor().getId()));  //

           List<BazarYabSanam> list = (List<BazarYabSanam>) cr.list();
            if (list.size() == 0)
            {
              return null;
            }
            else
            {
              return list;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

}
