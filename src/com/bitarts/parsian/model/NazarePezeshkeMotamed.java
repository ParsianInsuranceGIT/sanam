package com.bitarts.parsian.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 3/6/11
 * Time: 4:19 PM
 */
@Entity
@Table(name = "tbl_nazarePezeshkeMotamed")
public class NazarePezeshkeMotamed implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name_pezeshk")
    private String namePezesh;
    @Column(name = "ezafe_nerkh")
    private String ezafeNerkh;

    public NazarePezeshkeMotamed() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePezesh() {
        return namePezesh;
    }

    public void setNamePezesh(String namePezesh) {
        this.namePezesh = namePezesh;
    }

    public String getEzafeNerkh() {
        return ezafeNerkh;
    }

    public void setEzafeNerkh(String ezafeNerkh) {
        this.ezafeNerkh = ezafeNerkh;
    }

    public NazarePezeshkeMotamed(String namePezesh, String ezafeNerkh) {
        this.namePezesh = namePezesh;
        this.ezafeNerkh = ezafeNerkh;
    }
}
