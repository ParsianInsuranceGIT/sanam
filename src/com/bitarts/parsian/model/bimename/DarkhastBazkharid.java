package com.bitarts.parsian.model.bimename;

import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.management.EmzaKonande;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_darkhast_bazkharid")
public class DarkhastBazkharid implements Serializable, Comparable<DarkhastBazkharid> {

    public enum KhesaratType
    {
        OMR, HADESE, MOAFIAT, AMRAZ, NAGHSOZV
    }

    public static enum DarkhastType {
        BAZKHARID, VAM, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD, TOZIH, VARIZ, KHESARAT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "darbazkharid_id")
    private Integer id;
    @Column(name = "darbaz_nameBimeshode")
    private String nameBimeShode;
    @Column(name = "darbaz_familyBimeshode")
    private String familyBimeShode;
    @Column(name = "darbaz_namePedarBimeShode")
    private String namePedarBimeShode;
    @Column(name = "darbaz_kodeMeliBimeShode")
    private String kodeMeliBimeShode;
    @Column(name = "darbaz_arzesh_bazkharid")
    private String arzeshBazkharid;
    @Column(name = "darbaz_andukhte_ghatie")
    private String andukhteGhatie;
    @Column(name = "c_mozuElhaghie")
    private String mozuElhaghie;
    @Column(name = "c_matnElhaghie", columnDefinition = "VARCHAR2(4000)")
    private String matnElhaghie;
    @Column(name = "darbaz_shomareShenasname")
    private String shomareShenasnameBimeShode;
    @Column(name = "darbaz_tarikhTavalBimShod")
    private String tarikhTavallodBimeShode;
    @Column(name = "darbaz_telephonBimeShode")
    private String telephonSabetBimeShode;
    @Column(name = "darbaz_hamrahBimeShode")
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
    @Column(name = "darbaz_jaraemeDirkard")
    private String jaraemeDirkard;
    @Column(name = "darbaz_jaraemeTavigh")
    private String jaraemeTavigh;
    @Column(name = "darbaz_aghsat_nonpardakht")
    private String jameAghsatPardakhtNashode;
    @Column(name = "jame_Kol_Aghsa_tMoavagh")
    private String jameKolAghsatMoavagh;
    @Column(name = "Jame_Asl_Aghsat_Ati")
    private String jameAslAghsatAti;
    @Column(name = "darbaz_nameRooznameh")
    private String nameRooznameh;
    @Column(name = "darbaz_tarikhAvalinChap")
    private String tarikhAvalinChap;
    @Column(name = "darbaz_tarikhDovominChap")
    private String tarikhDovominChap;
    @Column(name = "darbaz_bimenameIsMafqud")
    private String bimenameIsMafqud;
    // baraa darkhast bardasht az andoukhte
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id1")
    private EmzaKonande emzaKonandeAval;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id2")
    private EmzaKonande emzaKonandeDovom;
    @Column(name = "darbaz_mablaghdarkhasti")
    private String mablaghDarkhastiBardasht;
    @Column(name = "max_Amount_bardasht")
    private Long maxAmountBardasht;
    @Column(name = "darbaz_darsadBardasht")
    private String darsadBardasht;
    @Column(name = "darbaz_finished")
    private Boolean finished;
//    @OneToOne(mappedBy = "darkhastBazkharid", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    private Elhaghiye elhaghie;

