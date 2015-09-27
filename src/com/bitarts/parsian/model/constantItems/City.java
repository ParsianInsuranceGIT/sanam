package com.bitarts.parsian.model.constantItems;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: navit
 * Date: 12/28/11
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_city")
public class City implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "city_id_for_pid")
    private Long cityIdForPid;
    @Column(name = "city_pid")
    private Long cityPid;
    @Column(name = "city_type")
    private int cityType;
    @Column(name = "city_name")
    private String cityName;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCityIdForPid() {
        return cityIdForPid;
    }

    public void setCityIdForPid(Long cityIdForPid) {
        this.cityIdForPid = cityIdForPid;
    }

    public Long getCityPid() {
        return cityPid;
    }

    public void setCityPid(Long cityPid) {
        this.cityPid = cityPid;
    }

    public int getCityType() {
        return cityType;
    }

    public void setCityType(int cityType) {
        this.cityType = cityType;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityNameWithoutOstan() {
        return cityName.replace("استان","").trim();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int compareTo(Object o) {
        return this.getCityName().compareTo(((City) o).getCityName());
    }
}
