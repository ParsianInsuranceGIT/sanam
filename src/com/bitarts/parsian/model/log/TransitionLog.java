package com.bitarts.parsian.model.log;

import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.Transition;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_transition_log")
public class TransitionLog implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transition_log_id")
    private Integer id;
    @Column(name = "transition_log_date")
    private String date;
    @Column(name = "transition_log_time")
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnehad_id")
    private Pishnehad pishnehad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taTransition_id")
    private Transition transition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhastBazkharid_id")
    private DarkhastBazkharid darkhastBazkharid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhastTaghirat_id")
    private DarkhastTaghirat darkhastTaghirat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khesarat_id")
    private Khesarat khesarat;

    @Column(name = "transition_message")
    private String message;

    public TransitionLog(String date, String time, User user, DarkhastBazkharid darkhastBazkharid, Transition transition) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.darkhastBazkharid = darkhastBazkharid;
        this.transition = transition;
    }

    public TransitionLog(String date, String time, User user, Pishnehad pishnehad, Transition transition) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.pishnehad = pishnehad;
        this.transition = transition;
    }

    public TransitionLog(String date, String time, User theUser, DarkhastTaghirat theDarkhast, Transition transition) {
        this.date = date;
        this.time = time;
        this.user = theUser;
        this.darkhastTaghirat = theDarkhast;
        this.transition = transition;
    }

    public TransitionLog(String date, String time, User theUser, Khesarat khesarat, Transition transition) {
        this.date = date;
        this.time = time;
        this.user = theUser;
        this.khesarat = khesarat;
        this.transition = transition;
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

    public String getMessage() {
        if(message == null) return "";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransitionLog() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public int compareTo(Object o) {
        int dateCompare = this.getDate().compareTo(((TransitionLog) (o)).getDate());
        if (dateCompare == 0) {
            return this.getTime().compareTo(((TransitionLog) (o)).getTime());
        } else {
            return dateCompare;
        }
    }

    public Khesarat getKhesarat() {
        return khesarat;
    }

    public void setKhesarat(Khesarat khesarat) {
        this.khesarat = khesarat;
    }
}
