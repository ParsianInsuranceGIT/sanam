package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.Daftar;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;

import javax.persistence.*;

/**
 * User: a.sabzechian
 * Date: 1/8/14
 * Time: 5:17 PM
 */
@Entity
@Table(name = "tbl_vagozari")
public class Vagozari {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "shomare")
    private String shomare;
    @Column(name = "create_date")
    private String createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;

    //b-h
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="daftar_id")
    private Daftar  daftar;

    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String getNamayandeName(){
        if (namayande != null){
            return namayande.getName();
        }
        return "";
    }

    public Integer getVagozariPerCredebit(){
        return InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getVagozariPerCredebit(getId());
    }
}
