package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.GStructure;
import com.bitarts.parsian.viewModel.GStructureTafkikVaziat;
import com.bitarts.parsian.viewModel.GStructureTransitionLog;
import com.bitarts.parsian.viewModel.PishnehadEstelam;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 3/6/11
 * Time: 4:25 PM
 */
@SuppressWarnings({"ALL"})
public class PishnehadDAO extends BaseDAO {
    public static String CREDEBIT_SEARCH_HQL = "from Credebit as cre where cre.createdDate like :attr1 and cre.amount_long like :attr2 and cre.shomareMoshtari like :attr3 and cre.status = :attr4 and cre.credebitType = :attr5";
    public static String ID = "id";
    public static String radif = "radif";
    public static String created_date = "createdDate";
    public static String namayande = "namayande";
    public static String BimeGozar = "BimeGozar";
    public static String bimeShode = "BimeShode";
    public static String nazarePezeshkeMotamed = "nazarePezeshkeMotamed";
    public static String soalateOmomiAzBimeShode = "soalateOmomiAzBimeShode";
    public static String vaziateSalamatiBimeShode = "vaziateSalamatiBimeShode";
    public static String vaziateSalamatiKhanevadeyeBimeShodeList = "vaziateSalamatiKhanevadeyeBimeShodeList";
    public static String savabegheBimei = "savabegheBimei";
    public static String foroshande = "foroshande";
    public static String estefadeKonandeganAzSarmayeBime = "estefadeKonandeganAzSarmayeBime";
    public static String BIME_NAME = "bimename";
    public static String userCreator = "userCreator";
    public static String bazarYab = "bazarYab";

    public void save(Pishnehad pishnehad) {
        super.save(pishnehad);
    }

    public void update(Pishnehad pishnehad) {
        super.update(pishnehad);
    }

    public List<Pishnehad> findAll() {
        return getSession().createCriteria(Pishnehad.class).add(Restrictions.eq("valid", true)).list();
    }

    public List<String> getFoundFishs(Integer pishnehadId)
    {
        Query query= getSession().createQuery("select f.isFound from Pishnehad p join p.fishs f where p.id = :pishId").setInteger("pishId",pishnehadId);
        return query.list();
    }

    public PaginatedListImpl findAllowedPishnehads(User user, PaginatedListImpl pgList, List<Integer> states)
    {
        String whereClause="";

        boolean hasKarshenasi = false, hasNezrat = false;

        if (OmrUtil.userHasRole(user, Constant.ROLE_NAMAYANDE))
        {
            whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_BAZARYAB))
        {
            whereClause+= "p.bazarYab.id="+user.getId()+" and ";
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR))
        {
            hasKarshenasi = true;
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                || OmrUtil.userHasRole(user, Constant.ROLE_RAEIS_SODUR)) {
            hasNezrat = true;
        }

        if(hasKarshenasi && hasNezrat) {
            // this works only for karshenas nazer & karshenas sodour baham
//            whereClause += "((kn.id = " + user.getId() + " and p.state.id != 249 and p.state.id != 251 and p.state.id != 252) or (k.id = " + user.getId() + " and p.state.id != 248)) and ";
            whereClause += "((kn.id = " + user.getId() + " and p.state.id = 248) or (k.id = " + user.getId() + " and p.state.id != 248)) and ";
        } else if(hasKarshenasi) {
            whereClause += "k.id = " + user.getId() + " and ";
        } else if(hasNezrat) {
            whereClause += "(kn.id = " + user.getId() + " or p.state.id != 248) and ";
        }

        if(states.size()!=0)
        {
            whereClause +="p.state.id in (:statesId) and ";
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count=getSession().createQuery
                (
                    "select count(p.id) from Pishnehad p left join p.karshenas k left join p.karshenasNazer kn left join p.namayande.sarparast s where p.valid=true  " + whereClause +
                    " order by p.jadidDate desc, p.radif desc"
                );
        if(states.size()!=0)
        {
            count.setParameterList("statesId", states);
        }

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query=getSession().createQuery
                    (
                        "select new com.bitarts.parsian.viewModel.PishnehadsVM(p.state.peygirKonande.roleColor, p.id, p.radif, p.namayande.name, s.name, k.firstName, k.lastName, p.jadidDate, p.state.stateName, " +
                        "p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, p.noeGharardad, p.namayande.ostan.cityName, p.estelam.sarmaye_paye_fot_long) " +
                        "from Pishnehad p left join p.karshenas k left join p.karshenasNazer kn left join p.namayande.sarparast s " + //join p.state s join s.transitionBegin t join t.roles r join r.users u " +
                        "where p.valid=true " + whereClause +
                        " order by p.jadidDate desc, p.radif desc"
                    );
        if(states.size()!=0)
        {
            query.setParameterList("statesId", states);
        }

        query.setFirstResult(pgList.getPageNumber()*pgList.getObjectsPerPage());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;

    }

    public boolean hasPermissionToViewAllPishnehad(User user, Integer pishnehadId,String tab)
    {
        String whereClause = "";

        if (userHasRole(user, "ROLE_BAZARYAB"))
        {
            whereClause += " p.bazarYab_id =" + user.getId() + " and ";
        }
        else if (userHasRole(user, "ROLE_NAMAYANDE"))
        {
            whereClause += " p.namayande_id = " + user.getNamayandegi().getId() + " and ";
        }
        else if (userHasRole(user, "ROLE_KARSHENAS_SODUR"))
        {
            if(tab==null || !tab.equals("3"))
            {
                whereClause += " p.karshenas_id = " + user.getId() + " and ";
            }
        }
        else if (!userHasRole(user, "ROLE_SUPERVISOR"))
        {
            whereClause += " p.state_id > 29 and ";
        }

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery
                (
                        "select count(*) from tbl_Pishnehad p " +
                                "where p.id = :pishId  " + whereClause
                ).setInteger("pishId", pishnehadId);


        return Integer.parseInt(query.uniqueResult().toString()) == 0 ? false : true;

    }
    public boolean hasPermissionToViewPisnehad(User user, Integer pishnehadId)
    {
        String whereClause = "";

        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles)
        {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE))
            {
                whereClause += " p.namayande_id = " + user.getNamayandegi().getId() + " and ";
            }
