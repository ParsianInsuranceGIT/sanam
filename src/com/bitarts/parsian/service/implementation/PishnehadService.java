package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.dao.*;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DasteSerial;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.bimename.Serial;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.log.TransitionLog;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.model.transitionwise.PezeshkSabtNazar;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.util.OmrUtil;
import com.bitarts.parsian.viewModel.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 5:59 PM
 */
public class PishnehadService implements IPishnehadService {
    private BimeGozarDAO bimeGozarDAO;
    private BimeShodeDAO bimeShodeDAO;
    private AddressDAO addressDAO;
    private ForoshandeDAO foroshandeDAO;
    private EstelamDAO estelamDAO;
    private SavabegheBimeiDAO savabegheBimeiDAO;
    private SoalateOmomiAzBimeShodeDAO soalateOmomiAzBimeShodeDAO;
    private VaziateSalamatiBimeShodeDAO vaziateSalamatiBimeShodeDAO;
    private VaziateSalamatiKhanevadeyeBimeShodeDAO vaziateSalamatiKhanevadeyeBimeShodeDAO;
    private EstefadeKonandeganAzSarmayeBimeDAO estefadeKonandeganAzSarmayeBimeDAO;
    private PishnehadDAO pishnehadDAO;
    private StateDAO stateDAO;
    private UserDAO userDAO;
    private ShakhsDAO shakhsDAO;
    private TransitionDAO transitionDAO;
    private ITransitionLogService transitionLogService;
    private ISequenceService sequenceService;
    private ILoginService loginService;
    private IGharardadService gharardadService;
    private IConstantsService constantsService;
    private ILogService logService;
    private IConstantItemsService constantItemsService;
    private BimenameDAO bimenameDAO;

    public boolean isMoreThanOnePishnehadByShakhs(String codeMeli) {
        return pishnehadDAO.isMoreThanOnePishnehadByShakhs(codeMeli);
    }

    public void saveOrUpdate(Object object) {
        pishnehadDAO.saveOrUpdate(object);
    }

    public void saveNewPishnehad(Pishnehad pishnehad) {
        bimeGozarDAO.save(pishnehad.getBimeGozar());
        addressDAO.save(pishnehad.getAddressBimeGozar());
        bimeShodeDAO.save(pishnehad.getBimeShode());

        if (pishnehad.getBimeGozar().getNesbatBabimeShode().equals("خود شخص"))
        {
            pishnehad.getBimeGozar().setShakhs(pishnehad.getBimeShode().getShakhs());
            bimeGozarDAO.update(pishnehad.getBimeGozar());
        }
        addressDAO.save(pishnehad.getAddressBimeShode());
        foroshandeDAO.save(pishnehad.getForoshande());
        soalateOmomiAzBimeShodeDAO.save(pishnehad.getSoalateOmomiAzBimeShode());

        List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeList = pishnehad.getEstefadeKonandeganAzSarmayeBime();
        if (estefadeKonandeganAzSarmayeBimeList != null)
            for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : estefadeKonandeganAzSarmayeBimeList) {
                estefadeKonandeganAzSarmayeBime.setPishnehad(pishnehad);
            }
        List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList = pishnehad.getVaziateSalamatiBimeShodeList();
        if (vaziateSalamatiBimeShodeList != null)
            for (VaziateSalamatiBimeShode vaziateSalamatiBimeShode : vaziateSalamatiBimeShodeList) {
                vaziateSalamatiBimeShode.setPishnehad(pishnehad);
            }
        List<SavabegheBimei> savabegheBimeiList = pishnehad.getSavabegheBimei();
        if (savabegheBimeiList != null)
            for (SavabegheBimei savabegheBimei : savabegheBimeiList) {
                savabegheBimei.setPishnehad(pishnehad);
            }
        List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShodeList = pishnehad.getVaziateSalamatiKhanevadeyeBimeShode();
        if (vaziateSalamatiKhanevadeyeBimeShodeList != null)
            for (VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode : vaziateSalamatiKhanevadeyeBimeShodeList) {
                vaziateSalamatiKhanevadeyeBimeShode.setPishnehad(pishnehad);
            }

        State state = stateDAO.findById(Constant.INITIAL_STATE);
        if (state != null) pishnehad.setState(state);

        pishnehad.setCreatedDate(DateUtil.getCurrentDate());
        pishnehad.setCreatedTime(DateUtil.getCurrentTime());
        pishnehad.setVersion(DateUtil.getCurrentDate());
        pishnehad.setInitialyPrinted("no");
        pishnehad.setRadif(sequenceService.nextShomareRadif(pishnehad.getNamayande()));
        pishnehadDAO.save(pishnehad);
        estefadeKonandeganAzSarmayeBimeDAO.saveOrUpdateAll(estefadeKonandeganAzSarmayeBimeList);
        vaziateSalamatiBimeShodeDAO.saveOrUpdateAll(vaziateSalamatiBimeShodeList);
        savabegheBimeiDAO.saveOrUpdateAll(savabegheBimeiList);
        vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(vaziateSalamatiKhanevadeyeBimeShodeList);
    }

    public void updatePishnehad(Pishnehad newPishnehad) {
        if (newPishnehad.getBimeGozar() != null)
            bimeGozarDAO.update(newPishnehad.getBimeGozar());
        if (newPishnehad.getAddressBimeGozar() != null)
            addressDAO.update(newPishnehad.getAddressBimeGozar());
        if (newPishnehad.getBimeShode() != null)
            bimeShodeDAO.update(newPishnehad.getBimeShode());
        if (newPishnehad.getAddressBimeShode() != null)
            if (!newPishnehad.getAddressBimeGozar().getId().equals(newPishnehad.getAddressBimeShode().getId())) {
                addressDAO.update(newPishnehad.getAddressBimeShode());
            }
        if (newPishnehad.getForoshande() != null)
            foroshandeDAO.update(newPishnehad.getForoshande());
        if (newPishnehad.getSoalateOmomiAzBimeShode() != null)
            soalateOmomiAzBimeShodeDAO.update(newPishnehad.getSoalateOmomiAzBimeShode());
        if (newPishnehad.getEstelam() != null)
            estelamDAO.update(newPishnehad.getEstelam());


        List<EstefadeKonandeganAzSarmayeBime> allEstefadeKonandeganAzSarmayeBimeList = newPishnehad.getEstefadeKonandeganAzSarmayeBime();
        List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeList = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        List<VaziateSalamatiBimeShode> allVaziateSalamatiBimeShodeList = newPishnehad.getVaziateSalamatiBimeShodeList();
        List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList = new ArrayList<VaziateSalamatiBimeShode>();
        List<SavabegheBimei> allSavabegheBimeiList = newPishnehad.getSavabegheBimei();
        List<SavabegheBimei> savabegheBimeiList = new ArrayList<SavabegheBimei>();
        List<VaziateSalamatiKhanevadeyeBimeShode> allVaziateSalamatiKhanevadeyeBimeShodeList = newPishnehad.getVaziateSalamatiKhanevadeyeBimeShode();
        List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShodeList = new ArrayList<VaziateSalamatiKhanevadeyeBimeShode>();

        boolean shouldSave = true;

        if (allEstefadeKonandeganAzSarmayeBimeList != null) {
            for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : allEstefadeKonandeganAzSarmayeBimeList) {
                if (estefadeKonandeganAzSarmayeBime.getNoeZiNafea() != null) {
                    estefadeKonandeganAzSarmayeBimeList.add(estefadeKonandeganAzSarmayeBime);
                    if (estefadeKonandeganAzSarmayeBime.getDarsadeSahm().contains(",")) {
                        shouldSave = false;
                    }
                } else {
                    estefadeKonandeganAzSarmayeBimeDAO.deleteItem(estefadeKonandeganAzSarmayeBime);
                }
            }
            if (shouldSave)
                estefadeKonandeganAzSarmayeBimeDAO.saveOrUpdateAll(estefadeKonandeganAzSarmayeBimeList);
        }

        if (allVaziateSalamatiBimeShodeList != null) {
            for (VaziateSalamatiBimeShode vaziateSalamatiBimeShode : allVaziateSalamatiBimeShodeList) {
                if (vaziateSalamatiBimeShode != null && vaziateSalamatiBimeShode.getJavabeSolal() == null) {
                    vaziateSalamatiBimeShodeDAO.deleteItem(vaziateSalamatiBimeShode);
                } else if (vaziateSalamatiBimeShode != null) {
                    vaziateSalamatiBimeShodeList.add(vaziateSalamatiBimeShode);
                }
            }
            vaziateSalamatiBimeShodeDAO.saveOrUpdateAll(vaziateSalamatiBimeShodeList);
        }

        if (allSavabegheBimeiList != null) {
            for (SavabegheBimei savabegheBimei : allSavabegheBimeiList) {
                if (savabegheBimei != null && savabegheBimei.getNoeBimeName() == null) {
                    savabegheBimeiDAO.deleteItem(savabegheBimei);
                } else if (savabegheBimei != null) {
                    savabegheBimeiList.add(savabegheBimei);
                }
            }
            savabegheBimeiDAO.saveOrUpdateAll(savabegheBimeiList);
        }

        if (allVaziateSalamatiKhanevadeyeBimeShodeList != null) {
            for (VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode : allVaziateSalamatiKhanevadeyeBimeShodeList) {
                if (vaziateSalamatiKhanevadeyeBimeShode != null && vaziateSalamatiKhanevadeyeBimeShode.getNesbatBabimeShode() == null) {
                    vaziateSalamatiKhanevadeyeBimeShodeDAO.deleteItem(vaziateSalamatiKhanevadeyeBimeShode);
                } else if (vaziateSalamatiKhanevadeyeBimeShode != null) {
                    vaziateSalamatiKhanevadeyeBimeShodeList.add(vaziateSalamatiKhanevadeyeBimeShode);
                }
            }
            vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(vaziateSalamatiKhanevadeyeBimeShodeList);
        }

        Pishnehad oldPishnehad = pishnehadDAO.findById(newPishnehad.getId());

        for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : estefadeKonandeganAzSarmayeBimeList) {
            estefadeKonandeganAzSarmayeBime.setPishnehad(oldPishnehad);
        }
        for (VaziateSalamatiBimeShode vaziateSalamatiBimeShode : vaziateSalamatiBimeShodeList) {
            vaziateSalamatiBimeShode.setPishnehad(oldPishnehad);
        }
        for (SavabegheBimei savabegheBimei : savabegheBimeiList) {
            savabegheBimei.setPishnehad(oldPishnehad);
        }
        for (VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode : vaziateSalamatiKhanevadeyeBimeShodeList) {
            vaziateSalamatiKhanevadeyeBimeShode.setPishnehad(oldPishnehad);
        }
        oldPishnehad.setNoeGharardad(newPishnehad.getNoeGharardad());
        oldPishnehad.setBimeGozar(newPishnehad.getBimeGozar());
        oldPishnehad.setBimeShode(newPishnehad.getBimeShode());
        oldPishnehad.setForoshande(newPishnehad.getForoshande());
        oldPishnehad.setSoalateOmomiAzBimeShode(newPishnehad.getSoalateOmomiAzBimeShode());
        oldPishnehad.setSavabegheBimei(savabegheBimeiList);
        oldPishnehad.setVaziateSalamatiBimeShodeList(vaziateSalamatiBimeShodeList);
        oldPishnehad.setEstefadeKonandeganAzSarmayeBime(estefadeKonandeganAzSarmayeBimeList);
        oldPishnehad.setNaghsPishnehad(newPishnehad.getNaghsPishnehad());
        oldPishnehad.setPishpardakhtType(newPishnehad.getPishpardakhtType());
        oldPishnehad.setKarshenas(newPishnehad.getKarshenas());
        oldPishnehad.setKarshenasNazer(newPishnehad.getKarshenasNazer());
        oldPishnehad.setZamaem(newPishnehad.getZamaem());
        oldPishnehad.setProcessor(newPishnehad.getProcessor());
        oldPishnehad.setProcessorDetails(newPishnehad.getProcessorDetails());
        oldPishnehad.setSharayeteJadid(newPishnehad.getSharayeteJadid());
        oldPishnehad.setElameHesab(newPishnehad.getElameHesab());
        oldPishnehad.setElameEnseraf(newPishnehad.getElameEnseraf());
        oldPishnehad.setBazarYab(newPishnehad.getBazarYab());
        oldPishnehad.setTarh(newPishnehad.getTarh());
        oldPishnehad.setInitialyPrinted(newPishnehad.getInitialyPrinted());
        oldPishnehad.setEstelam(newPishnehad.getEstelam());
        oldPishnehad.setNoeBimename(newPishnehad.getNoeBimename());
        oldPishnehad.setGharardad(newPishnehad.getGharardad());

        pishnehadDAO.update(oldPishnehad);
    }

    public Set<Pishnehad> findAll() {
        List<Pishnehad> dummy = pishnehadDAO.findAll();
        Set<Pishnehad> result = new HashSet<Pishnehad>();
        for (Pishnehad pishnehad : dummy) {
            result.add(pishnehad);
        }
        return result;
    }

    public void saveOrUpdateDasteSerials(DasteSerial dasteSerial) {
        bimenameDAO.saveOrUpdateDasteSerials(dasteSerial);
    }

    public DasteSerial findDasteSerialById(Integer id) {
        return bimenameDAO.findDasteSerialById(id);
    }

    public List<Serial> findSerialsEstefadeNashode(Long serialFirst, Long serialLast) {
        return bimenameDAO.findSerialsEstefadeNashode(serialFirst, serialLast);
    }

    public Long minOrMaxSerialByIdDaste(String minOrmax, Integer idDaste) {
        return bimenameDAO.minOrMaxSerialByIdDaste(minOrmax, idDaste);
    }

    public boolean isAvailableSerial(Long shomareSerial) {
        return bimenameDAO.isAvailableSerial(shomareSerial);
    }

    public List<Serial> findSerialByShomareSerial(Long[] shomareSerials) {
        return bimenameDAO.findSerialByShomareSerial(shomareSerials);
    }

    public void saveOrUpdateSerials(List<Serial> serials) {
        bimenameDAO.saveOrUpdateSerials(serials);
    }

    public PaginatedListImpl<DasteSerial> findAllDasteSerials(PaginatedListImpl paginatedList, String vaziateDaste, Long serialFirst, Long serialLast, Long namayandegi, String bimenameType, User user) {
        return bimenameDAO.findAllDasteSerials(paginatedList, vaziateDaste, serialFirst, serialLast, namayandegi, bimenameType, user);
    }

    public PaginatedListImpl<SerialsVM> findAllDasteSerials(PaginatedListImpl pgList, SerialsVM svmOb) {
        return bimenameDAO.findAllDasteSerials(pgList, svmOb);
    }

    public DasteSerial lastDasteSerial() {
        return bimenameDAO.findLastDasteSerial();
    }

    @Transactional
