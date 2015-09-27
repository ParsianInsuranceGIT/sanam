package com.bitarts.parsian.service.calculations.Reports;

import com.bitarts.parsian.model.asnadeSodor.Ghest;

import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/14/11
 * Time: 4:49 PM
 */
public class TaghsitReport {
    public String tarikh;
    private short saal;
    private double haghBimePardaakhtiSaal;
    private double kaarmozd;
    private double hazineBimeGari;
    private double hazineEdari;
    private double hazineVosul;
    private double haghBimeKhaalesFotYekja;
    private double sarmaayeFotBehHarEllat;
    private double sarmaayeFotDarAsarHaadeseh;
    private double sarmaayePusheshEzaafiAmraazKhaas;
    private double sarmaayePusheshEzaafiNaghsOzv;
    private double haghBimePusheshHaayeEzaafi;
    private double haghBimePoosheshAmraz;
    private double haghBimePoosheshHadese;
    private double haghBimePoosheshNaghsOzv;
    private double haghBimePoosheshMoafiat;
    private double maliat;
    private double kasrHazine;
    private double majmooHaghBimePoosheshhayeEzafi;
    private double zaribNahveyePardakht;
    private double haghBimeAvvalie;
    private boolean poosheshEzafiDetailsCorrect;
    private long ghestAmount;
    private FRs fRs;
    private boolean createdByElhaghie = false;

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public short getSaal() {
        return saal;
    }

    public void setSaal(short saal) {
        this.saal = saal;
    }

    public double getHaghBimePardaakhtiSaal() {
        return haghBimePardaakhtiSaal;
    }

    public void setHaghBimePardaakhtiSaal(double haghBimePardaakhtiSaal) {
        this.haghBimePardaakhtiSaal = haghBimePardaakhtiSaal;
    }

    public double getKaarmozd() {
        return kaarmozd;
    }

    public void setKaarmozd(double kaarmozd) {
        this.kaarmozd = kaarmozd;
    }

    public double getHazineBimeGari() {
        return hazineBimeGari;
    }

    public void setHazineBimeGari(double hazineBimeGari) {
        this.hazineBimeGari = hazineBimeGari;
    }

    public double getHazineEdari() {
        return hazineEdari;
    }

    public void setHazineEdari(double hazineEdari) {
        this.hazineEdari = hazineEdari;
    }

    public double getHazineVosul() {
        return hazineVosul;
    }

    public void setHazineVosul(double hazineVosul) {
        this.hazineVosul = hazineVosul;
    }

    public double getHaghBimeKhaalesFotYekja() {
        return haghBimeKhaalesFotYekja;
    }

    public void setHaghBimeKhaalesFotYekja(double haghBimeKhaalesFotYekja) {
        this.haghBimeKhaalesFotYekja = haghBimeKhaalesFotYekja;
    }

    public double getSarmaayeFotBehHarEllat() {
        return sarmaayeFotBehHarEllat;
    }

    public void setSarmaayeFotBehHarEllat(double sarmaayeFotBehHarEllat) {
        this.sarmaayeFotBehHarEllat = sarmaayeFotBehHarEllat;
    }

    public double getSarmaayeFotDarAsarHaadeseh() {
        return sarmaayeFotDarAsarHaadeseh;
    }

    public void setSarmaayeFotDarAsarHaadeseh(double sarmaayeFotDarAsarHaadeseh) {
        this.sarmaayeFotDarAsarHaadeseh = sarmaayeFotDarAsarHaadeseh;
    }

    public double getSarmaayePusheshEzaafiAmraazKhaas() {
        return sarmaayePusheshEzaafiAmraazKhaas;
    }

    public void setSarmaayePusheshEzaafiAmraazKhaas(double sarmaayePusheshEzaafiAmraazKhaas) {
        this.sarmaayePusheshEzaafiAmraazKhaas = sarmaayePusheshEzaafiAmraazKhaas;
    }

    public double getHaghBimePusheshHaayeEzaafi() {
        return haghBimePusheshHaayeEzaafi;
    }

    public void setHaghBimePusheshHaayeEzaafi(double haghBimePusheshHaayeEzaafi) {
        this.haghBimePusheshHaayeEzaafi = haghBimePusheshHaayeEzaafi;
    }

    public double getHaghBimePoosheshAmraz() {
        return haghBimePoosheshAmraz;
    }

    public void setHaghBimePoosheshAmraz(double haghBimePoosheshAmraz) {
        this.haghBimePoosheshAmraz = haghBimePoosheshAmraz;
    }

    public double getHaghBimePoosheshHadese() {
        return haghBimePoosheshHadese;
    }

