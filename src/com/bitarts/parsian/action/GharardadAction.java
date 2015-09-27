package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DasteSerial;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.bimename.Serial;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Reports.FRs;
import com.bitarts.parsian.service.implementation.AsnadeSodorService;
import com.bitarts.parsian.viewModel.PishPardakhtReport;
import com.bitarts.parsian.viewModel.PishnehadReport;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 7/16/12
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class GharardadAction extends BaseAction implements ServletContextAware {
    private IGharardadService gharardadService;
    private Gharardad gharardad;
    private List<Tarh> tarhs;
    private List<Namayande> namayandeList;
    private IConstantItemsService constantItemsService;
    private INamayandeService namayandeService;
    private ILoginService loginService;
    private IUserService userService;
    private IPishnehadService pishnehadService;
    private IConstantsService constantsService;
    private User user;
    private String aztarikh, tatarikh;
    private File uploadFile;
    private String uploadFileFileName;
    private FileService fileService;
    private File uploadFileFish;
    private String uploadFileFishFileName, tozih;
    private IShakhsService shakhsService;
    private List<Integer> checkBoxPishnehad;
    private List<Integer> checkBoxBimename;
    private List<PishnehadReport> pishnehadReportList;
    private String destFileDIR;
    private String realPath;
    private String sourceFilePath;
    private ISequenceService sequenceService;
    private Map<City, List<User>> karshenasha;
    private Long karshenasId;
    private IEstelamService estelamService;
    private ITransitionLogService transitionLogService;
    private EmzaKonande emzaKonandeAval;
    private EmzaKonande emzaKonandeDovom;
    private String tarikhSodur, tarikhShoru;
    private IClinicService clinicService;
    private IAsnadeSodorService asnadeSodorService;
    private String selectedMenu;
    PaginatedListImpl<Pishnehad> pishnehadPaginatedList;
    PaginatedListImpl<Bimename> bimenamePaginatedList;

    private String checkBoxSelectAllPishnehad;
    private String checkBoxSelectAllBimename;
    private Long serialStart;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        realPath = servletContext.getRealPath("/");
        gharardadService = (IGharardadService) wac.getBean("gharardadService");
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
        namayandeService = (INamayandeService) wac.getBean("namayandeService");
        loginService = (ILoginService) wac.getBean("loginService");
        userService = (IUserService) wac.getBean("userService");
        pishnehadService = (IPishnehadService) wac.getBean("pishnehadService");
        fileService = (FileService) wac.getBean("fileService");
        shakhsService = (IShakhsService) wac.getBean("shakhsService");
        sequenceService = (ISequenceService) wac.getBean("sequenceService");
        estelamService = (IEstelamService) wac.getBean("estelamService");
        transitionLogService = (ITransitionLogService) wac.getBean("transitionLogService");
        clinicService = (IClinicService) wac.getBean("clinicService");
        asnadeSodorService = (IAsnadeSodorService) wac.getBean("asnadeSodorService");
        this.constantsService = (IConstantsService) wac.getBean("constantsService");
    }

    public String delGharardad() {
        boolean error = false;
        if (gharardad != null && gharardad.getId() != null) {
            gharardad = gharardadService.findById(gharardad.getId());
            for (Pishnehad p : gharardad.getPishnehadList()) {
                if (p.getState().getId() >= 270) {
                    error = true;
                    break;
                }
            }
            if (!error) {
                gharardadService.removeById(gharardad.getId());
            } else addActionMessage("این قرارداد شامل پیشنهادی در وضعیت صدور با اعلام اقساط می باشد.");
        }
        return SUCCESS;
    }

    public String showGharardad() {
        if (gharardad != null && gharardad.getId() != null) {
            gharardad = gharardadService.findById(gharardad.getId());

            pishnehadPaginatedList = pishnehadService.findAllByGharardadId(gharardad.getId());
            bimenamePaginatedList = pishnehadService.findAllBimenameByGharardadId(gharardad.getId());
            //lazy load credebits:
//            for (Pishnehad p : gharardad.getPishnehadList())
//                p.getCredebits();
        }
        return SUCCESS;
    }

    public List<Tarh> getTarhs() {
        if (tarhs == null)
            tarhs = constantsService.listAllTarhs();
        return tarhs;
    }

    public void setTarhs(List<Tarh> tarhs) {
        this.tarhs = tarhs;
    }

    public IGharardadService getGharardadService() {
        return gharardadService;
    }

    public void setGharardadService(IGharardadService gharardadService) {
        this.gharardadService = gharardadService;
    }

    public Gharardad getGharardad() {
        return gharardad;
    }

    public void setGharardad(Gharardad gharardad) {
        this.gharardad = gharardad;
    }

    public List<Namayande> getNamayandeList() {
        if (namayandeList == null)
            namayandeList = namayandeService.getAllNamayande();
        return namayandeList;
    }

    public void setNamayandeList(List<Namayande> namayandeList) {
        this.namayandeList = namayandeList;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAztarikh() {
        return aztarikh;
    }

    public void setAztarikh(String aztarikh) {
        this.aztarikh = aztarikh;
    }

    public String getTatarikh() {
        return tatarikh;
    }

    public void setTatarikh(String tatarikh) {
        this.tatarikh = tatarikh;
    }

    public String addGharardad() {
        return SUCCESS;
    }

    public String validateSabtGharardad() {
        boolean error = false;
        if (gharardad.getNamayande() == null || gharardad.getNamayande().getId() == null || namayandeService.getNamayandeById(gharardad.getNamayande().getId()) == null) {
            addActionError("نماینده انتخاب نشده است");
            error = true;
        }
        if (gharardad.getHesab_bank() == null || gharardad.getHesab_bank().isEmpty()) {
            addActionError("نام بانک وارد نشده است.");
            error = true;
        }
        if (gharardad.getHesab_kodShobe() == null || gharardad.getHesab_kodShobe().isEmpty()) {
            addActionError("کد شعبه درست وارد نشده است.");
            error = true;
        }
        if (gharardad.getHesab_name() == null || gharardad.getHesab_name().isEmpty()) {
            addActionError("نام حساب وارد نشده است.");
            error = true;
        }
        if (gharardad.getHesab_sheba() == null || gharardad.getHesab_sheba().isEmpty()) {
            addActionError("شبای حساب وارد نشده است");
            error = true;
        }
        if (gharardad.getHesab_shobe() == null || gharardad.getHesab_shobe().isEmpty()) {
            addActionError("شعبه وارد نشده است");
            error = true;
        }
        if (gharardad.getHesab_shomare() == null || gharardad.getHesab_shomare().isEmpty()) {
            addActionError("شماره حساب وارد نشده است");
            error = true;
        }
        if (gharardad.getNameSherkat() == null || gharardad.getNameSherkat().isEmpty()) {
            addActionError("نام شرکت وارد نشده است");
            error = true;
        }
        if (gharardad.getShomareSabt() == null || gharardad.getShomareSabt().isEmpty()) {
            addActionError("شماره ثبت شرکت وارد نشده است");
            error = true;
        }
        if (gharardad.getTelphone() == null || gharardad.getTelphone().isEmpty()) {
            addActionError("شماره تلفن وارد نشده است");
            error = true;
        }
        return error ? INPUT : SUCCESS;
    }

    public String sabtGharardad() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null)
            user = loginService.findUserByUsername(username);
        if (user == null || gharardad == null) return ERROR;
        Gharardad ghabli = null;
        if (gharardad.getId() != null) ghabli = gharardadService.findById(gharardad.getId());
        gharardad.setNamayande(namayandeService.getNamayandeById(gharardad.getNamayande().getId()));
        if (ghabli == null || ghabli.getUserCreator() == null)
            gharardad.setUserCreator(user);
        else gharardad.setUserCreator(ghabli.getUserCreator());

        if (ghabli == null || ghabli.getShomare() == null)
            gharardad.setShomare(gharardadService.createShomareGharardad(gharardad.getNamayande().getKodeNamayandeKargozar(), String.valueOf(DateUtil.extractYear(DateUtil.getCurrentDate())).substring(2)));
        else gharardad.setShomare(ghabli.getShomare());
        gharardad.setShahr(constantItemsService.findCityById(gharardad.getShahr().getCityId()));
        gharardad.setOstan(constantItemsService.findCityById(gharardad.getOstan().getCityId()));
        if (uploadFile != null) {
            if (ghabli != null && ghabli.getFileId() != null)
                fileService.deleteFile(gharardad.getFileId());
            gharardad.setFileId(fileService.createFile(gharardad.getNamayande().getKodeNamayandeKargozar() + "-" + String.valueOf(DateUtil.extractYear(DateUtil.getCurrentDate())), uploadFileFileName, uploadFile));
        }
        if (uploadFileFish != null) {
            if (ghabli != null && ghabli.getFishFileId() != null)
                fileService.deleteFile(gharardad.getFishFileId());
            gharardad.setFishFileId(fileService.createFile(gharardad.getNamayande().getKodeNamayandeKargozar() + "-" + String.valueOf(DateUtil.extractYear(DateUtil.getCurrentDate())), uploadFileFishFileName, uploadFileFish));
        }
        if (gharardad.getTarh() != null && gharardad.getTarh().getId() != null) {
            gharardad.setTarh(constantsService.findTarhById(gharardad.getTarh().getId()));
        } else {
            gharardad.setTarh(null);
        }

        gharardadService.sabtGharardad(gharardad, tozih, user);
        addActionMessage("قرارداد با موفقیت ثبت گردید.");
        return SUCCESS;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }


    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public File getUploadFileFish() {
        return uploadFileFish;
    }

    public void setUploadFileFish(File uploadFileFish) {
        this.uploadFileFish = uploadFileFish;
    }


    public String getTozih() {
        return tozih;
    }

    public void setTozih(String tozih) {
        this.tozih = tozih;
    }

    public String uploadFileBimeGozar() {
        showGharardad();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null)
            user = loginService.findUserByUsername(username);
        if (user == null)
            return ERROR;
        List<Pishnehad> pishnehadList = new LinkedList<Pishnehad>();
        Boolean error = false;
        try {
            final Gharardad gh = gharardad;
            Workbook book = Workbook.getWorkbook(uploadFile);
            Sheet sheet = book.getSheet(0);

            for (int i = 1; i < sheet.getRows(); i++) {
                try {
                    Cell name = sheet.getCell(0, i);
                    Cell nameKhanevadegi = sheet.getCell(1, i);
                    Cell namePedar = sheet.getCell(2, i);
                    Cell pishvand = sheet.getCell(3, i);
                    Cell jensiat = sheet.getCell(4, i);
                    Cell vaziatTaahol = sheet.getCell(5, i);
                    Cell shenasname = sheet.getCell(6, i);
                    Cell mahalleSodur = sheet.getCell(7, i);
                    Cell tarikhTavallod = sheet.getCell(8, i);
                    Cell mahalleTavalod = sheet.getCell(9, i);
                    Cell kodeMelli = sheet.getCell(10, i);
                    Cell shoghlAsli = sheet.getCell(11, i);
                    Cell shoghlFari = sheet.getCell(12, i);
                    Cell ostanMahallSokunat = sheet.getCell(13, i);
                    Cell shahrMahallSokunat = sheet.getCell(14, i);
                    Cell neshaniManzel = sheet.getCell(15, i);
                    Cell kodePostiManzel = sheet.getCell(16, i);
                    Cell pishShomareManzel = sheet.getCell(17, i);
                    Cell tellManzel = sheet.getCell(18, i);
                    Cell hamrah = sheet.getCell(19, i);
                    Cell ostanMahallKar = sheet.getCell(20, i);
                    Cell shahrMahallKar = sheet.getCell(21, i);
                    Cell neshaniMahallKar = sheet.getCell(22, i);
                    Cell kodePostiKar = sheet.getCell(23, i);
                    Cell pishShomareKar = sheet.getCell(24, i);
                    Cell tellKar = sheet.getCell(25, i);
                    Cell daramadMahiane = sheet.getCell(26, i);
                    Cell postElectronic = sheet.getCell(27, i);
                    if (!StringUtil.validCodeMelli(kodeMelli.getContents())) {
                        error = true;
                        addActionMessage("کد ملی وارد شده در ردیف " + String.valueOf(i + 1) + " نادرست می باشد.");
                        continue;
                    }
                    Shakhs shakhs = shakhsService.searchShakhs(kodeMelli.getContents(),false);
//If shakhs is exists, should add the pishnehad still, so commented
//                    if (shakhs != null) {
//                        error = true;
//                        addActionMessage("شخص در ردیف " + String.valueOf(i + 1) + " قبلا در سیستم ایجاد شده است.");
//                        continue;
//                    }

                    BimeGozar bimegozar = null;
                    BimeShode bimeshode = null;
                    if (shakhs == null) {
                        shakhs = new Shakhs();
                        shakhs.setName(name.getContents());
                        shakhs.setNameKhanevadegi(nameKhanevadegi.getContents());
                        shakhs.setNamePedar(namePedar.getContents());
                        shakhs.setPishvand(pishvand.getContents());
                        shakhs.setIraniOrAtbaeKhareji(Shakhs.Melliat.IRANI);
                        shakhs.setKodeMelliShenasayi(kodeMelli.getContents());
                        shakhs.setShomareShenasnameh(shenasname.getContents());
                        shakhs.setMahalleSodoreShenasnameh(constantItemsService.findCityByName(mahalleSodur.getContents()).get(0));
                        shakhs.setMahalleTavallod(constantItemsService.findCityByName(mahalleTavalod.getContents()).get(0));
                        shakhs.setTarikheTavallod(tarikhTavallod.getContents());
                        shakhs.setVaziateTaahol(vaziatTaahol.getContents());
                        shakhs.setJensiat(jensiat.getContents());
                        shakhs.setShoghleAsli(shoghlAsli.getContents());
                        shakhs.setShoghleFarei(shoghlFari.getContents());
                        shakhs.setType(Shakhs.BimeGozarType.HAGHIGHI);
                        shakhsService.addNewShakhs(shakhs);
                    }
                    if (bimegozar == null) {
                        bimegozar = new BimeGozar();

                        bimegozar.setShakhs(shakhs);
                        bimegozar.setDaramadeMahiane(Long.parseLong(daramadMahiane.getContents().replace(",", "").trim()));
                        bimegozar.setNesbatBabimeShode("خود شخص");
                    }
                    Address address = new Address(constantItemsService.findCityByName(ostanMahallSokunat.getContents()).get(0), constantItemsService.findCityByName(shahrMahallSokunat.getContents()).get(0), neshaniManzel.getContents(), kodePostiManzel.getContents(), pishShomareManzel.getContents(), tellManzel.getContents(), hamrah.getContents()
                            , constantItemsService.findCityByName(ostanMahallKar.getContents()).get(0), constantItemsService.findCityByName(shahrMahallKar.getContents()).get(0), neshaniMahallKar.getContents(), kodePostiKar.getContents(), pishShomareKar.getContents(), tellKar.getContents(), postElectronic.getContents());

                    Pishnehad pishnehad = new Pishnehad();
                    pishnehad.setAddressBimeGozar(address);
                    pishnehad.setBimeGozar(bimegozar);
                    pishnehad.setBimeShode(bimeshode);
                    pishnehad.setNamayande(gh.getNamayande());
                    Set<Role> allTheRoles = user.getRoles();
                    for (Role allTheRole : allTheRoles) {
                        if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_SODOUR)) {
                            pishnehad.setProcessor(Constant.ROLE_KARSHENAS_SODOUR);
                        } else if (allTheRole.getRoleName().equalsIgnoreCase(Constant.ROLE_KARSHENAS_MASOUL)) {
                            pishnehad.setProcessor(Constant.ROLE_KARSHENAS_MASOUL);
                        }

                    }
                    pishnehad.setUserCreator(user);
                    pishnehad.setGharardad(gh);
                    pishnehad.setTarh(gh.getTarh());
                    String tarikh = DateUtil.getCurrentDate();
                    Estelam estelam = estelamService.adapte(new Estelam());
                    estelamService.addNewEstelam(estelam);
                    Zamaem zamaem = new Zamaem();
                    int zamId = pishnehadService.saveZamaem(zamaem);
                    Zamaem theZamime = pishnehadService.findZamaemById(zamId);
                    pishnehad.setEstelam(estelam);
                    estelam.setPishnehad(pishnehad);
                    pishnehad.setZamaem(theZamime);
                    pishnehad.setValid(true);
                    pishnehad.setEstelam(estelam);
                    pishnehad.setAddressBimeShode(new Address());
                    pishnehadList.add(pishnehad);
                } catch (Exception ex) {
                    error = true;
                    addActionMessage("خطا در ردیف " + String.valueOf(i + 1) + " در فایل اکسل");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionMessage("فایل آپلود شده منطبق با فرمت درخواستی نمی باشد.");
            return SUCCESS;
        }
        try {
            if (!error) {
                for (Pishnehad pishnehad : pishnehadList) {
                    pishnehadService.saveNewPishnehad(pishnehad);
                    transitionLogService.logCreation(user, pishnehad, Constant.PISHNEHAD_CREATION_LOG_MESSAGE);
                }
            } else return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            addActionMessage("خطا در ذخیره پیشنهادات." + ex.getMessage());
        }
        return SUCCESS;
    }

    public List<Integer> getCheckBoxPishnehad() {
        return checkBoxPishnehad;
    }

    public void setCheckBoxPishnehad(List<Integer> checkBoxPishnehad) {
        this.checkBoxPishnehad = checkBoxPishnehad;
    }

    public String hazfPishnehad() {
        boolean shouldDelete = true;
        if (checkBoxSelectAllPishnehad != null) {
            showGharardad();
            for (Pishnehad pishnehad : gharardad.getPishnehadList()) {
                pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                if (!pishnehad.getState().getId().equals(245))
                    shouldDelete = false;
            }
            if(shouldDelete)
            {
                for (Pishnehad pishnehad : gharardad.getPishnehadList()) {
                    pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                    if (pishnehad.getState().getId().equals(245))
                    {
                        try{
                        pishnehadService.deletePishnehad(pishnehad.getId());
                        } catch(Exception ex) {}
                    }
                }
            }
            else
            {
                addActionMessage("پيشنهاد(هاي) انتخاب شده در سيستم قابل حذف نمي باشند.");
            }
        } else if (checkBoxPishnehad != null) {
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                if (!pishnehad.getState().getId().equals(245))
                    shouldDelete = false;
            }
            if(shouldDelete)
            {
                for (Integer i : checkBoxPishnehad) {
                    Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                    if (pishnehad.getState().getId().equals(245))
                        System.out.println(i);
                        pishnehadService.deletePishnehad(i);
                }
            }
            else
            {
                addActionMessage("پيشنهاد(هاي) انتخاب شده در سيستم قابل حذف نمي باشند.");
            }
        } else {
            addActionMessage("هیچ پیشنهادی انتخاب نشده است");
        }
        return showGharardad();
    }

    public String ersalNahaiPishnehad() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null)
            user = loginService.findUserByUsername(username);
        if (user == null || gharardad == null) return ERROR;
        gharardad = gharardadService.findById(gharardad.getId());
        String namoavafagh = "";
        for (Pishnehad p : gharardad.getPishnehadList()) {
            if (p.getState().getId() < 20)
                namoavafagh += p.getRadif() + " ";
            else pishnehadService.transitPishnehad(user.getId(), String.valueOf(p.getId()), "2", "");
        }
        if (!namoavafagh.isEmpty())
            addActionError("پیشنهاد های زیر هنوز تایید نشده اند " + namoavafagh);
        return showGharardad();
    }

    public String nahayeeShodJami() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        showGharardad();
        List<Pishnehad> processList;
        if (checkBoxSelectAllPishnehad != null) {
            processList = gharardad.getPishnehadList();
        } else if (checkBoxPishnehad != null) {
            processList = new LinkedList<Pishnehad>();
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                processList.add(pishnehad);
            }
        } else {
            addActionMessage("هیچ پیشنهادی انتخاب نشده است");
            return SUCCESS;
        }
        for (Pishnehad pishnehad : processList) {
            if (pishnehad.getBimename() != null) {
                pishnehadService.transitPishnehad(user.getId(), String.valueOf(pishnehad.getId()), "41", "تغییر وضعیت گروهی");
            }
        }
        addActionMessage("تغییر وضعیت با موفقیت انجام شد.");
        return SUCCESS;
    }

    public String ersalShodSodurJami() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        showGharardad();
        List<Pishnehad> processList;
        if (checkBoxSelectAllPishnehad != null) {
            processList = gharardad.getPishnehadList();
        } else if (checkBoxPishnehad != null) {
            processList = new LinkedList<Pishnehad>();
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                processList.add(pishnehad);
            }
        } else {
            addActionMessage("هیچ پیشنهادی انتخاب نشده است");
            return SUCCESS;
        }
        for (Pishnehad pishnehad : processList) {
            if (pishnehad.getBimename() != null) {
                pishnehadService.transitPishnehad(user.getId(), String.valueOf(pishnehad.getId()), "39", "تغییر وضعیت گروهی");
            }
        }
        addActionMessage("تغییر وضعیت با موفقیت انجام شد.");
        return SUCCESS;
    }

    public String erjaPezeshkPishnehad() {
        final State state = constantItemsService.findStateById(160);
        if (checkBoxPishnehad != null)
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                pishnehad.setState(state);
                pishnehadService.updatePishnehad(pishnehad);
            }
        return showGharardad();
    }

    public IShakhsService getShakhsService() {
        return shakhsService;
    }

    public void setShakhsService(IShakhsService shakhsService) {
        this.shakhsService = shakhsService;
    }

    public List<PishnehadReport> getPishnehadReportList() {
        return pishnehadReportList;
    }

    public void setPishnehadReportList(List<PishnehadReport> pishnehadReportList) {
        this.pishnehadReportList = pishnehadReportList;
    }

    public Map<String,List<Long>> getSerialList(List<Serial> serialList, long startSerial, int size, Namayande vahedSodor)
    {
        final String NOT_DEPEND="notDepend";
        final String NOT_DEFINED="notDefind";
        final String ALREADY_USED="alreadyUsed";
        final String DEACTIVE="deactive";
        final String CANCELED="canceled";

        Map<String,List<Long>> rtnMap=new HashMap<String, List<Long>>();
        Long[] serials = {startSerial-1};

        for (int i = 0; i < size; i++)
        {
            serials[0] = serials[0] + 1;
            List<Serial> tempList = new ArrayList<Serial>();
            tempList=pishnehadService.findSerialByShomareSerial(serials);
            if (tempList.size() == 0)
            {
    //                ("شماره سریال در سیستم تعریف نشده است");
                List<Long> tempNotDefined = new ArrayList<Long>();
                if(rtnMap.containsKey(NOT_DEFINED))
                {
                    tempNotDefined = rtnMap.get(NOT_DEFINED);
                    rtnMap.remove(NOT_DEFINED);
                }
                tempNotDefined.add(serials[0]);
                rtnMap.put(NOT_DEFINED,tempNotDefined);
            }
            else
            {
//                String username = SecurityContextHolder.getContext().getAuthentication().getName();
//                Namayande vahedSodor = new Namayande();
                boolean isSetad = false;
//                if (loginService.findUserByUsername(username).getMojtamaSodoor() != null)
//                {
//                    vahedSodor = loginService.findUserByUsername(username).getMojtamaSodoor();
                    if (vahedSodor.getKodeNamayandeKargozar().equals("111116"))
                        isSetad = true;
//                }
//                else
//                {
//                    vahedSodor = loginService.findUserByUsername(username).getNamayandegi().getVahedSodur();
//                }
                if (tempList.get(0).getDasteSerial().getVahedeSodur() != vahedSodor && !isSetad)
                {
//                    ("شماره سریال متعلق به واحد صدور شما نمی باشد");
                    List<Long> tempNotDepend = new ArrayList<Long>();
                    if (rtnMap.containsKey(NOT_DEPEND))
                    {
                        tempNotDepend = rtnMap.get(NOT_DEPEND);
                        rtnMap.remove(NOT_DEPEND);
                    }
                    tempNotDepend.add(serials[0]);
                    rtnMap.put(NOT_DEPEND, tempNotDepend);
                }
                else
                {
                    if (tempList.get(0).getBimename() != null && tempList.get(0).getBimename() != tempList.get(0).getBimename())
                    {
//                        ("شماره سریال قبلاً استفاده شده است");
                        List<Long> tempAlreadyUsed = new ArrayList<Long>();
                        if (rtnMap.containsKey(ALREADY_USED))
                        {
                            tempAlreadyUsed= rtnMap.get(ALREADY_USED);
                            rtnMap.remove(ALREADY_USED);
                        }
                        tempAlreadyUsed.add(serials[0]);
                        rtnMap.put(ALREADY_USED, tempAlreadyUsed);
                    }
                    else if (tempList.get(0).getDasteSerial().getVaziateDaste() == DasteSerial.VaziateDaste.GHEYRE_FAAL)
                    {
//                        ("شماره سریال غیرفعال است");
                        List<Long> tempDeactive = new ArrayList<Long>();
                        if (rtnMap.containsKey(DEACTIVE))
                        {
                            tempDeactive = rtnMap.get(DEACTIVE);
                            rtnMap.remove(DEACTIVE);
                        }
                        tempDeactive.add(serials[0]);
                        rtnMap.put(DEACTIVE, tempDeactive);
                    }
                    else if (tempList.get(0).getVaziatSerial() == Serial.VaziatSerial.EBTAL_SHODE)
                    {
//                        ("شماره سریال ابطال شده است");
                        List<Long> tempCanceled = new ArrayList<Long>();
                        if (rtnMap.containsKey(CANCELED))
                        {
                            tempCanceled = rtnMap.get(CANCELED);
                            rtnMap.remove(NOT_DEPEND);
                        }
                        tempCanceled.add(serials[0]);
                        rtnMap.put(CANCELED, tempCanceled);
                    }
                    else
                    {
                        serialList.addAll(tempList);
                    }
                }
            }

        }
        return  rtnMap;
    }


    public String printBimenameList() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = loginService.findUserByUsername(username);
            pishnehadReportList = new LinkedList<PishnehadReport>();
            gharardad = gharardadService.findById(gharardad.getId());
            long curSerial = serialStart;

            if (checkBoxSelectAllBimename != null) {
                List<Bimename> bimenameList = pishnehadService.findAllBimenameByGharardadIdNonePaginated(gharardad.getId());
                List<Serial> serialList = new ArrayList<Serial>();
                Map<String, List<Long>> mapErr = new HashMap<String, List<Long>>();
                mapErr=getSerialList(serialList,serialStart, bimenameList.size(),user.getMojtamaSodoor()!=null?user.getMojtamaSodoor():user.getNamayandegi().getVahedSodur());
                if(mapErr.size()>0) return ERROR;//sholud be change
                int i=0 ;
                for (Bimename bimename : bimenameList) {
                    bimename = pishnehadService.findBimenameById(bimename.getId());
                    PishnehadReport p = new PishnehadReport();
                    bimename.setShomareSerialePrint(String.valueOf(curSerial++));
                    bimename.setSerialPrint(serialList.get(i));
                    serialList.get(i).setBimename(bimename);
                    pishnehadService.updateBimename(bimename);
                    pishnehadService.saveOrUpdateSerials(serialList.subList(i, i));
                    p.setPishnehad(bimename.getPishnehad());
                    p.setHaghBimeAmraz(bimename.getGhestBandiList().get(0).getHaghBimePoosheshAmraz());
                    p.setHaghBimeHadese(bimename.getGhestBandiList().get(0).getHaghBimePoosheshHadese());
                    p.setHaghBimeMoafiat(bimename.getGhestBandiList().get(0).getHaghBimePoosheshMoafiat());
                    p.setHaghBimeNaghsOzv(bimename.getGhestBandiList().get(0).getHaghBimePoosheshNaghsOzv());
                    asnadeSodorService.getMabaleghForChapp(bimename.getPishnehad(), DateUtil.getCurrentDate(), 0, true, p);
                    p.setGhestList(bimename.getGhestBandiList().get(0).getGhestList());
                    p.setMaliatAfzoade(((Long) (((Double) (AsnadeSodorService.getDarsadMaliat(bimename.getTarikhSodour(), bimename.getPishnehad().getTarh()) * 100)).longValue())).toString());
//                    pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "94", "تغییر وضعیت گروهی");
                    pishnehadReportList.add(p);
                    i++;
                }
            } else if (checkBoxBimename != null) {
                List<Serial> serialList = new ArrayList<Serial>();
                Map<String, List<Long>> mapErr = new HashMap<String, List<Long>>();
                mapErr = getSerialList(serialList, serialStart, checkBoxBimename.size(), user.getMojtamaSodoor() != null ? user.getMojtamaSodoor() : user.getNamayandegi().getVahedSodur());
                if (mapErr.size() > 0) return ERROR;//sholud be change
                int j=0;
                for (Integer i : checkBoxBimename) {
                    Bimename bimename = pishnehadService.findBimenameById(i);
                    PishnehadReport p = new PishnehadReport();
                    bimename.setShomareSerialePrint(String.valueOf(curSerial++));
                    bimename.setSerialPrint(serialList.get(j));
                    serialList.get(j).setBimename(bimename);
                    pishnehadService.updateBimename(bimename);
                    pishnehadService.saveOrUpdateSerials(serialList.subList(j, j));
//                    bimename.getPishnehad().getAddressBimeShode().setTelephoneHamrah("");
//                    bimename.getPishnehad().getAddressBimeShode().setTelephoneMahalleKar("");
//                    bimename.getPishnehad().getAddressBimeShode().setCodeTelephoneMahalleKar("");
//                    bimename.getPishnehad().getAddressBimeShode().setCodeTelephoneManzel("");
//                    bimename.getPishnehad().getAddressBimeShode().setTelephoneManzel("");
//                    bimename.getPishnehad().getAddressBimeGozar().setNeshaniManzel(bimename.getPishnehad().getAddressBimeGozar().getNeshaniMahalleKar());
                    p.setPishnehad(bimename.getPishnehad());
                    p.setHaghBimeAmraz(bimename.getGhestBandiList().get(0).getHaghBimePoosheshAmraz());
                    p.setHaghBimeHadese(bimename.getGhestBandiList().get(0).getHaghBimePoosheshHadese());
                    p.setHaghBimeMoafiat(bimename.getGhestBandiList().get(0).getHaghBimePoosheshMoafiat());
                    p.setHaghBimeNaghsOzv(bimename.getGhestBandiList().get(0).getHaghBimePoosheshNaghsOzv());
                    asnadeSodorService.getMabaleghForChapp(bimename.getPishnehad(), DateUtil.getCurrentDate(), 0, true, p);
                    p.setGhestList(bimename.getGhestBandiList().get(0).getGhestList());
                    p.setMaliatAfzoade(((Long) (((Double) (AsnadeSodorService.getDarsadMaliat(bimename.getTarikhSodour(), bimename.getPishnehad().getTarh()) * 100)).longValue())).toString());
//                    pishnehadService.transitPishnehad(user.getId(), String.valueOf(bimename.getPishnehad().getId()), "94", "تغییر وضعیت گروهی");
                    pishnehadReportList.add(p);
                    j++;
                }
            } else {
                return ERROR;
            }
            if (gharardad != null && gharardad.getId() == 48549470) {
                sourceFilePath = "report/bimename/bimenameChappi_emdad.jrxml";
            }
            destFileDIR = realPath + destFileDIR + "bimenameChappi.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jrxml", realPath + "report/bimename_estefadeKonandeganAzSarmayeBime.jasper");
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

    public String printZamimeBimenameList() {

        try {
            pishnehadReportList = new LinkedList<PishnehadReport>();
            gharardad = gharardadService.findById(gharardad.getId());
            for (Pishnehad pish : gharardad.getPishnehadList()) {
                PishnehadReport p = new PishnehadReport();
                p.setPishnehad(pish);
                pishnehadReportList.add(p);
            }
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

    public String printMohasebateRiaziList() throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        try {
            pishnehadReportList = new LinkedList<PishnehadReport>();
            gharardad = gharardadService.findById(gharardad.getId());
            if (checkBoxSelectAllBimename != null) {
                List<Bimename> bimenameList = pishnehadService.findAllBimenameByGharardadIdNonePaginated(gharardad.getId());
                for (Bimename bimename : bimenameList) {
                    bimename = pishnehadService.findBimenameById(bimename.getId());
                    PishnehadReport p = new PishnehadReport();
                    p.setPishnehad(bimename.getPishnehad());
                    p.setCurrentElhaghiye(null);
                    p.setFRsList(new ArrayList<FRs>());
                    PrintAction.calculate(p, Integer.parseInt(p.getPishnehad().getEstelam().getModat_bimename()));
                    pishnehadReportList.add(p);
                }
            } else if (checkBoxBimename != null) {
                for (Integer i : checkBoxBimename) {
                    Bimename bimename = pishnehadService.findBimenameById(i);
                    PishnehadReport p = new PishnehadReport();
                    p.setPishnehad(bimename.getPishnehad());
                    p.setCurrentElhaghiye(null);
                    p.setFRsList(new ArrayList<FRs>());
                    PrintAction.calculate(p, Integer.parseInt(p.getPishnehad().getEstelam().getModat_bimename()));
                    pishnehadReportList.add(p);
                }
            }
            else {
                return ERROR;
            }
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

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    private String majmuHaghBimehayePaye;

    public String printMohasebePishPardakht() {
        try {
            pishnehadReportList = new LinkedList<PishnehadReport>();
            PishnehadReport pishnehadReport = new PishnehadReport();
            pishnehadReport.setPishPardakhtReportList(new LinkedList<PishPardakhtReport>());
            gharardad = gharardadService.findById(gharardad.getId());
            Long majmuHaghBimehayePayeL = 0L;
            List<Pishnehad> processList;
        if (checkBoxSelectAllPishnehad != null) {
            processList = gharardad.getPishnehadList();
        } else if (checkBoxPishnehad != null) {
            processList = new LinkedList<Pishnehad>();
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                processList.add(pishnehad);
            }
        } else {
            processList = gharardad.getPishnehadList();
        }
            for (Pishnehad pish : processList) {
                try {
                    PishPardakhtReport pr = new PishPardakhtReport();
                    pr.setHaghBime(pish.getEstelam().getHagh_bime_pardakhti());
                    majmuHaghBimehayePayeL += Long.parseLong(pish.getEstelam().getHagh_bime_pardakhti().replace(",", "").trim());
                    pr.setName(pish.getBimeGozar().getShakhs().getFullName());
                    pr.setNameBimeshode(pish.getBimeShode().getShakhs().getFullName());
                    pr.setKodeMelli(pish.getBimeGozar().getShakhs().getKodeMelliShenasayi());
                    pr.setShenasame(pish.getBimeGozar().getShakhs().getShomareShenasnameh());
                    pishnehadReport.getPishPardakhtReportList().add(pr);
                } catch (Exception ignore) {
                }
            }
            majmuHaghBimehayePaye = NumberFormat.getNumberInstance().format(majmuHaghBimehayePayeL);
            pishnehadReportList.add(pishnehadReport);
            destFileDIR = realPath + destFileDIR + "mohasebePishPardakht.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/mohasebePishPardakht_subreport.jrxml", realPath + "report/mohasebePishPardakht_subreport.jasper");
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

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public String getMajmuHaghBimehayePaye() {
        return majmuHaghBimehayePaye;
    }

    public void setMajmuHaghBimehayePaye(String majmuHaghBimehayePaye) {
        this.majmuHaghBimehayePaye = majmuHaghBimehayePaye;
    }

    public Map<City, List<User>> getKarshenasha() {
        if (karshenasha == null) {
            karshenasha = new LinkedHashMap<City, List<User>>();
            List<City> ostanha = constantItemsService.getOstans();
            for (City ostan : ostanha) {
                List<User> users = pishnehadService.findAllKarshenasForPishnehadsByCity(ostan);
                karshenasha.put(ostan, users);
            }
        }
        return karshenasha;
    }

    public void setKarshenasha(Map<City, List<User>> karshenasha) {
        this.karshenasha = karshenasha;
    }

    public Long getKarshenasId() {
        return karshenasId;
    }

    public void setKarshenasId(Long karshenasId) {
        this.karshenasId = karshenasId;
    }

    public String takhsisKarshenas() {
        if (checkBoxPishnehad != null)
            if (karshenasId != null) {
                User karshenas = userService.getUserById(karshenasId);
                for (Integer i : checkBoxPishnehad) {
                    Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                    pishnehad.setKarshenas(karshenas);
                    pishnehadService.updatePishnehad(pishnehad);
                }
            }
        return showGharardad();
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    public String getUploadFileFishFileName() {
        return uploadFileFishFileName;
    }

    public void setUploadFileFishFileName(String uploadFileFishFileName) {
        this.uploadFileFishFileName = uploadFileFishFileName;
    }

    public IEstelamService getEstelamService() {
        return estelamService;
    }

    public void setEstelamService(IEstelamService estelamService) {
        this.estelamService = estelamService;
    }

    public ITransitionLogService getTransitionLogService() {
        return transitionLogService;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public EmzaKonande getEmzaKonandeAval() {
        return emzaKonandeAval;
    }

    public void setEmzaKonandeAval(EmzaKonande emzaKonandeAval) {
        this.emzaKonandeAval = emzaKonandeAval;
    }

    public EmzaKonande getEmzaKonandeDovom() {
        return emzaKonandeDovom;
    }

    public void setEmzaKonandeDovom(EmzaKonande emzaKonandeDovom) {
        this.emzaKonandeDovom = emzaKonandeDovom;
    }

    public String getTarikhSodur() {
        return tarikhSodur;
    }

    public void setTarikhSodur(String tarikhSodur) {
        this.tarikhSodur = tarikhSodur;
    }

    public String getTarikhShoru() {
        return tarikhShoru;
    }

    public void setTarikhShoru(String tarikhShoru) {
        this.tarikhShoru = tarikhShoru;
    }

    public String sodurJami() throws BimeNaamehVaSarmayehGozari.CustomValidationException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        showGharardad();
        List<String> existBimenameError = new LinkedList<String>();
        if (checkBoxSelectAllPishnehad != null) {
            for (Pishnehad pishnehad : gharardad.getPishnehadList()) {
                pishnehad = pishnehadService.loadPishnehadById(pishnehad.getId());
                if (pishnehad.getBimename() == null) {
                    Bimename bimename = new Bimename();
                    bimename.setState(constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE));
                    tarikhShoru=DateUtil.getCurrentDate();
                    tarikhSodur=tarikhShoru;
                    bimename.setTarikhShorou(tarikhShoru);
                    bimename.setTarikhSodour(tarikhSodur);
                    bimename.setTarikhEngheza(DateUtil.addYear(tarikhShoru, Integer.parseInt(pishnehad.getEstelam().getModat_bimename())));
                    pishnehad.setBimename(bimename);
                    bimename.setPrimitivePishnehad(pishnehad);
                    bimename.setShomare(sequenceService.nextShomareBimename(user.getMojtamaSodoor()));
                    bimename.setSerial(sequenceService.nextShomareSerialeBimename(user.getMojtamaSodoor()));
                    bimename.setVahedSodor(user.getMojtamaSodoor());
                    int bimenameId = pishnehadService.saveBimename(bimename);
                    bimename.setReadyToShow("yes");
                    pishnehadService.updateBimename(bimename);
                    Bimename theBime = pishnehadService.findBimenameById(bimenameId);
                    theBime.setEmzaKonandeAval(clinicService.findEmzaKonandeById(emzaKonandeAval.getId()));
                    theBime.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(emzaKonandeDovom.getId()));
                    pishnehad.setBimename(theBime);
                    pishnehadService.updateBimename(theBime);
                    pishnehadService.transitPishnehad(user.getId(), String.valueOf(pishnehad.getId()), "93", "تغییر وضعیت گروهی");
                    pishnehadService.updatePishnehad(pishnehad);
                    asnadeSodorService.refreshObject(pishnehad);
                    if (user != null)
                        asnadeSodorService.saveGhestbandi(pishnehad, 0, user);


                } else {
                    existBimenameError.add(pishnehad.getRadif());
                }
            }

            if (existBimenameError.size() > 0) {
                String errStr = "";
                int cnt = 0;
                for (String radif : existBimenameError) {
                    if (cnt++ != 0)
                        errStr += "، ";
                    errStr += radif;
                }

                addActionMessage("برای پیشنهاد(های) " + errStr + " قبلا بیمه نامه صادر شده است.");
            }
            if (pishnehadPaginatedList.getFullListSize()  - existBimenameError.size() > 0)
                addActionMessage("تعداد " + (pishnehadPaginatedList.getFullListSize()  - existBimenameError.size()) + " بیمه نامه برای پیشنهاد(های) انتخاب شده صادر شد.");
        } else if (checkBoxPishnehad != null) {
            for (Integer i : checkBoxPishnehad) {
                Pishnehad pishnehad = pishnehadService.loadPishnehadById(i);
                if (pishnehad.getBimename() == null) {
                    Bimename bimename = new Bimename();
                    bimename.setState(constantItemsService.findStateById(Constant.BIMENAME_INITIAL_STATE));
                    bimename.setTarikhShorou(tarikhShoru);
                    bimename.setTarikhSodour(tarikhSodur);
                    bimename.setTarikhEngheza(DateUtil.addYear(tarikhShoru, Integer.parseInt(pishnehad.getEstelam().getModat_bimename())));
                    pishnehad.setBimename(bimename);
                    bimename.setPrimitivePishnehad(pishnehad);
                    bimename.setShomare(sequenceService.nextShomareBimename(user.getMojtamaSodoor()));
                    bimename.setSerial(sequenceService.nextShomareSerialeBimename(user.getMojtamaSodoor()));
                    bimename.setVahedSodor(user.getMojtamaSodoor());
                    int bimenameId = pishnehadService.saveBimename(bimename);
                    bimename.setReadyToShow("yes");
                    pishnehadService.updateBimename(bimename);
                    Bimename theBime = pishnehadService.findBimenameById(bimenameId);
                    theBime.setEmzaKonandeAval(clinicService.findEmzaKonandeById(emzaKonandeAval.getId()));
                    theBime.setEmzaKonandeDovom(clinicService.findEmzaKonandeById(emzaKonandeDovom.getId()));

                    pishnehad.setBimename(theBime);
                    List<Pishnehad> pishnehadList= theBime.getPishnehadList() == null?new ArrayList<Pishnehad>(): theBime.getPishnehadList();
                    pishnehadList.add(pishnehad);

                    pishnehadList.add(pishnehad);
                    theBime.setPishnehadList(pishnehadList);
                    pishnehadService.updateBimename(theBime);
                    pishnehadService.updatePishnehad(pishnehad);
                    pishnehadService.transitPishnehad(user.getId(), String.valueOf(pishnehad.getId()), "93", "تغییر وضعیت گروهی");
                    if (user != null)
                        asnadeSodorService.saveGhestbandi(pishnehad, 0, user);

                } else {
                    existBimenameError.add(pishnehad.getRadif());
                }
            }

            if (existBimenameError.size() > 0) {
                String errStr = "";
                int cnt = 0;
                for (String radif : existBimenameError) {
                    if (cnt++ != 0)
                        errStr += "، ";
                    errStr += radif;
                }

                addActionMessage("برای پیشنهاد(های) " + errStr + " قبلا بیمه نامه صادر شده است.");
            }
            if (checkBoxPishnehad.size()  - existBimenameError.size() > 0)
                addActionMessage("تعداد " + (checkBoxPishnehad.size()  - existBimenameError.size()) + " بیمه نامه برای پیشنهاد(های) انتخاب شده صادر شد.");
        } else {
            addActionMessage("هیچ پیشنهادی انتخاب نشده است");
        }

        return SUCCESS;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public String printBimenameBaAghsat() {
        try {
            pishnehadReportList = new LinkedList<PishnehadReport>();
            PishnehadReport pishnehadReport = new PishnehadReport();
            pishnehadReport.setPishPardakhtReportList(new LinkedList<PishPardakhtReport>());
            gharardad = gharardadService.findById(gharardad.getId());
            Long majmuHaghBimehayePayeL = 0L;
            for (Pishnehad pish : gharardad.getPishnehadList()) {
                try {
                    if (pish.getBimename() != null) {
                        PishnehadReport pr = new PishnehadReport();
                        pr.setPishnehad(pish);
                        pishnehadReportList.add(pr);
                    }
                } catch (Exception ignore) {
                }
            }
            if (pishnehadReportList.size() == 0)
                pishnehadReportList.add(new PishnehadReport());
            destFileDIR = realPath + destFileDIR + "gharardadBimenameBaAghsat.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/gharardadBimenameBaAghsat_subreport.jrxml", realPath + "report/gharardadBimenameBaAghsat_subreport.jasper");
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

    public String getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public List<Integer> getCheckBoxBimename() {
        return checkBoxBimename;
    }

    public void setCheckBoxBimename(List<Integer> checkBoxBimename) {
        this.checkBoxBimename = checkBoxBimename;
    }

    public PaginatedListImpl<Pishnehad> getPishnehadPaginatedList() {
        return pishnehadPaginatedList;
    }

    public void setPishnehadPaginatedList(PaginatedListImpl<Pishnehad> pishnehadPaginatedList) {
        this.pishnehadPaginatedList = pishnehadPaginatedList;
    }

    public PaginatedListImpl<Bimename> getBimenamePaginatedList() {
        return bimenamePaginatedList;
    }

    public void setBimenamePaginatedList(PaginatedListImpl<Bimename> bimenamePaginatedList) {
        this.bimenamePaginatedList = bimenamePaginatedList;
    }

    public Long getSerialStart() {
        return serialStart;
    }

    public void setSerialStart(Long serialStart) {
        this.serialStart = serialStart;
    }

    public String getCheckBoxSelectAllPishnehad() {
        return checkBoxSelectAllPishnehad;
    }

    public void setCheckBoxSelectAllPishnehad(String checkBoxSelectAllPishnehad) {
        this.checkBoxSelectAllPishnehad = checkBoxSelectAllPishnehad;
    }

    public String getCheckBoxSelectAllBimename() {
        return checkBoxSelectAllBimename;
    }

    public void setCheckBoxSelectAllBimename(String checkBoxSelectAllBimename) {
        this.checkBoxSelectAllBimename = checkBoxSelectAllBimename;
    }
}
