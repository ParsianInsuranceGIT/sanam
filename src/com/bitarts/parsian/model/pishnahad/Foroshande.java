package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 2:38 PM
 */
@Entity
@Table(name = "tbl_Foroshande")
public class Foroshande implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pish_dah_mishenaasid")
    private String pishnahaad_dahande_mishenaasid;
    @Column(name = "modat_shenaakht")
    private String modat_shenaakht;
    @Column(name = "molaah_az_sal_feli")
    private String molaahezaat_az_salaamat_feli;
    @Column(name = "tozihe_molaahezaat")
    private String tozihe_molaahezaat;
    @Column(name = "etelaat_kaamel")
    private String etelaat_kaamel;
    @Column(name = "sehat_emzaa")
    private String sehat_emzaa;

    public Foroshande() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPishnahaad_dahande_mishenaasid() {
        return pishnahaad_dahande_mishenaasid;
    }

    public void setPishnahaad_dahande_mishenaasid(String pishnahaad_dahande_mishenaasid) {
        this.pishnahaad_dahande_mishenaasid = pishnahaad_dahande_mishenaasid;
    }

    public String getModat_shenaakht() {
        return modat_shenaakht;
    }

    public void setModat_shenaakht(String modat_shenaakht) {
        this.modat_shenaakht = modat_shenaakht;
    }

    public String getMolaahezaat_az_salaamat_feli() {
        return molaahezaat_az_salaamat_feli;
    }

    public void setMolaahezaat_az_salaamat_feli(String molaahezaat_az_salaamat_feli) {
        this.molaahezaat_az_salaamat_feli = molaahezaat_az_salaamat_feli;
    }

    public String getTozihe_molaahezaat() {
        return tozihe_molaahezaat;
    }

    public void setTozihe_molaahezaat(String tozihe_molaahezaat) {
        this.tozihe_molaahezaat = tozihe_molaahezaat;
    }

    public String getEtelaat_kaamel() {
        return etelaat_kaamel;
    }

    public void setEtelaat_kaamel(String etelaat_kaamel) {
        this.etelaat_kaamel = etelaat_kaamel;
    }

    public String getSehat_emzaa() {
        return sehat_emzaa;
    }

    public void setSehat_emzaa(String sehat_emzaa) {
        this.sehat_emzaa = sehat_emzaa;
    }

    public Foroshande(String pishnahaad_dahande_mishenaasid, String modat_shenaakht, String molaahezaat_az_salaamat_feli, String tozihe_molaahezaat, String etelaat_kaamel, String sehat_emzaa) {
        this.pishnahaad_dahande_mishenaasid = pishnahaad_dahande_mishenaasid;
        this.modat_shenaakht = modat_shenaakht;
        this.molaahezaat_az_salaamat_feli = molaahezaat_az_salaamat_feli;
        this.tozihe_molaahezaat = tozihe_molaahezaat;
        this.etelaat_kaamel = etelaat_kaamel;
        this.sehat_emzaa = sehat_emzaa;
    }
}
