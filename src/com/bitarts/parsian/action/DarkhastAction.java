package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.karmozd.KarmozdTadilat;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.EstefadeKonandeganAzSarmayeBime;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.service.karmozd.IKarmozdService;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import com.bitarts.parsian.webservice.ach.ACHPayment;
import logicaldoc.auth.AuthService_ServiceLocator;
import logicaldoc.doc.DocumentService;
import logicaldoc.doc.DocumentServiceImplServiceLocator;
import logicaldoc.doc.WsDocument;
import logicaldoc.doc.holders.WsDocumentHolder;
import logicaldoc.folder.FolderService;
import logicaldoc.folder.FolderServiceImplServiceLocator;
import logicaldoc.folder.WsFolder;
import logicaldoc.folder.holders.WsFolderHolder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringBufferInputStream;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Feb 16, 2011
 * Time: 4:25:50 PM
 */

public class DarkhastAction extends BaseAction implements ServletContextAware {

    private IShakhsService shakhsService;
    private IConstantItemsService constantItemsService;
    private String hadeAksarVam, telephonSabetBimeShodeKod, telephonSabetBimeShode;
    private ILoginService loginService;
    private ILogService logService;
    private IGharardadService gharardadService;
    private IConstantsService constantsService;
    private IAsnadeSodorService asnadeSodorService;
    private IPishnehadService pishnehadService;
    private String sodurVamSuccess;
    private IDarkhastService darkhastService;
    private IKhesaratService khesaratService;
    private ITransitionLogService transitionLogService;
    private IEstelamService estelamService;
    private ISequenceService sequenceService;
    private IClinicService clinicService;
    private Pishnehad pishnehad;
    private Estelam estelam;
    private Bimename bimename;
    private DarkhastBazkharid darkhastBazkharid;
    private boolean avalinTarikhGhestInvalid;
    private Integer khesaratCount;

    private List<TransitionLog> transitionLogs;
    private List<Bimename> bimenameha;
    private List<Pishnehad> reportResult;
    private List<Pishnehad> viewReportResult;
    private List<DarkhastBazkharid> darkhasthayeBazkharid;
    private List<User> karshenasha;
    private List<User> karshenasKhesaratha;
    private ArrayList<Transition> allowedTransitions;
    private Long karshenasId;
    private Integer transitionId;
    private String arzesheBazkharid;
    private File uploadedFile;
    private String noeFile;
    private String uploadedFileName;
    private String tozihat;
    private String logicaldocIndicator;
    private String logmessage;
    private String fileId;
    private PaginatedListImpl<Elhaghiye> elhaghiyeha;
    private boolean nosession;
    private Pishnehad newPishnehad;
    private DarkhastTaghirat darkhastTaghirat;
    private List<String> infoElhaghie=new ArrayList<String>();
    private List<DarkhastTaghirat> darkhasthayeTaghirat;
    private List<PishnehadFieldChanges> pishnehadFieldChangesList;
    private boolean timeToElamMali;
    private List<Clinic> clinics;
    private Map<String, String> errorsMap;
    private PishnehadConstants pishnehadConstants;
    private ZamaemDarkhast zamaemDarkhast;
    private String backfromupload;
    private List<Credebit> credebitListForShomareElhaghie;
    private String type;
    private List<User> bazaryabs;
    private Long resultArzesh;
    private String mablagh;
    private Map<String, String> warningsMap;
    private ArrayList<DarkhastBazkharid> allTheVaams;
    private String mablaghGhestVam;
    private String maliatBarArzeshAfzude;
    private String jamKolAghsatVam;
    private String message;
    private String bahrePardakhti, resultDarsad;
    private final int k = 15;//nerkhe bahre salane
    private String sourceFilePath, destFileDIR, realPath;
    private Long jameAghsatMoavaghBimename = 0L;
    private String mablagh2;
    private ILogGhestService logGhestService;
    private List<Gharardad> grouhha;
    private List<Tarh> tarhs;
    private Elhaghiye elhaghiye;
    private String newShakhsBimeGozarId, newShakhsBimeShodeId;
    private Integer piishnehadId;
    private boolean tozih;
    private INamayandeService namayandeService;
    private String kodeNamayandeKargozar;
    private HashMap<String, Object> validationConstants;
    private User bazaryabUser;
    private String bazarYabUserName;
    private boolean readOnlyMode = false;
    private IKarmozdService karmozdService;
    private String shomareVam;
    private String darkhastLoadMessage = "";
    private Long andukhte;
    private Integer vamId;
    private String transitionSelector;
    private boolean khesaratAction;
    private Khesarat khesarat;
    private List<ListBimenameTaghirVM> listBimenameTaghirVM;
    private Khesarat khesaratDovom;
    private StringBufferInputStream viewTrueFalse;
    private boolean haveBareMali;
    private String  tarikhAsar;
    private String nazarKarshenasKhesaratBeTafkik;
    private Namayande.NayamandeType namayandeType;
    private boolean refreshModeBardasht;
    private double andukhteDoubleBardasht;
    private Boolean generatedShomareParvande;

    public Boolean isGeneratedShomareParvande()
    {
        return generatedShomareParvande;
    }

    public void setGeneratedShomareParvande(Boolean generatedShomareParvande)
    {
        this.generatedShomareParvande = generatedShomareParvande;
    }

    public double getAndukhteDoubleBardasht()
    {
        return andukhteDoubleBardasht;
    }

    public void setAndukhteDoubleBardasht(double andukhteDoubleBardasht)
    {
        this.andukhteDoubleBardasht = andukhteDoubleBardasht;
    }

    public boolean isRefreshModeBardasht()
    {
        return refreshModeBardasht;
    }

    public void setRefreshModeBardasht(boolean refreshModeBardasht)
    {
        this.refreshModeBardasht = refreshModeBardasht;
    }

    public Namayande.NayamandeType getNamayandeType()
    {
        return namayandeType;
    }

    public void setNamayandeType(Namayande.NayamandeType namayandeType)
    {
        this.namayandeType = namayandeType;
    }

    public String getNazarKarshenasKhesaratBeTafkik()
    {
        return nazarKarshenasKhesaratBeTafkik;
    }

    public void setNazarKarshenasKhesaratBeTafkik(String nazarKarshenasKhesaratBeTafkik)
    {
        this.nazarKarshenasKhesaratBeTafkik = nazarKarshenasKhesaratBeTafkik;
    }

    public String getTarikhAsar()
    {
        return tarikhAsar;
    }

    public void setTarikhAsar(String tarikhAsar)
    {
        this.tarikhAsar = tarikhAsar;
    }

    public boolean isHaveBareMali()
    {
        return haveBareMali;
    }

    public void setHaveBareMali(boolean haveBareMali)
    {
        this.haveBareMali = haveBareMali;
    }

    public StringBufferInputStream getViewTrueFalse()
    {
        return viewTrueFalse;
    }

    public void setViewTrueFalse(StringBufferInputStream viewTrueFalse)
    {
        this.viewTrueFalse = viewTrueFalse;
    }

    public List<String> getInfoElhaghie()
    {
        return infoElhaghie;
    }

    public void setInfoElhaghie(List<String> infoElhaghie)
    {
        this.infoElhaghie = infoElhaghie;
    }

    public Integer getKhesaratCount() {
        return khesaratCount;
    }

    public void setKhesaratCount(Integer khesaratCount) {
        this.khesaratCount = khesaratCount;
    }

    public Khesarat getKhesaratDovom() {
        return khesaratDovom;
    }

    public void setKhesaratDovom(Khesarat khesaratDovom) {
        this.khesaratDovom = khesaratDovom;
    }

    public Integer getVamId() {
        return vamId;
    }

    public void setVamId(Integer vamId) {
        this.vamId = vamId;
    }

    public String getSodurVamSuccess() {
        return sodurVamSuccess;
    }

    public void setSodurVamSuccess(String sodurVamSuccess) {
        this.sodurVamSuccess = sodurVamSuccess;
    }

    public boolean getAvalinTarikhGhestInvalid() {
        return avalinTarikhGhestInvalid;
    }

    public void setAvalinTarikhGhestInvalid(boolean avalinTarikhGhestInvalid) {
        this.avalinTarikhGhestInvalid = avalinTarikhGhestInvalid;
    }

    public String getShomareVam() {
        return shomareVam;
    }

    public void setShomareVam(String shomareVam) {
        this.shomareVam = shomareVam;
    }

    public boolean isReadOnlyMode() {
        return readOnlyMode;
    }

    public void setReadOnlyMode(boolean readOnlyMode) {
        this.readOnlyMode = readOnlyMode;
    }

    public String getBazarYabUserName() {
        return bazarYabUserName;
    }

    public void setBazarYabUserName(String bazarYabUserName) {
        this.bazarYabUserName = bazarYabUserName;
    }

    public User getBazaryabUser() {
        return bazaryabUser;
    }

    public void setBazaryabUser(User bazaryabUser) {
        this.bazaryabUser = bazaryabUser;
    }

    public String getKodeNamayandeKargozar() {
        return kodeNamayandeKargozar;
    }

    public void setKodeNamayandeKargozar(String kodeNamayandeKargozar) {
        this.kodeNamayandeKargozar = kodeNamayandeKargozar;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public Integer getPiishnehadId() {
        return piishnehadId;
    }

    public void setPiishnehadId(Integer piishnehadId) {
        this.piishnehadId = piishnehadId;
    }

    public String getNewShakhsBimeShodeId() {
        return newShakhsBimeShodeId;
    }

    public void setNewShakhsBimeShodeId(String newShakhsBimeShodeId) {
        this.newShakhsBimeShodeId = newShakhsBimeShodeId;
    }

    public String getNewShakhsBimeGozarId() {
        return newShakhsBimeGozarId;
    }

    public void setNewShakhsBimeGozarId(String newShakhsBimeGozarId) {
        this.newShakhsBimeGozarId = newShakhsBimeGozarId;
    }

    public String getHadeAksarBardasht() {
        return hadeAksarBardasht;
    }

    public void setHadeAksarBardasht(String hadeAksarBardasht) {
        this.hadeAksarBardasht = hadeAksarBardasht;
    }

    private String hadeAksarBardasht;

    public void setServletContext(ServletContext servletContext) {

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        realPath = servletContext.getRealPath("/");
        this.shakhsService = (IShakhsService) wac.getBean(IShakhsService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean(IDarkhastService.SERVICE_NAME);
        this.khesaratService = (IKhesaratService) wac.getBean(IKhesaratService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
        this.logService = (ILogService) wac.getBean(ILogService.SERVICE_NAME);
        this.clinicService = (IClinicService) wac.getBean(IClinicService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.transitionLogService = (ITransitionLogService) wac.getBean(ITransitionLogService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.gharardadService = (IGharardadService) wac.getBean("gharardadService");
        logGhestService = (ILogGhestService) wac.getBean("logGhestService");
        constantsService = (IConstantsService) wac.getBean("constantsService");
        this.karmozdService = (IKarmozdService) wac.getBean("karmozdService");
    }

    public String ajaxlyCheckForValidBahremandi() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        user = loginService.findUserByUsername(username);
        User user = getUser();
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            errorsMap = new HashMap<String, String>();
            Bimename theBimename = pishnehadService.findBimenameById(bimename.getId());
            CredebitAction.mohasebeAndukhteBimename(theBimename, DateUtil.getCurrentDate(),null);
            long valueForArzeshBazkharid = Long.parseLong(arzesheBazkharid);
            if (theBimename.isThereYearTaghsitNashode() && DateUtil.isGreaterThanOrEqual(theBimename.getTarikhEngheza(),DateUtil.getCurrentDate()))
            {
                errorsMap.put("undone_taghsit", "قبل از ثبت درخواست بهره مندی از منافع، بیمه نامه را تقسیط نمایید.");
            }
            if (type.equalsIgnoreCase("VAM")) {
                if (theBimename.getState().getId().intValue() != Constant.BIMENAME_INITIAL_STATE.intValue()) {
                    errorsMap.put("elhaghiye_baaz", "بیمه نامه دارای یک الحاقیه در جریان می باشد.");
                }
                List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
                boolean hasUndoneVam = false;
                int numberOfVaams = 0;
                for (DarkhastBazkharid theDarkhast : allDarkhasts) {
                    if (theDarkhast.getState()!=null && theDarkhast.getState().getId().equals(Constant.VAM_ENSERAF)) continue;
                    if (theDarkhast.getDarkhastType()!=null && theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
                        numberOfVaams++;
//                        if(theDarkhast.getState().getId().intValue()!=Constant.VAM_FINAL_STATE.intValue()){
//                            hasUndoneVam = true;
//                        }
                        if (theDarkhast.getGhestBandi() != null)
                            for (Ghest g : theDarkhast.getGhestBandi().getGhestList()) {
                                if (!g.getCredebit().getRemainingAmount().equals("0")) {
                                    hasUndoneVam = true;
                                    break;
                                }
                            }
                    }
                }
                if (theBimename.getListDarkhastVamFinal()!=null && DateUtil.isGreaterThanOrEqual(theBimename.getListDarkhastVamFinal().get(theBimename.getListDarkhastVamFinal().size() - 1).getLastVamGhestSarresid(), DateUtil.getCurrentDate()))
                {
                    errorsMap.put("undone_vaam", "تا قبل از اتمام سررسید آخرین قسط وام قبلی، امکان ثبت درخواست وام جدید وجود ندارد.");
                }
                if (hasUndoneVam) {
                    errorsMap.put("undone_vaam", "بيمه‌نامه داراي وام تسويه نشده قبلي است. براي دريافت وام جديد، ابتدا وام قبلي را تسويه نماييد.");
                }

                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    if ((!arzesheBazkharid.equalsIgnoreCase("")) && valueForArzeshBazkharid < 0) {
                        errorsMap.put("bedehi_moavagh", "بيمه‌نامه داراي بدهي معوق است، براي دريافت وام ابتدا اقساط معوق را تسويه نماييد.");
                    }

                    if (DateUtil.isGreaterThan(DateUtil.addYear(theBimename.getTarikhShorou(), 2), DateUtil.getCurrentDate())) {
                        errorsMap.put("less_than_two_years", "برای ثبت درخواست وام باید حداقل دو سال از شروع بیمه نامه سپری شده باشد.");
                    }
                }
                if (numberOfVaams > 2) {
                    errorsMap.put("more_than_2times_vaam", "تعداد دفعات مجاز دريافت وام براي هر بيمه‌نامه حداكثر دو بار مي‌باشد.");
                }
                if (numberOfVaams > 2) {
                    errorsMap.put("more_than_2times_vaam", "تعداد دفعات مجاز دريافت وام براي هر بيمه‌نامه حداكثر دو بار مي‌باشد.");
                }


                if (errorsMap.size() == 0) {
                    resultArzesh = calcHadeAksarVam(theBimename, asnadeSodorService);
                    if (resultArzesh <= 0) {
                        errorsMap.put("arzesh_zero", "بيمه‌نامه داراي ارزش بازخريدي نمي‌باشد و وام به آن تعلق نمي‌گيرد.");
                    }
                }

            } else if (type.equalsIgnoreCase("BARDASHT_AZ_ANDOKHTE")) {

                if (theBimename.isVameTasvieNashode())
                {
                    errorsMap.put("undone_vaam", "بیمه نامه دارای وام تسویه نشده می باشد.");
                }

                if (DateUtil.isGreaterThan(DateUtil.addYear(theBimename.getTarikhShorou(), 2), DateUtil.getCurrentDate())) {
                    errorsMap.put("less_than_two_years", "براي ثبت درخواست برداشت از اندوخته بايد حداقل دو سال از شروع بيمه نامه گذشته باشد.");
                }
                List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
                List<DarkhastBazkharid> allBardashts = new ArrayList<DarkhastBazkharid>();
                int numberOfBardashts = 0;
                for (DarkhastBazkharid theDarkhast : allDarkhasts) {
                    if (theDarkhast.getState() != null && theDarkhast.getState().getId().equals(Constant.BARDASHT_ENSERAF))
                        continue;
                    if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
                        allBardashts.add(theDarkhast);
                        numberOfBardashts++;
                    }
                }
//                boolean periodIsOk = true;
//                for (DarkhastBazkharid bardasht1 : allBardashts) {
//                    for (DarkhastBazkharid bardasht2 : allBardashts) {
//                        if (bardasht1.getId() != bardasht2.getId()) {
//                            if (DateUtil.isGreaterThan(bardasht1.getTarikhDarkhast(), bardasht2.getTarikhDarkhast())) {
//                                if (DateUtil.isGreaterThan(DateUtil.addYear(bardasht2.getTarikhDarkhast(), 2), bardasht1.getTarikhDarkhast())) {
//                                    periodIsOk = false;
//                                }
//                            } else {
//                                if (DateUtil.isGreaterThan(DateUtil.addYear(bardasht1.getTarikhDarkhast(), 2), bardasht2.getTarikhDarkhast())) {
//                                    periodIsOk = false;
//                                }
//                            }
//                        }
//                    }
//                }

                if (theBimename.getArzeshBazkharidGhatie()<=0)
                {
                    errorsMap.put("saghf", "بيمه نامه داراي ارزش بازخريدي نمي باشد");
                }
                if (numberOfBardashts >= 3) {
                    errorsMap.put("more_than_3times_bardasht", "حداكثر تعداد دفعات مجاز براي برداشت از اندوخته شما به پايان رسيده است.");
                }
                if (!theBimename.isPeriodBardashtAzAndukhteOk()) {
                    errorsMap.put("period_not_okay", "براي ثبت درخواست برداشت از اندوخته جديد بايد حداقل سه سال از برداشت اندوخته قبلي گذشته باشد.");
                }

                if (errorsMap.size() == 0) {
                    resultArzesh = DarkhastAction.calcHadeAksarBardasht(theBimename, valueForArzeshBazkharid, asnadeSodorService);
                    resultDarsad = DarkhastAction.calcDarsadBaghimandeBardashtString(theBimename);
                    if (resultDarsad.equals("out of max andukhte"))
                    {
                        errorsMap.put("saghf", "حداكثر سقف مجازبراي برداشت از اندوخته اين بيمه نامه به پايان رسيده است");
                    }
                    else if (resultArzesh <= 0) {
                        errorsMap.put("saghf", "سقف برداشت از اندوخته معتبر نمي باشد");
                    }
                }

            } else if (type.equalsIgnoreCase("EBTAL")) {
                String tarikhShorou = theBimename.getTarikhShorou();
                String after60 = DateUtil.addMonth(tarikhShorou, 2);
                String after30 = DateUtil.addMonth(tarikhShorou, 1);
                warningsMap = new HashMap<String, String>();
                warningsMap.put("warning_azmayesh", "در صورت انجام آزمايش پزشكي توسط بيمه شده، هزينه آزمايشات از مبلغ پرداختي به بيمه گذار كسر خواهد شد.");
                warningsMap.put("warning_20days", "در صورتي كه مدارك شما ظرف 20 روز آينده به دست اداره خدمات پس از صدور نرسد، درخواست شما منقضي شده و باطل مي شود.");
                if (!DateUtil.isGreaterThan(after30, DateUtil.getCurrentDate()) && DateUtil.isGreaterThan(after60, DateUtil.getCurrentDate())) {

                    warningsMap.put("taeed_khadamat_required", "ابطال بيمه نامه بعد از گذشت 30 روز از تاريخ شروع بيمه نامه مشروط به تاييد اداره خدمات پس از صدور مي باشد.");
//                    List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
//                    boolean hasUndoneVam = false;
//                    int numberOfVaams = 0;
//                    for (DarkhastBazkharid theDarkhast : allDarkhasts) {
//                        if (theDarkhast.getState().getId().equals(Constant.VAM_ENSERAF)) continue;
//                        if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
//                            numberOfVaams++;
////                            if(theDarkhast.getState().getId().intValue()!=Constant.VAM_FINAL_STATE.intValue()){
////                                hasUndoneVam = true;
////                            }
//                            for (Ghest g : theDarkhast.getGhestBandi().getGhestList()) {
//                                if (!g.getCredebit().getRemainingAmount().equals("0")) {
//                                    hasUndoneVam = true;
//                                    break;
//                                }
//                            }
//                        }
//                    }

                }
                if (theBimename.isVameTasvieNashode()) {
                    errorsMap.put("undone_vaam", "بيمه‌نامه داراي وام تسويه نشده قبلي است. براي دريافت وام جديد، ابتدا وام قبلي را تسويه نماييد.");
                }

            } else if (type.equalsIgnoreCase("BAZKHARID")) {
                warningsMap = new HashMap<String, String>();
                List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
                List<DarkhastBazkharid> allTheVaams = new ArrayList<DarkhastBazkharid>();
//                for (DarkhastBazkharid darkhast : allDarkhasts) {
//                    if (darkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.TAGHIRKOD) || darkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.TOZIH))
//                        continue;
//                    if (darkhast.getState().getId().equals(Constant.VAM_ENSERAF)) continue;
//                    if (darkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
//                        allTheVaams.add(darkhast);
//                    }
//                }
                double majmueTasviyeNashodeVam = 1;
//                for (DarkhastBazkharid theVam : allTheVaams) {
//                    if (theVam.getGhestBandi() != null) {
//                        List<Ghest> allTheGhestsForThisVam = theVam.getGhestBandi().getGhestList();
//                        for (Ghest ghest : allTheGhestsForThisVam) {
//                            if (ghest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
//                                majmueTasviyeNashodeVam += Double.parseDouble(ghest.getCredebit().getAmount());
//                            }
//                        }
//                    }
//                }
//                if (majmueTasviyeNashodeVam > 0) {
//                    warningsMap.put("vam_tasviye_nashode_for_bazkharid", "اين بيمه نامه داراي مبلغ " + Math.round(majmueTasviyeNashodeVam) + " ريال مانده وام مي باشد.");
//                }
                if ((theBimename.getNotCheckVams()==null || !theBimename.getNotCheckVams()) && theBimename.isVameTasvieNashode()) {
                    errorsMap.put("undone_vaam", "بیمه نامه دارای وام تسویه نشده قبلی است.");
                }
//                resultArzesh = Math.round(valueForArzeshBazkharid);
                CredebitAction.mohasebeAndukhteBimename(theBimename, DateUtil.getCurrentDate(),null);
                resultArzesh = theBimename.getArzeshBazkharidGhatie();
                if (resultArzesh < 0) resultArzesh = 0L;

            } else if (type.equalsIgnoreCase("TASVIE_PISH_AZ_MOEDE_VAM")) {
                warningsMap = new HashMap<String, String>();
                warningsMap.put("mohlate_tasviye_vam", "مهلت مقرر جهت تسويه پيش از موعد بدهي وام، حداكثر 30 روز از تاريخ اعلام مانده بدهي وام توسط اداره خدمات پس از صدور مي‌باشد.");
                List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
                List<DarkhastBazkharid> allTheVaamsLocal = new ArrayList<DarkhastBazkharid>();
                allTheVaams = new ArrayList<DarkhastBazkharid>();

                for (DarkhastBazkharid darkhast : allDarkhasts) {
                    if (darkhast.getState().getId().equals(Constant.VAM_ENSERAF)) continue;
                    if (darkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
                        allTheVaamsLocal.add(darkhast);
                    }
                }
                for (DarkhastBazkharid localVam : allTheVaamsLocal) {
                    boolean result = checkIfVaamIsPayedThoroughly(localVam);
                    if (!result) {
                        allTheVaams.add(localVam);
                    }
                }
                resultArzesh = 0L;
            }

            bimename = theBimename;
            return SUCCESS;
        }
    }

    public static String calcDarsadBaghimandeBardashtString(Bimename theBimename)
    {
        double percent = calcDarsadBaghimandeBardasht(theBimename);
        if(percent!=-1)
            return String.valueOf(Math.round(percent * 100));
        else
            return "out of max andukhte";
    }
    public static double calcDarsadBaghimandeBardasht(Bimename theBimename)
    {
        double darsad = 0;
        double darsadLastBardasht = 0;
        DarkhastBazkharid lastBardasht = theBimename.getLastBardasht();
        if (lastBardasht!=null)
            darsadLastBardasht=Double.parseDouble(lastBardasht.getDarsadBardasht().trim().replaceAll(",", ""));
        if
        (
            (DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(), DateUtil.addYear(theBimename.getTarikhShorou(), 2))) &&
            (DateUtil.isGreaterThanOrEqual(DateUtil.addYear(theBimename.getTarikhShorou(), 6), DateUtil.getCurrentDate()))
        )
        {
            if (lastBardasht != null)
            {
                if (darsadLastBardasht < 0.5)
                    darsad = 0.5 - darsadLastBardasht;
                else
                    darsad = -1;
            }
            else
                darsad = 0.5;
        }
        else if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), DateUtil.addYear(theBimename.getTarikhShorou(), 6)))
        {
            if (lastBardasht != null && DateUtil.isGreaterThan(lastBardasht.getTarikhDarkhast(), DateUtil.addYear(theBimename.getTarikhShorou(), 6)))
            {
                for(DarkhastBazkharid b:theBimename.getDarkhastBardashtAzAndukhteFinal())
                {
                    if(b.getId()!=lastBardasht.getId())
                    {
                        if(DateUtil.isGreaterThan(b.getTarikhDarkhast(), DateUtil.addYear(theBimename.getTarikhShorou(), 6)))
                            darsadLastBardasht+= Double.parseDouble(b.getDarsadBardasht().trim().replaceAll(",", ""));
                    }
                }
                if (darsadLastBardasht < 0.9)
                    darsad = 0.9 - darsadLastBardasht;
                else
                    darsad = -1;
            }
            else
            {
                darsad = 0.9;
            }

        }
        return darsad;
    }

    public static long calcHadeAksarBardasht(Bimename theBimename, long valueForArzeshBazkharid, IAsnadeSodorService asnadeSodorService)
    {
        long hazineBimegari = CredebitAction.mohasebeHazineBimegari(theBimename, DateUtil.getCurrentDate(), null);
        long resultArzesh = 0l;
        if(theBimename.isPeriodBardashtAzAndukhteOk())
        {
            long tmp = 0;
            tmp = calcMaxForBardasht(theBimename,valueForArzeshBazkharid,asnadeSodorService);
            double percent=calcDarsadBaghimandeBardasht(theBimename);
            resultArzesh = Math.round(tmp * percent);
            if(valueForArzeshBazkharid - hazineBimegari <= resultArzesh)
            {
                resultArzesh = valueForArzeshBazkharid - hazineBimegari - 1l;
            }
        }
        return resultArzesh<0 ? 0 : resultArzesh;
    }

    public static long calcJaraemeVams(Bimename theBimename,IAsnadeSodorService asnadeSodorService)
    {
        long tmp=0;
        for (DarkhastBazkharid darkhastBazkharid : theBimename.getAllDarkhasts())
        {
            try
            {
                if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && darkhastBazkharid.getFinished() && darkhastBazkharid.getState().getId().equals(Constant.VAM_FINAL_STATE))
                {
                    final Long jameJaraemDirkard = Math.round(MohasebateFaniVam.calcJaraemeDirkard(darkhastBazkharid, asnadeSodorService) + MohasebateFaniVam.calcJaraemeTavigh(darkhastBazkharid));
                    final Long jameAslAghsatAti = Math.round(MohasebateFaniVam.calcJameAslAghsatAti(darkhastBazkharid));
                    final Long jameKolAghsatMoavaq = Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(darkhastBazkharid));
                    final Long jameKolAghsatMoavaqJarime = jameKolAghsatMoavaq + jameJaraemDirkard;
                    final Long jameKolBedehi = jameKolAghsatMoavaqJarime + jameAslAghsatAti;
                    tmp += jameKolBedehi;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return 0;
            }
        }
        return tmp;
    }

    public static long calcMaxForBardasht(Bimename theBimename, long valueForArzeshBazkharid, IAsnadeSodorService asnadeSodorService)
    {
        long tmp = calcJaraemeVams(theBimename,asnadeSodorService);
        try {

            tmp = valueForArzeshBazkharid - Long.parseLong(theBimename.getAmountMoavagh().replaceAll(",","").trim()) - tmp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
        return tmp;
    }

    private boolean checkIfVaamIsPayedThoroughly(DarkhastBazkharid localVam) {
        boolean result = true;
        DarkhastBazkharid theLocalVam = darkhastService.findDarkhastBazkharidById(localVam.getId());
        String nahve = theLocalVam.getNahveyePardakhteAghsat();
        List<Ghest> theGhests = theLocalVam.getGhestBandi().getGhestList();
        for (Ghest theGhest : theGhests) {
            if (theGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public String prepareToMakeRequest() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        user = loginService.findUserByUsername(username);
        User user = getUser();
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            bimename = pishnehadService.findBimenameById(bimename.getId());
            asnadeSodorService.setAndukhteAndArzeshBazkharid(bimename, DateUtil.getCurrentDate());
            double arzesheBazkharidDouble = bimename.getArzeshBazkharidGhatie();
            double andukhteDouble = bimename.getAndukhteyeGhatie();
            arzesheBazkharid = String.valueOf(Math.round(arzesheBazkharidDouble));
            andukhte = Math.round(andukhteDouble);
            return SUCCESS;
        }
    }

    public String sabteBardashtAzAndukhte() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        user = loginService.findUserByUsername(username);
        User user = getUser();
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            theDarkhast.setEmzaKonandeAval(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeAval().getId()));
            theDarkhast.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeDovom().getId()));
            theDarkhast.setMablaghDarkhastiBardasht(darkhastBazkharid.getMablaghDarkhastiBardasht());
            theDarkhast.setAndukhteGhatie(darkhastBazkharid.getAndukhteGhatie());
            double darsad = ((Double.parseDouble(theDarkhast.getMablaghDarkhastiBardasht().trim().replaceAll(",", "")) * 100.0) / Double.parseDouble(darkhastBazkharid.getAndukhteGhatie().trim().replaceAll(",", ""))) / 100.0;      //100.0ha lazem nist
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            twoDForm.setRoundingMode(RoundingMode.HALF_UP);
            theDarkhast.setDarsadBardasht(String.valueOf(Double.valueOf(twoDForm.format(darsad))));
            theDarkhast.setMaxAmountBardasht(darkhastBazkharid.getMaxAmountBardasht());
            if (!refreshModeBardasht)
                theDarkhast.setShomareBardashtAzAndukhte(sequenceService.nextShomareBahremandiAzManafe(theDarkhast.getBimename()));
            darkhastService.updateDarkhastBazkharid(theDarkhast);
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(theDarkhast.getId());
            bimename = pishnehadService.findBimenameById(darkhastBazkharid.getBimename().getId());
            karshenasha = darkhastService.findAllKarshenasForDarkhasts();
            timeToElamMali = false;
            if(!refreshModeBardasht)
            {
                retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), user.getId());
                timeToElamMali = true;
                try {
                    telephonSabetBimeShodeKod = darkhastBazkharid.getTelephonSabetBimeShode().substring(0, 3);
                    if (!telephonSabetBimeShodeKod.equals("021")) {
                        telephonSabetBimeShodeKod = darkhastBazkharid.getTelephonSabetBimeShode().substring(0, 4);
                        telephonSabetBimeShode = darkhastBazkharid.getTelephonSabetBimeShode().substring(4, 11);
                    } else {
                        telephonSabetBimeShode = darkhastBazkharid.getTelephonSabetBimeShode().substring(3, 11);
                    }
                } catch (Exception ex) {
                }
            }
            return SUCCESS;
        }
    }

    private Darkhast darkhast;
    private String bimenameIsMafqud;

    public String prepareToMakeRequest2() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        user = loginService.findUserByUsername(username);
        User user = getUser();
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {

            bimename = pishnehadService.findBimenameById(bimename.getId());
            pishnehad = bimename.getPishnehad();
            asnadeSodorService.setAndukhteAndArzeshBazkharid(bimename, DateUtil.getCurrentDate());
            arzesheBazkharid = NumberFormat.getNumberInstance().format(bimename.getArzeshBazkharidGhatie());
//            hadeAksarVam = getHadeAksarVam()!=null?getHadeAksarVam():NumberFormat.getInstance().format(calcHadeAksarVam(bimename, asnadeSodorService));
            if (mablagh2 != null) {
                mablagh = NumberFormat.getNumberInstance().format(Long.parseLong(mablagh2.replace(",", "").trim()));
            }
            telephonSabetBimeShode = bimename.getPishnehad().getAddressBimeGozar().getTelephoneManzel();
            telephonSabetBimeShodeKod = bimename.getPishnehad().getAddressBimeGozar().getCodeTelephoneManzel();
//            darkhastBazkharid = new DarkhastBazkharid();
//            darkhastBazkharid.setTelephonHamrahBimeShode(bimename.getPishnehad().getAddressBimeGozar().getTelephoneHamrah());
            karshenasha = darkhastService.findAllKarshenasForDarkhasts();
            setTarhs(constantsService.listAllTarhs());
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
            //----- initialize Validation Constants ------------------
            String date = DateUtil.getCurrentDate();
            if (pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            }
            validationConstants = AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date);
            //--------------------------------------------------------
            bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());
            if (darkhast.getDarkhastType() != null && darkhast.getDarkhastType().equals(Darkhast.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM))
                vamId = vamId;
            return SUCCESS;
        }
    }

    //    private double calculateArzesheBazkharid(Bimename bimename) {
