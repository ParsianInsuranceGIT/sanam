package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.Core.ConstantPaging;
import com.bitarts.parsian.dao.StateDAO;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.karmozd.BlackList;
import com.bitarts.parsian.model.karmozd.Karmozd;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.epayment.PgwStatus;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.service.karmozd.IKarmozdService;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import com.bitarts.parsian.viewModel.BahreMandiVM;
import com.bitarts.parsian.webservice.epayment.EShopServiceLocator;
import com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType;
import com.bitarts.parsian.webservice.sms.SMSSender;
import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import logicaldoc.auth.AuthService_ServiceLocator;
import logicaldoc.doc.DocumentService;
import logicaldoc.doc.DocumentServiceImplServiceLocator;
import logicaldoc.doc.WsDocument;
import logicaldoc.doc.holders.WsDocumentHolder;
import logicaldoc.folder.FolderService;
import logicaldoc.folder.FolderServiceImplServiceLocator;
import logicaldoc.folder.WsFolder;
import logicaldoc.folder.holders.WsFolderHolder;
import org.apache.axis.holders.UnsignedByteHolder;
import org.apache.axis.types.UnsignedByte;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.LongHolder;
import java.io.*;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Feb 16, 2011
 * Time: 4:25:50 PM
 */

public class LoginAction extends BaseAction implements ServletContextAware, ServletRequestAware {
    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private IUserService userService;
    private FileService fileService;
    private ILoginService loginService;
    private IPishnehadService pishnehadService;
    private IEstelamService estelamService;
    private INamayandeService namayandeService;
    private IClinicService clinicService;
    private IDarkhastService darkhastService;
    private IAsnadeSodorService asnadeSodorService;
    private IQueryService queryService;
    private ISequenceService sequenceService;
    private IConstantItemsService constantItemsService;
    private IShakhsService shakhsService;
    private String username;
    private String password;
    private String pishnehadId;
    private String transitionId;
    private IGharardadService gharardadService;
    private List<PezeshkSabtNazar> nazaraat;
    private PezeshkSabtNazar pezeshkSabtNazar;
    private String logmessage;
    private Pishnehad pishnehad;
    private Bimename bimename;
    private File[] uploadedPishnehads;
    private String uploadedPishnehadsNames;
    private File[] uploadedScannedFishs;
    private String uploadedScannedFishsNames;
    private File[] uploadedExtras;
    private String uploadedExtrasNames;
    private List<Clinic> clinics;
    private InputStream inputStream;
    private String contentType;
    private String fileName;
    private String bufferSize;
    private String fileId;
    private NaghsPishnehad naghsPishnehad;
    private Fish fish;
    private Long karshenasId;
    private boolean correctCalculation;
    private List<FRs> fRsList;
    private File uploadedFile;
    private String noeFile;
    private String shomareSafhe;
    private String tozihat;
    private String uploadedFileName;
    private Zamaem zamaem;
    private BankInfo bankInfo;
    private List<Credebit> foundFishes;
    private String theFishSelecterHolder;
    private String authorityId;
    private String bankResponse;
    private String logicaldocIndicator;
    private String selectedTab = "";
    //    private int page = 1;
//    private int pageNum = 1;
    private PaginatedListImpl<Bimename> bimenameha;
    private PaginatedListImpl<BimenameVM> bimenameVMPaginatedList;
    private String privateMessage;
    private List<DarkhastBazkharid> darkhasthayeBazkharid;
    private String shenaseIjadShode;
    private PaginatedListImpl<Elhaghiye> elhaghiyeha;
    private boolean nosession;
    private List<DarkhastTaghirat> darkhasthayeTaghirat;
    private DarkhastTaghirat darkhastTaghirat;
    private DarkhastBazkharid darkhastBazkharid;
    private boolean errorPardakht;
    private boolean errorPardakhtShenasedar;
    private Map<String, String> errorsMap;
    private Credebit credebit;
    private List<Fish> fishs;
    private String backfromupload = "false";
    private String backfromuploadKesarat = "false";
    private SharayeteJadid sharayeteJadid;
    private ElameHesab elameHesab;
    private List<State> states;
    private List<User> karshenasha;
    private List<City> ostanha;
    private PishnehadSearch pishnehadSearch;
    private GharardadSearch gharardadSearch;
    private List<BazarYab> bazaryabs;
    private List<EmzaKonande> emzaKonandeList;
    private Estelam estelam;
    private HashMap<String, String> messagesMap;
    private static final String ROLE_KARBAR_MALI = "ROLE_KARBAR_MALI";
    private static final String ROLE_BAZARYAB = "ROLE_BAZARYAB";
    private static final String ROLE_NAMAYANDE = "ROLE_NAMAYANDE";
    private static final String ROLE_KARSHENAS_SODUR = "ROLE_KARSHENAS_SODUR";
    private static final String ROLE_MALI_OPERATOR = "ROLE_MALI_OPERATOR";
    private int theId;
    private PaginatedListImpl<Pishnehad> viewReportResultPaginated;
    private PaginatedListImpl<Pishnehad> reportResultPaginated;
    private PaginatedListImpl<PishnehadsVM> pishnehadsVMPaginatedList;
    private PaginatedListImpl<PishnehadsVM> allPishnehadsVMPaginatedList;
    private PaginatedListImpl<DarkhastsVM> darkhastsVMPaginatedList;
    private HttpServletRequest request;
    private String tableId = "";
    private int pageSize;
    private PaginatedListImpl<Pishnehad> searchAslPaginated;
    private PaginatedListImpl<Pishnehad> searchViewPaginated;
    private String taeedCheckBox;
    private String darsad_ezafe;
    private IKhesaratService khesaratService;
    private String showTaghsit = "";
    private String fromLogin;
    private String fromReadOnlyMode;
    private StateDAO stateDAO;
    private String azTarikheDarkhast, taTarikheDarkhast;
    private String nameKarshenas;
    private Integer subsetState;
    private List<Darkhast> darkhasts;
    private String codeMovaghat;
    private Namayande namayande;
    private String darkhastType, karshenas;
    private String darkhastState;
    private List<Gharardad> grouhHa;
    private Long groupId;
    private ElhaghiyeSearch elhaghiyeSearch;
    private PaginatedListImpl<Darkhast> alldarkhastsPgList;
    private SearchDarkhasts searchDarkhast;
    private IKarmozdService karmozdService;
    private PaginatedListImpl<Karmozd> paginatedListKarmozds;
    private PaginatedListImpl<BlackList> paginatedtblBlackList;
    private PishnehadReport pishnehadReport;
    private Credebit etebar;
    private boolean pardakhtInternetiAction;
    private String paymentOrLogin;
    private String actionUrl;
    private Boolean isSearch;
    private String parametersUrl;
    private String kaptcha;
    private InputStream enableValidationUser;
    private String userIdChangePass;
    private KhesaratVM khesaratVMSrch;
    private long ghestAmount;
    private boolean fishEQghest;
    private PaginatedListImpl<BahreMandiVM> bahreMandiVMPaginatedList;
    private String srch_bhrmndi;
    private BahreMandiVM bahreMandiVMS;
    private String epaymentHost;
    private boolean elhaghieReq;
    private String usernameForget;
    private String mobileForget;
    private String codeRamzForget;
    private boolean readOnlyMode, khesaratAction;
    private List<State> statesBahreMandi;
    private List<User> userList;
    private User userTemp;
    private boolean findUserAction;
    private GhestBandi gb;
    private Sanad sanad;
    private boolean userKrtbl;
     //b-h
    private String daftarType;
    private InputStream hasDaftareNamayande;
    private String j_username;
    private int daftar_id;

    public int getDaftar_id() {
        return daftar_id;
    }

    public void setDaftar_id(int daftar_id) {
        this.daftar_id = daftar_id;
    }

    public String getJ_username() {
        return j_username;
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    public InputStream getHasDaftareNamayande() {
        return hasDaftareNamayande;
    }

    public void setHasDaftareNamayande(InputStream hasDaftareNamayande) {
        this.hasDaftareNamayande = hasDaftareNamayande;
    }

    public String getDaftarType() {
        return daftarType;
    }

    public void setDaftarType(String daftarType) {
        this.daftarType = daftarType;
    }

    public User getUserTemp()
    {
        return userTemp;
    }

    public void setUserTemp(User userTemp)
    {
        this.userTemp = userTemp;
    }

    public boolean isUserKrtbl() {
        return userKrtbl;
    }

    public void setUserKrtbl(boolean userKrtbl) {
        this.userKrtbl = userKrtbl;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public GhestBandi getGb() {
        return gb;
    }

    public void setGb(GhestBandi gb) {
        this.gb = gb;
    }

    public boolean isFindUserAction() {
        return findUserAction;
    }

    public void setFindUserAction(boolean findUserAction) {
        this.findUserAction = findUserAction;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<State> getStatesBahreMandi() {
        return statesBahreMandi;
    }

    public void setStatesBahreMandi(List<State> statesBahreMandi) {
        this.statesBahreMandi = statesBahreMandi;
    }

    public boolean getElhaghieReq() {
        return elhaghieReq;
    }

    public void setElhaghieReq(boolean elhaghieReq) {
        this.elhaghieReq = elhaghieReq;
    }

    public String getEpaymentHost() {
        return epaymentHost;
    }

    public void setEpaymentHost(String epaymentHost) {
        this.epaymentHost = epaymentHost;
    }

    public String getSrch_bhrmndi() {
        return srch_bhrmndi;
    }

    public void setSrch_bhrmndi(String srch_bhrmndi) {
        this.srch_bhrmndi = srch_bhrmndi;
    }

    public BahreMandiVM getBahreMandiVMS() {
        return bahreMandiVMS;
    }

    public void setBahreMandiVMS(BahreMandiVM bahreMandiVMS) {
        this.bahreMandiVMS = bahreMandiVMS;
    }

    public PaginatedListImpl<BahreMandiVM> getBahreMandiVMPaginatedList() {
        return bahreMandiVMPaginatedList;
    }

    public void setBahreMandiVMPaginatedList(PaginatedListImpl<BahreMandiVM> bahreMandiVMPaginatedList) {
        this.bahreMandiVMPaginatedList = bahreMandiVMPaginatedList;
    }

    public boolean getFishEQghest() {
        return fishEQghest;
    }

    public void setFishEQghest(boolean fishEQghest) {
        this.fishEQghest = fishEQghest;
    }

    public long getGhestAmount() {
        return ghestAmount;
    }

    public void setGhestAmount(long ghestAmount) {
        this.ghestAmount = ghestAmount;
    }

    public KhesaratVM getKhesaratVMSrch() {
        return khesaratVMSrch;
    }

    public void setKhesaratVMSrch(KhesaratVM khesaratVMSrch) {
        this.khesaratVMSrch = khesaratVMSrch;
    }

    public String getUserIdChangePass() {
        return userIdChangePass;
    }

    public void setUserIdChangePass(String userIdChangePass) {
        this.userIdChangePass = userIdChangePass;
    }

    public InputStream getEnableValidationUser() {
        return enableValidationUser;
    }

    public void setEnableValidationUser(InputStream enableValidationUser) {
        this.enableValidationUser = enableValidationUser;
    }

    public Boolean isSearch() {
        return isSearch;
    }

    public void setSearch(Boolean search) {
        isSearch = search;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public String getParametersUrl() {
        return parametersUrl;
    }

    public void setParametersUrl(String parametersUrl) {
        this.parametersUrl = parametersUrl;
    }

    public String getDarkhastState() {
        return darkhastState;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setDarkhastState(String darkhastState) {
        this.darkhastState = darkhastState;
    }

    public String getPaymentOrLogin() {
        return paymentOrLogin;
    }

    public void setPaymentOrLogin(String paymentOrLogin) {
        this.paymentOrLogin = paymentOrLogin;
    }

    public boolean isPardakhtInternetiAction() {
        return pardakhtInternetiAction;
    }

    public void setPardakhtInternetiAction(boolean pardakhtInternetiAction) {
        this.pardakhtInternetiAction = pardakhtInternetiAction;
    }

    public Credebit getEtebar() {
        return etebar;
    }

    public void setEtebar(Credebit etebar) {
        this.etebar = etebar;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.shakhsService = (IShakhsService) wac.getBean(IShakhsService.SERVICE_NAME);
        this.stateDAO = (StateDAO) wac.getBean("stateDAO");
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.clinicService = (IClinicService) wac.getBean(IClinicService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean(IDarkhastService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean((ISequenceService.SERVICE_NAME));
        this.khesaratService = (IKhesaratService) wac.getBean("khesaratService");
        this.userService = (IUserService) wac.getBean("userService");
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        gharardadService = (IGharardadService) wac.getBean("gharardadService");
        fileService = (FileService) wac.getBean("fileService");
        namayandeService = (INamayandeService) wac.getBean("namayandeService");
        this.karmozdService = (IKarmozdService) wac.getBean(IKarmozdService.SERVICE_NAME);
        this.queryService = (IQueryService) wac.getBean(IQueryService.SERVICE_NAME);
    }

    public String findEmzaKonande() {
        User user = getUser();
        if (pishnehad != null) {
            Pishnehad p = pishnehadService.loadPishnehadById(pishnehad.getId());
            Long maxCapital = Long.parseLong(p.getEstelam().getSarmaye_paye_fot().replaceAll(",", ""));
            if (elhaghieReq)
                maxCapital = null;
            emzaKonandeList = loginService.findAllEmzaKonandegan(user.getFirstName(), user.getPersonalCode(), maxCapital);
        } else {
            emzaKonandeList = loginService.findAllEmzaKonandegan(user.getFirstName(), user.getPersonalCode(), null);
        }

        return SUCCESS;
    }

    public String findUsers() {
        findUserAction = true;
        userList = loginService.findUsersByPersonalCodeOrName(getUser().getPersonalCode(), getUser().getFirstName(), getUser().getLastName());
        return SUCCESS;
    }
    //---------------------------------------------login----------------------------------------------------------------//
    public String login()
    {

        setSearch(true);
        User theUser = getUser();  //user jari
        if (theUser == null || theUser.getId() == -1)  //agar karbare jari vojood nadarad
        {
            nosession = true;
            return Constant.NOSESSION;
        }
        else  //agar karbare jari vojood darad
        {

            String roleColor = "";
            Set<Role> userRoles = theUser.getRoles();  //role haye usere jari
            boolean validPassword = false;
            boolean validMobile = false;
            boolean namayande = false;
            boolean userIsMali = false;
            boolean userKartabl = false;
            boolean pezeshk = false;
            boolean karmozd = false;
            boolean bazar = false;
            boolean role_karmozd = false;
            boolean userIsOperatorMali = false;


            //b-h
           setDaftar_id(theUser.getDaftar().getId());
//            putToSession("daftar_id",daftar_id);
            System.out.println("daftar_id"+getDaftar_id());
            // taa injaaa
            if (theUser.getValidPass() != null && theUser.getValidPass())
                validPassword = true;  //password motabar ast
            if (theUser.getMobile() != null && theUser.getMobile().length() > 0)
                validMobile = true; //shomare mobile darad

            for (Role userRole : userRoles) //baraye tak take rolehaye karbare jari
            {
                if (userRole.getRoleName().equalsIgnoreCase("ROLE_NAMAYANDE"))
                {
                    namayande = true;  //role namayande ra darad
                }
                else if (userRole.getRoleName().equalsIgnoreCase(ROLE_KARBAR_MALI))
                {
                    userIsMali = true;  //role karbare mali ra darad
                }
                if (userRole.getRoleName().equalsIgnoreCase("ROLE_USER_KARTABL"))
                {
                    userKartabl = true; //role kartabl ra darad
                }
                if (userRole.getRoleName().equalsIgnoreCase(Constant.ROLE_MALI_OPERATOR))
                {
                    userIsOperatorMali = true; //?
                    System.out.println(theUser.getFullName());
                }
                if (userRole.getId() != 2)  //role karbare addi
                {
                    roleColor = userRole.getRoleColor();
                }
                if (userRole.getId() == 13) //ROLE_HESAB_FIMABEYN
                {
                    pezeshk = true;
                }
                if (userRole.getId() == 220)//?
                { //todo set id
                    karmozd = true;
                }
                if (userRole.getId() == 20) //role bazar
                {
                    bazar = true;
                }
                if (userRole.getRoleName().equalsIgnoreCase("ROLE_KARMOZD") || userRole.getRoleName().equalsIgnoreCase("ROLE_KARMOZD_NAMAYANDE"))
                {
                    role_karmozd = true; //?
                }
            } //end for

            if (getFromSession("roleColor") == null) //agar role rang nadarad
                fromLogin = "yes"; //rang nadarad
            else
                fromLogin = "no"; //rang darad
            getSession().put("roleColor", roleColor);

            if (!validPassword) //agar password motabar nist
            {
                actionUrl = "/changeUserPassword?userTemp.id=" + theUser.getId();
                return "customUrl";
            }
            if (!validMobile) //agar shomare mobile motabar nist
            {
                actionUrl = "/setMobileNumber?user.id=" + theUser.getId();
                return "setMobileNumber";
            }

            if (userIsMali) //agar role karbare mali radarad
            {
                if (namayande) //agar role namayande ra darad
                    return "role_mali_namayande";
                else
                    return KARBAR_MALI_LOGIN;
            }
            else if (userKartabl)  //agar role kartabl ra darad
            {
                if (theUser.getUsername().equals("1111111111") || theUser.getUsername().equals("2222222222") || theUser.getUsername().equals("3333333333") || theUser.getUsername().equals("4444444444") ||
                        theUser.getUsername().equals("5555555555") || theUser.getUsername().equals("6666666666") || theUser.getUsername().equals("7777777777") || theUser.getUsername().equals("8888888888") ||
                        theUser.getUsername().equals("9999999999")) //in argham nemitavanand name karbari bashand
                {
                    actionUrl = "/jsp/user/page_login.jsp?error=invalid_bg";
                    SecurityContextHolder.clearContext();
                    return "customUrl";
                }
                return "userKartabl";
            }
            else if (pezeshk) //agar role pezeshk ra darad
            {
                return "pezeshk";
            }
            else if (karmozd)//agar role karmozd ra darad
            {
                return "karmozd";
            }
            else if (bazar) //agar role bazar ra darad
            {
                return "bazar";
            }
            else if (role_karmozd) //?
            {
                return "role_karmozd";
            }
            else if (userIsOperatorMali) //??
            {
                return "operatorMali";
            }
            else //agar rolehaye karbare mali va kartabl va pezeshk va karmozd va bazar va ? va ?? ra nadarad
            {
                retrieve();
                return SUCCESS;
            }
        } //end agar karbare jari vojood darad
    }
    //------------------------------------------------------------------------------------------------------------------//
    public String logMeOut() {
        getSession().clear();
        return SUCCESS;
    }

    public String bimenameCheck() {
        Bimename b = null;
        b = pishnehadService.findBimenameByShomare(shomareBimename);
        if (b != null && b.getReadyToShow().equals("yes")) {
            if (paymentOrLogin.equals("login"))
                return SUCCESS;
            else
                return "success_epayment";
        } else {
            if (paymentOrLogin.equals("login"))
                return ERROR;
            else
                return "error_epayment";
        }
    }

    public String userCheckLogin() {
        paymentOrLogin = "login";
        return SUCCESS;
    }

    public String userCheckPayment() {
        paymentOrLogin = "payment";
        return SUCCESS;
    }

    public String searchForBimename() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            PaginatedListImpl<Darkhast> darkhastPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());

            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);

            String nameBimeGozar = null;
            String familyBimeGozar = null;
            String kodeMelliBimeGozar = null;

            String nameBimeShode = null;
            String familyBimeShode = null;
            String kodeMelliBimeShode = null;

            if (pishnehad != null) {
                if (pishnehad.getBimeGozar() != null) {
                    nameBimeGozar = pishnehad.getBimeGozar().getShakhs().getName();
                    familyBimeGozar = pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi();
                    kodeMelliBimeGozar = pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi();
                }
                if (pishnehad.getBimeShode() != null) {
                    nameBimeShode = pishnehad.getBimeShode().getShakhs().getName();
                    familyBimeShode = pishnehad.getBimeShode().getShakhs().getNameKhanevadegi();
                    kodeMelliBimeShode = pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi();
                }
            }
            State st = null;
            if (bimename.getState().getId() != null && bimename.getState().getId() > 0)
                st = stateDAO.findById(bimename.getState().getId());
            //namayandegi,vahedSodor,karbareSabtKonande???        stateDAO.findById(bimename.getState().getId())
//            bimenameha = new PaginatedListImpl<Bimename>();
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            if (isExport()) {
//                bimenameha.setPageNumber(0);
//                bimenameha.setObjectsPerPage(Integer.MAX_VALUE);
                bimenameVMPaginatedList.setPageNumber(0);
                if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                    bimenameVMPaginatedList.setObjectsPerPage(0);
                else
                    bimenameVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
//                bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//                bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
                if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                    bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
                else
                    bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }
            paginatedListKarmozds = new PaginatedListImpl<Karmozd>();
            paginatedtblBlackList = new PaginatedListImpl<BlackList>();
            paginatedListKarmozds.setPageNumber(0);
            paginatedListKarmozds.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            paginatedtblBlackList.setPageNumber(0);
            paginatedtblBlackList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            paginatedListKarmozds = karmozdService.findAllKarmozds(paginatedListKarmozds, user);
            paginatedtblBlackList = karmozdService.loadAllBlackListPaging(paginatedtblBlackList);
//            bimenameha = pishnehadService.searchBimenamePaginated(bimenameha, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar, nameBimeShode, familyBimeShode, kodeMelliBimeShode, bimename.getShomare(), tarikhSodur, bimename.getTarikhSodour(), null, null,
//                    st, subsetState, null, pishnehad.getEstelam().getNahve_pardakht_hagh_bime(), user, pishnehad.getEstelam().getSarmaye_paye_fot().equals("0") ? null : pishnehad.getEstelam().getSarmaye_paye_fot(),
//                    pishnehad.getRadif(), pishnehad.getNamayande().getId(), pishnehad.getNamayande().getVahedSodur().getId(), pishnehad.getEstelam().getNoe_tarh(), pishnehad.getNoeGharardad(), pishnehad.getKarshenas().getFirstName(), groupId, pishnehad.getOptions().equals("CODE_MOVAGHAT")?true:false);
            bimenameVMPaginatedList = pishnehadService.searchBimenamePaginated(bimenameVMPaginatedList, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar, nameBimeShode, familyBimeShode, kodeMelliBimeShode, bimename.getShomare(), tarikhSodur, bimename.getTarikhSodour(), null, null,
                    st, subsetState, null, pishnehad.getEstelam().getNahve_pardakht_hagh_bime(), user, pishnehad.getEstelam().getSarmaye_paye_fot().equals("0") ? null : pishnehad.getEstelam().getSarmaye_paye_fot(),
                    pishnehad.getRadif(), pishnehad.getNamayande().getId(), pishnehad.getNamayande().getVahedSodur().getId(), pishnehad.getEstelam().getNoe_tarh(), pishnehad.getNoeGharardad(), pishnehad.getKarshenas().getFirstName(), groupId, pishnehad.getOptions().equals("CODE_MOVAGHAT") ? true : false);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setFullListSize(5);
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);
            states = pishnehadService.findAllSatetsForPishnehadSystem();
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            reportResult = new ArrayList<Pishnehad>();
            return SUCCESS;
        }
    }

    public String searchForAllDarkhasts() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//                Collections.sort(elhaghiyeha);
//            bimenameha = new PaginatedListImpl<Bimename>();
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);

//            String shomare=bimename.getShomare();
//            String nameBimeGozar = null;
//            String familyBimeGozar = null;
//            String kodeMelliBimeGozar = null;

//            String nameBimeShode = null;
//            String familyBimeShode = null;
//            String kodeMelliBimeShode = null;

            PaginatedListImpl<Darkhast> darkhastPaginatedListt = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = new PaginatedListImpl<Darkhast>();
            if (isExport()) {
                alldarkhastsPgList.setPageNumber(0);
                alldarkhastsPgList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                alldarkhastsPgList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber) - 1);
                alldarkhastsPgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }

//             darkhastPaginatedListt=darkhastService.searchDarkhasts(user, darkhastType,bimename.getShomare(),namayande.getId(),karshenas, azTarikheDarkhast, taTarikheDarkhast,
//                    pishnehad.getBimeGozar().getShakhs().getName(),pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi(),
//                    pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(),pishnehad.getBimeShode().getShakhs().getName(),
//                    pishnehad.getBimeShode().getShakhs().getNameKhanevadegi(),pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi());
            searchDarkhast.setDarkhastStateList(StringUtil.convertStringToList(searchDarkhast.getDarkhastState(), ','));
            alldarkhastsPgList = darkhastService.searchHameyeDarkhastha(searchDarkhast, user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedListt);
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);

            reportResult = new ArrayList<Pishnehad>();
            return SUCCESS;
        }
    }

    public String searchForDarkhasts() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//                Collections.sort(elhaghiyeha);
