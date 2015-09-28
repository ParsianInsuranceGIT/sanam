package com.bitarts.parsian.dao;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.internal.InternalAndukhteQueue;
import com.bitarts.parsian.model.karmozd.KarmozdNamayande;
import com.bitarts.parsian.model.management.Hesab;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.viewModel.*;
import com.bitarts.parsian.viewModel.search.CredebitSearchForm;
import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.xsom.impl.ListSimpleTypeImpl;
import oracle.jdbc.OracleTypes;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.hql.ast.ASTQueryTranslatorFactory;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:21 PM
 */
public class AsnadeSodorDAO extends BaseDAO {
    public static final String GHEST__CREDEBIT = "credebit";
    public static final String CREDEBIT__CREDEBIT_TYPE = "credebitType";
    public static final String BEDEHI_CREDEBIT = "bedehiCredebit";
    public static final String CREDEBIT__STATUS = "status";
    public static final String PISHNEHAD = "pishnehad";
    public static final String shomareMoshtari = "shomareMoshtari";
    public static final String shenaseCredebit = "shenaseCredebit";


    public void saveBedehi(Ghest ghest) {
        super.saveOrUpdate(ghest);
    }

    public void saveCredebit(Credebit credebit) {
        super.save(credebit);
    }

    public void saveCredebitObj(Credebit credebit) {
        super.save(credebit);
    }

    public void saveBankInfo(BankInfo bankInfo) {
        super.save(bankInfo);
    }

    public void saveVagozari(Vagozari vagozari) {
        super.save(vagozari);
    }

    public void saveSanad(Sanad sanad) {
        super.save(sanad);
    }

    public void saveOrUpdateSanad(Sanad sanad) {
        super.saveOrUpdate(sanad);
    }

    public void saveGhestBandi(GhestBandi ghestBandi) {
        super.save(ghestBandi);
    }

    private boolean userHasRole(User user, String roleName) {
        boolean result = false;
        if (user != null) {
            Set<Role> roles = user.getRoles();

            for (Role role : roles) {
                if (role.getRoleName().equalsIgnoreCase(roleName)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public PaginatedListImpl<Sanad> findAllSanadsPaginated() {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Sanad.class);
        return PagingUtil.getPaginatedList(criteria);
    }

    public List<Long> NamayandehayeSubset(User user) {

        List<Long> namayandeIdListId = new LinkedList<Long>();
        if (userHasRole(user, "ROLE_MALI_VIEW")) {
            //todo: khate paein chie?
//            namayandeIdListId = NamayandehayeSubset(user);
        } else {
            if (user.getNamayandegi() != null) {
                namayandeIdListId.add(user.getNamayandegi().getId());
            }
        }

        List<BigDecimal> namayandeIdlist = new ArrayList<BigDecimal>();
        if (user != null && user.getNamayandegi() != null) {

            if(userHasRole(user, "ROLE_MALI_VIEW")) {
                String sql = "select na.id from tbl_namayande na " +
                        " left join (SELECT n.id FROM tbl_namayande n START WITH id = " + user.getNamayandegi().getId() + " CONNECT BY NOCYCLE PRIOR id = sarparast_id)nam on nam.id = na.id " +
                        " where nam.id is not null ";//and nam.id = " + user.getNamayandegi().getId();

               System.out.println("subset query");
                System.out.println(sql);
                SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
                namayandeIdlist = sqlQuery.list();
            } else {
                namayandeIdlist.add(new BigDecimal(user.getNamayandegi().getId()));
            }
        }

        List<Long> namayandeIdListLong = new ArrayList<Long>();
        for (BigDecimal namayandeIdD : namayandeIdlist) {
            namayandeIdListLong.add(Long.parseLong(namayandeIdD.toString()));
        }
        return namayandeIdListLong;
    }

    public List<Credebit> findCredebitsByIdentifier(String shomarePishnehad) {
        return (List<Credebit>) (getSession().createQuery("select c from Credebit c where c.identifier like :idenfr").setString("idenfr", shomarePishnehad).list());
    }

    public List<CredebitBimenameVM> findCredebitsByIdentifier(String shomareBimenameFrom, String shomareBimenameTo, User user) {
        String hqlWhere = "";
        if (user.getNamayandegi() != null)
            hqlWhere = " and c.vahedeSodor.id = '" + user.getNamayandegi().getId() + "'  ";
        if (!shomareBimenameTo.equalsIgnoreCase(""))
            hqlWhere = " and (c.identifier like'" + shomareBimenameTo + "%' or c.identifier <='" + shomareBimenameTo + "'  )  ";
        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.CredebitBimenameVM(c.rcptName,c.identifier,c.createdDate,c.sarresidDate,c.amount_long,c.remainingAmount_long,kh.amount," +
                                " kh.etebarCredebit.credebitType,kh.etebarCredebit.shenaseCredebit,kh.etebarCredebit.amount_long,kh.etebarCredebit.shomareMoshtari,case when kh.etebarCredebit.sarresidDate is null then '--' else kh.etebarCredebit.sarresidDate end," +
                                "  case when  kh.etebarCredebit.vosoulState='TAEED_VOSOUL' and b.mainId is null then kh.etebarCredebit.createdDate  " +
                                " when  kh.etebarCredebit.vosoulState='TAEED_VOSOUL' and  b.mainId is not  null then  b.taarikh" +
                                " when  kh.etebarCredebit.vosoulState='TAEED_NASHODE' then '--'" +
                                " when    kh.etebarCredebit.sarresidDate >'" + DateUtil.getCurrentDate() + "' then '***'  else '--'  end,s.shomare,kh.etebarCredebit.vosoulState)" +
                                " from Credebit c   left join  c.khateSanadsBedehi kh join kh.sanad s left join kh.etebarCredebit.bankInfoList  b " +
                                " where c.subsystemName  like  'VEHICLE'" +
                                " and kh.bedehiCredebit.credebitType like 'VEHICLE_HAGHBIME'" +
                                "and (c.identifier like'" + shomareBimenameTo + "%' or c.identifier >='" + shomareBimenameFrom + "') " + hqlWhere + "order by c.identifier ");
        return (List<CredebitBimenameVM>) query.list();
    }

    public PaginatedListImpl<ViewKhateSanad> findAllKhateSanads(int page,String searchShode, String shomareSanad, Sanad.NoeSanad noeSanad, Sanad.Vaziat vaziat, String createdDateAz,
                                                            String createdDateTa, String amountSanad, Credebit.CredebitType bedehiType, Credebit.CredebitType etebarType,
                                                            String shomareMoshtariEtebar, String shenaseEtebar, String shomareMoshtariBedehi, String shenaseBedehi, String amountEtebar,
                                                            String amountBedehi, String shoBimenameBedehi, String shoBimenameEtebar, String shomareSanadBank, Long namayandeId, User user, int subSystemName, String shomareCheck, Long bazaryabSanamId, String shomareFish, String SystemName) {
        PaginatedListImpl<ViewKhateSanad> resultList=new PaginatedListImpl<ViewKhateSanad>();
       // int page = 1 ;
        resultList.setPageNumber(page); //(page)
        resultList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);

        String Query = "SELECT " +
                "  sanad.SHOMARE AS ShomareSanad ," +
                "  sanad.CREATED_DATE AS ZamanSabt ," +
                "  sanad.NOE_SANAD ," +
                "  sanad.VAZIAT ," +
                "  nvl(kh.AMOuNT, 0)                     AS MablaghKhateSanad," +
                "  nvl(bedehi.SUBSYSTEM_IDENTIFIER,'')   AS Bimename," +
                "  nvl(bedehi.RCPT_NAME,'')              AS NameBimeGozar," +
                "  nvl(etebar.SHENASE_CREDEBIT,'')       AS ShenasePardakhtEtebar," +
                "  nvl(etebar.SHOMARE_MOSHTARI,'')       AS SHMoshtariEtebar," +
                "  nvl(etebar.credebit_type,'')          AS NoeEtebar," +
                "  nvl(etebar.FARSINAME,'')              AS FARSINAMEEtebar, "+
                "  nvl(bedehi.SHENASE_CREDEBIT,'')       AS ShenasePardakhtBedehi, " +
                "  nvl(bedehi.SHOMARE_MOSHTARI ,'')      AS SHMoshtariBedehi," +
                "  nvl(bedehi.credebit_type , '')        AS NoeBedehi," +
                "  nvl(bedehi.FARSINAME,'')              AS FARSINAMEBedehi," +
                "  nvl(etebar.AMOUNT_LONG,0)             AS MablaghEtebar ," +
                "  nvl(etebar.REMAINING_AMOUNT_LONG,0)   AS MandeEtebar ," +
                "  nvl(bedehi.AMOUNT_LONG , 0)           AS Mablaghbedehi," +
                "  nvl(bedehi.REMAINING_AMOUNT_LONG , 0) AS Mandebedehi ," +
                "  nvl(etebar.SARRESID_DATE , '')        AS SarresidDateEtebar," +
                "  nvl(bedehi.SARRESID_DATE , '')        AS SarresidDateBedehi," +
                "  nvl(etebar.KodeVahedeSodor , '')      AS KodeVahedeSodorEtebar," +
                "  nvl(etebar.NameVadeheSodor , '')      AS NameVahedeSodorEtebar," +
                "  nvl(etebar.KodeSabteEtebar , '')      AS KodeVahedeSabterEtebar," +
                "  nvl(etebar.NameSabt,'')               AS NameBahedeSabteEtebar," +
                "  nvl(bedehi.KodeVahedeSodor ,'')       AS KodeVahedeSodorBedehi," +
                "  nvl(etebar.NameVadeheSodor ,'')       AS NameVahedeSodorBEdehi," +
                "  nvl(bedehi.KodeSabteBedehi ,'')       AS KodeVahedeSabterBedehi," +
                "  nvl(etebar.NameSabt , '')             AS NameBahedeSabteBedehi," +
                "  nvl(etebar.BANK ,'')                  AS Bank," +
                "  nvl(etebar.TARIKH , '')               AS Tarikh," +
                "  nvl(etebar.SHOMARESANADBANK , '')     AS SHOMARESANADBANK," +
                "  nvl(etebar.SHOMAREFISH ,'')           AS SHOMAREFISH," +
                "  nvl(etebar.SERIAL_NUMBER , '')        AS SERIAL_NUMBER," +
                "  nvl(nm.KODENAMAYANDEKARGOZAR , '')    AS KodeVahedeSabteSanad," +
                "  nvl(nm.NAME ,'')                      AS NameVahedeSabteSanad,"+
                "  bedehi.daftar_ID , " +
                "  dfnVaziat.DFNCOmment as VaziatStr , " +
                "  dfnNoeSanad.DFNCOmment as NoeSanadStr ,  " +
                "  etebar.ID                             as etebarId, " +
                "  bedehi.ID                             as bedehiId, " +
                "  bedehi.Subsystem_Name , " +
                "  sanad.ID as SanadId,"+
                "  etebar.seri                           AS seriCheck, "+
                "  etebar.Tarikh_sarresid                AS TARIKHSARRESIDCHECK "  +
                " FROM tbl_sanad sanad " +
                " INNER JOIN " +
                "  (SELECT * FROM tbl_khate_sanad  ) kh ON kh.sanad_id = sanad.id " +
                " INNER JOIN ( " +
                "  select cre.* ,v.KODENAMAYANDEKARGOZAR as KodeVahedeSodor, n.KODENAMAYANDEKARGOZAR as KodeSabteBedehi, v.NAME as NameVadeheSodor, n.NAME as NameSabt, cretype.FARSINAME " +
                "    from tbl_credebit cre inner join (select * from tbl_credebittype where bedorbes = 1)cretype on cre.credebit_type = cretype.latinname " +
                "    inner join tbl_namayande n on cre.namayande_id = n.id " +
                "    inner join tbl_namayande v on cre.vahedesodor_id = v.id " +
                " ) bedehi ON kh.bedehi_credebit_id = bedehi.id " +
                " INNER JOIN ( " +
                "  select cre.* , dfish.TARIKH , cre.bank_name as BANK ,v.KODENAMAYANDEKARGOZAR as KodeVahedeSodor , v.NAME as NameVadeheSodor " +
                "      , n.KODENAMAYANDEKARGOZAR as KodeSabteEtebar  , n.NAME as NameSabt , cretype.FARSINAME " +
                "    , SERIAL_NUMBER , SHOMAREFISH , SHOMARESANADBANK ,seri," +
                "    TARIKH_SARRESID" +
                "  from tbl_credebit cre " +
                "    inner join (select * from tbl_credebittype where bedorbes = 2)cretype on cre.credebit_type = cretype.latinname " +
                "    inner join tbl_namayande n on cre.namayande_id = n.id " +
                "    inner join tbl_namayande v on cre.vahedesodor_id = v.id " +
                "    left join (SELECT * FROM tbl_daryafte_check ) dcheck ON dcheck.ID = cre.DARYAFTE_CHECK_ID " +
                "    left join (SELECT * FROM tbl_daryafte_fish  ) dfish  ON dfish.ID = cre.DARYAFTE_FISH_ID " +
                "  )  etebar  ON kh.etebar_credebit_id = etebar.id " +
                " INNER JOIN " +
                "  (SELECT * FROM tbl_namayande ) nm ON nm.id = sanad.namayande_id " +
                " INNER JOIN " +
                "  (select * from tbl_dictionary where PID = 1010)  dfnVaziat on dfnVaziat.Value = sanad.vaziat " +
                " INNER JOIN " +
                "  (select * from tbl_dictionary where PID = 1011)  dfnNoeSanad on dfnNoeSanad.Value = sanad.noe_sanad "+
                " WHERE 1=1 ";


        if (user.getNamayandegi() != null ) {
            Query += " AND  nm.KODENAMAYANDEKARGOZAR = "+user.getNamayandegi().getKodeNamayandeKargozar();
        }

        if(searchShode == null){// && searchShode.compareTo("yes") == 0){
            String today = DateUtil.getCurrentDate();
            Query += " AND sanad.Created_Date = '"+ today +"'" ;
        }

        Integer daftar_id=user.getDaftar().getId();
        Query += " AND bedehi.daftar_id = " + daftar_id;

        if (shomareSanad != null && !shomareSanad.isEmpty())  //shomare sanad
        {
            Query += " AND sanad.SHOMARE =  " +shomareSanad ;
        }

        if (noeSanad != null) //noe sanad
        {

            Query += " AND sanad.NOE_SANAD like '%" + noeSanad + "%'" ;
        }

        if (shomareCheck != null && shomareCheck.length() > 0) //shomare check
        {

            Query += " AND etebar.SERIAL_NUMBER = '" + shomareCheck + "'" ;
        }

        if (vaziat != null  ) //vaziat
        {

            Query += " AND sanad.VAZIAT = '" + vaziat + "'" ;
        }

        if (createdDateAz != null && !createdDateAz.isEmpty())  //tarikhe sabte sanad (az)
        {

            Query += " AND sanad.CREATED_DATE >= '" + createdDateAz + "'" ;
        }

        if (createdDateTa != null && !createdDateTa.isEmpty()) //tarikhe sabte sanad (ta)
        {
            Query += " AND sanad.CREATED_DATE <= '" + createdDateTa + "'" ;
        }

        if (amountSanad != null && !amountSanad.isEmpty()) //mablaghe ?
        {
            Query += " AND kh.AMOuNT = " + amountSanad  ;
        }

        if (etebarType != null) //noe etebar
        {
            Query += " AND etebar.credebit_type = '" + etebarType + "'" ;
        }

        if (amountEtebar != null && !amountEtebar.isEmpty())  //mablaghe etebar
        {
            Query += " AND etebar.REMAINING_AMOUNT_LONG = " + amountEtebar  ;
        }

        if (shenaseEtebar != null && !shenaseEtebar.isEmpty()) //shenase etebar
        {
            Query += " AND etebar.SHENASE_CREDEBIT = '" + shenaseEtebar + "'" ;
        }

        if (shomareMoshtariEtebar != null && !shomareMoshtariEtebar.isEmpty()) //
        {
            Query += " AND etebar.SHOMARE_MOSHTARI = '" + shomareMoshtariEtebar + "'" ;
        }

        if (bedehiType != null) //noe bedehi
        {
            Query += " AND bedehi.credebit_type = '" + bedehiType + "'" ;
        }

        if (amountBedehi != null && !amountBedehi.isEmpty()) //
        {
            Query += " AND bedehi.REMAINING_AMOUNT_LONG = " + amountBedehi  ;
        }

        if (shenaseBedehi != null && !shenaseBedehi.isEmpty()) //
        {
            Query += " AND bedehi.SHENASE_CREDEBIT = '" + shenaseBedehi +"'" ;
        }

        if (shomareMoshtariBedehi != null && !shomareMoshtariBedehi.isEmpty()) {
            Query += " AND bedehi.SHOMARE_MOSHTARI = '" + shomareMoshtariBedehi+"'"  ;
        }

        if (shoBimenameBedehi != null && !shoBimenameBedehi.isEmpty())//
        {
            Query += " AND bedehi.SUBSYSTEM_IDENTIFIER = '" + shoBimenameBedehi+"'"  ;
        }

        if (shomareSanadBank != null && !shomareSanadBank.isEmpty()) {
            Query += " AND etebar.SHOMARESANADBANK = '" + shomareSanadBank+"'"  ;
        }

        if (namayandeId != null && namayandeId > 0)  //namayande
        {
            Query += " AND nm.ID  = '" + namayandeId+"'"  ;
        }

        if (shomareFish != null && !shomareFish.isEmpty()) //shomare fish
        {
            Query += " AND etebar.SHOMAREFISH  = '" + shomareFish+"'"  ;
        }

        if (bazaryabSanamId != null ) //bazaryab
        {
            Query += " and bedehi.bazaryab_sanam_id   = " + bazaryabSanamId ;
        }

        if ( subSystemName > 0   ) {
            if (subSystemName == 20)
                Query += " AND  (bedehi.field = 3 or bedehi.field = 4) ";
            else
                Query += " AND  bedehi.field = "+ subSystemName;
        }

        if (SystemName != null && !SystemName.isEmpty()) //system
        {
            Query += " AND bedehi.SUBSYSTEM_Name = '" + SystemName+"'"  ;
        }


        Query Str=getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(Query);
       // System.out.println("bedehi tasviye nashode query saba:"+Query);
        List<Object[]> tempList =Str.list();
        List<ViewKhateSanad> listKhateSanad = new ArrayList<ViewKhateSanad>();

        for(int i=0; i<tempList.size(); i++){
            String shomare_sabt     = (String)tempList.get(i)[0];
            String zaman_sabt       = (String)tempList.get(i)[1];
            Sanad.NoeSanad noe_sanad= Sanad.NoeSanad.GHABZE_RESID;
            String tempnoesanad     =(String)tempList.get(i)[2];
            if(tempnoesanad.compareTo("PARDAKHT") == 0) {
                   noe_sanad        = Sanad.NoeSanad.PARDAKHT;
            }
            Sanad.Vaziat vaziat2    =Sanad.Vaziat.MOVAGHAT;
            String tempvaziat       =(String)tempList.get(i)[3];
            if(     tempvaziat.compareTo("DAEM") == 0 ) {
                vaziat2             = Sanad.Vaziat.DAEM;
            }
            String mablagh_khate_sanad      = (String)tempList.get(i)[4];
            String bimename                 = (String)tempList.get(i)[5];
            String name_bimegozar           = (String)tempList.get(i)[6];
            String shenase_pardakht_etebar  = (String)tempList.get(i)[7];
            String shomare_moshtari_etebar  = (String)tempList.get(i)[8];
            String noe_etebar               = (String)tempList.get(i)[9];
            String noe_etebar_str           = (String)tempList.get(i)[10];
            String shenase_pardakht_bedehi  = (String)tempList.get(i)[11];
            String shomare_moshtari_bedehi  = (String)tempList.get(i)[12];
            String noe_bedehi               = (String)tempList.get(i)[13];
            String noe_bedehi_str           = (String)tempList.get(i)[14];
            BigDecimal mablagh_etebar       = (BigDecimal)tempList.get(i)[15];
            BigDecimal   mablagh_bedehi     = (BigDecimal)tempList.get(i)[16];
            BigDecimal   mande_etebar       = (BigDecimal)tempList.get(i)[17];
            BigDecimal   mande_bedehi       = (BigDecimal)tempList.get(i)[18];
            String sarresid_date_etebar     = (String)tempList.get(i)[19];
            String sarresid_date_bedehi     = (String)tempList.get(i)[20];
            String kode_vahed_sodor_etebar  = (String)tempList.get(i)[21];
            String name_vahed_sodor_etebar  = (String)tempList.get(i)[22];
            String kode_vahed_sabt_etebar   = (String)tempList.get(i)[23];
            String name_vahed_sabt_etebar   = (String)tempList.get(i)[24];
            String kode_vahed_sodor_bedehi  = (String)tempList.get(i)[25];
            String name_vahed_sodor_bedehi  = (String)tempList.get(i)[26];
            String kode_vahed_sabt_bedehi   = (String)tempList.get(i)[27];
            String name_vahed_sabt_bedehi   = (String)tempList.get(i)[28];
            String bank                     = (String)tempList.get(i)[29];
            String tarikh_sanad_bank        = (String)tempList.get(i)[30];
            String shomare_sanad_bank       = (String)tempList.get(i)[31];
            String shomare_fish             = (String)tempList.get(i)[32];
            String serial_check             = (String)tempList.get(i)[33];
            String kode_vahed_sabt_sanad    = (String)tempList.get(i)[34];
            String name_vahed_sabt_sanad    = (String)tempList.get(i)[35];
            String vaziat_str               = (String)tempList.get(i)[37];
            String noe_sanad_str            = (String)tempList.get(i)[38];
            BigDecimal etebar_id            = (BigDecimal)tempList.get(i)[39];
            BigDecimal bedehi_id            = (BigDecimal)tempList.get(i)[40];
            String subsystem_name           = (String)tempList.get(i)[41];
            BigDecimal sanad_id             = (BigDecimal)tempList.get(i)[42];
            String SeriCheck                = (String)tempList.get(i)[43];
            String TarikhCheck              = (String)tempList.get(i)[44];


            ViewKhateSanad KhS = new ViewKhateSanad(shomare_sabt, zaman_sabt,noe_sanad ,vaziat2, mablagh_khate_sanad,
                    bimename, name_bimegozar, shenase_pardakht_etebar, shomare_moshtari_etebar,
                    noe_etebar ,noe_etebar_str , shenase_pardakht_bedehi, shomare_moshtari_bedehi, noe_bedehi , noe_bedehi_str
                    , mablagh_etebar.longValue(), mablagh_bedehi.longValue() ,mande_etebar.longValue()
                    ,mande_bedehi.longValue() ,sarresid_date_etebar , sarresid_date_bedehi , kode_vahed_sodor_etebar
                    ,name_vahed_sodor_etebar , kode_vahed_sabt_etebar ,name_vahed_sabt_etebar ,kode_vahed_sodor_bedehi
                    , name_vahed_sodor_bedehi , kode_vahed_sabt_bedehi , name_vahed_sabt_bedehi ,bank
                    , tarikh_sanad_bank ,shomare_sanad_bank , shomare_fish , serial_check , kode_vahed_sabt_sanad,
                    name_vahed_sabt_sanad , vaziat_str , noe_sanad_str ,etebar_id.longValue() ,bedehi_id.longValue()
                    ,subsystem_name , sanad_id.longValue(), SeriCheck , TarikhCheck);
            listKhateSanad.add(KhS);
        }
        if(!isExport()) {
            int pagesize=((page -1 ) * PagingUtil.MAX_OBJECTS_PER_PAGE) + PagingUtil.MAX_OBJECTS_PER_PAGE;
            int listsize= listKhateSanad.size();
            if(listsize>=pagesize)
                resultList.setList(listKhateSanad.subList(((page-1) * PagingUtil.MAX_OBJECTS_PER_PAGE), pagesize ));
            else
                resultList.setList( listKhateSanad.subList(((page-1)  * PagingUtil.MAX_OBJECTS_PER_PAGE), listsize));
        }
        else{
            resultList.setList(listKhateSanad);
            resultList.setPageNumber(1);
            resultList.setObjectsPerPage(Integer.MAX_VALUE);

        }
      //  resultList.setList(listKhateSanad);
        resultList.setFullListSize(listKhateSanad.size());

//        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "kh");
//        count.add(Subqueries.propertyIn("kh.id", criteria));
//        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
//        paginatedList.setFullListSize(countInt);
//        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "kh").createCriteria("kh.sanad", "s").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        System.out.println("saba khate sanad uery :"+nonDetachedCriteria);
//        nonDetachedCriteria.add(Subqueries.propertyIn("kh.id", criteria));
//        nonDetachedCriteria.addOrder(Order.desc("s.shomare"));
//        nonDetachedCriteria.setFirstResult(paginatedList.getPageNumber() * paginatedList.getObjectsPerPage());
//        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
//        paginatedList.setList(nonDetachedCriteria.list());
//        paginatedList.setPageNumber(paginatedList.getPageNumber() + 1);
        return resultList;

    }

    public List getKhateSanadsOfVehicle(String fromDate, String toDate, Namayande namayande) {
        List<Credebit.CredebitType> etebarList = new ArrayList<Credebit.CredebitType>();
        etebarList.add(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI);
        etebarList.add(Credebit.CredebitType.VEHICLE_KHESARAT);
        etebarList.add(Credebit.CredebitType.VEHICLE_DARYAFT_ELECTRONICI);

        List<Credebit.CredebitType> bedehiList = new ArrayList<Credebit.CredebitType>();
        bedehiList.add(Credebit.CredebitType.VEHICLE_HAGHBIME);
        bedehiList.add(Credebit.CredebitType.VEHICLE_HAGHBIME_ELECTRONICI);
        String whereClasue = "";
        if (namayande != null) {
            whereClasue += " and (b.namayande.id=" + namayande.getId() + " or e.namayande.id=" + namayande.getId() + ") ";
        }
        Query query = getSession().createQuery
                (
                        "select kh " +
                                "from KhateSanad kh join kh.sanad s join kh.etebarCredebit e join kh.bedehiCredebit b " +
                                "where (s.createdDate<=:to and s.createdDate>=:from) and  (e.credebitType in (:etebarType) or b.credebitType in (:bedehiType)) " + whereClasue
                ).setString("from", fromDate).setString("to", toDate).setParameterList("etebarType", etebarList).setParameterList("bedehiType", bedehiList);
        return query.list();
    }

    public PaginatedListImpl<KhateSanad> findAllKhateSanadsBySanadIdPaginated(Integer sanadId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class)
                .add(Restrictions.eq("sanad.id", sanadId));
        return PagingUtil.getPaginatedListUnlimite(criteria);
    }

    public PaginatedListImpl<BankInfo> findAllBankInfosByCredebitId(Integer credebitId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BankInfo.class,"b");
        criteria.add(Restrictions.eq("b.credebit.id", credebitId));
        criteria.add(Restrictions.ne("b.status",BankInfo.BankInfoStatus.VOSOL_TEKRARI));
        criteria.createCriteria("b.credebit", "c", CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("c.vosoulState",Credebit.VaziyatVosoul.TAEED_VOSOUL));

        return PagingUtil.getPaginatedList(criteria);
    }

