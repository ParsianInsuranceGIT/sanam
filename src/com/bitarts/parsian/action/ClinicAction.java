package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.management.EmzaKonande;
import com.bitarts.parsian.model.pishnahad.Moarefiname;
import com.bitarts.parsian.service.IClinicService;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.*;

//import net.sf.jasperreports.fonts.

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: Feb 16, 2011
 * Time: 4:25:50 PM
 */

public class ClinicAction extends BaseAction implements ServletContextAware {

    private IClinicService clinicService;
    private ILoginService loginService;
    private List<Clinic> clinics;
    private List<Azmayesh> azmayeshat;
    private List<AzmayeshType> azmayeshTypes;
    private AzmayeshName azmayeshName;
    private List<AzmayeshName> azmayeshNames;
    private String azmayeshNameTypeYadak;
    private Integer sumup;
    private String duplicate;
    private List<User> users;
    private EmzaKonande emzaKonande;
    private User user;
    private PaginatedListImpl<EmzaKonande> emzaKonandegan;
    private static final int SAYER_CLINIC_ID = 111111;
    private Map<String, String> errorsMap;
    private Integer uiStatus, roleId, namayandeId, viSodurId;
    private String viSodurName, namayandeName;
    private String fname, lname, code, azEtebar, taEtebar;
    private List<Role> roleList;
    private IUserService userService;
    private String emzakonandeType;

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public List<AzmayeshName> getAzmayeshNames() {
        return azmayeshNames;
    }

    public void setAzmayeshNames(List<AzmayeshName> azmayeshNames) {
        this.azmayeshNames = azmayeshNames;
    }

    public String getAzmayeshNameTypeYadak() {
        return azmayeshNameTypeYadak;
    }