//        List<Sanad> sanads = asnadeSodorService.findAllSanads();
//        List<Ghest> ghestList = asnadeSodorService.findAllBedehisLessThanSarresidDate(DateUtil.getCurrentDate());
//        double result = 0;
//        double finalAmount = 0;
//        for (Ghest ghest : ghestList) {
//            int difffDate = DateUtil.subDates(DateUtil.getCurrentDate(), ghest.getSarresidDate());
//            double amounttVal = Double.parseDouble(ghest.getCredebit().getAmount());
//            amounttVal = amounttVal * -1;
//            double difffperyear = difffDate/365.0;
//            double difffperyearenchanted = Math.pow(1.15,difffperyear);
//            double amounttEnchanted = amounttVal*difffperyearenchanted;
//            finalAmount += amounttEnchanted;
//        }
//        List<Credebit> pardakhts = new ArrayList<Credebit>();
//        for (Sanad sanad : sanads) {
//            Set<KhateSanad> khateSanadSet = sanad.getKhateSanadSet();
//            for (KhateSanad khateSanad : khateSanadSet) {
//                for (Ghest ghest : ghestList) {
//                    if (khateSanad.getBedehiCredebit().getId().equals(ghest.getId())){
//                        Set<KhateSanad> sanadPardakhts = sanad.getKhateSanadSet();
//                        for (KhateSanad sanadPardakht : sanadPardakhts) {
//                            pardakhts.add(sanadPardakht.getEtebarCredebit());
//                        }
//                    }
//                }
//            }
//        }
//        double finalAmountPardakht = 0;
//        for (Credebit pardakht : pardakhts) {
//            int diffDate = DateUtil.subDates(DateUtil.getCurrentDate(), pardakht.getCreatedDate());
//            int amountVal = Integer.parseInt(pardakht.getAmount());
//            double diffperyear = diffDate/365.0;
//            double diffperyearenchanted = Math.pow(1.15,diffperyear);
//            double amountEnchanted = amountVal*diffperyearenchanted;
//            finalAmountPardakht += amountEnchanted;
//        }
//        result = finalAmountPardakht + finalAmount;
//        return result;
//    }
    private Integer whenApply = 0;

    @Transactional
    public String saveElhaghiyeTaghirat() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            darkhastTaghirat = new DarkhastTaghirat();
            darkhastTaghirat.setOldPishnehad(pishnehadService.findById(pishnehad.getId()));
            if (!darkhastTaghirat.getOldPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_INITIAL_STATE)) {
                return "error";
            }
            if(haveBareMali)
            {   tarikhAsar=null;
                darkhastTaghirat.setHaveBareMali(true);
                if (whenApply == null) whenApply = 0;
                // validate whenApply
                if (!darkhastTaghirat.getOldPishnehad().getBimename().getTarikhAsarListForElhaghie().containsKey(whenApply.toString())) {
                    return "error";
                }
            }

            ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
            zamaemDarkhast.setFileEhrazDescription("1");
            darkhastService.saveZamaemDarkhast(zamaemDarkhast);
            ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
            darkhastTaghirat.setZamaemDarkhast(theZamime);
            darkhastTaghirat.setArchive("no");
            darkhastTaghirat.setNewPishnehad(pishnehadService.findById(newPishnehad.getId()));
            Role namayandeRole = new Role();
            namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
            if (result.getRoles().contains(namayandeRole)) {
                darkhastTaghirat.setNamayande(result.getNamayandegi());
            } else {
                darkhastTaghirat.setNamayande(darkhastTaghirat.getOldPishnehad().getNamayande());
            }

            darkhastTaghirat.setCreator(result);
            darkhastTaghirat.setWhenApply(whenApply);
            int saveResult = darkhastService.saveDarkhastTaghirat(darkhastTaghirat, result,tarikhAsar);
            if(saveResult == -1) {
                return "error";
            } else {
                return SUCCESS;
            }
        }
    }

    public String validateSabteDarkhasteBazkharid() {
        boolean valid = true;
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if(bimename!=null && bimename.getId()!=null)
        {
            Bimename b = pishnehadService.findBimenameById(bimename.getId());
            if(!b.getState().getId().equals(Constant.BIMENAME_INITIAL_STATE))
            {
                addActionError("بیمه نامه دارای درخواست در جریان می باشد.");
                valid=false;
            }
        }
        if (darkhastBazkharid.getDarkhastType() != null && darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT)) {

        } else {
            try {
                String tel = telephonSabetBimeShodeKod + telephonSabetBimeShode;
                tel = tel.replaceAll("-", "").replaceAll(" ", "");
                NumberFormat.getNumberInstance().parse(tel);
                if (telephonSabetBimeShode.isEmpty() || telephonSabetBimeShodeKod.isEmpty() || tel.length() != 11) {
                    throw new NumberFormatException();
                }
                darkhastBazkharid.setTelephonSabetBimeShode(tel);

            } catch (Exception ex) {
                addActionError("شماره تلفن ثابت بیمه شده به درستی وارد نشده است");
                valid = false;
            }
            try {
                if (darkhastBazkharid.getTelephonHamrahBimeShode().isEmpty() || darkhastBazkharid.getTelephonHamrahBimeShode().replaceAll("-", "").replaceAll(" ", "").length() != 11) {
                    throw new NumberFormatException();
                }
                NumberFormat.getNumberInstance().parse(darkhastBazkharid.getTelephonHamrahBimeShode().replaceAll("-", "").replaceAll(" ", ""));

            } catch (Exception ex) {
                addActionError("شماره تلفن همراه به درستی وارد نشده است");
                valid = false;
            }
            try {
                if (darkhastBazkharid.getTelephoneNamayandegi().isEmpty()) throw new NumberFormatException();
                NumberFormat.getNumberInstance().parse(darkhastBazkharid.getTelephoneNamayandegi().replaceAll("-", "").replaceAll(" ", ""));
            } catch (Exception ex) {
                addActionError("شماره تلفن نمایندگی به درستی وارد نشده است");
                valid = false;
            }
            if (!darkhast.getDarkhastType().equals(Darkhast.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM)) {
                if (darkhastBazkharid.getShomareHesab().isEmpty() || (darkhastBazkharid.getShomareShaba().isEmpty() && !darkhastBazkharid.getBankName().equals("پارسیان")) || darkhastBazkharid.getBankName().isEmpty() || darkhastBazkharid.getShobeName().isEmpty() || darkhastBazkharid.getKodeShobe().isEmpty() || darkhastBazkharid.getSahebHesab().isEmpty()) {
                    addActionError("اطلاعات بانکی به درستی وارد نشده است");
                    valid = false;
                }
                // validate shaba
                //        if(!(OmrUtil.userHasRole(result, Constant.ROLE_KARSHENAS_SODOUR) || OmrUtil.userHasRole(result, Constant.ROLE_PAS_KARSHENAS))) {
                if (darkhastBazkharid.getShomareShaba().length() != 24) {
                    addActionError("شماره شبا اشتباه است.");
                    valid = false;
                } else {
                    BigInteger toCheck = new BigInteger(darkhastBazkharid.getShomareShaba().substring(2) + "1827" + darkhastBazkharid.getShomareShaba().substring(0, 2));
                    if (toCheck.mod(new BigInteger("97")).intValue() != 1) {
                        addActionError("شماره شبا اشتباه است.");
                        valid = false;
                    }
                }
            }
            //    }
            if (!prepareToMakeRequest2().equals(SUCCESS)) {
                addActionError("خطا نامشخص. لطفا دوباره وارد سیستم شوید.");
                valid = false;
            }
            if (!valid) {
                if (!darkhast.getDarkhastType().equals(Darkhast.DarkhastType.VAM) && !darkhast.getDarkhastType().equals(Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
                    mablagh = darkhastBazkharid.getArzeshBazkharid();
                }
                if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
                    mablagh = mablagh;
                    mablagh2 = mablagh2;
                }

                darkhastBazkharid.setArzeshBazkharid(null);
                darkhastBazkharid = null;
                karshenasha = darkhastService.findAllKarshenasForDarkhasts();
                setTarhs(constantsService.listAllTarhs());
                pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
                //----- initialize Validation Constants ------------------
                String date = DateUtil.getCurrentDate();
                if (darkhastBazkharid != null) {
                    if (darkhastBazkharid.getBimename() != null) {
                        date = darkhastBazkharid.getBimename().getTarikhSodour();
                    }
                    setValidationConstants(AsnadeSodorService.getValidationConstantsSet(darkhastBazkharid.getBimename().getPishnehad().getTarh(), date));
                }
                //--------------------------------------------------------
                bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());
            }
        }
        return valid ? SUCCESS : INPUT;
    }

    public String sabteDarkhasteTaghirCode() {
        darkhastBazkharid = new DarkhastBazkharid();
        darkhast = new Darkhast();
        darkhast.setDarkhastType(Darkhast.DarkhastType.TAGHIRKOD);
        return sabteDarkhasteBazkharid();
    }

    public String loadDeafultBazaryab() {
        Namayande namayande = namayandeService.getNamayandeByKodeKargozar(kodeNamayandeKargozar);
        if (namayande == null)
        {
            bazaryabUser = null;
            kodeNamayandeKargozar = null;
            nameNamayande = null;
            namayandeType=null;
            return SUCCESS;
        }
        bazaryabUser = namayande.getNamayandegiUser();
        kodeNamayandeKargozar = namayande.getKodeNamayandeKargozar();
        nameNamayande = namayande.getName();
        namayandeType=namayande.getNamayandeType();
        return SUCCESS;
    }



    public String checkAccidentDateAjaxly()
    {
        String valid = "true";
        //validation Khesarat. . .
        if (darkhastBazkharid.getDarkhastType() != null && darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT))
        {
            DarkhastBazkharid db = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            if (darkhastBazkharid.getKhesaratI().getAccidentDate() != null && !darkhastBazkharid.getKhesaratI().getAccidentDate().isEmpty())
            {
                String accidentDate = DateUtil.getCurrentDate();
                if (DateUtil.isGreaterThan(db.getBimename().getTarikhShorou(), darkhastBazkharid.getKhesaratI().getAccidentDate()))
                {

                    addActionError("تاریخ وقوع حادثه نباید قبل از تاریخ شروع بیمه نامه (" + db.getBimename().getTarikhShorou() + " )باشد. ");
                    valid = "less";
                }

                if (DateUtil.isGreaterThan(darkhastBazkharid.getKhesaratI().getAccidentDate(), accidentDate))
                {
                    addActionError("تاریخ وقوع حادثه نباید بعد از تاریخ ثبت درخواست خسارت (" + accidentDate + " )باشد. ");
                    valid = "great";
                }
                bimename = db.getBimename();
                pishnehad = bimename.getPishnehad();
            }
            else
            {
                valid = "null";
                addActionError("تاریخ وقوع خسارت را وارد کنید.");
            }
            if (khesaratCount == 2)
            {
                if (darkhastBazkharid.getKhesaratII().getAccidentDate() != null && !darkhastBazkharid.getKhesaratII().getAccidentDate().isEmpty())
                {
                    String accidentDate = DateUtil.getCurrentDate();
                    if (DateUtil.isGreaterThan(db.getBimename().getTarikhShorou(), darkhastBazkharid.getKhesaratII().getAccidentDate()))
                    {
                        addActionError("تاریخ وقوع حادثه نباید قبل از تاریخ شروع بیمه نامه (" + db.getBimename().getTarikhShorou() + " )باشد. ");
                        valid = "less";
                    }

                    if (DateUtil.isGreaterThan(darkhastBazkharid.getKhesaratII().getAccidentDate(), accidentDate))
                    {
                        addActionError("تاریخ وقوع حادثه نباید بعد از تاریخ ثبت درخواست خسارت (" + accidentDate + " )باشد. ");
                        valid = "great";
                    }
                }
                else
                {
                    valid = "null";
                    addActionError("تاریخ وقوع خسارت را وارد کنید.");
                }
            }

        }
        viewTrueFalse = new StringBufferInputStream(valid);
        return SUCCESS;
    }

    public String sabteDarkhasteBazkharid() {
        User user = getUser();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (telephonSabetBimeShodeKod != null || telephonSabetBimeShode != null) {
                darkhastBazkharid.setTelephonSabetBimeShode(telephonSabetBimeShodeKod + telephonSabetBimeShode);
            }

            darkhastBazkharid.setCreator(result);
//            darkhastBazkharid.setKarshenas(result);
            if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.BAZKHARID)) {
                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);
                bimename = pishnehadService.findBimenameById(bimename.getId());
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.BAZKHARID);
                CredebitAction.mohasebeAndukhteBimename(bimename, DateUtil.getCurrentDate(),null);
                darkhastBazkharid.setAndukhteGhatie(NumberFormat.getNumberInstance().format(bimename.getAndukhteyeGhatie()));
                darkhastBazkharid.setArzeshBazkharid(NumberFormat.getNumberInstance().format(bimename.getArzeshBazkharidGhatie()));
                darkhastBazkharid.setNameRooznameh(darkhastBazkharid.getNameRooznameh());
                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    darkhastBazkharid.setNamayande(user.getNamayandegi());
                } else {
                    darkhastBazkharid.setNamayande(bimename.getPishnehad().getNamayande());
                }
                int bazkharidId = darkhastService.saveDarkhastBazkharid(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
                pishnehadService.updateBimename(bimename);
            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.VAM)) {
//todo should be reviewed
                Long userId = result.getId();
                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);
                bimename = pishnehadService.findBimenameById(bimename.getId());
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.VAM);
                darkhastBazkharid.setMablagheVamedarkhasti(mablagh);
                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    darkhastBazkharid.setNamayande(user.getNamayandegi());
                } else {
                    darkhastBazkharid.setNamayande(bimename.getPishnehad().getNamayande());
                }
                int bazkharidId = darkhastService.saveDarkhastVam(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
//                bimename.getDarkhastVamList().add()
                bimename.getAllDarkhasts().add(theBazkharid);
                pishnehadService.updateBimename(bimename);
            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
//todo should be reviewed
                Long userId = result.getId();

                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);

                darkhastBazkharid.setMablaghDarkhastiBardasht(mablagh);
                bimename = pishnehadService.findBimenameById(bimename.getId());
                asnadeSodorService.setAndukhteAndArzeshBazkharid(bimename, DateUtil.getCurrentDate());
                darkhastBazkharid.setAndukhteGhatie(NumberFormat.getNumberInstance().format(bimename.getAndukhteyeGhatie()));
                darkhastBazkharid.setArzeshBazkharid(NumberFormat.getNumberInstance().format(bimename.getArzeshBazkharidGhatie()));
//                double arzesheBazkharidDouble = bimename.getArzeshBazkharidGhatie();
                double andukhteDouble = bimename.getAndukhteyeGhatie();
                resultArzesh = calcHadeAksarBardasht(bimename, Math.round(andukhteDouble), asnadeSodorService);
                double darsad=((Double.parseDouble(darkhastBazkharid.getMablaghDarkhastiBardasht().trim().replaceAll(",", ""))*100.0)/ Double.parseDouble(darkhastBazkharid.getAndukhteGhatie().trim().replaceAll(",", "")))/100.0;
                DecimalFormat twoDForm = new DecimalFormat("#.##");
                twoDForm.setRoundingMode(RoundingMode.HALF_UP);
                darkhastBazkharid.setDarsadBardasht(String.valueOf(Double.valueOf(twoDForm.format(darsad))));
                darkhastBazkharid.setMaxAmountBardasht(resultArzesh);
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE);
                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    darkhastBazkharid.setNamayande(user.getNamayandegi());
                } else {
                    darkhastBazkharid.setNamayande(bimename.getPishnehad().getNamayande());
                }
                int bazkharidId = darkhastService.saveDarkhastBardashtAzAndokhte(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
                bimename.getAllDarkhasts().add(theBazkharid);
                pishnehadService.updateBimename(bimename);
            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM)) {
//todo should be reviewed
                Long userId = result.getId();
                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);
                bimename = pishnehadService.findBimenameById(bimename.getId());
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.TASVIE_PISH_AZ_MOEDE_VAM);
                darkhastBazkharid.setTasvieVamPishAzMoedId(vamId);
                darkhastBazkharid.setShomareVam(darkhastService.findDarkhastBazkharidById(vamId).getShomareVam());
                int bazkharidId = darkhastService.saveDarkhasteTasvieyePishAzMoedeVam(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
                pishnehadService.updateBimename(bimename);
            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.EBTAL)) {
                Long userId = result.getId();
                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);
                bimename = pishnehadService.findBimenameById(bimename.getId());
                pishnehad = bimename.getPishnehad();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.EBTAL);
                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    darkhastBazkharid.setNamayande(user.getNamayandegi());
                } else {
                    darkhastBazkharid.setNamayande(bimename.getPishnehad().getNamayande());
                }
                darkhastBazkharid.setTelephoneNamayandegi(darkhastBazkharid.getTelephoneNamayandegi());
                int bazkharidId = darkhastService.saveDarkhastBazkharid(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
                pishnehadService.updateBimename(bimename);
            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.KHESARAT)) {
                Long userId = result.getId();
                user = result;
                DarkhastBazkharid theKhesaratDB = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                bimename = pishnehadService.findBimenameById(theKhesaratDB.getBimename().getId());
                //set khesarat aval
                City shahr = constantItemsService.findCityById(darkhastBazkharid.getKhesaratI().getShahrMahalleHadese().getCityId());
                City ostan = constantItemsService.findCityById(darkhastBazkharid.getKhesaratI().getOstanMahalleHadese().getCityId());
                Namayande pronouncerOrg = namayandeService.getNamayandeById(darkhastBazkharid.getKhesaratI().getPronouncerOrg().getId());
                Khesarat theKhesarat1 = new Khesarat(shahr, ostan, darkhastBazkharid.getKhesaratI().getNahveElam(), darkhastBazkharid.getKhesaratI().getEllat(), darkhastBazkharid.getKhesaratI().getSharhKhesarat(), user, darkhastBazkharid.getKhesaratI().getAccidentDate(), darkhastBazkharid.getKhesaratI().getKhesaratType(), darkhastBazkharid.getKhesaratI().getDarsadNaghsOzv(), pronouncerOrg);
                theKhesarat1.setAmountElamShode(darkhastBazkharid.getKhesaratI().getAmountElamShode());
                theKhesarat1.setAndukhte(darkhastBazkharid.getKhesaratI().getAndukhte());
                theKhesarat1.setAmountMazad(darkhastBazkharid.getKhesaratI().getAmountMazad());
                theKhesarat1.setKhesaratType(darkhastBazkharid.getKhesaratI().getKhesaratType());
                theKhesarat1.setAmountErfagh(darkhastBazkharid.getKhesaratI().getAmountErfagh());
                theKhesarat1.setNazarKarshenas(darkhastBazkharid.getKhesaratI().getNazarKarshenas());

                if (darkhastBazkharid.getKhesaratI().getAmountAti() != null)
                    theKhesarat1.setAmountAti(darkhastBazkharid.getKhesaratI().getAmountAti());
                khesaratService.saveKhesarat(theKhesarat1);
                Khesarat theKhesarat2 = null;
                if (khesaratCount == 2) {
                    //set khesarat dovom
                    pronouncerOrg = namayandeService.getNamayandeById(darkhastBazkharid.getKhesaratII().getPronouncerOrg().getId());
                    shahr = constantItemsService.findCityById(darkhastBazkharid.getKhesaratII().getShahrMahalleHadese().getCityId());
                    ostan = constantItemsService.findCityById(darkhastBazkharid.getKhesaratII().getOstanMahalleHadese().getCityId());
                    theKhesarat2 = new Khesarat(shahr, ostan, darkhastBazkharid.getKhesaratII().getNahveElam(), darkhastBazkharid.getKhesaratII().getEllat(), darkhastBazkharid.getKhesaratII().getSharhKhesarat(), user, darkhastBazkharid.getKhesaratII().getAccidentDate(), darkhastBazkharid.getKhesaratII().getKhesaratType(), darkhastBazkharid.getKhesaratII().getDarsadNaghsOzv(), pronouncerOrg);
                    theKhesarat2.setAmountElamShode(darkhastBazkharid.getKhesaratII().getAmountElamShode());
                    theKhesarat2.setAndukhte(darkhastBazkharid.getKhesaratII().getAndukhte());
                    theKhesarat2.setAmountMazad(darkhastBazkharid.getKhesaratII().getAmountMazad());
                    theKhesarat2.setKhesaratType(darkhastBazkharid.getKhesaratII().getKhesaratType());
                    theKhesarat2.setNazarKarshenas(darkhastBazkharid.getKhesaratII().getNazarKarshenas());
                    theKhesarat2.setAmountErfagh(darkhastBazkharid.getKhesaratII().getAmountErfagh());
                    if (darkhastBazkharid.getKhesaratII().getAmountAti() != null)
                        theKhesarat2.setAmountAti(darkhastBazkharid.getKhesaratII().getAmountAti());
                    khesaratService.saveKhesarat(theKhesarat2);
                }

                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                theKhesaratDB.setZamaemDarkhast(zamaemDarkhast);


                pishnehad = bimename.getPishnehad();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                theKhesaratDB.setShomareDarkhast(finalDarkhastShomare);
                Role namayandeRole = new Role();
                namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
                if (user.getRoles().contains(namayandeRole)) {
                    theKhesaratDB.setNamayande(user.getNamayandegi());
                    theKhesaratDB.setPronouncerOrg(user.getNamayandegi());
                } else {
                    theKhesaratDB.setNamayande(bimename.getPishnehad().getNamayande());
                    theKhesaratDB.setPronouncerOrg(user.getMojtamaSodoor());
                }
//                theKhesarat.setTelephoneNamayandegi(darkhastBazkharid.getTelephoneNamayandegi());
                theKhesaratDB.setDarkhastType(DarkhastBazkharid.DarkhastType.KHESARAT);
                theZamime.setDarkhastBazkharid(theKhesaratDB);

                theKhesaratDB.setKhesaratI(theKhesarat1);
                theKhesaratDB.setKhesaratII(theKhesarat2);
                theKhesaratDB.setCreator(user);
                int bazkharidId = darkhastService.saveDarkhastKhesarat(theKhesaratDB);
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theKhesaratDB);
                pishnehadService.updateBimename(bimename);
                darkhast = darkhastService.findDarkhastById(darkhast.getId());

            } else if (darkhast.getDarkhastType().equals(Darkhast.DarkhastType.TAGHIRKOD)) {
                Namayande namayande = namayandeService.getNamayandeByKodeKargozar(kodeNamayandeKargozar);
                if (namayande == null)
                    return "nosession";
                User uBazaryab = loginService.findUserOnlyByUsername(bazarYabUserName);
                if (uBazaryab == null)
                    return "nosession";
                bazarYabUserName = uBazaryab.getUsername();
                putToSession("bazarYabUserNameSes", bazarYabUserName);
                Long userId = result.getId();
                ZamaemDarkhast zamaemDarkhast = new ZamaemDarkhast();
                darkhastService.saveZamaemDarkhast(zamaemDarkhast);
                ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
                darkhastBazkharid.setZamaemDarkhast(zamaemDarkhast);
                pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                bimename = pishnehad.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE);
                bimename.setState(theState);
                bimename.setDarkhastDarJaryanType(darkhast.getDarkhastType());
                darkhastBazkharid.setBimename(bimename);
                String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                String finalDarkhastShomare = bimename.getShomare();
                finalDarkhastShomare += "-" + darkhastSequence;
                darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.TAGHIRKOD);
                darkhastBazkharid.setNamayande(namayande);
                darkhastBazkharid.setCreator(result);
//                darkhastBazkharid.setState(constantItemsService.findStateById(Constant.BAZKHARID_INITIAL_STATE));
                int bazkharidId = darkhastService.saveDarkhastTaghirKod(darkhastBazkharid);
                DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
                theZamime.setDarkhastBazkharid(theBazkharid);
                darkhastService.updateZamaem(theZamime);
                bimename.setDarkhastBazkharid(theBazkharid);
                pishnehadService.updateBimename(bimename);
//                elhaghiye=new Elhaghiye();
//                elhaghiye.set

            }

//            Darkhast d = new Darkhast();
            darkhast.setDarkhastBazkharid(darkhastBazkharid);