//            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR))
//            {
//                whereClause += " p.karshenas_id = " + user.getId() + " and ";
//            }
        }


            whereClause +=  " p.state_id in ("+
                                                "select s.state_id from tbl_state s " +
                                                "           join tbl_transition t on t.state_id1=s.state_id " +
                                                "           join rel_role_transition rt on rt.transition_id=t.transition_id " +
                                                "           join tbl_role r on r.role_id = rt.role_id " +
                                                "           where r.role_id in ( " +
                                                "                                  select r1.role_id from tbl_users u " +
                                                "                                             join rel_user_role ur on ur.user_id = u.user_id " +
                                                "                                             join tbl_role r1 on r1.role_id = ur.role_id " +
                                                "                                  where u.user_id=" + user.getId()+
                                                "                              )" +
                            "               ) and " ;

        if (whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery
                        (
                            "select count(*) from tbl_Pishnehad p " +
                            "where  p.id = :pishId " + whereClause
                        ).setInteger("pishId",pishnehadId);


        return Integer.parseInt(query.uniqueResult().toString())==0 ? false:true;
    }

    public PaginatedListImpl findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl pgList,List<Integer> states)
    {
        String whereClause = "";

        boolean hasKarshenasi = false, hasNezrat = false;

        if (OmrUtil.userHasRole(user, Constant.ROLE_NAMAYANDE)) {
            whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
            hasKarshenasi = true;
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_BAZARYAB))
        {
            whereClause += "p.bazarYab.id=" + user.getId() + " and ";
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                || OmrUtil.userHasRole(user, Constant.ROLE_RAEIS_SODUR)) {
            hasNezrat = true;
        }

        if (hasKarshenasi && hasNezrat) {
            // this works only for karshenas nazer & karshenas sodour baham
//            whereClause += "(kn.id = " + user.getId() + " and p.state.id != 249 and p.state.id != 251 and p.state.id != 252) or (k.id = " + user.getId() + " and p.state.id != 248) and ";
            whereClause += "((kn.id = " + user.getId() + " and p.state.id = 248) or (k.id = " + user.getId() + " and p.state.id != 248)) and ";
        } else if (hasKarshenasi) {
            whereClause += "k.id = " + user.getId() + " and ";
        } else if (hasNezrat) {
            whereClause += "(kn.id = " + user.getId() + " or p.state.id != 248) and ";
        }

        if(pishnehadSearch.getGroupId()!=null && pishnehadSearch.getGroupId()>0)
        {
            whereClause += "gh.id = " + pishnehadSearch.getGroupId() + " and ";
        }

        //bimeShode
        if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("") ||
            !pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("") ||
            !pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase(""))
        {

            if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs.name like '%" + pishnehadSearch.getNameBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs_nameKhanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs.kodeMelliShenasayi = '" + pishnehadSearch.getKodeMelliBimeShode() + "' and ";
            }
        }

        //BimeGozar
        if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase(""))
        {

            if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.name like '%" + pishnehadSearch.getNameBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.nameKhanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.kodeMelliShenasayi = '" + pishnehadSearch.getKodeMelliBimeGozar() + "' and ";
            }

        }

        if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getOstan().trim().equalsIgnoreCase("") ||
            userHasRole(user, "ROLE_NAMAYANDE"))
        {

            String query = "";
            if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase(""))
            {
                whereClause += "p.namayande.kodeNamayandeKargozar ='" + pishnehadSearch.getKodeNamayandeKargozar() + "' and ";
            }

            if (!pishnehadSearch.getOstan().trim().equalsIgnoreCase(""))
            {
                whereClause += "p.namayande.ostan.cityName ='" + pishnehadSearch.getOstan() + "' and ";
            }

            if (userHasRole(user, "ROLE_NAMAYANDE"))
            {
                whereClause += "p.namayande.id = '" + user.getNamayandegi().getId() + "' and ";
            }
        }

        if (!pishnehadSearch.getState().equalsIgnoreCase(""))
        {
            whereClause +="p.state.stateName = '" + pishnehadSearch.getState() + "' and ";
        }

        if (!pishnehadSearch.getRadif().equalsIgnoreCase(""))
        {
            whereClause += "p.radif like '%" + pishnehadSearch.getRadif() + "%' and ";
        }

        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase(""))
        {
            whereClause += "p.createdDate >= '" + pishnehadSearch.getAzTarikh() + "' and p.createdDate <= '" + pishnehadSearch.getTaTarikh() +"' and " ;
        }

        if (pishnehadSearch.getKarshenasId() != -1) {
            if(pishnehadSearch.getKarshenasId() == -2)
            {
//                pishnehads.add(Restrictions.isNull("karshenas"));
                whereClause += "p.karshenas is null  and ";
            }
            else
            {
                whereClause += "p.karshenas.id =" + pishnehadSearch.getKarshenasId() + " and ";
            }
        }

        if(pishnehadSearch.getKodeSarparast()!=null)
        {
            if (!pishnehadSearch.getKodeSarparast().equalsIgnoreCase(""))
            {
                whereClause += "s.kodeNamayandeKargozar= '" + pishnehadSearch.getKodeSarparast() + "' and ";
            }
        }

        if (!pishnehadSearch.getNoePardakht().equalsIgnoreCase(""))
        {
            whereClause += "p.pishpardakhtType = '" + pishnehadSearch.getNoePardakht() + "' and ";
        }

        if (!pishnehadSearch.getNoeBimename().equalsIgnoreCase(""))
        {
            whereClause += "p.noeBimename = '" + pishnehadSearch.getNoeBimename() + "' and ";
        }

        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase(""))
        {
            whereClause += "p.noeGharardad = '" + pishnehadSearch.getNoeGharardad() + "' and ";
        }

        if (!pishnehadSearch.getNahvePardakhtHagheBime().equalsIgnoreCase(""))
        {
            whereClause += "p.estelam.nahve_pardakht_hagh_bime = '" + pishnehadSearch.getNahvePardakhtHagheBime() + "' and ";
        }

        if (!pishnehadSearch.getPishpardakhtOK().equalsIgnoreCase(""))
        {
//            List<Integer> id= getSession().createQuery("select p.id from fishs f join f.pishnehad p where p.valid = true and f.isFound = 'true' ").list();
            whereClause += "p.id in (select p1.id from Fish f1 join f1.pishnehad p1 where p1.valid = true and f1.isFound = 'true') and ";
        }

        if (userHasRole(user, "ROLE_BAZARYAB"))
        {
            whereClause += "bzy.id =" + user.getId() + " and ";
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count=getSession().createQuery
                    (
                        "select count(p.id) from Pishnehad p left join p.karshenas k left join p.gharardad gh left join p.bazarYab bzy left join p.karshenasNazer kn where p.valid=true and p.state.id in (:statesId) " + whereClause +
                        " order by p.jadidDate desc ,p.radif desc"
                    ).setParameterList("statesId", states);

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query=getSession().createQuery
                    (
                        "select new com.bitarts.parsian.viewModel.PishnehadsVM(p.state.peygirKonande.roleColor, p.id, p.radif, p.namayande.name, s.name, k.firstName, k.lastName, p.jadidDate, p.state.stateName, " +
                                                "p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, p.noeGharardad, p.namayande.ostan.cityName, p.estelam.sarmaye_paye_fot_long) " +
                        "from Pishnehad p left join p.karshenas k left join p.karshenasNazer kn left join p.gharardad gh left join p.bazarYab bzy  left join p.namayande.sarparast s " + //join p.state s join s.transitionBegin t join t.roles r join r.users u " +
                        "where p.valid=true and p.state.id in (:statesId)"+
                         whereClause +
                        " order by p.jadidDate desc ,p.radif desc"
                    ).setParameterList("statesId",states);

        query.setFirstResult(pgList.getPageNumber()*pgList.getObjectsPerPage());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);

        return pgList;
    }

    public PaginatedListImpl<Pishnehad> findAllowedPishnehads(User user, PaginatedListImpl<Pishnehad> paginatedList) {
        int pageNum = paginatedList.getPageNumber();
        final int objectsPerPage = paginatedList.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;
        DetachedCriteria pishnehads = DetachedCriteria.forClass(Pishnehad.class).add(Restrictions.eq("valid", true));
        pishnehads.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        pishnehads.createCriteria("state")
                .createCriteria("transitionBegin")
                .createCriteria("roles")
                .createCriteria("users").add(Restrictions.eq("id", user.getId()));
        Set<Role> theRoles = user.getRoles();
        for (Role theRole : theRoles) {
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_NAMAYANDE)) {
                pishnehads.createAlias("namayande", "n")
                        .add(Restrictions.eq("n.id", user.getNamayandegi().getId()));
            }
            if (theRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR)) {
                pishnehads.add(Restrictions.isNotNull("karshenas"))
                        .add(Restrictions.eq("karshenas.id", user.getId()));
            }
        }

        pishnehads.setProjection(Projections.id());

        Criteria pishnehadsNonDetachedCount = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class);
        pishnehadsNonDetachedCount.add(Subqueries.propertyIn("id", pishnehads));
        int count = Integer.parseInt(pishnehadsNonDetachedCount.setProjection(Projections.rowCount()).list().get(0).toString());
        Criteria pishnehadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class, "p").createCriteria("p.bimeShode", "bs")
                .createCriteria("bs.shakhs","bss").createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "bgs");
        pishnehadsNonDetached.add(Subqueries.propertyIn("p.id", pishnehads));
        pishnehadsNonDetached.addOrder(Order.desc("p.createdDate")).addOrder(Order.desc("p.radif"));
        pishnehadsNonDetached.setFirstResult(firstResult);
        pishnehadsNonDetached.setMaxResults(objectsPerPage);
        List<Pishnehad> res = pishnehadsNonDetached.list();

        paginatedList.setFullListSize(count);
        paginatedList.setList(res);
        paginatedList.setPageNumber(pageNum + 1);
        paginatedList.setObjectsPerPage(objectsPerPage);
        return paginatedList;

    }

    public PaginatedListImpl<Pishnehad> gozareshAndukhteSaalAvaal(Long gharardadId){
        DetachedCriteria criteria = DetachedCriteria.forClass(Pishnehad.class, "p")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.add(Restrictions.eq("p.valid", true));
        criteria.createCriteria("p.gharardad","g");
        criteria.add(Restrictions.and(Restrictions.isNotNull("p.gharardad"),Restrictions.eq("g.id",gharardadId)));
        criteria.createCriteria("p.bimename","b");
        criteria.add(Restrictions.and(Restrictions.isNotNull("p.bimename"), Restrictions.eq("b.readyToShow", "yes")));
        criteria.setProjection(Projections.id());

        PaginatedListImpl<Pishnehad> paginatedList = new PaginatedListImpl<Pishnehad>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                    break;
                }
            }
        }
        paginatedList.setPageNumber(pageNumber);

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class, "p");
        count.add(Subqueries.propertyIn("p.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class, "p");
        nonDetachedCriteria.add(Subqueries.propertyIn("p.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("p.id"));
        nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());
        return paginatedList;
    }

    public boolean isMoreThanOnePishnehadByShakhs(String codeMeli)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class,"p").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("p.valid",true));
        criteria.createCriteria("p.bimeShode","bs").createCriteria("bs.shakhs","bss");
        criteria.createCriteria("p.bimeGozar","bg").createCriteria("bg.shakhs","bgs");
        criteria.add
        (
            Restrictions.or
            (
                Restrictions.eq("bss.kodeMelliShenasayi",codeMeli),
                Restrictions.eq("bgs.kodeMelliShenasayi",codeMeli)
            )
        );
        if(criteria.list().size()>1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Pishnehad findById(Integer pishnehadId) {
        return (Pishnehad) super.findById(Pishnehad.class, pishnehadId);
    }

    public Pishnehad findPishnehadById(Integer id) {
        return (Pishnehad) super.findById(Pishnehad.class, id);
    }

    public List<Transition> findAllowedTransitionsForId(Integer id) {
        Pishnehad pishnehad = (Pishnehad) super.findById(Pishnehad.class, id);
        State state = (State) super.findById(State.class, pishnehad.getState().getId());


        return null;  //To change body of created methods use File | Settings | File Templates.
    }

//    public PaginatedListImpl<Pishnehad> findAllowedToViewPishnehads(User user, PaginatedListImpl<Pishnehad> paginatedList) {
//        int pageNum = paginatedList.getPageNumber();
//        final int objectsPerPage = paginatedList.getObjectsPerPage();
//        final int firstResult = objectsPerPage * pageNum;
//
//        DetachedCriteria pishnehads = DetachedCriteria.forClass(Pishnehad.class).add(Restrictions.eq("valid", true));
//        pishnehads.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        if (userHasRole(user, "ROLE_BAZARYAB")) {
//            pishnehads.createAlias("bazarYab", "b").add(Restrictions.eq("b.id", user.getId()));
//        } else if (userHasRole(user, "ROLE_NAMAYANDE")) {
//            pishnehads.createAlias("namayande", "n").add(Restrictions.eq("n.id", user.getNamayandegi().getId()));
//        } else if (userHasRole(user, "ROLE_KARSHENAS_SODUR")) {
//            pishnehads.createAlias("karshenas", "k").add(Restrictions.isNotNull("k.id")).add(Restrictions.eq("k.id", user.getId()));
//        } else if(!userHasRole(user, "ROLE_SUPERVISOR")){
//            pishnehads.createAlias("state","s").add(Restrictions.gt("s.id", 29));
//        }
//
//        pishnehads.setProjection(Projections.id());
//
//        Criteria pishnehadsNonDetachedCount = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class);
//        pishnehadsNonDetachedCount.add(Subqueries.propertyIn("id", pishnehads));
//        int count = (Integer)(pishnehadsNonDetachedCount.setProjection(Projections.rowCount()).list().get(0));
//
//        Criteria pishnehadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class, "p").createCriteria("p.bimeShode", "bs")
//                .createCriteria("bs.shakhs","bss").createCriteria("p.bimeGozar", "bg").createCriteria("bg.shakhs", "bgs");
//        pishnehadsNonDetached.add(Subqueries.propertyIn("p.id", pishnehads));
////        pishnehadsNonDetached.addOrder(Order.desc("createdDate")).addOrder(Order.desc("radif"));
//        pishnehadsNonDetached.setFirstResult(firstResult);
//        pishnehadsNonDetached.setMaxResults(objectsPerPage);
//        List<Pishnehad> res = pishnehadsNonDetached.list();
//
//        paginatedList.setFullListSize(count);
//        paginatedList.setList(res);
//        paginatedList.setPageNumber(pageNum + 1);
//        paginatedList.setObjectsPerPage(objectsPerPage);
//        return paginatedList;
//    }

    public PaginatedListImpl findAllowedToViewPishnehads(User user, PaginatedListImpl pgList)
    {
        String whereClause = "";

        boolean hasKarshenasi = false, hasNezrat = false;

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
            hasKarshenasi = true;
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                || OmrUtil.userHasRole(user, Constant.ROLE_RAEIS_SODUR)) {
            hasNezrat = true;
        }

        if (hasKarshenasi && hasNezrat) {
            // this works only for karshenas nazer & karshenas sodour baham
            whereClause += "(kn.id = " + user.getId() + " or k.id = " + user.getId() + ") and ";
        } else if (hasKarshenasi) {
            whereClause += "k.id = " + user.getId() + " and ";
        } else if (hasNezrat) {
//            whereClause += "kn.id = " + user.getId() + " and ";
        }

        if (userHasRole(user, "ROLE_BAZARYAB"))
        {
            whereClause += "p.bazarYab.id =" + user.getId() + " and ";
        }
        else if (userHasRole(user, "ROLE_NAMAYANDE"))
        {
            whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
        }
        else if(!userHasRole(user, "ROLE_SUPERVISOR") && !hasKarshenasi && !hasNezrat)
        {
            whereClause += " p.state.id > 29 and ";
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count=getSession().createQuery
                    (
                        "select count(p.id) from Pishnehad p left join p.karshenas k left join p.karshenasNazer kn where p.valid = true " + whereClause +
                        " order by p.jadidDate desc ,p.radif desc"
                    );

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query=getSession().createQuery
                    (
                        "select new com.bitarts.parsian.viewModel.PishnehadsVM(p.state.peygirKonande.roleColor, p.id, p.radif, p.namayande.name, s.name, k.firstName, k.lastName, p.jadidDate, p.state.stateName, " +
                        "p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, p.noeGharardad, p.namayande.ostan.cityName, p.estelam.sarmaye_paye_fot_long, p.createdDate,p.bimeGozar.shakhs.name, p.bimeGozar.shakhs.nameKhanevadegi) " +
                        "from Pishnehad p left join p.karshenasNazer kn left join p.karshenas k left join p.namayande.sarparast s  " + //join p.state s join s.transitionBegin t join t.roles r join r.users u " +
                        "where p.valid=true  " + whereClause +
                        " order by p.jadidDate desc,p.radif desc"
                    );

        query.setFirstResult(pgList.getPageNumber()*pgList.getObjectsPerPage());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);

        return pgList;
    }

    public PaginatedListImpl<Pishnehad> findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> pgList)
    {
        String whereClause = "";

        boolean hasKarshenasi = false, hasNezrat = false;

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
            hasKarshenasi = true;
        }

        if (OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                || OmrUtil.userHasRole(user, Constant.ROLE_RAEIS_SODUR)) {
            hasNezrat = true;
        }

        if (hasKarshenasi && hasNezrat) {
            // this works only for karshenas nazer & karshenas sodour baham
            whereClause += "(kn.id = " + user.getId() + " or k.id = " + user.getId() + ") and ";
        } else if (hasKarshenasi) {
            whereClause += "k.id = " + user.getId() + " and ";
        } else if (hasNezrat) {
//            whereClause += "kn.id = " + user.getId() + " and ";
        }

        if (userHasRole(user, "ROLE_BAZARYAB")) {
            whereClause += "p.bazarYab.id =" + user.getId() + " and ";
        } else if (userHasRole(user, "ROLE_NAMAYANDE")) {
            whereClause += "p.namayande.id = " + user.getNamayandegi().getId() + " and ";
        } else if (!userHasRole(user, "ROLE_SUPERVISOR") && !hasKarshenasi && !hasNezrat) {
            whereClause += " p.state.id > 29 and ";
        }

        if(pishnehadSearch.getGroupId()!=null && pishnehadSearch.getGroupId()>0)
        {
            whereClause += "p.gharardad.id=" + pishnehadSearch.getGroupId() + " and ";
        }

        if(pishnehadSearch.getShomareBimename()!=null && !pishnehadSearch.getShomareBimename().isEmpty())
        {
            whereClause += "p.bimename.shomare like '%"+ pishnehadSearch.getShomareBimename() + "%' and ";
        }

        //bimeShode
        if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("") ||
            !pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("") ||
            !pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase(""))
        {

            if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs.name like '%" + pishnehadSearch.getNameBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs.nameKhanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeShode.shakhs.kodeMelliShenasayi = '" + pishnehadSearch.getKodeMelliBimeShode() + "' and ";
            }
        }

        //bimeGozar
        if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase(""))
        {
            if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.name like '%" + pishnehadSearch.getNameBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.nameKhanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase(""))
            {
                whereClause += "p.bimeGozar.shakhs.kodeMelliShenasayi = '" + pishnehadSearch.getKodeMelliBimeGozar() + "' and ";
            }
        }

        //Namayandeh
        if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("") ||
            !pishnehadSearch.getOstan().trim().equalsIgnoreCase("") ||
            userHasRole(user, "ROLE_NAMAYANDE"))
        {
            if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase(""))
            {
                whereClause += "p.namayande.kodeNamayandeKargozar ='" + pishnehadSearch.getKodeNamayandeKargozar() + "' and ";
            }

            if (!pishnehadSearch.getOstan().trim().equalsIgnoreCase(""))
            {
                whereClause += "p.namayande.ostan.cityName ='" + pishnehadSearch.getOstan() + "' and ";
            }

            if (userHasRole(user, "ROLE_NAMAYANDE"))
            {
                whereClause += "p.namayande.id ='" + user.getNamayandegi().getId() + "' and ";
            }
        }

        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase(""))
        {
            whereClause += "p.createdDate <= '"+ pishnehadSearch.getTaTarikh() + "' and p.createdDate >= '" + pishnehadSearch.getAzTarikh() + "' and ";
        }

        if (!pishnehadSearch.getState().equalsIgnoreCase(""))
        {

            whereClause += "p.state.stateName = '" + pishnehadSearch.getState() + "' and ";
        }

        if (!pishnehadSearch.getRadif().equalsIgnoreCase(""))
        {
            whereClause += "p.radif like '%" + pishnehadSearch.getRadif() + "%' and ";
        }

        if (!pishnehadSearch.getNoePardakht().equalsIgnoreCase(""))
        {
            whereClause += "p.pishpardakhtType = '" +pishnehadSearch.getNoePardakht();
        }

        if (!pishnehadSearch.getNoeBimename().equalsIgnoreCase(""))
        {
            whereClause += "p.noeBimename = '"+ pishnehadSearch.getNoeBimename() + "' and ";
        }

        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase(""))
        {
            whereClause += "p.noeGharardad = '"+ pishnehadSearch.getNoeGharardad() + "' and ";
        }
        if (!pishnehadSearch.getNahvePardakhtHagheBime().equalsIgnoreCase(""))
        {
            whereClause += "p.estelam.nahve_pardakht_hagh_bime = '" + pishnehadSearch.getNahvePardakhtHagheBime() + "' and ";
        }

