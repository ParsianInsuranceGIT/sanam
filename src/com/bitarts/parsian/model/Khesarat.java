package com.bitarts.parsian.model;

import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.util.OmrUtil;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/30/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_khesarat")
public class Khesarat {



    public enum KhesaratType {
        OMR, HADESE, MOAFIAT, AMRAZ, NAGHSOZV
    }

    public enum KhesaratStatus {
        DAEM
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Bimename bimename;
    @Column(name = "c_createdDate")
    private String createdDate;
    @Column(name = "c_accidentDate")
    private String accidentDate;
    @Column(name = "c_type")
    private String type;
    @Column(name = "c_type_khesarat")
    private String typeKhesarat;
    @Column(name = "c_vaziatKhesarat")
    @Enumerated(EnumType.STRING)
    private KhesaratStatus vaziatKhesarat;
    @Column(name = "c_khesarat_type")
    @Enumerated(EnumType.STRING)
    private KhesaratType khesaratType;

    @Column(name = "c_sharhKhesarat", columnDefinition = "varchar2(4000)")
    private String sharhKhesarat;
    @Column(name = "c_ellat")
    private String ellat;
    @Column(name = "c_nahveElam")
    private String nahveElam;
    @JoinColumn(name = "c_ostanMahalleHadese")
    @ManyToOne
    private City ostanMahalleHadese;
    @JoinColumn(name = "c_shahrMahalleHadese")
    @ManyToOne
    private City shahrMahalleHadese;
    @Column(name = "c_amountElamShode")
    private String amountElamShode;
    @Column(name = "c_amountTaidShode")
    private String amountTaidShode;
    @Column(name = "c_amountAti")
    private String amountAti;
    @Column(name = "c_amountErfagh")
    private String amountErfagh;
    @Column(name = "c_amountMazad")
    private String amountMazad;
    @Column(name = "c_amountGhabelPardakht")
    private String amountGhabelPardakht;
    @Column(name = "c_amountBargashti")
    private String amountBargashti;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User karshenasKhesarat;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User userCreator;
    @Column(name = "c_nazarKarshenas", columnDefinition = "varchar2(4000)")
    private String nazarKarshenas;
    @ManyToOne
    private State state;
    @Column(name = "c_shomareParvande")
    private String shomareParvande;
    @OneToMany(mappedBy = "khesarat")
    private List<KhesaratHavale> havaleList;

    @Column(name="jame_aghsat_pardakht_shode_ati")
    private String jameAghsatePardakhtiAti;

    @ManyToOne
    @JoinColumn(name = "namayande")
    private Namayande namayande;

    @ManyToOne
    @JoinColumn(name = "pronouncerOrg_id")
    private Namayande pronouncerOrg;

    @Column(name = "andukhte")
    private String andukhte;

    @Column(name= "darsad_Naghs_Ozv")
    private Integer darsadNaghsOzv;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "khesarat")
    private Elhaghiye elhaghiye;

    @OneToOne(mappedBy = "khesaratI", fetch = FetchType.LAZY)
    private DarkhastBazkharid darkhastBazkharidI;

    @OneToOne(mappedBy = "khesaratII", fetch = FetchType.LAZY)
    private DarkhastBazkharid darkhastBazkharidII;

    @Column(name="c_sarmaye_amraz")
    private Long sarmayeAmraz;

    @Column(name="c_sarmaye_naghs_ozv")
    private Long sarmayeNaghseOzv;

    public Long getSarmayeAmraz()
    {
        return sarmayeAmraz;
    }

    public void setSarmayeAmraz(Long sarmayeAmraz)
    {
        this.sarmayeAmraz = sarmayeAmraz;
    }

    public Long getSarmayeNaghseOzv()
    {
        return sarmayeNaghseOzv;
    }

    public void setSarmayeNaghseOzv(Long sarmayeNaghseOzv)
    {
        this.sarmayeNaghseOzv = sarmayeNaghseOzv;
    }

    public String getJameAghsatePardakhtiAti()
    {
        return jameAghsatePardakhtiAti;
    }

