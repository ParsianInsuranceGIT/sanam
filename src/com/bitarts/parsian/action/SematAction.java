package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 9/9/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class SematAction extends BaseAction implements ServletContextAware {
    private PaginatedListImpl<Role> listSemats;
    private IUserService userService;
    private Role role;

    @Override
    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        userService = (IUserService) wac.getBean("userService");
    }

    public String deleteSemat() {
        userService.removeRoleById(role.getId());
        addActionMessage("سمت با موفقیت حذف شد.");
        return SUCCESS;
    }

    public String sabtSemat() {

        if (role != null && role.getId() != null) {
            role = userService.findRoleById(role.getId());
        }

        return SUCCESS;
    }

    public String listAllSemats() {
        listSemats = new PaginatedListImpl<Role>();
        listSemats.setObjectsPerPage(15);
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
        listSemats.setPageNumber(pageNumber);
        listSemats = userService.findAllSemats(listSemats);
        getSession().put("listSemats", listSemats);
        return SUCCESS;
    }

    public String addSemat() {
        userService.saveNewRole(role);
        addActionMessage("سمت با موفقیت اضافه یا ویرایش شد.");
        return SUCCESS;
    }

    public PaginatedListImpl<Role> getListSemats() {
        return listSemats;
    }

    public void setListSemats(PaginatedListImpl<Role> listSemats) {
        this.listSemats = listSemats;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
