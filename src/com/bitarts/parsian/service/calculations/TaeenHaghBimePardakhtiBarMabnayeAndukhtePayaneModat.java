package com.bitarts.parsian.service.calculations;


import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.calculations.Assumptions.DiscountAssumptions;
import com.bitarts.parsian.service.calculations.Assumptions.ReverseGeneralAssumptions;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.IRs;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 8, 2011
 * Time: 11:28:51 PM
 */
public class TaeenHaghBimePardakhtiBarMabnayeAndukhtePayaneModat implements NSPConstants {

    private ReverseGeneralAssumptions generalAssumptions = null;
    private DiscountAssumptions discountAssumptions = null;
    private List<IRs> iRList;

    public List<IRs> intermediateReports(short Sene_Bime_Shode, double Darsad_Ezafe_Nerkh_Pezeshki, short Modat_Bime_Naameh,
                                         long Sarmaye_Paaye_Fot_Rial, long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                         boolean pushesh_Moaafiyat, boolean Pushesh_Naghs_Ozv, long Sarmaye_Pushesh_Naghs_Ozv,
                                         double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                         long andukhte_entehaye_modate_bimename, long Mablagh_Seporde_Ebtedaye_Saal_Rial, NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                         double Nerkh_Afzayesh_Saalaaneh_Mostamari, NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri, short Modat_Zaman_Daryaft_Mostamari,
                                         byte Tabaghe_Khatar, boolean Pushesh_Fot_Dar_Asar_Zelzele, double Bimeh_Gari, double Edari,
                                         double Kaarmozd_Az_Mahal_Hagh_Bimeh, double Kaarmozd_Az_Mahal_Kaarmozd, double Hazineh_Vosul, double nerkhBahreFanni, long hadeAksarSarFot, long hadeAksarSarmayeFotHadese,
                                         long hadeAksarAmraz, double nerkhPusheshZelzele, double darsadMaliat, double karmozdGyekja, double karmozdyekja,
                                         double bimegariGyekja, double bimegariyekja, double edariGyekja, double vosulGyekja, double[][] lifeTable, String _date, Tarh tarh) {
        if (Darsad_Ezafe_Nerkh_Pezeshki == 20.0) Darsad_Ezafe_Nerkh_Pezeshki = 20.1;
        generalAssumptions = new ReverseGeneralAssumptions(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
                andukhte_entehaye_modate_bimename, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, nerkhBahreFanni, lifeTable);


//        discountAssumptions = new DiscountAssumptions(0.01, 0.002,0.03, Kaarmozd_Az_Mahal_Kaarmozd, 0.03);
        discountAssumptions = new DiscountAssumptions(Bimeh_Gari, Edari, Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul);

        ArrayList<IRs> iRListTemp = new ArrayList<IRs>();
        IRs previousIRs = null;
        for (int yearCounter = 0; yearCounter < generalAssumptions.Modat_Bime_Nameh(); yearCounter++) {
            IRs newIRs = new IRs();
            newIRs.setSen(this.mohaasebeSen(yearCounter, lifeTable));
            newIRs.setSaal(this.mohaasebeSaal(yearCounter));
            newIRs.setHaghBimePardaakhti(this.mohaasebeHaghBimePardakhtiActuall(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable));
            newIRs.setHaghBimePardaakhtiBase(this.mohaasebeHaghBimePardakhti(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable));
            newIRs.setMablaghSepordeEbtedaayeSaal(this.mohaasebeMablaghSepordeEbtedaayeSaal(yearCounter, newIRs.getSaal()));
            newIRs.setSarmaayeFot(this.mohaasebeSarmayeFot(yearCounter, previousIRs, hadeAksarSarFot));
            newIRs.setSarmaayeHaadeseh(this.mohaasebeSarmayeHaadeseh(yearCounter, previousIRs, hadeAksarSarmayeFotHadese));
            newIRs.setSarmaayeNaghsOzv(this.mohaasebeSarmaayeNaghsOzv(yearCounter, previousIRs));
            newIRs.setSarmaayeAmraaz(this.mohaasebeSarmaayeAmraaz(yearCounter, previousIRs, newIRs.getSen(), hadeAksarAmraz));
            newIRs.setHaghBimeKhaalesFotYekja(this.mohaasebeHaghBimeKhaalesFotYekja(yearCounter, newIRs.getSarmaayeFot(), nerkhBahreFanni, lifeTable));
            newIRs.setArzeshAyandehHaghBimeHaayeKhaalesFot(this.mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(yearCounter, newIRs.getHaghBimeKhaalesFotYekja(), nerkhBahreFanni));
            newIRs.setKaarmozd(this.mohaasebeKaarmozd(yearCounter, nerkhBahreFanni, hadeAksarSarFot, karmozdGyekja, karmozdyekja, edariGyekja, lifeTable, _date, tarh));
            newIRs.setHazineBimeGari(this.mohaasebeHazineBimeGari(yearCounter, nerkhBahreFanni, hadeAksarSarFot, bimegariGyekja, bimegariyekja, edariGyekja, lifeTable, _date, tarh));
            newIRs.setHazineEdari(this.mohaasebeHazineEdari(yearCounter, newIRs.getSarmaayeFot(), edariGyekja));
            newIRs.setHazineVosul(this.mohaasebeHazineVosul(yearCounter, newIRs.getHaghBimePardaakhti(), newIRs.getMablaghSepordeEbtedaayeSaal(), vosulGyekja));
            newIRs.setHaghBimeFotDarAsarHaadese(this.mohaasebeHaghBimeFotDarAsarHaadese(yearCounter, newIRs.getSarmaayeHaadeseh(), _date, tarh));
            newIRs.setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(this.mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(newIRs.getSarmaayeNaghsOzv()));
            newIRs.setPusheshKhatarZelzele(this.mohaasebePusheshKhatarZelzele(newIRs.getSarmaayeHaadeseh(), nerkhPusheshZelzele));
            newIRs.setHaghBimeAmraazKhaas(this.mohaasebeHaghBimeAmraazKhaas(yearCounter, newIRs.getSen(), newIRs.getSarmaayeAmraaz()));
            newIRs.setHaghBimePeyvandAzaa(this.mohaasebeHaghBimePeyvandAzaa(newIRs.getHaghBimeAmraazKhaas()));
            newIRs.setMajmuHaghBimePeyvandVaAmraaz(this.mohaasebeMajmuHaghBimePeyvandVaAmraaz(newIRs.getHaghBimeAmraazKhaas(), newIRs.getHaghBimePeyvandAzaa()));
            newIRs.setHaghBimeAzKaarOftaadegi(this.mohaasebeHaghBimeAzKaarOftaadegi(yearCounter, newIRs, _date, tarh));
            newIRs.setHaghBimePusheshHaayeEzaafi(this.mohaasebeHaghBimePusheshHaayeEzaafi(newIRs));
            newIRs.setMaaliyaatBarArzeshAfzude(this.mohaasebeMaaliyaatBarArzeshAfzude(newIRs, darsadMaliat, _date, tarh));
            newIRs.setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini15(yearCounter, newIRs, previousIRs, nerkhBahreFanni));
            newIRs.setAndukhteSarmaayeGozaariBaaSud20Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini20(yearCounter, newIRs, previousIRs, 0.2));
            newIRs.setAndukhteSarmaayeGozaariBaaSud22Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini22(yearCounter, newIRs, previousIRs, 0.25));
            newIRs.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(newIRs));
            newIRs.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(newIRs));
            iRListTemp.add(newIRs);
            previousIRs = newIRs;
        }
        iRList = new ArrayList<IRs>();
        int yearCounter = 0;
        for (IRs iRs : iRListTemp) {
            iRs.setArzeshBazKharidBaaSudTazmini15Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(yearCounter, iRListTemp, iRs));
            iRList.add(iRs);
            yearCounter++;
        }
        for (IRs iRs : iRList) {
            iRs.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(iRs));
            iRs.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(iRs));
        }

        return iRList;
    }

    private double mohaasebeHaghBimePardakhtiActuall(int yearCounter, IRs previousIRs, double nerkhBahreFanni, long hadeAksarSarFot, double edariGyekja, double[][] lifeTable) {
        if (yearCounter == 0) {
            if (generalAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                double sourat = nerkhBahreFanni;
                double tavaan = 1.0 / generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal();
                double makhrajsourat = Math.pow((1 + nerkhBahreFanni), tavaan) - 1;
                double partialResult = (sourat / makhrajsourat) / generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal();
                return partialResult * mohaasebeHaghBimePardakhti(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable);
            } else {
                return mohaasebeHaghBimePardakhti(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable);
            }
        } else {
            if (generalAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() == 0) {
                    return previousIRs.getHaghBimePardaakhti() * (1 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime());
                } else {
                    return previousIRs.getHaghBimePardaakhti() * (1 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime());
                }
            } else {
                return 0;
            }
        }
    }

    private short mohaasebeSen(int yearCounter, double[][] lifeTable) {
        if (generalAssumptions.Sen_Asli_Bime_Shode() + lifeTable[yearCounter][0] < generalAssumptions.Sen_Asli_Bime_Shode() + generalAssumptions.Modat_Bime_Nameh()) {
            return (short) (generalAssumptions.Sen_Asli_Bime_Shode() + lifeTable[yearCounter][0]);
        } else {
            return (short) 0;
        }
    }

    private short mohaasebeSaal(int yearCounter) {
        return (short) (yearCounter + 1);
    }

    private double mohaasebeHaghBimePardakhti(int yearCounter, IRs previousIRs, double nerkhBahreFanni, long hadeAksarSarFot, double edariGyekja, double[][] lifeTable) {
        double Mablagh_Hagh_Bime_Pardaakhti = 0;
        if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() == 0) {
            double aValue = mohasebeyeA(yearCounter, previousIRs, nerkhBahreFanni);
            double bValue = mohasebeyeB(yearCounter, previousIRs, nerkhBahreFanni);
            double cValue = mohasebeyeC(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja);
            double eValue = mohasebeyeE(yearCounter, previousIRs, nerkhBahreFanni);
            double fValue = mohasebeyeF(yearCounter, previousIRs, false, nerkhBahreFanni);
            double sprValue = mohasebeSPR(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, lifeTable);

            double sourat = (generalAssumptions.Mablagh_Andukhte_Enteha_Modat() + aValue + bValue + cValue + sprValue - eValue);
            double makhraj = fValue;
            Mablagh_Hagh_Bime_Pardaakhti = sourat / makhraj;

        } else {
            double aValue = mohasebeyeA(yearCounter, previousIRs, nerkhBahreFanni);
            double bValue = mohasebeyeB(yearCounter, previousIRs, nerkhBahreFanni);
            double cValue = mohasebeyeC(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, edariGyekja);
            double eValue = mohasebeyeE(yearCounter, previousIRs, nerkhBahreFanni);
            double fValue = mohasebeyeF(yearCounter, previousIRs, true, nerkhBahreFanni);
            double sprValue = mohasebeSPR(yearCounter, previousIRs, nerkhBahreFanni, hadeAksarSarFot, lifeTable);

            double sourat = (generalAssumptions.Mablagh_Andukhte_Enteha_Modat() + aValue + bValue + cValue + sprValue - eValue);
            double makhraj = fValue;
            Mablagh_Hagh_Bime_Pardaakhti = sourat / makhraj;
        }
        return Mablagh_Hagh_Bime_Pardaakhti;
    }

    private double mohasebeyeF(int yearCounter, IRs previousIRs, boolean pardakhtAvvaliye, double nerkhBahreFanni) {
        double result = 0;
        if (!pardakhtAvvaliye) {
            if (nerkhBahreFanni == generalAssumptions.Nerkh_Afzayesh_Hagh_Bime()) {
                result = ((1 + (0.05 * (discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh()))) - 0.03 * (1 - (discountAssumptions.Hazineh_Vosul()))) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) * generalAssumptions.Modat_Bime_Nameh();
            } else {
                result = ((1 + (0.05 * (discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh()))) - 0.03 * (1 - (discountAssumptions.Hazineh_Vosul()))) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) * ((1 - Math.pow((1 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime()) / (1 + nerkhBahreFanni), generalAssumptions.Modat_Bime_Nameh())) / (1 - ((1 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime()) / (1 + nerkhBahreFanni))));
            }
        } else {
            if (nerkhBahreFanni == generalAssumptions.Nerkh_Afzayesh_Hagh_Bime()) {
                result = ((1 + (0.05 * (discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh()))) - 0.03 * (1 - (discountAssumptions.Hazineh_Vosul()))) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) * (generalAssumptions.Modat_Bime_Nameh());
            } else {
                double onePlusJOnOnePlusI = (1.0 + generalAssumptions.Nerkh_Afzayesh_Hagh_Bime()) / (1.0 + nerkhBahreFanni);
                double moddat = generalAssumptions.Modat_Bime_Nameh();
                result = ((1 + (0.05 * (discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh()))) - 0.03 * (1 - (discountAssumptions.Hazineh_Vosul()))) * ((Math.pow(1 + nerkhBahreFanni, moddat) * (1.0 - Math.pow(onePlusJOnOnePlusI, moddat))) / (1.0 - onePlusJOnOnePlusI));
            }
        }
        return result;
    }

    private double mohasebeyeE(int yearCounter, IRs previousIRs, double nerkhBahreFanni) {
        double result = 0;

        result = generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() * ((1 + (0.05 * (discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh()))) - 0.03 * (1 - (discountAssumptions.Hazineh_Vosul()))) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh());
        return result;

    }

    private double mohasebeSPR(int yearCounter, IRs previousIRs, double nerkhBahreFanni, long hadeAksarSarFot, double[][] lifeTable) {
        double theValue = 0;
        if (yearCounter == 0) {
            for (int l = 0; l < generalAssumptions.Modat_Bime_Nameh(); l++) {
                double haghBimeKhalesFotYekja = mohaasebeHaghBimeKhaalesFotYekjaAux(l, generalAssumptions.Sarmayeh_Fot(), l, nerkhBahreFanni, hadeAksarSarFot, lifeTable);
                double partialValue = haghBimeKhalesFotYekja * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - l);
                theValue += partialValue;
            }
            return theValue;
        } else {
            for (int l = 0; l < generalAssumptions.Modat_Bime_Nameh(); l++) {
                double haghBimeKhalesFotYekja = 0;
                if (generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() == 0) {
                    haghBimeKhalesFotYekja = mohaasebeHaghBimeKhaalesFotYekjaAux(yearCounter, previousIRs.getSarmaayeFot(), l, nerkhBahreFanni, hadeAksarSarFot, lifeTable);
                } else {
                    haghBimeKhalesFotYekja = mohaasebeHaghBimeKhaalesFotYekjaAux(yearCounter - 1, previousIRs.getSarmaayeFot(), l, nerkhBahreFanni, hadeAksarSarFot, lifeTable);
                }
                double partialValue = haghBimeKhalesFotYekja * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - l);
                theValue += partialValue;
            }
            return theValue;
        }
    }

    private double mohasebeyeC(int yearCounter, IRs previousIRs, double nerkhBahreFanni, long hadeAksarSarFot, double edariGyekja) {
        double result = 0;
        double theValue = 0;
        double theVal = 0;
        if (yearCounter == 0) {
            for (int l = 0; l < generalAssumptions.Modat_Bime_Nameh(); l++) {
                if (generalAssumptions.Sarmayeh_Fot() * Math.pow(1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh(), l) >= 1000000000) {
                    theVal = 0.002 * 1000000000 * (1 + (discountAssumptions.Edari())) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - l);
                    theValue += theVal;
                } else {
                    double hazineEdari = mohaasebeHazineEdariAux(l, generalAssumptions.Sarmayeh_Fot(), hadeAksarSarFot, edariGyekja);
                    theVal = hazineEdari * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - l);
//                    theVal=0.002*generalAssumptions.Sarmayeh_Fot()*(1+(discountAssumptions.Edari()))*Math.pow(1+generalAssumptions.Nerkh_Afzayesh_Sarmayeh(),l)*Math.pow(1+NERKH_BAHRE_FANNI,generalAssumptions.Modat_Bime_Nameh()-1);

                    theValue += theVal;
                }
            }
            result = theValue;
            return result;
        } else {
            for (int l = 0; l < generalAssumptions.Modat_Bime_Nameh(); l++) {
                if (previousIRs.getSarmaayeFot() * Math.pow(1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh(), l) >= 1000000000) {
                    theVal = 0.002 * 1000000000 * (1 + (discountAssumptions.Edari())) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - 1);
                    theValue += theVal;
                } else {
                    theVal = (0.002 * previousIRs.getSarmaayeFot() * (1 - (discountAssumptions.Edari()))) * Math.pow(1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh(), l) * Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - l);
                    theValue += theVal;
                }
            }
            result = theValue;
            return result;
        }
    }

    private double mohasebeyeB(int yearCounter, IRs previousIRs, double nerkhBahreFanni) {
        double result = 0;
        if (yearCounter == 0) {
            double rightPart = (0.01 * generalAssumptions.Sarmayeh_Fot() * (1 - (discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd())));
            double leftPart = ((0.4 * (Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()))) + ((0.15 / nerkhBahreFanni) * ((Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) - Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - 4)))));
            result = rightPart * leftPart;
            return result;
        } else {
            double rightPart = (0.01 * previousIRs.getSarmaayeFot() * (1 - (discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd())));
            double leftPart = ((0.4 * (Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()))) + ((0.15 / nerkhBahreFanni) * ((Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) - Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - 4)))));
            result = rightPart * leftPart;
            return result;
        }
    }


    private double mohasebeyeA(int yearCounter, IRs previousIRs, double nerkhBahreFanni) {
        double result = 0;
        if (yearCounter == 0) {
            double partRight = (0.03 * generalAssumptions.Sarmayeh_Fot() * (1 - (discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd())));
            double partLeft = ((0.4 * (Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()))) + ((0.15 / nerkhBahreFanni) * ((Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) - Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - 4)))));
            return partRight * partLeft;
        } else {
            double partRight = (0.03 * previousIRs.getSarmaayeFot() * (1 - (discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd())));
            double partLeft = ((0.4 * (Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()))) + ((0.15 / nerkhBahreFanni) * ((Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh()) - Math.pow(1 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - 4)))));
            return partRight * partLeft;
        }
    }

    private double mohaasebeMablaghSepordeEbtedaayeSaal(int yearCounter, short saal) {
        if (saal == 1) {
            return generalAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
        } else {
            return 0;
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

    public double mohaasebeSarmaayeNaghsOzv(int yearCounter, IRs previousIRs) {
        if (yearCounter == 0) {
            return Math.round(Math.min(generalAssumptions.Sarmaye_Naghs_Ozv(), HADE_AKSAR_SARMAYE_NAGHS_OZV));
        } else {
            return Math.round(Math.min(previousIRs.getSarmaayeNaghsOzv()
                    * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), HADE_AKSAR_SARMAYE_NAGHS_OZV));
        }
    }

    public double mohaasebeSarmaayeAmraaz(int yearCounter, IRs previousIRs, short sen, long hadeAksarAmraz) {
        if (yearCounter == 0) {
            if (generalAssumptions.Ezafe_Nerkh_Pezeshki() == 0) {
                if (HADE_AGHAL_SENNE_AMRAZ <= sen && sen < HADE_AKSAR_SENNE_AMRAZ) {
                    return Math.min(generalAssumptions.Sarmaye_Pushesh_Amraaz(), hadeAksarAmraz);
                } else {
                    return 0;
                }
            } else {
                if (HADE_AGHAL_SENNE_AMRAZ <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki()
                        && generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() < HADE_AKSAR_SENNE_AMRAZ) {
                    return Math.min(generalAssumptions.Sarmaye_Pushesh_Amraaz(), hadeAksarAmraz);
                } else {
                    return 0;
                }
            }
        } else {
            if (generalAssumptions.Ezafe_Nerkh_Pezeshki() == 0) {
                if (HADE_AGHAL_SENNE_AMRAZ <= sen && sen < HADE_AKSAR_SENNE_AMRAZ) {
                    return Math.min(previousIRs.getSarmaayeAmraaz()
                            * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarAmraz);
                } else {
                    return 0;
                }
            } else {
                if (HADE_AGHAL_SENNE_AMRAZ <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter
                        && generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter < HADE_AKSAR_SENNE_AMRAZ) {
                    return Math.min(previousIRs.getSarmaayeAmraaz()
                            * (1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh()), hadeAksarAmraz);
                } else {
                    return 0;
                }
            }
        }
    }

    private double mohaasebeHaghBimeKhaalesFotYekja(int yearCounter, double sarmaayeFot, double nerkhBahreFanni, double[][] lifeTable) {
        short senBaraayeJosteju;
        if (!generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter);
        } else {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Asli_Bime_Shode() + yearCounter);
        }
        double MX_1 = -1.0, MX_2 = -1.0, DX = -1.0;
        boolean oneParameterWasFound = false;
        for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
            if (senBaraayeJosteju == lifeTable[rowReader][0]) {
                MX_1 = lifeTable[rowReader][7];
                DX = lifeTable[rowReader][5];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            } else if (senBaraayeJosteju + 1 == lifeTable[rowReader][0]) {
                MX_2 = lifeTable[rowReader][7];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            }
        }
        if (Math.round(MX_1) != -1 && Math.round(DX) != -1 && Math.round(MX_2) != -1)
            return sarmaayeFot * Math.sqrt(1.0 + nerkhBahreFanni) * (MX_1 - MX_2) / DX;
        else
            return -1;
    }

    private double mohaasebeHaghBimeKhaalesFotYekja(int yearCounter, double sarmaayeFot, int addedYear, double nerkhBahreFanni, double[][] lifeTable) {
        short senBaraayeJosteju;
        if (!generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter);
        } else {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Asli_Bime_Shode() + yearCounter);
        }
