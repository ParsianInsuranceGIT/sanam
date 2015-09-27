package com.bitarts.parsian.model;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.karmozd.KarmozdNamayande;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 8/4/11
 * Time: 11:34 AM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_namayande")
public class Namayande implements Serializable, Comparable<Namayande> {

    public NayamandeType getNamayandeType() {
        return namayandeType;
    }

    public void setNamayandeType(NayamandeType namayandeType) {
        this.namayandeType = namayandeType;
    }

    public Long getEtebarMojazVosolNashodAmountCheck() {
        return etebarMojazVosolNashodAmountCheck;
    }

    public void setEtebarMojazVosolNashodAmountCheck(Long etebarMojazVosolNashodAmountCheck) {
        this.etebarMojazVosolNashodAmountCheck = etebarMojazVosolNashodAmountCheck;
    }

    public static enum NayamandeType {
        FORUSHANDE,NAMAYANDE_HOGHUGHI, NAMAYANDE_HAGHIGHI, KARGOZAR_HAGHIGHI, KARGOZAR_HOGHUGHI, MOJTAMA, SHOBE, BAJE_NAMAYANDE_HOGHUGHI, ICD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "kodeNamayandeKargozar")
    private String kodeNamayandeKargozar;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Namayande vahedSodur;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Namayande senior;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Namayande sarparast;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Namayande parent; // baraye namayandehayee mesle iran khodro
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shahr_id")
    private City shahr;
    @Transient
    private Double rankValue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ostan_id")
    private City ostan;
    @OneToMany(mappedBy = "namayande")
    private List<Pishnehad> pishnehadList;

    @OneToMany(mappedBy = "namayande")
    private List<KarmozdNamayande> karmozdNamayandeList;

    @Column(name = "address")
    private String address;
//    @Column(name = "type")
//    private String type;
    @Column(name = "type_enum")
    @Enumerated(EnumType.STRING)
    private NayamandeType namayandeType;
    @Column(name = "namayande_telephone")
    private String telephone;
    @OneToMany(mappedBy = "namayandegi", fetch = FetchType.LAZY)
    private Set<User> users;
    @OneToMany(mappedBy = "namayande", fetch = FetchType.LAZY)
    private List<Gharardad> gharardadList;
    @Column(name = "expDate")
    private String expDate;
    @Column(name = "issuance_code")
    private String issuanceCode;

    @Column(name = "is_arshad")
    private Boolean arshad;

