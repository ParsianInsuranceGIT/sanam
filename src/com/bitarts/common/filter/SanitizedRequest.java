package com.bitarts.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: navit
 * Date: 3/11/12
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SanitizedRequest extends HttpServletRequestWrapper{

    public SanitizedRequest(HttpServletRequest request)
    {
        super(request);
    }

    private String sanitize(String what)
    {
//        return what.replaceAll("ي","ی").replaceAll("ك","ک");
        if(what == null)
        {
            return null;
        }
        return what.replace("jiv","hhh");
    }



    public String[] getParameterValues(String parameter)
    {
        String[] results = super.getParameterValues(parameter);
        if (results == null)
        {
            return null;
        }
        for (int i=0; i<results.length; i++)
        {
            results[i] = sanitize(results[i]);
        }
        return results;
    }

    public String getParameter(String paramName) {
        String value = super.getParameter(paramName);
        return sanitize(value);
    }

    public java.lang.String getQueryString()
    {
        return sanitize(super.getQueryString());
    }

    public String getRequestURI()
    {
        return sanitize(super.getRequestURI());
    }

    public String getContextPath()
    {
        return sanitize(super.getContextPath());
    }

    public String getServletPath()
    {
        return sanitize(super.getServletPath());
    }

    public String getPathInfo()
    {
        return sanitize(super.getPathInfo());
    }



}
