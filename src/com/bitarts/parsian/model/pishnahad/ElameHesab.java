package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/20/11
 * Time: 9:53 AM
 */
@Entity
@Table(name = "tbl_elam_hesab")
public class ElameHesab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "elam_id")
    private Integer id;

    @Column(name = "hesab_banki")
    private String shomareHesabBanki;

    @Column(name = "shomare_shaba")
    private String shomareShaba;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "shobe_name")
    private String shobeName;

    @Column(name = "shobe_kod")
    private String shobeKod;

    @Column(name = "sahebHesab")
    private String sahebHesab;

    @Column(name = "taarikh")
    private String taarikh;

    @Column(name = "mablagh")
    private String mablagh;

    @Column(name = "majmu_pishpardakht")
    private String majmuPishpardakht;

    @Column(name = "majmu_hazine_pezeshki")
    private String majmuHazinePezeshki;

    @Column(name = "shomare_nameh")
    private String shomareNameh;


    @OneToOne(mappedBy = "elameHesab", fetch = FetchType.LAZY)
    private Pishnehad pishnehad;

    public ElameHesab() {

    }

    public ElameHesab(String shomareHesabBanki, String shomareShaba, String bankName, String shobeName, String shobeKod, String sahebHesab, String taarikh, String mablagh, String majmuPishpardakht, String majmuHazinePezeshki, String shomareNameh) {
        this.shomareHesabBanki = shomareHesabBanki;
        this.shomareShaba = shomareShaba;
        this.bankName = bankName;
        this.shobeName = shobeName;
        this.shobeKod = shobeKod;
        this.sahebHesab = sahebHesab;
        this.taarikh = taarikh;
        this.mablagh = mablagh;
        this.majmuPishpardakht = majmuPishpardakht;
        this.majmuHazinePezeshki = majmuHazinePezeshki;
        this.shomareNameh = shomareNameh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getSahebHesab() {
        return sahebHesab;
    }

    public void setSahebHesab(String sahebHesab) {
        this.sahebHesab = sahebHesab;
    }

    public String getShobeKod() {
        return shobeKod;
    }

    public void setShobeKod(String shobeKod) {
        this.shobeKod = shobeKod;
    }

    public String getShobeName() {
        return shobeName;
    }

    public void setShobeName(String shobeName) {
        this.shobeName = shobeName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getShomareShaba() {
        return shomareShaba;
    }

    public void setShomareShaba(String shomareShaba) {
        this.shomareShaba = shomareShaba;
    }

    public String getShomareHesabBanki() {
        return shomareHesabBanki;
    }

    public void setShomareHesabBanki(String shomareHesabBanki) {
        this.shomareHesabBanki = shomareHesabBanki;
    }

    public String getTaarikh() {
        return taarikh;
    }

    public void setTaarikh(String taarikh) {
        this.taarikh = taarikh;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public String getMajmuPishpardakht() {
        return majmuPishpardakht;
    }

    public void setMajmuPishpardakht(String majmuPishpardakht) {
        this.majmuPishpardakht = majmuPishpardakht;
    }

    public String getMajmuHazinePezeshki() {
        return majmuHazinePezeshki;
    }

    public void setMajmuHazinePezeshki(String majmuHazinePezeshki) {
        this.majmuHazinePezeshki = majmuHazinePezeshki;
    }

    public String getShomareNameh() {
        return shomareNameh;
    }

    public void setShomareNameh(String shomareNameh) {
        this.shomareNameh = shomareNameh;
    }
}
