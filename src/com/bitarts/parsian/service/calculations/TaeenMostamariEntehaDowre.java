package com.bitarts.parsian.service.calculations;


import com.bitarts.parsian.service.calculations.Assumptions.DiscountAssumptions;
import com.bitarts.parsian.service.calculations.Assumptions.MostamariGeneralAssumptions;
import com.bitarts.parsian.service.calculations.Constants.LifeTable;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.MostamariIRs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 8, 2011
 * Time: 11:28:51 PM
 */
public class TaeenMostamariEntehaDowre implements NSPConstants {

    private MostamariGeneralAssumptions mostamariGeneralAssumptions = null;
    private DiscountAssumptions discountAssumptions = null;
    private List<MostamariIRs> mostamariIRsList;

    public List<MostamariIRs> intermediateReports(Double andukhteEntehaModatBimename, Integer dowreTazminPardakht, String nahvePardakhtMostamari, Integer nerkhAfzayeshSalaneMostamari, String noeMostamariDarkhasti, Integer senMostamariBegir, double nerkhBahreFanni,
                                                  double[][] lifeTable) {
        mostamariGeneralAssumptions = new MostamariGeneralAssumptions(andukhteEntehaModatBimename, dowreTazminPardakht, nahvePardakhtMostamari, nerkhAfzayeshSalaneMostamari, noeMostamariDarkhasti, senMostamariBegir);

        ArrayList<MostamariIRs> mostamariIRListTemp = new ArrayList<MostamariIRs>();
        MostamariIRs mostamariPreviousIRs = null;
        int indexFor = 0;
        if (mostamariGeneralAssumptions.getNoeMostamariDarkhasti().equalsIgnoreCase("madamolomr")) {
            indexFor = 5;
        }
        for (int yearCounter = 0; yearCounter < mostamariGeneralAssumptions.getDowreTazminPardakht() + indexFor; yearCounter++) {
            MostamariIRs newMostamariIRs = new MostamariIRs();
            if (yearCounter < mostamariGeneralAssumptions.getDowreTazminPardakht()) {
                newMostamariIRs.setSen(this.mohaasebeSen(yearCounter, lifeTable));
            } else {
                newMostamariIRs.setSen(Short.parseShort(String.valueOf(yearCounter + mostamariGeneralAssumptions.getSenMostamariBegir())));
            }
            newMostamariIRs.setSaal(this.mohaasebeSaal(yearCounter));
            newMostamariIRs.setMablaghMostamari(this.mohaasebeMablaghMostamari(yearCounter, mostamariPreviousIRs, nerkhBahreFanni, lifeTable));
            mostamariIRListTemp.add(newMostamariIRs);
            mostamariPreviousIRs = newMostamariIRs;
        }
        mostamariIRsList = new ArrayList<MostamariIRs>();
        int yearCounter = 0;
        for (MostamariIRs mostamariIRs : mostamariIRListTemp) {
            mostamariIRsList.add(mostamariIRs);
            yearCounter++;
        }

        return mostamariIRsList;
    }