    public void saveOrUpdateAllBedehi(List<Ghest> ghestList) {
        super.saveOrUpdateAll(ghestList);
    }

    public void updateBedehi(Ghest ghest) {
        super.update(ghest);
    }

    public void updateEtebar(Credebit credebit) {
        super.update(credebit);
    }

    public List<Credebit> findAllCredebitsBySanadKhordan(boolean sanadKhorde) {
        if (sanadKhorde) {
            return super.findByProperty(Credebit.class, CREDEBIT__STATUS, Credebit.Status.SANAD_KHORDE);
        } else {
            return super.findByProperty(Credebit.class, CREDEBIT__STATUS, Credebit.Status.SANAD_NA_KHORDE);
        }
    }

    public PaginatedListImpl<Ghest> findAllBedehisByBimenameIdPaging(Integer id, PaginatedListImpl<Ghest> pgList) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "g")
                .createCriteria("g.ghestBandi", "gb")
                .createCriteria("gb.bimename", "b").add(Restrictions.eq("b.id", id));
        criteria.addOrder(Order.asc("gb.saleBimei"));
        pgList.setFullListSize(criteria.list().size());
        int maxResults = pgList.getObjectsPerPage() * pgList.getPageNumber();
        criteria.setFirstResult(maxResults);
        criteria.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(criteria.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        return pgList;
    }

    public List<KhateSanad> findAllKhateSanadByCredebitId(Integer id) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "k").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("k.bedehiCredebit", "b").add(Restrictions.eq("b.id", id));
        return criteria.list();
    }

    public List<KhateSanad> findAllKhateSanadByEtebarCredebitId(Integer id) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "k").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("k.etebarCredebit", "b").add(Restrictions.eq("b.id", id));
        return criteria.list();
    }

    public List<Ghest> findAllGhests(Integer bimenameId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "g")
                .createCriteria("g.ghestBandi", "gb").add(Restrictions.eq("gb.type", GhestBandi.Type.G_BIMENAME))
                .createCriteria("gb.bimename", "b").add(Restrictions.eq("b.id", bimenameId));
        return criteria.list();
    }

    public Ghest findBedehiById(Integer id) {
        return (Ghest) super.findById(Ghest.class, id);
    }

    public Credebit findCredebitById(Integer id) {
        return (Credebit) super.findById(Credebit.class, id);
    }

    public void saveGhestList(List<Ghest> ghestList) {
        super.saveOrUpdateAll(ghestList);
    }

    public GhestBandi findGhestBandi(Integer bimenameId, int saleBimei) {
        Bimename bimename = (Bimename) super.findById(Bimename.class, bimenameId);
        List<GhestBandi> ghestBandiList = bimename.getGhestBandiList();
        if (ghestBandiList == null) return null;
        for (GhestBandi ghestBandi : ghestBandiList) {
            if (ghestBandi.getType().equals(GhestBandi.Type.G_BIMENAME) && ghestBandi.getSaleBimei().equals(String.valueOf(saleBimei)))
                return ghestBandi;
        }
        return null;
    }

    public void deleteBedehiList(List<Credebit> credebitList)
    {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        try {
        for (Credebit credebit : credebitList)
        {
            Integer ghestId=credebit.getGhest().getId();
            session.createQuery("delete from Credebit c where c.id=:id ").setParameter("id", credebit.getId()).executeUpdate();
            session.createQuery("delete from Ghest g where g.id=:id ").setParameter("id", ghestId).executeUpdate();
        }
    }
    public void deleteGhestBandei(GhestBandi gb) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        try {
        for (Ghest ghest : gb.getGhestList()) {
            session.createQuery("delete from Credebit c where c.ghest.id=:id ").setParameter("id", ghest.getId()).executeUpdate();
            session.createQuery("delete from Ghest g where g.id=:id ").setParameter("id", ghest.getId()).executeUpdate();
        }
        if (gb.getLogPrintDaftarcheList() != null)
            for (LogPrintDaftarche lg : gb.getLogPrintDaftarcheList()) {
                session.createQuery("delete from  LogPrintDaftarche lg where lg.id=:id ").setParameter("id", lg.getId()).executeUpdate();
            }
        session.createQuery("delete from GhestBandi gb where gb.id=:id ").setParameter("id", gb.getId()).executeUpdate();
//        } catch(Exception ex) {}
    }

    public void deleteGhest(int ghestId) {
        Ghest ghest = (Ghest) findById(Ghest.class, ghestId);
        final Session session = getSession();
        session.createQuery("delete from Credebit c where c.id=:id ").setParameter("id", ghest.getCredebit().getId()).executeUpdate();
//            super.delete(ghest.getCredebit());
        session.createQuery("delete from Ghest g where g.id=:id ").setParameter("id", ghest.getId()).executeUpdate();
//            super.delete(ghest);
    }

    public void deleteBedehisByGhestbandiId(Integer ghestbandiId) {
        List<Ghest> ghestList = super.findByPropertyOfProperty(Ghest.class, "ghestBandi", "id", ghestbandiId);
        super.deleteAll(ghestList);
    }

    public void updateGhestbandi(GhestBandi ghestBandi) {
        super.update(ghestBandi);
    }

    public void deleteBedehi(Ghest ghest) {
        super.delete(ghest);
    }

    public PaginatedListImpl<Credebit> findAllCredebitsByFilterPaginated(CredebitSearchForm credebitSearchForm, User user, boolean sendToList) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class, "c");
         //      .add(Restrictions.eq("c.id", 47917338));
        //.add(Restrictions.eq(CREDEBIT__STATUS, Credebit.Status.SANAD_NA_KHORDE));
        if (credebitSearchForm.getType() != null)  //agar noe etebar ya bedehi null nist
        {
            if (credebitSearchForm.getType().equals(CredebitSearchForm.Credebit_type.ETEBAR)) //agar noe etebar ast
            {
                Criterion criterion = null;
                for (Credebit.CredebitType etebarType : Credebit.etebarTypes)  //baraye etebar az noe TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE ya DARYAFTE_FISH ya ...
                {
                    if (credebitSearchForm.getCredebitType().equals(etebarType.toString()) || credebitSearchForm.getCredebitType().equals("all"))//agar noe etebare entekhabi all=hamae mavared bood ya noe etebare entekhabi yeki az anvae etebar bood
                    {
                        boolean isValid = true;
                        for (Credebit.CredebitType credebitTypesNaMotabarSanadDasti : Credebit.credebitTypesNaMotabarSanadDasti) //baraye etebar az noe etebarate mojood dar liste credebitTypesNaMotabarSanadDasti
                            if (credebitTypesNaMotabarSanadDasti.equals(etebarType))
                            {
                                isValid = false; //isvalid etebar ra false kon (baraye inke in etebar az liste etebarat hazf shavad)
                                continue;
                            }

                        if (isValid) //agar isvalid true bood
                            if (criterion == null)  //dafe aval criterion null ast
                                criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType);
                            else
                                criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType)); // criterion = TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE ya DARYAFTE_FISH ya ...
                    }
                }
                criteria.add(criterion);
                Long[] namayandehayeSubset;
                List<Long> namayandeIdListId = NamayandehayeSubset(user);
                namayandehayeSubset = namayandeIdListId.toArray(new Long[namayandeIdListId.size()]);
                if (user != null && user.getNamayandegi() != null) //agar admin nabood
                {
//                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getVahedSodur().getId())));
//                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.in("c.namayande.id", namayandehayeSubset)));
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getId())));
                }

            } else if (credebitSearchForm.getType().equals(CredebitSearchForm.Credebit_type.BEDEHI))  //filter bedehi
            {
                Criterion criterion = null;
                for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes) {
                    if (credebitSearchForm.getCredebitType().equals(bedehiType.toString()) || credebitSearchForm.getCredebitType().equals("all")) {
                        boolean isValid = true;
                        for (Credebit.CredebitType credebitTypesNaMotabarSanadDasti : Credebit.credebitTypesNaMotabarSanadDasti)
                            if (credebitTypesNaMotabarSanadDasti.equals(bedehiType)) {
                                isValid = false;
                                continue;
                            }

                        if (isValid)
                            if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType);
                            else
                                criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType));
                    }
                }
                criteria.add(criterion);
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getId())));
                }
            }
        }

        criteria.add(Restrictions.gt("c.remainingAmount_long", 0l));
        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
//        else
            criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
        ///// ta inja
        if (credebitSearchForm.getShomareMoshtari() != null && !credebitSearchForm.getShomareMoshtari().isEmpty()) //shenase pardakht
            criteria.add(Restrictions.like("shomareMoshtari", credebitSearchForm.getShomareMoshtari(), MatchMode.ANYWHERE));

        if (credebitSearchForm.getIdentifier() != null && !credebitSearchForm.getIdentifier().isEmpty()){ //shomare bimename
            criteria.add(Restrictions.like("identifier", credebitSearchForm.getIdentifier(), MatchMode.ANYWHERE));
            System.out.println("shomare bime name"+credebitSearchForm.getIdentifier());
           }
        if (credebitSearchForm.getAmount() != null && !credebitSearchForm.getAmount().isEmpty()) //mablagh
        {
            criteria.add(Restrictions.eq("amount_long", Long.parseLong(credebitSearchForm.getAmount().replaceAll(",", "").trim())));
        }

        if (credebitSearchForm.getRcptName() != null && !credebitSearchForm.getRcptName().isEmpty()) //
        {
            criteria.add(Restrictions.like("rcptName", credebitSearchForm.getRcptName(), MatchMode.ANYWHERE));
        }

        if (credebitSearchForm.getShomareGharardad() != null && !credebitSearchForm.getShomareGharardad().isEmpty()) //shomare gharardad
        {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Pishnehad.class, "p")
                    .add(Restrictions.eq("p.valid", true))
                    .createCriteria("p.gharardad", "g")
                    .add(Restrictions.ilike("g.shomare", credebitSearchForm.getShomareGharardad()))
                    .setProjection(Projections.property("p.bimename"));
            criteria.createCriteria("bimename", "b2").add(Property.forName("b2.id").in(detachedCriteria));
        }
        if (credebitSearchForm.getShomareBedehi() != null && !credebitSearchForm.getShomareBedehi().isEmpty()) //
        {
            criteria.add(Restrictions.eq("shenaseCredebit", credebitSearchForm.getShomareBedehi()));
        }
        if (credebitSearchForm.getShomareEtebar() != null && !credebitSearchForm.getShomareEtebar().isEmpty()) //
        {
            criteria.add(Restrictions.eq("shenaseCredebit", credebitSearchForm.getShomareEtebar()));
        }
        if (credebitSearchForm.getNamayandeName() != null && !credebitSearchForm.getNamayandeName().isEmpty()) //namayande
        {
            criteria.createCriteria("c.namayande", "nam", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.like("nam.name", credebitSearchForm.getNamayandeName())));
        }
        if (credebitSearchForm.getBazaryabSanamId() != null && !credebitSearchForm.getBazaryabSanamName().isEmpty()) //
        {
            criteria.createCriteria("c.bazarYabSanam", "bazsanam");
            criteria.add(Restrictions.eq("bazsanam.id", credebitSearchForm.getBazaryabSanamId()));
        }
        if ((credebitSearchForm.getAzTarikh() != null && !credebitSearchForm.getAzTarikh().isEmpty()) || (credebitSearchForm.getTaTarikh() != null && !credebitSearchForm.getTaTarikh().isEmpty()))  //az tarikh ta tarikh
        {
            if (credebitSearchForm.getAzTarikh() != null && !credebitSearchForm.getAzTarikh().isEmpty()) {
                criteria.add(Restrictions.ge("c.sarresidDate", credebitSearchForm.getAzTarikh()));
            }
            if (credebitSearchForm.getTaTarikh() != null && !credebitSearchForm.getTaTarikh().isEmpty()) {
                criteria.add(Restrictions.le("c.sarresidDate", credebitSearchForm.getTaTarikh()));
            }
        }
        if ((credebitSearchForm.getAzTarikhEtebar() != null && !credebitSearchForm.getAzTarikhEtebar().isEmpty()) || (credebitSearchForm.getTaTarikhEtebar() != null && !credebitSearchForm.getTaTarikhEtebar().isEmpty())) //az tarikhe etebar ta tarikhe etebar
        {
            if (credebitSearchForm.getAzTarikhEtebar() != null && !credebitSearchForm.getAzTarikhEtebar().isEmpty()) {
                criteria.add(Restrictions.ge("c.createdDate", credebitSearchForm.getAzTarikhEtebar()));
            }
            if (credebitSearchForm.getTaTarikhEtebar() != null && !credebitSearchForm.getTaTarikhEtebar().isEmpty()) {
                criteria.add(Restrictions.le("c.createdDate", credebitSearchForm.getTaTarikhEtebar()));
            }
        }
        if (credebitSearchForm.getShomareCheck() != null && !credebitSearchForm.getShomareCheck().isEmpty()) //shomare chek
        {
              criteria.createCriteria("c.daryafteCheck", "d", Criteria.LEFT_JOIN); //marboot be etebarat
              criteria.add(Restrictions.and(Restrictions.isNotNull("c.daryafteCheck"), Restrictions.like("d.serial", credebitSearchForm.getShomareCheck())));
        }
        if (credebitSearchForm.getShomareFish() != null && !credebitSearchForm.getShomareFish().isEmpty()) //shomare fish
        {
            criteria.createCriteria("c.daryafteFish", "dfish", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.daryafteFish"), Restrictions.like("dfish.shomareFish", credebitSearchForm.getShomareFish())));
        }
        criteria.addOrder(Order.asc("id"));

        if (sendToList) {
            PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
            List list = criteria.list();
            if (list != null)
                paginatedList.setList(list);
            return paginatedList;
        }
        //b-h   for get sql query from criteria
        CriteriaImpl criteriaImpl = (CriteriaImpl)criteria;
        SessionImplementor session = criteriaImpl.getSession();
        SessionFactoryImplementor factory = session.getFactory();
        CriteriaQueryTranslator translator=new CriteriaQueryTranslator(factory,criteriaImpl,criteriaImpl.getEntityOrClassName(),CriteriaQueryTranslator.ROOT_SQL_ALIAS);
        String[] implementors = factory.getImplementors( criteriaImpl.getEntityOrClassName() );

        CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)factory.getEntityPersister(implementors[0]),
                translator,
                factory,
                criteriaImpl,
                criteriaImpl.getEntityOrClassName(),
                session.getLoadQueryInfluencers()   );

        String sql=walker.getSQLString();
        System.out.println("filter query:"+sql);
        //ta injaa
        return PagingUtil.getPaginatedList(criteria);
    }

    public GhestBandi findGhestBandiById(int ghestBandiId) {
        return (GhestBandi) super.findById(GhestBandi.class, ghestBandiId);
    }

    public Sanad findSanadById(Integer sanadId) {
        return (Sanad) super.findById(Sanad.class, sanadId);
    }

    public void saveCredebitList(List<Credebit> credebitList) {
        super.saveOrUpdateAll(credebitList);
    }

    public List<GhestBandi> ghestBandiList4SaleAval(Integer bimenameId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(GhestBandi.class, "gb").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.between("gb.saleBimei", "0", "4"));
        criteria.createCriteria("gb.bimename", "b").add(Restrictions.eq("b.id", bimenameId));
        criteria.addOrder(Order.asc("gb.saleBimei"));
        return criteria.list();
    }


    public PaginatedListImpl<Credebit> findAllBedehiCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, Long vahedesodorId, boolean isSearch) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        paginatedList.setPageNumber(page);

        Long[] namayandehayeSubset;
        List<Long> namayandeIdListId = NamayandehayeSubset(user);


        namayandehayeSubset = namayandeIdListId.toArray(new Long[namayandeIdListId.size()]);
     
        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        boolean isAdminMali = true;
        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.in("c.vahedeSodor.id", namayandehayeSubset)));
                    isAdminMali = false;
                }
            }
        }

        criteria.setProjection(Projections.id());

        Criterion criterion = null;
        for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes) {
            // aghsat omr neshan dade nemishavad
            if (!bedehiType.equals(Credebit.CredebitType.GHEST)) {
                if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType);
                else criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType));
            }
        }
        criteria.add(criterion);

        criteria.createCriteria("c.khateSanadsBedehi", "k", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("k.sanad", "s", CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.or(Restrictions.gt("c.remainingAmount_long", 0l), Restrictions.and(Restrictions.eq("c.status", Credebit.Status.SANAD_KHORDE), Restrictions.eq("s.vaziat", Sanad.Vaziat.MOVAGHAT))));
        criteria.add(Restrictions.lt("sarresidDateWithMohlatSarresid", DateUtil.getCurrentDate()));
        //b-h
//        int daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id !=1)
        Integer daftar_id=user.getDaftar().getId();
            criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
//        else
//            criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
        ////// ta inja
        //for search
        if (identifier != null && identifier.length() > 0)
            criteria.add(Restrictions.like("identifier", identifier, MatchMode.ANYWHERE));

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            criteria.add(Restrictions.eq("shomareMoshtari", shomareMoshtari));

        if (rcptName != null && rcptName.length() > 0)
            criteria.add(Restrictions.like("rcptName", rcptName, MatchMode.ANYWHERE));

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            criteria.add(Restrictions.ge("sarresidDate", sarresidDateFrom));

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            criteria.add(Restrictions.le("sarresidDate", sarresidDateTo));

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            criteria.add(Restrictions.ge("createdDate", createdDateFrom));

        if (createdDateTo != null && createdDateTo.length() > 0)
            criteria.add(Restrictions.le("createdDate", createdDateTo));

        if (amount != null && amount.length() > 0)
            criteria.add(Restrictions.eq("amount_long", Long.parseLong(amount.replaceAll(",", ""))));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            criteria.add(Restrictions.eq("paidReceivedAmount", Long.parseLong(paidReceivedAmount.replaceAll(",", ""))));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            criteria.add(Restrictions.eq("remainingAmount_long", Long.parseLong(remainingAmount.replaceAll(",", ""))));

        if (credebitTypeFarsi != null)
            criteria.add(Restrictions.eq("credebitType", credebitTypeFarsi));

        if (shomareGharardad != null && shomareGharardad.length() > 0)
            criteria.add(Restrictions.eq("shomareGharardad", shomareGharardad));

        if (namayandeId != null && namayandeId > 0) {
            criteria.createCriteria("c.namayande", "nam", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.like("nam.id", namayandeId)));
        }

        if (vahedesodorId != null && vahedesodorId > 0) {
            criteria.createCriteria("c.vahedeSodor", "vah", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.like("vah.id", vahedesodorId)));
        }

        if (statusFarsi != null) {
            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
                criteria.add(Restrictions.not(Restrictions.eqProperty("remainingAmount_long", "amount_long")));
            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
                criteria.add(Restrictions.eqProperty("remainingAmount_long", "amount_long"));
        }


        if (vosoulStateFarsi != null) {
            if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                criteria.add(Restrictions.eq("vosoulState", vosoulStateFarsi));
            else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE))
                criteria.add(Restrictions.or(Restrictions.eq("vosoulState", vosoulStateFarsi), Restrictions.isNull("vosoulState")));
        }


        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = 0;
        if (!isAdminMali || isSearch)
            countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.sarresidDate"));

//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.addOrder(Order.desc("c.sarresidDate"));
//        Criteria nonDetachedCriteria=criteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
        //b-h
        CriteriaImpl criteriaImpl = (CriteriaImpl)nonDetachedCriteria;
        SessionImplementor session = criteriaImpl.getSession();
        SessionFactoryImplementor factory = session.getFactory();
        CriteriaQueryTranslator translator=new CriteriaQueryTranslator(factory,criteriaImpl,criteriaImpl.getEntityOrClassName(),CriteriaQueryTranslator.ROOT_SQL_ALIAS);
        String[] implementors = factory.getImplementors( criteriaImpl.getEntityOrClassName() );

        CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)factory.getEntityPersister(implementors[0]),
                translator,
                factory,
                criteriaImpl,
                criteriaImpl.getEntityOrClassName(),
                session.getLoadQueryInfluencers()   );

        String sql=walker.getSQLString();
        System.out.println("sql query"+sql);
        //b-h here

        if (!isExport()) {

            nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
            nonDetachedCriteria.setMaxResults((paginatedList.getPageNumber()) * paginatedList.getObjectsPerPage());

        } else {
            nonDetachedCriteria.setFirstResult(0);
            nonDetachedCriteria.setMaxResults(countInt);
            paginatedList.setObjectsPerPage(countInt);
        }
        if (isAdminMali && !isSearch)
            paginatedList.setList(new ArrayList<Credebit>());
        else
            paginatedList.setList(nonDetachedCriteria.list());

        return paginatedList;
    }

    private boolean isExport() {
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    return true;

                }
            }
        }
        return false;
    }

    public PaginatedListImpl<Credebit> findAllEtebaratVosulNashodeCredebitsPaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        paginatedList.setPageNumber(page);

        String hqlWhere = "";
        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    hqlWhere = " where k.bedehiCredebit.id in (select c.id from Credebit c  where c.namayande.id=" + user.getNamayandegi().getId() +
                            " or c.namayande.id in  (" + StringUtil.join(NamayandehayeSubset(user), ",") + " ) )";
                }
            }
        }
        String hql = "from Credebit cc where (cc.vosoulState is null or cc.vosoulState like 'TAEED_NASHODE') " +
                "and (cc.credebitType like 'PARDAKHT_SHENASEDAR' or cc.credebitType='DARYAFTE_CHECK') and cc.id in " +
                " (select k.etebarCredebit.id from KhateSanad k " + hqlWhere + " )";
        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id ==1)
