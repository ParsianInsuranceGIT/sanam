package com.bitarts.parsian.service;

import logicaldoc.auth.AuthService_ServiceLocator;
import logicaldoc.doc.DocumentService;
import logicaldoc.doc.DocumentServiceImplServiceLocator;
import logicaldoc.doc.WsDocument;
import logicaldoc.doc.holders.WsDocumentHolder;
import logicaldoc.folder.FolderService;
import logicaldoc.folder.FolderServiceImplServiceLocator;
import logicaldoc.folder.WsFolder;
import logicaldoc.folder.holders.WsFolderHolder;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/17/12
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileService {
    public Long createFile(String folderName, String fileName, File uploadedFile) {
        try {
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = l.getAuthServiceImplPort().login("admin", "admin");
            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
            DocumentService documentService = docLocator.getDocumentServiceImplPort();
            FolderServiceImplServiceLocator folderLocator = new FolderServiceImplServiceLocator();
            FolderService folderService = folderLocator.getFolderServiceImplPort();
            WsFolderHolder folderHolder = new WsFolderHolder();
            WsFolder folder = new WsFolder();
            folder.setName(folderName);
            folder.setParentId(folderService.getRootFolder(sid).getId());
            folderHolder.value = folder;
            folderService.create(sid, folderHolder);
            RandomAccessFile raf = new RandomAccessFile(uploadedFile, "r");
            byte[] content = new byte[(int) raf.length()];
            while (raf.getFilePointer() < raf.length()) {
                content[((int) raf.getFilePointer())] = raf.readByte();
            }
            WsDocument document = new WsDocument();
            document.setFileName(fileName);
            document.setFileSize(uploadedFile.getTotalSpace());
            document.setFolderId(folderHolder.value.getId());
            WsDocumentHolder documentHolder = new WsDocumentHolder(document);
            documentService.create(sid, documentHolder, content);
            return documentHolder.value.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public class Document {
        private WsDocument doc;
        private byte[] bytes;

        public WsDocument getDoc() {
            return doc;
        }

        public void setDoc(WsDocument doc) {
            this.doc = doc;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }
    }

    public Document getDocument(Long fileId) {
        Document d = new Document();
        try {
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = l.getAuthServiceImplPort().login("admin", "admin");
            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
            DocumentService documentService = docLocator.getDocumentServiceImplPort();
            FolderServiceImplServiceLocator folderLocator = new FolderServiceImplServiceLocator();
            FolderService folderService = folderLocator.getFolderServiceImplPort();
            WsDocument theDoc = documentService.getDocument(sid, fileId);
            d.setDoc(theDoc);
            d.setBytes(documentService.getContent(sid, fileId));
            return d;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteFile(Long fileId) {
        try {
            AuthService_ServiceLocator l = new AuthService_ServiceLocator();
            String sid = l.getAuthServiceImplPort().login("admin", "admin");
            DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
            DocumentService documentService = docLocator.getDocumentServiceImplPort();
            documentService.delete(sid, fileId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
