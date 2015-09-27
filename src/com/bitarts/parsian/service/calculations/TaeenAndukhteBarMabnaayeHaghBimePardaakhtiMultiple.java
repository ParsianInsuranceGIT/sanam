package com.bitarts.parsian.service.calculations;

import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.calculations.Assumptions.DiscountAssumptions;
import com.bitarts.parsian.service.calculations.Assumptions.MultipleGeneralAssumptions;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.IRsMultiple;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 4/13/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaeenAndukhteBarMabnaayeHaghBimePardaakhtiMultiple implements NSPConstants {
    private MultipleGeneralAssumptions multipleGeneralAssumptions;
    private DiscountAssumptions discountAssumptions;
    private List<IRsMultiple> iRsMultipleList;

    public List<IRsMultiple> intermediateReports(
            boolean[] isPersonRegistered,
            short[] Sene_Bime_Shode,
            double[] Darsad_Ezafe_Nerkh_Pezeshki,
            short Modat_Bime_Naameh,
            long[] Sarmaye_Paaye_Fot_Rial,
            long[] Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial,
            long[] Sarmaye_Pushesh_Amraaz_Khaas_Rial,
            boolean[] pushesh_Moaafiyat,
            boolean[] Pushesh_Naghs_Ozv,
            long[] Sarmaye_Pushesh_Naghs_Ozv,
            double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,
            double[] Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,
            NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
            long Hagh_Bime_Pardaakhti_Rial,
            long Mablagh_Seporde_Ebtedaye_Saal_Rial,
            NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
            double Nerkh_Afzayesh_Saalaaneh_Mostamari,
            NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri,
            short Modat_Zaman_Daryaft_Mostamari,
            byte[] Tabaghe_Khatar,
            byte[] Tabaghe_Khatar_Naghsozv,
            boolean[] Pushesh_Fot_Dar_Asar_Zelzele,
            boolean moafiatDarAsareFoteMoghadam,
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
            double darsadMaliat,
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

        multipleGeneralAssumptions = new MultipleGeneralAssumptions(
                isPersonRegistered,
                Sene_Bime_Shode,
                Darsad_Ezafe_Nerkh_Pezeshki,
                Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial,
                Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial,
                Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat,
                Pushesh_Naghs_Ozv,
                Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,
                Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,
                Nahve_Pardaakht_Hagh_Bime,
                Hagh_Bime_Pardaakhti_Rial,
                Mablagh_Seporde_Ebtedaye_Saal_Rial,
                Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari,
                Nahve_Daryaft_Mostamri,
                Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar,
                Tabaghe_Khatar_Naghsozv,
                Pushesh_Fot_Dar_Asar_Zelzele,
                moafiatDarAsareFoteMoghadam,
                nerkhBahreFanni,
                lifeTable,
                nahve_kasr_hazineha_az_hagh_bime
        );

        discountAssumptions = new DiscountAssumptions(Bimeh_Gari, Edari, Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul);

        List<IRsMultiple> iRsMultipleListTemp = new ArrayList<IRsMultiple>();
        IRsMultiple previousIRsMultiple = null;
        for (int yearCount = 0; yearCount < multipleGeneralAssumptions.Modat_Bime_Nameh(); ++yearCount) {
            nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanniForYear(this.mohaasebeSaal(yearCount), new Constants(), tarh);
            if (yearCount == 0)
                multipleGeneralAssumptions.setNerkhBahreFanni5SaleAvval(nerkhBahreFanni);
            multipleGeneralAssumptions.setNerkhBahreFanni(nerkhBahreFanni);

            IRsMultiple newIRsMultiple = new IRsMultiple();
            newIRsMultiple.setSaal(mohaasebeSaal(yearCount));
            newIRsMultiple.setSen(mohaasebeSen(yearCount));
            newIRsMultiple.setHaghBimePardaakhti(mohaasebeHaghBimePardakhti(yearCount, previousIRsMultiple));
            newIRsMultiple.setMablaghSepordeEbtedaayeSaal(mohaasebeMablagheSepordeEbtedayeSaal(yearCount));
            newIRsMultiple.setHaghBimePardaakhtiSaal(this.mohaasebeHaghBimePardaakhtiSaal(newIRsMultiple));
            if(newIRsMultiple.getSaal() == 1) {
                newIRsMultiple.setHaghBimePardaakhtiSaleAvval(newIRsMultiple.getHaghBimePardaakhtiSaal());
            } else {
                newIRsMultiple.setHaghBimePardaakhtiSaleAvval(previousIRsMultiple.getHaghBimePardaakhtiSaleAvval());
            }
            newIRsMultiple.setSarmaayeFot(mohaasebeSarmayeFot(yearCount, previousIRsMultiple, hadeAksarSarFot));
            newIRsMultiple.setSarmaayeHaadeseh(mohaasebeSarmayeHaadeseh(yearCount, previousIRsMultiple, hadeAksarSarmayeFotHadese));
            newIRsMultiple.setSarmaayeNaghsOzv(mohaasebeSarmayeNaghseOzv(yearCount, previousIRsMultiple));
            newIRsMultiple.setSarmaayeAmraaz(mohaasebeSarmayeAmraz(yearCount, previousIRsMultiple, newIRsMultiple.getSen(), hadeAksarAmraz));
            newIRsMultiple.setHaghBimeKhaalesFotYekja(mohaasebeHaghBimeKhaalesFotYekja(yearCount, newIRsMultiple.getSarmaayeFot(), nerkhBahreFanni, lifeTable));
            newIRsMultiple.setSumHaghBimeKhaalesFotYekja(getSum(newIRsMultiple.getHaghBimeKhaalesFotYekja()));
            newIRsMultiple.setArzeshAyandehHaghBimeHaayeKhaalesFot(mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(yearCount, newIRsMultiple.getHaghBimeKhaalesFotYekja(), nerkhBahreFanni));
            newIRsMultiple.setSumArzeshAyandehHaghBimeHaayeKhaalesFot(getSum(newIRsMultiple.getArzeshAyandehHaghBimeHaayeKhaalesFot()));
            newIRsMultiple.setKaarmozd(mohaasebeKaarmozd(yearCount, karmozdGyekja, karmozdyekja, _date, tarh, newIRsMultiple.getHaghBimePardaakhtiSaleAvval()));
            newIRsMultiple.setHazineBimeGari(mohaasebeHazineBimeGari(yearCount, _date, tarh, bimegariGyekja, bimegariyekja));
            //TODO: dar surati ke paye mohasebat sarmaye fot bashad che chizi bayad be onvane sarmaye fot pass dade shavad? 1.max sarmaye fot 2.sum sarmaye fot --> felan sum ra pass midahim
            newIRsMultiple.setHazineEdari(mohaasebeHazineEdari(yearCount, newIRsMultiple.getHaghBimePardaakhti(), newIRsMultiple.getMablaghSepordeEbtedaayeSaal(), edariGyekja, payeMohasebat, getSum(newIRsMultiple.getSarmaayeFot())));
            newIRsMultiple.setHazineVosul(mohaasebeHazineVosul(yearCount, vosulGyekja, newIRsMultiple.getHaghBimePardaakhti(), newIRsMultiple.getMablaghSepordeEbtedaayeSaal()));
            newIRsMultiple.setHaghBimeFotDarAsarHaadese(mohaasebeHagheBimeFotDarAsarHadeseh(yearCount, newIRsMultiple.getSarmaayeHaadeseh(), _date, tarh));
            newIRsMultiple.setSumHaghBimeFotDarAsarHaadese(getSum(newIRsMultiple.getHaghBimeFotDarAsarHaadese()));
            newIRsMultiple.setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(newIRsMultiple.getSarmaayeNaghsOzv(), _date, tarh));
            newIRsMultiple.setSumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(getSum(newIRsMultiple.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv()));
            newIRsMultiple.setPusheshKhatarZelzele(mohaasebePusheshKhatarZelzele(newIRsMultiple.getSarmaayeHaadeseh(), nerkhPusheshZelzele));
            newIRsMultiple.setSumPusheshKhatarZelzele(getSum(newIRsMultiple.getPusheshKhatarZelzele()));
            newIRsMultiple.setMajmuHaghBimeHadeseVaZelzele(mohaasebeHaghBimeFotDarAsarHadesehVaZelzele(yearCount, newIRsMultiple.getHaghBimeFotDarAsarHaadese(), newIRsMultiple.getPusheshKhatarZelzele()));
            newIRsMultiple.setSumMajmuHaghBimeHadeseVaZelzele(getSum(newIRsMultiple.getMajmuHaghBimeHadeseVaZelzele()));
            newIRsMultiple.setHaghBimeAmraazKhaas(mohaasebeHaghBimeAmraazKhaas(yearCount, newIRsMultiple.getSen(), newIRsMultiple.getSarmaayeAmraaz()));
            newIRsMultiple.setSumHaghBimeAmraazKhaas(getSum(newIRsMultiple.getHaghBimeAmraazKhaas()));
            newIRsMultiple.setHaghBimePeyvandAzaa(mohaasebeHaghBimePeyvandAzaa(newIRsMultiple.getHaghBimeAmraazKhaas()));
            newIRsMultiple.setSumHaghBimePeyvandAzaa(getSum(newIRsMultiple.getHaghBimePeyvandAzaa()));
            newIRsMultiple.setMajmuHaghBimePeyvandVaAmraaz(mohaasebeMajmuHaghBimePeyvandVaAmraaz(newIRsMultiple.getHaghBimeAmraazKhaas(), newIRsMultiple.getHaghBimePeyvandAzaa()));
            newIRsMultiple.setSumMajmuHaghBimePeyvandVaAmraaz(getSum(newIRsMultiple.getMajmuHaghBimePeyvandVaAmraaz()));
            newIRsMultiple.setEhtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom(mohaasebeEhtemaleFoteBimeShodeAvalBeSharteHayateBimeShodeDovom(yearCount, newIRsMultiple, lifeTable, nerkhBahreFanni));
            newIRsMultiple.setZaribNahveyePardakht(multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
            iRsMultipleListTemp.add(newIRsMultiple);
            previousIRsMultiple = newIRsMultiple;
        }


        for (int yearCount = multipleGeneralAssumptions.Modat_Bime_Nameh() - 1; yearCount >= 0; --yearCount) {
            for (IRsMultiple iRsMultiple : iRsMultipleListTemp) {
                if (iRsMultiple.getSaal() == mohaasebeSaal(yearCount)) {
                    iRsMultiple.setSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval(mohaasebeSarmayeMoafiateFoteMoghadamDarSurateHayateBimeShodeDovom(yearCount, iRsMultipleListTemp));
                    iRsMultiple.setHaghBimeMoafiatDarSurateFoteMoghadam(mohaasebeHagheBimeMoafiatDarSurateFoteBimeShodeAvalVaHayateBimeShodeDovom(iRsMultiple.getSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval(), iRsMultiple.getEhtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom()));
                    iRsMultiple.setHaghBimeAzKaarOftaadegi(mohaasebeHaghBimeAzKaarOftaadegi(yearCount, iRsMultiple, _date, tarh));
                    iRsMultiple.setSumHaghBimeAzKaarOftaadegi(getSum(iRsMultiple.getHaghBimeAzKaarOftaadegi()));
                    iRsMultiple.setHaghBimePusheshHaayeEzaafi(mohaasebeHaghBimePusheshHaayeEzaafi(iRsMultiple));
                    iRsMultiple.setSumHaghBimePusheshHaayeEzaafi(getSum(iRsMultiple.getHaghBimePusheshHaayeEzaafi()));
                    break;
                }
            }
        }
        this.iRsMultipleList = new ArrayList<IRsMultiple>();
        {
            int yearCounter = 0;
            previousIRsMultiple = null;
            for (IRsMultiple iRsMultiple : iRsMultipleListTemp) {
                iRsMultiple.setMaaliyaatBarArzeshAfzude(mohaasebeMaaliyaatBarArzeshAfzude(iRsMultiple, darsadMaliat, _date, tarh));
                iRsMultiple.setAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad(mohaasebeAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad(yearCounter,previousIRsMultiple,iRsMultiple));
                iRsMultiple.setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, iRsMultiple, previousIRsMultiple, nerkhBahreFanni));
                iRsMultiple.setAndukhteSarmaayeGozaariBaaSud20Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, iRsMultiple, previousIRsMultiple, 0.2));
                iRsMultiple.setAndukhteSarmaayeGozaariBaaSud22Darsad(this.mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(yearCounter, iRsMultiple, previousIRsMultiple, 0.25));
                iRsMultiple.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(yearCounter, iRsMultipleListTemp, iRsMultiple));
                iRsMultiple.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(yearCounter, iRsMultipleListTemp, iRsMultiple));
                iRsMultiple.setSenneBimeShodePasAzEmaleEzafeNerkh(getSenneBimeShodePasAzEmaleEzafeNerkh());

                iRsMultiple.setArzeshBazKharidBaaSudTazmini15Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(yearCounter, iRsMultipleListTemp, iRsMultiple));
                iRsMultipleList.add(iRsMultiple);
                previousIRsMultiple = iRsMultiple;
                yearCounter++;
            }
        }

