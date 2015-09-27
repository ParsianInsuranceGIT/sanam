package com.bitarts.parsian.model;

import org.hibernate.engine.Cascade;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_azmayeshname")
public class AzmayeshName implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "azmayeshname_id")
    private Integer id;
    @Column(name = "azmayeshname_name")
    private String name;
    @Column(name = "azmayeshname_description")
    private String description;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "azmayeshtype_id")
    private AzmayeshType azmayeshType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AzmayeshType getAzmayeshType() {
        return azmayeshType;
    }

    public void setAzmayeshType(AzmayeshType azmayeshType) {
        this.azmayeshType = azmayeshType;
    }
}
