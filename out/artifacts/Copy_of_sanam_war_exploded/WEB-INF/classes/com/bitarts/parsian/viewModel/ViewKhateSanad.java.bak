package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: s-tayebifar
 * Date: 8/30/15
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Sanad;

import java.io.IOException;
import java.text.NumberFormat;
import java.lang.Long;
import java.lang.Number;
import java.util.Properties;
import java.lang.String;


public class ViewKhateSanad {

    private String shomare_sanad     ="";
    private String zaman_sabt       ="";
    private Sanad.NoeSanad noe_sanad        ;
    private Sanad.Vaziat vaziat           ;
    private String mablagh_khate_sanad="";
    private String bimename         ="";
    private String name_bimegozar   ="";
    private String shenase_pardakht_etebar  = "";
    private String shomare_moshtari_etebar  = "";
    private String noe_etebar               ="";
    private String noe_etebar_str           ="";
    private String shenase_pardakht_bedehi  = "";
    private String shomare_moshtari_bedehi  = "";
    private String noe_bedehi               = "";
    private String noe_bedehi_str           = "";
    private Long   mablagh_etebar           = new Long(0);
    private Long   mablagh_bedehi           = new Long(0);
    private Long   mande_etebar             = new Long(0);
    private Long   mande_bedehi             = new Long(0);
    private String sarresid_date_etebar     ="";
    private String sarresid_date_bedehi     ="";
    private String kode_vahed_sodor_etebar  ="";
    private String name_vahed_sodor_etebar  ="";
    private String kode_vahed_sabt_etebar   ="";
    private String name_vahed_sabt_etebar   ="";
    private String kode_vahed_sodor_bedehi  ="";
    private String name_vahed_sodor_bedehi  ="";
    private String kode_vahed_sabt_bedehi   ="";
    private String name_vahed_sabt_bedehi   ="";
    private String bank                     ="";
    private String tarikh_sanad_bank        ="";
    private String shomare_sanad_bank       ="";
    private String shomare_fish             ="";
    private String serial_check             ="";
    private String kode_vahed_sabt_sanad    ="";
    private String name_vahed_sabt_sanad    ="";
    private String vaziat_str               ="";
    private String noe_sanad_str            ="";
    private Long etebar_id                  =new Long(0);
    private Long bedehi_id                  =new Long(0);
    private String subsystem_name           ="";
    private Long sanad_id                   =new Long(0);

