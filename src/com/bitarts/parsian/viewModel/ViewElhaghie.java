package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Ghest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 10, 2011
 * Time: 7:36:12 PM
 */
public class ViewElhaghie {
    private String elamBeMaliShode;
    private List<Ghest> oldGhestList;
    private List<Ghest> newGhestList;
    private String mablagheElhaghie;

    public String getElamBeMaliShode() {
        return elamBeMaliShode;
    }

    public void setElamBeMaliShode(String elamBeMaliShode) {
        this.elamBeMaliShode = elamBeMaliShode;
    }

    public List<Ghest> getOldBedehiList() {
        return oldGhestList;
    }

    public void setOldBedehiList(List<Ghest> oldGhestList) {
        this.oldGhestList = oldGhestList;
    }

    public List<Ghest> getNewBedehiList() {
        return newGhestList;
    }

    public void setNewBedehiList(List<Ghest> newGhestList) {
        this.newGhestList = newGhestList;
    }

    public String getMablagheElhaghie() {
        return mablagheElhaghie;
    }

    public void setMablagheElhaghie(String mablagheElhaghie) {
        this.mablagheElhaghie = mablagheElhaghie;
    }
}
