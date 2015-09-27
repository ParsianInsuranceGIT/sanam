package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.bimename.DarkhastTaghirat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 1:39 PM
 */
@Entity
@Table(name = "tbl_logGhest")
public class LogGhest implements Serializable, Comparable<LogGhest> {
    public static final String ID = "id";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_time")
    private Long createdTime;
    @ManyToOne
    @JoinColumn(name = "c_darkhast")
    private DarkhastTaghirat darkhast;
    @OneToMany(mappedBy = "logGhest", cascade = CascadeType.ALL)
    private List<CredebitForLogGhest> credebitList;


    public LogGhest() {
    }

    public LogGhest(Long createdTime, DarkhastTaghirat darkhast) {
        this.createdTime = createdTime;
        this.darkhast = darkhast;
    }


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

    public DarkhastTaghirat getDarkhast() {
        return darkhast;
    }

    public void setDarkhast(DarkhastTaghirat darkhast) {
        this.darkhast = darkhast;
    }

    public List<CredebitForLogGhest> getCredebitList() {
        return credebitList;
    }

    public void setCredebitList(List<CredebitForLogGhest> credebitList) {
        this.credebitList = credebitList;
    }

    public List<CredebitForLogGhest> getPreCredebitList() {
        List<CredebitForLogGhest> list = new LinkedList<CredebitForLogGhest>();
        for (CredebitForLogGhest cflg : credebitList) {
            if (!cflg.getAfterChange())
                list.add(cflg);
        }
        return list;
    }

    public List<CredebitForLogGhest> getAfterCredebitList() {
        List<CredebitForLogGhest> list = new LinkedList<CredebitForLogGhest>();
        for (CredebitForLogGhest cflg : credebitList) {
            if (cflg.getAfterChange())
                list.add(cflg);
        }
        return list;
    }

    public int compareTo(LogGhest o) {
        return o.getCreatedTime().compareTo(createdTime);
    }
}
