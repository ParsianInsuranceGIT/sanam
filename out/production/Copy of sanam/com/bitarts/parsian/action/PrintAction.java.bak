package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.check.Check;
import com.bitarts.parsian.model.constantItems.Constants;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.model.utility.ElhaghiyeSaleBimeIntComparator;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Constants.NSPConstants;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import com.opensymphony.xwork2.ActionContext;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.apache.struts2.views.jasperreports.JasperReportConstants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import org.apache.poi.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron3
 * Date: 4/20/11
 * Time: 5:04 PM
 */
public class PrintAction extends BaseAction implements ServletContextAware, JasperReportConstants
{
    private IPishnehadService pishnehadService;
    private IDarkhastService darkhastService;
    private IAsnadeSodorService asnadeSodorService;
    private ILogService logService;
    private ILoginService loginService;
    private IConstantItemsService constantItemsService;
    private ISequenceService sequenceService;
    private DarkhastBazkharid darkhastBazkharid;
    private ICheckService checkService;
    private IKhesaratService khesaratService;
    private ITransitionLogService transitionLogService;
    private ReportSharayetBardasht reportSharayetBardasht;
    String realPath = "";
    private String serialStart;
    private Bimename bimename;
    private String ghableTaghir;
    private String azTarikheSodoreBimename;
    private String taTarikheSodoreBimename;
    private BatchTaghsitVM batchTaghsitVMS;
    private String genreDaftarche;
    private Integer sanadId;
    private String shomareBimenameFrom;
    private String shomareBimenameTo;
    private CredebitVMReport  credebitVMReport;
    private List<CredebitVMReport> credebitVMReportList;
    private EtebarBedehiVM etebarBedehiVM;
    private EtebarBedehiVMReport etebarBedehiVMReport;
    private List<EtebarBedehiVMReport> etebarBedehiVMReportList;
    private List<Credebit>  bedehiCredebit;
    private String rcptName;
    private String vahedeSodorId;
    private String pishShomareBimename;
    private String sodorSanadDateFrom,sodorSanadDateTo;
    private Boolean isOmr;
    private Credebit bedehi;
    private String toDate;
    private String fromDate;
    private CashTurnoverReportVM cashTurnoverReport;
    private String formatReport;

    //b-h
    private InputStream fileCLRBank;
    private String fileCLRBank_name;
    //  b-h
    private Long namayandegiId_gozareshbedehi;


    public Long getNamayandegiId_gozareshbedehi() {
        return namayandegiId_gozareshbedehi;
    }

    public void setNamayandegiId_gozareshbedehi(Long namayandegiId_gozareshbedehi) {
        this.namayandegiId_gozareshbedehi = namayandegiId_gozareshbedehi;
    }

    public void setVaziatMaliBimeNameReport(soratVaziatMaliBimeNameReport vaziatMaliBimeNameReport) {
        this.vaziatMaliBimeNameReport = vaziatMaliBimeNameReport;
    }

    public soratVaziatMaliBimeNameReport getVaziatMaliBimeNameReport() {

        return vaziatMaliBimeNameReport;
    }

    //b-h
    private soratVaziatMaliBimeNameReport vaziatMaliBimeNameReport;



//    public void setSooratVaziatMaliList(List<sooratVaziatMali_new> sooratVaziatMaliList) {
//        this.sooratVaziatMaliList = sooratVaziatMaliList;
//    }
//
//    public List<sooratVaziatMali_new> getSooratVaziatMaliList() {
//
//        return sooratVaziatMaliList;
//    }


    public void setFileCLRBank(InputStream fileCLRBank) {
        this.fileCLRBank = fileCLRBank;
    }

    public void setFileCLRBank_name(String fileCLRBank_name) {
        this.fileCLRBank_name = fileCLRBank_name;
    }

    public String getFileCLRBank_name() {

        return fileCLRBank_name;
    }

    public InputStream getFileCLRBank() {
        return fileCLRBank;
    }

    public String getFormatReport()
    {
        return formatReport;
    }

    public void setFormatReport(String formatReport)
    {
        this.formatReport = formatReport;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }

    public CashTurnoverReportVM getCashTurnoverReport()
    {
        return cashTurnoverReport;
    }

    public void setCashTurnoverReport(CashTurnoverReportVM cashTurnoverReport)
    {
        this.cashTurnoverReport = cashTurnoverReport;
    }

    public Credebit getBedehi()
    {
        return bedehi;
    }

    public void setBedehi(Credebit bedehi)
    {
        this.bedehi = bedehi;
    }

    public void setServletContext(ServletContext servletContext) {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean(IDarkhastService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
        this.checkService = (ICheckService) wac.getBean(ICheckService.SERVICE_NAME);
        this.khesaratService = (IKhesaratService) wac.getBean(IKhesaratService.SERVICE_NAME);
        this.setTransitionLogService((ITransitionLogService) wac.getBean(ITransitionLogService.SERVICE_NAME));
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.logService = (ILogService) wac.getBean(ILogService.SERVICE_NAME);
    }


    public String makeGozaresheBordroyeSodor() {
        List<Bimename> bimenameList = pishnehadService.loadAllBimenameByFilter(azTarikheSodoreBimename, taTarikheSodoreBimename, "");
        List<BimenameForGozaresh> bimenameForGozareshList = new ArrayList<BimenameForGozaresh>();
        for (Bimename bimename : bimenameList) {
            BimenameForGozaresh bimenameForGozaresh = new BimenameForGozaresh();
            bimenameForGozaresh.setShomareBimename(bimename.getShomare());
            bimenameForGozaresh.setNameBimeGozar(bimename.getPishnehad().getBimeGozar().getShakhs().getName() + " " + bimename.getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi());
            bimenameForGozaresh.setNameBimeShode(bimename.getPishnehad().getBimeShode().getShakhs().getName() + " " + bimename.getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi());
            bimenameForGozareshList.add(bimenameForGozaresh);
        }
        pishnehadReport = new PishnehadReport();
        pishnehadReport.setBimenameForGozareshList(bimenameForGozareshList);

        try {
            destFileDIR = realPath + destFileDIR + "xlsTest.xls";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);

//           InputStream input = new FileInputStream(new File(realPath+sourceFilePath));
//            JasperDesign design = JRXmlLoader.load(input);
//            JasperReport report = JasperCompileManager.compileReport(design);
//            JasperPrint print = JasperFillManager.fillReport(report, new HashMap());
// //            JasperPrint print = JasperManager.fillReport(input, new HashMap(), jasperReports);
//
// //            OutputStream output = new FileOutputStream(new File("c:/output/JasperReport.pdf"));
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile= new FileOutputStream(new File(realPath+destFileDIR+"xlsTest.xls"));
// //            JasperExportManager.exportReportToPdfStream(print, output);
//
//            JRXlsExporter exporterXLS = new JRXlsExporter();
//            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
//            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//            exporterXLS.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getAzTarikheSodoreBimename() {
        return azTarikheSodoreBimename;
    }

    public void setAzTarikheSodoreBimename(String azTarikheSodoreBimename) {
        this.azTarikheSodoreBimename = azTarikheSodoreBimename;
    }

    public String getTaTarikheSodoreBimename() {
        return taTarikheSodoreBimename;
    }

    public void setTaTarikheSodoreBimename(String taTarikheSodoreBimename) {
        this.taTarikheSodoreBimename = taTarikheSodoreBimename;
    }

    private Pishnehad pishnehad;
    private String sourceFilePath;
    private String destFileDIR;

    public String printKholasePishnehad() {
        pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());

        Collections.sort(pishnehad.getEstefadeKonandeganAzSarmayeBime());

        try {
            if (pishnehad.getBimeGozar().getShakhs().getType().equals(Shakhs.BimeGozarType.HOGHOGHI)) {
                pishnehad.getBimeGozar().getShakhs().setTypeString("HOGHOGHI");
            } else {
                pishnehad.getBimeGozar().getShakhs().setTypeString("HAGHIGHI");
            }

            destFileDIR = realPath + destFileDIR + "kholasePishnahad.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/vaziateSalamatiBimeShode.jrxml", realPath + "report/vaziateSalamatiBimeShode.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/vaziateSalamatiKhanevadeyeBimeShode.jrxml", realPath + "report/vaziateSalamatiKhanevadeyeBimeShode.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/savabegheBimei.jrxml", realPath + "report/savabegheBimei.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/estefadeKonandeganAzSarmayeBime.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printPishnehad() {
        pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());

        Collections.sort(pishnehad.getEstefadeKonandeganAzSarmayeBime());

        try {
            if (pishnehad.getBimeGozar().getShakhs().getType().equals(Shakhs.BimeGozarType.HOGHOGHI)) {
                pishnehad.getBimeGozar().getShakhs().setTypeString("HOGHOGHI");
            } else {
                pishnehad.getBimeGozar().getShakhs().setTypeString("HAGHIGHI");
            }

            destFileDIR = realPath + destFileDIR + "pishnehad.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/vaziateSalamatiBimeShode.jrxml", realPath + "report/vaziateSalamatiBimeShode.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/vaziateSalamatiKhanevadeyeBimeShode.jrxml", realPath + "report/vaziateSalamatiKhanevadeyeBimeShode.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/savabegheBimei.jrxml", realPath + "report/savabegheBimei.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/estefadeKonandeganAzSarmayeBime.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    PishnehadReport pishnehadReport;
    private Boolean correctCalculation;
    Credebit credebitReport;
    private List<Credebit> credebitReportList;

    public String printMohasebateRiazi() throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        Pishnehad p = pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId());
        if (pishnehadReport.getPishnehad().getEstelam() != null && (p.getBimename() == null || p.getBimename().getReadyToShow().equals("no"))) {
            p.getEstelam().setSen_bime_shode(pishnehadReport.getPishnehad().getEstelam().getSen_bime_shode());
            pishnehadService.updatePishnehad(p);
        }
        pishnehadReport.setPishnehad(p);
        Bimename bimename = pishnehadReport.getPishnehad().getBimename();
        pishnehadReport.setFRsList(new ArrayList<FRs>());
        if ((bimename != null && bimename.getElhaghiyehaDarJaryan().size() > 0) || (bimename != null && ghableTaghir != null && ghableTaghir.equals("yes") && bimename.getElhaghiyehaDaem().size() > 0)) {
            if (pishnehadReport.getCurrentElhaghiye() != null && ghableTaghir != null && ghableTaghir.equals("yes")) {
                for (Elhaghiye e : bimename.getElhaghiyeha()) {
                    if (e.getId().equals(pishnehadReport.getCurrentElhaghiye().getId())) {
                        e.setState(constantItemsService.findStateById(Constant.ELHAGHIYE_INITIAL_STATE));
                    }
                }
            }
            List<Elhaghiye> elhaghiyeList = bimename.getElhaghiyehaDarJaryan();
            if (ghableTaghir != null && ghableTaghir.equals("yes")) {
                elhaghiyeList = bimename.getElhaghiyehaDaem();
                pishnehadReport.setElhaghiyeDaemha(true);
                if (elhaghiyeList.size() == 0) {
                    int moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename());
                    pishnehadReport.setOldPishnehad(pishnehadReport.getPishnehad());
                    pishnehadReport.setNewPishnehad(pishnehadReport.getPishnehad());
                    pishnehadReport.setCurrentElhaghiye(null);
                    correctCalculation = calculate(pishnehadReport, moddat);
                }
            }
            Collections.sort(elhaghiyeList, new ElhaghiyeSaleBimeIntComparator());
//            for (int i = 0; i < elhaghiyeList.size(); i++) {
//                if (i < elhaghiyeList.size() - 1) {
//                    if (elhaghiyeList.get(i).getSaleBimeiAsar() == elhaghiyeList.get(i + 1).getSaleBimeiAsar()) {
//                        Elhaghiye mergedOne = logService.mergeTwoElhaghiesForSameSaleAsar(elhaghiyeList.get(i), elhaghiyeList.get(i + 1));
//                        elhaghiyeList.remove(i + 1);
//                        elhaghiyeList.remove(i);
//                        elhaghiyeList.add(i, mergedOne);
//                    }
//                }
//            }
            if (elhaghiyeList.size() > 0 && elhaghiyeList.get(0).getSaleBimeiAsar() != 0) {
                pishnehadReport.setPishnehad(bimename.getFirstPishnehad());
                pishnehadReport.setOldPishnehad(pishnehadReport.getPishnehad());
                pishnehadReport.setNewPishnehad(pishnehadReport.getPishnehad());
                pishnehadReport.setCurrentElhaghiye(elhaghiyeList.get(0));
                calculate(pishnehadReport, elhaghiyeList.get(0).getSaleBimeiAsar());
            }
            Pishnehad cachedPishnehadForSameSaleAsar = null;
            for (int i = 0; i < elhaghiyeList.size(); i++) {
                int moddat;
                if (i == elhaghiyeList.size() - 1) {
                    if (!bimename.getFirstPishnehad().getEstelam().getModat_bimename().equals(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getModat_bimename())) {
                        moddat = Integer.parseInt(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getModat_bimename()) - elhaghiyeList.get(i).getSaleBimeiAsar();
                    } else {
                        moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename()) - elhaghiyeList.get(i).getSaleBimeiAsar();
                    }
                } else {
                    if(elhaghiyeList.get(i + 1).getSaleBimeiAsar() == elhaghiyeList.get(i).getSaleBimeiAsar()) {
                        cachedPishnehadForSameSaleAsar = elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad();
                        continue;
                    } else {
                        moddat = elhaghiyeList.get(i + 1).getSaleBimeiAsar() - elhaghiyeList.get(i).getSaleBimeiAsar();
                    }
                }
                if (elhaghiyeList.get(i).getDarkhast().getDarkhastType().equals(Darkhast.DarkhastType.TAGHYIRAT)) {
                    pishnehadReport.setPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad());
                    if(cachedPishnehadForSameSaleAsar != null) {
                        pishnehadReport.setOldPishnehad(cachedPishnehadForSameSaleAsar);
                    } else {
                        pishnehadReport.setOldPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad());
                        cachedPishnehadForSameSaleAsar = null;
                    }
                    if (elhaghiyeList.get(i).getSaleBimeiAsar() > 0 &&
                            elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal() != null &&
                            elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").equals(
                                    elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getOldPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", "")
                            )) {
                        elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad().getEstelam().setMablagh_seporde_ebtedaye_sal("0");
                    }
                    pishnehadReport.setNewPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastTaghirat().getNewPishnehad());
                } else {
                    pishnehadReport.setPishnehad(elhaghiyeList.get(i).getDarkhast().getDarkhastBazkharid().getBimename().getPishnehad());
                }
                pishnehadReport.setCurrentElhaghiye(elhaghiyeList.get(i));
                calculate(pishnehadReport, moddat);
            }
        } else {
            int moddat = Integer.parseInt(pishnehadReport.getPishnehad().getEstelam().getModat_bimename());
            pishnehadReport.setCurrentElhaghiye(null);
            correctCalculation = calculate(pishnehadReport, moddat);
        }
        try {
            destFileDIR = realPath + destFileDIR + "mohasebateRiazi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/mohasebateRiazi_row.jrxml", realPath + "report/mohasebateRiazi_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printSoratVaziateMali() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        List<Ghest> ghestList = new ArrayList<Ghest>();
        List<Credebit> credebitList = new ArrayList<Credebit>();
        for (GhestBandi gb : pishnehadReport.getPishnehad().getBimename().getGhestBandiList()) {
            if(gb.getType().equals(GhestBandi.Type.G_BIMENAME))
            {
                ghestList.addAll(gb.getGhestList());
            }
        }
        for (Credebit c : pishnehadReport.getPishnehad().getBimename().getCredebitList()) {
            if (c.getCredebitType() == Credebit.CredebitType.GHEST) {
                credebitList.add(c);
            }
//            if (c.getCredebitType() == Credebit.CredebitType.ETEBAR_EBTAL || c.getCredebitType() == Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR) {
            if (c.getCredebitType() == Credebit.CredebitType.ETEBAR_EBTAL) {
                c.setAmount("-" + c.getAmount());
                credebitList.add(c);
            }
        }
        pishnehadReport.setGhestList(ghestList);
        pishnehadReport.setCredebitList(credebitList);
        Collections.sort(credebitList);
        try {
            Double total = 0.0;
            Double kolleMablagh = 0.0;
            Collections.sort(ghestList);
            Double amountElhaghie = 0.0;
            for (Ghest ghest : ghestList) {
                total = total + Double.parseDouble(ghest.getCredebit().getRemainingAmount().replace(",", ""));
                kolleMablagh = kolleMablagh + Double.parseDouble(ghest.getCredebit().getAmount().replace(",", ""));
                for(KhateSanad ks : ghest.getCredebit().getKhateSanadsBedehi()) {
                    if(ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)) {
                        amountElhaghie += ks.getAmount_long();
                    }
                }
            }
            Double pardakht = kolleMablagh - total;
            pardakht -= amountElhaghie;
            pishnehadReport.setMizanBedehi(total);
            if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_EBTAL)) {
                pishnehadReport.setSumOfBedehi(0.0);
            } else {
                pishnehadReport.setSumOfBedehi(kolleMablagh);
            }
            pishnehadReport.setSumOfPardakht(pardakht);
            destFileDIR = realPath + destFileDIR + "soratVaziateMali.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMali_etebar_row.jrxml", realPath + "report/soratVaziateMali_etebar_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMali_bedehi_row.jrxml", realPath + "report/soratVaziateMali_bedehi_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMali_row.jrxml", realPath + "report/soratVaziateMali_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printSoartVaziateBimeName() {
        Pishnehad p = pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId());
        if(pishnehadReport.getTarikhMabna()==null)pishnehadReport.setTarikhMabna(DateUtil.getCurrentDate());
        CredebitAction.mohasebeAndukhteBimename(p.getBimename(), pishnehadReport.getTarikhMabna(),null);
