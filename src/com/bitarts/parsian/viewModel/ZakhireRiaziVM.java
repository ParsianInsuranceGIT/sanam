package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Darkhast;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/10/13
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ZakhireRiaziVM
{
    private String ostan;
    private String city;
    private String vahedSodorName;
    private String vahedSodorCode;
    private String namayandeName;
    private String namayandeCode;
    private String bazaryabFullName;
    private String gharardadType;
    private String tarh;
    private String shomareBimename;
    private String bimeGozarFullName;
    private String bimeShodeFullName;
    private String bimeShodeAge;
    private String tarikhSodor;
    private String tarikhShoroBime;
    private String tarikhEngheza;
    private String modatBimename;
    private String sarmayeFot;
    private String hagheBimeKhalesFotYekja;
    private String nahvePardakhtHaghBime;
    private String haghBimePosheshHadese;
    private String haghBimeAmrazKhas;
    private String haghBimePosheshEzafi;
    private String jamSadere;
    private String jamPardakhti;
    private Boolean hijdahDarsad;
    private Integer bimenameStateId;
    private String bimenameStateName;
    private String bimenameStateChangeDate;

    private Integer bimenameId;

    private Long andukhteGhati;
    private Long andukhteAlalHesab;
    private Long BazkharidGhati;
    private Long BazkharidAlalHesab;

    private Integer ghestId;
    private Integer credebitId;

    private String nerkhTadilSarmaye;
    private String nerkhTadilHaghBime;
    private String shomareGhestVosouli;
    private String tarikhVosoulGhest;
    private String mablaghVosoulGhest;
    private String bimeGozarCodeMelli;
    private String bimeShodeCodeMelli;
    private String sarmayeFotHadese;
    private String haghBimePosheshAsli;
    private String haghBimePosheshTakmili;
    private String hazineTaghsit;
    private String zakhireRiaziSaleGhabl;
    private String zakhireRiaziSaleJari;
    private String maliat;
    private String bardashtAzAndukhte;
    private String zakhireRiaziGhati;
    private String arzeshBazkharid;
    private String codeSystemBimename;
    private String aghsatPardakhtShodeCount;
    private String gorohBimename;
    private String saleBimei;
    private String autoAndukhtAlalHesab;

    //zakhire tafkik ghest
    public ZakhireRiaziVM(String tarh, String gharardadType, String gorohBimename, String shomareBimename,String bimenameStateChangeDate,Integer bimenameStateId,String tarikhSodor, String tarikhShoroBime, String tarikhEngheza,
                          String nerkhTadilSarmaye, String nerkhTadilHaghBime, String shomareGhestVosouli, Integer credebitId, String bimeGozarFullName,
                          String bimeShodeFullName, String bimeGozarCodeMelli, String bimeShodeCodeMelli, String bimeShodeAge, String modatBimename,
                          String nahvePardakhtHaghBime, Long sarmayeFot, String sarmayeFotHadese, Long haghBimePosheshAsli, Long haghBimePosheshTakmili,
                          String hazineTaghsit, Long zakhireRiaziSaleGhabl, Long zakhireRiaziSaleJari, Long maliat, String bardashtAzAndukhte,
                          Long zakhireRiaziGhati, Long arzeshBazkharid, Long autoAndukhtAlalHesab, String codeSystemBimename, String aghsatPardakhtShodeCount,String tarikhMabna,String hazineKarmonz, String hazineBimegari, String hazineEdari, String hazineVosoul, Bimename bimename,
                          Integer etebarCredebitId, Credebit etebarCredebit)
    {
        this.gorohBimename = gorohBimename;
        this.tarh = tarh;
        this.gharardadType = gharardadType;
        this.shomareBimename=shomareBimename;
        if (bimenameStateChangeDate != null && bimenameStateChangeDate.length() > 0 && tarikhMabna != null && tarikhMabna.length() > 0){
            if (bimenameStateChangeDate.compareTo(tarikhMabna) <= 0)
                this.bimenameStateId = bimenameStateId;
            else
                this.bimenameStateId = Constant.BIMENAME_INITIAL_STATE;
        }
        this.bimenameStateName = getBimenameStateNameFarsi(bimename);
        this.tarikhSodor=tarikhSodor;
        this.tarikhShoroBime=tarikhShoroBime;
        this.tarikhEngheza=tarikhEngheza;
        this.nerkhTadilSarmaye=nerkhTadilSarmaye;
        this.nerkhTadilHaghBime=nerkhTadilHaghBime;
        this.shomareGhestVosouli=shomareGhestVosouli;
        this.tarikhVosoulGhest="-";
        if (etebarCredebitId != null && etebarCredebitId > 0){
            this.tarikhVosoulGhest= etebarCredebit.getDateFish();
            this.mablaghVosoulGhest = NumberFormat.getNumberInstance().format(etebarCredebit.getAmount_long());
        }
        this.bimeGozarFullName=bimeGozarFullName;
        this.bimeShodeFullName=bimeShodeFullName;
        this.bimeGozarCodeMelli=bimeGozarCodeMelli;
        this.bimeShodeCodeMelli=bimeShodeCodeMelli;
        //this.bimeShodeAge = String.valueOf(DateUtil.getBimeYear(tarikhSodor, bimeShodeAge)+ 1);
        this.bimeShodeAge = bimeShodeAge;
        this.modatBimename=modatBimename;
        this.nahvePardakhtHaghBime=nahvePardakhtHaghBime;
        this.sarmayeFot = sarmayeFot==null?"0": NumberFormat.getInstance().format(sarmayeFot);
        this.sarmayeFotHadese=sarmayeFotHadese==null?"0": NumberFormat.getInstance().format(Float.parseFloat(sarmayeFotHadese.replaceAll(",","")));
        this.haghBimePosheshAsli=haghBimePosheshAsli==null?"0": NumberFormat.getInstance().format(haghBimePosheshAsli);
        this.haghBimePosheshTakmili=haghBimePosheshTakmili==null?"0": NumberFormat.getInstance().format(haghBimePosheshTakmili);
        Float hazinetemp = 0f;
        if (hazineTaghsit != null && Float.parseFloat(hazineTaghsit) > 0){
            hazinetemp = Float.parseFloat(hazineKarmonz.replaceAll(",","")) + Float.parseFloat(hazineBimegari.replaceAll(",","")) + Float.parseFloat(hazineEdari.replaceAll(",","")) + Float.parseFloat(hazineVosoul.replaceAll(",","")) + haghBimePosheshAsli;
            hazinetemp = (hazinetemp * Float.parseFloat(hazineTaghsit)) - hazinetemp;
            hazinetemp = hazinetemp / Float.parseFloat(hazineTaghsit);
        }
        this.hazineTaghsit=hazineTaghsit==null?"0": NumberFormat.getInstance().format(hazinetemp.intValue());
        this.zakhireRiaziSaleGhabl=zakhireRiaziSaleGhabl==null?"": NumberFormat.getInstance().format(zakhireRiaziSaleGhabl > 0 ? zakhireRiaziSaleGhabl : 0);
        this.zakhireRiaziSaleJari=zakhireRiaziSaleJari==null?"": NumberFormat.getInstance().format((zakhireRiaziSaleJari - zakhireRiaziSaleGhabl > 0 ? zakhireRiaziSaleJari - zakhireRiaziSaleGhabl : 0));
        this.maliat=maliat==null?"0": NumberFormat.getInstance().format(maliat);
        this.bardashtAzAndukhte=bardashtAzAndukhte==null?"0": NumberFormat.getInstance().format(Float.parseFloat(bardashtAzAndukhte.replaceAll(",","")));
        this.zakhireRiaziGhati=zakhireRiaziGhati==null?"0": NumberFormat.getInstance().format(zakhireRiaziGhati > 0 ? zakhireRiaziGhati : 0);
        this.arzeshBazkharid=arzeshBazkharid==null?"0": NumberFormat.getInstance().format((arzeshBazkharid > 0 ? arzeshBazkharid : 0));
        this.autoAndukhtAlalHesab = autoAndukhtAlalHesab==null?"0" : NumberFormat.getInstance().format((autoAndukhtAlalHesab > 0 ? autoAndukhtAlalHesab : 0));
        this.codeSystemBimename=codeSystemBimename;
//        this.aghsatPardakhtShodeCount=aghsatPardakhtShodeCount;
        this.aghsatPardakhtShodeCount="1";
        Integer dd = DateUtil.getBimeYear(tarikhMabna,tarikhShoroBime) + 1;
        this.saleBimei = dd.toString();
    }

    public ZakhireRiaziVM(String ostan, String city, String vahedSodorName, String vahedSodorCode, String namayandeName, String namayandeCode, String bazaryabFullName,
                          String gharardadType, String tarh, String shomareBimename, String bimeGozarFullName, String bimeShodeFullName, String bimeShodeAge, String tarikhSodor,
                          String tarikhShoroBime, String tarikhEngheza, String modatBimename, Long sarmayeFot, String nahvePardakhtHaghBime, Boolean hijdahDarsad,
                          Long andukhteGhati, Long bazkharidGhati, Integer bimenameStateId, String bimenameStateChangeDate, Integer bimenameId,Integer credebitId)
    {
        this.ostan = ostan;
        this.city = city;
        this.vahedSodorName = vahedSodorName;
        this.vahedSodorCode = vahedSodorCode;
        this.namayandeName = namayandeName;
        this.namayandeCode = namayandeCode;
        this.bazaryabFullName = bazaryabFullName;
        this.gharardadType = gharardadType;
        this.tarh = tarh;
        this.shomareBimename = shomareBimename;
        this.bimeGozarFullName = bimeGozarFullName;
        this.bimeShodeFullName = bimeShodeFullName;
        this.bimeShodeAge = String.valueOf((int) Math.ceil(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), bimeShodeAge) / 365));
        this.tarikhSodor = tarikhSodor;
        this.tarikhShoroBime = tarikhShoroBime;
        this.tarikhEngheza = tarikhEngheza;
        this.modatBimename = modatBimename;
        this.sarmayeFot = sarmayeFot==null?"": NumberFormat.getInstance().format(sarmayeFot);
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
        this.hijdahDarsad = hijdahDarsad;
        this.andukhteGhati = andukhteGhati;
        this.BazkharidGhati = bazkharidGhati;
        this.bimenameStateId = bimenameStateId;
        this.bimenameStateChangeDate = bimenameStateChangeDate;
        this.bimenameId = bimenameId;
        this.credebitId = credebitId;
    }

    public String getBimenameStateNameFarsi(Bimename bimename)
    {
        if(bimename.getState() != null && bimename.getState().getId().equals(Constant.BIMENAME_LOCK_STATE))
        {
            return bimename.getState().getStateName() + " ( " + Darkhast.getTypeFarsi(bimename.getDarkhastDarJaryanType()) + " ) ";
        }
        else if (bimename.getState() != null && bimename.getState().getId().equals(Constant.BIMENAME_INITIAL_STATE))
        {
            if(bimename.getHasElhaghie()!=null && bimename.getHasElhaghie().equals("yes"))
            {
                return bimename.getState().getStateName() + " (دارای الحاقیه) ";
            }
            else
            {
                return bimename.getState().getStateName();
            }
        }
        else
        {
            return bimename.getState().getStateName();
        }
    }

    public String getBimenameStateName() {
        return bimenameStateName;
    }

    public void setBimenameStateName(String bimenameStateName) {
        this.bimenameStateName = bimenameStateName;
    }

    public ZakhireRiaziVM(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public String getOstan()
    {
        return ostan;
    }

    public void setOstan(String ostan)
    {
        this.ostan = ostan;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getVahedSodorName()
    {
        return vahedSodorName;
    }

    public void setVahedSodorName(String vahedSodorName)
    {
        this.vahedSodorName = vahedSodorName;
    }

    public String getVahedSodorCode()
    {
        return vahedSodorCode;
    }

    public void setVahedSodorCode(String vahedSodorCode)
    {
        this.vahedSodorCode = vahedSodorCode;
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

    public String getBazaryabFullName()
    {
        return bazaryabFullName;
    }

    public void setBazaryabFullName(String bazaryabFullName)
    {
        this.bazaryabFullName = bazaryabFullName;
    }

    public String getGharardadType()
    {
        return gharardadType;
    }

    public void setGharardadType(String gharardadType)
    {
        this.gharardadType = gharardadType;
    }

    public String getTarh()
    {
        return tarh;
    }

    public void setTarh(String tarh)
    {
        this.tarh = tarh;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getBimeGozarFullName()
    {
        return bimeGozarFullName;
    }

    public void setBimeGozarFullName(String bimeGozarFullName)
    {
        this.bimeGozarFullName = bimeGozarFullName;
    }

    public String getBimeShodeFullName()
    {
        return bimeShodeFullName;
    }

    public void setBimeShodeFullName(String bimeShodeFullName)
    {
        this.bimeShodeFullName = bimeShodeFullName;
    }

    public String getBimeShodeAge()
    {
        return bimeShodeAge;
    }

    public void setBimeShodeAge(String bimeShodeAge)
    {
        this.bimeShodeAge = bimeShodeAge;
    }

    public String getTarikhSodor()
    {
        return tarikhSodor;
    }

    public void setTarikhSodor(String tarikhSodor)
    {
        this.tarikhSodor = tarikhSodor;
    }

    public String getTarikhShoroBime()
    {
        return tarikhShoroBime;
    }

    public void setTarikhShoroBime(String tarikhShoroBime)
    {
        this.tarikhShoroBime = tarikhShoroBime;
    }

    public String getTarikhEngheza()
    {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza)
    {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getModatBimename()
    {
        return modatBimename;
    }

    public void setModatBimename(String modatBimename)
    {
        this.modatBimename = modatBimename;
    }

    public String getSarmayeFot()
    {
        return sarmayeFot;
    }

    public void setSarmayeFot(String sarmayeFot)
    {
        this.sarmayeFot = sarmayeFot;
    }

    public String getHagheBimeKhalesFotYekja()
    {
        return hagheBimeKhalesFotYekja;
    }

    public void setHagheBimeKhalesFotYekja(String hagheBimeKhalesFotYekja)
    {
        this.hagheBimeKhalesFotYekja = hagheBimeKhalesFotYekja;
    }

    public String getNahvePardakhtHaghBime()
    {
        return nahvePardakhtHaghBime;
    }

    public void setNahvePardakhtHaghBime(String nahvePardakhtHaghBime)
    {
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
    }

    public String getHaghBimePosheshHadese()
    {
        return haghBimePosheshHadese;
    }

    public void setHaghBimePosheshHadese(String haghBimePosheshHadese)
    {
        this.haghBimePosheshHadese = haghBimePosheshHadese;
    }

    public String getHaghBimeAmrazKhas()
    {
        return haghBimeAmrazKhas;
    }

    public void setHaghBimeAmrazKhas(String haghBimeAmrazKhas)
    {
        this.haghBimeAmrazKhas = haghBimeAmrazKhas;
    }

    public String getHaghBimePosheshEzafi()
    {
        return haghBimePosheshEzafi;
    }

    public void setHaghBimePosheshEzafi(String haghBimePosheshEzafi)
    {
        this.haghBimePosheshEzafi = haghBimePosheshEzafi;
    }

    public String getJamSadere()
    {
        return jamSadere;
    }

    public void setJamSadere(String jamSadere)
    {
        this.jamSadere = jamSadere;
    }

    public String getJamPardakhti()
    {
        return jamPardakhti;
    }

    public void setJamPardakhti(String jamPardakhti)
    {
        this.jamPardakhti = jamPardakhti;
    }

    public Integer getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public Long getAndukhteGhati() {
        return andukhteGhati;
    }

    public void setAndukhteGhati(Long andukhteGhati) {
        this.andukhteGhati = andukhteGhati;
    }

    public Long getAndukhteAlalHesab() {
        return andukhteAlalHesab;
    }

    public void setAndukhteAlalHesab(Long andukhteAlalHesab) {
        this.andukhteAlalHesab = andukhteAlalHesab;
    }

    public Long getBazkharidGhati() {
        return BazkharidGhati;
    }

    public void setBazkharidGhati(Long bazkharidGhati) {
        BazkharidGhati = bazkharidGhati;
    }

    public Long getBazkharidAlalHesab() {
        return BazkharidAlalHesab;
    }

    public void setBazkharidAlalHesab(Long bazkharidAlalHesab) {
        BazkharidAlalHesab = bazkharidAlalHesab;
    }

    public Boolean getHijdahDarsad() {
        if(hijdahDarsad == null) return false;
        return hijdahDarsad;
    }

    public void setHijdahDarsad(Boolean hijdahDarsad) {
        this.hijdahDarsad = hijdahDarsad;
    }

    public Integer getBimenameStateId() {
        return bimenameStateId;
    }

    public void setBimenameStateId(Integer bimenameStateId) {
        this.bimenameStateId = bimenameStateId;
    }

    public String getBimenameStateChangeDate() {
        return bimenameStateChangeDate;
    }

    public void setBimenameStateChangeDate(String bimenameStateChangeDate) {
        this.bimenameStateChangeDate = bimenameStateChangeDate;
    }

    public Integer getGhestId() {
        return ghestId;
    }

    public void setGhestId(Integer ghestId) {
        this.ghestId = ghestId;
    }

    public Integer getCredebitId() {
        return credebitId;
    }

    public void setCredebitId(Integer credebitId) {
        this.credebitId = credebitId;
    }

    public String getNerkhTadilSarmaye() {
        return nerkhTadilSarmaye;
    }

    public void setNerkhTadilSarmaye(String nerkhTadilSarmaye) {
        this.nerkhTadilSarmaye = nerkhTadilSarmaye;
    }

    public String getNerkhTadilHaghBime() {
        return nerkhTadilHaghBime;
    }

    public void setNerkhTadilHaghBime(String nerkhTadilHaghBime) {
        this.nerkhTadilHaghBime = nerkhTadilHaghBime;
    }

    public String getShomareGhestVosouli() {
        return shomareGhestVosouli;
    }

    public void setShomareGhestVosouli(String shomareGhestVosouli) {
        this.shomareGhestVosouli = shomareGhestVosouli;
    }

    public String getTarikhVosoulGhest() {
        return tarikhVosoulGhest;
    }

    public void setTarikhVosoulGhest(String tarikhVosoulGhest) {
        this.tarikhVosoulGhest = tarikhVosoulGhest;
    }

    public String getBimeGozarCodeMelli() {
        return bimeGozarCodeMelli;
    }

    public void setBimeGozarCodeMelli(String bimeGozarCodeMelli) {
        this.bimeGozarCodeMelli = bimeGozarCodeMelli;
    }

    public String getBimeShodeCodeMelli() {
        return bimeShodeCodeMelli;
    }

    public void setBimeShodeCodeMelli(String bimeShodeCodeMelli) {
        this.bimeShodeCodeMelli = bimeShodeCodeMelli;
    }

    public String getSarmayeFotHadese() {
        return sarmayeFotHadese;
    }

    public void setSarmayeFotHadese(String sarmayeFotHadese) {
        this.sarmayeFotHadese = sarmayeFotHadese;
    }

    public String getHaghBimePosheshAsli() {
        return haghBimePosheshAsli;
    }

    public void setHaghBimePosheshAsli(String haghBimePosheshAsli) {
        this.haghBimePosheshAsli = haghBimePosheshAsli;
    }

    public String getHaghBimePosheshTakmili() {
        return haghBimePosheshTakmili;
    }

    public void setHaghBimePosheshTakmili(String haghBimePosheshTakmili) {
        this.haghBimePosheshTakmili = haghBimePosheshTakmili;
    }

    public String getHazineTaghsit() {
        return hazineTaghsit;
    }

    public void setHazineTaghsit(String hazineTaghsit) {
        this.hazineTaghsit = hazineTaghsit;
    }

    public String getZakhireRiaziSaleGhabl() {
        return zakhireRiaziSaleGhabl;
    }

    public void setZakhireRiaziSaleGhabl(String zakhireRiaziSaleGhabl) {
        this.zakhireRiaziSaleGhabl = zakhireRiaziSaleGhabl;
    }

    public String getZakhireRiaziSaleJari() {
        return zakhireRiaziSaleJari;
    }

    public void setZakhireRiaziSaleJari(String zakhireRiaziSaleJari) {
        this.zakhireRiaziSaleJari = zakhireRiaziSaleJari;
    }

    public String getMaliat() {
        return maliat;
    }

    public void setMaliat(String maliat) {
        this.maliat = maliat;
    }

    public String getBardashtAzAndukhte() {
        return bardashtAzAndukhte;
    }

    public void setBardashtAzAndukhte(String bardashtAzAndukhte) {
        this.bardashtAzAndukhte = bardashtAzAndukhte;
    }

    public String getZakhireRiaziGhati() {
        return zakhireRiaziGhati;
    }

    public void setZakhireRiaziGhati(String zakhireRiaziGhati) {
        this.zakhireRiaziGhati = zakhireRiaziGhati;
    }

    public String getArzeshBazkharid() {
        return arzeshBazkharid;
    }

    public void setArzeshBazkharid(String arzeshBazkharid) {
        this.arzeshBazkharid = arzeshBazkharid;
    }

    public String getCodeSystemBimename() {
        return codeSystemBimename;
    }

    public void setCodeSystemBimename(String codeSystemBimename) {
        this.codeSystemBimename = codeSystemBimename;
    }

    public String getAghsatPardakhtShodeCount() {
        return aghsatPardakhtShodeCount;
    }

    public void setAghsatPardakhtShodeCount(String aghsatPardakhtShodeCount) {
        this.aghsatPardakhtShodeCount = aghsatPardakhtShodeCount;
    }

    public String getGorohBimename() {
        return gorohBimename;
    }

    public void setGorohBimename(String gorohBimename) {
        this.gorohBimename = gorohBimename;
    }

    public String getMablaghVosoulGhest() {
        return mablaghVosoulGhest;
    }

    public void setMablaghVosoulGhest(String mablaghVosoulGhest) {
        this.mablaghVosoulGhest = mablaghVosoulGhest;
    }

    public String getSaleBimei() {
        return saleBimei;
    }

    public void setSaleBimei(String saleBimei) {
        this.saleBimei = saleBimei;
    }

    public String getAutoAndukhtAlalHesab() {
        return autoAndukhtAlalHesab;
    }

    public void setAutoAndukhtAlalHesab(String autoAndukhtAlalHesab) {
        this.autoAndukhtAlalHesab = autoAndukhtAlalHesab;
    }
}