//            darkhast.setDarkhastType(Darkhast.DarkhastType.EBTAL);
            darkhast.setDarkhastType(Darkhast.DarkhastType.valueOf(darkhastBazkharid.getDarkhastType().toString()));
            darkhast.setFinished(false);
            darkhastService.saveDarkhast(darkhast);
            return SUCCESS;
        }
    }

    public String retrieve(User user) {
        // todo: this is wrong, performance
//        bimenameha = pishnehadService.findAllBimename();
//        darkhasthayeBazkharid = darkhastService.findAllUnfinishedDarkhastBazkharid();
//        darkhasthayeTaghirat = darkhastService.findAllUnfinishedDarkhastTaghir();
//        reportResult = pishnehadService.findAllowedPishnehads(user);
//        viewReportResult = pishnehadService.findAllowedToViewPishnehads(user.getId());
//        elhaghiyeha=new PaginatedListImpl<Elhaghiye>();
//        elhaghiyeha.setPageNumber(0);
//        elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//        elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha,user.getId());
        return SUCCESS;
    }

    public String loadDarkhastTaghiratReadOnly() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        readOnlyMode = true;
        return loadDarkhastTaghirat();
    }

    public String loadDarkhastTaghirat() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        User result = getUser();
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = result.getId();
            darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
            if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList))
            {
                if (darkhastTaghirat.getHaveBareMali() == null)
                {
                    darkhastTaghirat.setHaveBareMali(true);
                    darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                }
            }
            else
            {
                if (darkhastTaghirat.getHaveBareMali() != null && darkhastTaghirat.getHaveBareMali().equals(true))
                {
                    darkhastTaghirat.setHaveBareMali(false);
                    darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                }
            }
            elhaghiye = darkhastTaghirat.getDarkhast().getElhaghiye();
            pishnehad = darkhastTaghirat.getNewPishnehad();
            if (darkhastTaghirat.getState().getId() == 9080 || darkhastTaghirat.getState().getId() == 9140 || darkhastTaghirat.getState().getId() == 9030 || darkhastTaghirat.getState().getId() == 9160 || darkhastTaghirat.getState().getId() == 9050) {
                if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList)) {
                    try {
                        elhaghiye.setMablagh(mohasebeMablaghElhaghiye(darkhastTaghirat));
                    } catch (BimeNaamehVaSarmayehGozari.CustomValidationException ex) {
                        elhaghiye.setMablagh("خطای محاسباتی");
                        addActionMessage(ex.getMyMessage());
                    }
                } else {
                    elhaghiye.setMablagh("0");
                }
                listBimenameTaghirVM = darkhastService.getListBimenamehayeTaghirKarde(darkhastTaghirat);
            }
            if (darkhastTaghirat.getState().getId() == 9180 || darkhastTaghirat.getState().getId() == 9200|| darkhastTaghirat.getState().getId() == 9190) {
                listBimenameTaghirVM = darkhastService.getListBimenamehayeTaghirKarde(darkhastTaghirat);
            }
            bimename = pishnehadService.findBimenameById(darkhastTaghirat.getNewPishnehad().getBimename().getId());
            karshenasha = darkhastService.findAllKarshenasForDarkhasts();
            setTarhs(constantsService.listAllTarhs());
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
            //----- initialize Validation Constants ------------------
            String date = DateUtil.getCurrentDate();
            if (pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            }
            setValidationConstants(AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date));
            //--------------------------------------------------------
            retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
            bazaryabs = pishnehadService.findBazaryabForNamayande(darkhastTaghirat.getNewPishnehad().getNamayande());
            transitionLogs = bimename.getPishnehad().getTransitionLogs();
            putToSession("isDarkhast", "yes");
            message = OmrUtil.isElhaghieValid(pishnehadFieldChangesList, darkhastTaghirat);
            if (!message.equals("VALID"))
                addActionMessage(message);
            return SUCCESS;
        }
    }

    /*private String mohasebeMablaghElhaghiye(int whenApply) throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        for (Elhaghiye e : darkhastTaghirat.getOldPishnehad().getBimename().getElhaghiyehaDaem()) {
            if (e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT))
                return "دارای الحاقیه دائم";
        }
        if (darkhastTaghirat.getOldPishnehad().getBimename().getGhestBandiList().size() == 0)
            return "قسط بندی نشده";
        int currentSaleBime = darkhastTaghirat.getOldPishnehad().getBimename().getCurrentSaleBimei();
        if (whenApply == 1) { //ati
            if (darkhastTaghirat.getOldPishnehad().getBimename().getGhestBandiList().size() < currentSaleBime + 2) {
                return "تقسیط نشده";
            }
            currentSaleBime++;
        } else if (whenApply == 2) {
            if (darkhastTaghirat.getOldPishnehad().getBimename().getGhestBandiList().size() < currentSaleBime + 1) {
                return "تقسیط نشده";
            }
        }
        long elamBeMaliShodeGhabl = 0L;
        for (Ghest g : darkhastTaghirat.getOldPishnehad().getBimename().getGhestBandiList().get(currentSaleBime).getGhestList()) {
            elamBeMaliShodeGhabl += g.getCredebit().getAmount_long();
        }
        long elamBeMaliShodeJadid = 0L;
        List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), currentSaleBime, darkhastTaghirat.getNewPishnehad().getBimename().getTarikhShorou(), false);
        System.out.println("------ ELAM BE MALI SHODE JADID (" + darkhastTaghirat.getOldPishnehad().getBimename().getShomare() + ") ------");
        for (TaghsitReport tr : taghsitReport) {
            elamBeMaliShodeJadid += (int) tr.getHaghBimePardaakhtiSaal();
            System.out.println("H:" + tr.getHaghBimePardaakhtiSaal() + " T: " + tr.getTarikh() + " MA: " + tr.getMaliat() + " PE: " + tr.getHaghBimePusheshHaayeEzaafi()
                    + " HF: " + tr.getHaghBimeKhaalesFotYekja() + " B: " + tr.getHazineBimeGari() + " E: " + tr.getHazineEdari() + " V: " + tr.getHazineVosul() + " K: " + tr.getKaarmozd()
                    + " PE(H): " + tr.getHaghBimePoosheshHadese() + " PE(A): " + tr.getHaghBimePoosheshAmraz() + " PE(N): " + tr.getHaghBimePoosheshNaghsOzv() + " PE(M): " + tr.getHaghBimePoosheshMoafiat());
        }
        System.out.println("--------------------------------------");
        for (GhestBandi gb : darkhastTaghirat.getOldPishnehad().getBimename().getGhestBandiList()) {
            if (gb.getSaleBimeiInt() > currentSaleBime) {
                for (Ghest g : gb.getGhestList()) {
                    elamBeMaliShodeGhabl += g.getCredebit().getAmount_long();
                }
                taghsitReport = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), gb.getSaleBimeiInt(), darkhastTaghirat.getNewPishnehad().getBimename().getTarikhShorou(), false);
                System.out.println("------ ELAM BE MALI SHODE JADID (" + darkhastTaghirat.getOldPishnehad().getBimename().getShomare() + ") SAL:" + gb.getSaleBimeiInt() + " ------");
                for (TaghsitReport tr : taghsitReport) {
                    elamBeMaliShodeJadid += (int) tr.getHaghBimePardaakhtiSaal();
                    System.out.println("H:" + tr.getHaghBimePardaakhtiSaal() + " T: " + tr.getTarikh() + " MA: " + tr.getMaliat() + " PE: " + tr.getHaghBimePusheshHaayeEzaafi()
                            + " HF: " + tr.getHaghBimeKhaalesFotYekja() + " B: " + tr.getHazineBimeGari() + " E: " + tr.getHazineEdari() + " V: " + tr.getHazineVosul() + " K: " + tr.getKaarmozd()
                            + " PE(H): " + tr.getHaghBimePoosheshHadese() + " PE(A): " + tr.getHaghBimePoosheshAmraz() + " PE(N): " + tr.getHaghBimePoosheshNaghsOzv() + " PE(M): " + tr.getHaghBimePoosheshMoafiat());
                }
                System.out.println("--------------------------------------");
            }
        }
        long elhaghiyeAmount = elamBeMaliShodeJadid - elamBeMaliShodeGhabl;
        return NumberFormat.getNumberInstance().format(OmrUtil.rondPardakhti((int) elhaghiyeAmount));
    } */

    private String mohasebeMablaghElhaghiye(DarkhastTaghirat dt) throws BimeNaamehVaSarmayehGozari.CustomValidationException {
//        for (Elhaghiye e : dt.getOldPishnehad().getBimename().getElhaghiyehaDaem()) {
//            if (e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT))
//                return "دارای الحاقیه دائم";
//        }
        if (dt.getOldPishnehad().getBimename().getGhestBandiList().size() == 0)
            return "قسط بندی نشده";
        if (dt.getOldPishnehad().getBimename().getGhestBandiList().size() < dt.getWhenApply() + 1)
            return "قسط بندی نشده";
        long elamBeMaliShodeGhabl = 0L;
        long elamBeMaliShodeJadid = 0L;
        for(GhestBandi gb : dt.getOldPishnehad().getBimename().getGhestBandiList())
        {
            if(gb.getSaleBimeiInt() >= dt.getWhenApply())
            {
                for(Ghest g : gb.getGhestList())
                {
                    elamBeMaliShodeGhabl += g.getCredebit().getAmount_long();
                }
                // ravesh ghadimi
//                List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(dt.getNewPishnehad(), gb.getSaleBimeiInt(),
//                        dt.getNewPishnehad().getBimename().getTarikhShorou(), false);
//                for (TaghsitReport tr : taghsitReport) {
//                    elamBeMaliShodeJadid += (int) tr.getHaghBimePardaakhtiSaal();
//                }
            }
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User theUser = loginService.findUserByUsername(username);
        elamBeMaliShodeJadid = Long.parseLong(darkhastService.emalElhaghiyeTaghirat(dt, dt.getDarkhast().getElhaghiye(), theUser, true));
        long elhaghiyeAmount = elamBeMaliShodeJadid - elamBeMaliShodeGhabl;
        return NumberFormat.getNumberInstance().format(OmrUtil.rondPardakhti((int) elhaghiyeAmount));
    }

    public String loadDarkhastReadOnly() {
        readOnlyMode = true;
        return loadDarkhast();
    }

    public String loadDarkhast() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        User user = getUser();
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = result.getId();
//            user = loginService.findUserById(userId);
            if (darkhastBazkharid != null) {
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
//                retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(),result.getId());
//                pishnehad = darkhastBazkharid.getBimename().getPishnehad();
                bimename = pishnehadService.findBimenameById(darkhastBazkharid.getBimename().getId());

                if (darkhastBazkharid.getDarkhast() != null) {
//                    if (darkhastBazkharid.getDarkhast().getDarkhastType().equals(Darkhast.DarkhastType.KHESARAT)){
//                        khesaratAction = true;
//                    }
//                    else {
                    if (darkhastBazkharid.getDarkhast().getDarkhastType().equals(Darkhast.DarkhastType.KHESARAT)) {
                        if (darkhastBazkharid.getKhesaratII() == null) {

                        }
                    }
                    if (!darkhastBazkharid.getDarkhast().getDarkhastType().equals(Darkhast.DarkhastType.KHESARAT))
                    {
                        elhaghiye = darkhastBazkharid.getDarkhast().getElhaghiye();
                        if (elhaghiye == null) {
                            elhaghiye = new Elhaghiye();
                            darkhastBazkharid.getDarkhast().setElhaghiye(elhaghiye);
                            if (darkhastBazkharid.getDarkhastType() == DarkhastBazkharid.DarkhastType.TOZIH) {
                                elhaghiye.setMablagh("0");
                                elhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                                elhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
                                tozih = true;
                            }
                        }
                    }
//                    }
                }
//                if (darkhastBazkharid.getDarkhast().getElhaghiye() != null) {
//                    elhaghiye = darkhastBazkharid.getDarkhast().getElhaghiye();
//                }
                try {
                    telephonSabetBimeShodeKod = darkhastBazkharid.getTelephonSabetBimeShode().substring(0, 3);
                    if (!telephonSabetBimeShodeKod.equals("021")) {
                        telephonSabetBimeShodeKod = darkhastBazkharid.getTelephonSabetBimeShode().substring(0, 4);
                        telephonSabetBimeShode = darkhastBazkharid.getTelephonSabetBimeShode().substring(4, 11);
                    } else {
                        telephonSabetBimeShode = darkhastBazkharid.getTelephonSabetBimeShode().substring(3, 11);
                    }
                } catch (Exception ex) {
                }

            } else if (darkhastTaghirat != null) {
                darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                bimename = pishnehadService.findBimenameById(darkhastTaghirat.getOldPishnehad().getBimename().getId());
            }
            karshenasha = darkhastService.findAllKarshenasForDarkhasts();
            karshenasKhesaratha = pishnehadService.findAllKarshenasKhesarat();
            setTarhs(constantsService.listAllTarhs());
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();

            pishnehad = darkhastBazkharid.getBimename().getPishnehad();
            estelam=pishnehad.getEstelam();
            //----- initialize Validation Constants ------------------
            String date = DateUtil.getCurrentDate();
            if (pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            }
//            if (pishnehad != null) {
            setValidationConstants(AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date));
//                bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());
//            }
            //--------------------------------------------------------

            putToSession("isDarkhast", "yes");
            bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());
            if (darkhastBazkharid != null) {
                if (darkhastBazkharid.getState() != null) {
                    retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), userId);
                    if (darkhastBazkharid.getState().getId() == 12160) {
                        CredebitAction.mohasebeAndukhteBimename(darkhastBazkharid.getBimename(), DateUtil.getCurrentDate(),null);
                        andukhte = darkhastBazkharid.getBimename().getAndukhteyeGhatie();
                    }
                }
                transitionLogs = pishnehad.getTransitionLogs();
                if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
                    asnadeSodorService.setAndukhteAndArzeshBazkharid(pishnehad.getBimename(), DateUtil.getCurrentDate());
//                    double arzesheBazkharidDouble = pishnehad.getBimename().getArzeshBazkharidGhatie();

                    if((darkhastBazkharid.getShomareBardashtAzAndukhte()==null || darkhastBazkharid.getShomareBardashtAzAndukhte().isEmpty())&& darkhastBazkharid.getCredebitBardasht()==null)
                    {
                        CredebitAction.mohasebeAndukhteBimename(darkhastBazkharid.getBimename(), DateUtil.getCurrentDate(),null);
                        andukhte = darkhastBazkharid.getBimename().getAndukhteyeGhatie();
//                        andukhteDoubleBardasht = darkhastBazkharid.getAndukhteGhatie() != null ? Double.parseDouble(darkhastBazkharid.getAndukhteGhatie().replaceAll(",", "").trim()) : getBimename().getAndukhteyeGhatie();
                        hadeAksarBardasht = NumberFormat.getNumberInstance().format(calcHadeAksarBardasht(darkhastBazkharid.getBimename(), andukhte, asnadeSodorService));
                    }
                    if(darkhastBazkharid.getShomareBardashtAzAndukhte()!=null && !darkhastBazkharid.getShomareBardashtAzAndukhte().isEmpty())
                    {
                        timeToElamMali = true;
                    }
                    if(darkhastBazkharid.getState().getId().equals(11090))
                        jameKolBedehi=darkhastBazkharid.getJameKolBedehiVam();
                    else
                    {
                        jameKolBedehi=0l;
                        for(DarkhastBazkharid db : bimename.getAllDarkhasts())
                        {
                            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && db.getState().getId().equals(Constant.VAM_FINAL_STATE))
                            {
                                jameKolBedehi+= Math.round(MohasebateFaniVam.calcJaraemeDirkard(db, asnadeSodorService)+
                                                MohasebateFaniVam.calcJaraemeTavigh(db))+
                                                Math.round(MohasebateFaniVam.calcJameAslAghsatAti(db))+
                                                Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(db));
                            }
                        }
                    }

//                    jameAghsatMoavaghBimename = Long.parseLong(bimename.getAmountMoavagh().replaceAll(",", "").trim());
                    jameAghsatMoavaghBimename = Long.parseLong(darkhastBazkharid.getJameAghsatMoavaghBimenameCalc().replaceAll(",","").trim());
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
                    hadeAksarVam = NumberFormat.getNumberInstance().format(calcHadeAksarVam(darkhastBazkharid.getBimename(), asnadeSodorService));
                    tedadAghsatMoavagh = calcTedadAghsatMoavagh(darkhastBazkharid);
                    jameJaraemDirkard = Math.round(MohasebateFaniVam.calcJaraemeDirkard(darkhastBazkharid, asnadeSodorService) + MohasebateFaniVam.calcJaraemeTavigh(darkhastBazkharid));
                    jameAslAghsatAti = Math.round(MohasebateFaniVam.calcJameAslAghsatAti(darkhastBazkharid));
                    jameKolAghsatMoavaq = Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(darkhastBazkharid));
                    jameKolAghsatMoavaqJarime = jameKolAghsatMoavaq + jameJaraemDirkard;
                    jameKolBedehi = jameKolAghsatMoavaqJarime + jameAslAghsatAti;
                    jameAghsatMoavaghBimename = (Long) MohasebateFaniVam.calcJamAghsatMoavaghBimename(asnadeSodorService)[0];
                    final List<GhestVam> ghestVams;

                    if (darkhastBazkharid.getMablagheVamedarkhasti() != null && darkhastBazkharid.getNahveyePardakhteAghsat() != null) {
                        final double a = Double.parseDouble(darkhastBazkharid.getMablagheVamedarkhasti().replace(",", "").trim());
                        final double r = Constant.NERKHE_BAHRE_SALANEH_BE_DARSAD / 100.0;

                        if (darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
                            final int m1 = Integer.parseInt(darkhastBazkharid.getModatebazpardakht());
                            final double j1 = Math.pow(1 + r, 1.0 / 12.0) - 1;
                            final double tmp = Math.pow(1 + j1, m1);
                            final double r1 = (a * j1 * tmp) / (tmp - 1);
                            final double v1 = 1.0 / (1 + j1);

                            double bahreP = 0;
                            ghestVams = new ArrayList<GhestVam>(m1);
                            for (int i = 1; i <= m1; i++) {
                                final GhestVam gv = new GhestVam(darkhastBazkharid.getNahveyePardakhteAghsat());
                                gv.r1 = r1;
                                gv.i1 = r1 * (1 - Math.pow(v1, m1 - i + 1));
                                gv.p1 = r1 * Math.pow(v1, m1 - i + 1);
                                bahreP += gv.i1;
                                ghestVams.add(gv);
                            }
                            final double maliat = Constant.NERKHE_Maliat * bahreP;
                            Long bahrPardakhti = 0l;
                            if (darkhastBazkharid.getGhestBandi() != null) {
                                for (Ghest g : darkhastBazkharid.getGhestBandi().getGhestList()) {
                                    bahrPardakhti += Long.parseLong(g.getHazineKarmonz().replaceAll(",", "").trim());
                                }
                                bahrePardakhti = NumberFormat.getInstance().format(bahrPardakhti);//String.valueOf(NumberFormat.getNumberInstance().format(Math.round(bahreP)));
                                maliatBarArzeshAfzude = NumberFormat.getNumberInstance().format(OmrUtil.rondPardakhti(darkhastBazkharid.getGhestBandi().getGhestList().get(0).getMaliatLong().intValue()));//NumberFormat.getInstance().format(darkhastBazkharid.getGhestBandi().getGhestList().get(0).getCredebit().getAmount_long() - darkhastBazkharid.getGhestBandi().getGhestList().get(1).getCredebit().getAmount_long());//String.valueOf(NumberFormat.getNumberInstance().format(Math.round(maliat)));
                                mablaghGhestVam = darkhastBazkharid.getGhestBandi().getGhestList().get(1).getCredebit().getAmount(); //String.valueOf(NumberFormat.getNumberInstance().format(Math.round(r1)));
                                jamKolAghsatVam = NumberFormat.getInstance().format(darkhastBazkharid.getGhestBandi().getMajmooeAmount() - Long.parseLong(maliatBarArzeshAfzude.replaceAll(",", "").trim()));//String.valueOf(NumberFormat.getNumberInstance().format(Math.round(m1 * r1)));
                            }
                            ghestVams.get(0).r1 += maliat;

                        } else {
                            final int m2 = Integer.parseInt(darkhastBazkharid.getModatebazpardakht()) / 3;
                            final double j2 = Math.pow(1 + r, 1.0 / 4.0) - 1;
                            final double tmp = Math.pow(1 + j2, m2);
                            final double r2 = (a * j2 * tmp) / (tmp - 1);
                            final double v2 = 1.0 / (1 + j2);

                            double bahreP = 0;
                            ghestVams = new ArrayList<GhestVam>(m2);
                            for (int i = 1; i <= m2; i++) {
                                final GhestVam gv = new GhestVam(darkhastBazkharid.getNahveyePardakhteAghsat());
                                gv.r2 = r2;
                                gv.i2 = r2 * (1 - Math.pow(v2, m2 - i + 1));
                                gv.p2 = r2 * Math.pow(v2, m2 - i + 1);
                                bahreP += gv.i2;
                                ghestVams.add(gv);
                            }
                            final double maliat = Constant.NERKHE_Maliat * bahreP;
                            Long bahrPardakhti = 0l;
                            if (darkhastBazkharid.getGhestBandi() != null) {
                                for (Ghest g : darkhastBazkharid.getGhestBandi().getGhestList()) {
                                    bahrPardakhti += Long.parseLong(g.getHazineKarmonz().replaceAll(",", "").trim());
                                }
                                bahrePardakhti = NumberFormat.getInstance().format(bahrPardakhti);//bahrePardakhti = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(bahreP)));
                                maliatBarArzeshAfzude = NumberFormat.getNumberInstance().format(OmrUtil.rondPardakhti(darkhastBazkharid.getGhestBandi().getGhestList().get(0).getMaliatLong().intValue()));//NumberFormat.getInstance().format(darkhastBazkharid.getGhestBandi().getGhestList().get(0).getCredebit().getAmount_long() - darkhastBazkharid.getGhestBandi().getGhestList().get(1).getCredebit().getAmount_long());//maliatBarArzeshAfzude = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(maliat)));
                                mablaghGhestVam = darkhastBazkharid.getGhestBandi().getGhestList().get(1).getCredebit().getAmount();//mablaghGhestVam = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(r2)));
                                jamKolAghsatVam = NumberFormat.getInstance().format(darkhastBazkharid.getGhestBandi().getMajmooeAmount() - Long.parseLong(maliatBarArzeshAfzude.replaceAll(",", "").trim()));//jamKolAghsatVam = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(m2 * r2)));
                            }
                            ghestVams.get(0).r2 += maliat;

                        }
                    }
                } else if (darkhastBazkharid.getDarkhastType() == DarkhastBazkharid.DarkhastType.TOZIH) {


                }
            } else if (darkhastTaghirat != null) {
                retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
            }
            handleSessionThingy();


            user = result;
// todo should be corrected
//            darkhast = darkhastService.findDarkhastById(darkhast.getId());
            return SUCCESS;
        }
    }

    private void handleSessionThingy() {
        backfromupload = (String) getSession().remove("backfromupload");
        logicaldocIndicator = (String) getSession().remove("logicaldocIndicator");
        errorsMap = (Map<String, String>) getSession().remove("errorsMap");
    }

    public static Long calcHadeAksarVam(Bimename bimename, IAsnadeSodorService asnadeSodorService) {
        CredebitAction.mohasebeAndukhteBimename(bimename, DateUtil.getCurrentDate(), null);
        return Math.round(bimename.getArzeshBazkharidGhatie() * 0.9) < 0 ? 0 : Math.round(bimename.getArzeshBazkharidGhatie() * 0.9);
    }

    public String validateCalcVam() {
        final double vam = Double.valueOf(darkhastBazkharid.getMablagheVamedarkhasti().replace(",", "").trim());
        final DarkhastBazkharid dB = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        if (clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeAval().getId()) == null || clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeDovom().getId()) == null) {
            addActionError("امضا کننده درست انتخاب نشده است");
            return loadDarkhast().equals(SUCCESS) ? INPUT : ERROR;
        }
        final double hadeAksarVam = calcHadeAksarVam(dB.getBimename(), asnadeSodorService);
        if (vam > hadeAksarVam) {
            addActionError("مبلغ درخواستي از حداکثر وام بيشتر مي باشد.");
            return loadDarkhast().equals(SUCCESS) ? INPUT : ERROR;
        }
        int tedadGhest = Integer.valueOf(darkhastBazkharid.getModatebazpardakht());
        if (!darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
            tedadGhest /= 3;
        }
        for (int i = 0; i < tedadGhest; i++) {
            int amount = 0;
            String sarresidDate = "";
            if (darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
                sarresidDate = DateUtil.addMonth(darkhastBazkharid.getTarikheAvalinGhest(), i);
            } else {
                sarresidDate = DateUtil.addMonth(darkhastBazkharid.getTarikheAvalinGhest(), 3 * i);
            }

            if (DateUtil.isGreaterThan(sarresidDate, dB.getBimename().getTarikhEngheza())) {

                addActionMessage("تاریخ سررسید اقساط وام نمی تواند بعد از تاریخ انقضاء بیمه نامه (  " + dB.getBimename().getTarikhEngheza() + ") باشد.");
                return loadDarkhast().equals(SUCCESS) ? INPUT : ERROR;
            }
        }
        return SUCCESS;
    }

    public String calcVam() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = result.getId();
            DarkhastBazkharid oldDarkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            oldDarkhastBazkharid.setEmzaKonandeAval(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeAval().getId()));
            oldDarkhastBazkharid.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeDovom().getId()));
            oldDarkhastBazkharid.setModatebazpardakht(darkhastBazkharid.getModatebazpardakht());
            oldDarkhastBazkharid.setMablagheVamedarkhasti(darkhastBazkharid.getMablagheVamedarkhasti());
            oldDarkhastBazkharid.setNahveyePardakhteAghsat(darkhastBazkharid.getNahveyePardakhteAghsat());

            avalinTarikhGhestInvalid = false;
            if (oldDarkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
//                if (darkhastBazkharid.getTarikheAvalinGhest() != null &&
//                        (DateUtil.isGreaterThan(DateUtil.addMonth(DateUtil.getCurrentDate(), 1), darkhastBazkharid.getTarikheAvalinGhest()) ||
//                        DateUtil.isGreaterThan(darkhastBazkharid.getTarikheAvalinGhest(), DateUtil.addMonth(DateUtil.getCurrentDate(), 2)))) {
                if (darkhastBazkharid.getTarikheAvalinGhest() != null && !darkhastBazkharid.getTarikheAvalinGhest().equals(DateUtil.addMonth(DateUtil.getCurrentDate(), 1))) {
                    avalinTarikhGhestInvalid = true;
                }
            } else {
//                if (darkhastBazkharid.getTarikheAvalinGhest() != null &&
//                        (DateUtil.isGreaterThan(DateUtil.addMonth(DateUtil.getCurrentDate(), 3), darkhastBazkharid.getTarikheAvalinGhest()) ||
//                        DateUtil.isGreaterThan(darkhastBazkharid.getTarikheAvalinGhest(), DateUtil.addMonth(DateUtil.getCurrentDate(), 4)))) {
//                    avalinTarikhGhestInvalid = true;
//                }
                if (darkhastBazkharid.getTarikheAvalinGhest() != null && !darkhastBazkharid.getTarikheAvalinGhest().equals(DateUtil.addMonth(DateUtil.getCurrentDate(), 3))) {
                    avalinTarikhGhestInvalid = true;
                }
            }

            if (oldDarkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
                oldDarkhastBazkharid.setTarikheAvalinGhest(DateUtil.addDays(DateUtil.getCurrentDate(), 30));
            } else {
                oldDarkhastBazkharid.setTarikheAvalinGhest(DateUtil.addDays(DateUtil.getCurrentDate(), 3 * 30));
            }
            if (darkhastBazkharid.getTarikheAvalinGhest() != null) {
                oldDarkhastBazkharid.setTarikheAvalinGhest(darkhastBazkharid.getTarikheAvalinGhest());
            }
            darkhastService.updateDarkhastBazkharid(oldDarkhastBazkharid);
            darkhastBazkharid = oldDarkhastBazkharid;

            if (!avalinTarikhGhestInvalid) {
                ghestBandiVam();
            }

            bimename = pishnehadService.findBimenameById(oldDarkhastBazkharid.getBimename().getId());
            karshenasha = darkhastService.findAllKarshenasForDarkhasts();
            if (darkhastBazkharid.getState().getId().equals(10080)) {
                Transition t = pishnehadService.findTransitionById(10009);
                darkhastService.transitDarkhast(result, darkhastBazkharid.getId(), 10009, logmessage);
            }
            retrieveByDarkhastBazkharidId(oldDarkhastBazkharid.getId(), userId);

