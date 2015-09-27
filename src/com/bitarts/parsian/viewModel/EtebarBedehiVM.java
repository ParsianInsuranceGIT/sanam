package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.util.OmrUtil;

/**
 * User: a.sabzechian
 * Date: 7/15/14
 * Time: 6:01 PM
 */
public class EtebarBedehiVM {
    private Long sumAmount;
    private Long number;
    private Credebit.CredebitType credebitType;
    private String vahedesodor;
    private String statusFarsi;

    public String getStatusFarsi() {
        return statusFarsi;
    }

    public void setStatusFarsi(String statusFarsi) {
        this.statusFarsi = statusFarsi;
    }

    public Long getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Long sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public EtebarBedehiVM(Long sumAmount, Long number, Credebit.CredebitType credebitType, String vahedesodor) {
        this.setSumAmount(sumAmount);
        this.setNumber(number);
        this.setCredebitType(credebitType);
        this.statusFarsi= OmrUtil.getCredebitTypeFarsi(credebitType);
         this.setVahedesodor(vahedesodor);


    }



    public Credebit.CredebitType getCredebitType() {
        return credebitType;
    }

    public void setCredebitType(Credebit.CredebitType credebitType) {
        this.credebitType = credebitType;
    }

    public String getVahedesodor() {
        return vahedesodor;
    }

    public void setVahedesodor(String vahedesodor) {
        this.vahedesodor = vahedesodor;
    }
}
