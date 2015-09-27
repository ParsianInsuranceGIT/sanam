/**
 * FolderServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.folder;

public class FolderServiceImplServiceLocator extends org.apache.axis.client.Service implements logicaldoc.folder.FolderServiceImplService {

    public FolderServiceImplServiceLocator() {
    }


    public FolderServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FolderServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FolderServiceImplPort
    private java.lang.String FolderServiceImplPort_address = "http://172.16.0.158:8080/logicaldoc/services/Folder";

    public java.lang.String getFolderServiceImplPortAddress() {
        return FolderServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FolderServiceImplPortWSDDServiceName = "FolderServiceImplPort";

    public java.lang.String getFolderServiceImplPortWSDDServiceName() {
        return FolderServiceImplPortWSDDServiceName;
    }

    public void setFolderServiceImplPortWSDDServiceName(java.lang.String name) {
        FolderServiceImplPortWSDDServiceName = name;
    }

    public logicaldoc.folder.FolderService getFolderServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FolderServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFolderServiceImplPort(endpoint);
    }

    public logicaldoc.folder.FolderService getFolderServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            logicaldoc.folder.FolderServiceImplServiceSoapBindingStub _stub = new logicaldoc.folder.FolderServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getFolderServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFolderServiceImplPortEndpointAddress(java.lang.String address) {
        FolderServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (logicaldoc.folder.FolderService.class.isAssignableFrom(serviceEndpointInterface)) {
                logicaldoc.folder.FolderServiceImplServiceSoapBindingStub _stub = new logicaldoc.folder.FolderServiceImplServiceSoapBindingStub(new java.net.URL(FolderServiceImplPort_address), this);
                _stub.setPortName(getFolderServiceImplPortWSDDServiceName());
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
        if ("FolderServiceImplPort".equals(inputPortName)) {
            return getFolderServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://folder.webservice.logicaldoc.com/", "FolderServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://folder.webservice.logicaldoc.com/", "FolderServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FolderServiceImplPort".equals(portName)) {
            setFolderServiceImplPortEndpointAddress(address);
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
