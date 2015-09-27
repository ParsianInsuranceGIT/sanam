package com.bitarts.parsian.service;

import com.bitarts.parsian.model.UploadedFile;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/18/12
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IUploadedFileService extends IBaseService  {
    public static final String SERVICE_NAME = "uploadedFileService";

    public boolean saveUploadedFile(UploadedFile uploadedFile);
}
