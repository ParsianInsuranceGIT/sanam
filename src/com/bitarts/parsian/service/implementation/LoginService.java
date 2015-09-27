package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.dao.LoginDAO;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.service.ILoginService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
public class LoginService implements ILoginService {
    private LoginDAO loginDAO;

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Transactional
    public User authenticateUser(String username, String password) {

        User result = loginDAO.authenticateUser(username, password);
        return result;
    }

    public User findUserById(Long userId) {
        return loginDAO.findById(userId);
    }

    public User findUserOnlyByUsername(String username) {
        return loginDAO.findOnlyByUsername(username);
    }

    public User findUserByUsername(String username) {
        return loginDAO.findByUsername(username);
    }

    public User findUserByUsername(String username, String password) {
        User user = loginDAO.findByUsername(username);
        if (StringUtil.md5Hash(password.toString()).equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> findAllUsers() {
        return loginDAO.findAllUsers();
    }

    public List<User> findUsersByPersonalCodeOrName(String personalCode, String fname, String lname) {
        return loginDAO.findUsersByPersonalCodeOrName(personalCode, fname, lname);
    }

    public void updateUser(User user) {
        loginDAO.updateUser(user);
    }

    public Set<EmzaKonande> findAllEmzaKonandegan() {
        List<EmzaKonande> emzaKonandes = loginDAO.findAllEmzaKonandegan();
        Set<EmzaKonande> result = new HashSet<EmzaKonande>();
        for (EmzaKonande emzaKonande : emzaKonandes) {
            result.add(emzaKonande);
        }
        return result;
    }

    public List<EmzaKonande> findAllEmzaKonandegan(String name, String persoanlCode, Long maxCapital) {
        return loginDAO.findAllEmzaKonandegan(name, persoanlCode, maxCapital);
    }

    public List<User> findAllUsersForKarshenas() {
        return loginDAO.findAllUsersForKarshenas();
    }

    public List<User> findAllUsersNotEmzakonande() {
        return loginDAO.findAllUsersNotEmzakonande();
    }

    //    public List<SearchResult> doSearch(String naam, String naam_khaanevaadegi, String naam_pedar, String code_melli, String shomare_shenaasnaame) {
//        return addressDAO.doSearch(naam, naam_khaanevaadegi, naam_pedar, code_melli, shomare_shenaasnaame);
//    }
    public PaginatedListImpl<EmzaKonande> findAllEmzaKonandegan(PaginatedListImpl<EmzaKonande> emzaKonandegan, String fname, String lname, String code, Integer vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Integer namayandeId, String namayandeName, String azEtebar, String taEtebar) {
        return loginDAO.findAllEmzaKonandegan(emzaKonandegan, fname, lname, code, vSodurId, vSodurName, uStatus, roleId, namayandeId, namayandeName, azEtebar, taEtebar);
    }
}
