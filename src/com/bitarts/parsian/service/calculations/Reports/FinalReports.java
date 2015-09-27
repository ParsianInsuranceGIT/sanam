package com.bitarts.parsian.service.calculations.Reports;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 10, 2011
 * Time: 5:00:32 PM
 */
public class FinalReports {
    private short sen[]=null;
    private short saal[]=null;
    private double haghBimePardaakhtiSaal[]=null;
    private double mablaghSepordeEbtedaayeSaal[]=null;
    private double sarmaayeFotBehHarEllat[]=null;
    private double sarmaayeFotDarAsarHaadeseh[]=null;
    private double sarmaayePusheshEzaafiAmraazKhaas[]=null;
    private double majmuHaghBimePardaakhtiSaal[]=null;
    private double haghBimePusheshHaayeEzaafi[]=null;
    private double majmuHaghBimePusheshHaayeEzaafi[]=null;
    private double maaliyaatBarArzeshAfzude[]=null;
    private double majmuKolMabaaleghPardaakhti[]=null;
    private double arzeshBazKharidBaaSudTazmini15Darsad[]=null;
    private double pishbiniArzeshBazKharidBaaSud20Darsad[]=null;
    private double pishbiniArzeshBazKharidBaaSud22Darsad[]=null;

    private long haghBimePardaakhtiSaalRound[]=null;
    private long mablaghSepordeEbtedaayeSaalRound[]=null;
    private long sarmaayeFotBehHarEllatRound[]=null;
    private long sarmaayeFotDarAsarHaadesehRound[]=null;
    private long sarmaayePusheshEzaafiAmraazKhaasRound[]=null;
    private long majmuHaghBimePardaakhtiSaalRound[]=null;
    private long haghBimePusheshHaayeEzaafiRound[]=null;
    private long majmuHaghBimePusheshHaayeEzaafiRound[]=null;
    private long maaliyaatBarArzeshAfzudeRound[]=null;
    private long majmuKolMabaaleghPardaakhtiRound[]=null;
    private long arzeshBazKharidBaaSudTazmini15DarsadRound[]=null;
    private long pishbiniArzeshBazKharidBaaSud20DarsadRound[]=null;
    private long pishbiniArzeshBazKharidBaaSud22DarsadRound[]=null;

    public FinalReports(int modatBimeNameh){
        sen = new short[modatBimeNameh];
        saal = new short[modatBimeNameh];
        haghBimePardaakhtiSaal=new double[modatBimeNameh];
        sarmaayeFotBehHarEllat=new double[modatBimeNameh];
        sarmaayeFotDarAsarHaadeseh=new double[modatBimeNameh];
        majmuHaghBimePardaakhtiSaal=new double[modatBimeNameh];
        sarmaayePusheshEzaafiAmraazKhaas=new double[modatBimeNameh];
        majmuKolMabaaleghPardaakhti=new double[modatBimeNameh];
        majmuHaghBimePusheshHaayeEzaafi=new double[modatBimeNameh];
        haghBimePusheshHaayeEzaafi=new double[modatBimeNameh];
        maaliyaatBarArzeshAfzude=new double[modatBimeNameh];
        arzeshBazKharidBaaSudTazmini15Darsad=new double[modatBimeNameh];
        pishbiniArzeshBazKharidBaaSud20Darsad=new double[modatBimeNameh];
        pishbiniArzeshBazKharidBaaSud22Darsad=new double[modatBimeNameh];

        haghBimePardaakhtiSaalRound=new long[modatBimeNameh];
        sarmaayeFotBehHarEllatRound=new long[modatBimeNameh];
        sarmaayeFotDarAsarHaadesehRound=new long[modatBimeNameh];
        majmuHaghBimePardaakhtiSaalRound=new long[modatBimeNameh];
        sarmaayePusheshEzaafiAmraazKhaasRound=new long[modatBimeNameh];
        majmuKolMabaaleghPardaakhtiRound=new long[modatBimeNameh];
        majmuHaghBimePusheshHaayeEzaafiRound=new long[modatBimeNameh];
        haghBimePusheshHaayeEzaafiRound=new long[modatBimeNameh];
        maaliyaatBarArzeshAfzudeRound=new long[modatBimeNameh];
        arzeshBazKharidBaaSudTazmini15DarsadRound=new long[modatBimeNameh];
        pishbiniArzeshBazKharidBaaSud20DarsadRound=new long[modatBimeNameh];
        pishbiniArzeshBazKharidBaaSud22DarsadRound=new long[modatBimeNameh];
    }

