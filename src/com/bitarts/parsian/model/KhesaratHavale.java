package com.bitarts.parsian.model;

import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.constantItems.City;

import javax.persistence.*;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/30/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_khesaratHavale")
public class KhesaratHavale {
    public enum DaryaftKonande {
        BIMEGOZAR, BIMESHODE, ZINAF
    }
    public enum Type
    {
        CHECK,ACH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_shomareDakheli")
    private String shomareDakheli;
    @Column(name = "c_createdDate")
    private String createdDate;
    @Column(name = "c_typeHavale")
    private String typeHavale;
    @Column(name = "c_amalkardHavale")
    private String amalkardHavale;
    @Column(name = "c_shomareHavale")
    private String shomareHavale;
    @Column(name = "c_tarikhHavale")
    private String tarikhHavale;
    @Column(name = "c_amountHavale")
    private String amountHavale;
    @Column(name = "c_typeReciever")
    @Enumerated(EnumType.STRING)
    private DaryaftKonande typeReciever;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_family")
    private String family;
    @Column(name = "c_shomareHesab")
    private String shomareHesab;

    @Column(name = "c_shomareShenasname")
    private String shomareShenasname;

    @Column(name = "c_shomareShaba")
    private String shomareShaba;

    @Column(name = "c_code_melli")
    private String codeMelli;
    @JoinColumn(name = "c_mahalSodur")
    @ManyToOne
    private City mahalleSodur;
    @JoinColumn(name = "c_userCreator")
    @ManyToOne
    private User userCreator;
    @JoinColumn(name = "c_karshenasParvande")
    @ManyToOne
    private User karshenasParvande;
    @Column(name = "c_darsadKhesarat")
    private Double darsadKhesarat;
    @Column(name = "c_nesbat_ba_bimeShode")
    private String nesbatBabimeShode;
    @Column(name = "c_elamBeMaliShode")
    private Boolean elamBeMaliShode;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Credebit credebit;
    @JoinColumn(name = "c_khesarat")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private Khesarat khesarat;
    @Column(name = "c_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public String getTypeFa()
    {
        if(type != null && type.equals(Type.ACH))return "ACH";
        if(type != null && type.equals(Type.CHECK))return "چــک";
        return "";
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getShomareShaba()
    {
        return shomareShaba;
    }

    public void setShomareShaba(String shomareShaba)
    {
        this.shomareShaba = shomareShaba;
    }

    public String getCodeMelli() {
        return codeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        this.codeMelli = codeMelli;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShomareDakheli() {
        return shomareDakheli;
    }

    public void setShomareDakheli(String shomareDakheli) {
        this.shomareDakheli = shomareDakheli;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTypeHavale() {
        return typeHavale;
    }

    public void setTypeHavale(String typeHavale) {
        this.typeHavale = typeHavale;
    }

    public String getAmalkardHavale() {
        return amalkardHavale;
    }

    public void setAmalkardHavale(String amalkardHavale) {
        this.amalkardHavale = amalkardHavale;
    }

    public String getShomareHavale() {
        return shomareHavale;
    }

    public void setShomareHavale(String shomareHavale) {
        this.shomareHavale = shomareHavale;
    }

    public String getTarikhHavale() {
        return tarikhHavale;
    }

    public void setTarikhHavale(String tarikhHavale) {
        this.tarikhHavale = tarikhHavale;
    }

    public String getAmountHavale() {
        return amountHavale;
    }
    public String getPercent()
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");

        return String.valueOf(Double.valueOf(twoDForm.format(Double.parseDouble(amountHavale.trim().replaceAll(",", "")) * 100.0 / Double.parseDouble(khesarat.getAmountTaidShode().trim().replaceAll(",","")))));
    }

    public void setAmountHavale(String amountHavale) {
        this.amountHavale = amountHavale;
    }

    public DaryaftKonande getTypeReciever() {
        return typeReciever;
    }

    public void setTypeReciever(DaryaftKonande typeReciever) {
        this.typeReciever = typeReciever;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName()
    {
        return (name == null ? "" : name) + " " + (family== null ? "" : family);
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getShomareHesab() {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab) {
        this.shomareHesab = shomareHesab;
    }

    public String getShomareShenasname() {
        return shomareShenasname;
    }

    public void setShomareShenasname(String shomareShenasname) {
        this.shomareShenasname = shomareShenasname;
    }

    public City getMahalleSodur() {
        return mahalleSodur;
    }

    public void setMahalleSodur(City mahalleSodur) {
        this.mahalleSodur = mahalleSodur;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public User getKarshenasParvande() {
        return karshenasParvande;
    }

    public void setKarshenasParvande(User karshenasParvande) {
        this.karshenasParvande = karshenasParvande;
    }

    public Double getDarsadKhesarat() {
        return darsadKhesarat;
    }

    public void setDarsadKhesarat(Double darsadKhesarat) {
        this.darsadKhesarat = darsadKhesarat;
    }

    public String getNesbatBabimeShode() {
        return nesbatBabimeShode;
    }

    public void setNesbatBabimeShode(String nesbatBabimeShode) {
        this.nesbatBabimeShode = nesbatBabimeShode;
    }

    public Boolean getElamBeMaliShode() {
        if (elamBeMaliShode==null) return false;
        return elamBeMaliShode;
    }

    public void setElamBeMaliShode(Boolean elamBeMaliShode) {
        this.elamBeMaliShode = elamBeMaliShode;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public Khesarat getKhesarat() {
        return khesarat;
    }

    public void setKhesarat(Khesarat khesarat) {
        this.khesarat = khesarat;
    }

    public String getTypeRecieverFarsi() {
        switch (typeReciever) {
            case BIMEGOZAR:
                return "بیمه گذار";
            case BIMESHODE:
                return "بیمه شده";
            case ZINAF:
                return "ذینفع";
            default:
                return "";
        }
    }

    public String getAmountHavaleFarsi() {
        return StringUtil.convertMeghdareAdadiBeHorofi(amountHavale);
    }
}

