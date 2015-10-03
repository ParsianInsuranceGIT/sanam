package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.BankInfo;
import com.bitarts.parsian.model.Daftar;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.LogMosharekat;
import com.bitarts.parsian.model.management.Hesab;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.TaeenAndukhteBarMabnaayeHaghBimePardaakhti;
import com.bitarts.parsian.service.epayment.PgwStatus;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.bitarts.parsian.viewModel.CredebitVM;
import com.bitarts.parsian.viewModel.MaliNamayande;
import com.bitarts.parsian.viewModel.bedehiTasviyeNashode;
import com.bitarts.parsian.webservice.ach.ACHPayment;
import com.bitarts.parsian.webservice.epayment.EShopServiceLocator;
import com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType;
import com.opensymphony.xwork2.ActionContext;
import org.apache.axis.holders.UnsignedByteHolder;
import org.apache.axis.types.UnsignedByte;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.xml.rpc.holders.LongHolder;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:35 PM
 */
public class CredebitAction extends BaseAction implements ServletContextAware {

    private IAsnadeSodorService asnadeSodorService;
    private IPishnehadService pishnehadService;
    private ILoginService loginService;
    private INamayandeService namayandeService;
    private ISequenceService sequenceService;
    private List<Sanad> sanadList;
    private Credebit credebit;
    private String pishnehadId;
    private List<DaryafteCheck> daryafthayeCheck;
    private List<Hesab> hesabha;
    private DaryafteCheck daryafteCheck;
    private Mosharekat mosharekatDarManafe;
    private boolean nosession;
    private List<Bimename> bimenameha;
    private Bimename bimename;
    private PaginatedListImpl<Bimename> paginatedListBimename;
    private Long mablagheMosharekat;
    private ILogMosharekatService logMosharekatService;
    private String tarikhMabna;
    private boolean mohasebeForAll = false;
    private PaginatedListImpl<Credebit> credebitListPaginated;
    private PaginatedListImpl<Credebit> bedehiCredebitListPaginated;
    private PaginatedListImpl<Credebit> etebarCredebitListPaginated;
    private PaginatedListImpl<Credebit> etebarMoghayeratCredebitListPaginated;
    private PaginatedListImpl<CredebitVM> bedehiVosulNashodeCredebitListPaginated;
    private PaginatedListImpl<MaliNamayande> namayandeListPaginated;
    private PaginatedListImpl<Vagozari> vagozariPaginatedList;
    private Integer loadedIdEtebarat;

    private Credebit.CredebitMainType credebitNoe;
    private String identifier;
    private String shomareMoshtari;
    private String rcptName;
    private String sarresidDate;
    private String sarresidDateFrom;
    private String sarresidDateTo;
    private String createdDate;
    private String createdDateFrom;
    private String createdDateTo;
    private String amount;
    private String shomareShaba;
    private String paidReceivedAmount;
    private String remainingAmount;
    private Credebit.CredebitType credebitTypeFarsi;
    private String shomareGharardad;
    private Credebit.Status statusFarsi;
    private Credebit.VaziyatVosoul vosoulStateFarsi;
    private Long namayandeId;
    private Long search_namayandegiIdEtebarat;
    private Long search_namayandegiIdEtebarat3;
    private Long search_namayandegiIdEtebarat4;
    private Long search_namayandegiId;
    private Long search_vahedesodorId;
    private String bankName;
    private InputStream deleteEtebarAjax;
    private InputStream checkUsernameAjax;
    private Integer credebitId;
    private String credebitIds;
    private ServletContext servletContext;
    private String authorityId, bankResponse;
    private Sanad sanad;
    private String vosoulDesc;
    private PaginatedListImpl<BankInfo> bankInfoPaginatedList;
    private Integer formSanadId;
    private String subsystem_field;
    List<Credebit> bedehiList;
    List<Credebit> etebarList;
    private Integer bedehiId;
    private String epaymentHost;
    private InputStream inputStream;
    private String codeMelli;
    private String creebitAmount;
    private Integer sanadId;
    private String shomareHesab,shomareCheck,checkSerri,branchCode,accountOwnerName;
    private String searchPage = "";
    private String flagPage;
    private String shomareFish;

    //b-h
    private PaginatedListImpl<Credebit> ParsianCredebitsListPaginated;
    private Integer bedehiId_ForEnteghal;

    //b-h
    private Long bazaryabSanamId;
    //b-h
    private PaginatedListImpl<bedehiTasviyeNashode> bedehiTasviyeNashodePaginatedList;
    //b-h
    private Long search_vahedesodorId5;
    private Long search_namayandegiId5;
    private Long bazaryabSanamIdBedehi;

    //b-h
    private String bedehi_color;
    private int    consortium;
    private int reshte;
    private String todayDate;
    private String twoDaysLaterDate;

    public int getConsortium() {
        return consortium;
    }

    public void setConsortium(int consortium) {
        this.consortium = consortium;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getTwoDaysLaterDate() {
        return twoDaysLaterDate;
    }

    public void setTwoDaysLaterDate(String twoDaysLaterDate) {
        this.twoDaysLaterDate = twoDaysLaterDate;
    }

    public String getBedehi_color() {
        return bedehi_color;
    }

    public void setBedehi_color(String bedehi_color) {
        this.bedehi_color = bedehi_color;
    }

    public int getReshte() {
        return reshte;
    }

    public void setReshte(int reshte) {
        this.reshte = reshte;
    }

    public Long getBazaryabSanamIdBedehi() {
        return bazaryabSanamIdBedehi;
    }

    public void setBazaryabSanamIdBedehi(Long bazaryabSanamIdBedehi) {
        this.bazaryabSanamIdBedehi = bazaryabSanamIdBedehi;
    }

    public Long getSearch_vahedesodorId5() {
        return search_vahedesodorId5;
    }

    public void setSearch_vahedesodorId5(Long search_vahedesodorId5) {
        this.search_vahedesodorId5 = search_vahedesodorId5;
    }

    public Long getSearch_namayandegiId5() {
        return search_namayandegiId5;
    }

    public void setSearch_namayandegiId5(Long search_namayandegiId5) {
        this.search_namayandegiId5 = search_namayandegiId5;
    }

    public PaginatedListImpl<bedehiTasviyeNashode> getBedehiTasviyeNashodePaginatedList() {
        return bedehiTasviyeNashodePaginatedList;
    }

    public void setBedehiTasviyeNashodePaginatedList(PaginatedListImpl<bedehiTasviyeNashode> bedehiTasviyeNashodePaginatedList) {
        this.bedehiTasviyeNashodePaginatedList = bedehiTasviyeNashodePaginatedList;
    }

    public Long getBazaryabSanamId() {
        return bazaryabSanamId;
    }

    public void setBazaryabSanamId(Long bazaryabSanamId) {
        this.bazaryabSanamId = bazaryabSanamId;
    }

    public Integer getBedehiId_ForEnteghal() {
        return bedehiId_ForEnteghal;
    }

    public void setBedehiId_ForEnteghal(Integer bedehiId_ForEnteghal) {
        this.bedehiId_ForEnteghal = bedehiId_ForEnteghal;
    }




    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
        this.logMosharekatService = (ILogMosharekatService) wac.getBean(ILogMosharekatService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.servletContext = servletContext;
    }

    public String bargashteCheck() {
        DaryafteCheck theCheck = asnadeSodorService.findDaryafteCheckById(daryafteCheck.getId());
        theCheck.setStatus(DaryafteCheck.Status.MOSTARAD);
        asnadeSodorService.updateDaryafteCheck(theCheck);
        daryafthayeCheck = asnadeSodorService.findAllDaryafteChecks();
        hesabha = asnadeSodorService.findAllHesabs();
        return SUCCESS;
    }

    public String vosouleCheck() {
        DaryafteCheck theCheck = asnadeSodorService.findDaryafteCheckById(daryafteCheck.getId());
        theCheck.setStatus(DaryafteCheck.Status.VOSUL);
        asnadeSodorService.updateDaryafteCheck(theCheck);
        daryafthayeCheck = asnadeSodorService.findAllDaryafteChecks();
        hesabha = asnadeSodorService.findAllHesabs();
        return SUCCESS;
    }

    public String varizBeHesab() {
        DaryafteCheck theCheck = asnadeSodorService.findDaryafteCheckById(daryafteCheck.getId());
        //theCheck.setStatus(DaryafteCheck.Status.VARIZ_SHODE_BE_HESAB);
        theCheck.setStatus(DaryafteCheck.Status.VOSUL);
        theCheck.setHesabeVarizi(daryafteCheck.getHesabeVarizi());
        asnadeSodorService.updateDaryafteCheck(theCheck);
        daryafthayeCheck = asnadeSodorService.findAllDaryafteChecks();
        hesabha = asnadeSodorService.findAllHesabs();
        return SUCCESS;
    }

    public String listAllEtebaratCheck() {
        daryafthayeCheck = asnadeSodorService.findAllDaryafteChecks();
        hesabha = asnadeSodorService.findAllHesabs();
        return SUCCESS;
    }

//    public String viewCredebits() {
//        sanadList = asnadeSodorService.findAllSanads();
//        return SUCCESS;
//    }

    public String prepareForMohasebeMosharekat() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        if (resultUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (mosharekatDarManafe != null) {
                mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
            } else {
                mosharekatDarManafe = new Mosharekat();
                mosharekatDarManafe.setShomareMosharekat(sequenceService.nextShomareMosharekat());
            }
            return SUCCESS;
        }
    }

