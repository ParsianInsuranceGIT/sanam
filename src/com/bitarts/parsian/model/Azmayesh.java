package com.bitarts.parsian.model;

import org.hibernate.annotations.BatchSize;

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
@Table(name="tbl_azmayesh")
public class Azmayesh implements Serializable, Comparable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "azmayesh_id")
    private Integer id;
//    @Column(name = "azmayesh_type")
//    private String type;
//    @Column(name = "azmayesh_name")
//    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "azmayeshname_id")
    private AzmayeshName azmayeshName;

    @Column(name = "azmayesh_price")
    private String price;
    @Column(name = "azmayesh_description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 30)
    @JoinTable(name = "rel_azmayesh_clinic", joinColumns = { @JoinColumn(name = "azmayesh_id", referencedColumnName = "azmayesh_id") }, inverseJoinColumns = { @JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id") })
    private Set<Clinic> clinics;

    public Set<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(Set<Clinic> clinics) {
        this.clinics = clinics;
    }

    public AzmayeshName getAzmayeshName() {
        return azmayeshName;
    }

    public void setAzmayeshName(AzmayeshName azmayeshName) {
        this.azmayeshName = azmayeshName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int compareTo(Object o) {
        return getAzmayeshName().getName().compareTo(((Azmayesh)o).getAzmayeshName().getName());
    }
}