//        senBaraayeJosteju+=addedYear;
        double MX_1 = -1.0, MX_2 = -1.0, DX = -1.0;
        boolean oneParameterWasFound = false;
        for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
            if (senBaraayeJosteju == lifeTable[rowReader][0]) {
                MX_1 = lifeTable[rowReader][7];
                DX = lifeTable[rowReader][5];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            } else if (senBaraayeJosteju + 1 == lifeTable[rowReader][0]) {
                MX_2 = lifeTable[rowReader][7];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            }
        }
        if (Math.round(MX_1) != -1 && Math.round(DX) != -1 && Math.round(MX_2) != -1)
            return sarmaayeFot * Math.sqrt(1.0 + nerkhBahreFanni) * (MX_1 - MX_2) / DX;
        else
            return -1;
    }

    private double mohaasebeHaghBimeKhaalesFotYekjaAux(int yearCounter, double sarmaayeFot, int addedYear, double nerkhBahreFanni, long hadeAksarSarFot, double[][] lifeTable) {
        if (generalAssumptions.Nerkh_Afzayesh_Sarmayeh() != 0) {
            sarmaayeFot = Math.min((Math.pow(1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh(), yearCounter) * sarmaayeFot), hadeAksarSarFot);
        }
        short senBaraayeJosteju;
        if (!generalAssumptions.Ezafe_Nerkh_PezeshkiIsZero()) {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter);
        } else {
            senBaraayeJosteju = (short) (generalAssumptions.Sen_Asli_Bime_Shode() + yearCounter);
        }
