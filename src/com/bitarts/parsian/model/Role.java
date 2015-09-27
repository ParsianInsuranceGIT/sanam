package com.bitarts.parsian.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/4/11
 * Time: 6:51 PM
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tbl_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_color")
    private String roleColor;
//b-h
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rel_role_transition", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "transition_id", referencedColumnName = "transition_id")})
    Set<Transition> transitions;

//    //b-h
//    @OneToMany(mappedBy = "role")
//    private Set<User_Role> userRoles;

//    public Set<User_Role> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<User_Role> userRoles) {
//        this.userRoles = userRoles;
//    }

    public Role() {}


    public Role(Integer id, String roleName, String roleColor)
    {
        this.id = id;
        this.roleColor = roleColor;
        this.roleName = roleName;
    }

    public String getRoleColor() {
        return roleColor;
    }

    public void setRoleColor(String roleColor) {
        this.roleColor = roleColor;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(Set<Transition> transitions) {
        this.transitions = transitions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    //b-h
    public Set<User> getUsers() {
//        for(User_Role user_role:userRoles)
//            users.add(user_role.getUser());
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!roleName.equals(role.roleName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
