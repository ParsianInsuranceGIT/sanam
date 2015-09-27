package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.management.EmzaKonande;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron3
 * Date: 3/9/11
 * Time: 8:34 PM
 */
public interface ILoginService extends IBaseService {
    public static final String SERVICE_NAME = "loginService";

    public User authenticateUser(String username, String password);

    public User findUserById(Long userId);

//    public List<SearchResult> doSearch(String neshaniManzel, String kodePostiManzel, String telephoneManzel, String telephoneHamrah);

    public User findUserByUsername(String username);

    public User findUserOnlyByUsername(String username);

    public User findUserByUsername(String username,String password);

    public List<User> findAllUsers();

    public List<User> findUsersByPersonalCodeOrName(String personalCode, String fname, String lname);

    void updateUser(User theUser);

    Set<EmzaKonande> findAllEmzaKonandegan();

    List<EmzaKonande> findAllEmzaKonandegan(String name, String persoanlCode, Long maxCapital);

    List<User> findAllUsersForKarshenas();

    List<User> findAllUsersNotEmzakonande();

    PaginatedListImpl<EmzaKonande> findAllEmzaKonandegan(PaginatedListImpl<EmzaKonande> emzaKonandegan, String fname, String lname, String code, Integer vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Integer namayandeId, String namayandeName, String azEtebar, String taEtebar);
}