//    public PaginatedListImpl<Pishnehad> findAllowedPishnehads(User user, PaginatedListImpl<Pishnehad> paginatedList)
//    {
//        return pishnehadDAO.findAllowedPishnehads(user, paginatedList);
//    }

    public PaginatedListImpl<PishnehadsVM> findAllowedPishnehads(User user, PaginatedListImpl<PishnehadsVM> paginatedList) {
        List<Integer> rolesId = userDAO.getRolesIdOfUser(user);
        List<Integer> statesId = transitionDAO.getStateBeginsOfTransitions(rolesId);
        paginatedList = pishnehadDAO.findAllowedPishnehads(user, paginatedList, statesId);
        List<PishnehadsVM> list = paginatedList.getList();
        for (PishnehadsVM p : list) {
            List<String> founds = pishnehadDAO.getFoundFishs(p.getId());

            boolean result = true;
            for (String found : founds) {
                result = result && (found.equalsIgnoreCase("true"));
            }
            if (founds.size() == 0) result = false;
            p.setPishpardakhtOK(result);
        }
        paginatedList.setList(list);
        return paginatedList;
    }

    public boolean hasPermissionToViewAllPishnehad(User user, Integer pishnehadId, String tab) {
        return pishnehadDAO.hasPermissionToViewAllPishnehad(user, pishnehadId, tab);
    }

    @Transactional
    public PaginatedListImpl findAllowedToViewPishnehads(User user, PaginatedListImpl paginatedList) {
        paginatedList = pishnehadDAO.findAllowedToViewPishnehads(user, paginatedList);
        List<PishnehadsVM> list = paginatedList.getList();
        for (PishnehadsVM p : list) {
            List<String> founds = pishnehadDAO.getFoundFishs(p.getId());

            boolean result = true;
            for (String found : founds) {
                result = result && (found.equalsIgnoreCase("true"));
            }
            if (founds.size() == 0) result = false;
            p.setPishpardakhtOK(result);
        }
        paginatedList.setList(list);
        return paginatedList;
    }

    @Transactional
    public void deletePishnehad(Integer id) {
        pishnehadDAO.deletePishnehad(id);
    }

    @Transactional
    public void deleteFish(Integer id) {
        pishnehadDAO.deleteFish(id);
    }

    public Pishnehad findById(Integer pishnehadId) {
        return pishnehadDAO.findById(pishnehadId);
    }

    public boolean hasPermissionToViewPisnehad(User user, Integer pishnehadId) {
        return pishnehadDAO.hasPermissionToViewPisnehad(user, pishnehadId);
    }

    public Pishnehad loadPishnehadById(Integer id) {
        return pishnehadDAO.findPishnehadById(id);
    }

    public List<Transition> findAllowedTransitionsForId(Integer id) {

        return pishnehadDAO.findAllowedTransitionsForId(id);
    }

    public void transitPishnehad(Long userId, String pishnehadId, String transitionId, String logmessage) {
        Pishnehad thePish = pishnehadDAO.findById(Integer.parseInt(pishnehadId));
        Transition transition = transitionDAO.findById(Integer.parseInt(transitionId));
        User user = loginService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (thePish.getState().equals(transition.getStateBegin()) && hasPermission(user, thePish.getState().getId())) {
            thePish.setState(transition.getStateEnd());
            pishnehadDAO.update(thePish);
            transitionLogService.logTransition(user, thePish, transition, logmessage);
        }
        // todo: log failed transitions for security
    }

    public int addNewNazarPezeshk(PezeshkSabtNazar pezeshkSabtNazar) {
        pezeshkSabtNazar.setFromPezeshk(true);
        pishnehadDAO.addNewNazarPezeshk(pezeshkSabtNazar);
        return pezeshkSabtNazar.getId();
    }

    public int addNewNazarRaeis(PezeshkSabtNazar pezeshkSabtNazar) {
        pezeshkSabtNazar.setFromPezeshk(false);
        pishnehadDAO.addNewNazarPezeshk(pezeshkSabtNazar);
        return pezeshkSabtNazar.getId();
    }

    public int saveBimename(Bimename bimename) {
        State initial = this.stateDAO.findById(Constant.BIMENAME_INITIAL_STATE);
        bimename.setState(initial);
        this.bimenameDAO.save(bimename);
        return bimename.getId();
    }

    public PezeshkSabtNazar findPezeshkSabtNazarById(int pznId) {
        return pishnehadDAO.findPezeshkSabtNazarById(pznId);
    }

    public void updatePezeshkSabtNazar(PezeshkSabtNazar pzn) {
        pishnehadDAO.updatePezeshkSabtNazar(pzn);

    }

    public int saveNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        pishnehadDAO.saveNaghsPishnehad(naghsPishnehad);
        return naghsPishnehad.getId();
    }

    public void updateNaghsPishnehad(NaghsPishnehad naghsPishnehad) {
        pishnehadDAO.updateNaghsPishnehad(naghsPishnehad);
    }

    public NaghsPishnehad findNaghsPishnehadById(Integer naghsId) {
        return pishnehadDAO.findNaghsPishnehadById(naghsId);
    }

    public void removeNaghsPishnehad(Integer naghsId) {
        NaghsPishnehad naaghes = pishnehadDAO.findNaghsPishnehadById(naghsId);
        pishnehadDAO.removeNaghsPishnehad(naaghes);
    }

    public int saveFish(Fish fish) {
        pishnehadDAO.saveFish(fish);
        return fish.getId();
    }

    public Fish findFish(Integer fishId) {
        return pishnehadDAO.findFishById(fishId);
    }

    public List<Fish> findFish(String shomare, String bankName, String shobe, String time, String date) {
        return pishnehadDAO.findFish(shomare, bankName, shobe, time, date);
    }

    public void saveMoarefiname(Moarefiname moarefiname) {
        pishnehadDAO.saveMoarefiname(moarefiname);
    }

    public Moarefiname findMoarefiname(Integer id) {
        return pishnehadDAO.findMoarefiname(id);
    }

    public void updateFish(Fish theFish) {
        pishnehadDAO.updateFish(theFish);
    }

    public int saveZamaem(Zamaem zamaem) {
        return pishnehadDAO.saveZamaem(zamaem);
    }

    public Zamaem findZamaemById(int zamId) {
        return pishnehadDAO.findZamaemById(zamId);
    }

    public ZamaemKhesarat findZamaemKhesaratById(int zamId) {
        return pishnehadDAO.findZamaemKhesaratById(zamId);
    }

    public void updateZamaem(Zamaem theZamime) {
        pishnehadDAO.updateZamaem(theZamime);
    }

    public int saveZamaemKhesarat(ZamaemKhesarat zamaemKhesarat) {
        return pishnehadDAO.saveZamaemKhesarat(zamaemKhesarat);
    }

    public void updateZamaemKhesarat(ZamaemKhesarat zamaemKhesarat) {
        pishnehadDAO.updateZamaemKhesarat(zamaemKhesarat);
    }

    public void removeNazarPezeshk(Integer id) {
        PezeshkSabtNazar pezeshkSabtNazar = pishnehadDAO.findPezeshkSabtNazarById(id);
        pishnehadDAO.removeNazarPezeshk(pezeshkSabtNazar);
    }

    public List<Credebit> searchForFish(BankInfo bankInfo) {
        return pishnehadDAO.searchForFish(bankInfo);
    }

    public void updateSelfPishnehadOnly(Pishnehad pishnehad) {
        pishnehadDAO.update(pishnehad);
    }

    public Bimename findBimenameById(int bimenameId) {
        return pishnehadDAO.findBimenameById(bimenameId);
    }

    public PaginatedListImpl findAllBimenameForMosharekat(String tarikhShoro, String tarikhPayan, PaginatedListImpl pgList) {
        return bimenameDAO.findAll(tarikhShoro, tarikhPayan, pgList);
    }

    public List<Bimename> findAllBimenameForMosharekat(String tarikhShoro, String tarikhPayan) {
        return bimenameDAO.findAllForMosharekat(tarikhShoro, tarikhPayan);
    }

    public String executeMosharekat(String tarikhShoro, String tarikhPayan, double i) {
        return bimenameDAO.executeMosharekat(tarikhShoro, tarikhPayan, i);
    }

    public PaginatedListImpl findAllBimenamePaginated(User user, PaginatedListImpl pgList) {
        return bimenameDAO.findAllPaginated(user, pgList);
    }

    public List<Bimename> findAllBimenamePaginatedForBimeGozar(User user) {
        return bimenameDAO.findAllBimenamePaginatedForBimeGozar(user);
    }

    public Transition findTransitionById(int id) {
        return transitionDAO.findById(id);
    }

    public PaginatedListImpl<Bimename> findAllBimenamePaginatedForMosharekat(User user, PaginatedListImpl<Bimename> pgList, String tarikhShoro, String tarikhPayan) {
        return bimenameDAO.findAllPaginatedForMosharekat(user, pgList, tarikhShoro, tarikhPayan);
    }

    public void updateBimeGozar(BimeGozar b) {
        bimeGozarDAO.update(b);
    }

    public void updateBimeShode(BimeShode b) {
        bimeShodeDAO.update(b);
    }

    @Transactional
    public void updateBimename(Bimename bimename) {
        bimenameDAO.update(bimename);
    }

    @Transactional
    public void updateBimename(List<Bimename> bimename) {
        bimenameDAO.updateAll(bimename);
    }

    @Transactional
    public Pishnehad savePishnehadForElhaghie(Pishnehad newPishnehad, Pishnehad oldPishnehad, PishnehadDuplicationRules mafad, Gharardad gharardad, User user, boolean shouldCreateShakhsBimeGozar, boolean shouldCreateShakhsBimeShode) {
        Pishnehad newLocalPishnehad= new Pishnehad();
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
            newLocalPishnehad.setVersion(DateUtil.getCurrentDate());
        else
        {
            newPishnehad.setId(null);
            newPishnehad.setVersion(DateUtil.getCurrentDate());
        }
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setValid(false);
            newLocalPishnehad.setValid(false);
        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setValid(true);
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setCreatedDate(oldPishnehad.getCreatedDate());
            newLocalPishnehad.setCreatedDate(oldPishnehad.getCreatedDate());

        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setCreatedDate(DateUtil.getCurrentDate());
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setCreatedTime(oldPishnehad.getCreatedTime());
            newLocalPishnehad.setCreatedTime(oldPishnehad.getCreatedTime());
        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setCreatedTime(DateUtil.getCurrentTime());
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            newPishnehad.setState(oldPishnehad.getState());
            newLocalPishnehad.setState(oldPishnehad.getState());
        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setState(stateDAO.findById(245));
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setUserCreator(oldPishnehad.getUserCreator());
            newLocalPishnehad.setUserCreator(oldPishnehad.getUserCreator());
        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setUserCreator(gharardad.getNamayande().getNamayandegiUser());
        newPishnehad.setKarshenas(oldPishnehad.getKarshenas());
        if(mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami) && oldPishnehad.getKarshenas() == null)
            newPishnehad.setKarshenas(userDAO.getUserById(2474L));
        newLocalPishnehad.setKarshenas(oldPishnehad.getKarshenas());
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setNamayande(oldPishnehad.getNamayande());
            newLocalPishnehad.setNamayande(oldPishnehad.getNamayande());
        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setNamayande(gharardad.getNamayande());
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
            newPishnehad.setGharardad(gharardad);
            newPishnehad.setTarh(gharardad.getTarh());
        }
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            if (OmrUtil.userHasRole(user, Constant.ROLE_NAMAYANDE)) {
//                newPishnehad.setTarh(oldPishnehad.getTarh());
//                newPishnehad.setGharardad(oldPishnehad.getGharardad());
                newLocalPishnehad.setTarh(oldPishnehad.getTarh());
                newLocalPishnehad.setGharardad(oldPishnehad.getGharardad());
            } else {
                if (newPishnehad.getTarh() != null && newPishnehad.getTarh().getId() != null) {
//                    newPishnehad.setTarh(constantsService.findTarhById(newPishnehad.getTarh().getId()));
                    newLocalPishnehad.setTarh(constantsService.findTarhById(newPishnehad.getTarh().getId()));
                } else {
//                    newPishnehad.setTarh(null);
                    newLocalPishnehad.setTarh(null);
                    if(oldPishnehad.getTarh()!=null && oldPishnehad.getTarh()!=null)
                    {
                        newLocalPishnehad.setTarh(constantsService.findTarhById(oldPishnehad.getTarh().getId()));
                    }
                }
                if (newPishnehad.getGharardad() != null && newPishnehad.getGharardad().getId() != null) {
//                    newPishnehad.setGharardad(gharardadService.findById(newPishnehad.getGharardad().getId()));
                    newLocalPishnehad.setGharardad(gharardadService.findById(newPishnehad.getGharardad().getId()));
                } else {
//                    newPishnehad.setGharardad(null);
                    newLocalPishnehad.setGharardad(null);
                    if (oldPishnehad.getGharardad() != null && oldPishnehad.getGharardad() != null)
                    {
                        newLocalPishnehad.setGharardad(gharardadService.findById(oldPishnehad.getGharardad().getId()));
                    }
                }
            }
        }
        newPishnehad.setNoeGharardad(oldPishnehad.getNoeGharardad());
        newPishnehad.setNoePishnehad(oldPishnehad.getNoePishnehad());
        newPishnehad.setOptions(oldPishnehad.getOptions());
        newPishnehad.setAdameControllMohasebat(oldPishnehad.getAdameControllMohasebat());
        newPishnehad.setPishpardakhtSanad(oldPishnehad.getPishpardakhtSanad());
        newPishnehad.setPishpardakhtType(oldPishnehad.getPishpardakhtType());
        newPishnehad.setProcessor(oldPishnehad.getProcessor());

        newLocalPishnehad.setNoeGharardad(oldPishnehad.getNoeGharardad());
        newLocalPishnehad.setNoePishnehad(oldPishnehad.getNoePishnehad());
        newLocalPishnehad.setOptions(oldPishnehad.getOptions());
        newLocalPishnehad.setAdameControllMohasebat(oldPishnehad.getAdameControllMohasebat());
        newLocalPishnehad.setPishpardakhtSanad(oldPishnehad.getPishpardakhtSanad());
        newLocalPishnehad.setPishpardakhtType(oldPishnehad.getPishpardakhtType());
        newLocalPishnehad.setProcessor(oldPishnehad.getProcessor());

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
        {
//            newPishnehad.setRadif(oldPishnehad.getRadif());
            newLocalPishnehad.setRadif(oldPishnehad.getRadif());
        }
        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
            newPishnehad.setRadif(sequenceService.nextShomareRadif(newPishnehad.getNamayande()));


        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
        {
            if(newPishnehad.getEstelam() != null)
                estelamDAO.save(newPishnehad.getEstelam());
            pishnehadDAO.save(newPishnehad);
        }


//-----------------
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            List<Fish> fishList = oldPishnehad.getFishs();
            List<Fish> newFishList = new ArrayList<Fish>();
            if (fishList != null) {
                for (Fish fish : fishList) {
                    Fish newFish = new Fish();
                    newFish.setBankName(fish.getBankName());
                    newFish.setFileOtherId(fish.getFileOtherId());
                    newFish.setFileOtherName(fish.getFileOtherName());
                    newFish.setFilePishId(fish.getFilePishId());
                    newFish.setFilePishName(fish.getFilePishName());
                    newFish.setFileScannedId(fish.getFileScannedId());
                    newFish.setFileScannedName(fish.getFileScannedName());
                    newFish.setFound(fish.getFound());
                    newFish.setKodeShobe(fish.getKodeShobe());
                    newFish.setMablagh(fish.getMablagh());
                    newFish.setPaymentType(fish.getPaymentType());
                    newFish.setShomare(fish.getShomare());
//                    newFish.setTarikh(fish.getShomare());
                    newFish.setTarikh(fish.getTarikh());
                    newFish.setTime(fish.getTime());
                    newFish.setCredebit(fish.getCredebit());
//                    newFish.setPishnehad(newPishnehad);
                    newFish.setPishnehad(newLocalPishnehad);
                    newFishList.add(newFish);
                }
                pishnehadDAO.saveAllFish(newFishList);
//                newPishnehad.setFishs(newFishList);
                newLocalPishnehad.setFishs(newFishList);
            }
        }

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            Zamaem zamaem = oldPishnehad.getZamaem();
            if (zamaem != null) {
                Zamaem result = new Zamaem();
                result.setFileEnserafDescription(zamaem.getFileEnserafDescription());
                result.setFileEnserafId(zamaem.getFileEnserafId());
                result.setFileEnserafName(zamaem.getFileEnserafName());
                result.setFileElameHesabDescription(zamaem.getFileElameHesabDescription());
                result.setFileElameHesabId(zamaem.getFileElameHesabId());
                result.setFileElameHesabName(zamaem.getFileElameHesabName());
                result.setFileMoshaverePezeshkId(zamaem.getFileMoshaverePezeshkId());
                result.setFileMoshaverePezeshkName(zamaem.getFileMoshaverePezeshkName());
                result.setFileMoshaverePezeshkDesc(zamaem.getFileMoshaverePezeshkDesc());
                result.setFileMoshaverePezeshkJavabId(zamaem.getFileMoshaverePezeshkJavabId());
                result.setFileMoshaverePezeshkJavabName(zamaem.getFileMoshaverePezeshkJavabName());
                result.setFileMoshaverePezeshkJavabDesc(zamaem.getFileMoshaverePezeshkJavabDesc());
                result.setFileFishDescription1(zamaem.getFileFishDescription1());
                result.setFileFishDescription2(zamaem.getFileFishDescription2());
                result.setFileFishDescription3(zamaem.getFileFishDescription3());
                result.setFileFishDescription4(zamaem.getFileFishDescription4());
                result.setFileFishDescription5(zamaem.getFileFishDescription5());
                result.setFileFishId1(zamaem.getFileFishId1());
                result.setFileFishId2(zamaem.getFileFishId2());
                result.setFileFishId3(zamaem.getFileFishId3());
                result.setFileFishId4(zamaem.getFileFishId4());
                result.setFileFishId5(zamaem.getFileFishId5());
                result.setFileFishName1(zamaem.getFileFishName1());
                result.setFileFishName2(zamaem.getFileFishName2());
                result.setFileFishName3(zamaem.getFileFishName3());
                result.setFileFishName4(zamaem.getFileFishName4());
                result.setFileFishName5(zamaem.getFileFishName5());
                result.setFilePishDigitalDescription1(zamaem.getFilePishDigitalDescription1());
                 result.setFilePishDigitalDescription2(zamaem.getFilePishDigitalDescription2());
                result.setFilePishDigitalDescription3(zamaem.getFilePishDigitalDescription3());
                 result.setFilePishDigitalDescription4(zamaem.getFilePishDigitalDescription4());
                result.setFilePishDigitalDescription5(zamaem.getFilePishDigitalDescription5());
                result.setFilePishDigitalId1(zamaem.getFilePishDigitalId1());
                result.setFilePishDigitalId2(zamaem.getFilePishDigitalId2());
                result.setFilePishDigitalId3(zamaem.getFilePishDigitalId3());
                result.setFilePishDigitalId4(zamaem.getFilePishDigitalId4());
                result.setFilePishDigitalId5(zamaem.getFilePishDigitalId5());
                result.setFilePishDigitalName1(zamaem.getFilePishDigitalName1());
                result.setFilePishDigitalName2(zamaem.getFilePishDigitalName2());
                result.setFilePishDigitalName3(zamaem.getFilePishDigitalName3());
                result.setFilePishDigitalName4(zamaem.getFilePishDigitalName4());
                result.setFilePishDigitalName5(zamaem.getFilePishDigitalName5());
                result.setFilePishKatbiDescription1(zamaem.getFilePishKatbiDescription1());
                result.setFilePishKatbiDescription2(zamaem.getFilePishKatbiDescription2());
                result.setFilePishKatbiDescription3(zamaem.getFilePishKatbiDescription3());
                result.setFilePishKatbiDescription4(zamaem.getFilePishKatbiDescription4());
                result.setFilePishKatbiDescription5(zamaem.getFilePishKatbiDescription5());
                result.setFilePishKatbiId1(zamaem.getFilePishKatbiId1());
                result.setFilePishKatbiId2(zamaem.getFilePishKatbiId2());
                result.setFilePishKatbiId3(zamaem.getFilePishKatbiId3());
                result.setFilePishKatbiId4(zamaem.getFilePishKatbiId4());
                result.setFilePishKatbiId5(zamaem.getFilePishKatbiId5());
                result.setFilePishKatbiName1(zamaem.getFilePishKatbiName1());
                result.setFilePishKatbiName2(zamaem.getFilePishKatbiName2());
                result.setFilePishKatbiName3(zamaem.getFilePishKatbiName3());
                result.setFilePishKatbiName4(zamaem.getFilePishKatbiName4());
                result.setFilePishKatbiName5(zamaem.getFilePishKatbiName5());
                result.setFileSayerDescription1(zamaem.getFileSayerDescription1());
                result.setFileSayerId1(zamaem.getFileSayerId1());
                result.setFileSayerName1(zamaem.getFileSayerName1());
                result.setFileSayerDescription2(zamaem.getFileSayerDescription2());
                result.setFileSayerId2(zamaem.getFileSayerId2());
                result.setFileSayerName2(zamaem.getFileSayerName2());
                result.setFileSayerDescription3(zamaem.getFileSayerDescription3());
                result.setFileSayerId3(zamaem.getFileSayerId3());
                result.setFileSayerName3(zamaem.getFileSayerName3());
                result.setFileSayerDescription4(zamaem.getFileSayerDescription4());
                result.setFileSayerId4(zamaem.getFileSayerId4());
                result.setFileSayerName4(zamaem.getFileSayerName4());
                result.setFileSayerDescription5(zamaem.getFileSayerDescription5());
                result.setFileSayerId5(zamaem.getFileSayerId5());
                result.setFileSayerName5(zamaem.getFileSayerName5());
                result.setPishnehad(zamaem.getPishnehad());
                saveZamaem(result);
//                newPishnehad.setZamaem(result);
                newLocalPishnehad.setZamaem(result);

            }
        }
