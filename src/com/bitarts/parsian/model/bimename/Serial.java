package com.bitarts.parsian.model.bimename;


import javax.persistence.*;


/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 2/18/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_SERIAL")
public class Serial
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SERIAL_ID")
    private Integer id;

    @Column(name="SHOMARE_SERIAL")
    private Long shomareSerial;

    @Column(name="VAZIAT_SERIAL")
    @Enumerated(EnumType.STRING)
    private VaziatSerial vaziatSerial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DASTE_SERIAL_ID" )
    private DasteSerial dasteSerial;

    @Column(name="TARIKHE_EBTAL")
    private String tarikhEbtal;

    @Column(name="ELATE_EBTAL")
    @Enumerated(EnumType.STRING)
    private ElateEbtal elateEbtal;

    @Column(name="TOZIHAT")
    private String tozihat;
    //other fields. . .

    @OneToOne(mappedBy ="serialPrint" , fetch = FetchType.LAZY)
    private Bimename bimename;


    public static enum ElateEbtal
    {
        ESHTEBAH_DAR_SABT,
        ENTEGHAL_BE_NAMAYANDEGIE_DIGAR,
        TAGHIRE_NOE_DAFTARCHE,
        KHARABI_KAGHAZ_HENGAME_CHAP,
        SODURE_BIMENAME_ALMOSANNA,
        KEYFIAT_NAMONASEBE_CHAP,
        MAKHDOSH_BODAN_KAGHAZ_GHABL_AZ_CHAP,
        MAFGHOD_SHODANE_BIMENAME
    }

    public static enum VaziatSerial
    {
        FAAL, EBTAL_SHODE
    }

    public Bimename getBimename()
    {
        return bimename;
    }

    public void setBimename(Bimename bimename)
    {
        this.bimename = bimename;
    }

    public String getTarikhEbtal() {
        return tarikhEbtal;
    }

    public void setTarikhEbtal(String tarikhEbtal) {
        this.tarikhEbtal = tarikhEbtal;
    }

    public ElateEbtal getElateEbtal() {
        return elateEbtal;
    }

    public void setElateEbtal(ElateEbtal elateEbtal) {
        this.elateEbtal = elateEbtal;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public DasteSerial getDasteSerial()
    {
        return dasteSerial;
    }

    public void setDasteSerial(DasteSerial dasteSerial)
    {
        this.dasteSerial = dasteSerial;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getShomareSerial()
    {
        return shomareSerial;
    }

    public void setShomareSerial(Long shomareSerial)
    {
        this.shomareSerial = shomareSerial;
    }

    public VaziatSerial getVaziatSerial()
    {
        return vaziatSerial;
    }

    public void setVaziatSerial(VaziatSerial vaziatSerial)
    {
        this.vaziatSerial = vaziatSerial;
    }
}
