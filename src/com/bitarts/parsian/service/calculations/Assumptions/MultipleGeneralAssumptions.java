package com.bitarts.parsian.service.calculations.Assumptions;

import com.bitarts.parsian.service.calculations.Constants.NSPConstants;

/**
 * Created by IntelliJ IDEA.
 * User: e-soleymani
 * Date: 4/13/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultipleGeneralAssumptions implements NSPConstants {
    private int maxPersonsCount;

    private boolean[] isPersonRegistered;

    private double Nerkh_Afzayesh_Hagh_Bime = 0.0;

    private short Modat_Bime_Nameh = 0;

    private long Mablagh_Hagh_Bime_Pardaakhti = 0;

    private NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte;

    private long[] Sarmayeh_Fot;

    private double[] Nerkh_Afzayesh_Sarmayeh;

    private byte Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 0;

    private long Mablagh_Seporde_Ebtedaye_Saal_Rial = 0;

    private NAHVE_PARDAAKHT_HAGH_BIME Nahve_Pardaakht_Hagh_Bime;

    private double Nerkh_Afzayesh_Mostamari = 0.0;

    private NAHVE_DARYAAFT_MOSTAMERI Nahve_Daryaft_Mostamri;

    private short[] Sen_Asli_Bime_Shode;

    private double[] Ezafe_Nerkh_Pezeshki;

    private short[] Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki;

    private double Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime = 0.0;

    private byte[] Tabaghe_Khatar;

    private byte[] Tabaghe_Khatar_Naghsozv;

    private short Modat_Zaman_Daryaft_Mostamari = 0;

    //private long Andukhte_Entehaaye_Modat_Bime_Naame=0;

    private long[] Sarmaye_Fot_Dar_Asar_Hadese;

    private long[] Sarmaye_Naghs_Ozv;

    private long[] Hade_Aksar_Sarmaye_Naghs_Ozv;

    private double Z12 = 0.0;

    private long[] Sarmaye_Pushesh_Amraaz;

    private boolean[] Moaafiyat_Az_Pardakht_Hagh_Bime;

    private boolean[] Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv;

    private boolean[] Pushesh_Khatar_Zelzele;

    private double nerkhBahreFanni;

    private double nerkhBahreFanni5SaleAvval;

    private NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime;

    private long hadeAksareSarmayeFotSaleAval;

    private boolean moafiatFoteMoghadam;

    public MultipleGeneralAssumptions(boolean[] isPersonRegistered,
                                      short[] Sene_Bime_Shode,
                                      double[] Darsad_Ezafe_Nerkh_Pezeshki,
                                      short Modat_Bime_Naameh,
                                      long[] Sarmaye_Paaye_Fot_Rial,
                                      long[] Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial,
                                      long[] Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                                      boolean[] Pushesh_Moaafiyat,
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
                                      boolean moafiatFoteMoghadam,
                                      double nerkhBahreFanni,
                                      double[][] lifeTable,
                                      NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime) {

        this.maxPersonsCount = isPersonRegistered.length;

        this.isPersonRegistered = isPersonRegistered;

        this.Nerkh_Afzayesh_Hagh_Bime = Nerkh_Afzayesh_Saalaaneh_Hagh_Bime / 100.0;

        this.Modat_Bime_Nameh = Modat_Bime_Naameh;

        this.setNerkhBahreFanni(nerkhBahreFanni);

        this.Mablagh_Hagh_Bime_Pardaakhti = Hagh_Bime_Pardaakhti_Rial;

        this.Nahve_Daryaft_Andukhte = Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh;

        this.Sarmayeh_Fot = Sarmaye_Paaye_Fot_Rial;

        this.Nerkh_Afzayesh_Sarmayeh = tabdileDarsadBeNerkh(Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot);

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
            case YEKJA:
                this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal = 0;
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

        this.Ezafe_Nerkh_Pezeshki = tabdileDarsadBeNerkh(Darsad_Ezafe_Nerkh_Pezeshki);

        this.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki = computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki_For_All(Sene_Bime_Shode.length,lifeTable);

        if (this.Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
            this.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime = 1.0;
        } else {
            this.Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime =
                    nerkhBahreFanni / ((Math.pow(1.0 + nerkhBahreFanni, 1.0 / this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal) - 1.0) * this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal);
        }

        this.Tabaghe_Khatar = Tabaghe_Khatar;

        this.Tabaghe_Khatar_Naghsozv = Tabaghe_Khatar_Naghsozv;

        this.Modat_Zaman_Daryaft_Mostamari = Modat_Zaman_Daryaft_Mostamari;

        this.Sarmaye_Fot_Dar_Asar_Hadese = Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial;

        this.Sarmaye_Naghs_Ozv = Sarmaye_Pushesh_Naghs_Ozv;

        this.Hade_Aksar_Sarmaye_Naghs_Ozv = Sarmaye_Paaye_Fot_Rial;

        this.Z12 = nerkhBahreFanni / ((Math.pow(1.0 + nerkhBahreFanni, 1.0 / 12.0)) - 1.0);

        this.Sarmaye_Pushesh_Amraaz = Sarmaye_Pushesh_Amraaz_Khaas_Rial;

        this.Moaafiyat_Az_Pardakht_Hagh_Bime = Pushesh_Moaafiyat;

        this.Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv = Pushesh_Naghs_Ozv;

        this.Pushesh_Khatar_Zelzele = Pushesh_Fot_Dar_Asar_Zelzele;

        this.moafiatFoteMoghadam = moafiatFoteMoghadam;

        this.setNahve_kasr_hazineha_az_hagh_bime(nahve_kasr_hazineha_az_hagh_bime);

        this.hadeAksareSarmayeFotSaleAval = calculateHadeAksareSarmayeFot(Sarmaye_Paaye_Fot_Rial);
    }


    public double Nerkh_Afzayesh_Hagh_Bime() {
        return this.Nerkh_Afzayesh_Hagh_Bime;
    }

    public short Modat_Bime_Nameh() {
        return this.Modat_Bime_Nameh;
    }

    public long Mablagh_Hagh_Bime_Pardaakhti() {
        return this.Mablagh_Hagh_Bime_Pardaakhti;
    }

    public NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH Nahve_Daryaft_Andukhte() {
        return this.Nahve_Daryaft_Andukhte;
    }

    public long Sarmayeh_Fot(int personIndex) {
        return this.Sarmayeh_Fot[personIndex];
    }

    public double Nerkh_Afzayesh_Sarmayeh(int personIndex) {
        return this.Nerkh_Afzayesh_Sarmayeh[personIndex];
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

    public short Sen_Asli_Bime_Shode(int personIndex) {
        return this.Sen_Asli_Bime_Shode[personIndex];
    }

    public double Ezafe_Nerkh_Pezeshki(int personIndex) {
        return this.Ezafe_Nerkh_Pezeshki[personIndex];
    }

    public short Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(int personIndex) {
        return this.Sen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki[personIndex];
    }

    public double Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime() {
        if (this.Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
            return 1.0;
        } else {
            return this.getNerkhBahreFanni() / ((Math.pow(1.0 + getNerkhBahreFanni(), 1.0 / this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal) - 1.0) * this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal);
        }
    }

    public double Zarib_Tabdil_Nahve_Pardaakht_Hagh_Bime_5Sal_Avval() {
        if (this.Nahve_Pardaakht_Hagh_Bime == NAHVE_PARDAAKHT_HAGH_BIME.YEKJA) {
            return 1.0;
        } else {
            return this.nerkhBahreFanni5SaleAvval / ((Math.pow(1.0 + nerkhBahreFanni5SaleAvval, 1.0 / this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal) - 1.0) * this.Dafaat_Pardaakht_Hagh_Bime_Dar_Saal);
        }
    }

    public byte Tabaghe_Khatar(int personIndex) {
        return this.Tabaghe_Khatar[personIndex];
    }

    public byte Tabaghe_Khatar_Naghsozv(int personIndex) {
        return this.Tabaghe_Khatar_Naghsozv[personIndex];
    }

    public short Modat_Zaman_Daryaft_Mostamari() {
        return this.Modat_Zaman_Daryaft_Mostamari;
    }

    public long Sarmaye_Fot_Dar_Asar_Hadese(int personIndex) {
        return this.Sarmaye_Fot_Dar_Asar_Hadese[personIndex];
    }

    public long Sarmaye_Naghs_Ozv(int personIndex) {
        return this.Sarmaye_Naghs_Ozv[personIndex];
    }

    public long Hade_Aksar_Sarmaye_Naghs_Ozv(int personIndex) {
        return this.Hade_Aksar_Sarmaye_Naghs_Ozv[personIndex];
    }

    public double Z12() {
        return this.Z12;
    }

    public long Sarmaye_Pushesh_Amraaz(int personIndex) {
        return this.Sarmaye_Pushesh_Amraaz[personIndex];
    }

    public boolean[] Moaafiyat_Az_Pardakht_Hagh_Bime() {
        return this.Moaafiyat_Az_Pardakht_Hagh_Bime;
    }

    public boolean Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv(int personIndex) {
        return this.Pardakht_Gheraamat_Az_Kaar_Oftaadegi_Va_Naghs_Ozv[personIndex];
    }

    public boolean Pushesh_Khatar_Zelzele(int personIndex) {
        return this.Pushesh_Khatar_Zelzele[personIndex];
    }

    private short[] computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki_For_All(int personsNum, double[][] lifeTable) {
        short[] res= new short[personsNum];
        for (int personIndex = 0; personIndex < personsNum; ++personIndex) {
            res[personIndex] = computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(personIndex,lifeTable);
        }
        return res;
    }

    private short computingSen_Bime_Shode_Pas_Az_Emal_Ezafe_Nerkh_Pezeshki(int personIndex, double[][] lifeTable) {
        double value = 0;
        short age = 0;
        if (!Ezafe_Nerkh_PezeshkiIsZero(personIndex)) {
            for (int rowReader = 0; rowReader < lifeTable.length; rowReader++) {
                if (this.Sen_Asli_Bime_Shode[personIndex] == lifeTable[rowReader][0]) {
                    value = lifeTable[rowReader][8] * (1.0 + this.Ezafe_Nerkh_Pezeshki[personIndex]);
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

    public boolean Ezafe_Nerkh_PezeshkiIsZero(int personIndex) {
        return (Math.floor(Math.abs(10000 * this.Ezafe_Nerkh_Pezeshki[personIndex])) == 0);
    }

    public NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI getNahve_kasr_hazineha_az_hagh_bime() {
        return nahve_kasr_hazineha_az_hagh_bime;
    }

    public void setNahve_kasr_hazineha_az_hagh_bime(NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahve_kasr_hazineha_az_hagh_bime) {
        this.nahve_kasr_hazineha_az_hagh_bime = nahve_kasr_hazineha_az_hagh_bime;
    }

    public double getNerkhBahreFanni5SaleAvval() {
        return nerkhBahreFanni5SaleAvval;
    }

    public void setNerkhBahreFanni5SaleAvval(double nerkhBahreFanni5SaleAvval) {
        this.nerkhBahreFanni5SaleAvval = nerkhBahreFanni5SaleAvval;
    }

    public double getNerkhBahreFanni() {
        return nerkhBahreFanni;
    }

    public void setNerkhBahreFanni(double nerkhBahreFanni) {
        this.nerkhBahreFanni = nerkhBahreFanni;
    }

    private long calculateHadeAksareSarmayeFot(long[] sarmayeFot) {
        long maxSarmayeFot = 0;
        for (int personIndex = 0; personIndex < sarmayeFot.length; ++personIndex) {
            maxSarmayeFot = Math.max(maxSarmayeFot, sarmayeFot[personIndex]);
        }
        return maxSarmayeFot;
    }

    public long hadeAksareSarmayeFotSaleAval() {
        return this.hadeAksareSarmayeFotSaleAval;
    }

    private double[] tabdileDarsadBeNerkh(double[] darsadeAfzayeshSarmayeSalane) {
        double[] res = new double[darsadeAfzayeshSarmayeSalane.length];
        for (int personIndex = 0; personIndex < darsadeAfzayeshSarmayeSalane.length; ++personIndex) {
            res[personIndex] = darsadeAfzayeshSarmayeSalane[personIndex] / 100.0;
        }
        return res;
    }

    public boolean isPersonRegistered(int personIndex){
        return isPersonRegistered[personIndex];
    }

    public int maxPersonsCount(){
        return maxPersonsCount;
    }

    public boolean moafiatFoteMoghadam(){
        return this.moafiatFoteMoghadam;
    }
}