// todo should be corrected
//            darkhast = darkhastService.findDarkhastById(darkhast.getId());
            type = "tabs-103";
            return loadDarkhast();
        }

    }

    public String ajaxlyCheckJaraemeVaam() {

        darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        DarkhastBazkharid vam = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getTasvieVamPishAzMoedId());
        if (darkhastBazkharid.getState().getId() <= 12090) {
            MohasebateFaniVam mohasebateFaniVam = new MohasebateFaniVam();
            mohasebateFaniVam.setBazPardakhtType(MohasebateFaniVam.BazPardakhtType.valueOf(vam.getNahveyePardakhteAghsat()));
            mohasebateFaniVam.setMablagheVam(Double.parseDouble(vam.getMablagheVamedarkhasti().replaceAll(",", "").trim()));
            mohasebateFaniVam.setModatebazPardakht(Integer.parseInt(vam.getModatebazpardakht()));
            darkhastBazkharid.setJaraemeDirkard(String.valueOf(Math.round(mohasebateFaniVam.calcJaraemeDirkard(vam, asnadeSodorService))));
            darkhastBazkharid.setJameKolAghsatMoavagh(String.valueOf(Math.round(mohasebateFaniVam.calcJameKolAghsatMoavagh(vam))));
            darkhastBazkharid.setJameAslAghsatAti(String.valueOf(Math.round(mohasebateFaniVam.calcJameAslAghsatAti(vam))));
//            darkhastBazkharid.setJaraemeTavigh(String.valueOf(Math.round(mohasebateFaniVam.calcJaraemeTavigh(vam))));
//            darkhastBazkharid.setJameAghsatPardakhtNashode(String.valueOf(Math.round(mohasebateFaniVam.calcJameAghsatePardakhtNashode(vam))));
            darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
        }
        return SUCCESS;
    }

    private void ghestBandiVam() {
        if (darkhastBazkharid.getGhestBandi() != null) {
//            GhestBandi oldGb = darkhastBazkharid.getGhestBandi();
            darkhastBazkharid.setGhestBandi(null);
            darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
            //todo: orphaned ghests
//            ArrayList<Integer> gIds = new ArrayList<Integer>();
//            boolean finished = false;
//            while (!finished) {
//                Ghest ghest = oldGb.getGhestList().get(0);
//                asnadeSodorService.deleteCredebitById(ghest.getCredebit().getId());
//                oldGb.getGhestList().remove(ghest);
//                asnadeSodorService.deleteGhestsById(ghest.getId());
//                if(oldGb.getGhestList().size() == 0) finished = true;
//            }
//            asnadeSodorService.deleteGhestBandiById(oldGb);
        }

        MohasebateFaniVam mohasebateFaniVam = new MohasebateFaniVam();
        mohasebateFaniVam.setBazPardakhtType(MohasebateFaniVam.BazPardakhtType.valueOf(darkhastBazkharid.getNahveyePardakhteAghsat()));
        mohasebateFaniVam.setMablagheVam(Double.parseDouble(darkhastBazkharid.getMablagheVamedarkhasti().replace(",", "")));
        mohasebateFaniVam.setModatebazPardakht(Integer.parseInt(darkhastBazkharid.getModatebazpardakht()));
        int tedadGhest = Integer.valueOf(darkhastBazkharid.getModatebazpardakht());
        if (!darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
            tedadGhest /= 3;
            mohasebateFaniVam.setModatebazPardakht(tedadGhest);
        }
        GhestBandi ghestBandi = new GhestBandi();
        ghestBandi.setBimename(darkhastBazkharid.getBimename());
        ghestBandi.setType(GhestBandi.Type.G_VAM);
//        ghestBandi.setSaleBimei(String.valueOf(saleBimei));
        ghestBandi.setTarikheTaghsit(DateUtil.getCurrentDate());
        asnadeSodorService.saveGhestBandi(ghestBandi);
        List<Ghest> ghestList = new ArrayList<Ghest>();
        List<Credebit> credebitList = new ArrayList<Credebit>();
        Pishnehad thePishnehad = darkhastBazkharid.getBimename().getPishnehad();
        Long majmoAmount = 0l;
        long ekhtelafRounding = 0L;
        for (int i = 0; i < tedadGhest; i++) {
            int amount = 0;
            if (i == 0) {
                amount = (int) Math.round(mohasebateFaniVam.calcMohasebeyeGhesteVam() + mohasebateFaniVam.calcMaliatbarArzesheAfzoode());
            } else {
                amount = (int) Math.round(mohasebateFaniVam.calcMohasebeyeGhesteVam());
            }
            String sarresidDate = "";
            if (darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE")) {
                sarresidDate = DateUtil.addMonth(darkhastBazkharid.getTarikheAvalinGhest(), i);
            } else {
                sarresidDate = DateUtil.addMonth(darkhastBazkharid.getTarikheAvalinGhest(), 3 * i);
            }
            Ghest ghest = new Ghest();
            if (i == tedadGhest - 1) {
                amount += ekhtelafRounding;
            } else {
                ekhtelafRounding += amount - OmrUtil.rondPardakhti(amount);
            }
            amount = OmrUtil.rondPardakhti(amount);
            majmoAmount += amount;
            Credebit credebit = new Credebit(amount, "قسط وام", darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName(),
                    sarresidDate, "OMR", "GHEST_VAM", darkhastBazkharid.getBimename().getShomare(), sequenceService.nextShenaseCredebit(), "");
            credebit.setGhest(ghest);
            ghest.setCredebit(credebit);
            ghest.setGhestBandi(ghestBandi);
            ghest.setSarresidDate(sarresidDate);
            ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(mohasebateFaniVam.calcMablagheBahreyePardakhti(i + 1))));
            ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(mohasebateFaniVam.calcMablaghePardakhtiAzAsleVam(i + 1))));
            if (i == 0) {
                ghest.setMaliatLong(((Double) mohasebateFaniVam.calcMaliatbarArzesheAfzoode()).longValue());
            } else {
                ghest.setMaliatLong(0l);
            }

            thePishnehad.getCredebits().add(credebit);
            ghest.setNumber(i+1);
            ghestList.add(ghest);
            credebitList.add(credebit);
        }
        pishnehadService.updatePishnehad(thePishnehad);
        asnadeSodorService.saveAghsat(ghestList);
        asnadeSodorService.saveCredebitList(credebitList);
        ghestBandi = asnadeSodorService.findGhestBandiById(ghestBandi.getId());
        ghestBandi.setMajmooeAmount(majmoAmount);
        asnadeSodorService.updateGhestbandi(ghestBandi);
        // todo: majmooha
        ghestBandi.setGhestList(ghestList);
        darkhastBazkharid.setGhestBandi(ghestBandi);
        darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
    }

    public String removeFileZamime() {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theResult = loginService.findUserByUsername(theUsername);
        if (theResult.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            backfromupload = "true";
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = null;

            try {
                sid = l.getAuthServiceImplPort().login("admin", "admin");
            } catch (RemoteException e) {
                logicaldocIndicator = Constant.ERROR;
                getSession().put("backfromupload", backfromupload);
                getSession().put("logicaldocIndicator", logicaldocIndicator);
                return ERROR;
            } catch (ServiceException e) {
                logicaldocIndicator = Constant.ERROR;
                getSession().put("backfromupload", backfromupload);
                getSession().put("logicaldocIndicator", logicaldocIndicator);
                return ERROR;
            }
            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
            DocumentService documentService = null;
            try {
                documentService = docLocator.getDocumentServiceImplPort();
            } catch (ServiceException e) {
                logicaldocIndicator = Constant.ERROR;
                getSession().put("backfromupload", backfromupload);
                getSession().put("logicaldocIndicator", logicaldocIndicator);
                return ERROR;
            }
            if (documentService != null) {
                try {
                    documentService.delete(sid, Long.parseLong(fileId));
                } catch (RemoteException e) {
                    logicaldocIndicator = Constant.ERROR;
                    getSession().put("backfromupload", backfromupload);
                    getSession().put("logicaldocIndicator", logicaldocIndicator);
                    return ERROR;
                }
            }
            ZamaemDarkhast theZamime = darkhastService.findZamaemDarkhastById(zamaemDarkhast.getId());
//            pishnehad = pishnehadService.findById(pishnehad.getId());
            if (theZamime.getFileAgahiChapId() != null && theZamime.getFileAgahiChapId() == Integer.parseInt(fileId)) {
                theZamime.setFileAgahiChapDescription(null);
                theZamime.setFileAgahiChapName(null);
                theZamime.setFileAgahiChapId(null);
            } else if (theZamime.getFileDarkhastBahremandiId() != null && theZamime.getFileDarkhastBahremandiId() == Integer.parseInt(fileId)) {
                theZamime.setFileDarkhastBahremandiDescription(null);
                theZamime.setFileDarkhastBahremandiId(null);
                theZamime.setFileDarkhastBahremandiName(null);
            } else if (theZamime.getFileElameMafghudiId() != null && theZamime.getFileElameMafghudiId() == Integer.parseInt(fileId)) {
                theZamime.setFileElameMafghudiDescription(null);
                theZamime.setFileElameMafghudiId(null);
                theZamime.setFileElameMafghudiName(null);
            } else if (theZamime.getFileElhaghiyeId() != null && theZamime.getFileElhaghiyeId() == Integer.parseInt(fileId)) {
                theZamime.setFileElhaghiyeDescription(null);
                theZamime.setFileElhaghiyeId(null);
                theZamime.setFileElhaghiyeName(null);
            } else if (theZamime.getFileAkharinVaziatId() != null && theZamime.getFileAkharinVaziatId() == Integer.parseInt(fileId)) {
                theZamime.setFileAkharinVaziatDescription(null);
                theZamime.setFileAkharinVaziatId(null);
                theZamime.setFileAkharinVaziatName(null);
            } else if (theZamime.getFileShenaseBimegozarJadidId() != null && theZamime.getFileShenaseBimegozarJadidId() == Integer.parseInt(fileId)) {
                theZamime.setFileShenaseBimegozarJadidDescription(null);
                theZamime.setFileShenaseBimegozarJadidId(null);
                theZamime.setFileShenaseBimegozarJadidName(null);
            } else if (theZamime.getFileShenaseBimegozarGhadimId() != null && theZamime.getFileShenaseBimegozarGhadimId() == Integer.parseInt(fileId)) {
                theZamime.setFileShenaseBimegozarGhadimDescription(null);
                theZamime.setFileShenaseBimegozarGhadimId(null);
                theZamime.setFileShenaseBimegozarGhadimName(null);
            } else if (theZamime.getFileShenaseBimeshodeGhadimId() != null && theZamime.getFileShenaseBimeshodeGhadimId() == Integer.parseInt(fileId)) {
                theZamime.setFileShenaseBimeshodeGhadimDescription(null);
                theZamime.setFileShenaseBimeshodeGhadimId(null);
                theZamime.setFileShenaseBimeshodeGhadimName(null);
            } else if (theZamime.getFileKollieId() != null && theZamime.getFileKollieId() == Integer.parseInt(fileId)) {
                theZamime.setFileKollieDescription(null);
                theZamime.setFileKollieId(null);
                theZamime.setFileKollieName(null);
            } else if (theZamime.getFileSayerId() != null && theZamime.getFileSayerId() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription(null);
                theZamime.setFileSayerId(null);
                theZamime.setFileSayerName(null);
            } else if (theZamime.getFileAdameTataboghEmzaId() != null && theZamime.getFileAdameTataboghEmzaId() == Integer.parseInt(fileId)) {
                theZamime.setFileAdameTataboghEmzaDescription(null);
                theZamime.setFileAdameTataboghEmzaId(null);
                theZamime.setFileAdameTataboghEmzaName(null);
            } else if (theZamime.getFileFishBedehiVamId() != null && theZamime.getFileFishBedehiVamId() == Integer.parseInt(fileId)) {
                theZamime.setFileFishBedehiVamDescription(null);
                theZamime.setFileFishBedehiVamId(null);
                theZamime.setFileFishBedehiVamName(null);
            } else if (theZamime.getFileElamKhesaratId() != null && theZamime.getFileElamKhesaratId() == Integer.parseInt(fileId)) {
                theZamime.setFileElamKhesaratDescription(null);
                theZamime.setFileElamKhesaratId(null);
                theZamime.setFileElamKhesaratName(null);
            }
            darkhastService.updateZamaem(theZamime);
//            pishnehadService.updatePishnehad(pishnehad);
            if (darkhastBazkharid != null) {
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            } else if (darkhastTaghirat != null) {
                darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            }
            Long userId = theResult.getId();
            if (darkhastBazkharid != null) {
                retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), userId);
            } else if (darkhastTaghirat != null) {
                retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
            }
            getSession().put("backfromupload", backfromupload);
            return SUCCESS;
        }
    }

    public String saveBazkharidEbtalInfo() {
        DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        if (darkhastBazkharid.getShomareHesab() != null & !darkhastBazkharid.getShomareHesab().equals(""))
            theDarkhast.setShomareHesab(darkhastBazkharid.getShomareHesab());
        if (darkhastBazkharid.getShomareShaba() != null & !darkhastBazkharid.getShomareShaba().equals(""))
            theDarkhast.setShomareShaba(darkhastBazkharid.getShomareShaba());
        if (darkhastBazkharid.getBankName() != null & !darkhastBazkharid.getBankName().equals(""))
            theDarkhast.setBankName(darkhastBazkharid.getBankName());
        if (darkhastBazkharid.getShobeName() != null & !darkhastBazkharid.getShobeName().equals(""))
            theDarkhast.setSahebHesab(darkhastBazkharid.getShobeName());
        if (darkhastBazkharid.getKodeShobe() != null & !darkhastBazkharid.getKodeShobe().equals(""))
            theDarkhast.setKodeShobe(darkhastBazkharid.getKodeShobe());
        if (darkhastBazkharid.getSahebHesab() != null & !darkhastBazkharid.getSahebHesab().equals(""))
            theDarkhast.setSahebHesab(darkhastBazkharid.getSahebHesab());
        darkhastService.updateDarkhastBazkharid(theDarkhast);
        darkhastBazkharid.setId(theDarkhast.getId());
        return SUCCESS;
    }

    public void prepareObjectsForElhaghieForm()
    {

    }

    public String sodooreElhaghie() throws Exception {
        User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (darkhastTaghirat.getId() != null) {
                DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                theDarkhast.setJoziat(darkhastTaghirat.getJoziat());
                int i=0;
                for(Elhaghiye theElhaghiye : theDarkhast.getDarkhast().getElhaghiyeList()) {
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                    theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                    theElhaghiye.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                    theElhaghiye.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
                    theElhaghiye.setMablagh(elhaghiye.getMablagh());
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());
                    if(i==0)
                    {
                        theElhaghiye.setMozoo(elhaghiye.getMozoo());
                        theElhaghiye.setMatn(elhaghiye.getMatn());
                    }
                    else
                    {
                        if(infoElhaghie.get(i).equals("NEW"))
                        {
                            theElhaghiye.setMozoo(darkhastTaghirat.getDarkhast().getElhaghiyeList().get(i).getMozoo());
                            theElhaghiye.setMatn(darkhastTaghirat.getDarkhast().getElhaghiyeList().get(i).getMatn());
                            theElhaghiye.setTarikhAsar(darkhastTaghirat.getDarkhast().getElhaghiyeList().get(i).getTarikhAsar());
                        }
                        else//if(infoElhaghie[i].equals("CURRENT"))
                        {
                            theElhaghiye.setMozoo(elhaghiye.getMozoo());
                            theElhaghiye.setMatn(elhaghiye.getMatn());
                        }
                    }

                    darkhastService.updateElhaghiye(theElhaghiye);
                    i++;
                }
                darkhastService.updateDarkhastTaghirat(theDarkhast);
                darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                elhaghiye = darkhastTaghirat.getDarkhast().getElhaghiye();
                try {
                    pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
                    if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList))
                    {
                        if (darkhastTaghirat.getHaveBareMali() == null)
                        {
                            darkhastTaghirat.setHaveBareMali(true);
                            darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                        }
                    }
                    else
                    {
                        if (darkhastTaghirat.getHaveBareMali() != null && darkhastTaghirat.getHaveBareMali().equals(true))
                        {
                            darkhastTaghirat.setHaveBareMali(false);
                            darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                        }
                    }

                    if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList)) {
                        String logRaw = darkhastService.emalElhaghiyeTaghirat(darkhastTaghirat, elhaghiye, user, false);
                        //reGenerate ghestNumber
                        for(GhestBandi gb : darkhastTaghirat.getNewPishnehad().getBimename().getGhestBandiList())
                        {
                            OmrUtil.generateGhestNumber(gb);
                            for(Ghest g : gb.getGhestList())
                            {
                                asnadeSodorService.updateGhest(g);
                            }
                        }
//                        elhaghiye.setLog(log);
                        final org.slf4j.Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
                        String log = "\r\n----------- ELHAGHIYE TAGHIR LOG FOR " + darkhastTaghirat.getNewPishnehad().getBimename().getShomare() + "(dId:"+darkhastTaghirat.getId()+") -----------\r\n";
                        log += logRaw;
                        log += "------------------------------------------------------------------------------------------------------------------------------------";
                        logger.info(log);
                        darkhastService.updateElhaghiye(elhaghiye);
                    }
                    if (!Constant.DEV_testElhaghieTaghirat) {
                        // ashkhas
                        boolean isBimeShode = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMESHODE);
                        boolean isBimeGozar = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMEGOZAR);
                        Shakhs newShakhs = darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs();
                        if (isBimeGozar)
                            newShakhs = darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs();
                        if (isBimeShode || isBimeGozar)
                        {
                            if (darkhastTaghirat.getDarkhast().getElhaghiyeList().size() > 1)
                            {
                                // todo: farze yek taghir shakhs dar ye elhaghie
                                pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
                                boolean changeBimeGozarAddress = false;
                                boolean changeBimeShodeAddress = false;
                                for (PishnehadFieldChanges pChanges : pishnehadFieldChangesList)
                                {
                                    if (pChanges.getCategory().equals(("AD")))
                                    {
                                        if (pChanges.getSubject().contains("بیمه گذار"))
                                        {
                                            changeBimeGozarAddress = true;
                                        }

                                        else if (pChanges.getSubject().contains("بیمه شده"))
                                        {
                                            changeBimeShodeAddress = true;
                                        }
                                    }
                                }

                                List<ListBimenameTaghirVM> bTaghirList = darkhastService.getListBimenamehayeTaghirKarde(darkhastTaghirat);
                                for (ListBimenameTaghirVM bt : bTaghirList)
                                {
                                    for (Elhaghiye el : darkhastTaghirat.getDarkhast().getElhaghiyeList())
                                    {
                                        if (el.getOldPishnehad() == null || el.getOldPishnehad().getId() == darkhastTaghirat.getOldPishnehad().getId())
                                            continue;

                                        else if (bt.getBimename()==null)
                                        {
                                            Pishnehad p=pishnehadService.findByRadif(bt.getShomare());
                                            if (bt.getShakhsRole().equals("بیمه گذار"))
                                            {
                                                p.getBimeGozar().setShakhs(newShakhs);
                                                pishnehadService.updateBimeGozar(p.getBimeGozar());
//                                                if (changeBimeShodeAddress)
//                                                {
//                                                    p.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

//                                                if (changeBimeGozarAddress)
//                                                {
//                                                    p.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

                                            }
                                            else if (bt.getShakhsRole().equals("بیمه شده"))
                                            {
                                                p.getBimeShode().setShakhs(newShakhs);
                                                pishnehadService.updateBimeShode(p.getBimeShode());
//                                                if (changeBimeShodeAddress)
//                                                {
//                                                    p.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

//                                                if (changeBimeGozarAddress)
//                                                {
//                                                    p.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }
                                            }
                                            else// (bt.getShakhsRole().equals("بیمه شده | بیمه گذار"));
                                            {
                                                p.getBimeShode().setShakhs(newShakhs);
                                                pishnehadService.updateBimeShode(p.getBimeShode());

//                                                if (changeBimeShodeAddress)
//                                                {
//                                                    p.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

//                                                if (changeBimeGozarAddress)
//                                                {
//                                                    p.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

                                                p.getBimeGozar().setShakhs(newShakhs);
                                                pishnehadService.updateBimeGozar(p.getBimeGozar());

//                                                if (changeBimeShodeAddress)
//                                                {
//                                                    p.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }

//                                                if (changeBimeGozarAddress)
//                                                {
//                                                    p.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
//                                                    pishnehadService.updatePishnehad(p);
//                                                }
                                            }
                                        }

                                        else if (el.getOldPishnehad().getId().equals(bt.getBimename().getPishnehad().getId()))
                                        {
                                            if (bt.getShakhsRole().equals("بیمه گذار"))
                                            {
//                                    bt.getBimename().getPishnehad().getBimeGozar().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeGozar(bt.getBimename().getPishnehad().getBimeGozar());
                                                el.getNewPishnehad().getBimeGozar().setShakhs(newShakhs);
                                                pishnehadService.updateBimeGozar(el.getNewPishnehad().getBimeGozar());
                                                if (changeBimeShodeAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                                if (changeBimeGozarAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                            }
                                            else if (bt.getShakhsRole().equals("بیمه شده"))
                                            {
//                                    bt.getBimename().getPishnehad().getBimeShode().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeShode(bt.getBimename().getPishnehad().getBimeShode());
                                                el.getNewPishnehad().getBimeShode().setShakhs(newShakhs);
                                                pishnehadService.updateBimeShode(el.getNewPishnehad().getBimeShode());
                                                if (changeBimeShodeAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                                if (changeBimeGozarAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }
                                            }
                                            else// (bt.getShakhsRole().equals("بیمه شده | بیمه گذار"));
                                            {
//                                    bt.getBimename().getPishnehad().getBimeGozar().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeGozar(bt.getBimename().getPishnehad().getBimeGozar());
//                                    bt.getBimename().getPishnehad().getBimeShode().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeShode(bt.getBimename().getPishnehad().getBimeShode());

                                                el.getNewPishnehad().getBimeShode().setShakhs(newShakhs);
                                                pishnehadService.updateBimeShode(el.getNewPishnehad().getBimeShode());

                                                if (changeBimeShodeAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                                if (changeBimeGozarAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                                el.getNewPishnehad().getBimeGozar().setShakhs(newShakhs);
                                                pishnehadService.updateBimeGozar(el.getNewPishnehad().getBimeGozar());

                                                if (changeBimeShodeAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }

                                                if (changeBimeGozarAddress)
                                                {
                                                    el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                                    pishnehadService.updatePishnehad(el.getNewPishnehad());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        darkhastTaghirat.setArchive("yes");
                        darkhastTaghirat.setFinished(true);
                        darkhastTaghirat.getNewPishnehad().setValid(true);
                        darkhastTaghirat.getOldPishnehad().setValid(false);
                        for (Elhaghiye theElhaghiye : darkhastTaghirat.getDarkhast().getElhaghiyeList())
                        {
                            theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                            darkhastService.updateElhaghiye(theElhaghiye);
                            State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                            theElhaghiye.getBimename().setHasElhaghie("yes");
                            theElhaghiye.getBimename().setState(theState);
                            pishnehadService.updateBimename(theElhaghiye.getBimename());
                            if (theElhaghiye.getOldPishnehad() != null && theElhaghiye.getNewPishnehad() != null)
                            {
                                theElhaghiye.getOldPishnehad().setValid(false);
                                theElhaghiye.getNewPishnehad().setValid(true);
                                pishnehadService.updatePishnehad(theElhaghiye.getNewPishnehad());
                                pishnehadService.updatePishnehad(theElhaghiye.getOldPishnehad());
                            }
                        }
                        State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                        pishnehadService.updatePishnehad(darkhastTaghirat.getNewPishnehad());
                        pishnehadService.updatePishnehad(darkhastTaghirat.getOldPishnehad());
                        Bimename theBimename = darkhastTaghirat.getNewPishnehad().getBimename();
                        darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                        theBimename.setState(theState);
                        theBimename.setHasElhaghie("yes");
                        if (darkhastTaghirat.getWhenApply() == 0)
                        {
                            theBimename.setKarmozdBimename(karmozdService.karmozdBimename(darkhastTaghirat.getNewPishnehad().getBimename(), darkhastTaghirat.getNewPishnehad().getEstelam()));
                        }
                        pishnehadService.updateBimename(theBimename);
                        if(newShakhs.getChangeWithSerach()!=null)
                        {
                            newShakhs.setChangeWithSerach(null);
                            shakhsService.updateShakhs(newShakhs);
                        }
                    }
                    int transId = 0;
                    if (darkhastTaghirat.getState().getId() == 9080) transId = 9021;
                    if (darkhastTaghirat.getState().getId() == 9160) transId = 9046;
                    if (darkhastTaghirat.getState().getId() == 9030) transId = 9053;
                    if (darkhastTaghirat.getState().getId() == 9140) transId = 9039;
                    if (darkhastTaghirat.getState().getId() == 9050) transId = 9015;
                    if (!Constant.DEV_testElhaghieTaghirat) {
                        darkhastService.transitDarkhastTaghirat(user, darkhastTaghirat.getId(), transId, logmessage);
                        for (Elhaghiye theElhaghiye : theDarkhast.getDarkhast().getElhaghiyeList()) {
                            theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                            theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(theElhaghiye.getBimename()));
                            darkhastService.updateElhaghiye(theElhaghiye);
                        }
                        if (credebitListForShomareElhaghie != null) {
                            for (Credebit c : credebitListForShomareElhaghie) {
                                // mesle emal elhaghie baraye 1 elhaghie kar mikonad
                                c.setIdentifier(elhaghiye.getShomareElhaghiye());
                                asnadeSodorService.updateCredebit(c);
                            }
                        }
                    }
                    piishnehadId = darkhastTaghirat.getNewPishnehad().getId();
                    if (darkhastTaghirat.getDarkhast().getElhaghiyeList().size() > 1)
                        return "stayOnSamePlace";
                    else
                        return SUCCESS;
                } catch (Exception ex) {
                    for (Elhaghiye theElhaghiye : theDarkhast.getDarkhast().getElhaghiyeList()) {
                        theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_INITIAL_STATE));
                        darkhastService.updateElhaghiye(theElhaghiye);
                    }
                    throw ex;
                }
            } else if (darkhastBazkharid.getId() != null) {
                DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
//                Elhaghiye theElhaghiye = theDarkhast.getDarkhast().getElhaghiye();
                if (tozih) {
                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                    Elhaghiye theElhaghiye = new Elhaghiye();
                    bimename = darkhastBazkharid.getBimename();
                    piishnehadId = bimename.getPishnehad().getId();
                    theElhaghiye.setId(elhaghiye.getId());
                    theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                    theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(bimename));
                    theElhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TOZIH);
                    theElhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
                    theElhaghiye.setBimename(darkhastBazkharid.getBimename());
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                    theElhaghiye.setMablagh(elhaghiye.getMablagh());
                    theElhaghiye.setMatn(elhaghiye.getMatn());
                    theElhaghiye.setMozoo(elhaghiye.getMozoo());
                    theElhaghiye.setEmzaKonandeAval(elhaghiye.getEmzaKonandeAval());
                    theElhaghiye.setEmzaKonandeDovom(elhaghiye.getEmzaKonandeDovom());
                    theElhaghiye.setDarkhastBazkharid(darkhastBazkharid);
                    theElhaghiye.setDarkhast(darkhastBazkharid.getDarkhast());
                    theElhaghiye.setState(constantItemsService.findStateById(3001));
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());
                    darkhastBazkharid.setElhaghie(theElhaghiye);
                    darkhastBazkharid.setFinished(true);
                    darkhastBazkharid.getDarkhast().setFinished(true);
                    pishnehadService.updateBimename(bimename);
                    darkhastService.updateElhaghiye(theElhaghiye);
//                    darkhastBazkharid.getBimename().setState(constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE));
//                    darkhastBazkharid.getBimename().setHasElhaghie("yes");

                    String darkhastSequence = sequenceService.nextShomareDarkhastBazkharid();
                    String finalDarkhastShomare = bimename.getShomare();
                    finalDarkhastShomare += "-" + darkhastSequence;
                    darkhastBazkharid.setShomareDarkhast(finalDarkhastShomare);
                    darkhastBazkharid.setDarkhastType(DarkhastBazkharid.DarkhastType.TOZIH);
                    darkhastBazkharid.setNamayande(bimename.getPishnehad().getNamayande());
                    darkhastBazkharid.setCreator(user);
                    int bazkharidId = darkhastService.saveDarkhastTozih(darkhastBazkharid);
                    DarkhastBazkharid theBazkharid = darkhastService.findDarkhastBazkharidById(bazkharidId);
//                    theZamime.setDarkhastBazkharid(theBazkharid);
//                    darkhastService.updateZamaem(theZamime);
                    bimename.setDarkhastBazkharid(theBazkharid);
                    bimename.setHasElhaghie("yes");
                    pishnehadService.updateBimename(bimename);
//                    setActionMessages(Collections.singleton("صدور الحاقيه با موفقيت انجام شد."));

                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.TAGHIRKOD)) {
                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                    Elhaghiye theElhaghiye = new Elhaghiye();
                    theElhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHIRKOD);
                    theElhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
                    theElhaghiye.setBimename(darkhastBazkharid.getBimename());
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
//                    theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                    if (elhaghiye.getEmzaKonandeAval().getId() != null) {
                        theElhaghiye.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                    }
                    if (elhaghiye.getEmzaKonandeDovom().getId() != null) {
                        theElhaghiye.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
                    }
                    theElhaghiye.setMatn(elhaghiye.getMatn());
                    theElhaghiye.setMozoo(elhaghiye.getMozoo());
                    theElhaghiye.setTarikhAsar(elhaghiye.getTarikhAsar());
                    theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                    bimename = darkhastBazkharid.getBimename();
                    theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(bimename));
                    theElhaghiye.setMablagh(elhaghiye.getMablagh());
                    theElhaghiye.setState(constantItemsService.findStateById(3001));
                    theElhaghiye.setDarkhastBazkharid(darkhastBazkharid);
                    theElhaghiye.setDarkhast(darkhastBazkharid.getDarkhast());
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());

                    List<Pishnehad> pList=new ArrayList<Pishnehad>();
                    if(bimename.getPishnehad().getOptions()!=null&&bimename.getPishnehad().getOptions().equals("CODE_MOVAGHAT"))// movaghat be daem
                    {
                        bimename.getPishnehad().setNamayandePoshtiban(bimename.getPishnehad().getNamayande());
                        if(bimename.getPishnehadList().size()>1)
                        {
                            for(Pishnehad p :bimename.getPishnehadList())
                            {
                                if(p.getId()!=bimename.getPishnehad().getId())
                                {
                                    p.setNamayandePoshtiban(bimename.getPishnehad().getNamayande());
                                    p.setOptions(null);
                                    p.setNamayande(darkhastBazkharid.getNamayande());
                                    User userBazar = loginService.findUserByUsername(bazarYabUserName);
                                    p.setBazarYab(userBazar);
//                                    pishnehadService.saveOrUpdate(p);
                                    pList.add(p);
                                }
                            }
                        }
                    }
                    else//daem be daem
                    {
                        bimename.getPishnehad().setPreviousNamayande(bimename.getPishnehad().getNamayande());
                        if (bimename.getPishnehadList().size() > 1)
                        {
                            for (Pishnehad p : bimename.getPishnehadList())
                            {
                                if (p.getId() != bimename.getPishnehad().getId())
                                {
                                    p.setPreviousNamayande(bimename.getPishnehad().getNamayande());
                                    p.setOptions(null);
                                    p.setNamayande(darkhastBazkharid.getNamayande());
                                    User userBazar = loginService.findUserByUsername(bazarYabUserName);
                                    p.setBazarYab(userBazar);
//                                    pishnehadService.updatePishnehad(p);
                                    pList.add(p);
                                }
                            }
                        }
                        for(GhestBandi gb : bimename.getGhestBandiListOfBimename())
                        {
                            for(Ghest g :gb.getGhestList())
                            {
                                Set<KarmozdGhest> karmozdGhestSet = g.getkarmozdSet();
                                if (karmozdGhestSet == null)
                                    continue;
                                Iterator<KarmozdGhest> karmozdGhestIterator = karmozdGhestSet.iterator();
                                while (karmozdGhestIterator.hasNext())
                                {
                                    KarmozdGhest karmozdGhest = karmozdGhestIterator.next();
                                    String desc = "ناشی از الحاقیه تغییر کد دائم به دائم";
                                    KarmozdTadilat tadilat = new KarmozdTadilat(karmozdGhest.getKarmozdNamayande().getNamayande().getKodeNamayandeKargozar(), (Long.toString(karmozdGhest.getKarmozdAmount() * -1)), bimename.getShomare(), desc, desc, karmozdGhest);
                                    karmozdService.saveTadilat(tadilat);
                                }
                            }
                        }
                    }
                    if (pList.size()>0)
                    {
                        for(Pishnehad p:pList)
                        {
                            pishnehadService.updatePishnehad(p);
                        }
                    }
                    darkhastService.saveElhaghiye(theElhaghiye);
                    darkhastBazkharid.setElhaghie(theElhaghiye);
                    bimename.setState(constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE));
                    bimename.setHasElhaghie("yes");
                    pishnehadService.updateBimename(bimename);
                    bimename.getPishnehad().setOptions(null);
                    bimename.getPishnehad().setNamayande(darkhastBazkharid.getNamayande());
                    User userBazar = loginService.findUserByUsername(bazarYabUserName);
                    bimename.getPishnehad().setBazarYab(userBazar);
                    pishnehadService.updatePishnehad(bimename.getPishnehad());

                    darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                    elhaghiye = theElhaghiye;
                    piishnehadId = bimename.getPishnehad().getId();
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BAZKHARID)) {
                    Elhaghiye theElhaghiye = theDarkhast.getElhaghie()==null?new Elhaghiye():theDarkhast.getElhaghie();
                    theElhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.BAZKHARID);
                    theElhaghiye.setBimename(theDarkhast.getBimename());
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                    if (elhaghiye.getEmzaKonandeAval().getId() != null) {
                        theElhaghiye.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                    }
                    if (elhaghiye.getEmzaKonandeDovom().getId() != null) {
                        theElhaghiye.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
                    }
                    theElhaghiye.setMatn(elhaghiye.getMatn());
                    theElhaghiye.setMozoo(elhaghiye.getMozoo());
                    theElhaghiye.setTarikhAsar(elhaghiye.getTarikhAsar());
                    theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                    theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(theElhaghiye.getBimename()));
