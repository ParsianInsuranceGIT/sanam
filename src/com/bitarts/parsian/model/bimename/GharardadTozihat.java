package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/17/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_gharardadTozihat")
public class GharardadTozihat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_time")
    private Long createdTime;
    @Column(name = "c_createdDate")
    private String createdDate;
    @JoinColumn(name = "c_gharardad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gharardad gharardad;
    @Type(type = "text")
    @Column(name = "c_tozih")
    private String tozih;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userCreator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Gharardad getGharardad() {
        return gharardad;
    }

    public void setGharardad(Gharardad gharardad) {
        this.gharardad = gharardad;
    }

    public String getTozih() {
        return tozih;
    }

    public void setTozih(String tozih) {
        this.tozih = tozih;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
