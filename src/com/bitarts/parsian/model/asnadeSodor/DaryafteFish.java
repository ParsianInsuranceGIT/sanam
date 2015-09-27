package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.BankInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Sep 4, 2011
 * Time: 7:23:51 PM
 */
@Entity
@Table(name = "tbl_daryafte_fish")
public class DaryafteFish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sahebe_etebar")
    private String sahebeEtebar;
    @Column(name = "bank")
    private String bank;
    @Column(name = "kode_shobe")
    private String kodeShobe;

    @Column(name = "tozihat")
    private String tozihat;
    @Column(name = "shomareSanadBank")
    private String shomareSanadBank;
    @Column(name = "tarikh")
    private String tarikh;
    @Column(name = "time")
    private String time;
    @Column(name = "shomareFish")
    private String shomareFish;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="bank_info_id" )
    private BankInfo bankInfo;

    public BankInfo getBankInfo()
    {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo)
    {
        this.bankInfo = bankInfo;
    }

    public DaryafteFish(String sahebeEtebar, String bank, String kodeShobe, String tozihat, String shomareSanadBank) {
        this.sahebeEtebar = sahebeEtebar;
        this.bank = bank;
        this.kodeShobe = kodeShobe;
        this.tozihat = tozihat;
        this.shomareSanadBank = shomareSanadBank;
    }

    public DaryafteFish() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSahebeEtebar() {
        return sahebeEtebar;
    }

    public void setSahebeEtebar(String sahebeEtebar) {
        this.sahebeEtebar = sahebeEtebar;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTozihat() {
        if(tozihat == null)
            return "N/A";
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getKodeShobe() {
        return kodeShobe;
    }

    public void setKodeShobe(String kodeShobe) {
        this.kodeShobe = kodeShobe;
    }

    public String getShomareSanadBank() {
        if(shomareSanadBank == null)
            return "N/A";
        return shomareSanadBank;
    }

    public void setShomareSanadBank(String shomareSanadBank) {
        this.shomareSanadBank = shomareSanadBank;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShomareFish() {
        return shomareFish;
    }

    public void setShomareFish(String shomareFish) {
        this.shomareFish = shomareFish;
    }
}
