package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.viewModel.PishnehadConstants;
import com.bitarts.parsian.webservice.ach.ACHPayment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/30/12
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class KhesaratAction extends BaseAction implements ServletContextAware {
    private ILoginService loginService;
    private IConstantItemsService constantItemsService;
    private ISequenceService sequenceService;
    private IConstantsService constantsService;
    private PishnehadConstants pishnehadConstants;
    private Pishnehad pishnehad;
    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;
    private Estelam estelam;
    private User user;
    private List<User> bazaryabs;
    private boolean readOnlyMode, khesaratAction, noZamaem;
    private Khesarat khesarat;
    private List<User> karshenasKhesaratList;
    private IKhesaratService khesaratService;
    private Boolean kh_omr, kh_moafiat, kh_naghs, kh_hadese, kh_amraz;
    private KhesaratHavale khesaratHavale;
    private List<String> nesbatha;
    private Integer type;
    private String destFileDIR, sourceFilePath, realPath;
    private List<Namayande> vahedSodurs;
    private INamayandeService namayandeService;
    private List<User> karshenasha;
    private List<User> karshenasKhesaratha;
    private List<City> ostanha;
    private Long grouh;
    private List<Gharardad> grouhha;
    private HashMap<String,Object> validationConstants;
    private String sarmayeFot;
    private String sarmayeHadese;
    private String sarmayeNaghsOzv;
    private String sarmayeAmraz;
    private String hazinehayeAti;
    private String andukhte;
    private String havaleHa;
    private String updateMode;
    private String updateOrSave;
    private String movaghat;
    private String shomareHesab;
    private String shomareCheck;
    private String tarikhSarresid;
    private String seri;
    private String successOrFaild;
    private Long khesaratId;
    private InputStream inputStream;
    private String shomareShaba;
    private String noePardakht;
    private IDarkhastService darkhastService;
    private ICheckService checkService;
    private Bimename bimename;
    private ArrayList<Transition> allowedTransitions;
    private List<TransitionLog> transitionLogs;
    private DarkhastBazkharid darkhastBazkharid;
    private String pronouncerOrgName;
    private String darVajhe;
    private String shomareShenasnameh;
    private String codeMelli;
    private String telephoneHamrah;
    private String khesaratNum;

    public String getKhesaratNum()
    {
        return khesaratNum;
    }

    public void setKhesaratNum(String khesaratNum)
    {
        this.khesaratNum = khesaratNum;
    }

    public String getTelephoneHamrah()
    {
        return telephoneHamrah;
    }

    public void setTelephoneHamrah(String telephoneHamrah)
    {
        this.telephoneHamrah = telephoneHamrah;
    }

    public String getCodeMelli()
    {
        return codeMelli;
    }

    public void setCodeMelli(String codeMelli)
    {
        this.codeMelli = codeMelli;
    }

    public String getShomareShenasnameh()
    {
        return shomareShenasnameh;
    }

    public void setShomareShenasnameh(String shomareShenasnameh)
    {
        this.shomareShenasnameh = shomareShenasnameh;
    }

    public String getDarVajhe()
    {
        return darVajhe;
    }

    public void setDarVajhe(String darVajhe)
    {
        this.darVajhe = darVajhe;
    }

    public String getSeri()
    {
        return seri;
    }

    public void setSeri(String seri)
    {
        this.seri = seri;
    }

    public String getTarikhSarresid()
    {
        return tarikhSarresid;
    }

    public void setTarikhSarresid(String tarikhSarresid)
    {
        this.tarikhSarresid = tarikhSarresid;
    }

    public String getShomareCheck()
    {
        return shomareCheck;
    }

    public void setShomareCheck(String shomareCheck)
    {
        this.shomareCheck = shomareCheck;
    }

    public String getShomareHesab()
    {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab)
    {
        this.shomareHesab = shomareHesab;
    }

    public String getPronouncerOrgName() {
        return pronouncerOrgName;
    }

    public void setPronouncerOrgName(String pronouncerOrgName) {
        this.pronouncerOrgName = pronouncerOrgName;
    }

    public Long getKhesaratId()
    {
        return khesaratId;
    }

    public void setKhesaratId(Long khesaratId)
    {
        this.khesaratId = khesaratId;
    }

    public String getSuccessOrFaild()
    {
        return successOrFaild;
    }

    public void setSuccessOrFaild(String successOrFaild)
    {
        this.successOrFaild = successOrFaild;
    }

    public String getMovaghat()
    {
        return movaghat;
    }

    public void setMovaghat(String movaghat)
    {
        this.movaghat = movaghat;
    }

    public String getUpdateOrSave()
    {
        return updateOrSave;
    }

    public void setUpdateOrSave(String updateOrSave)
    {
        this.updateOrSave = updateOrSave;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        realPath = servletContext.getRealPath("/");
        loginService = (ILoginService) wac.getBean("loginService");
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
        pishnehadService = (IPishnehadService) wac.getBean("pishnehadService");
        khesaratService = (IKhesaratService) wac.getBean("khesaratService");
        sequenceService = (ISequenceService) wac.getBean("sequenceService");
        asnadeSodorService = (IAsnadeSodorService) wac.getBean("asnadeSodorService");
        namayandeService = (INamayandeService) wac.getBean("namayandeService");
        checkService = (ICheckService) wac.getBean("checkService");
        constantsService = (IConstantsService) wac.getBean("constantsService");
        darkhastService =  (IDarkhastService) wac.getBean("darkhastService");
    }

    public String prepareKhesarat() {
        khesarat=khesaratService.findById(khesarat.getId());
        if(khesarat!=null && khesarat.getId()!=null && (khesarat.getHavaleList()==null || khesarat.getHavaleList().size()==0))
            havaleHa="null";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        if (resultUser.getId() == -1) {
            return Constant.NOSESSION;
        } else {
            String date = DateUtil.getCurrentDate();
            Collection<String> values = getSession().keySet();
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
            if (khesarat != null && khesarat.getId() != null) {
                khesarat = khesaratService.findById(khesarat.getId());
            }
            if (bimename != null){
                bimename = pishnehadService.findBimenameById(bimename.getId());
                pishnehad = bimename.getPishnehad();
            }
            else if (pishnehad != null){
                pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                estelam = pishnehad.getEstelam();
                validationConstants = AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date);
            }
            else if (khesarat != null){
                darkhastBazkharid = khesarat.getDarkhastBazkharidI()!=null?khesarat.getDarkhastBazkharidI():khesarat.getDarkhastBazkharidII();
                pishnehad = darkhastBazkharid.getBimename().getPishnehad();

            }


            //----- initialize Validation Constants ------------------

            if (pishnehad != null && pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            }

            //--------------------------------------------------------

            karshenasKhesaratha = pishnehadService.findAllKarshenasKhesarat();
            user = resultUser;
            bazaryabs = pishnehadService.findBazaryabForNamayande(resultUser.getNamayandegi());
            setKarshenasha(pishnehadService.findAllKarshenasForPishnehads(""));
            setOstanha(constantItemsService.getOstans());

            if (bimename != null && bimename.getDarkhastBazkharid() != null && darkhastBazkharid != null && darkhastBazkharid.getState() != null && !darkhastBazkharid.getState().getId().equals(Constant.KHESARAT_SABTE_MOVAGHAT))
                retrieveByDarkhastBazkharidId(bimename.getDarkhastBazkharid().getId(),resultUser.getId());
            else if (darkhastBazkharid != null && darkhastBazkharid.getId() != null && !darkhastBazkharid.getState().getId().equals(Constant.KHESARAT_SABTE_MOVAGHAT))
                retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(),resultUser.getId());

//            readOnlyMode = false;
//            noZamaem=true;
            khesaratAction = true;
            return SUCCESS;
        }

    }

    public String retrieveByDarkhastBazkharidId(Integer darkhastBazkharidId, Long userId) {
        DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharidId);
        allowedTransitions = new ArrayList<Transition>();
        //allowedTransitions = pishnehadService.findAllowedTransitionsForId(userId,reportResult.get(0).getId());
        /*for (Pishnehad pishnehad: reportResult){
            for(Transition transition: pishnehad.getState().getTransitionBegin()){

            }

        }*/
        Set<Transition> allTheTransitions = theDarkhast.getState().getTransitions();
        for (Transition transition : allTheTransitions) {
            if (transition.getStateBegin().equals(theDarkhast.getState()) && transition.getTransitionShow().equals("yes")) {
                for (Role role : transition.getRoles()) {
                    for (User user : role.getUsers()) {
                        if (user.getId().equals(userId)) {
                            allowedTransitions.add(transition);
                        }
                    }
                }
            }
        }
        Collections.sort(allowedTransitions);
        //setTransitionLogs(getTransitionLogService().getDarkhastBazkharidLogs(darkhastBazkharid.getId()));