    public void setSen(int index,short value){sen[index]=value;}

    public void setSaal(int index,short value){saal[index]=value;}

    public void setHaghBimePardaakhtiSaal(int index,double value){
        haghBimePardaakhtiSaal[index]=value;
        haghBimePardaakhtiSaalRound[index]=Math.round(value);
    }

    public void setSarmaayeFotBehHarEllat(int index,double value){
        sarmaayeFotBehHarEllat[index]=value;
        sarmaayeFotBehHarEllatRound[index]=Math.round(value);
    }

    public void setSarmaayeFotDarAsarHaadeseh(int index,double value){
        sarmaayeFotDarAsarHaadeseh[index]=value;
        sarmaayeFotDarAsarHaadesehRound[index]=Math.round(value);
    }

    public void setMajmuHaghBimePardaakhtiSaal(int index,double value){
        majmuHaghBimePardaakhtiSaal[index]=value;
        majmuHaghBimePardaakhtiSaalRound[index]=Math.round(value);
    }

    public void setSarmaayePusheshEzaafiAmraazKhaas(int index,double value){
        sarmaayePusheshEzaafiAmraazKhaas[index]=value;
        sarmaayePusheshEzaafiAmraazKhaasRound[index]=Math.round(value);
    }

    public void setMajmuKolMabaaleghPardaakhti(int index,double value){
        majmuKolMabaaleghPardaakhti[index]=value;
        majmuKolMabaaleghPardaakhtiRound[index]=Math.round(value);
    }

    public void setMajmuHaghBimePusheshHaayeEzaafi(int index,double value){
        majmuHaghBimePusheshHaayeEzaafi[index]=value;
        majmuHaghBimePusheshHaayeEzaafiRound[index]=Math.round(value);
    }

    public void setHaghBimePusheshHaayeEzaafi(int index,double value){
        haghBimePusheshHaayeEzaafi[index]=value;
        haghBimePusheshHaayeEzaafiRound[index]=Math.round(value);
    }

    public void setMaaliyaatBarArzeshAfzude(int index,double value){
        maaliyaatBarArzeshAfzude[index]=value;
        maaliyaatBarArzeshAfzudeRound[index]=Math.round(value);
    }

    public void setArzeshBazKharidBaaSudTazmini15Darsad(int index,double value){
        arzeshBazKharidBaaSudTazmini15Darsad[index]=value;
        arzeshBazKharidBaaSudTazmini15DarsadRound[index]=Math.round(value);
    }

    public void setPishbiniArzeshBazKharidBaaSud20Darsad(int index,double value){
        pishbiniArzeshBazKharidBaaSud20Darsad[index]=value;
        pishbiniArzeshBazKharidBaaSud20DarsadRound[index]=Math.round(value);
    }

    public void setPishbiniArzeshBazKharidBaaSud22Darsad(int index,double value){
        pishbiniArzeshBazKharidBaaSud22Darsad[index]=value;
        pishbiniArzeshBazKharidBaaSud22DarsadRound[index]=Math.round(value);
    }

    //////////////////////////////////////////

    public short[] sen(){return sen;}

    public short[] saal(){return saal;}

    public double[] haghBimePardaakhtiSaal(){return haghBimePardaakhtiSaal;}

    public double[] mablaghSepordeEbtedaayeSaal(){return mablaghSepordeEbtedaayeSaal;}

    public double[] sarmaayeFotBehHarEllat(){return sarmaayeFotBehHarEllat;}

    public double[] sarmaayeFotDarAsarHaadeseh(){return sarmaayeFotDarAsarHaadeseh;}

    public double[] majmuHaghBimePardaakhtiSaal(){return majmuHaghBimePardaakhtiSaal;}

    public double[] sarmaayePusheshEzaafiAmraazKhaas(){return sarmaayePusheshEzaafiAmraazKhaas;}

    public double[] majmuKolMabaaleghPardaakhti(){return majmuKolMabaaleghPardaakhti;}

    public double[] majmuHaghBimePusheshHaayeEzaafi(){return majmuHaghBimePusheshHaayeEzaafi;}

    public double[] haghBimePusheshHaayeEzaafi(){return haghBimePusheshHaayeEzaafi;}

