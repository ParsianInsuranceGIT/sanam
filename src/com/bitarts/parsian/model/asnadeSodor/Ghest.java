package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.karmozd.Karmozd;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 1:48 PM
 */
@Entity
@Table(name = "tbl_ghest")
public class Ghest implements Serializable, Comparable<Ghest> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sarresid_date")
    private String sarresidDate;
    @Column(name = "hazine_karmozd")
    private String hazineKarmonz;
    @Column(name = "hazine_bimegari")
    private String hazineBimegari;
    @Column(name = "hazine_edari")
    private String hazineEdari;
    @Column(name = "hazine_vosoul")
    private String hazineVosoul;
    @Column(name = "maliat_long")
    private Long maliatLong;
    @Column(name = "hazine_taghsit")
    private String hazineTaghsit;
    @Column(name = "hagh_bime_fot_long")
    private Long haghBimeFot_long;
    @Column(name = "sarmaye_fot_anyway")
    private String sarmayeFotBeHarEllat;
    @Column(name = "sarmaye_got_hadese")
    private String sarmayeFotDarAsarHadeseh;
    @Column(name = "sarmaye_pooshesh_khaas")
    private String sarmayePoosheshEzafiAmraazKhaas;
    @Column(name = "hagh_bime_ezafi_long")
    private Long haghBimePoosheshhayeEzafiLong;
    @Column(name = "karmozd_pooshesh_ezafi")
    private Long karmozdPoosheshEzafi;
    @Column(name = "karmozd_vosuli")
    private Long karmozdVosuli;
    @Column(name = "hagh_bime_hadese")
    private String haghBimePoosheshHadese;
    @Column(name = "hagh_bime_moafiat")
    private String haghBimePoosheshMoafiat;
    @Column(name = "hagh_bime_amraz")
    private String haghBimePoosheshAmraz;
    @Column(name = "hagh_bime_naghs")
    private String haghBimePoosheshNaghsOzv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ghestBandi_id")
    private GhestBandi ghestBandi;
