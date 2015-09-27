package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.util.OmrUtil;

/**
 * User: a.sabzechian
 * Date: 7/27/14
 * Time: 11:04 AM
 */
public class CredebitBedehiVM {
    private Credebit.CredebitType credebitType;
    private String shomareBimename ;
    private String rcptName;
    private  String namayandegiCode;
    private String gharardadType;
    private String saresidDate;
    private  String createDate;
    private  Long bedehiAmount;
    private  Long pardakhtshodeAmount;
    private  Long bedehiRemaining_Amount;
    private String credebitTypeFarsi;


    public CredebitBedehiVM(String rcptName,
                            String shomareBimename,
                            String createDate,
                            String saresidDate,
                            Long bedehiAmount,
                            Long bedehiRemaining_Amount,
                            String pardakhtshodeAmount,
                            String namayandegiCode,
                            Credebit.CredebitType credebitType
                            ){
        this.rcptName=rcptName;
        this.shomareBimename=shomareBimename;
        this.namayandegiCode=namayandegiCode;
        this.saresidDate=saresidDate;
        this.createDate=createDate;
        this.bedehiAmount=bedehiAmount;
        this.pardakhtshodeAmount=Long.parseLong(pardakhtshodeAmount.replaceAll(",","").trim());
        this.bedehiRemaining_Amount=bedehiRemaining_Amount;
        this.credebitTypeFarsi= OmrUtil.getCredebitTypeFarsi(credebitType);


    }

    public Credebit.CredebitType getCredebitType() {
        return credebitType;
    }

    public void setCredebitType(Credebit.CredebitType credebitType) {
        this.credebitType = credebitType;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public String getNamayandegiCode() {
        return namayandegiCode;
    }

    public void setNamayandegiCode(String namayandegiCode) {
        this.namayandegiCode = namayandegiCode;
    }

    public String getGharardadType() {
        return gharardadType;
    }

    public void setGharardadType(String gharardadType) {
        this.gharardadType = gharardadType;
    }

    public String getSaresidDate() {
        return saresidDate;
    }

    public void setSaresidDate(String saresidDate) {
        this.saresidDate = saresidDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getBedehiAmount() {
        return bedehiAmount;
    }

    public void setBedehiAmount(Long bedehiAmount) {
        this.bedehiAmount = bedehiAmount;
    }

    public Long getPardakhtshodeAmount() {
        return pardakhtshodeAmount;
    }

    public void setPardakhtshodeAmount(Long pardakhtshodeAmount) {
        this.pardakhtshodeAmount = pardakhtshodeAmount;
    }

    public Long getBedehiRemaining_Amount() {
        return bedehiRemaining_Amount;
    }

    public void setBedehiRemaining_Amount(Long bedehiRemaining_Amount) {
        this.bedehiRemaining_Amount = bedehiRemaining_Amount;
    }

    public String getCredebitTypeFarsi() {
        return credebitTypeFarsi;
    }

    public void setCredebitTypeFarsi(String credebitTypeFarsi) {
        this.credebitTypeFarsi = credebitTypeFarsi;
    }
}
