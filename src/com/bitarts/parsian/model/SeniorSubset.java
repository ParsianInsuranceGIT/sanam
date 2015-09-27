package com.bitarts.parsian.model;

import com.bitarts.common.util.DateUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 12/18/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_senior_subset")
public class SeniorSubset
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "contract_date_from")
    private String contractDateFrom;

    @Column(name = "contract_date_to")
    private String contractDateTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senior_id")
    private Namayande senior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subset_id")
    private Namayande subset;

    @Column(name = "is_expired")
    private Boolean expired;

    public SeniorSubset()
    {
    }

    public SeniorSubset(String contractDateFrom, String contractDateTo, Namayande senior, Namayande subset, Boolean expired)
    {
        this.contractDateFrom = contractDateFrom;
        this.contractDateTo = contractDateTo;
        this.senior = senior;
        this.subset = subset;
        this.expired = expired;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getContractDateFrom()
    {
        return contractDateFrom;
    }

    public void setContractDateFrom(String contractDateFrom)
    {
        this.contractDateFrom = contractDateFrom;
    }

    public String getContractDateTo()
    {
        return contractDateTo;
    }

    public void setContractDateTo(String contractDateTo)
    {
        this.contractDateTo = contractDateTo;
    }

    public Namayande getSenior()
    {
        return senior;
    }

    public void setSenior(Namayande senior)
    {
        this.senior = senior;
    }

    public Namayande getSubset()
    {
        return subset;
    }

    public void setSubset(Namayande subset)
    {
        this.subset = subset;
    }

    public Boolean getExpired()
    {
        return expired;
    }

    public void setExpired(Boolean expired)
    {
        this.expired = expired;
    }

    public boolean getCheckExpired()
    {
        if(DateUtil.isGreaterThan(DateUtil.getCurrentDate(),this.getContractDateTo()))
            return true;
        return false;

    }
    public boolean getCheckExpired(String date)
    {
        if(DateUtil.isGreaterThan(date,this.getContractDateTo()))
            return true;
        return false;
    }
}

