package com.bitarts.parsian.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 6/19/11
 * Time: 3:32 PM
 */
public class UserDetailsImpl extends User {

    private com.bitarts.parsian.model.User user;
    private Date lastLoginDate;

    private static boolean accountNonExpired = true;
    private static boolean credentialsNonExpired = true;
    private static boolean accountNonLocked = true;

    public UserDetailsImpl(com.bitarts.parsian.model.User user, ArrayList<GrantedAuthority> authorities, Date lastLoginDate)
        throws IllegalArgumentException {
        super(user.getUsername(), user.getPassword(), true, accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities);
        this.user = user;
        this.lastLoginDate = lastLoginDate;
    }

    public Long getId() {
        return Long.parseLong(String.valueOf(user.getId()));
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }
}
