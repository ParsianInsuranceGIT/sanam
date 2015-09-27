package com.bitarts.parsian.service.calculations;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.util.OmrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Apr 15, 2011
 * Time: 6:47:01 PM
 */
public class MohasebateTeoriView {
    public static final int PERIOD_COUNT_IN_MAHANEH = 12;
    public static final int PERIOD_COUNT_IN_3MAHE = 4;
    public static final int PERIOD_COUNT_IN_6MAHE = 2;
    public static final int PERIOD_COUNT_IN_SAALANEH = 1;


    public List<TaghsitReport> convertViewToCustomPeriod(List<TaghsitReport> taghsitReports, int periodCountInOneYear){
        List<TaghsitReport> convertedTaghsitReports = new ArrayList<TaghsitReport>();
        String currentDate = DateUtil.getCurrentDate();

        for (TaghsitReport taghsitReport : taghsitReports) {
            for(int i = 0 ; i < periodCountInOneYear ; i++){
                TaghsitReport newTaghsitReport = new TaghsitReport();

                newTaghsitReport.setTarikh(DateUtil.addMonth(currentDate, periodCountInOneYear));
                newTaghsitReport.setSaal(taghsitReport.getSaal());
                newTaghsitReport.setHaghBimePardaakhtiSaal(taghsitReport.getHaghBimePardaakhtiSaal()/((double )periodCountInOneYear));
                newTaghsitReport.setKaarmozd(taghsitReport.getKaarmozd()/((double )periodCountInOneYear));
                newTaghsitReport.setHazineBimeGari(taghsitReport.getHazineBimeGari()/((double )periodCountInOneYear));
                newTaghsitReport.setHazineEdari(taghsitReport.getHazineEdari()/((double )periodCountInOneYear));
                newTaghsitReport.setHazineVosul(taghsitReport.getHazineVosul()/((double )periodCountInOneYear));
                newTaghsitReport.setHaghBimeKhaalesFotYekja(taghsitReport.getHaghBimeKhaalesFotYekja()/((double )periodCountInOneYear));
                newTaghsitReport.setSarmaayeFotBehHarEllat(taghsitReport.getSarmaayeFotBehHarEllat()/((double )periodCountInOneYear));
                newTaghsitReport.setSarmaayeFotDarAsarHaadeseh(taghsitReport.getSarmaayeFotDarAsarHaadeseh()/((double )periodCountInOneYear));
                newTaghsitReport.setSarmaayePusheshEzaafiAmraazKhaas(taghsitReport.getSarmaayePusheshEzaafiAmraazKhaas()/((double )periodCountInOneYear));
                newTaghsitReport.setHaghBimePusheshHaayeEzaafi(taghsitReport.getHaghBimePusheshHaayeEzaafi()/((double )periodCountInOneYear));

                convertedTaghsitReports.add(newTaghsitReport);
            }
        }
        return convertedTaghsitReports;
    }
    public List<TaghsitReport> convertViewToCustomPeriod_saleBimei(String tarikh, TaghsitReport taghsitReport, int periodCountInOneYear, Pishnehad pishnehad){
        List<TaghsitReport> convertedTaghsitReports = new ArrayList<TaghsitReport>();
        tarikh = DateUtil.addYear(tarikh, taghsitReport.getSaal() - 1);
        int premium = (int) (taghsitReport.getfRs().getiRs().getHaghBimePardaakhti() + taghsitReport.getMaliat()
                + taghsitReport.getMajmooHaghBimePoosheshhayeEzafi());
        if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
            premium = (int) (taghsitReport.getfRs().getiRs().getHaghBimePardaakhti());
        }
        int installment = OmrUtil.rondPardakhti(OmrUtil.rondPardakhti(premium) / periodCountInOneYear);
        int lastInstallment = OmrUtil.rondPardakhti(premium) - (periodCountInOneYear - 1) * installment;
        int kasrHazine =  premium - OmrUtil.rondPardakhti(premium);
        int majmooeAghsat = (periodCountInOneYear - 1) * installment + lastInstallment;
        if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
            majmooeAghsat = taghsitReport.getfRs().getiRs().getHaghBimePardaakhti()>0? (int) taghsitReport.getfRs().getiRs().getHaghBimePardaakhti():1;
        }
        for(int i = 0 ; i < periodCountInOneYear ; i++){
            TaghsitReport newTaghsitReport = new TaghsitReport();
            newTaghsitReport.setTarikh(tarikh);
            tarikh = DateUtil.addMonth(tarikh, 12/periodCountInOneYear);
            int ghestAmount;
//            if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
//                ghestAmount = (int)taghsitReport.getfRs().getiRs().getHaghBimePardaakhti() / periodCountInOneYear;
//                kasrHazine = 0;
//            } else {
//                if(i == periodCountInOneYear - 1) {
//                    ghestAmount = lastInstallment;
//                } else {
//                    ghestAmount = installment;
//                }
//            }
            if (i == periodCountInOneYear - 1) {
                ghestAmount = lastInstallment;
            } else {
                ghestAmount = installment;
            }
            newTaghsitReport.setHaghBimeAvvalie(0);
            if(taghsitReport.getSaal() == taghsitReport.getfRs().getiRs().getSaalEmalHaghBimeAvvalie()) {
                newTaghsitReport.setHaghBimePardaakhtiSaal(ghestAmount);
                if(i == 0) {
                    newTaghsitReport.setHaghBimePardaakhtiSaal(ghestAmount + taghsitReport.getHaghBimeAvvalie());
                    newTaghsitReport.setHaghBimeAvvalie(Math.round(taghsitReport.getHaghBimeAvvalie()));
                    majmooeAghsat += taghsitReport.getHaghBimeAvvalie();
                }
            } else {
                newTaghsitReport.setHaghBimePardaakhtiSaal(ghestAmount);
            }
            newTaghsitReport.setGhestAmount((long)newTaghsitReport.getHaghBimePardaakhtiSaal());
            double zaribNesbat = newTaghsitReport.getHaghBimePardaakhtiSaal() / (double)majmooeAghsat;
            // dar bimenamehaye yekja taghsite salhaye bad
            if(zaribNesbat == 0) zaribNesbat = 1;
            newTaghsitReport.setSaal(taghsitReport.getSaal());
            newTaghsitReport.setMaliat(Math.round(taghsitReport.getMaliat() * zaribNesbat));
            newTaghsitReport.setHaghBimePusheshHaayeEzaafi(Math.round(taghsitReport.getHaghBimePusheshHaayeEzaafi() * zaribNesbat));
            newTaghsitReport.setMajmooHaghBimePoosheshhayeEzafi(Math.round(taghsitReport.getMajmooHaghBimePoosheshhayeEzafi() * zaribNesbat));
            newTaghsitReport.setKaarmozd(Math.round(taghsitReport.getKaarmozd() * taghsitReport.getZaribNahveyePardakht() * zaribNesbat));
            newTaghsitReport.setHazineBimeGari(Math.round(taghsitReport.getHazineBimeGari() * taghsitReport.getZaribNahveyePardakht() * zaribNesbat));
            newTaghsitReport.setHazineEdari(Math.round(taghsitReport.getHazineEdari() * taghsitReport.getZaribNahveyePardakht() * zaribNesbat));
            newTaghsitReport.setHazineVosul(Math.round(taghsitReport.getHazineVosul() * taghsitReport.getZaribNahveyePardakht() * zaribNesbat));
            newTaghsitReport.setHaghBimeKhaalesFotYekja(Math.round(taghsitReport.getHaghBimeKhaalesFotYekja() * taghsitReport.getZaribNahveyePardakht() * zaribNesbat));
            newTaghsitReport.setSarmaayeFotBehHarEllat(Math.round(taghsitReport.getSarmaayeFotBehHarEllat() * zaribNesbat));
            newTaghsitReport.setSarmaayeFotDarAsarHaadeseh(Math.round(taghsitReport.getSarmaayeFotDarAsarHaadeseh() * zaribNesbat));
            newTaghsitReport.setSarmaayePusheshEzaafiAmraazKhaas(Math.round(taghsitReport.getSarmaayePusheshEzaafiAmraazKhaas() * zaribNesbat));
            newTaghsitReport.setHaghBimePusheshHaayeEzaafi(Math.round(taghsitReport.getHaghBimePusheshHaayeEzaafi() * zaribNesbat));
            newTaghsitReport.setHaghBimePoosheshAmraz(Math.round(taghsitReport.getHaghBimePoosheshAmraz() * zaribNesbat));
            newTaghsitReport.setHaghBimePoosheshHadese(Math.round(taghsitReport.getHaghBimePoosheshHadese() * zaribNesbat));
            newTaghsitReport.setHaghBimePoosheshNaghsOzv(Math.round(taghsitReport.getHaghBimePoosheshNaghsOzv() * zaribNesbat));
            newTaghsitReport.setHaghBimePoosheshMoafiat(Math.round(taghsitReport.getHaghBimePoosheshMoafiat() * zaribNesbat));
            newTaghsitReport.setZaribNahveyePardakht(taghsitReport.getZaribNahveyePardakht());
            newTaghsitReport.setGhestAmount(Math.round(newTaghsitReport.getHaghBimePardaakhtiSaal()));
            newTaghsitReport.setKasrHazine(kasrHazine);
            convertedTaghsitReports.add(newTaghsitReport);
        }
        return convertedTaghsitReports;
    }

}
