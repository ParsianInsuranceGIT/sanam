package com.bitarts.parsian.viewModel;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/16/12
 * Time: 3:30 PM
 * To change this template use UploadedFile | Settings | UploadedFile Templates.
 */
public class PishnehadDuplicationRules {
    public enum PishnehadCopySource {
        AzTemplate, AzExcel, BeTafkikePishnehad, SherkateTarafeGharardad, SameAsBimeGozar, AllHealthy
    }

    public enum DuplicationSource {
        ElhaghieEslahi, SodorJami
    }

    private PishnehadCopySource bimegozarSource;
    private PishnehadCopySource bimeshodeSource;
    private PishnehadCopySource bimenameSource;
    private PishnehadCopySource soalateOmoomiSource;
    private PishnehadCopySource vaziateSalamateBimeShodeSource;
    private PishnehadCopySource vaziateSalamateKhanevadeSource;
    private DuplicationSource source;

    public PishnehadCopySource getBimegozarSource() {
        return bimegozarSource;
    }

    public void setBimegozarSource(PishnehadCopySource bimegozarSource) {
        this.bimegozarSource = bimegozarSource;
    }

    public PishnehadCopySource getBimeshodeSource() {
        return bimeshodeSource;
    }

    public void setBimeshodeSource(PishnehadCopySource bimeshodeSource) {
        this.bimeshodeSource = bimeshodeSource;
    }

    public PishnehadCopySource getBimenameSource() {
        return bimenameSource;
    }

    public void setBimenameSource(PishnehadCopySource bimenameSource) {
        this.bimenameSource = bimenameSource;
    }

    public PishnehadCopySource getSoalateOmoomiSource() {
        return soalateOmoomiSource;
    }

    public void setSoalateOmoomiSource(PishnehadCopySource soalateOmoomiSource) {
        this.soalateOmoomiSource = soalateOmoomiSource;
    }

    public PishnehadCopySource getVaziateSalamateBimeShodeSource() {
        return vaziateSalamateBimeShodeSource;
    }

    public void setVaziateSalamateBimeShodeSource(PishnehadCopySource vaziateSalamateBimeShodeSource) {
        this.vaziateSalamateBimeShodeSource = vaziateSalamateBimeShodeSource;
    }

    public PishnehadCopySource getVaziateSalamateKhanevadeSource() {
        return vaziateSalamateKhanevadeSource;
    }

    public void setVaziateSalamateKhanevadeSource(PishnehadCopySource vaziateSalamateKhanevadeSource) {
        this.vaziateSalamateKhanevadeSource = vaziateSalamateKhanevadeSource;
    }

    public DuplicationSource getSource() {
        return source;
    }

    public void setSource(DuplicationSource source) {
        this.source = source;
    }
}