//    @OneToOne(mappedBy = "ghest", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
//    private Credebit credebit;
    @OneToMany(mappedBy = "ghest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Credebit> credebitList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Karmozd karmozd;
    private String karmozdAmount = "";//remainingAmount

    @OneToMany(mappedBy = "ghest", fetch = FetchType.LAZY)
    private Set<KarmozdGhest> karmozdSet;

    @Column(name="karmozd_paid")
    private Long karmozdPaid;
    @Column(name="karmozd_taghirat")
    private Long karmozdTgr;
    @Column(name="karmozd_real")
    private Long karmozdReal;
    @Column(name="karmozd_real_previous")
    private Long preKarmozdReal;
    @Column(name="ghest_number")
    private Integer number;

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public String getKarmozdTgrString()
    {
        return NumberFormat.getInstance().format(this.karmozdTgr!=null?this.karmozdTgr:0);
    }

    public Long getKarmozdTgr()
    {
        return karmozdTgr;
    }

    public void setKarmozdTgr(Long karmozdTgr)
    {
        this.karmozdTgr = karmozdTgr;
    }

    public Long getPreKarmozdReal()
    {
        return preKarmozdReal;
    }

    public void setPreKarmozdReal(Long preKarmozdReal)
    {
        this.preKarmozdReal = preKarmozdReal;
    }

    public Long getKarmozdPaid()
    {
        return karmozdPaid;
    }

    public void setKarmozdPaid(Long karmozdPaid)
    {
        this.karmozdPaid = karmozdPaid;
    }

    public Long getKarmozdReal()
    {
        return karmozdReal;
    }

    public void setKarmozdReal(Long karmozdReal)
    {
        this.karmozdReal = karmozdReal;
    }

    public String getKarmozdRealString()
    {
        long rtnVal=0;
        if (karmozdReal==null )
            rtnVal = KarmozdCalculate.calRealKarmozdForGhest(this.getCredebit(), KarmozdCalculate.getTarefeBimename(ghestBandi.getBimename()), null);
        else
            rtnVal = karmozdReal;
        rtnVal=KarmozdCalculate.percentPaymentOfKarmozdToNamayande(this.getCredebit().getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getNamayandeType(),rtnVal, KarmozdCalculate.getTarefeBimename(ghestBandi.getBimename()));
        return NumberFormat.getInstance().format(rtnVal);
    }

    public String getKarmozdVosuliString()
    {
        return NumberFormat.getInstance().format(this.karmozdVosuli!=null? this.karmozdVosuli : 0);
    }

    public String getKarmozdPoosheshEzafiString()
    {
        return NumberFormat.getInstance().format(this.karmozdPoosheshEzafi!=null? this.karmozdPoosheshEzafi : 0);
    }


    public String getKarmozdPaidString()
    {
        if (karmozdPaid==null )
            return "-";
        return NumberFormat.getInstance().format(karmozdPaid);
    }

    public Long getMaliatLong()
    {
        return maliatLong;
    }

    public void setMaliatLong(Long maliatLong)
    {
        this.maliatLong = maliatLong;
    }

    public Long getHaghBimePoosheshhayeEzafiLong()
    {
        return haghBimePoosheshhayeEzafiLong;
    }

    public void setHaghBimePoosheshhayeEzafiLong(Long haghBimePoosheshhayeEzafiLong)
    {
        this.haghBimePoosheshhayeEzafiLong = haghBimePoosheshhayeEzafiLong;
    }

    public Set<KarmozdGhest> getkarmozdSet()
    {
        return karmozdSet;
    }

    public void setKarmozdSet(Set<KarmozdGhest> karmozdSet)
    {
        this.karmozdSet = karmozdSet;
    }

    public Ghest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSarresidDate() {
        return sarresidDate;

    }

    public void setSarresidDate(String sarresidDate) {
        this.sarresidDate = sarresidDate;
    }

    public String getHazineKarmonz() {
        if(hazineKarmonz == null) return "0";
        return hazineKarmonz;
    }

    public void setHazineKarmonz(String hazineKarmonz) {
        this.hazineKarmonz = hazineKarmonz;
    }

    public String getHazineBimegari() {
        if(hazineBimegari == null) return "0";
        return hazineBimegari;
    }

    public void setHazineBimegari(String hazineBimegari) {
        this.hazineBimegari = hazineBimegari;
    }

    public String getHazineEdari() {
        if(hazineEdari == null) return "0";
        return hazineEdari;
    }

    public void setHazineEdari(String hazineEdari) {
        this.hazineEdari = hazineEdari;
    }

    public String getHazineVosoul() {
        if(hazineVosoul == null) return "0";
        return hazineVosoul;
    }

    public void setHazineVosoul(String hazineVosoul) {
        this.hazineVosoul = hazineVosoul;
    }

    public String getHaghBimeFotYekja() {
        if (haghBimeFot_long == null)
            return "0";
        return NumberFormat.getInstance().format(haghBimeFot_long) ;
    }

    public void setHaghBimeFotYekja(String haghBimeFot) {
        if(haghBimeFot==null || haghBimeFot.equals(""))
            this.haghBimeFot_long=null;
        else
            this.haghBimeFot_long = Long.parseLong(haghBimeFot.replaceAll(",","").trim());
    }

    public String getSarmayeFotBeHarEllat() {
        return sarmayeFotBeHarEllat;
    }

    public void setSarmayeFotBeHarEllat(String sarmayeFotBeHarEllat) {
        this.sarmayeFotBeHarEllat = sarmayeFotBeHarEllat;
    }

    public String getSarmayeFotDarAsarHadeseh() {
        return sarmayeFotDarAsarHadeseh;
    }

    public void setSarmayeFotDarAsarHadeseh(String sarmayeFotDarAsarHadeseh) {
        this.sarmayeFotDarAsarHadeseh = sarmayeFotDarAsarHadeseh;
    }

    public String getSarmayePoosheshEzafiAmraazKhaas() {
        return sarmayePoosheshEzafiAmraazKhaas;
    }

    public void setSarmayePoosheshEzafiAmraazKhaas(String sarmayePoosheshEzafiAmraazKhaas) {
        this.sarmayePoosheshEzafiAmraazKhaas = sarmayePoosheshEzafiAmraazKhaas;
    }

    public String getHaghBimePoosheshhayeEzafi() {
        if(haghBimePoosheshhayeEzafiLong == null) return "0";
        return NumberFormat.getInstance().format(haghBimePoosheshhayeEzafiLong);
    }

    public void setHaghBimePoosheshhayeEzafi(String haghBimePoosheshhayeEzafi) {
        this.haghBimePoosheshhayeEzafiLong = Long.parseLong(haghBimePoosheshhayeEzafi!=null?haghBimePoosheshhayeEzafi.replaceAll(",","").trim():"0");
    }

    public GhestBandi getGhestBandi() {
        return ghestBandi;
    }

    public void setGhestBandi(GhestBandi ghestBandi) {
        this.ghestBandi = ghestBandi;
    }

    public Credebit getCredebit() {
        if (credebitList == null) return null;
        if (credebitList.size() == 0)
            return null;
        else
            return credebitList.get(0);
    }

    public void setCredebit(Credebit credebit) {
        ArrayList<Credebit> p = new ArrayList<Credebit>();
        p.add(credebit);
        setCredebitList(p);
    }

    public Karmozd getKarmozd() {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd) {
        this.karmozd = karmozd;
    }

    public String getKarmozdAmount() {
        if (karmozdAmount == null) return "0";
        return NumberFormat.getNumberInstance().format(Long.parseLong(karmozdAmount.replace(",", "").trim()));
    }

    public void setKarmozdAmount(String karmozdAmount) {
        this.karmozdAmount = karmozdAmount;
    }

    public String getTarikhElamBeMali() {
        try {
            return getCredebit().getCreatedDate();
        } catch (Exception ex) {
            return "";
        }
    }

    public String getTarikhSanad() {
        return getCredebit().getTarikhSanadMali();
    }

    public String getMablaghVosuli() {
        try {
            long count = 0L;
            for (KhateSanad kh : getCredebit().getKhateSanadsBedehi())
                count += Long.parseLong(kh.getAmount().replace(",", "").trim());
            return NumberFormat.getNumberInstance().format(count);

        } catch (Exception ex) {
            return "";
        }
    }

    public String getTarikhPardakht() {
        try {
//            if (credebit.getRemainingAmount().equals("0")) {
            if (getCredebit().getKhateSanadsBedehi().size() > 0) {
                List<KhateSanad> khateSanadList = getCredebit().getKhateSanadsBedehi();
                Collections.max(khateSanadList);
                return khateSanadList.get(0).getEtebarCredebit().getCreatedDate();
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    public boolean isMoavagh() {
        return (getTarikhPardakht().isEmpty() && DateUtil.isGreaterThan(DateUtil.getCurrentDate(), sarresidDate)) || DateUtil.isGreaterThan(getTarikhPardakht(), sarresidDate);
    }

    public boolean isNareside() {
        return !DateUtil.isGreaterThan(DateUtil.getCurrentDate(), sarresidDate);
    }

    public int compareTo(Ghest o) {
        if (id.equals(o.getId())) return 0;
        if (sarresidDate == null) return 1;
        if (o.getSarresidDate() == null) return -1;
        return sarresidDate.compareTo(o.getSarresidDate());
    }

    public String getHaghBimePoosheshHadese() {
        if(haghBimePoosheshHadese == null) return "0";
        return haghBimePoosheshHadese;
    }

    public void setHaghBimePoosheshHadese(String haghBimePoosheshHadese) {
        this.haghBimePoosheshHadese = haghBimePoosheshHadese;
    }

    public String getHaghBimePoosheshMoafiat() {
        if(haghBimePoosheshMoafiat == null) return "0";
        return haghBimePoosheshMoafiat;
    }

    public void setHaghBimePoosheshMoafiat(String haghBimePoosheshMoafiat) {
        this.haghBimePoosheshMoafiat = haghBimePoosheshMoafiat;
    }

    public String getHaghBimePoosheshAmraz() {
        if(haghBimePoosheshAmraz == null) return "0";
        return haghBimePoosheshAmraz;
    }

    public void setHaghBimePoosheshAmraz(String haghBimePoosheshAmraz) {
        this.haghBimePoosheshAmraz = haghBimePoosheshAmraz;
    }

    public String getHaghBimePoosheshNaghsOzv() {
        if(haghBimePoosheshNaghsOzv == null) return "0";
        return haghBimePoosheshNaghsOzv;
    }

    public void setHaghBimePoosheshNaghsOzv(String haghBimePoosheshNaghsOzv) {
        this.haghBimePoosheshNaghsOzv = haghBimePoosheshNaghsOzv;
    }

    public int getShomareSal()
    {
        int temp=0;
        int shomarGhest= this.getShomareGhest();
        if( shomarGhest % 12 > 0)
            temp=1;

        return shomarGhest / 12 + temp;
    }


    public int getShomareGhest() {
        int i = 1;
        List<Ghest> ghests = ghestBandi.getGhestList();
        Collections.sort(ghests);
        for(Ghest g : ghests) {
            if(g.getSarresidDate().equals(sarresidDate)) {
                return i;
            }
            i++;
        }
        return i;
    }

    public String getMaliat() {
        if(maliatLong == null) return "0";
        return NumberFormat.getInstance().format(maliatLong);
    }

    public void setMaliat(String maliat) {
        this.maliatLong = Long.parseLong(maliat!=null?maliat.replaceAll(",","").trim():"0");
    }

    public String getHazineTaghsit() {
        return hazineTaghsit;
    }

    public void setHazineTaghsit(String hazineTaghsit) {
        this.hazineTaghsit = hazineTaghsit;
    }

    public Long getHaghBimeFot_long() {
        return haghBimeFot_long;
    }

    public void setHaghBimeFot_long(Long haghBimeFot_long) {
        this.haghBimeFot_long = haghBimeFot_long;
    }

    public long getMajmooHazineha() {
        return Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHaghBimePoosheshhayeEzafi().replaceAll(",", "")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getMaliat().replaceAll(",","")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHaghBimeFotYekja().replaceAll(",","")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHazineEdari().replaceAll(",","")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHazineBimegari().replaceAll(",","")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHazineVosoul().replaceAll(",","")))))
                                    + Long.parseLong(String.valueOf(Math.round(Float.parseFloat(getHazineKarmonz().replaceAll(",","")))));
    }

    public List<Credebit> getCredebitList() {
        return credebitList;
    }

    public void setCredebitList(List<Credebit> credebitList) {
        this.credebitList = credebitList;
    }

    public Long getKarmozdPoosheshEzafi()
    {
        return karmozdPoosheshEzafi;
    }

    public void setKarmozdPoosheshEzafi(Long karmozdPoosheshEzafi)
    {
        this.karmozdPoosheshEzafi = karmozdPoosheshEzafi;
    }

    public Long getKarmozdVosuli()
    {
        return karmozdVosuli;
    }

    public void setKarmozdVosuli(Long karmozdVosuli)
    {
        this.karmozdVosuli = karmozdVosuli;
    }

}
