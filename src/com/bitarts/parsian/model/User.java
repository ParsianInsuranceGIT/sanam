package com.bitarts.parsian.model;

import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.asnadeSodor.LogPrintDaftarche;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.BimeGozar;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.xsom.impl.scd.Iterators;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.*;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:42 PM
 */
@Entity
@Table(name = "tbl_users")
public class User implements Serializable, Comparable<User> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;

    // shart karshenasi
    @Column(name = "sarmaye_fot_max_for_karshenasi")
    private Long sarmayeFotMaxForKarshenasi;

    // shart sodor
    @Column(name = "sarmaye_fot_max_sodor")
    private Long sarmayeFotMaxForSodor;
    @Column(name = "sarmaye_hadese_max_sodor")
    private Long sarmayeHadeseMaxForSodor;
    @Column(name = "tabaghe_hadese_max_sodor")
    private Short tabagheKhatarHadeseMaxForSodor;
    @Column(name = "tabaghe_naghs_max_sodor")
    private Short tabagheKhatarNaghsMaxForSodor;
    @Column(name = "ezafe_nerkh_max_sodor")
    private Short ezafeNerkhMaxForSodor;

    // sharayet nezarat (karshenas nazer)
    @Column(name = "n_kn_sarmaye_fot")
    private Long nezaratKarshenasNazerSarmayeFot;
    @Column(name = "n_kn_sarmaye_hadese")
    private Long nezaratKarshenasNazerSarmayeHadese;
    @Column(name = "n_kn_tabaghe_hadese")
    private Short nezaratKarshenasNazerTabagheKhatarHadese;
    @Column(name = "n_kn_tabaghe_naghs")
    private Short nezaratKarshenasNazerTabagheKhatarNaghs;
    @Column(name = "n_kn_ezafe_nerkh")
    private Short nezaratKarshenasNazerEzafeNerkh;
    // sharayet nezarat (karshenas masool)
    @Column(name = "n_km_sarmaye_fot")
    private Long nezaratKarshenasMasoolSarmayeFot;
    @Column(name = "n_km_sarmaye_hadese")
    private Long nezaratKarshenasMasoolSarmayeHadese;
    @Column(name = "n_km_tabaghe_hadese")
    private Short nezaratKarshenasMasoolTabagheKhatarHadese;
    @Column(name = "n_km_tabaghe_naghs")
    private Short nezaratKarshenasMasoolTabagheKhatarNaghs;
    @Column(name = "n_km_ezafe_nerkh")
    private Short nezaratKarshenasMasoolEzafeNerkh;
    // sharayet nezarat (raees edare)
    @Column(name = "n_re_sarmaye_fot")
    private Long nezaratRaeesEdareSarmayeFot;
    @Column(name = "n_re_sarmaye_hadese")
    private Long nezaratRaeesEdareSarmayeHadese;
    @Column(name = "n_re_tabaghe_hadese")
    private Short nezaratRaeesEdareTabagheKhatarHadese;
    @Column(name = "n_re_tabaghe_naghs")
    private Short nezaratRaeesEdareTabagheKhatarNaghs;
    @Column(name = "n_re_ezafe_nerkh")
    private Short nezaratRaeesEdareEzafeNerkh;
    // nezarat enabled
    @Column(name = "n_enabled")
    private boolean nezaratEnabled;

    @Column(name = "semat_sazmani")
    private String sematSazmani;
    @Column(name = "faal")
    private boolean faal;
    @JoinColumn(name = "vahed_sodoor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Namayande mojtamaSodoor;

    @Column(name = "personal_code")
    private String personalCode;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "code_ramz")
    private String codeRamz;

    @Column(name = "code_ramz_tarikh")
    private String codeRamzTarikh;

    @OneToMany(mappedBy = "bazarYab", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Pishnehad> createdPisnehads;
  //b-h
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    @Fetch(FetchMode.SUBSELECT)
    private Set<Role> roles;




    //b-h
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<User_Role> userRoles;
    //b-h
//    @Transient
//    public static int daftar_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_user_state", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "state_id", referencedColumnName = "state_id")})
    @Fetch(FetchMode.SUBSELECT)
    private Set<State> states;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namayandegi_id")
    private Namayande namayandegi;


    @OneToMany(mappedBy = "karshenas", fetch = FetchType.LAZY)
    @BatchSize(size = 15)
    private List<Pishnehad> pishnehads;

    @OneToMany(mappedBy = "karshenas", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<DarkhastBazkharid> darkhasthayeBazkharid;

    @OneToMany(mappedBy = "karshenas", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<DarkhastTaghirat> darkhasthayeTaghirat;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<EmzaKonande> emzaKonandeList;
    @OneToMany(mappedBy = "userKartabl", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<BimeGozar> bimeGozarList;

    @Column(name = "valid_pass")
    private Boolean validPass;

    @Column(name = "shomare_sabt")
    private String shomare_sabt;

    @Column(name = "tarikhe_sabt")
    private String tarikhe_sabt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    @Fetch(FetchMode.SUBSELECT)
    private List<GhestBandi> ghestBandiList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    private List<LogPrintDaftarche> logPrintDaftarcheList;

    //b-h
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daftar_id")
    private Daftar daftar;

    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }

    //    public static int getDaftar_id() {
//        return daftar_id;
//    }
//
//    public static void setDaftar_id(int daftar_id) {
//        User.daftar_id = daftar_id;
//    }
//
//    public List<User_Role> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(List<User_Role> userRoles) {
//        this.userRoles = userRoles;
//    }

    public List<LogPrintDaftarche> getLogPrintDaftarcheList()
    {
        return logPrintDaftarcheList;
    }

    public void setLogPrintDaftarcheList(List<LogPrintDaftarche> logPrintDaftarcheList)
    {
        this.logPrintDaftarcheList = logPrintDaftarcheList;
    }

    public void setSarmayeFotMaxForKarshenasi(Long sarmayeFotMaxForKarshenasi)
    {
        this.sarmayeFotMaxForKarshenasi = sarmayeFotMaxForKarshenasi;
    }

    public List<GhestBandi> getGhestBandiList()
    {
        return ghestBandiList;
    }

    public void setGhestBandiList(List<GhestBandi> ghestBandiList)
    {
        this.ghestBandiList = ghestBandiList;
    }

    private Integer assignCount;

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }
    //b-h
    public Set<Role> getRoles() {
//        roles=new HashSet<Role>();
//        System.out.println("we are in get roles");

//        if(ActionContext.getContext().getSession() ==null)
//            System.out.println("action context is null");
//        Integer daftar_id=(Integer)ActionContext.getContext().getSession().get("daftar_id");
//        System.out.println("daftar_id"+daftar_id);

//        for(User_Role user_role:userRoles){
//            System.out.println("role daftar id:"+user_role.getDaftar().getId());
//           if(user_role.getDaftar().getId().intValue()==User.daftar_id){
//               roles.add(user_role.getRole());
//               System.out.println("role added:"+user_role.getRole().getRoleName());
////           }
//        }

        return roles;
    }

    public List<Integer> getRolesId()
    {

        Iterator<Role> iterators= roles.iterator();
        List<Integer> rolesId=new ArrayList<Integer>();
        while (iterators.hasNext())
        {
            rolesId.add(iterators.next().getId());
        }
        return rolesId;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasRole(String roleName)
    {
        boolean result = false;
        for (Role role : roles) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                result = true;
            }
        }
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Pishnehad> getPishnehads() {
        return pishnehads;
    }

    public void setPishnehads(List<Pishnehad> pishnehads) {
        this.pishnehads = pishnehads;
    }

    public Namayande getNamayandegi() {
        return namayandegi;
    }

    public void setNamayandegi(Namayande namayandegi) {
        this.namayandegi = namayandegi;

    }

    public List<DarkhastBazkharid> getDarkhasthayeBazkharid() {
        return darkhasthayeBazkharid;
    }

    public void setDarkhasthayeBazkharid(List<DarkhastBazkharid> darkhasthayeBazkharid) {
        this.darkhasthayeBazkharid = darkhasthayeBazkharid;
    }

    public List<EmzaKonande> getEmzaKonandeList() {
        return emzaKonandeList;
    }

    public void setEmzaKonandeList(List<EmzaKonande> emzaKonandeList) {
        this.emzaKonandeList = emzaKonandeList;
    }

    public EmzaKonande getEmzaKonande()
    {
        if (emzaKonandeList==null || emzaKonandeList.size() == 0) return null;
        else return emzaKonandeList.get(0);
    }

    public List<DarkhastTaghirat> getDarkhasthayeTaghirat() {
        return darkhasthayeTaghirat;
    }

    public void setDarkhasthayeTaghirat(List<DarkhastTaghirat> darkhasthayeTaghirat) {
        this.darkhasthayeTaghirat = darkhasthayeTaghirat;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getSematSazmani() {
        return sematSazmani;
    }

    public void setSematSazmani(String sematSazmani) {
        this.sematSazmani = sematSazmani;
    }

    public boolean isFaal() {
        return faal;
    }

    public void setFaal(boolean faal) {
        this.faal = faal;
    }

    public Namayande getMojtamaSodoor() {
        return mojtamaSodoor;
    }

    public void setMojtamaSodoor(Namayande mojtamaSodoor) {
        this.mojtamaSodoor = mojtamaSodoor;
    }

    public int compareTo(User o) {
        if (id.equals(o.getId())) return 0;
        else return lastName.compareTo(o.lastName);
    }

    public List<Pishnehad> getCreatedPisnehads() {
        return createdPisnehads;
    }

    public void setCreatedPisnehads(List<Pishnehad> createdPisnehads) {
        this.createdPisnehads = createdPisnehads;
    }

    public List<BimeGozar> getBimeGozarList() {
        return bimeGozarList;
    }

    public void setBimeGozarList(List<BimeGozar> bimeGozarList) {
        this.bimeGozarList = bimeGozarList;
    }

    public Integer getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(Integer assignCount) {
        this.assignCount = assignCount;
    }

    public Long getSarmayeFotMaxForKarshenasi() {
        return sarmayeFotMaxForKarshenasi;
    }

    public String getSarmayeFotMaxForKarshenasiFormatted() {
        if(sarmayeFotMaxForKarshenasi == null) return "بدون مقدار";
        return NumberFormat.getInstance().format(sarmayeFotMaxForKarshenasi);
    }

    public void setSarmayeFotForKarshenasi(Long sarmayeFotMaxForKarshenasi) {
        this.sarmayeFotMaxForKarshenasi = sarmayeFotMaxForKarshenasi;
    }

    public Short getEzafeNerkhMaxForSodor() {
        return ezafeNerkhMaxForSodor;
    }

    public void setEzafeNerkhMaxForSodor(Short ezafeNerkhMaxForSodor) {
        this.ezafeNerkhMaxForSodor = ezafeNerkhMaxForSodor;
    }

    public Long getSarmayeFotMaxForSodor() {
        return sarmayeFotMaxForSodor;
    }

    public void setSarmayeFotMaxForSodor(Long sarmayeFotMaxForSodor) {
        this.sarmayeFotMaxForSodor = sarmayeFotMaxForSodor;
    }

    public Long getSarmayeHadeseMaxForSodor() {
        return sarmayeHadeseMaxForSodor;
    }

    public void setSarmayeHadeseMaxForSodor(Long sarmayeHadeseMaxForSodor) {
        this.sarmayeHadeseMaxForSodor = sarmayeHadeseMaxForSodor;
    }

    public Short getTabagheKhatarHadeseMaxForSodor() {
        return tabagheKhatarHadeseMaxForSodor;
    }

    public void setTabagheKhatarHadeseMaxForSodor(Short tabagheKhatarHadeseMaxForSodor) {
        this.tabagheKhatarHadeseMaxForSodor = tabagheKhatarHadeseMaxForSodor;
    }

    public Short getTabagheKhatarNaghsMaxForSodor() {
        return tabagheKhatarNaghsMaxForSodor;
    }

    public void setTabagheKhatarNaghsMaxForSodor(Short tabagheKhatarNaghsMaxForSodor) {
        this.tabagheKhatarNaghsMaxForSodor = tabagheKhatarNaghsMaxForSodor;
    }

    public Long getNezaratKarshenasNazerSarmayeFot() {
        return nezaratKarshenasNazerSarmayeFot;
    }

    public void setNezaratKarshenasNazerSarmayeFot(Long nezaratKarshenasNazerSarmayeFot) {
        this.nezaratKarshenasNazerSarmayeFot = nezaratKarshenasNazerSarmayeFot;
    }

    public Long getNezaratKarshenasNazerSarmayeHadese() {
        return nezaratKarshenasNazerSarmayeHadese;
    }

    public void setNezaratKarshenasNazerSarmayeHadese(Long nezaratKarshenasNazerSarmayeHadese) {
        this.nezaratKarshenasNazerSarmayeHadese = nezaratKarshenasNazerSarmayeHadese;
    }

    public Short getNezaratKarshenasNazerTabagheKhatarHadese() {
        return nezaratKarshenasNazerTabagheKhatarHadese;
    }

    public void setNezaratKarshenasNazerTabagheKhatarHadese(Short nezaratKarshenasNazerTabagheKhatarHadese) {
        this.nezaratKarshenasNazerTabagheKhatarHadese = nezaratKarshenasNazerTabagheKhatarHadese;
    }

    public Short getNezaratKarshenasNazerTabagheKhatarNaghs() {
        return nezaratKarshenasNazerTabagheKhatarNaghs;
    }

    public void setNezaratKarshenasNazerTabagheKhatarNaghs(Short nezaratKarshenasNazerTabagheKhatarNaghs) {
        this.nezaratKarshenasNazerTabagheKhatarNaghs = nezaratKarshenasNazerTabagheKhatarNaghs;
    }

    public Short getNezaratKarshenasNazerEzafeNerkh() {
        return nezaratKarshenasNazerEzafeNerkh;
    }

    public void setNezaratKarshenasNazerEzafeNerkh(Short nezaratKarshenasNazerEzafeNerkh) {
        this.nezaratKarshenasNazerEzafeNerkh = nezaratKarshenasNazerEzafeNerkh;
    }

    public Long getNezaratKarshenasMasoolSarmayeFot() {
        return nezaratKarshenasMasoolSarmayeFot;
    }

    public void setNezaratKarshenasMasoolSarmayeFot(Long nezaratKarshenasMasoolSarmayeFot) {
        this.nezaratKarshenasMasoolSarmayeFot = nezaratKarshenasMasoolSarmayeFot;
    }

    public Long getNezaratKarshenasMasoolSarmayeHadese() {
        return nezaratKarshenasMasoolSarmayeHadese;
    }

    public void setNezaratKarshenasMasoolSarmayeHadese(Long nezaratKarshenasMasoolSarmayeHadese) {
        this.nezaratKarshenasMasoolSarmayeHadese = nezaratKarshenasMasoolSarmayeHadese;
    }

    public Short getNezaratKarshenasMasoolTabagheKhatarHadese() {
        return nezaratKarshenasMasoolTabagheKhatarHadese;
    }

    public void setNezaratKarshenasMasoolTabagheKhatarHadese(Short nezaratKarshenasMasoolTabagheKhatarHadese) {
        this.nezaratKarshenasMasoolTabagheKhatarHadese = nezaratKarshenasMasoolTabagheKhatarHadese;
    }

    public Short getNezaratKarshenasMasoolTabagheKhatarNaghs() {
        return nezaratKarshenasMasoolTabagheKhatarNaghs;
    }

    public void setNezaratKarshenasMasoolTabagheKhatarNaghs(Short nezaratKarshenasMasoolTabagheKhatarNaghs) {
        this.nezaratKarshenasMasoolTabagheKhatarNaghs = nezaratKarshenasMasoolTabagheKhatarNaghs;
    }

    public Short getNezaratKarshenasMasoolEzafeNerkh() {
        return nezaratKarshenasMasoolEzafeNerkh;
    }

    public void setNezaratKarshenasMasoolEzafeNerkh(Short nezaratKarshenasMasoolEzafeNerkh) {
        this.nezaratKarshenasMasoolEzafeNerkh = nezaratKarshenasMasoolEzafeNerkh;
    }

    public Long getNezaratRaeesEdareSarmayeFot() {
        return nezaratRaeesEdareSarmayeFot;
    }

    public void setNezaratRaeesEdareSarmayeFot(Long nezaratRaeesEdareSarmayeFot) {
        this.nezaratRaeesEdareSarmayeFot = nezaratRaeesEdareSarmayeFot;
    }

    public Long getNezaratRaeesEdareSarmayeHadese() {
        return nezaratRaeesEdareSarmayeHadese;
    }

    public void setNezaratRaeesEdareSarmayeHadese(Long nezaratRaeesEdareSarmayeHadese) {
        this.nezaratRaeesEdareSarmayeHadese = nezaratRaeesEdareSarmayeHadese;
    }

    public Short getNezaratRaeesEdareTabagheKhatarHadese() {
        return nezaratRaeesEdareTabagheKhatarHadese;
    }

    public void setNezaratRaeesEdareTabagheKhatarHadese(Short nezaratRaeesEdareTabagheKhatarHadese) {
        this.nezaratRaeesEdareTabagheKhatarHadese = nezaratRaeesEdareTabagheKhatarHadese;
    }

    public Short getNezaratRaeesEdareTabagheKhatarNaghs() {
        return nezaratRaeesEdareTabagheKhatarNaghs;
    }

    public void setNezaratRaeesEdareTabagheKhatarNaghs(Short nezaratRaeesEdareTabagheKhatarNaghs) {
        this.nezaratRaeesEdareTabagheKhatarNaghs = nezaratRaeesEdareTabagheKhatarNaghs;
    }

    public Short getNezaratRaeesEdareEzafeNerkh() {
        return nezaratRaeesEdareEzafeNerkh;
    }

    public void setNezaratRaeesEdareEzafeNerkh(Short nezaratRaeesEdareEzafeNerkh) {
        this.nezaratRaeesEdareEzafeNerkh = nezaratRaeesEdareEzafeNerkh;
    }

    public boolean isNezaratEnabled() {
        return nezaratEnabled;
    }

    public void setNezaratEnabled(boolean nezaratEnabled) {
        this.nezaratEnabled = nezaratEnabled;
    }

    public Boolean getValidPass() {
        return validPass;
    }

    public void setValidPass(Boolean validPass) {
        this.validPass = validPass;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCodeRamz() {
        return codeRamz;
    }

    public void setCodeRamz(String codeRamz) {
        this.codeRamz = codeRamz;
    }

    public String getCodeRamzTarikh() {
        return codeRamzTarikh;
    }

    public void setCodeRamzTarikh(String codeRamzTarikh) {
        this.codeRamzTarikh = codeRamzTarikh;
    }

    public String toString() {
        return "user.id: " + this.getId();
    }

    public String getShomare_sabt() {
        return shomare_sabt;
    }

    public void setShomare_sabt(String shomare_sabt) {
        this.shomare_sabt = shomare_sabt;
    }

    public String getTarikhe_sabt() {
        return tarikhe_sabt;
    }

    public void setTarikhe_sabt(String tarikhe_sabt) {
        this.tarikhe_sabt = tarikhe_sabt;
    }
}
