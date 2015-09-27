package com.bitarts.parsian.model.bimename;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.bitarts.parsian.util.OmrUtil;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Table(name = "tbl_bimename")
public class Bimename implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bimename_id")
    private Integer id;
    @Column(name = "bimename_shomare")
    private String shomare;
    @Column(name = "bimename_shomare_moshakhase")
    private String shomareMoshakhase;
    @Column(name = "shomare_seriale_print")
    private String shomareSerialePrint;
    @Column(name = "bimename_vaziat")
    private String vaziat;
    @Column(name = "bimename_serial")
    private String serial;

//    @Column(name = "bimename_vahedSodor")
//    private String vahedSodor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedSodor_id")
    private Namayande vahedSodor;

    @Column(name = "bimename_karmozd")
    private Long karmozdBimename;
    @Column(name = "bimename_tarikhsodur")
    private String tarikhSodour;
    @Column(name = "bimename_tarikhshorou")
    private String tarikhShorou;
    @Column(name = "bimename_tarikhengheza")
    private String tarikhEngheza;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @Column(name = "bimename_sharayetkhosusi", length = 1000)
    private String sharayeteKhosusi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id1")
    private EmzaKonande emzaKonandeAval;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id2")
    private EmzaKonande emzaKonandeDovom;
    @Column(name = "bimename_ready") //agar yes bashad shomare bime mojood ast agar no bashad shomare bime mojood nist
    private String readyToShow;
    @Column(name = "state_change_date")
    private String stateChangeDate;

    @OneToMany(mappedBy = "bimename", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<GhestBandi> ghestBandiList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bimename", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Pishnehad> pishnehadList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bimename", cascade = CascadeType.REMOVE)
    private Set<Pishnehad> pishnehadSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_current_id")
    private Pishnehad currentPishnehad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_sodour_id")
    private Pishnehad sodourPishnehad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_sodourWE_id")
    private Pishnehad sodourPishnehadWithElhaghie;

    @Column(name="not_Check_Vams")
    private Boolean notCheckVams;

    public Boolean getNotCheckVams()
    {
        return notCheckVams;
    }

    public void setNotCheckVams(Boolean notCheckVams)
    {
        this.notCheckVams = notCheckVams;
    }

    public Set<Pishnehad> getPishnehadSet()
    {
        return pishnehadSet;
    }

    public void setPishnehadSet(Set<Pishnehad> pishnehadSet)
    {
        this.pishnehadSet = pishnehadSet;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bimename", cascade = CascadeType.REMOVE)
//    @Fetch(FetchMode.SUBSELECT)
//    private List<Khesarat> khesarats;

    @OneToMany(mappedBy = "bimename", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @Fetch(FetchMode.SUBSELECT)
    private List<Elhaghiye> elhaghiyeha;

    @OneToMany(mappedBy = "bimename", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @JoinTable(name = "rel_bime_dar")
//    @Fetch(FetchMode.SUBSELECT)
    private List<DarkhastBazkharid> allDarkhasts;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhastBazkharid_id")
    private DarkhastBazkharid darkhastBazkharid;
    @Column(name = "andukht_ghati", nullable = true)
    private Long andukhteyeGhatie;
    @Column(name = "andukht_alal_hesab", nullable = true)
    private Long andukhteyeAlalHesab;
    @Column(name = "arzesh_ghati", nullable = true)
    private Long arzeshBazkharidGhatie;
    @Column(name = "arzesg_alal_hesab", nullable = true)
    private Long arzeshBazkharidAlalHesab;
    @Column(name = "mosharekat_dummy", nullable = true)
    private Long mablaghMosharekatDummy;
    @Column(name = "previous_mosharekat_dummy", nullable = true)
    private Long amountPreviousMosharekatDummy;
    @Column(name = "state_mosharekat", nullable = true)
    private String stateForMosharekat;
    @Column(name = "shouldSabtInMosharekat", nullable = true)
    private String shouldSabtInMosharekat;
    @OneToMany(mappedBy = "bimename", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<LogMosharekat> logMosharekats;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mosharekat lastMosharekat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SERIAL_ID")
    private Serial serialPrint;

    @OneToMany(mappedBy = "bimename", fetch = FetchType.LAZY)
    private List<Credebit> credebitList;

    @Column(name="BIMENAME_CONVERTED")
    private String converted;

    @Column(name = "has_elhaghie")
    private String hasElhaghie;

    @Column(name = "darkhast_dar_Jaryan_type")
    @Enumerated(EnumType.STRING)
    private Darkhast.DarkhastType darkhastDarJaryanType;


    @Column(name = "AUTO_ARZESG_ALAL_HESAB", nullable = true)
    private Long autoArzeshBazkharidAlalHesab;

    @Column(name = "AUTO_ARZESH_GHATI", nullable = true)
    private Long autoArzeshGhati;

    @Column(name = "AUTO_ANDUKHT_GHATI", nullable = true)
    private Long autoAndukhtGhati;

    @Column(name = "AUTO_ANDUKHT_ALAL_HESAB", nullable = true)
    private Long autoAndukhtAlalHesab;

    @Column(name = "AUTO_TARIKH_MABNA", nullable = true)
    private String autoTarikhMabna;

    @Column(name = "AUTO_ARZESG_ALAL_HESAB_2", nullable = true)
    private Long autoArzeshBazkharidAlalHesab2;

    @Column(name = "AUTO_ARZESH_GHATI_2", nullable = true)
    private Long autoArzeshGhati2;

    @Column(name = "AUTO_ANDUKHT_GHATI_2", nullable = true)
    private Long autoAndukhtGhati2;

    @Column(name = "AUTO_ANDUKHT_ALAL_HESAB_2", nullable = true)
    private Long autoAndukhtAlalHesab2;

    @Column(name = "AUTO_TARIKH_MABNA_2", nullable = true)
    private String autoTarikhMabna2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primitive_pishnehad_id")
    private Pishnehad primitivePishnehad;




    public Bimename() {

    }



    public Bimename(String shomare, String shomareMoshakhase, String shomareSerialePrint, String vaziat, String serial, String tarikhSodour, String tarikhShorou, String tarikhEngheza, State state, String sharayeteKhosusi, EmzaKonande emzaKonandeAval, EmzaKonande emzaKonandeDovom, String readyToShow, DarkhastBazkharid darkhastBazkharid, Long andukhteyeGhatie, Long andukhteyeAlalHesab, Long arzeshBazkharidGhatie, Long arzeshBazkharidAlalHesab, Long mablaghMosharekatDummy, Mosharekat lastMosharekat) {
        this.shomare = shomare;
        this.shomareMoshakhase = shomareMoshakhase;
        this.shomareSerialePrint = shomareSerialePrint;
        this.vaziat = vaziat;
        this.serial = serial;
        this.tarikhSodour = tarikhSodour;
        this.tarikhShorou = tarikhShorou;
        this.tarikhEngheza = tarikhEngheza;
        this.state = state;
        this.sharayeteKhosusi = sharayeteKhosusi;
        this.emzaKonandeAval = emzaKonandeAval;
        this.emzaKonandeDovom = emzaKonandeDovom;
        this.readyToShow = readyToShow;
        this.darkhastBazkharid = darkhastBazkharid;
        this.andukhteyeGhatie = andukhteyeGhatie;
        this.andukhteyeAlalHesab = andukhteyeAlalHesab;
        this.arzeshBazkharidGhatie = arzeshBazkharidGhatie;
        this.arzeshBazkharidAlalHesab = arzeshBazkharidAlalHesab;
        this.mablaghMosharekatDummy = mablaghMosharekatDummy;
        this.lastMosharekat = lastMosharekat;
    }

    public Pishnehad getPrimitivePishnehad()
    {
        return primitivePishnehad;
    }

    public void setPrimitivePishnehad(Pishnehad primitivePishnehad)
    {
        this.primitivePishnehad = primitivePishnehad;
    }

    public Darkhast.DarkhastType getDarkhastDarJaryanType()
    {
        return darkhastDarJaryanType;
    }

    public void setDarkhastDarJaryanType(Darkhast.DarkhastType darkhastDarJaryanType)
    {
        this.darkhastDarJaryanType = darkhastDarJaryanType;
    }

    public String getHasElhaghie()
    {
        return hasElhaghie;
    }

    public void setHasElhaghie(String hasElhaghie)
    {
        this.hasElhaghie = hasElhaghie;
    }

    public String getConverted()
    {
        return converted;
    }

    public void setConverted(String converted)
    {
        this.converted = converted;
    }

    public Long getAmountPreviousMosharekatDummy()
    {
        if(amountPreviousMosharekatDummy == null)
            return 0L;
        return amountPreviousMosharekatDummy;
    }

    public void setAmountPreviousMosharekatDummy(Long amountPreviousMosharekatDummy)
    {
        this.amountPreviousMosharekatDummy = amountPreviousMosharekatDummy;
    }

    public List<Khesarat> getKhesarats()
    {
        List<Khesarat> khesaratList=new ArrayList<Khesarat>();
        for(DarkhastBazkharid db :allDarkhasts)
        {
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT) && db.getState()!=null && db.getState().getId()== 656)
            {
                khesaratList.add(db.getKhesaratI());
                if(darkhastBazkharid.getKhesaratII()!=null)
                    khesaratList.add(db.getKhesaratII());
            }
        }
        return khesaratList;
    }

    public boolean isHaveKhesaratAmraz()
    {
        List<Khesarat> khesaratList=getKhesarats();
        if(khesaratList==null)return false;
        for(Khesarat k : khesaratList)
        {
            if(k.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))return true;
        }
        return false;
    }
    public boolean isHaveKhesaratNaghsOzv()
    {
        List<Khesarat> khesaratList=getKhesarats();
        if(khesaratList==null)return false;
        for(Khesarat k : khesaratList)
        {
            if(k.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))return true;
        }
        return false;
    }

