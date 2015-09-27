package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.pishnahad.Fish;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.viewModel.GStructure;
import com.bitarts.parsian.viewModel.GStructureNamayande;
import com.bitarts.parsian.viewModel.GStructureTafkikVaziat;
import com.bitarts.parsian.viewModel.GStructureTransitionLog;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 10/5/11
 * Time: 5:18 PM
 */
public class GozareshModiriatAction extends BaseAction implements ServletContextAware {
    private ILoginService loginService;
    private IPishnehadService pishnehadService;
    private INamayandeService namayandeService;
    private IUserService userService;
    private IAsnadeSodorService asnadeSodorService;
    private IConstantItemsService constantItemsService;
    private String realPath;
    private List<GStructure> gStructureList;
    private Integer roleId, stateId;
    private Long userId;
    private List<Role> roleList;
    private List<User> userList;
    private List<State> stateList;
    private String azTarikh;
    private String taTarikh;
    private String chartPath;
    private String azVaziat;
    private String beVaziat;
    private List<State> states;
    private String jamJadid;
    private String jamErsalShode;

    public void setServletContext(ServletContext servletContext) {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//        this.estelamService = (IEstelamService) wac.getBean(IEstelamService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService = (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.userService = (IUserService) wac.getBean(IUserService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");

    }

    private List<GStructureNamayande> gStructureNamayandeList;
    private Integer filter;
    private List<GStructureTafkikVaziat> gStructureTafkikList;
    private List<GStructureTafkikVaziat> gStructureTafkikVaziatList;
    private List<GStructureTransitionLog> gStructureTransitionLogList;
    private String kolZamanTaghirVaziat;
    private List<Fish> fishList;

    public String prepareGozareshSadereVarizi() {
        return SUCCESS;
    }

    public String makeGozareshSadereVarizi() {
        fishList = pishnehadService.makeGozareshSadereVarizi(azTarikh, taTarikh);
        return SUCCESS;
    }

    public String prepareGozareshPardakhtElectronic() {
        return SUCCESS;
    }

    public String makeGozareshPardakhtElectronic() {
        fishList = pishnehadService.makeGozareshPardakhtElectronic(azTarikh, taTarikh);
        return SUCCESS;
    }

    public String prepareGozareshTransitionLog() {
        azTarikh = DateUtil.addMonth(DateUtil.getCurrentDate(), -1);
        taTarikh = DateUtil.getCurrentDate();
        setStates(pishnehadService.findAllSatetsForPishnehadSystem());
        return SUCCESS;
    }

    public String makeGozareshTransitionLog() {
        User user = null;
        if (userId != -1)
            user = userService.getUserById(userId);
        gStructureTransitionLogList = pishnehadService.makeGozareshTransitionLog(user, azTarikh, taTarikh, azVaziat, beVaziat);
        if (gStructureTransitionLogList.size() > 1)
            kolZamanTaghirVaziat = DateUtil.getTimeDifferenceByStringDescription(gStructureTransitionLogList.get(gStructureTransitionLogList.size() - 1).getTarikh(), gStructureTransitionLogList.get(0).getTarikh());
        setStates(pishnehadService.findAllSatetsForPishnehadSystem());
        return SUCCESS;
    }

    public String prepareGozareshTedadPishnehadTafkikVaziat() {
        return SUCCESS;
    }

    public String makeGozareshTedadPishnehadTafkikVaziat() {
        State state = null;
        if (stateId != -1)
            state = constantItemsService.findStateById(stateId);
        gStructureTafkikVaziatList = pishnehadService.makeGozareshTedadTafkikVaziat(state);
        return SUCCESS;
    }

    public String prepareGozareshTedadPishnehadTafkikKarbar() {
        return SUCCESS;
    }

    public String makeGozareshTedadPishnehadTafkikKarbar() {
        User user = null;
        if (userId != -1)
            user = userService.getUserById(userId);
        gStructureTafkikList = pishnehadService.makeGozareshTedadTafkik(azTarikh, taTarikh, user);
        return SUCCESS;
    }

    public String makeGozareshMoghayeseNamayande() {
        gStructureNamayandeList = namayandeService.makeGozareshMoghayese(filter, azTarikh, taTarikh);
        return SUCCESS;
    }

    public String prepareGozareshNemudarSabtPishnehad() {
        azTarikh = DateUtil.getAvaleMah();
        taTarikh = DateUtil.getCurrentDate();
        return SUCCESS;
    }

    public String makeGozareshNemudarSabtPishnehad() {
        if (azTarikh == null || azTarikh.isEmpty())
            azTarikh = "0000/00/00";
        if (taTarikh == null || taTarikh.isEmpty())
            taTarikh = "9999/99/99";
        Map<String, Map<String, Double>> data = new LinkedHashMap<String, java.util.Map<String, Double>>();
        Map<String, Double> tmp = pishnehadService.gozareshNemudarData(azTarikh, taTarikh, true);
        jamJadid = NumberFormat.getNumberInstance().format(tmp.get("sum"));
        tmp.remove("sum");
        data.put("تعداد پیشنهادات جدید", tmp);
        tmp = pishnehadService.gozareshNemudarData(azTarikh, taTarikh, false);
        jamErsalShode = NumberFormat.getNumberInstance().format(tmp.get("sum"));
        tmp.remove("sum");
        data.put("تعداد پیشنهادات ارسال شده", tmp);
        chartPath = ChartAction.createBarChart(data, "نمودار تعداد ثبت پیشنهادات روزانه در بازه زمانی منتخب");
        return SUCCESS;
    }

