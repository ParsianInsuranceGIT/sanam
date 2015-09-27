package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 1:53 PM
 */
@Entity
@Table(name = "tbl_SoalateOmomiAzBimeShode")
public class SoalateOmomiAzBimeShode implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ghad_bime_shode")
    private String ghad_bime_shode;
    @Column(name = "vazn_bime_shode")
    private String vazn_bime_shode;
    @Column(name = "khdemat_nezaam_vazife")
    private String khdemat_nezaam_vazife;
    @Column(name = "moaafiyat_pezeshki")
    private String moaafiyat_pezeshki;
    @Column(name = "tozih_faaliyat_janbi")
    private String tozih_faaliyat_janbi;

    public SoalateOmomiAzBimeShode() {
    }

    public SoalateOmomiAzBimeShode(String ghad_bime_shode, String vazn_bime_shode, String khdemat_nezaam_vazife, String moaafiyat_pezeshki, String tozih_faaliyat_janbi) {
        this.ghad_bime_shode = ghad_bime_shode;
        this.vazn_bime_shode = vazn_bime_shode;
        this.khdemat_nezaam_vazife = khdemat_nezaam_vazife;
        this.moaafiyat_pezeshki = moaafiyat_pezeshki;
        this.tozih_faaliyat_janbi = tozih_faaliyat_janbi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGhad_bime_shode() {
        return ghad_bime_shode;
    }

    public void setGhad_bime_shode(String ghad_bime_shode) {
        this.ghad_bime_shode = ghad_bime_shode;
    }

    public String getVazn_bime_shode() {
        return vazn_bime_shode;
    }

    public void setVazn_bime_shode(String vazn_bime_shode) {
        this.vazn_bime_shode = vazn_bime_shode;
    }

    public String getKhdemat_nezaam_vazife() {
        return khdemat_nezaam_vazife;
    }

    public String getKhdemat_nezaam_vazifeFarsi() {
        if(khdemat_nezaam_vazife == null) return "";
        if(khdemat_nezaam_vazife.equals("1"))
            return "داراي كارت پايان خدمت";
        if(khdemat_nezaam_vazife.equals("2"))
            return "معافيت پزشكي";
        if(khdemat_nezaam_vazife.equals("3"))
            return "معافيت غير پزشكي";
        if(khdemat_nezaam_vazife.equals("4"))
            return "غير مشمول";
        if(khdemat_nezaam_vazife.equals("5"))
            return "سایر";
        return "";
    }

    public void setKhdemat_nezaam_vazife(String khdemat_nezaam_vazife) {
        this.khdemat_nezaam_vazife = khdemat_nezaam_vazife;
    }

    public String getMoaafiyat_pezeshki() {
        return moaafiyat_pezeshki;
    }

    public void setMoaafiyat_pezeshki(String moaafiyat_pezeshki) {
        this.moaafiyat_pezeshki = moaafiyat_pezeshki;
    }

    public String getTozih_faaliyat_janbi() {
        return tozih_faaliyat_janbi;
    }

    public void setTozih_faaliyat_janbi(String tozih_faaliyat_janbi) {
        this.tozih_faaliyat_janbi = tozih_faaliyat_janbi;
    }

    public SoalateOmomiAzBimeShode(String ghad_bime_shode, String vazn_bime_shode, String khdemat_nezaam_vazife, String moaafiyat_pezeshki, String faaliyat_janbi, String tozih_faaliyat_janbi) {
        this.ghad_bime_shode = ghad_bime_shode;
        this.vazn_bime_shode = vazn_bime_shode;
        this.khdemat_nezaam_vazife = khdemat_nezaam_vazife;
        this.moaafiyat_pezeshki = moaafiyat_pezeshki;
        this.tozih_faaliyat_janbi = tozih_faaliyat_janbi;
    }
}
