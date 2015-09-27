package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/20/11
 * Time: 9:53 AM
 */
@Entity
@Table(name = "tbl_fish")
@BatchSize(size = 10)
public class Fish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fish_mablagh")
    private String mablagh;

    @Column(name = "fish_shomare")
    private String shomare;

    @Column(name = "fish_tarikh")
    private String tarikh;

    @Column(name = "fish_time")
    private String time;

    @Column(name = "fish_bankname")
    private String bankName;

    @Column(name = "fish_kodeshobe")
    private String kodeShobe;

    @Column(name = "fish_isfound")
    private String isFound;

    @Column(name = "fish_type")
    private String paymentType;


    @Column(name = "file_fish_id")
    private Integer filePishId;
    @Column(name = "file_fish_name")
    private String filePishName;

    @Column(name = "file_scanned_id")
    private Integer fileScannedId;
    @Column(name = "file_scanned_name")
    private String fileScannedName;

    @Column(name = "file_other_id")
    private Integer fileOtherId;
    @Column(name = "file_other_name")
    private String fileOtherName;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "pishnahad_id")
    private Pishnehad pishnehad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credebit_id")
    private Credebit credebit;

    public Fish() {

    }

    public Fish(String mablagh, String shomare, String tarikh, String time, String bankName, String kodeShobe, String found, String paymentType, Integer filePishId, String filePishName, Integer fileScannedId, String fileScannedName, Integer fileOtherId, String fileOtherName, Pishnehad pishnehad, Credebit credebit) {
        this.mablagh = mablagh;
        this.shomare = shomare;
        this.tarikh = tarikh;
        this.time = time;
        this.bankName = bankName;
        this.kodeShobe = kodeShobe;
        isFound = found;
        this.paymentType = paymentType;
        this.filePishId = filePishId;
        this.filePishName = filePishName;
        this.fileScannedId = fileScannedId;
        this.fileScannedName = fileScannedName;
        this.fileOtherId = fileOtherId;
        this.fileOtherName = fileOtherName;
        this.pishnehad = pishnehad;
        this.credebit = credebit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public Integer getFilePishId() {
        return filePishId;
    }

    public void setFilePishId(Integer filePishId) {
        this.filePishId = filePishId;
    }

    public String getFilePishName() {
        return filePishName;
    }

    public void setFilePishName(String filePishName) {
        this.filePishName = filePishName;
    }

    public Integer getFileScannedId() {
        return fileScannedId;
    }

    public void setFileScannedId(Integer fileScannedId) {
        this.fileScannedId = fileScannedId;
    }

    public String getFileScannedName() {
        return fileScannedName;
    }

    public void setFileScannedName(String fileScannedName) {
        this.fileScannedName = fileScannedName;
    }

    public Integer getFileOtherId() {
        return fileOtherId;
    }

    public void setFileOtherId(Integer fileOtherId) {
        this.fileOtherId = fileOtherId;
    }

    public String getFileOtherName() {
        return fileOtherName;
    }

    public void setFileOtherName(String fileOtherName) {
        this.fileOtherName = fileOtherName;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getMablagh() {
        try {
            return NumberFormat.getNumberInstance().format(Long.parseLong(mablagh.replace(",", "").trim()));
        } catch (Exception ex) {
            return mablagh;
        }
    }

    public String getMablaghFormatted() {
        return NumberFormat.getInstance().format(Long.valueOf(mablagh.replaceAll(",", "")));
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getKodeShobe() {
        return kodeShobe;
    }

    public void setKodeShobe(String kodeShobe) {
        this.kodeShobe = kodeShobe;
    }

    public String getFound() {
        return isFound;
    }

    public void setFound(String found) {
        isFound = found;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }
}
