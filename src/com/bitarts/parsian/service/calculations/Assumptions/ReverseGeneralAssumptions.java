package com.bitarts.parsian.service.calculations.Assumptions;


import com.bitarts.parsian.service.calculations.Constants.LifeTable;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Faraz
 * Date: Feb 8, 2011
 * Time: 7:02:48 PM
 */
public class ReverseGeneralAssumptions implements NSPConstants {
    private double Nerkh_Afzayesh_Hagh_Bime = 0.0;

    private short Modat_Bime_Nameh = 0;

    private NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte;

    private long Sarmayeh_Fot = 0;

    private double Nerkh_Afzayesh_Sarmayeh = 0.0;

    private byte Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 0;

    private long Mablagh_Seporde_Ebtedaye_Saal_Rial = 0;

    private NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime;

    private double Nerkh_Afzayesh_Mostamari = 0.0;

    private NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri;

    private short Sen_Asli_Bime_Shode = 0;

    private double Ezafe_Nerkh_Pezeshki = 0.0;

    private short Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki = 0;

    private double Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime = 0.0;

    private byte Tabaghe_Khatar = 1;

    private short Modat_Zaman_Daryaft_Mostamari = 0;

    //private long Andukhte_Entehaaye_Modat_Bime_Naame=0;

    private long Sarmaye_Fot_Dar_Asar_Hadese = 0;

    private long Sarmaye_Naghs_Ozv = 0;

    private long Hade_Aksar_Sarmaye_Naghs_Ozv = 0;

    private double Z12 = 0.0;

    private long Sarmaye_Pushesh_Amraaz = 0;

    private boolean Moaafiyat_Az_Pardakht_Hagh_Bime;

    private boolean Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv;

    private boolean Pushesh_Khatar_Zelzele;

    private long Mablagh_Andukhte_Enteha_Modat;

    public ReverseGeneralAssumptions(short Sene_Bime_Shode, double Darsad_Ezafe_Nerkh_Pezeshki, short Modat_Bime_Naameh,
                                     long Sarmaye_Paaye_Fot_Rial, long Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, long Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                     boolean Pushesh_Moaafiyat, boolean Pushesh_Naghs_Ozv, long Sarmaye_Pushesh_Naghs_Ozv,
                                     double Nerkh_Afzayesh_Saalaaneh_Hagh_Bime, double Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot, NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime,
                                     long andukhte_entehaye_modate_bimename, long Mablagh_Seporde_Ebtedaye_Saal_Rial, NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                                     double Nerkh_Afzayesh_Saalaaneh_Mostamari, NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri, short Modat_Zaman_Daryaft_Mostamari,
                                     byte Tabaghe_Khatar, boolean Pushesh_Fot_Dar_Asar_Zelzele, double nerkhBahreFanni, double[][] lifeTable) {
        this.Nerkh_Afzayesh_Hagh_Bime = Nerkh_Afzayesh_Saalaaneh_Hagh_Bime / 100.0;

        this.Modat_Bime_Nameh = Modat_Bime_Naameh;

        this.Mablagh_Andukhte_Enteha_Modat = andukhte_entehaye_modate_bimename;

        this.Nahve_Daryaft_Andukhte = Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh;

        this.Sarmayeh_Fot = Sarmaye_Paaye_Fot_Rial;

        this.Nerkh_Afzayesh_Sarmayeh = Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot / 100.0;

        switch (Nahve_Pardaakht_Hagh_Bime) {
            case SE_MAAHE:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 4;
                break;
            case MAAHAANEH:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 12;
                break;
            case SHISH_MAAHE:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 2;
                break;
            case SAALAANEH:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 1;
                break;
            default:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 0;
                break;
        }

        this.Mablagh_Seporde_Ebtedaye_Saal_Rial = Mablagh_Seporde_Ebtedaye_Saal_Rial;

        this.Nahve_Pardaakht_Hagh_Bime = Nahve_Pardaakht_Hagh_Bime;

        this.Nerkh_Afzayesh_Mostamari = Nerkh_Afzayesh_Saalaaneh_Mostamari / 100.0;

        this.Nahve_Daryaft_Mostamri = Nahve_Daryaft_Mostamri;

        this.Sen_Asli_Bime_Shode = Sene_Bime_Shode;

        this.Ezafe_Nerkh_Pezeshki = Darsad_Ezafe_Nerkh_Pezeshki / 100.0;

        this.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki = computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(lifeTable);

        if (this.Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
            this.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime = 1.0;
        } else {
            this.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime =
                    nerkhBahreFanni / ((Math.pow(1.0 + nerkhBahreFanni, 1.0 / this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal) - 1.0) * this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal);
        }

        this.Tabaghe_Khatar = Tabaghe_Khatar;

        this.Modat_Zaman_Daryaft_Mostamari = Modat_Zaman_Daryaft_Mostamari;

        this.Sarmaye_Fot_Dar_Asar_Hadese = Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial;

        this.Sarmaye_Naghs_Ozv = Sarmaye_Pushesh_Naghs_Ozv;

        this.Hade_Aksar_Sarmaye_Naghs_Ozv = Sarmaye_Paaye_Fot_Rial;

        this.Z12 = nerkhBahreFanni / ((Math.pow(1.0 + nerkhBahreFanni, 1.0 / 12.0)) - 1.0);

        this.Sarmaye_Pushesh_Amraaz = Sarmaye_Pushesh_Amraaz_Khaas_Rial;

        this.Moaafiyat_Az_Pardakht_Hagh_Bime = Pushesh_Moaafiyat;

        this.Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv = Pushesh_Naghs_Ozv;

        this.Pushesh_Khatar_Zelzele = Pushesh_Fot_Dar_Asar_Zelzele;

    }


