package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/11/12
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserService extends IBaseService  {

    public static final String SERVICE_NAME = "userService";

    public List<User> findAllUserByRoleId(Integer roleId);

    public void addNewUser(User user);

    public void editUser(User user);

    public User getUserById(Long id);

    public PaginatedListImpl<User> findAllUser(PaginatedListImpl paginatedList);

    public void removeUser(Long id);

    public List<Role> getRoleList();

    public List<User> findAll();

    public Role findRoleById(Integer roleId);

    PaginatedListImpl<User> findAllUserByFilter(PaginatedListImpl paginatedList, String fname, String lname, String code, Long vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Long namayandeId, String namayandeName);

    void removeRoleById(Integer id);

    void saveNewRole(Role role);

    PaginatedListImpl<Role> findAllSemats(PaginatedListImpl<Role> listSemats);

    List<User> findByRole(List<Role> roles);
}