    public void setJameAghsatePardakhtiAti(String jameAghsatePardakhtiAti)
    {
        this.jameAghsatePardakhtiAti = jameAghsatePardakhtiAti;
    }

    public Integer getDarsadNaghsOzv()
    {
        return darsadNaghsOzv;
    }

    public void setDarsadNaghsOzv(Integer darsadNaghsOzv)
    {
        this.darsadNaghsOzv = darsadNaghsOzv;
    }

    public Elhaghiye getElhaghiye()
    {
        return elhaghiye;
    }

    public Khesarat()
    {
    }

    public Khesarat(City shahrMahalleHadese, City ostanMahalleHadese, String nahveElam, String ellat, String sharhKhesarat, User userCreator, String accidentDate, KhesaratType khesaratType,Integer darsadNaghsOzv, Namayande pronouncerOrg)
    {
        this.shahrMahalleHadese = shahrMahalleHadese;
        this.ostanMahalleHadese = ostanMahalleHadese;
        this.nahveElam = nahveElam;
        this.ellat = ellat;
        this.sharhKhesarat = sharhKhesarat;
        this.userCreator = userCreator;
        this.accidentDate = accidentDate;
        this.khesaratType = khesaratType;
        this.darsadNaghsOzv = darsadNaghsOzv;
        this.pronouncerOrg = pronouncerOrg;
    }

    public DarkhastBazkharid getDarkhastBazkharidI()
    {
        return darkhastBazkharidI;
    }

    public void setDarkhastBazkharidI(DarkhastBazkharid darkhastBazkharidI)
    {
        this.darkhastBazkharidI = darkhastBazkharidI;
    }

    public DarkhastBazkharid getDarkhastBazkharidII()
    {
        return darkhastBazkharidII;
    }

    public void setDarkhastBazkharidII(DarkhastBazkharid darkhastBazkharidII)
    {
        this.darkhastBazkharidII = darkhastBazkharidII;
    }

    public KhesaratType getKhesaratType()
    {
        return khesaratType;
    }

    public void setKhesaratType(KhesaratType khesaratType)
    {
        this.khesaratType = khesaratType;
    }

    public void setElhaghiye(Elhaghiye elhaghiye)
    {
        this.elhaghiye = elhaghiye;
    }

    public String getCalcTarikhAsar()
    {
        if(this.khesaratType.equals(KhesaratType.HADESE)|| this.khesaratType.equals(KhesaratType.OMR))
            return this.accidentDate;
        int bimeYear=0;
        if(darkhastBazkharidI!=null)
        {
            bimeYear=DateUtil.getBimeYear(accidentDate,darkhastBazkharidI.getBimename().getTarikhShorou());
            return OmrUtil.calculateTarikhAsar(bimeYear+1, darkhastBazkharidI.getBimename().getTarikhShorou());
        }
        else//(darkhastBazkharidII!=null)
        {
            bimeYear =DateUtil.getBimeYear(accidentDate,darkhastBazkharidII.getBimename().getTarikhShorou());
            return OmrUtil.calculateTarikhAsar(bimeYear+1, darkhastBazkharidII.getBimename().getTarikhShorou());
        }
    }

    public String getAndukhte()
    {
        return andukhte;
    }

    public void setAndukhte(String andukhte)
    {
        this.andukhte = andukhte;
    }

    public Namayande getPronouncerOrg()
    {
        return pronouncerOrg;
    }

    public void setPronouncerOrg(Namayande pronouncerOrg)
    {
        this.pronouncerOrg = pronouncerOrg;
    }

    public Namayande getNamayande()
    {
        return namayande;
    }

    public void setNamayande(Namayande namayande)
    {
        this.namayande = namayande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }


    public KhesaratStatus getVaziatKhesarat() {
        return vaziatKhesarat;
    }

    public void setVaziatKhesarat(KhesaratStatus vaziatKhesarat) {
        this.vaziatKhesarat = vaziatKhesarat;
    }

    public String getSharhKhesarat() {
        return sharhKhesarat;
    }

    public void setSharhKhesarat(String sharhKhesarat) {
        this.sharhKhesarat = sharhKhesarat;
    }

    public String getEllat() {
        return ellat;
    }

