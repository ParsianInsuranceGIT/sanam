package com.bitarts.parsian.model.estelam;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.calculations.MohasebateTeoriView;
import com.bitarts.parsian.viewModel.PoosheshEzafi;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_estelam")
public class Estelam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "estelam_id")
    private Integer id;
    @Column(name = "nam_bime_shode")
    private String nam_bime_shode;
    @Column(name = "tarikh_tavalod")
    private String tarikh_tavalod;
    @Column(name = "darsad_ezafe_ner_pez")
    private String darsad_ezafe_nerkh_pezeshki;
    @Column(name = "sen_bime_shode")
    private String sen_bime_shode;
    @Column(name = "sen_bimeie")
    private String seneBimeie;
    @Column(name = "sarmaye_paye_fot_long")
    private Long sarmaye_paye_fot_long;
    @Column(name = "nerkh_afza_sal_sar_fot")
    private String nerkh_afzayesh_salaneh_sarmaye_fot;
    @Column(name = "nahve_pardakht_hagh_bime")
    private String nahve_pardakht_hagh_bime;
    @Column(name = "nahve_kasr_hagh_eza")
    private String nahve_kasr_hagh_bime_poosheshhaye_ezafi;
    @Column(name = "hagh_bime_pardakhti")
    private String hagh_bime_pardakhti;
    @Column(name = "nerkh_afza_sal_hagh_bime")
    private String nerkh_afzayesh_salaneh_hagh_bime;
    @Column(name = "mablagh_sepor_ebted_sal")
    private String mablagh_seporde_ebtedaye_sal;
    @Column(name = "modat_bimename")
    private String modat_bimename;
    @Column(name = "nahve_daryaf_enteha_bime")
    private String nahveye_daryafte_andukhte_entehaye_modat_bimename;
    @Column(name = "nerkh_afza_sal_mostamar")
    private String nerkh_afzayesh_salaneh_mostamari;
    @Column(name = "nahve_daryaft_mostamari")
    private String nahve_daryaft_mostamari;
    @Column(name = "mdat_zamn_dryaf_mstamar")
    private String moddat_zaman_daryaft_mostamari;
    @Column(name = "tabaghe_khatar")
    private String tabaghe_khatar;
    @Column(name = "tabaghe_khatar_naghsozv")
    private String tabaghe_khatar_naghsozv;
    @Column(name = "poosh_fot_asar_hadese")
    private String pooshesh_fot_dar_asar_haadese;
    @Column(name = "poosh_fot_tar_shoru")
    private String pooshesh_fot_tarikh_shoru;
    @Column(name = "poosh_fot_tar_payan")
    private String pooshesh_fot_tarikh_payan;
    @Column(name = "sarm_fot_asar_hadese")
    private String sarmaye_paye_fot_dar_asar_hadese;
    @Column(name = "pooshesh_naghs_ozv")
    private String pooshesh_naghs_ozv;
    @Column(name = "poosh_naghs_tar_shoru")
    private String pooshesh_naghs_tarikh_shoru;
    @Column(name = "poosh_naghs_tar_payan")
    private String pooshesh_naghs_tarikh_payan;
    @Column(name = "sarm_poosh_naqs_ozv")
    private String sarmaye_pooshesh_naghs_ozv;
    @Column(name = "poosh_fot_asar_zel")
    private String pooshesh_fot_dar_asar_zelzele;
    @Column(name = "poosh_zel_tar_shoru")
    private String pooshesh_zelzele_tarikh_shoru;
    @Column(name = "poosh_zel_tar_payan")
    private String pooshesh_zelzele_tarikh_payan;
    @Column(name = "pooshesh_amraz_khas")
    private String pooshesh_amraz_khas;
    @Column(name = "poosh_amraz_tar_shoru")
    private String pooshesh_amraz_tarikh_shoru;
    @Column(name = "poosh_amraz_tar_payan")
    private String pooshesh_amraz_tarikh_payan;
    @Column(name = "joziyate_pooshesh_amraz_khas")
    private String joziyate_pooshesh_amraz_khas;
    @Column(name = "joziyate_pooshesh_moafiat")
    private String joziyate_pooshesh_moafiat;
    @Column(name = "sarm_poosh_khas")
    private String sarmaye_pooshesh_amraz_khas;
    @Column(name = "poosh_moafiat")
    private String pooshesh_moafiat;
    @Column(name = "noe_tarh")
    private String noe_tarh;
    @Column(name = "dars_takhfif_karmozd")
    private String darsad_takhfif_karmozd_karmozd;
    @Column(name = "dars_takhfif_vosool")
    private String darsad_takhfif_vosool;
