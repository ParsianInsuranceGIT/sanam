package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;

import java.text.NumberFormat;

public class BahreMandiVM
{
    private Integer id;
    private String shomareBimename;
    private String shomareVam;
    private String shomareBahreMandi;
    private String bimegozarFirstName;
    private String bimegozarLastName;
    private String namayandeName;
    private String namayandeCode;
    private String mablaghVam;
    private String mablaghBahreMandi;
    private Integer stateId;
    private String stateName;
    private String karshenasFirstName;
    private String karshenasLastName;
    private Long karshenasId;
    private String mablagheVamFrom;
    private String mablagheVamTo;
    private String mablagheBahreMandiFrom;
    private String mablagheBahreMandiTo;
    private User user;
    private DarkhastBazkharid.DarkhastType darkhastType;

    public BahreMandiVM()
    {
    }

    public BahreMandiVM(Integer id, String shomareBimename, String shomareVam,String shomareBardashtAzAndukhte,String bimegozarFirstName, String bimegozarLastName, String namayandeName, String namayandeCode, Long mablaghVam,String mablaghDarkhastiBardasht, DarkhastBazkharid.DarkhastType darkhastType)
    {
        this.id = id;
        this.darkhastType=darkhastType;
        this.shomareBimename = shomareBimename;
        this.bimegozarFirstName = bimegozarFirstName;
        this.bimegozarLastName = bimegozarLastName;
        this.namayandeName = namayandeName;
        this.namayandeCode = namayandeCode;
        this.darkhastType = darkhastType;

        if(darkhastType.equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE))
        {
            this.mablaghBahreMandi= mablaghDarkhastiBardasht;
            this.shomareBahreMandi= shomareBardashtAzAndukhte;
        }

        if(darkhastType.equals(DarkhastBazkharid.DarkhastType.VAM))
        {
            this.mablaghBahreMandi= NumberFormat.getInstance().format(mablaghVam);
            this.shomareBahreMandi= shomareVam;
        }
    }

    public String getMablaghBahreMandi()
    {
        return mablaghBahreMandi;
    }

    public void setMablaghBahreMandi(String mablaghBahreMandi)
    {
        this.mablaghBahreMandi = mablaghBahreMandi;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getMablagheVamFrom()
    {
        return mablagheVamFrom;
    }

    public void setMablagheVamFrom(String mablagheVamFrom)
    {
        this.mablagheVamFrom = mablagheVamFrom;
    }

    public String getMablagheVamTo()
    {
        return mablagheVamTo;
    }

    public void setMablagheVamTo(String mablagheVamTo)
    {
        this.mablagheVamTo = mablagheVamTo;
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

    public Integer getStateId()
    {
        return stateId;
    }

    public void setStateId(Integer stateId)
    {
        this.stateId = stateId;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    public String getBimegozarFirstName()
    {
        return bimegozarFirstName;
    }

    public void setBimegozarFirstName(String bimegozarFirstName)
    {
        this.bimegozarFirstName = bimegozarFirstName;
    }

    public String getBimegozarLastName()
    {
        return bimegozarLastName;
    }

    public void setBimegozarLastName(String bimegozarLastName)
    {
        this.bimegozarLastName = bimegozarLastName;
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

    public String getMablaghVam()
    {
        return mablaghVam;
    }

    public void setMablaghVam(String mablaghVam)
    {
        this.mablaghVam = mablaghVam;
    }

    public String getShomareBahreMandi()
    {
        return shomareBahreMandi;
    }

    public void setShomareBahreMandi(String shomareBahreMandi)
    {
        this.shomareBahreMandi = shomareBahreMandi;
    }

    public String getMablagheBahreMandiFrom()
    {
        return mablagheBahreMandiFrom;
    }

    public void setMablagheBahreMandiFrom(String mablagheBahreMandiFrom)
    {
        this.mablagheBahreMandiFrom = mablagheBahreMandiFrom;
    }

    public String getMablagheBahreMandiTo()
    {
        return mablagheBahreMandiTo;
    }

    public void setMablagheBahreMandiTo(String mablagheBahreMandiTo)
    {
        this.mablagheBahreMandiTo = mablagheBahreMandiTo;
    }

    public DarkhastBazkharid.DarkhastType getDarkhastType()
    {
        return darkhastType;
    }

    public void setDarkhastType(DarkhastBazkharid.DarkhastType darkhastType)
    {
        this.darkhastType = darkhastType;
    }

    public String getDarkhstTypeFarsi()
    {
        switch (darkhastType)
        {
            case VAM:
                return "دریافت وام";
            case BARDASHT_AZ_ANDOKHTE:
                return "برداشت از اندوخته";
//            case BAZKHARID:
//                return "بازخرید بیمه نامه";
//            case TASVIE_PISH_AZ_MOEDE_VAM:
//                return "تسویه پیش از موعد وام";
//            case EBTAL:
//                return "ابطال بیمه نامه";
//            case VARIZ:
//                return "واریز به اندوخته";
//            case TAGHIRKOD:
//                return "تغییر کد نمایندگی";
//            case TOZIH:
//                return "توضيح";
//            case KHESARAT:
//                return "خسارت";
            default:
                return "";
        }
    }
}
