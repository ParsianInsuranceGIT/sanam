package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Table(name="tbl_darkhast_bazkharid")
public class DarkhastVam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "darbazkharid_id")
    private Integer id;
    @Column(name= "darbaz_nameBimeshode")
    private String nameBimeShode;
    @Column(name= "darbaz_familyBimeshode")
    private String familyBimeShode;
    @Column(name="darbaz_namePedarBimeShode")
    private String namePedarBimeShode;
    @Column(name="darbaz_kodeMeliBimeShode")
    private String kodeMeliBimeShode;
    @Column(name="darbaz_arzesh_bazkharid")
    private String arzeshBazkharid;
    @Column(name="darbaz_shomareShenasname")
    private String shomareShenasnameBimeShode;
    @Column(name="darbaz_tarikhTavalBimShod")
    private String tarikhTavallodBimeShode;
    @Column(name="darbaz_telephonBimeShode")
    private String telephonSabetBimeShode;
    @Column(name="darbaz_hamrahBimeShode")
    private String telephonHamrahBimeShode;
    @Column(name = "darbaz_shomareHesab")
    private String shomareHesab;
    @Column(name = "darbaz_shomareShaba")
    private String shomareShaba;
    @Column(name = "darbaz_bankName")
    private String bankName;
    @Column(name = "darbaz_shobeName")
    private String shobeName;
    @Column(name = "darbaz_kodeShobe")
    private String kodeShobe;
    @Column(name = "darbaz_sahebHesab")
    private String sahebHesab;
    @Column(name = "darbaz_kodeNamayandegi")
    private String kodeNamayandegi;
    @Column(name = "darbaz_telephoneNamay")
    private String telephoneNamayandegi;
    @Column(name = "darbaz_noeNamayandegi")
    private String noeNamayandegi;
    @Column(name = "darbaz_tarikhijad")
    private String tarikhDarkhast;
    @Column(name = "darbaz_shomareDarkhast")
    private String shomareDarkhast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_id")
    private User karshenas;

    @OneToOne(mappedBy="darkhastBazkharid",fetch = FetchType.LAZY)
    private Bimename bimename;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zamaem_darkhast_id")
    private ZamaemDarkhast zamaemDarkhast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name="namayande_id")
    private Namayande namayande;

    public Namayande getNamayande()
    {
        return namayande;
    }

    public void setNamayande(Namayande namayande)
    {
        this.namayande = namayande;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameBimeShode() {
        return nameBimeShode;
    }

    public void setNameBimeShode(String nameBimeShode) {
        this.nameBimeShode = nameBimeShode;
    }

    public String getFamilyBimeShode() {
        return familyBimeShode;
    }

    public void setFamilyBimeShode(String familyBimeShode) {
        this.familyBimeShode = familyBimeShode;
    }

    public String getNamePedarBimeShode() {
        return namePedarBimeShode;
    }

    public void setNamePedarBimeShode(String namePedarBimeShode) {
        this.namePedarBimeShode = namePedarBimeShode;
    }

    public String getKodeMeliBimeShode() {
        return kodeMeliBimeShode;
    }

    public void setKodeMeliBimeShode(String kodeMeliBimeShode) {
        this.kodeMeliBimeShode = kodeMeliBimeShode;
    }

    public String getShomareShenasnameBimeShode() {
        return shomareShenasnameBimeShode;
    }

    public void setShomareShenasnameBimeShode(String shomareShenasnameBimeShode) {
        this.shomareShenasnameBimeShode = shomareShenasnameBimeShode;
    }

    public String getTarikhTavallodBimeShode() {
        return tarikhTavallodBimeShode;
    }

    public void setTarikhTavallodBimeShode(String tarikhTavallodBimeShode) {
        this.tarikhTavallodBimeShode = tarikhTavallodBimeShode;
    }

    public String getTelephonSabetBimeShode() {
        return telephonSabetBimeShode;
    }

    public void setTelephonSabetBimeShode(String telephonSabetBimeShode) {
        this.telephonSabetBimeShode = telephonSabetBimeShode;
    }

    public String getTelephonHamrahBimeShode() {
        return telephonHamrahBimeShode;
    }

    public void setTelephonHamrahBimeShode(String telephonHamrahBimeShode) {
        this.telephonHamrahBimeShode = telephonHamrahBimeShode;
    }

    public String getShomareHesab() {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab) {
        this.shomareHesab = shomareHesab;
    }

    public String getShomareShaba() {
        return shomareShaba;
    }

    public void setShomareShaba(String shomareShaba) {
        this.shomareShaba = shomareShaba;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getShobeName() {
        return shobeName;
    }

    public void setShobeName(String shobeName) {
        this.shobeName = shobeName;
    }

    public String getKodeShobe() {
        return kodeShobe;
    }

    public void setKodeShobe(String kodeShobe) {
        this.kodeShobe = kodeShobe;
    }

    public String getSahebHesab() {
        return sahebHesab;
    }

    public void setSahebHesab(String sahebHesab) {
        this.sahebHesab = sahebHesab;
    }

    public String getKodeNamayandegi() {
        return kodeNamayandegi;
    }

    public void setKodeNamayandegi(String kodeNamayandegi) {
        this.kodeNamayandegi = kodeNamayandegi;
    }

    public String getTelephoneNamayandegi() {
        return telephoneNamayandegi;
    }

    public void setTelephoneNamayandegi(String telephoneNamayandegi) {
        this.telephoneNamayandegi = telephoneNamayandegi;
    }

    public String getNoeNamayandegi() {
        return noeNamayandegi;
    }

    public void setNoeNamayandegi(String noeNamayandegi) {
        this.noeNamayandegi = noeNamayandegi;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getKarshenas() {
        return karshenas;
    }

    public void setKarshenas(User karshenas) {
        this.karshenas = karshenas;
    }

    public ZamaemDarkhast getZamaemDarkhast() {
        return zamaemDarkhast;
    }

    public void setZamaemDarkhast(ZamaemDarkhast zamaemDarkhast) {
        this.zamaemDarkhast = zamaemDarkhast;
    }

    public String getArzeshBazkharid() {
        return arzeshBazkharid;
    }

    public void setArzeshBazkharid(String arzeshBazkharid) {
        this.arzeshBazkharid = arzeshBazkharid;
    }

    public String getTarikhDarkhast() {
        return tarikhDarkhast;
    }

    public void setTarikhDarkhast(String tarikhDarkhast) {
        this.tarikhDarkhast = tarikhDarkhast;
    }

    public String getShomareDarkhast() {
        return shomareDarkhast;
    }

    public void setShomareDarkhast(String shomareDarkhast) {
        this.shomareDarkhast = shomareDarkhast;
    }
}