//-----------------

        List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList;
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            vaziateSalamatiBimeShodeList = newPishnehad.getVaziateSalamatiBimeShodeList();
        } else {
            vaziateSalamatiBimeShodeList = oldPishnehad.getVaziateSalamatiBimeShodeList();
        }

        List<VaziateSalamatiBimeShode> newVaziateSalamatiBimeShodeList = new ArrayList<VaziateSalamatiBimeShode>();
        if (vaziateSalamatiBimeShodeList != null) {
            for (VaziateSalamatiBimeShode vaziateSalamatiBimeShode : vaziateSalamatiBimeShodeList) {
                VaziateSalamatiBimeShode newVaziateSalamati = new VaziateSalamatiBimeShode();
                newVaziateSalamati.setConstantSoalItem(vaziateSalamatiBimeShode.getConstantSoalItem());
                newVaziateSalamati.setDarmaneAnjamShode(vaziateSalamatiBimeShode.getDarmaneAnjamShode());
                newVaziateSalamati.setDaroyeMasrafi(vaziateSalamatiBimeShode.getDaroyeMasrafi());
                if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
                    newVaziateSalamati.setJavabeSolal(vaziateSalamatiBimeShode.getJavabeSolal());
                } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
                    if (mafad.getVaziateSalamateBimeShodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AllHealthy)) {
                        if (vaziateSalamatiBimeShode.getConstantSoalItem().getId() != 4)
                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.KHEIR);
                        else
                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.BALI);
                    } else if (mafad.getVaziateSalamateBimeShodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.BeTafkikePishnehad)) {
                        if (vaziateSalamatiBimeShode.getConstantSoalItem().getId() != 4)
                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.BALI);
                        else
                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.KHEIR);
                    }
                }
                newVaziateSalamati.setMeghdareMasraf(vaziateSalamatiBimeShode.getMeghdareMasraf());
                newVaziateSalamati.setModateMasraf(vaziateSalamatiBimeShode.getModateMasraf());
                newVaziateSalamati.setShoroeZamaneBimari(vaziateSalamatiBimeShode.getShoroeZamaneBimari());
                newVaziateSalamati.setTarikheJarahi(vaziateSalamatiBimeShode.getTarikheJarahi());
                newVaziateSalamati.setTozih(vaziateSalamatiBimeShode.getTozih());
                newVaziateSalamati.setVaziateFelieBimeShode(vaziateSalamatiBimeShode.getVaziateFelieBimeShode());
                if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                    newVaziateSalamati.setPishnehad(newLocalPishnehad);
                else
                    newVaziateSalamati.setPishnehad(newPishnehad);
                newVaziateSalamatiBimeShodeList.add(newVaziateSalamati);
            }
            vaziateSalamatiBimeShodeDAO.saveOrUpdateAll(newVaziateSalamatiBimeShodeList);
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newLocalPishnehad.setVaziateSalamatiBimeShodeList(newVaziateSalamatiBimeShodeList);
            else
                newPishnehad.setVaziateSalamatiBimeShodeList(newVaziateSalamatiBimeShodeList);
        }

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShodeList =
                    newPishnehad.getVaziateSalamatiKhanevadeyeBimeShode();
            List<VaziateSalamatiKhanevadeyeBimeShode> newVaziateSalamatiKhanevadeyeBimeShodeList =
                    new ArrayList<VaziateSalamatiKhanevadeyeBimeShode>();
            if (vaziateSalamatiBimeShodeList != null) {

                for (VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode : vaziateSalamatiKhanevadeyeBimeShodeList) {
                    VaziateSalamatiKhanevadeyeBimeShode newVaziat = new VaziateSalamatiKhanevadeyeBimeShode();
                    newVaziat.setEllateFout(vaziateSalamatiKhanevadeyeBimeShode.getEllateFout());
                    newVaziat.setNesbatBabimeShode(vaziateSalamatiKhanevadeyeBimeShode.getNesbatBabimeShode());
                    newVaziat.setSenneDarHayat(vaziateSalamatiKhanevadeyeBimeShode.getSenneDarHayat());
                    newVaziat.setSenneDarZamaneFout(vaziateSalamatiKhanevadeyeBimeShode.getSenneDarZamaneFout());
                    newVaziat.setSharheEllateFout(vaziateSalamatiKhanevadeyeBimeShode.getSharheEllateFout());
                    newVaziat.setVaziateHayat(vaziateSalamatiKhanevadeyeBimeShode.getVaziateHayat());
                    newVaziat.setVaziateSalamati(vaziateSalamatiKhanevadeyeBimeShode.getVaziateSalamati());
//                    newVaziat.setPishnehad(newPishnehad);
                    newVaziat.setPishnehad(newLocalPishnehad);
                    newVaziateSalamatiKhanevadeyeBimeShodeList.add(newVaziat);
                }
                vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(newVaziateSalamatiKhanevadeyeBimeShodeList);
//                newPishnehad.setVaziateSalamatiKhanevadeyeBimeShode(newVaziateSalamatiKhanevadeyeBimeShodeList);
                newLocalPishnehad.setVaziateSalamatiKhanevadeyeBimeShode(newVaziateSalamatiKhanevadeyeBimeShodeList);
            }
        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
            vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(newPishnehad.getVaziateSalamatiKhanevadeyeBimeShode());
        }

        List<SavabegheBimei> savabegheBimeiList;
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            savabegheBimeiList = newPishnehad.getSavabegheBimei();
        } else {
            savabegheBimeiList = oldPishnehad.getSavabegheBimei();
        }

        List<SavabegheBimei> newSavabegheBimeiList = new ArrayList<SavabegheBimei>();
        if (savabegheBimeiList != null) {
            for (SavabegheBimei savabegheBimei : savabegheBimeiList) {
                SavabegheBimei newSabeghe = new SavabegheBimei();
                newSabeghe.setNameBimeName(savabegheBimei.getNameBimeName());
                newSabeghe.setNatijeNahayi(savabegheBimei.getNatijeNahayi());
                newSabeghe.setNoeBimeName(savabegheBimei.getNoeBimeName());
                newSabeghe.setSarmayeFout(savabegheBimei.getSarmayeFout());
                newSabeghe.setSarmayeHayat(savabegheBimei.getSarmayeHayat());
                newSabeghe.setSharheAdameSodor(savabegheBimei.getSharheAdameSodor());
                newSabeghe.setSherkateBimeGar(savabegheBimei.getSherkateBimeGar());
                newSabeghe.setSherkateBimeGarTozihateSayer(savabegheBimei.getSherkateBimeGar());
                newSabeghe.setTarikheKhateme(savabegheBimei.getTarikheKhateme());
                if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                    newSabeghe.setPishnehad(newLocalPishnehad);
                else
                    newSabeghe.setPishnehad(newPishnehad);
                newSavabegheBimeiList.add(newSabeghe);
            }
            savabegheBimeiDAO.saveOrUpdateAll(newSavabegheBimeiList);
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newLocalPishnehad.setSavabegheBimei(newSavabegheBimeiList);
            else
                newPishnehad.setSavabegheBimei(newSavabegheBimeiList);
            newLocalPishnehad.setSavabegheBimei(newSavabegheBimeiList);
        }

        List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeList;
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            estefadeKonandeganAzSarmayeBimeList = newPishnehad.getEstefadeKonandeganAzSarmayeBime();
        } else {

            estefadeKonandeganAzSarmayeBimeList=new ArrayList<EstefadeKonandeganAzSarmayeBime>();
            EstefadeKonandeganAzSarmayeBime estefadeKonandeHayat = new EstefadeKonandeganAzSarmayeBime();
            EstefadeKonandeganAzSarmayeBime estefadeKonandeFout = new EstefadeKonandeganAzSarmayeBime();
            Shakhs shakhs = new Shakhs();

            if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.SameAsBimeGozar))
            {
                shakhs = oldPishnehad.getBimeGozar().getShakhs();
            }
            else if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel))
            {
                shakhs=newPishnehad.getBimeShode().getShakhs();
            }
            estefadeKonandeHayat.setDarsadeSahm("100");
            estefadeKonandeFout.setDarsadeSahm("100");
//            estefadeKonandeHayat.setKodeEghtesadi();
//            estefadeKonandeFout.setKodeEghtesadi();
            estefadeKonandeHayat.setKodeMelli(shakhs.getKodeMelliShenasayi());
//            estefadeKonandeFout.setKodeMelli(shakhs.getKodeMelliShenasayi());
//            estefadeKonandeHayat.setMahalleSabt();
//            estefadeKonandeFout.setMahalleSabt();
            estefadeKonandeHayat.setMahalleTavallod(shakhs.getMahalleTavallod().getCityName());
//            estefadeKonandeFout.setMahalleTavallod(shakhs.getMahalleTavallod().getCityName());
            estefadeKonandeHayat.setName(shakhs.getName());
            estefadeKonandeHayat.setNamePedar(shakhs.getNamePedar());
            estefadeKonandeHayat.setMahalleSodoorShenasnameh(shakhs.getMahalleSodoreShenasnameh().getCityName());
//            estefadeKonandeFout.setName(shakhs.getName());
            estefadeKonandeHayat.setNameKhanevadegi(shakhs.getNameKhanevadegi());
//            estefadeKonandeFout.setNameKhanevadegi(shakhs.getNameKhanevadegi());
            estefadeKonandeHayat.setNesbatBabimeShode("خود شخص");
            estefadeKonandeFout.setNesbatBabimeShode("وراث قانوني");
            estefadeKonandeHayat.setNoeZiNafea("حقیقی");
            estefadeKonandeFout.setNoeZiNafea("حقیقی");
            estefadeKonandeHayat.setOlaviat("1");
            estefadeKonandeFout.setOlaviat("1");
//            estefadeKonandeHayat.setShomareSabt();
//            estefadeKonandeFout.setShomareSabt();
            estefadeKonandeHayat.setShomareShenasname(shakhs.getShomareShenasnameh());
//            estefadeKonandeFout.setShomareShenasname(shakhs.getShomareShenasnameh());
//            estefadeKonandeHayat.setTarikhSabt();
//            estefadeKonandeFout.setTarikhSabt();
            estefadeKonandeHayat.setTarikhTavallod(shakhs.getTarikheTavallod());
//            estefadeKonandeFout.setTarikhTavallod(shakhs.getTarikheTavallod());
            estefadeKonandeHayat.setVaziateFotVaHayat("hayat");
            estefadeKonandeFout.setVaziateFotVaHayat("fout");

            estefadeKonandeganAzSarmayeBimeList.add(estefadeKonandeHayat);
            estefadeKonandeganAzSarmayeBimeList.add(estefadeKonandeFout);

        }

        List<EstefadeKonandeganAzSarmayeBime> newEstefadeKonandeganAzSarmayeBimeList = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        if (estefadeKonandeganAzSarmayeBimeList != null) {
            for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : estefadeKonandeganAzSarmayeBimeList) {
                EstefadeKonandeganAzSarmayeBime newEstefadeKonandegan = new EstefadeKonandeganAzSarmayeBime();
                newEstefadeKonandegan.setDarsadeSahm(estefadeKonandeganAzSarmayeBime.getDarsadeSahm());
                newEstefadeKonandegan.setKodeEghtesadi(estefadeKonandeganAzSarmayeBime.getKodeEghtesadi());
                newEstefadeKonandegan.setKodeMelli(estefadeKonandeganAzSarmayeBime.getKodeMelli());
                newEstefadeKonandegan.setMahalleSabt(estefadeKonandeganAzSarmayeBime.getMahalleSabt());
                newEstefadeKonandegan.setMahalleSodoorShenasnameh(estefadeKonandeganAzSarmayeBime.getMahalleSodoorShenasnameh());
                newEstefadeKonandegan.setNamePedar(estefadeKonandeganAzSarmayeBime.getNamePedar());
                newEstefadeKonandegan.setMahalleTavallod(estefadeKonandeganAzSarmayeBime.getMahalleTavallod());
                newEstefadeKonandegan.setName(estefadeKonandeganAzSarmayeBime.getName());
                newEstefadeKonandegan.setNameKhanevadegi(estefadeKonandeganAzSarmayeBime.getNameKhanevadegi());
                newEstefadeKonandegan.setNesbatBabimeShode(estefadeKonandeganAzSarmayeBime.getNesbatBabimeShode());
                newEstefadeKonandegan.setNoeZiNafea(estefadeKonandeganAzSarmayeBime.getNoeZiNafea());
                newEstefadeKonandegan.setOlaviat(estefadeKonandeganAzSarmayeBime.getOlaviat());
                newEstefadeKonandegan.setShomareSabt(estefadeKonandeganAzSarmayeBime.getShomareSabt());
                newEstefadeKonandegan.setShomareShenasname(estefadeKonandeganAzSarmayeBime.getShomareShenasname());
                newEstefadeKonandegan.setTarikhSabt(estefadeKonandeganAzSarmayeBime.getTarikhSabt());
                newEstefadeKonandegan.setTarikhTavallod(estefadeKonandeganAzSarmayeBime.getTarikhTavallod());
                newEstefadeKonandegan.setVaziateFotVaHayat(estefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat());
                if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                    newEstefadeKonandegan.setPishnehad(newLocalPishnehad);
                else
                    newEstefadeKonandegan.setPishnehad(newPishnehad);
                newEstefadeKonandeganAzSarmayeBimeList.add(newEstefadeKonandegan);
            }
            estefadeKonandeganAzSarmayeBimeDAO.saveOrUpdateAll(newEstefadeKonandeganAzSarmayeBimeList);
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newLocalPishnehad.setEstefadeKonandeganAzSarmayeBime(newEstefadeKonandeganAzSarmayeBimeList);
            else
                newPishnehad.setEstefadeKonandeganAzSarmayeBime(newEstefadeKonandeganAzSarmayeBimeList);
        }


        Estelam newEstelam = new Estelam();

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            newPishnehad.getBimeShode().setShakhs(shakhsDAO.findById(newPishnehad.getBimeShode().getShakhs().getId()));
            newPishnehad.getBimeGozar().setShakhs(shakhsDAO.findById(newPishnehad.getBimeGozar().getShakhs().getId()));
        }

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            Shakhs bimeShodeShakhs = newPishnehad.getBimeShode().getShakhs();
            BimeShode newBimeshode = new BimeShode();
            if (shouldCreateShakhsBimeShode) {
                Shakhs newBimeShodeShakhs = new Shakhs();
                newBimeShodeShakhs.setMahalleSabt(bimeShodeShakhs.getMahalleSabt());
                newBimeShodeShakhs.setMahalleSodoreShenasnameh(bimeShodeShakhs.getMahalleSodoreShenasnameh());
                newBimeShodeShakhs.setMahalleTavallod(bimeShodeShakhs.getMahalleTavallod());
                newBimeShodeShakhs.setDolatiKhososi(bimeShodeShakhs.getDolatiKhososi());
                newBimeShodeShakhs.setIraniOrAtbaeKhareji(bimeShodeShakhs.getIraniOrAtbaeKhareji());
                newBimeShodeShakhs.setJensiat(bimeShodeShakhs.getJensiat());
                newBimeShodeShakhs.setKodeEghtesadi(bimeShodeShakhs.getKodeEghtesadi());
                newBimeShodeShakhs.setKodeMelliShenasayi(bimeShodeShakhs.getKodeMelliShenasayi());
                newBimeShodeShakhs.setName(bimeShodeShakhs.getName());
                newBimeShodeShakhs.setNameKhanevadegi(bimeShodeShakhs.getNameKhanevadegi());
                newBimeShodeShakhs.setNamePedar(bimeShodeShakhs.getNamePedar());
                newBimeShodeShakhs.setNoeFaaliat(bimeShodeShakhs.getNoeFaaliat());
                newBimeShodeShakhs.setPishvand(bimeShodeShakhs.getPishvand());
                newBimeShodeShakhs.setShoghleAsli(bimeShodeShakhs.getShoghleAsli());
                newBimeShodeShakhs.setShoghleFarei(bimeShodeShakhs.getShoghleFarei());
                newBimeShodeShakhs.setShomareSabt(bimeShodeShakhs.getShomareSabt());
                newBimeShodeShakhs.setTarikheSabt(bimeShodeShakhs.getTarikheSabt());
                newBimeShodeShakhs.setTarikheTavallod(bimeShodeShakhs.getTarikheTavallod());
                newBimeShodeShakhs.setShomareShenasnameh(bimeShodeShakhs.getShomareShenasnameh());
                newBimeShodeShakhs.setType(bimeShodeShakhs.getType());
                newBimeShodeShakhs.setTypeString(bimeShodeShakhs.getTypeString());
                newBimeShodeShakhs.setVaziateTaahol(bimeShodeShakhs.getVaziateTaahol());
                shakhsDAO.save(newBimeShodeShakhs);
                newBimeshode.setShakhs(newBimeShodeShakhs);
            } else {
                newBimeshode.setShakhs(bimeShodeShakhs);
            }
            bimeShodeDAO.save(newBimeshode);
