package com.bitarts.parsian.model.check;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 30, 2011
 * Time: 12:08:47 PM
 */
@Entity
@Table(name = "tbl_check")
public class Check implements Serializable {
    public static enum Status {
        NORMAL, PRINT_SHODE, BATEL_SHODE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "shomare")
    private String shomare;
    @Column(name = "darVajhe")
    private String darVajhe;
    @Column(name = "tarikh")
    private String tarikh;
    @Column(name = "amount_tajamoi")
    private String amountTajamoi;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daste_check_id")
    private DasteCheck dasteCheck;

    public Check() {
    }

    public Check(String shomare, String darVajhe, String tarikh, String amountTajamoi, Status status, DasteCheck dasteCheck) {
        this.shomare = shomare;
        this.darVajhe = darVajhe;
        this.tarikh = tarikh;
        this.amountTajamoi = amountTajamoi;
        this.status = status;
        this.dasteCheck = dasteCheck;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getDarVajhe() {
        return darVajhe;
    }

    public void setDarVajhe(String darVajhe) {
        this.darVajhe = darVajhe;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getAmountTajamoi() {
        return amountTajamoi;
    }

    public void setAmountTajamoi(String amountTajamoi) {
        this.amountTajamoi = amountTajamoi;
    }

    public DasteCheck getDasteCheck() {
        return dasteCheck;
    }

    public void setDasteCheck(DasteCheck dasteCheck) {
        this.dasteCheck = dasteCheck;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
