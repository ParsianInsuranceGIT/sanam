package com.bitarts.parsian.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 6/13/15
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */

//@Entity
//@Table(name = "rel_user_role")
public class User_Role implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "role_id")
    private Role role;

//    @ManyToOne
//    @JoinColumn(name = "daftar_id")
    private Daftar daftar;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Daftar getDaftar() {
        return daftar;
    }

    public void setDaftar(Daftar daftar) {
        this.daftar = daftar;
    }
}
