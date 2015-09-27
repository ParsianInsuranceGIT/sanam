package com.bitarts.parsian.model;

import com.bitarts.parsian.model.bimename.Gharardad;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/18/12
 * Time: 8:45 AM
 * To change this template use UploadedFile | Settings | UploadedFile Templates.
 */
@Entity
@Table(name="tbl_uploadedfile")
public class UploadedFile implements Serializable {
    public enum UploadedFileType {
        ExceleGharardadeJamieKhas
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "gharardad_id")
    private Gharardad gharardad;

    @Column(name = "created_date_time")
    private String createdDateTime;

    @Column(name="file_id")
    private Long fileId;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private UploadedFileType type;

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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public UploadedFileType getType() {
        return type;
    }

    public void setType(UploadedFileType type) {
        this.type = type;
    }
}
