/**
 * AuthService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.auth;

public class AuthService_ServiceLocator extends org.apache.axis.client.Service implements logicaldoc.auth.AuthService_Service {

    public AuthService_ServiceLocator() {
    }


    public AuthService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AuthService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AuthServiceImplPort
    private java.lang.String AuthServiceImplPort_address = "http://172.16.0.158:8080/logicaldoc/services/Auth";

    public java.lang.String getAuthServiceImplPortAddress() {
        return AuthServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuthServiceImplPortWSDDServiceName = "AuthServiceImplPort";

    public java.lang.String getAuthServiceImplPortWSDDServiceName() {
        return AuthServiceImplPortWSDDServiceName;
    }

    public void setAuthServiceImplPortWSDDServiceName(java.lang.String name) {
        AuthServiceImplPortWSDDServiceName = name;
    }

    public logicaldoc.auth.AuthService_PortType getAuthServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuthServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuthServiceImplPort(endpoint);
    }

    public logicaldoc.auth.AuthService_PortType getAuthServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            logicaldoc.auth.AuthServiceSoapBindingStub _stub = new logicaldoc.auth.AuthServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAuthServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAuthServiceImplPortEndpointAddress(java.lang.String address) {
        AuthServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (logicaldoc.auth.AuthService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                logicaldoc.auth.AuthServiceSoapBindingStub _stub = new logicaldoc.auth.AuthServiceSoapBindingStub(new java.net.URL(AuthServiceImplPort_address), this);
                _stub.setPortName(getAuthServiceImplPortWSDDServiceName());
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
        if ("AuthServiceImplPort".equals(inputPortName)) {
            return getAuthServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://auth.webservice.logicaldoc.com/", "AuthService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://auth.webservice.logicaldoc.com/", "AuthServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AuthServiceImplPort".equals(portName)) {
            setAuthServiceImplPortEndpointAddress(address);
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