//        for (IRsMultiple iRs : iRsMultipleList) {
//            iRs.setArzeshBazKharidBaaSud20Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(iRs));
//            iRs.setArzeshBazKharidBaaSud22Darsad(this.mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(iRs));
//        }
//        String debug = debugIRsList(iRsMultipleList);
//        String debugMat = debugMatriseMohasebeSarmayeMoafiatFoteMoghadam(matrisMohasebeSarmayeMoafiatDarSurateFoteMoghadam);
        return iRsMultipleList;
    }

    private short mohaasebeSaal(int yearCounter) {
        return (short) (yearCounter + 1);
    }

    private Short[] mohaasebeSen(int yearCount) {
        Short[] sens = new Short[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < sens.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                sens[personIndex] = (short) (multipleGeneralAssumptions.Sen_Asli_Bime_Shode(personIndex) + yearCount);
            } else {
                sens[personIndex] = null;
            }
        }
        return sens;
    }

    private double mohaasebeHaghBimePardaakhtiSaal(IRsMultiple iRs) {
        return iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal();
    }

    private double mohaasebeHaghBimePardakhti(int yearCounter, IRsMultiple previousIRsMultiple) {
        if (yearCounter == 0) {
            if (multipleGeneralAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                return multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal()
                            * multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
            } else {
                return multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
            }
        } else {
            if (multipleGeneralAssumptions.Nahve_Pardaakht_Hagh_Bime() != NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
                if (multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() == 0) {
                    return previousIRsMultiple.getHaghBimePardaakhti() * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Hagh_Bime());
                } else {
                    if (yearCounter == 0) {
                        if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
                            return multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
                        } else {
//                            return multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal()
//                                    * multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti();
                            return previousIRsMultiple.getHaghBimePardaakhti() * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Hagh_Bime());
                        }
                    } else {
                        return previousIRsMultiple.getHaghBimePardaakhti() * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Hagh_Bime());
                    }
                }
            } else {
                return 0;
            }
        }
    }

    /*private double[][] mohaasebeMatrisSarmayeMoafiat(double nerkhBahreFanni, List<IRsMultiple> iRsMultipleList) {
        double[][] matrix = new double[multipleGeneralAssumptions.Modat_Bime_Nameh()][multipleGeneralAssumptions.Modat_Bime_Nameh()];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (j >= i) {
                    short saal = mohaasebeSaal(j);
                    if (saal == multipleGeneralAssumptions.Modat_Bime_Nameh()) {
                        matrix[j][i] = 0;
                    } else {
                        for (IRsMultiple iRsMultiple : iRsMultipleList) {
                            if (iRsMultiple.getSaal() == saal + 1) {
                                matrix[j][i] = (((iRsMultiple.getHaghBimePardaakhti() + iRsMultiple.getSumHaghBimePusheshHaayeEzaafi()) -
                                        (iRsMultiple.getMajmuHaghBimeHadeseVaZelzele()[0] + iRsMultiple.getHaghBimeAmraazKhaas()[0] + iRsMultiple.getHaghBimeAzKaarOftaadegi()[0] +
                                                iRsMultiple.getHaghBimeMoafiatDarSurateFoteMoghadam() + iRsMultiple.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv()[0])
                                ) / Math.pow((double) (1 + nerkhBahreFanni), j - i + 1)) / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
                                break;
                            }
                        }
                    }
                } else {
                    matrix[j][i] = 0;
                }
            }
        }
        return matrix;
    }

    private double mohaasebeSarmayeMoafiateFoteMoghadamDarSurateHayateBimeShodeDovom(int yearCount, double[][] matriseMohasebeSarmayeMoafiat) {
        if (multipleGeneralAssumptions.moafiatFoteMoghadam()) {
            double sum = 0;
            for (int i = 0; i < matriseMohasebeSarmayeMoafiat.length; ++i) {
                sum += matriseMohasebeSarmayeMoafiat[i][yearCount];
            }
            return sum;
        }
        return 0;
    }*/

    private double mohaasebeSarmayeMoafiateFoteMoghadamDarSurateHayateBimeShodeDovom(int yearCount, List<IRsMultiple> iRsMultipleList) {


        double sum = 0;
        if (yearCount == multipleGeneralAssumptions.Modat_Bime_Nameh() - 1) {
            return 0;
        } else {
            if (multipleGeneralAssumptions.moafiatFoteMoghadam()) {
                short minSaal = mohaasebeSaal(yearCount);
                for (int year = yearCount; year < multipleGeneralAssumptions.Modat_Bime_Nameh() - 1; ++year) {
                    short saal = mohaasebeSaal(year);
                    IRsMultiple iRsMultiple = null;
                    for (IRsMultiple iRsMultipleTemp : iRsMultipleList) {
                        if (iRsMultipleTemp.getSaal() == saal + 1) {
                            iRsMultiple = iRsMultipleTemp;
                            break;
                        }
                    }
                    if (iRsMultiple == null) {
                        return 0;
                    }
                    double temp =
                            (((iRsMultiple.getHaghBimePardaakhti() + iRsMultiple.getSumHaghBimePusheshHaayeEzaafi()) -
                                    (iRsMultiple.getMajmuHaghBimeHadeseVaZelzele()[0] + iRsMultiple.getMajmuHaghBimePeyvandVaAmraaz()[0] + iRsMultiple.getHaghBimeAzKaarOftaadegi()[0] +
                                            iRsMultiple.getHaghBimeMoafiatDarSurateFoteMoghadam() + iRsMultiple.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv()[0])
                            ) / iRsMultiple.getZaribNahveyePardakht()) -
                                    iRsMultiple.getHaghBimeKhaalesFotYekja()[0];
                    double productNerkhBahreFanni = 1;
                    for (IRsMultiple irsm : iRsMultipleList) {
                        if (irsm.getSaal() >= minSaal && irsm.getSaal() <= saal) {
                            if (irsm.getSaal() <= 5) {
                                productNerkhBahreFanni *= (1 + multipleGeneralAssumptions.getNerkhBahreFanni5SaleAvval());
                            } else {
                                productNerkhBahreFanni *= (1 + multipleGeneralAssumptions.getNerkhBahreFanni());
                            }
                        }
                    }

                    sum += temp / productNerkhBahreFanni;
                }
                /* for (IRsMultiple iRsMultiple : iRsMultipleList) {
                    if (iRsMultiple.getSaal() == minSaal) {
                        return sum * iRsMultiple.getZaribNahveyePardakht();
                    }
                }*/
                return sum;
            } else {
                return 0;
            }
        }
    }

    private double mohaasebeMablagheSepordeEbtedayeSaal(int yearCount) {
        if (yearCount == 0) {
            return multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
        }
        return 0;
    }

    private Double[] mohaasebeSarmayeFot(int yearCounter, IRsMultiple previousIRsMultiple, long hadeAksarSarFot) {
        Double[] sarmayeFot = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < sarmayeFot.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (yearCounter == 0) {
                    sarmayeFot[personIndex] = (double) multipleGeneralAssumptions.Sarmayeh_Fot(personIndex);
                } else {
                    sarmayeFot[personIndex] = Math.min(previousIRsMultiple.getSarmaayeFot()[personIndex]
                            * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Sarmayeh(personIndex)), hadeAksarSarFot);
                }
            } else {
                sarmayeFot[personIndex] = null;
            }
        }
        return sarmayeFot;
    }

    private Double[] mohaasebeSarmayeHaadeseh(int yearCounter, IRsMultiple previousIRsMultiple, double hadeAksarSarmayeFotHadese) {
        Double[] sarmayeHadese = new Double[multipleGeneralAssumptions.maxPersonsCount()];

        for (int personIndex = 0; personIndex < multipleGeneralAssumptions.maxPersonsCount(); ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (yearCounter == 0) {
                    sarmayeHadese[personIndex] = (double) Math.min(multipleGeneralAssumptions.Sarmaye_Fot_Dar_Asar_Hadese(personIndex), hadeAksarSarmayeFotHadese);
                } else {
                    sarmayeHadese[personIndex] = Math.min(previousIRsMultiple.getSarmaayeHaadeseh()[personIndex]
                            * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Sarmayeh(personIndex)), hadeAksarSarmayeFotHadese);
                }
            } else {
                sarmayeHadese[personIndex] = null;
            }
        }
        return sarmayeHadese;
    }

    private Double[] mohaasebeSarmayeNaghseOzv(int yearCount, IRsMultiple previousIRsMultiple) {
        Double[] sarmayeNaghseOzv = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < sarmayeNaghseOzv.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (yearCount == 0) {
                    sarmayeNaghseOzv[personIndex] = (double) Math.round(Math.min(multipleGeneralAssumptions.Sarmaye_Naghs_Ozv(personIndex), (double) HADE_AKSAR_SARMAYE_NAGHS_OZV));
                } else {
                    sarmayeNaghseOzv[personIndex] = (double) Math.round(Math.min(previousIRsMultiple.getSarmaayeNaghsOzv()[personIndex]
                            * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Sarmayeh(personIndex)), HADE_AKSAR_SARMAYE_NAGHS_OZV));
                }
            } else {
                sarmayeNaghseOzv[personIndex] = null;
            }
        }
        return sarmayeNaghseOzv;
    }

    private Double[] mohaasebeSarmayeAmraz(int yearCount, IRsMultiple previousIRsMultiple, Short[] sen, long hadeAksarSarmayeAmraz) {
        Double[] sarmayeAmraz = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < sarmayeAmraz.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (yearCount == 0) {
                    if (multipleGeneralAssumptions.Ezafe_Nerkh_Pezeshki(personIndex) == 0) {
                        if (HADE_AGHAL_SENNE_AMRAZ <= sen[personIndex] && sen[personIndex] < HADE_AKSAR_SENNE_AMRAZ) {
                            sarmayeAmraz[personIndex] = (double) Math.min(multipleGeneralAssumptions.Sarmaye_Pushesh_Amraaz(personIndex), hadeAksarSarmayeAmraz);
                        } else {
                            sarmayeAmraz[personIndex] = 0.0;
                        }
                    } else {
                        if (HADE_AGHAL_SENNE_AMRAZ <= multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex)
                                && multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) < HADE_AKSAR_SENNE_AMRAZ) {
                            sarmayeAmraz[personIndex] = (double) Math.min(multipleGeneralAssumptions.Sarmaye_Pushesh_Amraaz(personIndex), hadeAksarSarmayeAmraz);
                        } else {
                            sarmayeAmraz[personIndex] = 0.0;
                        }
                    }
                } else {
                    if (multipleGeneralAssumptions.Ezafe_Nerkh_Pezeshki(personIndex) == 0) {
                        if (HADE_AGHAL_SENNE_AMRAZ <= sen[personIndex] && sen[personIndex] < HADE_AKSAR_SENNE_AMRAZ) {
                            sarmayeAmraz[personIndex] = Math.min(previousIRsMultiple.getSarmaayeAmraaz()[personIndex]
                                    * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Sarmayeh(personIndex)), hadeAksarSarmayeAmraz);
                        } else {
                            sarmayeAmraz[personIndex] = 0.0;
                        }
                    } else {
                        if (HADE_AGHAL_SENNE_AMRAZ <= multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCount
                                && multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCount < HADE_AKSAR_SENNE_AMRAZ) {
                            sarmayeAmraz[personIndex] = Math.min(previousIRsMultiple.getSarmaayeAmraaz()[personIndex]
                                    * (1 + multipleGeneralAssumptions.Nerkh_Afzayesh_Sarmayeh(personIndex)), hadeAksarSarmayeAmraz);
                        } else {
                            sarmayeAmraz[personIndex] = 0.0;
                        }
                    }
                }
            } else {
                sarmayeAmraz[personIndex] = null;
            }
        }
        return sarmayeAmraz;
    }

    private Double[] mohaasebeHaghBimeKhaalesFotYekja(int yearCount, Double[] sarmayeFot, double nerkheBahreFanni, double[][] lifeTable) {
        Double[] hagheBimeKhalesFotYekja = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < hagheBimeKhalesFotYekja.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                short senBarayeJosteju;
                if (!multipleGeneralAssumptions.Ezafe_Nerkh_PezeshkiIsZero(personIndex)) {
                    senBarayeJosteju = (short) (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCount);
                } else {
                    senBarayeJosteju = (short) (multipleGeneralAssumptions.Sen_Asli_Bime_Shode(personIndex) + yearCount);
                }

                double Qx = -1.0;
                for (int i = 0; i < lifeTable.length; ++i) {
                    if (lifeTable[i][0] == senBarayeJosteju) {
                        Qx = lifeTable[i][8];
                        break;
                    }
                }
                if (Math.round(Qx) != -1) {
                    hagheBimeKhalesFotYekja[personIndex] = sarmayeFot[personIndex] * (1.0 / Math.sqrt(1.0 + nerkheBahreFanni)) * Qx;
                } else {
                    hagheBimeKhalesFotYekja[personIndex] = -1.0;
                }
            } else {
                hagheBimeKhalesFotYekja[personIndex] = null;
            }
        }
        return hagheBimeKhalesFotYekja;
    }

    private Double[] mohaasebeArzeshAyandehHaghBimeHaayeKhaalesFot(int yearCounter, Double[] haghBimeKhaalesFotYekja, double nerkhBahreFanni) {
        Double[] arzeshAyandehHaghBimeHaayeKhaalesFot = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < arzeshAyandehHaghBimeHaayeKhaalesFot.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                arzeshAyandehHaghBimeHaayeKhaalesFot[personIndex] = haghBimeKhaalesFotYekja[personIndex] * Math.pow(1.0 + nerkhBahreFanni, multipleGeneralAssumptions.Modat_Bime_Nameh() - yearCounter);
            } else {
                arzeshAyandehHaghBimeHaayeKhaalesFot[personIndex] = null;
            }
        }
        return arzeshAyandehHaghBimeHaayeKhaalesFot;
    }

    private double mohaasebeKaarmozd(int yearCounter, double karmozdGyekja, double karmozdyekja, String _date, Tarh tarh, double haghBimePardakhtiSaleAvval) {
        // yekja nadarim
        if (multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            if (yearCounter == 0) {
                return 0.01 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//                return 0.025 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else if (yearCounter == 1 || yearCounter == 2) {
                return 0.005 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
//                return 0.0125 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else {
                return 0;
            }
        } else {
            double[] zarib = AsnadeSodorService.getDarsadKasriKarmozd(_date, tarh);
            int sal = AsnadeSodorService.getSalhayeEstehlakKarmozd(_date, tarh);
            double zaribHaghBimeSaleAvval = AsnadeSodorService.getDarsadKarmozdHaghBime(_date, tarh);
            if (yearCounter < sal) {
                if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                    double theTafrigh = (1.0 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd());
                    double theValue = zarib[yearCounter] * (mohaasebeHaghBimePardakhti(0, null) * karmozdyekja * theTafrigh);
                    return theValue;
                } else {
                    double theTafrigh = (1.0 - discountAssumptions.Kaarmozd_Az_Mahal_Kaarmozd());
                    double theValue = zarib[yearCounter] * (multipleGeneralAssumptions.hadeAksareSarmayeFotSaleAval() * karmozdGyekja * theTafrigh);
                    double anotherValue = zarib[yearCounter] *
                                          (((haghBimePardakhtiSaleAvval -
                                          multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial()) /
                                          multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()) +
                                          multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial()) * zaribHaghBimeSaleAvval *
                                          theTafrigh;
                    return Math.min(theValue, anotherValue);
                }
            } else {
                return 0;
            }
        }
    }

    private double mohaasebeHazineBimeGari(int yearCount, String _date, Tarh tarh, double bimegariGyekja, double bimegariyekja) {
        if (multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            if (yearCount == 0) {
                return 0.01 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else {
                return 0;
            }
        } else {
            double[] zarib = AsnadeSodorService.getDarsadKasriEdari(_date, tarh);
            int sal = AsnadeSodorService.getSalhayeEstehlakEdari(_date, tarh);
            if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                if (multipleGeneralAssumptions.Modat_Bime_Nameh() <= 10) {
                    if (yearCount < multipleGeneralAssumptions.Modat_Bime_Nameh()) {
                        return bimegariyekja * this.mohaasebeHaghBimePardakhti(0, null) / multipleGeneralAssumptions.Modat_Bime_Nameh();
                    } else {
                        return 0;
                    }
                } else if (yearCount < 10) {
                    return bimegariyekja * this.mohaasebeHaghBimePardakhti(0, null) / 10.0;
                } else {
                    return 0;
                }
            } else if (yearCount < sal) {
                return zarib[yearCount] * multipleGeneralAssumptions.hadeAksareSarmayeFotSaleAval() * bimegariGyekja * (1 - discountAssumptions.Bimeh_Gari());
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
        if (multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial() > 0 && multipleGeneralAssumptions.Mablagh_Hagh_Bime_Pardaakhti() == 0) {
            if (yearCounter == 0) {
                return 0.01 * multipleGeneralAssumptions.Mablagh_Seporde_Ebtedaye_Saal_Rial();
            } else {
                return 0;
            }
        } else {
            double mablaghePayeMohasebat = haghBimePardaakhti;
            if (payeMohasebat == Constants.PayeyeMohasebeHazineEdari.SarmayeFowt)
                mablaghePayeMohasebat = sarmayeFowt;

            if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
                return 0;
            } else if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
                return edariGyekja * (1 - discountAssumptions.Edari()) *
                        (mablaghePayeMohasebat + mablaghSepordeEbtedaayeSaal);
            } else {
                return edariGyekja * (1 - discountAssumptions.Edari()) *
                        (mablaghePayeMohasebat / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()
                                + mablaghSepordeEbtedaayeSaal);
            }
        }
    }

    private double mohaasebeHazineVosul(int yearCount, double hazineVosul, double hagheBimePardakhti, double mablagheSepordeEbteda) {
        if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if(yearCount == 0) {
                return hazineVosul * (hagheBimePardakhti + mablagheSepordeEbteda);
            } else {
                return hazineVosul * (hagheBimePardakhti);
            }


        } else if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() > 1) {
            double zaribTabdilNahvePardakht;
            if (yearCount >= 0 && yearCount < 5) {
                zaribTabdilNahvePardakht = multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime_5Sal_Avval();
            } else {
                zaribTabdilNahvePardakht = multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
            }
            if(yearCount == 0) {
                return hazineVosul * (hagheBimePardakhti  / zaribTabdilNahvePardakht + mablagheSepordeEbteda);
            } else {
                return hazineVosul * hagheBimePardakhti / zaribTabdilNahvePardakht;
            }

        }
        return 0;//TODO: yekja???!!!
    }

    private Double[] mohaasebeHaghBimeFotDarAsarHadesehVaZelzele(int yearCount, Double[] haghBimeHadese, Double[] haghBimeZelzele) {
        Double[] haghBimeHadeseVaZelzele = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < haghBimeHadeseVaZelzele.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                haghBimeHadeseVaZelzele[personIndex] = haghBimeHadese[personIndex] + haghBimeZelzele[personIndex];
            } else {
                haghBimeHadeseVaZelzele[personIndex] = null;
            }
        }
        return haghBimeHadeseVaZelzele;
    }

    private Double[] mohaasebeHagheBimeFotDarAsarHadeseh(int yearCount, Double[] sarmayeHadeseh, String _date, Tarh tarh) {
        Double[] hagheBimeFotDarAsareHadeseh = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < hagheBimeFotDarAsareHadeseh.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                hagheBimeFotDarAsareHadeseh[personIndex] = (AsnadeSodorService.getNerkhPusheshKhatarHadese(_date, multipleGeneralAssumptions.Tabaghe_Khatar(personIndex), tarh) /*+
                        AsnadeSodorService.getNerkhPusheshKhatarZelzele(_date, tarh)*/) *
                        sarmayeHadeseh[personIndex] *
                        multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
            } else {
                hagheBimeFotDarAsareHadeseh[personIndex] = null;
            }
        }
        return hagheBimeFotDarAsareHadeseh;
    }

    private Double[] mohaasebePardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(Double[] sarmaayeNaghsOzv, String _date, Tarh tarh) {
        Double[] pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv[personIndex] = AsnadeSodorService.getNerkhPusheshKhatarNaghsOzv(_date, multipleGeneralAssumptions.Tabaghe_Khatar_Naghsozv(personIndex), tarh) * sarmaayeNaghsOzv[personIndex] *
                        multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
            } else {
                pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv[personIndex] = null;
            }
        }
        return pardaakhtGharaamatAzKaarOftadegiVaNaghsOzv;
    }

    private Double[] mohaasebePusheshKhatarZelzele(Double[] sarmaayeHaadeseh, double nerkhPusheshZelzele) {
        Double[] pusheshKhatarZelzele = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < pusheshKhatarZelzele.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (multipleGeneralAssumptions.Pushesh_Khatar_Zelzele(personIndex)) {
                    pusheshKhatarZelzele[personIndex] = (nerkhPusheshZelzele * sarmaayeHaadeseh[personIndex] *
                            multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
                } else {
                    pusheshKhatarZelzele[personIndex] = 0.0;
                }
            } else {
                pusheshKhatarZelzele[personIndex] = null;
            }
        }
        return pusheshKhatarZelzele;
    }

    private Double[] mohaasebeHaghBimeAmraazKhaas(int yearCounter, Short[] sens, Double[] sarmaayeAmraaz) {
        Double[] hagheBimeAmraz = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < hagheBimeAmraz.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                short sen = sens[personIndex];
                double zarib = 0.0;
                if (multipleGeneralAssumptions.Ezafe_Nerkh_PezeshkiIsZero(personIndex)) {
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
                    hagheBimeAmraz[personIndex] = (zarib * sarmaayeAmraaz[personIndex] * multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime());
                } else {
                    if (15 <= multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter &&
                            multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 25) {
                        zarib = 0.00045;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 30) {
                        zarib = 0.00057;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 35) {
                        zarib = 0.00075;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 40) {
                        zarib = 0.0015;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 45) {
                        zarib = 0.0028;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 50) {
                        zarib = 0.0046;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 55) {
                        zarib = 0.0056;
                    } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex) + yearCounter <= 60) {
                        zarib = 0.0058;
                    } else {
                        zarib = 0.0;
                    }
                    if (yearCounter == 0)
                        hagheBimeAmraz[personIndex] = ((zarib * sarmaayeAmraaz[personIndex] *
                                multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()));
                    else {
                        hagheBimeAmraz[personIndex] = ((zarib * sarmaayeAmraaz[personIndex] *
                                multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime()));
                    }
                }
            } else {
                hagheBimeAmraz[personIndex] = null;
            }
        }
        return hagheBimeAmraz;
    }

    private Double[] mohaasebeHaghBimePeyvandAzaa(Double[] haghBimeAmraazKhaas) {
        Double[] haghBimePeyvandAzaa = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < haghBimePeyvandAzaa.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                haghBimePeyvandAzaa[personIndex] = (0.1 * haghBimeAmraazKhaas[personIndex]);
            } else {
                haghBimePeyvandAzaa[personIndex] = null;
            }
        }
        return haghBimePeyvandAzaa;
    }

    private Double[] mohaasebeMajmuHaghBimePeyvandVaAmraaz(Double[] haghBimeAmraazKhaas, Double[] haghBimePeyvandAzaa) {
        Double[] majmuHaghBimePeyvandVaAmraaz = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < majmuHaghBimePeyvandVaAmraaz.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                majmuHaghBimePeyvandVaAmraaz[personIndex] = haghBimeAmraazKhaas[personIndex] + haghBimePeyvandAzaa[personIndex];
            } else {
                majmuHaghBimePeyvandVaAmraaz[personIndex] = null;
            }

        }
        return majmuHaghBimePeyvandVaAmraaz;

    }

    private Double[] mohaasebeHaghBimeAzKaarOftaadegi(int yearCounter, IRsMultiple iRs, String _date, Tarh tarh) {
        Double[] haghBimeAzKaarOftaadegi = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < haghBimeAzKaarOftaadegi.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                if (personIndex == 0) {
                    double moafiatAzPardakht = AsnadeSodorService.getMoafiatAzPardakht(_date, tarh);
                    boolean[] params = AsnadeSodorService.getMoafiatParam(_date, tarh);
                    double haghbimekhalesfotyekja = params[0] ? iRs.getSumHaghBimeKhaalesFotYekja() : 0;
                    double karmozd = params[1] ? iRs.getKaarmozd() : 0;
                    double hazineBimeGari = params[2] ? iRs.getHazineBimeGari() : 0;
                    double hazineEdari = params[3] ? iRs.getHazineEdari() : 0;
                    double zaribtabdilnahvepardakhthaghbime = params[4] ? multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() : 0;
                    double poosheshekhatarzelzele = params[5] ? iRs.getSumPusheshKhatarZelzele() : 0;
                    double haghbimefotdarasarhadese = params[6] ? iRs.getSumHaghBimeFotDarAsarHaadese() : 0;
                    double gheramatazkaroftadegivanaghseozv = params[7] ? iRs.getSumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() : 0;
                    double majmuehaghebimeyepeyvandvaamraz = params[8] ? iRs.getSumMajmuHaghBimePeyvandVaAmraaz() : 0;
                    double haghbimepoosheshfotmoghadam = iRs.getHaghBimeMoafiatDarSurateFoteMoghadam();
                    if (multipleGeneralAssumptions.Moaafiyat_Az_Pardakht_Hagh_Bime()[personIndex]) {
                        if (multipleGeneralAssumptions.Ezafe_Nerkh_PezeshkiIsZero(personIndex)) {
                            if (iRs.getSen()[personIndex] < HADE_AKSAR_SENNE_AMRAZ) {
                                haghBimeAzKaarOftaadegi[personIndex] = moafiatAzPardakht *
                                        ((haghbimekhalesfotyekja + karmozd
                                                + hazineBimeGari + hazineEdari)
                                                * zaribtabdilnahvepardakhthaghbime
                                                + poosheshekhatarzelzele + haghbimefotdarasarhadese
                                                + gheramatazkaroftadegivanaghseozv + majmuehaghebimeyepeyvandvaamraz + haghbimepoosheshfotmoghadam);
                            } else {
                                haghBimeAzKaarOftaadegi[personIndex] = 0.0;
                            }
                        } else if (multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(0) + yearCounter < HADE_AKSAR_SENNE_AMRAZ) {
                            haghBimeAzKaarOftaadegi[personIndex] = moafiatAzPardakht *
                                    ((haghbimekhalesfotyekja + karmozd
                                            + hazineBimeGari + hazineEdari)
                                            * zaribtabdilnahvepardakhthaghbime
                                            + poosheshekhatarzelzele + haghbimefotdarasarhadese
                                            + gheramatazkaroftadegivanaghseozv + majmuehaghebimeyepeyvandvaamraz + haghbimepoosheshfotmoghadam);
                        } else {
                            haghBimeAzKaarOftaadegi[personIndex] = 0.0;
                        }
                    } else {
                        haghBimeAzKaarOftaadegi[personIndex] = 0.0;
                    }
                } else {
                    haghBimeAzKaarOftaadegi[personIndex] = 0.0;
                }
            } else {
                haghBimeAzKaarOftaadegi[personIndex] = null;
            }
        }
        return haghBimeAzKaarOftaadegi;
    }

    private Double[] mohaasebeHaghBimePusheshHaayeEzaafi(IRsMultiple iRs) {
        Double[] haghBimePusheshHaayeEzaafi = new Double[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < haghBimePusheshHaayeEzaafi.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                haghBimePusheshHaayeEzaafi[personIndex] = iRs.getMajmuHaghBimeHadeseVaZelzele()[personIndex]
                        + iRs.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv()[personIndex]
                        + iRs.getMajmuHaghBimePeyvandVaAmraaz()[personIndex]
                        + iRs.getHaghBimeAzKaarOftaadegi()[personIndex]
                        + (personIndex == 0 ? iRs.getHaghBimeMoafiatDarSurateFoteMoghadam() : 0);
            } else {
                haghBimePusheshHaayeEzaafi[personIndex] = null;
            }
        }
        return haghBimePusheshHaayeEzaafi;
    }

    private double mohaasebeEhtemaleFoteBimeShodeAvalBeSharteHayateBimeShodeDovom(int yearCount, IRsMultiple iRsMultiple, double[][] lifeTable, double nerkheBahreFanni) {
        if(!multipleGeneralAssumptions.moafiatFoteMoghadam()){
            return 0;
        }
        double qx = -1;
        double qy = -1;
        for (int i = 0; i < lifeTable.length; ++i) {
            if (lifeTable[i][0] == iRsMultiple.getSen()[0]) {
                qx = lifeTable[i][8];
            }
            if (lifeTable[i][0] == iRsMultiple.getSen()[1]) {
                qy = lifeTable[i][8];
            }
            if (qx != -1 && qy != -1) {
                break;
            }
        }
        if (qx == -1 || qy == -1) {
            return 1;
        }

        return qx * (1 - (0.5 * qy)) * (Math.pow(1 + nerkheBahreFanni, 0.5));
    }

    private double mohaasebeMaaliyaatBarArzeshAfzude(IRsMultiple iRs, double darsadMaliat, String _date, Tarh tarh) {
        boolean[] params = AsnadeSodorService.getMaliatParam(_date, tarh);
        double haghbimekhalesfotyekja = params[0] ? iRs.getSumHaghBimeKhaalesFotYekja() : 0;
        double karmozd = params[1] ? iRs.getKaarmozd() : 0;
        double hazineBimeGari = params[2] ? iRs.getHazineBimeGari() : 0;
        double hazineEdari = params[3] ? iRs.getHazineEdari() : 0;
        double hazineVosoul = params[4] ? iRs.getHazineVosul() : 0;
        double zaribtabdilnahvepardakhthaghbime = params[5] ? multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() : 0;
        double poosheshekhatarzelzele = params[6] ? iRs.getSumPusheshKhatarZelzele() : 0;
        double haghbimefotdarasarhadese = params[7] ? iRs.getSumHaghBimeFotDarAsarHaadese() : 0;
        double gheramatazkaroftadegivanaghseozv = params[8] ? iRs.getSumPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv() : 0;
        double majmuehaghebimeyepeyvandvaamraz = params[9] ? iRs.getSumMajmuHaghBimePeyvandVaAmraaz() : 0;
        double haghbimeazkaroftadegi = params[10] ? iRs.getSumHaghBimeAzKaarOftaadegi() : 0;

        return darsadMaliat * (((haghbimekhalesfotyekja
                + karmozd + hazineBimeGari
                + hazineEdari + hazineVosoul)
                * zaribtabdilnahvepardakhthaghbime) + poosheshekhatarzelzele
                + haghbimefotdarasarhadese + gheramatazkaroftadegivanaghseozv
                + majmuehaghebimeyepeyvandvaamraz + haghbimeazkaroftadegi + iRs.getHaghBimeMoafiatDarSurateFoteMoghadam());
    }

    private double getSum(Double[] array) {
        double sum = 0;
        for (Double val : array) {
            if (val != null) {
                sum += val;
            }
        }
        return sum;
    }

    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini(int yearCounter, IRsMultiple iRs, IRsMultiple previousIRs, double nerkheBahre) {
        double previousAndukhte = 0.0;
        if(previousIRs != null) {
            previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad();
            if(nerkheBahre == 0.2)
                previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad();
            else if(nerkheBahre == 0.25)
                previousAndukhte = previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad();
        }
        double haghBimePardakhti = 0.0;
        if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getSumHaghBimePusheshHaayeEzaafi();
        else if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
            haghBimePardakhti = iRs.getHaghBimePardaakhti();
        if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
            if (yearCounter > 0) {
                return ((
                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousAndukhte
                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((
                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())
                ) * (1 + nerkheBahre));
            }
        } else if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
            if (yearCounter > 0) {
                return ((((haghBimePardakhti
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        + previousAndukhte
                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()
                        + (iRs.getMaaliyaatBarArzeshAfzude() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                        + (iRs.getSumHaghBimePusheshHaayeEzaafi() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
                        * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return ((((haghBimePardakhti
                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
                        * (1 + nerkheBahre));
            }
        } else {
            if (yearCounter > 0) {
                return
                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                + previousAndukhte
                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            } else if (yearCounter == 0) {
                return
                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
            }
        }
        return 0;
    }

//    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini20(int yearCounter, IRsMultiple iRs, IRsMultiple previousIRs, double nerkheBahre) {
//        double haghBimePardakhti = 0.0;
//        if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getSumHaghBimePusheshHaayeEzaafi();
//        else if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti();
//        if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
//            if (yearCounter > 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())
//                ) * (1 + nerkheBahre));
//            }
//        } else if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//            if (yearCounter > 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()
//                        + (iRs.getMaaliyaatBarArzeshAfzude() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                        + (iRs.getSumHaghBimePusheshHaayeEzaafi() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
//                        * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
//                        * (1 + nerkheBahre));
//
//            }
//        } else {
//            if (yearCounter > 0) {
//                return
//                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud20Darsad()
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return
//                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            }
//        }
//        return 0;
//    }
//
//    private double mohaasebeAndukhteSarmaayeGozaariBaaSudTazmini22(int yearCounter, IRsMultiple iRs, IRsMultiple previousIRs, double nerkheBahre) {
//        double haghBimePardakhti = 0.0;
//        if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getSumHaghBimePusheshHaayeEzaafi();
//        else if (multipleGeneralAssumptions.getNahve_kasr_hazineha_az_hagh_bime() == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE)
//            haghBimePardakhti = iRs.getHaghBimePardaakhti();
//        if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 1) {
//            if (yearCounter > 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((
//                        ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())
//                ) * (1 + nerkheBahre));
//            }
//        } else if (multipleGeneralAssumptions.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() == 0) {
//            if (yearCounter > 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()
//                        + (iRs.getMaaliyaatBarArzeshAfzude() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                        + (iRs.getSumHaghBimePusheshHaayeEzaafi() / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())))
//                        * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return ((((haghBimePardakhti
//                        + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                        - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                        + iRs.getHazineEdari() + iRs.getHazineVosul()))
//                        * (1 + nerkheBahre));
//            }
//        } else {
//            if (yearCounter > 0) {
//                return
//                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                + previousIRs.getAndukhteSarmaayeGozaariBaaSud22Darsad()
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            } else if (yearCounter == 0) {
//                return
//                        (((((haghBimePardakhti / multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime())
//                                + iRs.getMablaghSepordeEbtedaayeSaal()) * (1 + (0.05 * discountAssumptions.Kaarmozd_Az_Mahal_Hagh_Bimeh())))
//                                - (iRs.getSumHaghBimeKhaalesFotYekja() + iRs.getKaarmozd() + iRs.getHazineBimeGari()
//                                + iRs.getHazineEdari() + iRs.getHazineVosul())) * (1 + nerkheBahre));
//            }
//        }
//        return 0;
//    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini15Darsad(int yearCounter, List<IRsMultiple> iRListTemp, IRsMultiple iRs) {
        double summation = sum(iRListTemp, yearCounter + 1);
        double resultDouble = (iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad() - summation);
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

    private double mohaasebeArzeshBazKharidBaaSudTazmini20Darsad(int yearCounter, List<IRsMultiple> iRListTemp, IRsMultiple iRs) {
//        double arzesheBazkharidBaSud15Darsad = iRs.getArzeshBazKharidBaaSudTazmini15Darsad();
//        double andukhteSarmayeGozariBasud20Darsad = iRs.getAndukhteSarmaayeGozaariBaaSud20Darsad();
//        double andukhteyeSarmayeGozriBaSoudTazmini15Darsad = iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad();
//        return arzesheBazkharidBaSud15Darsad
//                + (0.85 * (andukhteSarmayeGozariBasud20Darsad - andukhteyeSarmayeGozriBaSoudTazmini15Darsad));
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
    }

    private double mohaasebeArzeshBazKharidBaaSudTazmini22Darsad(int yearCounter, List<IRsMultiple> iRListTemp, IRsMultiple iRs) {
//        return iRs.getArzeshBazKharidBaaSudTazmini15Darsad()
//                + (0.85 * (iRs.getAndukhteSarmaayeGozaariBaaSud22Darsad() - iRs.getAndukhteSarmaayeGozaariBaaSudTazmini15Darsad()));
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
    }

    private double sum(List<IRsMultiple> iRListTemp, int start) {
        double sum = 0;
        int counter = 0;
        for (IRsMultiple iRs : iRListTemp) {
            if (counter >= start) {
                sum += iRs.getHazineBimeGari();
            }
            counter++;
        }
        return sum;
    }

    private double mohaasebeHagheBimeMoafiatDarSurateFoteBimeShodeAvalVaHayateBimeShodeDovom(double sarmayeMoafiatFoteMoghadamBimeShodeAval, double ehtemaleMargeBimeShodeAvalDarSurateHayateBimeShodeDovom) {
        return sarmayeMoafiatFoteMoghadamBimeShodeAval * ehtemaleMargeBimeShodeAvalDarSurateHayateBimeShodeDovom * multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
    }

    private Short[] getSenneBimeShodePasAzEmaleEzafeNerkh() {
        Short[] sen = new Short[multipleGeneralAssumptions.maxPersonsCount()];
        for (int personIndex = 0; personIndex < sen.length; ++personIndex) {
            if (multipleGeneralAssumptions.isPersonRegistered(personIndex)) {
                sen[personIndex] = multipleGeneralAssumptions.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex);
            } else {
                sen[personIndex] = null;
            }
        }
        return sen;
    }

    private double mohaasebeAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad(int yearCount,IRsMultiple previousIRsMultiple,IRsMultiple newIRsMultiple){
        short saal = mohaasebeSaal(yearCount);
        double previousAndukhte = previousIRsMultiple != null ? previousIRsMultiple.getAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad() : 0;
        double haghBimePardakhti = newIRsMultiple.getHaghBimePardaakhti();
        double zaribTabdilNahvePardakht = saal <= 5 ? multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime_5Sal_Avval() : multipleGeneralAssumptions.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime();
        double sumHaghBimeFot = newIRsMultiple.getSumHaghBimeKhaalesFotYekja();
        double karmozd = newIRsMultiple.getKaarmozd();
        double bimegari = newIRsMultiple.getHazineBimeGari();
        double edari = newIRsMultiple.getHazineEdari();
        double vosul = newIRsMultiple.getHazineVosul();
        double nerkhBahreFanni = saal<=5 ? multipleGeneralAssumptions.getNerkhBahreFanni5SaleAvval() : multipleGeneralAssumptions.getNerkhBahreFanni();
        return (previousAndukhte + (haghBimePardakhti / zaribTabdilNahvePardakht) -(sumHaghBimeFot + karmozd + bimegari + edari + vosul))*(1+nerkhBahreFanni);
    }

    private String debugIRsList(List<IRsMultiple> list) {
        String debugText = "";
        for (IRsMultiple item : list) {
            debugText += addDebugItem(item.getSaal());
            debugText += addDebugItem(item.getHaghBimePardaakhti(), "long");
            debugText += addDebugItem(item.getSen());
            debugText += addDebugItem(item.getHaghBimeKhaalesFotYekja(), "long");
            debugText += addDebugItem(item.getSarmaayeFot(), "long");
            debugText += addDebugItem(item.getSarmaayeHaadeseh(), "long");
            debugText += addDebugItem(item.getSarmaayeNaghsOzv(), "long");
            debugText += addDebugItem(item.getSarmaayeAmraaz(), "long");
            debugText += addDebugItem(item.getKaarmozd(), "long");
            debugText += addDebugItem(item.getHazineBimeGari(), "long");
            debugText += addDebugItem(item.getHazineEdari(), "long");
            debugText += addDebugItem(item.getHazineVosul(), "long");
            debugText += addDebugItem(item.getMajmuHaghBimeHadeseVaZelzele(), "long");
            debugText += addDebugItem(item.getHaghBimeAmraazKhaas(), "long");
            debugText += addDebugItem(item.getHaghBimePeyvandAzaa(), "long");
            debugText += addDebugItem(item.getMajmuHaghBimePeyvandVaAmraaz(), "long");
            debugText += addDebugItem(item.getEhtemaleMargeBimeShodeAvalBeSharteHayateBimeShodeDovom(), "double");
            debugText += addDebugItem(item.getSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval(), "long");
            debugText += addDebugItem(0.0, "long");
            debugText += addDebugItem(item.getHaghBimeAzKaarOftaadegi()[0], "long");
            debugText += addDebugItem(item.getPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(), "long");
            debugText += addDebugItem(item.getSumHaghBimePusheshHaayeEzaafi(), "long");
            debugText += addDebugItem(item.getMaaliyaatBarArzeshAfzude(), "long");
            debugText += addDebugItem(item.getAndukhteTa5Sal18DarsadMazadTa10Sal15DarsadMazadTaEnteha10Darsad(),"long");
            debugText += addDebugItem(item.getArzeshBazKharidBaaSudTazmini15Darsad(), "long");
            debugText += addDebugItem(item.getAndukhteSarmaayeGozaariBaaSud20Darsad(), "long");
            debugText += addDebugItem(item.getArzeshBazKharidBaaSud20Darsad(),"long");
            debugText += addDebugItem(item.getAndukhteSarmaayeGozaariBaaSud22Darsad(), "long");
            debugText += addDebugItem(item.getArzeshBazKharidBaaSud22Darsad(), "long");
            debugText += "\n";
        }
        return debugText;
    }

    private String addDebugItem(Double item, String type) {
        if (item == null) {
            return "\t";
        }
        if (type.equalsIgnoreCase("long")) {
            return item.longValue() + "\t";
        }
        if (type.equalsIgnoreCase("double")) {
            return item + "\t";
        }
        return item + "\t";
    }

    private String addDebugItem(Short item) {
        if (item == null) {
            return "\t";
        }
        return item + "\t";
    }

    private String addDebugItem(Double[] items, String type) {
        String str = "";
        for (Double d : items) {
            str += addDebugItem(d, type);
        }
        return str;
    }

    private String addDebugItem(Short[] items) {
        String str = "";
        for (Short s : items) {
            str += addDebugItem(s);
        }
        return str;
    }

    private String debugMatriseMohasebeSarmayeMoafiatFoteMoghadam(double[][] mat) {
        String str = "";
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                str += new Double(mat[j][i]).longValue() + "\t";
            }
            str += "\n";
        }
        return str;
    }
}
