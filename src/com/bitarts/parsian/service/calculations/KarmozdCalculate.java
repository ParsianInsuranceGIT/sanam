package com.bitarts.parsian.service.calculations;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 5/20/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class KarmozdCalculate
{


    public static KarmozdGhest.Tarefe getTarefeBimename(Bimename bimename)
    {
        if(DateUtil.isGreaterThanOrEqual("1391/12/30", bimename.getTarikhSodour())) return KarmozdGhest.Tarefe.NAVADO_YEK;
        else if(DateUtil.isGreaterThanOrEqual(bimename.getTarikhSodour(),"1392/01/01") && DateUtil.isGreaterThanOrEqual("1392/02/31",bimename.getTarikhSodour())) return KarmozdGhest.Tarefe.YEK_YEK_NAVAD_DO;
        else if(DateUtil.isGreaterThanOrEqual(bimename.getTarikhSodour(),"1392/03/01") && DateUtil.isGreaterThanOrEqual("1392/03/31",bimename.getTarikhSodour())) return KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO;
        else return KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO;
    }

    public static long calKarmozdSal(long karmozd,int sal,KarmozdGhest.Tarefe tarefe,boolean isYekja)
    {
        long percentKarmozd=0;
        if(isYekja)
        {
            switch(sal)
            {
                case 0:
                    percentKarmozd = (long) (0.5 * karmozd);
                    break;
                case 1: case 2:
                    percentKarmozd = (long) (0.25 * karmozd);
                    break;
                default:
                    return percentKarmozd;
            }
        }
        else
        {
//            double persentPeymentFirstYear=0.3;
//            double persentPeymentOther=0.175;

            double persentPeymentFirstYear = 0.4;
            double persentPeymentOther = 0.15;

//            if(tarefe.equals(KarmozdGhest.Tarefe.NAVADO_YEK) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
//            {
//                persentPeymentFirstYear = 0.4;
//                persentPeymentOther = 0.15;
//            }

            long bimenameKarmozd=karmozd;
            switch (sal)
            {
                case 1: case 2: case 3: case 4:
                    percentKarmozd=(long)(bimenameKarmozd*persentPeymentOther);
                    break;
                case 0:
                    percentKarmozd=(long)(bimenameKarmozd*persentPeymentFirstYear);
                    break;
                default:
                    return percentKarmozd;
            }
        }
        return percentKarmozd;
    }

//    public static long calKarmozdForBimenameGheyreYekja(KarmozdGhest.Tarefe tarefe, Pishnehad pishnehad, IAsnadeSodorService asnadeSodorService)
//    {
////        if(pishnehad.getBimename().getKarmozdBimename() != null && pishnehad.getBimename().getKarmozdBimename() > 0) return pishnehad.getBimename().getKarmozdBimename();
//        long sarmaye= (long) (bimename.getFirstPishnehadWithElhaghie().getEstelam().getSarmaye_paye_fot_long() * 0.03);
//        long sarmaye= (long) (pishnehad.getEstelam().getSarmaye_paye_fot_long() * 0.03);
//        long haghBimeAval=0l;
//        long haghBimeEzafi=0l;
//        long maliat=0l;
//        long sepordeEbteda = 0l;
//        long haghBimeOrSarmaye=sarmaye;
//        if (tarefe.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
//        {
//            if (asnadeSodorService == null) {
//            for(GhestBandi gb : pishnehad.getBimename().getGhestBandiList())
//            {
//                if(gb.getSaleBimeiInt().equals(0))
//                {
//                        for(Ghest g : gb.getGhestList())
//                        {
//                            haghBimeAval += g.getCredebit().getAmount_long();
//                            if(g.getHaghBimePoosheshhayeEzafi()!=null && !g.getHaghBimePoosheshhayeEzafi().isEmpty())
//                                haghBimeEzafi += Long.parseLong(g.getHaghBimePoosheshhayeEzafi().replaceAll(",","").trim());
//                            if(g.getMaliat()!=null && !g.getMaliat().isEmpty())
//                                maliat += Long.parseLong(g.getMaliat().replaceAll(",","").trim());
//                        }
//                    }
////                    if(bimename.getFirstPishnehadWithElhaghie().getEstelam().getMablagh_seporde_ebtedaye_sal()!=null)
//                    if(pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal()!=null)
////                        sepordeEbteda=Long.parseLong(bimename.getFirstPishnehadWithElhaghie().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",","").trim());
//                        sepordeEbteda=Long.parseLong(pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",","").trim());
//                    haghBimeAval = (long) ((haghBimeAval - haghBimeEzafi - maliat + sepordeEbteda) * 0.75);
//                    if(haghBimeAval<sarmaye)
//                        haghBimeOrSarmaye=haghBimeAval;
//                    break;
//                }
//            } else {
//                long haghBimeBeduneMaliatVaPoosheshha = asnadeSodorService.getJameSadereForGhestbandiKasrMaliatEzafi(pishnehad.getBimename().getId());
//                if (pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal() != null)
//                    sepordeEbteda = Long.parseLong(pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").trim());
//                haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha + sepordeEbteda) * 0.75);
//                if (haghBimeAval < sarmaye)
//                    haghBimeOrSarmaye = haghBimeAval;
//            }
//        }
//
////        if(bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.FORUSHANDE))
////        {
////            haghBimeOrSarmaye=(long) (0.7 * haghBimeOrSarmaye);
////        }
////        else if
////        (
////            bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.KARGOZAR_HAGHIGHI) ||
////            bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.KARGOZAR_HOGHUGHI)
////        )
////        {
////
////            if(tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
////            {
////                haghBimeOrSarmaye=(long) (0.95 * haghBimeOrSarmaye);
////            }
////
////            else
////            {
////                haghBimeOrSarmaye=(long) (0.9 * haghBimeOrSarmaye);
////            }
////        }
////        else if
////        (
////            bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.NAMAYANDE_HAGHIGHI)||
////            bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.NAMAYANDE_HOGHUGHI)||
////            bimename.getPishnehad().getNamayande().getNamayandeType().equals(Namayande.NayamandeType.BAJE_NAMAYANDE_HOGHUGHI)
////        )
////        {
////            haghBimeOrSarmaye=(long) (0.95 * haghBimeOrSarmaye);
////        }
////        else
////        {
////            return 0;
////        }
////        pishnehad.getBimename().setKarmozdBimename(haghBimeOrSarmaye);
////        if(asnadeSodorService != null)
////            asnadeSodorService.updateBimename(pishnehad.getBimename());
//        return haghBimeOrSarmaye;
//    }

    public static long calRealKarmozdForGhest(Credebit cGhest, KarmozdGhest.Tarefe tarefe, IAsnadeSodorService asnadeSodorService)
    {
//        double persentPeymentFirstYear=0.3;
//        double persentPeymentOther=0.175;
        double persentPeymentFirstYear = 0.4;
        double persentPeymentOther = 0.15;

//        if(tarefe.equals(KarmozdGhest.Tarefe.NAVADO_YEK) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
//        {
//            persentPeymentFirstYear = 0.4;
//            persentPeymentOther = 0.15;
//        }

//        long bimenameKarmozd=calKarmozdForBimenameGheyreYekja(tarefe, cGhest.getPishnehad(), asnadeSodorService);
        long bimenameKarmozd = 0l;
//        if(cGhest.getPishnehad().getBimename().getKarmozdBimename() != null)
            bimenameKarmozd = cGhest.getPishnehad().getBimename().getKarmozdBimename();

        long percentKarmozd=0;
        switch (cGhest.getGhest().getGhestBandi().getSaleBimeiInt())
        {
            case 1: case 2: case 3: case 4:
                percentKarmozd=Math.round(bimenameKarmozd*persentPeymentOther);
                break;
            case 0:
                percentKarmozd=Math.round(bimenameKarmozd*persentPeymentFirstYear);
                break;
            default:
                return percentKarmozd;
        }
//        Integer aghsatCount=0;

//        if (cGhest.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("mah"))
//            aghsatCount=12;
//
//        else if (cGhest.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("3mah"))
//            aghsatCount=4;
//
//        else if (cGhest.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("6mah"))
//            aghsatCount=2;
//
//        else if (cGhest.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("sal"))
//            aghsatCount=1;
        if(!cGhest.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
        {}
        else
        {
            long kfg;
            if(tarefe.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO))
            {
                kfg= (long)(0.05  * cGhest.getAmount_long()) ;
            }
            else
            {
                kfg = (long)(0.02* cGhest.getAmount_long());
            }
            //----------------------------------------------------------------------------------------------------------
            return kfg;
            //----------------------------------------------------------------------------------------------------------
        }
//        long karmozdForGhest=(percentKarmozd)/aghsatCount;
        long karmozdForGhest = cGhest.getAmount_long() * percentKarmozd / cGhest.getGhest().getGhestBandi().getMajmooeAmount();
        return karmozdForGhest;
    }

//    public static long calRealKarmozdForGhest(Ghest ghest, KarmozdGhest.Tarefe tarefe, long sumGhestsAmount) {
////        double persentPeymentFirstYear=0.3;
////        double persentPeymentOther=0.175;
//
//        double persentPeymentFirstYear = 0.4;
//        double persentPeymentOther = 0.15;
//
////        if(tarefe.equals(KarmozdGhest.Tarefe.NAVADO_YEK) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
////        {
////            persentPeymentFirstYear = 0.4;
////            persentPeymentOther = 0.15;
////        }
//
//        long bimenameKarmozd = calKarmozdForBimenameGheyreYekja(tarefe, ghest.getGhestBandi().getBimename());
//        long percentKarmozd = 0;
//        switch (ghest.getGhestBandi().getSaleBimeiInt()) {
//            case 1:
//            case 2:
//            case 3:
//            case 4:
//                percentKarmozd = Math.round(bimenameKarmozd * persentPeymentOther);
//                break;
//            case 0:
//                percentKarmozd = Math.round(bimenameKarmozd * persentPeymentFirstYear);
//                break;
//            default:
//                return percentKarmozd;
//        }
//        Integer aghsatCount = 0;
////        if (ghest.getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("mah"))
////            aghsatCount=12;
////        else if (ghest.getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("3mah"))
////            aghsatCount=4;
////        else if (ghest.getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("6mah"))
////            aghsatCount=2;
////        else if (ghest.getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("sal"))
////            aghsatCount=1;
////        else
//
//        if (ghest.getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja")) {
//            long kfg;
//            if (tarefe.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO)) {
//                kfg = (long) (0.05 * ghest.getCredebit().getAmount_long());
//            } else {
//                kfg = (long) (0.02 * ghest.getCredebit().getAmount_long());
//            }
//            //----------------------------------------------------------------------------------------------------------
//            return kfg;
//            //----------------------------------------------------------------------------------------------------------
//        } else {
////            long sumGhestsAmount=Long.parseLong(ghest.getGhestBandi().getSumGhestsAmount().replaceAll(",", ""));
//            long karmozdForGhest = (percentKarmozd * ghest.getCredebit().getAmount_long()) / sumGhestsAmount;
//            return karmozdForGhest;
//        }
//    }

    public static long percentPaymentOfKarmozdToNamayande(String namayandeType, long karmozd, String tarefe)
    {
//        System.out.println(ghest.getId());
        if
        (
            namayandeType.equals(Namayande.NayamandeType.KARGOZAR_HAGHIGHI.toString()) ||
            namayandeType.equals(Namayande.NayamandeType.KARGOZAR_HOGHUGHI.toString())
        )
        {
            if (tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO.toString()))
                return (long) (karmozd * 0.95);
            else
                return (long) (karmozd * 0.90);
        }

        else if
        (
            namayandeType.equals(Namayande.NayamandeType.NAMAYANDE_HAGHIGHI.toString()) ||
            namayandeType.equals(Namayande.NayamandeType.NAMAYANDE_HOGHUGHI.toString()) ||
            namayandeType.equals(Namayande.NayamandeType.BAJE_NAMAYANDE_HOGHUGHI.toString())
        )
        {
            return (long) (karmozd * 0.95);
        }

        else if (namayandeType.equals(Namayande.NayamandeType.FORUSHANDE.toString()))
        {
            return (long) (karmozd * 0.7);
        }

        else
            return 0;
    }

    public static long percentPaymentOfKarmozdToNamayande(Namayande n, long karmozd, KarmozdGhest.Tarefe tarefe)
    {
//        System.out.println(ghest.getId());
        if
                (
                    n.getNamayandeType().equals(Namayande.NayamandeType.KARGOZAR_HAGHIGHI) ||
                    n.getNamayandeType().equals(Namayande.NayamandeType.KARGOZAR_HOGHUGHI)
                )
        {
            if (tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
                return (long) (karmozd * 0.95);
            else
                return (long) (karmozd * 0.90);
        }

        else if
                (
                    n.getNamayandeType().equals(Namayande.NayamandeType.NAMAYANDE_HAGHIGHI) ||
                    n.getNamayandeType().equals(Namayande.NayamandeType.NAMAYANDE_HOGHUGHI) ||
                    n.getNamayandeType().equals(Namayande.NayamandeType.BAJE_NAMAYANDE_HOGHUGHI)
                )
        {
            return (long) (karmozd * 0.95);
        }

        else if (n.getNamayandeType().equals(Namayande.NayamandeType.FORUSHANDE))
        {
            return (long) (karmozd * 0.7);
        }

        else
            return 0;
    }

    public static long percentPaymentOfKarmozdToNamayande(Namayande.NayamandeType namayandeType, long karmozd, KarmozdGhest.Tarefe tarefe)
    {
//        System.out.println(ghest.getId());
        if
        (
            namayandeType.equals(Namayande.NayamandeType.KARGOZAR_HAGHIGHI)||
            namayandeType.equals(Namayande.NayamandeType.KARGOZAR_HOGHUGHI)
        )
        {
            if(tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
                return (long) (karmozd * 0.95);
            else
                return (long) (karmozd * 0.90);
        }

        else if
        (
            namayandeType.equals(Namayande.NayamandeType.NAMAYANDE_HAGHIGHI)||
            namayandeType.equals(Namayande.NayamandeType.NAMAYANDE_HOGHUGHI)||
            namayandeType.equals(Namayande.NayamandeType.BAJE_NAMAYANDE_HOGHUGHI)
        )
        {
            return (long) (karmozd * 0.95);
        }

        else if(namayandeType.equals(Namayande.NayamandeType.FORUSHANDE))
        {
            return (long) (karmozd * 0.7);
        }

        else
            return 0;
    }

    public static long calKarmozdForKarmozdGhestYekjayeMovaghat(KarmozdGhest kg, KarmozdGhest.Tarefe tarefe, Integer bimeYear)
    {
        Credebit kgGhestCredebit = kg.getGhest().getCredebit();
        long karmozdForGhest = kg.getGhest().getKarmozdReal();
        long paymentKarmozd = 0l;
        Namayande.NayamandeType namayandeType = kgGhestCredebit.getPishnehad().getValid() ?
                                                kgGhestCredebit.getPishnehad().getNamayande().getNamayandeType()
                                              : kgGhestCredebit.getPishnehad().getBimename().getPishnehad().getNamayande().getNamayandeType();
        if (bimeYear.equals(0))
        {
            paymentKarmozd = percentPaymentOfKarmozdToNamayande(namayandeType, (long) (0.5 * karmozdForGhest), tarefe);
        }
        else//1 Or 2
        {
            paymentKarmozd = percentPaymentOfKarmozdToNamayande(namayandeType, (long) (0.25 * karmozdForGhest), tarefe);
        }
        return paymentKarmozd;
    }


    public static long calKarmozdForKarmozdGhest(KarmozdGhest kg, KarmozdGhest.Tarefe tarefe,Pishnehad pishnehad, Credebit kgGhestCredebit, Long kReal)
    {
        if(kgGhestCredebit == null) kgGhestCredebit = kg.getGhest().getCredebit();

        long karmozdForGhest = kg.getGhest().getKarmozdReal()!=null? kg.getGhest().getKarmozdReal() : calRealKarmozdForGhest(kgGhestCredebit,tarefe,null);
        if(kReal!=null) karmozdForGhest = kReal;

        long paymentKarmozd=0l;

//        if(kg.getGhest().getGhestBandi().getBimename().getFirstPishnehadWithElhaghie().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))

        Namayande.NayamandeType namayandeType = kgGhestCredebit.getPishnehad().getValid() ?
                kgGhestCredebit.getPishnehad().getNamayande().getNamayandeType()
                : kgGhestCredebit.getPishnehad().getBimename().getPishnehad().getNamayande().getNamayandeType();
        if(pishnehad.getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
        {
            if(kg.getGhest().getGhestBandi().getBimename().getCurrentSaleBimei().equals(0))
            {
                paymentKarmozd = percentPaymentOfKarmozdToNamayande(namayandeType,(long) (0.5 * karmozdForGhest), tarefe);
            }
            else//1 Or 2
            {
                paymentKarmozd = percentPaymentOfKarmozdToNamayande(namayandeType, (long) (0.25 * karmozdForGhest), tarefe);
            }
        }
        else
        {
            paymentKarmozd = (Long.parseLong(kg.getKhateSanad().getAmount().replaceAll(",", "").trim()) * percentPaymentOfKarmozdToNamayande(namayandeType,karmozdForGhest,tarefe)) / kgGhestCredebit.getAmount_long();
        }

        return paymentKarmozd;
    }

    public static Long calKarmozdVosuliOrPoosheshForGYekja(long krmdVsliOrPshForGst , long amountKhateSanad , long amountBedehi , KarmozdGhest.Type type)
    {
        if(type.equals(KarmozdGhest.Type.POOSHESH_EZAFI) || type.equals(KarmozdGhest.Type.VOSULI))
        {
            return (krmdVsliOrPshForGst * amountKhateSanad)/amountBedehi;
        }
        return null;
    }

    public static final double KRMZD_BRGSHTI_SAL_1 = 0.1;
    public static final double KRMZD_BRGSHTI_SAL_2 = 0.075;
    public static final double KRMZD_BRGSHTI_SAL_3 = 0.025;
    public static final double KRMZD_BRGSHTI_SAL_4 = 0.025;

    public static long calKarmozdBargashtiEbtalOrBazkharid(KarmozdGhest kg)
    {
        Bimename bimename = kg.getGhest().getGhestBandi().getBimename();

        if(bimename.getState().getId() != 540 && bimename.getState().getId() != 550) return 0;

        if(bimename.getBimeYearWhenEbtlOrBzkhrd() == 0)
        {
            return (long)(kg.getKarmozdAmount() * KRMZD_BRGSHTI_SAL_1);
        }

        else if(bimename.getBimeYearWhenEbtlOrBzkhrd() == 1)
        {
            return (long)(kg.getKarmozdAmount() * KRMZD_BRGSHTI_SAL_2);
        }

        else if(bimename.getBimeYearWhenEbtlOrBzkhrd() == 2)
        {
            return (long)(kg.getKarmozdAmount() * KRMZD_BRGSHTI_SAL_3);
        }

        else if(bimename.getBimeYearWhenEbtlOrBzkhrd() == 3)
        {
            return (long)(kg.getKarmozdAmount() * KRMZD_BRGSHTI_SAL_4);
        }

        else
        {
            return 0;
        }
    }

    public static long calKarmozdTgr(KarmozdGhest kg)
    {
        Bimename bimename = kg.getGhest().getGhestBandi().getBimename();
        KarmozdGhest.Tarefe tarefe = kg.getTarefe();
        if(tarefe==null)tarefe=getTarefeBimename(bimename);
        Credebit cghest = kg.getGhest().getCredebit();
        Long karmozdReal = calRealKarmozdForGhest(cghest,tarefe,null);
        long newKrmzdCrtd=calKarmozdForKarmozdGhest(kg,tarefe,cghest.getPishnehad(),cghest,karmozdReal);
        return newKrmzdCrtd - (kg.getKarmozdAmount() + (kg.getKarmozdTadili()==null?0l:kg.getKarmozdTadili().getKarmozdAmount()));
    }

    private static final double NERKHE_TAKLIFI=0.03;
    private static final double KRMZD_TSHVIGHI_VSLI_PERCENT=0.01;


    public static double getNerkheTaklifi()
    {
        return NERKHE_TAKLIFI;
    }

    public static double getNerkheAfzode(String date, Tarh tarh)
    {
        return AsnadeSodorService.getDarsadMaliat(date, tarh);//0.06;
    }

//    public static long calMaliatTaklifi(long karmozd)
//    {
//        return (long)(karmozd - (karmozd * NERKHE_TAKLIFI));
//    }
//
//    public static long calMaliatAfzode(long karmozd)
//    {
//        return (long) (karmozd - (karmozd * NERKHE_AFZODE));
//    }
    public static final long calMaliat(long karmozd,String dateKarmozd)
    {
        double maliat = karmozd * (getNerkheAfzode(dateKarmozd,null) - getNerkheTaklifi());
        return (long) (karmozd + maliat);
    }

    public static long calKarmozdSenior(long karmozd)
    {
        return (long) ((karmozd*0.15) / 0.7);
    }

    public static long calKarmozdTashvighiVosuli(long khateSanadAmount)
    {
        return (long) (khateSanadAmount * KRMZD_TSHVIGHI_VSLI_PERCENT);
    }

}