//    public void setKhesarats(List<Khesarat> khesarats)
//    {
//        this.khesarats = khesarats;
//    }

    public Serial getSerialPrint()
    {
        return serialPrint;
    }

    public void setSerialPrint(Serial serialPrint)
    {
        this.serialPrint = serialPrint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getVaziat() {
        return vaziat;
    }

    public void setVaziat(String vaziat) {
        this.vaziat = vaziat;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTarikhSodour() {
        return tarikhSodour;
    }

    public void setTarikhSodour(String tarikhSodour) {
        this.tarikhSodour = tarikhSodour;
    }

    public String getTarikhShorou() {
        return tarikhShorou;
    }

    public void setTarikhShorou(String tarikhShorou) {
        this.tarikhShorou = tarikhShorou;
    }

    public String getTarikhEngheza() {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza) {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getSharayeteKhosusi() {
        return sharayeteKhosusi;
    }

    public void setSharayeteKhosusi(String sharayeteKhosusi) {
        this.sharayeteKhosusi = sharayeteKhosusi;
    }

    public EmzaKonande getEmzaKonandeAval() {
        return emzaKonandeAval;
    }

    public void setEmzaKonandeAval(EmzaKonande emzaKonandeAval) {
        this.emzaKonandeAval = emzaKonandeAval;
    }

    public EmzaKonande getEmzaKonandeDovom() {
        return emzaKonandeDovom;
    }

    public void setEmzaKonandeDovom(EmzaKonande emzaKonandeDovom) {
        this.emzaKonandeDovom = emzaKonandeDovom;
    }

    public Long getAutoArzeshBazkharidAlalHesab() {
        return autoArzeshBazkharidAlalHesab;
    }

    public void setAutoArzeshBazkharidAlalHesab(Long autoArzeshBazkharidAlalHesab) {
        this.autoArzeshBazkharidAlalHesab = autoArzeshBazkharidAlalHesab;
    }

    public Long getAutoArzeshGhati() {
        return autoArzeshGhati != null && autoArzeshGhati >= 0 ? autoArzeshGhati : 0;
    }

    public void setAutoArzeshGhati(Long autoArzeshGhati) {
        this.autoArzeshGhati = autoArzeshGhati;
    }

    public Long getAutoAndukhtGhati() {
        return autoAndukhtGhati!=null && autoAndukhtGhati>=0?autoAndukhtGhati:0;
    }

    public void setAutoAndukhtGhati(Long autoAndukhtGhati) {
        this.autoAndukhtGhati = autoAndukhtGhati;
    }

    public Long getAutoAndukhtAlalHesab() {
        return autoAndukhtAlalHesab != null && autoAndukhtAlalHesab >= 0 ? autoAndukhtAlalHesab : 0;
    }

    public void setAutoAndukhtAlalHesab(Long autoAndukhtAlalHesab) {
        this.autoAndukhtAlalHesab = autoAndukhtAlalHesab;
    }

    public String getAutoTarikhMabna() {
        return autoTarikhMabna;
    }

    public void setAutoTarikhMabna(String autoTarikhMabna) {
        this.autoTarikhMabna = autoTarikhMabna;
    }

    public Pishnehad getPishnehad() {
        if(pishnehadList == null) return null;
        for (Pishnehad p : pishnehadList)
            if (p.getValid()) return p;
        return null;
    }

    public Pishnehad getFirstPishnehad() {
        List<Pishnehad> pishnehadList = getPishnehadList();
        Pishnehad firstPishnehad = null;
        for(Pishnehad p : pishnehadList) {
            if(firstPishnehad == null || DateUtil.isGreaterThan(firstPishnehad.getVersion(), p.getVersion()))
                firstPishnehad = p;
        }
        return firstPishnehad;
    }


    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public String getShomareSerialePrint() {
        return shomareSerialePrint;
    }

    public void setShomareSerialePrint(String shomareSerialePrint) {
        this.shomareSerialePrint = shomareSerialePrint;
    }

    public String getReadyToShow() {
        return readyToShow;
    }

    public void setReadyToShow(String readyToShow) {
        this.readyToShow = readyToShow;
    }

    public List<Elhaghiye> getElhaghiyeha() {
        return elhaghiyeha;
    }

    public List<Elhaghiye> getElhaghiyehaDaem() {
        List<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
        if(getElhaghiyeha() == null) return elhaghiyes;
        for(Elhaghiye e : getElhaghiyeha()) {
            if(e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.TAGHYIRAT && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)) {
                elhaghiyes.add(e);
            }
        }
        return elhaghiyes;
    }

    public List<Elhaghiye> getKoleElhaghiyehaDaem()
    {
        List<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
        for(Elhaghiye e : getElhaghiyeha()) {
            if(e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)) {
                elhaghiyes.add(e);
            }
        }
        return elhaghiyes;
    }

    public List<Elhaghiye> getElhaghiyehaDarJaryan()
    {
        List<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
        for(Elhaghiye e : getElhaghiyeha()) {
            if(e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.TAGHYIRAT) {
                if(e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE) ||
                        (e.getState().getId().equals(Constant.ELHAGHIYE_INITIAL_STATE) && !e.getDarkhast().getDarkhastTaghirat().getFinished())) {
                    elhaghiyes.add(e);
                }
            }
        }
        return elhaghiyes;
    }

    public void setElhaghiyeha(List<Elhaghiye> elhaghiyeha) {
        this.elhaghiyeha = elhaghiyeha;
    }

    public String getHaghBimeBazkharidi()
    {
        long paymentAmount=0;
        long mablagh=0;
        for (GhestBandi gb : ghestBandiList)
        {
            if (gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                for (Ghest g : gb.getGhestList())
                {
                    paymentAmount += g.getCredebit().getAmount_long() - g.getCredebit().getRemainingAmount_long();
                    mablagh += g.getCredebit().getAmount_long();
                }
        }
        return NumberFormat.getInstance().format((mablagh - paymentAmount) * (-1));
    }

    public List<GhestBandi> getGhestBandiList() {
        if(ghestBandiList != null)
            Collections.sort(ghestBandiList);
        return ghestBandiList;
    }

    public void setGhestBandiList(List<GhestBandi> ghestBandiList) {
        this.ghestBandiList = ghestBandiList;
    }

    public int compareTo(Object o) {
        return this.getTarikhSodour().compareTo(((Bimename) o).getTarikhSodour()) * (-1);
    }

    public State getState() {
        return state;
    }

    public String getBardashtCountRemaining(String date)
    {
        List<DarkhastBazkharid> allDarkhasts = this.getAllDarkhasts();
        int numberOfBardashts = 0;
        for (DarkhastBazkharid theDarkhast : allDarkhasts)
        {
            if (theDarkhast.getState() != null && theDarkhast.getState().getId().equals(Constant.BARDASHT_ENSERAF))
                continue;
            if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE) && theDarkhast.getState().getId()==11090 && DateUtil.isGreaterThanOrEqual(date,theDarkhast.getTarikhDarkhast()))
            {
                numberOfBardashts++;
            }
        }
        return String.valueOf(3-numberOfBardashts<0? 0 : 3 - numberOfBardashts);
    }

    public String getBardashtCountRemaining()
    {
        List<DarkhastBazkharid> allDarkhasts = this.getAllDarkhasts();
        int numberOfBardashts = 0;
        for (DarkhastBazkharid theDarkhast : allDarkhasts)
        {
            if (theDarkhast.getState() != null && theDarkhast.getState().getId().equals(Constant.BARDASHT_ENSERAF))
                continue;
            if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE) && theDarkhast.getState().getId()==11090)
            {
                numberOfBardashts++;
            }
        }
        return String.valueOf(3-numberOfBardashts<0? 0 : 3 - numberOfBardashts);
    }

    public String getStateNameFarsiForMosharekat() {
        boolean isMoalagh = false, isMonghazi = false, pasAzMabna = false, isNormalState = false;
        if(stateForMosharekat != null && stateForMosharekat.contains("MOALAGH")) {
            isMoalagh = true;
        }
        if (stateForMosharekat != null && stateForMosharekat.contains("ENGHEZA")) {
            isMonghazi = true;
        }
        if(stateForMosharekat != null && stateForMosharekat.contains("PAS_AZ_MABNA")) {
            pasAzMabna = true;
        }
        if (getState().getId().equals(Constant.BIMENAME_LOCK_STATE) || getState().getId().equals(Constant.BIMENAME_INITIAL_STATE)) {
            isNormalState = true;
        }
        String stateName = "خطا";
        boolean shouldAddMabnaString = true;
        if(isNormalState) {
            if(isMonghazi) {
                // sarresid shodeha (olaviat 2)
                stateName = "بیمه نامه سررسید شده";
            } else if(isMoalagh) {
                // moalagh (olaviat 3)
                stateName = "بیمه نامه معلق";
            } else {
                // daem (olaviat 4)
                stateName = "بیمه نامه دائم";
                shouldAddMabnaString = false;
            }
        } else {
            // ebtal o bazkharid o ina (olaviat 1)
            stateName = getState().getStateName();
        }
        if(shouldAddMabnaString) {
            if(pasAzMabna) {
                stateName = stateName + " پس از تاریخ مبنا";
            } else {
                stateName = stateName + " قبل از تاریخ مبنا";
            }
        }
        return stateName;
    }

    public boolean isMoalagh() {
        return calcAndukhteAlalHesabLong(DateUtil.getCurrentDate()) <= 0L;
    }

    public void setState(State state) {
        this.state = state;
        this.stateChangeDate = DateUtil.getCurrentDate();
    }

    public List<DarkhastBazkharid> getAllDarkhasts() {
        return allDarkhasts;
    }

    public void setAllDarkhasts(List<DarkhastBazkharid> allDarkhasts) {
        this.allDarkhasts = allDarkhasts;
    }

    public List<DarkhastBazkharid> getDarkhastBahreMandiFinal()
    {
        List<DarkhastBazkharid> bahreMandiFinalList= new ArrayList<DarkhastBazkharid>();
        for (DarkhastBazkharid db : allDarkhasts)
        {
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)&& (db.getState().getId()== 10100||db.getState().getId()== 10130))
            {
                bahreMandiFinalList.add(db);
            }
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE)&& db.getState().getId()== 11090)
            {
                bahreMandiFinalList.add(db);
            }
        }
        return bahreMandiFinalList;
    }

    public String getVamDaryaftiJari()
    {
        String rtnVal="-";
        for(DarkhastBazkharid vam : allDarkhasts)
        {
            if(vam.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)&&vam.getState().getId()>= 10100&&vam.getState().getId()!= 10150&&vam.getState().getId()!= 10140)
            {
                rtnVal= vam.getMablagheVamedarkhasti();
            }
        }
        return rtnVal;
    }

    public List<DarkhastBazkharid> getListDarkhastVamFinal()
    {
        List<DarkhastBazkharid> vams=new ArrayList<DarkhastBazkharid>();
        for (DarkhastBazkharid db : this.getAllDarkhasts())
        {
            if (db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && db.getState().getId() >= 10100)
                vams.add(db);
        }
        return vams;
    }

    public String getGhesteVam()
    {
        for (DarkhastBazkharid db : this.getAllDarkhasts())
        {
            if (db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && db.getState().getId() >= 10100)
            {
                return db.getGhestBandi().getGhestList().get(1).getCredebit().getAmount();
            }
        }

        return "-";
    }

    public String getShomareMoshakhase() {
        return shomareMoshakhase;
    }

    public void setShomareMoshakhase(String shomareMoshakhase) {
        this.shomareMoshakhase = shomareMoshakhase;
    }

    public void setAndukhteyeGhatie(Long andukhteyeGhatie) {
        this.andukhteyeGhatie = andukhteyeGhatie;
    }

    public Long getAndukhteyeGhatie() {
        if(!(getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE))) return 0L;
        if(andukhteyeGhatie == null || andukhteyeGhatie < 0) return 0L;
        return andukhteyeGhatie;
    }

    public Long getAutoAndukhteyeGhatie() {
        if(!(getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE))) return 0L;
        if(autoAndukhtGhati == null || autoAndukhtGhati < 0) return 0L;
        return autoAndukhtGhati;
    }

    public Long getAndukhteyeGhatieWithTarikhMabna(String tarikhMabna) {
        // todo: this method needs to be completed
        if(!shouldCalcAndukhte(tarikhMabna)) return 0L;
        if (andukhteyeGhatie == null || andukhteyeGhatie < 0) return 0L;
        return andukhteyeGhatie;
    }

    public Long getAndukhteyeGhatieIgnoreBimenameState() {
        if (andukhteyeGhatie == null || andukhteyeGhatie < 0) return 0L;
        return andukhteyeGhatie;
    }

    public Long getAutoAndukhteyeGhatieIgnoreBimenameState() {
        if (autoAndukhtGhati == null || autoAndukhtGhati < 0) return 0L;
        return autoAndukhtGhati;
    }

    public Long getAndukhteyeAlalHesabIgnoreBimenameState() {
        if(andukhteyeAlalHesab == null || andukhteyeAlalHesab < 0) return 0L;
        return andukhteyeAlalHesab;
    }

    public Long getAndukhteyeAlalHesab() {
        if (!(getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE)))
            return 0L;
        if (andukhteyeAlalHesab == null || andukhteyeAlalHesab < 0) return 0L;
        return andukhteyeAlalHesab;
    }

    public Long getAndukhteyeAlalHesabWithTarikhMabna(String tarikhMabna) {
        // todo: this method needs to be completed
        if (!shouldCalcAndukhte(tarikhMabna)) return 0L;
        if (andukhteyeAlalHesab == null || andukhteyeAlalHesab < 0) return 0L;
        return andukhteyeAlalHesab;
    }

    public void setAndukhteyeAlalHesab(Long andukhteyeAlalHesab) {
        this.andukhteyeAlalHesab = andukhteyeAlalHesab;
    }

    public Long getEtebarVam(String tarikhMabna)
    {
        for (DarkhastBazkharid db : this.getAllDarkhasts())
        {
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM) && DateUtil.isGreaterThanOrEqual(tarikhMabna,db.getTarikhDarkhast())&& db.getState().getId() >= 10100&& db.getState().getId() != 10140&& db.getState().getId() != 10150)
            {
                for(Ghest g : db.getGhestBandi().getGhestList())
                {
                    if(g.getCredebit().getRemainingAmount_long()>0)
                        return 0l;
                }
            }

        }
       return (long) (Math.max(0.9 * arzeshBazkharidGhatie, 0));
    }

    public Long getArzeshBazkharidGhatie() {
        if(!(getState() != null && (getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE)))) return 0L;