//        Criteria pishnehadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class).add(Restrictions.eq("valid", true))
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (!pishnehadSearch.getPishpardakhtOK().equalsIgnoreCase(""))
        {
            whereClause += "p.id in (select p1.id from Fish f1 join f1.pishnehad p1 where p1.valid = true and f1.isFound = 'true') and ";
        }

        if (pishnehadSearch.getKarshenasId() != -1) {
                    if(pishnehadSearch.getKarshenasId() == -2)
                    {
        //                pishnehads.add(Restrictions.isNull("karshenas"));
                        whereClause += "p.karshenas is null  and ";
                    }
                    else
                    {
                        whereClause += "p.karshenas.id =" + pishnehadSearch.getKarshenasId() + " and ";
                    }
                }

        if (!userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR))
        {
            if(pishnehadSearch.getKarshenasId()!=null)
            {
                if (pishnehadSearch.getKarshenasId() != -1)
                {
                    if(pishnehadSearch.getKarshenasId() == -2)
                    {
                        whereClause += "p.karshenas is null  and ";
                    }
                    else
                    {
                        whereClause += "p.karshenas.id =" + pishnehadSearch.getKarshenasId() + " and ";
                    }
                }
            }
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count=getSession().createQuery
                    (
                        "select count(p.id) from Pishnehad p left join p.karshenas k left join p.karshenasNazer kn where p.valid = true " + whereClause +
                        " order by p.jadidDate desc ,p.radif desc"
                    );

        pgList.setFullListSize(((Long)count.uniqueResult()).intValue());

        Query query=getSession().createQuery
                    (
                        "select new com.bitarts.parsian.viewModel.PishnehadsVM(p.state.peygirKonande.roleColor, p.id, p.radif, p.namayande.name, s.name, k.firstName, k.lastName, p.jadidDate, p.state.stateName, " +
                        "p.bimeShode.shakhs.name, p.bimeShode.shakhs.nameKhanevadegi, p.noeGharardad, p.namayande.ostan.cityName, p.estelam.sarmaye_paye_fot_long, p.createdDate,p.bimeGozar.shakhs.name, p.bimeGozar.shakhs.nameKhanevadegi) " +
                        "from Pishnehad p left join p.karshenasNazer kn left join p.karshenas k left join p.namayande.sarparast s left join p.karshenasNazer kn " + //join p.state s join s.transitionBegin t join t.roles r join r.users u " +
                        "where p.valid=true  " + whereClause +
                        " order by p.jadidDate desc ,p.radif desc"
                    );

        query.setFirstResult(pgList.getPageNumber()*pgList.getObjectsPerPage());
        query.setMaxResults(pgList.getObjectsPerPage());

        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);

        return pgList;

    }

    public void addNewNazarPezeshk(PezeshkSabtNazar pezeshkSabtNazar) {
        super.save(pezeshkSabtNazar);
    }

    public PezeshkSabtNazar findPezeshkSabtNazarById(int pznId) {
        return (PezeshkSabtNazar) super.findById(PezeshkSabtNazar.class, pznId);
    }

    public void updatePezeshkSabtNazar(PezeshkSabtNazar pzn) {
        super.update(pzn);
    }

    public void saveNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        super.save(naghsPishnehad);
    }

    public void updateNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        super.update(naghsPishnehad);
    }

    public NaghsPishnehad findNaghsPishnehadById(Integer naghsPishnehadId) {
        return (NaghsPishnehad) super.findById(NaghsPishnehad.class, naghsPishnehadId);
    }

    public void removeNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        super.delete(naghsPishnehad);
    }

    public void saveFish(Fish fish) {
        fish.setMablagh(fish.getMablagh().replace(",", ""));
        super.save(fish);
    }

    public Fish findFishById(Integer fishId) {
        return (Fish) findById(Fish.class, fishId);
    }

    public List<Fish> findFish(String shomare, String bankName, String shobe, String time, String date)
    {
        List<Fish> fishs = super.findByProperties(Fish.class,
                new String[]{"shomare", "bankName", "kodeShobe", "time", "tarikh"},
                new Object[]{shomare, bankName, shobe, time, date});
        return fishs;

    }

    public void saveMoarefiname(Moarefiname moarefiname) {
        super.save(moarefiname);
    }

    public Moarefiname findMoarefiname(Integer id) {
        return (Moarefiname) super.findById(Moarefiname.class, id);
    }

    public void updateFish(Fish theFish) {
        super.update(theFish);
    }

    public int saveZamaem(Zamaem zamaem) {
        super.save(zamaem);
        return zamaem.getId();
    }

    public int saveZamaemKhesarat(ZamaemKhesarat zamaemKhesarat) {
        super.save(zamaemKhesarat);
        return zamaemKhesarat.getId();
    }

    public void updateZamaem(Zamaem zamaem) {
        super.update(zamaem);
    }

    public void updateZamaemKhesarat(ZamaemKhesarat zamaemKhesarat) {
        super.update(zamaemKhesarat);
    }

    public Zamaem findZamaemById(int zamId) {
        return (Zamaem) super.findById(Zamaem.class, zamId);
    }

    public ZamaemKhesarat findZamaemKhesaratById(int zamId) {
        return (ZamaemKhesarat) super.findById(ZamaemKhesarat.class, zamId);
    }

    public void removeNazarPezeshk(PezeshkSabtNazar pzn) {
        super.delete(pzn);
    }

    public List<Credebit> searchForFish(BankInfo bankInfo) {
        String tarikh = bankInfo.getTaarikh();
        String kod = bankInfo.getKodeShobe();
        String time = bankInfo.getTime();
        Criterion criterion = Restrictions.and(
                    Restrictions.or(Restrictions.eq(AsnadeSodorDAO.CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.DARYAFTE_FISH),
                            Restrictions.eq(AsnadeSodorDAO.CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PISHPARDAKHT)),
                Restrictions.eq(AsnadeSodorDAO.CREDEBIT__STATUS, Credebit.Status.SANAD_NA_KHORDE)
        );

        if (tarikh != null && !tarikh.equalsIgnoreCase(""))
            criterion = Restrictions.and(criterion, Restrictions.like("f.tarikh", tarikh, MatchMode.START));
        if (bankInfo.getMablagh() != null && !bankInfo.getMablagh().equalsIgnoreCase("")) {
            Long mablagh = Long.parseLong(bankInfo.getMablagh().replaceAll(",", ""));
            criterion = Restrictions.and(criterion, Restrictions.eq("amount_long", mablagh));
        }
//        if (shomare != null && !shomare.equalsIgnoreCase(""))
//            criterion = Restrictions.and(criterion, Restrictions.like("shomareMoshtari", shomare, MatchMode.START));
        if (kod != null && !kod.equalsIgnoreCase(""))
            criterion = Restrictions.and(criterion, Restrictions.like("f.kodeShobe", kod, MatchMode.START));
        if (time != null && !time.equalsIgnoreCase(""))
            criterion = Restrictions.and(criterion, Restrictions.like("f.time", time, MatchMode.END));
        return getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class, "c").createAlias("daryafteFish", "f")
                .add(criterion).setMaxResults(200).list();
    }

    public Bimename findBimenameById(int bimenameId) {
        return (Bimename) super.findById(Bimename.class, bimenameId);
    }

    public void saveAllPezeshkSabtNazar(List<PezeshkSabtNazar> pezeshkSabtNazarList) {
        super.saveOrUpdateAll(pezeshkSabtNazarList);
    }

    public void saveAllMoarefiname(List<Moarefiname> moarefinameList) {
        super.saveOrUpdateAll(moarefinameList);
    }

    public void saveAllFish(List<Fish> fishList) {
        super.saveOrUpdateAll(fishList);
    }

    public void removeMoarefiname(Integer id) {
        super.deleteById(Moarefiname.class, id);
    }

    public void saveSharayeteJadid(SharayeteJadid sharayeteJadid) {
        super.save(sharayeteJadid);
    }

    public void saveElameHesab(ElameHesab elameHesab) {
        super.save(elameHesab);
    }

    public List<Namayande> findallNamayandes() {
        return super.findAll(Namayande.class);
    }

    public List<State> findAllStatesForPishnehadSystem() {
        Criteria states = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(State.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("stateMachineName", Constant.PISHNEHAD_SYSTEM)).addOrder(Order.asc("id")).setCacheable(true);
        return states.list();
    }

    public List<User> findallKarshenasForPishnehads() {
        return super.findAll(User.class);
    }

    public List<User> findBazaryabForNamayande(Namayande namayande) {
        if (namayande == null) {
            return new ArrayList();
        }
        Criteria criteria = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("roles").add(Restrictions.or(Restrictions.eq("roleName", "ROLE_BAZARYAB"), Restrictions.eq("roleName", "ROLE_NAMAYANDE"))).addOrder(Order.desc("roleName"));
        if(namayande.getParent() != null)
            criteria.add(Restrictions.or(Restrictions.eq("namayandegi.id", namayande.getId()), Restrictions.eq("namayandegi.id", namayande.getParent().getId())));
        else
            criteria.add(Restrictions.eq("namayandegi.id", namayande.getId()));
        criteria.add(Restrictions.eq("faal", true));
        criteria.addOrder(Order.asc("firstName"));
        return criteria.list();
    }

    public List<BazarYab> findallBazaryabs() {
        Criteria bazaryabs = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        bazaryabs.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("roleName", "ROLE_BAZARYAB"));
        bazaryabs.addOrder(Order.asc("firstName")).setCacheable(true);
        return bazaryabs.list();
    }

    public BazarYab findBazarYabById(Integer id) {
        return (BazarYab) super.findById(BazarYab.class, id);
    }

