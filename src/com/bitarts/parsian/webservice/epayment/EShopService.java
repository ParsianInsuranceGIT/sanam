/**
 * EShopService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.epayment;

public interface EShopService extends javax.xml.rpc.Service {
    public java.lang.String getEShopServiceSoap12Address();

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getEShopServiceSoapAddress();

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
