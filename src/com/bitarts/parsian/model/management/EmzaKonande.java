package com.bitarts.parsian.model.management;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_emzakonande")
public class EmzaKonande implements Serializable {

    public enum EmzakonandeType {
        SODUR, KHESARAT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emzakonande_id")
    private Integer id;
    @Column(name = "emzakonande_active")
    private String active;
    @Column(name = "emzakonande_job")
    private String job;
    @Column(name = "emzakonande_date")
    private String date;
    @Column(name = "emzakonande_maxcapital")
    private long maxCapital;
    //    @OneToOne(mappedBy = "emzaKonande", fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "personal_code")
    private String personalCode;
    @Enumerated(EnumType.STRING)
    private EmzakonandeType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_emzakonande_namayande", joinColumns = {@JoinColumn(name = "emzakonande_id", referencedColumnName = "emzakonande_id")}, inverseJoinColumns = {@JoinColumn(name = "namayande_id", referencedColumnName = "id")})
    @Fetch(FetchMode.SUBSELECT)
    private List<Namayande> otherMojtamaSodoor;

    public EmzaKonande() {

    }

    public EmzaKonande(String active, String job, String date, long maxCapital, User user, String personalCode) {
        this.active = active;
        this.job = job;
        this.date = date;
        this.maxCapital = maxCapital;
        this.user = user;
        this.personalCode = personalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getMaxCapital() {
        return maxCapital;
    }

    public void setMaxCapital(long maxCapital) {
        this.maxCapital = maxCapital;
    }

    public String getMaxCapitalString() {
        return NumberFormat.getNumberInstance().format(maxCapital);
    }

    public void setMaxCapitalString(String maxCapital) {
        setMaxCapital(Long.parseLong(maxCapital.replaceAll(",","")));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getTypeFarsi() {
        if (type == null) return "";
        switch (type) {
            case SODUR:
                return "امضاکننده صدور";
            case KHESARAT:
                return "امضاکننده خسارت";
            default:
                return "";
        }
    }

    public EmzakonandeType getType() {
        return type;
    }

    public void setType(EmzakonandeType type) {
        this.type = type;
    }

    public List<Namayande> getOtherMojtamaSodoor() {
        return otherMojtamaSodoor;
    }

    public void setOtherMojtamaSodoor(List<Namayande> otherMojtamaSodoor) {
        this.otherMojtamaSodoor = otherMojtamaSodoor;
    }
}