//    public PaginatedListImpl<Pishnehad> findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList) {
//        int pageNum = paginatedList.getPageNumber();
//        final int objectsPerPage = paginatedList.getObjectsPerPage();
//        final int firstResult = objectsPerPage * pageNum;
//        DetachedCriteria pishnehads = DetachedCriteria.forClass(Pishnehad.class,"p").add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//        pishnehads.createAlias("bimeShode", "bss");
//        pishnehads.createCriteria("bss.shakhs");
//
//        pishnehads.createAlias("bimeGozar", "bs");
//        pishnehads.createCriteria("bs.shakhs");
//
//        pishnehads.createCriteria("namayande");
//        pishnehads.createCriteria("namayande.ostan");
//
//        if(pishnehadSearch.getGroupId()!=null && pishnehadSearch.getGroupId()>0)
//        {
//            pishnehads.createCriteria("p.gharardad","gh").add(Restrictions.eq("gh.id",pishnehadSearch.getGroupId()));
//        }
//
//        if(pishnehadSearch.getShomareBimename()!=null && !pishnehadSearch.getShomareBimename().isEmpty())
//        {
//            pishnehads.createCriteria("bimename","bi").add(Restrictions.like("bi.shomare",pishnehadSearch.getShomareBimename(),MatchMode.ANYWHERE));
//        }
//
//        //bimeShode
//        if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("") ||
//                !pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("") ||
//                !pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase("")) {
//            String query = "";
//
//            if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("")) {
//                query += "shakhs2_.name like '%" + pishnehadSearch.getNameBimeShode() + "%' and ";
//            }
//
//            if (!pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("")) {
//                query += "shakhs2_.name_khanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeShode() + "%' and ";
//            }
//
//            if (!pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase("")) {
//                query += "shakhs2_.kode_melli_shenasayi = '" + pishnehadSearch.getKodeMelliBimeShode() + "' and ";
//            }
//
//            query = query.substring(0, query.lastIndexOf(" and "));
//
//            pishnehads.add(Restrictions.sqlRestriction(query));
//        }
//
//        //bimeGozar
//        if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("") ||
//                !pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("") ||
//                !pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase("")) {
//
//            String query = "";
//            if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("")) {
//                query += "shakhs4_.name like '%" + pishnehadSearch.getNameBimeGozar() + "%' and ";
//            }
//
//            if (!pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("")) {
//                query += "shakhs4_.name_khanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeGozar() + "%' and ";
//            }
//
//            if (!pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase("")) {
//                query += "shakhs4_.kode_melli_shenasayi = '" + pishnehadSearch.getKodeMelliBimeGozar() + "' and ";
//            }
//
//            query = query.substring(0, query.lastIndexOf(" and "));
//
//            pishnehads.add(Restrictions.sqlRestriction(query));
//        }
//
//        //Namayandeh
//        if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("") ||
//                !pishnehadSearch.getOstan().trim().equalsIgnoreCase("") ||
//                userHasRole(user, "ROLE_NAMAYANDE")) {
//
//            String query = "";
//            if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("")) {
//                query += "namayande5_.kodeNamayandeKargozar ='" + pishnehadSearch.getKodeNamayandeKargozar() + "' and ";
//            }
//
//            if (!pishnehadSearch.getOstan().trim().equalsIgnoreCase("")) {
//                query += "city6_.city_Name ='" + pishnehadSearch.getOstan() + "' and ";
//            }
//
//            if (userHasRole(user, "ROLE_NAMAYANDE")) {
//                query += "namayande5_.id ='" + user.getNamayandegi().getId() + "' and ";
//            }
//
//            query = query.substring(0, query.lastIndexOf(" and "));
//            pishnehads.add(Restrictions.sqlRestriction(query));
//        }
//
//        pishnehads.createAlias("state", "s");
//
//        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.between("createdDate", pishnehadSearch.getAzTarikh(), pishnehadSearch.getTaTarikh()));
//        }
//
//
//        if (!pishnehadSearch.getState().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.eq("s.stateName", pishnehadSearch.getState()));
//        }
//
//        if (!pishnehadSearch.getRadif().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.ilike("radif", pishnehadSearch.getRadif(), MatchMode.ANYWHERE));
//        }
//
//        if (!pishnehadSearch.getNoePardakht().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.eq("pishpardakhtType", pishnehadSearch.getNoePardakht()));
//        }
//
//        if (!pishnehadSearch.getNoeBimename().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.eq("noeBimename", pishnehadSearch.getNoeBimename()));
//        }
//
//        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase("")) {
//            pishnehads.add(Restrictions.eq("noeGharardad", pishnehadSearch.getNoeGharardad()));
//        }
//
//        if (!pishnehadSearch.getNahvePardakhtHagheBime().equalsIgnoreCase("")) {
//            pishnehads.createAlias("estelam", "es");
//            pishnehads.add(Restrictions.eq("es.nahve_pardakht_hagh_bime", pishnehadSearch.getNahvePardakhtHagheBime()));
//        }
//
//        Criteria pishnehadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class).add(Restrictions.eq("valid", true))
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//
//        if (!pishnehadSearch.getPishpardakhtOK().equalsIgnoreCase("")) {
//            DetachedCriteria pishnehadsPishpardakht = DetachedCriteria.forClass(Pishnehad.class).add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            pishnehadsPishpardakht.createCriteria("fishs").add(Restrictions.eq("isFound",pishnehadSearch.getPishpardakhtOK()));
//            pishnehadsPishpardakht.setProjection(Projections.id());
//            pishnehadsNonDetached.add(Subqueries.propertyIn("id", pishnehadsPishpardakht));
//        }
//
//        if (userHasRole(user, Constant.ROLE_BAZARYAB)) {
//            pishnehads.createAlias("bazarYab", "b")
//                    .add(Restrictions.eq("b.id", user.getId()));
//        }
//
//        if (userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
//            pishnehads.createAlias("karshenas", "k");
//            pishnehads.add(Restrictions.eq("k.id", user.getId()));
//        } else if (pishnehadSearch.getKarshenasId() != -1) {
//            if(pishnehadSearch.getKarshenasId() == -2)
//            {
//                pishnehads.add(Restrictions.isNull("karshenas"));
//            }
//            else
//            {
//                pishnehads.createAlias("karshenas", "k")
//                    .add(Restrictions.eq("k.id", pishnehadSearch.getKarshenasId()));
//            }
//        }
//        if (!(userHasRole(user, Constant.ROLE_NAMAYANDE)||userHasRole(user, "ROLE_SUPERVISOR"))) {
//            pishnehads.add(Restrictions.gt("state.id", 29));
//        }
//
//        pishnehads.setProjection(Projections.id());
//
//        pishnehadsNonDetached.add(Subqueries.propertyIn("id", pishnehads));
//        pishnehadsNonDetached.addOrder(Order.desc("createdDate")).addOrder(Order.desc("radif"));
//        int count = (Integer)(pishnehadsNonDetached.setProjection(Projections.rowCount()).list().get(0));
//        pishnehadsNonDetached.setFirstResult(firstResult);
//        pishnehadsNonDetached.setMaxResults(objectsPerPage);
//        List<Pishnehad> res = pishnehadsNonDetached.setProjection(null).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//
//        paginatedList.setFullListSize(count);
//        paginatedList.setList(res);
//        paginatedList.setPageNumber(pageNum + 1);
//        paginatedList.setObjectsPerPage(objectsPerPage);
//        return paginatedList;
//    }

    public PaginatedListImpl<PishnehadEstelam> searchPishnehadForBordoyeSodourPaging(PaginatedListImpl pgList, PishnehadSearch pishnehadSearch)
    {
        String whereClause ="";
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class,"p").add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.createCriteria("bimename","b").add(Restrictions.eq("readyToShow", "yes"));
        if(pishnehadSearch.getAzTarikhEngheza()!=null && !pishnehadSearch.getAzTarikhEngheza().isEmpty())
        {
//            criteria.add(Restrictions.ge("b.tarikhEngheza",pishnehadSearch.getAzTarikhEngheza()));
            whereClause += "b.tarikhEngheza >= '" + pishnehadSearch.getAzTarikhEngheza() + "' and ";
        }
        if(pishnehadSearch.getTaTarikhEngheza()!=null && !pishnehadSearch.getTaTarikhEngheza().isEmpty())
        {
//            criteria.add(Restrictions.le("b.tarikhEngheza",pishnehadSearch.getTaTarikhEngheza()));
            whereClause += "b.tarikhEngheza <= '" + pishnehadSearch.getTaTarikhEngheza() + "' and ";
        }