    @OneToMany(mappedBy = "darkhastBazkharid", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Elhaghiye> elhaghiyeList;

    @OneToMany(mappedBy = "darkhastBazkharid", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<TransitionLog> transitionLogs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_id")
    private User karshenas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_khesarat_id")
    private User karshenasKhesarat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bime_dar")
    private Bimename bimename;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zamaem_darkhast_id")
    private ZamaemDarkhast zamaemDarkhast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name = "darkhast_type")
    @Enumerated(EnumType.STRING)
    private DarkhastType darkhastType;

    @ManyToOne
    @JoinColumn(name = "ghest_bandi_id")
    private GhestBandi ghestBandi;

    @OneToOne(mappedBy = "darkhastBazkharid", fetch = FetchType.LAZY)
    private Darkhast darkhast;

    @OneToOne(mappedBy = "darkhastBazkharid", fetch = FetchType.LAZY)
    private Credebit bedehiPishAzMoedCredebit;
    //--------------------------------------
    @Column(name = "tarikheAvalinGhest")
    private String tarikheAvalinGhest;
    @Column(name = "modatebazpardakht")
    private String modatebazpardakht;
    @Column(name = "nahveyePardakhteAghsat")
    private String nahveyePardakhteAghsat;
    @Column(name = "mablaghVamedarkhasti")
    private Long mablaghVamedarkhasti;
    @Column(name = "c_taghirKodBe")
    private String taghirKodBe;
    @Column(name = "c_rezayatNamayande")
    private Boolean rezayatNamayande;

    @ManyToOne
    @JoinColumn(name="namayande_id")
    private Namayande namayande;

    @Column(name = "shomare_vam")
    private String shomareVam;

    @Column(name="TasvieVamPishAzMoed_Id")
    private Integer TasvieVamPishAzMoedId;

    @Column(name="nahve_elam_khesarat")
    private Integer nahveElamKhesarat;

    @Column(name="tarikh_Voghue_khesarat")
    private Integer tarikhVoghueKhesarat;

    @Column(name = "khesarat_type")
    @Enumerated(EnumType.STRING)
    private KhesaratType khesaratType;

    @Column(name = "elateKhesarat")
    private String elateKhesarat;

    @Column(name = "khesarat_description")
    private String khesaratDescription;

    @ManyToOne
    @JoinColumn(name = "pronouncerOrg_id")
    private Namayande pronouncerOrg;

    @JoinColumn(name = "ostan_khesarat")
    @ManyToOne
    private City ostanKhesarat;

    @JoinColumn(name = "shahr_khesarat")
    @ManyToOne
    private City shahrKhesarat;

    @Column(name="nazar_Karshenas_Khesarat")
    private String nazarKarshenasKhesarat;

    @Column(name = "shomare_Parvande")
    private String shomareParvande;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khesarat_I")
    private Khesarat khesaratI;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khesarat_II")
    private Khesarat khesaratII;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Credebit_Khesarat")
    private Credebit credebitKhesarat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Credebit_Bardasht")
    private Credebit credebitBardasht;

    @Column(name = "shomare_bardasht_az_andukhte")
    private String shomareBardashtAzAndukhte;

    @Column(name = "jame_Aghsat_Moavagh_Bimename")
    private Long jameAghsatMoavaghBimename;

    @Column(name="jame_Kol_Bedehi_Vam")
    private Long jameKolBedehiVam;

    public Long getJameKolBedehiVam()
    {
        return jameKolBedehiVam;
    }

    public void setJameKolBedehiVam(Long jameKolBedehiVam)
    {
        this.jameKolBedehiVam = jameKolBedehiVam;
    }

    public String getJameAghsatMoavaghBimenameCalc()
    {
        if(state.getId()<11090 && state.getId()!=11100 && state.getId()!=11110)
           return bimename.getAmountMoavagh();
        return getJameAghsatMoavaghBimename() != null ? NumberFormat.getInstance().format(getJameAghsatMoavaghBimename()) : "0";
    }

    public Long getJameAghsatMoavaghBimename()
    {
        return jameAghsatMoavaghBimename;
    }

    public void setJameAghsatMoavaghBimename(Long jameAghsatMoavaghBimename)
    {
        this.jameAghsatMoavaghBimename = jameAghsatMoavaghBimename;
    }

    public String getJaraemeTavigh()
    {
        return jaraemeTavigh;
    }

    public String getHaveHavale()
    {
        if(khesaratI!=null)
        {
            if(khesaratI.getHavaleList()!=null && khesaratI.getHavaleList().size()>0 && Long.parseLong(khesaratI.getKoleAmountHavaleha().replaceAll(",","").trim())==Long.parseLong(khesaratI.getAmountTaidShode().replaceAll(",", "").trim()))
            {
                if(khesaratII!=null)
                {
                    if (khesaratII.getHavaleList() != null && khesaratII.getHavaleList().size() > 0 && Long.parseLong(khesaratII.getKoleAmountHavaleha().replaceAll(",", "").trim()) == Long.parseLong(khesaratII.getAmountTaidShode().replaceAll(",", "").trim()))
                        return "true";
                }
                else
                    return "true";
            }
        }
        return "false";
    }

    public void setJaraemeTavigh(String jaraemeTavigh)
    {
        this.jaraemeTavigh = jaraemeTavigh;
    }

    public String getShomareBardashtAzAndukhte()
    {
        return shomareBardashtAzAndukhte;
    }

    public void setShomareBardashtAzAndukhte(String shomareBardashtAzAndukhte)
    {
        this.shomareBardashtAzAndukhte = shomareBardashtAzAndukhte;
    }

    public String getTarikhHavale()
    {
        for (TransitionLog tLog : getTransitionLogs())
        {
            if (tLog.getTransition().getId().equals(617))
                return tLog.getDate();
        }
        return "";
    }

    public List<KhesaratHavale> getHavaleList()
    {
        List<KhesaratHavale> havaleList=new ArrayList<KhesaratHavale>();
        havaleList.addAll(this.khesaratI.getHavaleList());
        if(this.khesaratII!=null)
            havaleList.addAll(khesaratII.getHavaleList());

        return havaleList;
    }

    public Long getMaxAmountBardasht()
    {
        return maxAmountBardasht;
    }

    public String getMaxAmountBardashtStr()
    {
        return NumberFormat.getInstance().format(maxAmountBardasht);
    }

    public void setMaxAmountBardasht(Long maxAmountBardasht)
    {
        this.maxAmountBardasht = maxAmountBardasht;
    }

    public Khesarat getKhesaratI()
    {
        return khesaratI;
    }

    public Credebit getCredebitBardasht()
    {
        return credebitBardasht;
    }

    public void setCredebitBardasht(Credebit credebitBardasht)
    {
        this.credebitBardasht = credebitBardasht;
    }

    public Credebit getCredebitKhesarat()
    {
        return credebitKhesarat;
    }

    public void setCredebitKhesarat(Credebit credebitKhesarat)
    {
        this.credebitKhesarat = credebitKhesarat;
    }

    public void setKhesaratI(Khesarat khesaratI)
    {
        this.khesaratI = khesaratI;
    }

    public User getKarshenasKhesarat()
    {
        return karshenasKhesarat;
    }

    public void setKarshenasKhesarat(User karshenasKhesarat)
    {
        this.karshenasKhesarat = karshenasKhesarat;
    }

    public Khesarat getKhesaratII()
    {
        return khesaratII;
    }

    public void setKhesaratII(Khesarat khesaratII)
    {
        this.khesaratII = khesaratII;
    }

    public String getShomareParvande()
    {
        return shomareParvande;
    }

    public void setShomareParvande(String shomareParvande)
    {
        this.shomareParvande = shomareParvande;
    }

    public String getNazarKarshenasKhesarat()
    {
        return nazarKarshenasKhesarat;
    }

    public List<String> getNazarKarshenasKhesaratBeTafkik()
    {
        return StringUtil.convertStringToList(this.nazarKarshenasKhesarat,'|');
    }

    public void setNazarKarshenasKhesarat(String nazarKarshenasKhesarat)
    {
        this.nazarKarshenasKhesarat = nazarKarshenasKhesarat;
    }

    public String getElateKhesarat()
    {
        return elateKhesarat;
    }

    public void setElateKhesarat(String elateKhesarat)
    {
        this.elateKhesarat = elateKhesarat;
    }

    public String getKhesaratDescription()
    {
        return khesaratDescription;
    }

    public void setKhesaratDescription(String khesaratDescription)
    {
        this.khesaratDescription = khesaratDescription;
    }

    public Integer getNahveElamKhesarat()
    {
        return nahveElamKhesarat;
    }

    public void setNahveElamKhesarat(Integer nahveElamKhesarat)
    {
        this.nahveElamKhesarat = nahveElamKhesarat;
    }

    public Integer getTarikhVoghueKhesarat()
    {
        return tarikhVoghueKhesarat;
    }

    public void setTarikhVoghueKhesarat(Integer tarikhVoghueKhesarat)
    {
        this.tarikhVoghueKhesarat = tarikhVoghueKhesarat;
    }

    public KhesaratType getKhesaratType()
    {
        return khesaratType;
    }

    public void setKhesaratType(KhesaratType khesaratType)
    {
        this.khesaratType = khesaratType;
    }

    public City getOstanKhesarat()
    {
        return ostanKhesarat;
    }

    public void setOstanKhesarat(City ostanKhesarat)
    {
        this.ostanKhesarat = ostanKhesarat;
    }

    public City getShahrKhesarat()
    {
        return shahrKhesarat;
    }

    public void setShahrKhesarat(City shahrKhesarat)
    {
        this.shahrKhesarat = shahrKhesarat;
    }

    public Namayande getPronouncerOrg()
    {
        return pronouncerOrg;
    }

    public void setPronouncerOrg(Namayande pronouncerOrg)
    {
        this.pronouncerOrg = pronouncerOrg;
    }

    public Integer getTasvieVamPishAzMoedId()
    {
        return TasvieVamPishAzMoedId;
    }

    public void setTasvieVamPishAzMoedId(Integer tasvieVamPishAzMoedId)
    {
        TasvieVamPishAzMoedId = tasvieVamPishAzMoedId;
    }


    public DarkhastBazkharid() {
    }

    public DarkhastBazkharid(String nameBimeShode, String familyBimeShode, String namePedarBimeShode, String kodeMeliBimeShode, String arzeshBazkharid, String shomareShenasnameBimeShode, String tarikhTavallodBimeShode, String telephonSabetBimeShode, String telephonHamrahBimeShode, String shomareHesab, String shomareShaba, String bankName, String shobeName, String kodeShobe, String sahebHesab, String kodeNamayandegi, String telephoneNamayandegi, String noeNamayandegi, String tarikhDarkhast, String shomareDarkhast, String jaraemeDirkard, String jaraemeTavigh, String jameAghsatPardakhtNashode, String nameRooznameh, String tarikhAvalinChap, String tarikhDovominChap, String bimenameIsMafqud, EmzaKonande emzaKonandeAval, EmzaKonande emzaKonandeDovom, String mablaghDarkhastiBardasht, String darsadBardasht, Boolean finished, User karshenas, Bimename bimename, ZamaemDarkhast zamaemDarkhast, State state, DarkhastType darkhastType, GhestBandi ghestBandi, String tarikheAvalinGhest, String modatebazpardakht, String nahveyePardakhteAghsat, String mablagheVamedarkhasti) {
        this.nameBimeShode = nameBimeShode;
        this.familyBimeShode = familyBimeShode;
        this.namePedarBimeShode = namePedarBimeShode;
        this.kodeMeliBimeShode = kodeMeliBimeShode;
        this.arzeshBazkharid = arzeshBazkharid;
        this.shomareShenasnameBimeShode = shomareShenasnameBimeShode;
        this.tarikhTavallodBimeShode = tarikhTavallodBimeShode;
        this.telephonSabetBimeShode = telephonSabetBimeShode;
        this.telephonHamrahBimeShode = telephonHamrahBimeShode;
        this.shomareHesab = shomareHesab;
        this.shomareShaba = shomareShaba;
        this.bankName = bankName;
        this.shobeName = shobeName;
        this.kodeShobe = kodeShobe;
        this.sahebHesab = sahebHesab;
        this.kodeNamayandegi = kodeNamayandegi;
        this.telephoneNamayandegi = telephoneNamayandegi;
        this.noeNamayandegi = noeNamayandegi;
        this.tarikhDarkhast = tarikhDarkhast;
        this.shomareDarkhast = shomareDarkhast;
        this.jaraemeDirkard = jaraemeDirkard;
        this.jaraemeTavigh = jaraemeTavigh;
        this.jameAghsatPardakhtNashode = jameAghsatPardakhtNashode;
        this.nameRooznameh = nameRooznameh;
        this.tarikhAvalinChap = tarikhAvalinChap;
        this.tarikhDovominChap = tarikhDovominChap;
        this.bimenameIsMafqud = bimenameIsMafqud;
        this.emzaKonandeAval = emzaKonandeAval;
        this.emzaKonandeDovom = emzaKonandeDovom;
        this.mablaghDarkhastiBardasht = mablaghDarkhastiBardasht;
        this.darsadBardasht = darsadBardasht;
        this.finished = finished;
        this.karshenas = karshenas;
        this.bimename = bimename;
        this.zamaemDarkhast = zamaemDarkhast;
        this.state = state;
        this.darkhastType = darkhastType;
        this.ghestBandi = ghestBandi;
        this.tarikheAvalinGhest = tarikheAvalinGhest;
        this.modatebazpardakht = modatebazpardakht;
        this.nahveyePardakhteAghsat = nahveyePardakhteAghsat;
        this.mablaghVamedarkhasti = Long.parseLong(mablagheVamedarkhasti.replaceAll(",","").trim());
    }


    public String getShomareVam()
    {
        return shomareVam;
    }

    public void setShomareVam(String shomareVam)
    {
        this.shomareVam = shomareVam;
    }

    public Darkhast getDarkhast()
    {
        return darkhast;
    }

    public void setDarkhast(Darkhast darkhast)
    {
        this.darkhast = darkhast;
    }

    public Namayande getNamayande()
    {
        return namayande;
    }

    public void setNamayande(Namayande namayande)
    {
        this.namayande = namayande;
    }

    public DarkhastType getDarkhastType() {
        return darkhastType;
    }

    public void setDarkhastType(DarkhastType darkhastType) {
        this.darkhastType = darkhastType;
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
        if (arzeshBazkharid == null || Integer.parseInt(arzeshBazkharid.replaceAll(",","")) < 0) return "0";
        return NumberFormat.getNumberInstance().format(Long.parseLong(arzeshBazkharid.replace(",", "").trim()));
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

    public GhestBandi getGhestBandi() {
        return ghestBandi;
    }

    public void setGhestBandi(GhestBandi ghestBandi) {
        this.ghestBandi = ghestBandi;
    }

    public String getTarikheAvalinGhest() {
        return tarikheAvalinGhest;
    }

    public void setTarikheAvalinGhest(String tarikheAvalinGhest) {
        this.tarikheAvalinGhest = tarikheAvalinGhest;
    }

    public String getModatebazpardakht() {
        return modatebazpardakht;
    }

    public void setModatebazpardakht(String modatebazpardakht) {
        this.modatebazpardakht = modatebazpardakht;
    }

    public String getNahveyePardakhteAghsat() {
        return nahveyePardakhteAghsat;
    }

    public void setNahveyePardakhteAghsat(String nahveyePardakhteAghsat) {
        this.nahveyePardakhteAghsat = nahveyePardakhteAghsat;
    }



    public String getMablagheVamedarkhasti() {
        if (mablaghVamedarkhasti == null) return null;
        return NumberFormat.getNumberInstance().format(mablaghVamedarkhasti);
    }

    public void setMablagheVamedarkhasti(String mablagheVamedarkhasti) {
        this.mablaghVamedarkhasti = Long.parseLong(mablagheVamedarkhasti.replaceAll(",","").trim());
    }

    public Long getMablaghVamedarkhasti()
    {
        return mablaghVamedarkhasti;
    }

    public void setMablaghVamedarkhasti(Long mablaghVamedarkhasti)
    {
        this.mablaghVamedarkhasti = mablaghVamedarkhasti;
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

    public String getMablaghDarkhastiBardasht() {
        return mablaghDarkhastiBardasht;
    }

    public void setMablaghDarkhastiBardasht(String mablaghDarkhastiBardasht) {
        this.mablaghDarkhastiBardasht = mablaghDarkhastiBardasht;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public String getJaraemeDirkard() {
        return jaraemeDirkard;
    }

    public void setJaraemeDirkard(String jaraemeDirkard) {
        this.jaraemeDirkard = jaraemeDirkard;
    }

//    public String getJaraemeTavigh() {
//        return jaraemeTavigh;
//    }
//
//    public void setJaraemeTavigh(String jaraemeTavigh) {
//        this.jaraemeTavigh = jaraemeTavigh;
//    }

    public String getJameKolAghsatMoavagh()
    {
        return jameKolAghsatMoavagh;
    }

    public void setJameKolAghsatMoavagh(String jameKolAghsatMoavagh)
    {
        this.jameKolAghsatMoavagh = jameKolAghsatMoavagh;
    }

    public String getJameAghsatPardakhtNashode() {
        return jameAghsatPardakhtNashode;
    }

    public void setJameAghsatPardakhtNashode(String jameAghsatPardakhtNashode) {
        this.jameAghsatPardakhtNashode = jameAghsatPardakhtNashode;
    }

    public String getJameAslAghsatAti()
    {
        return jameAslAghsatAti;
    }

    public void setJameAslAghsatAti(String jameAslAghsatAti)
    {
        this.jameAslAghsatAti = jameAslAghsatAti;
    }

    public Boolean getFinished() {
        if (finished == null) finished = false;
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Elhaghiye> getElhaghiyeList()
    {
        return elhaghiyeList;
    }

    public void setElhaghiyeList(List<Elhaghiye> elhaghiyeList)
    {
        this.elhaghiyeList = elhaghiyeList;
    }

    public Elhaghiye getElhaghie()
    {
        if (this.elhaghiyeList != null && this.elhaghiyeList.size() > 0)
            return getElhaghiyeList().get(0);
        return null;
    }

    public void setElhaghie(Elhaghiye elhaghiye)
    {
        if (this.elhaghiyeList != null)
        {
            this.elhaghiyeList.add(elhaghiye);
        }
        else
        {
            this.elhaghiyeList = new ArrayList<Elhaghiye>();
            this.elhaghiyeList.add(elhaghiye);
        }
    }

    public String getDarsadBardasht() {
        return darsadBardasht;
    }

    public void setDarsadBardasht(String darsadBardasht) {
        this.darsadBardasht = darsadBardasht;
    }

    public String getDarkhstTypeFarsi() {
        switch (darkhastType) {
            case VAM:
                return "دریافت وام";
            case BARDASHT_AZ_ANDOKHTE:
                return "برداشت از اندوخته";
            case BAZKHARID:
                return "بازخرید بیمه نامه";
            case TASVIE_PISH_AZ_MOEDE_VAM:
                return "تسویه پیش از موعد وام";
            case EBTAL:
                return "ابطال بیمه نامه";
            case VARIZ:
                return "واریز به اندوخته";
            case TAGHIRKOD:
                return "تغییر کد نمایندگی";
            case TOZIH:
                return "توضيح";
            case KHESARAT:
                return "خسارت";
            default:
                return "";
        }
    }

    public String getNoeKhasFarsiString(){
        switch (darkhastType){
            case TOZIH:
                return "توضيح";
            case KHESARAT:
                return getCurrentKhesarat().getTypeFarsi();
            default:
                return getDarkhstTypeFarsi();
        }
    }

    public String getJoziatFarsiString(){
        switch(darkhastType){
            case TOZIH:
                return "توضيح";
            default:
                return getDarkhstTypeFarsi();
        }
    }

    public String getTaghirKodBe() {
        return taghirKodBe;
    }

    public void setTaghirKodBe(String taghirKodBe) {
        this.taghirKodBe = taghirKodBe;
    }

    public String getMozuElhaghie() {
        return mozuElhaghie;
    }

    public void setMozuElhaghie(String mozuElhaghie) {
        this.mozuElhaghie = mozuElhaghie;
    }

    public String getMatnElhaghie() {
        return matnElhaghie;
    }

    public void setMatnElhaghie(String matnElhaghie) {
        this.matnElhaghie = matnElhaghie;
    }

    public Boolean getRezayatNamayande() {
        return rezayatNamayande;
    }

    public void setRezayatNamayande(Boolean rezayatNamayande) {
        this.rezayatNamayande = rezayatNamayande;
    }

    public String getNameRooznameh() {
        return nameRooznameh;
    }

    public void setNameRooznameh(String nameRooznameh) {
        this.nameRooznameh = nameRooznameh;
    }

    public String getTarikhAvalinChap() {
        return tarikhAvalinChap;
    }

    public void setTarikhAvalinChap(String tarikhAvalinChap) {
        this.tarikhAvalinChap = tarikhAvalinChap;
    }

    public String getTarikhDovominChap() {
        return tarikhDovominChap;
    }

    public void setTarikhDovominChap(String tarikhDovominChap) {
        this.tarikhDovominChap = tarikhDovominChap;
    }

    public String getBimenameIsMafqud() {
        return bimenameIsMafqud;
    }

    public void setBimenameIsMafqud(String bimenameIsMafqud) {
        this.bimenameIsMafqud = bimenameIsMafqud;
    }

    public String getNahveyePardakhteAghsatFarsi() {
        if (nahveyePardakhteAghsat.equals("MAHANE")) return "ماهانه";
        else return "سه ماهه";
    }

    public int compareTo(DarkhastBazkharid o) {
        if (o.tarikhDarkhast == null) return 1;
        if (tarikhDarkhast == null) return -1;
        return (o.tarikhDarkhast.compareTo(tarikhDarkhast));
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getAndukhteGhatie() {
        return andukhteGhatie;
    }

    public void setAndukhteGhatie(String andukhteGhatie) {
        this.andukhteGhatie = andukhteGhatie;
    }

    public Credebit getBedehiPishAzMoedCredebit()
    {
        return bedehiPishAzMoedCredebit;
    }

    public void setBedehiPishAzMoedCredebit(Credebit bedehiPishAzMoedCredebit)
    {
        this.bedehiPishAzMoedCredebit = bedehiPishAzMoedCredebit;
    }

    public String getJameMabaleghePardakhti()
    {

        Double total = 0.0;
        Double kolleMablagh = 0.0;
        if(this.getDarkhastType().equals(DarkhastType.VAM) && this.getGhestBandi()!=null && this.getGhestBandi().getGhestList()!=null)
            for (Ghest g : this.getGhestBandi().getGhestList())
            {
                total = total + Double.parseDouble(g.getCredebit().getRemainingAmount().replace(",", ""));
                kolleMablagh = kolleMablagh + Double.parseDouble(g.getCredebit().getAmount().replace(",", ""));
            }
        return NumberFormat.getInstance().format(kolleMablagh - total);
    }

    public Khesarat getCurrentKhesarat()
    {
        if
        (
            getKhesaratII()==null ||
            (
                (
                    ((getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))&&!bimename.isHaveKhesaratAmraz())||
                    ((getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))&&!bimename.isHaveKhesaratNaghsOzv())||
                    ((!getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))&&!getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                )&&(getKhesaratI().getElhaghiye()==null)
            )
        )
            return khesaratI;
        else
            return khesaratII;
    }

    public String getFinalAmountKhesarat()
    {
        if(this.getKhesaratI().getAmountGhabelPardakht()==null)return null;
        Long finalAmount=Long.parseLong(StringUtil.removeNoneDigits(this.getKhesaratI().getAmountGhabelPardakht()));
        if(this.getKhesaratII()!=null && this.getKhesaratII().getAmountGhabelPardakht()!=null)
        {
            finalAmount+= Long.parseLong(StringUtil.removeNoneDigits(this.getKhesaratII().getAmountGhabelPardakht()));
        }
        return NumberFormat.getInstance().format(finalAmount);
    }

    public boolean isBimenameClosed()
    {
        if(khesaratI.getKhesaratType().equals(Khesarat.KhesaratType.OMR)|| khesaratI.getKhesaratType().equals(Khesarat.KhesaratType.HADESE))
            return true;
        if(khesaratII!=null && (khesaratII.getKhesaratType().equals(Khesarat.KhesaratType.OMR) || khesaratII.getKhesaratType().equals(Khesarat.KhesaratType.HADESE)))
            return true;
        return false;
    }

    public boolean isAkhzeMojavezKhesarat()
    {
        if(transitionLogs==null) return false;
        for(TransitionLog tLog:transitionLogs)
        {
            if(tLog.getTransition().getId().equals(604))
                return true;
        }
        return false;
    }

    public String getLastVamGhestSarresid()
    {
        return ghestBandi.getGhestList()!=null?ghestBandi.getGhestList().get(ghestBandi.getGhestList().size()-1).getSarresidDate() : null ;
    }

}
