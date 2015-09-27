package com.bitarts.parsian.model.karmozd;

import com.bitarts.common.util.DateUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 5/13/13
 * Time: 3:12 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_karmozd_tadilat")
public class KarmozdTadilat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "xl_radif")
    private String xlRadif;
    @Column(name = "xl_code_namayande")
    private String xlCodeNamayande;
    @Column(name = "xl_karmozde_2_sodore_1")
    private String xlKarmozde2Sodore1;
    @Column(name = "xl_mablagh")
    private String xlMablagh;
    @Column(name = "xl_bimename_1")
    private String xlBimename1;
    @Column(name = "xl_shomare_bimename")
    private String xlShomareBimename;
    @Column(name = "xl_onvan")
    private String xlOnvan;
    @Column(name = "xl_sharh")
    private String xlSharh;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karmozd_id")
    private Karmozd karmozd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="karmozd_ghest_id")
    private KarmozdGhest karmozdGhest;
    @Column(name="created_date")
    private String createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karmozd_namayande_id")
    private KarmozdNamayande karmozdNamayande;

    public KarmozdTadilat(String xlCodeNamayande,String xlMablagh,String xlShomareBimename,String xlOnvan,String xlSharh,KarmozdGhest karmozdGhest)
    {
        this.xlCodeNamayande=xlCodeNamayande;
        this.xlMablagh=xlMablagh;
        this.xlShomareBimename=xlShomareBimename;
        this.xlOnvan=xlOnvan;
        this.xlSharh=xlSharh;
        this.karmozdGhest=karmozdGhest;
        this.createdDate= DateUtil.getCurrentDate();
    }
    public KarmozdTadilat() {
    }

    public KarmozdTadilat(Karmozd karmozd, String xlRadif, String xlCodeNamayande, String xlKarmozde2Sodore1, String xlMablagh, String xlBimename1, String xlShomareBimename, String xlOnvan, String xlSharh) {
        this.karmozd = karmozd;
        this.xlRadif = xlRadif;
        this.xlCodeNamayande = xlCodeNamayande;
        this.xlKarmozde2Sodore1 = xlKarmozde2Sodore1;
        this.xlMablagh = xlMablagh;
        this.xlBimename1 = xlBimename1;
        this.xlShomareBimename = xlShomareBimename;
        this.xlOnvan = xlOnvan;
        this.xlSharh = xlSharh;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KarmozdNamayande getKarmozdNamayande()
    {
        return karmozdNamayande;
    }

    public void setKarmozdNamayande(KarmozdNamayande karmozdNamayande)
    {
        this.karmozdNamayande = karmozdNamayande;
    }

    public String getXlRadif() {
        return xlRadif;
    }

    public void setXlRadif(String xlRadif) {
        this.xlRadif = xlRadif;
    }

    public String getXlCodeNamayande() {
        return xlCodeNamayande;
    }

    public KarmozdGhest getKarmozdGhest()
    {
        return karmozdGhest;
    }

    public void setKarmozdGhest(KarmozdGhest karmozdGhest)
    {
        this.karmozdGhest = karmozdGhest;
    }

    public void setXlCodeNamayande(String xlCodeNamayande) {
        this.xlCodeNamayande = xlCodeNamayande;
    }

    public String getXlKarmozde2Sodore1() {
        return xlKarmozde2Sodore1;
    }

    public void setXlKarmozde2Sodore1(String xlKarmozde2Sodore1) {
        this.xlKarmozde2Sodore1 = xlKarmozde2Sodore1;
    }

    public String getXlMablagh() {
        return xlMablagh;
    }

    public void setXlMablagh(String xlMablagh) {
        this.xlMablagh = xlMablagh;
    }

    public String getXlBimename1() {
        return xlBimename1;
    }

    public void setXlBimename1(String xlBimename1) {
        this.xlBimename1 = xlBimename1;
    }

    public String getXlShomareBimename() {
        return xlShomareBimename;
    }

    public void setXlShomareBimename(String xlShomareBimename) {
        this.xlShomareBimename = xlShomareBimename;
    }

    public String getXlOnvan() {
        return xlOnvan;
    }

    public void setXlOnvan(String xlOnvan) {
        this.xlOnvan = xlOnvan;
    }

    public String getXlSharh() {
        return xlSharh;
    }

    public void setXlSharh(String xlSharh) {
        this.xlSharh = xlSharh;
    }

    public Karmozd getKarmozd() {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd) {
        this.karmozd = karmozd;
    }
}