//        if(arzeshBazkharidGhatie == null || arzeshBazkharidGhatie < 0) return 0L;
        if(arzeshBazkharidGhatie == null) return 0L;
        return arzeshBazkharidGhatie;
    }

    public Long getAutoArzeshBazkharidGhatie() {
        if(!(getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE))) return 0L;
//        if(arzeshBazkharidGhatie == null || arzeshBazkharidGhatie < 0) return 0L;
        if(autoArzeshGhati == null) return 0L;
        return autoArzeshGhati;
    }

    public Long getArzeshBazkharidGhatieWithTarikhMabna(String tarikhMabna) {
        // todo: this method needs to be completed
        if (!shouldCalcAndukhte(tarikhMabna)) return 0L;
        if (arzeshBazkharidGhatie == null || arzeshBazkharidGhatie < 0) return 0L;
        return arzeshBazkharidGhatie;
    }

    public Long getArzeshBazkharidGhatieIgnoreBimenameState() {
//        if (arzeshBazkharidGhatie == null || arzeshBazkharidGhatie < 0) return 0L;
        if (arzeshBazkharidGhatie == null) return 0L;
        return arzeshBazkharidGhatie;
    }

    public Long getAutoArzeshBazkharidGhatieIgnoreBimenameState() {
//        if (arzeshBazkharidGhatie == null || arzeshBazkharidGhatie < 0) return 0L;
        if (autoArzeshGhati == null) return 0L;
        return autoArzeshGhati;
    }

    public void setArzeshBazkharidGhatie(Long arzeshBazkharidGhatie) {
        this.arzeshBazkharidGhatie = arzeshBazkharidGhatie;
    }

    public Long getArzeshBazkharidAlalHesab() {
        if(!(getState().getId().equals(Constant.BIMENAME_INITIAL_STATE) || getState().getId().equals(Constant.BIMENAME_LOCK_STATE))) return 0L;
        if(arzeshBazkharidAlalHesab == null || arzeshBazkharidAlalHesab < 0) return 0L;
        return arzeshBazkharidAlalHesab;
    }

    public void setArzeshBazkharidAlalHesab(Long arzeshBazkharidAlalHesab) {
        this.arzeshBazkharidAlalHesab = arzeshBazkharidAlalHesab;
    }

    public Long getMablaghMosharekatDummy() {
        return mablaghMosharekatDummy == null ? 0 : mablaghMosharekatDummy;
    }

    public void setMablaghMosharekatDummy(Long mablaghMosharekatDummy) {
        this.mablaghMosharekatDummy = mablaghMosharekatDummy;
    }

    public List<LogMosharekat> getLogMosharekats() {
        return logMosharekats;
    }

    public void setLogMosharekats(List<LogMosharekat> logMosharekats) {
        this.logMosharekats = logMosharekats;
    }

    public Mosharekat getLastMosharekat() {
        return lastMosharekat;
    }

    public void setLastMosharekat(Mosharekat lastMosharekat) {
        this.lastMosharekat = lastMosharekat;
    }

    public String getPooshesh_moafiatFarsi() {
        return getPooshesh_moafiat() ? "دارد" : "ندارد";
    }

    public boolean getPooshesh_moafiat() {
        try {
            return getPishnehad().getEstelam().getPooshesh_moafiat().equals("yes");
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean getPooshesh_hadese() {
        try {
            return getPishnehad().getEstelam().getPooshesh_fot_dar_asar_haadese().equals("yes");
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean getPooshesh_naghsOzv() {
        try {
            return getPishnehad().getEstelam().getPooshesh_naghs_ozv().equals("yes");
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean getPooshesh_amraz() {
        try {
            return getPishnehad().getEstelam().getPooshesh_amraz_khas().equals("yes");
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean getPooshesh_amraz_forKhesarat()
    {
        if(getPooshesh_amraz())
        {
            if(getKhesarats()!=null && getKhesarats().size()>0)
            {
                long sarmaye=0l;
                long koleBardashti=0l;
                for(Khesarat kh : getKhesarats())
                {
                   if(kh.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                   {
                       if(sarmaye==0l)
                           sarmaye=kh.getSarmayeAmraz();
                       koleBardashti+=Long.parseLong(kh.getAmountTaidShode().trim().replaceAll(",",""));

                       if((sarmaye-koleBardashti)<=0l)
                           return false;
                   }
                }
            }
            return true;
        }
        return false;
    }

    public boolean getPooshesh_naghsOzv_forKhesarat()
    {
        if(getPooshesh_naghsOzv())
        {
            if(getKhesarats()!=null && getKhesarats().size()>0)
            {
                long sarmaye=0l;
                long koleBardashti=0l;
                for(Khesarat kh : getKhesarats())
                {
                   if(kh.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                   {
                       if(sarmaye==0l)
                           sarmaye=kh.getSarmayeNaghseOzv();
                       koleBardashti+=Long.parseLong(kh.getAmountTaidShode().trim().replaceAll(",",""));

                       if((sarmaye-koleBardashti)<=0l)
                           return false;
                   }
                }
            }
            return true;
        }
        return false;
    }



    public Integer getTedadAghsatPardakhtNashode() {
        int count = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        if (DateUtil.isGreaterThan(now, g.getSarresidDate()) && !g.getCredebit().getRemainingAmount().equals("0"))
                            count++;
                } catch (Exception ignore) {
                }
            }
        }
        return count;
    }

    public Integer getTedadVam() {
        int count = 0;
//        for (Elhaghiye e : elhaghiyeha)
//            if (e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.VAM))
//                count++;
        for (DarkhastBazkharid vam : allDarkhasts)
            if (vam.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
                count++;
        return count;
    }

    public Integer getTedadAghsatVamPardakhtNashode() {
        int count = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
                        if (DateUtil.isGreaterThan(now, g.getSarresidDate()) && !g.getCredebit().getRemainingAmount().equals("0"))
                            count++;
                } catch (Exception ignore) {
                }
            }
        }
        return count;
    }

    public Long getMajmuAghsat() {
        long mablagh = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        mablagh += Long.valueOf(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return mablagh;
    }

    public Long getMajmuAghsatPardakhtNashode() {
        long mablagh = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        if (DateUtil.isGreaterThan(now, g.getSarresidDate()))
                            mablagh += Long.valueOf(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return mablagh;
    }

    public Long getMajmuAghsatPardakhtShode() {
        long mablagh = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        if (DateUtil.isGreaterThan(now, g.getSarresidDate()))
                            mablagh += g.getCredebit().getAmount_long()-g.getCredebit().getRemainingAmount_long();
                } catch (Exception ignore) {
                }
            }
        }
        return mablagh;
    }

    public String getNahvePardakhtVam() {
        String tmp = "";
        Collections.sort(elhaghiyeha);
        for (Elhaghiye e : elhaghiyeha) {
            if (e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.VAM)) {
                return e.getDarkhastBazkharid().getNahveyePardakhteAghsatFarsi();
            }

        }
        return tmp;
    }

    public String getModatBazPardakhtVam() {
        String tmp = "";
        Collections.sort(elhaghiyeha);
        for (Elhaghiye e : elhaghiyeha) {
            if (e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.VAM)) {
                return e.getDarkhastBazkharid().getModatebazpardakht();
            }

        }
        return tmp;
    }

    public Long getMajmuAghsatVamPardakhtShode() {
        long mablagh = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
                        if (g.getCredebit().getRemainingAmount().equals("0"))
                            mablagh += Long.valueOf(g.getCredebit().getAmount().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return mablagh;
    }

    public Long getMajmuAghsatVamPardakhtNashode() {
        long mablagh = 0;
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
                        if (DateUtil.isGreaterThan(now, g.getSarresidDate()))
                            mablagh += Long.valueOf(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return mablagh;
    }

    public String calcArzeshBazkharidi() {
        return NumberFormat.getNumberInstance().format(Math.round(Long.parseLong(calcAndukhteAlalHesab(DateUtil.getCurrentDate()).replace(",", "").trim()) * 0.9));
    }

    public String calcAndukhteGhatie() {
        return calcAndukhteGhatie(DateUtil.getCurrentDate());
    }

    public String getAndukhteEmroozCalculate() {
        return calcAndukhteGhatie();
    }

    public String calcAndukhteGhatie(String _date) {
        Long andukhte = Long.parseLong(calcAndukhteAlalHesab(_date).replace(",", "").trim()) + getMosharekatUpTo(_date);
        if(andukhte < 0) andukhte = 0L;
        return NumberFormat.getNumberInstance().format(andukhte);
    }

    public CashFlow getCashFlow(String _date) {
        List<Flow> flows = new ArrayList<Flow>();
        for (Credebit credebit : credebitList) {
            if (credebit.getCredebitType().equals(Credebit.CredebitType.GHEST)) {
                if (DateUtil.isGreaterThanOrEqual(_date, credebit.getGhest().getSarresidDate())) {
                    double amount = credebit.getGhest().getMajmooHazineha();
                    String date = credebit.getGhest().getSarresidDate();
                    Flow flow = new Flow(amount, Flow.Type.GHEST, date);
                    flows.add(flow);
                }
                if (!credebit.getRemainingAmount().equals(credebit.getAmount())) {
                    List<KhateSanad> khateSanads = credebit.getKhateSanadsBedehi();
                    for (KhateSanad khateSanad : khateSanads) {
                        if(!khateSanad.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR))
                        {
                            double positiveAmount = Double.parseDouble(khateSanad.getAmount().replaceAll(",", ""));
                            if (DateUtil.isGreaterThanOrEqual(_date, khateSanad.getSanad().getCreatedDate())) {
                                String positiveDate = khateSanad.getEtebarCredebit().getDateFish();
                                if (positiveDate == null || positiveDate.length() != 10) {
                                    positiveDate = khateSanad.getEtebarCredebit().getCreatedDate();
                                }
                                Flow positiveFlow = new Flow(positiveAmount, Flow.Type.PARDAKHT_GHEST, positiveDate);
                                flows.add(positiveFlow);
                            }
                        }
                    }
                }
            } else if(credebit.getCredebitType().equals(Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT)) {
                if (DateUtil.isGreaterThanOrEqual(_date, credebit.getDateFish())) {
                    Flow flow = new Flow(credebit.getAmount_long(), Flow.Type.HAZINEHA, credebit.getDateFish());
                    flows.add(flow);
                }
            }
        }
        return new CashFlow(flows);
    }

    public String calcAndukhteAlalHesab(String _date) {
        return NumberFormat.getNumberInstance().format(Math.round(getCashFlow(_date).tajmi(this, _date, CashFlow.SOUD)));
    }

    public Long calcAndukhteAlalHesabLong(String _date) {
        return Math.round(getCashFlow(_date).tajmi(this, _date, CashFlow.SOUD));
    }

    public String getMablaghElhaghieBargashti() {
        return NumberFormat.getNumberInstance().format(Math.round(getMajmuAghsatPardakhtNashode() + getMajmuAghsatVamPardakhtNashode()));
    }

    public Long getHazineGhabeleKasrTaPayanSal(String _date) {
        int bimenameYear = DateUtil.getBimeYear(_date,this.tarikhShorou);
        long mablagh = 0;
        for (GhestBandi gb : ghestBandiList) {
            if(gb.getSaleBimeiInt()==bimenameYear && gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                for(Ghest g : gb.getGhestList())
                {
                    if(DateUtil.isGreaterThan(g.getSarresidDate(),_date))
                        mablagh+=g.getMajmooHazineha();
                }
        }
        return mablagh;
    }

    public List<Credebit> getBedehiList() {
        List<Credebit> list = new LinkedList<Credebit>();
        final String now = DateUtil.getCurrentDate();
        for (GhestBandi gb : ghestBandiList) {
            for (Ghest g : gb.getGhestList()) {
                try {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM) || g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        if (!g.getCredebit().getRemainingAmount().equals("0"))
                            list.add(g.getCredebit());
                } catch (Exception ignore) {
                }
            }
        }
        return list;
    }

    public boolean isHaveKhesaratAmrazOrNaghs()
    {
        if (getKhesarats()==null) return false;
        for(Khesarat khesarat : getKhesarats())
        {
            if(khesarat.getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ)||khesarat.getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                return true;
        }
        return false;
    }

    public boolean isVameTasvieNashode()
    {
        for (GhestBandi gb : ghestBandiList)
        {
            for (Ghest g : gb.getGhestList())
            {
                if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM) && g.getCredebit().getRemainingAmount_long()>0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public List<DarkhastBazkharid> getDarkhastBardashtAzAndukhteFinal()
    {
        List<DarkhastBazkharid> bardashtList= new ArrayList<DarkhastBazkharid>();
        for(DarkhastBazkharid db : allDarkhasts)
        {
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE) && db.getState().getId().equals(Constant.BARDASHT_AZ_ANDOKHTE_FINAL_STATE))
                bardashtList.add(db);
        }
        return bardashtList.size()>0?bardashtList:null;
    }

    public DarkhastBazkharid getLastBardasht()
    {
        DarkhastBazkharid lastBardasht = null;
        if (getDarkhastBardashtAzAndukhteFinal()!=null)
        for(DarkhastBazkharid db : getDarkhastBardashtAzAndukhteFinal())
        {
            if(db.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE) && db.getState().getId().equals(Constant.BARDASHT_AZ_ANDOKHTE_FINAL_STATE))
            {
                if (lastBardasht == null)
                {
                    lastBardasht = db;
                }
                else
                {
                    if (lastBardasht.compareTo(db) == 1)
                        lastBardasht = db;
                }
            }
        }
        return lastBardasht;
    }

    public String getMablaghBardashtShodeAzAndukhte()
    {
        Long totalAmount=0l;
        List<DarkhastBazkharid> bardashtList=getDarkhastBardashtAzAndukhteFinal();
        if(bardashtList==null) return "0";
        for(DarkhastBazkharid bardasht : bardashtList)
        {
            totalAmount+=Long.parseLong(bardasht.getMablaghDarkhastiBardasht().replaceAll(",","").trim());
        }
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getMablaghBardashtShodeAzAndukhte(String tarikhMabna)
    {
        Long totalAmount=0l;
        List<DarkhastBazkharid> bardashtList=getDarkhastBardashtAzAndukhteFinal();
        if(bardashtList==null) return "0";
        for(DarkhastBazkharid bardasht : bardashtList)
        {
            if(DateUtil.isGreaterThanOrEqual(tarikhMabna,bardasht.getTarikhDarkhast()))
                totalAmount+=Long.parseLong(bardasht.getMablaghDarkhastiBardasht().replaceAll(",","").trim());
        }
        return NumberFormat.getInstance().format(totalAmount);
    }

    public boolean isPeriodBardashtAzAndukhteOk()
    {
        DarkhastBazkharid lastBardasht=null;
        for(DarkhastBazkharid bardasht : allDarkhasts)
        {
            if(bardasht.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE) && bardasht.getState().getId().equals(Constant.BARDASHT_AZ_ANDOKHTE_FINAL_STATE))
            {
                if(lastBardasht==null)
                {
                    lastBardasht=bardasht;
                }
                else
                {
                    if(lastBardasht.compareTo(bardasht)==1)
                    {
                        lastBardasht=bardasht;
                    }
                }
            }
        }
        return lastBardasht==null ? true : DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(),DateUtil.addYear(lastBardasht.getTarikhDarkhast(),3));
    }

    public String getAmrazStartDateFirst()
    {
        GhestBandi firstGbAmraz=null;
        for(GhestBandi gb : ghestBandiList)
        {
            if(gb.getType()== GhestBandi.Type.G_BIMENAME)
            {
                if (gb.getHaghBimePoosheshAmraz_long()!=null && gb.getHaghBimePoosheshAmraz_long()>0)
                {
                    if(firstGbAmraz==null)
                        firstGbAmraz=gb;
                    else
                    {
                        if(gb.compareTo(firstGbAmraz)==-1)
                        {
                            firstGbAmraz=gb;
                        }
                    }
                }
            }
        }

        return DateUtil.addYear(this.tarikhSodour,firstGbAmraz.getSaleBimeiInt());
    }

    //check 3 month past after start poosheshAmraz
    public boolean isTimeWaitingAmraz(String date)
    {
        if(date==null || date.isEmpty())
            date= DateUtil.getCurrentDate();
        return DateUtil.isGreaterThan(DateUtil.addMonth(getAmrazStartDateFirst(),3), date);
    }


//    public User getUserKartabl() {
//        return userKartabl;
//    }
//
//    public void setUserKartabl(User userKartabl) {
//        this.userKartabl = userKartabl;
//    }

    public Integer getCurrentSaleBimei() {
        int sal = DateUtil.getBimeYear(DateUtil.getCurrentDate(), getTarikhShorou());
        // age bimename hanuz shoro nashode bashe, bejaye -1 0 midim
        if(sal == -1)
            sal = 0;
        return sal;

    }

    public List<Pishnehad> getPishnehadList() {
        return pishnehadList;
    }

    public void setPishnehadList(List<Pishnehad> pishnehadList) {
        this.pishnehadList = pishnehadList;
    }

//    public long getSarmayeFotSaleAvval() {
//        Pishnehad firstPishnehad = getPishnehad();
//        for(Pishnehad p : getPishnehadList()) {
//            if(DateUtil.isGreaterThan(firstPishnehad.getVersion(), p.getVersion())) {
//                firstPishnehad = p;
//            }
//        }
//        for(Elhaghiye e : getElhaghiyeha()) {
//            if(e.getTarikhAsar()!=null && e.getTarikhAsar().equals(getTarikhShorou()) && e.getState().getId().equals(Constant.EL)) {
//                firstPishnehad = e.getDarkhast().getDarkhastTaghirat().getNewPishnehad();
//            }
//        }
//        return Long.parseLong(firstPishnehad.getEstelam().getSarmaye_paye_fot().replaceAll(",",""));
//    }

    public Pishnehad getFirstPishnehadWithElhaghie()
    {
        Pishnehad firstPishnehad = getPishnehad();
        for (Pishnehad p : getPishnehadList())
        {
            if (DateUtil.isGreaterThan(firstPishnehad.getVersion(), p.getVersion()))
            {
                firstPishnehad = p;
            }
        }
        if(getElhaghiyeha() != null) {
            for (Elhaghiye e : getElhaghiyeha())
            {
                if (e.getTarikhAsar() != null && e.getTarikhAsar().equals(getTarikhShorou()) && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)
                        && e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.TAGHYIRAT)
                {
                    if(e.getDarkhast().getDarkhastTaghirat().getNewPishnehad() != null)
                        firstPishnehad = e.getDarkhast().getDarkhastTaghirat().getNewPishnehad();
                }
            }
        }
        return firstPishnehad;
    }

    public boolean isHaveElTaghiratToFirstYear()
    {
        for(Elhaghiye el : getElhaghiyehaDaem())
        {
            if(el.getSaleBimeiAsar()==0) return true;
        }
        return false;
    }

    public Elhaghiye getElTaghiratToFirstYear()
    {
        for(Elhaghiye el : getElhaghiyehaDaem())
        {
            if(el.getTarikhAsar().equals(this.getTarikhShorou()))return el;
        }
        return null;
    }

    public String getHaghBimeSaleAval()
    {
        long haghBimeAval=0;
        for(GhestBandi gb : ghestBandiList)
        {
            if(gb.getSaleBimeiInt().equals(0))
            {
                haghBimeAval=gb.getMajmooeAmount();
            }
        }
        return NumberFormat.getInstance().format(haghBimeAval);
    }

    public String getMaliatSaleAval()
    {
        long maliat=0;
        for(GhestBandi gb : ghestBandiList)
        {
            if(gb.getSaleBimeiInt().equals(0))
            {
                for(Ghest g : gb.getGhestList())
                {
                    maliat +=Long.parseLong(g.getMaliat().replaceAll(",","").trim());
                }
                break;
            }
        }
        return NumberFormat.getInstance().format(maliat);
    }

    public String getHaghBimePosheshEzafiSaleAval()
    {
        long hbpe=0;
        for(GhestBandi gb : ghestBandiList)
        {
            if(gb.getSaleBimeiInt().equals(0))
            {
                for(Ghest g : gb.getGhestList())
                {
                    hbpe +=Long.parseLong(g.getHaghBimePoosheshhayeEzafi().replaceAll(",","").trim());
                }
                break;
            }
        }
        return NumberFormat.getInstance().format(hbpe);
    }

    public List<Credebit> getCredebitList() {
        return credebitList;
    }

    public void setCredebitList(List<Credebit> credebitList) {
        this.credebitList = credebitList;
    }

    public String getJameMabaleghePardakhti()
    {
        List<GhestBandi> gbList = getGhestBandiList();
        Double total = 0.0;
        Double kolleMablagh = 0.0;
        for (GhestBandi gb : gbList)
        {
            for (Ghest g : gb.getGhestList())
            {
                total = total + Double.parseDouble(g.getCredebit().getRemainingAmount().replace(",",""));
                kolleMablagh = kolleMablagh + Double.parseDouble(g.getCredebit().getAmount().replace(",",""));
            }
        }
        return NumberFormat.getInstance().format(kolleMablagh - total);
    }

    public String getJameElamBeMali()
    {
        List<GhestBandi> gbList = getGhestBandiList();
        Double kolleMablagh = 0.0;
        for (GhestBandi gb : gbList)
        {
            for (Ghest g : gb.getGhestList())
            {
                kolleMablagh = kolleMablagh + Double.parseDouble(g.getCredebit().getAmount().replace(",",""));
            }
        }
        return NumberFormat.getInstance().format(kolleMablagh);
    }

    public String   getTarikhEbtal() {
        String returnValue = getTarikhShorou();
        if(getState().getId().equals(Constant.BIMENAME_EBTAL)){
            for(Elhaghiye e : getElhaghiyeha()) {
                if(e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.EBTAL && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)) {
                    returnValue = e.getCreatedDate();
                }
            }
        }
        return returnValue;
    }

    public String getTarikhBazkharid() {
        String returnValue = getTarikhShorou();
        if(getState().getId().equals(Constant.BIMENAME_BAZKHARID)){
            for(Elhaghiye e : getElhaghiyeha()) {
                if(e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.BAZKHARID && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)) {
                    returnValue = e.getCreatedDate();
                }
            }
        }
        return returnValue;
    }

    public String getTarikhBasteShodan() {
        String returnValue = getTarikhShorou();
        if (getState().getId().equals(Constant.BIMENAME_BASTE)) {
            for (DarkhastBazkharid d : getAllDarkhasts()) {
                if (d.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT)) {
                    if(d.getKhesaratI() != null && d.getKhesaratI().getElhaghiye() != null
                            && (d.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.OMR) || d.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.HADESE)))
                    {
                        returnValue = d.getKhesaratI().getAccidentDate();
                    }
                    else if (d.getKhesaratII() != null && d.getKhesaratII().getElhaghiye() != null
                            && (d.getKhesaratII().getKhesaratType().equals(Khesarat.KhesaratType.OMR) || d.getKhesaratII().getKhesaratType().equals(Khesarat.KhesaratType.HADESE))) {
                        returnValue = d.getKhesaratII().getAccidentDate();
                    }
                }
            }
        }
        return returnValue;
    }

    public String getSalBimeiBazkharid() {
        Integer temp = DateUtil.getBimeYear(getTarikhBazkharid(),getTarikhSodour()) + 1;
        return temp.toString();
    }

    public String getTarikhEbtalForKrmzd()
    {
        String returnValue = null ;
        if (getState().getId().equals(Constant.BIMENAME_EBTAL))
        {
            for (Elhaghiye e : getElhaghiyeha())
            {
                if (e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.EBTAL && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE))
                {
                    returnValue = e.getCreatedDate();
                }
            }
        }
        return returnValue;
    }

    public String getTarikhBazkharidForKrmzd()
    {
        String returnValue = null;
        if (getState().getId().equals(Constant.BIMENAME_BAZKHARID))
        {
            for (Elhaghiye e : getElhaghiyeha())
            {
                if (e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.BAZKHARID && e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE))
                {
                    returnValue = e.getCreatedDate();
                }
            }
        }
        return returnValue;
    }

    public String getTarikhFaskh() {
        String returnValue = getTarikhShorou();
        if(getState().getId().equals(Constant.BIMENAME_FASKH)) {
            for(Elhaghiye e : getElhaghiyehaDaem()) {
                if(e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.FASKH) {
                    returnValue = e.getCreatedDate();
                }
            }
        }
        return returnValue;
    }

    public String getMosharekatKol() {
        Long tmp = 0L;
        for (Credebit c : getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThanOrEqual(DateUtil.getCurrentDate(), c.getDateFish()))
                tmp += Long.parseLong(c.getAmount().replaceAll(",",""));
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public String getMablaghBazkharid() {
        Long tmp = 0L;
        for (Credebit c : getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.AZ_MAHALLE_BAZKHARID)
                return NumberFormat.getNumberInstance().format(c.getAmount_long());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public String getMablaghBazkharidIgnoreManfi() {
        Long resultLong = 0l;
        for (Credebit c : getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.AZ_MAHALLE_BAZKHARID)
                resultLong = c.getAmount_long();
        }
        if (resultLong < 0)
            resultLong = 0l;
        return NumberFormat.getNumberInstance().format(resultLong);
    }

    public String getMosharekatKol(String upTo) {
            Long tmp = 0L;
            for (Credebit c : getCredebitList()) {
                if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThan(upTo, c.getDateFish()))
                    tmp += Long.parseLong(c.getAmount().replaceAll(",",""));
            }
            return NumberFormat.getNumberInstance().format(tmp);
        }

    public Long getMosharekatKolLong() {
        return Long.parseLong(getMosharekatKol().replaceAll(",",""));
    }

    public Long getMosharekatKolLongUpTo(String date) {
        return Long.parseLong(getMosharekatKol(date).replaceAll(",",""));
    }

    public Long getSoudMosharekatUpToNow() {
        return getSoudMosharekatUpTo(DateUtil.getCurrentDate());
    }

    public Long getSoudMosharekatUpTo(String date) {
            CashFlow cashflow = new CashFlow();
            ArrayList<Flow> flows = new ArrayList<Flow>();
            for (Credebit c : getCredebitList()) {
                if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThan(date, c.getDateFish())) {
                    Flow f = new Flow(Long.parseLong(c.getAmount().replaceAll(",", "")), Flow.Type.MOSHAREKAT, c.getDateFish());
                    flows.add(f);
                }
            }
            cashflow.setFlows(flows);
            return Math.round(cashflow.tajmi(this, date, CashFlow.SOUD)) - getMosharekatKolLongUpTo(date);
        }

    public Long getMosharekatUpTo(String date) {
        CashFlow cashflow = new CashFlow();
        ArrayList<Flow> flows = new ArrayList<Flow>();
        for (Credebit c : getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.MOSHAREKAT && DateUtil.isGreaterThanOrEqual(date, c.getDateFish())) {
                Flow f = new Flow(Long.parseLong(c.getAmount().replaceAll(",", "")), Flow.Type.MOSHAREKAT, c.getDateFish());
                flows.add(f);
            }
        }
        cashflow.setFlows(flows);
        return Math.round(cashflow.tajmi(this, date, CashFlow.SOUD));
    }

    public Long getMosharekatUpToNow() {
        return getMosharekatUpTo(DateUtil.getCurrentDate());
    }

    public boolean shouldSabtBimenameInMosharekat(long andukhteEmrooz) {
        boolean returnValue = true;
        if(getState().getId().equals(Constant.BIMENAME_EBTAL)) {
            returnValue = false;
        }
        else if(getState().getId().equals(Constant.BIMENAME_BAZKHARID)) {
            returnValue = false;
        }
        else if(getState().getId().equals(Constant.BIMENAME_FASKH)) {
            returnValue = false;
        }
//        else if(isMoalagh()) {
        else if(andukhteEmrooz <= 0) {
            returnValue = false;
        }
        return returnValue;
    }

    public boolean shouldCalcAndukhteInMosharekat(String tarikhPayanDowre, long andukhteInPayanDowre, long andukhteEmrooz) {
        boolean returnValue = true;
        if(getState().getId().equals(Constant.BIMENAME_EBTAL)) {
            if(DateUtil.isGreaterThanOrEqual(tarikhPayanDowre, getTarikhEbtal())) {
                returnValue = false;
            }
        }
        else if(getState().getId().equals(Constant.BIMENAME_BAZKHARID)) {
            if(DateUtil.isGreaterThanOrEqual(tarikhPayanDowre, getTarikhBazkharid())) {
                returnValue = false;
            }
        }
        else if(getState().getId().equals(Constant.BIMENAME_FASKH)) {
            if(DateUtil.isGreaterThanOrEqual(tarikhPayanDowre, getTarikhFaskh())) {
                returnValue = false;
            }
        }
//        else if(isMoalagh()) {
        else if(andukhteEmrooz <= 0) {
            if(andukhteInPayanDowre <= 0) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public boolean shouldCalcAndukhte(String tarikhMabna) {
        boolean returnValue = true;
        if (getState().getId().equals(Constant.BIMENAME_EBTAL)) {
            if (DateUtil.isGreaterThanOrEqual(tarikhMabna, getTarikhEbtal())) {
                returnValue = false;
            }
        } else if (getState().getId().equals(Constant.BIMENAME_BAZKHARID)) {
            if (DateUtil.isGreaterThanOrEqual(tarikhMabna, getTarikhBazkharid())) {
                returnValue = false;
            }
        } else if (getState().getId().equals(Constant.BIMENAME_FASKH)) {
            if (DateUtil.isGreaterThanOrEqual(tarikhMabna, getTarikhFaskh())) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public String getStateForMosharekatFa()
    {
        if(stateForMosharekat==null)
            return "";
        if(stateForMosharekat.equals("MOALAGH_PAS_AZ_MABNA"))
            return "معلق پس از مبنا";
        if(stateForMosharekat.equals("MOALAGH_BIMENAME"))
            return "معلق قبل از مبنا";
        else
            return ""; //others. . .

    }
    public String getStateForMosharekat() {
        return stateForMosharekat;
    }

    public void setStateForMosharekat(String stateForMosharekat) {
        this.stateForMosharekat = stateForMosharekat;
    }

    public Elhaghiye getElhaghieBazkharid()
    {
        for(Elhaghiye e : this.getElhaghiyeha())
        {
            if(e.getDarkhastBazkharid()!=null) return e;
        }
        return null;
    }

    public Elhaghiye getElhaghieOnlyBazkharidType()
    {
        for(Elhaghiye e : this.getElhaghiyeha())
        {
            if(e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.BAZKHARID)) return e;
        }
        return null;
    }

    public String getSenBimeShodeBazkharid(){
        try{
        if (getPishnehad() != null && getPishnehad().getBimeShode() != null && getPishnehad().getBimeShode().getShakhs() != null && getElhaghieBazkharid() != null){
            String senBimeshode = getPishnehad().getBimeShode().getShakhs().getTarikheTavallod();
            String tarikhBazkharid = getElhaghieBazkharid().getTarikhAsar();
            return String.valueOf((int) Math.ceil(DateUtil.getBimeYear(tarikhBazkharid, senBimeshode)));
        }
        }catch (Exception e){
            System.out.println();
        }
        return "-";
    }

    public Namayande getVahedSodor()
    {
        return vahedSodor;
    }

    public void setVahedSodor(Namayande vahedSodor)
    {
        this.vahedSodor = vahedSodor;
    }

    public String getShouldSabtInMosharekat() {
        return shouldSabtInMosharekat;
    }

    public void setShouldSabtInMosharekat(String shouldSabtInMosharekat) {
        this.shouldSabtInMosharekat = shouldSabtInMosharekat;
    }

    public Elhaghiye getElhaghieEbtalOrBazkharid()
    {
        if(this.state.getId().equals(540) || this.state.getId().equals(550))
        {
            for (Elhaghiye e : getKoleElhaghiyehaDaem())
            {
                if(e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.EBTAL) || e.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.BAZKHARID))
                    return e;
            }
        }
        return null;
    }

    public Long getKarmozdBimename() {
        return karmozdBimename;
    }

    public void setKarmozdBimename(Long karmozdBimename) {
        this.karmozdBimename = karmozdBimename;
    }

    public String updateArzesheBimename(String date, double soud){
        try {
            SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
            String s = InsuranceServiceFactory.getAsnadeSodorService(sessionFactory).updateArzesheBimename(date, soud, getId());
            InsuranceServiceFactory.tearDownSessionFactory(sessionFactory);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String updateArzesheBimenameTajmi(String date, double soud, List<Integer> bimenameIds){
        try {
            SessionFactory sessionFactory = InsuranceServiceFactory.setUpSessionFactory();
            String s = InsuranceServiceFactory.getAsnadeSodorService(sessionFactory).updateArzesheBimenameTajmi(date, soud, bimenameIds);
            InsuranceServiceFactory.tearDownSessionFactory(sessionFactory);
            return s;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";

    }

    public Integer getBimeYearWhenEbtlOrBzkhrd()
    {
        Elhaghiye e = getElhaghieEbtalOrBazkharid();
        if(e!=null)
            return DateUtil.getBimeYear(e.getCreatedDate(), getTarikhShorou());
        return null;
    }

    public List<Credebit> getAghsatMoavagh()
    {
        List<Credebit> credebitMoavaghList= new ArrayList<Credebit>();
        for (GhestBandi gb : ghestBandiList)
        {
            for (Ghest g : gb.getGhestList())
            {
                    if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), g.getSarresidDate()) && !g.getCredebit().getRemainingAmount().equals("0"))
                            credebitMoavaghList.add(g.getCredebit());
            }
        }
        return credebitMoavaghList;
    }

    public List<GhestBandi> getGhestBandiListOfBimename()
    {
        List<GhestBandi> gbList=new ArrayList<GhestBandi>();
        if (ghestBandiList==null)
            return null;
        for (GhestBandi gb : ghestBandiList)
        {
            if (gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                gbList.add(gb);
        }
        return gbList;
    }

    public boolean isThereYearTaghsitNashode()
    {
        int bimeYear=DateUtil.getBimeYear(DateUtil.getCurrentDate(),this.getTarikhShorou());
        if(this.getGhestBandiListOfBimename().size() < bimeYear+1)
            return true;
        return false;
    }

    public String getAmountMoavagh()
    {
        Long amount=0l;
        for (GhestBandi gb : ghestBandiList)
        {
            for (Ghest g : gb.getGhestList())
            {
                if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                    if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), g.getSarresidDate()) && g.getCredebit().getRemainingAmount_long()>0)
                        amount+=g.getCredebit().getAmount_long();
            }
        }
        return NumberFormat.getInstance().format(amount);
    }

    public boolean getHasGhestMoavagh()
    {
        for (GhestBandi gb : ghestBandiList)
        {
            for (Ghest g : gb.getGhestList())
            {
                if (g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                    if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), g.getSarresidDate()) && g.getCredebit().getRemainingAmount_long()>0)
                        return true;

            }
        }
        return false;
    }

    public String getHashieString(Integer countReport)
    {
        return countReport % 2 == 0 ? "نسخه مشتری" : "نسخه بانک";
    }

    public String getStateChangeDate() {
        return stateChangeDate;
    }

    public void setStateChangeDate(String stateChangeDate) {
        this.stateChangeDate = stateChangeDate;
    }

    public Long getAutoArzeshBazkharidAlalHesab2() {
        return autoArzeshBazkharidAlalHesab2;
    }

    public void setAutoArzeshBazkharidAlalHesab2(Long autoArzeshBazkharidAlalHesab2) {
        this.autoArzeshBazkharidAlalHesab2 = autoArzeshBazkharidAlalHesab2;
    }

    public Long getAutoArzeshGhati2() {
        return autoArzeshGhati2;
    }

    public void setAutoArzeshGhati2(Long autoArzeshGhati2) {
        this.autoArzeshGhati2 = autoArzeshGhati2;
    }

    public Long getAutoAndukhtGhati2() {
        return autoAndukhtGhati2;
    }

    public void setAutoAndukhtGhati2(Long autoAndukhtGhati2) {
        this.autoAndukhtGhati2 = autoAndukhtGhati2;
    }

    public Long getAutoAndukhtAlalHesab2() {
        return autoAndukhtAlalHesab2;
    }

    public void setAutoAndukhtAlalHesab2(Long autoAndukhtAlalHesab2) {
        this.autoAndukhtAlalHesab2 = autoAndukhtAlalHesab2;
    }

    public String getAutoTarikhMabna2() {
        return autoTarikhMabna2;
    }

    public void setAutoTarikhMabna2(String autoTarikhMabna2) {
        this.autoTarikhMabna2 = autoTarikhMabna2;
    }

    public Map<String, String> getTarikhAsarListForElhaghie() {
        HashMap<String, String> returnList = new HashMap<String, String>();
        String headerString = "ابتدای سال بیمه ای  %s (%s)";
        List<Elhaghiye> elhaghiyeList = getElhaghiyehaDaem();
        int lastSaleBimeiAsarElhaghie = -1;
        for(Elhaghiye e : elhaghiyeList)
        {
            if(e.getSaleBimeiAsar() > lastSaleBimeiAsarElhaghie)
                lastSaleBimeiAsarElhaghie = e.getSaleBimeiAsar();
        }
        for (GhestBandi gb : getGhestBandiList())
        {
            if(gb.getType().equals(GhestBandi.Type.G_BIMENAME)&&gb.getSaleBimeiInt() >= lastSaleBimeiAsarElhaghie)
                returnList.put(gb.getSaleBimei(), String.format(headerString, gb.getSaleBimeiInt() + 1, OmrUtil.calculateTarikhAsar(gb.getSaleBimeiInt(), getTarikhShorou())));
        }
        Map<String, String> returnListMap = new TreeMap<String, String>(returnList);
        return returnListMap;
    }

    public String getLastTarikhAsarElhaghie()
    {
        List<Elhaghiye> elhaghiyeList= getElhaghiyehaDaem();
        String bigDate=null;
        if(elhaghiyeList!=null && elhaghiyeList.size()>0)
        {
            bigDate=elhaghiyeList.get(0).getTarikhAsar();
            for (int i=0;i<elhaghiyeList.size()-1;i++)
            {
                if (DateUtil.isGreaterThan(elhaghiyeList.get(i+1).getTarikhAsar(),bigDate))
                    bigDate= elhaghiyeList.get(i+1).getTarikhAsar();
            }
        }
        return bigDate;
    }
}
