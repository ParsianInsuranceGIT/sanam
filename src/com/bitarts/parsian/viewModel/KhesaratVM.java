package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 1/9/14
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class KhesaratVM
{
    private String shomareParvande;
    private String createdDate;
    private String stateName;
    private String stateId;
    private String userCreatorFirstName;
    private String userCreatorLastName;
    private Long userCreatorId;
    private String bimeShodeFirstName;
    private String bimeShodeLastName;
    private String bimeGozarFirstName;
    private String bimeGozarLastName;
    private String bimeGozarCodeMeli;
    private String bimeShodeCodeMeli;
    private String shomareBimename;
    private Long khesaratId;
    private User userCreator;
    private Integer id;

    public KhesaratVM()
    {

    }

    public KhesaratVM(Integer id, String shomareParvande, String createdDate, String stateName, String userCreatorFirstName, String userCreatorLastName, String bimeShodeFirstName, String bimeShodeLastName, String bimeGozarFirstName, String bimeGozarLastName, String shomareBimename, Long khesaratId,String bimeShodeCodeMeli,String bimeGozarCodeMeli)
    {
        this.shomareParvande = shomareParvande;
        this.createdDate = createdDate;
        this.stateName = stateName;
        this.userCreatorFirstName = userCreatorFirstName;
        this.userCreatorLastName = userCreatorLastName;
        this.bimeShodeFirstName = bimeShodeFirstName;
        this.bimeShodeLastName = bimeShodeLastName;
        this.bimeGozarFirstName = bimeGozarFirstName;
        this.bimeGozarLastName = bimeGozarLastName;
        this.shomareBimename = shomareBimename;
        this.khesaratId = khesaratId;
        this.bimeGozarCodeMeli=bimeGozarCodeMeli;
        this.bimeShodeCodeMeli=bimeShodeCodeMeli;
        this.id=id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getUserCreator()
    {
        return userCreator;
    }

    public void setUserCreator(User userCreator)
    {
        this.userCreator = userCreator;
    }

    public Long getUserCreatorId()
    {
        return userCreatorId;
    }

    public void setUserCreatorId(Long userCreatorId)
    {
        this.userCreatorId = userCreatorId;
    }

    public String getBimeGozarCodeMeli()
    {
        return bimeGozarCodeMeli;
    }

    public void setBimeGozarCodeMeli(String bimeGozarCodeMeli)
    {
        this.bimeGozarCodeMeli = bimeGozarCodeMeli;
    }

    public String getBimeShodeCodeMeli()
    {
        return bimeShodeCodeMeli;
    }

    public void setBimeShodeCodeMeli(String bimeShodeCodeMeli)
    {
        this.bimeShodeCodeMeli = bimeShodeCodeMeli;
    }

    public String getShomareParvande()
    {
        return shomareParvande;
    }

    public void setShomareParvande(String shomareParvande)
    {
        this.shomareParvande = shomareParvande;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public String getUserCreatorFirstName()
    {
        return userCreatorFirstName;
    }

    public void setUserCreatorFirstName(String userCreatorFirstName)
    {
        this.userCreatorFirstName = userCreatorFirstName;
    }

    public String getUserCreatorLastName()
    {
        return userCreatorLastName;
    }

    public void setUserCreatorLastName(String userCreatorLastName)
    {
        this.userCreatorLastName = userCreatorLastName;
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

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public Long getKhesaratId()
    {
        return khesaratId;
    }

    public void setKhesaratId(Long khesaratId)
    {
        this.khesaratId = khesaratId;
    }

    public String getStateId()
    {
        return stateId;
    }

    public void setStateId(String stateId)
    {
        this.stateId = stateId;
    }
}
