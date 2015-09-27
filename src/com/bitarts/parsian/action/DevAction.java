package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.util.OmrUtil;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.text.NumberFormat;
import java.util.*;

public class DevAction extends BaseAction implements ServletContextAware {

    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;
    private ILoginService loginService;
    private Integer bimenameId;
    private ISequenceService sequenceService;
    private INamayandeService namayandeService;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
    }

    public String taghsitManualTable() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        final org.slf4j.Logger logger = LoggerFactory.getLogger(DevAction.class);
        String header = String.format("taghsitByGroupBimename started at : " + DateUtil.getCurrentTime());
        addActionMessage(header);
        if (user == null) {
            String message = "User not logged in. Manual taghsit failed.";
            logger.info(message);
            addActionMessage(message);
            return SUCCESS;
        }
        List<Integer> pList = asnadeSodorService.findPishnehadsForAutoBatchTaghsit(true);
        for (Integer id : pList) {
            Pishnehad p = pishnehadService.loadPishnehadById(id);
            try {
                //addActionMessage(p.getBimename().getShomare());
                List<Ghest> ghestList = asnadeSodorService.saveGhestbandi(p, OmrUtil.getToGhestbandiYear(p.getBimename()), user);
                if (ghestList != null && ghestList.size() > 0)
                    header = String.format("taghsit bimename " + p.getBimename().getShomare() + " ok !");
                else
                    header = String.format("taghsit bimename " + p.getBimename().getShomare() + " failed !");
                logger.info(String.format(header));
                addActionMessage(header);
            } catch (Exception ex) {
                header = String.format("Exception in taghsit bimename " + p.getBimename().getShomare());
                logger.info(String.format(header));
                logger.error(ex.toString());
                addActionMessage(header);
            }
        }
        header = String.format("taghsitByGroupBimename ended at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        addActionMessage(header);
        return SUCCESS;
    }

    public String fixInvalidBimenames() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
