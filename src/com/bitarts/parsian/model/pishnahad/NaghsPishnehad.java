package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/19/11
 * Time: 6:58 PM
 */
@Entity
@Table(name = "tbl_naghs_pishnehad")
public class NaghsPishnehad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "naghs_id")
    private Integer id;
    @Column(name = "naghs_madarek")
    private String naghsMadaarek;
    @Column(name = "naghs_etelaat")
    private String naghsEtelaat;
    @Column(name = "naghs_fish")
    private String naghsFish;
    @Column(name = "tozihat")
    private String tozihat;

    @OneToOne(mappedBy="naghsPishnehad",fetch = FetchType.LAZY)
    private Pishnehad pishnehad;


    public NaghsPishnehad(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getNaghsMadaarek() {
        return naghsMadaarek;
    }

    public void setNaghsMadaarek(String naghsMadaarek) {
        this.naghsMadaarek = naghsMadaarek;
    }

    public String getNaghsEtelaat() {
        return naghsEtelaat;
    }

    public void setNaghsEtelaat(String naghsEtelaat) {
        this.naghsEtelaat = naghsEtelaat;
    }

    public String getNaghsFish() {
        return naghsFish;
    }

    public void setNaghsFish(String naghsFish) {
        this.naghsFish = naghsFish;
    }

}
