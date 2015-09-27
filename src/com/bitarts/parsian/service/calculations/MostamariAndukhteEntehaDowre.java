package com.bitarts.parsian.service.calculations;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.MostamariFRs;
import com.bitarts.parsian.service.calculations.Reports.MostamariIRs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nono
 * Date: 12/19/11
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MostamariAndukhteEntehaDowre implements NSPConstants {
    private List<MostamariIRs> mostamariIRsList;
    private List<MostamariFRs> mostamariFRsList;
    private int modateMostamari = 0;

    public List<MostamariFRs> finalReports(Double andukhteEntehaModatBimename, Integer dowreTazminPardakht, String nahvePardakhtMostamari, Integer nerkhAfzayeshSalaneMostamari, String noeMostamariDarkhasti, Integer senMostamariBegir, double nerkhBahreFanni, double[][] lifeTable) {
        modateMostamari = dowreTazminPardakht;
        TaeenMostamariEntehaDowre taeenMostamariEntehaDowre = new TaeenMostamariEntehaDowre();
        this.mostamariIRsList = taeenMostamariEntehaDowre.intermediateReports(andukhteEntehaModatBimename, dowreTazminPardakht, nahvePardakhtMostamari, nerkhAfzayeshSalaneMostamari, noeMostamariDarkhasti, senMostamariBegir, nerkhBahreFanni, lifeTable);
        mostamariFRsList = new ArrayList<MostamariFRs>();
        int yearCounter = 0;
        MostamariIRs mostamariIRsFirst = null;
        MostamariFRs previousMostamariFRs = null;
        String currentDate = DateUtil.getCurrentDate();
        for (MostamariIRs mostamariIRs : mostamariIRsList) {
            if (yearCounter == 0) mostamariIRsFirst = mostamariIRs;
            MostamariFRs fr = new MostamariFRs();
            fr.setSaal(mostamariIRs.getSaal());
            fr.setSen(mostamariIRs.getSen());
            fr.setMablaghMostamari(mostamariIRs.getMablaghMostamari());
            fr.setMablaghMostamariMahane(Math.round((mostamariIRs.getMablaghMostamari() * 1.067) / 12));
            fr.setMablaghMostamariSeMahe(Math.round((mostamariIRs.getMablaghMostamari() * 1.0546) / 4));
            fr.setMablaghMostamariSheshMahe(Math.round((mostamariIRs.getMablaghMostamari() * 1.0362) / 2));
            mostamariFRsList.add(fr);
        }

        return mostamariFRsList;
    }
}