//            newPishnehad.setBimeShode(newBimeshode);
            newLocalPishnehad.setBimeShode(newBimeshode);
            Address addressBimeShode = newPishnehad.getAddressBimeShode();
            if(addressBimeShode.getId()==null)
            {
                Address newAddressBimeShode = new Address();

                newAddressBimeShode.setTelephoneManzel(addressBimeShode.getTelephoneManzel());
                newAddressBimeShode.setCodeTelephoneMahalleKar(addressBimeShode.getCodeTelephoneMahalleKar());
                newAddressBimeShode.setCodeTelephoneManzel(addressBimeShode.getCodeTelephoneManzel());
                newAddressBimeShode.setKodePostiMahallekar(addressBimeShode.getKodePostiMahallekar());
                newAddressBimeShode.setKodePostiManzel(addressBimeShode.getKodePostiManzel());
                newAddressBimeShode.setNeshaniMahalleKar(addressBimeShode.getNeshaniMahalleKar());
                newAddressBimeShode.setNeshaniManzel(addressBimeShode.getNeshaniManzel());
                newAddressBimeShode.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeShode.getOstaanMahalleKar().getCityId()));
                newAddressBimeShode.setOstaanManzel(constantItemsService.findCityById(addressBimeShode.getOstaanManzel().getCityId()));
                newAddressBimeShode.setPosteElectronic(addressBimeShode.getPosteElectronic());
                newAddressBimeShode.setShahrMahalleKar(constantItemsService.findCityById(addressBimeShode.getShahrMahalleKar().getCityId()));
                newAddressBimeShode.setShahrManzel(constantItemsService.findCityById(addressBimeShode.getShahrManzel().getCityId()));
                newAddressBimeShode.setTelephoneHamrah(addressBimeShode.getTelephoneHamrah());
                newAddressBimeShode.setTelephoneMahalleKar(addressBimeShode.getTelephoneMahalleKar());
                addressDAO.save(newAddressBimeShode);
                newLocalPishnehad.setAddressBimeShode(newAddressBimeShode);
            }
//            newPishnehad.setAddressBimeShode(newAddressBimeShode);
            else
            {
                newLocalPishnehad.setAddressBimeShode(addressBimeShode);
            }
        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
            if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.SameAsBimeGozar)) {
                BimeShode newBimeshode = new BimeShode();
                newBimeshode.setShakhs(oldPishnehad.getBimeGozar().getShakhs());
                bimeShodeDAO.save(newBimeshode);
                newPishnehad.setBimeShode(newBimeshode);
                Address addressBimeGozar = oldPishnehad.getAddressBimeGozar();
                Address newAddressBimeShode = new Address();
                newAddressBimeShode.setTelephoneManzel(addressBimeGozar.getTelephoneManzel());
                newAddressBimeShode.setCodeTelephoneMahalleKar(addressBimeGozar.getCodeTelephoneMahalleKar());
                newAddressBimeShode.setCodeTelephoneManzel(addressBimeGozar.getCodeTelephoneManzel());
                newAddressBimeShode.setKodePostiMahallekar(addressBimeGozar.getKodePostiMahallekar());
                newAddressBimeShode.setKodePostiManzel(addressBimeGozar.getKodePostiManzel());
                newAddressBimeShode.setNeshaniMahalleKar(addressBimeGozar.getNeshaniMahalleKar());
                newAddressBimeShode.setNeshaniManzel(addressBimeGozar.getNeshaniManzel());
                newAddressBimeShode.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeGozar.getOstaanMahalleKar().getCityId()));
                newAddressBimeShode.setOstaanManzel(constantItemsService.findCityById(addressBimeGozar.getOstaanManzel().getCityId()));
                newAddressBimeShode.setPosteElectronic(addressBimeGozar.getPosteElectronic());
                newAddressBimeShode.setShahrMahalleKar(constantItemsService.findCityById(addressBimeGozar.getShahrMahalleKar().getCityId()));
                newAddressBimeShode.setShahrManzel(constantItemsService.findCityById(addressBimeGozar.getShahrManzel().getCityId()));
                newAddressBimeShode.setTelephoneHamrah(addressBimeGozar.getTelephoneHamrah());
                newAddressBimeShode.setTelephoneMahalleKar(addressBimeGozar.getTelephoneMahalleKar());
                addressDAO.save(newAddressBimeShode);
                newPishnehad.setAddressBimeShode(newAddressBimeShode);
            } else if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
                shakhsDAO.save(newPishnehad.getBimeShode().getShakhs());
                bimeShodeDAO.save(newPishnehad.getBimeShode());
                addressDAO.save(newPishnehad.getAddressBimeShode());
            }
            newEstelam.setSeneBimeie(newPishnehad.getBimeShode().getShakhs().getSen());
            newEstelam.setSen_bime_shode(newPishnehad.getBimeShode().getShakhs().getSen());
            newEstelam.setTarikh_tavalod(newPishnehad.getBimeShode().getShakhs().getTarikheTavallod());
        }

        //=================
//        logService.getBimeGozarOfPishnehadChangesList();

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
            BimeGozar bimeGozar = newPishnehad.getBimeGozar();
            Shakhs bimeGozarShakhs = newPishnehad.getBimeGozar().getShakhs();
            BimeGozar newBimegozar = new BimeGozar();
            newBimegozar.setDaramadeMahiane(bimeGozar.getDaramadeMahiane());
            newBimegozar.setNesbatBabimeShode(bimeGozar.getNesbatBabimeShode());
            if (oldPishnehad.getBimeGozar().getShakhs().getId().equals(oldPishnehad.getBimeShode().getShakhs().getId())) {
//                newBimegozar.setShakhs(newPishnehad.getBimeShode().getShakhs());
                newBimegozar.setShakhs(newLocalPishnehad.getBimeShode().getShakhs());
            } else {
                if (shouldCreateShakhsBimeGozar) {
                    Shakhs newBimeGozarShakhs = new Shakhs();
                    newBimeGozarShakhs.setMahalleSabt(bimeGozarShakhs.getMahalleSabt());
                    newBimeGozarShakhs.setMahalleSodoreShenasnameh(bimeGozarShakhs.getMahalleSodoreShenasnameh());
                    newBimeGozarShakhs.setMahalleTavallod(bimeGozarShakhs.getMahalleTavallod());
                    newBimeGozarShakhs.setDolatiKhososi(bimeGozarShakhs.getDolatiKhososi());
                    newBimeGozarShakhs.setIraniOrAtbaeKhareji(bimeGozarShakhs.getIraniOrAtbaeKhareji());
                    newBimeGozarShakhs.setJensiat(bimeGozarShakhs.getJensiat());
                    newBimeGozarShakhs.setKodeEghtesadi(bimeGozarShakhs.getKodeEghtesadi());
                    newBimeGozarShakhs.setKodeMelliShenasayi(bimeGozarShakhs.getKodeMelliShenasayi());
                    newBimeGozarShakhs.setName(bimeGozarShakhs.getName());
                    newBimeGozarShakhs.setNameKhanevadegi(bimeGozarShakhs.getNameKhanevadegi());
                    newBimeGozarShakhs.setNamePedar(bimeGozarShakhs.getNamePedar());
                    newBimeGozarShakhs.setNoeFaaliat(bimeGozarShakhs.getNoeFaaliat());
                    newBimeGozarShakhs.setPishvand(bimeGozarShakhs.getPishvand());
                    newBimeGozarShakhs.setShoghleAsli(bimeGozarShakhs.getShoghleAsli());
                    newBimeGozarShakhs.setShoghleFarei(bimeGozarShakhs.getShoghleFarei());
                    newBimeGozarShakhs.setShomareSabt(bimeGozarShakhs.getShomareSabt());
                    newBimeGozarShakhs.setTarikheSabt(bimeGozarShakhs.getTarikheSabt());
                    newBimeGozarShakhs.setTarikheTavallod(bimeGozarShakhs.getTarikheTavallod());
                    newBimeGozarShakhs.setShomareShenasnameh(bimeGozarShakhs.getShomareShenasnameh());
                    newBimeGozarShakhs.setType(bimeGozarShakhs.getType());
                    newBimeGozarShakhs.setTypeString(bimeGozarShakhs.getTypeString());
                    newBimeGozarShakhs.setVaziateTaahol(bimeGozarShakhs.getVaziateTaahol());
                    shakhsDAO.save(newBimeGozarShakhs);
                    newBimegozar.setShakhs(newBimeGozarShakhs);
                } else {
                    newBimegozar.setShakhs(bimeGozarShakhs);
                }
            }

//            newPishnehad.setBimeGozar(newBimegozar);
            newLocalPishnehad.setBimeGozar(newBimegozar);
            bimeGozarDAO.save(newBimegozar);
            Address addressBimeGozar = newPishnehad.getAddressBimeGozar();
            if(addressBimeGozar.getId()==null)
            {
                Address newAddressBimeGozar = new Address();
                newAddressBimeGozar.setTelephoneManzel(addressBimeGozar.getTelephoneManzel());
                newAddressBimeGozar.setCodeTelephoneMahalleKar(addressBimeGozar.getCodeTelephoneMahalleKar());
                newAddressBimeGozar.setCodeTelephoneManzel(addressBimeGozar.getCodeTelephoneManzel());
                newAddressBimeGozar.setKodePostiMahallekar(addressBimeGozar.getKodePostiMahallekar());
                newAddressBimeGozar.setKodePostiManzel(addressBimeGozar.getKodePostiManzel());
                newAddressBimeGozar.setNeshaniMahalleKar(addressBimeGozar.getNeshaniMahalleKar());
                newAddressBimeGozar.setNeshaniManzel(addressBimeGozar.getNeshaniManzel());
                newAddressBimeGozar.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeGozar.getOstaanMahalleKar().getCityId()));
                if (newBimegozar.getShakhs().getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
                    newAddressBimeGozar.setOstaanManzel(constantItemsService.findCityById(addressBimeGozar.getOstaanManzel().getCityId()));
                newAddressBimeGozar.setPosteElectronic(addressBimeGozar.getPosteElectronic());
                newAddressBimeGozar.setShahrMahalleKar(constantItemsService.findCityById(addressBimeGozar.getShahrMahalleKar().getCityId()));
                if (newBimegozar.getShakhs().getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
                    newAddressBimeGozar.setShahrManzel(constantItemsService.findCityById(addressBimeGozar.getShahrManzel().getCityId()));
                newAddressBimeGozar.setTelephoneHamrah(addressBimeGozar.getTelephoneHamrah());
                newAddressBimeGozar.setTelephoneMahalleKar(addressBimeGozar.getTelephoneMahalleKar());
                addressDAO.save(newAddressBimeGozar);
                newLocalPishnehad.setAddressBimeGozar(newAddressBimeGozar);
            }
//            newPishnehad.setAddressBimeGozar(newAddressBimeGozar);
            else
            {
                newLocalPishnehad.setAddressBimeGozar(addressBimeGozar);
            }
        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
            if (mafad.getBimegozarSource().equals(PishnehadDuplicationRules.PishnehadCopySource.SherkateTarafeGharardad)) {
                BimeGozar newBimegozar = new BimeGozar();
                newBimegozar.setDaramadeMahiane(null);
                newBimegozar.setNesbatBabimeShode("نامعلوم");

                List<SearchResult> newBimeGozarShakhsList = shakhsDAO.doSearchHoghughi(gharardad.getNameSherkat(), gharardad.getShomareSabt());
                Shakhs newBimeGozarShakhs;
                if (newBimeGozarShakhsList.size() > 0) { // Shakhs found
                    newBimeGozarShakhs = shakhsDAO.findById(newBimeGozarShakhsList.get(0).getId());
                } else { // Shakhs not found, create it
                    newBimeGozarShakhs = new Shakhs();
                    newBimeGozarShakhs.setMahalleSabt(gharardad.getShahr());
                    newBimeGozarShakhs.setMahalleSodoreShenasnameh(null);
                    newBimeGozarShakhs.setMahalleTavallod(null);
                    newBimeGozarShakhs.setDolatiKhososi(null); // sherkat
                    newBimeGozarShakhs.setIraniOrAtbaeKhareji(null);
                    newBimeGozarShakhs.setJensiat(null);
                    newBimeGozarShakhs.setKodeEghtesadi(null);  // sherkat , shomare roozname ?
                    newBimeGozarShakhs.setKodeMelliShenasayi(null);
                    newBimeGozarShakhs.setName(gharardad.getNameSherkat());
                    newBimeGozarShakhs.setNameKhanevadegi(null);
                    newBimeGozarShakhs.setNamePedar(null);
                    newBimeGozarShakhs.setNoeFaaliat(null); // sherkat
                    newBimeGozarShakhs.setPishvand(null);
                    newBimeGozarShakhs.setShoghleAsli(null);
                    newBimeGozarShakhs.setShoghleFarei(null);
                    newBimeGozarShakhs.setShomareSabt(gharardad.getShomareSabt());
                    newBimeGozarShakhs.setTarikheSabt(gharardad.getTarikhSabt());
                    newBimeGozarShakhs.setTarikheTavallod(null);
                    newBimeGozarShakhs.setShomareShenasnameh(null);
                    newBimeGozarShakhs.setType(Shakhs.BimeGozarType.HOGHOGHI);
                    newBimeGozarShakhs.setTypeString(null);
                    newBimeGozarShakhs.setVaziateTaahol(null);
                    shakhsDAO.save(newBimeGozarShakhs);
                }
                newBimegozar.setShakhs(newBimeGozarShakhs);
                newPishnehad.setBimeGozar(newBimegozar);
                Address newAddressBimeGozar = new Address();
                newAddressBimeGozar.setTelephoneManzel(null);
                newAddressBimeGozar.setCodeTelephoneMahalleKar(null);
                newAddressBimeGozar.setCodeTelephoneManzel(null);
                newAddressBimeGozar.setKodePostiMahallekar(null);
                newAddressBimeGozar.setKodePostiManzel(null);
                newAddressBimeGozar.setNeshaniMahalleKar(gharardad.getNeshani());
                newAddressBimeGozar.setNeshaniManzel(null);
                newAddressBimeGozar.setOstaanMahalleKar(gharardad.getOstan());
                newAddressBimeGozar.setOstaanManzel(null);
                newAddressBimeGozar.setPosteElectronic(null);
                newAddressBimeGozar.setShahrMahalleKar(gharardad.getShahr());
                newAddressBimeGozar.setShahrManzel(null);
                newAddressBimeGozar.setTelephoneHamrah(null);
                newAddressBimeGozar.setTelephoneMahalleKar(gharardad.getTelphone());
                addressDAO.save(newAddressBimeGozar);
                newPishnehad.setAddressBimeGozar(newAddressBimeGozar);
                bimeGozarDAO.save(newBimegozar);
                // shomare ruzname va fax mundan
            } else if (mafad.getBimegozarSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
                shakhsDAO.save(newPishnehad.getBimeGozar().getShakhs());
                bimeShodeDAO.save(newPishnehad.getBimeGozar());
                addressDAO.save(newPishnehad.getAddressBimeGozar());
            }

        }

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi) || mafad.getSoalateOmoomiSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate)) {
            SoalateOmomiAzBimeShode soalateOmomiAzBimeShode;
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
                soalateOmomiAzBimeShode = newPishnehad.getSoalateOmomiAzBimeShode();
            } else {
                soalateOmomiAzBimeShode = oldPishnehad.getSoalateOmomiAzBimeShode();
            }
            SoalateOmomiAzBimeShode newSoalateOmumi = new SoalateOmomiAzBimeShode();
            newSoalateOmumi.setGhad_bime_shode(soalateOmomiAzBimeShode.getGhad_bime_shode());
            newSoalateOmumi.setKhdemat_nezaam_vazife(soalateOmomiAzBimeShode.getKhdemat_nezaam_vazife());
            newSoalateOmumi.setMoaafiyat_pezeshki(soalateOmomiAzBimeShode.getMoaafiyat_pezeshki());
            newSoalateOmumi.setTozih_faaliyat_janbi(soalateOmomiAzBimeShode.getTozih_faaliyat_janbi());
            newSoalateOmumi.setVazn_bime_shode(soalateOmomiAzBimeShode.getVazn_bime_shode());
            soalateOmomiAzBimeShodeDAO.save(newSoalateOmumi);
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newLocalPishnehad.setSoalateOmomiAzBimeShode(newSoalateOmumi);
            else
                newPishnehad.setSoalateOmomiAzBimeShode(newSoalateOmumi);
        }

        Foroshande foroshande = oldPishnehad.getForoshande();
        Foroshande newForushande = new Foroshande();
        newForushande.setEtelaat_kaamel(foroshande.getEtelaat_kaamel());
        newForushande.setModat_shenaakht(foroshande.getModat_shenaakht());
        newForushande.setMolaahezaat_az_salaamat_feli(foroshande.getMolaahezaat_az_salaamat_feli());
        newForushande.setPishnahaad_dahande_mishenaasid(foroshande.getPishnahaad_dahande_mishenaasid());
        newForushande.setSehat_emzaa(foroshande.getSehat_emzaa());
        newForushande.setTozihe_molaahezaat(foroshande.getTozihe_molaahezaat());
        foroshandeDAO.save(newForushande);
        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
            newLocalPishnehad.setForoshande(newForushande);
        else
            newPishnehad.setForoshande(newForushande);

        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi) || mafad.getBimenameSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate)) {
            Estelam estelam;
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
                estelam = newPishnehad.getEstelam();
                newEstelam.setDarsad_ezafe_nerkh_pezeshki(newPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());
            } else {
                estelam = oldPishnehad.getEstelam();
                newEstelam.setDarsad_ezafe_nerkh_pezeshki(oldPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());
            }
            newEstelam.setDarsad_takhfif_karmozd_karmozd(estelam.getDarsad_takhfif_karmozd_karmozd());
            newEstelam.setDarsad_takhfif_vosool(estelam.getDarsad_takhfif_vosool());
            newEstelam.setHagh_bime_pardakhti(estelam.getHagh_bime_pardakhti());
            newEstelam.setJoziyate_pooshesh_amraz_khas(estelam.getJoziyate_pooshesh_amraz_khas());
            newEstelam.setJoziyate_pooshesh_moafiat(estelam.getJoziyate_pooshesh_moafiat());
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
                // hagh bime avvalie duplicate nemishe
                if (newPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal() != null)
                    newEstelam.setMablagh_seporde_ebtedaye_sal(newPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal());
                else
                    newEstelam.setMablagh_seporde_ebtedaye_sal(oldPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal());
            }
            newEstelam.setModat_bimename(estelam.getModat_bimename());
            newEstelam.setModdat_zaman_daryaft_mostamari(estelam.getModdat_zaman_daryaft_mostamari());
            newEstelam.setNahve_daryaft_mostamari(estelam.getNahve_daryaft_mostamari());
            newEstelam.setNahve_pardakht_hagh_bime(estelam.getNahve_pardakht_hagh_bime());
            newEstelam.setNahveye_daryafte_andukhte_entehaye_modat_bimename(estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename());
            newEstelam.setNahve_kasr_hagh_bime_poosheshhaye_ezafi(estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi());
            newEstelam.setNam_bime_shode(estelam.getNam_bime_shode());
            newEstelam.setNerkh_afzayesh_salaneh_hagh_bime(estelam.getNerkh_afzayesh_salaneh_hagh_bime());
            newEstelam.setNerkh_afzayesh_salaneh_mostamari(estelam.getNerkh_afzayesh_salaneh_mostamari());
            newEstelam.setNerkh_afzayesh_salaneh_sarmaye_fot(estelam.getNerkh_afzayesh_salaneh_sarmaye_fot());
            newEstelam.setNoe_tarh(estelam.getNoe_tarh());
            newEstelam.setPooshesh_amraz_khas(estelam.getPooshesh_amraz_khas());
            newEstelam.setPooshesh_amraz_tarikh_payan(estelam.getPooshesh_amraz_tarikh_payan());
            newEstelam.setPooshesh_amraz_tarikh_shoru(estelam.getPooshesh_amraz_tarikh_shoru());
            newEstelam.setPooshesh_fot_dar_asar_haadese(estelam.getPooshesh_fot_dar_asar_haadese());
            newEstelam.setPooshesh_fot_dar_asar_zelzele(estelam.getPooshesh_fot_dar_asar_zelzele());
            newEstelam.setPooshesh_fot_tarikh_payan(estelam.getPooshesh_fot_tarikh_payan());
            newEstelam.setPooshesh_fot_tarikh_shoru(estelam.getPooshesh_fot_tarikh_shoru());
            newEstelam.setPooshesh_moafiat(estelam.getPooshesh_moafiat());
            newEstelam.setPooshesh_naghs_ozv(estelam.getPooshesh_naghs_ozv());
            newEstelam.setPooshesh_naghs_tarikh_payan(estelam.getPooshesh_naghs_tarikh_payan());
            newEstelam.setPooshesh_naghs_tarikh_shoru(estelam.getPooshesh_naghs_tarikh_shoru());
            newEstelam.setPooshesh_zelzele_tarikh_payan(estelam.getPooshesh_zelzele_tarikh_payan());
            newEstelam.setPooshesh_zelzele_tarikh_shoru(estelam.getPooshesh_zelzele_tarikh_shoru());
            newEstelam.setSarmaye_paye_fot(estelam.getSarmaye_paye_fot());
            newEstelam.setSarmaye_paye_fot_dar_asar_hadese(estelam.getSarmaye_paye_fot_dar_asar_hadese());
            newEstelam.setSarmaye_pooshesh_amraz_khas(estelam.getSarmaye_pooshesh_amraz_khas());
            newEstelam.setSarmaye_pooshesh_naghs_ozv(estelam.getSarmaye_pooshesh_naghs_ozv());
            newEstelam.setTabaghe_khatar(oldPishnehad.getEstelam().getTabaghe_khatar());
            newEstelam.setTabaghe_khatar_naghsozv(oldPishnehad.getEstelam().getTabaghe_khatar_naghsozv());
            if (newEstelam.getTabaghe_khatar() != null && newEstelam.getTabaghe_khatar().equals("0"))
                newEstelam.setTabaghe_khatar("1");
            if (newEstelam.getTabaghe_khatar_naghsozv() != null && newEstelam.getTabaghe_khatar_naghsozv().equals("0"))
                newEstelam.setTabaghe_khatar_naghsozv("1");
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//                newPishnehad.setBimename(oldPishnehad.getBimename());
                newLocalPishnehad.setBimename(oldPishnehad.getBimename());
