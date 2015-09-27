package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.constantItems.Tarh;

import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Oct 5, 2011
 * Time: 11:07:40 PM
 */
public class BimenameForGozaresh {
    private Long primitiveEstelamSarmayePayeFot;
    private String radif = "";
    private String ostan = "";
    private String ostanId = "";
    private String shahr = "";
    private String shahrId = "";
    private String vahedSodur = "";
    private Long vahedSodurId;
    private String namayandegi = "";
    private Long namayandegiId;
    private String azTarikhPardakht = "";
    private String taTarikhPardakht = "";
    private String kodVahedSodur = "";
    private String namNamayande = "";
    private String kodNamayande = "";
    private String noeBimename = "";
    private String noeGharardad = "";
    private Tarh tarh;
    private String shomareBimename = "";
    private String tarikhSodurBimename = "";
    private String nameBimeGozar = "";
    private String nameBimeShode = "";
    private String tarikhShoru = "";
    private String tarikhEngheza = "";
    private String azTarikhEngheza = "";
    private String taTarikhEngheza = "";
    private String modatBimename = "";
    private String raveshPardakhtBimename = "";
    private String mablaghGhest = "";
    private String tarikhSarresidGhest = "";
    private String mablaghPardakhtiTavasotBimegozar = "";
    private String tarikhSodurGhabzResid = "";
    private String tarikhPardakhtGhest = "";
    private String shomareGhabzResid = "";
    private String jameHaghBimeSadere = "";
    private String haghBimePoosheshhayeAsli = "";
    private String haghBimePoosheshhayeEzafi = "";
    private String searchBar = "";
    private String taTarikhSodor, azTarikhSodor;
    private String taTarikhSodorElhaghiye, azTarikhSodorElhaghiye;
    private String salBimeei;
    private String sarmayeFot;
    private String maliat;
    private String payeAndMaliat;
    private String tarhName;
    private Integer bimenameId;
    private Long groupId;
    private String groupBimename;
    private String poshtibanName;
    private String poshtibanCode;
    private String options;
    private String azTarikhSanad;
    private String taTarikhSanad;
    private String codeMeliBimegozar;
    private Long haghBimeSaleAval;
    private String sepordeAvalie;
    private String primitiveHaghBimePardakhti;
    private Integer ghestNumber;
    private String primitiveNahvePardakht;
    private String bazaryab;



    public BimenameForGozaresh() {}

    public BimenameForGozaresh(String ostan, String shahr, String vahedSodur, String kodVahedSodur, String namNamayande, String kodNamayande, String shomareBimename, String tarikhSodurBimename, String salBimeei, String nameBimeGozar, String nameBimeShode, String tarikhShoru, String tarikhEngheza, String modatBimename, Long maliat, Long sarmayeFot, String raveshPardakhtBimename, String noeGharardad, String tarhName, Long mablaghGhest, String tarikhSarresidGhest, String mablaghPardakhtiTavasotBimegozar, String tarikhSodurGhabzResid, String tarikhPardakhtGhest, String shomareGhabzResid, Integer bimenameId,Long haghBimePoosheshhayeEzafi,Long haghBimeFot_long, String groupBimename, String poshtibanName, String poshtibanCode, String options,String primitiveHaghBimePardakhti,Long primitiveEstelamSarmayePayeFot,String primitiveNahvePardakht ,String sepordeAvalie, Integer ghestNumber, String bazaryab) {
        // gozaresh bordroye mabalegh pardakhti
        this.haghBimePoosheshhayeAsli = haghBimeFot_long==null?"0" : NumberFormat.getNumberInstance().format(haghBimeFot_long);
        if(haghBimePoosheshhayeEzafi==null)
            haghBimePoosheshhayeEzafi=0l;
        if(mablaghPardakhtiTavasotBimegozar==null)
            mablaghPardakhtiTavasotBimegozar="0";
        if(maliat==null)
            maliat=0l;
        this.haghBimePoosheshhayeEzafi = NumberFormat.getNumberInstance().format((haghBimePoosheshhayeEzafi * Long.parseLong(mablaghPardakhtiTavasotBimegozar.replaceAll(",", "").trim()))/ mablaghGhest);
        this.ostan = ostan;
        this.shahr = shahr;
        this.vahedSodur = vahedSodur;
        this.kodVahedSodur = kodVahedSodur;
        this.namNamayande = namNamayande;
        this.kodNamayande = kodNamayande;
        this.noeBimename = "بيمه نامه عمر و سرمايه گذاري";
        this.shomareBimename = shomareBimename;
        this.tarikhSodurBimename = tarikhSodurBimename;
        if(salBimeei == null) {
            this.salBimeei = "-";
        } else {
            this.salBimeei = String.valueOf(Integer.parseInt(salBimeei) + 1);
        }
        this.nameBimeGozar = nameBimeGozar;
        this.nameBimeShode = nameBimeShode;
        this.tarikhShoru = tarikhShoru;
        this.tarikhEngheza = tarikhEngheza;
        this.modatBimename = modatBimename;
        this.maliat = NumberFormat.getNumberInstance().format(maliat* Long.parseLong(mablaghPardakhtiTavasotBimegozar.replaceAll(",", "").trim()) /mablaghGhest);
        this.sarmayeFot = sarmayeFot!=null?NumberFormat.getNumberInstance().format(sarmayeFot):"0";
        this.raveshPardakhtBimename = raveshPardakhtBimename;
        this.noeGharardad = noeGharardad;
        this.setTarhName(tarhName);
        this.mablaghGhest = NumberFormat.getNumberInstance().format(mablaghGhest);
        this.tarikhSarresidGhest = tarikhSarresidGhest;
        this.mablaghPardakhtiTavasotBimegozar = NumberFormat.getNumberInstance().format(Long.parseLong(mablaghPardakhtiTavasotBimegozar.replaceAll(",", "").trim()));
        this.tarikhSodurGhabzResid = tarikhSodurGhabzResid;
        this.tarikhPardakhtGhest = tarikhPardakhtGhest;
        this.shomareGhabzResid = shomareGhabzResid;
        this.bimenameId = bimenameId;
        this.groupBimename = groupBimename;
        this.poshtibanCode=poshtibanCode;
        this.poshtibanName=poshtibanName;
        this.options= options ;
        this.haghBimeSaleAval= haghBimeSaleAval ;
        this.ghestNumber=ghestNumber;
        this.primitiveNahvePardakht= primitiveNahvePardakht;
        this.primitiveHaghBimePardakhti= primitiveHaghBimePardakhti;
        this.primitiveEstelamSarmayePayeFot=primitiveEstelamSarmayePayeFot ;
        this.sepordeAvalie=sepordeAvalie ;
        this.bazaryab=bazaryab;
    }

