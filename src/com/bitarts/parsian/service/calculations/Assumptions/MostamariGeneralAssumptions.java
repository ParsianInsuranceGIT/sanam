package com.bitarts.parsian.service.calculations.Assumptions;


import com.bitarts.parsian.service.calculations.Constants.LifeTable;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 8, 2011
 * Time: 7:02:48 PM
 */
public class MostamariGeneralAssumptions implements NSPConstants{
    private Double andukhteEntehaModatBimename;
    private Integer dowreTazminPardakht;
    private String nahvePardakhtMostamari;
    private double nerkhAfzayeshSalaneMostamari;
    private String noeMostamariDarkhasti;
    private Integer senMostamariBegir;

    public MostamariGeneralAssumptions(Double andukhteEntehaModatBimename, Integer dowreTazminPardakht,
                                       String nahvePardakhtMostamari, double nerkhAfzayeshSalaneMostamari,
                                       String noeMostamariDarkhasti, Integer senMostamariBegir){
        this.nerkhAfzayeshSalaneMostamari = nerkhAfzayeshSalaneMostamari/100.0;
        this.andukhteEntehaModatBimename = andukhteEntehaModatBimename;
        this.dowreTazminPardakht = dowreTazminPardakht;
        this.noeMostamariDarkhasti = noeMostamariDarkhasti;
        this.senMostamariBegir = senMostamariBegir;
        this.nahvePardakhtMostamari = nahvePardakhtMostamari;


    }

    public Double getAndukhteEntehaModatBimename() {
        return andukhteEntehaModatBimename;
    }

    public void setAndukhteEntehaModatBimename(Double andukhteEntehaModatBimename) {
        this.andukhteEntehaModatBimename = andukhteEntehaModatBimename;
    }

    public Integer getDowreTazminPardakht() {
        return dowreTazminPardakht;
    }

    public void setDowreTazminPardakht(Integer dowreTazminPardakht) {
        this.dowreTazminPardakht = dowreTazminPardakht;
    }

    public double getNerkhAfzayeshSalaneMostamari() {
        return nerkhAfzayeshSalaneMostamari;
    }

    public void setNerkhAfzayeshSalaneMostamari(double nerkhAfzayeshSalaneMostamari) {
        this.nerkhAfzayeshSalaneMostamari = nerkhAfzayeshSalaneMostamari;
    }

    public String getNoeMostamariDarkhasti() {
        return noeMostamariDarkhasti;
    }

    public void setNoeMostamariDarkhasti(String noeMostamariDarkhasti) {
        this.noeMostamariDarkhasti = noeMostamariDarkhasti;
    }

    public Integer getSenMostamariBegir() {
        return senMostamariBegir;
    }

    public void setSenMostamariBegir(Integer senMostamariBegir) {
        this.senMostamariBegir = senMostamariBegir;
    }

    public String getNahvePardakhtMostamari() {
        return nahvePardakhtMostamari;
    }

    public void setNahvePardakhtMostamari(String nahvePardakhtMostamari) {
        this.nahvePardakhtMostamari = nahvePardakhtMostamari;
    }
}