//            hql+=" AND (cc.daftar.id="+daftar_id+" OR cc.daftar.id is NULL)";
//        else
            hql+=" AND cc.daftar.id="+daftar_id;

        ////// ta inja
        if(identifier != null && identifier.length() > 0)
            hql += " AND cc.identifier LIKE '%" + identifier + "%'";

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            hql += " AND cc.shomareMoshtari = '" + shomareMoshtari + "'";

        if (rcptName != null && rcptName.length() > 0)
            hql += " AND cc.rcptName LIKE '%" + rcptName + "%'";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            hql += " AND cc.sarresidDate >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            hql += " AND cc.sarresidDate <= '" + sarresidDateTo + "'";

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hql += " AND cc.createdDate >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            hql += " AND cc.createdDate <= '" + createdDateTo + "'";

        if (amount != null && amount.length() > 0)
            hql += " AND cc.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            hql += " AND cc.paidReceivedAmount = " + Long.parseLong(paidReceivedAmount.replaceAll(",", ""));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            hql += " AND cc.remainingAmount_long = " + Long.parseLong(remainingAmount.replaceAll(",", ""));


        if (shomareGharardad != null && shomareGharardad.length() > 0)
            hql += " AND cc.shomareGharardad = " + shomareGharardad;

        if (namayandeId != null && namayandeId > 0) {
            hql += " AND cc.namayande IS NOT NULL AND cc.namayande.id = " + namayandeId;
        }

        List<Credebit> credebitList = new ArrayList<Credebit>();
        try {
            credebitList = (List<Credebit>) getSession().createQuery(hql).list();
//            System.out.print("etebarat vosoul nashode qiuery");
//            System.out.println(hqlToSql(hql).toString());
        } catch (Exception e) {

        }

        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-e")) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            ArrayList rtnList = new ArrayList();
            for (int i = 0; i < credebitList.size(); i++) {
                rtnList.add(credebitList.get(i));
            }
            if (credebitList != null)
                paginatedList.setList(rtnList);
        } else {
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            for (int i = firstResult; i < firstResult + paginatedList.getObjectsPerPage() && i < credebitList.size(); i++) {
                rtnList.add(credebitList.get(i));
            }
            if (credebitList != null)
                paginatedList.setList(rtnList);
            rtnList = new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE && i < credebitList.size(); i++) {
                rtnList.add(credebitList.get(i));
            }
            paginatedList.setFullListSize(rtnList.size());
        }

        return paginatedList;
    }

    public PaginatedListImpl<Credebit> findMoghayeratDarVosolNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDate, String createdDate, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String vosoulDesc) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        paginatedList.setPageNumber(page);
        Long[] namayandehayeSubset;
        List<Long> namayandeIdListId = NamayandehayeSubset(user);
        namayandehayeSubset = namayandeIdListId.toArray(new Long[namayandeIdListId.size()]);


        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.in("c.vahedeSodor.id", namayandehayeSubset)));
                    //  criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getId())));
                    // criteria.add(Restrictions.or(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.in("c.vahedeSodor.id", namayandehayeSubset)));

                }
            }
        }

        criteria.setProjection(Projections.id());

        criteria.add(Restrictions.or(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PARDAKHT_SHENASEDAR),Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.DARYAFTE_CHECK)));
        criteria.add(Restrictions.eq("vosoulState", Credebit.VaziyatVosoul.TAEED_VOSOUL));

        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
//        else
            criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
        ///// ta inja
        criteria.add(Restrictions.isNotNull("c.bankInfoList"));
        criteria.createCriteria("c.bankInfoList", "ba");
        criteria.add((Restrictions.and(Restrictions.isNotNull("c.bankInfoList"), Restrictions.not(Restrictions.like("ba.status", BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI)))));


        //for search
        if (identifier != null && identifier.length() > 0)
            criteria.add(Restrictions.like("identifier", identifier));

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            criteria.add(Restrictions.eq("shomareMoshtari", shomareMoshtari));

        if (rcptName != null && rcptName.length() > 0)
            criteria.add(Restrictions.like("rcptName", "%" + rcptName + "%"));

        if (sarresidDate != null && sarresidDate.length() > 0)
            criteria.add(Restrictions.eq("sarresidDate", sarresidDate));

        if (createdDate != null && createdDate.length() > 0)
            criteria.add(Restrictions.eq("createdDate", createdDate));

        if (amount != null && amount.length() > 0)
            criteria.add(Restrictions.eq("amount_long", Long.parseLong(amount.replaceAll(",", ""))));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            criteria.add(Restrictions.eq("paidReceivedAmount", Long.parseLong(paidReceivedAmount.replaceAll(",", ""))));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            criteria.add(Restrictions.eq("remainingAmount_long", Long.parseLong(remainingAmount.replaceAll(",", ""))));

        if (shomareGharardad != null && shomareGharardad.length() > 0)
            criteria.add(Restrictions.eq("shomareGharardad", shomareGharardad));

        if (namayandeId != null && namayandeId > 0) {
            criteria.createCriteria("c.namayande", "nam", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.like("nam.id", namayandeId)));
        }

        if (statusFarsi != null) {
            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
                criteria.add(Restrictions.not(Restrictions.eqProperty("remainingAmount_long", "amount_long")));
            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
                criteria.add(Restrictions.eqProperty("remainingAmount_long", "amount_long"));
        }

        if (vosoulDesc != null && vosoulDesc.length() > 0) {
            if (vosoulDesc.equals(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD.toString()))
                criteria.add((Restrictions.and(Restrictions.isNotNull("c.bankInfoList"), Restrictions.like("ba.status", BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD))));
            else if (vosoulDesc.equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR.toString()))
                criteria.add((Restrictions.and(Restrictions.isNotNull("c.bankInfoList"), Restrictions.like("ba.status", BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR))));
        }

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.id"));
        if (!isExport()) {
            nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
            nonDetachedCriteria.setMaxResults((paginatedList.getPageNumber()) * paginatedList.getObjectsPerPage());
        } else {
            nonDetachedCriteria.setFirstResult(0);
            nonDetachedCriteria.setMaxResults(countInt);
            paginatedList.setObjectsPerPage(countInt);
        }
        paginatedList.setList(nonDetachedCriteria.list());

        return paginatedList;
    }

    public PaginatedListImpl<CredebitVM> findBedehiVosulNashodeNamayandePaginated(int page, User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String vosoulDesc, boolean isSearch) {
        PaginatedListImpl<CredebitVM> paginatedList = new PaginatedListImpl<CredebitVM>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        paginatedList.setPageNumber(page);

        String hql = "select new com.bitarts.parsian.viewModel.CredebitVM(c.rcptName,n.id,n.name,n.kodeNamayandeKargozar,c.createdDate,c.amount,c.remainingAmount,c.paidReceivedAmount," +
                " c.identifier,c.shomareGharardad,c.status,c.sarresidDate,c.sarresidDateWithMohlatSarresid,k.amount) from Credebit c join c.namayande n join c.khateSanadsBedehi k join k.sanad s " +
                " where c.remainingAmount_long >= 0" +
                " and (k.bedehiCredebit.credebitType like 'VEHICLE_HAGHBIME'" +
                " or k.bedehiCredebit.credebitType like 'VEHICLE_HAGHBIME_ELECTRONICI')" +
                " and k.etebarCredebit.vosoulState not like '" + Credebit.VaziyatVosoul.TAEED_VOSOUL + "'" +
                " and k.etebarCredebit.credebitType not like 'ELHAGHIE_BARGASHTI' " +
                " and k.etebarCredebit.credebitType not like 'VEHICLE_HAGHBIME_ELECTRONICI' " +
                " and k.etebarCredebit.credebitType not like 'VEHICLE_HAGHBIME_BARGASHTI'  " +
                " and k.etebarCredebit.credebitType not like 'AZ_MAHALLE_TABLIGHAT'  ";
        //" and c.sarresidDateWithMohlatSarresid <= '" + DateUtil.addDaysWithTatilat(DateUtil.getCurrentDate(), 1) + "' " ;
        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            hql+=" and (c.daftar.id="+daftar_id+" or c.daftar.id is null)";
//        else
            hql+=" and c.daftar.id="+daftar_id;
        ///// ta inja
        if (identifier != null && identifier.length() > 0)
            hql += " and c.identifier like '%" + identifier + "%'";

        if (amount != null && amount.length() > 0)
            hql += " and c.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hql += " and c.createdDate >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            hql += " and c.createdDate <= '" + createdDateTo + "'";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            hql += " and c.sarresidDate >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            hql += " and c.sarresidDate <= '" + sarresidDateTo + "'";

        if (namayandeId != null && namayandeId > 0) {
          //  System.out.println("====***"+namayandeId);
            hql += " and c.namayande is not null and c.namayande.id = " + namayandeId;
        }
        hql += " and (";
        for (Credebit.CredebitType bed : Credebit.bedehiTypes) {
            hql += " c.credebitType like '" + bed + "' or ";
        }
        hql += " 1=2)";
        if (user != null && user.getNamayandegi() != null && user.getNamayandegi().getId() != null && user.getNamayandegi().getId() > 0)
            hql += " and c.namayande is not null and( c.namayande.id = " + user.getNamayandegi().getId() + " or  c.namayande.id in ( " + StringUtil.join(NamayandehayeSubset(user), ",") + ") )";
//            hql += " and c.namayande is not null and( c.namayande.id = " + user.getNamayandegi().getId() + ") ";
        System.out.println("bedehi vosoul nashode query"+hqlToSql(hql));
        boolean isAdminMali = true;
        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                isAdminMali = false;
            }
        }

        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equals("d-49684-e")) {
                    isExport = true;
                }
            }
        }
        System.out.println("isExport");
        System.out.println(isExport);
        System.out.println("isAdminMali");
        System.out.println(isAdminMali);
        System.out.println("page number");
        System.out.println(page);
        List<CredebitVM> credebitList = new ArrayList<CredebitVM>();
        try {
            if (!isExport) {
                  if (isAdminMali && !isSearch) {
                    credebitList = new LinkedList<CredebitVM>();
                } else {
                    credebitList = (List<CredebitVM>) getSessionFactory().getCurrentSession().createQuery(hql).list();
                     Collections.sort(credebitList, new Comparator<CredebitVM>() {
                        public int compare(CredebitVM credebitVM1, CredebitVM credebitVM2) {

                            return credebitVM1.getFinalSarresidDate().compareTo(credebitVM2.getFinalSarresidDate());
                        }
                    });
                    int pagesize=((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE) + PagingUtil.MAX_OBJECTS_PER_PAGE;
                    int listsize= credebitList.size();
                    if(listsize>=pagesize)
                        credebitList = credebitList.subList(((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE), pagesize );
                    else
                        credebitList = credebitList.subList(((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE), listsize);
                }
            } else {
                credebitList = (List<CredebitVM>) getSession().createQuery(hql).list();
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        if (isExport) {
            if (credebitList != null) {
                paginatedList.setList(credebitList);
                paginatedList.setFullListSize(credebitList.size());
            }
        } else {
            if (credebitList != null)
                paginatedList.setList(credebitList);

            BigDecimal count = null;
            String hqlCount = hql.replace("select new com.bitarts.parsian.viewModel.CredebitVM(c.rcptName,n.id,n.name,n.kodeNamayandeKargozar,c.createdDate,c.amount,c.remainingAmount,c.paidReceivedAmount," +
                    " c.identifier,c.shomareGharardad,c.status,c.sarresidDate,c.sarresidDateWithMohlatSarresid,k.amount) from Credebit c", "select count(c.id) from Credebit c");
            try {
                String sql = hqlToSql(hqlCount);
                sql = sql.replace("select count(credebit0_.id)", "select /*+ parallel(10) */ count(credebit0_.id)");
                System.out.println("==sql count");
                System.out.println(sql);
                SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
                if (isAdminMali && !isSearch) {
                    count = new BigDecimal(0);
                } else {
                    count = (BigDecimal) sqlQuery.uniqueResult();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            paginatedList.setFullListSize(Integer.parseInt(count.toString()));
        }

        return paginatedList;
    }

    public PaginatedListImpl<Credebit> findAllCredebitsPaginated(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field, String shomareCheck, String shomareFish) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        List<BigDecimal> namayandeIdlist = new ArrayList<BigDecimal>();
        String sql="";
        if (user != null && user.getNamayandegi() != null) //agar admin nabood
        {
            System.out.println(user.getNamayandegi().getId());
            if (userHasRole(user, "ROLE_MALI_VIEW") || userHasRole(user, "ROLE_KARBAR_MALI")) {
                 sql = " SELECT id FROM tbl_namayande CONNECT BY NOCYCLE PRIOR id = sarparast_id START WITH id = '" + user.getNamayandegi().getId() + "'  ";
                     //   " where nam.id is not null ";//and nam.id = " + user.getNamayandegi().getId();
                SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
                namayandeIdlist = sqlQuery.list();
            } else {
                namayandeIdlist.add(new BigDecimal(user.getNamayandegi().getId()));
                sql = "select c.namayande.id from c.namayande where c.namayande.id = " + user.getNamayandegi().getId();
            }
           // System.out.println(sql);
        }
        //SQLQuery Str=getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);


        List<Long> namayandeIdListLong = new ArrayList<Long>();
        for (BigDecimal namayandeIdD : namayandeIdlist) {
            namayandeIdListLong.add(Long.parseLong(namayandeIdD.toString()));
        }
        String hql = "";
        String hqlWhere = "";

        hql += "SELECT c FROM Credebit c left join c.daryafteCheck cd Left join c.check cc Left join c.daryafteFish cdfish WHERE 1=1 ";

        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    hqlWhere += " AND (c.namayande IS NOT NULL AND c.namayande.id = " + user.getNamayandegi().getId();
                    hqlWhere += " OR c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id = " + user.getNamayandegi().getId();
                    hqlWhere += " OR c.namayande IS NOT NULL AND c.namayande.id in ( " + StringUtil.join(namayandeIdListLong, ",") + ")";
                    hqlWhere += " OR c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id in ( " + StringUtil.join(namayandeIdListLong, ",") + ")";
                    hqlWhere += ")";
                }
            }
        }
        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            hqlWhere+= " AND (c.daftar.id="+daftar_id+" OR c.daftar.id is null)";
//        else
            hqlWhere+=" AND c.daftar.id="+daftar_id;
        ////// ta inja

        //for search
        if (credebitNoe != null) {
            Criterion criterion = null;
            hqlWhere += " AND ( ";
            if (credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes)
                    hqlWhere += " c.credebitType = '" + bedehiType + "' OR ";
            } else if (credebitNoe.equals(Credebit.CredebitMainType.ETEBAR)) {
                for (Credebit.CredebitType etebarType : Credebit.etebarTypes)
                    hqlWhere += " c.credebitType = '" + etebarType + "' OR ";
            }

            hqlWhere += " 1=2 )";

        }

        if (identifier != null && identifier.length() > 0)
            hqlWhere += " AND c.identifier LIKE '%" + identifier + "%'";

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            hqlWhere += " AND c.shomareMoshtari = '" + shomareMoshtari + "'";

        if (rcptName != null && rcptName.length() > 0)
            hqlWhere += " AND c.rcptName like '%" + rcptName + "%'";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            hqlWhere += " AND c.sarresidDate >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            hqlWhere += " AND c.sarresidDate <= '" + sarresidDateTo + "'";

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hqlWhere += " AND c.createdDate >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            hqlWhere += " AND c.createdDate <= '" + createdDateTo + "'";

        if (amount != null && amount.length() > 0)
            hqlWhere += " AND c.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            hqlWhere += " AND c.paidReceivedAmount = " + Long.parseLong(paidReceivedAmount.replaceAll(",", ""));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            hqlWhere += " AND c.remainingAmount_long = " + Long.parseLong(remainingAmount.replaceAll(",", ""));

        if (credebitTypeFarsi != null)
            hqlWhere += " AND c.credebitType = '" + credebitTypeFarsi + "'";

        if (shomareGharardad != null && shomareGharardad.length() > 0)
            hqlWhere += " AND c.shomareGharardad = " + shomareGharardad;

        if (subsystem_field != null && subsystem_field.length() > 0)
            hqlWhere += " AND c.subsystemName = '" + subsystem_field + "'";

        if (shomareCheck != null && shomareCheck.length() > 0)
        {
             if (credebitNoe == null)
             {
                 hqlWhere +=" AND (cd.serial ='"+ shomareCheck +"' OR cc.shomare ='"+ shomareCheck + "')";

             }
           else if (credebitNoe.equals(Credebit.CredebitMainType.BEDEHI))
            //hqlWhere += " AND c.check.shomare = '" + shomareCheck + "'";
            hqlWhere += " AND cc.shomare ='"+ shomareCheck + "'";
           else if (credebitNoe.equals(Credebit.CredebitMainType.ETEBAR))
            //hqlWhere += " AND c.daryafteCheck.serial = '" + shomareCheck + "'";
            hqlWhere +=" AND cd.serial ='"+ shomareCheck+"'";
        }


        if (namayandeId != null && namayandeId > 0) {
            hql = "SELECT c FROM Credebit c INNER JOIN c.namayande nam WHERE 1=1";
            hqlWhere += " AND c.namayande IS NOT NULL AND c.namayande.id = " + namayandeId;
        }


        if (statusFarsi != null) {
            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
                hqlWhere += " AND c.remainingAmount_long != amount_long ";
            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
                hqlWhere += " AND c.remainingAmount_long != 0 AND c.remainingAmount_long = amount_long ";
        }

        if (bankName != null && !bankName.equals("")) {
            hqlWhere += " AND c.bankName = '" + bankName + "'";
        }

        if (shomareFish != null && !shomareFish.isEmpty())
        {
            hqlWhere +=" AND cdfish.shomareFish = '"+ shomareFish+"'";
        }

        if (vosoulStateFarsi != null) {
            if (credebitNoe != null && credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                    hqlWhere += " AND c.id IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " AND cc.id not in (select ccc.id from Credebit ccc inner join ccc.khateSanadsBedehi k1 inner join k1.etebarCredebit e  where ccc.id = c.id   AND  ccc.remainingAmount_long = 0  " +
                            " group by ccc.id, e.vosoulState having e.vosoulState='TAEED_NASHODE' )" +
                            " group by cc.id, ec.vosoulState  )";
                    // " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL'
                } else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE)) {
                    hqlWhere += " AND c.id NOT IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " AND cc.id not in (select ccc.id from Credebit ccc inner join ccc.khateSanadsBedehi k1 inner join k1.etebarCredebit e  where ccc.id = c.id   AND  ccc.remainingAmount_long = 0  " +
                            " group by ccc.id, e.vosoulState having e.vosoulState='TAEED_NASHODE' )" +
                            " group by cc.id, ec.vosoulState  )";
                    // " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL'
                }
            } else {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                    hqlWhere += " AND c.vosoulState = '" + vosoulStateFarsi + "'";
                else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE))
                    hqlWhere += " AND (c.vosoulState IS NULL OR c.vosoulState = '" + vosoulStateFarsi + "')";
            }
        }

        Query query = getSession().createQuery(hql + hqlWhere);
        ////////////////////////// chekah
        System.out.println("sql ");
        System.out.println(hqlToSql(query.getQueryString()));

        //b-h
        /////
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            List list = query.list();
            paginatedList.setPageNumber(1);
            paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            ArrayList rtnList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
        } else {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            query.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query.setFirstResult(firstResult);
            List list = query.list();
//            for(int i=firstResult;i<firstResult+paginatedList.getObjectsPerPage();i++)
//            {
//                rtnList.add(list.get(i));
//            }
            if (list != null)
                paginatedList.setList(rtnList);
//            rtnList=new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }

            Query queryCount = getSession().createQuery("SELECT count(c.id) FROM Credebit c left join c.daryafteCheck cd Left join c.check cc Left join c.daryafteFish cdfish WHERE 1=1 " + hqlWhere);
            Long count = Long.parseLong(queryCount.list().get(0).toString());
            paginatedList.setFullListSize(count.intValue());
        }

        return paginatedList;

    }

    public PaginatedListImpl<Credebit> findListCredebitShabaPaginated(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);

        String hql = "";
        String hqlWhere = "";

        hql += "SELECT c FROM Credebit c   WHERE 1=1 ";

        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    hqlWhere += " AND c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id = " + user.getNamayandegi().getId();
                }
            }
        }


        //for search
        if (credebitNoe != null) {
            Criterion criterion = null;
            hqlWhere += " AND ( ";
            if (credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes)
                    hqlWhere += " c.credebitType = '" + bedehiType + "' OR ";
            } else if (credebitNoe.equals(Credebit.CredebitMainType.ETEBAR)) {
                for (Credebit.CredebitType etebarType : Credebit.etebarTypes)
                    hqlWhere += " c.credebitType = '" + etebarType + "' OR ";
            }

            hqlWhere += " 1=2 )";

        }

        if (identifier != null && identifier.length() > 0)
            hqlWhere += " AND c.identifier LIKE '%" + identifier + "%'";

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            hqlWhere += " AND c.shomareMoshtari = '" + shomareMoshtari + "'";

        if (rcptName != null && rcptName.length() > 0)
            hqlWhere += " AND c.rcptName like '%" + rcptName + "%'";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            hqlWhere += " AND c.sarresidDate >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            hqlWhere += " AND c.sarresidDate <= '" + sarresidDateTo + "'";

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hqlWhere += " AND c.createdDate >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            hqlWhere += " AND c.createdDate <= '" + createdDateTo + "'";

        if (amount != null && amount.length() > 0)
            hqlWhere += " AND c.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            hqlWhere += " AND c.paidReceivedAmount = " + Long.parseLong(paidReceivedAmount.replaceAll(",", ""));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            hqlWhere += " AND c.remainingAmount_long = " + Long.parseLong(remainingAmount.replaceAll(",", ""));

        hqlWhere += " AND (c.credebitType = 'VEHICLE_HAGHBIME_BARGASHTI' OR c.credebitType = 'VEHICLE_KHESARAT')";
        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id ==1)
