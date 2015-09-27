package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 1/15/14
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class BatchTaghsitVM
{

//-----------------------------
    private String toTarikheTaghsit;
    private String fromTarikheTaghsit;
    private String fromTarikhShoroNewYear;
    private String toTarikhShoroNewYear;
    private Long creatorId;
    private String vahedSodurName;
    private Long vahedSodurId;
    private String noe_tarh;
    private String noe_gharardad;
    private Long gorohId;

//-----------------------------

    private String shomareBimename;
    private String kodeMelliBimeGozar;
    //noeBimename?
    private String bimeGozarFirstName;
    private String bimeGozarLastName;

    private String bimeShodeFirstName;
    private String bimeShodeLastName;
    private String bimenameTarikheShoro;
    private String bimenameTarikhEngheza;
    private String bimenameTarikhSodur;
    private String modat;
    private String noeGharardad;
    private String nahvePardakht;
    private String saleBimeei;
    private String aghsatPardakhtiCount;
    private String tarikhShoroNewYear;
    private Long haghBimeNewYear;
    private String namayandeName;
    private String namayandeCode;
    private Long namayandeId;
    private Integer id;
    private Integer bimenameId;
    private String hasPrint;
    private String karshenasFirstName;
    private String karshenasLastName;
    private Long karshenasId;
    private String tarikhTaghsit;
    private String creatorFirstName;
    private String creatorLastName;

    public BatchTaghsitVM()
    {
        toTarikheTaghsit=DateUtil.getCurrentDate();//"1392/10/26";
        fromTarikheTaghsit=DateUtil.getCurrentDate();//"1392/10/26";
        creatorId=0l;
    }

    public BatchTaghsitVM(String shomareBimename, String bimeGozarFirstName, String bimeGozarLastName, String bimeShodeFirstName, String bimeShodeLastName, String bimenameTarikheShoro, String bimenameTarikhEngheza, String bimenameTarikhSodur, String noeGharardad, String nahvePardakht, String saleBimeei, String aghsatPardakhtiCount, String tarikhShoroNewYear, Long haghBimeNewYear, String namayandeName, String namayandeCode, String modat,Integer ghestBandiId,Integer bimenameId, String hasPrint, String karshenasFirstName, String karshenasLastName,Long karshenasId, String tarikhTaghsit,String creatorFirstName ,String creatorLastName)
    {
        this.shomareBimename = shomareBimename;
        this.bimeGozarFirstName = bimeGozarFirstName;
        this.bimeGozarLastName = bimeGozarLastName;
        this.bimeShodeFirstName = bimeShodeFirstName;
        this.bimeShodeLastName = bimeShodeLastName;
        this.bimenameTarikheShoro = bimenameTarikheShoro;
        this.bimenameTarikhEngheza = bimenameTarikhEngheza;
        this.bimenameTarikhSodur = bimenameTarikhSodur;
        this.noeGharardad = noeGharardad;
        this.nahvePardakht = nahvePardakht;
        this.saleBimeei = saleBimeei!=null?Integer.toString(Integer.parseInt(saleBimeei) + 1):"0";
        this.aghsatPardakhtiCount = aghsatPardakhtiCount;
//        this.tarikhShoroNewYear = tarikhShoroNewYear;
        this.tarikhShoroNewYear = DateUtil.addYear(bimenameTarikheShoro,Integer.parseInt(saleBimeei!=null?saleBimeei:"0"));
        this.haghBimeNewYear = haghBimeNewYear;
        this.namayandeName = namayandeName;
        this.namayandeCode = namayandeCode;
        this.modat= modat;
        this.id = ghestBandiId;
        this.bimenameId = bimenameId;
        this.hasPrint=hasPrint;
        this.karshenasId=karshenasId;
        this.karshenasFirstName=karshenasFirstName;
        this.karshenasLastName=karshenasLastName;
        this.creatorFirstName=creatorFirstName==null?"خودکار":creatorFirstName;
        this.creatorLastName=creatorLastName;
        this.tarikhTaghsit=tarikhTaghsit;
    }

    public String getTarikhTaghsit()
    {
        return tarikhTaghsit;
    }

    public void setTarikhTaghsit(String tarikhTaghsit)
    {
        this.tarikhTaghsit = tarikhTaghsit;
    }

    public String getCreatorFirstName()
    {
        return creatorFirstName;
    }

    public void setCreatorFirstName(String creatorFirstName)
    {
        this.creatorFirstName = creatorFirstName;
    }

    public String getCreatorLastName()
    {
        return creatorLastName;
    }

    public void setCreatorLastName(String creatorLastName)
    {
        this.creatorLastName = creatorLastName;
    }

    public String getKarshenasFirstName()
    {
        return karshenasFirstName;
    }

    public void setKarshenasFirstName(String karshenasFirstName)
    {
        this.karshenasFirstName = karshenasFirstName;
    }

    public String getKarshenasLastName()
    {
        return karshenasLastName;
    }

    public void setKarshenasLastName(String karshenasLastName)
    {
        this.karshenasLastName = karshenasLastName;
    }

    public Long getKarshenasId()
    {
        return karshenasId;
    }

    public void setKarshenasId(Long karshenasId)
    {
        this.karshenasId = karshenasId;
    }

    public String getHasPrint()
    {
        return hasPrint;
    }

    public void setHasPrint(String hasPrint)
    {
        this.hasPrint = hasPrint;
    }

    public Long getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(Long creatorId)
    {
        creatorId = creatorId;
    }

    public String getToTarikheTaghsit()
    {
        return toTarikheTaghsit;
    }

    public void setToTarikheTaghsit(String toTarikheTaghsit)
    {
        this.toTarikheTaghsit = toTarikheTaghsit;
    }

    public String getFromTarikheTaghsit()
    {
        return fromTarikheTaghsit;
    }

    public void setFromTarikheTaghsit(String fromTarikheTaghsit)
    {
        this.fromTarikheTaghsit = fromTarikheTaghsit;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getBimeGozarFirstName()
    {
        return bimeGozarFirstName;
    }

    public void setBimeGozarFirstName(String bimeGozarFirstName)
    {
        this.bimeGozarFirstName = bimeGozarFirstName;
    }

    public String getBimeGozarLastName()
    {
        return bimeGozarLastName;
    }

    public void setBimeGozarLastName(String bimeGozarLastName)
    {
        this.bimeGozarLastName = bimeGozarLastName;
    }

    public String getBimeShodeFirstName()
    {
        return bimeShodeFirstName;
    }

    public void setBimeShodeFirstName(String bimeShodeFirstName)
    {
        this.bimeShodeFirstName = bimeShodeFirstName;
    }

    public String getBimeShodeLastName()
    {
        return bimeShodeLastName;
    }

    public void setBimeShodeLastName(String bimeShodeLastName)
    {
        this.bimeShodeLastName = bimeShodeLastName;
    }

    public String getBimenameTarikheShoro()
    {
        return bimenameTarikheShoro;
    }

    public void setBimenameTarikheShoro(String bimenameTarikheShoro)
    {
        this.bimenameTarikheShoro = bimenameTarikheShoro;
    }

    public String getBimenameTarikhEngheza()
    {
        return bimenameTarikhEngheza;
    }

    public void setBimenameTarikhEngheza(String bimenameTarikhEngheza)
    {
        this.bimenameTarikhEngheza = bimenameTarikhEngheza;
    }

    public String getBimenameTarikhSodur()
    {
        return bimenameTarikhSodur;
    }

    public void setBimenameTarikhSodur(String bimenameTarikhSodur)
    {
        this.bimenameTarikhSodur = bimenameTarikhSodur;
    }

    public String getNoeGharardad()
    {
        return noeGharardad;
    }

    public void setNoeGharardad(String noeGharardad)
    {
        this.noeGharardad = noeGharardad;
    }

    public String getNahvePardakht()
    {
        return nahvePardakht;
    }

    public void setNahvePardakht(String nahvePardakht)
    {
        this.nahvePardakht = nahvePardakht;
    }

    public String getSaleBimeei()
    {
        return saleBimeei;
    }

    public void setSaleBimeei(String saleBimeei)
    {
        this.saleBimeei = saleBimeei;
    }

    public String getAghsatPardakhtiCount()
    {
        return aghsatPardakhtiCount;
    }

    public void setAghsatPardakhtiCount(String aghsatPardakhtiCount)
    {
        this.aghsatPardakhtiCount = aghsatPardakhtiCount;
    }

    public String getTarikhShoroNewYear()
    {
        return tarikhShoroNewYear;
    }

    public void setTarikhShoroNewYear(String tarikhShoroNewYear)
    {
        this.tarikhShoroNewYear = tarikhShoroNewYear;
    }

    public Long getHaghBimeNewYear()
    {
        return haghBimeNewYear;
    }

    public void setHaghBimeNewYear(Long haghBimeNewYear)
    {
        this.haghBimeNewYear = haghBimeNewYear;
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

    public String getModat()
    {
        return modat;
    }

    public void setModat(String modat)
    {
        this.modat = modat;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNahvePardakhtFarsi()
    {
        if (getNahvePardakht()==null)
            return "";
        else if(getNahvePardakht().equals("sal"))
            return "سالانه";
        else if(getNahvePardakht().equals("mah"))
            return "ماهانه";
        else if(getNahvePardakht().equals("3mah"))
            return "سه ماهه";
        else if(getNahvePardakht().equals("6mah"))
            return "شش ماهه";
        else if(getNahvePardakht().equals("yekja"))
            return "یکجا";
        else return getNahvePardakht();
    }

    public Integer getBimenameId()
    {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId)
    {
        this.bimenameId = bimenameId;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getFromTarikhShoroNewYear()
    {
        return fromTarikhShoroNewYear;
    }

    public void setFromTarikhShoroNewYear(String fromTarikhShoroNewYear)
    {
        this.fromTarikhShoroNewYear = fromTarikhShoroNewYear;
    }

    public String getToTarikhShoroNewYear()
    {
        return toTarikhShoroNewYear;
    }

    public void setToTarikhShoroNewYear(String toTarikhShoroNewYear)
    {
        this.toTarikhShoroNewYear = toTarikhShoroNewYear;
    }

    public String getVahedSodurName()
    {
        return vahedSodurName;
    }

    public void setVahedSodurName(String vahedSodurName)
    {
        this.vahedSodurName = vahedSodurName;
    }

    public Long getVahedSodurId()
    {
        return vahedSodurId;
    }

    public void setVahedSodurId(Long vahedSodurId)
    {
        this.vahedSodurId = vahedSodurId;
    }

    public String getNoe_tarh()
    {
        return noe_tarh;
    }

    public void setNoe_tarh(String noe_tarh)
    {
        this.noe_tarh = noe_tarh;
    }

    public String getNoe_gharardad()
    {
        return noe_gharardad;
    }

    public void setNoe_gharardad(String noe_gharardad)
    {
        this.noe_gharardad = noe_gharardad;
    }

    public Long getGorohId()
    {
        return gorohId;
    }

    public void setGorohId(Long gorohId)
    {
        this.gorohId = gorohId;
    }

    public String getKodeMelliBimeGozar() {
        return kodeMelliBimeGozar;
    }

    public void setKodeMelliBimeGozar(String kodeMelliBimeGozar) {
        this.kodeMelliBimeGozar = kodeMelliBimeGozar;
    }
}
