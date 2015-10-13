package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 1:39 PM
 */
@Entity
@Table(name = "tbl_credebit")
public class Credebit implements Serializable, Comparable {
    public static final String ID = "id";


    public static enum CredebitNoe {

    }
    public static enum CredebitType {
        GHEST, GHEST_VAM, PARDAKHTE_CHECK, PARDAKHTE_TANKHAH, DARYAFTE_FISH_NAMAYANDE, DARYAFTE_FISH, PARDAKHT_SHENASEDAR, DARYAFTE_ELECTRONICI, AZ_MAHALLE_BAZKHARID, UNKNOWN, ETEBAR_VAM, ETEBAR_BARDASHT, DARYAFTE_CHECK_NAMAYANDE, DARYAFTE_CHECK, PARDAKHT_KARMOZD,BARGASHT_KARMOZD, MOSHAREKAT,
        ETEBAR_KHESARAT, ELHAGHIE_EZAFI, ELHAGHIE_BARGASHTI, PISHPARDAKHT, VEHICLE_HAGHBIME, VEHICLE_HAGHBIME_BARGASHTI, ELHAGHIE_BARGASHTI_ETEBAR, VEHICLE_HAGHBIME_ELECTRONICI, VEHICLE_DARYAFT_ELECTRONICI,
        TAGHIRAT_ESLAH_AGHSAT, ETEBAR_EBTAL, HAZINE_PEZESHKI, ACH, CASH_NAMAYANDE, CASH, POS, HESAB_FI_MA_BEYN, HESAB_FI_MA_BEYN_BEDEHI, DARMAN_HAGHBIME, KASR_AZ_HOGHUGH,AZ_MAHALLE_TABLIGHAT,AZ_MAHALLE_TABLIGHAT_MANUAL, AZ_MAHALLE_TABLIGHAT_BEDEHI, DARMAN_ELHAGHIYE_BARGASHTI,
        TASVIE_PISH_AZ_MOED_BEDEHI, TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE, VEHICLE_KHESARAT ,AZ_MAHALE_HAZINE_BIME_DARAYIHA,ETEBAR_DARMAN_KHANEVADE,
        KASR_AZ_KARMOZD,
        SIMAB_CASH,
        SIMAB_FISH,
        SIMAB_CHEQUE,
        SIMAB_DECENDORSE,
        SIMAB_LOAN,
        SIMAB_CANCELCHEQUE,
        SIMAB_PROPAGANDA,
        SIMAB_DEBITPROPAGANDA,
        SIMAB_LOSSBILL_INC,
        SIMAB_LOSSBILL_ONACCOUNT,
        SIMAB_LOSSBILL_INCCOMPLEMENT,
        SIMAB_LOSSEXTRASURVEY,
        SIMAB_LOSSBILL_DEC,
        SIMAB_LOSSBILL_DECCOMPLEMENT,
        SIMAB_LOSSEXTRASURVEY_DEC,
        SIMAB_LOSSRECOVERYRECIEVE,
        SIMAB_LOSSRECOVERYRECIEVEGHEST,
        SIMAB_DEBIT_PAYMENTPAYA,
        SIMAB_CREDIT_PAYMENTPAYA,
        SIMAB_EXTRASURVEYTRANSFER,
        SIMAB_EXTRASURVEYTRANSFER_DEC,
        SIMAB_WAGEBACK,
        SIMAB_WAGEPAYED,
        SIMAB_LIFECHANGEAGENCY,
        SIMAB_POLICYTHIRDRET160111,
        SIMAB_LIFENOPAYDEBIT,
        SIMAB_POLICY_PRM,
        SIMAB_POLICY_GHESTPRM,
        SIMAB_ENDORSE_INCPRM,
        SIMAB_ENDORSE_GHESTINCPRM,
        SIMAB_CREDITREMOVESTORE,
        SIMAB_CREDITADDSTORE,
        SIMAB_INSTALLMENTLOAN,
        SIMAB_DEBITDELAYINSTALLMENTLOAN,
        SIMAB_CREDITSOLUTIONLOAN,
        SIMAB_CREDITSHARE,
        SIMAB_DEBITAGENCYWAGE,
        SIMAB_TAXFISH,
        SIMAB_PAYCASH,
        SIMAB_PAYFISH,
        SIMAB_PAYCHEQUE,
        SIMAB_DEBITCURRENT,
        SIMAB_CREDITCURRENT,
        SIMAB_DEBITBANKACCOUNT,
        SIMAB_DEBITINSUREDACCOUNT,
        SIMAB_CREDITBANKACCOUNT,
        SIMAB_CREDITINSUREDACCOUNT,
        SIMAB_CREDITELECTRONICFICH,
        SIMAB_CREDITCURRENCYCONVERSION,
        SIMAB_DEBITCURRENCYCONVERSION,
        SIMAB_CREDITAGENCYWAGE,
        SIMAB_CREDITREINSURANCE,
        SIMAB_DEBITREINSURANCE,
        SIMAB_CREDITREINSURANCEINSTALLMENT,
        SIMAB_DEBITFUND
    }
    public static enum ACHStatus{
        FIRSTSIGNATURE,DENY_FIRSTSIGNATURE,SECONDSIGNATURE,DENY_SECONDSIGNATURE,SENDTOBANK,MANUALPAYMNET,CANCELREQUEST,SUCCESSSPAYMENT,ERRORPAYMNET,SUBMITTECHNICALUNIT
    }

    public static enum CredebitTypeDesc {
        EHSAN, SODOR, JOBRAN_HAZINEHA, BARDASHT, KHONSA_KARDANE_POOSHESHHA
    }

