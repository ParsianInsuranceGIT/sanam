package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.UploadedFileDAO;
import com.bitarts.parsian.model.UploadedFile;
import com.bitarts.parsian.service.IUploadedFileService;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/18/12
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class UploadedFileService implements IUploadedFileService  {
    private UploadedFileDAO uploadedFileDAO;

    public void setUploadedFileDAO(UploadedFileDAO uploadedFileDAO) {
        this.uploadedFileDAO = uploadedFileDAO;
    }

    public boolean saveUploadedFile(UploadedFile uploadedFile) {
        return uploadedFileDAO.saveUploadedFile(uploadedFile);
    }
}
