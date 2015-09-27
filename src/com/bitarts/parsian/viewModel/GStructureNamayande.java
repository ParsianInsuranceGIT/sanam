package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/8/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class GStructureNamayande implements Comparable<GStructureNamayande> {
    private String nameAndKod;
    private Integer tedadPishnehadSabt;
    private Integer tedadPishnehadJadid;
    private Integer tedadPishnehadErsal;
    private Integer tedadPishnehadNahai;
    private Integer tedadPishnehadEnseraf;
    private Integer tedadPishnehadMontafi;

    public GStructureNamayande(String nameAndKod, Integer tedadPishnehadSabt, Integer tedadPishnehadJadid, Integer tedadPishnehadErsal, Integer tedadPishnehadNahai, Integer tedadPishnehadEnseraf, Integer tedadPishnehadMontafi) {
        this.nameAndKod = nameAndKod;
        this.tedadPishnehadSabt = tedadPishnehadSabt;
        this.tedadPishnehadJadid = tedadPishnehadJadid;
        this.tedadPishnehadErsal = tedadPishnehadErsal;
        this.tedadPishnehadNahai = tedadPishnehadNahai;
        this.tedadPishnehadEnseraf = tedadPishnehadEnseraf;
        this.tedadPishnehadMontafi = tedadPishnehadMontafi;
    }

    public String getNameAndKod() {
        return nameAndKod;
    }

    public void setNameAndKod(String nameAndKod) {
        this.nameAndKod = nameAndKod;
    }

    public Integer getTedadPishnehadSabt() {
        return tedadPishnehadSabt;
    }

    public void setTedadPishnehadSabt(Integer tedadPishnehadSabt) {
        this.tedadPishnehadSabt = tedadPishnehadSabt;
    }

    public Integer getTedadPishnehadJadid() {
        return tedadPishnehadJadid;
    }

    public void setTedadPishnehadJadid(Integer tedadPishnehadJadid) {
        this.tedadPishnehadJadid = tedadPishnehadJadid;
    }

    public Integer getTedadPishnehadErsal() {
        return tedadPishnehadErsal;
    }

    public void setTedadPishnehadErsal(Integer tedadPishnehadErsal) {
        this.tedadPishnehadErsal = tedadPishnehadErsal;
    }

    public Integer getTedadPishnehadNahai() {
        return tedadPishnehadNahai;
    }

    public void setTedadPishnehadNahai(Integer tedadPishnehadNahai) {
        this.tedadPishnehadNahai = tedadPishnehadNahai;
    }

    public Integer getTedadPishnehadEnseraf() {
        return tedadPishnehadEnseraf;
    }

    public void setTedadPishnehadEnseraf(Integer tedadPishnehadEnseraf) {
        this.tedadPishnehadEnseraf = tedadPishnehadEnseraf;
    }

    public Integer getTedadPishnehadMontafi() {
        return tedadPishnehadMontafi;
    }

    public void setTedadPishnehadMontafi(Integer tedadPishnehadMontafi) {
        this.tedadPishnehadMontafi = tedadPishnehadMontafi;
    }

    @Override
    public int compareTo(GStructureNamayande o) {
        if (o.getTedadPishnehadSabt().equals(tedadPishnehadSabt)) {
            if (o.getTedadPishnehadJadid().equals(tedadPishnehadJadid)) {
                if (o.getTedadPishnehadErsal().equals(tedadPishnehadErsal)) {
                    if (o.getTedadPishnehadNahai().equals(tedadPishnehadNahai)) {
                        return o.getTedadPishnehadEnseraf().compareTo(tedadPishnehadEnseraf);
                    } else return o.getTedadPishnehadNahai().compareTo(tedadPishnehadNahai);
                } else return o.getTedadPishnehadErsal().compareTo(tedadPishnehadErsal);
            } else o.getTedadPishnehadJadid().compareTo(tedadPishnehadJadid);
        }
        return o.getTedadPishnehadSabt().compareTo(tedadPishnehadSabt);
    }
}
