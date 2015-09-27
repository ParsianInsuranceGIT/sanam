package com.bitarts.parsian.service;

import com.bitarts.parsian.model.bimename.LogMosharekat;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILogMosharekatService extends IBaseService {
    public static final String SERVICE_NAME = "logMosharekatService";


    public void update(ArrayList<LogMosharekat> logs);
}
