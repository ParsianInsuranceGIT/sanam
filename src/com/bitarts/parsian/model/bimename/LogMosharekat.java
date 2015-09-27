package com.bitarts.parsian.model.bimename;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Mosharekat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/19/12
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_log_mosharekat")
public class LogMosharekat implements Serializable, Comparable<LogMosharekat> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Bimename bimename;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Mosharekat mosharekat;
    private String mablagheMosharekat;
    @Column(name = "created_date")
    private String createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public Mosharekat getMosharekat() {
        return mosharekat;
    }

    public void setMosharekat(Mosharekat mosharekat) {
        this.mosharekat = mosharekat;
    }

    public String getMablagheMosharekat() {
        if (mablagheMosharekat == null) return "0";
        return mablagheMosharekat;
    }

    public void setMablagheMosharekat(String mablagheMosharekat) {
        this.mablagheMosharekat = mablagheMosharekat;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int compareTo(LogMosharekat o) {
        if (id.equals(o.getId())) return 0;
        else if (!DateUtil.isGreaterThanOrEqual(createdDate, o.createdDate)) return 1;
        else return -1;
    }

}

