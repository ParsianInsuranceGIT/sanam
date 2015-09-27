package com.bitarts.parsian.viewModel;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 7/17/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PishnehadsVM
{
    private String roleColor;
    private boolean pishpardakhtOK;
    private Integer id;
    private String radif;
    private String namayandeName;
    private String sarparastName;
    private String karshenasFirstName;
    private String karshenasLastName;
    private String newDate;
    private String stateName;
    private String bimeShodeFirstName;
    private String bimeShodeLastName;
    private String gharardadType;
    private String ostan;
    private String sarmayePayeFot;
    private String createdDate;
    private String bimeGozarFirstName;
    private String bimeGozarLastName;


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

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getRoleColor()
    {
        return roleColor;
    }

    public void setRoleColor(String roleColor)
    {
        this.roleColor = roleColor;
    }

    public boolean isPishpardakhtOK()
    {
        return pishpardakhtOK;
    }

    public void setPishpardakhtOK(boolean pishpardakhtOK)
    {
        this.pishpardakhtOK = pishpardakhtOK;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRadif()
    {
        return radif;
    }

    public void setRadif(String radif)
    {
        this.radif = radif;
    }

    public String getNamayandeName()
    {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName)
    {
        this.namayandeName = namayandeName;
    }

    public String getSarparastName()
    {
        return sarparastName;
    }

    public void setSarparastName(String sarparastName)
    {
        this.sarparastName = sarparastName;
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

    public String getNewDate()
    {
        return newDate;
    }

    public void setNewDate(String newDate)
    {
        this.newDate = newDate;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
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

    public String getGharardadType()
    {
        return gharardadType;
    }

    public void setGharardadType(String gharardadType)
    {
        this.gharardadType = gharardadType;
    }

    public String getOstan()
    {
        return ostan;
    }

    public void setOstan(String ostan)
    {
        this.ostan = ostan;
    }

    public String getSarmayePayeFot()
    {
        return sarmayePayeFot;
    }

    public void setSarmayePayeFot(String sarmayePayeFot)
    {
        this.sarmayePayeFot = sarmayePayeFot;
    }

    public PishnehadsVM()
    {
    }

    public PishnehadsVM(String roleColor,  Integer pishnehadId, String radif, String namayandeName, String sarparastName,
                        String karshenasFirstName, String karshenasLastName, String newDate, String stateName, String bimeShodeFirstName,
                        String bimeShodeLastName, String gharardadType, String ostan, Long sarmayePayeFot)
    {
        // Darkhasthaye Man. . .
        this.roleColor = roleColor;
//        this.pishpardakhtOK = pishpardakhtOK;
        this.id = pishnehadId;
        this.radif = radif;
        this.namayandeName = namayandeName;
        this.sarparastName = sarparastName;
        this.karshenasFirstName = karshenasFirstName;
        this.karshenasLastName = karshenasLastName;
        this.newDate = newDate;
        this.stateName = stateName;
        this.bimeShodeFirstName = bimeShodeFirstName;
        this.bimeShodeLastName = bimeShodeLastName;
        this.gharardadType = gharardadType;
        this.ostan = ostan;
        this.sarmayePayeFot = sarmayePayeFot==null ? "" : NumberFormat.getInstance().format(sarmayePayeFot);
    }
    public PishnehadsVM(String roleColor,  Integer pishnehadId, String radif, String namayandeName, String sarparastName,
                            String karshenasFirstName, String karshenasLastName, String newDate, String stateName, String bimeShodeFirstName,
                            String bimeShodeLastName, String gharardadType, String ostan, Long sarmayePayeFot, String createdDate, String bimeGozarFirstName, String bimeGozarLastName)
    {
        // Hameye Darkhastha. . .
        this.roleColor = roleColor;
//        this.pishpardakhtOK = pishpardakhtOK;
        this.id = pishnehadId;
        this.radif = radif;
        this.namayandeName = namayandeName;
        this.sarparastName = sarparastName;
        this.karshenasFirstName = karshenasFirstName;
        this.karshenasLastName = karshenasLastName;
        this.newDate = newDate;
        this.stateName = stateName;
        this.bimeShodeFirstName = bimeShodeFirstName;
        this.bimeShodeLastName = bimeShodeLastName;
        this.gharardadType = gharardadType;
        this.ostan = ostan;
        this.sarmayePayeFot = sarmayePayeFot==null ? "" : NumberFormat.getInstance().format(sarmayePayeFot);
        this.createdDate= createdDate;
        this.bimeGozarFirstName=bimeGozarFirstName;
        this.bimeGozarLastName=bimeGozarLastName;
    }
}
