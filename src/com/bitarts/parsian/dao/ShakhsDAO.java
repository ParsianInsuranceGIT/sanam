package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Darkhast;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.viewModel.SearchResult;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:09 PM
 */
public class ShakhsDAO extends BaseDAO {
    public static String ID = "id";
    public static String NAME = "name";
    public static String NAME_KHANEVADEGI = "nameKhanevadegi";
    public static String NAME_PEDAR = "namePedar";
    public static String IRANI_OR_ATBAE_KHAREJI = "iraniOrAtbaeKhareji";
    public static String KODE_MELLI_SHENASAYI = "kodeMelliShenasayi";
    public static String SHOMARE_SHENASNAME = "shomareShenasnameh";
    public static String SHOMARE_SABT = "shomareSabt";
    public static String MAHALLE_TAVALOLD = "mahalleTavallod";
    public static String TARIKHE_TAVALLOD = "tarikheTavallod";
    public static String VAZIATE_TAAHOL = "vaziateTaahol";
    public static String JENSIAT = "jensiat";
    public static String SHOGHLE_ASLI = "shoghleAsli";
    public static String SHOGHLE_FAREI = "shoghleFarei";


    public void save(Shakhs shakhs) {
        super.save(shakhs);
    }

    public void update(Shakhs shakhs) {
        super.update(shakhs);
    }

    public List<SearchResult> doSearchHoghughi(String naam, String shomareSabt) {
        Criteria shakhsesCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        shakhsesCriteria.add(Restrictions.ilike(NAME, naam, MatchMode.ANYWHERE));
        shakhsesCriteria.add(Restrictions.eq(SHOMARE_SABT, shomareSabt));

        List<Shakhs> shakhses = shakhsesCriteria.list();
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        for (Shakhs shakhs : shakhses) {
            SearchResult searchResult = new SearchResult();
            searchResult.setId(shakhs.getId());
            searchResult.setNaam(shakhs.getName());
            searchResult.setShomareSabt(shakhs.getShomareSabt());
            searchResult.setKodeEghtesadi(shakhs.getKodeEghtesadi());
            searchResult.setMahalleSabt(shakhs.getMahalleSabt().getCityName());
            searchResult.setTarikheSabt(shakhs.getTarikheSabt());
            searchResults.add(searchResult);
        }
        return searchResults;
    }

    public List<SearchResult> doSearch(String code_melli, String searchBimeShode,String halateElhaghie) {
        Criteria criteria = getShakhsSearchCriteria(code_melli,halateElhaghie);
        List<Shakhs> shakhses = criteria.list();
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        for (Shakhs shakhs : shakhses) {
            boolean isShakhsOK = true;
            if(searchBimeShode.equals("true") && isAdamTaeed(shakhs.getKodeMelliShenasayi()))
                isShakhsOK = false;
            if(checkDarkhastTaghirForPerson(shakhs.getKodeMelliShenasayi()))
                isShakhsOK= false;
            SearchResult searchResult = new SearchResult();
            searchResult.setId(shakhs.getId());
            searchResult.setNaam(shakhs.getName());
            searchResult.setNaamKhaanevaadegi(shakhs.getNameKhanevadegi());
            searchResult.setNaamPedar(shakhs.getNamePedar());
            searchResult.setCodeMelli(shakhs.getKodeMelliShenasayi());
            searchResult.setShomareShenaasnaame(shakhs.getShomareShenasnameh());
            if(isShakhsOK)
                searchResult.setOK("yes");
            else
                searchResult.setOK("no");
            searchResults.add(searchResult);
        }
        return searchResults;
    }

    public Shakhs findById(Integer id) {
        return (Shakhs) super.findById(Shakhs.class, id);
    }

