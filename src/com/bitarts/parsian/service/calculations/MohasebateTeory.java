package com.bitarts.parsian.service.calculations;

import com.bitarts.parsian.action.PrintAction;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Darkhast;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.utility.ElhaghiyeSaleBimeIntComparator;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.viewModel.PishnehadReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/14/11
 * Time: 4:36 PM
 */

public class MohasebateTeory implements NSPConstants {
    private PishnehadReport pishnehadReport = new PishnehadReport();

    public List<TaghsitReport> taghsitReport(Pishnehad pish, boolean daemha, int saleTaghsit) throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        Bimename bimename = null;
        boolean correctCalculation = true;
        pishnehadReport.setFRsList(new ArrayList<FRs>());
        pishnehadReport.setSaleTaghsit(saleTaghsit);
        if(pish.getBimename() == null) {
            pishnehadReport.setPishnehad(pish);
            int moddat = Integer.parseInt(pish.getEstelam().getModat_bimename());
            pishnehadReport.setCurrentElhaghiye(null);
            correctCalculation = correctCalculation && PrintAction.calculate(pishnehadReport, moddat);
        } else {
            pishnehadReport.setPishnehad(pish.getBimename().getPishnehad());
            bimename = pishnehadReport.getPishnehad().getBimename();
            List<Elhaghiye> elhaghiyeList = bimename.getElhaghiyehaDaem();
            if(!daemha)
                elhaghiyeList = bimename.getElhaghiyehaDarJaryan();
            Collections.sort(elhaghiyeList, new ElhaghiyeSaleBimeIntComparator());
//            for(int i = 0; i < elhaghiyeList.size(); i++) {
//                if(i < elhaghiyeList.size() - 1) {
//                    if(elhaghiyeList.get(i).getSaleBimeiAsar() == elhaghiyeList.get(i + 1).getSaleBimeiAsar()) {
////                        Elhaghiye mergedOne = logService.mergeTwoElhaghiesForSameSaleAsar(elhaghiyeList.get(i), elhaghiyeList.get(i + 1));
////                        elhaghiyeList.remove(i + 1);
////                        elhaghiyeList.remove(i);
////                        elhaghiyeList.add(i, mergedOne);
//                    }
//                }
//            }
            if(elhaghiyeList.size() > 0) {
                if(elhaghiyeList.get(0).getSaleBimeiAsar() != 0) {
                    pishnehadReport.setPishnehad(bimename.getFirstPishnehad());
                    pishnehadReport.setOldPishnehad(pishnehadReport.getPishnehad());
                    pishnehadReport.setNewPishnehad(pishnehadReport.getPishnehad());
                    pishnehadReport.setCurrentElhaghiye(elhaghiyeList.get(0));
                    correctCalculation = correctCalculation && PrintAction.calculate(pishnehadReport, elhaghiyeList.get(0).getSaleBimeiAsar());
                }
                // agar seporde ro 0 kardim reveretesh mikonim akhar ba in
                String sepordeEbtedaRevert = "";
                Pishnehad cachedPishnehadForSameSaleAsar = null;
                for(int i = 0; i < elhaghiyeList.size(); i++) {
                    int moddat;
                    if(i == elhaghiyeList.size() - 1) {
                        if (!bimename.getFirstPishnehad().getEstelam().getModat_bimename().equals(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getModat_bimename())) {
                            moddat = Integer.parseInt(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getModat_bimename()) - elhaghiyeList.get(i).getSaleBimeiAsar();
                        } else {
                            moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename()) - elhaghiyeList.get(i).getSaleBimeiAsar();
                        }
                    } else {
                        if (elhaghiyeList.get(i + 1).getSaleBimeiAsar() == elhaghiyeList.get(i).getSaleBimeiAsar()) {
                            cachedPishnehadForSameSaleAsar = elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad();
                            continue;
                        } else {
                            moddat = elhaghiyeList.get(i + 1).getSaleBimeiAsar() - elhaghiyeList.get(i).getSaleBimeiAsar();
                        }
                    }
                    if (elhaghiyeList.get(i).getDarkhast().getDarkhastType().equals(Darkhast.DarkhastType.TAGHYIRAT)) {
                        pishnehadReport.setPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad());
                        if (cachedPishnehadForSameSaleAsar != null) {
                            pishnehadReport.setOldPishnehad(cachedPishnehadForSameSaleAsar);
                        } else {
                            pishnehadReport.setOldPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad());
                            cachedPishnehadForSameSaleAsar = null;
                        }
                        if (elhaghiyeList.get(i).getSaleBimeiAsar() > 0 &&
                                elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal() != null &&
                                elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").equals(
                                        elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")
                                )) {
                            sepordeEbtedaRevert = elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal();
                            elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().setMablagh_seporde_ebtedaye_sal("0");
                        }
                        pishnehadReport.setNewPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad());
                    } else {
                        pishnehadReport.setPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastBazkharid().getBimename().getPishnehad());
                    }
                    pishnehadReport.setCurrentElhaghiye(elhaghiyeList.get(i));
                    correctCalculation = correctCalculation && PrintAction.calculate(pishnehadReport, moddat);
                    if(sepordeEbtedaRevert.length() > 0)
                        elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().setMablagh_seporde_ebtedaye_sal(sepordeEbtedaRevert);
                }
            } else {
                int moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename());
                pishnehadReport.setCurrentElhaghiye(null);
                correctCalculation = correctCalculation && PrintAction.calculate(pishnehadReport, moddat);
            }
        }


        List<TaghsitReport> taghsitReports = new ArrayList<TaghsitReport>();
        String shomareBimename = "";
        if(pish.getBimename() != null) shomareBimename = pish.getBimename().getShomare();
