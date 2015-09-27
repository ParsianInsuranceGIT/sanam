package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.*;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.SharayeteJadid;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSamayehGozariMultiple;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.MostamariAndukhteEntehaDowre;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.calculations.Reports.FRsMultiple;
import com.bitarts.parsian.service.calculations.Reports.MostamariFRs;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.viewModel.PishnehadConstants;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Feb 16, 2011
 * Time: 4:25:50 PM
 */

public class EstelamAction extends BaseAction implements ServletContextAware {

    private IEstelamService estelamService;
    private IPishnehadService pishnehadService;
    private ILoginService loginService;
    private IConstantItemsService constantItemsService;
    private String codeNamayandegi;
    private boolean correctCalculation;
    private boolean nosession;
    private Pishnehad pishnehad;
    private String mablaghAvaliye;
    private String haghBimeMonazam;
    private String sarmayePayeFot;
    private SharayeteJadid sharayeteJadid;
    private Mostamari mostamari;
    private List<MostamariFRs> mostamariFRsList;
    private String checkForMadam = "false";
    private String zamanAsarElhaghie;
    private HashMap<String,Object> validationConstants;
    private IConstantsService constantsService;
    private List<Tarh> tarhs;
    private boolean inEstelam;

    public List<Tarh> getTarhs()
    {
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs)
    {
        this.tarhs = tarhs;
    }

    public String getNaghsDarad() {
        return naghsDarad;
    }

    public void setNaghsDarad(String naghsDarad) {
        this.naghsDarad = naghsDarad;
    }

