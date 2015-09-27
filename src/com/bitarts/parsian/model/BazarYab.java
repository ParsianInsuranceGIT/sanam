package com.bitarts.parsian.model;

import com.bitarts.parsian.model.pishnahad.Pishnehad;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name="tbl_bazaryab")
public class BazarYab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bazaryab_id")
    private Integer id;
    @Column(name = "bazaryab_name")
    private String name;
    @Column(name = "bazaryab_family")
    private String nameKhanevadegi;


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

    public String getNameKhanevadegi() {
        return nameKhanevadegi;
    }

    public void setNameKhanevadegi(String nameKhanevadegi) {
        this.nameKhanevadegi = nameKhanevadegi;
    }
}
