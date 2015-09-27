package com.bitarts.parsian.action.sodureJami;

import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.model.MafadeGharardad;
import com.bitarts.parsian.model.UploadedFile;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.viewModel.PishnehadDuplicationRules;
import com.opensymphony.xwork2.ActionSupport;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 12/12/12
 * Time: 2:37 PM
 */
public class SodureJamieKhasAction extends ActionSupport implements ServletContextAware {
    private User user;
    private Gharardad gharardad;
    private String templatePishnehadRadif;
    private File upload;
    private String uploadFileName;
    private String realPath = "";
    private String selectedMenu;

    private String bimeGozarSource;
    private String bimeShodeSource;
    private String bimenameSource;
    private String soalateOmoomiSource;
    private String vaziateSalamateBimeShodeSource;
    private String vaziateSalamateKhanevadeSource;


    private IPishnehadService pishnehadService;
    private ILoginService loginService;
    private IShakhsService shakhsService;
    private IConstantItemsService constantItemsService;
    private IGharardadService gharardadService;
    private IUploadedFileService uploadedFileService;

    private FileService fileService;

    public void setServletContext(ServletContext servletContext) {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        loginService = (ILoginService) wac.getBean("loginService");
        shakhsService = (IShakhsService) wac.getBean("shakhsService");
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
        gharardadService = (IGharardadService) wac.getBean("gharardadService");
        uploadedFileService = (IUploadedFileService) wac.getBean("uploadedFileService");
        fileService = (FileService) wac.getBean("fileService");
    }

