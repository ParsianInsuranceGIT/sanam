package com.bitarts.parsian.service.calculations;

import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.EstelamBimeShode;
import com.bitarts.parsian.model.estelam.EstelamKhanevade;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.FRsMultiple;
import com.bitarts.parsian.service.calculations.Reports.IRsMultiple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 4/15/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BimeNaamehVaSamayehGozariMultiple implements NSPConstants {
    private List<IRsMultiple> iRsMultipleList;
    private List<FRsMultiple> fRsMultipleList;


    public class CustomValidationException extends Exception {
    }

    public List<FRsMultiple> finalReports(EstelamKhanevade estelamKhanevade,
                                  NAHVE_PARDAAKHT_HAGH_BIME nahve_Pardaakht_Hagh_Bime,
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
                                  NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahve_daryaaft_andukhteh_entehaaye_modat_bime_naameh,
                                  NAHVE_DARYAAFT_MOSTAMERI nahve_daryaaft_mostameri,
                                  Constants.PayeyeMohasebeHazineEdari payeyeMohasebat,
                                  boolean[] isRegistered,
                                  boolean[] pooshesh_naghs_ozv_b,
                                  boolean[] pooshesh_fot_dar_asar_zelzele_b,
                                  boolean[] pooshesh_moafiat_b,
                                  boolean moafiatDarSurateFoteMoghdam)
            throws CustomValidationException {
        TaeenAndukhteBarMabnaayeHaghBimePardaakhtiMultiple taeenAndukhteBarMabnaayeHaghBimePardaakhti = new TaeenAndukhteBarMabnaayeHaghBimePardaakhtiMultiple();

        short modateBimeName = Short.parseShort(estelamKhanevade.getModateBimeName());
        double nerkheSalaneyeAfzayeshHagheBime = Double.parseDouble(estelamKhanevade.getNerkheAfzayesheHagheBime());
        long hagheBimePardakhti = Long.parseLong(estelamKhanevade.getMablagheHagheBimeMonazam().replace(",",""));
        long sepordeEbtedayeSale = Long.parseLong(estelamKhanevade.getMablagheHagheBimeAvalie().replace(",",""));
        double nerkheAfzayesheSalaneyeMostamari = Double.parseDouble(estelamKhanevade.getNerkhAfzayesheSalaneMostamari());
        short modatZamaneDaryafteMostamari = Short.parseShort(estelamKhanevade.getModatZamaneDaryafteMostamari());

        short[] seneBimeShode = new short[estelamKhanevade.getEstelamBimeShodes().length];
        double[] darsadEzafeNerkhPezeshki = new double[estelamKhanevade.getEstelamBimeShodes().length];
        long[] sarmayePayeFotRial = new long[estelamKhanevade.getEstelamBimeShodes().length];
        long[] sarmayePayeFotDarAsareHadese = new long[estelamKhanevade.getEstelamBimeShodes().length];
        long[] sarmayePusheshAmrazeKhas = new long[estelamKhanevade.getEstelamBimeShodes().length];
        long[] sarmayePusheshNaghseOzv = new long[estelamKhanevade.getEstelamBimeShodes().length];
        double[] nerkheSalaneAfzayesheSarmayeFot = new double[estelamKhanevade.getEstelamBimeShodes().length];
        byte[] tabagheKhatar = new byte[estelamKhanevade.getEstelamBimeShodes().length];
        byte[] tabagheKhatareNaghseOzv = new byte[estelamKhanevade.getEstelamBimeShodes().length];

        for (int personIndex = 0; personIndex < estelamKhanevade.getEstelamBimeShodes().length; ++personIndex) {
            if (isRegistered[personIndex]) {
                EstelamBimeShode estelamBimeShode = estelamKhanevade.getEstelamBimeShodes()[personIndex];

                seneBimeShode[personIndex] = Short.parseShort(estelamBimeShode.getSen());
                darsadEzafeNerkhPezeshki[personIndex] = Double.parseDouble(estelamBimeShode.getDarsadeEzafeNerkhePezeshki());
                sarmayePayeFotRial[personIndex] = Long.parseLong(estelamBimeShode.getSarmayePayeFot().replace(",",""));
                sarmayePayeFotDarAsareHadese[personIndex] = Long.parseLong(estelamBimeShode.getSarmayeFotBarAsareHadese().replace(",",""));
                sarmayePusheshAmrazeKhas[personIndex] = Long.parseLong(estelamBimeShode.getSarmayeAmrazeKhas().replace(",",""));
                sarmayePusheshNaghseOzv[personIndex] = Long.parseLong(estelamBimeShode.getSarmayeNaghseOzv().replace(",",""));
                nerkheSalaneAfzayesheSarmayeFot[personIndex] = Double.parseDouble(estelamBimeShode.getNerkhAfzayeshSarmayeFot());
                tabagheKhatar[personIndex] = Byte.parseByte(estelamBimeShode.getTabagheKhatar());
                tabagheKhatareNaghseOzv[personIndex] = Byte.parseByte(estelamBimeShode.getTabagheKhatareNaghseOzv());
            }
        }

        this.iRsMultipleList = taeenAndukhteBarMabnaayeHaghBimePardaakhti.intermediateReports(
                isRegistered,
                seneBimeShode,
                darsadEzafeNerkhPezeshki,
                modateBimeName,
                sarmayePayeFotRial,
                sarmayePayeFotDarAsareHadese,
                sarmayePusheshAmrazeKhas,
                pooshesh_moafiat_b,
                pooshesh_naghs_ozv_b,
                sarmayePusheshNaghseOzv,
                nerkheSalaneyeAfzayeshHagheBime,
                nerkheSalaneAfzayesheSarmayeFot,
                nahve_Pardaakht_Hagh_Bime,
                hagheBimePardakhti,
                sepordeEbtedayeSale,
                nahve_daryaaft_andukhteh_entehaaye_modat_bime_naameh,
                nerkheAfzayesheSalaneyeMostamari,
                nahve_daryaaft_mostameri,
                modatZamaneDaryafteMostamari,
                tabagheKhatar,
                tabagheKhatareNaghseOzv,
                pooshesh_fot_dar_asar_zelzele_b,
                moafiatDarSurateFoteMoghdam,
                Bimeh_Gari,
                Edari,
                Kaarmozd_Az_Mahal_Hagh_Bimeh,
                Kaarmozd_Az_Mahal_Kaarmozd,
                Hazineh_Vosul,
                nerkhBahreFanni,
                hadeAksarSarFot,
                hadeAksarSarmayeFotHadese,
                hadeAksarAmraz,
                nerkhPusheshZelzele,
                darsadMaliat,
                karmozdGyekja,
                karmozdyekja,
                bimegariGyekja,
                bimegariyekja,
                edariGyekja,
                vosulGyekja,
                lifeTable,
                _date,
                tarh,
                nahve_kasr_hazineha_az_hagh_bime,
                payeyeMohasebat
        );

        this.fRsMultipleList = new ArrayList<FRsMultiple>();
        FRsMultiple previousFRsMultiple = null;

        for(IRsMultiple iRsMultiple:iRsMultipleList){
            FRsMultiple fRsMultiple = new FRsMultiple();
            fRsMultiple.setSaal(iRsMultiple.getSaal());
            fRsMultiple.setSen(iRsMultiple.getSen());
            fRsMultiple.setSarmayeFot(iRsMultiple.getSarmaayeFot());
            fRsMultiple.setSarmayeHadese(iRsMultiple.getSarmaayeHaadeseh());
            fRsMultiple.setSarmayeNaghseOzv(iRsMultiple.getSarmaayeNaghsOzv());
            fRsMultiple.setSarmayeAmraz(iRsMultiple.getSarmaayeAmraaz());
            fRsMultiple.setHaghBimePusheshHayeEzafe(iRsMultiple.getSumHaghBimePusheshHaayeEzaafi());
            fRsMultiple.setMaliyatBarArzeshAfzude(iRsMultiple.getMaaliyaatBarArzeshAfzude());
            fRsMultiple.setHaghBime(iRsMultiple.getHaghBimePardaakhti()+iRsMultiple.getMablaghSepordeEbtedaayeSaal());
            fRsMultiple.setMajmuHaghBimePardakhti(mohaasebeMajmuHaghBimePardakhti(iRsMultiple));
            fRsMultiple.setMajmuKoleMabaleghePardakhti(mohaasebeMajmuKoleMabaleghePardakhti(fRsMultiple.getMajmuHaghBimePardakhti(),previousFRsMultiple));
            fRsMultiple.setSarmayeMohasebatiMoafiatFot(iRsMultiple.getSarmayeMoafiatDarSurateFoteMoghadameBimeShodeAval());
            fRsMultiple.setArzeshBazkharidBaSud15Darsad(iRsMultiple.getArzeshBazKharidBaaSudTazmini15Darsad());
            fRsMultiple.setArzeshBazkharidBaSud20Darsad(iRsMultiple.getArzeshBazKharidBaaSud20Darsad());
            fRsMultiple.setArzeshBazkharidBaSud25Darsad(iRsMultiple.getArzeshBazKharidBaaSud22Darsad());
            fRsMultiple.setPusheshMoafiat(mohaasebePoosheshMoafiat(pooshesh_moafiat_b,iRsMultiple.getSen()));
            fRsMultipleList.add(fRsMultiple);
            previousFRsMultiple= fRsMultiple;
        }


        if (sepordeEbtedayeSale == 0) {
            for (IRsMultiple iRs : iRsMultipleList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getSumHaghBimeKhaalesFotYekja();
//                if (iRs.getSaal() > 0) {
//                    theValue += +iRs.getHaghBimePusheshHaayeEzaafi();
//                }
                double haghBimePardakhti = 0.0;
                if (nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE)
                    haghBimePardakhti = iRs.getHaghBimePardaakhti() - iRs.getMaaliyaatBarArzeshAfzude() - iRs.getSumHaghBimePusheshHaayeEzaafi();
                else if(nahve_kasr_hazineha_az_hagh_bime == NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE || nahve_kasr_hazineha_az_hagh_bime == null)
                    haghBimePardakhti = iRs.getHaghBimePardaakhti();
                if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                    if (((haghBimePardakhti) / 1.067) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                    if (((haghBimePardakhti) / 1.0546) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                    if (((haghBimePardakhti) / 1.0362) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                    if ((haghBimePardakhti) < theValue) {
                        throw new CustomValidationException();
                    }
                }
            }
        } else if ((sepordeEbtedayeSale != 0) && (hagheBimePardakhti == 0)) {
            for (IRsMultiple iRs : iRsMultipleList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineEdari() + iRs.getSumHaghBimeKhaalesFotYekja();
                if ((sepordeEbtedayeSale / modateBimeName) < theValue) {
                    throw new CustomValidationException();
                }
            }
        } else if ((sepordeEbtedayeSale != 0) && (hagheBimePardakhti != 0)) {
            int yearCounter = 0;
            for (IRsMultiple iRs : iRsMultipleList) {
                double theValue = iRs.getKaarmozd() + iRs.getHazineBimeGari() + iRs.getHazineVosul() + iRs.getHazineEdari() + iRs.getSumHaghBimeKhaalesFotYekja();
                if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.067) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0546) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE)) {
                    if (((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) / 1.0362) < theValue) {
                        throw new CustomValidationException();
                    }
                } else if (nahve_Pardaakht_Hagh_Bime.equals(NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH)) {
                    if (yearCounter == 0) {
                        if ((iRs.getHaghBimePardaakhti() + iRs.getMablaghSepordeEbtedaayeSaal()) < theValue) {
                            throw new CustomValidationException();
                        }
                    } else {
                        if ((iRs.getHaghBimePardaakhti()) < theValue) {
                            throw new CustomValidationException();
                        }
                    }
                }
                yearCounter++;
            }
        }
        for (FRsMultiple frs : fRsMultipleList) {
            if (frs.getArzeshBazkharidBaSud15Darsad() < 0) throw new CustomValidationException();
            if (frs.getArzeshBazkharidBaSud20Darsad() < 0) throw new CustomValidationException();
            if (frs.getArzeshBazkharidBaSud25Darsad() < 0) throw new CustomValidationException();
        }
        return fRsMultipleList;
    }

    private double mohaasebeMajmuHaghBimePardakhti(IRsMultiple iRsMultiple){
        return iRsMultiple.getHaghBimePardaakhti() + iRsMultiple.getMaaliyaatBarArzeshAfzude() + iRsMultiple.getSumHaghBimePusheshHaayeEzaafi() +iRsMultiple.getMablaghSepordeEbtedaayeSaal();
    }

    private double mohaasebeMajmuKoleMabaleghePardakhti(double majmuHaghBimePardakhti,FRsMultiple previousFRsMultiple){
        return majmuHaghBimePardakhti + (previousFRsMultiple != null ? previousFRsMultiple.getMajmuKoleMabaleghePardakhti() : 0);
    }

    private String[] mohaasebePoosheshMoafiat(boolean[] pushesh_Moaafiyat, Short[] sen) {
        String[] res= new String[pushesh_Moaafiyat.length];
        for(int i=0;i<sen.length;++i){
            if(sen[i] == null){
                res[i] = "";
                continue;
            }
            if (!pushesh_Moaafiyat[i]) {
                res[i] = "ندارد";
            } else {
                if (sen[i] >= 60) {
                    res[i]= "ندارد";
                } else {
                    res[i] = "دارد";
                }
            }
        }
        return res;
    }
}