//        CredebitAction.mohasebeAndukhteBimename(p.getBimename(), "1392/09/04");
        pishnehadReport.setPishnehad(p);
        if (DateUtil.getBimeYear(pishnehadReport.getTarikhMabna(), p.getBimename().getTarikhShorou()) + 1 > p.getBimename().getGhestBandiList().size()) {
            addActionError("كليه سال هاي بيمه اي قبل از تاريخ مبنا اعلام به مالي شود");
            return "nosuccess";
        }
        List<Ghest> ghestList = asnadeSodorService.findAllGhests(pishnehadReport.getPishnehad().getBimename().getId());
        Double total = 0.0;
        Double kolleMablagh = 0.0;
        Double pardakht = 0.0;
        Double amountElhaghie = 0.0;
        for (Ghest ghest : ghestList) {
            total = total + Double.parseDouble(ghest.getCredebit().getRemainingAmount().replace(",", ""));
            kolleMablagh = kolleMablagh + Double.parseDouble(ghest.getCredebit().getAmount().replace(",", ""));
            if (ghest.getCredebit().getPaidAmount() > 0
                    && DateUtil.isGreaterThanOrEqual(pishnehadReport.getTarikhMabna(), ghest.getCredebit().getKhateSanadsBedehi().get(0).getSanad().getCreatedDate())) {
                pardakht = pardakht + ghest.getCredebit().getPaidAmount();
            }
            for (KhateSanad ks : ghest.getCredebit().getKhateSanadsBedehi()) {
                if (ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)) {
                    amountElhaghie += ks.getAmount_long();
                }
            }
        }
//        Double pardakht = kolleMablagh - total;
        pishnehadReport.setHaghBimeElamBeMali(NumberFormat.getNumberInstance().format(kolleMablagh));
        pishnehadReport.setHaghBimePardakhti(NumberFormat.getNumberInstance().format(pardakht - amountElhaghie));
        pishnehadReport.setHaghBimeMande(NumberFormat.getNumberInstance().format(total));

        double andukhteDouble = pishnehadReport.getPishnehad().getBimename().getAndukhteyeGhatie();
        long resultArzesh = DarkhastAction.calcHadeAksarBardasht(pishnehadReport.getPishnehad().getBimename(), pishnehadReport.getPishnehad().getBimename().getAndukhteyeGhatieWithTarikhMabna(pishnehadReport.getTarikhMabna()), asnadeSodorService);
        hadeAksarBardasht = NumberFormat.getNumberInstance().format(resultArzesh);

        pishnehadReport.setHadeAksareBardasht(hadeAksarBardasht);
        if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_EBTAL) || pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_BAZKHARID)) {
            pishnehadReport.setHaghBimeMande("0");
            pishnehadReport.getPishnehad().getEstelam().setSarmaye_paye_fot("0");
            pishnehadReport.getPishnehad().getEstelam().setNerkh_afzayesh_salaneh_sarmaye_fot("0");
            pishnehadReport.getPishnehad().getEstelam().setNerkh_afzayesh_salaneh_hagh_bime("0");
            pishnehadReport.getPishnehad().getEstelam().setSarmaye_paye_fot_dar_asar_hadese("0");
            pishnehadReport.getPishnehad().getEstelam().setSarmaye_pooshesh_amraz_khas("0");
            pishnehadReport.getPishnehad().getEstelam().setSarmaye_pooshesh_naghs_ozv("0");
            pishnehadReport.getPishnehad().getEstelam().setHagh_bime_pardakhti("0");
            pishnehadReport.getPishnehad().getEstelam().setMablagh_seporde_ebtedaye_sal("0");
            pishnehadReport.getPishnehad().getEstelam().setNahve_pardakht_hagh_bime("-");
            pishnehadReport.getPishnehad().getEstelam().setModat_bimename("0");
        }
        if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_EBTAL)) {
            pishnehadReport.setHaghBimeElamBeMali("0");
        }
        try {
            destFileDIR = realPath + destFileDIR + "printBimeName.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public static Boolean calculate(PishnehadReport pishnehadReport, int moddat) throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        boolean correctCalculation;
        Estelam estelam = pishnehadReport.getPishnehad().getEstelam();
        boolean pooshesh_moafiat_b = false;
        boolean pooshesh_fot_dar_asar_zelzele_b = false;
        boolean pooshesh_naghs_ozv_b = false;
        String tmpDate = DateUtil.getCurrentDate();
        if (estelam.getPishnehad().getBimename() != null)
            tmpDate = estelam.getPishnehad().getBimename().getTarikhSodour();
        String dateForSaghfSarmayeha = tmpDate;
        if(pishnehadReport.getCurrentElhaghiye() != null)
            dateForSaghfSarmayeha = pishnehadReport.getCurrentElhaghiye().getTarikhAsar();
        double nerkhBahreFanni = AsnadeSodorService.getNerkhBahreFanni(tmpDate, pishnehadReport.getPishnehad().getTarh());

        long hadeAksarSarFot = AsnadeSodorService.getHadeAksarSarmayeFot(dateForSaghfSarmayeha, pishnehadReport.getPishnehad().getTarh());
        long hadeAksarSarmayeFotHadese = AsnadeSodorService.getHadeAksarSarmayeFot(dateForSaghfSarmayeha, pishnehadReport.getPishnehad().getTarh());
        long hadeAksarAmraz = AsnadeSodorService.getHadeAksarSarmayeFotAmraz(dateForSaghfSarmayeha, pishnehadReport.getPishnehad().getTarh());

        double nerkhPusheshZelzele = AsnadeSodorService.getNerkhPusheshKhatarZelzele(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double karmozdGyekja = AsnadeSodorService.getKarmozdGyekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double karmozdyekja = AsnadeSodorService.getKarmozdYekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double bimegariGyekja = AsnadeSodorService.getBimeGariGyekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double bimegariyekja = AsnadeSodorService.getBimeGariYekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double edariGyekja = AsnadeSodorService.getEdariGyekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double vosulGyekja = AsnadeSodorService.getVosulGyekja(tmpDate, pishnehadReport.getPishnehad().getTarh());
        double[][] lifeTable = AsnadeSodorService.getLifeTable(tmpDate, pishnehadReport.getPishnehad().getTarh());
        Constants.PayeyeMohasebeHazineEdari payeyeMohasebat = AsnadeSodorService.getPayeyeMohasebeHazineEdari(tmpDate, pishnehadReport.getPishnehad().getTarh());
        if (estelam.getPooshesh_fot_dar_asar_haadese() != null && estelam.getPooshesh_fot_dar_asar_haadese().equals("yes")) {

        } else {
            estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        }

        if (estelam.getPooshesh_naghs_ozv() != null && estelam.getPooshesh_naghs_ozv().equals("yes")) {
            pooshesh_naghs_ozv_b = true;
        } else {
            pooshesh_naghs_ozv_b = false;
            estelam.setSarmaye_pooshesh_naghs_ozv("0");
        }
        if (estelam.getPooshesh_fot_dar_asar_zelzele() != null && estelam.getPooshesh_fot_dar_asar_zelzele().equals("yes")) {
            pooshesh_fot_dar_asar_zelzele_b = true;
        } else {
            pooshesh_fot_dar_asar_zelzele_b = false;
        }
        if (estelam.getPooshesh_amraz_khas() != null && estelam.getPooshesh_amraz_khas().equals("yes")) {
        } else {
            estelam.setSarmaye_pooshesh_amraz_khas("0");
        }
        if (estelam.getPooshesh_moafiat() != null && estelam.getPooshesh_moafiat().equals("yes")) {
            pooshesh_moafiat_b = true;
        } else {
            pooshesh_moafiat_b = false;
        }
        NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME nahve = null;
        if (estelam.getNahve_pardakht_hagh_bime() == null || estelam.getNahve_pardakht_hagh_bime().equals("sal")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SAALAANEH;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.MAAHAANEH;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("3mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SE_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("6mah")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.SHISH_MAAHE;
        } else if (estelam.getNahve_pardakht_hagh_bime().equals("yekja")) {
            nahve = NSPConstants.NAHVE_PARDAAKHT_HAGH_BIME.YEKJA;
        }
        NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH nahveDaryaft = null;
        if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename() != null) {
            if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("yekja")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.YEKJA;
            } else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_modat")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MODAT_MOAYAN;
            } else if (estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename().equals("mos_omr")) {
                nahveDaryaft = NSPConstants.NAHVE_DARYAAFT_ANDUKHTEH_ENTEHAAYE_MODAT_BIME_NAAMEH.MOSTAMERI_MADAMOL_OMR;
            }
        }
        NSPConstants.NAHVE_DARYAAFT_MOSTAMERI nahveDaryaftMostamari = null;
        if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("sal")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.SAALAANEH;
        } else if (estelam.getNahve_daryaft_mostamari() != null && estelam.getNahve_daryaft_mostamari().equals("mah")) {
            nahveDaryaftMostamari = NSPConstants.NAHVE_DARYAAFT_MOSTAMERI.MAAHAANEH;
        }
        NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI nahveKasrHaghBimePoosheshhayeEzafi = null;
        if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("mazad")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.MAZAD_BAR_HAGH_BIME_PAYE;
        } else if (estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi() != null && estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
            nahveKasrHaghBimePoosheshhayeEzafi = NSPConstants.NAHVE_KASR_HAGH_BIME_POOSHESHHAYE_EZAFI.AZ_HAGH_BIME_PAYE;
        }
        if (estelam.getSen_bime_shode() == null) estelam.setSen_bime_shode("0");
        if (estelam.getDarsad_ezafe_nerkh_pezeshki() == null || estelam.getDarsad_ezafe_nerkh_pezeshki().isEmpty())
            estelam.setDarsad_ezafe_nerkh_pezeshki("0");
        if (estelam.getSarmaye_paye_fot_dar_asar_hadese() == null) estelam.setSarmaye_paye_fot_dar_asar_hadese("0");
        if (estelam.getSarmaye_paye_fot() == null) estelam.setSarmaye_paye_fot("0");
        if (estelam.getSarmaye_pooshesh_amraz_khas() == null) estelam.setSarmaye_pooshesh_amraz_khas("0");
        if (estelam.getSarmaye_pooshesh_naghs_ozv() == null) estelam.setSarmaye_pooshesh_naghs_ozv("0");
        if (estelam.getNerkh_afzayesh_salaneh_hagh_bime() == null) estelam.setNerkh_afzayesh_salaneh_hagh_bime("0");
        if (estelam.getNerkh_afzayesh_salaneh_sarmaye_fot() == null) estelam.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
        if (estelam.getHagh_bime_pardakhti() == null) estelam.setHagh_bime_pardakhti("0");
        if (estelam.getMablagh_seporde_ebtedaye_sal() == null) estelam.setMablagh_seporde_ebtedaye_sal("0");
        if (estelam.getNerkh_afzayesh_salaneh_mostamari() == null) estelam.setNerkh_afzayesh_salaneh_mostamari("0");
        if (estelam.getModdat_zaman_daryaft_mostamari() == null) estelam.setModdat_zaman_daryaft_mostamari("0");
        if (estelam.getTabaghe_khatar() == null || estelam.getTabaghe_khatar().equalsIgnoreCase("null") || estelam.getTabaghe_khatar().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar("1");
        if (estelam.getTabaghe_khatar_naghsozv() == null || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("null") || estelam.getTabaghe_khatar_naghsozv().equalsIgnoreCase("undefined"))
            estelam.setTabaghe_khatar_naghsozv("1");
//        if(estelam.getDarsad_takhfif_bimegari() == null) estelam.setDarsad_takhfif_bimegari("0");
//        if(estelam.getDarsad_takhfif_edari() == null) darsad_takhfif_edari = "0";
//        if(estelam.getDarsad_takhfif_karmozd_hagh() == null) darsad_takhfif_karmozd_hagh = "0";
        if (estelam.getDarsad_takhfif_karmozd_karmozd() == null) estelam.setDarsad_takhfif_karmozd_karmozd("0");
        if (estelam.getDarsad_takhfif_vosool() == null) estelam.setDarsad_takhfif_vosool("0");

        BimeNaamehVaSarmayehGozari bimeNaamehVaSarmayehGozari = new BimeNaamehVaSarmayehGozari();
        correctCalculation = true;

        long sarmayeFotSaleAval = Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot()));
//        if(pishnehadReport.getPishnehad().getBimename() != null)
//            sarmayeFotSaleAval = pishnehadReport.getPishnehad().getBimename().getSarmayeFotSaleAvval();
        boolean enteghalFot = false;
        boolean enteghalHadese = false;
        boolean enteghalAmraz = false;
        boolean enteghalNaghs = false;
        boolean enteghalHaghBime = false;
        if (pishnehadReport.getOldPishnehad() != null && pishnehadReport.getNewPishnehad() != null) {
            if (pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_paye_fot() != null && pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_paye_fot() != null
                    && pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_paye_fot().replaceAll(",","").equals(pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_paye_fot().replaceAll(",", "")))
                enteghalFot = true;
            if (pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_paye_fot_dar_asar_hadese() != null && pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_paye_fot_dar_asar_hadese() != null && pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_paye_fot_dar_asar_hadese().replaceAll(",", "").equals(pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_paye_fot_dar_asar_hadese().replaceAll(",", "")))
                enteghalHadese = true;
            if (pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_pooshesh_amraz_khas() != null && pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_pooshesh_amraz_khas() != null && pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_pooshesh_amraz_khas().replaceAll(",", "").equals(pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_pooshesh_amraz_khas().replaceAll(",", "")))
                enteghalAmraz = true;
            if (pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_pooshesh_naghs_ozv() != null && pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_pooshesh_naghs_ozv() != null && pishnehadReport.getOldPishnehad().getEstelam().getSarmaye_pooshesh_naghs_ozv().replaceAll(",", "").equals(pishnehadReport.getNewPishnehad().getEstelam().getSarmaye_pooshesh_naghs_ozv().replaceAll(",", "")))
                enteghalNaghs = true;
            if (pishnehadReport.getOldPishnehad().getEstelam().getHagh_bime_pardakhti().replaceAll(",", "").equals(pishnehadReport.getNewPishnehad().getEstelam().getHagh_bime_pardakhti().replaceAll(",", ""))
                    && pishnehadReport.getOldPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals(pishnehadReport.getNewPishnehad().getEstelam().getNahve_pardakht_hagh_bime()))
                enteghalHaghBime = true;
        }

        int salNaghs = -1;
        int salAmraz = -1;
        int salInvalid = 99;
        if (pishnehadReport.getPishnehad().getBimename() != null && pishnehadReport.getPishnehad().getBimename().getId() != null) {
            if (pishnehadReport.getPishnehad().getBimename().getAllDarkhasts() != null)
                for (DarkhastBazkharid k : pishnehadReport.getPishnehad().getBimename().getAllDarkhasts()) {
                    if (k.getDarkhastType()!=null && k.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.KHESARAT) && k.getKhesaratI()!=null && k.getKhesaratI().getElhaghiye()!=null) {
                        if(k.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.AMRAZ))
                            salAmraz = DateUtil.getBimeYear(k.getKhesaratI().getElhaghiye().getTarikhAsar(), pishnehadReport.getPishnehad().getBimename().getTarikhShorou())-1;
                        if (k.getKhesaratI().getKhesaratType().equals(Khesarat.KhesaratType.NAGHSOZV))
                            salNaghs = DateUtil.getBimeYear(k.getKhesaratI().getElhaghiye().getTarikhAsar(), pishnehadReport.getPishnehad().getBimename().getTarikhShorou())-1;

                    }
                }
            if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_EBTAL)) {
                salInvalid = 1;
            }
            if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_BAZKHARID)) {
                salInvalid = DateUtil.getBimeYear(pishnehadReport.getPishnehad().getBimename().getTarikhBazkharid(), pishnehadReport.getPishnehad().getBimename().getTarikhShorou());
            }
            if (pishnehadReport.getPishnehad().getBimename().getState().getId().equals(Constant.BIMENAME_BASTE)) {
                salInvalid = DateUtil.getBimeYear(pishnehadReport.getPishnehad().getBimename().getTarikhBasteShodan(), pishnehadReport.getPishnehad().getBimename().getTarikhShorou());
            }
        }

        BimenameInfo bInfo = new BimenameInfo(pishnehadReport.getPishnehad().getBimename());
        bInfo.setSalTaghsit(pishnehadReport.getSaleTaghsit());
        bInfo.setElhaghiyeDaemha(pishnehadReport.isElhaghiyeDaemha());


        try {
                List<FRs> tempList = bimeNaamehVaSarmayehGozari.finalReportsWithPreviousFRs(
                    //Sene_Bime_Shode,Darsad_Ezafe_Nerkh_Pezeshki,Modat_Bime_Naameh,
                    Short.parseShort(estelam.getSen_bime_shode()), Double.parseDouble(estelam.getDarsad_ezafe_nerkh_pezeshki()), (short) moddat,
                    //Sarmaye_Paaye_Fot_Rial, Sarmaye_Paaye_Fot_Dar_Asar_Hadese_Rial, Sarmaye_Pushesh_Amraaz_Khaas_Rial,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_paye_fot_dar_asar_hadese())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_amraz_khas())),
                    //pushesh_Moaafiyat, Pushesh_Naghs_Ozv,  Sarmaye_Pushesh_Naghs_Ozv,
                    pooshesh_moafiat_b, pooshesh_naghs_ozv_b, Long.parseLong(StringUtil.removeNoneDigits(estelam.getSarmaye_pooshesh_naghs_ozv()))
                    //Nerkh_Afzayesh_Saalaaneh_Hagh_Bime,   Nerkh_Afzayesh_Saalaaneh_Sarmaye_Fot,   Nahve_Pardaakht_Hagh_Bime,
                    , Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_hagh_bime()), Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_sarmaye_fot()), nahve,
                    //Hagh_Bime_Pardaakhti_Rial,  Mablagh_Seporde_Ebtedaye_Saal_Rial,   Nahve_Daryaft_Andukhte_Entehaye_Modat_Bime_Naameh,
                    Long.parseLong(StringUtil.removeNoneDigits(estelam.getHagh_bime_pardakhti())), Long.parseLong(StringUtil.removeNoneDigits(estelam.getMablagh_seporde_ebtedaye_sal())), nahveDaryaft,
                    //Nerkh_Afzayesh_Saalaaneh_Mostamari,  Nahve_Daryaft_Mostamri,      Modat_Zaman_Daryaft_Mostamari,
                    Double.parseDouble(estelam.getNerkh_afzayesh_salaneh_mostamari()), nahveDaryaftMostamari, Short.parseShort(estelam.getModdat_zaman_daryaft_mostamari()),
                    //Tabaghe_Khatar, Pushesh_Fot_Dar_Asar_Zelzele, Bimeh_Gari,  Edari,
                    Byte.parseByte(estelam.getTabaghe_khatar()), Byte.parseByte(estelam.getTabaghe_khatar_naghsozv()), pooshesh_fot_dar_asar_zelzele_b,
                    0.0//Double.parseDouble(estelam.getDarsad_takhfif_bimegari()),
                    , 0.0// Double.parseDouble(estelam.getDarsad_takhfif_edari()),
                    //Kaarmozd_Az_Mahal_Hagh_Bimeh,  Kaarmozd_Az_Mahal_Kaarmozd,  Hazineh_Vosul
                    , 0.0//Double.parseDouble(estelam.getDarsad_takhfif_karmozd_hagh())
                    , Double.parseDouble(estelam.getDarsad_takhfif_karmozd_karmozd()), Double.parseDouble(estelam.getDarsad_takhfif_vosool()), nerkhBahreFanni, hadeAksarSarFot
                    , hadeAksarSarmayeFotHadese, hadeAksarAmraz, nerkhPusheshZelzele, karmozdGyekja, karmozdyekja, bimegariGyekja, bimegariyekja, edariGyekja, vosulGyekja, lifeTable,
                    tmpDate, pishnehadReport.getPishnehad().getTarh(), nahveKasrHaghBimePoosheshhayeEzafi, payeyeMohasebat, pishnehadReport.getPreviousFRs(), pishnehadReport.getCurrentFRs(), sarmayeFotSaleAval,
                    enteghalFot, enteghalHadese, enteghalAmraz, enteghalNaghs, enteghalHaghBime, salNaghs, salAmraz, salInvalid, bInfo);
                pishnehadReport.setPreviousFRs(tempList.get(tempList.size() - 2));
                pishnehadReport.setCurrentFRs(tempList.get(tempList.size() - 1));
                pishnehadReport.getFRsList().addAll(tempList.subList(0, tempList.size() - 1));