//        senBaraayeJosteju+=addedYear;
        double MX_1 = -1.0, MX_2 = -1.0, DX = -1.0;
        boolean oneParameterWasFound = false;
        for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
            if (senBaraayeJosteju == lifeTable[rowReader][0]) {
                MX_1 = lifeTable[rowReader][7];
                DX = lifeTable[rowReader][5];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            } else if (senBaraayeJosteju + 1 == lifeTable[rowReader][0]) {
                MX_2 = lifeTable[rowReader][7];
                if (oneParameterWasFound)
                    break;
                else
                    oneParameterWasFound = true;
            }
        }
        if (Math.round(MX_1) != -1 && Math.round(DX) != -1 && Math.round(MX_2) != -1)
            return sarmaayeFot * Math.sqrt(1.0 + nerkhBahreFanni) * (MX_1 - MX_2) / DX;
        else
            return -1;
    }

    private double mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(int yearCounter, double haghBimeKhaalesFotYekja, double nerkhBahreFanni) {
        return haghBimeKhaalesFotYekja * Math.pow(1.0 + nerkhBahreFanni, generalAssumptions.Modat_Bime_Nameh() - yearCounter);
    }

    private double mohaasebeKaarmozd(int yearCounter, double nerkhBahreFanni, long hadeAksarSarFot, double karmozdGyekja, double karmozdyekja, double edariGyekja, double[][] lifeTable, String _date, Tarh tarh) {
        int sal = AsnadeSodorService.getSalhayeEstehlakKarmozd(_date, tarh);
        double[] zarib = AsnadeSodorService.getDarsadKasriKarmozd(_date, tarh);
        if (yearCounter < sal) {
            if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                return zarib[yearCounter] * (mohaasebeHaghBimePardakhti(0, null, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable) * karmozdyekja * (1 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd()));
            } else {
                return zarib[yearCounter] * (generalAssumptions.Sarmayeh_Fot() * karmozdGyekja * (1 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd()));
            }
        } else {
            return 0;
        }
    }

    private double mohaasebeHazineBimeGari(int yearCounter, double nerkhBahreFanni, long hadeAksarSarFot, double bimegariGyekja, double bimegariyekja, double edariGyekja, double[][] lifeTable, String _date, Tarh tarh) {
        double[] zarib = AsnadeSodorService.getDarsadKasriKarmozd(_date, tarh);
        int sal = AsnadeSodorService.getSalhayeEstehlakKarmozd(_date, tarh);
        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (generalAssumptions.Modat_Bime_Nameh() <= 10) {
                if (yearCounter < generalAssumptions.Modat_Bime_Nameh()) {
                    return bimegariyekja * this.mohaasebeHaghBimePardakhti(0, null, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable) / generalAssumptions.Modat_Bime_Nameh();
                } else {
                    return 0;
                }
            } else if (yearCounter < 10) {
                return bimegariyekja * this.mohaasebeHaghBimePardakhti(0, null, nerkhBahreFanni, hadeAksarSarFot, edariGyekja, lifeTable) / 10.0;
            } else {
                return 0;
            }
        } else if (yearCounter < sal) {
            return zarib[yearCounter] * generalAssumptions.Sarmayeh_Fot() * bimegariGyekja * (1 - discountAssumptions.Bimeh_Gari());
        }
        return 0;
    }

    //    private double mohaasebeHazineEdariForFirstYear(int yearCounter, double sarmaayeFot) {