    public String prepareGozareshMoghayeseNamayande() {
        taTarikh = DateUtil.getCurrentDate();
        return SUCCESS;
    }

    public String prepareGozareshTedadVaziatPishnehad() {
        taTarikh = DateUtil.getCurrentDate();
        return SUCCESS;
    }

    public String makeGozareshTedadVaziatPishnehad() {
        Role role = null;
        if (roleId != -1)
            role = userService.findRoleById(roleId);
        gStructureList = pishnehadService.makeGozareshTedadVaziat(role, azTarikh, taTarikh);
        return SUCCESS;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public IPishnehadService getPishnehadService() {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService) {
        this.pishnehadService = pishnehadService;
    }

    public INamayandeService getNamayandeService() {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService) {
        this.namayandeService = namayandeService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public List<GStructure> getGStructureList() {
        return gStructureList;
    }

    public void setGStructureList(List<GStructure> gStructureList) {
        this.gStructureList = gStructureList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Role> getRoleList() {
        if (roleList == null) {
            roleList = userService.getRoleList();
            Iterator<Role> it = roleList.iterator();
            while (it.hasNext()) {
                final Role r = it.next();
                if (r.getRoleName().contains("MAJAZI"))
                    it.remove();
            }
        }

        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(String azTarikh) {
        this.azTarikh = azTarikh;
    }

    public String getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(String taTarikh) {
        this.taTarikh = taTarikh;
    }

    public List<GStructure> getgStructureList() {
        return gStructureList;
    }

    public void setgStructureList(List<GStructure> gStructureList) {
        this.gStructureList = gStructureList;
    }

    public List<GStructureNamayande> getGStructureNamayandeList() {
        return gStructureNamayandeList;
    }

    public void setGStructureNamayandeList(List<GStructureNamayande> gStructureNamayandeList) {
        this.gStructureNamayandeList = gStructureNamayandeList;
    }

    public List<GStructureNamayande> getgStructureNamayandeList() {
        return gStructureNamayandeList;
    }

    public void setgStructureNamayandeList(List<GStructureNamayande> gStructureNamayandeList) {
        this.gStructureNamayandeList = gStructureNamayandeList;
    }

    public Integer getFilter() {
        return filter;
    }

    public void setFilter(Integer filter) {
        this.filter = filter;
    }

    public String getChartPath() {
        return chartPath;
    }

    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    public List<User> getUserList() {
        if (userList == null) {
            ArrayList validRoles = new ArrayList<Role>();
            validRoles.add(userService.findRoleById(4));
            validRoles.add(userService.findRoleById(5));
            validRoles.add(userService.findRoleById(6));
            validRoles.add(userService.findRoleById(7));
            validRoles.add(userService.findRoleById(9));
            validRoles.add(userService.findRoleById(23));
            userList = userService.findByRole(validRoles);
            Collections.sort(userList);
        }
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<GStructureTafkikVaziat> getgStructureTafkikList() {
        return gStructureTafkikList;
    }

    public List<GStructureTafkikVaziat> getGStructureTafkikList() {
        return gStructureTafkikList;
    }

    public void setgStructureTafkikList(List<GStructureTafkikVaziat> gStructureTafkikList) {
        this.gStructureTafkikList = gStructureTafkikList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<State> getStateList() {
        if (stateList == null)
            stateList = constantItemsService.findStatesByStateMachine("PISHNAHAD_SYSTEM");
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public List<GStructureTafkikVaziat> getGStructureTafkikVaziatList() {
        return gStructureTafkikVaziatList;
    }

    public List<GStructureTafkikVaziat> getgStructureTafkikVaziatList() {
        return gStructureTafkikVaziatList;
    }

    public void setgStructureTafkikVaziatList(List<GStructureTafkikVaziat> gStructureTafkikVaziatList) {
        this.gStructureTafkikVaziatList = gStructureTafkikVaziatList;
    }

    public List<GStructureTransitionLog> getgStructureTransitionLogList() {
        return gStructureTransitionLogList;
    }

    public List<GStructureTransitionLog> getGStructureTransitionLogList() {
        return gStructureTransitionLogList;
    }

    public void setgStructureTransitionLogList(List<GStructureTransitionLog> gStructureTransitionLogList) {
        this.gStructureTransitionLogList = gStructureTransitionLogList;
    }

    public String getKolZamanTaghirVaziat() {
        return kolZamanTaghirVaziat;
    }

    public void setKolZamanTaghirVaziat(String kolZamanTaghirVaziat) {
        this.kolZamanTaghirVaziat = kolZamanTaghirVaziat;
    }

    public List<Fish> getFishList() {
        return fishList;
    }

    public void setFishList(List<Fish> fishList) {
        this.fishList = fishList;
    }


    public String getAzVaziat() {
        return azVaziat;
    }

    public void setAzVaziat(String azVaziat) {
        this.azVaziat = azVaziat;
    }

    public String getBeVaziat() {
        return beVaziat;
    }

    public void setBeVaziat(String beVaziat) {
        this.beVaziat = beVaziat;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public String getJamJadid() {
        return jamJadid;
    }

    public void setJamJadid(String jamJadid) {
        this.jamJadid = jamJadid;
    }

    public String getJamErsalShode() {
        return jamErsalShode;
    }

    public void setJamErsalShode(String jamErsalShode) {
        this.jamErsalShode = jamErsalShode;
    }
}

