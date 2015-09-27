package com.bitarts.parsian.model;

import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.viewModel.PishnehadDuplicationRules;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/18/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="tbl_mafadegharardad")
public class MafadeGharardad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "mafadeGharardad", cascade = CascadeType.REMOVE)
    private Gharardad gharardad;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "template_pishnehad_id")
    private Pishnehad templatePishnehad;

    @Column(name = "bimegozar_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource bimegozarSource;

    @Column(name = "bimeshode_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource bimeshodeSource;

    @Column(name = "bimename_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource bimenameSource;

    @Column(name = "soalate_omoomi_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource soalateOmoomiSource;

    @Column(name = "salamate_bimeshode_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource vaziateSalamateBimeShodeSource;

    @Column(name = "salamate_khanevade_source")
    @Enumerated(EnumType.ORDINAL)
    private PishnehadDuplicationRules.PishnehadCopySource vaziateSalamateKhanevadeSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gharardad getGharardad() {
        return gharardad;
    }

    public void setGharardad(Gharardad gharardad) {
        this.gharardad = gharardad;
    }

    public Pishnehad getTemplatePishnehad() {
        return templatePishnehad;
    }

    public void setTemplatePishnehad(Pishnehad templatePishnehad) {
        this.templatePishnehad = templatePishnehad;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getBimegozarSource() {
        return bimegozarSource;
    }

    public void setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource bimegozarSource) {
        this.bimegozarSource = bimegozarSource;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getBimeshodeSource() {
        return bimeshodeSource;
    }

    public void setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource bimeshodeSource) {
        this.bimeshodeSource = bimeshodeSource;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getBimenameSource() {
        return bimenameSource;
    }

    public void setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource bimenameSource) {
        this.bimenameSource = bimenameSource;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getSoalateOmoomiSource() {
        return soalateOmoomiSource;
    }

    public void setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource soalateOmoomiSource) {
        this.soalateOmoomiSource = soalateOmoomiSource;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getVaziateSalamateBimeShodeSource() {
        return vaziateSalamateBimeShodeSource;
    }

    public void setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource vaziateSalamateBimeShodeSource) {
        this.vaziateSalamateBimeShodeSource = vaziateSalamateBimeShodeSource;
    }

    public PishnehadDuplicationRules.PishnehadCopySource getVaziateSalamateKhanevadeSource() {
        return vaziateSalamateKhanevadeSource;
    }

    public void setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource vaziateSalamateKhanevadeSource) {
        this.vaziateSalamateKhanevadeSource = vaziateSalamateKhanevadeSource;
    }
}
