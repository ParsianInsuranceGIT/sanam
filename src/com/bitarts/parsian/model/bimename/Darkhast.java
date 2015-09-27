package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.log.TransitionLog;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Sep 7, 2011
 * Time: 2:41:36 PM
 */
@Entity
@Table(name = "tbl_darkhast")
public class Darkhast implements Comparable{

    public Elhaghiye getElhaghiye() {
        if(this.elhaghiyeList!=null && this.elhaghiyeList.size()>0)
            return getElhaghiyeList().get(0);
        return null;
    }

    public void setElhaghiye(Elhaghiye elhaghiye)
    {
        if(this.elhaghiyeList!=null)
        {
            this.elhaghiyeList.add(elhaghiye);
        }
        else
        {
            this.elhaghiyeList=new ArrayList<Elhaghiye>();
            this.elhaghiyeList.add(elhaghiye);
        }
    }

    public static enum DarkhastType {
        BAZKHARID, VAM, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, TAGHYIRAT, TAGHIRKOD, EBTAL, KHESARAT ,TOZIH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "darkhast_type")
    @Enumerated(EnumType.STRING)
    private DarkhastType darkhastType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_bazkharid")
    private DarkhastBazkharid darkhastBazkharid;
    @ManyToOne
    @JoinColumn(name = "darkhast_vam")
    private DarkhastVam darkhastVam;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_taghirat")
    private DarkhastTaghirat darkhastTaghirat;
    @Column(name = "darkhast_finished")
    private Boolean finished;

//    @OneToOne(mappedBy = "darkhast", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Elhaghiye elhaghiye;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "darkhast", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Elhaghiye> elhaghiyeList;

    public List<Elhaghiye> getElhaghiyeList()
    {
        return elhaghiyeList;
    }

