
package com.bitarts.parsian.model.karmozd;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Ghest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/23/12
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbl_karmozd")
public class Karmozd implements Serializable {

    public static enum Type
    {
        Karmozd_Pardakhti,
        Karmozd_Vosuli,
        Karmozd_Pooshesh_Ezafi,
        Karmozd_Seniors,
        Karmozd_Tashvighi_Vosuli
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "karmozd_id")
    private Long id;
    @Column(name = "createdDate")
    private String createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Namayande namayande;
    @OneToMany(mappedBy = "karmozd")
    private List<Ghest> ghests;

    @Column (name = "onvan")
    private String onvan;
    @Column (name = "description")
    private String description;
    @OneToMany(mappedBy = "karmozd" )
    private Set<KarmozdGhest> karmozdGhestSet;

    @OneToMany(mappedBy = "karmozd")
    private Set<KarmozdTadilat> karmozdTadilatSet;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name="ta_Tarikh_Vosoli")
    private String taTarikhVosoli;

    @Column(name = "az_Tarikh_Vosoli")
    private String azTarikhVosoli;

    @Column(name = "serial")
    private String serial;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "viewForNamayande")
    private String viewForNamayande;

    @Column(name = "step")
    private String step;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    @JoinColumn(name = "karmozd_Seniors_id")
//    private Karmozd karmozdSeniors;
//
//    public Karmozd getKarmozdSeniors()
//    {
//        return karmozdSeniors;
//    }

//    public void setKarmozdSeniors(Karmozd karmozdSeniors)
//    {
//        this.karmozdSeniors = karmozdSeniors;
//    }


    public String getStep()
    {
        return step;
    }

    public void setStep(String step)
    {
        this.step = step;
    }

    public String getViewForNamayande()
    {
        return viewForNamayande;
    }

    public void setViewForNamayande(String viewForNamayande)
    {
        this.viewForNamayande = viewForNamayande;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getTypeFarsi()
    {
        if(this.type!=null)
            if (this.type.equals(Type.Karmozd_Vosuli))
                return "کارمزد وصولی";
            else if (this.type.equals(Type.Karmozd_Pooshesh_Ezafi))
                return  "کارمزد پوشش های اضافه";
            else if (this.type.equals(Type.Karmozd_Seniors))
                return  "کارمزد نمایندگان ارشد";
            else if (this.type.equals(Type.Karmozd_Tashvighi_Vosuli))
                return "کارمزد تشویقی وصولی";
        return "کارمزد پرداختی";

    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }


    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public Set<KarmozdTadilat> getKarmozdTadilatSet()
    {
        return karmozdTadilatSet;
    }

    public void setKarmozdTadilatSet(Set<KarmozdTadilat> karmozdTadilatSet)
    {
        this.karmozdTadilatSet = karmozdTadilatSet;
    }

    public String getAzTarikhVosoli()
    {
        return azTarikhVosoli;
    }

    public void setAzTarikhVosoli(String azTarikhVosoli)
    {
        this.azTarikhVosoli = azTarikhVosoli;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getOnvan()
    {
        return onvan;
    }

    public void setOnvan(String onvan)
    {
        this.onvan = onvan;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<KarmozdGhest> getKarmozdGhestSet()
    {
        return karmozdGhestSet;
    }

    public void setKarmozdGhestSet(Set<KarmozdGhest> karmozdGhestSet)
    {
        this.karmozdGhestSet = karmozdGhestSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }


    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Ghest> getGhests() {
        return ghests;
    }

    public void setGhests(List<Ghest> ghests) {
        this.ghests = ghests;
    }

    public String getTaTarikhVosoli()
    {
        return taTarikhVosoli;
    }

    public void setTaTarikhVosoli(String taTarikhVosoli)
    {
        this.taTarikhVosoli = taTarikhVosoli;
    }
}