    public String getSepordeAvalie()
    {
        return sepordeAvalie;
    }

    public void setSepordeAvalie(String sepordeAvalie)
    {
        this.sepordeAvalie = sepordeAvalie;
    }

    public String getPrimitiveEstelamSarmayePayeFotStr()
    {
        return NumberFormat.getInstance().format(primitiveEstelamSarmayePayeFot);
    }
    public Long getPrimitiveEstelamSarmayePayeFot()
    {
        return primitiveEstelamSarmayePayeFot;
    }

    public void setPrimitiveEstelamSarmayePayeFot(Long primitiveEstelamSarmayePayeFot)
    {
        this.primitiveEstelamSarmayePayeFot = primitiveEstelamSarmayePayeFot;
    }

    public String getPrimitiveHaghBimePardakhti()
    {
        return primitiveHaghBimePardakhti;
    }

    public void setPrimitiveHaghBimePardakhti(String primitiveHaghBimePardakhti)
    {
        this.primitiveHaghBimePardakhti = primitiveHaghBimePardakhti;
    }

    public String getPrimitiveNahvePardakht()
    {
        return primitiveNahvePardakht;
    }

    public void setPrimitiveNahvePardakht(String primitiveNahvePardakht)
    {
        this.primitiveNahvePardakht = primitiveNahvePardakht;
    }

    public Integer getGhestNumber()
    {
        return ghestNumber;
    }

    public void setGhestNumber(Integer ghestNumber)
    {
        this.ghestNumber = ghestNumber;
    }

    public String getPoshtibanName()
    {
        return poshtibanName;
    }

    public String getBazaryab() {
        return bazaryab;
    }