//                if (newPishnehad.getBimeShode().getShakhs().getTarikheTavallod().equals(oldPishnehad.getBimeShode().getShakhs().getTarikheTavallod())) {
                if (newLocalPishnehad.getBimeShode().getShakhs().getTarikheTavallod().equals(oldPishnehad.getBimeShode().getShakhs().getTarikheTavallod())) {
                    newEstelam.setSen_bime_shode(oldPishnehad.getEstelam().getSen_bime_shode());
                    newEstelam.setSeneBimeie(oldPishnehad.getEstelam().getSeneBimeie());
                    newEstelam.setTarikh_tavalod(oldPishnehad.getEstelam().getTarikh_tavalod());
                } else {
                    newEstelam.setSen_bime_shode(estelam.getSen_bime_shode());
                    newEstelam.setSeneBimeie(estelam.getSeneBimeie());
                    newEstelam.setTarikh_tavalod(estelam.getTarikh_tavalod());
                }
            }
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newEstelam.setPishnehad(newLocalPishnehad);
            else
                newEstelam.setPishnehad(newPishnehad);
            estelamDAO.save(newEstelam);
            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
                newLocalPishnehad.setEstelam(newEstelam);
            else
                newPishnehad.setEstelam(newEstelam);
        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami) && mafad.getBimenameSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
            estelamDAO.save(newPishnehad.getEstelam());
        }


        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            pishnehadDAO.save(newPishnehad);
            List<TransitionLog> transitionLogs = new ArrayList<TransitionLog>();
//            transitionLogs.addAll(oldPishnehad.getTransitionLogs());
            for(TransitionLog tLog : oldPishnehad.getTransitionLogs())
            {
                TransitionLog trnsLog=new TransitionLog();
                trnsLog.setDate(tLog.getDate());
                trnsLog.setTime(tLog.getTime());
                trnsLog.setTransition(tLog.getTransition());
                trnsLog.setMessage(tLog.getMessage());
                trnsLog.setUser(tLog.getUser());
                trnsLog.setPishnehad(newLocalPishnehad);
                transitionLogService.save(trnsLog);
                transitionLogs.add(trnsLog);
            }
            newLocalPishnehad.setTransitionLogs(transitionLogs);
            pishnehadDAO.save(newLocalPishnehad);
            Shakhs p= new Shakhs();
            p.setName("bib");
            System.out.println(p.getName());
            Shakhs p1= p;
            p1.setName("bib1");
            System.out.println(p.getName());

            return newLocalPishnehad;
        } else {
            pishnehadDAO.update(newPishnehad);
            return newPishnehad;
        }

//=================
    }
