package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.LogGhest;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.viewModel.PishnehadConstants;
import com.bitarts.parsian.viewModel.PishnehadFieldChanges;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ElhaghieAction extends BaseAction implements ServletContextAware {
    private String realPath, sourceFilePath, destFileDIR;
    private ILoginService loginService;
    private boolean nosession;
    private Elhaghiye elhaghie;
    private List<Elhaghiye> elhaghieList;
    private List<String> infoElhaghie = new ArrayList<String>();
    private Elhaghiye elhaghiye;
    private IClinicService clinicService;
    private IElhaghieService elhaghieService;
    private DarkhastBazkharid darkhastBazkharid;
    private List<DarkhastBazkharid> darkhastBazkharids;
    private IDarkhastService darkhastService;
    private Bimename bimename;
    private IConstantItemsService constantItemsService;
    private INamayandeService namayandeService;
    private DarkhastTaghirat darkhastTaghirat;
    private ILogService logService;
    private List<PishnehadFieldChanges> pishnehadFieldChangesList;
//    private User user;
    private Pishnehad pishnehad;
    private Boolean option_changes = false;
    private LogGhest logGhest;
    private boolean showElhaghiye;
    private PishnehadConstants pishnehadConstants;
    private List<Tarh> tarhs;
    private List<User> karshenasha;
    private HashMap<String, Object> validationConstants;
    private boolean readOnlyMode;

    public boolean isReadOnlyMode()
    {
        return readOnlyMode;
    }

    public void setReadOnlyMode(boolean readOnlyMode)
    {
        this.readOnlyMode = readOnlyMode;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        realPath = servletContext.getRealPath("/");
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.elhaghieService = (IElhaghieService) wac.getBean(IElhaghieService.SERVICE_NAME);
        darkhastService = (IDarkhastService) wac.getBean("darkhastService");
        clinicService = (IClinicService) wac.getBean("clinicService");
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
        namayandeService = (INamayandeService) wac.getBean("namayandeService");
        logService = (ILogService) wac.getBean("logService");
    }

    public String loadElhaghie() {
        showElhaghiye = true;
        User theUser = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (theUser.getId() == -1) {
            nosession = true;
            return Constant.NOSESSION;
        }
        elhaghie = elhaghieService.findById(elhaghie.getId());
        if (elhaghie.getDarkhastBazkharid() != null) {
            darkhastBazkharid = elhaghie.getDarkhastBazkharid();
            darkhastBazkharids = loadDarkhastsByElhaghieType(elhaghie.getDarkhastBazkharid().getDarkhastType());
            bimename = darkhastBazkharid.getBimename();
            pishnehad = bimename.getPishnehad();
        }
        if (elhaghie.getDarkhast() != null && elhaghie.getDarkhast().getDarkhastTaghirat() != null)
            darkhastTaghirat = elhaghie.getDarkhast().getDarkhastTaghirat();
        if (elhaghie != null && elhaghie.getOldPishnehad() != null && elhaghie.getNewPishnehad() != null) {
            pishnehadFieldChangesList = logService.getPishnehadChangesList(elhaghie.getOldPishnehad(), elhaghie.getNewPishnehad());
            option_changes = false;
            for (PishnehadFieldChanges p : pishnehadFieldChangesList)
                if (p.isOption()) {
                    if (darkhastTaghirat.getHaveBareMali() == null)
                        darkhastTaghirat.setHaveBareMali(true);
                    option_changes = true;
                    break;
                }
            pishnehad = elhaghie.getNewPishnehad();
//            user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//            final List<LogGhest> tmpList = darkhastTaghirat.getLogGhestList();
//            if (tmpList != null && tmpList.size() > 0) {
//                Collections.sort(tmpList);
//                logGhest = tmpList.get(0);
//            }
        }
        else if (darkhastTaghirat != null && darkhastTaghirat.getOldPishnehad() != null && darkhastTaghirat.getNewPishnehad() != null) {
            pishnehadFieldChangesList = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
            option_changes = false;
            for (PishnehadFieldChanges p : pishnehadFieldChangesList)
                if (p.isOption()) {
                    if(darkhastTaghirat.getHaveBareMali()==null)
                        darkhastTaghirat.setHaveBareMali(true);
                    option_changes = true;
                    break;
                }
            pishnehad = darkhastTaghirat.getNewPishnehad();
//            user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            final List<LogGhest> tmpList = darkhastTaghirat.getLogGhestList();
            if (tmpList != null && tmpList.size() > 0) {
                Collections.sort(tmpList);
                logGhest = tmpList.get(0);
            }
        }
//        elhaghiye = elhaghie;
        karshenasha = darkhastService.findAllKarshenasForDarkhasts();
//        setTarhs(constantsService.listAllTarhs());
        pishnehadConstants = constantItemsService.findConstantsForPishnehadForm();

        //----- initialize Validation Constants ------------------
        String date = DateUtil.getCurrentDate();
        if (pishnehad.getBimename() != null) {
            date = pishnehad.getBimename().getTarikhSodour();
        }
        setValidationConstants(AsnadeSodorService.getValidationConstantsSet(pishnehad.getTarh(), date));
        //--------------------------------------------------------
        elhaghiye=elhaghie;
        readOnlyMode=true;
        return SUCCESS;
    }


    private List<DarkhastBazkharid> loadDarkhastsByElhaghieType(DarkhastBazkharid.DarkhastType darkhastType) {
        darkhastBazkharid = elhaghie.getDarkhastBazkharid();
        darkhastBazkharids = elhaghie.getBimename().getAllDarkhasts();
        bimename = darkhastBazkharid.getBimename();
        Iterator<DarkhastBazkharid> it = darkhastBazkharids.iterator();
        while (it.hasNext()) {
            DarkhastBazkharid db = it.next();
            if (!db.getDarkhastType().equals(darkhastType)) it.remove();
        }
        return darkhastBazkharids;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public Elhaghiye getElhaghie() {
        return elhaghie;
    }

    public void setElhaghie(Elhaghiye elhaghie) {
        this.elhaghie = elhaghie;
    }

    public IElhaghieService getElhaghieService() {
        return elhaghieService;
    }

    public void setElhaghieService(IElhaghieService elhaghieService) {
        this.elhaghieService = elhaghieService;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        if (darkhastBazkharid == null)
            if (elhaghie != null && elhaghie.getDarkhastBazkharid() != null)
                darkhastBazkharid = elhaghie.getDarkhastBazkharid();
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }


    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public List<DarkhastBazkharid> getDarkhastBazkharids() {
        return darkhastBazkharids;
    }

    public void setDarkhastBazkharids(List<DarkhastBazkharid> darkhastBazkharids) {
        this.darkhastBazkharids = darkhastBazkharids;
    }

    public String hazfDarkhast() {
        if (darkhastBazkharid.getId() != null)
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        if (darkhastBazkharid != null)
            darkhastService.removeDarkhast(darkhastBazkharid);
        return SUCCESS;
    }

    public String taiidDarkhastTaghirKod() {
        DarkhastBazkharid d = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        d.setEmzaKonandeAval(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeAval().getId()));
        d.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeDovom().getId()));
        d.getBimename().getPishnehad().getNamayande().setKodeNamayandeKargozar(darkhastBazkharid.getTaghirKodBe());
        d.setState(constantItemsService.findStateById(9170));
        d.setMatnElhaghie(darkhastBazkharid.getMatnElhaghie());
        d.setMozuElhaghie(darkhastBazkharid.getMozuElhaghie());
        darkhastService.updateDarkhastBazkharid(d);
        addActionMessage("با موفقیت اعلام به مالی شد.");
        return SUCCESS;
    }

    public String sabtTaiidDarkhastTaghirKod() {
        DarkhastBazkharid d = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        d.getBimename().getPishnehad().getNamayande().setKodeNamayandeKargozar(d.getTaghirKodBe());
        namayandeService.editNamayande(d.getBimename().getPishnehad().getNamayande());
        elhaghie = new Elhaghiye();
        elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHIRKOD);
        elhaghie.setBimename(d.getBimename());
        elhaghie.setCreatedDate(DateUtil.getCurrentDate());
        elhaghie.setTarikhAsar(d.getBimename().getTarikhShorou());
        elhaghie.setShomareElhaghiye(d.getShomareDarkhast());
        elhaghie.setDarkhastBazkharid(d);
        elhaghieService.sabteElhaghie(elhaghie);
        return SUCCESS;
    }

    public String printElhaghieTaghirKod() {
        try {
            final String s = loadElhaghie();
            if (s.equals(SUCCESS)) {
                destFileDIR = realPath + destFileDIR + "TaghirKod.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printElhaghieAzmayeshiA4() {
        try {
            elhaghieList = new LinkedList<Elhaghiye>();
            if (darkhastTaghirat.getId() != null) {
                DarkhastTaghirat dt = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                List<Elhaghiye> elTaghirAshkhasList = new ArrayList<Elhaghiye>();
                if (darkhastTaghirat.getDarkhast() != null)
                    elTaghirAshkhasList = darkhastTaghirat.getDarkhast().getElhaghiyeList();
                int i = 0;
                for (Elhaghiye e : dt.getDarkhast().getElhaghiyeList()) {
                    elhaghie = new Elhaghiye();
                    elhaghie.setId(e.getId());
                    loadElhaghie();
                    elhaghie.setCreatedDate(DateUtil.getCurrentDate());
                    if(i==0)
                    {
                        elhaghie.setMatn(elhaghiye.getMatn());
                        elhaghie.setMozoo(elhaghiye.getMozoo());
                        elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                    }
                    else
                    {
                        if(infoElhaghie.get(i).equals("NEW"))
                        {
                            elhaghie.setMatn(elTaghirAshkhasList.get(i).getMatn());
                            elhaghie.setMozoo(elTaghirAshkhasList.get(i).getMozoo());
                            elhaghie.setTarikhAsar(elTaghirAshkhasList.get(i).getTarikhAsar());
                        }
                        else
                        {
                            elhaghie.setMatn(elhaghiye.getMatn());
                            elhaghie.setMozoo(elhaghiye.getMozoo());
                            elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                        }
                    }

                    elhaghie.setMablagh(elhaghiye.getMablagh());
                    elhaghieList.add(elhaghie);
                    i++;
                }
                destFileDIR = realPath + destFileDIR + "elhaghiye_A4_azmayeshi.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            } else if (darkhastBazkharid.getId() != null) {
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                elhaghie = new Elhaghiye();
//                elhaghie.setId(elhaghiye.getId());
//                elhaghie.setCreatedDate(DateUtil.getCurrentDate());
                elhaghie.setMatn(elhaghiye.getMatn());
                elhaghie.setMozoo(elhaghiye.getMozoo());
                elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                if (darkhastBazkharid.getDarkhastType() == DarkhastBazkharid.DarkhastType.TOZIH) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TOZIH);
                    elhaghie.setMablagh(elhaghiye.getMablagh());
                    elhaghie.setCreatedDate(elhaghiye.getCreatedDate());

                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.TAGHIRKOD)) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHIRKOD);
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BAZKHARID)) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.BAZKHARID);
                } else {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.EBTAL);
                }
