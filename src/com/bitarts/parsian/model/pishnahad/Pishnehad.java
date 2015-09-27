package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.action.SerialsAction;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 3/6/11
 * Time: 4:25 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_pishnehad")
public class Pishnehad implements java.io.Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "radif")
    private String radif;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "jadid_date")
    private String jadidDate;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "noe_pishnehad")
    private String noePishnehad;
    @Column(name = "noe_bimename")
    private String noeBimename;
    @Column(name = "noe_gharardad")
    private String noeGharardad;
    @Column(name = "initialy_printed")
    private String initialyPrinted = "no";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_poshtiban_id")
    private Namayande namayandePoshtiban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Previous_namayande_id")
    private Namayande previousNamayande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_creator_id")
    private User userCreator;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "bimeGozar_id")
    private BimeGozar bimeGozar;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "bimeShode_id")
    private BimeShode bimeShode;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "SoalateOmomiAzBimeShode_id")
    private SoalateOmomiAzBimeShode soalateOmomiAzBimeShode;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TransitionLog> transitionLogs;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Credebit> credebits;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShode;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SavabegheBimei> savabegheBimei;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Foroshande_id")
    private Foroshande foroshande;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PezeshkSabtNazar> nazaraatePezeshk;
    @OneToMany(mappedBy = "pishnehad", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBime;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address addressBimeGozar;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "address_id2")
    private Address addressBimeShode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
//    @JoinColumn(name = "estelam_id")
//    @BatchSize(size = 30)
//    private Estelam estelam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "estelam_id")
    private Estelam estelam;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "naghs_id")
    private NaghsPishnehad naghsPishnehad;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sharayet_jadid_id")
    private SharayeteJadid sharayeteJadid;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "elam_hesab_id")
    private ElameHesab elameHesab;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "elam_enseraf_id")
    private ElameHesab elameEnseraf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "zamaem_id")
    private Zamaem zamaem;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "zamaemkhesarat_id")
    private ZamaemKhesarat zamaemKhesarat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Bimename bimename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_id")
    private User karshenas;
    @Column(name = "tarikh_assign_karshenas")
    private String tarikhAssignBeKarshenas;
    @Column(name = "tarikh_assign_karshenas_n")
    private String tarikhAssignBeKarshenasNazer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_nazer_id")
    private User karshenasNazer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bazaryab_id")
    private User bazarYab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processor_id")
    private User processorDetails;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pishnehad", cascade = CascadeType.REMOVE)
    @BatchSize(size = 30)
    private List<Fish> fishs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pishnehad", cascade = CascadeType.REMOVE)
    private List<Moarefiname> moarefinameList;


    @Column(name = "pishpardakht_type")
    private String pishpardakhtType;
    @Column(name = "pishpardakht_sanad")
    private String pishpardakhtSanad;
    @Column(name = "pishnehad_processor")
    private String processor;
    @Column(name = "adam_taeed_pezeshki")
    private String adamTaeedPezeshki;
    @Column(name = "c_valid")
    private Boolean valid;
    @Column(name = "version")
    private String version;
    @ManyToOne(fetch = FetchType.LAZY)
    private Gharardad gharardad;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Tarh tarh;
    @Column(name = "private_messages")
    private String privateMessages;
    @Column(name = "options")
    private String options;

    @Column(name="control_mohasebat")
    private Boolean adameControllMohasebat;