//        criteria.createAlias("namayande", "n");
        if (!pishnehadSearch.getOstan().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.eq("n.ostan.cityId", Long.parseLong(pishnehadSearch.getOstanId())));
            whereClause += "no.cityId = " + pishnehadSearch.getOstanId() + " and ";
        }

        if (!pishnehadSearch.getShahr().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.eq("n.shahr.cityId", Long.parseLong(pishnehadSearch.getShahrId())));
            whereClause += "ns.cityId = " + pishnehadSearch.getShahrId() + " and ";
        }

        if (!pishnehadSearch.getNamayande().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.eq("n.id", pishnehadSearch.getNamayandeId()));
            whereClause += "n.id = " + pishnehadSearch.getNamayandeId() + " and ";
        }

        if (!pishnehadSearch.getVahedSodor().equalsIgnoreCase(""))
        {
//            criteria.createCriteria("p.karshenas","pk").createCriteria("pk.mojtamaSodoor","pkm").add(Restrictions.eq("pkm.id", pishnehadSearch.getVahedSodorId()));
            whereClause += "km.id = " + pishnehadSearch.getVahedSodorId() + " and ";
        }

        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.ilike("noeGharardad", pishnehadSearch.getNoeGharardad(), MatchMode.ANYWHERE));
            whereClause += "p.noeGharardad = '" + pishnehadSearch.getNoeGharardad() + "' and ";
        }

        if (!pishnehadSearch.getBazarYab().equalsIgnoreCase(""))
        {
//            criteria.createAlias("bazarYab", "by").add(Restrictions.eq("by.id", pishnehadSearch.getBazarYabId()));
            whereClause += "bry.id = " + pishnehadSearch.getBazarYabId() + " and ";
        }


        if (!pishnehadSearch.getKarbareSaderKonnandeh().equalsIgnoreCase(""))
        {
//            criteria.createCriteria("b.emzaKonandeAval", "em1").createCriteria("em1.user","emu").add(Restrictions.eq("emu.id", pishnehadSearch.getKarbareSaderKonnandehId()));
            whereClause += "em1u.id = " + pishnehadSearch.getKarbareSaderKonnandehId() + " and ";
        }

        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.between("b.tarikhSodour", pishnehadSearch.getAzTarikh(), pishnehadSearch.getTaTarikh()));
            whereClause += "b.tarikhSodour >= '" + pishnehadSearch.getAzTarikh() + "' and b.tarikhSodour <= '" + pishnehadSearch.getTaTarikh() + "' and ";
        }

//        criteria.createAlias("estelam", "e");
        if(!pishnehadSearch.getTaSarmayeFoat().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.le("e.sarmaye_paye_fot_long",Long.parseLong(pishnehadSearch.getTaSarmayeFoat().replaceAll(",","").trim())));
            whereClause += "e.sarmaye_paye_fot_long <= " + Long.parseLong(pishnehadSearch.getTaSarmayeFoat().replaceAll(",", "").trim()) + " and ";
        }
        if(!pishnehadSearch.getAzSarmayeFoat().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.ge("e.sarmaye_paye_fot_long",Long.parseLong(pishnehadSearch.getAzSarmayeFoat().replaceAll(",","").trim())));
            whereClause += "e.sarmaye_paye_fot_long >= " + Long.parseLong(pishnehadSearch.getAzSarmayeFoat().replaceAll(",","").trim()) + " and ";
        }

        if(!pishnehadSearch.getNahvePardakhtHagheBime().isEmpty() && pishnehadSearch.getNahvePardakhtHagheBime()!=null)
        {
//            criteria.add(Restrictions.eq("e.nahve_pardakht_hagh_bime",pishnehadSearch.getNahvePardakhtHagheBime()));
            whereClause += "e.nahve_pardakht_hagh_bime = '" + pishnehadSearch.getNahvePardakhtHagheBime() + "' and ";
        }

        if(pishnehadSearch.getGroupId()!=null)
        {
            whereClause += "pgh.id = " + pishnehadSearch.getGroupId() + " and ";
        }

        if(pishnehadSearch.getTarh().getName()!=null && pishnehadSearch.getTarh().getName().length() > 0)
        {
            whereClause += "t.id = " + pishnehadSearch.getTarh().getName() + " and ";
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

//        criteria.addOrder(Order.desc("b.shomare"));
//        List<Pishnehad> res = criteria.list();
        Long countQuery=(Long)getSession().createQuery
                                                                (
                                                                     "select count(p.id)" +
                                                                     "from Pishnehad p join p.bimename b join p.bimeGozar bg join bg.shakhs bgs left join p.tarh t join p.bimeShode bs join bs.shakhs bss join p.namayande n left join " +
                                                                     "p.bazarYab bry left join p.karshenas k  join k.mojtamaSodoor km " +
                                                                     "join n.ostan no join n.shahr ns join b.emzaKonandeAval em1 " +
                                                                     "left join b.ghestBandiList gb  join em1.user em1u  join p.estelam e left join p.gharardad pgh left join p.namayandePoshtiban pn " +
                                                                     "where p.valid = true and b.readyToShow = :yes " + whereClause +
                                                                     " and gb.saleBimei = 0 "+
                                                                     "order by b.shomare desc"
                                                                ).setString("yes","yes").uniqueResult();

//        pgList.setFullListSize(query.list().size());
        pgList.setFullListSize(countQuery.intValue());
//        getSession().close();
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        org.hibernate.Query query=getSession().createQuery
                                                        (
                                                             "select new com.bitarts.parsian.viewModel.PishnehadEstelam(b.id, e.pooshesh_amraz_khas," +
                                                             "bgs.name||' '||bgs.nameKhanevadegi,e.sen_bime_shode, gb.haghBimePoosheshMoafiat_long,gb.haghBimePoosheshHadese_long, " +
                                                             "gb.haghBimePoosheshAmraz_long," +
                                                             "gb.haghBimePoosheshNaghsOzv_long,b.shomare, b.tarikhSodour,b.tarikhShorou, b.tarikhEngheza, e.sarmaye_pooshesh_naghs_ozv," +
                                                             "e.sarmaye_paye_fot_long,e.nerkh_afzayesh_salaneh_hagh_bime, e.sarmaye_paye_fot_dar_asar_hadese," +
                                                             "e.sarmaye_pooshesh_amraz_khas, n.kodeNamayandeKargozar, n.name,e.nahve_pardakht_hagh_bime," +
                                                             "bss.name||' '||bss.nameKhanevadegi, bry.firstName||' '||bry.lastName||' - '||bry.personalCode,e.nerkh_afzayesh_salaneh_sarmaye_fot, e.modat_bimename, no.cityName, ns.cityName" +
                                                             ", km.kodeNamayandeKargozar ,km.name, p.noeGharardad, t.name, e.pooshesh_moafiat, k.firstName||' '||k.lastName,pgh.nameSherkat,pn.name,pn.kodeNamayandeKargozar,p.options) " +
                                                             "from Pishnehad p join p.bimename b join p.bimeGozar bg join bg.shakhs bgs left join p.tarh t join p.bimeShode bs join bs.shakhs bss join p.namayande n left join " +
                                                             "p.bazarYab bry left join p.karshenas k  join k.mojtamaSodoor km " +
                                                             "join n.ostan no join n.shahr ns join b.emzaKonandeAval em1 " +
                                                             "join b.ghestBandiList gb  join em1.user em1u  join p.estelam e left join p.gharardad pgh left join p.namayandePoshtiban pn " +
                                                             "where p.valid = true and b.readyToShow = :yes " + whereClause +
                                                             " and gb.saleBimei = 0 "+
                                                             "order by b.shomare desc"
                                                        ).setString("yes","yes")      ;
        query.setFirstResult(maxResults);
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber()+1);
        return pgList;
    }

    public List<PishnehadEstelam> searchPishnehadForBordoyeSodour(PishnehadSearch pishnehadSearch)
    {
        String whereClause ="";
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class,"p").add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.createCriteria("bimename","b").add(Restrictions.eq("readyToShow", "yes"));
        if(pishnehadSearch.getAzTarikhEngheza()!=null && !pishnehadSearch.getAzTarikhEngheza().isEmpty())
        {
//            criteria.add(Restrictions.ge("b.tarikhEngheza",pishnehadSearch.getAzTarikhEngheza()));
            whereClause += "b.tarikhEngheza >= '" + pishnehadSearch.getAzTarikhEngheza() + "' and ";
        }
        if(pishnehadSearch.getTaTarikhEngheza()!=null && !pishnehadSearch.getTaTarikhEngheza().isEmpty())
        {
//            criteria.add(Restrictions.le("b.tarikhEngheza",pishnehadSearch.getTaTarikhEngheza()));
            whereClause += "b.tarikhEngheza <= '" + pishnehadSearch.getTaTarikhEngheza() + "' and ";
        }
