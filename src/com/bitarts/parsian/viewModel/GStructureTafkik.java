package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/8/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class GStructureTafkik {
    private String name;
    private String vaziat;
    private Long tedad;


    public GStructureTafkik(String name, String vaziat, Long tedad) {
        this.name = name;
        this.vaziat = vaziat;
        this.tedad = tedad;
    }

    public GStructureTafkik() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaziat() {
        return vaziat;
    }

    public void setVaziat(String vaziat) {
        this.vaziat = vaziat;
    }

    public Long getTedad() {
        return tedad;
    }

    public void setTedad(Long tedad) {
        this.tedad = tedad;
    }
}
