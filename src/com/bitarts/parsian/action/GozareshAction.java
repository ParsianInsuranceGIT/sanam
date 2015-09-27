package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.CashFlow;
import com.bitarts.parsian.model.asnadeSodor.LogPrintDaftarche;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.PishnehadSearch;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.viewModel.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 10/5/11
 * Time: 5:18 PM
 */
public class GozareshAction extends BaseAction implements ServletContextAware {
    private IGharardadService gharardadService;
    private ILoginService loginService;
    private IPishnehadService pishnehadService;
    private INamayandeService namayandeService;
    private IUserService userService;
    private boolean nosession;
    private List<BimenameForGozaresh> bimenameForGozareshList;
    private PaginatedListImpl<BimenameForGozaresh> bimenameForGozareshPaginatedList;
    private PaginatedListImpl<Bimename> bimenamePaginatedList;
    private PishnehadSearch pishnehadSearch;
    private AghsatMoavagh aghsatMoavagh;
    private BimenameForGozaresh bimenamehSearch;
    private List<Namayande> vahedSodurs;
    private List<Namayande> namayandegis;
    private List<User> bazaryabs, userSodors;
    private String[] noeGharardads;
    private List<Tarh> tarhs;
    private RankReport rankReport;
    private IAsnadeSodorService asnadeSodorService;
    private IDarkhastService darkhastService;
    private List<RankReport> rankReportList;
    private IConstantItemsService constantItemsService;
    private IConstantsService constantsService;
    private List<Namayande> namyandeList;
    private List<Gharardad> grouhHa;
    private PaginatedListImpl<PishnehadEstelam> pesPaginated;
    private PaginatedListImpl<RankReport> rankReportPaginatedList;
    private PaginatedListImpl<ZakhireRiaziVM> zakhireRiaziVMPgList;
    private List<ZakhireRiaziVM> zakhireRiaziVMList;
    private boolean prepareForGozaresh;
    private PaginatedListImpl<BatchTaghsitVM> batchTaghsitVMPgList;
    private BatchTaghsitVM batchTaghsitVMS;
    private Integer ghestBandiId;
    private List<LogPrintDaftarche> logPrintList;
    private List<Gharardad> gharardadList;
    private Long gharardadId;
    private List<AndukhteSaalAvaal> andukhteSaalAvaalList;
    private List<User> karshenasha;
    private PaginatedListImpl<GhestVam> gvPaginatedList;
    private PaginatedListImpl<ElhaghieVM> elhaghiePgList;
    private GhestVam gvs;
    private ElhaghieVM elhaghieVMS;

    public PaginatedListImpl<ElhaghieVM> getElhaghiePgList()
    {
        return elhaghiePgList;
    }

    public void setElhaghiePgList(PaginatedListImpl<ElhaghieVM> elhaghiePgList)
    {
        this.elhaghiePgList = elhaghiePgList;
    }

    public ElhaghieVM getElhaghieVMS()
    {
        return elhaghieVMS;
    }

    public void setElhaghieVMS(ElhaghieVM elhaghieVMS)
    {
        this.elhaghieVMS = elhaghieVMS;
    }

    public GhestVam getGvs()
    {
        return gvs;
    }

    public void setGvs(GhestVam gvs)
    {
        this.gvs = gvs;
    }

    public PaginatedListImpl<GhestVam> getGvPaginatedList()
    {
        return gvPaginatedList;
    }

    public void setGvPaginatedList(PaginatedListImpl<GhestVam> gvPaginatedList)
    {
        this.gvPaginatedList = gvPaginatedList;
    }

    public List<User> getKarshenasha()
    {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha)
    {
        this.karshenasha = karshenasha;
    }

