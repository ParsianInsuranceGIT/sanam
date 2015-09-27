package com.bitarts.parsian.service;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.util.OmrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Sep 7, 2011
 * Time: 6:29:03 PM
 */
public class MohasebateFaniVam {
    private Integer k;
    private double r;
    private double j;
    private double A;//mab
    private double R;
    private double v;
    private static final double NERKHE_BAHRE_SALANE_BE_DARSAD = 15;
    private static final double NERKHE_BAHRE_MOASER_SALANE = 0.15;

    public static Object[] calcJamAghsatMoavaghBimename(IAsnadeSodorService asnadeSodorService) {
        Object[] objects = new Object[2];
        objects[1] = false;
        Long jamAghsatMoavaghBimename = 0L;
//        for (Credebit c : asnadeSodorService.findAllBedehisLessThanSarresidDate(DateUtil.getCurrentDate())) {
//            if (!c.getRemainingAmount().equals("0")) {
//                objects[1] = true;
//                jamAghsatMoavaghBimename += Long.parseLong(c.getRemainingAmount().replace(",", "").trim());
//            }
//        }
        objects[0] = jamAghsatMoavaghBimename;
        return objects;
    }

    public static enum BazPardakhtType {
        MAHANE, SE_MAHE
    }

    private BazPardakhtType bazPardakhtType;
    private int modatebazPardakht;
    private double mablagheVam;


    public double calcMohasebeyeGhesteVam() {
        k = Constant.NERKHE_BAHRE_SALANEH_BE_DARSAD;
        r = (double) k / 100.0;
        if (bazPardakhtType.equals(BazPardakhtType.MAHANE)) {
            j = Math.pow((1.0 + r), 1.0 / 12.0) - 1.0;
        } else if (bazPardakhtType.equals(BazPardakhtType.SE_MAHE)) {
            j = Math.pow((1.0 + r), 1.0 / 4.0) - 1.0;
        }
        v = 1.0 / (1.0 + j);
        double temp = Math.pow(1 + j, (double) modatebazPardakht);
        R = (mablagheVam * j * temp) / (temp - 1.0);
        return R;
    }

    public double calcMablagheBahreyePardakhti(int t) {
        return R * (1.0 - Math.pow(v, modatebazPardakht - (double) t + 1));
    }

    public double calcMablaghePardakhtiAzAsleVam(int t) {
        return R * Math.pow(v, modatebazPardakht - (double) t + 1);
    }

    public double calcMaliatbarArzesheAfzoode() {
        double accom = 0.0;
        for (int i = 0; i < modatebazPardakht; i++) {
            accom += calcMablagheBahreyePardakhti(i + 1);
        }
        double darsadMaliat = AsnadeSodorService.getDarsadMaliat(DateUtil.getCurrentDate(), null);
        return OmrUtil.rondPardakhtiDahRial(((Double) (darsadMaliat * accom)).intValue());
    }