//            hqlWhere+=" AND (c.daftar.id="+daftar_id+" OR c.daftar.id is NULL)";
//        else
            hqlWhere+=" AND c.daftar.id="+daftar_id;
        /// ta inja
        if (shomareGharardad != null && shomareGharardad.length() > 0)
            hqlWhere += " AND c.shomareGharardad = " + shomareGharardad;

        if (subsystem_field != null && subsystem_field.length() > 0)
            hqlWhere += " AND c.subsystemName = '" + subsystem_field + "'";

        if (namayandeId != null && namayandeId > 0) {
            hql = "SELECT c FROM Credebit c INNER JOIN c.namayande nam WHERE 1=1";
            hqlWhere += " AND c.namayande IS NOT NULL AND c.namayande.id = " + namayandeId;
        }

        if (statusFarsi != null) {
            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
                hqlWhere += " AND c.remainingAmount_long != amount_long ";
            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
                hqlWhere += " AND c.remainingAmount_long != 0 "; /* c.remainingAmount_long = amount_long*/    /* AND   c.khateSanadsEtebar !=null */
        }

        if (bankName != null && !bankName.equals("")) {
            hqlWhere += " AND c.bankName = '" + bankName + "'";
        }


        if (vosoulStateFarsi != null) {
            if (credebitNoe != null && credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                    hqlWhere += " AND c.id IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " group by cc.id, ec.vosoulState " +
                            " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL' )";
                } else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE)) {
                    hqlWhere += " AND c.id NOT IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " group by cc.id, ec.vosoulState " +
                            " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL' )";
                }
            } else {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                    hqlWhere += " AND c.vosoulState = '" + vosoulStateFarsi + "'";
                else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE))
                    hqlWhere += " AND (c.vosoulState IS NULL OR c.vosoulState = '" + vosoulStateFarsi + "')";
            }
        }

        Query query = getSession().createQuery(hql + hqlWhere);
        System.out.println("shaba :"+hqlToSql(hql + hqlWhere));
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            List list = query.list();
            paginatedList.setPageNumber(1);
            paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            ArrayList rtnList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
        } else {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            List list = query.list();
            query.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query.setFirstResult(firstResult);
            for (int i = firstResult; i < firstResult + paginatedList.getObjectsPerPage() && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
            rtnList = new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }

            Query queryCount = getSession().createQuery("SELECT count(c.id) FROM Credebit c WHERE 1=1 " + hqlWhere);
            Long count = Long.parseLong(queryCount.list().get(0).toString());
            paginatedList.setFullListSize(count.intValue());
        }

        return paginatedList;

    }

    public PaginatedListImpl<BankInfo> findAllBankInfoPaginated(User user, String createdDateFrom, String createdDateTo, String tarikhVarizFrom, String tarikhVarizTo, String fullname, String codeMoshtari, String vosoulDesc) {
        PaginatedListImpl<BankInfo> paginatedList = new PaginatedListImpl<BankInfo>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);

        String hql = "";
        String hqlWhere = "";

        hql += "SELECT b FROM BankInfo b WHERE 1=1 ";

        if (tarikhVarizFrom != null && tarikhVarizFrom.length() > 0)
            hqlWhere += " AND b.taarikh >= '" + tarikhVarizFrom + "'";

        if (tarikhVarizTo != null && tarikhVarizTo.length() > 0)
            hqlWhere += " AND b.taarikh <= '" + tarikhVarizTo + "'";

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hqlWhere += " AND b.createdDate >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            hqlWhere += " AND b.createdDate <= '" + createdDateTo + "'";

        if (codeMoshtari != null && codeMoshtari.length() > 0)
            hqlWhere += " AND b.codeMoshtari LIKE '%" + codeMoshtari + "%'";

        if (vosoulDesc != null && vosoulDesc.length() > 0)
            hqlWhere += " AND b.status LIKE '" + vosoulDesc + "'";

        if (fullname != null && fullname.length() > 0) {
            hql = "SELECT b FROM BankInfo b  INNER JOIN b.bargozarandeh ba INNER JOIN ba.user us WHERE 1=1";
            hqlWhere += " AND ba.user IS NOT NULL AND us.firstName || ' ' || us.lastName LIKE '%" + fullname + "%'";
        }

        String orderStr = " ORDER BY b.id desc";
        Query query = getSession().createQuery(hql + hqlWhere + orderStr);

        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            List list = query.list();
            paginatedList.setPageNumber(1);
            paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            ArrayList rtnList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
        } else {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            query.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query.setFirstResult(firstResult);
            List list = query.list();
            for (int i = firstResult; i < firstResult + paginatedList.getObjectsPerPage() && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
            rtnList = new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }

            String hqlCount = hql.replace("SELECT b", "SELECT count(b)");
            Query queryCount = getSession().createQuery(hqlCount + hqlWhere);
            Long count = Long.parseLong(queryCount.list().get(0).toString());
            paginatedList.setFullListSize(count.intValue());
        }

        return paginatedList;

    }

    public PaginatedListImpl<Credebit> findListGozareshVosouli(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName, String subsystem_field) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);

        //createdDateFrom = createdDateTo = DateUtil.getCurrentDate();

        String hql1 = "";
        String hql2 = "";
        String hqlWhere = "";

        hql1 = " SELECT c FROM Credebit c " +
                " WHERE length(c.shenaseCredebit) > 8 " +
                ((identifier != null && identifier.length() > 0) ? " AND c.identifier LIKE '%" + identifier + "%'" : "") +
                " AND c.id IN (SELECT bc.id FROM Credebit bc " +
                " left join bc.khateSanadsBedehi kb " +
                " left join kb.etebarCredebit ec " +
                " left join ec.bankInfoList b " +
                " WHERE bc.subsystemName like 'VEHICLE' " +
                " AND length(bc.shenaseCredebit) > 8" +
                ((identifier != null && identifier.length() > 0) ? " AND bc.identifier LIKE '%" + identifier + "%'" : "") +
                ((createdDateFrom != null && createdDateFrom.length() > 0) ? " AND CASE WHEN (LENGTH(b.taarikh) = 10) THEN b.taarikh ELSE b.createdDate END >= '" + createdDateFrom + "'" : "") +
                ((createdDateTo != null && createdDateTo.length() > 0) ? " AND CASE WHEN (LENGTH(b.taarikh) = 10) THEN b.taarikh ELSE b.createdDate END <= '" + createdDateTo + "'" : "") +
                " AND bc.id IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ecc " +
                " where cc.remainingAmount_long = 0 " +
                " AND cc.id = bc.id " +
                " AND ecc.credebitType != 'ELHAGHIE_BARGASHTI'" +
                " AND ecc.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                " AND ecc.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                " AND ecc.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                " group by cc.id, ecc.vosoulState " +
                " having max(ecc.vosoulState) = 'TAEED_VOSOUL' AND min(ecc.vosoulState) = 'TAEED_VOSOUL' ))";

        hql2 = " SELECT c.id FROM Credebit c " +
                " WHERE (c.credebitType like 'ELHAGHIE_BARGASHTI' or c.credebitType like 'VEHICLE_HAGHBIME_ELECTRONICI' or c.credebitType like 'VEHICLE_HAGHBIME_BARGASHTI' ) " +
                " AND c.identifier LIKE '%" + identifier + "%'" +
                " AND c.subsystemName LIKE 'VEHICLE' " +
                ((createdDateFrom != null && createdDateFrom.length() > 0) ? " AND c.createdDate >= '" + createdDateFrom + "'" : "") +
                ((createdDateTo != null && createdDateTo.length() > 0) ? " AND c.createdDate <= '" + createdDateTo + "'" : "");

        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    hqlWhere += " AND (c.namayande IS NOT NULL AND c.namayande.id = " + user.getNamayandegi().getId();
                    hqlWhere += " OR c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id = " + user.getNamayandegi().getId() + ")";
                }
            }
        }

        Query query1 = getSession().createQuery(hql1 + hqlWhere);//.setParameter("createdDateFrom",createdDateFrom).setParameter("createdDateTo",createdDateTo);
        Query query2 = getSession().createQuery(hql2 + hqlWhere);//.setParameter("createdDateFrom",createdDateFrom).setParameter("createdDateTo",createdDateTo);

        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            List list1 = query1.list();
            list1.addAll(query2.list());
            paginatedList.setPageNumber(1);
            paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            if (list1 != null)
                paginatedList.setList(list1);
        } else {
            paginatedList.setPageNumber(pageNumber);
            List<Credebit> list1 = new ArrayList<Credebit>();
            list1.addAll(query1.list());
            list1.addAll(query2.list());
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            query1.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query1.setFirstResult(firstResult);
            query2.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query2.setFirstResult(firstResult);
            for (int i = firstResult; i < firstResult + paginatedList.getObjectsPerPage() && i < list1.size(); i++) {
                rtnList.add(list1.get(i));
            }
            if (list1 != null)
                paginatedList.setList(rtnList);
//            rtnList=new ArrayList();
//            for(int i=0;i<Integer.MAX_VALUE && i<list1.size();i++)
//            {
//                rtnList.add(list1.get(i));
//            }


            paginatedList.setFullListSize(list1.size());
        }

        return paginatedList;

    }

    public PaginatedListImpl<Credebit> findAllCredebitsMaliPaginated(User user, Credebit.CredebitMainType credebitNoe, String identifier, String shomareMoshtari, String rcptName, String sarresidDateFrom, String sarresidDateTo, String createdDateFrom, String createdDateTo, String amount, String paidReceivedAmount, String remainingAmount, Credebit.CredebitType credebitTypeFarsi, String shomareGharardad, Credebit.Status statusFarsi, Credebit.VaziyatVosoul vosoulStateFarsi, Long namayandeId, String bankName) {
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        List<BigDecimal> namayandeIdlist = new ArrayList<BigDecimal>();
        if (user != null && user.getNamayandegi() != null) {
            if (userHasRole(user, "ROLE_MALI_VIEW")) {
                String sql = "select na.id from tbl_namayande na " +
                        " left join (SELECT n.id FROM tbl_namayande n START WITH id = " + user.getNamayandegi().getId() + " CONNECT BY NOCYCLE PRIOR id = sarparast_id)nam on nam.id = na.id " +
                        " where nam.id is not null ";//and nam.id = " + user.getNamayandegi().getId();

                SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
                namayandeIdlist = sqlQuery.list();
            } else {
                namayandeIdlist.add(new BigDecimal(user.getNamayandegi().getId()));
            }
        }

        List<Long> namayandeIdListLong = new ArrayList<Long>();
        for (BigDecimal namayandeIdD : namayandeIdlist) {
            namayandeIdListLong.add(Long.parseLong(namayandeIdD.toString()));
        }

        String hql = "";
        String hqlWhere = "";

        hql += "SELECT c FROM Credebit c WHERE 1=1 ";

        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    hqlWhere += " AND (c.namayande IS NOT NULL AND c.namayande.id = " + user.getNamayandegi().getId();
                    hqlWhere += " OR c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id = " + user.getNamayandegi().getId();
                    hqlWhere += " OR c.namayande IS NOT NULL AND c.namayande.id in ( " + StringUtil.join(namayandeIdListLong, ",") + ")";
                    hqlWhere += " OR c.vahedeSodor IS NOT NULL AND c.vahedeSodor.id in ( " + StringUtil.join(namayandeIdListLong, ",") + "))";
                }
            }
        }


        //for search
        if (credebitNoe != null) {
            Criterion criterion = null;
            if (credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                hqlWhere += " AND ( ";
                for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes)
                    hqlWhere += " c.credebitType = '" + bedehiType + "' OR ";
            } else if (credebitNoe.equals(Credebit.CredebitMainType.ETEBAR)) {
                for (Credebit.CredebitType etebarType : Credebit.etebarTypes)
                    hqlWhere += " c.credebitType = '" + etebarType + "' OR ";
            }

            hqlWhere += " 1=2 )";

        }

        if (identifier != null && identifier.length() > 0)
            hqlWhere += " AND c.identifier LIKE '%" + identifier + "%'";

        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
            hqlWhere += " AND c.shomareMoshtari = '" + shomareMoshtari + "'";

        if (rcptName != null && rcptName.length() > 0)
            hqlWhere += " AND c.rcptName = '" + rcptName + "'";
        ;

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            hqlWhere += " AND c.sarresidDate >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            hqlWhere += " AND c.sarresidDate <= '" + sarresidDateTo + "'";
        ;

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            hqlWhere += " AND c.createdDate >= '" + createdDateFrom + "'";
        ;

        if (createdDateTo != null && createdDateTo.length() > 0)
            hqlWhere += " AND c.createdDate <= '" + createdDateTo + "'";
        ;

        if (amount != null && amount.length() > 0)
            hqlWhere += " AND c.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
            hqlWhere += " AND c.paidReceivedAmount = " + Long.parseLong(paidReceivedAmount.replaceAll(",", ""));
        }

        if (remainingAmount != null && remainingAmount.length() > 0)
            hqlWhere += " AND c.remainingAmount_long = " + Long.parseLong(remainingAmount.replaceAll(",", ""));

        if (credebitTypeFarsi != null)
            hqlWhere += " AND c.credebitType = '" + credebitTypeFarsi + "'";

        if (shomareGharardad != null && shomareGharardad.length() > 0)
            hqlWhere += " AND c.shomareGharardad = " + shomareGharardad;

        if (namayandeId != null && namayandeId > 0) {
            hql = "SELECT c FROM Credebit c INNER JOIN c.namayande nam WHERE 1=1";
            hqlWhere += " AND c.namayande IS NOT NULL AND c.namayande.id = " + user.getNamayandegi().getId();
        }

        if (statusFarsi != null) {
            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
                hqlWhere += " AND c.remainingAmount_long != amount_long ";
            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
                hqlWhere += " AND c.remainingAmount_long = amount_long ";
        }

        if (bankName != null && !bankName.equals("")) {
            hqlWhere += " AND c.bankName = '" + bankName + "'";
            ;
        }


        if (vosoulStateFarsi != null) {
            if (credebitNoe != null && credebitNoe.equals(Credebit.CredebitMainType.BEDEHI)) {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                    hqlWhere += " AND c.id IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " group by cc.id, ec.vosoulState " +
                            " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL' )";
                } else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE)) {
                    hqlWhere += " AND c.id NOT IN (select cc.id from Credebit cc inner join cc.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                            " where cc.id = c.id " +
                            " AND cc.remainingAmount_long = 0 " +
                            " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
                            " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
                            " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                            " group by cc.id, ec.vosoulState " +
                            " having max(ec.vosoulState) = 'TAEED_VOSOUL' AND min(ec.vosoulState) = 'TAEED_VOSOUL' )";
                }
            } else {
                if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                    hqlWhere += " AND c.vosoulState = '" + vosoulStateFarsi + "'";
                else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE))
                    hqlWhere += " AND (c.vosoulState IS NULL OR c.vosoulState = '" + vosoulStateFarsi + "')";
            }
        }

        Query query = getSession().createQuery(hql + hqlWhere);

        paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport = false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if ((name.startsWith("d-") && name.endsWith("-e"))) {
                    isExport = true;
                }
            }
        }
        if (isExport) {
            List list = query.list();
            paginatedList.setPageNumber(1);
            paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            ArrayList rtnList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
        } else {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber() - 1);
            ArrayList rtnList = new ArrayList();
            query.setMaxResults(firstResult + paginatedList.getObjectsPerPage());
            query.setFirstResult(firstResult);
            List list = query.list();
            for (int i = firstResult; i < firstResult + paginatedList.getObjectsPerPage() && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }
            if (list != null)
                paginatedList.setList(rtnList);
            rtnList = new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE && i < list.size(); i++) {
                rtnList.add(list.get(i));
            }

            Query queryCount = getSession().createQuery("SELECT count(c.id) FROM Credebit c WHERE 1=1 " + hqlWhere);
            Long count = Long.parseLong(queryCount.list().get(0).toString());
            paginatedList.setFullListSize(count.intValue());
        }

        return paginatedList;

    }

    public Credebit.VaziyatVosoul getVaziyatVosulBedehi(Integer credebitId) {
        List<KhateSanad> allKhateSanadByCredebitId = findAllKhateSanadByCredebitId(credebitId);
        System.out.println("khate sanad tedad"+allKhateSanadByCredebitId.size());
        if (allKhateSanadByCredebitId != null && allKhateSanadByCredebitId.size() > 0) {
            Query query = getSession().createQuery
                    (
                            "select c.id from Credebit c inner join c.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                                    " where c.id = :cid " +
                                    " AND c.remainingAmount_long = 0 " +
//                                    " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
//                                    " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
//                                    " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
//                                    " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                                    " group by c.id, ec.vosoulState " +
//                                    " having count(ec.vosoulState) = 1 AND ec.vosoulState = 'TAEED_VOSOUL' "
                                    " having ec.vosoulState = 'TAEED_NASHODE' "
                    ).setParameter("cid", credebitId);
            List credebitList = query.list();

            System.out.println("validation query"+hqlToSql("select c.id from Credebit c inner join c.khateSanadsBedehi k inner join k.etebarCredebit ec " +
                    " where c.id = :cid " +
                    " AND c.remainingAmount_long = 0 " +
//                                    " AND ec.credebitType != 'ELHAGHIE_BARGASHTI'" +
//                                    " AND ec.credebitType != 'VEHICLE_HAGHBIME_ELECTRONICI'" +
//                                    " AND ec.credebitType != 'VEHICLE_HAGHBIME_BARGASHTI'" +
//                                    " AND ec.credebitType != 'AZ_MAHALLE_TABLIGHAT'" +
                    " group by c.id, ec.vosoulState " +
//                                    " having count(ec.vosoulState) = 1 AND ec.vosoulState = 'TAEED_VOSOUL' "
                    " having ec.vosoulState = 'TAEED_NASHODE' "
            ));
            if (credebitList != null && credebitList.size() > 0)
                return Credebit.VaziyatVosoul.TAEED_NASHODE;
            return Credebit.VaziyatVosoul.TAEED_VOSOUL;

        }


        return Credebit.VaziyatVosoul.TAEED_NASHODE;

    }

    public PaginatedListImpl<Credebit> findAllEtebarCredebitsByStatusPaginated(User user, Integer credebitId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        criteria.setProjection(Projections.id());

        Criterion criterion = null;
        for (Credebit.CredebitType etebarType : Credebit.etebarTypes) {
            boolean isValid = true;
            for (Credebit.CredebitType credebitTypesNaMotabarSanadDasti : Credebit.credebitTypesNaMotabarSanadDasti)
                if (credebitTypesNaMotabarSanadDasti.equals(etebarType)) {
                    if (etebarType.equals(Credebit.CredebitType.AZ_MAHALLE_TABLIGHAT)) {
                        // for manual entries
                        if (criterion == null)
                            criterion = Restrictions.and(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType), Restrictions.eq("description", "MANUAL_CREATED"));
                        else
                            criterion = Restrictions.or(criterion, Restrictions.and(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType), Restrictions.eq("description", "MANUAL_CREATED")));
                    }
                    isValid = false;
                }

            if (isValid)
                if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType);
                else criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType));

        }
        criteria.add(criterion);

        criteria.add(Restrictions.gt("c.remainingAmount_long", 0l));

        if (user != null) {

            if (userHasRole(user, "ROLE_KARBAR_MALI") && userHasRole(user, "ROLE_NAMAYANDE")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getId())));
                }
            }
        }

        if (credebitId != null) {
            criteria.add(Restrictions.eq("c.id", credebitId));
        }
        //b-h
        Integer daftar_id=1;
        if(user != null)
                daftar_id=user.getDaftar().getId();
//         Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//         if(daftar_id==1)
//             criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
//         else
        criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
        ///// ta inja
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
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

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.id"));
        nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());

        //b-h
        CriteriaImpl c = (CriteriaImpl) nonDetachedCriteria;
        SessionImpl s = (SessionImpl) c.getSession();
        SessionFactoryImplementor factory = (SessionFactoryImplementor) s.getSessionFactory();
        String[] implementors = factory.getImplementors(c.getEntityOrClassName());
        LoadQueryInfluencers lqis = new LoadQueryInfluencers();
        CriteriaLoader loader = new CriteriaLoader((OuterJoinLoadable) factory.getEntityPersister(implementors[0]), factory, c, implementors[0], lqis);
       try{
        Field f = OuterJoinLoader.class.getDeclaredField("sql");
        f.setAccessible(true);
        String sql = (String) f.get(loader);
     //   System.out.println("bahare");
     //   System.out.println(sql);
       }
       catch (Exception e){
           e.printStackTrace();
       }
               //b-h
//        for(int i=0;i<paginatedList.getList().size();i++) {
//            System.out.println(i);
//            System.out.println(paginatedList.getList().get(i).getVosoulState());
//        }

        return paginatedList;

    }

    public PaginatedListImpl<Credebit> findAllBedehiCredebitsByStatusPaginated(User user, Integer credebitId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        criteria.setProjection(Projections.id());

        //criteria.add(Restrictions.eq(CREDEBIT__STATUS, Credebit.Status.SANAD_NA_KHORDE))
        criteria.add(Restrictions.or(
                Restrictions.or(
                        Restrictions.or(
                                Restrictions.or(
                                        Restrictions.or(
                                                Restrictions.or(
                                                        Restrictions.or(
                                                             Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST)
                                                             ,Restrictions.eq(CREDEBIT__CREDEBIT_TYPE,Credebit.CredebitType.DARMAN_HAGHBIME)
                                                        )
                                                        ,
                                                        Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PARDAKHTE_CHECK)
                                                )
                                                ,
                                                Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PARDAKHTE_TANKHAH)
                                        ),
                                        Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.VEHICLE_HAGHBIME)
                                )
                                , Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.HESAB_FI_MA_BEYN_BEDEHI)
                        )
                        , Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.VEHICLE_HAGHBIME_ELECTRONICI)
                )
                , Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST_VAM))
        );

        criteria.add(Restrictions.gt("c.remainingAmount_long", 0l));

        if (user != null) {
            if (userHasRole(user, "ROLE_KARBAR_MALI") && userHasRole(user, "ROLE_NAMAYANDE")) {
                if (user != null && user.getNamayandegi() != null) {
                    //criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"),Restrictions.eq("c.namayande.id",user.getNamayandegi().getId())));
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("c.vahedeSodor.id", user.getNamayandegi().getId())));
                }
            }
        }
        if (credebitId != null)
            criteria.add(Restrictions.eq("c.id", credebitId));
        //b-h

         Integer   daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
//        else
            criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
        ////// ta inja
        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
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

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.id"));
        nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());
        System.out.println("bedehi list size"+paginatedList.getList().size());
        return paginatedList;
    }

    public Credebit findBedehiCredebitsByCodeMoshtari(String codeMoshtari, Long amount) {
        // added for code moshtarihaye 18 raghami ke vase duplicateha ezafe shodan
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class);
        criteria.add(Restrictions.or(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST_VAM), Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST)));
