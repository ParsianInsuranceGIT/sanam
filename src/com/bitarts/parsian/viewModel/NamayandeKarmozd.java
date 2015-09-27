package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.asnadeSodor.Credebit;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/23/12
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class NamayandeKarmozd {
    private Namayande namayande;
    private Credebit credebit;
    private String amount;

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public Credebit getCredebit() {
        return credebit;
    }

    public void setCredebit(Credebit credebit) {
        this.credebit = credebit;
    }

    public String getAmount() {
        if (amount == null) amount = "0";
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