    public String mohasebeMosharekat() {
        mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
        paginatedListBimename = new PaginatedListImpl<Bimename>();
        paginatedListBimename.setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
        paginatedListBimename.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        if(isExport() || mohasebeForAll)
        {
            paginatedListBimename.setPageNumber(0);
            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
        }
        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
        bimenameha = paginatedListBimename.getList();
        ArrayList<Flow> flowhaayeShoroueDowre = new ArrayList<Flow>();
        ArrayList<Flow> flowhaayePayaneDowre = new ArrayList<Flow>();
        ArrayList<Flow> flowhaayeMianeDowre = new ArrayList<Flow>();
        Hashtable<Bimename, ArrayList<Flow>> flowhaayeShoroueDowreBimename = new Hashtable<Bimename, ArrayList<Flow>>();
        Hashtable<Bimename, ArrayList<Flow>> flowhaayePayaneDowreBimename = new Hashtable<Bimename, ArrayList<Flow>>();
        Hashtable<Bimename, ArrayList<Flow>> flowhaayeMianeDowreBimename = new Hashtable<Bimename, ArrayList<Flow>>();
        ArrayList<Bimename> newBimename = new ArrayList<Bimename>();
        for (Bimename bimename : bimenameha) {
            mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhPayanDowre(),bimename.getCashFlow(mosharekatDarManafe.getTarikhPayanDowre()));
            if(bimename.getAndukhteyeGhatie() > 0) {
                newBimename.add(bimename);
            }
        }
        for (Bimename bimename : newBimename) {
            ArrayList<Flow> f1 = new ArrayList<Flow>();
            ArrayList<Flow> f2 = new ArrayList<Flow>();
            ArrayList<Flow> f3 = new ArrayList<Flow>();
            flowhaayeShoroueDowreBimename.put(bimename, f1);
            flowhaayePayaneDowreBimename.put(bimename, f2);
            flowhaayeMianeDowreBimename.put(bimename, f3);
        }
        for (Bimename bimename : newBimename) {
            CashFlow result = bimename.getCashFlow(DateUtil.getCurrentDate());
//            CashFlow result = asnadeSodorService.getBimenameCashFlow(bimename, DateUtil.getCurrentDate());
            for(Flow flow : result.getFlows()) {
                if (!DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhShoruDowre())) {
                    flowhaayeShoroueDowre.add(flow);
                    ArrayList<Flow> f = flowhaayeShoroueDowreBimename.get(bimename);
                    f.add(flow);
                    flowhaayeShoroueDowreBimename.remove(bimename);
                    flowhaayeShoroueDowreBimename.put(bimename, f);
                }
                if (DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhShoruDowre()) && !DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhPayanDowre())) {
                    flowhaayeMianeDowre.add(flow);
                    ArrayList<Flow> f = flowhaayeMianeDowreBimename.get(bimename);
                    f.add(flow);
                    flowhaayeMianeDowreBimename.remove(bimename);
                    flowhaayeMianeDowreBimename.put(bimename, f);
                }
                if (!DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhPayanDowre())) {
                    flowhaayePayaneDowre.add(flow);
                    ArrayList<Flow> f = flowhaayePayaneDowreBimename.get(bimename);
                    f.add(flow);
                    flowhaayePayaneDowreBimename.remove(bimename);
                    flowhaayePayaneDowreBimename.put(bimename, f);
                }
            }
        }

        CashFlow shoroueDowre = new CashFlow(flowhaayeShoroueDowre);
        CashFlow payaneDowre = new CashFlow(flowhaayePayaneDowre);
        CashFlow mianDowre = new CashFlow(flowhaayeMianeDowre);

        if(mosharekatDarManafe.getSoud() == null || mosharekatDarManafe.getSoud().length() == 0) {
            Double soudeMosharekat = calculateNewtonlySoudeMosharekat(mosharekatDarManafe, shoroueDowre, payaneDowre, mianDowre);
            mosharekatDarManafe.setSoud(soudeMosharekat.toString());
            asnadeSodorService.updateMosharekatDarManafe(mosharekatDarManafe);
        }
        Double soudDouble = Double.parseDouble(mosharekatDarManafe.getSoud());
        for (Bimename bimename : bimenameha) {
            bimename.setMablaghMosharekatDummy(0L);
        }
        for (Bimename bimename : newBimename) {
            bimename.setMablaghMosharekatDummy(calcMablaghMosharekat(mosharekatDarManafe, new CashFlow(flowhaayeShoroueDowreBimename.get(bimename)),
                    new CashFlow(flowhaayePayaneDowreBimename.get(bimename)), new CashFlow(flowhaayeMianeDowreBimename.get(bimename)), soudDouble));
            mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhMabnaMohasebe(),bimename.getCashFlow(mosharekatDarManafe.getTarikhMabnaMohasebe()));
        }
        pishnehadService.updateBimename(newBimename);
        putToSession("enableMosharekat2", true);