//        criteria.add(Restrictions.gt("remainingAmount_long", 0l));
        criteria.add(Restrictions.eq("remainingAmount_long", amount));
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            // baraye code moshtarihaye tekrari ke 18 raghami hastand ezafe shod
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Credebit.class);
            criteria.add(Restrictions.or(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST_VAM), Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST)));
            criteria.add(Restrictions.eq("remainingAmount_long", amount));
            criteria.add(Restrictions.like("shomareMoshtari", codeMoshtari, MatchMode.ANYWHERE));
            result = criteria.list();
            if (result.size() > 0)
                return result.get(0);
        }
        return null;
    }

    public List<Credebit> findBedehiByBimenameAndAmount(String shomareBimename, Long amount) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class, "c").createCriteria("c.bimename", "b");
        criteria.add(Restrictions.or(Restrictions.eq("c."+CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST_VAM), Restrictions.eq("c."+CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST)));
        criteria.add(Restrictions.eq("c.remainingAmount_long", amount));
        criteria.add(Restrictions.eq("b.shomare", shomareBimename));
        criteria.addOrder(Order.asc("c.sarresidDate"));
        return criteria.list();
    }

    public Credebit findEtebarCredebitsByCodeMoshtari(String codeMoshtari, String amount) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class);

        Criterion criterion = null;
        for (Credebit.CredebitType etebarType : Credebit.etebarTypes) {
            if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType);
            else criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, etebarType));
        }
        criteria.add(criterion);
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            if (amount != null && result.get(0).getAmount_long().equals(Long.parseLong(amount.replaceAll(",", "").trim()))) {
                return result.get(0);
            } else {
                Credebit c = new Credebit();
                c.setId(-1);
                return c;
            }
        } else
            return null;
    }

    public Credebit findEtebarCredebitsByCodeMoshtariAndTypeOfSystem(String typeOfSystem, String codeMoshtari) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class);

        Criterion criterion = null;
        criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST);


        criteria.add(criterion);
        criteria.add
                (
                        Restrictions.and
                                (Restrictions.eq("shomareMoshtari", codeMoshtari), Restrictions.or(Restrictions.eq("subsystemName", typeOfSystem), Restrictions.isNull("subsystemName"))));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        } else
            return null;
    }

    public Credebit findAnyCredebitByCodeMoshtari(String codeMoshtari) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public Credebit findPardakhtShenaseDarCredebitByCodeMoshtari(String codeMoshtari) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        criteria.add(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PARDAKHT_SHENASEDAR));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public Credebit executeCredebitFileUpload(Credebit credebit, BankInfo bankInfo, Long amountParamLong) {
        if (credebit != null && bankInfo != null) {
            //Long amountParamLong = Long.parseLong(bankInfo.getMablagh().replaceAll(",", "").trim());
            if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)) {
                bankInfo.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                credebit.setRemainingAmount_long(credebit.getRemainingAmount_long() + amountParamLong);
                credebit.setAmount_long(credebit.getAmount_long() + amountParamLong);

                super.save(credebit);
                return credebit;
            } else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI)) {
                return credebit;
//            } else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.MABLAGH_MAZAD)){
//                Long diff = amountParamLong - credebit.getAmount_long();
//                credebit.setAmount_long(amountParamLong);
//                credebit.setRemainingAmount_long(credebit.getRemainingAmount_long() + diff);
//
//                super.save(credebit);
//                return credebit;
            } else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR)) {
                boolean shouldDelete = true;
                List<KhateSanad> khateSanadList = findAllKhateSanadByEtebarCredebitId(credebit.getId());
                if (khateSanadList != null && khateSanadList.size() > 0) {
                    for (KhateSanad khateSanad : khateSanadList) {
                        if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), DateUtil.addDaysWithTatilat(khateSanad.getSanad().getCreatedDate(), 3)))
                            shouldDelete = false;
                        if (khateSanad.getSanad() != null && shouldDelete)
                            deleteSanad(khateSanad.getSanad().getId());
                    }
                }
                if (shouldDelete) {
                    credebit.setAmount_long(amountParamLong);
                    credebit.setRemainingAmount_long(amountParamLong);
                    super.save(credebit);
                }
                return credebit;
            }
        }
        return null;
    }


    public Credebit findBedehiCredebitsByCodeMoshtari(String codeMoshtari, String amount) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class);


        //faghat GHEST ?
        Criterion criterion = null;
        for (Credebit.CredebitType credebitType : Credebit.bedehiTypes) {
            if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, credebitType);
            else criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, credebitType));
        }
        criteria.add(criterion);
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            if (amount != null && result.get(0).getAmount_long().equals(Long.parseLong(amount.replaceAll(",", "").trim()))) {
                return result.get(0);
            } else {
                Credebit c = new Credebit();
                c.setId(-1);
                return c;
            }
        } else
            return null;
    }

    public Credebit findCredebitByCodeMoshtari(String codeMoshtari) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST)
                        ,
                        Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.GHEST_VAM)
                )

        );
        criteria.add(Restrictions.gt("remainingAmount_long", 0l));
        criteria.add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> result = criteria.list();
        if (result.size() == 1)
            return result.get(0);
        else
            return null;
    }


    public boolean isAvailableCodeMoshtari(String codeMoshtari) {
        Query query = getSession().createQuery("select c.shomareMoshtari from Credebit c where c.shomareMoshtari = :shMoshtari").setString("shMoshtari", codeMoshtari);
        return query.list().size() > 0;
    }

    public PardakhteTankhah findPardakhtTankhah(Integer id) {
        return (PardakhteTankhah) super.findById(PardakhteTankhah.class, id);
    }

    public void savePardakhteTankhah(PardakhteTankhah pardakhteTankhah) {
        super.save(pardakhteTankhah);
    }

    public DaryafteFish findDaryafteFish(Integer id) {
        return (DaryafteFish) super.findById(DaryafteFish.class, id);
    }

    public void saveDaryafteFishElectroniki(DaryafteFish daryafteFish) {
        super.save(daryafteFish);
    }

    public void saveKhateSanad(KhateSanad khateSanad) {
        super.saveOrUpdate(khateSanad);
    }

    public void saveKhateSanadLog(KhateSanadLog khateSanadLog) {
        super.saveOrUpdate(khateSanadLog);
    }

    public void saveAllKhateSanad(Set<KhateSanad> khateSanadSet) {
        super.saveOrUpdateAll(khateSanadSet);
    }

    public void updateCredebit(Credebit credebit) {
            super.update(credebit);
    }

    public void saveAllSanad(List<Sanad> sanadList) {
        super.saveOrUpdateAll(sanadList);
    }

    public void saveAllCredebit(List<Credebit> credebitList) {
        super.saveOrUpdateAll(credebitList);
    }

    public void saveDaryafteCheck(DaryafteCheck daryafteCheck) {
        super.save(daryafteCheck);
    }

    public List<DaryafteCheck> findAllDaryafteChecks() {
        return (List<DaryafteCheck>) super.findAll(DaryafteCheck.class);
    }

    public List<Hesab> findAllHesabs() {
        return (List<Hesab>) super.findAll(Hesab.class);
    }

    public DaryafteCheck findDaryafteCheckById(Integer id) {
        return (DaryafteCheck) super.findById(DaryafteCheck.class, id);
    }

    public void updateDaryafteCheck(DaryafteCheck theCheck) {
        super.update(theCheck);
    }

    public void saveMosharekatDarManafe(Mosharekat mosharekatDarManafe) {
        super.saveOrUpdate(mosharekatDarManafe);
    }

    public List<KhateSanad> findAllKhateSanadsByCredebitId(Integer id) {
        return super.findByPropertyOfProperty(KhateSanad.class, BEDEHI_CREDEBIT, Credebit.ID, id);

    }

    public Mosharekat findMosharekatById(Integer id) {
        return (Mosharekat) super.findById(Mosharekat.class, id);
    }

    public List<Credebit> findEtebar(Credebit credebit) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        final Criteria c = session.createCriteria(Credebit.class, "e");
        c.createAlias("e.khateSanadsEtebar", "kh");
        c.add(Restrictions.eq("kh.bedehiCredebit", credebit));
        if (c.list().size() > 0)
            c.addOrder(Order.desc("e.createdDate"));
                return c.list();
    }

    public void updateMosharekatDarManafe(Mosharekat mosharekat) {
        super.update(mosharekat);
    }

    public PaginatedListImpl<AghsatMoavagh> findAllGhestMoavagh(AghsatMoavagh aghsatMoavagh) {
//        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//        boolean join = false;
//        final Criteria crit = session.createCriteria(Ghest.class);
//        crit.createCriteria("ghestBandi");

        String whereClause = "";

        if (!aghsatMoavagh.getAzTarikhSarresid().isEmpty()) {
//            crit.add(Restrictions.ge("sarresidDate", aghsatMoavagh.getAzTarikhSarresid()));
            whereClause += "g.sarresidDate >= '" + aghsatMoavagh.getAzTarikhSarresid() + "' and ";
        }
        if (!aghsatMoavagh.getTaTarikhSarresid().isEmpty()) {
//            crit.add(Restrictions.lt("sarresidDate", aghsatMoavagh.getTaTarikhSarresid()));
            whereClause += "g.sarresidDate <= '" + aghsatMoavagh.getTaTarikhSarresid() + "' and ";
        }

//        crit.createCriteria("credebit", "c");
//        crit.add(Restrictions.ne("c.remainingAmount_long", new Long(0)));
//        crit.createCriteria("ghestBandi","gb").createCriteria("gb.bimename","b");
        if (!aghsatMoavagh.getAzTarikh().isEmpty() && !aghsatMoavagh.getTaTarikh().isEmpty()) {
//            crit.createCriteria("p.bimename", "b")
//            crit.add(Restrictions.between("b.tarikhSodour", aghsatMoavagh.getAzTarikh(), aghsatMoavagh.getTaTarikh()));
            whereClause += "b.tarikhSodour <= '" + aghsatMoavagh.getTaTarikh() + "' and ";
            whereClause += "b.tarikhSodour >= '" + aghsatMoavagh.getAzTarikh() + "' and ";
        }
//        crit.createCriteria("b.pishnehadList","p").add(Restrictions.eq("p.valid",true));
        if (aghsatMoavagh.getPishnehad().getGharardad().getId() != null && aghsatMoavagh.getPishnehad().getGharardad().getId() > 0) {
//            crit.createCriteria("p.gharardad","pgh").add(Restrictions.eq("pgh.id",aghsatMoavagh.getPishnehad().getGharardad().getId()));
            whereClause += "pgh.id = " + aghsatMoavagh.getPishnehad().getGharardad().getId() + " and ";
        }

        if (!aghsatMoavagh.getOstan().isEmpty()) {
//            if (!join) {
//                crit.createCriteria("p.namayande", "n");
//                join = true;
//            }
            if (!aghsatMoavagh.getShahr().isEmpty()) {
//                crit.add(
//                        Restrictions.and(
//                                Restrictions.eq("n.ostan.cityId", aghsatMoavagh.getOstanId().longValue()),
//                                Restrictions.eq("n.shahr.cityId", aghsatMoavagh.getShahrId().longValue())
//                        )
//                );
                whereClause += "n.ostan.cityId = " + aghsatMoavagh.getOstanId().longValue() + " and "
                        + "n.shahr.cityId = " + aghsatMoavagh.getShahrId().longValue() + " and ";
            } else {
//                crit.add(Restrictions.eq("n.ostan.cityId", aghsatMoavagh.getOstanId().longValue()));
                whereClause += "n.ostan.cityId = " + aghsatMoavagh.getOstanId().longValue() + " and ";
            }
        }

        if (!aghsatMoavagh.getVahedSodur().isEmpty()) {
//            if (!join) {
//                crit.createCriteria("p.namayande", "n");
//                join = true;
//            }
//            crit.add(Restrictions.eq("n.vahedSodur.id", aghsatMoavagh.getVahedSodurId()));
            whereClause += "n.vahedSodur.id = " + aghsatMoavagh.getVahedSodurId() + " and ";

        }
        if (!aghsatMoavagh.getNamayande().isEmpty()) {
//            if (!join) {
//                crit.createCriteria("p.namayande", "n");
//                join = true;
//            }
//            crit.add(Restrictions.eq("n.id", aghsatMoavagh.getNamayandeId()));
            whereClause += "n.id = " + aghsatMoavagh.getNamayandeId() + " and ";
        }
        if (!aghsatMoavagh.getNoeGharardad().isEmpty()) {
//            crit.add(Restrictions.eq("p.noeGharardad", aghsatMoavagh.getNoeGharardad()));
            whereClause += "p.noeGharardad = '" + aghsatMoavagh.getNoeGharardad() + "' and ";
        }
        if (aghsatMoavagh.getTarh().getId() != null) {
//            crit.createCriteria("p.tarh","t");
//            crit.add(Restrictions.eq("t.name", aghsatMoavagh.getTarh().getName()));
            whereClause += "t.name = " + aghsatMoavagh.getTarh().getName() + " and ";
        }

        if (aghsatMoavagh.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime() != null && !aghsatMoavagh.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().isEmpty()) {
//            crit.createCriteria("p.estelam","pe");
//            crit.add(Restrictions.eq("pe.nahve_pardakht_hagh_bime",aghsatMoavagh.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime()));
            whereClause += "pe.nahve_pardakht_hagh_bime = '" + aghsatMoavagh.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime() + "' and ";
        }

//        if(aghsatMoavagh.getTaTarikhePardakht()!=null || !aghsatMoavagh.getTaTarikhePardakht().isEmpty() &&
//           aghsatMoavagh.getAzTarikhePardakht()==null || aghsatMoavagh.getAzTarikhePardakht().isEmpty())
//        {
//            whereClause +="(select max(c4.createdDate) from KhateSanad khe4 join khe4.etebarCredebit c4 join khe4.bedehiCredebit bc4 where bc4.id=c.id )<= '"+aghsatMoavagh.getTaTarikhePardakht()+"' and ";
//        }
//        if(aghsatMoavagh.getTaTarikhePardakht()==null || aghsatMoavagh.getTaTarikhePardakht().isEmpty() &&
//           aghsatMoavagh.getAzTarikhePardakht()!=null || !aghsatMoavagh.getAzTarikhePardakht().isEmpty())
//        {
//            whereClause +="(select max(c4.createdDate) from KhateSanad khe4 join khe4.etebarCredebit c4 join khe4.bedehiCredebit bc4 where bc4.id=c.id )>= '"+aghsatMoavagh.getAzTarikhePardakht()+"' and ";
//        }
//        if(aghsatMoavagh.getTaTarikhePardakht()!=null || !aghsatMoavagh.getTaTarikhePardakht().isEmpty() &&
//           aghsatMoavagh.getAzTarikhePardakht()!=null || !aghsatMoavagh.getAzTarikhePardakht().isEmpty())
//        {
//            whereClause +="(select max(c4.createdDate) from KhateSanad khe4 join khe4.etebarCredebit c4 join khe4.bedehiCredebit bc4 where bc4.id=c.id ) between '"+aghsatMoavagh.getAzTarikhePardakht()+"' and '"+aghsatMoavagh.getTaTarikhePardakht()+"' and ";
//        }

        if (whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.AghsatMoavagh(" +
                                "(select max(s1.createdDate) from Sanad s1 join s1.khateSanadSet khs join khs.bedehiCredebit bc1 where bc1.id=c.id ) ,c.remainingAmount_long," +
                                "g.sarresidDate,c.amount_long,b.shomare,b.tarikhSodour,bgsh.name||' '||bgsh.nameKhanevadegi,b.tarikhShorou,b.tarikhEngheza,n.name,n.kodeNamayandeKargozar," +
                                "pe.modat_bimename,pe.nahve_pardakht_hagh_bime,bssh.name||' '||bssh.nameKhanevadegi," +
                                "(select max(c1.createdDate) from KhateSanad khe join khe.etebarCredebit c1 join khe.bedehiCredebit bc where bc.id=c.id )," +
                                "pgh.nameSherkat,no.cityName,ns.cityName," +
                                "nv.name,nv.kodeNamayandeKargozar,pb.firstName||' '||pb.lastName||' - '||pb.personalCode,p.noeGharardad,t.name,pe.sen_bime_shode, pn.name, pn.kodeNamayandeKargozar, p.options ,c.shomareMoshtari, abg.telephoneHamrah) " +
                                "from Ghest g join g.credebitList c join g.ghestBandi gb join gb.bimename b join b.pishnehadList p join p.addressBimeGozar abg join p.namayande n " +
                                "join p.bimeGozar bgozar join bgozar.shakhs bgsh join p.bimeShode bshode join bshode.shakhs bssh " +
                                "join p.estelam pe left join p.gharardad pgh join n.ostan no join n.shahr ns left join  p.karshenas pk join pk.mojtamaSodoor nv left join p.bazarYab pb " +
                                "left join p.tarh t left join p.namayandePoshtiban pn " +
                                "where gb.type=:ghestType and p.valid = true and ((select count(khsB.id) from c.khateSanadsBedehi khsB  )= 0 or (select max(s2.createdDate) from Sanad s2 join s2.khateSanadSet khs1 join khs1.bedehiCredebit bc2 where bc2.id=c.id )<= :tMabna ) " +
                                "and c.remainingAmount_long > :remAmount and g.sarresidDate <= :tMabna and b.readyToShow='yes' " +
                                whereClause +
                                "order by g.sarresidDate asc "
                ).setString("tMabna", aghsatMoavagh.getTarikhMabna())
                .setLong("remAmount", 0l)
                .setParameter("ghestType", GhestBandi.Type.G_BIMENAME);
        PaginatedListImpl<AghsatMoavagh> listPaginated = PagingUtil.getPaginatedList(query.list());


//        crit.addOrder(Order.asc("sarresidDate"));
//        PaginatedListImpl<Ghest> listPaginated = PagingUtil.getPaginatedList(query.list());
//        List<Ghest> list1=new ArrayList<Ghest>();
//        List<Ghest> nonPaginatedGhests = listPaginated.getList();
//
//        for (Ghest g : nonPaginatedGhests) {
//
//            final List<KhateSanad> khateSanads = g.getCredebit().getKhateSanadsBedehi();
//            if (khateSanads.size() > 0) {
//                Collections.sort(khateSanads);
//                if (DateUtil.isGreaterThanOrEqual(aghsatMoavagh.getTarikhMabna(), khateSanads.get(0).getSanad().getCreatedDate()))
//                    list1.add(g);
////            }
//            if(g.getCredebit().getRemainingAmount() != "0" && DateUtil.isGreaterThanOrEqual(aghsatMoavagh.getTarikhMabna(), g.getSarresidDate())){
//                if(aghsatMoavagh.getAzTarikhePardakht().isEmpty() && aghsatMoavagh.getTaTarikhePardakht().isEmpty()){
//                    list1.add(g);
//                }else{
//                    List<KhateSanad> khateSanadList = g.getCredebit().getKhateSanadsBedehi();
//                    if(khateSanadList != null && khateSanadList.size() > 0){
//                        KhateSanad khateSanad = Collections.max(khateSanadList);
//                        Credebit etebarCredebit = khateSanad.getEtebarCredebit();
//                        if (!aghsatMoavagh.getAzTarikhePardakht().isEmpty()) {
//                            if(DateUtil.isGreaterThanOrEqual(etebarCredebit.getCreatedDate(), aghsatMoavagh.getAzTarikhePardakht())){
//                                if(!aghsatMoavagh.getTaTarikhePardakht().isEmpty()){
//                                    if(DateUtil.isGreaterThanOrEqual(aghsatMoavagh.getTaTarikhePardakht(), etebarCredebit.getCreatedDate())){
//                                        list1.add(g);
//                                    }
//                                }else{
//                                    list1.add(g);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        listPaginated.setList(list1);
        return listPaginated;


    }

    public Long getHaghBimePayePardakhtiSaalAvval(Integer bimenameId) {
        Query query = getSession().createQuery(
                " select nvl(sum(c.amount_long),0) - nvl(sum(g.maliatLong),0) - nvl(sum(g.haghBimePoosheshhayeEzafiLong),0)" +
                        " from Pishnehad p " +
                        " join p.bimename b" +
                        " join b.ghestBandiList gb " +
                        " join gb.ghestList g " +
                        " join g.credebitList c " +
                        " where gb.saleBimei like '0' and p.valid = true and b.id = " + bimenameId);
        List<Long> list = query.list();
        if (list != null && list.size() > 0 && list.get(0) != null)
            return list.get(0);
        return 0l;
    }

    public Long getKoleHagheBimePardakhtiSaalAvval(Integer bimenameId) {
        Query query = getSession().createQuery(
                " select nvl(sum(c.amount_long),0) - nvl(sum(c.remainingAmount_long),0)" +
                        " from Pishnehad p " +
                        " join p.bimename b" +
                        " join b.ghestBandiList gb " +
                        " join gb.ghestList g " +
                        " join g.credebitList c " +
                        " where gb.saleBimei like '0' and p.valid = true and b.id = " + bimenameId);
        List<Long> list = query.list();
        if (list != null && list.size() > 0 && list.get(0) != null)
            return list.get(0);
        return 0l;
    }

    public List<Ghest> findAllGhestForBimenameAndKarmozd(Bimename bimename) {
        final Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Ghest.class, "g");
        criteria.createCriteria("g.ghestBandi", "gb", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("gb.bimename", "b", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("g.credebitList", "c", CriteriaSpecification.LEFT_JOIN);
        criteria.add
                (
                        Restrictions.and
                                (
                                        Restrictions.and
                                                (
                                                        Restrictions.eq("b.id", bimename.getId()),
                                                        Restrictions.ltProperty("c.remainingAmount_long", "c.amount_long")
                                                ),
                                        Restrictions.and
                                                (
                                                        Restrictions.isNull("g.karmozd"),
                                                        Restrictions.eq("c.credebitType", Credebit.CredebitType.GHEST)
                                                )
                                )
                );
        return criteria.list();
//        return (List<Ghest>) session.createQuery("select g from Ghest g left join fetch g.ghestBandi gb left join fetch gb.bimename b left join fetch g.credebit c where b.id=:id and c.remainingAmount_long=:zero and c.credebitType=:type and g.karmozd is null").setParameter("id", bimename.getId()).setParameter("zero", "0").setParameter("type", Credebit.CredebitType.GHEST).list();

    }

    public List<Ghest> findGhests(Integer ghestBandi, Credebit.CredebitType ghest) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "ghest").add(Restrictions.eq("ghest.ghestBandi.id", ghestBandi)).createCriteria("ghest.credebitList", "c").add(Restrictions.eq("c.credebitType", ghest)).addOrder(Order.asc("ghest.sarresidDate")).list();
    }

    public void deleteBedehisById(Integer ghestId) {
        Ghest gh = (Ghest) super.findById(Ghest.class, ghestId);
        super.delete(gh);
//        getSession().createQuery("delete from Ghest g where g.id=:id ").setParameter("id", ghestId).executeUpdate();
    }

    public Credebit getCredebitByShomareMoshtari(String shenase, String sub) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class)
                .add(Restrictions.eq("shomareMoshtari", shenase))
                .add(Restrictions.eq("subsystemName", sub));
        List<Credebit> list = (List<Credebit>) criteria.list();
//        int remaining = 0;
//        for(Credebit c : list) {
//            remaining += Integer.parseInt(c.getRemainingAmount().replaceAll(",",""));
//        }
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public Credebit getCredebitByShomareMoshtari(String codeMoshtari) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class)
                .add(Restrictions.eq("shomareMoshtari", codeMoshtari));
        List<Credebit> list = (List<Credebit>) criteria.list();
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public Credebit getCredebitByShenase(String shenase, String sub) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class)
                .add(Restrictions.eq("shenaseCredebit", shenase))
                .add(Restrictions.eq("subsystemName", sub));
        List<Credebit> list = (List<Credebit>) criteria.list();
