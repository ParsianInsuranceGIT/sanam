package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/20/11
 * Time: 9:53 AM
 */
@Entity
@Table(name = "tbl_zamaem")
public class Zamaem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zamaem_id")
    private Integer id;
    @Column(name="file_enseraf_id")
    private Integer fileEnserafId;
    @Column(name = "file_enseraf_name")
    private String fileEnserafName;
    @Column(name = "file_enseraf_desc", length = 2000)
    private String fileEnserafDescription;
    @Column(name="file_elame_hesab")
    private Integer fileElameHesabId;
    @Column(name = "file_elamhesab_name")
    private String fileElameHesabName;
    @Column(name = "file_elamhesab_desc", length = 2000)
    private String fileElameHesabDescription;
    @Column(name = "file_pishkatbi_id1")
    private Integer filePishKatbiId1;
    @Column(name = "file_pishkatbi_id2")
    private Integer filePishKatbiId2;
    @Column(name = "file_pishkatbi_id3")
    private Integer filePishKatbiId3;
    @Column(name = "file_pishkatbi_id4")
    private Integer filePishKatbiId4;
    @Column(name = "file_pishkatbi_id5")
    private Integer filePishKatbiId5;
    @Column(name = "file_pishkatbi_name1")
    private String filePishKatbiName1;
    @Column(name = "file_pishkatbi_name2")
    private String filePishKatbiName2;
    @Column(name = "file_pishkatbi_name3")
    private String filePishKatbiName3;
    @Column(name = "file_pishkatbi_name4")
    private String filePishKatbiName4;
    @Column(name = "file_pishkatbi_name5")
    private String filePishKatbiName5;

    @Column(name = "file_pishkatbi_desc1", length = 2000)
    private String filePishKatbiDescription1;
    @Column(name = "file_pishkatbi_desc2", length = 2000)
    private String filePishKatbiDescription2;
    @Column(name = "file_pishkatbi_desc3", length = 2000)
    private String filePishKatbiDescription3;
    @Column(name = "file_pishkatbi_desc4", length = 2000)
    private String filePishKatbiDescription4;
    @Column(name = "file_pishkatbi_desc5", length = 2000)
    private String filePishKatbiDescription5;

    @Column(name = "file_pishDigital_id1")
    private Integer filePishDigitalId1;
    @Column(name = "file_pishDigital_id2")
    private Integer filePishDigitalId2;
    @Column(name = "file_pishDigital_id3")
    private Integer filePishDigitalId3;
    @Column(name = "file_pishDigital_id4")
    private Integer filePishDigitalId4;
    @Column(name = "file_pishDigital_id5")
    private Integer filePishDigitalId5;
    @Column(name = "file_pishDigital_name1")
    private String filePishDigitalName1;
    @Column(name = "file_pishDigital_name2")
    private String filePishDigitalName2;
    @Column(name = "file_pishDigital_name3")
    private String filePishDigitalName3;
    @Column(name = "file_pishDigital_name4")
    private String filePishDigitalName4;
    @Column(name = "file_pishDigital_name5")
    private String filePishDigitalName5;

    @Column(name = "file_pishdigital_desc1", length = 2000)
    private String filePishDigitalDescription1;
    @Column(name = "file_pishdigital_desc2", length = 2000)
    private String filePishDigitalDescription2;
    @Column(name = "file_pishdigital_desc3", length = 2000)
    private String filePishDigitalDescription3;
    @Column(name = "file_pishdigital_desc4", length = 2000)
    private String filePishDigitalDescription4;
    @Column(name = "file_pishdigital_desc5", length = 2000)
    private String filePishDigitalDescription5;

    @Column(name = "file_fish_id1")
    private Integer fileFishId1;
    @Column(name = "file_fish_name1")
    private String fileFishName1;
    @Column(name = "file_fish_desc1", length = 2000)
    private String fileFishDescription1;
    @Column(name = "file_fish_id2")
    private Integer fileFishId2;
    @Column(name = "file_fish_name2")
    private String fileFishName2;
    @Column(name = "file_fish_desc2", length = 2000)
    private String fileFishDescription2;
    @Column(name = "file_fish_id3")
    private Integer fileFishId3;
    @Column(name = "file_fish_name3")
    private String fileFishName3;
    @Column(name = "file_fish_desc3", length = 2000)
    private String fileFishDescription3;
    @Column(name = "file_fish_id4")
    private Integer fileFishId4;
    @Column(name = "file_fish_name4")
    private String fileFishName4;
    @Column(name = "file_fish_desc4", length = 2000)
    private String fileFishDescription4;
    @Column(name = "file_fish_id5")
    private Integer fileFishId5;
    @Column(name = "file_fish_name5")
    private String fileFishName5;
    @Column(name = "file_fish_desc5", length = 2000)
    private String fileFishDescription5;


    @Column(name = "file_sayer_id1")
    private Integer fileSayerId1;
    @Column(name = "file_sayer_name1")
    private String fileSayerName1;
    @Column(name = "file_sayer_desc1", length = 2000)
    private String fileSayerDescription1;
    @Column(name = "file_sayer_id2")
    private Integer fileSayerId2;
    @Column(name = "file_sayer_name2")
    private String fileSayerName2;
    @Column(name = "file_sayer_desc2", length = 2000)
    private String fileSayerDescription2;
    @Column(name = "file_sayer_id3")
    private Integer fileSayerId3;
    @Column(name = "file_sayer_name3")
    private String fileSayerName3;
    @Column(name = "file_sayer_desc3", length = 2000)
    private String fileSayerDescription3;
    @Column(name = "file_sayer_id4")
    private Integer fileSayerId4;
    @Column(name = "file_sayer_name4")
    private String fileSayerName4;
    @Column(name = "file_sayer_desc4", length = 2000)
    private String fileSayerDescription4;
    @Column(name = "file_sayer_id5")
    private Integer fileSayerId5;
    @Column(name = "file_sayer_name5")
    private String fileSayerName5;
    @Column(name = "file_sayer_desc5", length = 2000)
    private String fileSayerDescription5;

    @Column(name = "file_moshaverepez_id")
    private Integer fileMoshaverePezeshkId;
    @Column(name = "file_moshaverepez_name")
    private String fileMoshaverePezeshkName;
    @Column(name = "file_moshaverepez_desc", length = 2000)
    private String fileMoshaverePezeshkDesc;
    @Column(name = "file_moshaverepezj_id")
    private Integer fileMoshaverePezeshkJavabId;
    @Column(name = "file_moshaverepezj_name")
    private String fileMoshaverePezeshkJavabName;
    @Column(name = "file_moshaverepezj_desc", length = 2000)
    private String fileMoshaverePezeshkJavabDesc;

    @Column(name = "file_excelkh_id")
    private Integer fileExcelKhanevadeId;
    @Column(name = "file_excelkh_name")
    private String fileExcelKhanevadeName;
    @Column(name = "file_excelkh_desc", length = 2000)
    private String fileExcelKhanevadeDesc;

    @OneToOne(mappedBy="zamaem",fetch = FetchType.LAZY)
    private Pishnehad pishnehad;




    public Zamaem(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public Integer getFilePishKatbiId1() {
        return filePishKatbiId1;
    }

    public void setFilePishKatbiId1(Integer filePishKatbiId1) {
        this.filePishKatbiId1 = filePishKatbiId1;
    }

    public Integer getFilePishKatbiId2() {
        return filePishKatbiId2;
    }

    public void setFilePishKatbiId2(Integer filePishKatbiId2) {
        this.filePishKatbiId2 = filePishKatbiId2;
    }

    public Integer getFilePishKatbiId3() {
        return filePishKatbiId3;
    }

    public void setFilePishKatbiId3(Integer filePishKatbiId3) {
        this.filePishKatbiId3 = filePishKatbiId3;
    }

    public Integer getFilePishKatbiId4() {
        return filePishKatbiId4;
    }

    public void setFilePishKatbiId4(Integer filePishKatbiId4) {
        this.filePishKatbiId4 = filePishKatbiId4;
    }

    public Integer getFilePishKatbiId5() {
        return filePishKatbiId5;
    }

    public void setFilePishKatbiId5(Integer filePishKatbiId5) {
        this.filePishKatbiId5 = filePishKatbiId5;
    }

    public String getFilePishKatbiName1() {
        return filePishKatbiName1;
    }

    public void setFilePishKatbiName1(String filePishKatbiName1) {
        this.filePishKatbiName1 = filePishKatbiName1;
    }

    public String getFilePishKatbiName2() {
        return filePishKatbiName2;
    }

    public void setFilePishKatbiName2(String filePishKatbiName2) {
        this.filePishKatbiName2 = filePishKatbiName2;
    }

    public String getFilePishKatbiName3() {
        return filePishKatbiName3;
    }

    public void setFilePishKatbiName3(String filePishKatbiName3) {
        this.filePishKatbiName3 = filePishKatbiName3;
    }

    public String getFilePishKatbiName4() {
        return filePishKatbiName4;
    }

    public void setFilePishKatbiName4(String filePishKatbiName4) {
        this.filePishKatbiName4 = filePishKatbiName4;
    }

    public String getFilePishKatbiName5() {
        return filePishKatbiName5;
    }

    public void setFilePishKatbiName5(String filePishKatbiName5) {
        this.filePishKatbiName5 = filePishKatbiName5;
    }

    public String getFilePishKatbiDescription1() {
        return filePishKatbiDescription1;
    }

    public void setFilePishKatbiDescription1(String filePishKatbiDescription1) {
        this.filePishKatbiDescription1 = filePishKatbiDescription1;
    }

    public String getFilePishKatbiDescription2() {
        return filePishKatbiDescription2;
    }

    public void setFilePishKatbiDescription2(String filePishKatbiDescription2) {
        this.filePishKatbiDescription2 = filePishKatbiDescription2;
    }

    public String getFilePishKatbiDescription3() {
        return filePishKatbiDescription3;
    }

    public void setFilePishKatbiDescription3(String filePishKatbiDescription3) {
        this.filePishKatbiDescription3 = filePishKatbiDescription3;
    }

    public String getFilePishKatbiDescription4() {
        return filePishKatbiDescription4;
    }

    public void setFilePishKatbiDescription4(String filePishKatbiDescription4) {
        this.filePishKatbiDescription4 = filePishKatbiDescription4;
    }

    public String getFilePishKatbiDescription5() {
        return filePishKatbiDescription5;
    }

    public void setFilePishKatbiDescription5(String filePishKatbiDescription5) {
        this.filePishKatbiDescription5 = filePishKatbiDescription5;
    }

    public Integer getFilePishDigitalId1() {
        return filePishDigitalId1;
    }

    public void setFilePishDigitalId1(Integer filePishDigitalId1) {
        this.filePishDigitalId1 = filePishDigitalId1;
    }

    public Integer getFilePishDigitalId2() {
        return filePishDigitalId2;
    }

    public void setFilePishDigitalId2(Integer filePishDigitalId2) {
        this.filePishDigitalId2 = filePishDigitalId2;
    }

    public Integer getFilePishDigitalId3() {
        return filePishDigitalId3;
    }

    public void setFilePishDigitalId3(Integer filePishDigitalId3) {
        this.filePishDigitalId3 = filePishDigitalId3;
    }

    public Integer getFilePishDigitalId4() {
        return filePishDigitalId4;
    }

    public void setFilePishDigitalId4(Integer filePishDigitalId4) {
        this.filePishDigitalId4 = filePishDigitalId4;
    }

    public Integer getFilePishDigitalId5() {
        return filePishDigitalId5;
    }

    public void setFilePishDigitalId5(Integer filePishDigitalId5) {
        this.filePishDigitalId5 = filePishDigitalId5;
    }

    public String getFilePishDigitalName1() {
        return filePishDigitalName1;
    }

    public void setFilePishDigitalName1(String filePishDigitalName1) {
        this.filePishDigitalName1 = filePishDigitalName1;
    }

    public String getFilePishDigitalName2() {
        return filePishDigitalName2;
    }

    public void setFilePishDigitalName2(String filePishDigitalName2) {
        this.filePishDigitalName2 = filePishDigitalName2;
    }

    public String getFilePishDigitalName3() {
        return filePishDigitalName3;
    }

    public void setFilePishDigitalName3(String filePishDigitalName3) {
        this.filePishDigitalName3 = filePishDigitalName3;
    }

    public String getFilePishDigitalName4() {
        return filePishDigitalName4;
    }

    public void setFilePishDigitalName4(String filePishDigitalName4) {
        this.filePishDigitalName4 = filePishDigitalName4;
    }

    public String getFilePishDigitalName5() {
        return filePishDigitalName5;
    }

    public void setFilePishDigitalName5(String filePishDigitalName5) {
        this.filePishDigitalName5 = filePishDigitalName5;
    }

    public String getFilePishDigitalDescription1() {
        return filePishDigitalDescription1;
    }

    public void setFilePishDigitalDescription1(String filePishDigitalDescription1) {
        this.filePishDigitalDescription1 = filePishDigitalDescription1;
    }

    public String getFilePishDigitalDescription2() {
        return filePishDigitalDescription2;
    }

    public void setFilePishDigitalDescription2(String filePishDigitalDescription2) {
        this.filePishDigitalDescription2 = filePishDigitalDescription2;
    }

    public String getFilePishDigitalDescription3() {
        return filePishDigitalDescription3;
    }

    public void setFilePishDigitalDescription3(String filePishDigitalDescription3) {
        this.filePishDigitalDescription3 = filePishDigitalDescription3;
    }

    public String getFilePishDigitalDescription4() {
        return filePishDigitalDescription4;
    }

    public void setFilePishDigitalDescription4(String filePishDigitalDescription4) {
        this.filePishDigitalDescription4 = filePishDigitalDescription4;
    }

    public String getFilePishDigitalDescription5() {
        return filePishDigitalDescription5;
    }

    public void setFilePishDigitalDescription5(String filePishDigitalDescription5) {
        this.filePishDigitalDescription5 = filePishDigitalDescription5;
    }

    public Integer getFileFishId1() {
        return fileFishId1;
    }

    public void setFileFishId1(Integer fileFishId1) {
        this.fileFishId1 = fileFishId1;
    }

    public String getFileFishName1() {
        return fileFishName1;
    }

    public void setFileFishName1(String fileFishName1) {
        this.fileFishName1 = fileFishName1;
    }

    public String getFileFishDescription1() {
        return fileFishDescription1;
    }

    public void setFileFishDescription1(String fileFishDescription1) {
        this.fileFishDescription1 = fileFishDescription1;
    }

    public Integer getFileFishId2() {
        return fileFishId2;
    }

    public void setFileFishId2(Integer fileFishId2) {
        this.fileFishId2 = fileFishId2;
    }

    public String getFileFishName2() {
        return fileFishName2;
    }

    public void setFileFishName2(String fileFishName2) {
        this.fileFishName2 = fileFishName2;
    }

    public String getFileFishDescription2() {
        return fileFishDescription2;
    }

    public void setFileFishDescription2(String fileFishDescription2) {
        this.fileFishDescription2 = fileFishDescription2;
    }

    public Integer getFileFishId3() {
        return fileFishId3;
    }

    public void setFileFishId3(Integer fileFishId3) {
        this.fileFishId3 = fileFishId3;
    }

    public String getFileFishName3() {
        return fileFishName3;
    }

    public void setFileFishName3(String fileFishName3) {
        this.fileFishName3 = fileFishName3;
    }

    public String getFileFishDescription3() {
        return fileFishDescription3;
    }

    public void setFileFishDescription3(String fileFishDescription3) {
        this.fileFishDescription3 = fileFishDescription3;
    }

    public Integer getFileFishId4() {
        return fileFishId4;
    }

    public void setFileFishId4(Integer fileFishId4) {
        this.fileFishId4 = fileFishId4;
    }

    public String getFileFishName4() {
        return fileFishName4;
    }

    public void setFileFishName4(String fileFishName4) {
        this.fileFishName4 = fileFishName4;
    }

    public String getFileFishDescription4() {
        return fileFishDescription4;
    }

    public void setFileFishDescription4(String fileFishDescription4) {
        this.fileFishDescription4 = fileFishDescription4;
    }

    public Integer getFileFishId5() {
        return fileFishId5;
    }

    public void setFileFishId5(Integer fileFishId5) {
        this.fileFishId5 = fileFishId5;
    }

    public String getFileFishName5() {
        return fileFishName5;
    }

    public void setFileFishName5(String fileFishName5) {
        this.fileFishName5 = fileFishName5;
    }

    public String getFileFishDescription5() {
        return fileFishDescription5;
    }

    public void setFileFishDescription5(String fileFishDescription5) {
        this.fileFishDescription5 = fileFishDescription5;
    }

    public Integer getFileElameHesabId() {
        return fileElameHesabId;
    }

    public void setFileElameHesabId(Integer fileElameHesabId) {
        this.fileElameHesabId = fileElameHesabId;
    }

    public String getFileElameHesabName() {
        return fileElameHesabName;
    }

    public void setFileElameHesabName(String fileElameHesabName) {
        this.fileElameHesabName = fileElameHesabName;
    }

    public String getFileElameHesabDescription() {
        return fileElameHesabDescription;
    }

    public void setFileElameHesabDescription(String fileElameHesabDescription) {
        this.fileElameHesabDescription = fileElameHesabDescription;
    }

    public Integer getFileEnserafId() {
        return fileEnserafId;
    }

    public void setFileEnserafId(Integer fileEnserafId) {
        this.fileEnserafId = fileEnserafId;
    }

    public String getFileEnserafName() {
        return fileEnserafName;
    }

    public void setFileEnserafName(String fileEnserafName) {
        this.fileEnserafName = fileEnserafName;
    }

    public String getFileEnserafDescription() {
        return fileEnserafDescription;
    }

    public void setFileEnserafDescription(String fileEnserafDescription) {
        this.fileEnserafDescription = fileEnserafDescription;
    }
    public Zamaem clone(Zamaem zamaem){
        Zamaem result = new Zamaem();
        result.setFileEnserafDescription(zamaem.getFileEnserafDescription());
        result.setFileEnserafId(zamaem.getFileEnserafId());
        result.setFileEnserafName(zamaem.getFileEnserafName());
        result.setFileElameHesabDescription(zamaem.getFileElameHesabDescription());
        result.setFileElameHesabId(zamaem.getFileElameHesabId());
        result.setFileElameHesabName(zamaem.getFileElameHesabName());
        result.setFileFishDescription1(zamaem.getFileFishDescription1());
        result.setFileFishDescription2(zamaem.getFileFishDescription2());
        result.setFileFishDescription3(zamaem.getFileFishDescription3());
        result.setFileFishDescription4(zamaem.getFileFishDescription4());
        result.setFileFishDescription5(zamaem.getFileFishDescription5());
        result.setFileMoshaverePezeshkId(zamaem.getFileMoshaverePezeshkId());
        result.setFileMoshaverePezeshkName(zamaem.getFileMoshaverePezeshkName());
        result.setFileMoshaverePezeshkDesc(zamaem.getFileMoshaverePezeshkDesc());
        result.setFileMoshaverePezeshkJavabId(zamaem.getFileMoshaverePezeshkJavabId());
        result.setFileMoshaverePezeshkJavabName(zamaem.getFileMoshaverePezeshkJavabName());
        result.setFileMoshaverePezeshkJavabDesc(zamaem.getFileMoshaverePezeshkJavabDesc());
        result.setFileFishId1(zamaem.getFileFishId1());
        result.setFileFishId2(zamaem.getFileFishId2());
        result.setFileFishId3(zamaem.getFileFishId3());
        result.setFileFishId4(zamaem.getFileFishId4());
        result.setFileFishId5(zamaem.getFileFishId5());
        result.setFileFishName1(zamaem.getFileFishName1());
        result.setFileFishName2(zamaem.getFileFishName2());
        result.setFileFishName3(zamaem.getFileFishName3());
        result.setFileFishName4(zamaem.getFileFishName4());
        result.setFileFishName5(zamaem.getFileFishName5());
        result.setFilePishDigitalDescription1(zamaem.getFilePishDigitalDescription1());
        result.setFilePishDigitalDescription2(zamaem.getFilePishDigitalDescription2());
        result.setFilePishDigitalDescription3(zamaem.getFilePishDigitalDescription3());
        result.setFilePishDigitalDescription4(zamaem.getFilePishDigitalDescription4());
        result.setFilePishDigitalDescription5(zamaem.getFilePishDigitalDescription5());
        result.setFilePishDigitalId1(zamaem.getFilePishDigitalId1());
        result.setFilePishDigitalId2(zamaem.getFilePishDigitalId2());
        result.setFilePishDigitalId3(zamaem.getFilePishDigitalId3());
        result.setFilePishDigitalId4(zamaem.getFilePishDigitalId4());
        result.setFilePishDigitalId5(zamaem.getFilePishDigitalId5());
        result.setFilePishDigitalName1(zamaem.getFilePishDigitalName1());
        result.setFilePishDigitalName2(zamaem.getFilePishDigitalName2());
        result.setFilePishDigitalName3(zamaem.getFilePishDigitalName3());
        result.setFilePishDigitalName4(zamaem.getFilePishDigitalName4());
        result.setFilePishDigitalName5(zamaem.getFilePishDigitalName5());
        result.setFilePishKatbiDescription1(zamaem.getFilePishKatbiDescription1());
        result.setFilePishKatbiDescription2(zamaem.getFilePishKatbiDescription2());
        result.setFilePishKatbiDescription3(zamaem.getFilePishKatbiDescription3());
        result.setFilePishKatbiDescription4(zamaem.getFilePishKatbiDescription4());
        result.setFilePishKatbiDescription5(zamaem.getFilePishKatbiDescription5());
        result.setFilePishKatbiId1(zamaem.getFilePishKatbiId1());
        result.setFilePishKatbiId2(zamaem.getFilePishKatbiId2());
        result.setFilePishKatbiId3(zamaem.getFilePishKatbiId3());
        result.setFilePishKatbiId4(zamaem.getFilePishKatbiId4());
        result.setFilePishKatbiId5(zamaem.getFilePishKatbiId5());
        result.setFilePishKatbiName1(zamaem.getFilePishKatbiName1());
        result.setFilePishKatbiName2(zamaem.getFilePishKatbiName2());
        result.setFilePishKatbiName3(zamaem.getFilePishKatbiName3());
        result.setFilePishKatbiName4(zamaem.getFilePishKatbiName4());
        result.setFilePishKatbiName5(zamaem.getFilePishKatbiName5());
        result.setFileSayerDescription1(zamaem.getFileSayerDescription1());
        result.setFileSayerId1(zamaem.getFileSayerId1());
        result.setFileSayerName1(zamaem.getFileSayerName1());
        result.setFileSayerDescription1(zamaem.getFileSayerDescription2());
        result.setFileSayerId2(zamaem.getFileSayerId2());
        result.setFileSayerName2(zamaem.getFileSayerName2());
        result.setFileSayerDescription1(zamaem.getFileSayerDescription3());
        result.setFileSayerId3(zamaem.getFileSayerId3());
        result.setFileSayerName3(zamaem.getFileSayerName3());
        result.setFileSayerDescription1(zamaem.getFileSayerDescription4());
        result.setFileSayerId4(zamaem.getFileSayerId4());
        result.setFileSayerName4(zamaem.getFileSayerName4());
        result.setFileSayerDescription1(zamaem.getFileSayerDescription5());
        result.setFileSayerId5(zamaem.getFileSayerId5());
        result.setFileSayerName5(zamaem.getFileSayerName5());
        result.setPishnehad(zamaem.getPishnehad());
        return result;
    }

    public Integer getFileSayerId1() {
        return fileSayerId1;
    }

    public void setFileSayerId1(Integer fileSayerId1) {
        this.fileSayerId1 = fileSayerId1;
    }

    public String getFileSayerName1() {
        return fileSayerName1;
    }

    public void setFileSayerName1(String fileSayerName1) {
        this.fileSayerName1 = fileSayerName1;
    }

    public String getFileSayerDescription1() {
        return fileSayerDescription1;
    }

    public void setFileSayerDescription1(String fileSayerDescription1) {
        this.fileSayerDescription1 = fileSayerDescription1;
    }

    public Integer getFileSayerId2() {
        return fileSayerId2;
    }

    public void setFileSayerId2(Integer fileSayerId2) {
        this.fileSayerId2 = fileSayerId2;
    }

    public String getFileSayerName2() {
        return fileSayerName2;
    }

    public void setFileSayerName2(String fileSayerName2) {
        this.fileSayerName2 = fileSayerName2;
    }

    public String getFileSayerDescription2() {
        return fileSayerDescription2;
    }

    public void setFileSayerDescription2(String fileSayerDescription2) {
        this.fileSayerDescription2 = fileSayerDescription2;
    }

    public Integer getFileSayerId3() {
        return fileSayerId3;
    }

    public void setFileSayerId3(Integer fileSayerId3) {
        this.fileSayerId3 = fileSayerId3;
    }

    public String getFileSayerName3() {
        return fileSayerName3;
    }

    public void setFileSayerName3(String fileSayerName3) {
        this.fileSayerName3 = fileSayerName3;
    }

    public String getFileSayerDescription3() {
        return fileSayerDescription3;
    }

    public void setFileSayerDescription3(String fileSayerDescription3) {
        this.fileSayerDescription3 = fileSayerDescription3;
    }

    public Integer getFileSayerId4() {
        return fileSayerId4;
    }

    public void setFileSayerId4(Integer fileSayerId4) {
        this.fileSayerId4 = fileSayerId4;
    }

    public String getFileSayerName4() {
        return fileSayerName4;
    }

    public void setFileSayerName4(String fileSayerName4) {
        this.fileSayerName4 = fileSayerName4;
    }

    public String getFileSayerDescription4() {
        return fileSayerDescription4;
    }

    public void setFileSayerDescription4(String fileSayerDescription4) {
        this.fileSayerDescription4 = fileSayerDescription4;
    }

    public Integer getFileSayerId5() {
        return fileSayerId5;
    }

    public void setFileSayerId5(Integer fileSayerId5) {
        this.fileSayerId5 = fileSayerId5;
    }

    public String getFileSayerName5() {
        return fileSayerName5;
    }

    public void setFileSayerName5(String fileSayerName5) {
        this.fileSayerName5 = fileSayerName5;
    }

    public String getFileSayerDescription5() {
        return fileSayerDescription5;
    }

    public void setFileSayerDescription5(String fileSayerDescription5) {
        this.fileSayerDescription5 = fileSayerDescription5;
    }

    public Integer getFileMoshaverePezeshkId() {
        return fileMoshaverePezeshkId;
    }

    public void setFileMoshaverePezeshkId(Integer fileMoshaverePezeshkId) {
        this.fileMoshaverePezeshkId = fileMoshaverePezeshkId;
    }

    public String getFileMoshaverePezeshkName() {
        return fileMoshaverePezeshkName;
    }

    public void setFileMoshaverePezeshkName(String fileMoshaverePezeshkName) {
        this.fileMoshaverePezeshkName = fileMoshaverePezeshkName;
    }

    public String getFileMoshaverePezeshkDesc() {
        return fileMoshaverePezeshkDesc;
    }

    public void setFileMoshaverePezeshkDesc(String fileMoshaverePezeshkDesc) {
        this.fileMoshaverePezeshkDesc = fileMoshaverePezeshkDesc;
    }

    public Integer getFileMoshaverePezeshkJavabId() {
        return fileMoshaverePezeshkJavabId;
    }

    public void setFileMoshaverePezeshkJavabId(Integer fileMoshaverePezeshkJavabId) {
        this.fileMoshaverePezeshkJavabId = fileMoshaverePezeshkJavabId;
    }

    public String getFileMoshaverePezeshkJavabName() {
        return fileMoshaverePezeshkJavabName;
    }

    public void setFileMoshaverePezeshkJavabName(String fileMoshaverePezeshkJavabName) {
        this.fileMoshaverePezeshkJavabName = fileMoshaverePezeshkJavabName;
    }

    public String getFileMoshaverePezeshkJavabDesc() {
        return fileMoshaverePezeshkJavabDesc;
    }

    public void setFileMoshaverePezeshkJavabDesc(String fileMoshaverePezeshkJavabDesc) {
        this.fileMoshaverePezeshkJavabDesc = fileMoshaverePezeshkJavabDesc;
    }

    public Integer getFileExcelKhanevadeId() {
        return fileExcelKhanevadeId;
    }

    public void setFileExcelKhanevadeId(Integer fileExcelKhanevadeId) {
        this.fileExcelKhanevadeId = fileExcelKhanevadeId;
    }

    public String getFileExcelKhanevadeName() {
        return fileExcelKhanevadeName;
    }

    public void setFileExcelKhanevadeName(String fileExcelKhanevadeName) {
        this.fileExcelKhanevadeName = fileExcelKhanevadeName;
    }

    public String getFileExcelKhanevadeDesc() {
        return fileExcelKhanevadeDesc;
    }

    public void setFileExcelKhanevadeDesc(String fileExcelKhanevadeDesc) {
        this.fileExcelKhanevadeDesc = fileExcelKhanevadeDesc;
    }
}
