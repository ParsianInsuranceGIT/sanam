package com.bitarts.parsian.model;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_bankinfo")
public class BankInfo implements Serializable,Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "main_id")
    private Integer mainId;
    @Column(name = "state_id")
    private Integer stateId;
    @Column(name = "serial_id")
    private Long serialId;
    @Column(name = "receiptN")
    private String kodeDaryaft;
    @Column(name = "taarikh")
    private String taarikh;
    @Column(name = "mablagh")
    private String mablagh;
    @Column(name = "kode_shobe")
    private String kodeShobe;
    @Column(name = "docNo")
    private String shomareFish;
    @Column(name = "time")
    private String time;
    @Column(name = "bardasht")
    private String bardasht;
    @Column(name = "description")
    private String desc;
    @Column(name = "codeMoshtari")
    private String codeMoshtari;
    @Column(name = "Id_Uploaded")
    private String idUploaded;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "id")//todo: bargozarandeh_Id
    private Bargozarandeh bargozarandeh;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BankInfoStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credebit_id")
    private Credebit credebit;

    public int compareTo(Object o)
    {
        return this.status.toString().compareTo(((BankInfo) o).getStatus().toString()) ;
    }


    public static enum BankInfoStatus {
        TEKRARI,
        TEKRARI_CM,
        VOSOL_TEKRARI,
        Z_MABLAGH_MAZAD,
        Z_MABLAGH_KAMTAR,
        Z_MABLAGH_MOSAVI,
        SANAD_KHORDE,
        NO_GHEST,
        IJAD_CREDEBIT,
        VOSOL_TEKRARI_GHEYRE_MOJAZ,
        Z_AGHSAT_OMR,
        Z_PISH_PARDAKHT_OMR,
        INVALID_DATE,
        INVALID_AMOUNT,
        AGHSAT_OMR_MABLAGH_KAMTAR,
        AGHSAT_OMR_MABLAGH_MAZAD,
        NOT_MATCH_SHOMARE_HESAB
    }

    public BankInfo(){

    }

    //Pardakht Interneti
    public BankInfo(String mablagh, String description, String codeMoshtari, Credebit credebit){
        this.mablagh = mablagh;
        this.desc = description;
        this.credebit = credebit;
        this.codeMoshtari = codeMoshtari;
        this.taarikh = DateUtil.getCurrentDate();
        this.time = DateUtil.getCurrentTime();
        this.createdDate = DateUtil.getCurrentDate();
        this.createdTime = DateUtil.getCurrentTime();
        this.stateId = -4;
        this.status = BankInfoStatus.Z_MABLAGH_MOSAVI;
    }

    public Long getSerialId()
    {
        return serialId;
    }

    public void setSerialId(Long serialId)
    {
        this.serialId = serialId;
    }

    public Integer getStateId()
    {
        return stateId;
    }

    public void setStateId(Integer stateId)
    {
        this.stateId = stateId;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
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

    public String getShomareFish() {
        return shomareFish;
    }

    public void setShomareFish(String shomareFish) {
        this.shomareFish = shomareFish;
    }

    public Bargozarandeh getBargozarandeh() {
        return bargozarandeh;
    }

    public void setBargozarandeh(Bargozarandeh bargozarandeh) {
        this.bargozarandeh = bargozarandeh;
    }

    public String getKodeShobe() {
        return kodeShobe;
    }

    public void setKodeShobe(String kodeShobe) {
        this.kodeShobe = kodeShobe;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBardasht() {
        return bardasht;
    }

    public void setBardasht(String bardasht) {
        this.bardasht = bardasht;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIdUploaded() {
        return idUploaded;
    }

    public void setIdUploaded(String idUploaded) {
        this.idUploaded = idUploaded;
    }

    public String getKodeDaryaft() {
        return kodeDaryaft;
    }

    public void setKodeDaryaft(String kodeDaryaft) {
        this.kodeDaryaft = kodeDaryaft;
    }

    public String getCodeMoshtari() {
        return codeMoshtari;
    }

    public void setCodeMoshtari(String codeMoshtari) {
        this.codeMoshtari = codeMoshtari;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public BankInfoStatus getStatus() {
        return status;
    }

    public void setStatus(BankInfoStatus status) {
        this.status = status;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public String getVaziyateEtebar(){
        if (getStateId() != null)  {
            if (getStateId() == -1)
                return "پرداخت قسط";
            else if (getStateId() == -4)
                return "تایید وصول";
            else if (getStateId() == -5)
                return "مبلغ نامتعبر";
        }
        return "";
    }

    public String getVaziyateEtebarDesc(){
    if (getStatus() != null){
            if (getStatus().equals(BankInfoStatus.TEKRARI))
                return "تکراری";
            else if (getStatus().equals(BankInfoStatus.VOSOL_TEKRARI))
                return "وصول تکراری";
            else if (getStatus().equals(BankInfoStatus.Z_MABLAGH_MAZAD))
                return "مبلغ مازاد";
            else if (getStatus().equals(BankInfoStatus.Z_MABLAGH_KAMTAR))
                return "مبلغ کمتر";
            else if (getStatus().equals(BankInfoStatus.Z_MABLAGH_MOSAVI))
                return "مبلغ مساوی";
            else if (getStatus().equals(BankInfoStatus.SANAD_KHORDE))
                return "سند خورده";
            else if (getStatus().equals(BankInfoStatus.NO_GHEST))
                return "قسط پیدا نشد";
            else if (getStatus().equals(BankInfoStatus.IJAD_CREDEBIT))
                return "ایجاد موجودیت";
            else if (getStatus().equals(BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ))
                return "وصول تکراری غیر مجاز";
            else if (getStatus().equals(BankInfoStatus.Z_AGHSAT_OMR))
                return "اقساط عمر";
            else if (getStatus().equals(BankInfoStatus.Z_PISH_PARDAKHT_OMR))
                return "پیش پرداخت عمر";
            else if (getStatus().equals(BankInfoStatus.INVALID_DATE))
                return "ناریخ یا زمان معتبر نیست";
            else if (getStatus().equals(BankInfoStatus.INVALID_AMOUNT))
                return "مبلغ معتبر نیست";
            else if (getStatus().equals(BankInfoStatus.AGHSAT_OMR_MABLAGH_KAMTAR))
                return "مبلغ کمتر";
            else if (getStatus().equals(BankInfoStatus.AGHSAT_OMR_MABLAGH_MAZAD))
                return "مبلغ مازاد";
            else if (getStatus().equals(BankInfoStatus.TEKRARI_CM))
                return "کد مشتری تکراری";
            else if (getStatus().equals(BankInfoStatus.NOT_MATCH_SHOMARE_HESAB))
                return "عدم همخوانی شماره حساب مربوط به کد مشتری با شماره حساب انتخاب شده";
        }
        return "";
    }
}
