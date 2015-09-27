package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.LoginDAO;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import com.bitarts.parsian.i18n.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 6/19/11
 * Time: 2:12 PM
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private LoginDAO loginDAO;
//    private String daftarType;
//
//    public String getDaftarType() {
//        return daftarType;
//    }
//
//    public void setDaftarType(String daftarType) {
//        this.daftarType = daftarType;
//    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

       User userEntity = loginDAO.findByUsername(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("No such user: " + username);

        String userEntityName = userEntity.getUsername();
        String password = userEntity.getPassword();
        boolean enabled = userEntity.isFaal();
        boolean accountNonExpired = userEntity.isFaal();
        boolean credentialsNonExpired = userEntity.isFaal();
        boolean accountNonLocked = userEntity.isFaal();

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();


            System.out.println(userEntity.getFullName());
//        Integer daftar_id=(Integer) ActionContext.getContext().getSession().get("daftar_id");
//        System.out.println("daftar_id"+daftar_id);
        for (Role role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }


        return new org.springframework.security.core.userdetails.User(userEntityName, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

}
