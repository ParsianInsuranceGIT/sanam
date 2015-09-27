package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 2/17/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_DASTE_SERIAL")
public class DasteSerial
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DASTE_SERIAL_ID")
    private Integer id;

    @Column(name="KODE_DASTE")
    private Integer kodeDaste;

    @Column(name="VAZIAT_DASTE")
    @Enumerated(EnumType.STRING)
    private VaziateDaste vaziateDaste;

    @Column(name="TARIKH_SABT")
    private String tarikheSabt;

    @OneToMany(mappedBy = "dasteSerial", fetch = FetchType.LAZY)
    private List<Serial> serials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VAHEDESODUR")
    private  Namayande vahedeSodur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BAZARYAB")
    private User bazaryab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Namayandegi")
    private Namayande namayandegi;

    @Column(name="BIMENAME_TYPE")
    private String bimenameType;

    @Column(name="MizaneJaBeJayi")
    private Integer mizaneJabejayi;


    public static enum VaziateDaste
    {
        FAAL, GHEYRE_FAAL, BASTE_SHODE
    }

    public Namayande getVahedeSodur()
    {
        return vahedeSodur;
    }

    public void setVahedeSodur(Namayande vahedeSodur)
    {
        this.vahedeSodur = vahedeSodur;
    }

    public User getBazaryab()
    {
        return bazaryab;
    }

    public void setBazaryab(User bazaryab)
    {
        this.bazaryab = bazaryab;
    }

    public Namayande getNamayandegi() {
        return namayandegi;
    }

    public void setNamayandegi(Namayande namayandegi) {
        this.namayandegi = namayandegi;
    }

    public String getBimenameType() {
        return bimenameType;
    }

    public void setBimenameType(String bimenameType) {
        this.bimenameType = bimenameType;
    }

    public Integer getMizaneJabejayi() {
        return mizaneJabejayi;
    }

    public void setMizaneJabejayi(Integer mizaneJabejayi) {
        this.mizaneJabejayi = mizaneJabejayi;
    }

    public List<Serial> getSerials()
    {
        return serials;
    }

    public void setSerials(List<Serial> serials)
    {
        this.serials = serials;
    }

    public String getTarikheSabt()
    {
        return tarikheSabt;
    }

    public void setTarikheSabt(String tarikheSabt)
    {
        this.tarikheSabt = tarikheSabt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getKodeDaste()
    {
        return kodeDaste;
    }

    public void setKodeDaste(Integer kodeDaste)
    {
        this.kodeDaste = kodeDaste;
    }

    public String getVaziateDasteFarsi()
    {
        if (vaziateDaste.equals(VaziateDaste.GHEYRE_FAAL))
            return "غیرفعال";
        else if(vaziateDaste.equals(VaziateDaste.BASTE_SHODE))
            return "بسته شده";
        else //if(vaziateDaste.equals(VaziateDaste.FAAL))
            return "فعال";
    }

    public VaziateDaste getVaziateDaste()
    {
        return vaziateDaste;
    }

    public void setVaziateDaste(VaziateDaste vaziateDaste)
    {
        this.vaziateDaste = vaziateDaste;
    }


    public Serial getSerialFirst()
    {
        if(serials==null || serials.size()==0)
        {
            return null;
        }
        else
        {
            Serial rtnSerial=serials.get(0);
            for(Serial serial:serials)
            {
                if(serial.getShomareSerial()<rtnSerial.getShomareSerial())
                {
                    rtnSerial=serial;
                }
            }
            return rtnSerial;
        }
    }

    public Serial getSerialLast()
    {
        if(serials.equals(null))
        {
            return null;
        }
        else
        {
            Serial rtnSerial=serials.get(0);
            for(Serial serial:serials)
            {
                if(serial.getShomareSerial()>rtnSerial.getShomareSerial())
                {
                    rtnSerial=serial;
                }
            }
            return rtnSerial;
        }
    }

    public int getCountSerials()
    {
        return serials.size();
    }

    public int getCountSerialsEbtali()
    {
        int count=0;
        for(Serial serial : serials)
        {
            if (serial.getVaziatSerial().equals(Serial.VaziatSerial.EBTAL_SHODE))
            {
               count++;
            }
        }
        return count;
    }

    public int getSerialsEstefadeNashode()
    {
        int count=0;
        for(Serial serial : serials)
        {
            if (serial.getBimename()==null)
               count++;
        }
        return count;
    }
}
