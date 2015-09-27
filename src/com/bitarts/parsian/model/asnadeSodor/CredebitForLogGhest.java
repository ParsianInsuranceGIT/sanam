package com.bitarts.parsian.model.asnadeSodor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 1:39 PM
 */
@Entity
@Table(name = "tbl_credebitForLogGhest")
public class CredebitForLogGhest implements Serializable {
    public static final String ID = "id";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_created_date")
    private String createdDate;
    @Column(name = "c_sarresid")
    private String sarresidDate;
    @Column(name = "c_amount")
    private String amount;
    @Column(name = "c_shomare_moshtari")
    private String shomareMoshtari;
    @Column(name = "c_shenase_credebit")
    private String shenaseCredebit;
    @ManyToOne
    @JoinColumn(name = "c_logGhest")
    private LogGhest logGhest;
    @Column(name = "c_after")
    private Boolean afterChange;

    public CredebitForLogGhest() {
    }

    public CredebitForLogGhest(String createdDate, String sarresidDate, String amount, String shomareMoshtari, String shenaseCredebit, LogGhest logGhest, Boolean afterChange) {
        this.createdDate = createdDate;
        this.sarresidDate = sarresidDate;
        this.amount = amount;
        this.shomareMoshtari = shomareMoshtari;
        this.shenaseCredebit = shenaseCredebit;
        this.logGhest = logGhest;
        this.afterChange = afterChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getShenaseCredebit() {
        return shenaseCredebit;
    }

    public void setShenaseCredebit(String shenaseCredebit) {
        this.shenaseCredebit = shenaseCredebit;
    }

    public LogGhest getLogGhest() {
        return logGhest;
    }

    public void setLogGhest(LogGhest logGhest) {
        this.logGhest = logGhest;
    }

    public String getSarresidDate() {
        return sarresidDate;
    }

    public void setSarresidDate(String sarresidDate) {
        this.sarresidDate = sarresidDate;
    }

    public Boolean getAfterChange() {
        return afterChange;
    }

    public void setAfterChange(Boolean afterChange) {
        this.afterChange = afterChange;
    }
}
