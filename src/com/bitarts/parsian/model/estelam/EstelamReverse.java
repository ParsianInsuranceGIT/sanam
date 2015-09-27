package com.bitarts.parsian.model.estelam;

import com.bitarts.parsian.model.pishnahad.Pishnehad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@Entity
@Table(name = "tbl_estelam")
public class EstelamReverse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "estelam_id")
    private Integer id;
    @Column(name="nam_bime_shode")
    private String nam_bime_shode;
    @Column (name="tarikh_tavalod")
    private String tarikh_tavalod;
    @Column(name="darsad_ezafe_ner_pez")
    private String darsad_ezafe_nerkh_pezeshki;
    @Column(name="sen_bime_shode")
    private String sen_bime_shode;
    @Column(name="sarmaye_paye_fot")
    private String sarmaye_paye_fot;
    @Column(name="nerkh_afza_sal_sar_fot")
    private String nerkh_afzayesh_salaneh_sarmaye_fot;
    @Column(name="nahve_pardakht_hagh_bime")
    private String nahve_pardakht_hagh_bime;
    @Column(name="andukhte_ent_mod_bimename")
    private String andukhte_entehaye_modate_bimename;
    @Column(name="nerkh_afza_sal_hagh_bime")
    private String nerkh_afzayesh_salaneh_hagh_bime;
    @Column(name="mablagh_sepor_ebted_sal")
    private String mablagh_seporde_ebtedaye_sal;
    @Column(name="modat_bimename")
    private String modat_bimename;
    @Column(name="nahve_daryaf_enteha_bime")
    private String nahveye_daryafte_andukhte_entehaye_modat_bimename;
    @Column(name="nerkh_afza_sal_mostamar")
    private String nerkh_afzayesh_salaneh_mostamari;
    @Column(name="nahve_daryaft_mostamari")
    private String nahve_daryaft_mostamari;
    @Column(name="mdat_zamn_dryaf_mstamar")
    private String moddat_zaman_daryaft_mostamari;
    @Column(name="tabaghe_khatar")
    private String tabaghe_khatar;
    @Column(name="poosh_fot_asar_hadese")
    private String pooshesh_fot_dar_asar_haadese;
    @Column(name="sarm_fot_asar_hadese")
    private String sarmaye_paye_fot_dar_asar_hadese;
    @Column(name="pooshesh_naghs_ozv")
    private String pooshesh_naghs_ozv;
    @Column(name="sarm_poosh_naqs_ozv")
    private String sarmaye_pooshesh_naghs_ozv;
    @Column(name="poosh_fot_asar_zel")
    private String pooshesh_fot_dar_asar_zelzele;
    @Column(name="pooshesh_amraz_khas")
    private String pooshesh_amraz_khas;
    @Column(name="sarm_poosh_khas")
    private String sarmaye_pooshesh_amraz_khas;
    @Column(name="poosh_moafiat")
    private String pooshesh_moafiat;
    @Column(name="noe_tarh")
    private String noe_tarh;
    @Column(name="dars_takhfif_karmozd")
    private String darsad_takhfif_karmozd_karmozd;
    @Column(name="dars_takhfif_vosool")
    private String darsad_takhfif_vosool;
    @OneToOne(mappedBy="estelam",fetch = FetchType.LAZY)
    private Pishnehad pishnehad;

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
        return sarmaye_paye_fot;
    }

    public void setSarmaye_paye_fot(String sarmaye_paye_fot) {
        this.sarmaye_paye_fot = sarmaye_paye_fot;
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
        this.nahve_pardakht_hagh_bime = nahve_pardakht_hagh_bime;
    }

    public String getNerkh_afzayesh_salaneh_hagh_bime() {
        return nerkh_afzayesh_salaneh_hagh_bime;
    }

    public void setNerkh_afzayesh_salaneh_hagh_bime(String nerkh_afzayesh_salaneh_hagh_bime) {
        this.nerkh_afzayesh_salaneh_hagh_bime = nerkh_afzayesh_salaneh_hagh_bime;
    }

    public String getMablagh_seporde_ebtedaye_sal() {
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

    public void setPooshesh_fot_dar_asar_haadese(String pooshesh_fot_dar_asar_haadese) {
        this.pooshesh_fot_dar_asar_haadese = pooshesh_fot_dar_asar_haadese;
    }

    public String getSarmaye_paye_fot_dar_asar_hadese() {
        return sarmaye_paye_fot_dar_asar_hadese;
    }

    public void setSarmaye_paye_fot_dar_asar_hadese(String sarmaye_paye_fot_dar_asar_hadese) {
        this.sarmaye_paye_fot_dar_asar_hadese = sarmaye_paye_fot_dar_asar_hadese;
    }

    public String getPooshesh_naghs_ozv() {
        return pooshesh_naghs_ozv;
    }

    public void setPooshesh_naghs_ozv(String pooshesh_naghs_ozv) {
        this.pooshesh_naghs_ozv = pooshesh_naghs_ozv;
    }

    public String getSarmaye_pooshesh_naghs_ozv() {
        return sarmaye_pooshesh_naghs_ozv;
    }

    public void setSarmaye_pooshesh_naghs_ozv(String sarmaye_pooshesh_naghs_ozv) {
        this.sarmaye_pooshesh_naghs_ozv = sarmaye_pooshesh_naghs_ozv;
    }

    public String getPooshesh_fot_dar_asar_zelzele() {
        return pooshesh_fot_dar_asar_zelzele;
    }

    public void setPooshesh_fot_dar_asar_zelzele(String pooshesh_fot_dar_asar_zelzele) {
        this.pooshesh_fot_dar_asar_zelzele = pooshesh_fot_dar_asar_zelzele;
    }

    public String getPooshesh_amraz_khas() {
        return pooshesh_amraz_khas;
    }

    public void setPooshesh_amraz_khas(String pooshesh_amraz_khas) {
        this.pooshesh_amraz_khas = pooshesh_amraz_khas;
    }

    public String getSarmaye_pooshesh_amraz_khas() {
        return sarmaye_pooshesh_amraz_khas;
    }

    public void setSarmaye_pooshesh_amraz_khas(String sarmaye_pooshesh_amraz_khas) {
        this.sarmaye_pooshesh_amraz_khas = sarmaye_pooshesh_amraz_khas;
    }

    public String getPooshesh_moafiat() {
        return pooshesh_moafiat;
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
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getAndukhte_entehaye_modate_bimename() {
        return andukhte_entehaye_modate_bimename;
    }

    public void setAndukhte_entehaye_modate_bimename(String andukhte_entehaye_modate_bimename) {
        this.andukhte_entehaye_modate_bimename = andukhte_entehaye_modate_bimename;
    }
}