    private String naghsDarad;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.constantsService = (IConstantsService) wac.getBean(IConstantsService.SERVICE_NAME);
    }

    private Estelam estelam;
    private EstelamReverse estelamReverse;
    private EstelamKhanevade estelamKhanevade;
    private List<FRs> fRsList;
    private List<FRsMultiple> fRsMultipleList;
    private PishnehadConstants pishnehadConstants;
    private UserComment userComment;
    private List<UserComment> userComments;


    public List<User> getBazaryabs() {
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs) {
        this.bazaryabs = bazaryabs;
    }

    private List<User> bazaryabs;

    public String saveNazar() {
        estelamService.saveComment(userComment);
        return null;
    }

    public String viewNazarat() {
        userComments = estelamService.findUserComments();
        return SUCCESS;
    }

    private String currentDate;
    private User user;

    public String loadEstelam() {
        User resultUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        setInEstelam(true);
        if (resultUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        } else {
            user = resultUser;
            return SUCCESS;
        }
    }

    public String calculateForFormeMiani() {

        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;
        pishnehad = pishnehadService.findById(pishnehad.getId());
        estelam = pishnehad.getEstelam();
        String _date = DateUtil.getCurrentDate();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(_date, pishnehad.getTarh());
        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(_date, pishnehad.getTarh());
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(_date, pishnehad.getTarh());
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(_date, pishnehad.getTarh());
        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(_date, pishnehad.getTarh());
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(_date, pishnehad.getTarh());
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(_date, pishnehad.getTarh());
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(_date, pishnehad.getTarh());
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(_date, pishnehad.getTarh());
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(_date, pishnehad.getTarh());
        double edariGyekja = AsnadeSodorService.getEdariGyekja(_date, pishnehad.getTarh());
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(_date, pishnehad.getTarh());
        double[][] lifeTable = AsnadeSodorService.getLifeTable(_date, pishnehad.getTarh());
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(_date, pishnehad.getTarh());
//        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(pishnehad.getBimename().getTarikhSodour());
//        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(pishnehad.getBimename().getTarikhSodour());
//        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(pishnehad.getBimename().getTarikhSodour());
//        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(pishnehad.getBimename().getTarikhSodour());
//        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(pishnehad.getBimename().getTarikhSodour());
//        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(pishnehad.getBimename().getTarikhSodour());
//        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(pishnehad.getBimename().getTarikhSodour());
//        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(pishnehad.getBimename().getTarikhSodour());
//        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(pishnehad.getBimename().getTarikhSodour());
//        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(pishnehad.getBimename().getTarikhSodour());
//        double edariGyekja = AsnadeSodorService.getEdariGyekja(pishnehad.getBimename().getTarikhSodour());
//        double vosulGyekja = AsnadeSodorService.getVosulGyekja(pishnehad.getBimename().getTarikhSodour());
//        double[][] lifeTable = AsnadeSodorService.getLifeTable(pishnehad.getBimename().getTarikhSodour());
//        String _date = pishnehad.getBimename().getTarikhSodour();
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
        if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("mazad")) {
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
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("null") || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("undefined"))
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
                    , Double.parseDouble(estelam.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelam.getDarsad_takhfif_vosool())
                    , nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja,
                    vosulGyekja, lifeTable, _date, pishnehad.getTarh(), nahveKasrHaghBimePoosheshhayeEzafi, payeyeMohasebat);
            if (fRsList != null) {
                if (Integer.parseInt(estelam.getDarsad_ezafe_nerkh_pezeshki()) != 0) {
                    estelam.setSen_bime_shode(String.valueOf(fRsList.get(0).getSenneBimeShodePasAzEmaleEzafeNerkh()));
                }
            }
        } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
            correctCalculation = false;
        }

        return SUCCESS;
    }

    public String beginMohaasebeMostamari() {
        return SUCCESS;
    }

    public String mohaasebeMostamari() {
        MostamariAndukhteEntehaDowre mostamariAndukhteEntehaDowre = new MostamariAndukhteEntehaDowre();
        mostamariFRsList = mostamariAndukhteEntehaDowre.finalReports(mostamari.getAndukhteEntehaModatBimename()
                , mostamari.getDowreTazminPardakht(), mostamari.getNahvePardakhtMostamari(), mostamari.getNerkhAfzayeshSalaneMostamari()
                , mostamari.getNoeMostamariDarkhasti(), mostamari.getSenMostamariBegir(), AsnadeSodorService.getNerkhBahreFanni(DateUtil.getCurrentDate(), null), AsnadeSodorService.getLifeTable(DateUtil.getCurrentDate(), null));
        if (mostamari.getNoeMostamariDarkhasti().equalsIgnoreCase("madamolomr")) {
            checkForMadam = "true";
        }

        return SUCCESS;
    }


    public String calculate() {
        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;
        naghsDarad = "false";
        if (estelam.getId() != null) {
            estelam = estelamService.getEstelamById(estelam.getId());
        }
        if(estelam.getPishnehad().getTarh().getId()!=null)
        {
            estelam.getPishnehad().setTarh(constantsService.findTarhById(estelam.getPishnehad().getTarh().getId()));
        }
        else
        {
            estelam.getPishnehad().setTarh(null);
        }
        String _date = DateUtil.getCurrentDate();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(_date, estelam.getPishnehad().getTarh());
        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(_date,estelam.getPishnehad().getTarh());
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(_date, estelam.getPishnehad().getTarh());
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(_date, estelam.getPishnehad().getTarh());
        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(_date, estelam.getPishnehad().getTarh());
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(_date, estelam.getPishnehad().getTarh());
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(_date,estelam.getPishnehad().getTarh());
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(_date, estelam.getPishnehad().getTarh());
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(_date, estelam.getPishnehad().getTarh());
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(_date, estelam.getPishnehad().getTarh());
        double edariGyekja = AsnadeSodorService.getEdariGyekja(_date, estelam.getPishnehad().getTarh());
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(_date, estelam.getPishnehad().getTarh());
        double[][] lifeTable = AsnadeSodorService.getLifeTable(_date, estelam.getPishnehad().getTarh());
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(_date,estelam.getPishnehad().getTarh());

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
        if (estelam.getNahve_pardakht_hagh_bime() == null || estelam.getNahve_pardakht_hagh_bime().equals("yekja")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("3mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("6mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("sal")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
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
        if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("mazad")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE;
        } else if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE;
        }

        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        if (zamanAsarElhaghie != null) {
            if (zamanAsarElhaghie.equals("1")) {
                int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelam.getPishnehad().getBimename().getTarikhShorou());
                estelam.setSen_bime_shode(String.valueOf(Integer.parseInt(estelam.getSen_bime_shode()) + bimeYear));
            } else if (zamanAsarElhaghie.equals("2")) {
                int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelam.getPishnehad().getBimename().getTarikhShorou());
                estelam.setSen_bime_shode(String.valueOf(Integer.parseInt(estelam.getSen_bime_shode()) + bimeYear - 1));
            }
        }
        if(Integer.parseInt(estelam.getSen_bime_shode()) < 0) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty())
            estelam.setDarsad_ezafe_nerkh_pezeshki("0");
        estelam.setDarsad_ezafe_nerkh_pezeshki(estelam.getDarsad_ezafe_nerkh_pezeshki().replaceAll(",", "").replaceAll(" ", ""));
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
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equalsIgnoreCase("null") || estelam.getTabaghe_khatar().isEmpty() || estelam.getTabaghe_khatar().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("null") || estelam.getTabaghe_khatar_naghsozv().isEmpty() || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("undefined"))
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
                    , Double.parseDouble(estelam.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelam.getDarsad_takhfif_vosool())
                    , nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja,
                    lifeTable, _date, estelam.getPishnehad() == null ? null : estelam.getPishnehad().getTarh(), nahveKasrHaghBimePoosheshhayeEzafi, payeyeMohasebat);
            if (fRsList != null) {
                if (Integer.parseInt(estelam.getDarsad_ezafe_nerkh_pezeshki()) != 0) {
                    estelam.setSen_bime_shode(String.valueOf(fRsList.get(0).getSenneBimeShodePasAzEmaleEzafeNerkh()));
                }
            }
        } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
            correctCalculation = false;
        }
        if (pooshesh_naghs_ozv_b) {
            naghsDarad = "true";
        }

        return SUCCESS;
    }

    public String calculateMultiple() {
        boolean[] isRegistered = new boolean[estelamKhanevade.getEstelamBimeShodes().length];
        boolean[] pooshesh_naghs_ozv_b = new boolean[estelamKhanevade.getEstelamBimeShodes().length];
        boolean[] pooshesh_fot_dar_asar_zelzele_b = new boolean[estelamKhanevade.getEstelamBimeShodes().length];
        boolean[] pooshesh_moafiat_b = new boolean[estelamKhanevade.getEstelamBimeShodes().length];
        boolean pooshesh_moafiat_dar_soorate_fote_moghadam = false;

        String _date = DateUtil.getCurrentDate();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double edariGyekja = AsnadeSodorService.getEdariGyekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        double[][] lifeTable = AsnadeSodorService.getLifeTable(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(_date, estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh());

        for (int personIndex = 0; personIndex < estelamKhanevade.getEstelamBimeShodes().length; ++personIndex) {
            EstelamBimeShode estelamBimeShode = estelamKhanevade.getEstelamBimeShodes()[personIndex];

            if (estelamBimeShode.getRegistered() != null && estelamBimeShode.getRegistered().equals("yes")) {
                isRegistered[personIndex] = true;
            } else {
                isRegistered[personIndex] = false;
            }

            if (estelamBimeShode.getPusheshFotBarAsareHadese() != null && estelamBimeShode.getPusheshFotBarAsareHadese().equals("yes")) {

            } else {
                estelamBimeShode.setSarmayeFotBarAsareHadese("0");
            }

            if (estelamBimeShode.getPusheshNaghseOzv() != null && estelamBimeShode.getPusheshNaghseOzv().equals("yes")) {
                pooshesh_naghs_ozv_b[personIndex] = true;
            } else {
                pooshesh_naghs_ozv_b[personIndex] = false;
                estelamBimeShode.setSarmayeNaghseOzv("0");
            }
            if (estelamBimeShode.getPusheshFotBarAsareZelzele() != null && estelamBimeShode.getPusheshFotBarAsareZelzele().equals("yes")) {
                pooshesh_fot_dar_asar_zelzele_b[personIndex] = true;
            } else {
                pooshesh_fot_dar_asar_zelzele_b[personIndex] = false;
            }

            if (estelamBimeShode.getPusheshAmrazeKhas() != null && estelamBimeShode.getPusheshAmrazeKhas().equals("yes")) {
            } else {
                estelamBimeShode.setSarmayeAmrazeKhas("0");
            }

            if (estelamBimeShode.getPusheshMoafiat() != null && estelamBimeShode.getPusheshMoafiat().equals("yes")) {
                pooshesh_moafiat_b[personIndex] = true;
            } else {
                pooshesh_moafiat_b[personIndex] = false;
            }

            if (estelamBimeShode.getSen() == null) estelamBimeShode.setSen("0");

            if (zamanAsarElhaghie != null) {
                if (zamanAsarElhaghie.equals("1")) {
                    int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelamKhanevade.getPishnehad().getBimename().getTarikhShorou());
                    estelamBimeShode.setSen(String.valueOf(Integer.parseInt(estelamBimeShode.getSen()) + bimeYear));
                } else if (zamanAsarElhaghie.equals("2")) {
                    int bimeYear = DateUtil.getBimeYear(DateUtil.getCurrentDate(), estelamKhanevade.getPishnehad().getBimename().getTarikhShorou());
                    estelamBimeShode.setSen(String.valueOf(Integer.parseInt(estelamBimeShode.getSen()) + bimeYear - 1));
                }
            }

            if (estelamBimeShode.getDarsadeEzafeNerkhePezeshki() == null || estelamBimeShode.getDarsadeEzafeNerkhePezeshki().isEmpty()) {
                estelamBimeShode.setDarsadeEzafeNerkhePezeshki("0");
            }
            estelamBimeShode.setDarsadeEzafeNerkhePezeshki(estelamBimeShode.getDarsadeEzafeNerkhePezeshki().replaceAll(",", "").replaceAll(" ", ""));

            if (estelamBimeShode.getSarmayeFotBarAsareHadese() == null) {
                estelamBimeShode.setSarmayeFotBarAsareHadese("0");
            }

            if (estelamBimeShode.getSarmayePayeFot() == null) {
                estelamBimeShode.setSarmayePayeFot("0");
            }

            if (estelamBimeShode.getSarmayeAmrazeKhas() == null) {
                estelamBimeShode.setSarmayeAmrazeKhas("0");
            }

            if (estelamBimeShode.getSarmayeNaghseOzv() == null) {
                estelamBimeShode.setSarmayeNaghseOzv("0");
            }

            if (estelamBimeShode.getNerkhAfzayeshSarmayeFot() == null) {
                estelamBimeShode.setNerkhAfzayeshSarmayeFot("0");
            }

            if (estelamBimeShode.getTabagheKhatar() == null || estelamBimeShode.getTabagheKhatar().equalsIgnoreCase("null") || estelamBimeShode.getTabagheKhatar().isEmpty() || estelamBimeShode.getTabagheKhatar().equalsIgnoreCase("undefined")) {
                estelamBimeShode.setTabagheKhatar("1");
            }

            if (estelamBimeShode.getTabagheKhatareNaghseOzv() == null || estelamBimeShode.getTabagheKhatareNaghseOzv().equalsIgnoreCase("null") || estelamBimeShode.getTabagheKhatareNaghseOzv().isEmpty() || estelamBimeShode.getTabagheKhatareNaghseOzv().equalsIgnoreCase("undefined")) {
                estelamBimeShode.setTabagheKhatareNaghseOzv("1");
            }
        }

        NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME nahve = null;
        if (estelamKhanevade.getNahveyePardakht() == null || estelamKhanevade.getNahveyePardakht().equals("yekja")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
        } else if (estelamKhanevade.getNahveyePardakht().equals("mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
        } else if (estelamKhanevade.getNahveyePardakht().equals("3mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
        } else if (estelamKhanevade.getNahveyePardakht().equals("6mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
        } else if (estelamKhanevade.getNahveyePardakht().equals("sal")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
        }

        NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahveDaryaft = null;
        if (estelamKhanevade.getNahveyeDaryafteAndukhteEntehayeModateBimeName() != null) {
            if (estelamKhanevade.getNahveyeDaryafteAndukhteEntehayeModateBimeName().equals("yekja")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.YEKJA;
            } else if (estelamKhanevade.getNahveyeDaryafteAndukhteEntehayeModateBimeName().equals("mos_modat")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MODAT_MOAYAN;
            } else if (estelamKhanevade.getNahveyeDaryafteAndukhteEntehayeModateBimeName().equals("mos_omr")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MADAMOL_OMR;
            }
        }

        NSPConstants.NAHVE_DARYAAFT_MOSTAMERI nahveDaryaftMostamari = null;
        if (estelamKhanevade.getNahveheyeDaryafteMostamari() != null && estelamKhanevade.getNahveheyeDaryafteMostamari().equals("sal")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.SAALAANEH;
        } else if (estelamKhanevade.getNahveheyeDaryafteMostamari() != null && estelamKhanevade.getNahveheyeDaryafteMostamari().equals("mah")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.MAAHAANEH;
        }

        NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahveKasrHaghBimePoosheshhayeEzafi = null;
        if (estelamKhanevade.getNahveyeKasreHagheBimePusheshhayeEzafi() == null || estelamKhanevade.getNahveyeKasreHagheBimePusheshhayeEzafi().equals("mazad")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE;
        } else if (estelamKhanevade.getNahveyeKasreHagheBimePusheshhayeEzafi() != null && estelamKhanevade.getNahveyeKasreHagheBimePusheshhayeEzafi().equals("az")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE;
        }

        if (estelamKhanevade.getNerkheAfzayesheHagheBime() == null) {
            estelamKhanevade.setNerkheAfzayesheHagheBime("0");
        }

        if (estelamKhanevade.getMablagheHagheBimeMonazam() == null) {
            estelamKhanevade.setMablagheHagheBimeMonazam("0");
        }

        if (estelamKhanevade.getMablagheHagheBimeAvalie() == null) {
            estelamKhanevade.setMablagheHagheBimeAvalie("0");
        }

        if (estelamKhanevade.getNerkhAfzayesheSalaneMostamari() == null) {
            estelamKhanevade.setNerkhAfzayesheSalaneMostamari("0");
        }

        if (estelamKhanevade.getModatZamaneDaryafteMostamari() == null) {
            estelamKhanevade.setModatZamaneDaryafteMostamari("0");
        }

//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelamKhanevade.getDarsadeTakhfifeKarmozdKarmozd() == null) {
            estelamKhanevade.setDarsadeTakhfifeKarmozdKarmozd("0");
        }

        if (estelamKhanevade.getDarsadeTakhfifeVosool() == null) {
            estelamKhanevade.setDarsadeTakhfifeVosool("0");
        }

        if (estelamKhanevade.getPoosheshMoafiatDarSoorateFoteMoghadam() == null || estelamKhanevade.getPoosheshMoafiatDarSoorateFoteMoghadam().equals("yes")) {
            pooshesh_moafiat_dar_soorate_fote_moghadam = true;
        } else {
            pooshesh_moafiat_dar_soorate_fote_moghadam = false;
        }

        double bimeGari = 0.0;
        double edari = 0.0;
        double karmozdAzMahaleHagheBime = 0.0;
        double darsadeTakhfifeKarmozdKarmozd = Double.parseDouble(estelamKhanevade.getDarsadeTakhfifeKarmozdKarmozd());
        double darsadeTakhfifeVosool = Double.parseDouble(estelamKhanevade.getDarsadeTakhfifeVosool());
        Tarh tarh = estelamKhanevade.getPishnehad() == null ? null : estelamKhanevade.getPishnehad().getTarh();

        BimeNaamehVaSamayehGozariMultiple bimeNaamehVaSamayehGozariMultiple = new BimeNaamehVaSamayehGozariMultiple();
        correctCalculation = true;
        try {
            this.fRsMultipleList = bimeNaamehVaSamayehGozariMultiple.finalReports(
                    estelamKhanevade,
                    nahve,
                    bimeGari,
                    edari,
                    karmozdAzMahaleHagheBime,
                    darsadeTakhfifeKarmozdKarmozd,
                    darsadeTakhfifeVosool,
                    nerkhBahreFanni,
                    hadeAksarSarFot,
                    hadeAksarSarmayeFotHadese,
                    hadeAksarAmraz,
                    nerkhPusheshZelzele,
                    darsadMaliat,
                    karmozdGyekja,
                    karmozdyekja,
                    bimegariGyekja,
                    bimegariyekja,
                    edariGyekja,
                    vosulGyekja,
                    lifeTable,
                    _date,
                    tarh,
                    nahveKasrHaghBimePoosheshhayeEzafi,
                    nahveDaryaft,
                    nahveDaryaftMostamari,
                    payeyeMohasebat,
                    isRegistered,
                    pooshesh_naghs_ozv_b,
                    pooshesh_fot_dar_asar_zelzele_b,
                    pooshesh_moafiat_b,
                    pooshesh_moafiat_dar_soorate_fote_moghadam
            );
        } catch (BimeNaamehVaSamayehGozariMultiple.CustomValidationException e) {
            correctCalculation = false;
        }
        if (pooshesh_naghs_ozv_b[0]) {
            naghsDarad = "true";
        }
        return SUCCESS;
    }

    public String reverseCalculate() {
//        boolean pooshesh_moafiat_b = false;
//        boolean pooshesh_fot_dar_asar_zelzele_b = false;
//        boolean pooshesh_naghs_ozv_b = false;
//        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double edariGyekja = AsnadeSodorService.getEdariGyekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double vosulGyekja = AsnadeSodorService.getVosulGyekja(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        double[][] lifeTable = AsnadeSodorService.getLifeTable(estelamReverse.getPishnehad().getBimename().getTarikhSodour(), estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        String _date = estelamReverse.getPishnehad().getBimename().getTarikhSodour();
//        naghsDarad = "false";
//        if (estelamReverse.getPooshesh_fot_dar_asar_haadese() != null && estelamReverse.getPooshesh_fot_dar_asar_haadese().equals("yes")) {
//
//        } else {
//            estelamReverse.setSarmaye_paye_fot_dar_asar_hadese("0");
//        }
//
//        if (estelamReverse.getPooshesh_naghs_ozv() != null && estelamReverse.getPooshesh_naghs_ozv().equals("yes")) {
//            pooshesh_naghs_ozv_b = true;
//        } else {
//            pooshesh_naghs_ozv_b = false;
//            estelamReverse.setSarmaye_pooshesh_naghs_ozv("0");
//        }
//        if (estelamReverse.getPooshesh_fot_dar_asar_zelzele() != null && estelamReverse.getPooshesh_fot_dar_asar_zelzele().equals("yes")) {
//            pooshesh_fot_dar_asar_zelzele_b = true;
//        } else {
//            pooshesh_fot_dar_asar_zelzele_b = false;
//        }
//        if (estelamReverse.getPooshesh_amraz_khas() != null && estelamReverse.getPooshesh_amraz_khas().equals("yes")) {
//        } else {
//            estelamReverse.setSarmaye_pooshesh_amraz_khas("0");
//        }
//        if (estelamReverse.getPooshesh_moafiat() != null && estelamReverse.getPooshesh_moafiat().equals("yes")) {
//            pooshesh_moafiat_b = true;
//        } else {
//            pooshesh_moafiat_b = false;
//        }
//        NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME nahve = null;
//        if (estelamReverse.getNahve_pardakht_hagh_bime() == null || estelamReverse.getNahve_pardakht_hagh_bime().equals("yekja")) {
//            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
//        } else if (estelamReverse.getNahve_pardakht_hagh_bime().equals("mah")) {
//            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
//        } else if (estelamReverse.getNahve_pardakht_hagh_bime().equals("3mah")) {
//            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
//        } else if (estelamReverse.getNahve_pardakht_hagh_bime().equals("6mah")) {
//            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
//        } else if (estelamReverse.getNahve_pardakht_hagh_bime().equals("sal")) {
//            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
//        }
//        NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahveDaryaft = null;
//        if (estelamReverse.getNahveye_daryafte_andukhte_entehaye_modat_bimename() != null) {
//            if (estelamReverse.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("yekja")) {
//                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.YEKJA;
//            } else if (estelamReverse.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_modat")) {
//                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MODAT_MOAYAN;
//            } else if (estelamReverse.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_omr")) {
//                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MADAMOL_OMR;
//            }
//        }
//        NSPConstants.NAHVE_DARYAAFT_MOSTAMERI nahveDaryaftMostamari = null;
//        if (estelamReverse.getNahve_daryaft_mostamari() != null && estelamReverse.getNahve_daryaft_mostamari().equals("sal")) {
//            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.SAALAANEH;
//        } else if (estelamReverse.getNahve_daryaft_mostamari() != null && estelamReverse.getNahve_daryaft_mostamari().equals("mah")) {
//            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.MAAHAANEH;
//        }
//
//        if (estelamReverse.getSen_bime_shode() == null) estelamReverse.setSen_bime_shode("0");
//        if (estelamReverse.getDarsad_ezafe_nerkh_pezeshki() == null) estelamReverse.setDarsad_ezafe_nerkh_pezeshki("0");
//        if (estelamReverse.getSarmaye_paye_fot_dar_asar_hadese() == null)
//            estelamReverse.setSarmaye_paye_fot_dar_asar_hadese("0");
//        if (estelamReverse.getSarmaye_paye_fot() == null) estelamReverse.setSarmaye_paye_fot("0");
//        if (estelamReverse.getSarmaye_pooshesh_amraz_khas() == null) estelamReverse.setSarmaye_pooshesh_amraz_khas("0");
//        if (estelamReverse.getSarmaye_pooshesh_naghs_ozv() == null) estelamReverse.setSarmaye_pooshesh_naghs_ozv("0");
//        if (estelamReverse.getNerkh_afzayesh_salaneh_hagh_bime() == null)
//            estelamReverse.setNerkh_afzayesh_salaneh_hagh_bime("0");
//        if (estelamReverse.getNerkh_afzayesh_salaneh_sarmaye_fot() == null)
//            estelamReverse.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
//        if (estelamReverse.getAndukhte_entehaye_modate_bimename() == null)
//            estelamReverse.setAndukhte_entehaye_modate_bimename("0");
//        if (estelamReverse.getMablagh_seporde_ebtedaye_sal() == null)
//            estelamReverse.setMablagh_seporde_ebtedaye_sal("0");
//        if (estelamReverse.getNerkh_afzayesh_salaneh_mostamari() == null)
//            estelamReverse.setNerkh_afzayesh_salaneh_mostamari("0");
//        if (estelamReverse.getModdat_zaman_daryaft_mostamari() == null)
//            estelamReverse.setModdat_zaman_daryaft_mostamari("0");
//        if (estelamReverse.getTabaghe_khatar() == null || estelamReverse.getTabaghe_khatar().equalsIgnoreCase("null"))
//            estelamReverse.setTabaghe_khatar("1");
////        if(estelamReverse.getDarsad_takhfif_bimegari() == null) estelamReverse.setDarsad_takhfif_bimegari("0");
////        if(estelamReverse.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
////        if(estelamReverse.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
//        if (estelamReverse.getDarsad_takhfif_karmozd_karmozd() == null)
//            estelamReverse.setDarsad_takhfif_karmozd_karmozd("0");
//        if (estelamReverse.getDarsad_takhfif_vosool() == null) estelamReverse.setDarsad_takhfif_vosool("0");
//
//        BimeNaamehVaSarmayehGozari bimeNaamehVaSarmayehGozari = new BimeNaamehVaSarmayehGozari();
//        correctCalculation = true;
//        try {
//            fRsList = bimeNaamehVaSarmayehGozari.finalReportsReverse(
//                    //Sene_Bime_Shode,Darsad_Ezafe_Nerkh_Pezeshki,Modat_Bime_Naameh,
//                    Short.parseShort(estelamReverse.getSen_bime_shode()), Double.parseDouble(estelamReverse.getDarsad_ezafe_nerkh_pezeshki()), Short.parseShort(estelamReverse.getModat_bimename()),
//                    //Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
//                    Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getSarmaye_paye_fot())), Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getSarmaye_paye_fot_dar_asar_hadese())), Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getSarmaye_pooshesh_amraz_khas())),
//                    //pushesh_Moaafiyat, Pushesh_Naghs_Ozv,  Sarmaye_Pushesh_Naghs_Ozv,
//                    pooshesh_moafiat_b, pooshesh_naghs_ozv_b, Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getSarmaye_pooshesh_naghs_ozv()))
//                    //Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,   Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,   Nahve_Pardaakht_Hagh_Bime,
//                    , Double.parseDouble(estelamReverse.getNerkh_afzayesh_salaneh_hagh_bime()), Double.parseDouble(estelamReverse.getNerkh_afzayesh_salaneh_sarmaye_fot()), nahve,
//                    //Hagh_Bime_Pardaakhti_Rial,  Mablagh_Seporde_Ebtedaye_Saal_Rial,   Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
//                    Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getAndukhte_entehaye_modate_bimename())), Long.parseLong(StringUtil.removeNoneDigits(estelamReverse.getMablagh_seporde_ebtedaye_sal())), nahveDaryaft,
//                    //Nerkh_Afzayesh_Saalaaneh_Mostamari,  Nahve_Daryaft_Mostamri,      Modat_Zaman_Daryaft_Mostamari,
//                    Double.parseDouble(estelamReverse.getNerkh_afzayesh_salaneh_mostamari()), nahveDaryaftMostamari, Short.parseShort(estelamReverse.getModdat_zaman_daryaft_mostamari()),
//                    //Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari,  Edari,
//                    Byte.parseByte(estelamReverse.getTabaghe_khatar()), pooshesh_fot_dar_asar_zelzele_b,
//                    0.0//Double.parseDouble(estelamReverse.getDarsad_takhfif_bimegari()),
//                    , 0.0// Double.parseDouble(estelamReverse.getDarsad_takhfif_edari()),
//                    //Kaarmozd_Az_Mahal_Hagh_Bimeh,  Kaarmozd_Az_Mahal_Kaarmozd,  Hazineh_Vosul
//                    , 0.0//Double.parseDouble(estelamReverse.getDarsad_takhfif_karmozd_hagh())
//                    , Double.parseDouble(estelamReverse.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelamReverse.getDarsad_takhfif_vosool())
//                    , nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, darsadMaliat, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja,
//                    lifeTable, _date, estelamReverse.getPishnehad() == null ? null : estelamReverse.getPishnehad().getTarh());
//        } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
//            correctCalculation = false;
//            e.printStackTrace();
//        }
//        if (correctCalculation) {
//            fRsList.get(0).setMablaghHagheBimeSalane(fRsList.get(0).getHaghBimePardakhtiBase());
//            double sourat = nerkhBahreFanni;
//            double makhrajsourat = Math.pow((1 + nerkhBahreFanni), 0.5) - 1;
//            fRsList.get(0).setMablaghHagheBimeSheshMahe((((sourat / makhrajsourat) / 2) * fRsList.get(0).getMablaghHagheBimeSalane()) / 2);
//            makhrajsourat = Math.pow(1 + nerkhBahreFanni, 0.25) - 1;
//            fRsList.get(0).setMablaghHagheBimeSeMahe((((sourat / makhrajsourat) / 4) * fRsList.get(0).getMablaghHagheBimeSalane()) / 4);
//            makhrajsourat = Math.pow(1 + nerkhBahreFanni, 0.0833333333333333) - 1;
//            fRsList.get(0).setMablaghHagheBimeMahane((((sourat / makhrajsourat) / 12) * fRsList.get(0).getMablaghHagheBimeSalane()) / 12);
//            if (pooshesh_naghs_ozv_b) naghsDarad = "true";
//        }
////        fRsList.get(0).setMablaghHagheBimeSheshMahe(fRsList.get(0).getMablaghHagheBimeSalane());
        return SUCCESS;
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
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equals("undefined"))
            estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equals("undefined"))
            estelam.setTabaghe_khatar_naghsozv("1");
//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

    }

    public String prepareForEstelam(){
        tarhs=constantsService.listAllTarhs();
        validationConstants = AsnadeSodorService.getValidationConstantsSet(null, DateUtil.getCurrentDate());
        setInEstelam(true);
        return SUCCESS;
    }


    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public UserComment getUserComment() {
        return userComment;
    }

    public void setUserComment(UserComment userComment) {
        this.userComment = userComment;
    }

    public PishnehadConstants getPishnehadConstants() {
        return pishnehadConstants;
    }

    public void setPishnehadConstants(PishnehadConstants pishnehadConstants) {
        this.pishnehadConstants = pishnehadConstants;
    }

    public String getCodeNamayandegi() {
        return codeNamayandegi;
    }

    public void setCodeNamayandegi(String codeNamayandegi) {
        this.codeNamayandegi = codeNamayandegi;
    }

    public boolean isCorrectCalculation() {
        return correctCalculation;
    }

    public List<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<UserComment> userComments) {
        this.userComments = userComments;
    }

    public void setCorrectCalculation(boolean correctCalculation) {
        this.correctCalculation = correctCalculation;
    }

    public List<FRs> getFRsList() {
        return fRsList;
    }

    public void setFRsList(List<FRs> fRsList) {
        this.fRsList = fRsList;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public EstelamReverse getEstelamReverse() {
        return estelamReverse;
    }

    public void setEstelamReverse(EstelamReverse estelamReverse) {
        this.estelamReverse = estelamReverse;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getMablaghAvaliye() {
        return mablaghAvaliye;
    }

    public void setMablaghAvaliye(String mablaghAvaliye) {
        this.mablaghAvaliye = mablaghAvaliye;
    }

    public String getHaghBimeMonazam() {
        return haghBimeMonazam;
    }

    public void setHaghBimeMonazam(String haghBimeMonazam) {
        this.haghBimeMonazam = haghBimeMonazam;
    }

    public String getSarmayePayeFot() {
        return sarmayePayeFot;
    }

    public void setSarmayePayeFot(String sarmayePayeFot) {
        this.sarmayePayeFot = sarmayePayeFot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public SharayeteJadid getSharayeteJadid() {
        return sharayeteJadid;
    }

    public void setSharayeteJadid(SharayeteJadid sharayeteJadid) {
        this.sharayeteJadid = sharayeteJadid;
    }

    public Mostamari getMostamari() {
        return mostamari;
    }

    public void setMostamari(Mostamari mostamari) {
        this.mostamari = mostamari;
    }

    public List<MostamariFRs> getMostamariFRsList() {
        return mostamariFRsList;
    }

    public void setMostamariFRsList(List<MostamariFRs> mostamariFRsList) {
        this.mostamariFRsList = mostamariFRsList;
    }

    public String getCheckForMadam() {
        return checkForMadam;
    }

    public void setCheckForMadam(String checkForMadam) {
        this.checkForMadam = checkForMadam;
    }

    public String getZamanAsarElhaghie() {
        return zamanAsarElhaghie;
    }

    public void setZamanAsarElhaghie(String zamanAsarElhaghie) {
        this.zamanAsarElhaghie = zamanAsarElhaghie;
    }

    public EstelamKhanevade getEstelamKhanevade() {
        return estelamKhanevade;
    }

    public void setEstelamKhanevade(EstelamKhanevade estelamKhanevade) {
        this.estelamKhanevade = estelamKhanevade;
    }

    public List<FRsMultiple> getFRsMultipleList() {
        return fRsMultipleList;
    }

    public void setFRsMultipleList(List<FRsMultiple> fRsMultipleList) {
        this.fRsMultipleList = fRsMultipleList;
    }

    public static Double[] roundArray(Double[] array) {
        Double[] res = new Double[array.length];
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                res[i] = (double) Math.round(array[i]);
            }
            else{
                res[i] = 0.0;
            }
        }
        return res;
    }

    public static boolean hasManfiVal(Double[] vals) {
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i] != null && vals[i] < 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasManfiVal(Short[] vals) {
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i] != null && vals[i] < 0) {
                return true;
            }
        }
        return false;
    }

    public static String getOrdinalNumber(int num, boolean asliForAval) {
        if (num == 1) {
            return asliForAval ? "اصلي" : "اول";
        } else if (num == 2) {
            return "دوم";
        } else if (num == 3) {
            return "سوم";
        } else if (num == 4) {
            return "چهارم";
        }
        return "";
    }

    public HashMap<String, Object> getValidationConstants() {
        if(validationConstants == null || validationConstants.isEmpty()){
            AsnadeSodorService asnadeSodorService = new AsnadeSodorService();
            validationConstants = asnadeSodorService.getValidationConstantsSet(null, DateUtil.getCurrentDate());
        }
        return validationConstants;
    }

    public void setValidationConstants(HashMap<String, Object> validationConstants) {
        this.validationConstants = validationConstants;
    }

    public boolean isInEstelam() {
        return inEstelam;
    }

    public void setInEstelam(boolean inEstelam) {
        this.inEstelam = inEstelam;
    }
}
