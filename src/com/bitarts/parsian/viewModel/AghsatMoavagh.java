package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.pishnahad.Pishnehad;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/20/12
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AghsatMoavagh {
    private Pishnehad pishnehad;
    private String jamSadere = "", hagheBimeElamBeMaliSaleAvval = "";
    private Ghest ghest;

    private int taSal;

    private String tarikhMabna = "", mablaghGhest = "", tarikhSarresidGhest = "", mandeGhest = "", azTarikhSarresid, taTarikhSarresid;
    private String ostan = "", namayande = "", kodNamayande = "", noeGharardad = "";
    private String shahr = "", vahedSodur = "", kodVahedSodur = "", azTarikh = "", taTarikh = "", searchBar = "";
    private String azTarikhePardakht = "", taTarikhePardakht = "";
    private String tarikhePardakht;
    private Integer ostanId, vahedSodurId;
    private Long namayandeId;
    private Integer shahrId;
    private Tarh tarh;
    private String sherkateGharardad;
    private String bazaryab;
    private String noeTarh;
    private String seneBimeshode;
    private String nameBimeshode;
    private String nameBimegozar;
    private String nahvePardakhtHaghBime;
    private String modatBimename;
    private String tarikhEngheza;
    private String tarikhShoro;
    private String tarikhSodour;
    private String shomareBimename;
    private String tarikhSanadeGhest;
    private Integer credebitId;
    private String poshtibanName;
    private String poshtibanCode;
    private String options;
    private String shomareMoshtari;
    private String hamrahBimeGozar;

    public AghsatMoavagh()
    {

    }

    public AghsatMoavagh(String tarikhSanadeGhest, Long mandeGhest, String tarikhSarresidGhest, Long mablaghGhest, String shomareBimename,String tarikhSodour,String nameBimegozar, String tarikhShoro,
                         String tarikhEngheza,String  namayande ,String kodNamayande,String modatBimename, String nahvePardakhtHaghBime,String nameBimeshode,String tarikhPardakht, String sherkatGharardad,
                         String ostan,String city,String vahedSodur, String kodVahedSodur,String bazaryab, String noeGharardad, String noeTarh,String seneBimeshode,String poshtibanName, String poshtibanCode,
                         String options, String shomareMoshtari, String hamrahBimeGozar)
    {
        this.tarikhSanadeGhest=tarikhSanadeGhest;
        this.mandeGhest= mandeGhest!=null?NumberFormat.getInstance().format(mandeGhest):"0";
        this.tarikhSarresidGhest=tarikhSarresidGhest;
        this.mablaghGhest=mablaghGhest!=null?NumberFormat.getInstance().format(mablaghGhest):"0";
        this.shomareBimename=shomareBimename;
        this.tarikhSodour=tarikhSodour;
        this.nameBimegozar=nameBimegozar;
        this.tarikhShoro=tarikhShoro;
        this.tarikhEngheza=tarikhEngheza;
        this.namayande=namayande;
        this.kodNamayande=kodNamayande;
        this.modatBimename=modatBimename;
        this.nahvePardakhtHaghBime=nahvePardakhtHaghBime;
        this.nameBimeshode=nameBimeshode;
        this.tarikhePardakht=tarikhPardakht;
        this.sherkateGharardad=sherkatGharardad;
        this.ostan=ostan;
        this.shahr=city;
        this.vahedSodur=vahedSodur;
        this.kodVahedSodur=kodVahedSodur;
        this.bazaryab=bazaryab;
        this.noeGharardad=noeGharardad;
        this.noeTarh=noeTarh;
        this.seneBimeshode=seneBimeshode;
        this.poshtibanName=poshtibanName;
        this.poshtibanCode=poshtibanCode;
        this.options= options;
        this.shomareMoshtari= shomareMoshtari;
        this.hamrahBimeGozar = hamrahBimeGozar;
    }

    public String getShomareMoshtari()
    {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari)
    {
        this.shomareMoshtari = shomareMoshtari;
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

    public String getNahvePardakhtHaghBimeFarsi() {
        String s = "";
        if (nahvePardakhtHaghBime != null) {
            if (nahvePardakhtHaghBime.equals("mah")) s = "ماهانه";
            else if (nahvePardakhtHaghBime.equals("3mah")) s = "سه ماهه";
            else if (nahvePardakhtHaghBime.equals("6mah")) s = "شش ماهه";
            else if (nahvePardakhtHaghBime.equals("sal")) s = "سالانه";
            else if (nahvePardakhtHaghBime.equals("yekja")) s = "یکجا";
        }
        return s;
    }

    public String getNameBimegozar()
    {
        return nameBimegozar;
    }

    public void setNameBimegozar(String nameBimegozar)
    {
        this.nameBimegozar = nameBimegozar;
    }

    public String getSherkateGharardad()
    {
        return sherkateGharardad;
    }

    public void setSherkateGharardad(String sherkateGharardad)
    {
        this.sherkateGharardad = sherkateGharardad;
    }

    public String getBazaryab()
    {
        return bazaryab;
    }

    public void setBazaryab(String bazaryab)
    {
        this.bazaryab = bazaryab;
    }

    public String getNoeTarh()
    {
        return noeTarh;
    }

    public void setNoeTarh(String noeTarh)
    {
        this.noeTarh = noeTarh;
    }

    public String getSeneBimeshode()
    {
        return seneBimeshode;
    }

    public void setSeneBimeshode(String seneBimeshode)
    {
        this.seneBimeshode = seneBimeshode;
    }

    public String getNameBimeshode()
    {
        return nameBimeshode;
    }

    public void setNameBimeshode(String nameBimeshode)
    {
        this.nameBimeshode = nameBimeshode;
    }

    public String getNahvePardakhtHaghBime()
    {
        return nahvePardakhtHaghBime;
    }

    public void setNahvePardakhtHaghBime(String nahvePardakhtHaghBime)
    {
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
    }

    public String getModatBimename()
    {
        return modatBimename;
    }

    public void setModatBimename(String modatBimename)
    {
        this.modatBimename = modatBimename;
    }

    public String getTarikhEngheza()
    {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza)
    {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getTarikhShoro()
    {
        return tarikhShoro;
    }

    public void setTarikhShoro(String tarikhShoro)
    {
        this.tarikhShoro = tarikhShoro;
    }

    public String getTarikhSodour()
    {
        return tarikhSodour;
    }

    public void setTarikhSodour(String tarikhSodour)
    {
        this.tarikhSodour = tarikhSodour;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getTarikhSanadeGhest()
    {
        return tarikhSanadeGhest;
    }

    public void setTarikhSanadeGhest(String tarikhSanadeGhest)
    {
        this.tarikhSanadeGhest = tarikhSanadeGhest;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }


    public String getJamSadere() {
        return jamSadere;
    }

    public void setJamSadere(String jamSadere) {
        this.jamSadere = jamSadere;
    }


    public String getMablaghGhest() {
        return mablaghGhest;
    }

    public void setMablaghGhest(String mablaghGhest) {
        this.mablaghGhest = mablaghGhest;
    }


    public String getTarikhMabna() {
        return tarikhMabna;
    }

    public void setTarikhMabna(String tarikhMabna) {
        this.tarikhMabna = tarikhMabna;
    }


    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getNamayande() {
        return namayande;
    }

    public void setNamayande(String namayande) {
        this.namayande = namayande;
    }

    public String getKodNamayande() {
        return kodNamayande;
    }

    public void setKodNamayande(String kodNamayande) {
        this.kodNamayande = kodNamayande;
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

    public String getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(String azTarikh) {
        this.azTarikh = azTarikh;
    }

    public String getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(String taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Integer getOstanId() {
        return ostanId;
    }

    public void setOstanId(Integer ostanId) {
        this.ostanId = ostanId;
    }

    public Integer getVahedSodurId() {
        return vahedSodurId;
    }

    public void setVahedSodurId(Integer vahedSodurId) {
        this.vahedSodurId = vahedSodurId;
    }

    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public Integer getShahrId() {
        return shahrId;
    }

    public void setShahrId(Integer shahrId) {
        this.shahrId = shahrId;
    }

    public int getTaSal() {
        return taSal;
    }

    public void setTaSal(int taSal) {
        this.taSal = taSal;
    }

    public String getTarikhSarresidGhest() {
        return tarikhSarresidGhest;
    }

    public void setTarikhSarresidGhest(String tarikhSarresidGhest) {
        this.tarikhSarresidGhest = tarikhSarresidGhest;
    }

    public String getMandeGhest() {
        return mandeGhest;
    }

    public void setMandeGhest(String mandeGhest) {
        this.mandeGhest = mandeGhest;
    }

    public String getAzTarikhSarresid() {
        return azTarikhSarresid;
    }

    public void setAzTarikhSarresid(String azTarikhSarresid) {
        this.azTarikhSarresid = azTarikhSarresid;
    }

    public String getTaTarikhSarresid() {
        return taTarikhSarresid;
    }

    public void setTaTarikhSarresid(String taTarikhSarresid) {
        this.taTarikhSarresid = taTarikhSarresid;
    }

    public String getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public String getAzTarikhePardakht() {
        return azTarikhePardakht;
    }

    public void setAzTarikhePardakht(String azTarikhePardakht) {
        this.azTarikhePardakht = azTarikhePardakht;
    }

    public String getTaTarikhePardakht() {
        return taTarikhePardakht;
    }

    public void setTaTarikhePardakht(String taTarikhePardakht) {
        this.taTarikhePardakht = taTarikhePardakht;
    }

    public String getTarikhePardakht() {
        return tarikhePardakht;
    }

    public void setTarikhePardakht(String tarikhePardakht) {
        this.tarikhePardakht = tarikhePardakht;
    }

    public Ghest getGhest() {
        return ghest;
    }

    public void setGhest(Ghest ghest) {
        this.ghest = ghest;
    }

    public String getHagheBimeElamBeMaliSaleAvval() {
        return hagheBimeElamBeMaliSaleAvval;
    }

    public void setHagheBimeElamBeMaliSaleAvval(String hagheBimeElamBeMaliSaleAvval) {
        this.hagheBimeElamBeMaliSaleAvval = hagheBimeElamBeMaliSaleAvval;
    }

    public String getHamrahBimeGozar() {
        return hamrahBimeGozar;
    }

    public void setHamrahBimeGozar(String hamrahBimeGozar) {
        this.hamrahBimeGozar = hamrahBimeGozar;
    }
}
