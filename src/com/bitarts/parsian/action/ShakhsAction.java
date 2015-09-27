package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.IConstantItemsService;
import com.bitarts.parsian.service.IDarkhastService;
import com.bitarts.parsian.service.IShakhsService;
import com.bitarts.parsian.viewModel.SearchResult;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron3
 * Date: 3/9/11
 * Time: 4:50 PM
 */
public class ShakhsAction extends BaseAction implements ServletContextAware {

    private IShakhsService shakhsService;
    private IConstantItemsService constantItemsService;
    private IDarkhastService darkhastService;
    private String bimegozarKodeMelliIsChanged = "false";
    private String shakhsIsHoghughi = "no";
    private boolean isHalateElhaghie;
    private String nesbatBaBimeShode;
    private int darkhastTaghiratId;

    public String getNesbatBaBimeShode() {
        return nesbatBaBimeShode;
    }

    public void setNesbatBaBimeShode(String nesbatBaBimeShode) {
        this.nesbatBaBimeShode = nesbatBaBimeShode;
    }

    public boolean getIsHalateElhaghie() {
        return this.isHalateElhaghie;
    }

    public void setIsHalateElhaghie(boolean isHalateElhaghie) {
        this.isHalateElhaghie = isHalateElhaghie;
    }

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.shakhsService = (IShakhsService) wac.getBean(IShakhsService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean(IConstantItemsService.SERVICE_NAME);
        this.darkhastService = (IDarkhastService) wac.getBean(IDarkhastService.SERVICE_NAME);
    }


    public Shakhs getBimeShoodeShakhs() {
        return bimeShoodeShakhs;
    }

    public void setBimeShoodeShakhs(Shakhs bimeShoodeShakhs) {
        this.bimeShoodeShakhs = bimeShoodeShakhs;
    }

    public String getBimeShodeOrGozar() {
        return bimeShodeOrGozar;
    }

    public void setBimeShodeOrGozar(String bimeShodeOrGozar) {
        this.bimeShodeOrGozar = bimeShodeOrGozar;
    }

    public Shakhs getBimeGozarShakhs() {
        return bimeGozarShakhs;
    }

    public void setBimeGozarShakhs(Shakhs bimeGozarShakhs) {
        this.bimeGozarShakhs = bimeGozarShakhs;
    }

    public Integer getBiShodeId() {
        return biShodeId;
    }

    public void setBiShodeId(Integer biShodeId) {
        this.biShodeId = biShodeId;
    }

    private Shakhs shakhs;
    private Shakhs bimeShoodeShakhs;
    private Shakhs bimeGozarShakhs;
    private String bimeShodeOrGozar;
    private String biShodeFullName;
    private Integer biShodeId;
    private String shakhsId;
    private InputStream inputStream;

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public String checkNesbat()
    {
        bimeGozarShakhs=shakhsService.findShakhs(bimeGozarShakhs.getId());
        bimeShoodeShakhs=shakhsService.findShakhs(bimeShoodeShakhs.getId());
        if(bimeGozarShakhs.getKodeMelliShenasayi().equals(bimeShoodeShakhs.getKodeMelliShenasayi()))
            inputStream = new StringBufferInputStream("true");
        else
            inputStream = new StringBufferInputStream("false");
        return SUCCESS;
    }

    public String editShakhs() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean shouldShakhsEdit=false;
        Shakhs shakhsShouldEdit=null;
        Shakhs oldShakhs = shakhsService.findShakhs(shakhs.getId());
        boolean isEditInElhaghieTaghirStateMachine = false;
        if (darkhastTaghiratId > 0) {
            DarkhastTaghirat dt = darkhastService.findDarkhastTaghiratById(darkhastTaghiratId);
            if (dt.getState().getId() == 9010 || dt.getState().getId() == 9080 || dt.getState().getId() == 9140 || dt.getState().getId() == 9030 || dt.getState().getId() == 9160
                    || dt.getState().getId() == 9050) {
                isEditInElhaghieTaghirStateMachine = true;
            }
        }

