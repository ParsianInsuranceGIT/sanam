package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sabzechian
 */
public class CredebitVM
{
    private String rcptName;
    private Long namayandeId;
    private String namayandeName;
    private String namayandeCode;
    private String createdDate;
    private String amount;
    private String remainingAmount;
    private Long paidReceivedAmount;
    private String identifier;
    private String shomareGharardad;
    private Credebit.Status status;
    private String statusFarsi;
    private String sarresidDate;
    private String sarresidDateWithMohlatSarresid;
    private String amountKhateSanad;
    private Integer finalSarresidDate;

    public CredebitVM()
    {
    }

    public CredebitVM(String rcptName,
            Long namayandeId,
            String namayandeName,
            String namayandeCode,
            String createdDate,
            String amount,
            String remainingAmount,
            Long paidReceivedAmount,
            String identifier,
            String shomareGharardad,
            Credebit.Status status,
            String sarresidDate,
            String sarresidDateWithMohlatSarresid,
            String amountKhateSanad)
    {
        this.rcptName = rcptName;
        this.namayandeId = namayandeId;
        this.namayandeName = namayandeName;
        this.namayandeCode = namayandeCode;
        this.createdDate = createdDate;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
        this.paidReceivedAmount = paidReceivedAmount;
        this.identifier = identifier;
        this.shomareGharardad = shomareGharardad;
        if (status.equals(Credebit.Status.SANAD_KHORDE))
            this.statusFarsi =  "سند خورده";
        else if (status.equals(Credebit.Status.SANAD_NA_KHORDE) || status.equals(Credebit.Status.SANAD_KHORDE_FISH))
            this.statusFarsi =  "سند نخورده";
        else
            this.statusFarsi =  "";
        this.sarresidDate = sarresidDate;
        this.sarresidDateWithMohlatSarresid = sarresidDateWithMohlatSarresid;
        this.amountKhateSanad = amountKhateSanad;

        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mohlateSarResid = prop.getProperty("NamayandehAuthorized.MohlateSarresidForNamyandeh");
        String finalSarresidDatetemp = DateUtil.addDaysWithTatilat(getSarresidDateWithMohlatSarresid(), Integer.parseInt(mohlateSarResid));
        finalSarresidDate = DateUtil.getTimeDifferenceByDayCount(finalSarresidDatetemp,DateUtil.getCurrentDate());
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public String getNamayandeName() {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName) {
        this.namayandeName = namayandeName;
    }

    public String getNamayandeCode() {
        return namayandeCode;
    }

    public void setNamayandeCode(String namayandeCode) {
        this.namayandeCode = namayandeCode;
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

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Long getPaidReceivedAmount() {
        return paidReceivedAmount;
    }

    public void setPaidReceivedAmount(Long paidReceivedAmount) {
        this.paidReceivedAmount = paidReceivedAmount;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getShomareGharardad() {
        return shomareGharardad;
    }

    public void setShomareGharardad(String shomareGharardad) {
        this.shomareGharardad = shomareGharardad;
    }

    public Credebit.Status getStatus() {
        return status;
    }

    public void setStatus(Credebit.Status status) {
        this.status = status;
    }

    public String getStatusFarsi() {
        return statusFarsi;
    }

    public void setStatusFarsi(String statusFarsi) {
        this.statusFarsi = statusFarsi;
    }

    public String getSarresidDate() {
        return sarresidDate;
    }

    public void setSarresidDate(String sarresidDate) {
        this.sarresidDate = sarresidDate;
    }

    public String getSarresidDateWithMohlatSarresid() {
        return sarresidDateWithMohlatSarresid;
    }

    public void setSarresidDateWithMohlatSarresid(String sarresidDateWithMohlatSarresid) {
        this.sarresidDateWithMohlatSarresid = sarresidDateWithMohlatSarresid;
    }

    public String getAmountKhateSanad() {
        return amountKhateSanad;
    }

    public void setAmountKhateSanad(String amountKhateSanad) {
        this.amountKhateSanad = amountKhateSanad;
    }

    public Integer getFinalSarresidDate() {
        return finalSarresidDate;
    }

    public void setFinalSarresidDate(Integer finalSarresidDate) {
        this.finalSarresidDate = finalSarresidDate;
    }
}
