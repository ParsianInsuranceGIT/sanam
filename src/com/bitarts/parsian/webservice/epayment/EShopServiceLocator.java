/**
 * EShopServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.epayment;

import java.io.IOException;
import java.util.Properties;

public class EShopServiceLocator extends org.apache.axis.client.Service implements com.bitarts.parsian.webservice.epayment.EShopService {

    public EShopServiceLocator() {
    }


    public EShopServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EShopServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EShopServiceSoap12
    private java.lang.String EShopServiceSoap12_address = "/pecpaymentgateway/eshopservice.asmx";

    public java.lang.String getEShopServiceSoap12Address() {
        Properties prop = new Properties();
        try
        {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String property = prop.getProperty("EPaymentHost");
        return property + EShopServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EShopServiceSoap12WSDDServiceName = "EShopServiceSoap12";

    public java.lang.String getEShopServiceSoap12WSDDServiceName() {
        return EShopServiceSoap12WSDDServiceName;
    }

    public void setEShopServiceSoap12WSDDServiceName(java.lang.String name) {
        EShopServiceSoap12WSDDServiceName = name;
    }

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getEShopServiceSoap12Address());
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEShopServiceSoap12(endpoint);
    }

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bitarts.parsian.webservice.epayment.EShopServiceSoap12Stub _stub = new com.bitarts.parsian.webservice.epayment.EShopServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getEShopServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEShopServiceSoap12EndpointAddress(java.lang.String address) {
        EShopServiceSoap12_address = address;
    }


    // Use to get a proxy class for EShopServiceSoap
    private java.lang.String EShopServiceSoap_address = "/pecpaymentgateway/eshopservice.asmx";

    public java.lang.String getEShopServiceSoapAddress() {
        Properties prop = new Properties();
        try
        {
            prop.load(getClass().getClassLoader().getResourceAsStream("com/bitarts/parsian/config/appConfig.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String property = prop.getProperty("EPaymentHost");
        return property+EShopServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EShopServiceSoapWSDDServiceName = "EShopServiceSoap";

    public java.lang.String getEShopServiceSoapWSDDServiceName() {
        return EShopServiceSoapWSDDServiceName;
    }

    public void setEShopServiceSoapWSDDServiceName(java.lang.String name) {
        EShopServiceSoapWSDDServiceName = name;
    }

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getEShopServiceSoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEShopServiceSoap(endpoint);
    }

    public com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType getEShopServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bitarts.parsian.webservice.epayment.EShopServiceSoap_BindingStub _stub = new com.bitarts.parsian.webservice.epayment.EShopServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getEShopServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEShopServiceSoapEndpointAddress(java.lang.String address) {
        EShopServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bitarts.parsian.webservice.epayment.EShopServiceSoap12Stub _stub = new com.bitarts.parsian.webservice.epayment.EShopServiceSoap12Stub(new java.net.URL(getEShopServiceSoap12Address()), this);
                _stub.setPortName(getEShopServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bitarts.parsian.webservice.epayment.EShopServiceSoap_BindingStub _stub = new com.bitarts.parsian.webservice.epayment.EShopServiceSoap_BindingStub(new java.net.URL(getEShopServiceSoapAddress()), this);
                _stub.setPortName(getEShopServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EShopServiceSoap12".equals(inputPortName)) {
            return getEShopServiceSoap12();
        }
        else if ("EShopServiceSoap".equals(inputPortName)) {
            return getEShopServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "EShopService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EShopServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EShopServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EShopServiceSoap12".equals(portName)) {
            setEShopServiceSoap12EndpointAddress(address);
        }
        else 
if ("EShopServiceSoap".equals(portName)) {
            setEShopServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