//            bimenameha = new PaginatedListImpl<Bimename>();
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);

//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

            String shomare = bimename.getShomare();
            String nameBimeGozar = null;
            String familyBimeGozar = null;
            String kodeMelliBimeGozar = null;

            String nameBimeShode = null;
            String familyBimeShode = null;
            String kodeMelliBimeShode = null;

//            if(pishnehad != null)
//            {
//                if(pishnehad.getBimeGozar() != null)
//                {
//                    nameBimeGozar = pishnehad.getBimeGozar().getShakhs().getName();
//                    familyBimeGozar=pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi();
//                    kodeMelliBimeGozar=pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi();
//                }
//                if(pishnehad.getBimeShode() != null)
//                {
//                    nameBimeShode = darkhastBazkharid.getNameBimeShode();
//                    familyBimeShode=darkhastBazkharid.getFamilyBimeShode();
//                    kodeMelliBimeShode=darkhastBazkharid.getKodeMeliBimeShode();
//                }
//            }
//            darkhasthayeBazkharid=darkhastService.searchDarkhastBazkharidsPaginated(shomare,azTarikheDarkhast,taTarikheDarkhast,null,nameBimeGozar,familyBimeGozar,kodeMelliBimeGozar,nameBimeShode,
//                                                                                    familyBimeShode,kodeMelliBimeShode,nameKarshenas);

//            darkhasthayeTaghirat=darkhastService.searchDarkhastTaghiratsPaginated(shomare,azTarikheDarkhast,taTarikheDarkhast,null,nameBimeGozar,familyBimeGozar,kodeMelliBimeGozar,nameBimeShode,
//                                                                                  familyBimeShode, kodeMelliBimeShode, nameKarshenas);
            PaginatedListImpl<Darkhast> darkhastPaginatedListt = new PaginatedListImpl<Darkhast>();
            if (isExport()) {
                darkhastPaginatedListt.setPageNumber(0);
                darkhastPaginatedListt.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                darkhastPaginatedListt.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
                darkhastPaginatedListt.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }

            darkhastPaginatedListt = darkhastService.searchDarkhasts(darkhastPaginatedListt, user, darkhastType, bimename.getShomare(), namayande.getId(), karshenas, azTarikheDarkhast, taTarikheDarkhast,
                    pishnehad.getBimeGozar().getShakhs().getName(), pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi(),
                    pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(), pishnehad.getBimeShode().getShakhs().getName(),
                    pishnehad.getBimeShode().getShakhs().getNameKhanevadegi(), pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(), StringUtil.convertStringToList(darkhastState, ','));

            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedListt);
            reportResult = new ArrayList<Pishnehad>();
            return SUCCESS;
        }

    }

    public String searchForElhaghie() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            PaginatedListImpl<Darkhast> darkhastPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            int pageNumber = PagingUtil.getPageNumberFromContext("d-49685-p");
            elhaghiyeha.setPageNumber(pageNumber - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.searchForElhaghiye(elhaghiyeSearch, elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//            bimenameha = new PaginatedListImpl<Bimename>();
//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

            states = pishnehadService.findAllSatetsForPishnehadSystem();
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            ostanha = constantItemsService.getOstans();
            reportResult = new ArrayList<Pishnehad>();

//            searchAslPaginated = new PaginatedListImpl<Pishnehad>();
//            searchAslPaginated.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
//            searchAslPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            PaginatedListImpl<Pishnehad> theFoundPishnehads = pishnehadService.findAllowedPishnehads(user, searchAslPaginated);
//                        getSession().put("reportResult", theFoundPishnehads);
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);
            return SUCCESS;
        }
    }

    public String searchForPishnehads() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            PaginatedListImpl<Darkhast> darkhastPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//            bimenameha = new PaginatedListImpl<Bimename>();
//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);

            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

            states = pishnehadService.findAllSatetsForPishnehadSystem();
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            ostanha = constantItemsService.getOstans();
            reportResult = new ArrayList<Pishnehad>();

//            searchAslPaginated = new PaginatedListImpl<Pishnehad>();
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            if (isExport()) {
//                searchAslPaginated.setPageNumber(0);
//                searchAslPaginated.setObjectsPerPage(Integer.MAX_VALUE);
                pishnehadsVMPaginatedList.setPageNumber(0);
                pishnehadsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
//                searchAslPaginated.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
//                searchAslPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
                pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }
//            PaginatedListImpl<Pishnehad> theFoundPishnehads = pishnehadService.findPishnehadsByPishnehadSearch(pishnehadSearch, user, searchAslPaginated);
//            getSession().put("reportResult", theFoundPishnehads);
            pishnehadsVMPaginatedList = pishnehadService.findPishnehadsByPishnehadSearch(pishnehadSearch, user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);
            return SUCCESS;
        }
    }

    public String searchForGharardads() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            PaginatedListImpl<Darkhast> darkhastPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//            bimenameha = new PaginatedListImpl<Bimename>();
//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);

            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);

            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);

            states = pishnehadService.findAllSatetsForPishnehadSystem();
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            ostanha = constantItemsService.getOstans();
            reportResult = new ArrayList<Pishnehad>();

            PaginatedListImpl<Gharardad> searchGHPaginated = new PaginatedListImpl<Gharardad>();
            if (isExport()) {
                searchGHPaginated.setPageNumber(0);
                searchGHPaginated.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                searchGHPaginated.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.gharardadhaPageNumber) - 1);
                searchGHPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }
            PaginatedListImpl<Gharardad> theFoundGharardads = gharardadService.search(gharardadSearch, user, searchGHPaginated);//findPishnehadsByPishnehadSearch(pishnehadSearch, user, searchGHPaginated);

            getSession().put("gharardadha", theFoundGharardads);
            return SUCCESS;
        }
    }

    public String searchForPishnehadsToView() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            PaginatedListImpl<Darkhast> darkhastPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, PagingUtil.getPageNumberFromContext("page"));
            alldarkhastsPgList = darkhastService.loadDarkhasts(user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            elhaghiyeha.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
//            khesaratha = khesaratService.findAll();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
//            bimenameha = new PaginatedListImpl<Bimename>();
//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bimenameha = pishnehadService.findAllBimenamePaginated(user, bimenameha);

            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList);

            states = pishnehadService.findAllSatetsForPishnehadSystem();
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
//            ostanha = constantItemsService.getOstans();


//            searchAslPaginated = new PaginatedListImpl<Pishnehad>();
//            if(isExport()){
//                searchAslPaginated.setPageNumber(0);
//                searchAslPaginated.setObjectsPerPage(Integer.MAX_VALUE);
//            }
//            else
//            {
//                searchAslPaginated.setPageNumber(pageNum-1);
//                searchAslPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            }
//            PaginatedListImpl<Pishnehad> theFoundPishnehads = pishnehadService.findPishnehadsByPishnehadSearch(pishnehadSearch,user,searchAslPaginated);
//            getSession().put("reportResult",theFoundPishnehads);

//            searchViewPaginated = new PaginatedListImpl<Pishnehad>();
            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            if (isExport()) {
//                searchViewPaginated.setPageNumber(0);
//                searchViewPaginated.setObjectsPerPage(Integer.MAX_VALUE);

                allPishnehadsVMPaginatedList.setPageNumber(0);
                allPishnehadsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
//                searchViewPaginated.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
//                searchViewPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

                allPishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.viewReportResultPageNumber) - 1);
                allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }

