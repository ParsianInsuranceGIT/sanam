package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.calculations.Reports.IRs;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/20/12
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZakhireRiazi {
    private Pishnehad pishnehad;
    private String jamSadere, jamPardakhti, zakhire, bazkharid;
    private List<IRs> irs;
    private int taSal;
    private String haghBimeAzKaarOftaadegi;
    private String haghBimeFotDarAsarHaadese;
    private String haghBimeAmraazKhaas;
    private String haghBimePusheshHaayeEzaafi;
    private String haghBimeKhaalesFotYekja, tarikhMabna;
    private String haghBimePardaakhti, azSarmayeFoat, taSarmayeFoat;
    private String ostan, namayande, kodNamayande, noeGharardad;
    private String shahr, vahedSodur, kodVahedSodur, azTarikh, taTarikh;
    private Integer ostanId;
    private Long vahedSodurId, namayandeId;
    private Integer shahrId;
    private String searchBar;
    private Tarh tarh;
    private Ghest ghest;
    private String maliat;
    private String hazineTaghsit;
    private String shomareBimename;
    private String vaziat;

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public List<IRs> getIrs() {
        return irs;
    }

    public void setIrs(List<IRs> irs) {
        this.irs = irs;
    }

    public String getJamSadere() {
        return jamSadere;
    }

    public void setJamSadere(String jamSadere) {
        this.jamSadere = jamSadere;
    }

    public String getHaghBimeAzKaarOftaadegi() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimeAzKaarOftaadegi());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimeAzKaarOftaadegi(String haghBimeAzKaarOftaadegi) {
        this.haghBimeAzKaarOftaadegi = haghBimeAzKaarOftaadegi;
    }

    public String getHaghBimeFotDarAsarHaadese() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimeFotDarAsarHaadese());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimeFotDarAsarHaadese(String haghBimeFotDarAsarHaadese) {
        this.haghBimeFotDarAsarHaadese = haghBimeFotDarAsarHaadese;
    }

    public String getHaghBimeAmraazKhaas() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimeAmraazKhaas());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimeAmraazKhaas(String haghBimeAmraazKhaas) {
        this.haghBimeAmraazKhaas = haghBimeAmraazKhaas;
    }

    public String getHaghBimePusheshHaayeEzaafi() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimePusheshHaayeEzaafi());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimePusheshHaayeEzaafi(String haghBimePusheshHaayeEzaafi) {
        this.haghBimePusheshHaayeEzaafi = haghBimePusheshHaayeEzaafi;
    }

    public String getHaghBimeKhaalesFotYekja() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimeKhaalesFotYekja());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimeKhaalesFotYekja(String haghBimeKhaalesFotYekja) {
        this.haghBimeKhaalesFotYekja = haghBimeKhaalesFotYekja;
    }

    public String getHaghBimePardaakhti() {
        Long tmp = 0L;
        for (IRs i : irs) {
            if (i.getSaal() < taSal)
                tmp += Math.round(i.getHaghBimePardaakhti());
        }
        return NumberFormat.getNumberInstance().format(tmp);
    }

    public void setHaghBimePardaakhti(String haghBimePardaakhti) {
        this.haghBimePardaakhti = haghBimePardaakhti;
    }

    public String getJamPardakhti() {
        return jamPardakhti;
    }

    public void setJamPardakhti(String jamPardakhti) {
        this.jamPardakhti = jamPardakhti;
    }

    public String getZakhire() {
        return zakhire;
    }

    public void setZakhire(String zakhire) {
        this.zakhire = zakhire;
    }

    public String getBazkharid() {
        return bazkharid;
    }

    public void setBazkharid(String bazkharid) {
        this.bazkharid = bazkharid;
    }

    public String getTarikhMabna() {
        return tarikhMabna;
    }

    public void setTarikhMabna(String tarikhMabna) {
        this.tarikhMabna = tarikhMabna;
    }

    public String getAzSarmayeFoat() {
        return azSarmayeFoat;
    }

    public void setAzSarmayeFoat(String azSarmayeFoat) {
        this.azSarmayeFoat = azSarmayeFoat;
    }

    public String getTaSarmayeFoat() {
        return taSarmayeFoat;
    }

    public void setTaSarmayeFoat(String taSarmayeFoat) {
        this.taSarmayeFoat = taSarmayeFoat;
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

    public Long getVahedSodurId() {
        return vahedSodurId;
    }

    public void setVahedSodurId(Long vahedSodurId) {
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

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public String getSearchBar() {
        return searchBar;
    }

    public Ghest getGhest() {
        return ghest;
    }

    public void setGhest(Ghest ghest) {
        this.ghest = ghest;
    }

    public String getMaliat() {
        return maliat;
    }

    public void setMaliat(String maliat) {
        this.maliat = maliat;
    }

    public String getHazineTaghsit() {
        return hazineTaghsit;
    }

    public void setHazineTaghsit(String hazineTaghsit) {
        this.hazineTaghsit = hazineTaghsit;
    }

    public String getShomareBimename() {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename) {
        this.shomareBimename = shomareBimename;
    }

    public String getVaziat() {
        return vaziat;
    }

    public void setVaziat(String vaziat) {
        this.vaziat = vaziat;
    }
}
