package com.bitarts.parsian.model.constantItems;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/5/11
 * Time: 3:47 PM
 */
@Entity
@Table(name = "tbl_const_soalate_salamati")
public class ConstantSoalateVaziateSalamati implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "matne_soal")
    private String matneSoal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatneSoal() {
        return matneSoal;
    }

    public void setMatneSoal(String matneSoal) {
        this.matneSoal = matneSoal;
    }
}
