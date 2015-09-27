package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.KhesaratHavale;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastVam;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.model.pishnahad.Moarefiname;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.calculations.Reports.FRs;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Jul 13, 2011
 * Time: 3:41:58 PM
 */
public class PishnehadReport {
    private Pishnehad pishnehad;
    private FRs previousFRs = null;
    private FRs currentFRs = null;
    private List<PishPardakhtReport> pishPardakhtReportList;
    private List<FRs> fRsList;
    private List<Ghest> ghestList;
    private List<Credebit> credebitList;
    private Double sumOfBedehi;
    private Double mizanBedehi;
    private Double sumOfPardakht;
    private List<PishnehadFieldChanges> pishnehadFieldChangesList;
    private Fish fish;
    private Moarefiname moarefiname;
    private List<SoratVaziateMali> soratVaziateMaliList;
    private Check check;
    private Bimename bimename;
    private DarkhastBazkharid darkhastBazkharid;
    private Pishnehad oldPishnehad;
    private Pishnehad newPishnehad;
    private DarkhastVam darkhastVam;
    private User user;
    private GhestBandi ghestBandi;
    private List<GhestForPrint> ghestForPrintList;
    private Sanad sanad;
    private ReportSharayetVam reportSharayetVam;
    private String haghBimeHadese;
    private String haghBimeAmraz;
    private String haghBimeMoafiat;
    private String haghBimeNaghsOzv;
    private String haghBimeElamBeMali;
    private String haghBimePardakhti;
    private String haghBimeMande;
    private String tozihatBimenameForPrint;
    private List<KhateSanad> khateSanadsBedehi;
    private List<KhateSanad> khateSanadsEtebar;
    private Elhaghiye currentElhaghiye;
    private String nameNamayandeAvalie;
    private String codeNamayandeAvalie;
    private String tarikhMabna;
    private String mablaghGhest;
    private Vagozari vagozari;
    private String totalBedehiAmount;
    private String totalEtebarAmount;
    private String totalEtebarAmountShenaseDar;
    private String haghBimeSal;
    private Credebit credebit;
    private Credebit bedehi;
    private List<CredebitBedehiVM> credebitBedehiVMList;
    private Long sumBedehiRemaining_Amount;
    private Long sumPardakhtshodeAmount;
    private String hadeAksareBardasht;
    private Khesarat khesarat;
    private KhesaratHavale havale;
    private boolean omr;
    private ByteArrayInputStream qrCodeInStream;

    //b-h
    private Long tedadCheckHayeVagozarshode;






    public Long getTedadCheckHayeVagozarshode() {
        return tedadCheckHayeVagozarshode;
    }

    public void setTedadCheckHayeVagozarshode(Long tedadCheckHayeVagozarshode) {

        this.tedadCheckHayeVagozarshode = tedadCheckHayeVagozarshode;
    }



    public ByteArrayInputStream getQrCodeInStream()
    {
        return qrCodeInStream;
    }

    public void setQrCodeInStream(ByteArrayInputStream qrCodeInStream)
    {
        this.qrCodeInStream = qrCodeInStream;
    }

    public Credebit getBedehi()
    {
        return bedehi;
    }

    public void setBedehi(Credebit bedehi)
    {
        this.bedehi = bedehi;
    }

    public Khesarat getKhesarat()
    {
        return khesarat;
    }

    public void setKhesarat(Khesarat khesarat)
    {
        this.khesarat = khesarat;
    }

    public KhesaratHavale getHavale()
    {
        return havale;
    }

    public void setHavale(KhesaratHavale havale)
    {
        this.havale = havale;
    }

    public String getHadeAksareBardasht()
    {
        return hadeAksareBardasht;
    }

    public void setHadeAksareBardasht(String hadeAksareBardasht)
    {
        this.hadeAksareBardasht = hadeAksareBardasht;
    }

    public Long getSumBedehiRemaining_Amount() {
        return sumBedehiRemaining_Amount;
    }

    public void setSumBedehiRemaining_Amount(Long sumBedehiRemaining_Amount) {
        this.sumBedehiRemaining_Amount = sumBedehiRemaining_Amount;
    }

    public Long getSumPardakhtshodeAmount() {
        return sumPardakhtshodeAmount;
    }

