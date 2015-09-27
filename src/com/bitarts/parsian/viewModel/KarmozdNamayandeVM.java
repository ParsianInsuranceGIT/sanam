package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.karmozd.KarmozdNamayande;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 4/17/14
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class KarmozdNamayandeVM
{
    private Long id;

    private Long namayandeId;
    private String namayandeCodeKargozar;
    private String namayandeName;
    private KarmozdNamayande.State state;
    private Long karmozdId;
    private String karmozdSerial;
    private Long amount;
//    private Credebit credebit;
    private Long bedehiAmount;
    private Long tempNumerator;
    private Long denominator;
    private Long numerator;
    private Long tempDenominator;
    private KarmozdNamayande.Status status;
    private String description;

    public KarmozdNamayandeVM()
    {

    }

    public KarmozdNamayandeVM(Long id, Long karmozdId, KarmozdNamayande.State state,KarmozdNamayande.Status status, Long denominator, Long numerator, String namayandeCodeKargozar, String namayandeName,Long tempNumerator, Long tempDenominator,String description)
    {
        this.id=id;
        this.karmozdId=karmozdId;
        this.state=state;
        this.status=status;
        this.denominator=denominator;
        this.numerator=numerator;
        this.namayandeCodeKargozar=namayandeCodeKargozar;
        this.namayandeName=namayandeName;
        this.tempNumerator=tempNumerator;
        this.tempDenominator=tempDenominator;
        this.description=description;
    }

    public String getStatusFa()
    {
        if (this.status == null)
            return "";
        if (this.status.equals(KarmozdNamayande.Status.ELIGIBLE))
            return "مشمول";
        else//if(this.status.equals(Status.INELIGIBLE))
            return "غیر مشمول";
    }

    public String getStateFa()
    {
        String stateFarsi = "";
        if (!this.state.equals(""))
        {
            if (this.state.equals(KarmozdNamayande.State.ELAM_BE_MALI))
                stateFarsi = "اعلام شده به مالی";
            else// if ELAM_BE_MALI_NASHODE
                stateFarsi = "اعلام نشده به مالی";
        }
        return stateFarsi;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public String getNamayandeCodeKargozar()
    {
        return namayandeCodeKargozar;
    }

    public void setNamayandeCodeKargozar(String namayandeCodeKargozar)
    {
        this.namayandeCodeKargozar = namayandeCodeKargozar;
    }

    public String getNamayandeName()
    {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName)
    {
        this.namayandeName = namayandeName;
    }

    public KarmozdNamayande.State getState()
    {
        return state;
    }

    public void setState(KarmozdNamayande.State state)
    {
        this.state = state;
    }

    public Long getKarmozdId()
    {
        return karmozdId;
    }

    public void setKarmozdId(Long karmozdId)
    {
        this.karmozdId = karmozdId;
    }

    public String getKarmozdSerial()
    {
        return karmozdSerial;
    }

    public void setKarmozdSerial(String karmozdSerial)
    {
        this.karmozdSerial = karmozdSerial;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getBedehiAmount()
    {
        return bedehiAmount;
    }

    public void setBedehiAmount(Long bedehiAmount)
    {
        this.bedehiAmount = bedehiAmount;
    }

    public Long getTempNumerator()
    {
        return tempNumerator;
    }

    public void setTempNumerator(Long tempNumerator)
    {
        this.tempNumerator = tempNumerator;
    }

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

    public Long getTempDenominator()
    {
        return tempDenominator;
    }

    public void setTempDenominator(Long tempDenominator)
    {
        this.tempDenominator = tempDenominator;
    }

    public KarmozdNamayande.Status getStatus()
    {
        return status;
    }

    public void setStatus(KarmozdNamayande.Status status)
    {
        this.status = status;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVosuliAmount()
    {
        return NumberFormat.getInstance().format(this.numerator + this.tempNumerator);
    }

    public String getBargashtiSadereAmount()
    {
        return NumberFormat.getInstance().format(this.denominator + this.tempDenominator);
    }

    public String getPercent()
    {
        Long totalNumerator=this.numerator + this.tempNumerator;
        Long totalDenominator=this.denominator + this.tempDenominator;
        return totalNumerator*100/(totalDenominator==0?1:totalDenominator) + " % ";
    }

}