    public void setEllat(String ellat) {
        this.ellat = ellat;
    }

    public String getNahveElam() {
        return nahveElam;
    }

    public void setNahveElam(String nahveElam) {
        this.nahveElam = nahveElam;
    }

    public City getOstanMahalleHadese() {
        return ostanMahalleHadese;
    }

    public void setOstanMahalleHadese(City ostanMahalleHadese) {
        this.ostanMahalleHadese = ostanMahalleHadese;
    }

    public City getShahrMahalleHadese() {
        return shahrMahalleHadese;
    }

    public void setShahrMahalleHadese(City shahrMahalleHadese) {
        this.shahrMahalleHadese = shahrMahalleHadese;
    }

    public String getAmountElamShode() {
        return amountElamShode;
    }

    public void setAmountElamShode(String amountElamShode) {
        this.amountElamShode = amountElamShode;
    }

    public String getAmountTaidShode() {
        return amountTaidShode;
    }

    public void setAmountTaidShode(String amountTaidShode) {
        this.amountTaidShode = amountTaidShode;
    }

    public String getAmountErfagh() {
        return amountErfagh;
    }

    public void setAmountErfagh(String amountErfagh) {
        this.amountErfagh = amountErfagh;
    }

    public String getAmountMazad() {
        return amountMazad;
    }

    public void setAmountMazad(String amountMazad) {
        this.amountMazad = amountMazad;
    }

    public String getAmountBargashti() {
        return amountBargashti;
    }

    public void setAmountBargashti(String amountBargashti) {
        this.amountBargashti = amountBargashti;
    }

    public User getKarshenasKhesarat() {
        return karshenasKhesarat;
    }

