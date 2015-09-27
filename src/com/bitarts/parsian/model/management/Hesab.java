package com.bitarts.parsian.model.management;

import com.bitarts.parsian.model.AzmayeshType;
import com.bitarts.parsian.model.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_hesab")
public class Hesab implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hesab_id")
    private Integer id;
    @Column(name = "hesab_shomare")
    private String shomareHesab;
    @Column(name = "credit")
    private String mowjoudi;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShomareHesab() {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab) {
        this.shomareHesab = shomareHesab;
    }

    public String getMowjoudi() {
        return mowjoudi;
    }

    public void setMowjoudi(String mowjoudi) {
        this.mowjoudi = mowjoudi;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
