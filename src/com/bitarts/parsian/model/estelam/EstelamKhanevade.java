package com.bitarts.parsian.model.estelam;

import com.bitarts.parsian.model.pishnahad.Pishnehad;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 4/9/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class EstelamKhanevade implements Serializable {
    public EstelamKhanevade(){
        estelamBimeShodes = new EstelamBimeShode[4];
        for(int i=0;i<estelamBimeShodes.length;++i){
            estelamBimeShodes[i]=new EstelamBimeShode();
        }
    }

    private EstelamBimeShode[] estelamBimeShodes;

    private String noeTarh;

    private String mablagheHagheBimeAvalie;

    private String mablagheHagheBimeMonazam;

    private String nahveyePardakht;

    private String nerkheAfzayesheHagheBime;

    private String modateBimeName;

    private String nahveyeKasreHagheBimePusheshhayeEzafi;

    private Pishnehad pishnehad;

    private String nahveyeDaryafteAndukhteEntehayeModateBimeName;

    private String nahveheyeDaryafteMostamari;

    private String nerkhAfzayesheSalaneMostamari;

    private String modatZamaneDaryafteMostamari;

    private String darsadeTakhfifeKarmozdKarmozd;

    private String darsadeTakhfifeVosool;

    private String poosheshMoafiatDarSoorateFoteMoghadam;

    public EstelamBimeShode[] getEstelamBimeShodes() {
        return estelamBimeShodes;
    }

    public void setEstelamBimeShodes(EstelamBimeShode[] estelamBimeShodes) {
        this.estelamBimeShodes = estelamBimeShodes;
    }

    public String getNoeTarh() {
        return noeTarh;
    }

    public void setNoeTarh(String noeTarh) {
        this.noeTarh = noeTarh;
    }

    public String getMablagheHagheBimeAvalie() {
        return mablagheHagheBimeAvalie;
    }

    public void setMablagheHagheBimeAvalie(String mablagheHagheBimeAvalie) {
        this.mablagheHagheBimeAvalie = mablagheHagheBimeAvalie;
    }

    public String getMablagheHagheBimeMonazam() {
        return mablagheHagheBimeMonazam;
    }

    public void setMablagheHagheBimeMonazam(String mablagheHagheBimeMonazam) {
        this.mablagheHagheBimeMonazam = mablagheHagheBimeMonazam;
    }

    public String getNahveyePardakht() {
        return nahveyePardakht;
    }

    public void setNahveyePardakht(String nahveyePardakht) {
        this.nahveyePardakht = nahveyePardakht;
    }

    public String getNerkheAfzayesheHagheBime() {
        return nerkheAfzayesheHagheBime;
    }

    public void setNerkheAfzayesheHagheBime(String nerkheAfzayesheHagheBime) {
        this.nerkheAfzayesheHagheBime = nerkheAfzayesheHagheBime;
    }

    public String getModateBimeName() {
        return modateBimeName;
    }

    public void setModateBimeName(String modateBimeName) {
        this.modateBimeName = modateBimeName;
    }

    public String getNahveyeKasreHagheBimePusheshhayeEzafi() {
        return nahveyeKasreHagheBimePusheshhayeEzafi;
    }

    public void setNahveyeKasreHagheBimePusheshhayeEzafi(String nahveyeKasreHagheBimePusheshhayeEzafi) {
        this.nahveyeKasreHagheBimePusheshhayeEzafi = nahveyeKasreHagheBimePusheshhayeEzafi;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public String getNahveyeDaryafteAndukhteEntehayeModateBimeName() {
        return nahveyeDaryafteAndukhteEntehayeModateBimeName;
    }

    public void setNahveyeDaryafteAndukhteEntehayeModateBimeName(String nahveyeDaryafteAndukhteEntehayeModateBimeName) {
        this.nahveyeDaryafteAndukhteEntehayeModateBimeName = nahveyeDaryafteAndukhteEntehayeModateBimeName;
    }

    public String getNahveheyeDaryafteMostamari() {
        return nahveheyeDaryafteMostamari;
    }

    public void setNahveheyeDaryafteMostamari(String nahveheyeDaryafteMostamari) {
        this.nahveheyeDaryafteMostamari = nahveheyeDaryafteMostamari;
    }

    public String getNerkhAfzayesheSalaneMostamari() {
        return nerkhAfzayesheSalaneMostamari;
    }

    public void setNerkhAfzayesheSalaneMostamari(String nerkhAfzayesheSalaneMostamari) {
        this.nerkhAfzayesheSalaneMostamari = nerkhAfzayesheSalaneMostamari;
    }

    public String getModatZamaneDaryafteMostamari() {
        return modatZamaneDaryafteMostamari;
    }

    public void setModatZamaneDaryafteMostamari(String modatZamaneDaryafteMostamari) {
        this.modatZamaneDaryafteMostamari = modatZamaneDaryafteMostamari;
    }

    public String getDarsadeTakhfifeKarmozdKarmozd() {
        return darsadeTakhfifeKarmozdKarmozd;
    }

    public void setDarsadeTakhfifeKarmozdKarmozd(String darsadeTakhfifeKarmozdKarmozd) {
        this.darsadeTakhfifeKarmozdKarmozd = darsadeTakhfifeKarmozdKarmozd;
    }

    public String getDarsadeTakhfifeVosool() {
        return darsadeTakhfifeVosool;
    }

    public void setDarsadeTakhfifeVosool(String darsadeTakhfifeVosool) {
        this.darsadeTakhfifeVosool = darsadeTakhfifeVosool;
    }

    public String getPoosheshMoafiatDarSoorateFoteMoghadam() {
        return poosheshMoafiatDarSoorateFoteMoghadam;
    }

    public void setPoosheshMoafiatDarSoorateFoteMoghadam(String poosheshMoafiatDarSoorateFoteMoghadam) {
        this.poosheshMoafiatDarSoorateFoteMoghadam = poosheshMoafiatDarSoorateFoteMoghadam;
    }
}