//        List<Bimename> bimenames = null;
//        if(bimenameId != null) {
//            Bimename b = pishnehadService.findBimenameById(bimenameId);
//            bimenames = new ArrayList < Bimename >();
//            bimenames.add(b);
//        }
////        else
////        {
////            bimenames = pishnehadService.findInvalidBimenames();
////        }
        List<GhestBandi> ghestbandis = asnadeSodorService.findInvalidBimenames();
        for (GhestBandi gb : ghestbandis) {
            Bimename b = gb.getBimename();
            if (gb.getType() == GhestBandi.Type.G_BIMENAME) {
                List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(b.getPishnehad(), gb.getSaleBimeiInt(), b.getTarikhShorou(), true);
                boolean validated = true;
                if (taghsitReport.size() == gb.getGhestList().size()) {
                    for (int i = 0; i < taghsitReport.size(); i++) {
                        TaghsitReport tr = taghsitReport.get(i);
                        Ghest ghest = gb.getGhestList().get(i);
                        if (tr.getHaghBimePardaakhtiSaal() == ghest.getCredebit().getAmount_long() && tr.getTarikh().equals(ghest.getSarresidDate())) {
                            addActionMessage("OK: " + b.getShomare() + " - Sal: " + tr.getSaal() + " - Ghest: " + (i + 1));
                        } else {
                            validated = false;
                            addActionError("Invalid ghest data: " + b.getShomare() + " - Sal: " + tr.getSaal());
                        }
                    }
                } else {
                    validated = false;
                    addActionError("Invalid size: " + b.getShomare());
                }
                if (validated) {
                    gb.setKasrHazine((int) Math.round(taghsitReport.get(0).getKasrHazine()));
                    double sumAmraz = 0.0, sumHadese = 0.0, sumMoafiat = 0.0, sumNaghsOzv = 0.0, sumMaliat = 0.0, sumEzafi = 0.0, sumAmount = 0.0;
                    for (TaghsitReport tr : taghsitReport) {
                        sumAmraz += tr.getHaghBimePoosheshAmraz();
                        sumHadese += tr.getHaghBimePoosheshHadese();
                        sumMoafiat += tr.getHaghBimePoosheshMoafiat();
                        sumNaghsOzv += tr.getHaghBimePoosheshNaghsOzv();
                        sumMaliat += tr.getMaliat();
                        sumEzafi += tr.getMajmooHaghBimePoosheshhayeEzafi();
                        sumAmount += tr.getHaghBimePardaakhtiSaal();
                    }
                    gb.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(sumAmraz)));
                    gb.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(sumHadese)));
                    gb.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(sumMoafiat)));
                    gb.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(sumNaghsOzv)));
                    gb.setMajmooeAmount(Math.round(sumAmount));
                    gb.setMajmooeEzafi(Math.round(sumEzafi));
                    gb.setMajmooeMaliat(Math.round(sumMaliat));
                    asnadeSodorService.updateGhestbandi(gb);
                    for (int i = 0; i < taghsitReport.size(); i++) {
                        TaghsitReport report = taghsitReport.get(i);
                        Ghest ghest = gb.getGhestList().get(i);
                        ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimeKhaalesFotYekja())));
                        ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(report.getHaghBimePusheshHaayeEzaafi()));
                        ghest.setMaliat(NumberFormat.getNumberInstance().format(report.getMaliat()));
                        ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineBimeGari())));
                        ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineEdari())));
                        ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(report.getKaarmozd())));
                        ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(report.getHazineVosul())));
                        ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).length())));
                        ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(report.getKaarmozd()));
                        ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshAmraz())));
                        ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshHadese())));
                        ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshNaghsOzv())));
                        ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshMoafiat())));
                        boolean isYekja = b.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja");
                        ghest.setKarmozdVosuli(asnadeSodorService.getKarmozdVosul(b.getTarikhSodour(), b.getPishnehad().getTarh(), ghest.getCredebit().getAmount_long(), new Double(report.getMaliat()).longValue(), new Double(report.getMajmooHaghBimePoosheshhayeEzafi()).longValue(), isYekja, null));
                        ghest.setKarmozdPoosheshEzafi(asnadeSodorService.getKarmozdPoosheshEzafi(new Double(report.getHaghBimePusheshHaayeEzaafi()).longValue(), b.getPishnehad().getTarh(), b.getTarikhSodour()));
                        asnadeSodorService.updateGhest(ghest);
                    }
                }
            }
            addActionMessage("Done: " + b.getShomare());
        }
        return SUCCESS;
    }

    public String fixDuplicateGhestbandi() throws BimeNaamehVaSarmayehGozari.CustomValidationException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
//        if(false) {
        List<Integer> ghestbandiIds = asnadeSodorService.temp_findQueuedIds();
        for (int gbId : ghestbandiIds) {
            GhestBandi gb = asnadeSodorService.findGhestBandiById(gbId);
            addActionMessage("--- bimename " + gb.getBimename().getShomare() + " sal " + gb.getSaleBimei() + " ---");
            for (Ghest g : gb.getGhestList()) {
                String etebarString = "";
                for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi()) {
                    etebarString += " E.Id: " + ks.getEtebarCredebit().getId() + " E.A: " + ks.getEtebarCredebit().getAmount_long() + "|";
                }
                addActionMessage("B.Id: " + g.getCredebit().getId() + " B.A: " + g.getCredebit().getAmount_long() + " B.R: "
                        + g.getCredebit().getRemainingAmount_long() + " B.S: " + g.getSarresidDate() + etebarString);
            }
        }
