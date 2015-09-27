package com.bitarts.parsian.service.implementation;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.TransitionLogDAO;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.Transition;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.ITransitionLogService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 4:13 PM
 */
public class TransitionLogService implements ITransitionLogService {
    private TransitionLogDAO transitionLogDAO;

    public void setTransitionLogDAO(TransitionLogDAO transitionLogDAO) {
        this.transitionLogDAO = transitionLogDAO;
    }

    public void save(TransitionLog transitionLog)
    {
        transitionLogDAO.save(transitionLog);
    }

    public void logDarkhastBazkharidTransition(User user, DarkhastBazkharid darkhastBazkharid, Transition transition, String logmessage) {
        //To change body of implemented methods use File | Settings | File Templates.
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;


        TransitionLog transitionLog = new TransitionLog(date,time, user, darkhastBazkharid, transition);
        transitionLog.setMessage(logmessage);
        transitionLogDAO.save(transitionLog);
    }

    public void logTransition(User user, Pishnehad pishnehad, Transition transition, String logmessage) {
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;


        TransitionLog transitionLog = new TransitionLog(date,time, user, pishnehad, transition);
        transitionLog.setMessage(logmessage);
        transitionLogDAO.save(transitionLog);
    }

    @Transactional
    public void logCreation(User user, Pishnehad pishnehad, String pishnehadCreationLogMessage) {
        //To change body of implemented methods use File | Settings | File Templates.
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;


        TransitionLog transitionLog = new TransitionLog(date,time, user, pishnehad, null);
        transitionLog.setMessage(pishnehadCreationLogMessage);
        transitionLogDAO.save(transitionLog);
    }

    @Transactional
    public void logTaghiratCreation(User user, DarkhastTaghirat theDarkhast, String logmessage) {
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;

        TransitionLog transitionLog = new TransitionLog(date,time, user, theDarkhast, null);
        transitionLog.setMessage(logmessage);
        transitionLogDAO.save(transitionLog);
    }

    public List<TransitionLog> getDarkhastBazkharidLogs(Integer darkhastBazkharidId){
        return transitionLogDAO.findAllTransitionLogsByDarkhastBazkharidId(darkhastBazkharidId);
    }

    public List<TransitionLog> getDarkhastTaghirLogs(Integer darkhastTaghirId){
        return transitionLogDAO.findAllTransitionLogsByDarkhastTaghirId(darkhastTaghirId);
    }

    public void logDarkhastTaghiratTransition(User theUser, DarkhastTaghirat theDarkhast, Transition transition, String logmessage) {
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;


        TransitionLog transitionLog = new TransitionLog(date,time, theUser, theDarkhast, transition);
        transitionLog.setMessage(logmessage);
        transitionLogDAO.save(transitionLog);
    }

    public void logKhesaratTransition(User theUser, Khesarat khesarat, Transition transition, String logmessage) {
        String dateString = DateUtil.getCurrentDate();
        String date = dateString;
        Date theDate = new Date();
        String hours = Integer.toString(theDate.getHours());
        String minutes = Integer.toString(theDate.getMinutes());
        if (hours.length() == 1)
            hours = '0'+hours;
        if (minutes.length() == 1)
            minutes = '0'+minutes;
        String time = hours + ":" + minutes;


        TransitionLog transitionLog = new TransitionLog(date,time, theUser, khesarat, transition);
        transitionLog.setMessage(logmessage);
        transitionLogDAO.save(transitionLog);
    }

    public List<TransitionLog> getPishnehadLogs(Integer pishnehadId) {
        return transitionLogDAO.findAllTransitionLogByPishnehadId(pishnehadId);
    }
}
