package com.bitarts.parsian.service;

import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.Transition;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 4:12 PM
 */
public interface ITransitionLogService extends IBaseService  {
    public static final String SERVICE_NAME = "transitionLogService";

    public void logDarkhastBazkharidTransition(User userId, DarkhastBazkharid darkhastBazkharid, Transition transition, String logmessage);
    public void logTransition(User userId, Pishnehad pishnehad, Transition transition, String logmessage);
    void logCreation(User user, Pishnehad pishnehad, String pishnehadCreationLogMessage);


    public List<TransitionLog> getPishnehadLogs(Integer pishnehadId);
    public List<TransitionLog> getDarkhastBazkharidLogs(Integer darkhastBazkharidId);
    public List<TransitionLog> getDarkhastTaghirLogs(Integer darkhastTaghirId);

    void logDarkhastTaghiratTransition(User theUser, DarkhastTaghirat theDarkhast, Transition transition, String logmessage);
    void logTaghiratCreation(User user, DarkhastTaghirat theDarkhast, String logmessage);
    public void logKhesaratTransition(User theUser, Khesarat khesarat, Transition transition, String logmessage);
    public void save(TransitionLog transitionLog);
}

