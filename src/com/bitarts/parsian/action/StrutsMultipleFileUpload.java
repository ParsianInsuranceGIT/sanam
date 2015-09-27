package com.bitarts.parsian.action;

import com.bitarts.parsian.model.BankInfo;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.Transition;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IClinicService;
import com.bitarts.parsian.service.IPishnehadService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/20/11
 * Time: 6:28 PM
 */
public class StrutsMultipleFileUpload extends ActionSupport implements ServletContextAware{
    IPishnehadService pishnehadService;
    private List<File> upload = new ArrayList<File>();
    private String uploadContentType;
    private String userid;
    private String transitionId;
    private String pishnehadId;
    private String logmessage;
    private List<Pishnehad> reportResult;
    private List<Pishnehad> viewReportResult;
    private List<Transition> allowedTransitions;

    public String execute() throws Exception {

        /*System.err.println("upload size: "+upload.length);
        for(int ii=0;ii<upload.length;ii++){
        }
        for(int i=0;i<upload.length;i++){
            String fullFileName = "c:/upload/"+pishnehadId+"/"+upload[i].getName();
            File theFile = new File(fullFileName);
            FileUtils.copyFile(upload[i], theFile);
        }               */
        return SUCCESS;
    }
    public String pardakhtBaFishMakeFinal(){
        //pishnehadService.transitPishnehad(Integer.parseInt(userid), pishnehadId,transitionId, logmessage);

        retrieve(Long.parseLong(userid));

        return SUCCESS;
    }
    public String retrieve(Long userId) {

//        reportResult = pishnehadService.findAllowedPishnehads(userId);

//        viewReportResult = pishnehadService.findAllowedToViewPishnehads(userId);
        allowedTransitions = new ArrayList<Transition>();
        //allowedTransitions = pishnehadService.findAllowedTransitionsForId(userId,reportResult.get(0).getId());
        /*for (Pishnehad pishnehad: reportResult){
            for(Transition transition: pishnehad.getState().getTransitionBegin()){

            }

        }*/
        for (Pishnehad pishnehad : reportResult) {
            for (Transition transition : pishnehad.getState().getTransitions()) {
                if(transition.getStateBegin().equals(pishnehad.getState())){
                    for (Role role : transition.getRoles()) {
                        for (User user : role.getUsers()) {
                            if (user.getId().equals(userId)) {
                                allowedTransitions.add(transition);
                            }
                        }
                    }
                }
            }
            Transition fake = new Transition();
            fake.setId(-1);
            allowedTransitions.add(fake);
        }
        return SUCCESS;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
    }

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(String transitionId) {
        this.transitionId = transitionId;
    }

    public String getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(String pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public String getLogmessage() {
        return logmessage;
    }

    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }

    public List<Pishnehad> getReportResult() {
        return reportResult;
    }

    public void setReportResult(List<Pishnehad> reportResult) {
        this.reportResult = reportResult;
    }

    public List<Pishnehad> getViewReportResult() {
        return viewReportResult;
    }

    public void setViewReportResult(List<Pishnehad> viewReportResult) {
        this.viewReportResult = viewReportResult;
    }

    public List<Transition> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(List<Transition> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }
}
