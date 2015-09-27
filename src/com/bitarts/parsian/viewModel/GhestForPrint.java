package com.bitarts.parsian.viewModel;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 10/2/11
 * Time: 11:55 AM
 */
public class GhestForPrint {
    private String shomareMoshtari;
    private String amount;
    private String amountHorofi;
    private String shomareBimename;
    private String nameBimeGozar;
    private String nameBimeShode;
    private String mahalleSodour;
    private String nameNamayande;
    private String kodeNamayande;
    private String nahveyePardakht;
    private String sarresid;
    private String shomareyeSal;
    private String createdDate;
    private String createdTime;
    private String hashieString;
    private String shomareGhest;
    private String shomareVam;
    private String shomareHesab;

    public GhestForPrint(GhestForPrint copy) {
        shomareMoshtari = copy.getShomareMoshtari();
        amount = copy.getAmount();
        amountHorofi = copy.getAmountHorofi();
        shomareBimename = copy.getShomareBimename();
        nameBimeGozar = copy.getNameBimeGozar();
        nameBimeShode = copy.getNameBimeShode();
        mahalleSodour = copy.getMahalleSodour();
        nameNamayande = copy.getNameNamayande();
        kodeNamayande = copy.getKodeNamayande();
        nahveyePardakht = copy.getNahveyePardakht();
        sarresid = copy.getSarresid();
        setShomareyeSal(copy.getShomareyeSal());
        createdDate = copy.getCreatedDate();
        createdTime = copy.getCreatedTime();
        hashieString = copy.getHashieString();
        shomareGhest = copy.getShomareGhest();
        shomareHesab = copy.getShomareHesab();
    }


    public String getShomareVam()
    {
        return shomareVam;
    }

    public void setShomareVam(String shomareVam)
    {
        this.shomareVam = shomareVam;
    }

    public GhestForPrint() {}

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getNameBimeGozar() {
        return nameBimeGozar;
    }

    public void setNameBimeGozar(String nameBimeGozar) {
        this.nameBimeGozar = nameBimeGozar;
    }

    public String getNameBimeShode() {
        return nameBimeShode;
    }

    public void setNameBimeShode(String nameBimeShode) {
        this.nameBimeShode = nameBimeShode;
    }

    public String getMahalleSodour() {
        return mahalleSodour;
    }

    public void setMahalleSodour(String mahalleSodour) {
        this.mahalleSodour = mahalleSodour;
    }

    public String getNameNamayande() {
        return nameNamayande;
    }

    public void setNameNamayande(String nameNamayande) {
        this.nameNamayande = nameNamayande;
    }

    public String getKodeNamayande() {
        return kodeNamayande;
    }

    public void setKodeNamayande(String kodeNamayande) {
        this.kodeNamayande = kodeNamayande;
    }

    public String getNahveyePardakht() {
        return nahveyePardakht;
    }

    public void setNahveyePardakht(String nahveyePardakht) {
        this.nahveyePardakht = nahveyePardakht;
    }

    public String getSarresid() {
        return sarresid;
    }

    public void setSarresid(String sarresid) {
        this.sarresid = sarresid;
    }

    public String getShomareyeSal() {
        return shomareyeSal;
    }

    public void setShomareyeSal(String shomareyeSal) {
        this.shomareyeSal = shomareyeSal;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAmountHorofi() {
        return amountHorofi;
    }

    public void setAmountHorofi(String amountHorofi) {
        this.amountHorofi = amountHorofi;
    }

    public String getHashieString() {
        return hashieString;
    }

    public void setHashieString(String hashieString) {
        this.hashieString = hashieString;
    }

    public String getShomareGhest() {
        return shomareGhest;
    }

    public void setShomareGhest(String shomareGhest) {
        this.shomareGhest = shomareGhest;
    }

    public void setShomareHesab(String shomareHesab)
    {
        this.shomareHesab=shomareHesab;
    }

    public String getShomareHesab()
    {
        return shomareHesab;
    }
}
