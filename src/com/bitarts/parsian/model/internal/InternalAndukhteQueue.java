package com.bitarts.parsian.model.internal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "internal_andukhte_queue")
public class InternalAndukhteQueue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bimename_id")
    private Integer bimenameId;

    public Integer getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
