package com.bitarts.parsian.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_bargozarandeh")
public class Bargozarandeh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name_bank")
    private String nameBank;
    @Column(name = "name_shobe")
    private String nameShobe;
    @Column(name = "taarikh")
    private String taarikh;
    @Column(name="kode_shobe")
    private String kodeShobe;
    @Column(name = "shomare_hesab")
    private String shomareHesab;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "bargozarandeh",fetch = FetchType.LAZY)
    private Set<BankInfo> bankInfos;
    //b-h
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daftar_id")
    private Daftar daftar;

    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }

    public String getKodeShobe()
    {
        return kodeShobe;
    }

    public void setKodeShobe(String kodeShobe)
    {
        this.kodeShobe = kodeShobe;
    }

    public String getShomareHesab()
    {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab)
    {
        this.shomareHesab = shomareHesab;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getNameShobe() {
        return nameShobe;
    }

    public void setNameShobe(String nameShobe) {
        this.nameShobe = nameShobe;
    }

    public String getTaarikh() {
        return taarikh;
    }

    public void setTaarikh(String taarikh) {
        this.taarikh = taarikh;
    }

    public Set<BankInfo> getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(Set<BankInfo> bankInfos) {
        this.bankInfos = bankInfos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
