package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.log.PishnehadChangeHistoryLog;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.ILogService;
import com.bitarts.parsian.service.IPishnehadService;
import com.bitarts.parsian.service.ITransitionLogService;
import com.bitarts.parsian.service.implementation.PishnehadService;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Set;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 5:23 PM
 */
public class TransitionLogAction extends BaseAction implements ServletContextAware {
    private ITransitionLogService transitionLogService;
    private ILogService logService;
    private IPishnehadService pishnehadService;
    private DarkhastBazkharid darkhastBazkharid;
    private DarkhastTaghirat darkhastTaghir;
    private Pishnehad pishnehad;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.transitionLogService = (ITransitionLogService) wac.getBean(ITransitionLogService.SERVICE_NAME);
        this.logService = (ILogService) wac.getBean(ILogService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
    }

    private String pishnehadId;
    private List<TransitionLog> transitionLogs;
    private Set<PishnehadChangeHistoryLog> historyLogs;
    public String showDarkhastBazkharidTransitionLog(){
        transitionLogs = transitionLogService.getDarkhastBazkharidLogs(darkhastBazkharid.getId());
        return SUCCESS;
    }
    public String showDarkhastTaghirTransitionLog(){
        transitionLogs = transitionLogService.getDarkhastTaghirLogs(darkhastTaghir.getId());
        return SUCCESS;
    }
    public String showTransitionLog() {
        Integer pishnehadIdInteger = Integer.parseInt(pishnehadId);
        transitionLogs = transitionLogService.getPishnehadLogs(pishnehadIdInteger);
        historyLogs = logService.getPishnehadHistoryLogs(pishnehadIdInteger);
        pishnehad = pishnehadService.findById(pishnehadIdInteger);
        return SUCCESS;
    }

    public List<TransitionLog> getTransitionLogs() {
        return transitionLogs;
    }

    public void setTransitionLogs(List<TransitionLog> transitionLogs) {
        this.transitionLogs = transitionLogs;
    }

    public Set<PishnehadChangeHistoryLog> getHistoryLogs() {
        return historyLogs;
    }

    public void setHistoryLogs(Set<PishnehadChangeHistoryLog> historyLogs) {
        this.historyLogs = historyLogs;
    }

    public String getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(String pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public DarkhastTaghirat getDarkhastTaghir() {
        return darkhastTaghir;
    }

    public void setDarkhastTaghir(DarkhastTaghirat darkhastTaghir) {
        this.darkhastTaghir = darkhastTaghir;
    }
}
