package com.bitarts.common.util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: nono
 * Date: 3/5/12
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncodingInterceptor implements Interceptor {

    public void destroy() {
        System.out.println("Destroying MyLoggingInterceptor...");

    }


    public void init() {
        System.out.println("Initializing MyLoggingInterceptor...");
    }


    public String intercept(ActionInvocation invocation) throws Exception {
        Set<String> keySet = invocation.getInvocationContext().getParameters().keySet();
        Map<String, Object> keyMap = invocation.getInvocationContext().getParameters();
        invocation.getResultCode();
        invocation.setResultCode("UTF-8");
        invocation.getResultCode();

        for (String s : keySet) {
            String[] theParameterValueArray = (String[]) keyMap.get(s);
            for (String theParameterValue : theParameterValueArray) {
                byte[] test = theParameterValue.getBytes("ISO-8859-1");
                if(test.length>0&&test[0]!=63){
                    theParameterValue = new String(theParameterValue.getBytes("ISO-8859-1"),"UTF-8");
                    keyMap.put(s,theParameterValue);
                }
            }
        }
        String result = invocation.invoke();
        return result;
    }
}
