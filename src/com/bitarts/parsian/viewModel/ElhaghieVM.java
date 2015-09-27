package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.bimename.Elhaghiye;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 6/14/14
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElhaghieVM
{
    private String vahedSodurName;
    private String vahedSodurCode;
    private Long vahedSodurId;

    private String namayandeName;
    private String namayandeCode;
    private Long namayandeId;

    private String shomareElhaghie;
    private String radifElhaghie;
    private Elhaghiye.ElhaghiyeType elhaghiyeType;
    private String elhaghiyeTypeStr;

    private String shomareBimename;
    private String bimenameGroup;
    private Long bimenameGroupId;

    private String bimeGozarName;
    private String bimeShodeName;

    private String modatBimename;
    private String sarmayeFot;
    private String sarmayeFotDarHadese;
    private String sarmayeAmrazKhas;
    private String sarmayeNaghsOzv;
    private String poosheshMoafiatAzKarOftadegi;

    private String nahvePardakhtHaghBime;

    private String tarikhShorouBimename;
    private String tarikhEnghezaBimename;
    private String tarikhSodourBimename;
    private String tarikhSodourElhaghie;
    private String tarikhAsarElhaghie;

    private String haghBimeElhaghie;
    private String elhaghieSubject;
    private String bimeShodeCodeMeliOrShenasaei;
    private String bimeShodeBirthDate;

    private String bimeShodeFirstName;
    private String bimeShodeLastName;

    private String bimeGozarCodeMeliOrShenasaei;
    private String bimeGozarFirstName;
    private String bimeGozarLastName;
    private String noeGharardad;
    private Integer tarhId;

    private String nerkhTadilSarmaye;
    private String nerkhTadilHaghBime;

    private String fromTarikhSodurElhaghie;
    private String toTarikhSodurElhaghie;

    private Long userSaderKonandeId;
    private String userSaderKonandeName;
    private String userSaderKonandeCode;
    private Khesarat.KhesaratType khesaratType;

    public Khesarat.KhesaratType getKhesaratType()
    {
        return khesaratType;
    }

    public void setKhesaratType(Khesarat.KhesaratType khesaratType)
    {
        this.khesaratType = khesaratType;
    }

    public String getNoeKhas()
    {
        if (elhaghiyeType == null) return "";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.BARDASHT_AZ_ANDOKHTE)) return "برداشت از اندوخته";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.BAZKHARID)) return "بازخرید";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT)) return "تغییرات";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TASVIE_PISH_AZ_MOEDE_VAM)) return "تسویه پیش از موعد وام";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TAGHIRKOD)) return "تغییر کد نمایندگی";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TOZIH)) return "الحاقیه توضیح";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.VAM)) return "وام";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.EBTAL)) return "ابطال";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.KHESARAT))
        {
            return getTypeKhesaratFarsi();
        }
        return "-";
    }

    public String getTypeKhesaratFarsi()
    {
        if (khesaratType.equals(Khesarat.KhesaratType.AMRAZ)) return "امراض خاص";
        else if (khesaratType.equals(Khesarat.KhesaratType.HADESE)) return "حادثه";
        else if (khesaratType.equals(Khesarat.KhesaratType.MOAFIAT)) return "معافیت و از کار افتادگی";
        else if (khesaratType.equals(Khesarat.KhesaratType.NAGHSOZV)) return "نقص عضو";
        else if (khesaratType.equals(Khesarat.KhesaratType.OMR)) return "عمر";
        else return "-";
    }

    public String getUserSaderKonandeCode()
    {
        return userSaderKonandeCode;
    }

    public void setUserSaderKonandeCode(String userSaderKonandeCode)
    {
        this.userSaderKonandeCode = userSaderKonandeCode;
    }

    public Long getUserSaderKonandeId()
    {
        return userSaderKonandeId;
    }

    public void setUserSaderKonandeId(Long userSaderKonandeId)
    {
        this.userSaderKonandeId = userSaderKonandeId;
    }

    public String getUserSaderKonandeName()
    {
        return userSaderKonandeName;
    }

    public String getTarikhAsarElhaghie()
    {
        return tarikhAsarElhaghie;
    }

    public void setTarikhAsarElhaghie(String tarikhAsarElhaghie)
    {
        this.tarikhAsarElhaghie = tarikhAsarElhaghie;
    }

    public void setUserSaderKonandeName(String userSaderKonandeName)
    {
        userSaderKonandeName = userSaderKonandeName;
    }

    public String getElhaghiyeTypeStr()
    {
        return elhaghiyeTypeStr;
    }

    public void setElhaghiyeTypeStr(String elhaghiyeTypeStr)
    {
        this.elhaghiyeTypeStr = elhaghiyeTypeStr;
    }

    public String getNoeGharardad()
    {
        return noeGharardad;
    }

    public void setNoeGharardad(String noeGharardad)
    {
        this.noeGharardad = noeGharardad;
    }

    public Integer getTarhId()
    {
        return tarhId;
    }

    public void setTarhId(Integer tarhId)
    {
        this.tarhId = tarhId;
    }

    public Long getBimenameGroupId()
    {
        return bimenameGroupId;
    }

    public void setBimenameGroupId(Long bimenameGroupId)
    {
        this.bimenameGroupId = bimenameGroupId;
    }

    public String getFromTarikhSodurElhaghie()
    {
        return fromTarikhSodurElhaghie;
    }

    public void setFromTarikhSodurElhaghie(String fromTarikhSodurElhaghie)
    {
        this.fromTarikhSodurElhaghie = fromTarikhSodurElhaghie;
    }

    public String getToTarikhSodurElhaghie()
    {
        return toTarikhSodurElhaghie;
    }

    public void setToTarikhSodurElhaghie(String toTarikhSodurElhaghie)
    {
        this.toTarikhSodurElhaghie = toTarikhSodurElhaghie;
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

    public String getBimeGozarCodeMeliOrShenasaei()
    {
        return bimeGozarCodeMeliOrShenasaei;
    }

    public void setBimeGozarCodeMeliOrShenasaei(String bimeGozarCodeMeliOrShenasaei)
    {
        this.bimeGozarCodeMeliOrShenasaei = bimeGozarCodeMeliOrShenasaei;
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

    public String getVahedSodurName()
    {
        return vahedSodurName;
    }

    public void setVahedSodurName(String vahedSodurName)
    {
        this.vahedSodurName = vahedSodurName;
    }

    public String getVahedSodurCode()
    {
        return vahedSodurCode;
    }

    public void setVahedSodurCode(String vahedSodurCode)
    {
        this.vahedSodurCode = vahedSodurCode;
    }

    public Long getVahedSodurId()
    {
        return vahedSodurId;
    }

    public void setVahedSodurId(Long vahedSodurId)
    {
        this.vahedSodurId = vahedSodurId;
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

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getShomareElhaghie()
    {
        return shomareElhaghie;
    }

    public void setShomareElhaghie(String shomareElhaghie)
    {
        this.shomareElhaghie = shomareElhaghie;
    }

    public String getRadifElhaghie()
    {
        return radifElhaghie;
    }

    public void setRadifElhaghie(String radifElhaghie)
    {
        this.radifElhaghie = radifElhaghie;
    }

    public Elhaghiye.ElhaghiyeType getElhaghiyeType()
    {
        return elhaghiyeType;
    }

    public void setElhaghiyeType(Elhaghiye.ElhaghiyeType elhaghiyeType)
    {
        this.elhaghiyeType = elhaghiyeType;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getBimenameGroup()
    {
        return bimenameGroup;
    }

    public void setBimenameGroup(String bimenameGroup)
    {
        this.bimenameGroup = bimenameGroup;
    }

    public String getBimeGozarName()
    {
        return bimeGozarName;
    }

    public void setBimeGozarName(String bimeGozarName)
    {
        this.bimeGozarName = bimeGozarName;
    }

    public String getBimeShodeName()
    {
        return bimeShodeName;
    }

    public void setBimeShodeName(String bimeShodeName)
    {
        this.bimeShodeName = bimeShodeName;
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

    public String getSarmayeFotDarHadese()
    {
        return sarmayeFotDarHadese;
    }

    public void setSarmayeFotDarHadese(String sarmayeFotDarHadese)
    {
        this.sarmayeFotDarHadese = sarmayeFotDarHadese;
    }

    public String getSarmayeAmrazKhas()
    {
        return sarmayeAmrazKhas;
    }

    public void setSarmayeAmrazKhas(String sarmayeAmrazKhas)
    {
        this.sarmayeAmrazKhas = sarmayeAmrazKhas;
    }

    public String getSarmayeNaghsOzv()
    {
        return sarmayeNaghsOzv;
    }

    public void setSarmayeNaghsOzv(String sarmayeNaghsOzv)
    {
        this.sarmayeNaghsOzv = sarmayeNaghsOzv;
    }

    public String getPoosheshMoafiatAzKarOftadegi()
    {
        return poosheshMoafiatAzKarOftadegi;
    }

    public void setPoosheshMoafiatAzKarOftadegi(String poosheshMoafiatAzKarOftadegi)
    {
        poosheshMoafiatAzKarOftadegi = poosheshMoafiatAzKarOftadegi;
    }

    public String getNahvePardakhtHaghBime()
    {
        return nahvePardakhtHaghBime;
    }

    public void setNahvePardakhtHaghBime(String nahvePardakhtHaghBime)
    {
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
    }

    public String getTarikhShorouBimename()
    {
        return tarikhShorouBimename;
    }

    public void setTarikhShorouBimename(String tarikhShorouBimename)
    {
        this.tarikhShorouBimename = tarikhShorouBimename;
    }

    public String getTarikhEnghezaBimename()
    {
        return tarikhEnghezaBimename;
    }

    public void setTarikhEnghezaBimename(String tarikhEnghezaBimename)
    {
        this.tarikhEnghezaBimename = tarikhEnghezaBimename;
    }

    public String getTarikhSodourBimename()
    {
        return tarikhSodourBimename;
    }

    public void setTarikhSodourBimename(String tarikhSodourBimename)
    {
        this.tarikhSodourBimename = tarikhSodourBimename;
    }

    public String getTarikhSodourElhaghie()
    {
        return tarikhSodourElhaghie;
    }

    public void setTarikhSodourElhaghie(String tarikhSodourElhaghie)
    {
        this.tarikhSodourElhaghie = tarikhSodourElhaghie;
    }

    public String getBimeShodeCodeMeliOrShenasaei()
    {
        return bimeShodeCodeMeliOrShenasaei;
    }

    public void setBimeShodeCodeMeliOrShenasaei(String bimeShodeCodeMeliOrShenasaei)
    {
        this.bimeShodeCodeMeliOrShenasaei = bimeShodeCodeMeliOrShenasaei;
    }

    public String getBimeShodeBirthDate()
    {
        return bimeShodeBirthDate;
    }

    public void setBimeShodeBirthDate(String bimeShodeBirthDate)
    {
        this.bimeShodeBirthDate = bimeShodeBirthDate;
    }

    public String getHaghBimeElhaghie()
    {
        return haghBimeElhaghie;
    }

    public void setHaghBimeElhaghie(String haghBimeElhaghie)
    {
        this.haghBimeElhaghie = haghBimeElhaghie;
    }

    public String getElhaghieSubject()
    {
        return elhaghieSubject;
    }

    public void setElhaghieSubject(String elhaghieSubject)
    {
        this.elhaghieSubject = elhaghieSubject;
    }

    public String getNerkhTadilSarmaye()
    {
        return nerkhTadilSarmaye;
    }

    public void setNerkhTadilSarmaye(String nerkhTadilSarmaye)
    {
        this.nerkhTadilSarmaye = nerkhTadilSarmaye;
    }

    public String getNerkhTadilHaghBime()
    {
        return nerkhTadilHaghBime;
    }

    public void setNerkhTadilHaghBime(String nerkhTadilHaghBime)
    {
        this.nerkhTadilHaghBime = nerkhTadilHaghBime;
    }

    public ElhaghieVM()
    {

    }

    public ElhaghieVM(String vahedSodurNameMojtama, String vahedSodurNameNamayandegi,  String vahedSodurCodeMojtama,String vahedSodurCodeNamayandegi, Long vahedSodurIdMojtama, Long vahedSodurIdNamayandegi,
                      String namayandeNameBz, String namayandeNameTgr, String namayandeCodeBz,String namayandeCodeTgr, Long namayandeIdBz, Long namayandeIdTgr,
                      String shomareElhaghie, String radifElhaghie, Elhaghiye.ElhaghiyeType elhaghiyeType, String shomareBimename, String bimenameGroup,
                      String bimeGozarName, String bimeShodeName, String modatBimename, Long sarmayeFot, String sarmayeFotDarHadese, String sarmayeAmrazKhas,
                      String sarmayeNaghsOzv, String poosheshMoafiatAzKarOftadegi, String nahvePardakhtHaghBime, String tarikhShorouBimename, String tarikhEnghezaBimename,
                      String tarikhSodourBimename, String bimeShodeCodeMeliOrShenasaei, String bimeShodeBirthDate, String elhaghieSubject, String haghBimeElhaghie,
                      String tarikhSodurElhaghie,String nerkhTadilHaghBime,String nerkhTadilSarmaye,String tarikhAsarElhaghie,String userSaderKonandeName,String userSaderKonandeCode,String options, Khesarat.KhesaratType khesaratType)
    {
        if(elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT))
        {
            this.namayandeName = namayandeNameTgr;
            this.namayandeCode = namayandeCodeTgr;
            this.namayandeId = namayandeIdTgr;
        }
        else//if(elhaghiyeType== Elhaghiye.ElhaghiyeType.BAZKHARID || elhaghiyeType == Elhaghiye.ElhaghiyeType.EBTAL || elhaghiyeType == Elhaghiye.ElhaghiyeType.TAGHIRKOD || elhaghiyeType == Elhaghiye.ElhaghiyeType.TOZIH)
        {
            this.namayandeName = namayandeNameBz;
            this.namayandeCode = namayandeCodeBz;
            this.namayandeId = namayandeIdBz;
        }
        if (options!=null && options.toUpperCase().equals("CODE_MOVAGHAT"))
        {
            this.namayandeName = "نماینده موقت";
            this.namayandeCode = "111120";
            this.namayandeId = null;
        }

        this.khesaratType=khesaratType;
        this.vahedSodurName = vahedSodurNameNamayandegi!=null?vahedSodurNameNamayandegi:vahedSodurNameMojtama;
        this.vahedSodurCode = vahedSodurCodeNamayandegi != null ? vahedSodurCodeNamayandegi : vahedSodurCodeMojtama;
        this.vahedSodurId = vahedSodurIdNamayandegi != null ? vahedSodurIdNamayandegi : vahedSodurIdMojtama;
        this.shomareElhaghie = shomareElhaghie;
        this.radifElhaghie = radifElhaghie;
        this.elhaghiyeType = elhaghiyeType;
        this.shomareBimename = shomareBimename;
        this.bimenameGroup = bimenameGroup;
        this.bimeGozarName = bimeGozarName;
        this.bimeShodeName = bimeShodeName;
        this.modatBimename = modatBimename;
        this.sarmayeFot = sarmayeFot==null?"0": NumberFormat.getInstance().format(sarmayeFot);
        this.sarmayeFotDarHadese = sarmayeFotDarHadese;
        this.sarmayeAmrazKhas = sarmayeAmrazKhas;
        this.sarmayeNaghsOzv = sarmayeNaghsOzv;
        this.poosheshMoafiatAzKarOftadegi = poosheshMoafiatAzKarOftadegi;
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
        this.tarikhShorouBimename = tarikhShorouBimename;
        this.tarikhEnghezaBimename = tarikhEnghezaBimename;
        this.tarikhSodourBimename = tarikhSodourBimename;
        this.bimeShodeCodeMeliOrShenasaei = bimeShodeCodeMeliOrShenasaei;
        this.bimeShodeBirthDate= bimeShodeBirthDate;//dar hale hazer estelam.sen_bime_shode ra bar migardanad
        this.elhaghieSubject = elhaghieSubject;
        this.haghBimeElhaghie = haghBimeElhaghie;
        this.tarikhSodourElhaghie=tarikhSodurElhaghie;
        this.nerkhTadilSarmaye= nerkhTadilSarmaye;
        this.nerkhTadilHaghBime= nerkhTadilHaghBime;
        this.tarikhAsarElhaghie = tarikhAsarElhaghie;
        this.userSaderKonandeName=userSaderKonandeName;
        this.userSaderKonandeCode= userSaderKonandeCode;
    }

    public String getBimeShodeAge()
    {
//        if (bimeShodeBirthDate == null) return "0";
//        return String.valueOf((int) Math.ceil(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), bimeShodeBirthDate) / 365));
        return bimeShodeBirthDate;
    }
    
    public String getNahvePardakhtHaghBimeFa()
    {
        String s = "";
        if (nahvePardakhtHaghBime!= null)
        {
            if (nahvePardakhtHaghBime.equals("mah")) s = "ماهانه";
            else if (nahvePardakhtHaghBime.equals("3mah")) s = "سه ماهه";
            else if (nahvePardakhtHaghBime.equals("6mah")) s = "شش ماهه";
            else if (nahvePardakhtHaghBime.equals("sal")) s = "سالانه";
            else if (nahvePardakhtHaghBime.equals("yekja")) s = "یکجا";
        }
        return s;    
    }

    public String getPoosheshMoafiatAzKarOftadegiFa()
    {
        if (poosheshMoafiatAzKarOftadegi == null || poosheshMoafiatAzKarOftadegi.equals("no"))
            return "ندارد";
        else
            return "دارد";
    }
    public String getElhaghiyeTypeFa()
    {
        if(elhaghiyeType==null)return "";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.BARDASHT_AZ_ANDOKHTE)) return "برداشت از اندوخته";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.BAZKHARID)) return "بازخرید";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT)) return "تغییرات";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TASVIE_PISH_AZ_MOEDE_VAM)) return "تسویه پیش از موعد وام";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TAGHIRKOD)) return "تغییر کد نمایندگی";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.TOZIH)) return "الحاقیه توضیح";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.VAM)) return "وام";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.EBTAL)) return "ابطال";
        if (elhaghiyeType.equals(Elhaghiye.ElhaghiyeType.KHESARAT)) return "خسارت";
        return "";
    }
}
