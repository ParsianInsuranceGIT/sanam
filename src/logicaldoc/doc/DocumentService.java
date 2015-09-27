/**
 * DocumentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public interface DocumentService extends java.rmi.Remote {
    public logicaldoc.doc.WsDocument[] getDocuments(java.lang.String sid, long[] docIds) throws java.rmi.RemoteException;
    public void rename(java.lang.String sid, long docId, java.lang.String name) throws java.rmi.RemoteException;
    public void restore(java.lang.String sid, long docId, long folderId) throws java.rmi.RemoteException;
    public void unlock(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public void sendEmail(java.lang.String sid, long[] docIds, java.lang.String recipients, java.lang.String arg3, java.lang.String arg4) throws java.rmi.RemoteException;
    public void checkout(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public byte[] getContent(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public boolean isReadable(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public logicaldoc.doc.WsDocument[] getVersions(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public logicaldoc.doc.WsDocument getDocument(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public logicaldoc.doc.WsDocument createAlias(java.lang.String sid, long docId, long folderId) throws java.rmi.RemoteException;
    public logicaldoc.doc.WsDocument[] getRecentDocuments(java.lang.String sid, java.lang.Integer maxHits) throws java.rmi.RemoteException;
    public void move(java.lang.String sid, long docId, long folderId) throws java.rmi.RemoteException;
    public void lock(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public logicaldoc.doc.WsDocument[] list(java.lang.String sid, long folderId) throws java.rmi.RemoteException;
    public void create(java.lang.String sid, logicaldoc.doc.holders.WsDocumentHolder document, byte[] content) throws java.rmi.RemoteException;
    public void delete(java.lang.String sid, long docId) throws java.rmi.RemoteException;
    public void update(java.lang.String sid, logicaldoc.doc.WsDocument document) throws java.rmi.RemoteException;
    public void checkin(java.lang.String sid, long docId, java.lang.String comment, java.lang.String filename, boolean release, byte[] content) throws java.rmi.RemoteException;
}
