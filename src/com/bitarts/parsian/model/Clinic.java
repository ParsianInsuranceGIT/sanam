package com.bitarts.parsian.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name = "tbl_clinic")
public class Clinic implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clinic_id")
    private Integer id;
    @Column(name = "clinic_name")
    private String clinicName;
    @Column(name = "shahr_name")
    private String cityName;
    @Column(name = "clinic_address")
    private String address;
    @Column(name = "tarikh_shorou_gharardad")
    private String tarikhShorouGharardad;
    @Column(name = "tarikh_etmam_gharardad")
    private String tarikhEtmamGharardad;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clinics")
    @BatchSize(size = 30)
    private List<Azmayesh> azmayeshs;

    public List<Azmayesh> getAzmayeshs() {
        Collections.sort(azmayeshs);
        return azmayeshs;
    }

    public void setAzmayeshs(List<Azmayesh> azmayeshs) {
        this.azmayeshs = azmayeshs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTarikhShorouGharardad() {
        return tarikhShorouGharardad;
    }

    public void setTarikhShorouGharardad(String tarikhShorouGharardad) {
        this.tarikhShorouGharardad = tarikhShorouGharardad;
    }

    public String getTarikhEtmamGharardad() {
        return tarikhEtmamGharardad;
    }

    public void setTarikhEtmamGharardad(String tarikhEtmamGharardad) {
        this.tarikhEtmamGharardad = tarikhEtmamGharardad;
    }
}