    public ViewKhateSanad(){

    }
    public ViewKhateSanad(
            String shomare_sanad   ,String zaman_sabt   ,  Sanad.NoeSanad noe_sanad    ,   Sanad.Vaziat vaziat      ,
            String mablagh_khate_sanad,  String bimename ,  String name_bimegozar  ,
            String shenase_pardakht_etebar  , String shomare_moshtari_etebar  , String noe_etebar , String noe_etebar_str ,  String shenase_pardakht_bedehi ,
            String shomare_moshtari_bedehi  ,  String noe_bedehi , String noe_bedehi_str , Long   mablagh_etebar  ,
            Long   mablagh_bedehi  , Long   mande_etebar ,Long   mande_bedehi    ,
            String sarresid_date_etebar   , String sarresid_date_bedehi    ,  String kode_vahed_sodor_etebar  ,
            String name_vahed_sodor_etebar , String kode_vahed_sabt_etebar   , String name_vahed_sabt_etebar   , String kode_vahed_sodor_bedehi  ,
            String name_vahed_sodor_bedehi  , String kode_vahed_sabt_bedehi   , String name_vahed_sabt_bedehi  ,
            String bank , String tarikh_sanad_bank , String shomare_sanad_bank  , String shomare_fish       ,
            String serial_check ,String kode_vahed_sabt_sanad , String name_vahed_sabt_sanad ,String vaziat_str , String noe_sanad_str
            , Long etebar_id ,Long  bedehi_id ,String subsystem_name, Long sanad_id ){

        this.shomare_sanad             = shomare_sanad;
        this.zaman_sabt                 = zaman_sabt;
        this.noe_sanad                  = noe_sanad;
        this.vaziat                     = vaziat;
        this.mablagh_khate_sanad        = mablagh_khate_sanad;
        this.bimename                   = bimename;
        this.name_bimegozar             = name_bimegozar;
        this.shenase_pardakht_etebar    = shenase_pardakht_etebar;
        this.shomare_moshtari_etebar    = shomare_moshtari_etebar;
        this.noe_etebar                 = noe_etebar;
        this.noe_etebar_str             = noe_etebar_str;
        this.shenase_pardakht_bedehi    = shenase_pardakht_bedehi;
        this.shomare_moshtari_bedehi    = shomare_moshtari_bedehi;
        this.noe_bedehi                 = noe_bedehi;
        this.noe_bedehi_str             = noe_bedehi_str;
        this.mablagh_etebar             = mablagh_etebar;
        this.mablagh_bedehi             = mablagh_bedehi;
        this.mande_etebar               = mande_etebar;
        this.mande_bedehi               = mande_bedehi;
        this.sarresid_date_etebar       = sarresid_date_etebar;
        this.sarresid_date_bedehi       = sarresid_date_bedehi;
        this.kode_vahed_sodor_etebar    = kode_vahed_sodor_etebar;
        this.name_vahed_sodor_etebar    = name_vahed_sodor_etebar;
        this.kode_vahed_sabt_etebar     = kode_vahed_sabt_etebar;
        this.name_vahed_sabt_etebar     = name_vahed_sabt_etebar;
        this.kode_vahed_sodor_bedehi    = kode_vahed_sodor_bedehi;
        this.name_vahed_sodor_bedehi    = name_vahed_sodor_bedehi;
        this.kode_vahed_sabt_bedehi     = kode_vahed_sabt_bedehi;
        this.name_vahed_sabt_bedehi     = name_vahed_sabt_bedehi;
        this.bank                       = bank;
        this.tarikh_sanad_bank          = tarikh_sanad_bank;
        this.shomare_sanad_bank         = shomare_sanad_bank;
        this.shomare_fish               = shomare_fish;
        this.serial_check               = serial_check;
        this.kode_vahed_sabt_sanad      = kode_vahed_sabt_sanad;
        this.name_vahed_sabt_sanad      = name_vahed_sabt_sanad;
        this.vaziat_str                 = vaziat_str;
        this.noe_sanad_str              = noe_sanad_str;
        this.bedehi_id                  = bedehi_id;
        this.etebar_id                  = etebar_id;
        this.subsystem_name             = subsystem_name;
        this.sanad_id                   = sanad_id;
    }

    public String getShomare_sanad(){
        return this.shomare_sanad;
    }

    public void setShomare_sanad(String shomare_sanad){
        this.shomare_sanad = shomare_sanad;
    }

    public String getZaman_sabt(){
        return this.zaman_sabt;
    }

    public void setZaman_sabt(String zaman_sabt){
        this.zaman_sabt = zaman_sabt;
    }

    public Sanad.NoeSanad getNoe_sanad(){
        return this.noe_sanad;
    }

    public void setNoe_sanad(Sanad.NoeSanad noe_sanad){
        this.noe_sanad = noe_sanad;
    }

    public Sanad.Vaziat getVaziat(){
        return this.vaziat;
    }

    public void setVaziat(Sanad.Vaziat vaziat){
        this.vaziat = vaziat;
    }

    public String getMablagh_khate_sanad(){
        return this.mablagh_khate_sanad;
    }

    public void setMablagh_khate_sanad(String mablagh_khate_sanad){
        this.mablagh_khate_sanad = mablagh_khate_sanad;
    }

    public String getBimename(){
        return this.bimename;
    }

    public void setBimename(String bimename){
        this.bimename = bimename;
    }

    public String getName_bimegozar(){
        return this.name_bimegozar;
    }

    public void setName_bimegozar(String name_bimegozar){
        this.name_bimegozar = name_bimegozar;
    }

    public String getShenase_pardakht_etebar(){
        return this.shenase_pardakht_etebar;
    }