//                for (FRs fRs : pishnehadReport.getFRsList())
//                {
//                    if (salAmraz != -1 && fRs.getSaal() >= salAmraz )
//                        fRs.setSarmaayePusheshEzaafiAmraazKhaas(0);
//
//                    if (salNaghs != -1 && fRs.getSaal() >= salNaghs)
//                        fRs.setSarmaayePusheshEzaafiNaghsOzv(0);
//                }

            } catch (BimeNaamehVaSarmayehGozari.CustomValidationException e) {
            correctCalculation = false;
            throw e;
        }
        return correctCalculation;
    }

    public String printDarkhasteElhaghie() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        pishnehadReport.setNewPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getNewPishnehad().getId()));
        pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<PishnehadFieldChanges> pishnehadFieldChangesList = pishnehadReport.getPishnehadFieldChangesList();


        if (pishnehadFieldChangesList != null)
            for (PishnehadFieldChanges pishnehadFieldChanges : pishnehadFieldChangesList) {
                if (pishnehadFieldChanges.getFromValue().equalsIgnoreCase("bali"))
                    pishnehadFieldChanges.setFromValue("بلی");
                if (pishnehadFieldChanges.getFromValue().equalsIgnoreCase("kheir"))
                    pishnehadFieldChanges.setFromValue("خیر");
                if (pishnehadFieldChanges.getToValue().equalsIgnoreCase("bali"))
                    pishnehadFieldChanges.setToValue("بلی");
                if (pishnehadFieldChanges.getToValue().equalsIgnoreCase("kheir"))
                    pishnehadFieldChanges.setToValue("خیر");
            }

        try {
            destFileDIR = realPath + destFileDIR + "printDarkhasteElhaghie.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/pishnehadFieldChangesList.jrxml", realPath + "report/pishnehadFieldChangesList.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/estefadeKonandeganAzSarmayeBime.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printBimename() {
        try {
            Pishnehad pishnehad = pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId());
            if (pishnehad.getBimename() == null || pishnehad.getBimename().getGhestBandiList().size() == 0) {
                List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(pishnehad, 0, bimename.getTarikhShorou(), true);
                pishnehad.setBimename(getBimename());
                ArrayList<Pishnehad> pList = new ArrayList<Pishnehad>();
                pList.add(pishnehad);
                bimename.setPishnehadList(pList);
                GhestBandi gb = new GhestBandi();
                double sumAmraz = 0.0, sumHadese = 0.0, sumMoafiat = 0.0, sumNaghsOzv = 0.0;
                for (TaghsitReport tr : taghsitReport) {
                    sumAmraz += tr.getHaghBimePoosheshAmraz();
                    sumHadese += tr.getHaghBimePoosheshHadese();
                    sumMoafiat += tr.getHaghBimePoosheshMoafiat();
                    sumNaghsOzv += tr.getHaghBimePoosheshNaghsOzv();
                }
                gb.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(sumAmraz)));
                gb.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(sumHadese)));
                gb.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(sumMoafiat)));
                gb.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(sumNaghsOzv)));
                int ghestAmount;
                if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
                    ghestAmount = OmrUtil.rondPardakhti((int) taghsitReport.get(0).getHaghBimePardaakhtiSaal());
                } else {
                    ghestAmount = OmrUtil.rondPardakhti((int) taghsitReport.get(0).getHaghBimePardaakhtiSaal() + (int) taghsitReport.get(0).getMaliat() + (int) taghsitReport.get(0).getHaghBimePusheshHaayeEzaafi());
                }
                Ghest ghest = new Ghest();
                Credebit c = new Credebit(Integer.toString(ghestAmount), "-", bimename, null, Credebit.CredebitType.GHEST);
                ghest.setCredebit(c);
                ArrayList<Ghest> gList = new ArrayList<Ghest>();
                gList.add(ghest);
                gb.setGhestList(gList);
                ArrayList<GhestBandi> gbList = new ArrayList<GhestBandi>();
                gbList.add(gb);
                bimename.setGhestBandiList(gbList);
            } else {
                pishnehadReport.setGhestList(pishnehad.getBimename().getGhestBandiList().get(0).getGhestList());
            }
            Collections.sort(pishnehad.getEstefadeKonandeganAzSarmayeBime());
            pishnehadReport.setPishnehad(pishnehad);
//            pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(OmrUtil.rondPardakhti(asnadeSodorService.getMablagheGhestTaghsitNashode(pishnehad,DateUtil.getCurrentDate() ,0, true))));
//            Long mablaghGhest= asnadeSodorService.getMablagheAghestChapp(pishnehad, DateUtil.getCurrentDate(), 0, true);
//            pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(mablaghGhest));
//            pishnehadReport.setHaghBimeSal(NumberFormat.getInstance().format(asnadeSodorService.getMajmuAghsatSalChapp(pishnehad,DateUtil.getCurrentDate(),0,true,mablaghGhest)));
            asnadeSodorService.getMabaleghForChapp(pishnehad, DateUtil.getCurrentDate(), 0, true, pishnehadReport);
            if (pishnehad.getOptions().equals("CODE_MOVAGHAT")) {
                pishnehadReport.setNameNamayandeAvalie("نماینده کد موقت");
                pishnehadReport.setCodeNamayandeAvalie("111120");
            } else {
                pishnehadReport.setNameNamayandeAvalie(pishnehad.getNamayande().getName());
                pishnehadReport.setCodeNamayandeAvalie(pishnehad.getNamayande().getKodeNamayandeKargozar());
            }
            pishnehadReport.setMaliatAfzoade(((Long) (((Double) (AsnadeSodorService.getDarsadMaliat(pishnehad.getBimename() == null ? DateUtil.getCurrentDate() : pishnehad.getBimename().getTarikhSodour(), pishnehad.getTarh()) * 100)).longValue())).toString());
            destFileDIR = realPath + destFileDIR + "bimenameChappi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/bimename_poosheshEzafi.jrxml", realPath + "report/bimename_poosheshEzafi.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printBimenameHalateAvalie() {
        try {
            Pishnehad pishnehad = pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId());
            Pishnehad firstPishnehad = pishnehad.getBimename().getFirstPishnehad();
            if (firstPishnehad != null) pishnehad = firstPishnehad;
            if (pishnehad.getBimename() == null) {
                List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(pishnehad, 0, bimename.getTarikhShorou(), true);
                pishnehad.setBimename(getBimename());
                ArrayList<Pishnehad> pList = new ArrayList<Pishnehad>();
                pList.add(pishnehad);
                bimename.setPishnehadList(pList);
                GhestBandi gb = new GhestBandi();
                double sumAmraz = 0.0, sumHadese = 0.0, sumMoafiat = 0.0, sumNaghsOzv = 0.0;
                for (TaghsitReport tr : taghsitReport) {
                    sumAmraz += tr.getHaghBimePoosheshAmraz();
                    sumHadese += tr.getHaghBimePoosheshHadese();
                    sumMoafiat += tr.getHaghBimePoosheshMoafiat();
                    sumNaghsOzv += tr.getHaghBimePoosheshNaghsOzv();
                }
                gb.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(sumAmraz)));
                gb.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(sumHadese)));
                gb.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(sumMoafiat)));
                gb.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(sumNaghsOzv)));
                int ghestAmount;
                if (pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafi().equals("az")) {
                    ghestAmount = OmrUtil.rondPardakhti((int) taghsitReport.get(0).getHaghBimePardaakhtiSaal());
                } else {
                    ghestAmount = OmrUtil.rondPardakhti((int) taghsitReport.get(0).getHaghBimePardaakhtiSaal() + (int) taghsitReport.get(0).getMaliat() + (int) taghsitReport.get(0).getHaghBimePusheshHaayeEzaafi());
                }
                Ghest ghest = new Ghest();
                Credebit c = new Credebit(Integer.toString(ghestAmount), "-", bimename, null, Credebit.CredebitType.GHEST);
                ghest.setCredebit(c);
                ArrayList<Ghest> gList = new ArrayList<Ghest>();
                gList.add(ghest);
                gb.setGhestList(gList);
                ArrayList<GhestBandi> gbList = new ArrayList<GhestBandi>();
                gbList.add(gb);
                bimename.setGhestBandiList(gbList);
            } else {
                pishnehadReport.setGhestList(pishnehad.getBimename().getGhestBandiList().get(0).getGhestList());
            }
            pishnehadReport.setPishnehad(pishnehad);
