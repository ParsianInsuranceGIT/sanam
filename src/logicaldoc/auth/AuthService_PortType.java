/**
 * AuthService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.auth;

public interface AuthService_PortType extends java.rmi.Remote {
    public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public void logout(java.lang.String sid) throws java.rmi.RemoteException;
}