//        int remaining = 0;
//        for(Credebit c : list) {
//            remaining += Integer.parseInt(c.getRemainingAmount().replaceAll(",",""));
//        }
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public int getRemainingByIdentifier(String identifer, String sub) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class)
                .add(Restrictions.eq("identifier", identifer))
                .add(Restrictions.eq("subsystemName", sub));
        List<Credebit> list = (List<Credebit>) criteria.list();
        int remaining = 0;
        for (Credebit c : list) {
            remaining += Integer.parseInt(c.getRemainingAmount().replaceAll(",", ""));
        }
        return remaining;
    }

    public Credebit getCredebitByUniqueCode(String uniqueCode, String sub) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class)
                .add(Restrictions.eq("uniqueCode", uniqueCode))
                .add(Restrictions.eq("subsystemName", sub));
        List<Credebit> list = (List<Credebit>) criteria.list();
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }


    public long getJameSadere(Integer bimenameId) {
        long jamesadere = 0;
        final Session session = getSession();
        Long obj = (Long) session.createQuery("select sum(c.amount_long) from Ghest g join g.credebitList c join c.bimename b where b.id=:id ").setParameter("id", bimenameId).list().get(0);
        if (obj != null) {
            jamesadere += obj;
        }
        return jamesadere;
    }

    public long getJameSadereForGhestbandi(Integer ghestBandiId) {
        long jamesadere = 0;
        final Session session = getSession();
        Long obj = (Long) session.createQuery("select sum(c.amount_long) from Ghest g join g.credebitList c join g.ghestBandi gb where gb.id=:id ").setParameter("id", ghestBandiId).list().get(0);
        if (obj != null) {
            jamesadere += obj;
        }
        return jamesadere;
    }

    public long getJameSadereForGhestbandiKasrMaliatEzafi(int bimenameId) {
        long jamesadere = 0;
        final Session session = getSession();
//        Long obj = (Long) session.createQuery("select sum(c.amount_long)-sum(g.maliatLong)-sum(g.haghBimePoosheshhayeEzafiLong) from Ghest g join g.credebitList c join g.ghestBandi gb join c.bimename b where b.id=:id and gb.saleBimei=0").setParameter("id", bimenameId).list().get(0);
        Long obj = (Long) session.createQuery("select gb.majmooeAmount - gb.majmooeMaliat - gb.majmooeEzafi from GhestBandi gb join gb.bimename b where b.id=:id and gb.saleBimei=0").setParameter("id", bimenameId).list().get(0);
//        Long obj = (Long) session.createQuery("select 1 from dual").list().get(0);
        if (obj != null) {
            jamesadere += obj;
        }
        return jamesadere;
    }

    public boolean searchKarmozdNamayanade_HasBedehi(Long karmozdId, String state, String namayandeName, Long namayandeId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KarmozdNamayande.class, "kn").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (state != null && !state.isEmpty()) {
            criteria.add(Restrictions.eq("kn.state", KarmozdNamayande.State.valueOf(state)));
        }

        if (karmozdId != null) {
            criteria.createCriteria("kn.karmozd", "k").add(Restrictions.eq("k.id", karmozdId));
        }

        if (namayandeName != null && !namayandeName.isEmpty()) {
            criteria.createCriteria("kn.namayande", "n").add(Restrictions.eq("n.id", namayandeId));
        }

        criteria.createCriteria("kn.credebit", "c")
                .add(Restrictions.eq("c.credebitType", Credebit.CredebitType.BARGASHT_KARMOZD))
                .add(Restrictions.gt("c.remainingAmount_long", 0L));

        criteria.setProjection(Projections.count("kn.id"));
        Long count = (Long) criteria.uniqueResult();
        return (count > 0);
    }

    public long getJamePoosheshAsliSadere(Integer bimenameId) {
        long jamesadere = 0;
        final Session session = getSession();
        Long obj = (Long) session.createQuery("select sum(g.haghBimeFot_long) from Ghest g join g.credebitList c join c.bimename b where b.id=:id ").setParameter("id", bimenameId).list().get(0);
        if (obj != null) {
            jamesadere += obj;
        }
        return jamesadere;
    }

    public long getJamePoosheshhayeEzafiSadere(Integer bimenameId) {
        long jamesadere = 0;
        final Session session = getSession();
        Long obj = (Long) session.createQuery("select sum(gb.haghBimePoosheshNaghsOzv_long + gb.haghBimePoosheshAmraz_long + gb.haghBimePoosheshMoafiat_long + gb.haghBimePoosheshHadese_long) from GhestBandi gb join gb.bimename b where b.id=:id ").setParameter("id", bimenameId).list().get(0);
        if (obj != null) {
            jamesadere += obj;
        }
        return jamesadere;
    }


    public long getHagheBimeElamBeMaliSaleAvval(Integer bimenameId) {
        long value = 0;
        final Session session = getSession();
        Long obj = (Long) session.createQuery("select sum(c.amount_long) from Ghest g join g.credebitList c join c.bimename b where b.id=:id and g.ghestBandi.saleBimei='0'").setParameter("id", bimenameId).list().get(0);
        if (obj != null) {
            value += obj.longValue();
        }
        return value;
    }

    public PaginatedListImpl shakhesRanking(boolean faskh, RankReport rankReport, PaginatedListImpl pgList) {
        Query query = getSession().createQuery("select new com.bitarts.parsian.viewModel.RankReport('TEDAD_BIMENAME', count(b.id), n.id) " +
                "from Pishnehad p join p.bimename b join p.namayande n " +
                "where b.readyToShow = :readyParam and b.tarikhSodour >= :azTarikh and b.tarikhSodour <= :taTarikh and b.state.id != :constant group by n.id")
                .setString("readyParam", "yes")
                .setInteger("constant", Constant.BIMENAME_FASKH)
                .setString("azTarikh", rankReport.getAzTarikh())
                .setString("taTarikh", rankReport.getTaTarikh());
        return null;
    }

    public CashFlow getCashFlowForBimename(Bimename bimename, String date) {
        // todo: inja ro saree kon
        return bimename.getCashFlow(date);
    }

    public void updateBimename(Bimename bimename) {
        super.saveOrUpdate(bimename);
    }

    public List<Credebit> findCredebitForTaeedFish(Fish fish) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class, "c").createCriteria("c.daryafteFish", "df")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("c.status", Credebit.Status.SANAD_NA_KHORDE))
                .add(Restrictions.eq("c.credebitType", Credebit.CredebitType.PISHPARDAKHT))
                .add(Restrictions.eq("c.amount", fish.getMablagh()))
                .add(Restrictions.eq("df.shomareFish", fish.getShomare()))
                .add(Restrictions.eq("df.bank", fish.getBankName()))
                .add(Restrictions.eq("df.kodeShobe", fish.getKodeShobe()))
                .add(Restrictions.eq("df.tarikh", fish.getTarikh()));
        return (List<Credebit>) criteria.list();
    }

    @Transactional
    public String updateArzesheBimename(String date, double soud, Integer bimenameId) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call UPDATE_ARZESHE_BIMENAME(?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setString(2, date);
            st.setDouble(3, soud);
            st.setInt(4, bimenameId);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    @Transactional
    public String updateArzesheBimenameTajmi(String date, double soud, List<Integer> bimenameIds) {
        try {
            String callFunc = "{? = call UPDATE_ARZESHE_BIMENAME_TAJMI(?, ?, num_array(";
            List<String> parameters = new ArrayList<String>();
            for (Integer zr : bimenameIds)
                parameters.add("?");
            String parametersStr = StringUtil.join(parameters, ",");
            callFunc += parametersStr + "))}";
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setString(2, date);
            st.setDouble(3, soud);
            int i = 4;
            for (Integer zr : bimenameIds)
                st.setInt(i++, zr);
            st.execute();
            return st.getString(1);
//            for(int i : bimenameIds) {
//                String callFunc = "{? = call UPDATE_ARZESHE_BIMENAME(?, ?, ?)}";
//                CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
//                st.registerOutParameter(1, OracleTypes.VARCHAR);
//                st.setString(2, date);
//                st.setDouble(3, soud);
//                st.setInt(4, i);
//                st.execute();
//            }
//            return "";

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    @Transactional
    public String updateMohasebeArzeshBazkharidi(String date, Integer bimenameId) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call MOHASEBE_ARZESH_BAZKHARIDI(?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setString(2, date);
            st.setString(3, DateUtil.getCurrentDate());
            st.setInt(4, bimenameId);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    @Transactional
    public String updateMohasebeArzeshBazkharidiTajmi(String date, List<Integer> bimenameIds) {
        try {
            String callFunc = "{? = call MOHASB_ARZSH_BAZKHRIDI_TAJMI(?, ?, num_array(";
            List<String> parameters = new ArrayList<String>();
            for (Integer bimenameId : bimenameIds)
                parameters.add("?");
            String parametersStr = StringUtil.join(parameters, ",");
            callFunc += parametersStr + "))}";
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setString(2, date);
            st.setString(3, DateUtil.getCurrentDate());
            int i = 4;
            for (Integer bimenameId : bimenameIds)
                st.setInt(i++, bimenameId);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    @Transactional
    public String updateAndukhte(String date, double soud, Integer bimenameId) throws SQLException {
        String callFunc = "{? = call UPDATE_ANDUKHTE_TAJMI(?, ?, ?, ?)}";
        CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
        st.registerOutParameter(1, OracleTypes.VARCHAR);
        st.setString(2, date);
        st.setString(3, DateUtil.getCurrentDate());
        st.setDouble(4, soud);
        st.setInt(5, bimenameId);
        st.execute();
        if (st.getString(1).toLowerCase().equals("failed"))
            throw new SQLException();
        return st.getString(1);
    }

    @Transactional
    public String updateAndukhteTajmi(String date, List<ZakhireRiaziVM> bimenameIds) throws SQLException {
        Query query = getSession().createQuery("delete from InternalAndukhteQueue");
        query.executeUpdate();
        for (ZakhireRiaziVM id : bimenameIds) {
            InternalAndukhteQueue queueItem = new InternalAndukhteQueue();
            queueItem.setBimenameId(id.getBimenameId());
            save(queueItem);
        }
        getSession().flush();
        String callFunc = "{? = call UPDATE_ANDUKHTE_TAJMI(?, ?)}";
        CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
        st.registerOutParameter(1, OracleTypes.VARCHAR);
        st.setString(2, date);
        st.setString(3, DateUtil.getCurrentDate());
        st.execute();
        if (st.getString(1).toLowerCase().equals("failed"))
            throw new SQLException();
        return st.getString(1);
    }

    @Transactional
    public String updateAndukhteTajmi(String date1, String date2, double soud, List<Integer> bimenameIds) throws SQLException {
        String callFunc = "{? = call UPDATE_ANDUKHTE_TAJMI2(?, ?, ?, ?, num_array(";
        List<String> parameters = new ArrayList<String>();
        for (Integer bimenameId : bimenameIds)
            parameters.add("?");
        String parametersStr = StringUtil.join(parameters, ",");
        callFunc += parametersStr + "))}";
        CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall(callFunc);
        st.registerOutParameter(1, OracleTypes.VARCHAR);
        st.setString(2, date1);
        st.setString(3, date2);
        st.setString(4, DateUtil.getCurrentDate());
        st.setDouble(5, soud);
        int i = 6;
        for (Integer bimenameId : bimenameIds)
            st.setInt(i++, bimenameId);
        st.execute();
        if (st.getString(1).toLowerCase().equals("failed"))
            throw new SQLException();
        return st.getString(1);
    }

    @Transactional
    public List<Ghest> findGhestByRemainingAndSareresid() {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "g").createCriteria("g.credebitList", "gc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        String tenDays = DateUtil.addDays(DateUtil.getCurrentDate(), 10);
        criteria.add(Restrictions.eq("g.sarresidDate", tenDays));
        criteria.add(Restrictions.gt("gc.remainingAmount_long", 0l));

//        List<Ghest> ghestByRemainingAndSareresid = (List<Ghest>) criteria.list();

//        for (Ghest ghest : ghestByRemainingAndSareresid){
//            if (ghest.getGhestBandi() != null && ghest.getGhestBandi().getBimename() != null && ghest.getGhestBandi().getBimename().getPishnehad() != null &&
//                    ghest.getGhestBandi().getBimename().getPishnehad().getAddressBimeGozar() != null){
//                System.out.println("----------------" + ghest.getGhestBandi().getBimename().getPishnehad().getAddressBimeGozar().getTelephoneHamrah());
//            }
//
//        }


        return (List<Ghest>) criteria.list();
    }

    @Transactional
    public List<Bimename> findBimenameByGhest(List<Ghest> ghestList) {
        List<Bimename> bimenames = new ArrayList<Bimename>();
        for (Ghest ghest : ghestList)
            if (ghest.getGhestBandi() != null && ghest.getGhestBandi().getBimename() != null)
                bimenames.add(ghest.getGhestBandi().getBimename());
        return bimenames;
    }

    @Transactional
    public Shakhs findShakhsByBimename(Bimename bimename) {
        if (bimename != null && bimename.getPishnehad() != null && bimename.getPishnehad().getBimeGozar() != null)
            return (bimename.getPishnehad().getBimeGozar().getShakhs());
        return null;
    }

    public Map<Integer, List<String>> findInformationGhest10DayBeforeForSMS() {
        //key = ghest Id
        //List = 1.Name , 2.NameKhanevadegi , 3.Telephone Hamrah , 4.PolicyId (shomare Bimename)
        Map<Integer, List<String>> dictionary = new Hashtable<Integer, List<String>>();

        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "g").createCriteria("g.credebitList", "gc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        String tenDays = DateUtil.addDays(DateUtil.getCurrentDate(), 10);
        criteria.add(Restrictions.eq("g.sarresidDate", tenDays));
        criteria.add(Restrictions.gt("gc.remainingAmount_long", 0l));

        List<Ghest> ghestByRemainingAndSareresid = (List<Ghest>) criteria.list();
        for (Ghest ghest : ghestByRemainingAndSareresid) {
            List<String> information = new ArrayList<String>();

            GhestBandi ghestBandi = ghest.getGhestBandi();
            if (ghestBandi != null) {
                Bimename bimename = ghestBandi.getBimename();
                if (bimename != null) {
                    Pishnehad pishnehad = bimename.getPishnehad();
                    if (pishnehad != null && pishnehad.getValid().equals(true) && pishnehad.getGharardad() == null) {
                        if (pishnehad.getBimeGozar() != null) {
                            Shakhs shakhs = pishnehad.getBimeGozar().getShakhs();
                            information.add(shakhs.getName());
                            information.add(shakhs.getNameKhanevadegi());
                        }

                        if (pishnehad.getAddressBimeGozar() != null)
                            information.add(pishnehad.getAddressBimeGozar().getTelephoneHamrah());
                    }
                }
            }

            if (ghest.getGhestBandi() != null && ghest.getGhestBandi().getBimename() != null) {
                information.add(ghest.getGhestBandi().getBimename().getShomare());
            }

            if (information.size() == 4)
                dictionary.put(ghest.getId(), information);
            else
                System.out.println("Bad Data For Send SMS");
        }

        return dictionary;
    }

    public Map<Integer, List<String>> findInformationGhest20DayAfterForSMS() {
        //key = ghest Id
        //List = 1.Name , 2.NameKhanevadegi , 3.Telephone Hamrah , 4.PolicyId (shomare Bimename)
        Map<Integer, List<String>> dictionary = new Hashtable<Integer, List<String>>();

        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Ghest.class, "g").createCriteria("g.credebitList", "gc").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        String twentyDays = DateUtil.minusDays(DateUtil.getCurrentDate(), 20);
        criteria.add(Restrictions.eq("g.sarresidDate", twentyDays));
        criteria.add(Restrictions.gt("gc.remainingAmount_long", 0l));

        List<Ghest> ghestByRemainingAndSareresid = (List<Ghest>) criteria.list();
        for (Ghest ghest : ghestByRemainingAndSareresid) {
            List<String> information = new ArrayList<String>();

            GhestBandi ghestBandi = ghest.getGhestBandi();
            if (ghestBandi != null) {
                Bimename bimename = ghestBandi.getBimename();
                if (bimename != null) {
                    Pishnehad pishnehad = bimename.getPishnehad();
                    if (pishnehad != null && pishnehad.getValid().equals(true) && pishnehad.getGharardad() == null) {
                        if (pishnehad.getBimeGozar() != null) {
                            Shakhs shakhs = pishnehad.getBimeGozar().getShakhs();
                            information.add(shakhs.getName());
                            information.add(shakhs.getNameKhanevadegi());
                        }

                        if (pishnehad.getAddressBimeGozar() != null)
                            information.add(pishnehad.getAddressBimeGozar().getTelephoneHamrah());
                    }
                }
            }

            if (ghest.getGhestBandi() != null && ghest.getGhestBandi().getBimename() != null) {
                information.add(ghest.getGhestBandi().getBimename().getShomare());
            }

            if (information.size() == 4)
                dictionary.put(ghest.getId(), information);
            else
                System.out.println("Bad Data For Send SMS");
        }

        return dictionary;
    }

    public Long getRemainingAmountBedehiSanadNakhorde(Long namayandeId) {
        Long sumBedehiSanadNakhorde = 0l;
        Integer mohlateSanadZadan = -4;


        Namayande namayandeLoaded = (Namayande) super.findById(Namayande.class, namayandeId);

        if (namayandeLoaded != null) {
            if (namayandeLoaded.getMohlateSanadzadan() != null && namayandeLoaded.getMohlateSanadzadan() > 0)
                mohlateSanadZadan = -namayandeLoaded.getMohlateSanadzadan();
            else if (namayandeLoaded.getMohlateSanadzadan() != null && namayandeLoaded.getMohlateSanadzadan() <= 0)
                mohlateSanadZadan = namayandeLoaded.getMohlateSanadzadan();
        }

        final Session session = getSession();
        String hql = "select nvl(sum(c.remainingAmount_long),0) from Credebit c left join c.khateSanadsBedehi k left join k.sanad s " +
                " where c.sarresidDateWithMohlatSarresid <= '" + DateUtil.addDaysWithTatilat(DateUtil.getCurrentDate(), mohlateSanadZadan) + "' " +
                " and ((c.remainingAmount_long > 0) or (s.vaziat like 'MOVAGHAT' AND c.credebitType<>'"+Credebit.CredebitType.ACH+"')) and (c.identifier not like '1119%' and c.identifier not like '1129%') and (";

        for (Credebit.CredebitType bed : Credebit.bedehiTypes) {
            hql += " c.credebitType like '" + bed + "' or ";
        }
        Long obj = (Long) session.createQuery(hql + " 1=2) and c.vahedeSodor is not null and c.vahedeSodor.id=:id and c.daftar.id=1  and c.subsystemName <> 'SIMAB'").setParameter("id", namayandeId).list().get(0);
        if (obj != null) {
            sumBedehiSanadNakhorde += obj;
        }
//        System.out.println(hqlToSql(hql+ " 1=2)"));
        return sumBedehiSanadNakhorde;
    }

    public Long getRemainingAmountEtebarSanadKhordeVosulNashode(Long namayandeId, String etebarType) {
        Long sumBedehiSanadNakhorde = 0l;
        final Session session = getSession();
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(prop.getProperty("WebService.NamayandehAuthorized.MohlateSarresid"));
        String typeString = "";
        String mohlateSarResidBedehi = "";
        if (etebarType.equals("CHECK")) {
            mohlateSarResidBedehi = prop.getProperty("WebService.NamayandehAuthorized.MohlateSarresidCheck");
            typeString = "DARYAFTE_CHECK";
        } else if (etebarType.equals("CASH")) {
            mohlateSarResidBedehi = prop.getProperty("WebService.NamayandehAuthorized.MohlateSarresid");
            typeString = "PARDAKHT_SHENASEDAR";
        }

        int mohlateSarResidBedehiInt = Integer.parseInt(mohlateSarResidBedehi.trim());
        String hql = "select sum(cc.amount_long) from Credebit cc where (cc.vosoulState is null or cc.vosoulState like 'TAEED_NASHODE') and cc.credebitType like '" + typeString + "' and cc.id in " +
                " (select k.etebarCredebit.id from KhateSanad k where k.bedehiCredebit.id in (select c.id from Credebit      c " +
                " where c.sarresidDateWithMohlatSarresid <= '" + DateUtil.addDaysWithTatilat(DateUtil.getCurrentDate(), -mohlateSarResidBedehiInt) + "' " +
                "  and (";

        for (Credebit.CredebitType bed : Credebit.bedehiTypes) {
            hql += " c.credebitType like '" + bed + "' or ";
        }

        String query = (String)hqlToSql(hql + " 1=2)  and c.namayande is not null) )");//.setParameter("id", namayandeId).list().get(0);
//        System.out.println("query etebar sanad khorde vosoul nashode");
//        System.out.println(query);
//        System.out.println("hql :"+hql.toString());

        Long obj = (Long) session.createQuery(hql + " 1=2)  and c.namayande is not null and c.namayande.id=:id and c.daftar.id=1 and c.subsystemName <> 'SIMAB') )").setParameter("id", namayandeId).list().get(0);

        if (obj != null) {
            sumBedehiSanadNakhorde += obj;
        }
        return sumBedehiSanadNakhorde;
    }

    public Long getAmountCheckSanadKhordeVagozarNashode(Long namayandeId) {

        Long checkVagozarNashode = 0l;
        final Session session = getSession();
        String hql = "select sum(cc.amount_long) from Credebit cc join cc.daryafteCheck dc" +
                " where cc.credebitType  = 'DARYAFTE_CHECK' and cc.remainingAmount_long != cc.amount_long " +
                " and cc.createdDate < '" + DateUtil.getCurrentDate() + "' and dc.status = 'NAZD_SANDOGH' " +
                " and cc.namayande is not null and cc.namayande.id= '" + namayandeId + "' and cc.daftar.id=1  and cc.subsystemName <> 'SIMAB'";

        Long obj = (Long) session.createQuery(hql).list().get(0);

        if (obj != null) {
            checkVagozarNashode = obj;
        }
        return checkVagozarNashode;
    }

    public PaginatedListImpl<Credebit> findAllCheck(String shomareHesab, String shomareCheck, String sarresidDate, String checkSerri, String rcptName, String branchName, String branchCode, String accountOwnerName, String amount, User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        criteria.setProjection(Projections.id());

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.isNotNull("c.daryafteCheck"));

        criteria.add(disjunction);

        criteria.createCriteria("c.daryafteCheck", "d");

        criteria.add(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.DARYAFTE_CHECK));
        //criteria.add(Restrictions.like("d.status",DaryafteCheck.Status.NAZD_SANDOGH));

        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("c.daftar.id",daftar_id),Restrictions.isNull("c.daftar.id")));
//        else
            criteria.add(Restrictions.eq("c.daftar.id",daftar_id));
        ///// ta inja
        if (shomareHesab != null && !shomareHesab.isEmpty())
            criteria.add(Restrictions.eq("d.hesabBanki", shomareHesab));
        if (shomareCheck != null && !shomareCheck.isEmpty())
            criteria.add(Restrictions.eq("d.serial", shomareCheck));
        if (sarresidDate != null && !sarresidDate.isEmpty())
            criteria.add(Restrictions.eq("c.sarresidDate", sarresidDate));
        if (checkSerri != null && !checkSerri.isEmpty())
            criteria.add(Restrictions.eq("d.seri", checkSerri));
        if (rcptName != null && !rcptName.isEmpty())
            criteria.add(Restrictions.eq("c.rcptName", rcptName));
        if (branchName != null && !branchName.isEmpty())
            criteria.add(Restrictions.like("d.branchName", branchName));
        if (branchCode != null && !branchCode.isEmpty())
            criteria.add(Restrictions.eq("d.branchCode", branchCode));
        if (accountOwnerName != null && !accountOwnerName.isEmpty())
            criteria.add(Restrictions.eq("d.accountOwnerName", accountOwnerName));
        if (amount != null && !amount.isEmpty())
            criteria.add(Restrictions.eq("c.amount_long", Long.parseLong(amount.replaceAll(",", ""))));

//        criteria.add(Restrictions.eq("c.remainingAmount_long", 0l));

        if (user != null) {
            if (userHasRole(user, "ROLE_KARBAR_MALI") && userHasRole(user, "ROLE_NAMAYANDE")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.eq("c.namayande.id", user.getNamayandegi().getId())));
                }
            }
        }

        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
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

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.shenaseCredebit"));
        nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());
        return paginatedList;
    }

    public PaginatedListImpl<Vagozari> findListVaghozariCredebitPaginated(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Vagozari.class, "v");
        criteria.setProjection(Projections.id());

        if (user != null) {
            if (userHasRole(user, "ROLE_KARBAR_MALI") && userHasRole(user, "ROLE_NAMAYANDE")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("v.namayande"), Restrictions.eq("v.namayande.id", user.getNamayandegi().getId())));
                }
            }
        }

        //b-h
        Integer daftar_id=user.getDaftar().getId();
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("v.daftar.id",daftar_id),Restrictions.isNull("v.daftar.id")));
//        else
            criteria.add(Restrictions.eq("v.daftar.id",daftar_id));
        ///// ta inja
        PaginatedListImpl<Vagozari> paginatedList = new PaginatedListImpl<Vagozari>();
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

        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Vagozari.class, "v");
        count.add(Subqueries.propertyIn("v.id", criteria));
        int countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Vagozari.class, "v");
        nonDetachedCriteria.add(Subqueries.propertyIn("v.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("v.id"));
        nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());
        return paginatedList;
    }

    public Integer getVagozariPerCredebit(Integer vagozariId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        criteria.add(Restrictions.and(Restrictions.isNotNull("c.vagozari"), Restrictions.eq("c.vagozari.id", vagozariId)));
        int vagozariPerCredebit = Integer.parseInt(criteria.setProjection(Projections.rowCount()).list().get(0).toString());
        return vagozariPerCredebit;
    }

    public List<Credebit> findCheckByVagozari(Integer vagozariId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c");
        criteria.add(Restrictions.and(Restrictions.isNotNull("c.vagozari"), Restrictions.eq("c.vagozari.id", vagozariId)));
        return criteria.list();
    }

    public Vagozari findVagozari(Integer vagozariId) {
        return (Vagozari) super.findById(Vagozari.class, vagozariId);
    }

    public Integer countAghsatePardakhti(Integer bimenameId) {
        Long rtnVal = (Long) getSession().createQuery("select count(g.id) from Credebit c join c.ghest g where c.credebitType=:type and c.remainingAmount_long=0 and c.bimename.id=:id").setParameter("type", Credebit.CredebitType.GHEST).setParameter("id", bimenameId).uniqueResult();
        return rtnVal.intValue();
    }

    public PaginatedListImpl<BatchTaghsitVM> findBatchTaghsit(PaginatedListImpl<BatchTaghsitVM> pgList, BatchTaghsitVM searchObj) {
        String whereClause = "";
        if (searchObj != null) {
            if (searchObj.getFromTarikheTaghsit() != null && !searchObj.getFromTarikheTaghsit().isEmpty()) {
                whereClause += "gb.tarikheTaghsit>='" + searchObj.getFromTarikheTaghsit() + "' and ";
            }

            if (searchObj.getToTarikheTaghsit() != null && !searchObj.getToTarikheTaghsit().isEmpty()) {
                whereClause += "gb.tarikheTaghsit<='" + searchObj.getToTarikheTaghsit() + "' and ";
            }

            if (searchObj.getCreatorId() != null) {
//                if(searchObj.getCreatorId().equals(0l))
//                {
//                    whereClause += "gb.creator is null and ";
//                }
//                else
//                {
//                    whereClause += "gb.creator.id=" + searchObj.getCreatorId() + " and ";
//                }
            }

            if (searchObj.getNamayandeId() != null) {
                whereClause += "n.id=" + searchObj.getNamayandeId() + " and ";
            }

            if (searchObj.getVahedSodurId() != null) {
                whereClause += "k.getMojtamaSodoor.id=" + searchObj.getVahedSodurId() + " and ";
            }

            if (searchObj.getFromTarikhShoroNewYear() != null && !searchObj.getFromTarikhShoroNewYear().isEmpty()) {
                whereClause += "replace(b.tarikhShorou,substr(b.tarikhShorou,1,4)," + Integer.toString(Integer.parseInt(searchObj.getFromTarikhShoroNewYear().substring(0, 4))) + ")>='" + searchObj.getFromTarikhShoroNewYear() + "' and ";
            }

            if (searchObj.getShomareBimename() != null && !searchObj.getShomareBimename().isEmpty()) {
                whereClause += "b.shomare like '%" + searchObj.getShomareBimename() + "%' and ";
            }

            if (searchObj.getKodeMelliBimeGozar() != null && !searchObj.getKodeMelliBimeGozar().isEmpty()) {
                whereClause += "bgs.kodeMelliShenasayi = '" + searchObj.getKodeMelliBimeGozar() + "' and ";
            }

            if (searchObj.getToTarikhShoroNewYear() != null && !searchObj.getToTarikhShoroNewYear().isEmpty()) {
                whereClause += "replace(b.tarikhShorou,substr(b.tarikhShorou,1,4)," + Integer.toString(Integer.parseInt(searchObj.getToTarikhShoroNewYear().substring(0, 4))) + ")<='" + searchObj.getToTarikhShoroNewYear() + "' and ";
            }

            if (searchObj.getSaleBimeei() != null && !searchObj.getSaleBimeei().isEmpty()) {
                whereClause += "gb.saleBimei='" + searchObj.getSaleBimeei() + "' and ";
            }

            if (searchObj.getNoe_tarh() != null && !searchObj.getNoe_tarh().isEmpty()) {
                whereClause += " e.noe_tarh='" + searchObj.getNoe_tarh() + "' and ";
            }

            if (searchObj.getNoe_gharardad() != null && !searchObj.getNoe_gharardad().isEmpty()) {
                whereClause += " p.noeGharardad='" + searchObj.getNoe_gharardad() + "' and ";
            }

            if (searchObj.getGorohId() != null) {
                whereClause += " p.gharardad.id=" + searchObj.getGorohId() + " and ";
            }

            if (searchObj.getNahvePardakht() != null && !searchObj.getNahvePardakht().isEmpty()) {
                whereClause += "e.nahve_pardakht_hagh_bime='" + searchObj.getNahvePardakht() + "' and ";
            }

            if (searchObj.getKarshenasId() != null && searchObj.getKarshenasId() != 0) {
                whereClause += "k.id=" + searchObj.getKarshenasId() + " and ";
            }


//            Other whereClause place here . . .

            if (whereClause.length() > 0) {
                whereClause = "and " + whereClause;
                whereClause = whereClause.substring(0, whereClause.length() - 5);
            }
        }

        Query count = getSession().createQuery
                (
                        "select count(gb.id) from GhestBandi gb join gb.bimename b join b.pishnehadList p join p.bimeGozar bg join bg.shakhs bgs " +
                                "join p.bimeShode bs join bs.shakhs bscs join p.estelam e join p.namayande n " +
                                "where p.valid=true and gb.type=:tp " + whereClause
                ).setParameter("tp", GhestBandi.Type.G_BIMENAME);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.BatchTaghsitVM(b.shomare, bgs.name, bgs.nameKhanevadegi, bss.name, bss.nameKhanevadegi, b.tarikhShorou, b.tarikhEngheza, " +
                                "b.tarikhSodour, p.noeGharardad, e.nahve_pardakht_hagh_bime, gb.saleBimei, '', '', gb.majmooeAmount, n.name, n.kodeNamayandeKargozar, e.modat_bimename, gb.id, b.id, " +
                                "gb.hasPrint,k.firstName,k.lastName,k.id, gb.tarikheTaghsit, gbc.firstName, gbc.lastName) " +
                                "from GhestBandi gb join gb.bimename b join b.pishnehadList p join p.bimeGozar bg join bg.shakhs bgs " +
                                "join p.bimeShode bs join p.karshenas k join bs.shakhs bss join p.estelam e join p.namayande n left join gb.creator gbc " +
                                "where p.valid=true and gb.type=:tp and b.readyToShow='yes' " + whereClause
                ).setParameter("tp", GhestBandi.Type.G_BIMENAME);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        return pgList;
    }

    public List<GhestBandi> findGhestBandiListForPrintDaftarche(BatchTaghsitVM searchObj) {
        String clauseGroupBy = "";
        String whereClause = "";
        if (searchObj.getNahvePardakht() != null) {
            if (searchObj.getNahvePardakht().equals("mah")) {
                whereClause += "e.nahve_pardakht_hagh_bime ='mah' and ";
//                clauseGroupBy= "group by gb.id having count(c.id) <= 2";
            } else if (searchObj.getNahvePardakht().equals("3mah")) {
                whereClause += "e.nahve_pardakht_hagh_bime ='3mah' and ";
//                clauseGroupBy= "group by gb.id having count(c.id) <= 1";
            } else if (searchObj.getNahvePardakht().equals("6mah") || searchObj.getNahvePardakht().equals("sal")) {
                whereClause += "e.nahve_pardakht_hagh_bime ='" + searchObj.getNahvePardakht() + "' and ";
//                clauseGroupBy= "group by gb.id having count(c.id) = 0";
            } else if (searchObj.getNahvePardakht().equals("6mah&sal")) {
                whereClause += "(e.nahve_pardakht_hagh_bime ='6mah' or e.nahve_pardakht_hagh_bime ='sal') and ";
//                clauseGroupBy= "group by gb.id having count(c.id) = 0";
            } else {
                return null;
            }
        }


        if (searchObj.getFromTarikheTaghsit() != null && !searchObj.getFromTarikheTaghsit().isEmpty()) {
            whereClause += "gb.tarikheTaghsit>='" + searchObj.getFromTarikheTaghsit() + "' and ";
        }

        if (searchObj.getToTarikheTaghsit() != null && !searchObj.getToTarikheTaghsit().isEmpty()) {
            whereClause += "gb.tarikheTaghsit<='" + searchObj.getToTarikheTaghsit() + "' and ";
        }


        if (searchObj.getNamayandeId() != null) {
            whereClause += "n.id=" + searchObj.getNamayandeId() + " and ";
        }

        if (searchObj.getVahedSodurId() != null) {
            whereClause += "p.karshenas.getMojtamaSodoor.id=" + searchObj.getVahedSodurId() + " and ";
        }

        if (searchObj.getFromTarikhShoroNewYear() != null && !searchObj.getFromTarikhShoroNewYear().isEmpty()) {
            whereClause += "replace(b.tarikhShorou,substr(b.tarikhShorou,1,4)," + Integer.toString(Integer.parseInt(searchObj.getFromTarikhShoroNewYear().substring(0, 4))) + ")>='" + searchObj.getFromTarikhShoroNewYear() + "' and ";
        }

        if (searchObj.getToTarikhShoroNewYear() != null && !searchObj.getToTarikhShoroNewYear().isEmpty()) {
            whereClause += "replace(b.tarikhShorou,substr(b.tarikhShorou,1,4)," + Integer.toString(Integer.parseInt(searchObj.getToTarikhShoroNewYear().substring(0, 4))) + ")<='" + searchObj.getToTarikhShoroNewYear() + "' and ";
        }

        if (searchObj.getSaleBimeei() != null && !searchObj.getSaleBimeei().isEmpty()) {
            whereClause += "gb.saleBimei='" + searchObj.getSaleBimeei() + "' and ";
        }

        if (searchObj.getNoe_tarh() != null && !searchObj.getNoe_tarh().isEmpty()) {
            whereClause += " e.noe_tarh='" + searchObj.getNoe_tarh() + "' and ";
        }

        if (searchObj.getNoe_gharardad() != null && !searchObj.getNoe_gharardad().isEmpty()) {
            whereClause += " p.noeGharardad='" + searchObj.getNoe_gharardad() + "' and ";
        }

        if (searchObj.getGorohId() != null) {
            whereClause += " p.gharardad.id=" + searchObj.getGorohId() + " and ";
        }

        if (searchObj.getKarshenasId() != null) {
            whereClause += "p.karshenas.id=" + searchObj.getKarshenasId() + " and ";
        }


//            Other whereClause place here . . .

        if (whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query query = getSession().createQuery
                (
                        "select gb.id from Pishnehad p join p.estelam e join p.namayande n join p.bimename b join b.credebitList c join c.ghest g join g.ghestBandi gb1 join b.ghestBandiList gb " +
                                "where p.valid = true and gb1.type=:tp and gb1.tarikheTaghsit <:fromTaghsitDate and gb1.tarikheTaghsit >:toTaghsitDate and c.remainingAmount_long > 0 and gb.saleBimei is not null and gb.type=:tp " + whereClause + clauseGroupBy
                )
                .setString("fromTaghsitDate", searchObj.getFromTarikheTaghsit())
                .setString("toTaghsitDate", searchObj.getToTarikheTaghsit())
                .setParameter("tp", GhestBandi.Type.G_BIMENAME);

        return query.list();
    }

    public List<Credebit> credebitsShenaseDarVosulNashode(Long namayandeId, Credebit.VaziyatVosoul vosolState)
    {
        Query query = getSession().createQuery("select c from Credebit c join c.namayande n  where c.credebitType=:cType and c.vosoulState=:vState and n.id=:nId ")
                     .setParameter("cType",Credebit.CredebitType.PARDAKHT_SHENASEDAR)
                     .setParameter("vState",vosolState)
                     .setLong("nId", namayandeId);
        return (List<Credebit>) query.list();
    }

    public boolean validationDeleteEtebar(Integer credebitId, User user) {

        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("c.id", credebitId));

        List<Credebit> credebitList = criteria.list();
        if (credebitList != null && credebitList.size() > 0) {
//            if (!credebitList.get(0).getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
//                return false;
            if (credebitList.get(0).getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR) && credebitList.get(0).getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)){
                System.out.println("etebar taeed vosoul shode!");
                return false; //nemitavanad hazf shavad
//            if (credebitList.get(0).getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
//                return false;
            }
            if (credebitList.get(0).getStatus().equals(Credebit.Status.SANAD_KHORDE)) {
                System.out.println("etebar sanad khorde1");
                return false;
            }//nemitavanad hazf shavad
            if (!credebitList.get(0).getRemainingAmount_long().equals(credebitList.get(0).getAmount_long())) {
                System.out.println("etebar sanad khorde2");
                return false; //nemitavanad hazf shavad
            }
            Criteria criteria2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "k").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria2.add(Restrictions.and(Restrictions.isNotNull("k.etebarCredebit"), Restrictions.eq("k.etebarCredebit.id", credebitId)));
            List<KhateSanad> khateSanadList = criteria2.list();
            if (khateSanadList != null && khateSanadList.size() > 0){
                System.out.println("etebar khate sanad darad");
                return false; //nemitavanad hazf shavad
            }
            if (user.hasRole(Constant.ROLE_NAMAYANDE)) //agar role namayande ro dasht
            {
                if (!credebitList.get(0).getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK) && !(user.getDaftar().getId()!=1))  //faghat ejaze hazf daryaft check ra darad
                {
                    System.out.println("role namayande va daryafte check nist");
                    return false; //nemitavanad hazf shavad
                }
            } else if (!user.hasRole(Constant.ROLE_HAZFE_ETEBAR))  //agar role haze etebar ro nadasht
            {
                System.out.println("user role hazfe etebar nadarad");
                return false; //nemitavanad hazf shavad
            }

        } else {
            System.out.println("اعتبار وجود ندارد");
            return false; //nemitavanad hazf shavad
        }

        return true; //ejaze hazf darad
    }

    public boolean validationDeleteBedehi(Integer credebitId) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("c.id", credebitId));
        List<Credebit> credebitList = criteria.list();
        if (credebitList != null && credebitList.size() > 0) {
            if (!credebitList.get(0).getCredebitType().equals(Credebit.CredebitType.PARDAKHTE_TANKHAH))
                return false;
            if (credebitList.get(0).getStatus().equals(Credebit.Status.SANAD_KHORDE))
                return false;
            if (!credebitList.get(0).getRemainingAmount_long().equals(credebitList.get(0).getAmount_long()))
                return false;

            Criteria criteria2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(KhateSanad.class, "k").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria2.add(Restrictions.and(Restrictions.isNotNull("k.bedehiCredebit"), Restrictions.eq("k.bedehiCredebit.id", credebitId)));
            List<KhateSanad> khateSanadList = criteria2.list();
            if (khateSanadList != null && khateSanadList.size() > 0)
                return false;

        } else {
            return false;
        }

        return true;
    }

    public void deleteCredebitById(Integer credebitId) {
        Credebit credebit = (Credebit) super.findById(Credebit.class, credebitId);
        super.delete(credebit);
    }

    public void deleteDaryaftFishById(Integer daryaftFishId) {
        DaryafteFish daryafteFish = (DaryafteFish) super.findById(DaryafteFish.class, daryaftFishId);
        super.delete(daryafteFish);
    }

    public void deleteKhateSanadById(Integer khateSanadId) {
        KhateSanad khateSanad = (KhateSanad) super.findById(KhateSanad.class, khateSanadId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        KhateSanadLog khateSanadLog = new KhateSanadLog(khateSanad.getAmount(), khateSanad.getEtebarRemaining(), khateSanad.getBedehiRemaining(),
                khateSanad.getEtebarCredebit(), khateSanad.getBedehiCredebit(), khateSanad.getSanad().getId(), khateSanad.getSanad().getShomare(), username, "DELETE");
        saveKhateSanadLog(khateSanadLog);
        super.delete(khateSanad);
    }

    public boolean deleteSanad(Integer sanadId) {
        if (sanadId != null) {
            List<Credebit> credebitList = new ArrayList<Credebit>();
            List<KhateSanad> khateSanadList = new ArrayList<KhateSanad>();
            khateSanadList = findAllKhateSanadsBySanadIdPaginated(sanadId).getList();
            for (KhateSanad khateSanad : khateSanadList) {
                Credebit etebarCredebit = findCredebitById(khateSanad.getEtebarCredebit().getId());
                Credebit bedehiCredebit = findCredebitById(khateSanad.getBedehiCredebit().getId());
                if (etebarCredebit != null && bedehiCredebit != null) {
                    etebarCredebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);
                    bedehiCredebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);

                    if (!credebitList.contains(etebarCredebit)) {
                        etebarCredebit.setRemainingAmount_long(etebarCredebit.getRemainingAmount_long() + Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()));
                        credebitList.add(etebarCredebit);
                    } else {
                        int indexCredebit = credebitList.indexOf(etebarCredebit);
                        etebarCredebit.setRemainingAmount_long(etebarCredebit.getRemainingAmount_long() + Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()));
                        credebitList.set(indexCredebit, etebarCredebit);
                    }


                    if (!credebitList.contains(bedehiCredebit)) {
                        bedehiCredebit.setRemainingAmount_long(bedehiCredebit.getRemainingAmount_long() + Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()));
                        credebitList.add(bedehiCredebit);
                    } else {
                        int indexCredebit = credebitList.indexOf(bedehiCredebit);
                        bedehiCredebit.setRemainingAmount_long(bedehiCredebit.getRemainingAmount_long() + Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()));
                        credebitList.set(indexCredebit, bedehiCredebit);
                    }
                } else {
                    return false;
                }
            }

            if (credebitList != null && credebitList.size() > 0) {
                saveAllCredebit(credebitList);
            }

            if (khateSanadList != null && khateSanadList.size() > 0) {
                for (KhateSanad khateSanad : khateSanadList) {
                    deleteKhateSanadById(khateSanad.getId());
                }
            }

            try {
                Sanad sanad = (Sanad) super.findById(Sanad.class, sanadId);
                super.delete(sanad);
            } catch (Exception e) {
                System.out.println(e);
            }

            return true;

        }
        return false;

    }

    public int getTatilatFromTwoDate(String fromDate, String toDate) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Tatilat.class, "t").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.ge("t.tarikh", fromDate));
        criteria.add(Restrictions.le("t.tarikh", toDate));
        List<Tatilat> tatilatList = criteria.list();
        if (tatilatList != null && tatilatList.size() > 0) {
            return tatilatList.size();
        }
        return 0;
    }

    public List<Integer> findPishnehadsForAutoBatchTaghsit(boolean manual) {
        String today = DateUtil.getCurrentDate();
        String whereClause = "";
        int daysAfter = 10;
        if (manual) {
            whereClause = " and mat.bimename_id is not null";
            daysAfter = 365;
        }
//        Query query = getSession().createQuery
//                (
//                        "select p.id from Pishnehad p join p.bimename b join b.ghestBandiList gb " +
//                                "where p.valid=true and b.readyToShow='yes' and gb.type=:tp " +
//                                "group by p.id having max(gb.tarikheTaghsit)<=:tarikh"
//                )
//                .setString("tarikh", DateUtil.addYear(today, -1))
//                .setParameter("tp", GhestBandi.Type.G_BIMENAME);
        Query query = getSession().createSQLQuery
                (
                        "select p.id                                                                                         " +
                                "from tbl_pishnehad p join tbl_bimename b on p.bimename_bimename_id = b.bimename_id                  " +
                                "join tbl_ghestbandi gb on gb.bimename_id = b.bimename_id                                            " +
                                "join tbl_ghest g on g.ghestbandi_id = gb.id                                                         " +
                                "left join man_auto_taghsit mat on mat.bimename_id = b.bimename_id                  " +
                                "where p.c_valid = 1 and b.bimename_ready = 'yes'                                                    " +
                                whereClause +
                                " and (b.state_id = 500 or b.state_id = 510)                                                          " +
//                        "and '1392/01/01' >= (                                   "+
                                "and (('" + DateUtil.addYear(DateUtil.addDays(today, daysAfter), -1) + "' >= (                                   " +
                                "    select min(g_i2.sarresid_date)                                                                  " +
                                "    from tbl_pishnehad p_i2 join tbl_bimename b_i2 on p_i2.bimename_bimename_id = b_i2.bimename_id  " +
                                "    join tbl_ghestbandi gb_i2 on gb_i2.bimename_id = b_i2.bimename_id                               " +
                                "    join tbl_ghest g_i2 on g_i2.ghestbandi_id = gb_i2.id                                            " +
                                "    where b_i2.bimename_id = b.bimename_id                                                          " +
                                "    and gb_i2.salebimei =                                                                           " +
                                "       (                                                                                            " +
                                "            select max(gb_i1.salebimei)                                                             " +
                                "            from tbl_bimename b_i1 join tbl_ghestbandi gb_i1 on gb_i1.bimename_id = b_i1.bimename_id" +
                                "            where gb_i1.type = 'G_BIMENAME' and b_i1.bimename_id = b_i2.bimename_id                 " +
                                "       )                                                                                            " +
                                "    )) " +
                                "   or ((select count(g_i3.id) " +
                                "       from tbl_credebit c_i3 " +
                                "       join tbl_ghest g_i3 on g_i3.id=c_i3.ghest_id " +
                                "       join tbl_ghestbandi gb_i3 on gb_i3.id=g_i3.ghestbandi_id    " +
                                "       where gb_i3.type='G_BIMENAME' and c_i3.remaining_amount_long>0 and gb_i3.bimename_id=b.bimename_id)=0))" +
                                "group by p.id                                                                                       "

                );
        List queryList = query.list();
        List<Integer> returnList = new ArrayList<Integer>();
        for (Object obj : queryList) {
            returnList.add(Integer.parseInt(obj.toString()));
        }
        return returnList;
    }

    public List<GhestBandi> findInvalidBimenames() {
        Query query = getSession().createSQLQuery
                (
                        "select gb.id                                                                                         " +
                                "from tbl_ghestbandi gb join man_auto_retaghsit_hazine marh on gb.bimename_id = marh.bimename_id                   " +
                                "where gb.tarikhetaghsit = marh.taghsitdate                  "

                );
        List queryList = query.list();
        List<GhestBandi> returnList = new ArrayList<GhestBandi>();
        for (Object obj : queryList) {
            returnList.add(findGhestBandiById(Integer.parseInt(obj.toString())));
        }
        return returnList;
    }

    public List<Credebit> temp_findQueuedCredebits() {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        return (List<Credebit>) (getSession().createQuery("select c from Credebit c where  c.devFlag=:flag").setString("flag", "DUPLICATE_CM").list());
    }

    public List<Integer> temp_findQueuedIds() {
        Query query = getSession().createSQLQuery
                (
                        "select c.id from v_code_moshtari_tekrari c"

                );
        List queryList = query.list();
        List<Integer> returnList = new ArrayList<Integer>();
        for (Object obj : queryList) {
            returnList.add(Integer.parseInt(obj.toString()));
        }
        return returnList;
    }

    public List<Credebit> findCredebit(String identifier, Credebit.CredebitType type) {
        if (!identifier.equals(""))
            return (List<Credebit>) (getSession().createQuery("select c from Credebit c where c.identifier=:idenfr and c.credebitType=:creType").setString("idenfr", identifier).setString("creType", type.toString()).list());
        return (List<Credebit>) (getSession().createQuery("select c from Credebit c where c.credebitType=:creType").setString("creType", type.toString()).list());
    }
    public List<Integer> hazfeSanad(String created_date, Credebit.CredebitType type)
    {
       return (List<Integer>)(getSession().createQuery("select khs.id from KhateSanad kh join kh.sanad khs join kh.bedehiCredebit khb join kh.etebarCredebit khe where khs.createdDate=:created_date and khb.credebitType=:type and khb.vahedeSodor.id <> khe.vahedeSodor.id").setString("created_date",created_date.toString()).setParameter("type",type).list());

    }
    public void updateGhest(Ghest ghest) {
        super.update(ghest);
    }

    public void refreshObject(Object object) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().refresh(object);
    }

    public List<GhestBandi> listInvalidGhestBandiVams() {
        Query query = getSessionFactory().getCurrentSession().createQuery("select gb from GhestBandi gb left join gb.darkhastList db where db.id is null and gb.type=:type ").setParameter("type", GhestBandi.Type.G_VAM);
        return query.list() != null ? query.list() : null;
    }

    public PaginatedListImpl<GhestVam> getAghsatVamReport(PaginatedListImpl<GhestVam> pgList, GhestVam gvSerach) {
        String whereClause = "";
        //add clause. . .

        if (whereClause.length() > 0) {
            whereClause = "and " + whereClause;
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        Query count = getSession().createQuery
                (
                        "select count(c.id) " +
                                "from Credebit c join c.ghest g join g.ghestBandi gb join gb.darkhastList db " +
                                "where db.darkhastType=:dbVAM and db.state>=10100 and c.credebitType=:gVAM "
                )
                .setParameter("dbVAM", DarkhastBazkharid.DarkhastType.VAM)
                .setParameter("gVAM", Credebit.CredebitType.GHEST_VAM);

        pgList.setFullListSize(((Long) count.uniqueResult()).intValue());

        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.GhestVam(db.shomareVam,c.amount_long,c.createdDate,g.maliatLong,g.hazineKarmonz,c.shomareMoshtari,g.sarresidDate,g.hazineVosoul) " +
                                "from Credebit c join c.ghest g join g.ghestBandi gb join gb.darkhastList db " +
                                "where db.darkhastType=:dbVAM and db.state>=10100 and c.credebitType=:gVAM "
                )
                .setParameter("dbVAM", DarkhastBazkharid.DarkhastType.VAM)
                .setParameter("gVAM", Credebit.CredebitType.GHEST_VAM);

        query.setFirstResult(pgList.getObjectsPerPage() * pgList.getPageNumber());
        query.setMaxResults(pgList.getObjectsPerPage());
        pgList.setList(query.list());
        pgList.setPageNumber(pgList.getPageNumber() + 1);
        return pgList;
    }

    public boolean isAvailableAuthorityId(String authority) {
        Query count = getSession().createQuery
                (
                        "select count(c.id) " +
                                "from Credebit c " +
                                "where c.authorityId = '" + authority + "'"
                );

        Long result = (Long) count.uniqueResult();

        if (result != null && result > 0)
            return true;
        return false;
    }

    public List<Credebit> findCredebitbyStatusACHPayment(Credebit.ACHStatus status) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        return (List<Credebit>) (getSession().createQuery("select c from Credebit c where  c.credebitType=:creType and c.achStatus=:status ").setString("creType", "ACH").setString("status", status.toString()).list());
    }

    public List<Credebit> getCredebitByTypeForBimename(Credebit.CredebitType type, String shomareBimename) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Credebit.class, "c").createCriteria("c.bimename", "b")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("c.credebitType", type))
                .add(Restrictions.eq("b.shomare", shomareBimename));
        return (List<Credebit>) criteria.list();
    }

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforcales(String tarikheSodorFrom, String tarikheSodorTo, String vahedSodorId, User user) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        String whereClause = "";
        if (!(user.getNamayandegi() == null))
            whereClause += " and  v.id ='" + user.getNamayandegi().getId() + "'  ";
        if (!(vahedSodorId.equals(null) || vahedSodorId.equals("")))
            whereClause += "  and  c.vahedeSodor.id='" + vahedSodorId + "'";
        if (!(tarikheSodorFrom.equals(null) || tarikheSodorFrom.equals("")))
            whereClause += " and c.createdDate >='" + tarikheSodorFrom + "'  ";
        if (!(tarikheSodorTo.equals(null) || tarikheSodorTo.equals("")))
            whereClause += " and c.createdDate <='" + tarikheSodorTo + "'  ";
        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.EtebarBedehiVM(sum(c.amount_long),count(c.id) ,c.credebitType,n.kodeNamayandeKargozar)" +
                                " from Credebit c    inner  join  c.vahedeSodor n   inner  join n.vahedSodur v " +
                                "where  c.identifier like '1110%' " + whereClause +
                                "group by c.credebitType,c.vahedeSodor,n.kodeNamayandeKargozar  order by c.vahedeSodor ");
        return (List<EtebarBedehiVM>) query.list();

    }

    public List<EtebarBedehiVM> findCredebitbySubSystemIdentiferforBadane(String tarikheSodorFrom, String tarikheSodorTo, String vahedSodorId, User user) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        String whereClause = "";
        if (!(vahedSodorId.equals(null) || vahedSodorId.equals("")))
            whereClause += "  and  c.vahedeSodor.id='" + vahedSodorId + "'";
        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.EtebarBedehiVM(sum(c.amount_long),count(c.id) ,c.credebitType,n.kodeNamayandeKargozar )" +
                                " from Credebit c   inner  join  c.vahedeSodor n   inner  join n.vahedSodur v " +
                                "where  c.identifier like '1120%'  and c.createdDate >='" + tarikheSodorFrom + "'" +
                                "and c.createdDate <='" + tarikheSodorTo + "'" +
                                "and v.id='" + user.getNamayandegi().getVahedSodur().getId() + "'" + whereClause +
                                "group by c.credebitType,c.vahedeSodor,n.kodeNamayandeKargozar ");
        return (List<EtebarBedehiVM>) query.list();


    }


    public List<CredebitBedehiVM> findAllBedehiCredebits(String rcptName, String createdDateFrom, String createdDateTo, String namayandeId, String pishShomareBimename, User user) {
        String whereClause = "";
        if (createdDateFrom != null && createdDateFrom.length() > 0)
            whereClause += " and c.createdDate >='" + createdDateFrom + "'";
        if (createdDateTo != null && createdDateTo.length() > 0)
            whereClause += " and c.createdDate <='" + createdDateTo + "'";
        if (rcptName != null && rcptName.length() > 0)
            whereClause += " and c.rcptName  like '%" + rcptName + "%'";
        if (namayandeId != null && namayandeId.length() > 0)
            whereClause += "and n.id=" + Long.parseLong(namayandeId) + "";
        if (pishShomareBimename != null && pishShomareBimename.length() > 0)
            whereClause += "and c.identifier like '%" + pishShomareBimename + "%'";
        Query query = getSession().createQuery
                (
                        "select new com.bitarts.parsian.viewModel.CredebitBedehiVM(c.rcptName,c.identifier,c.createdDate,c.sarresidDate,c.amount_long,c.remainingAmount_long,kh.amount,n.kodeNamayandeKargozar,kh.etebarCredebit.credebitType)" +
                                " from Credebit c  join c.namayande n join c.khateSanadsBedehi kh " +
                                " where c.vahedeSodor.id= " + user.getNamayandegi().getId() + "  " + whereClause + " ");


        return (List<CredebitBedehiVM>) query.list();
    }

    public BazarYabSanam getBazarYabByCode(Long bazaryabCode) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BazarYabSanam.class)
                .add(Restrictions.eq("code", bazaryabCode));
        try {
            List<BazarYabSanam> list = (List<BazarYabSanam>) criteria.list();
            if (list.size() == 0)
                return null;
            else
                return list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void saveBAzarYabSanam(BazarYabSanam bazarYabSanam) {
        super.saveOrUpdate(bazarYabSanam);
    }

    public List<Integer> dev_findAllEtebarCredebitsByBankInfo() {
        Query query = getSession().createSQLQuery
                (
                        "select c_etebar.id from man_auto_upload mau join tbl_bankinfo bi on bi.main_id = mau.bankinfo_id " +
                                "join tbl_credebit c_etebar on c_etebar.id = bi.credebit_id " +
                                "join tbl_credebit c_bedehi on c_bedehi.id = mau.credebit_id " +
                                "where c_etebar.remaining_amount_long = c_etebar.amount_long " +
                                "and to_number(replace(bi.mablagh, ',', '')) = c_bedehi.remaining_amount_long "
                );
        List queryList = query.list();
        List<Integer> returnList = new ArrayList<Integer>();
        for (Object obj : queryList) {
            returnList.add(Integer.parseInt(obj.toString()));
        }
        return returnList;
    }
    public List<Credebit> getCredebitBankInfos()
    {
        Query query=getSession().createQuery
        (
            "select c from Credebit c join c.bankInfoList b where c.credebitType=:psh and c.remainingAmount_long > 0 and c.shomareMoshtari is not null "
        ).setParameter("psh",Credebit.CredebitType.PISHPARDAKHT);

        return query.list();
    }

    //b-h
    public Credebit findCredebitDaryafteCheckByCodeMoshtari(String codemoshtari){
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        criteria.add(Restrictions.eq("shomareMoshtari", codemoshtari));
        criteria.add(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.DARYAFTE_CHECK));
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public List<vaziateBedehiVaEtebar> estelamVaziateBedehiVaEtebar(String  uniqueCode,String subsystemName){
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
//                .createCriteria(Credebit.class, "c").createCriteria("c.") ;
//        criteria.add(Restrictions.eq("c.subsystemName",subsystemName)) ;
//        criteria.add(Restrictions.eq("c.uniqueCode",uniqueCode));
//        criteria.add(Restrictions.eq("c.remainingAmount_long",0));
//        criteria.add(Restrictions.eq("c.status",Credebit.Status.SANAD_KHORDE));
//        criteria.add(Restrictions.eq("c.vosoulState",Credebit.VaziyatVosoul.TAEED_VOSOUL));

        Query query=getSessionFactory().getCurrentSession().createQuery("");//select new  com.bitarts.parsian.viewModel.vaziateBedehiVaEtebar(s.createdDate,cre.status,cre.vosoulState,s.vaziat,cre.remainingAmount_long)"+
                //"  from KhateSanad khs  inner join  khs.sanad  s " +
               // " inner join khs.bedehiCredebit cre" +
               // " where cre.uniqueCode=:unCode AND cre.subsystemName=:Sname ").setString("unCode",uniqueCode).setString("Sname",subsystemName);
        return (List<vaziateBedehiVaEtebar>)query.list();
    }

    public List<sooratVaziatMali_new> findBedehiByShomareBimeName(String shomarebimename,User user){
        String hql="select new com.bitarts.parsian.viewModel.sooratVaziatMali_new(kh,s,etebar,bedehi) "+
                "        from  Credebit bedehi " +

                "                left join  bedehi.khateSanadsBedehi kh left join kh.etebarCredebit etebar "  +
                "                left join kh.sanad s where bedehi.identifier like '"+shomarebimename+"%' and bedehi.daftar.id="+user.getDaftar().getId();
        if(user.getNamayandegi() !=null && user.getNamayandegi().getNamayandeType()!= Namayande.NayamandeType.MOJTAMA)
            hql+=" and bedehi.vahedeSodor.id="+user.getNamayandegi().getId();
        hql+=" order by bedehi.identifier asc,bedehi.sarresidDate asc";
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        return  (List<sooratVaziatMali_new>)query.list();

    }

    public Credebit findAllCredebitsByCodeMoshtari(String codemoshtari,User user){
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class);
        criteria.add(Restrictions.eq("shomareMoshtari", codemoshtari));
        criteria.add(Restrictions.or(Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.DARYAFTE_CHECK),Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, Credebit.CredebitType.PARDAKHT_SHENASEDAR)));
        //b-h
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        if(daftar_id==1)
//            criteria.add(Restrictions.or(Restrictions.eq("daftar.id",daftar_id),Restrictions.isNull("daftar.id")));
//        else
//        if(daftar_id==1)
        Integer daftar_id=user.getDaftar().getId();
            criteria.add(Restrictions.eq("daftar.id",daftar_id));
        ///// ta inja
        List<Credebit> result = criteria.list();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public PaginatedListImpl<Credebit>  findOnlyDaftarParsianCredebits(User user,PaginatedListImpl<Credebit> paginatedList,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId){
//        PaginatedListImpl<Credebit> paginatedList = new PaginatedListImpl<Credebit>();
        //paginatedList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);


        Long[] namayandehayeSubset;
        List<Long> namayandeIdListId = NamayandehayeSubset(user);


        namayandehayeSubset = namayandeIdListId.toArray(new Long[namayandeIdListId.size()]);
        DetachedCriteria criteria = DetachedCriteria.forClass(Credebit.class, "c");
        boolean isAdminMali = true;
        if (user != null) {
            if (userHasRole(user, "ROLE_NAMAYANDE") && userHasRole(user, "ROLE_KARBAR_MALI")) {
                if (user != null && user.getNamayandegi() != null) {
                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.or(Restrictions.in("c.vahedeSodor.id", namayandehayeSubset),Restrictions.in("c.namayande.id",namayandehayeSubset))));
//                    criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.in("c.vahedeSodor.id", namayandehayeSubset)));
                    isAdminMali = false;
                }
            }
        }

        criteria.setProjection(Projections.id());

        Criterion criterion = null;
        for (Credebit.CredebitType bedehiType : Credebit.bedehiTypes) {
            // aghsat omr neshan dade nemishavad
            if (!bedehiType.equals(Credebit.CredebitType.GHEST)) {
                if (criterion == null) criterion = Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType);
                else criterion = Restrictions.or(criterion, Restrictions.eq(CREDEBIT__CREDEBIT_TYPE, bedehiType));
            }
        }
        criteria.add(criterion);

        criteria.createCriteria("c.khateSanadsBedehi", "k", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("k.sanad", "s", CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.or(Restrictions.gt("c.remainingAmount_long", 0l), Restrictions.and(Restrictions.eq("c.status", Credebit.Status.SANAD_KHORDE), Restrictions.eq("s.vaziat", Sanad.Vaziat.MOVAGHAT))));
        criteria.add(Restrictions.lt("sarresidDateWithMohlatSarresid", DateUtil.getCurrentDate()));
//        criteria.add(Restrictions.or(Restrictions.isNull("c.vaziatEnteghalBedehi"),Restrictions.eq("c.vaziatEnteghalBedehi",0)));
//        criteria.createCriteria("c.daftar", "d", CriteriaSpecification.LEFT_JOIN);
//        criteria.add(Restrictions.eq("d.id",new Integer(1))) ;
//      //b-h
        criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("c.daftar.id",1),Restrictions.isNull("c.daftar.id")),Restrictions.or(Restrictions.isNull("c.vaziatEnteghalBedehi"),Restrictions.eq("c.vaziatEnteghalBedehi",0))));
        //for search
        if (identifier != null && identifier.length() > 0)
            criteria.add(Restrictions.like("c.identifier", identifier, MatchMode.ANYWHERE));
