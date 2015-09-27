/**
 * FolderService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.folder;

public interface FolderService extends java.rmi.Remote {
    public void create(java.lang.String sid, logicaldoc.folder.holders.WsFolderHolder folder) throws java.rmi.RemoteException;
    public void rename(java.lang.String sid, long folderId, java.lang.String name) throws java.rmi.RemoteException;
    public void grantGroup(java.lang.String arg0, long arg1, long arg2, int arg3, boolean arg4) throws java.rmi.RemoteException;
    public void grantUser(java.lang.String arg0, long arg1, long arg2, int arg3, boolean arg4) throws java.rmi.RemoteException;
    public void move(java.lang.String sid, long folderId, long parentId) throws java.rmi.RemoteException;
    public boolean isWriteable(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.folder.WsFolder[] listChildren(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.folder.WsFolder[] getPath(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public void update(java.lang.String sid, logicaldoc.folder.WsFolder folder) throws java.rmi.RemoteException;
    public logicaldoc.folder.Right[] getGrantedGroups(java.lang.String arg0, long arg1) throws java.rmi.RemoteException;
    public void delete(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.folder.WsFolder getRootFolder(java.lang.String sid) throws java.rmi.RemoteException;
    public logicaldoc.folder.WsFolder[] list(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.folder.WsFolder getFolder(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public boolean isReadable(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.folder.Right[] getGrantedUsers(java.lang.String arg0, long arg1) throws java.rmi.RemoteException;
}