//        initPaginatedList(mosharekatDarManafe);
//        paginatedListBimename.setList(newBimename);
//        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
        return SUCCESS;
    }

    private void initPaginatedList(Mosharekat m) {
        setPaginatedListBimename(new PaginatedListImpl<Bimename>());
        getPaginatedListBimename().setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
        getPaginatedListBimename().setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        if(isExport()) {
            getPaginatedListBimename().setPageNumber(0);
            getPaginatedListBimename().setObjectsPerPage(Integer.MAX_VALUE);
        }
        setPaginatedListBimename(pishnehadService.findAllBimenamePaginatedForMosharekat(null, getPaginatedListBimename(), m.getTarikhShoruDowre(), m.getTarikhPayanDowre()));
    }

    public String sabteMosharekat() {
//        try {
//            mohasebeForAll = true;
//            mohasebeMosharekat();
////            mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
////            paginatedListBimename = new PaginatedListImpl<Bimename>();
////            paginatedListBimename.setPageNumber(0);
////            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
////            paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
////            bimenameha = paginatedListBimename.getList();
//            final ArrayList<LogMosharekat> logs = new ArrayList<LogMosharekat>(bimenameha.size());
//            final ArrayList<Credebit> credebits = new ArrayList<Credebit>(bimenameha.size());
//            for (Bimename bimename : bimenameha) {
//                if (bimename.getMablaghMosharekatDummy() != null && (bimename.getLastMosharekat() == null || !bimename.getLastMosharekat().equals(mosharekatDarManafe))) {
//                    boolean shouldSabt = false;
//                    if(bimename.getAndukhteyeGhatie() > 0 && bimename.shouldSabtBimenameInMosharekat()) {
//                        shouldSabt = true;
//                    }
////                    else {
////                      commented chon nemikhaym 0 beshe to marhale badi ke miad
////                        bimename.setMablaghMosharekatDummy(null);
////                    }
//                    bimename.setLastMosharekat(mosharekatDarManafe);
//                    if(shouldSabt) {
//                        final LogMosharekat log = new LogMosharekat();
//                        log.setBimename(bimename);
//                        log.setMosharekat(mosharekatDarManafe);
//                        log.setMablagheMosharekat(NumberFormat.getNumberInstance().format(bimename.getMablaghMosharekatDummy()));
//                        logs.add(log);
//                        final Credebit credebit = new Credebit(NumberFormat.getNumberInstance().format(bimename.getMablaghMosharekatDummy()),
//                                sequenceService.nextShenaseCredebit(), bimename, null, Credebit.CredebitType.MOSHAREKAT);
//                        credebit.setDateFish(mosharekatDarManafe.getTarikhPayanDowre());
//                        credebits.add(credebit);
//                    }
//                    mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhMabnaMohasebe());
//                    pishnehadService.updateBimename(bimename);
//                }
//            }
//            if (logs.size() > 0) logMosharekatService.update(logs);
//            if (credebits.size() > 0) asnadeSodorService.saveCredebitList(credebits);
//            addActionMessage("مشارکت با موفقیت ثبت شد");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ERROR;
//        }
////        initPaginatedList(mosharekatDarManafe);
//        paginatedListBimename = new PaginatedListImpl<Bimename>();
//        paginatedListBimename.setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
//        paginatedListBimename.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        if(isExport())
//        {
//            paginatedListBimename.setPageNumber(0);
//            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
//        }
//        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
////        paginatedListBimename.setList(bimenameha);
        return SUCCESS;
    }

    public String sabteMosharekatAllinOne_calc() {
            try {
                mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
                bimenameha = pishnehadService.findAllBimenameForMosharekat(mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
                final ArrayList<LogMosharekat> logs = new ArrayList<LogMosharekat>(bimenameha.size());
                final ArrayList<Credebit> credebits = new ArrayList<Credebit>(bimenameha.size());
                for (Bimename bimename : bimenameha) {
                    if (bimename.getMablaghMosharekatDummy() != null && (bimename.getLastMosharekat() == null || !bimename.getLastMosharekat().equals(mosharekatDarManafe))) {
                        boolean shouldSabt = false;
                        if(bimename.getShouldSabtInMosharekat().equals("yes")) {
                            shouldSabt = true;
                        }
                        bimename.setLastMosharekat(mosharekatDarManafe);
                        if(shouldSabt) {
                            final LogMosharekat log = new LogMosharekat();
                            log.setBimename(bimename);
                            log.setMosharekat(mosharekatDarManafe);
                            log.setMablagheMosharekat(NumberFormat.getNumberInstance().format(bimename.getMablaghMosharekatDummy()));
                            logs.add(log);
                            final Credebit credebit = new Credebit(NumberFormat.getNumberInstance().format(bimename.getMablaghMosharekatDummy()),
                                    sequenceService.nextShenaseCredebit(), bimename, null, Credebit.CredebitType.MOSHAREKAT);
                            credebit.setDateFish(mosharekatDarManafe.getTarikhPayanDowre());
                            credebits.add(credebit);
                        }
//                        mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhMabnaMohasebe());
                    }
                }
                pishnehadService.updateBimename(bimenameha);
                if (logs.size() > 0) logMosharekatService.update(logs);
                if (credebits.size() > 0) asnadeSodorService.saveCredebitList(credebits);
                addActionMessage("مشارکت با موفقیت ثبت شد");
            } catch (Exception ex) {
                ex.printStackTrace();
                return ERROR;
            }
            return SUCCESS;
        }

    private static Long calcSoudMajmuMosharekat(Bimename bimename, String date) {
//        Long tmp = 0L;
//            final List<LogMosharekat> mosharekats = bimename.getLogMosharekats();
//            Collections.sort(mosharekats);
//            for (LogMosharekat m : mosharekats) {
//                if (DateUtil.isGreaterThanOrEqual(date, m.getMosharekat().getTarikhPayanDowre()))
//                    tmp = Math.round((tmp + Long.parseLong(m.getSoudMablagheMosharekat().replace(",", "").trim())) * (CashFlow.SOUD - 1));
//            }
        CashFlow cashflow = new CashFlow();
        ArrayList<Flow> flows = new ArrayList<Flow>();
        for (Credebit c : bimename.getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThanOrEqual(date, c.getDateFish())) {
                Flow f = new Flow(Long.parseLong(c.getAmount().replaceAll(",", "")), Flow.Type.MOSHAREKAT, c.getDateFish());
                flows.add(f);
            }
        }
        cashflow.setFlows(flows);
        return Math.round(cashflow.tajmi(bimename, date, CashFlow.SOUD));
    }

    private static Long calcMajmuMosharekat(Bimename bimename, String date) {
        Long tmp = 0L;
//            for (LogMosharekat m : bimename.getLogMosharekats()) {
//                if (DateUtil.isGreaterThanOrEqual(date, m.getMosharekat().getTarikhPayanDowre()))
//                    tmp += Long.parseLong(m.getMablagheMosharekat().replace(",", "").trim());
//            }
        for (Credebit c : bimename.getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThanOrEqual(date, c.getDateFish()))
                tmp += Long.parseLong(c.getAmount().replaceAll(",",""));
        }
        return tmp;
    }

    public static void setAndukhteAlalHesabVaGhati(Bimename bimename, String date, CashFlow cashFlow) {
        if(cashFlow == null)
            cashFlow = bimename.getCashFlow(date);
        long alalhesab = Math.round(cashFlow.tajmi(bimename, date, CashFlow.SOUD));
        bimename.setAndukhteyeGhatie(alalhesab + calcSoudMajmuMosharekat(bimename, date));
        bimename.setAndukhteyeAlalHesab(alalhesab);
    }

    public static long mohasebeHazineBimegari(Bimename bimename, String date,CashFlow cashFlow)
    {
        long sumHazineBimegari = 0L;
        TaeenAndukhteBarMabnaayeHaghBimePardaakhti taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhti();
        int currentBimeYear = DateUtil.getBimeYear(date, bimename.getTarikhShorou());
        for (int i = currentBimeYear + 1; i < Short.parseShort(bimename.getPishnehad().getEstelam().getModat_bimename()); i++)
        {
            double bimegari = taeenAndukhteBarMabnaayeHaghBimePardaakhti.mohaasebeHazineBimeGari(i, bimename.getPishnehad().getEstelam().isYekja(),
                    bimename.getTarikhShorou(), bimename.getPishnehad().getTarh(), bimename.getFirstPishnehadWithElhaghie().getEstelam().getSarmaye_paye_fot_long(),
                    Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
            if (bimegari > 0)
                sumHazineBimegari += bimegari;
            else
                break;
        }
        return sumHazineBimegari;
    }
    public static void mohasebeAndukhteBimename(Bimename bimename, String date,CashFlow cashFlow) {
        setAndukhteAlalHesabVaGhati(bimename, date,cashFlow);

        //taki
//        String result = InsuranceServiceFactory.getAsnadeSodorService().updateArzesheBimename(date, CashFlow.SOUD, bimename.getId());
//        String result = ((IAsnadeSodorService)InsuranceServiceFactory.getService(IAsnadeSodorService.SERVICE_NAME)).updateArzesheBimename(date, CashFlow.SOUD, bimename.getId());
//          String result = bimename.updateArzesheBimename(date, CashFlow.SOUD);


        //tajmi
//        List<Integer>  bimenameIds = new ArrayList<Integer>();
//        bimenameIds.add(1);
//        bimenameIds.add(2);
//        bimenameIds.add(3);
//        String result = InsuranceServiceFactory.getAsnadeSodorService().updateArzesheBimenameTajmi(date, CashFlow.SOUD, bimenameIds);

//        TaeenAndukhteBarMabnaayeHaghBimePardaakhti taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhti();
        int currentBimeYear = DateUtil.getBimeYear(date, bimename.getTarikhShorou());
        long sumHazineBimegari = mohasebeHazineBimegari(bimename, date, cashFlow);
//        for(int i = currentBimeYear + 1; i < Short.parseShort(bimename.getPishnehad().getEstelam().getModat_bimename()); i++) {
//            double bimegari = taeenAndukhteBarMabnaayeHaghBimePardaakhti.mohaasebeHazineBimeGari(i, bimename.getPishnehad().getEstelam().isYekja(),
//                    bimename.getTarikhShorou(), bimename.getPishnehad().getTarh(), bimename.getFirstPishnehadWithElhaghie().getEstelam().getSarmaye_paye_fot_long(),
//                    Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
//            if(bimegari > 0)
//                sumHazineBimegari += bimegari;
//            else
//                break;
//        }
        double zaribBazkharid = 1.0;
        if(currentBimeYear == 0) zaribBazkharid = 0.9;
        else if (currentBimeYear == 1) zaribBazkharid = 0.92;
        else if (currentBimeYear == 2) zaribBazkharid = 0.94;
        else if (currentBimeYear == 3) zaribBazkharid = 0.96;
        else if (currentBimeYear == 4) zaribBazkharid = 0.98;
//        System.out.println(Math.round(zaribBazkharid * (bimename.getAndukhteyeAlalHesab() - sumHazineBimegari)));
//        System.out.println(Math.round(zaribBazkharid * (bimename.getAndukhteyeGhatie() - sumHazineBimegari)));
//        System.out.println(bimename.getId());
        bimename.setArzeshBazkharidAlalHesab(Math.round(zaribBazkharid * (bimename.getAndukhteyeAlalHesab() - sumHazineBimegari)));
        bimename.setArzeshBazkharidGhatie(Math.round(zaribBazkharid * (bimename.getAndukhteyeGhatie() - sumHazineBimegari)));
//        String result = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().updateMohasebeArzeshBazkharidi(date, bimename.getId());
//        System.out.println(result);
    }

    public String calculateAndukhteEtcAjaxly() {
        bimename = pishnehadService.findBimenameById(bimename.getId());
        mohasebeAndukhteBimename(bimename, tarikhMabna,bimename.getCashFlow(tarikhMabna));
        return SUCCESS;
    }

    private Long calcMablaghMosharekat(Mosharekat mosharekatDarManafe, CashFlow shoroueDowre, CashFlow payaneDowre, CashFlow mianDowre, double soudMosharekat) {
        soudMosharekat = 1 + soudMosharekat / 100.0;
        double vi = shoroueDowre.tajmi(null, mosharekatDarManafe.getTarikhShoruDowre(), soudMosharekat);
        double viprime = payaneDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), CashFlow.SOUD);
        return Math.round(vi * (soudMosharekat) + mianDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), soudMosharekat) - viprime);
    }

    public String mohasebeAndukhteBimenameha() {
//        mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
//        paginatedListBimename = new PaginatedListImpl<Bimename>();
//        paginatedListBimename.setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
//        paginatedListBimename.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        if(isExport())
//        {
//            paginatedListBimename.setPageNumber(0);
//            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
//        }
//        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
//        bimenameha = paginatedListBimename.getList();
//        ArrayList<Bimename> newBimename = new ArrayList<Bimename>();
//        for (Bimename bimename : bimenameha) {
//            if(bimename.shouldCalcAndukhteInMosharekat(mosharekatDarManafe.getTarikhPayanDowre())) {
//                newBimename.add(bimename);
//            }
//        }
//        for (Bimename bimename : newBimename) {
//            mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhMabnaMohasebe());
//        }
//        pishnehadService.updateBimename(newBimename);
//        putToSession("enableMosharekat", true);
////        paginatedListBimename.setList(newBimename);
        return SUCCESS;
    }



    public String tashimSoudMosharekat() {

//        bimenameha = pishnehadService.findAllBimenameForMosharekat(mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
//        for (Bimename bimename : bimenameha)
//        {
//            String moshakhase = bimename.getShomare() + "/";
//            moshakhase += mosharekatDarManafe.getShomareMosharekat();
//            bimename.setShomareMoshakhase(moshakhase);
//            bimename.setAndukhteyeGhatie(0L);
//            bimename.setAndukhteyeAlalHesab(0L);
//            bimename.setArzeshBazkharidGhatie(0L);
//            bimename.setArzeshBazkharidAlalHesab(0L);
//            bimename.setMablaghMosharekatDummy(0L);
//            boolean shouldCalc = bimename.shouldCalcAndukhteInMosharekat(mosharekatDarManafe.getTarikhPayanDowre());
//            boolean shouldSabt = bimename.shouldSabtBimenameInMosharekat();
//            if(shouldCalc && !shouldSabt)
//            {
//                bimename.setStateForMosharekat("PAS_AZ_MABNA");
//            }
//
//            else
//            {
//                bimename.setStateForMosharekat("BIMENAME");
//            }
//        }
//        asnadeSodorService.saveMosharekatDarManafe(mosharekatDarManafe);
//        pishnehadService.updateBimename(bimenameha);
//        putToSession("enableMosharekat", false);
//        putToSession("enableMosharekat2", false);
////        initPaginatedList(mosharekatDarManafe);
//        paginatedListBimename = new PaginatedListImpl<Bimename>();
//        paginatedListBimename.setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
//        paginatedListBimename.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        if(isExport())
//        {
//            paginatedListBimename.setPageNumber(0);
//            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
//        }
//        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
////        paginatedListBimename.setList(bimenameha);
        return SUCCESS;
    }

    public String showMosharekat() {
        mosharekatDarManafe = asnadeSodorService.findMosharekatById(mosharekatDarManafe.getId());
        paginatedListBimename = new PaginatedListImpl<Bimename>();
        paginatedListBimename.setPageNumber(PagingUtil.getPageNumberFromContext("d-16544-p") - 1);
        paginatedListBimename.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        if(isExport())
        {
            paginatedListBimename.setPageNumber(0);
            paginatedListBimename.setObjectsPerPage(Integer.MAX_VALUE);
        }
        paginatedListBimename = pishnehadService.findAllBimenamePaginatedForMosharekat(null,paginatedListBimename, mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
        return SUCCESS;
    }

    public String tashimSoudMosharekatAllinOne_calc() {

//        bimenameha = pishnehadService.findAllBimenameForMosharekat(mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre());
//        for (Bimename bimename : bimenameha)
//        {
//            String moshakhase = bimename.getShomare() + "/";
//            moshakhase += mosharekatDarManafe.getShomareMosharekat();
//            bimename.setShomareMoshakhase(moshakhase);
//            mohasebeAndukhteBimename(bimename, DateUtil.getCurrentDate());
//            long andukhteEmrooz = bimename.getAndukhteyeGhatie();
//            if(andukhteEmrooz <= 0) {
//                bimename.setStateForMosharekat("MOALAGH_");
//            } else {
//                bimename.setStateForMosharekat("");
//            }
//            mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhPayanDowre());
//            long andukhteInPayanDowre = bimename.getAndukhteyeGhatie();
//            boolean shouldCalc = bimename.shouldCalcAndukhteInMosharekat(mosharekatDarManafe.getTarikhPayanDowre(), andukhteInPayanDowre, andukhteEmrooz);
//            boolean shouldSabt = bimename.shouldSabtBimenameInMosharekat(andukhteEmrooz);
//            if(shouldCalc && !shouldSabt)
//            {
//                bimename.setStateForMosharekat(bimename.getStateForMosharekat() + "PAS_AZ_MABNA");
//            } else {
//                bimename.setStateForMosharekat(bimename.getStateForMosharekat() + "BIMENAME");
//            }
//            if(shouldSabt) {
//                bimename.setShouldSabtInMosharekat("yes");
//            } else {
//                bimename.setShouldSabtInMosharekat("no");
//            }
//            if(!shouldCalc) {
//                bimename.setAndukhteyeGhatie(0L);
//                bimename.setAndukhteyeAlalHesab(0L);
//                bimename.setArzeshBazkharidGhatie(0L);
//                bimename.setArzeshBazkharidAlalHesab(0L);
//            }
//            Double soudDouble = Double.parseDouble(mosharekatDarManafe.getSoud());
//            if(andukhteInPayanDowre > 0) {
//                CashFlow result = bimename.getCashFlow(DateUtil.getCurrentDate());
//                ArrayList<Flow> flowhaayeShoroueDowreBimename = new ArrayList<Flow>();
//                ArrayList<Flow> flowhaayePayaneDowreBimename = new ArrayList<Flow>();
//                ArrayList<Flow> flowhaayeMianeDowreBimename = new ArrayList<Flow>();
//                for(Flow flow : result.getFlows()) {
//                    if (!DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhShoruDowre())) {
//                        flowhaayeShoroueDowreBimename.add(flow);
//                    }
//                    if (DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhShoruDowre()) && !DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhPayanDowre())) {
//                        flowhaayeMianeDowreBimename.add(flow);
//                    }
//                    if (!DateUtil.isGreaterThan(flow.getDate(), mosharekatDarManafe.getTarikhPayanDowre())) {
//                        flowhaayePayaneDowreBimename.add(flow);
//                    }
//                }
//                bimename.setMablaghMosharekatDummy(calcMablaghMosharekat(mosharekatDarManafe, new CashFlow(flowhaayeShoroueDowreBimename),
//                        new CashFlow(flowhaayePayaneDowreBimename), new CashFlow(flowhaayeMianeDowreBimename), soudDouble));
//            } else {
//                bimename.setMablaghMosharekatDummy(0L);
//            }
//            if(!mosharekatDarManafe.getTarikhPayanDowre().equals(mosharekatDarManafe.getTarikhMabnaMohasebe()))
//                mohasebeAndukhteBimename(bimename, mosharekatDarManafe.getTarikhMabnaMohasebe());
//        }
//        asnadeSodorService.saveMosharekatDarManafe(mosharekatDarManafe);
//        pishnehadService.updateBimename(bimenameha);
        asnadeSodorService.saveMosharekatDarManafe(mosharekatDarManafe);
        System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()));
        String result = pishnehadService.executeMosharekat(mosharekatDarManafe.getTarikhShoruDowre(), mosharekatDarManafe.getTarikhPayanDowre(),
                Double.parseDouble(mosharekatDarManafe.getSoud()));
        System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()));
        putToSession("enableMosharekat", false);
        putToSession("enableMosharekat2", false);
        return SUCCESS;
    }

    private double calculateNewtonlySoudeMosharekat(Mosharekat mosharekatDarManafe, CashFlow shoroueDowre, CashFlow payaneDowre, CashFlow mianDowre) {
        double vi = shoroueDowre.tajmi(null, mosharekatDarManafe.getTarikhShoruDowre(), CashFlow.SOUD);
        double viprime = payaneDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), CashFlow.SOUD);
        double m = Double.parseDouble(mosharekatDarManafe.getMablaghSudTashim());

        double x = CashFlow.SOUD;

        double start = x, end = 10, middle = 0;
        while (Math.abs(start - end) > 0.01) {
            middle = (start + end) / 2.0;
            double res_start, res_middle, res_end;
            res_start = mianDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), start) - viprime + vi - m;
            res_middle = mianDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), middle) - viprime + vi - m;
            res_end = mianDowre.tajmi(null, mosharekatDarManafe.getTarikhPayanDowre(), end) - viprime + vi - m;
            boolean start_manfi = (res_start >= 0) ? false : true;
            boolean middle_manfi = (res_middle >= 0) ? false : true;
            boolean end_manfi = (res_end >= 0) ? false : true;
            if (start_manfi == middle_manfi) {
                start = middle;
            } else {
                end = middle;
            }
        }

        return middle;
    }

    public String operationSelectBedehiNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isValid = true;
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            List<Credebit> credebitList = new ArrayList<Credebit>();
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] credebitIds = (String[]) ActionContext.getContext().getParameters().get(name);

                        Long majmoBedehi = 0l;
                        for (String idString : credebitIds) {
                            Credebit credebit1 = asnadeSodorService.findCretebitById(Integer.parseInt(idString));
                            if (credebit1 != null && credebit1.getRemainingAmount_long() > 0){
                                majmoBedehi += credebit1.getRemainingAmount_long();
                                credebitList.add(credebit1);
                            }
                            else {
                                isValid = false;
                            }

                        }
                        amount = majmoBedehi.toString();
                    }
                }
            }
            credebitListPaginated = new PaginatedListImpl<Credebit>();
            credebitListPaginated.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
            credebitListPaginated.setPageNumber(1);
            credebitListPaginated.setFullListSize(credebitList.size());
            credebitListPaginated.setList(credebitList);
        }
        if (!isValid)
            addActionMessage("در لیست انتخابی، بدهای های سند خورده موقت وجود دارد که قادر به پرداخت آنها نیستید");
        return SUCCESS;
    }
    public String pardakhtInternetiBedehi() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        Long mablagh = 0l;
        String  credebitId="";
        String[] credebitIds;
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            List<Credebit> credebitList = new ArrayList<Credebit>();
            String additionalDataForBank = "";
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                         credebitIds = (String[]) ActionContext.getContext().getParameters().get(name);


                        for (String idString : credebitIds) {
                            credebitId+=idString+"/"  ;
                            Credebit credebit1 = asnadeSodorService.findCretebitById(Integer.parseInt(idString));
                           // additionalDataForBank += credebit1.getShomareMoshtari() + "/";
                            if (credebit1 != null){
                                mablagh += credebit1.getRemainingAmount_long();
                                credebitList.add(credebit1);
                            }

                        }

                    }
                }
            }
            credebitListPaginated = new PaginatedListImpl<Credebit>();
            credebitListPaginated.setObjectsPerPage(PagingUtil.MAX_OBJECTS_PER_PAGE);
            credebitListPaginated.setPageNumber(1);
            credebitListPaginated.setFullListSize(credebitList.size());
            credebitListPaginated.setList(credebitList);
       try {
                String pin = "78d6LN3o6isGu5Wj448C";
                Properties prop = new Properties();
                try
                {
                    prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                String property = prop.getProperty("EPaymentHost");
                epaymentHost = property;
               // String pgwPage = epaymentHost + "/pecpaymentgateway";
                EShopServiceLocator l = new EShopServiceLocator();
                EShopServiceSoap_PortType lsoap = null;
                lsoap = l.getEShopServiceSoap();
               // InetAddress inetAddress = InetAddress.getLocalHost();
                this.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(result.getNamayandegi(), "8", "230",null));
                String callBackUrl = StringUtil.getDeploymentPath() + "/fin/pardakhtInternetiBedehiFinal?credebitIds="+credebitId ;
                LongHolder authority = new LongHolder(0);
                UnsignedByte statusVal = new UnsignedByte(100);
                UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
           int textOrderId = LoginAction.getOrderId();
                if (textOrderId != -1) {
                    try {
                        //if(mablagh > 20000000) mablagh = 20000000l;
                        additionalDataForBank=this.getShomareMoshtari();
                        lsoap.pinPaymentRequestWithExtra(pin, BigDecimal.valueOf(mablagh), textOrderId, callBackUrl, additionalDataForBank, authority, status);
                        authorityId = String.valueOf(authority.value);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (textOrderId == -1) {
                    addActionError("خطا در دسترسی به بانک");

                    return ERROR;
                } else if (status.value.equals(PgwStatus.Successful)) {

                    return SUCCESS;
                }
            } catch (Exception ex) {
                addActionError("خطا در دسترسی به بانک");

                return ERROR;
            }
        }
        return ERROR;
    }

    public String pardakhtInternetiBedehiFinal() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            return Constant.NOSESSION;
        } else {
            Long mablagh = 0l;
            List<Credebit> credebitList = new ArrayList<Credebit>();
            try {
                final Logger logger = LoggerFactory.getLogger(CredebitAction.class);
                String[] temp =  (String[]) ActionContext.getContext().getParameters().get("au");
                this.setAuthorityId(temp[0]);
                String[] temp1 =  (String[]) ActionContext.getContext().getParameters().get("rs");
                this.setBankResponse(temp1[0]);
               /* this.setBankResponse("0");
                System.out.println(bankResponse+"--"+authorityId+"--"+credebitId);*/
                String header = String.format("Payment (INTERNET, ETEBAR) authId: %s | ", authorityId);
                String pin = "78d6LN3o6isGu5Wj448C";
                EShopServiceLocator l = new EShopServiceLocator();
                EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
                UnsignedByte statusVal = new UnsignedByte(100);
                UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
                if (ActionContext.getContext().getParameters().size() > 0) {
                    String[] temp2=  (String[]) ActionContext.getContext().getParameters().get("credebitIds");
                    String [] c= temp2[0].split("/") ;
                    for (int i=0;i<c.length;i++) {
                        Credebit credebit1 = asnadeSodorService.findCretebitById(Integer.parseInt(c[i]));
                        if (credebit1 != null){
                            logger.info(String.format(header + "bankResponse: %s credebit.id: %s"
                                    , bankResponse, credebit1.getId()));
                            mablagh += credebit1.getRemainingAmount_long();
                            credebitList.add(credebit1);
                        }

                    }
                }
                Boolean isNotValidBedehi=false;
                for(Credebit bedehi:credebitList){
                    if( bedehi.getRemainingAmount_long()==0)
                        isNotValidBedehi=true;
                }
                if(!isNotValidBedehi)   {
                if (Integer.parseInt(bankResponse) == 0 && authorityId != null) {
                    lsoap.pinPaymentEnquiry(pin, Long.parseLong(authorityId), status);
                    logger.info(String.format(header + "status: %s"
                            , status.value.toString()));
                }
                }
                if (status.value.longValue() == 0 && credebitList!=null && credebitList.size()>0 && !isNotValidBedehi) {
                    this.setCreebitAmount(mablagh.toString());
                    List<Credebit> etebarCredebitList = new ArrayList<Credebit>();
                    Credebit  etebar = new Credebit(Integer.parseInt(mablagh.toString()), "", result.getNamayandegi().getName_kod(), "", "VEHICLE", Credebit.CredebitType.DARYAFTE_ELECTRONICI.toString(), "", sequenceService.nextShenaseCredebit(), "");
//                    etebar.setBankName("تجارت الکترون?ک - م?رداماد شرق? (0201136462000)");
                    etebar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
                    etebar.setNamayande(result.getNamayandegi());
                    etebar.setVahedeSodor(result.getNamayandegi());
                    etebar.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                    etebar.setVosoulDate(DateUtil.getCurrentDate());
                    etebar.setNahveVosoul(Credebit.NahveVosoul.INTERNET);
                    etebar.setAuthorityId(authorityId);
                    etebar.setShomareMoshtari(this.getShomareMoshtari());
                    asnadeSodorService.saveCredebit(etebar);
                    etebarCredebitList.add(etebar);
                    this.setCredebitId(etebar.getId());
                    for(Credebit bedehi:credebitList){
                        bedehi.setVosoulDate(DateUtil.getCurrentDate());
                        bedehi.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                        asnadeSodorService.updateCredebit(bedehi);
                    }
                    Sanad sanad1 = asnadeSodorService.sabteSanad(credebitList, etebarCredebitList, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, result, true);
                    this.setSanadId(sanad1.getId());
                    logger.info(String.format(header + "bankResponse: %s sanad.id: %s"
                            , bankResponse, sanad1.getId().toString()));
                    addActionMessage("پرداخت شما با موفقیت انجام شد");
                    return SUCCESS;
                }else {
                   addActionError("خطا در دریافت اطلاعات از بانک");
                    return SUCCESS;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                addActionError("خطا در سیستم");
                return SUCCESS;
            }
        }
    }

    public String operationCheckList(){
        boolean error = false;
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            List<Credebit> credebitList = new ArrayList<Credebit>();
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] credebitIds = (String[]) ActionContext.getContext().getParameters().get(name);
                        for (String idString : credebitIds) {
                            Credebit credebit1 = asnadeSodorService.findCretebitById(Integer.parseInt(idString));
                            if (credebit1 != null && credebit1.getDaryafteCheck() != null && credebit1.getDaryafteCheck().getStatus().equals(DaryafteCheck.Status.NAZD_SANDOGH)
                                    && credebit1.getAmount_long() > credebit1.getRemainingAmount_long()) //agar check nazde sandogh ast va
                            {
                                credebit1.getDaryafteCheck().setStatus(DaryafteCheck.Status.VAGOZAR_SHODE);
                                credebit1.setShomareMoshtari( sequenceService.generateNextShomareMoshtari(result.getNamayandegi(), "8", "242",null)); //8 yani ?   242 yani  ?
                                credebitList.add(credebit1);
                            }
                            else if (credebit1.getDaryafteCheck().getStatus().equals(DaryafteCheck.Status.VAGOZAR_SHODE)) //vagozar shode
                            {
                                flagPage = "1";  //jahate namayeshe peyghame check vagozar shode mibashad
                                break;
                            }
                            else //agar dar liste etebarate entekhabi etebari vojood dashte bashad ke sharayete vagozari ra nadashte bashad
                            {
                                flagPage = "2"; //jahate namayeshe peyghame nadashtane sharayete vogozari
                            }

                        }

                    }
                }
            }

            if (credebitList != null && credebitList.size() > 0){
                Vagozari vagozari = new Vagozari();
                vagozari.setShomare(sequenceService.nextShenaseCredebit());
                vagozari.setCreateDate(DateUtil.getCurrentDate());
                vagozari.setNamayande(result.getNamayandegi());
                //b-h
                Integer daftar_id=result.getDaftar().getId();
//                Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
                Daftar daftar=asnadeSodorService.findDaftarById(daftar_id);
                vagozari.setDaftar(daftar);
                /////ta inja
                asnadeSodorService.saveVagozari(vagozari);
                for (Credebit credebit1: credebitList){
                    credebit1.setVagozari(vagozari);
                    asnadeSodorService.updateCredebit(credebit1);
                }
            }

        }
        elamVagozari();
        return SUCCESS;
    }

    public String elamVagozari(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
       if(user.getNamayandegi()!=null)
       this.setNamayandeId(user.getNamayandegi().getId());
        credebitListPaginated = asnadeSodorService.findAllCheck(shomareHesab, shomareCheck, sarresidDate, checkSerri, rcptName, bankName, branchCode, accountOwnerName, amount, user);
        return SUCCESS;
    }

    public String listVagozari(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        vagozariPaginatedList = asnadeSodorService.findListVaghozariCredebitPaginated(user);
        return SUCCESS;
    }

    public String listEtebarat(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        credebitListPaginated = asnadeSodorService.findAllCredebitsPaginated(user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, bankName, subsystem_field, shomareCheck, shomareFish);
        return SUCCESS;
    }

    public String listGozareshUpload(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        bankInfoPaginatedList = asnadeSodorService.findAllBankInfoPaginated(user,createdDateFrom,createdDateTo,sarresidDateFrom,sarresidDateTo,rcptName,shomareMoshtari,vosoulDesc);
        return SUCCESS;
    }

    public String listGozareshVosouli(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        credebitListPaginated = asnadeSodorService.findListGozareshVosouli(user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, namayandeId, bankName, subsystem_field);
        return SUCCESS;
    }

    public String listPardakhtInternetiBedehi(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        credebitListPaginated = asnadeSodorService.findAllCredebitsPaginated(user, Credebit.CredebitMainType.BEDEHI, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, Credebit.Status.SANAD_NA_KHORDE, vosoulStateFarsi, namayandeId, bankName, "VEHICLE", shomareCheck, shomareFish);
        return SUCCESS;
    }

    public String listPardakhtPaya(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        credebitListPaginated = asnadeSodorService.findListCredebitShabaPaginated(user, Credebit.CredebitMainType.ETEBAR, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, Credebit.Status.SANAD_NA_KHORDE, vosoulStateFarsi, namayandeId, bankName, subsystem_field);
        return SUCCESS;
    }

    public String viewPardakhtPayaForm(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        if (credebit != null && credebit.getId() != null){
            credebit = asnadeSodorService.findCretebitById(credebit.getId());
        }
        return SUCCESS;
    }

    public String sabtPardakhtPayaForm(){
        if (getShomareShaba().length() != 24) {
            addActionError("شماره شبا اشتباه است.");
            return ERROR;
        } else {
            BigInteger toCheck = new BigInteger(getShomareShaba().substring(2) + "1827" + getShomareShaba().substring(0, 2));
            if (toCheck.mod(new BigInteger("97")).intValue() != 1) {
                addActionError("شماره شبا اشتباه است.");
                return ERROR;
            }
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        if (credebitId != null){
            Credebit credebitEtebar = asnadeSodorService.findCretebitById(credebitId);
            if (credebitEtebar.getRemainingAmount_long() > 0 && credebitEtebar.getRemainingAmount_long() <= 50000000)
            {
                String response = ACHPayment.achPaymentRequestSanam(credebitEtebar.getShenaseCredebit(), credebitEtebar.getRemainingAmount_long().intValue(), credebitEtebar.getIdentifier(),
                        credebitEtebar.getRcptName(), getShomareShaba(), "", getCodeMelli(),
                        user.getUsername(), "", credebitEtebar.getSubsystemName().equalsIgnoreCase("VEHICLE") ? credebitEtebar.getVahedeSodor().getKodeNamayandeKargozar() : "111116");

                if (!response.contains("-"))
                {
                    Credebit credebitACH = new Credebit(credebitEtebar.getRemainingAmount_long().toString(), sequenceService.nextShenaseCredebit(), credebitEtebar.getBimename(), null, Credebit.CredebitType.ACH);
                    credebitACH.setRcptName(credebitEtebar.getRcptName());
                    credebitACH.setAmount_long(credebitEtebar.getRemainingAmount_long());
                    credebitACH.setRemainingAmount_long(credebitEtebar.getRemainingAmount_long());
                    credebitACH.setRahgiriACH(response);
                    credebitACH.setIdentifier(credebitEtebar.getIdentifier());
                    credebitACH.setNamayande(credebitEtebar.getNamayande());
                    credebitACH.setVahedeSodor(credebitEtebar.getVahedeSodor());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   credebitACH.setSarresidDate(credebitEtebar.getSarresidDate())                                                                                                                                                                                                                                                                                                                                                                                                                                                 ;
                    credebitACH.setSubsystemName("VEHICLE");
                    credebitACH.setStatus(Credebit.Status.SANAD_KHORDE);
                    credebitACH.setVosoulDate(DateUtil.getCurrentDate());
                    credebitACH.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                    //B-H
                    Daftar daftar=asnadeSodorService.findDaftarById(user.getDaftar().getId());
                    credebitACH.setDaftar(daftar);
                    //TA INJA
                    asnadeSodorService.saveCredebit(credebitACH);
                    asnadeSodorService.sabteSanad(user, credebitACH, credebitEtebar, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
                }
                else
                {
                    addActionError("به دلیل بروز مشکل، پرداخت پایا انجام نشد");
                    final org.slf4j.Logger logger = LoggerFactory.getLogger(CredebitAction.class);
                    logger.info(String.format("ACH Failed ! credebit identifier: %s bimename.shomare: %s ach response: %s"
                            , credebitEtebar.getIdentifier(), "", response));
                }
            } else{
                addActionError("مبلغ در محدوده مجاز نمی باشد");
            }
        }

        listPardakhtPaya();

        return SUCCESS;
    }



    public String listMaliNamayande(){
        namayandeListPaginated =  namayandeService.findMaliNamayandeHaList(sarresidDateFrom, sarresidDateTo, namayandeId); //PagingUtil.getPaginatedList(namayandeService.getAllNamayande());
        return SUCCESS;
    }

    public String listEtebaratNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        credebitListPaginated = asnadeSodorService.findAllCredebitsPaginated(user, credebitNoe,identifier,shomareMoshtari,rcptName,sarresidDateFrom,sarresidDateTo,createdDateFrom,createdDateTo,amount,paidReceivedAmount,remainingAmount,credebitTypeFarsi,shomareGharardad,statusFarsi,vosoulStateFarsi,namayandeId, bankName,subsystem_field, shomareCheck, shomareFish);
        return SUCCESS;
    }

    public String listEtebaratVosulNashodeNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }

        int page = PagingUtil.getPageNumberFromContext("pageNumber_etebar");
        if (isExportByCode("49681")){
            etebarCredebitListPaginated = asnadeSodorService.findAllEtebaratVosulNashodeCredebitsPaginated(0, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat);
            etebarCredebitListPaginated.setPageNumber(0);
            etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else {
            etebarCredebitListPaginated = asnadeSodorService.findAllEtebaratVosulNashodeCredebitsPaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat);
        }



        return SUCCESS;
    }

    public String listMoghayeratDarVosolNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }

        int page = PagingUtil.getPageNumberFromContext("pageNumber_moghayerat");
        if (isExportByCode("49683")){
            etebarMoghayeratCredebitListPaginated = asnadeSodorService.findMoghayeratDarVosolNamayandePaginated(0, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDate, createdDate, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat3, vosoulDesc);
            etebarMoghayeratCredebitListPaginated.setPageNumber(0);
            etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else {
            etebarMoghayeratCredebitListPaginated = asnadeSodorService.findMoghayeratDarVosolNamayandePaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDate, createdDate, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat3, vosoulDesc);
        }



        return SUCCESS;
    }

    public String listBedehiHayeVosulNashodeNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }

        boolean search = false;
        if(searchPage.equals("yes"))
            search = true;

        int page = PagingUtil.getPageNumberFromContext("pageNumber_bedehiVosulNashode");
        if (isExportByCode("49684")){
            bedehiVosulNashodeCredebitListPaginated = asnadeSodorService.findBedehiVosulNashodeNamayandePaginated(0, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom , createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat4, vosoulDesc, search);
            bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
            bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else {

            bedehiVosulNashodeCredebitListPaginated = asnadeSodorService.findBedehiVosulNashodeNamayandePaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom , createdDateTo, amount, paidReceivedAmount, remainingAmount, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiIdEtebarat4, vosoulDesc, search);
        }

        return SUCCESS;
    }

    public boolean isExportByCode(String codeExportDisplayTag) {
        boolean bExport = false;

        Map values = ActionContext.getContext().getParameters();

        if (values != null && !values.isEmpty()) {
            Iterator i = values.keySet().iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                System.out.println("exported value:"+name);
                if (name.equals("d-" + codeExportDisplayTag + "-e")) {
                    bExport = true;
                    break;
                }
            }
        }

        return bExport;
    }

    public String listBedehiNamayande(){


        //long bedehiSanadNakhorde = asnadeSodorService.getRemainingAmountBedehiSanadNakhorde(540020l);
        //long etebarSanadKhordeVosulNashode = asnadeSodorService.getRemainingAmountEtebarSanadKhordeVosulNashode(540020l);
        operationSelectBedehiNamayande();
        //if (!isExport()){

            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            String tab = "";
            for (String name : qs) {
                if (name.equals("selectedTab")){
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get("selectedTab");
                    tab = (String) o[0];
                }
            }
            if(tab.equals("tabs-1")){
                listBedehiVahedeSodorNamayande(true);
                etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarCredebitListPaginated.setPageNumber(0);
                etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarMoghayeratCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarMoghayeratCredebitListPaginated.setPageNumber(0);
                etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                bedehiVosulNashodeCredebitListPaginated = new PaginatedListImpl<CredebitVM>();
                bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
                bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                //b-h
                bedehiTasviyeNashodePaginatedList=new PaginatedListImpl<bedehiTasviyeNashode>();
                bedehiTasviyeNashodePaginatedList.setPageNumber(0);
                bedehiTasviyeNashodePaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                /// ta inja


            } else if(tab.equals("tabs-2")){
                bedehiCredebitListPaginated = new PaginatedListImpl<Credebit>();
                bedehiCredebitListPaginated.setPageNumber(0);
                bedehiCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                listEtebaratVosulNashodeNamayande();

                etebarMoghayeratCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarMoghayeratCredebitListPaginated.setPageNumber(0);
                etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                bedehiVosulNashodeCredebitListPaginated = new PaginatedListImpl<CredebitVM>();
                bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
                bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                //b-h
                bedehiTasviyeNashodePaginatedList=new PaginatedListImpl<bedehiTasviyeNashode>();
                bedehiTasviyeNashodePaginatedList.setPageNumber(0);
                bedehiTasviyeNashodePaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                /// ta inja

            } else if(tab.equals("tabs-3")){
                bedehiCredebitListPaginated = new PaginatedListImpl<Credebit>();
                bedehiCredebitListPaginated.setPageNumber(0);
                bedehiCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarCredebitListPaginated.setPageNumber(0);
                etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                listMoghayeratDarVosolNamayande();

                bedehiVosulNashodeCredebitListPaginated = new PaginatedListImpl<CredebitVM>();
                bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
                bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                //b-h
                bedehiTasviyeNashodePaginatedList=new PaginatedListImpl<bedehiTasviyeNashode>();
                bedehiTasviyeNashodePaginatedList.setPageNumber(0);
                bedehiTasviyeNashodePaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                /// ta inja

            } else if(tab.equals("tabs-4")){
                bedehiCredebitListPaginated = new PaginatedListImpl<Credebit>();
                bedehiCredebitListPaginated.setPageNumber(0);
                bedehiCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarCredebitListPaginated.setPageNumber(0);
                etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarMoghayeratCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarMoghayeratCredebitListPaginated.setPageNumber(0);
                etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                listBedehiHayeVosulNashodeNamayande();
                //b-h
                bedehiTasviyeNashodePaginatedList=new PaginatedListImpl<bedehiTasviyeNashode>();
                bedehiTasviyeNashodePaginatedList.setPageNumber(0);
                bedehiTasviyeNashodePaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                /// ta inja

            } else if(tab.equals("tabs-5")){
                bedehiCredebitListPaginated = new PaginatedListImpl<Credebit>();
                bedehiCredebitListPaginated.setPageNumber(0);
                bedehiCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarCredebitListPaginated.setPageNumber(0);
                etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                etebarMoghayeratCredebitListPaginated = new PaginatedListImpl<Credebit>();
                etebarMoghayeratCredebitListPaginated.setPageNumber(0);
                etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);


                bedehiVosulNashodeCredebitListPaginated = new PaginatedListImpl<CredebitVM>();
                bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
                bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                listBedehiHayeTasviyeNashodeNamayande();
            }

         else {
//            listBedehiVahedeSodorNamayande();
//            listEtebaratVosulNashodeNamayande();
//            listMoghayeratDarVosolNamayande();
//            listBedehiHayeVosulNashodeNamayande();
            listBedehiVahedeSodorNamayande(false);

            etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
            etebarCredebitListPaginated.setPageNumber(0);
            etebarCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

            etebarMoghayeratCredebitListPaginated = new PaginatedListImpl<Credebit>();
            etebarMoghayeratCredebitListPaginated.setPageNumber(0);
            etebarMoghayeratCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);

            bedehiVosulNashodeCredebitListPaginated = new PaginatedListImpl<CredebitVM>();
            bedehiVosulNashodeCredebitListPaginated.setPageNumber(0);
            bedehiVosulNashodeCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                //b-h
            bedehiTasviyeNashodePaginatedList=new PaginatedListImpl<bedehiTasviyeNashode>();
            bedehiTasviyeNashodePaginatedList.setPageNumber(0);
            bedehiTasviyeNashodePaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                /// ta inja
        }

        return SUCCESS;
    }

    public String listBedehiVahedeSodorNamayande() {
        Set<String> qs = ActionContext.getContext().getParameters().keySet();
        String tab = "";
        for (String name : qs) {
            if (name.equals("selectedTab")) {
                Object[] o = (Object[]) ActionContext.getContext().getParameters().get("selectedTab");
                tab = (String) o[0];
            }
        }
        if(tab.length() > 0)
            return listBedehiVahedeSodorNamayande(true);
        else
            return listBedehiVahedeSodorNamayande(false);
    }

    public String listBedehiVahedeSodorNamayande(boolean isSearch){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
            user = loginService.findUserByUsername(username);

        int page = PagingUtil.getPageNumberFromContext("page");
        if (isExportByCode("16544")){
            bedehiCredebitListPaginated = asnadeSodorService.findAllBedehiCredebitsPaginated(0, user, credebitNoe, identifier, shomareMoshtari, rcptName ,sarresidDateFrom,sarresidDateTo,createdDateFrom,createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiId, search_vahedesodorId, isSearch);
            bedehiCredebitListPaginated.setPageNumber(0);
            bedehiCredebitListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else {
            System.out.println("we are here");
            bedehiCredebitListPaginated = asnadeSodorService.findAllBedehiCredebitsPaginated(page, user, credebitNoe, identifier, shomareMoshtari, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, paidReceivedAmount, remainingAmount, credebitTypeFarsi, shomareGharardad, statusFarsi, vosoulStateFarsi, search_namayandegiId, search_vahedesodorId, isSearch);
        }

        return SUCCESS;
    }

    public String viewBankInfos(){
        Credebit myCredebit = asnadeSodorService.findCretebitById(credebit.getId());
        if(myCredebit!=null && myCredebit.getId() > 0 && !myCredebit.isBedehi()){
            List<BankInfo> list = asnadeSodorService.findAllBankInfosByCredebitId(myCredebit.getId()).getList();
            List<BankInfo> bankInfos = new ArrayList<BankInfo>();
            for (BankInfo bankInfo : list){
                bankInfo.setBardasht(bankInfo.getMablagh());
                bankInfos.add(bankInfo);
            }
            bankInfoPaginatedList = new PaginatedListImpl<BankInfo>();
            bankInfoPaginatedList.setPageNumber(1);
            bankInfoPaginatedList.setFullListSize(Integer.MAX_VALUE);
            bankInfoPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            bankInfoPaginatedList.setList(bankInfos);
        } else if(myCredebit!=null && myCredebit.getId() > 0 && myCredebit.isBedehi()){
            List<KhateSanad> allKhateSanadByCredebitId = asnadeSodorService.findAllKhateSanadByCredebitId(myCredebit.getId());
            List<BankInfo> bankInfos = new ArrayList<BankInfo>();
            for (KhateSanad khateSanad : allKhateSanadByCredebitId){
                List<BankInfo> list = asnadeSodorService.findAllBankInfosByCredebitId(khateSanad.getEtebarCredebit().getId()).getList();
                for (BankInfo bankInfo : list){
                    bankInfo.setBardasht(khateSanad.getAmount());
                    bankInfos.add(bankInfo);
                }
            }
            if (bankInfoPaginatedList != null)
                bankInfoPaginatedList.setList(bankInfos);
            else{
                bankInfoPaginatedList = new PaginatedListImpl<BankInfo>();
                bankInfoPaginatedList.setPageNumber(1);
                bankInfoPaginatedList.setFullListSize(Integer.MAX_VALUE);
                bankInfoPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                bankInfoPaginatedList.setList(bankInfos);
            }
        }

        return SUCCESS;
    }
    public String gozareshBedehiNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);

        }

        return SUCCESS;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public List<Sanad> getSanadList() {
        return sanadList;
    }

    public void setSanadList(List<Sanad> sanadList) {
        this.sanadList = sanadList;
    }

    public String getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(String pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public List<DaryafteCheck> getDaryafthayeCheck() {
        return daryafthayeCheck;
    }

    public void setDaryafthayeCheck(List<DaryafteCheck> daryafthayeCheck) {
        this.daryafthayeCheck = daryafthayeCheck;
    }

    public List<Hesab> getHesabha() {
        return hesabha;
    }

    public void setHesabha(List<Hesab> hesabha) {
        this.hesabha = hesabha;
    }

    public DaryafteCheck getDaryafteCheck() {
        return daryafteCheck;
    }

    public void setDaryafteCheck(DaryafteCheck daryafteCheck) {
        this.daryafteCheck = daryafteCheck;
    }

    public Mosharekat getMosharekatDarManafe() {
        return mosharekatDarManafe;
    }

    public void setMosharekatDarManafe(Mosharekat mosharekatDarManafe) {
        this.mosharekatDarManafe = mosharekatDarManafe;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public List<Bimename> getBimenameha() {
        return bimenameha;
    }

    public void setBimenameha(List<Bimename> bimenameha) {
        this.bimenameha = bimenameha;
    }

    public Long getMablagheMosharekat() {
        return mablagheMosharekat;
    }

    public void setMablagheMosharekat(Long mablagheMosharekat) {
        this.mablagheMosharekat = mablagheMosharekat;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public ILogMosharekatService getLogMosharekatService() {
        return logMosharekatService;
    }

    public void setLogMosharekatService(ILogMosharekatService logMosharekatService) {
        this.logMosharekatService = logMosharekatService;
    }

    public PaginatedListImpl<Bimename> getPaginatedListBimename() {
        return paginatedListBimename;
    }

    public void setPaginatedListBimename(PaginatedListImpl<Bimename> paginatedListBimename) {
        this.paginatedListBimename = paginatedListBimename;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public String getTarikhMabna() {
        return tarikhMabna;
    }

    public void setTarikhMabna(String tarikhMabna) {
        this.tarikhMabna = tarikhMabna;
    }

    public String deleteSanadOperation(){
        if (formSanadId != null && formSanadId > 0)
            asnadeSodorService.deleteSanad(formSanadId);
        return SUCCESS;
    }

    public String checkUsernamePassword(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String pass = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        User resultUser = loginService.findUserByUsername(username);
        if (resultUser != null && !resultUser.getPassword().equals(StringUtil.md5Hash(pass))){
            nosession = true;
            checkUsernameAjax = new StringBufferInputStream( "false");
            return Constant.NOSESSION;
        }
        if (resultUser.getId() == -1) {
            nosession = true;
            checkUsernameAjax = new StringBufferInputStream( "false");
            return Constant.NOSESSION;
        }
        checkUsernameAjax = new StringBufferInputStream( "true");
        return SUCCESS;
    }

    public InputStream getCheckUsernameAjax() {
        return checkUsernameAjax;
    }

    public void setCheckUsernameAjax(InputStream checkUsernameAjax) {
        this.checkUsernameAjax = checkUsernameAjax;
    }

    public String dev_testNextShomareSanad() throws Exception{
        Credebit credebit = asnadeSodorService.findCretebitById(14760902);
        Pishnehad pishnehad = credebit.getPishnehad();
        String num= sequenceService.generateNextShomareMoshtari(pishnehad.getKarshenas().getMojtamaSodoor(), "9", "120", credebit.getRemainingAmount_long());
        credebit.setShomareMoshtari(num);
        asnadeSodorService.updateCredebit(credebit);
        addActionMessage(num);
        return SUCCESS;
    }

    public String dev_deleteSanad(){
        asnadeSodorService.deleteSanad(26342932);
        return SUCCESS;
    }

    public String dev_sanadZaniII()
    {
        Credebit cr=asnadeSodorService.findCretebitById(credebitId);
        Credebit bd=asnadeSodorService.findCretebitById(bedehiId);
        List<Credebit> bedehi=new ArrayList<Credebit>();
        List<Credebit> etebarr=new ArrayList<Credebit>();
        bedehi.add(bd);
        etebarr.add(cr);
        asnadeSodorService.sabteSanad(bedehi, etebarr, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, null, true);
        return SUCCESS;
    }

    public String dev_sanadZani()
    {
        List<Credebit> bedehiVamList=asnadeSodorService.findCredebit(identifier, Credebit.CredebitType.GHEST_VAM);
        List<Credebit> etebarVamList=asnadeSodorService.findCredebit(identifier, Credebit.CredebitType.DARYAFTE_ELECTRONICI);

        bedehiList = new ArrayList<Credebit>();
        etebarList = new ArrayList<Credebit>();


        for(Credebit etebar : etebarVamList)
        {
            if(etebar.getKhateSanadsEtebar().size()==0 && etebar.getAmount_long().equals(etebar.getRemainingAmount_long()))
            {
                etebarList.add(etebar);
            }
        }

        for (Credebit bedehi : bedehiVamList)
        {
            if (bedehi.getGhest().getGhestBandi().getDarkhastList().size()!=0 && bedehi.getKhateSanadsEtebar().size()==0 && bedehi.getAmount_long().equals(bedehi.getRemainingAmount_long()))
            {
                bedehiList.add(bedehi);
            }
        }
        return SUCCESS;
    }
    public String dev_FindSanad()
    {
        List<Integer> khateSanadList = asnadeSodorService.hazfeSanad("1393/11/04", Credebit.CredebitType.VEHICLE_HAGHBIME);
        System.out.println(khateSanadList.size());
        for (Integer kh: khateSanadList)
        {
           asnadeSodorService.deleteSanad(kh);
        }
        return SUCCESS;
    }
    public String dev_sabteSanadACH(){
        List<Credebit> credebitACHs = asnadeSodorService.findCredebit("", Credebit.CredebitType.ACH);

        for (Credebit credebitACH : credebitACHs){
            if (credebitACH != null && credebitACH.getRemainingAmount_long().equals(credebitACH.getAmount_long())){
                List<Credebit> credebitBazkharid = asnadeSodorService.findCredebit(credebitACH.getIdentifier(), Credebit.CredebitType.AZ_MAHALLE_BAZKHARID);
                List<Credebit> credebitEbtal = asnadeSodorService.findCredebit(credebitACH.getIdentifier(), Credebit.CredebitType.ETEBAR_EBTAL);
                List<Credebit> credebitVam = asnadeSodorService.findCredebit(credebitACH.getIdentifier(), Credebit.CredebitType.ETEBAR_VAM);

                if (credebitBazkharid != null && credebitEbtal != null && credebitVam != null && (credebitBazkharid.size() + credebitEbtal.size() + credebitVam.size() == 1)){
                    List<Credebit> credebitAchTemp = new ArrayList<Credebit>();
                    credebitAchTemp.add(credebitACH);
                    if (credebitBazkharid.size() > 0 && credebitBazkharid.get(0).getRemainingAmount_long().equals(credebitBazkharid.get(0).getAmount_long()))
                        asnadeSodorService.sabteSanad(credebitAchTemp,credebitBazkharid, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM,null,true);
                    else if (credebitEbtal.size() > 0 && credebitEbtal.get(0).getRemainingAmount_long().equals(credebitEbtal.get(0).getAmount_long()))
                        asnadeSodorService.sabteSanad(credebitAchTemp,credebitEbtal, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM,null,true);
                    else if (credebitVam.size() > 0 && credebitVam.get(0).getRemainingAmount_long().equals(credebitVam.get(0).getAmount_long()))
                        asnadeSodorService.sabteSanad(credebitAchTemp,credebitVam, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM,null,true);
                }
            }
        }
        return SUCCESS;
    }

    public String   gozareshSoratVaziyateMaliBimename(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);

        }

        return SUCCESS;

    }
    public String   gozareshkoliEtebarvaBedehi(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);

        }

        return SUCCESS;

    }
    public String dev_testURLPath() {
        System.out.println(StringUtil.getDeploymentPath());
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/01/02", "1392/12/28"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/01/03", "1392/12/28"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/01/04", "1392/12/28"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/01/01", "1392/12/28"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/01/30", "1393/01/29"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/02/05", "1393/01/29"));
        System.out.println(DateUtil.getTimeDifferenceByDayCount("1393/02/05", "1392/01/29"));
        return SUCCESS;
    }


    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public PaginatedListImpl<Credebit> getCredebitListPaginated() {
        return credebitListPaginated;
    }

    public void setCredebitListPaginated(PaginatedListImpl<Credebit> credebitListPaginated) {
        this.credebitListPaginated = credebitListPaginated;
    }

    public Credebit.Status getStatusFarsi() {
        return statusFarsi;
    }

    public void setStatusFarsi(Credebit.Status statusFarsi) {
        this.statusFarsi = statusFarsi;
    }

    public String getShomareGharardad() {
        return shomareGharardad;
    }

    public void setShomareGharardad(String shomareGharardad) {
        this.shomareGharardad = shomareGharardad;
    }

    public Credebit.CredebitType getCredebitTypeFarsi() {
        return credebitTypeFarsi;
    }

    public void setCredebitTypeFarsi(Credebit.CredebitType credebitTypeFarsi) {
        this.credebitTypeFarsi = credebitTypeFarsi;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getPaidReceivedAmount() {
        return paidReceivedAmount;
    }

    public void setPaidReceivedAmount(String paidReceivedAmount) {
        this.paidReceivedAmount = paidReceivedAmount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getSarresidDate() {
        return sarresidDate;
    }

    public void setSarresidDate(String sarresidDate) {
        this.sarresidDate = sarresidDate;
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Credebit.CredebitMainType getCredebitNoe() {
        return credebitNoe;
    }

    public void setCredebitNoe(Credebit.CredebitMainType credebitNoe) {
        this.credebitNoe = credebitNoe;
    }

    public Credebit.VaziyatVosoul getVosoulStateFarsi() {
        return vosoulStateFarsi;
    }

    public void setVosoulStateFarsi(Credebit.VaziyatVosoul vosoulStateFarsi) {
        this.vosoulStateFarsi = vosoulStateFarsi;
    }

    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public PaginatedListImpl<Vagozari> getVagozariPaginatedList() {
        return vagozariPaginatedList;
    }

    public void setVagozariPaginatedList(PaginatedListImpl<Vagozari> vagozariPaginatedList) {
        this.vagozariPaginatedList = vagozariPaginatedList;
    }

    public PaginatedListImpl<Credebit> getBedehiCredebitListPaginated() {
        return bedehiCredebitListPaginated;
    }

    public void setBedehiCredebitListPaginated(PaginatedListImpl<Credebit> bedehiCredebitListPaginated) {
        this.bedehiCredebitListPaginated = bedehiCredebitListPaginated;
    }

    public PaginatedListImpl<Credebit> getEtebarCredebitListPaginated() {
        return etebarCredebitListPaginated;
    }

    public void setEtebarCredebitListPaginated(PaginatedListImpl<Credebit> etebarCredebitListPaginated) {
        this.etebarCredebitListPaginated = etebarCredebitListPaginated;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSarresidDateFrom() {
        return sarresidDateFrom;
    }

    public void setSarresidDateFrom(String sarresidDateFrom) {
        this.sarresidDateFrom = sarresidDateFrom;
    }

    public String getSarresidDateTo() {
        return sarresidDateTo;
    }

    public void setSarresidDateTo(String sarresidDateTo) {
        this.sarresidDateTo = sarresidDateTo;
    }

    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(String createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public String getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(String createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public InputStream getDeleteEtebarAjax() {
        return deleteEtebarAjax;
    }

    public void setDeleteEtebarAjax(InputStream deleteEtebarAjax) {
        this.deleteEtebarAjax = deleteEtebarAjax;
    }

    public Integer getCredebitId() {
        return credebitId;
    }

    public void setCredebitId(Integer credebitId) {
        this.credebitId = credebitId;
    }

    public Long getSearch_namayandegiIdEtebarat() {
        return search_namayandegiIdEtebarat;
    }

    public Integer getBedehiId()
    {
        return bedehiId;
    }

    public void setBedehiId(Integer bedehiId)
    {
        this.bedehiId = bedehiId;
    }

    public void setSearch_namayandegiIdEtebarat(Long search_namayandegiIdEtebarat) {
        this.search_namayandegiIdEtebarat = search_namayandegiIdEtebarat;
    }

    public Long getSearch_namayandegiId() {
        return search_namayandegiId;
    }

    public void setSearch_namayandegiId(Long search_namayandegiId) {
        this.search_namayandegiId = search_namayandegiId;
    }

    public PaginatedListImpl<Credebit> getEtebarMoghayeratCredebitListPaginated() {
        return etebarMoghayeratCredebitListPaginated;
    }

    public void setEtebarMoghayeratCredebitListPaginated(PaginatedListImpl<Credebit> etebarMoghayeratCredebitListPaginated) {
        this.etebarMoghayeratCredebitListPaginated = etebarMoghayeratCredebitListPaginated;
    }

    public Long getSearch_namayandegiIdEtebarat3() {
        return search_namayandegiIdEtebarat3;
    }

    public void setSearch_namayandegiIdEtebarat3(Long search_namayandegiIdEtebarat3) {
        this.search_namayandegiIdEtebarat3 = search_namayandegiIdEtebarat3;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public String getVosoulDesc() {
        return vosoulDesc;
    }

    public void setVosoulDesc(String vosoulDesc) {
        this.vosoulDesc = vosoulDesc;
    }

    public Long getSearch_vahedesodorId() {
        return search_vahedesodorId;
    }

    public void setSearch_vahedesodorId(Long search_vahedesodorId) {
        this.search_vahedesodorId = search_vahedesodorId;
    }

    public PaginatedListImpl<CredebitVM> getBedehiVosulNashodeCredebitListPaginated() {
        return bedehiVosulNashodeCredebitListPaginated;
    }

    public void setBedehiVosulNashodeCredebitListPaginated(PaginatedListImpl<CredebitVM> bedehiVosulNashodeCredebitListPaginated) {
        this.bedehiVosulNashodeCredebitListPaginated = bedehiVosulNashodeCredebitListPaginated;
    }

    public PaginatedListImpl<BankInfo> getBankInfoPaginatedList() {
        return bankInfoPaginatedList;
    }

    public void setBankInfoPaginatedList(PaginatedListImpl<BankInfo> bankInfoPaginatedList) {
        this.bankInfoPaginatedList = bankInfoPaginatedList;
    }

    public PaginatedListImpl<MaliNamayande> getNamayandeListPaginated() {
        return namayandeListPaginated;
    }

    public void setNamayandeListPaginated(PaginatedListImpl<MaliNamayande> namayandeListPaginated) {
        this.namayandeListPaginated = namayandeListPaginated;
    }

    public Integer getFormSanadId() {
        return formSanadId;
    }

    public void setFormSanadId(Integer formSanadId) {
        this.formSanadId = formSanadId;
    }

    public String deleteEtebarOperation(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (asnadeSodorService.validationDeleteEtebar(credebitId, user)){
            asnadeSodorService.deleteCredebitById(credebitId);
            deleteEtebarAjax = new StringBufferInputStream( "true");
        }
        else
            deleteEtebarAjax = new StringBufferInputStream( "false");
        return SUCCESS;
    }
    public String editEtebarOperation()
    {
        Integer credebitId = getLoadedIdEtebarat();
        credebit = asnadeSodorService.findCretebitById(credebitId);
        return SUCCESS;
    }
    public String sabteEtebareDaryaftCheck()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        credebit = asnadeSodorService.createEtebareDasti(credebit,resultUser);
        return SUCCESS;
    }
    public String deleteBedehiOperation(){
        if (asnadeSodorService.validationDeleteBedehi(credebitId)){
            asnadeSodorService.deleteCredebitById(credebitId);
            deleteEtebarAjax = new StringBufferInputStream( "true");
        }
        else
            deleteEtebarAjax = new StringBufferInputStream( "false");
        return SUCCESS;
    }

    public String getSubsystem_field() {
        return subsystem_field;
    }

    public void setSubsystem_field(String subsystem_field) {
        this.subsystem_field = subsystem_field;
    }

    public Long getSearch_namayandegiIdEtebarat4() {
        return search_namayandegiIdEtebarat4;
    }

    public void setSearch_namayandegiIdEtebarat4(Long search_namayandegiIdEtebarat4) {
        this.search_namayandegiIdEtebarat4 = search_namayandegiIdEtebarat4;
    }

    public String getShomareShaba() {
        return shomareShaba;
    }

    public void setShomareShaba(String shomareShaba) {
        this.shomareShaba = shomareShaba;
    }

    public String validationShomareShaba(){
        inputStream = new StringBufferInputStream("true");
        if (getShomareShaba().length() != 24) {
            inputStream = new StringBufferInputStream("false");
        } else {
            BigInteger toCheck = new BigInteger(getShomareShaba().substring(2) + "1827" + getShomareShaba().substring(0, 2));
            if (toCheck.mod(new BigInteger("97")).intValue() != 1) {
                inputStream = new StringBufferInputStream("false");
            }
        }
        return SUCCESS;
    }

    public String validationIdentifierShaba(){
        inputStream = new StringBufferInputStream("true");

        String iden = getIdentifier().substring(0,21);
        iden += "%";
        List<Credebit> credebitsByIdentifier = asnadeSodorService.findCredebitsByIdentifier(iden);
               Iterator<Credebit> iterator=credebitsByIdentifier.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().getCredebitType());
        for (Credebit credebit1 : credebitsByIdentifier){
            if (credebit1.isBedehi() && !credebit1.getAmount_long().equals(0l)){
                if (!credebit1.getRemainingAmount_long().equals(0l)){
                    inputStream = new StringBufferInputStream("false");
                    return SUCCESS;
                }
                Credebit.VaziyatVosoul vaziyatVosulBedehi = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getVaziyatVosulBedehi(credebit1.getId());
                System.out.println("vaziat vosoul"+vaziyatVosulBedehi.toString());
                if (!vaziyatVosulBedehi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)){
                    inputStream = new StringBufferInputStream("false");
                    return SUCCESS;
                }

            }
        }

        return SUCCESS;
    }

    //b-h
    public String listBedehihayeDaftarParsian(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;

        if (username != null)
            user = loginService.findUserByUsername(username);
//        int page = PagingUtil.getPageNumberFromContext("pageNumber_enteghalBedehiParsianBeNamayande");
        if(isExport())
        {

            ParsianCredebitsListPaginated=new PaginatedListImpl<Credebit>();
            ParsianCredebitsListPaginated.setPageNumber(0);
            ParsianCredebitsListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
            ParsianCredebitsListPaginated = asnadeSodorService.findOnlyDaftarParsianCredebits(user,ParsianCredebitsListPaginated,identifier,rcptName,sarresidDateFrom,sarresidDateTo,createdDateFrom,createdDateTo,search_namayandegiId,search_vahedesodorId,bazaryabSanamId);
        }
        else{

            ParsianCredebitsListPaginated=new PaginatedListImpl<Credebit>();
            ParsianCredebitsListPaginated.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            ParsianCredebitsListPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            ParsianCredebitsListPaginated = asnadeSodorService.findOnlyDaftarParsianCredebits(user,ParsianCredebitsListPaginated,identifier,rcptName,sarresidDateFrom,sarresidDateTo,createdDateFrom,createdDateTo,search_namayandegiId,search_vahedesodorId,bazaryabSanamId);
        }

       return SUCCESS;
    }
     //b-h
    public String enteghalTakiBedehiBeDaftarNamayande(){
        List<Integer> bedehiId=new ArrayList<Integer>();
        bedehiId.add(bedehiId_ForEnteghal);
        enteghalBedehiBeDaftarNamayande(bedehiId);
        return SUCCESS;
    }
    //b-h
    private void enteghalBedehiBeDaftarNamayande(List<Integer> bedehiList){
        Integer daftar_id=(Integer)getFromSession("daftar_id");
        Daftar daftar=asnadeSodorService.findDaftarById(daftar_id);
        for(Integer bedehiId:bedehiList){
            Credebit bedehiParsian=asnadeSodorService.findCretebitById(bedehiId);
            String username=SecurityContextHolder.getContext().getAuthentication().getName();
            Credebit namayandeBedehi=asnadeSodorService.enteghalBedehiBeDaftarNamayande(bedehiParsian,daftar,1) ;
        }
    }
    //b-h
    public String enteghalGoruhiBedehiBeDaftarNamayande(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            List<Integer> bedehiIds = new ArrayList<Integer>();
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] credebitIds = (String[]) ActionContext.getContext().getParameters().get(name);
                        for (String idString : credebitIds) {
                             bedehiIds.add (Integer.parseInt(idString));
                        }
                    }
                }
            enteghalBedehiBeDaftarNamayande(bedehiIds);
            }
        }
        return SUCCESS;
    }

    //b-h
    public String listBedehiHayeTasviyeNashodeNamayande(){
        setTodayDate(DateUtil.getCurrentDate());
        setTwoDaysLaterDate(DateUtil.addDays(DateUtil.getCurrentDate(),2));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        boolean search = false;
        if(searchPage.equals("yes"))
            search = true;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        else{
            return Constant.NOSESSION;//"nosession";
        }

        int page = PagingUtil.getPageNumberFromContext("pageNumber_bedehiTasviyeNashode");

        bedehiTasviyeNashodePaginatedList=asnadeSodorService.listBedehiTasviyeNashodeNamayande(page,user, identifier, rcptName, sarresidDateFrom, sarresidDateTo, createdDateFrom, createdDateTo, amount, remainingAmount, search_namayandegiId5, search_vahedesodorId5,bazaryabSanamIdBedehi,bedehi_color,reshte,consortium, search);
        System.out.println(bedehiTasviyeNashodePaginatedList.getList().toArray().toString());
        return SUCCESS;
    }
    /// ta inja
    public PaginatedListImpl<Credebit> getParsianCredebitsListPaginated() {
        return ParsianCredebitsListPaginated;
    }

    public void setParsianCredebitsListPaginated(PaginatedListImpl<Credebit> parsianCredebitsListPaginated) {
        ParsianCredebitsListPaginated = parsianCredebitsListPaginated;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getLoadedIdEtebarat() {
        return loadedIdEtebarat;
    }

    public void setLoadedIdEtebarat(Integer loadedIdEtebarat) {
        this.loadedIdEtebarat = loadedIdEtebarat;
    }

    public String getCodeMelli() {
        return codeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        this.codeMelli = codeMelli;
    }
    public String getCredebitIds() {
        return credebitIds;
    }

    public void setCredebitIds(String credebitIds) {
        this.credebitIds = credebitIds;
    }

    public Integer getSanadId() {
        return sanadId;
    }

    public void setSanadId(Integer sanadId) {
        this.sanadId = sanadId;
    }


    public String getCreebitAmount() {
        return creebitAmount;
    }

    public void setCreebitAmount(String creebitAmount) {
        this.creebitAmount = creebitAmount;
    }

    public String getEpaymentHost()
    {

        return epaymentHost;
    }
    public void setEpaymentHost(String epaymentHost)
    {
        this.epaymentHost = epaymentHost;
    }

    public List<Credebit> getBedehiList()
    {
        return bedehiList;
    }

    public void setBedehiList(List<Credebit> bedehiList)
    {
        this.bedehiList = bedehiList;
    }

    public List<Credebit> getEtebarList()
    {
        return etebarList;
    }

    public void setEtebarList(List<Credebit> etebarList)
    {
        this.etebarList = etebarList;
    }


    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCheckSerri() {
        return checkSerri;
    }

    public void setCheckSerri(String checkSerri) {
        this.checkSerri = checkSerri;
    }

    public String getShomareCheck() {
        return shomareCheck;
    }

    public void setShomareCheck(String shomareCheck) {
        this.shomareCheck = shomareCheck;
    }

    public String getShomareHesab() {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab) {
        this.shomareHesab = shomareHesab;
    }

    public String getSearchPage() {
        return searchPage;
    }

    public void setSearchPage(String searchPage) {
        this.searchPage = searchPage;
    }

    public String getFlagPage() {
        return flagPage;
    }

    public void setFlagPage(String flagPage) {
        this.flagPage = flagPage;
    }

    public String getShomareFish() {
        return shomareFish;
    }

    public void setShomareFish(String shomareFish) {
        this.shomareFish = shomareFish;
    }
}