//        criteria.createAlias("namayande", "n");
        if (!pishnehadSearch.getOstan().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.eq("n.ostan.cityId", Long.parseLong(pishnehadSearch.getOstanId())));
            whereClause += "no.cityId = " + pishnehadSearch.getOstanId() + " and ";
        }

        if (!pishnehadSearch.getShahr().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.eq("n.shahr.cityId", Long.parseLong(pishnehadSearch.getShahrId())));
            whereClause += "ns.cityId = " + pishnehadSearch.getShahrId() + " and ";
        }

        if (!pishnehadSearch.getNamayande().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.eq("n.id", pishnehadSearch.getNamayandeId()));
            whereClause += "n.id = " + pishnehadSearch.getNamayande() + " and ";
        }

        if (!pishnehadSearch.getVahedSodor().equalsIgnoreCase(""))
        {
//            criteria.createCriteria("p.karshenas","pk").createCriteria("pk.mojtamaSodoor","pkm").add(Restrictions.eq("pkm.id", pishnehadSearch.getVahedSodorId()));
            whereClause += "km.id = " + pishnehadSearch.getVahedSodorId() + " and ";
        }

        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase("")) {
//            criteria.add(Restrictions.ilike("noeGharardad", pishnehadSearch.getNoeGharardad(), MatchMode.ANYWHERE));
            whereClause += "p.noeGharardad = %" + pishnehadSearch.getNoeGharardad() + "% and ";
        }

        if (!pishnehadSearch.getBazarYab().equalsIgnoreCase(""))
        {
//            criteria.createAlias("bazarYab", "by").add(Restrictions.eq("by.id", pishnehadSearch.getBazarYabId()));
            whereClause += "bry.id = " + pishnehadSearch.getBazarYabId() + " and ";
        }


        if (!pishnehadSearch.getKarbareSaderKonnandeh().equalsIgnoreCase(""))
        {
//            criteria.createCriteria("b.emzaKonandeAval", "em1").createCriteria("em1.user","emu").add(Restrictions.eq("emu.id", pishnehadSearch.getKarbareSaderKonnandehId()));
            whereClause += "em1u.id = " + pishnehadSearch.getKarbareSaderKonnandehId() + " and ";
        }

        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.between("b.tarikhSodour", pishnehadSearch.getAzTarikh(), pishnehadSearch.getTaTarikh()));
            whereClause += "b.tarikhSodour >= '" + pishnehadSearch.getAzTarikh() + "' and b.tarikhSodour <= '" + pishnehadSearch.getTaTarikh() + "' and ";
        }

