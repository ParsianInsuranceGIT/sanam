package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/20/11
 * Time: 9:53 AM
 */
@Entity
@Table(name = "tbl_sharayet_jadid")
public class SharayeteJadid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sharayet_id")
    private Integer id;

    @Column(name="mablagh_seporde")
    private String mablagh_seporde_ebtedaye_sal;

    @Column(name = "hagh_bime_pardakhti")
    private String hagh_bime_pardakhti;

    @Column(name = "nahve_pardakht_hagh_bime")
    private String nahve_pardakht_hagh_bime;

    @Column(name = "nerkh_afzayesh_hagh_bime")
    private String nerkh_afzayesh_salaneh_hagh_bime;

    @Column(name="nerkh_afzayesh_sar_fot")
    private String nerkh_afzayesh_salaneh_sarmaye_fot;

    @Column(name = "sarmaye_paye_fot")
    private String sarmaye_paye_fot;


    @Column(name= "modat_bimename")
    private String modat_bimename;

    @Column(name= "pooshesh_fot_haadese")
    private String pooshesh_fot_dar_asar_haadese;



    @Column(name = "sarmaye_paye_fot_hadese")
    private String sarmaye_paye_fot_dar_asar_hadese;

    @Column(name = "pooshesh_naghs_ozv")
    private String pooshesh_naghs_ozv;

    @Column(name = "sarmaye_poosh_ozv")
    private String sarmaye_pooshesh_naghs_ozv;

    @Column(name = "pooshesh_fot_zelzele")
    private String pooshesh_fot_dar_asar_zelzele;

    @Column(name = "pooshesh_amraz_khas")
    private String pooshesh_amraz_khas;

    @Column(name = "sarmaye_poosh_khas")
    private String sarmaye_pooshesh_amraz_khas;

    @Column(name = "pooshesh_moafiat")
    private String pooshesh_moafiat;



    @OneToOne(mappedBy="sharayeteJadid",fetch = FetchType.LAZY)
    private Pishnehad pishnehad;



    public SharayeteJadid(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMablagh_seporde_ebtedaye_sal() {
        return mablagh_seporde_ebtedaye_sal;
    }

    public void setMablagh_seporde_ebtedaye_sal(String mablagh_seporde_ebtedaye_sal) {
        this.mablagh_seporde_ebtedaye_sal = mablagh_seporde_ebtedaye_sal;
    }

    public String getHagh_bime_pardakhti() {
        return hagh_bime_pardakhti;
    }

    public void setHagh_bime_pardakhti(String hagh_bime_pardakhti) {
        this.hagh_bime_pardakhti = hagh_bime_pardakhti;
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

    public String getNerkh_afzayesh_salaneh_sarmaye_fot() {
        return nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public void setNerkh_afzayesh_salaneh_sarmaye_fot(String nerkh_afzayesh_salaneh_sarmaye_fot) {
        this.nerkh_afzayesh_salaneh_sarmaye_fot = nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public String getSarmaye_paye_fot() {
        return sarmaye_paye_fot;
    }

    public void setSarmaye_paye_fot(String sarmaye_paye_fot) {
        this.sarmaye_paye_fot = sarmaye_paye_fot;
    }

    public String getModat_bimename() {
        return modat_bimename;
    }

    public void setModat_bimename(String modat_bimename) {
        this.modat_bimename = modat_bimename;
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

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }
}