//        }
        if (false) {

            List<Integer> ghestbandiiIds = asnadeSodorService.temp_findQueuedIds();
            HashMap<Bimename, LinkedList<GhestBandi>> bimenameMap = new HashMap<Bimename, LinkedList<GhestBandi>>();
            HashMap<Bimename, Integer> bimenameCount = new HashMap<Bimename, Integer>();
            for (int gbId : ghestbandiiIds) {
                GhestBandi gb = asnadeSodorService.findGhestBandiById(gbId);
                Bimename b = gb.getBimename();
                if (bimenameMap.containsKey(b)) {
                    bimenameMap.get(b).add(gb);
                    bimenameCount.put(b, bimenameCount.get(b) + 1);
                } else {
                    LinkedList<GhestBandi> gbList = new LinkedList<GhestBandi>();
                    gbList.add(gb);
                    bimenameMap.put(b, gbList);
                    bimenameCount.put(b, 1);
                }
            }
            for (Bimename b : bimenameMap.keySet()) {
                GhestBandi gbFirst = null;
                int pardakhtiFirst = 0;
                for (GhestBandi gb : bimenameMap.get(b)) {
                    int pardakhti = 0;
                    for (Ghest g : gb.getGhestList()) {
                        pardakhti += g.getCredebit().getAmount_long() - g.getCredebit().getRemainingAmount_long();
                    }
                    if (pardakhti > pardakhtiFirst) {
                        gbFirst = gb;
                        pardakhtiFirst = pardakhti;
                    }
                }
                HashSet<Integer> etebaratFirstIds = new HashSet<Integer>();
                for (Ghest g : gbFirst.getGhestList()) {
                    for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi()) {
                        etebaratFirstIds.add(ks.getEtebarCredebit().getId());
                    }
                }
                for (GhestBandi gb : bimenameMap.get(b)) {
                    if (!gb.getId().equals(gbFirst.getId())) {
                        for (Ghest g : gb.getGhestList()) {
                            for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi()) {
                                if (!etebaratFirstIds.contains(ks.getEtebarCredebit().getId())) {
                                    Credebit etebar = ks.getEtebarCredebit();
                                    String tarikhSanadGhabl = ks.getSanad().getCreatedDate();
                                    asnadeSodorService.deleteSanad(ks.getSanad().getId());
                                    asnadeSodorService.refreshObject(etebar);
                                    for (Ghest gFirst : gbFirst.getGhestList()) {
                                        if (gFirst.getCredebit().getRemainingAmount_long().equals(gFirst.getCredebit().getAmount_long())) {
                                            Sanad s = asnadeSodorService.sabteSanad(user, gFirst.getCredebit(), etebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
                                            asnadeSodorService.refreshObject(s);
                                            s.setCreatedDate(tarikhSanadGhabl);
                                            pishnehadService.saveOrUpdate(s);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return SUCCESS;
    }

    public String fixDuplicateCodeMoshtari_BankInfo() {
        List<Credebit> credebits = asnadeSodorService.dev_findAllEtebarCredebitsByBankInfo();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (username != null) {
            user = loginService.findUserByUsername(username);
        }
        for (Credebit c : credebits) {
            if (c.getShomareMoshtari() != null && c.getShomareMoshtari().length() > 14) {
                Credebit bedehi = asnadeSodorService.findBedehiByCodeMoshtari(c.getShomareMoshtari(), c.getRemainingAmount_long());
                if (bedehi != null && bedehi.getRemainingAmount_long() > 0) {
                    Sanad sanad = asnadeSodorService.sabteSanad(user, bedehi, c, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
                    addActionMessage("Sanad Done ! -> Credit.id: " + c.getId() + " Debit.id: " + bedehi.getId() +" Debit.sarresid:"+bedehi.getGhest().getSarresidDate()+ " Shomare_bimename: "+bedehi.getBimename().getShomare() );
                }
            }
        }
        return SUCCESS;
    }

    public String fixDuplicateCodeMoshtari() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        List<Credebit> credebits = asnadeSodorService.temp_findQueuedCredebits();
        HashMap<String, LinkedList<Credebit>> codeMoshtariMap = new HashMap<String, LinkedList<Credebit>>();
        for (Credebit credebit : credebits) {
            if (codeMoshtariMap.containsKey(credebit.getShomareMoshtari().substring(0, 17))) {
                codeMoshtariMap.get(credebit.getShomareMoshtari().substring(0, 17)).add(credebit);
            } else {
                LinkedList<Credebit> cList = new LinkedList<Credebit>();
                cList.add(credebit);
                codeMoshtariMap.put(credebit.getShomareMoshtari().substring(0, 17), cList);
            }
        }
        LinkedList<Credebit> codeMoshtariGeneratedWithSanad = new LinkedList<Credebit>();
        LinkedList<Credebit> codeMoshtariGeneratedWithoutSanad = new LinkedList<Credebit>();
        LinkedList<Credebit> allSanadsCorrect = new LinkedList<Credebit>();
        LinkedList<Credebit> allSanadsCorrectSameAmount = new LinkedList<Credebit>();
        int printCount = 0;
        for (String codeMoshtari : codeMoshtariMap.keySet()) {
            LinkedList<String> messages = new LinkedList<String>();
            messages.add("------ " + codeMoshtari + " ------ ");
            boolean mablaghMotefavet = false;
            boolean remainingDifferent = false;
            boolean hasSanad = false;
            boolean sanadCorrect = true;
            boolean allSanadKhorde = true;
            long amountToCheck = codeMoshtariMap.get(codeMoshtari).getFirst().getAmount_long();
            long remainingToCheck = codeMoshtariMap.get(codeMoshtari).getFirst().getRemainingAmount_long();
            if(remainingToCheck == 0) {
                for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
                    if (c.getRemainingAmount_long() > 0) {
                        remainingToCheck = c.getRemainingAmount_long();
                        break;
                    }
                }
            }
            LinkedList<Credebit> etebarat = new LinkedList<Credebit>();
            HashSet<Credebit> ghestsSanadKhorde = new HashSet<Credebit>();
            HashSet<Sanad> sanadList = new HashSet<Sanad>();
            int sanadCorrectCount = 0;
            for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
                messages.add(String.format("C.Id: %s C.Amount: %s C.Remaining: %s C.CustomerCode: %s",
                        c.getId(), c.getAmount_long(), c.getRemainingAmount_long(), c.getShomareMoshtari()));
                if (!c.getAmount_long().equals(amountToCheck)) {
                    mablaghMotefavet = true;
                }
                if (c.getRemainingAmount_long() > 0 && !c.getRemainingAmount_long().equals(remainingToCheck)) {
                    remainingDifferent = true;
                }
                int mablaghSanadKhorde = 0;
                for (KhateSanad ks : c.getKhateSanadsBedehi()) {
//                    if (ks.getEtebarCredebit().getShomareMoshtari() != null && ks.getEtebarCredebit().getShomareMoshtari().length() > 0) {
                        messages.add(String.format("-- E.Id: %s E.Amount: %s E.Remaining: %s E.CustomerCode: %s S.Id: %s",
                                ks.getEtebarCredebit().getId(), ks.getEtebarCredebit().getAmount_long(), ks.getEtebarCredebit().getRemainingAmount_long(),
                                ks.getEtebarCredebit().getShomareMoshtari(), ks.getSanad().getId()));
                        mablaghSanadKhorde += ks.getEtebarCredebit().getAmount_long();
                        etebarat.add(ks.getEtebarCredebit());
                        if (!sanadList.contains(ks.getSanad())) {
                            sanadList.add(ks.getSanad());
                        }
                        if (!ghestsSanadKhorde.contains(c)) {
                            ghestsSanadKhorde.add(c);
                        }
                        hasSanad = true;
//                    } else {
//                        messages.add(String.format("-- (ignore-no-cm) E.Id: %s E.Amount: %s E.Remaining: %s E.CustomerCode: %s S.Id: %s",
//                                ks.getEtebarCredebit().getId(), ks.getEtebarCredebit().getAmount_long(), ks.getEtebarCredebit().getRemainingAmount_long(),
//                                ks.getEtebarCredebit().getShomareMoshtari(), ks.getSanad().getId()));
//                    }
                }
                if (mablaghSanadKhorde > 0 && mablaghSanadKhorde != c.getAmount_long()) {
                    sanadCorrect = false;
                } else if (mablaghSanadKhorde > 0) {
                    sanadCorrectCount++;
                } else {
                    allSanadKhorde = false;
                }
            }
            messages.add("Is amount different ? " + mablaghMotefavet);
            messages.add("Is remaining different ? " + remainingDifferent);
            messages.add("Has sanad ? " + hasSanad);
            messages.add("Correct sanad ? " + sanadCorrect);
            messages.add("All have sanad ? " + allSanadKhorde);
            messages.add("Ghests with sanad : " + ghestsSanadKhorde.size());
            messages.add("Sanad correct count : " + sanadCorrectCount);
            boolean shouldPrint = true;
            boolean hasCandidate = false;
            // hame dorost sanad khordan
            if (sanadCorrect && allSanadKhorde) {
                for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
                    if (mablaghMotefavet) {
                        allSanadsCorrect.add(c);
                        shouldPrint = false;
                    } else {
                        allSanadsCorrectSameAmount.add(c);
                        shouldPrint = false;
                    }

                }
            } else if (sanadCorrectCount == ghestsSanadKhorde.size() - 1 && allSanadKhorde) {
                // agar n-1 sanad dorost bashand va akhari sanad khorde bashe, akhari ham dorost ast
                shouldPrint = false;
            } else {
                if (mablaghMotefavet) {
                    // felan multipleEtebar ro filter kardim
                    if (sanadCorrect) {
                        shouldPrint = false;
                    }
                    if (!sanadCorrect && ghestsSanadKhorde.size() == 1) {
                        int majmooAmountEtebarat = 0;
                        for (Credebit c : etebarat) {
                            // agar ghestsSanadKhorde.size > 1 bashad eshtebah mishavad
                            majmooAmountEtebarat += c.getAmount_long();
                        }
                        for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
                            if (c.getAmount_long().equals(c.getRemainingAmount_long())) {
                                if (majmooAmountEtebarat == c.getAmount_long()) {
//                                    String tarikhSanadGhabl = DateUtil.getCurrentDate();
//                                    for (Sanad s : sanadList) {
//                                        tarikhSanadGhabl = s.getCreatedDate();
//                                        messages.add(String.format("Sanad deleted ! S.Id: %s", s.getId()));
//                                        asnadeSodorService.deleteSanad(s.getId());
//                                    }
//                                    for (Credebit e : etebarat) {
//                                        asnadeSodorService.refreshObject(e);
//                                        Sanad s = asnadeSodorService.sabteSanad(user, c, e, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
//                                        asnadeSodorService.refreshObject(s);
//                                        s.setCreatedDate(tarikhSanadGhabl);
//                                        pishnehadService.saveOrUpdate(s);
//                                        addActionMessage(String.format("Sanad for C.Id: %s C.Amount: %s C.Remaining: %s C.CustomerCode: %s S.T: %s",
//                                                c.getId(), c.getAmount_long(), c.getRemainingAmount_long(), c.getShomareMoshtari(), tarikhSanadGhabl));
//                                    }
                                    messages.add(String.format("(Jabejayee) Sanad for C.Id: %s C.Amount: %s C.Remaining: %s C.CustomerCode: %s",
                                            c.getId(), c.getAmount_long(), c.getRemainingAmount_long(), c.getShomareMoshtari()));
                                }
                            }
                        }
                    }
                    if (!sanadCorrect) {
                        // try to match etebar to another sanad
                        for (Credebit cEtebar : etebarat) {
                            for (Credebit cBedehi : codeMoshtariMap.get(codeMoshtari)) {
                                int majmooSanadKhorde = 0;
                                boolean self = false;
                                for (KhateSanad ksBedehi : cBedehi.getKhateSanadsBedehi()) {
                                    majmooSanadKhorde += ksBedehi.getEtebarCredebit().getAmount_long();
                                    if (ksBedehi.getEtebarCredebit().getId().equals(cEtebar.getId())) {
                                        self = true;
                                    }
                                }
                                if (!self) {
                                    if (majmooSanadKhorde + cEtebar.getAmount_long() == cBedehi.getAmount_long()) {
                                        boolean isThereAnotherOne = false;
                                        for (Credebit etebarCheck : etebarat) {
                                            if (!etebarCheck.getId().equals(cEtebar.getId()) && etebarCheck.getAmount_long().equals(cEtebar.getAmount_long())) {
                                                isThereAnotherOne = true;
                                            }
                                        }
                                        if (!isThereAnotherOne) {
                                            String tarikhSanadGhabl = DateUtil.getCurrentDate();
                                            for (Sanad s : sanadList) {
                                                boolean shouldDelete = false;
                                                for (KhateSanad ks : s.getKhateSanadSet()) {
                                                    if (ks.getEtebarCredebit().getId().equals(cEtebar.getId())) {
                                                        shouldDelete = true;
                                                    }
                                                }
                                                if (shouldDelete) {
                                                    tarikhSanadGhabl = s.getCreatedDate();
                                                    try {
//                                                        asnadeSodorService.deleteSanad(s.getId());
                                                        messages.add(String.format("Sanad deleted ! S.Id: %s", s.getId()));
                                                    } catch (Exception ex) {
                                                        messages.add(String.format("Exception in deleting ! S.Id: %s", s.getId()));
                                                        ex.printStackTrace();
                                                    }

                                                }
                                            }
//                                            asnadeSodorService.refreshObject(cEtebar);
//                                            Sanad s = asnadeSodorService.sabteSanad(user, cBedehi, cEtebar, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
//                                            if(s.getId() > 0) {
//                                                asnadeSodorService.refreshObject(s);
//                                                s.setCreatedDate(tarikhSanadGhabl);
//                                                pishnehadService.saveOrUpdate(s);
//                                                messages.add(String.format("(Pishpardakht move) Sanad for C.Id: %s C.Amount: %s C.Remaining: %s C.CustomerCode: %s",
//                                                        cBedehi.getId(), cBedehi.getAmount_long(), cBedehi.getRemainingAmount_long(), cBedehi.getShomareMoshtari()));
//                                            } else {
//                                                messages.add(String.format("(Pishpardakht move) Sanad Error ! C.Id: %s ErrCode: %s",
//                                                        cBedehi.getId(), s.getId().toString()));
//                                            }

                                        } else {
                                            messages.add(String.format("(Multiple) Sanad candidate C.Id: %s C.Amount: %s C.Remaining: %s C.CustomerCode: %s",
                                                    cBedehi.getId(), cBedehi.getAmount_long(), cBedehi.getRemainingAmount_long(), cBedehi.getShomareMoshtari()));
                                            hasCandidate = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (remainingDifferent && !hasCandidate) {
                        shouldPrint = false;
                    }
                } else {
//                    if (hasSanad) {
//                        messages.add("(WithSanad) Generate CustomerCode here.");
//                        for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
//                            if (c.getKhateSanadsBedehi().size() == 0) {
////                                c.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(
////                                        c.getGhest().getGhestBandi().getBimename().getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "110"));
////                                asnadeSodorService.updateCredebit(c);
//                                codeMoshtariGeneratedWithSanad.add(c);
//                            }
//                        }
//                    } else {
//                        messages.add("(WithoutSanad) Generate CustomerCode here.");
//                        for (Credebit c : codeMoshtariMap.get(codeMoshtari)) {
//                            if (c.getKhateSanadsBedehi().size() == 0) {
////                                c.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(
////                                        c.getGhest().getGhestBandi().getBimename().getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "110"));
////                                asnadeSodorService.updateCredebit(c);
//                                codeMoshtariGeneratedWithoutSanad.add(c);
//                            }
//                        }
//                    }
                }
                if(codeMoshtari.equals("99999390007505091")) {
                    shouldPrint = false;
                }
            }
            if (shouldPrint) {
                printCount++;
                for (String s : messages) {
                    addActionMessage(s);
                }
            }
        }
        addActionMessage("--------------------------- ToDo size : " + printCount);
        addActionMessage("--- No action (all sanads correct same amount) ---");
        for (Credebit c : allSanadsCorrectSameAmount) {
            addActionMessage(String.format("C.Id: %s G.S: %s B: %s Sal: %s",
                    c.getId(), c.getGhest().getSarresidDate(), c.getGhest().getGhestBandi().getBimename().getShomare(), c.getGhest().getGhestBandi().getSaleBimei()));
        }
        addActionMessage("--- No action (all sanads correct different amount) ---");
        for (Credebit c : allSanadsCorrect) {
            addActionMessage(String.format("C.Id: %s G.S: %s B: %s Sal: %s",
                    c.getId(), c.getGhest().getSarresidDate(), c.getGhest().getGhestBandi().getBimename().getShomare(), c.getGhest().getGhestBandi().getSaleBimei()));
        }
        addActionMessage("--- CustomerCode generated (had sanad and same amount) ---");
        for (Credebit c : codeMoshtariGeneratedWithSanad) {
            addActionMessage(String.format("C.Id: %s G.S: %s B: %s Sal: %s C.CM: %s",
                    c.getId(), c.getGhest().getSarresidDate(), c.getGhest().getGhestBandi().getBimename().getShomare(), c.getGhest().getGhestBandi().getSaleBimei(), c.getShomareMoshtari()));
        }
        if (codeMoshtariGeneratedWithSanad.isEmpty()) {
            addActionMessage("(none)");
        }
        addActionMessage("--- CustomerCode generated (no sanad and same amount) ---");
        for (Credebit c : codeMoshtariGeneratedWithoutSanad) {
            addActionMessage(String.format("C.Id: %s G.S: %s B: %s Sal: %s C.CM: %s",
                    c.getId(), c.getGhest().getSarresidDate(), c.getGhest().getGhestBandi().getBimename().getShomare(), c.getGhest().getGhestBandi().getSaleBimei(), c.getShomareMoshtari()));
        }
        if (codeMoshtariGeneratedWithoutSanad.isEmpty()) {
            addActionMessage("(none)");
        }
        return SUCCESS;
    }

    public String createEtebarAzMahalleTablighat() {
        // ------ Data Entry
        Integer amount = 4066000;
        String name = "حمیدرضا محمدپور";
        String shomareBimename = "1110/111110/93/001368";
        String codeNamayande = "111110";
        String codeVahedeSodor = "111110";
        // ------
        String shenase = sequenceService.nextShenaseCredebit();
        Credebit credebit = new Credebit(amount, "MANUAL_CREATED", name, null, "VEHICLE", "AZ_MAHALLE_TABLIGHAT", shomareBimename, shenase, "");
        credebit.setDateFish(DateUtil.getCurrentDate());
        Namayande namayande = namayandeService.getNamayandeByKodeKargozar(codeNamayande);
        credebit.setNamayande(namayande);
        Namayande vahedeSodor = namayandeService.getNamayandeByKodeKargozar(codeVahedeSodor);
        credebit.setVahedeSodor(vahedeSodor);
        credebit.setVosoulDate(DateUtil.getCurrentDate());
        credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
        asnadeSodorService.saveCredebit(credebit);
        addActionMessage("Successful ! Shomare: " + shenase);
        return SUCCESS;
    }

    public String getNextShenaseCredebit() {
        addActionMessage(sequenceService.nextShenaseCredebit());
        return SUCCESS;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public Integer getBimenameId() {
        return bimenameId;

    }

    public void setBimenameId(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }
}
