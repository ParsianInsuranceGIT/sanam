package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.dao.UserDAO;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.IUserService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/11/12
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserService implements IUserService {

    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<User> findAllUserByRoleId(Integer roleId)
    {
        return userDAO.findAllUserByRoleId(roleId);
    }

    public void addNewUser(User user) {
        userDAO.addNewUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public PaginatedListImpl<User> findAllUserByFilter(PaginatedListImpl paginatedList, String fname, String lname, String code, Long vSodurId, String vSodurName, Boolean uStatus, Integer roleId, Long namayandeId, String namayandeName) {
        return userDAO.findAllUserByFilter(paginatedList, fname, lname, code, vSodurId, vSodurName, uStatus, roleId, namayandeId, namayandeName);
    }

    public void removeRoleById(Integer id) {
        userDAO.deleteById(Role.class, id);
    }

    public void saveNewRole(Role role) {
        userDAO.saveOrUpdate(role);
    }

    public PaginatedListImpl<User> findAllUser(PaginatedListImpl paginatedList) {
        return userDAO.findAllUser(paginatedList);
    }

    public PaginatedListImpl<Role> findAllSemats(PaginatedListImpl<Role> listSemats) {
        return userDAO.findAllRole(listSemats);
    }

    public List<User> findByRole(List<Role> roles) {
        return userDAO.findByRole(roles);
    }

    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }

    public List<Role> getRoleList() {
        return userDAO.getRoleList();
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public Role findRoleById(Integer roleId) {
        return userDAO.findRoleById(roleId);
    }
}
