package com.bitarts.parsian.interceptor;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.security.URISecurityManager;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.IPishnehadService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/3/12
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class URIcheckingInterceptor implements Interceptor, ServletContextAware
{
    private IPishnehadService pishnehadService;
    private ILoginService loginService;
    public void setServletContext(ServletContext servletContext)
    {
        WebApplicationContext webAppContext= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        setPishnehadService((IPishnehadService) webAppContext.getBean(IPishnehadService.SERVICE_NAME));

    }

    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        pishnehadService = (IPishnehadService)InsuranceServiceFactory.getService(IPishnehadService.SERVICE_NAME, InsuranceServiceFactory.setUpSessionFactory());
        loginService = (ILoginService) InsuranceServiceFactory.getService(ILoginService.SERVICE_NAME, InsuranceServiceFactory.setUpSessionFactory());

        String actionName = actionInvocation.getProxy().getActionName();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (actionName.equalsIgnoreCase("editShowForm") || actionName.equalsIgnoreCase("editShowFormReadOnly"))
        {
            String[] s = (String[]) ActionContext.getContext().getParameters().get("pishnehad.id");
            String[] tab = new String[0];
            if(ActionContext.getContext().getParameters().get("tabsName")!=null)
                tab= (String[])ActionContext.getContext().getParameters().get("tabsName");
            Integer requestedId = 0;
            if ( s!= null && s.length > 0)
            {
                requestedId = Integer.parseInt(s[0]);

                URISecurityManager manager = new URISecurityManager();
                String tabNumber=null;
                if(tab.length!=0)
                    tabNumber= tab[0].equals("bimename") ? "3":null;
                Boolean hasPermission = manager.hasPermissionToViewPisnehadAction(actionName, requestedId, getPishnehadService(), user, tabNumber);
                if (!hasPermission)
                {
                    return "login";
                }
            }
        }

        String result = actionInvocation.invoke();

        return result;
    }


    public void init() {
    }

    public void destroy() {
    }


    public IPishnehadService getPishnehadService()
    {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService)
    {
        this.pishnehadService = pishnehadService;
    }
}
