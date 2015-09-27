package com.bitarts.parsian.service;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.log.PishnehadChangeHistoryLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.viewModel.PishnehadFieldChanges;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:51 PM
 */
public interface ILogService extends IBaseService {
    public static final String SERVICE_NAME = "logService";

    public void logPishnehadChanges(Pishnehad oldPishnehad, Pishnehad newPishnehad, User user);

    public Set<PishnehadChangeHistoryLog> getPishnehadHistoryLogs(Integer pishnehadIdInteger);

    public List<PishnehadFieldChanges> getPishnehadChangesList(Pishnehad oldPishnehad, Pishnehad pishnehad);

    public void getBimeGozarOfPishnehadChangesList();

    public Elhaghiye mergeTwoElhaghiesForSameSaleAsar(Elhaghiye e1, Elhaghiye e2);

}