    private double mohaasebeMablaghMostamari(int yearCounter, MostamariIRs mostamariPreviousIRs, double nerkhBahreFanni, double[][] lifeTable) {
        double result = 0;
        if (mostamariGeneralAssumptions.getNahvePardakhtMostamari().equalsIgnoreCase("enteha")) {
            if (mostamariGeneralAssumptions.getNoeMostamariDarkhasti().equalsIgnoreCase("modatMoayan")) {
                if (nerkhBahreFanni == mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) {
                    if (yearCounter == 0) {
                        result = ((1 + nerkhBahreFanni) * mostamariGeneralAssumptions.getAndukhteEntehaModatBimename()) / mostamariGeneralAssumptions.getDowreTazminPardakht();
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }
                } else {
                    if (yearCounter == 0) {
                        double sourat = 0;
                        double makhraj = 0;
                        double nerkheTanzil = 0;
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        sourat = (nerkhBahreFanni - mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) * mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        makhraj = (1 - (Math.pow(nerkheTanzil, mostamariGeneralAssumptions.getDowreTazminPardakht())));
                        result = sourat / makhraj;
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }
                }
            } else {
                if (nerkhBahreFanni == mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) {
                    if (yearCounter == 0) {
                        double sourat = mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        double makhraj = 0;
                        double dixen = 0;
                        double dix = 0;
                        double nexen = 0;
                        double nerkheTanzil = 0;
                        int indexexen = mostamariGeneralAssumptions.getSenMostamariBegir() + mostamariGeneralAssumptions.getDowreTazminPardakht();
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        dixen = mohaasebeDix(nerkheTanzil, indexexen, lifeTable);
                        dix = mohaasebeDix(nerkheTanzil, mostamariGeneralAssumptions.getSenMostamariBegir(), lifeTable);

                        for (int i = 0; i < (lifeTable.length - indexexen); i++) {
                            nexen += mohaasebeDix(nerkheTanzil, indexexen + i, lifeTable);
                        }
                        //                    makhraj = mostamariGeneralAssumptions.getDowreTazminPardakht()+((dixen/dix)*(nexen/dixen));
                        makhraj = ((mohasebeNX(indexexen + 1, nerkhBahreFanni, lifeTable) / mohasebeDX(mostamariGeneralAssumptions.getSenMostamariBegir(), nerkhBahreFanni, lifeTable)) * (1 / (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()))) + (mostamariGeneralAssumptions.getDowreTazminPardakht() / (1 + nerkhBahreFanni));
                        result = sourat / makhraj;
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }

                } else {
                    if (yearCounter == 0) {
                        double sourat = mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        double makhraj = 0;
                        double dixen = 0;
                        double dix = 0;
                        double nexen = 0;
                        double nerkheTanzil = 0;
                        double nx = 0;
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        int indexexen = mostamariGeneralAssumptions.getSenMostamariBegir() + mostamariGeneralAssumptions.getDowreTazminPardakht();
                        dixen = mohaasebeDix(nerkheTanzil, indexexen, lifeTable);
                        dix = mohaasebeDix(nerkheTanzil, mostamariGeneralAssumptions.getSenMostamariBegir(), lifeTable);
                        for (int i = 0; i < (lifeTable.length - indexexen); i++) {
                            nexen += mohaasebeDix(nerkheTanzil, indexexen + i, lifeTable);
                        }

                        makhraj = (((1 - Math.pow(nerkheTanzil, mostamariGeneralAssumptions.getDowreTazminPardakht())) / (nerkhBahreFanni - mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()))
                                //                                *(1+NERKH_BAHRE_FANNI))+((dixen/dix)*(nexen/dixen));
                                + ((mohasebeNX(indexexen + 1, nerkhBahreFanni, lifeTable) / mohasebeDX(mostamariGeneralAssumptions.getSenMostamariBegir(), nerkhBahreFanni, lifeTable)) * (1 / (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()))));
                        result = sourat / makhraj;
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }

                }
            }
        } else {
            if (mostamariGeneralAssumptions.getNoeMostamariDarkhasti().equalsIgnoreCase("modatMoayan")) {
                if (nerkhBahreFanni == mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) {
                    if (yearCounter == 0) {
                        result = ((mostamariGeneralAssumptions.getAndukhteEntehaModatBimename())) / (mostamariGeneralAssumptions.getDowreTazminPardakht());
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }
                } else {
                    if (yearCounter == 0) {
                        double sourat = 0;
                        double makhraj = 0;
                        double nerkheTanzil = 0;
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        sourat = (nerkhBahreFanni - mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) * mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        makhraj = (1 - (Math.pow(nerkheTanzil, mostamariGeneralAssumptions.getDowreTazminPardakht())));
                        result = sourat / (makhraj * (1 + nerkhBahreFanni));
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }
                }
            } else {
                if (nerkhBahreFanni == mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) {
                    if (yearCounter == 0) {
                        double sourat = mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        double makhraj = 0;
                        double dixen = 0;
                        double dix = 0;
                        double nexen = 0;
                        double nerkheTanzil = 0;
                        int indexexen = mostamariGeneralAssumptions.getSenMostamariBegir() + mostamariGeneralAssumptions.getDowreTazminPardakht();
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        dixen = mohaasebeDix(nerkheTanzil, indexexen, lifeTable);
                        dix = mohaasebeDix(nerkheTanzil, mostamariGeneralAssumptions.getSenMostamariBegir(), lifeTable);

                        for (int i = 0; i < (lifeTable.length - indexexen); i++) {
                            nexen += mohaasebeDix(nerkheTanzil, indexexen + i, lifeTable);
                        }
                        //                    makhraj = mostamariGeneralAssumptions.getDowreTazminPardakht()+((dixen/dix)*(nexen/dixen));
                        makhraj = ((mohasebeNX(indexexen, nerkhBahreFanni, lifeTable) / mohasebeDX(mostamariGeneralAssumptions.getSenMostamariBegir(), nerkhBahreFanni, lifeTable))) + (mostamariGeneralAssumptions.getDowreTazminPardakht());
                        result = sourat / makhraj;
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }

                } else {
                    if (yearCounter == 0) {
                        double sourat = mostamariGeneralAssumptions.getAndukhteEntehaModatBimename();
                        double makhraj = 0;
                        double dixen = 0;
                        double dix = 0;
                        double nexen = 0;
                        double nerkheTanzil = 0;
                        double nx = 0;
                        nerkheTanzil = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
                        int indexexen = mostamariGeneralAssumptions.getSenMostamariBegir() + mostamariGeneralAssumptions.getDowreTazminPardakht();
                        dixen = mohaasebeDix(nerkheTanzil, indexexen, lifeTable);
                        dix = mohaasebeDix(nerkheTanzil, mostamariGeneralAssumptions.getSenMostamariBegir(), lifeTable);
                        for (int i = 0; i < (lifeTable.length - indexexen); i++) {
                            nexen += mohaasebeDix(nerkheTanzil, indexexen + i, lifeTable);
                        }

                        makhraj = ((((1 - Math.pow(nerkheTanzil, mostamariGeneralAssumptions.getDowreTazminPardakht())) / (nerkhBahreFanni - mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari())) * (1 + nerkhBahreFanni))
                                //                                *(1+NERKH_BAHRE_FANNI))+((dixen/dix)*(nexen/dixen));
                                + ((mohasebeNX(indexexen, nerkhBahreFanni, lifeTable) / mohasebeDX(mostamariGeneralAssumptions.getSenMostamariBegir(), nerkhBahreFanni, lifeTable))));
                        result = sourat / makhraj;
                    } else {
                        result = mostamariPreviousIRs.getMablaghMostamari() * (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari());
                    }

                }
            }
        }

        return Math.round(result);
    }

