package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.INamayandeService;
import com.bitarts.parsian.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/10/12
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserAction extends BaseAction implements ServletContextAware {

    private User user;
    private User candidateUser;
    private IUserService userService;
    private INamayandeService namayandeService;
    private ILoginService loginService;
    private PaginatedListImpl<User> userList;
    private List<Role> roleList;
    private List<Namayande> namayandeList;
    private ArrayList<String> selectedRoles;
    private Integer roleId;
    private Long namayandeId, viSodurId;
    private String viSodurName, namayandeName;
    private String code, lname, fname;
    private Integer uiStatus;

    public String sabtUser() {

        roleList = userService.getRoleList();
//        namayandeList = namayandeService.getAllNamayande();
        selectedRoles = new ArrayList<String>();

        if (candidateUser != null && candidateUser.getId() != null) {
            candidateUser = userService.getUserById(candidateUser.getId());

            Iterator i = candidateUser.getRoles().iterator();
            while (i.hasNext()) {
                selectedRoles.add(((Role) i.next()).getRoleName().toString());
            }
        }
        return SUCCESS;
    }

    public String addUser() {
        candidateUser.setRoles(new HashSet<Role>());
        Boolean hasNamayandegi = false;
        for (int i = 0; i < getSelectedRoles().size(); i++) {
            Role r = userService.findRoleById(Integer.parseInt(getSelectedRoles().get(i)));
            candidateUser.getRoles().add(r);

            if (getSelectedRoles().get(i).equalsIgnoreCase("10") || getSelectedRoles().get(i).equalsIgnoreCase("11")) {
                hasNamayandegi = true;
            }
        }

        if (!hasNamayandegi) {
            candidateUser.setNamayandegi(null);
        }else{
            candidateUser.setNamayandegi(namayandeService.getNamayandeById(candidateUser.getNamayandegi().getId()));
        }

        selectedRoles = new ArrayList<String>();
        Iterator i = candidateUser.getRoles().iterator();
        while (i.hasNext()) {
            selectedRoles.add(((Role) i.next()).getRoleName().toString());
        }
        if(loginService.findUserOnlyByUsername(candidateUser.getUsername()) != null){
            addActionError("نام کاربری تکراری است.");
            return SUCCESS;
        }
        candidateUser.setPassword(StringUtil.md5Hash(candidateUser.getPassword()));
        userService.addNewUser(candidateUser);
        addActionMessage("کاربر جدید با موفقیت اضافه شد.");
        return SUCCESS;
    }

    public String editUser() {
        Set<Role> roles = new HashSet<Role>();
        Boolean hasNamayandegi = false;
        for (int i = 0; i < getSelectedRoles().size(); i++) {
            Role r = userService.findRoleById(Integer.parseInt(getSelectedRoles().get(i)));
            roles.add(r);
            if (getSelectedRoles().get(i).equalsIgnoreCase("10")  //10 yani role namayande
             /*|| getSelectedRoles().get(i).equalsIgnoreCase("11")*/) {
                hasNamayandegi = true;
            }
        }
        if (!hasNamayandegi)  //agar role namayande ra nadarad
        {
            candidateUser.setNamayandegi(null);
        }else{
            candidateUser.setNamayandegi(namayandeService.getNamayandeById(candidateUser.getNamayandegi().getId()));
        }
        candidateUser.setRoles(roles);

        selectedRoles = new ArrayList<String>();
        Iterator i = candidateUser.getRoles().iterator();
//       if(!candidateUser.isFaal()) {
//           addActionError("کاربر مورد نظر فعال نمی باشد");
//           return SUCCESS;
//       }

        User u = loginService.findUserOnlyByUsername(candidateUser.getUsername());
        if(u != null && !u.getId().equals(candidateUser.getId())){
            addActionError("نام کاربری تکراری است.");
            return SUCCESS;
        }
        while (i.hasNext()) {
            Role role = ((Role) i.next());
            selectedRoles.add(role.getRoleName().toString());
            for (int ii = 0; ii < getSelectedRoles().size(); ii++) {
                if (!u.getRoles().contains(role))
                    u.getRoles().add(role);
            }
        }
        if (u!=null)
        {
//            candidateUser.setValidPass(u.getValidPass());
            if(candidateUser.getFirstName()!=null && !candidateUser.getFirstName().isEmpty())
                u.setFirstName(candidateUser.getFirstName());
            if(candidateUser.getLastName()!=null && !candidateUser.getLastName().isEmpty())
                u.setLastName(candidateUser.getLastName());
            if(candidateUser.getUsername()!=null && !candidateUser.getUsername().isEmpty())
                u.setUsername(candidateUser.getUsername());
            if(candidateUser.getPassword()!=null && !candidateUser.getPassword().isEmpty())
                u.setPassword(candidateUser.getPassword());
            if(candidateUser.getPersonalCode()!=null && !candidateUser.getPersonalCode().isEmpty())
                u.setPersonalCode(candidateUser.getPersonalCode());
            if(candidateUser.getMobile()!=null && !candidateUser.getMobile().isEmpty())
                u.setMobile(candidateUser.getMobile());
            if(candidateUser.getSematSazmani()!=null && !candidateUser.getSematSazmani().isEmpty())
                u.setSematSazmani(candidateUser.getSematSazmani());
            if(candidateUser.getNamayandegi()!=null && candidateUser.getNamayandegi().getName()!=null && !candidateUser.getNamayandegi().getName().isEmpty()) //agar role namayandegi ra darad
                u.setNamayandegi(candidateUser.getNamayandegi());
            if(candidateUser.getRoles()!=null /* && candidateUser.getRoles().size()==0*/)
                u.setRoles(candidateUser.getRoles());
            if (candidateUser.getMojtamaSodoor() != null && candidateUser.getMojtamaSodoor().getName() !=null && !candidateUser.getMojtamaSodoor().getName().isEmpty())
            {
                if (u.getMojtamaSodoor() != null)
                {
                  u.getMojtamaSodoor().setName(candidateUser.getMojtamaSodoor().getName());
                  namayandeService.editNamayande(u.getMojtamaSodoor());
                  u.setMojtamaSodoor(u.getMojtamaSodoor());
                }
            }
            u.setFaal(candidateUser.isFaal());
        }
//        userService.editUs
// er(candidateUser);
        userService.editUser(u);
        addActionMessage("کاربر با موفقیت ویرایش شد.");
        return SUCCESS;
    }

    public String deleteUser() {
        userService.removeUser(user.getId());
        return SUCCESS;
    }

    public String listAllUser() {
        userList = new PaginatedListImpl<User>();
        userList.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        userList.setPageNumber(pageNumber);
        Boolean uiStatus = null;
        if (this.uiStatus != null)
            switch (this.uiStatus) {
                case 0:
                    uiStatus = true;
                    break;
                case 1:
                    uiStatus = true;
                    break;
                default:
                    uiStatus = null;
            }
        userList = userService.findAllUserByFilter(userList, fname, lname, code, viSodurId, viSodurName, uiStatus, roleId, namayandeId, namayandeName);
        getSession().put("listUser", userList);
        return SUCCESS;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.userService = (IUserService) wac.getBean(IUserService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public PaginatedListImpl<User> getUserList() {
        return userList;
    }

    public void setUserList(PaginatedListImpl<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        if (roleList == null) roleList = userService.getRoleList();
        Iterator<Role> it = roleList.iterator();
        while (it.hasNext()) {
            final Role r = it.next();
//            if (r.getRoleName().contains("PAS") || r.getRoleName().contains("MAJAZI"))
            if (r.getRoleName().contains("MAJAZI"))
                it.remove();
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Namayande> getNamayandeList() {
        return namayandeList;
    }

    public void setNamayandeList(List<Namayande> namayandeList) {
        this.namayandeList = namayandeList;
    }

    public ArrayList<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(ArrayList<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getUiStatus() {
        return uiStatus;
    }

    public void setUiStatus(Integer uiStatus) {
        this.uiStatus = uiStatus;
    }

    public Long getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId) {
        this.namayandeId = namayandeId;
    }

    public String getNamayandeName() {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName) {
        this.namayandeName = namayandeName;
    }

    public String getViSodurName() {
        return viSodurName;
    }

    public void setViSodurName(String viSodurName) {
        this.viSodurName = viSodurName;
    }

    public Long getViSodurId() {
        return viSodurId;
    }

    public void setViSodurId(Long viSodurId) {
        this.viSodurId = viSodurId;
    }

    public User getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(User candidateUser) {
        this.candidateUser = candidateUser;
    }
}
