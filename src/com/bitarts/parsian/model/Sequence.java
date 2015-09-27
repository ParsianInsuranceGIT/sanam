package com.bitarts.parsian.model;

import com.bitarts.parsian.model.bimename.Bimename;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 2, 2011
 * Time: 6:25:48 PM
 */
@Entity
@Table(name="tbl_sequence")
public class Sequence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "seq_name")
    private String sequenceName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seq_namayandeh")
    private Namayande sequenceNamayande;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seq_bimename_elhaghie")
    private Bimename bimename_elhaghie;
    @Column(name = "seq_count")
    private String sequenceCount;
    @Column(name = "description")
    private String description;
    @Column (name = "year")
    private String year;

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public Sequence(String sequenceName, String sequenceCount) {
        this.sequenceName = sequenceName;
        this.sequenceCount = sequenceCount;
    }

    public Sequence(String sequenceName, String sequenceCount, Namayande namayande) {
        this.sequenceName = sequenceName;
        this.sequenceCount = sequenceCount;
        this.sequenceNamayande = namayande;
    }

    public Sequence(String sequenceName, String sequenceCount, Namayande namayande,String year) {
        this.sequenceName = sequenceName;
        this.sequenceCount = sequenceCount;
        this.sequenceNamayande = namayande;
        this.year=year;
    }

    public Sequence(String sequenceName, String sequenceCount, Bimename bimename) {
        this.sequenceName = sequenceName;
        this.sequenceCount = sequenceCount;
        this.bimename_elhaghie = bimename;
    }

    public Sequence() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceCount() {
        return sequenceCount;
    }

    public void setSequenceCount(String sequenceCount) {
        this.sequenceCount = sequenceCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Namayande getSequenceNamayande() {
        return sequenceNamayande;
    }

    public void setSequenceNamayande(Namayande sequenceNamayande) {
        this.sequenceNamayande = sequenceNamayande;
    }

    public Bimename getBimename_elhaghie() {
        return bimename_elhaghie;
    }

    public void setBimename_elhaghie(Bimename bimename_elhaghie) {
        this.bimename_elhaghie = bimename_elhaghie;
    }
}