//                elhaghie.setMablagh(elhaghiye.getMablagh());
                elhaghie.setBimename(darkhastBazkharid.getBimename());
                elhaghieList.add(elhaghie);
                destFileDIR = realPath + destFileDIR + "elhaghiye_A4_azmayeshi.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }

        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printElhaghieAzmayeshiA5() {
        elhaghieList = new LinkedList<Elhaghiye>();
        try {
            if (darkhastTaghirat.getId() != null) {
                DarkhastTaghirat dt = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());

                List<Elhaghiye> elTaghirAshkhasList = new ArrayList<Elhaghiye>();
                if(darkhastTaghirat.getDarkhast()!=null)
                    elTaghirAshkhasList = darkhastTaghirat.getDarkhast().getElhaghiyeList();
                int i = 0;
                for (Elhaghiye e : dt.getDarkhast().getElhaghiyeList())
                {
                    elhaghie = new Elhaghiye();
                    elhaghie.setId(e.getId());
                    loadElhaghie();
                    elhaghie.setCreatedDate(DateUtil.getCurrentDate());
                    if (i == 0)
                    {
                        elhaghie.setMatn(elhaghiye.getMatn());
                        elhaghie.setMozoo(elhaghiye.getMozoo());
                        elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                    }
                    else
                    {
                        if (infoElhaghie.get(i).equals("NEW"))
                        {
                            elhaghie.setMatn(elTaghirAshkhasList.get(i).getMatn());
                            elhaghie.setMozoo(elTaghirAshkhasList.get(i).getMozoo());
                            elhaghie.setTarikhAsar(elTaghirAshkhasList.get(i).getTarikhAsar());
                        }
                        else
                        {
                            elhaghie.setMatn(elhaghiye.getMatn());
                            elhaghie.setMozoo(elhaghiye.getMozoo());
                            elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                        }
                    }

                    elhaghie.setMablagh(elhaghiye.getMablagh());
                    elhaghieList.add(elhaghie);
                    i++;
                }
                destFileDIR = realPath + destFileDIR + "elhaghiye_A5_azmayeshi.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            } else if (darkhastBazkharid.getId() != null) {
                darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
                elhaghie = new Elhaghiye();
//                elhaghie.setId(elhaghiye.getId());
//                elhaghie.setCreatedDate(DateUtil.getCurrentDate());
                elhaghie.setMatn(elhaghiye.getMatn());
                elhaghie.setMozoo(elhaghiye.getMozoo());
                elhaghie.setTarikhAsar(elhaghiye.getTarikhAsar());
                if (darkhastBazkharid.getDarkhastType() == DarkhastBazkharid.DarkhastType.TOZIH) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TOZIH);
                    elhaghie.setMablagh(elhaghiye.getMablagh());
                    elhaghie.setCreatedDate(elhaghiye.getCreatedDate());
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.TAGHIRKOD)) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHIRKOD);
                } else if (darkhastBazkharid.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BAZKHARID)) {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.BAZKHARID);
                } else {
                    elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.EBTAL);
                }
