package com.bitarts.parsian.model.asnadeSodor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Sep 4, 2011
 * Time: 7:23:51 PM
 */
@Entity
@Table(name = "tbl_daryafte_check")
public class DaryafteCheck implements Serializable {


    public static enum Status {
        NAZD_SANDOGH, VAGOZAR_SHODE, VOSUL, MOSTARAD
    }

    public static enum Type {
        NORMAL, BARAT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hesab_banki")
    private String hesabBanki;
    @Column(name = "tarikh_sarresid")
    private String tarikhSarresid;
    @Column(name = "dar_vajh")
    private String darVajh;
    @Column(name = "tozihat")
    private String tozihat;
    @Column(name = "hesab_varizi")
    private String hesabeVarizi;

    @Column(name = "serial_number")
    private String serial;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "branch_code")
    private String branchCode;
    @Column(name = "account_owner_name")
    private String accountOwnerName;
    @Column(name = "type")
    private Type type;
    @Column(name = "seri")
    private String seri;


    @OneToOne(mappedBy = "daryafteCheck", fetch = FetchType.LAZY)
    private Credebit credebit;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public DaryafteCheck() {
    }

    public DaryafteCheck(String hesabBanki, String tarikhSarresid, String darVajh, String tozihat, String hesabeVarizi, Status status) {
        this.hesabBanki = hesabBanki;
        this.tarikhSarresid = tarikhSarresid;
        this.darVajh = darVajh;
        this.tozihat = tozihat;
        this.hesabeVarizi = hesabeVarizi;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getHesabBanki() {
        return hesabBanki;
    }

    public void setHesabBanki(String hesabBanki) {
        this.hesabBanki = hesabBanki;
    }

    public String getTarikhSarresid() {
        return tarikhSarresid;
    }

    public void setTarikhSarresid(String tarikhSarresid) {
        this.tarikhSarresid = tarikhSarresid;
    }

    public String getDarVajh() {
        return darVajh;
    }

    public void setDarVajh(String darVajh) {
        this.darVajh = darVajh;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusFarsi() {
        if(status.equals(Status.NAZD_SANDOGH))
            return "نزد صندوق";
        else if (status.equals(Status.VAGOZAR_SHODE))
            return "واگذار شده";
        else if (status.equals(Status.VOSUL))
            return "وصول شده";
        else if (status.equals(Status.MOSTARAD))
            return "مسترد";
        return "-";
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHesabeVarizi() {
        return hesabeVarizi;
    }

    public void setHesabeVarizi(String hesabeVarizi) {
        this.hesabeVarizi = hesabeVarizi;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }
}
