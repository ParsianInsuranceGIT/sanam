package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.util.OmrUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sabzechian
 */
public class CredebitBimenameVM
{
    private String rcptName;
    private String shomareBimename;
    private String sodorDate;
    private String sarresidGhest;
    private Long amount;
    private Long remaningAmount;
    private String  sahmeBimenameFromAmountEtebar;
    private Credebit.CredebitType TypeEtebar;
    private String shomareEtebar;
    private String amountEtebar;
    private String moshakhaseEtebar;
    private String sarresidEtebar;
    private String statusFarsi;
    private  Credebit.VaziyatVosoul VaziyatVosoul;
    private String VosoulStateFarsi;

    public String getVosoulStateFarsi() {
        return VosoulStateFarsi;
    }

    public void setVosoulStateFarsi(String vosoulStateFarsi) {
        VosoulStateFarsi = vosoulStateFarsi;
    }

    public Credebit.VaziyatVosoul getVaziyatVosoul() {
        return VaziyatVosoul;
    }

    public void setVaziyatVosoul(Credebit.VaziyatVosoul vaziyatVosoul) {
        VaziyatVosoul = vaziyatVosoul;
    }

    public String getStatusFarsi() {
        return statusFarsi;
    }

    public void setStatusFarsi(String statusFarsi) {
        this.statusFarsi = statusFarsi;
    }

    public String getSarresidEtebar() {
        return sarresidEtebar;
    }

    public void setSarresidEtebar(String sarresidEtebar) {
        this.sarresidEtebar = sarresidEtebar;
    }

    private String vosolDate;
    private String shomareSanad;


    public CredebitBimenameVM()
    {
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getSodorDate() {
        return sodorDate;
    }

    public void setSodorDate(String sodorDate) {
        this.sodorDate = sodorDate;
    }

    public String getSarresidGhest() {
        return sarresidGhest;
    }

    public void setSarresidGhest(String sarresidGhest) {
        this.sarresidGhest = sarresidGhest;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRemaningAmount() {
        return remaningAmount;
    }

    public void setRemaningAmount(Long remaningAmount) {
        this.remaningAmount = remaningAmount;
    }

    public String getSahmeBimenameFromAmountEtebar() {
        return sahmeBimenameFromAmountEtebar;
    }

    public void setSahmeBimenameFromAmountEtebar(String sahmeBimenameFromAmountEtebar) {
        this.sahmeBimenameFromAmountEtebar = sahmeBimenameFromAmountEtebar;
    }

    public Credebit.CredebitType getTypeEtebar() {
        return TypeEtebar;
    }

    public void setTypeEtebar(Credebit.CredebitType typeEtebar) {
        TypeEtebar = typeEtebar;
    }

    public String getShomareEtebar() {
        return shomareEtebar;
    }

    public void setShomareEtebar(String shomareEtebar) {
        this.shomareEtebar = shomareEtebar;
    }

    public String getAmountEtebar() {
        return amountEtebar;
    }

    public void setAmountEtebar(String amountEtebar) {
        this.amountEtebar = amountEtebar;
    }

    public String getMoshakhaseEtebar() {
        return moshakhaseEtebar;
    }

    public void setMoshakhaseEtebar(String moshakhaseEtebar) {
        this.moshakhaseEtebar = moshakhaseEtebar;
    }



    public String getVosolDate() {
        return vosolDate;
    }

    public void setVosolDate(String vosolDate) {
        this.vosolDate = vosolDate;
    }

    public String getShomareSanad() {
        return shomareSanad;
    }

    public void setShomareSanad(String shomareSanad) {
        this.shomareSanad = shomareSanad;
    }

    public CredebitBimenameVM(String rcptName,
                              String shomareBimename,
                              String sodorDate,
                              String sarresidGhest,
                              Long amount,
                              Long remaningAmount,
                              String sahmeBimenameFromAmountEtebar,
                              Credebit.CredebitType TypeEtebar,
                              String shomareEtebar,
                              Long amountEtebar,
                              String moshakhaseEtebar,
                              String sarresidEtebar,
                              String vosolDate,
                              String shomareSanad,
                              Credebit.VaziyatVosoul VaziyatVosoul)
    {
        this.rcptName = rcptName;
        this.shomareBimename = shomareBimename;
        this.sodorDate = sodorDate;
        this.sarresidGhest = sarresidGhest;
        this.amount = amount;
        this.remaningAmount = remaningAmount;
        this.sahmeBimenameFromAmountEtebar = sahmeBimenameFromAmountEtebar;
        this.TypeEtebar = TypeEtebar;
        this.statusFarsi= OmrUtil.getCredebitTypeFarsi(TypeEtebar);
        this.shomareEtebar = shomareEtebar;
        this.amountEtebar = String.valueOf(amountEtebar);
        this.moshakhaseEtebar = moshakhaseEtebar;
        this.sarresidEtebar= sarresidEtebar;
        if(vosolDate!=null && vosolDate.equals("***")  )     {
           this.setVosolDate("سر رسی نشده" );
            this.vosolDate  = "سر رسی نشده" ;
        } else
            this.vosolDate=vosolDate;
        this.shomareSanad = shomareSanad ;
        if(vosolDate!=null && !vosolDate.equals("--") && this.getVosolDate().equals("سر رسی نشده"))
        this.VosoulStateFarsi= "سر رسی نشده" ;
        else
            this.VosoulStateFarsi= OmrUtil.getVosoulStateFarsi(VaziyatVosoul);



      /*  Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
/*        String mohlateSarResid = prop.getProperty("WebService.NamayandehAuthorized.MohlateSarresid");
        String finalSarresidDatetemp = DateUtil.addDaysWithTatilat(getSarresidDateWithMohlatSarresid(), Integer.parseInt(mohlateSarResid));
        finalSarresidDate = DateUtil.getTimeDifferenceByDayCount(finalSarresidDatetemp,DateUtil.getCurrentDate());*/
    }

}