    public void setElhaghiyeList(List<Elhaghiye> elhaghiyeList)
    {
        this.elhaghiyeList = elhaghiyeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DarkhastType getDarkhastType() {
        return darkhastType;
    }

    public void setDarkhastType(DarkhastType darkhastType) {
        this.darkhastType = darkhastType;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public DarkhastVam getDarkhastVam() {
        return darkhastVam;
    }

    public void setDarkhastVam(DarkhastVam darkhastVam) {
        this.darkhastVam = darkhastVam;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getDarkhastDate()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                return darkhastTaghirat.getTarikhDarkhast();
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                return darkhastBazkharid.getTarikhDarkhast();
        }
    }

    public String getShomareBimename()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                if(darkhastTaghirat.getOldPishnehad()!=null) return darkhastTaghirat.getOldPishnehad().getBimename().getShomare();
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getBimename()!=null) return darkhastBazkharid.getBimename().getShomare();
                else return "-";
        }
    }

    public String getName_KodeNamayandegi()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                if(darkhastTaghirat.getNamayande()!=null)return darkhastTaghirat.getNamayande().getName_kod();
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getNamayande()!=null) return darkhastBazkharid.getNamayande().getName_kod();
                else return "-";
        }
    }

    public String getBimegozarFullName()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                if(darkhastTaghirat.getOldPishnehad()!=null) return darkhastTaghirat.getOldPishnehad().getBimeGozar().getShakhs().getFullName();
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getBimename()!=null) return darkhastBazkharid.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName();
                else return "-";
        }
    }



    public String getBimeshodeFullName()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                if(darkhastTaghirat.getOldPishnehad()!=null) return darkhastTaghirat.getOldPishnehad().getBimeShode().getShakhs().getFullName();
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getBimename()!=null) return darkhastBazkharid.getBimename().getPishnehad().getBimeShode().getShakhs().getFullName();
                else return "-";
        }
    }

    public String getKarshenasKhesaratFullName()
    {
        if (darkhastBazkharid==null) return null;
        return darkhastBazkharid.getKarshenasKhesarat()!=null ? darkhastBazkharid.getKarshenasKhesarat().getFullName() : "-";
    }

    public String getKarshenasFullName()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                if(darkhastTaghirat.getKarshenas()!=null) return darkhastTaghirat.getKarshenas().getFullName();
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getKarshenas()!=null) return darkhastBazkharid.getKarshenas().getFullName();
                else return "-";
        }
    }

    public String getNamayeshLink()
    {
        switch (darkhastType)
        {
             case TAGHYIRAT:
                       return "  /editDarkhastTaghirForm?darkhastTaghirat.id="+darkhastTaghirat.getId();
             default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
               return "/editDarkhastForm?darkhastBazkharid.id="+darkhastBazkharid.getId();
        }
    }

    public String getNamayeshLinkTabsAll()
    {
        switch (darkhastType)
        {
            case TAGHYIRAT:
                return "/editDarkhastTaghirFormReadOnly?darkhastTaghirat.id=" + darkhastTaghirat.getId();
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                return "/editDarkhastFormReadOnly?darkhastBazkharid.id=" + darkhastBazkharid.getId();
        }
    }

    public String getTarikhcheLink()
    {
        switch (darkhastType)
        {
             case TAGHYIRAT:
                       return "/showDarkhastTaghirTransitionLog?darkhastTaghir.id="+darkhastTaghirat.getId();
//            case KHESARAT:
//                return "";
             default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
               return "/showDarkhastBazkharidTransitionLog?darkhastBazkharid.id="+darkhastBazkharid.getId();
        }
    }

    public String getTransitionLogDate()
    {
        switch (darkhastType)
        {
            case KHESARAT:
                return null; //az koja?
            case TAGHYIRAT:
                if(darkhastTaghirat.getTransitionLogs().size()>0)
                {
                    List<TransitionLog> tLogsList= darkhastTaghirat.getTransitionLogs();
                    List<String> listDate=new ArrayList<String>();
                    for(TransitionLog t:tLogsList)
                        listDate.add(t.getDate());
                    Collections.sort(listDate);
                    return listDate.get(listDate.size()-1);
                }
                else return "-";
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                if(darkhastBazkharid.getTransitionLogs().size()>0)
                {
                    List<TransitionLog> tLogsList2= darkhastBazkharid.getTransitionLogs();
                    List<String> listDate2=new ArrayList<String>();
                    for(TransitionLog t:tLogsList2)
                        listDate2.add(t.getDate());
                    Collections.sort(listDate2);
                    return listDate2.get(listDate2.size()-1);
                }
                else return "-";
        }
    }

    public State getState()
    {
        switch (darkhastType)
        {
            case KHESARAT:
                return darkhastBazkharid.getState();
            case VAM:
                return darkhastBazkharid.getState();
            case TAGHYIRAT:
                return darkhastTaghirat.getState();
            default: //case BAZKHARID, BARDASHT_AZ_ANDOKHTE, TASVIE_PISH_AZ_MOEDE_VAM, EBTAL, TAGHIRKOD:
                return darkhastBazkharid.getState();
        }
    }

    public static String getTypeFarsi(DarkhastType type)
    {
        if(type==null)
            return "";
        switch (type)
        {
            case KHESARAT:
                return "خسارت";
            case VAM:
                return "وام";
            case TAGHYIRAT:
                return "تغییرات";
            case BAZKHARID:
                return "بازخرید";
            case BARDASHT_AZ_ANDOKHTE:
                return "برداشت از اندوخته";
            case TASVIE_PISH_AZ_MOEDE_VAM:
                return "تسویه پیش از موعد وام";
            case EBTAL:
                return "ابطال";
            case TAGHIRKOD:
                return "تغییر کد";
            case TOZIH:
                return "الحاقيه توضيح";
            default:
                return "";
        }
    }

    public String getDarkhastTypeFarsi()
    {
        switch (darkhastType)
        {
            case KHESARAT:
                return "خسارت";
            case VAM:
                return "وام";
            case TAGHYIRAT:
                return "تغییرات";
            case BAZKHARID:
                return "بازخرید";
            case BARDASHT_AZ_ANDOKHTE:
                return "برداشت از اندوخته";
            case TASVIE_PISH_AZ_MOEDE_VAM:
                return "تسویه پیش از موعد وام";
            case  EBTAL:
                return "ابطال";
            case TAGHIRKOD:
                return "تغییر کد";
            case TOZIH:
                return "الحاقيه توضيح";
            default:
                return null;
        }
    }

    public static String getDarkhastTypeFarsi(DarkhastType darkhastType)
    {
        switch (darkhastType)
        {
            case KHESARAT:
                return "خسارت";
            case VAM:
                return "وام";
            case TAGHYIRAT:
                return "تغییرات";
            case BAZKHARID:
                return "بازخرید";
            case BARDASHT_AZ_ANDOKHTE:
                return "برداشت از اندوخته";
            case TASVIE_PISH_AZ_MOEDE_VAM:
                return "تسویه پیش از موعد وام";
            case  EBTAL:
                return "ابطال";
            case TAGHIRKOD:
                return "تغییر کد";
            case TOZIH:
                return "الحاقيه توضيح";
            default:
                return null;
        }
    }

    public int compareTo(Object o) {
        return this.getTransitionLogDate().compareTo(((Darkhast) (o)).getTransitionLogDate());
    }

}
