package com.bitarts.parsian.model.asnadeSodor;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_print_daftarche")
public class LogPrintDaftarche
{
    public static enum Type
    {
        PARSIAN,TEJARAT
    }

    public static enum Genre
    {
        PRINT,VIEW
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "print_date")
    private String printDate;

    @Column(name = "print_time")
    private String printTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private GhestBandi ghestBandi;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public LogPrintDaftarche()
    {

    }

    public LogPrintDaftarche(User user, GhestBandi ghestBandi, Type type, Genre genre)
    {
        this.printDate = DateUtil.getCurrentDate();
        this.printTime = DateUtil.getCurrentTime();
        this.user = user;
        this.ghestBandi = ghestBandi;
        this.type=type;
        this.genre = genre;
    }

    public LogPrintDaftarche(String printDate, String printTime, User user, Type type, Genre genre)
    {
        this.printDate = printDate!=null?printDate : DateUtil.getCurrentDate();
        this.printTime = printTime!=null? printDate : DateUtil.getCurrentTime();;
        this.user = user;
        this.type = type;
        this.genre=genre;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPrintDate()
    {
        return printDate;
    }

    public void setPrintDate(String printDate)
    {
        this.printDate = printDate;
    }

    public String getPrintTime()
    {
        return printTime;
    }

    public void setPrintTime(String printTime)
    {
        this.printTime = printTime;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
        switch (this.type)
        {
            case PARSIAN: return "پارسیان";
            case TEJARAT: return "تجارت";
            default: return "";
        }
    }

    public GhestBandi getGhestBandi()
    {
        return ghestBandi;
    }

    public void setGhestBandi(GhestBandi ghestBandi)
    {
        this.ghestBandi = ghestBandi;
    }

    public String getGenreFa()
    {
        if(getGenre().equals(Genre.PRINT))
            return "چاپ";
        else//if(getGenre().equals(Genre.VIEW))
            return "نمایش";
    }

    public Genre getGenre()
    {
        return genre;
    }

    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }
}
