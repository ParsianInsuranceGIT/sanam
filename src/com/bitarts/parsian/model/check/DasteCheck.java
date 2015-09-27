package com.bitarts.parsian.model.check;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 30, 2011
 * Time: 12:07:41 PM
 */
@Entity
@Table(name = "tbl_daste_check")
public class DasteCheck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "shomare_hesab")
    private String shomareHesab;
    @Column(name = "az_shomare")
    private String azShomare;
    @Column(name = "ta_shomare")
    private String taShomare;
    @OneToMany(mappedBy = "dasteCheck", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Check> checkList;

    public DasteCheck() {
    }

    public DasteCheck(String name, String shomareHesab, String azShomare, String taShomare) {
        this.name = name;
        this.shomareHesab = shomareHesab;
        this.azShomare = azShomare;
        this.taShomare = taShomare;
    }

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

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

    public String getAzShomare() {
        return azShomare;
    }

    public void setAzShomare(String azShomare) {
        this.azShomare = azShomare;
    }

    public String getTaShomare() {
        return taShomare;
    }

    public void setTaShomare(String taShomare) {
        this.taShomare = taShomare;
    }

    public String getShomareHesab() {
        return shomareHesab;
    }

    public void setShomareHesab(String shomareHesab) {
        this.shomareHesab = shomareHesab;
    }
}