    public void setShenase_pardakht_etebar(String shenase_pardakht_etebar){
        this.shenase_pardakht_etebar = shenase_pardakht_etebar;
    }

    public String getShomare_moshtari_etebar(){
        return this.shomare_moshtari_etebar;
    }

    public void setShomare_moshtari_etebar(String shomare_moshtari_etebar){
        this.shomare_moshtari_etebar = shomare_moshtari_etebar;
    }

    public String getNoe_etebar(){
        return this.noe_etebar;
    }

    public void setNoe_etebar(String noe_etebar){
        this.noe_etebar = noe_etebar;
    }

    public String getShenase_pardakht_bedehi(){
        return this.shenase_pardakht_etebar;
    }

    public void setShenase_pardakht_bedehi(String shenase_pardakht_bedehi){
        this.shenase_pardakht_bedehi = shenase_pardakht_bedehi;
    }

    public String getShomare_moshtari_bedehi(){
        return this.shomare_moshtari_bedehi;
    }

    public void setShomare_moshtari_bedehi(String shomare_moshtari_bedehi){
        this.shomare_moshtari_bedehi = shomare_moshtari_bedehi;
    }

    public String getNoe_bedehi(){
        return this.noe_bedehi;
    }

    public void setNoe_bedehi(String noe_bedehi){
        this.noe_bedehi = noe_bedehi;
    }

    public Long getMablagh_etebar(){
        return this.mablagh_etebar;
    }

    public void setMablagh_etebar(Long mablagh_etebar){
        this.mablagh_etebar = mablagh_etebar;
    }

    public Long getMablagh_bedehi(){
        return this.mablagh_etebar;
    }

    public void setMablagh_bedehi(Long mablagh_bedehi){
        this.mablagh_bedehi = mablagh_bedehi;
    }

    public Long getMande_etebar(){
        return this.mande_etebar;
    }

    public void setMande_etebar(Long mande_etebar){
        this.mande_etebar = mande_etebar;
    }

    public Long getMande_bedehi(){
        return this.mande_bedehi;
    }

    public void setMande_bedehi(Long mande_bedehi){
        this.mande_bedehi = mande_bedehi;
    }

    public String getSarresid_date_etebar(){
        return this.sarresid_date_etebar;
    }

    public void setSarresid_date_etebar(String sarresid_date_etebar){
        this.sarresid_date_etebar = sarresid_date_etebar;
    }

    public String getSarresid_date_bedehi(){
        return this.sarresid_date_bedehi;
    }

    public void setSarresid_date_bedehi(String sarresid_date_bedehi){
        this.sarresid_date_bedehi = sarresid_date_bedehi;
    }

    public String getKode_vahed_sodor_etebar(){
        return this.kode_vahed_sodor_etebar;
    }

    public void setKode_vahed_sodor_etebar(String kode_vahed_sodor_etebar){
        this.kode_vahed_sodor_etebar = kode_vahed_sodor_etebar;
    }

    public String getName_vahed_sodor_etebar(){
        return this.name_vahed_sodor_etebar;
    }

    public void setName_vahed_sodor_etebar(String name_vahed_sodor_etebar){
        this.name_vahed_sodor_etebar = name_vahed_sodor_etebar;
    }

    public String getKode_vahed_sabt_etebar(){
        return this.kode_vahed_sabt_etebar;
    }

    public void setKode_vahed_sabt_etebar(String kode_vahed_sabt_etebar){
        this.kode_vahed_sabt_etebar = kode_vahed_sabt_etebar;
    }

    public String getName_vahed_sabt_etebar(){
        return this.name_vahed_sabt_etebar;
    }

    public void setName_vahed_sabt_etebar(String name_vahed_sabt_etebar){
        this.name_vahed_sabt_etebar = name_vahed_sabt_etebar;
    }


    public String getKode_vahed_sodor_bedehi(){
        return this.kode_vahed_sodor_bedehi;
    }

