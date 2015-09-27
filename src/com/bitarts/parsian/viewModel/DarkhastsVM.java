package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Darkhast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/20/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DarkhastsVM
{
//------------search fields
    private String darkhastTypeStr;
    private User user;
    private String bimeGozarCodeMelli;
    private String bimeShodeCodeMelli;
    private Long namayandeId;
    private String azTarikheDarkhast;
    private String taTarikheDarkhast;
    private List<String> darkhastStates;

    private Integer id;
    private String roleColor;
    private String darkhastDate;
    private String shomareBimename;
    private String namayandeName;
    private String namayandeCode;
    private Darkhast.DarkhastType darkhastType;
    private String bimeGozarFirstName;
    private String bimeGozrLastName;
    private String bimeShodeFirstName;
    private String bimeShodeLastName;
    private String karshenasFirstName;
    private String karshenasLastName;
    private String karshenasKhesaratFirstName;
    private String karshenasKhesaratLastName;
    private String creatorFirstName;
    private String creatorLastName;
    private String transitionLogDate;
    private String stateName;
    private Integer TaghirOrBazkharidId;

    public DarkhastsVM()
    {

    }

    public DarkhastsVM(Integer id,String taghirRoleColor,String bazkharidRoleColor, String taghirDarkhastDate,String bazkharidDarkhastDate, String taghirShomareBimename,String bazkharidShomareBimename,
                       String taghirNamayandeName, String bazkharidNamayandeName, String taghirNamayandeCode, String bazkharidNamayandeCode, Darkhast.DarkhastType darkhastType, String taghirBimeGozarFirstName,
                       String bazkharidBimeGozarFirstName,String taghirBimeGozrLastName,String bazkharidBimeGozrLastName, String taghirBimeShodeFirstName,String bazkharidBimeShodeFirstName,
                       String taghirBimeShodeLastName,String bazkharidBimeShodeLastName, String taghirKarshenasFirstName, String bazkharidKarshenasFirstName, String taghirKarshenasLastName,
                       String bazkharidKarshenasLastName, String taghirCreatorFirstName,String bazkharidCreatorFirstName, String taghirCreatorLastName, String bazkharidCreatorLastName,
                       String taghirTransitionLogDate,String bazkharidTransitionLogDate, String taghirStateName,String bazkharidStateName, Integer taghirId, Integer bazkharidId,String karshenasKhesaratFirstName,String karshenasKhesaratLastName)
    {
        if(darkhastType.equals(Darkhast.DarkhastType.TAGHYIRAT))
        {
            this.roleColor = taghirRoleColor;
            this.darkhastDate = taghirDarkhastDate;
            this.shomareBimename = taghirShomareBimename;
            this.namayandeName = taghirNamayandeName;
            this.namayandeCode = taghirNamayandeCode;
            this.darkhastType = darkhastType;
            this.bimeGozarFirstName = taghirBimeGozarFirstName;
            this.bimeGozrLastName = taghirBimeGozrLastName;
            this.bimeShodeFirstName = taghirBimeShodeFirstName;
            this.bimeShodeLastName = taghirBimeShodeLastName;
            this.karshenasFirstName = taghirKarshenasFirstName;
            this.karshenasLastName = taghirKarshenasLastName;
            this.creatorFirstName = taghirCreatorFirstName;
            this.creatorLastName = taghirCreatorLastName;
            this.transitionLogDate = taghirTransitionLogDate;
            this.stateName = taghirStateName;
            this.TaghirOrBazkharidId = taghirId;
        }

        else
        {
            this.roleColor = bazkharidRoleColor;
            this.darkhastDate = bazkharidDarkhastDate;
            this.shomareBimename = bazkharidShomareBimename;
            this.namayandeName = bazkharidNamayandeName;
            this.namayandeCode = bazkharidNamayandeCode;
            this.darkhastType = darkhastType;
            this.bimeGozarFirstName = bazkharidBimeGozarFirstName;
            this.bimeGozrLastName = bazkharidBimeGozrLastName;
            this.bimeShodeFirstName =bazkharidBimeShodeFirstName;
            this.bimeShodeLastName =bazkharidBimeShodeLastName;
            this.karshenasFirstName = bazkharidKarshenasFirstName;
            this.karshenasLastName= bazkharidKarshenasLastName;
            this.creatorFirstName =bazkharidCreatorFirstName;
            this.creatorLastName =bazkharidCreatorLastName;
            this.transitionLogDate= bazkharidTransitionLogDate;
            this.stateName = bazkharidStateName;
            this.TaghirOrBazkharidId = bazkharidId;
            this.karshenasKhesaratFirstName=karshenasKhesaratFirstName;
            this.karshenasKhesaratLastName=karshenasKhesaratLastName;
        }
        this.id=id;
    }

    public String getBimeShodeCodeMelli()
    {
        return bimeShodeCodeMelli;
    }

    public void setBimeShodeCodeMelli(String bimeShodeCodeMelli)
    {
        this.bimeShodeCodeMelli = bimeShodeCodeMelli;
    }

    public String getBimeGozarCodeMelli()
    {
        return bimeGozarCodeMelli;
    }

    public void setBimeGozarCodeMelli(String bimeGozarCodeMelli)
    {
        this.bimeGozarCodeMelli = bimeGozarCodeMelli;
    }

    public User getUser()
    {
        return user;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getTaTarikheDarkhast()
    {
        return taTarikheDarkhast;
    }

    public void setTaTarikheDarkhast(String taTarikheDarkhast)
    {
        this.taTarikheDarkhast = taTarikheDarkhast;
    }

    public String getDarkhastTypeStr()

    {
        return darkhastTypeStr;
    }

    public void setDarkhastTypeStr(String darkhastTypeStr)
    {
        this.darkhastTypeStr = darkhastTypeStr;
    }

    public String getKarshenasKhesaratFirstName()
    {
        return karshenasKhesaratFirstName;
    }

    public void setKarshenasKhesaratFirstName(String karshenasKhesaratFirstName)
    {
        this.karshenasKhesaratFirstName = karshenasKhesaratFirstName;
    }

    public String getKarshenasKhesaratLastName()
    {
        return karshenasKhesaratLastName;
    }

    public void setKarshenasKhesaratLastName(String karshenasKhesaratLastName)
    {
        this.karshenasKhesaratLastName = karshenasKhesaratLastName;
    }

    public String getNamayeshLinkTabsAll()
    {
        if (darkhastType.equals(Darkhast.DarkhastType.TAGHYIRAT))
        {
            return "  /editDarkhastTaghirFormReadOnly?darkhastTaghirat.id=" + getTaghirOrBazkharidId();
        }
        else
        {
            return "/editDarkhastFormReadOnly?darkhastBazkharid.id=" + getTaghirOrBazkharidId();
        }
    }

    public String getNamayeshLink()
    {
        if(darkhastType.equals(Darkhast.DarkhastType.TAGHYIRAT))
        {
           return "  /editDarkhastTaghirForm?darkhastTaghirat.id="+getTaghirOrBazkharidId();
        }
        else
        {
           return "/editDarkhastForm?darkhastBazkharid.id="+getTaghirOrBazkharidId();
        }
    }

    public String getDarkhastTypeFarsi()
    {
        return Darkhast.getDarkhastTypeFarsi(this.darkhastType);
    }

    public String getTarikhcheLink()
    {
        if(darkhastType.equals(Darkhast.DarkhastType.TAGHYIRAT))
           return "/showDarkhastTaghirTransitionLog?darkhastTaghir.id="+getTaghirOrBazkharidId();
        else
           return "/showDarkhastBazkharidTransitionLog?darkhastBazkharid.id="+getTaghirOrBazkharidId();
    }

    public String getRoleColor()
    {
        return roleColor;
    }

    public void setRoleColor(String roleColor)
    {
        this.roleColor = roleColor;
    }

    public String getDarkhastDate()
    {
        return darkhastDate;
    }

    public void setDarkhastDate(String darkhastDate)
    {
        this.darkhastDate = darkhastDate;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
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

    public void setDarkhastType(Darkhast.DarkhastType darkhastType)
    {
        this.darkhastType = darkhastType;
    }

    public String getBimeGozarFirstName()
    {
        return bimeGozarFirstName;
    }

    public void setBimeGozarFirstName(String bimeGozarFirstName)
    {
        this.bimeGozarFirstName = bimeGozarFirstName;
    }

    public String getBimeGozrLastName()
    {
        return bimeGozrLastName;
    }

    public void setBimeGozrLastName(String bimeGozrLastName)
    {
        this.bimeGozrLastName = bimeGozrLastName;
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

    public String getTransitionLogDate()
    {
        return transitionLogDate;
    }

    public void setTransitionLogDate(String transitionLogDate)
    {
        this.transitionLogDate = transitionLogDate;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public Integer getTaghirOrBazkharidId()
    {
        return TaghirOrBazkharidId;
    }

    public void setTaghirOrBazkharidId(Integer taghirOrBazkharidId)
    {
        TaghirOrBazkharidId = taghirOrBazkharidId;
    }

    public String getAzTarikheDarkhast()
    {
        return azTarikheDarkhast;
    }

    public void setAzTarikheDarkhast(String azTarikheDarkhast)
    {
        this.azTarikheDarkhast = azTarikheDarkhast;
    }

    public List<String> getDarkhastStates()
    {
        return darkhastStates;
    }

    public void setDarkhastStates(List<String> darkhastStates)
    {
        this.darkhastStates = darkhastStates;
    }
}

