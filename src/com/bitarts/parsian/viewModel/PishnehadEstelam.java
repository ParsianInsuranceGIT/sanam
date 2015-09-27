package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.calculations.Reports.IRs;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/20/12
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PishnehadEstelam {
    private String senBimeShode;
    private Pishnehad pishnehad;
    private String jamSadere;
    private IRs irs;
    private String haghBimeAzKaarOftaadegi;
    private String haghBimeFotDarAsarHaadese;
    private String haghBimeAmraazKhaas;
    private String haghBimePusheshHaayeEzaafi;
    private String haghBimeKhaalesFotYekja;
    private String haghBimePardaakhti;
    private String haghBimeNaghsOzv;
    private String shomareBimename;
    private String tarikhSodor;
    private String tarikhShorou;
    private String tarikhEngheza;
    private String sarmaye_pooshesh_naghs_ozv;
    private String sarmaye_paye_fot;
    private String nerkh_afzayesh_salaneh_hagh_bime;
    private String pooshesh_moafiatFarsi;
    private String sarmaye_paye_fot_dar_asar_hadese;
    private String pooshesh_amraz_khas;
    private String sarmaye_pooshesh_amraz_khas;
    private String kodeNamayandeKargozar;
    private String namayandeName;
    private String nahve_pardakht_hagh_bime;
    private String bimeGozarFullName;
    private String bimeShodeFullName;
    private String bazaryabFullName;
    private String nerkh_afzayesh_salaneh_sarmaye_fot;
    private String modat_bimename;
    private String ostanName;
    private String cityName;
    private String vahedSodorKode;
    private String vahedSodorName;
    private String noeGharardad;
    private String tarh;
    private String pooshesh_moafiat;
    private String karshenas;
    private String pooshesh_fot_dar_asar_haadese;
    private Integer bimenameId;
    private Integer ghestBandiId;
    private String groupBimename;
    private String poshtibanName;
    private String poshtibanCode;
    private String options;

//                    <display:column property="jamSadere" title="جمع حق بیمه صادره (ریال)"></display:column>
//                    <display:column property="haghBimeKhaalesFotYekja" title="حق بیمه خطر فوت (ریال)"></display:column>
//                    <display:column property="jamSadere" title="کل اعلام به مالی (ریال)"></display:column>

    public PishnehadEstelam(Integer bimenameId,String pooshesh_amraz_khas, String bimeGozarFullName,String senBimeShode, Long haghBimeAzKaarOftaadegi, Long haghBimeFotDarAsarHaadese, Long haghBimeAmraazKhaas, Long haghBimeNaghsOzv, String shomareBimename, String tarikhSodor, String tarikhShorou, String tarikhEngheza, String sarmaye_pooshesh_naghs_ozv, Long sarmaye_paye_fot, String nerkh_afzayesh_salaneh_hagh_bime, String sarmaye_paye_fot_dar_asar_hadese, String sarmaye_pooshesh_amraz_khas, String kodeNamayandeKargozar, String namayandeName, String nahve_pardakht_hagh_bime, String bimeShodeFullName, String bazaryabFullName, String nerkh_afzayesh_salaneh_sarmaye_fot, String modat_bimename, String ostanName, String cityName, String vahedSodorKode, String vahedSodorName, String noeGharardad, String tarh, String pooshesh_moafiat, String karshenas, String groupBimename,String poshtibanName,String poshtibanCode, String options)
    {
        this.bimenameId=bimenameId;
        this.bimeGozarFullName = bimeGozarFullName;
        this.senBimeShode = senBimeShode;
        if(haghBimeAzKaarOftaadegi!=null) this.haghBimeAzKaarOftaadegi = NumberFormat.getNumberInstance().format(haghBimeAzKaarOftaadegi) ;
        else this.haghBimeAzKaarOftaadegi = "0";
        if(haghBimeFotDarAsarHaadese!=null) this.haghBimeFotDarAsarHaadese = NumberFormat.getNumberInstance().format(haghBimeFotDarAsarHaadese);
        else this.haghBimeFotDarAsarHaadese="0";
        if(haghBimeAmraazKhaas!=null) this.haghBimeAmraazKhaas = NumberFormat.getNumberInstance().format(haghBimeAmraazKhaas);
        else this.haghBimeAmraazKhaas = "0";
//        this.haghBimePardaakhti = NumberFormat.getNumberInstance().format(haghBimepardakhtiAval);
        if(haghBimeNaghsOzv!=null) this.haghBimeNaghsOzv = NumberFormat.getNumberInstance().format(haghBimeNaghsOzv);
        else this.haghBimeNaghsOzv="0";
        this.shomareBimename = shomareBimename;
        this.tarikhSodor = tarikhSodor;
        this.tarikhShorou = tarikhShorou;
        this.tarikhEngheza = tarikhEngheza;
        this.sarmaye_pooshesh_naghs_ozv = sarmaye_pooshesh_naghs_ozv;
        if(sarmaye_paye_fot!=null) this.sarmaye_paye_fot = NumberFormat.getNumberInstance().format(sarmaye_paye_fot);
        else this.sarmaye_paye_fot="0";
        this.nerkh_afzayesh_salaneh_hagh_bime = nerkh_afzayesh_salaneh_hagh_bime;
        this.sarmaye_paye_fot_dar_asar_hadese = sarmaye_paye_fot_dar_asar_hadese;
        this.sarmaye_pooshesh_amraz_khas = sarmaye_pooshesh_amraz_khas;
        this.pooshesh_amraz_khas=pooshesh_amraz_khas;
        this.kodeNamayandeKargozar = kodeNamayandeKargozar;
        this.namayandeName = namayandeName;
        this.nahve_pardakht_hagh_bime = nahve_pardakht_hagh_bime;
        this.bimeShodeFullName = bimeShodeFullName;
        this.bazaryabFullName = bazaryabFullName;
        this.nerkh_afzayesh_salaneh_sarmaye_fot = nerkh_afzayesh_salaneh_sarmaye_fot;
        this.modat_bimename = modat_bimename;
        this.ostanName = ostanName;
        this.cityName = cityName;
        this.vahedSodorKode = vahedSodorKode;
        this.vahedSodorName = vahedSodorName;
        this.noeGharardad = noeGharardad;
        this.tarh = tarh;
        this.pooshesh_moafiat = pooshesh_moafiat;
        this.karshenas = karshenas;
//        this.ghestBandiId=ghestBandiId;
        this.groupBimename=groupBimename;
        this.poshtibanName=poshtibanName;
        this.poshtibanCode=poshtibanCode;
        this.options=options;
    }

    public String getPoshtibanName()
    {
        return poshtibanName;
    }

    public void setPoshtibanName(String poshtibanName)
    {
        this.poshtibanName = poshtibanName;
    }

    public String getPoshtibanCode()
    {
        return poshtibanCode;
    }

    public void setPoshtibanCode(String poshtibanCode)
    {
        this.poshtibanCode = poshtibanCode;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getGroupBimename()
    {
        return groupBimename;
    }

    public void setGroupBimename(String groupBimename)
    {
        this.groupBimename = groupBimename;
    }

    public Integer getGhestBandiId()
    {
        return ghestBandiId;
    }

    public void setGhestBandiId(Integer ghestBandiId)
    {
        this.ghestBandiId = ghestBandiId;
    }

    public Integer getBimenameId()
    {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId)
    {
        this.bimenameId = bimenameId;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getTarikhSodor()
    {
        return tarikhSodor;
    }

    public void setTarikhSodor(String tarikhSodor)
    {
        this.tarikhSodor = tarikhSodor;
    }

    public String getTarikhShorou()
    {
        return tarikhShorou;
    }

    public void setTarikhShorou(String tarikhShorou)
    {
        this.tarikhShorou = tarikhShorou;
    }

    public String getTarikhEngheza()
    {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza)
    {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getSarmaye_pooshesh_naghs_ozv()
    {
        return sarmaye_pooshesh_naghs_ozv;
    }

    public void setSarmaye_pooshesh_naghs_ozv(String sarmaye_pooshesh_naghs_ozv)
    {
        this.sarmaye_pooshesh_naghs_ozv = sarmaye_pooshesh_naghs_ozv;
    }

    public String getSarmaye_paye_fot()
    {
        return sarmaye_paye_fot;
    }

    public void setSarmaye_paye_fot(String sarmaye_paye_fot)
    {
        this.sarmaye_paye_fot = sarmaye_paye_fot;
    }

    public String getNerkh_afzayesh_salaneh_hagh_bime()
    {
        return nerkh_afzayesh_salaneh_hagh_bime;
    }

    public void setNerkh_afzayesh_salaneh_hagh_bime(String nerkh_afzayesh_salaneh_hagh_bime)
    {
        this.nerkh_afzayesh_salaneh_hagh_bime = nerkh_afzayesh_salaneh_hagh_bime;
    }

    public String getPooshesh_moafiatFarsi()
    {
        return pooshesh_moafiatFarsi;
    }

    public void setPooshesh_moafiatFarsi(String pooshesh_moafiatFarsi)
    {
        this.pooshesh_moafiatFarsi = pooshesh_moafiatFarsi;
    }

    public String getSarmaye_paye_fot_dar_asar_hadese()
    {
        return sarmaye_paye_fot_dar_asar_hadese;
    }

    public void setSarmaye_paye_fot_dar_asar_hadese(String sarmaye_paye_fot_dar_asar_hadese)
    {
        this.sarmaye_paye_fot_dar_asar_hadese = sarmaye_paye_fot_dar_asar_hadese;
    }

    public String getPooshesh_amraz_khas()
    {
        return pooshesh_amraz_khas;
    }

    public void setPooshesh_amraz_khas(String pooshesh_amraz_khas)
    {
        this.pooshesh_amraz_khas = pooshesh_amraz_khas;
    }

    public String getSarmaye_pooshesh_amraz_khas()
    {
        return sarmaye_pooshesh_amraz_khas;
    }

    public void setSarmaye_pooshesh_amraz_khas(String sarmaye_pooshesh_amraz_khas)
    {
        this.sarmaye_pooshesh_amraz_khas = sarmaye_pooshesh_amraz_khas;
    }

    public String getKodeNamayandeKargozar()
    {
        return kodeNamayandeKargozar;
    }

    public void setKodeNamayandeKargozar(String kodeNamayandeKargozar)
    {
        this.kodeNamayandeKargozar = kodeNamayandeKargozar;
    }

    public String getNamayandeName()
    {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName)
    {
        this.namayandeName = namayandeName;
    }

    public String getNahve_pardakht_hagh_bime()
    {
        return nahve_pardakht_hagh_bime;
    }

    public void setNahve_pardakht_hagh_bime(String nahve_pardakht_hagh_bime)
    {
        this.nahve_pardakht_hagh_bime = nahve_pardakht_hagh_bime;
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

    public String getBazaryabFullName()
    {
        return bazaryabFullName;
    }

    public void setBazaryabFullName(String bazaryabFullName)
    {
        this.bazaryabFullName = bazaryabFullName;
    }

    public String getNerkh_afzayesh_salaneh_sarmaye_fot()
    {
        return nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public void setNerkh_afzayesh_salaneh_sarmaye_fot(String nerkh_afzayesh_salaneh_sarmaye_fot)
    {
        this.nerkh_afzayesh_salaneh_sarmaye_fot = nerkh_afzayesh_salaneh_sarmaye_fot;
    }

    public String getModat_bimename()
    {
        return modat_bimename;
    }

    public void setModat_bimename(String modat_bimename)
    {
        this.modat_bimename = modat_bimename;
    }

    public String getOstanName()
    {
        return ostanName;
    }

    public void setOstanName(String ostanName)
    {
        this.ostanName = ostanName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getVahedSodorKode()
    {
        return vahedSodorKode;
    }

    public void setVahedSodorKode(String vahedSodorKode)
    {
        this.vahedSodorKode = vahedSodorKode;
    }

    public String getVahedSodorName()
    {
        return vahedSodorName;
    }

    public void setVahedSodorName(String vahedSodorName)
    {
        this.vahedSodorName = vahedSodorName;
    }

    public String getNoeGharardad()
    {
        return noeGharardad;
    }

    public void setNoeGharardad(String noeGharardad)
    {
        this.noeGharardad = noeGharardad;
    }

    public String getTarh()
    {
        return tarh;
    }

    public void setTarh(String tarh)
    {
        this.tarh = tarh;
    }

    public String getPooshesh_moafiat()
    {
        return pooshesh_moafiat;
    }

    public void setPooshesh_moafiat(String pooshesh_moafiat)
    {
        this.pooshesh_moafiat = pooshesh_moafiat;
    }

    public String getKarshenas()
    {
        return karshenas;
    }

    public void setKarshenas(String karshenas)
    {
        this.karshenas = karshenas;
    }

    public String getPooshesh_fot_dar_asar_haadese()
    {
        return pooshesh_fot_dar_asar_haadese;
    }

    public void setPooshesh_fot_dar_asar_haadese(String pooshesh_fot_dar_asar_haadese)
    {
        this.pooshesh_fot_dar_asar_haadese = pooshesh_fot_dar_asar_haadese;
    }

    public String getSenBimeShode()
    {
        return senBimeShode;
    }

    public void setSenBimeShode(String senBimeShode)
    {
        this.senBimeShode = senBimeShode;
    }

    public String getHaghBimeNaghsOzv()
    {
        if(haghBimeNaghsOzv==null)
            return "-";
        return NumberFormat.getNumberInstance().format(Long.parseLong(haghBimeNaghsOzv.replaceAll(",","").trim()));
    }

    public void setHaghBimeNaghsOzv(String haghBimeNaghsOzv)
    {
        this.haghBimeNaghsOzv = haghBimeNaghsOzv;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public IRs getIrs() {
        return irs;
    }

    public void setIrs(IRs irs) {
        this.irs = irs;
    }

    public String getJamSadere() {
        return jamSadere;
    }

    public void setJamSadere(String jamSadere) {
        this.jamSadere = jamSadere;
    }

    public String getHaghBimeAzKaarOftaadegi() {
        if(haghBimeAzKaarOftaadegi==null)
            return "-";
        return NumberFormat.getNumberInstance().format(Long.parseLong(haghBimeAzKaarOftaadegi.replaceAll(",","").trim()));
    }

    public void setHaghBimeAzKaarOftaadegi(String haghBimeAzKaarOftaadegi) {
        this.haghBimeAzKaarOftaadegi = haghBimeAzKaarOftaadegi;
    }

    public String getHaghBimeFotDarAsarHaadese() {
        if(haghBimeFotDarAsarHaadese==null)
            return "-";
        return NumberFormat.getNumberInstance().format(Long.parseLong(haghBimeFotDarAsarHaadese.replaceAll(",","").trim()));
    }

    public void setHaghBimeFotDarAsarHaadese(String haghBimeFotDarAsarHaadese) {
        this.haghBimeFotDarAsarHaadese = haghBimeFotDarAsarHaadese;
    }

    public String getHaghBimeAmraazKhaas() {
        if(haghBimeAmraazKhaas==null)
            return "-";
        return NumberFormat.getNumberInstance().format(Long.parseLong(haghBimeAmraazKhaas.replaceAll(",","").trim()));
    }

    public void setHaghBimeAmraazKhaas(String haghBimeAmraazKhaas) {
        this.haghBimeAmraazKhaas = haghBimeAmraazKhaas;
    }

    public String getHaghBimePusheshHaayeEzaafi() {
            return NumberFormat.getInstance().format(Long.parseLong(getHaghBimeAzKaarOftaadegi().replaceAll(",", "").trim()) +
                                                     Long.parseLong(getHaghBimeFotDarAsarHaadese().replaceAll(",", "").trim()) +
                                                     Long.parseLong(getHaghBimeAmraazKhaas().replaceAll(",", "").trim()) +
                                                     Long.parseLong(getHaghBimeNaghsOzv().replaceAll(",", "").trim()));
    }

    public void setHaghBimePusheshHaayeEzaafi(String haghBimePusheshHaayeEzaafi) {
        this.haghBimePusheshHaayeEzaafi = haghBimePusheshHaayeEzaafi;
    }

    public String getHaghBimeKhaalesFotYekja() {
        if(haghBimeKhaalesFotYekja==null)
            return "-";
        return NumberFormat.getNumberInstance().format(Long.parseLong(haghBimeKhaalesFotYekja.replaceAll(",","").trim()));
    }

    public void setHaghBimeKhaalesFotYekja(String haghBimeKhaalesFotYekja) {
        this.haghBimeKhaalesFotYekja = haghBimeKhaalesFotYekja;
    }

    public String getHaghBimePardaakhti() {
        if(haghBimePardaakhti==null)
            return "-";
        return haghBimePardaakhti;
    }

    public void setHaghBimePardaakhti(String haghBimePardaakhti) {
        this.haghBimePardaakhti = haghBimePardaakhti;
    }

}