    public void setAzmayeshNameTypeYadak(String azmayeshNameTypeYadak) {
        this.azmayeshNameTypeYadak = azmayeshNameTypeYadak;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.clinicService = (IClinicService) wac.getBean(IClinicService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.userService = (IUserService) wac.getBean("userService");
    }

    private Clinic clinic;
    private Azmayesh azmayesh;
    private AzmayeshType azmayeshType;

    public String removeClinic() {
        clinicService.removeClinic(clinic.getId());

        clinics = clinicService.findAllClinics();
        return SUCCESS;
    }

    public String update() {
        clinic = clinicService.getClinicById(clinic.getId());

        return SUCCESS;
    }

    public String finilizeClinicUpdate() {
        Clinic theClinic = clinicService.getClinicById(clinic.getId());
        theClinic.setAddress(clinic.getAddress());
        theClinic.setCityName(clinic.getCityName());
        theClinic.setClinicName(clinic.getClinicName());
        theClinic.setTarikhEtmamGharardad(clinic.getTarikhEtmamGharardad());
        theClinic.setTarikhShorouGharardad(clinic.getTarikhShorouGharardad());
        clinicService.updateClinic(theClinic);
        clinics = clinicService.findAllClinics();
        return SUCCESS;
    }

    public String save() {
        String result = clinicService.addNewClinic(clinic);
        if (result.equalsIgnoreCase("SUCCESS")) {
            return SUCCESS;
        } else {
            duplicate = "yes";
            return INPUT;
        }

    }

    public String redirectToAddNazarPezeshk() {

        return SUCCESS;
    }

    public String redirectToAddNazarReis() {
        return SUCCESS;
    }

    private PaginatedListImpl<Clinic> listClinics;
    private PaginatedListImpl<AzmayeshName> listAzmayeshNames;
    private PaginatedListImpl<AzmayeshType> listAzmayeshTypes;
    private String sname, scityname, saztarikh, statarikh, snoe;

    public String showAll() {
        listClinics = new PaginatedListImpl<Clinic>();
        listClinics.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        listClinics.setPageNumber(pageNumber);
        listClinics = clinicService.findAllClinics(listClinics, sname, scityname, saztarikh, statarikh);
        azmayeshNames = clinicService.findAllAzmayeshNames();
        getSession().put("listClinics", listClinics);
        return SUCCESS;
    }

    public String addAzemayeshToAll(){
        clinicService.addNewAzmayeshToAllClinic(azmayesh);
        addActionMessage("آزمایش با موفقیت به همه کلینیک ها اضافه گشت.");
        return SUCCESS;
    }

    public String addAzemayeshToSomeClinics(){
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("_chk")) {
                    Object[] clinicIds = (Object[]) ActionContext.getContext().getParameters().get(name);
                    clinicService.addAzemayeshToSomeClinics(azmayesh, clinicIds);
                    addActionMessage("آزمایش با موفقیت به کلینیک های انتخاب شده اضافه گشت.");
                    return SUCCESS;
                }
            }
        }
        return SUCCESS;
    }

    public String findAzmayeshat() {
        Integer theId = clinic.getId();
        Clinic theClinic = clinicService.getClinicById(theId);
        clinic = theClinic;
        azmayeshat = theClinic.getAzmayeshs();
        return SUCCESS;
    }

    public String addAzmayesh() {
        Integer clinicId = clinic.getId();
        Clinic sayerClinic = clinicService.getClinicById(SAYER_CLINIC_ID);
        Clinic theClinic = clinicService.getClinicById(clinicId);
        clinicService.addNewAzmayesh(azmayesh, clinicId);
        sayerClinic.getAzmayeshs().add(azmayesh);
        clinicService.updateClinic(sayerClinic);
        Clinic theNewClinic = clinicService.getClinicById(clinicId);
        azmayeshat = theNewClinic.getAzmayeshs();
        clinic = theNewClinic;

        return SUCCESS;
    }

    public String doEditAzmayesh() {
        Azmayesh theAzmayesh = clinicService.getAzmayeshById(azmayesh.getId());
        theAzmayesh.setDescription(azmayesh.getDescription());
        theAzmayesh.getAzmayeshName().setName(azmayesh.getAzmayeshName().getName());
        theAzmayesh.setPrice(azmayesh.getPrice());
        //theAzmayesh.setType(azmayesh.getType());
        clinicService.updateAzmayesh(theAzmayesh);
        Clinic theClinic = clinicService.getClinicById(clinic.getId());
        clinic = theClinic;
        azmayeshat = theClinic.getAzmayeshs();
        return SUCCESS;
    }

    public String submitAzmayesh() {
        Integer clinicId = clinic.getId();

        if (azmayesh.getId() == null) {
            //new

            boolean returnVal = clinicService.addNewAzmayesh(azmayesh, clinicId);
            if (!returnVal) {
                errorsMap = new HashMap<String, String>();
                errorsMap.put("duplicateName", "نام آزمایش تکراری می باشد");


                azmayeshType = azmayesh.getAzmayeshName().getAzmayeshType();
                azmayeshNames = clinicService.findAllAzmayeshNames();
                azmayeshName = azmayesh.getAzmayeshName();

                return ERROR;
            }
            Clinic theClinic = clinicService.getClinicById(clinicId);
            theClinic.getAzmayeshs().add(azmayesh);
            clinicService.updateClinic(theClinic);

            //Sayer
            clinicService.addNewAzmayesh(azmayesh, SAYER_CLINIC_ID);
            Clinic sayerClinic = clinicService.getClinicById(SAYER_CLINIC_ID);
            sayerClinic.getAzmayeshs().add(azmayesh);
            clinicService.updateClinic(sayerClinic);
        } else {
            //edit
            Azmayesh theAzmayesh = clinicService.getAzmayeshById(azmayesh.getId());
            theAzmayesh.setDescription(azmayesh.getDescription());
            theAzmayesh.setAzmayeshName(azmayesh.getAzmayeshName());
            theAzmayesh.setPrice(azmayesh.getPrice());
            clinicService.updateAzmayesh(theAzmayesh);
        }

        Clinic theClinic = clinicService.getClinicById(clinicId);
        azmayeshat = theClinic.getAzmayeshs();
        clinic = theClinic;

        return SUCCESS;
    }

    public String editAzmayesh() {
        azmayeshNames = clinicService.findAllAzmayeshNames();
        azmayesh = clinicService.getAzmayeshById(azmayesh.getId());
        azmayeshName = azmayesh.getAzmayeshName();
        azmayeshType = azmayesh.getAzmayeshName().getAzmayeshType();
        return SUCCESS;
    }

    public String editAzmayeshType() {
        azmayeshType = clinicService.getAzmayeshTypeById(azmayeshType.getId());
        return SUCCESS;
    }

    public String editAzmayeshName() {
        azmayeshName = clinicService.getAzmayeshNameById(azmayeshName.getId());
        azmayeshTypes = clinicService.findAllAzmayeshTypes();
        return SUCCESS;
    }


    public String doEditAzmayeshType() {
        AzmayeshType theAzmayeshType = clinicService.getAzmayeshTypeById(azmayeshType.getId());
        theAzmayeshType.setDescription(azmayeshType.getDescription());
        theAzmayeshType.setType(azmayeshType.getType());
        clinicService.updateAzmayeshType(theAzmayeshType);
        azmayeshTypes = clinicService.findAllAzmayeshTypes();
        return SUCCESS;
    }

    public String doEditAzmayeshName() {
        AzmayeshName theAzmayeshName = clinicService.getAzmayeshNameById(azmayeshName.getId());
        theAzmayeshName.setDescription(azmayeshName.getDescription());
        theAzmayeshName.setName(azmayeshName.getName());
        AzmayeshType theType = clinicService.getAzmayeshTypeById(Integer.parseInt(azmayeshNameTypeYadak));
        theAzmayeshName.setAzmayeshType(theType);
        clinicService.updateAzmayeshName(theAzmayeshName);
        azmayeshNames = clinicService.findAllAzmayeshNames();
        return SUCCESS;
    }

    public String addAzmayeshType() {
        String result = clinicService.addNewAzmayeshType(azmayeshType);
        if (result.equalsIgnoreCase("SUCCESS")) {
            return SUCCESS;
        } else {
            duplicate = "yes";
            return INPUT;
        }
    }

    public String addAzmayeshName() throws Exception {
        AzmayeshType theType = clinicService.getAzmayeshTypeById(Integer.parseInt(azmayeshNameTypeYadak));
        azmayeshName.setAzmayeshType(theType);
        String result = clinicService.addNewAzmayeshName(azmayeshName);
        if (result.equalsIgnoreCase("SUCCESS")) {
            return SUCCESS;
        } else {
            azmayeshTypes = clinicService.findAllAzmayeshTypes();
            duplicate = "yes";
            return INPUT;
        }
    }

    public String sabtAzmayeshType() {
        return SUCCESS;
    }

    public String prepareForSabtNameAzmayesh() {
        azmayeshTypes = clinicService.findAllAzmayeshTypes();
        return SUCCESS;
    }

