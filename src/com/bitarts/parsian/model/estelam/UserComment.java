package com.bitarts.parsian.model.estelam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/10/11
 * Time: 7:06 PM
 */
@Entity
@Table(name = "tbl_user_comment")
public class UserComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "estelam_id")
    private Integer id;
    @Column(name="user_name")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name="code_namayande")
    private String codeNamayandegi;
    @Column(name="nazar")
    private String nazar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNazar() {
        return nazar;
    }

    public void setNazar(String nazar) {
        this.nazar = nazar;
    }

    public String getCodeNamayandegi() {
        return codeNamayandegi;
    }

    public void setCodeNamayandegi(String codeNamayandegi) {
        this.codeNamayandegi = codeNamayandegi;
    }
}
