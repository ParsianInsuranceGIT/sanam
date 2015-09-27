package com.bitarts.parsian.model.constantItems;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_tarh")
public class Tarh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "hijdah_darsad")
    private Boolean hijdahDarsad;
    @OneToMany(mappedBy = "tarh",fetch = FetchType.LAZY)
    private Set<Constants> tarhParams;
    @Column(name="is_show_for_estelam")
    private Boolean showForEstelam;


    public Boolean getShowForEstelam()
    {
        if (showForEstelam == null) return false;
        return showForEstelam;
    }

    public void setShowForEstelam(Boolean showForEstelam)
    {
        this.showForEstelam = showForEstelam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Constants> getTarhParams() {
        return tarhParams;
    }

    public void setTarhParams(Set<Constants> tarhParams) {
        this.tarhParams = tarhParams;
    }

    public Boolean getHijdahDarsad() {
        return hijdahDarsad;
    }

    public void setHijdahDarsad(Boolean hijdahDarsad) {
        this.hijdahDarsad = hijdahDarsad;
    }
}
