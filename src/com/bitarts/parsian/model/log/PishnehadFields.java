package com.bitarts.parsian.model.log;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:16 PM
 */
@Entity
@Table(name = "tbl_pishnehad_fields")
public class PishnehadFields implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "subject")
    private String subject;
    @Column(name = "the_field")
    private String field;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public PishnehadFields(String subject, String field) {
        this.subject = subject;
        this.field = field;
    }

    public PishnehadFields() {

    }
}
