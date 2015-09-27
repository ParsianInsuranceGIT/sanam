package com.bitarts.parsian.service.calculations.Reports;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 7/10/11
 * Time: 7:36 PM
 */
public class FRs {
    private short sen;
    private short saal;
    private short senneBimeShodePasAzEmaleEzafeNerkh;
    private double haghBimePardaakhtiSaal;
    private double haghBimePardaakhti;
    private double mablaghSepordeEbtedaayeSaal;
    private double sarmaayeFotBehHarEllat;
    private double sarmaayeFotDarAsarHaadeseh;
    private double sarmaayePusheshEzaafiAmraazKhaas;
    private double majmuHaghBimePardaakhtiSaal;
    private double haghBimePusheshHaayeEzaafi;
    private double majmuHaghBimePusheshHaayeEzaafi;
    private double maaliyaatBarArzeshAfzude;
    private double majmuKolMabaaleghPardaakhti;
    private double arzeshBazKharidBaaSudTazmini15Darsad;
    private double pishbiniArzeshBazKharidBaaSud20Darsad;
    private double pishbiniArzeshBazKharidBaaSud22Darsad;
    private double mablaghHagheBimeSalane;
    private double mablaghHagheBimeSheshMahe;
    private double mablaghHagheBimeSeMahe;
    private double mablaghHagheBimeMahane;
    private double haghBimePardakhtiBase;


    private String nahvePardakhteHagheBime;
    private String tarikheShoro;
    private String tarikheKhatame;
    private String poosheshMoafiat;
    private double sarmaayePusheshEzaafiNaghsOzv;

    private IRs iRs;

    public short getSen() {
        return sen;
    }

