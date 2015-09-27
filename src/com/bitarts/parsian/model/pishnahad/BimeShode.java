package com.bitarts.parsian.model.pishnahad;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@Entity
@Table(name = "tbl_bimeShode")
public class BimeShode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "shakhs_id")
    private Shakhs shakhs;

    @OneToMany(mappedBy = "bimeShode", fetch = FetchType.LAZY)
    private Set<Pishnehad> pishnehadSet;

    public Set<Pishnehad> getPishnehadSet()
    {
        return pishnehadSet;
    }

    public void setPishnehadSet(Set<Pishnehad> pishnehadSet)
    {
        this.pishnehadSet = pishnehadSet;
    }

    public BimeShode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shakhs getShakhs() {
        return shakhs;
    }

    public void setShakhs(Shakhs shakhs) {
        this.shakhs = shakhs;
    }
}