//        Collections.sort(getTransitionLogs());
        return SUCCESS;
    }

    public String validationShomareShabaAjax(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        boolean inputStreamBool=true;//        inputStream = new StringBufferInputStream("true");
        if (getShomareShaba().length() != 24) {
            inputStreamBool=false; //inputStream = new StringBufferInputStream("false");
        } else {
            BigInteger toCheck = new BigInteger(getShomareShaba().substring(2) + "1827" + getShomareShaba().substring(0, 2));
            if (toCheck.mod(new BigInteger("97")).intValue() != 1) {
                inputStreamBool=false;//inputStream = new StringBufferInputStream("false");
            }
        }
        if (darkhastBazkharid.getId() != null && inputStreamBool)
        {
            darkhastBazkharid= darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            Long amount= Long.parseLong(StringUtil.removeNoneDigits(darkhastBazkharid.getFinalAmountKhesarat()));
            String identifier=sequenceService.nextShenaseCredebit();
            Credebit credebitEtebar = new Credebit(amount.toString(), identifier, darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ETEBAR_KHESARAT);
            credebitEtebar.setNamayande(resultUser.getNamayandegi());
            credebitEtebar.setVahedeSodor(resultUser.getNamayandegi());
            asnadeSodorService.saveCredebitObj(credebitEtebar);
            darkhastBazkharid.setCredebitKhesarat(credebitEtebar);
            darkhastService.updateDarkhastBazkharid(darkhastBazkharid);

            if (amount > 0l && amount <= 200000000l && shomareShaba.length() > 10)
            {
                String response = ACHPayment.achPaymentRequest(identifier, amount.intValue(), "درخواست خسارت بیمه نامه " + darkhastBazkharid.getBimename().getShomare(),
                                                               darkhastBazkharid.getSahebHesab(), shomareShaba,
                                                               shomareShenasnameh, codeMelli,resultUser.getUsername(), telephoneHamrah, "111116");
                if (!response.contains("-"))
                {
                    Credebit credebitACH = new Credebit(amount.toString(), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ACH);
                    credebitACH.setRahgiriACH(response);
                    asnadeSodorService.saveCredebit(credebitACH);
                    asnadeSodorService.sabteSanad(user, credebitACH, credebitEtebar, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
                }
                else
                {
                    final org.slf4j.Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
                    logger.info(String.format("ACH Failed ! credebit identifier: %s bimename.shomare: %s ach response: %s", identifier, darkhastBazkharid.getBimename().getShomare(), response));
                }
            }
        }
        inputStream = inputStreamBool ?  new StringBufferInputStream("true"): new StringBufferInputStream("false");
        return SUCCESS;
    }


    public String validationCheckAjax(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        inputStream = new StringBufferInputStream("true");


        if (darkhastBazkharid != null){
            darkhastBazkharid=darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            Long amount =0l;//todo:mablaghe Khesarat. . .
            Check check= new Check(shomareCheck,darVajhe,tarikhSarresid,amount.toString(), Check.Status.NORMAL,null);
            checkService.saveOrUpdateCheck(check);
            check = checkService.findCheckById(check.getId());
            Credebit credebitCheck = new Credebit(amount.toString(), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.PARDAKHTE_CHECK);
            credebitCheck.setNamayande(resultUser.getNamayandegi());
            credebitCheck.setVahedeSodor(resultUser.getNamayandegi());
            credebitCheck.setCheck(check);
            asnadeSodorService.saveCredebitObj(credebitCheck);
            //todo: credebit be should to darkhast
            Credebit credebitEtebar = new Credebit(amount.toString(), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ETEBAR_KHESARAT);
            credebitEtebar.setNamayande(resultUser.getNamayandegi());
            credebitEtebar.setVahedeSodor(resultUser.getNamayandegi());
            asnadeSodorService.saveCredebitObj(credebitEtebar);

            asnadeSodorService.sabteSanad(user,credebitCheck,credebitEtebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
        }

        return SUCCESS;
    }

    public String validateCalculateKhesaratAjaxly()
    {
        boolean error=false;
        DarkhastBazkharid db=darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        Bimename bimename = db.getBimename();

        if(khesaratNum.equals("II"))//mohasebe khesarateII
        {
            khesarat=darkhastBazkharid.getKhesaratII();
        }
        else
        {
            khesarat=darkhastBazkharid.getKhesaratI();
        }
        if(khesarat.getAccidentDate()!=null && !khesarat.getAccidentDate().isEmpty())
        {
            String accidentDate = DateUtil.getCurrentDate();
            if(db.getTarikhDarkhast()!=null && !db.getTarikhDarkhast().isEmpty())
                accidentDate=db.getTarikhDarkhast();
            if(DateUtil.isGreaterThan(bimename.getTarikhShorou(), khesarat.getAccidentDate()))
            {
                addActionError("تاریخ وقوع حادثه نباید قبل از تاریخ شروع بیمه نامه ("+ bimename.getTarikhShorou()+ " )باشد. ");
                error=true;
            }

            if(DateUtil.isGreaterThan(khesarat.getAccidentDate(),accidentDate))
            {
                addActionError("تاریخ وقوع حادثه نباید بعد از تاریخ ثبت درخواست خسارت ("+ accidentDate + " )باشد. ");
                error=true;
            }
            if (error==false && asnadeSodorService.findGhestBandi(bimename.getId(), DateUtil.getBimeYear(khesarat.getAccidentDate(), bimename.getTarikhShorou())) == null)
            {
                addActionError("سال " + khesarat.getAccidentDate().substring(0, 4) + " تقسیط نشده است. ");
                error = true;
            }
        }
        return error ? INPUT : SUCCESS;
    }

    public String calculateKhesaratAjaxly() throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        DarkhastBazkharid db = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        Bimename bimename = db.getBimename();
        if (khesaratNum.equals("II"))//mohasebe khesarateII
        {
            khesarat = darkhastBazkharid.getKhesaratII();
        }
        else
        {
            khesarat = darkhastBazkharid.getKhesaratI();
        }
        if(khesarat.getAccidentDate() != null && !khesarat.getAccidentDate().isEmpty())
            CredebitAction.mohasebeAndukhteBimename(bimename, khesarat.getAccidentDate(),bimename.getCashFlow(khesarat.getAccidentDate()));
        andukhte = NumberFormat.getNumberInstance().format(bimename.getAndukhteyeGhatie());
        if(khesarat.getAccidentDate()!=null && !khesarat.getAccidentDate().isEmpty())
            hazinehayeAti =  NumberFormat.getNumberInstance().format(bimename.getHazineGhabeleKasrTaPayanSal(khesarat.getAccidentDate()));
        MohasebateTeory mohasebateTeory = new MohasebateTeory();
        List<TaghsitReport> taghsitReport = mohasebateTeory.taghsitReport(bimename.getPishnehad(), true, -1);
        TaghsitReport tr=null;
        if(khesarat.getAccidentDate()!=null && !khesarat.getAccidentDate().isEmpty())
        {
            tr = taghsitReport.get(DateUtil.getBimeYear(khesarat.getAccidentDate(),bimename.getTarikhShorou()));
        }
        else
        {
            tr = taghsitReport.get(bimename.getCurrentSaleBimei());
        }
        Long jameAghsatePardakhtiAti=0l;
        for(GhestBandi gb : bimename.getGhestBandiList())
        {
            if(gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                for(Ghest g : gb.getGhestList())
                {
                    for(KhateSanad kh : g.getCredebit().getKhateSanadsBedehi())
                    {
                        if(DateUtil.isGreaterThan(kh.getEtebarCredebit().getDateFish(), khesarat.getAccidentDate()))
                            jameAghsatePardakhtiAti+=Long.parseLong(kh.getAmount().trim().replaceAll(",", ""));
                    }
                }
        }
        khesarat.setJameAghsatePardakhtiAti(NumberFormat.getInstance().format(jameAghsatePardakhtiAti));
        sarmayeFot = NumberFormat.getNumberInstance().format(tr.getSarmaayeFotBehHarEllat());
        sarmayeAmraz = NumberFormat.getNumberInstance().format(tr.getSarmaayePusheshEzaafiAmraazKhaas());
        sarmayeHadese = NumberFormat.getNumberInstance().format(tr.getSarmaayeFotDarAsarHaadeseh());
        sarmayeNaghsOzv = NumberFormat.getNumberInstance().format(tr.getSarmaayePusheshEzaafiNaghsOzv());

        Long allAmountsKhesaratAmraz = 0l;
        Long allAmountsKhesaratNaghsOzv = 0l;
        for(Khesarat kh : bimename.getKhesarats())
        {
            if(kh.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
            {
                sarmayeAmraz=kh.getSarmayeAmraz().toString();
                allAmountsKhesaratAmraz += Long.parseLong(kh.getAmountTaidShode().replaceAll(",","").trim());
            }
            if(kh.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
            {
                sarmayeNaghsOzv = kh.getSarmayeNaghseOzv().toString();
                allAmountsKhesaratNaghsOzv += Long.parseLong(kh.getAmountTaidShode().replaceAll(",","").trim());
            }
        }
        sarmayeAmraz= NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeAmraz.replaceAll(",","").trim()) - allAmountsKhesaratAmraz);
        sarmayeNaghsOzv= NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeNaghsOzv.replaceAll(",","").trim()) - allAmountsKhesaratNaghsOzv);
        if(khesarat.getKhesaratType().equals(Khesarat.KhesaratType.OMR))
        {
            khesarat.setAmountElamShode(sarmayeFot);
        }
        else if (khesarat.getKhesaratType().equals(Khesarat.KhesaratType.HADESE))
        {
            khesarat.setAmountElamShode(NumberFormat.getInstance().format(Long.parseLong(sarmayeFot.replaceAll(",","").trim())+ Long.parseLong(sarmayeHadese.replaceAll(",", "").trim())));
        }
        else if (khesarat.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
        {
            hazinehayeAti="0";
//            if(Long.parseLong(khesarat.getAmountElamShode()!=null&&!khesarat.getAmountElamShode().equals("")?khesarat.getAmountElamShode().trim().replaceAll(",",""):"0")>Long.parseLong(sarmayeAmraz.trim().replaceAll(",", "")))
//                khesarat.setAmountElamShode(sarmayeAmraz);
                khesarat.setJameAghsatePardakhtiAti("0");
        }
        else if (khesarat.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
        {
            hazinehayeAti = "0";
            khesarat.setAmountElamShode(sarmayeNaghsOzv);
            khesarat.setJameAghsatePardakhtiAti("0");
        }
        else if (khesarat.getKhesaratType().equals(Khesarat.KhesaratType.MOAFIAT))
        {
            hazinehayeAti = "0";
            khesarat.setJameAghsatePardakhtiAti("0");
        }

        if (khesaratNum.equals("II"))
        {
            db.getKhesaratII().setAccidentDate(khesarat.getAccidentDate());
            db.getKhesaratII().setAmountElamShode(khesarat.getAmountElamShode());
            db.getKhesaratII().setAndukhte(andukhte);
            db.getKhesaratII().setAmountAti(hazinehayeAti);
            db.getKhesaratII().setAmountTaidShode(khesarat.getAmountTaidShode());
            db.getKhesaratII().setAmountErfagh(khesarat.getAmountErfagh());
            db.getKhesaratII().setJameAghsatePardakhtiAti(khesarat.getJameAghsatePardakhtiAti());
            db.getKhesaratII().setSarmayeAmraz(Long.parseLong(sarmayeAmraz.trim().replaceAll(",", "")));
            db.getKhesaratII().setSarmayeNaghseOzv(Long.parseLong(sarmayeNaghsOzv.trim().replaceAll(",", "")));
            khesaratService.updateKhesarat(db.getKhesaratII());
        }
        else
        {
            db.getKhesaratI().setAccidentDate(khesarat.getAccidentDate());
            db.getKhesaratI().setAmountElamShode(khesarat.getAmountElamShode());
            db.getKhesaratI().setAndukhte(andukhte);
            db.getKhesaratI().setAmountAti(hazinehayeAti);
            db.getKhesaratI().setAmountTaidShode(khesarat.getAmountTaidShode());
            db.getKhesaratI().setAmountErfagh(khesarat.getAmountErfagh());
            db.getKhesaratI().setJameAghsatePardakhtiAti(khesarat.getJameAghsatePardakhtiAti());
            db.getKhesaratI().setSarmayeAmraz(Long.parseLong(sarmayeAmraz.trim().replaceAll(",", "")));
            db.getKhesaratI().setSarmayeNaghseOzv(Long.parseLong(sarmayeNaghsOzv.trim().replaceAll(",", "")));
            khesaratService.updateKhesarat(db.getKhesaratI());
        }
        darkhastBazkharid=db;
        successOrFaild="success";
        return SUCCESS;
    }



    public String validateSabtKhesarat() {
        boolean error = false;
        if (khesarat != null)
            return SUCCESS;
        if (khesarat.getSharhKhesarat() == null || khesarat.getSharhKhesarat().isEmpty()) {
            addActionError("فیلد شرح خسارت الزامی می باشد.");
            error = true;
        }
//        if (khesarat.getEllat() == null || khesarat.getEllat().isEmpty()) {
//            addActionError("فیلد " + "علت حادثه" + " الزامی می باشد.");
//            error = true;
//        }
        if (khesarat.getNahveElam() == null || khesarat.getNahveElam().isEmpty()) {
//            addActionError("فیلد " + "نحوه اعلام خسارت" + " الزامی می باشد.");
//            error = true;
        }
        if (khesarat.getShahrMahalleHadese().getCityId()==null || constantItemsService.findCityById(khesarat.getShahrMahalleHadese().getCityId()) == null) {
            addActionError("فیلد " + "شهر محل حادثه " + " الزامی می باشد.");
            error = true;
        }

        if (khesarat.getAmountElamShode() == null || khesarat.getAmountElamShode().isEmpty()) {
            addActionError("فیلد " + "مبلغ خسارت اعلام شده" + " الزامی می باشد.");
            error = true;
        }
        if (khesarat.getAmountTaidShode() == null || khesarat.getAmountTaidShode().isEmpty()) {
            addActionError("فیلد " + "مبلغ خسارت تایید شده" + " الزامی می باشد.");
            error = true;
        }
        if (khesarat.getAmountErfagh() == null || khesarat.getAmountErfagh().isEmpty()) {
            addActionError("فیلد " + "ارفاق مدیریتی" + " الزامی می باشد.");
            error = true;
        }
        if (khesarat.getAmountMazad() == null || khesarat.getAmountMazad().isEmpty()) {
            addActionError("فیلد " + "مازاد بر سقف" + " الزامی می باشد.");
            error = true;
        }
//        if (khesarat.getKarshenasKhesarat() == null || loginService.findUserById(khesarat.getKarshenasKhesarat().getId()) == null) {
//            addActionError("فیلد " + "کارشناس خسارت" + " الزامی می باشد.");
//            error = true;
//        }
        if (khesarat.getNazarKarshenas() == null || khesarat.getNazarKarshenas().isEmpty()) {
//            addActionError("فیلد " + "نظریه کارشناس" + " الزامی می باشد.");
//            error = true;
        }
        if (khesarat.getAccidentDate() == null || khesarat.getAccidentDate().isEmpty()) {
            addActionError("فیلد " + "تاریخ وقوع حادثه" + " الزامی می باشد.");
            error = true;
        }
        if (khesarat.getBimename().getId() == null) {
            addActionError("خطا نامشخص دوباره وارد سیستم شوید.");
            error = true;
        }
        if (kh_amraz == null && kh_hadese == null && kh_moafiat == null && kh_naghs == null && kh_omr == null) {
            addActionError("نوع مورد خسارت مشخص نشده است.");
            error = true;
        }
        //readOnlyMode = true;
        khesaratAction = true;
        prepareKhesarat();
        return error ? INPUT : SUCCESS;
    }

    public String editShowFormKhesaratReadOnly() throws ServiceException, RemoteException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);

        if (resultUser.getId() == -1) {
            return Constant.NOSESSION;
        } else {
            Collection<String> values = getSession().keySet();
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
            pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
            //----- initialize Validation Constants ------------------
            String date = DateUtil.getCurrentDate();
            if (pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            }
            validationConstants = AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date);
            //--------------------------------------------------------
            estelam = pishnehad.getEstelam();
            user = resultUser;
            bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());

            if (Constant.DEV_nezaratEnabled)
                karshenasha = pishnehadService.findAllKarshenasForPishnehads(pishnehad.getEstelam().getSarmaye_paye_fot_long());
            else
                karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            karshenasKhesaratha = pishnehadService.findAllKarshenasKhesarat();
            ostanha = constantItemsService.getOstans();
            darkhastBazkharid = getBimename().getDarkhastBazkharid();
            khesaratAction = true;
            return SUCCESS;
        }
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public PishnehadConstants getPishnehadConstants() {
        return pishnehadConstants;
    }

    public void setPishnehadConstants(PishnehadConstants pishnehadConstants) {
        this.pishnehadConstants = pishnehadConstants;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getBazaryabs() {
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    public boolean isReadOnlyMode() {
        return readOnlyMode;
    }

    public void setReadOnlyMode(boolean readOnlyMode) {
        this.readOnlyMode = readOnlyMode;
    }

    public boolean isKhesaratAction() {
        return khesaratAction;
    }

    public boolean isNoZamaem()
    {
        return noZamaem;
    }

    public void setNoZamaem(boolean noZamaem)
    {
        this.noZamaem = noZamaem;
    }

    public void setKhesaratAction(boolean khesaratAction) {
        this.khesaratAction = khesaratAction;
    }

    public Khesarat getKhesarat() {
        return khesarat;
    }

    public void setKhesarat(Khesarat khesarat) {
        this.khesarat = khesarat;
    }

    public List<User> getKarshenasKhesaratList() {
        if (karshenasKhesaratList == null) karshenasKhesaratList = loginService.findAllUsersForKarshenas();
        return karshenasKhesaratList;
    }

    public void setKarshenasKhesaratList(List<User> karshenasKhesaratList) {
        this.karshenasKhesaratList = karshenasKhesaratList;
    }

    public IKhesaratService getKhesaratService() {
        return khesaratService;
    }

    public void setKhesaratService(IKhesaratService khesaratService) {
        this.khesaratService = khesaratService;
    }

    public Boolean getKh_omr() {
        return kh_omr;
    }

    public void setKh_omr(Boolean kh_omr) {
        this.kh_omr = kh_omr;
    }

    public Boolean getKh_moafiat() {
        return kh_moafiat;
    }

    public void setKh_moafiat(Boolean kh_moafiat) {
        this.kh_moafiat = kh_moafiat;
    }

    public Boolean getKh_naghs() {
        return kh_naghs;
    }

    public void setKh_naghs(Boolean kh_naghs) {
        this.kh_naghs = kh_naghs;
    }

    public Boolean getKh_hadese() {
        return kh_hadese;
    }

    public void setKh_hadese(Boolean kh_hadese) {
        this.kh_hadese = kh_hadese;
    }

    public Boolean getKh_amraz() {
        return kh_amraz;
    }

    public void setKh_amraz(Boolean kh_amraz) {
        this.kh_amraz = kh_amraz;
    }

    public String prepareKhesaratHavale() {
        if (khesaratHavale != null && khesaratHavale.getId() != null)
            khesaratHavale = khesaratService.findHavaleById(khesaratHavale.getId());
        khesarat=khesaratService.findById(khesarat.getId());
        return SUCCESS;
    }

//    public String validateAddKhesaratHavale() {
//        boolean error = false;
//        if (khesaratHavale.getShomareHesab() == null || khesaratHavale.getShomareHesab().isEmpty()) {
////            addActionError("فیلد " + "شماره حساب" + " الزامی می باشد.");
////            error = true;
//        }
//        if (khesaratHavale.getName() == null || khesaratHavale.getName().isEmpty()) {
//            addActionError("فیلد " + "نام" + " الزامی می باشد.");
//            error = true;
//        }
//        if (khesaratHavale.getNesbatBabimeShode() == null || khesaratHavale.getNesbatBabimeShode().isEmpty()) {
////            addActionError("فیلد " + "نسبت با بیمه شده" + " الزامی می باشد.");
////            error = true;
//        }
//        if (khesaratHavale.getShomareShenasname() == null || khesaratHavale.getShomareShenasname().isEmpty()) {
////            addActionError("فیلد " + "شماره شناسنامه" + " الزامی می باشد.");
////            error = true;
//        }
//        if (khesaratHavale.getDarsadKhesarat() == null) {
////            addActionError("فیلد " + "درصد خسارت" + " الزامی می باشد.");
////            error = true;
//        }
//        return error ? INPUT : SUCCESS;
//    }

    public String addKhesaratHavale() {
        darkhastBazkharid=darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        khesarat = khesaratService.findById(khesarat.getId());
        if(khesaratHavale.getType().equals(KhesaratHavale.Type.ACH))
        {
            BigInteger toCheck = new BigInteger(khesaratHavale.getShomareShaba().substring(2) + "1827" + khesaratHavale.getShomareShaba().substring(0, 2));
            if (toCheck.mod(new BigInteger("97")).intValue() != 1)
            {
                addActionMessage("شماره شبا اشتباه است.");
                return SUCCESS;
            }
        }
        if(khesaratHavale.getId()==null)
        {
            KhesaratHavale havale=new KhesaratHavale();
            havale.setKhesarat(khesarat);
    //        havale.setShomareHavale(khesarat.getShomareParvande() + "/" + khesaratService.createShomareHavale(khesarat));
            havale.setType(khesaratHavale.getType());
    //        havale.setTypeHavale("حواله خسارت");
    //        havale.setAmalkardHavale("حواله پرداختی");
            havale.setAmountHavale(khesaratHavale.getAmountHavale());
            havale.setName(khesaratHavale.getName());
            havale.setFamily(khesaratHavale.getFamily());
            havale.setCodeMelli(khesaratHavale.getCodeMelli());
            havale.setShomareShaba(khesaratHavale.getShomareShaba());
            khesaratService.saveKhesaratHavale(havale);
        }
        else
        {
            KhesaratHavale havale=khesaratService.findHavaleById(khesaratHavale.getId());
            havale.setType(khesaratHavale.getType());
            if(khesaratHavale.getShomareShaba()!=null && khesaratHavale.getShomareShaba()!=""&& !khesaratHavale.getShomareShaba().isEmpty())
                havale.setShomareShaba(khesaratHavale.getShomareShaba());
            if(khesaratHavale.getName()!=null && khesaratHavale.getName()!=""&& !khesaratHavale.getName().isEmpty())
                havale.setName(khesaratHavale.getName());
            if(khesaratHavale.getFamily()!=null && khesaratHavale.getFamily()!=""&& !khesaratHavale.getFamily().isEmpty())
                havale.setFamily(khesaratHavale.getFamily());
            if(khesaratHavale.getCodeMelli()!=null && khesaratHavale.getCodeMelli()!=""&& !khesaratHavale.getCodeMelli().isEmpty())
                havale.setCodeMelli(khesaratHavale.getCodeMelli());
            khesaratService.saveKhesaratHavale(havale);
        }
        return SUCCESS;
    }

    public KhesaratHavale getKhesaratHavale() {
        return khesaratHavale;
    }

    public void setKhesaratHavale(KhesaratHavale khesaratHavale) {
        this.khesaratHavale = khesaratHavale;
    }

    public List<String> getNesbatha() {
        if (nesbatha == null) {
            nesbatha = new LinkedList<String>();
            for (ConstantForPishnehadForm cfpf : constantItemsService.findConstantsForPishnehadForm().getConstantForPishnehadFormList()) {
                if (cfpf.getConstantItemKey().equals(ConstantForPishnehadForm.ConstantItemKey.NESBAT_BA_BIME_SHODE))
                    nesbatha.add(cfpf.getConstantItemValue());
            }
        }
        return nesbatha;
    }

    public void setNesbatha(List<String> nesbatha) {
        this.nesbatha = nesbatha;
    }

    public String delKhesaratHavale() {
        darkhastBazkharid=darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        khesaratService.deleteHavaleById(khesaratHavale.getId());
        return SUCCESS;
    }

    public String validateSabtKhesaratHavale() {
        boolean error = false;
        if (type == 1) {
            prepareKhesarat();
            double tmp = 0;
            for (KhesaratHavale kh : khesarat.getHavaleList()) {
                tmp += kh.getDarsadKhesarat();
            }
            if (tmp < 99.99 || tmp > 100) {
                addActionError("مجموع درصد خسارت های وارد شده برابر با ۱۰۰ نمی شود.");
                error = true;
            }
        }
        return error ? INPUT : SUCCESS;
    }

    public String sabtKhesaratHavale() throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        khesarat = khesaratService.findById(khesarat.getId());
        boolean closeBimename = false;
        boolean isBimeDarJaryan = true;
        if (type == 1) {
            for (KhesaratHavale kh : khesarat.getHavaleList())
            {
                if(!kh.getElamBeMaliShode())
                {
                    Credebit credebit = new Credebit(kh.getAmountHavale(),sequenceService.nextShenaseCredebit(),khesarat.getBimename(),khesarat.getBimename().getPishnehad(),Credebit.CredebitType.ETEBAR_KHESARAT);
                    credebit.setTimeFish(DateUtil.getCurrentTime());
                    credebit.setDateFish(DateUtil.getCurrentDate());
                    asnadeSodorService.saveCredebit(credebit);
                    kh.setElamBeMaliShode(true);
                    kh.setCredebit(credebit);
                    khesaratService.updateHavale(kh);
                }
            }
            if (khesarat.isOmr() || khesarat.isHadese()) {
                Credebit credebit = new Credebit(khesarat.calcElhaghieBargashti(),sequenceService.nextShenaseCredebit(),khesarat.getBimename(),khesarat.getBimename().getPishnehad(),Credebit.CredebitType.ETEBAR_KHESARAT);
                credebit.setTimeFish(DateUtil.getCurrentTime());
                credebit.setDateFish(DateUtil.getCurrentDate());
                asnadeSodorService.saveCredebit(credebit);
                closeBimename = true;
                Bimename bimename = khesarat.getBimename();
                for (GhestBandi gb : bimename.getGhestBandiList())
                {
                    for (Ghest g : gb.getGhestList())
                    {
                        if (g.getTarikhPardakht().isEmpty())
                        {
                            Credebit ghestCredebit = new Credebit(Long.toString(-1 * g.getMajmooHazineha()), sequenceService.nextShenaseCredebit(), bimename, null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                            ghestCredebit.setDateFish(g.getSarresidDate());
                            asnadeSodorService.saveCredebit(ghestCredebit);
                            asnadeSodorService.hazfeGhest(g.getId());
                        }
                    }
                }
            }
            khesarat.setState(constantItemsService.findStateById(Constant.KHESARAT_FIN));
            khesaratService.saveKhesarat(khesarat);
            if(khesarat.isAmraz()) {
                Bimename bimename = khesarat.getBimename();
                MohasebateTeory mohasebateTeory = new MohasebateTeory();
                for(GhestBandi gb : bimename.getGhestBandiList()) {
                    if(gb.getSaleBimeiInt() > bimename.getCurrentSaleBimei()) {
                        List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(bimename.getPishnehad(), gb.getSaleBimeiInt(), bimename.getTarikhShorou(), true);
                        int i = 0;
                        for(Ghest g : gb.getGhestList()) {
                            if(g.getTarikhPardakht().isEmpty()) {
                                asnadeSodorService.hazfeGhest(g.getId());
                                asnadeSodorService.saveGhest(taghsitReport.get(i), gb);
                            }
                            i++;
                        }
                    }
                }
            }
            addActionMessage("خسارت با موفقیت اعلام به مالی شد.");
        } else if (type == 2) {
            khesarat.setState(constantItemsService.findStateById(Constant.KHESARAT_ENSAREF));
            khesaratService.updateKhesarat(khesarat);
            addActionMessage("انصراف داده شد.");
            if (khesarat.isOmr() || khesarat.isHadese())
            {
                closeBimename = true;
            }
        } else if (type == 3) {
            khesarat.setState(constantItemsService.findStateById(Constant.KHESARAT_ER));
            khesaratService.saveKhesarat(khesarat);
            addActionMessage("وضعیت خسارت به تسویه غیر قابل پرداخت تغییر پیدا کرد.");
            if (khesarat.isOmr() || khesarat.isHadese())
            {
                closeBimename = true;
            }
        }
        else if (type==4)
        {
            khesarat.setState(constantItemsService.findStateById(600));
            if (khesarat.getHavaleList() == null || khesarat.getHavaleList().size() == 0)
                havaleHa = "null";
            khesaratService.updateKhesarat(khesarat);
            if (khesarat.getBimename().getState().getId()!= 510) isBimeDarJaryan=false;
        }
        if(closeBimename) {
            khesarat.getBimename().setState(constantItemsService.findStateById(Constant.BIMENAME_BASTE));
        }
        else if(!isBimeDarJaryan)
        {
            khesarat.getBimename().setState(constantItemsService.findStateById(510));
        }
        else {
            khesarat.getBimename().setState(constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE));
        }
        pishnehadService.updateBimename(khesarat.getBimename());
        return SUCCESS;
    }

    public String elamBeMaliHavale()
    {
        KhesaratHavale kh = khesaratService.findHavaleById(khesaratHavale.getId());
        khesarat=kh.getKhesarat();
        if (!kh.getElamBeMaliShode())
        {
            Credebit credebit = new Credebit(kh.getAmountHavale(), sequenceService.nextShenaseCredebit(), khesarat.getBimename(), khesarat.getBimename().getPishnehad(), Credebit.CredebitType.ETEBAR_KHESARAT);
            credebit.setTimeFish(DateUtil.getCurrentTime());
            credebit.setDateFish(DateUtil.getCurrentDate());
            asnadeSodorService.saveCredebit(credebit);
            kh.setElamBeMaliShode(true);
            kh.setCredebit(credebit);
            khesaratService.updateHavale(kh);
            addActionMessage("حواله ی "+kh.getShomareHavale()+ " اعلام به مالی شد. ");
            addActionMessage("شماره شناسه اعتبار ایجاد شده : "+credebit.getShenaseCredebit());
        }
        return SUCCESS;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String printKhesarat() {

        try {
            final String s = prepareKhesarat();
            if (s.equals(SUCCESS)) {
                destFileDIR = realPath + destFileDIR + "KhesaratHavale.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
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

    public String getDestFileDIR() {
        return destFileDIR;
    }

    public void setDestFileDIR(String destFileDIR) {
        this.destFileDIR = destFileDIR;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String printMohasebeKhesarat() {
        try {
            if(movaghat!=null && movaghat.equals("yes"))
            {
                pishnehad=pishnehadService.findById(pishnehad.getId());
                Khesarat theKhesarat= new Khesarat();
                theKhesarat.setAmountGhabelPardakht(khesarat.getAmountGhabelPardakht());
                theKhesarat.setNazarKarshenas(khesarat.getNazarKarshenas());
                theKhesarat.setSharhKhesarat(khesarat.getSharhKhesarat());
                theKhesarat.setAmountMazad(khesarat.getAmountMazad());
                theKhesarat.setAmountErfagh(khesarat.getAmountErfagh());
                theKhesarat.setAmountAti(khesarat.getAmountAti());
                theKhesarat.setAccidentDate(khesarat.getAccidentDate());
                theKhesarat.setAmountElamShode(khesarat.getAmountElamShode());
                theKhesarat.setAmountTaidShode(khesarat.getAmountTaidShode());
                theKhesarat.setBimename(pishnehad.getBimename());

                khesarat=theKhesarat;
                destFileDIR = realPath + destFileDIR + "MohasebeKhesarat.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
            else
            {
                final String s = prepareKhesarat();
                if (s.equals(SUCCESS)) {
                    destFileDIR = realPath + destFileDIR + "MohasebeKhesarat.pdf";
                    boolean b = (new File(realPath + destFileDIR)).mkdirs();
                    File destFile = new File(destFileDIR);
                    if (!destFile.exists()) destFile.createNewFile();
                    JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
                }
            }
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

    public String printTainVaziat() {
        try {
            final String s = prepareKhesarat();
            if (s.equals(SUCCESS)) {
                destFileDIR = realPath + destFileDIR + "TainVaziatKhesarat.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
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

    private Namayande vahedSodur, namayande;
    private String noeGharardad, nameBimeGozar, azTarikh, taTarikh;
    private Integer noeKhesarat;
    private String[] noeGharardads;
    private List<Khesarat> khesaratha;
    private Tarh tarh;
    private List<Tarh> tarhs;

    public String prepareGozareshKhesarat() {
        return SUCCESS;
    }

    public String gozareshKhesarat() {
        City ostan = null;
        if (khesarat.getOstanMahalleHadese() != null && khesarat.getOstanMahalleHadese().getCityId() != null)
            ostan = constantItemsService.findCityById(khesarat.getOstanMahalleHadese().getCityId());
        City shahr = null;
        if (khesarat.getShahrMahalleHadese() != null && khesarat.getShahrMahalleHadese().getCityId() != null)
            shahr = constantItemsService.findCityById(khesarat.getShahrMahalleHadese().getCityId());
        if (namayande != null && namayande.getId() != null)
            namayande = namayandeService.getNamayandeById(namayande.getId());
        if (vahedSodur != null && vahedSodur.getId() != null)
            vahedSodur = namayandeService.getNamayandeById(vahedSodur.getId());
        khesaratha = khesaratService.searchKhesarat(ostan, shahr, namayande, vahedSodur, tarh, noeGharardad, nameBimeGozar, noeKhesarat, khesarat.getEllat(), azTarikh, taTarikh);
        return SUCCESS;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public List<Namayande> getVahedSodurs() {
        if (vahedSodurs == null)
            vahedSodurs = namayandeService.getAllVahedSodurs();
        return vahedSodurs;
    }

    public void setVahedSodurs(List<Namayande> vahedSodurs) {
        this.vahedSodurs = vahedSodurs;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public ICheckService getCheckService()
    {
        return checkService;
    }

    public void setCheckService(ICheckService checkService)
    {
        this.checkService = checkService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public Namayande getVahedSodur() {
        return vahedSodur;
    }

    public void setVahedSodur(Namayande vahedSodur) {
        this.vahedSodur = vahedSodur;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String getNoeGharardad() {
        return noeGharardad;
    }

    public void setNoeGharardad(String noeGharardad) {
        this.noeGharardad = noeGharardad;
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }

    public String getNameBimeGozar() {
        return nameBimeGozar;
    }

    public void setNameBimeGozar(String nameBimeGozar) {
        this.nameBimeGozar = nameBimeGozar;
    }

    public String getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(String azTarikh) {
        this.azTarikh = azTarikh;
    }

    public String getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(String taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Integer getNoeKhesarat() {
        return noeKhesarat;
    }

    public void setNoeKhesarat(Integer noeKhesarat) {
        this.noeKhesarat = noeKhesarat;
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

    public List<Khesarat> getKhesaratha() {
        return khesaratha;
    }

    public void setKhesaratha(List<Khesarat> khesaratha) {
        this.khesaratha = khesaratha;
    }

    public List<User> getKarshenasha() {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public List<City> getOstanha() {
        return ostanha;
    }

    public void setOstanha(List<City> ostanha) {
        this.ostanha = ostanha;
    }

    public Long getGrouh() {
        return grouh;
    }

    public void setGrouh(Long grouh) {
        this.grouh = grouh;
    }

    public List<Gharardad> getGrouhha() {
        if (grouhha == null) {
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//            grouhha = new LinkedList<Gharardad>();
//            User resultUser = loginService.findUserByUsername(username);
//            grouhha = gharardadService.findAllByNamayande(resultUser.getNamayandegi().getId());
            grouhha = new ArrayList<Gharardad>();
        }
        return grouhha;
    }

    public void setGrouhha(List<Gharardad> grouhha) {
        this.grouhha = grouhha;
    }

    public HashMap<String, Object> getValidationConstants() {
        return validationConstants;
    }

    public void setValidationConstants(HashMap<String, Object> validationConstants) {
        this.validationConstants = validationConstants;
    }

    public String getSarmayeFot() {
        return sarmayeFot;
    }

    public void setSarmayeFot(String sarmayeFot) {
        this.sarmayeFot = sarmayeFot;
    }

    public String getSarmayeHadese() {
        return sarmayeHadese;
    }

    public void setSarmayeHadese(String sarmayeHadese) {
        this.sarmayeHadese = sarmayeHadese;
    }

    public String getHazinehayeAti() {
        return hazinehayeAti;
    }

    public void setHazinehayeAti(String hazinehayeAti) {
        this.hazinehayeAti = hazinehayeAti;
    }

    public String getAndukhte() {
        return andukhte;
    }

    public void setAndukhte(String andukhte) {
        this.andukhte = andukhte;
    }

    public String getSarmayeNaghsOzv() {
        return sarmayeNaghsOzv;
    }

    public void setSarmayeNaghsOzv(String sarmayeNaghsOzv) {
        this.sarmayeNaghsOzv = sarmayeNaghsOzv;
    }

    public String getSarmayeAmraz() {
        return sarmayeAmraz;
    }

    public void setSarmayeAmraz(String sarmayeAmraz) {
        this.sarmayeAmraz = sarmayeAmraz;
    }

    public String getHavaleHa()
    {
        return havaleHa;
    }

    public void setHavaleHa(String havaleHa)
    {
        this.havaleHa = havaleHa;
    }

    public String getUpdateMode()
    {
        return updateMode;
    }

    public void setUpdateMode(String updateMode)
    {
        updateMode = updateMode;
    }

    public List<User> getKarshenasKhesaratha() {
        return karshenasKhesaratha;
    }

    public void setKarshenasKhesaratha(List<User> karshenasKhesaratha) {
        this.karshenasKhesaratha = karshenasKhesaratha;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getShomareShaba() {
        return shomareShaba;
    }

    public void setShomareShaba(String shomareShaba) {
        this.shomareShaba = shomareShaba;
    }

    public String getNoePardakht() {
        return noePardakht;
    }

    public void setNoePardakht(String noePardakht) {
        this.noePardakht = noePardakht;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public ArrayList<Transition> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(ArrayList<Transition> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }
}
