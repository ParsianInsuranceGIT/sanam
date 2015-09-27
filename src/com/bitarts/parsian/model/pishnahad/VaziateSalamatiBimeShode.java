package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:03 PM
 */
@Entity
@Table(name = "tbl_VaziateSalamati")
public class VaziateSalamatiBimeShode implements java.io.Serializable {
    public String getModateMasraf2() {
        return modateMasraf2;
    }

    public void setModateMasraf2(String modateMasraf2) {
        this.modateMasraf2 = modateMasraf2;
    }

    public static enum JavabeSolal {
        BALI, KHEIR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constant_soal_item_id")
    private ConstantSoalateVaziateSalamati constantSoalItem;
    @Column(name = "javab")
    @Enumerated(EnumType.STRING)
    private JavabeSolal javabeSolal;
    @Column(name = "shoroe_zamane_bimari")
    private String shoroeZamaneBimari;
    @Column(name = "vaziate_felie_bimeShode")
    private String vaziateFelieBimeShode;
    @Column(name = "daroye_masrafi")
    private String daroyeMasrafi;
    @Column(name = "meghdare_masraf")
    private String meghdareMasraf;
    @Column(name = "modate_masraf")
    private String modateMasraf;
    @Column(name = "darmane_anjam_shode")
    private String darmaneAnjamShode;
    @Column(name = "tarikhe_jarahi")
    private String tarikheJarahi;
    @Column(name = "modat_masraf2")
    private String modateMasraf2;
    @Column(name = "tozih")
    private String tozih;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;

    public VaziateSalamatiBimeShode() {
    }

    public VaziateSalamatiBimeShode(ConstantSoalateVaziateSalamati constantSoalItem, JavabeSolal javabeSolal, String shoroeZamaneBimari, String vaziateFelieBimeShode, String daroyeMasrafi, String meghdareMasraf, String modateMasraf, String darmaneAnjamShode, String tarikheJarahi, String tozih, Pishnehad pishnehad) {
        this.constantSoalItem = constantSoalItem;
        this.javabeSolal = javabeSolal;
        this.shoroeZamaneBimari = shoroeZamaneBimari;
        this.vaziateFelieBimeShode = vaziateFelieBimeShode;
        this.daroyeMasrafi = daroyeMasrafi;
        this.meghdareMasraf = meghdareMasraf;
        this.modateMasraf = modateMasraf;
        this.darmaneAnjamShode = darmaneAnjamShode;
        this.tarikheJarahi = tarikheJarahi;
        this.tozih = tozih;
        this.pishnehad = pishnehad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConstantSoalateVaziateSalamati getConstantSoalItem() {
        return constantSoalItem;
    }

    public void setConstantSoalItem(ConstantSoalateVaziateSalamati constantSoalItem) {
        this.constantSoalItem = constantSoalItem;
    }

    public JavabeSolal getJavabeSolal() {
        return javabeSolal;
    }

    public String getJavabeSolalFarsi() {
        if(javabeSolal == VaziateSalamatiBimeShode.JavabeSolal.BALI)
            return "بلی";
        else
            return "خیر";
    }

    public void setJavabeSolal(JavabeSolal javabeSolal) {
        this.javabeSolal = javabeSolal;
    }

    public String getTozih() {
        return tozih;
    }

    public void setTozih(String tozih) {
        this.tozih = tozih;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getShoroeZamaneBimari() {
        return shoroeZamaneBimari;
    }

    public void setShoroeZamaneBimari(String shoroeZamaneBimari) {
        this.shoroeZamaneBimari = shoroeZamaneBimari;
    }

    public String getVaziateFelieBimeShode() {
        return vaziateFelieBimeShode;
    }

    public void setVaziateFelieBimeShode(String vaziateFelieBimeShode) {
        this.vaziateFelieBimeShode = vaziateFelieBimeShode;
    }

    public String getDaroyeMasrafi() {
        return daroyeMasrafi;
    }

    public void setDaroyeMasrafi(String daroyeMasrafi) {
        this.daroyeMasrafi = daroyeMasrafi;
    }

    public String getModateMasraf() {
        return modateMasraf;
    }

    public void setModateMasraf(String modateMasraf) {
        this.modateMasraf = modateMasraf;
    }

    public String getDarmaneAnjamShode() {
        return darmaneAnjamShode;
    }

    public void setDarmaneAnjamShode(String darmaneAnjamShode) {
        this.darmaneAnjamShode = darmaneAnjamShode;
    }

    public String getTarikheJarahi() {
        return tarikheJarahi;
    }

    public void setTarikheJarahi(String tarikheJarahi) {
        this.tarikheJarahi = tarikheJarahi;
    }

    public String getMeghdareMasraf() {
        return meghdareMasraf;
    }

    public void setMeghdareMasraf(String meghdareMasraf) {
        this.meghdareMasraf = meghdareMasraf;
    }
}
