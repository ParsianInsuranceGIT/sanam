package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.model.pishnahad.Zamaem;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.MohasebateTeory;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.BimenameVM;
import com.bitarts.parsian.viewModel.CitySearch;
import com.bitarts.parsian.viewModel.PishnehadConstants;
import com.bitarts.parsian.viewModel.ViewElhaghie;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Feb 16, 2011
 * Time: 4:25:50 PM
 */

public class PishnahadAction extends BaseAction implements ServletContextAware {
    private Tarh tarh;
    private String namayandegiTelephone;
    private List<Tarh> tarhs;
    private IPishnehadService pishnehadService;
    private IShakhsService shakhsService;
    private IDarkhastService darkhastService;
    private static IGharardadService gharardadService;
    private IClinicService clinicService;
    private INamayandeService namayandeService;
    private IAsnadeSodorService asnadeSodorService;
    private ILoginService loginService;
    private ILogService logService;
    private ITransitionLogService transitionLogService;
    private IEstelamService estelamService;
    private IConstantItemsService constantItemsService;
    private List<TaghsitReport> taghsitReport;
    private User user;
    private Integer transitionId;
    private String logmessage;
    private Integer pishnehadId;
    private int txtAmount;
    private int txtOrderId;
    private String authorityId;
    private String bankResponse;
    private ArrayList<Transition> allowedTransitions;
    private List<User> karshenasha;
    private List<User> karshenasKhesaratha;
    private User theKarshenas;
    private List<Clinic> clinics;
    private List<City> ostanha;
    private Fish fish;
    private boolean nosession;
    private Pishnehad newPishnehad;
    private String currentDate;
    private String theDate;
    private boolean theDateResult;
    private String theExpiryDate;
    private String today;
    private String defaultExpiryDate;
    private String backfromupload;
    private List<User> bazaryabs;
    private Map<String, String> messagesMap;
    private Map<String, String> errorsMap;
    private String logicaldocIndicator;
    private boolean readOnlyMode = false;
    private List<TransitionLog> transitionLogs;
    private IConstantsService constantsService;
    private boolean ijadPishnehad;
    private boolean fromBimename = false;
    private boolean bimeShodeEditable;
    private boolean bimeGozarEditable;
    private DarkhastTaghirat darkhastTaghirat;
    private List<GhestBandi> ghestBandiList;
    private PaginatedListImpl<Ghest> ghestPaginatedList;
    private boolean isEditBimeShode;
    private boolean isEditBimeGozar;
    private HashMap<String,Object> validationConstants;
    private String pishnehadLoadMessage = "";
    private Integer credebitId;
    private List<KhateSanad> khateSanadList;
    private Integer tarhFormChanged;
    private boolean optionsPishnehad;
    private boolean khesaratAction;
    private Khesarat khesarat;
    private String selectedTab;
    private boolean noZamaem;
    private Bimename bimename;
    private String sarmayeFot;
    private String sarmayeAmraz;
    private String sarmayeHadese;
    private String sarmayeNaghsOzv;

    public String getSarmayeFot()
    {
        return sarmayeFot;
    }

    public void setSarmayeFot(String sarmayeFot)
    {
        this.sarmayeFot = sarmayeFot;
    }

    public String getSarmayeAmraz()
    {
        return sarmayeAmraz;
    }

    public void setSarmayeAmraz(String sarmayeAmraz)
    {
        this.sarmayeAmraz = sarmayeAmraz;
    }

    public String getSarmayeHadese()
    {
        return sarmayeHadese;
    }

    public void setSarmayeHadese(String sarmayeHadese)
    {
        this.sarmayeHadese = sarmayeHadese;
    }

    public String getSarmayeNaghsOzv()
    {
        return sarmayeNaghsOzv;
    }

    public void setSarmayeNaghsOzv(String sarmayeNaghsOzv)
    {
        this.sarmayeNaghsOzv = sarmayeNaghsOzv;
    }

    public Bimename getBimename()
    {
        return bimename;
    }

    public void setBimename(Bimename bimename)
    {
        this.bimename = bimename;
    }

    public boolean isNoZamaem()
    {
        return noZamaem;
    }

    public void setNoZamaem(boolean noZamaem)
    {
        this.noZamaem = noZamaem;
    }

    public String getSelectedTab()
    {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab)
    {
        this.selectedTab = selectedTab;
    }

    public boolean isOptionsPishnehad()
    {
        return optionsPishnehad;
    }

    public void setOptionsPishnehad(boolean optionsPishnehad)
    {
        this.optionsPishnehad = optionsPishnehad;
    }

    public Integer getTarhFormChanged() {
        return tarhFormChanged;
    }

    public void setTarhFormChanged(Integer tarhFormChanged) {
        this.tarhFormChanged = tarhFormChanged;
    }

    public List<KhateSanad> getKhateSanadList()
    {
        return khateSanadList;
    }

    public void setKhateSanadList(List<KhateSanad> khateSanadList)
    {
        this.khateSanadList = khateSanadList;
    }

    public Integer getCredebitId()
    {
        return credebitId;
    }

    public void setCredebitId(Integer credebitId)
    {
        this.credebitId = credebitId;
    }

    public boolean getIsEditBimeGozar()
    {
        return isEditBimeGozar;
    }

    public void setIsEditBimeGozar(boolean editBimeGozar)
    {
        isEditBimeGozar = editBimeGozar;
    }

