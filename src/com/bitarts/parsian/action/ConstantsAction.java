package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.IConstantsService;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/16/12
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantsAction extends BaseAction implements ServletContextAware {
    private List<Constants> constantsList;
    private IConstantsService constantsService;
    private HashMap<EnumString, List<EnumString>> hashNameEnumStrings;
    private List<EnumString> applyEnumStrings;
    private Constants constant;
    private String constantStyle, constantName, constantName2;
    private ILoginService loginService;
    private List<Tarh> tarhs;
    private Tarh tarh;
    private Long tarhId;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.constantsService = (IConstantsService) wac.getBean("constantsService");
        this.loginService = (ILoginService) wac.getBean("loginService");
    }

    public String prepare() {
        constantsList = constantsService.findAll();
        return SUCCESS;
    }

    public List<Constants> getConstantsList() {
        return constantsList;
    }

    public void setConstantsList(List<Constants> constantsList) {
        this.constantsList = constantsList;
    }

    public IConstantsService getConstantsService() {
        return constantsService;
    }

    public void setConstantsService(IConstantsService constantsService) {
        this.constantsService = constantsService;
    }

    public String addConstant() {
        constant.setApplyStyle(Constants.ConstantsApplyStyle.valueOf(constantStyle));
        constant.setName(Constants.Keys.valueOf(constantName));
        constant.setName2(Constants.KeysParam.valueOf(constantName2));
        if (tarhId != null)
            constant.setTarh(constantsService.findTarhById(tarhId));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            User user = loginService.findUserByUsername(username);
            constant.setUser(user);
        } else {
            SecurityContextHolder.clearContext();
            return ERROR;
        }
        constantsService.saveOrUpdate(constant);
        return SUCCESS;
    }

    public String listAllTarhs() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            SecurityContextHolder.clearContext();
            return "nosession";
        }
        tarhs = constantsService.listAllTarhs();
        return SUCCESS;
    }

    public String addTarhPrepare() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            SecurityContextHolder.clearContext();
            return "nosession";
        }
        if (tarh != null && tarh.getId() != null)
            tarh = constantsService.findTarhById(tarh.getId());
        return SUCCESS;
    }

    public String addTarh() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            SecurityContextHolder.clearContext();
            return "nosession";
        }
        constantsService.saveOrUpdateTarh(tarh);
        return SUCCESS;
    }

    private String shouldCheck, error;
    Integer type;

    public String getShouldCheck() {
        return shouldCheck;
    }

    public void setShouldCheck(String shouldCheck) {
        this.shouldCheck = shouldCheck;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Tarh> getTarhs() {
        if (tarhs == null)
            tarhs = constantsService.listAllTarhs();
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public Tarh getTarh() {
        return tarh;
    }

    public void setTarh(Tarh tarh) {
        this.tarh = tarh;
    }

    private class EnumString {
        private String enumS;
        private String farsiS;

        private EnumString(String enumS, String farsiS) {
            this.enumS = enumS;
            this.farsiS = farsiS;
        }

        public String getEnumS() {
            return enumS;
        }

        public void setEnumS(String enumS) {
            this.enumS = enumS;
        }

        public String getFarsiS() {
            return farsiS;
        }

        public void setFarsiS(String farsiS) {
            this.farsiS = farsiS;
        }
    }

    public String prepareAdd() {
        final Constants.Keys[] names = Constants.Keys.values();
        final Constants.ConstantsApplyStyle[] styles = Constants.ConstantsApplyStyle.values();
        hashNameEnumStrings = new LinkedHashMap<EnumString, List<EnumString>>(names.length);
        applyEnumStrings = new ArrayList<EnumString>(styles.length);
        final Constants c = new Constants();
        for (Constants.Keys s : names) {
            final EnumString en = new EnumString(s.name(), c.farsiName(s));
            final List<Constants.KeysParam> list = c.valuesOfKey(s);
            final List<EnumString> enList = new ArrayList<EnumString>(list.size());
            for (Constants.KeysParam k : list)
                enList.add(new EnumString(k.name(), c.farsiName2(k)));
            hashNameEnumStrings.put(en, enList);
        }
        for (Constants.ConstantsApplyStyle s : styles)
            applyEnumStrings.add(new EnumString(s.name(), c.farsiStyle(s)));
        return SUCCESS;
    }


    public HashMap<EnumString, List<EnumString>> getHashNameEnumStrings() {
        return hashNameEnumStrings;
    }

    public void setHashNameEnumStrings(HashMap<EnumString, List<EnumString>> hashNameEnumStrings) {
        this.hashNameEnumStrings = hashNameEnumStrings;
    }

    public List<EnumString> getApplyEnumStrings() {
        return applyEnumStrings;
    }

    public void setApplyEnumStrings(List<EnumString> applyEnumStrings) {
        this.applyEnumStrings = applyEnumStrings;
    }

    public Constants getConstant() {
        return constant;
    }

    public void setConstant(Constants constant) {
        this.constant = constant;
    }

    public String getConstantStyle() {
        return constantStyle;
    }

    public void setConstantStyle(String constantStyle) {
        this.constantStyle = constantStyle;
    }

    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    public String getConstantName2() {
        return constantName2;
    }

    public void setConstantName2(String constantName2) {
        this.constantName2 = constantName2;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public Long getTarhId() {
        return tarhId;
    }

    public void setTarhId(Long tarhId) {
        this.tarhId = tarhId;
    }
}

