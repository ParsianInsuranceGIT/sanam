package com.bitarts.parsian.service.calculations;


import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.calculations.Assumptions.DiscountAssumptions;
import com.bitarts.parsian.service.calculations.Assumptions.GeneralAssumptions;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.IRs;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.viewModel.BimenameInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 8, 2011
 * Time: 11:28:51 PM
 */
public class TaeenAndukhteBarMabnaayeHaghBimePardaakhti implements NSPConstants {

    private GeneralAssumptions generalAssumptions = null;
    private DiscountAssumptions discountAssumptions = null;
    private List<IRs> iRList;

    public List<IRs> intermediateReports(short Sene_Bime_Shode,
                                         double Darsad_Ezafe_Nerkh_Pezeshki,
                                         short Modat_Bime_Naameh,
                                         long Sarmaye_Paaye_Fot_Rial,
                                         long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial,
                                         long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                         boolean pushesh_Moaafiyat,
                                         boolean Pushesh_Naghs_Ozv,
                                         long Sarmaye_Pushesh_Naghs_Ozv,
                                         double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,
                                         double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,
                                         NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                         long Hagh_Bime_Pardaakhti_Rial,
                                         long Mablagh_Seporde_Ebtedaye_Saal_Rial,
                                         NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                         double Nerkh_Afzayesh_Saalaaneh_Mostamari,
                                         NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri,
                                         short Modat_Zaman_Daryaft_Mostamari,
                                         byte Tabaghe_Khatar,
                                         byte Tabaghe_Khatar_Naghsozv,
                                         boolean Pushesh_Fot_Dar_Asar_Zelzele,
                                         double Bimeh_Gari,
                                         double Edari,
                                         double Kaarmozd_Az_Mahal_Hagh_Bimeh,
                                         double Kaarmozd_Az_Mahal_Kaarmozd,
                                         double Hazineh_Vosul,
                                         double nerkhBahreFanni,
                                         long hadeAksarSarFot,
                                         long hadeAksarSarmayeFotHadese,
                                         long hadeAksarAmraz,
                                         double nerkhPusheshZelzele,
                                         double karmozdGyekja,
                                         double karmozdyekja,
                                         double bimegariGyekja,
                                         double bimegariyekja,
                                         double edariGyekja,
                                         double vosulGyekja,
                                         double[][] lifeTable,
                                         String _date,
                                         Tarh tarh,
                                         NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime,
                                         Constants.PayeyeMohasebeHazineEdari payeMohasebat) {
//        if (Darsad_Ezafe_Nerkh_Pezeshki == 20.0) Darsad_Ezafe_Nerkh_Pezeshki = 20.1;
        generalAssumptions = new GeneralAssumptions(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
                Hagh_Bime_Pardaakhti_Rial, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar, Tabaghe_Khatar_Naghsozv, Pushesh_Fot_Dar_Asar_Zelzele, nerkhBahreFanni, lifeTable, nahve_kasr_hazineha_az_hagh_bime);

        discountAssumptions = new DiscountAssumptions(Bimeh_Gari, Edari, Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul);

        long haghBimeAvvalie = generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
        boolean yekja = false;
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            yekja = true;
        }