    public boolean getIsEditBimeShode()
    {
        return isEditBimeShode;
    }

    public void setIsEditBimeShode(boolean editBimeShode)
    {
        isEditBimeShode = editBimeShode;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.setShakhsService((IShakhsService) wac.getBean(IShakhsService.SERVICE_NAME));
        this.clinicService = (IClinicService) wac.getBean(IClinicService.SERVICE_NAME);
        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.logService = (ILogService) wac.getBean(ILogService.SERVICE_NAME);
        this.transitionLogService = (ITransitionLogService) wac.getBean(ITransitionLogService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean("darkhastService");
        this.gharardadService = (IGharardadService) wac.getBean("gharardadService");
        this.namayandeService= (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.constantsService = (IConstantsService) wac.getBean("constantsService");
    }

    private Pishnehad pishnehad;
    private Estelam estelam;
    private PishnehadConstants pishnehadConstants;

    public String save() {
        User loggedInUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            if (pishnehad.getId() != null) {
                //           todo : to logging changes, I used a simplest way with low efficiency. I will correct this later.
                Pishnehad oldPishnehad = pishnehadService.findById(pishnehad.getId());
                // dont edit bimename
                if(oldPishnehad.getBimename() != null && oldPishnehad.getBimename().getReadyToShow().equals("yes") && oldPishnehad.getValid())
                    return SUCCESS;
                if (pishnehad.getAddressBimeGozar().getOstaanManzel().getCityId() == null) {
                    pishnehad.getAddressBimeGozar().setOstaanManzel(null);
                    pishnehad.getAddressBimeGozar().setShahrManzel(null);
                }
                if (estelam.getId() == null) {
                    estelam = estelamService.adapte(estelam);
                    estelamService.addNewEstelam(estelam);
                }
                pishnehad.setEstelam(estelam);
                Set<Role> allTheRoles = loggedInUser.getRoles();
                for (Role allTheRole : allTheRoles) {
                    if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR)) {
                        pishnehad.setProcessor(Constant.ROLE_KARSHENAS_SODOUR);
                    } else if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)) {
                        pishnehad.setProcessor(Constant.ROLE_KARSHENAS_MASOUL);
                    } else {
                        pishnehad.setProcessor(oldPishnehad.getProcessor());
                    }

                }
                if (pishnehad.getTarh() != null && pishnehad.getTarh().getId() != null) {
                        pishnehad.setTarh(constantsService.findTarhById(pishnehad.getTarh().getId()));
                } else {
                    pishnehad.setTarh(null);
                }
                if (pishnehad.getGharardad() != null && pishnehad.getGharardad().getId() != null) {
                    pishnehad.setGharardad(gharardadService.findById(pishnehad.getGharardad().getId()));
                } else {
                    pishnehad.setGharardad(null);
                }
                pishnehad.setKarshenas(oldPishnehad.getKarshenas());
                pishnehad.setNaghsPishnehad(oldPishnehad.getNaghsPishnehad());
                pishnehad.setElameHesab(oldPishnehad.getElameHesab());
                pishnehad.setZamaem(oldPishnehad.getZamaem());
                if(oldPishnehad.getEstelam() != null) {
                    pishnehad.getEstelam().setTabaghe_khatar(oldPishnehad.getEstelam().getTabaghe_khatar());
                    pishnehad.getEstelam().setTabaghe_khatar_naghsozv(oldPishnehad.getEstelam().getTabaghe_khatar_naghsozv());
                    pishnehad.getEstelam().setDarsad_ezafe_nerkh_pezeshki(oldPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());
                }
                pishnehadService.updatePishnehad(pishnehad);
                oldPishnehad.getNamayande().setTelephone(namayandegiTelephone);
                namayandeService.editNamayande(oldPishnehad.getNamayande());
            } else {
                //                todo: ijad pishnehad az estelam
//                if (estelam.getId() == null) {
                    estelam = estelamService.adapte(estelam);
                    estelamService.addNewEstelam(estelam);
//                }
                if (grouh != null && grouh != -1) {
                    Gharardad gharardad = gharardadService.findById(grouh);
                    pishnehad.setGharardad(gharardad);
//                    pishnehad.setTarh(gharardad.getTarh());
                    putToSession("grouh", grouh);

                }
                if(pishnehad.getGharardad() != null && pishnehad.getGharardad().getId() != null){
                    Gharardad gharardad = gharardadService.findById(pishnehad.getGharardad().getId());
                    pishnehad.setGharardad(gharardad);
//                    pishnehad.setTarh(gharardad.getTarh());
                }else{
                    pishnehad.setGharardad(null);
                    pishnehad.setTarh(null);
                }
                if (pishnehad.getTarh() != null && pishnehad.getTarh().getId() != null) {
                    pishnehad.setTarh(constantsService.findTarhById(pishnehad.getTarh().getId()));
                    putToSession("tarh", tarh);
                } else {
                    pishnehad.setTarh(null);
                }
                if (loggedInUser.getNamayandegi() != null) {
                    pishnehad.setNamayande(loggedInUser.getNamayandegi());
                } else if (pishnehad.getGharardad() != null && pishnehad.getGharardad().getNamayande() != null) {
                    pishnehad.setNamayande(pishnehad.getGharardad().getNamayande());
                }

                Zamaem zamaem = new Zamaem();
                int zamId = pishnehadService.saveZamaem(zamaem);
                Zamaem theZamime = pishnehadService.findZamaemById(zamId);

                User bazarYab = loginService.findUserById(pishnehad.getBazarYab().getId());
                Set<Role> allTheRoles = loggedInUser.getRoles();
                for (Role allTheRole : allTheRoles) {
                    if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR)) {
                        pishnehad.setProcessor(Constant.ROLE_KARSHENAS_SODOUR);
                    } else if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)) {
                        pishnehad.setProcessor(Constant.ROLE_KARSHENAS_MASOUL);
                    }

                }
                // city stuff
                if (pishnehad.getAddressBimeGozar().getOstaanManzel().getCityId() != null) {
                    City bimeGozarOstan = constantItemsService.findCityById(pishnehad.getAddressBimeGozar().getOstaanManzel().getCityId());
                    pishnehad.getAddressBimeGozar().setOstaanManzel(bimeGozarOstan);
                    City bimeGozarShahr = constantItemsService.findCityById(pishnehad.getAddressBimeGozar().getShahrManzel().getCityId());
                    pishnehad.getAddressBimeGozar().setShahrManzel(bimeGozarShahr);
                } else {
                    pishnehad.getAddressBimeGozar().setOstaanManzel(null);
                    pishnehad.getAddressBimeGozar().setShahrManzel(null);

                }
                City bimeGozarOstanKar = constantItemsService.findCityById(pishnehad.getAddressBimeGozar().getOstaanMahalleKar().getCityId());
                City bimeGozarShahrKar = constantItemsService.findCityById(pishnehad.getAddressBimeGozar().getShahrMahalleKar().getCityId());
                City bimeShodeOstan = constantItemsService.findCityById(pishnehad.getAddressBimeShode().getOstaanManzel().getCityId());
                City bimeShodeOstanKar = constantItemsService.findCityById(pishnehad.getAddressBimeShode().getOstaanMahalleKar().getCityId());
                City bimeShodeShahr = constantItemsService.findCityById(pishnehad.getAddressBimeShode().getShahrManzel().getCityId());
                City bimeShodeShahrKar = constantItemsService.findCityById(pishnehad.getAddressBimeShode().getShahrMahalleKar().getCityId());

                pishnehad.getAddressBimeGozar().setOstaanMahalleKar(bimeGozarOstanKar);
                pishnehad.getAddressBimeGozar().setShahrMahalleKar(bimeGozarShahrKar);
                pishnehad.getAddressBimeShode().setOstaanManzel(bimeShodeOstan);
                pishnehad.getAddressBimeShode().setOstaanMahalleKar(bimeShodeOstanKar);
                pishnehad.getAddressBimeShode().setShahrManzel(bimeShodeShahr);
                pishnehad.getAddressBimeShode().setShahrMahalleKar(bimeShodeShahrKar);

                pishnehad.setUserCreator(loggedInUser);
                pishnehad.setEstelam(estelam);
                estelam.setPishnehad(pishnehad);
                pishnehad.setZamaem(theZamime);
                pishnehad.setBazarYab(bazarYab);
                pishnehad.setValid(true);
                pishnehadService.saveNewPishnehad(pishnehad);
                estelamService.updateEstelam(estelam);
                Namayande n = namayandeService.getNamayandeById(loggedInUser.getNamayandegi().getId());
                if(n!=null)
                {
                    n.setTelephone(namayandegiTelephone);
                    namayandeService.editNamayande(n);
                }
                transitionLogService.logCreation(loggedInUser, pishnehad, Constant.PISHNEHAD_CREATION_LOG_MESSAGE);
            }
            if(darkhastTaghirat != null)
                return "successTaghirat";
            return SUCCESS;
        }
    }


    public String saveEstelam() {
        User theUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (theUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            adapte();
            estelam.setInsertRaeisSarmayeHadese(false);
            estelamService.addNewEstelam(estelam);
            return load();
        }
    }

    public void adapte() {
        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;

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

        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty()) estelam.setDarsad_ezafe_nerkh_pezeshki("0");
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
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equals("undefined")) estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equals("undefined")) estelam.setTabaghe_khatar_naghsozv("1");
//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

    }

    private Integer fishId;

    public Integer getFishId() {
        return fishId;
    }

    public void setFishId(Integer fishId) {
        this.fishId = fishId;
    }

    public String deleteFish() {
        pishnehadService.deleteFish(fishId);
        return SUCCESS;
    }

    public String ajaxlySetPrintedForPishnehad() {
        pishnehad = pishnehadService.findById(pishnehad.getId());
        pishnehad.setInitialyPrinted("yes");
        pishnehadService.updatePishnehad(pishnehad);
        return SUCCESS;
    }

    public String setTarikhEnghezaAjaxly() {
        theDateResult = DateUtil.isGreaterThanOrEqual(theDate, DateUtil.getCurrentDate());
        theExpiryDate = DateUtil.addYear(theDate, Integer.parseInt(pishnehad.getEstelam().getModat_bimename()));
        today = DateUtil.getCurrentDate();
        defaultExpiryDate = DateUtil.addYear(today, Integer.parseInt(pishnehad.getEstelam().getModat_bimename()));
        return SUCCESS;

    }

    public String changeTabagheKhatarAjaxly() {
        Pishnehad thePishnehad = pishnehadService.findById(pishnehad.getId());
        if(thePishnehad.getBimename() == null || !thePishnehad.getValid() || !thePishnehad.getBimename().getReadyToShow().equals("yes")) {
            thePishnehad.getEstelam().setTabaghe_khatar(pishnehad.getEstelam().getTabaghe_khatar());
            if(pishnehad.getEstelam().getTabaghe_khatar_naghsozv() != null && pishnehad.getEstelam().getTabaghe_khatar_naghsozv().equals("undefined"))
                pishnehad.getEstelam().setTabaghe_khatar_naghsozv("1");
            thePishnehad.getEstelam().setTabaghe_khatar_naghsozv(pishnehad.getEstelam().getTabaghe_khatar_naghsozv());
            pishnehadService.updatePishnehad(thePishnehad);
        }
        return SUCCESS;
    }

    public String dateChecker() {
        theDateResult = DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(), theDate);
        today = DateUtil.getCurrentDate();
        return SUCCESS;
    }

    public String loadDetailsOfGhest()
    {
         khateSanadList = asnadeSodorService.findAllKhateSanadByCredebitId(credebitId);
         return SUCCESS;
    }

    public String loadReadOnly() throws ServiceException, RemoteException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        if (resultUser.getId() == -1) {
            nosession = true;
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
            if (pishnehad.getGharardad() != null && pishnehad.getGharardad().getId() != null) {
                grouh = pishnehad.getGharardad().getId();
                putToSession("grouh", grouh);
                Gharardad gharardad = gharardadService.findById(pishnehad.getGharardad().getId());
                pishnehad.setGharardad(gharardad);
//                pishnehad.setTarh(gharardad.getTarh());
            }
            if (pishnehad.getTarh() != null && pishnehad.getTarh().getId() != null) {
                tarh = pishnehad.getTarh();
                putToSession("tarh", tarh);
            }
            if(pishnehad.getBimename() != null)
            {
                fromBimename = true;
            }
            if (Constant.DEV_nezaratEnabled)
                karshenasha = pishnehadService.findAllKarshenasForPishnehads(pishnehad.getEstelam().getSarmaye_paye_fot_long());
            else
                karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
            karshenasKhesaratha = pishnehadService.findAllKarshenasKhesarat();
            ostanha = constantItemsService.getOstans();
            readOnlyMode = true;
            transitionLogs = transitionLogService.getPishnehadLogs(pishnehad.getId());
            Collections.sort(transitionLogs);
            if(resultUser.hasRole("ROLE_KARMOZD")|| resultUser.hasRole("ROLE_KARMOZD_NAMAYANDE"))
            {

                ghestPaginatedList=new PaginatedListImpl<Ghest>();
                if (isExport())
                {
                    ghestPaginatedList.setPageNumber(0);
                    ghestPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                }
                else
                {
                    int pageNumber=0;
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
                    ghestPaginatedList.setPageNumber(pageNumber);
                    ghestPaginatedList.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
                }
                ghestBandiList=asnadeSodorService.ghestBandiList4SaleAval(pishnehad.getBimename().getId());
                ghestPaginatedList=asnadeSodorService.findAllBedehisByBimenameIdPaging(pishnehad.getBimename().getId(),ghestPaginatedList) ;
            }
//            if (pishnehad.getBimename().getDarkhastBazkharid() != null){
//                darkhast = pishnehad.getBimename().getDarkhastBazkharid();
//
////                darkhastService.transitDarkhast(user, darkhast.getId(), 617, logmessage);
//                return "stayOnDarkhastPlace";
//            }

            return SUCCESS;
        }
    }

    String term;
    List<CitySearch> citySearchResult;

    public List<CitySearch> getCitySearchResult() {
        return citySearchResult;
    }

    public void setCitySearchResult(List<CitySearch> citySearchResult) {
        this.citySearchResult = citySearchResult;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String cityForSearch) {
        this.term = cityForSearch;
    }

    public String searchCity() {
        try {
            List<City> citySearchResultTemp = constantItemsService.findCityByName(getTerm().replaceAll("ی", "ي").replaceAll("ک", "ك"));
            citySearchResult = new ArrayList<CitySearch>();
            for (int i = 0; i < citySearchResultTemp.size(); i++) {
                if (citySearchResultTemp.get(i).getCityPid() != null) {
                    CitySearch citySearchItem = new CitySearch();
                    City parent = constantItemsService.findCityByIdForPid(citySearchResultTemp.get(i).getCityPid());
                    if (parent != null) {
                        citySearchItem.setTitle(citySearchResultTemp.get(i).getCityName() + ", " + parent.getCityName());
                        citySearchItem.setParent(parent);
                        citySearchItem.setCity(citySearchResultTemp.get(i));
                        citySearchResult.add(citySearchItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    private List<Gharardad> grouhha;
    private Long grouh;

    public String load() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User resultUser = loginService.findUserByUsername(username);
        currentDate = DateUtil.getCurrentDate();
        if (resultUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            Collection<String> values = getSession().keySet();
            setIjadPishnehad(true);
            if (values.contains("messagesMap")) {
                messagesMap = (HashMap<String, String>) getSession().remove(Constant.MESSAGES_MAP);
            }
            if (values.contains("errorsMap")) {
                errorsMap = (HashMap<String, String>) getSession().remove(Constant.ERRORS_MAP);
                fish = (Fish) getSession().remove("fish");
            }
            if (values.contains("backfromupload")) {
                backfromupload = (String) getSession().remove("backfromupload");
            }
            if (values.contains("logicaldocindicator")) {
                logicaldocIndicator = (String) getSession().remove("logicaldocindicator");
            }
            pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();
            user = resultUser;
            if (pishnehad != null && pishnehad.getId() != null) {
                pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                setIjadPishnehad(false);
                estelam = pishnehad.getEstelam();
                allowedTransitions = new ArrayList<Transition>();
                clinics = clinicService.findAllClinics();

                if (pishnehad.getGharardad() != null) {
                    grouh = pishnehad.getGharardad().getId();
                    putToSession("grouh", grouh);
                }
                if (pishnehad.getTarh() != null) {
                    tarh = pishnehad.getTarh();
                    putToSession("tarh", tarh);
                }

                if(Constant.DEV_nezaratEnabled && pishnehad.getEstelam() != null)
                    karshenasha = pishnehadService.findAllKarshenasForPishnehads(pishnehad.getEstelam().getSarmaye_paye_fot_long());
                else
                    karshenasha = pishnehadService.findAllKarshenasForPishnehads("");
                karshenasKhesaratha = pishnehadService.findAllKarshenasKhesarat();
                ostanha = constantItemsService.getOstans();
                Set<Transition> transitionSet = pishnehad.getState().getTransitions();
                for (Transition transition : transitionSet) {
                    if (transition.getStateBegin().equals(pishnehad.getState()) && transition.getTransitionShow().equals("yes")) {
                        for(Role role : user.getRoles()) {
                            for(Role roleT : transition.getRoles()) {
                                if(role.getId().equals(roleT.getId()) && !allowedTransitions.contains(transition)) {
                                    allowedTransitions.add(transition);
                                }
                            }
                        }
                    }
                }
                Collections.sort(allowedTransitions);
                transitionLogs = transitionLogService.getPishnehadLogs(pishnehad.getId());
                Collections.sort(transitionLogs);
                if (pishnehad.getState().getId() == 40 || pishnehad.getState().getId() == 110) {
                    List<Fish> fishs = pishnehad.getFishs();
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
                setBimegozarAndBimeshodeEditable();
                bazaryabs = pishnehadService.findBazaryabForNamayande(pishnehad.getNamayande());
            } else {
                setOptionsPishnehad(optionsPishnehad);
                bimeShodeEditable = true;
                bimeGozarEditable = true;
                bazaryabs = pishnehadService.findBazaryabForNamayande(resultUser.getNamayandegi());
            }
            if (pishnehad != null && pishnehad.getTarh() != null && pishnehad.getTarh().getId() != null) {
                tarh = pishnehad.getTarh();
                putToSession("tarh", tarh);
            }
            if(pishnehad!=null && pishnehad.getBimeGozar()!=null)
            {
                    String biGozarShakhsCodeMeli=pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi();

//                if(!pishnehadService.isMoreThanOnePishnehadByShakhs(biGozarShakhsCodeMeli))
//                {
//                    isEditBimeGozar=true;
//                }
//                else
//                {
//                    isEditBimeGozar=false;
//                }
//                String biShodeShakhsCodeMeli=pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi();
//                if(!pishnehadService.isMoreThanOnePishnehadByShakhs(biShodeShakhsCodeMeli))
//                {
//                    isEditBimeShode=true;
//                }
//                else
//                {
//                    isEditBimeShode=false;
//                }
            }
            //----- initialize Validation Constants ------------------
            String date = DateUtil.getCurrentDate();
            if(pishnehad!=null)
            {
                if (pishnehad.getBimename() != null) {
                    date = pishnehad.getBimename().getTarikhSodour();
                }
                validationConstants = AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date);
            }
            else
            {
                validationConstants = AsnadeSodorService.getValidationConstantsSet(null, date);
            }

            //--------------------------------------------------------

            if(pishnehad !=null && pishnehad.getState().getId() == 245) {
                PaginatedListImpl<BimenameVM> bimenameVMPaginatedList = new PaginatedListImpl<BimenameVM>();
                bimenameVMPaginatedList.setPageNumber(0);
                bimenameVMPaginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                bimenameVMPaginatedList = pishnehadService.searchBimenamePaginated(bimenameVMPaginatedList, null, null, null, null, null,
                        pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(), null, "1386/01/01", DateUtil.getCurrentDate(), null, null, null, null, null, null, resultUser, null,
                        null, null, null, null, null, null, null, false);
                for(BimenameVM b : bimenameVMPaginatedList.getList()) {
                    String shomare = b.getShomareBimename();
                    String sarmaye = pishnehadService.findBimenameById(b.getBimenameId()).getPishnehad().getEstelam().getSarmaye_paye_fot();
                    pishnehadLoadMessage += "بیمه شده دارای بیمه نامه شماره " + "<br/>"+shomare + "با سرمایه فوت " + sarmaye + "ریال می باشد." + "<br/>";
                }
            }

//            if (pishnehad != null && pishnehad.getBimename() != null && pishnehad.getBimename().getKhesarats() != null && pishnehad.getBimename().getKhesarats().size() > 0){
//                readOnlyMode = true;
//                khesaratAction = true;
//                khesarat = pishnehad.getBimename().getKhesarats().get(0);
//                return "loadKhesarat";
//            }
            return SUCCESS;
        }
    }

    public String getValidationConstAjax(){
        String date = DateUtil.getCurrentDate();
        if(tarhFormChanged!=null)
        {
            if (pishnehad != null && pishnehad.getBimename() != null) {
                date = pishnehad.getBimename().getTarikhSodour();
            } else if(pishnehad != null && pishnehad.getId() != null) {
                date = pishnehadService.findById(pishnehad.getId()).getBimename().getTarikhSodour();
            }

            Tarh tarh = constantsService.findTarhById(Long.parseLong(tarhFormChanged.toString()));
            validationConstants = AsnadeSodorService.getValidationConstantsSet(tarh, date);
        }
        else
        {
            validationConstants = AsnadeSodorService.getValidationConstantsSet(null, date);
        }
        return SUCCESS;
    }

    private void setBimegozarAndBimeshodeEditable() {
        bimeShodeEditable = shakhsService.isPersonEditable(user.getUsername(), true, pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi());
        if(pishnehad.getBimeGozar().getShakhs().getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
            bimeGozarEditable = shakhsService.isPersonEditable(user.getUsername(), true, pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi());
        else
            bimeGozarEditable = shakhsService.isPersonEditable(user.getUsername(), false, pishnehad.getBimeGozar().getShakhs().getShomareSabt());
        if(isHalateElhaghie())
            bimeGozarEditable = true;
    }


    public boolean doesFishMatchCredebit(Fish f, Credebit c)
    {
        if(c.getAmount().equalsIgnoreCase(f.getMablagh()) && c.getShomareMoshtari().equalsIgnoreCase(f.getShomare()) && c.getDaryafteFish().getBank().equalsIgnoreCase(f.getBankName()) && c.getDaryafteFish().getKodeShobe().equalsIgnoreCase(f.getKodeShobe()) && c.getCreatedDate().equalsIgnoreCase(f.getTarikh()))
        {
            String credebitTime = c.getCreatedTime();
            if(credebitTime.length() > 5)
                credebitTime = credebitTime.substring(3);
            if(credebitTime.equals(f.getTime()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean isHalateElhaghie;

    public String loadForElhaghie() throws ServiceException, RemoteException {
        isHalateElhaghie = true;
        isEditBimeGozar = true;
        isEditBimeShode = true;
        return load();
    }

    private String fromValue;
    private String toValue;
    List<TaghsitReport> newTaghsitReport;
    private ViewElhaghie viewElhaghie;

    //---------------------------------------------------------------------------------------------------------------------
    public String pardakhtBaFish() {
        Pishnehad thePishnehad = pishnehadService.findById(pishnehadId);
        pishnehadId = thePishnehad.getId();
        return SUCCESS;
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

    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public List<TaghsitReport> getTaghsitReport() {
        return taghsitReport;
    }

    public void setTaghsitReport(List<TaghsitReport> taghsitReport) {
        this.taghsitReport = taghsitReport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(Integer transitionId) {
        this.transitionId = transitionId;
    }

    public String getLogmessage() {
        return logmessage;
    }

    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }

    public Integer getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(Integer pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public int getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(int txtAmount) {
        this.txtAmount = txtAmount;
    }

    public int getTxtOrderId() {
        return txtOrderId;
    }

    public void setTxtOrderId(int txtOrderId) {
        this.txtOrderId = txtOrderId;
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

    public ArrayList<Transition> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(ArrayList<Transition> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public List<User> getKarshenasha() {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public User getTheKarshenas() {
        return theKarshenas;
    }

    public void setTheKarshenas(User theKarshenas) {
        this.theKarshenas = theKarshenas;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public Fish getFish() {
        return fish;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public List<TaghsitReport> getNewTaghsitReport() {
        return newTaghsitReport;
    }

    public void setNewTaghsitReport(List<TaghsitReport> newTaghsitReport) {
        this.newTaghsitReport = newTaghsitReport;
    }

    public ViewElhaghie getViewElhaghie() {
        return viewElhaghie;
    }

    public void setViewElhaghie(ViewElhaghie viewElhaghie) {
        this.viewElhaghie = viewElhaghie;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public boolean isHalateElhaghie() {
        return isHalateElhaghie;
    }

    public void setHalateElhaghie(boolean halateElhaghie) {
        isHalateElhaghie = halateElhaghie;
    }

    public Pishnehad getNewPishnehad() {
        return newPishnehad;
    }

    public void setNewPishnehad(Pishnehad newPishnehad) {
        this.newPishnehad = newPishnehad;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTheDate() {
        return theDate;
    }

    public void setTheDate(String theDate) {
        this.theDate = theDate;
    }

    public boolean isTheDateResult() {
        return theDateResult;
    }

    public void setTheDateResult(boolean theDateResult) {
        this.theDateResult = theDateResult;
    }

    public String getTheExpiryDate() {
        return theExpiryDate;
    }

    public void setTheExpiryDate(String theExpiryDate) {
        this.theExpiryDate = theExpiryDate;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getDefaultExpiryDate() {
        return defaultExpiryDate;
    }

    public void setDefaultExpiryDate(String defaultExpiryDate) {
        this.defaultExpiryDate = defaultExpiryDate;
    }

    public String getBackfromupload() {
        return backfromupload;
    }

    public void setBackfromupload(String backfromupload) {
        this.backfromupload = backfromupload;
    }

    public List<User> getBazaryabs() {
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    public Map<String, String> getMessagesMap() {
        return messagesMap;
    }

    public void setMessagesMap(Map<String, String> messagesMap) {
        this.messagesMap = messagesMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public String getLogicaldocIndicator() {
        return logicaldocIndicator;
    }

    public void setLogicaldocindicator(String logicaldocIndicator) {
        this.logicaldocIndicator = logicaldocIndicator;
    }

    public boolean isReadOnlyMode() {
        return readOnlyMode;
    }

    public void setReadOnlyMode(boolean readOnlyMode) {
        this.readOnlyMode = readOnlyMode;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public List<City> getOstanha() {
        return ostanha;
    }

    public void setOstanha(List<City> ostanha) {
        this.ostanha = ostanha;
    }

    public Boolean taghirKodAction = false;

    public String darkhastTaghirKod() {
        try {
            String tmp = loadReadOnly();
            taghirKodAction = true;
            return SUCCESS;
        } catch (Exception ex) {
            return ERROR;
        }
    }

    public Boolean getTaghirKodAction() {
        return taghirKodAction;
    }

    public void setTaghirKodAction(Boolean taghirKodAction) {
        this.taghirKodAction = taghirKodAction;
    }

    private DarkhastBazkharid darkhast;

    public String sabtDarkhastTaghirKod() {
        try {
            darkhast = new DarkhastBazkharid();
            darkhast.setTaghirKodBe(pishnehad.getNamayande().getKodeNamayandeKargozar());
            pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
            darkhast.setDarkhastType(DarkhastBazkharid.DarkhastType.TAGHIRKOD);
            darkhast.setFinished(false);
            darkhast.setBimename(pishnehad.getBimename());
            darkhast.setTarikhDarkhast(DateUtil.getCurrentDate());
            darkhast.setShomareDarkhast(pishnehad.getBimename().getShomare());
            darkhast.setNameBimeShode(pishnehad.getBimeGozar().getShakhs().getFullName());
            darkhast.setKodeNamayandegi(pishnehad.getNamayande().getKodeNamayandeKargozar());
            darkhastService.saveDarkhastTaghirKod(darkhast);
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
    }

    public DarkhastBazkharid getDarkhast() {
        return darkhast;
    }

    public void setDarkhast(DarkhastBazkharid darkhast) {
        this.darkhast = darkhast;
    }

    private Boolean darkhastTozih = false;

    public String darkhastKhesarat()
    {
        user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getId() == -1)
        {
            nosession = true;
            return Constant.NOSESSION;
        }
        else
        {
            pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
            if (pishnehad.getBimename().isVameTasvieNashode())
            {
                addActionMessage("این بیمه نامه دارای وام تسویه نشده است.");
//                return ERROR;
            }

            try
            {
                String tmp = loadReadOnly();
                khesaratAction = true;
                try
                {

                    if(darkhast == null || darkhast.getId()==null)
                    {
                        darkhast = new DarkhastBazkharid();
                        darkhast.setDarkhastType(DarkhastBazkharid.DarkhastType.KHESARAT);
                        darkhast.setFinished(false);
                        darkhast.setBimename(pishnehad.getBimename());
        //                darkhast.getBimename().setState(constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE));\
                        darkhast.setTarikhDarkhast(DateUtil.getCurrentDate());
                        darkhast.setShomareDarkhast(pishnehad.getBimename().getShomare());
                        darkhast.setNameBimeShode(pishnehad.getBimeGozar().getShakhs().getFullName());
                        darkhast.setKodeNamayandegi(pishnehad.getNamayande().getKodeNamayandeKargozar());
                        darkhast.setNamayande(pishnehad.getNamayande());
        //                darkhast.setState(constantItemsService.findStateById(3000));
                        Darkhast d = new Darkhast();
                        d.setDarkhastType(Darkhast.DarkhastType.KHESARAT);
                        d.setFinished(false);
                        d.setDarkhastBazkharid(darkhast);

                        darkhastService.saveDarkhastTozih(darkhast);
                        darkhastService.saveDarkhast(d);
                        //                pishnehadService.updateBimename(darkhast.getBimename());
                        darkhast.setDarkhast(d);
                        darkhastService.updateDarkhastBazkharid(darkhast);
                    }
                    selectedTab="tabs_36";
                    readOnlyMode = false;
                    noZamaem=true;
                    bimename= pishnehad.getBimename();

                    MohasebateTeory mohasebateTeory = new MohasebateTeory();
                    List<TaghsitReport> taghsitReport = mohasebateTeory.taghsitReport(pishnehad, true, -1);
                    TaghsitReport tr = null;
                    tr = taghsitReport.get(bimename.getCurrentSaleBimei());

                    sarmayeFot = NumberFormat.getNumberInstance().format(tr.getSarmaayeFotBehHarEllat());
                    sarmayeAmraz = NumberFormat.getNumberInstance().format(tr.getSarmaayePusheshEzaafiAmraazKhaas());
                    sarmayeHadese = NumberFormat.getNumberInstance().format(tr.getSarmaayeFotDarAsarHaadeseh());
                    sarmayeNaghsOzv = NumberFormat.getNumberInstance().format(tr.getSarmaayePusheshEzaafiNaghsOzv());

                    Long allAmountsKhesaratAmraz = 0l;
                    Long allAmountsKhesaratNaghsOzv = 0l;
                    for (Khesarat kh : bimename.getKhesarats())
                    {
                        if (kh.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                            allAmountsKhesaratAmraz += Long.parseLong(kh.getAmountGhabelPardakht().replaceAll(",", "").trim());
                        if (kh.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                            allAmountsKhesaratNaghsOzv += Long.parseLong(kh.getAmountGhabelPardakht().replaceAll(",", "").trim());
                    }
                    sarmayeAmraz = NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeAmraz.replaceAll(",", "").trim()) - allAmountsKhesaratAmraz);
                    sarmayeNaghsOzv = NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeNaghsOzv.replaceAll(",", "").trim()) - allAmountsKhesaratNaghsOzv);

                    return SUCCESS;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    return ERROR;
                }
            }
            catch (Exception ex)
            {
                return ERROR;
            }
        }
    }
    public String darkhastTozih() {
        try {
            String tmp = loadReadOnly();
            darkhastTozih = true;
            try {
                darkhast = new DarkhastBazkharid();
                darkhast.setDarkhastType(DarkhastBazkharid.DarkhastType.TOZIH);
                darkhast.setFinished(false);
                darkhast.setBimename(pishnehad.getBimename());
//                darkhast.getBimename().setState(constantItemsService.findStateById(Constant.BIMENAME_LOCK_STATE));
                darkhast.getBimename().setDarkhastDarJaryanType(Darkhast.DarkhastType.TOZIH);
                darkhast.setTarikhDarkhast(DateUtil.getCurrentDate());
                darkhast.setShomareDarkhast(pishnehad.getBimename().getShomare());
                darkhast.setNameBimeShode(pishnehad.getBimeGozar().getShakhs().getFullName());
                darkhast.setKodeNamayandegi(pishnehad.getNamayande().getKodeNamayandeKargozar());
                darkhast.setNamayande(pishnehad.getNamayande());
//                darkhast.setState(constantItemsService.findStateById(3000));
                Darkhast d = new Darkhast();
                d.setDarkhastType(Darkhast.DarkhastType.TOZIH);
                d.setFinished(false);
                d.setDarkhastBazkharid(darkhast);

                darkhastService.saveDarkhastTozih(darkhast);
                pishnehadService.updateBimename(darkhast.getBimename());
                darkhastService.saveDarkhast(d);
                return SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                return ERROR;
            }
        } catch (Exception ex) {
            return ERROR;
        }
    }

    public Boolean getDarkhastTozih() {
        return darkhastTozih;
    }

    public void setDarkhastTozih(Boolean darkhastTozih) {
        this.darkhastTozih = darkhastTozih;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhast;
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

    public Long getGrouh() {
        if (grouh == null) grouh = (Long) getFromSession("grouh");
        return grouh;
    }

    public void setGrouh(Long grouh) {
        this.grouh = grouh;
    }

    public List<Tarh> getTarhs() {
        if (tarhs == null)
            tarhs = constantsService.listAllTarhs();
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public Tarh getTarh() {
        if (tarh == null) tarh = (Tarh) getFromSession("tarh");
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
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

    public static IGharardadService getGharardadService() {
        return gharardadService;
    }

    public static void setGharardadService(IGharardadService gharardadService) {
        PishnahadAction.gharardadService = gharardadService;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
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

    public ITransitionLogService getTransitionLogService() {
        return transitionLogService;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public IEstelamService getEstelamService() {
        return estelamService;
    }

    public void setEstelamService(IEstelamService estelamService) {
        this.estelamService = estelamService;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public boolean isIjadPishnehad() {
        return ijadPishnehad;
    }

    public void setIjadPishnehad(boolean ijadPishnehad) {
        this.ijadPishnehad = ijadPishnehad;
    }

    public String getNamayandegiTelephone() {
        return namayandegiTelephone;
    }

    public void setNamayandegiTelephone(String namayandegiTelephone) {
        this.namayandegiTelephone = namayandegiTelephone;
    }

    public boolean isFromBimename() {
        return fromBimename;
    }

    public void setFromBimename(boolean fromBimename) {
        this.fromBimename = fromBimename;
    }

    public boolean isBimeShodeEditable() {
        return bimeShodeEditable;
    }

    public void setBimeShodeEditable(boolean bimeShodeEditable) {
        this.bimeShodeEditable = bimeShodeEditable;
    }

    public boolean isBimeGozarEditable() {
        return bimeGozarEditable;
    }

    public void setBimeGozarEditable(boolean bimeGozarEditable) {
        this.bimeGozarEditable = bimeGozarEditable;
    }

    public IShakhsService getShakhsService() {
        return shakhsService;
    }

    public void setShakhsService(IShakhsService shakhsService) {
        this.shakhsService = shakhsService;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public List<GhestBandi> getGhestBandiList()
    {
        return ghestBandiList;
    }

    public void setGhestBandiList(List<GhestBandi> ghestBandiList)
    {
        this.ghestBandiList = ghestBandiList;
    }

    public PaginatedListImpl<Ghest> getGhestPaginatedList()
    {
        return ghestPaginatedList;
    }

    public void setGhestPaginatedList(PaginatedListImpl<Ghest> ghestPaginatedList)
    {
        this.ghestPaginatedList = ghestPaginatedList;
    }

    public HashMap<String, Object> getValidationConstants() {
        return validationConstants;
    }

    public void setValidationConstants(HashMap<String, Object> validationConstants) {
        this.validationConstants = validationConstants;
    }

    public String getPishnehadLoadMessage() {
        return pishnehadLoadMessage;
    }

    public void setPishnehadLoadMessage(String pishnehadLoadMessage) {
        this.pishnehadLoadMessage = pishnehadLoadMessage;
    }

    public List<User> getKarshenasKhesaratha() {
        return karshenasKhesaratha;
    }

    public void setKarshenasKhesaratha(List<User> karshenasKhesaratha) {
        this.karshenasKhesaratha = karshenasKhesaratha;
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
}

