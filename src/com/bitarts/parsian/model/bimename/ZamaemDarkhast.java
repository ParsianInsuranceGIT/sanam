package com.bitarts.parsian.model.bimename;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/20/11
 * Time: 9:53 AM
 */
@Entity
@Table(name = "tbl_zamaem_darkhast")
public class ZamaemDarkhast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zamaem_darkhast_id")
    private Integer id;

    @Column(name = "file_darkhast_id")
    private Integer fileDarkhastBahremandiId;
    @Column(name = "file_darkhast_name")
    private String fileDarkhastBahremandiName;
    @Column(name = "file_darkhast_desc")
    private String fileDarkhastBahremandiDescription;

    @Column(name = "file_elhagh_id")
    private Integer fileElhaghiyeId;
    @Column(name = "file_elhagh_name")
    private String fileElhaghiyeName;
    @Column(name = "file_elhagh_desc")
    private String fileElhaghiyeDescription;

    @Column(name = "file_akharin_vaz_id")
    private Integer fileAkharinVaziatId;
    @Column(name = "file_akharin_vaz_name")
    private String fileAkharinVaziatName;
    @Column(name = "file_akharin_vaz_desc")
    private String fileAkharinVaziatDescription;

    @Column(name = "file_shenasgozar_jadid_id")
    private Integer fileShenaseBimegozarJadidId;
    @Column(name = "file_shenasgozar_jadid_name")
    private String fileShenaseBimegozarJadidName;
    @Column(name = "file_shenasgozar_jadid_desc")
    private String fileShenaseBimegozarJadidDescription;

    @Column(name = "file_shenasgozar_ghadim_id")
    private Integer fileShenaseBimegozarGhadimId;
    @Column(name = "file_shenasgozar_ghadim_name")
    private String fileShenaseBimegozarGhadimName;
    @Column(name = "file_shenasgozar_ghadim_desc")
    private String fileShenaseBimegozarGhadimDescription;

    @Column(name = "file_fish_bedehi_vam_id")
    private Integer fileFishBedehiVamId;
    @Column(name = "file_fish_bedehi_vam_name")
    private String fileFishBedehiVamName;
    @Column(name = "file_fish_bedehi_vam_desc")
    private String fileFishBedehiVamDescription;

    @Column(name = "file_shenasshode_ghadim_id")
    private Integer fileShenaseBimeshodeGhadimId;
    @Column(name = "file_shenasshode_ghadim_name")
    private String fileShenaseBimeshodeGhadimName;
    @Column(name = "file_shenasshode_ghadim_desc")
    private String fileShenaseBimeshodeGhadimDescription;

    @Column(name = "file_mafghudi_id")
    private Integer fileElameMafghudiId;
    @Column(name = "file_mafghudi_name")
    private String fileElameMafghudiName;
    @Column(name = "file_mafghudi_desc")
    private String fileElameMafghudiDescription;

    @Column(name = "file_agahichap_id")
    private Integer fileAgahiChapId;
    @Column(name = "file_agahichap_name")
    private String fileAgahiChapName;
    @Column(name = "file_agahichap_desc")
    private String fileAgahiChapDescription;

    @Column(name = "file_ehraz_id")
    private Integer fileEhrazId;
    @Column(name = "file_ehraz_name")
    private String fileEhrazName;
    @Column(name = "file_ehraz_desc")
    private String fileEhrazDescription;

    @Column(name = "file_kollie_id")
    private Integer fileKollieId;
    @Column(name = "file_kollie_name")
    private String fileKollieName;
    @Column(name = "file_kollie_desc")
    private String fileKollieDescription;

    @Column(name = "file_sayer_id")
    private Integer fileSayerId;
    @Column(name = "file_sayer_name")
    private String fileSayerName;
    @Column(name = "file_sayer_desc")
    private String fileSayerDescription;

    @Column(name = "file_elam_khesarat_id")
    private Integer fileElamKhesaratId;
    @Column(name = "file_elam_khesarat_name")
    private String fileElamKhesaratName;
    @Column(name = "file_elam_khesarat_desc")
    private String fileElamKhesaratDescription;

    @Column(name = "file_adameTataboghEmza_id")
    private Integer fileAdameTataboghEmzaId;
    @Column(name = "file_adameTataboghEmza_name")
    private String fileAdameTataboghEmzaName;
    @Column(name = "file_adameTataboghEmza_desc")
    private String fileAdameTataboghEmzaDescription;

    @OneToOne(mappedBy="zamaemDarkhast", fetch = FetchType.LAZY)
    private DarkhastBazkharid darkhastBazkharid;


    public Integer getFileElamKhesaratId()
    {
        return fileElamKhesaratId;
    }

    public void setFileElamKhesaratId(Integer fileElamKhesaratId)
    {
        this.fileElamKhesaratId = fileElamKhesaratId;
    }

    public String getFileElamKhesaratName()
    {
        return fileElamKhesaratName;
    }

    public void setFileElamKhesaratName(String fileElamKhesaratName)
    {
        this.fileElamKhesaratName = fileElamKhesaratName;
    }

    public String getFileElamKhesaratDescription()
    {
        return fileElamKhesaratDescription;
    }

    public void setFileElamKhesaratDescription(String fileElamKhesaratDescription)
    {
        this.fileElamKhesaratDescription = fileElamKhesaratDescription;
    }

    public ZamaemDarkhast(){

    }

    public Integer getFileFishBedehiVamId()
    {
        return fileFishBedehiVamId;
    }

    public void setFileFishBedehiVamId(Integer fileFishBedehiVamId)
    {
        this.fileFishBedehiVamId = fileFishBedehiVamId;
    }

    public String getFileFishBedehiVamName()
    {
        return fileFishBedehiVamName;
    }

    public void setFileFishBedehiVamName(String fileFishBedehiVamName)
    {
        this.fileFishBedehiVamName = fileFishBedehiVamName;
    }

    public String getFileFishBedehiVamDescription()
    {
        return fileFishBedehiVamDescription;
    }

    public void setFileFishBedehiVamDescription(String fileFishBedehiVamDescription)
    {
        this.fileFishBedehiVamDescription = fileFishBedehiVamDescription;
    }

    public Integer getFileAdameTataboghEmzaId()
    {
        return fileAdameTataboghEmzaId;
    }

    public void setFileAdameTataboghEmzaId(Integer fileAdameTataboghEmzaId)
    {
        this.fileAdameTataboghEmzaId = fileAdameTataboghEmzaId;
    }

    public String getFileAdameTataboghEmzaName()
    {
        return fileAdameTataboghEmzaName;
    }

    public void setFileAdameTataboghEmzaName(String fileAdameTataboghEmzaName)
    {
        this.fileAdameTataboghEmzaName = fileAdameTataboghEmzaName;
    }

    public String getFileAdameTataboghEmzaDescription()
    {
        return fileAdameTataboghEmzaDescription;
    }

    public void setFileAdameTataboghEmzaDescription(String fileAdameTataboghEmzaDescription)
    {
        this.fileAdameTataboghEmzaDescription = fileAdameTataboghEmzaDescription;
    }

    public Integer getFileSayerId()
    {
        return fileSayerId;
    }

    public void setFileSayerId(Integer fileSayerId)
    {
        this.fileSayerId = fileSayerId;
    }

    public String getFileSayerName()
    {
        return fileSayerName;
    }

    public void setFileSayerName(String fileSayerName)
    {
        this.fileSayerName = fileSayerName;
    }

    public String getFileSayerDescription()
    {
        return fileSayerDescription;
    }

    public void setFileSayerDescription(String fileSayerDescription)
    {
        this.fileSayerDescription = fileSayerDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileDarkhastBahremandiId() {
        return fileDarkhastBahremandiId;
    }

    public void setFileDarkhastBahremandiId(Integer fileDarkhastBahremandiId) {
        this.fileDarkhastBahremandiId = fileDarkhastBahremandiId;
    }

    public String getFileDarkhastBahremandiName() {
        return fileDarkhastBahremandiName;
    }

    public void setFileDarkhastBahremandiName(String fileDarkhastBahremandiName) {
        this.fileDarkhastBahremandiName = fileDarkhastBahremandiName;
    }

    public String getFileDarkhastBahremandiDescription() {
        return fileDarkhastBahremandiDescription;
    }

    public void setFileDarkhastBahremandiDescription(String fileDarkhastBahremandiDescription) {
        this.fileDarkhastBahremandiDescription = fileDarkhastBahremandiDescription;
    }

    public Integer getFileElameMafghudiId() {
        return fileElameMafghudiId;
    }

    public void setFileElameMafghudiId(Integer fileElameMafghudiId) {
        this.fileElameMafghudiId = fileElameMafghudiId;
    }

    public String getFileElameMafghudiName() {
        return fileElameMafghudiName;
    }

    public void setFileElameMafghudiName(String fileElameMafghudiName) {
        this.fileElameMafghudiName = fileElameMafghudiName;
    }

    public String getFileElameMafghudiDescription() {
        return fileElameMafghudiDescription;
    }

    public void setFileElameMafghudiDescription(String fileElameMafghudiDescription) {
        this.fileElameMafghudiDescription = fileElameMafghudiDescription;
    }

    public Integer getFileAgahiChapId() {
        return fileAgahiChapId;
    }

    public void setFileAgahiChapId(Integer fileAgahiChapId) {
        this.fileAgahiChapId = fileAgahiChapId;
    }

    public String getFileAgahiChapName() {
        return fileAgahiChapName;
    }

    public void setFileAgahiChapName(String fileAgahiChapName) {
        this.fileAgahiChapName = fileAgahiChapName;
    }

    public String getFileAgahiChapDescription() {
        return fileAgahiChapDescription;
    }

    public void setFileAgahiChapDescription(String fileAgahiChapDescription) {
        this.fileAgahiChapDescription = fileAgahiChapDescription;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public Integer getFileEhrazId() {
        return fileEhrazId;
    }

    public void setFileEhrazId(Integer fileEhrazId) {
        this.fileEhrazId = fileEhrazId;
    }

    public String getFileEhrazName() {
        return fileEhrazName;
    }

    public void setFileEhrazName(String fileEhrazName) {
        this.fileEhrazName = fileEhrazName;
    }

    public String getFileEhrazDescription() {
        return fileEhrazDescription;
    }

    public void setFileEhrazDescription(String fileEhrazDescription) {
        this.fileEhrazDescription = fileEhrazDescription;
    }

    public Integer getFileElhaghiyeId() {
        return fileElhaghiyeId;
    }

    public void setFileElhaghiyeId(Integer fileElhaghiyeId) {
        this.fileElhaghiyeId = fileElhaghiyeId;
    }

    public String getFileElhaghiyeName() {
        return fileElhaghiyeName;
    }

    public void setFileElhaghiyeName(String fileElhaghiyeName) {
        this.fileElhaghiyeName = fileElhaghiyeName;
    }

    public String getFileElhaghiyeDescription() {
        return fileElhaghiyeDescription;
    }

    public void setFileElhaghiyeDescription(String fileElhaghiyeDescription) {
        this.fileElhaghiyeDescription = fileElhaghiyeDescription;
    }

    public Integer getFileAkharinVaziatId() {
        return fileAkharinVaziatId;
    }

    public void setFileAkharinVaziatId(Integer fileAkharinVaziatId) {
        this.fileAkharinVaziatId = fileAkharinVaziatId;
    }

    public String getFileAkharinVaziatName() {
        return fileAkharinVaziatName;
    }

    public void setFileAkharinVaziatName(String fileAkharinVaziatName) {
        this.fileAkharinVaziatName = fileAkharinVaziatName;
    }

    public String getFileAkharinVaziatDescription() {
        return fileAkharinVaziatDescription;
    }

    public void setFileAkharinVaziatDescription(String fileAkharinVaziatDescription) {
        this.fileAkharinVaziatDescription = fileAkharinVaziatDescription;
    }

    public Integer getFileShenaseBimegozarJadidId() {
        return fileShenaseBimegozarJadidId;
    }

    public void setFileShenaseBimegozarJadidId(Integer fileShenaseBimegozarJadidId) {
        this.fileShenaseBimegozarJadidId = fileShenaseBimegozarJadidId;
    }

    public String getFileShenaseBimegozarJadidName() {
        return fileShenaseBimegozarJadidName;
    }

    public void setFileShenaseBimegozarJadidName(String fileShenaseBimegozarJadidName) {
        this.fileShenaseBimegozarJadidName = fileShenaseBimegozarJadidName;
    }

    public String getFileShenaseBimegozarJadidDescription() {
        return fileShenaseBimegozarJadidDescription;
    }

    public void setFileShenaseBimegozarJadidDescription(String fileShenaseBimegozarJadidDescription) {
        this.fileShenaseBimegozarJadidDescription = fileShenaseBimegozarJadidDescription;
    }

    public Integer getFileShenaseBimegozarGhadimId() {
        return fileShenaseBimegozarGhadimId;
    }

    public void setFileShenaseBimegozarGhadimId(Integer fileShenaseBimegozarGhadimId) {
        this.fileShenaseBimegozarGhadimId = fileShenaseBimegozarGhadimId;
    }

    public String getFileShenaseBimegozarGhadimName() {
        return fileShenaseBimegozarGhadimName;
    }

    public void setFileShenaseBimegozarGhadimName(String fileShenaseBimegozarGhadimName) {
        this.fileShenaseBimegozarGhadimName = fileShenaseBimegozarGhadimName;
    }

    public String getFileShenaseBimegozarGhadimDescription() {
        return fileShenaseBimegozarGhadimDescription;
    }

    public void setFileShenaseBimegozarGhadimDescription(String fileShenaseBimegozarGhadimDescription) {
        this.fileShenaseBimegozarGhadimDescription = fileShenaseBimegozarGhadimDescription;
    }

    public Integer getFileShenaseBimeshodeGhadimId() {
        return fileShenaseBimeshodeGhadimId;
    }

    public void setFileShenaseBimeshodeGhadimId(Integer fileShenaseBimeshodeGhadimId) {
        this.fileShenaseBimeshodeGhadimId = fileShenaseBimeshodeGhadimId;
    }

    public String getFileShenaseBimeshodeGhadimName() {
        return fileShenaseBimeshodeGhadimName;
    }

    public void setFileShenaseBimeshodeGhadimName(String fileShenaseBimeshodeGhadimName) {
        this.fileShenaseBimeshodeGhadimName = fileShenaseBimeshodeGhadimName;
    }

    public String getFileShenaseBimeshodeGhadimDescription() {
        return fileShenaseBimeshodeGhadimDescription;
    }

    public void setFileShenaseBimeshodeGhadimDescription(String fileShenaseBimeshodeGhadimDescription) {
        this.fileShenaseBimeshodeGhadimDescription = fileShenaseBimeshodeGhadimDescription;
    }

    public Integer getFileKollieId() {
        return fileKollieId;
    }

    public void setFileKollieId(Integer fileKollieId) {
        this.fileKollieId = fileKollieId;
    }

    public String getFileKollieName() {
        return fileKollieName;
    }

    public void setFileKollieName(String fileKollieName) {
        this.fileKollieName = fileKollieName;
    }

    public String getFileKollieDescription() {
        return fileKollieDescription;
    }

    public void setFileKollieDescription(String fileKollieDescription) {
        this.fileKollieDescription = fileKollieDescription;
    }
}
