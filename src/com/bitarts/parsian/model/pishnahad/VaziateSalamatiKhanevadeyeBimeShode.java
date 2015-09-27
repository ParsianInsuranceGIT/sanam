package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:21 PM
 */
@Entity
@Table(name = "tbl_VaziateSalamatiKhanevadeye")
public class VaziateSalamatiKhanevadeyeBimeShode implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nesbat_ba_bimeShode")
    private String nesbatBabimeShode;
    @Column(name = "vaziate_hayat")
    private String vaziateHayat;
    @Column(name = "senne_dar_hayat")
    private String senneDarHayat;
    @Column(name = "vaziate_salamati")
    private String vaziateSalamati;
    @Column(name = "senne_Fout")
    private String senneDarZamaneFout;
    @Column(name = "ellate_fout")
    private String ellateFout;
    @Column(name = "sharhe_ellate_fout", length = 1000)
    private String sharheEllateFout;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;

    public VaziateSalamatiKhanevadeyeBimeShode() {
    }

    public VaziateSalamatiKhanevadeyeBimeShode(String nesbatBabimeShode, String vaziateHayat, String senneDarHayat, String vaziateSalamati, String senneDarZamaneFout, String ellateFout, String sharheEllateFout, Pishnehad pishnehad) {
        this.nesbatBabimeShode = nesbatBabimeShode;
        this.vaziateHayat = vaziateHayat;
        this.senneDarHayat = senneDarHayat;
        this.vaziateSalamati = vaziateSalamati;
        this.senneDarZamaneFout = senneDarZamaneFout;
        this.ellateFout = ellateFout;
        this.sharheEllateFout = sharheEllateFout;
        this.pishnehad = pishnehad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNesbatBabimeShode() {
        return nesbatBabimeShode;
    }

    public void setNesbatBabimeShode(String nesbatBabimeShode) {
        this.nesbatBabimeShode = nesbatBabimeShode;
    }

    public String getVaziateHayat() {
        return vaziateHayat;
    }

    public void setVaziateHayat(String vaziateHayat) {
        this.vaziateHayat = vaziateHayat;
    }

    public String getSenneDarHayat() {
        return senneDarHayat;
    }

    public void setSenneDarHayat(String senneDarHayat) {
        this.senneDarHayat = senneDarHayat;
    }

    public String getVaziateSalamati() {
        return vaziateSalamati;
    }

    public void setVaziateSalamati(String vaziateSalamati) {
        this.vaziateSalamati = vaziateSalamati;
    }

    public String getSenneDarZamaneFout() {
        return senneDarZamaneFout;
    }

    public void setSenneDarZamaneFout(String senneDarZamaneFout) {
        this.senneDarZamaneFout = senneDarZamaneFout;
    }

    public String getEllateFout() {
        return ellateFout;
    }

    public void setEllateFout(String ellateFout) {
        this.ellateFout = ellateFout;
    }

    public String getSharheEllateFout() {
        return sharheEllateFout;
    }

    public void setSharheEllateFout(String sharheEllateFout) {
        this.sharheEllateFout = sharheEllateFout != null ? sharheEllateFout.replaceAll("\"","") : "";
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }
}
