package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.action.DarkhastAction;
import com.bitarts.parsian.dao.BimenameDAO;
import com.bitarts.parsian.dao.StateDAO;
import com.bitarts.parsian.dao.TransitionDAO;
import com.bitarts.parsian.dao.UserDAO;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.Transition;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.bimename.*;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.IDarkhastService;
import com.bitarts.parsian.service.IKhesaratService;
import com.bitarts.parsian.service.ISequenceService;
import com.bitarts.parsian.service.ITransitionLogService;
import com.bitarts.parsian.service.calculations.BimeNaamehVaSarmayehGozari;
import com.bitarts.parsian.service.calculations.Reports.TaghsitReport;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 5:59 PM
 */
public class DarkhastService implements IDarkhastService {
    private BimenameDAO bimenameDAO;
    private StateDAO stateDAO;
    private TransitionDAO transitionDAO;
    private ITransitionLogService transitionLogService;
    private ISequenceService sequenceService;
    private UserDAO userDAO;
    private IKhesaratService khesaratService;
    private IShakhsService shakhsService;
    private ILogService logService;
    private IPishnehadService pishnehadService;
    private IAsnadeSodorService asnadeSodorService;

    @Transactional
    public int saveDarkhastTaghirat(DarkhastTaghirat darkhastTaghirat,User  user,String tarikhAsar) {
        State state = stateDAO.findById(Constant.TAGHIRAT_INITIAL_STATE);
        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
        darkhastTaghirat.setState(state);
        Bimename theBimename = darkhastTaghirat.getOldPishnehad().getBimename();
        asnadeSodorService.refreshObject(theBimename);
        if (!theBimename.getState().getId().equals(Constant.BIMENAME_INITIAL_STATE)) {
            return -1;
        }
        theBimename.setState(stateBimename);
        theBimename.setDarkhastDarJaryanType(Darkhast.DarkhastType.TAGHYIRAT);
        bimenameDAO.update(theBimename);
        darkhastTaghirat.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastTaghirat(darkhastTaghirat);
        Darkhast darkhast = new Darkhast();
        darkhast.setDarkhastTaghirat(darkhastTaghirat);
        darkhast.setDarkhastType(Darkhast.DarkhastType.TAGHYIRAT);
        darkhast.setFinished(false);
        bimenameDAO.saveDarkhast(darkhast);
        List<ListBimenameTaghirVM> bimenameTaghirVMs = getListBimenamehayeTaghirKarde(darkhastTaghirat);
        HashSet<Integer> shomareBimenameha = new HashSet<Integer>();
        shomareBimenameha.add(theBimename.getId());
        //--------------------------------------------------------------------------------------------------------------
        List<PishnehadFieldChanges> pishnehadFieldChangesList= logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
        if(OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMESHODE) || OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMEGOZAR))
        {
            theBimename.setDarkhastDarJaryanType(null);
            bimenameDAO.update(theBimename);
        }
        boolean isBimeShode = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMESHODE);
        boolean isBimeGozar = OmrUtil.isElhaghieTaghirShakhs(pishnehadFieldChangesList, Shakhs.Role.BIMEGOZAR);
        Shakhs newShakhs=null;
        if (isBimeShode || isBimeGozar)
        {
            newShakhs = darkhastTaghirat.getNewPishnehad().getBimeShode().getShakhs();
            if (isBimeGozar)
                newShakhs = darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs();
        }
        boolean changeBimeGozarAddress = false;
        boolean changeBimeShodeAddress = false;
        for (PishnehadFieldChanges pChanges : pishnehadFieldChangesList)
        {
            if (pChanges.getCategory()!=null && pChanges.getCategory().equals(("AD")))
            {
                if (pChanges.getSubject().contains("بیمه گذار"))
                {
                    changeBimeGozarAddress = true;
                }

                else if (pChanges.getSubject().contains("بیمه شده"))
                {
                    changeBimeShodeAddress = true;
                }
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        for(ListBimenameTaghirVM o : bimenameTaghirVMs)
        {
            if(o.getBimename() != null && !shomareBimenameha.contains(o.getBimename().getId())) {
                //duplicate pishnehad. . .
                PishnehadDuplicationRules mafad = new PishnehadDuplicationRules();
                mafad.setBimegozarSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                mafad.setBimeshodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                mafad.setBimenameSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                mafad.setSoalateOmoomiSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                mafad.setVaziateSalamateBimeShodeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                mafad.setSource(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi);
                mafad.setVaziateSalamateKhanevadeSource(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate);
                Pishnehad newPishnehad=new Pishnehad();
                newPishnehad= o.getBimename().getPishnehad();
//                newPishnehad.setId(null);
                newPishnehad = pishnehadService.savePishnehadForElhaghie(newPishnehad, o.getBimename().getPishnehad(), mafad, null, user, false, false);
                if (o.getShakhsRole().equals("بیمه گذار"))
                {
                    newPishnehad.getBimeGozar().setShakhs(newShakhs);
                    if (changeBimeShodeAddress)
                    {
                       newPishnehad.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                    }

                    if (changeBimeGozarAddress)
                    {
                        newPishnehad.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                    }
                }
                else if (o.getShakhsRole().equals("بیمه شده"))
                {
                    newPishnehad.getBimeShode().setShakhs(newShakhs);
                    if (changeBimeShodeAddress)
                    {
                        newPishnehad.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                    }

                    if (changeBimeGozarAddress)
                    {
                        newPishnehad.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                    }
                }
                else// (bt.getShakhsRole().equals("بیمه شده | بیمه گذار"));
                {
                    newPishnehad.getBimeShode().setShakhs(newShakhs);
                    newPishnehad.getBimeGozar().setShakhs(newShakhs);
                    if (changeBimeShodeAddress)
                    {
                        newPishnehad.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                        newPishnehad.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeShode());
                    }

                    if (changeBimeGozarAddress)
                    {
                        newPishnehad.setAddressBimeGozar(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                        newPishnehad.setAddressBimeShode(darkhastTaghirat.getNewPishnehad().getAddressBimeGozar());
                    }
                }
                pishnehadService.updatePishnehad(newPishnehad);
                Elhaghiye elhaghiye = new Elhaghiye();
                elhaghiye.setOldPishnehad(o.getBimename().getPishnehad());
                elhaghiye.setNewPishnehad(newPishnehad);
                elhaghiye.setBimename(o.getBimename());
                elhaghiye.setCreatedDate(DateUtil.getCurrentDate());
                elhaghiye.setDarkhast(darkhast);
                elhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHYIRAT);
                elhaghiye.setState(stateDAO.findById(Constant.ELHAGHIYE_INITIAL_STATE));
                if(tarikhAsar==null)
                    elhaghiye.setTarikhAsar(OmrUtil.calculateTarikhAsar(darkhastTaghirat.getWhenApply(), theBimename.getTarikhShorou()));
                else
                    elhaghiye.setTarikhAsar(o.getBimename().getLastTarikhAsarElhaghie()!=null?DateUtil.addDays(o.getBimename().getLastTarikhAsarElhaghie(),1): o.getBimename().getTarikhShorou());
                bimenameDAO.saveElhaghiye(elhaghiye);
                o.getBimename().setState(stateBimename);
                bimenameDAO.update(o.getBimename());
                shomareBimenameha.add(o.getBimename().getId());
            }

        }
        Elhaghiye elhaghiye = new Elhaghiye();
        elhaghiye.setBimename(theBimename);
        elhaghiye.setCreatedDate(DateUtil.getCurrentDate());
        elhaghiye.setDarkhast(darkhast);
        elhaghiye.setElhaghiyeType(Elhaghiye.ElhaghiyeType.TAGHYIRAT);
        elhaghiye.setState(stateDAO.findById(Constant.ELHAGHIYE_INITIAL_STATE));
        if (tarikhAsar == null)
            elhaghiye.setTarikhAsar(OmrUtil.calculateTarikhAsar(darkhastTaghirat.getWhenApply(), theBimename.getTarikhShorou()));
        else
            elhaghiye.setTarikhAsar(tarikhAsar);
        bimenameDAO.saveElhaghiye(elhaghiye);
        transitionLogService.logTaghiratCreation(darkhastTaghirat.getCreator(), darkhastTaghirat, "درخواست ایجاد شد.");
        return darkhastTaghirat.getId();
    }

    public void updateBimenameStateEbtal(Bimename bimename)
    {
        bimename.setState(stateDAO.findById(540));
        bimenameDAO.update(bimename);
    }

    @Transactional
    public void saveDarkhast(Darkhast darkhast) {
        bimenameDAO.saveDarkhast(darkhast);
    }

    public List<DarkhastTaghirat> findAllDarkhastTaghir() {
        return bimenameDAO.findAllDarkhastTaghir();
    }

    public DarkhastTaghirat findDarkhastTaghiratById(Integer id) {
        return bimenameDAO.findDarkhastTaghiratById(id);
    }

    @Transactional
    public void transitDarkhastTaghirat(User theUser, Integer id, Integer transitionId, String logmessage) {
        DarkhastTaghirat theDarkhast = bimenameDAO.findDarkhastTaghiratById(id);
        Transition transition = transitionDAO.findById(transitionId);
        theDarkhast.setState(transition.getStateEnd());

        transitionLogService.logDarkhastTaghiratTransition(theUser, theDarkhast, transition, logmessage);
        if(transition.getStateEnd().getId()==9180 && theDarkhast.getKarshenas()==null)
            theDarkhast.setKarshenas(theUser);
        bimenameDAO.updateDarkhstTaghirat(theDarkhast);
    }

    public void updateDarkhastTaghirat(DarkhastTaghirat theDarkhast) {
        bimenameDAO.updateDarkhstTaghirat(theDarkhast);
    }

    public void saveDarkhasteElhaghieTaghyirat(Darkhast darkhast) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<User> findAllKarshenasForDarkhasts() {
        return bimenameDAO.findAllKarshenas();
    }

    public List<DarkhastBazkharid> findAllUnfinishedDarkhastBazkharid() {
        return bimenameDAO.findAllUnfinishedDarkhastBazkharid();
    }

    public PaginatedListImpl<Darkhast> findAllDarkhastsUnFinished(User user,int pageNum)
    {
        return bimenameDAO.findAllDarkhastsUnFinished(user, pageNum);
    }

    public PaginatedListImpl findAllDarkhastsUnFinished(User user,PaginatedListImpl pgList)
    {
        List<Integer> rolesId=userDAO.getRolesIdOfUser(user);
        List<Integer> statesId=transitionDAO.getStateBeginsOfTransitions(rolesId);
        System.out.println("sss");
        return bimenameDAO.findAllDarkhastsUnFinished(user, pgList, statesId);
    }

    public PaginatedListImpl<Darkhast> searchHameyeDarkhastha(SearchDarkhasts sd,User user, int page)
    {
        return bimenameDAO.searchHameyeDarkhastha(sd, user, page);
    }

    public PaginatedListImpl<Darkhast>  loadDarkhasts(User user,int pageNum)
    {
        return bimenameDAO.loadDarkhasts(user, pageNum);
    }

    public PaginatedListImpl<DarkhastsVM> loadDarkhasts(User user, PaginatedListImpl<DarkhastsVM> pgList)
    {
        return bimenameDAO.loadDarkhasts(user, pgList);
    }

    public PaginatedListImpl<Darkhast> searchDarkhasts(PaginatedListImpl pgList,User user, String darkhastType ,String shomareBimename, Long namayandeId, String karshenas, String azTarikheDarkhast, String taTarikheDarkhast,
                                          String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily, String bimeShodeKodeMelli,List<String> darkhastState)
    {
        return bimenameDAO.searchDarkhasts(pgList, user, darkhastType, shomareBimename, namayandeId, karshenas, azTarikheDarkhast, taTarikheDarkhast,
                bimeGozarName, bimeGozarFamily, bimeGozarKodeMelli, bimeShodeName, bimeShodeFamily, bimeShodeKodeMelli, darkhastState);
    }

    public PaginatedListImpl<DarkhastsVM> myDarkhasts(PaginatedListImpl<DarkhastsVM> pgList, DarkhastsVM searchVM)
    {
        return bimenameDAO.myDarkhasts(pgList,searchVM);
    }

    public List<DarkhastTaghirat> searchDarkhastTaghiratsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas)
    {
        return bimenameDAO.searchDarkhastTaghiratsPaginated(shomareBime, azTarikheDarkhast, taTarikheDarkhast, namayandegiId, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar, nameBimeShode, familyBimeShode, kodeMelliBimeShode, karshenas);
    }

    public List<DarkhastBazkharid> searchDarkhastBazkharidsPaginated(String shomareBime, String azTarikheDarkhast, String taTarikheDarkhast, Long namayandegiId, String nameBimeGozar,
                                                               String familyBimeGozar, String kodeMelliBimeGozar, String  nameBimeShode, String  familyBimeShode, String kodeMelliBimeShode, String karshenas)
    {
        return bimenameDAO.searchDarkhastBazkharidsPaginated(shomareBime, azTarikheDarkhast, taTarikheDarkhast, namayandegiId, nameBimeGozar, familyBimeGozar, kodeMelliBimeGozar,
                nameBimeShode, familyBimeShode, kodeMelliBimeShode, karshenas);
    }

    public List<DarkhastTaghirat> findAllUnfinishedDarkhastTaghir() {
        return bimenameDAO.findAllUnfinishedDarkhastTaghir();
    }

    @Transactional
    public void removeDarkhast(DarkhastBazkharid darkhastBazkharid) {
        darkhastBazkharid.getBimename().setState(stateDAO.findById(Constant.BIMENAME_INITIAL_STATE));
        Darkhast darkhast=darkhastBazkharid.getDarkhast();
        Bimename bimename=darkhastBazkharid.getBimename();
        bimename.setDarkhastBazkharid(null);
        bimenameDAO.update(bimename);
        darkhastBazkharid.setBimename(null);
        bimenameDAO.updateDarkhstBazkharid(darkhastBazkharid);
        bimenameDAO.removeDarkhast(darkhastBazkharid);
        bimenameDAO.delete(darkhast);
    }

    @Transactional
    public int saveDarkhastTaghirKod(DarkhastBazkharid darkhast) {
//        darkhast.setState(stateDAO.findById(1600));
        darkhast.getBimename().setState(stateDAO.findById(510));
        bimenameDAO.saveDarkhastBazkharid(darkhast);
        bimenameDAO.update(darkhast.getBimename());
        return darkhast.getId();
    }

    @Transactional
    public int saveDarkhastTozih(DarkhastBazkharid darkhast) {
//        darkhast.setState(stateDAO.findById(1700));
//        darkhast.getBimename().setState(stateDAO.findById(510));
        bimenameDAO.saveDarkhastBazkharid(darkhast);
//        bimenameDAO.update(darkhast.getBimename());
        return darkhast.getId();
    }

    @Transactional
    public int saveDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        State state;
        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
        if (darkhastBazkharid.getBimenameIsMafqud() != null && darkhastBazkharid.getBimenameIsMafqud().equals("on") && Integer.parseInt(darkhastBazkharid.getArzeshBazkharid().replace(",", "")) > 20000000) {
            state = stateDAO.findById(900);
        } else {
            state = stateDAO.findById(Constant.BAZKHARID_INITIAL_STATE);
        }
        darkhastBazkharid.setState(state);
        Bimename theBimename = darkhastBazkharid.getBimename();
        theBimename.setState(stateBimename);
        bimenameDAO.update(theBimename);
        darkhastBazkharid.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    @Transactional
    public int saveDarkhastKhesarat(DarkhastBazkharid darkhastBazkharid)
    {
        State state;
        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
            state = stateDAO.findById(Constant.KHESARAT_SABTE_MOVAGHAT);
        darkhastBazkharid.setState(state);
        Bimename theBimename = darkhastBazkharid.getBimename();
        theBimename.setState(stateBimename);
        bimenameDAO.update(theBimename);
        darkhastBazkharid.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    @Transactional
    public int saveDarkhastBazkharidObj(DarkhastBazkharid darkhastBazkharid) {
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    @Transactional
    public int saveDarkhastVam(DarkhastBazkharid darkhastBazkharid) {
        State state = stateDAO.findById(Constant.VAM_INITIAL_STATE);
//        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
        darkhastBazkharid.setState(state);
//        Bimename theBimename = darkhastBazkharid.getBimename();
//        theBimename.setState(stateBimename);
//        bimenameDAO.update(theBimename);
        darkhastBazkharid.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    @Transactional
    public int saveDarkhastBardashtAzAndokhte(DarkhastBazkharid darkhastBazkharid) {
        State state = stateDAO.findById(Constant.BARDASHT_AZ_ANDOKHTE_INITIAL_STATE);
        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
        Bimename theBimename = darkhastBazkharid.getBimename();
        theBimename.setState(stateBimename);
        bimenameDAO.update(theBimename);
        darkhastBazkharid.setState(state);
        darkhastBazkharid.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    @Transactional
    public int saveDarkhasteTasvieyePishAzMoedeVam(DarkhastBazkharid darkhastBazkharid) {
        State state = stateDAO.findById(Constant.TASVIE_PISH_AZ_MOEDE_VAM_INITIAL_STATE);
        State stateBimename = stateDAO.findById(Constant.BIMENAME_LOCK_STATE);
        darkhastBazkharid.setState(state);
        Bimename theBimename = darkhastBazkharid.getBimename();
        theBimename.setState(stateBimename);
        bimenameDAO.update(theBimename);
        darkhastBazkharid.setTarikhDarkhast(DateUtil.getCurrentDate());
        bimenameDAO.saveDarkhastBazkharid(darkhastBazkharid);
        return darkhastBazkharid.getId();
    }

    public void updateDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid) {
        bimenameDAO.updateDarkhstBazkharid(darkhastBazkharid);
    }

    public List<DarkhastBazkharid> findAllDarkhastBazkharid() {
        return bimenameDAO.findAllDarkhastBazkharid();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<DarkhastBazkharid> findDarkhastKhesaratBeduneState()
    {
        return bimenameDAO.findDarkhastKhesaratBeduneState();
    }

    public void cleanChangeWithSerachField()
    {
        bimenameDAO.cleanChangeWithSerachField();
    }

    public DarkhastBazkharid findDarkhastBazkharidById(Integer darkhastBazkharidId) {
        return bimenameDAO.findDarkhastBazkharidById(darkhastBazkharidId);
    }

    public Darkhast findDarkhastKhesaratById(Integer khesaratId) {
        return bimenameDAO.findDarkhastByKhesaratId(khesaratId);
    }


    @Transactional
    public void transitKhesarat(User user, Long khesaratId, Integer transitionId, String logmessage) {
        try {
            Khesarat khesarat = khesaratService.findById(khesaratId);

            Transition transition = transitionDAO.findById(transitionId);
            khesarat.setState(transition.getStateEnd());

            transitionLogService.logKhesaratTransition(user, khesarat, transition, logmessage);
            //InsuranceServiceFactory.getWithoutSessionKhesaratService().updateKhesarat(khesarat);

            khesaratService.updateKhesarat(khesarat);

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Transactional
    public void transitDarkhast(User user, Integer darkhastId, Integer transitionId, String logmessage) {
        DarkhastBazkharid theDarkhast = bimenameDAO.findDarkhastBazkharidById(darkhastId);
        Transition transition = transitionDAO.findById(transitionId);
        theDarkhast.setState(transition.getStateEnd());

        transitionLogService.logDarkhastBazkharidTransition(user,theDarkhast,transition,logmessage);
        bimenameDAO.updateDarkhstBazkharid(theDarkhast);
    }

    public void updateZamaem(ZamaemDarkhast theZamime) {
        bimenameDAO.updateZamaem(theZamime);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveZamaemDarkhast(ZamaemDarkhast zamaemDarkhast) {
        bimenameDAO.saveZamaemDarkhast(zamaemDarkhast);
    }

    public ZamaemDarkhast findZamaemDarkhastById(int zamaemId) {
        return bimenameDAO.findZamaemDarkhastById(zamaemId);
    }

    public void saveElhaghiye(Elhaghiye elhaghiye) {
        bimenameDAO.saveElhaghiye(elhaghiye);
    }

    public void updateElhaghiye(Elhaghiye elhaghiye) {
        bimenameDAO.updateElhaghiye(elhaghiye);
    }

    public PaginatedListImpl<Elhaghiye> findAllElhaghiyes(PaginatedListImpl<Elhaghiye> pgList,Long userId) {
        return bimenameDAO.findAllElhaghiyes(pgList, userId);
    }

    public PaginatedListImpl<Elhaghiye> searchForElhaghiye(ElhaghiyeSearch elhaghiyeSearch, PaginatedListImpl<Elhaghiye> pgList,Long userId)
    {
        return bimenameDAO.searchForElhaghiye(elhaghiyeSearch, pgList, userId);
    }

    public Darkhast findDarkhastById(Integer id) {
        return bimenameDAO.findDarkhastById(id);
    }

    public List<State> findByStateMachine(String system)
    {
        return stateDAO.findByStateMachine(system);
    }

    public PaginatedListImpl<BahreMandiVM> findBahreMandi(PaginatedListImpl<BahreMandiVM> pgList, BahreMandiVM searchVM)
    {
        return bimenameDAO.findBahreMandi(pgList, searchVM);
    }

    public List<DarkhastBazkharid> findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day()
    {
        return bimenameDAO.findTasviePishAzMoedWithSttMontazerPardakhtAfter30Day();
    }

    public PaginatedListImpl<ElhaghieVM> elhaghieReport(PaginatedListImpl<ElhaghieVM> pgList, ElhaghieVM svm)
    {
        return bimenameDAO.elhaghieReport(pgList, svm);
    }

    public List<ListBimenameTaghirVM> getListBimenamehayeTaghirKarde(DarkhastTaghirat darkhastTaghirat) {
        // be ezaye darkhast taghir mige che bimenamehayee taghir mikonan (baraye elhaghie taghir ashkhas)
        List<PishnehadFieldChanges> changes = logService.getPishnehadChangesList(darkhastTaghirat.getOldPishnehad(), darkhastTaghirat.getNewPishnehad());
        ArrayList<ListBimenameTaghirVM> returnList = new ArrayList<ListBimenameTaghirVM>();
        boolean isBimeShode = OmrUtil.isElhaghieTaghirShakhs(changes, Shakhs.Role.BIMESHODE);
        boolean isBimeGozar = OmrUtil.isElhaghieTaghirShakhs(changes, Shakhs.Role.BIMEGOZAR);
        if(!isBimeGozar && !isBimeShode)
            return returnList;
        if(isBimeGozar && isBimeShode && darkhastTaghirat.getOldPishnehad().getBimeShode().getShakhs().getKodeMelliShenasayi().equals(
                darkhastTaghirat.getOldPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi()))
            isBimeGozar = false;
        if(isBimeShode) {
            returnList.addAll(getListBimenamehayeTaghirKardeForShakhs(darkhastTaghirat.getOldPishnehad().getBimeShode().getShakhs()
                    , darkhastTaghirat.getOldPishnehad().getBimename().getShomare(), OmrUtil.isElhaghieTaghirShakhsMali(changes), darkhastTaghirat));
        }
        if (isBimeGozar) {
            if(darkhastTaghirat.getNewPishnehad().getBimeGozar().getShakhs().getChangeWithSerach()==null)
            {
                List<ListBimenameTaghirVM> currentList = getListBimenamehayeTaghirKardeForShakhs(darkhastTaghirat.getOldPishnehad().getBimeGozar().getShakhs()
                        , darkhastTaghirat.getOldPishnehad().getBimename().getShomare(), OmrUtil.isElhaghieTaghirShakhsMali(changes),darkhastTaghirat);

                for (ListBimenameTaghirVM v : currentList) {
                    boolean found = false;
                    for (ListBimenameTaghirVM v2 : returnList) {
                        if (v2.getShomare().equals(v.getShomare())) {
                            found = true;
                        }
                    }
                    if (!found)
                        returnList.add(v);
                }
            }
        }
        return returnList;

    }

    public void deleteDarkhast(Darkhast darkhast)
    {
        bimenameDAO.delete(darkhast);
    }

    public void deleteDarkhastBazkharid(DarkhastBazkharid darkhastBazkharid)
    {
        bimenameDAO.delete(darkhastBazkharid);
    }

    @Transactional
    public String emalElhaghiyeTaghirat(DarkhastTaghirat darkhastTaghirat, Elhaghiye elhaghiye, User user, boolean mohasebeMablaghElhaghie) throws BimeNaamehVaSarmayehGozari.CustomValidationException {

        boolean shouldSave = true;
        if (mohasebeMablaghElhaghie || Constant.DEV_testElhaghieTaghirat)
            shouldSave = false;

        // variables
        String logString = "";
        Bimename currentBimename = darkhastTaghirat.getNewPishnehad().getBimename();
        List<Ghest> currentGhestList = asnadeSodorService.findAllGhests(currentBimename.getId());
        Collections.sort(currentGhestList);
        List<Credebit> eslahAghsatList = asnadeSodorService.getCredebitByTypeForBimename(Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT, currentBimename.getShomare());
        String tarikhSodorElhaghie = elhaghiye.getCreatedDate();
        boolean daemhaInTaghsit = true;
        if(!shouldSave) {
            tarikhSodorElhaghie = DateUtil.getCurrentDate();
            daemhaInTaghsit = false;
        }
        String tarikhAsarElhaghie = elhaghiye.getTarikhAsar();

        long elamBeMaliJadid = 0L;

        int haghBimeAvvalieId = 0;

        int currentHaghBimeAvvalie = 0;
        if(darkhastTaghirat.getOldPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal() != null) {
            currentHaghBimeAvvalie = Integer.parseInt(darkhastTaghirat.getOldPishnehad().getEstelam().getMablagh_seporde_ebtedaye_sal().replaceAll(",", ""));
        }
        if(currentHaghBimeAvvalie > 0) {
            for (Elhaghiye el : currentBimename.getElhaghiyehaDaem()) {
                // elhaghie marboot be ezafe kardane in hagh bime avvalie
                if (el.getElhaghiyeType().equals(Elhaghiye.ElhaghiyeType.TAGHYIRAT) && el.getDarkhast().getDarkhastTaghirat().getNewPishnehad().getId().equals(darkhastTaghirat.getOldPishnehad().getId())) {
                    // agar tarikh asar in elhaghie, az elhaghie haghbime avvalie ghabli badtar bud, engar haghbime avvalie nadarim
                    if(DateUtil.isGreaterThanOrEqual(tarikhAsarElhaghie, DateUtil.addYear(el.getTarikhAsar(), 1))) {
                        currentHaghBimeAvvalie = 0;
                    }
                }
            }
        }

        String maxSodorVaAsar = tarikhSodorElhaghie;
        if(DateUtil.isGreaterThan(tarikhAsarElhaghie, tarikhSodorElhaghie))
            maxSodorVaAsar = tarikhAsarElhaghie;


        if(!shouldSave)
            logString += String.format("*** SIMULATION ***") + "\r\n";
        logString += String.format("Sodor Elhaghiye: %s ", tarikhSodorElhaghie) + "\r\n";
        logString += String.format("Asar Elhaghiye: %s ", tarikhAsarElhaghie) + "\r\n";
        logString += String.format("Sodor Bimename: %s ", currentBimename.getTarikhSodour()) + "\r\n";
        logString += String.format("Today: %s ", tarikhSodorElhaghie) + "\r\n";


        String fardayeTarikhSodorElhaghie = DateUtil.addDays(tarikhSodorElhaghie, 1);
        LinkedList<Flow> flowsHazineha = new LinkedList<Flow>();
        int saleBimeiElhaghie = darkhastTaghirat.getWhenApply(); // ebtedaye in sal
        // elam be mali mablagh elhaghie
        String amountElhaghiye = elhaghiye.getMablagh();
        if (amountElhaghiye!=null && !amountElhaghiye.equals("0") && shouldSave) {
            Credebit credebit = new Credebit(amountElhaghiye, sequenceService.nextShenaseCredebit(), currentBimename, null, Credebit.CredebitType.ELHAGHIE_BARGASHTI);
            if (amountElhaghiye.indexOf('-') < 0) {
                credebit.setCredebitType(Credebit.CredebitType.ELHAGHIE_EZAFI);
            }
            if (shouldSave)
                asnadeSodorService.saveCredebit(credebit);
            logString += String.format("Mablagh elhaghie (%s) zakhire shod.", amountElhaghiye) + "\r\n";
        }
        int etebar = 0;
        for (Ghest g : currentGhestList) {
            if (DateUtil.isGreaterThanOrEqual(g.getSarresidDate(), maxSodorVaAsar) && g.getTarikhPardakht().isEmpty()) {
                // aghsat pardakht nashode ba sarresid bad az max(sodor,asar) elhaghie hazf mishavand
                if (shouldSave)
                    asnadeSodorService.hazfeGhest(g.getId());
                logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) hazf shod.", g.getGhestBandi().getSaleBimei(), g.getCredebit().getAmount_long(), g.getSarresidDate()) + "\r\n";
            }
            // implies  tarikhSodorElhaghie > tarikhAsarElhaghie
            else if (DateUtil.isGreaterThanOrEqual(tarikhSodorElhaghie, g.getSarresidDate()) && DateUtil.isGreaterThanOrEqual(g.getSarresidDate(), tarikhAsarElhaghie)) {
                // ghesti ke beyne tarikh asar va sodor elhaghie bashad dast nemikhorad va mablaghe an gerefte mishavad
                etebar += g.getCredebit().getAmount_long();
                elamBeMaliJadid += g.getCredebit().getAmount_long();
                logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) be etebar ezafe shod. Etebar: %s", g.getGhestBandi().getSaleBimei(),
                        g.getCredebit().getAmount_long(), g.getSarresidDate(), etebar) + "\r\n";
                // sabt hazine be onvane item mosbat dar cashflow hazineha
                Flow flow = new Flow(g.getMajmooHazineha(), Flow.Type.HAZINEHA_MOSBAT, g.getSarresidDate());
                flowsHazineha.add(flow);
                logString += String.format("Hazine (%s, %s) be cashflow ezafe shod.", g.getMajmooHazineha(), g.getSarresidDate()) + "\r\n";
                // bakhshe sanadkhorde az etebarat elhaghiehaye gozashte ke bedehi motenazer anha az tarikh asar elhaghie be bad ast
                for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehiSet()) {
                    if (ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)) {
                        etebar -= ks.getAmount_long();
                        logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) daraye sanad etebar elhaghie(%s) ast. Etebar: %s", g.getGhestBandi().getSaleBimei(),
                                g.getCredebit().getAmount_long(), g.getSarresidDate(), ks.getAmount(), etebar) + "\r\n";
                    }
                }
            }
            // aghsat pardakhti ati -> max(sodor, asar)
            else if (DateUtil.isGreaterThanOrEqual(g.getSarresidDate(), maxSodorVaAsar) && !g.getTarikhPardakht().isEmpty()) {
                // hazinehayeshan khonsa mishavad
                boolean ghablanKhonsaShode = false;
                elamBeMaliJadid += g.getCredebit().getAmount_long();
                for (Credebit c : eslahAghsatList) {
                    if (c.getAmount_long().equals(g.getMajmooHazineha()) && c.getDateFish().equals(g.getSarresidDate())) {
                        ghablanKhonsaShode = true;
                        logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) ghablan khonsa shode.", g.getGhestBandi().getSaleBimei(),
                                g.getCredebit().getAmount_long(), g.getSarresidDate()) + "\r\n";
                    }
                }
                if (!ghablanKhonsaShode) {
                    Credebit khonsaCredebit = new Credebit(Long.toString(g.getMajmooHazineha()), sequenceService.nextShenaseCredebit(), currentBimename
                            , null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
                    khonsaCredebit.setDescription("Jobrane Hazine, ghest.id = " + g.getId());
                    khonsaCredebit.setDateFish(g.getSarresidDate());
                    if (shouldSave)
                        asnadeSodorService.saveCredebit(khonsaCredebit);
                    logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) khonsa shod.", g.getGhestBandi().getSaleBimei(),
                            g.getCredebit().getAmount_long(), g.getSarresidDate()) + "\r\n";
                    etebar += g.getCredebit().getAmount_long();
                    logString += String.format("Ghest (sal:%s meghdar_bardasht:%s sarresid:%s) bardasht shod. Etebar: %s", g.getGhestBandi().getSaleBimei(),
                            g.getCredebit().getAmount_long() - g.getCredebit().getRemainingAmount_long(), g.getSarresidDate(), etebar) + "\r\n";
                    // bakhshe sanadkhorde az etebarat elhaghiehaye gozashte ke bedehi motenazer anha az tarikh asar elhaghie be bad ast
                    for (KhateSanad ks : g.getCredebit().getKhateSanadsBedehiSet()) {
                        if (ks.getEtebarCredebit().getCredebitType().equals(Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR)) {
                            etebar -= ks.getAmount_long();
                            logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) daraye sanad etebar elhaghie(%s) ast. Etebar: %s", g.getGhestBandi().getSaleBimei(),
                                    g.getCredebit().getAmount_long(), g.getSarresidDate(), ks.getAmount(), etebar) + "\r\n";
                        }
                    }
                }
            }
        }
        // sakhtan aghsate jadid bad az sodor elhaghie be tore kamel
        for (GhestBandi gb : currentBimename.getGhestBandiList()) {
            int saleBimeiGb = gb.getSaleBimeiInt();
            if (saleBimeiGb >= saleBimeiElhaghie) {
                List<TaghsitReport> taghsitReportJadid = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), saleBimeiGb
                        , darkhastTaghirat.getNewPishnehad().getBimename().getTarikhShorou(), daemhaInTaghsit);
                List<Ghest> ghestListGb = new ArrayList<Ghest>();
                List<GhestBandi> gbList = new ArrayList<GhestBandi>();
                List<Credebit> credebitListGb = new ArrayList<Credebit>();
                for (TaghsitReport report : taghsitReportJadid) {
                    Ghest ghest = new Ghest();
                    ghest.setSarresidDate(report.getTarikh());
                    ghest.setHaghBimeFotYekja(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimeKhaalesFotYekja())));
                    ghest.setHaghBimePoosheshhayeEzafi(NumberFormat.getNumberInstance().format(report.getHaghBimePusheshHaayeEzaafi()));
                    ghest.setMaliat(NumberFormat.getNumberInstance().format(report.getMaliat()));
                    ghest.setHazineBimegari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineBimeGari())));
                    ghest.setHazineEdari(NumberFormat.getNumberInstance().format(Math.round(report.getHazineEdari())));
                    ghest.setHazineKarmonz(NumberFormat.getNumberInstance().format(Math.round(report.getKaarmozd())));
                    ghest.setHazineVosoul(NumberFormat.getNumberInstance().format(Math.round(report.getHazineVosul())));
                    ghest.setSarmayeFotBeHarEllat(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotBehHarEllat())));
                    ghest.setSarmayeFotDarAsarHadeseh(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayeFotDarAsarHaadeseh())));
                    ghest.setSarmayePoosheshEzafiAmraazKhaas(NumberFormat.getNumberInstance().format(Math.round(report.getSarmaayePusheshEzaafiAmraazKhaas())));
                    ghest.setHazineTaghsit(NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).substring(0, Math.min(5, NumberFormat.getNumberInstance().format(report.getZaribNahveyePardakht()).length())));
                    ghest.setKarmozdAmount(NumberFormat.getNumberInstance().format(report.getKaarmozd()));
                    ghest.setHaghBimePoosheshAmraz(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshAmraz())));
                    ghest.setHaghBimePoosheshHadese(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshHadese())));
                    ghest.setHaghBimePoosheshNaghsOzv(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshNaghsOzv())));
                    ghest.setHaghBimePoosheshMoafiat(NumberFormat.getNumberInstance().format(Math.round(report.getHaghBimePoosheshMoafiat())));
                    ghest.setGhestBandi(gb);
                    if (DateUtil.isGreaterThanOrEqual(report.getTarikh(), tarikhSodorElhaghie)) {
                        // aghsate bad az sodore elhaghie tolid mishavand
                        // agar hagh bime avvalie dasht sanad nabayad bokhorad
                        if (report.getHaghBimeAvvalie() > 0) {
                            // ghest avvalie
                            Ghest ghestAvvalie = new Ghest();
                            // find ghestbandi for this ghest
                            int saleBimeiGhest = DateUtil.getBimeYear(report.getTarikh(), currentBimename.getTarikhShorou());
                            for (GhestBandi gb_find : currentBimename.getGhestBandiList()) {
                                if (saleBimeiGhest == gb_find.getSaleBimeiInt()) {
                                    ghestAvvalie.setGhestBandi(gb_find);
                                }
                            }
                            ghestAvvalie.setSarresidDate(report.getTarikh());
                            ghestAvvalie.setHaghBimeFotYekja("0");
                            ghestAvvalie.setHaghBimePoosheshhayeEzafi("0");
                            ghestAvvalie.setMaliat("0");
                            ghestAvvalie.setHazineBimegari("0");
                            ghestAvvalie.setHazineEdari("0");
                            ghestAvvalie.setHazineKarmonz("0");
                            ghestAvvalie.setHazineVosoul("0");
                            ghestAvvalie.setSarmayeFotBeHarEllat("0");
                            ghestAvvalie.setSarmayeFotDarAsarHadeseh("0");
                            ghestAvvalie.setSarmayePoosheshEzafiAmraazKhaas("0");
                            ghestAvvalie.setHazineTaghsit("0");
                            ghestAvvalie.setKarmozdAmount("0");
                            ghestAvvalie.setHaghBimePoosheshAmraz("0");
                            ghestAvvalie.setHaghBimePoosheshHadese("0");
                            ghestAvvalie.setHaghBimePoosheshNaghsOzv("0");
                            ghestAvvalie.setHaghBimePoosheshMoafiat("0");
                            int ghestAmountAvvalie = Math.abs((int) report.getHaghBimeAvvalie()) - currentHaghBimeAvvalie;
                            Credebit credebit = new Credebit(Integer.toString(ghestAmountAvvalie), sequenceService.nextShenaseCredebit(), currentBimename, darkhastTaghirat.getNewPishnehad(), Credebit.CredebitType.GHEST);
                            credebit.setGhest(ghestAvvalie);
                            credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120", credebit.getRemainingAmount_long()));
                            ghestAvvalie.setCredebit(credebit);
                            ghestAvvalie.setKarmozdVosuli(0L);
                            ghestAvvalie.setKarmozdPoosheshEzafi(0L);
                            elamBeMaliJadid += ghestAmountAvvalie;
                            if (shouldSave) {
                                asnadeSodorService.saveGhest(ghestAvvalie);
                                asnadeSodorService.saveCredebit(credebit);
                                haghBimeAvvalieId = ghestAvvalie.getId();
                            }
                            logString += String.format("Ghest HaghBime Avvalie (sal:%s meghdar:%s sarresid:%s) tolid shod. Etebar: %s", ghestAvvalie.getGhestBandi().getSaleBimei(),
                                    ghestAmountAvvalie, ghestAvvalie.getSarresidDate(), etebar) + "\r\n";
                            // --- baghie ghest ---
                            int ghestAmount = (int) report.getHaghBimePardaakhtiSaal() - ghestAmountAvvalie;
                            Credebit credebitBaghie = new Credebit(Integer.toString(ghestAmount), sequenceService.nextShenaseCredebit(), currentBimename, darkhastTaghirat.getNewPishnehad(), Credebit.CredebitType.GHEST);
                            credebitBaghie.setDateFish(ghest.getSarresidDate());
                            credebitBaghie.setGhest(ghest);
                            credebitBaghie.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120", credebit.getRemainingAmount_long()));
                            ghest.setCredebit(credebitBaghie);
                            ghest.setKarmozdVosuli(asnadeSodorService.getKarmozdVosul(currentBimename.getTarikhSodour(), darkhastTaghirat.getNewPishnehad().getTarh(), credebit.getAmount_long(), ghest.getMaliatLong(), ghest.getHaghBimePoosheshhayeEzafiLong(), false, null));
                            ghest.setKarmozdPoosheshEzafi(asnadeSodorService.getKarmozdPoosheshEzafi(ghest.getHaghBimePoosheshhayeEzafiLong(), darkhastTaghirat.getNewPishnehad().getTarh(), darkhastTaghirat.getNewPishnehad().getBimename().getTarikhSodour()));
                            ghestListGb.add(ghest);
                            gbList.add(ghest.getGhestBandi());
                            credebitListGb.add(credebitBaghie);
                            elamBeMaliJadid += ghestAmount;
                            logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) tolid shod.", ghest.getGhestBandi().getSaleBimei(),
                                    ghest.getCredebit().getAmount_long(), ghest.getSarresidDate()) + "\r\n";
                        } else {
                            int ghestAmount = (int) report.getHaghBimePardaakhtiSaal();
                            Credebit credebit = new Credebit(Integer.toString(ghestAmount), sequenceService.nextShenaseCredebit(), currentBimename, darkhastTaghirat.getNewPishnehad(), Credebit.CredebitType.GHEST);
                            credebit.setDateFish(ghest.getSarresidDate());
                            credebit.setGhest(ghest);
                            credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120", credebit.getRemainingAmount_long()));
                            ghest.setCredebit(credebit);
                            ghest.setKarmozdVosuli(asnadeSodorService.getKarmozdVosul(currentBimename.getTarikhSodour(), darkhastTaghirat.getNewPishnehad().getTarh(), credebit.getAmount_long(), ghest.getMaliatLong(), ghest.getHaghBimePoosheshhayeEzafiLong(), false, null));
                            ghest.setKarmozdPoosheshEzafi(asnadeSodorService.getKarmozdPoosheshEzafi(ghest.getHaghBimePoosheshhayeEzafiLong(), darkhastTaghirat.getNewPishnehad().getTarh(), darkhastTaghirat.getNewPishnehad().getBimename().getTarikhSodour()));
                            ghestListGb.add(ghest);
                            gbList.add(ghest.getGhestBandi());
                            credebitListGb.add(credebit);
                            elamBeMaliJadid += ghestAmount;
                            logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) tolid shod.", ghest.getGhestBandi().getSaleBimei(),
                                    ghest.getCredebit().getAmount_long(), ghest.getSarresidDate()) + "\r\n";
                        }
                    } else {
                        // agar hagh bime avvalie dasht ghest jozve bedehihaye elhaghie hesab nashavad va khodash ghest shavad (asar be ghabl)
                        // baghie mablagh (ghest hamrah ba an) bayad mesle baghie hesab shavad
                        if(report.getHaghBimeAvvalie() > 0) {
                            // hazine
                            Flow flow = new Flow(ghest.getMajmooHazineha(), Flow.Type.HAZINEHA_MANFI, ghest.getSarresidDate());
                            flowsHazineha.add(flow);
                            logString += String.format("Hazine HaghBime Avvalie (+ghest) (-%s, %s) be cashflow ezafe shod.", ghest.getMajmooHazineha(), ghest.getSarresidDate()) + "\r\n";
                            logString += String.format("HaghBime Avvalie feli in sal asar: %s", currentHaghBimeAvvalie) + "\r\n";
                            // ghest avvalie
                            Ghest ghestAvvalie = new Ghest();
                            // find ghestbandi for this ghest
                            int saleBimeiGhest = DateUtil.getBimeYear(maxSodorVaAsar, currentBimename.getTarikhShorou());
                            for (GhestBandi gb_find : currentBimename.getGhestBandiList()) {
                                if (saleBimeiGhest == gb_find.getSaleBimeiInt()) {
                                    ghestAvvalie.setGhestBandi(gb_find);
                                }
                            }
                            ghestAvvalie.setSarresidDate(maxSodorVaAsar);
                            ghestAvvalie.setHaghBimeFotYekja("0");
                            ghestAvvalie.setHaghBimePoosheshhayeEzafi("0");
                            ghestAvvalie.setMaliat("0");
                            ghestAvvalie.setHazineBimegari("0");
                            ghestAvvalie.setHazineEdari("0");
                            ghestAvvalie.setHazineKarmonz("0");
                            ghestAvvalie.setHazineVosoul("0");
                            ghestAvvalie.setSarmayeFotBeHarEllat("0");
                            ghestAvvalie.setSarmayeFotDarAsarHadeseh("0");
                            ghestAvvalie.setSarmayePoosheshEzafiAmraazKhaas("0");
                            ghestAvvalie.setHazineTaghsit("0");
                            ghestAvvalie.setKarmozdAmount("0");
                            ghestAvvalie.setHaghBimePoosheshAmraz("0");
                            ghestAvvalie.setHaghBimePoosheshHadese("0");
                            ghestAvvalie.setHaghBimePoosheshNaghsOzv("0");
                            ghestAvvalie.setHaghBimePoosheshMoafiat("0");
                            int ghestAmountAvvalie = Math.abs((int) report.getHaghBimeAvvalie()) - currentHaghBimeAvvalie;
                            Credebit credebit = new Credebit(Integer.toString(ghestAmountAvvalie), sequenceService.nextShenaseCredebit(), currentBimename, darkhastTaghirat.getNewPishnehad(), Credebit.CredebitType.GHEST);
                            credebit.setGhest(ghestAvvalie);
                            credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120", credebit.getRemainingAmount_long()));
                            ghestAvvalie.setCredebit(credebit);
                            ghestAvvalie.setKarmozdVosuli(0L);
                            ghestAvvalie.setKarmozdPoosheshEzafi(0L);
                            elamBeMaliJadid += ghestAmountAvvalie;
                            if (shouldSave) {
                                asnadeSodorService.saveGhest(ghestAvvalie);
                                asnadeSodorService.saveCredebit(credebit);
                                haghBimeAvvalieId = ghestAvvalie.getId();
                            }
                            logString += String.format("Ghest HaghBime Avvalie (sal:%s meghdar:%s sarresid:%s) tolid shod. Etebar: %s", ghestAvvalie.getGhestBandi().getSaleBimei(),
                                    ghestAmountAvvalie, ghestAvvalie.getSarresidDate(), etebar) + "\r\n";
                            // --- baghie ghest ---
                            etebar -= (int) report.getHaghBimePardaakhtiSaal() - ghestAmountAvvalie;
                            logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) tolid nashod. Etebar: %s", ghest.getGhestBandi().getSaleBimei(),
                                    (int) report.getHaghBimePardaakhtiSaal() - ghestAmountAvvalie, ghest.getSarresidDate(), etebar) + "\r\n";
                            // hazinehaye in ghest balatar dar cashflow ezafe shodand
                        } else {
                            // ghesti ke bayad migereftim vali tolid nemishavad, chon sarresid an ghabl az tarikh sodor elhaghie ast.
                            etebar -= (int) report.getHaghBimePardaakhtiSaal();
                            logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) tolid nashod. Etebar: %s", ghest.getGhestBandi().getSaleBimei(),
                                    report.getHaghBimePardaakhtiSaal(), ghest.getSarresidDate(), etebar) + "\r\n";
                            // sabte hazineha be onvane manfi dar cashflow hazineha
                            Flow flow = new Flow(ghest.getMajmooHazineha(), Flow.Type.HAZINEHA_MANFI, ghest.getSarresidDate());
                            flowsHazineha.add(flow);
                            logString += String.format("Hazine (-%s, %s) be cashflow ezafe shod.", ghest.getMajmooHazineha(), ghest.getSarresidDate()) + "\r\n";
                        }
                    }
                }
                if (shouldSave) {
                    asnadeSodorService.saveGhestList(ghestListGb);
                    asnadeSodorService.saveCredebitList(credebitListGb);

                }
            }
        }
        // beyne tarikh asar va tarikh sodor, beyne aghsati ke bayad mibudand (bad az elhaghie) va unayi ke hastan ekhtelaf riali migirim
        // va ghest ya etebar misazim, agar etebar bud sanad mizanim ba aghsat
        currentGhestList = asnadeSodorService.findAllGhests(currentBimename.getId());
        Collections.sort(currentGhestList);
        int saleBimeiGhestYaEtebarElhaghie = DateUtil.getBimeYear(maxSodorVaAsar, currentBimename.getTarikhShorou());
        for (GhestBandi gb : currentBimename.getGhestBandiList()) {
            if (saleBimeiGhestYaEtebarElhaghie == gb.getSaleBimeiInt()) {
                if (etebar < 0) {
                    Ghest ghest = new Ghest();
                    ghest.setGhestBandi(gb);
                    ghest.setSarresidDate(maxSodorVaAsar);
                    ghest.setHaghBimeFotYekja("0");
                    ghest.setHaghBimePoosheshhayeEzafi("0");
                    ghest.setMaliat("0");
                    ghest.setHazineBimegari("0");
                    ghest.setHazineEdari("0");
                    ghest.setHazineKarmonz("0");
                    ghest.setHazineVosoul("0");
                    ghest.setSarmayeFotBeHarEllat("0");
                    ghest.setSarmayeFotDarAsarHadeseh("0");
                    ghest.setSarmayePoosheshEzafiAmraazKhaas("0");
                    ghest.setHazineTaghsit("0");
                    ghest.setKarmozdAmount("0");
                    ghest.setHaghBimePoosheshAmraz("0");
                    ghest.setHaghBimePoosheshHadese("0");
                    ghest.setHaghBimePoosheshNaghsOzv("0");
                    ghest.setHaghBimePoosheshMoafiat("0");
                    int ghestAmount = Math.abs(etebar);
                    Credebit credebit = new Credebit(Integer.toString(ghestAmount), sequenceService.nextShenaseCredebit(), currentBimename, darkhastTaghirat.getNewPishnehad(), Credebit.CredebitType.GHEST);
                    credebit.setGhest(ghest);
                    credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120",credebit.getRemainingAmount_long()));
                    ghest.setCredebit(credebit);
                    ghest.setKarmozdVosuli(0L);
                    ghest.setKarmozdPoosheshEzafi(0L);
                    elamBeMaliJadid += ghestAmount;
                    if (shouldSave) {
                        asnadeSodorService.saveGhest(ghest);
                        asnadeSodorService.saveCredebit(credebit);
                    }
                    logString += String.format("Ghest (sal:%s meghdar:%s sarresid:%s) baraye elhaghie tolid shod.", ghest.getGhestBandi().getSaleBimei(),
                            ghestAmount, ghest.getSarresidDate()) + "\r\n";
                } else if (etebar > 0) {
                    Credebit credebit = new Credebit(Integer.toString(etebar), sequenceService.nextShenaseCredebit(), currentBimename, null, Credebit.CredebitType.ELHAGHIE_BARGASHTI_ETEBAR);
                    credebit.setDateFish(maxSodorVaAsar);
                    credebit.setShomareMoshtari(sequenceService.generateNextShomareMoshtari(currentBimename.getPishnehad().getKarshenas().getMojtamaSodoor(), "9", "120",credebit.getRemainingAmount_long()));
                    if (shouldSave)
                        asnadeSodorService.saveCredebit(credebit);
                    logString += String.format("Etebar (meghdar:%s tarikh:%s) baraye elhaghie tolid shod.", etebar,
                            maxSodorVaAsar) + "\r\n";
                    // be andaze etebar ijad shode baraye elhaghie bardasht mikonim
                    // UPDATE: chon type ELHAGHIE_BARGASHTI_ETEBAR dar andukhte hesab nemishavad, niazi be bardasht nist
//                    Credebit bardashtCredebit = new Credebit(Long.toString(etebar), sequenceService.nextShenaseCredebit(), currentBimename,
//                            null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
//                    bardashtCredebit.setDescription("Bardasht Etebar Elhaghie, darkhastTaghirat.id = " + darkhastTaghirat.getId());
//                    bardashtCredebit.setDateFish(fardayeTarikhSodorElhaghie);
//                    if (!Constant.DEV_testElhaghieTaghirat)
//                        asnadeSodorService.saveCredebit(bardashtCredebit);
//                    logString += String.format("Etebar (meghdar:%s tarikh:%s) bardasht shod.", etebar,
//                            fardayeTarikhSodorElhaghie) + "\r\n";
                    ArrayList<Credebit> bedehiList = new ArrayList<Credebit>();
                    do
                    {
                        for (Ghest g : currentGhestList) {
                            if (g.getCredebit().getRemainingAmount_long() > 0 && !g.getId().equals(haghBimeAvvalieId)
                                    // ghesti sanad mikhorad ba etebar elhaghie ke bad az tarikh asar elhaghie bashad
                                    && DateUtil.isGreaterThanOrEqual(g.getSarresidDate(), tarikhAsarElhaghie)) {
                                bedehiList.add(g.getCredebit());
                                etebar -= g.getCredebit().getRemainingAmount_long();
                                logString += String.format("Sanad baraye (sal:%s meghdar:%s sarresid:%s). Etebar: %s", g.getGhestBandi().getSaleBimei(),
                                        g.getCredebit().getRemainingAmount_long(), g.getSarresidDate(), etebar) + "\r\n";
                                if (etebar <= 0)
                                    break;
                            }
                        }
                        //agar bad az sanad zadane bedehiHa etebar 0 nashode bud sale badi ro taghsit mikonim
                        if(etebar > 0)
                        {
                            int saleBimei= OmrUtil.getToGhestbandiYear(currentBimename);
                            List<TaghsitReport> taghsitReport = asnadeSodorService.mohasebeyeAghsat(darkhastTaghirat.getNewPishnehad(), saleBimei, currentBimename.getTarikhShorou(), daemhaInTaghsit);
                            for(TaghsitReport tr : taghsitReport) {
                                elamBeMaliJadid += (int) tr.getHaghBimePardaakhtiSaal();
                            }
                            // todo: mablagh elhaghie dar halati ke niaz be taghsit 2 sal bashe eshtebah mishe
                            if(shouldSave)
                                currentGhestList = asnadeSodorService.saveGhestbandi(darkhastTaghirat.getNewPishnehad(), saleBimei, taghsitReport,user);
                        }

                    } while (etebar > 0 && shouldSave);


                    ArrayList<Credebit> etebarList = new ArrayList<Credebit>();
                    etebarList.add(credebit);
                    if (shouldSave)
                        asnadeSodorService.sabteSanad(bedehiList, etebarList, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, user, true);
                }
            }
        }
        // ezafe kardan jobran hazinehaye ghabl be cashflow
        for (Credebit c : eslahAghsatList) {
            if (c.getCredebitTypeDesc() != null && c.getCredebitTypeDesc().equals(Credebit.CredebitTypeDesc.JOBRAN_HAZINEHA)) {
                Flow flow = new Flow(-1 * c.getAmount_long(), Flow.Type.HAZINEHA, c.getDateFish());
                flowsHazineha.add(flow);
                logString += String.format("Ekhtelaf Hazine (%s, %s) be cashflow ezafe shod.", -1 * c.getAmount_long(), c.getDateFish()) + "\r\n";
            }
        }
        // zakhire sazi cash flow hazineha
        CashFlow hesabHazineha = new CashFlow();
        hesabHazineha.setFlows(flowsHazineha);
        double hazinehaMaBe = hesabHazineha.tajmi(null, fardayeTarikhSodorElhaghie, 1.15);
        if (hazinehaMaBe != 0) {
            Credebit maBeHazineha = new Credebit(Long.toString(Math.round(hazinehaMaBe)), sequenceService.nextShenaseCredebit(),
                    currentBimename, null, Credebit.CredebitType.TAGHIRAT_ESLAH_AGHSAT);
            maBeHazineha.setCredebitTypeDesc(Credebit.CredebitTypeDesc.JOBRAN_HAZINEHA);
            maBeHazineha.setDateFish(fardayeTarikhSodorElhaghie);
            if (shouldSave)
                asnadeSodorService.saveCredebit(maBeHazineha);
        }
        logString += String.format("Ma be tafavot hazineha (meghdar:%s tarikh:%s) zakhire shod.", hazinehaMaBe,
                fardayeTarikhSodorElhaghie) + "\r\n";
        if(shouldSave)
            return logString;
        else {
            final Logger logger = LoggerFactory.getLogger(DarkhastAction.class);
            logger.info(logString);
            return Long.toString(elamBeMaliJadid);
        }
    }

    private List<ListBimenameTaghirVM> getListBimenamehayeTaghirKardeForShakhs(Shakhs shakhs, String currentBimenameShomare, boolean isElhaghieTaghirShakhsMali,DarkhastTaghirat darkhastTaghirat) {
        ArrayList<ListBimenameTaghirVM> returnList = new ArrayList<ListBimenameTaghirVM>();
        List<Pishnehad> pishnehadListBimeGozar = shakhsService.findAllPishnehadsForShakhs(shakhs.getKodeMelliShenasayi(), Shakhs.Role.BIMEGOZAR, darkhastTaghirat.getDarkhast() != null && darkhastTaghirat.getDarkhast().getElhaghiyeList() != null ? darkhastTaghirat.getDarkhast().getElhaghiyeList() : null);
        List<Pishnehad> pishnehadListBimeShode = shakhsService.findAllPishnehadsForShakhs(shakhs.getKodeMelliShenasayi(), Shakhs.Role.BIMESHODE, darkhastTaghirat.getDarkhast() != null && darkhastTaghirat.getDarkhast().getElhaghiyeList() != null ? darkhastTaghirat.getDarkhast().getElhaghiyeList() : null);
        if (darkhastTaghirat.getState().getId().equals(9180) || darkhastTaghirat.getState().getId().equals(9190) || darkhastTaghirat.getState().getId().equals(9200))
        {
            pishnehadListBimeGozar=new ArrayList<Pishnehad>();
            pishnehadListBimeShode=new ArrayList<Pishnehad>();
             for(Elhaghiye el : darkhastTaghirat.getDarkhast().getElhaghiyeList())
             {
                 if(el.getOldPishnehad()!=null)
                 {
                    if(el.getOldPishnehad().getBimeGozar().getShakhs().getKodeMelliShenasayi().equals(shakhs.getKodeMelliShenasayi()))
                        pishnehadListBimeGozar.add(el.getOldPishnehad());
                    if(el.getOldPishnehad().getBimeShode().getShakhs().getKodeMelliShenasayi().equals(shakhs.getKodeMelliShenasayi()))
                        pishnehadListBimeShode.add(el.getOldPishnehad());
                 }
             }
        }

        String shomareBimenameFeli = currentBimenameShomare;
        for (Pishnehad p : pishnehadListBimeGozar) {
            ListBimenameTaghirVM bt = new ListBimenameTaghirVM();
            bt.setShakhsRole("بیمه گذار");
            bt.setCheckResult(true);
            bt.setTaghirMohasebati(false);
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes")) {
                bt.setShomare(p.getBimename().getShomare());
                bt.setState(p.getBimename().getState().getStateName());
                bt.setBimename(p.getBimename());
            } else {
                bt.setShomare(p.getRadif());
                bt.setState(p.getState().getStateName());
                bt.setBimename(null);
            }
            if (p.getBimename() == null || !(p.getBimename().getShomare().equals(shomareBimenameFeli)))
                returnList.add(bt);
        }
        for (Pishnehad p : pishnehadListBimeShode) {
            ListBimenameTaghirVM bt = new ListBimenameTaghirVM();
            bt.setShakhsRole("بیمه شده");
            bt.setCheckResult(true); //todo
            if (isElhaghieTaghirShakhsMali)
                bt.setTaghirMohasebati(true);
            else
                bt.setTaghirMohasebati(false);
            if (p.getBimename() != null && p.getBimename().getReadyToShow().equals("yes")) {
                bt.setShomare(p.getBimename().getShomare());
                bt.setState(p.getBimename().getState().getStateName());
                bt.setBimename(p.getBimename());
            } else {
                bt.setShomare(p.getRadif());
                bt.setState(p.getState().getStateName());
                bt.setBimename(null);
            }
            if (p.getBimename() == null || !p.getBimename().getShomare().equals(shomareBimenameFeli))
            {
                boolean isNewBimename=true;
                for(ListBimenameTaghirVM btVM : returnList)
                {
                    if(btVM.getShomare().equals(bt.getShomare()))
                    {
                        btVM.setShakhsRole("بیمه شده | بیمه گذار");
                        isNewBimename=false;
                        break;
                    }
                }
                if(isNewBimename)
                    returnList.add(bt);
            }
        }
        return returnList;
    }


    public BimenameDAO getBimenameDAO() {
        return bimenameDAO;
    }

    public void setBimenameDAO(BimenameDAO bimenameDAO) {
        this.bimenameDAO = bimenameDAO;
    }

    public StateDAO getStateDAO() {
        return stateDAO;
    }

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    public TransitionDAO getTransitionDAO() {
        return transitionDAO;
    }

    public void setTransitionDAO(TransitionDAO transitionDAO) {
        this.transitionDAO = transitionDAO;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public UserDAO getUserDAO()
    {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public IKhesaratService getKhesaratService() {
        return khesaratService;
    }

    public IPishnehadService getPishnehadService()
    {
        return pishnehadService;
    }

    public void setPishnehadService(IPishnehadService pishnehadService)
    {
        this.pishnehadService = pishnehadService;
    }

    public void setKhesaratService(IKhesaratService khesaratService) {
        this.khesaratService = khesaratService;
    }

    public void setShakhsService(IShakhsService shakhsService) {
        this.shakhsService = shakhsService;
    }

    public ILogService getLogService() {
        return logService;
    }

    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService) {
        this.asnadeSodorService = asnadeSodorService;
    }
}