//                elhaghie.setMablagh(elhaghiye.getMablagh());
                elhaghie.setBimename(darkhastBazkharid.getBimename());
                elhaghieList.add(elhaghie);
                destFileDIR = realPath + destFileDIR + "elhaghiye_A5_azmayeshi.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }

        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printElhaghieFinalA5() {
        try {
            elhaghieList = new LinkedList<Elhaghiye>();
            if (elhaghiye != null) {
                // chap tak elhaghie
                elhaghie = new Elhaghiye();
                elhaghie.setId(elhaghiye.getId());
                final String s = loadElhaghie();
                elhaghieList.add(elhaghie);
                if (s.equals(SUCCESS)) {
                    destFileDIR = realPath + destFileDIR + "elhaghiye_A5_final.pdf";
                    boolean b = (new File(realPath + destFileDIR)).mkdirs();
                    File destFile = new File(destFileDIR);
                    if (!destFile.exists()) destFile.createNewFile();
                    JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
                }
            } else {
                DarkhastTaghirat dt = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                for (Elhaghiye e : dt.getDarkhast().getElhaghiyeList()) {
                    elhaghie = new Elhaghiye();
                    elhaghie.setId(e.getId());
                    loadElhaghie();
                    elhaghieList.add(elhaghie);
                }
                destFileDIR = realPath + destFileDIR + "elhaghiye_A5_final.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printElhaghieFinalA4() {
        try {
            elhaghieList = new LinkedList<Elhaghiye>();
            if(elhaghiye != null) {
                // chap tak elhaghie
                elhaghie = new Elhaghiye();
                elhaghie.setId(elhaghiye.getId());
                final String s = loadElhaghie();
                elhaghieList.add(elhaghie);
                if (s.equals(SUCCESS)) {
                    destFileDIR = realPath + destFileDIR + "elhaghiye_A4_final.pdf";
                    boolean b = (new File(realPath + destFileDIR)).mkdirs();
                    File destFile = new File(destFileDIR);
                    if (!destFile.exists()) destFile.createNewFile();
                    JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
                }
            } else {
                DarkhastTaghirat dt = darkhastService.findDarkhastTaghiratById(darkhastTaghirat.getId());
                for (Elhaghiye e : dt.getDarkhast().getElhaghiyeList()) {
                    elhaghie = new Elhaghiye();
                    elhaghie.setId(e.getId());
                    loadElhaghie();
                    elhaghieList.add(elhaghie);
                }
                destFileDIR = realPath + destFileDIR + "elhaghiye_A4_final.pdf";
                boolean b = (new File(realPath + destFileDIR)).mkdirs();
                File destFile = new File(destFileDIR);
                if (!destFile.exists()) destFile.createNewFile();
                JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            }
        } catch (JRException e) {
//           todo log e, and set proper message to user
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public HashMap<String, Object> getValidationConstants() {
        return validationConstants;
    }

    public void setValidationConstants(HashMap<String, Object> validationConstants) {
        this.validationConstants = validationConstants;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getDestFileDIR() {
        return destFileDIR;
    }

    public void setDestFileDIR(String destFileDIR) {
        this.destFileDIR = destFileDIR;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public IDarkhastService getDarkhastService() {
        return darkhastService;
    }

    public void setDarkhastService(IDarkhastService darkhastService) {
        this.darkhastService = darkhastService;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public String taiidDarkhastTozih() {
        DarkhastBazkharid d = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
        d.setMozuElhaghie(darkhastBazkharid.getMozuElhaghie());
        d.setMatnElhaghie(darkhastBazkharid.getMatnElhaghie());
        try {
            d.setEmzaKonandeAval(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeAval().getId()));
            d.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(darkhastBazkharid.getEmzaKonandeDovom().getId()));
        } catch (Exception ex) {
            return ERROR;
        }
        elhaghie = new Elhaghiye();
        elhaghie.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TOZIH);
        elhaghie.setBimename(d.getBimename());
        elhaghie.setCreatedDate(DateUtil.getCurrentDate());
        elhaghie.setTarikhAsar(d.getBimename().getTarikhShorou());
        elhaghie.setShomareElhaghiye(d.getShomareDarkhast());
        elhaghie.setDarkhastBazkharid(d);
        elhaghieService.sabteElhaghie(elhaghie);
        return SUCCESS;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public DarkhastTaghirat getDarkhastTaghirat() {
        return darkhastTaghirat;
    }

    public void setDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat) {
        this.darkhastTaghirat = darkhastTaghirat;
    }

    public ILogService getLogService() {
        return logService;
    }

    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    public List<PishnehadFieldChanges> getPishnehadFieldChangesList() {
        return pishnehadFieldChangesList;
    }

    public void setPishnehadFieldChangesList(List<PishnehadFieldChanges> pishnehadFieldChangesList) {
        this.pishnehadFieldChangesList = pishnehadFieldChangesList;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
    }

    public Boolean getOption_changes() {
        return option_changes;
    }

    public void setOption_changes(Boolean option_changes) {
        this.option_changes = option_changes;
    }

    public LogGhest getLogGhest() {
        return logGhest;
    }

    public void setLogGhest(LogGhest logGhest) {
        this.logGhest = logGhest;
    }

    public Elhaghiye getElhaghiye() {
        return elhaghiye;
    }

    public void setElhaghiye(Elhaghiye elhaghiye) {
        this.elhaghiye = elhaghiye;
    }

    public boolean isShowElhaghiye() {
        return showElhaghiye;
    }

    public void setShowElhaghiye(boolean showElhaghiye) {
        this.showElhaghiye = showElhaghiye;
    }

    public PishnehadConstants getPishnehadConstants() {
        return pishnehadConstants;
    }

    public void setPishnehadConstants(PishnehadConstants pishnehadConstants) {
        this.pishnehadConstants = pishnehadConstants;
    }

    public List<Tarh> getTarhs() {
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public List<User> getKarshenasha() {
        return karshenasha;
    }

    public void setKarshenasha(List<User> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public List<Elhaghiye> getElhaghieList() {
        return elhaghieList;
    }

    public void setElhaghieList(List<Elhaghiye> elhaghieList) {
        this.elhaghieList = elhaghieList;
    }

    public List<String> getInfoElhaghie()
    {
        return infoElhaghie;
    }

    public void setInfoElhaghie(List<String> infoElhaghie)
    {
        this.infoElhaghie = infoElhaghie;
    }
}