    public void setKarshenasKhesarat(User karshenasKhesarat) {
        this.karshenasKhesarat = karshenasKhesarat;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public String getNazarKarshenas() {
        return nazarKarshenas;
    }

    public void setNazarKarshenas(String nazarKarshenas) {
        this.nazarKarshenas = nazarKarshenas;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getShomareParvande() {
        return shomareParvande;
    }

    public void setShomareParvande(String shomareParvande) {
        this.shomareParvande = shomareParvande;
    }

    public List<KhesaratHavale> getHavaleList() {
        return havaleList;
    }

    public void setHavaleList(List<KhesaratHavale> havaleList) {
        this.havaleList = havaleList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isOmr() {
        return type.contains(KhesaratType.OMR.name());
    }

    public Boolean isHadese() {
        return type.contains(KhesaratType.HADESE.name());
    }

    public Boolean isMoafiat() {
        return type.contains(KhesaratType.MOAFIAT.name());
    }

    public Boolean isAmraz() {
        return type.contains(KhesaratType.AMRAZ.name());
    }

    public Boolean isNaghsOzv() {
        return type.contains(KhesaratType.NAGHSOZV.name());
    }

    public String getAmountGhabelPardakht() {
        try {
            return amountGhabelPardakht;
        } catch (Exception ex) {
            return "0";
        }
    }

    public String getAmountGhabelPardakhtFarsi() {
        return StringUtil.convertMeghdareAdadiBeHorofi(getAmountGhabelPardakht());
    }

    public String getTypeFarsi() {
        if (khesaratType.equals(KhesaratType.AMRAZ)) return "امراض خاص";
        else if (khesaratType.equals(KhesaratType.HADESE)) return "فوت در اثر حادثه";
        else if (khesaratType.equals(KhesaratType.MOAFIAT))return "معافیت و از کار افتادگی";
        else if (khesaratType.equals(KhesaratType.NAGHSOZV)) return "نقص عضو";
        else if (khesaratType.equals(KhesaratType.OMR)) return "فوت";
        else return "-";
    }

    public Integer getKoleDarsadHavaleha()
    {
        Integer darsad=0;
        if(getHavaleList()!=null && !getHavaleList().isEmpty() && getHavaleList().size()>0)
            for(KhesaratHavale h : getHavaleList())
            {
                darsad += h.getDarsadKhesarat().intValue();
            }
        return darsad;
    }

    public String getKoleAmountHavaleha()
    {
        Long amount = 0l;
        if (getHavaleList() != null && !getHavaleList().isEmpty() && getHavaleList().size() > 0)
            for (KhesaratHavale h : getHavaleList())
            {
                amount += Long.parseLong(h.getAmountHavale()!=null?h.getAmountHavale().replaceAll(",", "").trim():"0");
            }
        return NumberFormat.getInstance().format(amount);
    }

    public String getRemainingAmount()
    {
        Long amount=0l;
        if(getAmountTaidShode()==null)
            return "0";
        Long koleAmountHavale = Long.parseLong(getKoleAmountHavaleha().replaceAll(",", ""));
        if(getKoleAmountHavaleha()==null)
            koleAmountHavale=0l;
        amount =Long.parseLong(getAmountTaidShode().replaceAll(",","").trim()) - koleAmountHavale;
        return NumberFormat.getInstance().format(amount);
    }

    public Integer getRemainingDarsad()
    {
        return 100 - (getKoleDarsadHavaleha()==null?0:getKoleDarsadHavaleha());
    }

    public String calcElhaghieBargashti() {
        return NumberFormat.getNumberInstance().format(bimename.getMajmuAghsatPardakhtNashode() + bimename.getMajmuAghsatVamPardakhtNashode());
    }

    public void setAmountGhabelPardakht(String amountGhabelPardakht) {
        this.amountGhabelPardakht = amountGhabelPardakht;
    }

    public String getAmountAti() {
        return amountAti;
    }

    public void setAmountAti(String amountAti) {
        this.amountAti = amountAti;
    }

    public String getTypeKhesarat() {
        return typeKhesarat;
    }

    public void setTypeKhesarat(String typeKhesarat) {
        this.typeKhesarat = typeKhesarat;
    }

    public boolean isShowAddBtn()
    {
        if((darkhastBazkharidI!=null && !darkhastBazkharidI.getState().getId().equals(655)) || (darkhastBazkharidII!=null && !darkhastBazkharidII.getState().getId().equals(655)))
            return false;
        boolean isShow=true;
        long amountHavale=0l;
        for(KhesaratHavale havale : havaleList)
        {
            amountHavale+=Long.parseLong(havale.getAmountHavale()!=null?havale.getAmountHavale().trim().replaceAll(",",""):"0");
        }
        if(amountHavale==Long.parseLong(amountTaidShode!=null?amountTaidShode.trim().replaceAll(",",""):"0"))
            return false;
        return true;
    }

    public String getKoleHaghBimeField()
    {
        long amount = 0;
        DarkhastBazkharid d=darkhastBazkharidI!=null?darkhastBazkharidI:darkhastBazkharidII;
        if (getKhesaratType().equals(Khesarat.KhesaratType.OMR) || getKhesaratType().equals(Khesarat.KhesaratType.HADESE))
        {
            List<GhestBandi> gbList = d.getBimename().getGhestBandiList();
            for (GhestBandi gb : gbList)
            {
                if (gb.getType().equals(GhestBandi.Type.G_BIMENAME))
                    for (Ghest g : gb.getGhestList())
                    {
                        if (g.getTarikhPardakht() == null || g.getTarikhPardakht().isEmpty())
                            amount += g.getCredebit().getRemainingAmount_long();
                    }
            }
        }
        if (getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ) || getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
        {
            int accidentBimeYear = DateUtil.getBimeYear(getAccidentDate(), d.getBimename().getTarikhShorou());
            for (GhestBandi gb : d.getBimename().getGhestBandiList())
            {
                if (gb.getSaleBimeiInt() > accidentBimeYear)
                {
                    for (Ghest g : gb.getGhestList())
                    {
                        if (getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                        {
                            amount += Long.parseLong(g.getHaghBimePoosheshAmraz().replaceAll(",", ""));
                        }
                        if (getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                        {
                            amount += Long.parseLong(g.getHaghBimePoosheshNaghsOzv().replaceAll(",", ""));
                        }
                    }
                }
            }
        }
        return "-" + Long.toString(amount);
    }

    public boolean isTimeWaitingAmraz()
    {
        return darkhastBazkharidI.getBimename().isTimeWaitingAmraz(getAccidentDate());
    }
}
