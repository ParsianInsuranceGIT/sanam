package com.bitarts.parsian.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: a-sajadian
 * Date: 10/11/15
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CredebitType")
public class CredebitType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "latinName")
    private String latinName;
    @Column(name = "farsiName")
    private String farsiName;
    @Column(name = "bedOrbes")
    private Number bedOrbes;
    @Column(name = "vaziatVosouli")
    private Number vaziatVosouli;

    public CredebitType(String latinName, String farsiName, Number bedOrbes, Number vaziatVosouli) {
        this.latinName = latinName;
        this.farsiName = farsiName;
        this.bedOrbes = bedOrbes;
        this.vaziatVosouli = vaziatVosouli;
    }

    public CredebitType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getFarsiName() {
        return farsiName;
    }

    public void setFarsiName(String farsiName) {
        this.farsiName = farsiName;
    }

    public Number getBedOrbes() {
        return bedOrbes;
    }

    public void setBedOrbes(Number bedOrbes) {
        this.bedOrbes = bedOrbes;
    }

    public Number getVaziatVosouli() {
        return vaziatVosouli;
    }

    public void setVaziatVosouli(Number vaziatVosouli) {
        this.vaziatVosouli = vaziatVosouli;
    }

}