    public static CredebitType[] bedehiTypes = new CredebitType[]{
                CredebitType.GHEST,
                CredebitType.GHEST_VAM,
                CredebitType.PARDAKHTE_CHECK,
                CredebitType.PARDAKHTE_TANKHAH,
                CredebitType.VEHICLE_HAGHBIME,
                CredebitType.VEHICLE_HAGHBIME_ELECTRONICI,
                CredebitType.HAZINE_PEZESHKI,
                CredebitType.BARGASHT_KARMOZD,
                CredebitType.ACH,
                CredebitType.HESAB_FI_MA_BEYN_BEDEHI,
                CredebitType.DARMAN_HAGHBIME,
                CredebitType.AZ_MAHALLE_TABLIGHAT_BEDEHI,
                CredebitType.TASVIE_PISH_AZ_MOED_BEDEHI,
            CredebitType.SIMAB_DEBITPROPAGANDA,
            CredebitType.SIMAB_LOSSBILL_DEC,
            CredebitType.SIMAB_LOSSBILL_DECCOMPLEMENT,
            CredebitType.SIMAB_LOSSEXTRASURVEY_DEC,
            CredebitType.SIMAB_LOSSRECOVERYRECIEVE,
            CredebitType.SIMAB_LOSSRECOVERYRECIEVEGHEST,
            CredebitType.SIMAB_DEBIT_PAYMENTPAYA,
            CredebitType.SIMAB_EXTRASURVEYTRANSFER_DEC,
            CredebitType.SIMAB_WAGEBACK,
            CredebitType.SIMAB_LIFENOPAYDEBIT,
            CredebitType.SIMAB_POLICY_PRM,
            CredebitType.SIMAB_POLICY_GHESTPRM,
            CredebitType.SIMAB_ENDORSE_INCPRM,
            CredebitType.SIMAB_ENDORSE_GHESTINCPRM,
            CredebitType.SIMAB_CREDITADDSTORE,
            CredebitType.SIMAB_INSTALLMENTLOAN,
            CredebitType.SIMAB_DEBITDELAYINSTALLMENTLOAN,
            CredebitType.SIMAB_DEBITAGENCYWAGE,
            CredebitType.SIMAB_PAYCASH,
            CredebitType.SIMAB_PAYFISH,
            CredebitType.SIMAB_PAYCHEQUE,
            CredebitType.SIMAB_DEBITCURRENT,
            CredebitType.SIMAB_DEBITBANKACCOUNT,
            CredebitType.SIMAB_DEBITINSUREDACCOUNT,
            CredebitType.SIMAB_DEBITCURRENCYCONVERSION,
            CredebitType.SIMAB_DEBITREINSURANCE,
            CredebitType.SIMAB_DEBITFUND

    };
    public static CredebitType[] etebarTypes = new CredebitType[]{
            CredebitType.TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE,
            CredebitType.DARYAFTE_FISH,
            CredebitType.DARYAFTE_FISH_NAMAYANDE,
            CredebitType.DARYAFTE_ELECTRONICI,
            CredebitType.AZ_MAHALLE_BAZKHARID,
            CredebitType.ETEBAR_VAM,
            CredebitType.ETEBAR_BARDASHT,
            CredebitType.DARYAFTE_CHECK,
            CredebitType.DARYAFTE_CHECK_NAMAYANDE,
            CredebitType.PARDAKHT_KARMOZD,
            CredebitType.MOSHAREKAT,
            CredebitType.ETEBAR_KHESARAT,
            CredebitType.ELHAGHIE_EZAFI,
            CredebitType.ELHAGHIE_BARGASHTI,
            CredebitType.ELHAGHIE_BARGASHTI_ETEBAR,
            CredebitType.PISHPARDAKHT,
            CredebitType.VEHICLE_HAGHBIME_BARGASHTI,
            CredebitType.VEHICLE_KHESARAT,
            CredebitType.VEHICLE_DARYAFT_ELECTRONICI,
            CredebitType.ETEBAR_EBTAL,
            CredebitType.CASH,
            CredebitType.CASH_NAMAYANDE,
            CredebitType.POS,
            CredebitType.HESAB_FI_MA_BEYN,
            CredebitType.PARDAKHT_SHENASEDAR,
            CredebitType.AZ_MAHALLE_TABLIGHAT,
            CredebitType.AZ_MAHALLE_TABLIGHAT_MANUAL,
            CredebitType.KASR_AZ_HOGHUGH,
            CredebitType.DARMAN_ELHAGHIYE_BARGASHTI ,
            CredebitType.AZ_MAHALE_HAZINE_BIME_DARAYIHA ,
            CredebitType.ETEBAR_DARMAN_KHANEVADE,
            CredebitType.KASR_AZ_KARMOZD,
            CredebitType.SIMAB_CASH,
            CredebitType.SIMAB_FISH,
            CredebitType.SIMAB_CHEQUE,
            CredebitType.SIMAB_DECENDORSE,
            CredebitType.SIMAB_LOAN,
            CredebitType.SIMAB_CANCELCHEQUE,
            CredebitType.SIMAB_PROPAGANDA,
            CredebitType.SIMAB_LOSSBILL_INC,
            CredebitType.SIMAB_LOSSBILL_ONACCOUNT,
            CredebitType.SIMAB_LOSSBILL_INCCOMPLEMENT,
            CredebitType.SIMAB_LOSSEXTRASURVEY,
            CredebitType.SIMAB_CREDIT_PAYMENTPAYA,
            CredebitType.SIMAB_EXTRASURVEYTRANSFER,
            CredebitType.SIMAB_WAGEPAYED,
            CredebitType.SIMAB_LIFECHANGEAGENCY,
            CredebitType.SIMAB_POLICYTHIRDRET160111,
            CredebitType.SIMAB_CREDITREMOVESTORE,
            CredebitType.SIMAB_CREDITSOLUTIONLOAN,
            CredebitType.SIMAB_CREDITSHARE,
            CredebitType.SIMAB_TAXFISH,
            CredebitType.SIMAB_CREDITCURRENT,
            CredebitType.SIMAB_CREDITBANKACCOUNT,
            CredebitType.SIMAB_CREDITINSUREDACCOUNT,
            CredebitType.SIMAB_CREDITELECTRONICFICH,
            CredebitType.SIMAB_CREDITCURRENCYCONVERSION,
            CredebitType.SIMAB_CREDITAGENCYWAGE,
            CredebitType.SIMAB_CREDITREINSURANCE,
            CredebitType.SIMAB_CREDITREINSURANCEINSTALLMENT
    };
    public static CredebitType[] credebitTypesNaMotabarSanadDasti = new CredebitType[]
            {
            CredebitType.ACH //noe majazi
          ,CredebitType.AZ_MAHALLE_TABLIGHAT
          ,CredebitType.MOSHAREKAT
          ,CredebitType.SIMAB_CASH,
           CredebitType.SIMAB_FISH,
           CredebitType.SIMAB_CHEQUE,
           CredebitType.SIMAB_DECENDORSE,
           CredebitType.SIMAB_LOAN,
           CredebitType.SIMAB_CANCELCHEQUE,
           CredebitType.SIMAB_PROPAGANDA,
           CredebitType.SIMAB_DEBITPROPAGANDA,
           CredebitType.SIMAB_LOSSBILL_INC,
           CredebitType.SIMAB_LOSSBILL_ONACCOUNT,
           CredebitType.SIMAB_LOSSBILL_INCCOMPLEMENT,
           CredebitType.SIMAB_LOSSEXTRASURVEY,
           CredebitType.SIMAB_LOSSBILL_DEC,
           CredebitType.SIMAB_LOSSBILL_DECCOMPLEMENT,
           CredebitType.SIMAB_LOSSEXTRASURVEY_DEC,
           CredebitType.SIMAB_LOSSRECOVERYRECIEVE,
           CredebitType.SIMAB_LOSSRECOVERYRECIEVEGHEST,
           CredebitType.SIMAB_DEBIT_PAYMENTPAYA,
           CredebitType.SIMAB_CREDIT_PAYMENTPAYA,
           CredebitType.SIMAB_EXTRASURVEYTRANSFER,
           CredebitType.SIMAB_EXTRASURVEYTRANSFER_DEC,
           CredebitType.SIMAB_WAGEBACK,
           CredebitType.SIMAB_WAGEPAYED,
           CredebitType.SIMAB_LIFECHANGEAGENCY,
           CredebitType.SIMAB_POLICYTHIRDRET160111,
           CredebitType.SIMAB_LIFENOPAYDEBIT,
           CredebitType.SIMAB_POLICY_PRM,
           CredebitType.SIMAB_POLICY_GHESTPRM,
           CredebitType.SIMAB_ENDORSE_INCPRM,
           CredebitType.SIMAB_ENDORSE_GHESTINCPRM,
           CredebitType.SIMAB_CREDITREMOVESTORE,
           CredebitType.SIMAB_CREDITADDSTORE,
           CredebitType.SIMAB_INSTALLMENTLOAN,
           CredebitType.SIMAB_DEBITDELAYINSTALLMENTLOAN,
           CredebitType.SIMAB_CREDITSOLUTIONLOAN,
           CredebitType.SIMAB_CREDITSHARE,
           CredebitType.SIMAB_DEBITAGENCYWAGE,
           CredebitType.SIMAB_TAXFISH,
           CredebitType.SIMAB_PAYCASH,
           CredebitType.SIMAB_PAYFISH,
           CredebitType.SIMAB_PAYCHEQUE,
           CredebitType.SIMAB_CREDITCURRENT,
           CredebitType.SIMAB_DEBITCURRENT,
           CredebitType.SIMAB_DEBITBANKACCOUNT,
           CredebitType.SIMAB_DEBITINSUREDACCOUNT,
           CredebitType.SIMAB_CREDITBANKACCOUNT,
           CredebitType.SIMAB_CREDITINSUREDACCOUNT,
           CredebitType.SIMAB_CREDITELECTRONICFICH,
           CredebitType.SIMAB_CREDITCURRENCYCONVERSION,
           CredebitType.SIMAB_DEBITCURRENCYCONVERSION,
           CredebitType.SIMAB_CREDITAGENCYWAGE,
           CredebitType.SIMAB_CREDITREINSURANCE,
           CredebitType.SIMAB_DEBITREINSURANCE,
           CredebitType.SIMAB_CREDITREINSURANCEINSTALLMENT,
           CredebitType.SIMAB_DEBITFUND
    };