    public boolean isHavePishnehadOrCurrentDarkhast(String codeMelli)
    {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class, "s");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("s.kodeMelliShenasayi", codeMelli));
        criteria.createCriteria("s.bimeGozar", "bg", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("s.bimeShode", "bs", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bs.pishnehadSet", "bspl", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bg.pishnehadSet", "bgpl", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bspl.bimename", "bsplb", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bgpl.bimename", "bgplb", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bsplb.state", "bsplbs", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("bgplb.state", "bgplbs", CriteriaSpecification.LEFT_JOIN);
        criteria.add
        (
            Restrictions.or(Restrictions.isNotNull("bg.id"),Restrictions.isNotNull("bs.id"))
        );
        criteria.add
        (
            Restrictions.or
            (
                Restrictions.or
                (
                    Restrictions.and(Restrictions.isNull("bsplb.id"),Restrictions.eq("bspl.valid",true)),
                    Restrictions.and(Restrictions.isNull("bgplb.id"),Restrictions.eq("bgpl.valid",true))
                ),
                Restrictions.or(Restrictions.eq("bgplbs.id",510),Restrictions.eq("bsplbs.id",510))
            )

        );
        List list = criteria.list();
        return !(list == null || list.size() == 0);
    }

    public boolean isThereKodeMelliShenasayi(String kodeMelliShenasayi) {
//        List list = super.findByProperty(Shakhs.class, KODE_MELLI_SHENASAYI, kodeMelliShenasayi);
        Criteria criteria = getShakhsSearchCriteria(kodeMelliShenasayi,null);
        List list = criteria.list();
        return !(list == null || list.size() == 0);
    }

    public boolean isThere2KodeMelliShenasayi(String kodeMelliShenasayi) {
        Criteria criteria = getShakhsSearchCriteria(kodeMelliShenasayi,null);
        List list = criteria.list();
//        List list = super.findByProperty(Shakhs.class, KODE_MELLI_SHENASAYI, kodeMelliShenasayi);
        return !(list == null || list.size() == 0 || list.size() == 1);
    }

    private Criteria getShakhsSearchCriteria(String kodeMelliShenasayi,String halateElhaghie) {
        int criteriaSpecification=CriteriaSpecification.LEFT_JOIN;
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class, "s")                                                                                                                                  ;
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)                                                                                                                ;
        criteria.add(Restrictions.eq("s.kodeMelliShenasayi", kodeMelliShenasayi));
        criteria.createCriteria("s.bimeGozar", "bg", criteriaSpecification);
        criteria.createCriteria("s.bimeShode", "bs", criteriaSpecification);
        criteria.createCriteria("bs.pishnehadSet", "bspl", criteriaSpecification)                                                                                                   ;
        criteria.createCriteria("bg.pishnehadSet", "bgpl", criteriaSpecification)                                                                                                   ;
        criteria.createCriteria("bspl.bimename", "bsplb",criteriaSpecification)                                                                                                     ;
        criteria.createCriteria("bgpl.bimename", "bgplb", criteriaSpecification)                                                                                                    ;
        criteria.createCriteria("bsplb.state", "bsplbs", criteriaSpecification)                                                                                                     ;
        criteria.createCriteria("bgplb.state", "bgplbs", criteriaSpecification)                                                                                                     ;
        criteria.add
        (
            Restrictions.and
            (
//                Restrictions.or
//                (
                    Restrictions.or(Restrictions.eq("bspl.valid", true), Restrictions.isNull("bspl.valid")),
//                    Restrictions.and(Restrictions.eq("bspl.valid", false), Restrictions.eq("bsplbs.id", Constant.BIMENAME_INITIAL_STATE))
//                ),
//                Restrictions.or
//                (
                    Restrictions.or(Restrictions.eq("bgpl.valid", true), Restrictions.isNull("bgpl.valid"))
//                    Restrictions.and(Restrictions.eq("bgpl.valid", false), Restrictions.eq("bgplbs.id", Constant.BIMENAME_INITIAL_STATE))
//                )
            )
        );
        criteria.add(Restrictions.or(Restrictions.in("bsplbs.id", new Integer[]{Constant.BIMENAME_INITIAL_STATE, Constant.BIMENAME_LOCK_STATE}), Restrictions.isNull("bsplbs.id"))) ;
        criteria.add(Restrictions.or(Restrictions.in("bgplbs.id", new Integer[]{Constant.BIMENAME_INITIAL_STATE, Constant.BIMENAME_LOCK_STATE}), Restrictions.isNull("bgplbs.id"))) ;
