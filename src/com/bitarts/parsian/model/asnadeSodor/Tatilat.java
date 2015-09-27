package com.bitarts.parsian.model.asnadeSodor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: a.sabzechian
 * Date: 1/29/14
 * Time: 1:48 PM
 */

@Entity
@Table(name = "tbl_tatilat")
public class Tatilat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tarikh")
    private String tarikh;
    @Column(name = "description")
    private String description;
    @Column(name = "year")
    private String year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