//
//    public String searchUsersToPrepareToAddEmzakonande() {
//        userList = loginService.findAllUsersNotEmzakonande();
//        getSession().put("listUser", userList);
//        return SUCCESS;
//    }

    public String showEmzaKonandegan() {
        emzaKonandegan = new PaginatedListImpl<EmzaKonande>();
        emzaKonandegan.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        emzaKonandegan.setPageNumber(pageNumber);
        Boolean uiStatus = null;
        if (this.uiStatus != null)
            switch (this.uiStatus) {
                case 0:
                    uiStatus = true;
                    break;
                case 1:
                    uiStatus = true;
                    break;
                default:
                    uiStatus = null;
            }
        emzaKonandegan = loginService.findAllEmzaKonandegan(emzaKonandegan, fname, lname, code, viSodurId, viSodurName, uiStatus, roleId, namayandeId, namayandeName, azEtebar, taEtebar);
        getSession().put("listEmzakonande", emzaKonandegan);
        return SUCCESS;
    }

    public String removeEmzaKonande() {
        clinicService.removeEmzaKonandeById(emzaKonande.getId());
        return showEmzaKonandegan();
    }

    public String prepareAddEmzakonande() {
        user = loginService.findUserById(user.getId());
        return SUCCESS;
    }

    public String prepareEditEmzaKonande() {
        emzaKonande = clinicService.findEmzaKonandeById(emzaKonande.getId());
        user = emzaKonande.getUser();
        return SUCCESS;
    }

    public String sabtEmzaKonande() {
        if(emzaKonande != null && emzaKonande.getId() != null){
            EmzaKonande theEmzaKonande = clinicService.findEmzaKonandeById(emzaKonande.getId());
            if (emzakonandeType != null && !emzakonandeType.isEmpty()) {
                theEmzaKonande.setType(EmzaKonande.EmzakonandeType.valueOf(emzakonandeType));
            }
            theEmzaKonande.setMaxCapitalString(emzaKonande.getMaxCapitalString());
            theEmzaKonande.setActive(emzaKonande.getActive());
            theEmzaKonande.setOtherMojtamaSodoor(emzaKonande.getOtherMojtamaSodoor());
            clinicService.updateEmzaKonande(theEmzaKonande);
        } else{
            User theUser = loginService.findUserById(emzaKonande.getUser().getId());
            if (emzakonandeType != null && !emzakonandeType.isEmpty()) {
                emzaKonande.setType(EmzaKonande.EmzakonandeType.valueOf(emzakonandeType));
            }
            emzaKonande.setUser(theUser);
            clinicService.addEmzaKonande(emzaKonande);
        }
        return SUCCESS;
    }

    public String listAllAzmayeshTypes() {
        listAzmayeshTypes = new PaginatedListImpl<AzmayeshType>();
        listAzmayeshTypes.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        listAzmayeshTypes.setPageNumber(pageNumber);
        listAzmayeshTypes = clinicService.findAllAzmayeshTypes(listAzmayeshTypes, snoe);
        getSession().put("listAzmayeshTypes", listAzmayeshTypes);
        return SUCCESS;
    }

    public String listAllAzmayeshNames() {
        listAzmayeshNames = new PaginatedListImpl<AzmayeshName>();
        listAzmayeshNames.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        listAzmayeshNames.setPageNumber(pageNumber);
        listAzmayeshNames = clinicService.findAllAzmayeshNames(listAzmayeshNames, sname, snoe);
        getSession().put("listAzmayeshNames", listAzmayeshNames);
        return SUCCESS;
    }

    public AzmayeshType getAzmayeshType() {
        return azmayeshType;
    }

    public void setAzmayeshType(AzmayeshType azmayeshType) {
        this.azmayeshType = azmayeshType;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<Clinic> getClinics() {

        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public List<Azmayesh> getAzmayeshat() {
        return azmayeshat;
    }

    public void setAzmayeshat(List<Azmayesh> azmayeshat) {
        this.azmayeshat = azmayeshat;
    }

    public Azmayesh getAzmayesh() {
        return azmayesh;
    }

    public void setAzmayesh(Azmayesh azmayesh) {
        this.azmayesh = azmayesh;
    }

    public IClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public List<AzmayeshType> getAzmayeshTypes() {
        return azmayeshTypes;
    }

    public void setAzmayeshTypes(List<AzmayeshType> azmayeshTypes) {
        this.azmayeshTypes = azmayeshTypes;
    }

    public AzmayeshName getAzmayeshName() {
        return azmayeshName;
    }

    public void setAzmayeshName(AzmayeshName azmayeshName) {
        this.azmayeshName = azmayeshName;
    }

    public String prepareAzmayeshAddition() {
        azmayeshNames = clinicService.findAllAzmayeshNames();
        return SUCCESS;
    }

    public String fetchAzmayeshType() throws Exception {
        AzmayeshName theAzmayeshName = clinicService.getAzmayeshNameById(azmayeshName.getId());
        azmayeshType = theAzmayeshName.getAzmayeshType();
        azmayeshNames = clinicService.findAllAzmayeshNames();
        azmayeshName = theAzmayeshName;
        return SUCCESS;
    }

    public String removeAzmayesh() {
        Clinic theClinic = clinicService.getClinicById(clinic.getId());
        Azmayesh theAzmayesh = clinicService.getAzmayeshById(azmayesh.getId());
        theClinic.getAzmayeshs().remove(theAzmayesh);
        clinicService.updateClinic(theClinic);
        theAzmayesh.getClinics().remove(theClinic);
        clinicService.updateAzmayesh(theAzmayesh);
        clinicService.removeAzmayesh(azmayesh.getId());
        Clinic theNewClinic = clinicService.getClinicById(theClinic.getId());
        clinic = theNewClinic;
        azmayeshat = theNewClinic.getAzmayeshs();
        return SUCCESS;
    }

    public String removeAzmayeshType() throws Exception {
        Integer azmayeshTypeId = azmayeshType.getId();
        AzmayeshType theAzmayeshType = clinicService.getAzmayeshTypeById(azmayeshTypeId);
        List<AzmayeshName> theAzmayeshNames = clinicService.findAllAzmayeshNames();
        for (AzmayeshName theAzmayeshName : theAzmayeshNames) {
            if (theAzmayeshName.getAzmayeshType().equals(theAzmayeshType)) {
                clinicService.removeAzmayeshNameById(theAzmayeshName.getId());
            }
        }
        clinicService.removeAzmayeshTypeById(azmayeshTypeId);
        azmayeshTypes = clinicService.findAllAzmayeshTypes();
        return SUCCESS;
    }

    public String removeAzmayeshName() {
        clinicService.removeAzmayeshNameById(azmayeshName.getId());
        azmayeshNames = clinicService.findAllAzmayeshNames();
        return SUCCESS;
    }

    public Integer getSumup() {
        return sumup;
    }

    public void setSumup(Integer sumup) {
        this.sumup = sumup;
    }

    public String getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(String duplicate) {
        this.duplicate = duplicate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public EmzaKonande getEmzaKonande() {
        return emzaKonande;
    }

    public void setEmzaKonande(EmzaKonande emzaKonande) {
        this.emzaKonande = emzaKonande;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String sabtHazinePezashkiStage1() {
        return SUCCESS;
    }

    private String azTarikhSodur, taTarikhSodur, kodeRahgiri, kodeMoarefi, nameBimeshode, nameKhanevadegiBimeShode, kodeMelli, mablaghClinic, mablaghKhesarat, tozihat;
    private int vaziatMoarefi, clinicSearch;
    private List<Moarefiname> searchResult;
    private List<Clinic> clinicList;
    private Moarefiname moarefiname;

    public String detailHazinePezashki() {
        moarefiname = clinicService.findMoarefinameById(moarefiname.getId());
        if (moarefiname == null) return ERROR;
        nameBimeshode = moarefiname.getPishnehad().getBimeShode().getShakhs().getName();
        nameKhanevadegiBimeShode = moarefiname.getPishnehad().getBimeShode().getShakhs().getNameKhanevadegi();
        kodeMelli = moarefiname.getPishnehad().getBimeShode().getShakhs().getKodeMelliShenasayi();
        if (moarefiname.getHazinePezeshki() != null) {
            mablaghClinic = moarefiname.getHazinePezeshki().getMablaghClinic();
            mablaghKhesarat = moarefiname.getHazinePezeshki().getMablaghKhesarat();
            tozihat = moarefiname.getHazinePezeshki().getTozihat();
        }

        return SUCCESS;
    }

    public String sabtDetailHazinePezashki() {
        moarefiname = clinicService.findMoarefinameById(moarefiname.getId());
        if (moarefiname == null) return ERROR;
        if (moarefiname.getHazinePezeshki() == null)
            moarefiname.setHazinePezeshki(new HazinePezeshki(moarefiname));
        moarefiname.getHazinePezeshki().setMablaghClinic(mablaghClinic);
        moarefiname.getHazinePezeshki().setMablaghKhesarat(mablaghKhesarat);
        moarefiname.getHazinePezeshki().setTozihat(tozihat);
        clinicService.updateMoarefiname(moarefiname);
        addActionMessage("هزینه پزشکی با موفقیت ثبت شد.");
        return SUCCESS;
    }

    public String searchHazinePezashki() {
        searchResult = clinicService.findMoarefiname(azTarikhSodur, taTarikhSodur, kodeRahgiri, kodeMoarefi, nameBimeshode, nameKhanevadegiBimeShode, kodeMelli, clinicSearch, vaziatMoarefi);
        return SUCCESS;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public String getAzTarikhSodur() {
        return azTarikhSodur;
    }

    public void setAzTarikhSodur(String azTarikhSodur) {
        this.azTarikhSodur = azTarikhSodur;
    }

    public String getTaTarikhSodur() {
        return taTarikhSodur;
    }

    public void setTaTarikhSodur(String taTarikhSodur) {
        this.taTarikhSodur = taTarikhSodur;
    }

    public String getKodeRahgiri() {
        return kodeRahgiri;
    }

    public void setKodeRahgiri(String kodeRahgiri) {
        this.kodeRahgiri = kodeRahgiri;
    }

    public String getKodeMoarefi() {
        return kodeMoarefi;
    }

    public void setKodeMoarefi(String kodeMoarefi) {
        this.kodeMoarefi = kodeMoarefi;
    }

    public String getNameBimeshode() {
        return nameBimeshode;
    }

    public void setNameBimeshode(String nameBimeshode) {
        this.nameBimeshode = nameBimeshode;
    }

    public String getNameKhanevadegiBimeShode() {
        return nameKhanevadegiBimeShode;
    }

    public void setNameKhanevadegiBimeShode(String nameKhanevadegiBimeShode) {
        this.nameKhanevadegiBimeShode = nameKhanevadegiBimeShode;
    }

    public String getKodeMelli() {
        return kodeMelli;
    }

    public void setKodeMelli(String kodeMelli) {
        this.kodeMelli = kodeMelli;
    }

    public int getVaziatMoarefi() {
        return vaziatMoarefi;
    }

    public void setVaziatMoarefi(int vaziatMoarefi) {
        this.vaziatMoarefi = vaziatMoarefi;
    }

    public List<Moarefiname> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Moarefiname> searchResult) {
        this.searchResult = searchResult;
    }

    public List<Clinic> getClinicList() {
        if (clinicList == null) {
            clinicList = clinicService.findAllClinics();
        }
        return clinicList;
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
    }

    public int getClinicSearch() {
        return clinicSearch;
    }

    public void setClinicSearch(int clinicSearch) {
        this.clinicSearch = clinicSearch;
    }

    public String getMablaghClinic() {
        return mablaghClinic;
    }

    public void setMablaghClinic(String mablaghClinic) {
        this.mablaghClinic = mablaghClinic;
    }

    public String getMablaghKhesarat() {
        return mablaghKhesarat;
    }

    public void setMablaghKhesarat(String mablaghKhesarat) {
        this.mablaghKhesarat = mablaghKhesarat;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public Moarefiname getMoarefiname() {
        return moarefiname;
    }

    public void setMoarefiname(Moarefiname moarefiname) {
        this.moarefiname = moarefiname;
    }

    public PaginatedListImpl<EmzaKonande> getEmzaKonandegan() {
        return emzaKonandegan;
    }

    public void setEmzaKonandegan(PaginatedListImpl<EmzaKonande> emzaKonandegan) {
        this.emzaKonandegan = emzaKonandegan;
    }

    public Integer getUiStatus() {
        return uiStatus;
    }

    public void setUiStatus(Integer uiStatus) {
        this.uiStatus = uiStatus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getNamayandeId() {
        return namayandeId;
    }

    public void setNamayandeId(Integer namayandeId) {
        this.namayandeId = namayandeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Role> getRoleList() {
        if (roleList == null) roleList = userService.getRoleList();
        Iterator<Role> it = roleList.iterator();
        while (it.hasNext()) {
            final Role r = it.next();
            if (r.getRoleName().contains("PAS") || r.getRoleName().contains("MAJAZI"))
                it.remove();
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getEmzakonandeType() {
        return emzakonandeType;
    }

    public void setEmzakonandeType(String emzakonandeType) {
        this.emzakonandeType = emzakonandeType;
    }

    public String getAzEtebar() {
        return azEtebar;
    }

    public void setAzEtebar(String azEtebar) {
        this.azEtebar = azEtebar;
    }

    public String getTaEtebar() {
        return taEtebar;
    }

    public void setTaEtebar(String taEtebar) {
        this.taEtebar = taEtebar;
    }

    public PaginatedListImpl<Clinic> getListClinics() {
        return listClinics;
    }

    public void setListClinics(PaginatedListImpl<Clinic> listClinics) {
        this.listClinics = listClinics;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getScityname() {
        return scityname;
    }

    public void setScityname(String scityname) {
        this.scityname = scityname;
    }

    public String getSaztarikh() {
        return saztarikh;
    }

    public void setSaztarikh(String saztarikh) {
        this.saztarikh = saztarikh;
    }

    public String getStatarikh() {
        return statarikh;
    }

    public void setStatarikh(String statarikh) {
        this.statarikh = statarikh;
    }

    public PaginatedListImpl<AzmayeshName> getListAzmayeshNames() {
        return listAzmayeshNames;
    }

    public void setListAzmayeshNames(PaginatedListImpl<AzmayeshName> listAzmayeshNames) {
        this.listAzmayeshNames = listAzmayeshNames;
    }

    public PaginatedListImpl<AzmayeshType> getListAzmayeshTypes() {
        return listAzmayeshTypes;
    }

    public void setListAzmayeshTypes(PaginatedListImpl<AzmayeshType> listAzmayeshTypes) {
        this.listAzmayeshTypes = listAzmayeshTypes;
    }

    public String getSnoe() {
        return snoe;
    }

    public Integer getViSodurId() {
        return viSodurId;
    }

    public void setViSodurId(Integer viSodurId) {
        this.viSodurId = viSodurId;
    }

    public String getViSodurName() {
        return viSodurName;
    }

    public void setViSodurName(String viSodurName) {
        this.viSodurName = viSodurName;
    }

    public String getNamayandeName() {
        return namayandeName;
    }

    public void setNamayandeName(String namayandeName) {
        this.namayandeName = namayandeName;
    }

    public void setSnoe(String snoe) {
        this.snoe = snoe;
    }
}