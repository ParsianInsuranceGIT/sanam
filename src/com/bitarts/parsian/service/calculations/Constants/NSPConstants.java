package com.bitarts.parsian.service.calculations.Constants;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 7, 2011
 * Time: 8:45:04 PM
 */
public interface NSPConstants {
    public final double


            //Hazine haa (gheir e yekjaa)

            KARMOZD_GHEIRE_YEKJA                =   0.030,
            BIME_GARI_GHEIRE_YEKJA              =   0.010,
            EDARI_GHEIRE_YEKJA                  =   0.050,
            VOSUL_GHEIRE_YEKJA                  =   0.020,

            //Hazine haa (yekjaa)

            KARMOZD_YEKJA                       =   0.020,
            BIME_GARI_YEKJA                     =   0.020,

            //Nerkhe pushesh haaye ezaafi

            MOAAFIYAT_AZ_PARDAKHT_HAGH_BIME     =   0.030,

            //Maliyat

            DARSAD_MALIYAT                      =   0.05,


            //Tabaghe khatar {

            //Nerkhe pushesh e haadese

            NERKH_PUSHESH_HADESE_1              =   0.0008,
            NERKH_PUSHESH_HADESE_2              =   0.001,
            NERKH_PUSHESH_HADESE_3              =   0.0015,
            NERKH_PUSHESH_HADESE_4              =   0.0019,
            NERKH_PUSHESH_HADESE_5              =   0.0023,

            //Nerkhe pushesh e gharaamat

            NERKH_PUSHESH_GHARAAMAT_1           =   0.0004,
            NERKH_PUSHESH_GHARAAMAT_2           =   0.0005,
            NERKH_PUSHESH_GHARAAMAT_3           =   0.0007,
            NERKH_PUSHESH_GHARAAMAT_4           =   0.0009,
            NERKH_PUSHESH_GHARAAMAT_5           =   0.0012,

            //Nerkhe pushesh e khatar zelzele

            NERKH_PUSHESH_KHATAR_ZELZELE        =    0.0002;

            enum  NAHVE_PARDAAKHT_HAGH_BIME{
                MAAHAANEH,
                SE_MAAHE,
                SHISH_MAAHE,
                SAALAANEH,
                YEKJA
            }

            enum NAHVE_DARYAAFT_MOSTAMERI{
                MAAHAANEH,
                SAALAANEH
            };

            enum NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH{
                YEKJA,
                MOSTAMERI_MODAT_MOAYAN,
                MOSTAMERI_MADAMOL_OMR
            }

            enum NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI{
                MAZAD_BAR_HAGH_BIME_PAYE,
                AZ_HAGH_BIME_PAYE
            }



    public final long

            //Mafruzaat

            HADE_AKSAR_SARMAYE_FOT              =   1000000000,
            HADE_AKSAR_SARMAYE_NAGHS_OZV        =   500000000,
            HADE_AKSAR_SARMAYE_HADESE           =   1000000000,
            HADE_AKSAR_SARMAYE_AMRAZ            =   300000000,
            SAGHF_SEN_MOAFIAT                   =   60;

    public final short

            //Mafruzaat

            HADE_AKSAR_SENNE_AMRAZ              =   60,
            HADE_AGHAL_SENNE_AMRAZ              =   15;


}
