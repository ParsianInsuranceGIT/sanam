package com.bitarts.parsian.model.pishnahad;

import com.bitarts.parsian.model.Azmayesh;
import com.bitarts.parsian.model.Clinic;
import com.bitarts.parsian.model.HazinePezeshki;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 7/26/11
 * Time: 10:47 PM
 */
@Entity
@Table(name = "tbl_moarefiname")
public class Moarefiname implements java.io.Serializable {


    public static enum Vaziat {
        DAR_JARYAN, BATEL_SHODE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "moarefiname_id")
    private Integer id;
    @Column(name = "moarefiname_clinicName")
    private String clinicNameSayer;
    @Column(name = "moarefiname_code")
    private String code;
    @Column(name = "tarikhe_sodur")
    private String tarikhSodur;
    @Column(name = "vaziat")
    @Enumerated(EnumType.STRING)
    private Vaziat vaziat;
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_moarefiname_azemayesh", joinColumns = {@JoinColumn(name = "moarefiname_id", referencedColumnName = "moarefiname_id")}, inverseJoinColumns = {@JoinColumn(name = "azmayesh_id", referencedColumnName = "azmayesh_id")})
    private List<Azmayesh> azmayeshList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pishnahad_id")
    private Pishnehad pishnehad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darkhast_taghir_id")
    private DarkhastTaghirat darkhastTaghirat;
    @OneToOne(mappedBy = "moarefiname", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HazinePezeshki hazinePezeshki;

    @Column(name = "bimeshode_name")
    private String bimeShodeName;
    @Column(name = "bimeshode_shomare_sh")
    private String bimeShodeShomareSh;
    @Column(name = "bimeshode_tarikh_tavallod")
    private String bimeShodeTarikhTavallod;
    @Column(name = "bimeshode_code_melli")
    private String bimeShodeCodeMelli;
    @Column(name = "bimeshode_name_pedar")
    private String bimeShodeNamePedar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarikhSodur() {
        return tarikhSodur;
    }

    public void setTarikhSodur(String tarikhSodur) {
        this.tarikhSodur = tarikhSodur;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<Azmayesh> getAzmayeshList() {
        return azmayeshList;
    }

    public void setAzmayeshList(List<Azmayesh> azmayeshList) {
        this.azmayeshList = azmayeshList;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public String getClinicNameSayer() {
        return clinicNameSayer;
    }

    public void setClinicNameSayer(String clinicNameSayer) {
        this.clinicNameSayer = clinicNameSayer;
    }

    public Vaziat getVaziat() {
        return vaziat;
    }

    public void setVaziat(Vaziat vaziat) {
        this.vaziat = vaziat;
    }

    public HazinePezeshki getHazinePezeshki() {
        return hazinePezeshki;
    }

    public void setHazinePezeshki(HazinePezeshki hazinePezeshki) {
        this.hazinePezeshki = hazinePezeshki;
    }

    public String vaziatFarsi() {
        switch (vaziat) {
            case BATEL_SHODE:
                return "باطل شده";
            case DAR_JARYAN:
                return "در جریان";
            default:
                return "";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBimeShodeName() {
        if(bimeShodeName == null) return pishnehad.getBimeShode().getShakhs().getFullName();
        return bimeShodeName;
    }

    public void setBimeShodeName(String bimeShodeName) {
        this.bimeShodeName = bimeShodeName;
    }

    public String getBimeShodeShomareSh() {
        if(bimeShodeShomareSh == null) return pishnehad.getBimeShode().getShakhs().getShomareShenasnameh();
        return bimeShodeShomareSh;
    }

    public void setBimeShodeShomareSh(String bimeShodeShomareSh) {
        this.bimeShodeShomareSh = bimeShodeShomareSh;
    }

    public String getBimeShodeTarikhTavallod() {
        if(bimeShodeTarikhTavallod == null) return pishnehad.getBimeShode().getShakhs().getTarikheTavallod();
        return bimeShodeTarikhTavallod;
    }

    public void setBimeShodeTarikhTavallod(String bimeShodeTarikhTavallod) {
        this.bimeShodeTarikhTavallod = bimeShodeTarikhTavallod;
    }

    public String getBimeShodeCodeMelli() {
        if(bimeShodeCodeMelli == null) return pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi();
        return bimeShodeCodeMelli;
    }

    public void setBimeShodeCodeMelli(String bimeShodeCodeMelli) {
        this.bimeShodeCodeMelli = bimeShodeCodeMelli;
    }

    public String getBimeShodeNamePedar() {
        if(bimeShodeNamePedar == null) return pishnehad.getBimeShode().getShakhs().getNamePedar();
        return bimeShodeNamePedar;
    }

    public void setBimeShodeNamePedar(String bimeShodeNamePedar) {
        this.bimeShodeNamePedar = bimeShodeNamePedar;
    }
}