//    @Transactional
//    public Pishnehad savePishnehadForElhaghie(Pishnehad newPishnehad, Pishnehad oldPishnehad, PishnehadDuplicationRules mafad, Gharardad gharardad, User user, boolean shouldCreateShakhsBimeGozar, boolean shouldCreateShakhsBimeShode) {
//        newPishnehad.setId(null);
//        newPishnehad.setVersion(DateUtil.getCurrentDate());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setValid(false);
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setValid(true);
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setCreatedDate(oldPishnehad.getCreatedDate());
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setCreatedDate(DateUtil.getCurrentDate());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setCreatedTime(oldPishnehad.getCreatedTime());
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setCreatedTime(DateUtil.getCurrentTime());
//        newPishnehad.setState(oldPishnehad.getState());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setUserCreator(oldPishnehad.getUserCreator());
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setUserCreator(gharardad.getNamayande().getNamayandegiUser());
//        newPishnehad.setKarshenas(oldPishnehad.getKarshenas());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setNamayande(oldPishnehad.getNamayande());
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setNamayande(gharardad.getNamayande());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
//            newPishnehad.setGharardad(gharardad);
//            newPishnehad.setTarh(gharardad.getTarh());
//        }
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            if (OmrUtil.userHasRole(user, Constant.ROLE_NAMAYANDE)) {
//                newPishnehad.setTarh(oldPishnehad.getTarh());
//                newPishnehad.setGharardad(oldPishnehad.getGharardad());
//            } else {
//                if (newPishnehad.getTarh() != null && newPishnehad.getTarh().getId() != null) {
//                    newPishnehad.setTarh(constantsService.findTarhById(newPishnehad.getTarh().getId()));
//                } else {
//                    newPishnehad.setTarh(null);
//                }
//                if (newPishnehad.getGharardad() != null && newPishnehad.getGharardad().getId() != null) {
//                    newPishnehad.setGharardad(gharardadService.findById(newPishnehad.getGharardad().getId()));
//                } else {
//                    newPishnehad.setGharardad(null);
//                }
//            }
//        }
//        newPishnehad.setNoeGharardad(oldPishnehad.getNoeGharardad());
//        newPishnehad.setNoePishnehad(oldPishnehad.getNoePishnehad());
//        newPishnehad.setOptions(oldPishnehad.getOptions());
//        newPishnehad.setAdameControllMohasebat(oldPishnehad.getAdameControllMohasebat());
//        newPishnehad.setPishpardakhtSanad(oldPishnehad.getPishpardakhtSanad());
//        newPishnehad.setPishpardakhtType(oldPishnehad.getPishpardakhtType());
//        newPishnehad.setProcessor(oldPishnehad.getProcessor());
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi))
//            newPishnehad.setRadif(oldPishnehad.getRadif());
//        else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            newPishnehad.setRadif(sequenceService.nextShomareRadif(newPishnehad.getNamayande()));
//
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami))
//            pishnehadDAO.save(newPishnehad);
//
////-----------------
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            List<Fish> fishList = oldPishnehad.getFishs();
//            List<Fish> newFishList = new ArrayList<Fish>();
//            if (fishList != null) {
//                for (Fish fish : fishList) {
//                    Fish newFish = new Fish();
//                    newFish.setBankName(fish.getBankName());
//                    newFish.setFileOtherId(fish.getFileOtherId());
//                    newFish.setFileOtherName(fish.getFileOtherName());
//                    newFish.setFilePishId(fish.getFilePishId());
//                    newFish.setFilePishName(fish.getFilePishName());
//                    newFish.setFileScannedId(fish.getFileScannedId());
//                    newFish.setFileScannedName(fish.getFileScannedName());
//                    newFish.setFound(fish.getFound());
//                    newFish.setKodeShobe(fish.getKodeShobe());
//                    newFish.setMablagh(fish.getMablagh());
//                    newFish.setPaymentType(fish.getPaymentType());
//                    newFish.setShomare(fish.getShomare());
////                    newFish.setTarikh(fish.getShomare());
//                    newFish.setTarikh(fish.getTarikh());
//                    newFish.setTime(fish.getTime());
//                    newFish.setCredebit(fish.getCredebit());
//                    newFish.setPishnehad(newPishnehad);
//                    newFishList.add(newFish);
//                }
//                pishnehadDAO.saveAllFish(newFishList);
//                newPishnehad.setFishs(newFishList);
//            }
//        }
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            Zamaem zamaem = oldPishnehad.getZamaem();
//            if (zamaem != null) {
//                Zamaem result = new Zamaem();
//                result.setFileEnserafDescription(zamaem.getFileEnserafDescription());
//                result.setFileEnserafId(zamaem.getFileEnserafId());
//                result.setFileEnserafName(zamaem.getFileEnserafName());
//                result.setFileElameHesabDescription(zamaem.getFileElameHesabDescription());
//                result.setFileElameHesabId(zamaem.getFileElameHesabId());
//                result.setFileElameHesabName(zamaem.getFileElameHesabName());
//                result.setFileMoshaverePezeshkId(zamaem.getFileMoshaverePezeshkId());
//                result.setFileMoshaverePezeshkName(zamaem.getFileMoshaverePezeshkName());
//                result.setFileMoshaverePezeshkDesc(zamaem.getFileMoshaverePezeshkDesc());
//                result.setFileMoshaverePezeshkJavabId(zamaem.getFileMoshaverePezeshkJavabId());
//                result.setFileMoshaverePezeshkJavabName(zamaem.getFileMoshaverePezeshkJavabName());
//                result.setFileMoshaverePezeshkJavabDesc(zamaem.getFileMoshaverePezeshkJavabDesc());
//                result.setFileFishDescription1(zamaem.getFileFishDescription1());
//                result.setFileFishDescription2(zamaem.getFileFishDescription2());
//                result.setFileFishDescription3(zamaem.getFileFishDescription3());
//                result.setFileFishDescription4(zamaem.getFileFishDescription4());
//                result.setFileFishDescription5(zamaem.getFileFishDescription5());
//                result.setFileFishId1(zamaem.getFileFishId1());
//                result.setFileFishId2(zamaem.getFileFishId2());
//                result.setFileFishId3(zamaem.getFileFishId3());
//                result.setFileFishId4(zamaem.getFileFishId4());
//                result.setFileFishId5(zamaem.getFileFishId5());
//                result.setFileFishName1(zamaem.getFileFishName1());
//                result.setFileFishName2(zamaem.getFileFishName2());
//                result.setFileFishName3(zamaem.getFileFishName3());
//                result.setFileFishName4(zamaem.getFileFishName4());
//                result.setFileFishName5(zamaem.getFileFishName5());
//                result.setFilePishDigitalDescription1(zamaem.getFilePishDigitalDescription1());
//                result.setFilePishDigitalDescription2(zamaem.getFilePishDigitalDescription2());
//                result.setFilePishDigitalDescription3(zamaem.getFilePishDigitalDescription3());
//                result.setFilePishDigitalDescription4(zamaem.getFilePishDigitalDescription4());
//                result.setFilePishDigitalDescription5(zamaem.getFilePishDigitalDescription5());
//                result.setFilePishDigitalId1(zamaem.getFilePishDigitalId1());
//                result.setFilePishDigitalId2(zamaem.getFilePishDigitalId2());
//                result.setFilePishDigitalId3(zamaem.getFilePishDigitalId3());
//                result.setFilePishDigitalId4(zamaem.getFilePishDigitalId4());
//                result.setFilePishDigitalId5(zamaem.getFilePishDigitalId5());
//                result.setFilePishDigitalName1(zamaem.getFilePishDigitalName1());
//                result.setFilePishDigitalName2(zamaem.getFilePishDigitalName2());
//                result.setFilePishDigitalName3(zamaem.getFilePishDigitalName3());
//                result.setFilePishDigitalName4(zamaem.getFilePishDigitalName4());
//                result.setFilePishDigitalName5(zamaem.getFilePishDigitalName5());
//                result.setFilePishKatbiDescription1(zamaem.getFilePishKatbiDescription1());
//                result.setFilePishKatbiDescription2(zamaem.getFilePishKatbiDescription2());
//                result.setFilePishKatbiDescription3(zamaem.getFilePishKatbiDescription3());
//                result.setFilePishKatbiDescription4(zamaem.getFilePishKatbiDescription4());
//                result.setFilePishKatbiDescription5(zamaem.getFilePishKatbiDescription5());
//                result.setFilePishKatbiId1(zamaem.getFilePishKatbiId1());
//                result.setFilePishKatbiId2(zamaem.getFilePishKatbiId2());
//                result.setFilePishKatbiId3(zamaem.getFilePishKatbiId3());
//                result.setFilePishKatbiId4(zamaem.getFilePishKatbiId4());
//                result.setFilePishKatbiId5(zamaem.getFilePishKatbiId5());
//                result.setFilePishKatbiName1(zamaem.getFilePishKatbiName1());
//                result.setFilePishKatbiName2(zamaem.getFilePishKatbiName2());
//                result.setFilePishKatbiName3(zamaem.getFilePishKatbiName3());
//                result.setFilePishKatbiName4(zamaem.getFilePishKatbiName4());
//                result.setFilePishKatbiName5(zamaem.getFilePishKatbiName5());
//                result.setFileSayerDescription1(zamaem.getFileSayerDescription1());
//                result.setFileSayerId1(zamaem.getFileSayerId1());
//                result.setFileSayerName1(zamaem.getFileSayerName1());
//                result.setFileSayerDescription2(zamaem.getFileSayerDescription2());
//                result.setFileSayerId2(zamaem.getFileSayerId2());
//                result.setFileSayerName2(zamaem.getFileSayerName2());
//                result.setFileSayerDescription3(zamaem.getFileSayerDescription3());
//                result.setFileSayerId3(zamaem.getFileSayerId3());
//                result.setFileSayerName3(zamaem.getFileSayerName3());
//                result.setFileSayerDescription4(zamaem.getFileSayerDescription4());
//                result.setFileSayerId4(zamaem.getFileSayerId4());
//                result.setFileSayerName4(zamaem.getFileSayerName4());
//                result.setFileSayerDescription5(zamaem.getFileSayerDescription5());
//                result.setFileSayerId5(zamaem.getFileSayerId5());
//                result.setFileSayerName5(zamaem.getFileSayerName5());
//                result.setPishnehad(zamaem.getPishnehad());
//                saveZamaem(result);
//                newPishnehad.setZamaem(result);
//
//            }
//        }
////-----------------
//
//        List<VaziateSalamatiBimeShode> vaziateSalamatiBimeShodeList;
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            vaziateSalamatiBimeShodeList = newPishnehad.getVaziateSalamatiBimeShodeList();
//        } else {
//            vaziateSalamatiBimeShodeList = oldPishnehad.getVaziateSalamatiBimeShodeList();
//        }
//
//        List<VaziateSalamatiBimeShode> newVaziateSalamatiBimeShodeList = new ArrayList<VaziateSalamatiBimeShode>();
//        if (vaziateSalamatiBimeShodeList != null) {
//            for (VaziateSalamatiBimeShode vaziateSalamatiBimeShode : vaziateSalamatiBimeShodeList) {
//                VaziateSalamatiBimeShode newVaziateSalamati = new VaziateSalamatiBimeShode();
//                newVaziateSalamati.setConstantSoalItem(vaziateSalamatiBimeShode.getConstantSoalItem());
//                newVaziateSalamati.setDarmaneAnjamShode(vaziateSalamatiBimeShode.getDarmaneAnjamShode());
//                newVaziateSalamati.setDaroyeMasrafi(vaziateSalamatiBimeShode.getDaroyeMasrafi());
//                if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//                    newVaziateSalamati.setJavabeSolal(vaziateSalamatiBimeShode.getJavabeSolal());
//                } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
//                    if (mafad.getVaziateSalamateBimeShodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AllHealthy)) {
//                        if (vaziateSalamatiBimeShode.getConstantSoalItem().getId() != 4)
//                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.KHEIR);
//                        else
//                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.BALI);
//                    } else if (mafad.getVaziateSalamateBimeShodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.BeTafkikePishnehad)) {
//                        if (vaziateSalamatiBimeShode.getConstantSoalItem().getId() != 4)
//                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.BALI);
//                        else
//                            newVaziateSalamati.setJavabeSolal(VaziateSalamatiBimeShode.JavabeSolal.KHEIR);
//                    }
//                }
//                newVaziateSalamati.setMeghdareMasraf(vaziateSalamatiBimeShode.getMeghdareMasraf());
//                newVaziateSalamati.setModateMasraf(vaziateSalamatiBimeShode.getModateMasraf());
//                newVaziateSalamati.setShoroeZamaneBimari(vaziateSalamatiBimeShode.getShoroeZamaneBimari());
//                newVaziateSalamati.setTarikheJarahi(vaziateSalamatiBimeShode.getTarikheJarahi());
//                newVaziateSalamati.setTozih(vaziateSalamatiBimeShode.getTozih());
//                newVaziateSalamati.setVaziateFelieBimeShode(vaziateSalamatiBimeShode.getVaziateFelieBimeShode());
//                newVaziateSalamati.setPishnehad(newPishnehad);
//                newVaziateSalamatiBimeShodeList.add(newVaziateSalamati);
//            }
//            vaziateSalamatiBimeShodeDAO.saveOrUpdateAll(newVaziateSalamatiBimeShodeList);
//            newPishnehad.setVaziateSalamatiBimeShodeList(newVaziateSalamatiBimeShodeList);
//        }
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            List<VaziateSalamatiKhanevadeyeBimeShode> vaziateSalamatiKhanevadeyeBimeShodeList =
//                    newPishnehad.getVaziateSalamatiKhanevadeyeBimeShode();
//            List<VaziateSalamatiKhanevadeyeBimeShode> newVaziateSalamatiKhanevadeyeBimeShodeList =
//                    new ArrayList<VaziateSalamatiKhanevadeyeBimeShode>();
//            if (vaziateSalamatiBimeShodeList != null) {
//
//                for (VaziateSalamatiKhanevadeyeBimeShode vaziateSalamatiKhanevadeyeBimeShode : vaziateSalamatiKhanevadeyeBimeShodeList) {
//                    VaziateSalamatiKhanevadeyeBimeShode newVaziat = new VaziateSalamatiKhanevadeyeBimeShode();
//                    newVaziat.setEllateFout(vaziateSalamatiKhanevadeyeBimeShode.getEllateFout());
//                    newVaziat.setNesbatBabimeShode(vaziateSalamatiKhanevadeyeBimeShode.getNesbatBabimeShode());
//                    newVaziat.setSenneDarHayat(vaziateSalamatiKhanevadeyeBimeShode.getSenneDarHayat());
//                    newVaziat.setSenneDarZamaneFout(vaziateSalamatiKhanevadeyeBimeShode.getSenneDarZamaneFout());
//                    newVaziat.setSharheEllateFout(vaziateSalamatiKhanevadeyeBimeShode.getSharheEllateFout());
//                    newVaziat.setVaziateHayat(vaziateSalamatiKhanevadeyeBimeShode.getVaziateHayat());
//                    newVaziat.setVaziateSalamati(vaziateSalamatiKhanevadeyeBimeShode.getVaziateSalamati());
//                    newVaziat.setPishnehad(newPishnehad);
//                    newVaziateSalamatiKhanevadeyeBimeShodeList.add(newVaziat);
//                }
//                vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(newVaziateSalamatiKhanevadeyeBimeShodeList);
//                newPishnehad.setVaziateSalamatiKhanevadeyeBimeShode(newVaziateSalamatiKhanevadeyeBimeShodeList);
//            }
//        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
//            vaziateSalamatiKhanevadeyeBimeShodeDAO.saveOrUpdateAll(newPishnehad.getVaziateSalamatiKhanevadeyeBimeShode());
//        }
//
//        List<SavabegheBimei> savabegheBimeiList;
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            savabegheBimeiList = newPishnehad.getSavabegheBimei();
//        } else {
//            savabegheBimeiList = oldPishnehad.getSavabegheBimei();
//        }
//
//        List<SavabegheBimei> newSavabegheBimeiList = new ArrayList<SavabegheBimei>();
//        if (savabegheBimeiList != null) {
//            for (SavabegheBimei savabegheBimei : savabegheBimeiList) {
//                SavabegheBimei newSabeghe = new SavabegheBimei();
//                newSabeghe.setNameBimeName(savabegheBimei.getNameBimeName());
//                newSabeghe.setNatijeNahayi(savabegheBimei.getNatijeNahayi());
//                newSabeghe.setNoeBimeName(savabegheBimei.getNoeBimeName());
//                newSabeghe.setSarmayeFout(savabegheBimei.getSarmayeFout());
//                newSabeghe.setSarmayeHayat(savabegheBimei.getSarmayeHayat());
//                newSabeghe.setSharheAdameSodor(savabegheBimei.getSharheAdameSodor());
//                newSabeghe.setSherkateBimeGar(savabegheBimei.getSherkateBimeGar());
//                newSabeghe.setSherkateBimeGarTozihateSayer(savabegheBimei.getSherkateBimeGar());
//                newSabeghe.setTarikheKhateme(savabegheBimei.getTarikheKhateme());
//                newSabeghe.setPishnehad(newPishnehad);
//                newSavabegheBimeiList.add(newSabeghe);
//            }
//            savabegheBimeiDAO.saveOrUpdateAll(newSavabegheBimeiList);
//            newPishnehad.setSavabegheBimei(newSavabegheBimeiList);
//        }
//
//        List<EstefadeKonandeganAzSarmayeBime> estefadeKonandeganAzSarmayeBimeList;
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            estefadeKonandeganAzSarmayeBimeList = newPishnehad.getEstefadeKonandeganAzSarmayeBime();
//        } else {
//            estefadeKonandeganAzSarmayeBimeList = oldPishnehad.getEstefadeKonandeganAzSarmayeBime();
//        }
//
//        List<EstefadeKonandeganAzSarmayeBime> newEstefadeKonandeganAzSarmayeBimeList = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
//        if (estefadeKonandeganAzSarmayeBimeList != null) {
//            for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : estefadeKonandeganAzSarmayeBimeList) {
//                EstefadeKonandeganAzSarmayeBime newEstefadeKonandegan = new EstefadeKonandeganAzSarmayeBime();
//                newEstefadeKonandegan.setDarsadeSahm(estefadeKonandeganAzSarmayeBime.getDarsadeSahm());
//                newEstefadeKonandegan.setKodeEghtesadi(estefadeKonandeganAzSarmayeBime.getKodeEghtesadi());
//                newEstefadeKonandegan.setKodeMelli(estefadeKonandeganAzSarmayeBime.getKodeMelli());
//                newEstefadeKonandegan.setMahalleSabt(estefadeKonandeganAzSarmayeBime.getMahalleSabt());
//                newEstefadeKonandegan.setMahalleTavallod(estefadeKonandeganAzSarmayeBime.getMahalleTavallod());
//                newEstefadeKonandegan.setName(estefadeKonandeganAzSarmayeBime.getName());
//                newEstefadeKonandegan.setNameKhanevadegi(estefadeKonandeganAzSarmayeBime.getNameKhanevadegi());
//                newEstefadeKonandegan.setNesbatBabimeShode(estefadeKonandeganAzSarmayeBime.getNesbatBabimeShode());
//                newEstefadeKonandegan.setNoeZiNafea(estefadeKonandeganAzSarmayeBime.getNoeZiNafea());
//                newEstefadeKonandegan.setOlaviat(estefadeKonandeganAzSarmayeBime.getOlaviat());
//                newEstefadeKonandegan.setShomareSabt(estefadeKonandeganAzSarmayeBime.getShomareSabt());
//                newEstefadeKonandegan.setShomareShenasname(estefadeKonandeganAzSarmayeBime.getShomareShenasname());
//                newEstefadeKonandegan.setTarikhSabt(estefadeKonandeganAzSarmayeBime.getTarikhSabt());
//                newEstefadeKonandegan.setTarikhTavallod(estefadeKonandeganAzSarmayeBime.getTarikhTavallod());
//                newEstefadeKonandegan.setVaziateFotVaHayat(estefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat());
//                newEstefadeKonandegan.setPishnehad(newPishnehad);
//                newEstefadeKonandeganAzSarmayeBimeList.add(newEstefadeKonandegan);
//            }
//            estefadeKonandeganAzSarmayeBimeDAO.saveOrUpdateAll(newEstefadeKonandeganAzSarmayeBimeList);
//            newPishnehad.setEstefadeKonandeganAzSarmayeBime(newEstefadeKonandeganAzSarmayeBimeList);
//        }
//
//
//        Estelam newEstelam = new Estelam();
//        newPishnehad.getBimeShode().setShakhs(shakhsDAO.findById(newPishnehad.getBimeShode().getShakhs().getId()));
//        newPishnehad.getBimeGozar().setShakhs(shakhsDAO.findById(newPishnehad.getBimeGozar().getShakhs().getId()));
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            Shakhs bimeShodeShakhs = newPishnehad.getBimeShode().getShakhs();
//            BimeShode newBimeshode = new BimeShode();
//            if (shouldCreateShakhsBimeShode) {
//                Shakhs newBimeShodeShakhs = new Shakhs();
//                newBimeShodeShakhs.setMahalleSabt(bimeShodeShakhs.getMahalleSabt());
//                newBimeShodeShakhs.setMahalleSodoreShenasnameh(bimeShodeShakhs.getMahalleSodoreShenasnameh());
//                newBimeShodeShakhs.setMahalleTavallod(bimeShodeShakhs.getMahalleTavallod());
//                newBimeShodeShakhs.setDolatiKhososi(bimeShodeShakhs.getDolatiKhososi());
//                newBimeShodeShakhs.setIraniOrAtbaeKhareji(bimeShodeShakhs.getIraniOrAtbaeKhareji());
//                newBimeShodeShakhs.setJensiat(bimeShodeShakhs.getJensiat());
//                newBimeShodeShakhs.setKodeEghtesadi(bimeShodeShakhs.getKodeEghtesadi());
//                newBimeShodeShakhs.setKodeMelliShenasayi(bimeShodeShakhs.getKodeMelliShenasayi());
//                newBimeShodeShakhs.setName(bimeShodeShakhs.getName());
//                newBimeShodeShakhs.setNameKhanevadegi(bimeShodeShakhs.getNameKhanevadegi());
//                newBimeShodeShakhs.setNamePedar(bimeShodeShakhs.getNamePedar());
//                newBimeShodeShakhs.setNoeFaaliat(bimeShodeShakhs.getNoeFaaliat());
//                newBimeShodeShakhs.setPishvand(bimeShodeShakhs.getPishvand());
//                newBimeShodeShakhs.setShoghleAsli(bimeShodeShakhs.getShoghleAsli());
//                newBimeShodeShakhs.setShoghleFarei(bimeShodeShakhs.getShoghleFarei());
//                newBimeShodeShakhs.setShomareSabt(bimeShodeShakhs.getShomareSabt());
//                newBimeShodeShakhs.setTarikheSabt(bimeShodeShakhs.getTarikheSabt());
//                newBimeShodeShakhs.setTarikheTavallod(bimeShodeShakhs.getTarikheTavallod());
//                newBimeShodeShakhs.setShomareShenasnameh(bimeShodeShakhs.getShomareShenasnameh());
//                newBimeShodeShakhs.setType(bimeShodeShakhs.getType());
//                newBimeShodeShakhs.setTypeString(bimeShodeShakhs.getTypeString());
//                newBimeShodeShakhs.setVaziateTaahol(bimeShodeShakhs.getVaziateTaahol());
//                shakhsDAO.save(newBimeShodeShakhs);
//                newBimeshode.setShakhs(newBimeShodeShakhs);
//            } else {
//                newBimeshode.setShakhs(bimeShodeShakhs);
//            }
//            bimeShodeDAO.save(newBimeshode);
//            newPishnehad.setBimeShode(newBimeshode);
//            Address addressBimeShode = newPishnehad.getAddressBimeShode();
//            Address newAddressBimeShode = new Address();
//
//
//            newAddressBimeShode.setTelephoneManzel(addressBimeShode.getTelephoneManzel());
//            newAddressBimeShode.setCodeTelephoneMahalleKar(addressBimeShode.getCodeTelephoneMahalleKar());
//            newAddressBimeShode.setCodeTelephoneManzel(addressBimeShode.getCodeTelephoneManzel());
//            newAddressBimeShode.setKodePostiMahallekar(addressBimeShode.getKodePostiMahallekar());
//            newAddressBimeShode.setKodePostiManzel(addressBimeShode.getKodePostiManzel());
//            newAddressBimeShode.setNeshaniMahalleKar(addressBimeShode.getNeshaniMahalleKar());
//            newAddressBimeShode.setNeshaniManzel(addressBimeShode.getNeshaniManzel());
//            newAddressBimeShode.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeShode.getOstaanMahalleKar().getCityId()));
//            newAddressBimeShode.setOstaanManzel(constantItemsService.findCityById(addressBimeShode.getOstaanManzel().getCityId()));
//            newAddressBimeShode.setPosteElectronic(addressBimeShode.getPosteElectronic());
//            newAddressBimeShode.setShahrMahalleKar(constantItemsService.findCityById(addressBimeShode.getShahrMahalleKar().getCityId()));
//            newAddressBimeShode.setShahrManzel(constantItemsService.findCityById(addressBimeShode.getShahrManzel().getCityId()));
//            newAddressBimeShode.setTelephoneHamrah(addressBimeShode.getTelephoneHamrah());
//            newAddressBimeShode.setTelephoneMahalleKar(addressBimeShode.getTelephoneMahalleKar());
//            addressDAO.save(newAddressBimeShode);
//            newPishnehad.setAddressBimeShode(newAddressBimeShode);
//        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
//            if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.SameAsBimeGozar)) {
//                BimeShode newBimeshode = new BimeShode();
//                newBimeshode.setShakhs(oldPishnehad.getBimeGozar().getShakhs());
//                bimeShodeDAO.save(newBimeshode);
//                newPishnehad.setBimeShode(newBimeshode);
//                Address addressBimeGozar = oldPishnehad.getAddressBimeGozar();
//                Address newAddressBimeShode = new Address();
//                newAddressBimeShode.setTelephoneManzel(addressBimeGozar.getTelephoneManzel());
//                newAddressBimeShode.setCodeTelephoneMahalleKar(addressBimeGozar.getCodeTelephoneMahalleKar());
//                newAddressBimeShode.setCodeTelephoneManzel(addressBimeGozar.getCodeTelephoneManzel());
//                newAddressBimeShode.setKodePostiMahallekar(addressBimeGozar.getKodePostiMahallekar());
//                newAddressBimeShode.setKodePostiManzel(addressBimeGozar.getKodePostiManzel());
//                newAddressBimeShode.setNeshaniMahalleKar(addressBimeGozar.getNeshaniMahalleKar());
//                newAddressBimeShode.setNeshaniManzel(addressBimeGozar.getNeshaniManzel());
//                newAddressBimeShode.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeGozar.getOstaanMahalleKar().getCityId()));
//                newAddressBimeShode.setOstaanManzel(constantItemsService.findCityById(addressBimeGozar.getOstaanManzel().getCityId()));
//                newAddressBimeShode.setPosteElectronic(addressBimeGozar.getPosteElectronic());
//                newAddressBimeShode.setShahrMahalleKar(constantItemsService.findCityById(addressBimeGozar.getShahrMahalleKar().getCityId()));
//                newAddressBimeShode.setShahrManzel(constantItemsService.findCityById(addressBimeGozar.getShahrManzel().getCityId()));
//                newAddressBimeShode.setTelephoneHamrah(addressBimeGozar.getTelephoneHamrah());
//                newAddressBimeShode.setTelephoneMahalleKar(addressBimeGozar.getTelephoneMahalleKar());
//                addressDAO.save(newAddressBimeShode);
//                newPishnehad.setAddressBimeShode(newAddressBimeShode);
//            } else if (mafad.getBimeshodeSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
//                shakhsDAO.save(newPishnehad.getBimeShode().getShakhs());
//                bimeShodeDAO.save(newPishnehad.getBimeShode());
//                addressDAO.save(newPishnehad.getAddressBimeShode());
//            }
//            newEstelam.setSeneBimeie(newPishnehad.getBimeShode().getShakhs().getSen());
//            newEstelam.setSen_bime_shode(newPishnehad.getBimeShode().getShakhs().getSen());
//            newEstelam.setTarikh_tavalod(newPishnehad.getBimeShode().getShakhs().getTarikheTavallod());
//        }
//
//        //=================
////        logService.getBimeGozarOfPishnehadChangesList();
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            BimeGozar bimeGozar = newPishnehad.getBimeGozar();
//            Shakhs bimeGozarShakhs = newPishnehad.getBimeGozar().getShakhs();
//            BimeGozar newBimegozar = new BimeGozar();
//            newBimegozar.setDaramadeMahiane(bimeGozar.getDaramadeMahiane());
//            newBimegozar.setNesbatBabimeShode(bimeGozar.getNesbatBabimeShode());
//            if (oldPishnehad.getBimeGozar().getShakhs().getId().equals(oldPishnehad.getBimeShode().getShakhs().getId())) {
//                newBimegozar.setShakhs(newPishnehad.getBimeShode().getShakhs());
//            } else {
//                if (shouldCreateShakhsBimeGozar) {
//                    Shakhs newBimeGozarShakhs = new Shakhs();
//                    newBimeGozarShakhs.setMahalleSabt(bimeGozarShakhs.getMahalleSabt());
//                    newBimeGozarShakhs.setMahalleSodoreShenasnameh(bimeGozarShakhs.getMahalleSodoreShenasnameh());
//                    newBimeGozarShakhs.setMahalleTavallod(bimeGozarShakhs.getMahalleTavallod());
//                    newBimeGozarShakhs.setDolatiKhososi(bimeGozarShakhs.getDolatiKhososi());
//                    newBimeGozarShakhs.setIraniOrAtbaeKhareji(bimeGozarShakhs.getIraniOrAtbaeKhareji());
//                    newBimeGozarShakhs.setJensiat(bimeGozarShakhs.getJensiat());
//                    newBimeGozarShakhs.setKodeEghtesadi(bimeGozarShakhs.getKodeEghtesadi());
//                    newBimeGozarShakhs.setKodeMelliShenasayi(bimeGozarShakhs.getKodeMelliShenasayi());
//                    newBimeGozarShakhs.setName(bimeGozarShakhs.getName());
//                    newBimeGozarShakhs.setNameKhanevadegi(bimeGozarShakhs.getNameKhanevadegi());
//                    newBimeGozarShakhs.setNamePedar(bimeGozarShakhs.getNamePedar());
//                    newBimeGozarShakhs.setNoeFaaliat(bimeGozarShakhs.getNoeFaaliat());
//                    newBimeGozarShakhs.setPishvand(bimeGozarShakhs.getPishvand());
//                    newBimeGozarShakhs.setShoghleAsli(bimeGozarShakhs.getShoghleAsli());
//                    newBimeGozarShakhs.setShoghleFarei(bimeGozarShakhs.getShoghleFarei());
//                    newBimeGozarShakhs.setShomareSabt(bimeGozarShakhs.getShomareSabt());
//                    newBimeGozarShakhs.setTarikheSabt(bimeGozarShakhs.getTarikheSabt());
//                    newBimeGozarShakhs.setTarikheTavallod(bimeGozarShakhs.getTarikheTavallod());
//                    newBimeGozarShakhs.setShomareShenasnameh(bimeGozarShakhs.getShomareShenasnameh());
//                    newBimeGozarShakhs.setType(bimeGozarShakhs.getType());
//                    newBimeGozarShakhs.setTypeString(bimeGozarShakhs.getTypeString());
//                    newBimeGozarShakhs.setVaziateTaahol(bimeGozarShakhs.getVaziateTaahol());
//                    shakhsDAO.save(newBimeGozarShakhs);
//                    newBimegozar.setShakhs(newBimeGozarShakhs);
//                } else {
//                    newBimegozar.setShakhs(bimeGozarShakhs);
//                }
//            }
//
//            newPishnehad.setBimeGozar(newBimegozar);
//            bimeGozarDAO.save(newBimegozar);
//            Address addressBimeGozar = newPishnehad.getAddressBimeGozar();
//            Address newAddressBimeGozar = new Address();
//            newAddressBimeGozar.setTelephoneManzel(addressBimeGozar.getTelephoneManzel());
//            newAddressBimeGozar.setCodeTelephoneMahalleKar(addressBimeGozar.getCodeTelephoneMahalleKar());
//            newAddressBimeGozar.setCodeTelephoneManzel(addressBimeGozar.getCodeTelephoneManzel());
//            newAddressBimeGozar.setKodePostiMahallekar(addressBimeGozar.getKodePostiMahallekar());
//            newAddressBimeGozar.setKodePostiManzel(addressBimeGozar.getKodePostiManzel());
//            newAddressBimeGozar.setNeshaniMahalleKar(addressBimeGozar.getNeshaniMahalleKar());
//            newAddressBimeGozar.setNeshaniManzel(addressBimeGozar.getNeshaniManzel());
//            newAddressBimeGozar.setOstaanMahalleKar(constantItemsService.findCityById(addressBimeGozar.getOstaanMahalleKar().getCityId()));
//            if (newBimegozar.getShakhs().getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
//                newAddressBimeGozar.setOstaanManzel(constantItemsService.findCityById(addressBimeGozar.getOstaanManzel().getCityId()));
//            newAddressBimeGozar.setPosteElectronic(addressBimeGozar.getPosteElectronic());
//            newAddressBimeGozar.setShahrMahalleKar(constantItemsService.findCityById(addressBimeGozar.getShahrMahalleKar().getCityId()));
//            if (newBimegozar.getShakhs().getType().equals(Shakhs.BimeGozarType.HAGHIGHI))
//                newAddressBimeGozar.setShahrManzel(constantItemsService.findCityById(addressBimeGozar.getShahrManzel().getCityId()));
//            newAddressBimeGozar.setTelephoneHamrah(addressBimeGozar.getTelephoneHamrah());
//            newAddressBimeGozar.setTelephoneMahalleKar(addressBimeGozar.getTelephoneMahalleKar());
//            addressDAO.save(newAddressBimeGozar);
//            newPishnehad.setAddressBimeGozar(newAddressBimeGozar);
//        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami)) {
//            if (mafad.getBimegozarSource().equals(PishnehadDuplicationRules.PishnehadCopySource.SherkateTarafeGharardad)) {
//                BimeGozar newBimegozar = new BimeGozar();
//                newBimegozar.setDaramadeMahiane(null);
//                newBimegozar.setNesbatBabimeShode("نامعلوم");
//
//                List<SearchResult> newBimeGozarShakhsList = shakhsDAO.doSearchHoghughi(gharardad.getNameSherkat(), gharardad.getShomareSabt());
//                Shakhs newBimeGozarShakhs;
//                if (newBimeGozarShakhsList.size() > 0) { // Shakhs found
//                    newBimeGozarShakhs = shakhsDAO.findById(newBimeGozarShakhsList.get(0).getId());
//                } else { // Shakhs not found, create it
//                    newBimeGozarShakhs = new Shakhs();
//                    newBimeGozarShakhs.setMahalleSabt(gharardad.getShahr());
//                    newBimeGozarShakhs.setMahalleSodoreShenasnameh(null);
//                    newBimeGozarShakhs.setMahalleTavallod(null);
//                    newBimeGozarShakhs.setDolatiKhososi(null); // sherkat
//                    newBimeGozarShakhs.setIraniOrAtbaeKhareji(null);
//                    newBimeGozarShakhs.setJensiat(null);
//                    newBimeGozarShakhs.setKodeEghtesadi(null);  // sherkat , shomare roozname ?
//                    newBimeGozarShakhs.setKodeMelliShenasayi(null);
//                    newBimeGozarShakhs.setName(gharardad.getNameSherkat());
//                    newBimeGozarShakhs.setNameKhanevadegi(null);
//                    newBimeGozarShakhs.setNamePedar(null);
//                    newBimeGozarShakhs.setNoeFaaliat(null); // sherkat
//                    newBimeGozarShakhs.setPishvand(null);
//                    newBimeGozarShakhs.setShoghleAsli(null);
//                    newBimeGozarShakhs.setShoghleFarei(null);
//                    newBimeGozarShakhs.setShomareSabt(gharardad.getShomareSabt());
//                    newBimeGozarShakhs.setTarikheSabt(gharardad.getTarikhSabt());
//                    newBimeGozarShakhs.setTarikheTavallod(null);
//                    newBimeGozarShakhs.setShomareShenasnameh(null);
//                    newBimeGozarShakhs.setType(Shakhs.BimeGozarType.HOGHOGHI);
//                    newBimeGozarShakhs.setTypeString(null);
//                    newBimeGozarShakhs.setVaziateTaahol(null);
//                    shakhsDAO.save(newBimeGozarShakhs);
//                }
//                newBimegozar.setShakhs(newBimeGozarShakhs);
//                newPishnehad.setBimeGozar(newBimegozar);
//                Address newAddressBimeGozar = new Address();
//                newAddressBimeGozar.setTelephoneManzel(null);
//                newAddressBimeGozar.setCodeTelephoneMahalleKar(null);
//                newAddressBimeGozar.setCodeTelephoneManzel(null);
//                newAddressBimeGozar.setKodePostiMahallekar(null);
//                newAddressBimeGozar.setKodePostiManzel(null);
//                newAddressBimeGozar.setNeshaniMahalleKar(gharardad.getNeshani());
//                newAddressBimeGozar.setNeshaniManzel(null);
//                newAddressBimeGozar.setOstaanMahalleKar(gharardad.getOstan());
//                newAddressBimeGozar.setOstaanManzel(null);
//                newAddressBimeGozar.setPosteElectronic(null);
//                newAddressBimeGozar.setShahrMahalleKar(gharardad.getShahr());
//                newAddressBimeGozar.setShahrManzel(null);
//                newAddressBimeGozar.setTelephoneHamrah(null);
//                newAddressBimeGozar.setTelephoneMahalleKar(gharardad.getTelphone());
//                addressDAO.save(newAddressBimeGozar);
//                newPishnehad.setAddressBimeGozar(newAddressBimeGozar);
//                bimeGozarDAO.save(newBimegozar);
//                // shomare ruzname va fax mundan
//            } else if (mafad.getBimegozarSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
//                shakhsDAO.save(newPishnehad.getBimeGozar().getShakhs());
//                bimeShodeDAO.save(newPishnehad.getBimeGozar());
//                addressDAO.save(newPishnehad.getAddressBimeGozar());
//            }
//
//        }
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi) || mafad.getSoalateOmoomiSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate)) {
//            SoalateOmomiAzBimeShode soalateOmomiAzBimeShode;
//            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//                soalateOmomiAzBimeShode = newPishnehad.getSoalateOmomiAzBimeShode();
//            } else {
//                soalateOmomiAzBimeShode = oldPishnehad.getSoalateOmomiAzBimeShode();
//            }
//            SoalateOmomiAzBimeShode newSoalateOmumi = new SoalateOmomiAzBimeShode();
//            newSoalateOmumi.setGhad_bime_shode(soalateOmomiAzBimeShode.getGhad_bime_shode());
//            newSoalateOmumi.setKhdemat_nezaam_vazife(soalateOmomiAzBimeShode.getKhdemat_nezaam_vazife());
//            newSoalateOmumi.setMoaafiyat_pezeshki(soalateOmomiAzBimeShode.getMoaafiyat_pezeshki());
//            newSoalateOmumi.setTozih_faaliyat_janbi(soalateOmomiAzBimeShode.getTozih_faaliyat_janbi());
//            newSoalateOmumi.setVazn_bime_shode(soalateOmomiAzBimeShode.getVazn_bime_shode());
//            soalateOmomiAzBimeShodeDAO.save(newSoalateOmumi);
//            newPishnehad.setSoalateOmomiAzBimeShode(newSoalateOmumi);
//        }
//
//        Foroshande foroshande = oldPishnehad.getForoshande();
//        Foroshande newForushande = new Foroshande();
//        newForushande.setEtelaat_kaamel(foroshande.getEtelaat_kaamel());
//        newForushande.setModat_shenaakht(foroshande.getModat_shenaakht());
//        newForushande.setMolaahezaat_az_salaamat_feli(foroshande.getMolaahezaat_az_salaamat_feli());
//        newForushande.setPishnahaad_dahande_mishenaasid(foroshande.getPishnahaad_dahande_mishenaasid());
//        newForushande.setSehat_emzaa(foroshande.getSehat_emzaa());
//        newForushande.setTozihe_molaahezaat(foroshande.getTozihe_molaahezaat());
//        foroshandeDAO.save(newForushande);
//        newPishnehad.setForoshande(newForushande);
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi) || mafad.getBimenameSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzTemplate)) {
//            Estelam estelam;
//            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//                estelam = newPishnehad.getEstelam();
//                newEstelam.setDarsad_ezafe_nerkh_pezeshki(newPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());
//            } else {
//                estelam = oldPishnehad.getEstelam();
//                newEstelam.setDarsad_ezafe_nerkh_pezeshki(oldPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki());
//            }
//            newEstelam.setDarsad_takhfif_karmozd_karmozd(estelam.getDarsad_takhfif_karmozd_karmozd());
//            newEstelam.setDarsad_takhfif_vosool(estelam.getDarsad_takhfif_vosool());
//            newEstelam.setHagh_bime_pardakhti(estelam.getHagh_bime_pardakhti());
//            newEstelam.setJoziyate_pooshesh_amraz_khas(estelam.getJoziyate_pooshesh_amraz_khas());
//            newEstelam.setJoziyate_pooshesh_moafiat(estelam.getJoziyate_pooshesh_moafiat());
//            // hagh bime avvalie duplicate nemishe
//            if (newPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal() != null)
//                newEstelam.setMablagh_seporde_ebtedaye_sal(newPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal());
//            else
//                newEstelam.setMablagh_seporde_ebtedaye_sal(oldPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal());
////            newEstelam.setMablagh_seporde_ebtedaye_sal(estelam.getMablagh_seporde_ebtedaye_sal());
//            newEstelam.setModat_bimename(estelam.getModat_bimename());
//            newEstelam.setModdat_zaman_daryaft_mostamari(estelam.getModdat_zaman_daryaft_mostamari());
//            newEstelam.setNahve_daryaft_mostamari(estelam.getNahve_daryaft_mostamari());
//            newEstelam.setNahve_pardakht_hagh_bime(estelam.getNahve_pardakht_hagh_bime());
//            newEstelam.setNahveye_daryafte_andukhte_entehaye_modat_bimename(estelam.getNahveye_daryafte_andukhte_entehaye_modat_bimename());
//            newEstelam.setNahve_kasr_hagh_bime_poosheshhaye_ezafi(estelam.getNahve_kasr_hagh_bime_poosheshhaye_ezafi());
//            newEstelam.setNam_bime_shode(estelam.getNam_bime_shode());
//            newEstelam.setNerkh_afzayesh_salaneh_hagh_bime(estelam.getNerkh_afzayesh_salaneh_hagh_bime());
//            newEstelam.setNerkh_afzayesh_salaneh_mostamari(estelam.getNerkh_afzayesh_salaneh_mostamari());
//            newEstelam.setNerkh_afzayesh_salaneh_sarmaye_fot(estelam.getNerkh_afzayesh_salaneh_sarmaye_fot());
//            newEstelam.setNoe_tarh(estelam.getNoe_tarh());
//            newEstelam.setPooshesh_amraz_khas(estelam.getPooshesh_amraz_khas());
//            newEstelam.setPooshesh_amraz_tarikh_payan(estelam.getPooshesh_amraz_tarikh_payan());
//            newEstelam.setPooshesh_amraz_tarikh_shoru(estelam.getPooshesh_amraz_tarikh_shoru());
//            newEstelam.setPooshesh_fot_dar_asar_haadese(estelam.getPooshesh_fot_dar_asar_haadese());
//            newEstelam.setPooshesh_fot_dar_asar_zelzele(estelam.getPooshesh_fot_dar_asar_zelzele());
//            newEstelam.setPooshesh_fot_tarikh_payan(estelam.getPooshesh_fot_tarikh_payan());
//            newEstelam.setPooshesh_fot_tarikh_shoru(estelam.getPooshesh_fot_tarikh_shoru());
//            newEstelam.setPooshesh_moafiat(estelam.getPooshesh_moafiat());
//            newEstelam.setPooshesh_naghs_ozv(estelam.getPooshesh_naghs_ozv());
//            newEstelam.setPooshesh_naghs_tarikh_payan(estelam.getPooshesh_naghs_tarikh_payan());
//            newEstelam.setPooshesh_naghs_tarikh_shoru(estelam.getPooshesh_naghs_tarikh_shoru());
//            newEstelam.setPooshesh_zelzele_tarikh_payan(estelam.getPooshesh_zelzele_tarikh_payan());
//            newEstelam.setPooshesh_zelzele_tarikh_shoru(estelam.getPooshesh_zelzele_tarikh_shoru());
//            newEstelam.setSarmaye_paye_fot(estelam.getSarmaye_paye_fot());
//            newEstelam.setSarmaye_paye_fot_dar_asar_hadese(estelam.getSarmaye_paye_fot_dar_asar_hadese());
//            newEstelam.setSarmaye_pooshesh_amraz_khas(estelam.getSarmaye_pooshesh_amraz_khas());
//            newEstelam.setSarmaye_pooshesh_naghs_ozv(estelam.getSarmaye_pooshesh_naghs_ozv());
//            newEstelam.setTabaghe_khatar(oldPishnehad.getEstelam().getTabaghe_khatar());
//            newEstelam.setTabaghe_khatar_naghsozv(oldPishnehad.getEstelam().getTabaghe_khatar_naghsozv());
//            if (newEstelam.getTabaghe_khatar() != null && newEstelam.getTabaghe_khatar().equals("0"))
//                newEstelam.setTabaghe_khatar("1");
//            if (newEstelam.getTabaghe_khatar_naghsozv() != null && newEstelam.getTabaghe_khatar_naghsozv().equals("0"))
//                newEstelam.setTabaghe_khatar_naghsozv("1");
//            if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//                newPishnehad.setBimename(oldPishnehad.getBimename());
//                if (newPishnehad.getBimeShode().getShakhs().getTarikheTavallod().equals(oldPishnehad.getBimeShode().getShakhs().getTarikheTavallod())) {
//                    newEstelam.setSen_bime_shode(oldPishnehad.getEstelam().getSen_bime_shode());
//                    newEstelam.setSeneBimeie(oldPishnehad.getEstelam().getSeneBimeie());
//                    newEstelam.setTarikh_tavalod(oldPishnehad.getEstelam().getTarikh_tavalod());
//                } else {
//                    newEstelam.setSen_bime_shode(estelam.getSen_bime_shode());
//                    newEstelam.setSeneBimeie(estelam.getSeneBimeie());
//                    newEstelam.setTarikh_tavalod(estelam.getTarikh_tavalod());
//                }
//            }
//            newEstelam.setPishnehad(newPishnehad);
//            estelamDAO.save(newEstelam);
//            newPishnehad.setEstelam(newEstelam);
//        } else if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.SodorJami) && mafad.getBimenameSource().equals(PishnehadDuplicationRules.PishnehadCopySource.AzExcel)) {
//            estelamDAO.save(newPishnehad.getEstelam());
//        }
//
//
//        if (mafad.getSource().equals(PishnehadDuplicationRules.DuplicationSource.ElhaghieEslahi)) {
//            pishnehadDAO.save(newPishnehad);
//        } else {
//            pishnehadDAO.update(newPishnehad);
//        }
//
////=================
//
//        return newPishnehad;
//    }

    public List loadAllBimenameByFilterForZakhire(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId) {
        return bimenameDAO.findAllBimenameByFilterForZakhire(azTarikheSodoreBimename, taTarikheSodoreBimename, shomareBimename, isExport, onlyId);
    }

    public List<Bimename> findByBimenameBazkharidShode(BimenameForGozaresh bimenamehSearch) {
        return bimenameDAO.findByBimenameBazkharidShode(bimenamehSearch);
    }

    public List<Bimename> findByBimenameSarresidShode(BimenameForGozaresh bimenamehSearch) {
        return bimenameDAO.findByBimenameSarresidShode(bimenamehSearch);
    }

    public List findAllBimenameByFilterForZakhireTafkikGhest(String tarikhMabna, String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename, boolean isExport, boolean onlyId) {
        return bimenameDAO.findAllBimenameByFilterForZakhireTafkikGhest(tarikhMabna, azTarikheSodoreBimename, taTarikheSodoreBimename, shomareBimename, isExport, onlyId);
    }

    public List<Bimename> loadAllBimenameByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename, String shomareBimename) {
        return bimenameDAO.findAllBimenameByFilter(azTarikheSodoreBimename, taTarikheSodoreBimename, shomareBimename);
    }

    public PaginatedListImpl makeGozareshZakhireRiazi(PaginatedListImpl pgList, ZakhireRiazi zr) {
        return bimenameDAO.makeGozareshZakhireRiazi(pgList, zr);
    }

    public List<Ghest> loadAllBimenameGhestsByFilter(String azTarikheSodoreBimename, String taTarikheSodoreBimename) {
        return bimenameDAO.findAllBimenameGhestByFilter(azTarikheSodoreBimename, taTarikheSodoreBimename);
    }

    public void removeMoarefiname(Integer id) {

        pishnehadDAO.removeMoarefiname(id);
    }

    public boolean hasPermission(User user, Integer stateId) {
        Set<Role> roleSet = user.getRoles();
        for (Role role : roleSet) {
            Set<Transition> transitionSet = role.getTransitions();
            for (Transition transition : transitionSet) {
                if (transition.getStateBegin().getId().equals(stateId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void saveSharayeteJadid(SharayeteJadid sharayeteJadid) {
        pishnehadDAO.saveSharayeteJadid(sharayeteJadid);
    }

    public void saveElameHesab(ElameHesab elameHesab) {
        pishnehadDAO.saveElameHesab(elameHesab);
    }

    public void updateElameHesab(ElameHesab elameHesab) {
        pishnehadDAO.updateElameHesab(elameHesab);
    }

    public List<Namayande> findAllNamayandes() {
        Set<Namayande> nama = new HashSet<Namayande>();
        nama.addAll(pishnehadDAO.findallNamayandes());
        List<Namayande> result = new ArrayList<Namayande>();
        result.addAll(nama);
        return result;
    }

    public List<State> findAllSatetsForPishnehadSystem() {

        return pishnehadDAO.findAllStatesForPishnehadSystem();
    }

    public List<User> findAllKarshenasForPishnehads(String tab) {
        return pishnehadDAO.findAllKarshenas(tab);
    }

    public User findKarshenasForNezaratAssignment(boolean nazer, boolean masool, boolean raees, User user) {
        return pishnehadDAO.findKarshenasForNezaratAssignment(nazer, masool, raees, user);
    }

    public List<User> findAllKarshenasForPishnehads(Long sarmayePayeFot) {
        return pishnehadDAO.findAllKarshenas(sarmayePayeFot);
    }

    public List<User> findAllKarshenasKhesarat() {
        return pishnehadDAO.findAllKarshenasKhesarat();
    }

    public List<User> findAllKarshenasForPishnehadsByCity(City city) {
        return pishnehadDAO.findAllKarshenasByCity(city);
    }

    public List<BazarYab> findAllBazaryabs() {
        return pishnehadDAO.findallBazaryabs();
    }

    public List<User> findBazaryabForNamayande(Namayande namayande) {
        return pishnehadDAO.findBazaryabForNamayande(namayande);
    }

    public BazarYab findBazayabById(Integer id) {
        return pishnehadDAO.findBazarYabById(id);
    }

    @Transactional
    public List<PishnehadEstelam> searchPishnehadForBordoyeSodour(PishnehadSearch pishnehadSearch) {
        return pishnehadDAO.searchPishnehadForBordoyeSodour(pishnehadSearch);
    }

    public PaginatedListImpl<PishnehadEstelam> searchPishnehadForBordoyeSodourPaginated(PaginatedListImpl pgList, PishnehadSearch pishnehadSearch) {
        return pishnehadDAO.searchPishnehadForBordoyeSodourPaging(pgList, pishnehadSearch);
    }

    @Transactional
//    public PaginatedListImpl<Pishnehad> findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList) {
//
//        return pishnehadDAO.findPishnehadsByPishnehadSearch(pishnehadSearch, user, paginatedList);
//    }

    public PaginatedListImpl findPishnehadsByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl paginatedList) {
        List<Integer> rolesId = userDAO.getRolesIdOfUser(user);
        List<Integer> statesId = transitionDAO.getStateBeginsOfTransitions(rolesId);
        if (statesId == null || statesId.size() == 0)
            statesId.add(0);
        paginatedList = pishnehadDAO.findPishnehadsByPishnehadSearch(pishnehadSearch, user, paginatedList, statesId);
        List<PishnehadsVM> list = paginatedList.getList();
        for (PishnehadsVM p : list) {
            List<String> founds = pishnehadDAO.getFoundFishs(p.getId());

            boolean result = true;
            for (String found : founds) {
                result = result && (found.equalsIgnoreCase("true"));
            }
            if (founds.size() == 0) result = false;
            p.setPishpardakhtOK(result);
        }
        paginatedList.setList(list);
        return paginatedList;
    }

    @Transactional
//    public PaginatedListImpl<Pishnehad> findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl<Pishnehad> paginatedList) {
//        return pishnehadDAO.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, paginatedList);
//    }

    public PaginatedListImpl findPishnehadsToViewByPishnehadSearch(PishnehadSearch pishnehadSearch, User user, PaginatedListImpl paginatedList) {
        paginatedList = pishnehadDAO.findPishnehadsToViewByPishnehadSearch(pishnehadSearch, user, paginatedList);
        List<PishnehadsVM> list = paginatedList.getList();
        for (PishnehadsVM p : list) {
            List<String> founds = pishnehadDAO.getFoundFishs(p.getId());

            boolean result = true;
            for (String found : founds) {
                result = result && (found.equalsIgnoreCase("true"));
            }
            if (founds.size() == 0) result = false;
            p.setPishpardakhtOK(result);
        }
        paginatedList.setList(list);
        return paginatedList;
    }

    @Transactional
    public void ebtalMoarefiname(Moarefiname theMoarefiname) {
        theMoarefiname.setVaziat(Moarefiname.Vaziat.BATEL_SHODE);
        pishnehadDAO.updateMoarefiname(theMoarefiname);
    }


//    ------------------------------------------------------------------------------------------------------------------


    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void setTransitionDAO(TransitionDAO transitionDAO) {
        this.transitionDAO = transitionDAO;
    }

    public void setShakhsDAO(ShakhsDAO shakhsDAO) {
        this.shakhsDAO = shakhsDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setBimeGozarDAO(BimeGozarDAO bimeGozarDAO) {
        this.bimeGozarDAO = bimeGozarDAO;
    }

    public void setBimeShodeDAO(BimeShodeDAO bimeShodeDAO) {
        this.bimeShodeDAO = bimeShodeDAO;
    }

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    public void setForoshandeDAO(ForoshandeDAO foroshandeDAO) {
        this.foroshandeDAO = foroshandeDAO;
    }

    public void setPishnehadDAO(PishnehadDAO pishnehadDAO) {
        this.pishnehadDAO = pishnehadDAO;
    }

    public void setSavabegheBimeiDAO(SavabegheBimeiDAO savabegheBimeiDAO) {
        this.savabegheBimeiDAO = savabegheBimeiDAO;
    }

    public void setSoalateOmomiAzBimeShodeDAO(SoalateOmomiAzBimeShodeDAO soalateOmomiAzBimeShodeDAO) {
        this.soalateOmomiAzBimeShodeDAO = soalateOmomiAzBimeShodeDAO;
    }

    public void setVaziateSalamatiBimeShodeDAO(VaziateSalamatiBimeShodeDAO vaziateSalamatiBimeShodeDAO) {
        this.vaziateSalamatiBimeShodeDAO = vaziateSalamatiBimeShodeDAO;
    }

    public void setVaziateSalamatiKhanevadeyeBimeShodeDAO(VaziateSalamatiKhanevadeyeBimeShodeDAO vaziateSalamatiKhanevadeyeBimeShodeDAO) {
        this.vaziateSalamatiKhanevadeyeBimeShodeDAO = vaziateSalamatiKhanevadeyeBimeShodeDAO;
    }

    public void setEstefadeKonandeganAzSarmayeBimeDAO(EstefadeKonandeganAzSarmayeBimeDAO estefadeKonandeganAzSarmayeBimeDAO) {
        this.estefadeKonandeganAzSarmayeBimeDAO = estefadeKonandeganAzSarmayeBimeDAO;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public void setBimenameDAO(BimenameDAO bimenameDAO) {
        this.bimenameDAO = bimenameDAO;
    }

    public BimenameDAO getBimenameDAO() {
        return bimenameDAO;
    }

    public void setEstelamDAO(EstelamDAO estelamDAO) {
        this.estelamDAO = estelamDAO;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public List<Credebit> findForMabaleghePardakhti(Bimename bimename, String azTarikh, String taTarikh) {
        return bimenameDAO.findForMabaleghePardakhti(bimename, azTarikh, taTarikh);
    }

    public List<Bimename> findAllBimenameForNamayande(Long nId, String azDate, String taDate) {
        return bimenameDAO.findAllBimenameForNamayande(nId, azDate, taDate);
    }

    public List<Bimename> findAllBimenameForNamayande(Long nId) {
        return bimenameDAO.findAllBimenameForNamayande(nId);
    }

    public List<Ghest> searchGhest(String shomare, String saleBimei, String bimeGozar, String bimeShode, String shomareMoshtari, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, String azPardakht, String taPardakht, String azMablagh, String taMablagh, String azSanad, String taSanad, State state, User user) {
        return bimenameDAO.searchGhest(shomare, saleBimei, bimeGozar, bimeShode, shomareMoshtari, azTarikhSodur, taTarikhSodur, azSarresid, taSarresid, azPardakht, taPardakht, azMablagh, taMablagh, azSanad, taSanad, state, user);

    }

    public List<Bimename> searchBimename(String bimeGozar, String bimeShode, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid, State state, Integer barresiPezeshki, String nahvePardakhtS, User user) {
        return bimenameDAO.searchBimename(bimeGozar, bimeShode, shomare, azTarikhSodur, taTarikhSodur, azSarresid, taSarresid, state, barresiPezeshki, nahvePardakhtS, user);
    }

//    public PaginatedListImpl<Bimename> searchBimenamePaginated(PaginatedListImpl<Bimename>pgList, String bimeGozarName,String bimeGozarFamily,String bimeGozarKodeMelli, String bimeShodeName,String bimeShodeFamily,
//                                                               String bimeShodeKodeMelli, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid,
//                                                               State state, Integer subsetState, Integer barresiPezeshki, String nahvePardakhtS, User user, String sarmayeFowt,String codeRahgiri,
//                                                               Integer namayandegiId,Integer vahedSodurId,String noe_tarh,String noeGharardad,String KarbareSabtKonande,Long groupId, Boolean isCodeMovaghat)
//    {
//        return bimenameDAO.searchBimenamePaginated( pgList,bimeGozarName, bimeGozarFamily, bimeGozarKodeMelli, bimeShodeName, bimeShodeFamily,
//                                                                bimeShodeKodeMelli, shomare, azTarikhSodur, taTarikhSodur, azSarresid, taSarresid,
//                                                                state, subsetState, barresiPezeshki, nahvePardakhtS, user, sarmayeFowt, codeRahgiri,
//                                                                namayandegiId, vahedSodurId, noe_tarh, noeGharardad,KarbareSabtKonande,groupId, isCodeMovaghat);
//    }

    public PaginatedListImpl searchBimenamePaginated(PaginatedListImpl pgList, String bimeGozarName, String bimeGozarFamily, String bimeGozarKodeMelli, String bimeShodeName, String bimeShodeFamily,
                                                     String bimeShodeKodeMelli, String shomare, String azTarikhSodur, String taTarikhSodur, String azSarresid, String taSarresid,
                                                     State state, Integer subsetState, Integer barresiPezeshki, String nahvePardakhtS, User user, String sarmayeFowt, String codeRahgiri,
                                                     Long namayandegiId, Long vahedSodurId, String noe_tarh, String noeGharardad, String KarbareSabtKonande, Long groupId, Boolean isCodeMovaghat) {
        return bimenameDAO.searchBimenamePaginated(pgList, bimeGozarName, bimeGozarFamily, bimeGozarKodeMelli, bimeShodeName, bimeShodeFamily,
                bimeShodeKodeMelli, shomare, azTarikhSodur, taTarikhSodur, azSarresid, taSarresid,
                state, subsetState, barresiPezeshki, nahvePardakhtS, user, sarmayeFowt, codeRahgiri,
                namayandegiId, vahedSodurId, noe_tarh, noeGharardad, KarbareSabtKonande, groupId, isCodeMovaghat);
    }

    public List<GStructure> makeGozareshTedadVaziat(Role role, String azTarikh, String taTarikh) {
        return pishnehadDAO.makeGozareshTedadVaziat(role, azTarikh, taTarikh);
    }

    public Map<String, Double> gozareshNemudarData(String azTarikh, String taTarikh, boolean jadid) {
        return pishnehadDAO.gozareshNemudarDate(azTarikh, taTarikh, jadid);
    }

    public List<GStructureTafkikVaziat> makeGozareshTedadTafkik(String azTarikh, String taTarikh, User user) {
        return pishnehadDAO.makeGozareshTedadTafkik(azTarikh, taTarikh, user);
    }

    public List<GStructureTafkikVaziat> makeGozareshTedadTafkikVaziat(State state) {
        return pishnehadDAO.makeGozareshTedadTafkikVaziat(state);
    }

    public List<GStructureTransitionLog> makeGozareshTransitionLog(User user, String azTarikh, String taTarikh, String azVaziat, String beVaziat) {
        return pishnehadDAO.makeGozareshTransitionLog(user, azTarikh, taTarikh, azVaziat, beVaziat);
    }

    public List<Fish> makeGozareshPardakhtElectronic(String azTarikh, String taTarikh) {
        return pishnehadDAO.makeGozareshElectronic(azTarikh, taTarikh, true, false);
    }

    public List<Fish> makeGozareshSadereVarizi(String azTarikh, String taTarikh) {
        return pishnehadDAO.makeGozareshElectronic(azTarikh, taTarikh, false, true);
    }

    public Bimename findBimenameByShomare(String shomareBimename) {
        return pishnehadDAO.findBimenameByShomare(shomareBimename);
    }

    public PaginatedListImpl<Pishnehad> findAllByGharardadId(Long id) {
        return pishnehadDAO.findAllByGharardadId(id);
    }

    public PaginatedListImpl<Bimename> findAllBimenameByGharardadId(Long id) {
        return pishnehadDAO.findAllBimenameByGharardadId(id);
    }

    public Pishnehad findByRadif(String templatePishnehadRadif) {
        return pishnehadDAO.findByRadif(templatePishnehadRadif);
    }

    public void createAllPishnehadsMergeByTemplate(Pishnehad templatePishnehad, List<Pishnehad> pishnehads, PishnehadDuplicationRules mafad, Gharardad gharardad) {
        for (Pishnehad pishnehad : pishnehads) {
            savePishnehadForElhaghie(pishnehad, templatePishnehad, mafad, gharardad, null, true, true);
        }
    }

    public List<Bimename> findAllBimenameByGharardadIdNonePaginated(Long id) {
        return pishnehadDAO.findAllBimenameByGharardadIdNonePaginated(id);
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public void setGharardadService(IGharardadService gharardadService) {
        this.gharardadService = gharardadService;
    }

    public void setConstantsService(IConstantsService constantsService) {
        this.constantsService = constantsService;
    }

    public PaginatedListImpl<Pishnehad> gozareshAndukhteSaalAvaal(Long gharardadId) {
        return pishnehadDAO.gozareshAndukhteSaalAvaal(gharardadId);
    }



}
