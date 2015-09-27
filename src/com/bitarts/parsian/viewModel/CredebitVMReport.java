package com.bitarts.parsian.viewModel;
import java.util.List;
/**
 * User: a.sabzechian
 * Date: 7/1/14
 * Time: 4:24 PM
 */
public class CredebitVMReport {


    private List<CredebitBimenameVM>     credebitVM2list;



    private String shomarebimename;
    private String rcptName;

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public String getShomarebimename() {
        return shomarebimename;
    }

    public void setShomarebimename(String shomarebimename) {
        this.shomarebimename = shomarebimename;
    }

    public List<CredebitBimenameVM> getCredebitVM2list() {
        return credebitVM2list;
    }

    public void setCredebitVM2list(List<CredebitBimenameVM> credebitVM2list) {
        this.credebitVM2list = credebitVM2list;
    }


}