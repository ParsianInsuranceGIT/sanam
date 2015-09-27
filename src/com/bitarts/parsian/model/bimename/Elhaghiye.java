package com.bitarts.parsian.model.bimename;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_elhaghiye")
public class Elhaghiye implements Serializable, Comparable<Elhaghiye> {




    public static enum ElhaghiyeType {
        BAZKHARID, BARDASHT_AZ_ANDOKHTE, VAM, TASVIE_PISH_AZ_MOEDE_VAM, TAGHIRKOD, TOZIH, TAGHYIRAT, EBTAL, FASKH, KHESARAT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "elhaghiye_id")
    private Integer id;
    @Column(name = "tarikh_asar")
    private String tarikhAsar;
    @Column(name = "tarikh_created")
    private String createdDate;
    @Column(name = "shomare_elhaghiye")
    private String shomareElhaghiye;
    @Column(name = "radif_elhaghiye")
    private String radifElhaghiye;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id1")
    private EmzaKonande emzaKonandeAval;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emzakonande_id2")
    private EmzaKonande emzaKonandeDovom;

    @Column(name = "elhaghiye_mozoo")
    private String mozoo;
    @Column(name = "elhaghiye_matn",length = 2000)
    private String matn;

    // todo: store long strings in db
    @Column(name = "elhaghiye_log")
    private String log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name = "elhaghiye_type")
    @Enumerated(EnumType.STRING)
    private ElhaghiyeType elhaghiyeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bimename_id")
    private Bimename bimename;
    //------------------------------
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "darkhast_elhaghie_id")
//    private DarkhastBazkharid darkhastBazkharid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_elhaghie_id")
    private DarkhastBazkharid darkhastBazkharid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khesarat_id")
    private Khesarat khesarat;

    @ManyToOne
    @JoinColumn(name="user_sader_konande_id")
    private User userSaderKonande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedSodor_id")
    private Namayande vahedSodor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_PISHNEHAD_ID")
    private Pishnehad oldPishnehad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_PISHNEHAD_ID")
    private Pishnehad newPishnehad;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "darkhast_id")
//    private Darkhast darkhast;

    public Pishnehad getOldPishnehad()
    {
        return oldPishnehad;
    }

    public void setOldPishnehad(Pishnehad oldPishnehad)
    {
        this.oldPishnehad = oldPishnehad;
    }

    public Pishnehad getNewPishnehad()
    {
        return newPishnehad;
    }

    public void setNewPishnehad(Pishnehad newPishnehad)
    {
        this.newPishnehad = newPishnehad;
    }

    public Khesarat getKhesarat()
    {
        return khesarat;
    }

    public void setKhesarat(Khesarat khesarat)
    {
        this.khesarat = khesarat;
    }

    public User getUserSaderKonande()
    {
        return userSaderKonande;
    }

    public void setUserSaderKonande(User userSaderKonande)
    {
        this.userSaderKonande = userSaderKonande;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_id")
    private Darkhast darkhast;

    @Column(name = "mablagh")
    private String mablagh;

    @Column(name = "hazinePezeshki")
    private String hazinePezeshki;
    @Column(name = "mablaghPardakhtiBimegozar")
    private String mablaghPardakhtiBimegozar;


    public String getHazinePezeshki()
    {
        return hazinePezeshki;
    }

    public void setHazinePezeshki(String hazinePezeshki)
    {
        this.hazinePezeshki = hazinePezeshki;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarikhAsar() {
        return tarikhAsar;
    }

    public int getSaleBimeiAsar() {
        int sal = 0;
        String checkDate = getBimename().getTarikhShorou();
        int term = 0;
        while(true) {
            term++;
            if(DateUtil.isGreaterThan(DateUtil.addYear(checkDate, 1), getTarikhAsar()) && (DateUtil.isGreaterThanOrEqual(getTarikhAsar(), checkDate))) {
                return sal;
            } else {
                checkDate = DateUtil.addYear(checkDate, 1);
                sal++;
            }
            if(term == 200)
                return -1;
        }
    }

    public void setTarikhAsar(String tarikhAsar) {
        this.tarikhAsar = tarikhAsar;
    }

    public String getShomareElhaghiye() {
        return shomareElhaghiye;
    }

    public void setShomareElhaghiye(String shomareElhaghiye) {
        this.shomareElhaghiye = shomareElhaghiye;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public ElhaghiyeType getElhaghiyeType() {
        return elhaghiyeType;
    }

    public void setElhaghiyeType(ElhaghiyeType elhaghiyeType) {
        this.elhaghiyeType = elhaghiyeType;
    }

    public String getElhaghieTypeFarsi() {
        if (elhaghiyeType.equals(ElhaghiyeType.BARDASHT_AZ_ANDOKHTE)) return "برداشت از اندوخته";
        if (elhaghiyeType.equals(ElhaghiyeType.BAZKHARID)) return "بازخرید";
        if (elhaghiyeType.equals(ElhaghiyeType.TAGHYIRAT)) return "تغییرات";
        if (elhaghiyeType.equals(ElhaghiyeType.TASVIE_PISH_AZ_MOEDE_VAM)) return "تسویه پیش از موعد وام";
        if (elhaghiyeType.equals(ElhaghiyeType.TAGHIRKOD)) return "تغییر کد نمایندگی";
        if (elhaghiyeType.equals(ElhaghiyeType.TOZIH)) return "الحاقیه توضیح";
        if (elhaghiyeType.equals(ElhaghiyeType.VAM)) return "وام";
        if (elhaghiyeType.equals(ElhaghiyeType.EBTAL)) return "ابطال";
        if (elhaghiyeType.equals(ElhaghiyeType.KHESARAT)) return "خسارت";
        return "";
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public Namayande getVahedSodor()
    {
        return vahedSodor;
    }

    public void setVahedSodor(Namayande vahedSodor)
    {
        this.vahedSodor = vahedSodor;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int compareTo(Elhaghiye o) {
        if (id.equals(o.id)) return 0;
        else if (DateUtil.isGreaterThan(createdDate, o.createdDate)) return 1;
        else return -1;
    }

    public String getRadifElhaghiye() {
        return radifElhaghiye;
    }

    public void setRadifElhaghiye(String radifElhaghiye) {
        this.radifElhaghiye = radifElhaghiye;
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

    public Darkhast getDarkhast() {
        return darkhast;
    }

    public void setDarkhast(Darkhast darkhast) {
        this.darkhast = darkhast;
    }

    public String getMozoo() {
        return mozoo;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public String getMablaghPardakhtiBimegozar() {
        return mablaghPardakhtiBimegozar;
    }

    public void setMablaghPardakhtiBimegozar(String mablaghPardakhtiBimegozar) {
        this.mablaghPardakhtiBimegozar = mablaghPardakhtiBimegozar;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}