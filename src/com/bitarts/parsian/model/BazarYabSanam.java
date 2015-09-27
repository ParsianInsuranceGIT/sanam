package com.bitarts.parsian.model;

import com.bitarts.parsian.model.asnadeSodor.Credebit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User: a.sabzechian
 * Date: 11/18/14
 * Time: 3:29 PM
 */
@Entity
@Table(name="tbl_bazaryab_sanam")
public class BazarYabSanam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bazaryab_sanam_id")
    private Long id;
    @Column(name = "bazaryab_code")
    private Long code;
    @Column(name = "bazaryab_name")
    private String name;
    @OneToMany(mappedBy = "bazarYabSanam", fetch = FetchType.LAZY)
    private List<Credebit> credebitList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedSodor_id")
    private Namayande vahedSodor;

    public Namayande getVahedSodor()
    {
        return vahedSodor;
    }

    public void setVahedSodor(Namayande vahedSodor)
    {
        this.vahedSodor = vahedSodor;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Credebit> getCredebitList() {
        return credebitList;
    }

    public void setCredebitList(List<Credebit> credebitList) {
        this.credebitList = credebitList;
    }

    public BazarYabSanam()
    {}

    public BazarYabSanam(Long code , String name, Namayande vahedSodor)
    {
        this.code = code;
        this.name = name;
        this.vahedSodor = vahedSodor;
    }
}