//    @OneToOne(mappedBy = "estelam", fetch = FetchType.LAZY, optional = false)
//    private Pishnehad pishnehad;

    @OneToMany(mappedBy = "estelam", fetch = FetchType.LAZY)
    private List<Pishnehad> pishnehadList;

    @Column(name="insert_raeis_sarmaye_hadese")
    private boolean insertRaeisSarmayeHadese;

    public Estelam() {
              this.insertRaeisSarmayeHadese=false;
    }

    public Estelam(String nam_bime_shode, String tarikh_tavalod, String darsad_ezafe_nerkh_pezeshki, String sen_bime_shode, String seneBimeie, String sarmaye_paye_fot, String nerkh_afzayesh_salaneh_sarmaye_fot, String nahve_pardakht_hagh_bime, String hagh_bime_pardakhti, String nerkh_afzayesh_salaneh_hagh_bime, String mablagh_seporde_ebtedaye_sal, String modat_bimename, String nahveye_daryafte_andukhte_entehaye_modat_bimename, String nerkh_afzayesh_salaneh_mostamari, String nahve_daryaft_mostamari, String moddat_zaman_daryaft_mostamari, String tabaghe_khatar, String pooshesh_fot_dar_asar_haadese, String pooshesh_fot_tarikh_shoru, String pooshesh_fot_tarikh_payan, String sarmaye_paye_fot_dar_asar_hadese, String pooshesh_naghs_ozv, String pooshesh_naghs_tarikh_shoru, String pooshesh_naghs_tarikh_payan, String sarmaye_pooshesh_naghs_ozv, String pooshesh_fot_dar_asar_zelzele, String pooshesh_zelzele_tarikh_shoru, String pooshesh_zelzele_tarikh_payan, String pooshesh_amraz_khas, String pooshesh_amraz_tarikh_shoru, String pooshesh_amraz_tarikh_payan, String joziyate_pooshesh_amraz_khas, String joziyate_pooshesh_moafiat, String sarmaye_pooshesh_amraz_khas, String pooshesh_moafiat, String noe_tarh, String darsad_takhfif_karmozd_karmozd, String darsad_takhfif_vosool) {
        this.nam_bime_shode = nam_bime_shode;
        this.tarikh_tavalod = tarikh_tavalod;
        this.darsad_ezafe_nerkh_pezeshki = darsad_ezafe_nerkh_pezeshki;
        this.sen_bime_shode = sen_bime_shode;
        this.seneBimeie = seneBimeie;
        this.nerkh_afzayesh_salaneh_sarmaye_fot = nerkh_afzayesh_salaneh_sarmaye_fot;
        this.nahve_pardakht_hagh_bime = nahve_pardakht_hagh_bime;
        this.hagh_bime_pardakhti = hagh_bime_pardakhti;
        this.nerkh_afzayesh_salaneh_hagh_bime = nerkh_afzayesh_salaneh_hagh_bime;
        this.mablagh_seporde_ebtedaye_sal = mablagh_seporde_ebtedaye_sal;
        this.modat_bimename = modat_bimename;
        this.nahveye_daryafte_andukhte_entehaye_modat_bimename = nahveye_daryafte_andukhte_entehaye_modat_bimename;
        this.nerkh_afzayesh_salaneh_mostamari = nerkh_afzayesh_salaneh_mostamari;
        this.nahve_daryaft_mostamari = nahve_daryaft_mostamari;
        this.moddat_zaman_daryaft_mostamari = moddat_zaman_daryaft_mostamari;
        this.tabaghe_khatar = tabaghe_khatar;
        this.pooshesh_fot_dar_asar_haadese = pooshesh_fot_dar_asar_haadese;
        this.pooshesh_fot_tarikh_shoru = pooshesh_fot_tarikh_shoru;
        this.pooshesh_fot_tarikh_payan = pooshesh_fot_tarikh_payan;
        this.sarmaye_paye_fot_dar_asar_hadese = sarmaye_paye_fot_dar_asar_hadese;
        this.pooshesh_naghs_ozv = pooshesh_naghs_ozv;
        this.pooshesh_naghs_tarikh_shoru = pooshesh_naghs_tarikh_shoru;
        this.pooshesh_naghs_tarikh_payan = pooshesh_naghs_tarikh_payan;
        this.sarmaye_pooshesh_naghs_ozv = sarmaye_pooshesh_naghs_ozv;
        this.pooshesh_fot_dar_asar_zelzele = pooshesh_fot_dar_asar_zelzele;
        this.pooshesh_zelzele_tarikh_shoru = pooshesh_zelzele_tarikh_shoru;
        this.pooshesh_zelzele_tarikh_payan = pooshesh_zelzele_tarikh_payan;
        this.pooshesh_amraz_khas = pooshesh_amraz_khas;
        this.pooshesh_amraz_tarikh_shoru = pooshesh_amraz_tarikh_shoru;
        this.pooshesh_amraz_tarikh_payan = pooshesh_amraz_tarikh_payan;
        this.joziyate_pooshesh_amraz_khas = joziyate_pooshesh_amraz_khas;
        this.joziyate_pooshesh_moafiat = joziyate_pooshesh_moafiat;
        this.sarmaye_pooshesh_amraz_khas = sarmaye_pooshesh_amraz_khas;
        this.pooshesh_moafiat = pooshesh_moafiat;
        this.noe_tarh = noe_tarh;
        this.darsad_takhfif_karmozd_karmozd = darsad_takhfif_karmozd_karmozd;
        this.darsad_takhfif_vosool = darsad_takhfif_vosool;
    }

    public boolean isInsertRaeisSarmayeHadese()
    {
        return insertRaeisSarmayeHadese;
    }

    public void setInsertRaeisSarmayeHadese(boolean insertRaeisSarmayeHadese)
    {
        this.insertRaeisSarmayeHadese = insertRaeisSarmayeHadese;
    }

    public Long getSarmaye_paye_fot_long()
    {
        return sarmaye_paye_fot_long;
    }

    public void setSarmaye_paye_fot_long(Long sarmaye_paye_fot_long)
    {
        this.sarmaye_paye_fot_long = sarmaye_paye_fot_long;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNam_bime_shode() {
        return nam_bime_shode;
    }

    public void setNam_bime_shode(String nam_bime_shode) {
        this.nam_bime_shode = nam_bime_shode;
    }

    public String getTarikh_tavalod() {
        return tarikh_tavalod;
    }

    public void setTarikh_tavalod(String tarikh_tavalod) {
        this.tarikh_tavalod = tarikh_tavalod;
    }

    public String getDarsad_ezafe_nerkh_pezeshki() {
        return darsad_ezafe_nerkh_pezeshki;
    }

    public void setDarsad_ezafe_nerkh_pezeshki(String darsad_ezafe_nerkh_pezeshki) {
        this.darsad_ezafe_nerkh_pezeshki = darsad_ezafe_nerkh_pezeshki;
    }

    public String getSen_bime_shode() {
        return sen_bime_shode;
    }

    public void setSen_bime_shode(String sen_bime_shode) {
        this.sen_bime_shode = sen_bime_shode;
    }

    public String getSarmaye_paye_fot() {
        if (sarmaye_paye_fot_long == null)
            return "0";
        return NumberFormat.getInstance().format(sarmaye_paye_fot_long) ;
    }

    public void setSarmaye_paye_fot(String sarmaye_paye_fot) {
        if(sarmaye_paye_fot==null || sarmaye_paye_fot.equals(""))
            this.sarmaye_paye_fot_long=null;
        else
        this.sarmaye_paye_fot_long = Long.parseLong(sarmaye_paye_fot.replaceAll(",","").trim());
    }

    public String getNerkh_afzayesh_salaneh_sarmaye_fot() {
        return nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public void setNerkh_afzayesh_salaneh_sarmaye_fot(String nerkh_afzayesh_salaneh_sarmaye_fot) {
        this.nerkh_afzayesh_salaneh_sarmaye_fot = nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public String getNahve_pardakht_hagh_bime() {
        return nahve_pardakht_hagh_bime;
    }

    public void setNahve_pardakht_hagh_bime(String nahve_pardakht_hagh_bime) {
        if(nahve_pardakht_hagh_bime==null)
        {
            final org.slf4j.Logger logger = LoggerFactory.getLogger(Estelam.class);
            logger.info(String.format("nahve_pardakht_hagh_bime estelame "+this.getId()+" dar tarikhe "+ DateUtil.getCurrentDate()+" va dar saate "+DateUtil.getCurrentTime()+" null shode ast"));
        }
        this.nahve_pardakht_hagh_bime = nahve_pardakht_hagh_bime;
    }

    public String getHagh_bime_pardakhti() {
        if(hagh_bime_pardakhti == null)
            return "0";
        return hagh_bime_pardakhti;
    }

    public void setHagh_bime_pardakhti(String hagh_bime_pardakhti) {
        this.hagh_bime_pardakhti = hagh_bime_pardakhti;
    }

    public String getNerkh_afzayesh_salaneh_hagh_bime() {
        if (nerkh_afzayesh_salaneh_hagh_bime==null) return "0";
        return nerkh_afzayesh_salaneh_hagh_bime;
    }

    public void setNerkh_afzayesh_salaneh_hagh_bime(String nerkh_afzayesh_salaneh_hagh_bime) {
        this.nerkh_afzayesh_salaneh_hagh_bime = nerkh_afzayesh_salaneh_hagh_bime;
    }

    public String getMablagh_seporde_ebtedaye_sal() {
        if(mablagh_seporde_ebtedaye_sal == null || mablagh_seporde_ebtedaye_sal.length() == 0) return "0";
        return mablagh_seporde_ebtedaye_sal;
    }

    public void setMablagh_seporde_ebtedaye_sal(String mablagh_seporde_ebtedaye_sal) {
        this.mablagh_seporde_ebtedaye_sal = mablagh_seporde_ebtedaye_sal;
    }

    public String getModat_bimename() {
        return modat_bimename;
    }

    public void setModat_bimename(String modat_bimename) {
        this.modat_bimename = modat_bimename;
    }

    public String getNahveye_daryafte_andukhte_entehaye_modat_bimename() {
        return nahveye_daryafte_andukhte_entehaye_modat_bimename;
    }

    public void setNahveye_daryafte_andukhte_entehaye_modat_bimename(String nahveye_daryafte_andukhte_entehaye_modat_bimename) {
        this.nahveye_daryafte_andukhte_entehaye_modat_bimename = nahveye_daryafte_andukhte_entehaye_modat_bimename;
    }

    public String getNerkh_afzayesh_salaneh_mostamari() {
        return nerkh_afzayesh_salaneh_mostamari;
    }

    public void setNerkh_afzayesh_salaneh_mostamari(String nerkh_afzayesh_salaneh_mostamari) {
        this.nerkh_afzayesh_salaneh_mostamari = nerkh_afzayesh_salaneh_mostamari;
    }

    public String getNahve_daryaft_mostamari() {
        return nahve_daryaft_mostamari;
    }

    public void setNahve_daryaft_mostamari(String nahve_daryaft_mostamari) {
        this.nahve_daryaft_mostamari = nahve_daryaft_mostamari;
    }

    public String getModdat_zaman_daryaft_mostamari() {
        return moddat_zaman_daryaft_mostamari;
    }

    public void setModdat_zaman_daryaft_mostamari(String moddat_zaman_daryaft_mostamari) {
        this.moddat_zaman_daryaft_mostamari = moddat_zaman_daryaft_mostamari;
    }

    public String getTabaghe_khatar() {
        return tabaghe_khatar;
    }

    public void setTabaghe_khatar(String tabaghe_khatar) {
        this.tabaghe_khatar = tabaghe_khatar;
    }

    public String getPooshesh_fot_dar_asar_haadese() {
        return pooshesh_fot_dar_asar_haadese;
    }

    public String getPooshesh_fot_dar_asar_haadeseFarsi() {
        if(getPooshesh_fot_dar_asar_haadese() == null || getPooshesh_fot_dar_asar_haadese().equals("no"))
            return "ندارد";
        else
            return "دارد";
    }

    public void setPooshesh_fot_dar_asar_haadese(String pooshesh_fot_dar_asar_haadese) {
        this.pooshesh_fot_dar_asar_haadese = pooshesh_fot_dar_asar_haadese;
    }

    public String getSarmaye_paye_fot_dar_asar_hadese() {
        if (sarmaye_paye_fot_dar_asar_hadese == null) return "0";
        return sarmaye_paye_fot_dar_asar_hadese;
    }

    public void setSarmaye_paye_fot_dar_asar_hadese(String sarmaye_paye_fot_dar_asar_hadese) {
        this.sarmaye_paye_fot_dar_asar_hadese = sarmaye_paye_fot_dar_asar_hadese;
    }

    public String getPooshesh_naghs_ozv() {
        return pooshesh_naghs_ozv;
    }

    public String getPooshesh_naghs_ozvFarsi() {
        if(getPooshesh_naghs_ozv() == null || getPooshesh_naghs_ozv().equals("no"))
            return "ندارد";
        else
            return "دارد";
    }

    public void setPooshesh_naghs_ozv(String pooshesh_naghs_ozv) {
        this.pooshesh_naghs_ozv = pooshesh_naghs_ozv;
    }

    public String getSarmaye_pooshesh_naghs_ozv() {
        if (sarmaye_pooshesh_naghs_ozv == null) return "0";
        return sarmaye_pooshesh_naghs_ozv;
    }

    public void setSarmaye_pooshesh_naghs_ozv(String sarmaye_pooshesh_naghs_ozv) {
        this.sarmaye_pooshesh_naghs_ozv = sarmaye_pooshesh_naghs_ozv;
    }

    public String getPooshesh_fot_dar_asar_zelzele() {
        if (pooshesh_fot_dar_asar_zelzele == null) return "0";
        return pooshesh_fot_dar_asar_zelzele;
    }

    public void setPooshesh_fot_dar_asar_zelzele(String pooshesh_fot_dar_asar_zelzele) {
        this.pooshesh_fot_dar_asar_zelzele = pooshesh_fot_dar_asar_zelzele;
    }

    public String getPooshesh_amraz_khas() {
        return pooshesh_amraz_khas;
    }

    public String getPooshesh_amraz_khasFarsi() {
        if(getPooshesh_amraz_khas() == null || getPooshesh_amraz_khas().equals("no"))
            return "ندارد";
        else
            return "دارد";
    }

    public void setPooshesh_amraz_khas(String pooshesh_amraz_khas) {

        this.pooshesh_amraz_khas = pooshesh_amraz_khas;
    }

    public String getSarmaye_pooshesh_amraz_khas() {
        if (sarmaye_pooshesh_amraz_khas == null) return "0";
        return sarmaye_pooshesh_amraz_khas;
    }

    public void setSarmaye_pooshesh_amraz_khas(String sarmaye_pooshesh_amraz_khas) {
        this.sarmaye_pooshesh_amraz_khas = sarmaye_pooshesh_amraz_khas;
    }

    public String getPooshesh_moafiat() {
        return pooshesh_moafiat;
    }

    public String getPooshesh_moafiatFarsi() {
        if(getPooshesh_moafiat() == null || getPooshesh_moafiat().equals("no"))
            return "ندارد";
        else
            return "دارد";
    }

    public void setPooshesh_moafiat(String pooshesh_moafiat) {
        this.pooshesh_moafiat = pooshesh_moafiat;
    }

    public String getNoe_tarh() {
        return noe_tarh;
    }

    public void setNoe_tarh(String noe_tarh) {
        this.noe_tarh = noe_tarh;
    }

    public String getDarsad_takhfif_karmozd_karmozd() {
        return darsad_takhfif_karmozd_karmozd;
    }

    public void setDarsad_takhfif_karmozd_karmozd(String darsad_takhfif_karmozd_karmozd) {
        this.darsad_takhfif_karmozd_karmozd = darsad_takhfif_karmozd_karmozd;
    }

    public String getDarsad_takhfif_vosool() {
        return darsad_takhfif_vosool;
    }

    public void setDarsad_takhfif_vosool(String darsad_takhfif_vosool) {
        this.darsad_takhfif_vosool = darsad_takhfif_vosool;
    }

    public Pishnehad getPishnehad() {
        if(pishnehadList == null) return null;
        if(pishnehadList.size() == 0)
            return null;
        else
            return pishnehadList.get(0);
    }

    public void setPishnehad(Pishnehad pishnehad) {
        ArrayList<Pishnehad> p = new ArrayList<Pishnehad>();
        p.add(pishnehad);
        setPishnehadList(p);
    }

    public String getSeneBimeie() {
        if(seneBimeie == null || seneBimeie.isEmpty() || seneBimeie.equals("0")) return sen_bime_shode;
        return seneBimeie;
    }

    public void setSeneBimeie(String seneBimeie) {
        this.seneBimeie = seneBimeie;
    }

    public String getJoziyate_pooshesh_amraz_khas() {
        return joziyate_pooshesh_amraz_khas;
    }

    public void setJoziyate_pooshesh_amraz_khas(String joziyate_pooshesh_amraz_khas) {
        this.joziyate_pooshesh_amraz_khas = joziyate_pooshesh_amraz_khas;
    }

    public String getJoziyate_pooshesh_moafiat() {
        return joziyate_pooshesh_moafiat;
    }

    public void setJoziyate_pooshesh_moafiat(String joziyate_pooshesh_moafiat) {
        this.joziyate_pooshesh_moafiat = joziyate_pooshesh_moafiat;
    }

    public String getPooshesh_fot_tarikh_shoru() {
        return pooshesh_fot_tarikh_shoru;
    }

    public void setPooshesh_fot_tarikh_shoru(String pooshesh_fot_tarikh_shoru) {
        this.pooshesh_fot_tarikh_shoru = pooshesh_fot_tarikh_shoru;
    }

    public String getPooshesh_fot_tarikh_payan() {
        return pooshesh_fot_tarikh_payan;
    }

    public void setPooshesh_fot_tarikh_payan(String pooshesh_fot_tarikh_payan) {
        this.pooshesh_fot_tarikh_payan = pooshesh_fot_tarikh_payan;
    }

    public String getPooshesh_naghs_tarikh_shoru() {
        return pooshesh_naghs_tarikh_shoru;
    }

    public void setPooshesh_naghs_tarikh_shoru(String pooshesh_naghs_tarikh_shoru) {
        this.pooshesh_naghs_tarikh_shoru = pooshesh_naghs_tarikh_shoru;
    }

    public String getPooshesh_naghs_tarikh_payan() {
        return pooshesh_naghs_tarikh_payan;
    }

    public void setPooshesh_naghs_tarikh_payan(String pooshesh_naghs_tarikh_payan) {
        this.pooshesh_naghs_tarikh_payan = pooshesh_naghs_tarikh_payan;
    }

    public String getPooshesh_zelzele_tarikh_shoru() {
        return pooshesh_zelzele_tarikh_shoru;
    }

    public void setPooshesh_zelzele_tarikh_shoru(String pooshesh_zelzele_tarikh_shoru) {
        this.pooshesh_zelzele_tarikh_shoru = pooshesh_zelzele_tarikh_shoru;
    }

    public String getPooshesh_zelzele_tarikh_payan() {
        return pooshesh_zelzele_tarikh_payan;
    }

    public void setPooshesh_zelzele_tarikh_payan(String pooshesh_zelzele_tarikh_payan) {
        this.pooshesh_zelzele_tarikh_payan = pooshesh_zelzele_tarikh_payan;
    }

    public String getPooshesh_amraz_tarikh_shoru() {
        return pooshesh_amraz_tarikh_shoru;
    }

    public void setPooshesh_amraz_tarikh_shoru(String pooshesh_amraz_tarikh_shoru) {
        this.pooshesh_amraz_tarikh_shoru = pooshesh_amraz_tarikh_shoru;
    }

    public String getPooshesh_amraz_tarikh_payan() {
        return pooshesh_amraz_tarikh_payan;
    }

    public void setPooshesh_amraz_tarikh_payan(String pooshesh_amraz_tarikh_payan) {
        this.pooshesh_amraz_tarikh_payan = pooshesh_amraz_tarikh_payan;
    }

    public String getNahvePardakhtHaghBimeFarsi() {
        String s = "";
        if (nahve_pardakht_hagh_bime != null) {
            if (nahve_pardakht_hagh_bime.equals("mah")) s = "ماهانه";
            else if (nahve_pardakht_hagh_bime.equals("3mah")) s = "سه ماهه";
            else if (nahve_pardakht_hagh_bime.equals("6mah")) s = "شش ماهه";
            else if (nahve_pardakht_hagh_bime.equals("sal")) s = "سالانه";
            else if (nahve_pardakht_hagh_bime.equals("yekja")) s = "یکجا";
        }
        return s;
    }


    public String getNahve_kasr_hagh_bime_poosheshhaye_ezafi() {
        if(nahve_kasr_hagh_bime_poosheshhaye_ezafi == null) return "mazad";
        return nahve_kasr_hagh_bime_poosheshhaye_ezafi;
    }

    public boolean isYekja() {
        return nahve_pardakht_hagh_bime.toLowerCase().equals("yekja");
    }

    public String getNahve_kasr_hagh_bime_poosheshhaye_ezafiFarsi() {
        if(getNahve_kasr_hagh_bime_poosheshhaye_ezafi() == null || getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equalsIgnoreCase("MAZAD"))
            return "مازاد بر حق بیمه";
        else
            return "از حق بیمه پایه";
    }

    public void setNahve_kasr_hagh_bime_poosheshhaye_ezafi(String nahve_kasr_hagh_bime_poosheshhaye_ezafi) {
        this.nahve_kasr_hagh_bime_poosheshhaye_ezafi = nahve_kasr_hagh_bime_poosheshhaye_ezafi;
    }

    public String getTabaghe_khatar_naghsozv() {
        return tabaghe_khatar_naghsozv;
    }

    public void setTabaghe_khatar_naghsozv(String tabaghe_khatar_naghsozv) {
        this.tabaghe_khatar_naghsozv = tabaghe_khatar_naghsozv;
    }

    public List<PoosheshEzafi> getPoosheshhayeEzafiList() {
        LinkedList<PoosheshEzafi> returnValue = new LinkedList<PoosheshEzafi>();
        if(pooshesh_fot_dar_asar_haadese != null && pooshesh_fot_dar_asar_haadese.equals("yes")) {
            PoosheshEzafi poosheshHadese = new PoosheshEzafi();
            poosheshHadese.setNoePooshesh("فوت در اثر حادثه");
            poosheshHadese.setSarmaye(getSarmaye_paye_fot_dar_asar_hadese());
            poosheshHadese.setHaghBime(getPishnehad().getBimename().getGhestBandiList().get(0).getHaghBimePoosheshHadese());
            returnValue.add(poosheshHadese);
        }
        if(pooshesh_amraz_khas != null && pooshesh_amraz_khas.equals("yes")) {
            PoosheshEzafi poosheshAmraz = new PoosheshEzafi();
            poosheshAmraz.setNoePooshesh("امراض خاص");
            poosheshAmraz.setSarmaye(getSarmaye_pooshesh_amraz_khas());
            poosheshAmraz.setHaghBime(getPishnehad().getBimename().getGhestBandiList().get(0).getHaghBimePoosheshAmraz());
            returnValue.add(poosheshAmraz);
        }
        if(pooshesh_moafiat != null && pooshesh_moafiat.equals("yes")) {
            PoosheshEzafi poosheshMoafiat = new PoosheshEzafi();
            poosheshMoafiat.setNoePooshesh("معافیت از پرداخت هزینه ها در صورت از کار افتادگی");
            poosheshMoafiat.setSarmaye("");
            poosheshMoafiat.setHaghBime(getPishnehad().getBimename().getGhestBandiList().get(0).getHaghBimePoosheshMoafiat());
            returnValue.add(poosheshMoafiat);
        }
        if(pooshesh_naghs_ozv != null && pooshesh_naghs_ozv.equals("yes")) {
            PoosheshEzafi poosheshNaghs = new PoosheshEzafi();
            poosheshNaghs.setNoePooshesh("نقص عضو یا از کار افتادگی دائم ناشی از حادثه");
            poosheshNaghs.setSarmaye(getSarmaye_pooshesh_naghs_ozv());
            poosheshNaghs.setHaghBime(getPishnehad().getBimename().getGhestBandiList().get(0).getHaghBimePoosheshNaghsOzv());
            returnValue.add(poosheshNaghs);
        }
        return returnValue;
    }

    public String getMablagheGhestSaleAvval() {
        if(nahve_pardakht_hagh_bime.equalsIgnoreCase("yekja")) return "0";
        return getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().get(0).getCredebit().getAmount();
    }

     public String getMablagheGhestSaleAvvalBeduneSarmayeAval() {
         if(nahve_pardakht_hagh_bime.equalsIgnoreCase("yekja")) return "0";
         int whichGhest = getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().size() / 2;
         if(mablagh_seporde_ebtedaye_sal!=null)
         {
             long sepordeEbtedayi=Long.parseLong(mablagh_seporde_ebtedaye_sal.replaceAll(",","").trim());
             long finalVal=getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().get(whichGhest).getCredebit().getAmount_long()-sepordeEbtedayi;
             return NumberFormat.getInstance().format(finalVal);
         }
         return getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().get(whichGhest).getCredebit().getAmount();
     }

    public String getMablagheGhestSaleAvvalBeduneSarmayeAval1()
    {
        if (nahve_pardakht_hagh_bime.equalsIgnoreCase("yekja")) return "0";
        if (mablagh_seporde_ebtedaye_sal != null)
        {
            long sepordeEbtedayi = Long.parseLong(mablagh_seporde_ebtedaye_sal.replaceAll(",", "").trim());
            long finalVal = getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().get(0).getCredebit().getAmount_long() - sepordeEbtedayi;
            return NumberFormat.getInstance().format(finalVal);
        }
        return getPishnehad().getBimename().getGhestBandiList().get(0).getGhestList().get(0).getCredebit().getAmount();
    }

    public int getPeriodCount() {
        if (this.getNahve_pardakht_hagh_bime().equalsIgnoreCase("mah")) {
            return MohasebateTeoriView.PERIOD_COUNT_IN_MAHANEH;
        } else if (this.getNahve_pardakht_hagh_bime().equalsIgnoreCase("3mah")) {
            return MohasebateTeoriView.PERIOD_COUNT_IN_3MAHE;
        } else if (this.getNahve_pardakht_hagh_bime().equalsIgnoreCase("6mah")) {
            return MohasebateTeoriView.PERIOD_COUNT_IN_6MAHE;
        } else {
            return MohasebateTeoriView.PERIOD_COUNT_IN_SAALANEH;
        }
    }

    public List<Pishnehad> getPishnehadList() {
        return pishnehadList;
    }

    public void setPishnehadList(List<Pishnehad> pishnehadList) {
        this.pishnehadList = pishnehadList;
    }
}