    public String ijadePishnehadateKhas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null)
            user = loginService.findUserByUsername(username);
        if (user == null)
            return ERROR;

        Pishnehad templatePishnehad = pishnehadService.findByRadif(templatePishnehadRadif);

        PishnehadDuplicationRules duplicationRules = getMafad();
        duplicationRules.setSource(PishnehadDuplicationRules.DuplicationSource.SodorJami);

        if(templatePishnehad == null){
            addActionError("پیشنهادی با کد رهگیری وارد شده وجود ندارد.");
        }else if(upload == null){
            addActionError("فایلی آپلود نشده است.");
        }else if(gharardad.getId() == null){
            addActionError("شماره قرارداد نامعلوم است.");
        }else {
            try {
                gharardad = gharardadService.findById(gharardad.getId());
                List<Pishnehad> pishnehads =  makePishnehads(gharardad, upload, duplicationRules);
                if(pishnehads != null && !pishnehads.isEmpty()){
                    pishnehadService.createAllPishnehadsMergeByTemplate(templatePishnehad, pishnehads, duplicationRules, gharardad);

                    UploadedFile file = new UploadedFile();
                    file.setGharardad(gharardad);
                    file.setCreatedDateTime(DateUtil.getCurrentDate() + " " + DateUtil.getCurrentTime());
                    file.setType(UploadedFile.UploadedFileType.ExceleGharardadeJamieKhas);
                    //TODO: Upload file to logical doc
                    //file.setFileId(fileService.createFile(gharardad.getNamayande().getKodeNamayandeKargozar() + "-" + String.valueOf(DateUtil.extractYear(DateUtil.getCurrentDate())), uploadFileName, upload));
                    uploadedFileService.saveUploadedFile(file);
                    gharardad.getUploadedFiles().add(file);

                    if (gharardad.getMafadeGharardad() == null || gharardad.getMafadeGharardad().getId() == null) {
                        MafadeGharardad mafad = new MafadeGharardad();
                        mafad.setGharardad(gharardad);
                        mafad.setTemplatePishnehad(templatePishnehad);
                        mafad.setBimegozarSource(duplicationRules.getBimegozarSource());
                        mafad.setBimeshodeSource(duplicationRules.getBimeshodeSource());
                        mafad.setBimenameSource(duplicationRules.getBimenameSource());
                        mafad.setSoalateOmoomiSource(duplicationRules.getSoalateOmoomiSource());
                        mafad.setVaziateSalamateBimeShodeSource(duplicationRules.getVaziateSalamateBimeShodeSource());
                        mafad.setVaziateSalamateKhanevadeSource(duplicationRules.getVaziateSalamateKhanevadeSource());
                        gharardadService.saveMafadeGharardad(mafad);
                        gharardad.setMafadeGharardad(mafad);
                    }

                    addActionMessage("پیشنهادات فایل آپلود شده ایجاد گردید.");
                }else if(pishnehads != null && pishnehads.isEmpty()){
                    addActionError("فایل آپلود شده دارای هیچ پیشنهادی نیست.");
                }
            } catch (BiffException e) {
                addActionError("فایل آپلود شده منطبق با فرمت درخواستی نمی باشد.");
            } catch (IOException e) {
                addActionError("فایل آپلود شده منطبق با فرمت درخواستی نمی باشد.");
            } catch (Exception e) {
                e.printStackTrace();
                addActionError("خطای نامشخص!");
            }
        }
        return SUCCESS;
    }

    private PishnehadDuplicationRules getMafad() {
        PishnehadDuplicationRules mafad = new PishnehadDuplicationRules();
        if (bimeGozarSource.equals("gharardad"))
            mafad.setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource.SherkateTarafeGharardad);
        else if (bimeGozarSource.equals("excel"))
            mafad.setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource.AzExcel);
        else
            mafad.setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        if (bimeShodeSource.equals("bimegozar"))
            mafad.setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource.SameAsBimeGozar);
        else if (bimeShodeSource.equals("excel"))
            mafad.setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzExcel);
        else
            mafad.setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        if (bimenameSource.equals("template"))
            mafad.setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        else if (bimenameSource.equals("excel"))
            mafad.setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource.AzExcel);
        else
            mafad.setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        if (soalateOmoomiSource.equals("template"))
            mafad.setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
        else if (soalateOmoomiSource.equals("excel"))
            mafad.setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource.AzExcel);
        else
            mafad.setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        if (vaziateSalamateBimeShodeSource.equals("salem"))
            mafad.setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource.AllHealthy);
        else if (vaziateSalamateBimeShodeSource.equals("tafkik"))
            mafad.setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource.BeTafkikePishnehad);
        else
            mafad.setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        if (vaziateSalamateKhanevadeSource.equals("parentexcel"))
            mafad.setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource.AzExcel);
        else if (vaziateSalamateKhanevadeSource.equals("tafkik"))
            mafad.setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource.BeTafkikePishnehad);
        else
            mafad.setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);

        return mafad;
    }

    private static final Logger logger = Logger.getLogger(SodureJamieKhasAction.class);

    private List<Pishnehad> makePishnehads(Gharardad gharardad, File uploadFile, PishnehadDuplicationRules duplicationRules) throws BiffException, IOException {
        List<Pishnehad> pishnehadList = new LinkedList<Pishnehad>();
        Boolean hasError = false;
        Workbook book = Workbook.getWorkbook(uploadFile);
        Sheet sheet = book.getSheet(0);

        List<Long> bimeGozarMelliCodeErrors = new LinkedList<Long>();
        List<Long> bimeShodeMelliCodeErrors = new LinkedList<Long>();
        List<Long> incompletePishnehadErrors = new LinkedList<Long>();
        List<Long> duplicateBimeShodeErrors = new LinkedList<Long>();
//        List<Long> emptyNameBimeShodeErrors = new LinkedList<Long>();

        HashMap<String, String> hmBimeShodeMelliCodes = new HashMap<String, String>();

        for (int i = 2; i < sheet.getRows(); i++) {
            try {
                if(i%100 == 0) logger.info("Row of Excel :" + i);
                Pishnehad pishnehad = ExcelExtraction.extractPishnehad(sheet, i, constantItemsService, duplicationRules);
                if(pishnehad == null) { // End of data
                    break;
                }

                if (duplicationRules.getBimegozarSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel &&
                        !StringUtil.validCodeMelli(pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi())) {
                    //addActionMessage("کد ملی وارد شده در ردیف " + String.valueOf(i + 1) + " نادرست می باشد.");
                    hasError = true;
                    bimeGozarMelliCodeErrors.add(Long.valueOf(i+1));
                    continue;
                }

                if (duplicationRules.getBimeshodeSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
                    if (hmBimeShodeMelliCodes.containsKey(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi())) {
                        hasError = true;
                        duplicateBimeShodeErrors.add(Long.valueOf(i+1));
                        continue;
                    }

                    if (!StringUtil.validCodeMelli(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi())) {
                        hasError = true;
                        bimeShodeMelliCodeErrors.add(Long.valueOf(i+1));
                        continue;
                    }

                    Shakhs existingShakhs = shakhsService.searchShakhs(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(),false);
                    if (existingShakhs == null) {
                    }else if(shakhsService.isShakhsValid(existingShakhs.getKodeMelliShenasayi()) != "NO"){
                        //addActionMessage("شخص وارد شده در ردیف " + String.valueOf(i + 1) + "در حال حاضر در سیستم وجود داشته و پیشنهادی با وضعیت ناتمام دارد.");
                        incompletePishnehadErrors.add(Long.valueOf(i+1));
                        hasError = true;
                        continue;
                    }else {
                        pishnehad.getBimeShode().setShakhs(existingShakhs);
                    }

                    hmBimeShodeMelliCodes.put(pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(), null);
                }

                pishnehadList.add(pishnehad);
            } catch (Exception ex) {
                hasError = true;
                addActionError("خطا در ردیف " + String.valueOf(i + 1) + " در فایل اکسل");
            }
        }

        if (hasError) {
            String strBimegozarMelliCodeRows = "";
            int i = 0;
            for (Long index : bimeGozarMelliCodeErrors) {
                if (i++ != 0)
                    strBimegozarMelliCodeRows += "، ";
                strBimegozarMelliCodeRows += index;
            }
            if (bimeGozarMelliCodeErrors.size() > 0)
                addActionError("کد ملی بیمه گذار وارد شده در ردیف(های) " + strBimegozarMelliCodeRows + " دارای اشکال می باشد.");

            String strBimeshodeMelliCodeRows = "";
            i = 0;
            for (Long index : bimeShodeMelliCodeErrors) {
                if (i++ != 0)
                    strBimeshodeMelliCodeRows += "، ";
                strBimeshodeMelliCodeRows += index;
            }
            if (bimeShodeMelliCodeErrors.size() > 0)
                addActionError("کد ملی بیمه شده وارد شده در ردیف(های) " + strBimeshodeMelliCodeRows + " دارای اشکال می باشد.");

            String strIncompletePishnehadRows = "";
            for (Long index : incompletePishnehadErrors) {
                if (index != 0)
                    strIncompletePishnehadRows += "، ";
                strIncompletePishnehadRows += index;
            }
            if (incompletePishnehadErrors.size() > 0)
                addActionError("شخص (اشخاص) بیمه شده در ردیفهای " + strIncompletePishnehadRows + " در حال حاضر در سیستم وجود داشته و پیشنهاد با وضعیت ناتمام دارد.");

            String strDuplicateBimeshodeRows = "";
            for (Long index : duplicateBimeShodeErrors) {
                if (index != 0)
                    strDuplicateBimeshodeRows += "، ";
                strDuplicateBimeshodeRows += index;
            }
            if (duplicateBimeShodeErrors.size() > 0)
                addActionError("اشخاص بیمه شده در ردیفهای " + strDuplicateBimeshodeRows + " تکراری می باشند.");

            return null;
        }

        return pishnehadList;
    }

    public String getTemplatePishnehadRadif() {
        return templatePishnehadRadif;
    }

    public void setTemplatePishnehadRadif(String templatePishnehadRadif) {
        this.templatePishnehadRadif = templatePishnehadRadif;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getRealPath() {
        return realPath;
    }

    public String getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Gharardad getGharardad() {
        return gharardad;
    }

    public void setGharardad(Gharardad gharardad) {
        this.gharardad = gharardad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBimeGozarSource() {
        return bimeGozarSource;
    }

    public void setBimeGozarSource(String bimeGozarSource) {
        this.bimeGozarSource = bimeGozarSource;
    }

    public String getBimeShodeSource() {
        return bimeShodeSource;
    }

    public void setBimeShodeSource(String bimeShodeSource) {
        this.bimeShodeSource = bimeShodeSource;
    }

    public String getBimenameSource() {
        return bimenameSource;
    }

    public void setBimenameSource(String bimenameSource) {
        this.bimenameSource = bimenameSource;
    }

    public String getSoalateOmoomiSource() {
        return soalateOmoomiSource;
    }

    public void setSoalateOmoomiSource(String soalateOmoomiSource) {
        this.soalateOmoomiSource = soalateOmoomiSource;
    }

    public String getVaziateSalamateBimeShodeSource() {
        return vaziateSalamateBimeShodeSource;
    }

    public void setVaziateSalamateBimeShodeSource(String vaziateSalamateBimeShodeSource) {
        this.vaziateSalamateBimeShodeSource = vaziateSalamateBimeShodeSource;
    }

    public String getVaziateSalamateKhanevadeSource() {
        return vaziateSalamateKhanevadeSource;
    }

    public void setVaziateSalamateKhanevadeSource(String vaziateSalamateKhanevadeSource) {
        this.vaziateSalamateKhanevadeSource = vaziateSalamateKhanevadeSource;
    }
}
