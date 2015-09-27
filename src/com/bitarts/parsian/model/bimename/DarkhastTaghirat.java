package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.LogGhest;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Moarefiname;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Table(name = "tbl_darkhast_taghir")
public class DarkhastTaghirat implements Serializable, Comparable<DarkhastTaghirat> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dartaghir_id")
    private Integer id;
    @Column(name = "dartaghir_tarikhdar")
    private String tarikhDarkhast;
    private Integer whenApply;

    @Column(name = "joziat")
    private String joziat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karshenas_id")
    private User karshenas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_creator_id")
    private User creator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zamaem_darkhast_id")
    private ZamaemDarkhast zamaemDarkhast;

//    @OneToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "oldpish_id")
    private Pishnehad oldPishnehad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newpish_id")
    private Pishnehad newPishnehad;

    @Column(name = "niaz_be_azmayesh")
    private String niazBeAzmayesh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToOne(mappedBy = "darkhastTaghirat", fetch = FetchType.LAZY)
    private Darkhast darkhast;

    @OneToMany(mappedBy = "darkhastTaghirat", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<TransitionLog> transitionLogs;

    @OneToMany(mappedBy = "darkhastTaghirat", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<PezeshkSabtNazar> nazaraatePezeshk;

    @OneToMany(mappedBy = "darkhastTaghirat", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Moarefiname> moarefinameList;

    @Column(name = "is_archive")
    private String isArchive;
    @Column(name = "darkhast_finished")
    private Boolean finished;
    @OneToMany(mappedBy = "darkhast", cascade = CascadeType.ALL)
    private List<LogGhest> logGhestList;

    @ManyToOne
    @JoinColumn(name="namayande_id")
    private Namayande namayande;

    @Column(name="have_bare_mali")
    private Boolean haveBareMali;

    public Boolean getHaveBareMali()
    {
        return haveBareMali;
    }

    public void setHaveBareMali(Boolean haveBareMali)
    {
        this.haveBareMali = haveBareMali;
    }

    public Namayande getNamayande()
    {
        return namayande;
    }

    public void setNamayande(Namayande namayande)
    {
        this.namayande = namayande;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getKarshenas() {
        return karshenas;
    }

    public void setKarshenas(User karshenas) {
        this.karshenas = karshenas;
    }

    public ZamaemDarkhast getZamaemDarkhast() {
        return zamaemDarkhast;
    }

    public void setZamaemDarkhast(ZamaemDarkhast zamaemDarkhast) {
        this.zamaemDarkhast = zamaemDarkhast;
    }

    public Pishnehad getOldPishnehad() {
        return oldPishnehad;
    }

    public void setOldPishnehad(Pishnehad oldPishnehad) {
        this.oldPishnehad = oldPishnehad;
    }

    public Pishnehad getNewPishnehad() {
        return newPishnehad;
    }

    public void setNewPishnehad(Pishnehad newPishnehad) {
        this.newPishnehad = newPishnehad;
    }

    public String getTarikhDarkhast() {
        return tarikhDarkhast;
    }

    public void setTarikhDarkhast(String tarikhDarkhast) {
        this.tarikhDarkhast = tarikhDarkhast;
    }

    public List<PezeshkSabtNazar> getNazaraatePezeshk() {
        return nazaraatePezeshk;
    }

    public void setNazaraatePezeshk(List<PezeshkSabtNazar> nazaraatePezeshk) {
        this.nazaraatePezeshk = nazaraatePezeshk;
    }

    public String getNiazBeAzmayesh() {
        return niazBeAzmayesh;
    }

    public void setNiazBeAzmayesh(String niazBeAzmayesh) {
        this.niazBeAzmayesh = niazBeAzmayesh;
    }

    public List<Moarefiname> getMoarefinameList() {
        return moarefinameList;
    }

    public void setMoarefinameList(List<Moarefiname> moarefinameList) {
        this.moarefinameList = moarefinameList;
    }

    public String getArchive() {
        return isArchive;
    }

    public void setArchive(String archive) {
        isArchive = archive;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public Boolean getFinished() {
        if (finished == null) finished = false;
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Integer getWhenApply() {
        return whenApply;
    }

    public void setWhenApply(Integer whenApply) {
        this.whenApply = whenApply;
    }

    public List<LogGhest> getLogGhestList() {
        return logGhestList;
    }

    public void setLogGhestList(List<LogGhest> logGhestList) {
        this.logGhestList = logGhestList;
    }

    public int compareTo(DarkhastTaghirat o) {
        if (o.tarikhDarkhast == null) return 1;
        if (tarikhDarkhast == null) return -1;
        return o.tarikhDarkhast.compareTo(tarikhDarkhast);
    }

    public Darkhast getDarkhast() {
        return darkhast;
    }

    public void setDarkhast(Darkhast darkhast) {
        this.darkhast = darkhast;
    }

    public String getJoziat() {
        return joziat;
    }

    public void setJoziat(String joziat) {
        this.joziat = joziat;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}