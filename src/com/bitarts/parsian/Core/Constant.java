package com.bitarts.parsian.Core;

public interface Constant {

    public static final Integer MAX_OBJECTS_PER_PAGE = 30;
    public static final Integer MIN_OBJECTS_PER_PAGE = 5;

    public static final Integer INITIAL_STATE = 10;
    public static final Integer BAZKHARID_INITIAL_STATE = 1000;
    public static final Integer VAM_INITIAL_STATE = 10000;
    public static final Integer VAM_FINAL_STATE = 10100;
    public static final Integer BARDASHT_AZ_ANDOKHTE_INITIAL_STATE = 11000;
    public static final Integer ELHAGHIYE_INITIAL_STATE = 3000;
    public static final Integer ELHAGHIYE_ENSERAF_STATE = 3002;
    public static final Integer ELHAGHIYE_FINAL_STATE = 3001;
    public static final Integer TAGHIRAT_INITIAL_STATE = 9010;
    public static final Integer TASVIE_PISH_AZ_MOEDE_VAM_INITIAL_STATE = 12010;
    public static final Integer BIMENAME_INITIAL_STATE = 500;
    public static final Integer BIMENAME_LOCK_STATE = 510;

    //KhesaratState
    public static final Integer KHESARAT_SABTE_MOVAGHAT = 640;
    public static final Integer KHESARAT_DARKHAST_KHESARAT_JADID = 619;
    public static final Integer KHESARAT_MOSHAHEDE_SHODE = 642;
    public static final Integer KHESARAT_MONTAFI_SHODE = 643;
    public static final Integer KHESARAT_NIAZ_MOJAVEZ = 644;
    public static final Integer KHESARAT_SODOR_MOJAVEZ = 645;
    public static final Integer KHESARAT_ADAM_SODOR_MOJAVEZ = 646;
    public static final Integer KHESARAT_KHATEME_PARVANDE = 647;
    public static final Integer KHESARAT_MONTAZER_BARESI = 648;
    public static final Integer KHESARAT_NAGHS_MADAREK = 649;
    public static final Integer KHESARAT_ESLAH_MADAREK = 650;
    public static final Integer KHESARAT_MONTAFI = 651;
    public static final Integer KHESARAT_DARKHAST_ELHAGHIYE_JADID = 652;
    public static final Integer KHESARAT_BARESI_MOJADAD = 653;
    public static final Integer KHESARAT_TAEED_PARVANDE = 654;
    public static final Integer KHESARAT_MONTAZERE_TAEED = 655;
    public static final Integer KHESARAT_TAEED_NAHAI = 656;
    public static final Integer KHESARAT_KARSHENASI = 658;


    public static final String FISH = "fish";
    public static final String INTERNETI = "interneti";
    public static final String SHENASEDAR = "shenasedar";
    public static final String ERROR = "error";
    public static final String NOSESSION = "nosession";
    public static final String ROLE_KARSHENAS_SODOUR = "ROLE_KARSHENAS_SODUR";
    public static final String ROLE_KARSHENAS_MASOUL = "ROLE_KARSHENAS_MASOUL";
    public static final String ROLE_RAEIS_SODUR = "ROLE_RAEIS_SODUR";
    public static final String ROLE_KARSHENAS_NAZER = "ROLE_KARSHENAS_NAZER";
    public static final String ROLE_PEZESHK = "ROLE_PEZESHK";
    public static final String ROLE_NAMAYANDE = "ROLE_NAMAYANDE";
    public static final String ROLE_HAZFE_ETEBAR = "ROLE_HAZFE_ETEBAR";
    public static final String ROLE_KARMOZD_NAMAYANDE = "ROLE_KARMOZD_NAMAYANDE";
    public static final String ROLE_KARMOZD = "ROLE_KARMOZD";
    public static final String ROLE_BAZARYAB = "ROLE_BAZARYAB";
    public static final String ROLE_PAS_KARSHENAS = "ROLE_PAS_KARSHENAS";
    public static final String ROLE_MALI_OPERATOR = "ROLE_MALI_OPERATOR";
    public static final String MESSAGES_MAP = "messagesMap";
    public static final String ERRORS_MAP = "errorsMap";
    public static final String PISHNEHAD_SYSTEM = "PISHNAHAD_SYSTEM";
    public static final String PISHNEHAD_CREATION_LOG_MESSAGE = "پیشنهاد ایجاد شد.";

    public static String[] NoeGharardad = new String[]{"قرارداد عمومي بيمه مركزي", "قرارداد بيمه-بانك پارسيان(BankAssurance)", "قرارداد كاركنان بيمه پارسيان", "قرارداد فروش جمعي", "ساير"};

    public static final Integer NERKHE_BAHRE_SALANEH_BE_DARSAD = 19;
    public static final double NERKHE_Maliat = 0.4;

    long MAXIMUM_FILE_SIZE_ALLOWED = 1048576;


    public static Integer BARDASHT_AZ_ANDOKHTE_FINAL_STATE = 11090;
    public static Integer VAM_ENSERAF = 10140;
    public static Integer BARDASHT_ENSERAF = 11100;

    public static Integer BIMENAME_FASKH = 520;
    public static Integer BIMENAME_BASTE = 560;
    public static Integer BIMENAME_EBTAL = 540;
    public static Integer BIMENAME_BAZKHARID = 550;

    public static Integer KHESARAT_INIT = 600;
    public static Integer KHESARAT_FIN = 610;
    public static Integer KHESARAT_ER = 620;
    public static Integer KHESARAT_ENSAREF = 630;
    public static Integer KHESARAT_SABT_MOVAGHAT = 640;
    public static Integer KHESARAT_SABT_JADID = 619;


//    public static Integer LogicalDocLifeFolderId = 6;
    public static Integer LogicalDocLifeFolderId = 83430;
    public static String LogicalDocUserName = "admin";
    public static String LogicalDocUserPass = "admin";

    public static String CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD = "تجارت الکترونیک - میرداماد شرقی (0201136462000)";//130 ,230 - electronici
    public static String CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD_GROUP = "تجارت الکترونیک - میرداماد شرقی (0201136462000) - پرداخت گروهی";//
    public static String CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ_GROUP = "پارسیان - بلوار کشاورز (0200234164006)- پرداخت گروهی";  //
    public static String CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ = "پارسیان - بلوار کشاورز (0200234164006)";  //110 - pishpardakht
    public static String CREDEBIT_BANK_PARSIAN_VANAK = "پارسیان - ونک (81011989)";  //110 - aghsat omr
    public static String CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI = "تجارت - سپهبد قرنی (17038494)";  //120 - aghsat omr
    public static String CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_NEW = "تجارت - سپهبد قرنی (0185007111)";  //120 - aghsat omr
    public static String CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_GHEIRE_OMR = "تجارت - سپهبد قرنی (17123130)";  //120
    public static String CREDEBIT_BANK_MELAT_VANAK = "ملت - ونک (4757575763)"; //242 - sanam
    public static String CREDEBIT_BANK_MELAT_ESKAN = "ملت - اسکان (2177777733)"; //241 - sanam

    public static final boolean DEV_testElhaghieTaghirat = false; // true: elhaghie taghirat ba dokme sodoor sader nemishe
    public static final boolean DEV_sendSMS = false; // true: don't send sms
    public static final boolean DEV_nezaratEnabled = true; // true: Tarh Nezarat enabled
}