    @OneToMany(mappedBy = "subset", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<SeniorSubset> subsetList;

    @OneToMany(mappedBy = "senior", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<SeniorSubset> seniorList;

    @Column(name = "mohlate_sanadzadan")
    private Integer mohlateSanadzadan;

    @Column(name = "etebarMojazVosolNashodeAmount")
    private Long etebarMojazVosolNashodAmount;

    @Column(name = "check_VNashode_Amount")
    private Long etebarMojazVosolNashodAmountCheck;

    @Column(name = "pishnehad_create_access")
    private Boolean pishnehadCrtAccess;

    @Column(name = "code_canceled")
    private Boolean codeCanceled;

    @OneToMany(mappedBy = "vahedSodor", fetch = FetchType.LAZY)
    private List<BazarYabSanam> bazarYabSanamList;

    @OneToMany(mappedBy = "vahedSodor", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccount;

    //b-h
    @Column(name = "tedad_daftar")
    private Integer tedadDaftar;

    public Integer getTedadDaftar() {
        return tedadDaftar;
    }

    public void setTedadDaftar(Integer tedadDaftar) {
        this.tedadDaftar = tedadDaftar;
    }

    public List<BankAccount> getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(List<BankAccount> bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public List<BazarYabSanam> getBazarYabSanamList()
    {
        return bazarYabSanamList;
    }

    public void setBazarYabSanamList(List<BazarYabSanam> bazarYabSanamList)
    {
        this.bazarYabSanamList = bazarYabSanamList;
    }

    public Boolean getCodeCanceled()
    {
        if (codeCanceled == null)
            return false;
        return codeCanceled;
    }

    public void setCodeCanceled(Boolean codeCanceled)
    {
        if (codeCanceled == null)
            this.codeCanceled = false;
        this.codeCanceled = codeCanceled;
    }

    public Boolean getPishnehadCrtAccess()
    {
        if(pishnehadCrtAccess==null)
            return false;
        return pishnehadCrtAccess;
    }

    public void setPishnehadCrtAccess(Boolean pishnehadCrtAccess)
    {
        if(pishnehadCrtAccess==null)
            this.pishnehadCrtAccess = false;
        this.pishnehadCrtAccess = pishnehadCrtAccess;
    }

    public List<KarmozdNamayande> getKarmozdNamayandeList()
    {
        return karmozdNamayandeList;
    }

    public void setKarmozdNamayandeList(List<KarmozdNamayande> karmozdNamayandeList)
    {
        this.karmozdNamayandeList = karmozdNamayandeList;
    }

    public List<SeniorSubset> getSubsetList()
    {
        return subsetList;
    }

    public void setSubsetList(List<SeniorSubset> subsetList)
    {
        this.subsetList = subsetList;
    }

    public List<SeniorSubset> getSeniorList()
    {
        return seniorList;
    }

    public void setSeniorList(List<SeniorSubset> seniorList)
    {
        this.seniorList = seniorList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getArshad()
    {
        return arshad;
    }

    public void setArshad(Boolean arshad)
    {
        this.arshad = arshad;
    }

    public String getKodeNamayandeKargozar() {
        return kodeNamayandeKargozar;
    }

    public void setKodeNamayandeKargozar(String kodeNamayandeKargozar) {
        this.kodeNamayandeKargozar = kodeNamayandeKargozar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return getNamayandeTypeFarsi();
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getTelephone() {

        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public City getShahr() {
        return shahr;
    }

    public void setShahr(City shahr) {
        this.shahr = shahr;
    }

    public City getOstan() {
        return ostan;
    }

    public void setOstan(City ostan) {
        this.ostan = ostan;
    }

    public Namayande getVahedSodur() {
        return vahedSodur;
    }

    public void setVahedSodur(Namayande vahedSodur) {
        this.vahedSodur = vahedSodur;
    }


    public Namayande getSenior()
    {
        return senior;
    }

    public void setSenior(Namayande senior)
    {
        this.senior = senior;
    }

    public Double getRankValue() {
        return rankValue;
    }

    public void setRankValue(Double rankValue) {
        this.rankValue = rankValue;
    }

    public int compareTo(Namayande o) {
        return o.rankValue.compareTo(rankValue);
    }

    public String getIssuanceCode() {
        return issuanceCode;
    }

    public void setIssuanceCode(String issuanceCode) {
        this.issuanceCode = issuanceCode;
    }

    public Namayande getSarparast() {
        return sarparast;
    }

    public void setSarparast(Namayande sarparast) {
        this.sarparast = sarparast;
    }

    public Namayande getParent() {
        return parent;
    }

    public void setParent(Namayande parent) {
        this.parent = parent;
    }

    public enum Indicator {
        TEDAD_BIMENAME, SARANE_BIMENAME, TEDAD_BIMENAME_FASKH, SARANE_BIMENAME_FASKH, HAGH_BIME_SAL1, SARANE_BIME_SAL1,
        KOL_HAGH_BIME, SARANE_HAGH_BIME, HAGH_BIME_PARDAKHT, SARANE_BIME_PARDAKHT, HAGH_BIME_SANAD, SARANE_BIME_SANAD,
        HAGH_BIME_PARDAKHT_SAL1, SARANE_BIME_PARDAKHT_SAL1, HAGH_BIME_SANAD_SAL1, SARANE_BIME_SANAD_SAL1, KOL_BIME_PARDAKHT,
        SARANE_KOL_BIME_PARDAKHT, KOL_BIME_SANAD, SARANE_KOL_BIME_SANAD, BARGASHTI_SAL1, SARANE_BARGASHTI_SAL1, KOL_BIME_BARGASHTI,
        SARANE_KOL_BIME_BARGASHTI, HAGH_BIME_MOAVAGH_SAL1, SARANE_HAGH_BIME_MOAVAGH_SAL1, HAGH_BIME_MOAVAGH, SARANE_HAGH_BIME_MOAVAGH,
        BARGASHTI_BE_SADERE, MOAVAGH_BE_SADERE, SARMAYE_FOT, ARZESHBAZKHARIDI
    }

    public Integer calcTedadPishnehadat(Integer stateId, String aztarikh, String tatarikh) {
        int count = 0;
        for (Pishnehad p : pishnehadList) {
            if (stateId == null || stateId == -1) {
                if (DateUtil.between(p.getCreatedDate(), aztarikh, tatarikh))
                    count++;
            } else {
                if (p.getState().getId() == stateId && DateUtil.between(p.getCreatedDate(), aztarikh, tatarikh))
                    count++;
            }
        }
        return count;
    }

    public Double calcTedadBimename(boolean faskh, String aztarikh, String tatarikh) {
        double count = 0;
        for (Pishnehad p : pishnehadList) {
            if (faskh) {
                if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && p.getBimename().getState().getId().equals(Constant.BIMENAME_FASKH) && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh))
                    count++;
            } else {
                if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && !p.getBimename().getState().getId().equals(Constant.BIMENAME_FASKH) && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh))
                    count++;
            }
        }
        return count;
    }

    public void calcRank(Indicator indicator, Integer tedadNamayande, String azTarikh, String taTarikh) {
        switch (indicator) {
            case TEDAD_BIMENAME:
                rankValue = calcTedadBimename(false, azTarikh, taTarikh);
                break;
            case SARANE_BIMENAME:
                rankValue = calcTedadBimename(false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case TEDAD_BIMENAME_FASKH:
                rankValue = calcTedadBimename(true, azTarikh, taTarikh);
                break;
            case SARANE_BIMENAME_FASKH:
                rankValue = calcTedadBimename(true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_SAL1:
                rankValue = calcSadereHaghBime(true, azTarikh, taTarikh);
                break;
            case SARANE_BIME_SAL1:
                rankValue = calcSadereHaghBime(true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case KOL_HAGH_BIME:
                rankValue = calcSadereHaghBime(false, azTarikh, taTarikh);
                break;
            case SARANE_HAGH_BIME:
                rankValue = calcSadereHaghBime(false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_PARDAKHT:
                rankValue = calcHaghBime(azTarikh, taTarikh, true, true, azTarikh, taTarikh);
                break;
            case SARANE_BIME_PARDAKHT:
                rankValue = calcHaghBime(azTarikh, taTarikh, true, true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_SANAD:
                rankValue = calcHaghBime(azTarikh, taTarikh, false, true, azTarikh, taTarikh);
                break;
            case SARANE_BIME_SANAD:
                rankValue = calcHaghBime(azTarikh, taTarikh, false, true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_PARDAKHT_SAL1:
                rankValue = calcHaghBime(azTarikh, taTarikh, true, false, azTarikh, taTarikh);
                break;
            case SARANE_BIME_PARDAKHT_SAL1:
                rankValue = calcHaghBime(azTarikh, taTarikh, true, false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_SANAD_SAL1:
                rankValue = calcHaghBime(azTarikh, taTarikh, false, false, azTarikh, taTarikh);
                break;
            case SARANE_BIME_SANAD_SAL1:
                rankValue = calcHaghBime(azTarikh, taTarikh, false, false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case KOL_BIME_PARDAKHT:
                rankValue = calcHaghBime("", DateUtil.getCurrentDate(), true, true, azTarikh, taTarikh);
                break;
            case SARANE_KOL_BIME_PARDAKHT:
                rankValue = calcHaghBime("", DateUtil.getCurrentDate(), true, true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case KOL_BIME_SANAD:
                rankValue = calcHaghBime("", DateUtil.getCurrentDate(), false, true, azTarikh, taTarikh);
                break;
            case SARANE_KOL_BIME_SANAD:
                rankValue = calcHaghBime("", DateUtil.getCurrentDate(), false, true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case BARGASHTI_SAL1:
                rankValue = calcBargashti(false, azTarikh, taTarikh);
                break;
            case SARANE_BARGASHTI_SAL1:
                rankValue = calcBargashti(false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case KOL_BIME_BARGASHTI:
                rankValue = calcBargashti(true, azTarikh, taTarikh);
                break;
            case SARANE_KOL_BIME_BARGASHTI:
                rankValue = calcBargashti(true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_MOAVAGH_SAL1:
                rankValue = calcBimeMoavagh(false, azTarikh, taTarikh);
                break;
            case SARANE_HAGH_BIME_MOAVAGH_SAL1:
                rankValue = calcBimeMoavagh(false, azTarikh, taTarikh) / tedadNamayande;
                break;
            case HAGH_BIME_MOAVAGH:
                rankValue = calcBimeMoavagh(true, azTarikh, taTarikh);
                break;
            case SARANE_HAGH_BIME_MOAVAGH:
                rankValue = calcBimeMoavagh(true, azTarikh, taTarikh) / tedadNamayande;
                break;
            case BARGASHTI_BE_SADERE:
                double tmp = calcSadereHaghBime(true, azTarikh, taTarikh);
                rankValue = tmp != 0.0 ? calcBargashti(true, azTarikh, taTarikh) / tmp : 0.0;
                break;
            case MOAVAGH_BE_SADERE:
                tmp = calcSadereHaghBime(true, azTarikh, taTarikh);
                rankValue = tmp != 0.0 ? calcBimeMoavagh(true, azTarikh, taTarikh) / tmp : 0.0;
                break;
            case SARMAYE_FOT:
                rankValue = calcSarmayeFot(azTarikh, taTarikh);
                break;
            case ARZESHBAZKHARIDI:
                rankValue = calcArzeshBazkharidi(azTarikh, taTarikh);
                break;
            default:
                rankValue = 0.0;
                break;
        }
    }

    private Double calcBargashti(boolean kolSal1, String aztarikh, String tatarikh) {
        double tmp = 0;
        for (Pishnehad p : pishnehadList) {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh)) {

                for (GhestBandi gb : p.getBimename().getGhestBandiList()) {
                    if (kolSal1) {
                        for (Ghest g : gb.getGhestList()) {
                            try {
                                for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi())
                                    if (ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI)) {
                                        tmp += Double.valueOf(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                                        break;
                                    }

                            } catch (Exception ignore) {
                            }
                        }

                    } else if (gb.getSaleBimei() != null && gb.getSaleBimei().equals("0")) {
                        for (Ghest g : gb.getGhestList()) {
                            try {
                                for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehi())
                                    if (ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI)) {
                                        tmp += Double.valueOf(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                                        break;
                                    }

                            } catch (Exception ignore) {
                            }
                        }
                    }

                }
            }
        }
        return tmp;
    }

    private Double calcArzeshBazkharidi(String aztarikh, String tatarikh) {
        double tmp = 0;
        for (Pishnehad p : pishnehadList) {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh)) {
                try {
                    tmp += Double.parseDouble(p.getBimename().calcArzeshBazkharidi().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return tmp;
    }

    private Double calcSarmayeFot(String aztarikh, String tatarikh) {
        double tmp = 0;
        for (Pishnehad p : pishnehadList) {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh)) {
                try {
                    tmp += Double.parseDouble(p.getEstelam().getSarmaye_paye_fot().replace(",", "").trim());
                } catch (Exception ignore) {
                }
            }
        }
        return tmp;
    }

    private Double calcBimeMoavagh(boolean kolSal1, String aztarikh, String tatarikh) {
        double tmp = 0;
        for (Pishnehad p : pishnehadList) {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh)) {
                for (GhestBandi gb : p.getBimename().getGhestBandiList()) {
                    try {
                        if (kolSal1) {
                            for (Ghest g : gb.getGhestList()) {
                                if (g.isMoavagh()) {
                                    tmp += Double.parseDouble(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                                }
                            }
                        } else if (gb.getSaleBimei().equals("0")) {
                            for (Ghest g : gb.getGhestList()) {
                                if (g.isMoavagh()) {
                                    tmp += Double.parseDouble(g.getCredebit().getRemainingAmount().replace(",", "").trim());
                                }
                            }
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        return tmp;
    }

    private Double calcHaghBime(String azTarikh, String taTarikh, boolean pardakhtSanad, boolean kolSal1, String aztarikh, String tatarikh) {
        double tmp = 0.0;
        for (Pishnehad p : pishnehadList) {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh)) {
                for (GhestBandi gb : p.getBimename().getGhestBandiList()) {
                    try {
                        if (!kolSal1 && gb.getSaleBimei().equals("0")) {
                            for (Ghest g : gb.getGhestList()) {
                                if (pardakhtSanad) {
                                    if (DateUtil.between(g.getTarikhElamBeMali(), azTarikh, taTarikh) && DateUtil.between(g.getTarikhPardakht(), azTarikh, taTarikh)) {
                                        tmp += Double.parseDouble(g.getCredebit().getAmount().replace(",", "").trim());
                                    }
                                } else {
                                    if (DateUtil.between(g.getTarikhElamBeMali(), azTarikh, taTarikh) && DateUtil.between(g.getTarikhSanad(), azTarikh, taTarikh)) {
                                        tmp += Double.parseDouble(g.getCredebit().getAmount().replace(",", "").trim());
                                    }
                                }

                            }
                        } else {
                            for (Ghest g : gb.getGhestList()) {

                                if (pardakhtSanad) {
                                    if (DateUtil.between(g.getTarikhElamBeMali(), azTarikh, taTarikh) && DateUtil.between(g.getTarikhPardakht(), azTarikh, taTarikh)) {
                                        tmp += Double.parseDouble(g.getCredebit().getAmount().replace(",", "").trim());
                                    }
                                } else {
                                    if (DateUtil.between(g.getTarikhElamBeMali(), azTarikh, taTarikh) && DateUtil.between(g.getTarikhSanad(), azTarikh, taTarikh)) {
                                        tmp += Double.parseDouble(g.getCredebit().getAmount().replace(",", "").trim());
                                    }
                                }
                            }
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        return tmp;
    }

    private Double calcSadereHaghBime(boolean kolSal1, String aztarikh, String tatarikh) {
        double tmp = 0;
        for (Pishnehad p : pishnehadList)
        {
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes") && DateUtil.between(p.getBimename().getTarikhSodour(), aztarikh, tatarikh))
            {
//                try
//                {
                    for (GhestBandi gb : p.getBimename().getGhestBandiList())
                    {
                        for(Ghest ghest : gb.getGhestList())
                        {
                            tmp+=Double.parseDouble(ghest.getCredebit().getAmount().replace(",","").trim());
                        }
                    }
//                    MohasebateTeory mohasebateTeory = new MohasebateTeory();
//                    List<TaghsitReport> list = mohasebateTeory.taghsitReport(p);
//                    if (kolSal1) {
//                        for (TaghsitReport t : list)
//                            tmp += t.getHaghBimePardaakhtiSaal();
//                    } else {
//                        tmp += list.get(0).getHaghBimePardaakhtiSaal();
//                    }
//                }
//                catch (Exception ignore) {}
            }
        }
        if(kolSal1) tmp=0;
        return tmp;
    }


    public String getNamayandeTypeFarsi()
    {
        switch (namayandeType)
        {
            case NAMAYANDE_HAGHIGHI:
                return "نماینده حقیقی";
            case NAMAYANDE_HOGHUGHI:
                return "نماینده حقوقی";
            case KARGOZAR_HOGHUGHI:
                return "کارگزار حقوقی";
            case KARGOZAR_HAGHIGHI:
                return "کارگزار حقیقی";
            case MOJTAMA:
                return "مجتمع بیمه ای";
            case FORUSHANDE:
                return "نماینده فروش";
            case BAJE_NAMAYANDE_HOGHUGHI:
                return "باجه نماینده حقوقی";
            case ICD:
                return "ICD";
            case SHOBE:
                return "شعبه";
            default:
                return null;
        }
    }

    public static String IndicatorFarsi(Indicator indicator) {
        switch (indicator) {
            case TEDAD_BIMENAME:
                return "تعداد بیمه نامه های صادره";
            case SARANE_BIMENAME:
                return "سرانه بیمه نامه های صادره";
            case TEDAD_BIMENAME_FASKH:
                return "تعداد بیمه نامه های فسخ/ابطال";
            case SARANE_BIMENAME_FASKH:
                return "سرانه تعداد بیمه نامه های فسخ/ابطال";
            case HAGH_BIME_SAL1:
                return "حق بیمه صادره سال اول";
            case SARANE_BIME_SAL1:
                return "سرانه حق بیمه صادره سال اول";
            case KOL_HAGH_BIME:
                return "کل حق بیمه صادره";
            case SARANE_HAGH_BIME:
                return "سرانه کل حق بیمه صادره";
            case HAGH_BIME_PARDAKHT:
                return "حق بیمه وصولی از صادره ماه بر اساس تاریخ پرداخت";
            case SARANE_BIME_PARDAKHT:
                return "سرانه حق بیمه وصولی از صادره ماه بر اساس تاریخ پرداخت";
            case HAGH_BIME_SANAD:
                return "حق بیمه وصولی از صادره ماه بر اساس تاریخ سند";
            case SARANE_BIME_SANAD:
                return "سرانه حق بیمه وصولی از صادره ماه بر اساس تاریخ سند";
            case HAGH_BIME_PARDAKHT_SAL1:
                return "حق بیمه وصولی سال اول بر اساس تاریخ پرداخت";
            case SARANE_BIME_PARDAKHT_SAL1:
                return "سرانه حق بیمه وصولی سال اول بر اساس تاریخ پرداخت";
            case HAGH_BIME_SANAD_SAL1:
                return "حق بیمه وصولی سال اول بر اساس تاریخ سند";
            case SARANE_BIME_SANAD_SAL1:
                return "سرانه حق بیمه وصولی سال اول بر اساس تاریخ سند";
            case KOL_BIME_PARDAKHT:
                return "کل حق بیمه وصولی بر اساس تاریخ پرداخت";
            case SARANE_KOL_BIME_PARDAKHT:
                return "سرانه کل حق بیمه وصولی بر اساس تاریخ پرداخت";
            case KOL_BIME_SANAD:
                return "کل حق بیمه وصولی بر اساس تاریخ سند";
            case SARANE_KOL_BIME_SANAD:
                return "سرانه کل حق بیمه وصولی بر اساس تاریخ سند";
            case BARGASHTI_SAL1:
                return "حق بیمه برگشتی سال اول";
            case SARANE_BARGASHTI_SAL1:
                return "سرانه حق بیمه برگشتی سال اول";
            case KOL_BIME_BARGASHTI:
                return "کل حق بیمه برگشتی";
            case SARANE_KOL_BIME_BARGASHTI:
                return "سرانه کل حق بیمه برگشتی";
            case HAGH_BIME_MOAVAGH_SAL1:
                return "حق بیمه معوق سال اول";
            case SARANE_HAGH_BIME_MOAVAGH_SAL1:
                return "سرانه حق بیمه معوق سال اول";
            case HAGH_BIME_MOAVAGH:
                return "کل حق بیمه معوق";
            case SARANE_HAGH_BIME_MOAVAGH:
                return "سرانه کل حق بیمه معوق";
            case BARGASHTI_BE_SADERE:
                return "نسبت حق بیمه برگشتی به حق بیمه صادره";
            case MOAVAGH_BE_SADERE:
                return "نسبت حق بیمه معوق به صادره";
            case SARMAYE_FOT:
                return "سرمایه فوت";
            case ARZESHBAZKHARIDI:
                return "ارزش بازخریدی";
            default:
                return "";
        }
    }

    public List<Pishnehad> getPishnehadList() {
        return pishnehadList;
    }

    public void setPishnehadList(List<Pishnehad> pishnehadList) {
        this.pishnehadList = pishnehadList;
    }

    public List<Gharardad> getGharardadList() {
        return gharardadList;
    }

    public void setGharardadList(List<Gharardad> gharardadList) {
        this.gharardadList = gharardadList;
    }

    public String getName_kod() {
        return name + " - " + kodeNamayandeKargozar;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Integer getMohlateSanadzadan() {
        return mohlateSanadzadan;
    }

    public void setMohlateSanadzadan(Integer mohlateSanadzadan) {
        this.mohlateSanadzadan = mohlateSanadzadan;
    }

    public Long getEtebarMojazVosolNashodAmount() {
        return etebarMojazVosolNashodAmount;
    }

    public void setEtebarMojazVosolNashodAmount(Long etebarMojazVosolNashodAmount) {
        this.etebarMojazVosolNashodAmount = etebarMojazVosolNashodAmount;
    }

    public User getNamayandegiUser()
    {
        for (User u : users)
        {
            if(u.getUsername().endsWith("04"))
                return u;
        }
        return null;
    }
}
