package com.bitarts.parsian.model.karmozd;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 4/30/13
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_karmozd_namayande")
public class KarmozdNamayande
{
    public static enum State
    {
        ELAM_BE_MALI_NASHODE,
        ELAM_BE_MALI
    }

    public static enum Status
    {
        INELIGIBLE,
        ELIGIBLE
    }

    public KarmozdNamayande()
    {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;

    @OneToMany(mappedBy = "karmozdNamayande" )
    private List<KarmozdGhest> karmozdGhests;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karmozd_id")
    private Karmozd karmozd;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "credebit_id")
    private Credebit credebit;

    @OneToMany(mappedBy = "karmozdNamayande")
    private List<KarmozdTadilat> karmozdTadilatList;

    @Column(name="bedehi_Amount")
    private Long bedehiAmount;

    @Column(name="temp_numerator")
    private Long tempNumerator;

    @Column(name="denominator")
    private Long denominator;

    @Column(name="numerator")
    private Long numerator;

    @Column(name="temp_denominator")
    private Long tempDenominator;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column (name="description", length = 1000)
    private String description;

    public Long getDenominator()
    {
        return denominator;
    }

    public void setDenominator(Long denominator)
    {
        this.denominator = denominator;
    }

    public Long getNumerator()
    {
        return numerator;
    }

    public void setNumerator(Long numerator)
    {
        this.numerator = numerator;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getTempNumerator()
    {
        return tempNumerator;
    }

    public void setTempNumerator(Long tempNumerator)
    {
        this.tempNumerator = tempNumerator;
    }

    public Long getTempDenominator()
    {
        return tempDenominator;
    }

    public void setTempDenominator(Long tempDenominator)
    {
        this.tempDenominator = tempDenominator;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getStatusFa()
    {
        if (this.status==null)
            return "";
        if(this.status.equals(Status.ELIGIBLE))
            return "مشمول";
        else//if(this.status.equals(Status.INELIGIBLE))
            return "غیر مشمول";
    }

    public Long getBedehiAmount()
    {
        return bedehiAmount;
    }

    public void setBedehiAmount(Long bedehiAmount)
    {
        this.bedehiAmount = bedehiAmount;
    }

    public String getBedehiAmountString()
    {
        if(this.bedehiAmount!=null)
            return NumberFormat.getInstance().format(this.bedehiAmount);
        return "0";
    }



    public String getTadiliAmountString()
    {
        long totalAmount=0l;
        for(KarmozdGhest kt : karmozdGhests)
        {
            if(kt.getType().equals(KarmozdGhest.Type.TADILI))
            totalAmount+= kt.getKarmozdAmount();
        }
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getAmountBlack()
    {
        long totalAmount=getAmount()-Long.parseLong(getAmountNoBlack().replaceAll(",","").trim());
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getAmountNoBlack()
    {
        long totalAmount=amount;
        for(KarmozdGhest kg : karmozdGhests)
        {
            if(kg.getBlackList()!=null)
            {
                totalAmount -= kg.getKarmozdAmount();
            }
        }
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getAmountForPayment()
    {
        long totalAmount=Long.parseLong(getFinalAmountNoBlack().replaceAll(",","").trim());
        for(KarmozdGhest kg :karmozdGhests)
        {
            if(kg.getType() != null && kg.getType().equals(KarmozdGhest.Type.CODE_MOVAGHAT) && kg.getBlackList() == null)
            {
                totalAmount -= kg.getKarmozdAmount();
            }
        }
        return NumberFormat.getInstance().format(totalAmount);
    }
    public String getAmountForPaymentWithMaliat()
    {
        long totalAmount=Long.parseLong(getAmountForPayment().replaceAll(",", "").trim());
        if(totalAmount>0)
        {
//            totalAmount = KarmozdCalculate.calMaliatmaliat(totalAmount);
//            totalAmount = KarmozdCalculate.calMaliatTaklifi(totalAmount);
            totalAmount = KarmozdCalculate.calMaliat(totalAmount,this.getKarmozd().getTaTarikhVosoli());
            return NumberFormat.getInstance().format(totalAmount);
        }

        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getFinalAmount()
    {
        long totalAmount=this.amount;
        totalAmount+= Long.parseLong(getTadiliAmountString().replaceAll(",","").trim());
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getMablaghNahaei()
    {
        return NumberFormat.getInstance().format(Long.parseLong(getAmountForPaymentWithMaliat().replaceAll(",",""))- Long.parseLong(getBedehiAmountString().replaceAll(",", "")));
    }

//    public String getTadiliAmountBlack()
//    {
//        long totalAmount=Long.parseLong(getTadiliAmountString().replaceAll(",","").trim());
//        for(KarmozdTadilat kt : karmozdTadilatList)
//        {
//            if(kt.getBlackList()== null)
//            {
//                totalAmount -= Long.parseLong(kt.getXlMablagh().replaceAll(",","").trim());
//            }
//        }
//        return NumberFormat.getInstance().format(totalAmount);
//    }

//    public String getTadiliAmountNoBlack()
//    {
//        long totalAmount=Long.parseLong(getTadiliAmountString().replaceAll(",","").trim());
//        for(KarmozdTadilat kt : karmozdTadilatList)
//        {
//            if(kt.getBlackList()!= null)
//            {
//                totalAmount -= Long.parseLong(kt.getXlMablagh().replaceAll(",","").trim());
//            }
//        }
//        return NumberFormat.getInstance().format(totalAmount);
//    }

    public String getFinalAmountNoBlack()
    {
        long totalAmount=Long.parseLong(getAmountNoBlack().replaceAll(",","").trim());
//        for(KarmozdTadilat kt : karmozdTadilatList)
//        {
//            if(kt.getBlackList()== null)
//                totalAmount += Long.parseLong(kt.getXlMablagh().replaceAll(",","").trim());
//        }
        return NumberFormat.getInstance().format(totalAmount);
    }



    public List<KarmozdTadilat> getKarmozdTadilatList()
    {
        return karmozdTadilatList;
    }

    public void setKarmozdTadilatList(List<KarmozdTadilat> karmozdTadilatList)
    {
        this.karmozdTadilatList = karmozdTadilatList;
    }

    public Credebit getCredebit()
    {
        return credebit;
    }

    public void setCredebit(Credebit credebit)
    {
        this.credebit = credebit;
    }

    public String getAmountPardakhtiString()
    {
        Long temp = amount;
        for(KarmozdGhest kt : karmozdGhests)
        {
            if(kt.getType().equals(KarmozdGhest.Type.TADILI))
            {
                temp-=kt.getKarmozdAmount();
            }
        }
        return NumberFormat.getInstance().format(temp);
    }

    public String getAmountString()
    {
        return NumberFormat.getInstance().format(amount);
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }

    public Karmozd getKarmozd()
    {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd)
    {
        this.karmozd = karmozd;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Namayande getNamayande()
    {
        return namayande;
    }

    public void setNamayande(Namayande namayande)
    {
        this.namayande = namayande;
    }

    public List<KarmozdGhest> getKarmozdGhests()
    {
        return karmozdGhests;
    }

    public void setKarmozdGhests(List<KarmozdGhest> karmozdGhests)
    {
        this.karmozdGhests = karmozdGhests;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public String getStateFarsi()
    {
        String stateFarsi="";
        if(!this.state.equals(""))
        {
            if(this.state.equals(State.ELAM_BE_MALI))
                stateFarsi="اعلام شده به مالی";
            else// if ELAM_BE_MALI_NASHODE
                stateFarsi="اعلام نشده به مالی";
        }
        return stateFarsi;
    }
}