    private double mohasebeDX(int index, double nerkhBahreFanni, double[][] lifeTable) {
        double res = 0;
        double paaye = (1 + mostamariGeneralAssumptions.getNerkhAfzayeshSalaneMostamari()) / (1 + nerkhBahreFanni);
        double haasel = Math.pow(paaye, index);
        res = haasel * lifeTable[index][2];
        return res;
    }

    private double mohasebeNX(int index, double nerkhBahreFanni, double[][] lifeTable) {
        double res = 0;
        for (int i = index; i < lifeTable.length; i++) {
            res += mohasebeDX(i, nerkhBahreFanni, lifeTable);
        }
        return res;  //To change body of created methods use File | Settings | File Templates.
    }

    private double mohaasebeDix(double nerkheTanzil, int x, double[][] lifeTable) {
        return Math.pow(nerkheTanzil, x) * lifeTable[x][5];
    }

    private short mohaasebeSen(int yearCounter, double[][] lifeTable) {
        if (mostamariGeneralAssumptions.getSenMostamariBegir() + lifeTable[yearCounter][0] < mostamariGeneralAssumptions.getSenMostamariBegir() + mostamariGeneralAssumptions.getDowreTazminPardakht()) {
            return (short) (mostamariGeneralAssumptions.getSenMostamariBegir() + lifeTable[yearCounter][0]);
        } else {
            return (short) 0;
        }
    }

    private short mohaasebeSaal(int yearCounter) {
        return (short) (yearCounter + 1);
    }


}
