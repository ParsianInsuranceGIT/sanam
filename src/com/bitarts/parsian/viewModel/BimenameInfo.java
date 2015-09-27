package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Elhaghiye;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 3/5/14
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class BimenameInfo {

    private Bimename bimename;
    private int salTaghsit;
    private boolean elhaghiyeDaemha = false;

    public BimenameInfo(Bimename bimename) {
        this.bimename = bimename;
    }

    public HashMap<Integer, String> getTaghsitDates() {
        HashMap<Integer, String> taghsitDates = new HashMap<Integer, String>();
        if (bimename != null && bimename.getGhestBandiList() != null && bimename.getGhestBandiList().size() > 0)
            for(GhestBandi gb : bimename.getGhestBandiList()) {
                taghsitDates.put(gb.getSaleBimeiInt(), gb.getTarikheTaghsit());
            }
        return taghsitDates;
    }

    private ArrayList<Elhaghiye> getElhaghieTaghirs() {
        ArrayList<Elhaghiye> elhaghiyes = new ArrayList<Elhaghiye>();
        if (bimename != null && bimename.getElhaghiyeha() != null && bimename.getElhaghiyeha().size() > 0)
            for (Elhaghiye e : bimename.getElhaghiyeha()) {
                if (e.getElhaghiyeType() == Elhaghiye.ElhaghiyeType.TAGHYIRAT) {
                    if(elhaghiyeDaemha) {
                        if(e.getState().getId().equals(Constant.ELHAGHIYE_FINAL_STATE)) {
                            elhaghiyes.add(e);
                        }
                    } else {
                        elhaghiyes.add(e);
                    }
                }
            }
        return elhaghiyes;
    }

    public String getLastElhaghieTaghirAsarDate() {
        ArrayList<Elhaghiye> elhaghiyeTaghirs = getElhaghieTaghirs();
        if(elhaghiyeTaghirs.size() == 0) return "9999/99/99";
        String lastDate = "1300/01/01";
        for(Elhaghiye e : elhaghiyeTaghirs) {
            if(DateUtil.isGreaterThan(e.getTarikhAsar(), lastDate))
                lastDate = e.getTarikhAsar();
        }
        return lastDate;
    }

    public String getLastElhaghieTaghirCreatedDate() {
        ArrayList<Elhaghiye> elhaghiyeTaghirs = getElhaghieTaghirs();
        if (elhaghiyeTaghirs.size() == 0) return "9999/99/99";
        String lastDate = "1300/01/01";
        for (Elhaghiye e : elhaghiyeTaghirs) {
            if (DateUtil.isGreaterThan(e.getCreatedDate(), lastDate))
                lastDate = e.getCreatedDate();
        }
        return lastDate;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public int getSalTaghsit() {
        return salTaghsit;
    }

    public void setSalTaghsit(int salTaghsit) {
        this.salTaghsit = salTaghsit;
    }

    public boolean isElhaghiyeDaemha() {
        return elhaghiyeDaemha;
    }

    public void setElhaghiyeDaemha(boolean elhaghiyeDaemha) {
        this.elhaghiyeDaemha = elhaghiyeDaemha;
    }
}
