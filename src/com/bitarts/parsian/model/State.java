package com.bitarts.parsian.model;

import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.implementation.UserService;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:51 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_state")
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id")
    private Integer id;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "statemachine_name")
    private String stateMachineName;
    @Column(name = "has_forme_miani")
    private String hasFormeMiani;
    @JoinColumn(name = "peygir_konande")
    @ManyToOne
    private Role peygirKonande;

    @OneToMany(mappedBy = "stateBegin", fetch = FetchType.LAZY)
    private Set<Transition> transitionBegin;
    @OneToMany(mappedBy = "stateEnd", fetch = FetchType.LAZY)
    private Set<Transition> transitionsEnd;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private Set<Pishnehad> pishnehadat;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private Set<DarkhastBazkharid> darkhasthayeBazkharid;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private Set<Elhaghiye> elhaghiyeha;
    @OneToMany(mappedBy = "state")
    private Set<Bimename> bimenameha;

    @ManyToMany(mappedBy = "states", fetch = FetchType.LAZY)
    private Set<User> users;

    public Set<Bimename> getBimenameha() {
        return bimenameha;
    }

    public void setBimenameha(Set<Bimename> bimenameha) {
        this.bimenameha = bimenameha;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Set<Transition> getTransitions() {
        Set<Transition> allTransitions = new HashSet<Transition>(transitionBegin);
        allTransitions.addAll(transitionsEnd);
        // Or use google-collections Sets.union() for bonus points
        return allTransitions;

    }

    public void setTransitions(Set<Transition> transitions) {
//        this.transitions = transitions;
    }

    public Set<Pishnehad> getPishnehadat() {
        return pishnehadat;
    }

    public void setPishnehadat(Set<Pishnehad> pishnehadat) {
        this.pishnehadat = pishnehadat;
    }

    public Set<Transition> getTransitionBegin() {
        return transitionBegin;
    }

    public void setTransitionBegin(Set<Transition> transitionBegin) {
        this.transitionBegin = transitionBegin;
    }

    public Set<Transition> getTransitionsEnd() {
        return transitionsEnd;
    }

    public void setTransitionsEnd(Set<Transition> transitionsEnd) {
        this.transitionsEnd = transitionsEnd;
    }

    public String getStateMachineName() {
        return stateMachineName;
    }

    public void setStateMachineName(String stateMachineName) {
        this.stateMachineName = stateMachineName;
    }

    public Set<DarkhastBazkharid> getDarkhasthayeBazkharid() {
        return darkhasthayeBazkharid;
    }

    public void setDarkhasthayeBazkharid(Set<DarkhastBazkharid> darkhasthayeBazkharid) {
        this.darkhasthayeBazkharid = darkhasthayeBazkharid;
    }

    public Set<Elhaghiye> getElhaghiyeha() {
        return elhaghiyeha;
    }

    public void setElhaghiyeha(Set<Elhaghiye> elhaghiyeha) {
        this.elhaghiyeha = elhaghiyeha;
    }

    public String getHasFormeMiani() {
        return hasFormeMiani;
    }

    public void setHasFormeMiani(String hasFormeMiani) {
        this.hasFormeMiani = hasFormeMiani;
    }

    public Role getPeygirKonande() {
        return peygirKonande;
    }

    public void setPeygirKonande(Role peygirKonande) {
        this.peygirKonande = peygirKonande;
    }
}
