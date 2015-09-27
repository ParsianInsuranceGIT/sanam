package com.bitarts.parsian.endpoints;

import com.bitarts.parsian.service.IWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 3/3/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(serviceName = "BitartsWebService")
public class WebServiceEndpoint {
    /*
    @Autowired
    private IWebService webService;
    @WebMethod
    public String createCredit(Integer amount, String description, String rcptName,
                                  String sarresidDate, String subsystemName, String type, String identifier) {
        return webService.createCredit(amount, description, rcptName, sarresidDate, subsystemName, type, identifier);
    }
    @WebMethod
    public String createDebit(Integer amount, String description, String rcptName,
                                  String sarresidDate, String subsystemName, String type, String identifier) {
        return webService.createDebit(amount, description, rcptName, sarresidDate, subsystemName, type, identifier);
    }
    @WebMethod
    public Integer queryRemainingWithIdentifier(String identifier, String subsystemName) {
        return webService.queryRemainingWithIdentifier(identifier, subsystemName);
    }
    @WebMethod
    public Integer queryRemainingWithTraceCode(String traceCode, String subsystemName) {
        return webService.queryRemainingWithTraceCode(traceCode, subsystemName);
    }
    */
}
