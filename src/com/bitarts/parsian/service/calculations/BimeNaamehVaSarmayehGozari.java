package com.bitarts.parsian.service.calculations;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.calculations.Reports.IRs;
import com.bitarts.parsian.viewModel.BimenameInfo;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 10, 2011
 * Time: 1:15:36 PM
 */
public class BimeNaamehVaSarmayehGozari implements NSPConstants {
    private List<IRs> iRsList;
    private List<FRs> fRsList;
    private int Modat_Bime_Naameh = 0;

    public class CustomValidationException extends Exception {
        private String myMessage = "";

        public CustomValidationException(String myMessage) {
            super();
            this.myMessage = myMessage;
        }

        public CustomValidationException() {
            super();
        }

        public String getMyMessage() {
            return myMessage;
        }
    }

    public List<FRs> finalReports(short Sene_Bime_Shode, double Darsad_Ezafe_Nerkh_Pezeshki, short Modat_Bime_Naameh,
                                  long Sarmaye_Paaye_Fot_Rial, long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                  boolean pushesh_Moaafiyat, boolean Pushesh_Naghs_Ozv, long Sarmaye_Pushesh_Naghs_Ozv,
                                  double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                  long Hagh_Bime_Pardaakhti_Rial, long Mablagh_Seporde_Ebtedaye_Saal_Rial, NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                  double Nerkh_Afzayesh_Saalaaneh_Mostamari, NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri, short Modat_Zaman_Daryaft_Mostamari,
                                  byte Tabaghe_Khatar, byte Tabaghe_Khatar_Naghsozv, boolean Pushesh_Fot_Dar_Asar_Zelzele, double Bimeh_Gari, double Edari,
                                  double Kaarmozd_Az_Mahal_Hagh_Bimeh, double Kaarmozd_Az_Mahal_Kaarmozd, double Hazineh_Vosul, double nerkhBahreFanni, long hadeAksarSarFot, long hadeAksarSarmayeFotHadese,
                                  long hadeAksarAmraz, double nerkhPusheshZelzele, double karmozdGyekja, double karmozdyekja, double bimegariGyekja, double bimegariyekja, double edariGyekja,
                                  double vosulGyekja, double[][] lifeTable, String _date, Tarh tarh, NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime,
                                  Constants.PayeyeMohasebeHazineEdari payeyeMohasebat)
            throws CustomValidationException {
        TaeenAndukhteBarMabnaayeHaghBimePardaakhti taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhti();

        this.iRsList = taeenAndukhteBarMabnaayeHaghBimePardaakhti.intermediateReports(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
                Hagh_Bime_Pardaakhti_Rial, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar, Tabaghe_Khatar_Naghsozv, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari, Edari,
                Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul, nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese,
                hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja, lifeTable, _date, tarh, nahve_kasr_hazineha_az_hagh_bime, payeyeMohasebat);

        this.Modat_Bime_Naameh = Modat_Bime_Naameh;

        fRsList = new ArrayList<FRs>();
        int yearCount = 0;
        FRs previusFRsFirst = null;
        String currentDate = _date;
        for (IRs iRs : iRsList) {
            FRs fRs = new FRs();
            fRs.setSenneBimeShodePasAzEmaleEzafeNerkh(iRs.getSenneBimeShodePasAzEmaleEzafeNerkh());
            fRs.setSen(iRs.getSen());
            fRs.setSaal(iRs.getSaal());
            fRs.setSarmaayeFotBehHarEllat(iRs.getSarmaayeFot());
            fRs.setSarmaayeFotDarAsarHaadeseh(iRs.getSarmaayeHaadeseh());
            fRs.setSarmaayePusheshEzaafiAmraazKhaas(iRs.getSarmaayeAmraaz());
            fRs.setSarmaayePusheshEzaafiNaghsOzv(iRs.getSarmaayeNaghsOzv());
            fRs.setHaghBimePardaakhtiSaal(iRs.getHaghBimePardaakhtiSaal());
            fRs.setMajmuHaghBimePardaakhtiSaal(iRs.getMajmuHaghBimePardaakhtiSaal());
            fRs.setHaghBimePusheshHaayeEzaafi(iRs.getHaghBimePusheshHaayeEzaafi());
            fRs.setMajmuHaghBimePusheshHaayeEzaafi(iRs.getMajmuHaghBimePusheshHaayeEzaafi());
            fRs.setMaaliyaatBarArzeshAfzude(iRs.getMaaliyaatBarArzeshAfzude());
            fRs.setMajmuKolMabaaleghPardaakhti(iRs.getMajmuKolMabaaleghPardaakhti());
            fRs.setArzeshBazKharidBaaSudTazmini15Darsad(iRs.getArzeshBazKharidBaaSudTazmini15Darsad());
            fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(iRs.getArzeshBazKharidBaaSud20Darsad());
            fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(iRs.getArzeshBazKharidBaaSud22Darsad());
            fRs.setPoosheshMoafiat(this.mohaasebePoosheshMoafiat(pushesh_Moaafiyat, iRs.getSenneBimeShodePasAzEmaleEzafeNerkh()));


            fRs.setNahvePardakhteHagheBime(Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH ? "ماهانه" :
                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH ? "سالانه" :
                            Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE ? "سه ماهه" :
                                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE ? "شش ماهه" : "یکجا");
            fRs.setTarikheShoro(currentDate);
            currentDate = DateUtil.addYear(currentDate, 1);
            fRs.setTarikheKhatame(currentDate);

            previusFRsFirst = fRs;
            fRsList.add(fRs);
            yearCount++;
        }

        if (Mablagh_Seporde_Ebtedaye_Saal_Rial == 0) {
            for (IRs iRs : iRsList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
                double haghBimePardakhti = 0.0;
                if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                    haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
                else if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE || nahve_kasr_hazineha_az_hagh_bime == null)
                    haghBimePardakhti = iRs.getHaghBimePardaakhti();
                if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                    if (((haghBimePardakhti) / 1.067) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                    if (((haghBimePardakhti) / 1.0546) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                    if (((haghBimePardakhti) / 1.0362) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                    if ((haghBimePardakhti) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                }
            }
        } else if ((Mablagh_Seporde_Ebtedaye_Saal_Rial != 0) && (Hagh_Bime_Pardaakhti_Rial == 0)) {
            for (IRs iRs : iRsList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
                if (nahve_kasr_hazineha_az_hagh_bime.equals(NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE))
                    theValue += iRs.getMajmuHaghBimePusheshHaayeEzaafi() + iRs.getMaaliyaatBarArzeshAfzude();
                if ((Mablagh_Seporde_Ebtedaye_Saal_Rial / Modat_Bime_Naameh) < theValue) {
                    throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                }
            }
        } else if ((Mablagh_Seporde_Ebtedaye_Saal_Rial != 0) && (Hagh_Bime_Pardaakhti_Rial != 0)) {
            int yearCounter = 0;
            for (IRs iRs : iRsList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
                if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.067) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0546) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0362) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                    if (yearCounter == 0) {
                        if ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else {
                        if ((iRs.getHaghBimePardaakhti()) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    }
                }
                yearCounter++;
            }
        }
        for (FRs frs : fRsList) {
            if (frs.getArzeshBazKharidBaaSudTazmini15Darsad() < 0)
                throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
            if (frs.getPishbiniArzeshBazKharidBaaSud20Darsad() < 0)
                throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
            if (frs.getPishbiniArzeshBazKharidBaaSud22Darsad() < 0)
                throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
        }
        return fRsList;
    }

