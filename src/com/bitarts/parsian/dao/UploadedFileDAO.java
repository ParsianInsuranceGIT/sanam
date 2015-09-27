package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.UploadedFile;

/**
 * Created with IntelliJ IDEA.
 * User: Ashki
 * Date: 12/18/12
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class UploadedFileDAO extends BaseDAO {

    public boolean saveUploadedFile(UploadedFile file) {
        super.save(file);
        return true;
    }

}
