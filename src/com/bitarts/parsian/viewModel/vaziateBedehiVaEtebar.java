package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Sanad;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 4/11/15
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */

//b-h
public class vaziateBedehiVaEtebar {
    //  private String bedehi_id;
    private String sanad_CreatedDate;
    private Credebit.Status bedehi_Status;
    private Credebit.VaziyatVosoul bedehi_VosoulState;
    private Sanad.Vaziat vaziateSanad;
    private Long bedehi_RemainingAmount;



    public vaziateBedehiVaEtebar(){

    }
    public vaziateBedehiVaEtebar(String sanad_CreatedDate,Credebit.Status bedehi_Status, Credebit.VaziyatVosoul bedehi_VosoulState, Sanad.Vaziat vaziateSanad,Long bedehi_RemainingAmount) {

        this.sanad_CreatedDate = sanad_CreatedDate;
        this.bedehi_Status = bedehi_Status;
        this.bedehi_VosoulState = bedehi_VosoulState;
        this.vaziateSanad = vaziateSanad;
        this.bedehi_RemainingAmount = bedehi_RemainingAmount;
    }


    public String getSanad_CreatedDate() {
        return sanad_CreatedDate;
    }

    public Credebit.Status getBedehi_Status() {
        return bedehi_Status;
    }

    public Credebit.VaziyatVosoul getBedehi_VosoulState() {
        return bedehi_VosoulState;
    }

    public Sanad.Vaziat getVaziateSanad() {
        return vaziateSanad;
    }


    public void setSanad_CreatedDate(String sanad_CreatedDate) {

        this.sanad_CreatedDate = sanad_CreatedDate;
    }

    public void setBedehi_Status(Credebit.Status bedehi_Status) {
        this.bedehi_Status = bedehi_Status;
    }

    public void setBedehi_VosoulState(Credebit.VaziyatVosoul bedehi_VosoulState) {
        this.bedehi_VosoulState = bedehi_VosoulState;
    }

    public void setVaziateSanad(Sanad.Vaziat vaziateSanad) {
        this.vaziateSanad = vaziateSanad;
    }

    public Long getBedehi_RemainingAmount() {
        return bedehi_RemainingAmount;
    }

    public void setBedehi_RemainingAmount(Long bedehi_RemainingAmount) {

        this.bedehi_RemainingAmount = bedehi_RemainingAmount;
    }
}
