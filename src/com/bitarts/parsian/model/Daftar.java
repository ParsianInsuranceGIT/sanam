package com.bitarts.parsian.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 5/24/15
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_daftar")
public class Daftar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;

    @Column(name="name")
  //  @Enumerated(EnumType.STRING)
    private String name;


    public static enum daftarName{
        PARSIAN,NAMAYANDE
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
