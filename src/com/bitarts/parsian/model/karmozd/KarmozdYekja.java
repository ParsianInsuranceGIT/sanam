package com.bitarts.parsian.model.karmozd;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.bimename.Bimename;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/23/12
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_karmozdYekja")
public class KarmozdYekja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "karmozd_id")
    private Long id;
    @Column(name = "createdDate")
    private String createdDate;
    @ManyToOne
    private Namayande namayande;
    @OneToOne(fetch = FetchType.LAZY)
    private Bimename bimename;
    private String amountSal1 = "";
    private String amountSal2 = "";
    private String amountSal3 = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public String getAmountSal1() {
        if (amountSal1 == null) return "";
        return amountSal1;
    }

    public void setAmountSal1(String amountSal1) {
        this.amountSal1 = amountSal1;
    }

    public String getAmountSal2() {
        if (amountSal2 == null) return "";
        return amountSal2;
    }

    public void setAmountSal2(String amountSal2) {
        this.amountSal2 = amountSal2;
    }

    public String getAmountSal3() {
        if (amountSal3 == null) return "";
        return amountSal3;
    }

    public void setAmountSal3(String amountSal3) {
        this.amountSal3 = amountSal3;
    }
}
