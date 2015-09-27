package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_khate_sanad_log")
public class KhateSanadLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private String amount;
    @Column(name = "etebarRemaining")
    private String etebarRemaining;
    @Column(name = "bedehiRemaining")
    private String bedehiRemaining;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etebar_credebit_id")
    private Credebit etebarCredebit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bedehi_credebit_id")
    private Credebit bedehiCredebit;
    @JoinColumn(name = "sanad_id")
    private Integer sanadId;
    @JoinColumn(name = "sanad_shomare")
    private String shomareSanad;
    @Column(name = "username_")
    private String username;
    @JoinColumn(name = "operation_sanad")
    private String operationSanad;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "created_time")
    private String createdTime;


    public KhateSanadLog() {
    }

    public KhateSanadLog(String amount, String etebarRemaining, String bedehiRemaining, Credebit etebarCredebit, Credebit bedehiCredebit,Integer sanadId,String shomareSanad,String username,String operationSanad) {
        this.amount = amount;
        this.etebarRemaining = etebarRemaining;
        this.bedehiRemaining = bedehiRemaining;
        this.etebarCredebit = etebarCredebit;
        this.bedehiCredebit = bedehiCredebit;
        this.sanadId = sanadId;
        this.shomareSanad = shomareSanad;
        this.username = username;
        this.operationSanad = operationSanad;
        this.createdDate = DateUtil.getCurrentDate();
        this.createdTime = DateUtil.getCurrentTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEtebarRemaining() {
        return etebarRemaining;
    }

    public void setEtebarRemaining(String etebarRemaining) {
        this.etebarRemaining = etebarRemaining;
    }

    public String getBedehiRemaining() {
        return bedehiRemaining;
    }

    public void setBedehiRemaining(String bedehiRemaining) {
        this.bedehiRemaining = bedehiRemaining;
    }

    public Credebit getEtebarCredebit() {
        return etebarCredebit;
    }

    public void setEtebarCredebit(Credebit etebarCredebit) {
        this.etebarCredebit = etebarCredebit;
    }

    public Credebit getBedehiCredebit() {
        return bedehiCredebit;
    }

    public void setBedehiCredebit(Credebit bedehiCredebit) {
        this.bedehiCredebit = bedehiCredebit;
    }

    public Integer getSanadId() {
        return sanadId;
    }

    public void setSanadId(Integer sanadId) {
        this.sanadId = sanadId;
    }

    public String getShomareSanad() {
        return shomareSanad;
    }

    public void setShomareSanad(String shomareSanad) {
        this.shomareSanad = shomareSanad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperationSanad() {
        return operationSanad;
    }

    public void setOperationSanad(String operationSanad) {
        this.operationSanad = operationSanad;
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
}