//            pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(OmrUtil.rondPardakhti(asnadeSodorService.getMablagheGhestTaghsitNashode(pishnehad, 0, true))));
//            Long mablaghGhest = asnadeSodorService.getMablagheAghestChapp(pishnehad, DateUtil.getCurrentDate(), 0, true);
//            pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(mablaghGhest));
//            pishnehadReport.setHaghBimeSal(NumberFormat.getInstance().format(asnadeSodorService.getMajmuAghsatSalChapp(pishnehad, DateUtil.getCurrentDate(), 0, true, mablaghGhest)));
            asnadeSodorService.getMabaleghForChapp(pishnehad, DateUtil.getCurrentDate(), 0, true, pishnehadReport);
            if (!pishnehad.getOptions().equals("CODE_MOVAGHAT")) {
                if (pishnehad.getNamayandePoshtiban() != null) {
                    pishnehadReport.setNameNamayandeAvalie("نماینده موقت");
                    pishnehadReport.setCodeNamayandeAvalie("111120");
                } else {
                    pishnehadReport.setNameNamayandeAvalie(pishnehad.getNamayande().getName());
                    pishnehadReport.setCodeNamayandeAvalie(pishnehad.getNamayande().getKodeNamayandeKargozar());
                }
            } else {
                pishnehadReport.setNameNamayandeAvalie("نماینده موقت");
                pishnehadReport.setCodeNamayandeAvalie("111120");
            }
            pishnehadReport.setMaliatAfzoade(((Long) (((Double) (AsnadeSodorService.getDarsadMaliat(pishnehad.getBimename() == null ? DateUtil.getCurrentDate() : pishnehad.getBimename().getTarikhSodour(), pishnehad.getTarh()) * 100)).longValue())).toString());
            destFileDIR = realPath + destFileDIR + "bimenameChappi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/bimename_poosheshEzafi.jrxml", realPath + "report/bimename_poosheshEzafi.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printBimenameChappi() {
        Pishnehad pishnehad = pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId());
        Long[] shSerial = {(Long) Long.parseLong(serialStart)};
        if (pishnehad.getBimename().getGhestBandiList().size() == 0) {
            addActionError("بیمه نامه قسط بندی نشده است.");
            return "nosuccess";
        }
        if (pishnehadService.findSerialByShomareSerial(shSerial).size() == 0) {
            addActionError("شماره سریال در سیستم تعریف نشده است");
            //error  shomareSerial tarif nashode.
            return "nosuccess";
        } else {
            Serial serial = pishnehadService.findSerialByShomareSerial(shSerial).get(0);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Namayande vahedSodor = new Namayande();
            boolean isSetad = false;
            if (loginService.findUserByUsername(username).getMojtamaSodoor() != null) {
                vahedSodor = loginService.findUserByUsername(username).getMojtamaSodoor();
                if (vahedSodor.getKodeNamayandeKargozar().equals("111116"))
                    isSetad = true;
            } else {
                vahedSodor = loginService.findUserByUsername(username).getNamayandegi().getVahedSodur();
            }
            if (serial.getDasteSerial().getVahedeSodur() != vahedSodor && !isSetad) {
                addActionError("شماره سریال متعلق به واحد صدور شما نمی باشد");
                return "nosuccess";
            } else {
                if (serial.getBimename() != null && serial.getBimename() != pishnehad.getBimename()) {
                    addActionError("شماره سریال قبلاً استفاده شده است");
                    //error shomareSerial ghablan estefade shode.
                    return "nosuccess";
                } else if (serial.getDasteSerial().getVaziateDaste() == DasteSerial.VaziateDaste.GHEYRE_FAAL) {
                    addActionError("شماره سریال غیرفعال است");
                    //error shomareSerial gheireFaal ast.
                    return "nosuccess";
                } else if (serial.getVaziatSerial() == Serial.VaziatSerial.EBTAL_SHODE) {
                    addActionError("شماره سریال ابطال شده است");
                    //error shomareSerial ebtal shode ast.
                    return "nosuccess";
                } else {
                    try {
                        pishnehad.getBimename().setSerialPrint(serial);
                        pishnehadService.updateBimename(pishnehad.getBimename());
//                        pishnehadReport.setPishnehad(pishnehad);
                        Pishnehad firstPishnehad = pishnehad.getBimename().getFirstPishnehad();
                        if (firstPishnehad == null) firstPishnehad = pishnehad;
                        Collections.sort(firstPishnehad.getEstefadeKonandeganAzSarmayeBime());
                        pishnehadReport.setPishnehad(firstPishnehad);
//                        pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(OmrUtil.rondPardakhti(asnadeSodorService.getMablagheGhestTaghsitNashode(pishnehad, 0, true))));
//                        Long mablaghGhest = asnadeSodorService.getMablagheAghestChapp(pishnehad, DateUtil.getCurrentDate(), 0, true);
//                        pishnehadReport.setMablaghGhest(NumberFormat.getInstance().format(mablaghGhest));
//                        pishnehadReport.setHaghBimeSal(NumberFormat.getInstance().format(asnadeSodorService.getMajmuAghsatSalChapp(pishnehad, DateUtil.getCurrentDate(), 0, true, mablaghGhest)));
                        asnadeSodorService.getMabaleghForChapp(pishnehad, DateUtil.getCurrentDate(), 0, true, pishnehadReport);
                        pishnehadReport.setGhestList(pishnehad.getBimename().getGhestBandiList().get(0).getGhestList());
                        pishnehadReport.setMaliatAfzoade(((Long) (((Double) (AsnadeSodorService.getDarsadMaliat(pishnehad.getBimename() == null ? DateUtil.getCurrentDate() : pishnehad.getBimename().getTarikhSodour(), pishnehad.getTarh()) * 100)).longValue())).toString());
                        String codeMelli=pishnehadReport.getPishnehad().getBimeShode().getShakhs().getKodeMelliShenasayi();
                        String bimenameNumber=pishnehadReport.getPishnehad().getBimename().getShomare();
                        String qrtext = "http://www.pi724.com/php/bimenameinfo.php?NC="+codeMelli+"&PolicyNo="+bimenameNumber;

                        ByteArrayOutputStream outputStream= QRCode.from(qrtext).to(ImageType.PNG).stream();
                        pishnehadReport.setQrCodeInStream(new ByteArrayInputStream(outputStream.toByteArray()));

                        if (pishnehad.getGharardad() != null && pishnehad.getGharardad().getId() == 48549470)
                        {
                            sourceFilePath = "report/bimename/bimenameChappi_emdad.jrxml";
                        }
                        destFileDIR = realPath + destFileDIR + "bimenameChappi.pdf";
                        boolean b = (new File(realPath + destFileDIR)).mkdirs();
                        File destFile = new File(destFileDIR);
                        if (!destFile.exists()) destFile.createNewFile();
                        JasperCompileManager.compileReportToFile(realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jasper");
                        JasperCompileManager.compileReportToFile(realPath + "report/bimename_poosheshEzafi.jrxml", realPath + "report/bimename_poosheshEzafi.jasper");
                        if (pishnehad.getTarh() != null && pishnehad.getTarh().getId() == 1993671) {
                            sourceFilePath = "report/bimename/bimenameChappi_komite.jrxml";
                        }
                        //                        JasperCompileManager.compileReportToFile(realPath + sourceFilePath , realPath + "report/bimename/bimenameChappi_komite.jasper");
                        JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
                    } catch (JRException e) {
                        e.printStackTrace();
                        return ERROR;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ERROR;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ERROR;
                    }
                }
            }
        }
        return SUCCESS;
    }

    public String residePishPardakht() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        pishnehadReport.setFish(pishnehadService.findFish(pishnehadReport.getFish().getId()));
        try {
            destFileDIR = realPath + destFileDIR + "residePishPardakht.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String darkhasteMoayenatePezeshki() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        pishnehadReport.setMoarefiname(pishnehadService.findMoarefiname(pishnehadReport.getMoarefiname().getId()));
        try {
            destFileDIR = realPath + destFileDIR + "darkhasteMoayenatePezeshki.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/azmayesh_row.jrxml", realPath + "report/azmayesh_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printZamimeBimename() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        try {
            destFileDIR = realPath + destFileDIR + "printZamimeBimename.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printHavaleyeKhesarat() {
        pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
        try {
            destFileDIR = realPath + destFileDIR + "havaleyeKhesarat.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printeCheck() {
        Check check = checkService.findCheckById(pishnehadReport.getCheck().getId());
        pishnehadReport.setCheck(check);
        checkService.changeCheckStatusToPrinted(check);
        try {
            destFileDIR = realPath + destFileDIR + "check.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printeFishBankMellat() {
        try {
            destFileDIR = realPath + destFileDIR + "fish_bankMellat_A5.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            credebitReport = asnadeSodorService.findCretebitById(credebitReport.getId());
            credebitReport.setDescription(StringUtil.convertMeghdareAdadiBeHorofi(credebitReport.getAmount_long().toString()));
            if (credebitReport.getBankName().contains("4757575763"))
                credebitReport.setBankName("4757575763");
            else if (credebitReport.getBankName().contains("2177777733"))
                credebitReport.setBankName("2177777733");
            credebitReport.setRcptName(credebitReport.getDaryafteFish().getSahebeEtebar());
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public String printVagozariBeBank() {
        try {
            destFileDIR = realPath + destFileDIR + "vagozari_bank.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            credebitReportList = asnadeSodorService.findCheckByVagozari(credebitReport.getId());
            for (int i = 0; i < credebitReportList.size(); i++) {
                credebitReportList.get(i).setDescription(StringUtil.convertMeghdareAdadiBeHorofi(credebitReportList.get(i).getAmount_long().toString()));
            }
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public String printCheckVagozarShode() {
        try {
            destFileDIR = realPath + destFileDIR + "list_check_vagozarshode.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            pishnehadReport = new PishnehadReport();
            pishnehadReport.setCredebitList(asnadeSodorService.findCheckByVagozari(credebitReport.getId()));
            pishnehadReport.setVagozari(asnadeSodorService.findVagozariById(credebitReport.getId()));
            double sum = 0.0;
            for (Credebit c : pishnehadReport.getCredebitList()) {
                sum += c.getAmount_long();
            }
            pishnehadReport.setSumOfPardakht(sum);
            JasperCompileManager.compileReportToFile(realPath + "report/list_check_vagozarshode_subReport.jrxml", realPath + "report/list_check_vagozarshode_subReport.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }


    public String printeFishBankTejarat() {
        try {
            destFileDIR = realPath + destFileDIR + "fish_bankTejarat_A5.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            credebitReport = asnadeSodorService.findCretebitById(credebitReport.getId());
            credebitReport.setDescription(StringUtil.convertMeghdareAdadiBeHorofi(credebitReport.getAmount_long().toString()));
            if (credebitReport.getBankName().contains("17038494"))
                credebitReport.setBankName("17038494");
            else if (credebitReport.getBankName().contains("0185007111"))
                credebitReport.setBankName("0185007111");
            credebitReport.setRcptName(credebitReport.getDaryafteFish().getSahebeEtebar());
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public String printeFishBankParsian() {
        try {
            destFileDIR = realPath + destFileDIR + "fish_bankParsian_A5.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            credebitReport = asnadeSodorService.findCretebitById(credebitReport.getId());
            credebitReport.setDescription(StringUtil.convertMeghdareAdadiBeHorofi(credebitReport.getAmount_long().toString()));
            if (credebitReport.getBankName().contains("0200234164006"))
                credebitReport.setBankName("0200234164006");
            else if (credebitReport.getBankName().contains("81011989"))
                credebitReport.setBankName("81011989");
            credebitReport.setRcptName(credebitReport.getDaryafteFish().getSahebeEtebar());
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }
    public String printeResidePardakhteInterneti() {
        try {
            destFileDIR = realPath + destFileDIR + "residePardakhteElectronici.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            pishnehadReport = new PishnehadReport();
            pishnehadReport.setOmr(false);
            if(isOmr)
            {
                pishnehadReport.setOmr(true);
                pishnehadReport.setBedehi(asnadeSodorService.findCretebitById(bedehi.getId()));
            }
            pishnehadReport.setCredebit(asnadeSodorService.findCretebitById(credebitReport.getId()));
            pishnehadReport.setSanad(asnadeSodorService.findSanadById(this.getSanadId()));
            pishnehadReport.getCredebit().setDescription(StringUtil.convertMeghdareAdadiBeHorofi(pishnehadReport.getCredebit().getAmount_long().toString()));
           // credebitReport.setDescription(StringUtil.convertMeghdareAdadiBeHorofi(credebitReport.getAmount_long().toString()));
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public String prepareCashTurnoverReport()
    {
        return SUCCESS;
    }

    public String cashTurnoverReport()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (user.getId() == -1)
        {

            return Constant.ERROR;
        }

        Namayande namayande= null;
        if(OmrUtil.userHasRole(user, Constant.ROLE_NAMAYANDE))
            namayande=user.getNamayandegi();
        cashTurnoverReport = asnadeSodorService.getCashTurnoverMap(fromDate, toDate,namayande);
        Collections.sort(cashTurnoverReport.getCashTurnoverRowsVMList());
        try
        {
            if(formatReport==null || (!formatReport.equals(FORMAT_PDF) && !formatReport.equals(FORMAT_XML) && !formatReport.equals(FORMAT_HTML) && !formatReport.equals(FORMAT_XLS) && !formatReport.equals(FORMAT_CSV) && !formatReport.equals(FORMAT_RTF)))
                formatReport=FORMAT_PDF;

            destFileDIR = realPath + destFileDIR + "cashTurnover."+formatReport;
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/cashTurnoverRows.jrxml", realPath + "report/cashTurnoverRows.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/cashTurnoverRowsPlaceHolder.jrxml", realPath + "report/cashTurnoverRowsPlaceHolder.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        }
        catch (JRException e)
        {
            e.printStackTrace();
            return ERROR;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS ;
    }

    public String printeSanad() {
        pishnehadReport.setSanad(asnadeSodorService.findSanadById(pishnehadReport.getSanad().getId()));
        Set<KhateSanad> khateSanadSet = pishnehadReport.getSanad().getKhateSanadSet();
        ArrayList<KhateSanad> khateSanadArrayList = new ArrayList<KhateSanad>();
        for (KhateSanad k : khateSanadSet) {
            khateSanadArrayList.add(k);
        }
        Collections.sort(khateSanadArrayList);

        List<KhateSanad> sanadEtebarList = new ArrayList<KhateSanad>();
        List<Integer> etebarCredebitId = new ArrayList<Integer>();
        Long etebarAmount = 0l;


        for (KhateSanad khateSanad : khateSanadArrayList) {
            if (!etebarCredebitId.contains(khateSanad.getEtebarCredebit().getId())) {
                etebarCredebitId.add(khateSanad.getEtebarCredebit().getId());
                etebarAmount += Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim());
                if(khateSanad.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK))
                    khateSanad.getEtebarCredebit().setAuthorityId(khateSanad.getEtebarCredebit().getShomareCheckShomareFish());
                sanadEtebarList.add(khateSanad);
            } else {
                KhateSanad khateSanad1 = sanadEtebarList.get(etebarCredebitId.indexOf(khateSanad.getEtebarCredebit().getId()));
                KhateSanad khateSanad1New = new KhateSanad(khateSanad1);
                Long khateSanadAmount = Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()) + Long.parseLong(khateSanad1.getAmount().replaceAll(",", "").trim());
                etebarAmount += Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim());
                khateSanad1New.setAmount(NumberFormat.getInstance().format(khateSanadAmount));
                sanadEtebarList.set(sanadEtebarList.indexOf(khateSanad1), khateSanad1New);
            }
        }
        List<KhateSanad> sanadBedehiList = new ArrayList<KhateSanad>();
        List<Integer> bedehiCredebitId = new ArrayList<Integer>();
        Long bedehiAmount = 0l;
        for (KhateSanad khateSanad : khateSanadArrayList) {
            if (!bedehiCredebitId.contains(khateSanad.getBedehiCredebit().getId())) {
                bedehiCredebitId.add(khateSanad.getBedehiCredebit().getId());
                bedehiAmount += Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim());
                sanadBedehiList.add(khateSanad);
            } else {
                KhateSanad khateSanad1 = sanadBedehiList.get(bedehiCredebitId.indexOf(khateSanad.getBedehiCredebit().getId()));
                KhateSanad khateSanad1New = new KhateSanad(khateSanad1);
                Long khateSanadAmount = Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()) + Long.parseLong(khateSanad1.getAmount().replaceAll(",", "").trim());
                bedehiAmount += Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim());
                khateSanad1New.setAmount(NumberFormat.getInstance().format(khateSanadAmount));
                sanadBedehiList.set(sanadBedehiList.indexOf(khateSanad1), khateSanad1New);
            }
        }
        pishnehadReport.setSanadTaeedEtebarShode(true);
        for(KhateSanad kh : sanadEtebarList) {
            if(kh.getEtebarCredebit().getVosoulState() == Credebit.VaziyatVosoul.TAEED_NASHODE || kh.getEtebarCredebit().getVosoulState() == null) {
                if (!(kh.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI) && kh.getBedehiCredebit().getCredebitType().equals(Credebit.CredebitType.ACH)))
                    pishnehadReport.setSanadTaeedEtebarShode(false);
            }
        }
        pishnehadReport.setTotalEtebarAmount(NumberFormat.getInstance().format(etebarAmount));
        pishnehadReport.setTotalBedehiAmount(NumberFormat.getInstance().format(bedehiAmount));
        Collections.sort(sanadBedehiList);
        Collections.sort(sanadEtebarList);
        pishnehadReport.setKhateSanadsBedehi(sanadBedehiList);
        pishnehadReport.setKhateSanadsEtebar(sanadEtebarList);
        try {
            destFileDIR = realPath + destFileDIR + "sanad.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/sanad_row_bedehi.jrxml", realPath + "report/sanad_row_bedehi.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/sanad_row_etebar.jrxml", realPath + "report/sanad_row_etebar.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/sanad_row.jrxml", realPath + "report/sanad_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printeBazkharid() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            destFileDIR = realPath + destFileDIR + "bazkharid.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    private String hadeAksarVam;
    private String hadeAksarBardasht;
    private String namayandeType;

    public String printeVam() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = loginService.findUserByUsername(username);
            if (user.getId() == -1) {
                return Constant.NOSESSION;
            }
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            hadeAksarVam = NumberFormat.getNumberInstance().format(DarkhastAction.calcHadeAksarVam(pishnehadReport.getDarkhastBazkharid().getBimename(), asnadeSodorService));
            Long resultArzesh = 0L;
            final Bimename theBimename = pishnehadReport.getDarkhastBazkharid().getBimename();
            asnadeSodorService.setAndukhteAndArzeshBazkharid(theBimename, DateUtil.getCurrentDate());
            double arzesheBazkharidDouble = theBimename.getAndukhteyeGhatie();
            resultArzesh = DarkhastAction.calcHadeAksarBardasht(theBimename, Math.round(arzesheBazkharidDouble), asnadeSodorService);
            hadeAksarBardasht = NumberFormat.getNumberInstance().format(resultArzesh);
            if (user.getNamayandegi() != null)
                namayandeType = user.getNamayandegi().getType();
            destFileDIR = realPath + destFileDIR + "vam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeTasvieyeVam() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            destFileDIR = realPath + destFileDIR + "tasvieyeVam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeAdameTatabogheEmza() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            destFileDIR = realPath + destFileDIR + "adameTatabogheEmza.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printMafqudi() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            destFileDIR = realPath + destFileDIR + "mafqudi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeDarkhasteSodoreCheckeBazkharidi() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            destFileDIR = realPath + destFileDIR + "darkhasteSodoreCheckeBazkharidi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeHavaleKhesarat() {
        try {
            pishnehadReport.setDarkhastBazkharid(darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId()));
            pishnehadReport.setKhesarat(khesaratService.findById(pishnehadReport.getKhesarat().getId()));
            pishnehadReport.setHavale(khesaratService.findHavaleById(pishnehadReport.getHavale().getId()));
            destFileDIR = realPath + destFileDIR + "havaleKhesarat.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeAghsat_parsian() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        try {
            pishnehadReport.setGhestBandi(asnadeSodorService.findGhestBandiById(pishnehadReport.getGhestBandi().getId()));
            List<GhestForPrint> ghestForPrintList = new ArrayList<GhestForPrint>();
            List<Ghest> gtList = pishnehadReport.getGhestBandi().getGhestList();
            int shomareGhest = 1;
            if (gtList.size() > 0) {
                if (!pishnehadReport.getGhestBandi().getSaleBimeiInt().equals(0)) {
                    List<GhestBandi> gbList = pishnehadReport.getGhestBandi().getBimename().getGhestBandiList();
                    for (GhestBandi gb : gbList) {
                        if(gb.getType().equals(GhestBandi.Type.G_BIMENAME) && gb.getSaleBimeiInt() < pishnehadReport.getGhestBandi().getSaleBimeiInt()) {
                            shomareGhest += gb.getGhestList().size();
                        }
                    }
                }
            }
            for (Ghest ghest : gtList) {
                if (!ghest.getCredebit().getRemainingAmount().equals("0")) {
                    GhestForPrint ghestForPrint = new GhestForPrint();
                    ghestForPrint.setShomareMoshtari(ghest.getCredebit().getShomareMoshtari());
                    ghestForPrint.setAmount(ghest.getCredebit().getRemainingAmount());
                    ghestForPrint.setAmountHorofi(StringUtil.convertMeghdareAdadiBeHorofi(ghest.getCredebit().getRemainingAmount()));
                    ghestForPrint.setShomareBimename(ghest.getGhestBandi().getBimename().getShomare());
                    ghestForPrint.setNameBimeGozar(
                            ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName()
                                    + " " +
                                    ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi()
                    );
                    ghestForPrint.setNameBimeShode(
                            ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getName()
                                    + " " +
                                    ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi()
                    );
                    ghestForPrint.setMahalleSodour(ghest.getGhestBandi().getBimename().getVahedSodor().getName_kod());
                    if (ghest.getGhestBandi().getBimename().getPishnehad().getOptions().toUpperCase().equals("CODE_MOVAGHAT")) {
                        ghestForPrint.setNameNamayande("نماینده موقت");
                        ghestForPrint.setKodeNamayande("111120");
                    } else {
                        ghestForPrint.setNameNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getName());
                        ghestForPrint.setKodeNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar());
                    }
                    ghestForPrint.setNahveyePardakht(ghest.getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime());
                    ghestForPrint.setSarresid(ghest.getSarresidDate());
                    ghestForPrint.setShomareyeSal(String.valueOf(Integer.parseInt(ghest.getGhestBandi().getSaleBimei()) + 1));
                    ghestForPrint.setCreatedDate(ghest.getCredebit().getCreatedDate());
                    ghestForPrint.setCreatedTime(ghest.getCredebit().getCreatedTime());
                    ghestForPrint.setHashieString("نسخه بانک");
                    ghestForPrint.setShomareGhest(Integer.toString(shomareGhest));
                    ghestForPrintList.add(ghestForPrint);
                    GhestForPrint ghestForPrint2 = new GhestForPrint(ghestForPrint);
                    ghestForPrint2.setHashieString("نسخه مشتری");
                    ghestForPrintList.add(ghestForPrint2);
                }
                shomareGhest++;
            }
            pishnehadReport.setGhestForPrintList(ghestForPrintList);
            destFileDIR = realPath + destFileDIR + "ghest_parsian.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/ghest_parsian_row.jrxml", realPath + "report/ghest_parsian_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            LogPrintDaftarche logPrintDaftarche = new LogPrintDaftarche(user, pishnehadReport.getGhestBandi(), LogPrintDaftarche.Type.PARSIAN, genreDaftarche.equals(LogPrintDaftarche.Genre.PRINT.toString()) ? LogPrintDaftarche.Genre.PRINT : LogPrintDaftarche.Genre.VIEW);
            asnadeSodorService.saveLogPrintDaftarche(logPrintDaftarche);
            pishnehadReport.getGhestBandi().setHasPrint("yes");
            asnadeSodorService.updateGhestbandi(pishnehadReport.getGhestBandi());
        } catch (JRException e) {
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

    public String printeBatchAghsatTejarat() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        List<GhestBandi> ghestBandiList = new ArrayList<GhestBandi>();

        if (user.getId() == -1) {
        } else {
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] ghestBandiIds = (String[]) ActionContext.getContext().getParameters().get(name);

                        for (String ghestBandiId : ghestBandiIds) {
                            GhestBandi ghestBandi = asnadeSodorService.findGhestBandiById(Integer.parseInt(ghestBandiId));
                            ghestBandiList.add(ghestBandi);
                        }

                    }

                }
            }
        }

        if (ghestBandiList != null && ghestBandiList.size() == 0){
            if (batchTaghsitVMS == null) {
                batchTaghsitVMS = new BatchTaghsitVM();
            }

            if (batchTaghsitVMS.getNahvePardakht().equals("mah") || batchTaghsitVMS.getNahvePardakht() == null || batchTaghsitVMS.getNahvePardakht().isEmpty()) {
                batchTaghsitVMS.setNahvePardakht("mah");
                List mahane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
                for (Object gbId : mahane) {
                    ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
                }
            }

            if (batchTaghsitVMS.getNahvePardakht().equals("3mah") || batchTaghsitVMS.getNahvePardakht() == null || batchTaghsitVMS.getNahvePardakht().isEmpty()) {
                batchTaghsitVMS.setNahvePardakht("3mah");
                List seMahe = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
                for (Object gbId : seMahe) {
                    ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
                }
            }
            if (batchTaghsitVMS.getNahvePardakht() != null && batchTaghsitVMS.getNahvePardakht().equals("6mah")) {

                batchTaghsitVMS.setNahvePardakht("6mah");//and sal
                List salane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
                for (Object gbId : salane) {
                    ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
                }
            }

            if (batchTaghsitVMS.getNahvePardakht().equals("sal")) {

                batchTaghsitVMS.setNahvePardakht("sal");
                List salane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
                for (Object gbId : salane) {
                    ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
                }
            }

            if (batchTaghsitVMS.getNahvePardakht() == null || batchTaghsitVMS.getNahvePardakht().isEmpty()) {

                batchTaghsitVMS.setNahvePardakht("sal&6mah");
                List salane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
                for (Object gbId : salane) {
                    ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
                }
            }
        }
        List<GhestForPrint> ghestForPrintList = new ArrayList<GhestForPrint>();
        pishnehadReport = new PishnehadReport();
        try {
            for (GhestBandi ghestBandi : ghestBandiList) {
                List<Ghest> gtList = ghestBandi.getGhestList();
                int shomareGhest = 1;
                if (gtList.size() > 0) {
                    if (!ghestBandi.getSaleBimeiInt().equals(0))
                    {
                        List<GhestBandi> gbList = ghestBandi.getBimename().getGhestBandiList();
                        for (GhestBandi gb : gbList) {
                            if (gb.getSaleBimeiInt() < ghestBandi.getSaleBimeiInt()) {
                                shomareGhest += gb.getGhestList().size();
                            }
                        }
                    }
                }
                for (Ghest ghest : gtList) {
                    if (!ghest.getCredebit().getRemainingAmount().equals("0")) {
                        GhestForPrint ghestForPrint = new GhestForPrint();
                        ghestForPrint.setShomareMoshtari(ghest.getCredebit().getShomareMoshtari());
                        ghestForPrint.setShomareHesab(OmrUtil.getShomareHesabTejarat(ghest.getCredebit().getShomareMoshtari()));
                        ghestForPrint.setAmount(ghest.getCredebit().getRemainingAmount());
                        ghestForPrint.setAmountHorofi(StringUtil.convertMeghdareAdadiBeHorofi(ghest.getCredebit().getRemainingAmount()));
                        ghestForPrint.setShomareBimename(ghest.getGhestBandi().getBimename().getShomare());
                        ghestForPrint.setNameBimeGozar(
                                ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName()
                                        + " " +
                                        ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi()
                        );
                        ghestForPrint.setNameBimeShode(
                                ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getName()
                                        + " " +
                                        ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi()
                        );
                        ghestForPrint.setMahalleSodour(ghest.getGhestBandi().getBimename().getVahedSodor().getName_kod());
                        if (ghest.getGhestBandi().getBimename().getPishnehad().getOptions().toUpperCase().equals("CODE_MOVAGHAT")) {
                            ghestForPrint.setNameNamayande("نماینده موقت");
                            ghestForPrint.setKodeNamayande("111120");
                        } else {
                            ghestForPrint.setNameNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getName());
                            ghestForPrint.setKodeNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar());
                        }
                        ghestForPrint.setNahveyePardakht(ghest.getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime());
                        ghestForPrint.setSarresid(ghest.getSarresidDate());
                        ghestForPrint.setShomareyeSal(String.valueOf(Integer.parseInt(ghest.getGhestBandi().getSaleBimei()) + 1));
                        ghestForPrint.setCreatedDate(ghest.getCredebit().getCreatedDate());
                        ghestForPrint.setCreatedTime(ghest.getCredebit().getCreatedTime());
                        ghestForPrint.setHashieString("نسخه بانک");
                        ghestForPrint.setShomareGhest(Integer.toString(shomareGhest));
                        ghestForPrintList.add(ghestForPrint);
                        GhestForPrint ghestForPrint2 = new GhestForPrint(ghestForPrint);
                        ghestForPrint2.setHashieString("نسخه مشتری");
                        ghestForPrintList.add(ghestForPrint2);
                    }
                    shomareGhest++;
                }
                LogPrintDaftarche logPrintDaftarcheList = new LogPrintDaftarche(user, ghestBandi, LogPrintDaftarche.Type.TEJARAT, LogPrintDaftarche.Genre.PRINT);
                asnadeSodorService.saveLogPrintDaftarche(logPrintDaftarcheList);
                ghestBandi.setHasPrint("yes");
                asnadeSodorService.updateGhestbandi(ghestBandi);
            }

            pishnehadReport.setGhestForPrintList(ghestForPrintList);
            destFileDIR = realPath + destFileDIR + "ghest_tejarat.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/ghest_tejarat_row.jrxml", realPath + "report/ghest_tejarat_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeBatchAghsatParsian() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        List<GhestBandi> ghestBandiList = new ArrayList<GhestBandi>();

        if (user.getId() == -1) {
        } else {
            if (ActionContext.getContext().getParameters().size() > 0) {
                Set<String> qs = ActionContext.getContext().getParameters().keySet();
                for (String name : qs) {
                    if (name.startsWith("_chk")) {
                        String[] ghestBandiIds = (String[]) ActionContext.getContext().getParameters().get(name);

                        for (String ghestBandiId : ghestBandiIds) {
                            GhestBandi ghestBandi = asnadeSodorService.findGhestBandiById(Integer.parseInt(ghestBandiId));
                            ghestBandiList.add(ghestBandi);
                        }

                    }

                }
            }
        }


        if (batchTaghsitVMS == null) {
            batchTaghsitVMS = new BatchTaghsitVM();
        }

        batchTaghsitVMS.setNahvePardakht("mah");
        List mahane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
        if (ghestBandiList != null && ghestBandiList.size() == 0)
            for (Object gbId : mahane) {
                ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
            }

//            batchTaghsitVMS.setNahvePardakht("3mah");
//            List seMahe = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
//            for (Object gbId :seMahe)
//            {
//                ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
//            }
//
//            batchTaghsitVMS.setNahvePardakht("6mah");//and sal
//            List salane = asnadeSodorService.findGhestBandiListForPrintDaftarche(batchTaghsitVMS);
//            for (Object gbId :salane)
//            {
//                ghestBandiList.add(asnadeSodorService.findGhestBandiById((Integer) gbId));
//            }

        List<GhestForPrint> ghestForPrintList = new ArrayList<GhestForPrint>();
        pishnehadReport = new PishnehadReport();

        try {
            for (GhestBandi ghestBandi : ghestBandiList) {
                List<Ghest> gtList = ghestBandi.getGhestList();
                int shomareGhest = 1;
                if (gtList.size() > 0) {
                    if (!ghestBandi.getSaleBimeiInt().equals(0)) {
                        List<GhestBandi> gbList = ghestBandi.getBimename().getGhestBandiList();
                        for (GhestBandi gb : gbList) {
                            if (gb.getSaleBimeiInt() < ghestBandi.getSaleBimeiInt()) {
                                shomareGhest += gb.getGhestList().size();
                            }
                        }
                    }
                }
                for (Ghest ghest : gtList) {
                    if (!ghest.getCredebit().getRemainingAmount().equals("0")) {
                        GhestForPrint ghestForPrint = new GhestForPrint();
                        ghestForPrint.setShomareMoshtari(ghest.getCredebit().getShomareMoshtari());
                        ghestForPrint.setAmount(ghest.getCredebit().getRemainingAmount());
                        ghestForPrint.setAmountHorofi(StringUtil.convertMeghdareAdadiBeHorofi(ghest.getCredebit().getRemainingAmount()));
                        ghestForPrint.setShomareBimename(ghest.getGhestBandi().getBimename().getShomare());
                        ghestForPrint.setNameBimeGozar(
                                ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName()
                                        + " " +
                                        ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi()
                        );
                        ghestForPrint.setNameBimeShode(
                                ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getName()
                                        + " " +
                                        ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi()
                        );
                        ghestForPrint.setMahalleSodour(ghest.getGhestBandi().getBimename().getVahedSodor().getName_kod());
                        if (ghest.getGhestBandi().getBimename().getPishnehad().getOptions().toUpperCase().equals("CODE_MOVAGHAT")) {
                            ghestForPrint.setNameNamayande("نماینده موقت");
                            ghestForPrint.setKodeNamayande("111120");
                        } else {
                            ghestForPrint.setNameNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getName());
                            ghestForPrint.setKodeNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar());
                        }
                        ghestForPrint.setNahveyePardakht(ghest.getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime());
                        ghestForPrint.setSarresid(ghest.getSarresidDate());
                        ghestForPrint.setShomareyeSal(String.valueOf(Integer.parseInt(ghest.getGhestBandi().getSaleBimei()) + 1));
                        ghestForPrint.setCreatedDate(ghest.getCredebit().getCreatedDate());
                        ghestForPrint.setCreatedTime(ghest.getCredebit().getCreatedTime());
                        ghestForPrint.setHashieString("نسخه بانک");
                        ghestForPrint.setShomareGhest(Integer.toString(shomareGhest));
                        ghestForPrintList.add(ghestForPrint);
                        GhestForPrint ghestForPrint2 = new GhestForPrint(ghestForPrint);
                        ghestForPrint2.setHashieString("نسخه مشتری");
                        ghestForPrintList.add(ghestForPrint2);
                    }
                    shomareGhest++;
                }

                LogPrintDaftarche logPrintDaftarcheList = new LogPrintDaftarche(user, ghestBandi, LogPrintDaftarche.Type.PARSIAN, LogPrintDaftarche.Genre.PRINT);
                asnadeSodorService.saveLogPrintDaftarche(logPrintDaftarcheList);
                ghestBandi.setHasPrint("yes");
                asnadeSodorService.updateGhestbandi(ghestBandi);
            }

            pishnehadReport.setGhestForPrintList(ghestForPrintList);
            destFileDIR = realPath + destFileDIR + "ghest_parsian.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/ghest_parsian_row.jrxml", realPath + "report/ghest_parsian_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeAghsat_tejarat() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        try {
            pishnehadReport.setGhestBandi(asnadeSodorService.findGhestBandiById(pishnehadReport.getGhestBandi().getId()));
            List<GhestForPrint> ghestForPrintList = new ArrayList<GhestForPrint>();
            List<Ghest> gtList = pishnehadReport.getGhestBandi().getGhestList();
            int shomareGhest = 1;
            if (gtList.size() > 0) {
                if (!pishnehadReport.getGhestBandi().getSaleBimeiInt().equals(0)) {
                    List<GhestBandi> gbList = pishnehadReport.getGhestBandi().getBimename().getGhestBandiList();
                    for (GhestBandi gb : gbList) {
                        if (gb.getType().equals(GhestBandi.Type.G_BIMENAME) && gb.getSaleBimeiInt() < pishnehadReport.getGhestBandi().getSaleBimeiInt()) {
                            shomareGhest += gb.getGhestList().size();
                        }
                    }
                }
            }
            for (Ghest ghest : gtList) {
                if (!ghest.getCredebit().getRemainingAmount().equals("0")) {
                    GhestForPrint ghestForPrint = new GhestForPrint();
                    ghestForPrint.setShomareMoshtari(ghest.getCredebit().getShomareMoshtari());
                    ghestForPrint.setShomareHesab(OmrUtil.getShomareHesabTejarat(ghest.getCredebit().getShomareMoshtari()));
                    ghestForPrint.setAmount(ghest.getCredebit().getRemainingAmount());
                    ghestForPrint.setAmountHorofi(StringUtil.convertMeghdareAdadiBeHorofi(ghest.getCredebit().getRemainingAmount()));
                    ghestForPrint.setShomareBimename(ghest.getGhestBandi().getBimename().getShomare());
                    ghestForPrint.setNameBimeGozar(
                            ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getName()
                                    + " " +
                                    ghest.getGhestBandi().getBimename().getPishnehad().getBimeGozar().getShakhs().getNameKhanevadegi()
                    );
                    ghestForPrint.setNameBimeShode(
                            ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getName()
                                    + " " +
                                    ghest.getGhestBandi().getBimename().getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi()
                    );
                    ghestForPrint.setMahalleSodour(ghest.getGhestBandi().getBimename().getVahedSodor().getName_kod());
                    if (ghest.getGhestBandi().getBimename().getPishnehad().getOptions().toUpperCase().equals("CODE_MOVAGHAT")) {
                        ghestForPrint.setNameNamayande("نماینده موقت");
                        ghestForPrint.setKodeNamayande("111120");
                    } else {
                        ghestForPrint.setNameNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getName());
                        ghestForPrint.setKodeNamayande(ghest.getGhestBandi().getBimename().getPishnehad().getNamayande().getKodeNamayandeKargozar());
                    }
                    ghestForPrint.setNahveyePardakht(ghest.getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime());
                    ghestForPrint.setSarresid(ghest.getSarresidDate());
                    ghestForPrint.setShomareyeSal(String.valueOf(Integer.parseInt(ghest.getGhestBandi().getSaleBimei()) + 1));
                    ghestForPrint.setCreatedDate(ghest.getCredebit().getCreatedDate());
                    ghestForPrint.setCreatedTime(ghest.getCredebit().getCreatedTime());
                    ghestForPrint.setHashieString("نسخه بانک");
                    ghestForPrint.setShomareGhest(Integer.toString(shomareGhest));
                    ghestForPrintList.add(ghestForPrint);
                    GhestForPrint ghestForPrint2 = new GhestForPrint(ghestForPrint);
                    ghestForPrint2.setHashieString("نسخه مشتری");
                    ghestForPrintList.add(ghestForPrint2);
                }
                shomareGhest++;
            }
            pishnehadReport.setGhestForPrintList(ghestForPrintList);
            destFileDIR = realPath + destFileDIR + "ghest_tejarat.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/ghest_tejarat_row.jrxml", realPath + "report/ghest_tejarat_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
            LogPrintDaftarche logPrintDaftarche = new LogPrintDaftarche(user, pishnehadReport.getGhestBandi(), LogPrintDaftarche.Type.TEJARAT,genreDaftarche.equals(LogPrintDaftarche.Genre.PRINT.toString())? LogPrintDaftarche.Genre.PRINT: LogPrintDaftarche.Genre.VIEW);
            asnadeSodorService.saveLogPrintDaftarche(logPrintDaftarche);
            pishnehadReport.getGhestBandi().setHasPrint("yes");
            asnadeSodorService.updateGhestbandi(pishnehadReport.getGhestBandi());
        } catch (JRException e) {
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

    public String printeSharayeteAkhzeVam() {
        try {
            Bimename bimename = pishnehadService.findBimenameById(pishnehadReport.getBimename().getId());
            pishnehadReport.setBimename(bimename);
            ReportSharayetVam reportSharayetVam = new ReportSharayetVam();
            asnadeSodorService.setAndukhteAndArzeshBazkharid(bimename, DateUtil.getCurrentDate());
            double arzesheBazkharidDouble = bimename.getArzeshBazkharidGhatie();
            reportSharayetVam.setArzeshBazkharid(Long.toString(Math.round(arzesheBazkharidDouble)));
            reportSharayetVam.setCheck2SalTamam(true);
            reportSharayetVam.setCheckAghsatMoavagh(bimename.getHasGhestMoavagh());
            reportSharayetVam.setCheckArzeshBazkharidi(true);
            reportSharayetVam.setCheckElhaghieBaz(true);
            reportSharayetVam.setCheckKamtarAz2Bar(true);
            reportSharayetVam.setCheckVamTasvieNashode(true);
            if (DateUtil.isGreaterThan(DateUtil.addYear(bimename.getTarikhShorou(), 2), DateUtil.getCurrentDate())) {
                reportSharayetVam.setCheck2SalTamam(false);
            }
            if (bimename.getState().getId().intValue() != Constant.BIMENAME_INITIAL_STATE.intValue()) {
//                reportSharayetVam.setCheckElhaghieBaz(false);
            }
            List<DarkhastBazkharid> allDarkhasts = bimename.getAllDarkhasts();
            boolean hasUndoneVam = false;
            int numberOfVaams = 0;
            DarkhastBazkharid vamPishin = null;
            for (DarkhastBazkharid theDarkhast : allDarkhasts) {
                if (theDarkhast.getState().getId().equals(Constant.VAM_ENSERAF) || !theDarkhast.getFinished()) continue;
                if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
                    numberOfVaams++;
//                    if (theDarkhast.getState().getId().intValue() != Constant.VAM_FINAL_STATE.intValue()) {
//                        hasUndoneVam = true;
//                    }
//                    for (Ghest g : theDarkhast.getGhestBandi().getGhestList())
                    for (Credebit cr : theDarkhast.getBimename().getCredebitList())
                    {
                        if (cr.getCredebitType().equals(Credebit.CredebitType.GHEST_VAM) && cr.getGhest().getGhestBandi().getId()!=theDarkhast.getGhestBandi().getId() && !cr.getRemainingAmount().equals("0")) {
                            hasUndoneVam = true;
                            vamPishin = theDarkhast;
                            break;
                        }
                    }
                }
            }

            reportSharayetVam.setTedadDafeatAkhzVam(numberOfVaams);
            if (hasUndoneVam) {
                reportSharayetVam.setAkhzShodeShomareVam(vamPishin.getShomareDarkhast());
                final Long jameJaraemDirkard = Math.round(MohasebateFaniVam.calcJaraemeDirkard(vamPishin, asnadeSodorService) + MohasebateFaniVam.calcJaraemeTavigh(vamPishin));
                final Long jameAslAghsatAti = Math.round(MohasebateFaniVam.calcJameAslAghsatAti(vamPishin));
                final Long jameKolAghsatMoavaq = Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(vamPishin));
                final Long jameKolAghsatMoavaqJarime = jameKolAghsatMoavaq + jameJaraemDirkard;
                final Long jameKolBedehi = jameKolAghsatMoavaqJarime + jameAslAghsatAti;
                reportSharayetVam.setAkhzShodeJamJaraemDirkard(jameJaraemDirkard);
                reportSharayetVam.setAkhzShodeJamAghsatMoavagh(jameKolAghsatMoavaq);
                reportSharayetVam.setAkhzShodeMablaghVam(vamPishin.getMablagheVamedarkhasti());
                reportSharayetVam.setTarikhAkhzVam(vamPishin.getTarikhDarkhast());
                reportSharayetVam.setJamAslAghsatAti(jameAslAghsatAti);
                reportSharayetVam.setMajmooBedehiVam(jameKolBedehi);
                reportSharayetVam.setCheckVamTasvieNashode(false);

            }
            if (arzesheBazkharidDouble < 0) {
//                reportSharayetVam.setCheckAghsatMoavagh(false);
            }
            if (arzesheBazkharidDouble == 0) {
                reportSharayetVam.setCheckArzeshBazkharidi(false);
            }
            if (numberOfVaams > 2) {
                reportSharayetVam.setCheckKamtarAz2Bar(false);
            }
            pishnehadReport.setReportSharayetVam(reportSharayetVam);
            destFileDIR = realPath + destFileDIR + "printeSharayeteAkhzeVam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeSharayeteTasvieyeVam() {
        try {
            pishnehadReport.setBimename(pishnehadService.findBimenameById(pishnehadReport.getBimename().getId()));
            DarkhastBazkharid db = darkhastService.findDarkhastBazkharidById(pishnehadReport.getDarkhastBazkharid().getId());
            DarkhastBazkharid vamForPrint = darkhastService.findDarkhastBazkharidById(db.getTasvieVamPishAzMoedId());
            vamForPrint.setJaraemeDirkard(db.getJaraemeDirkard());
            vamForPrint.setJameKolAghsatMoavagh(db.getJameKolAghsatMoavagh());
            vamForPrint.setJameAslAghsatAti(db.getJameAslAghsatAti());
            String majmuBedehi = NumberFormat.getInstance().format(Long.parseLong(db.getJameAslAghsatAti().replaceAll(",", "").trim()) + Long.parseLong(db.getJaraemeDirkard().replaceAll(",", "").trim()) + Long.parseLong(db.getJameKolAghsatMoavagh().replaceAll(",", "").trim()));
            vamForPrint.setJameAghsatPardakhtNashode(majmuBedehi);
            pishnehadReport.setDarkhastBazkharid(vamForPrint);
            destFileDIR = realPath + destFileDIR + "printeSharayeteTasvieyeVam.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeSharayeteBardashtAzAndokhte() {
        try {
            final Bimename theBimename = pishnehadService.findBimenameById(pishnehadReport.getBimename().getId());

            pishnehadReport.setBimename(theBimename);
            reportSharayetBardasht = new ReportSharayetBardasht();

            destFileDIR = realPath + destFileDIR + "printeSharayeteBardashtAzAndokhte.pdf";
            reportSharayetBardasht.setDoSalGozashte(!DateUtil.isGreaterThan(DateUtil.addYear(theBimename.getTarikhShorou(), 2), DateUtil.getCurrentDate()));
            List<DarkhastBazkharid> allDarkhasts = theBimename.getAllDarkhasts();
            List<DarkhastBazkharid> allBardashts = new ArrayList<DarkhastBazkharid>();
            int numberOfBardashts = 0, numberOfVaams = 0;
            boolean hasUndoneVam = false, elhaghieBaz = false, hasAghsatMoavagh = false;
            DarkhastBazkharid vamPishin = null;
            for (DarkhastBazkharid theDarkhast : allDarkhasts) {
                if (theDarkhast.getState()!=null && theDarkhast.getState().getId().intValue() != Constant.VAM_FINAL_STATE.intValue() && theDarkhast.getState().getId().intValue() != Constant.BARDASHT_AZ_ANDOKHTE_FINAL_STATE) {
                    elhaghieBaz = true;
                }
                if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.BARDASHT_AZ_ANDOKHTE)) {
                    if (theDarkhast.getState().getId().equals(Constant.BARDASHT_ENSERAF)) continue;
                    allBardashts.add(theDarkhast);
                    if(pishnehadReport.getDarkhastBazkharid()==null || pishnehadReport.getDarkhastBazkharid().getId()==null || !theDarkhast.getId().equals(pishnehadReport.getDarkhastBazkharid().getId()))
                        numberOfBardashts++;
                } else if (theDarkhast.getDarkhastType().equals(DarkhastBazkharid.DarkhastType.VAM)) {
                    numberOfVaams++;
//                    if (theDarkhast.getState().getId().intValue() != Constant.VAM_FINAL_STATE.intValue()) {
//                        hasUndoneVam = true;
//                    }
                    if (theDarkhast.getGhestBandi() != null)
                        for (Ghest g : theDarkhast.getGhestBandi().getGhestList())
                            if (g.getCredebit().getRemainingAmount_long()>0) {
                                hasUndoneVam = true;
                                vamPishin = theDarkhast;
                                break;
                            }
                }
            }

            Long resultArzesh = 0L;
            asnadeSodorService.setAndukhteAndArzeshBazkharid(theBimename, DateUtil.getCurrentDate());
            double arzesheBazkharidDouble = theBimename.getArzeshBazkharidGhatie();
            resultArzesh = DarkhastAction.calcHadeAksarBardasht(theBimename, Math.round(arzesheBazkharidDouble), asnadeSodorService);
            reportSharayetBardasht.setSaghf(resultArzesh > 0);
            reportSharayetBardasht.setNumberExceed(numberOfBardashts <= 2);
            reportSharayetBardasht.setPeriod(theBimename.isPeriodBardashtAzAndukhteOk());

            final Object[] objectst = MohasebateFaniVam.calcJamAghsatMoavaghBimename(asnadeSodorService);
            final Long jamAghsatMoavaghBimename = (Long) objectst[0];
            hasAghsatMoavagh = (Boolean) objectst[1];
            reportSharayetBardasht.setJamAghsatMoavaghBimename(Long.parseLong(theBimename.getAmountMoavagh().trim().replaceAll(",","")));
            reportSharayetBardasht.setJamJaraemDirkard(0L);
            reportSharayetBardasht.setJamAghsatMoavaghVam(0L);
            reportSharayetBardasht.setMajmuBedehiVam(0L);
            reportSharayetBardasht.setJameAslAghsatAti(0L);
            if (hasUndoneVam) {
                final Long jameJaraemDirkard = Math.round(MohasebateFaniVam.calcJaraemeDirkard(vamPishin, asnadeSodorService) + MohasebateFaniVam.calcJaraemeTavigh(vamPishin));
                final Long jameAslAghsatAti = Math.round(MohasebateFaniVam.calcJameAslAghsatAti(vamPishin));
                final Long jameKolAghsatMoavaq = Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(vamPishin));
                final Long jameKolAghsatMoavaqJarime = jameKolAghsatMoavaq + jameJaraemDirkard;
                final Long jameKolBedehi = jameKolAghsatMoavaqJarime + jameAslAghsatAti;
                reportSharayetBardasht.setJamJaraemDirkard(jameJaraemDirkard);
                reportSharayetBardasht.setJamAghsatMoavaghVam(jameKolAghsatMoavaq);
                reportSharayetBardasht.setMajmuBedehiVam(jameKolBedehi);
                reportSharayetBardasht.setJameAslAghsatAti(jameAslAghsatAti);

            }
            reportSharayetBardasht.setAndukhteBimename(NumberFormat.getNumberInstance().format(Math.round(arzesheBazkharidDouble)));
            String remainingPercent= DarkhastAction.calcDarsadBaghimandeBardashtString(theBimename);
            reportSharayetBardasht.setRemainingPercent(remainingPercent.equals("out of max andukhte")?"0":remainingPercent);
            Collections.sort(allBardashts,Collections.reverseOrder());
            for(int i=0 ; i<allBardashts.size(); i++)
            {
                if(i==0)
                {
                    reportSharayetBardasht.setShomareBardasht1(allBardashts.get(i).getShomareBardashtAzAndukhte());
                    reportSharayetBardasht.setTarikhBardasht1(allBardashts.get(i).getCredebitBardasht()!=null?allBardashts.get(i).getCredebitBardasht().getDateFish():allBardashts.get(i).getTarikhDarkhast());
                    reportSharayetBardasht.setMablaghBardasht1(allBardashts.get(i).getMablaghDarkhastiBardasht());
                    reportSharayetBardasht.setDarsadBardasht1(allBardashts.get(i).getDarsadBardasht());
                    reportSharayetBardasht.setMaxAmountBardasht1(allBardashts.get(i).getMaxAmountBardashtStr());
                    reportSharayetBardasht.setAndukhte1(allBardashts.get(i).getAndukhteGhatie());
                    if (pishnehadReport.getDarkhastBazkharid() != null && pishnehadReport.getDarkhastBazkharid().getId().equals(allBardashts.get(i).getId()))
                        break;
                }
                if(i==1)
                {
                    reportSharayetBardasht.setShomareBardasht2(allBardashts.get(i).getShomareBardashtAzAndukhte());
                    reportSharayetBardasht.setTarikhBardasht2(allBardashts.get(i).getCredebitBardasht() != null ? allBardashts.get(i).getCredebitBardasht().getDateFish() : allBardashts.get(i).getTarikhDarkhast());
                    reportSharayetBardasht.setMablaghBardasht2(allBardashts.get(i).getMablaghDarkhastiBardasht());
                    reportSharayetBardasht.setDarsadBardasht2(allBardashts.get(i).getDarsadBardasht());
                    reportSharayetBardasht.setMaxAmountBardasht2(allBardashts.get(i).getMaxAmountBardashtStr());
                    reportSharayetBardasht.setAndukhte2(allBardashts.get(i).getAndukhteGhatie());
                    if(pishnehadReport.getDarkhastBazkharid() != null && pishnehadReport.getDarkhastBazkharid().getId().equals(allBardashts.get(i).getId()))
                        break;
                }if(i==2)
                {
                    reportSharayetBardasht.setShomareBardasht3(allBardashts.get(i).getShomareBardashtAzAndukhte());
                    reportSharayetBardasht.setTarikhBardasht3(allBardashts.get(i).getCredebitBardasht() != null ? allBardashts.get(i).getCredebitBardasht().getDateFish() : allBardashts.get(i).getTarikhDarkhast());
                    reportSharayetBardasht.setMablaghBardasht3(allBardashts.get(i).getMablaghDarkhastiBardasht());
                    reportSharayetBardasht.setDarsadBardasht3(allBardashts.get(i).getDarsadBardasht());
                    reportSharayetBardasht.setMaxAmountBardasht3(allBardashts.get(i).getMaxAmountBardashtStr());
                    reportSharayetBardasht.setAndukhte3(allBardashts.get(i).getAndukhteGhatie());
                    if (pishnehadReport.getDarkhastBazkharid() != null && pishnehadReport.getDarkhastBazkharid().getId().equals(allBardashts.get(i).getId()))
                        break;
                }
            }

