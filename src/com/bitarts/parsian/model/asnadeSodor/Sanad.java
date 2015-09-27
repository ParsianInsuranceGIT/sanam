package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.Daftar;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 1:49 PM
 */
@Entity
@Table(name = "tbl_sanad")
public class Sanad implements Serializable {
    public static enum NoeSanad {
        GHABZE_RESID, PARDAKHT
    }

    public static enum Vaziat {
        MOVAGHAT, DAEM
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "shomare")
    private String shomare;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "noe_sanad")
    @Enumerated(EnumType.STRING)
    private NoeSanad noeSanad;
    @Column(name = "vaziat")
    @Enumerated(EnumType.STRING)
    private Vaziat vaziat;
    @OneToMany(mappedBy = "sanad", fetch = FetchType.LAZY)
    private Set<KhateSanad> khateSanadSet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedeSodor_id")
    private Namayande vahedeSodor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //b-h
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daftar_id")
    private Daftar daftar;


    public Sanad() {
    }

    public Sanad(String createdDate, String createdTime, NoeSanad noeSanad, Vaziat vaziat) {
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.noeSanad = noeSanad;
        this.vaziat = vaziat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public NoeSanad getNoeSanad() {
        return noeSanad;
    }

    public void setNoeSanad(NoeSanad noeSanad) {
        this.noeSanad = noeSanad;
    }

    public Vaziat getVaziat() {
        return vaziat;
    }


    public void setVaziat(Vaziat vaziat) {
        this.vaziat = vaziat;
    }

    public Set<KhateSanad> getKhateSanadSet() {
        return khateSanadSet;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public void setKhateSanadSet(Set<KhateSanad> khateSanadSet) {
        this.khateSanadSet = khateSanadSet;
    }

    public String getTarikhPardakht() {
        if(khateSanadSet.size() > 0)
        {
            KhateSanad[] khateSanads = (KhateSanad[])khateSanadSet.toArray();
            return khateSanads[0].getEtebarCredebit().getCreatedDate();
        }
        else
        {
            return "";
        }
    }

    public String getTotalBedehi()
    {
        long total=0l;
        for(KhateSanad kh: khateSanadSet)
        {
//            total+=Integer.parseInt(kh.getBedehiCredebit().getRemainingAmount().replaceAll(",","").trim());
            total +=Long.parseLong(kh.getAmount().replaceAll(",","").trim());
        }
        return NumberFormat.getNumberInstance().format(total);
    }

    public String getTotalEtebar()
    {
        Long total=0l;
        for(KhateSanad kh: khateSanadSet)
        {
//            total+=Integer.parseInt(kh.getEtebarCredebit().getAmount().replaceAll(",","").trim());
            total +=Long.parseLong(kh.getAmount().replaceAll(",","").trim());
        }

        return NumberFormat.getNumberInstance().format(total);
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public Namayande getVahedeSodor() {
        return vahedeSodor;
    }

    public void setVahedeSodor(Namayande vahedeSodor) {
        this.vahedeSodor = vahedeSodor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVaziatFarsi(){
        String rtnType="";
        switch (getVaziat()){
            case DAEM:
                rtnType="دائم";
                break;
            case MOVAGHAT:
                rtnType="موقت";
                break;
        }
         return rtnType;
    }

    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }
}