//                    theElhaghiye.setMablagh(elhaghiye.getMablagh());
                    darkhastService.saveElhaghiye(theElhaghiye);
                    theDarkhast.setElhaghie(theElhaghiye);
                    CredebitAction.mohasebeAndukhteBimename(theDarkhast.getBimename(), theElhaghiye.getTarikhAsar(),theDarkhast.getBimename().getCashFlow(theElhaghiye.getTarikhAsar()));
                    theDarkhast.setArzeshBazkharid(NumberFormat.getNumberInstance().format(theDarkhast.getBimename().getArzeshBazkharidGhatie()));
                    theDarkhast.setAndukhteGhatie(NumberFormat.getNumberInstance().format(theDarkhast.getBimename().getAndukhteyeGhatie()));
                    if (theDarkhast.getKarshenas() == null)
                        theDarkhast.setKarshenas(user);
                    darkhastService.updateDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhast(theDarkhast.getDarkhast());
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());

                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(theDarkhast.getId());
                    elhaghiye = darkhastBazkharid.getDarkhast().getElhaghiye();
                    piishnehadId = darkhastBazkharid.getBimename().getPishnehad().getId();
                    bimename = darkhastBazkharid.getBimename();
                    List<GhestBandi> gbList = bimename.getGhestBandiList();
                    long mablagh = 0l;
                    long paymentAmount = 0l;
                    for (GhestBandi gb : gbList) {
                        if(gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                            for (Ghest g : gb.getGhestList()) {
                                paymentAmount += g.getCredebit().getAmount_long() - g.getCredebit().getRemainingAmount_long();
                                mablagh += g.getCredebit().getAmount_long();
                            }
                    }
                    theElhaghiye.setMablagh(Long.toString((mablagh - paymentAmount)*(-1)));
                    revertGhestsProcess(true,true);
                    theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                    darkhastService.updateElhaghiye(theElhaghiye);
                    int transId = 0;
                    if (darkhastBazkharid.getState().getId() == 1100) transId = 1270;
                    if (darkhastBazkharid.getState().getId() == 1200) transId = 1200;
                    if (darkhastBazkharid.getState().getId() == 1450) transId = 1330;
                    if (darkhastBazkharid.getState().getId() == 1020) transId = 1165;
                    darkhastService.transitDarkhast(user, darkhastBazkharid.getId(), transId, logmessage);
                    bimename.setState(constantItemsService.findStateById(550));
                    pishnehadService.updateBimename(bimename);
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT)) {
                    Elhaghiye theElhaghiye = theDarkhast.getCurrentKhesarat().getElhaghiye()!=null? theDarkhast.getCurrentKhesarat().getElhaghiye():new Elhaghiye();
                    theElhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.KHESARAT);
                    theElhaghiye.setBimename(theDarkhast.getBimename());
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                    theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                    if (elhaghiye.getEmzaKonandeAval().getId() != null) {
                        theElhaghiye.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                    }
                    if (elhaghiye.getEmzaKonandeDovom().getId() != null) {
                        theElhaghiye.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
                    }
                    theElhaghiye.setMatn(elhaghiye.getMatn());
                    theElhaghiye.setMozoo(elhaghiye.getMozoo());
                    theElhaghiye.setTarikhAsar(elhaghiye.getTarikhAsar());
                    theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                    theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(theElhaghiye.getBimename()));
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());
                    Khesarat theKhesarat = theDarkhast.getCurrentKhesarat();
                    long amount = 0;
//                    if (theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.OMR) || theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.HADESE))
//                    {
//                        List<GhestBandi> gbList= theDarkhast.getBimename().getGhestBandiList();
//                        for (GhestBandi gb : gbList)
//                        {
//                            if(gb.getType().equals(GhestBandi.Type.G_BIMENAME))
//                                for (Ghest g : gb.getGhestList())
//                                {
//                                    if (g.getTarikhPardakht()==null || g.getTarikhPardakht().isEmpty())
//                                        amount+=g.getCredebit().getRemainingAmount_long();
//                                }
//                        }
//                    }
                    theElhaghiye.setMablagh("-" + String.valueOf(theKhesarat.getKoleHaghBimeField()));
                    theElhaghiye.setKhesarat(theKhesarat);
                    theElhaghiye.setDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhast(theDarkhast.getDarkhast());
                    darkhastService.saveElhaghiye(theElhaghiye);
                    theKhesarat.setElhaghiye(theElhaghiye);
                    khesaratService.updateKhesarat(theKhesarat);
                    theDarkhast.setElhaghie(theElhaghiye);
                    if (theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ) || theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                    {
                        amount = ((Double)asnadeSodorService.AmrazOrNaghsKhesaratProcesses(theDarkhast)).longValue();
                    }


                    darkhastService.updateDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhast(theDarkhast.getDarkhast());

                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(theDarkhast.getId());
                    if ((darkhastBazkharid.getKhesaratII() != null && darkhastBazkharid.getKhesaratII().getElhaghiye() != null) || darkhastBazkharid.getKhesaratII() == null) {
                        //todo: should call prepareObjectsForElhaghieForm();
                    }
                    piishnehadId = darkhastBazkharid.getBimename().getPishnehad().getId();
                    bimename = darkhastBazkharid.getBimename();

                    darkhastService.updateElhaghiye(theElhaghiye);
                    if(theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.OMR)|| theKhesarat.getKhesaratType().equals(Khesarat.KhesaratType.HADESE))
                    {
                        CredebitAction.mohasebeAndukhteBimename(bimename, theElhaghiye.getTarikhAsar(),bimename.getCashFlow(theElhaghiye.getTarikhAsar()));
                        darkhastBazkharid.setAndukhteGhatie(bimename.getAndukhteyeGhatie().toString());
                        darkhastBazkharid.setArzeshBazkharid(bimename.getArzeshBazkharidGhatie().toString());
                        darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                        revertGhestsProcess(true, false);
                        if(darkhastBazkharid.isBimenameClosed())
                        {
                            State theState = constantItemsService.findStateById(Constant.BIMENAME_BASTE);
                            bimename.setState(theState);
                            pishnehadService.updateBimename(bimename);
                        }
                    }
                    return "stayOnSamePlaceKhesarat";
                } else {
                    Elhaghiye theElhaghiye = new Elhaghiye();
                    theElhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.EBTAL);
                    theElhaghiye.setBimename(theDarkhast.getBimename());
                    theElhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                    theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                    theElhaghiye.setUserSaderKonande(user);
                    theElhaghiye.setVahedSodor(user.getMojtamaSodoor());
                    if (elhaghiye.getEmzaKonandeAval().getId() != null) {
                        theElhaghiye.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                    }
                    if (elhaghiye.getEmzaKonandeDovom().getId() != null) {
                        theElhaghiye.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
                    }
                    theElhaghiye.setMatn(elhaghiye.getMatn());
                    theElhaghiye.setMozoo(elhaghiye.getMozoo());
                    theElhaghiye.setTarikhAsar(elhaghiye.getTarikhAsar());
                    theElhaghiye.setRadifElhaghiye(sequenceService.nextShomareRadifElhaghie());
                    theElhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(theElhaghiye.getBimename()));
                    theElhaghiye.setMablagh("-" + theDarkhast.getBimename().getJameElamBeMali());
                    theElhaghiye.setHazinePezeshki(elhaghiye.getHazinePezeshki());
                    theElhaghiye.setMablaghPardakhtiBimegozar(elhaghiye.getMablaghPardakhtiBimegozar());
                    theElhaghiye.setDarkhastBazkharid(theDarkhast);
                    theElhaghiye.setDarkhast(theDarkhast.getDarkhast());
                    darkhastService.saveElhaghiye(theElhaghiye);
                    theDarkhast.setElhaghie(theElhaghiye);
                    if (theDarkhast.getKarshenas() == null)
                        theDarkhast.setKarshenas(user);
                    darkhastService.updateDarkhastBazkharid(theDarkhast);
                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(theDarkhast.getId());
                    bimename = darkhastBazkharid.getBimename();
                    elhaghiye = theElhaghiye;
                    piishnehadId = darkhastBazkharid.getBimename().getPishnehad().getId();
                    revertGhestsProcess(false, true);
                    int transId = 0;
                    if (darkhastBazkharid.getState().getId() == 1100) transId = 1270;
                    if (darkhastBazkharid.getState().getId() == 1200) transId = 1200;
                    if (darkhastBazkharid.getState().getId() == 1450) transId = 1330;
                    if (darkhastBazkharid.getState().getId() == 1020) transId = 1165;
                    darkhastService.transitDarkhast(user, darkhastBazkharid.getId(), transId, logmessage);
                    darkhastService.updateBimenameStateEbtal(bimename);
                }
            }
        }
        return SUCCESS;
    }

    private void revertGhestsProcess(boolean bazkharid,boolean isACH) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null) {
            user = loginService.findUserByUsername(username);
        }
        long amount = 0;
        long remainingAmount = 0;
        long paymentAmount = 0;
