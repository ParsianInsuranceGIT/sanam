package com.bitarts.parsian.action;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.dispatcher.StreamResult;

/**
 * Created by IntelliJ IDEA.
 * User: navid
 * Date: 7/19/11
 * Time: 10:02 AM
 */
public class DynamicStreamResult extends StreamResult {
    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        //get name of downloaded file
        String downloadedFileName = ""+invocation.getStack().findValue(conditionalParse("name", invocation));
        contentDisposition = "filename=\""+downloadedFileName + "\"";
        //get file size
        contentLength = ""+ invocation.getStack().findValue(conditionalParse("size", invocation));
        // get type of file
        contentType = ""+ invocation.getStack().findValue(conditionalParse("description", invocation));
        /*
        Executes the result given a final location
        (jsp page, action, etc) and
        the action invocation (the state in which
        the action was executed).
        */
        super.doExecute(finalLocation, invocation);

    }

}
