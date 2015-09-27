package com.bitarts.parsian.service.calculations.Reports;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 4/13/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class IRsMultiple {
    private boolean[] isRegistered;
    private Short[] sen;
    private short saal;
    private Short[] senneBimeShodePasAzEmaleEzafeNerkh;
    private double haghBimePardaakhti;
    private double mablaghSepordeEbtedaayeSaal;
    private Double[] sarmaayeFot;
    private Double[] sarmaayeHaadeseh;
    private Double[] sarmaayeNaghsOzv;
    private Double[] sarmaayeAmraaz;
    private Double[] haghBimeKhaalesFotYekja;
    private Double sumHaghBimeKhaalesFotYekja;
    private Double[] arzeshAyandehHaghBimeHaayeKhaalesFot;
    private double sumArzeshAyandehHaghBimeHaayeKhaalesFot;
    private double kaarmozd;
    private double hazineBimeGari;
    private double hazineEdari;
    private double hazineVosul;
    private Double[] haghBimeFotDarAsarHaadese;
    private double sumHaghBimeFotDarAsarHaadese;
    private Double[] pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    private double sumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    private Double[] pusheshKhatarZelzele;
    private double sumPusheshKhatarZelzele;
    private Double[] haghBimeAmraazKhaas;
    private double sumHaghBimeAmraazKhaas;
    private Double[] haghBimePeyvandAzaa;
    private double sumHaghBimePeyvandAzaa;
    private Double[] majmuHaghBimePeyvandVaAmraaz;
    private double sumMajmuHaghBimePeyvandVaAmraaz;
    private Double[] haghBimeAzKaarOftaadegi;
    private double sumHaghBimeAzKaarOftaadegi;
    private Double[] haghBimePusheshHaayeEzaafi;
    private double sumHaghBimePusheshHaayeEzaafi;
    private double maaliyaatBarArzeshAfzude;
    private double andukhteSarmaayeGozaariBaaSudTazmini15Darsad;
    private double arzeshBazKharidBaaSudTazmini15Darsad;
    private double andukhteSarmaayeGozaariBaaSud20Darsad;
    private double arzeshBazKharidBaaSud20Darsad;
    private double andukhteSarmaayeGozaariBaaSud22Darsad;
    private double arzeshBazKharidBaaSud22Darsad;
    private double haghBimePardaakhtiBase;
    private double zaribNahveyePardakht;
    private double haghBimeMoafiatDarSurateFoteMoghadam;
    private double ehtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom;
    private double sarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval;
    private Double[] majmuHaghBimeHadeseVaZelzele;
    private double sumMajmuHaghBimeHadeseVaZelzele;
    private double andukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad;
    private double majmuHaghBimePusheshHaayeEzaafi;
    private double haghBimePardaakhtiSaleAvval;
    private double majmuKolMabaaleghPardaakhti;
    private double haghBimePardaakhtiSaal;
    private double majmuHaghBimePardaakhtiSaal;




    public Short[] getSen() {
        return sen;
    }

    public void setSen(Short[] sen) {
        this.sen = sen;
    }

    public short getSaal() {
        return saal;
    }

    public void setSaal(short saal) {
        this.saal = saal;
    }

    public Short[] getSenneBimeShodePasAzEmaleEzafeNerkh() {
        return senneBimeShodePasAzEmaleEzafeNerkh;
    }

    public void setSenneBimeShodePasAzEmaleEzafeNerkh(Short[] senneBimeShodePasAzEmaleEzafeNerkh) {
        this.senneBimeShodePasAzEmaleEzafeNerkh = senneBimeShodePasAzEmaleEzafeNerkh;
    }

    public double getHaghBimePardaakhti() {
        return haghBimePardaakhti;
    }

    public void setHaghBimePardaakhti(double haghBimePardaakhti) {
        this.haghBimePardaakhti = haghBimePardaakhti;
    }

    public double getMablaghSepordeEbtedaayeSaal() {
        return mablaghSepordeEbtedaayeSaal;
    }

    public void setMablaghSepordeEbtedaayeSaal(double mablaghSepordeEbtedaayeSaal) {
        this.mablaghSepordeEbtedaayeSaal = mablaghSepordeEbtedaayeSaal;
    }

    public Double[] getSarmaayeFot() {
        return sarmaayeFot;
    }

    public void setSarmaayeFot(Double[] sarmaayeFot) {
        this.sarmaayeFot = sarmaayeFot;
    }

    public Double[] getSarmaayeHaadeseh() {
        return sarmaayeHaadeseh;
    }

    public void setSarmaayeHaadeseh(Double[] sarmaayeHaadeseh) {
        this.sarmaayeHaadeseh = sarmaayeHaadeseh;
    }

    public Double[] getSarmaayeNaghsOzv() {
        return sarmaayeNaghsOzv;
    }

    public void setSarmaayeNaghsOzv(Double[] sarmaayeNaghsOzv) {
        this.sarmaayeNaghsOzv = sarmaayeNaghsOzv;
    }

    public Double[] getSarmaayeAmraaz() {
        return sarmaayeAmraaz;
    }

    public void setSarmaayeAmraaz(Double[] sarmaayeAmraaz) {
        this.sarmaayeAmraaz = sarmaayeAmraaz;
    }

    public Double[] getHaghBimeKhaalesFotYekja() {
        return haghBimeKhaalesFotYekja;
    }

    public void setHaghBimeKhaalesFotYekja(Double[] haghBimeKhaalesFotYekja) {
        this.haghBimeKhaalesFotYekja = haghBimeKhaalesFotYekja;
    }

    public Double[] getArzeshAyandehHaghBimeHaayeKhaalesFot() {
        return arzeshAyandehHaghBimeHaayeKhaalesFot;
    }

    public void setArzeshAyandehHaghBimeHaayeKhaalesFot(Double[] arzeshAyandehHaghBimeHaayeKhaalesFot) {
        this.arzeshAyandehHaghBimeHaayeKhaalesFot = arzeshAyandehHaghBimeHaayeKhaalesFot;
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

    public Double[] getHaghBimeFotDarAsarHaadese() {
        return haghBimeFotDarAsarHaadese;
    }

    public void setHaghBimeFotDarAsarHaadese(Double[] haghBimeFotDarAsarHaadese) {
        this.haghBimeFotDarAsarHaadese = haghBimeFotDarAsarHaadese;
    }

    public Double[] getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() {
        return pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    }

    public void setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(Double[] pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv) {
        this.pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv = pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    }

    public Double[] getPusheshKhatarZelzele() {
        return pusheshKhatarZelzele;
    }

    public void setPusheshKhatarZelzele(Double[] pusheshKhatarZelzele) {
        this.pusheshKhatarZelzele = pusheshKhatarZelzele;
    }

    public Double[] getHaghBimeAmraazKhaas() {
        return haghBimeAmraazKhaas;
    }

    public void setHaghBimeAmraazKhaas(Double[] haghBimeAmraazKhaas) {
        this.haghBimeAmraazKhaas = haghBimeAmraazKhaas;
    }

    public Double[] getHaghBimePeyvandAzaa() {
        return haghBimePeyvandAzaa;
    }

    public void setHaghBimePeyvandAzaa(Double[] haghBimePeyvandAzaa) {
        this.haghBimePeyvandAzaa = haghBimePeyvandAzaa;
    }

    public Double[] getMajmuHaghBimePeyvandVaAmraaz() {
        return majmuHaghBimePeyvandVaAmraaz;
    }

    public void setMajmuHaghBimePeyvandVaAmraaz(Double[] majmuHaghBimePeyvandVaAmraaz) {
        this.majmuHaghBimePeyvandVaAmraaz = majmuHaghBimePeyvandVaAmraaz;
    }

    public Double[] getHaghBimeAzKaarOftaadegi() {
        return haghBimeAzKaarOftaadegi;
    }

    public void setHaghBimeAzKaarOftaadegi(Double[] haghBimeAzKaarOftaadegi) {
        this.haghBimeAzKaarOftaadegi = haghBimeAzKaarOftaadegi;
    }

    public Double[] getHaghBimePusheshHaayeEzaafi() {
        return haghBimePusheshHaayeEzaafi;
    }

    public void setHaghBimePusheshHaayeEzaafi(Double[] haghBimePusheshHaayeEzaafi) {
        this.haghBimePusheshHaayeEzaafi = haghBimePusheshHaayeEzaafi;
    }

    public double getMaaliyaatBarArzeshAfzude() {
        return maaliyaatBarArzeshAfzude;
    }

    public void setMaaliyaatBarArzeshAfzude(double maaliyaatBarArzeshAfzude) {
        this.maaliyaatBarArzeshAfzude = maaliyaatBarArzeshAfzude;
    }

    public double getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad() {
        return andukhteSarmaayeGozaariBaaSudTazmini15Darsad;
    }

    public void setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(double andukhteSarmaayeGozaariBaaSudTazmini15Darsad) {
        this.andukhteSarmaayeGozaariBaaSudTazmini15Darsad = andukhteSarmaayeGozaariBaaSudTazmini15Darsad;
    }

    public double getArzeshBazKharidBaaSudTazmini15Darsad() {
        return arzeshBazKharidBaaSudTazmini15Darsad;
    }

    public void setArzeshBazKharidBaaSudTazmini15Darsad(double arzeshBazKharidBaaSudTazmini15Darsad) {
        this.arzeshBazKharidBaaSudTazmini15Darsad = arzeshBazKharidBaaSudTazmini15Darsad;
    }

    public double getAndukhteSarmaayeGozaariBaaSud20Darsad() {
        return andukhteSarmaayeGozaariBaaSud20Darsad;
    }

    public void setAndukhteSarmaayeGozaariBaaSud20Darsad(double andukhteSarmaayeGozaariBaaSud20Darsad) {
        this.andukhteSarmaayeGozaariBaaSud20Darsad = andukhteSarmaayeGozaariBaaSud20Darsad;
    }

    public double getArzeshBazKharidBaaSud20Darsad() {
        return arzeshBazKharidBaaSud20Darsad;
    }

    public void setArzeshBazKharidBaaSud20Darsad(double arzeshBazKharidBaaSud20Darsad) {
        this.arzeshBazKharidBaaSud20Darsad = arzeshBazKharidBaaSud20Darsad;
    }

    public double getAndukhteSarmaayeGozaariBaaSud22Darsad() {
        return andukhteSarmaayeGozaariBaaSud22Darsad;
    }

    public void setAndukhteSarmaayeGozaariBaaSud22Darsad(double andukhteSarmaayeGozaariBaaSud22Darsad) {
        this.andukhteSarmaayeGozaariBaaSud22Darsad = andukhteSarmaayeGozaariBaaSud22Darsad;
    }

    public double getArzeshBazKharidBaaSud22Darsad() {
        return arzeshBazKharidBaaSud22Darsad;
    }

    public void setArzeshBazKharidBaaSud22Darsad(double arzeshBazKharidBaaSud22Darsad) {
        this.arzeshBazKharidBaaSud22Darsad = arzeshBazKharidBaaSud22Darsad;
    }

    public double getHaghBimePardaakhtiBase() {
        return haghBimePardaakhtiBase;
    }

    public void setHaghBimePardaakhtiBase(double haghBimePardaakhtiBase) {
        this.haghBimePardaakhtiBase = haghBimePardaakhtiBase;
    }

    public double getZaribNahveyePardakht() {
        return zaribNahveyePardakht;
    }

    public void setZaribNahveyePardakht(double zaribNahveyePardakht) {
        this.zaribNahveyePardakht = zaribNahveyePardakht;
    }

    public boolean[] getRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean[] registered) {
        isRegistered = registered;
    }

    public double getSumHaghBimeKhaalesFotYekja() {
        return sumHaghBimeKhaalesFotYekja;
    }

    public void setSumHaghBimeKhaalesFotYekja(double sumHaghBimeKhaalesFotYekja) {
        this.sumHaghBimeKhaalesFotYekja = sumHaghBimeKhaalesFotYekja;
    }

    public double getSumArzeshAyandehHaghBimeHaayeKhaalesFot() {
        return sumArzeshAyandehHaghBimeHaayeKhaalesFot;
    }

    public void setSumArzeshAyandehHaghBimeHaayeKhaalesFot(double sumArzeshAyandehHaghBimeHaayeKhaalesFot) {
        this.sumArzeshAyandehHaghBimeHaayeKhaalesFot = sumArzeshAyandehHaghBimeHaayeKhaalesFot;
    }

    public double getSumHaghBimeFotDarAsarHaadese() {
        return sumHaghBimeFotDarAsarHaadese;
    }

    public void setSumHaghBimeFotDarAsarHaadese(double sumHaghBimeFotDarAsarHaadese) {
        this.sumHaghBimeFotDarAsarHaadese = sumHaghBimeFotDarAsarHaadese;
    }

    public double getSumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() {
        return sumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    }

    public void setSumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(double sumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv) {
        this.sumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv = sumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    }

    public double getSumPusheshKhatarZelzele() {
        return sumPusheshKhatarZelzele;
    }

    public void setSumPusheshKhatarZelzele(double sumPusheshKhatarZelzele) {
        this.sumPusheshKhatarZelzele = sumPusheshKhatarZelzele;
    }

    public double getSumHaghBimeAmraazKhaas() {
        return sumHaghBimeAmraazKhaas;
    }

    public void setSumHaghBimeAmraazKhaas(double sumHaghBimeAmraazKhaas) {
        this.sumHaghBimeAmraazKhaas = sumHaghBimeAmraazKhaas;
    }

    public double getSumHaghBimePeyvandAzaa() {
        return sumHaghBimePeyvandAzaa;
    }

    public void setSumHaghBimePeyvandAzaa(double sumHaghBimePeyvandAzaa) {
        this.sumHaghBimePeyvandAzaa = sumHaghBimePeyvandAzaa;
    }

    public double getSumMajmuHaghBimePeyvandVaAmraaz() {
        return sumMajmuHaghBimePeyvandVaAmraaz;
    }

    public void setSumMajmuHaghBimePeyvandVaAmraaz(double sumMajmuHaghBimePeyvandVaAmraaz) {
        this.sumMajmuHaghBimePeyvandVaAmraaz = sumMajmuHaghBimePeyvandVaAmraaz;
    }

    public double getSumHaghBimePusheshHaayeEzaafi() {
        return sumHaghBimePusheshHaayeEzaafi;
    }

    public void setSumHaghBimePusheshHaayeEzaafi(double sumHaghBimePusheshHaayeEzaafi) {
        this.sumHaghBimePusheshHaayeEzaafi = sumHaghBimePusheshHaayeEzaafi;
    }

    public double getHaghBimeMoafiatDarSurateFoteMoghadam() {
        return haghBimeMoafiatDarSurateFoteMoghadam;
    }

    public void setHaghBimeMoafiatDarSurateFoteMoghadam(double haghBimeMoafiatDarSurateFoteMoghadam) {
        this.haghBimeMoafiatDarSurateFoteMoghadam = haghBimeMoafiatDarSurateFoteMoghadam;
    }

    public double getSumHaghBimeAzKaarOftaadegi() {
        return sumHaghBimeAzKaarOftaadegi;
    }

    public void setSumHaghBimeAzKaarOftaadegi(double sumHaghBimeAzKaarOftaadegi) {
        this.sumHaghBimeAzKaarOftaadegi = sumHaghBimeAzKaarOftaadegi;
    }

    public double getEhtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom() {
        return ehtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom;
    }

    public void setEhtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom(double ehtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom) {
        this.ehtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom = ehtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom;
    }

    public double getSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval() {
        return sarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval;
    }

    public void setSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval(double sarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval) {
        this.sarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval = sarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval;
    }

    public Double[] getMajmuHaghBimeHadeseVaZelzele() {
        return majmuHaghBimeHadeseVaZelzele;
    }

    public void setMajmuHaghBimeHadeseVaZelzele(Double[] majmuHaghBimeHadeseVaZelzele) {
        this.majmuHaghBimeHadeseVaZelzele = majmuHaghBimeHadeseVaZelzele;
    }

    public double getSumMajmuHaghBimeHadeseVaZelzele() {
        return sumMajmuHaghBimeHadeseVaZelzele;
    }

    public void setSumMajmuHaghBimeHadeseVaZelzele(double sumMajmuHaghBimeHadeseVaZelzele) {
        this.sumMajmuHaghBimeHadeseVaZelzele = sumMajmuHaghBimeHadeseVaZelzele;
    }

    public double getAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad() {
        return andukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad;
    }

    public void setAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad(double andukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad) {
        this.andukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad = andukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad;
    }

    public double getMajmuHaghBimePusheshHaayeEzaafi() {
        return majmuHaghBimePusheshHaayeEzaafi;
    }

    public void setMajmuHaghBimePusheshHaayeEzaafi(double majmuHaghBimePusheshHaayeEzaafi) {
        this.majmuHaghBimePusheshHaayeEzaafi = majmuHaghBimePusheshHaayeEzaafi;
    }

    public double getHaghBimePardaakhtiSaleAvval() {
        return haghBimePardaakhtiSaleAvval;
    }

    public void setHaghBimePardaakhtiSaleAvval(double haghBimePardaakhtiSaleAvval) {
        this.haghBimePardaakhtiSaleAvval = haghBimePardaakhtiSaleAvval;
    }

    public double getMajmuKolMabaaleghPardaakhti() {
        return majmuKolMabaaleghPardaakhti;
    }

    public void setMajmuKolMabaaleghPardaakhti(double majmuKolMabaaleghPardaakhti) {
        this.majmuKolMabaaleghPardaakhti = majmuKolMabaaleghPardaakhti;
    }

    public double getHaghBimePardaakhtiSaal() {
        return haghBimePardaakhtiSaal;
    }

    public void setHaghBimePardaakhtiSaal(double haghBimePardaakhtiSaal) {
        this.haghBimePardaakhtiSaal = haghBimePardaakhtiSaal;
    }

    public double getMajmuHaghBimePardaakhtiSaal() {
        return majmuHaghBimePardaakhtiSaal;
    }

    public void setMajmuHaghBimePardaakhtiSaal(double majmuHaghBimePardaakhtiSaal) {
        this.majmuHaghBimePardaakhtiSaal = majmuHaghBimePardaakhtiSaal;
    }
}
