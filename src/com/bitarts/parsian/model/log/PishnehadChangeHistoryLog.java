package com.bitarts.parsian.model.log;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.pishnahad.Pishnehad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:00 PM
 */
@Entity
@Table(name = "tbl_pishnehad_changes_log")
public class PishnehadChangeHistoryLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    private String date;
    @Column(name = "created_time")
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehadFields_id")
    private PishnehadFields pishnehadFields;
    @Column(name = "from_value")
    private String fromValue;
    @Column(name = "to_value")
    private String toValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public PishnehadFields getPishnehadFields() {
        return pishnehadFields;
    }

    public void setPishnehadFields(PishnehadFields pishnehadFields) {
        this.pishnehadFields = pishnehadFields;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public PishnehadChangeHistoryLog() {
    }

    public PishnehadChangeHistoryLog(PishnehadFields pishnehadFields, String fromValue, String toValue) {
        this.pishnehadFields = pishnehadFields;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }
}