//        List<GhestBandi> gbList = bimename.getGhestBandiList();
        List<GhestBandi> gbList = bimename.getGhestBandiListOfBimename();
        List<Ghest> ghestList = new ArrayList<Ghest>();
        List<Ghest> ghestsWithoutKhateSanad = new ArrayList<Ghest>();
        for (GhestBandi gb : gbList) {
            for (Ghest g : gb.getGhestList()) {
                if (!g.getTarikhPardakht().isEmpty()) {
                    amount += g.getCredebit().getAmount_long();
                    remainingAmount += g.getCredebit().getRemainingAmount_long();
                    ghestList.add(g);
                } else {
                    ghestsWithoutKhateSanad.add(g);
                }

            }
        }
        Credebit credebit;
        String identifier = sequenceService.nextShenaseCredebit();
        String reason = "";
        if(bazkharid) {
            reason = "بازخرید بیمه نامه " + bimename.getShomare();
        } else {
            reason = "ابطال بیمه نامه " + bimename.getShomare();
        }

        if (bazkharid) {
//            credebit = new Credebit(Long.toString(remainingAmount), identifier, bimename, bimename.getPishnehad(), Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR);
            credebit = new Credebit(Long.toString(remainingAmount), identifier, bimename, bimename.getPishnehad(), Credebit.CredebitType.ETEBAR_EBTAL);
            //credebit.setIdentifier(elhaghiye.getShomareElhaghiye());
            asnadeSodorService.saveCredebit(credebit);
            paymentAmount = bimename.getArzeshBazkharidGhatie();
        } else {
            credebit = new Credebit(Long.toString(amount), identifier, bimename, bimename.getPishnehad(), Credebit.CredebitType.ETEBAR_EBTAL);
            credebit.setIdentifier(elhaghiye.getShomareElhaghiye());
            asnadeSodorService.saveCredebit(credebit);
            if (elhaghiye != null && elhaghiye.getHazinePezeshki() != null && !elhaghiye.getHazinePezeshki().equals("0")) {
                if (!elhaghiye.getHazinePezeshki().equals("0")) {
                    Credebit credebitPezeshki = new Credebit(elhaghiye.getHazinePezeshki(), sequenceService.nextShenaseCredebit(), bimename, bimename.getPishnehad(), Credebit.CredebitType.HAZINE_PEZESHKI);
                    //credebitPezeshki.setIdentifier(elhaghiye.getShomareElhaghiye());
                    asnadeSodorService.saveCredebit(credebitPezeshki);
                    asnadeSodorService.sabteSanad(user, credebitPezeshki, credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
                }
            }
            if (elhaghiye != null && elhaghiye.getHazinePezeshki() != null)
                paymentAmount = amount - Long.parseLong(elhaghiye.getHazinePezeshki().replaceAll(",", ""));
        }
        for (Ghest g : ghestList) {
            if (g.getCredebit() != null && g.getCredebit().getRemainingAmount_long() > 0) {
                if (!bazkharid)//This line added for ticket[32708]
                    paymentAmount -= g.getCredebit().getRemainingAmount_long();

                asnadeSodorService.sabteSanad(user, g.getCredebit(), credebit, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
            }
        }

        for (Ghest g : ghestsWithoutKhateSanad) {
            Credebit ghestCredebit = new Credebit(Long.toString(-1 * g.getMajmooHazineha()), sequenceService.nextShenaseCredebit(), bimename, null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
            //ghestCredebit.setIdentifier(elhaghiye.getShomareElhaghiye());
            ghestCredebit.setDateFish(g.getSarresidDate());
            asnadeSodorService.saveCredebit(ghestCredebit);
            asnadeSodorService.hazfeGhest(g.getId());
        }
        if (bazkharid && isACH) {
            Credebit credebitBazkharid = new Credebit(Long.toString(bimename.getArzeshBazkharidGhatie()), sequenceService.nextShenaseCredebit(), bimename, bimename.getPishnehad(), Credebit.CredebitType.AZ_MAHALLE_BAZKHARID);
            //credebitBazkharid.setIdentifier(elhaghiye.getShomareElhaghiye());
            credebit = credebitBazkharid;
            asnadeSodorService.saveCredebit(credebitBazkharid);
        }
        // ach payment
        if (isACH && paymentAmount > 0 && paymentAmount <= 200000000 && darkhastBazkharid.getShomareShaba().length() > 10) {
            String response = ACHPayment.achPaymentRequest(identifier, (int) paymentAmount, reason,
                    darkhastBazkharid.getSahebHesab(), darkhastBazkharid.getShomareShaba(),
                    bimename.getPishnehad().getBimeGozar().getShakhs().getShomareShenasnameh(), bimename.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi(),
                    user.getUsername(), bimename.getPishnehad().getAddressBimeGozar().getTelephoneHamrah(), "111116");
            if (!response.contains("-")) {
                Credebit credebitACH = new Credebit(Long.toString(paymentAmount), sequenceService.nextShenaseCredebit(), bimename, bimename.getPishnehad(), Credebit.CredebitType.ACH);
                credebitACH.setRahgiriACH(response);
                asnadeSodorService.saveCredebit(credebitACH);
                asnadeSodorService.sabteSanad(user, credebitACH, credebit, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
            } else {
                final org.slf4j.Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
                logger.info(String.format("ACH Failed ! credebit identifier: %s bimename.shomare: %s ach response: %s"
                        , identifier, reason, response));
            }
        }
    }


    /*private void emalElhaghiye() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        // this method is now obsolete, this is the old way (ati & jari) for elhaghie taghirat
        pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        user = loginService.findUserByUsername(username);
        option_changes = false;
        boolean onlyHaghBimeAvvalie = false;
        boolean hasHaghBimeAvvalie = false;
        int numOption = 0;
        for (PishnehadFieldChanges p : pishnehadFieldChangesList) {
            if (p.isOption()) {
                option_changes = true;
                numOption++;
                if (p.getSubject().equals("حق بیمه اولیه"))
                    hasHaghBimeAvvalie = true;
            }
        }
        if (hasHaghBimeAvvalie && numOption == 1)
            onlyHaghBimeAvvalie = true;
        if (option_changes) {
            String amountElhaghiye = elhaghiye.getMablagh();
            if (!amountElhaghiye.equals("0")) {
                Credebit credebit = new Credebit(amountElhaghiye, sequenceService.nextShenaseCredebit(), darkhastTaghirat.getOldPishnehad().getBimename(), null, Credebit.CredebitType.ELHAGHIE_BARGASHTI);
                if (amountElhaghiye.indexOf('-') < 0) {
                    credebit.setCredebitType(Credebit.CredebitType.ELHAGHIE_EZAFI);
                }
                if (!Constant.DEV_testElhaghieTaghirat)
                    asnadeSodorService.saveCredebit(credebit);
            }
            int saleBimeiElhaghie = Integer.MAX_VALUE;
            List<CredebitForLogGhest> cflgList = new LinkedList<CredebitForLogGhest>();
            List<GhestBandi> gbList = new ArrayList<GhestBandi>();
            LogGhest logGhest = new LogGhest(new Date().getTime(), darkhastTaghirat);
            if (darkhastTaghirat.getWhenApply() == 1) {//ati
                Integer currentSal = darkhastTaghirat.getNewPishnehad().getBimename().getCurrentSaleBimei();
                if (currentSal != null) {
                    for (GhestBandi gb : darkhastTaghirat.getNewPishnehad().getBimename().getGhestBandiList()) {
                        if (gb.getSaleBimeiInt().equals(currentSal + 1)) {
                            // todo: farz mikonim ke hich sanadi to sale ati nakhorde bashe
                            saleBimeiElhaghie = gb.getSaleBimeiInt();
                            for (Ghest g : gb.getGhestList()) {
                                CredebitForLogGhest cflg = new CredebitForLogGhest(DateUtil.getCurrentDate(), g.getSarresidDate(), g.getCredebit().getRemainingAmount(), g.getCredebit().getShomareMoshtari(), g.getCredebit().getShenaseCredebit(), logGhest, false);
                                cflgList.add(cflg);
                            }
                            if (!Constant.DEV_testElhaghieTaghirat) {
                                asnadeSodorService.deleteGhestBandiById(gb);
                                if (user != null)
                                    asnadeSodorService.saveGhestbandi(darkhastTaghirat.getNewPishnehad(), gb.getSaleBimeiInt(), user);
                            }
                            GhestBandi newGb = asnadeSodorService.findGhestBandiById(gb.getId());
                            for (Ghest g : newGb.getGhestList()) {
                                CredebitForLogGhest cflg = new CredebitForLogGhest(DateUtil.getCurrentDate(), g.getSarresidDate(), g.getCredebit().getAmount(), g.getCredebit().getShomareMoshtari(), g.getCredebit().getShenaseCredebit(), logGhest, true);
                                cflgList.add(cflg);
                            }
                        }
                    }
                }
            } else if (darkhastTaghirat.getWhenApply() == 2) {//jari
                Integer currentSal = darkhastTaghirat.getNewPishnehad().getBimename().getCurrentSaleBimei();
                if (currentSal != null) {
                    List<Integer> gbIdList = new ArrayList<Integer>();
                    for (GhestBandi gb : darkhastTaghirat.getNewPishnehad().getBimename().getGhestBandiList()) {
                        if (currentSal.equals(gb.getSaleBimeiInt())) {
                            saleBimeiElhaghie = gb.getSaleBimeiInt();
                            double etebar = 0.0;
                            List<TaghsitReport> taghsitReportJadid = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), currentSal, darkhastTaghirat.getNewPishnehad().getBimename().getTarikhShorou(), true);
                            List<TaghsitReport> taghsitReportJadidDuplicate = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), currentSal, darkhastTaghirat.getNewPishnehad().getBimename().getTarikhShorou(), true);
                            String yekRuzBad = DateUtil.addDays(elhaghiye.getCreatedDate(), 1);
                            List<Integer> deleteList = new ArrayList<Integer>();
                            List<Ghest> ghestList = new ArrayList<Ghest>();
                            List<Credebit> credebitList = new ArrayList<Credebit>();
                            LinkedList<Flow> flowsHazineha = new LinkedList<Flow>();
                            LinkedList<String> tarikhhayeAghsatPardakhti = new LinkedList<String>();
                            for (Ghest g : gb.getGhestList()) {
                                CredebitForLogGhest cflg = new CredebitForLogGhest(DateUtil.getCurrentDate(), g.getSarresidDate(), g.getCredebit().getRemainingAmount(), g.getCredebit().getShomareMoshtari(), g.getCredebit().getShenaseCredebit(), logGhest, false);
                                cflgList.add(cflg);
                                if (g.getTarikhPardakht() != null && !g.getTarikhPardakht().isEmpty()) {
                                    etebar += Long.parseLong(g.getCredebit().getAmount().replace(",", "").trim());
                                    tarikhhayeAghsatPardakhti.add(g.getSarresidDate());
                                    Flow flow = new Flow(g.getMajmooHazineha(), Flow.Type.HAZINEHA_MOSBAT, g.getSarresidDate());
                                    flowsHazineha.add(flow);
                                } else {
                                    deleteList.add(g.getId());
                                }
                            }
                            String lastDateEdited = "1200/01/01";
                            double mablaghePardakhtiGhestEditShodeAkhar = 0;
                            // hazfe aghsat baraye negah dashtane pardakht shodeha
                            if (etebar > 0) {
                                if ((int) Math.floor(etebar / taghsitReportJadidDuplicate.get(0).getGhestAmount()) > 0) {
                                    boolean iterate = true;
                                    while (iterate) {
                                        boolean found = false;
                                        TaghsitReport removeCandidate = null;
                                        if (!tarikhhayeAghsatPardakhti.isEmpty()) {
                                            for (TaghsitReport tr : taghsitReportJadid) {
                                                if (tr.getTarikh().equals(tarikhhayeAghsatPardakhti.get(0))) {
                                                    removeCandidate = tr;
                                                    found = true;
                                                }
                                            }
                                        }
                                        if (!found) {
                                            // for etebar == 0
                                            lastDateEdited = taghsitReportJadid.get(0).getTarikh();
                                            mablaghePardakhtiGhestEditShodeAkhar = taghsitReportJadid.get(0).getGhestAmount();
                                            // --
                                            if (etebar >= taghsitReportJadid.get(0).getGhestAmount()) {
                                                etebar = etebar - taghsitReportJadid.get(0).getGhestAmount();
                                                taghsitReportJadid.remove(0);
                                            } else {
                                                iterate = false;
                                            }
                                        } else {
                                            // for etebar == 0
                                            lastDateEdited = removeCandidate.getTarikh();
                                            mablaghePardakhtiGhestEditShodeAkhar = removeCandidate.getGhestAmount();
                                            // --
                                            if (etebar >= removeCandidate.getGhestAmount()) {
                                                etebar = etebar - removeCandidate.getGhestAmount();
                                                taghsitReportJadid.remove(removeCandidate);
                                            } else {
                                                iterate = false;
                                            }
                                        }
                                        if (etebar == 0) iterate = false;
                                        if (!tarikhhayeAghsatPardakhti.isEmpty()) tarikhhayeAghsatPardakhti.remove(0);
                                    }
                                    if (etebar > 0) {  //bargashti
                                        lastDateEdited = taghsitReportJadid.get(0).getTarikh();
                                        // agar nahve pardakht taghir nakone, bayad in ghest edit shode ro biarim jelo be tarikhe elhaghie
                                        if (taghsitReportJadidDuplicate.size() == gb.getGhestList().size()
                                                && DateUtil.isGreaterThan(elhaghiye.getCreatedDate(), taghsitReportJadid.get(0).getTarikh())) {
                                            taghsitReportJadid.get(0).setTarikh(elhaghiye.getCreatedDate());
                                        }
                                        taghsitReportJadid.get(0).setGhestAmount((long) (taghsitReportJadid.get(0).getGhestAmount() - etebar));
                                        taghsitReportJadid.get(0).setCreatedByElhaghie(true);
                                        mablaghePardakhtiGhestEditShodeAkhar = etebar;
                                    }
                                } else if (etebar > 0) {
                                    // be nazar hamun kare balayi, baraye etebar < ghest amount
                                    int sumHazfShode = 0;
                                    while (tarikhhayeAghsatPardakhti.size() > 0) {
                                        boolean found = false;
                                        TaghsitReport removeCandidate = null;
                                        if (!tarikhhayeAghsatPardakhti.isEmpty()) {
                                            for (TaghsitReport tr : taghsitReportJadid) {
                                                if (tr.getTarikh().equals(tarikhhayeAghsatPardakhti.get(0))) {
                                                    removeCandidate = tr;
                                                    found = true;
                                                }
                                            }
                                        }
                                        if (!found) {
                                            sumHazfShode += taghsitReportJadid.get(0).getGhestAmount();
                                            taghsitReportJadid.remove(0);
                                        } else {
                                            sumHazfShode += removeCandidate.getGhestAmount();
                                            taghsitReportJadid.remove(removeCandidate);
                                        }
                                        if (!tarikhhayeAghsatPardakhti.isEmpty()) tarikhhayeAghsatPardakhti.remove(0);
                                    }
                                    if (sumHazfShode == 0) {
                                        sumHazfShode += taghsitReportJadid.get(0).getGhestAmount();
                                        taghsitReportJadid.remove(0);
                                    }
                                    etebar = Math.abs(etebar - sumHazfShode);
                                    TaghsitReport tr = new TaghsitReport();
                                    tr.setTarikh(elhaghiye.getCreatedDate());
                                    tr.setSarmaayeFotBehHarEllat(taghsitReportJadidDuplicate.get(0).getSarmaayeFotBehHarEllat());
                                    tr.setSarmaayeFotDarAsarHaadeseh(taghsitReportJadidDuplicate.get(0).getSarmaayeFotDarAsarHaadeseh());
                                    tr.setSarmaayePusheshEzaafiAmraazKhaas(taghsitReportJadidDuplicate.get(0).getSarmaayePusheshEzaafiAmraazKhaas());
                                    tr.setZaribNahveyePardakht(taghsitReportJadidDuplicate.get(0).getZaribNahveyePardakht());
                                    tr.setGhestAmount(OmrUtil.rondPardakhti((int) etebar));
                                    tr.setCreatedByElhaghie(true);
                                    taghsitReportJadid.add(tr);
                                }
                            }
                            String upToDate = yekRuzBad;
                            if (DateUtil.isGreaterThan(lastDateEdited, upToDate)) upToDate = lastDateEdited;
                            for (TaghsitReport tr : taghsitReportJadid) {
                                if (DateUtil.isGreaterThanOrEqual(upToDate, tr.getTarikh())) {
                                    if (tr.getGhestAmount() > 0) {
                                        if (DateUtil.isGreaterThanOrEqual(tr.getTarikh(), yekRuzBad)) {
                                            nesbatHazineha(taghsitReportJadidDuplicate.get(0).convertToGhest(), tr, taghsitReportJadidDuplicate.get(0).getGhestAmount());
                                        } else {
                                            nesbatHazineha(gb.getGhestList().get(0), tr, gb.getGhestList().get(0).getCredebit().getAmount_long());
                                            Flow flow = new Flow(tr.getMajmooHazineha(), Flow.Type.HAZINEHA_MOSBAT, tr.getTarikh());
                                            flowsHazineha.add(flow);
                                        }
                                    }
                                }
                            }
                            for (TaghsitReport tr : taghsitReportJadidDuplicate) {
                                if (DateUtil.isGreaterThan(upToDate, tr.getTarikh())) {
                                    Flow flow = new Flow(tr.getMajmooHazineha(), Flow.Type.HAZINEHA_MANFI, tr.getTarikh());
                                    flowsHazineha.add(flow);
                                }
                            }
                            if (mablaghePardakhtiGhestEditShodeAkhar > 0 && DateUtil.isGreaterThanOrEqual(lastDateEdited, yekRuzBad)) {
                                flowsHazineha.get(flowsHazineha.size() - 1).setAmount((flowsHazineha.get(flowsHazineha.size() - 1).getAmount() * mablaghePardakhtiGhestEditShodeAkhar) / taghsitReportJadidDuplicate.get(0).getGhestAmount());
                            }
                            CashFlow hesabHazineha = new CashFlow();
                            hesabHazineha.setFlows(flowsHazineha);
                            double hazinehaMaBe = hesabHazineha.tajmi(null, yekRuzBad, 1.15);
                            System.out.println("--- CASHFLOW HAZINEHA " + darkhastTaghirat.getNewPishnehad().getBimename().getShomare() + " ---");
                            for (Flow f : flowsHazineha) {
                                System.out.println("D: " + f.getDate() + " T: " + f.getType().toString() + " A: " + f.getAmount());
                            }
                            System.out.println("-----------------------------");
                            if (hazinehaMaBe != 0) {
                                Credebit maBeHazineha = new Credebit(Long.toString(Math.round(hazinehaMaBe)), sequenceService.nextShenaseCredebit(), darkhastTaghirat.getNewPishnehad().getBimename(), null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                                maBeHazineha.setDateFish(yekRuzBad);
                                credebitList.add(maBeHazineha);
                            }
                            System.out.println("--- AGHSATE TOLID SHODE " + darkhastTaghirat.getNewPishnehad().getBimename().getShomare() + " ---");
                            credebitListForShomareElhaghie = new ArrayList<Credebit>();
                            for (TaghsitReport tr : taghsitReportJadid) {
                                if (tr.getGhestAmount() > 0) {
                                    Ghest ghest = new Ghest();
                                    ghest.setGhestBandi(gb);
                                    ghest.setSarresidDate(tr.getTarikh());
                                    ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimeKhaalesFotYekja())));
                                    ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimePusheshHaayeEzaafi())));
                                    ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimePoosheshAmraz())));
                                    ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimePoosheshHadese())));
                                    ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimePoosheshNaghsOzv())));
                                    ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(tr.getHaghBimePoosheshMoafiat())));
                                    ghest.setMaliat(NumberFormat.getNumberInstance().format(Math.round(tr.getMaliat())));
                                    ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(tr.getHazineBimeGari())));
                                    ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(tr.getHazineEdari())));
                                    ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(tr.getKaarmozd())));
                                    ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(tr.getHazineVosul())));
                                    ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(tr.getSarmaayeFotBehHarEllat())));
                                    ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(tr.getSarmaayeFotDarAsarHaadeseh())));
                                    ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(tr.getSarmaayePusheshEzaafiAmraazKhaas())));
                                    ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(tr.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(tr.getZaribNahveyePardakht()).length())));
                                    ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(tr.getKaarmozd()));

                                    Credebit.CredebitType type;
//                                if(Math.round(tr.getGhestAmount()) == 0) {
//                                    type = Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT;
//                                } else {
//                                    type = Credebit.CredebitType.GHEST;
//                                }
                                    type = Credebit.CredebitType.GHEST;
                                    Credebit credebit = new Credebit(Long.toString(Math.round(tr.getGhestAmount())), sequenceService.nextShenaseCredebit(), darkhastTaghirat.getNewPishnehad().getBimename(), darkhastTaghirat.getNewPishnehad(), type);
                                    credebit.setDateFish(ghest.getSarresidDate());
                                    credebit.setGhest(ghest);
                                    credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(darkhastTaghirat.getNewPishnehad().getBimename().getFirstPishnehad().getKarshenas().getMojtamaSodoor(), "9", "110"));
                                    ghest.setCredebit(credebit);
                                    ghest.setKarmozdVosuli(asnadeSodorService.getKarmozdVosul(darkhastTaghirat.getNewPishnehad().getBimename().getTarikhSodour(), darkhastTaghirat.getNewPishnehad().getTarh(), credebit.getAmount_long(), ghest.getMaliatLong(), ghest.getHaghBimePoosheshhayeEzafiLong(), false, null));
                                    ghest.setKarmozdPoosheshEzafi(asnadeSodorService.getKarmozdPoosheshEzafi(ghest.getHaghBimePoosheshhayeEzafiLong(), darkhastTaghirat.getNewPishnehad().getTarh(), darkhastTaghirat.getNewPishnehad().getBimename().getTarikhSodour()));
                                    System.out.println("H:" + tr.getGhestAmount() + " T: " + tr.getTarikh() + " MA: " + tr.getMaliat() + " PE: " + tr.getHaghBimePusheshHaayeEzaafi()
                                            + " HF: " + tr.getHaghBimeKhaalesFotYekja() + " B: " + tr.getHazineBimeGari() + " E: " + tr.getHazineEdari() + " V: " + tr.getHazineVosul() + " K: " + tr.getKaarmozd()
                                            + " PE(H): " + tr.getHaghBimePoosheshHadese() + " PE(A): " + tr.getHaghBimePoosheshAmraz() + " PE(N): " + tr.getHaghBimePoosheshNaghsOzv() + " PE(M): " + tr.getHaghBimePoosheshMoafiat());
                                    ghestList.add(ghest);
                                    credebitList.add(credebit);
                                    if (tr.isCreatedByElhaghie())
                                        credebitListForShomareElhaghie.add(credebit);
                                }
                            }
                            System.out.println("-----------------------------");
                            if (!Constant.DEV_testElhaghieTaghirat) {
                                for (Integer i : deleteList) {
                                    asnadeSodorService.hazfeGhest(i);
                                }
                                asnadeSodorService.saveAghsat(ghestList);
                                asnadeSodorService.saveCredebitList(credebitList);
                                long majmooAmount = 0L, majmooEzafi = 0L, majmooMaliat = 0L;
                                GhestBandi newGb = asnadeSodorService.findGhestBandiById(gb.getId());
                                gbIdList.add(gb.getId());
                                for (Ghest g : newGb.getGhestList()) {
                                    if (!deleteList.contains(g.getId())) {
                                        majmooAmount += g.getCredebit().getAmount_long();
                                        majmooEzafi += g.getHaghBimePoosheshhayeEzafiLong();
                                        majmooMaliat += g.getMaliatLong();
                                    }
                                }
                                for (Ghest g : ghestList) {
                                    majmooAmount += g.getCredebit().getAmount_long();
                                    majmooEzafi += g.getHaghBimePoosheshhayeEzafiLong();
                                    majmooMaliat += g.getMaliatLong();
                                }
                                newGb.setMajmooeAmount(majmooAmount);
                                newGb.setMajmooeEzafi(majmooEzafi);
                                newGb.setMajmooeMaliat(majmooMaliat);
                                gbList.add(newGb);
                            }
                            GhestBandi newGb = asnadeSodorService.findGhestBandiById(gb.getId());
                            for (Ghest g : newGb.getGhestList()) {
                                CredebitForLogGhest cflg = new CredebitForLogGhest(DateUtil.getCurrentDate(), g.getSarresidDate(), g.getCredebit().getRemainingAmount(), g.getCredebit().getShomareMoshtari(), g.getCredebit().getShenaseCredebit(), logGhest, true);
                                cflgList.add(cflg);
                            }
                        }
                    }
                }

            }
            if (!Constant.DEV_testElhaghieTaghirat) {
                // taghsit salhaye ayande bayad avaz shavand
                Bimename newBimename = pishnehadService.findBimenameById(darkhastTaghirat.getNewPishnehad().getBimename().getId());
                if (!onlyHaghBimeAvvalie) {
                    // agar faghat hagh bime avvalie ezafe shode bud, taghsit salhaye ati ra retaghsit nemikonim
                    ArrayList<GhestBandi> toReTaghsit = new ArrayList<GhestBandi>();
                    for (GhestBandi gb : newBimename.getGhestBandiList()) {
                        if (gb.getSaleBimeiInt() > saleBimeiElhaghie) {
                            toReTaghsit.add(gb);
                        }
                    }
                    for (GhestBandi reTaghsit : toReTaghsit) {
                        int sal = reTaghsit.getSaleBimeiInt();
                        asnadeSodorService.deleteGhestBandiById(reTaghsit);
                        if (user != null)
                            asnadeSodorService.saveGhestbandi(darkhastTaghirat.getNewPishnehad(), sal, user);
                    }
                }
                logGhestService.save(logGhest);
                logGhestService.saveAllCFLG(cflgList);
                for (GhestBandi gb : gbList) {
                    asnadeSodorService.updateGhestbandi(gb);
                }
            }
        }

        if (!Constant.DEV_testElhaghieTaghirat) {
            // ashkhas
            boolean isBimeShode = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMESHODE);
            boolean isBimeGozar = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMEGOZAR);
            if (isBimeShode || isBimeGozar) {
                if (darkhastTaghirat.getDarkhast().getElhaghiyeList().size() > 1) {
                    // todo: farze yek taghir shakhs dar ye elhaghie
                    Shakhs newShakhs = darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs();
                    if (isBimeGozar)
                        newShakhs = darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs();
                    pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
                    boolean changeBimeGozarAddress=false;
                    boolean changeBimeShodeAddress=false;
                    for(PishnehadFieldChanges pChanges: pishnehadFieldChangesList)
                    {
                        if(pChanges.getCategory().equals(("AD")))
                        {
                            if(pChanges.getSubject().contains("بیمه گذار"))
                            {
                                changeBimeGozarAddress=true;
                            }

                            else if(pChanges.getSubject().contains("بیمه شده"))
                            {
                                changeBimeShodeAddress=true;
                            }
                        }
                    }

                    List<ListBimenameTaghirVM> bTaghirList = darkhastService.getListBimenamehayeTaghirKarde(darkhastTaghirat);
                    for (ListBimenameTaghirVM bt : bTaghirList) {
                        for(Elhaghiye el: darkhastTaghirat.getDarkhast().getElhaghiyeList())
                        {
                            if(el.getOldPishnehad()==null || el.getOldPishnehad().getId()==darkhastTaghirat.getOldPishnehad().getId())
                                continue;

                            else if(el.getOldPishnehad().getId().equals(bt.getBimename().getPishnehad().getId()))
                            {
                                if (bt.getShakhsRole().equals("بیمه گذار"))
                                {
//                                    bt.getBimename().getPishnehad().getBimeGozar().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeGozar(bt.getBimename().getPishnehad().getBimeGozar());
                                    el.getNewPishnehad().getBimeGozar().setShakhs(newShakhs);
                                    pishnehadService.updateBimeGozar(el.getNewPishnehad().getBimeGozar());
                                    if(changeBimeShodeAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                    if(changeBimeGozarAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                }
                                else if (bt.getShakhsRole().equals("بیمه شده"))
                                {
//                                    bt.getBimename().getPishnehad().getBimeShode().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeShode(bt.getBimename().getPishnehad().getBimeShode());
                                    el.getNewPishnehad().getBimeShode().setShakhs(newShakhs);
                                    pishnehadService.updateBimeShode(el.getNewPishnehad().getBimeShode());
                                    if (changeBimeShodeAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                    if (changeBimeGozarAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }
                                }
                                else// (bt.getShakhsRole().equals("بیمه شده | بیمه گذار"));
                                {
//                                    bt.getBimename().getPishnehad().getBimeGozar().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeGozar(bt.getBimename().getPishnehad().getBimeGozar());
//                                    bt.getBimename().getPishnehad().getBimeShode().setShakhs(newShakhs);
//                                    pishnehadService.updateBimeShode(bt.getBimename().getPishnehad().getBimeShode());

                                    el.getNewPishnehad().getBimeShode().setShakhs(newShakhs);
                                    pishnehadService.updateBimeShode(el.getNewPishnehad().getBimeShode());

                                    if (changeBimeShodeAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                    if (changeBimeGozarAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                    el.getNewPishnehad().getBimeGozar().setShakhs(newShakhs);
                                    pishnehadService.updateBimeGozar(el.getNewPishnehad().getBimeGozar());

                                    if (changeBimeShodeAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }

                                    if (changeBimeGozarAddress)
                                    {
                                        el.getNewPishnehad().setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                                        pishnehadService.updatePishnehad(el.getNewPishnehad());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            darkhastTaghirat.setArchive("yes");
            darkhastTaghirat.setFinished(true);
            darkhastTaghirat.getNewPishnehad().setValid(true);
            darkhastTaghirat.getOldPishnehad().setValid(false);
            for (Elhaghiye theElhaghiye : darkhastTaghirat.getDarkhast().getElhaghiyeList()) {
                theElhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_FINAL_STATE));
                darkhastService.updateElhaghiye(theElhaghiye);
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theElhaghiye.getBimename().setHasElhaghie("yes");
                theElhaghiye.getBimename().setState(theState);
                pishnehadService.updateBimename(theElhaghiye.getBimename());
                if(theElhaghiye.getOldPishnehad()!=null && theElhaghiye.getNewPishnehad()!=null)
                {
                    theElhaghiye.getOldPishnehad().setValid(false);
                    theElhaghiye.getNewPishnehad().setValid(true);
                    pishnehadService.updatePishnehad(theElhaghiye.getNewPishnehad());
                    pishnehadService.updatePishnehad(theElhaghiye.getOldPishnehad());
                }
            }
            pishnehadService.updatePishnehad(darkhastTaghirat.getNewPishnehad());
            pishnehadService.updatePishnehad(darkhastTaghirat.getOldPishnehad());
            Bimename theBimename = darkhastTaghirat.getNewPishnehad().getBimename();
            darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
            State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
            theBimename.setState(theState);
            theBimename.setHasElhaghie("yes");
            if (darkhastTaghirat.getWhenApply() == 2) //if elhaghie is jari
            {
                theBimename.setKarmozdBimename(karmozdService.karmozdBimename(darkhastTaghirat.getNewPishnehad().getBimename(), darkhastTaghirat.getNewPishnehad().getEstelam()));
            }
            pishnehadService.updateBimename(theBimename);
        }
    }    */

    private void nesbatHazineha(Ghest fromGhest, TaghsitReport toGhest, Long fromAmount) {
        Long toAmount = toGhest.getGhestAmount();
        toGhest.setHaghBimeKhaalesFotYekja(Math.round((Double.parseDouble(fromGhest.getHaghBimeFotYekja().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHaghBimePusheshHaayeEzaafi(Math.round((Double.parseDouble(fromGhest.getHaghBimePoosheshhayeEzafi().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHaghBimePoosheshAmraz(Math.round((Double.parseDouble(fromGhest.getHaghBimePoosheshAmraz().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHaghBimePoosheshHadese(Math.round((Double.parseDouble(fromGhest.getHaghBimePoosheshHadese().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHaghBimePoosheshMoafiat(Math.round((Double.parseDouble(fromGhest.getHaghBimePoosheshMoafiat().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHaghBimePoosheshNaghsOzv(Math.round((Double.parseDouble(fromGhest.getHaghBimePoosheshNaghsOzv().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHazineBimeGari(Math.round((Double.parseDouble(fromGhest.getHazineBimegari().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHazineEdari(Math.round((Double.parseDouble(fromGhest.getHazineEdari().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setKaarmozd(Math.round((Double.parseDouble(fromGhest.getHazineKarmonz().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setHazineVosul(Math.round((Double.parseDouble(fromGhest.getHazineVosoul().replaceAll(",", "")) * toAmount) / fromAmount));
        toGhest.setMaliat(Math.round((Double.parseDouble(fromGhest.getMaliat().replaceAll(",", "")) * toAmount) / fromAmount));
    }

    public String makeDarkhastTaghiratTransition() {
        User theUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (theUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Transition t = pishnehadService.findTransitionById(transitionId);
            darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
            if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList))
            {
                if (darkhastTaghirat.getHaveBareMali() == null)
                {
                    darkhastTaghirat.setHaveBareMali(true);
                    darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                }
            }
            else
            {
                if (darkhastTaghirat.getHaveBareMali() != null && darkhastTaghirat.getHaveBareMali().equals(true))
                {
                    darkhastTaghirat.setHaveBareMali(false);
                    darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                }
            }
            message = OmrUtil.isElhaghieValid(pishnehadFieldChangesList, darkhastTaghirat);
            if (!message.equals("VALID") && t.getStateEnd().getId() != 9220 && t.getStateEnd().getId() != 9210) {
                addActionMessage(message);
                return "stayOnSamePlace";
            }
            if (t.getStateEnd().getId() == 9020 && OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList)) {
                MohasebateTeory mt = new MohasebateTeory();
                List<TaghsitReport> tr = null;
                try {
                    tr = mt.taghsitReport(darkhastTaghirat.getNewPishnehad(), false, -1);
                } catch (BimeNaamehVaSarmayehGozari.CustomValidationException ex) {
                    addActionMessage(ex.getMyMessage());
                    tr = null;
                } catch (Exception ex) {
                    addActionMessage("بیمه نامه دارای خطای محاسباتی است.");
                    tr = null;
                }
                if (tr == null) {
                    return "stayOnSamePlace";
                }
            }
            darkhastService.transitDarkhastTaghirat(theUser, darkhastTaghirat.getId(), transitionId, logmessage);
            if (t.getStateEnd().getId() == 9220 || t.getStateEnd().getId() == 9210) {
                elhaghiye = darkhastTaghirat.getDarkhast().getElhaghiye();
                darkhastTaghirat.setArchive("yes");
                darkhastTaghirat.setFinished(true);
                darkhastTaghirat.getNewPishnehad().setValid(false);
                darkhastTaghirat.getOldPishnehad().setValid(true);
                elhaghiye.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_ENSERAF_STATE));
                darkhastService.updateElhaghiye(elhaghiye);
                pishnehadService.updatePishnehad(darkhastTaghirat.getNewPishnehad());
                pishnehadService.updatePishnehad(darkhastTaghirat.getOldPishnehad());
                Bimename theBimename = darkhastTaghirat.getNewPishnehad().getBimename();
                if (darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs().getChangeWithSerach() != null)
                {
                    darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs().setChangeWithSerach(null);
                    shakhsService.updateShakhs(darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs());
                }
                if (darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs().getChangeWithSerach() != null)
                {
                    darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs().setChangeWithSerach(null);
                    shakhsService.updateShakhs(darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs());
                }
                darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theBimename.setState(theState);
                pishnehadService.updateBimename(theBimename);

                for(Elhaghiye el : darkhastTaghirat.getDarkhast().getElhaghiyeList())
                {
                    if(el.getOldPishnehad()==null)continue;
                    el.getBimename().setState(theState);
                    pishnehadService.updateBimename(theBimename);
                    el.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_ENSERAF_STATE));
                    darkhastService.updateElhaghiye(el);
                }
            }
            if (pishnehadService.hasPermission(theUser, darkhastTaghirat.getState().getId())) {
                return "stayOnSamePlace";
            }
            return SUCCESS;
        }
    }

    public String makeDarkhastKhesaratTaghiratTransition() {
        User theUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (theUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            pishnehad = pishnehadService.findById(pishnehad.getId());
            if (pishnehad.getBimename().getKhesarats().get(0).getState().getId() == 641) {
                Transition t = pishnehadService.findTransitionById(601);
                InsuranceServiceFactory.getWithoutSessionKhesaratService().transitKhesarat(theUser, pishnehad.getBimename().getKhesarats().get(0).getId(), t.getId(), t.getTransitionName());
            } else if (pishnehad.getBimename().getKhesarats().get(0).getState().getId() == 642) {
                Transition t = pishnehadService.findTransitionById(602);
                InsuranceServiceFactory.getWithoutSessionKhesaratService().transitKhesarat(theUser, pishnehad.getBimename().getKhesarats().get(0).getId(), t.getId(), t.getTransitionName());
            } else if (getTransitionSelector() != null && getTransitionSelector().length() > 0) {
                Transition t = pishnehadService.findTransitionById(Integer.parseInt(getTransitionSelector()));
                InsuranceServiceFactory.getWithoutSessionKhesaratService().transitKhesarat(theUser, pishnehad.getBimename().getKhesarats().get(0).getId(), t.getId(), t.getTransitionName());
            }
            return SUCCESS;
        }
    }

    public String makeDarkhastTransition() {
        sodurVamSuccess = "no";
        User theUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (theUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = theUser.getId();
//            if (darkhastBazkharid != null && darkhastBazkharid.getId() != null)
//                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
//            else if (khesarat != null){
//                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(khesarat.getBimename().getDarkhastBazkharid().getId());
//            }
//            if (transitionId == null) {
//                transitionId = 601;
//            }
            String amountGhabelPardakht = "0";
            String amountTaeedShode = "0";
            String amountMazadI = "0";
            String amountAtiI = "0";
            String amountErfaghI = "0";
            String andukhteI = "0";
            String amountElamShode = "0";
            String accidentDate = "";


            String amountGhabelPardakhtII = "0";
            String amountTaeedShodeII = "0";
            String amountMazadII = "0";
            String amountErfaghII = "0";
            String amountAtiII = "0";
            String andukhteII = "0";
            String amountElamShodeII = "0";
            String accidentDateII = "";

            if (transitionId == 660 || transitionId == 650 || transitionId == 613|| transitionId == 670 || transitionId == 618) {
                amountElamShode=darkhastBazkharid.getKhesaratI().getAmountElamShode();
                accidentDate=darkhastBazkharid.getKhesaratI().getAccidentDate();
                amountGhabelPardakht = darkhastBazkharid.getKhesaratI().getAmountGhabelPardakht();
                amountTaeedShode = darkhastBazkharid.getKhesaratI().getAmountTaidShode();
                //todo: if amraz o naghs then should be amountGhabelPardakht=amountTaeedShode
                amountMazadI = darkhastBazkharid.getKhesaratI().getAmountMazad();
                amountErfaghI = darkhastBazkharid.getKhesaratI().getAmountErfagh();
                amountAtiI = darkhastBazkharid.getKhesaratI().getAmountAti();
                andukhteI = darkhastBazkharid.getKhesaratI().getAndukhte();

                DarkhastBazkharid db = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());

                if (db.getKhesaratII() != null) {
                    amountElamShodeII = darkhastBazkharid.getKhesaratII().getAmountElamShode();
                    accidentDateII = darkhastBazkharid.getKhesaratII().getAccidentDate();
                    amountGhabelPardakhtII = darkhastBazkharid.getKhesaratII().getAmountGhabelPardakht();
                    amountTaeedShodeII = darkhastBazkharid.getKhesaratII().getAmountTaidShode();
                    //todo: if amraz o naghs then should be  amountGhabelPardakhtII=amountTaeedShodeII
                    amountMazadII = darkhastBazkharid.getKhesaratII().getAmountMazad();
                    amountErfaghII = darkhastBazkharid.getKhesaratII().getAmountErfagh();
                    amountAtiII = darkhastBazkharid.getKhesaratII().getAmountAti();
                    andukhteII = darkhastBazkharid.getKhesaratII().getAndukhte();
                }

            }
            Transition t = pishnehadService.findTransitionById(transitionId);
            darkhastService.transitDarkhast(theUser, darkhastBazkharid.getId(), transitionId, logmessage);
            retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), theUser.getId());
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            if(nazarKarshenasKhesaratBeTafkik!=null && !nazarKarshenasKhesaratBeTafkik.isEmpty())
            {
                darkhastBazkharid.setNazarKarshenasKhesarat(darkhastBazkharid.getNazarKarshenasKhesarat()!=null? darkhastBazkharid.getNazarKarshenasKhesarat()+"|"+nazarKarshenasKhesaratBeTafkik: nazarKarshenasKhesaratBeTafkik);
            }
            if (transitionId == 10010) {
//                for (Ghest g : darkhastBazkharid.getGhestBandi().getGhestList())
//                String identifier = sequenceService.nextShenaseCredebit();
                Credebit credebit = new Credebit(darkhastBazkharid.getMablagheVamedarkhasti(), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ETEBAR_VAM);
                credebit.setIdentifier(darkhastBazkharid.getShomareVam());
                credebit.setDateFish(DateUtil.getCurrentDate());
                credebit.setTimeFish(DateUtil.getCurrentTime());
                for (Ghest ghest : darkhastBazkharid.getGhestBandi().getGhestList()) {
                    ghest.getCredebit().setShomareMoshtari(sequenceService.generateNextShomareMoshtari(theUser.getMojtamaSodoor(), "9", "120", ghest.getCredebit().getRemainingAmount_long()));
                    asnadeSodorService.updateCredebit(ghest.getCredebit());
                }
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                asnadeSodorService.saveCredebit(credebit);
//                Elhaghiye elhaghiye = new Elhaghiye();
//                elhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.VAM);
//                elhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
//                elhaghiye.setBimename(darkhastBazkharid.getBimename());
//                elhaghiye.setState(constantItemsService.findStateById(3001));
//                String darkhastSequence = sequenceService.nextShomareElhaghiye(darkhastBazkharid.getBimename());
//                String finalDarkhastShomare = darkhastBazkharid.getBimename().getShomare();
//                finalDarkhastShomare += "/" + darkhastSequence;
//                elhaghiye.setDarkhastBazkharid(darkhastBazkharid);
//                elhaghiye.setDarkhast(darkhastBazkharid.getDarkhast());
                darkhastBazkharid.setFinished(true);
//                darkhastBazkharid.setElhaghie(elhaghiye);
//                darkhastService.saveElhaghiye(elhaghiye);
//                darkhastBazkharid.getBimename().getElhaghiyeha().add(elhaghiye);
                darkhastBazkharid.setShomareVam(sequenceService.nextShomareVam(theUser.getMojtamaSodoor()));
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
//                elhaghiye.setShomareElhaghiye(sequenceService.nextShomareElhaghiye(darkhastBazkharid.getBimename()));
//                darkhastService.updateElhaghiye(elhaghiye);
                String reason = "وام بیمه نامه " + darkhastBazkharid.getBimename().getShomare() + " به شماره " + darkhastBazkharid.getShomareVam();
                // ach payment
                Integer paymentAmount = Integer.parseInt(darkhastBazkharid.getMablagheVamedarkhasti().replace(",", "").trim());
                if (paymentAmount > 0 && paymentAmount <= 200000000 && darkhastBazkharid.getShomareShaba().length() > 10) {
                    String response = ACHPayment.achPaymentRequest(credebit.getShenaseCredebit(), paymentAmount, reason,
                            darkhastBazkharid.getSahebHesab(), darkhastBazkharid.getShomareShaba(),
                            darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getShomareShenasnameh(), darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi(),
                            theUser.getUsername(), darkhastBazkharid.getBimename().getPishnehad().getAddressBimeGozar().getTelephoneHamrah(), "111116");
                    if (!response.contains("-")) {
                        Credebit credebitACH = new Credebit(Integer.toString(paymentAmount), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ACH);
                        credebitACH.setRahgiriACH(response);
                        asnadeSodorService.saveCredebit(credebitACH);
                        asnadeSodorService.sabteSanad(theUser, credebitACH, credebit, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
                    } else {
                        final org.slf4j.Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
                        logger.info(String.format("ACH Failed ! credebit identifier: %s bimename.shomare: %s ach response: %s"
                                , credebit.getShenaseCredebit(), reason, response));
                    }
                }

                Bimename theBimename = darkhastBazkharid.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theBimename.setState(theState);
                pishnehadService.updateBimename(theBimename);
                sodurVamSuccess = "yes";

            } else if (transitionId == 10013) {
//                Elhaghiye elhaghiye = new Elhaghiye();
//                elhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.VAM);
//                elhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
//                elhaghiye.setBimename(darkhastBazkharid.getBimename());
//                elhaghiye.setDarkhastBazkharid(darkhastBazkharid);
//                darkhastBazkharid.setElhaghie(elhaghiye);

//                String darkhastSequence = sequenceService.nextShomareElhaghiye(darkhastBazkharid.getBimename());
//                String finalDarkhastShomare = darkhastBazkharid.getBimename().getShomare();
//                finalDarkhastShomare += "/" + darkhastSequence;
//                darkhastService.saveElhaghiye(elhaghiye);
//                if (darkhastBazkharid.getBimename().getElhaghiyeha() != null) {
//                    darkhastBazkharid.getBimename().getElhaghiyeha().add(elhaghiye);
//                } else {
//                    List<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
//                    elhaghiyes.add(elhaghiye);
//                    darkhastBazkharid.getBimename().setElhaghiyeha(elhaghiyes);
//                }
                darkhastBazkharid.setFinished(true);
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);

//                elhaghiye.setShomareElhaghiye(finalDarkhastShomare);
//                darkhastService.updateElhaghiye(elhaghiye);

                Bimename theBimename = darkhastBazkharid.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theBimename.setState(theState);
                pishnehadService.updateBimename(theBimename);
            } else if (transitionId == 11008 || transitionId == 11014 || transitionId == 11018 || transitionId == 11009 || transitionId == 11032) {
                darkhastBazkharid.setJameAghsatMoavaghBimename(Long.parseLong(darkhastBazkharid.getBimename().getAmountMoavagh().trim().replaceAll(",","")));
                jameKolBedehi = 0l;
                for (DarkhastBazkharid db : darkhastBazkharid.getBimename().getAllDarkhasts())
                {
                    if (db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && db.getState().getId().equals(Constant.VAM_FINAL_STATE))
                    {
                        jameKolBedehi += Math.round(MohasebateFaniVam.calcJaraemeDirkard(db, asnadeSodorService) +
                                MohasebateFaniVam.calcJaraemeTavigh(db)) +
                                Math.round(MohasebateFaniVam.calcJameAslAghsatAti(db)) +
                                Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(db));
                    }
                }
                darkhastBazkharid.setJameKolBedehiVam(jameKolBedehi);
                Credebit credebit = new Credebit(darkhastBazkharid.getMablaghDarkhastiBardasht(), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ETEBAR_BARDASHT);
                credebit.setDateFish(DateUtil.getCurrentDate());
                credebit.setTimeFish(DateUtil.getCurrentTime());
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                asnadeSodorService.saveCredebit(credebit);
//------------------------------------------kasre bardasht Az andukhte bimename-----------------------------------------
                Credebit kasrAzAndukhte = new Credebit(Long.toString(0L-credebit.getAmount_long()), sequenceService.nextShenaseCredebit(),
                        darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                kasrAzAndukhte.setCredebitTypeDesc(Credebit.CredebitTypeDesc.BARDASHT);
                kasrAzAndukhte.setDateFish(credebit.getDateFish());
                kasrAzAndukhte.setTimeFish(credebit.getTimeFish());
                asnadeSodorService.saveCredebit(kasrAzAndukhte);
//----------------------------------------------------------------------------------------------------------------------
                darkhastBazkharid.setCredebitBardasht(credebit);

                // ach payment
                Integer paymentAmount = Integer.parseInt(darkhastBazkharid.getMablaghDarkhastiBardasht().replace(",", "").trim());
                if (paymentAmount > 0 && paymentAmount <= 200000000 && darkhastBazkharid.getShomareShaba().length() > 10) {
                    String response = ACHPayment.achPaymentRequest(credebit.getShenaseCredebit(), paymentAmount, "برداشت از اندوخته برای بیمه نامه " + darkhastBazkharid.getBimename().getShomare(),
                            darkhastBazkharid.getSahebHesab(), darkhastBazkharid.getShomareShaba(),
                            darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getShomareShenasnameh(), darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi(),
                            theUser.getUsername(), darkhastBazkharid.getBimename().getPishnehad().getAddressBimeGozar().getTelephoneHamrah(), "111116");
                    if (!response.contains("-")) {
                        Credebit credebitACH = new Credebit(Integer.toString(paymentAmount), sequenceService.nextShenaseCredebit(), darkhastBazkharid.getBimename(), darkhastBazkharid.getBimename().getPishnehad(), Credebit.CredebitType.ACH);
                        credebitACH.setRahgiriACH(response);
                        asnadeSodorService.saveCredebit(credebitACH);
                        asnadeSodorService.sabteSanad(theUser, credebitACH, credebit, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, true);
                    } else {
                        final org.slf4j.Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
                        logger.info(String.format("ACH Failed ! credebit identifier: %s bimename.shomare: %s ach response: %s"
                                , credebit.getShenaseCredebit(), darkhastBazkharid.getBimename().getShomare(), response));
                    }
                }
                String darkhastSequence = sequenceService.nextShomareElhaghiye(darkhastBazkharid.getBimename());
                String finalDarkhastShomare = darkhastBazkharid.getBimename().getShomare();
                finalDarkhastShomare += "/" + darkhastSequence;
                darkhastBazkharid.setFinished(true);
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                Bimename theBimename = darkhastBazkharid.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theBimename.setState(theState);
                pishnehadService.updateBimename(theBimename);
            } else if (transitionId == 12020) {
                Long jaraemDirkard = Long.parseLong(darkhastBazkharid.getJaraemeDirkard().replaceAll(",", "").trim());
                Long aghsatMoavagh = Long.parseLong(darkhastBazkharid.getJameAghsatPardakhtNashode().replaceAll(",", "").trim());
                Long jamAslAghsatAti = Long.parseLong(darkhastBazkharid.getJameAslAghsatAti().replaceAll(",", "").trim());
                Integer amount = ((Long) (jaraemDirkard + aghsatMoavagh + jamAslAghsatAti)).intValue();
                String customerNum = sequenceService.generateNextShomareMoshtari(theUser.getMojtamaSodoor(), "9", "120", amount.longValue());
                String sarresidDate = DateUtil.addDays(DateUtil.getCurrentDate(), 30);
                //todo: bastane ghesthaye vame marbute . . .
                Credebit credebit = new Credebit(amount, "بدهی تسویه پیش از موعد", darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName(),
                        sarresidDate, "OMR", "TASVIE_PISH_AZ_MOED_BEDEHI", darkhastBazkharid.getBimename().getShomare(), sequenceService.nextShenaseCredebit(), customerNum);

                List<Credebit> credebitList = new ArrayList<Credebit>();
                credebitList.add(credebit);
                asnadeSodorService.saveCredebitList(credebitList);
                darkhastBazkharid.setBedehiPishAzMoedCredebit(credebit);
                darkhastBazkharid.setEmzaKonandeAval(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeAval().getId()));
                darkhastBazkharid.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(elhaghiye.getEmzaKonandeDovom().getId()));
            } else if (transitionId == 12029) {
                Elhaghiye elhaghiye = new Elhaghiye();
                elhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TASVIE_PISH_AZ_MOEDE_VAM);
                elhaghiye.setTarikhAsar(DateUtil.getCurrentDate());
                elhaghiye.setBimename(darkhastBazkharid.getBimename());
                String darkhastSequence = sequenceService.nextShomareElhaghiye(darkhastBazkharid.getBimename());
                String finalDarkhastShomare = darkhastBazkharid.getBimename().getShomare();
                finalDarkhastShomare += "/" + darkhastSequence;
                if (darkhastBazkharid.getBimename().getElhaghiyeha() != null) {
                    darkhastBazkharid.getBimename().getElhaghiyeha().add(elhaghiye);
                } else {
                    List<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
                    elhaghiyes.add(elhaghiye);
                    darkhastBazkharid.getBimename().setElhaghiyeha(elhaghiyes);
                }
                Credebit bedehi = darkhastBazkharid.getBedehiPishAzMoedCredebit();

                List<Credebit> credebitList = new ArrayList<Credebit>();

                Credebit kasrAzAndukhteCredebit = new Credebit(((Long) (0L - bedehi.getAmount_long())).intValue(), "تسویه پیش از موعد از اندوخته", darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName(),
                        "", "OMR", "TAGHIRAT_ESLAH_AGHSAT", darkhastBazkharid.getBimename().getShomare(), sequenceService.nextShenaseCredebit(), bedehi.getShomareMoshtari());
                credebitList.add(kasrAzAndukhteCredebit);

                Credebit etebar = new Credebit(bedehi.getAmount_long().intValue(), "اعتبار تسویه پیش از موعد", darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName(),
                        "", "OMR", "TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE", darkhastBazkharid.getBimename().getShomare(), sequenceService.nextShenaseCredebit(), bedehi.getShomareMoshtari());
                credebitList.add(etebar);

                asnadeSodorService.saveCredebitList(credebitList);

                asnadeSodorService.sabteSanad(theUser, bedehi, etebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                darkhastService.saveElhaghiye(elhaghiye);
                elhaghiye.setShomareElhaghiye(finalDarkhastShomare);
                darkhastService.updateElhaghiye(elhaghiye);
                Bimename theBimename = darkhastBazkharid.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                theBimename.setState(theState);
                pishnehadService.updateBimename(theBimename);
//            } else if (transitionId == 10014 || transitionId == 11010 || transitionId == 9050 || transitionId == 1110 || transitionId==1130 || transitionId==1230 || transitionId==1170 || transitionId==1290 || transitionId==11016 || transitionId==10015) {
            }
            if(t.getStateEnd().getId()== 655)
            {

                if(darkhastBazkharid.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.HADESE)||darkhastBazkharid.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.OMR))
                {
                    for(EstefadeKonandeganAzSarmayeBime es:darkhastBazkharid.getBimename().getPishnehad().getEstefadeKonandeganAzSarmayeBime())
                    {
                        if(es.getVaziateFotVaHayat().equals("fout") && es.getOlaviat().equals("1"))
                        {
                            KhesaratHavale havale = new KhesaratHavale();
                            havale.setCodeMelli(es.getKodeMelli());
                            havale.setName(es.getName());
                            havale.setFamily(es.getNameKhanevadegi());
                            havale.setType(KhesaratHavale.Type.CHECK);
//                            havale.setAmountHavale(NumberFormat.getInstance().format(Long.parseLong(es.getDarsadeSahm().trim().replaceAll(",", "")) * Long.parseLong(darkhastBazkharid.getKhesaratI().getAmountTaidShode().trim().replaceAll(",", "")) / 100));
                            havale.setAmountHavale(NumberFormat.getInstance().format(Long.parseLong(darkhastBazkharid.getKhesaratI().getAmountTaidShode().trim().replaceAll(",", ""))));
                            havale.setKhesarat(darkhastBazkharid.getKhesaratI());
                            khesaratService.saveKhesaratHavale(havale);
                            break;
                        }
                    }
                }
                else
                {
                    KhesaratHavale havale = new KhesaratHavale();
                    havale.setCodeMelli(darkhastBazkharid.getBimename().getPishnehad().getBimeShode().getShakhs().getKodeMelliShenasayi());
                    havale.setName(darkhastBazkharid.getBimename().getPishnehad().getBimeShode().getShakhs().getName());
                    havale.setFamily(darkhastBazkharid.getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi());
                    havale.setType(KhesaratHavale.Type.CHECK);
                    havale.setKhesarat(darkhastBazkharid.getKhesaratI());
                    havale.setAmountHavale(darkhastBazkharid.getKhesaratI().getAmountTaidShode());
                    khesaratService.saveKhesaratHavale(havale);
                }
                if(darkhastBazkharid.getKhesaratII()!=null)
                {
                    if (darkhastBazkharid.getKhesaratII().getKhesaratType().equals(Khesarat.KhesaratType.HADESE) || darkhastBazkharid.getKhesaratII().getKhesaratType().equals(Khesarat.KhesaratType.OMR))
                    {
                        for (EstefadeKonandeganAzSarmayeBime es : darkhastBazkharid.getBimename().getPishnehad().getEstefadeKonandeganAzSarmayeBime())
                        {
                            if (es.getVaziateFotVaHayat().equals("fout") && es.getOlaviat().equals("1"))
                            {
                                KhesaratHavale havale = new KhesaratHavale();
                                havale.setCodeMelli(es.getKodeMelli());
                                havale.setName(es.getName());
                                havale.setFamily(es.getNameKhanevadegi());
                                havale.setType(KhesaratHavale.Type.CHECK);
//                                havale.setAmountHavale(NumberFormat.getInstance().format(Long.parseLong(es.getDarsadeSahm().trim().replaceAll(",", "")) * Long.parseLong(darkhastBazkharid.getKhesaratII().getAmountTaidShode().trim().replaceAll(",", "")) / 100));
                                havale.setAmountHavale(NumberFormat.getInstance().format(Long.parseLong(darkhastBazkharid.getKhesaratII().getAmountTaidShode().trim().replaceAll(",", ""))));
                                havale.setKhesarat(darkhastBazkharid.getKhesaratII());
                                khesaratService.saveKhesaratHavale(havale);
                            }
                        }
                    }
                }
            }
            if (transitionId == 660 || transitionId == 650 || transitionId == 613|| transitionId == 670|| transitionId == 618) {
                if(transitionId == 613 || darkhastBazkharid.getShomareParvande()==null || darkhastBazkharid.getShomareParvande().isEmpty())
                {
                    User user = theUser;

                    darkhastBazkharid.getKhesaratI().setAmountGhabelPardakht(amountGhabelPardakht);
                    darkhastBazkharid.getKhesaratI().setAmountTaidShode(amountTaeedShode);
                    darkhastBazkharid.getKhesaratI().setAmountMazad(amountMazadI);
                    darkhastBazkharid.getKhesaratI().setAmountAti(amountAtiI);
                    darkhastBazkharid.getKhesaratI().setAmountErfagh(amountErfaghI);
                    darkhastBazkharid.getKhesaratI().setAndukhte(andukhteI);
                    darkhastBazkharid.getKhesaratI().setAccidentDate(accidentDate);
                    darkhastBazkharid.getKhesaratI().setAmountElamShode(amountElamShode);
                    khesaratService.updateKhesarat(darkhastBazkharid.getKhesaratI());

                    if (darkhastBazkharid.getKhesaratII() != null) {
                        darkhastBazkharid.getKhesaratII().setAmountGhabelPardakht(amountGhabelPardakhtII);
                        darkhastBazkharid.getKhesaratII().setAmountTaidShode(amountTaeedShodeII);
                        darkhastBazkharid.getKhesaratII().setAmountMazad(amountMazadII);
                        darkhastBazkharid.getKhesaratII().setAmountAti(amountAtiII);
                        darkhastBazkharid.getKhesaratII().setAmountErfagh(amountErfaghII);
                        darkhastBazkharid.getKhesaratII().setAndukhte(andukhteII);
                        darkhastBazkharid.getKhesaratII().setAccidentDate(accidentDateII);
                        darkhastBazkharid.getKhesaratII().setAmountElamShode(amountElamShodeII);
                        khesaratService.updateKhesarat(darkhastBazkharid.getKhesaratII());
                    }

                    if (transitionId != 613) {
                        darkhastBazkharid.setShomareParvande(InsuranceServiceFactory.getWithoutSessionKhesaratService().createShomareParvande(user.getMojtamaSodoor().getKodeNamayandeKargozar()));
                        generatedShomareParvande=true;
                    }
                }
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                if (pishnehadService.hasPermission(theUser, darkhastBazkharid.getState().getId()))
                    return "stayOnSamePlace";
                return SUCCESS;
            }
            else if (t.getStateEnd().getId() == 647 || t.getStateEnd().getId() == 651 || t.getStateEnd().getId() == 630 || t.getStateEnd().getId() == 11100 ||t.getStateEnd().getId() == 11110 || t.getStateEnd().getId() == 656 || t.getStateEnd().getId() == 1700 || t.getStateEnd().getId() == 1600 || t.getStateEnd().getId() == 1500 || t.getStateEnd().getId() == 10140 || t.getStateEnd().getId() == 10150) {
                Bimename theBimename = darkhastBazkharid.getBimename();
                State theState = constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE);
                if (t.getStateEnd().getId() == 656)
                {      int i=0;
                    for(KhesaratHavale h : darkhastBazkharid.getHavaleList())
                    {
                        h.setShomareHavale(darkhastBazkharid.getShomareParvande() + "/" + StringUtils.leftPad(String.valueOf(i), 4, "0"));
                        i++;
                        khesaratService.updateHavale(h);
                    }
                }
                if (t.getStateEnd().getId() == 656 && darkhastBazkharid.isBimenameClosed()) {
                    theState = constantItemsService.findStateById(Constant.BIMENAME_BASTE);
                }

                theBimename.setState(theState);
                if (darkhastBazkharid.getGhestBandi() != null) {
                    GhestBandi ghestBandiVam = darkhastBazkharid.getGhestBandi();
                    darkhastBazkharid.setGhestBandi(null);
                    asnadeSodorService.deleteGhestBandiById(ghestBandiVam);
                }
                darkhastBazkharid.setFinished(true);
                darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                pishnehadService.updateBimename(theBimename);
            } else if (transitionId == 1120) {
                return "stayOnSamePlace";
            }

            //todo: be should refactor clean code
            if ((transitionId >= 600 && transitionId <= 640) || transitionId == 680 || transitionId == 11001 || transitionId == 11002 || transitionId == 12001 || transitionId == 12003) {
//                return "stayOnDarkhastPlace";
            }

            if (pishnehadService.hasPermission(theUser, darkhastBazkharid.getState().getId())) {
                if(!darkhastBazkharid.getState().getId().equals(649) && !darkhastBazkharid.getCreator().getId().equals(theUser.getId()))
                    return "stayOnSamePlace";
            }
            retrieve(theUser);

            return SUCCESS;
        }
    }

    public String uploadZamaemDarkhast() throws IOException {
        String theUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User theResult = loginService.findUserByUsername(theUsername);
        if (theResult.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            backfromupload = "true";
            errorsMap = new HashMap<String, String>();
            if (darkhastBazkharid != null) {
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            } else if (darkhastTaghirat != null) {
                darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            }
            if (uploadedFile == null) {
                errorsMap.put("nofilesuploaded", "فایلی برای آپلود کردن انتخاب نشد.");
//                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                backfromupload = "true";
                getSession().put("backfromupload", backfromupload);
                getSession().put("errorsMap", errorsMap);
                return ERROR;
            } else {
                if (uploadedFile.length() > Constant.MAXIMUM_FILE_SIZE_ALLOWED) {
                    errorsMap.put("maxsizereached", "حجم فایل آپلود شده از 1 مگابایت بیشتر است. لطفا حجم آن را کم نموده و مجددا تلاش نمایید.");
                    //                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                    backfromupload = "true";
                    getSession().put("backfromupload", backfromupload);
                    getSession().put("errorsMap", errorsMap);
                    return ERROR;
                } else {
                    if (noeFile.equalsIgnoreCase("bahremandi") && darkhastBazkharid.getZamaemDarkhast().getFileDarkhastBahremandiId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("mafghudi") && darkhastBazkharid.getZamaemDarkhast().getFileElameMafghudiId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("aagahi") && darkhastBazkharid.getZamaemDarkhast().getFileAgahiChapId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("ehraz") && darkhastBazkharid.getZamaemDarkhast().getFileEhrazId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("adameTataboghEmza") && darkhastBazkharid.getZamaemDarkhast().getFileAdameTataboghEmzaId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && darkhastBazkharid.getZamaemDarkhast().getFileSayerId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("elhaghiye") && darkhastTaghirat.getZamaemDarkhast().getFileElhaghiyeId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("akharinVaziat") && darkhastTaghirat.getZamaemDarkhast().getFileAkharinVaziatId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimegozarJadid") && darkhastTaghirat.getZamaemDarkhast().getFileShenaseBimegozarJadidId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimegozarGhadim") && darkhastTaghirat.getZamaemDarkhast().getFileShenaseBimegozarGhadimId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimeshodeGhadim") && darkhastTaghirat.getZamaemDarkhast().getFileShenaseBimeshodeGhadimId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("kollieMadarek") && darkhastTaghirat.getZamaemDarkhast().getFileKollieId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish_bedehi_vam") && darkhastBazkharid.getZamaemDarkhast().getFileFishBedehiVamId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("elam_khesarat") && darkhastBazkharid.getZamaemDarkhast().getFileElamKhesaratId() != null) {
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        backfromupload = "true";
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("kollieMadarek") && !(uploadedFileName.toLowerCase().endsWith(".pdf"))) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("maxsizereached", "نوع فایل نامعتبر است.");
                        //                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    }
                }
            }
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = null;
            try {

                sid = l.getAuthServiceImplPort().login(Constant.LogicalDocUserName, Constant.LogicalDocUserPass);

                DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
                DocumentService documentService = docLocator.getDocumentServiceImplPort();
                FolderServiceImplServiceLocator folderLocator = new FolderServiceImplServiceLocator();
                FolderService folderService = folderLocator.getFolderServiceImplPort();
                WsFolderHolder folderHolder = new WsFolderHolder();
                WsFolder folder = new WsFolder();
                String radif = "";
                if (darkhastBazkharid != null) {
                    radif = darkhastBazkharid.getBimename().getSerial();
                } else if (darkhastTaghirat != null) {
                    radif = darkhastTaghirat.getOldPishnehad().getBimename().getShomare();
                }


                radif = radif.replaceAll("/", "-");
                folder.setName("files-" + radif);
                folder.setParentId(folderService.getRootFolder(sid).getId());
                folderHolder.value = folder;
                folderService.create(sid, folderHolder);
                RandomAccessFile raf = new RandomAccessFile(uploadedFile, "r");
                byte[] content = new byte[(int) raf.length()];
                while (raf.getFilePointer() < raf.length()) {
                    content[((int) raf.getFilePointer())] = raf.readByte();
                }
                ZamaemDarkhast theZamime = new ZamaemDarkhast();
                if (darkhastBazkharid != null) {
                    theZamime = darkhastBazkharid.getZamaemDarkhast();
                } else if (darkhastTaghirat != null) {
                    theZamime = darkhastTaghirat.getZamaemDarkhast();
                }
                tozihat += " " + DateUtil.getCurrentDate() + "-" + DateUtil.getCurrentTime();
                if (noeFile.equalsIgnoreCase("bahremandi")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileDarkhastBahremandiDescription(tozihat);
                    theZamime.setFileDarkhastBahremandiId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileDarkhastBahremandiName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("sayer")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileSayerDescription(tozihat);
                    theZamime.setFileSayerId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileSayerName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("adameTataboghEmza")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileAdameTataboghEmzaDescription(tozihat);
                    theZamime.setFileAdameTataboghEmzaId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileAdameTataboghEmzaName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("mafghudi")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileElameMafghudiDescription(tozihat);
                    theZamime.setFileElameMafghudiId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileElameMafghudiName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("aagahi")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileAgahiChapDescription(tozihat);
                    theZamime.setFileAgahiChapId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileAgahiChapName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("ehraz")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileEhrazDescription(tozihat);
                    theZamime.setFileEhrazId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileEhrazName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("elhaghiye")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileElhaghiyeDescription(tozihat);
                    theZamime.setFileElhaghiyeId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileElhaghiyeName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("akharinVaziat")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileAkharinVaziatDescription(tozihat);
                    theZamime.setFileAkharinVaziatId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileAkharinVaziatName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimegozarJadid")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileShenaseBimegozarJadidDescription(tozihat);
                    theZamime.setFileShenaseBimegozarJadidId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileShenaseBimegozarJadidName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimegozarGhadim")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileShenaseBimegozarGhadimDescription(tozihat);
                    theZamime.setFileShenaseBimegozarGhadimId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileShenaseBimegozarGhadimName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("shenasnameYaMelliBimeshodeGhadim")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileShenaseBimeshodeGhadimDescription(tozihat);
                    theZamime.setFileShenaseBimeshodeGhadimId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileShenaseBimeshodeGhadimName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("kollieMadarek")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileKollieDescription(tozihat);
                    theZamime.setFileKollieId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileKollieName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("fish_bedehi_vam")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileFishBedehiVamDescription(tozihat);
                    theZamime.setFileFishBedehiVamId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileFishBedehiVamName(document.getFileName());
                } else if (noeFile.equalsIgnoreCase("elam_khesarat")) {
                    WsDocument document = new WsDocument();
                    document.setFileName(uploadedFileName);
                    document.setFileSize(uploadedFile.getTotalSpace());
                    document.setFolderId(folderHolder.value.getId());
                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                    documentService.create(sid, documentHolder, content);
                    theZamime.setFileElamKhesaratDescription(tozihat);
                    theZamime.setFileElamKhesaratId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                    theZamime.setFileElamKhesaratName(document.getFileName());
                }

                darkhastService.updateZamaem(theZamime);
                if (darkhastBazkharid != null) {
                    darkhastService.updateDarkhastBazkharid(darkhastBazkharid);
                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                } else if (darkhastTaghirat != null) {
                    darkhastService.updateDarkhastTaghirat(darkhastTaghirat);
                    darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                }


                Long userId = theResult.getId();
                if (darkhastBazkharid != null) {
                    retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), userId);
                } else if (darkhastTaghirat != null) {
                    retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
                }
                getSession().put("backfromupload", backfromupload);
                return SUCCESS;
            } catch (Exception e) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                // Inja ok e ke bashe findUser!!
                User result = loginService.findUserByUsername(username);
                Long userId = result.getId();
                if (darkhastBazkharid != null) {
                    retrieveByDarkhastBazkharidId(darkhastBazkharid.getId(), userId);
                    darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                } else if (darkhastTaghirat != null) {
                    retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
                    darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                }
                logicaldocIndicator = Constant.ERROR;
                return ERROR;
            }
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
        setTransitionLogs(getTransitionLogService().getDarkhastBazkharidLogs(darkhastBazkharid.getId()));
        Collections.sort(getTransitionLogs());
        return SUCCESS;
    }

    public String retrieveByDarkhastTaghiratId(Integer darkhasTaghiratId, Long userId) {
        DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhasTaghiratId);
        allowedTransitions = new ArrayList<Transition>();
        if (theDarkhast.getState().getId() == 9080) {
            clinics = clinicService.findAllClinics();
        }

        for (Transition transition : theDarkhast.getState().getTransitions()) {
            if (transition.getStateBegin().equals(theDarkhast.getState())) {
                for (Role role : transition.getRoles()) {
                    for (User user : role.getUsers()) {
                        if (user.getId().equals(userId) && transition.getTransitionShow().equals("yes") && !allowedTransitions.contains(transition)) {
                            allowedTransitions.add(transition);
                        }
                    }
                }
            }
        }
        Collections.sort(allowedTransitions);
        setTransitionLogs(getTransitionLogService().getDarkhastTaghirLogs(darkhastTaghirat.getId()));
        Collections.sort(getTransitionLogs());
        return SUCCESS;
    }

    public String assignKarshenas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (darkhastBazkharid != null) {
                Long userId = result.getId();
                User theUser = loginService.findUserById(karshenasId);
                DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT) && (theDarkhast.getState().getId() == 642 || theDarkhast.getState().getId() == 645)  )
                {
                    theDarkhast.setKarshenasKhesarat(theUser);
                    if (nazarKarshenasKhesaratBeTafkik != null && !nazarKarshenasKhesaratBeTafkik.isEmpty())
                    {
                        theDarkhast.setNazarKarshenasKhesarat(theDarkhast.getNazarKarshenasKhesarat() != null ? theDarkhast.getNazarKarshenasKhesarat() + "|" + nazarKarshenasKhesaratBeTafkik : nazarKarshenasKhesaratBeTafkik);
                    }
                }
                else
                    theDarkhast.setKarshenas(theUser);
                theUser.getDarkhasthayeBazkharid().add(theDarkhast);
                loginService.updateUser(theUser);
                darkhastService.updateDarkhastBazkharid(theDarkhast);
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(theDarkhast.getId());
                darkhastService.transitDarkhast(result, theDarkhast.getId(), transitionId, logmessage);
                retrieve(theUser);
                return SUCCESS;
            } else if (darkhastTaghirat != null) {
                Long userId = result.getId();
                User theUser = loginService.findUserById(karshenasId);
                DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                theDarkhast.setNiazBeAzmayesh(darkhastTaghirat.getNiazBeAzmayesh());
                theDarkhast.setKarshenas(theUser);
                theUser.getDarkhasthayeTaghirat().add(theDarkhast);
                loginService.updateUser(theUser);
                darkhastService.updateDarkhastTaghirat(theDarkhast);
                darkhastTaghirat = darkhastService.findDarkhastTaghiratById(theDarkhast.getId());
                darkhastService.transitDarkhastTaghirat(result, theDarkhast.getId(), transitionId, logmessage);
                return SUCCESS;
            } else {
                return ERROR;
            }
        }
    }

    public String assignKarshenasKhesarat() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = result.getId();
            User theUser = loginService.findUserById(karshenasId);
            pishnehad = pishnehadService.findById(pishnehad.getId());
            Khesarat khesarat = pishnehad.getBimename().getKhesarats().get(0);
            khesarat.setKarshenasKhesarat(theUser);
            InsuranceServiceFactory.getWithoutSessionKhesaratService().updateKhesarat(khesarat);
            InsuranceServiceFactory.getWithoutSessionKhesaratService().transitKhesarat(result, khesarat.getId(), transitionId, logmessage);
            Transition t = pishnehadService.findTransitionById(603);
            darkhastService.transitDarkhast(result, darkhastBazkharid.getId(), 603, logmessage);
        }
        return SUCCESS;
    }

    public String deleteUndoneElhaghiye() {
//        pishnehadService.deletePishnehad(newPishnehad.getId());
        return SUCCESS;
    }

    private boolean option_changes = false;
    private boolean valid_changes = true;

    public String darkhasteElhaghie() {
        User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(pishnehad!=null && estelam!=null)
            pishnehad.setEstelam(estelam);
        Pishnehad oldPishnehad = pishnehadService.findById(pishnehad.getId());
        if (pishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki() == null)
            pishnehad.getEstelam().setDarsad_ezafe_nerkh_pezeshki(oldPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());

        PishnehadDuplicationRules mafad = new PishnehadDuplicationRules();
        mafad.setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        mafad.setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        mafad.setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        mafad.setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        mafad.setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        mafad.setSource(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi);
        mafad.setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
//        if(newShakhsBimeGozarId!=null)
//            pishnehad.getBimeGozar().setShakhs(shakhsService.findShakhs(newShakhsBimeGozarId));
        // todo: this is inefficient, move code to savePishnehadForElhaghie
        String sen1 = "", sen2 = "", tt = "";
        boolean shouldCreateBg = true;
        boolean shouldCreateBs = true;
        if (newShakhsBimeGozarId != null && !newShakhsBimeGozarId.isEmpty() && !newShakhsBimeGozarId.equals("")) {
            shouldCreateBg = false;
        }
        if (newShakhsBimeShodeId != null && !newShakhsBimeShodeId.isEmpty() && !newShakhsBimeShodeId.equals("")) {
            shouldCreateBs = false;
        }
        if (newShakhsBimeShodeId != null && !newShakhsBimeShodeId.isEmpty() && !newShakhsBimeShodeId.equals("")) {
            sen1 = pishnehad.getEstelam().getSen_bime_shode();
            sen2 = pishnehad.getEstelam().getSeneBimeie();
            tt = pishnehad.getEstelam().getTarikh_tavalod();
        }
        if
        (
            newShakhsBimeGozarId != null && !newShakhsBimeGozarId.isEmpty() && !newShakhsBimeGozarId.equals("")&&
            pishnehad.getBimeGozar().getShakhs().getChangeWithSerach()!=null && pishnehad.getBimeGozar().getShakhs().getChangeWithSerach().equals("yes")
        )
        {
            Shakhs sh=shakhsService.findShakhs(Integer.parseInt(newShakhsBimeGozarId.replaceAll(",", "").trim()));
            sh.setChangeWithSerach("yes");
            shakhsService.updateShakhs(sh);
        }
        pishnehad.getAddressBimeGozar().setId(null);
        pishnehad.getAddressBimeShode().setId(null);
        newPishnehad = pishnehadService.savePishnehadForElhaghie(pishnehad, oldPishnehad, mafad, null, user, shouldCreateBg, shouldCreateBs);
        if (newShakhsBimeGozarId != null && !newShakhsBimeGozarId.isEmpty() && !newShakhsBimeGozarId.equals("")) {
            newPishnehad.getBimeGozar().setShakhs(shakhsService.findShakhs(Integer.parseInt(newShakhsBimeGozarId.replaceAll(",", "").trim())));
            if
            (
                oldPishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(oldPishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi()) &&
                newPishnehad.getBimeGozar().getShakhs().getChangeWithSerach()==null &&
                newPishnehad.getBimeGozar().getNesbatBabimeShode().equals("خود شخص")
            )
            {
                newPishnehad.getBimeShode().setShakhs(newPishnehad.getBimeGozar().getShakhs());
                newPishnehad.setAddressBimeShode(newPishnehad.getAddressBimeGozar());
            }
        }
        if (newShakhsBimeShodeId != null && !newShakhsBimeShodeId.isEmpty() && !newShakhsBimeShodeId.equals("")) {
            newPishnehad.getBimeShode().setShakhs(shakhsService.findShakhs(Integer.parseInt(newShakhsBimeShodeId.replaceAll(",", "").trim())));
            if (oldPishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(oldPishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi()) && newPishnehad.getBimeGozar().getNesbatBabimeShode().equals("خود شخص"))
            {
                newPishnehad.getBimeGozar().setShakhs(newPishnehad.getBimeShode().getShakhs());
                newPishnehad.setAddressBimeGozar(newPishnehad.getAddressBimeShode());
            }
            newPishnehad.getEstelam().setSen_bime_shode(sen1);
            newPishnehad.getEstelam().setSeneBimeie(sen2);
            newPishnehad.getEstelam().setTarikh_tavalod(tt);
        }
        pishnehadService.updatePishnehad(newPishnehad);
        pishnehadFieldChangesList = logService.getPishnehadChangesList(oldPishnehad, newPishnehad);
        haveBareMali=false;
        if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList))
        {
            haveBareMali=true;
        }
        option_changes = false;
        valid_changes = true;
        for (PishnehadFieldChanges p : pishnehadFieldChangesList) {
            if (p.isOption()) {
                option_changes = true;
                break;
            }
            if (!p.isValid()) {
                valid_changes = false;
                message = "تغییرات داده شده مجاز نیست.";
                break;
            }
        }
        DarkhastTaghirat dt = new DarkhastTaghirat();
        dt.setNewPishnehad(newPishnehad);
        message = OmrUtil.isElhaghieValid(pishnehadFieldChangesList, dt);
        if (!message.equals("VALID"))
            valid_changes = false;
        pishnehad = oldPishnehad;
        return SUCCESS;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public List<Bimename> getBimenameha() {
        return bimenameha;
    }

    public void setBimenameha(List<Bimename> bimenameha) {
        this.bimenameha = bimenameha;
    }

    public List<Pishnehad> getReportResult() {
        return reportResult;
    }

    public void setReportResult(List<Pishnehad> reportResult) {
        this.reportResult = reportResult;
    }

    public List<Pishnehad> getViewReportResult() {
        return viewReportResult;
    }

    public void setViewReportResult(List<Pishnehad> viewReportResult) {
        this.viewReportResult = viewReportResult;
    }

    public List<DarkhastBazkharid> getDarkhasthayeBazkharid() {
        return darkhasthayeBazkharid;
    }

    public void setDarkhasthayeBazkharid(List<DarkhastBazkharid> darkhasthayeBazkharid) {
        this.darkhasthayeBazkharid = darkhasthayeBazkharid;
    }

    public List<User> getKarshenasha() {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public ArrayList<Transition> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(ArrayList<Transition> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public Long getKarshenasId() {
        return karshenasId;
    }

    public void setKarshenasId(Long karshenasId) {
        this.karshenasId = karshenasId;
    }

    public Integer getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(Integer transitionId) {
        this.transitionId = transitionId;
    }

    public String getArzesheBazkharid() {
        return arzesheBazkharid;
    }

    public void setArzesheBazkharid(String arzesheBazkharid) {
        this.arzesheBazkharid = arzesheBazkharid;
    }

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getNoeFile() {
        return noeFile;
    }

    public void setNoeFile(String noeFile) {
        this.noeFile = noeFile;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getLogicaldocIndicator() {
        return logicaldocIndicator;
    }

    public void setLogicaldocIndicator(String logicaldocIndicator) {
        this.logicaldocIndicator = logicaldocIndicator;
    }

    public String getLogmessage() {
        return logmessage;
    }

    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }

    public PaginatedListImpl<Elhaghiye> getElhaghiyeha() {
        return elhaghiyeha;
    }

    public void setElhaghiyeha(PaginatedListImpl<Elhaghiye> elhaghiyeha) {
        this.elhaghiyeha = elhaghiyeha;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public Darkhast getDarkhast() {
        return darkhast;
    }

    public void setDarkhast(Darkhast darkhast) {
        this.darkhast = darkhast;
    }

    public Pishnehad getNewPishnehad() {
        return newPishnehad;
    }

    public void setNewPishnehad(Pishnehad newPishnehad) {
        this.newPishnehad = newPishnehad;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public List<DarkhastTaghirat> getDarkhasthayeTaghirat() {
        return darkhasthayeTaghirat;
    }

    public void setDarkhasthayeTaghirat(List<DarkhastTaghirat> darkhasthayeTaghirat) {
        this.darkhasthayeTaghirat = darkhasthayeTaghirat;
    }

    public List<PishnehadFieldChanges> getPishnehadFieldChangesList() {
        return pishnehadFieldChangesList;
    }

    public void setPishnehadFieldChangesList(List<PishnehadFieldChanges> pishnehadFieldChangesList) {
        this.pishnehadFieldChangesList = pishnehadFieldChangesList;
    }

    public boolean isTimeToElamMali() {
        return timeToElamMali;
    }

    public void setTimeToElamMali(boolean timeToElamMali) {
        this.timeToElamMali = timeToElamMali;
    }

    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public PishnehadConstants getPishnehadConstants() {
        return pishnehadConstants;
    }

    public void setPishnehadConstants(PishnehadConstants pishnehadConstants) {
        this.pishnehadConstants = pishnehadConstants;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public ZamaemDarkhast getZamaemDarkhast() {
        return zamaemDarkhast;
    }

    public void setZamaemDarkhast(ZamaemDarkhast zamaemDarkhast) {
        this.zamaemDarkhast = zamaemDarkhast;
    }

    public String getBackfromupload() {
        return backfromupload;
    }

    public void setBackfromupload(String backfromupload) {
        this.backfromupload = backfromupload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getResultArzesh() {
        return resultArzesh;
    }

    public void setResultArzesh(Long resultArzesh) {
        this.resultArzesh = resultArzesh;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public Map<String, String> getWarningsMap() {
        return warningsMap;
    }

    public void setWarningsMap(Map<String, String> warningsMap) {
        this.warningsMap = warningsMap;
    }

    public ArrayList<DarkhastBazkharid> getAllTheVaams() {
        return allTheVaams;
    }

    public void setAllTheVaams(ArrayList<DarkhastBazkharid> allTheVaams) {
        this.allTheVaams = allTheVaams;
    }

    public String getBimenameIsMafqud() {
        return bimenameIsMafqud;
    }

    public void setBimenameIsMafqud(String bimenameIsMafqud) {
        this.bimenameIsMafqud = bimenameIsMafqud;
    }

    public String getHadeAksarVam() {
        return hadeAksarVam;
    }

    public void setHadeAksarVam(String hadeAksarVam) {
        this.hadeAksarVam = hadeAksarVam;
    }

    public String getMablaghGhestVam() {
        return mablaghGhestVam;
    }

    public void setMablaghGhestVam(String mablaghGhestVam) {
        this.mablaghGhestVam = mablaghGhestVam;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public ILogService getLogService() {
        return logService;
    }

    public void setLogService(ILogService logService) {
        this.logService = logService;
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

    public IDarkhastService getDarkhastService() {
        return darkhastService;
    }

    public void setDarkhastService(IDarkhastService darkhastService) {
        this.darkhastService = darkhastService;
    }

    public IKhesaratService getKhesaratService() {
        return khesaratService;
    }

    public void setKhesaratService(IKhesaratService khesaratService) {
        this.khesaratService = khesaratService;
    }

    public IEstelamService getEstelamService() {
        return estelamService;
    }

    public void setEstelamService(IEstelamService estelamService) {
        this.estelamService = estelamService;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public String getMaliatBarArzeshAfzude() {
        return maliatBarArzeshAfzude;
    }

    public void setMaliatBarArzeshAfzude(String maliatBarArzeshAfzude) {
        this.maliatBarArzeshAfzude = maliatBarArzeshAfzude;
    }

    public String getJamKolAghsatVam() {
        return jamKolAghsatVam;
    }

    public void setJamKolAghsatVam(String jamKolAghsatVam) {
        this.jamKolAghsatVam = jamKolAghsatVam;
    }

    public String getBahrePardakhti() {
        return bahrePardakhti;
    }

    public void setBahrePardakhti(String bahrePardakhti) {
        this.bahrePardakhti = bahrePardakhti;
    }

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

    private List<Ghest> ghests;

    public String printSuratVaziatGhestVam() {
        try {
            if (!loadDarkhast().equals(SUCCESS)) return ERROR;
            ghests = darkhastBazkharid.getGhestBandi().getGhestList();
            destFileDIR = realPath + destFileDIR + "SuratVaziatGhestVam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/SuratVaziatGhestVam_subreport.jrxml", realPath + "report/SuratVaziatGhestVam_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    private String bank, shomareMoshtari, shomareBimename, nameBimeGozar, nameBimeShode, mahalleSodour, nameNamayande, kodeNamayande, nahveyePardakht, shomareyeSal;
    private PishnehadReport pishnehadReport;

    public PishnehadReport getPishnehadReport() {
        return pishnehadReport;
    }

    public void setPishnehadReport(PishnehadReport pishnehadReport) {
        this.pishnehadReport = pishnehadReport;
    }

    public String printDaftarcheGhestVam() {
        try {
            if (!loadDarkhast().equals(SUCCESS)) return ERROR;
            pishnehadReport = new PishnehadReport();
            ghests = darkhastBazkharid.getGhestBandi().getGhestList();
            bimename = darkhastBazkharid.getBimename();
            shomareBimename = darkhastBazkharid.getBimename().getShomare();
            shomareVam = darkhastBazkharid.getShomareVam();
            nameBimeGozar = darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName();
            nameBimeShode = darkhastBazkharid.getNameBimeShode() + " " + darkhastBazkharid.getFamilyBimeShode();
            mahalleSodour = darkhastBazkharid.getBimename().getPishnehad().getKarshenas().getMojtamaSodoor().getKodeNamayandeKargozar();//getOstan().getCityName() + "،" + darkhastBazkharid.getBimename().getPishnehad().getNamayande().getShahr().getCityName();
            nameNamayande = darkhastBazkharid.getBimename().getPishnehad().getNamayande().getName();
            kodeNamayande = darkhastBazkharid.getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar();
            nahveyePardakht = darkhastBazkharid.getNahveyePardakhteAghsat().equals("MAHANE") ? "ماهانه" : "سه ماهه";
            pishnehadReport.setGhests(ghests);
            pishnehadReport.setShomareBimename(shomareBimename);
            pishnehadReport.setShomareVam(shomareVam);
            pishnehadReport.setNameBimeGozar(nameBimeGozar);
            pishnehadReport.setNameBimeShode(nameBimeShode);
            pishnehadReport.setMahalleSodour(mahalleSodour);
            pishnehadReport.setNameNamayande(nameNamayande);
            pishnehadReport.setKodeNamayande(kodeNamayande);
            pishnehadReport.setNahveyePardakht(nahveyePardakht);

            if (bank.equals("parsian")) {
                List<Ghest> ghests2 = new ArrayList<Ghest>();
                for (Ghest g : ghests) {
                    ghests2.add(g);
                    ghests2.add(g);
                }
                ghests = ghests2;
                destFileDIR = realPath + destFileDIR + "ghest_" + (bank.equals("parsian") ? "parsian" : "tejarat") + "_row_vam.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            } else {
                List<GhestForPrint> ghestForPrintList = new ArrayList<GhestForPrint>();
                for (Ghest g : ghests) {
                    GhestForPrint ghestForPrint = new GhestForPrint();
                    ghestForPrint.setShomareBimename(shomareBimename);
                    ghestForPrint.setAmount(g.getCredebit().getAmount());
                    ghestForPrint.setCreatedDate(g.getCredebit().getCreatedDate());
                    ghestForPrint.setCreatedTime(g.getCredebit().getCreatedTime());
                    ghestForPrint.setNameBimeGozar(nameBimeGozar);
                    ghestForPrint.setNameBimeShode(nameBimeShode);
                    ghestForPrint.setMahalleSodour(mahalleSodour);
                    ghestForPrint.setNameNamayande(nameNamayande);
                    ghestForPrint.setShomareGhest(((Integer) g.getShomareGhest()).toString());
                    ghestForPrint.setKodeNamayande(kodeNamayande);
                    ghestForPrint.setNahveyePardakht(nahveyePardakht);
                    ghestForPrint.setShomareMoshtari(g.getCredebit().getShomareMoshtari());
                    ghestForPrint.setShomareVam(shomareVam);
                    ghestForPrint.setSarresid(g.getSarresidDate());
                    ghestForPrint.setShomareyeSal(((Integer) g.getShomareSal()).toString());
                    ghestForPrint.setHashieString("نسخه بانک");
                    ghestForPrintList.add(ghestForPrint);

                    GhestForPrint ghestForPrintMoshtari = new GhestForPrint();
                    ghestForPrintMoshtari.setShomareBimename(shomareBimename);
                    ghestForPrintMoshtari.setAmount(g.getCredebit().getAmount());
                    ghestForPrintMoshtari.setCreatedDate(g.getCredebit().getCreatedDate());
                    ghestForPrintMoshtari.setCreatedTime(g.getCredebit().getCreatedTime());
                    ghestForPrintMoshtari.setNameBimeGozar(nameBimeGozar);
                    ghestForPrintMoshtari.setNameBimeShode(nameBimeShode);
                    ghestForPrintMoshtari.setMahalleSodour(mahalleSodour);
                    ghestForPrintMoshtari.setNameNamayande(nameNamayande);
                    ghestForPrintMoshtari.setShomareGhest(((Integer) g.getShomareGhest()).toString());
                    ghestForPrintMoshtari.setKodeNamayande(kodeNamayande);
                    ghestForPrintMoshtari.setNahveyePardakht(nahveyePardakht);
                    ghestForPrintMoshtari.setShomareMoshtari(g.getCredebit().getShomareMoshtari());
                    ghestForPrintMoshtari.setShomareVam(shomareVam);
                    ghestForPrintMoshtari.setSarresid(g.getSarresidDate());
                    ghestForPrintMoshtari.setShomareyeSal(((Integer) g.getShomareSal()).toString());
                    ghestForPrintMoshtari.setHashieString("نسخه مشتری");
                    ghestForPrintList.add(ghestForPrintMoshtari);

                }
                pishnehadReport.setGhestForPrintList(ghestForPrintList);
                destFileDIR = realPath + destFileDIR + "ghest_vam_tejarat.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + "report/ghest_tejarat_row_vam.jrxml", realPath + "report/ghest_tejarat_row_vam.jasper");
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
        } catch (JRException e) {
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

    private List<GhestVamMali> ghestVamMaliList;
    private Integer tedadAghsatMoavagh;
    private Long jameJaraemDirkard, jameAslAghsatAti, jameKolAghsatMoavaq, jameKolAghsatMoavaqJarime, jameKolBedehi;

    public String printSuratVaziatMaliVam() {
        try {
            if (!loadDarkhast().equals(SUCCESS)) return ERROR;
            ghests = darkhastBazkharid.getGhestBandi().getGhestList();
            ghestVamMaliList = new ArrayList<GhestVamMali>(ghests.size());
            for (Ghest ghest : ghests) {
                GhestVamMali g = new GhestVamMali();
                g.setAmount(ghest.getCredebit().getAmount());
                g.setSarresid(ghest.getSarresidDate());
                final List<Credebit> credebits = asnadeSodorService.findEtebar(ghest.getCredebit());
                if (credebits.size() > 0) {
                    g.setPardakht(credebits.get(0).getCreatedDate());
                }
                g.setShenaseGhest(ghest.getCredebit().getShenaseCredebit());
                ghestVamMaliList.add(g);
            }

            destFileDIR = realPath + destFileDIR + "SuratVaziatMaliVam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/SuratVaziatMaliVam_subreport.jrxml", realPath + "report/SuratVaziatMaliVam_subreport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public static Integer calcTedadAghsatMoavagh(DarkhastBazkharid darkhastBazkharid) {
        int count = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            for (Ghest g : darkhastBazkharid.getGhestBandi().getGhestList()) {
                if ((DateUtil.isGreaterThan(DateUtil.getCurrentDate(), g.getSarresidDate())) && !g.getCredebit().getRemainingAmount().equals("0")) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Ghest> getGhests() {
        return ghests;
    }

    public void setGhests(List<Ghest> ghests) {
        this.ghests = ghests;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
    }


    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getNameBimeGozar() {
        return nameBimeGozar;
    }

    public void setNameBimeGozar(String nameBimeGozar) {
        this.nameBimeGozar = nameBimeGozar;
    }

    public String getNameBimeShode() {
        return nameBimeShode;
    }

    public void setNameBimeShode(String nameBimeShode) {
        this.nameBimeShode = nameBimeShode;
    }

    public String getMahalleSodour() {
        return mahalleSodour;
    }

    public void setMahalleSodour(String mahalleSodour) {
        this.mahalleSodour = mahalleSodour;
    }

    public String getNameNamayande() {
        return nameNamayande;
    }

    public void setNameNamayande(String nameNamayande) {
        this.nameNamayande = nameNamayande;
    }

    public String getKodeNamayande() {
        return kodeNamayande;
    }

    public void setKodeNamayande(String kodeNamayande) {
        this.kodeNamayande = kodeNamayande;
    }

    public String getNahveyePardakht() {
        return nahveyePardakht;
    }

    public void setNahveyePardakht(String nahveyePardakht) {
        this.nahveyePardakht = nahveyePardakht;
    }

    public String getShomareyeSal() {
        return shomareyeSal;
    }

    public void setShomareyeSal(String shomareyeSal) {
        this.shomareyeSal = shomareyeSal;
    }

    public Integer getTedadAghsatMoavagh() {
        return tedadAghsatMoavagh;
    }

    public void setTedadAghsatMoavagh(Integer tedadAghsatMoavagh) {
        this.tedadAghsatMoavagh = tedadAghsatMoavagh;
    }

    public List<GhestVamMali> getGhestVamMaliList() {
        return ghestVamMaliList;
    }

    public void setGhestVamMaliList(List<GhestVamMali> ghestVamMaliList) {
        this.ghestVamMaliList = ghestVamMaliList;
    }

    public Long getJameJaraemDirkard() {
        return jameJaraemDirkard;
    }

    public void setJameJaraemDirkard(Long jameJaraemDirkard) {
        this.jameJaraemDirkard = jameJaraemDirkard;
    }

    public Long getJameAslAghsatAti() {
        return jameAslAghsatAti;
    }

    public void setJameAslAghsatAti(Long jameAslAghsatAti) {
        this.jameAslAghsatAti = jameAslAghsatAti;
    }

    public Long getJameKolAghsatMoavaq() {
        return jameKolAghsatMoavaq;
    }

    public void setJameKolAghsatMoavaq(Long jameKolAghsatMoavaq) {
        this.jameKolAghsatMoavaq = jameKolAghsatMoavaq;
    }

    public Long getJameKolAghsatMoavaqJarime() {
        return jameKolAghsatMoavaqJarime;
    }

    public void setJameKolAghsatMoavaqJarime(Long jameKolAghsatMoavaqJarime) {
        this.jameKolAghsatMoavaqJarime = jameKolAghsatMoavaqJarime;
    }

    public Long getJameKolBedehi() {
        return jameKolBedehi;
    }

    public void setJameKolBedehi(Long jameKolBedehi) {
        this.jameKolBedehi = jameKolBedehi;
    }

    public String getTelephonSabetBimeShodeKod() {
        return telephonSabetBimeShodeKod;
    }

    public void setTelephonSabetBimeShodeKod(String telephonSabetBimeShodeKod) {
        this.telephonSabetBimeShodeKod = telephonSabetBimeShodeKod;
    }

    public String getTelephonSabetBimeShode() {
        return telephonSabetBimeShode;
    }

    public void setTelephonSabetBimeShode(String telephonSabetBimeShode) {
        this.telephonSabetBimeShode = telephonSabetBimeShode;
    }

    public Long getJameAghsatMoavaghBimename() {
        return jameAghsatMoavaghBimename;
    }

    public void setJameAghsatMoavaghBimename(Long jameAghsatMoavaghBimename) {
        this.jameAghsatMoavaghBimename = jameAghsatMoavaghBimename;
    }

    public String getResultDarsad() {
        return "شما در اینبازه زمانی تا سقف" + resultDarsad + " درصد اندوخته قطعی سرمايه گذاري بيمه نامه را برداشت نمایید.";
    }

    public void setResultDarsad(String resultDarsad) {
        this.resultDarsad = resultDarsad;
    }

    public String getMablagh2() {
        return mablagh2;
    }

    public void setMablagh2(String mablagh2) {
        this.mablagh2 = mablagh2;
    }

    public boolean getOption_changes() {
        return option_changes;
    }

    public void setOption_changes(boolean option_changes) {
        this.option_changes = option_changes;
    }

    public Integer getWhenApply() {
        return whenApply;
    }

    public void setWhenApply(Integer whenApply) {
        this.whenApply = whenApply;
    }


    public List<Gharardad> getGrouhha() {
        if (grouhha == null) {
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//            grouhha = new LinkedList<Gharardad>();
//            User resultUser = loginService.findUserByUsername(username);
//            grouhha = gharardadService.findAllByNamayande(resultUser.getNamayandegi().getId());
            grouhha = gharardadService.findAll();

        }
        return grouhha;
    }

    public void setGrouhha(List<Gharardad> grouhha) {
        this.grouhha = grouhha;
    }

    public List<Tarh> getTarhs() {
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public Elhaghiye getElhaghiye() {
        return elhaghiye;
    }

    public void setElhaghiye(Elhaghiye elhaghiye) {
        this.elhaghiye = elhaghiye;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public ITransitionLogService getTransitionLogService() {
        return transitionLogService;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public List<User> getBazaryabs() {
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    public boolean isTozih() {
        return tozih;
    }

    public void setTozih(boolean tozih) {
        this.tozih = tozih;
    }

    public HashMap<String, Object> getValidationConstants() {
        return validationConstants;
    }

    public void setValidationConstants(HashMap<String, Object> validationConstants) {
        this.validationConstants = validationConstants;
    }

    public boolean isValid_changes() {
        return valid_changes;
    }

    public void setValid_changes(boolean valid_changes) {
        this.valid_changes = valid_changes;
    }

    public String getDarkhastLoadMessage() {
        return darkhastLoadMessage;
    }

    public void setDarkhastLoadMessage(String darkhastLoadMessage) {
        this.darkhastLoadMessage = darkhastLoadMessage;
    }

    public Long getAndukhte() {
        return andukhte;
    }

    public void setAndukhte(Long andukhte) {
        this.andukhte = andukhte;
    }

    public String checkTarikhAsar()
    {
        Bimename b =pishnehadService.findBimenameById(bimename.getId());
        String bigTarikhAsar = b.getLastTarikhAsarElhaghie();
        if (bigTarikhAsar!=null && !bigTarikhAsar.equals("")&& !DateUtil.isGreaterThanOrEqual(tarikhAsar, b.getTarikhShorou()) && !DateUtil.isGreaterThan(tarikhAsar, bigTarikhAsar))
            viewTrueFalse = new StringBufferInputStream("false");
        else
            viewTrueFalse = new StringBufferInputStream("true");
        return SUCCESS;
    }
    public String saveSaleAsarAjaxly() {
        try {
            DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            pishnehadFieldChangesList = logService.getPishnehadChangesList(theDarkhast.getOldPishnehad(), theDarkhast.getNewPishnehad());
            if (OmrUtil.isElhaghieTaghiratMali(pishnehadFieldChangesList))
            {
                if (theDarkhast.getHaveBareMali() == null)
                {
                    theDarkhast.setHaveBareMali(true);
                    darkhastService.updateDarkhastTaghirat(theDarkhast);
                }
            }
            else
            {
                if (theDarkhast.getHaveBareMali() != null && theDarkhast.getHaveBareMali().equals(true))
                {
                    theDarkhast.setHaveBareMali(false);
                    darkhastService.updateDarkhastTaghirat(theDarkhast);
                }
            }
            Elhaghiye theElhaghiye = theDarkhast.getDarkhast().getElhaghiye();
            if (theElhaghiye.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE))
                return SUCCESS;
            if (theDarkhast.getHaveBareMali()==null || !theDarkhast.getHaveBareMali())
            {
                if(elhaghiye.getTarikhAsar()!=null)
                {
                    String bigTarikhAsar=theElhaghiye.getBimename().getLastTarikhAsarElhaghie();
                    if(!DateUtil.isGreaterThanOrEqual(elhaghiye.getTarikhAsar(),theElhaghiye.getBimename().getTarikhShorou()))
                        addActionMessage("err_before_start_date");
                    else if(bigTarikhAsar!=null && !DateUtil.isGreaterThan(elhaghiye.getTarikhAsar(),bigTarikhAsar))
                        addActionMessage("err_befor_asar_date");
                    else
                    {
                        theElhaghiye.setTarikhAsar(elhaghiye.getTarikhAsar());
                        darkhastService.updateElhaghiye(theElhaghiye);
                        darkhastService.updateDarkhastTaghirat(theDarkhast);
                        addActionMessage("done");
                    }
                }
                return SUCCESS;
            }
            theElhaghiye.setTarikhAsar(OmrUtil.calculateTarikhAsar(whenApply, theElhaghiye.getBimename().getTarikhShorou()));
            darkhastService.updateElhaghiye(theElhaghiye);
            theDarkhast.setWhenApply(whenApply);

            darkhastService.updateDarkhastTaghirat(theDarkhast);
            addActionMessage("done");
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            addActionMessage("error");
            return SUCCESS;
        }
    }


    public String getTransitionSelector() {
        return transitionSelector;
    }

    public void setTransitionSelector(String transitionSelector) {
        this.transitionSelector = transitionSelector;
    }

    public boolean isKhesaratAction() {
        return khesaratAction;
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

    public List<User> getKarshenasKhesaratha() {
        return karshenasKhesaratha;
    }

    public void setKarshenasKhesaratha(List<User> karshenasKhesaratha) {
        this.karshenasKhesaratha = karshenasKhesaratha;
    }

    public List<ListBimenameTaghirVM> getListBimenameTaghirVM() {
        return listBimenameTaghirVM;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

