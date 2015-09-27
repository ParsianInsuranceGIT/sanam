package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Bimename;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_bimeGozar")
public class BimeGozar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "daramade_mahiane")
    private Long daramadeMahiane;
    @Column(name = "nesbat_ba_bimeShode")
    private String nesbatBabimeShode;
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "shakhs_id")
    private Shakhs shakhs;
    @OneToMany(mappedBy = "bimeGozar", fetch = FetchType.LAZY)
    private Set<Pishnehad> pishnehadSet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private User userKartabl;

    public User getUserKartabl()
    {
        return userKartabl;
    }

    public void setUserKartabl(User userKartabl)
    {
        this.userKartabl = userKartabl;
    }

    public Set<Pishnehad> getPishnehadSet()
    {
        return pishnehadSet;
    }

    public void setPishnehadSet(Set<Pishnehad> pishnehadSet)
    {
        this.pishnehadSet = pishnehadSet;
    }

    public BimeGozar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDaramadeMahiane() {
        return daramadeMahiane;
    }

    public void setDaramadeMahiane(Long daramadeMahiane) {
        this.daramadeMahiane = daramadeMahiane;
    }

    public String getNesbatBabimeShode() {
        return nesbatBabimeShode;
    }

    public void setNesbatBabimeShode(String nesbatBabimeShode) {
        this.nesbatBabimeShode = nesbatBabimeShode;
    }

    public Shakhs getShakhs() {
        return shakhs;
    }

    public void setShakhs(Shakhs shakhs) {
        this.shakhs = shakhs;
    }

    public List<Bimename> getBimenameList()
    {
        List<Bimename> bimenameList=new ArrayList<Bimename>();

        Iterator<Pishnehad> pishnehadIterator= getPishnehadSet().iterator();
        while(pishnehadIterator.hasNext())
        {
            Pishnehad p=pishnehadIterator.next();
            if(p.getBimename()!=null)
                bimenameList.add(p.getBimename());
        }
        return bimenameList;
    }



}