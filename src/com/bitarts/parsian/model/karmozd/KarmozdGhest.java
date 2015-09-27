
package com.bitarts.parsian.model.karmozd;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;

import javax.persistence.*;
import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 4/15/13
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="tbl_karmozd_ghest")
public class KarmozdGhest
{
    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public static enum Type
    {
        TALIGHI,
        ADI,
        DAR_ENTEZAR_SARRESID,
        KARMOZD_BARGASHTI,
        PARDAKHTE_CODE_MOVAGHATI,
        TAGHIR_CODE_DAEM,
        TAGHIRAT,
        CODE_MOVAGHAT,
        VOSULI,
        POOSHESH_EZAFI,
        SENIOR,
        TADILI,
        KARMOZD_TASHVIGHI_VOSULI
    }

    public static enum Tarefe
    {
        NAVADO_YEK,
        YEK_YEK_NAVAD_DO,
        YEK_SE_NAVAD_DO,
        YEK_CHAHAR_NAVAD_DO
    }

    @Column(name = "tarefe")
    @Enumerated(EnumType.STRING)
    private Tarefe tarefe;

    @Column(name = "type_karmozd")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "percent_payment")
//    private String percentPayment;

    @Column(name = "karmozd_amount")
    private Long karmozdAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ghest_id")
    private Ghest ghest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="karmozd_id")
    private Karmozd karmozd;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "rel_karmozdGhest_karmozd", joinColumns = {@JoinColumn(name = "karmozd_ghest_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "karmozd_id", referencedColumnName = "karmozd_id")})
//    private List<Karmozd> karmozdList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credebit_id")
    private Credebit credebit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karmozd_namayande_id")
    private KarmozdNamayande karmozdNamayande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khate_sanad_id")
    private KhateSanad khateSanad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "black_list_id")
    private BlackList blackList;

    @Column(name = "is_paid")
    private Boolean wasPaid;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdBargashti;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdCodeMovaghat;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdTaghirCodeDaem;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdSenior;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdTadili;

    @OneToOne(fetch = FetchType.LAZY)
    private KarmozdGhest karmozdTaghirat;

    @Column(name = "sale_bimeei")
    private Integer saleBimeei;

    @Column(name = "description")
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    private Long namayandeId;

    public KarmozdGhest getKarmozdTadili()
    {
        return karmozdTadili;
    }

    public KarmozdGhest getKarmozdTaghirCodeDaem()
    {
        return karmozdTaghirCodeDaem;
    }

    public void setKarmozdTaghirCodeDaem(KarmozdGhest karmozdTaghirCodeDaem)
    {
        this.karmozdTaghirCodeDaem = karmozdTaghirCodeDaem;
    }

    public void setKarmozdTadili(KarmozdGhest karmozdTadili)
    {
        this.karmozdTadili = karmozdTadili;
    }

    public KarmozdGhest getKarmozdTaghirat()
    {
        return karmozdTaghirat;
    }

    public void setKarmozdTaghirat(KarmozdGhest karmozdTaghirat)
    {
        this.karmozdTaghirat = karmozdTaghirat;
    }

    public KarmozdGhest getKarmozdCodeMovaghat()
    {
        return karmozdCodeMovaghat;
    }

    public void setKarmozdCodeMovaghat(KarmozdGhest karmozdCodeMovaghat)
    {
        this.karmozdCodeMovaghat = karmozdCodeMovaghat;
    }

    public KarmozdGhest getKarmozdSenior()
    {
        return karmozdSenior;
    }

    public void setKarmozdSenior(KarmozdGhest karmozdSenior)
    {
        this.karmozdSenior = karmozdSenior;
    }

    public KarmozdGhest()
    {

    }

    //karmozdGhest constructor for ghest yekja. . .
    public KarmozdGhest(Credebit credebit, Karmozd karmozd, Ghest ghest, Tarefe tarefe)
    {
        this.credebit = credebit;
        this.karmozd = karmozd;
        this.ghest = ghest;
        this.tarefe = tarefe;
    }

    //karmozdGhest constructor for ghest gheyre yekja. . .
    public KarmozdGhest(KhateSanad khateSanad,Karmozd karmozd, Ghest ghest, Tarefe tarefe)
    {
        this.khateSanad = khateSanad;
        this.karmozd = karmozd;
        this.ghest = ghest;
        this.tarefe = tarefe;
    }


    public Integer getSaleBimeei()
    {
        return saleBimeei;
    }

    public void setSaleBimeei(Integer saleBimeei)
    {
        this.saleBimeei = saleBimeei;
    }

    public KarmozdGhest getKarmozdBargashti()
    {
        return karmozdBargashti;
    }

    public void setKarmozdBargashti(KarmozdGhest karmozdBargashti)
    {
        this.karmozdBargashti = karmozdBargashti;
    }

    public Boolean getWasPaid()
    {
        return wasPaid;
    }

    public void setWasPaid(Boolean wasPaid)
    {
        this.wasPaid = wasPaid;
    }

    public String getTarefeFarsi()
    {
        if(tarefe==null) return "";
        switch (tarefe)
        {
            case NAVADO_YEK : return "تعرفه 01/01/1391";
            case YEK_YEK_NAVAD_DO : return "تعرفه 01/01/1392";
            case YEK_SE_NAVAD_DO :return "تعرفه 01/03/1392";
            case YEK_CHAHAR_NAVAD_DO :  return "تعرفه 01/04/1392";
        }
        return null;
    }

    public Tarefe getTarefe()
    {
        return tarefe;
    }

    public void setTarefe(Tarefe tarefe)
    {
        this.tarefe = tarefe;
    }

    public BlackList getBlackList()
    {
        return blackList;
    }

    public String getHasBlackList()
    {
        return blackList!=null?"بلی":"خیر";
    }

    public void setBlackList(BlackList blackList)
    {
        this.blackList = blackList;
    }

    public KhateSanad getKhateSanad()
    {
        return khateSanad;
    }

    public void setKhateSanad(KhateSanad khateSanad)
    {
        this.khateSanad = khateSanad;
    }

    public KarmozdNamayande getKarmozdNamayande()
    {
        return karmozdNamayande;
    }

    public void setKarmozdNamayande(KarmozdNamayande karmozdNamayande)
    {
        this.karmozdNamayande = karmozdNamayande;
    }

//    public String getPercentPayment()
//    {
//        return percentPayment;
//    }
//
//    public void setPercentPayment(String percentPayment)
//    {
//        this.percentPayment = percentPayment;
//    }

    public Long getKarmozdAmount()
    {
        return karmozdAmount;
    }

    public String getKarmozdAMountString()
     {

        return getKarmozdAmount()==null? "0" : NumberFormat.getInstance().format(getKarmozdAmount());
    }

    public void setKarmozdAmount(Long karmozdAmount)
    {
        this.karmozdAmount = karmozdAmount;
    }

    public Credebit getCredebit()
    {
        return credebit;
    }

    public void setCredebit(Credebit credebit)
    {
        this.credebit = credebit;
    }

    public Ghest getGhest()
    {
        return ghest;
    }

    public void setGhest(Ghest ghest)
    {
        this.ghest = ghest;
    }

    public Karmozd getKarmozd()
    {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd)
    {
        this.karmozd = karmozd;
    }


//    public List<Karmozd> getKarmozdList()
//    {
//        return karmozdList;
//    }
//
//    public void setKarmozdList(List<Karmozd> karmozdList)
//    {
//        this.karmozdList = karmozdList;
//    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type=type;
    }

    public String getTypeFa()
    {
        if (this.type==null) return "";
        else
        {
            if(this.type.equals(Type.PARDAKHTE_CODE_MOVAGHATI)) return "پرداخت کد موقت";

            if(this.type.equals(Type.CODE_MOVAGHAT)) return "کد موقت";

            if(this.type.equals(Type.VOSULI)) return "وصولی";

            if(this.type.equals(Type.POOSHESH_EZAFI)) return "پوشش های اضافی";

            if(this.type.equals(Type.TAGHIRAT)) return "الحاقیه تغییر";

            if(this.type.equals(Type.ADI)) return "عادی";

            if(this.type.equals(Type.DAR_ENTEZAR_SARRESID)) return "در انتظار سر رسید";

            if(this.type.equals(Type.SENIOR)) return "نماینده ارشد";

            if(this.type.equals(Type.KARMOZD_BARGASHTI)) return "کارمزد برگشتی";

            if(this.type.equals(Type.TADILI)) return "تعدیلی";

            if(this.type.equals(Type.TALIGHI)) return "تعلیقی";

            if(this.type.equals(Type.KARMOZD_TASHVIGHI_VOSULI)) return "کارمزد تشویقی وصولی";

            if(this.type.equals(Type.TAGHIR_CODE_DAEM)) return "تغییر کد نمایندگی دائم به دائم";

            return "";
         }
    }



}
