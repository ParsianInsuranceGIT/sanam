package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.bimename.DasteSerial;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 1/28/14
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class SerialsVM
{
    private Long firstShomareSerial;
    private Long lastShomareSerial;
    private String tarikheSabt;
    private String  bimenameType;
    private Long vahedeSodurId;
    private String vahedSodurCode;
    private String vahedSodurName;
    private Long namayandeId;
    private String namayandeCode;
    private String namayandeName;
    private String bazaryabName;
    private DasteSerial.VaziateDaste vaziateDaste;
    private Integer countSerials;
    private Integer countSerialsEbtali;
    private Integer mizaneJabejayi;
    private Integer id;
    private Integer kodeDaste;
    private Integer countSerialsEstefadeNashode;


    public SerialsVM()
    {

    }


    public SerialsVM(Long firstShomareSerial, Long lastShomareSerial, String tarikheSabt, String bimenameType, Long vahedeSodurId, String vahedSodurCode, String vahedSodurName, Long namayandeId, String namayandeCode, String namayandeName,
                     String bazaryabName, DasteSerial.VaziateDaste vaziateDaste, Long countSerials, Long countSerialsEbtali, Integer mizaneJabejayi, Integer id, Integer kodeDaste, Long countSerialsEstefadeNashode)
    {
        this.firstShomareSerial = firstShomareSerial;
        this.lastShomareSerial = lastShomareSerial;
        this.tarikheSabt = tarikheSabt;
        this.bimenameType = bimenameType;
        this.vahedeSodurId = vahedeSodurId;
        this.vahedSodurCode = vahedSodurCode;
        this.vahedSodurName = vahedSodurName;
        this.namayandeId = namayandeId;
        this.namayandeCode = namayandeCode;
        this.namayandeName = namayandeName;
        this.bazaryabName = bazaryabName;
        this.vaziateDaste = vaziateDaste;
        this.countSerials = countSerials.intValue();
        this.countSerialsEbtali = countSerialsEbtali.intValue();
        this.mizaneJabejayi = mizaneJabejayi;
        this.id = id;
        this.kodeDaste = kodeDaste;
        this.countSerialsEstefadeNashode = countSerialsEstefadeNashode.intValue();
    }

    public Long getFirstShomareSerial()
    {
        return firstShomareSerial;
    }

    public void setFirstShomareSerial(Long firstShomareSerial)
    {
        this.firstShomareSerial = firstShomareSerial;
    }

    public Long getLastShomareSerial()
    {
        return lastShomareSerial;
    }

    public void setLastShomareSerial(Long lastShomareSerial)
    {
        this.lastShomareSerial = lastShomareSerial;
    }

    public String getTarikheSabt()
    {
        return tarikheSabt;
    }

    public void setTarikheSabt(String tarikheSabt)
    {
        this.tarikheSabt = tarikheSabt;
    }

    public String getBimenameType()
    {
        return bimenameType;
    }

    public void setBimenameType(String bimenameType)
    {
        this.bimenameType = bimenameType;
    }

    public Long getVahedeSodurId()
    {
        return vahedeSodurId;
    }

    public void setVahedeSodurId(Long vahedeSodurId)
    {
        this.vahedeSodurId = vahedeSodurId;
    }

    public String getVahedSodurCode()
    {
        return vahedSodurCode;
    }

    public void setVahedSodurCode(String vahedSodurCode)
    {
        this.vahedSodurCode = vahedSodurCode;
    }

    public String getVahedSodurName()
    {
        return vahedSodurName;
    }

    public void setVahedSodurName(String vahedSodurName)
    {
        this.vahedSodurName = vahedSodurName;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getNamayandeCode()
    {
        return namayandeCode;
    }

    public void setNamayandeCode(String namayandeCode)
    {
        this.namayandeCode = namayandeCode;
    }

    public String getNamayandeName()
    {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName)
    {
        this.namayandeName = namayandeName;
    }

    public String getBazaryabName()
    {
        return bazaryabName;
    }

    public void setBazaryabName(String bazaryabName)
    {
        this.bazaryabName = bazaryabName;
    }

    public String getVaziateDasteFa()
    {
        if (vaziateDaste.equals(DasteSerial.VaziateDaste.GHEYRE_FAAL))
            return "غیرفعال";
        else if (vaziateDaste.equals(DasteSerial.VaziateDaste.BASTE_SHODE))
            return "بسته شده";
        else //if(vaziateDaste.equals(VaziateDaste.FAAL))
            return "فعال";
    }
    public DasteSerial.VaziateDaste getVaziateDaste()
    {
        return vaziateDaste;
    }

    public void setVaziateDaste(DasteSerial.VaziateDaste vaziateDaste)
    {
        this.vaziateDaste = vaziateDaste;
    }

    public Integer getCountSerials()
    {
        return countSerials;
    }

    public void setCountSerials(Integer countSerials)
    {
        this.countSerials = countSerials;
    }

    public Integer getCountSerialsEbtali()
    {
        return countSerialsEbtali;
    }

    public void setCountSerialsEbtali(Integer countSerialsEbtali)
    {
        this.countSerialsEbtali = countSerialsEbtali;
    }

    public Integer getMizaneJabejayi()
    {
        return mizaneJabejayi;
    }

    public void setMizaneJabejayi(Integer mizaneJabejayi)
    {
        this.mizaneJabejayi = mizaneJabejayi;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getKodeDaste()
    {
        return kodeDaste;
    }

    public void setKodeDaste(Integer kodeDaste)
    {
        this.kodeDaste = kodeDaste;
    }

    public Integer getCountSerialsEstefadeNashode()
    {
        return countSerialsEstefadeNashode;
    }

    public void setCountSerialsEstefadeNashode(Integer countSerialsEstefadeNashode)
    {
        this.countSerialsEstefadeNashode = countSerialsEstefadeNashode;
    }
}
