package com.bitarts.parsian.model.bimename;

import com.bitarts.parsian.model.MafadeGharardad;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.UploadedFile;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:07 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_gharardad")
public class Gharardad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_time")
    private Long createdTime;
    @OneToMany(mappedBy = "gharardad", cascade = CascadeType.ALL)
    private List<Pishnehad> pishnehadList;
    @Column(name = "c_shomare")
    private String shomare;
    @Column(name = "c_createdDate")
    private String createdDate;
    @JoinColumn(name = "c_ostan")
    @ManyToOne(fetch = FetchType.LAZY)
    private City ostan;
    @JoinColumn(name = "c_shahr")
    @ManyToOne(fetch = FetchType.LAZY)
    private City shahr;
    @JoinColumn(name = "c_userCreator")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userCreator;
    @Column(name = "c_fileId")
    private Long fileId;
    @Column(name = "c_fishFileId")
    private Long fishFileId;
    @OneToMany(mappedBy = "gharardad", cascade = CascadeType.ALL)
    private List<GharardadTozihat> tozihat;

    @Column(name = "c_hesab_shomare")
    private String hesab_shomare;
    @Column(name = "c_hesab_sheba")
    private String hesab_sheba;
    @Column(name = "c_hesab_shobe")
    private String hesab_shobe;
    @Column(name = "c_hesab_bank")
    private String hesab_bank;
    @Column(name = "c_hesab_kodShobe")
    private String hesab_kodShobe;
    @Column(name = "c_hesab_name")
    private String hesab_name;
    @Column(name = "c_nameSherkat")
    private String nameSherkat;
    @Column(name = "c_shomareSabt")
    private String shomareSabt;
    @Column(name = "c_tarikhSabt")
    private String tarikhSabt;
    @Column(name = "c_shomareRuzname")
    private String shomareRuzname;
    @Column(name = "c_neshani")
    private String neshani;
    @Column(name = "c_telphone")
    private String telphone;
    @Column(name = "c_fax")
    private String fax;
    @ManyToOne(fetch = FetchType.LAZY)
    Namayande namayande;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Tarh tarh;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gharardad", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private List<UploadedFile> uploadedFiles;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mafad_gharardad_id")
    private MafadeGharardad mafadeGharardad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public City getOstan() {
        return ostan;
    }

    public void setOstan(City ostan) {
        this.ostan = ostan;
    }

    public City getShahr() {
        return shahr;
    }

    public void setShahr(City shahr) {
        this.shahr = shahr;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFishFileId() {
        return fishFileId;
    }

    public void setFishFileId(Long fishFileId) {
        this.fishFileId = fishFileId;
    }

    public List<GharardadTozihat> getTozihat() {
        return tozihat;
    }

    public void setTozihat(List<GharardadTozihat> tozihat) {
        this.tozihat = tozihat;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public List<Pishnehad> getPishnehadList() {
        return pishnehadList;
    }

    public void setPishnehadList(List<Pishnehad> pishnehadList) {
        this.pishnehadList = pishnehadList;
    }

    public String getNameSherkat() {
        return nameSherkat;
    }

    public String getShomareSabt() {
        return shomareSabt;
    }

    public String getNameNamayande() {
        for (Pishnehad p : pishnehadList) {
            if (p.getValid())
                return p.getNamayande().getName();
        }
        return "";
    }

    public String getKodeNamayande() {
        for (Pishnehad p : pishnehadList) {
            if (p.getValid())
                return p.getNamayande().getKodeNamayandeKargozar();
        }
        return "";
    }

    public String getHesab_shomare() {
        return hesab_shomare;
    }

    public void setHesab_shomare(String hesab_shomare) {
        this.hesab_shomare = hesab_shomare;
    }

    public String getHesab_sheba() {
        return hesab_sheba;
    }

    public void setHesab_sheba(String hesab_sheba) {
        this.hesab_sheba = hesab_sheba;
    }

    public String getHesab_shobe() {
        return hesab_shobe;
    }

    public void setHesab_shobe(String hesab_shobe) {
        this.hesab_shobe = hesab_shobe;
    }

    public String getHesab_bank() {
        return hesab_bank;
    }

    public void setHesab_bank(String hesab_bank) {
        this.hesab_bank = hesab_bank;
    }

    public String getHesab_kodShobe() {
        return hesab_kodShobe;
    }

    public void setHesab_kodShobe(String hesab_kodShobe) {
        this.hesab_kodShobe = hesab_kodShobe;
    }

    public String getHesab_name() {
        return hesab_name;
    }

    public void setHesab_name(String hesab_name) {
        this.hesab_name = hesab_name;
    }

    public String getTarikhSabt() {
        return tarikhSabt;
    }

    public void setTarikhSabt(String tarikhSabt) {
        this.tarikhSabt = tarikhSabt;
    }

    public String getShomareRuzname() {
        return shomareRuzname;
    }

    public void setShomareRuzname(String shomareRuzname) {
        this.shomareRuzname = shomareRuzname;
    }

    public String getNeshani() {
        return neshani;
    }

    public void setNeshani(String neshani) {
        this.neshani = neshani;
    }


    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public void setNameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
    }

    public void setShomareSabt(String shomareSabt) {
        this.shomareSabt = shomareSabt;
    }

    public List<Bimename> getBimenameList() {
        List<Bimename> list = new LinkedList<Bimename>();
        if (pishnehadList != null)
            for (Pishnehad p : pishnehadList)
//                if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes"))
                  if (p.getBimename() != null)
                    list.add(p.getBimename());

        return list;
    }

    public List<Credebit> getCredebitList() {
        List<Credebit> list = new LinkedList<Credebit>();
        if (pishnehadList != null)
            for (Pishnehad p : pishnehadList)
                for (Credebit c : p.getCredebits())
                    for (KhateSanad ks : c.getKhateSanadsBedehi())
                        list.add(ks.getEtebarCredebit());

        return list;
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public MafadeGharardad getMafadeGharardad() {
        return mafadeGharardad;
    }

    public void setMafadeGharardad(MafadeGharardad mafadeGharardad) {
        this.mafadeGharardad = mafadeGharardad;
    }
}