//    @OneToOne(mappedBy = "oldPishnehad", fetch = FetchType.LAZY)
//    private DarkhastTaghirat oldDarkhastTaghirat;

    @OneToMany(mappedBy = "oldPishnehad")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<DarkhastTaghirat> oldDarkhastTaghiratList;

    @OneToOne(mappedBy = "newPishnehad", fetch = FetchType.LAZY)
    private DarkhastTaghirat newDarkhastTaghirat;

    @OneToOne(mappedBy = "oldPishnehad", fetch = FetchType.LAZY)
    private Elhaghiye oldElhaghiye;

    @OneToOne(mappedBy = "newPishnehad", fetch = FetchType.LAZY)
    private Elhaghiye newElhaghiye;

    public Pishnehad(String radif, String createdDate, String createdTime, String noePishnehad, String noeGharardad, Tarh tarh, String initialyPrinted, Namayande namayande, User userCreator, BimeGozar bimeGozar, BimeShode bimeShode, SoalateOmomiAzBimeShode soalateOmomiAzBimeShode, Address addressBimeGozar, Address addressBimeShode, State state, Estelam estelam, NaghsPishnehad naghsPishnehad, SharayeteJadid sharayeteJadid, ElameHesab elameHesab, ElameHesab elameEnseraf, Zamaem zamaem, User karshenas, User bazarYab, User processorDetails, String pishpardakhtType, String pishpardakhtSanad, String processor, String adamTaeedPezeshki, String version, Foroshande foroshande, Bimename bimename) {
        this.radif = radif;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.noePishnehad = noePishnehad;
        this.noeGharardad = noeGharardad;
        this.tarh = tarh;
        this.initialyPrinted = initialyPrinted;
        this.namayande = namayande;
        this.userCreator = userCreator;
        this.bimeGozar = bimeGozar;
        this.bimeShode = bimeShode;
        this.soalateOmomiAzBimeShode = soalateOmomiAzBimeShode;
        this.addressBimeGozar = addressBimeGozar;
        this.addressBimeShode = addressBimeShode;
        this.state = state;
        this.estelam = estelam;
        this.naghsPishnehad = naghsPishnehad;
        this.sharayeteJadid = sharayeteJadid;
        this.elameHesab = elameHesab;
        this.elameEnseraf = elameEnseraf;
        this.zamaem = zamaem;
        this.karshenas = karshenas;
        this.bazarYab = bazarYab;
        this.processorDetails = processorDetails;
        this.fishs = fishs;
        this.moarefinameList = moarefinameList;
        this.pishpardakhtType = pishpardakhtType;
        this.pishpardakhtSanad = pishpardakhtSanad;
        this.processor = processor;
        this.adamTaeedPezeshki = adamTaeedPezeshki;
        this.version = version;
        this.foroshande = foroshande;
        this.bimename = bimename;
    }

    public Elhaghiye getOldElhaghiye()
    {
        return oldElhaghiye;
    }

    public void setOldElhaghiye(Elhaghiye oldElhaghiye)
    {
        this.oldElhaghiye = oldElhaghiye;
    }

    public Elhaghiye getNewElhaghiye()
    {
        return newElhaghiye;
    }

    public void setNewElhaghiye(Elhaghiye newElhaghiye)
    {
        this.newElhaghiye = newElhaghiye;
    }

    public Set<DarkhastTaghirat> getOldDarkhastTaghiratList()
    {
        return oldDarkhastTaghiratList;
    }

    public void setOldDarkhastTaghiratList(Set<DarkhastTaghirat> oldDarkhastTaghiratList)
    {
        this.oldDarkhastTaghiratList = oldDarkhastTaghiratList;
    }

    public DarkhastTaghirat getNewDarkhastTaghirat()
    {
        return newDarkhastTaghirat;
    }

    public void setNewDarkhastTaghirat(DarkhastTaghirat newDarkhastTaghirat)
    {
        this.newDarkhastTaghirat = newDarkhastTaghirat;
    }

    public Boolean getAdameControllMohasebat()
    {
        return adameControllMohasebat;
    }

    public void setAdameControllMohasebat(Boolean adameControllMohasebat)
    {
        this.adameControllMohasebat = adameControllMohasebat;
    }

    public Namayande getNamayandePoshtiban()
    {
        return namayandePoshtiban;
    }

    public void setNamayandePoshtiban(Namayande namayandePoshtiban)
    {
        this.namayandePoshtiban = namayandePoshtiban;
    }

    public Namayande getPreviousNamayande()
    {
        return previousNamayande;
    }

    public void setPreviousNamayande(Namayande previousNamayande)
    {
        this.previousNamayande = previousNamayande;
    }

    public List<PezeshkSabtNazar> getNazaraatePezeshk() {
        return nazaraatePezeshk;
    }

    public void setNazaraatePezeshk(List<PezeshkSabtNazar> nazaraatePezeshk) {
        this.nazaraatePezeshk = nazaraatePezeshk;
    }

    public Estelam getEstelam() {
        return estelam;
    }

    public void setEstelam(Estelam estelam) {
        this.estelam = estelam;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Address getAddressBimeGozar() {
        return addressBimeGozar;
    }

    public void setAddressBimeGozar(Address addressBimeGozar) {
        this.addressBimeGozar = addressBimeGozar;
    }

    public Address getAddressBimeShode() {
        return addressBimeShode;
    }

    public void setAddressBimeShode(Address addressBimeShode) {
        this.addressBimeShode = addressBimeShode;
    }

    public Pishnehad() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public BimeGozar getBimeGozar() {
        return bimeGozar;
    }

    public void setBimeGozar(BimeGozar bimeGozar) {
        this.bimeGozar = bimeGozar;
    }

    public BimeShode getBimeShode() {
        return bimeShode;
    }

    public void setBimeShode(BimeShode bimeShode) {
        this.bimeShode = bimeShode;
    }

    public SoalateOmomiAzBimeShode getSoalateOmomiAzBimeShode() {
        return soalateOmomiAzBimeShode;
    }

    public void setSoalateOmomiAzBimeShode(SoalateOmomiAzBimeShode soalateOmomiAzBimeShode) {
        this.soalateOmomiAzBimeShode = soalateOmomiAzBimeShode;
    }

    public List<VaziateSalamatiBimeShode> getVaziateSalamatiBimeShodeList() {
        return vaziateSalamatiBimeShodeList;
    }

    public void setVaziateSalamatiBimeShodeList(List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList) {
        this.vaziateSalamatiBimeShodeList = vaziateSalamatiBimeShodeList;
    }

    public List<VaziateSalamatiKhanevadeyeBimeShode> getVaziateSalamatiKhanevadeyeBimeShode() {
        return vaziateSalamatiKhanevadeyeBimeShode;
    }

    public void setVaziateSalamatiKhanevadeyeBimeShode(List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShode) {
        this.vaziateSalamatiKhanevadeyeBimeShode = vaziateSalamatiKhanevadeyeBimeShode;
    }

    public List<SavabegheBimei> getSavabegheBimei() {
        return savabegheBimei;
    }

    public void setSavabegheBimei(List<SavabegheBimei> savabegheBimei) {
        this.savabegheBimei = savabegheBimei;
    }

    public Foroshande getForoshande() {
        return foroshande;
    }

    public String getKodeNamayandeKargozar()
    {
        if(this.options!=null && this.options.equals("CODE_MOVAGHAT"))
            return "کد موقت";
        return namayande.getKodeNamayandeKargozar();
    }

    public String getNamayandeName()
    {
        if (this.options != null && this.options.equals("CODE_MOVAGHAT"))
            return "کد موقت";
        return namayande.getName();
    }

    public void setForoshande(Foroshande foroshande) {
        this.foroshande = foroshande;
    }

    public List<EstefadeKonandeganAzSarmayeBime> getEstefadeKonandeganAzSarmayeBime() {
        return estefadeKonandeganAzSarmayeBime;
    }

    public void setEstefadeKonandeganAzSarmayeBime(List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBime) {
        this.estefadeKonandeganAzSarmayeBime = estefadeKonandeganAzSarmayeBime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getRadif() {
        return radif;
    }

    public void setRadif(String radif) {
        this.radif = radif;
    }

    public String getNoePishnehad() {
        return noePishnehad;
    }

    public void setNoePishnehad(String noePishnehad) {
        this.noePishnehad = noePishnehad;
    }

    public String getNoeGharardad() {
        return noeGharardad;
    }

    public String getNoeGharardadShort() {
        if(noeGharardad == null)
            return "-";
        if(noeGharardad.equals("قرارداد عمومي بيمه مركزي"))
            return "عمومی";
        if(noeGharardad.equals("قرارداد بيمه-بانك پارسيان(BankAssurance)"))
            return "BA";
        if(noeGharardad.equals("قرارداد كاركنان بيمه پارسيان"))
            return "کارکنان";
        if(noeGharardad.equals("قرارداد فروش جمعي"))
            return "جمعی";
        if(noeGharardad.equals("ساير"))
            return "ساير";
        return "?";
    }

    public void setNoeGharardad(String noeGharardad) {
        this.noeGharardad = noeGharardad;
    }

    public NaghsPishnehad getNaghsPishnehad() {
        return naghsPishnehad;
    }

    public void setNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        this.naghsPishnehad = naghsPishnehad;
    }

    public List<Fish> getFishs() {
        return fishs;
    }

    public void setFishs(List<Fish> fishs) {
        this.fishs = fishs;
    }

    public String getPishpardakhtType() {
        return pishpardakhtType;
    }

    public void setPishpardakhtType(String pishpardakhtType) {
        this.pishpardakhtType = pishpardakhtType;
    }

    public User getKarshenas() {
        return karshenas;
    }

    public void setKarshenas(User karshenas) {
        this.karshenas = karshenas;
    }

    public List<Moarefiname> getMoarefinameList() {
        return moarefinameList;
    }

    public int getMoarefinameListDarJaryan() {
        int count = 0;
        for(Moarefiname m : moarefinameList) {
         if(m.getVaziat().equals(Moarefiname.Vaziat.DAR_JARYAN)) {
             count ++;
         }
        }
        return count;
    }

    public void setMoarefinameList(List<Moarefiname> moarefinameList) {
        this.moarefinameList = moarefinameList;
    }

    public Zamaem getZamaem() {
        return zamaem;
    }

    public void setZamaem(Zamaem zamaem) {
        this.zamaem = zamaem;
    }

    public ZamaemKhesarat getZamaemKhesarat() {
        return zamaemKhesarat;
    }

    public void setZamaemKhesarat(ZamaemKhesarat zamaemKhesarat) {
        this.zamaemKhesarat = zamaemKhesarat;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public String getPishpardakhtSanad() {
        return pishpardakhtSanad;
    }

    public void setPishpardakhtSanad(String pishpardakhtSanad) {
        this.pishpardakhtSanad = pishpardakhtSanad;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public Boolean getValid() {
        if (valid == null) return false;
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public List<Credebit> getCredebits() {
        return credebits;
    }

    public void setCredebits(List<Credebit> credebits) {
        this.credebits = credebits;
    }

    public int compareTo(Object o) {
        Pishnehad p = (Pishnehad) o;
        return this.createdDate.compareTo(p.createdDate) * (-1);
    }

    public User getProcessorDetails() {
        return processorDetails;
    }

    public void setProcessorDetails(User processorDetails) {
        this.processorDetails = processorDetails;

    }

    public SharayeteJadid getSharayeteJadid() {
        return sharayeteJadid;
    }

    public void setSharayeteJadid(SharayeteJadid sharayeteJadid) {
        this.sharayeteJadid = sharayeteJadid;
    }

    public ElameHesab getElameHesab() {
        return elameHesab;
    }

    public void setElameHesab(ElameHesab elameHesab) {
        this.elameHesab = elameHesab;
    }

    public ElameHesab getElameEnseraf() {
        return elameEnseraf;
    }

    public void setElameEnseraf(ElameHesab elameEnseraf) {
        this.elameEnseraf = elameEnseraf;
    }

    public User getBazarYab() {
        return bazarYab;
    }

    public void setBazarYab(User bazarYab) {
        this.bazarYab = bazarYab;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public String getInitialyPrinted() {
        return initialyPrinted;
    }

    public void setInitialyPrinted(String initialyPrinted) {
        this.initialyPrinted = initialyPrinted;
    }

    public String getAdamTaeedPezeshki() {
        return adamTaeedPezeshki;
    }

    public void setAdamTaeedPezeshki(String adamTaeedPezeshki) {
        this.adamTaeedPezeshki = adamTaeedPezeshki;
    }

    public boolean getPishpardakhtOK() {
        boolean result = true;
        for (Fish fish : fishs) {
            result = result && (fish.getFound().equalsIgnoreCase("true"));
        }
        if (fishs.size() == 0) result = false;
        return result;
    }

    public List<Credebit> getFoundFishes() {
        ArrayList<Credebit> returnValue = new ArrayList<Credebit>();
        for (Fish fish : fishs) {
            if(fish.getFound().equalsIgnoreCase("true")) {
                returnValue.add(fish.getCredebit());
            }
        }
        return returnValue;
    }

    public List<Credebit> getFoundAndHaveRemainingFishes() {
        ArrayList<Credebit> returnValue = new ArrayList<Credebit>();
        for (Fish fish : fishs) {
            // todo: fish.getCredebit() != null bayad dorost beshe
            if(fish.getCredebit() != null && fish.getFound().equalsIgnoreCase("true") && fish.getCredebit().getRemainingAmount_long() > 0) {
                returnValue.add(fish.getCredebit());
            }
        }
        return returnValue;
    }

    public long getFishValue() {
        long returnValue = 0;
        for (Fish fish : fishs) {
            if(fish.getFound().equalsIgnoreCase("true")) {
                returnValue += fish.getCredebit().getRemainingAmount_long();
            }
        }
        return returnValue;
    }

    public Gharardad getGharardad() {
        return gharardad;
    }

    public void setGharardad(Gharardad gharardad) {
        this.gharardad = gharardad;
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }

    public String getTarikhAssignBeKarshenas() {
        return tarikhAssignBeKarshenas;
    }

    public void setTarikhAssignBeKarshenas(String tarikhAssignBeKarshenas) {
        this.tarikhAssignBeKarshenas = tarikhAssignBeKarshenas;
    }

    public String getJadidDate() {
        return jadidDate;
    }

    public void setJadidDate(String jadidDate) {
        this.jadidDate = jadidDate;
    }

    public Role getPeygirKonandeForReport() {
        if(getState().getId().equals(60))
        {
            if(getKarshenas() == null)
            {
                return new Role(5,"ROLE_KARSHENAS_MASOUL","#99cc66");
            }
            else
            {

                return new Role(4,"ROLE_KARSHENAS_SODUR","#99ccff");
            }
        }
        else
        {
            return getState().getPeygirKonande();
        }
    }

    public String getPrivateMessages() {
        if(privateMessages == null) return "";
        return privateMessages;
    }

    public String getPrivateMessagesHTML() {
        if(privateMessages == null) return "";
        return privateMessages.replaceAll("\r\n","<br/>");
    }

    public void setPrivateMessages(String privateMessages) {
        this.privateMessages = privateMessages;
    }

    public String getNoeBimename() {
        if(noeBimename == null) return "";
        return noeBimename;
    }

    public void setNoeBimename(String noeBimename) {
        this.noeBimename = noeBimename;
    }

    private boolean isHijdahDarsadi() {
        boolean darsad_18 = false;
        if(!(getTarh() == null)) {
            Set<Constants> constList = getTarh().getTarhParams();
        for(Constants c : constList) {
            if(c.getName().equals(Constants.Keys.NERKHBAHREFANNI) && c.getName2().equals(Constants.KeysParam.MEGHDARNERKH))
                if(c.getValue().contains("0.18"))
                    darsad_18 = true;
        }
        }
        return darsad_18;
    }

    public String getTozihatMohasebatRiazi() {
        String retStr = "سرمايه بيمه در صورت فوت بيمه شده در هر سال = سرمايه بيمه فوت به هر علت در آن سال + سرمايه فوت در اثر حادثه (در صورت فوت بر اثر حادثه و خريد اين پوشش) + اندوخته سرمايه گذاري تا تاريخ فوت.";
        retStr += "\r\n";
        if(getTarh() != null && getTarh().getHijdahDarsad()) {
            retStr += "حداقل نرخ سود تضمینی در این بیمه نامه در 5 سال اول از تاریخ صدور، 18% و در 5 سال دوم، 15% سالانه و به صورت روزشمار می باشد.\n" +
                    "در صورت كسب سود مازاد بر سود تضميني از سرمايه گذاري اندوخته بيمه گذاران از محل صندوق کم ريسک، 85% از اين سود، به صورت مشاركت در منافع، در پايان سال مالي شركت، به اندوخته سرمايه گذاري بيمه نامه منظور خواهد شد.";
        } else {
            retStr += "حداقل نرخ سود اعطایی به این بیمه نامه 15% به صورت سالیانه و روزشمار می باشد که برای مدت 10 سال تضمین شده است.\n" +
                                "در صورت كسب سود مازاد بر 15% (20%،25% یا بالاتر) در پایان هر سال مالی، 85% از اين سود، به صورت مشاركت در منافع به اندوخته سرمايه گذاري بيمه نامه افزوده خواهد شد.";

        }
        retStr += "سود مشاركت در منافع در پايان هر سال مالي محاسبه و به بيمه گذار اطلاع داده خواهد شد.\r\n" +
                "بديهي است مقادير جدول فوق با فرض تداوم شرايط بيمه نامه تا پايان قرارداد و همچنين پرداخت حق بيمه در سررسيد هاي تعيين شده، محاسبه شده است.";
        retStr += "\r\n";
        retStr += "تمامي محاسبات مربوط به ماليات بر ارزش افزوده براساس نرخ ابلاغي دولت در زمان صدور بيمه نامه انجام شده است.بديهي است درصورت تغيير نرخ,محاسبات مربوطه اصلاح خواهند شد.";
        return retStr;
    }

    public String getTozihateBimenameForPrint()
    {
        String text = "الف)حداقل نرخ سود تضمین شده اعطايي به اين بيمه نامه 15 درصد و به صورت روزشمار مي باشد، که برای مدت 10 سال تضمین شده است. در پايان 10 سال، نرخ سود، مطابق اعلام و تحت نظارت بيمه مركزي ايران اعمال خواهد شد. ب) در صورت كسب سود مازاد بر 15% از سرمايه گذاري اندوخته بيمه گذاران، 85 درصد از این سود، به صورت مشاركت در منافع، در پايان سال مالي شركت، به اندوخته سرمايه گذاري بيمه نامه منظور خواهد شد. ج)دريافت وام،سود مشاركت در منافع و برداشت از اندوخته، مطابق مقررات بيمه اي و منوط به پرداخت كامل و به موقع اقساط حق بيمه ها مي باشد. د)پرداخت سود و محاسبه اندوخته به صورت روز شمار بوده و به حق بيمه هاي وصول شده زودتر از سررسيد، سود تشويقي اعطا خواهد شد.";
        if(this.getTarh() != null && this.getTarh().getHijdahDarsad()) {
            text = "الف)حداقل نرخ سود تضمین شده اعطايي به اين بيمه نامه در 5 سال اول معادل 18% و در 5 سال دوم برابر با  15% به صورت سالانه و روزشمار مي باشد. در پايان 10 سال، نرخ سود، مطابق اعلام و تحت نظارت بيمه مركزي ايران اعمال خواهد شد. ب) در صورت كسب سود مازاد بر سود تضميني از سرمايه گذاري اندوخته بيمه گذاران از محل صندوق کم ريسک، 85% از اين سود، به صورت مشاركت در منافع، در پايان سال مالي شركت، به اندوخته سرمايه گذاري بيمه نامه منظور خواهد شد. ج)دريافت وام،سود مشاركت در منافع و برداشت از اندوخته، مطابق مقررات بيمه اي و منوط به پرداخت كامل و به موقع اقساط حق بيمه ها مي باشد. د)پرداخت سود و محاسبه اندوخته به صورت روز شمار بوده و به حق بيمه هاي وصول شده زودتر از سررسيد، سود تشويقي اعطا خواهد شد.";
        }
        if(this.getEstelam().getPooshesh_fot_dar_asar_haadese()!=null && this.getEstelam().getPooshesh_fot_dar_asar_haadese().equals("yes")) {
            text += "ذ)فوت در اثر زلزله جزء پوششهاي اضافي مي باشد"+".";
        }
        if(this.getBimeShode().getShakhs().getIraniOrAtbaeKhareji()!=null && this.getBimeShode().getShakhs().getIraniOrAtbaeKhareji().equals(Shakhs.Melliat.ATBAE_KHAREJI))
        {
            text+= "ر)در صورت فوت بيمه شده خارج از مرزهاي ايران يا در صورتي كه فوت ايشان در زماني واقع شود كه اقامت وي در ايران معلق يا باطل شده باشد ،تنها اندوخته سرمايه گذاري بيمه نامه تا تاريخ فوت بيمه شده در وجه استفاده كنندگان يا وراث قانوني بيمه شده پرداخت خواهد شد." +
                    "ضمنا بيمه شده در موارد فوق الذكر از منافع پوششهاي اضافي بيمه نامه بهره مند نخواهد شد.";
        }
        System.out.println(text);
        return text;
    }

    public String getOptions() {
        if(options == null) return "";
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public User getKarshenasNazer() {
        return karshenasNazer;
    }

    public void setKarshenasNazer(User karshenasNazer) {
        this.karshenasNazer = karshenasNazer;
    }

    public String getTarikhAssignBeKarshenasNazer() {
        return tarikhAssignBeKarshenasNazer;
    }

    public void setTarikhAssignBeKarshenasNazer(String tarikhAssignBeKarshenasNazer) {
        this.tarikhAssignBeKarshenasNazer = tarikhAssignBeKarshenasNazer;
    }



}