//            PaginatedListImpl<Pishnehad> theFoundPishnehads = pishnehadService.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, searchViewPaginated);
//            getSession().put("viewReportResult", theFoundPishnehads);
//            getSession().put("theSearchedViewReportResult",theFoundPishnehads);
            allPishnehadsVMPaginatedList = pishnehadService.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, allPishnehadsVMPaginatedList);

            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);
            return SUCCESS;
        }
    }

    private List<Khesarat> khesaratha;
    private PaginatedListImpl<KhesaratVM> khesaratVMPaginatedList;
    //    private List<Gharardad> gharardadha;
    private PaginatedListImpl<Gharardad> gharardadhaPaginated;
    //--------------------------------------bazyabi----------------------------------------------------------------------//
    public String retrieve()
    {
        User user = getUser(); //karbare jari
        if (user == null) //agar karbare jari vojood nadarad
        {
            nosession = true;
            return Constant.NOSESSION;
        }
        else //agar karbare jari vojood darad
        {
            //tab1 = pishnahadhaye man
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            pishnehadsVMPaginatedList = setInitList(pishnehadsVMPaginatedList, ConstantPaging.reportResultPageNumber);
            if (pishnehadSearch != null)
                pishnehadsVMPaginatedList = pishnehadService.findPishnehadsByPishnehadSearch(pishnehadSearch, user, pishnehadsVMPaginatedList);

            //tab2 = hameye pishnahadha
            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();
            allPishnehadsVMPaginatedList = setInitList(allPishnehadsVMPaginatedList, ConstantPaging.viewReportResultPageNumber);
            if (pishnehadSearch != null)
                allPishnehadsVMPaginatedList = pishnehadService.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, allPishnehadsVMPaginatedList);

            //tab3 = bime namehha
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
            bimenameVMPaginatedList = setInitList(bimenameVMPaginatedList, ConstantPaging.bimenamehaPageNumber);
            if (pishnehad != null)
            {
                String nameBimeGozar = null;
                String familyBimeGozar = null;
                String kodeMelliBimeGozar = null;

                String nameBimeShode = null;
                String familyBimeShode = null;
                String kodeMelliBimeShode = null;

                if (pishnehad != null) {
                    if (pishnehad.getBimeGozar() != null) {
                        nameBimeGozar = pishnehad.getBimeGozar().getShakhs().getName();
                        familyBimeGozar = pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi();
                        kodeMelliBimeGozar = pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi();
                    }
                    if (pishnehad.getBimeShode() != null) {
                        nameBimeShode = pishnehad.getBimeShode().getShakhs().getName();
                        familyBimeShode = pishnehad.getBimeShode().getShakhs().getNameKhanevadegi();
                        kodeMelliBimeShode = pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi();
                    }
                }

                State st = null;
                if (bimename != null) {
                    if (bimename.getState() != null && bimename.getState().getId() != null && bimename.getState().getId() > 0)
                        st = stateDAO.findById(bimename.getState().getId());

                    bimenameVMPaginatedList = pishnehadService.searchBimenamePaginated(bimenameVMPaginatedList, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar, nameBimeShode, familyBimeShode, kodeMelliBimeShode, bimename.getShomare(), tarikhSodur, bimename.getTarikhSodour(), null, null,
                            st, subsetState, null, pishnehad.getEstelam() != null ? pishnehad.getEstelam().getNahve_pardakht_hagh_bime() : null, user, pishnehad.getEstelam() != null && !pishnehad.getEstelam().getSarmaye_paye_fot().equals("0") ? pishnehad.getEstelam().getSarmaye_paye_fot() : null,
                            pishnehad.getRadif(), pishnehad.getNamayande() != null ? pishnehad.getNamayande().getId() : null, pishnehad.getNamayande() != null ? pishnehad.getNamayande().getVahedSodur().getId() : null, pishnehad.getEstelam() != null ? pishnehad.getEstelam().getNoe_tarh() : null, pishnehad.getNoeGharardad(), pishnehad.getKarshenas() != null ? pishnehad.getKarshenas().getFirstName() : null, groupId, pishnehad.getOptions().equals("CODE_MOVAGHAT") ? true : false);
                    if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                        bimenameVMPaginatedList.setFullListSize(5);
                }
            }

            //tab4 = darkhasthaye dar jaryane man
            int darkhastPageNumber = PagingUtil.getPageNumberFromContext("page");
            PaginatedListImpl<Darkhast> darkhastPaginatedList = new PaginatedListImpl<Darkhast>();
            darkhastsVMPaginatedList = new PaginatedListImpl<DarkhastsVM>();

            if (isExport())
            {
                darkhastPaginatedList.setPageNumber(0);
                darkhastPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                darkhastsVMPaginatedList.setPageNumber(0);
                darkhastsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            }
            else
            {
                darkhastPaginatedList.setPageNumber(darkhastPageNumber - 1);
                darkhastPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                darkhastsVMPaginatedList.setPageNumber(darkhastPageNumber - 1);
                darkhastsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                if (darkhastPageNumber > 1)
                    if (pishnehad != null) {
                        DarkhastsVM searchDarkhastsVM = new DarkhastsVM();
                        if (darkhastType.equals("EBTAL"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.EBTAL.toString());
                        if (darkhastType.equals("BAZKHARID"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.BAZKHARID.toString());
                        if (darkhastType.equals("VAM"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.VAM.toString());
                        if (darkhastType.equals("TAGHIR_CODE"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TAGHIRKOD.toString());
                        if (darkhastType.equals("TOZIH"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TOZIH.toString());
                        if (darkhastType.equals("TAGHIRAT"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TAGHYIRAT.toString());
                        if (darkhastType.equals("BARDASHT"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE.toString());
                        if (darkhastType.equals("KHESARAT"))
                            searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.KHESARAT.toString());
                        if (darkhastType.equals("ALL"))
                            searchDarkhastsVM.setDarkhastTypeStr("ALL");
                        searchDarkhastsVM.setUser(user);
                        searchDarkhastsVM.setShomareBimename(bimename.getShomare());
                        searchDarkhastsVM.setNamayandeId(namayande.getId());
                        searchDarkhastsVM.setKarshenasFirstName(karshenas);
                        searchDarkhastsVM.setKarshenasLastName(karshenas);
                        searchDarkhastsVM.setAzTarikheDarkhast(azTarikheDarkhast);
                        searchDarkhastsVM.setTaTarikheDarkhast(taTarikheDarkhast);
                        searchDarkhastsVM.setBimeGozarCodeMelli(pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi());
                        searchDarkhastsVM.setBimeShodeCodeMelli(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi());
                        searchDarkhastsVM.setBimeGozarFirstName(pishnehad.getBimeShode().getShakhs().getName());
                        searchDarkhastsVM.setBimeGozrLastName(pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi());
                        searchDarkhastsVM.setBimeShodeFirstName(pishnehad.getBimeShode().getShakhs().getName());
                        searchDarkhastsVM.setBimeShodeLastName(pishnehad.getBimeShode().getShakhs().getNameKhanevadegi());
                        searchDarkhastsVM.setDarkhastStates(StringUtil.convertStringToList(darkhastState, ','));
                        darkhastsVMPaginatedList = darkhastService.myDarkhasts(darkhastsVMPaginatedList, searchDarkhastsVM);
                        setSearch(false);
//                        darkhastPaginatedList = darkhastService.searchDarkhasts(darkhastPaginatedList , user, darkhastType, bimename.getShomare(), namayande.getId(), karshenas, azTarikheDarkhast, taTarikheDarkhast,
//                            pishnehad.getBimeGozar().getShakhs().getName(), pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi(),
//                            pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(), pishnehad.getBimeShode().getShakhs().getName(),
//                            pishnehad.getBimeShode().getShakhs().getNameKhanevadegi(), pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(),StringUtil.convertStringToList(darkhastState,','));
                    }
            }
//            getSession().put("darkhastPaginatedList", darkhastPaginatedList);

            //tab8 = ?
            int allDarkhastPageNumber = PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber);
            alldarkhastsPgList = new PaginatedListImpl<Darkhast>();
            alldarkhastsPgList = setInitList(alldarkhastsPgList, ConstantPaging.allDarkhatsthayeDarJaryanPageNumber);
            if (searchDarkhast != null) {
                searchDarkhast.setDarkhastStateList(StringUtil.convertStringToList(searchDarkhast.getDarkhastState(), ','));
                alldarkhastsPgList = darkhastService.searchHameyeDarkhastha(searchDarkhast, user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            }

            //tab9 = ?
            bahreMandiVMPaginatedList = new PaginatedListImpl<BahreMandiVM>();
            if (isExport()) {
                bahreMandiVMPaginatedList.setPageNumber(0);
                bahreMandiVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                bahreMandiVMPaginatedList.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.vamResultPageNumber) - 1);
                bahreMandiVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }
            if (bahreMandiVMS == null)
                bahreMandiVMS = new BahreMandiVM();
            bahreMandiVMS.setUser(user);
            bahreMandiVMPaginatedList = darkhastService.findBahreMandi(bahreMandiVMPaginatedList, bahreMandiVMS);
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            statesBahreMandi = darkhastService.findByStateMachine("VAM_SYSTEM");
            statesBahreMandi.addAll(darkhastService.findByStateMachine("BARDASHT_AZ_ANDOKHTE_SYSTEM"));
            statesBahreMandi.add(new State());

            //tab5 =
            int pageNumber = PagingUtil.getPageNumberFromContext("d-49685-p");
            elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
            if (isExport()) {
                elhaghiyeha.setPageNumber(0);
                elhaghiyeha.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                elhaghiyeha.setPageNumber(pageNumber - 1);
                elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                if (pageNumber > 1)
                    if (elhaghiyeSearch != null)
                        elhaghiyeha = darkhastService.searchForElhaghiye(elhaghiyeSearch, elhaghiyeha, user.getId());
                    else
                        elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
            }


            //tab6 =
            khesaratha = new ArrayList<Khesarat>();
            khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();

            if (isExport()) {
                khesaratVMPaginatedList.setPageNumber(0);
                khesaratVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                khesaratVMPaginatedList.setPageNumber(0);
                khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            }
            KhesaratVM kvm = new KhesaratVM();
            kvm.setUserCreator(user);
            khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, kvm);
            if (userHasRole(user, "ROLE_KARSHENAS_MASOUL") || userHasRole(user, "ROLE_KARSHENAS_BAYEGANI") || userHasRole(user, "ROLE_PEZESHK"))
                karshenasha = pishnehadService.findAllKarshenasForPishnehads("");


            //tab7 =
            gharardadhaPaginated = new PaginatedListImpl<Gharardad>();
            if (isExport()) {
                gharardadhaPaginated.setPageNumber(0);
                gharardadhaPaginated.setObjectsPerPage(Integer.MAX_VALUE);
            } else {
                gharardadhaPaginated.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.gharardadhaPageNumber) - 1);
                gharardadhaPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                if (gharardadhaPaginated.getPageNumber() > 0)
                    gharardadhaPaginated = gharardadService.findAllowedGharardads(user, gharardadhaPaginated);
            }
            if (gharardadSearch != null)
                gharardadService.search(gharardadSearch, user, gharardadhaPaginated);
            getSession().put("gharardadha", gharardadhaPaginated);

            //other!
            states = pishnehadService.findAllSatetsForPishnehadSystem();
            ostanha = constantItemsService.getOstans();

            return SUCCESS;
        } //end agar karbare jari vojood darad
    }
    //------------------------------------------------------------------------------------------------------------------//
    private PaginatedListImpl setInitList(PaginatedListImpl list, String pageNumberName) {
        if (isExport()) {
            list.setPageNumber(0);
            list.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            list.setPageNumber(PagingUtil.getPageNumberFromContext(pageNumberName) - 1);
            list.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        return list;
    }

    private int getPageNumberFromUrlParam(String paramUrl, String paramName) {
        if (paramUrl != null) {
            if (paramUrl.startsWith(paramName)) {
                String[] paramValue = paramUrl.split("=");
                if (paramValue != null && paramValue.length > 0) {
                    parametersUrl = "";
                    return Integer.parseInt(paramValue[1]);
                }
            }
        }

        return 1;
    }


    public String resultTab1() {
        User user = getUser();

        if (userHasRole(user, "ROLE_KARSHENAS_MASOUL") || userHasRole(user, "ROLE_KARSHENAS_BAYEGANI") || userHasRole(user, "ROLE_PEZESHK"))
            karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
        ostanha = constantItemsService.getOstans();

        if (pishnehadsVMPaginatedList == null)
            pishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();

        if (isExport()) {
            pishnehadsVMPaginatedList.setPageNumber(0);
            pishnehadsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            pishnehadsVMPaginatedList.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.reportResultPageNumber) - 1);
            pishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        if (pishnehadSearch != null)
            pishnehadsVMPaginatedList = pishnehadService.findPishnehadsByPishnehadSearch(pishnehadSearch, user, pishnehadsVMPaginatedList);
        else
            pishnehadsVMPaginatedList = pishnehadService.findAllowedPishnehads(user, pishnehadsVMPaginatedList);
        return SUCCESS;
    }

    public String validateKaptcha() {
        String kaptchaExpected = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = getKaptcha();
        if (kaptchaExpected.equals(kaptchaReceived))
            return "success";
        return "error";
    }

    public String resultTab2() {
        User user = getUser();
        if (allPishnehadsVMPaginatedList == null)
            allPishnehadsVMPaginatedList = new PaginatedListImpl<PishnehadsVM>();

        if (isExport()) {
            allPishnehadsVMPaginatedList.setPageNumber(0);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            allPishnehadsVMPaginatedList.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.viewReportResultPageNumber) - 1);
            allPishnehadsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        if (pishnehadSearch != null)
            allPishnehadsVMPaginatedList = pishnehadService.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, allPishnehadsVMPaginatedList);
        else
            allPishnehadsVMPaginatedList = pishnehadService.findAllowedToViewPishnehads(user, allPishnehadsVMPaginatedList);
        return SUCCESS;
    }

    public String resultTab3() //bime nameha
    {
        User user = getUser(); //karbare jari
        if (bimenameVMPaginatedList == null) //liste bime nameha
            bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
        if (isExport()) //baraye inke kole result ra bargardanad (marboot be gozareshate excel)
        {
            bimenameVMPaginatedList.setPageNumber(0);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(0);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else  //agar gozareshe excel nabood (agar bekhahim dar har safhe 30 record neshan dahad)
        {
            bimenameVMPaginatedList.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.bimenamehaPageNumber) - 1);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MIN_OBJECTS_PER_PAGE);
            else
                bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }


        if (pishnehad != null) //agar pishnahad vojood darad
        {
            String nameBimeGozar = null;
            String familyBimeGozar = null;
            String kodeMelliBimeGozar = null;

            String nameBimeShode = null;
            String familyBimeShode = null;
            String kodeMelliBimeShode = null;

            if (pishnehad != null) //agar pishnahad vojood darad
            {
                if (pishnehad.getBimeGozar() != null)//agar bime gozar vojood darad
                {
                    nameBimeGozar = pishnehad.getBimeGozar().getShakhs().getName();
                    familyBimeGozar = pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi();
                    kodeMelliBimeGozar = pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi();
                }
                if (pishnehad.getBimeShode() != null) //agar bime shode vojood darad
                {
                    nameBimeShode = pishnehad.getBimeShode().getShakhs().getName();
                    familyBimeShode = pishnehad.getBimeShode().getShakhs().getNameKhanevadegi();
                    kodeMelliBimeShode = pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi();
                }
            }

            State st = null;
            if (bimename.getState().getId() != null && bimename.getState().getId() > 0) //agar vaziate bimename daem ast
                st = stateDAO.findById(bimename.getState().getId());
            //marboot be jostejoo dar tabe bimenameha
            bimenameVMPaginatedList = pishnehadService.searchBimenamePaginated(bimenameVMPaginatedList, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar, nameBimeShode, familyBimeShode, kodeMelliBimeShode, bimename.getShomare(), tarikhSodur, bimename.getTarikhSodour(), null, null,
                    st, subsetState, null, pishnehad.getEstelam().getNahve_pardakht_hagh_bime(), user, pishnehad.getEstelam().getSarmaye_paye_fot().equals("0") ? null : pishnehad.getEstelam().getSarmaye_paye_fot(),
                    pishnehad.getRadif(), pishnehad.getNamayande().getId(), pishnehad.getNamayande().getVahedSodur().getId(), pishnehad.getEstelam().getNoe_tarh(), pishnehad.getNoeGharardad(), pishnehad.getKarshenas().getFirstName(), groupId, pishnehad.getOptions().equals("CODE_MOVAGHAT") ? true : false);
            if (OmrUtil.userHasRole(user, "ROLE_LIMITED_AMIN_PARSIAN"))
                bimenameVMPaginatedList.setFullListSize(5);
        }
        else  //agar pishnahad vojood nadarad
        {
            bimenameVMPaginatedList = pishnehadService.findAllBimenamePaginated(user, bimenameVMPaginatedList); //peydakardane tamame bimenameha
        }
        return SUCCESS;
    }

    public String resultTab4() {
        User user = getUser();
        int darkhastPageNumber = PagingUtil.getPageNumberFromContext("page");
        int page = getPageNumberFromUrlParam(parametersUrl, "page") - 1;

        PaginatedListImpl<Darkhast> darkhastPaginatedList = new PaginatedListImpl<Darkhast>();
        darkhastsVMPaginatedList = new PaginatedListImpl<DarkhastsVM>();

        if (isExport()) {
            darkhastPaginatedList.setPageNumber(0);
            darkhastPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);

            darkhastsVMPaginatedList.setPageNumber(0);
            darkhastsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            darkhastPaginatedList.setPageNumber(page);
            darkhastPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            darkhastsVMPaginatedList.setPageNumber(page);
            darkhastsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }


        if (pishnehad != null) {
            DarkhastsVM searchDarkhastsVM = new DarkhastsVM();
            if (darkhastType.equals("EBTAL"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.EBTAL.toString());
            if (darkhastType.equals("BAZKHARID"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.BAZKHARID.toString());
            if (darkhastType.equals("VAM"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.VAM.toString());
            if (darkhastType.equals("TAGHIR_CODE"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TAGHIRKOD.toString());
            if (darkhastType.equals("TOZIH"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TOZIH.toString());
            if (darkhastType.equals("TAGHIRAT"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.TAGHYIRAT.toString());
            if (darkhastType.equals("BARDASHT"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.BARDASHT_AZ_ANDOKHTE.toString());
            if (darkhastType.equals("KHESARAT"))
                searchDarkhastsVM.setDarkhastTypeStr(Darkhast.DarkhastType.KHESARAT.toString());
            if (darkhastType.equals("ALL"))
                searchDarkhastsVM.setDarkhastTypeStr("ALL");

            searchDarkhastsVM.setUser(user);
            searchDarkhastsVM.setShomareBimename(bimename.getShomare());
            searchDarkhastsVM.setNamayandeId(namayande.getId());
            searchDarkhastsVM.setKarshenasFirstName(karshenas);
            searchDarkhastsVM.setKarshenasLastName(karshenas);
            searchDarkhastsVM.setAzTarikheDarkhast(azTarikheDarkhast);
            searchDarkhastsVM.setTaTarikheDarkhast(taTarikheDarkhast);
            searchDarkhastsVM.setBimeGozarCodeMelli(pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi());
            searchDarkhastsVM.setBimeShodeCodeMelli(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi());
            searchDarkhastsVM.setBimeGozarFirstName(pishnehad.getBimeShode().getShakhs().getName());
            searchDarkhastsVM.setBimeGozrLastName(pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi());
            searchDarkhastsVM.setBimeShodeFirstName(pishnehad.getBimeShode().getShakhs().getName());
            searchDarkhastsVM.setBimeShodeLastName(pishnehad.getBimeShode().getShakhs().getNameKhanevadegi());
            searchDarkhastsVM.setDarkhastStates(StringUtil.convertStringToList(darkhastState, ','));
            darkhastsVMPaginatedList = darkhastService.myDarkhasts(darkhastsVMPaginatedList, searchDarkhastsVM);
            setSearch(false);
//            darkhastPaginatedList = darkhastService.searchDarkhasts(darkhastPaginatedList , user, darkhastType, bimename.getShomare(), namayande.getId(), karshenas, azTarikheDarkhast, taTarikheDarkhast,
//                    pishnehad.getBimeGozar().getShakhs().getName(), pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi(),
//                    pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(), pishnehad.getBimeShode().getShakhs().getName(),
//                    pishnehad.getBimeShode().getShakhs().getNameKhanevadegi(), pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(),StringUtil.convertStringToList(darkhastState,','));
//            setSearch(true);

//            getSession().put("darkhastPaginatedList", darkhastPaginatedList);
        } else {
            DarkhastsVM searchDarkhastsVM = new DarkhastsVM();
            searchDarkhastsVM.setUser(user);
            searchDarkhastsVM.setDarkhastTypeStr("ALL");

//            darkhastsVMPaginatedList = darkhastService.findAllDarkhastsUnFinished(user, darkhastsVMPaginatedList);
            darkhastsVMPaginatedList = darkhastService.myDarkhasts(darkhastsVMPaginatedList, searchDarkhastsVM);
            setSearch(false);
        }


        return SUCCESS;
    }

    public String resultTab8() {
        User user = getUser();
        int allDarkhastPageNumber = getPageNumberFromUrlParam(parametersUrl, ConstantPaging.allDarkhatsthayeDarJaryanPageNumber);
        alldarkhastsPgList = new PaginatedListImpl<Darkhast>();
        darkhastsVMPaginatedList = new PaginatedListImpl<DarkhastsVM>();

        if (isExport()) {
            alldarkhastsPgList.setPageNumber(0);
            alldarkhastsPgList.setObjectsPerPage(Integer.MAX_VALUE);

            darkhastsVMPaginatedList.setPageNumber(0);
            darkhastsVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            alldarkhastsPgList.setPageNumber(allDarkhastPageNumber - 1);
            alldarkhastsPgList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            darkhastsVMPaginatedList.setPageNumber(allDarkhastPageNumber - 1);
            darkhastsVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

        }

        if (searchDarkhast != null) {
            searchDarkhast.setDarkhastStateList(StringUtil.convertStringToList(searchDarkhast.getDarkhastState(), ','));
            alldarkhastsPgList = darkhastService.searchHameyeDarkhastha(searchDarkhast, user, PagingUtil.getPageNumberFromContext(ConstantPaging.allDarkhatsthayeDarJaryanPageNumber));
            setSearch(true);
        } else {
//            alldarkhastsPgList = darkhastService.loadDarkhasts(user, allDarkhastPageNumber);
            darkhastsVMPaginatedList = darkhastService.loadDarkhasts(user, darkhastsVMPaginatedList);
            setSearch(false);
        }

        return SUCCESS;
    }

    public String resultTab5() {
        User user = getUser();
        int pageNumber = PagingUtil.getPageNumberFromContext("d-49685-p");
        elhaghiyeha = new PaginatedListImpl<Elhaghiye>();
        if (isExport()) {
            elhaghiyeha.setPageNumber(0);
            elhaghiyeha.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            elhaghiyeha.setPageNumber(pageNumber - 1);
            elhaghiyeha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        if (elhaghiyeSearch != null)
            elhaghiyeha = darkhastService.searchForElhaghiye(elhaghiyeSearch, elhaghiyeha, user.getId());
        else
            elhaghiyeha = darkhastService.findAllElhaghiyes(elhaghiyeha, user.getId());
        return SUCCESS;
    }

    public String resultTab6() {
        User user = getUser();
//        khesaratha = khesaratService.findAll();
        khesaratVMPaginatedList = new PaginatedListImpl<KhesaratVM>();

        if (isExport()) {
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            khesaratVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            khesaratVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        khesaratVMSrch.setUserCreator(user);
        khesaratVMPaginatedList = khesaratService.loadKhesarats(khesaratVMPaginatedList, khesaratVMSrch);
        return SUCCESS;
    }

    public String resultTab9() {
        User user = getUser();
        karshenasha = pishnehadService.findAllKarshenasForPishnehads("bahre_mandi");
        bahreMandiVMPaginatedList = new PaginatedListImpl<BahreMandiVM>();
        if (isExport()) {
            bahreMandiVMPaginatedList.setPageNumber(0);
            bahreMandiVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            bahreMandiVMPaginatedList.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.vamResultPageNumber) - 1);
            bahreMandiVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        if (bahreMandiVMS == null)
            bahreMandiVMS = new BahreMandiVM();
        bahreMandiVMS.setUser(user);
        statesBahreMandi = darkhastService.findByStateMachine("VAM_SYSTEM");
        statesBahreMandi.addAll(darkhastService.findByStateMachine("BARDASHT_AZ_ANDOKHTE_SYSTEM"));
        statesBahreMandi.add(new State());
        bahreMandiVMPaginatedList = darkhastService.findBahreMandi(bahreMandiVMPaginatedList, bahreMandiVMS);
        return SUCCESS;
    }

    public String resultTab7() {
        User user = getUser();
        PaginatedListImpl<Gharardad> gharardadhatPaginated = new PaginatedListImpl<Gharardad>();
        if (isExport()) {
            gharardadhatPaginated.setPageNumber(0);
            gharardadhatPaginated.setObjectsPerPage(Integer.MAX_VALUE);
        } else {
            gharardadhatPaginated.setPageNumber(getPageNumberFromUrlParam(parametersUrl, ConstantPaging.gharardadhaPageNumber) - 1);
            gharardadhatPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }

        if (gharardadSearch != null)
            gharardadService.search(gharardadSearch, user, gharardadhatPaginated);
        else
            gharardadhaPaginated = gharardadService.findAllowedGharardads(user, gharardadhatPaginated);
        getSession().put("gharardadha", gharardadhaPaginated);
        return SUCCESS;
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

    //    public String forgetPassword() {
//        User user = getUser();
//
//        if (user != null && user.getMobile() != null && user.getMobile().equals(getMobileForget())) {
//
//            Random ran = new Random();
//            int x = ran.nextInt(9999);
//            String code = "Parsian" + x;
//            String ramzStr = "";
//            if (OmrUtil.userHasRole(user, "ROLE_USER_KARTABL")) {
//                user.setPassword(StringUtil.md5Hash(code));
//                user.setValidPass(false);
//                ramzStr = "کلمه عبور";
//                userKrtbl = true;
//            } else {
//                user.setCodeRamz(code);
//                user.setCodeRamzTarikh(DateUtil.getCurrentDate());
//                ramzStr = "کد رمز کاربر ";
//                userKrtbl = false;
//            }
//            loginService.updateUser(user);
//            try {
//                String firstName = (user.getFirstName() != null ? user.getFirstName() : "-");
//                String lastName = (user.getLastName() != null ? user.getLastName() : "-");
//                String cellPhoneNo = user.getMobile();
//                String description = "بازیابی کلمه عبور";
//                String issueDate = DateUtil.getCurrentDate();
//                String text = ramzStr + user.getUsername() + " در بیمه پارسیان برابر " + code + " می باشد.";
//                String policyId = user.getUsername();
//                boolean b = SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 8);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return SUCCESS;
//        }
//        addActionError("نام کاربری یا شماره موبایل صحیح نمی باشد.");
//        return ERROR;
//    }
    public String forgetPassword(){
        User user = loginService.findUserByUsername(getUsernameForget());
//        if (user != null && user.getCodeRamz() != null && user.getCodeRamz().length() > 0 && user.getCodeRamzTarikh().equals(DateUtil.getCurrentDate())){
//            addActionError("کد رمز برای شما ارسال شده است.");
//            return ERROR;
//        }

        if (user != null && user.getMobile()!=null && user.getMobile().equals(getMobileForget())){

            Random ran = new Random();
            int x = ran.nextInt(9999);
            String code = "Parsian" + x;
            String ramzStr="";
            if (OmrUtil.userHasRole(user, "ROLE_USER_KARTABL"))
            {
                user.setPassword(StringUtil.md5Hash(code));
                user.setValidPass(false);
                ramzStr = "کلمه عبور";
                userKrtbl=true;
            }
            else
            {
                user.setCodeRamz(code);
                user.setCodeRamzTarikh(DateUtil.getCurrentDate());
                ramzStr= "کد رمز کاربر " ;
                userKrtbl=false;
            }
            loginService.updateUser(user);
            try{
                String firstName = (user.getFirstName() != null ? user.getFirstName() : "-");
                String lastName = (user.getLastName() != null ? user.getLastName() : "-");
                String cellPhoneNo = user.getMobile();
                String description = "بازیابی کلمه عبور";
                String issueDate = DateUtil.getCurrentDate();
                String text= ramzStr +user.getUsername() + " در بیمه پارسیان برابر "+ code + " می باشد.";
                String policyId = user.getUsername();
                boolean b = SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 8);
            } catch(Exception e){
                e.printStackTrace();
            }

            return SUCCESS;
        }
        addActionError("نام کاربری یا شماره موبایل صحیح نمی باشد.");
        return ERROR;
    }
    public String sendNewPassword() {
        User user = loginService.findUserByUsername(getUsernameForget());
        inputStream = new StringBufferInputStream("false1");
        if (user != null && user.getCodeRamz().equals(getCodeRamzForget()) && user.getCodeRamzTarikh() != null && user.getCodeRamzTarikh().equals(DateUtil.getCurrentDate())) {
            Random ran = new Random();
            int x = ran.nextInt(9999);
            String code = "Parsian" + x;
            user.setPassword(StringUtil.md5Hash(code));
            user.setValidPass(false);
            inputStream = new StringBufferInputStream(code);
            user.setCodeRamz("");
            user.setCodeRamzTarikh("");
            loginService.updateUser(user);
            try {
                String firstName = (user.getFirstName() != null ? user.getFirstName() : "-");
                String lastName = (user.getLastName() != null ? user.getLastName() : "-");
                String cellPhoneNo = user.getMobile();
                String description = "ارسال کلمه عبور";
                String issueDate = DateUtil.getCurrentDate();
                String text = "کلمه عبور کاربر" + user.getUsername() + " در بیمه پارسیان برابر " + code + " می باشد.";
                String policyId = user.getUsername();
                boolean b = SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (user != null && user.getCodeRamz().equals(getCodeRamzForget()) && user.getCodeRamzTarikh() != null && !user.getCodeRamzTarikh().equals(DateUtil.getCurrentDate())) {
            inputStream = new StringBufferInputStream("false2");
        }
        return SUCCESS;
    }

    public String retrieveByDarkhastTaghiratId(Integer darkhasTaghiratId, Long userId) {
        DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhasTaghiratId);
        allowedTransitions = new ArrayList<Transition>();
        for (Transition transition : theDarkhast.getState().getTransitions()) {
            if (transition.getStateBegin().equals(theDarkhast.getState())) {
                for (Role role : transition.getRoles()) {
                    for (User user : role.getUsers()) {
                        if (user.getId().equals(userId)) {
                            allowedTransitions.add(transition);
                        }
                    }
                }
            }
        }
        return SUCCESS;
    }

    public String retrieveByDarkhastBazkharidId(Integer darkhastBazkharidId, Long userId) {
        DarkhastBazkharid theDarkhast = darkhastService.findDarkhastBazkharidById(darkhastBazkharidId);
        allowedTransitions = new ArrayList<Transition>();
        for (Transition transition : theDarkhast.getState().getTransitions()) {
            if (transition.getStateBegin().equals(theDarkhast.getState())) {
                for (Role role : transition.getRoles()) {
                    for (User user : role.getUsers()) {
                        if (user.getId().equals(userId)) {
                            allowedTransitions.add(transition);
                        }
                    }
                }
            }
        }
        return SUCCESS;
    }

    public String retrieveByPishnehadId(Integer pishnehadId, Long userId) {
        Pishnehad thePishnehad = pishnehadService.findById(pishnehadId);
        allowedTransitions = new ArrayList<Transition>();
        //allowedTransitions = pishnehadService.findAllowedTransitionsForId(userId,reportResult.get(0).getId());
        /*for (Pishnehad pishnehad: reportResult){
            for(Transition transition: pishnehad.getState().getTransitionBegin()){

            }

        }*/

        for (Transition transition : thePishnehad.getState().getTransitions()) {
            if (transition.getStateBegin().equals(thePishnehad.getState())) {
                for (Role role : transition.getRoles()) {
                    for (User user : role.getUsers()) {
                        if (user.getId().equals(userId)) {
                            allowedTransitions.add(transition);
                        }
                    }
                }
            }
        }
        return SUCCESS;
    }

    public String elameShomareHesabForEnseraf() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            pishnehadService.updateElameHesab(elameHesab);
//            pishnehadService.updatePishnehad(thePish);
            retrieve();
            return SUCCESS;
        }
    }

    public String elameShomareHesab() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            pishnehadService.saveElameHesab(elameHesab);
            thePish.setElameHesab(elameHesab);
            pishnehadService.updatePishnehad(thePish);
            return SUCCESS;
        }
    }

    public String anjameSharayeteJadid() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            sharayeteJadid.setPishnehad(thePish);
            pishnehadService.saveSharayeteJadid(sharayeteJadid);
            thePish.setSharayeteJadid(sharayeteJadid);
            pishnehadService.updatePishnehad(thePish);
            pishnehadService.transitPishnehad(userId, String.valueOf(pishnehad.getId()), transitionId, logmessage);
            retrieve();
            return SUCCESS;
        }
    }

    public String assignKarshenas() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            User theUser = loginService.findUserById(karshenasId);
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            thePish.setKarshenas(theUser);
            thePish.setTarikhAssignBeKarshenas(DateUtil.getCurrentDate());
            theUser.getPishnehads().add(thePish);
            loginService.updateUser(theUser);
            pishnehadService.updatePishnehad(thePish);
            pishnehad = pishnehadService.findById(thePish.getId());
            pishnehadService.transitPishnehad(userId, String.valueOf(pishnehad.getId()), transitionId, logmessage);
            retrieve();
            return SUCCESS;
        }
    }

    public String assignKarshenasToSomePishnehad() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();

            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] pishnehadIds = (String[]) ActionContext.getContext().getParameters().get(name);
                        String errorMessage = "پیشنهاد(های)";
                        boolean showNotAssigned = false;
                        User theUser = loginService.findUserById(karshenasId);
                        for (String idString : pishnehadIds) {
                            Integer pishId = Integer.parseInt(idString);
                            Pishnehad thePish = pishnehadService.findById(pishId);
                            if (thePish.getEstelam().getSarmaye_paye_fot_long() <= theUser.getSarmayeFotMaxForKarshenasi()) {
                                thePish.setKarshenas(theUser);
                                thePish.setTarikhAssignBeKarshenas(DateUtil.getCurrentDate());
                                theUser.getPishnehads().add(thePish);
                                loginService.updateUser(theUser);
                                pishnehadService.updatePishnehad(thePish);
                                pishnehad = pishnehadService.findById(thePish.getId());
                                if (pishnehad.getState().getId() == 70) {
                                    transitionId = "8";
                                } else if (pishnehad.getState().getId() == 40) {
                                    transitionId = "9";
                                } else if (pishnehad.getState().getId() == 60) {
                                    transitionId = "10";
                                }
                                pishnehadService.transitPishnehad(userId, String.valueOf(pishnehad.getId()), transitionId, logmessage);
                            } else {
                                showNotAssigned = true;
                                errorMessage += thePish.getRadif() + " ";
                            }
                        }
                        addActionMessage("پیشنهادهای انتخاب شده تخصیص به کارشناس داده شد.");
                        if (showNotAssigned) {
                            errorMessage += "به دلیل عدم رعایت سرمایه فوت تخصیص داده نشدند.";
                            addActionMessage(errorMessage);
                        }
                        retrieve();
                        return SUCCESS;
                    }
                }
            }
            retrieve();
            return SUCCESS;
        }
    }

    public String transitionSomePishnehad() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();

            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] pishnehadIds = (String[]) ActionContext.getContext().getParameters().get(name);
                        for (String idString : pishnehadIds) {
                            Integer pishId = Integer.parseInt(idString);
                            Pishnehad thePish = pishnehadService.findById(pishId);
                            pishnehadService.transitPishnehad(user.getId(), String.valueOf(thePish.getId()), "39", "تغییر وضعیت گروهی");
                            pishnehadService.updatePishnehad(thePish);
                        }
                        addActionMessage("تغییر وضعیت با موفقیت انجام شد.");
                        retrieve();
                        return SUCCESS;
                    }
                }
            }
            retrieve();
            return SUCCESS;
        }
    }

    public String updateKarshenas() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            User theUser = loginService.findUserById(karshenasId);
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            thePish.setKarshenas(theUser);
            thePish.setTarikhAssignBeKarshenas(DateUtil.getCurrentDate());
            theUser.getPishnehads().add(thePish);
            loginService.updateUser(theUser);
            pishnehadService.updatePishnehad(thePish);
            return SUCCESS;
        }
    }

    public String makeTransition() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (Integer.parseInt(transitionId) == 26) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                nazaraat = thePish.getNazaraatePezeshk();
                return PEZESHK_SABT_NAZAR;
            } else if (Integer.parseInt(transitionId) == 7) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return UPLOAD_PISH_PARDAKHT;
            } else if (Integer.parseInt(transitionId) == 25) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                nazaraat = pishnehad.getNazaraatePezeshk();
                return ERJA_BE_KARSHENAS;
            } else if (Integer.parseInt(transitionId) == 46) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return TABDIL_BE_BIMENAMEH_MOVAGHAT;
            } else if (Integer.parseInt(transitionId) == 20) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                clinics = clinicService.findAllClinics();

                return SODURE_MOAREFI_NAME;
            } else if (Integer.parseInt(transitionId) == 35) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return TAKHSIS_BE_KARSHENAS;
            } else if (Integer.parseInt(transitionId) == 13) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return TAEED_FISH;
            } else if (Integer.parseInt(transitionId) == 14) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return SABTE_SHOMARE_HESAB;
            } else if (Integer.parseInt(transitionId) == 15) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return VORUD_MABLAGH_CHECK_BARGASHTI;
            } else if (Integer.parseInt(transitionId) == 15) {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = thePish;
                return TAEED_SODUR_CHECK_BARGASHTI;
            }


            pishnehadService.transitPishnehad(user.getId(), pishnehadId, transitionId, logmessage);
            retrieve();
            return SUCCESS;
        }
    }

    public String deleteMoarefiname() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            //pishnehadService.removeMoarefiname(moarefiname.getId());
            Moarefiname theMoarefiname = pishnehadService.findMoarefiname(moarefiname.getId());
            pishnehadService.ebtalMoarefiname(theMoarefiname);
            //pishnehadService.transitPishnehad(theUser.getId(),String.valueOf(pishnehad.getId()),transitionId,"");
            //retrieve(theUser.getId());
            return SUCCESS;
        }
    }

    public String makeInnerTransition() throws BimeNaamehVaSarmayehGozari.CustomValidationException {

        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            if (Integer.parseInt(transitionId) == 68) {
                Bimename theBim = thePish.getBimename();
                if (theBim != null) {
                    theBim.setReadyToShow("yes");
                    if (theBim.getPrimitivePishnehad() == null)
                        theBim.setPrimitivePishnehad(thePish);
                    pishnehadService.updateBimename(theBim);
                }
            } else if (Integer.parseInt(transitionId) == 1 || Integer.parseInt(transitionId) == 2 || Integer.parseInt(transitionId) == 5 || Integer.parseInt(transitionId) == 31 || Integer.parseInt(transitionId) == 73) {
                try {
                    pishnehadReport = new PishnehadReport();
                    pishnehadReport.setFRsList(new ArrayList<FRs>());
                    pishnehadReport.setPishnehad(thePish);
                    int moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename());
                    pishnehadReport.setCurrentElhaghiye(null);
                    correctCalculation = PrintAction.calculate(pishnehadReport, moddat);
                    calculate(thePish);
                } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
                    addActionMessage(e.getMyMessage());
                    throw e;
                }