    public static enum NahveVosoul {
        INTERNET,ADI
    }

    public static enum Status {
        SANAD_KHORDE, SANAD_NA_KHORDE, SANAD_KHORDE_FISH
    }

    public static enum VaziyatVosoul {
        TAEED_VOSOUL,TAEED_NASHODE
    }

    public static enum CredebitMainType {
        BEDEHI,ETEBAR
    }

    public static enum DescriptionVosoul {
        VOSOUL_TEKRARI
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "amount_long")
    private Long amount_long;
    @Column(name = "amount")
    private String amount;
    @Column(name = "remaining_amount_long")
    private Long remainingAmount_long;
    //    @Column(name = "remaining_amount")
    private String remainingAmount;
    @Column(name = "shomare_moshtari")
    private String shomareMoshtari;
    @Column(name = "shenase_credebit")
    private String shenaseCredebit;
    @Column(name = "rcpt_name")
    private String rcptName;
    @Column(name = "description")
    private String description;
    @Column(name = "subsystem_name")
    private String subsystemName;
    @Column(name = "shomare_gharardad")
    private String shomareGharardad;
    @Column(name = "subsystem_identifier")
    private String identifier;
    @Column(name = "sarresid_date")
    private String sarresidDate;
    @Column(name = "mohlat_sarresid")
    private Integer mohlatSarresid;
    @Formula("TO_char(( TO_DATE(sarresid_date, 'yyyy/mm/dd', 'nls_calendar=persian') + nvl(mohlat_sarresid,0) ),'yyyy/mm/dd','nls_calendar=persian')")
    private String sarresidDateWithMohlatSarresid;

    @Column(name = "authority_id")
    private String authorityId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bimename_id")
    private Bimename bimename;
    @Column(name = "credebit_type")
    @Enumerated(EnumType.STRING)
    private CredebitType credebitType;

    @Column(name = "ach_status")
    @Enumerated(EnumType.STRING)
    private ACHStatus achStatus;
    @Column(name = "credebit_type_desc")
    @Enumerated(EnumType.STRING)
    private CredebitTypeDesc credebitTypeDesc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id")
    private Check check;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pishnehad pishnehad;
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ghest_id")
//    private Ghest ghest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ghest_id")
    private Ghest ghest;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pardakhte_tankhah_id")
    private PardakhteTankhah pardakhteTankhah;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daryafte_fish_id")
    private DaryafteFish daryafteFish;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daryafte_check_id")
    private DaryafteCheck daryafteCheck;
    @OneToMany(mappedBy = "bedehiCredebit", fetch = FetchType.LAZY)
    private List<KhateSanad> khateSanadsBedehi;
    @OneToMany(mappedBy = "etebarCredebit", fetch = FetchType.LAZY)
    private List<KhateSanad> khateSanadsEtebar;

    @OneToMany(mappedBy = "credebit", fetch = FetchType.LAZY)
    private List<KarmozdGhest> karmozdGhestList;