    public void setBazaryab(String bazaryab) {
        this.bazaryab = bazaryab;
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

    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getHaghBimeSaleAval()
    {
        return haghBimeSaleAval;
    }

    public void setHaghBimeSaleAval(Long haghBimeSaleAval)
    {
        this.haghBimeSaleAval = haghBimeSaleAval;
    }

    public String getHaghBimeSaleAvalStr()
    {
        long count=1;
        if(primitiveNahvePardakht == null)
            return "خطا";
        if (primitiveNahvePardakht.equals("mah"))
            count=12;
        else if (primitiveNahvePardakht.equals("3mah"))
            count=4;
        else if (primitiveNahvePardakht.equals("6mah"))
            count=2;
        if(count>1)
            return NumberFormat.getInstance().format(Long.parseLong(getPrimitiveHaghBimePardakhti().replaceAll(",","").trim())*count);
        else
            return getSepordeAvalie();
    }

    public String getPayeAndMaliat()
    {
        long rtnVal=Long.parseLong(getMablaghPardakhtiTavasotBimegozar().replaceAll(",","").trim())-(Long.parseLong(getHaghBimePoosheshhayeEzafi().replaceAll(",","").trim())+Long.parseLong(getMaliat().replaceAll(",","").trim()));
        return NumberFormat.getNumberInstance().format(rtnVal);

//        return NumberFormat.getInstance().format(Long.parseLong(maliat.replaceAll(",", "").trim()) + Long.parseLong(mablaghGhest.replaceAll(",", "").trim()));
    }

    public void setPayeAndMaliat(String payeAndMaliat)
    {
        this.payeAndMaliat = payeAndMaliat;
    }

    public String getMaliat()
    {
        return maliat;
    }

    public void setMaliat(String maliat)
    {
        this.maliat = maliat;
    }

    public String getSarmayeFot()
    {
        return sarmayeFot;
    }

    public void setSarmayeFot(String sarmayeFot)
    {
        this.sarmayeFot = sarmayeFot;
    }

    public String getSalBimeei()
    {
        return salBimeei;
    }

    public void setSalBimeei(String salBimeei)
    {
        this.salBimeei = salBimeei;
    }

    public String getTaTarikhSodor()
    {
        return taTarikhSodor;
    }

    public void setTaTarikhSodor(String taTarikhSodor)
    {
        this.taTarikhSodor = taTarikhSodor;
    }

    public String getAzTarikhSodor()
    {
        return azTarikhSodor;
    }

    public void setAzTarikhSodor(String azTarikhSodor)
    {
        this.azTarikhSodor = azTarikhSodor;
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

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahr() {
        return shahr;
    }

    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    public String getVahedSodur() {
        return vahedSodur;
    }

    public void setVahedSodur(String vahedSodur) {
        this.vahedSodur = vahedSodur;
    }

    public String getKodVahedSodur() {
        return kodVahedSodur;
    }

    public void setKodVahedSodur(String kodVahedSodur) {
        this.kodVahedSodur = kodVahedSodur;
    }

    public String getNamNamayande() {
        return namNamayande;
    }

    public void setNamNamayande(String namNamayande) {
        this.namNamayande = namNamayande;
    }

    public String getKodNamayande() {
        return kodNamayande;
    }

    public void setKodNamayande(String kodNamayande) {
        this.kodNamayande = kodNamayande;
    }

    public String getNoeBimename() {
        return noeBimename;
    }

    public void setNoeBimename(String noeBimename) {
        this.noeBimename = noeBimename;
    }

    public String getNoeGharardad() {
        return noeGharardad;
    }

    public void setNoeGharardad(String noeGharardad) {
        this.noeGharardad = noeGharardad;
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }

    public String getTarikhSodurBimename() {
        return tarikhSodurBimename;
    }

    public void setTarikhSodurBimename(String tarikhSodurBimename) {
        this.tarikhSodurBimename = tarikhSodurBimename;
    }

    public String getTarikhShoru() {
        return tarikhShoru;
    }

    public void setTarikhShoru(String tarikhShoru) {
        this.tarikhShoru = tarikhShoru;
    }

    public String getTarikhEngheza() {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza) {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getModatBimename() {
        return modatBimename;
    }

    public void setModatBimename(String modatBimename) {
        this.modatBimename = modatBimename;
    }

    public String getRaveshPardakhtBimename() {
        return raveshPardakhtBimename;
    }

    public void setRaveshPardakhtBimename(String raveshPardakhtBimename) {
        this.raveshPardakhtBimename = raveshPardakhtBimename;
    }

    public String getMablaghGhest() {
        return mablaghGhest;
    }

    public void setMablaghGhest(String mablaghGhest) {
        this.mablaghGhest = mablaghGhest;
    }

    public String getTarikhSarresidGhest() {
        return tarikhSarresidGhest;
    }

    public void setTarikhSarresidGhest(String tarikhSarresidGhest) {
        this.tarikhSarresidGhest = tarikhSarresidGhest;
    }

    public String getMablaghPardakhtiTavasotBimegozar() {
        return mablaghPardakhtiTavasotBimegozar;
    }

    public void setMablaghPardakhtiTavasotBimegozar(String mablaghPardakhtiTavasotBimegozar) {
        this.mablaghPardakhtiTavasotBimegozar = mablaghPardakhtiTavasotBimegozar;
    }

    public String getTarikhSodurGhabzResid() {
        return tarikhSodurGhabzResid;
    }

    public void setTarikhSodurGhabzResid(String tarikhSodurGhabzResid) {
        this.tarikhSodurGhabzResid = tarikhSodurGhabzResid;
    }

    public String getTarikhPardakhtGhest() {
        return tarikhPardakhtGhest;
    }

    public void setTarikhPardakhtGhest(String tarikhPardakhtGhest) {
        this.tarikhPardakhtGhest = tarikhPardakhtGhest;
    }

    public String getShomareGhabzResid() {
        return shomareGhabzResid;
    }

    public void setShomareGhabzResid(String shomareGhabzResid) {
        this.shomareGhabzResid = shomareGhabzResid;
    }

    public String getJameHaghBimeSadere() {
        return jameHaghBimeSadere;
    }

    public void setJameHaghBimeSadere(String jameHaghBimeSadere) {
        this.jameHaghBimeSadere = jameHaghBimeSadere;
    }

    public String getHaghBimePoosheshhayeAsli() {
        return haghBimePoosheshhayeAsli;
    }

    public void setHaghBimePoosheshhayeAsli(String haghBimePoosheshhayeAsli) {
        this.haghBimePoosheshhayeAsli = haghBimePoosheshhayeAsli;
    }

    public String getHaghBimePoosheshhayeEzafi() {
        return haghBimePoosheshhayeEzafi;
    }

    public void setHaghBimePoosheshhayeEzafi(String haghBimePoosheshhayeEzafi) {
        this.haghBimePoosheshhayeEzafi = haghBimePoosheshhayeEzafi;
    }

    public String getNamayandegi() {
        return namayandegi;
    }

    public void setNamayandegi(String namayandegi) {
        this.namayandegi = namayandegi;
    }

    public String getAzTarikhPardakht() {
        return azTarikhPardakht;
    }

    public void setAzTarikhPardakht(String azTarikhPardakht) {
        this.azTarikhPardakht = azTarikhPardakht;
    }

    public String getTaTarikhPardakht() {
        return taTarikhPardakht;
    }

    public void setTaTarikhPardakht(String taTarikhPardakht) {
        this.taTarikhPardakht = taTarikhPardakht;
    }

    public String getOstanId() {
        return ostanId;
    }

    public void setOstanId(String ostanId) {
        this.ostanId = ostanId;
    }

    public String getShahrId() {
        return shahrId;
    }

    public void setShahrId(String shahrId) {
        this.shahrId = shahrId;
    }

    public String getRadif() {
        return radif;
    }

    public void setRadif(String radif) {
        this.radif = radif;
    }

    public Long getVahedSodurId() {
        return vahedSodurId;
    }

    public void setVahedSodurId(Long vahedSodurId) {
        this.vahedSodurId = vahedSodurId;
    }

    public Long getNamayandegiId() {
        return namayandegiId;
    }

    public void setNamayandegiId(Long namayandegiId) {
        this.namayandegiId = namayandegiId;
    }

    public String getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public String getTarhName() {
        return tarhName;
    }

    public void setTarhName(String tarhName) {
        this.tarhName = tarhName;
    }

    public Integer getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public String getAzTarikhSanad()
    {
        return azTarikhSanad;
    }

    public void setAzTarikhSanad(String azTarikhSanad)
    {
        this.azTarikhSanad = azTarikhSanad;
    }

    public String getTaTarikhSanad()
    {
        return taTarikhSanad;
    }

    public void setTaTarikhSanad(String taTarikhSanad)
    {
        this.taTarikhSanad = taTarikhSanad;
    }

    public String getCodeMeliBimegozar() {
        return codeMeliBimegozar;
    }

    public void setCodeMeliBimegozar(String codeMeliBimegozar) {
        this.codeMeliBimegozar = codeMeliBimegozar;
    }

    public String getAzTarikhEngheza() {
        return azTarikhEngheza;
    }

    public void setAzTarikhEngheza(String azTarikhEngheza) {
        this.azTarikhEngheza = azTarikhEngheza;
    }

    public String getTaTarikhEngheza() {
        return taTarikhEngheza;
    }

    public void setTaTarikhEngheza(String taTarikhEngheza) {
        this.taTarikhEngheza = taTarikhEngheza;
    }

    public String getTaTarikhSodorElhaghiye() {
        return taTarikhSodorElhaghiye;
    }

    public void setTaTarikhSodorElhaghiye(String taTarikhSodorElhaghiye) {
        this.taTarikhSodorElhaghiye = taTarikhSodorElhaghiye;
    }

    public String getAzTarikhSodorElhaghiye() {
        return azTarikhSodorElhaghiye;
    }

    public void setAzTarikhSodorElhaghiye(String azTarikhSodorElhaghiye) {
        this.azTarikhSodorElhaghiye = azTarikhSodorElhaghiye;
    }
}

