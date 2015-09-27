package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 8/25/11
 * Time: 12:14 PM
 */
@Entity
@Table(name = "tbl_khate_sanad")
public class KhateSanad implements Serializable, Comparable<KhateSanad> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private String amount;
    @Column(name = "etebarRemaining")
    private String etebarRemaining;
    @Column(name = "bedehiRemaining")
    private String bedehiRemaining;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etebar_credebit_id")
    private Credebit etebarCredebit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bedehi_credebit_id")
    private Credebit bedehiCredebit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanad_id")
    private Sanad sanad;

    @Column(name = "KHATESANAD_CONVERTED")
    private String converted;


    @OneToMany(mappedBy = "khateSanad",fetch = FetchType.LAZY)
    private List<KarmozdGhest> karmozdGhestList;



    public KhateSanad() {
    }

    public KhateSanad(KhateSanad from) {
        this.amount = from.amount;
        this.etebarRemaining = from.etebarRemaining;
        this.bedehiRemaining = from.bedehiRemaining;
        this.etebarCredebit = from.etebarCredebit;
        this.bedehiCredebit = from.bedehiCredebit;
        this.sanad = from.sanad;
        this.id = from.id;
    }


    public String getConverted()
    {
        return converted;
    }

    public void setConverted(String converted)
    {
        this.converted = converted;
    }

    public KarmozdGhest getKarmozdGhestePardakhti()       // karmozdGhesti ke pardakht mishe . . .
    {
        if(karmozdGhestList.size()==0) return null;
        for (KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getType().equals(KarmozdGhest.Type.ADI)) return kg;
            if(kg.getType().equals(KarmozdGhest.Type.TALIGHI)) return kg;
            if(kg.getType().equals(KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI)) return kg;
        }
        return null;
    }

    public boolean isHaveDarEntezarSarresid()
    {
        if (karmozdGhestList==null) return false;

        for(KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getType().equals(KarmozdGhest.Type.DAR_ENTEZAR_SARRESID))
                return true;
        }
        return false;
    }
    public KarmozdGhest getDarEntezarSarresidKarmozd()
    {
        if (karmozdGhestList==null) return null;

        for(KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getType().equals(KarmozdGhest.Type.DAR_ENTEZAR_SARRESID))
                return kg;
        }
        return null;
    }

    public List<KarmozdGhest> getKarmozdGhestList()
    {
        return karmozdGhestList;
    }

    public void setKarmozdGhestList(List<KarmozdGhest> karmozdGhestList)
    {
        this.karmozdGhestList = karmozdGhestList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Credebit getEtebarCredebit() {
        return etebarCredebit;
    }

    public void setEtebarCredebit(Credebit etebarCredebit) {
        this.etebarCredebit = etebarCredebit;
    }

    public Credebit getBedehiCredebit() {
        return bedehiCredebit;
    }

    public void setBedehiCredebit(Credebit bedehiCredebit) {
        this.bedehiCredebit = bedehiCredebit;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public String getAmount() {
        return amount;
    }

    public Long getAmount_long() {
        if(amount == null)
            return 0L;
        return Long.parseLong(amount.replaceAll(",", ""));
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public int compareTo(KhateSanad o) {
        if (id.equals(o.getId())) return 0;
        if (DateUtil.isGreaterThan(sanad.getCreatedDate(), o.sanad.getCreatedDate())) return 1;
        else if(sanad.getCreatedDate().equals(o.sanad.getCreatedDate())) {
            if(etebarCredebit.getId().equals(o.getEtebarCredebit().getId())) {
                return bedehiCredebit.compareTo(o.getBedehiCredebit());
            } else {
                return etebarCredebit.compareTo(o.getEtebarCredebit());
            }
        }
        else return -1;

    }

    public String getEtebarRemaining() {
        return etebarRemaining;
    }

    public void setEtebarRemaining(String etebarRemaining) {
        this.etebarRemaining = etebarRemaining;
    }

    public String getBedehiRemaining() {
        return bedehiRemaining;
    }

    public void setBedehiRemaining(String bedehiRemaining) {
        this.bedehiRemaining = bedehiRemaining;
    }
}
