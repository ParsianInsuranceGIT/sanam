package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;

import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 6/9/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class AllPayment
{
    private String type;
    private String karmozdReal;
    private String karmozdPaid;
    private String mablaghPardakhti;
    private String meghdarBedehi;
    private String mablaghPardakhtiAzBedehi;
    private String shomareMoshtari;
    private String saleBimei;
    private String sarresidDate;
    private String shomareBimename;
    private String sarmayePayeFot;
    private String nahvePardakhtHaghBime;
    private String kodeNamayande;
    private String nameNamayande;
    private String typeNamayande;
    private String typeKarmozd;
    private String tarefe;
    private String onvan;
    private String description;
    private String bimeGozarFirstName;
    private String bimeGozarLastName;

    public AllPayment()
    {

    }

    public AllPayment(String type, Long karmozdReal, Long karmozdPaid, Long mablaghPardakhti, Long meghdarBedehi, String mablaghPardakhtiAzBedehi, String shomareMoshtari, String saleBimei,
                      String sarresidDate, String shomareBimename, Long sarmayePayeFot, String nahvePardakhtHaghBime, String kodeNamayande, String nameNamayande, Namayande.NayamandeType typeNamayande, KarmozdGhest.Type typeKarmozd, KarmozdGhest.Tarefe tarefe, String onvan, String description, String bimeGozarFirstName, String bimeGozarLastName)
    {
        this.type = type;
        if(typeKarmozd.equals(KarmozdGhest.Type.POOSHESH_EZAFI) || typeKarmozd.equals(KarmozdGhest.Type.VOSULI))
            this.karmozdReal = NumberFormat.getInstance().format(karmozdReal == null ? "":karmozdReal);
        else
            this.karmozdReal = karmozdReal == null ? "" :NumberFormat.getInstance().format(KarmozdCalculate.percentPaymentOfKarmozdToNamayande(typeNamayande.toString(), karmozdReal, tarefe!=null?tarefe.toString(): KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO.toString()));
        this.karmozdPaid = karmozdPaid==null?"":NumberFormat.getInstance().format(karmozdPaid);
        this.mablaghPardakhti = mablaghPardakhti==null?"":NumberFormat.getInstance().format(mablaghPardakhti);
        this.meghdarBedehi = meghdarBedehi==null?"": NumberFormat.getInstance().format(meghdarBedehi);
        this.mablaghPardakhtiAzBedehi =mablaghPardakhtiAzBedehi==null?"": NumberFormat.getInstance().format(Long.parseLong(mablaghPardakhtiAzBedehi.replaceAll(",","").trim()));
        this.shomareMoshtari = shomareMoshtari;
        this.saleBimei = saleBimei==null?"":((Integer)(Integer.parseInt(saleBimei)+1)).toString();
        this.sarresidDate = sarresidDate;
        this.shomareBimename = shomareBimename;
        this.sarmayePayeFot =sarmayePayeFot==null?"": NumberFormat.getInstance().format(sarmayePayeFot);
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
        this.kodeNamayande = kodeNamayande;
        this.nameNamayande = nameNamayande;
        this.typeNamayande = typeNamayande.toString();
        this.typeKarmozd = typeKarmozd.toString();
        this.tarefe=tarefe==null?"":tarefe.toString();
        this.onvan = onvan;
        this.description = description==null?"":description;
        this.bimeGozarFirstName = bimeGozarFirstName;
        this.bimeGozarLastName = bimeGozarLastName;
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

    public String getTarefe()
    {
        return tarefe;
    }

    public void setTarefe(String tarefe)
    {
        this.tarefe = tarefe;
    }

    public String getType()
    {
        return type;
    }

    public String getTypeFa()
    {
        if (this.typeKarmozd == null)
            return "";
        else
        {
            if (this.typeKarmozd.equals(KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI.toString())) return "پرداخت کد موقت";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.CODE_MOVAGHAT.toString())) return "کد موقت";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.VOSULI.toString())) return "وصولی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.POOSHESH_EZAFI.toString())) return "پوشش های اضافی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.TAGHIRAT.toString())) return "الحاقیه تغییر";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.ADI.toString())) return "عادی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.DAR_ENTEZAR_SARRESID.toString())) return "در انتظار سر رسید";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.SENIOR.toString())) return "نماینده ارشد";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.KARMOZD_BARGASHTI.toString())) return "کارمزد برگشتی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.TADILI.toString())) return "تعدیلی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.TALIGHI.toString())) return "تعلیقی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.KARMOZD_TASHVIGHI_VOSULI.toString())) return "کارمزد تشویقی وصولی";
            if (this.typeKarmozd.equals(KarmozdGhest.Type.TAGHIR_CODE_DAEM.toString())) return "تغییر کد نمایندگی دائم به دائم";

            return "";
        }
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getKarmozdReal()
    {
        return karmozdReal;
    }

    public void setKarmozdReal(String karmozdReal)
    {
        this.karmozdReal = karmozdReal;
    }

    public String getKarmozdPaid()
    {
        return karmozdPaid;
    }

    public void setKarmozdPaid(String karmozdPaid)
    {
        this.karmozdPaid = karmozdPaid;
    }

    public String getMablaghPardakhti()
    {
        return mablaghPardakhti;
    }

    public void setMablaghPardakhti(String mablaghPardakhti)
    {
        this.mablaghPardakhti = mablaghPardakhti;
    }

    public String getMeghdarBedehi()
    {
        return meghdarBedehi;
    }

    public void setMeghdarBedehi(String meghdarBedehi)
    {
        this.meghdarBedehi = meghdarBedehi;
    }

    public String getMablaghPardakhtiAzBedehi()
    {
        return mablaghPardakhtiAzBedehi;
    }

    public void setMablaghPardakhtiAzBedehi(String mablaghPardakhtiAzBedehi)
    {
        this.mablaghPardakhtiAzBedehi = mablaghPardakhtiAzBedehi;
    }

    public String getShomareMoshtari()
    {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari)
    {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getSaleBimei()
    {
        return saleBimei;
    }

    public void setSaleBimei(String saleBimei)
    {
        this.saleBimei = saleBimei;
    }

    public String getSarresidDate()
    {
        return sarresidDate;
    }

    public void setSarresidDate(String sarresidDate)
    {
        this.sarresidDate = sarresidDate;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getSarmayePayeFot()
    {
        return sarmayePayeFot;
    }

    public void setSarmayePayeFot(String sarmayePayeFot)
    {
        this.sarmayePayeFot = sarmayePayeFot;
    }

    public String getNahvePardakhtHaghBime()
    {
        return nahvePardakhtHaghBime;
    }

    public void setNahvePardakhtHaghBime(String nahvePardakhtHaghBime)
    {
        this.nahvePardakhtHaghBime = nahvePardakhtHaghBime;
    }

    public String getKodeNamayande()
    {
        return kodeNamayande;
    }

    public void setKodeNamayande(String kodeNamayande)
    {
        this.kodeNamayande = kodeNamayande;
    }

    public String getNameNamayande()
    {
        return nameNamayande;
    }

    public void setNameNamayande(String nameNamayande)
    {
        this.nameNamayande = nameNamayande;
    }

    public String getTypeNamayande()
    {
        return typeNamayande;
    }

    public void setTypeNamayande(String typeNamayande)
    {
        this.typeNamayande = typeNamayande;
    }

    public String getTypeKarmozd()
    {
        return typeKarmozd;
    }

    public void setTypeKarmozd(String typeKarmozd)
    {
        this.typeKarmozd = typeKarmozd;
    }

    public String getOnvan()
    {
        return onvan;
    }

    public void setOnvan(String onvan)
    {
        this.onvan = onvan;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