//                if (!correctCalculation) {
//
//                    addActionMessage("اشکال محاسباتی، لطفا اطلاعات را اصلاح نمایید.");
//                    return SUCCESS;
//                }
            } else if (Integer.parseInt(transitionId) == 33) {
                SharayeteJadid sharayeteJadid = thePish.getSharayeteJadid();
                Estelam estelam = thePish.getEstelam();
                if (sharayeteJadid.getHagh_bime_pardakhti() != null) {
                    estelam.setHagh_bime_pardakhti(sharayeteJadid.getHagh_bime_pardakhti());
                }
                if (sharayeteJadid.getMablagh_seporde_ebtedaye_sal() != null) {
                    estelam.setMablagh_seporde_ebtedaye_sal(sharayeteJadid.getMablagh_seporde_ebtedaye_sal());
                }

                if (sharayeteJadid.getModat_bimename() != null) {
                    estelam.setModat_bimename(sharayeteJadid.getModat_bimename());
                }
                if (sharayeteJadid.getNahve_pardakht_hagh_bime() != null) {
                    estelam.setNahve_pardakht_hagh_bime(sharayeteJadid.getNahve_pardakht_hagh_bime());
                }
                if (sharayeteJadid.getNerkh_afzayesh_salaneh_hagh_bime() != null) {
                    estelam.setNerkh_afzayesh_salaneh_hagh_bime(sharayeteJadid.getNerkh_afzayesh_salaneh_hagh_bime());
                }
                if (sharayeteJadid.getNerkh_afzayesh_salaneh_sarmaye_fot() != null) {
                    estelam.setNerkh_afzayesh_salaneh_sarmaye_fot(sharayeteJadid.getNerkh_afzayesh_salaneh_sarmaye_fot());
                }
                if (sharayeteJadid.getPooshesh_amraz_khas() != null) {
                    estelam.setPooshesh_amraz_khas(sharayeteJadid.getPooshesh_amraz_khas());
                }
                if (sharayeteJadid.getPooshesh_fot_dar_asar_haadese() != null) {
                    estelam.setPooshesh_fot_dar_asar_haadese(sharayeteJadid.getPooshesh_fot_dar_asar_haadese());
                }
                if (sharayeteJadid.getPooshesh_fot_dar_asar_zelzele() != null) {
                    estelam.setPooshesh_fot_dar_asar_zelzele(sharayeteJadid.getPooshesh_fot_dar_asar_zelzele());
                }
                if (sharayeteJadid.getPooshesh_moafiat() != null) {
                    estelam.setPooshesh_moafiat(sharayeteJadid.getPooshesh_moafiat());
                }
                if (sharayeteJadid.getPooshesh_naghs_ozv() != null) {
                    estelam.setPooshesh_naghs_ozv(sharayeteJadid.getPooshesh_naghs_ozv());
                }
                if (sharayeteJadid.getSarmaye_paye_fot() != null) {
                    estelam.setSarmaye_paye_fot(sharayeteJadid.getSarmaye_paye_fot());
                }
                if (sharayeteJadid.getSarmaye_paye_fot_dar_asar_hadese() != null) {
                    estelam.setSarmaye_paye_fot_dar_asar_hadese(sharayeteJadid.getSarmaye_paye_fot_dar_asar_hadese());
                }
                if (sharayeteJadid.getSarmaye_pooshesh_amraz_khas() != null) {
                    estelam.setSarmaye_pooshesh_amraz_khas(sharayeteJadid.getSarmaye_pooshesh_amraz_khas());
                }
                if (sharayeteJadid.getSarmaye_pooshesh_naghs_ozv() != null) {
                    estelam.setSarmaye_pooshesh_naghs_ozv(sharayeteJadid.getSarmaye_pooshesh_naghs_ozv());
                }
                thePish.setEstelam(estelam);
                pishnehadService.updatePishnehad(thePish);


            } else if (Integer.parseInt(transitionId) == 3 || Integer.parseInt(transitionId) == 14) {
                fishs = pishnehad.getFishs();
                if (fishs != null) {
                    for (Fish theFish : fishs) {
                        List<Credebit> credebits = asnadeSodorService.findCredebitForTaeedFish(theFish);
                        for (Credebit credebit : credebits) {
                            if (OmrUtil.doesMinuteAndSecondMatch(theFish.getTime(), credebit.getCreatedTime())) {
                                theFish.setFound("true");
                                theFish.setCredebit(credebit);
                                credebit.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
                                asnadeSodorService.updateCredebit(credebit);
                                pishnehadService.updateFish(theFish);
                                break;
                            }
                        }
                    }
                }
            }

            if (Integer.parseInt(transitionId) == 2) {
                thePish.setJadidDate(DateUtil.getCurrentDate());
                pishnehadService.updatePishnehad(thePish);
            }
            String systemMessage = "";
            if (Integer.parseInt(transitionId) == 102) {
                assignPishnehadForNezarat(thePish);
                systemMessage = " | پیشنهاد برای نظارت به کاربر " + thePish.getKarshenasNazer().getUsername() + " (" + thePish.getKarshenasNazer().getFullName() + ") ارجاع داده شد.";
                addActionMessage(systemMessage);
            }
            boolean showBimename = false;
            if (Integer.parseInt(transitionId) == 109) {
                sodoorBimename(user, thePish.getBimename());
                showBimename = true;
            }
            if (logmessage == null || logmessage.length() < 2) {
                logmessage = "-";
            }
            if (privateMessage != null && privateMessage.length() > 0) {
                thePish.setPrivateMessages(privateMessage);
                pishnehadService.updatePishnehad(thePish);
            }
            pishnehadService.transitPishnehad(user.getId(), String.valueOf(pishnehad.getId()), transitionId, logmessage + systemMessage);
            pishnehad = pishnehadService.findById(pishnehad.getId());
            if (showBimename)
                return "showBimename";
            if (!pishnehad.getState().getId().equals(249) && !pishnehad.getState().getId().equals(251) && !pishnehad.getState().getId().equals(252) && !pishnehad.getState().getId().equals(250) && pishnehadService.hasPermission(user, pishnehad.getState().getId())) {
                return "stayOnSamePlace";
            }
            if (pishnehad.getGharardad() != null && pishnehad.getGharardad().getId() != null) {
                addActionMessage("اتمام آپلود پیشنهاد" + pishnehad.getRadif() + "با موفقیت انجام شد.");
                return "SUCCESS_forGharardad";
            } else {
                return SUCCESS;
            }
        }
    }

    public String sodoureBimenameh() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            if (codeMovaghat != null) {
                thePish.setOptions("CODE_MOVAGHAT");
            } else {
                thePish.setOptions(null);
            }

            if (thePish.getNoeBimename().equals("خانواده")) {
                pishnehadService.updatePishnehad(thePish);
                pishnehadService.transitPishnehad(userId, String.valueOf(thePish.getId()), "96", "صدور بیمه نامه خانواده");
                retrieve();
                return "list";
            }
            bimename.setVahedSodor(user.getMojtamaSodoor());
            thePish.setBimename(bimename);
            bimename.setReadyToShow("no");
            if (bimename.getPrimitivePishnehad() == null)
                bimename.setPrimitivePishnehad(thePish);
            bimename.setSharayeteKhosusi(pishnehadReport.getTozihatBimenameForPrint());
            EmzaKonande emza1 = clinicService.findEmzaKonandeById(bimename.getEmzaKonandeAval().getId());
            bimename.setEmzaKonandeAval(emza1);
            EmzaKonande emza2 = clinicService.findEmzaKonandeById(bimename.getEmzaKonandeDovom().getId());
            bimename.setEmzaKonandeDovom(emza2);
            int bimenameId = pishnehadService.saveBimename(bimename);
            Bimename theBime = pishnehadService.findBimenameById(bimenameId);
            thePish.setBimename(theBime);
            ArrayList<Pishnehad> pishList = new ArrayList<Pishnehad>();
            pishList.add(thePish);
            theBime.setPishnehadList(pishList);
            if (Integer.parseInt(thePish.getEstelam().getSeneBimeie()) > 60) {
                thePish.getEstelam().setPooshesh_amraz_khas("no");
                thePish.getEstelam().setSarmaye_pooshesh_amraz_khas("0");
                thePish.getEstelam().setPooshesh_moafiat("no");
            }

            pishnehadService.updateBimename(theBime);
            pishnehadService.updatePishnehad(thePish);

            thePish = pishnehadService.findById(pishnehad.getId());
            theBime = pishnehadService.findBimenameById(bimenameId);

            if (Constant.DEV_nezaratEnabled) {
                if (hasShartSodor(theBime, thePish.getKarshenas())) {
                    pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "95", logmessage);
                    sodoorBimename(user, theBime);
                    return SUCCESS;
                } else {
                    boolean hasPassedNezaratPezeshk = false;
                    for (TransitionLog tl : thePish.getTransitionLogs()) {
                        if (tl.getTransition() != null && (tl.getTransition().getId() == 101 || tl.getTransition().getStateBegin().getId() == 160))
                            hasPassedNezaratPezeshk = true;
                    }
                    if (!hasPassedNezaratPezeshk && Short.parseShort(thePish.getEstelam().getDarsad_ezafe_nerkh_pezeshki()) > 30) {
                        pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "101", logmessage);
                        addActionMessage("پیشنهاد به کارتابل مسئول امور پزشکی ارجاع داده شد.");
                    } else {
                        assignPishnehadForNezarat(thePish);
                        String systemMessage = " | پیشنهاد برای نظارت به کاربر " + thePish.getKarshenasNazer().getUsername() + " (" + thePish.getKarshenasNazer().getFullName() + ") ارجاع داده شد.";
                        pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "104", logmessage + systemMessage);
                        addActionMessage(systemMessage);
                    }
                    retrieve();
                    return "list";
                }
            } else {
                pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "95", logmessage);
                sodoorBimename(user, theBime);
                return SUCCESS;
            }
        }
    }

    public void sodoorBimename(User user, Bimename bimename) throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        User theUser = getUser();
        if (bimename.getGhestBandiList() == null || bimename.getGhestBandiList().size() == 0)
            taghsitFirstYear(bimename.getPishnehad().getId());
        asnadeSodorService.refreshObject(bimename);
        KarmozdGhest.Tarefe tarefe = KarmozdCalculate.getTarefeBimename(bimename);
        long sarmaye = (long) (bimename.getPishnehad().getEstelam().getSarmaye_paye_fot_long() * 0.03);
        long haghBimeAval;
        long sepordeEbteda = 0l;
        long haghBimeOrSarmaye = sarmaye;
        if (tarefe.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO)) {
            long haghBimeBeduneMaliatVaPoosheshha = queryService.getJameSadereForGhestbandiKasrMaliatEzafi(bimename.getId());
//            if (bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal() != null)
//                sepordeEbteda = Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").trim());
//            haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha + sepordeEbteda) * 0.75);
            haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha) * 0.75);
            if (haghBimeAval < sarmaye)
                haghBimeOrSarmaye = haghBimeAval;
        }
        bimename.setKarmozdBimename(haghBimeOrSarmaye);
        bimename.setReadyToShow("yes");
        bimename.setTarikhSodour(DateUtil.getCurrentDate());
        if (DateUtil.isGreaterThan(bimename.getTarikhSodour(), bimename.getTarikhShorou())) {
            bimename.setTarikhShorou(bimename.getTarikhSodour());
            bimename.setTarikhEngheza(DateUtil.addYear(bimename.getTarikhShorou(), Integer.parseInt(bimename.getPishnehad().getEstelam().getModat_bimename())));
        }
        bimename.setSerial(sequenceService.nextShomareSerialeBimename(user.getMojtamaSodoor()));
        bimename.setShomare(sequenceService.nextShomareBimename(user.getMojtamaSodoor()));
        for (Credebit c : bimename.getCredebitList()) {
            c.setIdentifier(bimename.getShomare());
            asnadeSodorService.updateCredebit(c);
        }
        bimename.setPrimitivePishnehad(bimename.getPishnehad());
        pishnehadService.updateBimename(bimename);
        sendSodooreBimeSMS(bimename, bimename.getPishnehad());
    }

    private void assignPishnehadForNezarat(Pishnehad thePish) {
        boolean assignToKarshenasNazer = true, assignToKarshenasMasool = true;
        // check for karshenas nazer assignment
        if (thePish.getEstelam().getSarmaye_paye_fot_long() > thePish.getKarshenas().getNezaratKarshenasNazerSarmayeFot())
            assignToKarshenasNazer = false;
        if (Long.parseLong(thePish.getEstelam().getSarmaye_paye_fot_dar_asar_hadese().replaceAll(",", "")) > thePish.getKarshenas().getNezaratKarshenasNazerSarmayeHadese())
            assignToKarshenasNazer = false;
        if (Short.parseShort(thePish.getEstelam().getTabaghe_khatar()) > thePish.getKarshenas().getNezaratKarshenasNazerTabagheKhatarHadese())
            assignToKarshenasNazer = false;
        if (Short.parseShort(thePish.getEstelam().getTabaghe_khatar_naghsozv()) > thePish.getKarshenas().getNezaratKarshenasNazerTabagheKhatarNaghs())
            assignToKarshenasNazer = false;
        if (Short.parseShort(thePish.getEstelam().getDarsad_ezafe_nerkh_pezeshki()) > thePish.getKarshenas().getNezaratKarshenasNazerEzafeNerkh())
            assignToKarshenasNazer = false;
        // check for karshenas masool assignment
        if (!assignToKarshenasNazer) {
            if (thePish.getEstelam().getSarmaye_paye_fot_long() > thePish.getKarshenas().getNezaratKarshenasMasoolSarmayeFot())
                assignToKarshenasMasool = false;
            if (Long.parseLong(thePish.getEstelam().getSarmaye_paye_fot_dar_asar_hadese().replaceAll(",", "")) > thePish.getKarshenas().getNezaratKarshenasMasoolSarmayeHadese())
                assignToKarshenasMasool = false;
            if (Short.parseShort(thePish.getEstelam().getTabaghe_khatar()) > thePish.getKarshenas().getNezaratKarshenasMasoolTabagheKhatarHadese())
                assignToKarshenasMasool = false;
            if (Short.parseShort(thePish.getEstelam().getTabaghe_khatar_naghsozv()) > thePish.getKarshenas().getNezaratKarshenasMasoolTabagheKhatarNaghs())
                assignToKarshenasMasool = false;
            if (Short.parseShort(thePish.getEstelam().getDarsad_ezafe_nerkh_pezeshki()) > thePish.getKarshenas().getNezaratKarshenasMasoolEzafeNerkh())
                assignToKarshenasMasool = false;
        }
        // assign
        User theKarshenas = null;
        User theUser = getUser();
        if (assignToKarshenasNazer) {
            theKarshenas = pishnehadService.findKarshenasForNezaratAssignment(true, false, false, theUser);
        } else if (assignToKarshenasMasool) {
            theKarshenas = pishnehadService.findKarshenasForNezaratAssignment(false, true, false, theUser);
        } else {
            // assign to raees edare
            theKarshenas = pishnehadService.findKarshenasForNezaratAssignment(false, false, true, theUser);
        }
        thePish.setKarshenasNazer(theKarshenas);
        thePish.setTarikhAssignBeKarshenasNazer(DateUtil.getCurrentDate());
        pishnehadService.updatePishnehad(thePish);
    }

    private boolean hasShartSodor(Bimename bimename, User karshenas) {
        if (bimename.getPishnehad().getEstelam().getSarmaye_paye_fot_long() > karshenas.getSarmayeFotMaxForSodor())
            return false;
        if (Long.parseLong(bimename.getPishnehad().getEstelam().getSarmaye_paye_fot_dar_asar_hadese().replaceAll(",", "").trim()) > karshenas.getSarmayeHadeseMaxForSodor())
            return false;
        if (Short.parseShort(bimename.getPishnehad().getEstelam().getTabaghe_khatar().trim()) > karshenas.getTabagheKhatarHadeseMaxForSodor())
            return false;
        if (Short.parseShort(bimename.getPishnehad().getEstelam().getTabaghe_khatar_naghsozv().trim()) > karshenas.getTabagheKhatarNaghsMaxForSodor())
            return false;
        if (Short.parseShort(bimename.getPishnehad().getEstelam().getDarsad_ezafe_nerkh_pezeshki().replaceAll(",", "").trim()) > karshenas.getEzafeNerkhMaxForSodor())
            return false;
        return true;
    }

    public void batchTaghsit() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        List<Integer> pList = asnadeSodorService.findPishnehadsForAutoBatchTaghsit(false);
        for (Integer id : pList) {
            Pishnehad p = pishnehadService.loadPishnehadById(id);
            int toGhestBandi = 0;
            for (GhestBandi gb : p.getBimename().getGhestBandiList()) {
                if (gb.getSaleBimeiInt() != null && gb.getSaleBimeiInt() > toGhestBandi)
                    toGhestBandi = gb.getSaleBimeiInt();
            }
            asnadeSodorService.saveGhestbandi(p, toGhestBandi + 1, null);
        }
    }

    public void taghsitFirstYear(int pishnehadId) throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        User user = getUser();
        Pishnehad thePish = pishnehadService.findById(pishnehadId);
        taghsitReport = asnadeSodorService.mohasebeyeAghsat(thePish, 0, thePish.getBimename().getTarikhShorou(), true);
        if (user != null)
            asnadeSodorService.saveGhestbandi(thePish, 0, taghsitReport, user);

        boolean finished = false;
        int yearCounter = 1;
        int maxTaghsit = (int) Math.ceil(thePish.getFishValue() / (double) OmrUtil.majmooeAghsat(taghsitReport));
        while (!finished) {
            thePish = pishnehadService.findById(pishnehadId);
            List<Credebit> fishs = thePish.getFoundFishes();
            long fishValue = 0;
            for (Credebit c : fishs) {
                fishValue += c.getRemainingAmount_long();
            }
            if (fishValue > 0) {
                taghsitReport = asnadeSodorService.mohasebeyeAghsat(thePish, yearCounter, thePish.getBimename().getTarikhShorou(), true);
                if (user != null) {
                    asnadeSodorService.saveGhestbandi(thePish, yearCounter, taghsitReport, user);
                }
                if (yearCounter >= maxTaghsit) finished = true;
                yearCounter++;
            } else {
                finished = true;
            }
        }

    }

    private boolean sendSodooreBimeSMS(Bimename theBime, Pishnehad pishnehad) {
        try {
            String firstName = pishnehad.getBimeGozar().getShakhs().getName();
            String lastName = pishnehad.getBimeGozar().getShakhs().getNameKhanevadegi();
            String cellPhoneNo = pishnehad.getAddressBimeGozar().getTelephoneHamrah();
            String description = "صدور بيمه نامه";
            String issueDate = theBime.getTarikhSodour();
            String text = "با تبريك پيوستن شما به خانواده بزرگ پارسيان،‌ به اطلاع مي رساند بيمه نامه عمر و سرمايه گذاري شما در تاريخ " +
                    issueDate +
                    " صادر گرديده و به زودي براي شما ارسال خواهد شد. همچنين جهت مشاهده بيمه نامه،‌ مي توانيد از سامانه خدمات الكترونيك به آدرس زير استفاده فرماييد.\n" +
                    "http://lifecrm.parsianinsurance.com\n" +
                    "*بيمه پارسيان*";
            String policyId = theBime.getShomare();
            return SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 27);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String sabteShomareHesab() throws Exception {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pishnehadService.transitPishnehad(userId, String.valueOf(pishnehadId), String.valueOf(transitionId), logmessage);
            retrieve();
            return SUCCESS;
        }

    }

    public String assingToExpert() throws Exception {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pishnehadService.transitPishnehad(userId, String.valueOf(pishnehadId), String.valueOf(transitionId), logmessage);
            retrieve();
            return SUCCESS;
        }

    }

    public String sendNaghs() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();

            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            thePish.setProcessorDetails(user);
            Set<Role> allTheRoles = user.getRoles();
            for (Role allTheRole : allTheRoles) {
                if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR)) {
                    thePish.setProcessor(Constant.ROLE_KARSHENAS_SODOUR);
                } else if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)) {
                    thePish.setProcessor(Constant.ROLE_KARSHENAS_MASOUL);
                }

            }