    public void setSen(short sen) {
        this.sen = sen;
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

    public double getMablaghSepordeEbtedaayeSaal() {
        return mablaghSepordeEbtedaayeSaal;
    }

    public void setMablaghSepordeEbtedaayeSaal(double mablaghSepordeEbtedaayeSaal) {
        this.mablaghSepordeEbtedaayeSaal = mablaghSepordeEbtedaayeSaal;
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

    public double getMajmuHaghBimePardaakhtiSaal() {
        return majmuHaghBimePardaakhtiSaal;
    }

    public void setMajmuHaghBimePardaakhtiSaal(double majmuHaghBimePardaakhtiSaal) {
        this.majmuHaghBimePardaakhtiSaal = majmuHaghBimePardaakhtiSaal;
    }

    public double getHaghBimePusheshHaayeEzaafi() {
        return haghBimePusheshHaayeEzaafi;
    }

    public void setHaghBimePusheshHaayeEzaafi(double haghBimePusheshHaayeEzaafi) {
        this.haghBimePusheshHaayeEzaafi = haghBimePusheshHaayeEzaafi;
    }

    public double getMajmuHaghBimePusheshHaayeEzaafi() {
        return majmuHaghBimePusheshHaayeEzaafi;
    }

    public void setMajmuHaghBimePusheshHaayeEzaafi(double majmuHaghBimePusheshHaayeEzaafi) {
        this.majmuHaghBimePusheshHaayeEzaafi = majmuHaghBimePusheshHaayeEzaafi;
    }

    public double getMaaliyaatBarArzeshAfzude() {
        return maaliyaatBarArzeshAfzude;
    }

    public void setMaaliyaatBarArzeshAfzude(double maaliyaatBarArzeshAfzude) {
        this.maaliyaatBarArzeshAfzude = maaliyaatBarArzeshAfzude;
    }

    public double getMajmuKolMabaaleghPardaakhti() {
        return majmuKolMabaaleghPardaakhti;
    }

    public void setMajmuKolMabaaleghPardaakhti(double majmuKolMabaaleghPardaakhti) {
        this.majmuKolMabaaleghPardaakhti = majmuKolMabaaleghPardaakhti;
    }

    public double getArzeshBazKharidBaaSudTazmini15Darsad() {
        return arzeshBazKharidBaaSudTazmini15Darsad;
    }

    public void setArzeshBazKharidBaaSudTazmini15Darsad(double arzeshBazKharidBaaSudTazmini15Darsad) {
        this.arzeshBazKharidBaaSudTazmini15Darsad = arzeshBazKharidBaaSudTazmini15Darsad;
    }

    public double getPishbiniArzeshBazKharidBaaSud20Darsad() {
        return pishbiniArzeshBazKharidBaaSud20Darsad;
    }

    public void setPishbiniArzeshBazKharidBaaSud20Darsad(double pishbiniArzeshBazKharidBaaSud20Darsad) {
        this.pishbiniArzeshBazKharidBaaSud20Darsad = pishbiniArzeshBazKharidBaaSud20Darsad;
    }

    public double getPishbiniArzeshBazKharidBaaSud22Darsad() {
        return pishbiniArzeshBazKharidBaaSud22Darsad;
    }

    public void setPishbiniArzeshBazKharidBaaSud22Darsad(double pishbiniArzeshBazKharidBaaSud22Darsad) {
        this.pishbiniArzeshBazKharidBaaSud22Darsad = pishbiniArzeshBazKharidBaaSud22Darsad;
    }

    public String getNahvePardakhteHagheBime() {
        return nahvePardakhteHagheBime;
    }

    public void setNahvePardakhteHagheBime(String nahvePardakhteHagheBime) {
        this.nahvePardakhteHagheBime = nahvePardakhteHagheBime;
    }

    public String getTarikheShoro() {
        return tarikheShoro;
    }

    public void setTarikheShoro(String tarikheShoro) {
        this.tarikheShoro = tarikheShoro;
    }

    public String getTarikheKhatame() {
        return tarikheKhatame;
    }

    public void setTarikheKhatame(String tarikheKhatame) {
        this.tarikheKhatame = tarikheKhatame;
    }

    public short getSenneBimeShodePasAzEmaleEzafeNerkh() {
        return senneBimeShodePasAzEmaleEzafeNerkh;
    }

    public void setSenneBimeShodePasAzEmaleEzafeNerkh(short senneBimeShodePasAzEmaleEzafeNerkh) {
        this.senneBimeShodePasAzEmaleEzafeNerkh = senneBimeShodePasAzEmaleEzafeNerkh;
    }

    public double getMablaghHagheBimeSalane() {
        return mablaghHagheBimeSalane;
    }

    public void setMablaghHagheBimeSalane(double mablaghHagheBimeSalane) {
        this.mablaghHagheBimeSalane = mablaghHagheBimeSalane;
    }

    public double getMablaghHagheBimeSheshMahe() {
        return mablaghHagheBimeSheshMahe;
    }

    public void setMablaghHagheBimeSheshMahe(double mablaghHagheBimeSheshMahe) {
        this.mablaghHagheBimeSheshMahe = mablaghHagheBimeSheshMahe;
    }

    public double getMablaghHagheBimeSeMahe() {
        return mablaghHagheBimeSeMahe;
    }

    public void setMablaghHagheBimeSeMahe(double mablaghHagheBimeSeMahe) {
        this.mablaghHagheBimeSeMahe = mablaghHagheBimeSeMahe;
    }

    public double getMablaghHagheBimeMahane() {
        return mablaghHagheBimeMahane;
    }

    public void setMablaghHagheBimeMahane(double mablaghHagheBimeMahane) {
        this.mablaghHagheBimeMahane = mablaghHagheBimeMahane;
    }

    public double getHaghBimePardakhtiBase() {
        return haghBimePardakhtiBase;
    }

    public void setHaghBimePardakhtiBase(double haghBimePardakhtiBase) {
        this.haghBimePardakhtiBase = haghBimePardakhtiBase;
    }

    public void setSarmaayePusheshEzaafiNaghsOzv(double sarmaayePusheshEzaafiNaghsOzv) {
        this.sarmaayePusheshEzaafiNaghsOzv = sarmaayePusheshEzaafiNaghsOzv;
    }

    public double getSarmaayePusheshEzaafiNaghsOzv() {
        return sarmaayePusheshEzaafiNaghsOzv;
    }

    public String getPoosheshMoafiat() {
        return poosheshMoafiat;
    }

    public void setPoosheshMoafiat(String poosheshMoafiat) {
        this.poosheshMoafiat = poosheshMoafiat;
    }

    public IRs getiRs() {
        return iRs;
    }

    public void setiRs(IRs iRs) {
        this.iRs = iRs;
    }

    public double getHaghBimePardaakhti() {
        return haghBimePardaakhti;
    }

    public void setHaghBimePardaakhti(double haghBimePardaakhti) {
        this.haghBimePardaakhti = haghBimePardaakhti;
    }
}
