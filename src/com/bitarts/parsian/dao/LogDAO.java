package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.log.PishnehadChangeHistoryLog;
import com.bitarts.parsian.model.log.PishnehadFields;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:49 PM
 */
public class LogDAO extends BaseDAO {
    public static final String pishnehad = "pishnehad";
    public void savePishnehadChangeHistoryLog(PishnehadChangeHistoryLog pishnehadChangeHistoryLog) {
        super.save(pishnehadChangeHistoryLog);
    }

    public List<PishnehadFields> findAllFields() {
        return super.findAll(PishnehadFields.class);
    }

    public Set<PishnehadChangeHistoryLog> findHistoryLogsByPishnehadId(Integer pishnehadIdInteger) {
        List<PishnehadChangeHistoryLog> pishnehadChangeHistoryLogList = (List<PishnehadChangeHistoryLog>) super.findByPropertyOfProperty(PishnehadChangeHistoryLog.class, pishnehad, PishnehadDAO.ID, pishnehadIdInteger);
        Set<PishnehadChangeHistoryLog> pishnehadChangeHistoryLogSet = new HashSet<PishnehadChangeHistoryLog>();
        for (PishnehadChangeHistoryLog pishnehadChangeHistoryLog : pishnehadChangeHistoryLogList) {
            pishnehadChangeHistoryLogSet.add(pishnehadChangeHistoryLog);
        }
        return pishnehadChangeHistoryLogSet;
    }
}
