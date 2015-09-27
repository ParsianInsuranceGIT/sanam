package com.bitarts.parsian.viewModel;

import java.util.List;

/**
 * User: a.sabzechian
 * Date: 7/16/14
 * Time: 2:14 PM
 */
public class EtebarBedehiVMReport {
    private List<EtebarBedehiVM> etebarBedehiVMListForshakhseSaless;
    private List<EtebarBedehiVM> etebarBedehiVMListForBadane ;
    private String vahedeSodor;

    public List<EtebarBedehiVM> getEtebarBedehiVMListForBadane() {
        return etebarBedehiVMListForBadane;
    }

    public void setEtebarBedehiVMListForBadane(List<EtebarBedehiVM> etebarBedehiVMListForBadane) {
        this.etebarBedehiVMListForBadane = etebarBedehiVMListForBadane;
    }

    public List<EtebarBedehiVM> getEtebarBedehiVMListForshakhseSaless() {
        return etebarBedehiVMListForshakhseSaless;
    }

    public void setEtebarBedehiVMListForshakhseSaless(List<EtebarBedehiVM> etebarBedehiVMListForshakhseSaless) {
        this.etebarBedehiVMListForshakhseSaless = etebarBedehiVMListForshakhseSaless;
    }

    public String getVahedeSodor() {
        return vahedeSodor;
    }

    public void setVahedeSodor(String vahedeSodor) {
        this.vahedeSodor = vahedeSodor;
    }
}
