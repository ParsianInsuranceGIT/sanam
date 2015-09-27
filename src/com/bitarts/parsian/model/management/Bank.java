package com.bitarts.parsian.model.management;

import com.bitarts.parsian.model.AzmayeshName;
import com.bitarts.parsian.model.User;

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
@Table(name="tbl_bank")
public class Bank implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id")
    private Integer id;
    @Column(name = "bank_name")
    private String name;
    @OneToMany(mappedBy = "bank",fetch = FetchType.LAZY)
    private Set<Hesab> hesabs;

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

    public Set<Hesab> getHesabs() {
        return hesabs;
    }

    public void setHesabs(Set<Hesab> hesabs) {
        this.hesabs = hesabs;
    }
}
