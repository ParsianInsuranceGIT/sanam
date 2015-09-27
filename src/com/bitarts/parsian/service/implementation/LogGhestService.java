package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.LogGhestDAO;
import com.bitarts.parsian.model.asnadeSodor.CredebitForLogGhest;
import com.bitarts.parsian.model.asnadeSodor.LogGhest;
import com.bitarts.parsian.service.ILogGhestService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:51 PM
 */
public class LogGhestService implements ILogGhestService {
    private LogGhestDAO logGhestDAO;

    public LogGhestDAO getLogGhestDAO() {
        return logGhestDAO;
    }

    public void setLogGhestDAO(LogGhestDAO logGhestDAO) {
        this.logGhestDAO = logGhestDAO;
    }

    public void save(LogGhest logGhest) {
        logGhestDAO.saveOrUpdate(logGhest);
    }

    @Transactional
    public void saveAllCFLG(List<CredebitForLogGhest> cflgList) {
        logGhestDAO.saveOrUpdateAll(cflgList);
    }
}
