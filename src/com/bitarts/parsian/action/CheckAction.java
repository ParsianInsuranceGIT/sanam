package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.check.DasteCheck;
import com.bitarts.parsian.service.ICheckService;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 31, 2011
 * Time: 5:12:02 PM
 */
public class CheckAction extends BaseAction implements ServletContextAware {
    private ICheckService checkService;
    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.checkService = (ICheckService) wac.getBean(ICheckService.SERVICE_NAME);
    }

    List<DasteCheck> dasteCheckList;
    public String loadDasteCheckHa(){
        dasteCheckList = checkService.loadDasteCheckHa();
        return SUCCESS;
    }

    DasteCheck dasteCheck;
    public String addDasteCheck(){
        checkService.addDasteCheck(dasteCheck);
        addActionMessage(getText("dasteCheck.added"));
        return loadDasteCheckHa();
    }

    public String viewCheckList(){
        dasteCheck = checkService.findDasteCheckById(dasteCheck.getId());
        return SUCCESS;
    }

    private Check check;
    public String editCheck(){
        checkService.editCheck(check);
        dasteCheck = checkService.findDasteCheckById(dasteCheck.getId());
        return SUCCESS;
    }

    public List<DasteCheck> getDasteCheckList() {
        return dasteCheckList;
    }

    public void setDasteCheckList(List<DasteCheck> dasteCheckList) {
        this.dasteCheckList = dasteCheckList;
    }

    public DasteCheck getDasteCheck() {
        return dasteCheck;
    }

    public void setDasteCheck(DasteCheck dasteCheck) {
        this.dasteCheck = dasteCheck;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
