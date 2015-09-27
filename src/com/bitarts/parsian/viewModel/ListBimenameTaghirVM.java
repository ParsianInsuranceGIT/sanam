package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.bimename.Bimename;

public class ListBimenameTaghirVM
{

    private String state;
    private String shomare;
    private String shakhsRole;
    private boolean taghirMohasebati;
    private boolean checkResult;
    private Bimename bimename;

    public boolean isTaghirMohasebati() {
        return taghirMohasebati;
    }

    public void setTaghirMohasebati(boolean taghirMohasebati) {
        this.taghirMohasebati = taghirMohasebati;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public String getShakhsRole() {
        return shakhsRole;
    }

    public void setShakhsRole(String shakhsRole) {
        this.shakhsRole = shakhsRole;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }
}