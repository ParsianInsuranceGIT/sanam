package com.bitarts.parsian.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.engine.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:51 PM
 */
@Entity
@Table(name="tbl_transition")
public class Transition implements Serializable, Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transition_id")
    private Integer id;
    @Column(name = "transition_name")
    private String transitionName;
    @ManyToMany(mappedBy = "transitions",fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Set<Role> roles;
    @ManyToOne(cascade = {CascadeType.ALL},optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id1")
    State stateBegin;
    @ManyToOne(cascade = {CascadeType.ALL},optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id2", insertable = false, updatable = false)
    State stateEnd;
    @Column(name = "transition_show")
    private String transitionShow;

    public State getStateBegin() {
        return stateBegin;
    }

    public void setStateBegin(State stateBegin) {
        this.stateBegin = stateBegin;
    }

    public State getStateEnd() {
        return stateEnd;
    }

    public void setStateEnd(State stateEnd) {
        this.stateEnd = stateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransitionName() {
        return transitionName;
    }

    public void setTransitionName(String transitionName) {
        this.transitionName = transitionName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int compareTo(Object o) {
        return this.getStateEnd().getId().compareTo(((Transition)o).getStateEnd().getId());
    }

    public String getTransitionShow() {
        return transitionShow;
    }

    public void setTransitionShow(String transitionShow) {
        this.transitionShow = transitionShow;
    }
}
