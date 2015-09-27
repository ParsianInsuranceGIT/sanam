package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.asnadeSodor.Sanad;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 4/20/15
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
//b-h
public class sooratVaziatMali_new {
    private KhateSanad khatesanad;
    private Sanad sanad;
    private Credebit etebar;
    private Credebit bedehi;

    public sooratVaziatMali_new(KhateSanad khatesanad, Sanad sanad, Credebit etebar, Credebit bedehi) {
        this.khatesanad = khatesanad;
        this.sanad = sanad;
        this.etebar = etebar;
        this.bedehi = bedehi;
    }
    public sooratVaziatMali_new(){
       this.khatesanad=new KhateSanad();
        this.sanad=new Sanad();
        this.etebar=new Credebit();
        this.bedehi=new Credebit();
    }

    public void setKhatesanad(KhateSanad khatesanad) {
        this.khatesanad = khatesanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }

    public void setEtebar(Credebit etebar) {
        this.etebar = etebar;
    }

    public void setBedehi(Credebit bedehi) {
        this.bedehi = bedehi;
    }

    public KhateSanad getKhatesanad() {
        return khatesanad;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public Credebit getEtebar() {
        return etebar;
    }

    public Credebit getBedehi() {
        return bedehi;
    }
}
