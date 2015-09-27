package com.bitarts.parsian.service.calculations.Assumptions;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 9, 2011
 * Time: 1:13:32 PM
 */
public class DiscountAssumptions {
    private double Bimeh_Gari=0.0;
    private double Edari=0.0;
    private double Kaarmozd_Az_Mahal_Hagh_Bimeh=0.0;
    private double Kaarmozd_Az_Mahal_Kaarmozd=0.0;
    private double Hazineh_Vosul=0.0;

    public DiscountAssumptions(  double Bimeh_Gari,
                                 double Edari,
                                 double Kaarmozd_Az_Mahal_Hagh_Bimeh,
                                 double Kaarmozd_Az_Mahal_Kaarmozd,
                                 double Hazineh_Vosul){
        this.Bimeh_Gari=Bimeh_Gari;
        this.Edari=Edari;
        this.Kaarmozd_Az_Mahal_Hagh_Bimeh=Kaarmozd_Az_Mahal_Hagh_Bimeh;
        this.Kaarmozd_Az_Mahal_Kaarmozd=Kaarmozd_Az_Mahal_Kaarmozd;
        this.Hazineh_Vosul=Hazineh_Vosul;

    }

    public double Bimeh_Gari(){
        return Bimeh_Gari/100.0;
    }

    public double Bimeh_Gari_Darsad(){
        return Bimeh_Gari;
    }

    public double Edari(){
        return Edari/100.0;
    }

    public double Edari_Darsad(){
        return Edari;
    }

    public double Kaarmozd_Az_Mahal_Hagh_Bimeh(){
        return Kaarmozd_Az_Mahal_Hagh_Bimeh/100.0;
    }

    public double Kaarmozd_Az_Mahal_Hagh_Bimeh_Darsad(){
        return Kaarmozd_Az_Mahal_Hagh_Bimeh;
    }

    public double Kaarmozd_Az_Mahal_Kaarmozd(){
        return Kaarmozd_Az_Mahal_Kaarmozd/100.0;
    }

    public double Kaarmozd_Az_Mahal_Kaarmozd_Darsad(){
        return Kaarmozd_Az_Mahal_Kaarmozd;
    }

    public double Hazineh_Vosul(){
        return Hazineh_Vosul/100.0;
    }

    public double Hazineh_Vosul_Darsad(){
        return Hazineh_Vosul;
    }
}