    public void setHaghBimePoosheshHadese(double haghBimePoosheshHadese) {
        this.haghBimePoosheshHadese = haghBimePoosheshHadese;
    }

    public double getHaghBimePoosheshNaghsOzv() {
        return haghBimePoosheshNaghsOzv;
    }

    public void setHaghBimePoosheshNaghsOzv(double haghBimePoosheshNaghsOzv) {
        this.haghBimePoosheshNaghsOzv = haghBimePoosheshNaghsOzv;
    }

    public double getHaghBimePoosheshMoafiat() {
        return haghBimePoosheshMoafiat;
    }

    public void setHaghBimePoosheshMoafiat(double haghBimePoosheshMoafiat) {
        this.haghBimePoosheshMoafiat = haghBimePoosheshMoafiat;
    }

    public double getMaliat() {
        return maliat;
    }

    public void setMaliat(double maliat) {
        this.maliat = maliat;
    }

    public double getMajmooHaghBimePoosheshhayeEzafi() {
        return majmooHaghBimePoosheshhayeEzafi;
    }

    public void setMajmooHaghBimePoosheshhayeEzafi(double majmooHaghBimePoosheshhayeEzafi) {
        this.majmooHaghBimePoosheshhayeEzafi = majmooHaghBimePoosheshhayeEzafi;
    }

    public double getZaribNahveyePardakht() {
        return zaribNahveyePardakht;
    }

    public void setZaribNahveyePardakht(double zaribNahveyePardakht) {
        this.zaribNahveyePardakht = zaribNahveyePardakht;
    }

    public double getHaghBimeAvvalie() {
        return haghBimeAvvalie;
    }

    public void setHaghBimeAvvalie(double haghBimeAvvalie) {
        this.haghBimeAvvalie = haghBimeAvvalie;
    }

    public boolean isPoosheshEzafiDetailsCorrect() {
        return poosheshEzafiDetailsCorrect;
    }

    public void setPoosheshEzafiDetailsCorrect(boolean poosheshEzafiDetailsCorrect) {
        this.poosheshEzafiDetailsCorrect = poosheshEzafiDetailsCorrect;
    }

    public long getGhestAmount() {
        return ghestAmount;
    }

    public void setGhestAmount(long ghestAmount) {
        this.ghestAmount = ghestAmount;
    }

    public long getMajmooHazineha() {
        return Math.round(getHaghBimePusheshHaayeEzaafi()
                                    + getMaliat()
                                    + getHaghBimeKhaalesFotYekja()
                                    + getHazineEdari()
                                    + getHazineVosul()
                                    + getHazineBimeGari()
                                    + getKaarmozd());
    }

    public double getSarmaayePusheshEzaafiNaghsOzv() {
        return sarmaayePusheshEzaafiNaghsOzv;
    }

    public void setSarmaayePusheshEzaafiNaghsOzv(double sarmaayePusheshEzaafiNaghsOzv) {
        this.sarmaayePusheshEzaafiNaghsOzv = sarmaayePusheshEzaafiNaghsOzv;
    }

    public FRs getfRs() {
        return fRs;
    }

    public void setfRs(FRs fRs) {
        this.fRs = fRs;
    }

    public Ghest convertToGhest() {
        Ghest ghest = new Ghest();
        ghest.setSarresidDate(getTarikh());
        ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(getHaghBimeKhaalesFotYekja())));
        ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(getHaghBimePusheshHaayeEzaafi()));
        ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(getHaghBimePoosheshAmraz())));
        ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(getHaghBimePoosheshHadese())));
        ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(getHaghBimePoosheshNaghsOzv())));
        ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(getHaghBimePoosheshMoafiat())));
        ghest.setMaliat(NumberFormat.getNumberInstance().format(getMaliat()));
        ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(getHazineBimeGari())));
        ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(getHazineEdari())));
        ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(getKaarmozd())));
        ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(getHazineVosul())));
        ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(getSarmaayeFotBehHarEllat())));
        ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(getSarmaayeFotDarAsarHaadeseh())));
        ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(getSarmaayePusheshEzaafiAmraazKhaas())));
        ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(getZaribNahveyePardakht()).length())));
        ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(getKaarmozd()));
        return ghest;
    }

    public double getKasrHazine() {
        return kasrHazine;
    }

    public void setKasrHazine(double kasrHazine) {
        this.kasrHazine = kasrHazine;
    }

    public boolean isCreatedByElhaghie() {
        return createdByElhaghie;
    }

    public void setCreatedByElhaghie(boolean createdByElhaghie) {
        this.createdByElhaghie = createdByElhaghie;
    }
}
