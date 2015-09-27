package com.bitarts.parsian.viewModel.search;

public class CredebitSearchForm {
    public static enum Credebit_type{ETEBAR, BEDEHI}
    private Credebit_type type;
    private String shomareBimename;
    private String shomareBedehi;
    private String shomareEtebar;
    private String shomareGharardad;
    private String azTarikh;
    private String taTarikh;
    private String credebitType;
    private String identifier;
    private String rcptName;
    private String shomareMoshtari;
    private String namayandeName;
    private String bazaryabSanamName;
    private Long bazaryabSanamId;
    private String azTarikhEtebar;
    private String taTarikhEtebar;
    private String amount;
    private String shomareCheck;
    private String shomareFish;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAzTarikhEtebar() {
        return azTarikhEtebar;
    }

    public void setAzTarikhEtebar(String azTarikhEtebar) {
        this.azTarikhEtebar = azTarikhEtebar;
    }

    public String getTaTarikhEtebar() {
        return taTarikhEtebar;
    }

    public void setTaTarikhEtebar(String taTarikhEtebar) {
        this.taTarikhEtebar = taTarikhEtebar;
    }

    public String getNamayandeName() {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName) {
        this.namayandeName = namayandeName;
    }

    public String getBazaryabSanamName() {
        return bazaryabSanamName;
    }

    public void setBazaryabSanamName(String bazaryabSanamName) {
        this.bazaryabSanamName = bazaryabSanamName;
    }

    public String getShomareMoshtari()
    {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari)
    {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier=identifier;
    }

    public String getRcptName()
    {
        return rcptName;
    }

    public void setRcptName(String rcptName)
    {
        this.rcptName=rcptName;
    }


    public String getCredebitType()
    {
        return credebitType;
    }

    public void setCredebitType(String credebitType)
    {
        this.credebitType=credebitType;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getShomareBedehi() {
        return shomareBedehi;
    }

    public void setShomareBedehi(String shomareBedehi) {
        this.shomareBedehi = shomareBedehi;
    }

    public String getShomareEtebar() {
        return shomareEtebar;
    }

    public void setShomareEtebar(String shomareEtebar) {
        this.shomareEtebar = shomareEtebar;
    }

    public String getShomareGharardad() {
        return shomareGharardad;
    }

    public void setShomareGharardad(String shomareGharardad) {
        this.shomareGharardad = shomareGharardad;
    }

    public Credebit_type getType() {
        return type;
    }

    public void setType(Credebit_type type) {
        this.type = type;
    }

    public String getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(String azTarikh) {
        this.azTarikh = azTarikh;
    }

    public String getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(String taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Long getBazaryabSanamId() {
        return bazaryabSanamId;
    }

    public void setBazaryabSanamId(Long bazaryabSanamId) {
        this.bazaryabSanamId = bazaryabSanamId;
    }

    public String getShomareCheck() {
        return shomareCheck;
    }

    public void setShomareCheck(String shomareCheck) {
        this.shomareCheck = shomareCheck;
    }

    public String getShomareFish() {
        return shomareFish;
    }

    public void setShomareFish(String shomareFish) {
        this.shomareFish = shomareFish;
    }
}
