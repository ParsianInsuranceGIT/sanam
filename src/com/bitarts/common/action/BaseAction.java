package com.bitarts.common.action;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Alireza
 * Date: Oct 24, 2010
 * Time: 6:20:10 PM
 */
public class BaseAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    protected static final String PEZESHK_SABT_NAZAR = "pezeshksabtnazar";
    protected static final String UPLOAD_PISH_PARDAKHT = "uploadpishpardakht";
    protected static final String ERJA_BE_KARSHENAS = "erjabekarshenas";
    protected static final String TABDIL_BE_BIMENAMEH_MOVAGHAT = "tabdilbebimenamehmovaghat";
    protected static final String SODURE_MOAREFI_NAME = "soduremoarefiname";
    protected static final String TAKHSIS_BE_KARSHENAS = "takhsisbekarshenas";
    protected static final String SABTE_SHOMARE_HESAB = "sabteshomarehesab";
    protected static final String VORUD_MABLAGH_CHECK_BARGASHTI = "vorudmablaghcheckbargashti";
    protected static final String TAEED_SODUR_CHECK_BARGASHTI = "taeedsodurcheckbargashti";
    protected static final String TAEED_FISH = "taeedfish";
    protected static final String KARBAR_MALI_LOGIN = "karbarmalilogin";
    private String rndValue;

    public String getParameters(String name) {
        try {
            String[] s;
            s = (String[]) ActionContext.getContext().getParameters().get(name);
            return s[0];
        } catch (Exception ex) {
            String s;
            s = (String) ActionContext.getContext().getParameters().get(name);
            return s;
        }
    }

    public Object getFromSession(String key) {
        if (session != null)
            return session.get(key);
        else return ActionContext.getContext().getSession().get(key);
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void putToSession(String key, Object val) {
        session.put(key, val);
    }

    public void removeFromSession(String key) {
        session.remove(key);
    }

    public boolean isExport() {
        boolean bExport = false;

        Map values = ActionContext.getContext().getParameters();

        if (values != null && !values.isEmpty()) {
            Iterator i = values.keySet().iterator();
            while (i.hasNext()) {
                String name = (String) i.next();
                // Export parameter is of the form 'd-5314487-e'
                if (name.startsWith("d-") && name.endsWith("-e")) {
                    bExport = true;
                    break;
                }
            }
        }

        return bExport;
    }

    public String getUserAgent(HttpServletRequest request)
    {
        return request.getHeader("user-agent");
    }


    public String getRndValue() {
        return UUID.randomUUID().toString();
    }

    public User getUser() {
        try {
            ILoginService loginService = (ILoginService) InsuranceServiceFactory.getService(ILoginService.SERVICE_NAME, InsuranceServiceFactory.setUpSessionFactory());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (username != null && username.length() > 0) {
                User theUser = loginService.findUserByUsername(username);
                if (theUser != null && theUser.getId() > 0) {
                    return theUser;
                }
            }
            SecurityContextHolder.clearContext();
            return null;
        } catch(Exception e) {
            return null;
        }
    }

}