    public double[] maaliyaatBarArzeshAfzude(){return maaliyaatBarArzeshAfzude;}

    public double[] arzeshBazKharidBaaSudTazmini15Darsad(){return arzeshBazKharidBaaSudTazmini15Darsad;}

    public double[] pishbiniArzeshBazKharidBaaSud20Darsad(){return pishbiniArzeshBazKharidBaaSud20Darsad;}

    public double[] pishbiniArzeshBazKharidBaaSud22Darsad(){return pishbiniArzeshBazKharidBaaSud22Darsad;}

     ///////////////////Round//////////////////

    public long[] haghBimePardaakhtiSaalRound(){return haghBimePardaakhtiSaalRound;}

    public long[] mablaghSepordeEbtedaayeSaalRound(){return mablaghSepordeEbtedaayeSaalRound;}

    public long[] sarmaayeFotBehHarEllatRound(){return sarmaayeFotBehHarEllatRound;}

    public long[] sarmaayeFotDarAsarHaadesehRound(){return sarmaayeFotDarAsarHaadesehRound;}

    public long[] majmuHaghBimePardaakhtiSaalRound(){return majmuHaghBimePardaakhtiSaalRound;}

    public long[] sarmaayePusheshEzaafiAmraazKhaasRound(){return sarmaayePusheshEzaafiAmraazKhaasRound;}

    public long[] majmuKolMabaaleghPardaakhtiRound(){return majmuKolMabaaleghPardaakhtiRound;}

    public long[] majmuHaghBimePusheshHaayeEzaafiRound(){return majmuHaghBimePusheshHaayeEzaafiRound;}

    public long[] haghBimePusheshHaayeEzaafiRound(){return haghBimePusheshHaayeEzaafiRound;}

    public long[] maaliyaatBarArzeshAfzudeRound(){return maaliyaatBarArzeshAfzudeRound;}

    public long[] arzeshBazKharidBaaSudTazmini15DarsadRound(){return arzeshBazKharidBaaSudTazmini15DarsadRound;}

    public long[] pishbiniArzeshBazKharidBaaSud20DarsadRound(){return pishbiniArzeshBazKharidBaaSud20DarsadRound;}

    public long[] pishbiniArzeshBazKharidBaaSud22DarsadRound(){return pishbiniArzeshBazKharidBaaSud22DarsadRound;}

    //////////////////////////////////////////

    public short sen(int index){return sen[index];}

    public short saal(int index){return saal[index];}

    public double haghBimePardaakhtiSaal(int index){return haghBimePardaakhtiSaal[index];}

    public double mablaghSepordeEbtedaayeSaal(int index){return mablaghSepordeEbtedaayeSaal[index];}

    public double sarmaayeFotBehHarEllat(int index){return sarmaayeFotBehHarEllat[index];}

    public double sarmaayeFotDarAsarHaadeseh(int index){return sarmaayeFotDarAsarHaadeseh[index];}

    public double majmuHaghBimePardaakhtiSaal(int index){return majmuHaghBimePardaakhtiSaal[index];}

    public double sarmaayePusheshEzaafiAmraazKhaas(int index){return sarmaayePusheshEzaafiAmraazKhaas[index];}

    public double majmuKolMabaaleghPardaakhti(int index){return majmuKolMabaaleghPardaakhti[index];}

    public double majmuHaghBimePusheshHaayeEzaafi(int index){return majmuHaghBimePusheshHaayeEzaafi[index];}

    public double haghBimePusheshHaayeEzaafi(int index){return haghBimePusheshHaayeEzaafi[index];}

    public double maaliyaatBarArzeshAfzude(int index){return maaliyaatBarArzeshAfzude[index];}

    public double arzeshBazKharidBaaSudTazmini15Darsad(int index){return arzeshBazKharidBaaSudTazmini15Darsad[index];}

    public double pishbiniArzeshBazKharidBaaSud20Darsad(int index){return pishbiniArzeshBazKharidBaaSud20Darsad[index];}

    public double pishbiniAArzeshBazKharidBaaSud22Darsad(int index){return pishbiniArzeshBazKharidBaaSud22Darsad[index];}

    private void roundArray(double[] doubleArray,long[] longArray){
        for(int index=0;index<doubleArray.length;index++){
            longArray[index]=Math.round(doubleArray[index]);
        }
    }
}