    public void setKode_vahed_sodor_bedehi(String kode_vahed_sodor_bedehi){
        this.kode_vahed_sodor_bedehi = kode_vahed_sodor_bedehi;
    }

    public String getName_vahed_sodor_bedehi(){
        return this.name_vahed_sodor_bedehi;
    }

    public void setName_vahed_sodor_bedehi(String name_vahed_sodor_bedehi){
        this.name_vahed_sodor_bedehi = name_vahed_sodor_bedehi;
    }

    public String getKode_vahed_sabt_bedehi(){
        return this.kode_vahed_sabt_bedehi;
    }

    public void setKode_vahed_sabt_bedehi(String kode_vahed_sabt_bedehi){
        this.kode_vahed_sabt_bedehi = kode_vahed_sabt_bedehi;
    }

    public String getName_vahed_sabt_bedehi(){
        return this.name_vahed_sabt_bedehi;
    }

    public void setName_vahed_sabt_bedehi(String name_vahed_sabt_bedehi){
        this.name_vahed_sabt_bedehi = name_vahed_sabt_bedehi;
    }

    public void setBank(String bank){
        this.bank = bank;
    }

    public String getBank(){
        return this.bank;
    }

    public void setTarikh_sanad_bank(String tarikh_sanad_bank){
        this.tarikh_sanad_bank = tarikh_sanad_bank;
    }

    public String getTarikh_sanad_bank(){
        return this.tarikh_sanad_bank;
    }

    public void setShomare_sanad_bank(String shomare_sanad_bank){
        this.shomare_sanad_bank = shomare_sanad_bank;
    }

    public String getShomare_sanad_bank(){
        return this.shomare_sanad_bank;
    }

    public void setShomare_fish(String shomare_fish){
        this.shomare_fish = shomare_fish;
    }

    public String getShomare_fish(){
        return this.shomare_fish;
    }

    public void setSerial_check(String serial_check){
        this.serial_check = serial_check;
    }

    public String getSerial_check(){
        return this.serial_check;
    }

    public void setKode_vahed_sabt_sanad(String kode_vahed_sabt_sanad){
        this.kode_vahed_sabt_sanad = kode_vahed_sabt_sanad;
    }

    public String getKode_vahed_sabt_sanad(){
        return this.kode_vahed_sabt_sanad;
    }

    public void setName_vahed_sabt_sanad(String name_vahed_sabt_sanad){
        this.name_vahed_sabt_sanad = name_vahed_sabt_sanad;
    }

    public String getName_vahed_sabt_sanad(){
        return this.name_vahed_sabt_sanad;
    }

    public void setVaziat_str(String vaziat_str){
        this.vaziat_str = vaziat_str;
    }

    public String getVaziat_str(){
        return this.vaziat_str;
    }

    public void setNoe_sanad_str(String noe_sanad_str){
        this.noe_sanad_str = noe_sanad_str;
    }

    public String getNoe_sanad_str(){
        return this.noe_sanad_str;
    }

    public void setNoe_etebar_str(String noe_etebar_str){
        this.noe_etebar_str = noe_etebar_str;
    }

    public String getNoe_etebar_str(){
        return this.noe_etebar_str;
    }

    public void setNoe_bedehi_str(String noe_bedehi_str){
        this.noe_bedehi_str = noe_bedehi_str;
    }

    public String getNoe_bedehi_str(){
        return this.noe_bedehi_str;
    }

    public void setBedehi_id(Long bedehi_id){
        this.bedehi_id = bedehi_id;
    }

    public Long getBedehi_id(){
        return this.bedehi_id;
    }

    public void setEtebar_id(Long etebar_id){
        this.etebar_id = etebar_id;
    }

    public Long getEtebar_id(){
        return this.etebar_id;
    }

    public void setSubsystem_name(String subsystem_name){
        this.subsystem_name = subsystem_name;
    }

    public String getSubsystem_name(){
        return this.subsystem_name;
    }

    public void setSanad_id (Long sanad_id){
        this.sanad_id = sanad_id;
    }

    public Long getSanad_id(){
        return this.sanad_id;
    }
}
