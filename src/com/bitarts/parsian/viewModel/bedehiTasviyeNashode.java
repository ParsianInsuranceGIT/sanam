package com.bitarts.parsian.viewModel;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.asnadeSodor.Credebit;

import java.io.IOException;
import java.text.NumberFormat;
import java.lang.Long;
import java.util.Properties;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 6/27/15
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class bedehiTasviyeNashode
{
    private String namayandeID="";
    private String namayandeName="";
    private String bimenameID="";
    private String bimeGozarID="";
    private String sarresid_date="";
    private String vsodoorID="";
    private String vsodoorName="";
    private Long   mablaghKol=new Long(0);
    private String created_date="";
    private String CreType="";
    private Long   tasvieNashode=new Long(0);
    private Long   sanadNakhorde=new Long(0);
    private Long   bazaryab_sanam_id=new Long(0);
    private int    mohlatsarresid;
    private String finalsarresid;

//    private Credebit credebit;
//    private Long sumOfVosoulNashode;
//    private Integer finalSarresidDate;
//    private Long remainingAmount;

    public bedehiTasviyeNashode(){

    }
    public bedehiTasviyeNashode(String bimenameID, String bimeGozarID, String namayandeID, String namayandeName, String vsodoorID, String vsodoorName, String sarresid, String created_date,Long mablaghKol,Long  sanadNakhorde,Long   tasvieNashode, String CreType,Long bazaryab_sanam_id, int mohlatsarresid){

            this.bimenameID     = bimenameID;
            this.bimeGozarID    = bimeGozarID;
            this.namayandeID    = namayandeID;
            this.namayandeName  = namayandeName;
            this.vsodoorID      = vsodoorID;
            this.vsodoorName    = vsodoorName;
            this.created_date   = created_date;
            this.mablaghKol     = mablaghKol;
            this.sanadNakhorde  = sanadNakhorde;
            this.tasvieNashode  = tasvieNashode;
            this.CreType        = CreType;
            this.bazaryab_sanam_id = bazaryab_sanam_id;
            this.sarresid_date  = sarresid;
            this.mohlatsarresid = mohlatsarresid;
            this.finalsarresid  = DateUtil.addDays(this.sarresid_date, this.mohlatsarresid);
    }

    public String getBimenameID(){
        return this.bimenameID;
    }

    public void setBimenameID(String bimenameID){
        this.bimenameID = bimenameID;
    }

    public String getBimeGozarID(){
        return this.bimeGozarID;
    }

    public String getNamayandeID(){
        return this.namayandeID;
    }
    public String getNamayandeName(){
        return this.namayandeName;
    }
    public void setNamayandeID(String namayandeID){
        this.namayandeID = namayandeID;
    }
    public void setNamayandeName(String namayandeName){
        this.namayandeName = namayandeName;
    }
    public String getVsodoorID(){
        return this.vsodoorID;
    }
    public void setVsodoorID(String vsodoorID){
        this.vsodoorID = vsodoorID;
    }
    public String getVsodoorName(){
        return this.vsodoorName;
    }
    public void setVsodoorName(String vsodoorName){
        this.vsodoorName = vsodoorName;
    }
    public String getSarresid_date(){
        return this.sarresid_date;
    }
    public void setSarresid_date(String sarresid_date){
        this.sarresid_date = sarresid_date;
    }
    public void setBimeGozarID(String bimeGozarID){
        this.bimeGozarID = bimeGozarID;
    }

    public String getCreated_date(){
        return this.created_date;
    }
    public void setCreated_date(String created_date){
        this.created_date = created_date;
    }

    public Long getMablaghKol(){
        return this.mablaghKol;
    }
    public void setMablaghKol(Long mablaghKol){
        this.mablaghKol = mablaghKol;
    }
    public  Long getSanadNakhorde(){
        return sanadNakhorde;
    }
    public void setSanadNakhorde(Long sanadNakhorde){
        this.sanadNakhorde = sanadNakhorde;
    }
    public Long getTasvieNashode(){
        return this.tasvieNashode;
    }
    public void setTasvieNashode(Long tasvieNashode){
        this.tasvieNashode = tasvieNashode;
    }
    public void setCreType(String creType){
        this.CreType = creType;
    }
    public Long getBazaryab_sanam_id(){
        return this.bazaryab_sanam_id ;
    }
    public void setBazaryab_sanam_id(Long bazaryab_sanam_id){
        this.bazaryab_sanam_id = bazaryab_sanam_id;
    }
    public String getCreType(){
        return this.CreType;
    }

    public void setMohlatsarresid(int mohlatsarresid){
        this.mohlatsarresid = mohlatsarresid;
    }
    public int getMohlatsarresid(){
        return this.mohlatsarresid;
    }

    public String getFinalsarresid() {
        return finalsarresid;
    }

    public void setFinalsarresid(String finalsarresid) {
        this.finalsarresid = finalsarresid;
    }
}
