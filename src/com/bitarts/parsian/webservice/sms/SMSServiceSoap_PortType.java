/**
 * SMSServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.sms;

public interface SMSServiceSoap_PortType extends java.rmi.Remote {
    public java.lang.String insertSMS(java.lang.String userName, java.lang.String passwrd, java.lang.String firstName, java.lang.String lastName, java.lang.String cellPhone, java.lang.String SMSText, java.lang.String description, java.lang.String policyID, int appName) throws java.rmi.RemoteException;
}
