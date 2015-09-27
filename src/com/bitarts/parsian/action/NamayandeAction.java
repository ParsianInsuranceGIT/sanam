package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.Core.ConstantPaging;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.asnadeSodor.Sanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.karmozd.*;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;
import com.bitarts.parsian.service.karmozd.IKarmozdService;
import com.bitarts.parsian.viewModel.AllPayment;
import com.bitarts.parsian.viewModel.BimenameVM;
import com.bitarts.parsian.viewModel.KarmozdNamayandeVM;
import com.bitarts.parsian.viewModel.NamayandeKarmozd;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/10/12
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class NamayandeAction extends BaseAction implements ServletContextAware {

    Karmozd karmozd;
    Long knId;
    private PaginatedListImpl<Karmozd> paginatedListKarmozds;
    private PaginatedListImpl<KarmozdNamayande> paginatedListKarmozdNamayande;
    private PaginatedListImpl<KarmozdNamayandeVM> pgListKnVM;
    private KarmozdNamayandeVM knSVM;
    private PaginatedListImpl<Ghest> paginatedListGhests;
    private PaginatedListImpl<KarmozdGhest> karmozdGhestPaginatedList;
    private PaginatedListImpl<KarmozdTadilat> karmozdTadilatPaginatedList;
    private PaginatedListImpl<AllPayment> allPaymentPaginatedList;
    private HttpServletRequest request;

    private String azTarikhVosoli;
    private String taTarikhVosoli;
    private Namayande namayande;
    private INamayandeService namayandeService;
    private ILoginService loginService;
    private IConstantItemsService constantItemsService;
    private PaginatedListImpl<Namayande> namayandeList;
    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;
    private IQueryService queryService;
    private ISequenceService sequenceService;
    private IKarmozdService karmozdService;
    private String nname, ncode, ntype;
    private Long ostanId, shahrId;
    private String ostan, shahr;
    private Long viSodurId;
    private IUserService userService;
    private String namayandegiName;
    private String namayandegiCode;
    private String bazaryabSanamName;
    private String bazaryabSanamCode;
    private String selectedTab;
    private String shomareMoshtari,shenaseEtebar;
    private String shomareBimename;
    private String kodeNamayande;
    private PaginatedListImpl<BlackList> paginatedtblBlackList;
    private PaginatedListImpl<Bimename> bimenameha;
    private PaginatedListImpl<BimenameVM> bimenameVMPaginatedList;
    private Credebit credebit;
    private List<KhateSanad> khateSanadList;
    private String msgValidation;
    private boolean isThereNotFinal;
    private String typeBlackList;
    private PaginatedListImpl<Namayande> PaginatedListSeniors;
    private Long seniorId;
    private String contractDateFrom;
    private String contractDateTo;
    private PaginatedListImpl<SeniorSubset> paginatedListSubset;
    private Long karmozdProjectId;
    private List<Gharardad> grouhHa;
    private Long groupId;
    private IGharardadService gharardadService;
    private String batch;
    private InputStream viewTrueFalse;
    private String knDesc;
    private Set<KarmozdGhest> kgYekjaList;
    private User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Set<KarmozdGhest> getKgYekjaList()
    {
        return kgYekjaList;
    }

    public void setKgYekjaList(Set<KarmozdGhest> kgYekjaList)
    {
        this.kgYekjaList = kgYekjaList;
    }

    public String getKnDesc()
    {
        return knDesc;
    }

    public void setKnDesc(String knDesc)
    {
        this.knDesc = knDesc;
    }

    public PaginatedListImpl<KarmozdNamayandeVM> getPgListKnVM()
    {
        return pgListKnVM;
    }

    public void setPgListKnVM(PaginatedListImpl<KarmozdNamayandeVM> pgListKnVM)
    {
        this.pgListKnVM = pgListKnVM;
    }

    public KarmozdNamayandeVM getKnSVM()
    {
        return knSVM;
    }

    public void setKnSVM(KarmozdNamayandeVM knSVM)
    {
        this.knSVM = knSVM;
    }

    public InputStream getViewTrueFalse()
    {
        return viewTrueFalse;
    }

    public void setViewTrueFalse(InputStream viewTrueFalse)
    {
        this.viewTrueFalse = viewTrueFalse;
    }

    public String getBatch()
    {
        return batch;
    }

    public void setBatch(String batch)
    {
        this.batch = batch;
    }

    public IGharardadService getGharardadService()
    {
        return gharardadService;
    }

    public void setGharardadService(IGharardadService gharardadService)
    {
        this.gharardadService = gharardadService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public Long getKarmozdProjectId()
    {
        return karmozdProjectId;
    }

    public void setKarmozdProjectId(Long karmozdProjectId)
    {
        this.karmozdProjectId = karmozdProjectId;
    }

    public PaginatedListImpl<SeniorSubset> getPaginatedListSubset()
    {
        return paginatedListSubset;
    }

    public void setPaginatedListSubset(PaginatedListImpl<SeniorSubset> paginatedListSubset)
    {
        paginatedListSubset = paginatedListSubset;
    }

    public String getContractDateFrom()
    {
        return contractDateFrom;
    }

    public void setContractDateFrom(String contractDateFrom)
    {
        this.contractDateFrom = contractDateFrom;
    }

    public String getContractDateTo()
    {
        return contractDateTo;
    }

    public void setContractDateTo(String contractDateTo)
    {
        this.contractDateTo = contractDateTo;
    }

    public Long getSeniorId()
    {
        return seniorId;
    }

    public void setSeniorId(Long seniorId)
    {
        this.seniorId = seniorId;
    }

    public PaginatedListImpl<Namayande> getPaginatedListSeniors()
    {
        return PaginatedListSeniors;
    }

    public void setPaginatedListSeniors(PaginatedListImpl<Namayande> paginatedListSeniors)
    {
        PaginatedListSeniors = paginatedListSeniors;
    }

    public String getTypeBlackList()
    {
        return typeBlackList;
    }

    public void setTypeBlackList(String typeBlackList)
    {
        this.typeBlackList = typeBlackList;
    }

    public boolean getIsThereNotFinal()
    {
        return isThereNotFinal;
    }

    public void setIsThereNotFinal(boolean thereNotFinal)
    {
        isThereNotFinal = thereNotFinal;
    }

    public IConstantItemsService getConstantItemsService()
    {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService)
    {
        this.constantItemsService = constantItemsService;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public String getMsgValidation()
    {
        return msgValidation;
    }

    public void setMsgValidation(String msgValidation)
    {
        this.msgValidation = msgValidation;
    }

    public String getKodeNamayande()
    {
        return kodeNamayande;
    }

    public void setKodeNamayande(String kodeNamayande)
    {
        this.kodeNamayande = kodeNamayande;
    }

    public List<KhateSanad> getKhateSanadList()
    {
        return khateSanadList;
    }

    public void setKhateSanadList(List<KhateSanad> khateSanadList)
    {
        this.khateSanadList = khateSanadList;
    }

    public String sabtNamyande() {

        if (namayande != null && namayande.getId() != null) {
            namayande = namayandeService.getNamayandeById(namayande.getId());
        }

        return SUCCESS;
    }

    private List<Namayande> reportResult;
    private List<BazarYabSanam> reportBazaryabResult; //combo bazaryab sanam

    public String searchDialog() {
//        setReportResult(namayandeService.doSearch(namayandegiName, namayandegiCode));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        setReportResult(namayandeService.getChildNamayande(namayandegiName, namayandegiCode,user));
        return SUCCESS;
    }

    public String searchAllDialog() {
        setReportResult(namayandeService.doSearch(namayandegiName, namayandegiCode));
        return SUCCESS;
    }

    public String searchBazSanDialog() //search bazaryabsanam
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        setReportBazaryabResult(namayandeService.getBazaryabSanam(bazaryabSanamName, bazaryabSanamCode,user));
        return SUCCESS;
    }
    public String searchBazaryabSanamAllDialog()
    {
        setReportBazaryabResult(namayandeService.doSearchBazSan(bazaryabSanamName, bazaryabSanamCode));
        return SUCCESS;
    }

    public static Random random = new Random();

    public String ajaxlyValidationInsertNamayande()
    {

        boolean isRepetitiousIssuanceCode = sequenceService.isRepetitiousIssuanceCode(namayande.getIssuanceCode());
        if(isRepetitiousIssuanceCode)
            msgValidation="msgRepetitiousIssuanceCode";
        else
            msgValidation="msgIssuanceCodeOk";

        return SUCCESS;
    }

    public String addNamayande() {

        if (namayandeService.getNamayandeByKodeKargozar(namayande.getKodeNamayandeKargozar()) != null) {
            addActionMessage("نماینده مورد نظر وجود دارد.");
            return SUCCESS;
        }

        if (viSodurId != null){
            namayande.setVahedSodur(namayandeService.getNamayandeById(viSodurId));
            namayandeService.editNamayande(namayande.getVahedSodur());
        }
        if(namayande.getSarparast().getId()!=null)
        {
            namayande.setSarparast(namayandeService.getNamayandeById(namayande.getSarparast().getId()));
            namayandeService.editNamayande(namayande.getSarparast());
        }
        if(namayande.getParent().getId()!=null)
        {
            namayande.setParent(namayandeService.getNamayandeById(namayande.getParent().getId()));
            namayandeService.editNamayande(namayande.getParent());
        }
        if(namayande.getParent().getId()==null)
            namayande.setParent(null);
        if(namayande.getOstan() != null){
            namayande.setOstan(constantItemsService.findCityById(namayande.getOstan().getCityId()));
            pishnehadService.saveOrUpdate(namayande.getOstan());

        }
        if(namayande.getShahr() != null){
            namayande.setShahr(constantItemsService.findCityById(namayande.getShahr().getCityId()));
            pishnehadService.saveOrUpdate(namayande.getShahr());
        }
        if(namayande.getNamayandeType().equals("ICD"))
            namayande.setNamayandeType(Namayande.NayamandeType.ICD);
        else if (namayande.getNamayandeType().equals("NAMAYANDE_HAGHIGHI"))
            namayande.setNamayandeType(Namayande.NayamandeType.NAMAYANDE_HAGHIGHI);
        else if(namayande.getNamayandeType().equals("NAMAYANDE_HOGHUGHI"))
            namayande.setNamayandeType(Namayande.NayamandeType.NAMAYANDE_HOGHUGHI);
        else if (namayande.getNamayandeType().equals("BAJE_NAMAYANDE_HOGHUGHI"))
            namayande.setNamayandeType(Namayande.NayamandeType.BAJE_NAMAYANDE_HOGHUGHI);
        else if (namayande.getNamayandeType().equals("FORUSHANDE"))
            namayande.setNamayandeType(Namayande.NayamandeType.FORUSHANDE);
        else if (namayande.getNamayandeType().equals("KARGOZAR_HOGHUGHI"))
            namayande.setNamayandeType(Namayande.NayamandeType.KARGOZAR_HOGHUGHI);
        else if (namayande.getNamayandeType().equals("KARGOZAR_HAGHIGHI"))
            namayande.setNamayandeType(Namayande.NayamandeType.KARGOZAR_HAGHIGHI);
        else if (namayande.getNamayandeType().equals("MOJTAMA"))
            namayande.setNamayandeType(Namayande.NayamandeType.MOJTAMA);
        else if (namayande.getNamayandeType().equals("SHOBE"))
            namayande.setNamayandeType(Namayande.NayamandeType.SHOBE);


        // b-h
        namayande.setTedadDaftar(1);
        /// ta inja
        namayandeService.addNewNamayande(namayande);
        User user = new User();
        User user09 = new User();
        User user07 = new User();

        user.setFirstName(namayande.getName());
        user09.setFirstName(namayande.getName());
        user07.setFirstName(namayande.getName());

        user.setAdmin(false);
        user09.setAdmin(false);
        user07.setAdmin(false);

        user.setLastName("");
        user09.setLastName("");
        user07.setLastName("");

        user.setMojtamaSodoor(namayande.getVahedSodur());
        user09.setMojtamaSodoor(namayande.getVahedSodur());
        user07.setMojtamaSodoor(namayande.getVahedSodur());

        user.setNamayandegi(namayande);
        user09.setNamayandegi(namayande);
        user07.setNamayandegi(namayande);

        user.setFaal(true);
        user09.setFaal(true);
        user07.setFaal(true);

        int num = (random.nextInt(Math.abs(999999 - 111111) + 1)) + 111111;
        user.setPassword(StringUtil.md5Hash(String.valueOf(num)));
        user09.setPassword(StringUtil.md5Hash(String.valueOf(num)));
        user07.setPassword(StringUtil.md5Hash("123456"));

        user.setPersonalCode(namayande.getKodeNamayandeKargozar());
        user09.setPersonalCode(namayande.getKodeNamayandeKargozar());
        user07.setPersonalCode(namayande.getKodeNamayandeKargozar());

        user.setSematSazmani("نماینده");
        user09.setSematSazmani("نماینده");
        user07.setSematSazmani("نماینده");

        user.setUsername(namayande.getKodeNamayandeKargozar().trim() + "04");
        user09.setUsername(namayande.getKodeNamayandeKargozar().trim() + "09");
        user07.setUsername(namayande.getKodeNamayandeKargozar().trim() + "07");

        Set<Role> roles = new LinkedHashSet<Role>();
        roles.add(userService.findRoleById(2));
        roles.add(userService.findRoleById(10));
        user.setRoles(roles);
        Set<Role> roles09 = new LinkedHashSet<Role>();
        roles09.add(userService.findRoleById(2));
        roles09.add(userService.findRoleById(78));
        user09.setRoles(roles09);
        Set<Role> roles07 = new LinkedHashSet<Role>();
        roles07.add(userService.findRoleById(2));
        roles07.add(userService.findRoleById(8));
        roles07.add(userService.findRoleById(10));
        user07.setRoles(roles07);

        //b_h
        Daftar daftar=asnadeSodorService.findDaftarById(1);
        user07.setDaftar(daftar);
        //// ta inja
        userService.addNewUser(user);
        userService.addNewUser(user09);
        userService.addNewUser(user07);

        addActionMessage("نمایندگی مورد نظر با موفقیت ثیت شد.");
        addActionMessage("کاربر مورد نظر با نام کاربری " + user.getUsername()+" و رمز عبور "+ String.valueOf(num)+" با موفقیت ایجاد شد.");
        addActionMessage("کاربر مورد نظر با نام کاربری " + user09.getUsername() + " و رمز عبور " + String.valueOf(num) + " با موفقیت ایجاد شد.");
        addActionMessage("کاربر مورد نظر با نام کاربری " + user07.getUsername() + " و رمز عبور " + String.valueOf("123456") + " با موفقیت ایجاد شد.");

        return SUCCESS;
    }

    public String editNamayande() {
        if (viSodurId != null)
            namayande.setVahedSodur(namayandeService.getNamayandeById(viSodurId));
        if(namayande.getSarparast().getId()!=null)
            namayande.setSarparast(namayandeService.getNamayandeById(namayande.getSarparast().getId()));
        if(namayande.getOstan() != null){
            namayande.setOstan(constantItemsService.findCityById(namayande.getOstan().getCityId()));
        }
        if(namayande.getShahr() != null){
            namayande.setShahr(constantItemsService.findCityById(namayande.getShahr().getCityId()));
        }
        if (namayande.getParent().getId() != null)
            namayande.setParent(namayandeService.getNamayandeById(namayande.getParent().getId()));
        if (namayande.getParent().getId() == null)
            namayande.setParent(null);

        namayandeService.editNamayande(namayande);
        return SUCCESS;
    }

    public String deleteNamayande() {
        namayandeService.removeNamayande(namayande.getId());
        return SUCCESS;
    }

    public String listAllNamayande() {
        namayandeList = new PaginatedListImpl<Namayande>();

        namayandeList.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        namayandeList.setPageNumber(pageNumber);
        namayandeList = namayandeService.findAllNamayande(namayandeList, nname, ncode, ntype, viSodurId, ostanId, shahrId);
        getSession().put("listNamayande", namayandeList);
        return SUCCESS;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
        this.karmozdService = (IKarmozdService) wac.getBean(IKarmozdService.SERVICE_NAME);
        this.queryService = (IQueryService) wac.getBean(IQueryService.SERVICE_NAME);
        this.userService = (IUserService) wac.getBean("userService");
        this.gharardadService = (IGharardadService) wac.getBean("gharardadService");
    }

    public PaginatedListImpl<Namayande> getNamayandeList() {
        return namayandeList;
    }

    public void setNamayandeList(PaginatedListImpl<Namayande> namayandeList) {
        this.namayandeList = namayandeList;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String searchKarmozdNamayande()
    {
      Integer pageNumber = 0;
        karmozdGhestPaginatedList=new PaginatedListImpl<KarmozdGhest>();
        paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
        allPaymentPaginatedList=new PaginatedListImpl<AllPayment>();
        pgListKnVM=new PaginatedListImpl<KarmozdNamayandeVM>();

        if (isExport())
        {
            karmozdGhestPaginatedList.setPageNumber(0);
            karmozdGhestPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);

            paginatedListKarmozdNamayande.setPageNumber(0);
            paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);

            allPaymentPaginatedList.setPageNumber(0);
            allPaymentPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);

            pgListKnVM.setPageNumber(0);
            pgListKnVM.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else
        {
            if (ActionContext.getContext().getParameters().size() > 0)
            {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for(String name: qs)
                {
                    if (name.startsWith("d-") && name.endsWith("-p"))
                    {
                        Object[] o = (Object[]) ActionContext.getContext().getParameters().get(qs.iterator().next());
                        pageNumber = Integer.parseInt((String) o[0]) - 1;
                    }
                }
            }
            paginatedListKarmozdNamayande.setPageNumber(PagingUtil.getPageNumberFromContext("d-49682-p")-1);
            paginatedListKarmozdNamayande.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            karmozdGhestPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page")-1);
            karmozdGhestPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            allPaymentPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49683-p")-1);
            allPaymentPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            pgListKnVM.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p")-1);
            pgListKnVM.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        if(selectedTab.equals("tabs-3"))
        {
            karmozdGhestPaginatedList=karmozdService.findKarmozdGhestByKarmozdId(karmozdGhestPaginatedList,karmozd.getId(),null);
            paginatedListKarmozdNamayande=karmozdService.searchKarmozdNamayanade(paginatedListKarmozdNamayande,karmozd.getId(),null,namayande.getName(),namayande.getId());
            karmozdTadilatPaginatedList=new PaginatedListImpl<KarmozdTadilat>();
            karmozdTadilatPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49681-p") - 1);
            karmozdTadilatPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            karmozdTadilatPaginatedList = karmozdService.findKarmozedTadilat(karmozdTadilatPaginatedList, karmozd.getId());
            allPaymentPaginatedList=karmozdService.findAllPaymentByKarmozdId(allPaymentPaginatedList,karmozd.getId(),null, karmozdService.findById(karmozd.getId()).getType());
        }

        if(selectedTab.equals("tabs-5"))
        {
            karmozd= karmozdService.findById(karmozd.getId());
            knSVM=new KarmozdNamayandeVM();
            knSVM.setKarmozdId(karmozd.getId());
            knSVM.setNamayandeId(namayande.getId());
            pgListKnVM=karmozdService.getReportTashvighiList(pgListKnVM,knSVM);
        }


        return SUCCESS;
    }

    public String searchKarmozdGhest()
    {
        Integer pageNumber = 0;
        karmozdGhestPaginatedList=new PaginatedListImpl<KarmozdGhest>();
        paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
        allPaymentPaginatedList=new PaginatedListImpl<AllPayment>();
        if (isExport())
        {
            karmozdGhestPaginatedList.setPageNumber(0);
            karmozdGhestPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);

            paginatedListKarmozdNamayande.setPageNumber(0);
            paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);

            allPaymentPaginatedList.setPageNumber(0);
            allPaymentPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else
        {
            if (ActionContext.getContext().getParameters().size() > 0)
            {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for(String name: qs)
                {
                    if (name.startsWith("d-") && name.endsWith("-p"))
                    {
                        Object[] o = (Object[]) ActionContext.getContext().getParameters().get(qs.iterator().next());
                        pageNumber = Integer.parseInt((String) o[0]) - 1;
                    }
                }
            }
            paginatedListKarmozdNamayande.setPageNumber(PagingUtil.getPageNumberFromContext("d-49682-p")-1);
            paginatedListKarmozdNamayande.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            karmozdGhestPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page")-1);
            karmozdGhestPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            allPaymentPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49683-p")-1);
            allPaymentPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        karmozdGhestPaginatedList=karmozdService.searchKarmozdGhest(karmozdGhestPaginatedList,karmozd.getId(),shomareMoshtari,shenaseEtebar,kodeNamayande,shomareBimename);
        paginatedListKarmozdNamayande=karmozdService.findKarmozdNamayandeByKarmozdId(paginatedListKarmozdNamayande, karmozd.getId(), null);
        karmozdTadilatPaginatedList=new PaginatedListImpl<KarmozdTadilat>();
        karmozdTadilatPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49681-p")-1);
        karmozdTadilatPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        karmozdTadilatPaginatedList = karmozdService.findKarmozedTadilat(karmozdTadilatPaginatedList,karmozd.getId());
        allPaymentPaginatedList=karmozdService.findAllPaymentByKarmozdId(allPaymentPaginatedList,karmozd.getId(),null, karmozdService.findById(karmozd.getId()).getType());
        return SUCCESS;
    }

    public Karmozd createKarmozdOb(Karmozd.Type type)
    {
        Karmozd karmozdOb = new Karmozd();
        karmozdOb.setUser((User) getSession().get("user"));
        karmozdOb.setCreatedDate(DateUtil.getCurrentDate());
        karmozdOb.setType(karmozd.getType());
        karmozdOb.setAzTarikhVosoli(azTarikhVosoli);
        karmozdOb.setTaTarikhVosoli(taTarikhVosoli);
        karmozdOb.setDescription(karmozd.getDescription());
        karmozdOb.setOnvan(karmozd.getOnvan());
        String az = azTarikhVosoli.replaceAll("/", "");
        String ta = taTarikhVosoli.replaceAll("/", "");
        String str = az.substring(2, az.length()) + "/" + ta.substring(2, ta.length()) + "/";
        String serial = karmozdService.getSerialKarmozd(str);
        if (serial == null || serial.isEmpty())
        {
            serial = str + "01";
        }
        else
        {
            String newSerial = str;
            int sequence = Integer.parseInt(serial.substring(serial.length() - 2, serial.length())) + 1;
            if (Integer.toString(sequence).length() < 2)
                newSerial += "0" + Integer.toString(sequence);
            else
                newSerial += Integer.toString(sequence);
            serial = newSerial;
        }
        karmozdOb.setSerial(serial);
        karmozdOb.setType(type);
        if(type.equals(Karmozd.Type.Karmozd_Tashvighi_Vosuli))
        {
            karmozdOb.setStep("1");
        }
        karmozdService.save(karmozdOb);
        return karmozdOb;
    }

    public void createKarmozdNamayande(Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {
        List<KarmozdGhest> followKgList = new ArrayList<KarmozdGhest>();
        List<KarmozdNamayande> kanaList = new ArrayList<KarmozdNamayande>();

        for (Namayande n : followKarmozdGhestMap.keySet())
        {
            List<KarmozdGhest> karmozdGhestList = new ArrayList<KarmozdGhest>();
            long amount = 0;

            for (KarmozdGhest kg : followKarmozdGhestMap.get(n))
            {
                karmozdGhestList.add(kg);
                followKgList.add(kg);
                amount += kg.getKarmozdAmount();
            }

            KarmozdNamayande karmozdNamayande = new KarmozdNamayande();

            PaginatedListImpl<KarmozdNamayande> paginatedListKarmozdNamayande = new PaginatedListImpl<KarmozdNamayande>();
            paginatedListKarmozdNamayande.setPageNumber(0);
            paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);
            long debits = 0;
            if (queryService.searchKarmozdNamayanade_HasBedehi(null, null, n.getName(), n.getId()))
            {
                paginatedListKarmozdNamayande = karmozdService.searchKarmozdNamayanade(paginatedListKarmozdNamayande, null, null, n.getName(), n.getId());
                for (KarmozdNamayande karnama : paginatedListKarmozdNamayande.getList())
                {
                    if (karnama.getCredebit() != null)
                    {
                        if (karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.BARGASHT_KARMOZD))
                        {
                            if (karnama.getCredebit().getRemainingAmount_long() > 0)
                            {
                                debits += karnama.getCredebit().getRemainingAmount_long();
                            }
                        }
                    }
                }
            }
            karmozdNamayande.setBedehiAmount(debits);
            karmozdNamayande.setNamayande(n);
            karmozdNamayande.setState(KarmozdNamayande.State.ELAM_BE_MALI_NASHODE);
            karmozdNamayande.setKarmozd(karmozdOb);
            karmozdNamayande.setAmount(amount);
            karmozdNamayande.setKarmozdGhests(karmozdGhestList);

            kanaList.add(karmozdNamayande);
        }
        karmozdService.saveKarmozdNamayandeList(kanaList);
        for (KarmozdGhest kg : followKgList)
        {
            for (KarmozdNamayande kn : kanaList)
            {
                if (kn.getNamayande().getId().equals(kg.getNamayandeId()))
                {
                    kg.setKarmozdNamayande(kn);
                    break;
                }
            }
        }
        karmozdService.saveKarmozdGhestList(followKgList);
    }


    public String createProjectKarmozd()
    {
        System.out.println("Start > " + DateUtil.getCurrentTime());
        if(super.getUser()==null)
        {
            return ERROR;
        }


        if(!karmozd.getType().equals(Karmozd.Type.Karmozd_Pardakhti))
        {
            if(karmozd.getType().equals(Karmozd.Type.Karmozd_Tashvighi_Vosuli))
            {
                if(karmozd.getStep()==null)
                {
                    Karmozd karmozdOb = createKarmozdOb(Karmozd.Type.Karmozd_Tashvighi_Vosuli);
                    karmozdService.karmozdTashvighiStepOne(karmozdOb,karmozdOb.getAzTarikhVosoli(),karmozdOb.getTaTarikhVosoli());
                }

                else
                {
                    Karmozd karmozdOb=karmozdService.findById(karmozd.getId());
                    karmozdTashvighiStepTwo(karmozdOb);
                }
            }

            else if(karmozd.getType().equals(Karmozd.Type.Karmozd_Seniors))
            {
                HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap = new HashMap<Namayande, List<KarmozdGhest>>();
//                Karmozd karmozdPardakhti= karmozdService.findById(karmozdProjectId);

                System.out.println("findKarmozdGhestsForSenior Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgForSeniorKarmozd = karmozdService.findKarmozdGhestsForSenior(azTarikhVosoli, taTarikhVosoli);
                System.out.println("findKarmozdGhestsForSenior End at > " + DateUtil.getCurrentTime());

                Karmozd karmozdOb = createKarmozdOb(Karmozd.Type.Karmozd_Seniors);

                System.out.println("processCalcKarmozdSeniors Start at > " + DateUtil.getCurrentTime());
                karmozdService.processCalcKarmozdSeniors(kgForSeniorKarmozd, karmozdOb, followKarmozdGhestMap);
                System.out.println("processCalcKarmozdSeniors End at > " + DateUtil.getCurrentTime());

                karmozdService.saveOrUpdateAll(kgForSeniorKarmozd);
                karmozdService.saveKarmozdGhestMap(followKarmozdGhestMap);

                createKarmozdNamayande(karmozdOb, followKarmozdGhestMap);
            }

            else
            {
                List<Ghest> followGhestList = new ArrayList<Ghest>();
                HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap = new HashMap<Namayande, List<KarmozdGhest>>();

                System.out.println("findGhestsYekjaForVosuliOrPoshesh Start at > " + DateUtil.getCurrentTime());
                List<Ghest> ghestYekjaPoosheshOrVosuliList = karmozdService.findGhestsYekjaForVosuliOrPoshesh(azTarikhVosoli, taTarikhVosoli, karmozd.getType());
                System.out.println("findGhestsYekjaForVosuliOrPoshesh End at > " + DateUtil.getCurrentTime());

                System.out.println("findKhateSanadGYekjaForVosuliOrPoshesh Start at > " + DateUtil.getCurrentTime());
                List<KhateSanad> khateSanadGYekjaPoosheshOrVosuliList = karmozdService.findKhateSanadGYekjaForVosuliOrPoshesh(azTarikhVosoli, taTarikhVosoli, karmozd.getType());
                System.out.println("findKhateSanadGYekjaForVosuliOrPoshesh End at > " + DateUtil.getCurrentTime());


                Karmozd karmozdOb = createKarmozdOb(karmozd.getType());
                List<BlackList> blackLists = null;
                if(karmozd.getType().equals(Karmozd.Type.Karmozd_Vosuli))
                    blackLists = karmozdService.loadAllBlackList(BlackList.Type.KARMOZD_VOSULI);

                System.out.println("karmozdYekjaProcessForVosulOrPooshesh Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdYekjaProcessForVosulOrPooshesh (ghestYekjaPoosheshOrVosuliList,taTarikhVosoli, azTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap, karmozd.getType());
                System.out.println("karmozdYekjaProcessForVosulOrPooshesh End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdProcessForVosulOrPooshesh Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdProcessForVosulOrPooshesh(khateSanadGYekjaPoosheshOrVosuliList,azTarikhVosoli, taTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap, karmozd.getType());
                System.out.println("karmozdProcessForVosulOrPooshesh End at > " + DateUtil.getCurrentTime());

                System.out.println("createKarmozdFromTadilat Start at > " + DateUtil.getCurrentTime());
                karmozdService.createKarmozdFromTadilat(karmozdOb, azTarikhVosoli, taTarikhVosoli, karmozdOb.getType(), followKarmozdGhestMap);
                System.out.println("createKarmozdFromTadilat End at > " + DateUtil.getCurrentTime());

                karmozdService.saveKarmozdGhestMap(followKarmozdGhestMap);
                createKarmozdNamayande(karmozdOb,followKarmozdGhestMap);
            }
        }
        if(karmozd.getType().equals(Karmozd.Type.Karmozd_Pardakhti))
        {
            System.out.println("findGhestsByTarikhVosoli Start at > " + DateUtil.getCurrentTime());
            List<KhateSanad> khateSanadsList = karmozdService.findGhestsByTarikhVosoli(azTarikhVosoli, taTarikhVosoli);
            System.out.println("findGhestsByTarikhVosoli End at > " + DateUtil.getCurrentTime());
            if (khateSanadsList.size() > 0)// || ghestListYekja.size()>0)
            {
                // process bimenameha
                HashSet<Pishnehad> pishnehads = new HashSet<Pishnehad>();
                HashSet<Bimename> bimenamehs = new HashSet<Bimename>();
                for (KhateSanad khateSanad : khateSanadsList) {
                    if (khateSanad.getBedehiCredebit().getBimename().getKarmozdBimename() == null) {
                        if (!pishnehads.contains(khateSanad.getBedehiCredebit().getPishnehad())) {
                            pishnehads.add(khateSanad.getBedehiCredebit().getPishnehad());
                        }
                    }
                }


                for (Pishnehad p : pishnehads) {
                    KarmozdGhest.Tarefe tar = KarmozdCalculate.getTarefeBimename(p.getBimename());
                    long sarmaye = (long) (p.getEstelam().getSarmaye_paye_fot_long() * 0.03);
                    long haghBimeAval;
                    long sepordeEbteda = 0l;
                    long haghBimeOrSarmaye = sarmaye;
                    if (tar.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO) || tar.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO)) {
                        long haghBimeBeduneMaliatVaPoosheshha = queryService.getJameSadereForGhestbandiKasrMaliatEzafi(p.getBimename().getId());
//                        if (p.getEstelam().getMablagh_seporde_ebtedaye_sal() != null)
//                            sepordeEbteda = Long.parseLong(p.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").trim());
//                        haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha + sepordeEbteda) * 0.75);
                        haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha) * 0.75);
                        if (haghBimeAval < sarmaye)
                            haghBimeOrSarmaye = haghBimeAval;
                    }
                    p.getBimename().setKarmozdBimename(haghBimeOrSarmaye);
                    bimenamehs.add(p.getBimename());
                }

                asnadeSodorService.updateBimename(bimenamehs);

                System.out.println("findGhestsYekja Start at > " + DateUtil.getCurrentTime());
                List<Ghest> ghestListYekja = karmozdService.findGhestsYekja(azTarikhVosoli, taTarikhVosoli);
                System.out.println("findGhestsYekja End at > " + DateUtil.getCurrentTime());

                System.out.println("findkarmozdGhestsBazkharidOrEbtal Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgBzkhrdOrEbtlShde = karmozdService.findKarmozdGhestsBazkharidOrEbtal(azTarikhVosoli, taTarikhVosoli);
                System.out.println("findkarmozdGhestsBazkharidOrEbtal End at > " + DateUtil.getCurrentTime());

                System.out.println("findkarmozdGhestsCodeMovaghat Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgCodeMovaghat = karmozdService.findKarmozdGhestsCodeMovaghat(azTarikhVosoli, taTarikhVosoli);
                System.out.println("findkarmozdGhestsCodeMovaghat End at > " + DateUtil.getCurrentTime());

                System.out.println("findKarmozdGhestsChangeNamayande Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgChangeNamayande = karmozdService.findKarmozdGhestsChangeNamayande(azTarikhVosoli, taTarikhVosoli);
                System.out.println("findKarmozdGhestsChangeNamayande End at > " + DateUtil.getCurrentTime());

                System.out.println("findKarmozdGhestDoubleTaghirat Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgDoubleTaghirat = karmozdService.findKarmozdGhestDoubleTaghirat(azTarikhVosoli,taTarikhVosoli);
                System.out.println("findKarmozdGhestDoubleTaghirat End at > " + DateUtil.getCurrentTime());

                System.out.println("findkarmozdGhestForTaghirat Start at > " + DateUtil.getCurrentTime());
                List<KarmozdGhest> kgTaghirat = karmozdService.findKarmozdGhestForTaghirat(azTarikhVosoli,taTarikhVosoli);
                System.out.println("findkarmozdGhestForTaghirat End at > " + DateUtil.getCurrentTime());

                List<BlackList> blackLists = karmozdService.loadAllBlackList(BlackList.Type.KARMOZD_PARDAKHTI);

                Karmozd karmozdOb=createKarmozdOb(karmozd.getType());

                List<Ghest> followGhestList = new ArrayList<Ghest>();
                HashMap<Namayande,List<KarmozdGhest>> followKarmozdGhestMap = new HashMap<Namayande, List<KarmozdGhest>>();


                System.out.println("karmozdGheyreYekjaProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdGheyreYekjaProcess(khateSanadsList, azTarikhVosoli, taTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap);
                System.out.println("karmozdGheyreYekjaProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdYekjaProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdYekjaProcess(ghestListYekja, taTarikhVosoli, azTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap);
                System.out.println("karmozdYekjaProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdEbtalOrBazkharidProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdEbtalOrBazkharidProcess(kgBzkhrdOrEbtlShde, karmozdOb, followKarmozdGhestMap);
                System.out.println("karmozdEbtalOrBazkharidProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdDoubleTaghiratProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdDoubleTaghiratProcess(kgDoubleTaghirat,karmozdOb,followKarmozdGhestMap,azTarikhVosoli,taTarikhVosoli);
                System.out.println("karmozdDoubleTaghiratProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdTaghiratProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdTaghiratProcess(kgTaghirat,karmozdOb,followKarmozdGhestMap,azTarikhVosoli,taTarikhVosoli);
                System.out.println("karmozdTaghiratProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdBeCodeMovaghatProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdBeCodeMovaghatProcess(kgCodeMovaghat, karmozdOb, followKarmozdGhestMap,azTarikhVosoli,taTarikhVosoli);
                System.out.println("karmozdBeCodeMovaghatProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("karmozdTaghirCodeDaemProcess Start at > " + DateUtil.getCurrentTime());
                karmozdService.karmozdTaghirCodeDaemProcess(kgChangeNamayande, karmozdOb, followKarmozdGhestMap,azTarikhVosoli,taTarikhVosoli);
                System.out.println("karmozdTaghirCodeDaemProcess End at > " + DateUtil.getCurrentTime());

                System.out.println("createKarmozdFromTadilat Start at > " + DateUtil.getCurrentTime());
                karmozdService.createKarmozdFromTadilat(karmozdOb, azTarikhVosoli, taTarikhVosoli, karmozdOb.getType(), followKarmozdGhestMap);
                System.out.println("createKarmozdFromTadilat End at > " + DateUtil.getCurrentTime());

                asnadeSodorService.saveGhestList(followGhestList);
                karmozdService.saveKarmozdGhestMap(followKarmozdGhestMap);
                karmozdService.saveOrUpdateAll(kgBzkhrdOrEbtlShde);
                karmozdService.saveOrUpdateAll(kgDoubleTaghirat);
                karmozdService.saveOrUpdateAll(kgTaghirat);
                karmozdService.saveOrUpdateAll(kgCodeMovaghat);
                karmozdService.saveOrUpdateAll(kgChangeNamayande);

                List<KarmozdGhest> followKgList = new ArrayList<KarmozdGhest>();
                List<KarmozdNamayande> kanaList = new ArrayList<KarmozdNamayande>();

                for (Namayande n : followKarmozdGhestMap.keySet())
                {
                    List<KarmozdGhest> karmozdGhestList = new ArrayList<KarmozdGhest>();
                    long amount = 0;

                    for(KarmozdGhest kg : followKarmozdGhestMap.get(n))
                    {
                        karmozdGhestList.add(kg);
                        followKgList.add(kg);
                        amount += kg.getKarmozdAmount();
                    }

                        KarmozdNamayande karmozdNamayande = new KarmozdNamayande();

                        PaginatedListImpl<KarmozdNamayande> paginatedListKarmozdNamayande = new PaginatedListImpl<KarmozdNamayande>();
                        paginatedListKarmozdNamayande.setPageNumber(0);
                        paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);
                        long debits = 0;
                        if(queryService.searchKarmozdNamayanade_HasBedehi(null, null, n.getName(), n.getId())) {
                            paginatedListKarmozdNamayande = karmozdService.searchKarmozdNamayanade(paginatedListKarmozdNamayande, null, null, n.getName(), n.getId());
                            for (KarmozdNamayande karnama : paginatedListKarmozdNamayande.getList())
                            {
                                if (karnama.getCredebit() != null)
                                {
                                    if (karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.BARGASHT_KARMOZD))
                                    {
                                        if (karnama.getCredebit().getRemainingAmount_long() > 0)
                                        {
                                            debits += karnama.getCredebit().getRemainingAmount_long();
                                        }
                                    }
                                }
                            }
                        }
                        karmozdNamayande.setBedehiAmount(debits);
                        karmozdNamayande.setNamayande(n);
                        karmozdNamayande.setState(KarmozdNamayande.State.ELAM_BE_MALI_NASHODE);
                        karmozdNamayande.setKarmozd(karmozdOb);
                        karmozdNamayande.setAmount(amount);
                        karmozdNamayande.setKarmozdGhests(karmozdGhestList);

                        kanaList.add(karmozdNamayande);
                }
                karmozdService.saveKarmozdNamayandeList(kanaList);
                for (KarmozdGhest kg : followKgList) {
                    for (KarmozdNamayande kn : kanaList) {
                        if (kn.getNamayande().getId().equals(kg.getNamayandeId())) {
                            kg.setKarmozdNamayande(kn);
                            break;
                        }
                    }
                }
                karmozdService.saveKarmozdGhestList(followKgList);
            }

            else
            {
                addActionMessage("قسطی با کارمزد پرداخت نشده پیدا نشد.");
            }
        }
        System.out.println("End > " + DateUtil.getCurrentTime());
        return SUCCESS;
    }

    public void karmozdTashvighiStepTwo(Karmozd karmozd)
    {
        karmozdService.createKarmozdGhestTashvighi(karmozd);
        karmozdService.setAmountTashvighiKarmozdNamayande(karmozd);
        karmozd.setStep("2");
        karmozdService.update(karmozd);
    }

//    public long calculateRealKarmozd(Ghest ghest)
//    {
//        long bimenameKarmozd=Math.round(ghest.getGhestBandi().getBimename().getPishnehad().getEstelam().getSarmaye_paye_fot_long()*0.03);
//        long percentKarmozd=0;
//        switch (ghest.getGhestBandi().getSaleBimeiInt())
//        {
//            case 1: case 2: case 3: case 4:
//                percentKarmozd=Math.round(bimenameKarmozd*0.15);
//                break;
//            case 0:
//                percentKarmozd=Math.round(bimenameKarmozd*0.4);
//                break;
//            default:
//                return percentKarmozd;
//        }
//        Integer aghsatCount=0;
//        if (ghest.getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("mah"))
//            aghsatCount=12;
//        else if (ghest.getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("3mah"))
//            aghsatCount=4;
//        else if (ghest.getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("6mah"))
//            aghsatCount=2;
//        else if (ghest.getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("sal"))
//            aghsatCount=1;
//
//        long karmozdForGhest=percentKarmozd/aghsatCount;
//        return karmozdForGhest;
//    }
//
//    public long calculateKarmozd(KarmozdGhest kg)
//    {
//        long bimenameKarmozd=Math.round(Long.parseLong(kg.getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getSarmaye_paye_fot().replaceAll(",", "").trim())*0.03);
//        long percentKarmozd=0;
//        switch (kg.getGhest().getGhestBandi().getSaleBimeiInt())
//        {
//            case 1: case 2: case 3: case 4:
//                percentKarmozd=Math.round(bimenameKarmozd*0.15);
//                break;
//            case 0:
//                percentKarmozd=Math.round(bimenameKarmozd*0.4);
//                break;
//            default:
//                return percentKarmozd;
//        }
//        Integer aghsatCount=0;
//        if (kg.getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("mah"))
//            aghsatCount=12;
//        else if (kg.getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("3mah"))
//            aghsatCount=4;
//        else if (kg.getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("6mah"))
//            aghsatCount=2;
//        else if (kg.getGhest().getGhestBandi().getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("sal"))
//            aghsatCount=1;
//
//        long karmozdForGhest=percentKarmozd/aghsatCount;
//        long paymentKarmozd = (Long.parseLong(kg.getKhateSanad().getAmount().replaceAll(",", "").trim()) * karmozdForGhest) / Long.parseLong(kg.getGhest().getCredebit().getAmount().replaceAll(",", "").trim());
//        return paymentKarmozd;
//    }

    public String karmozdPayment()
    {
        paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
        paginatedListKarmozdNamayande.setPageNumber(0);
        paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);
        List<KarmozdNamayande> karmozdNamayandeList=karmozdService.findKarmozdNamayandeByKarmozdId(paginatedListKarmozdNamayande,karmozd.getId(), KarmozdNamayande.State.ELAM_BE_MALI_NASHODE.toString()).getList();
        int batchCount = 30;
        int loopCount = (int)Math.ceil(karmozdNamayandeList.size() / (double)batchCount);
        for(int i = 0; i < loopCount; i++) {
            if(i<loopCount-1) {
                karmozdService.pardakhtKarmozdForKarmozdNamayande(karmozdNamayandeList.subList(i * batchCount, i * batchCount + batchCount));
            } else {
                karmozdService.pardakhtKarmozdForKarmozdNamayande(karmozdNamayandeList.subList(i * batchCount, karmozdNamayandeList.size()));
            }

        }

        for(KarmozdNamayande kn : karmozdNamayandeList)
        {
//---------------------------------- sanad khordane etebarat o bedehie namayandeha -------------------------------------
            paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
            paginatedListKarmozdNamayande.setPageNumber(0);
            paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);

            if(queryService.searchKarmozdNamayanade_HasBedehi(null, null, kn.getNamayande().getName(), kn.getNamayande().getId())) {
                paginatedListKarmozdNamayande = karmozdService.searchKarmozdNamayanade(paginatedListKarmozdNamayande,null,null,kn.getNamayande().getName(), kn.getNamayande().getId());
                List<Credebit> debits= new ArrayList<Credebit>();
                List<Credebit> credits= new ArrayList<Credebit>();
                for(KarmozdNamayande karnama : paginatedListKarmozdNamayande.getList())
                {
                    if(karnama.getCredebit()!= null)
                    {
                        if(karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.BARGASHT_KARMOZD))
                        {
                            if(karnama.getCredebit().getRemainingAmount_long()>0)
                            {
                                debits.add(karnama.getCredebit());
                            }
                        }
                        else //if (karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_KARMOZD)
                        {
                            if(karnama.getCredebit().getRemainingAmount_long()>0)
                            {
                                credits.add(karnama.getCredebit());
                            }
                        }
                    }
                }
                if(debits.size()>0 && credits.size()>0)
                {
                    String username = SecurityContextHolder.getContext().getAuthentication().getName();
                    User user = loginService.findUserByUsername(username);
                    asnadeSodorService.sabteSanad(debits,credits, Sanad.NoeSanad.PARDAKHT, Sanad.Vaziat.DAEM, user, true);
                }
            }
//----------------------------------------------------------------------------------------------------------------------
            Karmozd k = karmozdService.findById(karmozd.getId());
            k.setStatus("final");
            karmozdService.save(k);
        }
        return SUCCESS;
    }

    public String changeStatusKN()
    {
        if(knDesc==null || knDesc.equals("") || knDesc.equals(" "))
        {
            //add Err;
        }
        else
        {
            KarmozdNamayande kn = karmozdService.findKarmozdNamayandeById(knId);
            kn.setDescription(knDesc);
            if(kn.getStatus().equals(KarmozdNamayande.Status.ELIGIBLE))
                kn.setStatus(KarmozdNamayande.Status.INELIGIBLE);
            else
                kn.setStatus(KarmozdNamayande.Status.ELIGIBLE);
            karmozdService.update(kn);
        }
        viewTrueFalse = new StringBufferInputStream("true");
        return SUCCESS;
    }

    public String makeViewForNamayande()
    {
        karmozd = karmozdService.findById(karmozdProjectId);
        if(karmozd.getViewForNamayande()==null)
        {
            karmozd.setViewForNamayande("yes");
        }
        else
        {
            karmozd.setViewForNamayande(null);
        }
        karmozdService.update(karmozd);
        viewTrueFalse = new StringBufferInputStream("true");
        return SUCCESS;
    }

    public String viewKarmozdProject()
    {
        User user = (User) getSession().get("user");
        Namayande n=null;
        if (user.getNamayandegi()!=null)
            n=user.getNamayandegi();

        Integer pageNumber = 0;
//        paginatedListGhests=new PaginatedListImpl<Ghest>();
        karmozdGhestPaginatedList=new PaginatedListImpl<KarmozdGhest>();
        paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
        allPaymentPaginatedList=new PaginatedListImpl<AllPayment>();
        pgListKnVM=new PaginatedListImpl<KarmozdNamayandeVM>();

        paginatedListKarmozdNamayande.setPageNumber(PagingUtil.getPageNumberFromContext("d-49682-p")-1);
        paginatedListKarmozdNamayande.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

//            paginatedListGhests.setPageNumber(pageNumber);
//            paginatedListGhests.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        karmozdGhestPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("page")-1);
        karmozdGhestPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

        allPaymentPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49683-p")-1);
        allPaymentPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

        pgListKnVM.setPageNumber(PagingUtil.getPageNumberFromContext("d-49685-p") - 1);
        pgListKnVM.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

        if(isExport())
        {
            if(selectedTab.equals("tabs-1"))
            {
                karmozdGhestPaginatedList.setPageNumber(0);
                karmozdGhestPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            }
            else if(selectedTab.equals("tabs-3"))
            {
                paginatedListKarmozdNamayande.setPageNumber(0);
                paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);
            }
            else if(selectedTab.equals("tabs-4"))
            {
                allPaymentPaginatedList.setPageNumber(0);
                allPaymentPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
            }

            else if(selectedTab.equals("tabs-5"))
            {
                pgListKnVM.setPageNumber(0);
                pgListKnVM.setObjectsPerPage(Integer.MAX_VALUE);
            }
        }
//        paginatedListGhests=karmozdService.findGhestsByKarmozdId(paginatedListGhests,karmozd.getId());


        if(n==null)
            paginatedListKarmozdNamayande=karmozdService.findKarmozdNamayandeByKarmozdId(paginatedListKarmozdNamayande, karmozd.getId(), null);

        karmozd = karmozdService.findById(karmozd.getId());
        if (!karmozd.getType().equals(Karmozd.Type.Karmozd_Tashvighi_Vosuli))
        {
            karmozdGhestPaginatedList = karmozdService.findKarmozdGhestByKarmozdId(karmozdGhestPaginatedList, karmozd.getId(), n);
        }
        karmozdTadilatPaginatedList=new PaginatedListImpl<KarmozdTadilat>();
        karmozdTadilatPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext("d-49681-p")-1);
        karmozdTadilatPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        karmozdTadilatPaginatedList = karmozdService.findKarmozedTadilat(karmozdTadilatPaginatedList,karmozd.getId());
//        if(karmozd.getType().equals(Karmozd.Type.Karmozd_Pardakhti))
            allPaymentPaginatedList=karmozdService.findAllPaymentByKarmozdId(allPaymentPaginatedList,karmozd.getId(),n, karmozdService.findById(karmozd.getId()).getType());
        Karmozd k=karmozdService.findById(karmozd.getId());

        if(k.getType().equals(Karmozd.Type.Karmozd_Tashvighi_Vosuli))
        {
            if(knSVM==null)
                knSVM=new KarmozdNamayandeVM();
            selectedTab="tabs-5";
            knSVM.setKarmozdId(karmozd.getId());
            pgListKnVM = karmozdService.getReportTashvighiList(pgListKnVM, knSVM);
        }

        return SUCCESS;
    }

    public String addToBlackList()
    {

        if(typeBlackList.equals("Pardakhti"))
        {
            Bimename bimename = pishnehadService.findBimenameByShomare(shomareBimename);
            BlackList.Type type = BlackList.Type.KARMOZD_PARDAKHTI;
            if (bimename != null)
            {
                if(karmozdService.findBlackListByBimenameId(bimename.getId(),type)==null)
                {
                    karmozdService.addToBlackList(bimename, type);
                    List<KarmozdGhest> kgList = karmozdService.findKarmozdGhestByBimenameId(bimename.getId(), KarmozdNamayande.State.ELAM_BE_MALI_NASHODE,Karmozd.Type.Karmozd_Pardakhti);
                    for (KarmozdGhest kg : kgList)
                    {
                        kg.setBlackList(karmozdService.findBlackListByBimenameId(bimename.getId(), type));
                        karmozdService.saveKarmozdGhest(kg);
                    }
                }
                else
                {
                    addActionMessage("این بیمه نامه قبلاً اضافه شده است.");
                    return SUCCESS;
                }
            }
            else
            {
                addActionMessage("بیمه نامه ای با این مشخصات یافت نشد.");
                return SUCCESS;
            }
        }
        else//(typeBlackList.equals("Vosuli"))
        {
            List<Bimename> bimenameList= new ArrayList<Bimename>();
            if(batch!=null && batch.equals("yes"))
            {
                if(groupId==null)
                {
                    addActionMessage("گروه بیمه نامه را انتخاب کنید.");
                    return SUCCESS;
                }
                else
                {
                    bimenameList=pishnehadService.findAllBimenameByGharardadIdNonePaginated(groupId);
                }
            }
            else
            {
                bimenameList.add(pishnehadService.findBimenameByShomare(shomareBimename));
            }

            if (bimenameList.size()!=0)
            {
                BlackList.Type type = BlackList.Type.KARMOZD_VOSULI;
                String errMsg="";
                for(Bimename b : bimenameList)
                {
                    if (karmozdService.findBlackListByBimenameId(b.getId(), type) == null)
                    {
                        karmozdService.addToBlackList(b, type);
                        List<KarmozdGhest> kgList = karmozdService.findKarmozdGhestByBimenameId(b.getId(), KarmozdNamayande.State.ELAM_BE_MALI_NASHODE, Karmozd.Type.Karmozd_Vosuli);
                        for (KarmozdGhest kg : kgList)
                        {
                            kg.setBlackList(karmozdService.findBlackListByBimenameId(b.getId(), type));
                            karmozdService.saveKarmozdGhest(kg);
                        }
                    }
                    else
                    {
                        errMsg+= b.getShomare() + " , ";
                    }
                }

                if(!errMsg.isEmpty() && errMsg!=null)
                {
                    errMsg= errMsg.substring(0, errMsg.length() - 2);
                    if(errMsg.contains(","))
                    {
                        addActionMessage("بیمه نامه های "+errMsg+" قبلاً اضافه شده اند.");
                    }
                    else
                    {
                        addActionMessage("بیمه نامه" + errMsg + "قبلاً اضافه شده است.");
                    }
                }
            }
            else
            {
                addActionMessage("بیمه نامه ای با این مشخصات یافت نشد.");
                return SUCCESS;
            }
        }
        return SUCCESS;
    }

    public String detailsKarmozd()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        user = loginService.findUserByUsername(username);

        credebit=asnadeSodorService.findCretebitById(credebit.getId());
        khateSanadList=asnadeSodorService.findAllKhateSanadByCredebitId(credebit.getId());

        if(credebit.getBimename().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
            kgYekjaList = credebit.getGhest().getkarmozdSet();
        return SUCCESS;
    }

    public String karmozdPanel()
    {
        isThereNotFinal=false;
        isThereNotFinal=karmozdService.isThereNotFinal();
        Integer pageNumber = 0;
        paginatedListKarmozds=new PaginatedListImpl<Karmozd>();
        paginatedtblBlackList=new PaginatedListImpl<BlackList>();
        PaginatedListSeniors=new PaginatedListImpl<Namayande>();

//        bimenameha=new PaginatedListImpl<Bimename>();
        bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
        if (isExport())
        {
            paginatedListKarmozds.setPageNumber(0);
            paginatedListKarmozds.setObjectsPerPage(Integer.MAX_VALUE);

            paginatedtblBlackList.setPageNumber(0);
            paginatedtblBlackList.setObjectsPerPage(Integer.MAX_VALUE);

//            bimenameha.setPageNumber(0);
//            bimenameha.setObjectsPerPage(Integer.MAX_VALUE);
            bimenameVMPaginatedList.setPageNumber(0);
            bimenameVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);

            PaginatedListSeniors.setPageNumber(0);
            PaginatedListSeniors.setObjectsPerPage(Integer.MAX_VALUE);
        }
        else
        {
            if (ActionContext.getContext().getParameters().size() > 0)
            {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for(String name: qs)
                {
                    if (name.startsWith("d-") && name.endsWith("-p"))
                    {
                        Object[] o = (Object[]) ActionContext.getContext().getParameters().get(qs.iterator().next());
                        pageNumber = Integer.parseInt((String) o[0]) - 1;
                    }
                }
            }
            paginatedListKarmozds.setPageNumber(pageNumber);
            paginatedListKarmozds.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            paginatedtblBlackList.setPageNumber(pageNumber);
            paginatedtblBlackList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            PaginatedListSeniors.setPageNumber(PagingUtil.getPageNumberFromContext("6578706f7274") - 1);
            PaginatedListSeniors.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

//            bimenameha.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
//            bimenameha.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenameVMPaginatedList.setPageNumber(PagingUtil.getPageNumberFromContext(ConstantPaging.bimenamehaPageNumber) - 1);
            bimenameVMPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        }
        user = super.getUser();
        paginatedListKarmozds=karmozdService.findAllKarmozds(paginatedListKarmozds,user);
        paginatedtblBlackList=karmozdService.loadAllBlackListPaging(paginatedtblBlackList);
//        bimenameha=pishnehadService.findAllBimenamePaginated((User)getSession().get("user"),bimenameha);
        bimenameVMPaginatedList=pishnehadService.findAllBimenamePaginated(user,bimenameVMPaginatedList);
        PaginatedListSeniors=namayandeService.findAllSenior(PaginatedListSeniors);
        return SUCCESS;
    }

    public String loadNamaSubset()
    {
        namayande=namayandeService.getNamayandeById(namayande.getId());
        return SUCCESS;
    }

    public String viewSubset()
    {
        Namayande senior = namayandeService.getNamayandeById(seniorId);
        //todo: update list Subsetha ba tavajoh be tarikhe expire Shodaneshon . . .
        SeniorSubset seniorSubset=new SeniorSubset(null,null,senior,null,null);

        paginatedListSubset=new PaginatedListImpl<SeniorSubset>();
        paginatedListSubset.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
        paginatedListSubset.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
        namayandeService.findAllSeniorSubset(seniorSubset, paginatedListSubset);
        return SUCCESS;
    }

    public String addSeniorSubset()
    {
        namayande = namayandeService.getNamayandeById(namayande.getId());
        Namayande senior = namayandeService.getNamayandeById(seniorId);

        if(namayande.getSubsetList()==null || namayande.getSubsetList().size()==0)
        {
            SeniorSubset seniorSubset = new SeniorSubset(contractDateFrom, contractDateTo, senior, namayande, false);
            namayande.setSenior(senior);
            namayandeService.saveOrUpdateSeniorSubset(seniorSubset);
        }
        else
        {
            for(SeniorSubset ss : namayande.getSubsetList())
            {
                if
                (
                    (DateUtil.betweenEq(contractDateFrom, ss.getContractDateFrom(), ss.getContractDateTo()))||
                    (DateUtil.betweenEq(contractDateTo, ss.getContractDateFrom(), ss.getContractDateTo()))
                )
                {
                    addActionMessage("این نماینده تا تاریخ "+ss.getContractDateTo()+" زیر مجموعه نماینده ارشد "+ss.getSenior().getKodeNamayandeKargozar()+" می باشد.");
                    return SUCCESS;
                }

                else
                {
                    SeniorSubset seniorSubset = new SeniorSubset(contractDateFrom, contractDateTo, senior, namayande, null);
                    namayandeService.saveOrUpdateSeniorSubset(seniorSubset);
                }
            }
        }
        namayandeService.editNamayande(namayande);
        namayandeService.editNamayande(senior);
        addActionMessage("نماینده "+ namayande.getKodeNamayandeKargozar()+ "با موفقیت اضافه شد.");
        return SUCCESS;
    }

    public String addSenior()
    {
        namayande=namayandeService.getNamayandeById(seniorId);
        if(namayande.getArshad()!=null)
            addActionError("این نماینده قبلا به عنوان ارشد انتخاب شده است.");
        else
        {
            namayande.setArshad(true);
            namayandeService.editNamayande(namayande);
            addActionMessage("نماینده "+namayande.getKodeNamayandeKargozar()+" با موفقیت اضافه شد. ");
        }
        return SUCCESS;
    }

    public String listAllNamayandeForKarmozd() {
        namayandeList = new PaginatedListImpl<Namayande>();

        namayandeList.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            Object[] o = (Object[]) ActionContext.getContext().getParameters().get(qs.iterator().next());
            pageNumber = Integer.parseInt((String) o[0]) - 1;
        }
        namayandeList.setPageNumber(pageNumber);
        namayandeList = namayandeService.findAllNamayande(namayandeList);
        return SUCCESS;
    }

    private List<Long> selectList;
    private List<Namayande> namayandes;
    private List<NamayandeKarmozd> nks;
    private String azTarikhSodur, taTarikhSodur;
    private List<Ghest> ghestList;
    private Byte stage;

    public String pardakhtKarmozdStage1() {
        stage = 1;
        return pardakhtKarmozd(false);
    }

    public String pardakhtKarmozdStage2() {
        stage = 2;
        return pardakhtKarmozd(true);
    }

    public String joziatPardakhtKarmozd() {
        final List<Bimename> bimenames = pishnehadService.findAllBimenameForNamayande(namayande.getId(), azTarikhSodur, taTarikhSodur);
        ghestList = new LinkedList<Ghest>();
        for (Bimename b : bimenames) {
            ghestList.addAll(asnadeSodorService.findAllGhestForBimenameAndKarmozd(b));
        }
        return SUCCESS;
    }

    public String pardakhtKarmozd(boolean persist) {
        if (selectList != null) {
            nks = new ArrayList<NamayandeKarmozd>(selectList.size());
            if (taTarikhSodur == null || taTarikhSodur.isEmpty()) taTarikhSodur = DateUtil.getCurrentDate();
            namayandes = new ArrayList<Namayande>(selectList.size());
            for (Long id : selectList)

            {
                final Namayande n = namayandeService.getNamayandeById(id);
                namayandes.add(n);
                nks.add(pardakhtKarmozdAmount(n, azTarikhSodur, taTarikhSodur, persist));
            }
        }
        return SUCCESS;
    }

    private NamayandeKarmozd pardakhtKarmozdAmount(Namayande n, String azDate, String taDate, boolean persist) {
        final NamayandeKarmozd nk = new NamayandeKarmozd();
        nk.setNamayande(n);
        final List<Bimename> bimenames = pishnehadService.findAllBimenameForNamayande(n.getId(), azDate, taDate);

        for (Bimename bimename : bimenames) {
            if (bimename.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja")) {
                KarmozdYekja k = karmozdService.findByBimenameYekja(bimename.getId());
                if (k == null) {
                    k = new KarmozdYekja();
                    k.setBimename(bimename);
                    k.setNamayande(n);
                }
                final String now = DateUtil.getCurrentDate();
                final String year1 = DateUtil.addYear(bimename.getTarikhSodour(), 1);
                final String year2 = DateUtil.addYear(bimename.getTarikhSodour(), 2);
                final String year3 = DateUtil.addYear(bimename.getTarikhSodour(), 3);

                if (DateUtil.isGreaterThanOrEqual(now, year1) && k.getAmountSal1().isEmpty()) {
                    long a = Math.round(Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replace(",", "").trim()) * 0.02);
                    final String amount = NumberFormat.getNumberInstance().format(Math.round(a * 0.50));
                    if (persist) {
                        k.setAmountSal1(amount);
                        nk.setCredebit(saveCredebitForKarmozd(amount, bimename));
                        karmozdService.saveYekja(k);
                    } else
                        nk.setAmount(amount);

                }
                if (DateUtil.isGreaterThanOrEqual(now, year2) && k.getAmountSal2().isEmpty()) {
                    long a = Math.round(Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replace(",", "").trim()) * 0.02);
                    final String amount = NumberFormat.getNumberInstance().format(Math.round(a * 0.25));
                    if (persist) {
                        k.setAmountSal2(amount);
                        nk.setCredebit(saveCredebitForKarmozd(amount, bimename));
                        karmozdService.saveYekja(k);
                    } else nk.setAmount(amount);
                }
                if (DateUtil.isGreaterThanOrEqual(now, year3) && k.getAmountSal3().isEmpty()) {
                    long a = Math.round(Long.parseLong(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replace(",", "").trim()) * 0.02);
                    final String amount = NumberFormat.getNumberInstance().format(Math.round(a * 0.25));
                    if (persist) {
                        k.setAmountSal3(amount);
                        nk.setCredebit(saveCredebitForKarmozd(amount, bimename));
                        karmozdService.saveYekja(k);
                    } else nk.setAmount(amount);
                }

            } else {
                final Karmozd k = new Karmozd();
                k.setNamayande(n);
                final List<Ghest> ghests = asnadeSodorService.findAllGhestForBimenameAndKarmozd(bimename);
                long tmp = 0;
                for (Ghest g : ghests) {
                    final KarmozdGhest karmozdGhest=new KarmozdGhest();
                    if (!g.getKarmozdAmount().replace(",", "").trim().isEmpty()) {

                        long paidKarmozd=  (
                                                ((Long.parseLong(g.getCredebit().getAmount().replace(",","").trim())) - (Long.parseLong(g.getCredebit().getRemainingAmount().replace(",","").trim())))
                                                * Long.parseLong(g.getHazineKarmonz().replace(",","").trim())
                                           )
                                           / (Long.parseLong(g.getCredebit().getAmount().replace(",","").trim()));
                        tmp+=paidKarmozd;
//                        tmp += Long.parseLong(g.getKarmozdAmount().replace(",", "").trim());
                        if (persist)
                        {
                            Long remainingKarmozd = Long.parseLong(g.getHazineKarmonz())-
                                                    (
                                                        (
                                                            (
                                                                (Long.parseLong(g.getCredebit().getAmount().replace(",","").trim())) -
                                                                (Long.parseLong(g.getCredebit().getRemainingAmount().replace(",","").trim()))
                                                            )*
                                                            Long.parseLong(g.getHazineKarmonz().replace(",","").trim())
                                                        ) /
                                                        (Long.parseLong(g.getCredebit().getAmount().replace(",","").trim()))
                                                    );
                            Long percentPayment=
                                                (
                                                    (
                                                        (Long.parseLong(g.getCredebit().getAmount().replace(",","").trim())) -
                                                        (Long.parseLong(g.getCredebit().getRemainingAmount().replace(",","").trim()))
                                                    ) * 100
                                                ) / (Long.parseLong(g.getCredebit().getAmount().replace(",","").trim()));
                            g.setKarmozdAmount(remainingKarmozd.toString());
                            g.setKarmozd(k);
                            karmozdGhest.setGhest(g);
//                            karmozdGhest.setKarmozd(k);
                            karmozdGhest.setCredebit(g.getCredebit());
//                            karmozdGhest.setPercentPayment(percentPayment.toString());
                            karmozdService.saveKarmozdGhest(karmozdGhest);
                        }
                    }
                }
                if (tmp > 0) {
                    if (persist) {
                        karmozdService.save(k);
                        nk.setCredebit(saveCredebitForKarmozd(NumberFormat.getNumberInstance().format(tmp), bimename));
                        asnadeSodorService.saveAghsat(ghests);
                    } else nk.setAmount(NumberFormat.getNumberInstance().format(tmp));
                }
            }
        }
        return nk;
    }

    private Credebit saveCredebitForKarmozd(String amount, Bimename bimename) {
        Credebit credebit = new Credebit(amount, sequenceService.nextShenaseCredebit(), bimename, null, Credebit.CredebitType.PARDAKHT_KARMOZD);
        asnadeSodorService.saveCredebit(credebit);
        return credebit;
    }

    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public List<Gharardad> getGrouhHa()
    {
        if (grouhHa == null)
        {
            grouhHa = gharardadService.findAll();
        }
        return grouhHa;
    }

    public void setGrouhHa(List<Gharardad> grouhHa)
    {
        this.grouhHa = grouhHa;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public List<Long> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<Long> selectList) {
        this.selectList = selectList;
    }

    public List<Namayande> getNamayandes() {
        return namayandes;
    }

    public void setNamayandes(List<Namayande> namayandes) {
        this.namayandes = namayandes;
    }

    public String getAzTarikhSodur() {
        return azTarikhSodur;
    }

    public void setAzTarikhSodur(String azTarikhSodur) {
        this.azTarikhSodur = azTarikhSodur;
    }

    public String getTaTarikhSodur() {
        return taTarikhSodur;
    }

    public void setTaTarikhSodur(String taTarikhSodur) {
        this.taTarikhSodur = taTarikhSodur;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
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

    public IKarmozdService getKarmozdService() {
        return karmozdService;
    }

    public void setKarmozdService(IKarmozdService karmozdService) {
        this.karmozdService = karmozdService;
    }

    public List<NamayandeKarmozd> getNks() {
        return nks;
    }

    public void setNks(List<NamayandeKarmozd> nks) {
        this.nks = nks;
    }

    public Byte getStage() {
        return stage;
    }

    public void setStage(Byte stage) {
        this.stage = stage;
    }

    public List<Ghest> getGhestList() {
        return ghestList;
    }

    public void setGhestList(List<Ghest> ghestList) {
        this.ghestList = ghestList;
    }

    public List<Namayande> getReportResult() {
        return reportResult;
    }

    public void setReportResult(List<Namayande> reportResult) {
        this.reportResult = reportResult;
    }

    public String getNamayandegiName() {
        return namayandegiName;
    }

    public void setNamayandegiName(String namayandegiName) {
        this.namayandegiName = namayandegiName;
    }

    public String getNamayandegiCode() {
        return namayandegiCode;
    }

    public void setNamayandegiCode(String namayandegiCode) {
        this.namayandegiCode = namayandegiCode;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public Long getOstanId() {
        return ostanId;
    }

    public void setOstanId(Long ostanId) {
        this.ostanId = ostanId;
    }

    public Long getShahrId() {
        return shahrId;
    }

    public void setShahrId(Long shahrId) {
        this.shahrId = shahrId;
    }

    public Long getViSodurId() {
        return viSodurId;
    }

    public void setViSodurId(Long viSodurId) {
        this.viSodurId = viSodurId;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        NamayandeAction.random = random;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahr() {
        return shahr;
    }

    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    public PaginatedListImpl<Karmozd> getPaginatedListKarmozds()
    {
        return paginatedListKarmozds;
    }

    public void setPaginatedListKarmozds(PaginatedListImpl<Karmozd> paginatedListKarmozds)
    {
        this.paginatedListKarmozds = paginatedListKarmozds;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public PaginatedListImpl<Ghest> getPaginatedListGhests()
    {
        return paginatedListGhests;
    }

    public void setPaginatedListGhests(PaginatedListImpl<Ghest> paginatedListGhests)
    {
        this.paginatedListGhests = paginatedListGhests;
    }

    public Long getKnId()
    {
        return knId;
    }

    public void setKnId(Long knId)
    {
        this.knId = knId;
    }

    public Karmozd getKarmozd()
    {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd)
    {
        this.karmozd = karmozd;
    }

    public String getTaTarikhVosoli()
    {
        return taTarikhVosoli;
    }

    public void setTaTarikhVosoli(String taTarikhVosoli)
    {
        this.taTarikhVosoli = taTarikhVosoli;
    }

    public PaginatedListImpl<KarmozdNamayande> getPaginatedListKarmozdNamayande()
    {
        return paginatedListKarmozdNamayande;
    }

    public void setPaginatedListKarmozdNamayande(PaginatedListImpl<KarmozdNamayande> paginatedListKarmozdNamayande)
    {
        this.paginatedListKarmozdNamayande = paginatedListKarmozdNamayande;
    }

    public String getSelectedTab()
    {
        return selectedTab;
    }

    public PaginatedListImpl<KarmozdGhest> getKarmozdGhestPaginatedList()
    {
        return karmozdGhestPaginatedList;
    }

    public void setKarmozdGhestPaginatedList(PaginatedListImpl<KarmozdGhest> karmozdGhestPaginatedList)
    {
        this.karmozdGhestPaginatedList = karmozdGhestPaginatedList;
    }

    public void setSelectedTab(String selectedTab)
    {
        this.selectedTab = selectedTab;
    }


    public PaginatedListImpl<AllPayment> getAllPaymentPaginatedList()
    {
        return allPaymentPaginatedList;
    }

    public void setAllPaymentPaginatedList(PaginatedListImpl<AllPayment> allPaymentPaginatedList)
    {
        this.allPaymentPaginatedList = allPaymentPaginatedList;
    }

    public PaginatedListImpl<KarmozdTadilat> getKarmozdTadilatPaginatedList() {
        return karmozdTadilatPaginatedList;
    }

    public void setKarmozdTadilatPaginatedList(PaginatedListImpl<KarmozdTadilat> karmozdTadilatPaginatedList) {
        this.karmozdTadilatPaginatedList = karmozdTadilatPaginatedList;
    }

    public String getAzTarikhVosoli()
    {
        return azTarikhVosoli;
    }

    public void setAzTarikhVosoli(String azTarikhVosoli)
    {
        this.azTarikhVosoli = azTarikhVosoli;
    }

    public String getShomareMoshtari()
    {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari)
    {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getShenaseEtebar()
    {
        return shenaseEtebar;
    }

    public void setShenaseEtebar(String shenaseEtebar)
    {
        this.shenaseEtebar = shenaseEtebar;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public PaginatedListImpl<BlackList> getPaginatedtblBlackList()
    {
        return paginatedtblBlackList;
    }

    public void setPaginatedtblBlackList(PaginatedListImpl<BlackList> paginatedtblBlackList)
    {
        this.paginatedtblBlackList = paginatedtblBlackList;
    }

    public PaginatedListImpl<Bimename> getBimenameha()
    {
        return bimenameha;
    }

    public void setBimenameha(PaginatedListImpl<Bimename> bimenameha)
    {
        this.bimenameha = bimenameha;
    }

    public PaginatedListImpl<BimenameVM> getBimenameVMPaginatedList()
    {
        return bimenameVMPaginatedList;
    }

    public void setBimenameVMPaginatedList(PaginatedListImpl<BimenameVM> bimenameVMPaginatedList)
    {
        this.bimenameVMPaginatedList = bimenameVMPaginatedList;
    }

    public Credebit getCredebit()
    {
        return credebit;
    }

    public void setCredebit(Credebit credebit)
    {
        this.credebit = credebit;
    }

    public IQueryService getQueryService() {
        return queryService;
    }

    public void setQueryService(IQueryService queryService) {
        this.queryService = queryService;
    }

    public List<BazarYabSanam> getReportBazaryabResult() {
        return reportBazaryabResult;
    }

    public void setReportBazaryabResult(List<BazarYabSanam> reportBazaryabResult) {
        this.reportBazaryabResult = reportBazaryabResult;
    }

    public String getBazaryabSanamName() {
        return bazaryabSanamName;
    }

    public void setBazaryabSanamName(String bazaryabSanamName) {
        this.bazaryabSanamName = bazaryabSanamName;
    }

    public String getBazaryabSanamCode() {
        return bazaryabSanamCode;
    }

    public void setBazaryabSanamCode(String bazaryabSanamCode) {
        this.bazaryabSanamCode = bazaryabSanamCode;
    }
}
