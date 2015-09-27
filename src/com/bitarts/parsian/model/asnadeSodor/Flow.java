package com.bitarts.parsian.model.asnadeSodor;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 9/27/11
 * Time: 3:26 PM
 */
public class Flow {
    public static enum Type {
        GHEST,MOSHAREKAT,GHEST_VAM,PARDAKHT_GHEST, HAZINEHA_MOSBAT, HAZINEHA_MANFI, HAZINEHA
    }
    private double amount;
    private Type type;
    private String date;
    public Flow(double amount, Type type, String date){
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
