package com.bitarts.parsian.service.logicaldoc;

import logicaldoc.auth.AuthService_ServiceLocator;
import logicaldoc.doc.DocumentService;
import logicaldoc.doc.DocumentServiceImplServiceLocator;
import logicaldoc.doc.WsDocument;
import logicaldoc.doc.holders.WsDocumentHolder;
import logicaldoc.folder.FolderService;
import logicaldoc.folder.FolderServiceImplServiceLocator;
import logicaldoc.folder.WsFolder;
import logicaldoc.folder.holders.WsFolderHolder;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 6/27/11
 * Time: 1:26 PM
 */
public class LDAuth {
  public static void main(String[] argv) throws IOException, ServiceException {
    // Please, do not remove this line from file template, here invocation of web service will be inserted
      AuthService_ServiceLocator l = new AuthService_ServiceLocator();
      String sid = l.getAuthServiceImplPort().login("admin", "admin");
      DocumentServiceImplServiceLocator docLocator = new DocumentServiceImplServiceLocator();
      DocumentService documentService = docLocator.getDocumentServiceImplPort();

      FolderServiceImplServiceLocator folderLocator = new FolderServiceImplServiceLocator();
      FolderService folderService = folderLocator.getFolderServiceImplPort();
      WsFolderHolder folderHolder = new WsFolderHolder();
      WsFolder folder = new WsFolder();
      folder.setDescription("This is made from a webservice call!");
      folder.setName("NavitFolder");
      folder.setParentId(folderService.getRootFolder(sid).getId());
      folderHolder.value = folder;
      folderService.create(sid, folderHolder);
      File file = new File("F:\\share\\Struts2.zip");
      RandomAccessFile raf = new RandomAccessFile(file,"r");
      byte[] content = new byte[(int) raf.length()];
      while(raf.getFilePointer() < raf.length()){
          content[((int) raf.getFilePointer())] = raf.readByte();
      }

      WsDocument document = new WsDocument();
      document.setFileName(file.getName());
      document.setFileSize(file.getTotalSpace());
      document.setFolderId(folderHolder.value.getId());
      WsDocumentHolder documentHolder = new WsDocumentHolder(document);
      documentService.create(sid,documentHolder,content);

  }
}