//
//        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//            return 0;
//        } else {
//            return EDARI_GHEIRE_YEKJA * (1 - discountAssumptions.Edari())* sarmaayeFot;
//        }
//    }
    private double mohaasebeHazineEdari(int yearCounter, double sarmaayeFot, double edariGyekja) {

        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            return 0;
        } else {
            return edariGyekja * (1 - discountAssumptions.Edari()) * sarmaayeFot;
        }
    }

    private double mohaasebeHazineEdariAux(int yearCounter, double sarmaayeFot, long hadeAksarSarFot, double edariGyekja) {

        sarmaayeFot = Math.min((Math.pow(1 + generalAssumptions.Nerkh_Afzayesh_Sarmayeh(), yearCounter)) * sarmaayeFot, hadeAksarSarFot);

        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            return 0;
        } else {
            return edariGyekja * (1 - discountAssumptions.Edari()) * sarmaayeFot;
        }
    }

    private double mohaasebeHazineVosul(int yearCounter, double haghBimePardaakhti, double mablaghSepordeEbtedaayeSaal, double vosulGyekja) {
        if (yearCounter > 0) {
            mablaghSepordeEbtedaayeSaal = 0;
        }

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

    private double mohaasebeHaghBimeFotDarAsarHaadese(int yearCounter, double sarmaayeHaadeseh, String _date, Tarh tarh) {
        return AsnadeSodorService.getNerkhPusheshKhatarHadese(_date, generalAssumptions.Tabaghe_Khatar(), tarh) * sarmaayeHaadeseh *
                generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
    }

    private double mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(double sarmaayeNaghsOzv) {
        if (generalAssumptions.Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv()) {
            switch (generalAssumptions.Tabaghe_Khatar()) {
                case 1:
                    return NERKH_PUSHESH_GHARAAMAT_1 * sarmaayeNaghsOzv *
                            generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
                case 2:
                    return NERKH_PUSHESH_GHARAAMAT_2 * sarmaayeNaghsOzv *
                            generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
                case 3:
                    return NERKH_PUSHESH_GHARAAMAT_3 * sarmaayeNaghsOzv *
                            generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
                case 4:
                    return NERKH_PUSHESH_GHARAAMAT_4 * sarmaayeNaghsOzv *
                            generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
                default:
                    return NERKH_PUSHESH_GHARAAMAT_5 * sarmaayeNaghsOzv *
                            generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
            }
        }
        return 0;
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
            if (15 <= generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter &&
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
            } else if (generalAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() + yearCounter <= 60) {
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

    private double mohaasebeMaaliyaatBarArzeshAfzude(IRs iRs, double darsadMaliat, String _date, Tarh tarh) {
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

        return darsadMaliat * ((haghbimekhalesfotyekja
                + karmozd + hazineBimeGari
                + hazineEdari + hazineVosoul
                * zaribtabdilnahvepardakhthaghbime) + poosheshekhatarzelzele
                + haghbimefotdarasarhadese + gheramatazkaroftadegivanaghseozv
                + majmuehaghebimeyepeyvandvaamraz + haghbimeazkaroftadegi);
    }


    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini22(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if (yearCounter > 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())
                ) * (1 + nerkheBahre));
            }
        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (yearCounter > 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            }
        } else {
            if (yearCounter > 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            }
        }
        return 0;
    }


    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini20(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if (yearCounter > 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())
                ) * (1 + nerkheBahre));
            }
        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (yearCounter > 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            }
        } else {
            if (yearCounter > 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            }
        }
        return 0;
    }


    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini15(int yearCounter, IRs iRs, IRs previousIRs, double nerkheBahre) {
        if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if (yearCounter > 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((
                        ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())
                ) * (1 + nerkheBahre));
            }
        } else if (generalAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (yearCounter > 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        + previousIRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((((iRs.getHaghBimePardaakhti()
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getHaghBimePusheshHaayeEzaafi() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            }
        } else {
            if (yearCounter > 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousIRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return
                        (((((iRs.getHaghBimePardaakhti() / generalAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            }
        }
        return 0;
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(int yearCounter, List<IRs> iRListTemp, IRs iRs) {
        double resultDouble = (iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad() - sum(iRListTemp, yearCounter + 1));
        if (yearCounter == 0) {
            return 0.9 * resultDouble;
        } else if (yearCounter == 1) {
            return 0.92 * resultDouble;
        } else if (yearCounter == 2) {
            return 0.94 * resultDouble;
        } else if (yearCounter == 3) {
            return 0.96 * resultDouble;
        } else if (yearCounter == 4) {
            return 0.98 * resultDouble;
        } else {
            return 1 * resultDouble;
        }
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(IRs iRs) {
        return iRs.getArzeshBazKharidBaaSudTazmini15Darsad()
                + (0.85 * (iRs.getAndukhteSarmaayeGozaariBaaSud20Darsad() - iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()));
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(IRs iRs) {
        return iRs.getArzeshBazKharidBaaSudTazmini15Darsad()
                + (0.85 * (iRs.getAndukhteSarmaayeGozaariBaaSud22Darsad() - iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()));
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
