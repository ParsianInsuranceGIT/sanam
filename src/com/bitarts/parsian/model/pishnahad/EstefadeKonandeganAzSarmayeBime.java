package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/13/11
 * Time: 5:47 PM
 */
@Entity
@Table(name = "tbl_estefade_konandegan")
public class EstefadeKonandeganAzSarmayeBime implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "noe_zi_nafea")
    private String noeZiNafea;
    @Column(name = "nesbatBabimeShode")
    private String nesbatBabimeShode;
    @Column(name = "ekasb_name")
    private String name;
    @Column(name = "name_khanev")
    private String nameKhanevadegi;
    @Column(name = "shomare_shenasname")
    private String shomareShenasname;
    @Column(name = "shomare_sabt")
    private String shomareSabt;
    @Column(name = "kode_melli")
    private String kodeMelli;
    @Column(name = "kode_eghtesadi")
    private String kodeEghtesadi;
    @Column(name = "tarikh_tavallod")
    private String tarikhTavallod;
    @Column(name = "tarikh_sabt")
    private String tarikhSabt;
    @Column(name = "mahalle_tavallod")
    private String mahalleTavallod;
    @Column(name = "mahalle_sabt")
    private String mahalleSabt;
    @Column(name = "darsadeSahm")
    private String darsadeSahm;
    @Column(name = "olaviat")
    private String olaviat;
    @Column(name = "mahalle_sodor")
    private String mahalleSodoorShenasnameh;
    @Column(name = "name_pedar")
    private String namePedar;
    @Column(name = "jensiat")
    private String jensiat;
    @Column(name = "vaziate_fot_va_hayat")
    private String vaziateFotVaHayat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;

    public EstefadeKonandeganAzSarmayeBime() {

    }

    public EstefadeKonandeganAzSarmayeBime(String noeZiNafea, String nesbatBabimeShode, String name, String nameKhanevadegi, String shomareShenasname, String shomareSabt, String kodeMelli, String kodeEghtesadi, String tarikhTavallod, String tarikhSabt, String mahalleTavallod, String mahalleSabt, String darsadeSahm, String olaviat, String vaziateFotVaHayat, Pishnehad pishnehad) {
        this.noeZiNafea = noeZiNafea;
        this.nesbatBabimeShode = nesbatBabimeShode;
        this.name = name;
        this.nameKhanevadegi = nameKhanevadegi;
        this.shomareShenasname = shomareShenasname;
        this.shomareSabt = shomareSabt;
        this.kodeMelli = kodeMelli;
        this.kodeEghtesadi = kodeEghtesadi;
        this.tarikhTavallod = tarikhTavallod;
        this.tarikhSabt = tarikhSabt;
        this.mahalleTavallod = mahalleTavallod;
        this.mahalleSabt = mahalleSabt;
        this.darsadeSahm = darsadeSahm;
        this.olaviat = olaviat;
        this.vaziateFotVaHayat = vaziateFotVaHayat;
        this.pishnehad = pishnehad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoeZiNafea() {
        return noeZiNafea;
    }

    public void setNoeZiNafea(String noeZiNafea) {
        this.noeZiNafea = noeZiNafea;
    }

    public String getNesbatBabimeShode() {
        return nesbatBabimeShode;
    }

    public void setNesbatBabimeShode(String nesbatBabimeShode) {
        this.nesbatBabimeShode = nesbatBabimeShode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKhanevadegi() {
        return nameKhanevadegi;
    }

    public void setNameKhanevadegi(String nameKhanevadegi) {
        this.nameKhanevadegi = nameKhanevadegi;
    }

    public String getShomareShenasname() {
        return shomareShenasname;
    }

    public void setShomareShenasname(String shomareShenasname) {
        this.shomareShenasname = shomareShenasname;
    }

    public String getShomareSabt() {
        return shomareSabt;
    }

    public void setShomareSabt(String shomareSabt) {
        this.shomareSabt = shomareSabt;
    }

    public String getKodeMelli() {
        return kodeMelli;
    }

    public void setKodeMelli(String kodeMelli) {
        this.kodeMelli = kodeMelli;
    }

    public String getKodeEghtesadi() {
        return kodeEghtesadi;
    }

    public void setKodeEghtesadi(String kodeEghtesadi) {
        this.kodeEghtesadi = kodeEghtesadi;
    }

    public String getTarikhTavallod() {
        return tarikhTavallod;
    }

    public void setTarikhTavallod(String tarikhTavallod) {
        this.tarikhTavallod = tarikhTavallod;
    }

    public String getTarikhSabt() {
        return tarikhSabt;
    }

    public void setTarikhSabt(String tarikhSabt) {
        this.tarikhSabt = tarikhSabt;
    }

    public String getMahalleTavallod() {
        return mahalleTavallod;
    }

    public void setMahalleTavallod(String mahalleTavallod) {
        this.mahalleTavallod = mahalleTavallod;
    }

    public String getMahalleSabt() {
        return mahalleSabt;
    }

    public void setMahalleSabt(String mahalleSabt) {
        this.mahalleSabt = mahalleSabt;
    }

    public String getDarsadeSahm() {
        return darsadeSahm;
    }

    public void setDarsadeSahm(String darsadeSahm) {
        this.darsadeSahm = darsadeSahm;
    }

    public String getOlaviat() {
        return olaviat;
    }

    public void setOlaviat(String olaviat) {
        this.olaviat = olaviat;
    }

    public String getVaziateFotVaHayat() {
        return vaziateFotVaHayat;
    }

    public void setVaziateFotVaHayat(String vaziateFotVaHayat) {
        this.vaziateFotVaHayat = vaziateFotVaHayat;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public int compareTo(Object o) {
        if (this.getVaziateFotVaHayat() == null)
            return 0;
        if (((EstefadeKonandeganAzSarmayeBime) (o)).getVaziateFotVaHayat() == null)
            return 0;
        return this.getVaziateFotVaHayat().compareTo(((EstefadeKonandeganAzSarmayeBime) o).getVaziateFotVaHayat()) * (-1);
    }

    public String getMahalleSodoorShenasnameh() {
        return mahalleSodoorShenasnameh;
    }

    public void setMahalleSodoorShenasnameh(String mahalleSodoorShenasnameh) {
        this.mahalleSodoorShenasnameh = mahalleSodoorShenasnameh;
    }

    public String getNamePedar() {
        return namePedar;
    }

    public void setNamePedar(String namePedar) {
        this.namePedar = namePedar;
    }

    public String getFullName() {
        return name + " " + nameKhanevadegi;
    }

    public String getJensiat() {
        return jensiat;
    }

    public void setJensiat(String jensiat) {
        this.jensiat = jensiat;
    }

    public String getPishvand() {
        if(jensiat.equals("زن")) {
            return "خانم";
        }
        else {
            return "آقای";
        }

    }
}
