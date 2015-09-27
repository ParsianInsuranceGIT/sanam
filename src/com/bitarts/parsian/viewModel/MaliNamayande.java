package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.Namayande;

public class MaliNamayande {
    private Long elameBeMaliHaghBime;
    private Long elameBeMaliBargashti;
    private Long khalesBedehi;
    private Long mablaghVosoulShode;
    private Namayande namayande;


    public MaliNamayande()
    {

    }

    public MaliNamayande(Long elameBeMaliHaghBime,Long elameBeMaliBargashti,Long mablaghVosoulShode, Namayande namayande)
    {
        this.elameBeMaliHaghBime = elameBeMaliHaghBime;
        this.elameBeMaliBargashti = elameBeMaliBargashti;
        this.khalesBedehi = elameBeMaliHaghBime - elameBeMaliBargashti;
        this.mablaghVosoulShode = mablaghVosoulShode;
        this.namayande = namayande;
    }

    public Long getElameBeMaliHaghBime() {
        return elameBeMaliHaghBime;
    }

    public void setElameBeMaliHaghBime(Long elameBeMaliHaghBime) {
        this.elameBeMaliHaghBime = elameBeMaliHaghBime;
    }

    public Long getElameBeMaliBargashti() {
        return elameBeMaliBargashti;
    }

    public void setElameBeMaliBargashti(Long elameBeMaliBargashti) {
        this.elameBeMaliBargashti = elameBeMaliBargashti;
    }

    public Long getKhalesBedehi() {
        return khalesBedehi;
    }

    public void setKhalesBedehi(Long khalesBedehi) {
        this.khalesBedehi = khalesBedehi;
    }

    public Long getMablaghVosoulShode() {
        return mablaghVosoulShode;
    }

    public void setMablaghVosoulShode(Long mablaghVosoulShode) {
        this.mablaghVosoulShode = mablaghVosoulShode;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }
}