    public void setSumPardakhtshodeAmount(Long sumPardakhtshodeAmount) {
        this.sumPardakhtshodeAmount = sumPardakhtshodeAmount;
    }

    public List<CredebitBedehiVM> getCredebitBedehiVMList() {
        return credebitBedehiVMList;
    }

    public void setCredebitBedehiVMList(List<CredebitBedehiVM> credebitBedehiVMList) {
        this.credebitBedehiVMList = credebitBedehiVMList;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    private int saleTaghsit = -1;

    private List<Ghest> ghests;
    private String shomareBimename;
    private String shomareVam;
    private String nameBimeGozar;
    private String nameBimeShode;
    private String mahalleSodour;
    private String nameNamayande;
    private String kodeNamayande;
    private String nahveyePardakht;
    private boolean elhaghiyeDaemha = false;

    private boolean sanadTaeedEtebarShode;

    private String maliatAfzoade;

    public String getMaliatAfzoade()
    {
        return maliatAfzoade;
    }

    public void setMaliatAfzoade(String maliatAfzoade)
    {
        this.maliatAfzoade = maliatAfzoade;
    }

    public List<Ghest> getGhests()
    {
        return ghests;
    }

    public void setGhests(List<Ghest> ghests)
    {
        this.ghests = ghests;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getShomareVam()
    {
        return shomareVam;
    }

    public void setShomareVam(String shomareVam)
    {
        this.shomareVam = shomareVam;
    }

    public String getNameBimeGozar()
    {
        return nameBimeGozar;
    }

    public void setNameBimeGozar(String nameBimeGozar)
    {
        this.nameBimeGozar = nameBimeGozar;
    }

    public String getNameBimeShode()
    {
        return nameBimeShode;
    }

    public void setNameBimeShode(String nameBimeShode)
    {
        this.nameBimeShode = nameBimeShode;
    }

    public String getMahalleSodour()
    {
        return mahalleSodour;
    }

    public void setMahalleSodour(String mahalleSodour)
    {
        this.mahalleSodour = mahalleSodour;
    }

    public String getNameNamayande()
    {
        return nameNamayande;
    }

    public void setNameNamayande(String nameNamayande)
    {
        this.nameNamayande = nameNamayande;
    }

    public String getKodeNamayande()
    {
        return kodeNamayande;
    }

    public void setKodeNamayande(String kodeNamayande)
    {
        this.kodeNamayande = kodeNamayande;
    }

    public String getNahveyePardakht()
    {
        return nahveyePardakht;
    }

    public void setNahveyePardakht(String nahveyePardakht)
    {
        this.nahveyePardakht = nahveyePardakht;
    }

    public String getHaghBimeSal()
    {
        return haghBimeSal;
    }

    public void setHaghBimeSal(String haghBimeSal)
    {
        this.haghBimeSal = haghBimeSal;
    }

    public String getMablaghGhest()
    {
        return mablaghGhest;
    }

    public void setMablaghGhest(String mablaghGhest)
    {
        this.mablaghGhest = mablaghGhest;
    }

    public String getNameNamayandeAvalie()
    {
        return nameNamayandeAvalie;
    }

    public void setNameNamayandeAvalie(String nameNamayandeAvalie)
    {
        this.nameNamayandeAvalie = nameNamayandeAvalie;
    }

    public String getCodeNamayandeAvalie()
    {
        return codeNamayandeAvalie;
    }

    public void setCodeNamayandeAvalie(String codeNamayandeAvalie)
    {
        this.codeNamayandeAvalie = codeNamayandeAvalie;
    }

    public List<KhateSanad> getKhateSanadsBedehi()
    {
        return khateSanadsBedehi;
    }

    public void setKhateSanadsBedehi(List<KhateSanad> khateSanadsBedehi)
    {
        this.khateSanadsBedehi = khateSanadsBedehi;
    }

    public List<KhateSanad> getKhateSanadsEtebar()
    {
        return khateSanadsEtebar;
    }

    public void setKhateSanadsEtebar(List<KhateSanad> khateSanadsEtebar)
    {
        this.khateSanadsEtebar = khateSanadsEtebar;
    }

    public String getTozihatBimenameForPrint()
    {
        return tozihatBimenameForPrint;
    }

    public void setTozihatBimenameForPrint(String tozihatBimenameForPrint)
    {
        this.tozihatBimenameForPrint = tozihatBimenameForPrint;
    }

    private List<BimenameForGozaresh> bimenameForGozareshList;

    public List<BimenameForGozaresh> getBimenameForGozareshList() {
        return bimenameForGozareshList;
    }

    public void setBimenameForGozareshList(List<BimenameForGozaresh> bimenameForGozareshList) {
        this.bimenameForGozareshList = bimenameForGozareshList;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public List<FRs> getFRsList() {
        return fRsList;
    }

    public void setFRsList(List<FRs> fRsList) {
        this.fRsList = fRsList;
    }

    public List<Ghest> getGhestList() {
        return ghestList;
    }

    public void setGhestList(List<Ghest> ghestList) {
        this.ghestList = ghestList;
    }

    public Double getSumOfBedehi() {
        return sumOfBedehi;
    }

    public Double getMizanBedehi() {
        return mizanBedehi;
    }

    public void setMizanBedehi(Double mizanBedehi) {
        this.mizanBedehi = mizanBedehi;
    }

    public void setSumOfBedehi(Double sumOfBedehi) {
        this.sumOfBedehi = sumOfBedehi;
    }

    public Double getSumOfPardakht() {
        return sumOfPardakht;
    }

    public void setSumOfPardakht(Double sumOfPardakht) {
        this.sumOfPardakht = sumOfPardakht;
    }

    public List<PishnehadFieldChanges> getPishnehadFieldChangesList() {
        return pishnehadFieldChangesList;
    }

    public void setPishnehadFieldChangesList(List<PishnehadFieldChanges> pishnehadFieldChangesList) {
        this.pishnehadFieldChangesList = pishnehadFieldChangesList;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Moarefiname getMoarefiname() {
        return moarefiname;
    }

    public void setMoarefiname(Moarefiname moarefiname) {
        this.moarefiname = moarefiname;
    }

    public List<SoratVaziateMali> getSoratVaziateMaliList() {
        return soratVaziateMaliList;
    }

    public void setSoratVaziateMaliList(List<SoratVaziateMali> soratVaziateMaliList) {
        this.soratVaziateMaliList = soratVaziateMaliList;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public DarkhastVam getDarkhastVam() {
        return darkhastVam;
    }

    public void setDarkhastVam(DarkhastVam darkhastVam) {
        this.darkhastVam = darkhastVam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GhestBandi getGhestBandi() {
        return ghestBandi;
    }

    public void setGhestBandi(GhestBandi ghestBandi) {
        this.ghestBandi = ghestBandi;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public List<GhestForPrint> getGhestForPrintList() {
        return ghestForPrintList;
    }

    public void setGhestForPrintList(List<GhestForPrint> ghestForPrintList) {
        this.ghestForPrintList = ghestForPrintList;
    }

    public ReportSharayetVam getReportSharayetVam() {
        return reportSharayetVam;
    }

    public void setReportSharayetVam(ReportSharayetVam reportSharayetVam) {
        this.reportSharayetVam = reportSharayetVam;
    }

    public List<PishPardakhtReport> getPishPardakhtReportList() {
        return pishPardakhtReportList;
    }

    public void setPishPardakhtReportList(List<PishPardakhtReport> pishPardakhtReportList) {
        this.pishPardakhtReportList = pishPardakhtReportList;
    }

    public List<FRs> getfRsList() {
        return fRsList;
    }

    public void setfRsList(List<FRs> fRsList) {
        this.fRsList = fRsList;
    }

    public String getHaghBimeHadese() {
        return haghBimeHadese;
    }

    public void setHaghBimeHadese(String haghBimeHadese) {
        this.haghBimeHadese = haghBimeHadese;
    }

    public String getHaghBimeAmraz() {
        return haghBimeAmraz;
    }

    public void setHaghBimeAmraz(String haghBimeAmraz) {
        this.haghBimeAmraz = haghBimeAmraz;
    }

    public String getHaghBimeMoafiat() {
        return haghBimeMoafiat;
    }

    public void setHaghBimeMoafiat(String haghBimeMoafiat) {
        this.haghBimeMoafiat = haghBimeMoafiat;
    }

    public String getHaghBimeNaghsOzv() {
        return haghBimeNaghsOzv;
    }

    public void setHaghBimeNaghsOzv(String haghBimeNaghsOzv) {
        this.haghBimeNaghsOzv = haghBimeNaghsOzv;
    }

    public String getHaghBimePardakhti() {
        return haghBimePardakhti;
    }

    public void setHaghBimePardakhti(String haghBimePardakhti) {
        this.haghBimePardakhti = haghBimePardakhti;
    }

    public String getHaghBimeMande() {
        return haghBimeMande;
    }

    public void setHaghBimeMande(String haghBimeMande) {
        this.haghBimeMande = haghBimeMande;
    }

    public String getHaghBimeElamBeMali() {
        return haghBimeElamBeMali;
    }

    public void setHaghBimeElamBeMali(String haghBimeElamBeMali) {
        this.haghBimeElamBeMali = haghBimeElamBeMali;
    }

    public Pishnehad getOldPishnehad() {
        return oldPishnehad;
    }

    public void setOldPishnehad(Pishnehad oldPishnehad) {
        this.oldPishnehad = oldPishnehad;
    }

    public Pishnehad getNewPishnehad() {
        return newPishnehad;
    }

    public void setNewPishnehad(Pishnehad newPishnehad) {
        this.newPishnehad = newPishnehad;
    }

    public Elhaghiye getCurrentElhaghiye() {
        return currentElhaghiye;
    }

    public void setCurrentElhaghiye(Elhaghiye currentElhaghiye) {
        this.currentElhaghiye = currentElhaghiye;
    }

    public List<Credebit> getCredebitList() {
        return credebitList;
    }

    public void setCredebitList(List<Credebit> credebitList) {
        this.credebitList = credebitList;
    }

    public String getTarikhMabna() {
        return tarikhMabna;
    }

    public void setTarikhMabna(String tarikhMabna) {
        this.tarikhMabna = tarikhMabna;
    }

    public Vagozari getVagozari() {
        return vagozari;
    }

    public void setVagozari(Vagozari vagozari) {
        this.vagozari = vagozari;
    }

    public String getTotalBedehiAmount() {
        return totalBedehiAmount;
    }

    public void setTotalBedehiAmount(String totalBedehiAmount) {
        this.totalBedehiAmount = totalBedehiAmount;
    }

    public String getTotalEtebarAmount() {
        return totalEtebarAmount;
    }

    public void setTotalEtebarAmount(String totalEtebarAmount) {
        this.totalEtebarAmount = totalEtebarAmount;
    }

    public String getTotalEtebarAmountShenaseDar() {
        return totalEtebarAmountShenaseDar;
    }

    public void setTotalEtebarAmountShenaseDar(String totalEtebarAmountShenaseDar) {
        this.totalEtebarAmountShenaseDar = totalEtebarAmountShenaseDar;
    }

    public boolean isSanadTaeedEtebarShode() {
        return sanadTaeedEtebarShode;
    }

    public void setSanadTaeedEtebarShode(boolean sanadTaeedEtebarShode) {
        this.sanadTaeedEtebarShode = sanadTaeedEtebarShode;
    }

    public int getSaleTaghsit() {
        return saleTaghsit;
    }

    public void setSaleTaghsit(int saleTaghsit) {
        this.saleTaghsit = saleTaghsit;
    }

    public boolean isElhaghiyeDaemha() {
        return elhaghiyeDaemha;
    }

    public void setElhaghiyeDaemha(boolean elhaghiyeDaemha) {
        this.elhaghiyeDaemha = elhaghiyeDaemha;
    }

    public FRs getPreviousFRs() {
        return previousFRs;
    }

    public void setPreviousFRs(FRs previousFRs) {
        this.previousFRs = previousFRs;
    }

    public FRs getCurrentFRs() {
        return currentFRs;
    }

    public void setCurrentFRs(FRs currentFRs) {
        this.currentFRs = currentFRs;
    }

    public boolean isOmr()
    {
        return omr;
    }

    public void setOmr(boolean omr)
    {
        this.omr = omr;
    }
}