//        criteria.createAlias("estelam", "e");
        if(!pishnehadSearch.getTaSarmayeFoat().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.le("e.sarmaye_paye_fot_long",Long.parseLong(pishnehadSearch.getTaSarmayeFoat().replaceAll(",","").trim())));
            whereClause += "e.sarmaye_paye_fot_long <= " + Long.parseLong(pishnehadSearch.getTaSarmayeFoat().replaceAll(",", "").trim()) + " and ";
        }
        if(!pishnehadSearch.getAzSarmayeFoat().equalsIgnoreCase(""))
        {
//            criteria.add(Restrictions.ge("e.sarmaye_paye_fot_long",Long.parseLong(pishnehadSearch.getAzSarmayeFoat().replaceAll(",","").trim())));
            whereClause += "e.sarmaye_paye_fot_long >= " + Long.parseLong(pishnehadSearch.getAzSarmayeFoat().replaceAll(",","").trim()) + " and ";
        }

        if(!pishnehadSearch.getNahvePardakhtHagheBime().isEmpty() && pishnehadSearch.getNahvePardakhtHagheBime()!=null)
        {
//            criteria.add(Restrictions.eq("e.nahve_pardakht_hagh_bime",pishnehadSearch.getNahvePardakhtHagheBime()));
            whereClause += "e.nahve_pardakht_hagh_bime = " + pishnehadSearch.getNahvePardakhtHagheBime() + " and ";
        }

        if(whereClause.length() > 0)
        {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

//        criteria.addOrder(Order.desc("b.shomare"));
//        List<Pishnehad> res = criteria.list();
        List<PishnehadEstelam> pishnehadEstelamList=getSession().createQuery
                                                                (
                                                                     "select new com.bitarts.parsian.viewModel.PishnehadEstelam(b.id, e.pooshesh_amraz_khas," +
                                                                     "bgs.name||' '||bgs.nameKhanevadegi,e.sen_bime_shode, gb.haghBimePoosheshMoafiat_long,gb.haghBimePoosheshHadese_long, " +
                                                                     "gb.haghBimePoosheshAmraz_long,(select sum(c1.amount_long) from Ghest g1 join g1.credebit c1 join g1.ghestBandi gb1 where gb1.id = gb.id )," +
                                                                     "gb.haghBimePoosheshNaghsOzv_long,b.shomare, b.tarikhSodour,b.tarikhShorou, b.tarikhEngheza, e.sarmaye_pooshesh_naghs_ozv," +
                                                                     "e.sarmaye_paye_fot_long,e.nerkh_afzayesh_salaneh_hagh_bime, e.sarmaye_paye_fot_dar_asar_hadese," +
                                                                     "e.sarmaye_pooshesh_amraz_khas, n.kodeNamayandeKargozar, n.name,e.nahve_pardakht_hagh_bime," +
                                                                     "bss.name||' '||bss.nameKhanevadegi, bry.firstName||' '||bry.lastName,e.nerkh_afzayesh_salaneh_sarmaye_fot, e.modat_bimename, no.cityName, ns.cityName" +
                                                                     ", km.kodeNamayandeKargozar ,km.name, p.noeGharardad, t.name, e.pooshesh_moafiat, k.firstName||' '||k.lastName) " +
                                                                     "from Pishnehad p join p.bimename b join p.bimeGozar bg join bg.shakhs bgs left join p.tarh t join p.bimeShode bs join bs.shakhs bss join p.namayande n left join " +
                                                                     "p.bazarYab bry left join p.karshenas k  join k.mojtamaSodoor km " +
                                                                     "join n.ostan no join n.shahr ns join b.emzaKonandeAval em1 " +
                                                                     "join b.ghestBandiList gb  join em1.user em1u  join p.estelam e " +
                                                                     "where p.valid = true and b.readyToShow = :yes " + whereClause +
                                                                     " and gb.saleBimei = 0 "+
                                                                     "order by b.shomare desc"
                                                                ).setString("yes","yes")
                                                                .list() ;
        return pishnehadEstelamList;
    }

    public PaginatedListImpl<Pishnehad> findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList) {
        int pageNum = paginatedList.getPageNumber();
        final int objectsPerPage = paginatedList.getObjectsPerPage();
        final int firstResult = objectsPerPage * pageNum;
        DetachedCriteria pishnehads = DetachedCriteria.forClass(Pishnehad.class,"p").add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        pishnehads.createAlias("bimeShode", "bss");
        pishnehads.createCriteria("bss.shakhs");

        pishnehads.createAlias("bimeGozar", "bs");
        pishnehads.createCriteria("bs.shakhs");

        pishnehads.createCriteria("namayande");
        pishnehads.createCriteria("namayande.ostan");

        pishnehads.createCriteria("state")
                .createCriteria("transitionBegin").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .createCriteria("users").add(Restrictions.eq("id", user.getId()));

        if(pishnehadSearch.getGroupId()!=null && pishnehadSearch.getGroupId()>0)
        {
            pishnehads.createCriteria("p.gharardad","gh").add(Restrictions.eq("gh.id",pishnehadSearch.getGroupId()));
        }

        //bimeShode
        if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("") ||
                !pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("") ||
                !pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase("")) {
            String query = "";

            if (!pishnehadSearch.getNameBimeShode().equalsIgnoreCase("")) {
                query += "shakhs2_.name like '%" + pishnehadSearch.getNameBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeShode().equalsIgnoreCase("")) {
                query += "shakhs2_.name_khanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeShode() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeShode().equalsIgnoreCase("")) {
                query += "shakhs2_.kode_melli_shenasayi = '" + pishnehadSearch.getKodeMelliBimeShode() + "' and ";
            }

            query = query.substring(0, query.lastIndexOf(" and "));

            pishnehads.add(Restrictions.sqlRestriction(query));
        }

        //BimeGozar
        if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("") ||
                !pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("") ||
                !pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase("")) {

            String query = "";
            if (!pishnehadSearch.getNameBimeGozar().equalsIgnoreCase("")) {
                query += "shakhs4_.name like '%" + pishnehadSearch.getNameBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getNameKhanevadegiBimeGozar().equalsIgnoreCase("")) {
                query += "shakhs4_.name_khanevadegi like '%" + pishnehadSearch.getNameKhanevadegiBimeGozar() + "%' and ";
            }

            if (!pishnehadSearch.getKodeMelliBimeGozar().equalsIgnoreCase("")) {
                query += "shakhs4_.kode_melli_shenasayi = '" + pishnehadSearch.getKodeMelliBimeGozar() + "' and ";
            }

            query = query.substring(0, query.lastIndexOf(" and "));

            pishnehads.add(Restrictions.sqlRestriction(query));
        }

        if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("") ||
                !pishnehadSearch.getOstan().trim().equalsIgnoreCase("") ||
                userHasRole(user, "ROLE_NAMAYANDE")) {

            String query = "";
            if (!pishnehadSearch.getKodeNamayandeKargozar().equalsIgnoreCase("")) {
                query += "namayande5_.kodeNamayandeKargozar ='" + pishnehadSearch.getKodeNamayandeKargozar() + "' and ";
            }

            if (!pishnehadSearch.getOstan().trim().equalsIgnoreCase("")) {
                query += "city6_.city_Name ='" + pishnehadSearch.getOstan() + "' and ";
            }

            if (userHasRole(user, "ROLE_NAMAYANDE")) {
                query += "namayande5_.id ='" + user.getNamayandegi().getId() + "' and ";
            }

            query = query.substring(0, query.lastIndexOf(" and "));
            pishnehads.add(Restrictions.sqlRestriction(query));
        }

        if (!pishnehadSearch.getState().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.sqlRestriction(" state7_.state_name='" + pishnehadSearch.getState() + "' "));
        }

        if (!pishnehadSearch.getRadif().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.ilike("radif", pishnehadSearch.getRadif(), MatchMode.ANYWHERE));
        }

        if (!pishnehadSearch.getAzTarikh().equalsIgnoreCase("") && !pishnehadSearch.getTaTarikh().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.between("createdDate", pishnehadSearch.getAzTarikh(), pishnehadSearch.getTaTarikh()));
        }

        if (pishnehadSearch.getKarshenasId() != -1) {
            if(pishnehadSearch.getKarshenasId() == -2)
            {
                pishnehads.add(Restrictions.isNull("karshenas"));
            }
            else
            {
                pishnehads.createAlias("karshenas", "k")
                    .add(Restrictions.eq("k.id", pishnehadSearch.getKarshenasId()));
            }
        }

        if(pishnehadSearch.getKodeSarparast()!=null)
        {
            if (!pishnehadSearch.getKodeSarparast().equalsIgnoreCase("")) {
                pishnehads.createCriteria("namayande.sarparast", "ns").add(Restrictions.eq("ns.kodeNamayandeKargozar", pishnehadSearch.getKodeSarparast()));


                }
        }

        if (!pishnehadSearch.getNoePardakht().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.eq("pishpardakhtType", pishnehadSearch.getNoePardakht()));
        }

        if (!pishnehadSearch.getNoeBimename().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.eq("noeBimename", pishnehadSearch.getNoeBimename()));
        }

        if (!pishnehadSearch.getNoeGharardad().equalsIgnoreCase("")) {
            pishnehads.add(Restrictions.eq("noeGharardad", pishnehadSearch.getNoeGharardad()));
        }

        if (!pishnehadSearch.getNahvePardakhtHagheBime().equalsIgnoreCase("")) {
            pishnehads.createAlias("estelam", "es");
            pishnehads.add(Restrictions.eq("es.nahve_pardakht_hagh_bime", pishnehadSearch.getNahvePardakhtHagheBime()));
        }

        Criteria pishnehadsNonDetached = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class).add(Restrictions.eq("valid", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (!pishnehadSearch.getPishpardakhtOK().equalsIgnoreCase("")) {
            DetachedCriteria pishnehadsPishpardakht = DetachedCriteria.forClass(Pishnehad.class).add(Restrictions.eq("valid", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            pishnehadsPishpardakht.createCriteria("fishs").add(Restrictions.eq("isFound",pishnehadSearch.getPishpardakhtOK()));
            pishnehadsPishpardakht.setProjection(Projections.id());
            pishnehadsNonDetached.add(Subqueries.propertyIn("id", pishnehadsPishpardakht));
        }

        if (userHasRole(user, "ROLE_BAZARYAB")) {
            pishnehads.createAlias("bazarYab", "b")
                    .add(Restrictions.eq("b.id", user.getId()));
        }
        if (userHasRole(user, Constant.ROLE_NAMAYANDE)) {
            pishnehads.add(Restrictions.eq("namayande.id", user.getNamayandegi().getId()));
        }
        if (userHasRole(user, Constant.ROLE_KARSHENAS_SODOUR)) {
            pishnehads.add(Restrictions.isNotNull("karshenas"))
                    .add(Restrictions.eq("karshenas.id", user.getId()));
        }
        if (userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                || userHasRole(user, Constant.ROLE_RAEIS_SODUR)) {
            // for nezarat
            pishnehads.createCriteria("karshenasNazer", "kn").add(Restrictions.or(Restrictions.eq("kn.id", user.getId()), Restrictions.ne("state.id", 248)));
        }



        pishnehads.setProjection(Projections.id());

        pishnehadsNonDetached.add(Subqueries.propertyIn("id", pishnehads));
        pishnehadsNonDetached.addOrder(Order.desc("createdDate")).addOrder(Order.desc("radif"));
        int count = Integer.parseInt(pishnehadsNonDetached.setProjection(Projections.rowCount()).list().get(0).toString());
        pishnehadsNonDetached.setFirstResult(firstResult);
        pishnehadsNonDetached.setMaxResults(objectsPerPage);
        List<Pishnehad> res = pishnehadsNonDetached.setProjection(null).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        paginatedList.setFullListSize(count);
        paginatedList.setList(res);
        paginatedList.setPageNumber(pageNum + 1);
        paginatedList.setObjectsPerPage(objectsPerPage);
        return paginatedList;
    }

    public void updateMoarefiname(Moarefiname theMoarefiname) {
        super.update(theMoarefiname);
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


    public void deletePishnehad(Integer id) {
        super.deleteById(Pishnehad.class, id);
    }

    public void deleteFish(Integer id) {
        super.deleteById(Fish.class, id);
    }

    public void updateElameHesab(ElameHesab elameHesab) {
        super.update(elameHesab);
    }

    public SharayeteJadid findSharayetJadid(Integer id) {
        return (SharayeteJadid) getSession().get(SharayeteJadid.class, id);
    }

    public ElameHesab findElameHesab(Integer elameHesab) {
        return (ElameHesab) getSession().get(ElameHesab.class, elameHesab);
    }

    public long countByRoleId(Integer roleId) {
        if (roleId == -1)
            return (Long) getSession().createQuery("select count(*) from Pishnehad p where p.valid=true").uniqueResult();
        else
            return (Long) getSession().createQuery("select count(*) from Pishnehad p left join p.state s where p.valid=true and s.peygirKonande.id=:rid").setParameter("rid", roleId).uniqueResult();

    }

    public long countByStateId(Integer stateId, String aztarikh, String tatarikh) {
        if (stateId == null || stateId == -1)
            return (Long) getSession().createQuery("select count(*) from Pishnehad p where p.valid=true and p.createdDate<:ta and p.createdDate>=:az").setParameter("az", aztarikh).setParameter("ta", tatarikh).uniqueResult();
        else
            return (Long) getSession().createQuery("select count(*) from Pishnehad p where p.valid=true and p.state.id=:sid and p.createdDate<:ta and p.createdDate>=:az").setParameter("az", aztarikh).setParameter("ta", tatarikh).setParameter("sid", stateId).uniqueResult();

    }

    public List<GStructure> makeGozareshTedadVaziat(Role role, String azTarikh, String taTarikh) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Pishnehad.class, "p").add(Restrictions.eq("valid", true)).createCriteria("p.state", "s").addOrder(Order.asc("s.id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (role != null) {
            crit.createCriteria("s.peygirKonande", "pK").add(Restrictions.eq("pK.id", role.getId()));
        }
        if (azTarikh != null && !azTarikh.isEmpty()) {
            crit.add(Restrictions.ge("p.createdDate", azTarikh));
        }
        if (taTarikh != null && !taTarikh.isEmpty()) {
            crit.add(Restrictions.lt("p.createdDate", taTarikh));
        }
        List<Pishnehad> list = crit.list();
        List<GStructure> gStructureList = new LinkedList<GStructure>();
        LinkedHashMap<Integer, LinkedHashMap<Integer, GStructure>> data = new LinkedHashMap<Integer, LinkedHashMap<Integer, GStructure>>();
        for (Pishnehad p : list) {
            if (!p.getPeygirKonandeForReport().getRoleName().equals("ROLE_MAJAZI_NAHAII")) {
                if (data.containsKey(p.getPeygirKonandeForReport().getId())) {
                    if (data.get(p.getPeygirKonandeForReport().getId()).containsKey(p.getState().getId())) {
                        GStructure g = data.get(p.getPeygirKonandeForReport().getId()).get(p.getState().getId());
                        g.setTedadPishnehad(g.getTedadPishnehad() + 1);
                    } else {
                        data.get(p.getPeygirKonandeForReport().getId()).put(p.getState().getId(), new GStructure(p.getState().getStateName(), 1, 0.0, p.getPeygirKonandeForReport().getRoleName(), p.getPeygirKonandeForReport().getRoleColor()));
                    }
                } else {
                    LinkedHashMap<Integer, GStructure> tmp = new LinkedHashMap<Integer, GStructure>();
                    tmp.put(p.getState().getId(), new GStructure(p.getState().getStateName(), 1, 0.0, p.getPeygirKonandeForReport().getRoleName(), p.getPeygirKonandeForReport().getRoleColor()));
                    data.put(p.getPeygirKonandeForReport().getId(), tmp);
                }
            }
        }
        for (Integer i : data.keySet()) {
            double count = 0;
            String naghsh = "";
            String color = "";
            for (Integer j : data.get(i).keySet()) {
                count += data.get(i).get(j).getTedadPishnehad();
            }
            for (Integer j : data.get(i).keySet()) {
                GStructure tmp = data.get(i).get(j);
                tmp.setDarsadKol((int) Math.round((tmp.getTedadPishnehad() * 100 / count) * 100) / 100.0);
                gStructureList.add(tmp);
                naghsh = tmp.getNaghsh();
                color = tmp.getColor();
            }
            GStructure tmp = new GStructure("مجموع", (int) count, 100.0, naghsh, color);
            gStructureList.add(tmp);
        }
        return gStructureList;
    }

    public Map<String, Double> gozareshNemudarDate(String azTarikh, String taTarikh, boolean jadidErsal) {
        final Criteria crit = getSession().createCriteria(TransitionLog.class, "tl").add(Restrictions.ge("tl.date", azTarikh)).add(Restrictions.le("tl.date", taTarikh)).addOrder(Order.asc("tl.date")).createCriteria("tl.transition", "t");
        if (jadidErsal)
            crit.createCriteria("t.stateEnd", "s").add(Restrictions.eq("s.id", 30));
        else
            crit.createCriteria("t.stateEnd", "s").add(Restrictions.eq("s.id", 290));
        List<TransitionLog> list = crit.list();
        Map<String, Double> tmp = new LinkedHashMap<String, Double>();
        tmp.put("sum", (double)list.size());
        for (TransitionLog tl : list) {
            if (tmp.containsKey(tl.getDate())) {
                double d = tmp.get(tl.getDate());
                tmp.remove(tl.getDate());
                d++;
                tmp.put(tl.getDate(), d);
            } else {
                tmp.put(tl.getDate(), 1.0);
            }
        }
        return tmp;
    }

    public List<GStructureTafkikVaziat> makeGozareshTedadTafkik(String azTarikh, String taTarikh, User user) {
        final Session session = getSession();
        if (azTarikh == null || azTarikh.isEmpty())
            azTarikh = "0000/00/00";
        if (taTarikh == null || taTarikh.isEmpty())
            taTarikh = "9999/99/99";
        List<User> userList;
        if (user == null)
            userList = session.createQuery("select distinct u from User u where u.id not in (select distinct u2.id from User u2 left join u2.roles r where r.roleName=:baz or r.roleName=:nam) order by u.lastName asc").setParameter("nam", Constant.ROLE_NAMAYANDE).setParameter("baz", Constant.ROLE_BAZARYAB).list();
        else {
            userList = new ArrayList<User>(1);
            userList.add(user);
        }
        final List<GStructureTafkikVaziat> list = new ArrayList<GStructureTafkikVaziat>(userList.size());
        for (User u : userList) {
            Map<String, Long> tmp = new LinkedHashMap<String, Long>();
            long ccount = 0;
            if(u.hasRole(Constant.ROLE_KARSHENAS_MASOUL))
            {
                ccount = (Long) session.createQuery("select count(p) from Pishnehad p where p.valid=true and p.state.id=60 and p.karshenas.id is null and p.createdDate>=:az and p.createdDate<=:ta").setParameter("az", azTarikh).setParameter("ta", taTarikh).uniqueResult();
                tmp.put("اصلاح پیشنهاد", ccount);
            }
            else
            {
                List<State> stateList = session.createQuery("select distinct p.state from Pishnehad p where p.valid=true and p.karshenas.id=:uid and p.createdDate>=:az and p.createdDate<=:ta").setParameter("uid", u.getId()).setParameter("az", azTarikh).setParameter("ta", taTarikh).list();
                for (State s : stateList) {
                    long count = (Long) session.createQuery("select count(p) from Pishnehad p where p.valid=true and p.state.id=:sid and p.karshenas.id=:uid and p.createdDate>=:az and p.createdDate<=:ta").setParameter("sid", s.getId()).setParameter("uid", u.getId()).setParameter("az", azTarikh).setParameter("ta", taTarikh).uniqueResult();
                    tmp.put(s.getStateName(), count);
                    ccount += count;
                }
            }
            tmp.put("مجموع", ccount);
            final GStructureTafkikVaziat gstv = new GStructureTafkikVaziat(u.getFullName(), tmp);
            list.add(gstv);
        }
        return list;

    }

    public List<GStructureTafkikVaziat> makeGozareshTedadTafkikVaziat(State state) {
        final Session session = getSession();
        List<State> stateList;
        if (state == null)
            stateList = session.createQuery("select distinct s from State s where s.stateMachineName='PISHNAHAD_SYSTEM' order by s.stateName asc").list();
        else {
            stateList = new ArrayList<State>(1);
            stateList.add(state);
        }
        List<GStructureTafkikVaziat> list = new ArrayList<GStructureTafkikVaziat>(stateList.size());
        for (State s : stateList) {
            Map<String, Long> tmp = new LinkedHashMap<String, Long>();
            List<User> userList = session.createQuery("select distinct u from User u left join u.roles role where role.roleName=:rn and u.id not in (select distinct u2.id from User u2 left join u2.roles r where r.roleName=:baz or r.roleName=:nam) order by u.lastName asc").setParameter("nam", Constant.ROLE_NAMAYANDE).setParameter("baz", Constant.ROLE_BAZARYAB).setParameter("rn", s.getPeygirKonande().getRoleName()).list();
            long ccount = 0;
            for (User u : userList) {
                long count = (Long) session.createQuery("select count(p) from Pishnehad p where p.valid=true and p.state.id=:sid and p.karshenas.id=:uid").setParameter("sid", s.getId()).setParameter("uid", u.getId()).uniqueResult();
                tmp.put(u.getFullName(), count);
                ccount += count;
            }
            tmp.put("مجموع", ccount);
            final GStructureTafkikVaziat gstv = new GStructureTafkikVaziat(s.getStateName(), tmp);
            list.add(gstv);
        }
        return list;
    }

    public List<GStructureTransitionLog> makeGozareshTransitionLog(User user, String azTarikh, String taTarikh, String azVaziat, String beVaziat) {
        final Session session = getSession();
//        final Criteria crit = session.createCriteria(TransitionLog.class, "t").createCriteria("pishnehad", "p").add(Restrictions.eq("p.valid",true)).add(Restrictions.isNotNull("t.pishnehad")).add(Restrictions.isNotNull("t.transition")).setProjection(Projections.groupProperty("p.id"));

/*
        if(azTarikh!=null && !azTarikh.isEmpty())
            crit.add(Restrictions.ge("t.date",azTarikh));
        if(taTarikh!=null && !taTarikh.isEmpty())
            crit.add(Restrictions.lt("t.date",taTarikh));
        if(user!=null && user.getId()!=null)
            crit.createCriteria("t.user","u").add(Restrictions.eq("u.id",user.getId()));
*/
//        List<TransitionLog> transitionLogs=crit.addOrder(Order.asc("t.date")).addOrder(Order.asc("t.time")).list();
        if (azTarikh == null || azTarikh.isEmpty())
            azTarikh = "0000/00/00";
        if (taTarikh == null || taTarikh.isEmpty())
            taTarikh = "9999/99/99";
        List<TransitionLog> transitionLogs;
        String stateString = "";
        if(!azVaziat.equals("-1"))
            stateString += " and t.transition.stateBegin = " + azVaziat + " ";
        if(!beVaziat.equals("-1"))
            stateString += " and t.transition.stateEnd = " + beVaziat + " ";
        if (user == null || user.getId() == null)
            transitionLogs = session.createQuery("select distinct t from TransitionLog t where t.pishnehad is not null  and t.transition is not null " + stateString + " and t.date>=:az and t.date<=:ta order by t.time asc,t.pishnehad.id,t.date asc").setParameter("az", azTarikh).setParameter("ta", taTarikh).list();
        else
            transitionLogs = session.createQuery("select distinct t from TransitionLog t where t.user.id=:uid and t.pishnehad is not null  and t.transition is not null " + stateString + " and t.date>=:az and t.date<=:ta order by t.time asc,t.pishnehad.id,t.date asc").setParameter("az", azTarikh).setParameter("ta", taTarikh).setParameter("uid", user.getId()).list();
        List<GStructureTransitionLog> list = new ArrayList<GStructureTransitionLog>(transitionLogs.size());
        TransitionLog now = null, last = null;
        for (TransitionLog tl : transitionLogs) {
            last = now;
            now = tl;
            final GStructureTransitionLog gstl = new GStructureTransitionLog(tl.getPishnehad().getRadif(), tl.getPishnehad().getCreatedDate(), tl.getUser().getFullName()
                    , tl.getTransition().getStateBegin().getStateName(), tl.getTransition().getStateEnd().getStateName(), tl.getDate(), tl.getTime(), "", tl.getMessage());
            if (last != null && last.getPishnehad().getId().equals(now.getPishnehad().getId()))
                gstl.setEkhtelaf(DateUtil.getTimeDifferenceByStringDescription(now.getDate(), last.getDate()));
            list.add(gstl);
        }
        return list;

    }

    public List<Fish> makeGozareshElectronic(String azTarikh, String taTarikh, boolean faghatInterneti, boolean faghatSadere) {
        final Session session = getSession();
        final Criteria crit = session.createCriteria(Fish.class, "f");
        if (azTarikh != null && !azTarikh.isEmpty())
            crit.add(Restrictions.ge("f.tarikh", azTarikh));
        if (taTarikh != null && !taTarikh.isEmpty())
            crit.add(Restrictions.lt("f.tarikh", taTarikh));
        if (faghatInterneti)
            crit.add(Restrictions.eq("f.paymentType", "interneti"));
        if (faghatSadere) {
            crit.add(Restrictions.isNotNull("f.pishnehad"));
            crit.createCriteria("f.pishnehad", "p").createCriteria("p.bimename", "b");
            crit.add(Restrictions.isNotNull("b.id")).add(Restrictions.eq("b.readyToShow", "yes"));
        }
        return crit.addOrder(Order.asc("tarikh")).list();
    }


    public Bimename findBimenameByShomare(String shomareBimename) {
        try {
            return (Bimename) getSession().createCriteria(Bimename.class).add(Restrictions.eq("shomare", shomareBimename)).list().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<User> findAllKarshenas(String tab) {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("faal", true)).add(Restrictions.gt("sarmayeFotMaxForKarshenasi", 0L));

        if(tab!=null && tab.equals("bahre_mandi"))
        {
            list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.or(Restrictions.eq("roleName", "ROLE_KARSHENAS_SODUR"),Restrictions.eq("roleName","ROLE_PAS_KARSHENAS")));
        }
        else
        {
            list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .add(Restrictions.eq("roleName", "ROLE_KARSHENAS_SODUR"));
        }
        list.addOrder(Order.asc("firstName")).setCacheable(true);
        List<User> returnList = list.list();
        for(User k: returnList)
        {
            Long assignCount = (Long) getSession().createQuery("select count(*) from Pishnehad p where p.karshenas=:ka and p.tarikhAssignBeKarshenas=:em").setParameter("ka",k).setParameter("em",DateUtil.getCurrentDate()).uniqueResult();
            k.setAssignCount(assignCount.intValue());
        }
        return returnList;
    }

    public User findKarshenasForNezaratAssignment(boolean nazer, boolean masool, boolean raees, User user) {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("faal", true)).add(Restrictions.eq("nezaratEnabled", true));
        if(nazer) {
            list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.eq("roleName", "ROLE_KARSHENAS_NAZER"));
        } else if (masool) {
            list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.eq("roleName", "ROLE_KARSHENAS_MASOUL"));
        } else if(raees) {
            list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.eq("roleName", "ROLE_RAEIS_SODUR"));
        }
        List<User> returnList = list.list();
        User returnUser = returnList.get(0);
        long assignReturn = Integer.MAX_VALUE;
        for (User k : returnList) {
            long assignCount = (Long) getSession().createQuery("select count(*) from Pishnehad p join p.state s join p.karshenasNazer kn " +
                    "where kn.id=:ka and p.tarikhAssignBeKarshenasNazer=:tarikh").setParameter("ka", k.getId())
                    .setParameter("tarikh", DateUtil.getCurrentDate()).uniqueResult();
            if(assignCount < assignReturn && k.getId() != user.getId()) {
                returnUser = k;
                assignReturn = assignCount;
            }
        }
        return returnUser;
    }

    public List<User> findAllKarshenas(Long sarmayePayeFot) {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class)
                .add(Restrictions.ge("sarmayeFotMaxForKarshenasi", sarmayePayeFot))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("faal", true));
        list.createCriteria("roles").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("roleName", "ROLE_KARSHENAS_SODUR"));
        list.addOrder(Order.asc("firstName")).setCacheable(true);
        List<User> returnList = list.list();
        for (User k : returnList) {
            Long assignCount = (Long) getSession().createQuery("select count(*) from Pishnehad p where p.karshenas=:ka and p.tarikhAssignBeKarshenas=:em").setParameter("ka", k).setParameter("em", DateUtil.getCurrentDate()).uniqueResult();
            k.setAssignCount(assignCount.intValue());
        }
        return returnList;
    }

    public List<User> findAllKarshenasByCity(City city) {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("u.faal", true)).createCriteria("mojtamaSodoor", "m").createCriteria("m.ostan", "o").add(Restrictions.eq("o.cityId", city.getCityId()));
        list.createCriteria("u.roles", "r").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("r.roleName", "ROLE_KARSHENAS_SODUR"));
        list.addOrder(Order.asc("u.firstName")).setCacheable(true);
        return list.list();
    }

    public List<User> findAllKarshenasKhesarat() {
        Criteria list = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class, "u")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("u.faal", true));
        list.createCriteria("u.roles", "r").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("r.roleName", "ROLE_KARSHENAS_KHESARAT"));
        list.addOrder(Order.asc("u.firstName")).setCacheable(true);
        return list.list();
    }

    public PaginatedListImpl<Pishnehad> findAllByGharardadId(Long id) {
        Criteria criteria = getSession().createCriteria(Pishnehad.class)
                .add(Restrictions.eq("valid", true))
                .add(Restrictions.eq("gharardad.id", id))
                .addOrder(Order.asc("createdDate"))
                .addOrder(Order.asc("radif"));
         return PagingUtil.getPaginatedList(criteria);
    }

    public PaginatedListImpl<Bimename> findAllBimenameByGharardadId(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Pishnehad.class)
                .add(Restrictions.eq("valid", true))
                .add(Restrictions.eq("gharardad.id", id))
                .setProjection(Projections.property("bimename"));
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class)
                .add(Property.forName("id").in(detachedCriteria)).addOrder(Order.asc("shomare"));
         return PagingUtil.getPaginatedList(criteria);
    }

    public Pishnehad findByRadif(String templatePishnehadRadif) {
        List<Object> o = super.findByProperty(Pishnehad.class, "radif", templatePishnehadRadif);
        if(o == null || o.size() == 0){
            return null;
        }else{
            return (Pishnehad)o.get(0);
        }
    }

    public List<Bimename> findAllBimenameByGharardadIdNonePaginated(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Pishnehad.class)
                .add(Restrictions.eq("valid", true))
                .add(Restrictions.eq("gharardad.id", id))
                .setProjection(Projections.property("bimename"));
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Bimename.class)
                .add(Property.forName("id").in(detachedCriteria)).addOrder(Order.asc("shomare"));
        return criteria.list();
    }



}
