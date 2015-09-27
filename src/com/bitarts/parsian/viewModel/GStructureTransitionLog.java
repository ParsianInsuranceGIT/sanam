package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/8/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class GStructureTransitionLog {
    private String kodRahgiri, createdDate, username, azstate, bestate, tarikh, saat, ekhtelaf, peygham;

    public GStructureTransitionLog(String kodRahgiri, String createdDate, String username, String azstate, String bestate, String tarikh, String saat, String ekhtelaf, String peygham) {
        this.kodRahgiri = kodRahgiri;
        this.createdDate = createdDate;
        this.username = username;
        this.azstate = azstate;
        this.bestate = bestate;
        this.tarikh = tarikh;
        this.saat = saat;
        this.ekhtelaf = ekhtelaf;
        this.peygham = peygham;
    }

    public String getKodRahgiri() {
        return kodRahgiri;
    }

    public void setKodRahgiri(String kodRahgiri) {
        this.kodRahgiri = kodRahgiri;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAzstate() {
        return azstate;
    }

    public void setAzstate(String azstate) {
        this.azstate = azstate;
    }

    public String getBestate() {
        return bestate;
    }

    public void setBestate(String bestate) {
        this.bestate = bestate;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getEkhtelaf() {
        return ekhtelaf;
    }

    public void setEkhtelaf(String ekhtelaf) {
        this.ekhtelaf = ekhtelaf;
    }

    public String getPeygham() {
        return peygham;
    }

    public void setPeygham(String peygham) {
        this.peygham = peygham;
    }
}
