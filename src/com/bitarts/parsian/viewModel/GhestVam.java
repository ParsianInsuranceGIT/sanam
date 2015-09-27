package com.bitarts.parsian.viewModel;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/9/12
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class GhestVam {
    private String bahre;
    private String mablagh;
    private String pardakhtAzvam;
    public Double r1, r2, i1, i2, p1, p2;
    private String type;
    private String shomareVam;
    private String ghestCreatedDate;
    private String ghestSarresidDate;
    private String maliat;
    private String shomareMoshtari;
    private String ghestAmount;
    private String ghestBahre;
    private String ghestMaliat;
    private String ghestPaymentAmount;
    private String namayandeName;
    private String namayandeCode;
    private Long namayandeId;



//--------------------------------------------------report Ghest Vams---------------------------------------------------
    public GhestVam(String shomareVam,Long ghestAmount,String ghestCreatedDate,Long maliat,String bahre,String shomareMoshtari,String ghestSarresidDate, String ghestPaymentAmount)
    {
        this.shomareVam=shomareVam;
        this.ghestAmount=ghestAmount==null?"":ghestAmount.toString();
        this.shomareMoshtari=shomareMoshtari;
        this.ghestCreatedDate=ghestCreatedDate;
        this.ghestCreatedDate=ghestCreatedDate;
        this.ghestMaliat=maliat==null?"":maliat.toString();
        this.ghestBahre=bahre;
        this.shomareMoshtari=shomareMoshtari;
        this.ghestSarresidDate=ghestSarresidDate;
        this.ghestPaymentAmount=ghestPaymentAmount;
    }
//----------------------------------------------------------------------------------------------------------------------


    public String getGhestPaymentAmount()
    {
        return ghestPaymentAmount;
    }

    public void setGhestPaymentAmount(String ghestPaymentAmount)
    {
        this.ghestPaymentAmount = ghestPaymentAmount;
    }

    public String getNamayandeName()
    {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName)
    {
        this.namayandeName = namayandeName;
    }

    public String getNamayandeCode()
    {
        return namayandeCode;
    }

    public void setNamayandeCode(String namayandeCode)
    {
        this.namayandeCode = namayandeCode;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getGhestAmount()
    {
        return ghestAmount;
    }

    public void setGhestAmount(String ghestAmount)
    {
        this.ghestAmount = ghestAmount;
    }

    public String getGhestBahre()
    {
        return ghestBahre;
    }

    public void setGhestBahre(String ghestBahre)
    {
        this.ghestBahre = ghestBahre;
    }

    public String getGhestMaliat()
    {
        return ghestMaliat;
    }

    public void setGhestMaliat(String ghestMaliat)
    {
        this.ghestMaliat = ghestMaliat;
    }

    public GhestVam(String nahveyePardakhteAghsat) {
        this.type = nahveyePardakhteAghsat;
    }

    public String getShomareVam()
    {
        return shomareVam;
    }

    public String getGhestSarresidDate()
    {
        return ghestSarresidDate;
    }

    public void setGhestSarresidDate(String ghestSarresidDate)
    {
        this.ghestSarresidDate = ghestSarresidDate;
    }

    public void setShomareVam(String shomareVam)
    {
        this.shomareVam = shomareVam;
    }

    public String getGhestCreatedDate()
    {
        return ghestCreatedDate;
    }

    public void setGhestCreatedDate(String ghestCreatedDate)
    {
        this.ghestCreatedDate = ghestCreatedDate;
    }

    public String getMaliat()
    {
        return maliat;
    }

    public void setMaliat(String maliat)
    {
        this.maliat = maliat;
    }

    public String getShomareMoshtari()
    {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari)
    {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getBahre() {
        if (type.equals("MAHANE"))
            bahre = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(i1)));
        else bahre = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(i2)));
        return bahre;
    }

    public void setBahre(String bahre) {
        this.bahre = bahre;
    }

    public String getMablagh() {
        if (type.equals("MAHANE"))
            mablagh = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(r1)));
        else mablagh = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(r2)));
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public String getPardakhtAzvam() {
        if (type.equals("MAHANE"))
            pardakhtAzvam = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(p1)));
        else pardakhtAzvam = String.valueOf(NumberFormat.getNumberInstance().format(Math.round(p2)));
        return pardakhtAzvam;
    }

    public void setPardakhtAzvam(String pardakhtAzvam) {
        this.pardakhtAzvam = pardakhtAzvam;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