    public double Nerkh_Afzayesh_Hagh_Bime() {
        return this.Nerkh_Afzayesh_Hagh_Bime;
    }

    public short Modat_Bime_Nameh() {
        return this.Modat_Bime_Nameh;
    }

    public NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte() {
        return this.Nahve_Daryaft_Andukhte;
    }

    public long Sarmayeh_Fot() {
        return this.Sarmayeh_Fot;
    }

    public double Nerkh_Afzayesh_Sarmayeh() {
        return this.Nerkh_Afzayesh_Sarmayeh;
    }

    public byte Dafaat_Pardaakht_Hagh_Bime_Dar_Saal() {
        return this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal;
    }

    public long Mablagh_Seporde_Ebtedaye_Saal_Rial() {
        return this.Mablagh_Seporde_Ebtedaye_Saal_Rial;
    }

    public NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime() {
        return this.Nahve_Pardaakht_Hagh_Bime;
    }

    public double Nerkh_Afzayesh_Mostamari() {
        return this.Nerkh_Afzayesh_Mostamari;
    }

    public NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri() {
        return this.Nahve_Daryaft_Mostamri;
    }

    public short Sen_Asli_Bime_Shode() {
        return this.Sen_Asli_Bime_Shode;
    }

    public double Ezafe_Nerkh_Pezeshki() {
        return this.Ezafe_Nerkh_Pezeshki;
    }

    public short Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki() {
        return this.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki;
    }

    public double Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() {
        return this.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime;
    }

    public byte Tabaghe_Khatar() {
        return this.Tabaghe_Khatar;
    }

    public short Modat_Zaman_Daryaft_Mostamari() {
        return this.Modat_Zaman_Daryaft_Mostamari;
    }

    public long Sarmaye_Fot_Dar_Asar_Hadese() {
        return this.Sarmaye_Fot_Dar_Asar_Hadese;
    }

    public long Sarmaye_Naghs_Ozv() {
        return this.Sarmaye_Naghs_Ozv;
    }

    public long Hade_Aksar_Sarmaye_Naghs_Ozv() {
        return this.Hade_Aksar_Sarmaye_Naghs_Ozv;
    }

    public double Z12() {
        return this.Z12;
    }

    public long Sarmaye_Pushesh_Amraaz() {
        return this.Sarmaye_Pushesh_Amraaz;
    }

    public boolean Moaafiyat_Az_Pardakht_Hagh_Bime() {
        return this.Moaafiyat_Az_Pardakht_Hagh_Bime;
    }

    public boolean Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv() {
        return this.Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv;
    }

    public boolean Pushesh_Khatar_Zelzele() {
        return this.Pushesh_Khatar_Zelzele;
    }

    public long Mablagh_Andukhte_Enteha_Modat() {
        return Mablagh_Andukhte_Enteha_Modat;
    }

    private short computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(double[][] lifeTable) {
        double value = 0;
        short age = 0;
        if (!Ezafe_Nerkh_PezeshkiIsZero()) {
            for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
                if (this.Sen_Asli_Bime_Shode == lifeTable[rowReader][0]) {
                    value = lifeTable[rowReader][8] * (1.0 + this.Ezafe_Nerkh_Pezeshki);
                    break;
                }
            }
            double minus = 1.0;
            for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
                if (Math.abs(lifeTable[rowReader][8] - value) < minus && lifeTable[rowReader][8] <= value) {
                    minus = Math.abs(lifeTable[rowReader][8] - value);
                    age = (short) (lifeTable[rowReader][0] + 1);
                }
            }
            return age;
        } else {
            return 0;
        }
    }

    public boolean Ezafe_Nerkh_PezeshkiIsZero() {
        return (Math.floor(Math.abs(10000 * this.Ezafe_Nerkh_Pezeshki)) == 0);
    }

}