    public void setServletContext(ServletContext servletContext) {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.userService = (IUserService) wac.getBean(IUserService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
        this.constantsService = (IConstantsService) wac.getBean("constantsService");
        this.gharardadService = (IGharardadService) wac.getBean(IGharardadService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean(IDarkhastService.SERVICE_NAME);
    }

    public List<AndukhteSaalAvaal> getAndukhteSaalAvaalList() {
        return andukhteSaalAvaalList;
    }

    public void setAndukhteSaalAvaalList(List<AndukhteSaalAvaal> andukhteSaalAvaalList) {
        this.andukhteSaalAvaalList = andukhteSaalAvaalList;
    }

    public Long getGharardadId() {
        return gharardadId;
    }

    public void setGharardadId(Long gharardadId) {
        this.gharardadId = gharardadId;
    }

    public PaginatedListImpl<PishnehadEstelam> getPesPaginated() {
        return pesPaginated;
    }

    public void setPesPaginated(PaginatedListImpl<PishnehadEstelam> pesPaginated) {
        this.pesPaginated = pesPaginated;
    }

    public String prepareGozaresheBordroyeSodor() {
        return SUCCESS;
    }


    private List<Pishnehad> pishnehadResultList;

    public List<Pishnehad> getPishnehadResultList() {
        return pishnehadResultList;
    }

    public void setPishnehadResultList(List<Pishnehad> pishnehadResultList) {
        this.pishnehadResultList = pishnehadResultList;
    }

    public List<Gharardad> getGharardadList() {
        gharardadList = gharardadService.findAll();
        return gharardadList;
    }

    public void setGharardadList(List<Gharardad> gharardadList) {
        this.gharardadList = gharardadList;
    }

    private ArrayList<PishnehadEstelam> pes;

    public String makeGozaresheBordroyeSodor() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(theUsername);
        if (pishnehadSearch.getBazarYabId() != null)
            pishnehadSearch.setBazarYab(userService.getUserById(pishnehadSearch.getBazarYabId()).getFullName());
        else pishnehadSearch.setBazarYab("");
        if (pishnehadSearch.getKarbareSaderKonnandehId() != null)
            pishnehadSearch.setKarbareSaderKonnandeh(userService.getUserById(pishnehadSearch.getKarbareSaderKonnandehId()).getFullName());
        else pishnehadSearch.setKarbareSaderKonnandeh("");
        if (pishnehadSearch.getVahedSodorId() != null)
            pishnehadSearch.setVahedSodor(namayandeService.getNamayandeById(pishnehadSearch.getVahedSodorId()).getName());
        else pishnehadSearch.setVahedSodor("");
        if (pishnehadSearch.getNamayandeId() != null)
            pishnehadSearch.setNamayande(namayandeService.getNamayandeById(pishnehadSearch.getNamayandeId()).getName());
        else pishnehadSearch.setNamayande("");
        if (user.hasRole("ROLE_NAMAYANDE")) {
            pishnehadSearch.setNamayande(user.getNamayandegi().getName());
            pishnehadSearch.setNamayandeId(user.getNamayandegi().getId());
            pishnehadSearch.setOstan("");
            pishnehadSearch.setShahr("");
            pishnehadSearch.setNoeBimename("");
            pishnehadSearch.setNoeGharardad("");
            pishnehadSearch.setTarh(new Tarh());
        }


//        pishnehadResultList = getPishnehadService().searchPishnehadForBordoyeSodour(pishnehadSearch);
//        List<PishnehadEstelam>peListTemp = pishnehadService.searchPishnehadForBordoyeSodour(pishnehadSearch);

        pesPaginated = new PaginatedListImpl<PishnehadEstelam>();

        if (isExport()) {
            pesPaginated.setPageNumber(0);
            pesPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            pesPaginated.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            pesPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
//        pesPaginated=PagingUtil.getPaginatedList(peListTemp);
        pesPaginated = pishnehadService.searchPishnehadForBordoyeSodourPaginated(pesPaginated, pishnehadSearch);
        pes = new ArrayList<PishnehadEstelam>();
        for (PishnehadEstelam pe : pesPaginated.getList()) {
            pe.setHaghBimeKhaalesFotYekja(NumberFormat.getNumberInstance().format(asnadeSodorService.getJamePoosheshAsliSadere(pe.getBimenameId())));
            pe.setJamSadere(NumberFormat.getNumberInstance().format(asnadeSodorService.getJameSadere(pe.getBimenameId())));
            pe.setHaghBimePardaakhti(NumberFormat.getNumberInstance().format(asnadeSodorService.getHagheBimeElamBeMaliSaleAvval(pe.getBimenameId())));
            pes.add(pe);
        }
        pesPaginated.setList(pes);
//        getSession().put("pes", pes);
//        getSession().put("pishnehadSearch", pishnehadSearch);
        return SUCCESS;
    }


    private Map<String, String> indicatorList;

    public Map<String, String> getIndicatorList() {
        if (indicatorList == null) {
            indicatorList = new LinkedHashMap<String, String>();
            for (Namayande.Indicator s : Namayande.Indicator.values()) {
                indicatorList.put(s.name(), Namayande.IndicatorFarsi(s));
            }
        }
        return indicatorList;
    }

    public void setIndicatorList(Map<String, String> indicatorList) {
        this.indicatorList = indicatorList;
    }

    public String prepareGozaresheSaleRanking() {
        rankReport = new RankReport();
        rankReport.setShakhes("TEDAD_BIMENAME");
        final String now = DateUtil.getCurrentDate();
        rankReport.setTaTarikh(now);
        rankReport.setAzTarikh(now);
        return SUCCESS;
    }

    private String charak1, charak3, tedadZirCharak1, tedadBalaCharak3, miangin, searchBarText;

//    public String makeGozaresheSaleRanking()
//    {
//        asnadeSodorService.findAllBedehis();
//        return SUCCESS;
//    }

    public String makeGozaresheSaleRanking() {
        LinkedList<String> search = new LinkedList<String>();

        final Namayande.Indicator indicator = Namayande.Indicator.valueOf(rankReport.getShakhes());
//        search.add("شاخص:" + Namayande.IndicatorFarsi(indicator) + " ");
//        search.add("از تاریخ:" + rankReport.getAzTarikh() + " ");
//        search.add("تا تاریخ:" + rankReport.getTaTarikh() + " ");
        final List<Namayande> list = namayandeService.searchNamayande(rankReport);
        final int count = list.size();
        rankReportList = new ArrayList<RankReport>(count);
        final List<Double> statics = new ArrayList<Double>(count);
        for (Namayande n : list) {
            n.calcRank(indicator, count, rankReport.getAzTarikh(), rankReport.getTaTarikh());
        }
        Collections.sort(list);
        tedadBalaCharak3 = tedadZirCharak1 = charak3 = charak1 = miangin = "";
        double miang = 0.0;
        for (Namayande n : list) {
            statics.add(n.getRankValue());
            miang = (miang + n.getRankValue()) / 2.0;
        }
        if (statics.size() > 0) {
            miangin = NumberFormat.getNumberInstance().format(miang);
            double charak1D = statics.get((int) Math.ceil(count / 2.0) - (int) Math.ceil(count / 4.0));
            charak1 = NumberFormat.getNumberInstance().format(charak1D);
            double charak3D = statics.get((int) Math.ceil(count / 2.0) + (int) Math.floor(count / 4.0) - 1);
            charak3 = NumberFormat.getNumberInstance().format(charak3D);
            int tedadZ = 0;
            int tedadB = 0;
            for (Double d : statics) {
                if (d < charak1D)
                    tedadZ++;
                if (d > charak3D)
                    tedadB++;
            }
            tedadZirCharak1 = String.valueOf(tedadZ);
            tedadBalaCharak3 = String.valueOf(tedadB);
        }
        for (Namayande n : list) {
            final RankReport rr = new RankReport();
            rr.setTedadNamayande(Integer.toString(namayandeService.findNamayandeOfVahedSodor(n.getId())));
            rr.setNamNamayande(n);
            try {
                rr.setVahedSodur(n.getVahedSodur());
                rr.setVahedSodurPedar(n.getVahedSodur().getVahedSodur());
            } catch (Exception ignore) {
            }
            rr.setOstan(n.getOstan());
            rr.setShahr(n.getShahr());
            final HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put(indicator.name(), NumberFormat.getNumberInstance().format(n.getRankValue()));
            for (Namayande.Indicator i : Namayande.Indicator.values()) {
                if (!tmp.containsKey(i.name())) {
                    n.calcRank(i, count, rankReport.getAzTarikh(), rankReport.getTaTarikh());
                    tmp.put(i.name(), NumberFormat.getNumberInstance().format(n.getRankValue()));
                }
                switch (i) {
                    case TEDAD_BIMENAME:
                        rr.setTedadBimenameSadere(tmp.get(i.name()));
                        break;
                    case SARANE_BIMENAME:
                        rr.setSaraneBimenameSadere(tmp.get(i.name()));
                        break;
                    case TEDAD_BIMENAME_FASKH:
                        rr.setTedadBimenameFaskh(tmp.get(i.name()));
                        break;
                    case SARANE_BIMENAME_FASKH:
                        rr.setSareneBimenameFaskh(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_SAL1:
                        rr.setHaghBimeSadere1(tmp.get(i.name()));
                        break;
                    case SARANE_BIME_SAL1:
                        rr.setSaraneHaghBimeSadere1(tmp.get(i.name()));
                        break;
                    case KOL_HAGH_BIME:
                        rr.setKolHaghBimeSadere(tmp.get(i.name()));
                        break;
                    case SARANE_HAGH_BIME:
                        rr.setSaraneHaghBimeSadere(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_PARDAKHT:
                        rr.setHaghBimeVosuliAzSadereByPardakht(tmp.get(i.name()));
                        break;
                    case SARANE_BIME_PARDAKHT:
                        rr.setSaraneHaghBimeVosuliAzSadereByPardakht(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_SANAD:
                        rr.setHaghBimeVosuliAzSadereBySanad(tmp.get(i.name()));
                        break;
                    case SARANE_BIME_SANAD:
                        rr.setSaraneHaghBimeVosuliAzSadereBySand(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_PARDAKHT_SAL1:
                        rr.setHaghBimeVosuliSal1ByPardakht(tmp.get(i.name()));
                        break;
                    case SARANE_BIME_PARDAKHT_SAL1:
                        rr.setSaraneHaghBimeVosuliSal1ByPardakh(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_SANAD_SAL1:
                        rr.setHaghBimeVosuliSal1BySanad(tmp.get(i.name()));
                        break;
                    case SARANE_BIME_SANAD_SAL1:
                        rr.setSaraneHaghBimeVosuliSal1BySanad(tmp.get(i.name()));
                        break;
                    case KOL_BIME_PARDAKHT:
                        rr.setKolHaghBimeVosuliByPardakht(tmp.get(i.name()));
                        break;
                    case SARANE_KOL_BIME_PARDAKHT:
                        rr.setSaraneKolHaghBimeVosuliByPardakht(tmp.get(i.name()));
                        break;
                    case KOL_BIME_SANAD:
                        rr.setKolHaghBimeVosuliBySanad(tmp.get(i.name()));
                        break;
                    case SARANE_KOL_BIME_SANAD:
                        rr.setSaraneKolHaghBimeVosuliBySanad(tmp.get(i.name()));
                        break;
                    case BARGASHTI_SAL1:
                        rr.setHaghBimeBargashtiSal1(tmp.get(i.name()));
                        break;
                    case SARANE_BARGASHTI_SAL1:
                        rr.setSaraneHaghBimeBargashtiSal1(tmp.get(i.name()));
                        break;
                    case KOL_BIME_BARGASHTI:
                        rr.setKolHaghBimeBargashti(tmp.get(i.name()));
                        break;
                    case SARANE_KOL_BIME_BARGASHTI:
                        rr.setSaraneKolHaghBimeBargashti(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_MOAVAGH_SAL1:
                        rr.setHaghBimeMoavagheSal1(tmp.get(i.name()));
                        break;
                    case SARANE_HAGH_BIME_MOAVAGH_SAL1:
                        rr.setSaraneHaghBimeMoavagheSal1(tmp.get(i.name()));
                        break;
                    case HAGH_BIME_MOAVAGH:
                        rr.setHaghBimeMoavaghe(tmp.get(i.name()));
                        break;
                    case SARANE_HAGH_BIME_MOAVAGH:
                        rr.setSaraneHaghBimeMoavaghe(tmp.get(i.name()));
                        break;
                    case BARGASHTI_BE_SADERE:
                        rr.setNesbatHaghBimeBargashtiSadere(tmp.get(i.name()));
                        break;
                    case MOAVAGH_BE_SADERE:
                        rr.setNesbatHaghBimeMoavagheSadere(tmp.get(i.name()));
                        break;
                    case SARMAYE_FOT:
                        rr.setSarmayeFot(tmp.get(i.name()));
                        break;
                    case ARZESHBAZKHARIDI:
                        rr.setArzeshBazKharidi(tmp.get(i.name()));
                        break;
                }
            }
            rr.setRankMap(tmp);
            rankReportList.add(rr);

        }
        rankReportPaginatedList = PagingUtil.getPaginatedList(rankReportList);
        searchBarText = StringUtil.join(search, "-");
        putToSession("rankReportList", rankReportList);
        putToSession("searchBarTextRanking", searchBarText);
        putToSession("mianginRanking", miangin);
        putToSession("charak1Ranking", charak1);
        putToSession("charak3Ranking", charak3);
        putToSession("zirCharak1Ranking", tedadZirCharak1);
        putToSession("balaCharak3Ranking", tedadBalaCharak3);
        return SUCCESS;
    }

    public String prepareGozaresheBordroyeMabaleghPardakhti() {
        prepareForGozaresh = true;
        return SUCCESS;
    }

    public String makeGozaresheBordroyeMablaghPardakhti() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theResult = loginService.findUserByUsername(theUsername);
        if (theResult.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (bimenamehSearch.getVahedSodurId() != null)
                bimenamehSearch.setVahedSodurId(namayandeService.getNamayandeById(bimenamehSearch.getVahedSodurId()).getId());
            else bimenamehSearch.setVahedSodur("");
            if (bimenamehSearch.getNamayandegiId() != null)
                bimenamehSearch.setNamayandegiId(namayandeService.getNamayandeById(bimenamehSearch.getNamayandegiId()).getId());
            else bimenamehSearch.setNamayandegi("");
            if (theResult.hasRole("ROLE_NAMAYANDE")) {
                bimenamehSearch.setNamayandegi(theResult.getNamayandegi().getName());
                bimenamehSearch.setNamayandegiId(theResult.getNamayandegi().getId());
                bimenamehSearch.setTarh(new Tarh());
//                bimenamehSearch.setg
            }
            List<BimenameForGozaresh> bimenameForGozareshListTemp = pishnehadService.getBimenameDAO().findByBimenameGozareshFilter(bimenamehSearch);
            bimenameForGozareshPaginatedList = PagingUtil.getPaginatedList(bimenameForGozareshListTemp);
            bimenameForGozareshList = new ArrayList<BimenameForGozaresh>();
            for (BimenameForGozaresh bimenameForGozaresh : bimenameForGozareshPaginatedList.getList()) {
//                bimenameForGozaresh.setHaghBimePoosheshhayeAsli(NumberFormat.getNumberInstance().format(asnadeSodorService.getJamePoosheshAsliSadere(bimenameForGozaresh.getBimenameId())));
//                bimenameForGozaresh.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(asnadeSodorService.getJamePoosheshhayeEzafiSadere(bimenameForGozaresh.getBimenameId())));
                if(!isExport())
                    bimenameForGozaresh.setJameHaghBimeSadere(NumberFormat.getNumberInstance().format(asnadeSodorService.getJameSadere(bimenameForGozaresh.getBimenameId())));
                else
                    bimenameForGozaresh.setJameHaghBimeSadere("محاسبه نشده");
                bimenameForGozareshList.add(bimenameForGozaresh);
            }
            bimenameForGozareshPaginatedList.setList(bimenameForGozareshList);
//            final List<KhateSanad> khateSanads = pishnehadService.getBimenameDAO().findByBimenameGozareshFilter(bimenamehSearch);
//            bimenameForGozareshList = new ArrayList<BimenameForGozaresh>();
//            for (KhateSanad c : khateSanads) {
//                BimenameForGozaresh bimenameForGozaresh = new BimenameForGozaresh();
//                bimenameForGozaresh.setOstan(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getOstan().getCityName());
//                bimenameForGozaresh.setShahr(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getShahr().getCityName());
//                if(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getVahedSodur() != null) {
//                    bimenameForGozaresh.setVahedSodur(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getVahedSodur().getName());
//                    bimenameForGozaresh.setKodVahedSodur(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getVahedSodur().getKodeNamayandeKargozar());
//                }
//                else {
//                    bimenameForGozaresh.setVahedSodur("-");
//                    bimenameForGozaresh.setKodVahedSodur("-");
//                }
//                bimenameForGozaresh.setNamNamayande(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getName());
//                bimenameForGozaresh.setKodNamayande(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar());
//                bimenameForGozaresh.setNoeBimename("بیمه نامه عمر و سرمایه گذاری");
//                bimenameForGozaresh.setShomareBimename(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimename().getShomare());
//                bimenameForGozaresh.setTarikhSodurBimename(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimename().getTarikhSodour());
//                bimenameForGozaresh.setSalBimeei(Integer.toString(Integer.parseInt(c.getBedehiCredebit().getGhest().getGhestBandi().getSaleBimei()) + 1));
//                bimenameForGozaresh.setNameBimeGozar(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName() + " " + (c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi() == null ? "" : c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi()));
//                bimenameForGozaresh.setNameBimeShode(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getName() + " " + c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi());
//                bimenameForGozaresh.setTarikhShoru(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimename().getTarikhShorou());
//                bimenameForGozaresh.setTarikhEngheza(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getBimename().getTarikhEngheza());
//                bimenameForGozaresh.setModatBimename(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getModat_bimename());
//                bimenameForGozaresh.setMaliat(c.getBedehiCredebit().getGhest().getMaliat());
//                bimenameForGozaresh.setSarmayeFot(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getSarmaye_paye_fot());
//                bimenameForGozaresh.setRaveshPardakhtBimename(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime());
//                bimenameForGozaresh.setNoeGharardad(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNoeGharardad());
//                bimenameForGozaresh.setTarh(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getTarh());
//                bimenameForGozaresh.setMablaghGhest(c.getBedehiCredebit().getAmount());
//                bimenameForGozaresh.setTarikhSarresidGhest(c.getBedehiCredebit().getGhest().getSarresidDate());
//                bimenameForGozaresh.setMablaghPardakhtiTavasotBimegozar(NumberFormat.getNumberInstance().format(Long.parseLong(c.getAmount().replace(",", "").trim())));
//                bimenameForGozaresh.setTarikhSodurGhabzResid(c.getSanad().getCreatedDate());
//                bimenameForGozaresh.setTarikhPardakhtGhest(c.getEtebarCredebit().getCreatedDate());
//                bimenameForGozaresh.setShomareGhabzResid(c.getSanad().getShomare());
//                bimenameForGozaresh.setHaghBimePoosheshhayeAsli(NumberFormat.getNumberInstance().format(asnadeSodorService.getJamePoosheshAsliSadere(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getId())));
//                bimenameForGozaresh.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(asnadeSodorService.getJamePoosheshhayeEzafiSadere(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getId())));
//                bimenameForGozaresh.setJameHaghBimeSadere(NumberFormat.getNumberInstance().format(asnadeSodorService.getJameSadere(c.getBedehiCredebit().getGhest().getGhestBandi().getBimename().getId())));
//                bimenameForGozareshList.add(bimenameForGozaresh);
//            }
//            bimenameForGozareshPaginatedList= PagingUtil.getPaginatedList(bimenameForGozareshList);
            return SUCCESS;
        }
    }

    public String prepareGozaresheBordroyeBazkharidShode() {
        prepareForGozaresh = true;
        return SUCCESS;
    }

    public String makeGozaresheBordroyeBazkharidShode() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theResult = loginService.findUserByUsername(theUsername);
        if (theResult.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (bimenamehSearch.getNamayandegiId() != null)
                bimenamehSearch.setNamayandegiId(namayandeService.getNamayandeById(bimenamehSearch.getNamayandegiId()).getId());
            else bimenamehSearch.setNamayandegi("");
            if (theResult.hasRole("ROLE_NAMAYANDE")) {
                bimenamehSearch.setNamayandegi(theResult.getNamayandegi().getName());
                bimenamehSearch.setNamayandegiId(theResult.getNamayandegi().getId());
            }

            bimenamePaginatedList = new PaginatedListImpl<Bimename>();
            bimenamePaginatedList = PagingUtil.getPaginatedList(pishnehadService.findByBimenameBazkharidShode(bimenamehSearch));
            return SUCCESS;
        }
    }

    public String prepareGozaresheBordroyeSarresidShode() {
        prepareForGozaresh = true;
        return SUCCESS;
    }

    public String makeGozaresheBordroyeSarresidShode() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theResult = loginService.findUserByUsername(theUsername);
        if (theResult.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (bimenamehSearch.getNamayandegiId() != null)
                bimenamehSearch.setNamayandegiId(namayandeService.getNamayandeById(bimenamehSearch.getNamayandegiId()).getId());
            else bimenamehSearch.setNamayandegi("");
            if (theResult.hasRole("ROLE_NAMAYANDE")) {
                bimenamehSearch.setNamayandegi(theResult.getNamayandegi().getName());
                bimenamehSearch.setNamayandegiId(theResult.getNamayandegi().getId());
            }

            bimenamePaginatedList = new PaginatedListImpl<Bimename>();
            bimenamePaginatedList = PagingUtil.getPaginatedList(pishnehadService.findByBimenameSarresidShode(bimenamehSearch));
            return SUCCESS;
        }
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public List<BimenameForGozaresh> getBimenameForGozareshList() {
        return bimenameForGozareshList;
    }

    public void setBimenameForGozareshList(List<BimenameForGozaresh> bimenameForGozareshList) {
        this.bimenameForGozareshList = bimenameForGozareshList;
    }

    public PishnehadSearch getPishnehadSearch() {
        return pishnehadSearch;
    }

    public void setPishnehadSearch(PishnehadSearch pishnehadSearch) {
        this.pishnehadSearch = pishnehadSearch;
    }

    public BimenameForGozaresh getBimenamehSearch() {
        return bimenamehSearch;
    }

    public void setBimenamehSearch(BimenameForGozaresh bimenamehSearch) {
        this.bimenamehSearch = bimenamehSearch;
    }

    private String sourceFilePath;
    private String destFileDIR;
    private String realPath;

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getDestFileDIR() {
        return destFileDIR;
    }

    public void setDestFileDIR(String destFileDIR) {
        this.destFileDIR = destFileDIR;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String printGozareshSodur() {
        try {
            pes = (ArrayList<PishnehadEstelam>) getSession().get("pes");
            pishnehadSearch = (PishnehadSearch) getSession().get("pishnehadSearch");
            LinkedList<String> searchBar = new LinkedList<String>();
            if (!pishnehadSearch.getOstan().isEmpty()) {
                searchBar.add(pishnehadSearch.getOstan());
                searchBar.add(pishnehadSearch.getShahr());
            }
            if (!pishnehadSearch.getVahedSodor().isEmpty())
                searchBar.add("واحد صدور:" + pishnehadSearch.getVahedSodor());
            if (!pishnehadSearch.getNamayande().isEmpty()) searchBar.add("نمایندگی:" + pishnehadSearch.getNamayande());
            if (!pishnehadSearch.getNoeGharardad().isEmpty())
                searchBar.add("نوع قرارداد:" + pishnehadSearch.getNoeGharardad());
            if (pishnehadSearch.getTarh() != null) searchBar.add("نام طرح:" + pishnehadSearch.getTarh().getName());
            if (!pishnehadSearch.getBazarYab().isEmpty()) searchBar.add("بازاریاب:" + pishnehadSearch.getBazarYab());
            if (!pishnehadSearch.getKarbareSaderKonnandeh().isEmpty())
                searchBar.add("کاربر صادرکننده:" + pishnehadSearch.getKarbareSaderKonnandeh());
            if (!pishnehadSearch.getAzTarikh().isEmpty()) searchBar.add("از تاریخ:" + pishnehadSearch.getAzTarikh());
            if (!pishnehadSearch.getTaTarikh().isEmpty()) searchBar.add("تا تاریخ:" + pishnehadSearch.getTaTarikh());
            if (!pishnehadSearch.getAzSarmayeFoat().isEmpty())
                searchBar.add("از سرمایه فوت:" + pishnehadSearch.getAzSarmayeFoat());
            if (!pishnehadSearch.getTaSarmayeFoat().isEmpty())
                searchBar.add("تا سرمایه فوت:" + pishnehadSearch.getTaSarmayeFoat());
            pishnehadSearch.setSearchBar(StringUtil.join(searchBar, "-"));
            destFileDIR = realPath + destFileDIR + "GozareshSodur.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/GozareshMablagh_subreport.jrxml", realPath + "report/GozareshMablagh_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printGozareshMabalegh() {
        try {
            bimenameForGozareshList = (List<BimenameForGozaresh>) getFromSession("bimenameForGozareshList");
            bimenamehSearch = (BimenameForGozaresh) getSession().get("bimenamehSearch");
            LinkedList<String> searchBar = new LinkedList<String>();
            if (!bimenamehSearch.getOstan().isEmpty()) {
                searchBar.add(bimenamehSearch.getOstan());
                searchBar.add(bimenamehSearch.getShahr());
            }
            if (!bimenamehSearch.getVahedSodur().isEmpty())
                searchBar.add("واحد صدور:" + bimenamehSearch.getVahedSodur());
            if (!bimenamehSearch.getNamayandegi().isEmpty())
                searchBar.add("نمایندگی:" + bimenamehSearch.getNamayandegi());
            if (!bimenamehSearch.getNoeGharardad().isEmpty())
                searchBar.add("نوع قرارداد:" + bimenamehSearch.getNoeGharardad());
            if (bimenamehSearch.getTarh() != null) searchBar.add("نام طرح:" + bimenamehSearch.getTarh());

            if (!bimenamehSearch.getAzTarikhPardakht().isEmpty())
                searchBar.add("از تاریخ:" + bimenamehSearch.getAzTarikhPardakht());
            if (!bimenamehSearch.getTaTarikhPardakht().isEmpty())
                searchBar.add("تا تاریخ:" + bimenamehSearch.getTaTarikhPardakht());
            bimenamehSearch.setSearchBar(StringUtil.join(searchBar, "-"));
            destFileDIR = realPath + destFileDIR + "GozareshMabaleghPardakhti.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/GozareshMabaleghPardakhti_subreport.jrxml", realPath + "report/GozareshMabaleghPardakhti_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public List<Namayande> getVahedSodurs() {
        if (vahedSodurs == null) vahedSodurs = namayandeService.getAllVahedSodurs();
        return vahedSodurs;
    }

    public void setVahedSodurs(List<Namayande> vahedSodurs) {
        this.vahedSodurs = vahedSodurs;
    }

    public List<Namayande> getNamayandegis() {
        if (namayandegis == null) namayandegis = namayandeService.getAllNamayande();
        return namayandegis;
    }

    public void setNamayandegis(List<Namayande> namayandegis) {
        this.namayandegis = namayandegis;
    }

    public String[] getNoeGharardads() {
        return Constant.NoeGharardad;
    }

    public void setNoeGharardads(String[] noeGharardads) {
        this.noeGharardads = noeGharardads;
    }

    public List<Tarh> getTarhs() {
        if (tarhs == null)
            tarhs = constantsService.listAllTarhs();
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public List<User> getUserSodors() {
        if (userSodors == null) {
            userSodors = mergeUserList(userSodors, 7);
            userSodors = mergeUserList(userSodors, 5);
            userSodors = mergeUserList(userSodors, 4);
        }
        return userSodors;
    }

    public List mergeUserList(List<User> userList, Integer roleId) {
        if (userList == null) return userService.findAllUserByRoleId(roleId);
        else {
            List<User> userSubList = userService.findAllUserByRoleId(roleId);
            for (User user : userSubList) {
                userList.add(user);
            }
            return userList;
        }
    }

    public void setUserSodors(List<User> userSodors) {
        this.userSodors = userSodors;
    }

    public List<User> getBazaryabs() {
        if (bazaryabs == null) bazaryabs = userService.findAllUserByRoleId(11);
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public RankReport getRankReport() {
        if (rankReport == null) rankReport = new RankReport();
        return rankReport;
    }

    public void setRankReport(RankReport rankReport) {
        this.rankReport = rankReport;
    }

    public List<RankReport> getRankReportList() {
        return rankReportList;
    }

    public void setRankReportList(List<RankReport> rankReportList) {
        this.rankReportList = rankReportList;
    }

    public ArrayList<PishnehadEstelam> getPes() {
        return pes;
    }

    public void setPes(ArrayList<PishnehadEstelam> pes) {
        this.pes = pes;
    }


    public String prepareGozaresheZakhireRiazi() {
        return SUCCESS;
    }

    public String prepareGozaresheZakhireRiaziTafkikGhest() {
        return SUCCESS;
    }

    private List<ZakhireRiazi> zakhireRiaziList;
    private ZakhireRiazi zakhireRiazi;

    public String makeGozaresheZakhireRiaziTafkikGhest() throws BimeNaamehVaSarmayehGozari.CustomValidationException, SQLException {
        final Logger logger = LoggerFactory.getLogger(GozareshAction.class);
//todo--------------------------------------- must use below code. . .--------------------------------------------------
//        zakhireRiaziVMPgList=new PaginatedListImpl<ZakhireRiaziVM>();
//        zakhireRiaziVMPgList.setPageNumber(0);
//        zakhireRiaziVMPgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        zakhireRiaziVMPgList=pishnehadService.makeGozareshZakhireRiazi(zakhireRiaziVMPgList,zakhireRiazi);
//----------------------------------------------------------------------------------------------------------------------

        List<ZakhireRiaziVM> bimenameIds = (List < ZakhireRiaziVM >)pishnehadService.findAllBimenameByFilterForZakhireTafkikGhest(zakhireRiazi.getTarikhMabna(), zakhireRiazi.getAzTarikh(), zakhireRiazi.getTaTarikh(), zakhireRiazi.getShomareBimename(), isExport(), true);
        logger.info(bimenameIds.size() + " id retrieved. ");
        List<Integer> bimename15Ids = new ArrayList<Integer>();
        int i = 0;
        for (ZakhireRiaziVM zr : bimenameIds) {
            if(!zr.getHijdahDarsad()) {
                bimename15Ids.add(zr.getBimenameId());
            }
            i++;
            if ((i > 29) && !isExport()) break;
        }

        Collections.sort(bimename15Ids);

        List<Integer>  uniqueBimename15Ids = new ArrayList<Integer>();
        for (Integer id : bimename15Ids){
                if (!uniqueBimename15Ids.contains(id))
                    uniqueBimename15Ids.add(id);
         }



        asnadeSodorService.updateAndukhteTajmi("1391/12/30","1392/12/29", CashFlow.SOUD, uniqueBimename15Ids);
        logger.info("15% andukhte & bazkharid done.");

        zakhireRiaziVMList = (List <ZakhireRiaziVM >)pishnehadService.findAllBimenameByFilterForZakhireTafkikGhest(zakhireRiazi.getTarikhMabna(), zakhireRiazi.getAzTarikh(), zakhireRiazi.getTaTarikh(), zakhireRiazi.getShomareBimename(), isExport(), false);
        logger.info(zakhireRiaziVMList.size() + " retrieved. ");
        i = 0;
        for (ZakhireRiaziVM zr : zakhireRiaziVMList) {
            if (zr.getHijdahDarsad()) {
                Bimename b = pishnehadService.findBimenameById(zr.getBimenameId());
                CredebitAction.mohasebeAndukhteBimename(b, zakhireRiazi.getTarikhMabna(),b.getCashFlow(zakhireRiazi.getTarikhMabna()));
                zr.setBimenameStateChangeDate(b.getTarikhBazkharid());
                zr.setAndukhteGhati(b.getAndukhteyeGhatie());
                zr.setBazkharidGhati(b.getArzeshBazkharidGhatie());
            }
            i++;
            if ((i > 29) && !isExport()) break;
        }
        i = 0;
        logger.info("18% andukhte and bazkharid done.");
        for (ZakhireRiaziVM zr : zakhireRiaziVMList) {
            if (zr.getBimenameStateChangeDate() != null && DateUtil.isGreaterThan(zr.getBimenameStateChangeDate(), zakhireRiazi.getTarikhMabna()) && zr.getBimenameStateId().equals(Constant.BIMENAME_BAZKHARID)) {
                if (zr.getAndukhteGhati() == null || zr.getAndukhteGhati() < 0) zr.setAndukhteGhati(0L);
                if (zr.getBazkharidGhati() == null || zr.getBazkharidGhati() < 0) zr.setBazkharidGhati(0L);
            } else {
                if (zr.getBimenameStateId() != null && !(zr.getBimenameStateId().equals(Constant.BIMENAME_INITIAL_STATE) || zr.getBimenameStateId().equals(Constant.BIMENAME_LOCK_STATE))) {
                    zr.setAndukhteGhati(0L);
                    zr.setBazkharidGhati(0L);
                }
                else if (zr.getAndukhteGhati() == null || zr.getAndukhteGhati() < 0) zr.setAndukhteGhati(0L);
            }
            i++;
            if ((i > 29) && !isExport()) break;
        }
        logger.info("finished.");
        putToSession("zakhireRiazi", zakhireRiazi);
        putToSession("zakhireRiaziVMList", zakhireRiaziVMList);
        return SUCCESS;
    }

    public String makeGozaresheAndukhteSaalAvaal() throws BimeNaamehVaSarmayehGozari.CustomValidationException{

        if (gharardadId != null){
            PaginatedListImpl<Pishnehad> pishnehadList = pishnehadService.gozareshAndukhteSaalAvaal(gharardadId);
            andukhteSaalAvaalList = new ArrayList<AndukhteSaalAvaal>(pishnehadList.getFullListSize());
            int i =0;
            for (Pishnehad pishnehad : pishnehadList.getList()){
                AndukhteSaalAvaal andukhteSaalAvaal = new AndukhteSaalAvaal();
                andukhteSaalAvaal.setFullNameBimeGozar(pishnehad.getBimeGozar().getShakhs().getFullName());
                andukhteSaalAvaal.setShomareBimename(pishnehad.getBimename().getShomare());
                //andukhteSaalAvaal.setHagheBimePayePardakhtiSaalAvaal(NumberFormat.getNumberInstance().format(asnadeSodorService.getHaghBimePayePardakhtiSaalAvval(pishnehad.getBimename().getId())));
                andukhteSaalAvaal.setKoleHagheBimePardakhtiSaalAvaal(NumberFormat.getNumberInstance().format(asnadeSodorService.getKoleHagheBimePardakhtiSaalAvval(pishnehad.getBimename().getId())));

                MohasebateTeory mohasebateTeory = new MohasebateTeory();
                List<TaghsitReport> taghsitReports = mohasebateTeory.taghsitReport(pishnehad, true, -1);
                if (taghsitReports != null && taghsitReports.size() > 0){
                    andukhteSaalAvaal.setHagheBimePayePardakhtiSaalAvaal(NumberFormat.getNumberInstance().format(taghsitReports.get(0).getfRs().getHaghBimePardaakhti()));
                    Double andukhteSarmaye15 = (taghsitReports.get(0).getfRs() != null) ?  taghsitReports.get(0).getfRs().getArzeshBazKharidBaaSudTazmini15Darsad() : 0;
                    andukhteSaalAvaal.setAndukhteTeoricPayanSaalAvaal(NumberFormat.getNumberInstance().format(Math.round(andukhteSarmaye15)));
                }

                CredebitAction.setAndukhteAlalHesabVaGhati(pishnehad.getBimename(), DateUtil.addYear(pishnehad.getBimename().getTarikhShorou(), 1),pishnehad.getBimename().getCashFlow(DateUtil.addYear(pishnehad.getBimename().getTarikhShorou(), 1)));
                andukhteSaalAvaal.setAndukhteAlalHesabPayanSaalAvaal(NumberFormat.getNumberInstance().format(pishnehad.getBimename().getAndukhteyeAlalHesab()));
                andukhteSaalAvaal.setAndukhteGhatiPayanSaalAvaal(NumberFormat.getNumberInstance().format(pishnehad.getBimename().getAndukhteyeGhatie()));

                andukhteSaalAvaalList.add(andukhteSaalAvaal);
                i++;
                if((i > 29) && !isExport()) break;
            }

        }

        putToSession("andukhteSaalAvaalList", andukhteSaalAvaalList);
        return SUCCESS;
    }

    public String makeGozaresheZakhireRiazi() throws SQLException {
        final Logger logger = LoggerFactory.getLogger(GozareshAction.class);
        if (zakhireRiazi.getVahedSodurId() != null)
            zakhireRiazi.setVahedSodur(namayandeService.getNamayandeById(zakhireRiazi.getVahedSodurId()).getName());
        else zakhireRiazi.setVahedSodur("");
        if (zakhireRiazi.getNamayandeId() != null)
            zakhireRiazi.setNamayande(namayandeService.getNamayandeById(zakhireRiazi.getNamayandeId()).getName());
        else zakhireRiazi.setNamayande("");
//todo--------------------------------------- must use below code. . .--------------------------------------------------
//        zakhireRiaziVMPgList=new PaginatedListImpl<ZakhireRiaziVM>();
//        zakhireRiaziVMPgList.setPageNumber(0);
//        zakhireRiaziVMPgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        zakhireRiaziVMPgList=pishnehadService.makeGozareshZakhireRiazi(zakhireRiaziVMPgList,zakhireRiazi);
//----------------------------------------------------------------------------------------------------------------------

        List<ZakhireRiaziVM> bimenameIds = (List < ZakhireRiaziVM >)pishnehadService.loadAllBimenameByFilterForZakhire(zakhireRiazi.getAzTarikh(), zakhireRiazi.getTaTarikh(), zakhireRiazi.getShomareBimename(), isExport(), true);
        logger.info(bimenameIds.size() + " id retrieved. ");
        asnadeSodorService.updateAndukhteTajmi(zakhireRiazi.getTarikhMabna(), bimenameIds);
        logger.info("andukhte & bazkharid done.");

        zakhireRiaziVMList = (List <ZakhireRiaziVM >)pishnehadService.loadAllBimenameByFilterForZakhire(zakhireRiazi.getAzTarikh(), zakhireRiazi.getTaTarikh(), zakhireRiazi.getShomareBimename(), isExport(), false);
        logger.info(zakhireRiaziVMList.size() + " retrieved. ");
        int i = 0;
        for (ZakhireRiaziVM zr : zakhireRiaziVMList) {
            if (zr.getBimenameStateChangeDate() != null && DateUtil.isGreaterThan(zr.getBimenameStateChangeDate(), zakhireRiazi.getTarikhMabna()) && zr.getBimenameStateId().equals(Constant.BIMENAME_BAZKHARID)) {
                if (zr.getAndukhteGhati() == null || zr.getAndukhteGhati() < 0) zr.setAndukhteGhati(0L);
                if (zr.getBazkharidGhati() == null || zr.getBazkharidGhati() < 0) zr.setBazkharidGhati(0L);
            } else {
                if (!(zr.getBimenameStateId().equals(Constant.BIMENAME_INITIAL_STATE) || zr.getBimenameStateId().equals(Constant.BIMENAME_LOCK_STATE))) {
                    zr.setAndukhteGhati(0L);
                    zr.setBazkharidGhati(0L);
                }
                else if (zr.getAndukhteGhati() == null || zr.getAndukhteGhati() < 0) zr.setAndukhteGhati(0L);
            }
            i++;
            if ((i > 29) && !isExport()) break;
        }
        logger.info("finished.");
        putToSession("zakhireRiazi", zakhireRiazi);
        putToSession("zakhireRiaziVMList", zakhireRiaziVMList);
        return SUCCESS;
    }

    public String printZakhireRiazi() {
        try {
            zakhireRiaziList = (List<ZakhireRiazi>) getFromSession("zakhireRiaziList");
            zakhireRiazi = (ZakhireRiazi) getSession().get("zakhireRiazi");
            LinkedList<String> searchBar = new LinkedList<String>();
            if (!zakhireRiazi.getOstan().isEmpty()) {
                searchBar.add(zakhireRiazi.getOstan());
                searchBar.add(zakhireRiazi.getShahr());
            }
            if (!zakhireRiazi.getVahedSodur().isEmpty())
                searchBar.add("واحد صدور:" + zakhireRiazi.getVahedSodur());
            if (!zakhireRiazi.getNamayande().isEmpty())
                searchBar.add("نمایندگی:" + zakhireRiazi.getNamayande());
            if (!zakhireRiazi.getNoeGharardad().isEmpty())
                searchBar.add("نوع قرارداد:" + zakhireRiazi.getNoeGharardad());
            if (zakhireRiazi.getTarh() != null) searchBar.add("نام طرح:" + zakhireRiazi.getTarh().getName());

            if (!zakhireRiazi.getAzTarikh().isEmpty())
                searchBar.add("از تاریخ:" + zakhireRiazi.getAzTarikh());
            if (!zakhireRiazi.getTaTarikh().isEmpty())
                searchBar.add("تا تاریخ:" + zakhireRiazi.getTaTarikh());
            if (!zakhireRiazi.getTarikhMabna().isEmpty())
                searchBar.add("تاریخ مبنا:" + zakhireRiazi.getTarikhMabna());
            zakhireRiazi.setSearchBar(StringUtil.join(searchBar, "-"));
            destFileDIR = realPath + destFileDIR + "GozareshRiazi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/GozareshRiazi_subreport.jrxml", realPath + "report/GozareshRiazi_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printAghsatMoavagh() {
        try {
//            aghsatMoavaghList = (List<AghsatMoavagh>) getFromSession("aghsatMoavaghList");
            aghsatMoavagh = (AghsatMoavagh) getSession().get("aghsatMoavagh");
            LinkedList<String> searchBar = new LinkedList<String>();
            if (aghsatMoavagh.getOstan() != null && !aghsatMoavagh.getOstan().isEmpty()) {
                searchBar.add(aghsatMoavagh.getOstan());
                searchBar.add(aghsatMoavagh.getShahr());
            }
            if (aghsatMoavagh.getVahedSodur() != null && !aghsatMoavagh.getVahedSodur().isEmpty())
                searchBar.add("واحد صدور:" + aghsatMoavagh.getVahedSodur());
            if (aghsatMoavagh.getNamayande() != null && !aghsatMoavagh.getNamayande().isEmpty())
                searchBar.add("نمایندگی:" + aghsatMoavagh.getNamayande());
            if (aghsatMoavagh.getNoeGharardad() != null && !aghsatMoavagh.getNoeGharardad().isEmpty())
                searchBar.add("نوع قرارداد:" + aghsatMoavagh.getNoeGharardad());
            if (aghsatMoavagh.getTarh().getName() != null && !aghsatMoavagh.getTarh().getName().isEmpty())
                searchBar.add("نام طرح:" + aghsatMoavagh.getTarh().getName());

            if (aghsatMoavagh.getAzTarikh() != null && !aghsatMoavagh.getAzTarikh().isEmpty())
                searchBar.add("از تاریخ صدور بیمه نامه:" + aghsatMoavagh.getAzTarikh());
            if (aghsatMoavagh.getTaTarikh() != null && !aghsatMoavagh.getTaTarikh().isEmpty())
                searchBar.add("تا تاریخ صدور بیمه نامه:" + aghsatMoavagh.getTaTarikh());
            if (aghsatMoavagh.getAzTarikhSarresid() != null && !aghsatMoavagh.getAzTarikhSarresid().isEmpty())
                searchBar.add("از تاریخ سررسید:" + aghsatMoavagh.getAzTarikhSarresid());
            if (aghsatMoavagh.getTaTarikhSarresid() != null && !aghsatMoavagh.getTaTarikhSarresid().isEmpty())
                searchBar.add("تا تاریخ سررسید:" + aghsatMoavagh.getTaTarikhSarresid());
            if (aghsatMoavagh.getTarikhMabna() != null && !aghsatMoavagh.getTarikhMabna().isEmpty())
                searchBar.add("تاریخ مبنا:" + aghsatMoavagh.getTarikhMabna());
            aghsatMoavagh.setSearchBar(StringUtil.join(searchBar, "-"));
            destFileDIR = realPath + destFileDIR + "GozareshAghsatMoavagh.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/GozareshAghsatMoavagh_subreport.jrxml", realPath + "report/GozareshAghsatMoavagh_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public IDarkhastService getDarkhastService()
    {
        return darkhastService;
    }

    public void setDarkhastService(IDarkhastService darkhastService)
    {
        this.darkhastService = darkhastService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public List<ZakhireRiazi> getZakhireRiaziList() {
        return zakhireRiaziList;
    }

    public void setZakhireRiaziList(List<ZakhireRiazi> zakhireRiaziList) {
        this.zakhireRiaziList = zakhireRiaziList;
    }

    public ZakhireRiazi getZakhireRiazi() {
        return zakhireRiazi;
    }

    public void setZakhireRiazi(ZakhireRiazi zakhireRiazi) {
        this.zakhireRiazi = zakhireRiazi;
    }

    public String prepareGozaresheAghsatMoavagh() {
        prepareForGozaresh = true;
        return SUCCESS;
    }

    private PaginatedListImpl<AghsatMoavagh> aghsatMoavaghListPaginated;

    public String makeGozaresheAghsatMoavagh() {
        if (aghsatMoavagh.getTaTarikhSarresid() == null || aghsatMoavagh.getTaTarikhSarresid().isEmpty())
            aghsatMoavagh.setTaTarikhSarresid(DateUtil.getCurrentDate());
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(theUsername);
        if (user.hasRole("ROLE_NAMAYANDE")) {
            aghsatMoavagh.setNamayande(user.getNamayandegi().getName());
            aghsatMoavagh.setNamayandeId(user.getNamayandegi().getId());
            aghsatMoavagh.getPishnehad().setGharardad(new Gharardad());
            aghsatMoavagh.setTarh(new Tarh());
        }
        aghsatMoavaghListPaginated = asnadeSodorService.findAllAghsatMoavagh(aghsatMoavagh);

        return SUCCESS;
    }

    public PaginatedListImpl<AghsatMoavagh> getAghsatMoavaghListPaginated() {
        return aghsatMoavaghListPaginated;
    }

    public void setAghsatMoavaghListPaginated(PaginatedListImpl<AghsatMoavagh> aghsatMoavaghListPaginated) {
        this.aghsatMoavaghListPaginated = aghsatMoavaghListPaginated;
    }

    public AghsatMoavagh getAghsatMoavagh() {
        return aghsatMoavagh;
    }

    public void setAghsatMoavagh(AghsatMoavagh aghsatMoavagh) {
        this.aghsatMoavagh = aghsatMoavagh;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public String getCharak1() {
        return charak1;
    }

    public void setCharak1(String charak1) {
        this.charak1 = charak1;
    }

    public String getCharak3() {
        return charak3;
    }

    public void setCharak3(String charak3) {
        this.charak3 = charak3;
    }

    public String getTedadZirCharak1() {
        return tedadZirCharak1;
    }

    public void setTedadZirCharak1(String tedadZirCharak1) {
        this.tedadZirCharak1 = tedadZirCharak1;
    }

    public String getTedadBalaCharak3() {
        return tedadBalaCharak3;
    }

    public void setTedadBalaCharak3(String tedadBalaCharak3) {
        this.tedadBalaCharak3 = tedadBalaCharak3;
    }

    public String getMiangin() {
        return miangin;
    }

    public void setMiangin(String miangin) {
        this.miangin = miangin;
    }

    public String printGozareshRanking() {
        try {
            rankReportList = (List<RankReport>) getFromSession("rankReportList");
            miangin = (String) getFromSession("mianginRanking");
            charak1 = (String) getFromSession("charak1Ranking");
            charak3 = (String) getFromSession("charak3Ranking");
            tedadZirCharak1 = (String) getFromSession("zirCharak1Ranking");
            tedadBalaCharak3 = (String) getFromSession("balaCharak3Ranking");
            if (rankReportList == null) return ERROR;
            searchBarText = (String) getFromSession("searchBarTextRanking");
            destFileDIR = realPath + destFileDIR + "GozareshRanking.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/GozareshRanking_subreport.jrxml", realPath + "report/GozareshRanking_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String batchTaghsitReport()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        batchTaghsitVMPgList=new PaginatedListImpl<BatchTaghsitVM>();
        if (isExport())
        {
            batchTaghsitVMPgList.setPageNumber(0);
            batchTaghsitVMPgList.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else
        {
//            batchTaghsitVMPgList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            batchTaghsitVMPgList.setPageNumber(0);
            batchTaghsitVMPgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        if(batchTaghsitVMS==null)
        {
            batchTaghsitVMS=new BatchTaghsitVM();
        }
        Role namayandeRole= new Role();
        namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
        if(user.getRoles().contains(namayandeRole))
        {
            batchTaghsitVMS.setNamayandeId(user.getNamayandegi().getId());
        }
        batchTaghsitVMPgList = asnadeSodorService.findBatchTaghsit(batchTaghsitVMPgList,batchTaghsitVMS)  ;
        for(BatchTaghsitVM bt: batchTaghsitVMPgList.getList())
        {
            bt.setAghsatPardakhtiCount(Long.toString(asnadeSodorService.countAghsatePardakhti(bt.getBimenameId())));
        }
        grouhHa=getGrouhHa();
        karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
        return SUCCESS;
    }

    public String logsDaftarche()
    {
        logPrintList=asnadeSodorService.findAllLogPrinteDaftarcheByGhestBandiId(ghestBandiId);
        return SUCCESS;
    }

    public String aghsatVamReport()
    {
        gvPaginatedList=new PaginatedListImpl<GhestVam>();
        if (isExport())
        {
            gvPaginatedList.setPageNumber(0);
            gvPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        }

        else
        {
           gvPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
           gvPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        gvPaginatedList = asnadeSodorService.aghsatVamReport(gvPaginatedList,gvs);

        return SUCCESS;
    }

    public String prepareAghsatVamReport()
    {
        return SUCCESS;
    }

    public String prepareElhaghieReport()
    {
        return SUCCESS;
    }

    public String elhaghieReport()
    {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(theUsername);

        elhaghiePgList = new PaginatedListImpl<ElhaghieVM>();
        if (isExport())
        {
            elhaghiePgList.setPageNumber(0);
            elhaghiePgList.setObjectsPerPage(Integer.MAX_VALUE);
        }

        else
        {
            elhaghiePgList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            elhaghiePgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        if (user.hasRole("ROLE_NAMAYANDE"))
        {
            elhaghieVMS.setNamayandeId(user.getNamayandegi().getId());
        }
        elhaghiePgList= darkhastService.elhaghieReport(elhaghiePgList, elhaghieVMS);
        return SUCCESS;
    }

    public String prepareKhesaratReport()
    {
        return SUCCESS;
    }

    public String khesaratReport()
    {
        return SUCCESS;
    }

    public List<LogPrintDaftarche> getLogPrintList()
    {
        return logPrintList;
    }

    public void setLogPrintList(List<LogPrintDaftarche> logPrintList)
    {
        this.logPrintList = logPrintList;
    }

    public Integer getGhestBandiId()
    {
        return ghestBandiId;
    }

    public void setGhestBandiId(Integer ghestBandiId)
    {
        this.ghestBandiId = ghestBandiId;
    }

    public BatchTaghsitVM getBatchTaghsitVMS()
    {
        return batchTaghsitVMS;
    }

    public void setBatchTaghsitVMS(BatchTaghsitVM batchTaghsitVMS)
    {
        this.batchTaghsitVMS = batchTaghsitVMS;
    }

    public PaginatedListImpl<BatchTaghsitVM> getBatchTaghsitVMPgList()
    {
        return batchTaghsitVMPgList;
    }

    public void setBatchTaghsitVMPgList(PaginatedListImpl<BatchTaghsitVM> batchTaghsitVMPgList)
    {
        this.batchTaghsitVMPgList = batchTaghsitVMPgList;
    }

    public String getSearchBarText() {
        return searchBarText;
    }

    public void setSearchBarText(String searchBarText) {
        this.searchBarText = searchBarText;
    }

    public List<Namayande> getNamyandeList() {
        if (namyandeList == null) namyandeList = namayandeService.getAllNamayandegi();
        return namyandeList;
    }

    public void setNamyandeList(List<Namayande> namyandeList) {
        this.namyandeList = namyandeList;
    }

    public List<Gharardad> getGrouhHa() {
        if (grouhHa == null) {
            grouhHa = gharardadService.findAll();
        }
        return grouhHa;
    }

    public void setGrouhHa(List<Gharardad> grouhHa) {
        this.grouhHa = grouhHa;
    }

    public PaginatedListImpl<BimenameForGozaresh> getBimenameForGozareshPaginatedList() {
        return bimenameForGozareshPaginatedList;
    }

    public void setBimenameForGozareshPaginatedList(PaginatedListImpl<BimenameForGozaresh> bimenameForGozareshPaginatedList) {
        this.bimenameForGozareshPaginatedList = bimenameForGozareshPaginatedList;
    }

    public PaginatedListImpl<RankReport> getRankReportPaginatedList() {
        return rankReportPaginatedList;
    }

    public void setRankReportPaginatedList(PaginatedListImpl<RankReport> rankReportPaginatedList) {
        this.rankReportPaginatedList = rankReportPaginatedList;
    }

    public boolean isPrepareForGozaresh() {
        return prepareForGozaresh;
    }

    public void setPrepareForGozaresh(boolean prepareForGozaresh) {
        this.prepareForGozaresh = prepareForGozaresh;
    }

    public PaginatedListImpl<ZakhireRiaziVM> getZakhireRiaziVMPgList()
    {
        return zakhireRiaziVMPgList;
    }

    public void setZakhireRiaziVMPgList(PaginatedListImpl<ZakhireRiaziVM> zakhireRiaziVMPgList)
    {
        this.zakhireRiaziVMPgList = zakhireRiaziVMPgList;
    }

    public List<ZakhireRiaziVM> getZakhireRiaziVMList() {
        return zakhireRiaziVMList;
    }

    public void setZakhireRiaziVMList(List<ZakhireRiaziVM> zakhireRiaziVMList) {
        this.zakhireRiaziVMList = zakhireRiaziVMList;
    }

    public PaginatedListImpl<Bimename> getBimenamePaginatedList() {
        return bimenamePaginatedList;
    }

    public void setBimenamePaginatedList(PaginatedListImpl<Bimename> bimenamePaginatedList) {
        this.bimenamePaginatedList = bimenamePaginatedList;
    }
}
