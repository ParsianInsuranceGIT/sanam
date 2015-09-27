package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.asnadeSodor.Sanad;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.ICheckService;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.ISequenceService;
import com.bitarts.parsian.util.DPUtil;
import com.bitarts.parsian.viewModel.ViewKhateSanad;
import com.bitarts.parsian.viewModel.search.CredebitSearchForm;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 6/1/11
 * Time: 2:15 PM
 */
public class SabteSanadAction extends BaseAction implements ServletContextAware {

    private IAsnadeSodorService asnadeSodorService;
    private ICheckService checkService;
    private String createdDateAz,createdDateTa,amountSanad;
    private HttpServletRequest request;
    private User user;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.checkService = (ICheckService) wac.getBean(ICheckService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
    }

    private int pishnehadId;
    private String searchShode;
    private List<Credebit> etebarCredebitList;
    private List<Credebit> bedehiCredebitList;
    private PaginatedListImpl<Credebit> etebarCredebitListPaginated;
    private PaginatedListImpl<Credebit> bedehiCredebitListPaginated;
    private List<Check> checkList;
    private Sanad.NoeSanad noeSanad;
    private Sanad.Vaziat vaziat;
    private String shomareSanad;
    private CredebitSearchForm credebitSearchForm;
    private Credebit.CredebitType bedehiType,etebarType;
    private String shomareMoshtariEtebar;
    private String shenaseEtebar;
    private String shomareMoshtariBedehi;
    private String shenaseBedehi;
    private String amountEtebar;
    private String amountBedehi;
    private String shoBimenameBedehi;
    private String shoBimenameEtebar;
    private String shomareSanadBank;
    private String shomareCheck;
    private String shomareFish;
    private String SystemName;
    private Integer pageNumber;
    private ILoginService loginService;
    private Integer loadedIdEtebarat;
    private InputStream generateCodeMoshtariAjax;
    private InputStream bankNameCredebit;
    private InputStream userRoleNamayande;
    private InputStream inputStream;
    private Integer credebitId;
    private ISequenceService sequenceService;
    private String bankNameCodeMoshtari;
    private Long namayandeId;
    private Long bazaryabSanamId;
    private String tarikheSarresid ;
    private int subSystemName;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getTarikheSarresid() {
        return tarikheSarresid;
    }

    public void setTarikheSarresid(String tarikheSarresid) {
        this.tarikheSarresid = tarikheSarresid;
    }

    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public String getShomareSanadBank() {
        return shomareSanadBank;
    }

