package com.bitarts.common.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
    private String encoding = "utf-8";
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String theParameterName = parameterNames.nextElement();
            request.getCharacterEncoding();

            String[] paramValues = request.getParameterValues(theParameterName);
            int paramValuesCounter = 0;
            for (String paramValue : paramValues) {
                //This fixes the problem with different types of kaaf and yaa'e. Here we make it unified so what
                //that the user enters matches exactly what that is stored in our databse!
                byte[] test = paramValue.getBytes("ISO-8859-1");
                if(test.length>0&&test[0]!=63){
                    paramValue = new String(paramValue.getBytes("ISO-8859-1"),"UTF-8");
                    paramValues[paramValuesCounter]=paramValue;
                }
                paramValue = paramValue.replaceAll("ي","ی");
                paramValue = paramValue.replaceAll("ك","ک");
//                paramValues[paramValuesCounter] = "abc";
                paramValues[paramValuesCounter] = paramValue;
                paramValuesCounter++;
            }
        }
//        filterChain.doFilter(new SanitizedRequest((HttpServletRequest)request), response);
        filterChain.doFilter(request, response);

    }
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }
    public void destroy() {
        // nothing
    }
}
