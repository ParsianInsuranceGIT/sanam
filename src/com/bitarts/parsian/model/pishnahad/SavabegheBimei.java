package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:33 PM
 */
@Entity
@Table(name = "tbl_SavabegheBimei")
public class SavabegheBimei implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "noe_bime_name")
    private String noeBimeName;
    @Column(name = "name_bime_name")
    private String nameBimeName;
    @Column(name = "sherkate_bime_gar")
    private String sherkateBimeGar;
    @Column(name = "sherkate_bime_gar_sayer")
    private String sherkateBimeGarTozihateSayer;
    @Column(name = "natije_nahayi")
    private String natijeNahayi;
    @Column(name = "sarmaye_fout")
    private String sarmayeFout;
    @Column(name = "sarmaye_hayat")
    private String sarmayeHayat;
    @Column(name = "sharhe_adame_sodor")
    private String sharheAdameSodor;
    @Column(name = "tarikhe_khateme")
    private String tarikheKhateme;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;

    public SavabegheBimei() {
    }

    public SavabegheBimei(String noeBimeName, String nameBimeName, String sherkateBimeGar, String sherkateBimeGarTozihateSayer, String natijeNahayi, String sarmayeFout, String sarmayeHayat, String sharheAdameSodor, String tarikheKhateme, Pishnehad pishnehad) {
        this.noeBimeName = noeBimeName;
        this.nameBimeName = nameBimeName;
        this.sherkateBimeGar = sherkateBimeGar;
        this.sherkateBimeGarTozihateSayer = sherkateBimeGarTozihateSayer;
        this.natijeNahayi = natijeNahayi;
        this.sarmayeFout = sarmayeFout;
        this.sarmayeHayat = sarmayeHayat;
        this.sharheAdameSodor = sharheAdameSodor;
        this.tarikheKhateme = tarikheKhateme;
        this.pishnehad = pishnehad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoeBimeName() {
        return noeBimeName;
    }

    public void setNoeBimeName(String noeBimeName) {
        this.noeBimeName = noeBimeName;
    }

    public String getNameBimeName() {
        return nameBimeName;
    }

    public void setNameBimeName(String nameBimeName) {
        this.nameBimeName = nameBimeName;
    }

    public String getSherkateBimeGar() {
        return sherkateBimeGar;
    }

    public void setSherkateBimeGar(String sherkateBimeGar) {
        this.sherkateBimeGar = sherkateBimeGar;
    }

    public String getNatijeNahayi() {
        return natijeNahayi;
    }

    public void setNatijeNahayi(String natijeNahayi) {
        this.natijeNahayi = natijeNahayi;
    }

    public String getSarmayeFout() {
        return sarmayeFout;
    }

    public void setSarmayeFout(String sarmayeFout) {
        this.sarmayeFout = sarmayeFout;
    }

    public String getSarmayeHayat() {
        return sarmayeHayat;
    }

    public void setSarmayeHayat(String sarmayeHayat) {
        this.sarmayeHayat = sarmayeHayat;
    }

    public String getSharheAdameSodor() {
        return sharheAdameSodor;
    }

    public void setSharheAdameSodor(String sharheAdameSodor) {
        this.sharheAdameSodor = sharheAdameSodor;
    }

    public String getTarikheKhateme() {
        return tarikheKhateme;
    }

    public void setTarikheKhateme(String tarikheKhateme) {
        this.tarikheKhateme = tarikheKhateme;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getSherkateBimeGarTozihateSayer() {
        return sherkateBimeGarTozihateSayer;
    }

    public void setSherkateBimeGarTozihateSayer(String sherkateBimeGarTozihateSayer) {
        this.sherkateBimeGarTozihateSayer = sherkateBimeGarTozihateSayer;
    }
}