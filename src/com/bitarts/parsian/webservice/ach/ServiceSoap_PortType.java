/**
 * ServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.ach;

public interface ServiceSoap_PortType extends java.rmi.Remote {
    public java.lang.String payRequest(java.lang.String userName, java.lang.String passwrd, java.lang.String requestDate, java.lang.String cited, int amount, java.lang.String reason, java.lang.String receiver, java.lang.String acountNo, java.lang.String propertyCode, java.lang.String usingUnit, java.lang.String registerNo, java.lang.String financialCode, java.lang.String taxDocumentNo, int companyType, java.lang.String addres, java.lang.String telNo, java.lang.String requestUnitId, java.lang.String requestUserId, int requestType, java.lang.String sign1, java.lang.String sign2, java.lang.String mobile, java.lang.String uniquecode) throws java.rmi.RemoteException;
    public java.lang.String cancel(java.lang.String userName, java.lang.String passwrd, java.lang.String traceCode) throws java.rmi.RemoteException;
    public com.bitarts.parsian.webservice.ach.GetGroupStateResponseGetGroupStateResult getGroupState(java.lang.String userName, java.lang.String passwrd, java.lang.String createDateFrom, java.lang.String createDateTo) throws java.rmi.RemoteException;
    public java.lang.String getBankTraceCode(java.lang.String userName, java.lang.String passwrd, java.lang.String traceCode) throws java.rmi.RemoteException;
    public java.lang.String insuranceCustomer(java.lang.String userName, java.lang.String passwrd, java.lang.String customerISSN, java.lang.String persianDate, java.lang.String type) throws java.rmi.RemoteException;
}