//            pishnehadService.updatePishnehad(thePish);


            if (naghsPishnehad.getNaghsEtelaat() != null)
                naghsPishnehad.setNaghsEtelaat("1");
            else naghsPishnehad.setNaghsEtelaat("0");
            if (naghsPishnehad.getNaghsFish() != null)
                naghsPishnehad.setNaghsFish("1");
            else naghsPishnehad.setNaghsFish("0");
            if (naghsPishnehad.getNaghsMadaarek() != null)
                naghsPishnehad.setNaghsMadaarek("1");
            else naghsPishnehad.setNaghsMadaarek("0");
            int id = pishnehadService.saveNaghsPishnehad(naghsPishnehad);
            NaghsPishnehad naaghes = pishnehadService.findNaghsPishnehadById(id);
            thePish.setNaghsPishnehad(naaghes);
            naaghes.setPishnehad(thePish);

            pishnehadService.updateNaghsPishnehad(naaghes);
            pishnehadService.updatePishnehad(thePish);

            pishnehadService.transitPishnehad(userId, String.valueOf(thePish.getId()), transitionId, logmessage);

            retrieveByPishnehadId(thePish.getId(), userId);
            pishnehad = pishnehadService.findById(thePish.getId());
            retrieve();
            return SUCCESS;
        }
    }

    public String removeFileZamime() throws ServiceException, RemoteException {
        backfromupload = "true_rem";
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = null;

            sid = l.getAuthServiceImplPort().login("admin", "admin");
            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
            DocumentService documentService = docLocator.getDocumentServiceImplPort();
            documentService.delete(sid, Long.parseLong(fileId));
            Zamaem theZamime = pishnehadService.findZamaemById(zamaem.getId());
//            pishnehad = pishnehadService.findById(pishnehad.getId());

            if (theZamime.getFilePishDigitalId1() != null && theZamime.getFilePishDigitalId1() == Integer.parseInt(fileId)) {
                theZamime.setFilePishDigitalDescription1(null);
                theZamime.setFilePishDigitalId1(null);
                theZamime.setFilePishDigitalName1(null);
            } else if (theZamime.getFilePishDigitalId2() != null && theZamime.getFilePishDigitalId2() == Integer.parseInt(fileId)) {
                theZamime.setFilePishDigitalDescription2(null);
                theZamime.setFilePishDigitalId2(null);
                theZamime.setFilePishDigitalName2(null);
            } else if (theZamime.getFilePishDigitalId3() != null && theZamime.getFilePishDigitalId3() == Integer.parseInt(fileId)) {
                theZamime.setFilePishDigitalDescription3(null);
                theZamime.setFilePishDigitalId3(null);
                theZamime.setFilePishDigitalName3(null);
            } else if (theZamime.getFilePishDigitalId4() != null && theZamime.getFilePishDigitalId4() == Integer.parseInt(fileId)) {
                theZamime.setFilePishDigitalDescription4(null);
                theZamime.setFilePishDigitalId4(null);
                theZamime.setFilePishDigitalName4(null);
            } else if (theZamime.getFilePishDigitalId5() != null && theZamime.getFilePishDigitalId5() == Integer.parseInt(fileId)) {
                theZamime.setFilePishDigitalDescription5(null);
                theZamime.setFilePishDigitalId5(null);
                theZamime.setFilePishDigitalName5(null);
            } else if (theZamime.getFileFishId1() != null && theZamime.getFileFishId1() == Integer.parseInt(fileId)) {
                theZamime.setFileFishDescription1(null);
                theZamime.setFileFishId1(null);
                theZamime.setFileFishName1(null);
            } else if (theZamime.getFileFishId2() != null && theZamime.getFileFishId2() == Integer.parseInt(fileId)) {
                theZamime.setFileFishDescription2(null);
                theZamime.setFileFishId2(null);
                theZamime.setFileFishName2(null);
            } else if (theZamime.getFileFishId3() != null && theZamime.getFileFishId3() == Integer.parseInt(fileId)) {
                theZamime.setFileFishDescription3(null);
                theZamime.setFileFishId3(null);
                theZamime.setFileFishName3(null);
            } else if (theZamime.getFileFishId4() != null && theZamime.getFileFishId4() == Integer.parseInt(fileId)) {
                theZamime.setFileFishDescription4(null);
                theZamime.setFileFishId4(null);
                theZamime.setFileFishName4(null);
            } else if (theZamime.getFileFishId5() != null && theZamime.getFileFishId5() == Integer.parseInt(fileId)) {
                theZamime.setFileFishDescription5(null);
                theZamime.setFileFishId5(null);
                theZamime.setFileFishName5(null);
            } else if (theZamime.getFileSayerId1() != null && theZamime.getFileSayerId1() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription1(null);
                theZamime.setFileSayerId1(null);
                theZamime.setFileSayerName1(null);
            } else if (theZamime.getFileSayerId2() != null && theZamime.getFileSayerId2() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription2(null);
                theZamime.setFileSayerId2(null);
                theZamime.setFileSayerName2(null);
            } else if (theZamime.getFileSayerId3() != null && theZamime.getFileSayerId3() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription3(null);
                theZamime.setFileSayerId3(null);
                theZamime.setFileSayerName3(null);
            } else if (theZamime.getFileSayerId4() != null && theZamime.getFileSayerId4() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription4(null);
                theZamime.setFileSayerId4(null);
                theZamime.setFileSayerName4(null);
            } else if (theZamime.getFileSayerId5() != null && theZamime.getFileSayerId5() == Integer.parseInt(fileId)) {
                theZamime.setFileSayerDescription5(null);
                theZamime.setFileSayerId5(null);
                theZamime.setFileSayerName5(null);
            } else if (theZamime.getFilePishKatbiId1() != null && theZamime.getFilePishKatbiId1() == Integer.parseInt(fileId)) {
                theZamime.setFilePishKatbiDescription1(null);
                theZamime.setFilePishKatbiId1(null);
                theZamime.setFilePishKatbiName1(null);
            } else if (theZamime.getFilePishKatbiId2() != null && theZamime.getFilePishKatbiId2() == Integer.parseInt(fileId)) {
                theZamime.setFilePishKatbiDescription2(null);
                theZamime.setFilePishKatbiId2(null);
                theZamime.setFilePishKatbiName2(null);
            } else if (theZamime.getFilePishKatbiId3() != null && theZamime.getFilePishKatbiId3() == Integer.parseInt(fileId)) {
                theZamime.setFilePishKatbiDescription3(null);
                theZamime.setFilePishKatbiId3(null);
                theZamime.setFilePishKatbiName3(null);
            } else if (theZamime.getFilePishKatbiId4() != null && theZamime.getFilePishKatbiId4() == Integer.parseInt(fileId)) {
                theZamime.setFilePishKatbiDescription4(null);
                theZamime.setFilePishKatbiId4(null);
                theZamime.setFilePishKatbiName4(null);
            } else if (theZamime.getFilePishKatbiId5() != null && theZamime.getFilePishKatbiId5() == Integer.parseInt(fileId)) {
                theZamime.setFilePishKatbiDescription5(null);
                theZamime.setFilePishKatbiId5(null);
                theZamime.setFilePishKatbiName5(null);
            } else if (theZamime.getFileElameHesabId() != null && theZamime.getFileElameHesabId() == Integer.parseInt(fileId)) {
                theZamime.setFileElameHesabDescription(null);
                theZamime.setFileElameHesabId(null);
                theZamime.setFileElameHesabName(null);
            } else if (theZamime.getFileEnserafId() != null && theZamime.getFileEnserafId() == Integer.parseInt(fileId)) {
                theZamime.setFileEnserafDescription(null);
                theZamime.setFileEnserafId(null);
                theZamime.setFileEnserafName(null);
            } else if (theZamime.getFileMoshaverePezeshkId() != null && theZamime.getFileMoshaverePezeshkId() == Integer.parseInt(fileId)) {
                theZamime.setFileMoshaverePezeshkId(null);
                theZamime.setFileMoshaverePezeshkName(null);
                theZamime.setFileMoshaverePezeshkDesc(null);
            } else if (theZamime.getFileMoshaverePezeshkJavabId() != null && theZamime.getFileMoshaverePezeshkJavabId() == Integer.parseInt(fileId)) {
                theZamime.setFileMoshaverePezeshkJavabId(null);
                theZamime.setFileMoshaverePezeshkJavabName(null);
                theZamime.setFileMoshaverePezeshkJavabDesc(null);
            } else if (theZamime.getFileExcelKhanevadeId() != null && theZamime.getFileExcelKhanevadeId() == Integer.parseInt(fileId)) {
                theZamime.setFileExcelKhanevadeId(null);
                theZamime.setFileExcelKhanevadeName(null);
                theZamime.setFileExcelKhanevadeDesc(null);
            }
            pishnehadService.updateZamaem(theZamime);
            pishnehad = pishnehadService.findById(pishnehad.getId());
            Long userId = user.getId();
            retrieveByPishnehadId(pishnehad.getId(), userId);
            getSession().put("backfromupload", backfromupload);
            return SUCCESS;
        }
    }


    public String uploadFishMakeFinal() {
        backfromupload = "true";
        User user = getUser();
        if (user.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            pishnehad = pishnehadService.findById(pishnehad.getId());
            shomareSafhe = "1";
            if (uploadedFile == null) {
                errorsMap = new HashMap<String, String>();
                errorsMap.put("nofilesuploaded", "فایلی برای آپلود کردن انتخاب نشد.");
//                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                getSession().put("backfromupload", backfromupload);
                getSession().put("errorsMap", errorsMap);
                return ERROR;
            } else {
                if (uploadedFile.length() > Constant.MAXIMUM_FILE_SIZE_ALLOWED) {
                    errorsMap = new HashMap<String, String>();
                    errorsMap.put("maxsizereached", "حجم فایل آپلود شده از 1 مگابایت بیشتر است. لطفا حجم آن را کم نموده و مجددا تلاش نمایید.");
                    //                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                    getSession().put("backfromupload", backfromupload);
                    getSession().put("errorsMap", errorsMap);
                    return ERROR;
                } else if (!(uploadedFileName.toLowerCase().endsWith(".pdf") || uploadedFileName.toLowerCase().endsWith(".xlsx") || uploadedFileName.toLowerCase().endsWith(".xls"))) {
                    errorsMap = new HashMap<String, String>();
                    errorsMap.put("maxsizereached", "نوع فایل نامعتبر است.");
                    //                retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                    getSession().put("backfromupload", backfromupload);
                    getSession().put("errorsMap", errorsMap);
                    return ERROR;
                } else {
                    if (noeFile.equalsIgnoreCase("enseraf") && pishnehad.getZamaem().getFileEnserafId() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("elamhesab") && pishnehad.getZamaem().getFileElameHesabId() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("moshavere_pezeshk") && pishnehad.getZamaem().getFileMoshaverePezeshkId() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("moshavere_pezeshk_javab") && pishnehad.getZamaem().getFileMoshaverePezeshkJavabId() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("katbi") && shomareSafhe.equalsIgnoreCase("1") && pishnehad.getZamaem().getFilePishKatbiId1() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("katbi") && shomareSafhe.equalsIgnoreCase("2") && pishnehad.getZamaem().getFilePishKatbiId2() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("katbi") && shomareSafhe.equalsIgnoreCase("3") && pishnehad.getZamaem().getFilePishKatbiId3() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("katbi") && shomareSafhe.equalsIgnoreCase("4") && pishnehad.getZamaem().getFilePishKatbiId4() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("katbi") && shomareSafhe.equalsIgnoreCase("5") && pishnehad.getZamaem().getFilePishKatbiId5() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("digital") && shomareSafhe.equalsIgnoreCase("1") && pishnehad.getZamaem().getFilePishDigitalId1() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("digital") && shomareSafhe.equalsIgnoreCase("2") && pishnehad.getZamaem().getFilePishDigitalId2() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("digital") && shomareSafhe.equalsIgnoreCase("3") && pishnehad.getZamaem().getFilePishDigitalId3() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("digital") && shomareSafhe.equalsIgnoreCase("4") && pishnehad.getZamaem().getFilePishDigitalId4() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("digital") && shomareSafhe.equalsIgnoreCase("5") && pishnehad.getZamaem().getFilePishDigitalId5() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish") && shomareSafhe.equalsIgnoreCase("1") && pishnehad.getZamaem().getFileFishId1() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish") && shomareSafhe.equalsIgnoreCase("2") && pishnehad.getZamaem().getFileFishId2() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish") && shomareSafhe.equalsIgnoreCase("3") && pishnehad.getZamaem().getFileFishId3() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish") && shomareSafhe.equalsIgnoreCase("4") && pishnehad.getZamaem().getFileFishId4() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("fish") && shomareSafhe.equalsIgnoreCase("5") && pishnehad.getZamaem().getFileFishId5() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && shomareSafhe.equalsIgnoreCase("1") && pishnehad.getZamaem().getFileSayerId1() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && shomareSafhe.equalsIgnoreCase("2") && pishnehad.getZamaem().getFileSayerId2() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && shomareSafhe.equalsIgnoreCase("3") && pishnehad.getZamaem().getFileSayerId3() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && shomareSafhe.equalsIgnoreCase("4") && pishnehad.getZamaem().getFileSayerId4() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("sayer") && shomareSafhe.equalsIgnoreCase("5") && pishnehad.getZamaem().getFileSayerId5() != null) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("duplicateupload", "این فایل قبلا آپلود شده است. برای آپلود کردن مجدد ابتدا فایل قدیمی را حذف نمایید.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else if (noeFile.equalsIgnoreCase("excel_kh") && !pishnehad.getNoeBimename().equals("خانواده")) {
                        errorsMap = new HashMap<String, String>();
                        errorsMap.put("maxsizereached", "نوع بیمه نامه خانواده نمی باشد.");
                        //                    retrieveByPishnehadId(pishnehad.getId(),theResult.getId());
                        getSession().put("backfromupload", backfromupload);
                        getSession().put("errorsMap", errorsMap);
                        return ERROR;
                    } else {
                        AuthService_ServiceLocator l = new AuthService_ServiceLocator();
                        String sid = null;
                        try {
//                            if (tozihat == null || tozihat.length() < 1) tozihat = "-";

                            tozihat = "(" + DateUtil.getCurrentDate() + " " + DateUtil.getCurrentTime() + ") - " + tozihat;

                            sid = l.getAuthServiceImplPort().login(Constant.LogicalDocUserName, Constant.LogicalDocUserPass);

                            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
                            DocumentService documentService = docLocator.getDocumentServiceImplPort();

                            FolderServiceImplServiceLocator folderLocator = new FolderServiceImplServiceLocator();
                            FolderService folderService = folderLocator.getFolderServiceImplPort();
                            WsFolderHolder folderHolder = new WsFolderHolder();
                            WsFolder folder = new WsFolder();
                            pishnehad = pishnehadService.findById(pishnehad.getId());

                            String radif = pishnehad.getRadif();
                            radif = radif.replaceAll("/", "-");
                            folder.setName("files-" + radif);
                            WsFolder parent = folderService.getFolder(sid, Constant.LogicalDocLifeFolderId); // life
//                            folder.setParentId(folderService.getRootFolder(sid).getId());
                            folder.setParentId(Constant.LogicalDocLifeFolderId);
                            folderHolder.value = folder;
                            folderService.create(sid, folderHolder);

                            RandomAccessFile raf = new RandomAccessFile(uploadedFile, "r");
                            byte[] content = new byte[(int) raf.length()];
                            while (raf.getFilePointer() < raf.length()) {
                                content[((int) raf.getFilePointer())] = raf.readByte();
                            }
                            Zamaem theZamime = pishnehad.getZamaem();

                            if (noeFile.equalsIgnoreCase("katbi")) {
                                if (shomareSafhe.equalsIgnoreCase("1")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishKatbiDescription1(tozihat);
                                    theZamime.setFilePishKatbiId1(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishKatbiName1(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("2")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishKatbiDescription2(tozihat);
                                    theZamime.setFilePishKatbiId2(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishKatbiName2(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("3")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishKatbiDescription3(tozihat);
                                    theZamime.setFilePishKatbiId3(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishKatbiName3(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("4")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishKatbiDescription4(tozihat);
                                    theZamime.setFilePishKatbiId4(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishKatbiName4(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("5")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishKatbiDescription5(tozihat);
                                    theZamime.setFilePishKatbiId5(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishKatbiName5(document.getFileName());
                                }
                            } else if (noeFile.equalsIgnoreCase("digital")) {
                                if (shomareSafhe.equalsIgnoreCase("1")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishDigitalDescription1(tozihat);
                                    theZamime.setFilePishDigitalId1(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishDigitalName1(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("2")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishDigitalDescription2(tozihat);
                                    theZamime.setFilePishDigitalId2(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishDigitalName2(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("3")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishDigitalDescription3(tozihat);
                                    theZamime.setFilePishDigitalId3(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishDigitalName3(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("4")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishDigitalDescription4(tozihat);
                                    theZamime.setFilePishDigitalId4(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishDigitalName4(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("5")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFilePishDigitalDescription5(tozihat);
                                    theZamime.setFilePishDigitalId5(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFilePishDigitalName5(document.getFileName());
                                }
                            } else if (noeFile.equalsIgnoreCase("fish")) {
                                if (shomareSafhe.equalsIgnoreCase("1")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileFishDescription1(tozihat);
                                    theZamime.setFileFishId1(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileFishName1(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("2")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileFishDescription2(tozihat);
                                    theZamime.setFileFishId2(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileFishName2(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("3")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileFishDescription3(tozihat);
                                    theZamime.setFileFishId3(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileFishName3(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("4")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileFishDescription4(tozihat);
                                    theZamime.setFileFishId4(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileFishName4(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("5")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileFishDescription5(tozihat);
                                    theZamime.setFileFishId5(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileFishName5(document.getFileName());
                                }

                            } else if (noeFile.equalsIgnoreCase("sayer")) {
                                if (shomareSafhe.equalsIgnoreCase("1")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileSayerDescription1(tozihat);
                                    theZamime.setFileSayerId1(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileSayerName1(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("2")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileSayerDescription2(tozihat);
                                    theZamime.setFileSayerId2(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileSayerName2(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("3")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileSayerDescription3(tozihat);
                                    theZamime.setFileSayerId3(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileSayerName3(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("4")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileSayerDescription4(tozihat);
                                    theZamime.setFileSayerId4(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileSayerName4(document.getFileName());
                                } else if (shomareSafhe.equalsIgnoreCase("5")) {
                                    WsDocument document = new WsDocument();
                                    document.setFileName(uploadedFileName);
                                    document.setFileSize(uploadedFile.getTotalSpace());
                                    document.setFolderId(folderHolder.value.getId());
                                    WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                    documentService.create(sid, documentHolder, content);
                                    theZamime.setFileSayerDescription5(tozihat);
                                    theZamime.setFileSayerId5(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                    theZamime.setFileSayerName5(document.getFileName());
                                }
                            } else if (noeFile.equalsIgnoreCase("elamhesab")) {
                                WsDocument document = new WsDocument();
                                document.setFileName(uploadedFileName);
                                document.setFileSize(uploadedFile.getTotalSpace());
                                document.setFolderId(folderHolder.value.getId());
                                WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                documentService.create(sid, documentHolder, content);
                                theZamime.setFileElameHesabDescription(tozihat);
                                theZamime.setFileElameHesabId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                theZamime.setFileElameHesabName(document.getFileName());
                            } else if (noeFile.equalsIgnoreCase("moshavere_pezeshk")) {
                                WsDocument document = new WsDocument();
                                document.setFileName(uploadedFileName);
                                document.setFileSize(uploadedFile.getTotalSpace());
                                document.setFolderId(folderHolder.value.getId());
                                WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                documentService.create(sid, documentHolder, content);
                                theZamime.setFileMoshaverePezeshkDesc(tozihat);
                                theZamime.setFileMoshaverePezeshkId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                theZamime.setFileMoshaverePezeshkName(document.getFileName());
                            } else if (noeFile.equalsIgnoreCase("moshavere_pezeshk_javab")) {
                                WsDocument document = new WsDocument();
                                document.setFileName(uploadedFileName);
                                document.setFileSize(uploadedFile.getTotalSpace());
                                document.setFolderId(folderHolder.value.getId());
                                WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                documentService.create(sid, documentHolder, content);
                                theZamime.setFileMoshaverePezeshkJavabDesc(tozihat);
                                theZamime.setFileMoshaverePezeshkJavabId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                theZamime.setFileMoshaverePezeshkJavabName(document.getFileName());
                            } else if (noeFile.equalsIgnoreCase("enseraf")) {
                                WsDocument document = new WsDocument();
                                document.setFileName(uploadedFileName);
                                document.setFileSize(uploadedFile.getTotalSpace());
                                document.setFolderId(folderHolder.value.getId());
                                WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                documentService.create(sid, documentHolder, content);
                                theZamime.setFileEnserafDescription(tozihat);
                                theZamime.setFileEnserafId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                theZamime.setFileEnserafName(document.getFileName());
                            } else if (noeFile.equalsIgnoreCase("excel_kh")) {
                                WsDocument document = new WsDocument();
                                document.setFileName(uploadedFileName);
                                document.setFileSize(uploadedFile.getTotalSpace());
                                document.setFolderId(folderHolder.value.getId());
                                WsDocumentHolder documentHolder = new WsDocumentHolder(document);
                                documentService.create(sid, documentHolder, content);
                                theZamime.setFileExcelKhanevadeDesc(tozihat);
                                theZamime.setFileExcelKhanevadeId(Integer.parseInt(String.valueOf(documentHolder.value.getId())));
                                theZamime.setFileExcelKhanevadeName(document.getFileName());
                            }
                            if (!noeFile.equalsIgnoreCase("khesarat")) {
                                pishnehadService.updateZamaem(theZamime);
                                pishnehadService.updatePishnehad(pishnehad);
                                pishnehad = pishnehadService.findById(pishnehad.getId());
                                Long userId = user.getId();
                                getSession().put("backfromupload", backfromupload);
                                retrieveByPishnehadId(pishnehad.getId(), userId);
                                raf.close();
                                uploadedFile = null;
                            }
                            return SUCCESS;
                        } catch (Exception e) {
                            Long userId = user.getId();
                            retrieveByPishnehadId(pishnehad.getId(), userId);
                            pishnehad = pishnehadService.findById(pishnehad.getId());
                            logicaldocIndicator = Constant.ERROR;
                            getSession().put("backfromupload", backfromupload);
                            getSession().put("logicaldocindicator", logicaldocIndicator);
                            getSession().put("errorsMap", errorsMap);
                            return ERROR;
                        }
                    }
                }
            }
        }
    }

    public String pardakhtShenasedaar() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            fish.setPaymentType(Constant.SHENASEDAR);
            String orderId = fish.getShomare();
            if (!orderId.equalsIgnoreCase("error")) {
                fish.setShomare(orderId);
                int fishId = pishnehadService.saveFish(fish);
                Fish theFish = pishnehadService.findFish(fishId);

                Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
                theFish.setPishnehad(thePish);
                thePish.getFishs().add(theFish);
                thePish.setPishpardakhtType(Constant.SHENASEDAR);
                //        thePish.setPishpardakhtType("fish");
                pishnehadService.updateFish(theFish);
                pishnehadService.updatePishnehad(thePish);
                //now I should save the fish!
                //        pishnehadService.transitPishnehad(userId, pishnehadId, transitionId, logmessage);

                pishnehad = pishnehadService.findById(thePish.getId());
                addActionMessage(getText("pardakht.success.done"));
                shenaseIjadShode = orderId;
            } else {
                errorPardakhtShenasedar = true;
            }
            pishnehad = pishnehadService.findById(pishnehad.getId());
            retrieveByPishnehadId(pishnehad.getId(), userId);

            return SUCCESS;
        }

    }

    public String pardakhtBaFishMakeFinal() throws IOException, ServiceException {

        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            errorsMap = new HashMap<String, String>();
            messagesMap = new HashMap<String, String>();

            Long userId = user.getId();

            if (fish.getMablagh().equalsIgnoreCase("")) {
                errorsMap.put("emptyMablagh", "این فیلد باید حتما مقدار داشته باشد.");
            } else {
                if (!isNumber(fish.getMablagh().replace(",", ""))) {
                    errorsMap.put("notdigitMablagh", "مقدار این فیلد باید حتما عددی باشد.");
                }
            }
            if (fish.getShomare().equalsIgnoreCase("")) {
                errorsMap.put("emptyShomare", "این فیلد باید حتما مقدار داشته باشد.");
            } else {
                if (!isNumber(fish.getShomare())) {
                    errorsMap.put("notdigitShomare", "مقدار این فیلد باید حتما عددی باشد.");
                }
            }
            if (fish.getBankName().equalsIgnoreCase("")) {
                errorsMap.put("emptyBankName", "این فیلد باید حتما مقدار داشته باشد.");
            } else {
                if (!isOnlyWords(fish.getBankName())) {
                    errorsMap.put("notwordBankName", "مقدار این فیلد باید تنها شامل حروف باشد.");
                }
            }
            if (fish.getKodeShobe().equalsIgnoreCase("")) {
                errorsMap.put("emptyKodeShobe", "این فیلد باید حتما مقدار داشته باشد.");
            } else {
                if (!isNumber(fish.getKodeShobe())) {
                    errorsMap.put("notdigitKodeShobe", "مقدار این فیلد باید حتما عددی باشد.");
                }
            }
            if (fish.getTarikh().equalsIgnoreCase("")) {
                errorsMap.put("emptyTarikh", "این فیلد باید حتما مقدار داشته باشد.");
            }
            if (fish.getTime().equalsIgnoreCase("")) {
                errorsMap.put("emptyTime", "این فیلد باید حتما مقدار داشته باشد.");
            }
            List<Fish> fishs = pishnehadService.findFish(fish.getShomare(), fish.getBankName(), fish.getKodeShobe(), fish.getTime(), fish.getTarikh());
            if (fishs.size() != 0) {
                errorsMap.put("duplicateFish", "فیش مورد نظر قبلا ثبت شده است.");
            }
            if (errorsMap.size() == 0) {
                fish.setPaymentType(Constant.FISH);
                int fishId = pishnehadService.saveFish(fish);
                Fish theFish = pishnehadService.findFish(fishId);
                theFish.setFound("false");
                List<Credebit> credebits = asnadeSodorService.findCredebitForTaeedFish(theFish);
                for (Credebit credebit : credebits) {
                    if (OmrUtil.doesMinuteAndSecondMatch(theFish.getTime(), credebit.getCreatedTime())) {
                        theFish.setFound("true");
                        theFish.setCredebit(credebit);
                        credebit.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
                        asnadeSodorService.updateCredebit(credebit);
                        pishnehadService.updateFish(theFish);
                        break;
                    }
                }
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                theFish.setPishnehad(thePish);
                thePish.getFishs().add(theFish);
                thePish.setPishpardakhtType(Constant.FISH);
                pishnehadService.updateFish(theFish);
                pishnehadService.updatePishnehad(thePish);
                pishnehad = pishnehadService.findById(thePish.getId());
                retrieveByPishnehadId(pishnehad.getId(), userId);
                messagesMap.put("pardakht.success", "پرداخت با موفقیت انجام شد");
                getSession().put(Constant.MESSAGES_MAP, messagesMap);
                addActionMessage(getText("pardakht.success.done"));
                return SUCCESS;
            } else {
                Pishnehad thePish = pishnehadService.findById(Integer.parseInt(pishnehadId));
                pishnehad = pishnehadService.findById(thePish.getId());
                retrieveByPishnehadId(pishnehad.getId(), userId);
                getSession().put(Constant.ERRORS_MAP, errorsMap);
                getSession().put("fish", fish);
                return ERROR;
            }
        }
    }

    public String getOrderIdForShenase() {
        shenaseIjadShode = getCenterPaymentId();
        return SUCCESS;
    }

    public String ajaxFishSanadzani() {
        credebit = asnadeSodorService.findCretebitById(credebit.getId());
        credebit.setStatus(Credebit.Status.SANAD_KHORDE);
        asnadeSodorService.updateCredebit(credebit);
        return SUCCESS;
    }

    public String ajaxFishSearch() {
        Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
        List<Fish> theFishs = thePish.getFishs();
        for (Fish theFish : theFishs) {
            List<Credebit> credebits = asnadeSodorService.findCredebitForTaeedFish(theFish);
            for (Credebit credebit : credebits) {
                if (OmrUtil.doesMinuteAndSecondMatch(theFish.getTime(), credebit.getCreatedTime())) {
                    theFish.setFound("true");
                    theFish.setCredebit(credebit);
                    credebit.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
                    asnadeSodorService.updateCredebit(credebit);
                    pishnehadService.updateFish(theFish);
                    break;
                }
            }
        }
        pishnehadService.updatePishnehad(thePish);
        fishs = thePish.getFishs();
        pishnehad = thePish;
        return SUCCESS;
    }

    public String ajaxFishUpdate() {
        Fish theFish = pishnehadService.findFish(fish.getId());
        theFish.setFound("true");
        theFish.setKodeShobe(fish.getKodeShobe());
        theFish.setMablagh(fish.getMablagh());
        theFish.setShomare(fish.getShomare());
        theFish.setTarikh(fish.getTarikh());
        theFish.setTime(fish.getTime());
        credebit = asnadeSodorService.findCretebitById(credebit.getId());
        credebit.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
        theFish.setCredebit(credebit);
        asnadeSodorService.updateCredebit(credebit);
        pishnehadService.updateFish(theFish);
        return SUCCESS;
    }

    public String makeSearchForFish() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            foundFishes = pishnehadService.searchForFish(bankInfo);
            Set<Credebit> foundFishesSet = new HashSet<Credebit>();
            foundFishesSet.addAll(foundFishes);
            foundFishes = new ArrayList<Credebit>();
            for (Credebit credebit : foundFishesSet) {
                foundFishes.add(credebit);
            }
            pishnehad = pishnehadService.findById(pishnehad.getId());
            retrieveByPishnehadId(pishnehad.getId(), userId);
            return SUCCESS;
        }
    }

    public String enserafBazgashtBeBimeOmr() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            retrieveByPishnehadId(thePish.getId(), userId);
            return SUCCESS;
        }
    }

    public String pardakhtInternetiMakeFinal() throws Exception {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAction.class);
            String header = String.format("Payment (INTERNET, PISHPARDAKHT) authId: %s | ", authorityId);
            logger.info(String.format(header + "bankResponse: %s pishnehad.id: %s"
                    , bankResponse, pishnehad.getId().toString()));
            Long userId = user.getId();
            String pin = "78d6LN3o6isGu5Wj448C";
            EShopServiceLocator l = new EShopServiceLocator();
            EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
            UnsignedByte statusVal = new UnsignedByte(100);
            UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
            if (Integer.parseInt(bankResponse) == 0 && authorityId != null) {
                lsoap.pinPaymentEnquiry(pin, Long.parseLong(authorityId), status);
            }
            logger.info(String.format(header + "status: %s"
                    , status.value.toString()));
            if (status.value.longValue() == 0) {
                Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
                Fish theFish = new Fish();
                theFish.setMablagh(fish.getMablagh());
                theFish.setPaymentType(Constant.INTERNETI);
                theFish.setTarikh(DateUtil.getCurrentDate());
                theFish.setPishnehad(thePish);
                theFish.setBankName("تجارت الکترونیک");
                theFish.setFound("true");
                theFish.setTime(DateUtil.getCurrentTime());
                theFish.setShomare(authorityId);
                Credebit credebit = new Credebit(theFish.getMablagh(), sequenceService.nextShenaseCredebit(), null, theFish.getPishnehad(), Credebit.CredebitType.DARYAFTE_ELECTRONICI);
                logger.info(header + "credebit init done.");
                credebit.setAuthorityId(authorityId);
                credebit.setTimeFish(theFish.getTime());
                credebit.setDateFish(theFish.getTarikh());
                credebit.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
                credebit.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
                credebit.setPishnehad(thePish);
                theFish.setCredebit(credebit);
                asnadeSodorService.saveCredebit(credebit);
                logger.info(header + "credebit saved.");
                pishnehadService.saveFish(theFish);
                logger.info(header + "fish saved.");
                thePish.getFishs().add(theFish);
                thePish.setPishpardakhtType(Constant.INTERNETI);
                pishnehadService.updatePishnehad(thePish);
                pishnehad = pishnehadService.findById(thePish.getId());
                retrieveByPishnehadId(pishnehad.getId(), userId);
//                addActionMessage(getText("pardakht.success.done"));
                etebar = credebit;
                pardakhtInternetiAction = true;
                return SUCCESS;

            } else return ERROR;
        }
    }

    public String ePayment() {
        return SUCCESS;
    }

    public String debitSearch() {
        Credebit c = new Credebit();
        if (credebit.getShomareMoshtari() != null)
            c = asnadeSodorService.findCredebitByCodeMoshtari(credebit.getShomareMoshtari());
        if (c != null && c.getId() != null && (c.getCredebitType().equals(Credebit.CredebitType.GHEST_VAM) || c.getCredebitType().equals(Credebit.CredebitType.GHEST)))
            credebit = c;
        return SUCCESS;
    }

    public String paymentDebit() throws Exception {
//        if (Integer.parseInt(fish.getMablagh().replace(",", "")) > 20000000)
//        {
//            errorsMap = new HashMap<String, String>();
//            errorsMap.put("maxMablaghInterneti", "سقف پرداخت اینترنتی مبلغ 20.000.000 ریال می‌باشد. در صورت نیاز به پرداخت مبالغ بالاتر می توانید بیش از یک پرداخت اینترنتی انجام دهید و در چندین مرحله پیش‌پرداخت خود را تکمیل نمایید");
//            getSession().put(Constant.ERRORS_MAP, errorsMap);
//            return ERROR;
//        }

        Credebit c = asnadeSodorService.findCretebitById(credebit.getId());
        if (c == null || c.getRemainingAmount_long() <= 0) {
            errorsMap = new HashMap<String, String>();
            errorsMap.put("credebitNotAvailable", "خطا در سیستم");
            getSession().put(Constant.ERRORS_MAP, errorsMap);
            return ERROR;
        }
        credebit = c;
        String pin = "78d6LN3o6isGu5Wj448C";
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = prop.getProperty("EPaymentHost");
        epaymentHost = property;
        String pgwPage = epaymentHost + "/pecpaymentgateway";


        EShopServiceLocator l = new EShopServiceLocator();
        EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
        InetAddress inetAddress = InetAddress.getLocalHost();
        String callBackUrl = StringUtil.getDeploymentPath() + "/jsp/user/page_paymentAccept.jsp?credebit.id=" + credebit.getId();
        LongHolder authority = new LongHolder(0);
        UnsignedByte statusVal = new UnsignedByte(100);
        UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
        int textOrderId = getOrderId();
//            int textOrderId = 18545487;
        if (textOrderId != -1) {
            try {
                Integer mablagh = credebit.getRemainingAmount_long().intValue();
                if (mablagh > 200000000)
                    mablagh = 20000000;
                lsoap.pinPaymentRequest(pin, mablagh, textOrderId, callBackUrl, authority, status);
                authorityId = String.valueOf(authority.value);
                final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAction.class);
                String header = String.format("Payment (INTERNET, GHEST) authId: %s | ", authorityId);
                logger.info(String.format(header + "payment attempt. mablagh: %s pishnehad.id: %s", mablagh, credebit.getPishnehad() != null ? credebit.getPishnehad().getId() : ""));
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use UploadedFile | Settings | UploadedFile Templates.
            }
        }
        if (textOrderId == -1) {
            errorPardakht = true;
            return ERROR;
        } else if (status.value.equals(PgwStatus.Successful)) {
            return SUCCESS;
        } else return ERROR;
    }

    public String ePaymentMakeFinal() throws Exception {
        User user = getUser();
        Credebit bedehi = asnadeSodorService.findCretebitById(credebit.getId());
        String pin = "78d6LN3o6isGu5Wj448C";
        EShopServiceLocator l = new EShopServiceLocator();
        EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
        UnsignedByte statusVal = new UnsignedByte(100);
        UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
        if (Integer.parseInt(bankResponse) == 0 && authorityId != null) {
            lsoap.pinPaymentEnquiry(pin, Long.parseLong(authorityId), status);
        }
        if (status.value.longValue() == 0) {

            long amount = bedehi.getRemainingAmount_long();
            if (amount > 2000000000)
                amount = 2000000000;
//            Credebit etebar = new Credebit(Long.toString(amount), sequenceService.nextShenaseCredebit(), null, bedehi.getPishnehad(), Credebit.CredebitType.DARYAFTE_ELECTRONICI);
            etebar = new Credebit(Long.toString(amount), sequenceService.nextShenaseCredebit(), null, bedehi.getPishnehad(), Credebit.CredebitType.DARYAFTE_ELECTRONICI);
            etebar.setAuthorityId(authorityId);
            etebar.setShomareMoshtari(bedehi.getShomareMoshtari());
            etebar.setTimeFish(DateUtil.getCurrentTime());
            etebar.setDateFish(DateUtil.getCurrentDate());
            etebar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
            etebar.setStatus(Credebit.Status.SANAD_KHORDE_FISH);
            asnadeSodorService.saveCredebit(etebar);
            credebit = bedehi;
            sanad = asnadeSodorService.sabteSanad(user, bedehi, etebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
            addActionMessage(getText("pardakht.success.done"));
            return SUCCESS;

        } else return ERROR;
    }

    public String pardakhtInterneti() throws Exception {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (Integer.parseInt(fish.getMablagh().replace(",", "")) > 2000000000) {
                errorsMap = new HashMap<String, String>();
                errorsMap.put("maxMablaghInterneti", "سقف پرداخت اینترنتی مبلغ 200,000,000 ریال می‌باشد. در صورت نیاز به پرداخت مبالغ بالاتر می توانید بیش از یک پرداخت اینترنتی انجام دهید و در چندین مرحله پیش‌پرداخت خود را تکمیل نمایید");
                getSession().put(Constant.ERRORS_MAP, errorsMap);
                return ERROR;
            }
            String pin = "78d6LN3o6isGu5Wj448C";
            Properties prop = new Properties();
            try {
                prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String property = prop.getProperty("EPaymentHost");
            epaymentHost = property;
            String pgwPage = epaymentHost + "/pecpaymentgateway";
            Pishnehad thePishnehad = pishnehadService.findById(pishnehad.getId());
            EShopServiceLocator l = new EShopServiceLocator();
            EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
            InetAddress inetAddress = InetAddress.getLocalHost();
            String callBackUrl = StringUtil.getDeploymentPath() + "/jsp/pishnahad/page_taeedInterneti.jsp?pishnehad.id=" + pishnehad.getId() + "&fish.mablagh=" + fish.getMablagh();
            LongHolder authority = new LongHolder(0);
            UnsignedByte statusVal = new UnsignedByte(100);
            UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
            int textOrderId = getOrderId();
//            int textOrderId = 18545487;
            if (textOrderId != -1) {
                try {
                    String mablagh = fish.getMablagh();
                    mablagh = mablagh.replaceAll(",", "");
                    lsoap.pinPaymentRequest(pin, Integer.parseInt(mablagh), textOrderId, callBackUrl, authority, status);
                    authorityId = String.valueOf(authority.value);
                    final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAction.class);
                    String header = String.format("Payment (INTERNET, PISHPARDAKHT) authId: %s | ", authorityId);
                    logger.info(String.format(header + "payment attempt. mablagh: %s pishnehad.id: %s", mablagh, thePishnehad.getId()));
                } catch (RemoteException e) {
                    e.printStackTrace();  //To change body of catch statement use UploadedFile | Settings | UploadedFile Templates.
                }
            }
            if (textOrderId == -1) {
                retrieveByPishnehadId(thePishnehad.getId(), user.getId());
                pishnehad = thePishnehad;
                errorPardakht = true;
                return ERROR;
            } else if (status.value.equals(PgwStatus.Successful)) {


                return SUCCESS;

            } else return ERROR;
        }


    }


    public String downloadFile() throws ServiceException, RemoteException {

        FileService.Document theDoc = fileService.getDocument(Long.parseLong(fileId));
        contentType = "application/octet-stream";
        fileName = theDoc.getDoc().getFileName();
        byte[] bytes = theDoc.getBytes();
        inputStream = new ByteInputStream(bytes, bytes.length);
        bufferSize = bytes.length + "";
        return SUCCESS;

    }

    public String taeedeNazarePezeshk() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
            PezeshkSabtNazar pzn = new PezeshkSabtNazar();
            pzn.setFromPezeshk(false);
            pzn.setEzafeNerkh(pezeshkSabtNazar.getEzafeNerkh());
            pzn.setNazar(logmessage);
            pzn.setPishnehad(thePish);
            int pznId = pishnehadService.addNewNazarPezeshk(pzn);

            PezeshkSabtNazar thePzn = pishnehadService.findPezeshkSabtNazarById(pznId);
            thePish.getNazaraatePezeshk().add(thePzn);
            thePish.getEstelam().setDarsad_ezafe_nerkh_pezeshki(pezeshkSabtNazar.getEzafeNerkh());
            pishnehadService.updatePishnehad(thePish);
            pishnehad = pishnehadService.findById(thePish.getId());
            pishnehadService.transitPishnehad(userId, String.valueOf(pishnehad.getId()), transitionId, logmessage);
            retrieve();
            return SUCCESS;
        }
    }

    public String removeNazarPezeshkTaghirat() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            PezeshkSabtNazar pzn = pishnehadService.findPezeshkSabtNazarById(pezeshkSabtNazar.getId());
            pishnehadService.removeNazarPezeshk(pezeshkSabtNazar.getId());
            darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
            return SUCCESS;
        }
    }

    public String addNazarPezeshkTaghirat() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pezeshkSabtNazar.setFromPezeshk(true);
            int pznId = pishnehadService.addNewNazarPezeshk(pezeshkSabtNazar);
            PezeshkSabtNazar pzn = pishnehadService.findPezeshkSabtNazarById(pznId);
            DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            pzn.setDarkhastTaghirat(theDarkhast);
            theDarkhast.getNazaraatePezeshk().add(pzn);
            darkhastService.updateDarkhastTaghirat(theDarkhast);
            pishnehadService.updatePezeshkSabtNazar(pzn);
            darkhastTaghirat = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            retrieveByDarkhastTaghiratId(darkhastTaghirat.getId(), userId);
            return SUCCESS;
        }

    }

    public String addNazarPezeshk() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pezeshkSabtNazar.setFromPezeshk(true);
            int pznId = pishnehadService.addNewNazarPezeshk(pezeshkSabtNazar);
            PezeshkSabtNazar pzn = pishnehadService.findPezeshkSabtNazarById(pznId);
            Pishnehad thePishnehad = pishnehadService.findById(pishnehad.getId());
            pzn.setPishnehad(thePishnehad);
            thePishnehad.getNazaraatePezeshk().add(pzn);
            pishnehadService.updatePishnehad(thePishnehad);
            pishnehadService.updatePezeshkSabtNazar(pzn);
            int ezafeNerkh = 0;
            for (PezeshkSabtNazar pn : thePishnehad.getNazaraatePezeshk()) {
                ezafeNerkh += Integer.parseInt(pn.getEzafeNerkh());
            }
            thePishnehad.getEstelam().setDarsad_ezafe_nerkh_pezeshki(String.valueOf(ezafeNerkh));
            pishnehadService.updatePishnehad(thePishnehad);
            pishnehad = pishnehadService.findById(pishnehad.getId());
            return SUCCESS;
        }
    }

    public String saveEzafeNerkhAjaxly() {
        User user = getUser();
        Pishnehad thePish = pishnehadService.findById(pishnehad.getId());
        if (fromReadOnlyMode == null || fromReadOnlyMode.equals("false")) {
            if (thePish.getBimename() != null && thePish.getBimename().getReadyToShow().equals("yes")) {
                addActionMessage("uneditable");
                return SUCCESS;
            }
            for (TransitionLog tl : thePish.getTransitionLogs()) {
                if (tl.getTransition() != null && (tl.getTransition().getStateEnd().getId() == 200 || tl.getTransition().getStateEnd().getId() == 246)) {
                    if (!(OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_NAZER) || OmrUtil.userHasRole(user, Constant.ROLE_KARSHENAS_MASOUL)
                            || OmrUtil.userHasRole(user, Constant.ROLE_RAEIS_SODUR))) {
                        addActionMessage("uneditable");
                        return SUCCESS;
                    }
                }
            }
            thePish.getEstelam().setDarsad_ezafe_nerkh_pezeshki(darsad_ezafe);
            pishnehadService.updatePishnehad(thePish);
            addActionMessage("done");
        } else {
            if (!thePish.getValid()) {
                thePish.getEstelam().setDarsad_ezafe_nerkh_pezeshki(darsad_ezafe);
                pishnehadService.updatePishnehad(thePish);
            }
            addActionMessage("elhaghie");
            return SUCCESS;
        }
        return SUCCESS;
    }

    public String addNazarPezeshk_Taeedie() {
        pishnehad = pishnehadService.findById(pishnehad.getId());
        pishnehad.setAdamTaeedPezeshki(taeedCheckBox);
        pishnehadService.updatePishnehad(pishnehad);
        return SUCCESS;
    }

    public String saveEzafeNerkheRaees() {
        String sarmayeFoteHadese = "";
        if (pishnehad.getEstelam().getSarmaye_paye_fot_dar_asar_hadese() != null && !pishnehad.getEstelam().getSarmaye_paye_fot_dar_asar_hadese().isEmpty())
            sarmayeFoteHadese = pishnehad.getEstelam().getSarmaye_paye_fot_dar_asar_hadese();
        pishnehad = pishnehadService.findById(pishnehad.getId());
        pishnehad.getEstelam().setDarsad_ezafe_nerkh_pezeshki(darsad_ezafe);
        if (sarmayeFoteHadese != null && !sarmayeFoteHadese.isEmpty()) {
            pishnehad.getEstelam().setSarmaye_paye_fot_dar_asar_hadese(sarmayeFoteHadese);
            pishnehad.getEstelam().setInsertRaeisSarmayeHadese(true);
        }
        pishnehadService.updatePishnehad(pishnehad);
        return SUCCESS;
    }

    public String removeNazarPezeshk() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pishnehad = pishnehadService.findById(pishnehad.getId());
            PezeshkSabtNazar pzn = pishnehadService.findPezeshkSabtNazarById(pezeshkSabtNazar.getId());
            String currentEzafeNerkhString = pishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki();
            int currentEzafeNerkh = 0;
            if (currentEzafeNerkhString.length() > 0)
                currentEzafeNerkh = Integer.parseInt(currentEzafeNerkhString);
            pishnehad.getEstelam().setDarsad_ezafe_nerkh_pezeshki(String.valueOf(currentEzafeNerkh - Integer.parseInt(pzn.getEzafeNerkh())));
//            thePishnehad.setPrivateMessages(privateMessage);
            pishnehadService.updatePishnehad(pishnehad);
            pishnehadService.removeNazarPezeshk(pezeshkSabtNazar.getId());
            retrieveByPishnehadId(pishnehad.getId(), userId);
            pishnehad = pishnehadService.findById(pishnehad.getId());
            return SUCCESS;
        }
    }

    public String addNazarRaeis() {
        int pznId = pishnehadService.addNewNazarRaeis(pezeshkSabtNazar);
        PezeshkSabtNazar pzn = pishnehadService.findPezeshkSabtNazarById(pznId);
        Pishnehad thePishnehad = pishnehadService.findById(Integer.parseInt(pishnehadId));
        pzn.setPishnehad(thePishnehad);
        thePishnehad.getNazaraatePezeshk().add(pzn);
        pishnehadService.updatePishnehad(thePishnehad);
        pishnehadService.updatePezeshkSabtNazar(pzn);
        List<PezeshkSabtNazar> preNazaraat = thePishnehad.getNazaraatePezeshk();
        List<PezeshkSabtNazar> finalNazaraat = new ArrayList<PezeshkSabtNazar>();
        for (PezeshkSabtNazar pznn : preNazaraat) {
            if (pznn.isFromPezeshk()) {
                finalNazaraat.add(pznn);
            }
        }
        nazaraat = finalNazaraat;
        return SUCCESS;
    }

    private Moarefiname moarefiname;

    public String sodurMoarefiname() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Pishnehad thePishnehad = pishnehadService.findById(Integer.parseInt(pishnehadId));
            moarefiname.setPishnehad(thePishnehad);
            moarefiname.setVaziat(Moarefiname.Vaziat.DAR_JARYAN);
            pishnehadService.saveMoarefiname(moarefiname);
            pishnehad = pishnehadService.findById(Integer.parseInt(pishnehadId));
            return SUCCESS;
        }

    }

    public String ebtalMoarefiname() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            pishnehad = pishnehadService.findById(pishnehad.getId());
            Moarefiname theMoarefiname = pishnehadService.findMoarefiname(moarefiname.getId());
            pishnehadService.ebtalMoarefiname(theMoarefiname);
            return SUCCESS;
        }

    }

    public String addMoarefinameForTgrAjaxly() {
        DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
        moarefiname.setDarkhastTaghirat(theDarkhast);
        pishnehadService.saveMoarefiname(moarefiname);
        darkhastTaghirat = theDarkhast;
        pishnehad = pishnehadService.findById(pishnehad.getId());
        return SUCCESS;
    }

    public String sodurMoarefinameForTaghirat() {
        User user = getUser();
        if (user == null) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Long userId = user.getId();
            DarkhastTaghirat theDarkhast = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
            darkhastService.transitDarkhastTaghirat(user, darkhastTaghirat.getId(), Integer.parseInt(transitionId), logmessage);
//            moarefiname.setDarkhastTaghirat(theDarkhast);
//            pishnehadService.saveMoarefiname(moarefiname);
            retrieve();
            return SUCCESS;
        }

    }

    public void setMoarefiname(Moarefiname moarefiname) {
        this.moarefiname = moarefiname;
    }

    public Moarefiname getMoarefiname() {
        return moarefiname;
    }

    public String anjameMohasebatePaye() {
        Pishnehad thePishnehad = pishnehadService.findById(pishnehad.getId());

        Estelam estelam = thePishnehad.getEstelam();
        sharayeteJadid = thePishnehad.getSharayeteJadid();
        if (sharayeteJadid.getHagh_bime_pardakhti() != null) {
            estelam.setHagh_bime_pardakhti(sharayeteJadid.getHagh_bime_pardakhti());
        }
        if (sharayeteJadid.getMablagh_seporde_ebtedaye_sal() != null) {
            estelam.setMablagh_seporde_ebtedaye_sal(sharayeteJadid.getMablagh_seporde_ebtedaye_sal());
        }

        if (sharayeteJadid.getModat_bimename() != null) {
            estelam.setModat_bimename(sharayeteJadid.getModat_bimename());
        }
        if (sharayeteJadid.getNahve_pardakht_hagh_bime() != null) {
            estelam.setNahve_pardakht_hagh_bime(sharayeteJadid.getNahve_pardakht_hagh_bime());
        }
        if (sharayeteJadid.getNerkh_afzayesh_salaneh_hagh_bime() != null) {
            estelam.setNerkh_afzayesh_salaneh_hagh_bime(sharayeteJadid.getNerkh_afzayesh_salaneh_hagh_bime());
        }
        if (sharayeteJadid.getNerkh_afzayesh_salaneh_sarmaye_fot() != null) {
            estelam.setNerkh_afzayesh_salaneh_sarmaye_fot(sharayeteJadid.getNerkh_afzayesh_salaneh_sarmaye_fot());
        }
        if (sharayeteJadid.getPooshesh_amraz_khas() != null) {
            estelam.setPooshesh_amraz_khas(sharayeteJadid.getPooshesh_amraz_khas());
        }
        if (sharayeteJadid.getPooshesh_fot_dar_asar_haadese() != null) {
            estelam.setPooshesh_fot_dar_asar_haadese(sharayeteJadid.getPooshesh_fot_dar_asar_haadese());
        }
        if (sharayeteJadid.getPooshesh_fot_dar_asar_zelzele() != null) {
            estelam.setPooshesh_fot_dar_asar_zelzele(sharayeteJadid.getPooshesh_fot_dar_asar_zelzele());
        }
        if (sharayeteJadid.getPooshesh_moafiat() != null) {
            estelam.setPooshesh_moafiat(sharayeteJadid.getPooshesh_moafiat());
        }
        if (sharayeteJadid.getPooshesh_naghs_ozv() != null) {
            estelam.setPooshesh_naghs_ozv(sharayeteJadid.getPooshesh_naghs_ozv());
        }
        if (sharayeteJadid.getSarmaye_paye_fot() != null) {
            estelam.setSarmaye_paye_fot(sharayeteJadid.getSarmaye_paye_fot());
        }
        if (sharayeteJadid.getSarmaye_paye_fot_dar_asar_hadese() != null) {
            estelam.setSarmaye_paye_fot_dar_asar_hadese(sharayeteJadid.getSarmaye_paye_fot_dar_asar_hadese());
        }
        if (sharayeteJadid.getSarmaye_pooshesh_amraz_khas() != null) {
            estelam.setSarmaye_pooshesh_amraz_khas(sharayeteJadid.getSarmaye_pooshesh_amraz_khas());
        }
        if (sharayeteJadid.getSarmaye_pooshesh_naghs_ozv() != null) {
            estelam.setSarmaye_pooshesh_naghs_ozv(sharayeteJadid.getSarmaye_pooshesh_naghs_ozv());
        }
        calculate(thePishnehad);
        return SUCCESS;
    }

    public String anjameMohasebatePayeForVaziateGhestBandi() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        pishnehad = pishnehadService.findById(pishnehad.getId());
        MohasebateTeory mohasebateTeory = new MohasebateTeory();

        if (pishnehad.getBimename() != null)
            pishnehad = pishnehad.getBimename().getPishnehad();

        List<TaghsitReport> tr = mohasebateTeory.taghsitReport(pishnehad, true, -1);
        Estelam estel = pishnehad.getEstelam();

//        String tarikhShorou=DateUtil.getCurrentDate();
//        MohasebateTeoriView mohasebateTeoriView = new MohasebateTeoriView();
//
//        int count = count = MohasebateTeoriView.PERIOD_COUNT_IN_SAALANEH;
//
//        if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("mah"))
//            count= MohasebateTeoriView.PERIOD_COUNT_IN_MAHANEH;
//
//        else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("3mah"))
//            count = MohasebateTeoriView.PERIOD_COUNT_IN_3MAHE;
//
//        else if (estel.getNahve_pardakht_hagh_bime().equalsIgnoreCase("6mah"))
//            count = MohasebateTeoriView.PERIOD_COUNT_IN_6MAHE;
//
//        ghestAmount = ((Double) mohasebateTeoriView.convertViewToCustomPeriod_saleBimei(tarikhShorou, tr.get(0), count, pishnehad).get(0).getHaghBimePardaakhtiSaal()).longValue();

        ghestAmount = Long.parseLong(estel.getHagh_bime_pardakhti().replaceAll(",", "").trim()) + Long.parseLong(estel.getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").trim());
        if (ghestAmount == pishnehad.getFishValue()) {
            fishEQghest = true;
        } else {
            fishEQghest = false;
        }

        if (tr != null) {
            fRsList = new ArrayList<FRs>();
            for (TaghsitReport t : tr) {
                fRsList.add(t.getfRs());
            }
            correctCalculation = true;
        } else {
            correctCalculation = false;
        }
        return SUCCESS;
    }

    public String searchForThisNewShomareMoshtari() {
//        List<Credebit> allTheGhests = asnadeSodorService.findAllBedehiCredebits();
//        int truthChecker = 0;
//        for (Credebit ghest : allTheGhests) {
//            if (truthChecker == 0) {
//                if (ghest.getShomareMoshtari().trim().equalsIgnoreCase(bankInfo.getKodeDaryaft().trim())) {
//                    if (Double.parseDouble(ghest.getRemainingAmount()) == Double.parseDouble(bankInfo.getMablagh())) {
//                        truthChecker = 1;
//                    } else if (Double.parseDouble(ghest.getRemainingAmount()) > Double.parseDouble(bankInfo.getMablagh())) {
//                        truthChecker = 2;
//                    } else if (Double.parseDouble(ghest.getRemainingAmount()) < Double.parseDouble(bankInfo.getMablagh())) {
//                        truthChecker = 3;
//                    }
//                }
//            }
//        }
//        bankInfo = new BankInfo();
//        if (truthChecker == 0) {
//            bankInfo.setMainId(-1);
//            theId = -1;
//        } else if (truthChecker == 2) {
//            bankInfo.setMainId(-2);
//            theId = -2;
//        } else if (truthChecker == 3) {
//            bankInfo.setMainId(-3);
//            theId = -3;
//        } else if (truthChecker == 1) {
//            bankInfo.setMainId(-4);
//            theId = -4;
//        }
        return SUCCESS;
    }

    private int saleBimei;

    private boolean tayeedeReverseSanad = false;
    private List<Ghest> reverseCandidateGhests;

    public String hazfeTaghsit() {
        Pishnehad p = pishnehadService.loadPishnehadById(pishnehad.getId());
        GhestBandi ghestBandi = asnadeSodorService.findGhestBandiById(gb.getId());

        if (ghestBandi != null) {

            boolean hasSanad = false;
            reverseCandidateGhests = new ArrayList<Ghest>();
            List<Ghest> ghests = ghestBandi.getGhestList();
            for (Ghest ghest : ghests) {
                List<KhateSanad> khateSanadList = ghest.getCredebit().getKhateSanadsBedehi();
                if (khateSanadList != null && !khateSanadList.isEmpty()) {
                    if (!tayeedeReverseSanad) {
                        hasSanad = true;
                        reverseCandidateGhests.add(ghest);
                    } else {
//                        asnadeSodorService.reverseKhateSanadList(khateSanadList);
                    }
                }
            }
            if (hasSanad) {
                gb=ghestBandi;
                return "showAlarmForReverseSanad";
            }

            asnadeSodorService.deleteGhestBandiById(ghestBandi);
        }
//        pishnehad = pishnehadService.findById(pishnehad.getId());
//        calculate(pishnehad);
        return SUCCESS;
    }

    List<TaghsitReport> taghsitReport;
    List<TaghsitReport> trList;

    public String anjameTaghsit() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        pishnehad = pishnehadService.findById(pishnehad.getId());
        if (getShowTaghsit().equals("yes")) {
            taghsitReport = asnadeSodorService.namayesheAghsat(pishnehad, saleBimei, pishnehad.getBimename().getTarikhShorou());

            boolean needToRecalculate = false;

            for (TaghsitReport tr : taghsitReport) {
                if (!tr.isPoosheshEzafiDetailsCorrect()) {
//                    needToRecalculate = true;
                    // disable recalculation for now (blue numbers)
                    tr.setPoosheshEzafiDetailsCorrect(true);
//                    break;
                }
            }


            needToRecalculate = false;

            if (needToRecalculate) {
                List<TaghsitReport> recalcTaghsitReportList = asnadeSodorService.mohasebeyeAghsat(pishnehad, saleBimei, pishnehad.getBimename().getTarikhShorou(), true);
                int index = 0;
                for (TaghsitReport tr : recalcTaghsitReportList) {
                    if (!tr.isPoosheshEzafiDetailsCorrect() && taghsitReport.size() > index) {
                        // todo: elhaghie taghirat
                        taghsitReport.get(index).setHaghBimePoosheshAmraz(tr.getHaghBimePoosheshAmraz());
                        taghsitReport.get(index).setHaghBimePoosheshHadese(tr.getHaghBimePoosheshHadese());
                        taghsitReport.get(index).setHaghBimePoosheshNaghsOzv(tr.getHaghBimePoosheshNaghsOzv());
                        taghsitReport.get(index).setHaghBimePoosheshMoafiat(tr.getHaghBimePoosheshMoafiat());
                    }
                    index++;
                }
            }

        } else {
            taghsitReport = asnadeSodorService.mohasebeyeAghsat(pishnehad, saleBimei, pishnehad.getBimename().getTarikhShorou(), true);
        }
        TaghsitReport totalTaghsitReport = new TaghsitReport();
        totalTaghsitReport.setTarikh("jam");
        totalTaghsitReport.setSaal(taghsitReport.get(0).getSaal());
        totalTaghsitReport.setHaghBimePardaakhtiSaal(0);
        totalTaghsitReport.setKaarmozd(0);
        totalTaghsitReport.setHazineBimeGari(0);
        totalTaghsitReport.setHazineEdari(0);
        totalTaghsitReport.setHazineVosul(0);
        totalTaghsitReport.setHaghBimeKhaalesFotYekja(0);
        totalTaghsitReport.setSarmaayeFotBehHarEllat(taghsitReport.get(0).getSarmaayeFotBehHarEllat());
        totalTaghsitReport.setSarmaayeFotDarAsarHaadeseh(taghsitReport.get(0).getSarmaayeFotDarAsarHaadeseh());
        totalTaghsitReport.setSarmaayePusheshEzaafiAmraazKhaas(taghsitReport.get(0).getSarmaayePusheshEzaafiAmraazKhaas());
        totalTaghsitReport.setHaghBimePusheshHaayeEzaafi(0);
        totalTaghsitReport.setHaghBimePusheshHaayeEzaafi(0);
        totalTaghsitReport.setHaghBimePoosheshAmraz(0);
        totalTaghsitReport.setHaghBimePoosheshHadese(0);
        totalTaghsitReport.setHaghBimePoosheshNaghsOzv(0);
        totalTaghsitReport.setHaghBimePoosheshMoafiat(0);
        totalTaghsitReport.setMaliat(0);
        totalTaghsitReport.setMajmooHaghBimePoosheshhayeEzafi(taghsitReport.get(0).getMajmooHaghBimePoosheshhayeEzafi());
        totalTaghsitReport.setZaribNahveyePardakht(taghsitReport.get(0).getZaribNahveyePardakht());
        totalTaghsitReport.setHaghBimeAvvalie(taghsitReport.get(0).getHaghBimeAvvalie());
        for (TaghsitReport tr : taghsitReport) {
            totalTaghsitReport.setHaghBimePardaakhtiSaal(totalTaghsitReport.getHaghBimePardaakhtiSaal() + tr.getHaghBimePardaakhtiSaal());
            totalTaghsitReport.setKaarmozd(totalTaghsitReport.getKaarmozd() + tr.getKaarmozd());
            totalTaghsitReport.setHazineBimeGari(totalTaghsitReport.getHazineBimeGari() + tr.getHazineBimeGari());
            totalTaghsitReport.setHazineEdari(totalTaghsitReport.getHazineEdari() + tr.getHazineEdari());
            totalTaghsitReport.setHazineVosul(totalTaghsitReport.getHazineVosul() + tr.getHazineVosul());
            totalTaghsitReport.setHaghBimeKhaalesFotYekja(totalTaghsitReport.getHaghBimeKhaalesFotYekja() + tr.getHaghBimeKhaalesFotYekja());
            totalTaghsitReport.setHaghBimePusheshHaayeEzaafi(totalTaghsitReport.getHaghBimePusheshHaayeEzaafi() + tr.getHaghBimePusheshHaayeEzaafi());
            totalTaghsitReport.setHaghBimePoosheshAmraz(totalTaghsitReport.getHaghBimePoosheshAmraz() + tr.getHaghBimePoosheshAmraz());
            totalTaghsitReport.setHaghBimePoosheshHadese(totalTaghsitReport.getHaghBimePoosheshHadese() + tr.getHaghBimePoosheshHadese());
            totalTaghsitReport.setHaghBimePoosheshNaghsOzv(totalTaghsitReport.getHaghBimePoosheshNaghsOzv() + tr.getHaghBimePoosheshNaghsOzv());
            totalTaghsitReport.setHaghBimePoosheshMoafiat(totalTaghsitReport.getHaghBimePoosheshMoafiat() + tr.getHaghBimePoosheshMoafiat());
            totalTaghsitReport.setMaliat(totalTaghsitReport.getMaliat() + tr.getMaliat());
        }
        putToSession("taghsitReport", taghsitReport);
        trList = new ArrayList<TaghsitReport>();
        trList.add(totalTaghsitReport);
        putToSession("trList", trList);
        return SUCCESS;
    }

    public String tayideGhestbandi() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        pishnehad = pishnehadService.findById(pishnehad.getId());
        boolean taghsitShode = false;
        int maxSaleBimeiTaghsitShode = 0;
        for (GhestBandi gb : pishnehad.getBimename().getGhestBandiList()) {
            if (gb.getSaleBimeiInt() != null && gb.getSaleBimeiInt() > maxSaleBimeiTaghsitShode) {
                maxSaleBimeiTaghsitShode = gb.getSaleBimeiInt();
            }
        }
        if (saleBimei > maxSaleBimeiTaghsitShode + 1) {
            return SUCCESS;
        }
        for (GhestBandi gb : pishnehad.getBimename().getGhestBandiList()) {
            if (gb.getSaleBimeiInt() == saleBimei)
                taghsitShode = true;
        }
        if (!taghsitShode) {
            User user = getUser();
            if (user != null) {
                asnadeSodorService.saveGhestbandi(pishnehad, saleBimei, user);
            }
            removeFromSession("taghsitReport");
        }
        return anjameMohasebatePayeForVaziateGhestBandi();
    }

    public String calculate(Pishnehad pish) {
        // todo: invalid shode
        Estelam estelam = pish.getEstelam();
        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;
        String tmpDate = DateUtil.getCurrentDate();
        if (estelam.getPishnehad().getBimename() != null)
            tmpDate = estelam.getPishnehad().getBimename().getTarikhSodour();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(tmpDate, pish.getTarh());
        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(tmpDate, pish.getTarh());
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(tmpDate, pish.getTarh());
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(tmpDate, pish.getTarh());
        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(tmpDate, pish.getTarh());
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(tmpDate, pish.getTarh());
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(tmpDate, pish.getTarh());
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(tmpDate, pish.getTarh());
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(tmpDate, pish.getTarh());
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(tmpDate, pish.getTarh());
        double edariGyekja = AsnadeSodorService.getEdariGyekja(tmpDate, pish.getTarh());
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(tmpDate, pish.getTarh());
        double[][] lifeTable = AsnadeSodorService.getLifeTable(tmpDate, pish.getTarh());
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(tmpDate, pish.getTarh());
        String _date = tmpDate;
        if (estelam.getPooshesh_fot_dar_asar_haadese() != null && estelam.getPooshesh_fot_dar_asar_haadese().equals("yes")) {

        } else {
            estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        }

        if (estelam.getPooshesh_naghs_ozv() != null && estelam.getPooshesh_naghs_ozv().equals("yes")) {
            pooshesh_naghs_ozv_b = true;
        } else {
            pooshesh_naghs_ozv_b = false;
            estelam.setSarmaye_pooshesh_naghs_ozv("0");
        }
        if (estelam.getPooshesh_fot_dar_asar_zelzele() != null && estelam.getPooshesh_fot_dar_asar_zelzele().equals("yes")) {
            pooshesh_fot_dar_asar_zelzele_b = true;
        } else {
            pooshesh_fot_dar_asar_zelzele_b = false;
        }
        if (estelam.getPooshesh_amraz_khas() != null && estelam.getPooshesh_amraz_khas().equals("yes")) {
        } else {
            estelam.setSarmaye_pooshesh_amraz_khas("0");
        }
        if (estelam.getPooshesh_moafiat() != null && estelam.getPooshesh_moafiat().equals("yes")) {
            pooshesh_moafiat_b = true;
        } else {
            pooshesh_moafiat_b = false;
        }
        NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME nahve = null;
        if (estelam.getNahve_pardakht_hagh_bime() == null || estelam.getNahve_pardakht_hagh_bime().equals("sal")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("3mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("6mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("yekja")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
        }
        NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahveDaryaft = null;
        if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename() != null) {
            if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("yekja")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.YEKJA;
            } else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_modat")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MODAT_MOAYAN;
            } else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_omr")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MADAMOL_OMR;
            }
        }
        NSPConstants.NAHVE_DARYAAFT_MOSTAMERI nahveDaryaftMostamari = null;
        if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("sal")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.SAALAANEH;
        } else if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("mah")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.MAAHAANEH;
        }
        NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahveKasrHaghBimePoosheshhayeEzafi = null;
        if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() == null || estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("mazad")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE;
        } else if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE;
        }

        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty())
            estelam.setDarsad_ezafe_nerkh_pezeshki("0");
        if (estelam.getSarmaye_paye_fot_dar_asar_hadese() == null) estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        if (estelam.getSarmaye_paye_fot() == null) estelam.setSarmaye_paye_fot("0");
        if (estelam.getSarmaye_pooshesh_amraz_khas() == null) estelam.setSarmaye_pooshesh_amraz_khas("0");
        if (estelam.getSarmaye_pooshesh_naghs_ozv() == null) estelam.setSarmaye_pooshesh_naghs_ozv("0");
        if (estelam.getNerkh_afzayesh_salaneh_hagh_bime() == null) estelam.setNerkh_afzayesh_salaneh_hagh_bime("0");
        if (estelam.getNerkh_afzayesh_salaneh_sarmaye_fot() == null) estelam.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
        if (estelam.getHagh_bime_pardakhti() == null) estelam.setHagh_bime_pardakhti("0");
        if (estelam.getMablagh_seporde_ebtedaye_sal() == null) estelam.setMablagh_seporde_ebtedaye_sal("0");
        if (estelam.getNerkh_afzayesh_salaneh_mostamari() == null) estelam.setNerkh_afzayesh_salaneh_mostamari("0");
        if (estelam.getModdat_zaman_daryaft_mostamari() == null) estelam.setModdat_zaman_daryaft_mostamari("0");
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equalsIgnoreCase("null") || estelam.getTabaghe_khatar().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("undefined") || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("null"))
            estelam.setTabaghe_khatar_naghsozv("1");