//            reportSharayetBardasht.setVamTasvieNashode(!hasUndoneVam);
//            reportSharayetBardasht.setElhaghieBaz(!elhaghieBaz);
//            reportSharayetBardasht.setAghsatMoavagh(!hasAghsatMoavagh);

//            reportSharayetBardasht.setArzeshBazkharidi(arzesheBazkharidDouble>0);
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeElamieBestankarBardashtAzAndokhte() {
        try {
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            pishnehadReport = new PishnehadReport();
            pishnehadReport.setBimename(darkhastBazkharid.getBimename());
            pishnehadReport.setDarkhastBazkharid(darkhastBazkharid);
            destFileDIR = realPath + destFileDIR + "elamieBestankarBardashtAzAndokhte.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeElamBeMali() {
        try {
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            pishnehadReport = new PishnehadReport();
            pishnehadReport.setBimename(darkhastBazkharid.getBimename());
            pishnehadReport.setDarkhastBazkharid(darkhastBazkharid);
            destFileDIR = realPath + destFileDIR + "elamBeMali.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    public String printeElameShomarehesab() {
        try {
            pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
            pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            destFileDIR = realPath + destFileDIR + "elameShomareHesab.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    private String enseraf;

    public String printeElameShomarehesab_NameBeMali() {
        try {
            pishnehadReport.setPishnehad(pishnehadService.loadPishnehadById(pishnehadReport.getPishnehad().getId()));
            pishnehadReport.setUser(loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            enseraf = "unknown";
            List<TransitionLog> transitionLogs = getTransitionLogService().getPishnehadLogs(pishnehadReport.getPishnehad().getId());
            Collections.sort(transitionLogs);
            if (transitionLogs.get(transitionLogs.size() - 1).getTransition().getId() == 74) {
                enseraf = "yes";
            } else if (transitionLogs.get(transitionLogs.size() - 1).getTransition().getId() == 65) {
                enseraf = "no";
            }
            destFileDIR = realPath + destFileDIR + "elamBeMali_Enseraf.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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

    private String mablaghBedehiAslVam, mablaghBedehiAghsatVam, mablaghBedehiBimename, mablaghBedehiJarimeVam, emza1, emza2;

    public String printJoziatBardasht() {
        try {
            darkhastBazkharid = darkhastService.findDarkhastBazkharidById(darkhastBazkharid.getId());
            darkhastBazkharid.getMablaghDarkhastiBardasht();
            Long resultArzesh = 0L;
            final Bimename theBimename = darkhastBazkharid.getBimename();
            asnadeSodorService.setAndukhteAndArzeshBazkharid(theBimename, DateUtil.getCurrentDate());
            double arzesheBazkharidDouble = theBimename.getAndukhteyeGhatie();
            resultArzesh = DarkhastAction.calcHadeAksarBardasht(theBimename, Math.round(arzesheBazkharidDouble), asnadeSodorService);
            hadeAksarBardasht = NumberFormat.getNumberInstance().format(resultArzesh);
            final Long jameJaraemDirkard = Math.round(MohasebateFaniVam.calcJaraemeDirkard(darkhastBazkharid, asnadeSodorService) + MohasebateFaniVam.calcJaraemeTavigh(darkhastBazkharid));
            final Long jameAslAghsatAti = Math.round(MohasebateFaniVam.calcJameAslAghsatAti(darkhastBazkharid));
            final Long jameKolAghsatMoavaq = Math.round(MohasebateFaniVam.calcJameKolAghsatMoavagh(darkhastBazkharid));
            final Long jameKolAghsatMoavaqJarime = jameKolAghsatMoavaq + jameJaraemDirkard;
            final Long jameKolBedehi = jameKolAghsatMoavaqJarime + jameAslAghsatAti;
            final Long jameAghsatMoavaghBimename = (Long) MohasebateFaniVam.calcJamAghsatMoavaghBimename(asnadeSodorService)[0];
            mablaghBedehiAslVam = NumberFormat.getNumberInstance().format(jameAslAghsatAti);
            mablaghBedehiAghsatVam = NumberFormat.getNumberInstance().format(jameKolAghsatMoavaq + jameAslAghsatAti);
            mablaghBedehiBimename = NumberFormat.getNumberInstance().format(jameAghsatMoavaghBimename);
            mablaghBedehiJarimeVam = NumberFormat.getNumberInstance().format(jameJaraemDirkard);
            if (darkhastBazkharid.getEmzaKonandeAval() != null)
                emza1 = darkhastBazkharid.getEmzaKonandeAval().getUser().getFullName();
            if (darkhastBazkharid.getEmzaKonandeDovom() != null)
                emza2 = darkhastBazkharid.getEmzaKonandeDovom().getUser().getFullName();
            destFileDIR = realPath + destFileDIR + "JoziatBardasht.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
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
    public String printGozaresheKoliEtebarvaBedehi(){
        List<EtebarBedehiVM> shakesalesList= new ArrayList<EtebarBedehiVM>();
        List<EtebarBedehiVM> badaneList= new ArrayList<EtebarBedehiVM>();
        List<EtebarBedehiVMReport>    vmList = new ArrayList<EtebarBedehiVMReport>();
        User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<EtebarBedehiVM> etebarBedehiVMList1 =asnadeSodorService.findCredebitbySubSystemIdentiferforcales(azTarikheSodoreBimename, taTarikheSodoreBimename, vahedeSodorId,user);
        List<EtebarBedehiVM> etebarBedehiVMList2 =asnadeSodorService.findCredebitbySubSystemIdentiferforBadane(azTarikheSodoreBimename, taTarikheSodoreBimename, vahedeSodorId,user);

         int i=0;
        HashMap<String,List<EtebarBedehiVM>> EtebarBedehiReport1Hashmap=new HashMap<String,List<EtebarBedehiVM>>();
        HashMap<String,List<EtebarBedehiVM>> EtebarBedehiReport2Hashmap=new HashMap<String,List<EtebarBedehiVM>>();


        for(EtebarBedehiVM eb:etebarBedehiVMList1)   {
          if(i>0){
         if(! etebarBedehiVMList1.get(i-1).getVahedesodor().equals(eb.getVahedesodor()))  {
             shakesalesList = new ArrayList<EtebarBedehiVM>();
             shakesalesList.add(eb) ;
              EtebarBedehiReport1Hashmap.put(eb.getVahedesodor(),shakesalesList);
         } else{
             shakesalesList.add(eb) ;
              EtebarBedehiReport1Hashmap.put(eb.getVahedesodor(),shakesalesList);
          }

      }else{
              shakesalesList.add(eb) ;
            EtebarBedehiReport1Hashmap.put(eb.getVahedesodor(),shakesalesList);
        }
            i++;
      }
        i=0;
        for(EtebarBedehiVM eb:etebarBedehiVMList2)   {
            if(i>0){
                if(! etebarBedehiVMList2.get(i-1).getVahedesodor().equals(eb.getVahedesodor()))  {
                    badaneList = new ArrayList<EtebarBedehiVM>();
                    badaneList.add(eb) ;
                    EtebarBedehiReport2Hashmap.put(eb.getVahedesodor(),badaneList);

                } else{
                    badaneList.add(eb) ;
                    EtebarBedehiReport2Hashmap.put(eb.getVahedesodor(),badaneList);
                }

            }else{
                badaneList.add(eb) ;
                EtebarBedehiReport2Hashmap.put(eb.getVahedesodor(),badaneList);
        }
            i++;
        }

         int j=0;
        for(EtebarBedehiVM eb:etebarBedehiVMList1)   {
            etebarBedehiVMReport= new EtebarBedehiVMReport();
            if(j>=1){
                if( !(etebarBedehiVMList1.get(j-1).getVahedesodor().equals(eb.getVahedesodor())))  {
                    etebarBedehiVMReport= new EtebarBedehiVMReport();
                    etebarBedehiVMReport.setEtebarBedehiVMListForshakhseSaless(EtebarBedehiReport1Hashmap.get(eb.getVahedesodor()));
                    etebarBedehiVMReport.setEtebarBedehiVMListForBadane(EtebarBedehiReport2Hashmap.get(eb.getVahedesodor()));
                    etebarBedehiVMReport.setVahedeSodor(eb.getVahedesodor());
                    vmList.add( etebarBedehiVMReport);
                }
            }else{
                etebarBedehiVMReport.setEtebarBedehiVMListForshakhseSaless(EtebarBedehiReport1Hashmap.get(eb.getVahedesodor()));
                etebarBedehiVMReport.setEtebarBedehiVMListForBadane(EtebarBedehiReport2Hashmap.get(eb.getVahedesodor()));
                etebarBedehiVMReport.setVahedeSodor(eb.getVahedesodor());
                vmList.add( etebarBedehiVMReport);
            }

           j++;
        }

        this.setEtebarBedehiVMReportList(vmList);
         if(vmList.size()<=0)    {
       etebarBedehiVMReport = new EtebarBedehiVMReport() ;
        etebarBedehiVMReportList.add(etebarBedehiVMReport);
         }
        try {
            destFileDIR = realPath + destFileDIR + "gozaresheKoliEtebarvaBedehi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/gozaresheKoliEtebarvaBedehi_subreport1.jrxml", realPath + "report/gozaresheKoliEtebarvaBedehi_subreport1.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/gozaresheKoliEtebarvaBedehi_subreport2.jrxml", realPath + "report/gozaresheKoliEtebarvaBedehi_subreport2.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/gozaresheKoliEtebarvaBedehi.jrxml", realPath + "report/gozaresheKoliEtebarvaBedehi.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

//    public String printSoratVaziateMaliBimename() {
//
//     List<CredebitBimenameVM> cList= new ArrayList<CredebitBimenameVM>();
//     CredebitVMReport  credebitVMReport= new CredebitVMReport();
//        User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        List<CredebitBimenameVM> credebitVMs = asnadeSodorService.findCredebitsByIdentifier(shomareBimenameFrom, shomareBimenameTo,user) ;
//     CredebitBimenameVM[] credebitVMsArrya= new CredebitBimenameVM[(credebitVMs.size())];
//     credebitVMsArrya=  credebitVMs.toArray(credebitVMsArrya) ;
//     HashMap<String,List<CredebitBimenameVM>> credebitReportHashmap=new HashMap<String,List<CredebitBimenameVM>>();
//        for (  int i=0;i<credebitVMsArrya.length;i++) {
//            if(i>=1){
//             if(!credebitVMsArrya[i].getShomareBimename().substring(0, 21).equalsIgnoreCase(credebitVMsArrya[i - 1].getShomareBimename().substring(0, 21))  ){
//                  cList = new ArrayList<CredebitBimenameVM>();
//                 cList.add(credebitVMsArrya[i]) ;
//                 credebitReportHashmap.put(credebitVMsArrya[i].getShomareBimename(),cList);
//                  } else{
//                    cList.add(credebitVMsArrya[i]) ;
//                 credebitReportHashmap.put(credebitVMsArrya[i].getShomareBimename(),cList);
//        }
//                }else{
//             cList.add(credebitVMsArrya[i]) ;
//                credebitReportHashmap.put(credebitVMsArrya[i].getShomareBimename(),cList);
//             }
//
//       }
//                           List<CredebitVMReport>    vmList = new ArrayList<CredebitVMReport>();
//                          for (  int i=0;i<credebitVMsArrya.length;i++) {
//                                if(i>=1){
//                                 if(!credebitVMsArrya[i].getShomareBimename().substring(0, 21).equalsIgnoreCase(credebitVMsArrya[i-1].getShomareBimename().substring(0, 21))  ){
//                                       credebitVMReport= new CredebitVMReport();
//                             credebitVMReport.setShomarebimename(credebitVMsArrya[i].getShomareBimename());
//                                     credebitVMReport.setRcptName(credebitVMsArrya[i].getRcptName());
//                                  credebitVMReport.setCredebitVM2list(credebitReportHashmap.get(credebitVMsArrya[i].getShomareBimename()));
//                                        vmList.add( credebitVMReport);
//                                        }
//                                       }else{
//                                        credebitVMReport.setShomarebimename(credebitVMsArrya[i].getShomareBimename());
//                                    credebitVMReport.setRcptName(credebitVMsArrya[i].getRcptName());
//                                        credebitVMReport.setCredebitVM2list(credebitReportHashmap.get(credebitVMsArrya[i].getShomareBimename()));
//                                       vmList.add( credebitVMReport);
//                                   }
//
//
//                                  }
//            this.setCredebitVMReportList(vmList) ;
//        if(vmList.size()<=0 ) {
//            credebitVMReport= new CredebitVMReport();
//           credebitVMReportList.add(credebitVMReport);
//        }
//       System.out.println(vmList.size());
//
//
//        try {
//            destFileDIR = realPath + destFileDIR + "soratVaziateMaliBimename.pdf";
//            boolean b = (new File(realPath + destFileDIR)).mkdirs();
//            File destFile = new File(destFileDIR);
//            if (!destFile.exists()) destFile.createNewFile();
//            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMalibimename_row.jrxml", realPath + "report/soratVaziateMalibimename_row.jasper");
//            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMaliBimename.jrxml", realPath + "report/soratVaziateMaliBimename.jasper");
//            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
//        } catch (JRException e) {
//            e.printStackTrace();
//            return ERROR;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ERROR;
//        }
//        return SUCCESS;
//    }

    //b-h : method bala aval bud man taghiresh dadam be in
    public String printSoratVaziateMaliBimename() {
        vaziatMaliBimeNameReport=new soratVaziatMaliBimeNameReport();
        List<sooratVaziatMali_new> sooratVaziatMaliList=new ArrayList<sooratVaziatMali_new>();
        User user=loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        sooratVaziatMaliList=asnadeSodorService.findBedehiByShomareBimeName(shomareBimenameFrom,user);
        long sumOfPardakhtshenasedarVosoulshode=0;
        long sumOfDaryafteCheckVosoulshode=0;
        long sumOfPardakhtshenasedarVosoulNashode=0;
        long sumOfDaryafteCheckVosoulNashode=0;
        long sumOfOtherEtebarVosoulshode=0;
        long sumOfElhaghiyeBargashti=0;
        long sumOfEtebaratVosulShode=0;
        long sumOfbedehi=0;
        long sumOfBedehiSarresidShode=0;
        long sumOfBedehiSarresidShodeVosoulShode=0;
        long sumOfBedehiSarresidShodeVosoulnashode=0;
        long sumOfBedehiSarresidNaShode=0;

        if(sooratVaziatMaliList!=null && sooratVaziatMaliList.size()>0) {
            for(sooratVaziatMali_new so:sooratVaziatMaliList) {
                if(!so.getBedehi().getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI)){
                    sumOfbedehi+=so.getBedehi().getAmount_long();
                    if(so.getBedehi().getSarresidDate().compareTo( DateUtil.getCurrentDate())<0) {
                        sumOfBedehiSarresidShode+=so.getBedehi().getAmount_long();
                        if(so.getBedehi().getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                            sumOfBedehiSarresidShodeVosoulShode+= so.getBedehi().getAmount_long();
                        else
                            sumOfBedehiSarresidShodeVosoulnashode+=so.getBedehi().getAmount_long();
                    }
                    else
                        sumOfBedehiSarresidNaShode+=so.getBedehi().getAmount_long();
                }
                if(so.getEtebar() !=null && so.getEtebar().getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                    if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                        sumOfPardakhtshenasedarVosoulshode+=so.getEtebar().getAmount_long();
                    else if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK))
                        sumOfDaryafteCheckVosoulshode+=so.getEtebar().getAmount_long();
                    else if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI) || so.getEtebar().getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI))
                        sumOfElhaghiyeBargashti+= so.getEtebar().getAmount_long();
                    else
                        sumOfOtherEtebarVosoulshode+=so.getEtebar().getAmount_long();
                }

                else
                {
                    if(so.getEtebar() != null) {
                        if(  so.getEtebar().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                            sumOfPardakhtshenasedarVosoulNashode+=so.getEtebar().getAmount_long();
                        else
                            sumOfDaryafteCheckVosoulNashode+=so.getEtebar().getAmount_long();
                    }
                }
            }
        }
        sumOfEtebaratVosulShode= sumOfPardakhtshenasedarVosoulshode+sumOfDaryafteCheckVosoulshode+sumOfOtherEtebarVosoulshode+sumOfElhaghiyeBargashti;
        vaziatMaliBimeNameReport.setSooratVaziatMaliList(sooratVaziatMaliList);
        vaziatMaliBimeNameReport.setSumOfDaryafteCheckVosoulNashode(sumOfDaryafteCheckVosoulNashode);
        vaziatMaliBimeNameReport.setSumOfDaryafteCheckVosoulshode(sumOfDaryafteCheckVosoulshode);
        vaziatMaliBimeNameReport.setSumOfPardakhtshenasedarVosoulNashode(sumOfPardakhtshenasedarVosoulNashode);
        vaziatMaliBimeNameReport.setSumOfPardakhtshenasedarVosoulshode(sumOfPardakhtshenasedarVosoulshode);
        vaziatMaliBimeNameReport.setSumOfOtherEtebarVosoulshode(sumOfOtherEtebarVosoulshode);
//        vaziatMaliBimeNameReport.setSumOfEtebarat(sumOfDaryafteCheckVosoulshode+sumOfPardakhtshenasedarVosoulshode+sumOfOtherEtebarVosoulshode);
        vaziatMaliBimeNameReport.setSumOfElhaghiyeBargashti(sumOfElhaghiyeBargashti);
        vaziatMaliBimeNameReport.setSumOfbedehi(sumOfbedehi);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidNaShode(sumOfBedehiSarresidNaShode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShode(sumOfBedehiSarresidShode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShodeVosoulnashode(sumOfBedehiSarresidShodeVosoulnashode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShodeVosoulShode(sumOfBedehiSarresidShodeVosoulShode);
        vaziatMaliBimeNameReport.setSumOfEtebaratVosulShode(sumOfEtebaratVosulShode);
        try {
            destFileDIR = realPath + destFileDIR + "soratVaziateMaliBimename.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMalibimename_row.jrxml", realPath + "report/soratVaziateMalibimename_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/soratVaziateMaliBimename.jrxml", realPath + "report/soratVaziateMaliBimename.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String printListKoleBedehi(){
        pishnehadReport= preparForGozaresheListeKoleBedehi();
        try {
            destFileDIR = realPath + destFileDIR + "listKoleBedehi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/listKoleBedehi_row.jrxml", realPath + "report/listKoleBedehi_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/listKoleBedehi.jrxml", realPath + "report/listKoleBedehi.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;

    }
    public String printListKoleBedehiWithExcelFormat(){
        pishnehadReport= preparForGozaresheListeKoleBedehi();
        try {
            destFileDIR = realPath + destFileDIR + "listKoleBedehi.xls";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/listKoleBedehi_row.jrxml", realPath + "report/listKoleBedehi_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/listKoleBedehiWithExcelFormat.jrxml", realPath + "report/listKoleBedehiWithExcelFormat.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

   public PishnehadReport preparForGozaresheListeKoleBedehi() {
       Long bedehiAmount=0l;
       Long bedehiRemaining_Amount= 0L;
       Long pardakhtshodeAmount= 0L;
       User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
       pishnehadReport=new PishnehadReport();
       List<CredebitBedehiVM> bedehiCredebits=asnadeSodorService.findAllBedehiCredebits(rcptName,azTarikheSodoreBimename, taTarikheSodoreBimename,vahedeSodorId,pishShomareBimename,user ) ;
       for (CredebitBedehiVM credebitBedehiVM:bedehiCredebits)  {
           bedehiAmount+=credebitBedehiVM.getBedehiAmount();
           bedehiRemaining_Amount+= credebitBedehiVM.getBedehiRemaining_Amount();
           pardakhtshodeAmount=+credebitBedehiVM.getPardakhtshodeAmount() ;
       }
       pishnehadReport.setTotalBedehiAmount(String.valueOf(bedehiAmount));
       pishnehadReport.setSumBedehiRemaining_Amount(bedehiRemaining_Amount);
       pishnehadReport.setSumPardakhtshodeAmount(pardakhtshodeAmount);
       pishnehadReport.setCredebitBedehiVMList(bedehiCredebits);
       return   pishnehadReport;

   }

    //b-h
    public String excelCheckVagozarshode(){
//        code for generating excel vagozari check
//        pishnehadReport= preparForGozaresheListeKoleBedehi();
//        try {
//
//            destFileDIR = realPath + destFileDIR + "checkvagozarshode.xls";
//            boolean b = (new File(realPath + destFileDIR)).mkdirs();
//            File destFile = new File(destFileDIR);
//            pishnehadReport = new PishnehadReport();
//            pishnehadReport.setCredebitList(asnadeSodorService.findCheckByVagozari(credebitReport.getId()));
//            pishnehadReport.setVagozari(asnadeSodorService.findVagozariById(credebitReport.getId()));
//            if (!destFile.exists()) destFile.createNewFile();
//            double sum = 0.0;
//            System.out.println("vagozari");
//            System.out.println(pishnehadReport.getCredebitList().get(0).getVagozari());
//            for (Credebit c : pishnehadReport.getCredebitList()) {
//                sum += c.getAmount_long();
//                System.out.println(c.getDaryafteCheck().getSeri());
//                System.out.println(c.getDaryafteCheck().getBankName());
//            }
//            pishnehadReport.setSumOfPardakht(sum);
//            int tedadCheck=pishnehadReport.getCredebitList().size();
//            pishnehadReport.setTedadCheckHayeVagozarshode(new Long(tedadCheck));
//
//            System.out.println(realPath);
//            JasperCompileManager.compileReportToFile(realPath + "report/CheckVagozarshodeExcellFormat_row.jrxml", realPath + "report/CheckVagozarshodeExcellFormat_row.jasper");
//            System.out.println("=======here ");
//            JasperCompileManager.compileReportToFile(realPath + "report/CheckVagozarshodeExcellFormat.jrxml", realPath + "report/CheckVagozarshodeExcellFormat.jasper");
//            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
//        } catch (JRException e) {
//            e.printStackTrace();
//            return ERROR;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ERROR;
//        }
//        return SUCCESS;
        try{
            List<Credebit> credebits=asnadeSodorService.findCheckByVagozari(credebitReport.getId());
            Vagozari vagozari=asnadeSodorService.findVagozariById(credebitReport.getId());
            destFileDIR = realPath + destFileDIR + "checkvagozarshode.CLR";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            FileWriter writer=new FileWriter(destFile);
           // writer.append("hello welcome bahare!");
            String headerRow="";
            headerRow+= vagozari.getCreateDate().replaceAll("/","");
            headerRow+="4757575763";
            int tedadeAsnad=credebits.size();
            NumberFormat tedadeAsnadFormatter = new DecimalFormat("0000");
            headerRow+= tedadeAsnadFormatter.format(tedadeAsnad);
            System.out.println(tedadeAsnadFormatter.format(tedadeAsnad));
            String details="";
            int sumOfAmounts=0;
             int index=0;
            for(Credebit credebit:credebits){
                System.out.println("round "+index);
                index++;
                details+="00";  //seri harfi
                details+=String.format("%06d",Integer.parseInt(credebit.getDaryafteCheck().getSeri()));
                System.out.println(String.format("%06d",Integer.parseInt(credebit.getDaryafteCheck().getSeri())));
                details+=String.format("%08d",Integer.parseInt(credebit.getDaryafteCheck().getSerial()));
                System.out.println(String.format("%08d",Integer.parseInt(credebit.getDaryafteCheck().getSerial())));
                String hesabbanki=credebit.getDaryafteCheck().getHesabBanki();
                while(hesabbanki.length()<18)
                    hesabbanki="0"+hesabbanki;
                details+=hesabbanki;
                System.out.println(hesabbanki);
                details+=credebit.getBankName().substring(credebit.getBankName().length()-2,credebit.getBankName().length());
                System.out.println("kode shobe bank"+credebit.getBankName().substring(credebit.getBankName().length()-2,credebit.getBankName().length()));
                details+=String.format("%07d",Integer.parseInt(credebit.getDaryafteCheck().getBranchCode()));
                System.out.println(String.format("%07d",Integer.parseInt(credebit.getDaryafteCheck().getBranchCode())));
                details+="1"; //noe sanad
                details+= credebit.getSarresidDate().replaceAll("/","");
                details+=String.format("%015d",credebit.getAmount_long());
                System.out.println(String.format("%015d",credebit.getAmount_long()));
                details+=credebit.getShomareMoshtari();
                details+="\r\n";
                sumOfAmounts+= credebit.getAmount_long();
            }
            headerRow+=String.format("%018d",sumOfAmounts);
            System.out.println(String.format("%018d",sumOfAmounts));
            headerRow+="\r\n";
            writer.append(headerRow);
            writer.append(details);
            //writer.append("\n");
            writer.flush();
            writer.close();
            fileCLRBank=new FileInputStream(destFile);
            fileCLRBank_name="4757575763.CLR";

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return ERROR;
        }
        catch (IOException e){
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    //b-h
    public String printBedehiNamayande(){
        User user=loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        vaziatMaliBimeNameReport=new soratVaziatMaliBimeNameReport();
        List<sooratVaziatMali_new> sooratVaziatMaliList=asnadeSodorService.findbedehiNamayandeForGozaresh(namayandegiId_gozareshbedehi,user);
        long sumOfPardakhtshenasedarVosoulshode=0;
        long sumOfDaryafteCheckVosoulshode=0;
        long sumOfPardakhtshenasedarVosoulNashode=0;
        long sumOfDaryafteCheckVosoulNashode=0;
        long sumOfOtherEtebarVosoulshode=0;
        long sumOfElhaghiyeBargashti=0;
        long sumOfbedehi=0;
        long sumOfBedehiSarresidShode=0;
        long sumOfBedehiSarresidShodeVosoulShode=0;
        long sumOfBedehiSarresidShodeVosoulnashode=0;
        long sumOfBedehiSarresidNaShode=0;
        long sumOfEtebaratVosulShode=0;
        if(sooratVaziatMaliList!=null && sooratVaziatMaliList.size()>0) {
            for(sooratVaziatMali_new so:sooratVaziatMaliList) {
                if(so.getBedehi().getAmount_long() != null)
                    sumOfbedehi+=so.getBedehi().getAmount_long();
                if(so.getBedehi().getSarresidDate() !=null){
                    if(so.getBedehi().getSarresidDate().compareTo( DateUtil.getCurrentDate())<0) {
                         sumOfBedehiSarresidShode+=so.getBedehi().getAmount_long();
                          if(so.getBedehi().getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL))
                                sumOfBedehiSarresidShodeVosoulShode+= so.getBedehi().getAmount_long();
                          else
                                sumOfBedehiSarresidShodeVosoulnashode+=so.getBedehi().getAmount_long();
                        }
                        else
                            sumOfBedehiSarresidNaShode+=so.getBedehi().getAmount_long();
                }
                else
                    sumOfBedehiSarresidNaShode+=so.getBedehi().getAmount_long();
                if(so.getEtebar() !=null && so.getEtebar().getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                    if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                        sumOfPardakhtshenasedarVosoulshode+=so.getEtebar().getAmount_long();
                    else if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK))
                        sumOfDaryafteCheckVosoulshode+=so.getEtebar().getAmount_long();
                    else if(so.getEtebar().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI) || so.getEtebar().getCredebitType().equals(Credebit.CredebitType.VEHICLE_HAGHBIME_BARGASHTI))
                        sumOfElhaghiyeBargashti+= so.getEtebar().getAmount_long();
                    else
                        sumOfOtherEtebarVosoulshode+=so.getEtebar().getAmount_long();
                }

                else
                {
                    if(so.getEtebar() != null) {
                        if(  so.getEtebar().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR))
                            sumOfPardakhtshenasedarVosoulNashode+=so.getEtebar().getAmount_long();
                        else
                            sumOfDaryafteCheckVosoulNashode+=so.getEtebar().getAmount_long();
                    }
                }
            }
        }
        sumOfEtebaratVosulShode= sumOfPardakhtshenasedarVosoulshode+sumOfDaryafteCheckVosoulshode+sumOfOtherEtebarVosoulshode+sumOfElhaghiyeBargashti;
        vaziatMaliBimeNameReport.setSooratVaziatMaliList(sooratVaziatMaliList);
        vaziatMaliBimeNameReport.setSumOfDaryafteCheckVosoulNashode(sumOfDaryafteCheckVosoulNashode);
        vaziatMaliBimeNameReport.setSumOfDaryafteCheckVosoulshode(sumOfDaryafteCheckVosoulshode);
        vaziatMaliBimeNameReport.setSumOfPardakhtshenasedarVosoulNashode(sumOfPardakhtshenasedarVosoulNashode);
        vaziatMaliBimeNameReport.setSumOfPardakhtshenasedarVosoulshode(sumOfPardakhtshenasedarVosoulshode);
        vaziatMaliBimeNameReport.setSumOfOtherEtebarVosoulshode(sumOfOtherEtebarVosoulshode);
        vaziatMaliBimeNameReport.setSumOfElhaghiyeBargashti(sumOfElhaghiyeBargashti);
        vaziatMaliBimeNameReport.setSumOfbedehi(sumOfbedehi);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidNaShode(sumOfBedehiSarresidNaShode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShode(sumOfBedehiSarresidShode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShodeVosoulnashode(sumOfBedehiSarresidShodeVosoulnashode);
        vaziatMaliBimeNameReport.setSumOfBedehiSarresidShodeVosoulShode(sumOfBedehiSarresidShodeVosoulShode);
        vaziatMaliBimeNameReport.setSumOfEtebaratVosulShode(sumOfEtebaratVosulShode);


        try {
            destFileDIR = realPath + destFileDIR + "listBedehiNamayande.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/printBedehiNamayande_row.jrxml", realPath + "report/printBedehiNamayande_row.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/printBedehiNamayande.jrxml", realPath + "report/printBedehiNamayande.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        } catch (JRException e) {
            e.printStackTrace();
            return ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public Pishnehad getPishnehad() {
        return pishnehad;
    }

    public void setPishnehad(Pishnehad pishnehad) {
        this.pishnehad = pishnehad;
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

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public boolean isCorrectCalculation() {
        return correctCalculation;
    }

    public void setCorrectCalculation(boolean correctCalculation) {
        this.correctCalculation = correctCalculation;
    }

    public Credebit getCredebitReport() {
        return credebitReport;
    }

    public void setCredebitReport(Credebit credebitReport) {
        this.credebitReport = credebitReport;
    }

    public PishnehadReport getPishnehadReport() {
        return pishnehadReport;
    }

    public void setPishnehadReport(PishnehadReport pishnehadReport) {
        this.pishnehadReport = pishnehadReport;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public BatchTaghsitVM getBatchTaghsitVMS() {
        return batchTaghsitVMS;
    }

    public void setBatchTaghsitVMS(BatchTaghsitVM batchTaghsitVMS) {
        this.batchTaghsitVMS = batchTaghsitVMS;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public IDarkhastService getDarkhastService() {
        return darkhastService;
    }

    public void setDarkhastService(IDarkhastService darkhastService) {
        this.darkhastService = darkhastService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public ICheckService getCheckService() {
        return checkService;
    }

    public IKhesaratService getKhesaratService()
    {
        return khesaratService;
    }

    public void setKhesaratService(IKhesaratService khesaratService)
    {
        this.khesaratService = khesaratService;
    }

    public void setCheckService(ICheckService checkService) {
        this.checkService = checkService;
    }

    public ReportSharayetBardasht getReportSharayetBardasht() {
        return reportSharayetBardasht;
    }

    public void setReportSharayetBardasht(ReportSharayetBardasht reportSharayetBardasht) {
        this.reportSharayetBardasht = reportSharayetBardasht;
    }

    public String getHadeAksarVam() {
        return hadeAksarVam;
    }

    public void setHadeAksarVam(String hadeAksarVam) {
        this.hadeAksarVam = hadeAksarVam;
    }

    public String getHadeAksarBardasht() {
        return hadeAksarBardasht;
    }

    public void setHadeAksarBardasht(String hadeAksarBardasht) {
        this.hadeAksarBardasht = hadeAksarBardasht;
    }

    public String getNamayandeType() {
        return namayandeType;
    }

    public void setNamayandeType(String namayandeType) {
        this.namayandeType = namayandeType;
    }

    public DarkhastBazkharid getDarkhastBazkharid() {
        return darkhastBazkharid;
    }

    public void setDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        this.darkhastBazkharid = darkhastBazkharid;
    }


    public String getMablaghBedehiAslVam() {
        return mablaghBedehiAslVam;
    }

    public void setMablaghBedehiAslVam(String mablaghBedehiAslVam) {
        this.mablaghBedehiAslVam = mablaghBedehiAslVam;
    }

    public String getMablaghBedehiAghsatVam() {
        return mablaghBedehiAghsatVam;
    }

    public void setMablaghBedehiAghsatVam(String mablaghBedehiAghsatVam) {
        this.mablaghBedehiAghsatVam = mablaghBedehiAghsatVam;
    }

    public String getMablaghBedehiBimename() {
        return mablaghBedehiBimename;
    }

    public Boolean getOmr()
    {
        return isOmr;
    }

    public void setOmr(Boolean omr)
    {
        isOmr = omr;
    }

    public void setMablaghBedehiBimename(String mablaghBedehiBimename) {
        this.mablaghBedehiBimename = mablaghBedehiBimename;
    }

    public String getMablaghBedehiJarimeVam() {
        return mablaghBedehiJarimeVam;
    }

    public void setMablaghBedehiJarimeVam(String mablaghBedehiJarimeVam) {
        this.mablaghBedehiJarimeVam = mablaghBedehiJarimeVam;
    }

    public String getEmza1() {
        return emza1;
    }

    public void setEmza1(String emza1) {
        this.emza1 = emza1;
    }

    public String getEmza2() {
        return emza2;
    }

    public void setEmza2(String emza2) {
        this.emza2 = emza2;
    }

    public String getEnseraf() {
        return enseraf;
    }

    public void setEnseraf(String enseraf) {
        this.enseraf = enseraf;
    }

    public ITransitionLogService getTransitionLogService() {
        return transitionLogService;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }


    public String getSerialStart() {
        return serialStart;
    }

    public void setSerialStart(String serialStart) {
        this.serialStart = serialStart;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public String getGhableTaghir() {
        return ghableTaghir;
    }

    public void setGhableTaghir(String ghableTaghir) {
        this.ghableTaghir = ghableTaghir;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public ILogService getLogService() {
        return logService;
    }

    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    public List<Credebit> getCredebitReportList() {
        return credebitReportList;
    }

    public void setCredebitReportList(List<Credebit> credebitReportList) {
        this.credebitReportList = credebitReportList;
    }

    public String getGenreDaftarche()
    {
        return genreDaftarche;
    }

    public void setGenreDaftarche(String genreDaftarche)
    {
        this.genreDaftarche = genreDaftarche;
    }

    public List<CredebitVMReport> getCredebitVMReportList() {
        return credebitVMReportList;
    }

    public void setCredebitVMReportList(List<CredebitVMReport> credebitVMReportList) {
        this.credebitVMReportList = credebitVMReportList;
    }

    public CredebitVMReport getCredebitVMReport() {
        return credebitVMReport;
    }

    public void setCredebitVMReport(CredebitVMReport credebitVMReport) {
        this.credebitVMReport = credebitVMReport;
    }
    public String getShomareBimenameTo() {
        return shomareBimenameTo;
    }


    public void setShomareBimenameTo(String shomareBimenameTo) {
        this.shomareBimenameTo = shomareBimenameTo;
    }

    public String getShomareBimenameFrom() {
        return shomareBimenameFrom;
    }

    public void setShomareBimenameFrom(String shomareBimenameFrom) {
        this.shomareBimenameFrom = shomareBimenameFrom;
    }

    public Integer getSanadId() {
        return sanadId;
    }

    public void setSanadId(Integer sanadId) {
        this.sanadId = sanadId;
    }

    public String getVahedeSodorId() {
        return vahedeSodorId;
    }

    public void setVahedeSodorId(String vahedeSodorId) {
        this.vahedeSodorId = vahedeSodorId;
    }

    public EtebarBedehiVM getEtebarBedehiVM() {
        return etebarBedehiVM;
    }

    public void setEtebarBedehiVM(EtebarBedehiVM etebarBedehiVM) {
        this.etebarBedehiVM = etebarBedehiVM;
    }

    public EtebarBedehiVMReport getEtebarBedehiVMReport() {
        return etebarBedehiVMReport;
    }

    public void setEtebarBedehiVMReport(EtebarBedehiVMReport etebarBedehiVMReport) {
        this.etebarBedehiVMReport = etebarBedehiVMReport;
    }

    public List<EtebarBedehiVMReport> getEtebarBedehiVMReportList() {
        return etebarBedehiVMReportList;
    }

    public void setEtebarBedehiVMReportList(List<EtebarBedehiVMReport> etebarBedehiVMReportList) {
        this.etebarBedehiVMReportList = etebarBedehiVMReportList;
    }
    public List<Credebit> getBedehiCredebit() {
        return bedehiCredebit;
    }

    public void setBedehiCredebit(List<Credebit> bedehiCredebit) {
        this.bedehiCredebit = bedehiCredebit;
    }


    public String getRcptName() {
        return rcptName;
    }

    public void setRcptName(String rcptName) {
        this.rcptName = rcptName;
    }
    public String getPishShomareBimename() {
        return pishShomareBimename;
    }

    public void setPishShomareBimename(String pishShomareBimename) {
        this.pishShomareBimename = pishShomareBimename;
    }

    public String getSodorSanadDateTo() {
        return sodorSanadDateTo;
    }

    public void setSodorSanadDateTo(String sodorSanadDateTo) {
        this.sodorSanadDateTo = sodorSanadDateTo;
    }

    public String getSodorSanadDateFrom() {
        return sodorSanadDateFrom;
    }

    public void setSodorSanadDateFrom(String sodorSanadDateFrom) {
        this.sodorSanadDateFrom = sodorSanadDateFrom;
    }



}
