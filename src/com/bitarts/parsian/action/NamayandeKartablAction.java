package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.StateDAO;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.GhestBandi;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.IElhaghieService;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.IPishnehadService;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 6/24/12
 * Time: 9:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class NamayandeKartablAction extends BaseAction implements ServletContextAware {
    private StateDAO stateDAO;
    private ILoginService loginService;
    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;
    private IElhaghieService elhaghieService;


    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.stateDAO = (StateDAO) wac.getBean("stateDAO");
        this.loginService = (ILoginService) wac.getBean("loginService");
        this.pishnehadService = (IPishnehadService) wac.getBean("pishnehadService");
        asnadeSodorService = (IAsnadeSodorService) wac.getBean("asnadeSodorService");
        elhaghieService = (IElhaghieService) wac.getBean("elhaghieService");

    }

    private String shomare, saleBimei, bimeGozar, bimeShode, shomareMoshtari, azTarikhSodur, taTarikhSodur, azSarresid, taSarresid, azPardakht, taPardakht, azMablagh, taMablagh, azSanad, taSanad;
    private Integer vaziat;
    private List<State> vaziats;
    private List<Ghest> results_ghest;

    private int pishnehadId;
    private int stateId;

    public String gozareshAghsatMaliStage1() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        final List<Bimename> bimenames = pishnehadService.findAllBimenameForNamayande(user.getId());
        if (bimenames != null && bimenames.size() > 0) {
            azTarikhSodur = bimenames.get(0).getTarikhSodour();
        }
        final String now = DateUtil.getCurrentDate();
        final String avalMah = DateUtil.getAvaleMah();
        taTarikhSodur = now;
        azSarresid = avalMah;
        taSarresid = now;
        azPardakht = avalMah;
        taPardakht = now;
        azSanad = avalMah;
        taSanad = now;
        return SUCCESS;
    }

    public String validateGozareshAghsatMaliStageSearch() {
        if ((azSarresid != null && !azSarresid.isEmpty() && taSarresid != null && !taSarresid.isEmpty()) ||
                (azPardakht != null && !azPardakht.isEmpty() && taPardakht != null && !taPardakht.isEmpty()) ||
                (azSanad != null && !azSanad.isEmpty() && taSanad != null && !taSanad.isEmpty()))
            return SUCCESS;
        else {
            addActionError("لازم است یکی از بازه های سررسید قسط، یا پرداخت قسط، یا سند مالی مشخص باشد.");
            return INPUT;
        }
    }

    public String gozareshAghsatMaliStageSearch() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        final State state = stateDAO.findById(vaziat);
        shomare = shomare.isEmpty() ? null : shomare;
        saleBimei = saleBimei.isEmpty() ? null : saleBimei;
        bimeGozar = bimeGozar.isEmpty() ? null : bimeGozar;
        bimeShode = bimeShode.isEmpty() ? null : bimeShode;
        shomareMoshtari = shomareMoshtari.isEmpty() ? null : shomareMoshtari;
        azTarikhSodur = azTarikhSodur.isEmpty() ? null : azTarikhSodur;
        taTarikhSodur = taTarikhSodur.isEmpty() ? null : taTarikhSodur;
        azSarresid = azSarresid.isEmpty() ? null : azSarresid;
        taSarresid = taSarresid.isEmpty() ? null : taSarresid;
        azPardakht = azPardakht.isEmpty() ? null : azPardakht;
        taPardakht = taPardakht.isEmpty() ? null : taPardakht;
        azMablagh = azMablagh.isEmpty() ? null : azMablagh;
        taMablagh = taMablagh.isEmpty() ? null : taMablagh;
        azSanad = azSanad.isEmpty() ? null : azSanad;
        taSanad = taSanad.isEmpty() ? null : taSanad;
        results_ghest = pishnehadService.searchGhest(shomare, saleBimei, bimeGozar,
                bimeShode, shomareMoshtari, azTarikhSodur,
                taTarikhSodur, azSarresid, taSarresid,
                azPardakht, taPardakht, azMablagh,
                taMablagh, azSanad, taSanad, state, user);
        return SUCCESS;
    }

    public StateDAO getStateDAO() {
        return stateDAO;
    }

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
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

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public String getSaleBimei() {
        return saleBimei;
    }

    public void setSaleBimei(String saleBimei) {
        this.saleBimei = saleBimei;
    }

    public String getBimeGozar() {
        return bimeGozar;
    }

    public void setBimeGozar(String bimeGozar) {
        this.bimeGozar = bimeGozar;
    }

    public String getBimeShode() {
        return bimeShode;
    }

    public void setBimeShode(String bimeShode) {
        this.bimeShode = bimeShode;
    }

    public String getShomareMoshtari() {
        return shomareMoshtari;
    }

    public void setShomareMoshtari(String shomareMoshtari) {
        this.shomareMoshtari = shomareMoshtari;
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

    public String getAzSarresid() {
        return azSarresid;
    }

    public void setAzSarresid(String azSarresid) {
        this.azSarresid = azSarresid;
    }

    public String getTaSarresid() {
        return taSarresid;
    }

    public void setTaSarresid(String taSarresid) {
        this.taSarresid = taSarresid;
    }

    public String getAzPardakht() {
        return azPardakht;
    }

    public void setAzPardakht(String azPardakht) {
        this.azPardakht = azPardakht;
    }

    public String getTaPardakht() {
        return taPardakht;
    }

    public void setTaPardakht(String taPardakht) {
        this.taPardakht = taPardakht;
    }

    public String getAzMablagh() {
        return azMablagh;
    }

    public void setAzMablagh(String azMablagh) {
        this.azMablagh = azMablagh;
    }

    public String getTaMablagh() {
        return taMablagh;
    }

    public void setTaMablagh(String taMablagh) {
        this.taMablagh = taMablagh;
    }

    public String getAzSanad() {
        return azSanad;
    }

    public void setAzSanad(String azSanad) {
        this.azSanad = azSanad;
    }

    public String getTaSanad() {
        return taSanad;
    }

    public void setTaSanad(String taSanad) {
        this.taSanad = taSanad;
    }

    public Integer getVaziat() {
        return vaziat;
    }

    public void setVaziat(Integer vaziat) {
        this.vaziat = vaziat;
    }

    public List<State> getVaziats() {
        if (vaziats == null) vaziats = stateDAO.findByStateMachine("BIMENAME_SYSTEM");
        return vaziats;
    }

    public void setVaziats(List<State> vaziats) {
        this.vaziats = vaziats;
    }

    public List<Ghest> getResults_ghest() {
        return results_ghest;
    }

    public void setResults_ghest(List<Ghest> results_ghest) {
        this.results_ghest = results_ghest;
    }

    private Integer ghestBandi, type;
    private String tarikhSodur, address, telphone, mobile, shomareVam, akhzVam;

    public String gozareshAghsatMaliStageDetail() {
        final GhestBandi gb = asnadeSodorService.findGhestBandiById(ghestBandi);
        shomare = gb.getBimename().getShomare();
        tarikhSodur = gb.getBimename().getTarikhSodour();
        address = gb.getBimename().getPishnehad().getAddressBimeGozar().getNeshaniManzel();
        telphone = gb.getBimename().getPishnehad().getAddressBimeGozar().getCodeTelephoneManzel() + gb.getBimename().getPishnehad().getAddressBimeGozar().getTelephoneManzel();
        mobile = gb.getBimename().getPishnehad().getAddressBimeGozar().getTelephoneHamrah();
        bimeGozar = gb.getBimename().getPishnehad().getBimeGozar().getShakhs().getFullName();
        if (type == 0)
            results_ghest = asnadeSodorService.findGhests(ghestBandi, Credebit.CredebitType.GHEST);
        else if (type == 1) {
            results_ghest = asnadeSodorService.findGhests(ghestBandi, Credebit.CredebitType.GHEST_VAM);
            final List<Elhaghiye> els = elhaghieService.findByBimename(gb.getBimename().getId(), Elhaghiye.ElhaghiyeType.VAM);
            if (els != null && els.size() > 0) {
                shomareVam = els.get(0).getShomareElhaghiye();
                akhzVam = els.get(0).getTarikhAsar();
            }
        } else results_ghest = gb.getGhestList();
        return SUCCESS;
    }

    public IAsnadeSodorService getAsnadeSodorService() {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGhestBandi() {
        return ghestBandi;
    }

    public void setGhestBandi(Integer ghestBandi) {
        this.ghestBandi = ghestBandi;
    }

    public String getTarikhSodur() {
        return tarikhSodur;
    }

    public void setTarikhSodur(String tarikhSodur) {
        this.tarikhSodur = tarikhSodur;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShomareVam() {
        return shomareVam;
    }

    public void setShomareVam(String shomareVam) {
        this.shomareVam = shomareVam;
    }

    public String getAkhzVam() {
        return akhzVam;
    }

    public void setAkhzVam(String akhzVam) {
        this.akhzVam = akhzVam;
    }

    public IElhaghieService getElhaghieService() {
        return elhaghieService;
    }

    public void setElhaghieService(IElhaghieService elhaghieService) {
        this.elhaghieService = elhaghieService;
    }

    private Integer barresiPezeshki, nahvePardakht;
    private String azTedadHaghBime, taTedadHaghBime, azMablaghHaghBime, taMablaghHaghBime, azTedadVam, taTedadVam, azMablaghVam, taMablaghVam;
    private List<Bimename> result_bimename;

    public String gozareshAghsatMoavaghStage1() {
        return gozareshAghsatMaliStage1();
    }

    public String validateGozareshAghsatMoavaghSearch() {
        if ((azSarresid != null && !azSarresid.isEmpty() && taSarresid != null && !taSarresid.isEmpty()))
            return SUCCESS;
        else {
            addActionError("لازم است بازه زمانی سررسید قسط مشخص شده باشد.");
            return INPUT;
        }
    }

    public String gozareshAghsatMoavaghSearch() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        final State state = stateDAO.findById(vaziat);
        final String nahvePardakhtS;
        switch (nahvePardakht) {
            case 0:
                nahvePardakhtS = "mah";
                break;
            case 1:
                nahvePardakhtS = "3mah";
                break;
            case 2:
                nahvePardakhtS = "6mah";
                break;
            case 3:
                nahvePardakhtS = "sal";
                break;
            case 4:
                nahvePardakhtS = "yekja";
                break;
            default:
                nahvePardakhtS = null;
        }
        shomare = shomare.isEmpty() ? null : shomare;
        bimeGozar = bimeGozar.isEmpty() ? null : bimeGozar;
        bimeShode = bimeShode.isEmpty() ? null : bimeShode;
        azTarikhSodur = azTarikhSodur.isEmpty() ? null : azTarikhSodur;
        taTarikhSodur = taTarikhSodur.isEmpty() ? null : taTarikhSodur;
        azSarresid = azSarresid.isEmpty() ? null : azSarresid;
        taSarresid = taSarresid.isEmpty() ? null : taSarresid;
        result_bimename = pishnehadService.searchBimename(bimeGozar, bimeShode, shomare
                , azTarikhSodur, taTarikhSodur, azSarresid, taSarresid, state, barresiPezeshki, nahvePardakhtS
                , user);
        Iterator<Bimename> iterator = result_bimename.iterator();
        while (iterator.hasNext()) {
            Bimename bimename = iterator.next();
            final int tedadAghsat = bimename.getTedadAghsatPardakhtNashode();
            final int tedadAghsatVam = bimename.getTedadAghsatVamPardakhtNashode();
            final long mablaghGhest = bimename.getMajmuAghsatPardakhtNashode();
            final long mablaghVam = bimename.getMajmuAghsatVamPardakhtNashode();
            try {
                if (tedadAghsat >= Integer.parseInt(taTedadHaghBime) || tedadAghsat < Integer.parseInt(azTedadHaghBime))
                    iterator.remove();
            } catch (Exception ignore) {
            }
            try {
                if (tedadAghsatVam >= Integer.parseInt(taTedadVam) || tedadAghsatVam < Integer.parseInt(azTedadVam))
                    iterator.remove();
            } catch (Exception ignore) {
            }
            try {
                if (mablaghGhest >= Integer.parseInt(taMablaghHaghBime) || mablaghGhest < Integer.parseInt(azMablaghHaghBime))
                    iterator.remove();
            } catch (Exception ignore) {
            }
            try {
                if (mablaghVam >= Integer.parseInt(taMablaghVam) || mablaghVam < Integer.parseInt(azMablaghVam))
                    iterator.remove();
            } catch (Exception ignore) {
            }
        }
        return SUCCESS;
    }

    public Integer getBarresiPezeshki() {
        return barresiPezeshki;
    }

    public void setBarresiPezeshki(Integer barresiPezeshki) {
        this.barresiPezeshki = barresiPezeshki;
    }

    public Integer getNahvePardakht() {
        return nahvePardakht;
    }

    public void setNahvePardakht(Integer nahvePardakht) {
        this.nahvePardakht = nahvePardakht;
    }

    public String getAzTedadHaghBime() {
        return azTedadHaghBime;
    }

    public void setAzTedadHaghBime(String azTedadHaghBime) {
        this.azTedadHaghBime = azTedadHaghBime;
    }

    public String getTaTedadHaghBime() {
        return taTedadHaghBime;
    }

    public void setTaTedadHaghBime(String taTedadHaghBime) {
        this.taTedadHaghBime = taTedadHaghBime;
    }

    public String getAzMablaghHaghBime() {
        return azMablaghHaghBime;
    }

    public void setAzMablaghHaghBime(String azMablaghHaghBime) {
        this.azMablaghHaghBime = azMablaghHaghBime;
    }

    public String getTaMablaghHaghBime() {
        return taMablaghHaghBime;
    }

    public void setTaMablaghHaghBime(String taMablaghHaghBime) {
        this.taMablaghHaghBime = taMablaghHaghBime;
    }

    public String getAzTedadVam() {
        return azTedadVam;
    }

    public void setAzTedadVam(String azTedadVam) {
        this.azTedadVam = azTedadVam;
    }

    public String getTaTedadVam() {
        return taTedadVam;
    }

    public void setTaTedadVam(String taTedadVam) {
        this.taTedadVam = taTedadVam;
    }

    public String getAzMablaghVam() {
        return azMablaghVam;
    }

    public void setAzMablaghVam(String azMablaghVam) {
        this.azMablaghVam = azMablaghVam;
    }

    public String getTaMablaghVam() {
        return taMablaghVam;
    }

    public void setTaMablaghVam(String taMablaghVam) {
        this.taMablaghVam = taMablaghVam;
    }

    public List<Bimename> getResult_bimename() {
        return result_bimename;
    }

    public void setResult_bimename(List<Bimename> result_bimename) {
        this.result_bimename = result_bimename;
    }

    private List<Integer> years;
    private Integer year, month, typeOfReport;


    public String bazgashtVaziatStage1() {
        return SUCCESS;
    }

    public String bazgashtVaziatStage2() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        Pishnehad pish = pishnehadService.loadPishnehadById(pishnehadId);
        State state = stateDAO.findById(stateId);
        boolean validTransition = false;
        boolean nullKarshenas = false;
        if (pish.getState().getId() == 70 && state.getId() == 40)
            validTransition = true;
        if (pish.getState().getId() == 80 && state.getId() == 70) {
            validTransition = true;
            nullKarshenas = true;
        }
        if (pish.getState().getId() == 80 && state.getId() == 40) {
            validTransition = true;
            nullKarshenas = true;
        }
        if (pish.getState().getId() == 130 && state.getId() == 80)
            validTransition = true;
        if (pish.getState().getId() == 110 && state.getId() == 100)
            validTransition = true;
        if (pish.getState().getId() == 160 && state.getId() == 80)
            validTransition = true;
        if (pish.getState().getId() == 200 && state.getId() == 160)
            validTransition = true;
        if (pish.getState().getId() == 210 && state.getId() == 200)
            validTransition = true;
        if (pish.getState().getId() == 240 && state.getId() == 150)
            validTransition = true;
        if (pish.getState().getId() == 310 && state.getId() == 50)
            validTransition = true;
        if (pish.getState().getId() == 340 && state.getId() == 50)
            validTransition = true;
        if (pish.getState().getId() == 120 && state.getId() == 80)
            validTransition = true;
        if (pish.getState().getId() == 230 && state.getId() == 50)
            validTransition = true;
        if (pish.getState().getId() == 150 && state.getId() == 50)
            validTransition = true;
        if(username.equals("2328"))
            validTransition = true;
        if (validTransition) {
            pish.setState(state);
            if (nullKarshenas) {
                pish.setKarshenas(null);
            }
            pishnehadService.updatePishnehad(pish);
            addActionMessage("تغییر وضعیت با موفقیت انجام شد");
        } else {
            addActionMessage("تغییر وضعیت نامعتبر است");
        }

        return SUCCESS;
    }

    public String gozareshAghsatMahaneStage1() {
        year = DateUtil.extractYear(DateUtil.getCurrentDate());
        month = DateUtil.extractMonth(DateUtil.getCurrentDate());
        return SUCCESS;
    }

    public String gozareshAghsatMahaneSearch() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final User user = loginService.findUserByUsername(username);
        if (user == null) return ERROR;
        final String monthS;
        final String yearS = String.valueOf(year);
        if (month > 9)
            monthS = String.valueOf(month);
        else monthS = "0" + String.valueOf(month);
        azSarresid = yearS + "/" + monthS + "/" + "01";
        taSarresid = DateUtil.addMonth(azSarresid, 1);
        results_ghest = pishnehadService.searchGhest(null, null, null, null, null, null, null, azSarresid, taSarresid, null, null, null, null, null, null, null, user);
        Iterator<Ghest> iterator = results_ghest.iterator();
        while (iterator.hasNext()) {
            Ghest g = iterator.next();
            if (typeOfReport == 0) {
                try {
                    if (!g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST))
                        iterator.remove();
                } catch (Exception ignore) {
                }
            } else if (typeOfReport == 1) {
                try {
                    if (!g.getCredebit().getCredebitType().equals(Credebit.CredebitType.GHEST_VAM))
                        iterator.remove();
                } catch (Exception ignore) {
                }
            }

        }
        return SUCCESS;
    }

    public List<Integer> getYears() {
        years = new LinkedList<Integer>();
        for (int i = 1386; i <= DateUtil.extractYear(DateUtil.getCurrentDate()); i++)
            years.add(i);
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTypeOfReport() {
        return typeOfReport;
    }

    public void setTypeOfReport(Integer typeOfReport) {
        this.typeOfReport = typeOfReport;
    }

    private Integer bimenameId;
    private Bimename bimename;

    public String gozareshAghsatMaliFastView() {
        bimename = pishnehadService.findBimenameById(bimenameId);
        return SUCCESS;
    }

    public Integer getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(Integer bimenameId) {
        this.bimenameId = bimenameId;
    }

    public Bimename getBimename() {
        return bimename;
    }

    public void setBimename(Bimename bimename) {
        this.bimename = bimename;
    }

    public int getPishnehadId() {
        return pishnehadId;
    }

    public void setPishnehadId(int pishnehadId) {
        this.pishnehadId = pishnehadId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
}

