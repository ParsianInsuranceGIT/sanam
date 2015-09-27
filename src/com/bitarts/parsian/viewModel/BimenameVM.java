package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.bimename.Darkhast;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/20/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class BimenameVM
{
    private Integer pishnehadId;
    private String shomareBimename;
    private String tarikhSodour;
    private String tarikhEngheza;
    private String stateName;
    private String bimeGozarFirstName;
    private String bimeGozarLastName;
    private String bimeShodeFirstName;
    private String bimeShodeLastName;
    private String namayandeName;
    private String namayandeCode;
    private String vahedSodurName;
    private String karshenasFirstName;
    private String karshenasLastName;
    private String karshenasPersonalCode;
    private Integer stateId;
    private Integer bimenameId;
    private String hasElhaghie;
    private Darkhast.DarkhastType darkhastDarJaryanType;

    public String getHasElhaghie()
    {
        return hasElhaghie;
    }

    public void setHasElhaghie(String hasElhaghie)
    {
        this.hasElhaghie = hasElhaghie;
    }

    public Darkhast.DarkhastType getDarkhastDarJaryanType()
    {
        return darkhastDarJaryanType;
    }

    public void setDarkhastDarJaryanType(Darkhast.DarkhastType darkhastDarJaryanType)
    {
        this.darkhastDarJaryanType = darkhastDarJaryanType;
    }

    public String getKarshenasLastName()
    {
        return karshenasLastName;
    }

    public void setKarshenasLastName(String karshenasLastName)
    {
        this.karshenasLastName = karshenasLastName;
    }

    public Integer getPishnehadId()
    {
        return pishnehadId;
    }

    public void setPishnehadId(Integer pishnehadId)
    {
        this.pishnehadId = pishnehadId;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getTarikhSodour()
    {
        return tarikhSodour;
    }

    public void setTarikhSodour(String tarikhSodour)
    {
        this.tarikhSodour = tarikhSodour;
    }

    public String getTarikhEngheza()
    {
        return tarikhEngheza;
    }

    public void setTarikhEngheza(String tarikhEngheza)
    {
        this.tarikhEngheza = tarikhEngheza;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
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

    public String getVahedSodurName()
    {
        return vahedSodurName;
    }

    public void setVahedSodurName(String vahedSodurName)
    {
        this.vahedSodurName = vahedSodurName;
    }

    public String getKarshenasFirstName()
    {
        return karshenasFirstName;
    }

    public void setKarshenasFirstName(String karshenasFirstName)
    {
        this.karshenasFirstName = karshenasFirstName;
    }

    public String getKarshenasPersonalCode()
    {
        return karshenasPersonalCode;
    }

    public void setKarshenasPersonalCode(String karshenasPersonalCode)
    {
        this.karshenasPersonalCode = karshenasPersonalCode;
    }

    public Integer getStateId()
    {
        return stateId;
    }

    public void setStateId(Integer stateId)
    {
        this.stateId = stateId;
    }

    public Integer getBimenameId()
    {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId)
    {
        this.bimenameId = bimenameId;
    }

    public String getVaziatField()
    {
        if(this.getStateId().equals(Constant.BIMENAME_LOCK_STATE))
        {
            String str= this.stateName ;
            if(this.darkhastDarJaryanType != null)
                str+= " ( " +  Darkhast.getTypeFarsi(this.darkhastDarJaryanType)+" ) ";
            else
                str+=" (اصلاح اشخاص) ";
            return str;
        }
        else if (this.getStateId().equals(Constant.BIMENAME_INITIAL_STATE))
        {
            if(this.getHasElhaghie()!=null && this.getHasElhaghie().equals("yes"))
            {
                return this.stateName + " (دارای الحاقیه) ";
            }
            else
            {
                return this.stateName;
            }
        }
        else
        {
            return this.stateName;
        }
    }

    public BimenameVM(Integer pishnehadId, String shomareBimename, String tarikhSodour, String tarikhEngheza, String stateName, String bimeGozarFirstName, String bimeGozarLastName, String bimeShodeFirstName, String bimeShodeLastName, String namayandeName, String namayandeCode, String vahedSodurName, String karshenasFirstName, String karshenasLastName, String karshenasPersonalCode, Integer stateId, Integer bimenameId, String hasElhaghie, Darkhast.DarkhastType darkhastDarJaryanType)
    {
        this.pishnehadId = pishnehadId;
        this.shomareBimename = shomareBimename;
        this.tarikhSodour = tarikhSodour;
        this.tarikhEngheza = tarikhEngheza;
        this.stateName = stateName;
        this.bimeGozarFirstName = bimeGozarFirstName;
        this.bimeGozarLastName = bimeGozarLastName;
        this.bimeShodeFirstName = bimeShodeFirstName;
        this.bimeShodeLastName = bimeShodeLastName;
        this.namayandeName = namayandeName;
        this.namayandeCode = namayandeCode;
        this.vahedSodurName = vahedSodurName;
        this.karshenasFirstName = karshenasFirstName;
        this.karshenasLastName =  karshenasLastName;
        this.karshenasPersonalCode = karshenasPersonalCode;
        this.stateId = stateId;
        this.bimenameId = bimenameId;
        this.darkhastDarJaryanType=darkhastDarJaryanType;
        this.hasElhaghie=hasElhaghie;
    }
}
