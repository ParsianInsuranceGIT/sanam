package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.asnadeSodor.Mosharekat;
import com.bitarts.parsian.model.bimename.LogMosharekat;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogMosharekatDao extends BaseDAO {
    @Transactional
    public void updateAll(ArrayList<LogMosharekat> logs) {
        for (LogMosharekat l : logs) l.setCreatedDate(l.getMosharekat().getTarikhPayanDowre());
        saveOrUpdateAll(logs);
    }

    public Mosharekat findMosharekatById(Integer id) {
        return (Mosharekat) getSession().get(Mosharekat.class, id);
    }
}
