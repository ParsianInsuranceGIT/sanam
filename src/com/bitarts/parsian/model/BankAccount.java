package com.bitarts.parsian.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 12/1/14
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_bank_account")
public class BankAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_account")
    private String bankName;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_Number")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedSodor_id")
    private Namayande vahedSodor;


    public Namayande getVahedSodor()
    {
        return vahedSodor;
    }

    public void setVahedSodor(Namayande vahedSodor)
    {
        this.vahedSodor = vahedSodor;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getBankAccount()
    {
        return getBankName() + getAccountName() + " - " + "(" + getAccountNumber() + ") "  ;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }
}