        ArrayList<IRs> iRListTemp = new ArrayList<IRs>();
        IRs previousIRs = null;
        for (int yearCounter = 0; yearCounter < generalAssumptions.Modat_Bime_Nameh(); yearCounter++) {
            IRs newIRs = new IRs();
            nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanniForYear(this.mohaasebeSaal(yearCounter), new Constants(), tarh);
            if(yearCounter == 0)
                generalAssumptions.setNerkhBahreFanni5SaleAvval(nerkhBahreFanni);
            generalAssumptions.setNerkhBahreFanni(nerkhBahreFanni);
            newIRs.setSen(this.mohaasebeSen(yearCounter, lifeTable));
            newIRs.setSaal(this.mohaasebeSaal(yearCounter));
            newIRs.setHaghBimePardaakhti(this.mohaasebeHaghBimePardakhti(yearCounter, previousIRs));
            newIRs.setMablaghSepordeEbtedaayeSaal(this.mohaasebeMablaghSepordeEbtedaayeSaal(0, newIRs.getSaal()));
            newIRs.setSaalEmalHaghBimeAvvalie(this.mohasebeSalEmalHaghBimeAvvalie(yearCounter, newIRs.getSaal()));
            newIRs.setHaghBimePardaakhtiSaal(this.mohaasebeHaghBimePardaakhtiSaal(newIRs));
            newIRs.setSarmaayeFot(this.mohaasebeSarmayeFot(yearCounter, previousIRs, hadeAksarSarFot));
            newIRs.setZaribNahveyePardakht(generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
            if(newIRs.getSaal() == 1) {
                newIRs.setSarmayeFotSaleAvval(newIRs.getSarmaayeFot());
                newIRs.setHaghBimePardaakhtiSaleAvval(newIRs.getHaghBimePardaakhtiSaal());
                newIRs.setZaribTabdilSalAvval(newIRs.getZaribNahveyePardakht());
                newIRs.setMablaghSepordeEbtedaayeSaalSaleAvval(newIRs.getMablaghSepordeEbtedaayeSaal());
            } else {
                newIRs.setSarmayeFotSaleAvval(previousIRs.getSarmayeFotSaleAvval());
                newIRs.setHaghBimePardaakhtiSaleAvval(previousIRs.getHaghBimePardaakhtiSaleAvval());
                newIRs.setZaribTabdilSalAvval(previousIRs.getZaribTabdilSalAvval());
                newIRs.setMablaghSepordeEbtedaayeSaalSaleAvval(previousIRs.getMablaghSepordeEbtedaayeSaalSaleAvval());
            }
            newIRs.setSarmaayeHaadeseh(this.mohaasebeSarmayeHaadeseh(yearCounter, previousIRs, hadeAksarSarmayeFotHadese));
            newIRs.setSarmaayeNaghsOzv(this.mohaasebeSarmaayeNaghsOzv(yearCounter, previousIRs, -1));
            newIRs.setSarmaayeAmraaz(this.mohaasebeSarmaayeAmraaz(yearCounter, previousIRs, newIRs.getSen(), hadeAksarAmraz, -1));
            newIRs.setHaghBimeKhaalesFotYekja(this.mohaasebeHaghBimeKhaalesFotYekja(yearCounter, newIRs.getSarmaayeFot(), nerkhBahreFanni, lifeTable));
            newIRs.setArzeshAyandehHaghBimeHaayeKhaalesFot(this.mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(yearCounter, newIRs.getHaghBimeKhaalesFotYekja(), nerkhBahreFanni));
            newIRs.setKaarmozd(this.mohaasebeKaarmozd(yearCounter, karmozdGyekja, karmozdyekja, _date, tarh, newIRs));
            newIRs.setHazineBimeGari(this.mohaasebeHazineBimeGari(yearCounter, yekja, _date, tarh, newIRs.getSarmayeFotSaleAvval(), haghBimeAvvalie));
            newIRs.setHazineEdari(this.mohaasebeHazineEdari(yearCounter, newIRs.getHaghBimePardaakhti(), newIRs.getMablaghSepordeEbtedaayeSaal(), edariGyekja, payeMohasebat, newIRs.getSarmaayeFot()));
            newIRs.setHazineVosul(this.mohaasebeHazineVosul(yearCounter, newIRs.getHaghBimePardaakhti(), newIRs.getMablaghSepordeEbtedaayeSaal(), vosulGyekja, _date));
            newIRs.setHaghBimeFotDarAsarHaadese(this.mohaasebeHaghBimeFotDarAsarHaadese(yearCounter, newIRs.getSarmaayeHaadeseh(), _date, tarh));
            newIRs.setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(this.mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(newIRs.getSarmaayeNaghsOzv(), _date, tarh));
            newIRs.setPusheshKhatarZelzele(this.mohaasebePusheshKhatarZelzele(newIRs.getSarmaayeHaadeseh(), nerkhPusheshZelzele));
            newIRs.setHaghBimeAmraazKhaas(this.mohaasebeHaghBimeAmraazKhaas(yearCounter, newIRs.getSen(), newIRs.getSarmaayeAmraaz()));
            newIRs.setHaghBimePeyvandAzaa(this.mohaasebeHaghBimePeyvandAzaa(newIRs.getHaghBimeAmraazKhaas()));
            newIRs.setMajmuHaghBimePeyvandVaAmraaz(this.mohaasebeMajmuHaghBimePeyvandVaAmraaz(newIRs.getHaghBimeAmraazKhaas(), newIRs.getHaghBimePeyvandAzaa()));
            newIRs.setHaghBimeAzKaarOftaadegi(this.mohaasebeHaghBimeAzKaarOftaadegi(yearCounter, newIRs, _date, tarh));
            newIRs.setHaghBimePusheshHaayeEzaafi(this.mohaasebeHaghBimePusheshHaayeEzaafi(newIRs));
            newIRs.setMaaliyaatBarArzeshAfzude(this.mohaasebeMaaliyaatBarArzeshAfzude(newIRs, _date, tarh, null));
            newIRs.setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, newIRs, previousIRs, nerkhBahreFanni));
            newIRs.setAndukhteSarmaayeGozaariBaaSud20Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, newIRs, previousIRs, 0.2));
            newIRs.setAndukhteSarmaayeGozaariBaaSud22Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, newIRs, previousIRs, 0.25));
            newIRs.setSenneBimeShodePasAzEmaleEzafeNerkh((short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + newIRs.getSaal() - 1));
            newIRs.setMajmuHaghBimePardaakhtiSaal(this.mohaasebeMajmuHaghBimePardaakhtiSaal(newIRs, previousIRs));
            newIRs.setMajmuHaghBimePusheshHaayeEzaafi(newIRs.getHaghBimePusheshHaayeEzaafi());
            newIRs.setMajmuKolMabaaleghPardaakhti(this.mohaasebeMajmuKolMabaaleghPardaakhti(newIRs, previousIRs, nahve_kasr_hazineha_az_hagh_bime));
            iRListTemp.add(newIRs);
            previousIRs = newIRs;
        }
        iRList = new ArrayList<IRs>();
        int yearCounter = 0;
        for (IRs iRs : iRListTemp) {
            iRs.setArzeshBazKharidBaaSudTazmini15Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(yearCounter, iRListTemp, iRs));
            iRs.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(yearCounter, iRListTemp, iRs, _date));
            iRs.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(yearCounter, iRListTemp, iRs, _date));
            iRList.add(iRs);
            yearCounter++;
        }
        return iRList;
    }

    public List<IRs> intermediateReportsWithPreviousIRs(short Sene_Bime_Shode,
                                         double Darsad_Ezafe_Nerkh_Pezeshki,
                                         short Modat_Bime_Naameh,
                                         long Sarmaye_Paaye_Fot_Rial,
                                         long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial,
                                         long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                         boolean pushesh_Moaafiyat,
                                         boolean Pushesh_Naghs_Ozv,
                                         long Sarmaye_Pushesh_Naghs_Ozv,
                                         double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,
                                         double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,
                                         NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                         long Hagh_Bime_Pardaakhti_Rial,
                                         long Mablagh_Seporde_Ebtedaye_Saal_Rial,
                                         NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                         double Nerkh_Afzayesh_Saalaaneh_Mostamari,
                                         NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri,
                                         short Modat_Zaman_Daryaft_Mostamari,
                                         byte Tabaghe_Khatar,
                                         byte Tabaghe_Khatar_Naghsozv,
                                         boolean Pushesh_Fot_Dar_Asar_Zelzele,
                                         double Bimeh_Gari,
                                         double Edari,
                                         double Kaarmozd_Az_Mahal_Hagh_Bimeh,
                                         double Kaarmozd_Az_Mahal_Kaarmozd,
                                         double Hazineh_Vosul,
                                         double nerkhBahreFanni,
                                         long hadeAksarSarFot,
                                         long hadeAksarSarmayeFotHadese,
                                         long hadeAksarAmraz,
                                         double nerkhPusheshZelzele,
                                         double karmozdGyekja,
                                         double karmozdyekja,
                                         double bimegariGyekja,
                                         double bimegariyekja,
                                         double edariGyekja,
                                         double vosulGyekja,
                                         double[][] lifeTable,
                                         String _date,
                                         Tarh tarh,
                                         NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime,
                                         Constants.PayeyeMohasebeHazineEdari payeMohasebat,
                                         IRs previousIRs, IRs currentIRs, int salHadeseNaghs, int salHadeseAmraz, BimenameInfo bInfo) {
//        if (Darsad_Ezafe_Nerkh_Pezeshki == 20.0) Darsad_Ezafe_Nerkh_Pezeshki = 20.1;
        generalAssumptions = new GeneralAssumptions(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
                Hagh_Bime_Pardaakhti_Rial, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar, Tabaghe_Khatar_Naghsozv, Pushesh_Fot_Dar_Asar_Zelzele, nerkhBahreFanni, lifeTable, nahve_kasr_hazineha_az_hagh_bime);

        discountAssumptions = new DiscountAssumptions(Bimeh_Gari, Edari, Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul);

        long haghBimeAvvalie = generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
        boolean yekja = false;
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            yekja = true;
        }

        ArrayList<IRs> iRListTemp = new ArrayList<IRs>();
        int yearCounterStart = 0;
        if(previousIRs != null) yearCounterStart = previousIRs.getSaal();
        int salMohasebe = Math.max(generalAssumptions.Modat_Bime_Nameh(), 5);
        for (int yearCounter = 0; yearCounter < salMohasebe; yearCounter++) {
            IRs newIRs = new IRs();
            nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanniForYear(this.mohaasebeSaal(yearCounter), new Constants(), tarh);
            if(yearCounter+yearCounterStart == 0 || yearCounter+yearCounterStart == 1 || yearCounter+yearCounterStart == 2 || yearCounter+yearCounterStart == 3 || yearCounter+yearCounterStart == 4)
                generalAssumptions.setNerkhBahreFanni5SaleAvval(nerkhBahreFanni);
            generalAssumptions.setNerkhBahreFanni(nerkhBahreFanni);
            newIRs.setSen(this.mohaasebeSen(yearCounter + yearCounterStart, lifeTable));
            newIRs.setSaal(this.mohaasebeSaal(yearCounter + yearCounterStart));
            newIRs.setHaghBimePardaakhti(this.mohaasebeHaghBimePardakhti(yearCounter, previousIRs));
            newIRs.setHaghBimePardakhtiGhest(newIRs.getHaghBimePardaakhti() / (double)generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal());
            newIRs.setMablaghSepordeEbtedaayeSaal(this.mohaasebeMablaghSepordeEbtedaayeSaal(yearCounterStart, newIRs.getSaal()));
            newIRs.setSaalEmalHaghBimeAvvalie(this.mohasebeSalEmalHaghBimeAvvalie(yearCounterStart, newIRs.getSaal()));
            newIRs.setHaghBimePardaakhtiSaal(this.mohaasebeHaghBimePardaakhtiSaal(newIRs));
            newIRs.setSarmaayeFot(this.mohaasebeSarmayeFot(yearCounter, previousIRs, hadeAksarSarFot));
            newIRs.setZaribNahveyePardakht(generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
            if(newIRs.getSaal() == 1) {
                newIRs.setSarmayeFotSaleAvval(newIRs.getSarmaayeFot());
                newIRs.setHaghBimePardaakhtiSaleAvval(newIRs.getHaghBimePardaakhtiSaal());
                newIRs.setZaribTabdilSalAvval(newIRs.getZaribNahveyePardakht());
                newIRs.setMablaghSepordeEbtedaayeSaalSaleAvval(newIRs.getMablaghSepordeEbtedaayeSaal());
            } else {
                newIRs.setSarmayeFotSaleAvval(previousIRs.getSarmayeFotSaleAvval());
                newIRs.setHaghBimePardaakhtiSaleAvval(previousIRs.getHaghBimePardaakhtiSaleAvval());
                newIRs.setZaribTabdilSalAvval(previousIRs.getZaribTabdilSalAvval());
                newIRs.setMablaghSepordeEbtedaayeSaalSaleAvval(previousIRs.getMablaghSepordeEbtedaayeSaalSaleAvval());
            }
            newIRs.setSarmaayeHaadeseh(this.mohaasebeSarmayeHaadeseh(yearCounter, previousIRs, hadeAksarSarmayeFotHadese));
            if (yearCounter + yearCounterStart > salHadeseNaghs && salHadeseNaghs != -1)// khesarat naghsOzv
            {
                newIRs.setSarmaayeNaghsOzv(0);
            }
            else
            {
                newIRs.setSarmaayeNaghsOzv(this.mohaasebeSarmaayeNaghsOzv(yearCounter, previousIRs, salHadeseNaghs));
            }

            if (yearCounter + yearCounterStart > salHadeseAmraz && salHadeseAmraz != -1)// khesarat amraz
            {
                newIRs.setSarmaayeNaghsOzv(0);
            }
            else
            {
                newIRs.setSarmaayeAmraaz(this.mohaasebeSarmaayeAmraaz(yearCounter, previousIRs, newIRs.getSen(), hadeAksarAmraz, salHadeseAmraz));
            }

            newIRs.setHaghBimeKhaalesFotYekja(this.mohaasebeHaghBimeKhaalesFotYekja(yearCounter + yearCounterStart, newIRs.getSarmaayeFot(), nerkhBahreFanni, lifeTable));
            newIRs.setArzeshAyandehHaghBimeHaayeKhaalesFot(this.mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(yearCounter + yearCounterStart, newIRs.getHaghBimeKhaalesFotYekja(), nerkhBahreFanni));
            newIRs.setKaarmozd(this.mohaasebeKaarmozd(yearCounter + yearCounterStart, karmozdGyekja, karmozdyekja, _date, tarh, newIRs));
            newIRs.setHazineBimeGari(this.mohaasebeHazineBimeGari(yearCounter + yearCounterStart, yekja, _date, tarh, newIRs.getSarmayeFotSaleAvval(), haghBimeAvvalie));
            newIRs.setHazineEdari(this.mohaasebeHazineEdari(yearCounter + yearCounterStart, newIRs.getHaghBimePardaakhti(), newIRs.getMablaghSepordeEbtedaayeSaal(), edariGyekja, payeMohasebat, newIRs.getSarmaayeFot()));
            newIRs.setHazineVosul(this.mohaasebeHazineVosul(yearCounter + yearCounterStart, newIRs.getHaghBimePardaakhti(), newIRs.getMablaghSepordeEbtedaayeSaal(), vosulGyekja, _date));
            newIRs.setHaghBimeFotDarAsarHaadese(this.mohaasebeHaghBimeFotDarAsarHaadese(yearCounter + yearCounterStart, newIRs.getSarmaayeHaadeseh(), _date, tarh));
            newIRs.setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(this.mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(newIRs.getSarmaayeNaghsOzv(), _date, tarh));
            newIRs.setPusheshKhatarZelzele(this.mohaasebePusheshKhatarZelzele(newIRs.getSarmaayeHaadeseh(), nerkhPusheshZelzele));
            newIRs.setHaghBimeAmraazKhaas(this.mohaasebeHaghBimeAmraazKhaas(yearCounter + yearCounterStart, newIRs.getSen(), newIRs.getSarmaayeAmraaz()));
            newIRs.setHaghBimePeyvandAzaa(this.mohaasebeHaghBimePeyvandAzaa(newIRs.getHaghBimeAmraazKhaas()));
            newIRs.setMajmuHaghBimePeyvandVaAmraaz(this.mohaasebeMajmuHaghBimePeyvandVaAmraaz(newIRs.getHaghBimeAmraazKhaas(), newIRs.getHaghBimePeyvandAzaa()));
            newIRs.setHaghBimeAzKaarOftaadegi(this.mohaasebeHaghBimeAzKaarOftaadegi(yearCounter + yearCounterStart, newIRs, _date, tarh));
            newIRs.setHaghBimePusheshHaayeEzaafi(this.mohaasebeHaghBimePusheshHaayeEzaafi(newIRs));
            newIRs.setMaaliyaatBarArzeshAfzude(this.mohaasebeMaaliyaatBarArzeshAfzude(newIRs, _date, tarh, bInfo));
            newIRs.setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter + yearCounterStart, newIRs, previousIRs, nerkhBahreFanni));
            newIRs.setAndukhteSarmaayeGozaariBaaSud20Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter + yearCounterStart, newIRs, previousIRs, 0.2));
            newIRs.setAndukhteSarmaayeGozaariBaaSud22Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter + yearCounterStart, newIRs, previousIRs, 0.25));
            newIRs.setSenneBimeShodePasAzEmaleEzafeNerkh((short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + newIRs.getSaal() - 1));
            newIRs.setMajmuHaghBimePardaakhtiSaal(this.mohaasebeMajmuHaghBimePardaakhtiSaal(newIRs, previousIRs));
            newIRs.setMajmuHaghBimePusheshHaayeEzaafi(newIRs.getHaghBimePusheshHaayeEzaafi());
            newIRs.setMajmuKolMabaaleghPardaakhti(this.mohaasebeMajmuKolMabaaleghPardaakhti(newIRs, previousIRs, nahve_kasr_hazineha_az_hagh_bime));
            iRListTemp.add(newIRs);
            previousIRs = newIRs;
        }
        iRList = new ArrayList<IRs>();
        int yearCounter = 0;
        for (IRs iRs : iRListTemp) {
            iRs.setArzeshBazKharidBaaSudTazmini15Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(yearCounter, iRListTemp, iRs));
            iRs.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(yearCounter, iRListTemp, iRs, _date));
            iRs.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(yearCounter, iRListTemp, iRs, _date));
            iRList.add(iRs);
            yearCounter++;
        }
        iRList = iRList.subList(0, generalAssumptions.Modat_Bime_Nameh());
        iRList.get(iRList.size() - 1).setMajmooeHazineBimegari(previousIRs.getMajmooeHazineBimegari() + sum(iRList.subList(0, iRList.size() - 1), 0));
        return iRList;
    }

    private short mohaasebeSen(int yearCounter, double[][] lifeTable) {
        return (short) (generalAssumptions.Sen_Asli_Bime_Shode() + lifeTable[yearCounter][0]);
    }

    private short mohaasebeSaal(int yearCounter) {
        return (short) (yearCounter + 1);
    }

    private double mohaasebeMajmuKolMabaaleghPardaakhti(IRs iRs, IRs previusIRsFirst, NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime) {
        if (previusIRsFirst != null) {
            if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                return previusIRsFirst.getMajmuKolMabaaleghPardaakhti()
                        + iRs.getHaghBimePardaakhtiSaal();
            if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
                return previusIRsFirst.getMajmuKolMabaaleghPardaakhti()
                        + iRs.getMaaliyaatBarArzeshAfzude() + iRs.getHaghBimePusheshHaayeEzaafi()
                        + iRs.getHaghBimePardaakhtiSaal();
        } else {
            if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                return iRs.getMajmuHaghBimePardaakhtiSaal();
            if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
                return iRs.getMajmuHaghBimePardaakhtiSaal()
                        + iRs.getMajmuHaghBimePusheshHaayeEzaafi() + iRs.getMaaliyaatBarArzeshAfzude();
        }
        return 0.0;
    }

    private double mohaasebeHaghBimePardaakhtiSaal(IRs iRs) {
        return iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal();
    }

    private double mohaasebeMajmuHaghBimePardaakhtiSaal(IRs iRs, IRs previusIRs) {
        if (previusIRs != null) {
            return previusIRs.getMajmuHaghBimePardaakhtiSaal()
                    + iRs.getHaghBimePardaakhtiSaal();
        } else {
            return iRs.getHaghBimePardaakhtiSaal();
        }
    }

    private double mohaasebeHaghBimePardakhti(int yearCounter, IRs previousIRs) {
        if (yearCounter == 0) {
            if (generalAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                return generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal()
                        * generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
            } else {
                return generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
            }
        } else {
            if (generalAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                return previousIRs.getHaghBimePardaakhti() * (1 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime());
            } else {
                return 0;
            }
        }
    }

    private double mohaasebeMablaghSepordeEbtedaayeSaal(int yearCounter, short saal) {
        if (saal == yearCounter + 1) {
            return generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
        } else {
            return 0;
        }
    }

    private short mohasebeSalEmalHaghBimeAvvalie(int yearCounter, short saal) {
        if (saal == yearCounter + 1) {
            return saal;
        } else {
            return -1;
        }
    }

    private long mohaasebeSarmayeFot(int yearCounter, IRs previousIRs, long hadeAksarSarFot) {
        if (yearCounter == 0) {
            return generalAssumptions.Sarmayeh_Fot();
        } else {
            return Math.round(Math.min(previousIRs.getSarmaayeFot()
                    * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarSarFot));
        }
    }

    public double mohaasebeSarmayeHaadeseh(int yearCounter, IRs previousIRs, long hadeAksarSarmayeFotHadese) {
        if (yearCounter == 0) {
            return Math.min(generalAssumptions.Sarmaye_Fot_Dar_Asar_Hadese(), hadeAksarSarmayeFotHadese);
        } else {
            return Math.min(previousIRs.getSarmaayeHaadeseh()
                    * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarSarmayeFotHadese);
        }
    }

    public double mohaasebeSarmaayeNaghsOzv(int yearCounter, IRs previousIRs, int salHadeseNaghs) {
        if(yearCounter > salHadeseNaghs && salHadeseNaghs != -1) {
            return 0;
        }
        if (yearCounter == 0) {
            return Math.round(Math.min(generalAssumptions.Sarmaye_Naghs_Ozv(), HADE_AKSAR_SARMAYE_NAGHS_OZV));
        } else {
            return Math.round(Math.min(previousIRs.getSarmaayeNaghsOzv()
                    * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), HADE_AKSAR_SARMAYE_NAGHS_OZV));
        }
    }

    public double mohaasebeSarmaayeAmraaz(int yearCounter, IRs previousIRs, short sen, long hadeAksarAmraz, int salHadeseAmraz) {
        if(yearCounter > salHadeseAmraz && salHadeseAmraz != -1) {
            return 0;
        }
        int salAdd = 0;
        if(previousIRs != null)
            salAdd += previousIRs.getSaal();
        if (HADE_AGHAL_SENNE_AMRAZ > generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + salAdd
                || generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + salAdd >= HADE_AKSAR_SENNE_AMRAZ) {
            return 0;
        }
        if (yearCounter == 0) {
            return Math.min(generalAssumptions.Sarmaye_Pushesh_Amraaz(), hadeAksarAmraz);
        } else {
            return Math.min(previousIRs.getSarmaayeAmraaz()
                            * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarAmraz);
        }

//        if (yearCounter == 0) {
//            if (generalAssumptions.Ezafe_Nerkh_Pezeshki() == 0) {
//                if (HADE_AGHAL_SENNE_AMRAZ <= sen && sen < HADE_AKSAR_SENNE_AMRAZ) {
//                    return Math.min(generalAssumptions.Sarmaye_Pushesh_Amraaz(), hadeAksarAmraz);
//                } else {
//                    return 0;
//                }
//            } else {
//                if (HADE_AGHAL_SENNE_AMRAZ <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki()
//                        && generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() < HADE_AKSAR_SENNE_AMRAZ) {
//                    return Math.min(generalAssumptions.Sarmaye_Pushesh_Amraaz(), hadeAksarAmraz);
//                } else {
//                    return 0;
//                }
//            }
//        } else {
//            if (generalAssumptions.Ezafe_Nerkh_Pezeshki() == 0) {
//                if (HADE_AGHAL_SENNE_AMRAZ <= sen && sen < HADE_AKSAR_SENNE_AMRAZ) {
//                    return Math.min(previousIRs.getSarmaayeAmraaz()
//                            * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarAmraz);
//                } else {
//                    return 0;
//                }
//            } else {
//                if (HADE_AGHAL_SENNE_AMRAZ <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + previousIRs.getSaal()
//                        && generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + previousIRs.getSaal() < HADE_AKSAR_SENNE_AMRAZ) {
//                    return Math.min(previousIRs.getSarmaayeAmraaz()
//                            * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarAmraz);
//                } else {
//                    return 0;
//                }
//            }
//        }
    }

    private double mohaasebeHaghBimeKhaalesFotYekja(int yearCounter, double sarmaayeFot, double nerkhBahreFanni, double[][] lifeTable) {
        short senBaraayeJosteju;
        if (!generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter);
        } else {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Asli_Bime_Shode() + yearCounter);
        }