        if (isEditInElhaghieTaghirStateMachine || isHalateElhaghie)
        {
            if(shakhsService.isHavePishnehadOrCurrentDarkhast(oldShakhs.getKodeMelliShenasayi()))
            {
                addActionError("این شخص دارای پیشنهاد یا بیمه نامه دارای درخواست در جریان می باشد،لذا قبل از ویرایش اطلاعات وی، پیشنهادات و درخواست های در جریان مربوطه را به اتمام برسانید.");
                return ERROR;
            }
        }

        if (oldShakhs.getKodeMelliShenasayi() != null) {
            if (!oldShakhs.getKodeMelliShenasayi().equalsIgnoreCase(shakhs.getKodeMelliShenasayi())) {
                if(isEditInElhaghieTaghirStateMachine || isHalateElhaghie) {
                    if(shakhsService.searchShakhs(shakhs.getKodeMelliShenasayi(), true)!=null)
                    {
                        if(shakhsService.searchOrphanPersonRecord(shakhs.getKodeMelliShenasayi(), true)==null)
                        {
                            addActionError(getText("error.kodeMelliShenasayi.duplicated"));
                            return ERROR;
                        }
                        else
                        {
                            shouldShakhsEdit=true;
                            shakhsShouldEdit= shakhsService.searchOrphanPersonRecord(shakhs.getKodeMelliShenasayi(), true);
                        }
                    }
                }
                else if (shakhsService.isThereKodeMelliShenasayi(shakhs.getKodeMelliShenasayi())) {
                    addActionError(getText("error.kodeMelliShenasayi.duplicated"));
                    return ERROR;
                }
            }
            if (oldShakhs.getKodeMelliShenasayi().equalsIgnoreCase(shakhs.getKodeMelliShenasayi()))
            {
                if(isEditInElhaghieTaghirStateMachine || isHalateElhaghie)
                {
                    if(shakhsService.searchShakhs(shakhs.getKodeMelliShenasayi(),true)!=null)
                    {
                        if(shakhsService.searchOrphanPersonRecord(shakhs.getKodeMelliShenasayi(), true)!=null)
                        {
                            shouldShakhsEdit = true;
                            shakhsShouldEdit = shakhsService.searchOrphanPersonRecord(shakhs.getKodeMelliShenasayi(), true);
                        }
                    }
                }
            }
            if (shakhs.getKodeMelliShenasayi() != null && shakhs.getName() != null && shakhs.getNameKhanevadegi() != null)
            {
                if (!oldShakhs.getKodeMelliShenasayi().equals(shakhs.getKodeMelliShenasayi()) && !oldShakhs.getName().equals(shakhs.getName()) && !oldShakhs.getNameKhanevadegi().equals(shakhs.getNameKhanevadegi()))
                {
//                    addActionError("دقت داشته باشید، چنانچه قصد ایجاد شخص جدیدی را دارید از فرم نمایش اطلاعات فرد اقدام نفرمایید.");
//                    return ERROR;
                }
            }
        }
        if(oldShakhs.getTarikheTavallod() != null) {
            if(!oldShakhs.getTarikheTavallod().equalsIgnoreCase(shakhs.getTarikheTavallod())) {
                if (isEditInElhaghieTaghirStateMachine || isHalateElhaghie) {
                    List<Pishnehad> pList = shakhsService.findAllPishnehadsForShakhs(shakhs.getKodeMelliShenasayi(), Shakhs.Role.BIMESHODE,null);
                    if(oldShakhs.getBimeShode() != null && pList.size() > 0) {
                        addActionError("شخص مورد نظر بیمه شده پیشنهاد یا بیمه نامه ای در سیستم است. تغییر تاریخ تولد مجاز نیست.");
                        for(Pishnehad p : pList) {
                            if(p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes"))
                                addActionError(p.getBimename().getShomare());
                            else
                                addActionError(p.getRadif());
                        }
                        return ERROR;
                    }
                }
            }
        }
        boolean haghighi = false;
        if (shakhs.getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
            haghighi = true;
        String code = oldShakhs.getShomareSabt();
        if (haghighi)
            code = oldShakhs.getKodeMelliShenasayi();
        if (!getIsHalateElhaghie()) {
            // todo: security check for EL
            if (isEditInElhaghieTaghirStateMachine) {
                shakhsService.updateShakhs(shakhs);
            } else {
                if (!shakhsService.isPersonEditable(username, haghighi, code)) {
                    addActionError("شخص مورد نظر دارای بیمه نامه دائم در سیستم است و قابل ویرایش نمی باشد.");
                    return ERROR;
                }
                shakhsService.updateShakhs(shakhs);
            }
        }
        else
        {
//            if (nesbatBaBimeShode.equals("خود شخص")) {
//                Shakhs sh = shakhs;
//                if(shouldShakhsEdit)
//                {
//                    sh.setId(shakhsShouldEdit.getId());
//                    shakhsService.updateShakhs(sh);
//                }
//                else
//                    shakhsService.saveShakhs(sh);
//                bimeShoodeShakhs = sh;
//                bimeGozarShakhs = sh;
//                biShodeId = bimeShoodeShakhs.getId();
//            } else {
                if (bimeShodeOrGozar.equals("bimeShode")) {
                    bimeShoodeShakhs = shakhs;
                    if(shouldShakhsEdit)
                    {
                        bimeShoodeShakhs.setId(shakhsShouldEdit.getId());
                        shakhsService.updateShakhs(bimeShoodeShakhs);
                    }
                    else
                        shakhsService.saveShakhs(bimeShoodeShakhs);
                    biShodeId = bimeShoodeShakhs.getId();
                } else {
                    bimeGozarShakhs = shakhs;
                    if (shouldShakhsEdit)
                    {
                        bimeGozarShakhs.setId(shakhsShouldEdit.getId());
                        shakhsService.updateShakhs(bimeGozarShakhs);
                    }
                    else
                        shakhsService.saveShakhs(bimeGozarShakhs);
                    biShodeId = null;
                }
//            }
        }

        return SUCCESS;
    }

    public String shakhsTekrari() {
        if (shakhsService.isThere2KodeMelliShenasayi(shakhs.getKodeMelliShenasayi()))
            addActionError("TEKRARI");
        else
            addActionError("OK");
        return SUCCESS;
    }

    public String register() {
        try {
            if (shakhs.getType() == Shakhs.BimeGozarType.HAGHIGHI) {
                City theCityMahalleSodour = constantItemsService.findCityById(shakhs.getMahalleSodoreShenasnameh().getCityId());
                City theCityMahalleTavallod = constantItemsService.findCityById(shakhs.getMahalleTavallod().getCityId());
                shakhs.setMahalleSodoreShenasnameh(theCityMahalleSodour);
                shakhs.setMahalleTavallod(theCityMahalleTavallod);
                shakhsIsHoghughi = "no";
                if (DateUtil.isGreaterThan(shakhs.getTarikheTavallod(), DateUtil.getCurrentDate()))
                {
                    addActionError("این شخص متولد نشده است");
                    return ERROR;
                }
            } else {
                City theCityMahaleSabt = constantItemsService.findCityById(shakhs.getMahalleSabt().getCityId());
                shakhs.setMahalleSabt(theCityMahaleSabt);
                shakhsIsHoghughi = "yes";
            }

            shakhs = shakhsService.addNewShakhs(shakhs);
            return SUCCESS;
        } catch (IShakhsService.KodeMelliShenasayiDuplicated kodeMelliShenasayiDuplicated) {
            addActionError(getText("error.kodeMelliShenasayi.duplicated"));
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String findShakhsInfo() {
        if (getShakhsId() != null && getShakhsId().length() > 0)
            shakhs = shakhsService.findShakhs(Integer.valueOf(getShakhsId()));
        return SUCCESS;
    }

    private String naam;
    private String name;
    private String naam_khaanevaadegi;
    private String naam_pedar;
    private String code_melli;
    private String shomare_shenaasnaame;
    private String shomareSabt;
    private List<SearchResult> reportResult;
    private String searchBimeShode;
    private String shakhsValidString;
    private String halateElhaghie;

    public String getHalateElhaghie()
    {
        return halateElhaghie;
    }

    public void setHalateElhaghie(String halateElhaghie)
    {
        this.halateElhaghie = halateElhaghie;
    }

    public String search() {
        reportResult = shakhsService.doSearch(
                code_melli,
                searchBimeShode,
                halateElhaghie
        );

        return SUCCESS;
    }

    public String isValid() {
        shakhsValidString = shakhsService.isShakhsValid(code_melli);
        return SUCCESS;
    }

    public String searchHoghughi() {
        reportResult = shakhsService.doSearchHoghughi(naam, shomareSabt);
        return SUCCESS;
    }

    public IShakhsService getShakhsService() {
        return shakhsService;
    }

    public void setShakhsService(IShakhsService shakhsService) {
        this.shakhsService = shakhsService;
    }

    public Shakhs getShakhs() {
        return shakhs;
    }

    public void setShakhs(Shakhs shakhs) {
        this.shakhs = shakhs;
    }

    public String getShakhsId() {
        return shakhsId;
    }

    public void setShakhsId(String shakhsId) {
        this.shakhsId = shakhsId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam_khaanevaadegi() {
        return naam_khaanevaadegi;
    }

    public void setNaam_khaanevaadegi(String naam_khaanevaadegi) {
        this.naam_khaanevaadegi = naam_khaanevaadegi;
    }

    public String getNaam_pedar() {
        return naam_pedar;
    }

    public void setNaam_pedar(String naam_pedar) {
        this.naam_pedar = naam_pedar;
    }

    public String getCode_melli() {
        return code_melli;
    }

    public void setCode_melli(String code_melli) {
        this.code_melli = code_melli;
    }

    public String getShomare_shenaasnaame() {
        return shomare_shenaasnaame;
    }

    public void setShomare_shenaasnaame(String shomare_shenaasnaame) {
        this.shomare_shenaasnaame = shomare_shenaasnaame;
    }

    public List<SearchResult> getReportResult() {
        return reportResult;
    }

    public void setReportResult(List<SearchResult> reportResult) {
        this.reportResult = reportResult;
    }

    public String getBimegozarKodeMelliIsChanged() {
        return bimegozarKodeMelliIsChanged;
    }

    public void setBimegozarKodeMelliIsChanged(String bimegozarKodeMelliIsChanged) {
        this.bimegozarKodeMelliIsChanged = bimegozarKodeMelliIsChanged;
    }

    public String getShakhsIsHoghughi() {
        return shakhsIsHoghughi;
    }

    public void setShakhsIsHoghughi(String shakhsIsHoghughi) {
        this.shakhsIsHoghughi = shakhsIsHoghughi;
    }

    public String getShomareSabt() {
        return shomareSabt;
    }

    public void setShomareSabt(String shomareSabt) {
        this.shomareSabt = shomareSabt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchBimeShode() {
        return searchBimeShode;
    }

    public void setSearchBimeShode(String searchBimeShode) {
        this.searchBimeShode = searchBimeShode;
    }

    public String getShakhsValidString() {
        return shakhsValidString;
    }

    public void setShakhsValidString(String shakhsValidString) {
        this.shakhsValidString = shakhsValidString;
    }

    public int getDarkhastTaghiratId() {
        return darkhastTaghiratId;
    }

    public void setDarkhastTaghiratId(int darkhastTaghiratId) {
        this.darkhastTaghiratId = darkhastTaghiratId;
    }
}
