package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Jul 31, 2011
 * Time: 5:03:49 PM
 */
@Entity
@Table(name = "tbl_ghestBandi")
public class GhestBandi implements Serializable, Comparable<GhestBandi> {

    public static enum Type
    {
        G_VAM,
        G_BIMENAME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "saleBimei")
    private String saleBimei;
    @Column(name = "tarikheTaghsit")
    private String tarikheTaghsit;
    @OneToMany(mappedBy = "ghestBandi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Ghest> ghestList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bimename_id")
    private Bimename bimename;
    @OneToMany(mappedBy = "ghestBandi")
    private List<DarkhastBazkharid> darkhastList;
    @Column(name = "hagh_bime_hadese_long")
    private Long haghBimePoosheshHadese_long;
    @Column(name = "hagh_bime_moafiat_long")
    private Long haghBimePoosheshMoafiat_long;
    @Column(name = "hagh_bime_amraz_long")
    private Long haghBimePoosheshAmraz_long;
    @Column(name = "hagh_bime_naghs_long")
    private Long haghBimePoosheshNaghsOzv_long;
    @Column(name = "majmoo_ezafi")
    private Long majmooeEzafi;
    @Column(name = "majmoo_amount")
    private Long majmooeAmount;
    @Column(name = "majmoo_maliat")
    private Long majmooeMaliat;
    @Column(name = "kasr_hazine")
    private Integer kasrHazine;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name= "has_print_daftarche")
    private String hasPrint;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ghestBandi")
    @Fetch(FetchMode.SUBSELECT)
    private List<LogPrintDaftarche> logPrintDaftarcheList;

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    public GhestBandi() {
    }

    public GhestBandi(String saleBimei, String tarikheTaghsit, Bimename bimename) {
        this.saleBimei = saleBimei;
        this.tarikheTaghsit = tarikheTaghsit;
        this.bimename = bimename;
    }

    public List<LogPrintDaftarche> getLogPrintDaftarcheList()
    {
        return logPrintDaftarcheList;
    }

    public void setLogPrintDaftarcheList(List<LogPrintDaftarche> logPrintDaftarcheList)
    {
        this.logPrintDaftarcheList = logPrintDaftarcheList;
    }

    public User getCreator()
    {
        return creator;
    }

    public void setCreator(User creator)
    {
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleBimei() {
        return saleBimei;
    }

    public Integer getSaleBimeiInt() {
        if (saleBimei == null) return -1;
        return Integer.parseInt(saleBimei);
    }

    public void setSaleBimei(String saleBimei) {
        this.saleBimei = saleBimei;
    }

    public String getTarikheTaghsit() {
        return tarikheTaghsit;
    }

    public void setTarikheTaghsit(String tarikheTaghsit) {
        this.tarikheTaghsit = tarikheTaghsit;
    }

    public List<Ghest> getGhestList() {
        Collections.sort(ghestList);
        return ghestList;
    }

    public void setGhestList(List<Ghest> ghestList) {
        this.ghestList = ghestList;
    }

    public Bimename getBimename() {
        return bimename;
    }


    public String getHasPrint()
    {
        return hasPrint;
    }

    public void setHasPrint(String hasPrint)
    {
        this.hasPrint = hasPrint;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public int compareTo(GhestBandi o) {
        if (id.equals(o.getId())) return 0;
        if (saleBimei == null) return 1;
        if (o.getSaleBimei() == null) return -1;
        return getSaleBimeiInt().compareTo(o.getSaleBimeiInt());
    }

    public String getShomareVam() {
        for (DarkhastBazkharid d : darkhastList) {
            if (d.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM))
                return d.getShomareDarkhast();
        }
        return "";
    }

    public List<DarkhastBazkharid> getDarkhastList() {
        return darkhastList;
    }

    public void setDarkhastList(List<DarkhastBazkharid> darkhastList) {
        this.darkhastList = darkhastList;
    }

    public Long getMajmuAghsat() {
        long tmp = 0L;
        for (Ghest g : ghestList)
            if (g.getCredebit() != null && g.getCredebit().getAmount() != null && !g.getCredebit().getAmount().isEmpty())
                tmp += Long.parseLong(g.getCredebit().getAmount().replace(",", "").trim());
        return tmp;
    }

    public String getHaghBimePoosheshHadese() {
        if (haghBimePoosheshHadese_long == null)
            return "0";
        return NumberFormat.getInstance().format(haghBimePoosheshHadese_long) ;
    }

    public void setHaghBimePoosheshHadese(String haghBimePoosheshHadese) {
        if(haghBimePoosheshHadese==null || haghBimePoosheshHadese.equals(""))
            this.haghBimePoosheshHadese_long=null;
        this.haghBimePoosheshHadese_long = Long.parseLong(haghBimePoosheshHadese.replaceAll(",","").trim());
    }

    public String getHaghBimePoosheshMoafiat() {
        if (haghBimePoosheshMoafiat_long == null)
            return "0";
        return NumberFormat.getInstance().format(haghBimePoosheshMoafiat_long) ;
    }

    public void setHaghBimePoosheshMoafiat(String haghBimePoosheshMoafiat) {
        if(haghBimePoosheshMoafiat==null || haghBimePoosheshMoafiat.equals(""))
            this.haghBimePoosheshMoafiat_long=null;
        this.haghBimePoosheshMoafiat_long = Long.parseLong(haghBimePoosheshMoafiat.replaceAll(",","").trim());
    }

    public String getHaghBimePoosheshAmraz() {
        if (haghBimePoosheshAmraz_long == null)
            return "0";
        return NumberFormat.getInstance().format(haghBimePoosheshAmraz_long) ;
    }

    public void setHaghBimePoosheshAmraz(String haghBimePoosheshAmraz) {
        if(haghBimePoosheshAmraz==null || haghBimePoosheshAmraz.equals(""))
            this.haghBimePoosheshAmraz_long=null;
        this.haghBimePoosheshAmraz_long = Long.parseLong(haghBimePoosheshAmraz.replaceAll(",","").trim());
    }

    public String getHaghBimePoosheshNaghsOzv() {
        if (haghBimePoosheshNaghsOzv_long == null)
            return "0";
        return NumberFormat.getInstance().format(haghBimePoosheshNaghsOzv_long) ;
    }

    public void setHaghBimePoosheshNaghsOzv(String haghBimePoosheshNaghsOzv) {
        if(haghBimePoosheshNaghsOzv==null || haghBimePoosheshNaghsOzv.equals(""))
            this.haghBimePoosheshNaghsOzv_long=null;
        this.haghBimePoosheshNaghsOzv_long = Long.parseLong(haghBimePoosheshNaghsOzv.replaceAll(",","").trim());
    }

    public Long getHaghBimePoosheshHadese_long() {
        return haghBimePoosheshHadese_long;
    }

    public void setHaghBimePoosheshHadese_long(Long haghBimePoosheshHadese_long) {
        this.haghBimePoosheshHadese_long = haghBimePoosheshHadese_long;
    }

    public Long getHaghBimePoosheshMoafiat_long() {
        return haghBimePoosheshMoafiat_long;
    }

    public void setHaghBimePoosheshMoafiat_long(Long haghBimePoosheshMoafiat_long) {
        this.haghBimePoosheshMoafiat_long = haghBimePoosheshMoafiat_long;
    }

    public Long getHaghBimePoosheshAmraz_long() {
        return haghBimePoosheshAmraz_long;
    }

    public void setHaghBimePoosheshAmraz_long(Long haghBimePoosheshAmraz_long) {
        this.haghBimePoosheshAmraz_long = haghBimePoosheshAmraz_long;
    }

    public Long getHaghBimePoosheshNaghsOzv_long() {
        return haghBimePoosheshNaghsOzv_long;
    }

    public void setHaghBimePoosheshNaghsOzv_long(Long haghBimePoosheshNaghsOzv_long) {
        this.haghBimePoosheshNaghsOzv_long = haghBimePoosheshNaghsOzv_long;
    }

    public String getMajmuHaghBimeSal()
    {
        long totalAmount=0;

        for(Ghest g : ghestList)
        {
            totalAmount+=g.getCredebit().getAmount_long();
        }
        return NumberFormat.getInstance().format(totalAmount);
    }

    public String getKarmozdSal()
    {
        long totalAmount=0;
        for(Ghest g : ghestList)
        {
            if(this.bimename.getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
            {
                totalAmount = KarmozdCalculate.calKarmozdSal(KarmozdCalculate.calRealKarmozdForGhest(g.getCredebit(),KarmozdCalculate.getTarefeBimename(this.bimename), null),0,KarmozdCalculate.getTarefeBimename(this.bimename),true);
            }
            else
            {
                totalAmount+= KarmozdCalculate.calRealKarmozdForGhest(g.getCredebit(),KarmozdCalculate.getTarefeBimename(this.bimename), null);
            }
        }
        totalAmount = KarmozdCalculate.percentPaymentOfKarmozdToNamayande(bimename.getPishnehad().getNamayande(), totalAmount, KarmozdCalculate.getTarefeBimename(this.getBimename()));
        return NumberFormat.getInstance().format(totalAmount);
    }
    public String getTarefe()
    {
        if(KarmozdCalculate.getTarefeBimename(this.bimename).equals(KarmozdGhest.Tarefe.NAVADO_YEK))
            return "تعرفه 1391/01/01";
        else if(KarmozdCalculate.getTarefeBimename(this.bimename).equals(KarmozdGhest.Tarefe.YEK_YEK_NAVAD_DO))
            return "تعرفه 1392/01/01";
        else if(KarmozdCalculate.getTarefeBimename(this.bimename).equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO))
            return "تعرفه 1392/03/01";
        else if(KarmozdCalculate.getTarefeBimename(this.bimename).equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
            return "تعرفه 1392/04/01";
        else
            return "";
    }

    public String getSumGhestsAmount()
    {
        long sum=0;
        for(Ghest g : ghestList)
        {
            sum += g.getCredebit().getAmount_long();
        }
        return NumberFormat.getInstance().format(sum);
    }

    public Long getMajmooeEzafi() {
        return majmooeEzafi;
    }

    public void setMajmooeEzafi(Long majmooeEzafi) {
        this.majmooeEzafi = majmooeEzafi;
    }

    public Long getMajmooeAmount() {
        return majmooeAmount;
    }

    public void setMajmooeAmount(Long majmooeAmount) {
        this.majmooeAmount = majmooeAmount;
    }

    public Long getMajmooeMaliat() {
        return majmooeMaliat;
    }

    public void setMajmooeMaliat(Long majmooeMaliat) {
        this.majmooeMaliat = majmooeMaliat;
    }

    public Integer getKasrHazine() {
        if(kasrHazine == null) return 0;
        return kasrHazine;
    }

    public void setKasrHazine(Integer kasrHazine) {
        this.kasrHazine = kasrHazine;
    }

    public String getTypeFarsi()
    {
        if(this.type==null)
            return "";
        if(this.type.equals(Type.G_BIMENAME))
            return "قسط بندی بیمه نامه";
        else if(this.type.equals(Type.G_VAM))
            return "قسط بندی وام";
        else
            return "-";
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }


}