    @Column(name = "date_fish")
    private String dateFish;
    @Column(name = "time_fish")
    private String timeFish;
    @Column(name = "format")
    private String format;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "ACH_rahgiri")
    private String rahgiriACH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayande_id")
    private Namayande namayande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vahedeSodor_id")
    private Namayande vahedeSodor;

    @Column(name = "POS_terminalNo")
    private String terminalNoPOS;
    @Column(name = "POS_transSerial")
    private String transSerialPOS;
    @Column(name = "POS_marjaNo")
    private String marjaNoPOS;
    @Column(name = "POS_codePeygiri")
    private String codePeygiriPOS;
    @Column(name = "vosoul_state")
    @Enumerated(EnumType.STRING)
    private VaziyatVosoul vosoulState;

    @Column(name = "nahve_vosoul")
    @Enumerated(EnumType.STRING)
    private NahveVosoul nahveVosoul;

    @Formula("amount_long - remaining_amount_long")
    private Long paidReceivedAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vagozari_id")
    private Vagozari vagozari;


    @Column(name = "uniqueCode")
    private String uniqueCode;

    @Column(name="vosoul_date")
    private String vosoulDate;

    @OneToMany(mappedBy = "credebit", fetch = FetchType.LAZY)
    private List<BankInfo> bankInfoList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_bazkharid")
    private DarkhastBazkharid darkhastBazkharid;

    @Column(name = "dev_flag")
    private String devFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bazaryab_sanam_id")
    private BazarYabSanam bazarYabSanam ;

    //b-h
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daftar_id")
    private Daftar daftar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAFTARPARSIAN_CREDEBITID")
    private Credebit daftarParsian_credebit;

    @Column(name = "VAZIAT_ENTEGHAL_BEDEHI")
    private Integer vaziatEnteghalBedehi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User  user;

    //----------------------------------------------------------------------------------------------------------------------

    public Credebit() {
        this.vosoulState = VaziyatVosoul.TAEED_NASHODE;
    }

    public Credebit(String amount, String shenaseCredebit, Bimename bimename, Pishnehad pishnehad, CredebitType credebitType) {
       setAmount(amount);
       setRemainingAmount(amount);
        this.createdDate = DateUtil.getCurrentDate();
        this.createdTime = DateUtil.getCurrentTime();
        if (bimename == null) {
            this.pishnehad = pishnehad;
            this.bimename = pishnehad!=null?pishnehad.getBimename():null;
            this.identifier= this.bimename!=null?this.bimename.getShomare():null;
        } else {
            this.bimename = bimename;
            this.pishnehad = bimename.getPishnehad();
            this.identifier = bimename.getShomare();
        }

        this.shenaseCredebit = shenaseCredebit;
        this.credebitType = credebitType;
        if (pishnehad != null)
        {
            this.pishnehad =  pishnehad;
            this.rcptName = pishnehad.getBimeGozar().getShakhs().getFullName();
        }
        this.subsystemName = "";
        this.status = Credebit.Status.SANAD_NA_KHORDE;
        this.vosoulState = VaziyatVosoul.TAEED_NASHODE;
    }

    public Credebit(Integer amount, String description, String rcptName,
                    String sarresidDate, String subsystemName, String type, String identifier, String shenaseCredebit, String shomareMoshtari) {
        // web service constructor
        this.createdDate = DateUtil.getCurrentDate();
        this.createdTime = DateUtil.getCurrentTime();
        this.amount = NumberFormat.getNumberInstance().format(amount);
        this.remainingAmount = NumberFormat.getNumberInstance().format(amount);
        setAmount(this.amount);
        setRemainingAmount(this.remainingAmount);
        this.description = description;
        this.rcptName = rcptName;
        this.sarresidDate = sarresidDate;
        this.subsystemName = subsystemName;
        this.identifier = identifier;
        this.shomareMoshtari = shomareMoshtari;
        this.shenaseCredebit = shenaseCredebit;
        this.status = Status.SANAD_NA_KHORDE;
        this.credebitType = CredebitType.valueOf(type);
        this.vosoulState = VaziyatVosoul.TAEED_NASHODE;
    }

    public Credebit(String format, Integer amount, String description, String rcptName, String sarresidDate, String subsystemName, String type, String identifier, String shenaseCredebit, String shomareMoshtari, String timeFish, String dateFish, String codeErja) {
        // web service constructor (sanadRegister)
        this.authorityId = codeErja;
        this.createdDate = DateUtil.getCurrentDate();
        this.createdTime = DateUtil.getCurrentTime();
        this.timeFish = timeFish;
        this.dateFish = dateFish;
//        this.amount = NumberFormat.getNumberInstance().format(amount);
        this.remainingAmount = NumberFormat.getNumberInstance().format(amount);
//        setAmount(this.amount);
        setRemainingAmount(this.remainingAmount);
        this.description = description;
        this.rcptName = rcptName;
        this.sarresidDate = sarresidDate;
        this.subsystemName = subsystemName;
        this.identifier = identifier;
        this.shomareMoshtari = shomareMoshtari;
        this.shenaseCredebit = shenaseCredebit;
        this.status = Status.SANAD_KHORDE;
        this.credebitType = CredebitType.valueOf(type);
        this.format = format;
        if (format.equals("241"))
            this.setBankName(Constant.CREDEBIT_BANK_MELAT_ESKAN);
        else if (format.equals("242"))
            this.setBankName(Constant.CREDEBIT_BANK_MELAT_VANAK);
        else if (format.equals("220"))
            this.setBankName("!");
        else if (format.equals("230"))
            this.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
        this.vosoulState = VaziyatVosoul.TAEED_NASHODE;

    }
    //b-h
    public Credebit(Credebit credebit){   //constructor for entegal bedehi
        this.setCreatedDate(credebit.getCreatedDate());
        this.setCreatedTime(credebit.getCreatedTime());
        this.setAmount_long(credebit.getAmount_long());
        this.setRemainingAmount_long(credebit.getRemainingAmount_long());
        this.setCredebitType(credebit.getCredebitType());
        this.setVagozari(credebit.getVagozari());
        this.setDaryafteCheck(credebit.getDaryafteCheck());
        this.setVosoulState(credebit.getVosoulState());
        this.setDescription(credebit.getDescription());
        this.setRcptName(credebit.getRcptName());
        this.setSarresidDate(credebit.getSarresidDate());
        this.setSubsystemName(credebit.getSubsystemName());
        this.setIdentifier(credebit.getIdentifier());
        this.setShomareMoshtari(credebit.getShomareMoshtari());
        this.setShenaseCredebit(credebit.getShenaseCredebit());
        this.setStatus(credebit.getStatus());
//        this.setVosoulDate(credebit.getVosoulDate());
        this.setNamayande(credebit.getNamayande());
        this.setVahedeSodor(credebit.getVahedeSodor());
       // this.setUniqueCode(credebit.getUniqueCode());
        this.setMohlatSarresid(credebit.getMohlatSarresid());
        this.setBazarYabSanam(credebit.getBazarYabSanam());
    }
    public Vagozari getVagozari() {
        return vagozari;
    }

    public void setVagozari(Vagozari vagozari) {
        this.vagozari = vagozari;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDateFish() {
        return dateFish;
    }

    public void setDateFish(String dateFish) {
        this.dateFish = dateFish;
    }

    public String getTimeFish() {
        return timeFish;
    }

    public void setTimeFish(String timeFish) {
        this.timeFish = timeFish;
    }

    public List<KarmozdGhest> getKarmozdGhestList() {
        return karmozdGhestList;
    }

    public List<KarmozdGhest> getKarmozdGhestListAdi()
    {
        List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
        for(KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getType().equals(KarmozdGhest.Type.ADI))
            {
                kgList.add(kg);
            }
        }
        return kgList;
    }

    public void setKarmozdGhestList(List<KarmozdGhest> karmozdGhestList) {
        this.karmozdGhestList = karmozdGhestList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAmount() {
        if (amount_long == null)
            return "";
        return NumberFormat.getInstance().format(amount_long);
    }

    public void setAmount(String amount) {
        if (amount == null || amount.equals(""))
            this.amount_long = null;
        else{
            this.amount_long = Long.parseLong(amount.replaceAll(",", "").trim());
        }
    }

    public Long getAmount_long() {
        return amount_long;
    }

    public void setAmount_long(Long amount_long) {
        this.amount_long = amount_long;
    }

    public long getPaidAmount()
    {
        long rtnVal=getAmount_long() - getRemainingAmount_long();
        return rtnVal;
    }

    public String getRemainingAmount() {
        if (remainingAmount_long == null)
            return "0";
        return NumberFormat.getInstance().format(remainingAmount_long);
    }

    public void setRemainingAmount(String remainingAmount) {
        if (remainingAmount == null || remainingAmount.equals(""))
            this.remainingAmount_long = 0L;
        else{
            //this.remainingAmount = remainingAmount;
            this.remainingAmount_long = Long.parseLong(remainingAmount.replaceAll(",", "").trim());
        }
    }

    public Long getRemainingAmount_long() {
        return remainingAmount_long;
    }

    public void setRemainingAmount_long(Long remainingAmount_long) {
        this.remainingAmount_long = remainingAmount_long;
    }

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
    }

    public String getShenaseCredebit() {
        return shenaseCredebit;
    }

    public void setShenaseCredebit(String shenaseCredebit) {
        this.shenaseCredebit = shenaseCredebit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusFarsi(){
        if (status.equals(Status.SANAD_KHORDE))
            return "سند خورده";
        if (status.equals(Status.SANAD_NA_KHORDE) || status.equals(Status.SANAD_KHORDE_FISH))
            return "سند نخورده";
//        if (status.equals(Status.SANAD_KHORDE_FISH))
//            return "سند خورده فیش";
        return "";
    }


    public String getStatusFarsiValidationBedehi(){
        if (status.equals(Status.SANAD_KHORDE))
            return "سند خورده - موقت";
        if (status.equals(Status.SANAD_NA_KHORDE))
            return "سند نخورده";
        return "";
    }

    public CredebitType getCredebitType() {
        return credebitType;
    }

    public void setCredebitType(CredebitType credebitType) {
        this.credebitType = credebitType;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Ghest getGhest() {
        return ghest;
    }

    public void setGhest(Ghest ghest) {
        this.ghest = ghest;
        if (ghest != null)
            this.sarresidDate = ghest.getSarresidDate();
    }

    public PardakhteTankhah getPardakhteTankhah() {
        return pardakhteTankhah;
    }

    public void setPardakhteTankhah(PardakhteTankhah pardakhteTankhah) {
        this.pardakhteTankhah = pardakhteTankhah;
    }

    public DaryafteFish getDaryafteFish() {
        return daryafteFish;
    }

    public void setDaryafteFish(DaryafteFish daryafteFish) {
        this.daryafteFish = daryafteFish;
    }

    public DaryafteCheck getDaryafteCheck() {
        return daryafteCheck;
    }

    public String getBankDaryafteCheck() {
        if (daryafteCheck != null)
            return daryafteCheck.getBankName();
        return "";
    }

    public void setDaryafteCheck(DaryafteCheck daryafteCheck) {
        this.daryafteCheck = daryafteCheck;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public List<KhateSanad> getKhateSanadsBedehi() {
        Collections.sort(khateSanadsBedehi);
        return khateSanadsBedehi;
    }

    public Set<KhateSanad> getKhateSanadsBedehiSet() {
        Set<KhateSanad> khS = new HashSet<KhateSanad>();
        Collections.sort(khateSanadsBedehi);
        khS.addAll(khateSanadsBedehi);
        return khS;
    }

    public Set<KhateSanad> getKhateSanadsEtebarSet() {
        Set<KhateSanad> khS = new HashSet<KhateSanad>();
        Collections.sort(khateSanadsEtebar);
        khS.addAll(khateSanadsEtebar);
        return khS;
    }

    public void setKhateSanadsBedehi(List<KhateSanad> khateSanadsBedehi) {
        this.khateSanadsBedehi = khateSanadsBedehi;
    }

    public List<KhateSanad> getKhateSanadsEtebar() {
        Collections.sort(khateSanadsEtebar);
        return khateSanadsEtebar;
    }

    public void setKhateSanadsEtebar(List<KhateSanad> khateSanadsEtebar) {
        this.khateSanadsEtebar = khateSanadsEtebar;
    }

    public String getVaziatVam() {
        try {
            if (credebitType.equals(Credebit.CredebitType.GHEST_VAM))
                return "دارد";
            else return "ندارد";
        } catch (Exception ex) {
            return "ندارد";
        }
    }

    public String getVaziatPardakht() {
        try {
            if (getRemainingAmount().equals("0"))
                return "پرداخت شده";
            else if (getRemainingAmount_long().equals(getAmount_long())) {
                return "پرداخت نشده";
            } else return "دارای مانده";
        } catch (Exception ex) {
            return "پرداخت نشده";
        }
    }

    public String getVaziatGhest() {
        try {
            if (status.equals(Status.SANAD_KHORDE))
                return "سند خورده";
            else if (Math.abs(DateUtil.getTimeDifferenceByDayCount(DateUtil.getCurrentDate(), createdDate)) < 30)
                return "معوق";
            else return "معلق";
        } catch (Exception ex) {
            return "معلق";
        }
    }

    public String getTarikhSanadMali() {
        try {
            if (khateSanadsBedehi != null && khateSanadsBedehi.size() > 0) {
                Collections.max(khateSanadsBedehi);
                return khateSanadsBedehi.get(0).getSanad().getCreatedDate();
            } else {
                return "";
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public String getCredebitTypeFarsi() {
        String rtnType = null;
        switch (getCredebitType()) {
            case GHEST:
                rtnType = "قسط";
                break;
            case GHEST_VAM:
                rtnType = "قسط وام";
                break;
            case PARDAKHTE_CHECK:
                rtnType = "پرداخت چک";
                break;
            case PARDAKHTE_TANKHAH:
                rtnType = "پرداخت تنخواه";
                break;
            case DARYAFTE_FISH:
                rtnType = "دریافت فیش";
                break;
            case DARYAFTE_FISH_NAMAYANDE:
                rtnType = "دریافت فیش (نمایندگی)";
                break;
            case PARDAKHT_SHENASEDAR:
                rtnType = "پرداخت شناسه دار";
                break;
            case DARYAFTE_ELECTRONICI:
                rtnType = "دریافت الکترونیکی";
                break;
            case AZ_MAHALLE_BAZKHARID:
                rtnType = "از محل بازخرید";
                break;
            case ETEBAR_VAM:
                rtnType = "اعتبار وام";
                break;
            case ETEBAR_BARDASHT:
                rtnType = "اعتبار برداشت";
                break;
            case DARYAFTE_CHECK:
                rtnType = "دریافت چک";
                break;
            case PARDAKHT_KARMOZD:
                rtnType = "پرداخت کارمزد";
                break;
            case MOSHAREKAT:
                rtnType = "مشارکت";
                break;
            case ETEBAR_KHESARAT:
                rtnType = "اعتبار خسارت";
                break;
            case ELHAGHIE_EZAFI:
                rtnType = "الحاقیه اضافی";
                break;
            case ELHAGHIE_BARGASHTI:
                rtnType = "الحاقیه برداشتی";
                break;
            case ELHAGHIE_BARGASHTI_ETEBAR:
                rtnType = "اعتبار الحاقیه برگشتی";
                break;
            case PISHPARDAKHT:
                rtnType = "پیش پرداخت";
                break;
            case VEHICLE_HAGHBIME:
                rtnType = "حق بیمه (اتومبیل)";
                break;
            case VEHICLE_HAGHBIME_BARGASHTI:
                rtnType = "حق بیمه برگشتی (اتومبیل)";
                break;
            case VEHICLE_KHESARAT:
                rtnType = "خسارت (اتومبیل)";
                break;
            case VEHICLE_DARYAFT_ELECTRONICI:
                rtnType = "دریافت الکترونیکی (اتومبیل)";
                break;
            case ACH:
                rtnType = "پرداخت پایا";
                break;
            case VEHICLE_HAGHBIME_ELECTRONICI:
                rtnType = "حق بیمه الکترونیکی (اتومبیل)";
                break;
            case ETEBAR_EBTAL:
                rtnType = "اعتبار ابطال";
                break;
            case CASH:
                rtnType = "پرداخت نقدی";
                break;
            case HESAB_FI_MA_BEYN:
                rtnType = "حساب فی مابین";
                break;
            case HAZINE_PEZESHKI:
                rtnType = "";
                break;
            case BARGASHT_KARMOZD:
                rtnType = "برگشت کارمزد";
                break;
            case HESAB_FI_MA_BEYN_BEDEHI:
                rtnType = "حساب فی مابین";
                break;
            case DARMAN_HAGHBIME:
                rtnType = "حق بیمه درمان";
                break;
            case AZ_MAHALLE_TABLIGHAT_BEDEHI:
                rtnType = "بدهی از محل تبلیغات";
                break;
            case POS:
                rtnType = "کارتخوان";
                break;
            case AZ_MAHALLE_TABLIGHAT:
                rtnType = "از محل تبلیغات";
                break;
            case AZ_MAHALLE_TABLIGHAT_MANUAL:
                rtnType = "از محل تبلیغات (دستی)";
                break;
            case KASR_AZ_HOGHUGH:
                rtnType = "کسر از حقوق";
                break;
            case TAGHIRAT_ESLAH_AGHSAT:
                rtnType = "اصلاح اقساط";
                break;
            case DARMAN_ELHAGHIYE_BARGASHTI:
                rtnType = "الحاقیه برگشتی درمان";
                break;
            case TASVIE_PISH_AZ_MOED_BEDEHI:
                rtnType = "بدهی تسویه پیش از موعد";
                break;
            case TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE:
                rtnType = "اعتبار تسویه پیش از موعد از محل اندوخته";
                break;
            case AZ_MAHALE_HAZINE_BIME_DARAYIHA:
                rtnType="از محل هزينه بيمه دارايي ها" ;
                break;
            case ETEBAR_DARMAN_KHANEVADE:
                rtnType="اعتبار درمان خانواده" ;
                break;
            case KASR_AZ_KARMOZD:
                rtnType = "كسر از كارمزد";
                break;
            default:
                rtnType = "تبدیل شده از سیماب";
                break;

        }
        return rtnType;
    }
    public static String getCredebitTypeFarsi(CredebitType credebitType) {
        String rtnType = null;
        switch (credebitType) {
            case GHEST:
                rtnType = "قسط";
                break;
            case GHEST_VAM:
                rtnType = "قسط وام";
                break;
            case PARDAKHTE_CHECK:
                rtnType = "پرداخت چک";
                break;
            case PARDAKHTE_TANKHAH:
                rtnType = "پرداخت تنخواه";
                break;
            case DARYAFTE_FISH:
                rtnType = "دریافت فیش";
                break;
            case DARYAFTE_FISH_NAMAYANDE:
                rtnType = "دریافت فیش (نمایندگی)";
                break;
            case PARDAKHT_SHENASEDAR:
                rtnType = "پرداخت شناسه دار";
                break;
            case DARYAFTE_ELECTRONICI:
                rtnType = "دریافت الکترونیکی";
                break;
            case AZ_MAHALLE_BAZKHARID:
                rtnType = "از محل بازخرید";
                break;
            case ETEBAR_VAM:
                rtnType = "اعتبار وام";
                break;
            case ETEBAR_BARDASHT:
                rtnType = "اعتبار برداشت";
                break;
            case DARYAFTE_CHECK:
                rtnType = "دریافت چک";
                break;
            case DARYAFTE_CHECK_NAMAYANDE:
                rtnType = "دریافت چک (نمایندگی)";
                break;
            case PARDAKHT_KARMOZD:
                rtnType = "پرداخت کارمزد";
                break;
            case MOSHAREKAT:
                rtnType = "مشارکت";
                break;
            case ETEBAR_KHESARAT:
                rtnType = "اعتبار خسارت";
                break;
            case ELHAGHIE_EZAFI:
                rtnType = "الحاقیه اضافی";
                break;
            case ELHAGHIE_BARGASHTI:
                rtnType = "الحاقیه برداشتی";
                break;
            case ELHAGHIE_BARGASHTI_ETEBAR:
                rtnType = "اعتبار الحاقیه برگشتی";
                break;
            case PISHPARDAKHT:
                rtnType = "پیش پرداخت";
                break;
            case VEHICLE_HAGHBIME:
                rtnType = "حق بیمه (اتومبیل)";
                break;
            case VEHICLE_HAGHBIME_BARGASHTI:
                rtnType = "حق بیمه برگشتی (اتومبیل)";
                break;
            case VEHICLE_KHESARAT:
                rtnType = "خسارت (اتومبیل)";
                break;
            case VEHICLE_DARYAFT_ELECTRONICI:
                rtnType = "دریافت الکترونیکی (اتومبیل)";
                break;
            case ACH:
                rtnType = "پرداخت پایا";
                break;
            case VEHICLE_HAGHBIME_ELECTRONICI:
                rtnType = "حق بیمه الکترونیکی (اتومبیل)";
                break;
            case ETEBAR_EBTAL:
                rtnType = "اعتبار ابطال";
                break;
            case CASH:
                rtnType = "پرداخت نقدی";
                break;
            case CASH_NAMAYANDE:
                rtnType = "پرداخت نقدی (نمایندگی)";
                break;
            case HESAB_FI_MA_BEYN:
                rtnType = "حساب فی مابین";
                break;
            case HAZINE_PEZESHKI:
                rtnType = "";
                break;
            case BARGASHT_KARMOZD:
                rtnType = "برگشت کارمزد";
                break;
            case HESAB_FI_MA_BEYN_BEDEHI:
                rtnType = "حساب فی مابین";
                break;
            case DARMAN_HAGHBIME:
                rtnType = "حق بیمه درمان";
                break;
            case AZ_MAHALLE_TABLIGHAT_BEDEHI:
                rtnType = "بدهی از محل تبلیغات";
                break;
            case POS:
                rtnType = "کارتخوان";
                break;
            case AZ_MAHALLE_TABLIGHAT:
                rtnType = "از محل تبلیغات";
                break;
            case AZ_MAHALLE_TABLIGHAT_MANUAL:
                rtnType = "از محل تبلیغات (دستی)";
                break;
            case KASR_AZ_HOGHUGH:
                rtnType = "کسر از حقوق";
                break;
            case TAGHIRAT_ESLAH_AGHSAT:
                rtnType = "اصلاح اقساط";
                break;
            case DARMAN_ELHAGHIYE_BARGASHTI:
                rtnType = "الحاقیه برگشتی درمان";
                break;
            case TASVIE_PISH_AZ_MOED_BEDEHI:
                rtnType = "بدهی تسویه پیش از موعد";
                break;
            case TASVIE_PISH_AZ_MOED_ETEBAR_AZ_ANDUKHTE:
                rtnType = "اعتبار تسویه پیش از موعد از محل اندوخته";
                break;
            case AZ_MAHALE_HAZINE_BIME_DARAYIHA:
                rtnType="از محل هزينه بيمه دارايي ها" ;
                break;
            case ETEBAR_DARMAN_KHANEVADE:
                rtnType="اعتبار درمان خانواده" ;
                break;
            case KASR_AZ_KARMOZD:
                rtnType="كسر از كارمزد";
                break;
            default:
                rtnType = "تبدیل شده از سیماب";
                break;
        }
        return rtnType;
    }

    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubsystemName() {
        return subsystemName;
    }

    public void setSubsystemName(String subsystemName) {
        this.subsystemName = subsystemName;
    }

    public String getSarresidDate() {
        return sarresidDate;
    }

    public void setSarresidDate(String sarresidDate) {
        this.sarresidDate = sarresidDate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPaidReceivedAmountFormat() {
        if (paidReceivedAmount != null)
            return NumberFormat.getInstance().format(paidReceivedAmount);
        return "";
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public boolean isBedehi() {
        for (CredebitType type : bedehiTypes) {
            if (type == getCredebitType()) {
                return true;
            }
        }
        return false;
    }

    public String getCredebitNoe(){
        if (isBedehi())
            return "بدهی";
        return "اعتبار";
    }

    public int compareTo(Object o) {
        Credebit oCredebit = (Credebit) o;
        String thisDateString = getCreatedDate();
        if (getSarresidDate() != null && !getSarresidDate().isEmpty()) {
            thisDateString = getSarresidDate();
        } else if (getDateFish() != null && !getDateFish().isEmpty()) {
            thisDateString = getDateFish();
        }
        String toDateString = oCredebit.getCreatedDate();
        if (oCredebit.getSarresidDate() != null && !oCredebit.getSarresidDate().isEmpty()) {
            toDateString = oCredebit.getSarresidDate();
        } else if (oCredebit.getDateFish() != null && !oCredebit.getDateFish().isEmpty()) {
            toDateString = oCredebit.getDateFish();
        }
        if(thisDateString.equals(toDateString) && getShenaseCredebit() != null && oCredebit.getShenaseCredebit() != null) {
            return getShenaseCredebit().compareTo(oCredebit.getShenaseCredebit());
        } else {
            return thisDateString.compareTo(toDateString);
        }
    }

    public String getRahgiriACH() {
        return rahgiriACH;
    }

    public void setRahgiriACH(String rahgiriACH) {
        this.rahgiriACH = rahgiriACH;
    }

    public KarmozdGhest getKarmozdGhestMovaghatSale1()
    {
        for(KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getSaleBimeei().equals(1) && kg.getType().equals(KarmozdGhest.Type.CODE_MOVAGHAT))
                return kg;
        }
        return null;
    }

    public KarmozdGhest getKarmozdGhestMovaghatSale2()
    {
        for(KarmozdGhest kg : karmozdGhestList)
        {
            if(kg.getSaleBimeei().equals(2) && kg.getType().equals(KarmozdGhest.Type.CODE_MOVAGHAT))
                return kg;
        }
        return null;
    }

    public KarmozdGhest getKarmozdGhestAdiSale1()
    {
        for (KarmozdGhest kg : karmozdGhestList)
        {
            if (kg.getSaleBimeei().equals(1) && kg.getType().equals(KarmozdGhest.Type.ADI))
                return kg;
        }
        return null;
    }

    public KarmozdGhest getKarmozdGhestAdiSale2()
    {
        for (KarmozdGhest kg : karmozdGhestList)
        {
            if (kg.getSaleBimeei().equals(2) && kg.getType().equals(KarmozdGhest.Type.ADI))
                return kg;
        }
        return null;
    }

    public CredebitTypeDesc getCredebitTypeDesc() {
        return credebitTypeDesc;
    }

    public void setCredebitTypeDesc(CredebitTypeDesc credebitTypeDesc) {
        this.credebitTypeDesc = credebitTypeDesc;
    }

    public String getTerminalNoPOS() {
        return terminalNoPOS;
    }

    public void setTerminalNoPOS(String terminalNoPOS) {
        this.terminalNoPOS = terminalNoPOS;
    }

    public String getTransSerialPOS() {
        return transSerialPOS;
    }

    public void setTransSerialPOS(String transSerialPOS) {
        this.transSerialPOS = transSerialPOS;
    }

    public String getMarjaNoPOS() {
        return marjaNoPOS;
    }

    public void setMarjaNoPOS(String marjaNoPOS) {
        this.marjaNoPOS = marjaNoPOS;
    }

    public String getCodePeygiriPOS() {
        return codePeygiriPOS;
    }

    public void setCodePeygiriPOS(String codePeygiriPOS) {
        this.codePeygiriPOS = codePeygiriPOS;
    }

    public String getShomareGharardad() {
        return shomareGharardad;
    }

    public void setShomareGharardad(String shomareGharardad) {
        this.shomareGharardad = shomareGharardad;
    }

    public VaziyatVosoul getVosoulState() {
        return vosoulState;
    }

    public String getVosoulStateFarsi() {
        if (vosoulState != null) {
            if (isBedehi()){
                VaziyatVosoul vaziyatVosulBedehi = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getVaziyatVosulBedehi(id);
                if (vaziyatVosulBedehi.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                    return "وصول شده";
            } else {
                if (vosoulState.equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                    return "وصول شده";
            }
        return "وصول نشده";
        }
        else
            return null;
    }

    public void setVosoulState(VaziyatVosoul vosoulState) {
        this.vosoulState = vosoulState;
    }

    public String getNoeArz(){
        return "ریال";
    }

    public String getNerkhArz(){
        return "1";
    }

    public String getDocIdentifier(){
        if (identifier != null && identifier.length() > 0){
            String[] split = identifier.split("/");
            if (split != null && split.length > 1)
                return split[1];
        }
        return "";
    }

    public Long getPaidReceivedAmount() {
        return paidReceivedAmount;
    }

    public void setPaidReceivedAmount(Long paidReceivedAmount) {
        this.paidReceivedAmount = paidReceivedAmount;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public String getNamayandeName() {
        if (namayande != null)
            return namayande.getName();
        return "";
    }

    public String getNamayandeKodeNamayandeKargozar() {
        if (namayande != null)
            return namayande.getKodeNamayandeKargozar();
        return "";
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Namayande getVahedeSodor() {
        return vahedeSodor;
    }

    public void setVahedeSodor(Namayande vahedeSodor) {
        this.vahedeSodor = vahedeSodor;
    }

    public List<BankInfo> getBankInfoList() {
        return bankInfoList;
    }

    public void setBankInfoList(List<BankInfo> bankInfoList) {
        this.bankInfoList = bankInfoList;
    }

    public Integer getFinalSarresidDate() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mohlateSarResid = prop.getProperty("WebService.NamayandehAuthorized.MohlateSarresid");
        String finalSarresidDatetemp = DateUtil.addDaysWithTatilat(getSarresidDateWithMohlatSarresid(),Integer.parseInt(mohlateSarResid));
        return DateUtil.getTimeDifferenceByDayCount(finalSarresidDatetemp,DateUtil.getCurrentDate());
    }

    //getter for print sanad
    public String getCreatedDateSarresidDate(){
        if (this.credebitType != null && this.credebitType.equals(CredebitType.DARYAFTE_CHECK))
            if (this.getDaryafteCheck() != null)
                return this.getDaryafteCheck().getTarikhSarresid();
        if (this.credebitType != null && (this.credebitType.equals(CredebitType.PISHPARDAKHT) || this.credebitType.equals(CredebitType.DARYAFTE_FISH)))
            if (this.getDaryafteFish() != null)
                return this.getDaryafteFish().getTarikh();
        return getCreatedDate();
    }

    public String getShomareCheckShomareFish(){
        if (this.credebitType != null && this.credebitType.equals(CredebitType.DARYAFTE_CHECK))
            if (this.getDaryafteCheck() != null)
                return this.getDaryafteCheck().getSerial();
        else if (this.credebitType != null && this.credebitType.equals(CredebitType.DARYAFTE_FISH)){
            if (this.getDaryafteFish() != null)
                return this.getDaryafteFish().getShomareFish();
        }
        return "";
    }

    public String getDaryafteCheckStatus() {
        if (daryafteCheck != null)     {
            if (daryafteCheck.getStatus().equals(DaryafteCheck.Status.MOSTARAD)){
                return "مسترد";
            }
            else if (daryafteCheck.getStatus().equals(DaryafteCheck.Status.NAZD_SANDOGH)){
                return "نزد صندوق";
            }
            else if (daryafteCheck.getStatus().equals(DaryafteCheck.Status.VAGOZAR_SHODE)){
                return "واگذار شده ";
            }
            else if (daryafteCheck.getStatus().equals(DaryafteCheck.Status.VOSUL)){
                return "وصول";
            }
        }
        return "";
    }

    public String getVaziyateEtebarDesc(){
        if (getBankInfoList() != null && getBankInfoList().size() > 0){
            for (BankInfo bankInfo : getBankInfoList()){
                if (bankInfo.getStatus() != null){
                    if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.TEKRARI))
                        return "تکراری";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI))
                        return "وصول تکراری";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD))
                        return "مبلغ مازاد";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR))
                        return "مبلغ کمتر";
                    //else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI))
                        //return "مبلغ مساوی";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.SANAD_KHORDE))
                        return "سند خورده";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.NO_GHEST))
                        return "قسط پیدا نشد";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.IJAD_CREDEBIT))
                        return "ایجاد موجودیت";
                    else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ))
                        return "وصول تکراری غیر مجاز";
                }
            }
        }
        return "";
    }

    public NahveVosoul getNahveVosoul() {
        return nahveVosoul;
    }

    public void setNahveVosoul(NahveVosoul nahveVosoul) {
        this.nahveVosoul = nahveVosoul;
    }

    public Integer getMohlatSarresid() {
        return mohlatSarresid;
    }

    public void setMohlatSarresid(Integer mohlatSarresid) {
        this.mohlatSarresid = mohlatSarresid;
    }

    public String getSarresidDateWithMohlatSarresid() {
        return sarresidDateWithMohlatSarresid;
    }

    public void setSarresidDateWithMohlatSarresid(String sarresidDateWithMohlatSarresid) {
        this.sarresidDateWithMohlatSarresid = sarresidDateWithMohlatSarresid;
    }
    public String getVosoulDate() {
        if(Credebit.VaziyatVosoul.TAEED_VOSOUL.equals(vosoulState))  {

        }
        return vosoulDate;
    }

    public void setVosoulDate(String vosoulDate) {
        this.vosoulDate = vosoulDate;
    }

    public ACHStatus getAchStatus() {
        return achStatus;
    }

    public void setAchStatus(ACHStatus achStatus) {
        this.achStatus = achStatus;
    }
    public String getSubsystemNameFa()
    {
        return this.subsystemName;
    }

    public BazarYabSanam getBazarYabSanam() {
        return bazarYabSanam;
    }

    public void setBazarYabSanam(BazarYabSanam bazarYabSanam) {
        this.bazarYabSanam = bazarYabSanam;
    }

    public String getDevFlag() {
        return devFlag;
    }

    public void setDevFlag(String devFlag) {
        this.devFlag = devFlag;
    }
    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }

    public Credebit getDaftarParsian_credebit() {
        return daftarParsian_credebit;
    }

    public void setDaftarParsian_credebit(Credebit daftarParsian_credebit) {
        this.daftarParsian_credebit = daftarParsian_credebit;
    }

    public Integer getVaziatEnteghalBedehi() {
        return vaziatEnteghalBedehi;
    }

    public void setVaziatEnteghalBedehi(Integer vaziatEnteghalBedehi) {
        this.vaziatEnteghalBedehi = vaziatEnteghalBedehi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
