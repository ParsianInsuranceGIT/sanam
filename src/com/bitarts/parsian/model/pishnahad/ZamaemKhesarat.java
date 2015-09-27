package com.bitarts.parsian.model.pishnahad;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: alireza
 */
@Entity
@Table(name = "tbl_zamaemkhesarat")
public class ZamaemKhesarat implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name="file_elame_khesarat_id")
    private Integer fileElameKhesaratId;
    @Column(name = "file_elame_khesarat_name")
    private String fileElameKhesaratName;
    @Column(name = "file_elame_khesarat_desc", length = 2000)
    private String fileElameKhesaratDescription;


    @OneToOne(mappedBy="zamaemKhesarat",fetch = FetchType.LAZY)
    private Pishnehad pishnehad;




    public ZamaemKhesarat(){

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

    public Integer getFileElameKhesaratId() {
        return fileElameKhesaratId;
    }

    public void setFileElameKhesaratId(Integer fileElameKhesaratId) {
        this.fileElameKhesaratId = fileElameKhesaratId;
    }

    public String getFileElameKhesaratName() {
        return fileElameKhesaratName;
    }

    public void setFileElameKhesaratName(String fileElameKhesaratName) {
        this.fileElameKhesaratName = fileElameKhesaratName;
    }

    public String getFileElameKhesaratDescription() {
        return fileElameKhesaratDescription;
    }

    public void setFileElameKhesaratDescription(String fileElameKhesaratDescription) {
        this.fileElameKhesaratDescription = fileElameKhesaratDescription;
    }

    public ZamaemKhesarat clone(ZamaemKhesarat zamaem){
        ZamaemKhesarat result = new ZamaemKhesarat();
        result.setFileElameKhesaratDescription(zamaem.getFileElameKhesaratDescription());
        result.setFileElameKhesaratId(zamaem.getFileElameKhesaratId());
        result.setFileElameKhesaratName(zamaem.getFileElameKhesaratName());
        result.setPishnehad(zamaem.getPishnehad());
        return result;
    }

}