    public void setShomareSanadBank(String shomareSanadBank) {
        this.shomareSanadBank = shomareSanadBank;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getUserRoleNamayande() {
        return userRoleNamayande;
    }

    public void setUserRoleNamayande(InputStream userRoleNamayande) {
        this.userRoleNamayande = userRoleNamayande;
    }

    public Integer getCredebitId() {
        return credebitId;
    }

    public void setCredebitId(Integer credebitId) {
        this.credebitId = credebitId;
    }

    public InputStream getBankNameCredebit() {
        return bankNameCredebit;
    }

    public void setBankNameCredebit(InputStream bankNameCredebit) {
        this.bankNameCredebit = bankNameCredebit;
    }

    public String getBankNameCodeMoshtari() {
        return bankNameCodeMoshtari;
    }

    public Long getBazaryabSanamId() {
        return bazaryabSanamId;
    }

    public String getSearchShode() {
        return searchShode;
    }

    public void setSearchShode(String searchShode) {
        this.searchShode = searchShode;
    }

    public void setBazaryabSanamId(Long bazaryabSanamId) {
        this.bazaryabSanamId = bazaryabSanamId;
    }

    public void setBankNameCodeMoshtari(String bankNameCodeMoshtari) {
        this.bankNameCodeMoshtari = bankNameCodeMoshtari;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public InputStream getGenerateCodeMoshtariAjax() {
        return generateCodeMoshtariAjax;
    }

    public void setGenerateCodeMoshtariAjax(InputStream generateCodeMoshtariAjax) {
        this.generateCodeMoshtariAjax = generateCodeMoshtariAjax;
    }

    public Integer getLoadedIdEtebarat() {
        return loadedIdEtebarat;
    }

    public void setLoadedIdEtebarat(Integer loadedIdEtebarat) {
        this.loadedIdEtebarat = loadedIdEtebarat;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public Integer getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public String takhsisCodeMoshtari(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        etebarCredebitListPaginated = asnadeSodorService.findAllEtebarCredebitsPaginated(user);
        bedehiCredebitListPaginated = asnadeSodorService.findAllBedehiCredebitsPaginated(user);
        checkList = checkService.findAllNormalCheck();
        return SUCCESS;
    }

    public String loadSabteSanad() {
//        Should paginated
//        etebarCredebitList = asnadeSodorService.findAllEtebarCredebits();
//        bedehiCredebitList = asnadeSodorService.findAllBedehiCredebits();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        if(userHasRole(user, "ROLE_NAMAYANDE"))
        {
            etebarCredebitListPaginated = asnadeSodorService.findAllEtebarCredebitsPaginated(user);
            bedehiCredebitListPaginated = asnadeSodorService.findAllBedehiCredebitsPaginated(user);
//            credebit=new Credebit();
//            credebit.setNamayande(user.getNamayandegi());
        }
        else
        {
            etebarCredebitListPaginated = new PaginatedListImpl<Credebit>();
            bedehiCredebitListPaginated = new PaginatedListImpl<Credebit>();
        }
        checkList = checkService.findAllNormalCheck();
        return SUCCESS;
    }
    public String loadEtebarCredebitListPaginated() {
//        Should paginated
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        etebarCredebitListPaginated = asnadeSodorService.findAllEtebarCredebitsPaginated(user);
        return SUCCESS;
    }
    public String loadbedehihaPaginated() {
//        Should paginated
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        etebarCredebitListPaginated = asnadeSodorService.findAllBedehiCredebitsPaginated(user);
        return SUCCESS;
    }

    public String loadCredebitsByFilter() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        etebarCredebitListPaginated = asnadeSodorService.findAllCredebitsByFilterPaginated(credebitSearchForm,user,false);
        return SUCCESS;
    }

    public String loadCredebitsByFilterSendToList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
        etebarCredebitListPaginated = asnadeSodorService.findAllCredebitsByFilterPaginated(credebitSearchForm,user,true);
        return SUCCESS;
    }

    public String generateCodeMoshtariCredebit(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        String shomareMoshtariAjax = "";
        String format = "";

        if (bankNameCodeMoshtari.contains("0201136462000")){
            format = "230"; //tejarat electronic mirdamad sharghi
        }
//        else if (bankNameCodeMoshtari.contains("0200234164006")){
//            format = "110";
//        }
//        else if (bankNameCodeMoshtari.contains("81011989")){
//            format = "110";
//        }
        else if (bankNameCodeMoshtari.contains("17123130")){
            format = "220"; //sepahbod gharani
        }
        else if (bankNameCodeMoshtari.contains("4757575763")){
            format = "242"; //mellat vanak
        }
//        else if (bankNameCodeMoshtari.contains("2177777733")){
//            format = "241";
//        }

        if (resultUser != null && resultUser.getNamayandegi() != null)
            shomareMoshtariAjax = sequenceService.generateNextShomareMoshtari(resultUser.getNamayandegi(), "8", format,null);


        generateCodeMoshtariAjax= new StringBufferInputStream(shomareMoshtariAjax);
        return SUCCESS;
    }

    private boolean userHasRole(User user, String roleName) {
        boolean result = false;
        if (user != null){
            Set<Role> roles = user.getRoles();

            for (Role role : roles) {
                if (role.getRoleName().equalsIgnoreCase(roleName)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public String isUserRoleNamayandeAjax(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
//        userRoleNamayande = (userHasRole(resultUser,"ROLE_NAMAYANDE") == true ? new StringBufferInputStream("true") : new StringBufferInputStream("false"));
        if (userHasRole(resultUser,"ROLE_NAMAYANDE")){
            String roles="";
            for(Role role:resultUser.getRoles()){
                roles+= role.getRoleName();
            }
            userRoleNamayande = new StringBufferInputStream(roles) ;
        } else {
            userRoleNamayande = new StringBufferInputStream("ADMIN");
        }

        return SUCCESS;
    }

    public String getTarikhSarresidCredebitAjax(){
        Credebit credebit = asnadeSodorService.findCretebitById(credebitId);
        if (credebit != null && credebit.getDaryafteCheck() != null && credebit.getDaryafteCheck().getTarikhSarresid() != null)
            inputStream=  new StringBufferInputStream(DateUtil.minusDays(credebit.getDaryafteCheck().getTarikhSarresid(),5));
        return SUCCESS;
    }

    public String getBankNameCredebitAjax(){
        Credebit credebit = asnadeSodorService.findCretebitById(credebitId);
        if (credebit.getBankName() != null && credebit.getBankName().contains("0201136462000")){
            bankNameCredebit = new StringBufferInputStream("0201136462000");
        }
        else if (credebit.getBankName() != null && credebit.getBankName().contains("0200234164006")){
            bankNameCredebit = new StringBufferInputStream("0200234164006");
        }
        else if (credebit.getBankName() != null && credebit.getBankName().contains("81011989")){
            bankNameCredebit = new StringBufferInputStream("81011989");
        }
        else if (credebit.getBankName() != null && credebit.getBankName().contains("17123130")){
            bankNameCredebit = new StringBufferInputStream("17123130");
        }
        else if (credebit.getBankName() != null && credebit.getBankName().contains("4757575763")){
            bankNameCredebit = new StringBufferInputStream("4757575763");
        }
        else if (credebit.getBankName() != null && credebit.getBankName().contains("2177777733")){
            bankNameCredebit = new StringBufferInputStream("2177777733");
        }else{
            bankNameCredebit = new StringBufferInputStream("0");
        }
        return SUCCESS;
    }

    private Sanad sanad;
    public String sabteSanad(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        sanad = asnadeSodorService.sabteSanad(bedehiCredebitList, etebarCredebitList, noeSanad, vaziat, user, false);
        if (sanad != null){
            if (sanad.getId() > 0)
                addActionMessage(getText("multiSabteSanad.success.done"));
            else if (sanad.getId().equals(-1)){
                sanad = null;
                addActionError("امکان ثبت این سند در سیستم وجود ندارد");
            }
            else if (sanad.getId().equals(-2)){
                sanad = null;
                addActionError("تاريخ سررسيد چك و تاريخ سررسيد بدهي مي توانند حداكثر 3 روز اختلاف داشته باشند");
            }
            else if (sanad.getId().equals(-3)){
                sanad = null;
                addActionError("بیمه گذار بدهی با بیمه گذار اعتبار یکسان نمی باشد");
            }
            else if (sanad.getId().equals(-4)){
                sanad = null;
                addActionError("پیشنهاد انتخاب شده، دائم نشده است، لطفا با واحد فن آوری اطلاعات تماس حاصل فرمایید");
            }
            else if (sanad.getId().equals(-5)){
                sanad = null;
                addActionError("در سند زدن حساب فی مابین مشکلی وجود دارد");
            }
            else if (sanad.getId().equals(-6)){
                sanad = null;
                addActionError("تمام بدهی ها برای یک نمایندگی نمی باشد");
            } else if (sanad.getId().equals(-7)){
                sanad = null;
                addActionError("ثبت سند با اعتبار پرداخت شناسه دار و بدهی پرداخت تنخواه امکان پذیر نمی باشد.");
            } else if (sanad.getId().equals(-8)) {
                sanad = null;
                addActionError("اعتبار پرداخت شناسه دار فاقد شناسه پرداخت است.");
            } else if (sanad.getId().equals(-9)) {
                sanad = null;
                addActionError("تاریخ چک با توجه به تاریخ سند معتبر نیست.");
            }
            else if (sanad.getId().equals(-10)) //tamame bedehiha baraye yek bazaryab nemibashad
            {
                sanad = null;
                addActionError("تمام بدهی ها برای یک بازاریاب نمی باشد.");
            }
        }
        else
            addActionError("امکان ثبت این سند در سیستم وجود ندارد");
        return loadSabteSanad();
    }

    private Credebit credebit;
    public String sabteCredebiteDasti(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        if(credebit.getNamayande()!=null && credebit.getNamayande().getId()==null) {
            credebit.setNamayande(null);
        }
        if(!credebit.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR) || resultUser.getNamayandegi().getIssuanceCode() != null){

            credebit = asnadeSodorService.createEtebareDasti(credebit,resultUser);
        }
            if (credebit == null)
            addActionError("امکان ثبت اعتبار/بدهی وجود ندارد");
        etebarCredebitList = new ArrayList<Credebit>();
        etebarCredebitList.add(credebit);
        return SUCCESS;
    }
    public String checkEtebarDasti() //check kardane tarikhe sarreside etebar
    {
       addActionMessage(DPUtil.isCredebitValidForSabtDasti(credebit));
       return SUCCESS;
    }
    public String wirayeshEtebareCheck() //wirayeshe etebar dar safheye liste etebarat va bedehiha
    {
       asnadeSodorService.wirayeshEtebareCheck(credebit);
       return SUCCESS;
    }
    public String loadEtebarat(){
        Integer credebitId = getLoadedIdEtebarat();
        credebit = asnadeSodorService.findCretebitById(credebitId);
        return SUCCESS;
    }

    public String loadBedehiha(){
        Integer credebitId = getLoadedIdEtebarat();
        credebit = asnadeSodorService.findCretebitById(credebitId);
        return SUCCESS;
    }

    PaginatedListImpl<Sanad> sanadListPaginated;
    public String loadSanadHa(){
        sanadListPaginated = asnadeSodorService.findAllSanadsPaginated();
        return SUCCESS;
    }

    PaginatedListImpl<ViewKhateSanad> khateSanadListPaginated;
    PaginatedListImpl<KhateSanad>     YekkhateSanadListPaginated;
    Integer id;

    public String viewYekSanad(){
        if(sanad!=null && sanad.getId()>0)
        {
            //TODO does it work?

            YekkhateSanadListPaginated = new PaginatedListImpl<KhateSanad>();
            YekkhateSanadListPaginated = asnadeSodorService.findAllKhateSanadsBySanadIdPaginated(sanad.getId());

        }
        return SUCCESS;
    }

    public String viewKhateSanadHa(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null)
        {
            user = loginService.findUserByUsername(username);
        }
    if(sanad!=null && sanad.getId()>0)
    {
        //TODO

    }
    else
    {
        pageNumber=0;
        if(isExport())
        {
           // khateSanadListPaginated=new PaginatedListImpl<KhateSanad>();
            khateSanadListPaginated = new PaginatedListImpl<ViewKhateSanad>();
            khateSanadListPaginated.setPageNumber(0);
            khateSanadListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
            khateSanadListPaginated = asnadeSodorService.findAllKhateSanads(user,pageNumber.intValue(),searchShode ,shomareSanad,noeSanad,vaziat,createdDateAz,createdDateTa,amountSanad, bedehiType,etebarType,
                                                                            shomareMoshtariEtebar,shenaseEtebar,shomareMoshtariBedehi,shenaseBedehi,amountEtebar,
                                                                            amountBedehi,shoBimenameBedehi,shoBimenameEtebar,shomareSanadBank,namayandeId,subSystemName,shomareCheck, bazaryabSanamId, shomareFish, SystemName);
        }
        else
        {
//            if (ActionContext.getContext().getParameters().size() > 0)
//            {
//                Set<String> qs = ActionContext.getContext().getParameters().keySet();
//                for(String name: qs)
//                {
//                    if (name.startsWith("d-") && name.endsWith("-p"))
//                    {
//                        Object[] o = (Object[]) ActionContext.getContext().getParameters().get(qs.iterator().next());
//                        pageNumber = Integer.parseInt((String) o[0]) - 1;
//                    }
//                }
//            }

            khateSanadListPaginated=new PaginatedListImpl<ViewKhateSanad>();
           // khateSanadListPaginated.setPageNumber(PagingUtil.getPageNumberFromContext("page") - 1);
            pageNumber = PagingUtil.getPageNumberFromContext("page");
            khateSanadListPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khateSanadListPaginated = asnadeSodorService.findAllKhateSanads(user,pageNumber.intValue(),searchShode, shomareSanad,noeSanad,vaziat,createdDateAz,createdDateTa,amountSanad, bedehiType,etebarType,
                                                                            shomareMoshtariEtebar,shenaseEtebar,shomareMoshtariBedehi,shenaseBedehi,amountEtebar,
                                                                            amountBedehi,shoBimenameBedehi,shoBimenameEtebar,shomareSanadBank,namayandeId,subSystemName,shomareCheck, bazaryabSanamId, shomareFish, SystemName);
        }
    }

        return SUCCESS;
    }

    public String changeVaziateSanad()
    {
        sanad=asnadeSodorService.findSanadById(id);
        sanad.setVaziat(Sanad.Vaziat.DAEM);
        asnadeSodorService.editSanad(sanad);
        for(KhateSanad kh:sanad.getKhateSanadSet()){
            if(kh.getBedehiCredebit().getSarresidDate().compareTo(DateUtil.getCurrentDate())>0) {
                addActionMessage("كاربر گرامي، در صورت صدور قبض رسيد صندوق جهت اقساط سررسيد نشده يا اعتبارات نقد، تسويه مبلغ قبض رسيد صندوق صادره حداكثر ظرف مدت 24 ساعت الزامي مي باشد.");
                System.out.println("we have action message");
            }
        }
        return SUCCESS;
    }

//----------------------------------------------------------------------------------------------------------------------


    public String getShoBimenameBedehi()
    {
        return shoBimenameBedehi;
    }

    public void setShoBimenameBedehi(String shoBimenameBedehi)
    {
        this.shoBimenameBedehi = shoBimenameBedehi;
    }

    public String getShoBimenameEtebar()
    {
        return shoBimenameEtebar;
    }

    public void setShoBimenameEtebar(String shoBimenameEtebar)
    {
        this.shoBimenameEtebar = shoBimenameEtebar;
    }

    public String getAmountEtebar() {
        return amountEtebar;
    }

    public void setAmountEtebar(String amountEtebar) {
        this.amountEtebar = amountEtebar;
    }

    public String getAmountBedehi() {
        return amountBedehi;
    }

    public void setAmountBedehi(String amountBedehi) {
        this.amountBedehi = amountBedehi;
    }

    public String getShomareMoshtariEtebar() {
        return shomareMoshtariEtebar;
    }

    public void setShomareMoshtariEtebar(String shomareMoshtariEtebar) {
        this.shomareMoshtariEtebar = shomareMoshtariEtebar;
    }

    public String getShenaseEtebar() {
        return shenaseEtebar;
    }

    public void setShenaseEtebar(String shenaseEtebar) {
        this.shenaseEtebar = shenaseEtebar;
    }

    public String getShomareMoshtariBedehi() {
        return shomareMoshtariBedehi;
    }

    public void setShomareMoshtariBedehi(String shomareMoshtariBedehi) {
        this.shomareMoshtariBedehi = shomareMoshtariBedehi;
    }

    public String getShenaseBedehi() {
        return shenaseBedehi;
    }

    public void setShenaseBedehi(String shenaseBedehi) {
        this.shenaseBedehi = shenaseBedehi;
    }

    public Credebit.CredebitType getBedehiType() {
        return bedehiType;
    }

    public void setBedehiType(Credebit.CredebitType bedehiType) {
        this.bedehiType = bedehiType;
    }

    public Credebit.CredebitType getEtebarType() {
        return etebarType;
    }

    public void setEtebarType(Credebit.CredebitType etebarType) {
        this.etebarType = etebarType;
    }

    public String getAmountSanad() {
        return amountSanad;
    }

    public void setAmountSanad(String amountSanad) {
        this.amountSanad = amountSanad;
    }

    public String getCreatedDateTa() {
        return createdDateTa;
    }

    public void setCreatedDateTa(String createdDateTa) {
        this.createdDateTa = createdDateTa;
    }

    public String getCreatedDateAz() {
        return createdDateAz;
    }

    public void setCreatedDateAz(String createdDateAz) {
        this.createdDateAz = createdDateAz;
    }

    public String getShomareSanad() {
        return shomareSanad;
    }

    public void setShomareSanad(String shomareSanad) {
        this.shomareSanad = shomareSanad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaginatedListImpl<Sanad> getSanadListPaginated() {
        return sanadListPaginated;
    }

    public void setSanadListPaginated(PaginatedListImpl<Sanad> sanadListPaginated) {
        this.sanadListPaginated = sanadListPaginated;
    }

    public PaginatedListImpl<ViewKhateSanad> getKhateSanadListPaginated() {
        return khateSanadListPaginated;
    }

    public void setKhateSanadListPaginated(PaginatedListImpl<ViewKhateSanad> khateSanadListPaginated) {
        this.khateSanadListPaginated = khateSanadListPaginated;
    }

    public PaginatedListImpl<KhateSanad> getYekKhateSanadListPaginated() {
        return YekkhateSanadListPaginated;
    }

    public void setYekKhateSanadListPaginated(PaginatedListImpl<KhateSanad> YekkhateSanadListPaginated) {
        this.YekkhateSanadListPaginated = YekkhateSanadListPaginated;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public Sanad.Vaziat getVaziat() {
        return vaziat;
    }

    public void setVaziat(Sanad.Vaziat vaziat) {
        this.vaziat = vaziat;
    }

    public Sanad.NoeSanad getNoeSanad() {
        return noeSanad;
    }

    public void setNoeSanad(Sanad.NoeSanad noeSanad) {
        this.noeSanad = noeSanad;
    }

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

    public List<Credebit> getBedehiCredebitList() {
        return bedehiCredebitList;
    }

    public void setBedehiCredebitList(List<Credebit> bedehiCredebitList) {
        this.bedehiCredebitList = bedehiCredebitList;
    }

    public List<Credebit> getEtebarCredebitList() {
        return etebarCredebitList;
    }

    public void setEtebarCredebitList(List<Credebit> etebarCredebitList) {
        this.etebarCredebitList = etebarCredebitList;
    }

    public int getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(int pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public PaginatedListImpl<Credebit> getEtebarCredebitListPaginated() {
        return etebarCredebitListPaginated;
    }

    public void setEtebarCredebitListPaginated(PaginatedListImpl<Credebit> etebarCredebitListPaginated) {
        this.etebarCredebitListPaginated = etebarCredebitListPaginated;
    }

    public PaginatedListImpl<Credebit> getBedehiCredebitListPaginated() {
        return bedehiCredebitListPaginated;
    }

    public void setBedehiCredebitListPaginated(PaginatedListImpl<Credebit> bedehiCredebitListPaginated) {
        this.bedehiCredebitListPaginated = bedehiCredebitListPaginated;
    }

    public CredebitSearchForm getCredebitSearchForm() {
        return credebitSearchForm;
    }

    public void setCredebitSearchForm(CredebitSearchForm credebitSearchForm) {
        this.credebitSearchForm = credebitSearchForm;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public int getSubSystemName() {
        return subSystemName;
    }

    public void setSubSystemName(int subSystemName) {
        this.subSystemName = subSystemName;
    }

    public String getShomareCheck() {
        return shomareCheck;
    }

    public void setShomareCheck(String shomareCheck) {
        this.shomareCheck = shomareCheck;
    }

    public String getShomareFish() {
        return shomareFish;
    }

    public void setShomareFish(String shomareFish) {
        this.shomareFish = shomareFish;
    }

    public String getSystemName() {
        return SystemName;
    }

    public void setSystemName(String systemName) {
        SystemName = systemName;
    }
}
