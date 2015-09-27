package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.Sanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.IPishnehadService;
import com.bitarts.parsian.service.ISequenceService;
import com.bitarts.parsian.service.epayment.PgwStatus;
import com.bitarts.parsian.webservice.epayment.EShopServiceLocator;
import com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType;
import com.bitarts.parsian.webservice.sms.SMSSender;
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
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/10/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class KartablAction extends BaseAction implements ServletContextAware {
    private User user;
    private ILoginService loginService;
    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;
    private ISequenceService sequenceService;
    private Ghest ghest;
    private String authorityId, bankResponse;
    private PaginatedListImpl<Bimename> bimenamePaginatedList;
    private Pishnehad pishnehad;
    private boolean pardakhtInternetiGhestAction;
    private Credebit etebar;
    private String epaymentHost;
    private Sanad sanad;
    private Credebit credebit;

    public Credebit getCredebit()
    {
        return credebit;
    }

    public void setCredebit(Credebit credebit)
    {
        this.credebit = credebit;
    }

    public Sanad getSanad()
    {
        return sanad;
    }

    public void setSanad(Sanad sanad)
    {
        this.sanad = sanad;
    }

    public String getEpaymentHost()
    {
        return epaymentHost;
    }

    public void setEpaymentHost(String epaymentHost)
    {
        this.epaymentHost = epaymentHost;
    }

    public Credebit getEtebar()
    {
        return etebar;
    }

    public void setEtebar(Credebit etebar)
    {
        this.etebar = etebar;
    }

    public boolean isPardakhtInternetiGhestAction()
    {
        return pardakhtInternetiGhestAction;
    }

    public void setPardakhtInternetiGhestAction(boolean pardakhtInternetiGhestAction)
    {
        this.pardakhtInternetiGhestAction = pardakhtInternetiGhestAction;
    }

    public Pishnehad getPishnehad()
    {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad)
    {
        this.pishnehad = pishnehad;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        loginService = (ILoginService) wac.getBean("loginService");
        pishnehadService = (IPishnehadService) wac.getBean("pishnehadService");
        asnadeSodorService = (IAsnadeSodorService) wac.getBean("asnadeSodorService");
        sequenceService = (ISequenceService) wac.getBean("sequenceService");
    }

    public String login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            user = loginService.findUserByUsername(username);
            bimenamePaginatedList= new PaginatedListImpl<Bimename>();
            bimenamePaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            bimenamePaginatedList.setPageNumber(0);
//            List<BimeGozar> bimeGozarList = user.getBimeGozarList();
//            List<Bimename> bimenameList = new ArrayList<Bimename>();
//            for(BimeGozar bg : bimeGozarList)
//            {
//                bimenameList.addAll(bg.getBimenameList());
//            }
            bimenamePaginatedList.setList(pishnehadService.findAllBimenamePaginatedForBimeGozar(user));
            bimenamePaginatedList.setFullListSize(bimenamePaginatedList.getList().size());
            return SUCCESS;
        } else return ERROR;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public String pardakhtInternetiGhest() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            return Constant.NOSESSION;
        } else {
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
                String pgwPage = epaymentHost+"/pecpaymentgateway";
                ghest = asnadeSodorService.findBedehiById(ghest.getId());
                EShopServiceLocator l = new EShopServiceLocator();
                EShopServiceSoap_PortType lsoap = null;
                lsoap = l.getEShopServiceSoap();
                InetAddress inetAddress = InetAddress.getLocalHost();
                String callBackUrl = StringUtil.getDeploymentPath() + "/jsp/pishnahad/page_taeedInternetiGhest.jsp?ghest.id=" + ghest.getId();
                LongHolder authority = new LongHolder(0);
                UnsignedByte statusVal = new UnsignedByte(100);
                UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
                int textOrderId = LoginAction.getOrderId();
//            int textOrderId = 18545487;
                if (textOrderId != -1) {
                    try {
                        String mablagh = ghest.getCredebit().getRemainingAmount();
                        mablagh = mablagh.replaceAll(",", "").trim();
                        if(Integer.parseInt(mablagh) > 200000000) mablagh = "200000000";
                        lsoap.pinPaymentRequest(pin, Integer.parseInt(mablagh), textOrderId, callBackUrl, authority, status);
                        authorityId = String.valueOf(authority.value);
                        final Logger logger = LoggerFactory.getLogger(KartablAction.class);
                        String header = String.format("Payment (INTERNET, GHEST) authId: %s | ", authorityId);
                        logger.info(String.format(header + "payment attempt. ghest.id: %s", ghest.getId()));
                    } catch (RemoteException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

    public Ghest getGhest() {
        return ghest;
    }

    public void setGhest(Ghest ghest) {
        this.ghest = ghest;
    }

    public String pardakhtInternetiGhestFinal() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = loginService.findUserByUsername(username);
        if (result.getId() == -1) {
            return Constant.NOSESSION;
        } else {
            try {
                if (asnadeSodorService.isAvailableAuthorityId(authorityId))
                {
                    addActionMessage("برای کد رهگیری "+ authorityId + " قبلاً اعتباری ساخته شده است.");
                    return SUCCESS;
                }
                ghest = asnadeSodorService.findBedehiById(ghest.getId());
                if(ghest.getCredebit().getRemainingAmount_long()==0)
                {
                    addActionMessage("این قسط پیش تر سند خورده است.");
                    return SUCCESS;
                }
                final Logger logger = LoggerFactory.getLogger(KartablAction.class);
                String header = String.format("Payment (INTERNET, GHEST) authId: %s | ", authorityId);
                logger.info(String.format(header + "bankResponse: %s ghest.id: %s"
                        , bankResponse, ghest.getId().toString()));
                String pin = "78d6LN3o6isGu5Wj448C";
                EShopServiceLocator l = new EShopServiceLocator();
                EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap();
                UnsignedByte statusVal = new UnsignedByte(100);
                UnsignedByteHolder status = new UnsignedByteHolder(statusVal);
                if (Integer.parseInt(bankResponse) == 0 && authorityId != null) {
                    lsoap.pinPaymentEnquiry(pin, Long.parseLong(authorityId), status);
                    logger.info(String.format(header + "status: %s"
                            , status.value.toString()));
                }
                if (status.value.longValue() == 0) {
                    ghest = asnadeSodorService.findBedehiById(ghest.getId());
                    List<Credebit> bedehi = new ArrayList<Credebit>(1);
                    List<Credebit> etebarr = new ArrayList<Credebit>(1);
                    bedehi.add(ghest.getCredebit());
                    long mablagh = ghest.getCredebit().getRemainingAmount_long();
                    if(mablagh > 200000000) mablagh = 200000000;
                    pishnehad=ghest.getGhestBandi().getBimename().getPishnehad();
                    Credebit credebit = new Credebit(NumberFormat.getNumberInstance().format(mablagh), sequenceService.nextShenaseCredebit(), ghest.getGhestBandi().getBimename(), null, Credebit.CredebitType.DARYAFTE_ELECTRONICI);
                    credebit.setShomareMoshtari(ghest.getCredebit().getShomareMoshtari());
                    logger.info(header + "credebit object init.");
                    credebit.setAuthorityId(authorityId);
                    credebit.setTimeFish(DateUtil.getCurrentTime());
                    credebit.setDateFish(DateUtil.getCurrentDate());
                    credebit.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
                    asnadeSodorService.saveCredebit(credebit);
                    logger.info(header + "credebit object saved.");
                    etebarr.add(credebit);
                    sanad=asnadeSodorService.sabteSanad(bedehi, etebarr, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, result, true);
                    logger.info(header + "sanad done.");
                    //------- Send SMS -----------
                    sendPardakhteGhestSMS();
                    //----------------------------
//                    addActionMessage(getText("pardakht.success.done"));
//                    Role namayandeRole = new Role();
//                    namayandeRole.setRoleName(Constant.ROLE_NAMAYANDE);
//                    if (result.getRoles().contains(namayandeRole))
//                        return "success_namayande";
//                    else
                    pardakhtInternetiGhestAction=true;
                    etebar=credebit;
                    this.credebit=bedehi.get(0);
                    return SUCCESS;
                } else if (status.value.longValue() == 21) {
                    // ghablan sanad ro zadim
                    return SUCCESS;
                } else {
                    addActionError("خطا در دریافت اطلاعات از بانک");
                    return ERROR;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                addActionError("خطا در سیستم");
                return ERROR;
            }
        }
    }

    private boolean sendPardakhteGhestSMS() {
        try {
            String firstName = ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName();
            String lastName = ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi();
            String cellphoneNo = ghest.getGhestBandi().getBimename().getPishnehad().getAddressBimeGozar().getTelephoneHamrah();
            String shomareGhest = String.valueOf(ghest.getShomareGhest());
            String mablagheGhest = String.valueOf(ghest.getCredebit().getAmount());
            String text = "بيمه گذار محترم،‌ ضمن تشكر از پرداخت به موقع اقساط خود،‌ قسط شماره " +
                    shomareGhest +
                    " به مبلغ " +
                    mablagheGhest +
                    " در سيستم ثبت گرديد. همچنين جهت مشاهده وضعيت اقساط،‌ مي توانيد از سامانه خدمات الكترونيك به آدرس زير استفاده فرماييد.\n" +
                    "http://lifecrm.parsianinsurance.com\n" +
                    "*بيمه پارسيان*";
            String description = "پرداخت اينترنتي اقساط";
            String policyId = ghest.getGhestBandi().getBimename().getPishnehad().getBimename().getShomare();
            return SMSSender.sendSMS(firstName, lastName, cellphoneNo, text, description, policyId, 8);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public PaginatedListImpl<Bimename> getBimenamePaginatedList()
    {
        return bimenamePaginatedList;
    }

    public void setBimenamePaginatedList(PaginatedListImpl<Bimename> bimenamePaginatedList)
    {
        this.bimenamePaginatedList = bimenamePaginatedList;
    }
}
