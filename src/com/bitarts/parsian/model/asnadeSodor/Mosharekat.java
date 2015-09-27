package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.LogMosharekat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 10/2/11
 * Time: 6:23 PM
 */
@Entity
@Table(name = "tbl_mosharekat")
public class Mosharekat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "vahed_sodour")
    private String vahedSodour;
    @Column(name = "shomare_mosharekat")
    private String shomareMosharekat;
    @Column(name = "shorou_dowre_tarikh")
    private String tarikhShoruDowre;
    @Column(name = "payan_dowre_tarikh")
    private String tarikhPayanDowre;
    @Column(name = "onvan_dowre")
    private String onvanDowre;
    @Column(name = "tozihat")
    private String tozihat;
    @Column(name = "mablagh_sud_tashim")
    private String mablaghSudTashim;
    @Column(name = "mabna_mohasebe_tarikh")
    private String tarikhMabnaMohasebe;
    @Column(name = "mohasebe_sud_tarikh")
    private String tarikhMohasebeSud;
    @Column(name = "soud")
    private String soud;
    @OneToMany(mappedBy = "mosharekat")
    private List<LogMosharekat> logMosharekats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVahedSodour() {
        return vahedSodour;
    }

    public void setVahedSodour(String vahedSodour) {
        this.vahedSodour = vahedSodour;
    }

    public String getShomareMosharekat() {
        return shomareMosharekat;
    }

    public void setShomareMosharekat(String shomareMosharekat) {
        this.shomareMosharekat = shomareMosharekat;
    }

    public String getTarikhShoruDowre() {
        return tarikhShoruDowre;
    }

    public void setTarikhShoruDowre(String tarikhShoruDowre) {
        this.tarikhShoruDowre = tarikhShoruDowre;
    }

    public String getTarikhPayanDowre() {
        return tarikhPayanDowre;
    }

    public void setTarikhPayanDowre(String tarikhPayanDowre) {
        this.tarikhPayanDowre = tarikhPayanDowre;
    }

    public String getOnvanDowre() {
        return onvanDowre;
    }

    public void setOnvanDowre(String onvanDowre) {
        this.onvanDowre = onvanDowre;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getMablaghSudTashim() {
        return mablaghSudTashim;
    }

    public void setMablaghSudTashim(String mablaghSudTashim) {
        this.mablaghSudTashim = mablaghSudTashim;
    }

    public String getTarikhMabnaMohasebe() {
        return tarikhMabnaMohasebe;
    }

    public void setTarikhMabnaMohasebe(String tarikhMabnaMohasebe) {
        this.tarikhMabnaMohasebe = tarikhMabnaMohasebe;
    }

    public String getTarikhMohasebeSud() {
        return tarikhMohasebeSud;
    }

    public void setTarikhMohasebeSud(String tarikhMohasebeSud) {
        this.tarikhMohasebeSud = tarikhMohasebeSud;
    }

    public List<LogMosharekat> getLogMosharekats() {
        return logMosharekats;
    }

    public void setLogMosharekats(List<LogMosharekat> logMosharekats) {
        this.logMosharekats = logMosharekats;
    }

    @Override
    public int hashCode() {
        return super.hashCode();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(((Mosharekat) obj).getId());
    }


    public String getSoud() {
        return soud;
    }

    public void setSoud(String soud) {
        this.soud = soud;
    }

}