    public static double calcJameAghsatePardakhtNashode(DarkhastBazkharid darkhastBazkharid) {
        double result = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            List<Ghest> allTheGhests = darkhastBazkharid.getGhestBandi().getGhestList();
            List<Ghest> allTheVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhestsGheireMashmul = new ArrayList<Ghest>();
            for (Ghest ghest : allTheGhests) {
                if (ghest.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM)) {
                    allTheVaamGhests.add(ghest);
                }
            }
            for (Ghest vaamGhest : allTheVaamGhests) {
                //                if (vaamGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
                if (vaamGhest.getCredebit().getRemainingAmount_long() > 0)
                {
                    napardakhtiVaamGhests.add(vaamGhest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhests) {
                if (!DateUtil.isGreaterThan(DateUtil.getCurrentDate(), ghest.getSarresidDate())) {
                    napardakhtiVaamGhestsGheireMashmul.add(ghest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhestsGheireMashmul) {
                result += Double.valueOf(ghest.getCredebit().getAmount().replace(",", ""));
            }
        }
        return result;
    }

    public static double calcJaraemeTavigh(DarkhastBazkharid darkhastBazkharid) {
        double result = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            List<Ghest> allTheGhests = darkhastBazkharid.getGhestBandi().getGhestList();
            List<Ghest> allTheVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhestsMashmul = new ArrayList<Ghest>();
            for (Ghest ghest : allTheGhests) {
                if (ghest.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM)) {
                    allTheVaamGhests.add(ghest);
                }
            }
            for (Ghest vaamGhest : allTheVaamGhests) {
                //                if (vaamGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
                if (vaamGhest.getCredebit().getRemainingAmount_long() > 0)
                {
                    napardakhtiVaamGhests.add(vaamGhest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhests) {
                if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), ghest.getSarresidDate())) {
                    napardakhtiVaamGhestsMashmul.add(ghest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhestsMashmul) {
                final int di = Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), ghest.getSarresidDate()));
                final double r = Double.valueOf(ghest.getCredebit().getAmount().replace(",", ""));
                result += r * (Math.pow((1 + AsnadeSodorService.getNerkhBahreFanni(darkhastBazkharid.getBimename().getTarikhSodour(), new Constants(), darkhastBazkharid.getBimename().getPishnehad().getTarh())), (di / 365.0))) - r;
            }
        }
        return result;
    }

    public static double calcJaraemeDirkard(DarkhastBazkharid darkhastBazkharid, IAsnadeSodorService asnadeSodorService) {
        double sumFi = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            List<Ghest> allTheGhests = darkhastBazkharid.getGhestBandi().getGhestList();
            List<Ghest> allTheVaamGhests = new ArrayList<Ghest>();
            List<Ghest> pardakhtiVaamGhests = new ArrayList<Ghest>();
            List<Ghest> pardakhtiVaamGhestsMoavaqe = new ArrayList<Ghest>();

            for (Ghest ghest : allTheGhests) {
                if (ghest.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM)) {
                    allTheVaamGhests.add(ghest);
                }
            }
            for (Ghest vaamGhest : allTheVaamGhests) {
                if (vaamGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_KHORDE)) {
                    pardakhtiVaamGhests.add(vaamGhest);
                }
            }
            for (Ghest pardakhtiVaamGhest : pardakhtiVaamGhests) {
                final String pardakhtDate = asnadeSodorService.findEtebar(pardakhtiVaamGhest.getCredebit()).get(0).getCreatedDate();
                if (DateUtil.isGreaterThan(pardakhtDate, pardakhtiVaamGhest.getSarresidDate())) {
                    pardakhtiVaamGhestsMoavaqe.add(pardakhtiVaamGhest);
                }
            }

            for (Ghest ghest : pardakhtiVaamGhestsMoavaqe) {
                final String pardakhtDate = asnadeSodorService.findEtebar(ghest.getCredebit()).get(0).getCreatedDate();
                final int di = Math.abs(DateUtil.getTimeDifferenceByDayCount(pardakhtDate, ghest.getSarresidDate()));
                final int ti = Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), ghest.getCredebit().getCreatedDate()));
                final double r = Double.valueOf(ghest.getCredebit().getAmount().replace(",", ""));
                final double fi = ((r * Math.pow(1 + AsnadeSodorService.getNerkhBahreFanni(darkhastBazkharid.getBimename().getTarikhSodour(), new Constants(), darkhastBazkharid.getBimename().getPishnehad().getTarh()), (di / 365.0))) - r) * (Math.pow(1 + AsnadeSodorService.getNerkhBahreFanni(darkhastBazkharid.getBimename().getTarikhSodour(), new Constants(), darkhastBazkharid.getBimename().getPishnehad().getTarh()), ti / 365.0));
                sumFi += fi;
            }
        }
        return sumFi;
    }

    public static double calcJameKolAghsatMoavagh(DarkhastBazkharid darkhastBazkharid) {
        double result = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            List<Ghest> allTheGhests = darkhastBazkharid.getGhestBandi().getGhestList();
            List<Ghest> allTheVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhestsMashmul = new ArrayList<Ghest>();
            for (Ghest ghest : allTheGhests) {
                if (ghest.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM)) {
                    allTheVaamGhests.add(ghest);
                }
            }
            for (Ghest vaamGhest : allTheVaamGhests) {
//                if (vaamGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
                if (vaamGhest.getCredebit().getRemainingAmount_long()>0) {
                    napardakhtiVaamGhests.add(vaamGhest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhests) {
                if (DateUtil.isGreaterThan(DateUtil.getCurrentDate(), ghest.getSarresidDate())) {
                    napardakhtiVaamGhestsMashmul.add(ghest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhestsMashmul) {
                result += Double.valueOf(ghest.getCredebit().getAmount().replace(",", ""));
            }
        }
        return result;
    }

    public static double calcJameAslAghsatAti(DarkhastBazkharid darkhastBazkharid) {
        double result = 0;
        if (darkhastBazkharid.getGhestBandi() != null) {
            List<Ghest> allTheGhests = darkhastBazkharid.getGhestBandi().getGhestList();
            List<Ghest> allTheVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhests = new ArrayList<Ghest>();
            List<Ghest> napardakhtiVaamGhestsGheireMashmul = new ArrayList<Ghest>();
            for (Ghest ghest : allTheGhests) {
                if (ghest.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM)) {
                    allTheVaamGhests.add(ghest);
                }
            }
            for (Ghest vaamGhest : allTheVaamGhests) {
                //                if (vaamGhest.getCredebit().getStatus().equals(Credebit.Status.SANAD_NA_KHORDE)) {
                if (vaamGhest.getCredebit().getRemainingAmount_long() > 0)
                {
                    napardakhtiVaamGhests.add(vaamGhest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhests) {
                if (!DateUtil.isGreaterThan(DateUtil.getCurrentDate(), ghest.getSarresidDate())) {
                    napardakhtiVaamGhestsGheireMashmul.add(ghest);
                }
            }
            for (Ghest ghest : napardakhtiVaamGhestsGheireMashmul) {
                result += Double.valueOf(ghest.getHazineVosoul().replace(",", ""));
            }
        }
        return result;
    }

    public static double calcJameAghsateMoavvagh() {
        // todo feilan velesh
        return 0.0;
    }

    // ---------------------------------------------------------------------------------------------------------------------
    public BazPardakhtType getBazPardakhtType() {
        return bazPardakhtType;
    }

    public void setBazPardakhtType(BazPardakhtType bazPardakhtType) {
        this.bazPardakhtType = bazPardakhtType;
    }

    public Integer getModatebazPardakht() {
        return modatebazPardakht;
    }

    public void setModatebazPardakht(Integer modatebazPardakht) {
        this.modatebazPardakht = modatebazPardakht;
    }

    public double getMablagheVam() {
        return mablagheVam;
    }

    public void setMablagheVam(double mablagheVam) {
        this.mablagheVam = mablagheVam;
    }
}