    public List<FRs> finalReportsWithPreviousFRs(short Sene_Bime_Shode, double Darsad_Ezafe_Nerkh_Pezeshki, short Modat_Bime_Naameh,
                                                 long Sarmaye_Paaye_Fot_Rial, long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                                 boolean pushesh_Moaafiyat, boolean Pushesh_Naghs_Ozv, long Sarmaye_Pushesh_Naghs_Ozv,
                                                 double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                                 long Hagh_Bime_Pardaakhti_Rial, long Mablagh_Seporde_Ebtedaye_Saal_Rial, NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                                 double Nerkh_Afzayesh_Saalaaneh_Mostamari, NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri, short Modat_Zaman_Daryaft_Mostamari,
                                                 byte Tabaghe_Khatar, byte Tabaghe_Khatar_Naghsozv, boolean Pushesh_Fot_Dar_Asar_Zelzele, double Bimeh_Gari, double Edari,
                                                 double Kaarmozd_Az_Mahal_Hagh_Bimeh, double Kaarmozd_Az_Mahal_Kaarmozd, double Hazineh_Vosul, double nerkhBahreFanni, long hadeAksarSarFot, long hadeAksarSarmayeFotHadese,
                                                 long hadeAksarAmraz, double nerkhPusheshZelzele, double karmozdGyekja, double karmozdyekja, double bimegariGyekja, double bimegariyekja, double edariGyekja,
                                                 double vosulGyekja, double[][] lifeTable, String _date, Tarh tarh, NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime,
                                                 Constants.PayeyeMohasebeHazineEdari payeyeMohasebat, FRs previousFRs, FRs currentFRs, long sarmayePayeFotSaleAvval, boolean enteghalFot,
                                                 boolean enteghalHadese, boolean enteghalAmraz, boolean enteghalNaghs, boolean enteghalHaghBime, int salHadeseNaghs, int salHadeseAmraz, int salInvalid, BimenameInfo bInfo)
            throws CustomValidationException {
        TaeenAndukhteBarMabnaayeHaghBimePardaakhti taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhti();

        Modat_Bime_Naameh += 1;
        IRs previousIRs = null;
        IRs currentIRs = null;
        if (previousFRs != null) {
            previousIRs = previousFRs.getiRs();
            if (enteghalFot)
                Sarmaye_Paaye_Fot_Rial = (long) currentFRs.getiRs().getSarmaayeFot();
            if (enteghalHadese)
                Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial = (long) currentFRs.getiRs().getSarmaayeHaadeseh();
            if (enteghalAmraz)
                Sarmaye_Pushesh_Amraaz_Khaas_Rial = (long) currentFRs.getiRs().getSarmaayeAmraaz();
            if (enteghalNaghs)
                Sarmaye_Pushesh_Naghs_Ozv = (long) currentFRs.getiRs().getSarmaayeNaghsOzv();
            if (enteghalHaghBime)
                Hagh_Bime_Pardaakhti_Rial = (long) currentFRs.getiRs().getHaghBimePardakhtiGhest();
            currentIRs = currentFRs.getiRs();
        }

        this.iRsList = taeenAndukhteBarMabnaayeHaghBimePardaakhti.intermediateReportsWithPreviousIRs(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
                Hagh_Bime_Pardaakhti_Rial, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
                Tabaghe_Khatar, Tabaghe_Khatar_Naghsozv, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari, Edari,
                Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul, nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese,
                hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja, lifeTable,
                _date, tarh, nahve_kasr_hazineha_az_hagh_bime, payeyeMohasebat, previousIRs, currentIRs, salHadeseNaghs, salHadeseAmraz, bInfo);

        this.Modat_Bime_Naameh = Modat_Bime_Naameh;

        fRsList = new ArrayList<FRs>();
        String currentDate = _date;
        if (bInfo != null && bInfo.getBimename() != null) currentDate = bInfo.getBimename().getTarikhShorou();
        if (previousFRs != null) currentDate = DateUtil.addYear(currentDate, previousFRs.getSaal());
        for (IRs iRs : iRsList) {
            FRs fRs = new FRs();
            fRs.setSenneBimeShodePasAzEmaleEzafeNerkh(iRs.getSenneBimeShodePasAzEmaleEzafeNerkh());
            fRs.setSen(iRs.getSen());
            fRs.setSaal(iRs.getSaal());
            fRs.setSarmaayeFotBehHarEllat(iRs.getSarmaayeFot());
            fRs.setSarmaayeFotDarAsarHaadeseh(iRs.getSarmaayeHaadeseh());
            fRs.setSarmaayePusheshEzaafiAmraazKhaas(iRs.getSarmaayeAmraaz());
            fRs.setSarmaayePusheshEzaafiNaghsOzv(iRs.getSarmaayeNaghsOzv());
            fRs.setHaghBimePardaakhtiSaal(iRs.getHaghBimePardaakhtiSaal());
            fRs.setMajmuHaghBimePardaakhtiSaal(iRs.getMajmuHaghBimePardaakhtiSaal());
            fRs.setHaghBimePardaakhti(iRs.getHaghBimePardaakhti());
            fRs.setHaghBimePusheshHaayeEzaafi(iRs.getHaghBimePusheshHaayeEzaafi());
            fRs.setMajmuHaghBimePusheshHaayeEzaafi(iRs.getMajmuHaghBimePusheshHaayeEzaafi());
            fRs.setMaaliyaatBarArzeshAfzude(iRs.getMaaliyaatBarArzeshAfzude());
            fRs.setMajmuKolMabaaleghPardaakhti(iRs.getMajmuKolMabaaleghPardaakhti());
            fRs.setArzeshBazKharidBaaSudTazmini15Darsad(iRs.getArzeshBazKharidBaaSudTazmini15Darsad());
            fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(iRs.getArzeshBazKharidBaaSud20Darsad());
            fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(iRs.getArzeshBazKharidBaaSud22Darsad());
            fRs.setPoosheshMoafiat(this.mohaasebePoosheshMoafiat(pushesh_Moaafiyat, iRs.getSenneBimeShodePasAzEmaleEzafeNerkh()));
            fRs.setHaghBimePardakhtiBase(iRs.getMablaghSepordeEbtedaayeSaal());


            fRs.setNahvePardakhteHagheBime(Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH ? "ماهانه" :
                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH ? "سالانه" :
                            Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE ? "سه ماهه" :
                                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE ? "شش ماهه" : "یکجا");
            fRs.setTarikheShoro(currentDate);
            currentDate = DateUtil.addYear(currentDate, 1);
            fRs.setTarikheKhatame(currentDate);

            fRs.setiRs(iRs);

            fRsList.add(fRs);
        }

        int yearsWithoutValidation = 0;
        if (bInfo.getBimename() != null && bInfo.getBimename().getConverted() != null && bInfo.getBimename().getConverted().equals("yes")) {
            yearsWithoutValidation = 1;
        }

        if (bInfo.getBimename() != null && bInfo.getBimename().getPishnehad().getAdameControllMohasebat() != null && bInfo.getBimename().getPishnehad().getAdameControllMohasebat()) {
            yearsWithoutValidation = 100;
        }

        if (Mablagh_Seporde_Ebtedaye_Saal_Rial == 0) {
            for (IRs iRs : iRsList) {
                if (iRs.getSaal() < Modat_Bime_Naameh && iRs.getSaal() > yearsWithoutValidation) {
                    double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
//                if (iRs.getSaal() > 0) {
//                    theValue += +iRs.getHaghBimePusheshHaayeEzaafi();
//                }
                    double haghBimePardakhti = 0.0;
                    if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                        haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
                    else if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE || nahve_kasr_hazineha_az_hagh_bime == null)
                        haghBimePardakhti = iRs.getHaghBimePardaakhti();
                    if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                        if (((haghBimePardakhti) / 1.067) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                        if (((haghBimePardakhti) / 1.0546) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                        if (((haghBimePardakhti) / 1.0362) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                        if ((haghBimePardakhti) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    }
                }
            }
        } else if ((Mablagh_Seporde_Ebtedaye_Saal_Rial != 0) && (Hagh_Bime_Pardaakhti_Rial == 0)) {
            for (IRs iRs : iRsList) {
                if (iRs.getSaal() < Modat_Bime_Naameh && iRs.getSaal() > yearsWithoutValidation) {
                    double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
                    if (nahve_kasr_hazineha_az_hagh_bime.equals(NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE))
                        theValue += iRs.getMajmuHaghBimePusheshHaayeEzaafi() + iRs.getMaaliyaatBarArzeshAfzude();
                    if ((Mablagh_Seporde_Ebtedaye_Saal_Rial / (Modat_Bime_Naameh - 1)) < theValue) {
                        throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                    }
                }
            }
        } else if ((Mablagh_Seporde_Ebtedaye_Saal_Rial != 0) && (Hagh_Bime_Pardaakhti_Rial != 0)) {
            int yearCounter = 0;
            for (IRs iRs : iRsList) {
                if (iRs.getSaal() < Modat_Bime_Naameh && iRs.getSaal() > yearsWithoutValidation) {
                    double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
                    double haghBimePardakhti = 0.0;
                    if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                        haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getHaghBimePusheshHaayeEzaafi();
                    else if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE || nahve_kasr_hazineha_az_hagh_bime == null)
                        haghBimePardakhti = iRs.getHaghBimePardaakhti();
                    if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                        if (((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.067) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                        if (((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0546) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                        if (((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0362) < theValue) {
                            throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                        }
                    } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                        if (yearCounter == 0) {
                            if ((haghBimePardakhti + iRs.getMablaghSepordeEbtedaayeSaal()) < theValue) {
                                throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                            }
                        } else {
                            if ((haghBimePardakhti) < theValue) {
                                throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + iRs.getSaal() + "ام منفی یا صفر است.");
                            }
                        }
                    }
                }
                yearCounter++;
            }
        }
        for (FRs frs : fRsList) {
            if (frs.getSaal() < Modat_Bime_Naameh && frs.getSaal() > yearsWithoutValidation) {
                if (frs.getArzeshBazKharidBaaSudTazmini15Darsad() < 0)
                    throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
                if (frs.getPishbiniArzeshBazKharidBaaSud20Darsad() < 0)
                    throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
                if (frs.getPishbiniArzeshBazKharidBaaSud22Darsad() < 0)
                    throw new CustomValidationException("مبلغ سرمایه گذاری در سال " + frs.getiRs().getSaal() + "ام منفی یا صفر است.");
            }
        }

        for (FRs fRs : fRsList) {

            if (fRs.getSaal() >= salInvalid) {
                fRs.setSarmaayeFotBehHarEllat(0);
                fRs.setSarmaayeFotDarAsarHaadeseh(0);
                fRs.setSarmaayePusheshEzaafiAmraazKhaas(0);
                fRs.setSarmaayePusheshEzaafiNaghsOzv(0);
                fRs.setHaghBimePardaakhtiSaal(0);
                fRs.setMajmuHaghBimePardaakhtiSaal(0);
                fRs.setHaghBimePusheshHaayeEzaafi(0);
                fRs.setMajmuHaghBimePusheshHaayeEzaafi(0);
                fRs.setMaaliyaatBarArzeshAfzude(0);
                fRs.setMajmuKolMabaaleghPardaakhti(0);
                fRs.setArzeshBazKharidBaaSudTazmini15Darsad(0);
                fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(0);
                fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(0);
                fRs.setPoosheshMoafiat("ندارد");
                fRs.getiRs().setHaghBimePardaakhti(0);
                fRs.getiRs().setHaghBimePardakhtiGhest(0);
                fRs.getiRs().setMablaghSepordeEbtedaayeSaal(0);
                fRs.getiRs().setHaghBimePardaakhtiSaal(0);
                fRs.getiRs().setSarmaayeFot(0);
                fRs.getiRs().setSarmayeFotSaleAvval(0);
                fRs.getiRs().setHaghBimePardaakhtiSaleAvval(0);
                fRs.getiRs().setSarmaayeHaadeseh(0);
                fRs.getiRs().setSarmaayeNaghsOzv(0);
                fRs.getiRs().setSarmaayeAmraaz(0);
                fRs.getiRs().setHaghBimeKhaalesFotYekja(0);
                fRs.getiRs().setArzeshAyandehHaghBimeHaayeKhaalesFot(0);
                fRs.getiRs().setKaarmozd(0);
                fRs.getiRs().setHazineBimeGari(0);
                fRs.getiRs().setHazineEdari(0);
                fRs.getiRs().setHazineVosul(0);
                fRs.getiRs().setHaghBimeFotDarAsarHaadese(0);
                fRs.getiRs().setPardaakhtGharaamatAzKaarOftadegiVaNaghsOzv(0);
                fRs.getiRs().setPusheshKhatarZelzele(0);
                fRs.getiRs().setHaghBimeAmraazKhaas(0);
                fRs.getiRs().setHaghBimePeyvandAzaa(0);
                fRs.getiRs().setMajmuHaghBimePeyvandVaAmraaz(0);
                fRs.getiRs().setHaghBimeAzKaarOftaadegi(0);
                fRs.getiRs().setHaghBimePusheshHaayeEzaafi(0);
                fRs.getiRs().setMaaliyaatBarArzeshAfzude(0);
                fRs.getiRs().setAndukhteSarmaayeGozaariBaaSudTazmini15Darsad(0);
                fRs.getiRs().setAndukhteSarmaayeGozaariBaaSud20Darsad(0);
                fRs.getiRs().setAndukhteSarmaayeGozaariBaaSud22Darsad(0);
                fRs.getiRs().setArzeshBazKharidBaaSud20Darsad(0);
                fRs.getiRs().setArzeshBazKharidBaaSud22Darsad(0);
                fRs.getiRs().setMajmuHaghBimePardaakhtiSaal(0);
                fRs.getiRs().setMajmuHaghBimePusheshHaayeEzaafi(0);
                fRs.getiRs().setMajmuKolMabaaleghPardaakhti(0);
            }
        }

        return fRsList;
    }


    private String mohaasebePoosheshMoafiat(boolean pushesh_Moaafiyat, short sen) {
        if (!pushesh_Moaafiyat) {
            return "ندارد";
        } else {
            if (sen >= 60) {
                return "ندارد";
            } else {
                return "دارد";
            }
        }
    }

    private double mohasebeQm(int m, int seneBimeShode1, int seneBimeShode2, double[][] lifeTable, double nerkheBahreFani) {
        double qx = lifeTable[seneBimeShode1 + (m - 1)][8];
        double qy = lifeTable[seneBimeShode2 + (m - 1)][8];
        return qx * (1 - (0.5) * qy) * pow((1 + nerkheBahreFani), 0.5);
    }

    private double mohasebePf(int m, int seneBimeShode1, double[][] lifeTable, double sarmayeFot, double nerkheBahreFani) {

        double qx = lifeTable[seneBimeShode1 + (m - 1)][8];
        return sarmayeFot * qx * pow((1 / (1 + nerkheBahreFani)), 0.5);
    }


    public double mohasebeHagheBimePoosheshMoafiat(double sarmayeFot, int seneBimeShode1, int seneBimeShode2, double[][] lifeTable, int year, int modatBimename, double mablaghHagheBimeSalane,
                                                   double majmooHaghBimePoosheshhayeEzafi, double haghBimeFotDarAsarHadese, double haghBimeAmraz,
                                                   double haghBimeMoafiat, double haghBimeNaghsOzv, double zaribTabdilSalianePardakhtha, double nerkheBahreFanni) {
        /*
           year == m,k
           year + 1 == k+1
           modateBimename == n
           modateBimename-1 == n-1
           zaribTabdilSalianePardakhtha == z
        */
        double pi = 1 + nerkheBahreFanni;
        double variableQm = mohasebeQm(year, seneBimeShode1, seneBimeShode2, lifeTable, nerkheBahreFanni);

        if (year == modatBimename - 1) {
            return
                    (
                            (
                                    (
                                            ((mablaghHagheBimeSalane + majmooHaghBimePoosheshhayeEzafi) - (haghBimeFotDarAsarHadese + haghBimeAmraz + haghBimeMoafiat + haghBimeNaghsOzv + 0))
                                                    /
                                                    zaribTabdilSalianePardakhtha
                                    ) - mohasebePf(year + 1, seneBimeShode1, lifeTable, sarmayeFot, nerkheBahreFanni)
                            ) / pi
                    ) * variableQm * zaribTabdilSalianePardakhtha;
        } else {
            double sigma =
                    (
                            (
                                    (mablaghHagheBimeSalane + majmooHaghBimePoosheshhayeEzafi)
                                            -
                                            (
                                                    haghBimeFotDarAsarHadese
                                                            +
                                                            haghBimeAmraz
                                                            +
                                                            haghBimeMoafiat
                                                            +
                                                            haghBimeNaghsOzv
                                                            +
                                                            mohasebeHagheBimePoosheshMoafiat(sarmayeFot, seneBimeShode1, seneBimeShode2, lifeTable, year + 1, modatBimename, mablaghHagheBimeSalane,
                                                                    majmooHaghBimePoosheshhayeEzafi, haghBimeFotDarAsarHadese, haghBimeAmraz, haghBimeMoafiat,
                                                                    haghBimeNaghsOzv, zaribTabdilSalianePardakhtha, nerkheBahreFanni)
                                            ) / zaribTabdilSalianePardakhtha
                            ) - mohasebePf(year + 1, seneBimeShode1, lifeTable, sarmayeFot, nerkheBahreFanni)
                    ) / pi;
            for (int i = year; i < (modatBimename - 1); i++) {
                for (int j = year; j < (modatBimename - 1); j++) {
                    pi = pi * (1 + nerkheBahreFanni);
                }
                sigma = sigma
                        +
                        (
                                (
                                        (
                                                (mablaghHagheBimeSalane + majmooHaghBimePoosheshhayeEzafi)
                                                        -
                                                        (
                                                                haghBimeFotDarAsarHadese
                                                                        +
                                                                        haghBimeAmraz
                                                                        +
                                                                        haghBimeMoafiat
                                                                        +
                                                                        haghBimeNaghsOzv
                                                                        +
                                                                        mohasebeHagheBimePoosheshMoafiat(sarmayeFot, seneBimeShode1, seneBimeShode2, lifeTable, year + 1, modatBimename, mablaghHagheBimeSalane,
                                                                                majmooHaghBimePoosheshhayeEzafi, haghBimeFotDarAsarHadese, haghBimeAmraz, haghBimeMoafiat,
                                                                                haghBimeNaghsOzv, zaribTabdilSalianePardakhtha, nerkheBahreFanni)
                                                        ) / zaribTabdilSalianePardakhtha
                                        ) - mohasebePf(year + 1, seneBimeShode1, lifeTable, sarmayeFot, nerkheBahreFanni)
                                ) / pi
                        );
            }
            return sigma * variableQm * zaribTabdilSalianePardakhtha;
        }
    }

//    public List<FRs> finalReportsReverse(short Sene_Bime_Shode, double Darsad_Ezafe_Nerkh_Pezeshki, short Modat_Bime_Naameh,
//                                         long Sarmaye_Paaye_Fot_Rial, long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
//                                         boolean pushesh_Moaafiyat, boolean Pushesh_Naghs_Ozv, long Sarmaye_Pushesh_Naghs_Ozv,
//                                         double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
//                                         long andukhte_entehaye_modate_bimename, long Mablagh_Seporde_Ebtedaye_Saal_Rial, NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
//                                         double Nerkh_Afzayesh_Saalaaneh_Mostamari, NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri, short Modat_Zaman_Daryaft_Mostamari,
//                                         byte Tabaghe_Khatar, boolean Pushesh_Fot_Dar_Asar_Zelzele, double Bimeh_Gari, double Edari,
//                                         double Kaarmozd_Az_Mahal_Hagh_Bimeh, double Kaarmozd_Az_Mahal_Kaarmozd, double Hazineh_Vosul, double nerkhBahreFanni, long hadeAksarSarFot, long hadeAksarSarmayeFotHadese,
//                                         long hadeAksarAmraz, double nerkhPusheshZelzele, double darsadMaliat, double karmozdGyekja, double karmozdyekja, double bimegariGyekja, double bimegariyekja, double edariGyekja,
//                                         double vosulGyekja, double[][] lifeTable, String _date, Tarh tarh)
//            throws CustomValidationException {
//        TaeenHaghBimePardakhtiBarMabnayeAndukhtePayaneModat taeenHaghBimePardakhtiBarMabnayeAndukhtePayaneModat = new TaeenHaghBimePardakhtiBarMabnayeAndukhtePayaneModat();
//        this.iRsList = taeenHaghBimePardakhtiBarMabnayeAndukhtePayaneModat.intermediateReports(Sene_Bime_Shode, Darsad_Ezafe_Nerkh_Pezeshki, Modat_Bime_Naameh,
//                Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
//                pushesh_Moaafiyat, Pushesh_Naghs_Ozv, Sarmaye_Pushesh_Naghs_Ozv,
//                Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, Nahve_Pardaakht_Hagh_Bime,
//                andukhte_entehaye_modate_bimename, Mablagh_Seporde_Ebtedaye_Saal_Rial, Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
//                Nerkh_Afzayesh_Saalaaneh_Mostamari, Nahve_Daryaft_Mostamri, Modat_Zaman_Daryaft_Mostamari,
//                Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari, Edari,
//                Kaarmozd_Az_Mahal_Hagh_Bimeh, Kaarmozd_Az_Mahal_Kaarmozd, Hazineh_Vosul, nerkhBahreFanni, hadeAksarSarFot, hadeAksarSarmayeFotHadese,
//                hadeAksarAmraz, nerkhPusheshZelzele, darsadMaliat, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja, lifeTable, _date, tarh);
//
//        this.Modat_Bime_Naameh = Modat_Bime_Naameh;
//
//        fRsList = new ArrayList<FRs>();
//        int yearCount = 0;
//        FRs previusFRsFirst = null;
//
//        String currentDate = DateUtil.getCurrentDate();
//        for (IRs iRs : iRsList) {
//            FRs fRs = new FRs();
//            fRs.setSen(iRs.getSen());
//            fRs.setSaal(iRs.getSaal());
//            fRs.setSarmaayeFotBehHarEllat(iRs.getSarmaayeFot());
//            fRs.setSarmaayeFotDarAsarHaadeseh(iRs.getSarmaayeHaadeseh());
//            fRs.setSarmaayePusheshEzaafiAmraazKhaas(iRs.getSarmaayeAmraaz());
//            fRs.setHaghBimePardaakhtiSaal(this.mohaasebeHaghBimePardaakhtiSaal(iRs));
//            fRs.setMajmuHaghBimePardaakhtiSaal(this.mohaasebeMajmuHaghBimePardaakhtiSaal(yearCount, fRs, previusFRsFirst));
//            fRs.setHaghBimePusheshHaayeEzaafi(iRs.getHaghBimePusheshHaayeEzaafi());
//            fRs.setMajmuHaghBimePusheshHaayeEzaafi(iRs.getHaghBimePusheshHaayeEzaafi());
//            fRs.setMaaliyaatBarArzeshAfzude(iRs.getMaaliyaatBarArzeshAfzude());
//            fRs.setMajmuKolMabaaleghPardaakhti(this.mohaasebeMajmuKolMabaaleghPardaakhti(yearCount, fRs, previusFRsFirst, NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE));
//            fRs.setArzeshBazKharidBaaSudTazmini15Darsad(iRs.getArzeshBazKharidBaaSudTazmini15Darsad());
//            fRs.setPishbiniArzeshBazKharidBaaSud20Darsad(iRs.getArzeshBazKharidBaaSud20Darsad());
//            fRs.setPishbiniArzeshBazKharidBaaSud22Darsad(iRs.getArzeshBazKharidBaaSud22Darsad());
//            fRs.setHaghBimePardakhtiBase(iRs.getHaghBimePardaakhtiBase());
//
//
//            fRs.setNahvePardakhteHagheBime(Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH ? "ماهانه" :
//                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH ? "سالانه" :
//                            Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE ? "سه ماهه" :
//                                    Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE ? "شش ماهه" : "یکجا");
//            fRs.setTarikheShoro(currentDate);
//            currentDate = DateUtil.addMonth(currentDate, 12);
//            fRs.setTarikheKhatame(currentDate);
//
//            previusFRsFirst = fRs;
//            fRsList.add(fRs);
//            yearCount++;
//        }
//
//        if (Mablagh_Seporde_Ebtedaye_Saal_Rial == 0) {
//            for (IRs iRs : iRsList) {
//                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
//                if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.067) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.0546) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.0362) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
//                    if ((iRs.getHaghBimePardaakhti()) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                }
//            }
//        } else if ((Mablagh_Seporde_Ebtedaye_Saal_Rial != 0)) {
//            int yearCounter = 0;
//            for (IRs iRs : iRsList) {
//                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getHaghBimeKhaalesFotYekja();
//                if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.067) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.0546) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
//                    if (((iRs.getHaghBimePardaakhti()) / 1.0362) < theValue) {
//                        throw new CustomValidationException();
//                    }
//                } else if (Nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
//                    if (yearCounter == 0) {
//                        if ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) < theValue) {
//                            throw new CustomValidationException();
//                        }
//                    } else {
//                        if ((iRs.getHaghBimePardaakhti()) < theValue) {
//                            throw new CustomValidationException();
//                        }
//                    }
//                }
//                yearCounter++;
//            }
//        }
//        return fRsList;
//    }

}
