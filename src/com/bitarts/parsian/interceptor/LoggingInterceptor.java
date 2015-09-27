package com.bitarts.parsian.interceptor;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.security.URISecurityManager;
import com.bitarts.parsian.service.IPishnehadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.util.ServletContextAware;
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
public class LoggingInterceptor implements Interceptor,ServletContextAware
{
    private IPishnehadService pishnehadService;
    public void setServletContext(ServletContext servletContext)
    {
        WebApplicationContext webAppContext= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        setPishnehadService((IPishnehadService) webAppContext.getBean(IPishnehadService.SERVICE_NAME));

    }

    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        String actionName = actionInvocation.getProxy().getActionName();

        if (actionName.equalsIgnoreCase("editShowForm") || actionName.equalsIgnoreCase("editShowFormReadOnly"))
        {
            String[] s = (String[]) ActionContext.getContext().getParameters().get("pishnehad.id");
            String[] tab = new String[0];
            if (ActionContext.getContext().getParameters().get("tabsName") != null)
                tab = (String[]) ActionContext.getContext().getParameters().get("tabsName");
            Integer requestedId = 0;
            if ( s!= null && s.length > 0)
            {
                requestedId = Integer.parseInt(s[0]);

                URISecurityManager manager = new URISecurityManager();
                User user = (User) ActionContext.getContext().getSession().get("user");
                String tabNumber = null;
                if (tab.length != 0)
                    tabNumber = tab[0].equals("bimename") ? "3" : null;
                Boolean hasPermission = manager.hasPermissionToViewPisnehadAction(actionName, requestedId, getPishnehadService(),user,tabNumber);
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
