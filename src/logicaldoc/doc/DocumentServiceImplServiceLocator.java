/**
 * DocumentServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public class DocumentServiceImplServiceLocator extends org.apache.axis.client.Service implements logicaldoc.doc.DocumentServiceImplService {

    public DocumentServiceImplServiceLocator() {
    }


    public DocumentServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DocumentServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DocumentServiceImplPort
    private java.lang.String DocumentServiceImplPort_address = "http://172.16.0.158:8080/logicaldoc/services/Document";

    public java.lang.String getDocumentServiceImplPortAddress() {
        return DocumentServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DocumentServiceImplPortWSDDServiceName = "DocumentServiceImplPort";

    public java.lang.String getDocumentServiceImplPortWSDDServiceName() {
        return DocumentServiceImplPortWSDDServiceName;
    }

    public void setDocumentServiceImplPortWSDDServiceName(java.lang.String name) {
        DocumentServiceImplPortWSDDServiceName = name;
    }

    public logicaldoc.doc.DocumentService getDocumentServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DocumentServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDocumentServiceImplPort(endpoint);
    }

    public logicaldoc.doc.DocumentService getDocumentServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            logicaldoc.doc.DocumentServiceImplServiceSoapBindingStub _stub = new logicaldoc.doc.DocumentServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDocumentServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDocumentServiceImplPortEndpointAddress(java.lang.String address) {
        DocumentServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (logicaldoc.doc.DocumentService.class.isAssignableFrom(serviceEndpointInterface)) {
                logicaldoc.doc.DocumentServiceImplServiceSoapBindingStub _stub = new logicaldoc.doc.DocumentServiceImplServiceSoapBindingStub(new java.net.URL(DocumentServiceImplPort_address), this);
                _stub.setPortName(getDocumentServiceImplPortWSDDServiceName());
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
        if ("DocumentServiceImplPort".equals(inputPortName)) {
            return getDocumentServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "DocumentServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "DocumentServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DocumentServiceImplPort".equals(portName)) {
            setDocumentServiceImplPortEndpointAddress(address);
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
