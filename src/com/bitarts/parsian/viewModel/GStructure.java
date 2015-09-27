package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/8/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class GStructure {
    private String vaziat;
    private Integer tedadPishnehad;
    private Double darsadKol;
    private String naghsh;
    private String color;

    public GStructure() {
    }

    public GStructure(String vaziat, Integer tedadPishnehad, Double darsadKol, String naghsh, String color) {
        this.vaziat = vaziat;
        this.tedadPishnehad = tedadPishnehad;
        this.darsadKol = darsadKol;
        this.naghsh = naghsh;
        this.color = color;
    }

    public String getVaziat() {
        return vaziat;
    }

    public void setVaziat(String vaziat) {
        this.vaziat = vaziat;
    }

    public Integer getTedadPishnehad() {
        return tedadPishnehad;
    }

    public void setTedadPishnehad(Integer tedadPishnehad) {
        this.tedadPishnehad = tedadPishnehad;
    }

    public Double getDarsadKol() {
        return darsadKol;
    }

    public void setDarsadKol(Double darsadKol) {
        this.darsadKol = darsadKol;
    }

    public String getNaghsh() {
        return naghsh;
    }

    public void setNaghsh(String naghsh) {
        this.naghsh = naghsh;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
