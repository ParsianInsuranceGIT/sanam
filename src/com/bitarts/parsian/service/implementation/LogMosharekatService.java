package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.LogMosharekatDao;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.bimename.LogMosharekat;
import com.bitarts.parsian.service.ILogMosharekatService;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogMosharekatService implements ILogMosharekatService {
    private LogMosharekatDao logMosharekatDao;

    public Elhaghiye findById(Integer id) {
        return (Elhaghiye) logMosharekatDao.getHibernateTemplate().load(Elhaghiye.class, id);
    }

    public LogMosharekatDao getLogMosharekatDao() {
        return logMosharekatDao;
    }

    public void setLogMosharekatDao(LogMosharekatDao logMosharekatDao) {
        this.logMosharekatDao = logMosharekatDao;
    }

    public void update(ArrayList<LogMosharekat> logs) {
        logMosharekatDao.updateAll(logs);

    }
}
