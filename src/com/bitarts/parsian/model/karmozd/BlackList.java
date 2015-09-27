package com.bitarts.parsian.model.karmozd;

import com.bitarts.parsian.model.bimename.Bimename;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 5/16/13
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_black_list")
public class BlackList
{
    public static enum Type
    {
        KARMOZD_VOSULI,
        KARMOZD_PARDAKHTI
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "blackList")
    private List<KarmozdGhest> karmozdGhestList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bimename_id")
    private Bimename bimename;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public String getTypeFarsi()
    {
        if (this.type.equals(Type.KARMOZD_VOSULI))
            return "لیست سیاه کارمزدهای وصولی";
        return "لیست سیاه کارمزدهای پرداختی";
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public Bimename getBimename()
    {
        return bimename;
    }

    public void setBimename(Bimename bimename)
    {
        this.bimename = bimename;
    }

    public List<KarmozdGhest> getKarmozdGhestList()
    {
        return karmozdGhestList;
    }

    public void setKarmozdGhestList(List<KarmozdGhest> karmozdGhestList)
    {
        this.karmozdGhestList = karmozdGhestList;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
