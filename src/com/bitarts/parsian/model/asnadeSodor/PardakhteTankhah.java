package com.bitarts.parsian.model.asnadeSodor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Sep 4, 2011
 * Time: 7:17:18 PM
 */
@Entity
@Table(name = "tbl_pardakhte_tankhah")
public class PardakhteTankhah implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "girande")
    private String girande;
    @Column(name = "tarikh")
    private String tarikh;
    @Column(name = "tozih")
    private String tozih;
    @OneToOne(mappedBy = "pardakhteTankhah", fetch = FetchType.LAZY)
    private Credebit credebit;

    public PardakhteTankhah(String girande, String tarikh, String tozih) {
        this.girande = girande;
        this.tarikh = tarikh;
        this.tozih = tozih;
    }

    public PardakhteTankhah() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGirande() {
        return girande;
    }

    public void setGirande(String girande) {
        this.girande = girande;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getTozih() {
        return tozih;
    }

    public void setTozih(String tozih) {
        this.tozih = tozih;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }
}