//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

        BimeNaamehVaSarmayehGozari bimeNaamehVaSarmayehGozari = new BimeNaamehVaSarmayehGozari();
        correctCalculation = true;

        try {
            fRsList = bimeNaamehVaSarmayehGozari.finalReports(
                    //Sene_Bime_Shode,Darsad_Ezafe_Nerkh_Pezeshki,Modat_Bime_Naameh,
                    Short.parseShort(estelam.getSen_bime_shode()), Double.parseDouble(estelam.getDarsad_ezafe_nerkh_pezeshki()), Short.parseShort(estelam.getModat_bimename()),
                    //Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot_dar_asar_hadese())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_amraz_khas())),
                    //pushesh_Moaafiyat, Pushesh_Naghs_Ozv,  Sarmaye_Pushesh_Naghs_Ozv,
                    pooshesh_moafiat_b, pooshesh_naghs_ozv_b, Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_naghs_ozv()))
                    //Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,   Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,   Nahve_Pardaakht_Hagh_Bime,
                    , Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_hagh_bime()), Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_sarmaye_fot()), nahve,
                    //Hagh_Bime_Pardaakhti_Rial,  Mablagh_Seporde_Ebtedaye_Saal_Rial,   Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getHagh_bime_pardakhti())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getMablagh_seporde_ebtedaye_sal())), nahveDaryaft,
                    //Nerkh_Afzayesh_Saalaaneh_Mostamari,  Nahve_Daryaft_Mostamri,      Modat_Zaman_Daryaft_Mostamari,
                    Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_mostamari()), nahveDaryaftMostamari, Short.parseShort(estelam.getModdat_zaman_daryaft_mostamari()),
                    //Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari,  Edari,
                    Byte.parseByte(estelam.getTabaghe_khatar()), Byte.parseByte(estelam.getTabaghe_khatar_naghsozv()), pooshesh_fot_dar_asar_zelzele_b,
                    0.0//Double.parseDouble(estelam.getDarsad_takhfif_bimegari()),
                    , 0.0// Double.parseDouble(estelam.getDarsad_takhfif_edari()),
                    //Kaarmozd_Az_Mahal_Hagh_Bimeh,  Kaarmozd_Az_Mahal_Kaarmozd,  Hazineh_Vosul
                    , 0.0//Double.parseDouble(estelam.getDarsad_takhfif_karmozd_hagh())
                    , Double.parseDouble(estelam.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelam.getDarsad_takhfif_vosool()), nerkhBahreFanni
                    , hadeAksarSarFot, hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja,
                    vosulGyekja, lifeTable, _date, pish.getTarh(), nahveKasrHaghBimePoosheshhayeEzafi, payeyeMohasebat);
        } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
            correctCalculation = false;
        }
        return SUCCESS;
    }

    public List<Darkhast> getDarkhasts() {
        return darkhasts;
    }

    public void setDarkhasts(List<Darkhast> darkhasts) {
        this.darkhasts = darkhasts;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String testSecurity() throws Exception {
        return "success";
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public File[] getUploadedPishnehads() {
        return uploadedPishnehads;
    }

    public void setUploadedPishnehads(File[] uploadedPishnehads) {
        this.uploadedPishnehads = uploadedPishnehads;
    }

    public File[] getUploadedScannedFishs() {
        return uploadedScannedFishs;
    }

    public void setUploadedScannedFishs(File[] uploadedScannedFishs) {
        this.uploadedScannedFishs = uploadedScannedFishs;
    }

    public File[] getUploadedExtras() {
        return uploadedExtras;
    }

    public void setUploadedExtras(File[] uploadedExtras) {
        this.uploadedExtras = uploadedExtras;
    }

    public String getUploadedPishnehadsNames() {
        return uploadedPishnehadsNames;
    }

    public void setUploadedPishnehadsNames(String uploadedPishnehadsNames) {
        this.uploadedPishnehadsNames = uploadedPishnehadsNames;
    }

    public String getUploadedScannedFishsNames() {
        return uploadedScannedFishsNames;
    }

    public void setUploadedScannedFishsNames(String uploadedScannedFishsNames) {
        this.uploadedScannedFishsNames = uploadedScannedFishsNames;
    }

    public String getUploadedExtrasNames() {
        return uploadedExtrasNames;
    }

    public void setUploadedExtrasNames(String uploadedExtrasNames) {
        this.uploadedExtrasNames = uploadedExtrasNames;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public NaghsPishnehad getNaghsPishnehad() {
        return naghsPishnehad;
    }

    public void setNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        this.naghsPishnehad = naghsPishnehad;
    }

    public String getLogmessage() {
        return logmessage;
    }

    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }

    public PezeshkSabtNazar getPezeshkSabtNazar() {
        return pezeshkSabtNazar;
    }

    public void setPezeshkSabtNazar(PezeshkSabtNazar pezeshkSabtNazar) {
        this.pezeshkSabtNazar = pezeshkSabtNazar;
    }

    public List<PezeshkSabtNazar> getNazaraat() {
        return nazaraat;
    }

    public void setNazaraat(List<PezeshkSabtNazar> nazaraat) {
        this.nazaraat = nazaraat;
    }

    public String getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(String pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public String getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(String transitionId) {
        this.transitionId = transitionId;
    }

    List<Transition> allowedTransitions;

    public PaginatedListImpl<DarkhastsVM> getDarkhastsVMPaginatedList() {
        return darkhastsVMPaginatedList;
    }

    public void setDarkhastsVMPaginatedList(PaginatedListImpl<DarkhastsVM> darkhastsVMPaginatedList) {
        this.darkhastsVMPaginatedList = darkhastsVMPaginatedList;
    }

    public PaginatedListImpl<PishnehadsVM> getAllPishnehadsVMPaginatedList() {
        return allPishnehadsVMPaginatedList;
    }

    public void setAllPishnehadsVMPaginatedList(PaginatedListImpl<PishnehadsVM> allPishnehadsVMPaginatedList) {
        this.allPishnehadsVMPaginatedList = allPishnehadsVMPaginatedList;
    }

    public PaginatedListImpl<PishnehadsVM> getPishnehadsVMPaginatedList() {
        return pishnehadsVMPaginatedList;
    }

    public PaginatedListImpl<KhesaratVM> getKhesaratVMPaginatedList() {
        return khesaratVMPaginatedList;
    }

    public void setKhesaratVMPaginatedList(PaginatedListImpl<KhesaratVM> khesaratVMPaginatedList) {
        this.khesaratVMPaginatedList = khesaratVMPaginatedList;
    }

    public void setPishnehadsVMPaginatedList(PaginatedListImpl<PishnehadsVM> pishnehadsVMPaginatedList) {
        this.pishnehadsVMPaginatedList = pishnehadsVMPaginatedList;
    }

    public List<Pishnehad> getReportResult() {
        return reportResult;
    }

    public void setReportResult(List<Pishnehad> reportResult) {
        this.reportResult = reportResult;
    }

    private List<Pishnehad> reportResult;

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transition> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(List<Transition> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Long getKarshenasId() {
        return karshenasId;
    }

    public void setKarshenasId(Long karshenasId) {
        this.karshenasId = karshenasId;
    }

    public List<FRs> getFRsList() {
        return fRsList;
    }

    public void setFRsList(List<FRs> fRsList) {
        this.fRsList = fRsList;
    }

    public boolean isCorrectCalculation() {
        return correctCalculation;
    }

    public void setCorrectCalculation(boolean correctCalculation) {
        this.correctCalculation = correctCalculation;
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

    public String getShomareSafhe() {
        return shomareSafhe;
    }

    public void setShomareSafhe(String shomareSafhe) {
        this.shomareSafhe = shomareSafhe;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public List<Credebit> getFoundFishes() {
        return foundFishes;
    }

    public void setFoundFishes(List<Credebit> foundFishes) {
        this.foundFishes = foundFishes;
    }

    public String getTheFishSelecterHolder() {
        return theFishSelecterHolder;
    }

    public void setTheFishSelecterHolder(String theFishSelecterHolder) {
        this.theFishSelecterHolder = theFishSelecterHolder;
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

    public String getCenterPaymentId() {
        String result = "";

        Connection con = null;
        CallableStatement proc_stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://172.16.2.2\\BITARTS;databaseName=PiWD_LifeSuggestions", "sa", "sa@sa");
            proc_stmt = con.prepareCall("{ call SP_GetCenterPaymentId(?) }");
            proc_stmt.setString(1, "1000");
            rs = proc_stmt.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (ClassNotFoundException ex) {
            result = "error";
//            ex.printStackTrace();
        } catch (Exception ex) {
            result = "error";
        } finally {
            try {
                rs.close();
                proc_stmt.close();
                con.close();
            } catch (Exception ex) {
                result = "error";
            }
        }

        return result;
    }

    public static int getOrderId() {
        try {
            Connection con = null;
            CallableStatement proc_stmt = null;
            ResultSet rs = null;
            int result = -1;
            try {
                Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://PIWA-SERVER;databaseName=PIWD_Epayment", "epaymentuser", "|-p@y");
                proc_stmt = con.prepareCall("{ call SP_GetNewOrderId() }");
                rs = proc_stmt.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    rs.close();
                    proc_stmt.close();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return result;
        } catch (Exception e) {
            return -1;
        }
    }


    public String getLogicaldocIndicator() {
        return logicaldocIndicator;
    }

    public void setLogicaldocIndicator(String logicaldocIndicator) {
        this.logicaldocIndicator = logicaldocIndicator;
    }

    public int getSaleBimei() {
        return saleBimei;
    }

    public void setSaleBimei(int saleBimei) {
        this.saleBimei = saleBimei;
    }

    public List<TaghsitReport> getTrList() {
        return trList;
    }

    public void setTrList(List<TaghsitReport> trList) {
        this.trList = trList;
    }

    public List<TaghsitReport> getTaghsitReport() {
        return taghsitReport;
    }

    public void setTaghsitReport(List<TaghsitReport> taghsitReport) {
        this.taghsitReport = taghsitReport;
    }

    public PaginatedListImpl<Bimename> getBimenameha() {
        return bimenameha;
    }

    public void setBimenameha(PaginatedListImpl<Bimename> bimenameha) {
        this.bimenameha = bimenameha;
    }

    public PaginatedListImpl<BimenameVM> getBimenameVMPaginatedList() {
        return bimenameVMPaginatedList;
    }

    public void setBimenameVMPaginatedList(PaginatedListImpl<BimenameVM> bimenameVMPaginatedList) {
        this.bimenameVMPaginatedList = bimenameVMPaginatedList;
    }

    public List<DarkhastBazkharid> getDarkhasthayeBazkharid() {
        return darkhasthayeBazkharid;
    }

    public void setDarkhasthayeBazkharid(List<DarkhastBazkharid> darkhasthayeBazkharid) {
        this.darkhasthayeBazkharid = darkhasthayeBazkharid;
    }

    public String getShenaseIjadShode() {
        return shenaseIjadShode;
    }

    public void setShenaseIjadShode(String shenaseIjadShode) {
        this.shenaseIjadShode = shenaseIjadShode;
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

    public List<DarkhastTaghirat> getDarkhasthayeTaghirat() {
        return darkhasthayeTaghirat;
    }

    public void setDarkhasthayeTaghirat(List<DarkhastTaghirat> darkhasthayeTaghirat) {
        this.darkhasthayeTaghirat = darkhasthayeTaghirat;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public boolean isErrorPardakht() {
        return errorPardakht;
    }

    public void setErrorPardakht(boolean errorPardakht) {
        this.errorPardakht = errorPardakht;
    }

    public boolean isErrorPardakhtShenasedar() {
        return errorPardakhtShenasedar;
    }

    public void setErrorPardakhtShenasedar(boolean errorPardakhtShenasedar) {
        this.errorPardakhtShenasedar = errorPardakhtShenasedar;
    }

    public Zamaem getZamaem() {
        return zamaem;
    }

    public void setZamaem(Zamaem zamaem) {
        this.zamaem = zamaem;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public List<Fish> getFishs() {
        return fishs;
    }

    public void setFishs(List<Fish> fishs) {
        this.fishs = fishs;
    }

    public boolean isNumber(String string) {
        char[] c = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isOnlyWords(String string) {
        char[] c = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean time24HoursValidator(String theTime) {
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(theTime);
        return matcher.matches();
    }

    public String getBackfromupload() {
        return backfromupload;
    }

    public void setBackfromupload(String backfromupload) {
        this.backfromupload = backfromupload;
    }

    public SharayeteJadid getSharayeteJadid() {
        return sharayeteJadid;
    }

    public void setSharayeteJadid(SharayeteJadid sharayeteJadid) {
        this.sharayeteJadid = sharayeteJadid;
    }

    public ElameHesab getElameHesab() {
        return elameHesab;
    }

    public void setElameHesab(ElameHesab elameHesab) {
        this.elameHesab = elameHesab;
    }

    public List<User> getKarshenasha() {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public PishnehadSearch getPishnehadSearch() {
        return pishnehadSearch;
    }

    public void setPishnehadSearch(PishnehadSearch pishnehadSearch) {
        this.pishnehadSearch = pishnehadSearch;
    }

//    public List<TestObject> getTestObjects() {
//        return testObjects;
//    }
//
//    public void setTestObjects(List<TestObject> testObjects) {
//        this.testObjects = testObjects;
//    }

    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public HashMap<String, String> getMessagesMap() {
        return messagesMap;
    }

    public void setMessagesMap(HashMap<String, String> messagesMap) {
        this.messagesMap = messagesMap;
    }

    public int getTheId() {
        return theId;
    }

    public void setTheId(int theId) {
        this.theId = theId;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public PaginatedListImpl<Pishnehad> getViewReportResultPaginated() {
        return viewReportResultPaginated;
    }

    public void setViewReportResultPaginated(PaginatedListImpl<Pishnehad> viewReportResultPaginated) {
        this.viewReportResultPaginated = viewReportResultPaginated;
    }

//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }

    public PaginatedListImpl<Pishnehad> getReportResultPaginated() {
        return reportResultPaginated;
    }

    public void setReportResultPaginated(PaginatedListImpl<Pishnehad> reportResultPaginated) {
        this.reportResultPaginated = reportResultPaginated;
    }


    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

//    public int getPageNum() {
//        return pageNum;
//    }
//
//    public void setPageNum(int pageNum) {
//        this.pageNum = pageNum;
//    }

    public PaginatedListImpl<Pishnehad> getSearchAslPaginated() {
        return searchAslPaginated;
    }

    public void setSearchAslPaginated(PaginatedListImpl<Pishnehad> searchAslPaginated) {
        this.searchAslPaginated = searchAslPaginated;
    }

    public PaginatedListImpl<Pishnehad> getSearchViewPaginated() {
        return searchViewPaginated;
    }

    public void setSearchViewPaginated(PaginatedListImpl<Pishnehad> searchViewPaginated) {
        this.searchViewPaginated = searchViewPaginated;
    }

    public List<EmzaKonande> getEmzaKonandeList() {
        return emzaKonandeList;
    }

    public void setEmzaKonandeList(List<EmzaKonande> emzaKonandeList) {
        this.emzaKonandeList = emzaKonandeList;
    }

    public String getTaeedCheckBox() {
        return taeedCheckBox;
    }

    public void setTaeedCheckBox(String taeedCheckBox) {
        this.taeedCheckBox = taeedCheckBox;
    }

    public String getDarsad_ezafe() {
        return darsad_ezafe;
    }

    public void setDarsad_ezafe(String darsad_ezafe) {
        this.darsad_ezafe = darsad_ezafe;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public IEstelamService getEstelamService() {
        return estelamService;
    }

    public void setEstelamService(IEstelamService estelamService) {
        this.estelamService = estelamService;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public IDarkhastService getDarkhastService() {
        return darkhastService;
    }

    public void setDarkhastService(IDarkhastService darkhastService) {
        this.darkhastService = darkhastService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public String getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(String bufferSize) {
        this.bufferSize = bufferSize;
    }

    public List<FRs> getfRsList() {
        return fRsList;
    }

    public void setfRsList(List<FRs> fRsList) {
        this.fRsList = fRsList;
    }

    public List<BazarYab> getBazaryabs() {
        return bazaryabs;
    }

    public void setBazaryabs(List<BazarYab> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    public IKhesaratService getKhesaratService() {
        return khesaratService;
    }

    public void setKhesaratService(IKhesaratService khesaratService) {
        this.khesaratService = khesaratService;
    }

    public List<Khesarat> getKhesaratha() {
        return khesaratha;
    }

    public void setKhesaratha(List<Khesarat> khesaratha) {
        this.khesaratha = khesaratha;
    }

    private String shomareBimename, shomareMelli, password1, password2, tarikhSodur, codeShenasayi, shmreSabt, tarikhSabt, flagPage;

    public String prepareSignup() {
        return SUCCESS;
    }

    public String validateSignUp() {
        boolean error = false;
//        Captcha captcha = (Captcha) getSession().get(Captcha.NAME);
//        String answer = request.getParameter("answer");
//        if (!captcha.isCorrect(answer)) {
//            addActionError("captch درست وارد نشده است.");
//            error = true;
//        }
        String kaptchaExpected = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = request.getParameter("kaptcha"); //horoofe tasvir

        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
            addActionMessage("حروف تصویر اشتباه است.");  //
            addActionError("حروف تصویر اشتباه است.");  //
            return SUCCESS;
            //return INPUT;
        }
        if (!password1.equals(password2)) {
            addActionError("رمز عبور با تکرار آن مطابقت ندارد.");
            error = true;
        }
        if (password1 == null || password1.length() < 8) {
            addActionError("رمز عبور حداقل باید 8 حرفی باشد.");
            error = true;
        }
        Bimename tmp = pishnehadService.findBimenameByShomare(shomareBimename);
        if (shomareBimename == null || shomareBimename.isEmpty() || tmp == null) {
            addActionError("این بیمه نامه یافت نشد.");
            error = true;
        }
        if (tmp != null && !tmp.getReadyToShow().equals("yes")) {
            addActionError("بیمه نامه هنوز آماده نشده است.");
            error = true;
        }
//        if (tmp != null && !tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(shomareMelli)) {
//            addActionError("شماره ملی یا کد شناسایی وارد شده مربوط به این بیمه نامه نمی باشد.");
//            error = true;
//        }
        if (tmp != null && !tmp.getPishnehad().getBimename().getTarikhSodour().equals(tarikhSodur)) {
            addActionError("تاریخ صدور بیمه نامه درست وارد نشده است.");
            error = true;
        }

        if (flagPage.equals("0")) //code melli farde irani
        {
            if (shomareMelli == null || shomareMelli.isEmpty() || shomareMelli.length() != 10 || shomareMelli.matches("[^0-9]+")) //code malii marboot be farde irani
            {
                addActionError("شماره ملی فرد درست وارد نشده است.");
                error = true;
            }
            if (tmp != null && (tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi() == null || tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().isEmpty())) //hoghooghi code melli ya code shenasayi nadarad
            {
                addActionError("این شماره بیمه نامه حقیقی نیست.");
                error = true;
            }
            if (tmp != null &&  tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi() != null && !tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(shomareMelli)) {
                addActionError("شماره ملی مربوط به این بیمه نامه نمی باشد.");
                error = true;
            }
        } else if (flagPage.equals("1")) //code shenasayi farde khareji
        {
            if (codeShenasayi == null || codeShenasayi.isEmpty())  //code marboot be farde khareji
            {
                addActionError(" کد شناسایی فرد درست وارد نشده است.");
                error = true;
            }
            if (tmp != null && tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi() == null || tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().isEmpty()) //hoghooghi code melli ya code shenasayi nadarad
            {
                addActionError("این شماره بیمه نامه حقیقی نیست.");
                error = true;
            }
            if (tmp != null && tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi() != null &&!tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(codeShenasayi)) {
                addActionError("کد شناسایی مربوط به این بیمه نامه نمی باشد.");
                error = true;
            }
        } else if (flagPage.equals("2")) //hoghooghi
        {
            if ((shmreSabt == null) || shmreSabt.isEmpty()) //shomare sabt
            {
                addActionError("شماره ثبت درست وارد نشده است.");
                error = true;
            }
            if (tmp.getPishnehad().getBimeGozar().getShakhs().getShomareSabt() != null && !tmp.getPishnehad().getBimeGozar().getShakhs().getShomareSabt().equals(shmreSabt))
            {
                addActionError("شماره ثبت وارد شده اشتباه است.");
                error = true;
            }
            if ((tarikhSabt == null) || tarikhSabt.isEmpty()) //tarikhe sabt
            {
                addActionError("تاریخ ثبت درست وارد نشده است.");
                error = true;
            }
            if (tmp.getPishnehad().getBimeGozar().getShakhs().getTarikheSabt() != null && !tmp.getPishnehad().getBimeGozar().getShakhs().getTarikheSabt().equals(tarikhSabt))
            {
                addActionError("تاریخ ثبت وارد شده اشتباه است.");
                error = true;
            }
        }
        return error ? INPUT : SUCCESS;
    }

    public String signUp() {
        String shomare = shomareMelli;
        String code = "کد ملی";
        String[] tsArray = tarikhSabt.split("/");

        Bimename tmp = pishnehadService.findBimenameByShomare(shomareBimename);
        if (flagPage.equals("1")) //khareji
        {
            shomare = codeShenasayi;
            code = "کد شناسایی";
        } else if (flagPage.equals("2"))  //hoghooghi
        {
            shomare = shmreSabt + tsArray[0] + tsArray[1] + tsArray[2];
            code = "شماره ثبت و تاریخ ثبت ";
        }
        User user = loginService.findUserByUsername(shomare);
        if (user == null) {
            user = new User();
            user.setAdmin(false);
            user.setPassword(StringUtil.md5Hash(password1));
            if (flagPage.equals("0")) //irani
            {
                user.setUsername(shomareMelli);
            } else if (flagPage.equals("1")) //khareji
            {
                user.setUsername(codeShenasayi);
            } else if (flagPage.equals("2")) //hoghooghi
            {
                user.setUsername(shmreSabt + tsArray[0] + tsArray[1] + tsArray[2]); //shomare sabt va sal mah rooz(marboot be tarikh sabt)
                user.setShomare_sabt(shmreSabt);
                user.setTarikhe_sabt(tarikhSabt);
            }
            user.setFaal(true);
            user.setValidPass(true);
            Set<Role> roles = new HashSet<Role>();
            roles.add(userService.findRoleById(2)); //role user
            roles.add(userService.findRoleById(14)); //role user kartabl
            user.setRoles(roles);
            user.setFirstName(tmp.getPishnehad().getBimeGozar().getShakhs().getName());
            user.setLastName(tmp.getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi());
            userService.addNewUser(user);
            try {
                if (tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi() != null)  //agar haghighi bood
                {
                    String codeMeliYaShenasayi = tmp.getPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi(); //code melli ya code shenasayie shakhs
                    List<Shakhs> shakhsList = shakhsService.searchAllShakhsByCodeMeli(codeMeliYaShenasayi);
                    for (Shakhs s : shakhsList) {
                        Set<BimeGozar> bimeGozarSet = s.getBimeGozar();
                        Iterator<BimeGozar> bimeGozarIterator = bimeGozarSet.iterator();
                        while (bimeGozarIterator.hasNext()) {
                            BimeGozar bg = bimeGozarIterator.next();
                            bg.setUserKartabl(user);
                            pishnehadService.updateBimeGozar(bg);
                        }
                    }
                }

                pishnehadService.updateBimename(tmp);
//                addActionMessage("کاربر " + shomareMelli + "با کلمه عبور " + password1 + " ایجاد شد.");
                //addActionMessage("ثبت نام شما با نام كاربري " + shomare + " و كلمه عبور " + password1 + " با موفقيت انجام شد. لطفا از اين پس جهت ورود به سيستم،به آدرس اينترنتي http://life.parsianinsurance.com   مراجعه فرماييد. جهت استفاده از اين سيستم مي بايست از مرورگر mozila firefox استفاده نماييد. حتما ورژن آن مي بايست  با لاتر از 10 باشد. سپس با ورود به صفحه مورد نظربه جاي نام كاربري، كدملي بيمه گذار و به جاي كلمه عبور همان رمز عبور درج شده در قسمت ثبت نام را وارد نماييد.");
                addActionMessage("ثبت نام شما با نام کاربری" + shomare + " و  کلمه عبور " + password1 + "با موفقیت انجام شد. لطفا از این پس جهت ورود به سیستم، به آدرس اینترنتی http://life.parsianinsurance.com مراجعه فرمایید. جهت استفاده از این سیستم می بایست از مرورگر mozila firefox استفاده نمایید. حتما ورژن آن می بایست بالاتر از 10 باشد." + "سپس با ورود به صفحه موردنظر به جای نام کاربری، " + code + "بیمه گذار و به جای کلمه عبور همان رمز عبور درج شده در قسمت ثبت نام را وارد نمایید.   ");
            } catch (Exception ex) {
                if (user.getBimeGozarList().size() == 0)
                    userService.removeUser(user.getId());
                addActionError("خطا در سیستم");
                return ERROR;
            }
        } else {
            addActionError("کاربر در سیستم وجود دارد.");
        }
        return SUCCESS;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getCodeShenasayi() {
        return codeShenasayi;
    }

    public void setCodeShenasayi(String codeShenasayi) {
        this.codeShenasayi = codeShenasayi;
    }

    public String getShomareMelli() {
        return shomareMelli;
    }

    public void setShomareMelli(String shomareMelli) {
        this.shomareMelli = shomareMelli;
    }

    public String getTarikhSabt() {
        return tarikhSabt;
    }

    public void setTarikhSabt(String tarikhSabt) {
        this.tarikhSabt = tarikhSabt;
    }

    public String getShmreSabt() {
        return shmreSabt;
    }

    public void setShmreSabt(String shmreSabt) {
        this.shmreSabt = shmreSabt;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getTarikhSodur() {
        return tarikhSodur;
    }

    public void setTarikhSodur(String tarikhSodur) {
        this.tarikhSodur = tarikhSodur;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String validateChangePassword() {
        boolean error = false;
        User theUser = getUser();
        if (theUser == null) {
            addActionError("شما در سیستم وارد نشده اید.");
            error = true;
        }
        if (!password1.equals(password2)) {
            addActionError("رمز عبور درست تکرار نشده است.");
            error = true;
        }
        if (password1.length() < 6) {
            addActionError("رمز عبور باید حداقل ۶ حرفی باشد.");
            error = true;
        }
//        if (!theUser.getPassword().equals(password)) {
//            addActionError("رمز عبور درست وارد نشده است.");
//            error = true;
//        }

        return error ? INPUT : SUCCESS;
    }

    public String chageUserPassword() {
        userTemp = loginService.findUserById(userTemp.getId());
        if (userTemp != null) {
            addActionMessage("به دلیل سیاست جدید امنیتی باید رمز عبور عوض شود");
            addActionMessage("رمز عبور باید حداقل 6 حرفی و شامل اعداد و حروف باشد.");
            request.setAttribute("show", "show"); //jahate namayeshe peyfgham
            User currentUser = getUser();
            if (currentUser.getValidPass() != null && currentUser.getValidPass() &&
                    (userHasRole(currentUser, "ROLE_SUPERVISOR") || userHasRole(currentUser, "ROLE_MALI_OPERATOR")))
                password = userTemp.getPassword();
        }

        return SUCCESS;
    }

    public String preMobileNumber() {
        return SUCCESS;
    }

    public String setMobileNumber() {
        User user = getUser();
        if (user != null) {
            user.setMobile(getMobileForget());
            loginService.updateUser(user);
        }
        return SUCCESS;
    }

    public String getEnableValidationUserAjax() {
        User user = getUser();
        if (userIdChangePass.equals(user.getId().toString())) {
            enableValidationUser = new StringBufferInputStream("true");
        } else {
            enableValidationUser = new StringBufferInputStream("false");
        }
        return SUCCESS;
    }

    public String changePassword() {
        userTemp = loginService.findUserById(userTemp.getId());
        boolean ret = false;
        boolean error = false;
        if (userTemp != null) {
            ret = false;
            String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{8,100})";
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password1);
            if (!matcher.matches())
                error = true;
            //return ERROR;
            if (!password1.equals(password2))
                error = true;                             // return ERROR;
        }

        if (!ret)
            if (!password.equals(userTemp.getPassword()))
                // return ERROR;
                error = true;
        userTemp.setPassword(StringUtil.md5Hash(password1));
        if (ret && userTemp != null && (userTemp.getValidPass() == null || userTemp.getValidPass() == false))
            userTemp.setValidPass(false);
        else
            userTemp.setValidPass(true);
        loginService.updateUser(userTemp);
        addActionMessage("رمز عبور با موفقیت تغییر کرد.");
        if (ret) return "modiriat";
//        user = new User();
        return error ? INPUT :SUCCESS;
    }

    public IGharardadService getGharardadService() {
        return gharardadService;
    }

    public void setGharardadService(IGharardadService gharardadService) {
        this.gharardadService = gharardadService;
    }

//    public List<Gharardad> getGharardadha() {
//        return gharardadha;
//    }
//
//    public void setGharardadha(List<Gharardad> gharardadha) {
//        this.gharardadha = gharardadha;
//    }

    public PaginatedListImpl<Gharardad> getGharardadhaPaginated() {
        return gharardadhaPaginated;
    }

    public void setGharardadhaPaginated(PaginatedListImpl<Gharardad> gharardadhaPaginated) {
        this.gharardadhaPaginated = gharardadhaPaginated;
    }

    public String cleanCache() {
//        for (String c : CacheManager.getInstance().getCacheNames())
//            CacheManager.getInstance().getCache(c).removeAll();
        return SUCCESS;
    }

    public List<City> getOstanha() {
        return ostanha;
    }

    public void setOstanha(List<City> ostanha) {
        this.ostanha = ostanha;
    }

    public GharardadSearch getGharardadSearch() {
        return gharardadSearch;
    }

    public void setGharardadSearch(GharardadSearch gharardadSearch) {
        this.gharardadSearch = gharardadSearch;
    }

    public String getShowTaghsit() {
        return showTaghsit;
    }

    public void setShowTaghsit(String showTaghsit) {
        this.showTaghsit = showTaghsit;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    public String getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }

    public String getFromReadOnlyMode() {
        return fromReadOnlyMode;
    }

    public void setFromReadOnlyMode(String fromReadOnlyMode) {
        this.fromReadOnlyMode = fromReadOnlyMode;
    }

    public StateDAO getStateDAO() {
        return stateDAO;
    }

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    public void setAzTarikheDarkhast(String azTarikheDarkhast) {
        this.azTarikheDarkhast = azTarikheDarkhast;
    }

    public String getAzTarikheDarkhast() {
        return azTarikheDarkhast;
    }

    public void setTaTarikheDarkhast(String taTarikheDarkhast) {
        this.taTarikheDarkhast = taTarikheDarkhast;
    }

    public String getTaTarikheDarkhast() {
        return taTarikheDarkhast;
    }

    public String getNameKarshenas() {
        return nameKarshenas;
    }

    public void setNameKarshenas(String nameKarshenas) {
        this.nameKarshenas = nameKarshenas;
    }

    public Integer getSubsetState() {
        return subsetState;
    }

    public void setSubsetState(Integer subsetState) {
        this.subsetState = subsetState;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String getDarkhastType() {
        return darkhastType;
    }

    public void setDarkhastType(String darkhastType) {
        this.darkhastType = darkhastType;
    }

    public String getKarshenas() {
        return karshenas;
    }

    public void setKarshenas(String karshenas) {
        this.karshenas = karshenas;
    }


    public String getCodeMovaghat() {
        return codeMovaghat;
    }

    public void setCodeMovaghat(String codeMovaghat) {
        this.codeMovaghat = codeMovaghat;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public ElhaghiyeSearch getElhaghiyeSearch() {
        return elhaghiyeSearch;
    }

    public void setElhaghiyeSearch(ElhaghiyeSearch elhaghiyeSearch) {
        this.elhaghiyeSearch = elhaghiyeSearch;
    }

    public boolean isTayeedeReverseSanad() {
        return tayeedeReverseSanad;
    }

    public void setTayeedeReverseSanad(boolean tayeedeReverseSanad) {
        this.tayeedeReverseSanad = tayeedeReverseSanad;
    }

    public List<Ghest> getReverseCandidateGhests() {
        return reverseCandidateGhests;
    }

    public void setReverseCandidateGhests(List<Ghest> reverseCandidateGhests) {
        this.reverseCandidateGhests = reverseCandidateGhests;
    }

    public PaginatedListImpl<Darkhast> getAlldarkhastsPgList() {
        return alldarkhastsPgList;
    }

    public void setAlldarkhastsPgList(PaginatedListImpl<Darkhast> alldarkhastsPgList) {
        this.alldarkhastsPgList = alldarkhastsPgList;
    }

    public SearchDarkhasts getSearchDarkhast() {
        return searchDarkhast;
    }

    public void setSearchDarkhast(SearchDarkhasts searchDarkhast) {
        this.searchDarkhast = searchDarkhast;
    }

    public IKarmozdService getKarmozdService() {
        return karmozdService;
    }

    public void setKarmozdService(IKarmozdService karmozdService) {
        this.karmozdService = karmozdService;
    }

    public PaginatedListImpl<Karmozd> getPaginatedListKarmozds() {
        return paginatedListKarmozds;
    }

    public void setPaginatedListKarmozds(PaginatedListImpl<Karmozd> paginatedListKarmozds) {
        this.paginatedListKarmozds = paginatedListKarmozds;
    }

    public PaginatedListImpl<BlackList> getPaginatedtblBlackList() {
        return paginatedtblBlackList;
    }

    public void setPaginatedtblBlackList(PaginatedListImpl<BlackList> paginatedtblBlackList) {
        this.paginatedtblBlackList = paginatedtblBlackList;
    }

    public PishnehadReport getPishnehadReport() {
        return pishnehadReport;
    }

    public void setPishnehadReport(PishnehadReport pishnehadReport) {
        this.pishnehadReport = pishnehadReport;
    }

    public IQueryService getQueryService() {
        return queryService;
    }

    public void setQueryService(IQueryService queryService) {
        this.queryService = queryService;
    }

    public String getUsernameForget() {
        return usernameForget;
    }

    public void setUsernameForget(String usernameForget) {
        this.usernameForget = usernameForget;
    }

    public String getMobileForget() {
        return mobileForget;
    }

    public void setMobileForget(String mobileForget) {
        this.mobileForget = mobileForget;
    }

    public String getCodeRamzForget() {
        return codeRamzForget;
    }

    public void setCodeRamzForget(String codeRamzForget) {
        this.codeRamzForget = codeRamzForget;
    }

    public String getBackfromuploadKesarat() {
        return backfromuploadKesarat;
    }

    public void setBackfromuploadKesarat(String backfromuploadKesarat) {
        this.backfromuploadKesarat = backfromuploadKesarat;
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

    public void setKhesaratAction(boolean khesaratAction) {
        this.khesaratAction = khesaratAction;
    }

    public String getFlagPage() {
        return flagPage;
    }

    public void setFlagPage(String flagPage) {
        this.flagPage = flagPage;
    }

    public String setSessionDaftar(){
        System.out.println("username:"+getJ_username());
        System.out.println("daftar:"+getDaftarType());
//      putToSession("daftar",getDaftarType());



       User user= loginService.findUserByUsername(getJ_username());
        Integer daftar_id=0;
        String output="";
        String errorDaftarNamayande=null;
        if(getDaftarType().equals("PARSIAN")){

            daftar_id=1;  // login ba daftar parsian
            output="true";
        }
        else if(getDaftarType().equals("NAMAYANDE")) {
            Daftar d=asnadeSodorService.findDaftarIdByNamayandeName(user.getNamayandegi().getId());
            if(d !=null) {
                daftar_id=d.getId();
                output="true";
            }
            else{
                output="false";
                System.out.println("has namayande:"+output);
                errorDaftarNamayande="true";
            }
        }
//        Map session=ActionContext.getContext().getSession();
//        session.put("daftar_id",daftar_id);
        putToSession("daftar_id",daftar_id);
        putToSession("errorDaftarNamayande",errorDaftarNamayande);
//        User.daftar_id=daftar_id;

  //        Integer daftar_id_session=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        System.out.println("daftar id session:"+daftar_id_session);

        hasDaftareNamayande = new ByteArrayInputStream(output.getBytes());
//           hasDaftareNamayande=new StringBufferInputStream(output);
        return SUCCESS;
    }
}