//
//        if (shomareMoshtari != null && shomareMoshtari.length() > 0)
//            criteria.add(Restrictions.eq("shomareMoshtari", shomareMoshtari));
//
        if (rcptName != null && rcptName.length() > 0)
            criteria.add(Restrictions.like("c.rcptName", rcptName, MatchMode.ANYWHERE));

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            criteria.add(Restrictions.ge("c.sarresidDate", sarresidDateFrom));

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            criteria.add(Restrictions.le("c.sarresidDate", sarresidDateTo));

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            criteria.add(Restrictions.ge("c.createdDate", createdDateFrom));

        if (createdDateTo != null && createdDateTo.length() > 0)
            criteria.add(Restrictions.le("c.createdDate", createdDateTo));

//        if (amount != null && amount.length() > 0)
//            criteria.add(Restrictions.eq("amount_long", Long.parseLong(amount.replaceAll(",", ""))));
//
//        if (paidReceivedAmount != null && paidReceivedAmount.length() > 0) {
//            criteria.add(Restrictions.eq("paidReceivedAmount", Long.parseLong(paidReceivedAmount.replaceAll(",", ""))));
//        }
//
//        if (remainingAmount != null && remainingAmount.length() > 0)
//            criteria.add(Restrictions.eq("remainingAmount_long", Long.parseLong(remainingAmount.replaceAll(",", ""))));
//
//        if (credebitTypeFarsi != null)
//            criteria.add(Restrictions.eq("credebitType", credebitTypeFarsi));
//
//        if (shomareGharardad != null && shomareGharardad.length() > 0)
//            criteria.add(Restrictions.eq("shomareGharardad", shomareGharardad));

        if (search_namayandegiId != null && search_namayandegiId > 0) {
            criteria.createCriteria("c.namayande", "nam", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.namayande"), Restrictions.eq("nam.id", search_namayandegiId)));
        }

        if (search_vahedesodorId != null && search_vahedesodorId > 0) {
            criteria.createCriteria("c.vahedeSodor", "vah", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.vahedeSodor"), Restrictions.eq("vah.id", search_vahedesodorId)));
        }
        if (bazaryabSanamId != null && bazaryabSanamId > 0) //bazaryabSanam
        {
            criteria.createCriteria("c.bazarYabSanam", "ecbs", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.and(Restrictions.isNotNull("c.bazarYabSanam"), Restrictions.eq("ecbs.id", bazaryabSanamId)));
        }
//
//        if (statusFarsi != null) {
//            if (statusFarsi.equals(Credebit.Status.SANAD_KHORDE))
//                criteria.add(Restrictions.not(Restrictions.eqProperty("remainingAmount_long", "amount_long")));
//            else if (statusFarsi.equals(Credebit.Status.SANAD_NA_KHORDE))
//                criteria.add(Restrictions.eqProperty("remainingAmount_long", "amount_long"));
//        }
//
//
//        if (vosoulStateFarsi != null) {
//            if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
//                criteria.add(Restrictions.eq("vosoulState", vosoulStateFarsi));
//            else if (vosoulStateFarsi.equals(Credebit.VaziyatVosoul.TAEED_NASHODE))
//                criteria.add(Restrictions.or(Restrictions.eq("vosoulState", vosoulStateFarsi), Restrictions.isNull("vosoulState")));
//        }


        Criteria count = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        count.add(Subqueries.propertyIn("c.id", criteria));
        int countInt = 0;
        if (!isAdminMali || true)
            countInt = Integer.parseInt(count.setProjection(Projections.rowCount()).list().get(0).toString());
        paginatedList.setFullListSize(countInt);

        Criteria nonDetachedCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Credebit.class, "c").
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        nonDetachedCriteria.add(Subqueries.propertyIn("c.id", criteria));
        nonDetachedCriteria.addOrder(Order.desc("c.sarresidDate"));

//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        criteria.addOrder(Order.desc("c.sarresidDate"));
//        Criteria nonDetachedCriteria=criteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
        //b-h
//        CriteriaImpl criteriaImpl = (CriteriaImpl)nonDetachedCriteria;
//        SessionImplementor session = criteriaImpl.getSession();
//        SessionFactoryImplementor factory = session.getFactory();
//        CriteriaQueryTranslator translator=new CriteriaQueryTranslator(factory,criteriaImpl,criteriaImpl.getEntityOrClassName(),CriteriaQueryTranslator.ROOT_SQL_ALIAS);
//        String[] implementors = factory.getImplementors( criteriaImpl.getEntityOrClassName() );
//
//        CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)factory.getEntityPersister(implementors[0]),
//                translator,
//                factory,
//                criteriaImpl,
//                criteriaImpl.getEntityOrClassName(),
//                session.getLoadQueryInfluencers()   );
//
//        String sql=walker.getSQLString();
//        System.out.println("sql query"+sql);
        //b-h here

//        if (!isExport()) {
//
//            nonDetachedCriteria.setFirstResult((paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage());
//            nonDetachedCriteria.setMaxResults((paginatedList.getPageNumber()) * paginatedList.getObjectsPerPage());
//
//        } else {
//            nonDetachedCriteria.setFirstResult(0);
//            nonDetachedCriteria.setMaxResults(countInt);
//            paginatedList.setObjectsPerPage(countInt);
//        }
//        if (isAdminMali && !isSearch)
//            paginatedList.setList(new ArrayList<Credebit>());
//        else
        nonDetachedCriteria.setFirstResult(paginatedList.getPageNumber() * paginatedList.getObjectsPerPage());
        nonDetachedCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        paginatedList.setList(nonDetachedCriteria.list());
        paginatedList.setPageNumber(paginatedList.getPageNumber() + 1);


        return paginatedList;
    }

    public Daftar findDaftarIdByName(Long namayandeId){

        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Daftar.class,"d");

        criteria.add(Restrictions.eq("d.namayande.id",namayandeId));
        List daftars=criteria.list();
        if(daftars !=null && daftars.size()>0)
            return (Daftar)daftars.get(0);
        else
            return null;

    }

    public Credebit enteghalBedehiBeDaftarNamayande(Credebit Parsianbedehi,Daftar daftar,Integer noeEnteghal){
        Credebit namayandeBedehi=new Credebit(Parsianbedehi);
        namayandeBedehi.setCreatedDate(DateUtil.getCurrentDate());
        namayandeBedehi.setCreatedTime(DateUtil.getCurrentTime());
        namayandeBedehi.setStatus(Credebit.Status.SANAD_NA_KHORDE);
        namayandeBedehi.setVosoulState(Credebit.VaziyatVosoul.TAEED_NASHODE);
        namayandeBedehi.setDaftarParsian_credebit(Parsianbedehi);
        namayandeBedehi.setDaftar(daftar);
        super.save(namayandeBedehi);
        Parsianbedehi.setVaziatEnteghalBedehi(noeEnteghal);
        super.save(Parsianbedehi);
        return  namayandeBedehi;
    }

    public Daftar findDaftarById(Integer id) {
        return (Daftar) super.findById(Daftar.class, id);
    }

    public Daftar findDaftarByCodeNamayande(Long namayandeId){
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Daftar.class,"d");
        criteria.add(Restrictions.eq("d.namayande.id",namayandeId));
        return (Daftar)criteria.list().get(0);
    }

    public int tedadDaftareNamayande(Long namayandeID){
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Namayande.class);
        criteria.add(Restrictions.eq("id",namayandeID));
        Namayande namayande=(Namayande)criteria.list().get(0);
        return namayande.getTedadDaftar();
    }

    public PaginatedListImpl<bedehiTasviyeNashode> findbedehiTasviyeNashodeNamayande(int page,User user,String identifier,String rcptName,String sarresidDateFrom,String sarresidDateTo,String createdDateFrom,String createdDateTo,String amount,String remainingAmount,Long search_namayandegiId,Long search_vahedesodorId,Long bazaryabSanamId,String bedehiColor, int reshte, boolean isSearch){
        PaginatedListImpl<bedehiTasviyeNashode> resultList=new PaginatedListImpl<bedehiTasviyeNashode>();
        resultList.setPageNumber(page);
        resultList.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);

        String query = "select subsystem_identifier as bimenameID, " +
                             " nvl(cre.rcpt_name,'') as bimeGozarID, " +
                             " namayande.kodenamayandekargozar as namayandeID, " +
                             " namayande.name as namayandeName, " +
                             " vsodoor.kodenamayandekargozar as vsodoorID," +
                             " vsodoor.name as vsodoorName, " +
                             " cre.sarresid_date as sarresid_date, " +
                             " cre.created_date as created_date, " +
                             " nvl(cre.amount_long,0) as mablaghKol, " +
                             " nvl(cre.remaining_amount_long,0) as sanadNakhorde, " +
                             " nvl(cre.mablaghtasvienashode, 0) as tasvieNashode, " +
                             " cretype.farsiname as CreType, " +
                             " cre.bazaryab_sanam_id as bazaryab_sanam_id, " +
                             " nvl(cre.mohlat_sarresid, 0) as mohlatsarresid" +
                             " from (select * from tbl_credebit where mablaghtasvienashode > 0)cre inner join " +
                             " (select * from tbl_credebittype where bedorbes = 1 )cretype on cre.credebit_type = cretype.latinname" +
                             " inner join tbl_namayande namayande on cre.namayande_id = namayande.id" +
                             " inner join tbl_namayande vsodoor on cre.vahedesodor_id = vsodoor.id" +
                             " where (1=1)  ";
        if(user.getNamayandegi()!=null){
             query += "AND namayande.kodenamayandekargozar = " + user.getNamayandegi().getKodeNamayandeKargozar();
        }
        if(identifier != null && identifier.length() > 0)
            query += " AND cre.subsystem_identifier LIKE '%" + identifier + "%'";

        if (rcptName != null && rcptName.length() > 0)
            query += " AND cre.rcpt_Name LIKE '%" + rcptName + "%'";

        if (sarresidDateFrom != null && sarresidDateFrom.length() > 0)
            query += " AND cre.sarresid_Date >= '" + sarresidDateFrom + "'";

        if (sarresidDateTo != null && sarresidDateTo.length() > 0)
            query += " AND cre.sarresid_Date <= '" + sarresidDateTo + "'";

        if (createdDateFrom != null && createdDateFrom.length() > 0)
            query += " AND cre.created_Date >= '" + createdDateFrom + "'";

        if (createdDateTo != null && createdDateTo.length() > 0)
            query += " AND cre.created_Date <= '" + createdDateTo + "'";

        if (amount != null && amount.length() > 0)
            query += " AND cre.amount_long = " + Long.parseLong(amount.replaceAll(",", ""));

        if (remainingAmount != null && remainingAmount.length() > 0)
            query += " AND cre.remaining_Amount_long = " + Long.parseLong(remainingAmount.replaceAll(",", ""));

        if (search_namayandegiId != null && search_namayandegiId > 0) {
            query += " AND namayande.KODENAMAYANDEKARGOZAR = " + search_namayandegiId;
        }
        if (search_vahedesodorId != null && search_vahedesodorId > 0) {
            query += " AND vsodoor.KODENAMAYANDEKARGOZAR = " + search_vahedesodorId;
        }
        if (bazaryabSanamId != null && bazaryabSanamId > 0) {
            query += " AND  cre.bazaryab_sanam_id ="+bazaryabSanamId;
        }
        if (bedehiColor != null && bedehiColor.length() > 0) {
            if(bedehiColor.compareTo("RED") == 0 ){

                query += " and sarresid_date is not NULL "
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian' ) + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE  "
                        + " and length(sarresid_date) = 10 "
                        + " AND  cre.remaining_amount_long > 0 "; //sanad nakhorde darad
            }
            else if(bedehiColor.compareTo("ORANGE") == 0 ){

                query += " and sarresid_date is not NULL "
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE  "
                        + " and length(sarresid_date) = 10 "
                        + " AND  cre.MABLAGHTASVIENASHODE > 0 "  // tasvie nashode darad
                        + " AND  cre.remaining_amount_long = 0 ";    // sanad nazade nadarad
            }
            else if(bedehiColor.compareTo("YELLOW") == 0 ){

                query += " and sarresid_date is not NULL "
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE+2  "
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  > CURRENT_DATE  "
                        + " and length(sarresid_date) = 10 "
                        + " AND  cre.MABLAGHTASVIENASHODE > 0 ";  // tasvie nashode darad

            }
            else if(bedehiColor.compareTo("NO_COLOR") == 0 ){

                query += " AND sarresid_date is not NULL "
                        + " AND"
                        +" ( cre.MABLAGHTASVIENASHODE = 0 AND  cre.remaining_amount_long = 0) "  // tasvie nashode darad
                        + " OR (cre.MABLAGHTASVIENASHODE = 0 AND TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  >= CURRENT_DATE AND TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE + 2 )  "
                        + " OR (TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  > CURRENT_DATE + 2 ) "   ;
            }
            else if(bedehiColor.compareTo("RED_ORANGE") == 0 ){

                query += " and ( (sarresid_date is not NULL "        //ORANGE
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian') + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE  "
                        + " and length(sarresid_date) = 10 "
                        + " AND  cre.MABLAGHTASVIENASHODE > 0 "
                        + " AND  cre.remaining_amount_long = 0) ";
                query += " or (sarresid_date is not NULL "       //RED
                        + " and TO_DATE(sarresid_date , 'yyyy/mm/dd' , 'nls_calendar=persian' ) + NVL(MOHLAT_SARRESID , 0)  <= CURRENT_DATE  "
                        + " and length(sarresid_date) = 10 "
                        + " AND  cre.remaining_amount_long > 0 ) )  ";

            }

        }
        if ( reshte > 0) {
            query += " AND  cre.field = "+ reshte;
        }
        if(!isSearch){
            query += " AND (1=2)";
        }
		if (user.getNamayandegi() != null ) {
            query += " AND  namayande.KODENAMAYANDEKARGOZAR = "+user.getNamayandegi().getKodeNamayandeKargozar();
        }
        Query Str=getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query);
        System.out.println("bedehi tasviye nashode query ali:"+query);
        List<Object[]> tempList =Str.list();
        List<bedehiTasviyeNashode> listBedehi = new ArrayList<bedehiTasviyeNashode>();

        for(int i=0; i<tempList.size(); i++){
            //String obj = tempList.get(i)[0];
//            System.out.println((String)tempList.get(i)[0]);
            String bimenameID            = (String)tempList.get(i)[0];
            String bimeGozarID           = (String)tempList.get(i)[1];
            String namayandeID           = (String)tempList.get(i)[2];
            String namayandeName         = (String)tempList.get(i)[3];
            String vsodoorID             = (String)tempList.get(i)[4];
            String vsodoorName           = (String)tempList.get(i)[5];
            String sarresid_date         = (String)tempList.get(i)[6];
            String created_date          = (String)tempList.get(i)[7];
            BigDecimal   mablaghKol      = (BigDecimal)tempList.get(i)[8];
            BigDecimal   sanadNakhorde   = (BigDecimal)tempList.get(i)[9];
            BigDecimal   tasvieNashode   = (BigDecimal)tempList.get(i)[10];
            String CreType               = (String)tempList.get(i)[11];
            BigDecimal bazaryab_sanam_id = (BigDecimal)tempList.get(i)[12];
            Number mohlatsarresid        = (Number)tempList.get(i)[13];

            int mohlat;
            Long bazaryab;
            if(bazaryab_sanam_id != null){
                bazaryab = bazaryab_sanam_id.longValue();
            }
            else
                bazaryab = new Long(0);
            if(mohlatsarresid != null){
                 mohlat = mohlatsarresid.intValue();
            }
            else{
                mohlat = 0;
            }
            bedehiTasviyeNashode b = new bedehiTasviyeNashode(bimenameID, bimeGozarID, namayandeID, namayandeName, vsodoorID, vsodoorName, sarresid_date, created_date, mablaghKol.longValue(), sanadNakhorde.longValue(), tasvieNashode.longValue(), CreType, bazaryab ,mohlat);
            listBedehi.add(b);
        }
        if(!isExport()) {
            int pagesize=((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE) + PagingUtil.MAX_OBJECTS_PER_PAGE;
            int listsize= listBedehi.size();
            if(listsize>=pagesize)
                resultList.setList(listBedehi.subList(((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE), pagesize ));
            else
                resultList.setList( listBedehi.subList(((page - 1) * PagingUtil.MAX_OBJECTS_PER_PAGE), listsize));
        }
        else{
            resultList.setList(listBedehi);
            resultList.setPageNumber(1);
            resultList.setObjectsPerPage(Integer.MAX_VALUE);

        }
        resultList.setFullListSize(listBedehi.size());
        //System.out.println(resultList.getList().get(1).);
        //System.out.println(resultList.getList().get(0).);

        return  resultList;
    }

    public List<sooratVaziatMali_new> findbedehiNamayandeForGozaresh(Long namayandeId,User user){

        List<Credebit.CredebitType> types=new ArrayList<Credebit.CredebitType>();
        for(Credebit.CredebitType type:Credebit.bedehiTypes) {
            if(!type.equals(Credebit.CredebitType.GHEST) && !type.equals(Credebit.CredebitType.GHEST_VAM))
                types.add(type);
        }
        Credebit.CredebitType[]  typesArray =types.toArray(new Credebit.CredebitType[types.size()]) ;
        List<Long> namayandeIdListId = NamayandehayeSubsetForGozareshListBedehi(namayandeId);

        Long[]namayandehayeSubset = namayandeIdListId.toArray(new Long[namayandeIdListId.size()]);
        String hql="select new com.bitarts.parsian.viewModel.sooratVaziatMali_new(kh,s,etebar,bedehi) from  Credebit  bedehi  " +
                " left join  bedehi.khateSanadsBedehi kh left join kh.etebarCredebit etebar" +
                " left join kh.sanad s where bedehi.credebitType in (:types) and bedehi.daftar.id="+user.getDaftar().getId()+" and bedehi.vahedeSodor.id in (:namayandehayeSubset)" ;


//        System.out.println("gozaresh query"+hqlToSql(hql));
        Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameterList("types",typesArray);
        query.setParameterList("namayandehayeSubset",namayandehayeSubset);
        return (List<sooratVaziatMali_new>) query.list();
    }

    public List<Long> NamayandehayeSubsetForGozareshListBedehi(Long namayandeId) {

//        List<Long> namayandeIdListId = new LinkedList<Long>();
//        namayandeIdListId.add(namayandeId);
        List<BigDecimal> namayandeIdlist = new ArrayList<BigDecimal>();
        String sql = "select na.id from tbl_namayande na " +
                        " left join (SELECT n.id FROM tbl_namayande n START WITH id = " + namayandeId + " CONNECT BY NOCYCLE PRIOR id = sarparast_id)nam on nam.id = na.id " +
                        " where nam.id is not null ";//and nam.id = " + user.getNamayandegi().getId();

        SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        namayandeIdlist = sqlQuery.list();
        System.out.println("namayande subset new query"+sql);
//        namayandeIdlist.add(new BigDecimal(user.getNamayandegi().getId()));
        List<Long> namayandeIdListLong = new ArrayList<Long>();
        for (BigDecimal namayandeIdD : namayandeIdlist) {
            namayandeIdListLong.add(Long.parseLong(namayandeIdD.toString()));
        }
        return namayandeIdListLong;
    }



}
