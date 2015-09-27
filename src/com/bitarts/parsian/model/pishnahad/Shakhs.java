package com.bitarts.parsian.model.pishnahad;


import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.constantItems.City;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_Shakhs")
public class Shakhs implements java.io.Serializable {
    public static enum Melliat {
        IRANI, ATBAE_KHAREJI
    }

    public static enum Role {
        BIMEGOZAR, BIMESHODE
    }

    public static enum BimeGozarType {
        HAGHIGHI, HOGHOGHI
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Shakhs_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "name_khanevadegi")
    private String nameKhanevadegi;
    @Column(name = "name_pedar")
    private String namePedar;
    @Column(name = "pishvand")
    private String pishvand;
    @Column(name = "irani_or_atbaeKhareji")
    @Enumerated(EnumType.STRING)
    private Melliat iraniOrAtbaeKhareji;
    @Column(name = "kode_melli_shenasayi")
    private String kodeMelliShenasayi;
    @Column(name = "shomare_shenasnameh")
    private String shomareShenasnameh;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mahalle_sodor_id")
    private City mahalleSodoreShenasnameh;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mahalle_tavallod_id")
    private City mahalleTavallod;
    @Column(name = "tarikhe_tavallod")
    private String tarikheTavallod;
    @Column(name = "vaziate_taahol")
    private String vaziateTaahol;
    @Column(name = "jensiat")
    private String jensiat;
    @Column(name = "shoghle_asli")
    private String shoghleAsli;
    @Column(name = "shoghle_farei")
    private String shoghleFarei;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BimeGozarType type;
    private String typeString;
    @Column(name = "dolati_khososi")
    private String dolatiKhososi;
    @Column(name = "noe_faaliat")
    private String noeFaaliat;
    @Column(name = "kode_eghtesadi")
    private String kodeEghtesadi;
    @Column(name = "shomare_sabt")
    private String shomareSabt;
    @Column(name = "tarikhe_sabt")
    private String tarikheSabt;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mahalle_sabt")
    private City mahalleSabt;
    @OneToMany(mappedBy = "shakhs", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<BimeGozar> bimeGozar;
    @OneToMany(mappedBy = "shakhs", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<BimeShode> bimeShode;

    //this field added for elhaghie taghir ashkhas. . .
    @Column (name = "change_with_search")
    private String changeWithSerach;


    public String getChangeWithSerach()
    {
        return changeWithSerach;
    }

    public void setChangeWithSerach(String changeWithSerach)
    {
        this.changeWithSerach = changeWithSerach;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNamePedar() {
        return namePedar;
    }

    public void setNamePedar(String namePedar) {
        this.namePedar = namePedar;
    }

    public Melliat getIraniOrAtbaeKhareji() {
        return iraniOrAtbaeKhareji;
    }

    public void setIraniOrAtbaeKhareji(Melliat iraniOrAtbaeKhareji) {
        this.iraniOrAtbaeKhareji = iraniOrAtbaeKhareji;
    }

    public String getKodeMelliShenasayi() {
        return kodeMelliShenasayi;
    }

    public void setKodeMelliShenasayi(String kodeMelliShenasayi) {
        this.kodeMelliShenasayi = kodeMelliShenasayi;
    }

    public String getShomareShenasnameh() {
        return shomareShenasnameh;
    }

    public void setShomareShenasnameh(String shomareShenasnameh) {
        this.shomareShenasnameh = shomareShenasnameh;
    }

    public City getMahalleTavallod() {
        return mahalleTavallod;
    }

    public void setMahalleTavallod(City mahalleTavallod) {
        this.mahalleTavallod = mahalleTavallod;
    }

    public String getTarikheTavallod() {
        return tarikheTavallod;
    }

    public void setTarikheTavallod(String tarikheTavallod) {
        this.tarikheTavallod = tarikheTavallod;
    }

    public String getVaziateTaahol() {
        return vaziateTaahol;
    }

    public void setVaziateTaahol(String vaziateTaahol) {
        this.vaziateTaahol = vaziateTaahol;
    }

    public String getJensiat() {
        return jensiat;
    }

    public void setJensiat(String jensiat) {
        this.jensiat = jensiat;
    }

    public String getShoghleAsli() {
        return shoghleAsli;
    }

    public void setShoghleAsli(String shoghleAsli) {
        this.shoghleAsli = shoghleAsli;
    }

    public String getShoghleFarei() {
        return shoghleFarei;
    }

    public void setShoghleFarei(String shoghleFarei) {
        this.shoghleFarei = shoghleFarei;
    }

    public String getPishvand() {
        return pishvand;
    }

    public void setPishvand(String pishvand) {
        this.pishvand = pishvand;
    }

    public City getMahalleSodoreShenasnameh() {
        return mahalleSodoreShenasnameh;
    }

    public void setMahalleSodoreShenasnameh(City mahalleSodoreShenasnameh) {
        this.mahalleSodoreShenasnameh = mahalleSodoreShenasnameh;
    }

    public BimeGozarType getType() {
        return type;
    }

    public void setType(BimeGozarType type) {
        this.type = type;
    }

    public String getDolatiKhososi() {
        return dolatiKhososi;
    }

    public void setDolatiKhososi(String dolatiKhososi) {
        this.dolatiKhososi = dolatiKhososi;
    }

    public String getNoeFaaliat() {
        return noeFaaliat;
    }

    public void setNoeFaaliat(String noeFaaliat) {
        this.noeFaaliat = noeFaaliat;
    }

    public String getKodeEghtesadi() {
        return kodeEghtesadi;
    }

    public void setKodeEghtesadi(String kodeEghtesadi) {
        this.kodeEghtesadi = kodeEghtesadi;
    }

    public String getShomareSabt() {
        return shomareSabt;
    }

    public void setShomareSabt(String shomareSabt) {
        this.shomareSabt = shomareSabt;
    }

    public String getTarikheSabt() {
        return tarikheSabt;
    }

    public void setTarikheSabt(String tarikheSabt) {
        this.tarikheSabt = tarikheSabt;
    }

    public City getMahalleSabt() {
        return mahalleSabt;
    }

    public void setMahalleSabt(City mahalleSabt) {
        this.mahalleSabt = mahalleSabt;
    }

    public String getSen() {
        if(tarikheTavallod == null) return "0";
        return String.valueOf((int) Math.ceil(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), tarikheTavallod) / 365));
    }
    public String getFullName() {
        return (name == null ? "" : name) + " " + (nameKhanevadegi == null ? "" : nameKhanevadegi);
    }

    public Set<BimeGozar> getBimeGozar() {
        return bimeGozar;
    }

    public void setBimeGozar(Set<BimeGozar> bimeGozar) {
        this.bimeGozar = bimeGozar;
    }

    public Set<BimeShode> getBimeShode() {
        return bimeShode;
    }

    public void setBimeShode(Set<BimeShode> bimeShode) {
        this.bimeShode = bimeShode;
    }
}
