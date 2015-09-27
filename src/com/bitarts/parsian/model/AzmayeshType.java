package com.bitarts.parsian.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_azmayeshtype")
public class AzmayeshType implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "azmayeshtype_id")
    private Integer id;
    @Column(name = "azmayeshtype_type")
    private String type;
    @Column(name = "azmayeshtype_description")
    private String description;
    @OneToMany(mappedBy = "azmayeshType",fetch = FetchType.LAZY)
    private Set<AzmayeshName> azmayeshNames;

    public Set<AzmayeshName> getAzmayeshNames() {
        return azmayeshNames;
    }

    public void setAzmayeshNames(Set<AzmayeshName> azmayeshNames) {
        this.azmayeshNames = azmayeshNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
