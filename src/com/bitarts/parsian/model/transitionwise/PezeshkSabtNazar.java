package com.bitarts.parsian.model.transitionwise;

import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.pishnahad.Pishnehad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_pezeshkt_sabt_nazar")
public class PezeshkSabtNazar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sabt_nazar_id")
    private Integer id;
    @Column(name = "sabt_nazar_nazar")
    private String nazar;
    @Column(name = "sabt_nazar_nerkh")
    private String ezafeNerkh;
    @Column(name = "sabt_nazar_pezeshk")
    private boolean fromPezeshk;
    @Column(name="sabt_nazar_tozih")
    private String tozihat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Pishnehad pishnehad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_taghirat_id")
    private DarkhastTaghirat darkhastTaghirat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazar() {
        return nazar;
    }

    public void setNazar(String nazar) {
        this.nazar = nazar;
    }

    public String getEzafeNerkh() {
        return ezafeNerkh;
    }

    public void setEzafeNerkh(String ezafeNerkh) {
        this.ezafeNerkh = ezafeNerkh;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public boolean isFromPezeshk() {
        return fromPezeshk;
    }

    public void setFromPezeshk(boolean fromPezeshk) {
        this.fromPezeshk = fromPezeshk;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

}