//      BAR ASAS FORMUL (MX_1 - MX_2) / Dx
//        double MX_1 = -1.0, MX_2 = -1.0, DX = -1.0;
//        boolean oneParameterWasFound = false;
//        for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
//            if (senBaraayeJosteju == lifeTable[rowReader][0]) {
//                MX_1 = lifeTable[rowReader][7];
//                DX = lifeTable[rowReader][5];
//                if (oneParameterWasFound)
//                    break;
//                else
//                    oneParameterWasFound = true;
//            } else if (senBaraayeJosteju + 1 == lifeTable[rowReader][0]) {
//                MX_2 = lifeTable[rowReader][7];
//                if (oneParameterWasFound)
//                    break;
//                else
//                    oneParameterWasFound = true;
//            }
//        }
//        if (Math.round(MX_1) != -1 && Math.round(DX) != -1 && Math.round(MX_2) != -1)
//            return sarmaayeFot * Math.sqrt(1.0 + nerkhBahreFanni) * (MX_1 - MX_2) / DX;
//        else
//            return -1;
        double QX = -1.0;
        for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
            if (senBaraayeJosteju == lifeTable[rowReader][0]) {
                QX = lifeTable[rowReader][8];
                break;
            }
        }
        if (Math.round(QX) != -1)
            return sarmaayeFot * (1.0 / Math.sqrt(1.0 + nerkhBahreFanni)) * QX;
        else
            return -1;
    }

    private double mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(int yearCounter, double haghBimeKhaalesFotYekja, double nerkhBahreFanni) {
        return haghBimeKhaalesFotYekja * Math.pow(1.0 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - yearCounter);
    }

    private double mohaasebeKaarmozd(int yearCounter, double karmozdGyekja, double karmozdyekja, String _date, Tarh tarh, IRs newIRs) {
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            if (yearCounter == 0) {
//                return 0.025 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//                return 0.01 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
                return 0.5 * karmozdyekja * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else if (yearCounter == 1 || yearCounter == 2) {
                return 0.25 * karmozdyekja * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//                return 0.005 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//                return 0.0125 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else {
                return 0;
            }
        } else {
            double[] zarib = AsnadeSodorService.getDarsadKasriKarmozd(_date, tarh);
            double zaribHaghBimeSaleAvval = AsnadeSodorService.getDarsadKarmozdHaghBime(_date, tarh);
            int sal = AsnadeSodorService.getSalhayeEstehlakKarmozd(_date, tarh);
            Constants.RaveshMohasebeKarmozd raveshMohasebeKarmozd = AsnadeSodorService.getRaveshMohasebeKarmozd(_date, tarh);
            if (yearCounter < sal) {
                if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                    double theTafrigh = (1.0 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd());
                    return zarib[yearCounter] * (mohaasebeHaghBimePardakhti(0, null) * karmozdyekja * theTafrigh);
                } else {
                    double theTafrigh = (1.0 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd());
                    double theValue = zarib[yearCounter] * (newIRs.getSarmayeFotSaleAvval() * karmozdGyekja * theTafrigh);
                    double anotherValue = zarib[yearCounter] *
                                          (((newIRs.getHaghBimePardaakhtiSaleAvval() -
                                          newIRs.getMablaghSepordeEbtedaayeSaalSaleAvval()) / newIRs.getZaribTabdilSalAvval()) +
                                                  newIRs.getMablaghSepordeEbtedaayeSaalSaleAvval()) * zaribHaghBimeSaleAvval *
                                          theTafrigh;
                    if(raveshMohasebeKarmozd.equals(Constants.RaveshMohasebeKarmozd.FOT_AND_HAGHBIME))
                        return Math.min(theValue, anotherValue);
                    else
                        return theValue;
                }
            } else {
                return 0;
            }
        }
    }

    public double mohaasebeHazineBimeGari(int yearCounter, boolean yekja, String _date, Tarh tarh, long sarmayeFotSaleAvval, long haghBimeAvvalie) {
        double bimegariGYekja = AsnadeSodorService.getBimeGariGyekja(_date, tarh);
        double bimegariYekja = AsnadeSodorService.getBimeGariYekja(_date, tarh);
        if (yekja) {
            if (yearCounter == 0) {
                return bimegariYekja * haghBimeAvvalie;
            } else {
                return 0;
            }
        } else {
            double[] zarib = AsnadeSodorService.getDarsadKasriBimeGari(_date, tarh);
            int sal = AsnadeSodorService.getSalhayeEstehlakBimeGari(_date, tarh);
            if (yearCounter < sal) {
                return zarib[yearCounter] * sarmayeFotSaleAvval * bimegariGYekja;
            } else {
                return 0;
            }
        }
    }

    private double mohaasebeHazineEdari(int yearCounter, double haghBimePardaakhti, double mablaghSepordeEbtedaayeSaal, double edariGyekja, Constants.PayeyeMohasebeHazineEdari payeMohasebat, double sarmayeFowt) {
//        if(generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial()>0&&generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti()==0){
//            if(yearCounter==0){
//                return 0.01 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//            }else{
//                return 0;
//            }
//
//        }else{
//            if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//                return 0;
//            } else {
//                return EDARI_GHEIRE_YEKJA * (1 - discountAssumptions.Edari())* sarmaayeFot;
//            }
//        }
//       TAGHIRATE AEEN NAME JADID (1391-02-01)
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            if (yearCounter == 0) {
                return 0.01 * generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else {
                return 0;
            }
        } else {
            double mablaghePayeMohasebat = haghBimePardaakhti;
            if (payeMohasebat == Constants.PayeyeMohasebeHazineEdari.SarmayeFowt)
                mablaghePayeMohasebat = sarmayeFowt;

            if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                return 0;
            } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
                if (payeMohasebat == Constants.PayeyeMohasebeHazineEdari.HagheBime) {
                    return edariGyekja * (1 - discountAssumptions.Edari()) *
                            (mablaghePayeMohasebat + mablaghSepordeEbtedaayeSaal);
                } else {
                    return edariGyekja * (1 - discountAssumptions.Edari()) *
                            (mablaghePayeMohasebat);
                }
            } else {
                if (payeMohasebat == Constants.PayeyeMohasebeHazineEdari.HagheBime)
                    return edariGyekja * (1 - discountAssumptions.Edari()) *
                        (mablaghePayeMohasebat / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()
                                + mablaghSepordeEbtedaayeSaal);
                else
                    return edariGyekja * (1 - discountAssumptions.Edari()) *
                            (mablaghePayeMohasebat);

            }
        }
    }

    private double mohaasebeHazineVosul(int yearCounter, double haghBimePardaakhti, double mablaghSepordeEbtedaayeSaal, double vosulGyekja, String _date) {
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && generalAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            return 0;
        } else {
            if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                return 0;
            } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
                return vosulGyekja * (1 - discountAssumptions.Hazineh_Vosul()) *
                        (haghBimePardaakhti + mablaghSepordeEbtedaayeSaal);
            } else {
                return vosulGyekja * (1 - discountAssumptions.Hazineh_Vosul()) *
                        (haghBimePardaakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()
                                + mablaghSepordeEbtedaayeSaal);
            }
        }
    }

    private double mohaasebeHaghBimeFotDarAsarHaadese(int yearCounter, double sarmaayeHaadeseh, String _date, Tarh tarh) {

        return AsnadeSodorService.getNerkhPusheshKhatarHadese(_date, generalAssumptions.Tabaghe_Khatar(), tarh) * sarmaayeHaadeseh *
                generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
    }

    private double mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(double sarmaayeNaghsOzv, String _date, Tarh tarh) {
        return AsnadeSodorService.getNerkhPusheshKhatarNaghsOzv(_date, generalAssumptions.Tabaghe_Khatar_Naghsozv(), tarh) * sarmaayeNaghsOzv *
                generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
    }

    private double mohaasebePusheshKhatarZelzele(double sarmaayeHaadeseh, double nerkhPusheshZelzele) {
        if (generalAssumptions.Pushesh_Khatar_Zelzele()) {
            return (nerkhPusheshZelzele * sarmaayeHaadeseh *
                    generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
        }
        return 0;
    }

    private double mohaasebeHaghBimeAmraazKhaas(int yearCounter, short sen, double sarmaayeAmraaz) {
        double zarib = 0.0;
        if (generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
            if (15 <= sen && sen <= 25) {
                zarib = 0.00045;
            } else if (sen <= 30) {
                zarib = 0.00057;
            } else if (sen <= 35) {
                zarib = 0.00075;
            } else if (sen <= 40) {
                zarib = 0.0015;
            } else if (sen <= 45) {
                zarib = 0.0028;
            } else if (sen <= 50) {
                zarib = 0.0046;
            } else if (sen <= 55) {
                zarib = 0.0056;
            } else if (sen <= 60) {
                zarib = 0.0058;
            } else {
                zarib = 0.0;
            }
            return (zarib * sarmaayeAmraaz * generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
        } else {
            if (20 <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter &&
                    generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 25) {
                zarib = 0.00045;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 30) {
                zarib = 0.00057;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 35) {
                zarib = 0.00075;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 40) {
                zarib = 0.0015;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 45) {
                zarib = 0.0028;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 50) {
                zarib = 0.0046;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 55) {
                zarib = 0.0056;
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter < 60) {
                zarib = 0.0058;
            } else {
                zarib = 0.0;
            }
            if (yearCounter == 0)
                return ((zarib * sarmaayeAmraaz *
                        generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()));
            else {
                return ((zarib * sarmaayeAmraaz *
                        generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()));
            }
        }
    }

    private double mohaasebeHaghBimePeyvandAzaa(double haghBimeAmraazKhaas) {
        return (0.1 * haghBimeAmraazKhaas);
    }

    private double mohaasebeMajmuHaghBimePeyvandVaAmraaz(double haghBimeAmraazKhaas, double haghBimePeyvandAzaa) {
        return haghBimeAmraazKhaas + haghBimePeyvandAzaa;
    }

    private double mohaasebeHaghBimeAzKaarOftaadegi(int yearCounter, IRs iRs, String _date, Tarh tarh) {
        double moafiatAzPardakht = AsnadeSodorService.getMoafiatAzPardakht(_date, tarh);
        boolean[] params = AsnadeSodorService.getMoafiatParam(_date, tarh);
        double haghbimekhalesfotyekja = params[0] ? iRs.getHaghBimeKhaalesFotYekja() : 0;
        double karmozd = params[1] ? iRs.getKaarmozd() : 0;
        double hazineBimeGari = params[2] ? iRs.getHazineBimeGari() : 0;
        double hazineEdari = params[3] ? iRs.getHazineEdari() : 0;
        double zaribtabdilnahvepardakhthaghbime = params[4] ? generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() : 0;
        double poosheshekhatarzelzele = params[5] ? iRs.getPusheshKhatarZelzele() : 0;
        double haghbimefotdarasarhadese = params[6] ? iRs.getHaghBimeFotDarAsarHaadese() : 0;
        double gheramatazkaroftadegivanaghseozv = params[7] ? iRs.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() : 0;
        double majmuehaghebimeyepeyvandvaamraz = params[8] ? iRs.getMajmuHaghBimePeyvandVaAmraaz() : 0;
        if (generalAssumptions.Moaafiyat_Az_Pardakht_Hagh_Bime()) {
            if (generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
                if (iRs.getSen() < HADE_AKSAR_SENNE_AMRAZ) {
                    return moafiatAzPardakht *
                            ((haghbimekhalesfotyekja + karmozd
                                    + hazineBimeGari + hazineEdari)
                                    * zaribtabdilnahvepardakhthaghbime
                                    + poosheshekhatarzelzele + haghbimefotdarasarhadese
                                    + gheramatazkaroftadegivanaghseozv + majmuehaghebimeyepeyvandvaamraz);
                } else {
                    return 0;
                }
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter < HADE_AKSAR_SENNE_AMRAZ) {
                return moafiatAzPardakht *
                        ((haghbimekhalesfotyekja + karmozd
                                + hazineBimeGari + hazineEdari)
                                * zaribtabdilnahvepardakhthaghbime
                                + poosheshekhatarzelzele + haghbimefotdarasarhadese
                                + gheramatazkaroftadegivanaghseozv + majmuehaghebimeyepeyvandvaamraz);
            } else {
                return 0;
            }
        }
        return 0;
    }

    private double mohaasebeHaghBimePusheshHaayeEzaafi(IRs iRs) {
        return iRs.getHaghBimeFotDarAsarHaadese()
                + iRs.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv()
                + iRs.getPusheshKhatarZelzele()
                + iRs.getHaghBimeAzKaarOftaadegi()
                + iRs.getMajmuHaghBimePeyvandVaAmraaz();
    }

    private double mohaasebeMaaliyaatBarArzeshAfzude(IRs iRs, String _date, Tarh tarh, BimenameInfo bInfo) {
        boolean[] params = AsnadeSodorService.getMaliatParam(_date, tarh);
        double haghbimekhalesfotyekja = params[0] ? iRs.getHaghBimeKhaalesFotYekja() : 0;
        double karmozd = params[1] ? iRs.getKaarmozd() : 0;
        double hazineBimeGari = params[2] ? iRs.getHazineBimeGari() : 0;
        double hazineEdari = params[3] ? iRs.getHazineEdari() : 0;
        double hazineVosoul = params[4] ? iRs.getHazineVosul() : 0;
        double zaribtabdilnahvepardakhthaghbime = params[5] ? generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() : 0;
        double poosheshekhatarzelzele = params[6] ? iRs.getPusheshKhatarZelzele() : 0;
        double haghbimefotdarasarhadese = params[7] ? iRs.getHaghBimeFotDarAsarHaadese() : 0;
        double gheramatazkaroftadegivanaghseozv = params[8] ? iRs.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() : 0;
        double majmuehaghebimeyepeyvandvaamraz = params[9] ? iRs.getMajmuHaghBimePeyvandVaAmraaz() : 0;
        double haghbimeazkaroftadegi = params[10] ? iRs.getHaghBimeAzKaarOftaadegi() : 0;

//        String tarikhDarsadMaliat = DateUtil.addYear(_date, iRs.getSaal() - 1);
        String tarikhDarsadMaliat = _date;

        if(bInfo != null) {
            String lastElhaghieAsarDate = bInfo.getLastElhaghieTaghirAsarDate();
            HashMap<Integer, String> taghsitDates = bInfo.getTaghsitDates();
            if(taghsitDates.containsKey(iRs.getSaal() - 1)) {
                if(taghsitDates.get(iRs.getSaal() - 1).length() > 0) {
                    tarikhDarsadMaliat = taghsitDates.get(iRs.getSaal() - 1);
                }
            } else if(iRs.getSaal() - 1 == bInfo.getSalTaghsit()) {
                tarikhDarsadMaliat = DateUtil.getCurrentDate();
            } else if(DateUtil.isGreaterThanOrEqual(DateUtil.addYear(_date, iRs.getSaal() - 1), lastElhaghieAsarDate)) {
                // agar elhaghie taghir dasht maaliat dar mohasebat bayad update shavad va be tarikh akharin elhaghie hesab shavad
                tarikhDarsadMaliat = bInfo.getLastElhaghieTaghirCreatedDate();
            }
        }

        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(tarikhDarsadMaliat, tarh);

        return darsadMaliat * (((haghbimekhalesfotyekja
                + karmozd + hazineBimeGari
                + hazineEdari + hazineVosoul)
                * zaribtabdilnahvepardakhthaghbime) + poosheshekhatarzelzele
                + haghbimefotdarasarhadese + gheramatazkaroftadegivanaghseozv
                + majmuehaghebimeyepeyvandvaamraz + haghbimeazkaroftadegi);
    }

    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
        double previousAndukhte = 0.0;
        if(previousIRs != null) {
            previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad();
            if(nerkheBahre == 0.2)
                previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad();
            else if(nerkheBahre == 0.25)
                previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad();
        }
        double haghBimePardakhti = 0.0;
        if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
        else if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
            haghBimePardakhti = iRs.getHaghBimePardaakhti();
        if(yearCounter > 0 && generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) haghBimePardakhti = 0; // baraye yekja
        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if (yearCounter > 0) {
                return ((
                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousAndukhte
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((
                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())
                ) * (1 + nerkheBahre));
            }
        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (yearCounter > 0) {
                return ((((haghBimePardakhti
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        + previousAndukhte
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((((haghBimePardakhti
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
                        * (1 + nerkheBahre));
            }
        } else {
            if (yearCounter > 0) {
                return
                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousAndukhte
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return
                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            }
        }
        return 0;
    }

//    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini20(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
//        double haghBimePardakhti = 0.0;
//        if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
//        else if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti();
//        if(yearCounter > 0 && generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) haghBimePardakhti = 0; // baraye yekja
//        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
//            if (yearCounter > 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())
//                ) * (1 + nerkheBahre));
//            }
//        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//            if (yearCounter > 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()
//                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
//                        * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
//                        * (1 + nerkheBahre));
//
//            }
//        } else {
//            if (yearCounter > 0) {
//                return
//                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return
//                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            }
//        }
//        return 0;
//    }
//
//    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini22(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
//        double haghBimePardakhti = 0.0;
//        if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
//        else if(generalAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti();
//        if(yearCounter > 0 && generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) haghBimePardakhti = 0; // baraye yekja
//        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
//            if (yearCounter > 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())
//                ) * (1 + nerkheBahre));
//            }
//        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//            if (yearCounter > 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()
//                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
//                        * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
//                        * (1 + nerkheBahre));
//            }
//        } else {
//            if (yearCounter > 0) {
//                return
//                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return
//                        (((((haghBimePardakhti / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            }
//        }
//        return 0;
//    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(int yearCounter, List<IRs> iRListTemp, IRs iRs) {
        double summation = sum(iRListTemp, yearCounter + 1);
        double resultDouble = (iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad() - summation);
        if (iRs.getSaal() == 1) {
            return 0.9 * resultDouble;
        } else if (iRs.getSaal() == 2) {
            return 0.92 * resultDouble;
        } else if (iRs.getSaal() == 3) {
            return 0.94 * resultDouble;
        } else if (iRs.getSaal() == 4) {
            return 0.96 * resultDouble;
        } else if (iRs.getSaal() == 5) {
            return 0.98 * resultDouble;
        } else {
            return 1 * resultDouble;
        }
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(int yearCounter, List<IRs> iRListTemp, IRs iRs, String _date) {
        if(DateUtil.isGreaterThanOrEqual(_date, "1392/06/06")) {
            double summation = sum(iRListTemp, yearCounter + 1);
            double resultDouble = (iRs.getAndukhteSarmaayeGozaariBaaSud20Darsad() - summation);
            if (iRs.getSaal() == 1) {
                return 0.9 * resultDouble;
            } else if (iRs.getSaal() == 2) {
                return 0.92 * resultDouble;
            } else if (iRs.getSaal() == 3) {
                return 0.94 * resultDouble;
            } else if (iRs.getSaal() == 4) {
                return 0.96 * resultDouble;
            } else if (iRs.getSaal() == 5) {
                return 0.98 * resultDouble;
            } else {
                return 1 * resultDouble;
            }
        } else {
            double arzesheBazkharidBaSud15Darsad = iRs.getArzeshBazKharidBaaSudTazmini15Darsad();
            double andukhteSarmayeGozariBasud20Darsad = iRs.getAndukhteSarmaayeGozaariBaaSud20Darsad();
            double andukhteyeSarmayeGozriBaSoudTazmini15Darsad = iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad();
            return arzesheBazkharidBaSud15Darsad
                    + (0.85 * (andukhteSarmayeGozariBasud20Darsad - andukhteyeSarmayeGozriBaSoudTazmini15Darsad));
        }
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(int yearCounter, List<IRs> iRListTemp, IRs iRs, String _date) {
        if (DateUtil.isGreaterThanOrEqual(_date, "1392/06/06")) {
            double summation = sum(iRListTemp, yearCounter + 1);
            double resultDouble = (iRs.getAndukhteSarmaayeGozaariBaaSud22Darsad() - summation);
            if (iRs.getSaal() == 1) {
                return 0.9 * resultDouble;
            } else if (iRs.getSaal() == 2) {
                return 0.92 * resultDouble;
            } else if (iRs.getSaal() == 3) {
                return 0.94 * resultDouble;
            } else if (iRs.getSaal() == 4) {
                return 0.96 * resultDouble;
            } else if (iRs.getSaal() == 5) {
                return 0.98 * resultDouble;
            } else {
                return 1 * resultDouble;
            }
        } else {
            return iRs.getArzeshBazKharidBaaSudTazmini15Darsad()
                    + (0.85 * (iRs.getAndukhteSarmaayeGozaariBaaSud22Darsad() - iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()));
        }
    }

    private double sum(List<IRs> iRListTemp, int start) {
        double sum = 0;
        int counter = 0;
        for (IRs iRs : iRListTemp) {
            if (counter >= start) {
                sum += iRs.getHazineBimeGari();
            }
            counter++;
        }
        return sum;
    }

}