//        if (halateElhaghie != null && halateElhaghie.equals("true"))
            criteria.add
            (
//                Restrictions.or
//                (
                    Restrictions.or(Restrictions.isNotNull("bg.id"),Restrictions.isNotNull("bs.id"))
//                    Restrictions.and(Restrictions.isNull("bg.id"), Restrictions.isNull("bs.id"))
//                )
            );
        return criteria;
    }


    public Shakhs searchShakhs(String kodeMell,boolean isHalateElhaghie) {
//Performance tuned
//        List list = super.findByProperty(Shakhs.class, KODE_MELLI_SHENASAYI, kodeMell);
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class,"s")
                .add(Restrictions.eq("s.kodeMelliShenasayi", kodeMell))
                .setCacheable(true);
        List list = criteria.list();
        if (list == null || list.size() == 0) return null;
        return (Shakhs) list.get(0);
    }
    public Shakhs searchOrphanPersonRecord(String kodeMell,boolean isHalateElhaghie)
    {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class,"s")
                .add(Restrictions.eq("s.kodeMelliShenasayi", kodeMell))
                .setCacheable(true);
        if(isHalateElhaghie)
        {
            criteria.createCriteria("s.bimeGozar", "bg", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("s.bimeShode", "bs", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bs.pishnehadSet", "bspl", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bg.pishnehadSet", "bgpl", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bspl.bimename", "bsplb", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bgpl.bimename", "bgplb", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bsplb.state", "bsplbs", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bgplb.state", "bgplbs", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bspl.oldDarkhastTaghiratList", "bspOldDarkhast", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bgpl.oldDarkhastTaghiratList", "bgpOldDarkhast", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bspl.oldElhaghiye", "bspOldElhaghie", CriteriaSpecification.LEFT_JOIN);
            criteria.createCriteria("bgpl.oldElhaghiye", "bgpOldElhaghie", CriteriaSpecification.LEFT_JOIN);
            criteria.add
            (
                Restrictions.and
                (
                    Restrictions.and
                    (
                        Restrictions.or(Restrictions.eq("bspl.valid", false), Restrictions.isNull("bspl.valid")),
                        Restrictions.and
                        (
                            Restrictions.and(Restrictions.isNull("bspOldDarkhast.id"), Restrictions.isNull("bspOldElhaghie.id")),
                            Restrictions.or(Restrictions.eq("bsplbs.id", Constant.BIMENAME_INITIAL_STATE),Restrictions.isNull("bsplb.id"))
                        )
                    ),
                    Restrictions.and
                    (
                        Restrictions.or(Restrictions.eq("bgpl.valid", false), Restrictions.isNull("bgpl.valid")),
                        Restrictions.and
                        (
                            Restrictions.and(Restrictions.isNull("bgpOldDarkhast.id"), Restrictions.isNull("bgpOldElhaghie.id")),
                            Restrictions.or(Restrictions.eq("bgplbs.id", Constant.BIMENAME_INITIAL_STATE), Restrictions.isNull("bsplb.id"))
                        )
                    )
                )
            );
        }
        List list = criteria.list();
        if (list == null || list.size() == 0) return null;
        return (Shakhs) list.get(0);
    }

    public List<Shakhs> searchAllShakhsByCodeMeli(String kodeMeli)
    {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Shakhs.class)
                .add(Restrictions.eq(KODE_MELLI_SHENASAYI, kodeMeli))
                .setCacheable(true);
        List list = criteria.list();
        if (list == null || list.size() == 0) return null;
        return list;
    }

    public boolean isAdamTaeed(String kodeMelliShenasayi) {
        Criteria shakhs = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Pishnehad.class, "p")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        shakhs.createCriteria("p.bimeShode", "b").createCriteria("b.shakhs", "s").createCriteria("p.state","st");
        shakhs.add(Restrictions.eq("s.kodeMelliShenasayi", kodeMelliShenasayi));
        shakhs.add(Restrictions.not(Restrictions.in("st.id", new Integer[]{250,270,290,300,360,320,350})));
        shakhs.add(Restrictions.eq("p.valid",true));
        shakhs.setCacheable(true);
        if(shakhs.list().size() > 0)
            return true;
        return false;
    }

    public boolean checkDarkhastTaghirForPerson(String kodeMelliShenasayi)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Darkhast.class,"d").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createCriteria("d.darkhastTaghirat","dt");
        criteria.createCriteria("d.elhaghiyeList","e", CriteriaSpecification.LEFT_JOIN);

        criteria.createCriteria("dt.oldPishnehad","dtOldPishnehad");
        criteria.createCriteria("dtOldPishnehad.bimename", "dtB").add(Restrictions.eq("dtB.state.id", 510));
        criteria.createCriteria("dtOldPishnehad.bimeShode", "dtBS");
        criteria.createCriteria("dtBS.shakhs","dtBsShakhs");
        criteria.createCriteria("dtOldPishnehad.bimeGozar","dtBG");
        criteria.createCriteria("dtBG.shakhs","dtBgShakhs");

        criteria.createCriteria("e.oldPishnehad","eOldPishnehad",CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("eOldPishnehad.bimeShode", "eBS", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("eBS.shakhs", "eBsShakhs", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("eOldPishnehad.bimeGozar", "eBG", CriteriaSpecification.LEFT_JOIN);
        criteria.createCriteria("eBG.shakhs", "eBgShakhs", CriteriaSpecification.LEFT_JOIN);

        criteria.add
        (
            Restrictions.or
            (
                Restrictions.or
                (
                    Restrictions.eq("dtBsShakhs.kodeMelliShenasayi", kodeMelliShenasayi),
                    Restrictions.eq("dtBgShakhs.kodeMelliShenasayi", kodeMelliShenasayi)
                ),
                Restrictions.or
                (
                    Restrictions.eq("eBsShakhs.kodeMelliShenasayi", kodeMelliShenasayi),
                    Restrictions.eq("eBgShakhs.kodeMelliShenasayi", kodeMelliShenasayi)
                )
            )
        );
        if (criteria.list().size() > 0)
            return true;
        return false;
    }

    public String isShakhsValid(String code_melli) {
        boolean result = isAdamTaeed(code_melli);
        if(result) return "YES";
        else return "NO";
    }

    public boolean isPersonEditable(boolean haghighi, String code) {
        boolean result = true;
        Criteria bimeShodeCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Bimename.class, "bimename")
                .createCriteria("bimename.pishnehadList", "pishnehad")
                .createCriteria("pishnehad.bimeShode", "bimeshode")
                .createCriteria("bimeshode.shakhs", "shakhs")
                .add(Restrictions.eq("pishnehad.valid",true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setCacheable(true);
        Criteria bimeGozarCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Bimename.class, "bimename")
                .createCriteria("bimename.pishnehadList", "pishnehad")
                .createCriteria("pishnehad.bimeGozar", "bimegozar")
                .createCriteria("bimegozar.shakhs", "shakhs")
                .add(Restrictions.eq("pishnehad.valid",true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setCacheable(true);
        if(haghighi) {
            bimeShodeCriteria.add(Restrictions.eq("shakhs.kodeMelliShenasayi", code));
            bimeGozarCriteria.add(Restrictions.eq("shakhs.kodeMelliShenasayi", code));
        } else {
            bimeShodeCriteria.add(Restrictions.eq("shakhs.shomareSabt", code));
            bimeGozarCriteria.add(Restrictions.eq("shakhs.shomareSabt", code));
        }
        if(bimeGozarCriteria.list().size() > 0 || bimeShodeCriteria.list().size() > 0)
            result = false;
        return result;
    }

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli, Shakhs.Role shakhsRole,List<Elhaghiye> elhaghiyeList ) {
        Criteria criteria = null;
        List<Integer> pishnehadIdList=new ArrayList<Integer>();
        if(elhaghiyeList!=null)
            for(Elhaghiye el : elhaghiyeList)
            {
                if(el.getOldPishnehad()!=null)
                {
                    pishnehadIdList.add(el.getOldPishnehad().getId());
                }
            }
        if(shakhsRole.equals(Shakhs.Role.BIMEGOZAR)) {
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Pishnehad.class, "p")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .createCriteria("p.bimeGozar", "bg")
                    .createCriteria("bg.shakhs", "bgs")
                    .createCriteria("p.bimename", "b", CriteriaSpecification.LEFT_JOIN)
                    .add(Restrictions.eq("bgs.kodeMelliShenasayi", kodeMelli)) ;
            if(pishnehadIdList.size()==0)
            {
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.isNull("b.id"),
                        Restrictions.and
                        (
                            Restrictions.eq("b.readyToShow", "yes"),
                            Restrictions.eq("b.state.id", 500)
                        )
                    )
                );
            }
            else
            {
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.isNull("b.id"),
                        Restrictions.or
                        (
                            Restrictions.and
                            (
                                Restrictions.eq("b.readyToShow", "yes"),
                                Restrictions.eq("b.state.id", 500)
                            ),
                                Restrictions.in("p.id",pishnehadIdList)
                        )
                    )
                );
            }

            criteria.add(Restrictions.eq("p.valid", true));
        } else if(shakhsRole.equals(Shakhs.Role.BIMESHODE)) {
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Pishnehad.class, "p")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .createCriteria("p.bimeShode", "bs")
                    .createCriteria("bs.shakhs", "bss")
                    .createCriteria("p.bimename", "b", CriteriaSpecification.LEFT_JOIN)
                    .add(Restrictions.eq("bss.kodeMelliShenasayi", kodeMelli));
            if (pishnehadIdList.size() == 0)
            {
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.isNull("b.id"),
                        Restrictions.and
                        (
                                Restrictions.eq("b.readyToShow", "yes"),
                                Restrictions.eq("b.state.id", 500)
                        )
                    )
                );
            }
            else
            {
                criteria.add
                (
                    Restrictions.or
                    (
                        Restrictions.isNull("b.id"),
                        Restrictions.or
                        (
                            Restrictions.and
                            (
                                Restrictions.eq("b.readyToShow", "yes"),
                                Restrictions.eq("b.state.id", 500)
                            ),
                            Restrictions.in("p.id", pishnehadIdList)
                        )
                    )
                );
            }

            criteria.add(Restrictions.eq("p.valid", true));
        }
        return criteria.list();
    }

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli) {
        ArrayList<Pishnehad> returnList = new ArrayList<Pishnehad>();
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Pishnehad.class, "p")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .createCriteria("p.bimeGozar", "bg")
                    .createCriteria("bg.shakhs", "bgs")
                    .createCriteria("p.bimename", "b", CriteriaSpecification.LEFT_JOIN)
                    .add(Restrictions.eq("bgs.kodeMelliShenasayi", kodeMelli))
                    .add(Restrictions.or(Restrictions.isNull("b.id"), Restrictions.eq("b.readyToShow", "yes")))
                    .add(Restrictions.eq("p.valid", true));
        returnList.addAll(criteria.list());
        criteria = getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Pishnehad.class, "p")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .createCriteria("p.bimeShode", "bs")
                    .createCriteria("bs.shakhs", "bss")
                    .createCriteria("p.bimename", "b", CriteriaSpecification.LEFT_JOIN)
                    .add(Restrictions.eq("bss.kodeMelliShenasayi", kodeMelli))
                    .add(Restrictions.or(Restrictions.isNull("b.id"), Restrictions.eq("b.readyToShow", "yes")))
                    .add(Restrictions.eq("p.valid", true));
                    List<Pishnehad> resultSet2 = (List < Pishnehad >)criteria.list();
        for(Pishnehad p : resultSet2) {
            boolean found = false;
            for(Pishnehad p2 : returnList) {
                if(p2.getId().equals(p.getId())) {
                    found = true;
                }
            }
            if(!found)
                returnList.add(p);
        }
        return returnList;
    }
}
