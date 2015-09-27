package com.bitarts.parsian.model;

import com.bitarts.parsian.model.pishnahad.Moarefiname;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/24/12
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_hazinePezeshki")
public class HazinePezeshki implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Integer id;
    private String mablaghClinic;
    private String mablaghKhesarat;
    @Column(columnDefinition = "VARCHAR2(4000)")
    private String tozihat;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Moarefiname moarefiname;

    public HazinePezeshki() {
    }

    public HazinePezeshki(Moarefiname moarefiname) {
        this.moarefiname = moarefiname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMablaghClinic() {
        return mablaghClinic;
    }

    public void setMablaghClinic(String mablaghClinic) {
        this.mablaghClinic = mablaghClinic;
    }

    public String getMablaghKhesarat() {
        return mablaghKhesarat;
    }

    public void setMablaghKhesarat(String mablaghKhesarat) {
        this.mablaghKhesarat = mablaghKhesarat;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public Moarefiname getMoarefiname() {
        return moarefiname;
    }

    public void setMoarefiname(Moarefiname moarefiname) {
        this.moarefiname = moarefiname;
    }
}