//        System.out.println("----- HAZINEHA BE EZAYE SAL(" + shomareBimename + ") -----");
        for (FRs fRs : pishnehadReport.getFRsList()) {
            TaghsitReport taghsitReport = new TaghsitReport();
            taghsitReport.setSaal(fRs.getiRs().getSaal());
            taghsitReport.setHaghBimePardaakhtiSaal(fRs.getiRs().getHaghBimePardaakhti() + fRs.getiRs().getMablaghSepordeEbtedaayeSaal());
            taghsitReport.setKaarmozd(fRs.getiRs().getKaarmozd());
            taghsitReport.setHazineBimeGari(fRs.getiRs().getHazineBimeGari());
            taghsitReport.setHazineEdari(fRs.getiRs().getHazineEdari());
            taghsitReport.setHazineVosul(fRs.getiRs().getHazineVosul());
            taghsitReport.setHaghBimeKhaalesFotYekja(fRs.getiRs().getHaghBimeKhaalesFotYekja());
            taghsitReport.setSarmaayeFotBehHarEllat(fRs.getiRs().getSarmaayeFot());
            taghsitReport.setSarmaayeFotDarAsarHaadeseh(fRs.getiRs().getSarmaayeHaadeseh());
            taghsitReport.setSarmaayePusheshEzaafiAmraazKhaas(fRs.getiRs().getSarmaayeAmraaz());
            taghsitReport.setSarmaayePusheshEzaafiNaghsOzv(fRs.getiRs().getSarmaayeNaghsOzv());
            taghsitReport.setHaghBimePusheshHaayeEzaafi(fRs.getiRs().getHaghBimePusheshHaayeEzaafi());
            taghsitReport.setHaghBimePoosheshAmraz(fRs.getiRs().getMajmuHaghBimePeyvandVaAmraaz());
            taghsitReport.setHaghBimePoosheshHadese(fRs.getiRs().getHaghBimeFotDarAsarHaadese() + fRs.getiRs().getPusheshKhatarZelzele());
            taghsitReport.setHaghBimePoosheshNaghsOzv(fRs.getiRs().getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv());
            taghsitReport.setHaghBimePoosheshMoafiat(fRs.getiRs().getHaghBimeAzKaarOftaadegi());
            taghsitReport.setMaliat(fRs.getiRs().getMaaliyaatBarArzeshAfzude());
            taghsitReport.setMajmooHaghBimePoosheshhayeEzafi(fRs.getiRs().getHaghBimePusheshHaayeEzaafi());
            taghsitReport.setZaribNahveyePardakht(fRs.getiRs().getZaribNahveyePardakht());
            taghsitReport.setHaghBimeAvvalie(fRs.getiRs().getMablaghSepordeEbtedaayeSaal());
//            if(pish.getBimename() == null) {
//                taghsitReport.setHaghBimeAvvalie(Double.parseDouble(pish.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
//            } else {
//                taghsitReport.setHaghBimeAvvalie(Double.parseDouble(bimename.getPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")));
//            }
            taghsitReport.setfRs(fRs);
            taghsitReports.add(taghsitReport);
//            System.out.println("S: " + taghsitReport.getSaal() + " MA: " + taghsitReport.getMaliat() + " PE: " + taghsitReport.getMajmooHaghBimePoosheshhayeEzafi()
//              + " K: " + taghsitReport.getKaarmozd() + " E: " + taghsitReport.getHazineEdari() + " B: " + taghsitReport.getHazineBimeGari() + " F: " + taghsitReport.getHaghBimeKhaalesFotYekja()
//              + " V: " + taghsitReport.getHazineVosul() + " PE(H): " + taghsitReport.getHaghBimePoosheshHadese() + " PE(A): " + taghsitReport.getHaghBimePoosheshAmraz()
//              + " PE(N): " + taghsitReport.getHaghBimePoosheshNaghsOzv() + " PE(M): " + taghsitReport.getHaghBimePoosheshMoafiat());
        }
//        System.out.println("--------------------------------");
        if(!correctCalculation)
            return null;
        return taghsitReports;
    }

}
